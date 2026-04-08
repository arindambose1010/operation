package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_DRUGS_MST database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_DRUGS_MST")
public class EhfmChronicDrugsMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmChronicDrugsMstPK id;
	private String actCode;
	private String activeYn;
	private Date crtDt;
	private String crtUsr;
	private String drugName;
	private String drugSubTypeCode;
	private String drugSubTypeName;
	private String drugTypeCode;
	private String drugTypeName;
	private String langId;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String routeName;
	private String strength;

    public EhfmChronicDrugsMst() {
    }


	@EmbeddedId
	public EhfmChronicDrugsMstPK getId() {
		return this.id;
	}

	public void setId(EhfmChronicDrugsMstPK id) {
		this.id = id;
	}
	

	@Column(name="ACT_CODE")
	public String getActCode() {
		return this.actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}


	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}


    @Temporal( TemporalType.DATE)
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


	@Column(name="DRUG_NAME")
	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	@Column(name="DRUG_SUB_TYPE_CODE")
	public String getDrugSubTypeCode() {
		return this.drugSubTypeCode;
	}

	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}


	@Column(name="DRUG_SUB_TYPE_NAME")
	public String getDrugSubTypeName() {
		return this.drugSubTypeName;
	}

	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}


	@Column(name="DRUG_TYPE_CODE")
	public String getDrugTypeCode() {
		return this.drugTypeCode;
	}

	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}


	@Column(name="DRUG_TYPE_NAME")
	public String getDrugTypeName() {
		return this.drugTypeName;
	}

	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}


	@Column(name="LANG_ID")
	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}


    @Temporal( TemporalType.DATE)
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


	@Column(name="ROUTE_NAME")
	public String getRouteName() {
		return this.routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}


	public String getStrength() {
		return this.strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

}