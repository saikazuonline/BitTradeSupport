package org.sko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KeyController {
    
    @RequestMapping("/key/regist")
    public String index(Model model) {
        
        return "redirect:/login";
    }

}
