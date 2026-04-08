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
@Table(name = "ehfm_unitlevels")
public class EhfmUnitlevels implements Serializable {
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "levelId", column = @Column(name = "LEVEL_ID", nullable = false, length = 12)),
			@AttributeOverride(name = "langId", column = @Column(name = "LANG_ID", nullable = false, length = 5)) })
	private EhfmUnitlevelsId id;
	@Column(name = "level_name")
	private String levelName;
	@Column(name = "loc_id")
	private String locId;
	@Column(name = "lvl_shrt_name")
	private String lvlShrtName;
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
	public EhfmUnitlevels() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmUnitlevels(EhfmUnitlevelsId id, String levelName, String locId,
			String lvlShrtName, Date crtDt, String crtUsr, String lstUpdUsr,
			Date lstUpdDt) {
		super();
		this.id = id;
		this.levelName = levelName;
		this.locId = locId;
		this.lvlShrtName = lvlShrtName;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}
	public EhfmUnitlevelsId getId() {
		return id;
	}
	public void setId(EhfmUnitlevelsId id) {
		this.id = id;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLvlShrtName() {
		return lvlShrtName;
	}
	public void setLvlShrtName(String lvlShrtName) {
		this.lvlShrtName = lvlShrtName;
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

