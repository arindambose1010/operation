package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_COTD_CHKLST database table.
 * 
 */
@Embeddable
public class EhfCotdChklstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicId;
	private String chronicNo;

    public EhfCotdChklstPK() {
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

	public EhfCotdChklstPK(String chronicId, String chronicNo) {
		super();
		this.chronicId = chronicId;
		this.chronicNo = chronicNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfCotdChklstPK)) {
			return false;
		}
		EhfCotdChklstPK castOther = (EhfCotdChklstPK)other;
		return 
			this.chronicId.equals(castOther.chronicId)
			&& this.chronicNo.equals(castOther.chronicNo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicId.hashCode();
		hash = hash * prime + this.chronicNo.hashCode();
		
		return hash;
    }
}