package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHFM_DUTY_DCTRS database table.
 * 
 */
@Entity
@Table(name="EHFM_DUTY_DCTRS")
public class EhfmDutyDctrs implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmDutyDctrsId id;
	private String apprvStatus;
	private String contactno;
	private Date crtDt;
	private String crtUsr;
	private Date dateOfLeaving;
	private String doctorName;
	private String dctrRmrks;
	private String ddocId;
	private String dept;
	private String experience;
	private String isActiveYn;
	private String isDactvShftRqstd;
	private String isIncharge;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String mdclCnslStatus;
	private String mobile;
	private String reqNo;
	private BigDecimal shiftFrmHrs;
	private BigDecimal shiftFrmMins;
	private String shiftLen;
	private BigDecimal shiftToHrs;
	private BigDecimal shiftToMins;
	private String shiftType;
	private String title;
	private String university;

    public EhfmDutyDctrs() {
    }


	@EmbeddedId
	public EhfmDutyDctrsId getId() {
		return this.id;
	}

	public void setId(EhfmDutyDctrsId id) {
		this.id = id;
	}
	

	@Column(name="APPRV_STATUS")
	public String getApprvStatus() {
		return this.apprvStatus;
	}

	public void setApprvStatus(String apprvStatus) {
		this.apprvStatus = apprvStatus;
	}


	public String getContactno() {
		return this.contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DATE_OF_LEAVING")
	public Date getDateOfLeaving() {
		return this.dateOfLeaving;
	}

	public void setDateOfLeaving(Date dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}


	@Column(name="DCTR_NAME")
	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String dctrName) {
		this.doctorName = dctrName;
	}


	@Column(name="DCTR_RMRKS")
	public String getDctrRmrks() {
		return this.dctrRmrks;
	}

	public void setDctrRmrks(String dctrRmrks) {
		this.dctrRmrks = dctrRmrks;
	}


	@Column(name="DDOC_ID")
	public String getDdocId() {
		return this.ddocId;
	}

	public void setDdocId(String ddocId) {
		this.ddocId = ddocId;
	}


	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}


	@Column(name="IS_ACTIVEYN")
	public String getIsActiveYn() {
		return isActiveYn;
	}


	public void setIsActiveYn(String isActiveYn) {
		this.isActiveYn = isActiveYn;
	}


	@Column(name="IS_DACTV_SHFT_RQSTD")
	public String getIsDactvShftRqstd() {
		return this.isDactvShftRqstd;
	}

	


	public void setIsDactvShftRqstd(String isDactvShftRqstd) {
		this.isDactvShftRqstd = isDactvShftRqstd;
	}


	@Column(name="IS_INCHARGE")
	public String getIsIncharge() {
		return this.isIncharge;
	}

	public void setIsIncharge(String isIncharge) {
		this.isIncharge = isIncharge;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="MDCL_CNSL_STATUS")
	public String getMdclCnslStatus() {
		return this.mdclCnslStatus;
	}

	public void setMdclCnslStatus(String mdclCnslStatus) {
		this.mdclCnslStatus = mdclCnslStatus;
	}


	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Column(name="REQ_NO")
	public String getReqNo() {
		return this.reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}


	@Column(name="SHIFT_FRM_HRS")
	public BigDecimal getShiftFrmHrs() {
		return this.shiftFrmHrs;
	}

	public void setShiftFrmHrs(BigDecimal shiftFrmHrs) {
		this.shiftFrmHrs = shiftFrmHrs;
	}


	@Column(name="SHIFT_FRM_MINS")
	public BigDecimal getShiftFrmMins() {
		return this.shiftFrmMins;
	}

	public void setShiftFrmMins(BigDecimal shiftFrmMins) {
		this.shiftFrmMins = shiftFrmMins;
	}


	@Column(name="SHIFT_LEN")
	public String getShiftLen() {
		return this.shiftLen;
	}

	public void setShiftLen(String shiftLen) {
		this.shiftLen = shiftLen;
	}


	@Column(name="SHIFT_TO_HRS")
	public BigDecimal getShiftToHrs() {
		return this.shiftToHrs;
	}

	public void setShiftToHrs(BigDecimal shiftToHrs) {
		this.shiftToHrs = shiftToHrs;
	}


	@Column(name="SHIFT_TO_MINS")
	public BigDecimal getShiftToMins() {
		return this.shiftToMins;
	}

	public void setShiftToMins(BigDecimal shiftToMins) {
		this.shiftToMins = shiftToMins;
	}


	@Column(name="SHIFT_TYPE")
	public String getShiftType() {
		return this.shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

}