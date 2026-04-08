package com.ahct.flagging.form;

import java.util.List;
import com.ahct.flagging.vo.FlaggingVO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FlaggingForm extends ActionForm
{
	private String flagNature;
	private String flagStatus;
	private String flagRemarks;
	private String amount;
	private String amountRefund;
	private FormFile doc[]=new FormFile[5];
	private String attachVal; 
	private String fdf;
	private String deFlagId;
	private String caseId;
	private String caseId1;
	private String caseIdS;
	private String fromDt;
	private String toDt;
	private List<FlaggingVO> lstFlaggedCases;
	private String flagged;
	private String cardNo;
	private String patDist;
	private String hospType;
	private String hospDist;
	private String hospName;
	private String patState;
	private String hospState;
	private String authority;
	private String flaggedCasesForApproval;
	private String loggedUsrGrp;
	private String newFlag;
	private String mainCatName;
	private String catName;
	private String procName;
	private String patStateType;
	
	
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public FormFile getDoc(int index) {
		return doc[index];
	}
	public void setDoc(int index,FormFile value) {
		doc[index] = value;
	}
	public String getAttachVal() {
		return attachVal;
	}
	public void setAttachVal(String attachVal) {
		this.attachVal = attachVal;
	}
	public String getFdf() {
		return fdf;
	}
	public void setFdf(String fdf) {
		this.fdf = fdf;
	}
	public String getDeFlagId() {
		return deFlagId;
	}
	public void setDeFlagId(String deFlagId) {
		this.deFlagId = deFlagId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
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
	public List<FlaggingVO> getLstFlaggedCases() {
		return lstFlaggedCases;
	}
	public void setLstFlaggedCases(List<FlaggingVO> lstFlaggedCases) {
		this.lstFlaggedCases = lstFlaggedCases;
	}
	public String getFlagged() {
		return flagged;
	}
	public void setFlagged(String flagged) {
		this.flagged = flagged;
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
	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	public String getHospDist() {
		return hospDist;
	}
	public void setHospDist(String hospDist) {
		this.hospDist = hospDist;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
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
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getFlaggedCasesForApproval() {
		return flaggedCasesForApproval;
	}
	public void setFlaggedCasesForApproval(String flaggedCasesForApproval) {
		this.flaggedCasesForApproval = flaggedCasesForApproval;
	}
	public String getLoggedUsrGrp() {
		return loggedUsrGrp;
	}
	public void setLoggedUsrGrp(String loggedUsrGrp) {
		this.loggedUsrGrp = loggedUsrGrp;
	}
	public String getAmountRefund() {
		return amountRefund;
	}
	public void setAmountRefund(String amountRefund) {
		this.amountRefund = amountRefund;
	}
	public String getNewFlag() {
		return newFlag;
	}
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}
	public String getCaseId1() {
		return caseId1;
	}
	public void setCaseId1(String caseId1) {
		this.caseId1 = caseId1;
	}
	public String getCaseIdS() {
		return caseIdS;
	}
	public void setCaseIdS(String caseIdS) {
		this.caseIdS = caseIdS;
	}
	public String getPatStateType() {
		return patStateType;
	}
	public void setPatStateType(String patStateType) {
		this.patStateType = patStateType;
	}
	
	
	
	
}
