package com.happyineo.addribute.event;

import com.happyineo.addribute.manager.StatusManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    // プレイヤーがサーバーに参加したときのイベント
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){

        // マネージャーを取得
        StatusManager manager = StatusManager.getManager();

        // ステータスを設定
        manager.setStatusEntity(event.getPlayer());
    }
}
