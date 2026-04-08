package com.ahct.followup.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.AttachmentVO;
import com.ahct.preauth.vo.DrugsVO;



public class FollowUpVO implements java.io.Serializable {

	private String wapNo;
	private Number COUNT = 0;
	private String CLINICALID;
	private String TEMPERATURE;
	private String PULSE;
	private String HEART_RATE;
	private String LUNGS;
	private String RESPIRATORY;
	private String CASEID;
	private String BLOODPRESSURE;
	private String FLUIDINPT;
	private String FLUIDOUTPUT;
	private String flag;
	private String HEMOGLOBIN;
	private String INVESTIGATIONS;
	private String DRUGCODE;
	private String ASRIDRUGCODE;
	private String ROUTE;
	private String STRENGTH;
	private String DOSAGE;
	private String MEDICATION;
	private String WARDTYPE;
	private String SYST_DIAS;
	private String CRTUSR;
    private String followUpSeq;
    private String cochFollowUpId;
	
	private String claimAmt;
	private Number claimAmtNum;
	private String claimNwhAmt;
	private String followUpClaimSubDt;
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
	private String followUpId;
	private String followUpDt;
	private String caseType;
	private String followUpRemark;
	private String msg;
	private String roleId;
	private String userId;
	private String actionDone;
	private String followUpStatus;

	private String patName;
	private String caseNo;
	private String fromDate;
	private String toDate;
	private String searchFlag;
	private String startIndex;
	private String rowsPerPage;
	private String totRowCount;
	private List<CasesSearchVO> lstCaseSearch = null;
	private String showPage;
	private String userRole;
	private String caseId;
	private String followUpSubDt;
	private String packAmt;

	private String claimNamAmt;
	private String claimFcxAmt;
	private String claimFtdAmt;
	private String claimChAmt;
	private String fcxRemark;
	private String chRemark;
	private String showCh;
	private String status;
	private List<DrugsVO> drugs;
	private List<AttachmentVO> genAttachmentsList=null;

    private Long totalPackAmt;
    private Long fistInstAmt;
    private Long nextInstAmt;
	
    private String actualAmt;
    private String claimFwd;
    private String claimFwdOld;
    private String balAvail;
    private String casesForApprovalFlag;
    private String acoAprAmt;
	 private String acoRemark;
    private String ceoRemark;
    private String smsMsg;
    private String patientId;
    private String viewFlag;
    private String schemeType;
    private String cardNo;
    private String enrollParntId;
    private String enrollPhoto;
    private String csAdmDt;
    private String csSurgDt;
    private Date CSDISDT;
 	private String nxtFollowUpDt;
    private String OnloadCaseOpen;
 	private String unlockCase;
 	private String claimFcxPharmBill;
 	private String claimFcxConsulBill;
 	private String claimFcxInvestBill;
 	private String claimFcxTotBill;
 	private String claimFTDPharmBill;
 	private String claimFTDConsulBill;
 	private String claimFTDInvestBill;
 	private String claimFTDTotBill;
 	private String claimCHPharmBill;
 	private String claimCHConsulBill;
 	private String claimCHInvestBill;
 	private double claimFwdAmt;
 	private String newClmFwdAmt;
 	private String patientScheme;

