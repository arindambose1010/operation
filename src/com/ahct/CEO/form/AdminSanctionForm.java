package com.ahct.CEO.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.CEO.vo.AdminSanctionRemarksVO;
import com.ahct.CEO.vo.AdminSanctionVO;


public class AdminSanctionForm extends ActionForm{
	
	private String sanctionId;
	private String issuingAuthority;
	private String issuingAuthorityName;
	 private String date;
	 private String subject;
	 private String deptName;
	 private String reference;
	 private String scheme;
	 private String sanctionAmount;
	 private String detailedHead;
	 private String detailedHeadName; 
	 private String subHead;
	 
	 private String subHeadName;
	 private String majorHead;
	 private String majorHeadName;
	 private String sourceOfBudget; 
	 private String sourceCode;
	 
	 private String sanctionOrderDate; 
	 private String purchaseDate;
	 private String inspectionAuthority;
	 private String executingAuthority;
	 private String vendorType;
	 private String vendorName;
	 private String vendorCode;
	 private String toBeIssuedOn;
	 private String typoOfSanction;
	 private String specification;
	 private String cost;
	 private String userId;
	 private String showBack;
	 private String attachFlag;
	 private List<AdminSanctionVO> adminSancList;
	private List<AdminSanctionRemarksVO> remarksList;
	    private String actionTaken;
	    private String adminSancRemarks;
	    private String specFlag;
	    private String vName;
	    private String sourceName;
	    private String reqStatus;
	    private String reqRaisedDtTo;
	    private String reqRaisedDtFrm;
	    private Long amtSanc;
	    private String statusFlag;
	    
	    private String isSelected;
      private String NoofRecords;
      private String TotalRecords;
      private String SANCTIONID;
      private int showPage;
      private int startPage;
      private int endPage;
      private int rowsPerPage;
     private String prev;
     private String next;
     
     
	    
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
		public int getShowPage() {
		return showPage;
	}
	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}
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
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
		public List<AdminSanctionRemarksVO> getRemarksList() {
				return remarksList;
			}
			public void setRemarksList(List<AdminSanctionRemarksVO> remarksList) {
				this.remarksList = remarksList;
			}
			public String getActionTaken() {
				return actionTaken;
			}
			public void setActionTaken(String actionTaken) {
				this.actionTaken = actionTaken;
			}
			public String getAdminSancRemarks() {
				return adminSancRemarks;
			}
			public void setAdminSancRemarks(String adminSancRemarks) {
				this.adminSancRemarks = adminSancRemarks;
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
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
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
		public String getSanctionOrderDate() {
			return sanctionOrderDate;
		}
		public void setSanctionOrderDate(String sanctionOrderDate) {
			this.sanctionOrderDate = sanctionOrderDate;
		}
		public String getPurchaseDate() {
			return purchaseDate;
		}
		public void setPurchaseDate(String purchaseDate) {
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
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		public String getDeptName() {
			return deptName;
		}
		public String getSanctionId() {
			return sanctionId;
		}
		public void setSanctionId(String sanctionId) {
			this.sanctionId = sanctionId;
		}
		public String getSpecFlag() {
			return specFlag;
		}
		public void setSpecFlag(String specFlag) {
			this.specFlag = specFlag;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public List<AdminSanctionVO> getAdminSancList() {
			return adminSancList;
		}
		public void setAdminSancList(List<AdminSanctionVO> adminSancList) {
			this.adminSancList = adminSancList;
		}
		public String getShowBack() {
			return showBack;
		}
		public void setShowBack(String showBack) {
			this.showBack = showBack;
		}
		public String getAttachFlag() {
			return attachFlag;
		}
		public void setAttachFlag(String attachFlag) {
			this.attachFlag = attachFlag;
		}
		public void setvName(String vName) {
			this.vName = vName;
		}
		public String getvName() {
			return vName;
		}
		public String getSourceName() {
			return sourceName;
		}
		public void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}
		public String getReqStatus() {
			return reqStatus;
		}
		public void setReqStatus(String reqStatus) {
			this.reqStatus = reqStatus;
		}
		public String getReqRaisedDtTo() {
			return reqRaisedDtTo;
		}
		public void setReqRaisedDtTo(String reqRaisedDtTo) {
			this.reqRaisedDtTo = reqRaisedDtTo;
		}
		
		public Long getAmtSanc() {
			return amtSanc;
		}
		public void setAmtSanc(Long amtSanc) {
			this.amtSanc = amtSanc;
		}
		public String getReqRaisedDtFrm() {
			return reqRaisedDtFrm;
		}
		public void setReqRaisedDtFrm(String reqRaisedDtFrm) {
			this.reqRaisedDtFrm = reqRaisedDtFrm;
		}
		public String getStatusFlag() {
			return statusFlag;
		}
		public void setStatusFlag(String statusFlag) {
			this.statusFlag = statusFlag;
		}
		public String getNoofRecords() {
			return NoofRecords;
		}
		public void setNoofRecords(String noofRecords) {
			NoofRecords = noofRecords;
		}
		public String getIsSelected() {
			return isSelected;
		}
		public void setIsSelected(String isSelected) {
			this.isSelected = isSelected;
		}
		public String getTotalRecords() {
			return TotalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			TotalRecords = totalRecords;
		}
		public String getSANCTIONID() {
			return SANCTIONID;
		}
		public void setSANCTIONID(String sANCTIONID) {
			SANCTIONID = sANCTIONID;
		}
		
	
	 
}
