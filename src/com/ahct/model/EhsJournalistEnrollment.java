package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EHF_JRNLST_ENROLLMENT")
public class EhsJournalistEnrollment implements java.io.Serializable{
private String journalCode;
private String journalEnrollParntId;
private String journalMaritalStatus;
private String community;
private String nameOfMedia;
private String designation;
private String ofcHouseNo;
private String ofcStreetName;
private String ofcDistrict;
private String ofcMunicipality;
private String ofcVillage;
private String ofcState;
private String ofcPincode;
private String ofcEmail;
private String homeHouseNo;
private String homeStreetname;
private String homeDistrict;
private String homeMunc;
private String homeVillage;
private String homeState;
private String homePincode;
private String homeEmail;
private String rationCardColor;
private String rationCardNo;
private String journalistCardYN;
private String journalistCardNo;
private Date crtDt;
private String crtUsr;
private Date lstUpdDt;
private String lstUpdUsr;
private String ofcMobileNo;
private String homeMobileNo;
private String scheme;
private String accCardYN;
private String accCardNo;
private String epfCardYN;
private String epfCardNo;
private String identificationMarks1;
private String identificationMarks2;
private String eduQualification;
private Date jrnlsmStrtDate;
private String totExperience;
private Date retirementDate;
private String homeMandMunciSel;



@Column(name="SCHEME")
public String getScheme() {
	return scheme;
}
public void setScheme(String scheme) {
	this.scheme = scheme;
}
@Column(name="ACC_CARD_YN")
public String getAccCardYN() {
	return accCardYN;
}
public void setAccCardYN(String accCardYN) {
	this.accCardYN = accCardYN;
}
@Column(name="ACC_CARD_NO")
public String getAccCardNo() {
	return accCardNo;
}
public void setAccCardNo(String accCardNo) {
	this.accCardNo = accCardNo;
}
@Column(name="EPF_CARD_YN")
public String getEpfCardYN() {
	return epfCardYN;
}
public void setEpfCardYN(String epfCardYN) {
	this.epfCardYN = epfCardYN;
}
@Column(name="EPF_CARD_NO")
public String getEpfCardNo() {
	return epfCardNo;
}
public void setEpfCardNo(String epfCardNo) {
	this.epfCardNo = epfCardNo;
}
@Column(name="IDENTIFICATION_MARK1")
public String getIdentificationMarks1() {
	return identificationMarks1;
}
public void setIdentificationMarks1(String identificationMarks1) {
	this.identificationMarks1 = identificationMarks1;
}
@Column(name="IDENTIFICATION_MARK2")
public String getIdentificationMarks2() {
	return identificationMarks2;
}
public void setIdentificationMarks2(String identificationMarks2) {
	this.identificationMarks2 = identificationMarks2;
}
@Column(name="EDU_QUALIFICATION")
public String getEduQualification() {
	return eduQualification;
}
public void setEduQualification(String eduQualification) {
	this.eduQualification = eduQualification;
}
@Column(name="JRNLSM_STRT_ST")
public Date getJrnlsmStrtDate() {
	return jrnlsmStrtDate;
}
public void setJrnlsmStrtDate(Date jrnlsmStrtDate) {
	this.jrnlsmStrtDate = jrnlsmStrtDate;
}
@Column(name="TOT_EXPERIENCE")
public String getTotExperience() {
	return totExperience;
}
public void setTotExperience(String totExperience) {
	this.totExperience = totExperience;
}
@Column(name="RETIREMENT_DT")
public Date getRetirementDate() {
	return retirementDate;
}
public void setRetirementDate(Date retirementDate) {
	this.retirementDate = retirementDate;
}
@Column(name="ofc_mobile_no")
public String getOfcMobileNo() {
	return ofcMobileNo;
}
public void setOfcMobileNo(String ofcMobileNo) {
	this.ofcMobileNo = ofcMobileNo;
}
@Column(name="home_mobile_no")
public String getHomeMobileNo() {
	return homeMobileNo;
}
public void setHomeMobileNo(String homeMobileNo) {
	this.homeMobileNo = homeMobileNo;
}
@Column(name="journal_CODE")
public String getJournalCode() {
	return journalCode;
}
public void setJournalCode(String journalCode) {
	this.journalCode = journalCode;
}
@Id
@Column(name="journal_ENROLL_PRNT_ID")
public String getJournalEnrollParntId() {
	return journalEnrollParntId;
}
public void setJournalEnrollParntId(String journalEnrollParntId) {
	this.journalEnrollParntId = journalEnrollParntId;
}
@Column(name="journal_MARITAL_STAT")
public String getJournalMaritalStatus() {
	return journalMaritalStatus;
}
public void setJournalMaritalStatus(String journalMaritalStatus) {
	this.journalMaritalStatus = journalMaritalStatus;
}
@Column(name="COMMUnity")
public String getCommunity() {
	return community;
}
public void setCommunity(String community) {
	this.community = community;
}
@Column(name="name_of_media")
public String getNameOfMedia() {
	return nameOfMedia;
}
public void setNameOfMedia(String nameOfMedia) {
	this.nameOfMedia = nameOfMedia;
}
@Column(name="designation")
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
@Column(name="ofc_houseNo")
public String getOfcHouseNo() {
	return ofcHouseNo;
}
public void setOfcHouseNo(String ofcHouseNo) {
	this.ofcHouseNo = ofcHouseNo;
}
@Column(name="ofc_streetName")
public String getOfcStreetName() {
	return ofcStreetName;
}
public void setOfcStreetName(String ofcStreetName) {
	this.ofcStreetName = ofcStreetName;
}
@Column(name="ofc_district")
public String getOfcDistrict() {
	return ofcDistrict;
}
public void setOfcDistrict(String ofcDistrict) {
	this.ofcDistrict = ofcDistrict;
}
@Column(name="ofc_muncipality")
public String getOfcMunicipality() {
	return ofcMunicipality;
}
public void setOfcMunicipality(String ofcMunicipality) {
	this.ofcMunicipality = ofcMunicipality;
}
@Column(name="ofc_village")
public String getOfcVillage() {
	return ofcVillage;
}
public void setOfcVillage(String ofcVillage) {
	this.ofcVillage = ofcVillage;
}
@Column(name="ofc_state")
public String getOfcState() {
	return ofcState;
}
public void setOfcState(String ofcState) {
	this.ofcState = ofcState;
}
@Column(name="ofc_pincode")
public String getOfcPincode() {
	return ofcPincode;
}
public void setOfcPincode(String ofcPincode) {
	this.ofcPincode = ofcPincode;
}
@Column(name="ofc_email")
public String getOfcEmail() {
	return ofcEmail;
}
public void setOfcEmail(String ofcEmail) {
	this.ofcEmail = ofcEmail;
}
@Column(name="home_houseNo")
public String getHomeHouseNo() {
	return homeHouseNo;
}
public void setHomeHouseNo(String homeHouseNo) {
	this.homeHouseNo = homeHouseNo;
}
@Column(name="home_streetName")
public String getHomeStreetname() {
	return homeStreetname;
}
public void setHomeStreetname(String homeStreetname) {
	this.homeStreetname = homeStreetname;
}
@Column(name="home_district")
public String getHomeDistrict() {
	return homeDistrict;
}
public void setHomeDistrict(String homeDistrict) {
	this.homeDistrict = homeDistrict;
}
@Column(name="home_muncipality")
public String getHomeMunc() {
	return homeMunc;
}
public void setHomeMunc(String homeMunc) {
	this.homeMunc = homeMunc;
}
@Column(name="home_village")
public String getHomeVillage() {
	return homeVillage;
}
public void setHomeVillage(String homeVillage) {
	this.homeVillage = homeVillage;
}
@Column(name="home_state")
public String getHomeState() {
	return homeState;
}
public void setHomeState(String homeState) {
	this.homeState = homeState;
}
@Column(name="home_pincode")
public String getHomePincode() {
	return homePincode;
}
public void setHomePincode(String homePincode) {
	this.homePincode = homePincode;
}
@Column(name="home_email")
public String getHomeEmail() {
	return homeEmail;
}
public void setHomeEmail(String homeEmail) {
	this.homeEmail = homeEmail;
}
@Column(name="RATION_CARD_Type")
public String getRationCardColor() {
	return rationCardColor;
}
public void setRationCardColor(String rationCardColor) {
	this.rationCardColor = rationCardColor;
}
@Column(name="ration_card_no")
public String getRationCardNo() {
	return rationCardNo;
}
public void setRationCardNo(String rationCardNo) {
	this.rationCardNo = rationCardNo;
}
@Column(name="arogyasri_CARD_YN")
public String getJournalistCardYN() {
	return journalistCardYN;
}
public void setJournalistCardYN(String journalistCardYN) {
	this.journalistCardYN = journalistCardYN;
}
@Column(name="arogyasri_CARD_NO")
public String getJournalistCardNo() {
	return journalistCardNo;
}
public void setJournalistCardNo(String journalistCardNo) {
	this.journalistCardNo = journalistCardNo;
}


@Column(name="HOME_MAND_MUNCI_SEL")
public String getHomeMandMunciSel() {
	return homeMandMunciSel;
}
public void setHomeMandMunciSel(String homeMandMunciSel) {
	this.homeMandMunciSel = homeMandMunciSel;
}


@Column(name="crt_dt")
public Date getCrtDt() {
	return crtDt;
}

public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}

