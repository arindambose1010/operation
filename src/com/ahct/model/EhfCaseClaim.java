package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;


@Entity
@Table(name = "EHF_CASE_CLAIM")
public class EhfCaseClaim implements java.io.Serializable {
	
	private String caseId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Double claimBillAmt;
	private Double cexAprAmt;
	private Double cpdAprAmt;
	private Double ctdAprAmt;
	private Double secCpdAmt;
	private String  seCpdRmk;
	private Double chAprAmt;
	private Double acoAprAmt;
	private String  medcoRemrk;
	private String  cexRmrk;
	private String  cpdRemrk;
	private String  ctdRemrk;
	private String  chRemrk;
	private String  acoRemrk;
	private Date claimBillDate;
	private String ceoRemark;
	private Double hospPctAmt;
	private Double tdsHospPctAmt;
    private Double tdsPctAmt;
    private Double tdsSurAmt;
    private Double tdsTaxAmt;
    private Double tdsCessAmt;
    private Double trustPctAmt;
    private Double totClaimAmt;
    private Double eoAprAmt;
	private String  eoRemrk;
	private Double eoComAprAmt;
	private String  eoComRemrk;
	private String claimInitAmt;
	private Double chEntAprAmt;
	private Double chNabhAmt;
	private Double eoComEntAprAmt;
	private Double eoComNabhAmt;
	//Chandana - 6444 - 04-02-2025
	private String hubspokeFlag;
    private Long pkgAmt;
    private Double hubAmt;
    private Double spokeAmt;
    private Double agencyAmt;
    private Double agencyPctAmt;
    private Long cycleCount;
    private Double hmAmt;
    private Double smAmt;
	
