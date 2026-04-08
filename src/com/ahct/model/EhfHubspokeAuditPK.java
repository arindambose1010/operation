package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfHubspokeAuditPK implements Serializable
{
	private String hubspokePaymentId;
    private Long actOrder;
    
    
	public String getHubspokePaymentId() {
		return hubspokePaymentId;
	}
	public void setHubspokePaymentId(String hubspokePaymentId) {
		this.hubspokePaymentId = hubspokePaymentId;
	}
	public Long getActOrder() {
		return actOrder;
	}
	public void setActOrder(Long actOrder) {
		this.actOrder = actOrder;
	}
    
    
    
    
}
