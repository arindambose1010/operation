package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_FOLLOWUP_PACKAGES database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_FOLLOWUP_PACKAGES")
public class EhfmChronicFollowupPackage implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmChronicFollowupPackagePK id;
	private String activeYn;
	private String chronicDisName;
	private String consultAmt;
	private Date crtDt;
	private String crtUsr;
	private String drugAmt;
	private String investAmt;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private BigDecimal pkgAmount;

    public EhfmChronicFollowupPackage() {
    }


	@EmbeddedId
	public EhfmChronicFollowupPackagePK getId() {
		return this.id;
	}

	public void setId(EhfmChronicFollowupPackagePK id) {
		this.id = id;
	}
	

	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}


	@Column(name="CHRONIC_DIS_NAME")
	public String getChronicDisName() {
		return this.chronicDisName;
	}

	public void setChronicDisName(String chronicDisName) {
		this.chronicDisName = chronicDisName;
	}


	@Column(name="CONSULT_AMT")
	public String getConsultAmt() {
		return this.consultAmt;
	}

	public void setConsultAmt(String consultAmt) {
		this.consultAmt = consultAmt;
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


	@Column(name="DRUG_AMT")
	public String getDrugAmt() {
		return this.drugAmt;
	}

	public void setDrugAmt(String drugAmt) {
		this.drugAmt = drugAmt;
	}


	@Column(name="INVEST_AMT")
	public String getInvestAmt() {
		return this.investAmt;
	}

	public void setInvestAmt(String investAmt) {
		this.investAmt = investAmt;
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


	@Column(name="PKG_AMOUNT")
	public BigDecimal getPkgAmount() {
		return this.pkgAmount;
	}

	public void setPkgAmount(BigDecimal pkgAmount) {
		this.pkgAmount = pkgAmount;
	}

}