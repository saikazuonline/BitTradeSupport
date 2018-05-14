package org.sko.form;

import javax.validation.constraints.Size;

public class TradeForm {

    @Size(min=1, max=10)
    private String currency;

    @Size(min=1, max=10)
    private String quentity;
    
    @Size(min=1, max=3)
    private String upper;

    @Size(min=1, max=3)
    private String lower;
    
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getQuentity() {
        return quentity;
    }

    public void setQuentity(String quentity) {
        this.quentity = quentity;
    }
    
    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }
    
    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }
}
