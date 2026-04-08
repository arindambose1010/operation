package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_CHRONIC_CASE_THERAPY database table.
 * 
 */
@Embeddable
public class EhfChronicCaseTherapyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CHRONIC_THERAPY_ID")
	private long chronicTherapyId;

	@Column(name="CHRONIC_ID")
	private String chronicId;

    public EhfChronicCaseTherapyPK() {
    }
	public long getChronicTherapyId() {
		return this.chronicTherapyId;
	}
	public void setChronicTherapyId(long chronicTherapyId) {
		this.chronicTherapyId = chronicTherapyId;
	}
	public String getChronicId() {
		return this.chronicId;
	}
	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfChronicCaseTherapyPK)) {
			return false;
		}
		EhfChronicCaseTherapyPK castOther = (EhfChronicCaseTherapyPK)other;
		return 
			(this.chronicTherapyId == castOther.chronicTherapyId)
			&& this.chronicId.equals(castOther.chronicId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.chronicTherapyId ^ (this.chronicTherapyId >>> 32)));
		hash = hash * prime + this.chronicId.hashCode();
		
		return hash;
    }
}