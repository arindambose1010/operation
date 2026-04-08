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
@Table(name = "EHF_AHC_AUDIT")
public class EhfAhcAudit implements Serializable {

	private EhfAhcAuditId id;
    private String actBy;
    private String actId;
    private Date crtDt;
    private String crtUsr;
    private String remarks;
    private Double apprvAmt;
    private String userRole;
    //added for payment transaction
    private String transactionId;
	private Date sbhPaidDate;
	private String paymentUid;
	private String rejCode;
    private String filename;
    
    
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="actOrder", column=@Column(name="ACT_ORDER", nullable=false, length=5) ), 
        @AttributeOverride(name="ahcId", column=@Column(name="AHC_ID", nullable=false, length=5) ) } )
public EhfAhcAuditId getId() {
        return id;
}
public void setId(EhfAhcAuditId id) {
        this.id = id;
}
@Column(name="ACT_BY")
public String getActBy() {
    return actBy;
}

public void setActBy(String actBy) {
    this.actBy = actBy;
}

@Column(name="ACT_ID", nullable = false)
public String getActId() {
    return actId;
}

public void setActId(String actId) {
    this.actId = actId;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DT", nullable = false)
public Date getCrtDt() {
    return crtDt;
}

public void setCrtDt(Date crtDt) {
    this.crtDt = crtDt;
}

@Column(name="CRT_USR", nullable = false)
public String getCrtUsr() {
    return crtUsr;
}

public void setCrtUsr(String crtUsr) {
    this.crtUsr = crtUsr;
}

@Column(name="REMARKS")
public String getRemarks() {
    return remarks;
}

public void setRemarks(String remarks) {
    this.remarks = remarks;
}
@Column(name="apprv_amt")
public Double getApprvAmt() {
	return apprvAmt;
}
public void setApprvAmt(Double apprvAmt) {
	this.apprvAmt = apprvAmt;
}

@Column(name="user_role")
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}
@Column(name="transaction_id")
public String getTransactionId() {
	return transactionId;
}
public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}
@Column(name="sbh_paid_date")
public Date getSbhPaidDate() {
	return sbhPaidDate;
}
public void setSbhPaidDate(Date sbhPaidDate) {
	this.sbhPaidDate = sbhPaidDate;
}
@Column(name="payment_uid")
public String getPaymentUid() {
	return paymentUid;
}
public void setPaymentUid(String paymentUid) {
	this.paymentUid = paymentUid;
}
@Column(name="rej_code")
public String getRejCode() {
	return rejCode;
}
public void setRejCode(String rejCode) {
	this.rejCode = rejCode;
}
@Column(name="FILE_NAME")
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}




}
