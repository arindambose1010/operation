package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "EHF_PATIENT")
public class EhfPatient implements Serializable {

	private String patientId;
    private String cardNo;
    private String employeeNo;
    private String name;
    private String cardType;
    private String occupationCd;
    private Date enrollDob;
    private Long age;
    private Long ageDays;
    private Long ageMonths;
    private String gender;
    private String relation;
    private String childYn;
    private Long contactNo;
    private String familyHead;
    private String houseNo;
    private String street;
    private String districtCode;
    private String mdl_mpl;
    private String mandalCode;
    private String villageCode;
    private String pinCode;
    private String state;
    private String srcRegistration;    
    private String patientIpop;
    private String patientIpopNo;
    private Long phaseId;
    private String patientScheme;
    private String schemeId;
    private Long sourceId;
    private Date regHospDate;
    private String regHospId;
    private String intimationId;
    private String attachment;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String cHouseNo;
    private String cStreet;
    private String cState;
    private String cDistrictCode;
    private String c_mdl_mpl;
    private String cMandalCode;
    private String cVillageCode;
    private String cPinCode;
    private String caste;
    private String maritalStatus;
    private Date cardIssueDt;
    private String slab;
    private String emailId;
    private String registerYN;
    private String newBornBaby;
    private String prc;
    private String payScale;
    private String dept;
    private String postDDOcode;
    private String servDsgn;
    private String ddoOffUnit;
    private String deptHod;
    private String postDist;
    private String currPay;
    private String designation;
    private String aadharID;
    private String aadharEID;
    private String fromDisp;
    private String dispCode;
    private String refSpclty;
    private String refDist;
    private String refHospId;
    private String specialityType;
    private String tokenNo;
    private String roomNo;
    private String dtrsFlag;
    private String nabhHosp;
    private String abhaNo;//Chandana - 8442 - Added this variable for abha number
    
@Id
@Column(name="PATIENT_ID", nullable = false)
    public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column(name="card_no", nullable = false)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Column(name="EMPLOYEE_NO", nullable = false)
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	@Column(name="name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="card_type", nullable = false)
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Column(name="occupation_cd")
	public String getOccupationCd() {
		return occupationCd;
	}

	public void setOccupationCd(String occupationCd) {
		this.occupationCd = occupationCd;
	}
	@Column(name="enroll_dob")
	public Date getEnrollDob() {
		return enrollDob;
	}

	public void setEnrollDob(Date enrollDob) {
		this.enrollDob = enrollDob;
	}

	@Column(name="age", nullable = false)
	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
	@Column(name="age_days")
	public Long getAgeDays() {
		return ageDays;
	}

	public void setAgeDays(Long ageDays) {
		this.ageDays = ageDays;
	}
	@Column(name="age_months")
	public Long getAgeMonths() {
		return ageMonths;
	}

	public void setAgeMonths(Long ageMonths) {
		this.ageMonths = ageMonths;
	}
	@Column(name="gender", nullable = false)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="relation")
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Column(name="child_yn")
	public String getChildYn() {
		return childYn;
	}

	public void setChildYn(String childYn) {
		this.childYn = childYn;
	}
	@Column(name="contact_no")
	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	@Column(name="family_head")
	public String getFamilyHead() {
		return familyHead;
	}

