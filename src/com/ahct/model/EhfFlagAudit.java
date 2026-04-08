package com.ahct.model;

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
@Table(name="Ehf_Flag_Audit")
public class EhfFlagAudit implements java.io.Serializable
{
	 private EhfFlagAuditId id;
	 String caseId;		
	 String remarks;
	 String actId;
	 String actUsrGrp;
	 String locId;				
	 String langId;		
	 String crtUsr;
	 Date crtDt;
	 
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "flagId", column = @Column(name = "FLAG_ID", nullable = false, length = 20)),
		@AttributeOverride(name = "actionOrder", column = @Column(name = "ACTION_ORDER", nullable = false, length = 15))
		})
    public EhfFlagAuditId getId() {
	return id;
	}

	public void setId(EhfFlagAuditId id) {
	this.id = id;
	}
	
	@Column(name="CASE_ID", nullable = false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	
	
	@Column(name="REMARKS")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="ACT_ID")
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	
	@Column(name="ACT_USR_GRP")
	public String getActUsrGrp() {
		return actUsrGrp;
	}

	public void setActUsrGrp(String actUsrGrp) {
		this.actUsrGrp = actUsrGrp;
	}
	
	
	
	@Column(name="LOC_ID")
	public String getLocId() {
		return locId;
	}


	public void setLocId(String locId) {
		this.locId = locId;
	}
	
	@Column(name="LANG_ID", nullable = false)
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	
	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	public EhfFlagAudit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfFlagAudit(EhfFlagAuditId id,String actUsrGrp, 
			String caseId,String remarks,String locId,String langId,
			Date crtDt, String crtUsr) {
		super();
		this.id = id;
		this.actUsrGrp=actUsrGrp;
		this.caseId = caseId;
		this.remarks = remarks;
		this.crtUsr = crtUsr;
		this.locId = locId;
		this.langId = langId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
	}

	
}
