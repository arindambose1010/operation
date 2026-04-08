package com.ahct.PanelDoc.VO;

import java.util.List;

public class panelDocVo {

	private String userId;
	private String empName;
	private String loginName;
	private String accountNum;
	private String bankName;
	private String bankId;
	private String branchName;
	private String branchId;
	private String ifscCode;
	private String pan;
	private String panName;
	private String crtUsr;
	private String State;
	
	private String rowsPerPage;
	private int startIndex;
	private int maxResults;
	private String showPage;
	private String totalRows;
	
	private List<panelDocVo> panelDocLst;
	private long count;
	private long NEWCOUNT;
	
	
	private String USERID;
	private String EMPLOYEENAME;
	private String LOGINNAME;
	private String ACCOUNTNUMBER;
	private String BANKNAME;
	private String BANKID;
	private String CREATEUSER;
	private String CREATEDATE;
	private String LASTUPDTDUSER;
	private String LASTUPDATEDDATE;
	
	public String getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(String cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	public String getLASTUPDTDUSER() {
		return LASTUPDTDUSER;
	}
	public void setLASTUPDTDUSER(String lASTUPDTDUSER) {
		LASTUPDTDUSER = lASTUPDTDUSER;
	}
	public String getLASTUPDATEDDATE() {
		return LASTUPDATEDDATE;
	}
	public void setLASTUPDATEDDATE(String lASTUPDATEDDATE) {
		LASTUPDATEDDATE = lASTUPDATEDDATE;
	}

	
		
	public long getNEWCOUNT() {
		return NEWCOUNT;
	}
	public void setNEWCOUNT(long nEWCOUNT) {
		NEWCOUNT = nEWCOUNT;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getEMPLOYEENAME() {
		return EMPLOYEENAME;
	}
	public void setEMPLOYEENAME(String eMPLOYEENAME) {
		EMPLOYEENAME = eMPLOYEENAME;
	}
	public String getLOGINNAME() {
		return LOGINNAME;
	}
	public void setLOGINNAME(String lOGINNAME) {
		LOGINNAME = lOGINNAME;
	}
	public String getACCOUNTNUMBER() {
		return ACCOUNTNUMBER;
	}
	public void setACCOUNTNUMBER(String aCCOUNTNUMBER) {
		ACCOUNTNUMBER = aCCOUNTNUMBER;
	}
	public String getBANKNAME() {
		return BANKNAME;
	}
	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}
	public String getBANKID() {
		return BANKID;
	}
	public void setBANKID(String bANKID) {
		BANKID = bANKID;
	}
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

	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPanName() {
		return panName;
	}
	public void setPanName(String panName) {
		this.panName = panName;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCREATEUSER() {
		return CREATEUSER;
	}
	public void setCREATEUSER(String cREATEUSER) {
		CREATEUSER = cREATEUSER;
	}
	
	
	
}
