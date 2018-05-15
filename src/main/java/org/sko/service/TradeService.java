package org.sko.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.sko.dto.KeyDto;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TradeService {
    
    private KeyDto keyDto;
    
    public KeyDto getKey() {
        
        String strKeyData = null;
        
        try{
            // ファイル場所指定
            File file = new File("src/main/resources/static/json/key.json");
            
            // ロードするために必要なインスタンス生成
            BufferedReader br = new BufferedReader(new FileReader(file));

            // jsonDataをString型で挿入
            strKeyData = br.readLine();

            br.close();
          }catch(FileNotFoundException e){
            System.out.println(e);
          }catch(IOException e){
            System.out.println(e);
          }
        
        // インスタンス生成
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            keyDto = mapper.readValue(strKeyData, KeyDto.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return keyDto;
    }

}
