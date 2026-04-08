package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EhfmUsrDsgnDtlsId implements Serializable {

	@Column(name = "user_id", nullable = false, length = 12)
	private String userId;
	@Column(name = "DSGN_ID", nullable = false, length = 12)
	private String dsgnId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DT", nullable = false, length = 7)
	private Date startDt;

	public EhfmUsrDsgnDtlsId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmUsrDsgnDtlsId(String userId, String dsgnId, Date startDt) {
		super();
		this.userId = userId;
		this.dsgnId = dsgnId;
		this.startDt = startDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDsgnId() {
		return dsgnId;
	}

	public void setDsgnId(String dsgnId) {
		this.dsgnId = dsgnId;
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
		result = prime * result + ((dsgnId == null) ? 0 : dsgnId.hashCode());
		result = prime * result + ((startDt == null) ? 0 : startDt.hashCode());
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
		EhfmUsrDsgnDtlsId other = (EhfmUsrDsgnDtlsId) obj;
		if (dsgnId == null) {
			if (other.dsgnId != null)
				return false;
		} else if (!dsgnId.equals(other.dsgnId))
			return false;
		if (startDt == null) {
			if (other.startDt != null)
				return false;
		} else if (!startDt.equals(other.startDt))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
