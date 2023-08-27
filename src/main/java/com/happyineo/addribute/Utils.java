package com.happyineo.addribute;

import com.happyineo.addribute.type.LogType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Utils {

    private static Logger logger = Addribute.getPlugin().getLogger();   // ログ用

    /**
     * ログ表示
     * @param message メッセージ
     */
    public static void log(String... message){
        // ログ表示
        log(LogType.INFO,message);
    }

    /**
     * ログ表示
     * @param logType ログの種類
     * @param message メッセージ
     */
    public static void log(LogType logType,String... message){
        // ログメッセージ表示
        for (String str : message) {
            // ログ表示
            switch (logType) {
                case INFO:
                    logger.info(str);
                    break;

                case ERROR:
                    logger.warning("ERROR:"+str);
                    break;

                case WARNING:
                    logger.warning("IMPORTANT_ERROR:"+str);
                    break;

                case CRITICAL:
                    logger.severe("CRITICAL_ERROR:"+str);
                    break;
            }
        }
    }

    /**
     * RGBで色を付ける
     * @param r red
     * @param g green
     * @param b blue
     * @return カラーコード
     */
    public static String getColorCode(int r,int g,int b){
        // RGB値を変換しカラーコードに変換し文字列にし表示する際の色の形式にする
        return getColorCode(String.format("%02x%02x%02x", r,g,b));
    }


    /**
     * RGBで色を付ける
     * @param color カラーコード
     * @return 表示に使用するカラーコード
     */
    public static String getColorCode(String color){
        // コードが間違っていたらなしにする
        if(ChatColor.getByChar(color) == null) return "";
        // 変換しその値を返す
        return ChatColor.getByChar(color).toString();
    }

    /**
     * 文字に色を付けるメソッド
     * @param str 色を付ける文字列
     * @return 色が付いた文字列
     */
    public static String color(String str){
        return ChatColor.translateAlternateColorCodes('&',str);
    }

    /**
     * 色がついた文字を元の文字列に戻すメソッド
     * @param str 色が付いた文字列
     * @return 元の文字列
     */
    public static String deColor(String str){
        return ChatColor.stripColor(color(str));
    }

    /**
     * プレイヤーにメッセージを表示する
     * @param player 対象
     * @param message メッセージ
     */
    public static void msgPlayer(Player player, String... message){
        for (String str : message) {
            player.sendMessage(str);
        }
    }

    /**
     * 負の値を含めた乱数を生成する
     * @param size 最大値
     * @return 乱数
     */
    public static double getRandomMinus(double size){
        // 乱数生成
        double random = Math.random() * (size + 0.1);

        // 最大値を超えている場合、最大値にする(小数点を含む乱数のため)
        if(random > size) random = size;

        // 50%の確率で値をマイナスにする
        if(Math.random() > 0.5) random *= -1;

        // 値を返す
        return random;
    }

    /**
     * 乱数を生成する
     * @param size 最大値
     * @return 乱数
     */
    public static double getRandom(double size){
        // 乱数生成
        double random = Math.random() * (size + 0.1);

        // 最大値を超えている場合、最大値にする(小数点を含む乱数のため)
        if(random > size) random = size;

        // 値を返す
        return random;
    }
}
