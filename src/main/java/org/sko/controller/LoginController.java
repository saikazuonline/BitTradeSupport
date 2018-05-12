package org.sko.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sko.form.KeyForm;
import org.sko.form.LoginForm;
import org.sko.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    protected ResourceLoader resourceLoader;
    
    @RequestMapping("/")
    public String index(Model model) {
        
        return "redirect:/login";
    }
    
    @RequestMapping("/login")
    public String Login(Model model) {
        
        model.addAttribute("loginForm", new LoginForm());
        
        return "login/index";
    }
    
    @RequestMapping("/login/do")
    public String LoginDo(Model model, @Valid LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {
        
        String pathAndFile = "static/json/key.json";
        Resource resource = resourceLoader.getResource("classpath:" + pathAndFile);
        
        if (loginService.login(loginForm) 
                && loginService.jsonCheck(resource)) {

            return "trade/index";
            
        } else if(loginService.login(loginForm)
                && !loginService.jsonCheck(resource)) {
                
            model.addAttribute("keyForm", new KeyForm());
            
            return "key/index";
        }
        
        model.addAttribute("error", "ID 又は PWが違います。");
        model.addAttribute("loginForm", new LoginForm());
        
        return "login/index";
    }
}
