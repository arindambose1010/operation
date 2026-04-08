package com.ahct.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_DRUG_VENDOR_BANK_DTLS database table.
 * 
 */
@Entity
@Table(name="EHFM_DRUG_VENDOR_BANK_DTLS")
public class EhfmDrugVendorBankDtl implements Serializable {
	private static final long serialVersionUID = 1L;
	private String benficiaryId;
	private String accountNo;
	private String bankId;
	private String benfAccountName;
	private String benfBillingheadQual;
	private String benficiaryAddress;
	private String benficiaryName;
	private String billHeadContactNo;
	private String billHeadEmail;
	private String billHeadName;
	private Date crtDt;
	private String crtUsr;
	private String deptId;
	private String ifscCode;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String panHolderName;
	private String panNumber;
	private String revFundActive;
	private String schemeId;
	private String tdsExempStatus;
	private Date tdsFromDt;
	private String tdsRemarks;
	private Date tdsToDt;

    public EhfmDrugVendorBankDtl() {
    }


	@Id
	@Column(name="BENFICIARY_ID")
	public String getBenficiaryId() {
		return this.benficiaryId;
	}

	public void setBenficiaryId(String benficiaryId) {
		this.benficiaryId = benficiaryId;
	}


	@Column(name="ACCOUNT_NO")
	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	@Column(name="BANK_ID")
	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}


	@Column(name="BENF_ACCOUNT_NAME")
	public String getBenfAccountName() {
		return this.benfAccountName;
	}

	public void setBenfAccountName(String benfAccountName) {
		this.benfAccountName = benfAccountName;
	}


	@Column(name="BENF_BILLINGHEAD_QUAL")
	public String getBenfBillingheadQual() {
		return this.benfBillingheadQual;
	}

	public void setBenfBillingheadQual(String benfBillingheadQual) {
		this.benfBillingheadQual = benfBillingheadQual;
	}


	@Column(name="BENFICIARY_ADDRESS")
	public String getBenficiaryAddress() {
		return this.benficiaryAddress;
	}

	public void setBenficiaryAddress(String benficiaryAddress) {
		this.benficiaryAddress = benficiaryAddress;
	}


	@Column(name="BENFICIARY_NAME")
	public String getBenficiaryName() {
		return this.benficiaryName;
	}

	public void setBenficiaryName(String benficiaryName) {
		this.benficiaryName = benficiaryName;
	}


	@Column(name="BILL_HEAD_CONTACT_NO")
	public String getBillHeadContactNo() {
		return this.billHeadContactNo;
	}

	public void setBillHeadContactNo(String billHeadContactNo) {
		this.billHeadContactNo = billHeadContactNo;
	}


	@Column(name="BILL_HEAD_EMAIL")
	public String getBillHeadEmail() {
		return this.billHeadEmail;
	}

	public void setBillHeadEmail(String billHeadEmail) {
		this.billHeadEmail = billHeadEmail;
	}


	@Column(name="BILL_HEAD_NAME")
	public String getBillHeadName() {
		return this.billHeadName;
	}

	public void setBillHeadName(String billHeadName) {
		this.billHeadName = billHeadName;
	}


    @Temporal( TemporalType.DATE)
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


	@Column(name="DEPT_ID")
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	@Column(name="IFSC_CODE")
	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


    @Temporal( TemporalType.DATE)
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


	@Column(name="PAN_HOLDER_NAME")
	public String getPanHolderName() {
		return this.panHolderName;
	}

	public void setPanHolderName(String panHolderName) {
		this.panHolderName = panHolderName;
	}


	@Column(name="PAN_NUMBER")
	public String getPanNumber() {
		return this.panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	@Column(name="REV_FUND_ACTIVE")
	public String getRevFundActive() {
		return this.revFundActive;
	}

	public void setRevFundActive(String revFundActive) {
		this.revFundActive = revFundActive;
	}


	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}


	@Column(name="TDS_EXEMP_STATUS")
	public String getTdsExempStatus() {
		return this.tdsExempStatus;
	}

	public void setTdsExempStatus(String tdsExempStatus) {
		this.tdsExempStatus = tdsExempStatus;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="TDS_FROM_DT")
	public Date getTdsFromDt() {
		return this.tdsFromDt;
	}

	public void setTdsFromDt(Date tdsFromDt) {
		this.tdsFromDt = tdsFromDt;
	}


	@Column(name="TDS_REMARKS")
	public String getTdsRemarks() {
		return this.tdsRemarks;
	}

	public void setTdsRemarks(String tdsRemarks) {
		this.tdsRemarks = tdsRemarks;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="TDS_TO_DT")
	public Date getTdsToDt() {
		return this.tdsToDt;
	}

	public void setTdsToDt(Date tdsToDt) {
		this.tdsToDt = tdsToDt;
	}

}