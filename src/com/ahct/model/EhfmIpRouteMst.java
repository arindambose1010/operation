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
@Table(name="EHFM_IP_ROUTE_MST")
public class EhfmIpRouteMst implements Serializable {

	private String routeTypeCode;
	private String routeTypeName;
	private String routeCode;
	private String routeName;
	private String activeYN;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;

	public EhfmIpRouteMst() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmIpRouteMst(String routeTypeCode, String routeTypeName,
			String routeCode, String routeName, String activeYN, Date crtDt,
			String crtUsr, Date lstUpdDt, String lstUpdUsr) {
		super();
		this.routeTypeCode = routeTypeCode;
		this.routeTypeName = routeTypeName;
		this.routeCode = routeCode;
		this.routeName = routeName;
		this.activeYN = activeYN;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
	}

	@Column(name = "ROUTE_TYPE_CODE")
	public String getRouteTypeCode() {
		return routeTypeCode;
	}

	public void setRouteTypeCode(String routeTypeCode) {
		this.routeTypeCode = routeTypeCode;
	}

	@Column(name = "ROUTE_TYPE_NAME")
	public String getRouteTypeName() {
		return routeTypeName;
	}

	public void setRouteTypeName(String routeTypeName) {
		this.routeTypeName = routeTypeName;
	}
    @Id
	@Column(name = "ROUTE_CODE")
	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	@Column(name = "ROUTE_NAME")
	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
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
				+ ((routeCode == null) ? 0 : routeCode.hashCode());
		result = prime * result
				+ ((routeName == null) ? 0 : routeName.hashCode());
		result = prime * result
				+ ((routeTypeCode == null) ? 0 : routeTypeCode.hashCode());
		result = prime * result
				+ ((routeTypeName == null) ? 0 : routeTypeName.hashCode());
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
		EhfmIpRouteMst other = (EhfmIpRouteMst) obj;
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
		if (routeCode == null) {
			if (other.routeCode != null)
				return false;
		} else if (!routeCode.equals(other.routeCode))
			return false;
		if (routeName == null) {
			if (other.routeName != null)
				return false;
		} else if (!routeName.equals(other.routeName))
			return false;
		if (routeTypeCode == null) {
			if (other.routeTypeCode != null)
				return false;
		} else if (!routeTypeCode.equals(other.routeTypeCode))
			return false;
		if (routeTypeName == null) {
			if (other.routeTypeName != null)
				return false;
		} else if (!routeTypeName.equals(other.routeTypeName))
			return false;
		return true;
	}
}
