package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_AUDIT database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_AUDIT")
public class EhfChronicAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfChronicAuditPK id;
	private String actBy;
	private String actId;
	private long apprvAmt;
	private Date crtDt;
	private String crtUsr;
	private String fileName;
	private String langId;
	private String paymentUid;
	private String rejCode;
	private String remarks;
	private Date sbhPaidDate;
	private String transactionId;
	private String userRole;
	//private EhfChronicCaseDtl ehfChronicCaseDtl;

    public EhfChronicAudit() {
    }


	@EmbeddedId
	public EhfChronicAuditPK getId() {
		return this.id;
	}

	public void setId(EhfChronicAuditPK id) {
		this.id = id;
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


	@Column(name="APPRV_AMT")
	public long getApprvAmt() {
		return apprvAmt;
	}


	public void setApprvAmt(long apprvAmt) {
		this.apprvAmt = apprvAmt;
	}



    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	

	public void setCrtDt(Date crtDt) {
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


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="SBH_PAID_DATE")
	public Date getSbhPaidDate() {
		return this.sbhPaidDate;
	}

	public void setSbhPaidDate(Date sbhPaidDate) {
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


	//bi-directional many-to-one association to EhfChronicCaseDtl
   /* @ManyToOne
	@JoinColumns({
		@JoinColumn(name="CHRONIC_ID", referencedColumnName="CHRONIC_ID"),
		@JoinColumn(name="CHRONIC_NO", referencedColumnName="CHRONIC_NO")
		})
	public EhfChronicCaseDtl getEhfChronicCaseDtl() {
		return this.ehfChronicCaseDtl;
	}

	public void setEhfChronicCaseDtl(EhfChronicCaseDtl ehfChronicCaseDtl) {
		this.ehfChronicCaseDtl = ehfChronicCaseDtl;
	}
	*/
}