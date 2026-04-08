package com.ahct.flagging.vo;

import java.util.Date;
import java.util.List;
import com.ahct.flagging.vo.FlaggingVO;

public class FlaggingVO
{
	private String flagDocId;
	private String flagId;
	private String deFlagId;
	private String caseId;
	private String flagNature;
	private String flagStatus;
	private String currentStatus;
	private String flagRemarks;
	private long amount;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private long actionOrder;
	private long maxActionOrder;
	private String doc[];
	private String ID;
	private String ID1;
	private String firstName;
	private String middleName;
	private String lastName;
	private String role;
	private String attachPath;
	private String flagNatureId;
	private String fromDt;
	private String toDt;
	private int start_index;
	private int end_index;
	private double dateDif;
	private String fdf;
	private String dist;
	private String hospName;
	private String cardNo;
	private String patDist;
	private String hospDist;
	private char hospType;
	private String patState;
	private String hospState;
	private String loggedUsrGrp;
	private String authority;
	private Number FLAGGEDCASES;
	private String flagColour;
	private String flaggedCasesForApproval;
	private String loggedUsrId;
	private long amountRef;
	private long AMOUNTREF;
	private String fileDocArr[];
	private String locId;
	private String langId;
	private String gmOpFlag;
	private String actId;
	private String flagged;
	
	private String FLAGID;
	private String CASEID;
	private String FLAGNATURE;
	private String FLAGSTATUS;
	private Long AMOUNT;
	private String LOCID;
	private String LANGID;
	private String CRTUSR;
	private String CRTDT;
	private String LSTUPDUSR;
	private String LSTUPDDT;
	private String GMOPFLAG;
	private String schemeId;
	private String dcId;
	private String dmId;
	private String tlId;
	private String hospId;

