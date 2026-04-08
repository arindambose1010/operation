package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EhfmDesignationId implements Serializable {

	@Column(name = "dsgn_id", nullable = false)
	private String dsgnId;
	@Column(name = "lang_id", nullable = false)
	private String langId;

	public EhfmDesignationId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmDesignationId(String dsgnId, String langId) {
		super();
		this.dsgnId = dsgnId;
		this.langId = langId;
	}

	public String getDsgnId() {
		return dsgnId;
	}

	public void setDsgnId(String dsgnId) {
		this.dsgnId = dsgnId;
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
		result = prime * result + ((dsgnId == null) ? 0 : dsgnId.hashCode());
		result = prime * result + ((langId == null) ? 0 : langId.hashCode());
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
		EhfmDesignationId other = (EhfmDesignationId) obj;
		if (dsgnId == null) {
			if (other.dsgnId != null)
				return false;
		} else if (!dsgnId.equals(other.dsgnId))
			return false;
		if (langId == null) {
			if (other.langId != null)
				return false;
		} else if (!langId.equals(other.langId))
			return false;
		return true;
	}

}
