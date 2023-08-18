package com.happyineo.addribute;

import com.happyineo.addribute.manager.DataManager;
import com.happyineo.addribute.manager.JsonManager;
import com.happyineo.addribute.manager.StatusManager;
import com.happyineo.addribute.manager.YamlManager;

import static com.happyineo.addribute.Utils.log;

public class Setup {

    public void setup(){
        log("セットアップ中");

        SetupFiles file = new SetupFiles();

        file.setupFile();

        JsonManager.getManager();

        YamlManager.getManager();

        DataManager.getManager();

        StatusManager.getManager();

        log("セットアップが完了しました");
    }
}
