package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@SuppressWarnings("serial")
@Embeddable
public class EhfmUsrGrpsMpgId implements Serializable  {
	
	@Column(name="GRP_ID", nullable=false, length=12)
	private String grpId;
	@Column(name="usergrp_id", nullable=false, length=12)
    private String usergrpId;
	@Column(name="LANG_ID", nullable=false, length=5)
    private String langId;
	
	public EhfmUsrGrpsMpgId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmUsrGrpsMpgId(String grpId, String usergrpId, String langId) {
		super();
		this.grpId = grpId;
		this.usergrpId = usergrpId;
		this.langId = langId;
	}

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getUsergrpId() {
		return usergrpId;
	}

	public void setUsergrpId(String usergrpId) {
		this.usergrpId = usergrpId;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grpId == null) ? 0 : grpId.hashCode());
		result = prime * result + ((langId == null) ? 0 : langId.hashCode());
		result = prime * result
				+ ((usergrpId == null) ? 0 : usergrpId.hashCode());
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
		EhfmUsrGrpsMpgId other = (EhfmUsrGrpsMpgId) obj;
		if (grpId == null) {
			if (other.grpId != null)
				return false;
		} else if (!grpId.equals(other.grpId))
			return false;
		if (langId == null) {
			if (other.langId != null)
				return false;
		} else if (!langId.equals(other.langId))
			return false;
		if (usergrpId == null) {
			if (other.usergrpId != null)
				return false;
		} else if (!usergrpId.equals(other.usergrpId))
			return false;
		return true;
	}
	
	
	
	
}
