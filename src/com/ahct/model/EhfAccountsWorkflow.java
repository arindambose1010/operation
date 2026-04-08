
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
@Table(name="EHF_ACCOUNTS_WORKFLOW")
public class EhfAccountsWorkflow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sanctionId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpddt;
	
	private String prevOwnerGrp;
	private String prevWorkFlowId;
	private String currWorkFlowId;
	private String currOwnerGrp;
	
	private String status;
	
	
	@Id
	@Column(name = "SANCTION_ID", nullable = false, length = 20)
	public String getSanctionId() {
		return sanctionId;
	}
	public void setSanctionId(String sanctionId) {
		this.sanctionId = sanctionId;
	}
	
	
	@Column(name = "CRT_USR", nullable = true, length = 10)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT", nullable = true, length = 7)
	public Date getLstUpddt() {
		return lstUpddt;
	}
	public void setLstUpddt(Date lstUpddt) {
		this.lstUpddt = lstUpddt;
	}
	
	@Column(name = "PREVIOUS_OWNER_GRP", nullable = true, length = 50)
	public String getPrevOwnerGrp() {
		return prevOwnerGrp;
	}
	public void setPrevOwnerGrp(String prevOwnerGrp) {
		this.prevOwnerGrp = prevOwnerGrp;
	}
	@Column(name = "PREVIOUS_WORKFLOW_ID", nullable = true, length = 50)
	public String getPrevWorkFlowId() {
		return prevWorkFlowId;
	}
	public void setPrevWorkFlowId(String prevWorkFlowId) {
		this.prevWorkFlowId = prevWorkFlowId;
	}
	@Column(name = "CURRENT_WORKFLOW_ID", nullable = true, length = 50)
	public String getCurrWorkFlowId() {
		return currWorkFlowId;
	}
	public void setCurrWorkFlowId(String currWorkFlowId) {
		this.currWorkFlowId = currWorkFlowId;
	}
	@Column(name = "CURRENT_OWNER_GRP", nullable = true, length = 50)
	public String getCurrOwnerGrp() {
		return currOwnerGrp;
	}
	public void setCurrOwnerGrp(String currOwnerGrp) {
		this.currOwnerGrp = currOwnerGrp;
	}
	
	@Column(name = "STATUS", nullable = true, length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
