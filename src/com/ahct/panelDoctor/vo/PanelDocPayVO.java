package com.ahct.panelDoctor.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import com.ahct.common.vo.LabelBean;

public class PanelDocPayVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String ID;
	private String EMPNAME;
	private String DOC_NAME;
	private String DOC_ID;
	private String CL_APP_AMT;
	private String CL_PEND_AMT;
	private String CL_REJ_AMT;
	private String PR_APP_AMT;
	private String PR_PEND_AMT;
	private String PR_REJ_AMT;
	private String AMOUNT;
	private Number CLAIM_APRV_AMT;
	private Number CLAIM_REJ_AMT;
	private Number CLAIM_PEND_AMT;
	private Number PREAUTH_APRV_AMT;
	private Number PREAUTH_REJ_AMT;
	private Number PREAUTH_PEND_AMT;
	private Number TOTAL_PNLDOC_AMT;
	private String selperiod;
	private String fromDate;
	private String toDate;
	private String dispType;
	private String CASE_DATE;
	private String CASE_ID;
	private String HOSP_ID;
	private String HOSP_NAME;
	private String PARTICULARS;
	private String actionType;
	private String result;
	private String panelDocPmntStatus;
	private String DISTINCTSTATUS;
	private String COUNT;
	private List<LabelBean> grpList;
	private String LEVELLIST;
	private String GROUPNAME;
	private String GRPID;
	private String prevGrpName;
	private String curGrpName;
	private String curLevel;
	private String nextLevel;
	private String currGrpId;
	private String remarks;
	private String CURRWRKFLWID;
	private String prevWrkflowId;
	private String prevOwnr;
	private String currWrkflowId;
	private String currOwnr;
	private String CURROWNR;
	private String BANKNAME;
	private String BANKBRANCH;
	private String BANKCATEGORY;
	private String BANKIFSC;
	private String BANKID;
	private String ACCOUNTNO;
	private String ACCNAME;
	private double PanelDocAmt;
	private double TDSAmt;
	private double SurchargeAmt;
	private double CessAmt;
	private double TaxAmt;
	private String tdsActive;
	private String caseSetId;
	private String tdsPaymentId;
	private String SRCACCNO;
	private String DESTACCNO;
	private String TDSID;
	private String ADDRESS;
	private String failedList;
	private String REGCODE;
	private String WRKFLWSETID;
	private String flag;
	private String FILEID;
	private String USERTYPE;
	private String paymentUID;
	private Number WORKFLW_SET_ID;
	private String scheme;
	private String SCHEME;
	private Long claimAprvAmt;
	private Long claimRejAmt;
	private Long claimPendAmt;
	private Long preauthAprvAmt;
	private Long preauthRejAmt;
	private Long preauthPendAmt;
	private Long totalPnldocAmt;
	private Long wId;
	private Number WID;
	private Number IDNUM;
	private String month;
	private String year;
	private String MONTH;
	private String YEAR;
	private String firstName;
	private String lastName;
	private String workFlow;
	private Date lstUpdDt;
	private String wrkFlowFlg;
	private String action;
	private String lastMonth;
	private String lastYear;
	private String type;
	private String ceoRemarks;
	private String ceoRemarksDoc;
	private int startIndex;
	private int endIndex;
	private Date crtDt;
	private String caseType;
	
	private String HD_PEND;
	private String HD_RELEASE;
	
	private String paymentStatusSelected;
	private String sentBackSearch;
	private String sentBack;
	private String sentToUser;
	private String sentToSec;
	private String PREVWRKFLWID;
	
	
	public String getHD_PEND() {
		return HD_PEND;
	}
	public void setHD_PEND(String hD_PEND) {
		HD_PEND = hD_PEND;
	}
	public String getHD_RELEASE() {
		return HD_RELEASE;
	}
	public void setHD_RELEASE(String hD_RELEASE) {
		HD_RELEASE = hD_RELEASE;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getWrkFlowFlg() {
		return wrkFlowFlg;
	}
	public void setWrkFlowFlg(String wrkFlowFlg) {
		this.wrkFlowFlg = wrkFlowFlg;
	}
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	public String getWorkFlow() {
		return workFlow;
	}
	public void setWorkFlow(String workFlow) {
		this.workFlow = workFlow;
	}
	public Long getwId() {
		return wId;
	}
	public void setwId(Long wId) {
		this.wId = wId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Long getClaimAprvAmt() {
		return claimAprvAmt;
	}
	public void setClaimAprvAmt(Long claimAprvAmt) {
		this.claimAprvAmt = claimAprvAmt;
	}
	public Long getClaimRejAmt() {
		return claimRejAmt;
	}
	public void setClaimRejAmt(Long claimRejAmt) {
		this.claimRejAmt = claimRejAmt;
	}
	public Long getClaimPendAmt() {
		return claimPendAmt;
	}
	public void setClaimPendAmt(Long claimPendAmt) {
		this.claimPendAmt = claimPendAmt;
	}
	public Long getPreauthAprvAmt() {
		return preauthAprvAmt;
	}
	public void setPreauthAprvAmt(Long preauthAprvAmt) {
		this.preauthAprvAmt = preauthAprvAmt;
	}
	public Long getPreauthRejAmt() {
		return preauthRejAmt;
	}
	public void setPreauthRejAmt(Long preauthRejAmt) {
		this.preauthRejAmt = preauthRejAmt;
	}
	public Long getPreauthPendAmt() {
		return preauthPendAmt;
	}
	public void setPreauthPendAmt(Long preauthPendAmt) {
		this.preauthPendAmt = preauthPendAmt;
	}
	public Long getTotalPnldocAmt() {
		return totalPnldocAmt;
	}
	public void setTotalPnldocAmt(Long totalPnldocAmt) {
		this.totalPnldocAmt = totalPnldocAmt;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public Number getWORKFLW_SET_ID() {
		return WORKFLW_SET_ID;
	}
	public void setWORKFLW_SET_ID(Number wORKFLW_SET_ID) {
		WORKFLW_SET_ID = wORKFLW_SET_ID;
	}
	public String getUSERTYPE() {
		return USERTYPE;
	}
	public void setUSERTYPE(String uSERTYPE) {
		USERTYPE = uSERTYPE;
	}
	public double getPanelDocAmt() {
		return PanelDocAmt;
	}
	public void setPanelDocAmt(double panelDocAmt) {
		PanelDocAmt = panelDocAmt;
	}
	public double getTDSAmt() {
		return TDSAmt;
	}
	public void setTDSAmt(double tDSAmt) {
		TDSAmt = tDSAmt;
	}
	public double getSurchargeAmt() {
		return SurchargeAmt;
	}
	public void setSurchargeAmt(double surchargeAmt) {
		SurchargeAmt = surchargeAmt;
	}
	public double getCessAmt() {
		return CessAmt;
	}
	public void setCessAmt(double cessAmt) {
		CessAmt = cessAmt;
	}
	public double getTaxAmt() {
		return TaxAmt;
	}
	public void setTaxAmt(double taxAmt) {
		TaxAmt = taxAmt;
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
	public String getBANKIFSC() {
		return BANKIFSC;
	}
	public void setBANKIFSC(String bANKIFSC) {
		BANKIFSC = bANKIFSC;
	}
	public String getBANKID() {
		return BANKID;
	}
	public void setBANKID(String bANKID) {
		BANKID = bANKID;
	}
	public String getACCOUNTNO() {
		return ACCOUNTNO;
	}
	public void setACCOUNTNO(String aCCOUNTNO) {
		ACCOUNTNO = aCCOUNTNO;
	}
	public String getACCNAME() {
		return ACCNAME;
	}
	public void setACCNAME(String aCCNAME) {
		ACCNAME = aCCNAME;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getEMPNAME() {
		return EMPNAME;
	}
	public void setEMPNAME(String eMPNAME) {
		EMPNAME = eMPNAME;
	}
	public String getDOC_NAME() {
		return DOC_NAME;
	}
	public void setDOC_NAME(String dOC_NAME) {
		DOC_NAME = dOC_NAME;
	}
	public String getDOC_ID() {
		return DOC_ID;
	}
	public void setDOC_ID(String dOC_ID) {
		DOC_ID = dOC_ID;
	}
	public String getCL_APP_AMT() {
		return CL_APP_AMT;
	}
	public void setCL_APP_AMT(String cL_APP_AMT) {
		CL_APP_AMT = cL_APP_AMT;
	}
	public String getCL_PEND_AMT() {
		return CL_PEND_AMT;
	}
	public void setCL_PEND_AMT(String cL_PEND_AMT) {
		CL_PEND_AMT = cL_PEND_AMT;
	}
	public String getCL_REJ_AMT() {
		return CL_REJ_AMT;
	}
	public void setCL_REJ_AMT(String cL_REJ_AMT) {
		CL_REJ_AMT = cL_REJ_AMT;
	}
	public String getPR_APP_AMT() {
		return PR_APP_AMT;
	}
	public void setPR_APP_AMT(String pR_APP_AMT) {
		PR_APP_AMT = pR_APP_AMT;
	}
	public String getPR_PEND_AMT() {
		return PR_PEND_AMT;
	}
	public void setPR_PEND_AMT(String pR_PEND_AMT) {
		PR_PEND_AMT = pR_PEND_AMT;
	}
	public String getPR_REJ_AMT() {
		return PR_REJ_AMT;
	}
	public void setPR_REJ_AMT(String pR_REJ_AMT) {
		PR_REJ_AMT = pR_REJ_AMT;
	}
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getSelperiod() {
		return selperiod;
	}
	public void setSelperiod(String selperiod) {
		this.selperiod = selperiod;
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
	public String getDispType() {
		return dispType;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public void setDispType(String dispType) {
		this.dispType = dispType;
	}
	public String getCASE_DATE() {
		return CASE_DATE;
	}
	public void setCASE_DATE(String cASE_DATE) {
		CASE_DATE = cASE_DATE;
	}
	public String getCASE_ID() {
		return CASE_ID;
	}
	public void setCASE_ID(String cASE_ID) {
		CASE_ID = cASE_ID;
	}
	
	public String getHOSP_ID() {
		return HOSP_ID;
	}
	public void setHOSP_ID(String hOSP_ID) {
		HOSP_ID = hOSP_ID;
	}
	public String getHOSP_NAME() {
		return HOSP_NAME;
	}
	public void setHOSP_NAME(String hOSP_NAME) {
		HOSP_NAME = hOSP_NAME;
	}
	public String getPARTICULARS() {
		return PARTICULARS;
	}
	public void setPARTICULARS(String pARTICULARS) {
		PARTICULARS = pARTICULARS;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPanelDocPmntStatus() {
		return panelDocPmntStatus;
	}
	public void setPanelDocPmntStatus(String panelDocPmntStatus) {
		this.panelDocPmntStatus = panelDocPmntStatus;
	}
	public String getDISTINCTSTATUS() {
		return DISTINCTSTATUS;
	}
	public void setDISTINCTSTATUS(String DISTINCTSTATUS) {
		this.DISTINCTSTATUS = DISTINCTSTATUS;
	}
	public String getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(String COUNT) {
		this.COUNT = COUNT;
	}
	public List<LabelBean> getGrpList() {
		return grpList;
	}
	public void setGrpList(List<LabelBean> grpList) {
		this.grpList = grpList;
	}
	public String getLEVELLIST() {
		return LEVELLIST;
	}
	public void setLEVELLIST(String lEVELLIST) {
		LEVELLIST = lEVELLIST;
	}
	public String getGROUPNAME() {
		return GROUPNAME;
	}
	public void setGROUPNAME(String gROUPNAME) {
		GROUPNAME = gROUPNAME;
	}
	public String getGRPID() {
		return GRPID;
	}
	public void setGRPID(String gRPID) {
		GRPID = gRPID;
	}
	public String getPrevGrpName() {
		return prevGrpName;
	}
	public void setPrevGrpName(String prevGrpName) {
		this.prevGrpName = prevGrpName;
	}
	public String getCurGrpName() {
		return curGrpName;
	}
	public void setCurGrpName(String curGrpName) {
		this.curGrpName = curGrpName;
	}
	public String getCurLevel() {
		return curLevel;
	}
	public void setCurLevel(String curLevel) {
		this.curLevel = curLevel;
	}
	public String getNextLevel() {
		return nextLevel;
	}
	public void setNextLevel(String nextLevel) {
		this.nextLevel = nextLevel;
	}
	public String getCurrGrpId() {
		return currGrpId;
	}
	public void setCurrGrpId(String currGrpId) {
		this.currGrpId = currGrpId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCURRWRKFLWID() {
		return CURRWRKFLWID;
	}
	public void setCURRWRKFLWID(String cURRWRKFLWID) {
		CURRWRKFLWID = cURRWRKFLWID;
	}
	
	public String getPrevWrkflowId() {
		return prevWrkflowId;
	}
	public void setPrevWrkflowId(String prevWrkflowId) {
		this.prevWrkflowId = prevWrkflowId;
	}
	public String getPrevOwnr() {
		return prevOwnr;
	}
	public void setPrevOwnr(String prevOwnr) {
		this.prevOwnr = prevOwnr;
	}
	public String getCurrWrkflowId() {
		return currWrkflowId;
	}
	public void setCurrWrkflowId(String currWrkflowId) {
		this.currWrkflowId = currWrkflowId;
	}
	public String getCurrOwnr() {
		return currOwnr;
	}
	public void setCurrOwnr(String currOwnr) {
		this.currOwnr = currOwnr;
	}
	public String getTdsActive() {
		return tdsActive;
	}
	public void setTdsActive(String tdsActive) {
		this.tdsActive = tdsActive;
	}
	public String getCaseSetId() {
		return caseSetId;
	}
	public void setCaseSetId(String caseSetId) {
		this.caseSetId = caseSetId;
	}
	public String getTdsPaymentId() {
		return tdsPaymentId;
	}
	public void setTdsPaymentId(String tdsPaymentId) {
		this.tdsPaymentId = tdsPaymentId;
	}
	public String getSRCACCNO() {
		return SRCACCNO;
	}
	public void setSRCACCNO(String sRCACCNO) {
		SRCACCNO = sRCACCNO;
	}
	public String getDESTACCNO() {
		return DESTACCNO;
	}
	public void setDESTACCNO(String dESTACCNO) {
		DESTACCNO = dESTACCNO;
	}
	public String getTDSID() {
		return TDSID;
	}
	public void setTDSID(String tDSID) {
		TDSID = tDSID;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getFailedList() {
		return failedList;
	}
	public void setFailedList(String failedList) {
		this.failedList = failedList;
	}
	public String getREGCODE() {
		return REGCODE;
	}
	public void setREGCODE(String rEGCODE) {
		REGCODE = rEGCODE;
	}
	public String getWRKFLWSETID() {
		return WRKFLWSETID;
	}
	public void setWRKFLWSETID(String wRKFLWSETID) {
		WRKFLWSETID = wRKFLWSETID;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFILEID() {
		return FILEID;
	}
	public void setFILEID(String fILEID) {
		FILEID = fILEID;
	}
	public String getPaymentUID() {
		return paymentUID;
	}
	public void setPaymentUID(String paymentUID) {
		this.paymentUID = paymentUID;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCURROWNR() {
		return CURROWNR;
	}
	public void setCURROWNR(String cURROWNR) {
		CURROWNR = cURROWNR;
	}
	public String getSCHEME() {
		return SCHEME;
	}
	public void setSCHEME(String sCHEME) {
		SCHEME = sCHEME;
	}
	public String getMONTH() {
		return MONTH;
	}
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
	
	public void setTOTAL_PNLDOC_AMT(Long tOTAL_PNLDOC_AMT) {
		TOTAL_PNLDOC_AMT = tOTAL_PNLDOC_AMT;
	}

	public Number getCLAIM_APRV_AMT() {
		return CLAIM_APRV_AMT;
	}
	public void setCLAIM_APRV_AMT(Number cLAIM_APRV_AMT) {
		CLAIM_APRV_AMT = cLAIM_APRV_AMT;
	}
	public Number getCLAIM_REJ_AMT() {
		return CLAIM_REJ_AMT;
	}
	public void setCLAIM_REJ_AMT(Number cLAIM_REJ_AMT) {
		CLAIM_REJ_AMT = cLAIM_REJ_AMT;
	}
	public Number getCLAIM_PEND_AMT() {
		return CLAIM_PEND_AMT;
	}
	public void setCLAIM_PEND_AMT(Number cLAIM_PEND_AMT) {
		CLAIM_PEND_AMT = cLAIM_PEND_AMT;
	}
	public Number getPREAUTH_APRV_AMT() {
		return PREAUTH_APRV_AMT;
	}
	public void setPREAUTH_APRV_AMT(Number pREAUTH_APRV_AMT) {
		PREAUTH_APRV_AMT = pREAUTH_APRV_AMT;
	}
	public Number getPREAUTH_REJ_AMT() {
		return PREAUTH_REJ_AMT;
	}
	public void setPREAUTH_REJ_AMT(Number pREAUTH_REJ_AMT) {
		PREAUTH_REJ_AMT = pREAUTH_REJ_AMT;
	}
	public Number getPREAUTH_PEND_AMT() {
		return PREAUTH_PEND_AMT;
	}
	public void setPREAUTH_PEND_AMT(Number pREAUTH_PEND_AMT) {
		PREAUTH_PEND_AMT = pREAUTH_PEND_AMT;
	}
	public Number getTOTAL_PNLDOC_AMT() {
		return TOTAL_PNLDOC_AMT;
	}
	public void setTOTAL_PNLDOC_AMT(Number tOTAL_PNLDOC_AMT) {
		TOTAL_PNLDOC_AMT = tOTAL_PNLDOC_AMT;
	}
	public Number getWID() {
		return WID;
	}
	public void setWID(Number wID) {
		WID = wID;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public String getLastYear() {
		return lastYear;
	}
	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}
	public Number getIDNUM() {
		return IDNUM;
	}
	public void setIDNUM(Number iDNUM) {
		IDNUM = iDNUM;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCeoRemarks() {
		return ceoRemarks;
	}
	public void setCeoRemarks(String ceoRemarks) {
		this.ceoRemarks = ceoRemarks;
	}
	public String getCeoRemarksDoc() {
		return ceoRemarksDoc;
	}
	public void setCeoRemarksDoc(String ceoRemarksDoc) {
		this.ceoRemarksDoc = ceoRemarksDoc;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getPaymentStatusSelected() {
		return paymentStatusSelected;
	}
	public void setPaymentStatusSelected(String paymentStatusSelected) {
		this.paymentStatusSelected = paymentStatusSelected;
	}
	public String getSentBackSearch() {
		return sentBackSearch;
	}
	public void setSentBackSearch(String sentBackSearch) {
		this.sentBackSearch = sentBackSearch;
	}
	public String getSentBack() {
		return sentBack;
	}
	public void setSentBack(String sentBack) {
		this.sentBack = sentBack;
	}
	public String getSentToUser() {
		return sentToUser;
	}
	public void setSentToUser(String sentToUser) {
		this.sentToUser = sentToUser;
	}
	public String getSentToSec() {
		return sentToSec;
	}
	public void setSentToSec(String sentToSec) {
		this.sentToSec = sentToSec;
	}
	public String getPREVWRKFLWID() {
		return PREVWRKFLWID;
	}
	public void setPREVWRKFLWID(String pREVWRKFLWID) {
		PREVWRKFLWID = pREVWRKFLWID;
	}

	
	
}
