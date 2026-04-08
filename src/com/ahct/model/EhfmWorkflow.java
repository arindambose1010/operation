package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_WORKFLOW")
public class EhfmWorkflow  implements java.io.Serializable{
	
	private Long sNo;
	private String currStatus;
	private String currRole;
	private String buttonName;
	private String activeYn;
	private String crtUsr;
	private Date crtDt;
	private String mainModule;
	private String subModule;
	
	@Id
	@Column(name="s_no" , nullable=false)
	public Long getsNo() {
		return sNo;
	}
	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}
	@Column(name="current_status")
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
	@Column(name="current_role")
	public String getCurrRole() {
		return currRole;
	}
	public void setCurrRole(String currRole) {
		this.currRole = currRole;
	}
	@Column(name="button_name")
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	@Column(name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="crt_usr")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="crt_dt")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="main_module")
	public String getMainModule() {
		return mainModule;
	}
	public void setMainModule(String mainModule) {
		this.mainModule = mainModule;
	}
	@Column(name="sub_module")
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	public EhfmWorkflow(Long sNo, String currStatus, String currRole,
			String buttonName, String activeYn, String crtUsr, Date crtDt,
			String mainModule, String subModule) {
		super();
		this.sNo = sNo;
		this.currStatus = currStatus;
		this.currRole = currRole;
		this.buttonName = buttonName;
		this.activeYn = activeYn;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.mainModule = mainModule;
		this.subModule = subModule;
	}
	public EhfmWorkflow() {
		super();
		// TODO Auto-generated constructor stub
	}

}
