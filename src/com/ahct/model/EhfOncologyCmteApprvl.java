package com.ahct.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EHF_ONCOLOGY_CMTE_APPRVL")
public class EhfOncologyCmteApprvl implements Serializable{
	private String patientId;
	private String proformaId;
	private String cardNo;
	private String status;
	private String nimsApprvl;
	private String mnjioApprvl;
	private Timestamp crtDt;
	private String crtUsr;
	private Timestamp lstUpdDt;
	private String lstUpdUsr;
	
	public EhfOncologyCmteApprvl(){
		
	}
	
	@Id
	@Column(name = "PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Column(name = "PROFORMA_ID")
	public String getProformaId() {
		return proformaId;
	}
	public void setProformaId(String proformaId) {
		this.proformaId = proformaId;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CARD_NO")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "NIMS_APPRVL")
	public String getNimsApprvl() {
		return nimsApprvl;
	}
	public void setNimsApprvl(String nimsApprvl) {
		this.nimsApprvl = nimsApprvl;
	}
	
	@Column(name = "MNJIO_APPRVL")
	public String getMnjioApprvl() {
		return mnjioApprvl;
	}
	public void setMnjioApprvl(String mnjioApprvl) {
		this.mnjioApprvl = mnjioApprvl;
	}
	
	@Column(name = "CRT_DT")
	public Timestamp getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Timestamp crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name = "CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Column(name = "LST_UPD_DT")
	public Timestamp getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Timestamp lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name = "LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
}
