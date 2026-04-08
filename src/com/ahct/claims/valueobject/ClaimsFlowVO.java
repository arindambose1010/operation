
package com.ahct.claims.valueobject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;



/**
 * @author Ishank Paharia
 * @class This Class is used as a ValueObject in claim. 
 * @version jdk 1.6
 * @Date  4 April 2013
 *
 */
public class ClaimsFlowVO implements Serializable {	
	
	//claim payment
	private String flagged;
	private String nabhAmnt;
	private String tdsPaymentId;
	private String paymentType;
	private String TDSACTIVEYN;
	private String ACTIVEYN;
	private Number HOSPPERCENT;
	private Number TRUSTPERCENT;
	private Number TDSHOSPPERCENT;
	private Number TDSPERCENT;
	private Number SURCHARGEPERCENT;
	private Number CESSPERCENT;
	private char HOSPTYPE;
	private String DIRECTDEDUCTION;     //direct_deduction
	private Number SLABAMOUNT;          //slab_amount
	private Number TOTALTDSAMT;         //total_tds_amt
	private String HOSPDEDUCTORID;      //hosp_deductor_id
	private String RQSTACTIVEYN;        //rqst_active_yn
	private String SLABAMTAPPLICABLE;
	private Number CLAIMAMOUNT;  
	private String REVFUNDACTIVE;
	private Number TOTALCLAIMAMT;
	private String HOSPITALID;
	private String claimInitAmt;
	private String noOfStay;
	private String penaltyAmount;
	private String schemeType;
	private Double hospPctAmt;
	private Double tdsHospPctAmt;
    private Double tdsPctAmt;
    private Double trustPctAmt;
    private String ctdFinAprAmt;
	private String ctdNabhAmt;
	private String nextStatus;
	private String currStatus;
	private String[] caseSelected;
	private String[] casesReady;
	private String ceoRemark;
	private String acoAprAmt;
	private String acoRemark;
	private String errAcoAprAmt;
	private String errAcoRemark;
	private Integer MAXSETID;
	private String nimsFlag;
	//adding for erroneous
	private String claimInitFlag;
	private String errClamFlag;
	private String errClaimStatus; 
    private String errClaimSubDt;
    private Long claimPaidAmt;
	private String errAmt;
	private String errMedcoRemark;
	private String errCtdRemark;
	private String errCtdAprAmt;
	private String errChAprAmt;
	private String errChRemark;
	private String wapNo;
	private String patName;
	private String fromDate;
	private String toDate;
	private String chronicId;
	private String chronicNo;
	private String dentalFlag;
	private Long enhAmounts;
    private String medicalSurgFlag;
    
	private String sennBackRole;
    private String heamFlag;
    private String ICDPROCCODE;
    private String techCheckOrg1;
    private String techCheckOrg2;
    private String techCheckOrg3;
    private String techCheckOrg4;
    private String totalClaimOrg;
    private String deductionOrg;
    private String stayOrg;
    private String stayRemarkOrg;
    private String inputsOrg;
    private String inputsRmrkOrg;
    private String profFeeOrg;
    private String profFeeRmrkOrg;
    private String investBillOrg;
    private String investBillRmrkOrg;
    private String claimPanelRemarkOrg;
    private String techChklstOrg;
	private String cpdAprAmtOrg;
	// CR 6444 - Chandana - 04-02-2025
	private String HUBSPOKEFLAG;
	private Number PKGAMT;
	private Number CYCLECOUNT;
    private Double HUB_AMT;
    private Double SPOKE_AMT;
    private Double agencyPctAmt;
    private String AGENCY_BANK_ID;
    private Number ACTORDER;            //ACT_ORDER
    private String ACTID;               //ACT_ID
    private Double HM_MNT;
    private Double SM_MNT;
    private String NAMEASPERACT;
    private String ID;
    private Double hospPercent;
    private String ACCTNO;
    private String BANKID;
    private String IFSCCODE;
    private String BANKNAME;
    private String BANKBRANCH;
    private String BANKCATEGORY;
    	public String getTechCheckOrg1() {
		return techCheckOrg1;
	}

	public void setTechCheckOrg1(String techCheckOrg1) {
		this.techCheckOrg1 = techCheckOrg1;
	}

	public String getTechCheckOrg2() {
		return techCheckOrg2;
	}

	public void setTechCheckOrg2(String techCheckOrg2) {
		this.techCheckOrg2 = techCheckOrg2;
	}

	public String getTechCheckOrg3() {
		return techCheckOrg3;
	}

	public void setTechCheckOrg3(String techCheckOrg3) {
		this.techCheckOrg3 = techCheckOrg3;
	}

	public String getTechCheckOrg4() {
		return techCheckOrg4;
	}

	public void setTechCheckOrg4(String techCheckOrg4) {
		this.techCheckOrg4 = techCheckOrg4;
	}

	public String getTotalClaimOrg() {
		return totalClaimOrg;
	}

	public void setTotalClaimOrg(String totalClaimOrg) {
		this.totalClaimOrg = totalClaimOrg;
	}

	public String getDeductionOrg() {
		return deductionOrg;
	}

	public void setDeductionOrg(String deductionOrg) {
		this.deductionOrg = deductionOrg;
	}

	public String getStayOrg() {
		return stayOrg;
	}

	public void setStayOrg(String stayOrg) {
		this.stayOrg = stayOrg;
	}

	public String getStayRemarkOrg() {
		return stayRemarkOrg;
	}

	public void setStayRemarkOrg(String stayRemarkOrg) {
		this.stayRemarkOrg = stayRemarkOrg;
	}

	public String getInputsOrg() {
		return inputsOrg;
	}

	public void setInputsOrg(String inputsOrg) {
		this.inputsOrg = inputsOrg;
	}

	public String getInputsRmrkOrg() {
		return inputsRmrkOrg;
	}

	public void setInputsRmrkOrg(String inputsRmrkOrg) {
		this.inputsRmrkOrg = inputsRmrkOrg;
	}

	public String getProfFeeOrg() {
		return profFeeOrg;
	}

	public void setProfFeeOrg(String profFeeOrg) {
		this.profFeeOrg = profFeeOrg;
	}

	public String getProfFeeRmrkOrg() {
		return profFeeRmrkOrg;
	}

	public void setProfFeeRmrkOrg(String profFeeRmrkOrg) {
		this.profFeeRmrkOrg = profFeeRmrkOrg;
	}

	public String getInvestBillOrg() {
		return investBillOrg;
	}

	public void setInvestBillOrg(String investBillOrg) {
		this.investBillOrg = investBillOrg;
	}

	public String getInvestBillRmrkOrg() {
		return investBillRmrkOrg;
	}

	public void setInvestBillRmrkOrg(String investBillRmrkOrg) {
		this.investBillRmrkOrg = investBillRmrkOrg;
	}

	public String getClaimPanelRemarkOrg() {
		return claimPanelRemarkOrg;
	}

	public void setClaimPanelRemarkOrg(String claimPanelRemarkOrg) {
		this.claimPanelRemarkOrg = claimPanelRemarkOrg;
	}

	public String getTechChklstOrg() {
		return techChklstOrg;
	}

	public void setTechChklstOrg(String techChklstOrg) {
		this.techChklstOrg = techChklstOrg;
	}

	public String getCpdAprAmtOrg() {
		return cpdAprAmtOrg;
	}

	public void setCpdAprAmtOrg(String cpdAprAmtOrg) {
		this.cpdAprAmtOrg = cpdAprAmtOrg;
	}

