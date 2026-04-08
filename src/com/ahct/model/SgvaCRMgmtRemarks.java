package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SGVA_CRMGMT_REMARKS")
public class SgvaCRMgmtRemarks implements java.io.Serializable
{
    public SgvaCRMgmtRemarks() {}
    
    private SgvaCRMgmtRemarksId id;
    private String empId;
    private String remarks ;
    private String actionTaken;
    private String active;
    private Date crtDate;
    private String crtUser;
    private Date lastUpdDate;
    private String lastUpdUser;   
    private String attachExists;
    
    @EmbeddedId    
    
    @AttributeOverrides( {
        @AttributeOverride(name="remarksId", column=@Column(name="REMARKS_ID", nullable=false, length=50) ), 
        @AttributeOverride(name="lineItemNo", column=@Column(name="LINEITEM_NO", nullable=false, length=10) )} )

    public SgvaCRMgmtRemarksId getId() {
        return this.id;
    }
    
    public void setId(SgvaCRMgmtRemarksId id) {
        this.id = id;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Column(name="EMP_ID", length=12)
    public String getEmpId() {
        return empId;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name="REMARKS", length=3000)
    public String getRemarks() {
        return remarks;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Column(name="ACTION_TAKEN", length=30)
    public String getActionTaken() {
        return actionTaken;
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

    public void setAttachExists(String attachExists) {
        this.attachExists = attachExists;
    }

    @Column(name="ATTACH_EXISTS",length=10)
    public String getAttachExists() {
        return attachExists;
    }
}
