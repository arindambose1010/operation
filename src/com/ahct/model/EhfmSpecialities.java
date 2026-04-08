 package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_SPECIALITIES"
)
public class EhfmSpecialities implements Serializable{

    private String disMainId ;
    private String disMainName;
    private String crtUsr;
    private Date crtDt;
    private String lstUpdUsr;
    private Date lstUpdDt;
    private String langId;
    private Character disActiveYN;
    private Long surgOrder;
    
    
    public EhfmSpecialities() {
    }

    public void setDisMainId(String disMainId) {
        this.disMainId = disMainId;
    }
    @Id
    @Column(name="DIS_MAIN_ID", nullable=false, length=100)
    public String getDisMainId() {
        return disMainId;
    }

    public void setDisMainName(String disMainName) {
        this.disMainName = disMainName;
    }
    @Column(name="DIS_MAIN_NAME", nullable=false, length=100)
    public String getDisMainName() {
        return disMainName;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Column(name="CRT_USR", nullable=false, length=12)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CR_DT", nullable=false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name="LST_UPD_USR", nullable=true, length=12)
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT", nullable=true)
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }
    @Column(name="LANG_ID", nullable=false, length=25)
    public String getLangId() {
        return langId;
    }

    public void setDisActiveYN(Character disActiveYN) {
        this.disActiveYN = disActiveYN;
    }
    @Column(name="DIS_ACTIVE_YN", nullable=true, length=1)
    public Character getDisActiveYN() {
        return disActiveYN;
    }

    public void setSurgOrder(Long surgOrder) {
        this.surgOrder = surgOrder;
    }
    @Column(name="SURG_ORDER", nullable=true)
    public Long getSurgOrder() {
        return surgOrder;
    }
}
