package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.Status;
import com.happyineo.addribute.Beans.StatusConfig;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class StatusManager {

    private static StatusManager manager;   // 自クラスのインスタンス格納用

    private DataManager dataManager;    // データ関係用

    /**
     * コンストラクタ
     */
    private StatusManager() {
        manager = this;

        //  データベースに切り替える場合、ここを変更することで切り替えれるようにする
        dataManager = DataManager.getManager();

    }


    /**
     * エンティティのステータスを取得する
     * @param entity 対象
     * @return ステータス
     */
    public Status getStatus(Entity entity){
        if(entity == null) return null;
        return dataManager.getStatus(entity);
    }

    /**
     * ステータスを設定する
     * @param status ステータス情報
     */
    public void setStatus(Entity entity, Status status){
        dataManager.setStatus(entity,status);
    }


    /**
     * ステータスを登録または初期化する
     * @param entity 対象
     */
    public void createStatusEntity(Entity entity) {
        // コンフィグ取得
        StatusConfig statusConfig = dataManager.getStatusConfig();

        // 初期ステータス作成
        Status data = new Status();
        data.setName(statusConfig.getNameDefault());
        data.setMaxHealth(statusConfig.getMaxHealthDefault());
        data.setAddMaxHealth(statusConfig.getAddMaxHealthDefault());
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
        if (!(entity instanceof Player) && entity instanceof LivingEntity) {
            // プレイヤー以外の場合

            // 生きているエンティティに変換
            LivingEntity livingEntity = (LivingEntity) entity;

            // 一部の初期値変更(体力などをエンティティの体力にあわせるため)
            // 名前の設定を取得
            String name = statusConfig.getNameDefault();

            // 名前がない場合
            if (entity.getName().equals("")) name = name.replace("%n", entity.getType().toString());
                // 名前がある場合
            else name = name.replace("%n", entity.getName());

            // 変更する
            data.setName(name); // 名前
            data.setMaxHealth((int) livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());    // 最大体力
            data.setHealth(livingEntity.getHealth());   // 体力
        }else if(!(entity instanceof LivingEntity)){
            // 生きていないエンティティの場合

            // 一部内容変更
            // 名前の設定を取得
            String name = statusConfig.getNameDefault();

            // 名前がない場合
            if (entity.getName().equals("")) name = name.replace("%n", entity.getType().toString());
                // 名前がある場合
            else name = name.replace("%n", entity.getName());
        }

        // 登録
        this.setStatus(entity,data);
    }

    /**
     * ステータスを持っているか調べる
     * @param entity 対象
     * @return ステータスを持っているかいないか
     */
    public boolean hasStatus(Entity entity){
        return this.getStatus(entity) != null;
    }

    /**
     * StatusManagerのインスタンスを取得する
     * @return {@link StatusManager}
     */
    public static StatusManager getManager(){
        if(manager == null) manager = new StatusManager();
        return manager;
    }

}
