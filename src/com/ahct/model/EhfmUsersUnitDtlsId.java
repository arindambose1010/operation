package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EhfmUsersUnitDtlsId implements Serializable {

	@Column(name = "UNIT_ID", nullable = false, length = 50)
	private String unitId;
	@Column(name = "USER_ID", nullable = false, length = 50)
	private String userId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_dt", nullable = false, length = 7)
	private Date startDt;	

	public EhfmUsersUnitDtlsId(String unitId, String userId, Date startDt) {
		super();
		this.unitId = unitId;
		this.userId = userId;
		this.startDt = startDt;
	}

	public EhfmUsersUnitDtlsId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startDt == null) ? 0 : startDt.hashCode());
		result = prime * result + ((unitId == null) ? 0 : unitId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		EhfmUsersUnitDtlsId other = (EhfmUsersUnitDtlsId) obj;
		if (startDt == null) {
			if (other.startDt != null)
				return false;
		} else if (!startDt.equals(other.startDt))
			return false;
		if (unitId == null) {
			if (other.unitId != null)
				return false;
		} else if (!unitId.equals(other.unitId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	
	
}
