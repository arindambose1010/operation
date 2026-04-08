package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHFM_COMORBID")
public class EhfmComorbid implements java.io.Serializable{
private String comorbidId;
private String comorbidName;
private String comorbidVal;
private String crtUser;
private Date crtDt;
private String lstUpdUsr;
private Date lstUpdt;
@Id
@Column(name="comorbid_id")
public String getComorbidId() {
	return comorbidId;
}
public void setComorbidId(String comorbidId) {
	this.comorbidId = comorbidId;
}
@Column(name="comorbid_name")
public String getComorbidName() {
	return comorbidName;
}
public void setComorbidName(String comorbidName) {
	this.comorbidName = comorbidName;
}
@Column(name="comorbid_value")
public String getComorbidVal() {
	return comorbidVal;
}
public void setComorbidVal(String comorbidVal) {
	this.comorbidVal = comorbidVal;
}
@Column(name="crt_usr")
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt")
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
public Date getLstUpdt() {
	return lstUpdt;
}
public void setLstUpdt(Date lstUpdt) {
	this.lstUpdt = lstUpdt;
}
public EhfmComorbid() {
	super();
}



}
