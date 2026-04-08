package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmDrugsMstId implements Serializable {

	
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String drugCode;
	
	public EhfmDrugsMstId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmDrugsMstId(String drugTypeCode, String drugSubTypeCode,
			String drugCode) {
		super();
		this.drugTypeCode = drugTypeCode;
		this.drugSubTypeCode = drugSubTypeCode;
		this.drugCode = drugCode;
	}
	
	@Column(name="DRUG_TYPE_CODE")
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	@Column(name="DRUG_SUB_TYPE_CODE")
	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}

	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}
	@Column(name="DRUG_CODE")
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drugTypeCode == null) ? 0 : drugTypeCode.hashCode());
		result = prime * result
				+ ((drugSubTypeCode == null) ? 0 : drugSubTypeCode.hashCode());
		result = prime * result
				+ ((drugCode == null) ? 0 : drugCode.hashCode());
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
		EhfmDrugsMstId other = (EhfmDrugsMstId) obj;
		if (drugTypeCode == null) {
			if (other.drugTypeCode != null)
				return false;
		} else if (!drugTypeCode.equals(other.drugTypeCode))
			return false;
		if (drugSubTypeCode == null) {
			if (other.drugSubTypeCode != null)
				return false;
		} else if (!drugSubTypeCode.equals(other.drugSubTypeCode))
			return false;
		if (drugCode == null) {
			if (other.drugCode != null)
				return false;
		} else if (!drugCode.equals(other.drugCode))
			return false;
		return true;
	}
}
