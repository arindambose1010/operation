package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_INVEST_MST database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_INVEST_MST")
public class EhfmChronicInvestMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmChronicInvestMstPK id;
	private String activeYn;
	private Date crtDt;
	private String crtUsr;
	private String invMainCode;
	private String invMainName;
	private String invName;
	private String langId;
	private Date lstUpdDt;
	private String lstUpdUsr;

    public EhfmChronicInvestMst() {
    }


	@EmbeddedId
	public EhfmChronicInvestMstPK getId() {
		return this.id;
	}

	public void setId(EhfmChronicInvestMstPK id) {
		this.id = id;
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


	@Column(name="INV_MAIN_CODE")
	public String getInvMainCode() {
		return this.invMainCode;
	}

	public void setInvMainCode(String invMainCode) {
		this.invMainCode = invMainCode;
	}


	@Column(name="INV_MAIN_NAME")
	public String getInvMainName() {
		return this.invMainName;
	}

	public void setInvMainName(String invMainName) {
		this.invMainName = invMainName;
	}


	@Column(name="INV_NAME")
	public String getInvName() {
		return this.invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
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

}