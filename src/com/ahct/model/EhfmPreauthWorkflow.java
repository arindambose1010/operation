package com.ahct.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="Ehfm_Preauth_Workflow")
public class EhfmPreauthWorkflow implements Serializable{
	private EhfmPreauthWorkflowId id;
	private String nextStatus;
	private String actions;
	
	public void EhfmPreauthWorkflow()
	{}

	
	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="currentStatus", column=@Column(name="CURRENT_STATUS", nullable=false) ), 
	   @AttributeOverride(name="actionUsr", column=@Column(name="ACTION_USR", nullable=false) ),
	   @AttributeOverride(name="actiondone", column=@Column(name="ACTION_DONE", nullable=false) ) } )
	public EhfmPreauthWorkflowId getId() {
		return id;
	}
	public void setId(EhfmPreauthWorkflowId id) {
		this.id = id;
	}

	@Column(name="NEXT_STATUS")
	public String getNextStatus() {
		return nextStatus;
	}

	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}
	@Column(name="ACTIONS")
	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public EhfmPreauthWorkflow(EhfmPreauthWorkflowId id, String nextStatus,
			String actions) {
		super();
		this.id = id;
		this.nextStatus = nextStatus;
		this.actions = actions;
	}

	public EhfmPreauthWorkflow() {
		super();
		// TODO Auto-generated constructor stub
	}
}
