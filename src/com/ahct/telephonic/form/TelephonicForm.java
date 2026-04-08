package com.ahct.telephonic.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.telephonic.vo.TelephonicVO;

public class TelephonicForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String rowsPerPage;
	private String startIndex;
	private String showPage;
	private String searchFlag;
	private String totRowCount;
	private String next;
	private String prev;
	private String endIndex;
	private String totalRows;
	private List<TelephonicVO> lstCaseSearch;
	private String teleStatus;
	private String telephonicId;
	private String healthCardNo;
	private String patientName;
	private String crtDt;
	private String callerName;
	private String callerMobileNo;
	private String locName;
	private String hospName;
	private String icdCatName;
	private String procName;
	private String doctorName;
	private String mobileNo;
	private String cardType;
	private String fromDate;
	private String toDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getShowPage() {
		return showPage;
	}
	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getTotRowCount() {
		return totRowCount;
	}
	public void setTotRowCount(String totRowCount) {
		this.totRowCount = totRowCount;
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
	public List<TelephonicVO> getLstCaseSearch() {
		return lstCaseSearch;
	}
	public void setLstCaseSearch(List<TelephonicVO> lstCaseSearch) {
		this.lstCaseSearch = lstCaseSearch;
	}
	public String getTeleStatus() {
		return teleStatus;
	}
	public void setTeleStatus(String teleStatus) {
		this.teleStatus = teleStatus;
	}
	public String getTelephonicId() {
		return telephonicId;
	}
	public void setTelephonicId(String telephonicId) {
		this.telephonicId = telephonicId;
	}
	public String getHealthCardNo() {
		return healthCardNo;
	}
	public void setHealthCardNo(String healthCardNo) {
		this.healthCardNo = healthCardNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getCallerName() {
		return callerName;
	}
	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}
	public String getCallerMobileNo() {
		return callerMobileNo;
	}
	public void setCallerMobileNo(String callerMobileNo) {
		this.callerMobileNo = callerMobileNo;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
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
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	
}