	public void setFamilyHead(String familyHead) {
		this.familyHead = familyHead;
	}
	@Column(name="house_no", nullable = false)
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	@Column(name="street", nullable = false)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name="district_code", nullable = false)
	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="mdl_mpl")
	public String getMdl_mpl() {
		return mdl_mpl;
	}

	public void setMdl_mpl(String mdl_mpl) {
		this.mdl_mpl = mdl_mpl;
	}

	@Column(name="mandal_code", nullable = false)
	public String getMandalCode() {
		return mandalCode;
	}

	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}
	@Column(name="village_code", nullable = false)
	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	@Column(name="pin_code")
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Column(name="state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name="src_registration", nullable = false)
	public String getSrcRegistration() {
		return srcRegistration;
	}

	public void setSrcRegistration(String srcRegistration) {
		this.srcRegistration = srcRegistration;
	}
	@Column(name="patient_ipop", nullable = false)
	public String getPatientIpop() {
		return patientIpop;
	}

	public void setPatientIpop(String patientIpop) {
		this.patientIpop = patientIpop;
	}
	@Column(name="patient_ipop_no")
	public String getPatientIpopNo() {
		return patientIpopNo;
	}

	public void setPatientIpopNo(String patientIpopNo) {
		this.patientIpopNo = patientIpopNo;
	}
	@Column(name="phase_id", nullable = false)
	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}
	@Column(name="patient_scheme")
	public String getPatientScheme() {
		return patientScheme;
	}

	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	
	@Column(name="scheme_id")
	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	@Column(name="source_id")
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_hosp_date")
	public Date getRegHospDate() {
		return regHospDate;
	}

	public void setRegHospDate(Date regHospDate) {
		this.regHospDate = regHospDate;
	}
	@Column(name="reg_hosp_id")
	public String getRegHospId() {
		return regHospId;
	}

	public void setRegHospId(String regHospId) {
		this.regHospId = regHospId;
	}
	@Column(name="intimation_id")
	public String getIntimationId() {
		return intimationId;
	}

	public void setIntimationId(String intimationId) {
		this.intimationId = intimationId;
	}
	@Column(name="attachment")
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR", nullable = false)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name="c_house_no")
	public String getcHouseNo() {
		return cHouseNo;
	}

	public void setcHouseNo(String cHouseNo) {
		this.cHouseNo = cHouseNo;
	}
	@Column(name="c_street")
	public String getcStreet() {
		return cStreet;
	}

	public void setcStreet(String cStreet) {
		this.cStreet = cStreet;
	}
	@Column(name="c_state")
	public String getcState() {
		return cState;
	}

	public void setcState(String cState) {
		this.cState = cState;
	}

	@Column(name="c_district_code")
	public String getcDistrictCode() {
		return cDistrictCode;
	}

	public void setcDistrictCode(String cDistrictCode) {
		this.cDistrictCode = cDistrictCode;
	}
	@Column(name="c_mdl_mpl")
	public String getC_mdl_mpl() {
		return c_mdl_mpl;
	}
	public void setC_mdl_mpl(String c_mdl_mpl) {
		this.c_mdl_mpl = c_mdl_mpl;
	}

	@Column(name="c_mandal_code")
	public String getcMandalCode() {
		return cMandalCode;
	}

	public void setcMandalCode(String cMandalCode) {
		this.cMandalCode = cMandalCode;
	}
	@Column(name="c_village_code")
	public String getcVillageCode() {
		return cVillageCode;
	}

	public void setcVillageCode(String cVillageCode) {
		this.cVillageCode = cVillageCode;
	}
	@Column(name="c_pin_code")
	public String getcPinCode() {
		return cPinCode;
	}

	public void setcPinCode(String cPinCode) {
		this.cPinCode = cPinCode;
	}
	@Column(name="caste")
	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}
	@Column(name="maritalstatus")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CARD_ISSUE_DT")
	public Date getCardIssueDt() {
		return cardIssueDt;
	}

	public void setCardIssueDt(Date cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}
	@Column(name="SLAB")
	public String getSlab() {
		return slab;
	}

	public void setSlab(String slab) {
		this.slab = slab;
	}
	@Column(name="EMAILID")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name="register_y_n")
	public String getRegisterYN() {
		return registerYN;
	}

	public void setRegisterYN(String registerYN) {
		this.registerYN = registerYN;
	}

	@Column(name="NEW_BORN_BABY")
	public String getNewBornBaby() {
		return newBornBaby;
	}

	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}
	
	@Column(name="PRC")
	public String getPrc() {
		return prc;
	}

	public void setPrc(String prc) {
		this.prc = prc;
	}

	@Column(name="PAY_SCALE")
	public String getPayScale() {
		return payScale;
	}

	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}

	@Column(name="DEPT")
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name="POST_DDO_CODE")
	public String getPostDDOcode() {
		return postDDOcode;
	}

	public void setPostDDOcode(String postDDOcode) {
		this.postDDOcode = postDDOcode;
	}

	@Column(name="SERV_DSGN")
	public String getServDsgn() {
		return servDsgn;
	}

	public void setServDsgn(String servDsgn) {
		this.servDsgn = servDsgn;
	}

	@Column(name="DDO_OFF_UNIT")
	public String getDdoOffUnit() {
		return ddoOffUnit;
	}

	public void setDdoOffUnit(String ddoOffUnit) {
		this.ddoOffUnit = ddoOffUnit;
	}

	@Column(name="DEPT_HOD")
	public String getDeptHod() {
		return deptHod;
	}

	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}

	@Column(name="POST_DIST")
	public String getPostDist() {
		return postDist;
	}

	public void setPostDist(String postDist) {
		this.postDist = postDist;
	}

	@Column(name="CURR_PAY")
	public String getCurrPay() {
		return currPay;
	}

	public void setCurrPay(String currPay) {
		this.currPay = currPay;
	}

	@Column(name="DESIGNATION")
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name="AADHAR_ID")
	public String getAadharID() {
		return aadharID;
	}

	public void setAadharID(String aadharID) {
		this.aadharID = aadharID;
	}

	@Column(name="AADHAR_EID")
	public String getAadharEID() {
		return aadharEID;
	}

	public void setAadharEID(String aadharEID) {
		this.aadharEID = aadharEID;
	}

	@Column(name="FROM_DISP")
	public String getFromDisp() {
		return fromDisp;
	}

	public void setFromDisp(String fromDisp) {
		this.fromDisp = fromDisp;
	}

	@Column(name="DISP_CODE")
	public String getDispCode() {
		return dispCode;
	}

	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}

	@Column(name="REF_SPCLTY")
	public String getRefSpclty() {
		return refSpclty;
	}

	public void setRefSpclty(String refSpclty) {
		this.refSpclty = refSpclty;
	}
	@Column(name="REF_DIST")
	public String getRefDist() {
		return refDist;
	}

	public void setRefDist(String refDist) {
		this.refDist = refDist;
	}
	@Column(name="REF_HOSP_ID")
	public String getRefHospId() {
		return refHospId;
	}

	public void setRefHospId(String refHospId) {
		this.refHospId = refHospId;
	}

	@Column(name="SPECIALITY_TYPE")
	public String getSpecialityType() {
		return specialityType;
	}

	public void setSpecialityType(String specialityType) {
		this.specialityType = specialityType;
	}

	@Column(name="TOKEN_NO")
	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	@Column(name="ROOM_NO")
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	@Column(name="DTRS_FLAG")
	public String getDtrsFlag() {
		return dtrsFlag;
	}

	public void setDtrsFlag(String dtrsFlag) {
		this.dtrsFlag = dtrsFlag;
	}
	@Column(name="NABH_HOSP")
	public String getNabhHosp() {
		return nabhHosp;
	}

	public void setNabhHosp(String nabhHosp) {
		this.nabhHosp = nabhHosp;
	}
	//Chandana - 8442 - Added below for new column ABHA_NO and setters getters for the new variable
	@Column(name="ABHA_NO")
	public String getAbhaNo() {
		return abhaNo;
	}

	public void setAbhaNo(String abhaNo) {
		this.abhaNo = abhaNo;
	}
	
    
}
