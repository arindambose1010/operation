package com.ahct.annualCheckUp.form;

import java.util.Date;
import java.util.List;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import org.apache.struts.action.ActionForm;
import com.ahct.common.vo.LabelBean;
import org.apache.struts.upload.FormFile;

public class AnnualCheckUpForm extends ActionForm
{
	private String cardType;
	private String head;
	private String ECNo0;
	private String ECNo1;
	private String ECNo2;
	private String ECNo3;
	private String ECNo4;
	private String ECNo5;
	private String ECNo6;
	private String ECNo7;
	private String ECNo8;
	private String ECNo9;
	private String ECNo10;
	private String cardIssueDt;
	private String name;
	private String gender;
	private String maritalStatus;
	private String photoUrl;
	private String dateOfBirth;
	private String years;
	private String months;
	private String days;
	private String relation;
	private String designation;
	private String contactno;
	private String slab;
	private String hno;
	private String street;
	private String state;
	private String district;
	private String mdl_mcl;
	private String mandal;
	private String municipality;
	private String village;
	private String pin;
	private String commCheck;
	private String comm_hno;
	private String comm_street;
	private String same_state;
	private String comm_state;
	private String same_dist;
	private String comm_dist;
	private String same_mdl_mcl;
	private String comm_mdl_mcl;
	private String same_mandal;
	private String comm_mandal;
	private String same_municipality;
	private String comm_municipality;
	private String same_village;
	private String comm_village;
	private String comm_pin;
	private String hospitalId;
	private String dtTime;
	private String ageString;
	private String empCode;
	private String eMailId;
	private String scheme;
	private String disableFlag;
	private String errMsg;
	private String cardNo;
	private String prev;
	private String next;
	private String caste;
	private String patientNo;
	private String caseId;
    private String dept_Hod;
    private String postDist;
    private String stoCode;
    private String ddoCode;
	private String deptHod;
	private String postDdoCode;
	private String medcoFlag;
	private List<LabelBean> maritalStatusList;
	private List<LabelBean> relationList;
	private List<LabelBean> stateList;
	private List<LabelBean> districtList;
	private List<LabelBean> mdlList;
	private List<LabelBean> mplList;
	private List<LabelBean> villList;
	private List<LabelBean> cdistrictList;
	private List<LabelBean> cmdlList;
	private List<LabelBean> cmplList;
	private List<LabelBean> cvillList;
	private String[] testCheck;
	private List<AnnualCheckUpVo> annualList;
	private List<AnnualCheckUpVo> annualCSVList;
	public List<AnnualCheckUpVo> getAnnualCSVList() {
		return annualCSVList;
	}

	public void setAnnualCSVList(List<AnnualCheckUpVo> annualCSVList) {
		this.annualCSVList = annualCSVList;
	}

	
	


	public String[] getTestCheck() {
		return testCheck;
	}

	public void setTestCheck(String[] testCheck) {
		this.testCheck = testCheck;
	}





	private int startPage;
	private int endPage;
	private int startIndex;
	private int endIndex;
	private String rowsPerPage;
	private String showPage;

	private String fromDate;
	private String toDate;

	private long age;
	private String status;
	private String hospitalName;
	private String[] examinationFnd;
	private String[] personalHistory;
	private String[] pastHistory;
	private String[] familyHistory;
	private String presentHistory;
	
	
	private String haemoglobin;
	private String tlc;
	private String polymorphs;
	private String lymphocytes;
	private String eosinophils;
	private String basophils;
	private String monocytes;
	private String rbc;
	private String wbc;
	private String platelets;
	private String bloodGroup;
	private String esr;
	private String fastingSugar;
	private String postPrandialSugar;
	private String glycosylatedHaemoglobin;
	private String hbsAg;
	private String totalCholesterol;
	private String hdlCholesterol;
	private String ldlCholesterol;
	private String vldlCholesterol;
	private String triglycerides;

	private String bloodUrea;
	private String sCreatinine;
	private String sUricAcid;

