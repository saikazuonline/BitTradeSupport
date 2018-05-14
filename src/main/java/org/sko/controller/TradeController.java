package org.sko.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sko.form.TradeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TradeController {

    @RequestMapping("/trade/do")
    public String tradeDo(Model model, @Valid TradeForm tradeForm, BindingResult bindingResult, HttpServletRequest request) {
        // TODO
        return "trade/wait";
    }
    
}
