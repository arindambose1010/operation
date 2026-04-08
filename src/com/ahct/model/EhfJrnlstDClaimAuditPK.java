package com.ahct.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfJrnlstDClaimAuditPK implements Serializable {
    @Column(name = "CLAIM_SEQ", length = 20)
    private String claimSeq;
    @Column(name = "ACT_ORDER", precision = 38, scale = 0)
    private Double actOrder;
	// Default constructor
    public EhfJrnlstDClaimAuditPK() {}
	// Parameterized constructor
    public EhfJrnlstDClaimAuditPK(String claimSeq, Double actOrder) {
        this.claimSeq = claimSeq;
        this.actOrder = actOrder;
    }
	// Getters and Setters
    public String getClaimSeq() {
        return claimSeq;
    }
    public void setClaimSeq(String claimSeq) {
        this.claimSeq = claimSeq;
    }
    public Double getActOrder() {
        return actOrder;
    }
    public void setActOrder(Double actOrder) {
        this.actOrder = actOrder;
    }
	// hashCode and equals methods for composite key comparison
    @Override
    public int hashCode() {
        return claimSeq.hashCode() + actOrder.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EhfJrnlstDClaimAuditPK that = (EhfJrnlstDClaimAuditPK) obj;
        return claimSeq.equals(that.claimSeq) && actOrder.equals(that.actOrder);
    }
}
