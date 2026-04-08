package com.ahct.panelDoctor.form;

import java.util.List;

import org.apache.struts.action.ActionForm;


import com.ahct.panelDoctor.vo.PanelDocPayVO;
import com.ahct.common.vo.LabelBean;


public class PanelDocPayForm extends ActionForm{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String paymentStatus;
	 private String dispType;
	 private String fromDate;
	 private String toDate;
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
	 private String caseStatus;
	 private String userType;
	 private String scheme;
	 private List<LabelBean> schemeList;
	 private String tdsStatus;
	 private List<LabelBean> tdsStatusList;
	 private Long wId;
	 private String checkAll;
	 private String individualPayment; 
	 private String sendBack;
	 private List<PanelDocPayVO> sentBackremarksLst;
	 private String ceoRemarksDoc;
	 private String docName;
	 private String paySent;
	 private String statusCeo;
	 private String ceoRemarks;
	 private String allDepts;
	 private String allUsers;
	 private List<PanelDocPayVO> pnlDocSpcLst;
	 private String pnlDocTotAmt;
	 private String userSendback;
	 
	public Long getwId() {
		return wId;
	}

	public void setwId(Long wId) {
		this.wId = wId;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSelPeriod() {
		return selPeriod;
	}

	public void setSelPeriod(String selPeriod) {
		this.selPeriod = selPeriod;
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
	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
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

	public List<PanelDocPayVO> getPanelDocList() {
		return PanelDocList;
	}

	public void setPanelDocList(List<PanelDocPayVO> panelDocList) {
		PanelDocList = panelDocList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<LabelBean> getMonthlist() {
		return monthlist;
	}

	public void setMonthlist(List<LabelBean> monthList2) {
		this.monthlist = monthList2;
	}

	public List<LabelBean> getYearlist() {
		return yearlist;
	}

	public void setYearlist(List<LabelBean> yearlist) {
		this.yearlist = yearlist;
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

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public List<LabelBean> getPaymentStatusList() {
		return paymentStatusList;
	}

	public void setPaymentStatusList(List<LabelBean> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
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

	public String getActionSelect() {
		return actionSelect;
	}

	public void setActionSelect(String actionSelect) {
		this.actionSelect = actionSelect;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
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

	public String getSendBack() {
		return sendBack;
	}

	public void setSendBack(String sendBack) {
		this.sendBack = sendBack;
	}

	public List<PanelDocPayVO> getSentBackremarksLst() {
		return sentBackremarksLst;
	}

	public void setSentBackremarksLst(List<PanelDocPayVO> sentBackremarksLst) {
		this.sentBackremarksLst = sentBackremarksLst;
	}

	public String getCeoRemarksDoc() {
		return ceoRemarksDoc;
	}

	public void setCeoRemarksDoc(String ceoRemarksDoc) {
		this.ceoRemarksDoc = ceoRemarksDoc;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getPaySent() {
		return paySent;
	}

	public void setPaySent(String paySent) {
		this.paySent = paySent;
	}

	public String getStatusCeo() {
		return statusCeo;
	}

	public void setStatusCeo(String statusCeo) {
		this.statusCeo = statusCeo;
	}

	public String getCeoRemarks() {
		return ceoRemarks;
	}

	public void setCeoRemarks(String ceoRemarks) {
		this.ceoRemarks = ceoRemarks;
	}

	public String getAllDepts() {
		return allDepts;
	}

	public void setAllDepts(String allDepts) {
		this.allDepts = allDepts;
	}

	public String getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(String allUsers) {
		this.allUsers = allUsers;
	}

	public List<PanelDocPayVO> getPnlDocSpcLst() {
		return pnlDocSpcLst;
	}

	public void setPnlDocSpcLst(List<PanelDocPayVO> pnlDocSpcLst) {
		this.pnlDocSpcLst = pnlDocSpcLst;
	}

	public String getPnlDocTotAmt() {
		return pnlDocTotAmt;
	}

	public void setPnlDocTotAmt(String pnlDocTotAmt) {
		this.pnlDocTotAmt = pnlDocTotAmt;
	}

	public String getUserSendback() {
		return userSendback;
	}

	public void setUserSendback(String userSendback) {
		this.userSendback = userSendback;
	}

	
		
}
