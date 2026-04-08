package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHFM_OP_STRENGTH_MST")
public class EhfmOpStrengthMst implements Serializable {

	private String strengthUnitCode;
	private String strengthUnitName;
	private String strengthCode;
	private String strengthName;
	private String activeYN;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;

	public EhfmOpStrengthMst() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmOpStrengthMst(String strengthUnitCode, String strengthUnitName,
			String strengthCode, String strengthName, String activeYN,
			Date crtDt, String crtUsr, Date lstUpdDt, String lstUpdUsr) {
		super();
		this.strengthUnitCode = strengthUnitCode;
		this.strengthUnitName = strengthUnitName;
		this.strengthCode = strengthCode;
		this.strengthName = strengthName;
		this.activeYN = activeYN;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
	}

	@Column(name = "STRENGTH_UNIT_CODE")
	public String getStrengthUnitCode() {
		return strengthUnitCode;
	}

	public void setStrengthUnitCode(String strengthUnitCode) {
		this.strengthUnitCode = strengthUnitCode;
	}

	@Column(name = "STRENGTH_UNIT_NAME")
	public String getStrengthUnitName() {
		return strengthUnitName;
	}

	public void setStrengthUnitName(String strengthUnitName) {
		this.strengthUnitName = strengthUnitName;
	}

	@Id
	@Column(name = "STRENGTH_CODE")
	public String getStrengthCode() {
		return strengthCode;
	}

	public void setStrengthCode(String strengthCode) {
		this.strengthCode = strengthCode;
	}

	@Column(name = "STRENGTH_NAME")
	public String getStrengthName() {
		return strengthName;
	}

	public void setStrengthName(String strengthName) {
		this.strengthName = strengthName;
	}

	@Column(name = "active_yn")
	public String getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}

	@Column(name = "CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activeYN == null) ? 0 : activeYN.hashCode());
		result = prime * result + ((crtDt == null) ? 0 : crtDt.hashCode());
		result = prime * result + ((crtUsr == null) ? 0 : crtUsr.hashCode());
		result = prime * result
				+ ((lstUpdDt == null) ? 0 : lstUpdDt.hashCode());
		result = prime * result
				+ ((lstUpdUsr == null) ? 0 : lstUpdUsr.hashCode());
		result = prime * result
				+ ((strengthCode == null) ? 0 : strengthCode.hashCode());
		result = prime * result
				+ ((strengthName == null) ? 0 : strengthName.hashCode());
		result = prime
				* result
				+ ((strengthUnitCode == null) ? 0 : strengthUnitCode.hashCode());
		result = prime
				* result
				+ ((strengthUnitName == null) ? 0 : strengthUnitName.hashCode());
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
		EhfmOpStrengthMst other = (EhfmOpStrengthMst) obj;
		if (activeYN == null) {
			if (other.activeYN != null)
				return false;
		} else if (!activeYN.equals(other.activeYN))
			return false;
		if (crtDt == null) {
			if (other.crtDt != null)
				return false;
		} else if (!crtDt.equals(other.crtDt))
			return false;
		if (crtUsr == null) {
			if (other.crtUsr != null)
				return false;
		} else if (!crtUsr.equals(other.crtUsr))
			return false;
		if (lstUpdDt == null) {
			if (other.lstUpdDt != null)
				return false;
		} else if (!lstUpdDt.equals(other.lstUpdDt))
			return false;
		if (lstUpdUsr == null) {
			if (other.lstUpdUsr != null)
				return false;
		} else if (!lstUpdUsr.equals(other.lstUpdUsr))
			return false;
		if (strengthCode == null) {
			if (other.strengthCode != null)
				return false;
		} else if (!strengthCode.equals(other.strengthCode))
			return false;
		if (strengthName == null) {
			if (other.strengthName != null)
				return false;
		} else if (!strengthName.equals(other.strengthName))
			return false;
		if (strengthUnitCode == null) {
			if (other.strengthUnitCode != null)
				return false;
		} else if (!strengthUnitCode.equals(other.strengthUnitCode))
			return false;
		if (strengthUnitName == null) {
			if (other.strengthUnitName != null)
				return false;
		} else if (!strengthUnitName.equals(other.strengthUnitName))
			return false;
		return true;
	}

}
