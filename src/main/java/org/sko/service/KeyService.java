package org.sko.service;

import java.io.File;
import java.io.FileWriter;

import org.sko.form.KeyForm;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KeyService {

    /**
     * apiKey secretKey 登録処理
     * 
     * @param loginForm
     */
    public void regist(KeyForm keyForm) throws Exception {

        // 作成先パス指定
        File file = new File("src/main/resources/static/json/key.json");

        // ファイル作成
        this.fileCreate(file);

        String jsonDataStr = this.jsonDataCreate(keyForm);

        this.fileWrite(file, jsonDataStr);
    }

    /**
     * ファイル作成
     * 
     */
    public void fileCreate(File file) throws Exception {

        // 作成先パス指定
        File newdir = new File("src/main/resources/static/json");

        // ディレクトリ作成
        newdir.mkdir();

        // ファイル作成
        file.createNewFile();
    }

    /**
     * keyFormの値をjsonデータをString型で返却
     * 
     * @param keyForm
     * @return
     */
    public String jsonDataCreate(KeyForm keyForm) throws Exception {

        // オブジェクトマッパーインスタンス作成
        ObjectMapper mapper = new ObjectMapper();

        // keyFormの値をJSON形式でStringに格納
        String json = mapper.writeValueAsString(keyForm);

        return json;
    }

    /**
     * ファイル書き込み
     */
    public void fileWrite(File file, String jsonData) throws Exception {
        // FileWriterオブジェクトを作成（追記モード）
        FileWriter fw = new FileWriter(file, true);
        // ファイルに書き込み
        fw.write(jsonData);
        // ファイルクローズ
        fw.close();
    }
}
