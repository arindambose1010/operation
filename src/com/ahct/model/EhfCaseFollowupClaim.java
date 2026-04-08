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
@Table(name="EHF_CASE_FOLLOWUP_CLAIM")
public class EhfCaseFollowupClaim implements Serializable{
	
	public String caseFollowupId;
	public String caseId;
	public String paymentStatus;
	private String followUpStatus;
	public Double claimAmount;
	public Long actualPack;
	public Double claimNwhAmount;
	private String nwhRemark;
	private String namRemark;
	public Double claimNamAmount;
	private String fcxRemark;
	public Double claimFcxAmount;
	private String ftdRemark;
	public Double claimFtdAmount;
	private String chRemark;
	public Double claimChAmount;
	public Date flupClaimSubDate; 
	private Double hospPctAmt;
	private Double tdsHospPctAmt;
    private Double tdsPctAmt;
    private Double tdsSurAmt;
    private Double tdsTaxAmt;
    private Double tdsCessAmt;
    private Double trustPctAmt;
	private Date crtDt;
    private String crtUsr;
    private String lstUpdUsr;
    private Date lstUpdDt;
    private Double totClaimAmt;
    private String filename;
    private String paymentCheck;
    private Date paymentSentDate;
    private Double acoAprAmt;
    private String  acoRemrk;
    private String ceoRemark; 
	private String viewFlag;
    private Date caseBlockedDt;	
    private String schemeId;
	public String cexPharmacyBill;
	public String cexConsultBill;
	public String cexInvestBill;
	public String FTDPharmacyBill;
	public String FTDConsultBill;
	public String FTDInvestBill;
	public String CHPharmacyBill;
	public String CHConsultBill;
	public String CHInvestBill;
	private Double carryFwdAmt;
	private String pendingWith;
	private String cochlearYN;
	private Double claimCocCmtAmt;
	private Double claimDyEoAmt;
	private String coccmtRemark;
	private String dyeoRemark;
	private String patientScheme;
	private String sentbackUsrRemrks;
	
