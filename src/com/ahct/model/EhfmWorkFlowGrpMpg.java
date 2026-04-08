package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
( name = "EHFM_WRKFLW_GRP_MPG" )
public class EhfmWorkFlowGrpMpg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String workFlowId;
	private String aprvlWorkFlowId;
	private String pndgWorkFlowId;
	private String rjctnWorkFlowId;
	
	
	 public EhfmWorkFlowGrpMpg() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	  
	public EhfmWorkFlowGrpMpg(String workFlowId, String aprvlWorkFlowId,
			String pndgWorkFlowId, String rjctnWorkFlowId) {
		super();
		this.workFlowId = workFlowId;
		this.aprvlWorkFlowId = aprvlWorkFlowId;
		this.pndgWorkFlowId = pndgWorkFlowId;
		this.rjctnWorkFlowId = rjctnWorkFlowId;
	}


	@Id
	 @Column(name = "WORKFLOW_ID", nullable = true, length = 50)
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	@Column(name = "APPROVAL_WORKFLOW_ID", nullable = true, length = 50)
	public String getAprvlWorkFlowId() {
		return aprvlWorkFlowId;
	}
	public void setAprvlWorkFlowId(String aprvlWorkFlowId) {
		this.aprvlWorkFlowId = aprvlWorkFlowId;
	}
	@Column(name = "PENDING_WORKFLOW_ID", nullable = true, length = 50)
	public String getPndgWorkFlowId() {
		return pndgWorkFlowId;
	}
	public void setPndgWorkFlowId(String pndgWorkFlowId) {
		this.pndgWorkFlowId = pndgWorkFlowId;
	}
	@Column(name = "REJECTION_WORKFLOW_ID", nullable = true, length = 50)
	public String getRjctnWorkFlowId() {
		return rjctnWorkFlowId;
	}
	public void setRjctnWorkFlowId(String rjctnWorkFlowId) {
		this.rjctnWorkFlowId = rjctnWorkFlowId;
	}
	
	
	

}
