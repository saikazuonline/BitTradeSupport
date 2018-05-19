package org.sko.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

import org.sko.dto.KeyDto;
import org.sko.dto.TradeDto;
import org.sko.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

@Component
public class TradeBatch {

    @Autowired
    private TradeService tradeService;

    private TradeDto tradeDto;
    private KeyDto keyDto;

    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void sellBatch() throws Exception {

        String strTradeData = null;
        String strKeyData = null;

        // ファイル場所指定
        File tradeFile = new File("src/main/resources/static/json/trade.json");
        File keyFile = new File("src/main/resources/static/json/key.json");

        if (!tradeFile.exists() || !keyFile.exists()) {
            System.out.println("ファイルがありません。");
            return;
        }

        // ロードするために必要なインスタンス生成
        BufferedReader brtf = new BufferedReader(new FileReader(tradeFile));
        BufferedReader brkf = new BufferedReader(new FileReader(keyFile));

        // jsonDataをString型で挿入
        strTradeData = brtf.readLine();
        strKeyData = brkf.readLine();

        // ファイルクローズ
        brtf.close();
        brkf.close();

        // インスタンス生成
        ObjectMapper mapper = new ObjectMapper();

        // Dto型にJSONデータを格納
        tradeDto = mapper.readValue(strTradeData, TradeDto.class);
        keyDto = mapper.readValue(strKeyData, KeyDto.class);

        // BinanceApiインスタンス生成
        BinanceApi api = new BinanceApi();

        // apiにapiKeyとsecretKeyをセット
        api.setApiKey(keyDto.getApiKey());
        api.setSecretKey(keyDto.getSecretKey());

        // 現在価格の初期化
        BigDecimal nowPrice = BigDecimal.ZERO;

        try {
            nowPrice = api.pricesMap().get(tradeDto.getCurrency() + "BTC");
        } catch (BinanceApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 購入時価格を変数に格納
        BigDecimal price = tradeDto.getPrice();

        // 小数点の数を取得するための処理 START
        String strPrice = price.toString();
        String strRepPrice = strPrice.replace(".", ",");
        String[] split = strRepPrice.split(",");
        int decimal = split[1].length();
        // 小数点の数を取得するための処理 END

        // 購入時上限倍率を購入時価格に乗算し格納
        BigDecimal upper = price.multiply(new BigDecimal(tradeDto.getUpper()));
        upper = upper.setScale(decimal, BigDecimal.ROUND_HALF_DOWN);

        // 購入時下限倍率を購入時価格に乗算し格納
        BigDecimal lower = price.multiply(new BigDecimal(tradeDto.getLower()));
        lower = lower.setScale(decimal, BigDecimal.ROUND_HALF_DOWN);

        // 上限値と下限値に上回るor下回れば売却処理を実行
        if (upper.compareTo(nowPrice) <= 0) {
            try {
                tradeService.sell(api, tradeDto);

                // ファイル削除
                tradeFile.delete();
            } catch (BinanceApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (lower.compareTo(nowPrice) >= 0) {
            try {
                tradeService.sell(api, tradeDto);

                // ファイル削除
                tradeFile.delete();
            } catch (BinanceApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("売れていません。");
        }
    }
}