		public String getSennBackRole() {
		return sennBackRole;
	}

	public void setSennBackRole(String sennBackRole) {
		this.sennBackRole = sennBackRole;
	}

    /** The count. */
    private Number COUNT=0;
    /** The patient Id. */
    private String patientId;
    private String smsMsg;
    /** The case id. */
    private String caseId;
    private String remarks;
    /** The case stat. */
    private String caseStat;
    private String caseTherapyId;
    
    /** The role id. */
    private String roleId;
    
    /** The msg. */
    private String msg;
    private String dentCond;
    private String caseUnits;
    private String caseUnitsPar;
    
    /** The pack amt. */
    private String packAmt;
    
    /** The pre auth date. */
    private String preAuthDate;
    
    /** The pre auth Approved date. */
    private String preAuthApprvDate;
    
    /** The adm date. */
    private String admDate;
    private Date caseStartTime;
    private Date caseEndTime;
    
    /** The surg date. */
    private String surgDate;
    
    /** The dis date. */
    private String disDate;
    
    /** The days diff. */
    private Long daysDiff;
    
    /** The claim sub dt. */
    private String claimSubDt;
    
    /** The case next stat. */
    private String caseNextStat;
    
    /** The action done. */
    private String actionDone;
    
    /** The user id. */
    private String userId;
    
    /** The ben photo check. */
    private String benPhotoCheck;
	
	/** The name check. */
	private String nameCheck;
	
	/** The gender check. */
	private String genderCheck;
	
	/** The case st adm dt. */
	private String caseStAdmDt;
	
	/** The case st surg dt. */
	private String caseStSurgDt;
	
	/** The case st dischrg dt. */
	private String caseStDischrgDt;
	
	/** The adm dt check. */
	private String admDtCheck;
	
	/** The surg dt check. */
	private String surgDtCheck;
	
	/** The discharge dt check. */
	private String dischargeDtCheck;
	
	/** The doc ver1. */
	private String docVer1;
	
	/** The doc ver2. */
	private String docVer2;
	
	/** The doc ver3. */
	private String docVer3;
	
	/** The doc ver4. */
	private String docVer4;
	
	/** The doc ver5. */
	private String docVer5;
	
	/** The cex remark. */
	private String cexRemark;
	
	/** The tech check1. */
	private String techCheck1;
	
	/** The tech check2. */
	private String techCheck2;
	
	/** The tech check3. */
	private String techCheck3;
	private String techCheck4;
	
	/** The total claim. */
	private String totalClaim;
	
	/** The deduction. */
	private String deduction;
	
	/** The stay. */
	private String stay;
	
	/** The stay remark. */
	private String stayRemark;
	
	/** The inputs. */
	private String inputs;
	
	/** The inputs rmrk. */
	private String inputsRmrk;
	
	/** The prof fee. */
	private String profFee;
	
	/** The prof fee rmrk. */
	private String profFeeRmrk;
	
	/** The invest bill. */
	private String investBill;
	
	/** The invest bill rmrk. */
	private String investBillRmrk;
	
	/** The claim panel remark. */
	private String claimPanelRemark;
	
	/** The trust doc1. */
	private String trustDoc1;
	
	/** The trust doc2. */
	private String trustDoc2;
	
	/** The trust doc3. */
	private String trustDoc3;
	private String trustDoc4;
	
	/** The audit date. */
	private String auditDate;
	private Date fpauditDate;
	private Date chronicAuditDate;
	/** The audit role. */
	private String auditRole;
	
	/** The audit name. */
	private String auditName;
	
	/** The audit action. */
	private String auditAction;
	
	/** The non tech chklst. */
	private String nonTechChklst;
	
	/** The tech chklst. */
	private String techChklst;
	
	/** The trst doc chklst. */
	private String trstDocChklst;
	
	/** The ctd remark. */
	private String ctdRemark;
	
	/** The ch remark. */
	private String chRemark;
    
    /** The button list. */
    private List<LabelBean> buttonList=null;
	private String billDt;
	private String billAmt;
	private String medcoRemark;
	private String cexAprAmt;
	private String cpdAprAmt;
	private String ctdAprAmt;
	private String chAprAmt;
	private String eoAprAmt;
	private String eoRemark;
	private String eoComRemark;
	private String eoComAprAmt; 
	private String showEO;
	private String showEOCom;
	
	private String hospAdd;
	private String bankId;
	private String bankBranch;
	private String bankName;
	private String ifscCode;
	private String bankCategory;
	private String accNo;
	private String hospAccName;
	private String hospId;
	private String hospActiveYN;
	
	private String chNabhAmt;
	private String chEntAprAmt;
	private String nabhFlag;
	private String eoComNabhAmt;
	private String eoComEntAprAmt;
	private String phaseId;
	private String photoMatch;
	private String photoMatchRemarks;
	private String photoAttached;
	private String photoRemarks;
	private String acquaintanceAttached;
	private String acquaintanceRemarks;
	private String reportsAttached;
	private String reportsRemarks;
	private String exeRemarksVerified;
	private String exeVerifyRemarks;
	private String finalApproveAmt;
	private String drugAmount;
	private String sendBackFlag;
	private String moduleType;
	private String newBornBaby;
	private String acoFlag;
	private String sendBackRmrks;
	
	private String COCHLEARYN;
	private String procName;
	private String hospStayAmt;
	private String commonCatAmt;
	private String icdAmt;
	private String scheme;
	private String test;
	private String testKnown;
	private String ipOpFlag;
	private String opProcUnits;
	private String process;
	private Integer noOfDays;
	private String totPackgAmt;
	private String ctdApprvdUnits;
	private String chApprvdUnits;
	private List<LabelBean> investDetails = null;
	private List<LabelBean> drugDetails = null;
	private Number totalConsumableAmount;
	private Number totalDrugAmt;
	
	
	private String email;
	
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCtdApprvdUnits() {
		return ctdApprvdUnits;
	}

	public void setCtdApprvdUnits(String ctdApprvdUnits) {
		this.ctdApprvdUnits = ctdApprvdUnits;
	}

	public String getChApprvdUnits() {
		return chApprvdUnits;
	}

