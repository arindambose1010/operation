package com.ahct.model;

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
(name = "EHFM_WORKFLOW_STATUS")
public class EhfmWorkflowStatus implements java.io.Serializable {

	private EhfmWorkflowStatusId id;
	private String nextStatus;
	private String activeYn;
	private String crtUsr;
	private Date crtDt;
	
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "currStatus", column = @Column(name = "current_status", nullable = false, length = 10)),
			@AttributeOverride(name = "currRole", column = @Column(name = "current_role", nullable = false, length = 10)),
			@AttributeOverride(name = "actionDone", column = @Column(name = "action_done", nullable = false, length = 10))
	})	
	public EhfmWorkflowStatusId getId() {
		return id;
	}
	public void setId(EhfmWorkflowStatusId id) {
		this.id = id;
	}
	@Column(name = "next_status")
	public String getNextStatus() {
		return nextStatus;
	}
	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}
	@Column(name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="crt_usr")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="crt_dt")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
}
