package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_CHRONIC_DRUG_ISSUE_DTLS database table.
 * 
 */
@Embeddable
public class EhfChronicDrugIssueDtlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FOLLOWUP_ID")
	private String followupId;

	@Column(name="DRUG_CODE")
	private String drugCode;

    public EhfChronicDrugIssueDtlPK() {
    }
	public String getFollowupId() {
		return this.followupId;
	}
	public void setFollowupId(String followupId) {
		this.followupId = followupId;
	}
	public String getDrugCode() {
		return this.drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfChronicDrugIssueDtlPK)) {
			return false;
		}
		EhfChronicDrugIssueDtlPK castOther = (EhfChronicDrugIssueDtlPK)other;
		return 
			this.followupId.equals(castOther.followupId)
			&& this.drugCode.equals(castOther.drugCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.followupId.hashCode();
		hash = hash * prime + this.drugCode.hashCode();
		
		return hash;
    }
}