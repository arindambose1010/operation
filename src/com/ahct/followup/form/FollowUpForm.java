package com.ahct.followup.form;

import java.util.List;
import java .util.Date;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.preauth.vo.AttachmentVO;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PatientVO;



public class FollowUpForm extends ActionForm{
    /*Start for followUP init*/
	private String bloodPD;
	private String bloodPS;
	private String pluseRate;
	private String temp;
	private String heartRate;
	private String lungs;
	private String fluidIn;
	private String fluidOut;
	private String hemoglobin;
	private String respRate;
	private String investigations;
	private String[] finalInvest;
	private String caseId;
	private String clinicalId;
	private String wapNo;
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String drugSubTypeName;
	private String asriDrugCode;
	private String drugName;
	private String drugCode;
	private String route;
	private String strength;
	private String dosage;
	private String medicatPeriod;
	
	/*End for followUP init*/
	/* Start for follow up claim */
	private String claimAmt;
	private String claimNwhAmt;
    private String medcoRemarks;
    private String namRemarks;
    public String exeDisphotoChklst;
	public String exeDisphotoremark;
	public String exePatphotoChklst;
	public String exePatphotoRemark;
	public String exeAcqvrifyChklst;
	public String exeAcqverifyRemark;
	public String exeMedverifyChklst;
	public String exeMedVerifyRemark;
	public String exeQuantverifyRemark;
	public String exeQuantverifyChklst;
	public String exereprtcheckChklst;
	public String exeReprtcheckRemark;
	public String ftdRemarkvrifedChklst;
	public String ftdRemarkvrifedRemark;
	public String ftdBeneficiryChklst;
	public String ftdBeneficiryRemark;
	private String ftdRmks;
	private String showNam;
	private String showFcx;
	private String showFtd;
	private String followUpId;
	private String followUpDt;
	private String packageAmt;
	/* End for follow up claim */
	/*Start for pagination*/
	private String startIndex;
	private String endIndex;
	private String totalRows;
	private String rowsPerPage;
	private String caseNo;
	private String fromDate;
	private String toDate;
	private List<CasesSearchVO> lstCaseSearch;
	private String patName;
	private String next;
	private String prev;
	private String showPage;
	/*End for pagination*/
	private List<FollowUpVO> followUPLst;
	private List<FollowUpVO> followUPTotalLst;
	 private List<LabelBean> buttonList=null;
	 private String msg;
	 /** The lstwork flow. */
	    private List<ClaimsFlowVO> lstworkFlow;
	 private String claimNamAmt;
	 private String claimFcxAmt;
	 private String claimFtdAmt;
	 private String claimChAmt;
	 private String fcxRemark;
	 private String chRemark;
	 private String showCh;
	 private String drugs;
	 private FormFile genAttach[]= new FormFile[100];
	 private List<com.ahct.attachments.vo.AttachmentVO> lstAttachments;
	 private List<com.ahct.attachments.vo.AttachmentVO> lstCompDocs;
	 private List<com.ahct.attachments.vo.AttachmentVO> lstCompPhotos;
	 private List<com.ahct.attachments.vo.AttachmentVO> lstCompDTRS;
	 
	 private String casesForApprovalFlag;
     private String roleId;
     private String acoAprAmt;
 	 private String acoRemark;
 	 private String showAco;
 	private List<LabelBean> casesForPaymentList;
 	private String genFlag;
    private String followUpStatus; 
    private String[] caseSelected;
 	private String ceoRemark;
 	private String genInvestigations;
 	private String genBlockInvestName;
 	private String addAttach;
	private String viewAttach;
	private PatientVO patient;
	private List<FollowUpVO> FollowUpData;
	private List<DrugsVO> drugsList;
	private String showScheme;
	private String schemeType;
 	private String photoUrl;
 	private String csSurgDt;
 	private String csDisDt;
 	private String csAdmDt;
 	private String nxtFollowUpDt;
 	private Date nxtFollowUpMin;
 	private String batchNumber;
 	private String drugExpiryDt;
 	private String claimFcxPharmBill;
 	private String claimFcxConsulBill;
 	private String claimFcxInvestBill;
 	private String claimFTDPharmBill;
 	private String claimFTDConsulBill;
 	private String claimFTDInvestBill;
 	private String claimFTDTotBill;
 	private String claimCHPharmBill;
 	private String claimCHConsulBill;
 	private String claimCHInvestBill;
 	private String newPckAmt;
 	private String newFwdAmt;
 	private String newtotAmt;
 	private String newFTDPckAmt;
 	private String newFTDFwdAmt;
 	private String newFTDtotAmt;
 	private String newCHPckAmt;
 	private String newCHFwdAmt;
 	private String newCHtotAmt;
 	private String newClmFwdAmt;
 	
