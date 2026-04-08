package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_SMS_DATA")
public class EhfmSmsData implements java.io.Serializable{
private Integer serialNo;
private String phoneNo;
private String smsText;
private String userId;
private String crtUsr;
private Date crtDt;
private String smsLoc;
private String ehfPasswod;
@Id
@Column(name="SERIAL_NO")
public Integer getSerialNo() {
	return serialNo;
}
public void setSerialNo(Integer serialNo) {
	this.serialNo = serialNo;
}
@Column(name="PHONE_NO")
public String getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}
@Column(name="SMS_TEXT")
public String getSmsText() {
	return smsText;
}
public void setSmsText(String smsText) {
	this.smsText = smsText;
}
@Column(name="USER_ID")
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
@Column(name="CRT_USR")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DT")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="SMS_LOC")
public String getSmsLoc() {
	return smsLoc;
}
public void setSmsLoc(String smsLoc) {
	this.smsLoc = smsLoc;
}
@Column(name="EHF_PASSWORD")
public String getEhfPasswod() {
	return ehfPasswod;
}
public void setEhfPasswod(String ehfPasswod) {
	this.ehfPasswod = ehfPasswod;
}
public EhfmSmsData(Integer serialNo, String phoneNo, String smsText,
		String userId, String crtUsr, Date crtDt, String smsLoc,
		String ehfPasswod) {
	super();
	this.serialNo = serialNo;
	this.phoneNo = phoneNo;
	this.smsText = smsText;
	this.userId = userId;
	this.crtUsr = crtUsr;
	this.crtDt = crtDt;
	this.smsLoc = smsLoc;
	this.ehfPasswod = ehfPasswod;
}
public EhfmSmsData() {
	super();
}


}
