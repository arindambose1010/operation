package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="EHF_JRNLSTD_ADM_EST_DTLS")

public class EhfJrnlstdAdmEstDtls implements Serializable{

   private String patientId ;
   private String crtUsr;
   private Date crtDt;
   private String lstUpdUsr;
   private Date lstUpdDt;
   private Date admissionDate;
   private String admissionNote;
   private long estAmount;
   private String docPath;
   private String fileName;
   //private String attachment;
  
   public EhfJrnlstdAdmEstDtls() {
   }
   
   
   @Id
   @Column(name="PATIENT_ID")
   public String getPatientId() {
	return patientId;
}

public void setPatientId(String patientId) {
	this.patientId = patientId;
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

@Column(name="ADM_DATE")
public Date getAdmissionDate() {
	return admissionDate;
}

public void setAdmissionDate(Date admissionDate) {
	this.admissionDate = admissionDate;
}

@Column(name="ADM_NOTE")
public String getAdmissionNote() {
	return admissionNote;
}

public void setAdmissionNote(String admissionNote) {
	this.admissionNote = admissionNote;
}

@Column(name="EST_AMT")
public long getEstAmount() {
	return estAmount;
}

public void setEstAmount(long estAmount) {
	this.estAmount = estAmount;
}

@Column(name="DOC_PATH")
public String getDocPath() {
	return docPath;
}

public void setDocPath(String docPath) {
	this.docPath = docPath;
}

@Column(name="FILE_NAME")
public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}



}