@Column(name="crt_user")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
@Column(name="lst_upd_dt")
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
@Column(name="lst_upd_usr")
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}

public EhsJournalistEnrollment(String journalCode, String journalEnrollParntId,
		String journalMaritalStatus, String community, String nameOfMedia,
		String designation, String ofcHouseNo, String ofcStreetName,
		String ofcDistrict, String ofcMunicipality, String ofcVillage,
		String ofcState, String ofcPincode, String ofcEmail,
		String homeHouseNo, String homeStreetname, String homeDistrict,
		String homeMunc, String homeVillage, String homeState,
		String homePincode, String homeEmail, String rationCardColor,
		String rationCardNo, String journalistCardYN, String journalistCardNo,
		Date crtDt, String crtUsr, Date lstUpdDt, String lstUpdUsr,String homeMandMunciSel) {
	super();
	this.journalCode = journalCode;
	this.journalEnrollParntId = journalEnrollParntId;
	this.journalMaritalStatus = journalMaritalStatus;
	this.community = community;
	this.nameOfMedia = nameOfMedia;
	this.designation = designation;
	this.ofcHouseNo = ofcHouseNo;
	this.ofcStreetName = ofcStreetName;
	this.ofcDistrict = ofcDistrict;
	this.ofcMunicipality = ofcMunicipality;
	this.ofcVillage = ofcVillage;
	this.ofcState = ofcState;
	this.ofcPincode = ofcPincode;
	this.ofcEmail = ofcEmail;
	this.homeHouseNo = homeHouseNo;
	this.homeStreetname = homeStreetname;
	this.homeDistrict = homeDistrict;
	this.homeMunc = homeMunc;
	this.homeVillage = homeVillage;
	this.homeState = homeState;
	this.homePincode = homePincode;
	this.homeEmail = homeEmail;
	this.rationCardColor = rationCardColor;
	this.rationCardNo = rationCardNo;
	this.journalistCardYN = journalistCardYN;
	this.journalistCardNo = journalistCardNo;
	this.crtDt = crtDt;
	this.crtUsr = crtUsr;
	this.lstUpdDt = lstUpdDt;
	this.lstUpdUsr = lstUpdUsr;
	this.homeMandMunciSel=homeMandMunciSel;
}
public EhsJournalistEnrollment() {
	super();
}



}
