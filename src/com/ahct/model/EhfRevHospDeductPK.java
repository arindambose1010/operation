package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@SuppressWarnings("serial")
@Embeddable
public class EhfRevHospDeductPK implements Serializable
{
   
    private String hospId;
    private String deductType;
    private String flagType;
   
    
    public EhfRevHospDeductPK() 
    {
    }


    public void setHospId(String hospId) {
        this.hospId = hospId;
    }
    @Column(name="HOSP_ID")
    public String getHospId() {
        return hospId;
    }

    public void setDeductType(String deductType) {
        this.deductType = deductType;
    }
    @Column(name="DEDUCT_TYPE")
    public String getDeductType() {
        return deductType;
    }


    public void setFlagType(String flagType) {
        this.flagType = flagType;
    }
    @Column(name="FLAG_TYPE")
    public String getFlagType() {
        return flagType;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deductType == null) ? 0 : deductType.hashCode());
		result = prime * result
				+ ((flagType == null) ? 0 : flagType.hashCode());
		result = prime * result + ((hospId == null) ? 0 : hospId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfRevHospDeductPK other = (EhfRevHospDeductPK) obj;
		if (deductType == null) {
			if (other.deductType != null)
				return false;
		} else if (!deductType.equals(other.deductType))
			return false;
		if (flagType == null) {
			if (other.flagType != null)
				return false;
		} else if (!flagType.equals(other.flagType))
			return false;
		if (hospId == null) {
			if (other.hospId != null)
				return false;
		} else if (!hospId.equals(other.hospId))
			return false;
		return true;
	}
}
