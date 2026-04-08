package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the EHF_EMP_BIOSELFLOGIN_DTLS database table.
 * 
 */
@Embeddable
public class EhfEmpBioselfloginDtlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String userId;
	private java.util.Date loginDate;

    public EhfEmpBioselfloginDtlPK() {
    }

	@Column(name="USER_ID")
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

    @Temporal( TemporalType.DATE)
	@Column(name="LOGIN_DATE")
	public java.util.Date getLoginDate() {
		return this.loginDate;
	}
	public void setLoginDate(java.util.Date loginDate) {
		this.loginDate = loginDate;
	}

	public EhfEmpBioselfloginDtlPK(String userId, Date loginDate) {
		super();
		this.userId = userId;
		this.loginDate = loginDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfEmpBioselfloginDtlPK)) {
			return false;
		}
		EhfEmpBioselfloginDtlPK castOther = (EhfEmpBioselfloginDtlPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.loginDate.equals(castOther.loginDate);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.loginDate.hashCode();
		
		return hash;
    }
}