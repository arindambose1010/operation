package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Chandana - 8326 - The persistent class for the ABHA_RESPONSE database table.
 * 
 */
@Entity
@Table(name="ABHA_RESPONSE")
@NamedQuery(name="AbhaResponse.findAll", query="SELECT a FROM AbhaResponse a")
public class AbhaResponse implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CARD_ID")
	private String cardId;
	
	@Column(name="CARD_TYPE")
	private String cardType;

	@Column(name="ABHA_ID")
	private String abhaId;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="DOB")
	private String dob;
	
	@Column(name="ADHAAR_ADDR")
	private String aadharAddr;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="PROFILE_PHOTO_PATH")
	private String profilePhotoPath;
	
	@Column(name="EKYC_PHOTO_PATH")
	private String ekycPhotoPath;
	
	@Column(name="IS_ABHA_NEW")
	private String isAbhaNew;

	@Column(name="IS_EKYC_VERIFIED")
	private String isEkycVerified;
	
	@Column(name="ACTIVE_YN")
	private String activeYn;

	@Temporal(TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Temporal(TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;
	
	@Column(name="APP_OPR_NABH")
	private String appOprNabh;
	
	@Column(name="ABHA_TYPE")
	private String abhaType;
	
	@Column(name="ABHA_PREF_ADDR")
	private String abhaPrefAddr;
	
	@Column(name="AADHAAR_CARD")
	private String aadhaarCard;
	
	@Column(name="AUTH_TYPE")
	private String authType;
	
	@Column(name="PINCODE")
	private String pinCode;
	
	@Column(name="DISTRICT")
	private String district;

	public AbhaResponse() {
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAbhaId() {
		return this.abhaId;
	}

	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}

	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getEkycPhotoPath() {
		return this.ekycPhotoPath;
	}

	public void setEkycPhotoPath(String ekycPhotoPath) {
		this.ekycPhotoPath = ekycPhotoPath;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsAbhaNew() {
		return this.isAbhaNew;
	}

	public void setIsAbhaNew(String isAbhaNew) {
		this.isAbhaNew = isAbhaNew;
	}

	public String getIsEkycVerified() {
		return this.isEkycVerified;
	}

	public void setIsEkycVerified(String isEkycVerified) {
		this.isEkycVerified = isEkycVerified;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfilePhotoPath() {
		return this.profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}

	public String getAppOprNabh() {
		return appOprNabh;
	}

	public void setAppOprNabh(String appOprNabh) {
		this.appOprNabh = appOprNabh;
	}

	public String getAbhaType() {
		return abhaType;
	}

	public void setAbhaType(String abhaType) {
		this.abhaType = abhaType;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAadharAddr() {
		return aadharAddr;
	}

	public void setAadharAddr(String aadharAddr) {
		this.aadharAddr = aadharAddr;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAbhaPrefAddr() {
		return abhaPrefAddr;
	}

	public void setAbhaPrefAddr(String abhaPrefAddr) {
		this.abhaPrefAddr = abhaPrefAddr;
	}

	public String getAadhaarCard() {
		return aadhaarCard;
	}

	public void setAadhaarCard(String aadhaarCard) {
		this.aadhaarCard = aadhaarCard;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	

}

