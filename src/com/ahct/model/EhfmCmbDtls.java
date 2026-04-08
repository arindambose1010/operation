package com.ahct.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ehfm_cmb_dtls")
public class EhfmCmbDtls implements Serializable {
	@Id
	@Column(name = "cmb_dtl_id", nullable = false, length = 10)
	private String cmbDtlId;
	@Column(name = "LANG_ID", nullable = false, length = 5)
	private String langId;
	@Column(name = "cmb_dtl_name", nullable = false, length = 200)
	private String cmbDtlName;
	@Column(name = "cmb_hdr_id", nullable = false, length = 5)
	private String cmbHdrId;
	@Column(name = "cmb_parnt_dtl_id")
	private String cmbParntDtlId;
	@Column(name = "cmb_order")
	private Integer cmbOrder;
	@Column(name = "cmb_attr_val")
	private String cmbAttrVal;
	@Column(name = "LOC_ID", length = 5)
	private String locId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "crt_dt", nullable = true)
	private Date crtDt;
	@Column(name = "crt_usr", nullable = false)
	private String crtUsr;
	@Column(name = "lst_upd_usr", nullable = true)
	private String lstUpdUsr;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_upd_dt", nullable = true)
	private Date lstUpdDt;

	public EhfmCmbDtls() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmCmbDtls(String cmbDtlId, String langId, String cmbDtlName,
			String cmbHdrId, String cmbParntDtlId, Integer cmbOrder,
			String cmbAttrVal, String locId, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.cmbDtlId = cmbDtlId;
		this.langId = langId;
		this.cmbDtlName = cmbDtlName;
		this.cmbHdrId = cmbHdrId;
		this.cmbParntDtlId = cmbParntDtlId;
		this.cmbOrder = cmbOrder;
		this.cmbAttrVal = cmbAttrVal;
		this.locId = locId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}

	public String getCmbDtlId() {
		return cmbDtlId;
	}

	public void setCmbDtlId(String cmbDtlId) {
		this.cmbDtlId = cmbDtlId;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getCmbDtlName() {
		return cmbDtlName;
	}

	public void setCmbDtlName(String cmbDtlName) {
		this.cmbDtlName = cmbDtlName;
	}

	public String getCmbHdrId() {
		return cmbHdrId;
	}

	public void setCmbHdrId(String cmbHdrId) {
		this.cmbHdrId = cmbHdrId;
	}

	public String getCmbParntDtlId() {
		return cmbParntDtlId;
	}

	public void setCmbParntDtlId(String cmbParntDtlId) {
		this.cmbParntDtlId = cmbParntDtlId;
	}

	public Integer getCmbOrder() {
		return cmbOrder;
	}

	public void setCmbOrder(Integer cmbOrder) {
		this.cmbOrder = cmbOrder;
	}

	public String getCmbAttrVal() {
		return cmbAttrVal;
	}

	public void setCmbAttrVal(String cmbAttrVal) {
		this.cmbAttrVal = cmbAttrVal;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

}
