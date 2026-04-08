package com.ahct.bioMetric.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.bioMetric.vo.BioMetricVo;

public class BioMetricForm extends ActionForm {

	private String userName;
	private String userId;
	private String designation;
	private String department;
	private String cugNumber;
	private String mobNumber;
	private String address;
	private String permAddress;
	private String photoUrl;
	private FormFile photoAttach;
	private String photoUpload;
	private String fingerPrint;
	private String loginName;
	private String hospId;
	private String hospMacId;
	private String attendanceUser;
	private String otherUser;
	private String attendType;
	
	private String rowsPerPage;
	private String startIndex;
	private String showPage;
	private String totalRows;
	private String showScheme;
	private String schemeType;
	private String deptId;
	private String distId;
	private String dsgnId;
	
	private String fromDate;
	private String toDate;
	
	
	private String empName;
	
	private String hospitalName;
	private String loginDays;
	private String notLoginDays;
	
	private String endIndex;

	
	
	
	private List<BioMetricVo> lstBiometricSearch;
	private List<BioMetricVo> deptLst;
	private List<BioMetricVo> dsgnLst;
	private List<BioMetricVo> othersList;
	private List<BioMetricVo> attendanceRept;
	
	private String USERNAME;
	private String stateType;
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCugNumber() {
		return cugNumber;
	}
	public void setCugNumber(String cugNumber) {
		this.cugNumber = cugNumber;
	}
	public String getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPermAddress() {
		return permAddress;
	}
	public void setPermAddress(String permAddress) {
		this.permAddress = permAddress;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public FormFile getPhotoAttach() {
		return photoAttach;
	}
	public void setPhotoAttach(FormFile photoAttach) {
		this.photoAttach = photoAttach;
	}
	public String getPhotoUpload() {
		return photoUpload;
	}
	public void setPhotoUpload(String photoUpload) {
		this.photoUpload = photoUpload;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFingerPrint() {
		return fingerPrint;
	}
	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	public String getAttendanceUser() {
		return attendanceUser;
	}
	public void setAttendanceUser(String attendanceUser) {
		this.attendanceUser = attendanceUser;
	}
	public String getOtherUser() {
		return otherUser;
	}
	public void setOtherUser(String otherUser) {
		this.otherUser = otherUser;
	}
	public String getAttendType() {
		return attendType;
	}
	public void setAttendType(String attendType) {
		this.attendType = attendType;
	}
	public String getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getShowPage() {
		return showPage;
	}
	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}
	public String getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
	public String getShowScheme() {
		return showScheme;
	}
	public void setShowScheme(String showScheme) {
		this.showScheme = showScheme;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
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
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public List<BioMetricVo> getLstBiometricSearch() {
		return lstBiometricSearch;
	}
	public void setLstBiometricSearch(List<BioMetricVo> lstBiometricSearch) {
		this.lstBiometricSearch = lstBiometricSearch;
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
	public String getLoginDays() {
		return loginDays;
	}
	public void setLoginDays(String loginDays) {
		this.loginDays = loginDays;
	}
	public String getNotLoginDays() {
		return notLoginDays;
	}
	public void setNotLoginDays(String notLoginDays) {
		this.notLoginDays = notLoginDays;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public String getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}
	public List<BioMetricVo> getDeptLst() {
		return deptLst;
	}
	public void setDeptLst(List<BioMetricVo> deptLst) {
		this.deptLst = deptLst;
	}
	public List<BioMetricVo> getDsgnLst() {
		return dsgnLst;
	}
	public void setDsgnLst(List<BioMetricVo> dsgnLst) {
		this.dsgnLst = dsgnLst;
	}
	public List<BioMetricVo> getAttendanceRept() {
		return attendanceRept;
	}
	public void setAttendanceRept(List<BioMetricVo> attendanceRept) {
		this.attendanceRept = attendanceRept;
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
	public String getStateType() {
		return stateType;
	}
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	
	
}