	public void setChApprvdUnits(String chApprvdUnits) {
		this.chApprvdUnits = chApprvdUnits;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getHospStayAmt() {
		return hospStayAmt;
	}

	public void setHospStayAmt(String hospStayAmt) {
		this.hospStayAmt = hospStayAmt;
	}

	public String getCommonCatAmt() {
		return commonCatAmt;
	}

	public void setCommonCatAmt(String commonCatAmt) {
		this.commonCatAmt = commonCatAmt;
	}

	public String getIcdAmt() {
		return icdAmt;
	}

	public void setIcdAmt(String icdAmt) {
		this.icdAmt = icdAmt;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getTestKnown() {
		return testKnown;
	}

	public void setTestKnown(String testKnown) {
		this.testKnown = testKnown;
	}

	public String getIpOpFlag() {
		return ipOpFlag;
	}

	public void setIpOpFlag(String ipOpFlag) {
		this.ipOpFlag = ipOpFlag;
	}

	public String getOpProcUnits() {
		return opProcUnits;
	}

	public void setOpProcUnits(String opProcUnits) {
		this.opProcUnits = opProcUnits;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	
    public String getPhotoMatch() {
		return photoMatch;
	}

	public void setPhotoMatch(String photoMatch) {
		this.photoMatch = photoMatch;
	}

	public String getPhotoMatchRemarks() {
		return photoMatchRemarks;
	}

	public void setPhotoMatchRemarks(String photoMatchRemarks) {
		this.photoMatchRemarks = photoMatchRemarks;
	}

	public String getPhotoAttached() {
		return photoAttached;
	}

	public void setPhotoAttached(String photoAttached) {
		this.photoAttached = photoAttached;
	}

	public String getPhotoRemarks() {
		return photoRemarks;
	}

	public void setPhotoRemarks(String photoRemarks) {
		this.photoRemarks = photoRemarks;
	}

	public String getAcquaintanceAttached() {
		return acquaintanceAttached;
	}

	public void setAcquaintanceAttached(String acquaintanceAttached) {
		this.acquaintanceAttached = acquaintanceAttached;
	}

	public String getAcquaintanceRemarks() {
		return acquaintanceRemarks;
	}

	public void setAcquaintanceRemarks(String acquaintanceRemarks) {
		this.acquaintanceRemarks = acquaintanceRemarks;
	}

	public String getReportsAttached() {
		return reportsAttached;
	}

	public void setReportsAttached(String reportsAttached) {
		this.reportsAttached = reportsAttached;
	}

	public String getReportsRemarks() {
		return reportsRemarks;
	}

	public void setReportsRemarks(String reportsRemarks) {
		this.reportsRemarks = reportsRemarks;
	}

	public String getExeRemarksVerified() {
		return exeRemarksVerified;
	}

	public void setExeRemarksVerified(String exeRemarksVerified) {
		this.exeRemarksVerified = exeRemarksVerified;
	}

	public String getExeVerifyRemarks() {
		return exeVerifyRemarks;
	}

	public void setExeVerifyRemarks(String exeVerifyRemarks) {
		this.exeVerifyRemarks = exeVerifyRemarks;
	}

	public String getFinalApproveAmt() {
		return finalApproveAmt;
	}

	public void setFinalApproveAmt(String finalApproveAmt) {
		this.finalApproveAmt = finalApproveAmt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}



	public String getCexAprAmt() {
		return cexAprAmt;
	}

	public void setCexAprAmt(String cexAprAmt) {
		this.cexAprAmt = cexAprAmt;
	}

	public String getCpdAprAmt() {
		return cpdAprAmt;
	}

	public void setCpdAprAmt(String cpdAprAmt) {
		this.cpdAprAmt = cpdAprAmt;
	}

	public String getCtdAprAmt() {
		return ctdAprAmt;
	}

	public void setCtdAprAmt(String ctdAprAmt) {
		this.ctdAprAmt = ctdAprAmt;
	}

	public String getChAprAmt() {
		return chAprAmt;
	}

	public void setChAprAmt(String chAprAmt) {
		this.chAprAmt = chAprAmt;
	}

	public String getBillDt() {
		return billDt;
	}

	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}

	public String getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}

	public String getMedcoRemark() {
		return medcoRemark;
	}

	public void setMedcoRemark(String medcoRemark) {
		this.medcoRemark = medcoRemark;
	}

	/**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the action done.
	 *
	 * @return the action done
	 */
	public String getActionDone() {
		return actionDone;
	}
	
	/**
	 * Sets the action done.
	 *
	 * @param actionDone the new action done
	 */
	public void setActionDone(String actionDone) {
		this.actionDone = actionDone;
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Number getCOUNT() {
		return COUNT;
	}
	
	/**
	 * Sets the count.
	 *
	 * @param cOUNT the new count
	 */
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}
	
	/**
	 * Gets the case next stat.
	 *
	 * @return the case next stat
	 */
	public String getCaseNextStat() {
		return caseNextStat;
	}
	
	/**
	 * Sets the case next stat.
	 *
	 * @param caseNextStat the new case next stat
	 */
	public void setCaseNextStat(String caseNextStat) {
		this.caseNextStat = caseNextStat;
	}
	
	/**
	 * Gets the days diff.
	 *
	 * @return the days diff
	 */
	public Long getDaysDiff() {
		return daysDiff;
	}
	
	/**
	 * Sets the days diff.
	 *
	 * @param daysDiff the new days diff
	 */
	public void setDaysDiff(Long daysDiff) {
		this.daysDiff = daysDiff;
	}
	
	/**
	 * Gets the case id.
	 *
	 * @return the case id
	 */
	public String getCaseId() {
		return caseId;
	}
	
	/**
	 * Sets the case id.
	 *
	 * @param caseId the new case id
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	/**
	 * Gets the case stat.
	 *
	 * @return the case stat
	 */
	public String getCaseStat() {
		return caseStat;
	}
	
	/**
	 * Sets the case stat.
	 *
	 * @param caseStat the new case stat
	 */
	public void setCaseStat(String caseStat) {
		this.caseStat = caseStat;
	}
	
	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public String getRoleId() {
		return roleId;
	}
	
	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Sets the msg.
	 *
	 * @param msg the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Gets the pack amt.
	 *
	 * @return the pack amt
	 */
	public String getPackAmt() {
		return packAmt;
	}
	
	/**
	 * Sets the pack amt.
	 *
	 * @param packAmt the new pack amt
	 */
	public void setPackAmt(String packAmt) {
		this.packAmt = packAmt;
	}
	
	/**
	 * Gets the pre auth date.
	 *
	 * @return the pre auth date
	 */
	public String getPreAuthDate() {
		return preAuthDate;
	}
	
	/**
	 * Sets the pre auth date.
	 *
	 * @param preAuthDate the new pre auth date
	 */
	public void setPreAuthDate(String preAuthDate) {
		this.preAuthDate = preAuthDate;
	}
	
	/**
	 * Gets the adm date.
	 *
	 * @return the adm date
	 */
	public String getAdmDate() {
		return admDate;
	}
	
	/**
	 * Sets the adm date.
	 *
	 * @param admDate the new adm date
	 */
	public void setAdmDate(String admDate) {
		this.admDate = admDate;
	}
	
	/**
	 * Gets the surg date.
	 *
	 * @return the surg date
	 */
	public String getSurgDate() {
		return surgDate;
	}
	
	/**
	 * Sets the surg date.
	 *
	 * @param surgDate the new surg date
	 */
	public void setSurgDate(String surgDate) {
		this.surgDate = surgDate;
	}
	
	/**
	 * Gets the dis date.
	 *
	 * @return the dis date
	 */
	public String getDisDate() {
		return disDate;
	}
	
	/**
	 * Sets the dis date.
	 *
	 * @param disDate the new dis date
	 */
	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}
	
	/**
	 * Gets the claim sub dt.
	 *
	 * @return the claim sub dt
	 */
	public String getClaimSubDt() {
		return claimSubDt;
	}
	
	/**
	 * Sets the claim sub dt.
	 *
	 * @param claimSubDt the new claim sub dt
	 */
	public void setClaimSubDt(String claimSubDt) {
		this.claimSubDt = claimSubDt;
	}
	
	/**
	 * Gets the button list.
	 *
	 * @return the button list
	 */
	public List<LabelBean> getButtonList() {
		return buttonList;
	}
	
	/**
	 * Sets the button list.
	 *
	 * @param buttonList the new button list
	 */
	public void setButtonList(List<LabelBean> buttonList) {
		this.buttonList = buttonList;
	}
	
	/**
	 * Gets the ben photo check.
	 *
	 * @return the ben photo check
	 */
	public String getBenPhotoCheck() {
		return benPhotoCheck;
	}
	
