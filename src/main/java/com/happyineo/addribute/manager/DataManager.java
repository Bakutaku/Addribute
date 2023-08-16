package com.happyineo.addribute.manager;

import com.happyineo.addribute.Beans.Config;
import com.happyineo.addribute.type.LogType;

import java.io.IOException;

import static com.happyineo.addribute.Utils.log;

public class DataManager {

    private static DataManager manager; //　インスタンス格納用

    private DataManager() {

        try {
            YamlManager yamlManager = YamlManager.getManager();

            Config config = yamlManager.loadYamlData("test.yml", Config.class);

            log("name:"+config.getName());
            log("count:"+config.getCount());

            for (String str : config.getList()) {
                log("list:"+str);
            }
            for (String key : config.getMap().keySet()) {
                log("key:"+key,"value:"+config.getMap().get(key));
            }
            for (Config.test test : config.getTests()) {
                log("id:"+test.getId()+":name:"+test.getData());
            }

        } catch (IOException e) {
            log(LogType.WARNING,"ファイル読み込み失敗");
        }
    }


    public static DataManager getManager(){
        if (manager == null) manager = new DataManager();
        return manager;
    }

}
