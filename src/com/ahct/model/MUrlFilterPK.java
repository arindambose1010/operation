package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the M_URL_FILTER database table.
 * 
 */
@Embeddable
public class MUrlFilterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TYPE")
	private String type;

	@Column(name="VALUE")
	private String value;

	public MUrlFilterPK() {
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MUrlFilterPK)) {
			return false;
		}
		MUrlFilterPK castOther = (MUrlFilterPK)other;
		return 
			this.type.equals(castOther.type)
			&& this.value.equals(castOther.value);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.type.hashCode();
		hash = hash * prime + this.value.hashCode();
		
		return hash;
	}
}