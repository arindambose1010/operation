package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class SgvcPswdAuditId implements Serializable{
	private String empId;
    private String seq_no;
	
    
    @Column(name="EMP_ID", nullable=false, length=12)
    public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@Column(name="SEQ_NO", nullable=false, length=3)
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
    


    public SgvcPswdAuditId() {
		super();
	}
	public SgvcPswdAuditId(String empId, String seq_no) {
		super();
		this.empId = empId;
		this.seq_no = seq_no;
	}
	
	
	
	
	


    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
                if ( (other == null ) ) return false;
                if ( !(other instanceof SgvcPswdAuditId) ) return false;
                SgvcPswdAuditId castOther = ( SgvcPswdAuditId ) other; 
        
                return ( (this.getEmpId()==castOther.getEmpId()) || ( this.getEmpId()!=null && castOther.getEmpId()!=null && this.getEmpId().equals(castOther.getEmpId()) ) )
    && ( (this.getSeq_no()==castOther.getSeq_no()) || ( this.getSeq_no()!=null && castOther.getSeq_no()!=null && this.getSeq_no().equals(castOther.getSeq_no()) ) );
    }
    
    public int hashCode() {
        int result = 17;
        
        result = 37 * result + ( getEmpId() == null ? 0 : this.getEmpId().hashCode() );
        result = 37 * result + ( getSeq_no() == null ? 0 : this.getSeq_no().hashCode() );
        return result;
    }






    
}
