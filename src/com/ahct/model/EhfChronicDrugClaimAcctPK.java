package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_CHRONIC_DRUG_CLAIM_ACCTS database table.
 * 
 */
@Embeddable
public class EhfChronicDrugClaimAcctPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicDrugId;
	private String chronicNo;

    public EhfChronicDrugClaimAcctPK() {
    }

	@Column(name="CHRONIC_DRUG_ID")
	public String getchronicDrugId() {
		return this.chronicDrugId;
	}
	public void setchronicDrugId(String chronicDrugId) {
		this.chronicDrugId = chronicDrugId;
	}

	@Column(name="CHRONIC_NO")
	public String getChronicNo() {
		return this.chronicNo;
	}
	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}

	public EhfChronicDrugClaimAcctPK(String chronicDrugId, String chronicNo) {
		super();
		this.chronicDrugId = chronicDrugId;
		this.chronicNo = chronicNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfChronicDrugClaimAcctPK)) {
			return false;
		}
		EhfChronicDrugClaimAcctPK castOther = (EhfChronicDrugClaimAcctPK)other;
		return 
			this.chronicDrugId.equals(castOther.chronicDrugId)
			&& this.chronicNo.equals(castOther.chronicNo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicDrugId.hashCode();
		hash = hash * prime + this.chronicNo.hashCode();
		
		return hash;
    }
}