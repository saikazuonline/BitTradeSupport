package org.sko.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sko.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
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
        
        return "login/index";
    }
}
