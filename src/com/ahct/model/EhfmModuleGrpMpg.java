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

@Entity
@Table(name="EHFM_MODULE_GRPS_MPG")
public class EhfmModuleGrpMpg implements java.io.Serializable{
	
	/** The id. */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "moduleName", column = @Column(name = "MODULE_NAME", nullable = false, length = 30)),
			@AttributeOverride(name = "grpId", column = @Column(name = "GRP_ID", nullable = false, length = 12)) })
	private EhfmModuleGrpMpgId id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT", nullable = true)
	private Date crtDt;
	
	/** The crt usr. */
	@Column(name = "CRT_USR", nullable = false)
	private String crtUsr;
	
	/** The lst upd usr. */
	@Column(name = "LST_UPD_USR", nullable = true)
	private String lstUpdUsr;
	
	/** The lst upd dt. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT", nullable = true)
	private Date lstUpdDt;
	
	 /** The module id. */
    @Column(name = "FLAG", length = 1)
	private String flag;
    
    @Column(name = "scheme_id")
	private String schemeId;

	public EhfmModuleGrpMpgId getId() {
		return id;
	}

	public void setId(EhfmModuleGrpMpgId id) {
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	
	
	
	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public EhfmModuleGrpMpg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmModuleGrpMpg(EhfmModuleGrpMpgId id, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt, String flag) {
		super();
		this.id = id;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crtDt == null) ? 0 : crtDt.hashCode());
		result = prime * result + ((crtUsr == null) ? 0 : crtUsr.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lstUpdDt == null) ? 0 : lstUpdDt.hashCode());
		result = prime * result
				+ ((lstUpdUsr == null) ? 0 : lstUpdUsr.hashCode());
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
		EhfmModuleGrpMpg other = (EhfmModuleGrpMpg) obj;
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
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return true;
	}

    
}
