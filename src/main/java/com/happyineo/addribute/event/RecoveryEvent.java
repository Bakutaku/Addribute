package com.happyineo.addribute.event;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class RecoveryEvent implements Listener {

    @EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
        // 自然回復を無効にする
        if (event.getEntityType() == EntityType.PLAYER) {
            event.setCancelled(true);
        }
    }
}
