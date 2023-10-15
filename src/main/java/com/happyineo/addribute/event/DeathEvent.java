package com.happyineo.addribute.event;

import com.happyineo.addribute.Beans.Status;
import com.happyineo.addribute.Beans.StatusConfig;
import com.happyineo.addribute.manager.DataManager;
import com.happyineo.addribute.manager.StatusManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e){

        // マネージャーを取得
        StatusManager manager = StatusManager.getManager();
        StatusConfig config = DataManager.getManager().getStatusConfig();

        // ステータスを取得
        Status status = manager.getStatus(e.getEntity());

        // 最大体力を計算する
        double maxHealth = (status.getMaxHealth() + status.getAddMaxHealth()) * config.getHealthValue();

        // 最大魔力を計算する
        double maxMagic = (status.getMaxMagic() + status.getAddMaxMagic()) * config.getMagicValue();

        // 現在の体力と魔力に適応する
        status.setHealth(maxHealth);
        status.setMagic(maxMagic);
    }
}
