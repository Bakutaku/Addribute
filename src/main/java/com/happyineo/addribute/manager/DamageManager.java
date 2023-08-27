package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.Status;
import com.happyineo.addribute.Beans.StatusConfig;
import com.happyineo.addribute.StringCalc;
import com.happyineo.addribute.type.LogType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.happyineo.addribute.Utils.color;
import static com.happyineo.addribute.Utils.log;

public class DamageManager {

    private static DamageManager manager;   // 自クラスのインスタンス格納用

    private StatusConfig config = DataManager.getManager().getStatusConfig();   // ステータスのコンフィグ

    private StatusManager status = StatusManager.getManager();  // ステータス

    private List<String> magicUses = new CopyOnWriteArrayList<>();    // 魔法で攻撃したかどうか

    private DamageManager(){
        manager = this;
    }

    /**
     * DamageManagerのインスタンスを取得する
     * @return {@link DamageManager}
     */
    public static DamageManager getManager(){
        if (manager == null) manager = new DamageManager();
        return manager;
    }


    /**
     * 属性倍率を算出する
     * @param atk 攻撃の属性
     * @param defense 防御の属性
     * @return 倍率
     */
    public double calcMags(String atk,String... defense){

        // マネージャーを取得
        AttributeManager manager = AttributeManager.getManager();

        double answer = 0; // 結果格納用

        // 受ける側の属性を1ずつ確かめる
        for(String key : defense){
            // 弱点属性を調べる
            for(String mag : manager.getAttribute(key).getMags().keySet()){
                // 弱点が一致するか調べる
                if(atk.equals(mag)){
                    //  倍率を加算する
                    answer += manager.getAttribute(key).getMags().get(mag).getMag();
                }
            }
        }

        // 倍率を返す
        return answer;
    }


    /**
     * 通常攻撃
     * @param atk 攻撃者
     * @param entity 対象
     * @param attribute 属性
     * @param damage ダメージ
     * @return 耐えれるかどうか(体力が0以下になるとfalseになる)
     */
    public boolean damage(Entity atk,Entity entity,String attribute,double damage){

        // 攻撃者のステータス取得
        Status atkStatus = status.getStatus(atk);

        // 対象のステータス取得
        Status entityStatus = status.getStatus(entity);

        double vitality = 0;    // 防御用
        String[] entityAttribute = {};   // 属性用

        if(atkStatus != null) {
            // ステータスの影響を付与
            // 攻撃力
            damage += (atkStatus.getStrength() + atkStatus.getAddStrength()) * config.getStrengthValue();
            // 防御
            vitality = (entityStatus.getVitality() + entityStatus.getAddVitality()) * config.getVitalityValue();

            // 属性取得
            entityAttribute = entityStatus.getAttribute().toArray(new String[entityStatus.getAttribute().size()]);
        }

        // 倍率計算
        double mags = this.calcMags(attribute,entityAttribute);

        // ダメージ計算
        damage = this.calcDamage(damage,vitality,mags);

        // 体力計算
        double health = entityStatus.getHealth() - damage;

        // 体力が最大値を超えていないか
        if(health > (entityStatus.getMaxHealth() + entityStatus.getAddMaxHealth()) * config.getHealthValue()){
            // 超えている場合
            // 最大値にする
            health = (entityStatus.getMaxHealth() + entityStatus.getAddMaxHealth()) * config.getHealthValue();
        }

        // 体力を設定
        entityStatus.setHealth(health);

        // 攻撃を耐えたかどうか
        return health > 0;

    }

    /**
     * 魔法攻撃
     * @param atk 攻撃者
     * @param entity 対象
     * @param attribute 属性
     * @param damage ダメージ
     * @return 耐えれるかどうか
     */
    public boolean magicDamage(Entity atk,Entity entity,String attribute,double damage){
        // ステータスを持っていない物の場合、攻撃を無効にする
        if(!(status.hasStatus(atk) && status.hasStatus(entity))){
            atk.sendMessage(color("&c対象はステータスを持っていません"));//TODO デバック用のため後で削除する

            // ダメージを与える
            if(entity instanceof LivingEntity) ((LivingEntity) entity).damage(damage);
            return false;
        }

        // ステータス取得
        Status atkStatus = status.getStatus(atk);

        // ダメージを魔法用に変更する(計算を物理攻撃のもので行うため物理分の増加分を減らし、魔法攻撃力を加算する)
        damage += (atkStatus.getIntelligence() + atkStatus.getAddIntelligence()) - (atkStatus.getStrength() + atkStatus.getAddStrength()) * config.getStrengthValue();

        // 使用履歴の登録用文字列の作成(文字の並び,[UUIDs:属性s:ダメージ])
        String magic = atk.getUniqueId() + ":s:" + attribute + ":s:" + damage;


        // ダメージを与える
        if(entity instanceof LivingEntity) {
            // 魔法利用履歴に追加する
            magicUses.add(magic);
            ((LivingEntity) entity).damage(damage);
        }

        // 対象の現在のステータス取得 & 耐えたかどうか
        return status.getStatus(entity).getHealth() > 0;
    }


    /**
     * ダメージ計算
     * @param damage ダメージ
     * @param vit 防御力
     * @param mags 倍率
     * @return 計算結果
     */
    public double calcDamage(double damage,double vit,double mags){

        double answer = 0;  // 結果格納用

        // 計算を行う
        answer = new StringCalc(config.getCalcDamage())
                .replace("da",damage)   // 変数変換
                .replace("vit",vit)
                .replace("mags",mags)
                .replace("ra",Math.random())
                .build()    // 計算式変換
                .calc();    // 計算

        // 答えが負の値かつ倍率が負の値ではない時、結果を0にする
        if(answer < 0 && mags >= 0) answer = 0;

        // 結果を返す
        return answer;
    }


    /**
     * 魔法で攻撃されたかを判断する
     * @param atk 攻撃者
     * @param damage ダメージ
     * @return 魔法かどうか
     */
    public boolean checkMagicAtk(Entity atk,double damage){

        // 検索用の文字列作成
        String temp  = ":s:" + atk.getUniqueId().toString() + ":s:" + damage;

        // リストにあるか調べる
        for(String magic : this.magicUses){
            log(magic.substring(magic.indexOf(":s:"),magic.length()),temp); // TODO デバック用
            if(magic.substring(magic.indexOf(":s:"),magic.length()).equals(temp))
                return true;
        }
        return false;
    }


    public String getMagicAtkAttribute(Entity atk,double damage){
        // 検索用の文字列作成
        String temp  = ":s:" + atk.getUniqueId().toString() + ":s:" + damage;

        // リストにあるか調べる
        for(String magic : this.magicUses){
            log(magic.substring(magic.indexOf(":s:")),temp); // TODO デバック用

            // :s:が特定の数あるか調べる
            Pattern p = Pattern.compile("(:s:){4,}");
            Matcher m = p.matcher(magic);
            if(!magic.contains(":s:") || m.find()){
                // なかった場合

                // リストから削除
                magicUses.remove(magic);

                log(LogType.ERROR,"魔法の属性取得に失敗しました",
                                           "属性名に使用できない文字が含まれている可能性があります");

                return null;
            }
            if(magic.substring(magic.indexOf(":s:")).equals(temp))
                return magic.substring(0,magic.indexOf(":s:"));
        }
        return null;
    }


}
