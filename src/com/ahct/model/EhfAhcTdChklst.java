package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_AHC_TD_CHKLST database table.
 * 
 */
@Entity
@Table(name="EHF_AHC_TD_CHKLST")
public class EhfAhcTdChklst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AHC_ID")
	private String ahcId;

    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="EXE_RMKS_REMARKS")
	private String exeRmksRemarks;

	@Column(name="EXE_RMKS_VERIFIED")
	private String exeRmksVerified;

	@Column(name="final_app_amt")
	private String finalApproveAmt;
	
    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	private String remarks;

    public EhfAhcTdChklst() {
    }

	public String getAhcId() {
		return this.ahcId;
	}

	public String getFinalApproveAmt() {
		return finalApproveAmt;
	}

	public void setFinalApproveAmt(String finalApproveAmt) {
		this.finalApproveAmt = finalApproveAmt;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getExeRmksRemarks() {
		return this.exeRmksRemarks;
	}

	public void setExeRmksRemarks(String exeRmksRemarks) {
		this.exeRmksRemarks = exeRmksRemarks;
	}

	public String getExeRmksVerified() {
		return this.exeRmksVerified;
	}

	public void setExeRmksVerified(String exeRmksVerified) {
		this.exeRmksVerified = exeRmksVerified;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}