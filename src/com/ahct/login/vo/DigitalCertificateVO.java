package com.ahct.login.vo;

public class DigitalCertificateVO {
	  
	 
    private String signedTime;
    private String issueDn;
    private String serialNo;
    private String expiryDate;
    private String osFlag;
    private String key;
    private String userId;
    private String textData;
    private String textSignature;
    private String resultData;
    private String langId;
    private String locId;


    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setIssueDn(String issueDn) {
        this.issueDn = issueDn;
    }

    public String getIssueDn() {
        return issueDn;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setOsFlag(String osFlag) {
        this.osFlag = osFlag;
    }

    public String getOsFlag() {
        return osFlag;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getTextData() {
        return textData;
    }

    public void setTextSignature(String textSignature) {
        this.textSignature = textSignature;
    }

    public String getTextSignature() {
        return textSignature;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public String getResultData() {
        return resultData;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getLangId() {
        return langId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public String getLocId() {
        return locId;
    }
}
