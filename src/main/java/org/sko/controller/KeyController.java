package org.sko.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sko.form.LoginForm;
import org.sko.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KeyController {
    
    @Autowired
    private KeyService keyService;
    
    @RequestMapping("/key/regist")
    public String regist(Model model, @Valid LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {
        
        keyService.regist(loginForm); // TODO
        
        return "trade/index";
    }

}
