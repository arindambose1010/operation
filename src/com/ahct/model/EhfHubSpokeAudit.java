package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the EHF_HUBSPOKE_AUDIT database table.
 * 
 */
@Entity
@Table(name="EHF_HUBSPOKE_AUDIT")
@NamedQuery(name="EhfHubSpokeAudit.findAll", query="SELECT a FROM EhfHubSpokeAudit a")
public class EhfHubSpokeAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="hubspokePaymentId", column=@Column(name="HUBSPOKE_PAYMENT_ID",nullable=false,length=20)),
        @AttributeOverride(name="actOrder",column=@Column(name="ACT_ORDER",nullable=false,length=10 ))
    }
    )
    private EhfHubspokeAuditPK id;

	@Column(name="ACCTS_CONSUMED")
	private String acctsConsumed;

	@Column(name="ACT_BY")
	private String actBy;

	@Column(name="ACT_ID")
	private String actId;

	@Column(name="CASE_TYPE")
	private String caseType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="DES_ACCT_NO")
	private String desAcctNo;

	@Column(name="DES_IFSC_CODE")
	private String desIfscCode;

	@Column(name="LANG_ID")
	private String langId;

	@Column(name="PAYMENT_UID")
	private String paymentUid;

	@Column(name="REJ_CODE")
	private String rejCode;

	private String remarks;

	@Column(name="RESP_FILENAME")
	private String respFilename;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SBH_PAID_DATE")
	private Date sbhPaidDate;

	@Column(name="SRC_ACCT_NO")
	private String srcAcctNo;

	@Column(name="TRANSACTION_ID")
	private String transactionId;

	public EhfHubSpokeAudit() {
	}

	public void setId(EhfHubspokeAuditPK id) {
		this.id = id;
	}
	
	public EhfHubspokeAuditPK getId() {
		return id;
	}
	
	public String getAcctsConsumed() {
		return this.acctsConsumed;
	}

	public void setAcctsConsumed(String acctsConsumed) {
		this.acctsConsumed = acctsConsumed;
	}

	public String getActBy() {
		return this.actBy;
	}

	public void setActBy(String actBy) {
		this.actBy = actBy;
	}

	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getDesAcctNo() {
		return this.desAcctNo;
	}

	public void setDesAcctNo(String desAcctNo) {
		this.desAcctNo = desAcctNo;
	}

	public String getDesIfscCode() {
		return this.desIfscCode;
	}

	public void setDesIfscCode(String desIfscCode) {
		this.desIfscCode = desIfscCode;
	}

	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getPaymentUid() {
		return this.paymentUid;
	}

	public void setPaymentUid(String paymentUid) {
		this.paymentUid = paymentUid;
	}

	public String getRejCode() {
		return this.rejCode;
	}

	public void setRejCode(String rejCode) {
		this.rejCode = rejCode;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRespFilename() {
		return this.respFilename;
	}

	public void setRespFilename(String respFilename) {
		this.respFilename = respFilename;
	}

	public Date getSbhPaidDate() {
		return this.sbhPaidDate;
	}

	public void setSbhPaidDate(Date sbhPaidDate) {
		this.sbhPaidDate = sbhPaidDate;
	}

	public String getSrcAcctNo() {
		return this.srcAcctNo;
	}

	public void setSrcAcctNo(String srcAcctNo) {
		this.srcAcctNo = srcAcctNo;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}