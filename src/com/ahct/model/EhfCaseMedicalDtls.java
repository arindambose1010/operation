package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "EHF_CASE_MEDICAL_DTLS")
public class EhfCaseMedicalDtls implements Serializable {
	 private Date admnDt;
	    private String caseId;
	    private Date crtDt;
	    private String crtUsr;
	    private String expectdHospStay;
	    private String finalDiag;
	    private String floor;
	    private Long hospStayDays;
	    private Date lstUpdDt;
	    private String lstUpdUsr;
	    private Long mobileNo;
	    private String nurseName;
	    private String paramedicName;
	    private Date preauthStrtDt;
	    private String qualification;
	    private String regnNo;
	    private String remarks;
	    private String roomNo;
	    private String speciality;
	    private String surgeonCntctNo;
	    private String surgeonName;
	    private String surgeonQual;
	    private String surgeonRegno;
	    private String surgAsstCntctno;
	    private String surgAsstName;
	    private String surgAsstQual;
	    private String surgAsstRegno;
	    private Long telNo;
	    private String trtdocName;
	    private String trtmntProtocol;
	    private String wardNo;    

	    public EhfCaseMedicalDtls (){
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="ADMN_DT")
	    public Date getAdmnDt() {
	        return admnDt;
	    }

	    public void setAdmnDt(Date admnDt) {
	        this.admnDt = admnDt;
	    }

	    @Id
	    @Column(name="CASE_ID", nullable = false)
	    public String getCaseId() {
	        return caseId;
	    }

	    public void setCaseId(String caseId) {
	        this.caseId = caseId;
	    }
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="CRT_DT")
	    public Date getCrtDt() {
	        return crtDt;
	    }
	   
	    public void setCrtDt(Date crtDt) {
	        this.crtDt = crtDt;
	    }

	    @Column(name="CRT_USR")
	    public String getCrtUsr() {
	        return crtUsr;
	    }

	    public void setCrtUsr(String crtUsr) {
	        this.crtUsr = crtUsr;
	    }

	    @Column(name="EXPECTD_HOSP_STAY")
	    public String getExpectdHospStay() {
	        return expectdHospStay;
	    }

	    public void setExpectdHospStay(String expectdHospStay) {
	        this.expectdHospStay = expectdHospStay;
	    }

	    @Column(name="FINAL_DIAG")
	    public String getFinalDiag() {
	        return finalDiag;
	    }

	    public void setFinalDiag(String finalDiag) {
	        this.finalDiag = finalDiag;
	    }

	    public String getFloor() {
	        return floor;
	    }

	    public void setFloor(String floor) {
	        this.floor = floor;
	    }

	    @Column(name="HOSP_STAY_DAYS")
	    public Long getHospStayDays() {
	        return hospStayDays;
	    }

	    public void setHospStayDays(Long hospStayDays) {
	        this.hospStayDays = hospStayDays;
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

	    @Column(name="MOBILE_NO")
	    public Long getMobileNo() {
	        return mobileNo;
	    }

	    public void setMobileNo(Long mobileNo) {
	        this.mobileNo = mobileNo;
	    }

	    @Column(name="NURSE_NAME")
	    public String getNurseName() {
	        return nurseName;
	    }

	    public void setNurseName(String nurseName) {
	        this.nurseName = nurseName;
	    }

	    @Column(name="PARAMEDIC_NAME")
	    public String getParamedicName() {
	        return paramedicName;
	    }

	    public void setParamedicName(String paramedicName) {
	        this.paramedicName = paramedicName;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="PREAUTH_STRT_DT")
	    public Date getPreauthStrtDt() {
	        return preauthStrtDt;
	    }

	    public void setPreauthStrtDt(Date preauthStrtDt) {
	        this.preauthStrtDt = preauthStrtDt;
	    }

	    public String getQualification() {
	        return qualification;
	    }

	    public void setQualification(String qualification) {
	        this.qualification = qualification;
	    }

	    @Column(name="REGN_NO")
	    public String getRegnNo() {
	        return regnNo;
	    }

	    public void setRegnNo(String regnNo) {
	        this.regnNo = regnNo;
	    }

	    public String getRemarks() {
	        return remarks;
	    }

	    public void setRemarks(String remarks) {
	        this.remarks = remarks;
	    }

	    @Column(name="ROOM_NO")
	    public String getRoomNo() {
	        return roomNo;
	    }

	    public void setRoomNo(String roomNo) {
	        this.roomNo = roomNo;
	    }

	    public String getSpeciality() {
	        return speciality;
	    }

	    public void setSpeciality(String speciality) {
	        this.speciality = speciality;
	    }

	    @Column(name="SURGEON_CNTCT_NO")
	    public String getSurgeonCntctNo() {
	        return surgeonCntctNo;
	    }

	    public void setSurgeonCntctNo(String surgeonCntctNo) {
	        this.surgeonCntctNo = surgeonCntctNo;
	    }

	    @Column(name="SURGEON_NAME")
	    public String getSurgeonName() {
	        return surgeonName;
	    }

	    public void setSurgeonName(String surgeonName) {
	        this.surgeonName = surgeonName;
	    }

	    @Column(name="SURGEON_QUAL")
	    public String getSurgeonQual() {
	        return surgeonQual;
	    }

	    public void setSurgeonQual(String surgeonQual) {
	        this.surgeonQual = surgeonQual;
	    }

	    @Column(name="SURGEON_REGNO")
	    public String getSurgeonRegno() {
	        return surgeonRegno;
	    }

	    public void setSurgeonRegno(String surgeonRegno) {
	        this.surgeonRegno = surgeonRegno;
	    }

	    @Column(name="SURG_ASST_CNTCTNO")
	    public String getSurgAsstCntctno() {
	        return surgAsstCntctno;
	    }

	    public void setSurgAsstCntctno(String surgAsstCntctno) {
	        this.surgAsstCntctno = surgAsstCntctno;
	    }

	    @Column(name="SURG_ASST_NAME")
	    public String getSurgAsstName() {
	        return surgAsstName;
	    }

	    public void setSurgAsstName(String surgAsstName) {
	        this.surgAsstName = surgAsstName;
	    }

	    @Column(name="SURG_ASST_QUAL")
	    public String getSurgAsstQual() {
	        return surgAsstQual;
	    }

	    public void setSurgAsstQual(String surgAsstQual) {
	        this.surgAsstQual = surgAsstQual;
	    }

	    @Column(name="SURG_ASST_REGNO")
	    public String getSurgAsstRegno() {
	        return surgAsstRegno;
	    }

	    public void setSurgAsstRegno(String surgAsstRegno) {
	        this.surgAsstRegno = surgAsstRegno;
	    }

	    @Column(name="TEL_NO")
	    public Long getTelNo() {
	        return telNo;
	    }

	    public void setTelNo(Long telNo) {
	        this.telNo = telNo;
	    }

	    @Column(name="TRTDOC_NAME")
	    public String getTrtdocName() {
	        return trtdocName;
	    }

	    public void setTrtdocName(String trtdocName) {
	        this.trtdocName = trtdocName;
	    }

	    @Column(name="TRTMNT_PROTOCOL")
	    public String getTrtmntProtocol() {
	        return trtmntProtocol;
	    }

	    public void setTrtmntProtocol(String trtmntProtocol) {
	        this.trtmntProtocol = trtmntProtocol;
	    }

	    @Column(name="WARD_NO")
	    public String getWardNo() {
	        return wardNo;
	    }

	    public void setWardNo(String wardNo) {
	        this.wardNo = wardNo;
	    }
}
