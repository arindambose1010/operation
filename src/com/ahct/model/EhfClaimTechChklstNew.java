package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_CLAIM_TECH_CHKLST_ORG")
public class EhfClaimTechChklstNew implements java.io.Serializable {

	private String caseId;
	private String totalClaim;
	private String dedRecomd;
	private String stay;
	private String input;
	private String profFeeBill;
	private String investBill;
	private String stayRemark;
	private String inputRemark;
	private String profFeeRemark;
	private String investBillRemark;
	private String diagnosisYn;
	private String casemgmtYn;
	private String evidenceYn;
	private String cpdRemark;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String mandatoryYn;

	@Id
	@Column(name = "case_id", nullable = false)
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column(name = "total_claim")
	public String getTotalClaim() {
		return totalClaim;
	}

	public void setTotalClaim(String totalClaim) {
		this.totalClaim = totalClaim;
	}

	@Column(name = "ded_recommended")
	public String getDedRecomd() {
		return dedRecomd;
	}

	public void setDedRecomd(String dedRecomd) {
		this.dedRecomd = dedRecomd;
	}

	@Column(name = "stay")
	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	@Column(name = "inputs")
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@Column(name = "prof_fee_bill")
	public String getProfFeeBill() {
		return profFeeBill;
	}

	public void setProfFeeBill(String profFeeBill) {
		this.profFeeBill = profFeeBill;
	}

	@Column(name = "invest_bill")
	public String getInvestBill() {
		return investBill;
	}

	public void setInvestBill(String investBill) {
		this.investBill = investBill;
	}

	@Column(name = "stay_remark")
	public String getStayRemark() {
		return stayRemark;
	}

	public void setStayRemark(String stayRemark) {
		this.stayRemark = stayRemark;
	}

	@Column(name = "input_remark")
	public String getInputRemark() {
		return inputRemark;
	}

	public void setInputRemark(String inputRemark) {
		this.inputRemark = inputRemark;
	}

	@Column(name = "prof_fee_remark")
	public String getProfFeeRemark() {
		return profFeeRemark;
	}

	public void setProfFeeRemark(String profFeeRemark) {
		this.profFeeRemark = profFeeRemark;
	}

	@Column(name = "invest_bill_remark")
	public String getInvestBillRemark() {
		return investBillRemark;
	}

	public void setInvestBillRemark(String investBillRemark) {
		this.investBillRemark = investBillRemark;
	}

	@Column(name = "diagnosis_yn")
	public String getDiagnosisYn() {
		return diagnosisYn;
	}

	public void setDiagnosisYn(String diagnosisYn) {
		this.diagnosisYn = diagnosisYn;
	}

	@Column(name = "casemgmt_yn")
	public String getCasemgmtYn() {
		return casemgmtYn;
	}

	public void setCasemgmtYn(String casemgmtYn) {
		this.casemgmtYn = casemgmtYn;
	}

	@Column(name = "evidence_yn")
	public String getEvidenceYn() {
		return evidenceYn;
	}

	public void setEvidenceYn(String evidenceYn) {
		this.evidenceYn = evidenceYn;
	}

	@Column(name = "cpd_remark")
	public String getCpdRemark() {
		return cpdRemark;
	}

	public void setCpdRemark(String cpdRemark) {
		this.cpdRemark = cpdRemark;
	}

	@Column(name = "CRT_USR", length = 30)
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "LST_UPD_USR", length = 30)
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name = "mandatory_yn")
	public String getMandatoryYn() {
		return mandatoryYn;
	}

	public void setMandatoryYn(String mandatoryYn) {
		this.mandatoryYn = mandatoryYn;
	}

	
}
