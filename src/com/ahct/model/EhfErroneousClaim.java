package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_ERRONEOUS_CLAIM")

public class EhfErroneousClaim  implements Serializable{

	private String errClaimId;
	private String caseId;
	private String errClaimStatus;
	private Double claimAmt;
	private Double errClaimAmt;
	private Date errSubDate;
	private String medcoRemark; 
	private Double ctdAprvAmt;
	private String ctdRemark;
	private Double chAprvAmt;
	private String chRemark;
	private Double hospPctAmt;
	private Double tdsHospPctAmt;
    private Double tdsPctAmt;
    private Double tdsSurAmt;
    private Double tdsTaxAmt;
    private Double tdsCessAmt;
    private Double trustPctAmt;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private Double totClaimAmt;
	private Double acoAprAmt;
	private String  acoRemrk;
    private String filename;
    private String paymentCheck;
    private Date paymentSentDate;
    private String schemeId;
    
	@Id
	@Column(name = "err_claim_id")
	public String getErrClaimId() {
		return errClaimId;
	}
	public void setErrClaimId(String errClaimId) {
		this.errClaimId = errClaimId;
	}
	@Column(name = "case_id",nullable=false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name = "err_claim_status")
	public String getErrClaimStatus() {
		return errClaimStatus;
	}
	public void setErrClaimStatus(String errClaimStatus) {
		this.errClaimStatus = errClaimStatus;
	}
	@Column(name = "claim_amount")
	public Double getClaimAmt() {
		return claimAmt;
	}
	public void setClaimAmt(Double claimAmt) {
		this.claimAmt = claimAmt;
	}
	@Column(name = "err_claim_amount")
	public Double getErrClaimAmt() {
		return errClaimAmt;
	}
	public void setErrClaimAmt(Double errClaimAmt) {
		this.errClaimAmt = errClaimAmt;
	}
	@Column(name = "err_sub_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getErrSubDate() {
		return errSubDate;
	}
	public void setErrSubDate(Date errSubDate) {
		this.errSubDate = errSubDate;
	}
	@Column(name = "medco_remarks")
	public String getMedcoRemark() {
		return medcoRemark;
	}
	public void setMedcoRemark(String medcoRemark) {
		this.medcoRemark = medcoRemark;
	}
	@Column(name = "ctd_aprv_amt")
	public Double getCtdAprvAmt() {
		return ctdAprvAmt;
	}
	public void setCtdAprvAmt(Double ctdAprvAmt) {
		this.ctdAprvAmt = ctdAprvAmt;
	}
	@Column(name = "ctd_remarks")
	public String getCtdRemark() {
		return ctdRemark;
	}
	public void setCtdRemark(String ctdRemark) {
		this.ctdRemark = ctdRemark;
	}
	@Column(name = "CRT_DT", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Column(name = "LST_UPD_DT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	@Column(name = "LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Column(name = "ch_aprv_amt")
	public Double getChAprvAmt() {
		return chAprvAmt;
	}
	public void setChAprvAmt(Double chAprvAmt) {
		this.chAprvAmt = chAprvAmt;
	}
	@Column(name = "ch_remarks")
	public String getChRemark() {
		return chRemark;
	}
	public void setChRemark(String chRemark) {
		this.chRemark = chRemark;
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
	
	@Column(name="file_name")
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	 @Column(name="payment_check")
	public String getPaymentCheck() {
		return paymentCheck;
	}
	public void setPaymentCheck(String paymentCheck) {
		this.paymentCheck = paymentCheck;
	}
	 @Column(name="payment_sent_date")
	public Date getPaymentSentDate() {
		return paymentSentDate;
	}
	public void setPaymentSentDate(Date paymentSentDate) {
		this.paymentSentDate = paymentSentDate;
	}	
	
	 @Column(name="SCHEME_ID")
	    public String getSchemeId() {
			return schemeId;
		}

		public void setSchemeId(String schemeId) {
			this.schemeId = schemeId;
		}

}
