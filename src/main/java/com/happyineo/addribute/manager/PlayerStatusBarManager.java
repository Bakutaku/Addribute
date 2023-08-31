package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.Status;
import com.happyineo.addribute.Beans.StatusConfig;
import com.happyineo.addribute.StringCalc;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

import static com.happyineo.addribute.Addribute.getPlugin;
import static com.happyineo.addribute.Utils.color;
import static com.happyineo.addribute.Utils.log;

public class PlayerStatusBarManager {
    //##################################################################################################################
    private static PlayerStatusBarManager manager;  // インスタンス格納用

    private StatusManager statusManager = StatusManager.getManager();
    private StatusConfig config = DataManager.getManager().getStatusConfig();

    private DecimalFormat format = new DecimalFormat("####0.00");


    //##################################################################################################################
    // コンストラクタ関係
    private PlayerStatusBarManager(){
        manager = this;

        // アクションバーの更新(1秒に1回)
        new BukkitRunnable(){

            @Override
            public void run() {

                // 全てのプレイヤーを更新する
                Bukkit.getOnlinePlayers().forEach(player -> {
                    // 表示する文字準備
                    TextComponent disp = new TextComponent();

                    // ステータス取得
                    Status status = statusManager.getStatus(player);

                    // 表示フォーマット取得
                    String text = DataManager.getManager().getConfig().getStatusActionBar();

                    // 値を変換
                    text = text.replaceAll("%h", format.format(status.getHealth()))
                            .replaceAll("@h",format.format((status.getMaxHealth() + status.getAddMaxHealth()) * config.getHealthValue()))
                            .replaceAll("@ah",format.format(status.getAddMaxHealth()* config.getHealthValue()))
                            .replaceAll("%m",format.format(status.getMagic()))
                            .replaceAll("@m",format.format((status.getMaxMagic() + status.getAddMaxMagic()) * config.getMagicValue()))
                            .replaceAll("@am",format.format(status.getAddMaxMagic() * config.getMagicValue()));

                    // 文字設定
                    disp.setText(color(text));

                    // 表示
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,disp);
                });

            }
        }.runTaskTimer(getPlugin(),0L,DataManager.getManager().getConfig().getStatusActionBarTimer());

    }

    public static PlayerStatusBarManager getManager(){
        if(manager == null) manager = new PlayerStatusBarManager();
        return manager;
    }

    //##################################################################################################################
    // 外部から呼び出された使用するメソッド


    /**
     * ステータスバーの更新
     * @param player 対象
     */
    public void update(Player player){

        // ステータス取得
        Status status = statusManager.getStatus(player);


        // 体力を変更する##################################################################################################

        // 最大体力を取得
        double max = (status.getMaxHealth() + status.getAddMaxHealth()) * config.getHealthValue();

        // 現在の体力を取得
        double health = status.getHealth();

        // 計算(現在の割合を計算
        health = 20.0 * (health / max);

        // 適応(0以下ではないか確かめる(例外が出るため)
        if(health > 0 && health <= 20) player.setHealth(health);
        else if (health > 20) player.setHealth(20); // 上限値を超えた場合
        else player.setHealth(0.1); // 体力が0以下になった場合はダメージを受けて倒れるようにするため0.1にする(キルログのため)


        // MP(空腹度)を変更する############################################################################################

        // 最大MPを取得
        max = (status.getMaxMagic() + status.getAddMaxMagic()) * config.getMagicValue();

        // 現在のMPを取得
        double magic = status.getMagic();

        // 計算(現在の割合を計算
        magic = 19.0 * (magic / max);

        // 適応
        if(magic >= 0 && magic <= 19.0) player.setFoodLevel((int) Math.ceil(magic));
        else if(magic >= 19.0) player.setFoodLevel(19); // 限界値を超えていた場合
        else player.setFoodLevel(0);    // 0以下だった場合0にする(0以下になってる時点でバグってるが気にしない...w)

        // 経験値を変更する################################################################################################

        // 現在のレベルを取得
        int level = status.getLevel();

        // 次のレベルになるのに必要な経験値を求める
        long calc = (long) new StringCalc(config.getLevelUpNeedExpPoint())
                .replace("lv",level)
                .build()
                .calc();

        // 経験値の割合を取得
        float exp = (float) status.getExp() / calc;

        // 適応する
        if(exp <= 1 && exp >= 0)player.setExp(exp);
        else if(exp > 1) player.setExp(1);  // 限界値を超えた場合用
        else player.setExp(0);  // 限界値以下の場合用

        // レベル適応
        player.setLevel(level);

    }
}
