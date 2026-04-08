package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ehfm_work_allocation")
public class EhfmWorkAllocation implements Serializable {
	
    @EmbeddedId
    
    @AttributeOverrides( {
       @AttributeOverride(name="userRole", column=@Column(name="user_role", nullable=false) ),
       @AttributeOverride(name="userDeptId", column=@Column(name="user_dept_id", nullable=false) ) 
       } )
	private EhfmWorkAllocationId id;
    @Column(name="user_code", nullable=true)
	private String userCode;
    @Column(name="user_name", nullable=true)
	private String userName;
    @Column(name="user_dsgn_id", nullable=true)
	private String userDsgnId;
    @Column(name="user_login_id", nullable=false)
	private String userLoginId;
    @Column(name="user_work_staion", nullable=true)
	private String userWorkStation;
    @Column(name="user_work_loc_id", nullable=true)
	private String userWorkLocId;
    @Column(name="user_digital_token", nullable=true)
	private String userDigitalToken;
    @Column(name="user_cug_no", nullable=true)
	private String userCugNo;
    @Column(name="user_ip_address", nullable=true)
	private String userIPAddress;
    @Column(name="user_extn_no", nullable=true)
	private String userExtnNo;
    @Column(name="user_shift_code", nullable=true)
	private String userShiftCode;
    @Column(name="process", nullable=true)
	private String process;
    @Column(name="user_week_off", nullable=true)
	private String userWeekOff;
    @Column(name="rept_role", nullable=true)
	private String reptRole;
    @Column(name="personal_no", nullable=true)
	private String personalNo;
    @Column(name="workflow", nullable=true)
	private String workflow;
    @Column(name="primary_flag", nullable=true)
	private String primaryFlag;
    @Column(name="allocation_flag", nullable=true)
	private String allocationFlag;
    @Column(name="crt_usr", nullable=false)
	private String crtUsr;
    @Column(name="crt_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
	private Date crtDt;
    @Column(name="lst_upd_usr", nullable=true)
	private String lstUpdUsr;
    @Column(name="lst_upd_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
	private Date lstUpdDt;

	public EhfmWorkAllocationId getId() {
		return id;
	}
	public void setId(EhfmWorkAllocationId id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDsgnId() {
		return userDsgnId;
	}
	public void setUserDsgnId(String userDsgnId) {
		this.userDsgnId = userDsgnId;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getUserWorkStation() {
		return userWorkStation;
	}
	public void setUserWorkStation(String userWorkStation) {
		this.userWorkStation = userWorkStation;
	}
	public String getUserWorkLocId() {
		return userWorkLocId;
	}
	public void setUserWorkLocId(String userWorkLocId) {
		this.userWorkLocId = userWorkLocId;
	}
	public String getUserDigitalToken() {
		return userDigitalToken;
	}
	public void setUserDigitalToken(String userDigitalToken) {
		this.userDigitalToken = userDigitalToken;
	}
	public String getUserCugNo() {
		return userCugNo;
	}
	public void setUserCugNo(String userCugNo) {
		this.userCugNo = userCugNo;
	}
	public String getUserIPAddress() {
		return userIPAddress;
	}
	public void setUserIPAddress(String userIPAddress) {
		this.userIPAddress = userIPAddress;
	}
	public String getUserExtnNo() {
		return userExtnNo;
	}
	public void setUserExtnNo(String userExtnNo) {
		this.userExtnNo = userExtnNo;
	}
	public String getUserShiftCode() {
		return userShiftCode;
	}
	public void setUserShiftCode(String userShiftCode) {
		this.userShiftCode = userShiftCode;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getUserWeekOff() {
		return userWeekOff;
	}
	public void setUserWeekOff(String userWeekOff) {
		this.userWeekOff = userWeekOff;
	}
	public String getReptRole() {
		return reptRole;
	}
	public void setReptRole(String reptRole) {
		this.reptRole = reptRole;
	}
	public String getPersonalNo() {
		return personalNo;
	}
	public void setPersonalNo(String personalNo) {
		this.personalNo = personalNo;
	}
	public String getWorkflow() {
		return workflow;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public String getPrimaryFlag() {
		return primaryFlag;
	}
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	public String getAllocationFlag() {
		return allocationFlag;
	}
	public void setAllocationFlag(String allocationFlag) {
		this.allocationFlag = allocationFlag;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	

}
