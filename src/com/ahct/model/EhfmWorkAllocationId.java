package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfmWorkAllocationId implements Serializable {
    @Column(name="user_role", nullable=false)
	private String userRole;
    @Column(name="user_dept_id", nullable=false)
	private String userDeptId;
    
    
    
	public EhfmWorkAllocationId() {
		super();
	}
	public EhfmWorkAllocationId(String userRole,
			String userDeptId) {
		super();
		
		this.userRole = userRole;
		this.userDeptId = userDeptId;
	}
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserDeptId() {
		return userDeptId;
	}
	public void setUserDeptId(String userDeptId) {
		this.userDeptId = userDeptId;
	}
    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
                if ( (other == null ) ) return false;
                if ( !(other instanceof EhfmWorkAllocationId) ) return false;
                EhfmWorkAllocationId castOther = ( EhfmWorkAllocationId ) other; 
    return( (this.getUserRole()==castOther.getUserRole()) || ( this.getUserRole()!=null && castOther.getUserRole()!=null && this.getUserRole().equals(castOther.getUserRole()) ) 
    && ( (this.getUserDeptId()==castOther.getUserDeptId()) || ( this.getUserDeptId()!=null && castOther.getUserDeptId()!=null && this.getUserDeptId().equals(castOther.getUserDeptId()) )));
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getUserRole() == null ? 0 : this.getUserRole().hashCode() );
        result = 37 * result + ( getUserDeptId() == null ? 0 : this.getUserDeptId().hashCode() );
        return result;
    }

}
