package com.happyineo.addribute.manager;

import com.google.gson.Gson;
import com.happyineo.addribute.SetupFiles;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonManager {

    private static JsonManager manager; // インスタンス格納用

    private JsonManager(){
        manager = this;
    }

    /**
     * JsonManagerを取得する
     * @return {@link JsonManager}
     */
    public static JsonManager getManager(){
        if(manager == null) manager = new JsonManager();
        return manager;
    }

    public <T> T loadJsonData(String fileName,Class<T> type) throws IOException {

        // ファイル読み込み準備
        try(BufferedReader file = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(SetupFiles.current + fileName)), StandardCharsets.UTF_8))){
            // JSON読み込み準備
            Gson gson = new Gson();

            // Javaオブジェクトに変換 & 値を返す
            return gson.fromJson(file,type);
        }
    }
}
