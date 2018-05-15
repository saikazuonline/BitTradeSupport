package org.sko.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sko.dto.KeyDto;
import org.sko.dto.TradeDto;
import org.sko.form.TradeForm;
import org.sko.service.TradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcerebrium.binance.api.BinanceApi;

@Controller
public class TradeController {
    
    @Autowired
    private TradeService tradeService;
    
    private TradeDto tradeDto;
    
    // BinanceApiインスタンス生成
    BinanceApi api;

    @RequestMapping("/trade/do")
    public String tradeDo(Model model, @Valid TradeForm tradeForm, BindingResult bindingResult, HttpServletRequest request) {
        
        // インスタンス生成
        api = new BinanceApi();
        
        // apiKeyとsecretKeyを取得
        KeyDto keyDto = tradeService.getKey();
        
        tradeDto = new TradeDto();
        
        // formをdtoにコピー
        BeanUtils.copyProperties(tradeForm, tradeDto);
        
        // TODO
        return "trade/wait";
    }
    
}