 	private String asriCatCode;
 	private String remarks;
 	
 	
 	/*
 	 * Added By Srikalyan for Cochlear FollowUp
 	 */
 	private String id;
 	private String value;
 	private String pendingFlag;
 	private String postOP;
 	private String postOPRemarks;
 	private String woundSight;
 	private String switchOnDate;
 	private String audiologist;
 	private String childProgressRemarks;
 	private String reviewDate;
 	private String audiologistName;
 	private int cochlearCount;
 	private String cochlearCountStr;
 	private String fromDatePrev[];
 	private String toDatePrev[];
 	private String childProgressRemarksPrev[];
 	private String reviewDatePrev[];
 	private String audiologistNamePrev[];
 	private String prevFollowUp;
 	private int toStartFUS; 
 	private String initialMapping;
 	private int currentNum;
 	private Date crtDate;
 	private int strtIndex;
 	private int maxresults;
 	private List<FollowUpVO> claimList;
 	private int pageId;
 	private int totalPages;
 	private int totalRecords;
 	private String hospName; 
 	private String catName;
 	private String claimNo;
 	private String district;
 	private String mandal;
 	private String village;
 	private Long contactNo;
 	private long actualPack;
 	private double claimAmnt;
 	private double claimNWHAmnt;
 	private String claimNWHAmtStr;
 	private String followUptype;
 	private String csDischDt;
 	private String hospCity;
 	private String age;
 	private String gender;
 	private String statusId;
 	private String userGrp;
 	private String submitButton;
 	private long actOrder;
 	private Double claimCocCmtAmt;
 	private String claimCocCmtAmtStr;
	private Double claimDyEoAmt;
	private String claimDyEoAmtStr;
	private String coccmtRemark;
	private String dyeoRemark;
	private String patientIpop;
	private String pendingBy;
	private String name;
	private String auditDate;
 	private String count;
 	private String sendSms;
 	private String userScheme;
 	private String returnMsg;
 	private String dateStr;
 	private String procCode;
 	private BigDecimal packageAmt;
 	private String sendBackRmrks;
 	private String cochlearYn;
 	private String followUpType;
 	private String cochelarSearch;
 	private String csvFlag;
 	
