package com.ahct.CEO.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;




public class ChangeMgmtVO implements Serializable
{
    private String crId = null;
    private String crAppName = null;
    private String crOrgName = null;
    private String crType = null;
    private String crReqTypeId = null;
    private String crWorkflowId = null;
    private String deptId = null;
    private String subMenuName = null;
    private String crTitle = null;
    private String crDesc = null;
    private String status = null;
    private String empId = null;
    private String currOwnerName = null;
    private String prevOwnerUnitId = null;
    private String currOwnerUnitId = null;
    private String nextOwnerUnitId = null;
    private String[] fileDetailsArray = null;
    private String langId;
    private String actionTaken;
    private String eOfficeDeptHrchy;
    private String asriDeptHrchy;
    private String currDeptId;
    private String currLgsb;
    private String eOfficeLgsbSequence;
    private String asriLgsbSequence;
    private String actionFlag;
    private String remarks;
    private String actionTakenBy;
    private String actionTakenTime;
    private String prevLgsb;
    private String prevDept;
    private String sourceDeptName;
    private String crOrgFullName;
    private String crReqTypeName;
    private String crPriorityId;
    private String crAsignedEmpId;
    private Date crRequiredDate;
    private String crSelAssignUnit;
    private String attachFileName;
    private String lineItemNo;
    private String crSeverityId=null;
    private Date crExpectDeliveryDt;
    private String assignedEmpName;
    private String subCRTitle=null;
    private String subCRDesc=null ;
    private String subCRChangeType=null; 
    private String returnStatus = null;
    private String subCRId;
    private String parentCrId;
    private String crtUsr;
    private String crtDt;
    private String TempCrCeoAprvFlag;
    private String testScenDesc;
    private String[] testScenResults;
    private Long sno;
    private String crWorkStatusId;
    private String crBuildId;
    private String uatDept;
    private String externalStatus;
    private String attachExist;
    private String buildDate;
    private String buildDesc=null;
    private String buildID=null;
    private String active=null;
    private String searchCategory= null;
    private String searchText=null;
    private String searchType=null;
    private String inputFromDt=null;
    private String inputToDt=null;
    private String sectionId=null; //lgsb  
    private String buildEditFlag=null;
    private String fromLgsb=null;
    private String moduleId;
    private String employeeType=null;
    private String typeOfReport=null;
    private String dsgnId=null;
    private String closeIssueFlag=null;
    private String subDeptCode=null;
 
    private String effortForAnalysis=null;
    private String effortForDesign=null;
    private String effortForCoding=null;
    private String effortForUnitTesting=null;
    private String effortForSystemTesting=null;
    private String effortTotalHrs=null;
    private String caseId=null;
    private String caseNo=null;
    private String depttype=null;

    private String userType=null;

    
    public void setCrAppName(String crAppName) {
        this.crAppName = crAppName;
    }

    public String getCrAppName() {
        return crAppName;
    }

    public void setCrOrgName(String crOrgName) {
        this.crOrgName = crOrgName;
    }

    public String getCrOrgName() {
        return crOrgName;
    }

    public void setCrType(String crType) {
        this.crType = crType;
    }

    public String getCrType() {
        return crType;
    }

    public void setCrReqTypeId(String crReqTypeId) {
        this.crReqTypeId = crReqTypeId;
    }

    public String getCrReqTypeId() {
        return crReqTypeId;
    }

    public void setCrWorkflowId(String crWorkflowId) {
        this.crWorkflowId = crWorkflowId;
    }

