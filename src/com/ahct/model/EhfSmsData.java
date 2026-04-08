package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_SMS_DATA")
public class EhfSmsData implements java.io.Serializable{
private Integer serialNo;
private String phoneNo;
private String smsText;
private String userId;
private String crtUsr;
private Date crtDt;
private String smsLoc;
private String ehfPasswod;
private String templateId;
@Id

@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq-gen")
@SequenceGenerator(name="seq-gen", sequenceName="SMS_SEQ", initialValue=20, allocationSize=1)
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

@Column(name="TEMPLATE_ID")
public String getTemplateId(){
	return templateId;
}
public void setTemplateId(String templateId){
	this.templateId = templateId;
}
public EhfSmsData(Integer serialNo, String phoneNo, String smsText,
		String userId, String crtUsr, Date crtDt, String smsLoc,
		String ehfPasswod, String templateId) {
	super();
	this.serialNo = serialNo;
	this.phoneNo = phoneNo;
	this.smsText = smsText;
	this.userId = userId;
	this.crtUsr = crtUsr;
	this.crtDt = crtDt;
	this.smsLoc = smsLoc;
	this.ehfPasswod = ehfPasswod;
	this.templateId = templateId;
}
public EhfSmsData() {
	super();
}


}
