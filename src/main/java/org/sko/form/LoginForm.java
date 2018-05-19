package org.sko.form;

import javax.validation.constraints.Size;

public class LoginForm {

    @Size(min = 1, max = 50)
    private String username;

    @Size(min = 1, max = 50)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
