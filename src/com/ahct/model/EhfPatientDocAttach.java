package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_PATIENT_DOC_ATTCH")
public class EhfPatientDocAttach implements java.io.Serializable{
    public EhfPatientDocAttach() {
    }
    private Long docSeqId;
    private String patientId;
    private String actualName;
    private String savedName;
    private String langId;
    private String attachType;
    private Date crtDate;
    private String crtUser;
    private Date lastUpdDate;
    private String lastUpdUser;

    public void setDocSeqId(Long docSeqId) {
        this.docSeqId = docSeqId;
    }

    @Id
    @Column(name="DOC_SEQ_ID",length=20, nullable=false)
    public Long getDocSeqId() {
        return docSeqId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    @Column(name="PATIENT_ID", length=20, nullable=false)
    public String getPatientId() {
        return patientId;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }
    
    @Column(name="file_NAME", length=1000)
    public String getActualName() {
        return actualName;
    }
    
    public void setLangId(String langId) {
        this.langId = langId;
    }
    
    @Column(name="LANG_ID", length=5)
    public String getLangId() {
        return langId;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    @Column(name="ATTACH_TYPE", length=5)
    public String getAttachType() {
        return attachType;
    }    public void setCrtDate(Date crtDate) {
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

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }
    
    @Column(name="file_path", length=4000,nullable=false)
    public String getSavedName() {
        return savedName;
    }
}
