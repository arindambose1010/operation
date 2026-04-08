package com.ahct.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "EHF_REV_HOSP_DEDUCT")
public class EhfRevHospDeduct implements Serializable
{
    private EhfRevHospDeductPK id;
    private Long claimAmount;
    private Long toBeDeductAmount;
    private Long balanceAmt;
    private String lstUpdUsr;
    private Date lstUpdDt;


    public void setId(EhfRevHospDeductPK id) {
        this.id = id;
    }
    @EmbeddedId 
        @AttributeOverrides( {
            @AttributeOverride(name="hospId", column=@Column(name="HOSP_ID",nullable=false,length=20)),
            @AttributeOverride(name="deductType",column=@Column(name="DEDUCT_TYPE",nullable=false,length=10 )),
            @AttributeOverride(name="flagType",column=@Column(name="FLAG_TYPE",nullable=false,length=10 ))
        }
        ) 
    public EhfRevHospDeductPK getId() {
        return id;
    }

    public void setClaimAmount(Long claimAmount) {
        this.claimAmount = claimAmount;
    }
    @Column(name="CLAIM_AMOUNT")
    public Long getClaimAmount() {
        return claimAmount;
    }
   
    public void setToBeDeductAmount(Long toBeDeductAmount) {
        this.toBeDeductAmount = toBeDeductAmount;
    }
    @Column(name="TOBE_DEDUCT_AMT")
    public Long getToBeDeductAmount() {
        return toBeDeductAmount;
    }
    
    public void setBalanceAmt(Long balanceAmt) {
        this.balanceAmt = balanceAmt;
    }
    @Column(name="BALANCE_AMT")
    public Long getBalanceAmt() {
        return balanceAmt;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }
    
}
