package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_AHC_INVESTIGATION_DTLS database table.
 * 
 */
@Entity
@Table(name="EHFM_AHC_INVESTIGATION_DTLS")
public class EhfmAhcInvestigationDtl implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sNo;
	private String activeYn;
	private Date crtDt;
	private String crtUsr;
	private String gender;
	private String testId;
	private String investigationName;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String price;
	private String schemeId;

    public EhfmAhcInvestigationDtl() {
    }


	@Id
	@Column(name="S_NO")
	public String getSNo() {
		return this.sNo;
	}

	public void setSNo(String sNo) {
		this.sNo = sNo;
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


	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	@Column(name="INVESTIGATION_NAME")
	public String getInvestigationName() {
		return this.investigationName;
	}

	public void setInvestigationName(String investigationName) {
		this.investigationName = investigationName;
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


	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name="TEST_ID")
	public String getTestId() {
		return testId;
	}


	public void setTestId(String testId) {
		this.testId = testId;
	}
	
	
	

}