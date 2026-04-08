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
@Table(name = "EHF_ENROLLMENT")
public class EhfEnrollment implements Serializable {
	private Date crtDt;
	private String crtUsr;
	private String currPay;
	private String ddoOffUnit;
	private String dept;
	private String deptHod;
	private String designation;
	private String empApgliNo;
	private String empCaste;
	private String empCode;
	private String empCommu;
	private String empGpfNo;
	private String empHdist;
	private String empHemail;
	private String empHmandMunci;
	private String empHmandMunciSel;
	private String empHno;
	private String empHomeDist;
	private String empHomeMandMunci;
	private String empHomeMandMunciSel;
	private String empHomeVillTwn;
	private String empHphone;
	private String empHstreetno;
	private String empHvillTwn;
	private String empIdentiMarks;
	private String empIdentiMarksSecond;
	private String empIdentiPanNo;
	private String empMaritalStat;
	private String empNationality;
	private String empNationalityCertiNo;
	private Date empNationalityIssDt;
	private String empOdist;
	private String empOemail;
	private String empOffHno;
	private String empOmandMunci;
	private String empOmandMunciSel;
	private String empOphone;
	private String empOstreetno;
	private String empOvillTwn;
	private String empPanNo;
	private String empRationNo;
	private String empType;
	private String empZppfNo;
	private String enrollPrntId;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String payScale;
	private String postDdoCode;
	private String postDist;
	private String prc;
	private String servDsgn;
	private String NmtFMem;
	private String postCode;
	private String cardExistFlag;
	private Date dateOfRetrmnt;
	private String stoAppo;
	private String pScale;
	private String lastPostDist;
	private String paySource;
	private String retrmntType;
	private String service;
	private String deptDesignation;
	private String cicDist;
	private String cicId;
	private String empHstate;
	private String empOstate;
	private String lastPostState;
	private String postState;
	private String Scheme;
	
	
	public EhfEnrollment() {
	}

	@Column(name = "CRT_DT", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Column(name = "CURR_PAY")
	public String getCurrPay() {
		return currPay;
	}

	public void setCurrPay(String currPay) {
		this.currPay = currPay;
	}

	@Column(name = "DDO_OFF_UNIT")
	public String getDdoOffUnit() {
		return ddoOffUnit;
	}

	public void setDdoOffUnit(String ddoOffUnit) {
		this.ddoOffUnit = ddoOffUnit;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "DEPT_HOD")
	public String getDeptHod() {
		return deptHod;
	}

	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "EMP_APGLI_NO")
	public String getEmpApgliNo() {
		return empApgliNo;
	}

	public void setEmpApgliNo(String empApgliNo) {
		this.empApgliNo = empApgliNo;
	}

	@Column(name = "EMP_CASTE")
	public String getEmpCaste() {
		return empCaste;
	}

	public void setEmpCaste(String empCaste) {
		this.empCaste = empCaste;
	}

	@Column(name = "EMP_CODE")
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	@Column(name = "EMP_COMMU")
	public String getEmpCommu() {
		return empCommu;
	}

	public void setEmpCommu(String empCommu) {
		this.empCommu = empCommu;
	}

	@Column(name = "EMP_GPF_NO")
	public String getEmpGpfNo() {
		return empGpfNo;
	}

	public void setEmpGpfNo(String empGpfNo) {
		this.empGpfNo = empGpfNo;
	}

	@Column(name = "EMP_HDIST")
	public String getEmpHdist() {
		return empHdist;
	}

	public void setEmpHdist(String empHdist) {
		this.empHdist = empHdist;
	}

	@Column(name = "EMP_HEMAIL")
	public String getEmpHemail() {
		return empHemail;
	}

	public void setEmpHemail(String empHemail) {
		this.empHemail = empHemail;
	}

	@Column(name = "EMP_HMAND_MUNCI")
	public String getEmpHmandMunci() {
		return empHmandMunci;
	}

