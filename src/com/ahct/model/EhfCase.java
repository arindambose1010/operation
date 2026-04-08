package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedNativeQueries ( { 
@NamedNativeQuery ( 
name = "findCaseDtls", resultClass = EhfCase.class,
query = "SELECT * FROM EHF_CASE WHERE CASE_ID = :caseId " )
})

@Entity
@Table(name = "EHF_CASE")
public class EhfCase implements Serializable {
	private String caseId;
    private String caseNo;
	private String caseHospCode;    
    private String casePatientNo;    
    private String caseStatus;
    private Date caseRegnDate;
    private Date crtDt;	
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;    
    private String claimNo;
    private Long phaseId;    
    private String patientScheme;
    private String schemeId;
    private Long surgCount;
    private Long pckAppvAmt;
    private String errClaimStatus;
    private Long csClAmount;
    private String discStatus;
    private String blockedUsr;
    private String flupFlag;
    private String enhFlag;
    private String schdFlag;
    private Long sourceId;
    private String filename;
    private String paymentCheck;
    private Date paymentSentDate;
    private Date csDisDt;
    private String csDisUpdBy;
    private Date csDisUpdDt;
    private Date csSurgDt;
    private String csSurgUpdBy;
    private Date csSurgUpdDt;
    private Date csDeathDt;
    private String csDisMainCode;
    
    private Long enhAmounts;
    private String ipNo;
    
    private Date clmSubDt;
    private Date csPreauthDt;
    private Date csAdmDt;
    private Long enhAppvAmt;
    
    private Date csEnhApvDt;
    private Date csEnhRejDt;
    private Date csEnhReqDt;
    private String preauthTotPckgAmt;
    private String viewFlag;
    private Date case_blocked_dt;
    private String caseBlockedUsr;
   
    //private EhfChronicCaseClaim ehfChronicCaseClaim;
    private Long enhReqAmt;
    private String enhCaseStatus;
    private Date preauthFwdDate;
    
    private Long comorBidAmt;
    private String comorbidVals;
    private Long comorbidAppvAmt;
    private String preauthPckgAmt;
    private Date chronicEndDt;
    //added for payment transaction
    private String csTransId;
	private Date csTransDt;
	private Date csSbhDt;
	private String csRemarks;
	private String ceoApprovalFlag;
	private String pendingFlag;
	
	private Date preauthAprvDate;
	private Date preauthRejDate;
	private String ppdGrpFlg;
	private Long preauthTotalPackageAmt;
	private String procedureConsent;
	private String medCardioClearence;
	private String bloodTransfusion;
	private Date preauthCancelDt;
	private String grievanceFlag;
	private Long clinicalNotesMissingDays;
	private Long penaltyAmount;
	private String cochlearYN;
	private String organTransYN;
	private String cochlearQues;
	private String flagged;
	private String nabhFlg;
	private String pendingWith;
	private String newBornBaby;
	
