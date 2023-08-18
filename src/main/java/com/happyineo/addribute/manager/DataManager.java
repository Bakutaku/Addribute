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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
                Status data = this.getPlayerStatus(player);

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


    public Status getPlayerStatus(Player player) throws IOException {

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

        return data;
    }

    public void setStatus(Entity entity,Status status){
        // プレイヤーかどうか調べる
        if(entity instanceof Player){

            // ステータス更新
            this.playerStatus.put(entity.getUniqueId(),status);

        }else if(this.entityStatus.getEntityStatus().containsKey(entity.getUniqueId())){
            // ステータス更新
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
     * プレイヤーのステータスを保存する
     * @param player 対象
     */
    public void save(Player player){
        // マネージャーを取得
        JsonManager manager = JsonManager.getManager();

        // ステータスを保存する
        try {
            manager.saveJsonData("status/data/"+player.getUniqueId().toString()+".json",    // 保存先
                    this.getStatus(player));    // データ

            // ステータスの登録を取り消す
            playerStatus.remove(player.getUniqueId());

        } catch (IOException e) {
            log(LogType.ERROR,player.getName()+"さんのステータスの保存に失敗しました");
        }
    }

    /**
     * ステータスが登録されているか調べる
     * @param entity
     * @return
     */
    public boolean hasStatus(Entity entity){

        boolean flg = false;    // 結果格納用

        // エンティティのからステータスがあるか調べる
        flg = this.entityStatus.getEntityStatus().containsKey(entity.getUniqueId());

        // プレイヤーの場合、プレイヤーのからステータスがあるか調べる
        if(entity instanceof Player) flg = this.playerStatus.containsKey(entity.getUniqueId());
        //TODO

        // 結果を返す
        return flg;
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