	public void setEmpHmandMunci(String empHmandMunci) {
		this.empHmandMunci = empHmandMunci;
	}

	@Column(name = "EMP_HMAND_MUNCI_SEL")
	public String getEmpHmandMunciSel() {
		return empHmandMunciSel;
	}

	public void setEmpHmandMunciSel(String empHmandMunciSel) {
		this.empHmandMunciSel = empHmandMunciSel;
	}

	@Column(name = "EMP_HNO")
	public String getEmpHno() {
		return empHno;
	}

	public void setEmpHno(String empHno) {
		this.empHno = empHno;
	}

	@Column(name = "EMP_HOME_DIST")
	public String getEmpHomeDist() {
		return empHomeDist;
	}

	public void setEmpHomeDist(String empHomeDist) {
		this.empHomeDist = empHomeDist;
	}

	@Column(name = "EMP_HOME_MAND_MUNCI")
	public String getEmpHomeMandMunci() {
		return empHomeMandMunci;
	}

	public void setEmpHomeMandMunci(String empHomeMandMunci) {
		this.empHomeMandMunci = empHomeMandMunci;
	}

	@Column(name = "EMP_HOME_MAND_MUNCI_SEL")
	public String getEmpHomeMandMunciSel() {
		return empHomeMandMunciSel;
	}

	public void setEmpHomeMandMunciSel(String empHomeMandMunciSel) {
		this.empHomeMandMunciSel = empHomeMandMunciSel;
	}

	@Column(name = "EMP_HOME_VILL_TWN")
	public String getEmpHomeVillTwn() {
		return empHomeVillTwn;
	}

	public void setEmpHomeVillTwn(String empHomeVillTwn) {
		this.empHomeVillTwn = empHomeVillTwn;
	}

	@Column(name = "EMP_HPHONE")
	public String getEmpHphone() {
		return empHphone;
	}

	public void setEmpHphone(String empHphone) {
		this.empHphone = empHphone;
	}

	@Column(name = "EMP_HSTREETNO")
	public String getEmpHstreetno() {
		return empHstreetno;
	}

	public void setEmpHstreetno(String empHstreetno) {
		this.empHstreetno = empHstreetno;
	}

	@Column(name = "EMP_HVILL_TWN")
	public String getEmpHvillTwn() {
		return empHvillTwn;
	}

	public void setEmpHvillTwn(String empHvillTwn) {
		this.empHvillTwn = empHvillTwn;
	}

	@Column(name = "EMP_IDENTI_MARKS")
	public String getEmpIdentiMarks() {
		return empIdentiMarks;
	}

	public void setEmpIdentiMarks(String empIdentiMarks) {
		this.empIdentiMarks = empIdentiMarks;
	}

	@Column(name = "EMP_IDENTI_PAN_NO")
	public String getEmpIdentiPanNo() {
		return empIdentiPanNo;
	}

	public void setEmpIdentiPanNo(String empIdentiPanNo) {
		this.empIdentiPanNo = empIdentiPanNo;
	}

	@Column(name = "EMP_MARITAL_STAT")
	public String getEmpMaritalStat() {
		return empMaritalStat;
	}

	public void setEmpMaritalStat(String empMaritalStat) {
		this.empMaritalStat = empMaritalStat;
	}

	@Column(name = "EMP_NATIONALITY")
	public String getEmpNationality() {
		return empNationality;
	}

	public void setEmpNationality(String empNationality) {
		this.empNationality = empNationality;
	}

	@Column(name = "EMP_NATIONALITY_CERTI_NO")
	public String getEmpNationalityCertiNo() {
		return empNationalityCertiNo;
	}

	public void setEmpNationalityCertiNo(String empNationalityCertiNo) {
		this.empNationalityCertiNo = empNationalityCertiNo;
	}

	@Column(name = "EMP_NATIONALITY_ISS_DT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEmpNationalityIssDt() {
		return empNationalityIssDt;
	}

