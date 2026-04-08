package com.ahct.CEO.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.CEO.vo.SQLChangeMgmtTransVO;
import com.ahct.CEO.vo.ChangeMgmtVO;

public class CeoApprovalsForm extends ActionForm{
		 private String crOrgName = null;
	     private String crType = null;
	     private String crReqTypeId = null;
	     private String crTitle = null;
	     private String crDesc = null; 
	     private String crAppName = null;
     	 private List<SQLChangeMgmtTransVO> crViewDetails=null;            
	     private String crId=null;
	     private String remarks=null;
		 private String crOrgFullName=null;
		 private String crRequiredDate=null;
		 private String crWorkFlowId=null;
		 private String crWorkStatusId;
		 private String status=null;
		 private String saveRemarksFlag=null;
		 private String caseId= null;
		 private String caseNo= null;
	 	private List<SQLChangeMgmtTransVO> myCrRqstAprvlList=null;
	 	private int  startPage;
		private int endPage;
		private int startIndex;
		private int endIndex;
		private String rowsPerPage;
		private String showPage;
		private String next;
		private String prev;
		 private String crPriorityId=null;
		 private List<ChangeMgmtVO> attachVOList=null;
		 private String addAttachFlag;
		 private List<SQLChangeMgmtTransVO> signOffActionRemarkList=null;
		 private List<SQLChangeMgmtTransVO> otherActionRemarkList=null;
		  private String msg=null;
		  private String sourceDeptName=null;

		  
		  
		  
	public String getCrOrgName() {
			return crOrgName;
		}

		public void setCrOrgName(String crOrgName) {
			this.crOrgName = crOrgName;
		}

		public String getCrType() {
			return crType;
		}

		public void setCrType(String crType) {
			this.crType = crType;
		}

		public String getCrReqTypeId() {
			return crReqTypeId;
		}

		public void setCrReqTypeId(String crReqTypeId) {
			this.crReqTypeId = crReqTypeId;
		}

		public String getCrTitle() {
			return crTitle;
		}

		public void setCrTitle(String crTitle) {
			this.crTitle = crTitle;
		}

		public String getCrDesc() {
			return crDesc;
		}

		public void setCrDesc(String crDesc) {
			this.crDesc = crDesc;
		}

		public String getCrAppName() {
			return crAppName;
		}

		public void setCrAppName(String crAppName) {
			this.crAppName = crAppName;
		}

		public List<SQLChangeMgmtTransVO> getCrViewDetails() {
			return crViewDetails;
		}

		public void setCrViewDetails(List<SQLChangeMgmtTransVO> crViewDetails) {
			this.crViewDetails = crViewDetails;
		}

		public String getCrId() {
			return crId;
		}

		public void setCrId(String crId) {
			this.crId = crId;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public String getCrOrgFullName() {
			return crOrgFullName;
		}

		public void setCrOrgFullName(String crOrgFullName) {
			this.crOrgFullName = crOrgFullName;
		}

		public String getCrRequiredDate() {
			return crRequiredDate;
		}

		public void setCrRequiredDate(String crRequiredDate) {
			this.crRequiredDate = crRequiredDate;
		}

		public String getCrWorkFlowId() {
			return crWorkFlowId;
		}

		public void setCrWorkFlowId(String crWorkFlowId) {
			this.crWorkFlowId = crWorkFlowId;
		}

		public String getCrWorkStatusId() {
			return crWorkStatusId;
		}

		public void setCrWorkStatusId(String crWorkStatusId) {
			this.crWorkStatusId = crWorkStatusId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getSaveRemarksFlag() {
			return saveRemarksFlag;
		}

		public void setSaveRemarksFlag(String saveRemarksFlag) {
			this.saveRemarksFlag = saveRemarksFlag;
		}

		public String getCaseId() {
			return caseId;
		}

		public void setCaseId(String caseId) {
			this.caseId = caseId;
		}

		public String getCaseNo() {
			return caseNo;
		}

		public void setCaseNo(String caseNo) {
			this.caseNo = caseNo;
		}

		public String getCrPriorityId() {
			return crPriorityId;
		}

		public void setCrPriorityId(String crPriorityId) {
			this.crPriorityId = crPriorityId;
		}

		public List<ChangeMgmtVO> getAttachVOList() {
			return attachVOList;
		}

		public void setAttachVOList(List<ChangeMgmtVO> attachVOList) {
			this.attachVOList = attachVOList;
		}

		public String getAddAttachFlag() {
			return addAttachFlag;
		}

		public void setAddAttachFlag(String addAttachFlag) {
			this.addAttachFlag = addAttachFlag;
		}

		public List<SQLChangeMgmtTransVO> getSignOffActionRemarkList() {
			return signOffActionRemarkList;
		}

		public void setSignOffActionRemarkList(
				List<SQLChangeMgmtTransVO> signOffActionRemarkList) {
			this.signOffActionRemarkList = signOffActionRemarkList;
		}

		public List<SQLChangeMgmtTransVO> getOtherActionRemarkList() {
			return otherActionRemarkList;
		}

		public void setOtherActionRemarkList(
				List<SQLChangeMgmtTransVO> otherActionRemarkList) {
			this.otherActionRemarkList = otherActionRemarkList;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getSourceDeptName() {
			return sourceDeptName;
		}

		public void setSourceDeptName(String sourceDeptName) {
			this.sourceDeptName = sourceDeptName;
		}

	public List<SQLChangeMgmtTransVO> getMyCrRqstAprvlList() {
		return myCrRqstAprvlList;
	}

	public void setMyCrRqstAprvlList(List<SQLChangeMgmtTransVO> myCrRqstAprvlList) {
		this.myCrRqstAprvlList = myCrRqstAprvlList;
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

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getShowPage() {
		return showPage;
	}

	public void setShowPage(String showPage) {
		this.showPage = showPage;
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
	

}
