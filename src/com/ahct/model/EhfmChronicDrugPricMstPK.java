package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_CHRONIC_DRUG_PRIC_MST database table.
 * 
 */
@Embeddable
public class EhfmChronicDrugPricMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String drugCode;
	private String active;

    public EhfmChronicDrugPricMstPK() {
    }

	@Column(name="DRUG_CODE")
	public String getDrugCode() {
		return this.drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getActive() {
		return this.active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmChronicDrugPricMstPK)) {
			return false;
		}
		EhfmChronicDrugPricMstPK castOther = (EhfmChronicDrugPricMstPK)other;
		return 
			this.drugCode.equals(castOther.drugCode)
			&& this.active.equals(castOther.active);

    }
    
	public EhfmChronicDrugPricMstPK(String drugCode, String active) {
		super();
		this.drugCode = drugCode;
		this.active = active;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.drugCode.hashCode();
		hash = hash * prime + this.active.hashCode();
		
		return hash;
    }
}