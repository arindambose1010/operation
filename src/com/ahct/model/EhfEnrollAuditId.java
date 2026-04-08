package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfEnrollAuditId implements Serializable 
{
  private String enrollId ;
  private int enrollOrder ;
  public EhfEnrollAuditId ()
  {
  }
  
  public EhfEnrollAuditId (String enrollId ,int enrollOrder)
  {
  this.enrollId=enrollId;
  this.enrollOrder=enrollOrder;
  }
  public void setEnrollId( String enrollId )
  {
    this.enrollId = enrollId;
  }
  
  @Column(name="ENROLL_ID", nullable=false, length=10)
  public String getEnrollId ()
  {
    return enrollId ;
  }
  public void setEnrollOrder ( int enrollOrder )
  {
    this.enrollOrder = enrollOrder;
  }
  
  @Column(name="ENROLL_ORDER", nullable=false,length=4)
  public int getEnrollOrder ()
  {
    return enrollOrder ;
  }
  
  public boolean equals ( Object other )
  {
    if ( ( this == other ) )
      return true ;
    if ( ( other == null ) )
      return false ;
    if ( !( other instanceof EhfEnrollAuditId ) )
      return false ;
    EhfEnrollAuditId castOther = ( EhfEnrollAuditId ) other ;
    
    return ( (this.getEnrollId()==castOther.getEnrollId()) || ( this.getEnrollId()!=null && castOther.getEnrollId()!=null && this.getEnrollId().equals(castOther.getEnrollId()) ) )
    && ( (this.getEnrollOrder()==castOther.getEnrollOrder()) || ( this.getEnrollOrder()!=0 && castOther.getEnrollOrder()!=0 &&( this.getEnrollOrder()==castOther.getEnrollOrder()))  );

  }

  public int hashCode ()
  {
    int result = 17 ;
    result = 37 * result + ( getEnrollId() == null ? 0 : this.getEnrollId ().hashCode () ) ;
    result = 37 * result + ( getEnrollOrder () == 0 ? 0 : this.getEnrollOrder() ) ;
    return result ;
  }
}
