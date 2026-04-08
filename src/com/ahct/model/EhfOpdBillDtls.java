package com.ahct.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EHF_OPD_BILL_DTLS")
public class EhfOpdBillDtls {

    @Id
    @Column(name = "BILL_NO")
    private String billNo;

    @Column(name = "CLAIM_NO", length = 200)
    private String claimNo;

    @Column(name = "CR_NO", length = 100)
    private String crNo;

    @Column(name = "CLAIM_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDt;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "CRT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDt;

    @Column(name = "CRT_USR", length = 100)
    private String crtUsr;

    @Column(name = "LST_UPD_DT")
    @Temporal(TemporalType.TIMESTAMP)//Chandana - changed from DATE to TIMESTAMP(to store the time stamp also along with the date)
    private Date lstUpdDt;

    @Column(name = "LST_UPD_USR", length = 100)
    private String lstUpdUsr;

    @Column(name = "PEX_REMARKS")
    private String pexRemarks;

    // Getters and Setters
    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    public String getCrNo() {
        return crNo;
    }

    public void setCrNo(String crNo) {
        this.crNo = crNo;
    }

    public Date getClaimDt() {
        return claimDt;
    }

    public void setClaimDt(Date claimDt) {
        this.claimDt = claimDt;
    }

    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    
}

