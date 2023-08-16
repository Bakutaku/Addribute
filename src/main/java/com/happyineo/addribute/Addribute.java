package com.happyineo.addribute;

import org.bukkit.plugin.java.JavaPlugin;

public final class Addribute extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // 起動時の処理
        plugin = this;

        super.onEnable();
    }

    @Override
    public void onDisable() {
        // 終了時の処理

        super.onDisable();
    }

    public static JavaPlugin getPlugin(){return plugin;}
}
