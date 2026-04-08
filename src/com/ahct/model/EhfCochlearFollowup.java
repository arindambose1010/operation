package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_COCHLEAR_FOLLOWUP database table.
 * 
 */
@Entity
@Table(name="EHF_COCHLEAR_FOLLOWUP")
public class EhfCochlearFollowup implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cochlearFollowupId;
	private String audiologist;
	private Date avTheraphyFromDate;
	private Date avTheraphyToDate;
	private String avtheraphyAudiologist;
	private String caseId;
	private String childProgressRemarks;
	private Date crtDt;
	private String crtUsr;
	private Date dateOfSwitchOn;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String postopPeriod;
	private String postopRemarks;
	private Date reviewDate;
	private String woundSight;
	private String cochlearFollowupCount;
	private String followupType;
	private String patientScheme;
	private String followupProc;
	private String schemeId;
	

    public EhfCochlearFollowup() {
    }


	@Id
	@Column(name="COCHLEAR_FOLLOWUP_ID")
	public String getCochlearFollowupId() {
		return this.cochlearFollowupId;
	}

	public void setCochlearFollowupId(String cochlearFollowupId) {
		this.cochlearFollowupId = cochlearFollowupId;
	}


	public String getAudiologist() {
		return this.audiologist;
	}

	public void setAudiologist(String audiologist) {
		this.audiologist = audiologist;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="AV_THERAPHY_FROM_DATE")
	public Date getAvTheraphyFromDate() {
		return this.avTheraphyFromDate;
	}

	public void setAvTheraphyFromDate(Date avTheraphyFromDate) {
		this.avTheraphyFromDate = avTheraphyFromDate;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="AV_THERAPHY_TO_DATE")
	public Date getAvTheraphyToDate() {
		return this.avTheraphyToDate;
	}

	public void setAvTheraphyToDate(Date avTheraphyToDate) {
		this.avTheraphyToDate = avTheraphyToDate;
	}


	@Column(name="AVTHERAPHY_AUDIOLOGIST")
	public String getAvtheraphyAudiologist() {
		return this.avtheraphyAudiologist;
	}

	public void setAvtheraphyAudiologist(String avtheraphyAudiologist) {
		this.avtheraphyAudiologist = avtheraphyAudiologist;
	}


	@Column(name="CASE_ID")
	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}


	@Column(name="CHILD_PROGRESS_REMARKS")
	public String getChildProgressRemarks() {
		return this.childProgressRemarks;
	}

	public void setChildProgressRemarks(String childProgressRemarks) {
		this.childProgressRemarks = childProgressRemarks;
	}


	@Temporal( TemporalType.TIMESTAMP)
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
	@Column(name="DATE_OF_SWITCH_ON")
	public Date getDateOfSwitchOn() {
		return this.dateOfSwitchOn;
	}

	public void setDateOfSwitchOn(Date dateOfSwitchOn) {
		this.dateOfSwitchOn = dateOfSwitchOn;
	}


    @Temporal( TemporalType.TIMESTAMP)
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


	@Column(name="POSTOP_PERIOD")
	public String getPostopPeriod() {
		return this.postopPeriod;
	}

	public void setPostopPeriod(String postopPeriod) {
		this.postopPeriod = postopPeriod;
	}


	@Column(name="POSTOP_REMARKS")
	public String getPostopRemarks() {
		return this.postopRemarks;
	}

	public void setPostopRemarks(String postopRemarks) {
		this.postopRemarks = postopRemarks;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="REVIEW_DATE")
	public Date getReviewDate() {
		return this.reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	@Column(name="WOUND_SIGHT")
	public String getWoundSight() {
		return this.woundSight;
	}

	public void setWoundSight(String woundSight) {
		this.woundSight = woundSight;
	}

	@Column(name="COCHLEAR_FOLLOWUP_COUNT")
	public String getCochlearFollowupCount() {
		return cochlearFollowupCount;
	}


	public void setCochlearFollowupCount(String cochlearFollowupCount) {
		this.cochlearFollowupCount = cochlearFollowupCount;
	}

	@Column(name="FOLLOWUP_TYPE")
	public String getFollowupType() {
		return followupType;
	}


	public void setFollowupType(String followupType) {
		this.followupType = followupType;
	}

	
	@Column(name="PATIENT_SCHEME")
	public String getPatientScheme() {
		return patientScheme;
	}


	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}

	@Column(name="FOLLOWUP_PROC")
	public String getFollowupProc() {
		return followupProc;
	}


	public void setFollowupProc(String followupProc) {
		this.followupProc = followupProc;
	}

	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return schemeId;
	}


	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	
}