	public void setEmpNationalityIssDt(Date empNationalityIssDt) {
		this.empNationalityIssDt = empNationalityIssDt;
	}

	@Column(name = "EMP_ODIST")
	public String getEmpOdist() {
		return empOdist;
	}

	public void setEmpOdist(String empOdist) {
		this.empOdist = empOdist;
	}

	@Column(name = "EMP_OEMAIL")
	public String getEmpOemail() {
		return empOemail;
	}

	public void setEmpOemail(String empOemail) {
		this.empOemail = empOemail;
	}

	@Column(name = "EMP_OFF_HNO")
	public String getEmpOffHno() {
		return empOffHno;
	}

	public void setEmpOffHno(String empOffHno) {
		this.empOffHno = empOffHno;
	}

	@Column(name = "EMP_OMAND_MUNCI")
	public String getEmpOmandMunci() {
		return empOmandMunci;
	}

	public void setEmpOmandMunci(String empOmandMunci) {
		this.empOmandMunci = empOmandMunci;
	}

	@Column(name = "EMP_OMAND_MUNCI_SEL")
	public String getEmpOmandMunciSel() {
		return empOmandMunciSel;
	}

	public void setEmpOmandMunciSel(String empOmandMunciSel) {
		this.empOmandMunciSel = empOmandMunciSel;
	}

	@Column(name = "EMP_OPHONE")
	public String getEmpOphone() {
		return empOphone;
	}

	public void setEmpOphone(String empOphone) {
		this.empOphone = empOphone;
	}

	@Column(name = "EMP_OSTREETNO")
	public String getEmpOstreetno() {
		return empOstreetno;
	}

	public void setEmpOstreetno(String empOstreetno) {
		this.empOstreetno = empOstreetno;
	}

	@Column(name = "EMP_OVILL_TWN")
	public String getEmpOvillTwn() {
		return empOvillTwn;
	}

	public void setEmpOvillTwn(String empOvillTwn) {
		this.empOvillTwn = empOvillTwn;
	}

	@Column(name = "EMP_PAN_NO")
	public String getEmpPanNo() {
		return empPanNo;
	}

	public void setEmpPanNo(String empPanNo) {
		this.empPanNo = empPanNo;
	}

	@Column(name = "EMP_RATION_NO")
	public String getEmpRationNo() {
		return empRationNo;
	}

	public void setEmpRationNo(String empRationNo) {
		this.empRationNo = empRationNo;
	}

	@Column(name = "EMP_TYPE", nullable = false)
	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	@Column(name = "EMP_ZPPF_NO")
	public String getEmpZppfNo() {
		return empZppfNo;
	}

	public void setEmpZppfNo(String empZppfNo) {
		this.empZppfNo = empZppfNo;
	}

	@Id
	@Column(name = "ENROLL_PRNT_ID", nullable = false)
	public String getEnrollPrntId() {
		return enrollPrntId;
	}

	public void setEnrollPrntId(String enrollPrntId) {
		this.enrollPrntId = enrollPrntId;
	}

	@Column(name = "LST_UPD_DT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	@Column(name = "LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Column(name = "PAY_SCALE")
	public String getPayScale() {
		return payScale;
	}

	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}

	@Column(name = "POST_DDO_CODE")
	public String getPostDdoCode() {
		return postDdoCode;
	}

	public void setPostDdoCode(String postDdoCode) {
		this.postDdoCode = postDdoCode;
	}

	@Column(name = "POST_DIST")
	public String getPostDist() {
		return postDist;
	}

	public void setPostDist(String postDist) {
		this.postDist = postDist;
	}

	@Column(name = "PRC")
	public String getPrc() {
		return prc;
	}

	public void setPrc(String prc) {
		this.prc = prc;
	}

	@Column(name = "SERV_DSGN")
	public String getServDsgn() {
		return servDsgn;
	}

