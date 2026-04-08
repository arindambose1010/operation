package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_CASE_CLAIM database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_CASE_CLAIM")
public class EhfChronicCaseClaim implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal acoAprvAmt;
	private String acoRemarks;
	private BigDecimal ceoAprvAmt;
	private String ceoRemarks;
	private BigDecimal cexAprvAmt;
	private String cexRemarks;
	private BigDecimal chAprvAmt;
	private String chRemarks;
	private String chronicId;
	private String chronicNo;
	private BigDecimal claimBillAmt;
	private Date claimBillDate;
	private String claiminitamt;
	private Date crtDt;
	private String crtUsr;
	private BigDecimal ctdAprvAmt;
	private String ctdRemarks;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String medcoRemarks;
	private BigDecimal totClaimAmt;
	private String mithraRemarks;

    public EhfChronicCaseClaim() {
    }


	@Column(name="ACO_APRV_AMT")
	public BigDecimal getAcoAprvAmt() {
		return this.acoAprvAmt;
	}

	public void setAcoAprvAmt(BigDecimal acoAprvAmt) {
		this.acoAprvAmt = acoAprvAmt;
	}


	@Column(name="ACO_REMARKS")
	public String getAcoRemarks() {
		return this.acoRemarks;
	}

	public void setAcoRemarks(String acoRemarks) {
		this.acoRemarks = acoRemarks;
	}


	@Column(name="CEO_APRV_AMT")
	public BigDecimal getCeoAprvAmt() {
		return this.ceoAprvAmt;
	}

	public void setCeoAprvAmt(BigDecimal ceoAprvAmt) {
		this.ceoAprvAmt = ceoAprvAmt;
	}


	@Column(name="CEO_REMARKS")
	public String getCeoRemarks() {
		return this.ceoRemarks;
	}

	public void setCeoRemarks(String ceoRemarks) {
		this.ceoRemarks = ceoRemarks;
	}


	@Column(name="CEX_APRV_AMT")
	public BigDecimal getCexAprvAmt() {
		return this.cexAprvAmt;
	}

	public void setCexAprvAmt(BigDecimal cexAprvAmt) {
		this.cexAprvAmt = cexAprvAmt;
	}


	@Column(name="CEX_REMARKS")
	public String getCexRemarks() {
		return this.cexRemarks;
	}

	public void setCexRemarks(String cexRemarks) {
		this.cexRemarks = cexRemarks;
	}


	@Column(name="CH_APRV_AMT")
	public BigDecimal getChAprvAmt() {
		return this.chAprvAmt;
	}

	public void setChAprvAmt(BigDecimal chAprvAmt) {
		this.chAprvAmt = chAprvAmt;
	}


	@Column(name="CH_REMARKS")
	public String getChRemarks() {
		return this.chRemarks;
	}

	public void setChRemarks(String chRemarks) {
		this.chRemarks = chRemarks;
	}


	@Column(name="CHRONIC_ID")
	public String getChronicId() {
		return this.chronicId;
	}

	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

    @Id
	@Column(name="CHRONIC_NO")
	public String getChronicNo() {
		return this.chronicNo;
	}

	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}


	@Column(name="CLAIM_BILL_AMT")
	public BigDecimal getClaimBillAmt() {
		return this.claimBillAmt;
	}

	public void setClaimBillAmt(BigDecimal claimBillAmt) {
		this.claimBillAmt = claimBillAmt;
	}

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CLAIM_BILL_DATE")
	public Date getClaimBillDate() {
		return this.claimBillDate;
	}

	public void setClaimBillDate(Date claimBillDate) {
		this.claimBillDate = claimBillDate;
	}


	public String getClaiminitamt() {
		return this.claiminitamt;
	}

	public void setClaiminitamt(String claiminitamt) {
		this.claiminitamt = claiminitamt;
	}

	@Temporal( TemporalType.TIMESTAMP)
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


	@Column(name="CTD_APRV_AMT")
	public BigDecimal getCtdAprvAmt() {
		return this.ctdAprvAmt;
	}

	public void setCtdAprvAmt(BigDecimal ctdAprvAmt) {
		this.ctdAprvAmt = ctdAprvAmt;
	}


	@Column(name="CTD_REMARKS")
	public String getCtdRemarks() {
		return this.ctdRemarks;
	}

	public void setCtdRemarks(String ctdRemarks) {
		this.ctdRemarks = ctdRemarks;
	}


    @Temporal( TemporalType.TIMESTAMP)
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


	@Column(name="MEDCO_REMARKS")
	public String getMedcoRemarks() {
		return this.medcoRemarks;
	}

	public void setMedcoRemarks(String medcoRemarks) {
		this.medcoRemarks = medcoRemarks;
	}


	@Column(name="TOT_CLAIM_AMT")
	public BigDecimal getTotClaimAmt() {
		return this.totClaimAmt;
	}

	public void setTotClaimAmt(BigDecimal totClaimAmt) {
		this.totClaimAmt = totClaimAmt;
	}

	@Column(name="MITHRA_REMARKS")
	public String getMithraRemarks() {
		return mithraRemarks;
	}


	public void setMithraRemarks(String mithraRemarks) {
		this.mithraRemarks = mithraRemarks;
	}
	
	

}