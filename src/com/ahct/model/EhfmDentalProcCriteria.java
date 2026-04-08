package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_DENTAL_PROC_CRITERIA database table.
 * 
 */
@Entity
@Table(name="EHFM_DENTAL_PROC_CRITERIA")
public class EhfmDentalProcCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmDentalProcCriteriaPK id;
	private String activeYn;
	private String ageLimit;
	private String comoboProcCode;
	private Date crtDt;
	private String crtUsr;
	private String frameworkPrice;
	private String ipOp;
	private String lifetimeUnitsLimit;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String nonComboProcCode;
	private String standaloneProc;
	private String subsequentPrice;
	private String unitsLimit;
	private String lifeTimeMonths;
	

    public EhfmDentalProcCriteria() {
    }


	@EmbeddedId
	public EhfmDentalProcCriteriaPK getId() {
		return this.id;
	}

	public void setId(EhfmDentalProcCriteriaPK id) {
		this.id = id;
	}
	

	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}


	@Column(name="AGE_LIMIT")
	public String getAgeLimit() {
		return this.ageLimit;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}


	@Column(name="COMBO_PROC_CODE")
	public String getComoboProcCode() {
		return this.comoboProcCode;
	}

	public void setComoboProcCode(String comoboProcCode) {
		this.comoboProcCode = comoboProcCode;
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


	@Column(name="FRAMEWORK_PRICE")
	public String getFrameworkPrice() {
		return this.frameworkPrice;
	}

	public void setFrameworkPrice(String frameworkPrice) {
		this.frameworkPrice = frameworkPrice;
	}


	@Column(name="IP_OP")
	public String getIpOp() {
		return this.ipOp;
	}

	public void setIpOp(String ipOp) {
		this.ipOp = ipOp;
	}


	@Column(name="LIFETIME_UNITS_LIMIT")
	public String getLifetimeUnitsLimit() {
		return this.lifetimeUnitsLimit;
	}

	public void setLifetimeUnitsLimit(String lifetimeUnitsLimit) {
		this.lifetimeUnitsLimit = lifetimeUnitsLimit;
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


	@Column(name="NON_COMBO_PROC_CODE")
	public String getNonComboProcCode() {
		return this.nonComboProcCode;
	}

	public void setNonComboProcCode(String nonComboProcCode) {
		this.nonComboProcCode = nonComboProcCode;
	}


	@Column(name="STANDALONE_PROC")
	public String getStandaloneProc() {
		return standaloneProc;
	}


	public void setStandaloneProc(String standaloneProc) {
		this.standaloneProc = standaloneProc;
	}


	@Column(name="SUBSEQUENT_PRICE")
	public String getSubsequentPrice() {
		return this.subsequentPrice;
	}



	public void setSubsequentPrice(String subsequentPrice) {
		this.subsequentPrice = subsequentPrice;
	}


	@Column(name="UNITS_LIMIT")
	public String getUnitsLimit() {
		return this.unitsLimit;
	}

	public void setUnitsLimit(String unitsLimit) {
		this.unitsLimit = unitsLimit;
	}

	@Column(name="life_time_months")
	public String getLifeTimeMonths() {
		return lifeTimeMonths;
	}


	public void setLifeTimeMonths(String lifeTimeMonths) {
		this.lifeTimeMonths = lifeTimeMonths;
	}

	
	
}