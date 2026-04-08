package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_DME_QUERY")
public class EhfmDmeQuery implements Serializable{
	
	private String caseId;
    private String caseStatus;
	private String Remarks;
	private Date crtDt;
	private String crtUsr;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String eoRemarks;
	
	@Id
    @Column(name="CASE_ID", nullable = false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT",nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="CRT_USR",nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
    @Column(name="CASE_STATUS", nullable = false)
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
    @Column(name="REMARKS", nullable = false)
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="EO_REMARKS")
	public String getEoRemarks() {
		return eoRemarks;
	}
	public void setEoRemarks(String eoRemarks) {
		this.eoRemarks = eoRemarks;
	}
	
	
}
