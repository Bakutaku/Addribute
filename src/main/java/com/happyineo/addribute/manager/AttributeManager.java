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

        // defaultの属性を作成(登録されていない時)
        if(!this.attributes.containsKey("default")) {
            // データ作成
            Attribute def = new Attribute();
            def.setName("物理攻撃");
            Map<String,Attribute.weakness> defMap = new HashMap<>();
            def.setMags(defMap);

            // 設定
            this.attributes.put("default",def);
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
