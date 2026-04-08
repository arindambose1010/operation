package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehfm_user_dtls")
public class EhfmUserDtls implements Serializable {
	@Id
    @Column(name="user_id", nullable=false)
	private String userId;
	@Column(name="first_name", nullable=false)
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="middle_name")
	private String middleName;
	@Column(name="email_id")
	private String emailId;
	@Column(name="dob")
	private Date dob;
	@Column(name="addr1")
	private String addr1;
	@Column(name="addr2")
	private String addr2;
	@Column(name="city", nullable=false)
	private String city;
	@Column(name="district")
	private String district;
	@Column(name="state", nullable=false)
	private String state;
	@Column(name="country", nullable=false)
	private String country;
	@Column(name="pin")
	private String pin;
	@Column(name="city1")
	private String city1;
	@Column(name="state1")
	private String state1;
	@Column(name="pin1")
	private String pin1;
	@Column(name="country1")
	private String country1;
	@Column(name="phone_no")
	private String phoneNo;
	@Column(name="zone")
	private String zone;
	@Column(name="address")
	private String address;
	@Column(name="gender")
	private String gender;
	@Column(name="lang_id", nullable=false)
	private String langId;
	@Column(name="loc_id", nullable=false)
	private String locId;
	@Column(name="crt_dt")
	private Date crtDt;
	@Column(name="crt_usr", nullable=false)
	private String crtUsr;
	@Column(name="lst_upd_usr")
	private String lstUpdUsr;
	@Column(name="lst_upd_dt")
	private Date lastUpdDt;
	@Column(name="PRSNT_MAND")
	private String mandMunci;
	@Column(name="PRSNT_MANDALS")
	private String addrMandMunci;
	@Column(name="PERMNT_MAND")
	private String mandMunci1;
	@Column(name="PERMNT_MANDALS")
	private String addrMandMunci1;
	@Column(name="DISTRICT1")
	private String district1;
	@Column(name="PHOTO")
	private String photo;
	
	
	
	
	public EhfmUserDtls(String userId, String firstName, String lastName,
			String middleName, String emailId, Date dob, String addr1,
			String addr2, String city, String district, String state,
			String country, String pin, String city1, String state1,
			String pin1, String country1, String phoneNo, String zone,
			String address, String gender, String langId, String locId,
			Date crtDt, String crtUsr, String lstUpdUsr, Date lastUpdDt,
			String mandMunci,String addrMandMunci,String mandMunci1,
			String addrMandMunci1, String district1) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.emailId = emailId;
		this.dob = dob;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.city = city;
		this.district = district;
		this.state = state;
		this.country = country;
		this.pin = pin;
		this.city1 = city1;
		this.state1 = state1;
		this.pin1 = pin1;
		this.country1 = country1;
		this.phoneNo = phoneNo;
		this.zone = zone;
		this.address = address;
		this.gender = gender;
		this.langId = langId;
		this.locId = locId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lastUpdDt = lastUpdDt;
		this.mandMunci=mandMunci;
		this.addrMandMunci=addrMandMunci;
		this.mandMunci1=mandMunci1;
		this.addrMandMunci1=addrMandMunci1;
		this.district1=district1;
	}
	
	
	
	public EhfmUserDtls() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getMandMunci1() {
		return mandMunci1;
	}



	public void setMandMunci1(String mandMunci1) {
		this.mandMunci1 = mandMunci1;
	}



	public String getAddrMandMunci1() {
		return addrMandMunci1;
	}



	public void setAddrMandMunci1(String addrMandMunci1) {
		this.addrMandMunci1 = addrMandMunci1;
	}



	public String getDistrict1() {
		return district1;
	}



	public void setDistrict1(String district1) {
		this.district1 = district1;
	}



	public String getMandMunci() {
		return mandMunci;
	}

	public void setMandMunci(String mandMunci) {
		this.mandMunci = mandMunci;
	}

	public String getAddrMandMunci() {
		return addrMandMunci;
	}

	public void setAddrMandMunci(String addrMandMunci) {
		this.addrMandMunci = addrMandMunci;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
	}
	public String getPin1() {
		return pin1;
	}
	public void setPin1(String pin1) {
		this.pin1 = pin1;
	}
	public String getCountry1() {
		return country1;
	}
	public void setCountry1(String country1) {
		this.country1 = country1;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	public Date getLastUpdDt() {
		return lastUpdDt;
	}
	public void setLastUpdDt(Date lastUpdDt) {
		this.lastUpdDt = lastUpdDt;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
