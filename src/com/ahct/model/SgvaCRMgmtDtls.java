package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="SGVA_CRMGMT_DTLS")
public class SgvaCRMgmtDtls implements java.io.Serializable
{
    public SgvaCRMgmtDtls() {}
    
    private String crId;
    private String crAppName;
    private String crOrgName;
    private String crType;
    private String crReqTypeId;
    private String crWorkflowId;
    private String deptId;
    private String empId;
    private String moduleId;
    private String subMenuName;
    private String crTitle;
    private String crDesc;
    private String status;
    private String prevOwnerUnitId;
    private String currOwnerUnitId;
    private String nextOwnerUnitId;
    private String active;
    private String currLogicalSubject;
    private String currDeptId;
    private String workStatus;
    private Date crRaisedDate;
    private Date crRequiredDate;
    private Date crtDate;
    private String crtUser;
    private Date lastUpdDate;
    private String lastUpdUser;
    private String crPriority;
    private String crAssignedEmpId;
    private String crSeverity;
    private Date crExpectedDeliveryDt;
    private String parentCrId;
    private String crBuildId;
    private String crExternalStatus;
    private String caseId;
    private String caseNo;
    private String userType;

    public void setCrId(String crId) {
        this.crId = crId;
    }

    @Id
    @Column(name="CR_ID", length=200, nullable=false)
    public String getCrId() {
        return crId;
    }

    public void setCrAppName(String crAppName) {
        this.crAppName = crAppName;
    }

    @Column(name="CR_APPNAME", length=50)
    public String getCrAppName() {
        return crAppName;
    }

    public void setCrOrgName(String crOrgName) {
        this.crOrgName = crOrgName;
    }

    @Column(name="CR_ORGNAME", length=50)
    public String getCrOrgName() {
        return crOrgName;
    }

    public void setCrType(String crType) {
        this.crType = crType;
    }

    @Column(name="CR_TYPE", length=50)
    public String getCrType() {
        return crType;
    }

    public void setCrReqTypeId(String crReqTypeId) {
        this.crReqTypeId = crReqTypeId;
    }

    @Column(name="CR_REQTYPE_ID", length=50)
    public String getCrReqTypeId() {
        return crReqTypeId;
    }

    public void setCrWorkflowId(String crWorkflowId) {
        this.crWorkflowId = crWorkflowId;
    }

    @Column(name="CR_WORKFLOW_ID", length=50)
    public String getCrWorkflowId() {
        return crWorkflowId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name="CREATED_DEPTID", length=20)
    public String getDeptId() {
        return deptId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Column(name="EMP_ID", length=12)
    public String getEmpId() {
        return empId;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    @Column(name="SUBMENU_NAME", length=100)
    public String getSubMenuName() {
        return subMenuName;
    }

    public void setCrTitle(String crTitle) {
        this.crTitle = crTitle;
    }

    @Column(name="CR_TITLE", length=1000)
    public String getCrTitle() {
        return crTitle;
    }

    public void setCrDesc(String crDesc) {
        this.crDesc = crDesc;
    }

    @Column(name="CR_DESC", length=4000)
    public String getCrDesc() {
        return crDesc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="STATUS", length=1000)
    public String getStatus() {
        return status;
    }

    public void setPrevOwnerUnitId(String prevOwnerUnitId) {
        this.prevOwnerUnitId = prevOwnerUnitId;
    }

    @Column(name="PREV_OWNR_UNITID", length=200)
    public String getPrevOwnerUnitId() {
        return prevOwnerUnitId;
    }

    public void setCurrOwnerUnitId(String currOwnerUnitId) {
        this.currOwnerUnitId = currOwnerUnitId;
    }

    @Column(name="CURR_OWNR_UNITID", length=200)
    public String getCurrOwnerUnitId() {
        return currOwnerUnitId;
    }

    public void setNextOwnerUnitId(String nextOwnerUnitId) {
        this.nextOwnerUnitId = nextOwnerUnitId;
    }

    @Column(name="NEXT_OWNR_UNITID", length=200)
    public String getNextOwnerUnitId() {
        return nextOwnerUnitId;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", length=7)
    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    @Column(name="CRT_USR", length=10)
    public String getCrtUser() {
        return crtUser;
    }

    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT", length=7)
    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }

    @Column(name="LST_UPD_USR", length=10)
    public String getLastUpdUser() {
        return lastUpdUser;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Column(name="ACTIVE", length=5)
    public String getActive() {
        return active;
    }

    public void setCurrLogicalSubject(String currLogicalSubject) {
        this.currLogicalSubject = currLogicalSubject;
    }

    @Column(name="CURR_LGSB", length=50)
    public String getCurrLogicalSubject() {
        return currLogicalSubject;
    }

    public void setCurrDeptId(String currDeptId) {
        this.currDeptId = currDeptId;
    }

    @Column(name="CURR_DEPT_ID", length=20)
    public String getCurrDeptId() {
        return currDeptId;
    }
    
    public void setCrRaisedDate(Date crRaisedDate) {
        this.crRaisedDate = crRaisedDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CR_RAISED_DATE", length=7)
    public Date getCrRaisedDate() {
        return crRaisedDate;
    }

    public void setCrRequiredDate(Date crRequiredDate) {
        this.crRequiredDate = crRequiredDate;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CR_REQUIRED_DATE", length=7)
    public Date getCrRequiredDate() {
        return crRequiredDate;
    }

    public void setCrPriority(String crPriority) {
        this.crPriority = crPriority;
    }

    @Column(name="crPriority", length=50)
    public String getCrPriority() {
        return crPriority;
    }

    public void setCrAssignedEmpId(String crAssignedEmpId) {
        this.crAssignedEmpId = crAssignedEmpId;
    }

    @Column(name="crAssignedEmpId", length=50)
    public String getCrAssignedEmpId() {
        return crAssignedEmpId;
    }

    public void setCrSeverity(String crSeverity) {
        this.crSeverity = crSeverity;
    }

    @Column(name="crSeverity", length=50)
    public String getCrSeverity() {
        return crSeverity;
    }
    
    public void setCrExpectedDeliveryDt(Date crExpectedDeliveryDt) {
        this.crExpectedDeliveryDt = crExpectedDeliveryDt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="cr_Expect_DeliveryDt", length=7)
    public Date getCrExpectedDeliveryDt() {
        return crExpectedDeliveryDt;
    }

    public void setParentCrId(String parentCrId) {
        this.parentCrId = parentCrId;
    }

    @Column(name="parentCrId", length=200)
    public String getParentCrId() {
            return parentCrId;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
    
    @Column(name="work_status", length=50)
    public String getWorkStatus() {
        return workStatus;
    }

    public void setCrBuildId(String crBuildId) {
        this.crBuildId = crBuildId;
    }

    @Column(name="CR_BUILD_ID", length=20)
    public String getCrBuildId() {
        return crBuildId;
    }

    public void setCrExternalStatus(String crExternalStatus) {
        this.crExternalStatus = crExternalStatus;
    }
    
    @Column(name="external_status" , length=300)
    public String getCrExternalStatus() {
        return crExternalStatus;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name="moduleId", length=100)
    public String getModuleId() {
        return moduleId;
    }
    @Column(name="case_id", length=12)
	public String getCaseId() {
		return caseId;
	}
	
	 public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column(name="case_no", length=20)
	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	@Column(name="SCHEME_ID", length=20)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
