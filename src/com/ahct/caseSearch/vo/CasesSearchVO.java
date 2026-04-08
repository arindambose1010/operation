package com.ahct.caseSearch.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;


public class CasesSearchVO implements java.io.Serializable{

private String caseNo;
private String claimNo;
private String oldCaseId;
private String patientName;
private String wapNo;
private String CREATEDBY;
private String FEEDBACKID;
private String feedback;
private String crt_usr;
private String enhanceflag;
private String REMARKS;
private Date CREATEDON; 
private String claimStatus;
private String claimSubmittedDate;
private String billSubmittedDate;
private String inpatientCaseSubDt;
private Long claimAmt;
private String catId;
private String errStatusId;
private String errStatus;
private String surgyId;
private String patName;
private String distId;
private String viewFlag;
private Date lstUpdDt;
private String colorFlag;
private String colorCode;
private String colorCodeView;
private List<LabelBean> grpList;
private String casesForApprovalFlag;
private String roleId;
private String patIpOp;
private String drugTypeName;
private String drugSubTypeName;
private String errCaseApprovalFlag;
private String errDentalCaseApprovalFlag;
private String pendingFlag;
private String caseBlockedUsr;
private String lstUpdUsrNew;
private String caseForDissFlg;
private Long   issueId;
private String issuecaseId=null;
private String hospId=null;
private String issuestatus=null;
private String issuetitle=null;
private Date createddate=null;
private String issueResultFlag=null;
private String issueResultFlagSize=null;
private String grievanceFlag;
private String telephonicId;
private Long preauthAppAmt;
private String excelFlag;
private String csPreauthDt;
private String preauthAprvDate;
private String csDisDt;
private String csDeathDt;
private String lstUpdUsr;
private String clmSubDt;
private String csTransId;
private String csTransDt;
private String csSbhDt;
private String csRemarks;
private int start_index;
private int end_index;
private String flagged;
private String speciality;
private String process;
private String procCode;
private String csSurgDt;
private String followUpId;
private String followUpStatus;
private Date followUpSubDt;
private String claimAmt1;
private String activeyn;
private String patientScheme;
private String ceoFlag;
private String procedure;
private String preauthPckgAmt;
private String enhFlag;
private long enhReqAmt;
private String dentalFlag;
private String CATID;
private String PATIPOP;
private String ISSUESTATUS;
private String CASENO;
private String PATIENTID;
private String PATNAME;
private String HOSPNAME;
private String CASESTATUS;
private String CASESTATUSID;
private Number CLAIMAMT;
private Number PREAUTHAPPAMT;
private String PROC;
private String tdsPctAmt;
private String trustPctAmt;
private String tdsHospPctAmt;
private String hospPctAmt;
private String mandalId;
private String villageId;
private String userId;
private String userRole;
private String searchFlag;
private String startIndex;
private String rowsPerPage;
private String schemeVal;
private String totRowCount;
private List<CasesSearchVO> lstCaseSearch = null;
private String showPage;
private String caseId;
private String teleStatus;
private String fromDate;
private String toDate;
private String caseSearchType;
private String caseStatus;
private String loginName;
private String hospName;
private Date SUBMITTEDDATE;
private String patientId;
private String mainSymptom;
private String symptom;
private String testName;
private String genTestPath;
private String diagnosisName;
private String mainCatName;
private String catName;
private String subCatName;
private String diseaseName;
private String disAnatomicalName;
private String disMainName;
private String icdCatName;
private String procName;
private String attachTotalPath;
private String opDrugName;
private String icdOpPkgName;
private String icdOpCatName;
private String caseStatusId;
private String investDesc;
private String drugName;
private String schemeId;
private long totPckgAmt;
private String downloadCSV;
private String csvMsg;
private String requestId;
private String csvFilePath;
private String nabhCase;
private String SPCLTYID;
private String patientType;
private Number DIFF;
private Date CASEREGNDATE;
private Number COUNT;

private Number CARDCOUNT;
private String CARDNO;
private String CASEPATIENTNO;
private String GENREMARKS;
private String EOREMARKS;
private String genRemarks1;
private String LOCNAME;
private String NABHCASE;
private String ADMNDATE;
private String SURGDATE;
private String DISCDATE;
private String RELATION;
private String CATNAME;

private String orgFlag;
private String organFlagNew;
private String AADHARID;
private String AADHAREID;
private String cardNo1;
private String cardNo2;
private String EMPNO;
private String CARDTYPE;
private Number CLAIMINITAMT;

public Number getCLAIMINITAMT() {
	return CLAIMINITAMT;
}
public void setCLAIMINITAMT(Number cLAIMINITAMT) {
	CLAIMINITAMT = cLAIMINITAMT;
}

public String getOrgFlag() {
	return orgFlag;
}
public void setOrgFlag(String orgFlag) {
	this.orgFlag = orgFlag;
}
public String getOrganFlagNew() {
	return organFlagNew;
}
public void setOrganFlagNew(String organFlagNew) {
	this.organFlagNew = organFlagNew;
}
private int startPage;
private int endPage;
private String showPages;
private String totalRows; 
public int getStartPage() {
	return startPage;
}
public void setStartPage(int startPage) {
	this.startPage = startPage;
}
public int getEndPage() {
	return endPage;
}
public void setEndPage(int endPage) {
	this.endPage = endPage;
}
public String getShowPages() {
	return showPages;
}
public void setShowPages(String showPages) {
	this.showPages = showPages;
}
public String getTotalRows() {
	return totalRows;
}
public void setTotalRows(String totalRows) {
	this.totalRows = totalRows;
}
public String getPrev() {
	return prev;
}
public void setPrev(String prev) {
	this.prev = prev;
}
public String getNext() {
	return next;
}
public void setNext(String next) {
	this.next = next;
}
public String getNoofRecords() {
	return NoofRecords;
}
public void setNoofRecords(String noofRecords) {
	NoofRecords = noofRecords;
}
public String getTotalRecords() {
	return TotalRecords;
}
public void setTotalRecords(String totalRecords) {
	TotalRecords = totalRecords;
}
private String prev;
private String next;
 private String NoofRecords;
 private String TotalRecords;

/**
 * Added for hubspokeCR
 */
private String diaFlg;
private String diaPendFlg;
public String getDiaFlg() {
	return diaFlg;
}
public void setDiaFlg(String diaFlg) {
	this.diaFlg = diaFlg;
}
public String getDiaPendFlg() {
	return diaPendFlg;
}
public void setDiaPendFlg(String diaPendFlg) {
	this.diaPendFlg = diaPendFlg;
}
/**
 * End
 * @return
 */

public String getErrDentalCaseApprovalFlag() {
	return errDentalCaseApprovalFlag;
}
public void setErrDentalCaseApprovalFlag(String errDentalCaseApprovalFlag) {
	this.errDentalCaseApprovalFlag = errDentalCaseApprovalFlag;
}
public String getDrugName() {
	return drugName;
}
public void setDrugName(String drugName) {
	this.drugName = drugName;
}
public String getInvestDesc() {
	return investDesc;
}
public void setInvestDesc(String investDesc) {
	this.investDesc = investDesc;
}
public String getCaseStatusId() {
	return caseStatusId;
}
public void setCaseStatusId(String caseStatusId) {
	this.caseStatusId = caseStatusId;
}
public String getIcdOpCatName() {
	return icdOpCatName;
}
public void setIcdOpCatName(String icdOpCatName) {
	this.icdOpCatName = icdOpCatName;
}
public String getIcdOpPkgName() {
	return icdOpPkgName;
}
public void setIcdOpPkgName(String icdOpPkgName) {
	this.icdOpPkgName = icdOpPkgName;
}
public String getOpDrugName() {
	return opDrugName;
}
public void setOpDrugName(String opDrugName) {
	this.opDrugName = opDrugName;
}
public String getAttachTotalPath() {
	return attachTotalPath;
}
public void setAttachTotalPath(String attachTotalPath) {
	this.attachTotalPath = attachTotalPath;
}
public String getDisMainName() {
	return disMainName;
}
public void setDisMainName(String disMainName) {
	this.disMainName = disMainName;
}
public String getIcdCatName() {
	return icdCatName;
}
public void setIcdCatName(String icdCatName) {
	this.icdCatName = icdCatName;
}
public String getProcName() {
	return procName;
}
public void setProcName(String procName) {
	this.procName = procName;
}
public String getDiagnosisName() {
	return diagnosisName;
}
public void setDiagnosisName(String diagnosisName) {
	this.diagnosisName = diagnosisName;
}
public String getMainCatName() {
	return mainCatName;
}
public void setMainCatName(String mainCatName) {
	this.mainCatName = mainCatName;
}
public String getCatName() {
	return catName;
}
public void setCatName(String catName) {
	this.catName = catName;
}
public String getSubCatName() {
	return subCatName;
}
public void setSubCatName(String subCatName) {
	this.subCatName = subCatName;
}
public String getDiseaseName() {
	return diseaseName;
}
public void setDiseaseName(String diseaseName) {
	this.diseaseName = diseaseName;
}
public String getDisAnatomicalName() {
	return disAnatomicalName;
}
public void setDisAnatomicalName(String disAnatomicalName) {
	this.disAnatomicalName = disAnatomicalName;
}

public String getTestName() {
	return testName;
}
public void setTestName(String testName) {
	this.testName = testName;
}
public String getGenTestPath() {
	return genTestPath;
}
public void setGenTestPath(String genTestPath) {
	this.genTestPath = genTestPath;
}
public String getMainSymptom() {
	return mainSymptom;
}
public void setMainSymptom(String mainSymptom) {
	this.mainSymptom = mainSymptom;
}
public String getSymptom() {
	return symptom;
}
public void setSymptom(String symptom) {
	this.symptom = symptom;
}
public String getPatientId() {
	return patientId;
}
public void setPatientId(String patientId) {
	this.patientId = patientId;
}
public String getSUBMITTEDDATE() {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(this.SUBMITTEDDATE==null)
		return "";
	else
	return sdf.format(SUBMITTEDDATE);
}

public void setSUBMITTEDDATE(Date sUBMITTEDDATE) {
	SUBMITTEDDATE = sUBMITTEDDATE;
}



public String getHospName() {
	return hospName;
}
public void setHospName(String hospName) {
	this.hospName = hospName;
}
public String getCaseStatus() {
	return caseStatus;
}
public void setCaseStatus(String caseStatus) {
	this.caseStatus = caseStatus;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getShowPage() {
	return showPage;
}
public void setShowPage(String showPage) {
	this.showPage = showPage;
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
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
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
public String getSchemeVal() {
	return schemeVal;
}
public void setSchemeVal(String schemeVal) {
	this.schemeVal = schemeVal;
}
public String getCatId() {
	return catId;
}
public void setCatId(String catId) {
	this.catId = catId;
}
public String getErrStatusId() {
	return errStatusId;
}
public void setErrStatusId(String errStatusId) {
	this.errStatusId = errStatusId;
}
public String getSurgyId() {
	return surgyId;
}
public void setSurgyId(String surgyId) {
	this.surgyId = surgyId;
}
public String getPatName() {
	return patName;
}
public void setPatName(String patName) {
	this.patName = patName;
}
public String getDistId() {
	return distId;
}
public void setDistId(String distId) {
	this.distId = distId;
}
public String getMandalId() {
	return mandalId;
}
public void setMandalId(String mandalId) {
	this.mandalId = mandalId;
}
public String getVillageId() {
	return villageId;
}
public void setVillageId(String villageId) {
	this.villageId = villageId;
}

public Long getClaimAmt() {
	return claimAmt;
}
public void setClaimAmt(Long claimAmt) {
	this.claimAmt = claimAmt;
}
public String getCaseNo() {
	return caseNo;
}
public void setCaseNo(String caseNo) {
	this.caseNo = caseNo;
}
public String getClaimNo() {
	return claimNo;
}
public void setClaimNo(String claimNo) {
	this.claimNo = claimNo;
}
public String getPatientName() {
	return patientName;
}
public void setPatientName(String patientName) {
	this.patientName = patientName;
}
public String getWapNo() {
	return wapNo;
}
public void setWapNo(String wapNo) {
	this.wapNo = wapNo;
}
public String getClaimStatus() {
	return claimStatus;
}
public void setClaimStatus(String claimStatus) {
	this.claimStatus = claimStatus;
}
public String getClaimSubmittedDate() {
	return claimSubmittedDate;
}
public void setClaimSubmittedDate(String claimSubmittedDate) {
	this.claimSubmittedDate = claimSubmittedDate;
}
public String getBillSubmittedDate() {
	return billSubmittedDate;
}
public void setBillSubmittedDate(String billSubmittedDate) {
	this.billSubmittedDate = billSubmittedDate;
}
public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
public String getTeleStatus() {
	return teleStatus;
}
public void setTeleStatus(String teleStatus) {
	this.teleStatus = teleStatus;
}
public String getCaseSearchType() {
	return caseSearchType;
}
public void setCaseSearchType(String caseSearchType) {
	this.caseSearchType = caseSearchType;
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
public String getErrStatus() {
	return errStatus;
}
public void setErrStatus(String errStatus) {
	this.errStatus = errStatus;
}
public String getPendingFlag() {
	return pendingFlag;
}
public void setPendingFlag(String pendingFlag) {
	this.pendingFlag = pendingFlag;
}
public String getCaseBlockedUsr() {
	return caseBlockedUsr;
}
public void setCaseBlockedUsr(String caseBlockedUsr) {
	this.caseBlockedUsr = caseBlockedUsr;
}
public String getCaseForDissFlg() {  
	return caseForDissFlg;
}
public void setCaseForDissFlg(String caseForDissFlg) {
	this.caseForDissFlg = caseForDissFlg;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}



/**
 * @return the feedback
 */
public String getFeedback() {
	return feedback;
}
/**
 * @param feedback the feedback to set
 */
public void setFeedback(String feedback) {
	this.feedback = feedback;
}
/**
 * @return the crt_usr
 */
public String getCrt_usr() {
	return crt_usr;
}
/**
 * @param crt_usr the crt_usr to set
 */
public void setCrt_usr(String crt_usr) {
	this.crt_usr = crt_usr;
}
/**
 * @return the crt_dt
 */

/**
 * @return the fEEDBACKID
 */
public String getFEEDBACKID() {
	return FEEDBACKID;
}
/**
 * @param fEEDBACKID the fEEDBACKID to set
 */
public void setFEEDBACKID(String fEEDBACKID) {
	FEEDBACKID = fEEDBACKID;
}
/**
 * @return the cREATEDBY
 */
public String getCREATEDBY() {
	return CREATEDBY;
}
/**
 * @param cREATEDBY the cREATEDBY to set
 */
public void setCREATEDBY(String cREATEDBY) {
	CREATEDBY = cREATEDBY;
}
/**
 * @return the cREATEDON
 */
public Date getCREATEDON() {
	return CREATEDON;
}
/**
 * @param cREATEDON the cREATEDON to set
 */
public void setCREATEDON(Date cREATEDON) {
	CREATEDON = cREATEDON;
}
/**
 * @return the rEMARKS
 */
public String getREMARKS() {
	return REMARKS;
}
/**
 * @param rEMARKS the rEMARKS to set
 */
public void setREMARKS(String rEMARKS) {
	REMARKS = rEMARKS;
}
public String getGrievanceFlag() {
	return grievanceFlag;
}
public void setGrievanceFlag(String grievanceFlag) {
	this.grievanceFlag = grievanceFlag;
}
public String getTelephonicId() {
	return telephonicId;
}
public void setTelephonicId(String telephonicId) {
	this.telephonicId = telephonicId;
}
public String getExcelFlag() {
	return excelFlag;
}
public void setExcelFlag(String excelFlag) {
	this.excelFlag = excelFlag;
}
public String getCsPreauthDt() {
	return csPreauthDt;
}

public String getFlagged() {
	return flagged;
}
public void setFlagged(String flagged) {
	this.flagged = flagged;
}
public void setCsPreauthDt(Date csPreauthDt) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(csPreauthDt != null )
		try {
			this.csPreauthDt =  sdf.format(csPreauthDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getPreauthAprvDate() {
	return preauthAprvDate;
}
public void setPreauthAprvDate(Date preauthAprvDate) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(preauthAprvDate != null )
		try {
			this.preauthAprvDate =  sdf.format(preauthAprvDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCsDisDt() {
	return csDisDt;
}
public void setCsDisDt(Date csDisDt) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(csDisDt != null )
		try {
			this.csDisDt =  sdf.format(csDisDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCsDeathDt() {
	return csDeathDt;
}
public void setCsDeathDt(Date csDeathDt) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(csDeathDt != null )
		try {
			this.csDeathDt =  sdf.format(csDeathDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getClmSubDt() {
	return clmSubDt;
}
public void setClmSubDt(Date clmSubDt) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(clmSubDt != null )
		try {
			this.clmSubDt =  sdf.format(clmSubDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCsTransId() {
	return csTransId;
}
public void setCsTransId(String csTransId) {
	this.csTransId = csTransId;
}
public String getCsTransDt() {
	return csTransDt;
}
public void setCsTransDt(Date csTransDt) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(csTransDt != null )
		try {
			this.csTransDt =  sdf.format(csTransDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCsSbhDt() {
	return csSbhDt;
}
public void setCsSbhDt(Date csSbhDt) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy a");
	if(csSbhDt != null )
		try {
			this.csSbhDt =  sdf.format(csSbhDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCsRemarks() {
	return csRemarks;
}
public void setCsRemarks(String csRemarks) {
	this.csRemarks = csRemarks;
}
public String getCsSurgDt() {
	return csSurgDt;
}
public void setCsSurgDt(String csSurgDt) {
	this.csSurgDt = csSurgDt;
}
public String getSchemeId() {
	return schemeId;
}
public void setSchemeId(String schemeId) {
	this.schemeId = schemeId;
}
public String getPatientScheme() {
	return patientScheme;
}
public void setPatientScheme(String patientScheme) {
	this.patientScheme = patientScheme;
}
public String getCeoFlag() {
	return ceoFlag;
}
public void setCeoFlag(String ceoFlag) {
	this.ceoFlag = ceoFlag;
}
public String getProcedure() {
	return procedure;
}
public void setProcedure(String procedure) {
	this.procedure = procedure;
}
public String getPreauthPckgAmt() {
	return preauthPckgAmt;
}
public void setPreauthPckgAmt(String preauthPckgAmt) {
	this.preauthPckgAmt = preauthPckgAmt;
}
public String getEnhFlag() {
	return enhFlag;
}
public void setEnhFlag(String enhFlag) {
	this.enhFlag = enhFlag;
}
public long getEnhReqAmt() {
	return enhReqAmt;
}
public void setEnhReqAmt(long enhReqAmt) {
	this.enhReqAmt = enhReqAmt;
}
public long getTotPckgAmt() {
	return totPckgAmt;
}
public void setTotPckgAmt(long totPckgAmt) {
	this.totPckgAmt = totPckgAmt;
}
public String getDentalFlag() {
	return dentalFlag;
}
public void setDentalFlag(String dentalFlag) {
	this.dentalFlag = dentalFlag;
}

public String getEnhanceflag() {
	return enhanceflag;
}
public void setEnhanceflag(String enhanceflag) {
	this.enhanceflag = enhanceflag;
}
public String getCATID() {
	return CATID;
}
public void setCATID(String cATID) {
	CATID = cATID;
}
public String getPATIPOP() {
	return PATIPOP;
}
public void setPATIPOP(String pATIPOP) {
	PATIPOP = pATIPOP;
}
public String getISSUESTATUS() {
	return ISSUESTATUS;
}
public void setISSUESTATUS(String iSSUESTATUS) {
	ISSUESTATUS = iSSUESTATUS;
}
public String getCASENO() {
	return CASENO;
}
public void setCASENO(String cASENO) {
	CASENO = cASENO;
}
public String getPATIENTID() {
	return PATIENTID;
}
public void setPATIENTID(String pATIENTID) {
	PATIENTID = pATIENTID;
}
public String getPATNAME() {
	return PATNAME;
}
public void setPATNAME(String pATNAME) {
	PATNAME = pATNAME;
}
public String getHOSPNAME() {
	return HOSPNAME;
}
public void setHOSPNAME(String hOSPNAME) {
	HOSPNAME = hOSPNAME;
}
public String getCASESTATUS() {
	return CASESTATUS;
}
public void setCASESTATUS(String cASESTATUS) {
	CASESTATUS = cASESTATUS;
}
public String getCASESTATUSID() {
	return CASESTATUSID;
}
public void setCASESTATUSID(String cASESTATUSID) {
	CASESTATUSID = cASESTATUSID;
}
public Number getCLAIMAMT() {
	return CLAIMAMT;
}
public void setCLAIMAMT(Number cLAIMAMT) {
	CLAIMAMT = cLAIMAMT;
}
public Number getPREAUTHAPPAMT() {
	return PREAUTHAPPAMT;
}
public void setPREAUTHAPPAMT(Number pREAUTHAPPAMT) {
	PREAUTHAPPAMT = pREAUTHAPPAMT;
}
public String getPROC() {
	return PROC;
}
public void setPROC(String pROC) {
	PROC = pROC;
}
public String getTdsPctAmt() {
	return tdsPctAmt;
}
public void setTdsPctAmt(String tdsPctAmt) {
	this.tdsPctAmt = tdsPctAmt;
}
public String getTrustPctAmt() {
	return trustPctAmt;
}
public void setTrustPctAmt(String trustPctAmt) {
	this.trustPctAmt = trustPctAmt;
}
public String getTdsHospPctAmt() {
	return tdsHospPctAmt;
}
public void setTdsHospPctAmt(String tdsHospPctAmt) {
	this.tdsHospPctAmt = tdsHospPctAmt;
}
public String getHospPctAmt() {
	return hospPctAmt;
}
public void setHospPctAmt(String hospPctAmt) {
	this.hospPctAmt = hospPctAmt;
}
public String getDownloadCSV() {
	return downloadCSV;
}
public void setDownloadCSV(String downloadCSV) {
	this.downloadCSV = downloadCSV;
}
public String getCsvMsg() {
	return csvMsg;
}
public void setCsvMsg(String csvMsg) {
	this.csvMsg = csvMsg;
}

public String getActiveyn() {
	return activeyn;
}
public void setActiveyn(String activeyn) {
	this.activeyn = activeyn;
}
public String getClaimAmt1() {
	return claimAmt1;
}
public void setClaimAmt1(String claimAmt1) {
	this.claimAmt1 = claimAmt1;
}
public Date getFollowUpSubDt() {
	return followUpSubDt;
}
public void setFollowUpSubDt(Date followUpSubDt) {
	this.followUpSubDt = followUpSubDt;
}
public String getFollowUpStatus() {
	return followUpStatus;
}
public void setFollowUpStatus(String followUpStatus) {
	this.followUpStatus = followUpStatus;
}
public String getFollowUpId() {
	return followUpId;
}
public void setFollowUpId(String followUpId) {
	this.followUpId = followUpId;
}
public String getSpeciality() {
	return speciality;
}
public void setSpeciality(String speciality) {
	this.speciality = speciality;
}
public String getProcess() {
	return process;
}
public void setProcess(String process) {
	this.process = process;
}
public String getProcCode() {
	return procCode;
}
public void setProcCode(String procCode) {
	this.procCode = procCode;
}
public int getStart_index() {
	return start_index;
}
public void setStart_index(int start_index) {
	this.start_index = start_index;
}
public int getEnd_index() {
	return end_index;
}
public void setEnd_index(int end_index) {
	this.end_index = end_index;
}
public Long getPreauthAppAmt() {
	return preauthAppAmt;
}
public void setPreauthAppAmt(Long preauthAppAmt) {
	this.preauthAppAmt = preauthAppAmt;
}

public String getIssueResultFlagSize() {
	return issueResultFlagSize;
}
public void setIssueResultFlagSize(String issueResultFlagSize) {
	this.issueResultFlagSize = issueResultFlagSize;
}
public Long getIssueId() {
	return issueId;
}
public void setIssueId(Long issueId) {
	this.issueId = issueId;
}
public String getIssuecaseId() {
	return issuecaseId;
}
public void setIssuecaseId(String issuecaseId) {
	this.issuecaseId = issuecaseId;
}
public String getHospId() {
	return hospId;
}
public void setHospId(String hospId) {
	this.hospId = hospId;
}
public String getIssuestatus() {
	return issuestatus;
}
public void setIssuestatus(String issuestatus) {
	this.issuestatus = issuestatus;
}
public String getIssuetitle() {
	return issuetitle;
}
public void setIssuetitle(String issuetitle) {
	this.issuetitle = issuetitle;
}
public Date getCreateddate() {
	return createddate;
}
public void setCreateddate(Date createddate) {
	this.createddate = createddate;
}
public String getIssueResultFlag() {
	return issueResultFlag;
}
public void setIssueResultFlag(String issueResultFlag) {
	this.issueResultFlag = issueResultFlag;
}
private String module;

public String getErrCaseApprovalFlag() {
	return errCaseApprovalFlag;
}
public void setErrCaseApprovalFlag(String errCaseApprovalFlag) {
	this.errCaseApprovalFlag = errCaseApprovalFlag;
}
public String getDrugSubTypeName() {
	return drugSubTypeName;
}
public void setDrugSubTypeName(String drugSubTypeName) {
	this.drugSubTypeName = drugSubTypeName;
}
public String getDrugTypeName() {
	return drugTypeName;
}
public void setDrugTypeName(String drugTypeName) {
	this.drugTypeName = drugTypeName;
}
public String getPatIpOp() {
	return patIpOp;
}
public void setPatIpOp(String patIpOp) {
	this.patIpOp = patIpOp;
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
public List<LabelBean> getGrpList() {
	return grpList;
}
public void setGrpList(List<LabelBean> grpList) {
	this.grpList = grpList;
}
public String getColorFlag() {
	return colorFlag;
}
public void setColorFlag(String colorFlag) {
	this.colorFlag = colorFlag;
}
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;	
}
public String getViewFlag() {
	return viewFlag;
}
public void setViewFlag(String viewFlag) {
	this.viewFlag = viewFlag;
}
public String getInpatientCaseSubDt() {
	return inpatientCaseSubDt;
}
public void setInpatientCaseSubDt(Date inpatientCaseSubDt) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(inpatientCaseSubDt != null )
		try {
			this.inpatientCaseSubDt =  sdf.format(inpatientCaseSubDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getRequestId() {
	return requestId;
}
public void setRequestId(String requestId) {
	this.requestId = requestId;
}
public String getCsvFilePath() {
	return csvFilePath;
}
public void setCsvFilePath(String csvFilePath) {
	this.csvFilePath = csvFilePath;
}
public String getNabhCase() {
	return nabhCase;
}
public void setNabhCase(String nabhCase) {
	this.nabhCase = nabhCase;
}

public String getCARDNO() {
	return CARDNO;
}
public void setCARDNO(String cARDNO) {
	CARDNO = cARDNO;
}
public String getCASEPATIENTNO() {
	return CASEPATIENTNO;
}
public void setCASEPATIENTNO(String cASEPATIENTNO) {
	CASEPATIENTNO = cASEPATIENTNO;
}
public Number getCARDCOUNT() {
	return CARDCOUNT;
}
public void setCARDCOUNT(Number cARDCOUNT) {
	CARDCOUNT = cARDCOUNT;
}
public String getSPCLTYID() {
	return SPCLTYID;
}
public void setSPCLTYID(String sPCLTYID) {
	SPCLTYID = sPCLTYID;
}
public String getColorCode() {
	return colorCode;
}
public void setColorCode(String colorCode) {
	this.colorCode = colorCode;
}
public String getColorCodeView() {
	return colorCodeView;
}
public void setColorCodeView(String colorCodeView) {
	this.colorCodeView = colorCodeView;
}
public Number getDIFF() {
	return DIFF;
}
public void setDIFF(Number dIFF) {
	DIFF = dIFF;
}
public String getPatientType() {
	return patientType;
}
public void setPatientType(String patientType) {
	this.patientType = patientType;
}

public String getOldCaseId() {
	return oldCaseId;
}
public void setOldCaseId(String oldCaseId) {
	this.oldCaseId = oldCaseId;
}

/**
 * Added by ramalakshmi for CSV Cases search
 * @return
 */
private String CASEID;
private String FLAGGED;
private String PATIENTNAME;
private String WAPNO;
private String CLAIMSTATUS;
private String HOSPID;
private String SCHEMEID;
private Date LSTUPDDT        ;
private String ERRSTATUSID    ;
private String PENDINGFLAG     ;
private String PREAUTHPCKGAMT  ;
private String ENHFLAG         ;
private Long ENHREQAMT       ;
private String PREAUTHTOTALPACKAGEAMT;
private String TDSPCTAMT         ;
private String TRUSTPCTAMT       ;
private String HOSPPCTAMT        ;
private String TDSHOSPPCTAMT     ;
private String TELESTATUS        ;
private String VIEWFLAG          ;
private String CASEBLOCKEDUSR    ;
private String CLAIMNO           ;
private String GRIEVANCEFLAG     ;
private String CSPREAUTHDT       ;
private Date PREAUTHAPRVDT       ;
private String CSDISDT           ;
private String CSDEATHDT         ;
private String CLMSUBDT          ;
private String CSTRANSID         ;
private String CSTRANSDT         ;
private String CSREMARKS         ;
private String CSSBHDT           ;
private String PREAUTHAPRVDATE;
private String INPATIENTCASESUBDT;

/**
 * End of cases search CSv
 * @return
 */

/**Added by  ramalakshmi for DME login cases search */
private String healthCardNum;
private String empPenId;
private String agreeCondtn;

private List<CasesSearchVO> PreauthCaseSearch;
//Added by ramalakshmi for DME login

private String PNAME;

private String EMPLOYEENO;

private String PROCDURENAME;
private String PREAUTHDATE;
private String PREAUTHAMT;
private List<CasesSearchVO> lstSplInvet;

private String icdCatCode;

public String getIcdCatCode() {
	return icdCatCode;
}
public void setIcdCatCode(String icdCatCode) {
	this.icdCatCode = icdCatCode;
}
public String getIcdProcCode() {
	return icdProcCode;
}
public void setIcdProcCode(String icdProcCode) {
	this.icdProcCode = icdProcCode;
}
public String getSeqNo() {
	return seqNo;
}
public void setSeqNo(String seqNo) {
	this.seqNo = seqNo;
}
public String getDocName() {
	return docName;
}
public void setDocName(String docName) {
	this.docName = docName;
}
public String getDocReg() {
	return docReg;
}
public void setDocReg(String docReg) {
	this.docReg = docReg;
}
public String getDocQual() {
	return docQual;
}
public void setDocQual(String docQual) {
	this.docQual = docQual;
}
public String getDocMobNo() {
	return docMobNo;
}
public void setDocMobNo(String docMobNo) {
	this.docMobNo = docMobNo;
}
public String getOpProcUnits() {
	return opProcUnits;
}
public void setOpProcUnits(String opProcUnits) {
	this.opProcUnits = opProcUnits;
}
public String getInvestRemarks() {
	return investRemarks;
}
public void setInvestRemarks(String investRemarks) {
	this.investRemarks = investRemarks;
}
public String getDays() {
	return days;
}
public void setDays(String days) {
	this.days = days;
}
public String getActiveYN() {
	return activeYN;
}
public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}
private String icdProcCode;
private String seqNo;
private String docName;
private String docReg;
private String docQual;
private String docMobNo;
private String opProcUnits;
private String criticalFlag;

private String investRemarks;
private String days;
private String activeYN;

private String asriCatName;
private String hospStayAmt;
private String commonCatAmt;
private String icdAmt;
private String dmeFlag;


public String getPNAME() {
	return PNAME;
}
public void setPNAME(String pNAME) {
	PNAME = pNAME;
}
public String getEMPLOYEENO() {
	return EMPLOYEENO;
}
public void setEMPLOYEENO(String eMPLOYEENO) {
	EMPLOYEENO = eMPLOYEENO;
}
public String getPROCDURENAME() {
	return PROCDURENAME;
}
public void setPROCDURENAME(String pROCDURENAME) {
	PROCDURENAME = pROCDURENAME;
}
public String getPREAUTHDATE() {
	return PREAUTHDATE;
}
public void setPREAUTHDATE(String pREAUTHDATE) {
	PREAUTHDATE = pREAUTHDATE;
}
public String getPREAUTHAMT() {
	return PREAUTHAMT;
}
public void setPREAUTHAMT(String pREAUTHAMT) {
	PREAUTHAMT = pREAUTHAMT;
}
public String getCASEID() {
	return CASEID;
}
public void setCASEID(String cASEID) {
	CASEID = cASEID;
}
public String getFLAGGED() {
	return FLAGGED;
}
public void setFLAGGED(String fLAGGED) {
	FLAGGED = fLAGGED;
}
public String getPATIENTNAME() {
	return PATIENTNAME;
}
public void setPATIENTNAME(String pATIENTNAME) {
	PATIENTNAME = pATIENTNAME;
}
public String getWAPNO() {
	return WAPNO;
}
public void setWAPNO(String wAPNO) {
	WAPNO = wAPNO;
}
public String getCLAIMSTATUS() {
	return CLAIMSTATUS;
}
public void setCLAIMSTATUS(String cLAIMSTATUS) {
	CLAIMSTATUS = cLAIMSTATUS;
}
public String getHOSPID() {
	return HOSPID;
}
public void setHOSPID(String hOSPID) {
	HOSPID = hOSPID;
}
public String getSCHEMEID() {
	return SCHEMEID;
}
public void setSCHEMEID(String sCHEMEID) {
	SCHEMEID = sCHEMEID;
}
public String getPENDINGFLAG() {
	return PENDINGFLAG;
}
public void setPENDINGFLAG(String pENDINGFLAG) {
	PENDINGFLAG = pENDINGFLAG;
}
public String getPREAUTHPCKGAMT() {
	return PREAUTHPCKGAMT;
}
public void setPREAUTHPCKGAMT(String pREAUTHPCKGAMT) {
	PREAUTHPCKGAMT = pREAUTHPCKGAMT;
}
public String getENHFLAG() {
	return ENHFLAG;
}
public void setENHFLAG(String eNHFLAG) {
	ENHFLAG = eNHFLAG;
}
public String getPREAUTHTOTALPACKAGEAMT() {
	return PREAUTHTOTALPACKAGEAMT;
}
public void setPREAUTHTOTALPACKAGEAMT(String pREAUTHTOTALPACKAGEAMT) {
	PREAUTHTOTALPACKAGEAMT = pREAUTHTOTALPACKAGEAMT;
}
public String getTDSPCTAMT() {
	return TDSPCTAMT;
}
public void setTDSPCTAMT(String tDSPCTAMT) {
	TDSPCTAMT = tDSPCTAMT;
}
public String getTRUSTPCTAMT() {
	return TRUSTPCTAMT;
}
public void setTRUSTPCTAMT(String tRUSTPCTAMT) {
	TRUSTPCTAMT = tRUSTPCTAMT;
}
public String getHOSPPCTAMT() {
	return HOSPPCTAMT;
}
public void setHOSPPCTAMT(String hOSPPCTAMT) {
	HOSPPCTAMT = hOSPPCTAMT;
}
public String getTDSHOSPPCTAMT() {
	return TDSHOSPPCTAMT;
}
public void setTDSHOSPPCTAMT(String tDSHOSPPCTAMT) {
	TDSHOSPPCTAMT = tDSHOSPPCTAMT;
}
public String getTELESTATUS() {
	return TELESTATUS;
}
public void setTELESTATUS(String tELESTATUS) {
	TELESTATUS = tELESTATUS;
}
public String getVIEWFLAG() {
	return VIEWFLAG;
}
public void setVIEWFLAG(String vIEWFLAG) {
	VIEWFLAG = vIEWFLAG;
}
public String getCASEBLOCKEDUSR() {
	return CASEBLOCKEDUSR;
}
public void setCASEBLOCKEDUSR(String cASEBLOCKEDUSR) {
	CASEBLOCKEDUSR = cASEBLOCKEDUSR;
}
public String getCLAIMNO() {
	return CLAIMNO;
}
public void setCLAIMNO(String cLAIMNO) {
	CLAIMNO = cLAIMNO;
}
public String getGRIEVANCEFLAG() {
	return GRIEVANCEFLAG;
}
public void setGRIEVANCEFLAG(String gRIEVANCEFLAG) {
	GRIEVANCEFLAG = gRIEVANCEFLAG;
}
public String getCSPREAUTHDT() {
	return CSPREAUTHDT;
}
public void setCSPREAUTHDT(Date CSPREAUTHDT) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		if(CSPREAUTHDT != null )
			try {
				this.CSPREAUTHDT =  sdf.format(CSPREAUTHDT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
public Date getPREAUTHAPRVDT() {
	return PREAUTHAPRVDT;
}
public void setPREAUTHAPRVDT(Date pREAUTHAPRVDT) {
	PREAUTHAPRVDT = pREAUTHAPRVDT;
}
public String getCSDISDT() {
	return CSDISDT;
}
public void setCSDISDT(Date CSDISDT) {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(CSDISDT != null )
		try {
			this.CSDISDT =  sdf.format(CSDISDT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCSDEATHDT() {
	return CSDEATHDT;
}
public void setCSDEATHDT(Date CSDEATHDT) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(CSDEATHDT != null )
		try {
			this.CSDEATHDT =  sdf.format(CSDEATHDT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCLMSUBDT() {
	return CLMSUBDT;
}
public void setCLMSUBDT(Date CLMSUBDT) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(CLMSUBDT != null )
		try {
			this.CLMSUBDT =  sdf.format(CLMSUBDT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCSTRANSID() {
	return CSTRANSID;
}
public void setCSTRANSID(String cSTRANSID) {
	CSTRANSID = cSTRANSID;
}
public String getCSTRANSDT() {
	return CSTRANSDT;
}
public void setCSTRANSDT(Date CSTRANSDT) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(CSTRANSDT != null )
		try {
			this.CSTRANSDT =  sdf.format(CSTRANSDT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getCSREMARKS() {
	return CSREMARKS;
}
public void setCSREMARKS(String cSREMARKS) {
	CSREMARKS = cSREMARKS;
}
public String getCSSBHDT() {
	return CSSBHDT;
}
public void setCSSBHDT(Date CSSBHDT) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(CSSBHDT != null )
		try {
			this.CSSBHDT =  sdf.format(CSSBHDT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getPREAUTHAPRVDATE() {
	return PREAUTHAPRVDATE;
}
public void setPREAUTHAPRVDATE(Date PREAUTHAPRVDATE) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(PREAUTHAPRVDATE != null )
		try {
			this.PREAUTHAPRVDATE =  sdf.format(PREAUTHAPRVDATE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getINPATIENTCASESUBDT() {
	return INPATIENTCASESUBDT;
}
public void setINPATIENTCASESUBDT(Date INPATIENTCASESUBDT) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	if(INPATIENTCASESUBDT != null )
		try {
			this.INPATIENTCASESUBDT =  sdf.format(INPATIENTCASESUBDT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public Date getLSTUPDDT() {
	return LSTUPDDT;
}
public void setLSTUPDDT(Date lSTUPDDT) {
	LSTUPDDT = lSTUPDDT;
}
public long getENHREQAMT() {
	return ENHREQAMT;
}
public void setENHREQAMT(long eNHREQAMT) {
	ENHREQAMT = eNHREQAMT;
}
public String getERRSTATUSID() {
	return ERRSTATUSID;
}
public void setERRSTATUSID(String eRRSTATUSID) {
	ERRSTATUSID = eRRSTATUSID;
}
public String getEmpPenId() {
	return empPenId;
}
public void setEmpPenId(String empPenId) {
	this.empPenId = empPenId;
}
public String getAgreeCondtn() {
	return agreeCondtn;
}
public void setAgreeCondtn(String agreeCondtn) {
	this.agreeCondtn = agreeCondtn;
}
public String getHealthCardNum() {
	return healthCardNum;
}
public void setHealthCardNum(String healthCardNum) {
	this.healthCardNum = healthCardNum;
}
public List<CasesSearchVO> getPreauthCaseSearch() {
	return PreauthCaseSearch;
}
public void setPreauthCaseSearch(List<CasesSearchVO> preauthCaseSearch) {
	PreauthCaseSearch = preauthCaseSearch;
}
public List<CasesSearchVO> getLstSplInvet() {
	return lstSplInvet;
}
public void setLstSplInvet(List<CasesSearchVO> lstSplInvet) {
	this.lstSplInvet = lstSplInvet;
}
public String getCriticalFlag() {
	return criticalFlag;
}
public void setCriticalFlag(String criticalFlag) {
	this.criticalFlag = criticalFlag;
}
public String getAsriCatName() {
	return asriCatName;
}
public void setAsriCatName(String asriCatName) {
	this.asriCatName = asriCatName;
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
public String getDmeFlag() {
	return dmeFlag;
}
public void setDmeFlag(String dmeFlag) {
	this.dmeFlag = dmeFlag;
}
public String getGENREMARKS() {
	return GENREMARKS;
}
public void setGENREMARKS(String gENREMARKS) {
	GENREMARKS = gENREMARKS;
}
public String getGenRemarks1() {
	return genRemarks1;
}
public void setGenRemarks1(String genRemarks1) {
	this.genRemarks1 = genRemarks1;
}
public String getLOCNAME() {
	return LOCNAME;
}
public void setLOCNAME(String lOCNAME) {
	LOCNAME = lOCNAME;
}
public String getNABHCASE() {
	return NABHCASE;
}
public void setNABHCASE(String nABHCASE) {
	NABHCASE = nABHCASE;
}
public String getEOREMARKS() {
	return EOREMARKS;
}
public void setEOREMARKS(String eOREMARKS) {
	EOREMARKS = eOREMARKS;
}
public String getADMNDATE() {
	return ADMNDATE;
}
public void setADMNDATE(String aDMNDATE) {
	ADMNDATE = aDMNDATE;
}
public String getSURGDATE() {
	return SURGDATE;
}
public void setSURGDATE(String sURGDATE) {
	SURGDATE = sURGDATE;
}
public String getDISCDATE() {
	return DISCDATE;
}
public void setDISCDATE(String dISCDATE) {
	DISCDATE = dISCDATE;
}
public String getRELATION() {
	return RELATION;
}
public void setRELATION(String rELATION) {
	RELATION = rELATION;
}
public String getCATNAME() {
	return CATNAME;
}
public void setCATNAME(String cATNAME) {
	CATNAME = cATNAME;
}
public Number getCOUNT() {
	return COUNT;
}
public void setCOUNT(Number cOUNT) {
	COUNT = cOUNT;
}
public String getAADHARID() {
	return AADHARID;
}
public void setAADHARID(String aADHARID) {
	AADHARID = aADHARID;
}
public String getCardNo1() {
	return cardNo1;
}
public void setCardNo1(String cardNo1) {
	this.cardNo1 = cardNo1;
}
public String getCardNo2() {
	return cardNo2;
}
public void setCardNo2(String cardNo2) {
	this.cardNo2 = cardNo2;
}
public String getEMPNO() {
	return EMPNO;
}
public void setEMPNO(String eMPNO) {
	EMPNO = eMPNO;
}
public String getCARDTYPE() {
	return CARDTYPE;
}
public void setCARDTYPE(String cARDTYPE) {
	CARDTYPE = cARDTYPE;
}
public String getAADHAREID() {
	return AADHAREID;
}
public void setAADHAREID(String aADHAREID) {
	AADHAREID = aADHAREID;
}
public String getLstUpdUsrNew() {
	return lstUpdUsrNew;
}
public void setLstUpdUsrNew(String lstUpdUsrNew) {
	this.lstUpdUsrNew = lstUpdUsrNew;
}

public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}
public Date getCASEREGNDATE() {
	return CASEREGNDATE;
}
public void setCASEREGNDATE(Date cASEREGNDATE) {
	CASEREGNDATE = cASEREGNDATE;
}



}
