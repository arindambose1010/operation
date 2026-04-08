
package com.ahct.claims.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.panelDoctor.vo.PanelDocPayVO;
import com.ahct.preauth.vo.PreauthVO;

/**
 * @author Ishank Paharia
 * @class This Class is used as form for claim
 * @version jdk 1.6
 * @Date  4 April 2013
 */
public class ClaimsFlowForm extends ActionForm{

	private String newBornBaby;
	private String paymentType;
	private String medcoNabhAmount;
	private String ceoRemark;
	private String errChRemark;
	private String showCh1;
	private String errChAppAmount;
	private String showCtd1;
	private String showAco;
	private String showAco1;
	private String showCpdOrg;
	public String getShowCpdOrg() {
		return showCpdOrg;
	}

	public void setShowCpdOrg(String showCpdOrg) {
		this.showCpdOrg = showCpdOrg;
	}
	private String dispErrInitBlock;
	private String addAttach;
	private String viewAttach;
	private String wapNo;
	private String patName;
	private String fromDate;
	private String toDate;
	private String genFlag;
	private String errClaimStatus; 
	private List<LabelBean> casesForPaymentList;
	private String[] caseSelected;
	private String claimInitAmt;
	private String penaltyAmount;
	private String phaseId;
	private String chronicId;
	private String cocFinal;
	private String ctdFinAprAmt;
	private String errStatusId;
	private String ctdNabhAmt;
	private String medcoProcUnits;
	private String ctdProcUnits;
	private String chProcUnits;
	private String caseUnits;
	private String caseUnitsPar;
	private Number totalConsumableAmount;
	private Number totalDrugAmount;
	private String medicalSurgFlag;
    private String techCheckOrg1;
    private String techCheckOrg2;
    private String techCheckOrg3;
    private String techCheckOrg4;
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
	/** The case id. */
	private String caseId;
	
	/** The case status. */
	private String caseStatus;
	
	/** The dt time. */
	private String dtTime;
	
	/** The package amt. */
	private String packageAmt;
	
	/** The preauth date. */
	private String preauthDate;
	
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
	
	/** The online dschrge date. */
	private String onlineDschrgeDate;
	
	/** The online surg date. */
	private String onlineSurgDate;
	
	/** The online adm date. */
	private String onlineAdmDate;
	
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
	
	/** The trust doc1. */
	private String trustDoc1;
	
	/** The trust doc2. */
	private String trustDoc2;
	
	/** The trust doc3. */
	private String trustDoc3;
	private String trustDoc4;
	
	/** The tech check1. */
	private String techCheck1;
	
	/** The tech check2. */
	private String techCheck2;
	
	/** The tech check3. */
	private String techCheck3;
	
	/** The tech check4. */
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
	
	/** The msg. */
	private String msg;
	
	/** The role id. */
	private String roleId;
	
	/** The show cex. */
	private String showCex;
	
	/** The show cpd. */
	private String showCpd;
	
	/** The show ctd. */
	private String showCtd;
	
	/** The show ch. */
	private String showCh;	
    
    /** The claim info. */
    private ClaimsFlowVO claimInfo=null;
    
    /** The lstwork flow. */
    private List<ClaimsFlowVO> lstworkFlow;
	
	/** The err claim paid. */
	private String errClaimPaid;
	
	/** The err amount. */
	private String errAmount;
	
	/** The err sub date. */
	private String errSubDate;
	
	/** The err med remark. */
	private String errMedRemark;
	
	/** The err app amount. */
	private String errAppAmount;

	/** The err ctd remark. */
	private String errCtdRemark;
	
	/** The ctd remark. */
	private String ctdRemark;
	
	/** The ch remark. */
	private String chRemark;
	
	private String billDt;
	private String billAmt;
	private String medcoRemark;
	private String cexAprAmt;
	private String cpdAprAmt;
	private String cpdAprAmtOrg;
	public String getCpdAprAmtOrg() {
		return cpdAprAmtOrg;
	}

