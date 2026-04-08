package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EhfmHospMithraDtlsId implements java.io.Serializable{
	
	private String hospId;
	private String mithraId;
	private Date startDt;
	@Column(name="hosp_id", nullable=false)
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	@Column(name="mithra_id", nullable=false)
	public String getMithraId() {
		return mithraId;
	}
	public void setMithraId(String mithraId) {
		this.mithraId = mithraId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_dt", nullable=false)
	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public EhfmHospMithraDtlsId(String hospId, String mithraId, Date startDt) {
		super();
		this.hospId = hospId;
		this.mithraId = mithraId;
		this.startDt = startDt;
	}
	public EhfmHospMithraDtlsId() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hospId == null) ? 0 : hospId.hashCode());
		result = prime * result
				+ ((mithraId == null) ? 0 : mithraId.hashCode());
		result = prime * result + ((startDt == null) ? 0 : startDt.hashCode());
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
		EhfmHospMithraDtlsId other = (EhfmHospMithraDtlsId) obj;
		if (hospId == null) {
			if (other.hospId != null)
				return false;
		} else if (!hospId.equals(other.hospId))
			return false;
		if (mithraId == null) {
			if (other.mithraId != null)
				return false;
		} else if (!mithraId.equals(other.mithraId))
			return false;
		if (startDt == null) {
			if (other.startDt != null)
				return false;
		} else if (!startDt.equals(other.startDt))
			return false;
		return true;
	}
	
}
