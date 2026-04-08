package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_DUTY_DCTRS database table.
 * 
 */
@Embeddable
public class EhfmDutyDctrsId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String regNum;
	private String hospId;
	private String scheme;

    public EhfmDutyDctrsId() {
    }

	@Column(name="REG_NUM")
	public String getRegNum() {
		return this.regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	@Column(name="HOSP_ID")
	public String getHospId() {
		return this.hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getScheme() {
		return this.scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmDutyDctrsId)) {
			return false;
		}
		EhfmDutyDctrsId castOther = (EhfmDutyDctrsId)other;
		return 
			this.regNum.equals(castOther.regNum)
			&& this.hospId.equals(castOther.hospId)
			&& this.scheme.equals(castOther.scheme);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.regNum.hashCode();
		hash = hash * prime + this.hospId.hashCode();
		hash = hash * prime + this.scheme.hashCode();
		
		return hash;
    }
}