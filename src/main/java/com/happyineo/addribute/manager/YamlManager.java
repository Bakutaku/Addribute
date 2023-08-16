package com.happyineo.addribute.manager;

import com.happyineo.addribute.SetupFiles;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.FileInputStream;
import java.io.IOException;

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

    public <T> T loadYamlData(String FileName, Class<T> type) throws IOException {

        try(FileInputStream file = new FileInputStream(SetupFiles.current + FileName)){

            // snakeyamlの準備
            Yaml yaml = new Yaml(new CustomClassLoaderConstructor(type.getClassLoader()));

            // Javaオブジェクトに変換 & 値を返す
            return yaml.loadAs(file,type);
        }
    }
}
