package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AsritAccountsDocAttchPK implements Serializable
{

    private String transId;
    private String savedName;
    
    public AsritAccountsDocAttchPK() 
    {
    }
    
    public AsritAccountsDocAttchPK(String transId, String savedName)
    {
        this.transId = transId;
        this.savedName = savedName;
    }
    
  
    @Column(name="TRANS_ID", nullable = false)
    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
    
    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }
    @Column(name="SAVED_NAME")
    public String getSavedName() {
        return savedName;
    }


}
