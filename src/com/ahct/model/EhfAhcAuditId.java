package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;


public class EhfAhcAuditId implements Serializable {
    public Long actOrder;
    public String ahcId;

    public EhfAhcAuditId() {
    }

    public EhfAhcAuditId(Long actOrder, String ahcId) {
        this.actOrder = actOrder;
        this.ahcId = ahcId;
    }
    
    public void setActOrder(Long actOrder) {
        this.actOrder = actOrder;
    }
    @Column(name="ACT_ORDER", nullable = false)
    public Long getActOrder() {
        return actOrder;
    }

    public void setAhcId(String ahcId) {
        this.ahcId = ahcId;
    }
    @Column(name="AHC_ID", nullable = false)
    public String getAhcId() {
        return ahcId;
    }

    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
                if ( (other == null ) ) return false;
                if ( !(other instanceof EhfAhcAuditId) ) return false;
                EhfAhcAuditId castOther = ( EhfAhcAuditId ) other; 
        
        return ( (this.getActOrder()==castOther.getActOrder() || ( this.getActOrder()!=null && castOther.getActOrder()!=null && this.getActOrder().equals(castOther.getActOrder()) ) )
        && ( (this.getAhcId()==castOther.getAhcId()) || ( this.getAhcId()!=null && castOther.getAhcId()!=null && this.getAhcId().equals(castOther.getAhcId()) ) ) );
      
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getActOrder() == null ? 0 : this.getActOrder().hashCode() );
        result = 37 * result + ( getAhcId() == null ? 0 : this.getAhcId().hashCode() );
        return result;
    }
}