	public void setCpdAprAmtOrg(String cpdAprAmtOrg) {
		this.cpdAprAmtOrg = cpdAprAmtOrg;
	}
	private String ctdAprAmt;
	private String chAprAmt;
	private String acoAprAmt;
	private String acoRemark;
	private String errAcoAprAmt;
	private String errAcoRemark;
	private String showEo;
	private String showEoCom;
	private String eoAprAmt;
	private String eoRemark;
	private String eoComRemark;
	private String eoComAprAmt;
	private String showScheme;
	private String schemeType;
	private String chNabhAmt;
	private String chEntAprAmt;
	private String nabhFlag;
	private String eoComEntAprAmt;
	private String eoComNabhAmt;
	private String paymentStatus;
	 private String dispType;
	 private String month;
	 private String year;
	 private List<LabelBean> monthlist;
	 private List<LabelBean> yearlist;
	 private String flag;
	 private List<PanelDocPayVO> PanelDocList;
	 private List<PanelDocPayVO> PanelDocCasesList;
	 private List<PanelDocPayVO> caseCountStatus;
	 private String checkId;
	 private Float amount;
	 private String radioValue;
	 private String result;
	 private String status;
	 private String selPeriod;
	 private String docid;
	 private String action;
	 private String remarks;
	 private String rejId;
	 private List<LabelBean> paymentStatusList;
	 private String actionSelect;
	 private String ID;
	 private String totalAmt;
	 private String userType;
	 private String scheme;
	 private List<LabelBean> schemeList;
	 private String tdsStatus;
	 private List<LabelBean> tdsStatusList;
	 private Long wId;
	 private String checkAll;
	 private String individualPayment; 
	 
	private String endIndex;
	private String totalRows;
	private String rowsPerPage;
	private String showPage;
	private String next;
	private String prev;
	private String startIndex;
	private List<PreauthVO> lstPreauthVO;
	private String preAuthApprvDate;
	private List<LabelBean> investDetails;
	private List<LabelBean> drugDetails;
	private String sendtoPanelDoc;
	private String eoComSendRemarks;
	private String sendbackPpdremarks;
	private String allUsers;
	
	public String getPreAuthApprvDate() {
		return preAuthApprvDate;
	}

