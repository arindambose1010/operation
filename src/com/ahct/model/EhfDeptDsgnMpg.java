package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_DEPT_DSGN_MPG database table.
 * 
 */
@Entity
@Table(name="EHF_DEPT_DSGN_MPG")
public class EhfDeptDsgnMpg implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfDeptDsgnMpgPK id;
	private String biomYn;
	private Date crtDt;
	private String crtUser;
	private String deptName;
	private String dsgnName;
	

    public EhfDeptDsgnMpg() {
    }


	@EmbeddedId
	public EhfDeptDsgnMpgPK getId() {
		return this.id;
	}

	public void setId(EhfDeptDsgnMpgPK id) {
		this.id = id;
	}
	

	@Column(name="BIOM_YN")
	public String getBiomYn() {
		return this.biomYn;
	}

	public void setBiomYn(String biomYn) {
		this.biomYn = biomYn;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USER")
	public String getCrtUser() {
		return this.crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}


	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	@Column(name="DSGN_NAME")
	public String getDsgnName() {
		return this.dsgnName;
	}

	public void setDsgnName(String dsgnName) {
		this.dsgnName = dsgnName;
	}


	

}