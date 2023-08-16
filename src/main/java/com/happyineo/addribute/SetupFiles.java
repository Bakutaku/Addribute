package com.happyineo.addribute;

import com.happyineo.addribute.type.LogType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static com.happyineo.addribute.Addribute.getPlugin;
import static com.happyineo.addribute.Utils.log;

public class SetupFiles {

    public static final String current = "./plugins/" + getPlugin().getClass().getSimpleName() + "/"; // ファイル生成場所
    private final String resourcesFile = "files/";  // リソースファイルの場所

    private final String[] configFiles = {"test.yml"};  // 実行に必要なファイル
    private final String[] oneCreateFiles = {"license/Snakeyaml_license.txt","license/Gson_license.txt","使い方.txt"};   // 1度だけ生成されるファイル


    public void setupFile(){

        log(LogType.INFO,"ファイルのセットアップ中");

        List<String> temp = new ArrayList<>();  // フォルダのパスを一時格納する用

        // 使用するフォルダを全て取得
        for(String ps : configFiles){
            // 末尾の/の場所を調べる
            int index = ps.lastIndexOf("/");

            // フォルダがパスに含まれているか
            if(index != -1){
                // 含まれている場合

                // フォルダの部分を追加する
                temp.add(ps.substring(0,index));
            }
        }
        for(String ps : oneCreateFiles ){
            // 末尾の/の場所を調べる
            int index = ps.lastIndexOf("/");

            // フォルダがパスに含まれているか
            if(index != -1){
                // 含まれている場合

                // フォルダの部分を追加する
                temp.add(ps.substring(0,index));
            }
        }

        // 重複削除
        temp = new ArrayList<>(new HashSet<>(temp));


        // フォルダを並び変える(長さ順)
        temp.sort(Comparator.comparing(String::length));

        try{
            // コンフィグフォルダを取得
            File root = new File(current);

            // コンフィグフォルダがあるか
            if(!root.exists()){
                // ない場合

                // フォルダを作成
                root.mkdir();

                // コンフィグフォルダ直下にある１度しか生成しないファイルの生成
                for(String f : oneCreateFiles){
                    // 直下か調べる
                    if(!f.contains("/")){
                        // 直下だった場合

                        // ファイル生成
                        this.createFile(f);
                    }
                }
            }

            List<String> files = new ArrayList<>();   // 生成するファイル格納用

            // ファイルの生成の有無を確かめる(最初に生成するもの)
            for(String ps : temp){
                // パスのフォルダを取得
                File file = new File(current+ps);

                // フォルダがあるか調べる
                if(!file.exists()){
                    // ない場合

                    // フォルダを生成
                    log(LogType.INFO,"フォルダの作成:"+file.mkdir());

                    // 生成するファイルを調べる
                    for(String f : oneCreateFiles){
                        // フォルダの範囲を調べる
                        int index = f.lastIndexOf("/");

                        // フォルダの中に入っていない場合スキップする
                        if(index == -1)continue;

                        // フォルダのパスが同じか
                        if(f.substring(0,index).equals(ps)){
                            // 同じ場合
                            files.add(f);
                        }
                    }
                }
            }

            // 実行するのに必要なファイルを生成する
            for(String ps :configFiles){
                // ファイルがあるか調べる
                if(!new File(current+ps).exists()){
                    // ない場合

                    // ファイルを生成する
                    this.createFile(ps);
                }
            }

            // 生成時のみ生成するファイルを生成する(ややこしい(*'▽'))
            for(String ps : files){
                // ファイルを生成する
                this.createFile(ps);
            }

        }catch (IOException e){
            log(LogType.WARNING,"ファイルのセットアップに失敗しました"+e);
            return;
        }catch (Exception e){
            log(LogType.CRITICAL,"ファイルのセットアップ中に予期しないエラーが発生しました"+e);
            return;
        }
        log(LogType.INFO,"ファイルのセットアップが完了しました");
    }




    /**
     * 指定されてファイルパスのファイルを作成する
     * @param ps ファイルを生成できたかどうか
     */
    public boolean createFile(String ps) throws IOException,Exception{
        // 変数定義
        InputStream resource;
        File file;

        log(LogType.INFO,"ファイルを生成します");

        // 対象ファイルを選択
        file = new File(current+ps);

        // ファイルを作成
        if(!file.createNewFile()){
            // すでに存在する場合
            return false;
        }

        // リソースファイルのファイルを取得する
        resource = getResourceIn(resourcesFile+ps);

        // 読み込みの可否
        if(resource == null){
            log(LogType.WARNING,"リソースファイルの取得に失敗しました");
            throw new IOException();
        }

        // ファイルの書き込み
        try(BufferedReader br = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))){

            // 変数定義
            String data;    // 取得したデータを一時格納する用

            // 1行ずつ書き込む
            while((data = br.readLine()) != null){
                // 書き込み
                bufferedWriter.write(data+"\n");
            }
        }

        log(LogType.INFO,"ファイルを生成しました");
        return true;
    }



    /**
     * リソース内のファイルを取得
     * @param ps ファイルパス
     * @return {@link InputStream}
     */
    private InputStream getResourceIn(String ps){
        return getPlugin().getClass().getClassLoader().getResourceAsStream(ps);
    }
}

