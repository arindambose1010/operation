package com.ahct.model;

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
@Table(name="EHFM_MOD_GRP_MPG")
public class EhfmModGrpMpg implements java.io.Serializable{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crtDt == null) ? 0 : crtDt.hashCode());
		result = prime * result + ((crtUsr == null) ? 0 : crtUsr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((langId == null) ? 0 : langId.hashCode());
		result = prime * result + ((locId == null) ? 0 : locId.hashCode());
		result = prime * result
				+ ((lstUpdDt == null) ? 0 : lstUpdDt.hashCode());
		result = prime * result
				+ ((lstUpdUsr == null) ? 0 : lstUpdUsr.hashCode());
		result = prime * result
				+ ((moduleId == null) ? 0 : moduleId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfmModGrpMpg other = (EhfmModGrpMpg) obj;
		if (crtDt == null) {
			if (other.crtDt != null)
				return false;
		} else if (!crtDt.equals(other.crtDt))
			return false;
		if (crtUsr == null) {
			if (other.crtUsr != null)
				return false;
		} else if (!crtUsr.equals(other.crtUsr))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (langId == null) {
			if (other.langId != null)
				return false;
		} else if (!langId.equals(other.langId))
			return false;
		if (locId == null) {
			if (other.locId != null)
				return false;
		} else if (!locId.equals(other.locId))
			return false;
		if (lstUpdDt == null) {
			if (other.lstUpdDt != null)
				return false;
		} else if (!lstUpdDt.equals(other.lstUpdDt))
			return false;
		if (lstUpdUsr == null) {
			if (other.lstUpdUsr != null)
				return false;
		} else if (!lstUpdUsr.equals(other.lstUpdUsr))
			return false;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		return true;
	}
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "modId", column = @Column(name = "mod_id", nullable = false, length = 12)),
			@AttributeOverride(name = "grpId", column = @Column(name = "grp_id", nullable = false, length = 12)) })
	private EhfmModGrpMpgId id;
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
	@Column(name = "lang_id")
	private String langId;
    @Column(name = "LOC_ID", length = 5)
	private String locId;
    @Column(name = "MODULE_ID", length = 50)
	private String moduleId;
	public EhfmModGrpMpg() {
		super();
	}
	public EhfmModGrpMpg(EhfmModGrpMpgId id, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt, String langId, String locId,String moduleId) {
		super();
		this.id = id;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.langId = langId;
		this.locId = locId;
		this.moduleId = moduleId;
	}
	public EhfmModGrpMpgId getId() {
		return id;
	}
	public void setId(EhfmModGrpMpgId id) {
		this.id = id;
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
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
 }
