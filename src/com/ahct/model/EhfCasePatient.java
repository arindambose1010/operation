package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_CASE_PATIENT")
public class EhfCasePatient implements java.io.Serializable{
	
	private String patientId;
    private String cardNo;
    private String employeeNo;
    private String name;
    private String cardType;
    private String occupationCd;
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
    private String mandalCode;
    private String villageCode;
    private String pinCode;
    private String state;
    private String srcRegistration;    
    private String patientIpopNo;
    private Long phaseId;
    private String patientScheme;
    private String schemeId;
    private Long sourceId;
    private Date regHospDate;
    private String regHospId;
    private String intimationId;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;

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
    	@Column(name="age")
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
    	@Column(name="gender")
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
    	@Column(name="house_no")
    	public String getHouseNo() {
    		return houseNo;
    	}

    	public void setHouseNo(String houseNo) {
    		this.houseNo = houseNo;
    	}
    	@Column(name="street")
    	public String getStreet() {
    		return street;
    	}

    	public void setStreet(String street) {
    		this.street = street;
    	}
    	@Column(name="district_code")
    	public String getDistrictCode() {
    		return districtCode;
    	}

    	public void setDistrictCode(String districtCode) {
    		this.districtCode = districtCode;
    	}
    	@Column(name="mandal_code")
    	public String getMandalCode() {
    		return mandalCode;
    	}

    	public void setMandalCode(String mandalCode) {
    		this.mandalCode = mandalCode;
    	}
    	@Column(name="village_code")
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
    	@Column(name="src_registration")
    	public String getSrcRegistration() {
    		return srcRegistration;
    	}

    	public void setSrcRegistration(String srcRegistration) {
    		this.srcRegistration = srcRegistration;
    	}
    
    	@Column(name="patient_ip_no")
    	public String getPatientIpopNo() {
    		return patientIpopNo;
    	}

    	public void setPatientIpopNo(String patientIpopNo) {
    		this.patientIpopNo = patientIpopNo;
    	}
    	@Column(name="phase_id")
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
    	

    	@Temporal(TemporalType.TIMESTAMP)
        @Column(name="crt_dt", nullable = false)
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

	}
