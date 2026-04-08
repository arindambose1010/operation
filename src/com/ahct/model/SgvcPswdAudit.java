package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table

( name = "SGVC_PSWD_AUDIT" )
public class SgvcPswdAudit  implements java.io.Serializable{
	private String empId;
    private Integer seq_no;
    private String password;
    private String changeType;
    private Date crtDt;
    private String crtUsr;

    
	public SgvcPswdAudit() {
		super();
	}
    
	public SgvcPswdAudit(String password, String changeType,
			Date crtDt, String crtUsr) {
		super();
		this.password = password;
		this.changeType = changeType;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
	}
	
	
	
	 @Column(name="EMP_ID", nullable=false, length=12)
	    public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq-gen")
		@SequenceGenerator(name="seq-gen", sequenceName="SMS_AUDIT_SEQ", initialValue=1500, allocationSize=1)
		@Column(name="SEQ_NO", nullable=false, length=3)
		public Integer getSeq_no() {
			return seq_no;
		}
		public void setSeq_no(Integer seq_no) {
			this.seq_no = seq_no;
		}
	    


	@Column(name="PASSWORD",nullable=false, length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Column(name="CHANGE_TYPE",nullable=false, length=2)
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	
	
	@Temporal ( TemporalType.DATE )
	@Column(name="CRT_DT",nullable=false, length=7)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	
	@Column(name="CRT_USR", nullable=false, length=12)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


}
