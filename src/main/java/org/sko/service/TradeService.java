package org.sko.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

import org.sko.dto.KeyDto;
import org.sko.dto.TradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceOrder;
import com.webcerebrium.binance.datatype.BinanceOrderPlacement;
import com.webcerebrium.binance.datatype.BinanceOrderSide;
import com.webcerebrium.binance.datatype.BinanceOrderType;
import com.webcerebrium.binance.datatype.BinanceSymbol;

@Service
public class TradeService {

    @Autowired
    private KeyService keyService;

    private KeyDto keyDto;

    public KeyDto getKey() throws Exception {

        String strKeyData = null;

        // ファイル場所指定
        File file = new File("src/main/resources/static/json/key.json");

        // ロードするために必要なインスタンス生成
        BufferedReader br = new BufferedReader(new FileReader(file));

        // jsonDataをString型で挿入
        strKeyData = br.readLine();

        br.close();

        // インスタンス生成
        ObjectMapper mapper = new ObjectMapper();

        // JSON形式データをDtoに格納
        keyDto = mapper.readValue(strKeyData, KeyDto.class);

        return keyDto;
    }

    public TradeDto getPrice(TradeDto tradeDto, BinanceApi api) {

        BigDecimal price = null;
        try {
            price = api.pricesMap().get(tradeDto.getCurrency() + "BTC");
        } catch (BinanceApiException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        tradeDto.setPrice(price);

        return tradeDto;
    }

    public void setJson(TradeDto tradeDto) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(tradeDto);

        File file = new File("src/main/resources/static/json/trade.json");

        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();

        keyService.fileWrite(file, json);
    }

    public void buy(BinanceApi api, TradeDto tradeDto) throws BinanceApiException {

        BinanceSymbol symbol = new BinanceSymbol(tradeDto.getCurrency() + "BTC");
        BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.BUY);
        placement.setType(BinanceOrderType.LIMIT);
        placement.setPrice(tradeDto.getPrice());
        placement.setQuantity(new BigDecimal(tradeDto.getQuentity()));
        BinanceOrder order = api.getOrderById(symbol, api.createOrder(placement).get("orderId").getAsLong());
        System.out.println(order.toString());

    }

    public void sell(BinanceApi api, TradeDto tradeDto) throws BinanceApiException {

        BinanceSymbol symbol = new BinanceSymbol(tradeDto.getCurrency() + "BTC");
        BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.SELL);
        placement.setType(BinanceOrderType.LIMIT);
        placement.setPrice(tradeDto.getPrice());
        placement.setQuantity(new BigDecimal(tradeDto.getQuentity()));
        BinanceOrder order = api.getOrderById(symbol, api.createOrder(placement).get("orderId").getAsLong());
        System.out.println(order.toString());

    }

}
