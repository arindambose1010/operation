package com.ahct.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfOpdClaimAuditPK implements Serializable {
    @Column(name = "OP_CLAIM_SEQ", length = 20)
    private String opClaimSeq;
    @Column(name = "ACT_ORDER", precision = 38, scale = 0)
    private Double actOrder;
	// Default constructor
    public EhfOpdClaimAuditPK() {}
	// Parameterized constructor
    public EhfOpdClaimAuditPK(String opClaimSeq, Double actOrder) {
        this.opClaimSeq = opClaimSeq;
        this.actOrder = actOrder;
    }
	// Getters and Setters
    public String getOpClaimSeq() {
        return opClaimSeq;
    }
    public void setOpClaimSeq(String opClaimSeq) {
        this.opClaimSeq = opClaimSeq;
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
        return opClaimSeq.hashCode() + actOrder.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EhfOpdClaimAuditPK that = (EhfOpdClaimAuditPK) obj;
        return opClaimSeq.equals(that.opClaimSeq) && actOrder.equals(that.actOrder);
    }
}
