package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_TDS_AUDIT")
public class EhfTdsAudit implements Serializable{

    private EhfTdsAuditId id;
    private String actId;
    private String actBy;
    private String remarks;
    private String caseType;
    private String crtUsr;
    private Date crtDt;
    private String langId;
    private String transactionId;
    private Date sbhPaidDate;
    private String rejCode;
    private String acctsConsumed;
    private String paymentUid;
    private String fileName;

    
    public void setId(EhfTdsAuditId id) {
        this.id = id;
    }

    @EmbeddedId 
    @AttributeOverrides( {
        @AttributeOverride(name="tdsPaymentId", column=@Column(name="TDS_PAYMENT_ID",nullable=false,length=25)),
        @AttributeOverride(name="actOrder",column=@Column(name="ACT_ORDER",nullable=false,length=10 ))
    }
    ) 
    public EhfTdsAuditId getId() {
        return id;
    }
    
    public void setActId(String actId) {
        this.actId = actId;
    }
    @Column(name="act_id")
    public String getActId() {
        return actId;
    }

    public void setActBy(String actBy) {
        this.actBy = actBy;
    }
    @Column(name="act_by")
    public String getActBy() {
        return actBy;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name="remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
    @Column(name="case_type")
    public String getCaseType() {
        return caseType;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Column(name="CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }
    @Column(name="lang_id")
    public String getLangId() {
        return langId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    @Column(name="transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setSbhPaidDate(Date sbhPaidDate) {
        this.sbhPaidDate = sbhPaidDate;
    }
    @Column(name="sbh_paid_date")
    public Date getSbhPaidDate() {
        return sbhPaidDate;
    }

    public void setRejCode(String rejCode) {
        this.rejCode = rejCode;
    }
    @Column(name="rej_code")
    public String getRejCode() {
        return rejCode;
    }

    public void setAcctsConsumed(String acctsConsumed) {
        this.acctsConsumed = acctsConsumed;
    }
    @Column(name="accts_consumed")
    public String getAcctsConsumed() {
        return acctsConsumed;
    }
   
    public void setPaymentUid(String paymentUid) {
        this.paymentUid = paymentUid;
    }
    @Column(name="payment_uid")
    public String getPaymentUid() {
        return paymentUid;
    }
    @Column(name="file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
}