   @Id
   @Column(name="case_followup_id",nullable=false)
	public String getCaseFollowupId() {
		return caseFollowupId;
	}
	public void setCaseFollowupId(String caseFollowupId) {
		this.caseFollowupId = caseFollowupId;
	}
	@Column(name="case_id")
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name="payment_status")
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Column(name="followup_status")
	public String getFollowUpStatus() {
		return followUpStatus;
	}
	public void setFollowUpStatus(String followUpStatus) {
		this.followUpStatus = followUpStatus;
	}
	@Column(name="claim_paid")
	public Double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}
	
	@Column(name="Actual_package")
	public Long getActualPack() {
		return actualPack;
	}
	public void setActualPack(Long actualPack) {
		this.actualPack = actualPack;
	}
	@Column(name="claim_nhw_amt")
	public Double getClaimNwhAmount() {
		return claimNwhAmount;
	}
	public void setClaimNwhAmount(Double claimNwhAmount) {
		this.claimNwhAmount = claimNwhAmount;
	}
	@Column(name="flup_claim_sub_date")
	public Date getFlupClaimSubDate() {
		return flupClaimSubDate;
	}
	public void setFlupClaimSubDate(Date flupClaimSubDate) {
		this.flupClaimSubDate = flupClaimSubDate;
	}
	 @Temporal(TemporalType.TIMESTAMP)
		@Column(name="CRT_DT", nullable = false)
		public Date getCrtDt() {
			return crtDt;
		}
		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}
		@Column(name="crt_by", nullable = false)
		public String getCrtUsr() {
			return crtUsr;
		}
		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}

	@Column(name="lst_upd_by")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	 @Temporal(TemporalType.TIMESTAMP)
		@Column(name="lst_upd_dt")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="nwh_remark")
	public String getNwhRemark() {
		return nwhRemark;
	}
	public void setNwhRemark(String nwhRemark) {
		this.nwhRemark = nwhRemark;
	}
	@Column(name="ftd_remark")
	public String getFtdRemark() {
		return ftdRemark;
	}
	public void setFtdRemark(String ftdRemark) {
		this.ftdRemark = ftdRemark;
	}
	@Column(name="nam_remark")
	public String getNamRemark() {
		return namRemark;
	}
	public void setNamRemark(String namRemark) {
		this.namRemark = namRemark;
	}
	@Column(name="claim_nam_amt")
	public Double getClaimNamAmount() {
		return claimNamAmount;
	}
	public void setClaimNamAmount(Double claimNamAmount) {
		this.claimNamAmount = claimNamAmount;
	}
	@Column(name="fcx_remark")
	public String getFcxRemark() {
		return fcxRemark;
	}
	public void setFcxRemark(String fcxRemark) {
		this.fcxRemark = fcxRemark;
	}
	@Column(name="claim_fcx_amt")
	public Double getClaimFcxAmount() {
		return claimFcxAmount;
	}
	public void setClaimFcxAmount(Double claimFcxAmount) {
		this.claimFcxAmount = claimFcxAmount;
	}
	@Column(name="claim_ftd_amt")
	public Double getClaimFtdAmount() {
		return claimFtdAmount;
	}
	public void setClaimFtdAmount(Double claimFtdAmount) {
		this.claimFtdAmount = claimFtdAmount;
	}
	@Column(name="ch_remark")
	public String getChRemark() {
		return chRemark;
	}
	public void setChRemark(String chRemark) {
		this.chRemark = chRemark;
	}
	@Column(name="claim_ch_amt")
	public Double getClaimChAmount() {
		return claimChAmount;
	}
	public void setClaimChAmount(Double claimChAmount) {
		this.claimChAmount = claimChAmount;
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
	
	@Column(name="view_flag")
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	@Column(name="case_blocked_dt")
	public Date getCaseBlockedDt() {
		return caseBlockedDt;
	}
	public void setCaseBlockedDt(Date caseBlockedDt) {
		this.caseBlockedDt = caseBlockedDt;
	}
	@Column(name="scheme_id")
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	@Column(name="CEX_PHARMACY_BILL")
	public String getCexPharmacyBill() {
		return cexPharmacyBill;
	}
	public void setCexPharmacyBill(String cexPharmacyBill) {
		this.cexPharmacyBill = cexPharmacyBill;
	}
	
	@Column(name="CEX_CONSULT_BILL")
	public String getCexConsultBill() {
		return cexConsultBill;
	}
	public void setCexConsultBill(String cexConsultBill) {
		this.cexConsultBill = cexConsultBill;
	}
	
	@Column(name="CEX_INVEST_BILL")
	public String getCexInvestBill() {
		return cexInvestBill;
	}
	public void setCexInvestBill(String cexInvestBill) {
		this.cexInvestBill = cexInvestBill;
	}
	
	@Column(name="FTD_PHARMACY_BILL")
	public String getFTDPharmacyBill() {
		return FTDPharmacyBill;
	}
	public void setFTDPharmacyBill(String fTDPharmacyBill) {
		FTDPharmacyBill = fTDPharmacyBill;
	}
	
	@Column(name="FTD_CONSULT_BILL")
	public String getFTDConsultBill() {
		return FTDConsultBill;
	}
	public void setFTDConsultBill(String fTDConsultBill) {
		FTDConsultBill = fTDConsultBill;
	}
	
	@Column(name="FTD_INVEST_BILL")
	public String getFTDInvestBill() {
		return FTDInvestBill;
	}
	public void setFTDInvestBill(String fTDInvestBill) {
		FTDInvestBill = fTDInvestBill;
	}
	
	@Column(name="CH_PHARMACY_BILL")
	public String getCHPharmacyBill() {
		return CHPharmacyBill;
	}
	public void setCHPharmacyBill(String cHPharmacyBill) {
		CHPharmacyBill = cHPharmacyBill;
	}
	
	@Column(name="CH_CONSULT_BILL")
	public String getCHConsultBill() {
		return CHConsultBill;
	}
	public void setCHConsultBill(String cHConsultBill) {
		CHConsultBill = cHConsultBill;
	}
	
	@Column(name="CH_INVEST_BILL")
	public String getCHInvestBill() {
		return CHInvestBill;
	}
	public void setCHInvestBill(String cHInvestBill) {
		CHInvestBill = cHInvestBill;
	}
	
	@Column(name="CARRY_FWD_AMT")
	public Double getCarryFwdAmt() {
		return carryFwdAmt;
	}
	public void setCarryFwdAmt(Double carryFwdAmt) {
		this.carryFwdAmt = carryFwdAmt;
	}
	@Column(name="PENDING_WITH")
	public String getPendingWith() {
		return pendingWith;
	}
	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	
	@Column(name="cochlear_yn")
	public String getCochlearYN() {
		return cochlearYN;
	}
	public void setCochlearYN(String cochlearYN) {
		this.cochlearYN = cochlearYN;
	}
	
	@Column(name="CLAIM_COC_CMT_AMT")
	public Double getClaimCocCmtAmt() {
		return claimCocCmtAmt;
	}
	public void setClaimCocCmtAmt(Double claimCocCmtAmt) {
		this.claimCocCmtAmt = claimCocCmtAmt;
	}
	
	@Column(name="CLAIM_DY_EO_AMT")
	public Double getClaimDyEoAmt() {
		return claimDyEoAmt;
	}
	public void setClaimDyEoAmt(Double claimDyEoAmt) {
		this.claimDyEoAmt = claimDyEoAmt;
	}
	
	@Column(name="COCCMT_REMARK")
	public String getCoccmtRemark() {
		return coccmtRemark;
	}
	public void setCoccmtRemark(String coccmtRemark) {
		this.coccmtRemark = coccmtRemark;
	}
	
	@Column(name="DYEO_REMARK")
	public String getDyeoRemark() {
		return dyeoRemark;
	}
	public void setDyeoRemark(String dyeoRemark) {
		this.dyeoRemark = dyeoRemark;
	}
	
	
	
	@Column(name="PATIENT_SCHEME")
	public String getPatientScheme() {
		return patientScheme;
	}


	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	
	@Column(name="sentback_usr_remrks")
	public String getSentbackUsrRemrks() {
		return sentbackUsrRemrks;
	}
	public void setSentbackUsrRemrks(String sentbackUsrRemrks) {
		this.sentbackUsrRemrks = sentbackUsrRemrks;
	}
}