	private List<FlaggingVO> flaggingVOLst;
	private String mainCatName;
	private String catName;
	private String procName;
	 
	
	
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
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	public String getGmOpFlag() {
		return gmOpFlag;
	}
	public void setGmOpFlag(String gmOpFlag) {
		this.gmOpFlag = gmOpFlag;
	}
	public String[] getFileDocArr() {
		return fileDocArr;
	}
	public void setFileDocArr(String[] fileDocArr) {
		this.fileDocArr = fileDocArr;
	}
	public String getFlagNature()
	{
		return flagNature;
	}
	public void setFlagNature(String flagNature)
	{
		this.flagNature=flagNature;
	}
	public String getFlagStatus() {
		return flagStatus;
	}
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}
	public String getFlagRemarks() {
		return flagRemarks;
	}
	public void setFlagRemarks(String flagRemarks) {
		this.flagRemarks = flagRemarks;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String[] getDoc() {
		return doc;
	}
	public void setDoc(String[] doc) {
		this.doc = doc;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getFlagId() {
		return flagId;
	}
	public void setFlagId(String flagId) {
		this.flagId = flagId;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public String getFlagDocId() {
		return flagDocId;
	}
	public void setFlagDocId(String flagDocId) {
		this.flagDocId = flagDocId;
	}
	public long getActionOrder() {
		return actionOrder;
	}
	public void setActionOrder(long actionOrder) {
		this.actionOrder = actionOrder;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getID1() {
		return ID1;
	}
	public void setID1(String iD1) {
		ID1 = iD1;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getFlagNatureId() {
		return flagNatureId;
	}
	public void setFlagNatureId(String flagNatureId) {
		this.flagNatureId = flagNatureId;
	}
	public String getDeFlagId() {
		return deFlagId;
	}
	public void setDeFlagId(String deFlagId) {
		this.deFlagId = deFlagId;
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
	public String getFromDt() {
		return fromDt;
	}
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	public String getFdf() {
		return fdf;
	}
	public void setFdf(String fdf) {
		this.fdf = fdf;
	}
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	public long getMaxActionOrder() {
		return maxActionOrder;
	}
	public void setMaxActionOrder(long maxActionOrder) {
		this.maxActionOrder = maxActionOrder;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPatDist() {
		return patDist;
	}
	public void setPatDist(String patDist) {
		this.patDist = patDist;
	}
	public String getHospDist() {
		return hospDist;
	}
	public void setHospDist(String hospDist) {
		this.hospDist = hospDist;
	}
	public char getHospType() {
		return hospType;
	}
	public void setHospType(char hospType) {
		this.hospType = hospType;
	}
	public String getPatState() {
		return patState;
	}
	public void setPatState(String patState) {
		this.patState = patState;
	}
	public String getHospState() {
		return hospState;
	}
	public void setHospState(String hospState) {
		this.hospState = hospState;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getLoggedUsrGrp() {
		return loggedUsrGrp;
	}
	public void setLoggedUsrGrp(String loggedUsrGrp) {
		this.loggedUsrGrp = loggedUsrGrp;
	}
	public double getDateDif() {
		return dateDif;
	}
	public void setDateDif(double dateDif) {
		this.dateDif = dateDif;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Number getFLAGGEDCASES() {
		return FLAGGEDCASES;
	}
	public void setFLAGGEDCASES(Number fLAGGEDCASES) {
		FLAGGEDCASES = fLAGGEDCASES;
	}
	public String getFlagColour() {
		return flagColour;
	}
	public void setFlagColour(String flagColour) {
		this.flagColour = flagColour;
	}
	public String getFlaggedCasesForApproval() {
		return flaggedCasesForApproval;
	}
	public void setFlaggedCasesForApproval(String flaggedCasesForApproval) {
		this.flaggedCasesForApproval = flaggedCasesForApproval;
	}
	public String getLoggedUsrId() {
		return loggedUsrId;
	}
	public void setLoggedUsrId(String loggedUsrId) {
		this.loggedUsrId = loggedUsrId;
	}
	public long getAmountRef() {
		return amountRef;
	}
	public void setAmountRef(long amountRef) {
		this.amountRef = amountRef;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getFLAGID() {
		return FLAGID;
	}
	public void setFLAGID(String fLAGID) {
		FLAGID = fLAGID;
	}
	public String getCASEID() {
		return CASEID;
	}
	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}
	public String getFLAGNATURE() {
		return FLAGNATURE;
	}
	public void setFLAGNATURE(String fLAGNATURE) {
		FLAGNATURE = fLAGNATURE;
	}
	public String getFLAGSTATUS() {
		return FLAGSTATUS;
	}
	public void setFLAGSTATUS(String fLAGSTATUS) {
		FLAGSTATUS = fLAGSTATUS;
	}
	public Long getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Long aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getLOCID() {
		return LOCID;
	}
	public void setLOCID(String lOCID) {
		LOCID = lOCID;
	}
	public String getLANGID() {
		return LANGID;
	}
	public void setLANGID(String lANGID) {
		LANGID = lANGID;
	}
	public String getCRTUSR() {
		return CRTUSR;
	}
	public void setCRTUSR(String cRTUSR) {
		CRTUSR = cRTUSR;
	}
	public String getCRTDT() {
		return CRTDT;
	}
	public void setCRTDT(String cRTDT) {
		CRTDT = cRTDT;
	}
	public String getLSTUPDUSR() {
		return LSTUPDUSR;
	}
	public void setLSTUPDUSR(String lSTUPDUSR) {
		LSTUPDUSR = lSTUPDUSR;
	}
	public String getLSTUPDDT() {
		return LSTUPDDT;
	}
	public void setLSTUPDDT(String lSTUPDDT) {
		LSTUPDDT = lSTUPDDT;
	}
	public String getGMOPFLAG() {
		return GMOPFLAG;
	}
	public void setGMOPFLAG(String gMOPFLAG) {
		GMOPFLAG = gMOPFLAG;
	}
	public long getAMOUNTREF() {
		return AMOUNTREF;
	}
	public void setAMOUNTREF(long aMOUNTREF) {
		AMOUNTREF = aMOUNTREF;
	}
	public String getFlagged() {
		return flagged;
	}
	public void setFlagged(String flagged) {
		this.flagged = flagged;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getDcId() {
		return dcId;
	}
	public void setDcId(String dcId) {
		this.dcId = dcId;
	}
	public String getDmId() {
		return dmId;
	}
	public void setDmId(String dmId) {
		this.dmId = dmId;
	}
	public String getTlId() {
		return tlId;
	}
	public void setTlId(String tlId) {
		this.tlId = tlId;
	}
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	public List<FlaggingVO> getFlaggingVOLst() {
		return flaggingVOLst;
	}
	public void setFlaggingVOLst(List<FlaggingVO> flaggingVOLst) {
		this.flaggingVOLst = flaggingVOLst;
	}
	
	
	
	
	
	
	
	
}