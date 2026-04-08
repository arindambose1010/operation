package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_ROUTE_MST database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_ROUTE_MST")
public class EhfmChronicRouteMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private String routeCode;
	private String activeYn;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String routeName;
	private String routeTypeCode;
	private String routeTypeName;

    public EhfmChronicRouteMst() {
    }


	@Id
	@Column(name="ROUTE_CODE")
	public String getRouteCode() {
		return this.routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}


	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="ROUTE_NAME")
	public String getRouteName() {
		return this.routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}


	@Column(name="ROUTE_TYPE_CODE")
	public String getRouteTypeCode() {
		return this.routeTypeCode;
	}

	public void setRouteTypeCode(String routeTypeCode) {
		this.routeTypeCode = routeTypeCode;
	}


	@Column(name="ROUTE_TYPE_NAME")
	public String getRouteTypeName() {
		return this.routeTypeName;
	}

	public void setRouteTypeName(String routeTypeName) {
		this.routeTypeName = routeTypeName;
	}

}