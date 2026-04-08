package com.ahct.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Ehf_Flag_Attach")
public class EhfFlagAttach implements java.io.Serializable
{
	 String flagDocId;		
	 String flagId;
	 String caseId;		
	 String attachPath; 
	 String locId;				
	 String langId;		
	 String crtUsr;
	 Date crtDt;
	 String lstUpdUsr;
	 Date lstUpdDt;
	 
	 
    @Id
	@Column(name="FLAG_DOCID", nullable = false)  
	public String getFlagDocId() {
		return flagDocId;
	}
	public void setFlagDocId(String flagDocId) {
		this.flagDocId = flagDocId;
	}

	@Column(name="FLAG_ID", nullable = false)  
	public String getFlagId() {
		return flagId;
	}
	public void setFlagId(String flagId) {
		this.flagId = flagId;
	}

	@Column(name="CASE_ID", nullable = false)  
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column(name="ATTACH_PATH", nullable = false)  
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
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
	
	public EhfFlagAttach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfFlagAttach(String flagDocId, String flagId,
			String caseId, String attachPath,String locId,String langId,
			Date crtDt, String crtUsr,String lstUpdUsr,Date lstUpdDt) {
		super();
		this.flagDocId=flagDocId;
		this.flagId = flagId;
		this.caseId = caseId;
		this.attachPath = attachPath;
		this.locId = locId;
		this.langId = langId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}
	
	
	}
