package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_SPLST_DCTRS")
public class EhfmSplstDctrs implements Serializable {
	private EhfmSplstDctrsId id;
	private String reqNo;
	private String splstId;
	private String title;
	private String splstName;
	private String experience;
	private String contactNo;
	private String mobile;
	private String clinicalDetails;
	private String isConsultant;
	private String vstsType;
	private String noOfVsts;
	private String location;
	private String otherHosp;
	private String doctorRemarks;
	private String mdclCnslStatus;
	private String apprvStatus;
	private String isDactvShftRqstd;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Date dateOfLeaving;
	private String otherNtwkHosp;
	private String isActiveYn;
	private String university;
	private String scheme;
	
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "regNum", column = @Column(name = "REG_NUM", nullable = false, length =50)),
		
			@AttributeOverride(name = "hospId", column = @Column(name = "HOSP_ID", nullable = false, length = 20)) })
	public EhfmSplstDctrsId getId() {
		return id;
	}
	public void setId(EhfmSplstDctrsId id) {
		this.id = id;
	}
	@Column(name = "REQ_NO", nullable = false, length = 50)
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	@Column(name = "SPLST_ID", nullable = true, length = 50)
	public String getSplstId() {
		return splstId;
	}
	public void setSplstId(String splstId) {
		this.splstId = splstId;
	}
	@Column(name = "TITLE", nullable = true, length = 3)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "SPLST_NAME", nullable = true, length = 400)
	public String getSplstName() {
		return splstName;
	}
	public void setSplstName(String splstName) {
		this.splstName = splstName;
	}
	@Column(name = "UNIVERSITY", nullable = true, length = 100)
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	@Column(name = "EXPERIENCE", nullable = true, length = 25)
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	@Column(name = "CONTACTNO", nullable = true, length = 15)
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	@Column(name = "MOBILE", nullable = true, length = 15)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "CLINIC_DETAILS", nullable = true, length = 400)
	public String getClinicalDetails() {
		return clinicalDetails;
	}
	public void setClinicalDetails(String clinicalDetails) {
		this.clinicalDetails = clinicalDetails;
	}
	@Column(columnDefinition="char",name = "IS_CONSULTANT", nullable = true, length = 1)
	public String getIsConsultant() {
		return isConsultant;
	}
	public void setIsConsultant(String isConsultant) {
		this.isConsultant = isConsultant;
	}
	@Column(name = "VSTS_TYPE", nullable = true, length = 2)
	public String getVstsType() {
		return vstsType;
	}
	public void setVstsType(String vstsType) {
		this.vstsType = vstsType;
	}
	@Column(name = "NO_OF_VSTS", nullable = true, length = 3)
	public String getNoOfVsts() {
		return noOfVsts;
	}
	public void setNoOfVsts(String noOfVsts) {
		this.noOfVsts = noOfVsts;
	}
	@Column(name = "LOCATION", nullable = true, length = 200)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "OTHER_HOSP", nullable = true, length = 500)
	public String getOtherHosp() {
		return otherHosp;
	}
	public void setOtherHosp(String otherHosp) {
		this.otherHosp = otherHosp;
	}
	@Column(name = "DCTR_RMRKS", nullable = true, length = 500)
	public String getDoctorRemarks() {
		return doctorRemarks;
	}
	public void setDoctorRemarks(String doctorRemarks) {
		this.doctorRemarks = doctorRemarks;
	}
	@Column(columnDefinition="char",name = "MDCL_CNSL_STATUS", nullable = true, length = 1)
	public String getMdclCnslStatus() {
		return mdclCnslStatus;
	}
	public void setMdclCnslStatus(String mdclCnslStatus) {
		this.mdclCnslStatus = mdclCnslStatus;
	}
	@Column(name = "APPRV_STATUS", nullable = true, length = 5)
	public String getApprvStatus() {
		return apprvStatus;
	}
	public void setApprvStatus(String apprvStatus) {
		this.apprvStatus = apprvStatus;
	}
	@Column(name = "IS_DACTV_SHFT_RQSTD", nullable = true, length = 20)
	public String getIsDactvShftRqstd() {
		return isDactvShftRqstd;
	}
	public void setIsDactvShftRqstd(String isDactvShftRqstd) {
		this.isDactvShftRqstd = isDactvShftRqstd;
	}
	@Column(name = "CRT_USR", nullable = true, length = 40)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT", nullable = true, length = 7)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name = "LST_UPD_USR", nullable = true, length = 40)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT", nullable = true, length = 7)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_OF_LEAVING", nullable = true, length = 7)
	public Date getDateOfLeaving() {
		return dateOfLeaving;
	}
	public void setDateOfLeaving(Date dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}
	@Column(name = "OTHER_NTWK_HOSP", nullable = true, length = 100)
	public String getOtherNtwkHosp() {
		return otherNtwkHosp;
	}
	public void setOtherNtwkHosp(String otherNtwkHosp) {
		this.otherNtwkHosp = otherNtwkHosp;
	}
	
	@Column(columnDefinition="char", name = "IS_ACTIVEYN", nullable = false, length = 1)
	public String getIsActiveYn() {
		return isActiveYn;
	}
	public void setIsActiveYn(String isActiveYn) {
		this.isActiveYn = isActiveYn;
	}
	@Column(name="SCHEME")
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	
}
