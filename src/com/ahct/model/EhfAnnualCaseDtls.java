package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_ANNUAL_CASE_DTLS database table.
 * 
 */
@Entity
@Table(name="EHF_ANNUAL_CASE_DTLS")
public class EhfAnnualCaseDtls implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AHC_ID")
	private String ahcId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="AHC_CLM_SUB_DT")
	private Date ahcClmSubDt;

	@Column(name="AHC_HOSP_CODE")
	private String ahcHospCode;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="AHC_REGN_DATE")
	private Date ahcRegnDate;

	@Column(name="AHC_STATUS")
	private String ahcStatus;

	@Column(name="CLAIM_NO")
	private String claimNo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="CS_REMARKS")
	private String csRemarks;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CS_SBH_DT")
	private Date csSbhDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CS_TRANS_DT")
	private Date csTransDt;

	@Column(name="CS_TRANS_ID")
	private String csTransId;

	@Column(name="FILE_NAME")
	private String fileName;
	
	@Column(name="FLAGGED")
	private String flagged;

	@Column(name="GRIEVANCE_FLAG")
	private String grievanceFlag;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="PAYMENT_CHECK")
	private String paymentCheck;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="PAYMENT_SENT_DATE")
	private Date paymentSentDate;

	@Column(name="PCK_APPV_AMT")
	private BigDecimal pckAppvAmt;

	@Column(name="SCHEME_ID")
	private String schemeId;

	@Column(name="TOT_PCKG_AMT")
	private String totPckgAmt;
	
	@Column(name="CLAIM_INIT_AMT")
	private String claimInitAmount;
	
	@Column(name="CLAIM_INIT_REMARKS")
	private String claimInitRemarks;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="BILL_DATE")
	private Date billDate;
	
	public EhfAnnualCaseDtls() {
    }
	
    public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	

	public String getAhcId() {
		return this.ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}

	public Date getAhcClmSubDt() {
		return this.ahcClmSubDt;
	}

	public void setAhcClmSubDt(Date ahcClmSubDt) {
		this.ahcClmSubDt = ahcClmSubDt;
	}

	public String getAhcHospCode() {
		return this.ahcHospCode;
	}

	public void setAhcHospCode(String ahcHospCode) {
		this.ahcHospCode = ahcHospCode;
	}

	public Date getAhcRegnDate() {
		return this.ahcRegnDate;
	}

	public void setAhcRegnDate(Date ahcRegnDate) {
		this.ahcRegnDate = ahcRegnDate;
	}

	public String getAhcStatus() {
		return this.ahcStatus;
	}

	public void setAhcStatus(String ahcStatus) {
		this.ahcStatus = ahcStatus;
	}

	public String getClaimNo() {
		return this.claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
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

	public String getCsRemarks() {
		return this.csRemarks;
	}

	public void setCsRemarks(String csRemarks) {
		this.csRemarks = csRemarks;
	}

	public Date getCsSbhDt() {
		return this.csSbhDt;
	}

	public void setCsSbhDt(Date csSbhDt) {
		this.csSbhDt = csSbhDt;
	}

	public Date getCsTransDt() {
		return this.csTransDt;
	}

	public void setCsTransDt(Date csTransDt) {
		this.csTransDt = csTransDt;
	}

	public String getCsTransId() {
		return this.csTransId;
	}

	public void setCsTransId(String csTransId) {
		this.csTransId = csTransId;
	}

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

	public String getGrievanceFlag() {
		return this.grievanceFlag;
	}

	public void setGrievanceFlag(String grievanceFlag) {
		this.grievanceFlag = grievanceFlag;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getPaymentCheck() {
		return this.paymentCheck;
	}

	public void setPaymentCheck(String paymentCheck) {
		this.paymentCheck = paymentCheck;
	}

	public Date getPaymentSentDate() {
		return this.paymentSentDate;
	}

	public void setPaymentSentDate(Date paymentSentDate) {
		this.paymentSentDate = paymentSentDate;
	}

	public BigDecimal getPckAppvAmt() {
		return this.pckAppvAmt;
	}

	public void setPckAppvAmt(BigDecimal pckAppvAmt) {
		this.pckAppvAmt = pckAppvAmt;
	}

	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getTotPckgAmt() {
		return this.totPckgAmt;
	}

	public void setTotPckgAmt(String totPckgAmt) {
		this.totPckgAmt = totPckgAmt;
	}
	
	public String getClaimInitAmount() {
		return claimInitAmount;
	}

	public void setClaimInitAmount(String claimInitAmount) {
		this.claimInitAmount = claimInitAmount;
	}
	
	public String getClaimInitRemarks() {
		return claimInitRemarks;
	}

	public void setClaimInitRemarks(String claimInitRemarks) {
		this.claimInitRemarks = claimInitRemarks;
	}

	

	
}