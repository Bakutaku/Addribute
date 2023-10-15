package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.*;
import com.happyineo.addribute.SetupFiles;
import com.happyineo.addribute.type.LogType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.happyineo.addribute.Utils.log;

public class DataManager {
    private static DataManager manager; //　インスタンス格納用

    private Config config;  // プラグインのコンフィグ
    private StatusConfig statusConfig;  // ステータスのコンフィグ
    private AttributeConfig attributes; // 属性情報
    private EntityStatus entityStatus;  // エンティティのステータス情報
    private Map<UUID, Status> playerStatus = new HashMap<>(); // プレイヤーのステータス情報


    private DataManager() {

        try {
            log("コンフィグロード中");

            YamlManager yamlManager = YamlManager.getManager();
            JsonManager jsonManager = JsonManager.getManager();

            // コンフィグなどの読み込み
            config = yamlManager.loadYamlData("Config.yml", Config.class);  // コンフィグ
            statusConfig = yamlManager.loadYamlData("status/StatusConfig.yml", StatusConfig.class); // ステータスのコンフィグ
            attributes = yamlManager.loadYamlData("attribute/AttributeConfig.yml", AttributeConfig.class);  // 属性情報のコンフィグ
            entityStatus = jsonManager.loadJsonData("status/data/StatusData.json", EntityStatus.class); // エンティティのステータス情報

            // 取得できたかのチェック
            if(config == null) config = new Config();
            if(statusConfig == null) statusConfig = new StatusConfig();
            if(attributes == null) attributes = new AttributeConfig();
            if(entityStatus == null) entityStatus = new EntityStatus();

            // プレイヤーのステータス情報を取得
            for (Player player : Bukkit.getOnlinePlayers()) {

                // ステータス取得
                Status data = this.getFilePlayerStatus(player);

                // 取得に成功した場合は登録する
                if(data != null) this.playerStatus.put(player.getUniqueId(),data);
            }

            log("コンフィグのロード完了しました");

        } catch (IOException e) {
            log(LogType.WARNING,"コンフィグの読み込みに失敗しました");
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            log(LogType.CRITICAL,"データの生成に失敗しました(システムに問題がある可能性があるため開発者に報告をしてくれるとありがたいです)",e.getMessage());
        }
    }


    public static DataManager getManager(){
        if (manager == null) manager = new DataManager();
        return manager;
    }


    private Status getFilePlayerStatus(Player player) throws IOException {

        // マネージャーを取得
        JsonManager jsonManager = JsonManager.getManager();

        // ファイル取得
        File file = new File(SetupFiles.current +"status/data/"+player.getUniqueId()+".json");

        // データ格納用
        Status data;

        // ファイルがあるかどうか
        if(file.exists()){
            // ある場合
            data = jsonManager.loadJsonData("status/data/"+player.getUniqueId()+".json", Status.class);

            // 取得に失敗した場合
            if(data == null){
                log(LogType.ERROR,player.getName()+"さんのステータス情報の取得に失敗しました。",
                        "ファイルが破損している可能性があります、ファイルを削除することで初期化することができます");
            }

        }else {
            log("ファイル生成中");

            // ファイル作成
            file.createNewFile();

            // ステータス作成
            data = new Status();

            // 初期値設定
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

            // ファイルに保存
            jsonManager.saveJsonData("status/data/"+player.getUniqueId()+".json", data);
        }

        // 値を返す
        return data;
    }

    public Status getStatus(Entity entity){
        // ステータス取得
        Status data = this.entityStatus.getEntityStatus().get(entity.getUniqueId());

        // プレイヤーの場合のステータス取得
        if(entity instanceof Player) data = this.playerStatus.get(entity.getUniqueId());

        // 上の2つで取得できなかった場合プレイヤーのファイルからステータスを取得する
        if(entity instanceof Player && data == null) {
            try {
                // ファイルからステータスを取得
                data = this.getFilePlayerStatus((Player) entity);

                // ステータスを登録する(登録されておらずファイルが生成されている(もしくは生成した)ため)
                this.playerStatus.put(entity.getUniqueId(),data);

            } catch (IOException ignored) {;}
        }

        // 値を返す
        return data;
    }

    /**
     * ステータスの登録(更新)
     * @param entity
     * @param status
     */
    public void setStatus(Entity entity,Status status){
        // プレイヤーかどうか調べる
        if(entity instanceof Player && this.playerStatus.containsKey(entity.getUniqueId())){
            // ステータス更新
            this.playerStatus.put(entity.getUniqueId(),status);

        }else if(this.entityStatus.getEntityStatus().containsKey(entity.getUniqueId())){
            // ステータス更新
            this.entityStatus.getEntityStatus().put(entity.getUniqueId(),status);
        }else{
            // ステータス登録
            this.entityStatus.getEntityStatus().put(entity.getUniqueId(),status);
        }
    }



    /**
     * dataを保存する
     */
    public void save() {
        // マネージャーを取得
        JsonManager manager = JsonManager.getManager();

        try {
            manager.saveJsonData("status/data/StatusData.json",this.entityStatus);

        } catch (IOException e) {
            log(LogType.ERROR,"エンティティステータスの保存に失敗しました");
        }

        this.playerStatus.forEach((uuid, status) -> {
            try {
                manager.saveJsonData("status/data/"+uuid.toString()+".json",status);
            } catch (IOException e) {
                log(LogType.ERROR,uuid.toString()+"の保存に失敗しました");
            }
        });
    }

    /**
     * データを保存する
     * @param player 対象
     */
    public void save(Player player){
        // マネージャーを取得
        JsonManager manager = JsonManager.getManager();

        // ファイルに保存
        try {
            manager.saveJsonData("status/data/"+player.getUniqueId()+".json",this.playerStatus.get(player.getUniqueId()));
        } catch (IOException e) {
            log(LogType.ERROR,player.getName()+"さんのステータスの保存に失敗しました");
        }

        // データをメモリから削除
        this.playerStatus.remove(player.getUniqueId());

    }




    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public StatusConfig getStatusConfig() {
        return statusConfig;
    }

    public void setStatusConfig(StatusConfig statusConfig) {
        this.statusConfig = statusConfig;
    }

    public AttributeConfig getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeConfig attributes) {
        this.attributes = attributes;
    }
}
