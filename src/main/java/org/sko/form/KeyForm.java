package org.sko.form;

import javax.validation.constraints.Size;

public class KeyForm {
    
    @Size(min=1, max=50)
    private String apiKey;

    @Size(min=1, max=50)
    private String secretKey;
    
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
