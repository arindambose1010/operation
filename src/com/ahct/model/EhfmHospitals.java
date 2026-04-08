package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_HOSPITALS"
)
public class EhfmHospitals implements java.io.Serializable{
private String hospId;
private String hospName;
private String hospDispCode;
private String hospCity;
private String houseNumber;
private String street;
private String hospFaxNo;
private String hospEmail;
private String cugNo;
private String hospContactPerson;
private String hospContactNo;
private String hospActiveYN;
private String langId;
private String hospDist;
private char hospType;
private String hospEmpnlRefNum;
private String hospCategory;
private Integer onbedPatients;
private String govHopsType;
private Date hospEmpnlDate;
private String hospEstYear;
private String hospMacId;
private String biomReq;
private String depoId;
private String mandalMunicipalitySel;
private String mandalMuncipality;
private String bedStrength;
private String payment;
private String village;
private String crtUser;
private Date crtDt;
private String lstUpdUser;
private Date lstUpdDt;
private String nabhFlg;
private String stateCode;
private String ahcFlag;
private String chronicFlag;
private String scheme;
private String taashaHosp;
private String oldHospDistCode;
private String hubSpoke;


@Column(name="scheme")
public String getScheme() {
	return scheme;
}
public void setScheme(String scheme) {
	this.scheme = scheme;
}
@Column(name="nabh_flg")
public String getNabhFlg() {
	return nabhFlg;
}
public void setNabhFlg(String nabhFlg) {
	this.nabhFlg = nabhFlg;
}
@Id
@Column(name="hosp_id", nullable=false)
public String getHospId() {
	return hospId;
}
public void setHospId(String hospId) {
	this.hospId = hospId;
}
@Column(name="hosp_name", nullable=true)
public String getHospName() {
	return hospName;
}
public void setHospName(String hospName) {
	this.hospName = hospName;
}
@Column(name="hosp_disp_code", nullable=true)
public String getHospDispCode() {
	return hospDispCode;
}
public void setHospDispCode(String hospDispCode) {
	this.hospDispCode = hospDispCode;
}
@Column(name="hosp_city", nullable=true)
public String getHospCity() {
	return hospCity;
}
public void setHospCity(String hospCity) {
	this.hospCity = hospCity;
}

@Column(name="house_no", nullable=true)
public String getHouseNumber() {
	return houseNumber;
}
public void setHouseNumber(String houseNumber) {
	this.houseNumber = houseNumber;
}
@Column(name="street", nullable=true)
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
@Column(name="hosp_fax_no", nullable=true)
public String getHospFaxNo() {
	return hospFaxNo;
}
public void setHospFaxNo(String hospFaxNo) {
	this.hospFaxNo = hospFaxNo;
}
@Column(name="hosp_email", nullable=true)
public String getHospEmail() {
	return hospEmail;
}
public void setHospEmail(String hospEmail) {
	this.hospEmail = hospEmail;
}
@Column(name="cug_no", nullable=true)
public String getCugNo() {
	return cugNo;
}
public void setCugNo(String cugNo) {
	this.cugNo = cugNo;
}
@Column(name="hosp_contact_person", nullable=true)
public String getHospContactPerson() {
	return hospContactPerson;
}
public void setHospContactPerson(String hospContactPerson) {
	this.hospContactPerson = hospContactPerson;
}
@Column(name="hosp_contact_no", nullable=true)
public String getHospContactNo() {
	return hospContactNo;
}
public void setHospContactNo(String hospContactNo) {
	this.hospContactNo = hospContactNo;
}
@Column(name="hosp_active_yn", nullable=true)
public String getHospActiveYN() {
	return hospActiveYN;
}
public void setHospActiveYN(String hospActiveYN) {
	this.hospActiveYN = hospActiveYN;
}
@Column(name="lang_id", nullable=true)
public String getLangId() {
	return langId;
}
public void setLangId(String langId) {
	this.langId = langId;
}
@Column(name="hosp_dist", nullable=true)
public String getHospDist() {
	return hospDist;
}
public void setHospDist(String hospDist) {
	this.hospDist = hospDist;
}
@Column(name="hosp_type", nullable=true)
public char getHospType() {
	return hospType;
}
public void setHospType(char hospType) {
	this.hospType = hospType;
}
@Column(name="hosp_empnl_ref_num", nullable=true)
public String getHospEmpnlRefNum() {
	return hospEmpnlRefNum;
}
public void setHospEmpnlRefNum(String hospEmpnlRefNum) {
	this.hospEmpnlRefNum = hospEmpnlRefNum;
}
@Column(name="hosp_category", nullable=true)
public String getHospCategory() {
	return hospCategory;
}
public void setHospCategory(String hospCategory) {
	this.hospCategory = hospCategory;
}
@Column(name="onbed_patients", nullable=true)
public Integer getOnbedPatients() {
	return onbedPatients;
}
public void setOnbedPatients(Integer onbedPatients) {
	this.onbedPatients = onbedPatients;
}
@Column(name="govt_hosp_type", nullable=true)
public String getGovHopsType() {
	return govHopsType;
}
public void setGovHopsType(String govHopsType) {
	this.govHopsType = govHopsType;
}
@Column(name="hosp_empnl_date", nullable=true)
public Date getHospEmpnlDate() {
	return hospEmpnlDate;
}
public void setHospEmpnlDate(Date hospEmpnlDate) {
	this.hospEmpnlDate = hospEmpnlDate;
}
@Column(name="hosp_estab_yr", nullable=true)
public String getHospEstYear() {
	return hospEstYear;
}
public void setHospEstYear(String hospEstYear) {
	this.hospEstYear = hospEstYear;
}
@Column(name="hosp_macid", nullable=true)
public String getHospMacId() {
	return hospMacId;
}
public void setHospMacId(String hospMacId) {
	this.hospMacId = hospMacId;
}
@Column(name="biom_required", nullable=true)
public String getBiomReq() {
	return biomReq;
}
public void setBiomReq(String biomReq) {
	this.biomReq = biomReq;
}
@Column(name="depo_id", nullable=true)
public String getDepoId() {
	return depoId;
}
public void setDepoId(String depoId) {
	this.depoId = depoId;
}
@Column(name="mandal_municipality_sel", nullable=true)
public String getMandalMunicipalitySel() {
	return mandalMunicipalitySel;
}
public void setMandalMunicipalitySel(String mandalMunicipalitySel) {
	this.mandalMunicipalitySel = mandalMunicipalitySel;
}
@Column(name="mandal_municipality", nullable=true)
public String getMandalMuncipality() {
	return mandalMuncipality;
}
public void setMandalMuncipality(String mandalMuncipality) {
	this.mandalMuncipality = mandalMuncipality;
}
@Column(name="bed_strength", nullable=true)
public String getBedStrength() {
	return bedStrength;
}
public void setBedStrength(String bedStrength) {
	this.bedStrength = bedStrength;
}
@Column(name="crt_usr", nullable=true)
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt", nullable=true)
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="lst_upd_usr", nullable=true)
public String getLstUpdUser() {
	return lstUpdUser;
}
public void setLstUpdUser(String lstUpdUser) {
	this.lstUpdUser = lstUpdUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="lst_upd_dt", nullable=true)
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}

