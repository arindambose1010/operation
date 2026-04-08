package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfSsrMedinpDataPK implements Serializable {
	   
	 public String inpCode; 
	 public Long ssrCurVer;
	
	 @Column(name="INP_CODE",nullable=false)
	public String getInpCode() {
		return inpCode;
	}

	public void setInpCode(String inpCode) {
		this.inpCode = inpCode;
	}
	 @Column(name="SSR_CUR_VER",nullable=false)
	public Long getSsrCurVer() {
		return ssrCurVer;
	}

	public void setSsrCurVer(Long ssrCurVer) {
		this.ssrCurVer = ssrCurVer;
	}

	public EhfSsrMedinpDataPK(String inpCode, Long ssrCurVer) {
		super();
		this.inpCode = inpCode;
		this.ssrCurVer = ssrCurVer;
	}

	public EhfSsrMedinpDataPK() {
		super();
	}

  
}