package com.ahct.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EHF_ONCOLOGY_CMTE_ADUIT")
public class EhfOncologyCmteAudit {

    @EmbeddedId
    private EhfOncologyCmteAuditPK id;

    @Column(name = "STATUS", length = 20)
    private String status;

    @Column(name = "REMARKS", length = 2000)
    private String remarks;

    @Column(name = "ACT_BY", length = 20)
    private String actBy;

    @Column(name = "CRT_USR", length = 20)
    private String crtUsr;

    @Column(name = "CRT_DT")
    private Timestamp crtDt;

    @Column(name = "LST_UPD_USR", length = 20)
    private String lstUpdUsr;

    @Column(name = "LST_UPD_DT")
    private Timestamp lstUpdDt;

    // Default constructor
    public EhfOncologyCmteAudit() {}

    // Getters and Setters
    public EhfOncologyCmteAuditPK getId() {
        return id;
    }

    public void setId(EhfOncologyCmteAuditPK id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Timestamp getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Timestamp crtDt) {
        this.crtDt = crtDt;
    }

    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }

    public Timestamp getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Timestamp lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
}
