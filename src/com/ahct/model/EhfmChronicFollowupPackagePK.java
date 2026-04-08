package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_CHRONIC_FOLLOWUP_PACKAGES database table.
 * 
 */
@Embeddable
public class EhfmChronicFollowupPackagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicDisCode;
	private long actOrder;
	private String scheme;

    public EhfmChronicFollowupPackagePK() {
    }

	@Column(name="CHRONIC_DIS_CODE")
	public String getChronicDisCode() {
		return this.chronicDisCode;
	}
	public void setChronicDisCode(String chronicDisCode) {
		this.chronicDisCode = chronicDisCode;
	}

	@Column(name="ACT_ORDER")
	public long getActOrder() {
		return this.actOrder;
	}
	public void setActOrder(long actOrder) {
		this.actOrder = actOrder;
	}

	public String getScheme() {
		return this.scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmChronicFollowupPackagePK)) {
			return false;
		}
		EhfmChronicFollowupPackagePK castOther = (EhfmChronicFollowupPackagePK)other;
		return 
			this.chronicDisCode.equals(castOther.chronicDisCode)
			&& (this.actOrder == castOther.actOrder)
			&& this.scheme.equals(castOther.scheme);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicDisCode.hashCode();
		hash = hash * prime + ((int) (this.actOrder ^ (this.actOrder >>> 32)));
		hash = hash * prime + this.scheme.hashCode();
		
		return hash;
    }
}