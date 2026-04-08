package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_ANNUAL_PATIENT_DTLS database table.
 * 
 */
@Entity
@Table(name="EHF_ANNUAL_PATIENT_DTLS")
public class EhfAnnualPatientDtls implements Serializable {
	private static final long serialVersionUID = 1L;

	

	@Id
	@Column(name="AHC_ID")
	private String ahcId;

	@Column(name="AGE")
	private BigDecimal age;

	@Column(name="AGE_DAYS")
	private BigDecimal ageDays;

	@Column(name="AGE_MONTHS")
	private BigDecimal ageMonths;

	@Column(name="ATTACHMENT")
	private String attachment;

	@Column(name="C_DISTRICT_CODE")
	private String cDistrictCode;

	@Column(name="C_HOUSE_NO")
	private String cHouseNo;

	@Column(name="C_MANDAL_CODE")
	private String cMandalCode;

	@Column(name="C_MDL_MPL")
	private String cMdlMpl;

	@Column(name="C_PIN_CODE")
	private String cPinCode;

	@Column(name="C_STATE")
	private String cState;

	@Column(name="C_STREET")
	private String cStreet;

	@Column(name="C_VILLAGE_CODE")
	private String cVillageCode;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CARD_ISSUE_DT")
	private Date cardIssueDt;

	@Column(name="CARD_NO")
	private String cardNo;

	@Column(name="CARD_TYPE")
	private String cardType;
	
	@Column(name="SCHEME_ID")
	private String schemeId;

	@Column(name="CASTE")
	private String caste;

	@Column(name="CHILD_YN")
	private String childYn;

	@Column(name="CONTACT_NO")
	private BigDecimal contactNo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="DISTRICT_CODE")
	private String districtCode;

	@Column(name="EMAILID")
	private String emailid;

	@Column(name="EMPLOYEE_NO")
	private String employeeNo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="ENROLL_DOB")
	private Date enrollDob;

	@Column(name="FAMILY_HEAD")
	private String familyHead;

	
	@Column(name="GENDER")
	private String gender;

	@Column(name="HOUSE_NO")
	private String houseNo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="MANDAL_CODE")
	private String mandalCode;
	

	@Column(name="MARITALSTATUS")
	private String maritalStatus;

	@Column(name="MDL_MPL")
	private String mdlMpl;

	@Column(name="NAME")
	private String name;

	@Column(name="OCCUPATION_CD")
	private String occupationCd;

	@Column(name="PIN_CODE")
	private String pinCode;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="REG_HOSP_DATE")
	private Date regHospDate;

	@Column(name="REG_HOSP_ID")
	private String regHospId;

	@Column(name="REGISTER_Y_N")
	private String registerYN;

	
	@Column(name="RELATION")
	private String relation;
	
	
	@Column(name="SLAB")
	private String slab;

	
	@Column(name="STATE")
	private String state;

	@Column(name="STREET")
	private String street;

	@Column(name="VILLAGE_CODE")
	private String villageCode;
	
	
	@Column(name="DEPT_HOD")
	private String deptHod;
	
	@Column(name="POST_DIST")
	private String postDist;
	
	@Column(name="DDO_CODE")
	private String ddoCode;
	


    public EhfAnnualPatientDtls() {
    }



	public String getAhcId() {
		return ahcId;
	}



	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}



	public BigDecimal getAge() {
		return this.age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public BigDecimal getAgeDays() {
		return this.ageDays;
	}

	public void setAgeDays(BigDecimal ageDays) {
		this.ageDays = ageDays;
	}

	public BigDecimal getAgeMonths() {
		return this.ageMonths;
	}

	public void setAgeMonths(BigDecimal ageMonths) {
		this.ageMonths = ageMonths;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getCDistrictCode() {
		return this.cDistrictCode;
	}

	public void setCDistrictCode(String cDistrictCode) {
		this.cDistrictCode = cDistrictCode;
	}

	public String getCHouseNo() {
		return this.cHouseNo;
	}

	public void setCHouseNo(String cHouseNo) {
		this.cHouseNo = cHouseNo;
	}

	public String getCMandalCode() {
		return this.cMandalCode;
	}

	public void setCMandalCode(String cMandalCode) {
		this.cMandalCode = cMandalCode;
	}

	public String getCMdlMpl() {
		return this.cMdlMpl;
	}

	public void setCMdlMpl(String cMdlMpl) {
		this.cMdlMpl = cMdlMpl;
	}

	public String getCPinCode() {
		return this.cPinCode;
	}

	public void setCPinCode(String cPinCode) {
		this.cPinCode = cPinCode;
	}

	public String getCState() {
		return this.cState;
	}

	public void setCState(String cState) {
		this.cState = cState;
	}

	public String getCStreet() {
		return this.cStreet;
	}

	public void setCStreet(String cStreet) {
		this.cStreet = cStreet;
	}

	public String getCVillageCode() {
		return this.cVillageCode;
	}

	public void setCVillageCode(String cVillageCode) {
		this.cVillageCode = cVillageCode;
	}

	public Date getCardIssueDt() {
		return this.cardIssueDt;
	}

	public void setCardIssueDt(Date cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCaste() {
		return this.caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getChildYn() {
		return this.childYn;
	}

	public void setChildYn(String childYn) {
		this.childYn = childYn;
	}

	public BigDecimal getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(BigDecimal contactNo) {
		this.contactNo = contactNo;
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

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Date getEnrollDob() {
		return this.enrollDob;
	}

	public void setEnrollDob(Date enrollDob) {
		this.enrollDob = enrollDob;
	}

	public String getFamilyHead() {
		return this.familyHead;
	}

	public void setFamilyHead(String familyHead) {
		this.familyHead = familyHead;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
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

	public String getMandalCode() {
		return this.mandalCode;
	}

	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMdlMpl() {
		return this.mdlMpl;
	}

	public void setMdlMpl(String mdlMpl) {
		this.mdlMpl = mdlMpl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupationCd() {
		return this.occupationCd;
	}

	public void setOccupationCd(String occupationCd) {
		this.occupationCd = occupationCd;
	}

	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Date getRegHospDate() {
		return this.regHospDate;
	}

	public void setRegHospDate(Date regHospDate) {
		this.regHospDate = regHospDate;
	}

	public String getRegHospId() {
		return this.regHospId;
	}

	public void setRegHospId(String regHospId) {
		this.regHospId = regHospId;
	}

	public String getRegisterYN() {
		return this.registerYN;
	}

	public void setRegisterYN(String registerYN) {
		this.registerYN = registerYN;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getSlab() {
		return this.slab;
	}

	public void setSlab(String slab) {
		this.slab = slab;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getVillageCode() {
		return this.villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}



	public String getDeptHod() {
		return deptHod;
	}



	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}



	public String getPostDist() {
		return postDist;
	}



	public void setPostDist(String postDist) {
		this.postDist = postDist;
	}



	public String getDdoCode() {
		return ddoCode;
	}



	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}



	public String getSchemeId() {
		return schemeId;
	}



	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}



	
	

}