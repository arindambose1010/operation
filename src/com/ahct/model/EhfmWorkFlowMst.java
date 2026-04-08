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
@Table
( name = "EHFM_WRKFLW_MST" )
public class EhfmWorkFlowMst implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String workFlowId;
	private String workFlowName;
	private String deptId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpddt;
	private Date workFlowStartdt;
	private Date workFlowEnddt;
	
	
	public EhfmWorkFlowMst() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public EhfmWorkFlowMst(String workFlowId, String workFlowName,
			String deptId, String crtUsr, Date crtDt, String lstUpdUsr,
			Date lstUpddt, Date workFlowStartdt, Date workFlowEnddt) {
		super();
		this.workFlowId = workFlowId;
		this.workFlowName = workFlowName;
		this.deptId = deptId;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpddt = lstUpddt;
		this.workFlowStartdt = workFlowStartdt;
		this.workFlowEnddt = workFlowEnddt;
	}

	 @Id
	 @Column(name = "WORKFLOW_ID", nullable = true, length = 50)
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	 @Column(name = "WORKFLOW_NAME", nullable = true, length = 100)
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	 @Column(name = "DEPT_ID", nullable = true, length = 100)
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	@Temporal(TemporalType.DATE)
	@Column(name = "WORKFLOW_START_DATE", nullable = true, length = 7)
	public Date getWorkFlowStartdt() {
		return workFlowStartdt;
	}
	public void setWorkFlowStartdt(Date workFlowStartdt) {
		this.workFlowStartdt = workFlowStartdt;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "WORKFLOW_END_DATE", nullable = true, length = 7)
	public Date getWorkFlowEnddt() {
		return workFlowEnddt;
	}
	public void setWorkFlowEnddt(Date workFlowEnddt) {
		this.workFlowEnddt = workFlowEnddt;
	}
	

	
}
