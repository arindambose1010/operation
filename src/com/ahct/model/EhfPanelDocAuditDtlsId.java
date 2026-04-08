package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfPanelDocAuditDtlsId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pmtId;
	private Long actOrder;
	
	 public EhfPanelDocAuditDtlsId() {
	 }
	
	public EhfPanelDocAuditDtlsId(Long pmtId, Long actOrder) {
		this.pmtId = pmtId;
		this.actOrder = actOrder;
	}
	
	

	@Column(name = "W_ID", nullable = false)
	
	public Long getPmtId() {
		return pmtId;
	}

	public void setPmtId(Long pmtId) {
		this.pmtId = pmtId;
	}

	
	@Column(name = "ACT_ORDER", nullable = false)
	public Long getActOrder() {
		return actOrder;
	}
	

	
	public void setActOrder(Long actOrder) {
		this.actOrder = actOrder;
	}
	
	
	public boolean equals(Object other) {
	    if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EhfPanelDocAuditDtlsId) ) return false;
		 EhfPanelDocAuditDtlsId castOther = ( EhfPanelDocAuditDtlsId ) other; 
	    
		 return ( (this.getPmtId()==castOther.getPmtId()) || ( this.getPmtId()!=null && castOther.getPmtId()!=null && this.getPmtId().equals(castOther.getPmtId()) ) )
	&& ( (this.getActOrder()==castOther.getActOrder()) || ( this.getActOrder()!=null && castOther.getActOrder()!=null && this.getActOrder().equals(castOther.getActOrder()) ) );
	}

	public int hashCode() {
	    int result = 17;
	    
	    result = 37 * result + ( getPmtId() == null ? 0 : this.getPmtId().hashCode() );
	    result = 37 * result + ( getActOrder() == null ? 0 : this.getActOrder().hashCode() );
	    return result;
	}   

}