	public void setServDsgn(String servDsgn) {
		this.servDsgn = servDsgn;
	}

	@Column(name = "NOMINATED_FAM_MEM")
	public String getNmtFMem() {
		return NmtFMem;
	}

	public void setNmtFMem(String nmtFMem) {
		NmtFMem = nmtFMem;
	}

	// public String getNmtFMem() {
	// return NmtFMem;
	// }
	//
	// public void setNmtFMem(String nmtFMem) {
	// this.NmtFMem = nmtFMem;
	// }

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "POST_CODE")
	public String getPostCode() {
		return postCode;
	}

	public void setCardExistFlag(String cardExistFlag) {
		this.cardExistFlag = cardExistFlag;
	}

	@Column(name = "CARD_EXIST_FLAG")
	public String getCardExistFlag() {
		return cardExistFlag;
	}

	public void setEmpIdentiMarksSecond(String empIdentiMarksSecond) {
		this.empIdentiMarksSecond = empIdentiMarksSecond;
	}

	@Column(name = "EMP_IDENTI_MARKS_SEC")
	public String getEmpIdentiMarksSecond() {
		return empIdentiMarksSecond;
	}

	public void setDateOfRetrmnt(Date dateOfRetrmnt) {
		this.dateOfRetrmnt = dateOfRetrmnt;
	}

	@Column(name = "DATEOFRETRMNT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateOfRetrmnt() {
		return dateOfRetrmnt;
	}

	public void setStoAppo(String stoAppo) {
		this.stoAppo = stoAppo;
	}

	@Column(name = "STOAPPO")
	public String getStoAppo() {
		return stoAppo;
	}

	@Column(name = "P_SCALE")
	public String getpScale() {
		return pScale;
	}

	public void setpScale(String pScale) {
		this.pScale = pScale;
	}

	public void setLastPostDist(String lastPostDist) {
		this.lastPostDist = lastPostDist;
	}

	@Column(name = "LAST_POST_DIST")
	public String getLastPostDist() {
		return lastPostDist;
	}

	public void setPaySource(String paySource) {
		this.paySource = paySource;
	}

	@Column(name = "PAY_SOURCE")
	public String getPaySource() {
		return paySource;
	}

	public void setRetrmntType(String retrmntType) {
		this.retrmntType = retrmntType;
	}

	@Column(name = "RETIREMENT_TYPE")
	public String getRetrmntType() {
		return retrmntType;
	}

	@Column(name = "SERVICE")
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	@Column(name = "dept_designation")
	public String getDeptDesignation() {
		return deptDesignation;
	}

	public void setDeptDesignation(String deptDesignation) {
		this.deptDesignation = deptDesignation;
	}
	@Column(name = "CIC_DIST")
	public String getCicDist() {
		return cicDist;
	}

	public void setCicDist(String cicDist) {
		this.cicDist = cicDist;
	}
	@Column(name = "CIC_ID")
	public String getCicId() {
		return cicId;
	}

	public void setCicId(String cicId) {
		this.cicId = cicId;
	}
	@Column(name = "emp_hstate")
	public String getEmpHstate() {
		return empHstate;
	}

	public void setEmpHstate(String empHstate) {
		this.empHstate = empHstate;
	}
	@Column(name = "emp_ostate")
	public String getEmpOstate() {
		return empOstate;
	}

	public void setEmpOstate(String empOstate) {
		this.empOstate = empOstate;
	}
	@Column(name = "last_post_state")
	public String getLastPostState() {
		return lastPostState;
	}

	public void setLastPostState(String lastPostState) {
		this.lastPostState = lastPostState;
	}

	@Column(name = "emp_post_state")
	public String getPostState() {
		return postState;
	}
	public void setPostState(String postState) {
		this.postState = postState;
	}
	
	@Column(name = "scheme")
	public String getScheme() {
		return Scheme;
	}

	public void setScheme(String scheme) {
		Scheme = scheme;
	}
	
	

}
