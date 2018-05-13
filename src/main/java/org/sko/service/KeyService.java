package org.sko.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.sko.form.KeyForm;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KeyService {
    
    /**
     * apiKey secretKey 登録処理
     * 
     * @param loginForm
     */
    public void regist(KeyForm keyForm) {
        
        File file = new File("src/main/resources/static/json/key.json");
        
        this.fileCreate(file);
        
        String jsonDataStr = this.jsonDataCreate(keyForm);
        
        this.fileWrite(file, jsonDataStr);
        
    }
    
    /**
     * ファイル作成
     * 
     */
    public void fileCreate(File file) {
        File newdir = new File("src/main/resources/static/json");
        newdir.mkdir();
        
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * keyFormの値をjsonデータをString型で返却
     * 
     * @param keyForm
     * @return
     */
    public String jsonDataCreate(KeyForm keyForm) {
        
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            json = mapper.writeValueAsString(keyForm);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return json;
    }
    
    /**
     * ファイル書き込み
     */
    public void fileWrite(File file, String jsonData) {
        
      //FileWriterオブジェクトを作成（追記モード）
        try {
            
            FileWriter fw = new FileWriter(file, true);
            fw.write(jsonData);
            
            //FileWriterオブジェクトをクローズ
            fw.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
