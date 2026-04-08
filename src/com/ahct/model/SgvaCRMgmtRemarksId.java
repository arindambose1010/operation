package com.ahct.model;

import javax.persistence.Column;

public class SgvaCRMgmtRemarksId implements java.io.Serializable
{
    public SgvaCRMgmtRemarksId() {}
    
    private String remarksId;
    private Long lineItemNo;
    
    /** full constructor */
    public SgvaCRMgmtRemarksId(String remarksId,Long lineItemNo) {
        this.remarksId = remarksId;
        this.lineItemNo = lineItemNo;
    }
    
    public void setRemarksId(String remarksId) {
        this.remarksId = remarksId;
    }
  
    @Column(name="REMARKS_ID",nullable=false, length=50)
    public String getRemarksId() {
        return remarksId;
    }

    public void setLineItemNo(Long lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    @Column(name="LINEITEM_NO",nullable=false, length=10)
    public Long getLineItemNo() {
        return lineItemNo;
    }
    
    public boolean equals(Object other) {
          if ( (this == other ) ) return true;
          if ( (other == null ) ) return false;
          if ( !(other instanceof SgvaCRMgmtRemarksId) ) return false;
          SgvaCRMgmtRemarksId castOther = ( SgvaCRMgmtRemarksId ) other; 
          
          return ( ((this.getRemarksId()==castOther.getRemarksId()) || ( this.getRemarksId()!=null && castOther.getRemarksId()!=null && this.getRemarksId().equals(castOther.getRemarksId())) )          
          && ( (this.getLineItemNo()==castOther.getLineItemNo()) || ( this.getLineItemNo()!=null && castOther.getLineItemNo()!=null && this.getLineItemNo().equals(castOther.getLineItemNo()) )));
    }
    
    public int hashCode() {
          int result = 17;          
          result = 37 * result + ( getRemarksId() == null ? 0 : this.getRemarksId().hashCode() );
          result = 37 * result + ( getLineItemNo() == null ? 0 : this.getLineItemNo().hashCode() );          
          return result;
    }  
}
