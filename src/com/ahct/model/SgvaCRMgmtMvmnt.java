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
@Table(name="SGVA_CRMGMT_MVMNT")
public class SgvaCRMgmtMvmnt implements java.io.Serializable
{
    public SgvaCRMgmtMvmnt() {}
    
    private SgvaCRMgmtMvmntId id;
    private String fromUnitId;
    private String toUnitId;
    private String actionTaken;
    private String actionFlag;
    private String active;
    private String fromLgsb;
    private String toLgsb;
    private String fromDept;
    private String toDept;
    private Date crtDate;
    private String crtUser;
    
    @EmbeddedId     
    @AttributeOverrides( {
        @AttributeOverride(name="crId", column=@Column(name="CR_ID", nullable=false, length=100) ), 
        @AttributeOverride(name="lineItemNo", column=@Column(name="LINEITEM_NO", nullable=false, length=10) )} )

    public SgvaCRMgmtMvmntId getId() {
        return this.id;
    }
    
    public void setId(SgvaCRMgmtMvmntId id) {
        this.id = id;
    }
    
    public void setFromUnitId(String fromUnitId) {
        this.fromUnitId = fromUnitId;
    }
  
    @Column(name="FROM_UNITID", length=200)
    public String getFromUnitId() {
        return fromUnitId;
    }

    public void setToUnitId(String toUnitId) {
        this.toUnitId = toUnitId;
    }

    @Column(name="TO_UNITID", length=200)
    public String getToUnitId() {
        return toUnitId;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Column(name="ACTION_TAKEN", length=50)
    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    @Column(name="ACTION_FLAG", length=50)
    public String getActionFlag() {
        return actionFlag;
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

    public void setActive(String active) {
        this.active = active;
    }

    @Column(name="ACTIVE", length=5)
    public String getActive() {
        return active;
    }

    public void setFromLgsb(String fromLgsb) {
        this.fromLgsb = fromLgsb;
    }
    
    @Column(name="from_lgsb", length=100)
    public String getFromLgsb() {
        return fromLgsb;
    }

    public void setToLgsb(String toLgsb) {
        this.toLgsb = toLgsb;
    }
    
    @Column(name="to_lgsb", length=100)
    public String getToLgsb() {
        return toLgsb;
    }

    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    @Column(name="from_dept", length=10)
    public String getFromDept() {
        return fromDept;
    }

    public void setToDept(String toDept) {
        this.toDept = toDept;
    }

    @Column(name="to_dept", length=10)
    public String getToDept() {
        return toDept;
    }
}
