package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_CHRONIC_THERAPY_INVEST database table.
 * 
 */
@Embeddable
public class EhfChronicTherapyInvestPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicId;
	private String investigationId;

    public EhfChronicTherapyInvestPK() {
    }

	@Column(name="CHRONIC_ID")
	public String getChronicId() {
		return this.chronicId;
	}
	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	@Column(name="INVESTIGATION_ID")
	public String getInvestigationId() {
		return this.investigationId;
	}
	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
	}

	public EhfChronicTherapyInvestPK(String chronicId, String investigationId) {
		super();
		this.chronicId = chronicId;
		this.investigationId = investigationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfChronicTherapyInvestPK)) {
			return false;
		}
		EhfChronicTherapyInvestPK castOther = (EhfChronicTherapyInvestPK)other;
		return 
			this.chronicId.equals(castOther.chronicId)
			&& this.investigationId.equals(castOther.investigationId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicId.hashCode();
		hash = hash * prime + this.investigationId.hashCode();
		
		return hash;
    }
}