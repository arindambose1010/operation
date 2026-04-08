package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_AHC_CASE_CLAIM database table.
 * 
 */
@Entity
@Table(name="EHF_AHC_CASE_CLAIM")
public class EhfAhcCaseClaim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AHC_ID")
	private String ahcId;

	@Column(name="ACO_APRV_AMT")
	private BigDecimal acoAprvAmt;

	@Column(name="ACO_REMARKS")
	private String acoRemarks;

	@Column(name="CEO_REMARKS")
	private String ceoRemarks;

	@Column(name="CEX_APRV_AMT")
	private BigDecimal cexAprvAmt;

	@Column(name="CEX_REMARKS")
	private String cexRemarks;

	@Column(name="CH_APRV_AMT")
	private BigDecimal chAprvAmt;

	@Column(name="CH_REMARKS")
	private String chRemarks;

	@Column(name="CLAIM_BILL_AMT")
	private BigDecimal claimBillAmt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CLAIM_BILL_DATE")
	private Date claimBillDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="CTD_APRV_AMT")
	private BigDecimal ctdAprvAmt;

	@Column(name="CTD_REMARKS")
	private String ctdRemarks;

	@Column(name="HOSP_PCT_AMT")
	private BigDecimal hospPctAmt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="MEDCO_REMARKS")
	private String medcoRemarks;

	@Column(name="NAM_APRV_AMT")
	private BigDecimal namAprvAmt;

	@Column(name="NAM_REMARKS")
	private String namRemarks;

	@Column(name="TDS_CESS_AMT")
	private BigDecimal tdsCessAmt;

	@Column(name="TDS_HOSP_PCT_AMT")
	private BigDecimal tdsHospPctAmt;

	@Column(name="TDS_PCT_AMT")
	private BigDecimal tdsPctAmt;

	@Column(name="TDS_SUR_AMT")
	private BigDecimal tdsSurAmt;

	@Column(name="TDS_TAX_AMT")
	private BigDecimal tdsTaxAmt;

	@Column(name="TOT_CLAIM_AMT")
	private BigDecimal totClaimAmt;

	@Column(name="TRUST_PCT_AMT")
	private BigDecimal trustPctAmt;
	
	
	@Column(name="CTD_UPD_REMARKS")
	private String ctdUpdRemarks;
	
	@Column(name="CH_UPD_REMARKS")
	private String chUpdRemarks;

    public EhfAhcCaseClaim() {
    }

	public String getAhcId() {
		return this.ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}

	public BigDecimal getAcoAprvAmt() {
		return this.acoAprvAmt;
	}

	public void setAcoAprvAmt(BigDecimal acoAprvAmt) {
		this.acoAprvAmt = acoAprvAmt;
	}

	public String getAcoRemarks() {
		return this.acoRemarks;
	}

	public void setAcoRemarks(String acoRemarks) {
		this.acoRemarks = acoRemarks;
	}

	public String getCeoRemarks() {
		return this.ceoRemarks;
	}

	public void setCeoRemarks(String ceoRemarks) {
		this.ceoRemarks = ceoRemarks;
	}

	public BigDecimal getCexAprvAmt() {
		return this.cexAprvAmt;
	}

	public void setCexAprvAmt(BigDecimal cexAprvAmt) {
		this.cexAprvAmt = cexAprvAmt;
	}

	public String getCexRemarks() {
		return this.cexRemarks;
	}

	public void setCexRemarks(String cexRemarks) {
		this.cexRemarks = cexRemarks;
	}

	public BigDecimal getChAprvAmt() {
		return this.chAprvAmt;
	}

	public void setChAprvAmt(BigDecimal chAprvAmt) {
		this.chAprvAmt = chAprvAmt;
	}

	public String getChRemarks() {
		return this.chRemarks;
	}

	public void setChRemarks(String chRemarks) {
		this.chRemarks = chRemarks;
	}

	public BigDecimal getClaimBillAmt() {
		return this.claimBillAmt;
	}

	public void setClaimBillAmt(BigDecimal claimBillAmt) {
		this.claimBillAmt = claimBillAmt;
	}

	public Date getClaimBillDate() {
		return this.claimBillDate;
	}

	public void setClaimBillDate(Date claimBillDate) {
		this.claimBillDate = claimBillDate;
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

	public BigDecimal getCtdAprvAmt() {
		return this.ctdAprvAmt;
	}

	public void setCtdAprvAmt(BigDecimal ctdAprvAmt) {
		this.ctdAprvAmt = ctdAprvAmt;
	}

	public String getCtdRemarks() {
		return this.ctdRemarks;
	}

	public void setCtdRemarks(String ctdRemarks) {
		this.ctdRemarks = ctdRemarks;
	}

	public BigDecimal getHospPctAmt() {
		return this.hospPctAmt;
	}

	public void setHospPctAmt(BigDecimal hospPctAmt) {
		this.hospPctAmt = hospPctAmt;
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

	public String getMedcoRemarks() {
		return this.medcoRemarks;
	}

	public void setMedcoRemarks(String medcoRemarks) {
		this.medcoRemarks = medcoRemarks;
	}

	public BigDecimal getNamAprvAmt() {
		return this.namAprvAmt;
	}

	public void setNamAprvAmt(BigDecimal namAprvAmt) {
		this.namAprvAmt = namAprvAmt;
	}

	public String getNamRemarks() {
		return this.namRemarks;
	}

	public void setNamRemarks(String namRemarks) {
		this.namRemarks = namRemarks;
	}

	public BigDecimal getTdsCessAmt() {
		return this.tdsCessAmt;
	}

	public void setTdsCessAmt(BigDecimal tdsCessAmt) {
		this.tdsCessAmt = tdsCessAmt;
	}

	public BigDecimal getTdsHospPctAmt() {
		return this.tdsHospPctAmt;
	}

	public void setTdsHospPctAmt(BigDecimal tdsHospPctAmt) {
		this.tdsHospPctAmt = tdsHospPctAmt;
	}

	public BigDecimal getTdsPctAmt() {
		return this.tdsPctAmt;
	}

	public void setTdsPctAmt(BigDecimal tdsPctAmt) {
		this.tdsPctAmt = tdsPctAmt;
	}

	public BigDecimal getTdsSurAmt() {
		return this.tdsSurAmt;
	}

	public void setTdsSurAmt(BigDecimal tdsSurAmt) {
		this.tdsSurAmt = tdsSurAmt;
	}

	public BigDecimal getTdsTaxAmt() {
		return this.tdsTaxAmt;
	}

	public void setTdsTaxAmt(BigDecimal tdsTaxAmt) {
		this.tdsTaxAmt = tdsTaxAmt;
	}

	public BigDecimal getTotClaimAmt() {
		return this.totClaimAmt;
	}

	public void setTotClaimAmt(BigDecimal totClaimAmt) {
		this.totClaimAmt = totClaimAmt;
	}

	public BigDecimal getTrustPctAmt() {
		return this.trustPctAmt;
	}

	public void setTrustPctAmt(BigDecimal trustPctAmt) {
		this.trustPctAmt = trustPctAmt;
	}

	public String getCtdUpdRemarks() {
		return ctdUpdRemarks;
	}

	public void setCtdUpdRemarks(String ctdUpdRemarks) {
		this.ctdUpdRemarks = ctdUpdRemarks;
	}

	public String getChUpdRemarks() {
		return chUpdRemarks;
	}

	public void setChUpdRemarks(String chUpdRemarks) {
		this.chUpdRemarks = chUpdRemarks;
	}

	
	
}