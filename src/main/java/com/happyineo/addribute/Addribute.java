package com.happyineo.addribute;

import com.happyineo.addribute.Beans.Config;
import com.happyineo.addribute.command.DebugAddItemAttributeCommand;
import com.happyineo.addribute.command.DebugAddItemDamageCommand;
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

        // コンフィグ取得
        Config config = DataManager.getManager().getConfig();

        // イベント登録###################################################################################################
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this); // ログイン時のイベント(データベースの場合不要)
        Bukkit.getPluginManager().registerEvents(new QuitEvent(),this); // ログアウト時のイベント
        Bukkit.getPluginManager().registerEvents(new DamageEvent(),this);   // ダメージを受けたときのイベント
        Bukkit.getPluginManager().registerEvents(new RecoveryEvent(),this); // 回復したときのイベント(自然回復を無効にするため)
        Bukkit.getPluginManager().registerEvents(new FoodEvent(),this); // 空腹ゲージが変更された時のイベント(空腹ゲージが勝手に変わらないようにするため)
        Bukkit.getPluginManager().registerEvents(new DeathEvent(),this);    // プレイヤーが死亡したときのイベント
        //##############################################################################################################
        // 特殊イベント登録################################################################################################
        // コンフィグの設定値によって登録するか変わるイベント
        if(config.isNatureStatusHasEntity()) Bukkit.getPluginManager().registerEvents(new SpawnEvent(),this);    // エンティティを体力管理対象に自動追加する時のイベント
        //##############################################################################################################

        // コマンド登録###################################################################################################
        getCommand("debugItemAttribute").setExecutor(new DebugAddItemAttributeCommand()); // デバック用    アイテムに属性を設定する
        getCommand("debugItemDamage").setExecutor(new DebugAddItemDamageCommand());    // デバック用    アイテムに攻撃力を設定する
        //##############################################################################################################
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
