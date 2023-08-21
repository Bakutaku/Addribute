package com.happyineo.addribute.event;

import com.happyineo.addribute.manager.StatusManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent implements Listener {

    private StatusManager statusManager = StatusManager.getManager();

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){

        // 空腹ダメージを無効にする
        if(event.getCause().equals(EntityDamageEvent.DamageCause.STARVATION)){
            // イベントをキャンセル
            event.setCancelled(true);

            // 処理を終了
            return;
        }

        // アイテムの場合処理を止める
        if(event.getEntity().getType().equals(EntityType.DROPPED_ITEM)) return;

        // ステータスがないエンティティは処理を行わない
        if(!statusManager.hasStatus(event.getEntity())) return;

        // エンティティからダメージを受けたか
        if(event instanceof EntityDamageByEntityEvent){
            // 受けた場合
            // イベントを変換
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;

            // 攻撃したエンティティを取得
            Entity atk = e.getDamager();

            // 攻撃者を取得する(飛び道具の場合)
            if (atk instanceof Arrow) {
                // 弓で攻撃された場合
                // 攻撃者を取得
                if (((Arrow) atk).getShooter() instanceof Entity) {
                    atk = (Entity) ((Arrow) atk).getShooter();
                }

            } else if (atk instanceof TNTPrimed) {
                // TNTで攻撃された場合
                // 攻撃者を取得(取得できれば)
                if (((TNTPrimed) atk).getSource() != null) atk = ((TNTPrimed) atk).getSource();

            } else if (atk instanceof Trident) {
                // トライデントで攻撃された場合
                // 攻撃者を取得
                atk = (Entity) ((Trident) atk).getShooter();
            } else if (atk instanceof ThrownPotion) {
                // ポーションで攻撃された場合
                // 攻撃者を取得
                atk = (Entity) ((ThrownPotion) atk).getShooter();
            }

        }



    }

}
