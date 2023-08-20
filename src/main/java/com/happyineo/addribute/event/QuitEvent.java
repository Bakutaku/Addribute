package com.happyineo.addribute.event;

import com.happyineo.addribute.manager.DataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    // プレイヤーがログアウトした時のイベント
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){

        // プレイヤーのデータを保存する
        DataManager.getManager().save(event.getPlayer());

    }
}
