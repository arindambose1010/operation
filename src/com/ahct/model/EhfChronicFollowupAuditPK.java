package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_CHRONIC_FOLLOWUP_AUDIT database table.
 * 
 */
@Embeddable
public class EhfChronicFollowupAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private long actOrder;
	private String chronicFollowupId;
	private String caseType;

    public EhfChronicFollowupAuditPK() {
    }

	@Column(name="ACT_ORDER")
	public long getActOrder() {
		return this.actOrder;
	}
	public void setActOrder(long actOrder) {
		this.actOrder = actOrder;
	}

	@Column(name="CHRONIC_FOLLOWUP_ID")
	public String getChronicFollowupId() {
		return this.chronicFollowupId;
	}
	public void setChronicFollowupId(String chronicFollowupId) {
		this.chronicFollowupId = chronicFollowupId;
	}

	@Column(name="CASE_TYPE")
	public String getCaseType() {
		return this.caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfChronicFollowupAuditPK)) {
			return false;
		}
		EhfChronicFollowupAuditPK castOther = (EhfChronicFollowupAuditPK)other;
		return 
			(this.actOrder == castOther.actOrder)
			&& this.chronicFollowupId.equals(castOther.chronicFollowupId)
			&& this.caseType.equals(castOther.caseType);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.actOrder ^ (this.actOrder >>> 32)));
		hash = hash * prime + this.chronicFollowupId.hashCode();
		hash = hash * prime + this.caseType.hashCode();
		
		return hash;
    }
}