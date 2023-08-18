package com.happyineo.addribute.manager;

import com.happyineo.addribute.SetupFiles;
import com.happyineo.addribute.type.LogType;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.parser.ParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

import static com.happyineo.addribute.Utils.log;

public class YamlManager {

    private static YamlManager manager; // インスタンス格納用

    private YamlManager() {
        manager = this;
    }

    /**
     * YamlManagerを取得する
     * @return {@link YamlManager}
     */
    public static YamlManager getManager(){
        if(manager == null) manager = new YamlManager();
        return manager;
    }

    /**
     * Yamlファイルを読み込む
     * @param fileName ファイル名
     * @param type データ型
     * @return データ
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T loadYamlData(String fileName, Class<T> type) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        try(BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(SetupFiles.current + fileName), StandardCharsets.UTF_8))){

            // snakeyamlの準備
            Yaml yaml = new Yaml(new CustomClassLoaderConstructor(type.getClassLoader()));

            // Javaオブジェクトに変換
            T data = yaml.loadAs(file,type);

            // 取得に失敗した場合、インスタンスを生成する
            if(data == null) {
                log(fileName+"の情報を取得するのに失敗したため初期値を使用します");
                data = type.getDeclaredConstructor().newInstance();
            }

            // 値を返す
            return data;
        }catch (ParserException | ConstructorException e){
            log(LogType.ERROR,fileName+"の構文が間違っています。",
                    fileName+"の取得に失敗したため初期値を適応します",
                    "原因:"+e.getMessage());
            return type.getDeclaredConstructor().newInstance();
        }
    }
}
