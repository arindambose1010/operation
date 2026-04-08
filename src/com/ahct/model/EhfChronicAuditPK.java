package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_CHRONIC_AUDIT database table.
 * 
 */
@Embeddable
public class EhfChronicAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicId;
	private String chronicNo;
	private long actOrder;

    public EhfChronicAuditPK() {
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

	public EhfChronicAuditPK(String chronicId, String chronicNo, long actOrder) {
		super();
		this.chronicId = chronicId;
		this.chronicNo = chronicNo;
		this.actOrder = actOrder;
	}

	@Column(name="ACT_ORDER")
	public long getActOrder() {
		return this.actOrder;
	}
	public void setActOrder(long actOrder) {
		this.actOrder = actOrder;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfChronicAuditPK)) {
			return false;
		}
		EhfChronicAuditPK castOther = (EhfChronicAuditPK)other;
		return 
			this.chronicId.equals(castOther.chronicId)
			&& this.chronicNo.equals(castOther.chronicNo)
			&& (this.actOrder == castOther.actOrder);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicId.hashCode();
		hash = hash * prime + this.chronicNo.hashCode();
		hash = hash * prime + ((int) (this.actOrder ^ (this.actOrder >>> 32)));
		
		return hash;
    }
}