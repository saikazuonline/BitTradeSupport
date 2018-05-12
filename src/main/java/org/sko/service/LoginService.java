package org.sko.service;

import org.apache.commons.lang3.StringUtils;
import org.sko.form.LoginForm;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    
    /**
     * Jsonファイル存在チェック
     * 
     * @param resource
     * @return
     */
    public boolean jsonCheck(Resource resource) {
        
        // Jsonファイルが存在しているかチェック
        if(resource.exists()) {
            return true;
        }
        
         return false;
    }
    
    /**
     * ログイン
     * 
     * @param userId
     * @param password
     * @return
     */
    public boolean login(LoginForm loginForm) {
        if (StringUtils.isNotEmpty(loginForm.getUsername())
                && StringUtils.isNotEmpty(loginForm.getPassword())) {
            if ("a".equals(loginForm.getUsername()) 
                    && "a".equals(loginForm.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
