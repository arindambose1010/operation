package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmFollowupPackagesId implements Serializable 
{
	private String surgeryId;
	private String schemeId;
	
	
	public EhfmFollowupPackagesId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EhfmFollowupPackagesId(String surgeryId, String schemeId) {
		super();
		this.surgeryId = surgeryId;
		this.schemeId = schemeId;
	}
	@Column(name="SURGERY_ID",nullable=false)
	public String getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(String surgeryId) {
		this.surgeryId = surgeryId;
	}
	@Column(name="SCHEME_ID",nullable=false)
	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	
	
}
