package com.happyineo.addribute;

import com.happyineo.addribute.manager.DataManager;

import static com.happyineo.addribute.Utils.log;

public class Setup {

    public void setup(){
        log("セットアップ中");

        SetupFiles file = new SetupFiles();

        file.setupFile();

        DataManager.getManager();

        log("セットアップが完了しました");
    }
}