	 public String getNewClmFwdAmt() {
		return newClmFwdAmt;
	}
	public void setNewClmFwdAmt(String newClmFwdAmt) {
		this.newClmFwdAmt = newClmFwdAmt;
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
	public String getClaimFcxTotBill() {
		return claimFcxTotBill;
	}
	public void setClaimFcxTotBill(String claimFcxTotBill) {
		this.claimFcxTotBill = claimFcxTotBill;
	}
	public String getUnlockCase() {
		return unlockCase;
	}
	public void setUnlockCase(String unlockCase) {
		this.unlockCase = unlockCase;
	}
	public String getOnloadCaseOpen() {
		return OnloadCaseOpen;
	}
	public void setOnloadCaseOpen(String onloadCaseOpen) {
		OnloadCaseOpen = onloadCaseOpen;
	}
	public String getNxtFollowUpDt() {
		return nxtFollowUpDt;
	}
	public void setNxtFollowUpDt(String nxtFollowUpDt) {
		this.nxtFollowUpDt = nxtFollowUpDt;
	}
	public String getWapNo() {
		return wapNo;
	}
	public void setWapNo(String wapNo) {
		this.wapNo = wapNo;
	}
	public String getCasesForApprovalFlag() {
	 	return casesForApprovalFlag;
	 }
	 public void setCasesForApprovalFlag(String casesForApprovalFlag) {
	 	this.casesForApprovalFlag = casesForApprovalFlag;
	 }
			
    private List<LabelBean> grpList;
    public String getActualAmt() {
		return actualAmt;
	}

	public void setActualAmt(String actualAmt) {
		this.actualAmt = actualAmt;
	}

	public String getClaimFwd() {
		return claimFwd;
	}

	public void setClaimFwd(String claimFwd) {
		this.claimFwd = claimFwd;
	}

	public String getBalAvail() {
		return balAvail;
	}

	public void setBalAvail(String balAvail) {
		this.balAvail = balAvail;
	}

	public Long getTotalPackAmt() {
		return totalPackAmt;
	}

	public void setTotalPackAmt(Long totalPackAmt) {
		this.totalPackAmt = totalPackAmt;
	}

	public Long getFistInstAmt() {
		return fistInstAmt;
	}

	public void setFistInstAmt(Long fistInstAmt) {
		this.fistInstAmt = fistInstAmt;
	}

	public Long getNextInstAmt() {
		return nextInstAmt;
	}

	public void setNextInstAmt(Long nextInstAmt) {
		this.nextInstAmt = nextInstAmt;
	}

	public List<AttachmentVO> getGenAttachmentsList() {
		return genAttachmentsList;
	}

	public void setGenAttachmentsList(List<AttachmentVO> genAttachmentsList) {
		this.genAttachmentsList = genAttachmentsList;
	}

	public List<DrugsVO> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<DrugsVO> drugs) {
		this.drugs = drugs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public void setFollowUpSubDtl(Date followUpSubDt) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss a");
		
		if(followUpSubDt != null )
			try {
				this.followUpSubDt =  sdf.format(followUpSubDt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public String getFollowUpClaimSubDt() {
		return followUpClaimSubDt;
	}

	public void setFollowUpClaimSubDt(String followUpClaimSubDt) {
		this.followUpClaimSubDt = followUpClaimSubDt;
	}

	public String getHEMOGLOBIN() {
		return HEMOGLOBIN;
	}

	public void setHEMOGLOBIN(String hEMOGLOBIN) {
		HEMOGLOBIN = hEMOGLOBIN;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCLINICALID() {
		return CLINICALID;
	}

	public void setCLINICALID(String cLINICALID) {
		CLINICALID = cLINICALID;
	}

	public String getTEMPERATURE() {
		return TEMPERATURE;
	}

	public void setTEMPERATURE(String tEMPERATURE) {
		TEMPERATURE = tEMPERATURE;
	}

	public String getPULSE() {
		return PULSE;
	}

	public void setPULSE(String pULSE) {
		PULSE = pULSE;
	}

	public String getHEART_RATE() {
		return HEART_RATE;
	}

	public void setHEART_RATE(String hEART_RATE) {
		HEART_RATE = hEART_RATE;
	}

	public String getLUNGS() {
		return LUNGS;
	}

	public void setLUNGS(String lUNGS) {
		LUNGS = lUNGS;
	}

	public String getRESPIRATORY() {
		return RESPIRATORY;
	}

	public void setRESPIRATORY(String rESPIRATORY) {
		RESPIRATORY = rESPIRATORY;
	}

	public String getCASEID() {
		return CASEID;
	}

	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}

	public String getBLOODPRESSURE() {
		return BLOODPRESSURE;
	}

	public void setBLOODPRESSURE(String bLOODPRESSURE) {
		BLOODPRESSURE = bLOODPRESSURE;
	}

	public String getFLUIDINPT() {
		return FLUIDINPT;
	}

	public void setFLUIDINPT(String fLUIDINPT) {
		FLUIDINPT = fLUIDINPT;
	}

	public String getFLUIDOUTPUT() {
		return FLUIDOUTPUT;
	}

	public void setFLUIDOUTPUT(String fLUIDOUTPUT) {
		FLUIDOUTPUT = fLUIDOUTPUT;
	}

	public String getINVESTIGATIONS() {
		return INVESTIGATIONS;
	}

	public void setINVESTIGATIONS(String iNVESTIGATIONS) {
		INVESTIGATIONS = iNVESTIGATIONS;
	}

	public String getDRUGCODE() {
		return DRUGCODE;
	}

	public void setDRUGCODE(String dRUGCODE) {
		DRUGCODE = dRUGCODE;
	}

	public String getASRIDRUGCODE() {
		return ASRIDRUGCODE;
	}

	public void setASRIDRUGCODE(String aSRIDRUGCODE) {
		ASRIDRUGCODE = aSRIDRUGCODE;
	}

	public String getROUTE() {
		return ROUTE;
	}

	public void setROUTE(String rOUTE) {
		ROUTE = rOUTE;
	}

	public String getSTRENGTH() {
		return STRENGTH;
	}

	public void setSTRENGTH(String sTRENGTH) {
		STRENGTH = sTRENGTH;
	}

	public String getDOSAGE() {
		return DOSAGE;
	}

	public void setDOSAGE(String dOSAGE) {
		DOSAGE = dOSAGE;
	}

	public String getMEDICATION() {
		return MEDICATION;
	}

	public void setMEDICATION(String mEDICATION) {
		MEDICATION = mEDICATION;
	}

	public String getClaimAmt() {
		return claimAmt;
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

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getFollowUpRemark() {
		return followUpRemark;
	}

	public void setFollowUpRemark(String followUpRemark) {
		this.followUpRemark = followUpRemark;
	}

	public Number getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActionDone() {
		return actionDone;
	}

	public void setActionDone(String actionDone) {
		this.actionDone = actionDone;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
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

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getTotRowCount() {
		return totRowCount;
	}

	public void setTotRowCount(String totRowCount) {
		this.totRowCount = totRowCount;
	}

	public List<CasesSearchVO> getLstCaseSearch() {
		return lstCaseSearch;
	}

	public void setLstCaseSearch(List<CasesSearchVO> lstCaseSearch) {
		this.lstCaseSearch = lstCaseSearch;
	}

	public String getShowPage() {
		return showPage;
	}

	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getFollowUpStatus() {
		return followUpStatus;
	}

	public void setFollowUpStatus(String followUpStatus) {
		this.followUpStatus = followUpStatus;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getFollowUpSubDt() {
		return followUpSubDt;
	}

	public void setFollowUpSubDt(Date followUpSubDt) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss a");

		if (followUpSubDt != null)
			try {
				this.followUpSubDt = sdf.format(followUpSubDt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public String getPackAmt() {
		return packAmt;
	}

	public void setPackAmt(String packAmt) {
		this.packAmt = packAmt;
	}

	public String getClaimNwhAmt() {
		return claimNwhAmt;
	}

	public void setClaimNwhAmt(String claimNwhAmt) {
		this.claimNwhAmt = claimNwhAmt;
	}

	public List<LabelBean> getGrpList() {
		return grpList;
	}

	public void setGrpList(List<LabelBean> grpList) {
		this.grpList = grpList;
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
	public String getCeoRemark() {
		return ceoRemark;
	}
	public void setCeoRemark(String ceoRemark) {
		this.ceoRemark = ceoRemark;
	}
	public String getSmsMsg() {
		return smsMsg;
	}
	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	public String getWARDTYPE() {
		return WARDTYPE;
	}
	public void setWARDTYPE(String wARDTYPE) {
		WARDTYPE = wARDTYPE;
	}
	public String getSYST_DIAS() {
		return SYST_DIAS;
	}
	public void setSYST_DIAS(String sYST_DIAS) {
		SYST_DIAS = sYST_DIAS;
	}
	public String getCRTUSR() {
		return CRTUSR;
	}
	public void setCRTUSR(String cRTUSR) {
		CRTUSR = cRTUSR;
	}
	public String getFollowUpSeq() {
		return followUpSeq;
	}
	public void setFollowUpSeq(String followUpSeq) {
		this.followUpSeq = followUpSeq;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getClaimFwdOld() {
		return claimFwdOld;
	}
	public void setClaimFwdOld(String claimFwdOld) {
		this.claimFwdOld = claimFwdOld;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getEnrollParntId() {
		return enrollParntId;
	}
	public void setEnrollParntId(String enrollParntId) {
		this.enrollParntId = enrollParntId;
	}
	public String getEnrollPhoto() {
		return enrollPhoto;
	}
	public void setEnrollPhoto(String enrollPhoto) {
		this.enrollPhoto = enrollPhoto;
	}
	public String getCsAdmDt() {
		return csAdmDt;
	}
	public void setCsAdmDt(String csAdmDt) {
		this.csAdmDt = csAdmDt;
	}
	public String getCsSurgDt() {
		return csSurgDt;
	}
	public void setCsSurgDt(String csSurgDt) {
		this.csSurgDt = csSurgDt;
	}
	public Date getCSDISDT() {
		return CSDISDT;
	}
	public void setCSDISDT(Date cSDISDT) {
		CSDISDT = cSDISDT;
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
	public double getClaimFwdAmt() {
		return claimFwdAmt;
	}
	public void setClaimFwdAmt(double claimFwdAmt) {
		this.claimFwdAmt = claimFwdAmt;
	}
	public String getPatientScheme() {
		return patientScheme;
	}
	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	public String getPendingFlag() {
		return pendingFlag;
	}
	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
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
	public int getCochlearCount() {
		return cochlearCount;
	}
	public void setCochlearCount(int cochlearCount) {
		this.cochlearCount = cochlearCount;
	}
	public String[] getFromDatePrev() {
		return fromDatePrev;
	}
	public void setFromDatePrev(String[] fromDatePrev) {
		this.fromDatePrev = fromDatePrev;
	}
	public String[] getToDatePrev() {
		return toDatePrev;
	}
	public void setToDatePrev(String[] toDatePrev) {
		this.toDatePrev = toDatePrev;
	}
	public String[] getChildProgressRemarksPrev() {
		return childProgressRemarksPrev;
	}
	public void setChildProgressRemarksPrev(String[] childProgressRemarksPrev) {
		this.childProgressRemarksPrev = childProgressRemarksPrev;
	}
	public String[] getReviewDatePrev() {
		return reviewDatePrev;
	}
	public void setReviewDatePrev(String[] reviewDatePrev) {
		this.reviewDatePrev = reviewDatePrev;
	}
	public String[] getAudiologistNamePrev() {
		return audiologistNamePrev;
	}
	public void setAudiologistNamePrev(String[] audiologistNamePrev) {
		this.audiologistNamePrev = audiologistNamePrev;
	}
	public String getPrevFollowUp() {
		return prevFollowUp;
	}
	public void setPrevFollowUp(String prevFollowUp) {
		this.prevFollowUp = prevFollowUp;
	}
	public int getToStartFUS() {
		return toStartFUS;
	}
	public void setToStartFUS(int toStartFUS) {
		this.toStartFUS = toStartFUS;
	}
	public String getInitialMapping() {
		return initialMapping;
	}
	public void setInitialMapping(String initialMapping) {
		this.initialMapping = initialMapping;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	
	public String getAsriCatCode() {
		return asriCatCode;
	}
	public void setAsriCatCode(String asriCatCode) {
		this.asriCatCode = asriCatCode;
	}
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	public int getStrtIndex() {
		return strtIndex;
	}
	public void setStrtIndex(int strtIndex) {
		this.strtIndex = strtIndex;
	}
	public int getMaxresults() {
		return maxresults;
	}
	public void setMaxresults(int maxresults) {
		this.maxresults = maxresults;
	}
	public List<FollowUpVO> getClaimList() {
		return claimList;
	}
	public void setClaimList(List<FollowUpVO> claimList) {
		this.claimList = claimList;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Number getClaimAmtNum() {
		return claimAmtNum;
	}
	public void setClaimAmtNum(Number claimAmtNum) {
		this.claimAmtNum = claimAmtNum;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public long getActualPack() {
		return actualPack;
	}
	public void setActualPack(long actualPack) {
		this.actualPack = actualPack;
	}
	public double getClaimAmnt() {
		return claimAmnt;
	}
	public void setClaimAmnt(double claimAmnt) {
		this.claimAmnt = claimAmnt;
	}
	public String getFollowUptype() {
		return followUptype;
	}
	public void setFollowUptype(String followUptype) {
		this.followUptype = followUptype;
	}
	public String getCsDischDt() {
		return csDischDt;
	}
	public void setCsDischDt(String csDischDt) {
		this.csDischDt = csDischDt;
	}
	public String getHospCity() {
		return hospCity;
	}
	public void setHospCity(String hospCity) {
		this.hospCity = hospCity;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getUserGrp() {
		return userGrp;
	}
	public void setUserGrp(String userGrp) {
		this.userGrp = userGrp;
	}
	public String getSubmitButton() {
		return submitButton;
	}
	public void setSubmitButton(String submitButton) {
		this.submitButton = submitButton;
	}
	public long getActOrder() {
		return actOrder;
	}
	public void setActOrder(long actOrder) {
		this.actOrder = actOrder;
	}
	public double getClaimNWHAmnt() {
		return claimNWHAmnt;
	}
	public void setClaimNWHAmnt(double claimNWHAmnt) {
		this.claimNWHAmnt = claimNWHAmnt;
	}
	public Double getClaimCocCmtAmt() {
		return claimCocCmtAmt;
	}
	public void setClaimCocCmtAmt(Double claimCocCmtAmt) {
		this.claimCocCmtAmt = claimCocCmtAmt;
	}
	public Double getClaimDyEoAmt() {
		return claimDyEoAmt;
	}
	public void setClaimDyEoAmt(Double claimDyEoAmt) {
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
	public String getPatientIpop() {
		return patientIpop;
	}
	public void setPatientIpop(String patientIpop) {
		this.patientIpop = patientIpop;
	}
	public String getPendingBy() {
		return pendingBy;
	}
	public void setPendingBy(String pendingBy) {
		this.pendingBy = pendingBy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getSendSms() {
		return sendSms;
	}
	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUserScheme() {
		return userScheme;
	}
	public void setUserScheme(String userScheme) {
		this.userScheme = userScheme;
	}
	public String getClaimNWHAmtStr() {
		return claimNWHAmtStr;
	}
	public void setClaimNWHAmtStr(String claimNWHAmtStr) {
		this.claimNWHAmtStr = claimNWHAmtStr;
	}
	public String getClaimCocCmtAmtStr() {
		return claimCocCmtAmtStr;
	}
	public void setClaimCocCmtAmtStr(String claimCocCmtAmtStr) {
		this.claimCocCmtAmtStr = claimCocCmtAmtStr;
	}
	public String getClaimDyEoAmtStr() {
		return claimDyEoAmtStr;
	}
	public void setClaimDyEoAmtStr(String claimDyEoAmtStr) {
		this.claimDyEoAmtStr = claimDyEoAmtStr;
	}
	public String getCochlearCountStr() {
		return cochlearCountStr;
	}
	public void setCochlearCountStr(String cochlearCountStr) {
		this.cochlearCountStr = cochlearCountStr;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getProcCode() {
		return procCode;
	}
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}
	public BigDecimal getPackageAmt() {
		return packageAmt;
	}
	public void setPackageAmt(BigDecimal packageAmt) {
		this.packageAmt = packageAmt;
	}
	public String getSendBackRmrks() {
		return sendBackRmrks;
	}
	public void setSendBackRmrks(String sendBackRmrks) {
		this.sendBackRmrks = sendBackRmrks;
	}
	public String getCochlearYn() {
		return cochlearYn;
	}
	public void setCochlearYn(String cochlearYn) {
		this.cochlearYn = cochlearYn;
	}
	public String getFollowUpType() {
		return followUpType;
	}
	public void setFollowUpType(String followUpType) {
		this.followUpType = followUpType;
	}
	public String getCochelarSearch() {
		return cochelarSearch;
	}
	public void setCochelarSearch(String cochelarSearch) {
		this.cochelarSearch = cochelarSearch;
	}
	public String getCsvFlag() {
		return csvFlag;
	}
	public void setCsvFlag(String csvFlag) {
		this.csvFlag = csvFlag;
	}
	public String getCochFollowUpId() {
		return cochFollowUpId;
	}
	public void setCochFollowUpId(String cochFollowUpId) {
		this.cochFollowUpId = cochFollowUpId;
	}

	
}
