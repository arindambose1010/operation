package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_DEPT_DSGN_MPG database table.
 * 
 */
@Embeddable
public class EhfDeptDsgnMpgPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dsgnId;
	private String deptId;
	private String schemeId;

    public EhfDeptDsgnMpgPK() {
    }

	@Column(name="DSGN_ID")
	public String getDsgnId() {
		return this.dsgnId;
	}
	public void setDsgnId(String dsgnId) {
		this.dsgnId = dsgnId;
	}

	@Column(name="DEPT_ID")
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfDeptDsgnMpgPK)) {
			return false;
		}
		EhfDeptDsgnMpgPK castOther = (EhfDeptDsgnMpgPK)other;
		return 
			this.dsgnId.equals(castOther.dsgnId)
			&& this.deptId.equals(castOther.deptId)
			&& this.deptId.equals(castOther.schemeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dsgnId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
}