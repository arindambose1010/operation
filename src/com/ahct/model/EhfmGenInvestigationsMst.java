package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_GEN_INVESTIGATIONS_MST")
public class EhfmGenInvestigationsMst {

	private String invMainCode;
	private String invMainType;
	private String invCode;
	private String invName;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String langId;
    private String activeYN;
    private String invCatCode;
    private String invCatName;
    private String invMainName;
    private String investPrice;
    
    @Column(name="INV_MAIN_CODE")
	public String getInvMainCode() {
		return invMainCode;
	}
	public void setInvMainCode(String invMainCode) {
		this.invMainCode = invMainCode;
	}
	@Column(name="INV_MAIN_TYPE")
	public String getInvMainType() {
		return invMainType;
	}
	public void setInvMainType(String invMainType) {
		this.invMainType = invMainType;
	}
	@Id
	@Column(name="INV_CODE")
	public String getInvCode() {
		return invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
	@Column(name="INV_NAME")
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	
	@Column(name="lang_id")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="active_yn")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="inv_cat_code")
	public String getInvCatCode() {
		return invCatCode;
	}
	public void setInvCatCode(String invCatCode) {
		this.invCatCode = invCatCode;
	}
	@Column(name="inv_cat_name")
	public String getInvCatName() {
		return invCatName;
	}
	public void setInvCatName(String invCatName) {
		this.invCatName = invCatName;
	}
	@Column(name="inv_main_name")
	public String getInvMainName() {
		return invMainName;
	}
	public void setInvMainName(String invMainName) {
		this.invMainName = invMainName;
	}
	
	@Column(name="invest_price")
	public String getInvestPrice() {
		return investPrice;
	}
	public void setInvestPrice(String investPrice) {
		this.investPrice = investPrice;
	}
	
	
}
