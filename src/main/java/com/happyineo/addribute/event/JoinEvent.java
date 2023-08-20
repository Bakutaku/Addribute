package com.happyineo.addribute.event;

import com.happyineo.addribute.manager.StatusManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    // プレイヤーがログインしたときのイベント
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        // プレイヤーのステータスを設定する(getの際にプレイヤーのデータも自動で作成される)
        StatusManager.getManager().getStatus(event.getPlayer());

    }
}
