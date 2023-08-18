package com.happyineo.addribute;

import com.happyineo.addribute.Beans.Config;
import com.happyineo.addribute.event.JoinEvent;
import com.happyineo.addribute.event.QuitEvent;
import com.happyineo.addribute.manager.DataManager;
import com.happyineo.addribute.type.LogType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.happyineo.addribute.Utils.log;

public final class Addribute extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // 起動時の処理
        plugin = this;

        // セットアップ
        new Setup().setup();

        // イベント登録
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(),this);

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
