package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_THERAPY_INVEST database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_THERAPY_INVEST")
public class EhfChronicTherapyInvest implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfChronicTherapyInvestPK id;
	private String activeyn;
	private String attachTotalPath;
	private String chronicCatCode;
	private Date crtDt;
	private String crtUsr;
	private String filename;
	private String investigationStage;
	private Date lstUpdDt;
	private String lstUpdUsr;

    public EhfChronicTherapyInvest() {
    }


	@EmbeddedId
	public EhfChronicTherapyInvestPK getId() {
		return this.id;
	}

	public void setId(EhfChronicTherapyInvestPK id) {
		this.id = id;
	}
	

	public String getActiveyn() {
		return this.activeyn;
	}

	public void setActiveyn(String activeyn) {
		this.activeyn = activeyn;
	}


	@Column(name="ATTACH_TOTAL_PATH")
	public String getAttachTotalPath() {
		return this.attachTotalPath;
	}

	public void setAttachTotalPath(String attachTotalPath) {
		this.attachTotalPath = attachTotalPath;
	}


	@Column(name="CHRONIC_CAT_CODE")
	public String getChronicCatCode() {
		return this.chronicCatCode;
	}

	public void setChronicCatCode(String chronicCatCode) {
		this.chronicCatCode = chronicCatCode;
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


	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	@Column(name="INVESTIGATION_STAGE")
	public String getInvestigationStage() {
		return this.investigationStage;
	}

	public void setInvestigationStage(String investigationStage) {
		this.investigationStage = investigationStage;
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

}