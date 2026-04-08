package com.ahct.annualCheckUp.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;

public class AhcClaimsForm extends ActionForm {
		private String ahcId;
		private String packageAmt;
		private String regDate;
		private String claimDt;
		private String billAmt;
		private String billAmt1;
		private String billDt;
		private List<ClaimsFlowVO> lstworkFlow;
		private ClaimsFlowVO claimInfo;
		private String msg;
		private String remarks;
		private String status;
		private String medcoRemarks;
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
		private String cardNo;
		private String name;
		private String fromDate;
		private String toDate;
		private String scheme;
		private String showScheme;
		private List<LabelBean> casesForPaymentList;
		private String genFlag;
		private String roleId;
		private String[] caseSelected;
		private String chApproveAmt;
		private String[] testCheck;
		private FormFile[] investAttach=new FormFile[100];
		private String AHCCEXRemarks;
		private String AHCCTDRemarks;
		private String AHCCHRemarks;
		private String AHCCHAmt;
		private String AHCCNAMRemarks;
		private String AHCACORemarks;
		private String medcoCTDUpdRemarks;
		private String medcoCHUpdRemarks;
		
		
		
		
		public FormFile getInvestAttach(int index)
		{
			return investAttach[index];
		}
	public void setInvestAttach(int index,FormFile value)
		{
			investAttach[index]=value;
		}
		public String[] getTestCheck() {
			return testCheck;
		}
		public void setTestCheck(String[] testCheck) {
			this.testCheck = testCheck;
		}
		public String getChApproveAmt() {
			return chApproveAmt;
		}
		public void setChApproveAmt(String chApproveAmt) {
			this.chApproveAmt = chApproveAmt;
		}
		public String[] getCaseSelected() {
			return caseSelected;
		}
		public void setCaseSelected(String[] caseSelected) {
			this.caseSelected = caseSelected;
		}
		public String getRoleId() {
			return roleId;
		}
		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}
		public String getGenFlag() {
			return genFlag;
		}
		public void setGenFlag(String genFlag) {
			this.genFlag = genFlag;
		}
		public String getScheme() {
			return scheme;
		}
		public void setScheme(String scheme) {
			this.scheme = scheme;
		}
		public String getShowScheme() {
			return showScheme;
		}
		public void setShowScheme(String showScheme) {
			this.showScheme = showScheme;
		}
		public String getCardNo() {
			return cardNo;
		}
		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getMedcoRemarks() {
			return medcoRemarks;
		}
		public void setMedcoRemarks(String medcoRemarks) {
			this.medcoRemarks = medcoRemarks;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAhcId() {
			return ahcId;
		}
		public void setAhcId(String ahcId) {
			this.ahcId = ahcId;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public ClaimsFlowVO getClaimInfo() {
			return claimInfo;
		}
		public void setClaimInfo(ClaimsFlowVO claimInfo) {
			this.claimInfo = claimInfo;
		}
		public List<ClaimsFlowVO> getLstworkFlow() {
			return lstworkFlow;
		}
		public void setLstworkFlow(List<ClaimsFlowVO> lstworkFlow) {
			this.lstworkFlow = lstworkFlow;
		}
		public String getPackageAmt() {
			return packageAmt;
		}
		public void setPackageAmt(String packageAmt) {
			this.packageAmt = packageAmt;
		}
		public String getRegDate() {
			return regDate;
		}
		public void setRegDate(String regDate) {
			this.regDate = regDate;
		}
		public String getClaimDt() {
			return claimDt;
		}
		public void setClaimDt(String claimDt) {
			this.claimDt = claimDt;
		}
		public String getBillAmt() {
			return billAmt;
		}
		public void setBillAmt(String billAmt) {
			this.billAmt = billAmt;
		}
		
		public String getBillAmt1() {
			return billAmt1;
		}
		public void setBillAmt1(String billAmt1) {
			this.billAmt1 = billAmt1;
		}
		public String getBillDt() {
			return billDt;
		}
		public void setBillDt(String billDt) {
			this.billDt = billDt;
		}
		public List<LabelBean> getCasesForPaymentList() {
			return casesForPaymentList;
		}
		public void setCasesForPaymentList(List<LabelBean> casesForPaymentList) {
			this.casesForPaymentList = casesForPaymentList;
		}
		public String getAHCCEXRemarks() {
			return AHCCEXRemarks;
		}
		public void setAHCCEXRemarks(String aHCCEXRemarks) {
			AHCCEXRemarks = aHCCEXRemarks;
		}
		public String getAHCCTDRemarks() {
			return AHCCTDRemarks;
		}
		public void setAHCCTDRemarks(String aHCCTDRemarks) {
			AHCCTDRemarks = aHCCTDRemarks;
		}
		public String getAHCCHRemarks() {
			return AHCCHRemarks;
		}
		public void setAHCCHRemarks(String aHCCHRemarks) {
			AHCCHRemarks = aHCCHRemarks;
		}
		public String getAHCCNAMRemarks() {
			return AHCCNAMRemarks;
		}
		public void setAHCCNAMRemarks(String aHCCNAMRemarks) {
			AHCCNAMRemarks = aHCCNAMRemarks;
		}
		public String getAHCACORemarks() {
			return AHCACORemarks;
		}
		public void setAHCACORemarks(String aHCACORemarks) {
			AHCACORemarks = aHCACORemarks;
		}
		public String getAHCCHAmt() {
			return AHCCHAmt;
		}
		public void setAHCCHAmt(String aHCCHAmt) {
			AHCCHAmt = aHCCHAmt;
		}
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
		
		
		
		
}
