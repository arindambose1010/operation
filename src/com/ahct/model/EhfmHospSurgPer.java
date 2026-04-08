package com.ahct.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EHFM_HOSP_SURG_PER")
public class EhfmHospSurgPer implements Serializable{

	private EhfmHospSurgPerId id;
    private String seqId;
    private String crtUsr;
    private Date crtDt;
    private String lstUpdUsr;
    private Date lstUpdDt;
    private Double hospPercent;
    private Double trustPercent;
    private String activeYn;
    private Double tdsPercent;
    private Double tdsHospPercent;
    private String tdsActiveYn;
    private Double tdsSurchargePct;
    private Double tdsCessPct;
   
    //private Set<EhfCaseTherapy> ehfCaseTherapy =  new HashSet<EhfCaseTherapy>(0);
    
    public void setId(EhfmHospSurgPerId id) {
        this.id = id;
    }

    @EmbeddedId 
    @AttributeOverrides( {
        @AttributeOverride(name="hospId", column=@Column(name="HOSP_ID",nullable=false,length=20)),
        @AttributeOverride(name="surgeryId",column=@Column(name="SURGERY_ID",nullable=false,length=10 )),
        @AttributeOverride(name="claimType",column=@Column(name="CLAIM_TYPE",nullable=false,length=10 )),
        @AttributeOverride(name="deductorType",column=@Column(name="DEDUCTOR_TYPE",nullable=false,length=10 ))
    }
    ) 
    public EhfmHospSurgPerId getId() {
        return id;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }
    @Column(name="SEQ_ID")
    public String getSeqId() {
        return seqId;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Column(name="CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column(name="CRT_DT")
    public Date getCrtDt() {
        return crtDt;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setHospPercent(Double hospPercent) {
        this.hospPercent = hospPercent;
    }
    @Column(name="HOSP_PERCENT")
    public Double getHospPercent() {
        return hospPercent;
    }

    public void setTrustPercent(Double trustPercent) {
        this.trustPercent = trustPercent;
    }
    @Column(name="TRUST_PERCENT")
    public Double getTrustPercent() {
        return trustPercent;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }
    @Column(name="ACTIVE_YN")
    public String getActiveYn() {
        return activeYn;
    }

    public void setTdsPercent(Double tdsPercent) {
        this.tdsPercent = tdsPercent;
    }
    @Column(name="TDS_PERCENT")
    public Double getTdsPercent() {
        return tdsPercent;
    }

    public void setTdsHospPercent(Double tdsHospPercent) {
        this.tdsHospPercent = tdsHospPercent;
    }
    @Column(name="TDS_HOSP_PERCENT")
    public Double getTdsHospPercent() {
        return tdsHospPercent;
    }

    public void setTdsActiveYn(String tdsActiveYn) {
        this.tdsActiveYn = tdsActiveYn;
    }
    @Column(name="TDS_ACTIVE_YN")
    public String getTdsActiveYn() {
        return tdsActiveYn;
    }

    public void setTdsSurchargePct(Double tdsSurchargePct) {
        this.tdsSurchargePct = tdsSurchargePct;
    }
    @Column(name="TDS_SURCHARGE_PCT")
    public Double getTdsSurchargePct() {
        return tdsSurchargePct;
    }

    public void setTdsCessPct(Double tdsCessPct) {
        this.tdsCessPct = tdsCessPct;
    }
    @Column(name="TDS_CESS_PCT")
    public Double getTdsCessPct() {
        return tdsCessPct;
    }
   /* @ManyToMany
    @JoinTable(name="EHF_CASE_THERAPY",
            joinColumns={@JoinColumn(name="SURGERY_ID")},
            inverseJoinColumns={@JoinColumn(name="ICD_Proc_Code")})
	public Set<EhfCaseTherapy> getEhfCaseTherapy() {
		return ehfCaseTherapy;
	}

	public void setEhfCaseTherapy(Set<EhfCaseTherapy> ehfCaseTherapy) {
		this.ehfCaseTherapy = ehfCaseTherapy;
	}
    
	*/

	
    
    
    
}
