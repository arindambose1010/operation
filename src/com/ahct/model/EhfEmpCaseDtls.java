package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_EMP_CASE_DTLS")
public class EhfEmpCaseDtls implements Serializable {

	private String caseId;
    private String caseNo;
    private String casePatientNo;
    private String caseHospCode;
    private String cardType;
    private String cardNo;
    private String caseStatus;
    private Date caseRegnDate;
    private String employeeNo;
    private String relation;
    private String childYn;
    private String familyHead;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String deptHod;
    private String postDist;
    private String ddoCode;
    private String stoCode;
    
    
    @Id
    @Column(name="CASE_ID", nullable = false)
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    @Column(name="CASE_NO", nullable = false)
    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    @Column(name="CASE_PATIENT_NO")
    public String getCasePatientNo() {
        return casePatientNo;
    }

    public void setCasePatientNo(String casePatientNo) {
        this.casePatientNo = casePatientNo;
    }

    @Column(name="CASE_REGN_DATE")
    public Date getCaseRegnDate() {
        return caseRegnDate;
    }

    public void setCaseRegnDate(Date caseRegnDate) {
        this.caseRegnDate = caseRegnDate;
    }

    @Column(name="CASE_STATUS")
    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
    
    @Column(name="card_type", nullable = false)
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Column(name="EMPLOYEE_NO", nullable = false)
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	@Column(name="case_hosp_code")
	public String getCaseHospCode() {
		return caseHospCode;
	}
	public void setCaseHospCode(String caseHospCode) {
		this.caseHospCode = caseHospCode;
	}
	@Column(name="card_no", nullable = false)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Column(name="relation")
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Column(name="child_yn")
	public String getChildYn() {
		return childYn;
	}

	public void setChildYn(String childYn) {
		this.childYn = childYn;
	}
	
	@Column(name="family_head")
	public String getFamilyHead() {
		return familyHead;
	}

	public void setFamilyHead(String familyHead) {
		this.familyHead = familyHead;
	}
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR", nullable = false)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name="DEPT_HOD")
	public String getDeptHod() {
		return deptHod;
	}

	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}
	@Column(name="POST_DIST")
	public String getPostDist() {
		return postDist;
	}

	public void setPostDist(String postDist) {
		this.postDist = postDist;
	}
	@Column(name="DDO_CODE")
	public String getDdoCode() {
		return ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}
	@Column(name="STO_CODE")
	public String getStoCode() {
		return stoCode;
	}

	public void setStoCode(String stoCode) {
		this.stoCode = stoCode;
	}

}