 	/*
 	 * Added by Srikalyan for Cochlear FollowUp
 	 */
 	private List<FollowUpVO> followUpCochList;
 	private String postOP;
 	private String postOPRemarks;
 	private String woundSight;
 	private String switchOnDate;
 	private String audiologist;
 	private String childProgressRemarks;
 	private String reviewDate;
 	private String audiologistName;
 	private String fromDatePrev[]=new String[5];
 	private String toDatePrev[]=new String[5];
 	private String childProgressRemarksPrev[]=new String[5];
 	private String reviewDatePrev[]=new String[5];
 	private String audiologistNamePrev[]=new String[5];
 	private String firstButtonsDiv;
 	private List<FollowUpVO> followUpCochClaimList;
 	private List<FollowUpVO> prevFlpDtls;
	private String cardNo;
	private String followUpIdHidden;
	private String caseIdHidden;
	private String lstrUsrGrp;
	private String submitButton;
	private String claimCocCmtAmt;
	private String coccmtRemark;
	private String claimDyEoAmt;
	private String dyeoRemark;
	private String searchFollowUpStatus;
	private String searchCaseNo;
	private String searchFollowUpId;
	private String searchPatName;
	private String searchFromDate;
	private String searchToDate;
	private String searchCardNo;
	private String searchSchemeType;
	private List<FollowUpVO> auditLst;
	private String sendBackRmrks;
	private String paymentRadio;
	private String csvFollowUpStatus;
	private String csvCaseNo;
	private String csvFollowUpId;
	private String csvPatName;
	private String csvFromDate;
	private String csvToDate;
	private String csvCardNo;
	private String csvSchemeType;
	
 	public String getFromDatePrev(int index)
 		{
 			return fromDatePrev[index];
 		}
 	public void setFromDatePrev(int index,String value)
 		{
 			fromDatePrev[index]=value;
 		}
 	
 	public String getToDatePrev(int index)
		{
			return toDatePrev[index];
		}
	public void setToDatePrev(int index,String value)
		{
			toDatePrev[index]=value;
		}
	
	public String getChildProgressRemarksPrev(int index)
		{
			return childProgressRemarksPrev[index];
		}
	public void setChildProgressRemarksPrev(int index,String value)
		{
			childProgressRemarksPrev[index]=value;
		}
	
	public String getReviewDatePrev(int index)
		{
			return reviewDatePrev[index];
		}
	public void setReviewDatePrev(int index,String value)
		{
			reviewDatePrev[index]=value;
		}
	
	public String getAudiologistNamePrev(int index)
		{
			return audiologistNamePrev[index];
		}
	public void setAudiologistNamePrev(int index,String value)
		{
			audiologistNamePrev[index]=value;
		}
	
