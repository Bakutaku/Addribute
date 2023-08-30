package com.happyineo.addribute.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        // 空腹ゲージを自然に減らないようにするイベント
        event.setCancelled(true);
    }
}
