package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.Status;
import com.happyineo.addribute.Beans.StatusConfig;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;



public class StatusManager {

    private static StatusManager manager;

    private DataManager dataManager;

    private StatusManager() {
        manager = this;

        // マネージャーを取得
        dataManager = DataManager.getManager();

    }

    /**
     * ステータスを変更する
     * @param entity 対象
     * @param status ステータス
     */
    public void setStatusEntity(Entity entity, Status status){
        // ステータス変更 & 登録
        dataManager.setStatus(entity,status);
    }

    /**
     * ステータスが登録されているか調べる
     * @return あるかないか
     */
    public boolean hasStatusEntity(){
        return false;//TODO ステータスが登録済みまたはファイルに保存されいるかを調べ結果を返すメソッドを作成する
    }


    /**
     * ステータスを登録または初期化する
     * @param entity 対象
     */
    public void setStatusEntity(Entity entity){

        LivingEntity livingEntity;  // データ型返還後の格納用

        // データ型を変換する(生きているエンティティか調べる)
        if(entity instanceof LivingEntity) livingEntity = (LivingEntity) entity;
        // 出来なかった場合
        else return;

        // コンフィグ取得
        StatusConfig statusConfig = dataManager.getStatusConfig();

        // 初期ステータス作成
        Status data = new Status();
        data.setName(statusConfig.getNameDefault());
        data.setMaxHealth(statusConfig.getMaxHealthDefault());
        data.setMaxHealth(statusConfig.getAddMaxHealthDefault());
        data.setHealth(statusConfig.getHealthDefault());
        data.setRecoveryHealth(statusConfig.getRecoveryHealthDefault());
        data.setMaxMagic(statusConfig.getMaxMagicDefault());
        data.setAddMaxMagic(statusConfig.getAddMaxMagicDefault());
        data.setMagic(statusConfig.getMagicDefault());
        data.setRecoveryMagic(statusConfig.getRecoveryMagicDefault());
        data.setStrength(statusConfig.getStrengthDefault());
        data.setAddStrength(statusConfig.getAddStrengthDefault());
        data.setIntelligence(statusConfig.getIntelligenceDefault());
        data.setAddIntelligence(statusConfig.getAddIntelligenceDefault());
        data.setVitality(statusConfig.getVitalityDefault());
        data.setAddVitality(statusConfig.getAddVitalityDefault());
        data.setAgility(statusConfig.getAgilityDefault());
        data.setAddAgility(statusConfig.getAddAgilityDefault());
        data.setExp(statusConfig.getExpDefault());
        data.setDropExp(statusConfig.getDropExpDefault());
        data.setLevel(statusConfig.getLevelDefault());
        data.setStatusPoint(statusConfig.getStatusPointDefault());

        // プレイヤー以外かどうか
        if(!(entity instanceof Player)){
            // プレイヤー以外の場合

            // 一部の初期値変更(体力などをエンティティの体力にあわせるため)
            // 名前の設定を取得
            String name = statusConfig.getNameDefault();

            // 名前がない場合
            if(entity.getName().equals("")) name = name.replace("%n",entity.getType().toString());
            // 名前がある場合
            else name = name.replace("%n",entity.getName());

            // 変更する
            data.setName(name); // 名前
            data.setMaxHealth((int) livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());    // 最大体力
            data.setHealth(livingEntity.getHealth());   // 体力
        }

        // ステータスを登録する
        this.setStatusEntity(entity,data);
    }


    public static StatusManager getManager(){
        if(manager == null) manager = new StatusManager();
        return manager;
    }
}
