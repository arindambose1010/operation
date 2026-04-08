package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_DENTAL_PROC_CRITERIA database table.
 * 
 */
@Embeddable
public class EhfmDentalProcCriteriaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String icdProcCode;
	private String schemeId;

    public EhfmDentalProcCriteriaPK() {
    }

	@Column(name="ICD_PROC_CODE")
	public String getIcdProcCode() {
		return this.icdProcCode;
	}
	public void setIcdProcCode(String icdProcCode) {
		this.icdProcCode = icdProcCode;
	}

	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmDentalProcCriteriaPK)) {
			return false;
		}
		EhfmDentalProcCriteriaPK castOther = (EhfmDentalProcCriteriaPK)other;
		return 
			this.icdProcCode.equals(castOther.icdProcCode)
			&& this.schemeId.equals(castOther.schemeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.icdProcCode.hashCode();
		hash = hash * prime + this.schemeId.hashCode();
		
		return hash;
    }
}