package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
( name = "EHFM_GRP_WORKFLOW" )
public class EhfmGrpWorkFlow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfmGrpWorkFlowId id;
	
	private String active;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpddt;
	private String grpId;
	
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "workFlowId", column = @Column(name = "WORKFLOW_ID", nullable = false, length = 25)),
			@AttributeOverride(name = "grpLevel", column = @Column(name = "GRP_LEVEL", nullable = false, length=25)) })
	public EhfmGrpWorkFlowId getId() {
		return id;
	}
	public void setId(EhfmGrpWorkFlowId id) {
		this.id = id;
	}
	
	@Column(name = "GRP_ID", nullable = true, length = 10)
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	

	@Column(name = "ACTIVE_YN", nullable = true, length = 10)
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Column(name = "CRT_USR", nullable = true, length = 10)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CRT_DT", nullable = true, length = 7)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name = "LST_UPD_USR", nullable = true, length = 10)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "LST_UPD_DT", nullable = true, length = 7)
	public Date getLstUpddt() {
		return lstUpddt;
	}
	public void setLstUpddt(Date lstUpddt) {
		this.lstUpddt = lstUpddt;
	}
	
	
	

}
