package com.happyineo.addribute;

import com.happyineo.addribute.event.DamageEvent;
import com.happyineo.addribute.event.JoinEvent;
import com.happyineo.addribute.event.QuitEvent;
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

        super.onEnable();
    }

    @Override
    public void onDisable() {
        // 終了時の処理

        // データを保存
        DataManager.getManager().save();

        super.onDisable();
    }

    public static JavaPlugin getPlugin(){return plugin;}
}
