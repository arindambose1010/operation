package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfRevFundAuditId implements Serializable {

	private String rfPaymentId;
    private Long actOrder;


    @Column(name="RF_PAYMENT_ID",nullable=false,length=25)
    public String getRfPaymentId() {
		return rfPaymentId;
	}
	public void setRfPaymentId(String rfPaymentId) {
		this.rfPaymentId = rfPaymentId;
	}
	public void setActOrder(Long actOrder) {
        this.actOrder = actOrder;
    }
    @Column(name="ACT_ORDER",nullable=false,length=10 )
    public Long getActOrder() {
        return actOrder;
    }
}
