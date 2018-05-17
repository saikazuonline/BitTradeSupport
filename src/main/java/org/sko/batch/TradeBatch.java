package org.sko.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import org.sko.dto.KeyDto;
import org.sko.dto.TradeDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webcerebrium.binance.api.BinanceApi;

@Component
public class TradeBatch {
    
    private TradeDto tradeDto;
    private KeyDto keyDto;
    
    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void sellBatch() {
        
      String strTradeData = null;
      String strKeyData = null;
      
      try{
          // ファイル場所指定
          File tradeFile = new File("src/main/resources/static/json/trade.json");
          File keyFile = new File("src/main/resources/static/json/key.json");
          
          // ロードするために必要なインスタンス生成
          BufferedReader brtf = new BufferedReader(new FileReader(tradeFile));
          BufferedReader brkf = new BufferedReader(new FileReader(keyFile));

          // jsonDataをString型で挿入
          strTradeData = brtf.readLine();
          strKeyData = brkf.readLine();

          brtf.close();
          brkf.close();
        }catch(FileNotFoundException e){
          System.out.println(e);
        }catch(IOException e){
          System.out.println(e);
        }
      
   // インスタンス生成
      ObjectMapper mapper = new ObjectMapper();
      
      try {
          tradeDto = mapper.readValue(strTradeData, TradeDto.class);
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
      
      BinanceApi api = new BinanceApi();
      
      api.setApiKey(keyDto.getApiKey());
      api.setSecretKey(keyDto.getSecretKey());
      
      BigDecimal price = api.pricesMap().get(tradeDto.getCurrency() + "BTC");
      
    }
}
