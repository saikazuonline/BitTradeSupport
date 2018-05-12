package org.sko.controller;

import org.sko.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KeyController {
    
    @Autowired
    private KeyService keyService;
    
    @RequestMapping("/key/regist")
    public String regist(Model model) {
        
        keyService.regist(); // TODO
        
        return "redirect:/login";
    }

}
