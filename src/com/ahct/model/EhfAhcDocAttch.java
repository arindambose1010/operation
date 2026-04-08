package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table

(name = "EHF_AHC_DOC_ATTACH")
public class EhfAhcDocAttch implements java.io.Serializable {

    private Long docSeqId;
    private String ahcId;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String actualName;
    private String savedName;
    private String attachType;
    private String activeYN;
    private String docCount;
    
    
    @Column(name="doc_count")
    public String getDocCount() {
		return docCount;
	}
	public void setDocCount(String docCount) {
		this.docCount = docCount;
	}
	@Column(name = "activeYN",  nullable = true, length = 2)  
    public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	
	@Id
    @Column(name = "DOC_SEQ_ID", unique = true, nullable = false, length = 20)  
    public Long getDocSeqId() {
        return docSeqId;
    }
        public void setDocSeqId(Long docSeqId) {
            this.docSeqId = docSeqId;
        }

    @Column(name = "AHC_ID", unique = true, nullable = false, length = 20)  

    public String getAhcId() {
        return ahcId;
    }

    public void setAhcId(String ahcId) {
        this.ahcId = ahcId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CRT_DT")
    public Date getCrtDt() {
        return crtDt;
    }

  
    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column(name = "CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }
    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    @Column(name = "LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }
    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    @Column(name = "LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }
    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name = "FILE_NAME", length=1000)
    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    @Column(name = "FILE_PATH", length=4000)
    public String getSavedName() {
        return savedName;
    }
    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    @Column(name = "ATTACH_TYPE")
    public String getAttachType() {
        return attachType;
    }
    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

   
}
