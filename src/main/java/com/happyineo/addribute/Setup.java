package com.happyineo.addribute;

import com.happyineo.addribute.manager.*;

import static com.happyineo.addribute.Utils.log;

public class Setup {

    public void setup(){
        log("セットアップ中");

        SetupFiles file = new SetupFiles();

        file.setupFile();

        JsonManager.getManager();

        YamlManager.getManager();

        DataManager.getManager();

        DamageManager.getManager();

        StatusManager.getManager();

        PlayerStatusBarManager.getManager();

        AttributeManager.getManager();

        RPGDamage.getManager();

        log("セットアップが完了しました");
    }
}
