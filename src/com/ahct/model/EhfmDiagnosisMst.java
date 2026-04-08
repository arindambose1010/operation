package com.ahct.model;

import java.io.Serializable;
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
@Table(name = "EHFM_DIAGNOSIS_MST")
public class EhfmDiagnosisMst implements Serializable {

	private EhfmDiagnosisMstId id;
	private String diagnosisName;
	private String activeYn;
	private String langId;
	private String crtUsr;
	private Date  crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String medOrSurg;
	
	public EhfmDiagnosisMst()
	{}
	
	

	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="chapter", column=@Column(name="chapter", nullable=false) ), 
	   @AttributeOverride(name="diagnosisCode", column=@Column(name="diagnosis_code", nullable=false) )
	   } )
	public EhfmDiagnosisMstId getId() {
		return id;
	}
	public void setId(EhfmDiagnosisMstId id) {
		this.id = id;
	}
	@Column(name="diagnosis_name")
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	@Column(columnDefinition="char",name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="lang_id")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="crt_usr")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cr_dt")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="lst_upd_usr")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lst_upd_dt")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="medical_surg")
	public String getMedOrSurg() {
		return medOrSurg;
	}



	public void setMedOrSurg(String medOrSurg) {
		this.medOrSurg = medOrSurg;
	}
	
}
