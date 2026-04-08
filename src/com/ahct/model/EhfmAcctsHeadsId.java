package com.ahct.model;

import javax.persistence.Column;

public class EhfmAcctsHeadsId implements java.io.Serializable
{
 private String headId;
 private String scheme;
 
//Constructors

	 /** default constructor */
	 public EhfmAcctsHeadsId() {
	 }

	 /** full constructor */
	 public EhfmAcctsHeadsId(String headId, String scheme) {
	     this.headId = headId;
	     this.scheme = scheme;
	 }
	@Column(name = "HEAD_ID", nullable = false, length = 20)
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	@Column(name = "SCHEME", nullable = false, length = 50)
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public boolean equals(Object other) {
	    if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EhfmAcctsHeadsId) ) return false;
		 EhfmAcctsHeadsId castOther = ( EhfmAcctsHeadsId ) other; 
	    
		 return ( (this.getHeadId()==castOther.getHeadId()) || ( this.getHeadId()!=null && castOther.getHeadId()!=null && this.getHeadId().equals(castOther.getHeadId()) ) )
	&& ( (this.getScheme()==castOther.getScheme()) || ( this.getScheme()!=null && castOther.getScheme()!=null && this.getScheme().equals(castOther.getScheme()) ) );
	}

	public int hashCode() {
	    int result = 17;
	    
	    result = 37 * result + ( getHeadId() == null ? 0 : this.getHeadId().hashCode() );
	    result = 37 * result + ( getScheme() == null ? 0 : this.getScheme().hashCode() );
	    return result;
	}   


}
