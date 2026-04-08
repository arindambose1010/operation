package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_CASE_ENHANCEMENT_DTLS")
public class EhfCaseEnhancementDtls implements java.io.Serializable{
private Long sno;
private String caseId;
private String type;
private String enhCode;
private String enhQuantCode;
private Integer noOfUnits;
private double amount;
private String crtUser;
private Date crtDt;
private String lstUpdUser;
private Date lstUpdDt;
private String activeYN;
private String attachPath;
private String attachName;
@Id
@Column(name="SNO")
public Long getSno() {
	return sno;
}
public void setSno(Long sno) {
	this.sno = sno;
}
@Column(name="CASE_ID")
public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
@Column(name="TYPE")
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
@Column(name="ENH_CODE")
public String getEnhCode() {
	return enhCode;
}
public void setEnhCode(String enhCode) {
	this.enhCode = enhCode;
}
@Column(name="ENH_QUAN_CODE")
public String getEnhQuantCode() {
	return enhQuantCode;
}
public void setEnhQuantCode(String enhQuantCode) {
	this.enhQuantCode = enhQuantCode;
}
@Column(name="NO_OF_UNITS")
public Integer getNoOfUnits() {
	return noOfUnits;
}
public void setNoOfUnits(Integer noOfUnits) {
	this.noOfUnits = noOfUnits;
}
@Column(name="AMOUNT")
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
@Column(name="CRT_USR")
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DT")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="LST_UPD_USR")
public String getLstUpdUser() {
	return lstUpdUser;
}
public void setLstUpdUser(String lstUpdUser) {
	this.lstUpdUser = lstUpdUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="LST_UPD_DT")
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
@Column(name="active_y_n")
public String getActiveYN() {
	return activeYN;
}
public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}
@Column(name="attach_path")
public String getAttachPath() {
	return attachPath;
}
public void setAttachPath(String attachPath) {
	this.attachPath = attachPath;
}
@Column(name="attach_name")
public String getAttachName() {
	return attachName;
}
public void setAttachName(String attachName) {
	this.attachName = attachName;
}
public EhfCaseEnhancementDtls(Long sno, String caseId, String type,
		String enhCode, String enhQuantCode, Integer noOfUnits, double amount,
		String crtUser, Date crtDt, String lstUpdUser, Date lstUpdDt,String activeYN,String attachPath,String attachName) {
	super();
	this.sno = sno;
	this.caseId = caseId;
	this.type = type;
	this.enhCode = enhCode;
	this.enhQuantCode = enhQuantCode;
	this.noOfUnits = noOfUnits;
	this.amount = amount;
	this.crtUser = crtUser;
	this.crtDt = crtDt;
	this.lstUpdUser = lstUpdUser;
	this.lstUpdDt = lstUpdDt;
	this.activeYN = activeYN;
	this.attachPath = attachPath;
	this.attachName = attachName;
}
public EhfCaseEnhancementDtls() {
	super();
}


}
