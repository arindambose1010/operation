package com.ahct.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SgvaCRMgmtAttachId implements java.io.Serializable
{
    public SgvaCRMgmtAttachId() {}
    
    private String crId;
    private Long lineItemNo;
    
    /** full constructor */
    public SgvaCRMgmtAttachId(String crId,Long lineItemNo) {
        this.crId = crId;
        this.lineItemNo = lineItemNo;
    }
    
    public void setCrId(String crId) {
        this.crId = crId;
    }
   
    @Column(name="CR_ID",nullable=false, length=100)
    public String getCrId() {
        return crId;
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
          if ( !(other instanceof SgvaCRMgmtAttachId) ) return false;
          SgvaCRMgmtAttachId castOther = ( SgvaCRMgmtAttachId ) other; 
          
          return ( ((this.getCrId()==castOther.getCrId()) || ( this.getCrId()!=null && castOther.getCrId()!=null && this.getCrId().equals(castOther.getCrId())) )          
          && ( (this.getLineItemNo()==castOther.getLineItemNo()) || ( this.getLineItemNo()!=null && castOther.getLineItemNo()!=null && this.getLineItemNo().equals(castOther.getLineItemNo()) )));
    }
    
    public int hashCode() {
          int result = 17;          
          result = 37 * result + ( getCrId() == null ? 0 : this.getCrId().hashCode() );
          result = 37 * result + ( getLineItemNo() == null ? 0 : this.getLineItemNo().hashCode() );          
        System.out.println(result);
          return result;          
    }  
}
