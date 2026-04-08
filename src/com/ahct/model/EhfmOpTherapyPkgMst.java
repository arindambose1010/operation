package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="EHFM_OP_THERAPY_PKG_MST")
public class EhfmOpTherapyPkgMst implements Serializable {

	private EhfmOpTherapyPkgMstId id;
	private String asriMainCode;
	private String icdOpPkgName;
	private String icdCatName;
	private String langId;
	private char activeYN;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Long casePackageAmt;
	public EhfmOpTherapyPkgMst() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmOpTherapyPkgMst(EhfmOpTherapyPkgMstId id, String asriMainCode,
			String icdOpPkgName, String icdCatName, String langId,
			char activeYN, String crtUsr, Date crtDt, String lstUpdUsr,
			Date lstUpdDt,Long casePackageAmt) {
		super();
		this.id = id;
		this.asriMainCode = asriMainCode;
		this.icdOpPkgName = icdOpPkgName;
		this.icdCatName = icdCatName;
		this.langId = langId;
		this.activeYN = activeYN;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.casePackageAmt = casePackageAmt;
	}
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "icdOpPkgCode", column = @Column(name = "ICD_OP_PKG_CODE", nullable = false, length = 100)),
			@AttributeOverride(name = "icdCatCode", column = @Column(name = "ICD_Cat_Code", nullable = false, length = 100))
			})
	public EhfmOpTherapyPkgMstId getId() {
		return id;
	}
	public void setId(EhfmOpTherapyPkgMstId id) {
		this.id = id;
	}
	@Column(name="ASRI_MAIN_CODE")
	public String getAsriMainCode() {
		return asriMainCode;
	}
	public void setAsriMainCode(String asriMainCode) {
		this.asriMainCode = asriMainCode;
	}
	@Column(name="ICD_OP_PKG_NAME")
	public String getIcdOpPkgName() {
		return icdOpPkgName;
	}
	public void setIcdOpPkgName(String icdOpPkgName) {
		this.icdOpPkgName = icdOpPkgName;
	}
	@Column(name="ICD_Cat_Name")
	public String getIcdCatName() {
		return icdCatName;
	}
	public void setIcdCatName(String icdCatName) {
		this.icdCatName = icdCatName;
	}
	@Column(name="lang_id")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="active_yn", nullable=true)
	public char getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(char activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name="crt_usr", nullable=true)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="crt_dt", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="lst_upd_usr", nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="lst_upd_dt", nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="PKG_AMT")
	public Long getCasePackageAmt() {
		return casePackageAmt;
	}
	public void setCasePackageAmt(Long casePackageAmt) {
		this.casePackageAmt = casePackageAmt;
	}

}
