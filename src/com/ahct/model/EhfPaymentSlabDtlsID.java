package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfPaymentSlabDtlsID  implements Serializable {

	public EhfPaymentSlabDtlsID(){
		// TODO Auto-generated constructor stub
	}

	private String hospDeductorId; 
	private String scheme;
	
	@Column(name = "HOSP_DEDUCTOR_ID", nullable = false)
	public String getHospDeductorId() {
		return hospDeductorId;
	}

	public void setHospDeductorId(String hospDeductorId) {
		this.hospDeductorId = hospDeductorId;
	}
	@Column(name = "SCHEME", nullable = false)
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public EhfPaymentSlabDtlsID(String hospDeductorId, String scheme) {
		super();
		this.hospDeductorId = hospDeductorId;
		this.scheme = scheme;
	
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hospDeductorId == null) ? 0 : hospDeductorId.hashCode());		
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
		EhfPaymentSlabDtlsID other = (EhfPaymentSlabDtlsID) obj;
		if (hospDeductorId == null) {
			if (other.hospDeductorId != null)
				return false;
		} else if (!hospDeductorId.equals(other.hospDeductorId))
			return false;

		if (scheme == null) {
			if (other.scheme != null)
				return false;
		} else if (!scheme.equals(other.scheme))
			return false;
		return true;
	}
	
	
	
	
}
