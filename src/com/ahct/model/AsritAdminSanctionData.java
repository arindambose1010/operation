package com.ahct.model;

import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="ehf_admin_sanction_data")
public class AsritAdminSanctionData implements Serializable{
    
    @Id
    @Column(name="sanction_id")
    private String sanctionId;
    @Column(name="status") 
    private String status;
    @Column(name="amount_requested") 
    private Long amountRequested;
    @Column(name="amount_approved") 
    private Long amountApproved;
    @Column(name="crt_usr")
    private String crtUsr;
    @Column(name="upd_usr")
    private String updUsr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="crt_dt", length=7)
    private Calendar crtDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="upd_dt", length=7)
    private Calendar updDt;
    @Column(name="partial_or_full_approve")
    private String partialOrFullApproved;
    @Column(name= "venders")
    private String vendors;

    public void setSanctionId(String sanctionId) {
        this.sanctionId = sanctionId;
    }

    public String getSanctionId() {
        return sanctionId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    public String getCrtUsr() {
        return crtUsr;
    }

    public void setUpdUsr(String updUsr) {
        this.updUsr = updUsr;
    }

    public String getUpdUsr() {
        return updUsr;
    }

    public void setCrtDt(Calendar crtDt) {
        this.crtDt = crtDt;
    }

    public Calendar getCrtDt() {
        return crtDt;
    }

    public void setUpdDt(Calendar updDt) {
        this.updDt = updDt;
    }

    public Calendar getUpdDt() {
        return updDt;
    }

    public void setAmountRequested(Long amountRequested) {
        this.amountRequested = amountRequested;
    }

    public Long getAmountRequested() {
        return amountRequested;
    }

    public void setAmountApproved(Long amountApproved) {
        this.amountApproved = amountApproved;
    }

    public Long getAmountApproved() {
        return amountApproved;
    }

    public void setPartialOrFullApproved(String partialOrFullApproved) {
        this.partialOrFullApproved = partialOrFullApproved;
    }

    public String getPartialOrFullApproved() {
        return partialOrFullApproved;
    }

    public void setVendors(String vendors) {
        this.vendors = vendors;
    }

    public String getVendors() {
        return vendors;
    }
}

