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
@Table(name = "EHF_FOLLOWUP_AUDIT")
public class EhfFollowUpAudit implements Serializable  {

	private EhfFollowUpAuditId id;
	private String actBy;
    private String actId;
    private Date crtDt;
    private String crtUsr;
    private String langId;
    private String remarks;
    private String transactionId;
    private Date sbhPaidDt;
    private String rejCode;
    private String acctConsumed;
    private String paymentUid;
    private Double apprAmt;
    private String userRole;
    private String fileName;
	private String booksUpdatedStatus;
    
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="actOrder", column=@Column(name="ACT_ORDER", nullable=false, length=10) ), 
        @AttributeOverride(name="caseFollowupId", column=@Column(name="CASE_FOLLOWUP_ID", nullable=false, length=15) ),
        @AttributeOverride(name="caseType", column=@Column(name="CASE_TYPE", nullable=false, length=12) ) } )  
    
    
    public EhfFollowUpAuditId getId() {
		return id;
	}
	public void setId(EhfFollowUpAuditId id) {
		this.id = id;
	}
	@Column(name="ACT_BY")    
    public String getActBy() {
		return actBy;
	}
	public void setActBy(String actBy) {
		this.actBy = actBy;
	}
	@Column(name="ACT_ID")
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
	@Column(name="lang_id")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="transaction_id")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@Column(name="sbh_paid_date")
	public Date getSbhPaidDt() {
		return sbhPaidDt;
	}
	public void setSbhPaidDt(Date sbhPaidDt) {
		this.sbhPaidDt = sbhPaidDt;
	}
	@Column(name="rej_code")
	public String getRejCode() {
		return rejCode;
	}
	public void setRejCode(String rejCode) {
		this.rejCode = rejCode;
	}
	@Column(name="accts_consumed")
	public String getAcctConsumed() {
		return acctConsumed;
	}
	public void setAcctConsumed(String acctConsumed) {
		this.acctConsumed = acctConsumed;
	}
	@Column(name="payment_uid")
	public String getPaymentUid() {
		return paymentUid;
	}
	public void setPaymentUid(String paymentUid) {
		this.paymentUid = paymentUid;
	}
	@Column(name="appr_amt")
	public Double getApprAmt() {
		return apprAmt;
	}
	public void setApprAmt(Double apprAmt) {
		this.apprAmt = apprAmt;
	}
	@Column(name="user_role")
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="BOOKS_UPDATED_STATUS")
	public String getBooksUpdatedStatus() {
		return booksUpdatedStatus;
	}
	public void setBooksUpdatedStatus(String booksUpdatedStatus) {
		this.booksUpdatedStatus = booksUpdatedStatus;
	}

    
}