	@Id
	@Column(name = "case_id", nullable = false)
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	@Column(name = "CRT_USR", length = 30)
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "LST_UPD_USR", length = 30)
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name = "claim_bill_amt")
	public Double getClaimBillAmt() {
		return claimBillAmt;
	}

	public void setClaimBillAmt(Double claimBillAmt) {
		this.claimBillAmt = claimBillAmt;
	}
	
	@Column(name = "cex_aprv_amt")
	public Double getCexAprAmt() {
		return cexAprAmt;
	}

	public void setCexAprAmt(Double cexAprAmt) {
		this.cexAprAmt = cexAprAmt;
	}
	@Column(name = "cpd_aprv_amt")
	public Double getCpdAprAmt() {
		return cpdAprAmt;
	}

	public void setCpdAprAmt(Double cpdAprAmt) {
		this.cpdAprAmt = cpdAprAmt;
	}
	@Column(name = "SEC_CPD_AMT")
	public Double getSecCpdAmt() {
		return secCpdAmt;
	}

	public void setSecCpdAmt(Double secCpdAmt) {
		this.secCpdAmt = secCpdAmt;
	}
	@Column(name = "SEC_CPD_RMRK")
	public String getSeCpdRmk() {
		return seCpdRmk;
	}

	public void setSeCpdRmk(String seCpdRmk) {
		this.seCpdRmk = seCpdRmk;
	}
	@Column(name = "ctd_aprv_amt")
	public Double getCtdAprAmt() {
		return ctdAprAmt;
	}

	public void setCtdAprAmt(Double ctdAprAmt) {
		this.ctdAprAmt = ctdAprAmt;
	}
	@Column(name = "ch_aprv_amt")
	public Double getChAprAmt() {
		return chAprAmt;
	}

	public void setChAprAmt(Double chAprAmt) {
		this.chAprAmt = chAprAmt;
	}
	@Column(name = "medco_remarks")
	public String getMedcoRemrk() {
		return medcoRemrk;
	}

	public void setMedcoRemrk(String medcoRemrk) {
		this.medcoRemrk = medcoRemrk;
	}
	@Column(name = "cex_remarks")
	public String getCexRmrk() {
		return cexRmrk;
	}

	public void setCexRmrk(String cexRmrk) {
		this.cexRmrk = cexRmrk;
	}
	@Column(name = "cpd_remarks")
	public String getCpdRemrk() {
		return cpdRemrk;
	}

	public void setCpdRemrk(String cpdRemrk) {
		this.cpdRemrk = cpdRemrk;
	}
	@Column(name = "ctd_remarks")
	public String getCtdRemrk() {
		return ctdRemrk;
	}

	public void setCtdRemrk(String ctdRemrk) {
		this.ctdRemrk = ctdRemrk;
	}
	@Column(name = "ch_remarks")
	public String getChRemrk() {
		return chRemrk;
	}

	public void setChRemrk(String chRemrk) {
		this.chRemrk = chRemrk;
	}
	@Column(name = "claim_bill_date")
	public Date getClaimBillDate() {
		return claimBillDate;
	}

	public void setClaimBillDate(Date claimBillDate) {
		this.claimBillDate = claimBillDate;
	}
	@Column(name = "aco_aprv_amt")
	public Double getAcoAprAmt() {
		return acoAprAmt;
	}

	public void setAcoAprAmt(Double acoAprAmt) {
		this.acoAprAmt = acoAprAmt;
	}
	@Column(name = "aco_remarks")
	public String getAcoRemrk() {
		return acoRemrk;
	}

	public void setAcoRemrk(String acoRemrk) {
		this.acoRemrk = acoRemrk;
	}
	@Column(name = "ceo_remarks")
	public String getCeoRemark() {
		return ceoRemark;
	}

	public void setCeoRemark(String ceoRemark) {
		this.ceoRemark = ceoRemark;
	}
	
	@Column(name="HOSP_PCT_AMT")
    public Double getHospPctAmt() {
        return hospPctAmt;
    }

    public void setHospPctAmt(Double hospPctAmt) {
        this.hospPctAmt = hospPctAmt;
    }
    
    @Column(name="TDS_CESS_AMT")
    public Double getTdsCessAmt() {
        return tdsCessAmt;
    }

    public void setTdsCessAmt(Double tdsCessAmt) {
        this.tdsCessAmt = tdsCessAmt;
    }

    @Column(name="TDS_HOSP_PCT_AMT")
    public Double getTdsHospPctAmt() {
        return tdsHospPctAmt;
    }

    public void setTdsHospPctAmt(Double tdsHospPctAmt) {
        this.tdsHospPctAmt = tdsHospPctAmt;
    }

    @Column(name="TDS_PCT_AMT")
    public Double getTdsPctAmt() {
        return tdsPctAmt;
    }

    public void setTdsPctAmt(Double tdsPctAmt) {
        this.tdsPctAmt = tdsPctAmt;
    }

    @Column(name="TDS_SUR_AMT")
    public Double getTdsSurAmt() {
        return tdsSurAmt;
    }

    public void setTdsSurAmt(Double tdsSurAmt) {
        this.tdsSurAmt = tdsSurAmt;
    }

    @Column(name="TDS_TAX_AMT")
    public Double getTdsTaxAmt() {
        return tdsTaxAmt;
    }

    public void setTdsTaxAmt(Double tdsTaxAmt) {
        this.tdsTaxAmt = tdsTaxAmt;
    }

    @Column(name="TRUST_PCT_AMT")
    public Double getTrustPctAmt() {
        return trustPctAmt;
    }

    public void setTrustPctAmt(Double trustPctAmt) {
        this.trustPctAmt = trustPctAmt;
    }
    @Column(name="TOT_CLAIM_AMT")
	public Double getTotClaimAmt() {
		return totClaimAmt;
	}

	public void setTotClaimAmt(Double totClaimAmt) {
		this.totClaimAmt = totClaimAmt;
	}
	@Column(name="eo_aprv_amt")
	public Double getEoAprAmt() {
		return eoAprAmt;
	}

	public void setEoAprAmt(Double eoAprAmt) {
		this.eoAprAmt = eoAprAmt;
	}
	@Column(name="eo_remarks")
	public String getEoRemrk() {
		return eoRemrk;
	}

	public void setEoRemrk(String eoRemrk) {
		this.eoRemrk = eoRemrk;
	}
	@Column(name="eo_com_aprv_amt")
	public Double getEoComAprAmt() {
		return eoComAprAmt;
	}

	public void setEoComAprAmt(Double eoComAprAmt) {
		this.eoComAprAmt = eoComAprAmt;
	}
	@Column(name="eo_com_remarks")
	public String getEoComRemrk() {
		return eoComRemrk;
	}

	public void setEoComRemrk(String eoComRemrk) {
		this.eoComRemrk = eoComRemrk;
	}
	@Column(name="claimInitAmt")
	public String getClaimInitAmt() {
		return claimInitAmt;
	}

	public void setClaimInitAmt(String claimInitAmt) {
		this.claimInitAmt = claimInitAmt;
	}
	@Column(name="CH_ENT_APRV_AMT")
	public Double getChEntAprAmt() {
		return chEntAprAmt;
	}

	public void setChEntAprAmt(Double chEntAprAmt) {
		this.chEntAprAmt = chEntAprAmt;
	}
	@Column(name="CH_NABH_AMT")
	public Double getChNabhAmt() {
		return chNabhAmt;
	}

	public void setChNabhAmt(Double chNabhAmt) {
		this.chNabhAmt = chNabhAmt;
	}
	@Column(name="EOCOM_ENT_APRV_AMT")
	public Double getEoComEntAprAmt() {
		return eoComEntAprAmt;
	}

	public void setEoComEntAprAmt(Double eoComEntAprAmt) {
		this.eoComEntAprAmt = eoComEntAprAmt;
	}
	@Column(name="EOCOM_NABH_AMT")
	public Double getEoComNabhAmt() {
		return eoComNabhAmt;
	}

	public void setEoComNabhAmt(Double eoComNabhAmt) {
		this.eoComNabhAmt = eoComNabhAmt;
	}
	//Chandana - 6444 - 04-02-2025
	@Column(name="HUBSPOKE_FLAG")
	public String getHubspokeFlag() {
		return hubspokeFlag;
	}
	public void setHubspokeFlag(String hubspokeFlag) {
		this.hubspokeFlag = hubspokeFlag;
	}
	@Column(name="PKG_AMT")
	public Long getPkgAmt() {
		return pkgAmt;
	}
	public void setPkgAmt(Long pkgAmt) {
		this.pkgAmt = pkgAmt;
	}
	@Column(name="HUB_AMT")
	public Double getHubAmt() {
		return hubAmt;
	}
	public void setHubAmt(Double hubAmt) {
		this.hubAmt = hubAmt;
	}
	@Column(name="SPOKE_AMT")
	public Double getSpokeAmt() {
		return spokeAmt;
	}
	public void setSpokeAmt(Double spokeAmt) {
		this.spokeAmt = spokeAmt;
	}
	@Column(name="AGENCY_AMT")
	public Double getAgencyAmt() {
		return agencyAmt;
	}
	public void setAgencyAmt(Double agencyAmt) {
		this.agencyAmt = agencyAmt;
	}
	@Column(name="CYCLE_CNT")
	public Long getCycleCount() {
		return cycleCount;
	}
	public void setCycleCount(Long cycleCount) {
		this.cycleCount = cycleCount;
	}
	@Column(name="HM_AMT")
	public Double getHmAmt() {
		return hmAmt;
	}
	public void setHmAmt(Double hmAmt) {
		this.hmAmt = hmAmt;
	}
	@Column(name="SM_AMT")
	public Double getSmAmt() {
		return smAmt;
	}
	public void setSmAmt(Double smAmt) {
		this.smAmt = smAmt;
	}
	@Column(name="AGENCY_PCT_AMT")
	public Double getAgencyPctAmt() {
		return agencyPctAmt;
	}
	public void setAgencyPctAmt(Double agencyPctAmt) {
		this.agencyPctAmt = agencyPctAmt;
	}
	
	
	/*private EhfCase ehfCase;
	@OneToOne(fetch=FetchType.LAZY,mappedBy="ehfCaseClaim",cascade=CascadeType.ALL)
	public EhfCase getEhfCase() {
		return ehfCase;
	}

	public void setEhfCase(EhfCase ehfCase) {
		this.ehfCase = ehfCase;
	}
	*/
	
	
}
