package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_DOC_ATTCH database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_DOC_ATTCH")
public class EhfChronicDocAttch implements Serializable {
	private static final long serialVersionUID = 1L;
	private String activeYN;
	private String attachType;
	private Date crtDt;
	private String crtUsr;
	private String docCount;
	private long docSeqId;
	private String actualName;
	private String savedName;
	private String langId;
	private Date lstUpdDt;
	private String lstUpdUsr;
	//private EhfChronicCaseDtl ehfChronicCaseDtl;
	private String chronicId;
	private String chronicNo;

    public EhfChronicDocAttch() {
    }


	
    @Column(name = "CHRONIC_ID")
    public String getChronicId() {
		return chronicId;
	}




	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}



	@Column(name = "CHRONIC_NO")
	public String getChronicNo() {
		return chronicNo;
	}




	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}




	@Column(name = "activeYN")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}





	@Column(name="ATTACH_TYPE")
	public String getAttachType() {
		return this.attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	@Column(name="DOC_COUNT")
	public String getDocCount() {
		return this.docCount;
	}

	public void setDocCount(String docCount) {
		this.docCount = docCount;
	}

	@Id
	@Column(name="DOC_SEQ_ID")
	public long getDocSeqId() {
		return docSeqId;
	}


	public void setDocSeqId(long docSeqId) {
		this.docSeqId = docSeqId;
	}



	@Column(name="FILE_NAME")
	public String getActualName() {
		return actualName;
	}


	

	public void setActualName(String actualName) {
		this.actualName = actualName;
	}



	@Column(name="FILE_PATH")
	public String getSavedName() {
		return savedName;
	}


	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}


	@Column(name="LANG_ID")
	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	//bi-directional many-to-one association to EhfChronicCaseDtl
    /*@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CHRONIC_ID", referencedColumnName="CHRONIC_ID"),
		@JoinColumn(name="CHRONIC_NO", referencedColumnName="CHRONIC_NO")
		})
	public EhfChronicCaseDtl getEhfChronicCaseDtl() {
		return this.ehfChronicCaseDtl;
	}

	public void setEhfChronicCaseDtl(EhfChronicCaseDtl ehfChronicCaseDtl) {
		this.ehfChronicCaseDtl = ehfChronicCaseDtl;
	}*/
	
}