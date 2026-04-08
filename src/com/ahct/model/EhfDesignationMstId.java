package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfDesignationMstId implements Serializable{
	
	private String hod;
	private String service;
	private String categoryName;
	private String primaryDesignation;
	private Long dsgnId;
	
	@Column(name = "DSGN_ID", nullable = false)
	public Long getDsgnId() {
		return dsgnId;
	}
	public void setDsgnId(Long dsgnId) {
		this.dsgnId = dsgnId;
	}
	@Column(name = "HOD", nullable = false)
	public String getHod() {
		return hod;
	}
	public void setHod(String hod) {
		this.hod = hod;
	}
	@Column(name = "SERVICE", nullable = false)
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	@Column(name = "CATEGORY_NAME", nullable = false)
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Column(name = "PRIMARY_DESIGNATION", nullable = true)
	public String getPrimaryDesignation() {
		return primaryDesignation;
	}
	public void setPrimaryDesignation(String primaryDesignation) {
		this.primaryDesignation = primaryDesignation;
	}
	
	public EhfDesignationMstId() {
		super();
	}
	public EhfDesignationMstId(String hod, String service, String categoryName,
			String primaryDesignation, Long dsgnId) {
		super();
		this.hod = hod;
		this.service = service;
		this.categoryName = categoryName;
		this.primaryDesignation = primaryDesignation;
		this.dsgnId = dsgnId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((dsgnId == null) ? 0 : dsgnId.hashCode());
		result = prime * result + ((hod == null) ? 0 : hod.hashCode());
		result = prime
				* result
				+ ((primaryDesignation == null) ? 0 : primaryDesignation
						.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
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
		EhfDesignationMstId other = (EhfDesignationMstId) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (dsgnId == null) {
			if (other.dsgnId != null)
				return false;
		} else if (!dsgnId.equals(other.dsgnId))
			return false;
		if (hod == null) {
			if (other.hod != null)
				return false;
		} else if (!hod.equals(other.hod))
			return false;
		if (primaryDesignation == null) {
			if (other.primaryDesignation != null)
				return false;
		} else if (!primaryDesignation.equals(other.primaryDesignation))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}
	

}
