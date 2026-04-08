package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_CASE_DTLS database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_CASE_DTLS")
public class EhfChronicCaseDtl implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfChronicCaseDtlPK id;
	private Date chCancelDt;
	private BigDecimal chClAmount;
	private String chDrugCode;
	private Date chSbhDt;
	private Date chSubDt;
	private Date chTransDt;
	private String chTransId;
	private Date chronicRegnDate;
	private String chronicStatus;
	private String claimAmount;
	private String claimNo;
	private Date clmSubDt;
	private Date crtDt;
	private String crtUsr;
	private String fileName;
	private String flagged;
	private String grievanceFlag;
	private String hospCode;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String paymentCheck;
	private Date paymentSentDate;
	private Double pckAppvAmt;
	private String schemeId;
	private String totDrugAmount;
	private String totPckgAmt;
	private String pendingWith;

    public EhfChronicCaseDtl() {
    }


	@EmbeddedId
	public EhfChronicCaseDtlPK getId() {
		return this.id;
	}

	public void setId(EhfChronicCaseDtlPK id) {
		this.id = id;
	}
	

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CH_CANCEL_DT")
	public Date getChCancelDt() {
		return this.chCancelDt;
	}

	public void setChCancelDt(Date chCancelDt) {
		this.chCancelDt = chCancelDt;
	}


	@Column(name="CH_CL_AMOUNT")
	public BigDecimal getChClAmount() {
		return this.chClAmount;
	}

	public void setChClAmount(BigDecimal chClAmount) {
		this.chClAmount = chClAmount;
	}


	@Column(name="CH_DRUG_CODE")
	public String getChDrugCode() {
		return this.chDrugCode;
	}

	public void setChDrugCode(String chDrugCode) {
		this.chDrugCode = chDrugCode;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CH_SBH_DT")
	public Date getChSbhDt() {
		return this.chSbhDt;
	}

	public void setChSbhDt(Date chSbhDt) {
		this.chSbhDt = chSbhDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CH_SUB_DT")
	public Date getChSubDt() {
		return this.chSubDt;
	}

	public void setChSubDt(Date chSubDt) {
		this.chSubDt = chSubDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CH_TRANS_DT")
	public Date getChTransDt() {
		return this.chTransDt;
	}

	public void setChTransDt(Date chTransDt) {
		this.chTransDt = chTransDt;
	}


	@Column(name="CH_TRANS_ID")
	public String getChTransId() {
		return this.chTransId;
	}

	public void setChTransId(String chTransId) {
		this.chTransId = chTransId;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CHRONIC_REGN_DATE")
	public Date getChronicRegnDate() {
		return this.chronicRegnDate;
	}

	public void setChronicRegnDate(Date chronicRegnDate) {
		this.chronicRegnDate = chronicRegnDate;
	}


	@Column(name="CHRONIC_STATUS")
	public String getChronicStatus() {
		return this.chronicStatus;
	}

	public void setChronicStatus(String chronicStatus) {
		this.chronicStatus = chronicStatus;
	}


	@Column(name="CLAIM_AMOUNT")
	public String getClaimAmount() {
		return this.claimAmount;
	}

	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}


	@Column(name="CLAIM_NO")
	public String getClaimNo() {
		return this.claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CLM_SUB_DT")
	public Date getClmSubDt() {
		return this.clmSubDt;
	}

	public void setClmSubDt(Date clmSubDt) {
		this.clmSubDt = clmSubDt;
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


	public String getFlagged() {
		return this.flagged;
	}

	public void setFlagged(String flagged) {
		this.flagged = flagged;
	}


	@Column(name="GRIEVANCE_FLAG")
	public String getGrievanceFlag() {
		return this.grievanceFlag;
	}

	public void setGrievanceFlag(String grievanceFlag) {
		this.grievanceFlag = grievanceFlag;
	}


	@Column(name="HOSP_CODE")
	public String getHospCode() {
		return this.hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="PAYMENT_CHECK")
	public String getPaymentCheck() {
		return this.paymentCheck;
	}

	public void setPaymentCheck(String paymentCheck) {
		this.paymentCheck = paymentCheck;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="PAYMENT_SENT_DATE")
	public Date getPaymentSentDate() {
		return this.paymentSentDate;
	}

	public void setPaymentSentDate(Date paymentSentDate) {
		this.paymentSentDate = paymentSentDate;
	}


	@Column(name="PCK_APPV_AMT")
	public Double getPckAppvAmt() {
		return pckAppvAmt;
	}


	public void setPckAppvAmt(Double pckAppvAmt) {
		this.pckAppvAmt = pckAppvAmt;
	}


	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}

	

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}


	@Column(name="TOT_DRUG_AMOUNT")
	public String getTotDrugAmount() {
		return this.totDrugAmount;
	}

	public void setTotDrugAmount(String totDrugAmount) {
		this.totDrugAmount = totDrugAmount;
	}


	@Column(name="TOT_PCKG_AMT")
	public String getTotPckgAmt() {
		return this.totPckgAmt;
	}

	public void setTotPckgAmt(String totPckgAmt) {
		this.totPckgAmt = totPckgAmt;
	}

	@Column(name="PENDING_WITH")
	public String getPendingWith() {
		return pendingWith;
	}


	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	
	

}