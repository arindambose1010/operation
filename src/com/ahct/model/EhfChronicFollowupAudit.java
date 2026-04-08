package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the EHF_CHRONIC_FOLLOWUP_AUDIT database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_FOLLOWUP_AUDIT")
public class EhfChronicFollowupAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfChronicFollowupAuditPK id;
	private String acctsConsumed;
	private String actBy;
	private String actId;
	private BigDecimal apprAmt;
	private Timestamp crtDt;
	private String crtUsr;
	private String fileName;
	private String langId;
	private String paymentUid;
	private String rejCode;
	private String remarks;
	private Timestamp sbhPaidDate;
	private String transactionId;
	private String userRole;

    public EhfChronicFollowupAudit() {
    }


	@EmbeddedId
	public EhfChronicFollowupAuditPK getId() {
		return this.id;
	}

	public void setId(EhfChronicFollowupAuditPK id) {
		this.id = id;
	}
	

	@Column(name="ACCTS_CONSUMED")
	public String getAcctsConsumed() {
		return this.acctsConsumed;
	}

	public void setAcctsConsumed(String acctsConsumed) {
		this.acctsConsumed = acctsConsumed;
	}


	@Column(name="ACT_BY")
	public String getActBy() {
		return this.actBy;
	}

	public void setActBy(String actBy) {
		this.actBy = actBy;
	}


	@Column(name="ACT_ID")
	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}


	@Column(name="APPR_AMT")
	public BigDecimal getApprAmt() {
		return this.apprAmt;
	}

	public void setApprAmt(BigDecimal apprAmt) {
		this.apprAmt = apprAmt;
	}


	@Column(name="CRT_DT")
	public Timestamp getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Timestamp crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	@Column(name="FILE_NAME")
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Column(name="LANG_ID")
	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}


	@Column(name="PAYMENT_UID")
	public String getPaymentUid() {
		return this.paymentUid;
	}

	public void setPaymentUid(String paymentUid) {
		this.paymentUid = paymentUid;
	}


	@Column(name="REJ_CODE")
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


	@Column(name="SBH_PAID_DATE")
	public Timestamp getSbhPaidDate() {
		return this.sbhPaidDate;
	}

	public void setSbhPaidDate(Timestamp sbhPaidDate) {
		this.sbhPaidDate = sbhPaidDate;
	}


	@Column(name="TRANSACTION_ID")
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	@Column(name="USER_ROLE")
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}