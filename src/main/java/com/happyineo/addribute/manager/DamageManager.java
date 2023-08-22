package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.StatusConfig;
import com.happyineo.addribute.StringCalc;
import org.bukkit.entity.Entity;

public class DamageManager {

    private static DamageManager manager;   // 自クラスのインスタンス格納用

    private StatusConfig config = DataManager.getManager().getStatusConfig();   // ステータスのコンフィグ

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
     */
    public void damage(Entity atk,Entity entity,String attribute,double damage){

    }

    /**
     * 魔法攻撃
     * @param atk 攻撃者
     * @param entity 対象
     * @param attribute 属性
     * @param damage ダメージ
     */
    public void magicDamage(Entity atk,Entity entity,String attribute,double damage){

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
                .build()    // 計算式変換
                .calc();    // 計算

        // 答えが負の値かつ倍率が負の値ではない時、結果を0にする
        if(answer < 0 && mags >= 0) answer = 0;

        // 結果を返す
        return answer;
    }




}
