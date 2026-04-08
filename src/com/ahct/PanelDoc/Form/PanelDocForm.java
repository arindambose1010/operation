package com.ahct.PanelDoc.Form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.PanelDoc.VO.panelDocVo;

public class PanelDocForm extends ActionForm {

	
	private String empName;
	private String loginName;
	private String loginNameTemp;
	private String accountNum;
	private String bankName;
	private String bankId;
	private String branchName;
	private String branchId;
	private String ifscCode;
	private String pan;
	private String panName;
	private String userId;
	
	private String rowsPerPage;
	private String startIndex;
	private String showPage;
	private String totalRows;
	private String endIndex;
	private String result;
	
	List<panelDocVo> panelDocLst=new ArrayList<panelDocVo>();
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
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
	public String getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
	public List<panelDocVo> getPanelDocLst() {
		return panelDocLst;
	}
	public void setPanelDocLst(List<panelDocVo> panelDocLst) {
		this.panelDocLst = panelDocLst;
	}
	public String getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}
	public String getPanName() {
		return panName;
	}
	public void setPanName(String panName) {
		this.panName = panName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginNameTemp() {
		return loginNameTemp;
	}
	public void setLoginNameTemp(String loginNameTemp) {
		this.loginNameTemp = loginNameTemp;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
