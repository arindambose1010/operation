package com.ahct.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfmModuleGrpMpgId implements java.io.Serializable{

	/** The mod id. */
	@Column(name = "MODULE_NAME", nullable = false, length = 30)
	private String moduleName;
	
	/** The grp id. */
	@Column(name = "GRP_ID", nullable = false, length = 12)
	private String grpId;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public EhfmModuleGrpMpgId(String moduleName, String grpId) {
		super();
		this.moduleName = moduleName;
		this.grpId = grpId;
	}

	public EhfmModuleGrpMpgId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grpId == null) ? 0 : grpId.hashCode());
		result = prime * result
				+ ((moduleName == null) ? 0 : moduleName.hashCode());
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
		EhfmModuleGrpMpgId other = (EhfmModuleGrpMpgId) obj;
		if (grpId == null) {
			if (other.grpId != null)
				return false;
		} else if (!grpId.equals(other.grpId))
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		return true;
	}

	
	
	
	
}
