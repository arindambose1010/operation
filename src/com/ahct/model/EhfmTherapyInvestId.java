package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmTherapyInvestId implements Serializable {

	private String investigationId;
	private String ICDProcCode;
	private String asriSpec;
	private String schemeId;
	
	@Column(name="investigation_id",nullable=false)
    public String getInvestigationId() {
		return investigationId;
	}
	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
	}
	
	@Column(name="ICD_proc_code",nullable=false)
	 public String getICDProcCode() {
		return ICDProcCode;
	}
	public void setICDProcCode(String iCDProcCode) {
		ICDProcCode = iCDProcCode;
	}
	
	@Column(name="asri_spec",nullable=false)
	public String getAsriSpec() {
		return asriSpec;
	}
	public void setAsriSpec(String asriSpec) {
		this.asriSpec = asriSpec;
	}
	@Column(name="scheme_id",nullable=false)	
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public EhfmTherapyInvestId() {
		super();
	}
	public EhfmTherapyInvestId(String investigationId, String iCDProcCode,
			String asriSpec ,String schemeId) {
		super();
		this.investigationId = investigationId;
		ICDProcCode = iCDProcCode;
		this.asriSpec = asriSpec;
		this.schemeId = schemeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ICDProcCode == null) ? 0 : ICDProcCode.hashCode());
		result = prime * result
				+ ((asriSpec == null) ? 0 : asriSpec.hashCode());
		result = prime * result
				+ ((investigationId == null) ? 0 : investigationId.hashCode());
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
		EhfmTherapyInvestId other = (EhfmTherapyInvestId) obj;
		if (ICDProcCode == null) {
			if (other.ICDProcCode != null)
				return false;
		} else if (!ICDProcCode.equals(other.ICDProcCode))
			return false;
		if (asriSpec == null) {
			if (other.asriSpec != null)
				return false;
		} else if (!asriSpec.equals(other.asriSpec))
			return false;
		if (investigationId == null) {
			if (other.investigationId != null)
				return false;
		} else if (!investigationId.equals(other.investigationId))
			return false;
		return true;
	}
	

}
