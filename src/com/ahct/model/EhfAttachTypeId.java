package com.ahct.model;

import javax.persistence.Column;

public class EhfAttachTypeId implements java.io.Serializable{
	private String docType;
	private String updType;
	@Column(name="doc_type", nullable=false, length=20)
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	@Column(name="ATTCH_TYPE", nullable=false, length=20)
	public String getUpdType() {
		return updType;
	}
	public void setUpdType(String updType) {
		this.updType = updType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docType == null) ? 0 : docType.hashCode());
		result = prime * result + ((updType == null) ? 0 : updType.hashCode());
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
		EhfAttachTypeId other = (EhfAttachTypeId) obj;
		if (docType == null) {
			if (other.docType != null)
				return false;
		} else if (!docType.equals(other.docType))
			return false;
		if (updType == null) {
			if (other.updType != null)
				return false;
		} else if (!updType.equals(other.updType))
			return false;
		return true;
	}
	public EhfAttachTypeId(String docType, String updType) {
		super();
		this.docType = docType;
		this.updType = updType;
	}
	public EhfAttachTypeId() {
		super();
	}


}
