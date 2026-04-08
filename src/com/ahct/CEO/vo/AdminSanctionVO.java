package com.ahct.CEO.vo;

import java.util.Date;

public class AdminSanctionVO {
	private String sanctionId;
	private String issuingAuthority;
	private String issuingAuthorityName;
	 private Date date;
	 private String subject;
	 private String deptName;
	 private String reference;
	 private String scheme;
	 private String sanctionAmount;
	 private String detailedHead;
	 private String detailedHeadName; 
	 private String subHead;
	 private String headId;
	 private String subHeadName;
	 private String majorHead;
	 private String majorHeadName;
	 private String sourceOfBudget; 
	 private String sourceCode;
	 
	 private Date sanctionOrderDate; 
	 private Date purchaseDate;
	 private String inspectionAuthority;
	 private String executingAuthority;
	 private String vendorType;
	 private String vendorName;
	 private String vendorCode;
	 private String toBeIssuedOn;
	 private String typoOfSanction;
	 private String specification;
	 private String cost;
	private String prevOwner;
	private String currentOwner;
	    private String workFlowName;
	    private String workFlowId;
	    private String grpId;
	    private String currentWorkFlowId;
	    private String prevWorkFlowId;
	 private String buttonValue;
	private Date crtDt;
     private String crtUsr;
   	 private Date lstUpdDt;
	 private String lstUpdUsr;
	private String adminSancRemarks;
	private String status;
	private Long reqAmt;
	
	private String SANCTIONID;
	private String SUBJECT;
	private String SANCAMOUNT;
	private String ACCCODE;
	private String VENDORNAME;
	
	
	
