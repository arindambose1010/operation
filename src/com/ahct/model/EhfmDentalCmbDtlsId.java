package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class EhfmDentalCmbDtlsId implements Serializable
{
	
	private String labelID;
	private String scheme;
	
	public EhfmDentalCmbDtlsId()
	{
		super();
	}
	
	public EhfmDentalCmbDtlsId(String labelID ,String scheme )
	{
		this.labelID=labelID;
		this.scheme=scheme;
	}
	@Column(name="label_ID",nullable=false)
	public String getLabelID() {
		return labelID;
	}
	public void setLabelID(String labelID) {
		this.labelID = labelID;
	}
	@Column(name="Scheme",nullable=false)
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((labelID == null) ? 0 : labelID.hashCode());
		result = prime * result
				+ ((scheme == null) ? 0 : scheme.hashCode());
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
		EhfmDentalCmbDtlsId other = (EhfmDentalCmbDtlsId) obj;
		if (labelID == null) {
			if (other.labelID!= null)
				return false;
		} else if (!labelID.equals(other.labelID))
			return false;
		if (scheme == null) {
			if (other.scheme != null)
				return false;
		} else if (!scheme.equals(other.scheme))
			return false;
		return true;
	}
	

}
