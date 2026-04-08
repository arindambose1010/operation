package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_CLAIM_CTD_CHKLST")
public class EhfClaimCtdChklst implements java.io.Serializable{
	
	private String caseId;
	private String agreeYn;
	private String casemgmtYn;
	private String evidenceYn;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String remarks;
	private String chRemarks;
	private String mandatoryYn;
	
	@Id
	@Column(name = "case_id", nullable = false)
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	
	@Column(name = "agree_yn")
	public String getAgreeYn() {
		return agreeYn;
	}

	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
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
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "ch_remark")
	public String getChRemarks() {
		return chRemarks;
	}

	public void setChRemarks(String chRemarks) {
		this.chRemarks = chRemarks;
	}
	@Column(name = "mandatory_yn")
	public String getMandatoryYn() {
		return mandatoryYn;
	}

	public void setMandatoryYn(String mandatoryYn) {
		this.mandatoryYn = mandatoryYn;
	}
	
	
	
}