	 public String getSUBJECT() {
		return SUBJECT;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public String getSANCAMOUNT() {
		return SANCAMOUNT;
	}
	public void setSANCAMOUNT(String sANCAMOUNT) {
		SANCAMOUNT = sANCAMOUNT;
	}
	public String getACCCODE() {
		return ACCCODE;
	}
	public void setACCCODE(String aCCCODE) {
		ACCCODE = aCCCODE;
	}
	public String getVENDORNAME() {
		return VENDORNAME;
	}
	public void setVENDORNAME(String vENDORNAME) {
		VENDORNAME = vENDORNAME;
	}
	public String getCurrentOwner() {
			return currentOwner;
		}
		public void setCurrentOwner(String currentOwner) {
			this.currentOwner = currentOwner;
		}
		public String getWorkFlowName() {
			return workFlowName;
		}
		public void setWorkFlowName(String workFlowName) {
			this.workFlowName = workFlowName;
		}
		public String getWorkFlowId() {
			return workFlowId;
		}
		public void setWorkFlowId(String workFlowId) {
			this.workFlowId = workFlowId;
		}
		public String getGrpId() {
			return grpId;
		}
		public void setGrpId(String grpId) {
			this.grpId = grpId;
		}
		public String getCurrentWorkFlowId() {
			return currentWorkFlowId;
		}
		public void setCurrentWorkFlowId(String currentWorkFlowId) {
			this.currentWorkFlowId = currentWorkFlowId;
		}
		public String getPrevWorkFlowId() {
			return prevWorkFlowId;
		}
		public void setPrevWorkFlowId(String prevWorkFlowId) {
			this.prevWorkFlowId = prevWorkFlowId;
		}
	 public String getIssuingAuthority() {
			return issuingAuthority;
		}
		public void setIssuingAuthority(String issuingAuthority) {
			this.issuingAuthority = issuingAuthority;
		}
		public String getIssuingAuthorityName() {
			return issuingAuthorityName;
		}
		public void setIssuingAuthorityName(String issuingAuthorityName) {
			this.issuingAuthorityName = issuingAuthorityName;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public String getScheme() {
			return scheme;
		}
		public void setScheme(String scheme) {
			this.scheme = scheme;
		}
		public String getSanctionAmount() {
			return sanctionAmount;
		}
		public void setSanctionAmount(String sanctionAmount) {
			this.sanctionAmount = sanctionAmount;
		}
		public String getDetailedHead() {
			return detailedHead;
		}
		public void setDetailedHead(String detailedHead) {
			this.detailedHead = detailedHead;
		}
		public String getDetailedHeadName() {
			return detailedHeadName;
		}
		public void setDetailedHeadName(String detailedHeadName) {
			this.detailedHeadName = detailedHeadName;
		}
		public String getSubHead() {
			return subHead;
		}
		public void setSubHead(String subHead) {
			this.subHead = subHead;
		}
		public String getSubHeadName() {
			return subHeadName;
		}
		public void setSubHeadName(String subHeadName) {
			this.subHeadName = subHeadName;
		}
		public String getMajorHead() {
			return majorHead;
		}
		public void setMajorHead(String majorHead) {
			this.majorHead = majorHead;
		}
		public String getMajorHeadName() {
			return majorHeadName;
		}
		public void setMajorHeadName(String majorHeadName) {
			this.majorHeadName = majorHeadName;
		}
		public String getSourceOfBudget() {
			return sourceOfBudget;
		}
		public void setSourceOfBudget(String sourceOfBudget) {
			this.sourceOfBudget = sourceOfBudget;
		}
		public String getSourceCode() {
			return sourceCode;
		}
		public void setSourceCode(String sourceCode) {
			this.sourceCode = sourceCode;
		}
		public Date getSanctionOrderDate() {
			return sanctionOrderDate;
		}
		public void setSanctionOrderDate(Date sanctionOrderDate) {
			this.sanctionOrderDate = sanctionOrderDate;
		}
		public Date getPurchaseDate() {
			return purchaseDate;
		}
		public void setPurchaseDate(Date purchaseDate) {
			this.purchaseDate = purchaseDate;
		}
		public String getInspectionAuthority() {
			return inspectionAuthority;
		}
		public void setInspectionAuthority(String inspectionAuthority) {
			this.inspectionAuthority = inspectionAuthority;
		}
		public String getExecutingAuthority() {
			return executingAuthority;
		}
		public void setExecutingAuthority(String executingAuthority) {
			this.executingAuthority = executingAuthority;
		}
		public String getVendorType() {
			return vendorType;
		}
		public void setVendorType(String vendorType) {
			this.vendorType = vendorType;
		}
		public String getVendorName() {
			return vendorName;
		}
		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}
		public String getVendorCode() {
			return vendorCode;
		}
		public void setVendorCode(String vendorCode) {
			this.vendorCode = vendorCode;
		}
		public String getToBeIssuedOn() {
			return toBeIssuedOn;
		}
		public void setToBeIssuedOn(String toBeIssuedOn) {
			this.toBeIssuedOn = toBeIssuedOn;
		}
		public String getTypoOfSanction() {
			return typoOfSanction;
		}
		public void setTypoOfSanction(String typoOfSanction) {
			this.typoOfSanction = typoOfSanction;
		}
		public String getSpecification() {
			return specification;
		}
		public void setSpecification(String specification) {
			this.specification = specification;
		}
		public String getCost() {
			return cost;
		}
		public void setCost(String cost) {
			this.cost = cost;
		}
		public void setHeadId(String headId) {
			this.headId = headId;
		}
		public String getHeadId() {
			return headId;
		}
		public String getSanctionId() {
			return sanctionId;
		}
		public void setSanctionId(String sanctionId) {
			this.sanctionId = sanctionId;
		}
		public Date getCrtDt() {
			return crtDt;
		}
		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}
		public String getCrtUsr() {
			return crtUsr;
		}
		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}
		public Date getLstUpdDt() {
			return lstUpdDt;
		}
		public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;
		}
		public String getLstUpdUsr() {
			return lstUpdUsr;
		}
		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}
		public void setButtonValue(String buttonValue) {
			this.buttonValue = buttonValue;
		}
		public String getButtonValue() {
			return buttonValue;
		}
		public String getAdminSancRemarks() {
			return adminSancRemarks;
		}
		public void setAdminSancRemarks(String adminSancRemarks) {
			this.adminSancRemarks = adminSancRemarks;
		}
		public String getPrevOwner() {
			return prevOwner;
		}
		public void setPrevOwner(String prevOwner) {
			this.prevOwner = prevOwner;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Long getReqAmt() {
			return reqAmt;
		}
		public void setReqAmt(Long reqAmt) {
			this.reqAmt = reqAmt;
		}
		public String getSANCTIONID() {
			return SANCTIONID;
		}
		public void setSANCTIONID(String sANCTIONID) {
			SANCTIONID = sANCTIONID;
		}
}