@Column(name="payment", nullable=true)
public String getPayment() {
	return payment;
}
public void setPayment(String payment) {
	this.payment = payment;
}
@Column(name="village", nullable=true)
public String getVillage() {
	return village;
}
public void setVillage(String village) {
	this.village = village;
}
@Column(name="state_code", nullable=true)
public String getStateCode() {
	return stateCode;
}
public void setStateCode(String stateCode) {
	this.stateCode = stateCode;
}

@Column(name="ahc_flag", nullable=true)
public String getAhcFlag() {
	return ahcFlag;
}
public void setAhcFlag(String ahcFlag) {
	this.ahcFlag = ahcFlag;
}

@Column(name="chronic_flag", nullable=true)
public String getChronicFlag() {
	return chronicFlag;
}
public void setChronicFlag(String chronicFlag) {
	this.chronicFlag = chronicFlag;
}

@Column(name="taasha_hosp",nullable=true)
public String getTaashaHosp() {
	return taashaHosp;
}
public void setTaashaHosp(String taashaHosp) {
	this.taashaHosp = taashaHosp;
}

@Column(name="old_district_code",nullable=true)
public String getOldHospDistCode() {
	return oldHospDistCode;
}
public void setOldHospDistCode(String oldHospDistCode) {
	this.oldHospDistCode = oldHospDistCode;
}
@Column(name="hub_spoke")
public String getHubSpoke() {
	return hubSpoke;
}
public void setHubSpoke(String hubSpoke) {
	this.hubSpoke = hubSpoke;
}
public EhfmHospitals(String hospId, String hospName, String hospDispCode,
		String hospCity, String houseNumber, String street, String hospFaxNo,
		String hospEmail, String cugNo, String hospContactPerson,
		String hospContactNo, String hospActiveYN, String langId,
		String hospDist, char hospType, String hospEmpnlRefNum,
		String hospCategory, Integer onbedPatients, String govHopsType,
		Date hospEmpnlDate, String hospEstYear, String hospMacId,
		String biomReq, String depoId, String mandalMunicipalitySel,
		String mandalMuncipality, String bedStrength, String crtUser,
		Date crtDt, String lstUpdUser, Date lstUpdDt,String stateCode,String taashaHosp) {
	super();
	this.hospId = hospId;
	this.hospName = hospName;
	this.hospDispCode = hospDispCode;
	this.hospCity = hospCity;
	this.houseNumber = houseNumber;
	this.street = street;
	this.hospFaxNo = hospFaxNo;
	this.hospEmail = hospEmail;
	this.cugNo = cugNo;
	this.hospContactPerson = hospContactPerson;
	this.hospContactNo = hospContactNo;
	this.hospActiveYN = hospActiveYN;
	this.langId = langId;
	this.hospDist = hospDist;
	this.hospType = hospType;
	this.hospEmpnlRefNum = hospEmpnlRefNum;
	this.hospCategory = hospCategory;
	this.onbedPatients = onbedPatients;
	this.govHopsType = govHopsType;
	this.hospEmpnlDate = hospEmpnlDate;
	this.hospEstYear = hospEstYear;
	this.hospMacId = hospMacId;
	this.biomReq = biomReq;
	this.depoId = depoId;
	this.mandalMunicipalitySel = mandalMunicipalitySel;
	this.mandalMuncipality = mandalMuncipality;
	this.bedStrength = bedStrength;
	this.crtUser = crtUser;
	this.crtDt = crtDt;
	this.lstUpdUser = lstUpdUser;
	this.lstUpdDt = lstUpdDt;
	this.stateCode=stateCode;
	this.taashaHosp=taashaHosp;
	this.oldHospDistCode=oldHospDistCode;
	this.hubSpoke=hubSpoke;

}
public EhfmHospitals() {
	super();
}






}
