package com.ahct.model;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "EHF_JRNLSTD_CLAIM_AUDIT")
public class EhfJrnlstdClaimAudit {
    @EmbeddedId
    private EhfJrnlstDClaimAuditPK id;
    
    @Column(name = "PATIENT_ID")
    private String patientId;
    
    @Column(name = "CLAIM_STATUS", length = 20)
    private String claimStatus;
    
    @Column(name = "REMARKS", length = 2000)
    private String remarks;
    
    @Column(name = "ACT_BY", length = 20)
    private String actBy;
    
    @Column(name = "CRT_USR", length = 20)
    private String crtUsr;
    
    @Column(name = "CRT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDt;
    
    @Column(name = "LST_UPD_USR", length = 20)
    private String lstUpdUsr;
    
    @Column(name = "LST_UPD_DT")
    @Temporal(TemporalType.DATE)
    private Date lstUpdDt;
    
    @Column(name = "CLAIM_AMOUNT")
    private Double claimAmount;
    
    @Column(name = "USER_ROLE")
    private String userRole;

    // Getters and Setters
    public EhfJrnlstDClaimAuditPK getId() {
        return id;
    }
    public void setId(EhfJrnlstDClaimAuditPK id) {
        this.id = id;
    }
    
    public String getClaimStatus() {
        return claimStatus;
    }
    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
    
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getActBy() {
        return actBy;
    }
    public void setActBy(String actBy) {
        this.actBy = actBy;
    }
    
    public String getCrtUsr() {
        return crtUsr;
    }
    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    
    public Date getCrtDt() {
        return crtDt;
    }
    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }
    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    
    public Date getLstUpdDt() {
        return lstUpdDt;
    }
    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    
    public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
