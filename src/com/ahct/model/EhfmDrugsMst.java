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

@Entity
@Table(name="EHFM_DRUGS_MST")
public class EhfmDrugsMst implements Serializable {
  
	private EhfmDrugsMstId id;
	private String drugTypeName;
	private String drugSubTypeName;
	private String drugName;
	private String actCode;
	private String routeCode;
	private String routeName;
	private String strength;
	private String activeYN;
	private String langId;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    
	public EhfmDrugsMst() {
		super();
		}
	public EhfmDrugsMst(EhfmDrugsMstId id, String drugTypeName,
			String drugSubTypeName, String drugName, String actCode,
			String routeCode, String routeName, String strength,
			String activeYN, String langId, Date crtDt, String crtUsr,
			Date lstUpdDt, String lstUpdUsr) {
		super();
		this.id = id;
		this.drugTypeName = drugTypeName;
		this.drugSubTypeName = drugSubTypeName;
		this.drugName = drugName;
		this.actCode = actCode;
		this.routeCode = routeCode;
		this.routeName = routeName;
		this.strength = strength;
		this.activeYN = activeYN;
		this.langId = langId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
	}
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "drugTypeCode", column = @Column(name = "DRUG_TYPE_CODE", nullable = false, length = 100)),
			@AttributeOverride(name = "drugSubTypeCode", column = @Column(name = "DRUG_SUB_TYPE_CODE", nullable = false, length = 100)),
			@AttributeOverride(name = "drugCode", column = @Column(name = "DRUG_CODE", nullable = false, length = 100))
			})
	public EhfmDrugsMstId getId() {
		return id;
	}
	public void setId(EhfmDrugsMstId id) {
		this.id = id;
	}
	@Column(name="DRUG_TYPE_NAME")
	public String getDrugTypeName() {
		return drugTypeName;
	}
	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}
	
	@Column(name="DRUG_SUB_TYPE_NAME")
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}

	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}

	@Column(name="DRUG_NAME")
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	@Column(name="ACT_CODE")
	public String getActCode() {
		return actCode;
	}
	public void setActCode(String actCode) {
		this.actCode = actCode;
	}
	@Column(name="ROUTE_CODE")
	public String getRouteCode() {
		return routeCode;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	@Column(name="ROUTE_NAME")
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	@Column(name="STRENGTH")
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	@Column(name="active_yn")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}

	@Column(name="lang_id")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
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
	
}
