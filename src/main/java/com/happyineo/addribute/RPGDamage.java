package com.happyineo.addribute;

import com.happyineo.addribute.Beans.Attribute;
import com.happyineo.addribute.Beans.Config;
import com.happyineo.addribute.manager.AttributeManager;
import com.happyineo.addribute.manager.DataManager;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;

import static com.happyineo.addribute.Addribute.getPlugin;
import static com.happyineo.addribute.Utils.*;

public class RPGDamage {

    private static RPGDamage manager;   // インスタンス格納用
    private final Config config = DataManager.getManager().getConfig(); // コンフィグ
    private final DecimalFormat format;
    private final Map<ArmorStand,Integer> indicators = new HashMap<>();   // ダメージ表示用のアーマースタンド管理用

    private RPGDamage(){
        manager = this;

        // フォーマット設定
        // 表示フォーマット
        format = new DecimalFormat(config.getDisplayDamageFormat().replace("?", "#"));

        // 上昇値設定
        double positionUp = config.getDisplayDamagePositionMaxY() / (config.getDisplayDamageTime() / 2.0);

        // 表示しているダメージの管理(時間が経過したら削除するなど)
        new BukkitRunnable(){
            final Set<ArmorStand> stands = indicators.keySet(); // 現在表示しているアーマースタンド
            final List<ArmorStand> removal = new ArrayList<>(); // 削除するアーマースタンド格納用

            @Override
            public void run() {
                for (ArmorStand key : stands) {
                    // 削除対象か調べる
                    if(indicators.get(key) == 0){
                        // 削除する場合
                        removal.add(key);
                        continue;
                    }

                    // 移動
                    if(indicators.get(key) > config.getDisplayDamageTime() / 2 ) key.teleport(key.getLocation().clone().add(0,positionUp,0));   // 上昇する場合
                    else key.teleport(key.getLocation().clone().add(0,positionUp * -1,0));  // 下降する場合

                    // 現在の数値を1減らす
                    indicators.put(key,indicators.get(key) - 1);
                }
                // killする
                removal.forEach(Entity::remove);
                removal.forEach(stands::remove);
            }
        // 1tickに１回処理を行う
        }.runTaskTimer(getPlugin(),0L,1L);


    }

    /**
     * RPGDamageのインスタンスを取得
     * @return {@link RPGDamage}
     */
    public static RPGDamage getManager(){
        if(manager == null) manager = new RPGDamage();
        return manager;
    }


    /**
     * ダメージを表示する
     * @param entity 対象
     * @param damage ダメージ
     * @param attribute 属性
     */
    public void disp(Entity entity,double damage,String attribute){

        // 表示する文字を作成
        String message = format.format(damage);

        // マネージャーを取得
        AttributeManager manager = AttributeManager.getManager();

        // 属性情報取得
        Attribute data = manager.getAttribute(attribute);

        // 色を付ける
        message = getColorCode(data.getColorR(),data.getColorG(),data.getColorB()) + message;

        // マイナスだった場合 色を変える
        if(damage < 0) message = "&a" + deColor(message);

        // 表示する
        this.disp(entity,message);

    }

    /**
     * ダメージを表示する
     * @param entity 対象
     * @param damage ダメージ
     */
    public void disp(Entity entity,double damage){
        // 表示する文字を作成する
        String message = "&c"+format.format(damage);

        // マイナスだった場合 色を変える
        if(damage < 0) message = "&a" + deColor(message);

        // 表示する
        this.disp(entity,message);

    }

    /**
     * ダメージを表示する
     * @param entity 対象
     * @param message 表示内容
     */
    private void disp(Entity entity,String message){
        // 最大値を超えていないか
        if(config.getDisplayDamageMax() != -1 && config.getDisplayDamageMax() < indicators.size()){
            // 超えている場合 処理を止める
            log("ダメージ表示が最大値に達しました");
            return;
        }

        // 文字に色を付ける
        message = color(message);

        // 表示する座標を取得
        Location location = entity.getLocation().clone().add(
                getRandomMinus(config.getDisplayDamagePositionX()),
                getRandom(config.getDisplayDamagePositionY()) + 1.5,
                getRandomMinus(config.getDisplayDamagePositionZ())
        );

        // 文字表示用のアーマースタンドを作成する
        String finalMessage = message;
        entity.getWorld().spawn(location,ArmorStand.class, armorStand -> {
            armorStand.setMarker(true); // マーカー
            armorStand.setVisible(false);   // 透明
            armorStand.setSmall(true);  // 小さく
            armorStand.setGravity(true);    // 重力
            armorStand.setCustomNameVisible(true);  // 名前表示
            armorStand.setCustomName(finalMessage); // 表示名
            indicators.put(armorStand,config.getDisplayDamageTime());   // リストに追加
        });
    }

    /**
     * 現在表示しているダメージを全て削除する
     */
    public void allDeleteDisp(){
        this.indicators.keySet().forEach(Entity::remove);
    }

}
