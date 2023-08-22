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


    /**
     * 属性倍率を算出する
     * @param atk 攻撃の属性
     * @param defense 防御の属性
     * @return 倍率
     */
    public double calcMags(String atk,String... defense){

        // マネージャーを取得
        AttributeManager manager = AttributeManager.getManager();

        double answer = 0; // 結果格納用

        // 受ける側の属性を1ずつ確かめる
        for(String key : defense){
            // 弱点属性を調べる
            for(String mag : manager.getAttribute(key).getMags().keySet()){
                // 弱点が一致するか調べる
                if(atk.equals(mag)){
                    //  倍率を加算する
                    answer += manager.getAttribute(key).getMags().get(mag).getMag();
                }
            }
        }

        // 倍率を返す
        return answer;
    }









}
