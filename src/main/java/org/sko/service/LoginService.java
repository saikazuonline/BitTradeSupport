package org.sko.service;

import org.apache.commons.lang3.StringUtils;
import org.sko.form.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    
    public boolean jsonCheck() {
        
        return true;
    }
    
    /**
     * ログイン
     * 
     * @param userId
     * @param password
     * @return
     */
    public boolean login(LoginForm loginForm) {
        if (StringUtils.isNotEmpty(loginForm.getUsername()) && StringUtils.isNotEmpty(loginForm.getPassword())) {
            if ("a".equals(loginForm.getUsername()) && "a".equals(loginForm.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
