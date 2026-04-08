package com.ahct.model;

import java.io.Serializable;
import javax.persistence.Column;

public class EhfFlagAuditId implements Serializable {


	private String flagId;
	private long actionOrder;
	
	
	public EhfFlagAuditId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfFlagAuditId(String flagId, long actionOrder) {
		super();
		this.flagId = flagId;
		this.actionOrder = actionOrder;
	}

	
	@Column(name="FLAG_ID",nullable=false)
	public String getFlagId() {
		return flagId;
	}

	public void setFlagId(String flagId) {
		this.flagId = flagId;
	}

	@Column(name="ACTION_ORDER",nullable=false)
	public long getActionOrder() {
		return actionOrder;
	}

	public void setActionOrder(long actionOrder) {
		this.actionOrder = actionOrder;
	}
	
	
	}