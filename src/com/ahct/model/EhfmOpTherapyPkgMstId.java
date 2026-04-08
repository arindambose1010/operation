package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class EhfmOpTherapyPkgMstId implements Serializable {

	private String icdOpPkgCode;
	private String icdCatCode;
	
	public EhfmOpTherapyPkgMstId() {
		super();
		// TODO Auto-generated constructor stub
	}
		public EhfmOpTherapyPkgMstId(String icdOpPkgCode, String icdCatCode) {
		super();
		this.icdOpPkgCode = icdOpPkgCode;
		this.icdCatCode = icdCatCode;
	}
	@Column(name="ICD_OP_PKG_CODE")
	public String getIcdOpPkgCode() {
		return icdOpPkgCode;
	}
	public void setIcdOpPkgCode(String icdOpPkgCode) {
		this.icdOpPkgCode = icdOpPkgCode;
	}
	@Column(name="ICD_Cat_Code")
	public String getIcdCatCode() {
		return icdCatCode;
	}
	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((icdOpPkgCode == null) ? 0 : icdOpPkgCode.hashCode());
		result = prime * result
				+ ((icdCatCode == null) ? 0 : icdCatCode.hashCode());
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
		EhfmOpTherapyPkgMstId other = (EhfmOpTherapyPkgMstId) obj;
		if (icdOpPkgCode == null) {
			if (other.icdOpPkgCode != null)
				return false;
		} else if (!icdOpPkgCode.equals(other.icdOpPkgCode))
			return false;
		if (icdCatCode == null) {
			if (other.icdCatCode != null)
				return false;
		} else if (!icdCatCode.equals(other.icdCatCode))
			return false;
		return true;
	}
}