    public String getCrWorkflowId() {
        return crWorkflowId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setCrTitle(String crTitle) {
        this.crTitle = crTitle;
    }

    public String getCrTitle() {
        return crTitle;
    }

    public void setCrDesc(String crDesc) {
        this.crDesc = crDesc;
    }

    public String getCrDesc() {
        return crDesc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPrevOwnerUnitId(String prevOwnerUnitId) {
        this.prevOwnerUnitId = prevOwnerUnitId;
    }

    public String getPrevOwnerUnitId() {
        return prevOwnerUnitId;
    }

    public void setCurrOwnerUnitId(String currOwnerUnitId) {
        this.currOwnerUnitId = currOwnerUnitId;
    }

    public String getCurrOwnerUnitId() {
        return currOwnerUnitId;
    }

    public void setNextOwnerUnitId(String nextOwnerUnitId) {
        this.nextOwnerUnitId = nextOwnerUnitId;
    }

    public String getNextOwnerUnitId() {
        return nextOwnerUnitId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setCrId(String crId) {
        this.crId = crId;
    }

    public String getCrId() {
        return crId;
    }

    public void setFileDetailsArray(String[] fileDetailsArray) {
        this.fileDetailsArray = fileDetailsArray;
    }

    public String[] getFileDetailsArray() {
        return fileDetailsArray;
    }

    public void setCurrOwnerName(String currOwnerName) {
        this.currOwnerName = currOwnerName;
    }

    public String getCurrOwnerName() {
        return currOwnerName;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getLangId() {
        return langId;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setEOfficeDeptHrchy(String eOfficeDeptHrchy) {
        this.eOfficeDeptHrchy = eOfficeDeptHrchy;
    }

    public String getEOfficeDeptHrchy() {
        return eOfficeDeptHrchy;
    }

    public void setAsriDeptHrchy(String asriDeptHrchy) {
        this.asriDeptHrchy = asriDeptHrchy;
    }

    public String getAsriDeptHrchy() {
        return asriDeptHrchy;
    }

    public void setCurrDeptId(String currDeptId) {
        this.currDeptId = currDeptId;
    }

    public String getCurrDeptId() {
        return currDeptId;
    }

    public void setCurrLgsb(String currLgsb) {
        this.currLgsb = currLgsb;
    }

    public String getCurrLgsb() {
        return currLgsb;
    }

    public void setEOfficeLgsbSequence(String eOfficeLgsbSequence) {
        this.eOfficeLgsbSequence = eOfficeLgsbSequence;
    }

    public String getEOfficeLgsbSequence() {
        return eOfficeLgsbSequence;
    }

    public void setAsriLgsbSequence(String asriLgsbSequence) {
        this.asriLgsbSequence = asriLgsbSequence;
    }

    public String getAsriLgsbSequence() {
        return asriLgsbSequence;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setActionTakenBy(String actionTakenBy) {
        this.actionTakenBy = actionTakenBy;
    }

    public String getActionTakenBy() {
        return actionTakenBy;
    }

    public void setActionTakenTime(String actionTakenTime) {
        this.actionTakenTime = actionTakenTime;
    }

    public String getActionTakenTime() {
        return actionTakenTime;
    }

    public void setPrevLgsb(String prevLgsb) {
        this.prevLgsb = prevLgsb;
    }

    public String getPrevLgsb() {
        return prevLgsb;
    }

    public void setPrevDept(String prevDept) {
        this.prevDept = prevDept;
    }

    public String getPrevDept() {
        return prevDept;
    }

    public void setSourceDeptName(String sourceDeptName) {
        this.sourceDeptName = sourceDeptName;
    }

    public String getSourceDeptName() {
        return sourceDeptName;
    }

    public void setCrOrgFullName(String crOrgFullName) {
        this.crOrgFullName = crOrgFullName;
    }

    public String getCrOrgFullName() {
        return crOrgFullName;
    }

    public void setCrReqTypeName(String crReqTypeName) {
        this.crReqTypeName = crReqTypeName;
    }

    public String getCrReqTypeName() {
        return crReqTypeName;
    }

    public void setCrPriorityId(String crPriorityId) {
        this.crPriorityId = crPriorityId;
    }

    public String getCrPriorityId() {
        return crPriorityId;
    }

    public void setCrAsignedEmpId(String crAsignedEmpId) {
        this.crAsignedEmpId = crAsignedEmpId;
    }

    public String getCrAsignedEmpId() {
        return crAsignedEmpId;
    }

    public void setCrRequiredDate(Date crRequiredDate) {
        this.crRequiredDate = crRequiredDate;
    }

    public Date getCrRequiredDate() {
        return crRequiredDate;
    }

    public void setCrSelAssignUnit(String crSelAssignUnit) {
        this.crSelAssignUnit = crSelAssignUnit;
    }

    public String getCrSelAssignUnit() {
        return crSelAssignUnit;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName;
    }

    public String getAttachFileName() {
        return attachFileName;
    }


    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setCrSeverityId(String crSeverityId) {
        this.crSeverityId = crSeverityId;
    }

    public String getCrSeverityId() {
        return crSeverityId;
    }

    public void setCrExpectDeliveryDt(Date crExpectDeliveryDt) {
        this.crExpectDeliveryDt = crExpectDeliveryDt;
    }

    public Date getCrExpectDeliveryDt() {
        return crExpectDeliveryDt;
    }

    public void setAssignedEmpName(String assignedEmpName) {
        this.assignedEmpName = assignedEmpName;
    }

    public String getAssignedEmpName() {
        return assignedEmpName;
    }

    public void setSubCRTitle(String subCRTitle) {
        this.subCRTitle = subCRTitle;
    }

    public String getSubCRTitle() {
        return subCRTitle;
    }

    public void setSubCRDesc(String subCRDesc) {
        this.subCRDesc = subCRDesc;
    }

    public String getSubCRDesc() {
        return subCRDesc;
    }

    public void setSubCRChangeType(String subCRChangeType) {
        this.subCRChangeType = subCRChangeType;
    }

    public String getSubCRChangeType() {
        return subCRChangeType;
    }

    public void setSubCRId(String subCRId) {
        this.subCRId = subCRId;
    }

    public String getSubCRId() {
        return subCRId;
    }

    public void setParentCrId(String parentCrId) {
        this.parentCrId = parentCrId;
    }

    public String getParentCrId() {
        return parentCrId;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }

    public String getCrtDt() {
        return crtDt;
    }

    public void setTempCrCeoAprvFlag(String tempCrCeoAprvFlag) {
        this.TempCrCeoAprvFlag = tempCrCeoAprvFlag;
    }

    public String getTempCrCeoAprvFlag() {
        return TempCrCeoAprvFlag;
    }

    public void setTestScenDesc(String testScenDesc) {
        this.testScenDesc = testScenDesc;
    }

    public String getTestScenDesc() {
        return testScenDesc;
    }

    public void setTestScenResults(String[] testScenResults) {
        this.testScenResults = testScenResults;
    }

    public String[] getTestScenResults() {
        return testScenResults;
    }

    public void setSno(Long sno) {
        this.sno = sno;
    }

    public Long getSno() {
        return sno;
    }


    public void setCrWorkStatusId(String crWorkStatusId) {
        this.crWorkStatusId = crWorkStatusId;
    }

    public String getCrWorkStatusId() {
        return crWorkStatusId;
    }

    public void setCrBuildId(String crBuildId) {
        this.crBuildId = crBuildId;
    }

    public String getCrBuildId() {
        return crBuildId;
    }

    public void setUatDept(String uatDept) {
        this.uatDept = uatDept;
    }

    public String getUatDept() {
        return uatDept;
    }

    public void setExternalStatus(String externalStatus) {
        this.externalStatus = externalStatus;
    }

    public String getExternalStatus() {
        return externalStatus;
    }

    public void setAttachExist(String attachExist) {
        this.attachExist = attachExist;
    }

    public String getAttachExist() {
        return attachExist;
    }

 
    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
    }

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildID(String buildID) {
        this.buildID = buildID;
    }

    public String getBuildID() {
        return buildID;
    }
    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActive() {
        return active;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setInputFromDt(String inputFromDt) {
        this.inputFromDt = inputFromDt;
    }

    public String getInputFromDt() {
        return inputFromDt;
    }

    public void setInputToDt(String inputToDt) {
        this.inputToDt = inputToDt;
    }

    public String getInputToDt() {
        return inputToDt;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setBuildEditFlag(String buildEditFlag) {
        this.buildEditFlag = buildEditFlag;
    }

    public String getBuildEditFlag() {
        return buildEditFlag;
    }

    public void setFromLgsb(String fromLgsb) {
        this.fromLgsb = fromLgsb;
    }

    public String getFromLgsb() {
        return fromLgsb;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeType() {
        return employeeType;
    }


    public void setTypeOfReport(String typeOfReport) {
        this.typeOfReport = typeOfReport;
    }

    public String getTypeOfReport() {
        return typeOfReport;
    }

    public void setDsgnId(String dsgnId) {
        this.dsgnId = dsgnId;
    }

    public String getDsgnId() {
        return dsgnId;
    }

    public void setCloseIssueFlag(String closeIssueFlag) {
        this.closeIssueFlag = closeIssueFlag;
    }

    public String getCloseIssueFlag() {
        return closeIssueFlag;
    }

    public void setSubDeptCode(String subDeptCode) {
        this.subDeptCode = subDeptCode;
    }

    public String getSubDeptCode() {
        return subDeptCode;
    }
    public void setEffortForAnalysis(String effortForAnalysis) {
		this.effortForAnalysis = effortForAnalysis;
	}

	public String getEffortForAnalysis() {
		return effortForAnalysis;
	}

	public void setEffortForDesign(String effortForDesign) {
		this.effortForDesign = effortForDesign;
	}

	public String getEffortForDesign() {
		return effortForDesign;
	}

	public void setEffortForCoding(String effortForCoding) {
		this.effortForCoding = effortForCoding;
	}

	public String getEffortForCoding() {
		return effortForCoding;
	}

	public void setEffortForUnitTesting(String effortForUnitTesting) {
		this.effortForUnitTesting = effortForUnitTesting;
	}

	public String getEffortForUnitTesting() {
		return effortForUnitTesting;
	}

	public void setEffortForSystemTesting(String effortForSystemTesting) {
		this.effortForSystemTesting = effortForSystemTesting;
	}

	public String getEffortForSystemTesting() {
		return effortForSystemTesting;
	}

	public void setEffortTotalHrs(String effortTotalHrs) {
		this.effortTotalHrs = effortTotalHrs;
	}

	public String getEffortTotalHrs() {
		return effortTotalHrs;
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
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDepttype() {
		return depttype;
	}

	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}

}
