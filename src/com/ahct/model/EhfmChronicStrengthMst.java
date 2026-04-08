package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_STRENGTH_MST database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_STRENGTH_MST")
public class EhfmChronicStrengthMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private String strengthCode;
	private String activeYn;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String strengthName;
	private String strengthUnitCode;
	private String strengthUnitName;

    public EhfmChronicStrengthMst() {
    }


	@Id
	@Column(name="STRENGTH_CODE")
	public String getStrengthCode() {
		return this.strengthCode;
	}

	public void setStrengthCode(String strengthCode) {
		this.strengthCode = strengthCode;
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


	@Column(name="STRENGTH_NAME")
	public String getStrengthName() {
		return this.strengthName;
	}

	public void setStrengthName(String strengthName) {
		this.strengthName = strengthName;
	}


	@Column(name="STRENGTH_UNIT_CODE")
	public String getStrengthUnitCode() {
		return this.strengthUnitCode;
	}

	public void setStrengthUnitCode(String strengthUnitCode) {
		this.strengthUnitCode = strengthUnitCode;
	}


	@Column(name="STRENGTH_UNIT_NAME")
	public String getStrengthUnitName() {
		return this.strengthUnitName;
	}

	public void setStrengthUnitName(String strengthUnitName) {
		this.strengthUnitName = strengthUnitName;
	}

}