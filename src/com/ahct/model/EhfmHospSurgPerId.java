package com.ahct.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfmHospSurgPerId  implements Serializable{

	private String hospId;
    private String surgeryId;
    private String claimType;
    private String deductorType;
    private String scheme;


    public EhfmHospSurgPerId(String hospId, String surgeryId, String claimType,
			String deductorType,String scheme) {
		super();
		this.hospId = hospId;
		this.surgeryId = surgeryId;
		this.claimType = claimType;
		this.deductorType = deductorType;
		this.scheme = scheme;
	}
    
    
    
	public EhfmHospSurgPerId() {
		super();
		// TODO Auto-generated constructor stub
	}



	public void setHospId(String hospId) {
        this.hospId = hospId;
    }
    @Column(name="HOSP_ID")
    public String getHospId() {
        return hospId;
    }

    public void setSurgeryId(String surgeryId) {
        this.surgeryId = surgeryId;
    }
    @Column(name="SURGERY_ID")
    public String getSurgeryId() {
        return surgeryId;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }
    @Column(name="CLAIM_TYPE")
    public String getClaimType() {
        return claimType;
    }

    public void setDeductorType(String deductorType) {
        this.deductorType = deductorType;
    }
    @Column(name="DEDUCTOR_TYPE")
    public String getDeductorType() {
        return deductorType;
    }


    @Column(name="SCHEME")
	public String getScheme() {
		return scheme;
	}



	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
    
    
}
