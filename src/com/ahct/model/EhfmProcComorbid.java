package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="EHFM_PROC_COMORBID")
public class EhfmProcComorbid implements java.io.Serializable{
private String icdProcCode;
private String comorbidId;
private String activeYN;
private String crtUsr;
private Date crtDt;
@Id
@Column(name="icd_proc_code")
public String getIcdProcCode() {
	return icdProcCode;
}
public void setIcdProcCode(String icdProcCode) {
	this.icdProcCode = icdProcCode;
}
@Column(name="comorbid_id")
public String getComorbidId() {
	return comorbidId;
}
public void setComorbidId(String comorbidId) {
	this.comorbidId = comorbidId;
}
@Column(name="active_yn")
public String getActiveYN() {
	return activeYN;
}
public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}
@Column(name="crt_usr")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
@Column(name="crt_dt")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
public EhfmProcComorbid() {
	super();
}


}
