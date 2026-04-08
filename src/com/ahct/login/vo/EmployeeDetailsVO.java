package com.ahct.login.vo;

import java.util.List;

import com.ahct.common.vo.LabelBean;

public class EmployeeDetailsVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userRole;
	//private List<LabelBean> roleList;
	
	private String msg;
	//private List<MenuVo> lstMenu = null;
	private List<LabelBean> grpList;
	private List<LabelBean> moduleList;
	private String ID;
	private String VALUE;
	private String EMPCODE;
	private String LOGINNAME;
	private String POSTCODE;
	private String USERID;
	private String USERROLE;
	private String NAME;
	private String ISLOGGEDIN;
	private String BIOMETRIICREQU;
	private String DIGILOGIN;
	private String DSGNID;
	private String DSGNNAME;
	private String ROLENAME;
	private String USERNO;
	private String DESIGNATIONID;
	private String SERVICEFLAG;
	private List<String> grpIdList;
	private String GENDER;
	private String roleList;
	private String userState;
	private String mobileNo;
	private String email;
	private String sessionId;
	private String seqId;
	private String vpnAccess;
	
	
	
	public String getVpnAccess() {
		return vpnAccess;
	}
	public void setVpnAccess(String vpnAccess) {
		this.vpnAccess = vpnAccess;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public List<String> getGrpIdList() {
		return grpIdList;
	}
	public void setGrpIdList(List<String> grpIdList) {
		this.grpIdList = grpIdList;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public String getRoleList() {
		return roleList;
	}
	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}
	public String getSERVICEFLAG() {
		return SERVICEFLAG;
	}
	public void setSERVICEFLAG(String sERVICEFLAG) {
		SERVICEFLAG = sERVICEFLAG;
	}
	public String getDESIGNATIONID() {
		return DESIGNATIONID;
	}
	public void setDESIGNATIONID(String dESIGNATIONID) {
		DESIGNATIONID = dESIGNATIONID;
	}
	public String getUSERNO() {
		return USERNO;
	}
	public void setUSERNO(String uSERNO) {
		USERNO = uSERNO;
	}
	public List<LabelBean> getGrpList() {
		return grpList;
	}
	public void setGrpList(List<LabelBean> grpList) {
		this.grpList = grpList;
	}
	public String getROLENAME() {
		return ROLENAME;
	}
	public void setROLENAME(String rOLENAME) {
		ROLENAME = rOLENAME;
	}
	public String getDSGNID() {
		return DSGNID;
	}
	public void setDSGNID(String dSGNID) {
		DSGNID = dSGNID;
	}
	public String getDSGNNAME() {
		return DSGNNAME;
	}
	public void setDSGNNAME(String dSGNNAME) {
		DSGNNAME = dSGNNAME;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
	public String getEMPCODE() {
		return EMPCODE;
	}
	public void setEMPCODE(String eMPCODE) {
		EMPCODE = eMPCODE;
	}
	public String getLOGINNAME() {
		return LOGINNAME;
	}
	public void setLOGINNAME(String lOGINNAME) {
		LOGINNAME = lOGINNAME;
	}
	public String getPOSTCODE() {
		return POSTCODE;
	}
	public void setPOSTCODE(String pOSTCODE) {
		POSTCODE = pOSTCODE;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getUSERROLE() {
		return USERROLE;
	}
	public void setUSERROLE(String uSERROLE) {
		USERROLE = uSERROLE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getISLOGGEDIN() {
		return ISLOGGEDIN;
	}
	public void setISLOGGEDIN(String iSLOGGEDIN) {
		ISLOGGEDIN = iSLOGGEDIN;
	}
	public String getBIOMETRIICREQU() {
		return BIOMETRIICREQU;
	}
	public void setBIOMETRIICREQU(String bIOMETRIICREQU) {
		BIOMETRIICREQU = bIOMETRIICREQU;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/*public List<LabelBean> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<LabelBean> roleList) {
		this.roleList = roleList;
	}
	*/
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/*public List<MenuVo> getLstMenu() {
		return lstMenu;
	}
	public void setLstMenu(List<MenuVo> lstMenu) {
		this.lstMenu = lstMenu;
	}*/
	public String getDIGILOGIN() {
		return DIGILOGIN;
	}
	public void setDIGILOGIN(String dIGILOGIN) {
		DIGILOGIN = dIGILOGIN;
	}
	public List<LabelBean> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<LabelBean> moduleList) {
		this.moduleList = moduleList;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	
}
