package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="EHF_JRNLSTD_PATIENT_THERAPY")

public class EhfJrnlstPatientTherapy implements Serializable{

   private String patientId ;
   private String icdCatCode;
   private String provisionDiagnosis;
   private String crtUsr;
   private Date crtDt;
   private String lstUpdUsr;
   private Date lstUpdDt;
   private String activeYN;
  
   public EhfJrnlstPatientTherapy() {
   }
   
   
   @Id
   @Column(name="PATIENT_ID")
   public String getPatientId() {
	return patientId;
}

public void setPatientId(String patientId) {
	this.patientId = patientId;
}

@Column(name="ICD_CAT_CODE")
public String getIcdCatCode() {
	return icdCatCode;
}

public void setIcdCatCode(String icdCatCode) {
	this.icdCatCode = icdCatCode;
}

@Column(name="PROVISIONAL_DIAGNOSIS")
public String getProvisionDiagnosis() {
	return provisionDiagnosis;
}

public void setProvisionDiagnosis(String provisionDiagnosis) {
	this.provisionDiagnosis = provisionDiagnosis;
}

@Column(name="ACTIVE_YN")
public String getActiveYN() {
	return activeYN;
}

public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}

@Column(name="CRT_USR")
public String getCrtUsr() {
	return crtUsr;
}


public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}

@Column(name="CRT_DT")
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

@Column(name="LST_UPD_DT")
public Date getLstUpdDt() {
	return lstUpdDt;
}


public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
				

}

