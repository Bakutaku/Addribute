package com.happyineo.addribute.event;

import com.happyineo.addribute.manager.DataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    // プレイヤーがログアウトする時のイベント
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){

        // マネージャーを取得
        DataManager manager = DataManager.getManager();

        // ステータスを保存
        manager.save(event.getPlayer());

    }

}