	/**
	 * Sets the ben photo check.
	 *
	 * @param benPhotoCheck the new ben photo check
	 */
	public void setBenPhotoCheck(String benPhotoCheck) {
		this.benPhotoCheck = benPhotoCheck;
	}
	
	/**
	 * Gets the name check.
	 *
	 * @return the name check
	 */
	public String getNameCheck() {
		return nameCheck;
	}
	
	/**
	 * Sets the name check.
	 *
	 * @param nameCheck the new name check
	 */
	public void setNameCheck(String nameCheck) {
		this.nameCheck = nameCheck;
	}
	
	/**
	 * Gets the gender check.
	 *
	 * @return the gender check
	 */
	public String getGenderCheck() {
		return genderCheck;
	}
	
	/**
	 * Sets the gender check.
	 *
	 * @param genderCheck the new gender check
	 */
	public void setGenderCheck(String genderCheck) {
		this.genderCheck = genderCheck;
	}
	
	/**
	 * Gets the case st adm dt.
	 *
	 * @return the case st adm dt
	 */
	public String getCaseStAdmDt() {
		return caseStAdmDt;
	}
	
	/**
	 * Sets the case st adm dt.
	 *
	 * @param caseStAdmDt the new case st adm dt
	 */
	public void setCaseStAdmDt(String caseStAdmDt) {
		this.caseStAdmDt = caseStAdmDt;
	}
	
	/**
	 * Gets the case st surg dt.
	 *
	 * @return the case st surg dt
	 */
	public String getCaseStSurgDt() {
		return caseStSurgDt;
	}
	
	/**
	 * Sets the case st surg dt.
	 *
	 * @param caseStSurgDt the new case st surg dt
	 */
	public void setCaseStSurgDt(String caseStSurgDt) {
		this.caseStSurgDt = caseStSurgDt;
	}
	
	/**
	 * Gets the case st dischrg dt.
	 *
	 * @return the case st dischrg dt
	 */
	public String getCaseStDischrgDt() {
		return caseStDischrgDt;
	}
	
	/**
	 * Sets the case st dischrg dt.
	 *
	 * @param caseStDischrgDt the new case st dischrg dt
	 */
	public void setCaseStDischrgDt(String caseStDischrgDt) {
		this.caseStDischrgDt = caseStDischrgDt;
	}
	
	/**
	 * Gets the adm dt check.
	 *
	 * @return the adm dt check
	 */
	public String getAdmDtCheck() {
		return admDtCheck;
	}
	
	/**
	 * Sets the adm dt check.
	 *
	 * @param admDtCheck the new adm dt check
	 */
	public void setAdmDtCheck(String admDtCheck) {
		this.admDtCheck = admDtCheck;
	}
	
	/**
	 * Gets the surg dt check.
	 *
	 * @return the surg dt check
	 */
	public String getSurgDtCheck() {
		return surgDtCheck;
	}
	
	/**
	 * Sets the surg dt check.
	 *
	 * @param surgDtCheck the new surg dt check
	 */
	public void setSurgDtCheck(String surgDtCheck) {
		this.surgDtCheck = surgDtCheck;
	}
	
	/**
	 * Gets the discharge dt check.
	 *
	 * @return the discharge dt check
	 */
	public String getDischargeDtCheck() {
		return dischargeDtCheck;
	}
	
	/**
	 * Sets the discharge dt check.
	 *
	 * @param dischargeDtCheck the new discharge dt check
	 */
	public void setDischargeDtCheck(String dischargeDtCheck) {
		this.dischargeDtCheck = dischargeDtCheck;
	}
	
	/**
	 * Gets the doc ver1.
	 *
	 * @return the doc ver1
	 */
	public String getDocVer1() {
		return docVer1;
	}
	
	/**
	 * Sets the doc ver1.
	 *
	 * @param docVer1 the new doc ver1
	 */
	public void setDocVer1(String docVer1) {
		this.docVer1 = docVer1;
	}
	
	/**
	 * Gets the doc ver2.
	 *
	 * @return the doc ver2
	 */
	public String getDocVer2() {
		return docVer2;
	}
	
	/**
	 * Sets the doc ver2.
	 *
	 * @param docVer2 the new doc ver2
	 */
	public void setDocVer2(String docVer2) {
		this.docVer2 = docVer2;
	}
	
	/**
	 * Gets the doc ver3.
	 *
	 * @return the doc ver3
	 */
	public String getDocVer3() {
		return docVer3;
	}
	
	/**
	 * Sets the doc ver3.
	 *
	 * @param docVer3 the new doc ver3
	 */
	public void setDocVer3(String docVer3) {
		this.docVer3 = docVer3;
	}
	
	/**
	 * Gets the doc ver4.
	 *
	 * @return the doc ver4
	 */
	public String getDocVer4() {
		return docVer4;
	}
	
	/**
	 * Sets the doc ver4.
	 *
	 * @param docVer4 the new doc ver4
	 */
	public void setDocVer4(String docVer4) {
		this.docVer4 = docVer4;
	}
	
	/**
	 * Gets the doc ver5.
	 *
	 * @return the doc ver5
	 */
	public String getDocVer5() {
		return docVer5;
	}
	
	/**
	 * Sets the doc ver5.
	 *
	 * @param docVer5 the new doc ver5
	 */
	public void setDocVer5(String docVer5) {
		this.docVer5 = docVer5;
	}
	
	/**
	 * Gets the cex remark.
	 *
	 * @return the cex remark
	 */
	public String getCexRemark() {
		return cexRemark;
	}
	
	/**
	 * Sets the cex remark.
	 *
	 * @param cexRemark the new cex remark
	 */
	public void setCexRemark(String cexRemark) {
		this.cexRemark = cexRemark;
	}
	
	/**
	 * Gets the tech check1.
	 *
	 * @return the tech check1
	 */
	public String getTechCheck1() {
		return techCheck1;
	}
	
	/**
	 * Sets the tech check1.
	 *
	 * @param techCheck1 the new tech check1
	 */
	public void setTechCheck1(String techCheck1) {
		this.techCheck1 = techCheck1;
	}
	
	/**
	 * Gets the tech check2.
	 *
	 * @return the tech check2
	 */
	public String getTechCheck2() {
		return techCheck2;
	}
	
	/**
	 * Sets the tech check2.
	 *
	 * @param techCheck2 the new tech check2
	 */
	public void setTechCheck2(String techCheck2) {
		this.techCheck2 = techCheck2;
	}
	
	/**
	 * Gets the tech check3.
	 *
	 * @return the tech check3
	 */
	public String getTechCheck3() {
		return techCheck3;
	}
	
	/**
	 * Sets the tech check3.
	 *
	 * @param techCheck3 the new tech check3
	 */
	public void setTechCheck3(String techCheck3) {
		this.techCheck3 = techCheck3;
	}
	
	/**
	 * Gets the total claim.
	 *
	 * @return the total claim
	 */
	public String getTotalClaim() {
		return totalClaim;
	}
	
	/**
	 * Sets the total claim.
	 *
	 * @param totalClaim the new total claim
	 */
	public void setTotalClaim(String totalClaim) {
		this.totalClaim = totalClaim;
	}
	
	/**
	 * Gets the deduction.
	 *
	 * @return the deduction
	 */
	public String getDeduction() {
		return deduction;
	}
	
