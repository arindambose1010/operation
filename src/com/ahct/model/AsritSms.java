package com.ahct.model;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ASRIT_SMS")
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
public class AsritSms  implements java.io.Serializable{
    @Id
    @Column(name = "SNO", unique = true, nullable = false)
    private Long sno;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "MESSAGE")
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CRT_DT")
    private Date crtDt;
    @Column(name = "CRT_USR")
    private String crtUsr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPD_DT")
    private Date updDt;
    @Column(name = "UPD_USR")
    private String updUsr;
    @Column(name = "SMS_SENT")
    private String smsSent;
    @Column(name = "sms_retry_count")
    Long retryCount;
    @Column(name = "sms_sent_count")
    Long smsSentCount;
    @Column(name = "state_code")
    String stateCode;
    @Column(name = "template_id")
    String templateId;
    
    public AsritSms(){}
    public AsritSms(String mobile, String message, String crtUsr, Date crtDt, String updUsr, Date updDt, String smsSent,String stateCode){
        this.mobile = mobile;
        this.message = message;
        this.crtDt = crtDt;
        this.crtUsr = crtUsr;
        this.updDt = updDt;
        this.updUsr = updUsr;
        this.smsSent = smsSent;
        this.stateCode = stateCode;
        
    }


    public void setSno(Long sno) {
        this.sno = sno;
    }

    public Long getSno() {
        return sno;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    public String getCrtUsr() {
        return crtUsr;
    }

    public void setUpdDt(Date updDt) {
        this.updDt = updDt;
    }

    public Date getUpdDt() {
        return updDt;
    }

    public void setUpdUsr(String updUsr) {
        this.updUsr = updUsr;
    }

    public String getUpdUsr() {
        return updUsr;
    }

    public void setSmsSent(String smsSent) {
        this.smsSent = smsSent;
    }

    public String getSmsSent() {
        return smsSent;
    }

    public void setRetryCount(Long retryCount) {
        this.retryCount = retryCount;
    }

    public Long getRetryCount() {
        return retryCount;
    }

    public void setSmsSentCount(Long smsSentCount) {
        this.smsSentCount = smsSentCount;
    }

    public Long getSmsSentCount() {
        return smsSentCount;
    }
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
    
    
}
