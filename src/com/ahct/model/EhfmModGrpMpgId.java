package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@SuppressWarnings("serial")
@Embeddable
public class EhfmModGrpMpgId implements Serializable {

	@Column(name = "mod_id", nullable = false, length = 12)
	private String modId;
	@Column(name = "grp_id", nullable = false, length = 12)
	private String grpId;
	public EhfmModGrpMpgId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmModGrpMpgId(String modId, String grpId) {
		super();
		this.modId = modId;
		this.grpId = grpId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grpId == null) ? 0 : grpId.hashCode());
		result = prime * result + ((modId == null) ? 0 : modId.hashCode());
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
		EhfmModGrpMpgId other = (EhfmModGrpMpgId) obj;
		if (grpId == null) {
			if (other.grpId != null)
				return false;
		} else if (!grpId.equals(other.grpId))
			return false;
		if (modId == null) {
			if (other.modId != null)
				return false;
		} else if (!modId.equals(other.modId))
			return false;
		return true;
	}
	
	
}
