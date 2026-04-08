package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfPnlDocWrkFlowId implements Serializable{
	
	private Long wId;
	private String docId;
	
	public EhfPnlDocWrkFlowId(Long wId, String docId) {
		this.wId=wId;
		this.docId=docId;
	}
	public EhfPnlDocWrkFlowId() {
		// TODO Auto-generated constructor stub
	}
	@Column(name = "W_ID", nullable = false)
	public Long getwId() {
		return wId;
	}
	public void setwId(Long wId) {
		this.wId = wId;
	}
	
	@Column(name = "DOC_ID", nullable = false)
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	public boolean equals(Object other) {
	    if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EhfPnlDocWrkFlowId) ) return false;
		 EhfPnlDocWrkFlowId castOther = ( EhfPnlDocWrkFlowId ) other; 
	    
		 return ( (this.getwId()==castOther.getwId()) || ( this.getwId()!=null && castOther.getwId()!=null && this.getwId().equals(castOther.getwId()) ) )
	&& ( (this.getDocId()==castOther.getDocId()) || ( this.getDocId()!=null && castOther.getDocId()!=null && this.getDocId().equals(castOther.getDocId()) ) );
	}

	public int hashCode() {
	    int result = 17;
	    
	    result = 37 * result + ( getwId() == null ? 0 : this.getwId().hashCode() );
	    result = 37 * result + ( getDocId() == null ? 0 : this.getDocId().hashCode() );
	    return result;
	}   


}
