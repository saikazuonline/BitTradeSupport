package org.sko.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.sko.form.KeyForm;
import org.sko.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    public String cookieName = "FA-admin";
    
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
    public String LoginDo(Model model, @Valid LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        
        if(login(loginForm)){
            
            model.addAttribute("keyForm", new KeyForm());

            return "/key/index";
        }
        
        model.addAttribute("error", "ログイン失敗");
        model.addAttribute("loginForm", new LoginForm());
        
        return "login/index";
    }
    
    /**
     * ログイン
     * 
     * @param userId
     * @param password
     * @return
     */
    private boolean login(LoginForm loginForm) {
        if (StringUtils.isNotEmpty(loginForm.getUsername()) && StringUtils.isNotEmpty(loginForm.getPassword())) {
            if ("a".equals(loginForm.getUsername()) && "a".equals(loginForm.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
