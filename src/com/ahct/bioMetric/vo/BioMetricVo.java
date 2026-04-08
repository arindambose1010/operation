package com.ahct.bioMetric.vo;

import java.util.Date;
import java.util.List;

public class BioMetricVo {

	private String ID;
	private String VALUE;
	private String userId;
	private String hospId;
	private String hospMacId;
	private String otherUser;
	
	private String USERID;
	private String LOGINNAME;
	private String EMPLOYEENAME;
	private String DESIGNATION;
	private String MOBILE;
	private String DEPARTMENT;
	private String ADDRESS;
	private String PERMADDRESS;
	private String PHOTO;
	private String FINGERPRINT;
	private String ATTENDANCEUSER;
	private String OTHERUSER;
	private String ATTENDTYPE;
	private Date LOGOUTTIME;

	private String deptId;
	private String distId;
	private String dsgnId;
	
	private String fromDate;
	private String toDate;
	
	
	
	private String empName;
	private String hospitalName;
	private long loginDays;
	private Number notLoginDays;
	
	private String deptName;
	private String dsgnName;
	

	private String  LOGINDATE;
	private String SCHEDULEDHOURS;
	private Date FIRSTLOGIN;
	private Date LASTLOGOUT;
	private String HOSPNAME;
	private String MACADDRESS;
	private String TOTALHOURSWORKED;
	private String EMPNAME;
	private String csvFlag;
	
	private int startIndex;
	private int maxResults;
	private List<BioMetricVo> attendanceReport;
	private long count;
	private String USERNAME;
	private List<BioMetricVo> othersList;
	
	
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	public String getHospMacId() {
		return hospMacId;
	}
	public void setHospMacId(String hospMacId) {
		this.hospMacId = hospMacId;
	}
	public String getEMPLOYEENAME() {
		return EMPLOYEENAME;
	}
	public void setEMPLOYEENAME(String eMPLOYEENAME) {
		EMPLOYEENAME = eMPLOYEENAME;
	}
	public String getDESIGNATION() {
		return DESIGNATION;
	}
	public void setDESIGNATION(String dESIGNATION) {
		DESIGNATION = dESIGNATION;
	}
	public String getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPERMADDRESS() {
		return PERMADDRESS;
	}
	public void setPERMADDRESS(String pERMADDRESS) {
		PERMADDRESS = pERMADDRESS;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getPHOTO() {
		return PHOTO;
	}
	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getFINGERPRINT() {
		return FINGERPRINT;
	}
	public void setFINGERPRINT(String fINGERPRINT) {
		FINGERPRINT = fINGERPRINT;
	}
	public String getLOGINNAME() {
		return LOGINNAME;
	}
	public void setLOGINNAME(String lOGINNAME) {
		LOGINNAME = lOGINNAME;
	}
	public String getATTENDANCEUSER() {
		return ATTENDANCEUSER;
	}
	public void setATTENDANCEUSER(String aTTENDANCEUSER) {
		ATTENDANCEUSER = aTTENDANCEUSER;
	}
	public String getOTHERUSER() {
		return OTHERUSER;
	}
	public void setOTHERUSER(String oTHERUSER) {
		OTHERUSER = oTHERUSER;
	}
	public String getATTENDTYPE() {
		return ATTENDTYPE;
	}
	public void setATTENDTYPE(String aTTENDTYPE) {
		ATTENDTYPE = aTTENDTYPE;
	}
	public Date getLOGOUTTIME() {
		return LOGOUTTIME;
	}
	public void setLOGOUTTIME(Date lOGOUTTIME) {
		LOGOUTTIME = lOGOUTTIME;
	}
	public String getOtherUser() {
		return otherUser;
	}
	public void setOtherUser(String otherUser) {
		this.otherUser = otherUser;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getDsgnId() {
		return dsgnId;
	}
	public void setDsgnId(String dsgnId) {
		this.dsgnId = dsgnId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public long getLoginDays() {
		return loginDays;
	}
	public void setLoginDays(long loginDays) {
		this.loginDays = loginDays;
	}


	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDsgnName() {
		return dsgnName;
	}
	public void setDsgnName(String dsgnName) {
		this.dsgnName = dsgnName;
	}

	public String getSCHEDULEDHOURS() {
		return SCHEDULEDHOURS;
	}
	public void setSCHEDULEDHOURS(String sCHEDULEDHOURS) {
		SCHEDULEDHOURS = sCHEDULEDHOURS;
	}

	public String getLOGINDATE() {
		return LOGINDATE;
	}
	public void setLOGINDATE(String lOGINDATE) {
		LOGINDATE = lOGINDATE;
	}
	public Date getFIRSTLOGIN() {
		return FIRSTLOGIN;
	}
	public void setFIRSTLOGIN(Date fIRSTLOGIN) {
		FIRSTLOGIN = fIRSTLOGIN;
	}
	public Date getLASTLOGOUT() {
		return LASTLOGOUT;
	}
	public void setLASTLOGOUT(Date lASTLOGOUT) {
		LASTLOGOUT = lASTLOGOUT;
	}
	public String getHOSPNAME() {
		return HOSPNAME;
	}
	public void setHOSPNAME(String hOSPNAME) {
		HOSPNAME = hOSPNAME;
	}
	public String getMACADDRESS() {
		return MACADDRESS;
	}
	public void setMACADDRESS(String mACADDRESS) {
		MACADDRESS = mACADDRESS;
	}
	public String getTOTALHOURSWORKED() {
		return TOTALHOURSWORKED;
	}
	public void setTOTALHOURSWORKED(String tOTALHOURSWORKED) {
		TOTALHOURSWORKED = tOTALHOURSWORKED;
	}
	public String getEMPNAME() {
		return EMPNAME;
	}
	public void setEMPNAME(String eMPNAME) {
		EMPNAME = eMPNAME;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public List<BioMetricVo> getAttendanceReport() {
		return attendanceReport;
	}
	public void setAttendanceReport(List<BioMetricVo> attendanceReport) {
		this.attendanceReport = attendanceReport;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Number getNotLoginDays() {
		return notLoginDays;
	}
	public void setNotLoginDays(Number notLoginDays) {
		this.notLoginDays = notLoginDays;
	}
	public String getCsvFlag() {
		return csvFlag;
	}
	public void setCsvFlag(String csvFlag) {
		this.csvFlag = csvFlag;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public List<BioMetricVo> getOthersList() {
		return othersList;
	}
	public void setOthersList(List<BioMetricVo> othersList) {
		this.othersList = othersList;
	}
	
	
	
	
	
}
