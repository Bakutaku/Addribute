package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.Attribute;
import com.happyineo.addribute.Beans.AttributeConfig;

import java.util.HashMap;
import java.util.Map;

public class AttributeManager {

    private static AttributeManager manager;
    private Map<String, Attribute> attributes;

    private AttributeManager() {
        manager = this;

        // 属性値取得
        this.attributes = DataManager.getManager().getAttributes().getAttributes();

        // default(属性なしの攻撃)の属性を作成(登録されていない時)
        if(!this.attributes.containsKey("default")) {
            // データ作成
            Attribute def = new Attribute();
            def.setName("通常攻撃");
            def.setColorR(255);
            def.setColorG(255);
            def.setColorB(255);
            Map<String,Attribute.weakness> defMap = new HashMap<>();
            Attribute.weakness weakness = new Attribute.weakness();
            weakness.setName("通常攻撃");
            weakness.setMag(1);
            defMap.put("default",weakness);
            def.setMags(defMap);

            // 設定
            this.attributes.put("default",def);
        }
        // nature(自然ダメージ(落下など))の属性を作成(登録されていなければ)
        if(!this.attributes.containsKey("nature")) {
            // データ作成
            Attribute nat = new Attribute();
            nat.setName("自然影響");
            nat.setColorR(120);
            nat.setColorG(205);
            nat.setColorB(170);
            Map<String,Attribute.weakness> defMap = new HashMap<>();
            Attribute.weakness weakness = new Attribute.weakness();
            weakness.setName("自然影響");
            weakness.setMag(1);
            defMap.put("nature",weakness);
            nat.setMags(defMap);

            // 設定
            this.attributes.put("nature",nat);
        }


    }

    /**
     * AttributeManagerのインスタンスを取得する
     * @return {@link AttributeManager}
     */
    public static AttributeManager getManager(){
        if(manager == null) manager = new AttributeManager();
        return manager;
    }


    /**
     * 属性情報を取得する
     * @param key 属性名
     * @return 属性情報
     */
    public Attribute getAttribute(String key){
        return this.attributes.get(key);
    }

}
