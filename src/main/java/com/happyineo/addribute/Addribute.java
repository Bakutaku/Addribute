package com.happyineo.addribute;

import com.happyineo.addribute.event.*;
import com.happyineo.addribute.manager.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static com.happyineo.addribute.Utils.*;

public final class Addribute extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // 起動時の処理
        plugin = this;

        // セットアップ
        new Setup().setup();

        // イベント登録
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this); // ログイン時のイベント(データベースの場合不要)
        Bukkit.getPluginManager().registerEvents(new QuitEvent(),this); // ログアウト時のイベント
        Bukkit.getPluginManager().registerEvents(new DamageEvent(),this);   // ダメージを受けたときのイベント
        Bukkit.getPluginManager().registerEvents(new RecoveryEvent(),this); // 回復したときのイベント(自然回復を無効にするため)
        Bukkit.getPluginManager().registerEvents(new FoodEvent(),this); // 空腹ゲージが変更された時のイベント(空腹ゲージが勝手に変わらないようにするため)

        super.onEnable();
    }

    @Override
    public void onDisable() {
        // 終了時の処理

        // 表示しているダメージを全て削除
        RPGDamage.getManager().allDeleteDisp();

        // データを保存
        DataManager.getManager().save();


        super.onDisable();
    }

    public static JavaPlugin getPlugin(){return plugin;}
}
