package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_EMPLOYEE_DOC_ATTACH")
public class EhfEmployeeDocAttach implements java.io.Serializable{
private String employeeParntId;
private String actualName;
private String savedName;
private String crtUser;
private Date crtDt;
private String LstUpdUsr;
private Date lstUpdDt;
private String langId;
private String attachType;
private Long docSeqId;
private String docCount;
private String attachactiveYN;

@Column(name="EMPLOYEE_PRNT_ID", nullable = false)
public String getEmployeeParntId() {
	return employeeParntId;
}
public void setEmployeeParntId(String employeeParntId) {
	this.employeeParntId = employeeParntId;
}
@Column(name="ACTUAL_NAME", nullable = true)
public String getActualName() {
	return actualName;
}
public void setActualName(String actualName) {
	this.actualName = actualName;
}
@Column(name="SAVED_NAME", nullable = false)
public String getSavedName() {
	return savedName;
}
public void setSavedName(String savedName) {
	this.savedName = savedName;
}
@Column(name="CRT_USR", nullable = false)
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DT", nullable = false)
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="LST_UPD_USR", nullable = true)
public String getLstUpdUsr() {
	return LstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	LstUpdUsr = lstUpdUsr;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="LST_UPD_DT", nullable = true)
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
@Column(name="LANG_ID", nullable = true)
public String getLangId() {
	return langId;
}
public void setLangId(String langId) {
	this.langId = langId;
}
@Column(name="ATTACH_TYPE", nullable = true)
public String getAttachType() {
	return attachType;
}
public void setAttachType(String attachType) {
	this.attachType = attachType;
}
@Id
@Column(name="DOC_SEQ_ID", nullable = false)
public Long getDocSeqId() {
	return docSeqId;
}
public void setDocSeqId(Long docSeqId) {
	this.docSeqId = docSeqId;
}
@Column(name="DOC_COUNT", nullable = true)
public String getDocCount() {
	return docCount;
}
public void setDocCount(String docCount) {
	this.docCount = docCount;
}
@Column(name="ATTCH_ACTVE_YN", nullable = true)
public String getAttachactiveYN() {
	return attachactiveYN;
}
public void setAttachactiveYN(String attachactiveYN) {
	this.attachactiveYN = attachactiveYN;
}
public EhfEmployeeDocAttach(String employeeParntId, String actualName,
		String savedName, String crtUser, Date crtDt, String lstUpdUsr,
		Date lstUpdDt, String langId, String attachType, Long docSeqId,
		String docCount, String attachactiveYN) {
	super();
	this.employeeParntId = employeeParntId;
	this.actualName = actualName;
	this.savedName = savedName;
	this.crtUser = crtUser;
	this.crtDt = crtDt;
	LstUpdUsr = lstUpdUsr;
	this.lstUpdDt = lstUpdDt;
	this.langId = langId;
	this.attachType = attachType;
	this.docSeqId = docSeqId;
	this.docCount = docCount;
	this.attachactiveYN = attachactiveYN;
}
public EhfEmployeeDocAttach() {
	super();
}


}