	private String serumElectrolytes;
	private String serumCreatinine;
	private String sgot;
	private String sgpt;
	private String sTotalBilirubin;
	private String sDirectBilirubin;
	private String  liverSgot;
	private String  liverSgpt;
   
    private String urineColor;
    private String urineAlbumin;
    private String urineSugar;
    private String urineMicroscopicExam;
    private String abdomenUltrasound;
    private String chestXrayPAView;
    private String pulmonaryFunction;
    private String ecg;
    private String twodEcho;
    private String treadmillTest;
    private String retinopathy;
    private String fundoscopy;
    private String audiometry;
    private String pelvicLocalExam;
    private String perVaginum;
    private String perSpeculum;
    private String surgicalExam;
    private String breastExam;
    private String papSmear;
    private String examFndsVal;
    private String personalHistVal;
    private String[] personalHist;
    private String pastHistrySurg;
    private String pastHistryOthr;
    private String familyHistoryOthr;
    private String pastHistryCancers;
    private String pastHistryEndDis;
    private String msg;
	private String contactNo;
	private String houseNo;
	private String commHouseNo;
	private String commStreet;
	private String commState;
	private String commDistrict;
	private String commMandal;
	private String commVillage;
	private String commPin;
	
	
	private String haemoglobinReport;
	private String bloodSugarReport;
	private String cholesterolReport;
	private String liverFunctionReport;
	private String kidneyReport;
	private String cardiacReport;
	private String overallHealthRemarks;
	private String summary;
	private String healthGrade;
	private String addrEnable;
	private String diagnosedBy;
	private String unitName;
	private String unitHodName;
	private String genInvestigations;
	private String genBlockInvestName;
	private FormFile[] investAttach=new FormFile[100];
	private FormFile[] consultAttach=new FormFile[100];

	public String getDiagnosedBy() {
		return diagnosedBy;
	}

	public void setDiagnosedBy(String diagnosedBy) {
		this.diagnosedBy = diagnosedBy;
	}

	public String getHaemoglobinReport() {
		return haemoglobinReport;
	}

	public void setHaemoglobinReport(String haemoglobinReport) {
		this.haemoglobinReport = haemoglobinReport;
	}

	public String getBloodSugarReport() {
		return bloodSugarReport;
	}

	public void setBloodSugarReport(String bloodSugarReport) {
		this.bloodSugarReport = bloodSugarReport;
	}

	public String getCholesterolReport() {
		return cholesterolReport;
	}

	public void setCholesterolReport(String cholesterolReport) {
		this.cholesterolReport = cholesterolReport;
	}

	public String getLiverFunctionReport() {
		return liverFunctionReport;
	}

	public void setLiverFunctionReport(String liverFunctionReport) {
		this.liverFunctionReport = liverFunctionReport;
	}

	public String getKidneyReport() {
		return kidneyReport;
	}

	public void setKidneyReport(String kidneyReport) {
		this.kidneyReport = kidneyReport;
	}

	public String getCardiacReport() {
		return cardiacReport;
	}

	public void setCardiacReport(String cardiacReport) {
		this.cardiacReport = cardiacReport;
	}

	public String getOverallHealthRemarks() {
		return overallHealthRemarks;
	}

