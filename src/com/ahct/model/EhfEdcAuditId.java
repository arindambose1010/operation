package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfEdcAuditId implements Serializable {
	

	private String actionId;
	private Integer actOrder;
	private Integer remarksOrder;
	
	
	
	public EhfEdcAuditId(String actionId, Integer actOrder, Integer remarksOrder) {
		super();
		this.actionId = actionId;
		this.actOrder = actOrder;
		this.remarksOrder = remarksOrder;
	}
	public EhfEdcAuditId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Column(name="ACTION_ID",nullable=false)
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@Column(name="ACT_ORDER",nullable=false)
	public Integer getActOrder() {
		return actOrder;
	}
	public void setActOrder(Integer actOrder) {
		this.actOrder = actOrder;
	}
	
	@Column(name="REMARKS_ORDER",nullable=false)
	public Integer getRemarksOrder() {
		return remarksOrder;
	}
	public void setRemarksOrder(Integer remarksOrder) {
		this.remarksOrder = remarksOrder;
	}
	
	

	
}
