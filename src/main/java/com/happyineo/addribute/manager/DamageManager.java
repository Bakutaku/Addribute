package com.happyineo.addribute.manager;

public class DamageManager {

    private static DamageManager manager;   // 自クラスのインスタンス格納用

    private DamageManager(){
        manager = this;
    }

    /**
     * DamageManagerのインスタンスを取得する
     * @return {@link DamageManager}
     */
    public static DamageManager getManager(){
        if (manager == null) manager = new DamageManager();
        return manager;
    }
















}