	private String opInvestAmt;
	private String opConsultAmt;
	private String pharmaFlag;
	private String skipFlag;
	private String nabhHosp;
	private String exceedFlg;
	private String paneldocuserid;
	 private String labTokenNo;
	 private String dmeFlag;
	 private Date actualClmSubDt;
	 private String secFlag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="preauth_cancel_dt")
	public Date getPreauthCancelDt() {
		return preauthCancelDt;
	}

	public void setPreauthCancelDt(Date preauthCancelDt) {
		this.preauthCancelDt = preauthCancelDt;
	}

	@Column(name="procedure_consent")
	public String getProcedureConsent() {
		return procedureConsent;
	}

	public void setProcedureConsent(String procedureConsent) {
		this.procedureConsent = procedureConsent;
	}
	@Column(name="SEC_FLAG")
	public String getSecFlag() {
		return secFlag;
	}

	public void setSecFlag(String secFlag) {
		this.secFlag = secFlag;
	}
	@Column(name="medCardi_clearance")
	public String getMedCardioClearence() {
		return medCardioClearence;
	}

	public void setMedCardioClearence(String medCardioClearence) {
		this.medCardioClearence = medCardioClearence;
	}
	@Column(name="blood_transfusion")
	public String getBloodTransfusion() {
		return bloodTransfusion;
	}

	public void setBloodTransfusion(String bloodTransfusion) {
		this.bloodTransfusion = bloodTransfusion;
	}

	@Column(name="preauth_total_package_amt")
	public Long getPreauthTotalPackageAmt() {
		return preauthTotalPackageAmt;
	}

	public void setPreauthTotalPackageAmt(Long preauthTotalPackageAmt) {
		this.preauthTotalPackageAmt = preauthTotalPackageAmt;
	}

	@Column(name="ppd_grp_flg")
	public String getPpdGrpFlg() {
		return ppdGrpFlg;
	}

	public void setPpdGrpFlg(String ppdGrpFlg) {
		this.ppdGrpFlg = ppdGrpFlg;
	}

	@Column(name="pending_flag")
	public String getPendingFlag() {
		return pendingFlag;
	}

	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
	}

	@Column(name="ceo_approval_Flag")
    public String getCeoApprovalFlag() {
		return ceoApprovalFlag;
	}

	public void setCeoApprovalFlag(String ceoApprovalFlag) {
		this.ceoApprovalFlag = ceoApprovalFlag;
	}

	@Column(name="preauth_pckg_amt")
    public String getPreauthPckgAmt() {
		return preauthPckgAmt;
	}

	public void setPreauthPckgAmt(String preauthPckgAmt) {
		this.preauthPckgAmt = preauthPckgAmt;
	}

	@Column(name="comorbid_appv_amt")
    public Long getComorbidAppvAmt() {
		return comorbidAppvAmt;
	}

	public void setComorbidAppvAmt(Long comorbidAppvAmt) {
		this.comorbidAppvAmt = comorbidAppvAmt;
	}

	@Column(name="comorbid_amt")  
    public Long getComorBidAmt() {
		return comorBidAmt;
	}

	public void setComorBidAmt(Long comorBidAmt) {
		this.comorBidAmt = comorBidAmt;
	}
	@Column(name="comorbid_vals")
	public String getComorbidVals() {
		return comorbidVals;
	}

	public void setComorbidVals(String comorbidVals) {
		this.comorbidVals = comorbidVals;
	}

	@Column(name="preauth_fwd_dt")
    public Date getPreauthFwdDate() {
		return preauthFwdDate;
	}

	public void setPreauthFwdDate(Date preauthFwdDate) {
		this.preauthFwdDate = preauthFwdDate;
	}

	@Column(name="enh_req_amt")
    public Long getEnhReqAmt() {
		return enhReqAmt;
	}

	public void setEnhReqAmt(Long enhReqAmt) {
		this.enhReqAmt = enhReqAmt;
	}
	@Column(name="enh_case_status")
	public String getEnhCaseStatus() {
		return enhCaseStatus;
	}

	public void setEnhCaseStatus(String enhCaseStatus) {
		this.enhCaseStatus = enhCaseStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="case_blocked_dt")
    public Date getCase_blocked_dt() {
		return case_blocked_dt;
	}

	public void setCase_blocked_dt(Date case_blocked_dt) {
		this.case_blocked_dt = case_blocked_dt;
	}

	@Column(name="view_flag")
    public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	@Column(name="tot_pckg_amt")
	public String getPreauthTotPckgAmt() {
		return preauthTotPckgAmt;
	}

	public void setPreauthTotPckgAmt(String preauthTotPckgAmt) {
		this.preauthTotPckgAmt = preauthTotPckgAmt;
	}

	/*  private Date billSubDt;
    private Date blockedDt;    
    private Long phaseRenewal;
    private String claimSeqNo;
    private Date clmSubDt;
    private String cmcoRenewal;
    private String cmletterflag;
    private String cmochecked;    
    private Date csApprvRejDt;
    private String csBheadName;
    private Date csBillDt;
    private String csBillTime;
    private Long csClmBillAmt;
    private Long csClAmount;
    private Date csCnclApvDt;
    private Date csCnclReqDt;   
    private String csDiagnosis;    
    private String csDisMainCode;       
    private Date csDtPreAuth;
    private Date csEnhApvDt;
    private Date csEnhRejDt;
    private Date csEnhReqDt;
    private Date csPreauthDt;
    private Date csPreAtchDt;
    private String csRemarks;
    private Date csSbhDt;
    private Date csTransDt;
    private String csTransId;
    private String deductorType;
    private Date discActTime;
    private String discActUsr;    
    private String docRegNo;    
    private String errClmStatus;
    private String feedbackId;
    private String fileName;    
    private String insDocId;
    private String isTpPrinted;
    private String journalistRenewal;
    private String langId;    
    private String oldClaimNo;
    private String paymentCheck;
    private String paymentScheduleStatus;
    private Date paymentSentDate;        
    private Date scheduleDt;    
    private String servTaxYn;    
    private Long tpPrintedCount;
    private String treatingDoc;*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="cs_enh_apvDt")
    public Date getCsEnhApvDt() {
		return csEnhApvDt;
	}

	public void setCsEnhApvDt(Date csEnhApvDt) {
		this.csEnhApvDt = csEnhApvDt;
	}
	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name="cs_enh_rejDt")
	public Date getCsEnhRejDt() {
		return csEnhRejDt;
	}

	public void setCsEnhRejDt(Date csEnhRejDt) {
		this.csEnhRejDt = csEnhRejDt;
	}
	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name="cs_enh_reqDt")
	public Date getCsEnhReqDt() {
		return csEnhReqDt;
	}

	public void setCsEnhReqDt(Date csEnhReqDt) {
		this.csEnhReqDt = csEnhReqDt;
	}

	public EhfCase() {
    }
   
    @Column(name="IP_NO")
    public String getIpNo() {
		return ipNo;
	}


	public void setIpNo(String ipNo) {
		this.ipNo = ipNo;
	}


	@Column(name="CASE_HOSP_CODE")
    public String getCaseHospCode() {
        return caseHospCode;
    }

    public void setCaseHospCode(String caseHospCode) {
        this.caseHospCode = caseHospCode;
    }

    @Id
    @Column(name="CASE_ID", nullable = false)
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @Column(name="CASE_NO", nullable = false)
    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    @Column(name="CASE_PATIENT_NO")
    public String getCasePatientNo() {
        return casePatientNo;
    }

    public void setCasePatientNo(String casePatientNo) {
        this.casePatientNo = casePatientNo;
    }

    @Column(name="CASE_REGN_DATE")
    public Date getCaseRegnDate() {
        return caseRegnDate;
    }

    public void setCaseRegnDate(Date caseRegnDate) {
        this.caseRegnDate = caseRegnDate;
    }

    @Column(name="CASE_STATUS")
    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    @Column(name="CLAIM_NO")
    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR", nullable = false)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CS_DIS_DT")
    public Date getCsDisDt() {
        return csDisDt;
    }

    public void setCsDisDt(Date csDisDt) {
        this.csDisDt = csDisDt;
    }

    @Column(name="CS_DIS_UPD_BY")
    public String getCsDisUpdBy() {
        return csDisUpdBy;
    }

    public void setCsDisUpdBy(String csDisUpdBy) {
        this.csDisUpdBy = csDisUpdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CS_DIS_UPD_DT")
    public Date getCsDisUpdDt() {
        return csDisUpdDt;
    }

    public void setCsDisUpdDt(Date csDisUpdDt) {
        this.csDisUpdDt = csDisUpdDt;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CS_SURG_DT")
    public Date getCsSurgDt() {
        return csSurgDt;
    }

    public void setCsSurgDt(Date csSurgDt) {
        this.csSurgDt = csSurgDt;
    }

    @Column(name="CS_SURG_UPD_BY")
    public String getCsSurgUpdBy() {
        return csSurgUpdBy;
    }

    public void setCsSurgUpdBy(String csSurgUpdBy) {
        this.csSurgUpdBy = csSurgUpdBy;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CS_SURG_UPD_DT")
    public Date getCsSurgUpdDt() {
        return csSurgUpdDt;
    }

    public void setCsSurgUpdDt(Date csSurgUpdDt) {
        this.csSurgUpdDt = csSurgUpdDt;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }

    @Column(name="PCK_APPV_AMT")
    public Long getPckAppvAmt() {
        return pckAppvAmt;
    }

    public void setPckAppvAmt(Long pckAppvAmt) {
        this.pckAppvAmt = pckAppvAmt;
    }

    @Column(name="PHASE_ID", nullable = false)
    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }
    
	@Column(name="patient_scheme")
	public String getPatientScheme() {
		return patientScheme;
	}

	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}

    @Column(name="SCHEME_ID")
    public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

    @Column(name="SURG_COUNT")
    public Long getSurgCount() {
        return surgCount;
    }

	public void setSurgCount(Long surgCount) {
        this.surgCount = surgCount;
    }

    @Column(name="CS_CL_AMOUNT")
    public Long getCsClAmount() {
		return csClAmount;
	}

	public void setCsClAmount(Long csClAmount) {
		this.csClAmount = csClAmount;
	}

	@Column(name="DISC_STATUS")
	public String getDiscStatus() {
		return discStatus;
	}

	public void setDiscStatus(String discStatus) {
		this.discStatus = discStatus;
	}
	
	@Column(name="BLOCKED_USR")
	public String getBlockedUsr() {
		return blockedUsr;
	}

	public void setBlockedUsr(String blockedUsr) {
		this.blockedUsr = blockedUsr;
	}

	@Column(name="FLUP_FLAG")
	public String getFlupFlag() {
		return flupFlag;
	}

	public void setFlupFlag(String flupFlag) {
		this.flupFlag = flupFlag;
	}

	@Column(name="ENH_FLAG")
	public String getEnhFlag() {
		return enhFlag;
	}

	public void setEnhFlag(String enhFlag) {
		this.enhFlag = enhFlag;
	}

	@Column(name="SCHD_FLAG")
	public String getSchdFlag() {
		return schdFlag;
	}

	public void setSchdFlag(String schdFlag) {
		this.schdFlag = schdFlag;
	}
		
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="CS_DEATH_DT")
	public Date getCsDeathDt() {
		return csDeathDt;
	}

	public void setCsDeathDt(Date csDeathDt) {
		this.csDeathDt = csDeathDt;
	}  
	
	@Column(name="SOURCE_ID")
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	
	@Column(name="CS_DIS_MAIN_CODE")
    public String getCsDisMainCode() {
        return csDisMainCode;
    }

    public void setCsDisMainCode(String csDisMainCode) {
        this.csDisMainCode = csDisMainCode;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CLM_SUB_DT")
	public Date getClmSubDt() {
		return clmSubDt;
	}

	public void setClmSubDt(Date clmSubDt) {
		this.clmSubDt = clmSubDt;
	}
	 @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="cs_preauth_dt")
	public Date getCsPreauthDt() {
		return csPreauthDt;
	}

	public void setCsPreauthDt(Date csPreauthDt) {
		this.csPreauthDt = csPreauthDt;
	}
	 @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="cs_adm_dt")
	public Date getCsAdmDt() {
		return csAdmDt;
	}

	public void setCsAdmDt(Date csAdmDt) {
		this.csAdmDt = csAdmDt;
	}
    @Column(name="enh_appv_amt")
	public Long getEnhAppvAmt() {
		return enhAppvAmt;
	}

	public void setEnhAppvAmt(Long enhAppvAmt) {
		this.enhAppvAmt = enhAppvAmt;
	}
	@Column(name="err_clm_status")
	public String getErrClaimStatus() {
		return errClaimStatus;
	}

	public void setErrClaimStatus(String errClaimStatus) {
		this.errClaimStatus = errClaimStatus;
	}

	/*@OneToOne(fetch = FetchType.LAZY, mappedBy = "ehfCase", cascade = CascadeType.ALL)
	public EhfChronicCaseClaim getEhfChronicCaseClaim() {
		return ehfChronicCaseClaim;
	}

	public void setEhfChronicCaseClaim(EhfChronicCaseClaim ehfChronicCaseClaim) {
		this.ehfChronicCaseClaim = ehfChronicCaseClaim;
	}*/
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_sent_date")
	public Date getPaymentSentDate() {
		return paymentSentDate;
	}

	public void setPaymentSentDate(Date paymentSentDate) {
		this.paymentSentDate = paymentSentDate;
	}
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name="chronic_End_Dt")
	public Date getChronicEndDt() {
		return chronicEndDt;
	}

	public void setChronicEndDt(Date chronicEndDt) {
		this.chronicEndDt = chronicEndDt;
	}
	 @Column(name="cs_trans_id")
	public String getCsTransId() {
		return csTransId;
	}

	public void setCsTransId(String csTransId) {
		this.csTransId = csTransId;
	}
	 @Column(name="cs_trans_dt")
	public Date getCsTransDt() {
		return csTransDt;
	}

	public void setCsTransDt(Date csTransDt) {
		this.csTransDt = csTransDt;
	}
	 @Column(name="cs_sbh_dt")
	public Date getCsSbhDt() {
		return csSbhDt;
	}

	public void setCsSbhDt(Date csSbhDt) {
		this.csSbhDt = csSbhDt;
	}
	 @Column(name="cs_remarks")
	public String getCsRemarks() {
		return csRemarks;
	}

	public void setCsRemarks(String csRemarks) {
		this.csRemarks = csRemarks;
	}

	@Column(name="case_blocked_usr")
	public String getCaseBlockedUsr() {
		return caseBlockedUsr;
	}

	public void setCaseBlockedUsr(String caseBlockedUsr) {
		this.caseBlockedUsr = caseBlockedUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	 @Column(name="preauth_rej_dt")
	public Date getPreauthRejDate() {
		return preauthRejDate;
	}

	public void setPreauthRejDate(Date preauthRejDate) {
		this.preauthRejDate = preauthRejDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	 @Column(name="preauth_aprv_dt")
	public Date getPreauthAprvDate() {
		return preauthAprvDate;
	}

	public void setPreauthAprvDate(Date preauthAprvDate) {
		this.preauthAprvDate = preauthAprvDate;
	}

	@Column(name="grievance_flag")
	public String getGrievanceFlag() {
		return grievanceFlag;
	}

	public void setGrievanceFlag(String grievanceFlag) {
		this.grievanceFlag = grievanceFlag;
	}

	@Column(name="clinical_notes_missing_days")
	public Long getClinicalNotesMissingDays() {
		return clinicalNotesMissingDays;
	}

	public void setClinicalNotesMissingDays(Long clinicalNotesMissingDays) {
		this.clinicalNotesMissingDays = clinicalNotesMissingDays;
	}

	@Column(name="penalty_amount")
	public Long getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(Long penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}
	@Column(name="cochlear_YN")
	public String getCochlearYN() {
		return cochlearYN;
	}

	public void setCochlearYN(String cochlearYN) {
		this.cochlearYN = cochlearYN;
	}

	@Column(name="cochlear_ques")
	public String getCochlearQues() {
		return cochlearQues;
	}

	public void setCochlearQues(String cochlearQues) {
		this.cochlearQues = cochlearQues;
	}

	@Column(name="flagged")
	public String getFlagged() {
		return flagged;
	}

	public void setFlagged(String flagged) {
		this.flagged = flagged;
	}
	
	@Column(name="NABH_FLG")
	public String getNabhFlg() {
		return nabhFlg;
	}

	public void setNabhFlg(String nabhFlg) {
		this.nabhFlg = nabhFlg;
	}
	@Column(name="PENDING_WITH")
	public String getPendingWith() {
		return pendingWith;
	}
	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	@Column(name="NEW_BORN_BABY")
	public String getNewBornBaby() {
		return newBornBaby;
	}

	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}

	private EhfCaseClaim ehfCaseClaim;

	/*@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public EhfCaseClaim getEhfCaseClaim() {
		return ehfCaseClaim;
	}

	public void setEhfCaseClaim(EhfCaseClaim ehfCaseClaim) {
		this.ehfCaseClaim = ehfCaseClaim;
	}
	*/
	@Column(name="OP_INVEST_AMT")
	public String getOpInvestAmt() {
		return opInvestAmt;
	}

	public void setOpInvestAmt(String opInvestAmt) {
		this.opInvestAmt = opInvestAmt;
	}
    @Column(name="OP_CONSULT_AMT")
	public String getOpConsultAmt() {
		return opConsultAmt;
	}

	public void setOpConsultAmt(String opConsultAmt) {
		this.opConsultAmt = opConsultAmt;
	}


	@Column(name="pharma_flag")
	public String getPharmaFlag() {
		return pharmaFlag;
	}

	public void setPharmaFlag(String pharmaFlag) {
		this.pharmaFlag = pharmaFlag;
	}
	@Column(name="skip_flag")
	public String getSkipFlag() {
		return skipFlag;
	}

	public void setSkipFlag(String skipFlag) {
		this.skipFlag = skipFlag;
	}
	@Column(name="NABH_Hosp")
	public String getNabhHosp() {
		return nabhHosp;
	}

	public void setNabhHosp(String nabhHosp) {
		this.nabhHosp = nabhHosp;
	}
	@Column(name="exceed_flg")
	public String getExceedFlg() {
		return exceedFlg;
	}

	public void setExceedFlg(String exceedFlg) {
		this.exceedFlg = exceedFlg;
	}
	@Column(name="ORGAN_TRANS_YN")
	public String getOrganTransYN() {
		return organTransYN;
	}

	public void setOrganTransYN(String organTransYN) {
		this.organTransYN = organTransYN;
	}
	
	
	@Column(name="lab_token_no")
	public String getLabTokenNo() {
		return labTokenNo;
	}

	public void setLabTokenNo(String labTokenNo) {
		this.labTokenNo = labTokenNo;
	}
	@Column(name="ENH_AMOUNT")
	public Long getEnhAmounts() {
		return enhAmounts;
	}

	public void setEnhAmounts(Long enhAmounts) {
		this.enhAmounts = enhAmounts;
	}
   /* @Column(name="PANEL_DOC_USER_ID")
	public String getPaneldocuserid() {
		return paneldocuserid;
	}

	public void setPaneldocuserid(String paneldocuserid) {
		paneldocuserid = paneldocuserid;
	}*/
	@Column(name="DME_FLAG")
	public String getDmeFlag() {
		return dmeFlag;
	}
    public void setDmeFlag(String dmeFlag) {
		this.dmeFlag = dmeFlag;
	}
	@Column(name="PANEL_DOC_USER_ID")
	public String getPaneldocuserid() {
		return paneldocuserid;
	}

	public void setPaneldocuserid(String paneldocuserid) {
		this.paneldocuserid = paneldocuserid;
	}

	
	  @Temporal(TemporalType.TIMESTAMP)
	   @Column(name="ACTUAL_CLM_SUB_DT")
	public Date getActualClmSubDt() {
		return actualClmSubDt;
	}

	public void setActualClmSubDt(Date actualClmSubDt) {
		this.actualClmSubDt = actualClmSubDt;
	}

}