	public void setPreAuthApprvDate(String preAuthApprvDate) {
		this.preAuthApprvDate = preAuthApprvDate;
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

	public String getAddAttach() {
		return addAttach;
	}

	public void setAddAttach(String addAttach) {
		this.addAttach = addAttach;
	}

	public String getViewAttach() {
		return viewAttach;
	}

	public void setViewAttach(String viewAttach) {
		this.viewAttach = viewAttach;
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

	/**
	 * Gets the lstwork flow.
	 *
	 * @return the lstwork flow
	 */
	public List<ClaimsFlowVO> getLstworkFlow() {
		return lstworkFlow;
	}
	
	/**
	 * Sets the lstwork flow.
	 *
	 * @param lstworkFlow the new lstwork flow
	 */
	public void setLstworkFlow(List<ClaimsFlowVO> lstworkFlow) {
		this.lstworkFlow = lstworkFlow;
	}
	
	/**
	 * Gets the claim info.
	 *
	 * @return the claim info
	 */
	public ClaimsFlowVO getClaimInfo() {
		return claimInfo;
	}
	
	/**
	 * Sets the claim info.
	 *
	 * @param claimInfo the new claim info
	 */
	public void setClaimInfo(ClaimsFlowVO claimInfo) {
		this.claimInfo = claimInfo;
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
	 * Gets the case status.
	 *
	 * @return the case status
	 */
	public String getCaseStatus() {
		return caseStatus;
	}
	
	/**
	 * Sets the case status.
	 *
	 * @param caseStatus the new case status
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	/**
	 * Gets the dt time.
	 *
	 * @return the dt time
	 */
	public String getDtTime() {
		return dtTime;
	}
	
	/**
	 * Sets the dt time.
	 *
	 * @param dtTime the new dt time
	 */
	public void setDtTime(String dtTime) {
		this.dtTime = dtTime;
	}
	
	/**
	 * Gets the package amt.
	 *
	 * @return the package amt
	 */
	public String getPackageAmt() {
		return packageAmt;
	}
	
	/**
	 * Sets the package amt.
	 *
	 * @param packageAmt the new package amt
	 */
	public void setPackageAmt(String packageAmt) {
		this.packageAmt = packageAmt;
	}
	
	/**
	 * Gets the preauth date.
	 *
	 */
	public String getPreauthDate() {
		return preauthDate;
	}
	
	/**
	 * Sets the preauth date.
	 *
	 * @param preauthDate the new preauth date
	 */
	public void setPreauthDate(String preauthDate) {
		this.preauthDate = preauthDate;
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
	 * Gets the online dschrge date.
	 *
	 * @return the online dschrge date
	 */
	public String getOnlineDschrgeDate() {
		return onlineDschrgeDate;
	}
	
	/**
	 * Sets the online dschrge date.
	 *
	 * @param onlineDschrgeDate the new online dschrge date
	 */
	public void setOnlineDschrgeDate(String onlineDschrgeDate) {
		this.onlineDschrgeDate = onlineDschrgeDate;
	}
	
	/**
	 * Gets the online surg date.
	 *
	 * @return the online surg date
	 */
	public String getOnlineSurgDate() {
		return onlineSurgDate;
	}
	
	/**
	 * Sets the online surg date.
	 *
	 * @param onlineSurgDate the new online surg date
	 */
	public void setOnlineSurgDate(String onlineSurgDate) {
		this.onlineSurgDate = onlineSurgDate;
	}
	
	/**
	 * Gets the online adm date.
	 *
	 * @return the online adm date
	 */
	public String getOnlineAdmDate() {
		return onlineAdmDate;
	}
	
	/**
	 * Sets the online adm date.
	 *
	 * @param onlineAdmDate the new online adm date
	 */
	public void setOnlineAdmDate(String onlineAdmDate) {
		this.onlineAdmDate = onlineAdmDate;
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
	 * Gets the show cex.
	 *
	 * @return the show cex
	 */
	public String getShowCex() {
		return showCex;
	}
	
	/**
	 * Sets the show cex.
	 *
	 * @param showCex the new show cex
	 */
	public void setShowCex(String showCex) {
		this.showCex = showCex;
	}
	
	/**
	 * Gets the show cpd.
	 *
	 * @return the show cpd
	 */
	public String getShowCpd() {
		return showCpd;
	}
	
	/**
	 * Sets the show cpd.
	 *
	 * @param showCpd the new show cpd
	 */
	public void setShowCpd(String showCpd) {
		this.showCpd = showCpd;
	}
	
	/**
	 * Gets the show ctd.
	 *
	 * @return the show ctd
	 */
	public String getShowCtd() {
		return showCtd;
	}
	
	/**
	 * Sets the show ctd.
	 *
	 * @param showCtd the new show ctd
	 */
	public void setShowCtd(String showCtd) {
		this.showCtd = showCtd;
	}
	
	/**
	 * Gets the show ch.
	 *
	 * @return the show ch
	 */
	public String getShowCh() {
		return showCh;
	}
	
	/**
	 * Sets the show ch.
	 *
	 * @param showCh the new show ch
	 */
	public void setShowCh(String showCh) {
		this.showCh = showCh;
	}
	
	/**
	 * Gets the err claim paid.
	 *
	 * @return the err claim paid
	 */
	public String getErrClaimPaid() {
		return errClaimPaid;
	}
	
	/**
	 * Sets the err claim paid.
	 *
	 * @param errClaimPaid the new err claim paid
	 */
	public void setErrClaimPaid(String errClaimPaid) {
		this.errClaimPaid = errClaimPaid;
	}
	
	/**
	 * Gets the err amount.
	 *
	 * @return the err amount
	 */
	public String getErrAmount() {
		return errAmount;
	}
	
	/**
	 * Sets the err amount.
	 *
	 * @param errAmount the new err amount
	 */
	public void setErrAmount(String errAmount) {
		this.errAmount = errAmount;
	}
	
	/**
	 * Gets the err sub date.
	 *
	 * @return the err sub date
	 */
	public String getErrSubDate() {
		return errSubDate;
	}
	
	/**
	 * Sets the err sub date.
	 *
	 * @param errSubDate the new err sub date
	 */
	public void setErrSubDate(String errSubDate) {
		this.errSubDate = errSubDate;
	}
	
	/**
	 * Gets the err med remark.
	 *
	 * @return the err med remark
	 */
	public String getErrMedRemark() {
		return errMedRemark;
	}
	
	/**
	 * Sets the err med remark.
	 *
	 * @param errMedRemark the new err med remark
	 */
	public void setErrMedRemark(String errMedRemark) {
		this.errMedRemark = errMedRemark;
	}
	
	/**
	 * Gets the err app amount.
	 *
	 * @return the err app amount
	 */
	public String getErrAppAmount() {
		return errAppAmount;
	}
	
	/**
	 * Sets the err app amount.
	 *
	 * @param errAppAmount the new err app amount
	 */
	public void setErrAppAmount(String errAppAmount) {
		this.errAppAmount = errAppAmount;
	}
	
	/**
	 * Gets the err ctd remark.
	 *
	 * @return the err ctd remark
	 */
	public String getErrCtdRemark() {
		return errCtdRemark;
	}
	
	/**
	 * Sets the err ctd remark.
	 *
	 * @param errCtdRemark the new err ctd remark
	 */
	public void setErrCtdRemark(String errCtdRemark) {
		this.errCtdRemark = errCtdRemark;
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

	public String getDispErrInitBlock() {
		return dispErrInitBlock;
	}

	public void setDispErrInitBlock(String dispErrInitBlock) {
		this.dispErrInitBlock = dispErrInitBlock;
	}

	public String getShowCtd1() {
		return showCtd1;
	}

	public void setShowCtd1(String showCtd1) {
		this.showCtd1 = showCtd1;
	}

	public String getErrChRemark() {
		return errChRemark;
	}

	public void setErrChRemark(String errChRemark) {
		this.errChRemark = errChRemark;
	}

	public String getShowCh1() {
		return showCh1;
	}

	public void setShowCh1(String showCh1) {
		this.showCh1 = showCh1;
	}

	public String getErrChAppAmount() {
		return errChAppAmount;
	}

	public void setErrChAppAmount(String errChAppAmount) {
		this.errChAppAmount = errChAppAmount;
	}

	public String getShowAco() {
		return showAco;
	}

	public void setShowAco(String showAco) {
		this.showAco = showAco;
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

	public List<LabelBean> getCasesForPaymentList() {
		return casesForPaymentList;
	}

	public void setCasesForPaymentList(List<LabelBean> casesForPaymentList) {
		this.casesForPaymentList = casesForPaymentList;
	}

	public String getGenFlag() {
		return genFlag;
	}

	public void setGenFlag(String genFlag) {
		this.genFlag = genFlag;
	}

	public String[] getCaseSelected() {
		return caseSelected;
	}

	public void setCaseSelected(String[] caseSelected) {
		this.caseSelected = caseSelected;
	}

	public String getCeoRemark() {
		return ceoRemark;
	}

	public void setCeoRemark(String ceoRemark) {
		this.ceoRemark = ceoRemark;
	}

	public String getShowAco1() {
		return showAco1;
	}

	public void setShowAco1(String showAco1) {
		this.showAco1 = showAco1;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getErrClaimStatus() {
		return errClaimStatus;
	}

	public void setErrClaimStatus(String errClaimStatus) {
		this.errClaimStatus = errClaimStatus;
	}

	public String getShowEo() {
		return showEo;
	}

	public void setShowEo(String showEo) {
		this.showEo = showEo;
	}

	public String getShowEoCom() {
		return showEoCom;
	}

	public void setShowEoCom(String showEoCom) {
		this.showEoCom = showEoCom;
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

	public String getClaimInitAmt() {
		return claimInitAmt;
	}

	public void setClaimInitAmt(String claimInitAmt) {
		this.claimInitAmt = claimInitAmt;
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

	public String getShowScheme() {
		return showScheme;
	}

	public void setShowScheme(String showScheme) {
		this.showScheme = showScheme;
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

	public String getEoComEntAprAmt() {
		return eoComEntAprAmt;
	}

	public void setEoComEntAprAmt(String eoComEntAprAmt) {
		this.eoComEntAprAmt = eoComEntAprAmt;
	}

	public String getEoComNabhAmt() {
		return eoComNabhAmt;
	}

	public void setEoComNabhAmt(String eoComNabhAmt) {
		this.eoComNabhAmt = eoComNabhAmt;
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	public String getMedcoNabhAmount() {
		return medcoNabhAmount;
	}

	public void setMedcoNabhAmount(String medcoNabhAmount) {
		this.medcoNabhAmount = medcoNabhAmount;
	}

	public String getChronicId() {
		return chronicId;
	}

	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	public String getCocFinal() {
		return cocFinal;
	}

	public void setCocFinal(String cocFinal) {
		this.cocFinal = cocFinal;
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

	public String getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}

	public String getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getShowPage() {
		return showPage;
	}

	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;

	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getDispType() {
		return dispType;
	}

	public void setDispType(String dispType) {
		this.dispType = dispType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<LabelBean> getMonthlist() {
		return monthlist;
	}

	public void setMonthlist(List<LabelBean> monthlist) {
		this.monthlist = monthlist;
	}

	public List<LabelBean> getYearlist() {
		return yearlist;
	}

	public void setYearlist(List<LabelBean> yearlist) {
		this.yearlist = yearlist;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<PanelDocPayVO> getPanelDocList() {
		return PanelDocList;
	}

	public void setPanelDocList(List<PanelDocPayVO> panelDocList) {
		PanelDocList = panelDocList;
	}

	public List<PanelDocPayVO> getPanelDocCasesList() {
		return PanelDocCasesList;
	}

	public void setPanelDocCasesList(List<PanelDocPayVO> panelDocCasesList) {
		PanelDocCasesList = panelDocCasesList;
	}

	public List<PanelDocPayVO> getCaseCountStatus() {
		return caseCountStatus;
	}

	public void setCaseCountStatus(List<PanelDocPayVO> caseCountStatus) {
		this.caseCountStatus = caseCountStatus;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getRadioValue() {
		return radioValue;
	}

	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSelPeriod() {
		return selPeriod;
	}

	public void setSelPeriod(String selPeriod) {
		this.selPeriod = selPeriod;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRejId() {
		return rejId;
	}

	public void setRejId(String rejId) {
		this.rejId = rejId;
	}

	public List<LabelBean> getPaymentStatusList() {
		return paymentStatusList;
	}

	public void setPaymentStatusList(List<LabelBean> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public String getActionSelect() {
		return actionSelect;
	}

	public void setActionSelect(String actionSelect) {
		this.actionSelect = actionSelect;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public List<LabelBean> getSchemeList() {
		return schemeList;
	}

	public void setSchemeList(List<LabelBean> schemeList) {
		this.schemeList = schemeList;
	}

	public String getTdsStatus() {
		return tdsStatus;
	}

	public void setTdsStatus(String tdsStatus) {
		this.tdsStatus = tdsStatus;
	}

	public List<LabelBean> getTdsStatusList() {
		return tdsStatusList;
	}

	public void setTdsStatusList(List<LabelBean> tdsStatusList) {
		this.tdsStatusList = tdsStatusList;
	}

	public Long getwId() {
		return wId;
	}

	public void setwId(Long wId) {
		this.wId = wId;
	}

	public String getCheckAll() {
		return checkAll;
	}

	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}

	public String getIndividualPayment() {
		return individualPayment;
	}

	public void setIndividualPayment(String individualPayment) {
		this.individualPayment = individualPayment;
	}

	public String getErrStatusId() {
		return errStatusId;
	}

	public void setErrStatusId(String errStatusId) {
		this.errStatusId = errStatusId;

	}	
	public String getNewBornBaby() {
		return newBornBaby;
	}

	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}

	public List<PreauthVO> getLstPreauthVO() {
		return lstPreauthVO;
	}

	public void setLstPreauthVO(List<PreauthVO> lstPreauthVO) {
		this.lstPreauthVO = lstPreauthVO;
	}

	public String getMedcoProcUnits() {
		return medcoProcUnits;
	}

	public void setMedcoProcUnits(String medcoProcUnits) {
		this.medcoProcUnits = medcoProcUnits;
	}

	public String getCtdProcUnits() {
		return ctdProcUnits;
	}

	public void setCtdProcUnits(String ctdProcUnits) {
		this.ctdProcUnits = ctdProcUnits;
	}

	public String getChProcUnits() {
		return chProcUnits;
	}

	public void setChProcUnits(String chProcUnits) {
		this.chProcUnits = chProcUnits;
	}

	public String getCaseUnits() {
		return caseUnits;
	}

	public void setCaseUnits(String caseUnits) {
		this.caseUnits = caseUnits;
	}

	public String getCaseUnitsPar() {
		return caseUnitsPar;
	}

	public void setCaseUnitsPar(String caseUnitsPar) {
		this.caseUnitsPar = caseUnitsPar;
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

	public List<LabelBean> getDrugDetails() {
		return drugDetails;
	}

	public void setDrugDetails(List<LabelBean> drugDetails) {
		this.drugDetails = drugDetails;
	}

	public String getMedicalSurgFlag() {
		return medicalSurgFlag;
	}

	public void setMedicalSurgFlag(String medicalSurgFlag) {
		this.medicalSurgFlag = medicalSurgFlag;
	}

	public Number getTotalDrugAmount() {
		return totalDrugAmount;
	}

	public void setTotalDrugAmount(Number totalDrugAmount) {
		this.totalDrugAmount = totalDrugAmount;
	}

	public String getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(String allUsers) {
		this.allUsers = allUsers;
	}
	public String getSendbackPpdremarks() {
		return sendbackPpdremarks;
	}

	public void setSendbackPpdremarks(String sendbackPpdremarks) {
		this.sendbackPpdremarks = sendbackPpdremarks;
	}

	public String getSendtoPanelDoc() {
		return sendtoPanelDoc;
	}

	public void setSendtoPanelDoc(String sendtoPanelDoc) {
		this.sendtoPanelDoc = sendtoPanelDoc;
	}


	public String getEoComSendRemarks() {
		return eoComSendRemarks;
	}

	public void setEoComSendRemarks(String eoComSendRemarks) {
		this.eoComSendRemarks = eoComSendRemarks;
	}
}
