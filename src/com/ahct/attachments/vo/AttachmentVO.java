package com.ahct.attachments.vo;

import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

public class AttachmentVO implements java.io.Serializable{
	private String actualName;
	  private String fileName;
	  private String savedName;
	  private String attachType;
	  private String crtRole;
	  private String crtUsrName;
	  private String remarks;
	  private String caseStat;
	  private String fileCrtTime;
	  private String attachVisibility;
	  private String attachMode;
	  private String attachDispType;
	  private FormFile fileAttach[];
	  private String empParentId;
	  private String cmbDtlID;
	  private String heading;
	  private String docType;
	  private String cmbprntId;
	  private String oldAttachType;
	  private Date crtDt;
	  private String attachDocSeqId;
	  private List<AttachmentVO> lstAttachments;
	  private String caseId;
	  private String surgeryId;
	  private String spltType;
	  private String surgInvstId;
	  private Long docCount;
	  private String filePath;
	  private String chronicId;
	  private String chronicNo;
	  private String installment;
	  private String followUpId;
	  private long docSeqId;
	  private String inst;
	  private String ACTUAL_NAME;  
	  private String attachPath;
	  private Integer actOrder;
	    
	
		public Integer getActOrder() {
		return actOrder;
	}
	public void setActOrder(Integer actOrder) {
		this.actOrder = actOrder;
	}
		public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
		public String getACTUAL_NAME() {
		return ACTUAL_NAME;
	}
	public void setACTUAL_NAME(String aCTUAL_NAME) {
		ACTUAL_NAME = aCTUAL_NAME;
	}
		public Long getDocCount() {
		return docCount;
	}
	public void setDocCount(Long docCount) {
		this.docCount = docCount;
	}
		public String getSurgInvstId() {
		return surgInvstId;
	}
	public void setSurgInvstId(String surgInvstId) {
		this.surgInvstId = surgInvstId;
	}
		public String getSurgeryId() {
		return surgeryId;
	}
	public void setSurgeryId(String surgeryId) {
		this.surgeryId = surgeryId;
	}
	public String getSpltType() {
		return spltType;
	}
	public void setSpltType(String spltType) {
		this.spltType = spltType;
	}
		public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
		public List<AttachmentVO> getLstAttachments() {
		return lstAttachments;
	}
	public void setLstAttachments(List<AttachmentVO> lstAttachments) {
		this.lstAttachments = lstAttachments;
	}
		public String getAttachDocSeqId() {
		return attachDocSeqId;
	}
	public void setAttachDocSeqId(String attachDocSeqId) {
		this.attachDocSeqId = attachDocSeqId;
	}
		public String getCmbDtlID() {
			return cmbDtlID;
		}
		public void setCmbDtlID(String cmbDtlID) {
			this.cmbDtlID = cmbDtlID;
		}
		public String getHeading() {
			return heading;
		}
		public void setHeading(String heading) {
			this.heading = heading;
		}
		public String getDocType() {
			return docType;
		}
		public void setDocType(String docType) {
			this.docType = docType;
		}
		public String getCmbprntId() {
			return cmbprntId;
		}
		public void setCmbprntId(String cmbprntId) {
			this.cmbprntId = cmbprntId;
		}
		
		public String getOldAttachType() {
			return oldAttachType;
		}
		public void setOldAttachType(String oldAttachType) {
			this.oldAttachType = oldAttachType;
		}
		
		public Date getCrtDt() {
			return crtDt;
		}
		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}
	  
	  
	public String getEmpParentId() {
		return empParentId;
	}
	public void setEmpParentId(String empParentId) {
		this.empParentId = empParentId;
	}
	public FormFile[] getFileAttach() {
		return fileAttach;
	}
	public void setFileAttach(FormFile[] fileAttach) {
		this.fileAttach = fileAttach;
	}
	public String getActualName() {
		return actualName;
	}
	public void setActualName(String actualName) {
		this.actualName = actualName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSavedName() {
		return savedName;
	}
	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}
	public String getAttachType() {
		return attachType;
	}
	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}
	public String getCrtRole() {
		return crtRole;
	}
	public void setCrtRole(String crtRole) {
		this.crtRole = crtRole;
	}
	public String getCrtUsrName() {
		return crtUsrName;
	}
	public void setCrtUsrName(String crtUsrName) {
		this.crtUsrName = crtUsrName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCaseStat() {
		return caseStat;
	}
	public void setCaseStat(String caseStat) {
		this.caseStat = caseStat;
	}
	public String getFileCrtTime() {
		return fileCrtTime;
	}
	public void setFileCrtTime(String fileCrtTime) {
		this.fileCrtTime = fileCrtTime;
	}
	public String getAttachVisibility() {
		return attachVisibility;
	}
	public void setAttachVisibility(String attachVisibility) {
		this.attachVisibility = attachVisibility;
	}
	public String getAttachMode() {
		return attachMode;
	}
	public void setAttachMode(String attachMode) {
		this.attachMode = attachMode;
	}
	public String getAttachDispType() {
		return attachDispType;
	}
	public void setAttachDispType(String attachDispType) {
		this.attachDispType = attachDispType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getChronicId() {
		return chronicId;
	}
	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}
	public String getChronicNo() {
		return chronicNo;
	}
	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	public String getFollowUpId() {
		return followUpId;
	}
	public void setFollowUpId(String followUpId) {
		this.followUpId = followUpId;
	}
	public long getDocSeqId() {
		return docSeqId;
	}
	public void setDocSeqId(long docSeqId) {
		this.docSeqId = docSeqId;
	}
	public String getInst() {
		return inst;
	}
	public void setInst(String inst) {
		this.inst = inst;
	}
	
	  
}
