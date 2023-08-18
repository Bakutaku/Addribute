package com.happyineo.addribute.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.happyineo.addribute.SetupFiles;

import java.io.*;
import java.nio.charset.StandardCharsets;


import static com.happyineo.addribute.Utils.log;

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

    /**
     * Jsonファイルを読み込む
     * @param fileName ファイル名
     * @param type データ型
     * @return データ
     * @throws IOException
     */
    public <T> T loadJsonData(String fileName,Class<T> type) throws IOException{

        // ファイル読み込み準備
        try(BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream (SetupFiles.current + fileName), StandardCharsets.UTF_8))){
            // JSON読み込み準備
            Gson gson = new Gson();

            // Javaオブジェクトに変換 & 値を返す
            return gson.fromJson(file,type);
        }
    }


    public <T> void saveJsonData(String fileName,T data) throws IOException {

        log(fileName+"を保存中...");

        File temp = new File(SetupFiles.current+ fileName);

        // ファイルがあるか調べない場合は作成する
        if(!temp.exists()) temp.createNewFile();

        // ファイル保存準備
        try(BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (SetupFiles.current + fileName),StandardCharsets.UTF_8))){

            // Json保存準備
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            // 保存する
            gson.toJson(data,file);

            log(fileName+"の保存が完了しました");
        }
    }
}
