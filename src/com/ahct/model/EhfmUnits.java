package com.ahct.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehfm_units")
public class EhfmUnits implements Serializable {
	
	@Id
	@Column(name = "unit_id", nullable = false)
	private String unitId;
	@Column(name = "lang_id", nullable = false)
	private String langId;
	@Column(name = "dept_id", nullable = false)
	private String deptId;
	@Column(name = "level_id", nullable = false)
	private String levelId;
	@Column(name = "unit_name", nullable = false)
	private String unitName;
	@Column(name = "parent_unit_id", nullable = false)
	private String parantUnitId;
	@Column(name = "unit_type", nullable = false)
	private String unitType;
	@Column(name = "unit_shrt_name", nullable = false)
	private String unitShrtName;
	@Column(name = "loc_id", nullable = false)
	private String locId;
	@Column(name = "group_id", nullable = false)
	private String groupId;
	@Column(name = "crt_dt", nullable = false)
	private Date crtDt;
	@Column(name = "crt_usr", nullable = false)
	private String crtUsr;
	@Column(name = "lst_upd_usr", nullable = false)
	private String lstUpdUsr;
	@Column(name = "lst_upd_dt", nullable = false)
	private Date lstUpdDt;

	
	
	

	public EhfmUnits(String unitId, String langId, String deptId,
			String levelId, String unitName, String parantUnitId,
			String unitType, String unitShrtName, String locId, String groupId,
			Date crtDt, String crtUsr, String lstUpdUsr, Date lstUpdDt) {
		super();
		this.unitId = unitId;
		this.langId = langId;
		this.deptId = deptId;
		this.levelId = levelId;
		this.unitName = unitName;
		this.parantUnitId = parantUnitId;
		this.unitType = unitType;
		this.unitShrtName = unitShrtName;
		this.locId = locId;
		this.groupId = groupId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}

	public EhfmUnits() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getParantUnitId() {
		return parantUnitId;
	}

	public void setParantUnitId(String parantUnitId) {
		this.parantUnitId = parantUnitId;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitShrtName() {
		return unitShrtName;
	}

	public void setUnitShrtName(String unitShrtName) {
		this.unitShrtName = unitShrtName;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
