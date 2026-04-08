package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ehfm_depts")
public class EhfmDepts implements Serializable {

	@Id
	@Column(name = "dept_id", nullable = true)
	private String deptId;
	@Column(name = "dept_name", nullable = true)
	private String deptName;
	@Column(name = "dept_shrt_name", nullable = true)
	private String deptShrtName;
	@Column(name = "lang_id", nullable = true)
	private String langId;
	@Column(name = "loc_id", nullable = true)
	private String locId;
	@Column(name = "dept_type")
	private String deptType;
	@Column(name = "dept_activity_desc")
	private String deptActivityDesc;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "crt_dt", nullable = true)
	private Date crtDt;
	@Column(name = "crt_usr", nullable = false)
	private String crtUsr;
	@Column(name = "lst_upd_usr", nullable = true)
	private String lstUpdUsr;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_upd_dt", nullable = true)
	private Date lstUpdDt;

	public EhfmDepts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmDepts(String deptId, String deptName, String deptShrtName,
			String langId, String locId, String deptType,
			String deptActivityDesc, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptShrtName = deptShrtName;
		this.langId = langId;
		this.locId = locId;
		this.deptType = deptType;
		this.deptActivityDesc = deptActivityDesc;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptShrtName() {
		return deptShrtName;
	}

	public void setDeptShrtName(String deptShrtName) {
		this.deptShrtName = deptShrtName;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getDeptActivityDesc() {
		return deptActivityDesc;
	}

	public void setDeptActivityDesc(String deptActivityDesc) {
		this.deptActivityDesc = deptActivityDesc;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

}
