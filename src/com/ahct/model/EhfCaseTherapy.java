package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_CASE_THERAPY")
public class EhfCaseTherapy implements java.io.Serializable{

	private Long caseTherapyId;
	private String caseId;
	private String icdCatCode;
	private String asriCatCode;
	private String icdSubCatCode;
	private String icdProcCode;
	private String asriProcCode;
	private String isapproved;
	private Long noOfDays;
	private String splInvRemarks;
	private String activeyn;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String docRegNum;
	private String docName;
	private String docQual;
	private String docMobileNo;
	private String procUnits;
	private String surgProcUnits;
	private String toothedUnits;
	private String medcoProcUnits;
	private String ctdProcUnits;
	private String chProcUnits;
	private String unitsPartial;
	private String hospstayAmt;
	private String commonCatAmt;
	private String icdAmt;
    //private Set<EhfmHospSurgPer> ehfmHospSurgPer = new HashSet<EhfmHospSurgPer>(0);
    
@Id
@Column(name="case_therapy_id")
	public Long getCaseTherapyId() {
		return caseTherapyId;
	}
	public void setCaseTherapyId(Long caseTherapyId) {
		this.caseTherapyId = caseTherapyId;
	}
	@Column(name="case_id")
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name="ICD_Cat_Code")
	public String getIcdCatCode() {
		return icdCatCode;
	}
	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}
	@Column(name="asri_Cat_Code")
	public String getAsriCatCode() {
		return asriCatCode;
	}
	public void setAsriCatCode(String asriCatCode) {
		this.asriCatCode = asriCatCode;
	}
	@Column(name="ICD_SubCat_Code")
	public String getIcdSubCatCode() {
		return icdSubCatCode;
	}
	public void setIcdSubCatCode(String icdSubCatCode) {
		this.icdSubCatCode = icdSubCatCode;
	}
	@Column(name="ICD_Proc_Code")
	public String getIcdProcCode() {
		return icdProcCode;
	}
	public void setIcdProcCode(String icdProcCode) {
		this.icdProcCode = icdProcCode;
	}
	@Column(name="isapproved")
	public String getIsapproved() {
		return isapproved;
	}
	public void setIsapproved(String isapproved) {
		this.isapproved = isapproved;
	}
	@Column(name="no_of_days")
	public Long getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}
	@Column(name="spl_inv_remarks")
	public String getSplInvRemarks() {
		return splInvRemarks;
	}
	public void setSplInvRemarks(String splInvRemarks) {
		this.splInvRemarks = splInvRemarks;
	}
	@Column(name="activeyn")
	public String getActiveyn() {
		return activeyn;
	}
	public void setActiveyn(String activeyn) {
		this.activeyn = activeyn;
	}
	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="asri_proc_code")
	public String getAsriProcCode() {
		return asriProcCode;
	}
	public void setAsriProcCode(String asriProcCode) {
		this.asriProcCode = asriProcCode;
	}
	
	/*@ManyToMany
    @JoinTable(name="EHFM_HOSP_SURG_PER",
            joinColumns={@JoinColumn(name="ICD_Proc_Code")},
            inverseJoinColumns={@JoinColumn(name="SURGERY_ID")})
	public Set<EhfmHospSurgPer> getEhfmHospSurgPer() {
		return ehfmHospSurgPer;
	}
	public void setEhfmHospSurgPer(Set<EhfmHospSurgPer> ehfmHospSurgPer) {
		this.ehfmHospSurgPer = ehfmHospSurgPer;
	}*/
	@Column(name="doc_reg_num")
	public String getDocRegNum() {
		return docRegNum;
	}
	public void setDocRegNum(String docRegNum) {
		this.docRegNum = docRegNum;
	}
	@Column(name="doc_name")
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	@Column(name="doc_qual")
	public String getDocQual() {
		return docQual;
	}
	public void setDocQual(String docQual) {
		this.docQual = docQual;
	}
	@Column(name="doc_mobile_no")
	public String getDocMobileNo() {
		return docMobileNo;
	}
	public void setDocMobileNo(String docMobileNo) {
		this.docMobileNo = docMobileNo;
	}
	@Column(name="op_proc_units")
	public String getProcUnits() {
		return procUnits;
	}
	public void setProcUnits(String procUnits) {
		this.procUnits = procUnits;
	}
	@Column(name="SURG_PROC_UNITS")
	public String getSurgProcUnits() {
		return surgProcUnits;
	}
	public void setSurgProcUnits(String surgProcUnits) {
		this.surgProcUnits = surgProcUnits;
	}
	@Column(name="TOOTHED_UNITS")
	public String getToothedUnits() {
		return toothedUnits;
	}
	public void setToothedUnits(String toothedUnits) {
		this.toothedUnits = toothedUnits;
	}
	@Column(name="MEDCO_PROC_UNITS")
	public String getMedcoProcUnits() {
		return medcoProcUnits;
	}
	public void setMedcoProcUnits(String medcoProcUnits) {
		this.medcoProcUnits = medcoProcUnits;
	}
	@Column(name="CTD_PROC_UNITS")
	public String getCtdProcUnits() {
		return ctdProcUnits;
	}
	public void setCtdProcUnits(String ctdProcUnits) {
		this.ctdProcUnits = ctdProcUnits;
	}
	@Column(name="CH_PROC_UNITS")
	public String getChProcUnits() {
		return chProcUnits;
	}
	public void setChProcUnits(String chProcUnits) {
		this.chProcUnits = chProcUnits;
	}
	@Column(name="UNITS_PAR")
	public String getUnitsPartial() {
		return unitsPartial;
	}
	public void setUnitsPartial(String unitsPartial) {
		this.unitsPartial = unitsPartial;
	}
	@Column(name="hosp_stay_amt")
	public String getHospstayAmt() {
		return hospstayAmt;
	}
	public void setHospstayAmt(String hospstayAmt) {
		this.hospstayAmt = hospstayAmt;
	}
	
	@Column(name="common_cat_amt")
	public String getCommonCatAmt() {
		return commonCatAmt;
	}
	public void setCommonCatAmt(String commonCatAmt) {
		this.commonCatAmt = commonCatAmt;
	}
	@Column(name="icd_amt")
	public String getIcdAmt() {
		return icdAmt;
	}
	public void setIcdAmt(String icdAmt) {
		this.icdAmt = icdAmt;
	}
	public EhfCaseTherapy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfCaseTherapy(Long caseTherapyId, String caseId, String icdCatCode,
			String asriCatCode, String icdSubCatCode, String icdProcCode,
			String asriProcCode, String isapproved, Long noOfDays,
			String splInvRemarks, String activeyn, String crtUsr, Date crtDt,
			String lstUpdUsr, Date lstUpdDt, String docRegNum, String docName,
			String docQual, String docMobileNo, String procUnits,String surgProcUnits,String hospstayAmt, String commonCatAmt,
			String icdAmt,String toothedUnits,String medcoProcUnits,String ctdProcUnits,String chProcUnits,String unitsPartial) {
		super();
		this.caseTherapyId = caseTherapyId;
		this.caseId = caseId;
		this.icdCatCode = icdCatCode;
		this.asriCatCode = asriCatCode;
		this.icdSubCatCode = icdSubCatCode;
		this.icdProcCode = icdProcCode;
		this.asriProcCode = asriProcCode;
		this.isapproved = isapproved;
		this.noOfDays = noOfDays;
		this.splInvRemarks = splInvRemarks;
		this.activeyn = activeyn;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.docRegNum = docRegNum;
		this.docName = docName;
		this.docQual = docQual;
		this.docMobileNo = docMobileNo;
		this.procUnits = procUnits;
		this.surgProcUnits = surgProcUnits;
		this.toothedUnits = toothedUnits;
		this.medcoProcUnits = medcoProcUnits;
		this.ctdProcUnits = ctdProcUnits;
		this.chProcUnits = chProcUnits;
		this.unitsPartial = unitsPartial;
		this.hospstayAmt = hospstayAmt;
		this.commonCatAmt = commonCatAmt;
		this.icdAmt = icdAmt;
	}
	}
