package com.happyineo.addribute.event;

import com.happyineo.addribute.manager.StatusManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.happyineo.addribute.Utils.color;
import static com.happyineo.addribute.Utils.getColorCode;

public class JoinEvent implements Listener {

    // プレイヤーがログインしたときのイベント
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        // プレイヤーのステータスを設定する(getの際にプレイヤーのデータも自動で作成される)
        StatusManager.getManager().getStatus(event.getPlayer());

        event.getPlayer().sendMessage(color(getColorCode(220,0,0)+"test"));


    }
}
