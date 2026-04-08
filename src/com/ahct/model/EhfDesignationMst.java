package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "EHF_DESIGNATION_MST")
public class EhfDesignationMst implements Serializable {
	private EhfDesignationMstId id;
	private String goAdminDept;
	private String goNumber;
	
	private String deptDesignation;
	private Date crtDt;
	private String crtUsr;
	private String lstUpdUsr;
	private Date LstUpdDt;
	 @EmbeddedId
	    
	    @AttributeOverrides( {
	       @AttributeOverride(name="hod", column=@Column(name="HOD") ), 
	       @AttributeOverride(name="service", column=@Column(name="SERVICE") ) ,
	       @AttributeOverride(name="categoryName", column=@Column(name="CATEGORY_NAME") ),
	       @AttributeOverride(name="primaryDesignation", column=@Column(name="PRIMARY_DESIGNATION") ) ,
	       @AttributeOverride(name="dsgnId", column=@Column(name="DSGN_ID") ) 
	    })


	public EhfDesignationMstId getId() {
		return id;
	}

	public void setId(EhfDesignationMstId id) {
		this.id = id;
	}

	@Column(name = "GO_ADMIN_DEPT", nullable = true)
	public String getGoAdminDept() {
		return goAdminDept;
	}

	public void setGoAdminDept(String goAdminDept) {
		this.goAdminDept = goAdminDept;
	}

	@Column(name = "GO_NUMBER", nullable = true)
	public String getGoNumber() {
		return goNumber;
	}

	public void setGoNumber(String goNumber) {
		this.goNumber = goNumber;
	}

	

	@Column(name = "DEPT_DESIGNATION", nullable = true)
	public String getDeptDesignation() {
		return deptDesignation;
	}

	public void setDeptDesignation(String deptDesignation) {
		this.deptDesignation = deptDesignation;
	}

	@Column(name = "CRT_DT", nullable = true)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "CRT_USR", nullable = true)
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Column(name = "LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Column(name = "LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return LstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		LstUpdDt = lstUpdDt;
	}

	public EhfDesignationMst(EhfDesignationMstId id, String goAdminDept,
			String goNumber, String deptDesignation, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.id = id;
		this.goAdminDept = goAdminDept;
		this.goNumber = goNumber;
		this.deptDesignation = deptDesignation;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		LstUpdDt = lstUpdDt;
	}

	public EhfDesignationMst() {
		super();
	}

}
