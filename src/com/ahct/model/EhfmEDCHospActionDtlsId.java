package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EhfmEDCHospActionDtlsId implements java.io.Serializable{

	

	@Column(name = "hosp_id", nullable = false)
	private String hospId;
	@Column(name = "scheme", nullable = false)
	private String scheme;
		
	public EhfmEDCHospActionDtlsId(){
	
		super();
	}
		
     public EhfmEDCHospActionDtlsId(String hospId, String scheme){
		
			super();
				
		this.hospId = hospId;
		this.scheme = scheme;
	}

public String getHospId() {
	return hospId;
}


public void setHospId(String hospId) {
	this.hospId = hospId;
}


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
	result = prime * result + ((hospId == null) ? 0 : hospId.hashCode());
	result = prime * result + ((scheme == null) ? 0 : scheme.hashCode());
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
	EhfmEDCHospActionDtlsId other = (EhfmEDCHospActionDtlsId) obj;
	if (hospId == null) {
		if (other.hospId != null)
			return false;
	} else if (!hospId.equals(other.hospId))
		return false;
	if (scheme == null) {
		if (other.scheme != null)
			return false;
	} else if (!scheme.equals(other.scheme))
		return false;
	return true;
}	
	
}
