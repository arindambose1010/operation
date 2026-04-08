package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_CLINICAL_FLPUP_MPG")
public class EhfClinicalFlpupMpg  implements java.io.Serializable{

	private String caseId;
	
	private EhfClinicalFlpupMpgId id;
	private String crtUser;
	private Date crtDt;
	private String lstUpdUser;
	private Date lstUpdDt;
	private Date nxtFollowupDt;

	
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="followUpId", column=@Column(name="followup_id", nullable=false) ), 
        @AttributeOverride(name="clinicalId", column=@Column(name="clinical_id", nullable=false) ) } )  
    public EhfClinicalFlpupMpgId getId() {
		return id;
	}
	public void setId(EhfClinicalFlpupMpgId id) {
		this.id = id;
	}
	
	@Column(name="case_id")
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name="CRT_USR")
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR")
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name="NXT_FOLLOWUP_DT")
	public Date getNxtFollowupDt() {
		return nxtFollowupDt;
	}
	public void setNxtFollowupDt(Date nxtFollowupDt) {
		this.nxtFollowupDt = nxtFollowupDt;
	}
	
	public EhfClinicalFlpupMpg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfClinicalFlpupMpg(String caseId,
			EhfClinicalFlpupMpgId id, String crtUser, Date crtDt,
			String lstUpdUser, Date lstUpdDt) {
		super();
		this.caseId = caseId;
		this.id = id;
		this.crtUser = crtUser;
		this.crtDt = crtDt;
		this.lstUpdUser = lstUpdUser;
		this.lstUpdDt = lstUpdDt;
	}
		
}
