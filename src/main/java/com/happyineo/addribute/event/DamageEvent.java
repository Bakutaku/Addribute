package com.happyineo.addribute.event;

import com.happyineo.addribute.manager.DamageManager;
import com.happyineo.addribute.manager.DataManager;
import com.happyineo.addribute.manager.PlayerStatusBarManager;
import com.happyineo.addribute.manager.StatusManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import static com.happyineo.addribute.Addribute.getPlugin;
import static com.happyineo.addribute.Utils.log;

public class DamageEvent implements Listener {

    private final StatusManager statusManager = StatusManager.getManager();
    private final DamageManager damageManager = DamageManager.getManager();

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

        // 生きていないエンティティは処理を行わない
        if(!(event.getEntity() instanceof LivingEntity)) return;


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
                if (((Arrow) atk).getShooter() instanceof Entity) atk = (Entity) ((Arrow) atk).getShooter();

            } else if (atk instanceof TNTPrimed) {
                // TNTで攻撃された場合
                // 攻撃者を取得(取得できれば)
                if (((TNTPrimed) atk).getSource() != null) atk = ((TNTPrimed) atk).getSource();

            } else if (atk instanceof Trident) {
                // トライデントで攻撃された場合
                // 攻撃者を取得
                if(((Trident) atk).getShooter() != null) atk = (Entity) ((Trident) atk).getShooter();
            } else if (atk instanceof ThrownPotion) {
                // ポーションで攻撃された場合
                // 攻撃者を取得
                if(((ThrownPotion) atk).getShooter() != null) atk = (Entity) ((ThrownPotion) atk).getShooter();
            }

            // ダメージ取得
            double damage = e.getDamage();

            // 攻撃に使用する属性
            String attribute = "default";

            // 攻撃が魔法かどうかを調べる
            if(damageManager.checkMagicAtk(atk,damage)){
                // 魔法の場合
                // 属性取得
                attribute = damageManager.getMagicAtkAttribute(atk,damage);
            }else{
                // 通常攻撃の場合
                // 武器の攻撃力を取得
                ItemMeta item = null;
                if(atk instanceof LivingEntity && ((LivingEntity) atk).getEquipment() != null) item = ((LivingEntity) atk).getEquipment().getItemInMainHand().getItemMeta();

                // アイテムを取得できているか
                if(item != null){
                    // できた場合
                    // 検索用のkeyを作成
                    NamespacedKey atkKey = new NamespacedKey(getPlugin(),"damage");
                    NamespacedKey attKey = new NamespacedKey(getPlugin(),"attribute");

                    // アイテムから情報を取得
                    PersistentDataContainer data = item.getPersistentDataContainer();

                    // 攻撃力を取得(設定されている場合のみ)
                    if(data.has(atkKey, PersistentDataType.DOUBLE)){
                        damage = data.get(atkKey,PersistentDataType.DOUBLE);
                    }

                    // 攻撃属性を取得(設定されている場合のみ)
                    if(data.has(attKey,PersistentDataType.STRING)){
                        attribute = data.get(attKey,PersistentDataType.STRING);
                    }
                }
            }

            // ダメージを反映する & 耐えれた場合ダメージを０にする
            if(damageManager.damage(atk,e.getEntity(),attribute,damage)) event.setDamage(0);
            else ((LivingEntity) event.getEntity()).setHealth(0.1);

            // 無敵時間を無効化するかどうか
            if(DataManager.getManager().getConfig().isDisableInvincibleTime()){
                // 無効化する場合
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        ((LivingEntity) e.getEntity()).setNoDamageTicks(0);
                    }
                }.runTaskLater(getPlugin(), 0);
            }

        }else {
            // エンティティ以外からのダメージ

            // ダメージを反映 & 耐えれた場合ダメージを0にする
            if(damageManager.damage(event.getEntity(),event.getEntity(),"nature",event.getDamage())) event.setDamage(0);
            else ((LivingEntity) event.getEntity()).setHealth(0.1);
        }

        // プレイヤーだったらステータスバーを変更する
        if(event.getEntity() instanceof Player) PlayerStatusBarManager.getManager().update((Player) event.getEntity());

    }

}
