package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_PNLDOC_STATUS_MPG database table.
 * 
 */
@Entity
@Table(name="EHFM_PNLDOC_STATUS_MPG")
public class EhfmPnldocStatusMpg implements Serializable {
	private static final long serialVersionUID = 1L;
	private String refId;
	private String activeYn;
	private Date crtDt;
	private String crtUsr;
	private String currentOwnerGrp;
	private String currentWorkflowId;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String scheme;

    public EhfmPnldocStatusMpg() {
    }


	@Id
	@Column(name="REF_ID")
	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}


	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
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


	@Column(name="CURRENT_OWNER_GRP")
	public String getCurrentOwnerGrp() {
		return this.currentOwnerGrp;
	}

	public void setCurrentOwnerGrp(String currentOwnerGrp) {
		this.currentOwnerGrp = currentOwnerGrp;
	}


	@Column(name="CURRENT_WORKFLOW_ID")
	public String getCurrentWorkflowId() {
		return this.currentWorkflowId;
	}

	public void setCurrentWorkflowId(String currentWorkflowId) {
		this.currentWorkflowId = currentWorkflowId;
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


	public String getScheme() {
		return this.scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

}