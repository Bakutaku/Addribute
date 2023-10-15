package com.happyineo.addribute.event;

import com.happyineo.addribute.Beans.Config;
import com.happyineo.addribute.manager.DataManager;
import com.happyineo.addribute.manager.StatusManager;
import com.happyineo.addribute.type.LogType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.List;

import static com.happyineo.addribute.Utils.log;


public class SpawnEvent implements Listener {

    private final List<EntityType> entityList;    // 対象外にするエンティティ

    private final StatusManager manager = StatusManager.getManager();   // ステータスマネージャー


    public SpawnEvent(){

        // コンフィグ取得
        Config config = DataManager.getManager().getConfig();

        // 初期化
        entityList = new ArrayList<>();

        // 無効に設定されているエンティティを調べる
        for(String entity : config.getStatusNotEntity()){
            try{
                // 設定に追加する(設定できるか調べる)
                entityList.add(EntityType.valueOf(entity));
            }catch (IllegalArgumentException e){
                log(LogType.ERROR,entity+"の無効化に失敗しました","名前が間違っている可能性があるため再度確認してください");
            }
        }
    }

    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent event){

        // 対象エンティティ取得
        Entity entity = event.getEntity();

        // 対象外のエンティティだったら処理を終わる
        if(entityList.contains(event.getEntityType())) return;

        // エンティティがステータスを持っていないか調べる
        if(!manager.hasStatus(entity)){
            // ステータス設定
            manager.createStatusEntity(entity);
        }
    }
}