	/**
	 * Sets the deduction.
	 *
	 * @param deduction the new deduction
	 */
	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}
	
	/**
	 * Gets the stay.
	 *
	 * @return the stay
	 */
	public String getStay() {
		return stay;
	}
	
	/**
	 * Sets the stay.
	 *
	 * @param stay the new stay
	 */
	public void setStay(String stay) {
		this.stay = stay;
	}
	
	/**
	 * Gets the stay remark.
	 *
	 * @return the stay remark
	 */
	public String getStayRemark() {
		return stayRemark;
	}
	
	/**
	 * Sets the stay remark.
	 *
	 * @param stayRemark the new stay remark
	 */
	public void setStayRemark(String stayRemark) {
		this.stayRemark = stayRemark;
	}
	
	/**
	 * Gets the inputs.
	 *
	 * @return the inputs
	 */
	public String getInputs() {
		return inputs;
	}
	
	/**
	 * Sets the inputs.
	 *
	 * @param inputs the new inputs
	 */
	public void setInputs(String inputs) {
		this.inputs = inputs;
	}
	
	/**
	 * Gets the inputs rmrk.
	 *
	 * @return the inputs rmrk
	 */
	public String getInputsRmrk() {
		return inputsRmrk;
	}
	
	/**
	 * Sets the inputs rmrk.
	 *
	 * @param inputsRmrk the new inputs rmrk
	 */
	public void setInputsRmrk(String inputsRmrk) {
		this.inputsRmrk = inputsRmrk;
	}
	
	/**
	 * Gets the prof fee.
	 *
	 * @return the prof fee
	 */
	public String getProfFee() {
		return profFee;
	}
	
	/**
	 * Sets the prof fee.
	 *
	 * @param profFee the new prof fee
	 */
	public void setProfFee(String profFee) {
		this.profFee = profFee;
	}
	
	/**
	 * Gets the prof fee rmrk.
	 *
	 * @return the prof fee rmrk
	 */
	public String getProfFeeRmrk() {
		return profFeeRmrk;
	}
	
	/**
	 * Sets the prof fee rmrk.
	 *
	 * @param profFeeRmrk the new prof fee rmrk
	 */
	public void setProfFeeRmrk(String profFeeRmrk) {
		this.profFeeRmrk = profFeeRmrk;
	}
	
	/**
	 * Gets the invest bill.
	 *
	 * @return the invest bill
	 */
	public String getInvestBill() {
		return investBill;
	}
	
	/**
	 * Sets the invest bill.
	 *
	 * @param investBill the new invest bill
	 */
	public void setInvestBill(String investBill) {
		this.investBill = investBill;
	}
	
	/**
	 * Gets the invest bill rmrk.
	 *
	 * @return the invest bill rmrk
	 */
	public String getInvestBillRmrk() {
		return investBillRmrk;
	}
	
	/**
	 * Sets the invest bill rmrk.
	 *
	 * @param investBillRmrk the new invest bill rmrk
	 */
	public void setInvestBillRmrk(String investBillRmrk) {
		this.investBillRmrk = investBillRmrk;
	}
	
	/**
	 * Gets the claim panel remark.
	 *
	 * @return the claim panel remark
	 */
	public String getClaimPanelRemark() {
		return claimPanelRemark;
	}
	
	/**
	 * Sets the claim panel remark.
	 *
	 * @param claimPanelRemark the new claim panel remark
	 */
	public void setClaimPanelRemark(String claimPanelRemark) {
		this.claimPanelRemark = claimPanelRemark;
	}
	
	/**
	 * Gets the trust doc1.
	 *
	 * @return the trust doc1
	 */
	public String getTrustDoc1() {
		return trustDoc1;
	}
	
	/**
	 * Sets the trust doc1.
	 *
	 * @param trustDoc1 the new trust doc1
	 */
	public void setTrustDoc1(String trustDoc1) {
		this.trustDoc1 = trustDoc1;
	}
	
	/**
	 * Gets the trust doc2.
	 *
	 * @return the trust doc2
	 */
	public String getTrustDoc2() {
		return trustDoc2;
	}
	
	/**
	 * Sets the trust doc2.
	 *
	 * @param trustDoc2 the new trust doc2
	 */
	public void setTrustDoc2(String trustDoc2) {
		this.trustDoc2 = trustDoc2;
	}
	
	/**
	 * Gets the trust doc3.
	 *
	 * @return the trust doc3
	 */
	public String getTrustDoc3() {
		return trustDoc3;
	}
	
	/**
	 * Sets the trust doc3.
	 *
	 * @param trustDoc3 the new trust doc3
	 */
	public void setTrustDoc3(String trustDoc3) {
		this.trustDoc3 = trustDoc3;
	}
	
	/**
	 * Gets the audit date.
	 *
	 * @return the audit date
	 */
	public String getAuditDate() {
		return auditDate;
	}
	
	/**
	 * Sets the audit date.
	 *
	 * @param auditDate the new audit date
	 */
	public void setAuditDate1(String auditDate) {
		//SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
		//this.auditDate = df1.format(auditDate);
		this.auditDate = auditDate;
	}
	
	/**
	 * Gets the audit role.
	 *
	 * @return the audit role
	 */
	public String getAuditRole() {
		return auditRole;
	}
	
	/**
	 * Sets the audit role.
	 *
	 * @param auditRole the new audit role
	 */
	public void setAuditRole(String auditRole) {
		this.auditRole = auditRole;
	}
	
	/**
	 * Gets the audit name.
	 *
	 * @return the audit name
	 */
	public String getAuditName() {
		return auditName;
	}
	
	/**
	 * Sets the audit name.
	 *
	 * @param auditName the new audit name
	 */
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	
	/**
	 * Gets the audit action.
	 *
	 * @return the audit action
	 */
	public String getAuditAction() {
		return auditAction;
	}
	
	/**
	 * Sets the audit action.
	 *
	 * @param auditAction the new audit action
	 */
	public void setAuditAction(String auditAction) {
		this.auditAction = auditAction;
	}
	
	/**
	 * Gets the non tech chklst.
	 *
	 * @return the non tech chklst
	 */
	public String getNonTechChklst() {
		return nonTechChklst;
	}
	
	/**
	 * Sets the non tech chklst.
	 *
	 * @param nonTechChklst the new non tech chklst
	 */
	public void setNonTechChklst(String nonTechChklst) {
		this.nonTechChklst = nonTechChklst;
	}
	
	/**
	 * Gets the tech chklst.
	 *
	 * @return the tech chklst
	 */
	public String getTechChklst() {
		return techChklst;
	}
	
	/**
	 * Sets the tech chklst.
	 *
	 * @param techChklst the new tech chklst
	 */
	public void setTechChklst(String techChklst) {
		this.techChklst = techChklst;
	}
	
	/**
	 * Gets the trst doc chklst.
	 *
	 * @return the trst doc chklst
	 */
	public String getTrstDocChklst() {
		return trstDocChklst;
	}
	
	/**
	 * Sets the trst doc chklst.
	 *
	 * @param trstDocChklst the new trst doc chklst
	 */
	public void setTrstDocChklst(String trstDocChklst) {
		this.trstDocChklst = trstDocChklst;
	}
	
	/**
	 * Gets the ctd remark.
	 *
	 * @return the ctd remark
	 */
	public String getCtdRemark() {
		return ctdRemark;
	}
	
	/**
	 * Sets the ctd remark.
	 *
	 * @param ctdRemark the new ctd remark
	 */
	public void setCtdRemark(String ctdRemark) {
		this.ctdRemark = ctdRemark;
	}
	
	/**
	 * Gets the ch remark.
	 *
	 * @return the ch remark
	 */
	public String getChRemark() {
		return chRemark;
	}
	
	/**
	 * Sets the ch remark.
	 *
	 * @param chRemark the new ch remark
	 */
	public void setChRemark(String chRemark) {
		this.chRemark = chRemark;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}

	public Long getClaimPaidAmt() {
		return claimPaidAmt;
	}

	public void setClaimPaidAmt(Long long1) {
		this.claimPaidAmt = long1;
	}

	public String getErrClaimSubDt() {
		return errClaimSubDt;
	}

	public void setErrClaimSubDt(String errClaimSubDt) {
		this.errClaimSubDt = errClaimSubDt;
	}

	public String getErrAmt() {
		return errAmt;
	}

	public void setErrAmt(String errAmt) {
		this.errAmt = errAmt;
	}

	public String getErrMedcoRemark() {
		return errMedcoRemark;
	}

	public void setErrMedcoRemark(String errMedcoRemark) {
		this.errMedcoRemark = errMedcoRemark;
	}

	public String getErrCtdRemark() {
		return errCtdRemark;
	}

	public void setErrCtdRemark(String errCtdRemark) {
		this.errCtdRemark = errCtdRemark;
	}

	public String getErrCtdAprAmt() {
		return errCtdAprAmt;
	}

	public void setErrCtdAprAmt(String errCtdAprAmt) {
		this.errCtdAprAmt = errCtdAprAmt;
	}

	public String getErrChAprAmt() {
		return errChAprAmt;
	}

	public void setErrChAprAmt(String errChAprAmt) {
		this.errChAprAmt = errChAprAmt;
	}

	public String getErrChRemark() {
		return errChRemark;
	}

	public void setErrChRemark(String errChRemark) {
		this.errChRemark = errChRemark;
	}

	public String getErrClaimStatus() {
		return errClaimStatus;
	}

	public void setErrClaimStatus(String errClaimStatus) {
		this.errClaimStatus = errClaimStatus;
	}

	public String getErrClamFlag() {
		return errClamFlag;
	}

	public void setErrClamFlag(String errClamFlag) {
		this.errClamFlag = errClamFlag;
	}

	public String getClaimInitFlag() {
		return claimInitFlag;
	}

	public void setClaimInitFlag(String claimInitFlag) {
		this.claimInitFlag = claimInitFlag;
	}

	public String getAcoAprAmt() {
		return acoAprAmt;
	}

	public void setAcoAprAmt(String acoAprAmt) {
		this.acoAprAmt = acoAprAmt;
	}

	public String getAcoRemark() {
		return acoRemark;
	}

	public void setAcoRemark(String acoRemark) {
		this.acoRemark = acoRemark;
	}

	public String getWapNo() {
		return wapNo;
	}

	public void setWapNo(String wapNo) {
		this.wapNo = wapNo;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getCeoRemark() {
		return ceoRemark;
	}

	public void setCeoRemark(String ceoRemark) {
		this.ceoRemark = ceoRemark;
	}

	public String[] getCaseSelected() {
		return caseSelected;
	}

	public void setCaseSelected(String[] caseSelected) {
		this.caseSelected = caseSelected;
	}

	public String getHospAdd() {
		return hospAdd;
	}

	public void setHospAdd(String hospAdd) {
		this.hospAdd = hospAdd;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankCategory() {
		return bankCategory;
	}

	public void setBankCategory(String bankCategory) {
		this.bankCategory = bankCategory;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getHospAccName() {
		return hospAccName;
	}

	public void setHospAccName(String hospAccName) {
		this.hospAccName = hospAccName;
	}

	public String getHospId() {
		return hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getHospActiveYN() {
		return hospActiveYN;
	}

	public void setHospActiveYN(String hospActiveYN) {
		this.hospActiveYN = hospActiveYN;
	}

	public void setAuditDate(Date auditDate) {
		SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
		this.auditDate = df1.format(auditDate);
		//this.auditDate = auditDate;
	}

	public String getNextStatus() {
		return nextStatus;
	}

	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}

	public String getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}

	public Integer getMAXSETID() {
		return MAXSETID;
	}

	public void setMAXSETID(Integer mAXSETID) {
		MAXSETID = mAXSETID;
	}

	public String getTDSACTIVEYN() {
		return TDSACTIVEYN;
	}

	public void setTDSACTIVEYN(String tDSACTIVEYN) {
		TDSACTIVEYN = tDSACTIVEYN;
	}

	public String getACTIVEYN() {
		return ACTIVEYN;
	}

	public void setACTIVEYN(String aCTIVEYN) {
		ACTIVEYN = aCTIVEYN;
	}

	

	public Number getHOSPPERCENT() {
		return HOSPPERCENT;
	}

	public void setHOSPPERCENT(Number hOSPPERCENT) {
		HOSPPERCENT = hOSPPERCENT;
	}

	public Number getTRUSTPERCENT() {
		return TRUSTPERCENT;
	}

	public void setTRUSTPERCENT(Number tRUSTPERCENT) {
		TRUSTPERCENT = tRUSTPERCENT;
	}

	public Number getTDSHOSPPERCENT() {
		return TDSHOSPPERCENT;
	}

	public void setTDSHOSPPERCENT(Number tDSHOSPPERCENT) {
		TDSHOSPPERCENT = tDSHOSPPERCENT;
	}

	public Number getTDSPERCENT() {
		return TDSPERCENT;
	}

	public void setTDSPERCENT(Number tDSPERCENT) {
		TDSPERCENT = tDSPERCENT;
	}

	public Number getSURCHARGEPERCENT() {
		return SURCHARGEPERCENT;
	}

	public void setSURCHARGEPERCENT(Number sURCHARGEPERCENT) {
		SURCHARGEPERCENT = sURCHARGEPERCENT;
	}

	public Number getCESSPERCENT() {
		return CESSPERCENT;
	}

	public void setCESSPERCENT(Number cESSPERCENT) {
		CESSPERCENT = cESSPERCENT;
	}

	public char getHOSPTYPE() {
		return HOSPTYPE;
	}

	public void setHOSPTYPE(char hOSPTYPE) {
		HOSPTYPE = hOSPTYPE;
	}

	public String getDIRECTDEDUCTION() {
		return DIRECTDEDUCTION;
	}

	public void setDIRECTDEDUCTION(String dIRECTDEDUCTION) {
		DIRECTDEDUCTION = dIRECTDEDUCTION;
	}

	public Number getSLABAMOUNT() {
		return SLABAMOUNT;
	}

	public void setSLABAMOUNT(Number sLABAMOUNT) {
		SLABAMOUNT = sLABAMOUNT;
	}

	public Number getTOTALTDSAMT() {
		return TOTALTDSAMT;
	}

	public void setTOTALTDSAMT(Number tOTALTDSAMT) {
		TOTALTDSAMT = tOTALTDSAMT;
	}

	public String getHOSPDEDUCTORID() {
		return HOSPDEDUCTORID;
	}

	public void setHOSPDEDUCTORID(String hOSPDEDUCTORID) {
		HOSPDEDUCTORID = hOSPDEDUCTORID;
	}

	public String getRQSTACTIVEYN() {
		return RQSTACTIVEYN;
	}

	public void setRQSTACTIVEYN(String rQSTACTIVEYN) {
		RQSTACTIVEYN = rQSTACTIVEYN;
	}

	public String getSLABAMTAPPLICABLE() {
		return SLABAMTAPPLICABLE;
	}

	public void setSLABAMTAPPLICABLE(String sLABAMTAPPLICABLE) {
		SLABAMTAPPLICABLE = sLABAMTAPPLICABLE;
	}

	public Number getCLAIMAMOUNT() {
		return CLAIMAMOUNT;
	}

	public void setCLAIMAMOUNT(Number cLAIMAMOUNT) {
		CLAIMAMOUNT = cLAIMAMOUNT;
	}

	public String getREVFUNDACTIVE() {
		return REVFUNDACTIVE;
	}

	public void setREVFUNDACTIVE(String rEVFUNDACTIVE) {
		REVFUNDACTIVE = rEVFUNDACTIVE;
	}

	public Number getTOTALCLAIMAMT() {
		return TOTALCLAIMAMT;
	}

	public void setTOTALCLAIMAMT(Number tOTALCLAIMAMT) {
		TOTALCLAIMAMT = tOTALCLAIMAMT;
	}

	public String getHOSPITALID() {
		return HOSPITALID;
	}

	public void setHOSPITALID(String hOSPITALID) {
		HOSPITALID = hOSPITALID;
	}

	public Double getHospPctAmt() {
		return hospPctAmt;
	}

	public void setHospPctAmt(Double hospPctAmt) {
		this.hospPctAmt = hospPctAmt;
	}

	public Double getTdsHospPctAmt() {
		return tdsHospPctAmt;
	}

	public void setTdsHospPctAmt(Double tdsHospPctAmt) {
		this.tdsHospPctAmt = tdsHospPctAmt;
	}

	public Double getTdsPctAmt() {
		return tdsPctAmt;
	}

	public void setTdsPctAmt(Double tdsPctAmt) {
		this.tdsPctAmt = tdsPctAmt;
	}

	public Double getTrustPctAmt() {
		return trustPctAmt;
	}

	public void setTrustPctAmt(Double trustPctAmt) {
		this.trustPctAmt = trustPctAmt;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}



	public Date getFpauditDate() {
		return fpauditDate;
	}

	public void setFpauditDate(Date fpauditDate) {
		this.fpauditDate = fpauditDate;
	}

	public String getErrAcoAprAmt() {
		return errAcoAprAmt;
	}

	public void setErrAcoAprAmt(String errAcoAprAmt) {
		this.errAcoAprAmt = errAcoAprAmt;
	}

	public String getErrAcoRemark() {
		return errAcoRemark;
	}

	public void setErrAcoRemark(String errAcoRemark) {
		this.errAcoRemark = errAcoRemark;
	}

	public String getTdsPaymentId() {
		return tdsPaymentId;
	}

	public void setTdsPaymentId(String tdsPaymentId) {
		this.tdsPaymentId = tdsPaymentId;
	}

	public String getEoAprAmt() {
		return eoAprAmt;
	}

	public void setEoAprAmt(String eoAprAmt) {
		this.eoAprAmt = eoAprAmt;
	}

	public String getEoRemark() {
		return eoRemark;
	}

	public void setEoRemark(String eoRemark) {
		this.eoRemark = eoRemark;
	}

	public String getEoComRemark() {
		return eoComRemark;
	}

	public void setEoComRemark(String eoComRemark) {
		this.eoComRemark = eoComRemark;
	}

	public String getEoComAprAmt() {
		return eoComAprAmt;
	}

	public void setEoComAprAmt(String eoComAprAmt) {
		this.eoComAprAmt = eoComAprAmt;
	}

	public String getShowEO() {
		return showEO;
	}

	public void setShowEO(String showEO) {
		this.showEO = showEO;
	}

	public String getShowEOCom() {
		return showEOCom;
	}

	public void setShowEOCom(String showEOCom) {
		this.showEOCom = showEOCom;
	}

	public String getClaimInitAmt() {
		return claimInitAmt;
	}

	public void setClaimInitAmt(String claimInitAmt) {
		this.claimInitAmt = claimInitAmt;
	}

	public String getNoOfStay() {
		return noOfStay;
	}

	public void setNoOfStay(String noOfStay) {
		this.noOfStay = noOfStay;
	}

	public String getTechCheck4() {
		return techCheck4;
	}

	public void setTechCheck4(String techCheck4) {
		this.techCheck4 = techCheck4;
	}

	public String getTrustDoc4() {
		return trustDoc4;
	}

	public void setTrustDoc4(String trustDoc4) {
		this.trustDoc4 = trustDoc4;
	}

	public String getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(String penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public String getChNabhAmt() {
		return chNabhAmt;
	}

	public void setChNabhAmt(String chNabhAmt) {
		this.chNabhAmt = chNabhAmt;
	}

	public String getChEntAprAmt() {
		return chEntAprAmt;
	}

	public void setChEntAprAmt(String chEntAprAmt) {
		this.chEntAprAmt = chEntAprAmt;
	}

	public String getNabhFlag() {
		return nabhFlag;
	}

	public void setNabhFlag(String nabhFlag) {
		this.nabhFlag = nabhFlag;
	}

	public String getEoComNabhAmt() {
		return eoComNabhAmt;
	}

	public void setEoComNabhAmt(String eoComNabhAmt) {
		this.eoComNabhAmt = eoComNabhAmt;
	}

	public String getEoComEntAprAmt() {
		return eoComEntAprAmt;
	}

	public void setEoComEntAprAmt(String eoComEntAprAmt) {
		this.eoComEntAprAmt = eoComEntAprAmt;
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	public String getFlagged() {
		return flagged;
	}

	public void setFlagged(String flagged) {
		this.flagged = flagged;
	}

	public Date getChronicAuditDate() {
		return chronicAuditDate;
	}

	public void setChronicAuditDate(Date chronicAuditDate) {
		this.chronicAuditDate = chronicAuditDate;
	}

	public String getChronicId() {
		return chronicId;
	}

	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	public String getChronicNo() {
		return chronicNo;
	}

	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}


	public String getCtdFinAprAmt() {
		return ctdFinAprAmt;
	}

	public void setCtdFinAprAmt(String ctdFinAprAmt) {
		this.ctdFinAprAmt = ctdFinAprAmt;
	}

	public String getCtdNabhAmt() {
		return ctdNabhAmt;
	}

	public void setCtdNabhAmt(String ctdNabhAmt) {
		this.ctdNabhAmt = ctdNabhAmt;
	}

	public String getDrugAmount() {
		return drugAmount;
	}

	public void setDrugAmount(String drugAmount) {
		this.drugAmount = drugAmount;
	}

	public String[] getCasesReady() {
		return casesReady;
	}

	public void setCasesReady(String[] casesReady) {
		this.casesReady = casesReady;
	}

	public String getSendBackFlag() {
		return sendBackFlag;
	}

	public void setSendBackFlag(String sendBackFlag) {
		this.sendBackFlag = sendBackFlag;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getNewBornBaby() {
		return newBornBaby;
	}

	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}

	public String getDentalFlag() {
		return dentalFlag;
	}

	public void setDentalFlag(String dentalFlag) {
		this.dentalFlag = dentalFlag;
	}

	public String getAcoFlag() {
		return acoFlag;
	}

	public void setAcoFlag(String acoFlag) {
		this.acoFlag = acoFlag;
	}

	public String getSendBackRmrks() {
		return sendBackRmrks;
	}


	public String getCOCHLEARYN() {
		return COCHLEARYN;
	}

	public void setCOCHLEARYN(String cOCHLEARYN) {
		COCHLEARYN = cOCHLEARYN;
	}



	public void setSendBackRmrks(String sendBackRmrks) {
		this.sendBackRmrks = sendBackRmrks;
	}

	public String getPreAuthApprvDate() {
		return preAuthApprvDate;
	}

	public void setPreAuthApprvDate(String preAuthApprvDate) {
		this.preAuthApprvDate = preAuthApprvDate;
	}
	private String medcoCHUpdRemarks;
	private String medcoCTDUpdRemarks;
	
	public String getMedcoCTDUpdRemarks() {
		return medcoCTDUpdRemarks;
	}
	public void setMedcoCTDUpdRemarks(String medcoCTDUpdRemarks) {
		this.medcoCTDUpdRemarks = medcoCTDUpdRemarks;
	}
	public String getMedcoCHUpdRemarks() {
		return medcoCHUpdRemarks;
	}
	public void setMedcoCHUpdRemarks(String medcoCHUpdRemarks) {
		this.medcoCHUpdRemarks = medcoCHUpdRemarks;
	}
	public String getDentCond() {
		return dentCond;
	}

	public void setDentCond(String dentCond) {
		this.dentCond = dentCond;
	}

	public String getCaseUnits() {
		return caseUnits;
	}

	public void setCaseUnits(String caseUnits) {
		this.caseUnits = caseUnits;
	}

	public String getCaseTherapyId() {
		return caseTherapyId;
	}

	public void setCaseTherapyId(String caseTherapyId) {
		this.caseTherapyId = caseTherapyId;
	}

	public String getTotPackgAmt() {
		return totPackgAmt;
	}

	public void setTotPackgAmt(String totPackgAmt) {
		this.totPackgAmt = totPackgAmt;
	}

	public String getCaseUnitsPar() {
		return caseUnitsPar;
	}

	public void setCaseUnitsPar(String caseUnitsPar) {
		this.caseUnitsPar = caseUnitsPar;
	}

	public String getNabhAmnt() {
		return nabhAmnt;
	}

	public void setNabhAmnt(String nabhAmnt) {
		this.nabhAmnt = nabhAmnt;
	}
    
	public List<LabelBean> getInvestDetails() {
		return investDetails;
	}

	public void setInvestDetails(List<LabelBean> investDetails) {
		this.investDetails = investDetails;
	}

	public Number getTotalConsumableAmount() {
		return totalConsumableAmount;
	}

	public void setTotalConsumableAmount(Number totalConsumableAmount) {
		this.totalConsumableAmount = totalConsumableAmount;
	}


	public String getMedicalSurgFlag() {
		return medicalSurgFlag;
	}

	public void setMedicalSurgFlag(String medicalSurgFlag) {
		this.medicalSurgFlag = medicalSurgFlag;
	}

	public Number getTotalDrugAmt() {
		return totalDrugAmt;
	}

	public void setTotalDrugAmt(Number totalDrugAmt) {
		this.totalDrugAmt = totalDrugAmt;
	}

	public List<LabelBean> getDrugDetails() {
		return drugDetails;
	}

	public void setDrugDetails(List<LabelBean> drugDetails) {
		this.drugDetails = drugDetails;
	}

	public Long getEnhAmounts() {
		return enhAmounts;
	}

	public void setEnhAmounts(Long enhAmounts) {
		this.enhAmounts = enhAmounts;
	}

	public String getNimsFlag() {
		return nimsFlag;
	}

	public void setNimsFlag(String nimsFlag) {
		this.nimsFlag = nimsFlag;
	}
	public Date getCaseStartTime() {
		return caseStartTime;
	}

	public void setCaseStartTime(Date caseStartTime) {
		this.caseStartTime = caseStartTime;
	}

	public Date getCaseEndTime() {
		return caseEndTime;
	}

	public void setCaseEndTime(Date caseEndTime) {
		this.caseEndTime = caseEndTime;
	}
	public String getHeamFlag() {
		return heamFlag;
	}

	public void setHeamFlag(String heamFlag) {
		this.heamFlag = heamFlag;
	}

	public String getICDPROCCODE() {
		return ICDPROCCODE;
	}

	public void setICDPROCCODE(String iCDPROCCODE) {
		ICDPROCCODE = iCDPROCCODE;
	}
	//Chandana - 6444 - 04-02-2025
	public String getHUBSPOKEFLAG() {
		return HUBSPOKEFLAG;
	}
	public String getNAMEASPERACT() {
		return NAMEASPERACT;
	}
	public void setNAMEASPERACT(String nAMEASPERACT) {
		NAMEASPERACT = nAMEASPERACT;
	}
	public void setHUBSPOKEFLAG(String hUBSPOKEFLAG) {
		HUBSPOKEFLAG = hUBSPOKEFLAG;
	}
	public Number getPKGAMT() {
		return PKGAMT;
	}
	public void setPKGAMT(Number pKGAMT) {
		PKGAMT = pKGAMT;
	}
	public Double getHUB_AMT() {
		return HUB_AMT;
	}
	public void setHUB_AMT(Double hUB_AMT) {
		HUB_AMT = hUB_AMT;
	}
	public Double getSPOKE_AMT() {
		return SPOKE_AMT;
	}
	public void setSPOKE_AMT(Double sPOKE_AMT) {
		SPOKE_AMT = sPOKE_AMT;
	}
	public String getAGENCY_BANK_ID() {
		return AGENCY_BANK_ID;
	}
	public void setAGENCY_BANK_ID(String aGENCY_BANK_ID) {
		AGENCY_BANK_ID = aGENCY_BANK_ID;
	}
	public Number getCYCLECOUNT() {
		return CYCLECOUNT;
	}
	public void setCYCLECOUNT(Number cYCLECOUNT) {
		CYCLECOUNT = cYCLECOUNT;
	}
	public Number getACTORDER() {
		return ACTORDER;
	}
	public void setACTORDER(Number aCTORDER) {
		ACTORDER = aCTORDER;
	}
	public String getACTID() {
		return ACTID;
	}
	public void setACTID(String aCTID) {
		ACTID = aCTID;
	}
	public Double getHM_MNT() {
		return HM_MNT;
	}
	public void setHM_MNT(Double hM_MNT) {
		HM_MNT = hM_MNT;
	}
	public Double getSM_MNT() {
		return SM_MNT;
	}
	public void setSM_MNT(Double sM_MNT) {
		SM_MNT = sM_MNT;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Double getHospPercent() {
		return hospPercent;
	}
	public void setHospPercent(Double hospPercent) {
		this.hospPercent = hospPercent;
	}
	public Double getAgencyPctAmt() {
		return agencyPctAmt;
	}
	public void setAgencyPctAmt(Double agencyPctAmt) {
		this.agencyPctAmt = agencyPctAmt;
	}
	public String getACCTNO() {
		return ACCTNO;
	}
	public void setACCTNO(String aCCTNO) {
		ACCTNO = aCCTNO;
	}
	public String getBANKID() {
		return BANKID;
	}
	public void setBANKID(String bANKID) {
		BANKID = bANKID;
	}
	public String getIFSCCODE() {
		return IFSCCODE;
	}
	public void setIFSCCODE(String iFSCCODE) {
		IFSCCODE = iFSCCODE;
	}
	public String getBANKNAME() {
		return BANKNAME;
	}
	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}
	public String getBANKBRANCH() {
		return BANKBRANCH;
	}
	public void setBANKBRANCH(String bANKBRANCH) {
		BANKBRANCH = bANKBRANCH;
	}
	public String getBANKCATEGORY() {
		return BANKCATEGORY;
	}
	public void setBANKCATEGORY(String bANKCATEGORY) {
		BANKCATEGORY = bANKCATEGORY;
	}
}
