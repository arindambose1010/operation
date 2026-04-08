package com.ahct.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfmUnitlevelsId  implements Serializable{
	
	@Column(name="LEVEL_ID", nullable=false, length=12)
	private String levelId;
	@Column(name="LANG_ID", nullable=false, length=5)
    private String langId;
	public EhfmUnitlevelsId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmUnitlevelsId(String levelId, String langId) {
		super();
		this.levelId = levelId;
		this.langId = langId;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
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
		result = prime * result + ((langId == null) ? 0 : langId.hashCode());
		result = prime * result + ((levelId == null) ? 0 : levelId.hashCode());
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
		EhfmUnitlevelsId other = (EhfmUnitlevelsId) obj;
		if (langId == null) {
			if (other.langId != null)
				return false;
		} else if (!langId.equals(other.langId))
			return false;
		if (levelId == null) {
			if (other.levelId != null)
				return false;
		} else if (!levelId.equals(other.levelId))
			return false;
		return true;
	}
	
	
}

