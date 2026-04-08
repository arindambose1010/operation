package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfTdsAuditId implements Serializable{

    private String tdsPaymentId;
    private Long actOrder;


    public void setTdsPaymentId(String tdsPaymentId) {
        this.tdsPaymentId = tdsPaymentId;
    }
    @Column(name="TDS_PAYMENT_ID",nullable=false,length=25)
    public String getTdsPaymentId() {
        return tdsPaymentId;
    }

    public void setActOrder(Long actOrder) {
        this.actOrder = actOrder;
    }
    @Column(name="ACT_ORDER",nullable=false,length=10 )
    public Long getActOrder() {
        return actOrder;
    }
}
