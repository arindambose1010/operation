package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_CHRONIC_PATIENT_DRUGS database table.
 * 
 */
@Embeddable
public class EhfChronicPatientDrugPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicId;
	private String chronicNo;
	private long drugId;

    public EhfChronicPatientDrugPK() {
    }

	@Column(name="CHRONIC_ID")
	public String getChronicId() {
		return this.chronicId;
	}
	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	@Column(name="CHRONIC_NO")
	public String getChronicNo() {
		return this.chronicNo;
	}
	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}

	@Column(name="DRUG_ID")
	public long getDrugId() {
		return this.drugId;
	}
	public void setDrugId(long drugId) {
		this.drugId = drugId;
	}

	public EhfChronicPatientDrugPK(String chronicId, String chronicNo,
			long drugId) {
		super();
		this.chronicId = chronicId;
		this.chronicNo = chronicNo;
		this.drugId = drugId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfChronicPatientDrugPK)) {
			return false;
		}
		EhfChronicPatientDrugPK castOther = (EhfChronicPatientDrugPK)other;
		return 
			this.chronicId.equals(castOther.chronicId)
			&& this.chronicNo.equals(castOther.chronicNo)
			&& (this.drugId == castOther.drugId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicId.hashCode();
		hash = hash * prime + this.chronicNo.hashCode();
		hash = hash * prime + ((int) (this.drugId ^ (this.drugId >>> 32)));
		
		return hash;
    }
}