    public String getWapNo() {
		return wapNo;
	}
	public void setWapNo(String wapNo) {
		this.wapNo = wapNo;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getCasesForApprovalFlag() {
	 	return casesForApprovalFlag;
	 }
	 public void setCasesForApprovalFlag(String casesForApprovalFlag) {
	 	this.casesForApprovalFlag = casesForApprovalFlag;
	 }
	 
	 
	 public List<FollowUpVO> getFollowUPTotalLst() {
		return followUPTotalLst;
	}
	public void setFollowUPTotalLst(List<FollowUpVO> followUPTotalLst) {
		this.followUPTotalLst = followUPTotalLst;
	}
	public List<com.ahct.attachments.vo.AttachmentVO> getLstAttachments() {
		return lstAttachments;
	}
	public void setLstAttachments(List<com.ahct.attachments.vo.AttachmentVO> lstAttachments) {
		this.lstAttachments = lstAttachments;
	}
	public FormFile getGenAttach(int index) {
			return genAttach[index];
		}
		public void setGenAttach(int index,FormFile value) {
			this.genAttach[index] = value;
		}

	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getClinicalId() {
		return clinicalId;
	}
	public void setClinicalId(String clinicalId) {
		this.clinicalId = clinicalId;
	}
	public String getShowCh() {
		return showCh;
	}
	public void setShowCh(String showCh) {
		this.showCh = showCh;
	}
	public String getClaimNamAmt() {
		return claimNamAmt;
	}
	public void setClaimNamAmt(String claimNamAmt) {
		this.claimNamAmt = claimNamAmt;
	}
	public String getClaimFcxAmt() {
		return claimFcxAmt;
	}
	public void setClaimFcxAmt(String claimFcxAmt) {
		this.claimFcxAmt = claimFcxAmt;
	}
	public String getClaimFtdAmt() {
		return claimFtdAmt;
	}
	public void setClaimFtdAmt(String claimFtdAmt) {
		this.claimFtdAmt = claimFtdAmt;
	}
	public String getClaimChAmt() {
		return claimChAmt;
	}
	public void setClaimChAmt(String claimChAmt) {
		this.claimChAmt = claimChAmt;
	}
	public String getFcxRemark() {
		return fcxRemark;
	}
	public void setFcxRemark(String fcxRemark) {
		this.fcxRemark = fcxRemark;
	}
	public String getChRemark() {
		return chRemark;
	}
	public void setChRemark(String chRemark) {
		this.chRemark = chRemark;
	}
	public List<ClaimsFlowVO> getLstworkFlow() {
		return lstworkFlow;
	}
	public void setLstworkFlow(List<ClaimsFlowVO> lstworkFlow) {
		this.lstworkFlow = lstworkFlow;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getClaimNwhAmt() {
		return claimNwhAmt;
	}
	public void setClaimNwhAmt(String claimNwhAmt) {
		this.claimNwhAmt = claimNwhAmt;
	}
	public String getBloodPD() {
		return bloodPD;
	}
	public void setBloodPD(String bloodPD) {
		this.bloodPD = bloodPD;
	}
	public String getBloodPS() {
		return bloodPS;
	}
	public void setBloodPS(String bloodPS) {
		this.bloodPS = bloodPS;
	}
	public String getPluseRate() {
		return pluseRate;
	}
	public void setPluseRate(String pluseRate) {
		this.pluseRate = pluseRate;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}
	public String getLungs() {
		return lungs;
	}
	public void setLungs(String lungs) {
		this.lungs = lungs;
	}
	public String getFluidIn() {
		return fluidIn;
	}
	public void setFluidIn(String fluidIn) {
		this.fluidIn = fluidIn;
	}
	public String getFluidOut() {
		return fluidOut;
	}
	public void setFluidOut(String fluidOut) {
		this.fluidOut = fluidOut;
	}
	public String getHemoglobin() {
		return hemoglobin;
	}
	public void setHemoglobin(String hemoglobin) {
		this.hemoglobin = hemoglobin;
	}
	public String getInvestigations() {
		return investigations;
	}
	public void setInvestigations(String investigations) {
		this.investigations = investigations;
	}
	public String[] getFinalInvest() {
		return finalInvest;
	}
	public void setFinalInvest(String[] finalInvest) {
		this.finalInvest = finalInvest;
	}
	public String getRespRate() {
		return respRate;
	}
	public void setRespRate(String respRate) {
		this.respRate = respRate;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	public String getAsriDrugCode() {
		return asriDrugCode;
	}
	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
	}
	public String getMedcoRemarks() {
		return medcoRemarks;
	}
	public void setMedcoRemarks(String medcoRemarks) {
		this.medcoRemarks = medcoRemarks;
	}
	public String getNamRemarks() {
		return namRemarks;
	}
	public void setNamRemarks(String namRemarks) {
		this.namRemarks = namRemarks;
	}
	public String getExeDisphotoChklst() {
		return exeDisphotoChklst;
	}
	public void setExeDisphotoChklst(String exeDisphotoChklst) {
		this.exeDisphotoChklst = exeDisphotoChklst;
	}
	public String getExeDisphotoremark() {
		return exeDisphotoremark;
	}
	public void setExeDisphotoremark(String exeDisphotoremark) {
		this.exeDisphotoremark = exeDisphotoremark;
	}
	public String getExePatphotoChklst() {
		return exePatphotoChklst;
	}
	public void setExePatphotoChklst(String exePatphotoChklst) {
		this.exePatphotoChklst = exePatphotoChklst;
	}
	public String getExePatphotoRemark() {
		return exePatphotoRemark;
	}
	public void setExePatphotoRemark(String exePatphotoRemark) {
		this.exePatphotoRemark = exePatphotoRemark;
	}
	public String getExeAcqvrifyChklst() {
		return exeAcqvrifyChklst;
	}
	public void setExeAcqvrifyChklst(String exeAcqvrifyChklst) {
		this.exeAcqvrifyChklst = exeAcqvrifyChklst;
	}
	public String getExeAcqverifyRemark() {
		return exeAcqverifyRemark;
	}
	public void setExeAcqverifyRemark(String exeAcqverifyRemark) {
		this.exeAcqverifyRemark = exeAcqverifyRemark;
	}
	public String getExeMedverifyChklst() {
		return exeMedverifyChklst;
	}
	public void setExeMedverifyChklst(String exeMedverifyChklst) {
		this.exeMedverifyChklst = exeMedverifyChklst;
	}
	public String getExeMedVerifyRemark() {
		return exeMedVerifyRemark;
	}
	public void setExeMedVerifyRemark(String exeMedVerifyRemark) {
		this.exeMedVerifyRemark = exeMedVerifyRemark;
	}
	public String getExeQuantverifyRemark() {
		return exeQuantverifyRemark;
	}
	public void setExeQuantverifyRemark(String exeQuantverifyRemark) {
		this.exeQuantverifyRemark = exeQuantverifyRemark;
	}
	public String getExeQuantverifyChklst() {
		return exeQuantverifyChklst;
	}
	public void setExeQuantverifyChklst(String exeQuantverifyChklst) {
		this.exeQuantverifyChklst = exeQuantverifyChklst;
	}
	public String getExereprtcheckChklst() {
		return exereprtcheckChklst;
	}
	public void setExereprtcheckChklst(String exereprtcheckChklst) {
		this.exereprtcheckChklst = exereprtcheckChklst;
	}
	public String getExeReprtcheckRemark() {
		return exeReprtcheckRemark;
	}
	public void setExeReprtcheckRemark(String exeReprtcheckRemark) {
		this.exeReprtcheckRemark = exeReprtcheckRemark;
	}
	public String getFtdRemarkvrifedChklst() {
		return ftdRemarkvrifedChklst;
	}
	public void setFtdRemarkvrifedChklst(String ftdRemarkvrifedChklst) {
		this.ftdRemarkvrifedChklst = ftdRemarkvrifedChklst;
	}
	public String getFtdRemarkvrifedRemark() {
		return ftdRemarkvrifedRemark;
	}
	public void setFtdRemarkvrifedRemark(String ftdRemarkvrifedRemark) {
		this.ftdRemarkvrifedRemark = ftdRemarkvrifedRemark;
	}
	public String getFtdBeneficiryChklst() {
		return ftdBeneficiryChklst;
	}
	public void setFtdBeneficiryChklst(String ftdBeneficiryChklst) {
		this.ftdBeneficiryChklst = ftdBeneficiryChklst;
	}
	public String getFtdBeneficiryRemark() {
		return ftdBeneficiryRemark;
	}
	public void setFtdBeneficiryRemark(String ftdBeneficiryRemark) {
		this.ftdBeneficiryRemark = ftdBeneficiryRemark;
	}
	public String getFtdRmks() {
		return ftdRmks;
	}
	public void setFtdRmks(String ftdRmks) {
		this.ftdRmks = ftdRmks;
	}
	public String getShowNam() {
		return showNam;
	}
	public void setShowNam(String showNam) {
		this.showNam = showNam;
	}
	public String getShowFcx() {
		return showFcx;
	}
	public void setShowFcx(String showFcx) {
		this.showFcx = showFcx;
	}
	public String getShowFtd() {
		return showFtd;
	}
	public void setShowFtd(String showFtd) {
		this.showFtd = showFtd;
	}
	public String getFollowUpId() {
		return followUpId;
	}
	public void setFollowUpId(String followUpId) {
		this.followUpId = followUpId;
	}
	public String getFollowUpDt() {
		return followUpDt;
	}
	public void setFollowUpDt(String followUpDt) {
		this.followUpDt = followUpDt;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
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
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
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
	public List<CasesSearchVO> getLstCaseSearch() {
		return lstCaseSearch;
	}
	public void setLstCaseSearch(List<CasesSearchVO> lstCaseSearch) {
		this.lstCaseSearch = lstCaseSearch;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
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
	public String getShowPage() {
		return showPage;
	}
	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}
	public String getPackageAmt() {
		return packageAmt;
	}
	public void setPackageAmt(String packageAmt) {
		this.packageAmt = packageAmt;
	}
	public List<FollowUpVO> getFollowUPLst() {
		return followUPLst;
	}
	public void setFollowUPLst(List<FollowUpVO> followUPLst) {
		this.followUPLst = followUPLst;
	}
	public List<LabelBean> getButtonList() {
		return buttonList;
	}
	public void setButtonList(List<LabelBean> buttonList) {
		this.buttonList = buttonList;
	}
	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}
	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}
	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getMedicatPeriod() {
		return medicatPeriod;
	}
	public void setMedicatPeriod(String medicatPeriod) {
		this.medicatPeriod = medicatPeriod;
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
	public String getShowAco() {
		return showAco;
	}
	public void setShowAco(String showAco) {
		this.showAco = showAco;
	}
	public List<LabelBean> getCasesForPaymentList() {
		return casesForPaymentList;
	}
	public void setCasesForPaymentList(
			List<LabelBean> casesForPaymentList) {
		this.casesForPaymentList = casesForPaymentList;
	}
	public String getGenFlag() {
		return genFlag;
	}
	public void setGenFlag(String genFlag) {
		this.genFlag = genFlag;
	}
	public String getFollowUpStatus() {
		return followUpStatus;
	}
	public void setFollowUpStatus(String followUpStatus) {
		this.followUpStatus = followUpStatus;
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
	public String getGenInvestigations() {
		return genInvestigations;
	}
	public void setGenInvestigations(String genInvestigations) {
		this.genInvestigations = genInvestigations;
	}
	public String getGenBlockInvestName() {
		return genBlockInvestName;
	}
	public void setGenBlockInvestName(String genBlockInvestName) {
		this.genBlockInvestName = genBlockInvestName;
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
	public PatientVO getPatient() {
		return patient;
	}
	public void setPatient(PatientVO patient) {
		this.patient = patient;
	}
	public List<FollowUpVO> getFollowUpData() {
		return FollowUpData;
	}
	public void setFollowUpData(List<FollowUpVO> followUpData) {
		FollowUpData = followUpData;
	}
	public List<DrugsVO> getDrugsList() {
		return drugsList;
	}
	public void setDrugsList(List<DrugsVO> drugsList) {
		this.drugsList = drugsList;
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
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getCsSurgDt() {
		return csSurgDt;
	}
	public void setCsSurgDt(String csSurgDt) {
		this.csSurgDt = csSurgDt;
	}
	public String getCsDisDt() {
		return csDisDt;
	}
	public void setCsDisDt(String csDisDt) {
		this.csDisDt = csDisDt;
	}
	public String getCsAdmDt() {
		return csAdmDt;
	}
	public void setCsAdmDt(String csAdmDt) {
		this.csAdmDt = csAdmDt;
	}
	public String getNxtFollowUpDt() {
		return nxtFollowUpDt;
	}
	public void setNxtFollowUpDt(String nxtFollowUpDt) {
		this.nxtFollowUpDt = nxtFollowUpDt;
	}
	public Date getNxtFollowUpMin() {
		return nxtFollowUpMin;
	}
	public void setNxtFollowUpMin(Date nxtFollowUpMin) {
		this.nxtFollowUpMin = nxtFollowUpMin;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getDrugExpiryDt() {
		return drugExpiryDt;
	}
	public void setDrugExpiryDt(String drugExpiryDt) {
		this.drugExpiryDt = drugExpiryDt;
	}
	public List<com.ahct.attachments.vo.AttachmentVO> getLstCompDocs() {
		return lstCompDocs;
	}
	public void setLstCompDocs(
			List<com.ahct.attachments.vo.AttachmentVO> lstCompDocs) {
		this.lstCompDocs = lstCompDocs;
	}
	public List<com.ahct.attachments.vo.AttachmentVO> getLstCompPhotos() {
		return lstCompPhotos;
	}
	public void setLstCompPhotos(
			List<com.ahct.attachments.vo.AttachmentVO> lstCompPhotos) {
		this.lstCompPhotos = lstCompPhotos;
	}
	public List<com.ahct.attachments.vo.AttachmentVO> getLstCompDTRS() {
		return lstCompDTRS;
	}
	public void setLstCompDTRS(
			List<com.ahct.attachments.vo.AttachmentVO> lstCompDTRS) {
		this.lstCompDTRS = lstCompDTRS;
	}
	public String getClaimFcxPharmBill() {
		return claimFcxPharmBill;
	}
	public void setClaimFcxPharmBill(String claimFcxPharmBill) {
		this.claimFcxPharmBill = claimFcxPharmBill;
	}
	public String getClaimFcxConsulBill() {
		return claimFcxConsulBill;
	}
	public void setClaimFcxConsulBill(String claimFcxConsulBill) {
		this.claimFcxConsulBill = claimFcxConsulBill;
	}
	public String getClaimFcxInvestBill() {
		return claimFcxInvestBill;
	}
	public void setClaimFcxInvestBill(String claimFcxInvestBill) {
		this.claimFcxInvestBill = claimFcxInvestBill;
	}

	public String getClaimFTDPharmBill() {
		return claimFTDPharmBill;
	}
	public void setClaimFTDPharmBill(String claimFTDPharmBill) {
		this.claimFTDPharmBill = claimFTDPharmBill;
	}
	public String getClaimFTDConsulBill() {
		return claimFTDConsulBill;
	}
	public void setClaimFTDConsulBill(String claimFTDConsulBill) {
		this.claimFTDConsulBill = claimFTDConsulBill;
	}
	public String getClaimFTDInvestBill() {
		return claimFTDInvestBill;
	}
	public void setClaimFTDInvestBill(String claimFTDInvestBill) {
		this.claimFTDInvestBill = claimFTDInvestBill;
	}
	public String getClaimFTDTotBill() {
		return claimFTDTotBill;
	}
	public void setClaimFTDTotBill(String claimFTDTotBill) {
		this.claimFTDTotBill = claimFTDTotBill;
	}
	public String getClaimCHPharmBill() {
		return claimCHPharmBill;
	}
	public void setClaimCHPharmBill(String claimCHPharmBill) {
		this.claimCHPharmBill = claimCHPharmBill;
	}
	public String getClaimCHConsulBill() {
		return claimCHConsulBill;
	}
	public void setClaimCHConsulBill(String claimCHConsulBill) {
		this.claimCHConsulBill = claimCHConsulBill;
	}
	public String getClaimCHInvestBill() {
		return claimCHInvestBill;
	}
	public void setClaimCHInvestBill(String claimCHInvestBill) {
		this.claimCHInvestBill = claimCHInvestBill;
	}
	public String getNewPckAmt() {
		return newPckAmt;
	}
	public void setNewPckAmt(String newPckAmt) {
		this.newPckAmt = newPckAmt;
	}
	public String getNewFwdAmt() {
		return newFwdAmt;
	}
	public void setNewFwdAmt(String newFwdAmt) {
		this.newFwdAmt = newFwdAmt;
	}
	public String getNewtotAmt() {
		return newtotAmt;
	}
	public void setNewtotAmt(String newtotAmt) {
		this.newtotAmt = newtotAmt;
	}
	public String getNewClmFwdAmt() {
		return newClmFwdAmt;
	}
	public void setNewClmFwdAmt(String newClmFwdAmt) {
		this.newClmFwdAmt = newClmFwdAmt;
	}
	public String getNewFTDPckAmt() {
		return newFTDPckAmt;
	}
	public void setNewFTDPckAmt(String newFTDPckAmt) {
		this.newFTDPckAmt = newFTDPckAmt;
	}
	public String getNewFTDFwdAmt() {
		return newFTDFwdAmt;
	}
	public void setNewFTDFwdAmt(String newFTDFwdAmt) {
		this.newFTDFwdAmt = newFTDFwdAmt;
	}
	public String getNewFTDtotAmt() {
		return newFTDtotAmt;
	}
	public void setNewFTDtotAmt(String newFTDtotAmt) {
		this.newFTDtotAmt = newFTDtotAmt;
	}
	public String getNewCHPckAmt() {
		return newCHPckAmt;
	}
	public void setNewCHPckAmt(String newCHPckAmt) {
		this.newCHPckAmt = newCHPckAmt;
	}
	public String getNewCHFwdAmt() {
		return newCHFwdAmt;
	}
	public void setNewCHFwdAmt(String newCHFwdAmt) {
		this.newCHFwdAmt = newCHFwdAmt;
	}
	public String getNewCHtotAmt() {
		return newCHtotAmt;
	}
	public void setNewCHtotAmt(String newCHtotAmt) {
		this.newCHtotAmt = newCHtotAmt;
	}
	public String getPostOP() {
		return postOP;
	}
	public void setPostOP(String postOP) {
		this.postOP = postOP;
	}
	public String getPostOPRemarks() {
		return postOPRemarks;
	}
	public void setPostOPRemarks(String postOPRemarks) {
		this.postOPRemarks = postOPRemarks;
	}
	public String getWoundSight() {
		return woundSight;
	}
	public void setWoundSight(String woundSight) {
		this.woundSight = woundSight;
	}
	public String getSwitchOnDate() {
		return switchOnDate;
	}
	public void setSwitchOnDate(String switchOnDate) {
		this.switchOnDate = switchOnDate;
	}
	public String getAudiologist() {
		return audiologist;
	}
	public void setAudiologist(String audiologist) {
		this.audiologist = audiologist;
	}
	public String getChildProgressRemarks() {
		return childProgressRemarks;
	}
	public void setChildProgressRemarks(String childProgressRemarks) {
		this.childProgressRemarks = childProgressRemarks;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getAudiologistName() {
		return audiologistName;
	}
	public void setAudiologistName(String audiologistName) {
		this.audiologistName = audiologistName;
	}
	public List<FollowUpVO> getFollowUpCochList() {
		return followUpCochList;
	}
	public void setFollowUpCochList(List<FollowUpVO> followUpCochList) {
		this.followUpCochList = followUpCochList;
	}
	public String getFirstButtonsDiv() {
		return firstButtonsDiv;
	}
	public void setFirstButtonsDiv(String firstButtonsDiv) {
		this.firstButtonsDiv = firstButtonsDiv;
	}
	public List<FollowUpVO> getFollowUpCochClaimList() {
		return followUpCochClaimList;
	}
	public void setFollowUpCochClaimList(List<FollowUpVO> followUpCochClaimList) {
		this.followUpCochClaimList = followUpCochClaimList;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public List<FollowUpVO> getPrevFlpDtls() {
		return prevFlpDtls;
	}
	public void setPrevFlpDtls(List<FollowUpVO> prevFlpDtls) {
		this.prevFlpDtls = prevFlpDtls;
	}
	public String getFollowUpIdHidden() {
		return followUpIdHidden;
	}
	public void setFollowUpIdHidden(String followUpIdHidden) {
		this.followUpIdHidden = followUpIdHidden;
	}
	public String getClaimAmt() {
		return claimAmt;
	}
	public String getCaseIdHidden() {
		return caseIdHidden;
	}
	public void setCaseIdHidden(String caseIdHidden) {
		this.caseIdHidden = caseIdHidden;
	}
	public String getLstrUsrGrp() {
		return lstrUsrGrp;
	}
	public void setLstrUsrGrp(String lstrUsrGrp) {
		this.lstrUsrGrp = lstrUsrGrp;
	}
	public String getSubmitButton() {
		return submitButton;
	}
	public void setSubmitButton(String submitButton) {
		this.submitButton = submitButton;
	}
	public String getClaimCocCmtAmt() {
		return claimCocCmtAmt;
	}
	public void setClaimCocCmtAmt(String claimCocCmtAmt) {
		this.claimCocCmtAmt = claimCocCmtAmt;
	}
	public String getClaimDyEoAmt() {
		return claimDyEoAmt;
	}
	public void setClaimDyEoAmt(String claimDyEoAmt) {
		this.claimDyEoAmt = claimDyEoAmt;
	}
	public String getCoccmtRemark() {
		return coccmtRemark;
	}
	public void setCoccmtRemark(String coccmtRemark) {
		this.coccmtRemark = coccmtRemark;
	}
	public String getDyeoRemark() {
		return dyeoRemark;
	}
	public void setDyeoRemark(String dyeoRemark) {
		this.dyeoRemark = dyeoRemark;
	}
	public String getSearchFollowUpStatus() {
		return searchFollowUpStatus;
	}
	public void setSearchFollowUpStatus(String searchFollowUpStatus) {
		this.searchFollowUpStatus = searchFollowUpStatus;
	}
	public String getSearchCaseNo() {
		return searchCaseNo;
	}
	public void setSearchCaseNo(String searchCaseNo) {
		this.searchCaseNo = searchCaseNo;
	}
	public String getSearchFollowUpId() {
		return searchFollowUpId;
	}
	public void setSearchFollowUpId(String searchFollowUpId) {
		this.searchFollowUpId = searchFollowUpId;
	}
	public String getSearchPatName() {
		return searchPatName;
	}
	public void setSearchPatName(String searchPatName) {
		this.searchPatName = searchPatName;
	}
	public String getSearchFromDate() {
		return searchFromDate;
	}
	public void setSearchFromDate(String searchFromDate) {
		this.searchFromDate = searchFromDate;
	}
	public String getSearchToDate() {
		return searchToDate;
	}
	public void setSearchToDate(String searchToDate) {
		this.searchToDate = searchToDate;
	}
	public String getSearchCardNo() {
		return searchCardNo;
	}
	public void setSearchCardNo(String searchCardNo) {
		this.searchCardNo = searchCardNo;
	}
	public String getSearchSchemeType() {
		return searchSchemeType;
	}
	public void setSearchSchemeType(String searchSchemeType) {
		this.searchSchemeType = searchSchemeType;
	}
	public List<FollowUpVO> getAuditLst() {
		return auditLst;
	}
	public void setAuditLst(List<FollowUpVO> auditLst) {
		this.auditLst = auditLst;
	}
	public String getSendBackRmrks() {
		return sendBackRmrks;
	}
	public void setSendBackRmrks(String sendBackRmrks) {
		this.sendBackRmrks = sendBackRmrks;
	}
	public String getPaymentRadio() {
		return paymentRadio;
	}
	public void setPaymentRadio(String paymentRadio) {
		this.paymentRadio = paymentRadio;
	}
	public String getCsvFollowUpStatus() {
		return csvFollowUpStatus;
	}
	public void setCsvFollowUpStatus(String csvFollowUpStatus) {
		this.csvFollowUpStatus = csvFollowUpStatus;
	}
	public String getCsvCaseNo() {
		return csvCaseNo;
	}
	public void setCsvCaseNo(String csvCaseNo) {
		this.csvCaseNo = csvCaseNo;
	}
	public String getCsvFollowUpId() {
		return csvFollowUpId;
	}
	public void setCsvFollowUpId(String csvFollowUpId) {
		this.csvFollowUpId = csvFollowUpId;
	}
	public String getCsvPatName() {
		return csvPatName;
	}
	public void setCsvPatName(String csvPatName) {
		this.csvPatName = csvPatName;
	}
	public String getCsvFromDate() {
		return csvFromDate;
	}
	public void setCsvFromDate(String csvFromDate) {
		this.csvFromDate = csvFromDate;
	}
	public String getCsvToDate() {
		return csvToDate;
	}
	public void setCsvToDate(String csvToDate) {
		this.csvToDate = csvToDate;
	}
	public String getCsvCardNo() {
		return csvCardNo;
	}
	public void setCsvCardNo(String csvCardNo) {
		this.csvCardNo = csvCardNo;
	}
	public String getCsvSchemeType() {
		return csvSchemeType;
	}
	public void setCsvSchemeType(String csvSchemeType) {
		this.csvSchemeType = csvSchemeType;
	}

	

}
