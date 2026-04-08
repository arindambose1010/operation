package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EHF_JRNLST_FAMILY")
public class EhfJrnlstFamily implements java.io.Serializable{

private String journalEnrollId;
private String journalEnrollParntId;
private String journalEnrollSno;
private String enrollStatus;
private String aadharId;
private String aadharEid;
private String enrollName;
private Date enrollDob;
private String enrollGender;
private String enrollRelation;
private String enrollDisabYN;
private String enrollDisabiltyPercent;
private String crtUsr;
private Date crtDt;
private String lstUpdUsr;
private Date lstUpdDt;
private String enrollDisbCer;
private String enrollPhoto;
private String enrollDobCer;
private String enrollAadharCer;
private String aadharExists;
private Date enrollSubDt;
private Date rejDt;
private String displayFlag; 
private String journalCradNo;
private Date apprvDt;
private String spouseGovtEmpYN;
private String spouseEmpId;
private String spouseDept;
private String cardValid;

//Chandana - 8326 - for ABHA
private String ekycDoneYN;
private String abhaId;
private Date abhaUpdDt;
private String abhaUpdUsr;

/*private String houseNo;
private String street;
private String state;
private String district;
private String mandal;
private String village;
private String email;
private String pinCode;
private String mobileNo;*/





@Column(name="apprv_dt")
public Date getApprvDt() {
	return apprvDt;
}
@Column(name="card_valid")
public String getCardValid() {
	return cardValid;
}
public void setCardValid(String cardValid) {
	this.cardValid = cardValid;
}
@Column(name="SPOUSE_GOVTEMP_YN")
public String getSpouseGovtEmpYN() {
	return spouseGovtEmpYN;
}
public void setSpouseGovtEmpYN(String spouseGovtEmpYN) {
	this.spouseGovtEmpYN = spouseGovtEmpYN;
}
@Column(name="SPOUSE_EMPID")
public String getSpouseEmpId() {
	return spouseEmpId;
}
public void setSpouseEmpId(String spouseEmpId) {
	this.spouseEmpId = spouseEmpId;
}
@Column(name="SPOUSE_DEPT")
public String getSpouseDept() {
	return spouseDept;
}
public void setSpouseDept(String spouseDept) {
	this.spouseDept = spouseDept;
}
public void setApprvDt(Date apprvDt) {
	this.apprvDt = apprvDt;
}
@Column(name="journal_card_no")
public String getJournalCradNo() {
	return journalCradNo;
}
public void setJournalCradNo(String journalCradNo) {
	this.journalCradNo = journalCradNo;
}
@Id
@Column(name="journal_ENROLL_ID")
public String getJournalEnrollId() {
	return journalEnrollId;
}
public void setJournalEnrollId(String journalEnrollId) {
	this.journalEnrollId = journalEnrollId;
}
@Column(name="journal_ENROLL_PRNT_ID")
public String getJournalEnrollParntId() {
	return journalEnrollParntId;
}
public void setJournalEnrollParntId(String journalEnrollParntId) {
	this.journalEnrollParntId = journalEnrollParntId;
}
@Column(name="journal_ENROLL_SNO")
public String getJournalEnrollSno() {
	return journalEnrollSno;
}
public void setJournalEnrollSno(String journalEnrollSno) {
	this.journalEnrollSno = journalEnrollSno;
}
@Column(name="ENROLL_STATUS")
public String getEnrollStatus() {
	return enrollStatus;
}
public void setEnrollStatus(String enrollStatus) {
	this.enrollStatus = enrollStatus;
}
@Column(name="AADHAR_ID")
public String getAadharId() {
	return aadharId;
}
public void setAadharId(String aadharId) {
	this.aadharId = aadharId;
}
@Column(name="AADHAR_EID")
public String getAadharEid() {
	return aadharEid;
}
public void setAadharEid(String aadharEid) {
	this.aadharEid = aadharEid;
}
@Column(name="NAME")
public String getEnrollName() {
	return enrollName;
}
public void setEnrollName(String enrollName) {
	this.enrollName = enrollName;
}
@Column(name="DOB")
public Date getEnrollDob() {
	return enrollDob;
}
public void setEnrollDob(Date enrollDob) {
	this.enrollDob = enrollDob;
}
@Column(name="GENDER")
public String getEnrollGender() {
	return enrollGender;
}
public void setEnrollGender(String enrollGender) {
	this.enrollGender = enrollGender;
}
@Column(name="RELATION")
public String getEnrollRelation() {
	return enrollRelation;
}
public void setEnrollRelation(String enrollRelation) {
	this.enrollRelation = enrollRelation;
}
@Column(name="DISABLED")
public String getEnrollDisabYN() {
	return enrollDisabYN;
}
public void setEnrollDisabYN(String enrollDisabYN) {
	this.enrollDisabYN = enrollDisabYN;
}
@Column(name="DISABILITY_PERCENT")
public String getEnrollDisabiltyPercent() {
	return enrollDisabiltyPercent;
}
public void setEnrollDisabiltyPercent(String enrollDisabiltyPercent) {
	this.enrollDisabiltyPercent = enrollDisabiltyPercent;
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
@Column(name="DISABILITY_CERF")
public String getEnrollDisbCer() {
	return enrollDisbCer;
}
public void setEnrollDisbCer(String enrollDisbCer) {
	this.enrollDisbCer = enrollDisbCer;
}
@Column(name="PHOTO")
public String getEnrollPhoto() {
	return enrollPhoto;
}
public void setEnrollPhoto(String enrollPhoto) {
	this.enrollPhoto = enrollPhoto;
}
@Column(name="DOB_CERTI")
public String getEnrollDobCer() {
	return enrollDobCer;
}
public void setEnrollDobCer(String enrollDobCer) {
	this.enrollDobCer = enrollDobCer;
}
@Column(name="AADHAR_CERTI")
public String getEnrollAadharCer() {
	return enrollAadharCer;
}
public void setEnrollAadharCer(String enrollAadharCer) {
	this.enrollAadharCer = enrollAadharCer;
}
@Column(name="AADHAR_EXISTS")
public String getAadharExists() {
	return aadharExists;
}
public void setAadharExists(String aadharExists) {
	this.aadharExists = aadharExists;
}
@Column(name="SUBMITTED_DATE")
public Date getEnrollSubDt() {
	return enrollSubDt;
}
public void setEnrollSubDt(Date enrollSubDt) {
	this.enrollSubDt = enrollSubDt;
}
@Column(name="rej_date")
public Date getRejDt() {
	return rejDt;
}
public void setRejDt(Date rejDt) {
	this.rejDt = rejDt;
}
@Column(name="DISPLAY_FLG")
public String getDisplayFlag() {
	return displayFlag;
}
public void setDisplayFlag(String displayFlag) {
	this.displayFlag = displayFlag;
}
//Chandana - 8326 - For ABHA
@Column(name="EKYC_DONE_YN")
public String getEkycDoneYN() {
	return ekycDoneYN;
}
public void setEkycDoneYN(String ekycDoneYN) {
	this.ekycDoneYN = ekycDoneYN;
}
@Column(name="ABHA_ID")
public String getAbhaId() {
	return abhaId;
}
public void setAbhaId(String abhaId) {
	this.abhaId = abhaId;
}
@Column(name="ABHA_UPD_DT")
public Date getAbhaUpdDt() {
	return abhaUpdDt;
}
public void setAbhaUpdDt(Date abhaUpdDt) {
	this.abhaUpdDt = abhaUpdDt;
}
@Column(name="ABHA_UPD_USR")
public String getAbhaUpdUsr() {
	return abhaUpdUsr;
}
public void setAbhaUpdUsr(String abhaUpdUsr) {
	this.abhaUpdUsr = abhaUpdUsr;
}
public EhfJrnlstFamily(String journalEnrollId,
		String journalEnrollParntId, String journalEnrollSno,
		String enrollStatus, String aadharId, String aadharEid,
		String enrollName, Date enrollDob, String enrollGender,
		String enrollRelation,  String enrollDisabYN,
		String enrollDisabiltyPercent, String crtUsr, Date crtDt,
		String lstUpdUsr, Date lstUpdDt, String enrollDisbCer,
		String enrollPhoto, String enrollDobCer, String enrollAadharCer,
		String aadharExists, Date enrollSubDt, Date rejDt, String displayFlag) {
	super();
	this.journalEnrollId = journalEnrollId;
	this.journalEnrollParntId = journalEnrollParntId;
	this.journalEnrollSno = journalEnrollSno;
	this.enrollStatus = enrollStatus;
	this.aadharId = aadharId;
	this.aadharEid = aadharEid;
	this.enrollName = enrollName;
	this.enrollDob = enrollDob;
	this.enrollGender = enrollGender;
	this.enrollRelation = enrollRelation;
	this.enrollDisabYN = enrollDisabYN;
	this.enrollDisabiltyPercent = enrollDisabiltyPercent;
	this.crtUsr = crtUsr;
	this.crtDt = crtDt;
	this.lstUpdUsr = lstUpdUsr;
	this.lstUpdDt = lstUpdDt;
	this.enrollDisbCer = enrollDisbCer;
	this.enrollPhoto = enrollPhoto;
	this.enrollDobCer = enrollDobCer;
	this.enrollAadharCer = enrollAadharCer;
	this.aadharExists = aadharExists;
	this.enrollSubDt = enrollSubDt;
	this.rejDt = rejDt;
	this.displayFlag = displayFlag;
}
public EhfJrnlstFamily() {
	super();
}


}
