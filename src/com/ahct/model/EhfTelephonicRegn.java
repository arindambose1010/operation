package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_TELEPHONIC_REGN")
public class EhfTelephonicRegn implements java.io.Serializable {
	
	private String telephonicId;
    private String patientId;
    private String employeeNo;
    private Long age;
    private Long ageDays;
    private Long ageMonths;
    private String cardNo;
    private String name;
    private String gender;
    private String relation;
    private String occupationCd;
    private String slab;
    private String hNo;
    private String street;
    private String state;
    private String districtCode;
    private String mdl_mpl;
    private String mandalCode;
    private String villageCode;
    private Long contactNo;
    private String pinCode;
    private String hospId;
    private String callerName;
    private String callerDesig;
    private String callerMobileNo;
    private String disMainId;
    private String disSubId;
   
    private String teleIntimRemarks;
    private String doctorName;
    private String doctorDesig;
    private String docMobileNo;
    private String docRemarks;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String familyHead;
    private Date updDt;
    private String cardType;
    private Date enrollDob;
    
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
    
    private String asriCatCode;
    private String ICDCatCode;
    private String ICDSubCatCode;
    private String ICDProcCode;
    private String diaType;
    private String diaMainCatCode;
    private String diaCatCode;
    private String diaSubCatCode;
    private String diaDiseaseCode;
    private String diaDisAnatomicalCode;
    private String remarksProcedure;
    private String remarksDiagnosis;
    private String schemeId;
    private String patientScheme;
    private String nabhHosp;
    @Id
    @Column(name="telephonic_id")
	public String getTelephonicId() {
		return telephonicId;
	}
	public void setTelephonicId(String telephonicId) {
		this.telephonicId = telephonicId;
	}
	 @Column(name="patient_id")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	 @Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@Column(name="EMPLOYEE_NO")
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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
	 @Column(name="card_no")
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String CardNo) {
		this.cardNo = CardNo;
	}
	
		
	@Column(name="name")
	 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	 @Column(name="relationship")
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	 @Column(name="house_no")
	public String gethNo() {
		return hNo;
	}
	public void sethNo(String hNo) {
		this.hNo = hNo;
	}
	 @Column(name="street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	 @Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="district")
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
	@Column(name="village")
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	@Column(name="contact_no")
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	@Column(name="pin")
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Column(name="hosp_id", nullable = false)
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	@Column(name="caller_name", nullable = false)
	public String getCallerName() {
		return callerName;
	}
	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}
	@Column(name="caller_desig", nullable = false)
	public String getCallerDesig() {
		return callerDesig;
	}
	public void setCallerDesig(String callerDesig) {
		this.callerDesig = callerDesig;
	}
	@Column(name="caller_mobile_no", nullable = false)
	public String getCallerMobileNo() {
		return callerMobileNo;
	}
	public void setCallerMobileNo(String callerMobileNo) {
		this.callerMobileNo = callerMobileNo;
	}
	@Column(name="dis_main_id")
	public String getDisMainId() {
		return disMainId;
	}
	public void setDisMainId(String disMainId) {
		this.disMainId = disMainId;
	}
	@Column(name="dis_sub_id")
	public String getDisSubId() {
		return disSubId;
	}
	public void setDisSubId(String disSubId) {
		this.disSubId = disSubId;
	}
	
	@Column(name="tele_intim_remarks", nullable = false)
	public String getTeleIntimRemarks() {
		return teleIntimRemarks;
	}
	public void setTeleIntimRemarks(String teleIntimRemarks) {
		this.teleIntimRemarks = teleIntimRemarks;
	}
	@Column(name="doctor_name", nullable = false)
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	@Column(name="doctor_desig", nullable = false)
	public String getDoctorDesig() {
		return doctorDesig;
	}
	public void setDoctorDesig(String doctorDesig) {
		this.doctorDesig = doctorDesig;
	}
	@Column(name="doc_mobile_no", nullable = false)
	public String getDocMobileNo() {
		return docMobileNo;
	}
	public void setDocMobileNo(String docMobileNo) {
		this.docMobileNo = docMobileNo;
	}
	@Column(name="doc_remarks")
	public String getDocRemarks() {
		return docRemarks;
	}
	public void setDocRemarks(String docRemarks) {
		this.docRemarks = docRemarks;
	}
	 @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="crt_date")
	    public Date getCrtDt() {
	        return crtDt;
	    }

	    public void setCrtDt(Date crtDt) {
	        this.crtDt = crtDt;
	    }

	    @Column(name="crt_usr", nullable = false)
	    public String getCrtUsr() {
	        return crtUsr;
	    }

	    public void setCrtUsr(String crtUsr) {
	        this.crtUsr = crtUsr;
	    }

	    @Temporal(TemporalType.TIMESTAMP)
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
	    @Column(name="familyhead_y_n")
	public String getFamilyHead() {
		return familyHead;
	}
	public void setFamilyHead(String familyHead) {
		this.familyHead = familyHead;
	}
	  @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="upd_dt")
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	@Column(name="mandal")
	public String getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}
	@Column(name="occupation_cd")
	public String getOccupationCd() {
		return occupationCd;
	}
	public void setOccupationCd(String occupationCd) {
		this.occupationCd = occupationCd;
	}
	@Column(name="SLAB")
	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
	}
	@Column(name="card_type")
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Column(name="enroll_dob")
	public Date getEnrollDob() {
		return enrollDob;
	}
	public void setEnrollDob(Date enrollDob) {
		this.enrollDob = enrollDob;
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
    @Column(name="card_issue_dt")
	public Date getCardIssueDt() {
		return cardIssueDt;
	}
	public void setCardIssueDt(Date cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}
	@Column(name="ASRI_CAT_CODE")
	public String getAsriCatCode() {
		return asriCatCode;
	}
	public void setAsriCatCode(String asriCatCode) {
		this.asriCatCode = asriCatCode;
	}
	@Column(name="ICD_CAT_CODE")
	public String getICDCatCode() {
		return ICDCatCode;
	}
	public void setICDCatCode(String iCDCatCode) {
		ICDCatCode = iCDCatCode;
	}
	@Column(name="ICD_SUBCAT_CODE")
	public String getICDSubCatCode() {
		return ICDSubCatCode;
	}
	public void setICDSubCatCode(String iCDSubCatCode) {
		ICDSubCatCode = iCDSubCatCode;
	}
	@Column(name="ICD_PROC_CODE")
	public String getICDProcCode() {
		return ICDProcCode;
	}
	public void setICDProcCode(String iCDProcCode) {
		ICDProcCode = iCDProcCode;
	}
	@Column(name="DIA_TYPE")
	public String getDiaType() {
		return diaType;
	}
	public void setDiaType(String diaType) {
		this.diaType = diaType;
	}
	@Column(name="DIA_MAIN_CAT_CODE")
	public String getDiaMainCatCode() {
		return diaMainCatCode;
	}
	public void setDiaMainCatCode(String diaMainCatCode) {
		this.diaMainCatCode = diaMainCatCode;
	}
	@Column(name="DIA_CAT_CODE")
	public String getDiaCatCode() {
		return diaCatCode;
	}
	public void setDiaCatCode(String diaCatCode) {
		this.diaCatCode = diaCatCode;
	}
	@Column(name="DIA_SUBCAT_CODE")
	public String getDiaSubCatCode() {
		return diaSubCatCode;
	}
	public void setDiaSubCatCode(String diaSubCatCode) {
		this.diaSubCatCode = diaSubCatCode;
	}
	@Column(name="DIA_DIASEASE_CODE")
	public String getDiaDiseaseCode() {
		return diaDiseaseCode;
	}
	public void setDiaDiseaseCode(String diaDiseaseCode) {
		this.diaDiseaseCode = diaDiseaseCode;
	}
	@Column(name="DIA_DIS_ANATOMICAL_CODE")
	public String getDiaDisAnatomicalCode() {
		return diaDisAnatomicalCode;
	}
	public void setDiaDisAnatomicalCode(String diaDisAnatomicalCode) {
		this.diaDisAnatomicalCode = diaDisAnatomicalCode;
	}
	@Column(name="REMARKS_PROCEDURE")
	public String getRemarksProcedure() {
		return remarksProcedure;
	}
	public void setRemarksProcedure(String remarksProcedure) {
		this.remarksProcedure = remarksProcedure;
	}
	@Column(name="REMARKS_DIAGNOSIS")
	public String getRemarksDiagnosis() {
		return remarksDiagnosis;
	}
	public void setRemarksDiagnosis(String remarksDiagnosis) {
		this.remarksDiagnosis = remarksDiagnosis;
	}
	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name="patient_scheme")
	public String getPatientScheme() {
		return patientScheme;
	}
	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	@Column(name="nabh_hosp")
	public String getNabhHosp() {
		return nabhHosp;
	}
	public void setNabhHosp(String nabhHosp) {
		this.nabhHosp = nabhHosp;
	}
    
}

