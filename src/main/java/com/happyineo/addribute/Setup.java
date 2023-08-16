package com.happyineo.addribute;

import com.happyineo.addribute.manager.DataManager;

import static com.happyineo.addribute.Utils.log;

public class Setup {

    public void setup(){
        SetupFiles file = new SetupFiles();

        file.setupFile();

        log("ログ");

        DataManager.getManager();
    }

}
