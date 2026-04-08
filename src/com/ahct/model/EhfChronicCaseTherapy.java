package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_CASE_THERAPY database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_CASE_THERAPY")
public class EhfChronicCaseTherapy implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EhfChronicCaseTherapyPK id;

	private String activeyn;

	@Column(name="CHRONIC_CAT_CODE")
	private String chronicCatCode;

    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="DOC_MOBILE_NO")
	private String docMobileNo;

	@Column(name="DOC_NAME")
	private String docName;

	@Column(name="DOC_QUAL")
	private String docQual;

	@Column(name="DOC_REG_NUM")
	private String docRegNum;

	@Column(name="ICD_CAT_CODE")
	private String icdCatCode;

	@Column(name="ICD_PKG_CODE")
	private String icdPkgCode;

    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="NO_OF_DAYS")
	private BigDecimal noOfDays;

	@Column(name="SPL_INV_REMARKS")
	private String splInvRemarks;

    public EhfChronicCaseTherapy() {
    }

	public EhfChronicCaseTherapyPK getId() {
		return this.id;
	}

	public void setId(EhfChronicCaseTherapyPK id) {
		this.id = id;
	}
	
	public String getActiveyn() {
		return this.activeyn;
	}

	public void setActiveyn(String activeyn) {
		this.activeyn = activeyn;
	}

	public String getChronicCatCode() {
		return this.chronicCatCode;
	}

	public void setChronicCatCode(String chronicCatCode) {
		this.chronicCatCode = chronicCatCode;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getDocMobileNo() {
		return this.docMobileNo;
	}

	public void setDocMobileNo(String docMobileNo) {
		this.docMobileNo = docMobileNo;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocQual() {
		return this.docQual;
	}

	public void setDocQual(String docQual) {
		this.docQual = docQual;
	}

	public String getDocRegNum() {
		return this.docRegNum;
	}

	public void setDocRegNum(String docRegNum) {
		this.docRegNum = docRegNum;
	}

	public String getIcdCatCode() {
		return this.icdCatCode;
	}

	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}

	public String getIcdPkgCode() {
		return this.icdPkgCode;
	}

	public void setIcdPkgCode(String icdPkgCode) {
		this.icdPkgCode = icdPkgCode;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public BigDecimal getNoOfDays() {
		return this.noOfDays;
	}

	public void setNoOfDays(BigDecimal noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getSplInvRemarks() {
		return this.splInvRemarks;
	}

	public void setSplInvRemarks(String splInvRemarks) {
		this.splInvRemarks = splInvRemarks;
	}

	public void setChronicID(String lStrChronicId) {
		// TODO Auto-generated method stub
		
	}

}