	public void setOverallHealthRemarks(String overallHealthRemarks) {
		this.overallHealthRemarks = overallHealthRemarks;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getHealthGrade() {
		return healthGrade;
	}

	public void setHealthGrade(String healthGrade) {
		this.healthGrade = healthGrade;
	}

	public List<AnnualCheckUpVo> getAnnualList() {
		return annualList;
	}

	public void setAnnualList(List<AnnualCheckUpVo> annualList) {
		this.annualList = annualList;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getECNo0() {
		return ECNo0;
	}
	public void setECNo0(String eCNo0) {
		ECNo0 = eCNo0;
	}
	public String getECNo1() {
		return ECNo1;
	}
	public void setECNo1(String eCNo1) {
		ECNo1 = eCNo1;
	}
	public String getECNo2() {
		return ECNo2;
	}
	public void setECNo2(String eCNo2) {
		ECNo2 = eCNo2;
	}
	public String getECNo3() {
		return ECNo3;
	}
	public void setECNo3(String eCNo3) {
		ECNo3 = eCNo3;
	}
	public String getECNo4() {
		return ECNo4;
	}
	public void setECNo4(String eCNo4) {
		ECNo4 = eCNo4;
	}
	public String getECNo5() {
		return ECNo5;
	}
	public void setECNo5(String eCNo5) {
		ECNo5 = eCNo5;
	}
	public String getECNo6() {
		return ECNo6;
	}
	public void setECNo6(String eCNo6) {
		ECNo6 = eCNo6;
	}
	public String getECNo7() {
		return ECNo7;
	}
	public void setECNo7(String eCNo7) {
		ECNo7 = eCNo7;
	}
	public String getECNo8() {
		return ECNo8;
	}
	public void setECNo8(String eCNo8) {
		ECNo8 = eCNo8;
	}
	public String getECNo9() {
		return ECNo9;
	}
	public void setECNo9(String eCNo9) {
		ECNo9 = eCNo9;
	}
	public String getECNo10() {
		return ECNo10;
	}
	public void setECNo10(String eCNo10) {
		ECNo10 = eCNo10;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMdl_mcl() {
		return mdl_mcl;
	}
	public void setMdl_mcl(String mdl_mcl) {
		this.mdl_mcl = mdl_mcl;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCommCheck() {
		return commCheck;
	}
	public void setCommCheck(String commCheck) {
		this.commCheck = commCheck;
	}
	public String getComm_hno() {
		return comm_hno;
	}
	public void setComm_hno(String comm_hno) {
		this.comm_hno = comm_hno;
	}
	public String getComm_street() {
		return comm_street;
	}
	public void setComm_street(String comm_street) {
		this.comm_street = comm_street;
	}
	public String getSame_state() {
		return same_state;
	}
	public void setSame_state(String same_state) {
		this.same_state = same_state;
	}
	public String getComm_state() {
		return comm_state;
	}
	public void setComm_state(String comm_state) {
		this.comm_state = comm_state;
	}
	public String getSame_dist() {
		return same_dist;
	}
	public void setSame_dist(String same_dist) {
		this.same_dist = same_dist;
	}
	public String getComm_dist() {
		return comm_dist;
	}
	public void setComm_dist(String comm_dist) {
		this.comm_dist = comm_dist;
	}
	public String getSame_mdl_mcl() {
		return same_mdl_mcl;
	}
	public void setSame_mdl_mcl(String same_mdl_mcl) {
		this.same_mdl_mcl = same_mdl_mcl;
	}
	public String getComm_mdl_mcl() {
		return comm_mdl_mcl;
	}
	public void setComm_mdl_mcl(String comm_mdl_mcl) {
		this.comm_mdl_mcl = comm_mdl_mcl;
	}
	public String getSame_mandal() {
		return same_mandal;
	}
	public void setSame_mandal(String same_mandal) {
		this.same_mandal = same_mandal;
	}
	public String getComm_mandal() {
		return comm_mandal;
	}
	public void setComm_mandal(String comm_mandal) {
		this.comm_mandal = comm_mandal;
	}
	public String getSame_municipality() {
		return same_municipality;
	}
	public void setSame_municipality(String same_municipality) {
		this.same_municipality = same_municipality;
	}
	public String getComm_municipality() {
		return comm_municipality;
	}
	public void setComm_municipality(String comm_municipality) {
		this.comm_municipality = comm_municipality;
	}
	public String getSame_village() {
		return same_village;
	}
	public void setSame_village(String same_village) {
		this.same_village = same_village;
	}
	public String getComm_village() {
		return comm_village;
	}
	public void setComm_village(String comm_village) {
		this.comm_village = comm_village;
	}
	public String getComm_pin() {
		return comm_pin;
	}
	public void setComm_pin(String comm_pin) {
		this.comm_pin = comm_pin;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDtTime() {
		return dtTime;
	}
	public void setDtTime(String dtTime) {
		this.dtTime = dtTime;
	}
	public String getAgeString() {
		return ageString;
	}
	public void setAgeString(String ageString) {
		this.ageString = ageString;
	}
	public List<LabelBean> getMaritalStatusList() {
		return maritalStatusList;
	}
	public void setMaritalStatusList(List<LabelBean> maritalStatusList) {
		this.maritalStatusList = maritalStatusList;
	}
	public List<LabelBean> getRelationList() {
		return relationList;
	}
	public void setRelationList(List<LabelBean> relationList) {
		this.relationList = relationList;
	}
	public List<LabelBean> getStateList() {
		return stateList;
	}
	public void setStateList(List<LabelBean> stateList) {
		this.stateList = stateList;
	}
	public List<LabelBean> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<LabelBean> districtList) {
		this.districtList = districtList;
	}
	public List<LabelBean> getMdlList() {
		return mdlList;
	}
	public void setMdlList(List<LabelBean> mdlList) {
		this.mdlList = mdlList;
	}
	public List<LabelBean> getMplList() {
		return mplList;
	}
	public void setMplList(List<LabelBean> mplList) {
		this.mplList = mplList;
	}
	public List<LabelBean> getVillList() {
		return villList;
	}
	public void setVillList(List<LabelBean> villList) {
		this.villList = villList;
	}
	public List<LabelBean> getCdistrictList() {
		return cdistrictList;
	}
	public void setCdistrictList(List<LabelBean> cdistrictList) {
		this.cdistrictList = cdistrictList;
	}
	public List<LabelBean> getCmdlList() {
		return cmdlList;
	}
	public void setCmdlList(List<LabelBean> cmdlList) {
		this.cmdlList = cmdlList;
	}
	public List<LabelBean> getCmplList() {
		return cmplList;
	}
	public void setCmplList(List<LabelBean> cmplList) {
		this.cmplList = cmplList;
	}
	public List<LabelBean> getCvillList() {
		return cvillList;
	}
	public void setCvillList(List<LabelBean> cvillList) {
		this.cvillList = cvillList;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String geteMailId() {
		return eMailId;
	}
	public void seteMailId(String eMailId) {
		this.eMailId = eMailId;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getDisableFlag() {
		return disableFlag;
	}
	public void setDisableFlag(String disableFlag) {
		this.disableFlag = disableFlag;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getCardIssueDt() {
		return cardIssueDt;
	}
	public void setCardIssueDt(String cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getDeptHod() {
		return deptHod;
	}

	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}

	public String getPostDist() {
		return postDist;
	}

	public void setPostDist(String postDist) {
		this.postDist = postDist;
	}

	public String getStoCode() {
		return stoCode;
	}

	public void setStoCode(String stoCode) {
		this.stoCode = stoCode;
	}

	public String getDdoCode() {
		return ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getDept_Hod() {
		return dept_Hod;
	}

	public void setDept_Hod(String dept_Hod) {
		this.dept_Hod = dept_Hod;
	}

	public String getPostDdoCode() {
		return postDdoCode;
	}

	public void setPostDdoCode(String postDdoCode) {
		this.postDdoCode = postDdoCode;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getShowPage() {
		return showPage;
	}

	public void setShowPage(String showPage) {
		this.showPage = showPage;
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

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String[] getExaminationFnd() {
		return examinationFnd;
	}

	public void setExaminationFnd(String[] examinationFnd) {
		this.examinationFnd = examinationFnd;
	}

	public String[] getPersonalHistory() {
		return personalHistory;
	}

	public void setPersonalHistory(String[] personalHistory) {
		this.personalHistory = personalHistory;
	}

	public String[] getPastHistory() {
		return pastHistory;
	}

	public void setPastHistory(String[] pastHistory) {
		this.pastHistory = pastHistory;
	}

	public String[] getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String[] familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getPresentHistory() {
		return presentHistory;
	}

	public void setPresentHistory(String presentHistory) {
		this.presentHistory = presentHistory;
	}

	public String getHaemoglobin() {
		return haemoglobin;
	}

	public void setHaemoglobin(String haemoglobin) {
		this.haemoglobin = haemoglobin;
	}

	public String getTlc() {
		return tlc;
	}

	public void setTlc(String tlc) {
		this.tlc = tlc;
	}

	public String getPolymorphs() {
		return polymorphs;
	}

	public void setPolymorphs(String polymorphs) {
		this.polymorphs = polymorphs;
	}

	public String getLymphocytes() {
		return lymphocytes;
	}

	public void setLymphocytes(String lymphocytes) {
		this.lymphocytes = lymphocytes;
	}

	public String getEosinophils() {
		return eosinophils;
	}

	public void setEosinophils(String eosinophils) {
		this.eosinophils = eosinophils;
	}

	public String getBasophils() {
		return basophils;
	}

	public void setBasophils(String basophils) {
		this.basophils = basophils;
	}

	public String getMonocytes() {
		return monocytes;
	}

	public void setMonocytes(String monocytes) {
		this.monocytes = monocytes;
	}

	public String getRbc() {
		return rbc;
	}

	public void setRbc(String rbc) {
		this.rbc = rbc;
	}

	public String getWbc() {
		return wbc;
	}

	public void setWbc(String wbc) {
		this.wbc = wbc;
	}

	public String getPlatelets() {
		return platelets;
	}

	public void setPlatelets(String platelets) {
		this.platelets = platelets;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getEsr() {
		return esr;
	}

	public void setEsr(String esr) {
		this.esr = esr;
	}

	public String getFastingSugar() {
		return fastingSugar;
	}

	public void setFastingSugar(String fastingSugar) {
		this.fastingSugar = fastingSugar;
	}

	public String getPostPrandialSugar() {
		return postPrandialSugar;
	}

	public void setPostPrandialSugar(String postPrandialSugar) {
		this.postPrandialSugar = postPrandialSugar;
	}

	public String getGlycosylatedHaemoglobin() {
		return glycosylatedHaemoglobin;
	}

	public void setGlycosylatedHaemoglobin(String glycosylatedHaemoglobin) {
		this.glycosylatedHaemoglobin = glycosylatedHaemoglobin;
	}

	public String getHbsAg() {
		return hbsAg;
	}

	public void setHbsAg(String hbsAg) {
		this.hbsAg = hbsAg;
	}

	public String getTotalCholesterol() {
		return totalCholesterol;
	}

	public void setTotalCholesterol(String totalCholesterol) {
		this.totalCholesterol = totalCholesterol;
	}

	public String getHdlCholesterol() {
		return hdlCholesterol;
	}

	public void setHdlCholesterol(String hdlCholesterol) {
		this.hdlCholesterol = hdlCholesterol;
	}

	public String getLdlCholesterol() {
		return ldlCholesterol;
	}

	public void setLdlCholesterol(String ldlCholesterol) {
		this.ldlCholesterol = ldlCholesterol;
	}

	public String getVldlCholesterol() {
		return vldlCholesterol;
	}

	public void setVldlCholesterol(String vldlCholesterol) {
		this.vldlCholesterol = vldlCholesterol;
	}

	public String getTriglycerides() {
		return triglycerides;
	}

	public void setTriglycerides(String triglycerides) {
		this.triglycerides = triglycerides;
	}

	public String getBloodUrea() {
		return bloodUrea;
	}

	public void setBloodUrea(String bloodUrea) {
		this.bloodUrea = bloodUrea;
	}

	public String getsCreatinine() {
		return sCreatinine;
	}

	public void setsCreatinine(String sCreatinine) {
		this.sCreatinine = sCreatinine;
	}

	public String getsUricAcid() {
		return sUricAcid;
	}

	public void setsUricAcid(String sUricAcid) {
		this.sUricAcid = sUricAcid;
	}

	public String getSerumElectrolytes() {
		return serumElectrolytes;
	}

	public void setSerumElectrolytes(String serumElectrolytes) {
		this.serumElectrolytes = serumElectrolytes;
	}

	public String getSerumCreatinine() {
		return serumCreatinine;
	}

	public void setSerumCreatinine(String serumCreatinine) {
		this.serumCreatinine = serumCreatinine;
	}

	public String getSgot() {
		return sgot;
	}

	public void setSgot(String sgot) {
		this.sgot = sgot;
	}

	public String getSgpt() {
		return sgpt;
	}

	public void setSgpt(String sgpt) {
		this.sgpt = sgpt;
	}

	public String getsTotalBilirubin() {
		return sTotalBilirubin;
	}

	public void setsTotalBilirubin(String sTotalBilirubin) {
		this.sTotalBilirubin = sTotalBilirubin;
	}

	public String getsDirectBilirubin() {
		return sDirectBilirubin;
	}

	public void setsDirectBilirubin(String sDirectBilirubin) {
		this.sDirectBilirubin = sDirectBilirubin;
	}

	public String getLiverSgot() {
		return liverSgot;
	}

	public void setLiverSgot(String liverSgot) {
		this.liverSgot = liverSgot;
	}

	public String getLiverSgpt() {
		return liverSgpt;
	}

	public void setLiverSgpt(String liverSgpt) {
		this.liverSgpt = liverSgpt;
	}

	public String getUrineColor() {
		return urineColor;
	}

	public void setUrineColor(String urineColor) {
		this.urineColor = urineColor;
	}

	public String getUrineAlbumin() {
		return urineAlbumin;
	}

	public void setUrineAlbumin(String urineAlbumin) {
		this.urineAlbumin = urineAlbumin;
	}

	public String getUrineSugar() {
		return urineSugar;
	}

	public void setUrineSugar(String urineSugar) {
		this.urineSugar = urineSugar;
	}

	public String getUrineMicroscopicExam() {
		return urineMicroscopicExam;
	}

	public void setUrineMicroscopicExam(String urineMicroscopicExam) {
		this.urineMicroscopicExam = urineMicroscopicExam;
	}

	public String getAbdomenUltrasound() {
		return abdomenUltrasound;
	}

	public void setAbdomenUltrasound(String abdomenUltrasound) {
		this.abdomenUltrasound = abdomenUltrasound;
	}

	public String getChestXrayPAView() {
		return chestXrayPAView;
	}

	public void setChestXrayPAView(String chestXrayPAView) {
		this.chestXrayPAView = chestXrayPAView;
	}

	public String getPulmonaryFunction() {
		return pulmonaryFunction;
	}

	public void setPulmonaryFunction(String pulmonaryFunction) {
		this.pulmonaryFunction = pulmonaryFunction;
	}

	public String getEcg() {
		return ecg;
	}

	public void setEcg(String ecg) {
		this.ecg = ecg;
	}

	public String getTwodEcho() {
		return twodEcho;
	}

	public void setTwodEcho(String twodEcho) {
		this.twodEcho = twodEcho;
	}

	public String getTreadmillTest() {
		return treadmillTest;
	}

	public void setTreadmillTest(String treadmillTest) {
		this.treadmillTest = treadmillTest;
	}

	public String getRetinopathy() {
		return retinopathy;
	}

	public void setRetinopathy(String retinopathy) {
		this.retinopathy = retinopathy;
	}

	public String getFundoscopy() {
		return fundoscopy;
	}

	public void setFundoscopy(String fundoscopy) {
		this.fundoscopy = fundoscopy;
	}

	public String getAudiometry() {
		return audiometry;
	}

	public void setAudiometry(String audiometry) {
		this.audiometry = audiometry;
	}

	public String getPelvicLocalExam() {
		return pelvicLocalExam;
	}

	public void setPelvicLocalExam(String pelvicLocalExam) {
		this.pelvicLocalExam = pelvicLocalExam;
	}

	public String getPerVaginum() {
		return perVaginum;
	}

	public void setPerVaginum(String perVaginum) {
		this.perVaginum = perVaginum;
	}

	public String getPerSpeculum() {
		return perSpeculum;
	}

	public void setPerSpeculum(String perSpeculum) {
		this.perSpeculum = perSpeculum;
	}

	public String getSurgicalExam() {
		return surgicalExam;
	}

	public void setSurgicalExam(String surgicalExam) {
		this.surgicalExam = surgicalExam;
	}

	public String getBreastExam() {
		return breastExam;
	}

	public void setBreastExam(String breastExam) {
		this.breastExam = breastExam;
	}

	public String getPapSmear() {
		return papSmear;
	}

	public void setPapSmear(String papSmear) {
		this.papSmear = papSmear;
	}

	public String getExamFndsVal() {
		return examFndsVal;
	}

	public void setExamFndsVal(String examFndsVal) {
		this.examFndsVal = examFndsVal;
	}

	public String getPersonalHistVal() {
		return personalHistVal;
	}

	public void setPersonalHistVal(String personalHistVal) {
		this.personalHistVal = personalHistVal;
	}

	public String[] getPersonalHist() {
		return personalHist;
	}

	public void setPersonalHist(String[] personalHist) {
		this.personalHist = personalHist;
	}

	public String getPastHistrySurg() {
		return pastHistrySurg;
	}

	public void setPastHistrySurg(String pastHistrySurg) {
		this.pastHistrySurg = pastHistrySurg;
	}

	public String getPastHistryOthr() {
		return pastHistryOthr;
	}

	public void setPastHistryOthr(String pastHistryOthr) {
		this.pastHistryOthr = pastHistryOthr;
	}

	public String getFamilyHistoryOthr() {
		return familyHistoryOthr;
	}

	public void setFamilyHistoryOthr(String familyHistoryOthr) {
		this.familyHistoryOthr = familyHistoryOthr;
	}

	public String getPastHistryCancers() {
		return pastHistryCancers;
	}

	public void setPastHistryCancers(String pastHistryCancers) {
		this.pastHistryCancers = pastHistryCancers;
	}

	public String getPastHistryEndDis() {
		return pastHistryEndDis;
	}

	public void setPastHistryEndDis(String pastHistryEndDis) {
		this.pastHistryEndDis = pastHistryEndDis;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getCommHouseNo() {
		return commHouseNo;
	}

	public void setCommHouseNo(String commHouseNo) {
		this.commHouseNo = commHouseNo;
	}

	public String getCommStreet() {
		return commStreet;
	}

	public void setCommStreet(String commStreet) {
		this.commStreet = commStreet;
	}

	public String getCommState() {
		return commState;
	}

	public void setCommState(String commState) {
		this.commState = commState;
	}

	public String getCommDistrict() {
		return commDistrict;
	}

	public void setCommDistrict(String commDistrict) {
		this.commDistrict = commDistrict;
	}

	public String getCommMandal() {
		return commMandal;
	}

	public void setCommMandal(String commMandal) {
		this.commMandal = commMandal;
	}

	public String getCommVillage() {
		return commVillage;
	}

	public void setCommVillage(String commVillage) {
		this.commVillage = commVillage;
	}

	public String getCommPin() {
		return commPin;
	}

	public void setCommPin(String commPin) {
		this.commPin = commPin;
	}


	public String getMedcoFlag() {
		return medcoFlag;
	}

	public void setMedcoFlag(String medcoFlag) {
		this.medcoFlag = medcoFlag;
	}

	public String getAddrEnable() {
		return addrEnable;
	}

	public void setAddrEnable(String addrEnable) {
		this.addrEnable = addrEnable;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitHodName() {
		return unitHodName;
	}

	public void setUnitHodName(String unitHodName) {
		this.unitHodName = unitHodName;
	}

	public String getGenInvestigations() {
		return genInvestigations;
	}

	public void setGenInvestigations(String genInvestigations) {
		this.genInvestigations = genInvestigations;
	}

	public String getGenBlockInvestName() {
		return genBlockInvestName;
	}

	public void setGenBlockInvestName(String genBlockInvestName) {
		this.genBlockInvestName = genBlockInvestName;
	}

	public FormFile getInvestAttach(int index)
		{
			return investAttach[index];
		}
	public void setInvestAttach(int index,FormFile value)
		{
			investAttach[index]=value;
		}

	public FormFile getConsultAttach(int index)
	{
		return consultAttach[index];
	}
	public void setConsultAttach(int index,FormFile value)
	{
	consultAttach[index]=value;
	}
	
	

}