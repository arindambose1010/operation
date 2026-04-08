package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_PKG_MST database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_PKG_MST")
public class EhfmChronicPkgMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmChronicPkgMstPK id;
	private String activeYn;
	private String asriMainCode;
	private Date crtDt;
	private String crtUsr;
	private String icdCatName;
	private String icdOpPkgName;
	private String langId;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private BigDecimal pkgAmt;

    public EhfmChronicPkgMst() {
    }


	@EmbeddedId
	public EhfmChronicPkgMstPK getId() {
		return this.id;
	}

	public void setId(EhfmChronicPkgMstPK id) {
		this.id = id;
	}
	

	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}


	@Column(name="ASRI_MAIN_CODE")
	public String getAsriMainCode() {
		return this.asriMainCode;
	}

	public void setAsriMainCode(String asriMainCode) {
		this.asriMainCode = asriMainCode;
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


	@Column(name="ICD_CAT_NAME")
	public String getIcdCatName() {
		return this.icdCatName;
	}

	public void setIcdCatName(String icdCatName) {
		this.icdCatName = icdCatName;
	}


	@Column(name="ICD_OP_PKG_NAME")
	public String getIcdOpPkgName() {
		return this.icdOpPkgName;
	}

	public void setIcdOpPkgName(String icdOpPkgName) {
		this.icdOpPkgName = icdOpPkgName;
	}


	@Column(name="LANG_ID")
	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
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


	@Column(name="PKG_AMT")
	public BigDecimal getPkgAmt() {
		return this.pkgAmt;
	}

	public void setPkgAmt(BigDecimal pkgAmt) {
		this.pkgAmt = pkgAmt;
	}

}