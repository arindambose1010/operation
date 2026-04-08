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
@Table

( name = "EHFM_PHASE_MST" )
public class PhaseMaster implements Serializable{
	private String phaseId;
	private String phaseName;
	private String phaseDesc;
	private String implName;
	private String pcaYN;
	private String schemeId;
	private Date startDt;
	private Date endDt;
	private String crtUsr;
	private String lastUpdUsr;
	private Date crtDt;
	private Date lastUpdDt;
	private String prevPhaseId;
	private String langId;
	
	



	public PhaseMaster() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PhaseMaster(String phaseId, String phaseName, String phaseDesc,
			String implName, String pcaYN, String schemeId, Date startDt,
			Date endDt, String crtUsr, String lastUpdUsr, Date crtDt,
			Date lastUpdDt, String prevPhaseId, String langId) {
		super();
		this.phaseId = phaseId;
		this.phaseName = phaseName;
		this.phaseDesc = phaseDesc;
		this.implName = implName;
		this.pcaYN = pcaYN;
		this.schemeId = schemeId;
		this.startDt = startDt;
		this.endDt = endDt;
		this.crtUsr = crtUsr;
		this.lastUpdUsr = lastUpdUsr;
		this.crtDt = crtDt;
		this.lastUpdDt = lastUpdDt;
		this.prevPhaseId = prevPhaseId;
		this.langId = langId;
	}


	@Id
	 @Column(name="phase_id", nullable=false, length=20)
	public String getPhaseId() {
		return phaseId;
	}


	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	 @Column(name="phase_name", nullable=true, length=20)
	public String getPhaseName() {
		return phaseName;
	}


	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	 @Column(name="phase_desc", nullable=true, length=20)
	public String getPhaseDesc() {
		return phaseDesc;
	}


	public void setPhaseDesc(String phaseDesc) {
		this.phaseDesc = phaseDesc;
	}

	 @Column(name="Impl_name", nullable=true, length=20)
	public String getImplName() {
		return implName;
	}


	public void setImplName(String implName) {
		this.implName = implName;
	}

	 @Column(name="pca_yn", nullable=true, length=20)
	public String getPcaYN() {
		return pcaYN;
	}


	public void setPcaYN(String pcaYN) {
		this.pcaYN = pcaYN;
	}

	 @Column(name="scheme_id", nullable=true, length=20)
	public String getSchemeId() {
		return schemeId;
	}


	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_dt", nullable=true)
	public Date getStartDt() {
		return startDt;
	}


	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_dt", nullable=true)
	public Date getEndDt() {
		return endDt;
	}


	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}


	 @Column(name="crt_usr", nullable=true, length=20)
	public String getCrtUsr() {
		return crtUsr;
	}



	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	 @Column(name="last_upd_usr", nullable=true, length=20)
	public String getLastUpdUsr() {
		return lastUpdUsr;
	}



	public void setLastUpdUsr(String lastUpdUsr) {
		this.lastUpdUsr = lastUpdUsr;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="crt_dt", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}



	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_upd_dt", nullable=true)
	public Date getLastUpdDt() {
		return lastUpdDt;
	}



	public void setLastUpdDt(Date lastUpdDt) {
		this.lastUpdDt = lastUpdDt;
	}


	 @Column(name="prev_phase_id", nullable=true, length=20)
	public String getPrevPhaseId() {
		return prevPhaseId;
	}



	public void setPrevPhaseId(String prevPhaseId) {
		this.prevPhaseId = prevPhaseId;
	}

	 @Column(name="lang_id", nullable=true, length=20)
	public String getLangId() {
		return langId;
	}


	public void setLangId(String langId) {
		this.langId = langId;
	}
	
	
	
	
	
	

}
