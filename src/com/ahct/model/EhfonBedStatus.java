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
@Table(name = "EHF_ONBED_STATUS")
public class EhfonBedStatus implements Serializable {
    private String caseId;
    private String caseNo;
    private String patientName;
    private String hospital;
    private String cardNo;
    private Long age;
    private String gender;
    private String district;
    private String caseStatus;
    private String bedStatusId;
    private String bedStatus;
    private Date bedStatusUpdDate;
    private String regDate;
    private String hospDiagnosis;
    private String schemeId;
    private Long phaseId;
    private String districtCode;
    private String caseHospCode;
    private Long sourceId;
    private String wardNo;
    private String surgeryDt;
    private String remarks;
    private String surgeryDateStatus;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    @Id
    @Column(name="CASE_ID", nullable=false)
    public String getCaseId() {
        return caseId;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }
    @Column(name="CASE_NO", nullable=false)
    public String getCaseNo() {
        return caseNo;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    @Column(name="PATIENT_NAME")
    public String getPatientName() {
        return patientName;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    @Column(name="HOSPITAL")
    public String getHospital() {
        return hospital;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
   @Column(name="CARD_NO", nullable=false)
    public String getCardNo() {
        return cardNo;
    }

    public void setAge(Long age) {
        this.age = age;
    }
    @Column(name="AGE", nullable=false)
    public Long getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name="GENDER", nullable=false)
    public String getGender() {
        return gender;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    @Column(name="DISTRICT")
    public String getDistrict() {
        return district;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
    @Column(name="CASE_STATUS", nullable=false)
    public String getCaseStatus() {
        return caseStatus;
    }

    public void setBedStatusId(String bedStatusId) {
        this.bedStatusId = bedStatusId;
    }
    @Column(name="BED_STATUS_ID")
    public String getBedStatusId() {
        return bedStatusId;
    }

    public void setBedStatus(String bedStatus) {
        this.bedStatus = bedStatus;
    }
    @Column(name="BED_STATUS")
    public String getBedStatus() {
        return bedStatus;
    }

    public void setBedStatusUpdDate(Date bedStatusUpdDate) {
        this.bedStatusUpdDate = bedStatusUpdDate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BEDSTATUSUPDDATE")
    public Date getBedStatusUpdDate() {
        return bedStatusUpdDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    @Column(name="REG_DATE")
    public String getRegDate() {
        return regDate;
    }

    public void setHospDiagnosis(String hospDiagnosis) {
        this.hospDiagnosis = hospDiagnosis;
    }
    @Column(name="HOSP_DIAGNOSIS")
    public String getHospDiagnosis() {
        return hospDiagnosis;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }
    @Column(name="SCHEME_ID")
    public String getSchemeId() {
        return schemeId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }
    @Column(name="PHASE_ID", nullable=false)
    public Long getPhaseId() {
        return phaseId;
    }
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    @Column(name="DISTRICT_CODE", nullable=false)
    public String getDistrictCode() {
        return districtCode;
    }
    public void setCaseHospCode(String caseHospCode) {
        this.caseHospCode = caseHospCode;
    }
    @Column(name="CASE_HOSP_CODE")
    public String getCaseHospCode() {
        return caseHospCode;
    }

    
    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }
    @Column(name="WARDNO")
    public String getWardNo() {
        return wardNo;
    }
    
    public void setSurgeryDt(String surgeryDt) {
        this.surgeryDt = surgeryDt;
    }
    @Column(name="SURGERYDT")
    public String getSurgeryDt() {
        return surgeryDt;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name="REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setSurgeryDateStatus(String surgeryDateStatus) {
        this.surgeryDateStatus = surgeryDateStatus;
    }
    @Column(name="SURGERYDATESTATUS")
    public String getSurgeryDateStatus() {
        return surgeryDateStatus;
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
    @Column(name="source_id")
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
    
    
    
    
    
    
}
