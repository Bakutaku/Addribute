package com.happyineo.addribute.command;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static com.happyineo.addribute.Addribute.getPlugin;

public class DebugAddItemAttributeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // プレイヤー以外を拒否する
        if(!(sender instanceof Player)) return false;

        // 引数がない場合
        if(args.length != 1) return false;

        // プレイヤーに変換
        Player player = (Player) sender;

        // OPを持っていないプレイヤーを拒否する
        if(!player.isOp()) return false;

        // プレイヤーが持っているアイテムを取得
        ItemStack item = player.getInventory().getItemInMainHand();

        // アイテム情報取得
        ItemMeta meta = item.getItemMeta();

        // アイテムを持ってなければ処理を停止
        if(meta == null) return false;

        // NBTのKEYを作成
        NamespacedKey key = new NamespacedKey(getPlugin(),"attribute");

        // アイテムのNBT情報を取得
        PersistentDataContainer data = meta.getPersistentDataContainer();

        // 属性情報を設定
        data.set(key, PersistentDataType.STRING,args[0]);

        // アイテム情報設定
        item.setItemMeta(meta);

        return true;
    }
}
