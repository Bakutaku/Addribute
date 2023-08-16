package com.happyineo.addribute;

import com.happyineo.addribute.type.LogType;
import org.bukkit.plugin.java.JavaPlugin;

import static com.happyineo.addribute.Utils.log;

public final class Addribute extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // 起動時の処理
        plugin = this;

        log("セットアップ中");

        // セットアップ
        new Setup().setup();

        log("セットアップが完了しました");

        super.onEnable();
    }

    @Override
    public void onDisable() {
        // 終了時の処理

        super.onDisable();
    }

    public static JavaPlugin getPlugin(){return plugin;}
}
