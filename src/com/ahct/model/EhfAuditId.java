package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;


public class EhfAuditId implements Serializable {
    public Long actOrder;
    public String caseId;

    public EhfAuditId() {
    }

    public EhfAuditId(Long actOrder, String caseId) {
        this.actOrder = actOrder;
        this.caseId = caseId;
    }
    
    public void setActOrder(Long actOrder) {
        this.actOrder = actOrder;
    }
    @Column(name="ACT_ORDER", nullable = false)
    public Long getActOrder() {
        return actOrder;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    @Column(name="CASE_ID", nullable = false)
    public String getCaseId() {
        return caseId;
    }

    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
                if ( (other == null ) ) return false;
                if ( !(other instanceof EhfAuditId) ) return false;
                EhfAuditId castOther = ( EhfAuditId ) other; 
        
        return ( (this.getActOrder()==castOther.getActOrder() || ( this.getActOrder()!=null && castOther.getActOrder()!=null && this.getActOrder().equals(castOther.getActOrder()) ) )
        && ( (this.getCaseId()==castOther.getCaseId()) || ( this.getCaseId()!=null && castOther.getCaseId()!=null && this.getCaseId().equals(castOther.getCaseId()) ) ) );
      
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getActOrder() == null ? 0 : this.getActOrder().hashCode() );
        result = 37 * result + ( getCaseId() == null ? 0 : this.getCaseId().hashCode() );
        return result;
    }
}