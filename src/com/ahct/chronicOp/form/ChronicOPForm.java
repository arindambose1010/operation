package com.ahct.chronicOp.form;


import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;

import com.ahct.chronicOp.vo.ChronicOPVO;


public class ChronicOPForm extends ActionForm {
	

	private String patientId;
	private String msg;
	private String empCode;
	private String errMsg;
	private String cardNo;
	private String card_type;
	private String head;
	private String cardIssueDt;
	private String dateOfBirth;
	private String ageString;
	private String fname;
	private String mname;
	private String lname;
	private String eMailId;
	private String chronicSubID;
	public String getChronicSubID() {
		return chronicSubID;
	}
	public void setChronicSubID(String chronicSubID) {
		this.chronicSubID = chronicSubID;
	}


	private String child;
	private String months;
	private String days;
	private String years;
	private String caste;
	private String maritalStatus;
	private String relation;
	private String occupation;
	private String contactno;
	private String slab;
	private String commCheck;
	private String comm_hno;
	private String comm_street;
	private String comm_state;
	private String comm_dist;
	private String comm_mandal;
	private String same_mdl_mcl;
	private String comm_mdl_mcl;
	private String same_municipality;
	private String comm_municipality;
	private String comm_village;
	private String comm_hamlet;
	private String comm_pin;
	private String hno;
	private String street;
	private String state;
	
	private String mdl_mcl;
	private String mandal;
	private String municipality;
	private String village;
	private String hamlet;
	private String pin;
	private List<LabelBean> districtList;
	private List<LabelBean> symList;
	private List<LabelBean> genInvList;
	private List<DrugsVO> drugLt;
	private List<PreauthVO> investigationLt;
	private String same_state;
	private String same_dist;
	private String same_mandal;
	private String same_village;
	private String same_hamlet;
	private String hospitalId;
	private String hospitalName;
	private String dtTime;
	private String sourceNo;
	private String photoUrl;
	private String disableFlag;
	private String hosptype;
	//Start of patient search
	private String patientNo;
	private String patientName;
	
	private String phaseId;
	private String ageRange;
	private int startIndex;
	private int noOfPages;
	private String currCaseId;
	private String currPatId;
	private String currPatName;
	private String currHealthCardNo;
	private String currStateId;
	private String currDistrictId;
	private String currFromDate;
	private String currToDate;
	private String currHospId;
	//end of patient search
	//Start of Ramco patient diagnosis to convert to IP/OP
	private String complaints;
	private String[] patientComplaint;
	private String complaintCode;
	private String patComplaintCode;
	private String presentHistory;
	private String[] pastHistory;
	private String[] personalHistory;
	private String[] personalHist;
	private String[] familyHistory;
	private String[] examinationFnd;
	private String speciality;
	private String otherDocDetailsList;
	private String genInvestigations;
	private String genBlockInvestName;
	private String provisionalDiagnosis;
	private String investigations;
	private String investigationSel;
	private String addTests;
	private String diagnosedBy;
	private String doctorName;
	private String otherDocName;
	private String docRegNo;
	private String docQual;
	private String docMobileNo;
	private String userRole;
	private String claimSubmit;

   private String chronicNo;
   private String addrEnable;
   private String packageDrugAmt;
   
   private String caseApprovalFlag;
   private String chronicStatus;
   private String ceoRemark;
 
   
	
	
	
	public String getChronicNo() {
	return chronicNo;
}
public void setChronicNo(String chronicNo) {
	this.chronicNo = chronicNo;
}


	//IP Doc Details Properties
	private String ipDiagnosedBy;
	private String ipDoctorName;
	private String ipOtherDocName;
	private String ipDocRegNo;
	private String ipDocQual;
	private String ipDocMobileNo;
	//End of IP Doc Details Properties
	private String addr;
	private String hospaddr;
	private String dateIp;
	private String patTemp;
	private String patPulse;
	private String respir;
	private String bloodPr;
	private String pastHist;
	private String famHist;
	private String complType;
	private String pastIllnessValue;
	private String familyHistValue;
	private String allergy;
	private String habbits;
	private List<PreauthVO> procList;
	private String patientType;
	private String testsCount;
	private String ipNo;
	private String admissionType;
	private String ipDate;
	private String diagnosisCategory;
	private String therapyCategory;
	private String therapy;
	private String therapyType;
	private String remarks;
	private String ipFinalDiagnosis;
	private String ipPrescription;
	private String opNo;
	private String opDate;
	private String opRemarks;
	private String opFinalDiagnosis;
	private String[] opPrescription;
	private FormFile attach[]= new FormFile[100];
	private FormFile genAttach[]= new FormFile[100];
	private FormFile updateGenAttach[]= new FormFile[100];
	private String caseId;
	//END of IP/OP
	private String teleCallerDesgn;
	private String teleCallerName;
	private String telePhoneNo;
	private String teleDocName;
	private String teleDocDesgn;
	private String teleDocPhoneNo;
	private String remarksProcedure;
	private String remarksDiagnosis;
	private String surgeryName;
	private String catName;
	private String subCatName;
	private String ICDCatName;
	private String ICDSubCatName;
	private String ICDProcName;
	private String ICDCatCode;
	private String ICDSubCatCode;
	private String ICDProcCode;
	private String procCode;
	private String diagType;
	private String mainCatName;
	private String diseaseName;
	private String disAnatomicalName;
	private String diagCode;
	private String mainCatCode;
	private String catCode;
	private String subCatCode;
	private String diseaseCode;
	private String disAnatomicalCode;
	private String indication;
	private String telephonicId;
	private String telephonicReg;
	private String bloodPrLt;
	private String bloodPrRt;

	private String personalHistVal;
	private String examFndsVal;

	private String mainSymptomCode;
	private String mainSymptomName;
	private String subSymptomName;
	private String subSymptomCode;
	private String symptomName;
	private String symptomCode;
	private String asriCatName;
	private String asriCatCode;
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String drugSubTypeName;
	private String asriDrugCode;
	private String drugName;
	private String drugCode;
	private String drugs;
	private String symptoms;
	private String pSubGrpName;
	private String pSubGrpCode;
	private String cSubGrpName;
	private String cSubGrpCode;


	private String chronicID;
	private String name;
	private String employeeNo;
	private String district;
	private String gender;
	private BigDecimal  age;
	private String registrationDate;

	
	private String fromDate;
	private String toDate;

	
	private int startPage;
	private int endPage;

	private int endIndex;
	private String rowsPerPage;
	private String showPage;
	private String next;
	private String prev;
	private String pastHistryOthr;
	private String pastHistryCancers;
	private String pastHistryEndDis;
	private String pastHistrySurg;
	private String familyHistoryOthr;
	private String route;
	private String routeType;
	private String strengthType;
	private String strength;
	private String dosage;
	private String medicatPeriod;

	private String opCatName;
	private String opCatCode;
	private String opPkgName;
	private String opPkgCode;
	private String opIcdName;
	private String opIcdCode;
	private String mithra;
	private String docSpecReg;
	private String legalCase;
	private String legalCaseNo;
	private String policeStatName;
	private String procUnits;
	private String scheme;
	
    private String deptHod;
    private String postDist;
    private String stoCode;
    private String ddoCode;
    /*added for claims*/
    private long claimAmt;
	private long claimNwhAmt;
    private String medcoRemarks;
    private String namRemarks;
    public String exeDisphotoChklst;
	public String exeDisphotoremark;
	public String exePatphotoChklst;
	public String exePatphotoRemark;
	public String exeAcqvrifyChklst;
	public String exeAcqverifyRemark;
	public String exeMedverifyChklst;
	public String exeMedVerifyRemark;
	public String exeQuantverifyRemark;
	public String exeQuantverifyChklst;
	public String exereprtcheckChklst;
	public String exeReprtcheckRemark;
	public String ftdRemarkvrifedChklst;
	public String ftdRemarkvrifedRemark;
	public String ftdBeneficiryChklst;
	public String ftdBeneficiryRemark;
	private String ftdRmks;
	private String showNam;
	private String showMedco;
	private String showFcx;
	private String showFtd;
	private String followUpId;
	private String followUpDt;
	private long packageAmt;
    private List<ClaimsFlowVO> lstworkFlow;
	 private long claimNamAmt;
	 private long claimFcxAmt;
	 private long claimFtdAmt;
	 private long claimChAmt;
	 private String fcxRemark;
	 private String chRemark;
	 private String showCh;
	 private String roleId;
     private long acoAprAmt;
 	 private String acoRemark;
 	 private String showAco;
 	private List<LabelBean> casesForPaymentList;
 	private List<LabelBean> buttonList;
 	private String batchNo;
 	private String expiryDt;
	private String regCaseFlg;
	private String addPckgFlg;
	private String chronicPackageCode;
	private String chronicCatCode;
	private String hospCode;
	private String checkType;
	
	private String[] chronicPkgList;
	private int chronicPkgCount;
	
	private String investOthers;
	private String otherInvName;
	private int otherInvestCount;
	private String hospScheme;
	
	private String stateType;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getCardIssueDt() {
		return cardIssueDt;
	}
	public void setCardIssueDt(String cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAgeString() {
		return ageString;
	}
	public void setAgeString(String ageString) {
		this.ageString = ageString;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String geteMailId() {
		return eMailId;
	}
	public void seteMailId(String eMailId) {
		this.eMailId = eMailId;
	}
	
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
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
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
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
	public String getComm_state() {
		return comm_state;
	}
	public void setComm_state(String comm_state) {
		this.comm_state = comm_state;
	}
	public String getComm_dist() {
		return comm_dist;
	}
	public void setComm_dist(String comm_dist) {
		this.comm_dist = comm_dist;
	}
	public String getComm_mandal() {
		return comm_mandal;
	}
	public void setComm_mandal(String comm_mandal) {
		this.comm_mandal = comm_mandal;
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
	public String getComm_village() {
		return comm_village;
	}
	public void setComm_village(String comm_village) {
		this.comm_village = comm_village;
	}
	public String getComm_hamlet() {
		return comm_hamlet;
	}
	public void setComm_hamlet(String comm_hamlet) {
		this.comm_hamlet = comm_hamlet;
	}
	public String getComm_pin() {
		return comm_pin;
	}
	public void setComm_pin(String comm_pin) {
		this.comm_pin = comm_pin;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
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
	public String getHamlet() {
		return hamlet;
	}
	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public List<LabelBean> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<LabelBean> districtList) {
		this.districtList = districtList;
	}
	public List<LabelBean> getSymList() {
		return symList;
	}
	public void setSymList(List<LabelBean> symList) {
		this.symList = symList;
	}
	public List<LabelBean> getGenInvList() {
		return genInvList;
	}
	public void setGenInvList(List<LabelBean> genInvList) {
		this.genInvList = genInvList;
	}
	public List<DrugsVO> getDrugLt() {
		return drugLt;
	}
	public void setDrugLt(List<DrugsVO> drugLt) {
		this.drugLt = drugLt;
	}
	public List<PreauthVO> getInvestigationLt() {
		return investigationLt;
	}
	public void setInvestigationLt(List<PreauthVO> investigationLt) {
		this.investigationLt = investigationLt;
	}
	public String getSame_state() {
		return same_state;
	}
	public void setSame_state(String same_state) {
		this.same_state = same_state;
	}
	public String getSame_dist() {
		return same_dist;
	}
	public void setSame_dist(String same_dist) {
		this.same_dist = same_dist;
	}
	public String getSame_mandal() {
		return same_mandal;
	}
	public void setSame_mandal(String same_mandal) {
		this.same_mandal = same_mandal;
	}
	public String getSame_village() {
		return same_village;
	}
	public void setSame_village(String same_village) {
		this.same_village = same_village;
	}
	public String getSame_hamlet() {
		return same_hamlet;
	}
	public void setSame_hamlet(String same_hamlet) {
		this.same_hamlet = same_hamlet;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDtTime() {
		return dtTime;
	}
	public void setDtTime(String dtTime) {
		this.dtTime = dtTime;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDisableFlag() {
		return disableFlag;
	}
	public void setDisableFlag(String disableFlag) {
		this.disableFlag = disableFlag;
	}
	public String getHosptype() {
		return hosptype;
	}
	public void setHosptype(String hosptype) {
		this.hosptype = hosptype;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public String getCurrCaseId() {
		return currCaseId;
	}
	public void setCurrCaseId(String currCaseId) {
		this.currCaseId = currCaseId;
	}
	public String getCurrPatId() {
		return currPatId;
	}
	public void setCurrPatId(String currPatId) {
		this.currPatId = currPatId;
	}
	public String getCurrPatName() {
		return currPatName;
	}
	public void setCurrPatName(String currPatName) {
		this.currPatName = currPatName;
	}
	public String getCurrHealthCardNo() {
		return currHealthCardNo;
	}
	public void setCurrHealthCardNo(String currHealthCardNo) {
		this.currHealthCardNo = currHealthCardNo;
	}
	public String getCurrStateId() {
		return currStateId;
	}
	public void setCurrStateId(String currStateId) {
		this.currStateId = currStateId;
	}
	public String getCurrDistrictId() {
		return currDistrictId;
	}
	public void setCurrDistrictId(String currDistrictId) {
		this.currDistrictId = currDistrictId;
	}
	public String getCurrFromDate() {
		return currFromDate;
	}
	public void setCurrFromDate(String currFromDate) {
		this.currFromDate = currFromDate;
	}
	public String getCurrToDate() {
		return currToDate;
	}
	public void setCurrToDate(String currToDate) {
		this.currToDate = currToDate;
	}
	public String getCurrHospId() {
		return currHospId;
	}
	public void setCurrHospId(String currHospId) {
		this.currHospId = currHospId;
	}
	public String getComplaints() {
		return complaints;
	}
	public void setComplaints(String complaints) {
		this.complaints = complaints;
	}
	public String[] getPatientComplaint() {
		return patientComplaint;
	}
	public void setPatientComplaint(String[] patientComplaint) {
		this.patientComplaint = patientComplaint;
	}
	public String getComplaintCode() {
		return complaintCode;
	}
	public void setComplaintCode(String complaintCode) {
		this.complaintCode = complaintCode;
	}
	public String getPatComplaintCode() {
		return patComplaintCode;
	}
	public void setPatComplaintCode(String patComplaintCode) {
		this.patComplaintCode = patComplaintCode;
	}
	public String getPresentHistory() {
		return presentHistory;
	}
	public void setPresentHistory(String presentHistory) {
		this.presentHistory = presentHistory;
	}
	public String[] getPastHistory() {
		return pastHistory;
	}
	public void setPastHistory(String[] pastHistory) {
		this.pastHistory = pastHistory;
	}
	public String[] getPersonalHistory() {
		return personalHistory;
	}
	public void setPersonalHistory(String[] personalHistory) {
		this.personalHistory = personalHistory;
	}
	public String[] getPersonalHist() {
		return personalHist;
	}
	public void setPersonalHist(String[] personalHist) {
		this.personalHist = personalHist;
	}
	public String[] getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String[] familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String[] getExaminationFnd() {
		return examinationFnd;
	}
	public void setExaminationFnd(String[] examinationFnd) {
		this.examinationFnd = examinationFnd;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getOtherDocDetailsList() {
		return otherDocDetailsList;
	}
	public void setOtherDocDetailsList(String otherDocDetailsList) {
		this.otherDocDetailsList = otherDocDetailsList;
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
	public String getProvisionalDiagnosis() {
		return provisionalDiagnosis;
	}
	public void setProvisionalDiagnosis(String provisionalDiagnosis) {
		this.provisionalDiagnosis = provisionalDiagnosis;
	}
	public String getInvestigations() {
		return investigations;
	}
	public void setInvestigations(String investigations) {
		this.investigations = investigations;
	}
	public String getInvestigationSel() {
		return investigationSel;
	}
	public void setInvestigationSel(String investigationSel) {
		this.investigationSel = investigationSel;
	}
	public String getAddTests() {
		return addTests;
	}
	public void setAddTests(String addTests) {
		this.addTests = addTests;
	}
	public String getDiagnosedBy() {
		return diagnosedBy;
	}
	public void setDiagnosedBy(String diagnosedBy) {
		this.diagnosedBy = diagnosedBy;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getOtherDocName() {
		return otherDocName;
	}
	public void setOtherDocName(String otherDocName) {
		this.otherDocName = otherDocName;
	}
	public String getDocRegNo() {
		return docRegNo;
	}
	public void setDocRegNo(String docRegNo) {
		this.docRegNo = docRegNo;
	}
	public String getDocQual() {
		return docQual;
	}
	public void setDocQual(String docQual) {
		this.docQual = docQual;
	}
	public String getDocMobileNo() {
		return docMobileNo;
	}
	public void setDocMobileNo(String docMobileNo) {
		this.docMobileNo = docMobileNo;
	}
	public String getIpDiagnosedBy() {
		return ipDiagnosedBy;
	}
	public void setIpDiagnosedBy(String ipDiagnosedBy) {
		this.ipDiagnosedBy = ipDiagnosedBy;
	}
	public String getIpDoctorName() {
		return ipDoctorName;
	}
	public void setIpDoctorName(String ipDoctorName) {
		this.ipDoctorName = ipDoctorName;
	}
	public String getIpOtherDocName() {
		return ipOtherDocName;
	}
	public void setIpOtherDocName(String ipOtherDocName) {
		this.ipOtherDocName = ipOtherDocName;
	}
	public String getIpDocRegNo() {
		return ipDocRegNo;
	}
	public void setIpDocRegNo(String ipDocRegNo) {
		this.ipDocRegNo = ipDocRegNo;
	}
	public String getIpDocQual() {
		return ipDocQual;
	}
	public void setIpDocQual(String ipDocQual) {
		this.ipDocQual = ipDocQual;
	}
	public String getIpDocMobileNo() {
		return ipDocMobileNo;
	}
	public void setIpDocMobileNo(String ipDocMobileNo) {
		this.ipDocMobileNo = ipDocMobileNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getHospaddr() {
		return hospaddr;
	}
	public void setHospaddr(String hospaddr) {
		this.hospaddr = hospaddr;
	}
	public String getDateIp() {
		return dateIp;
	}
	public void setDateIp(String dateIp) {
		this.dateIp = dateIp;
	}
	public String getPatTemp() {
		return patTemp;
	}
	public void setPatTemp(String patTemp) {
		this.patTemp = patTemp;
	}
	public String getPatPulse() {
		return patPulse;
	}
	public void setPatPulse(String patPulse) {
		this.patPulse = patPulse;
	}
	public String getRespir() {
		return respir;
	}
	public void setRespir(String respir) {
		this.respir = respir;
	}
	public String getBloodPr() {
		return bloodPr;
	}
	public void setBloodPr(String bloodPr) {
		this.bloodPr = bloodPr;
	}
	public String getPastHist() {
		return pastHist;
	}
	public void setPastHist(String pastHist) {
		this.pastHist = pastHist;
	}
	public String getFamHist() {
		return famHist;
	}
	public void setFamHist(String famHist) {
		this.famHist = famHist;
	}
	public String getComplType() {
		return complType;
	}
	public void setComplType(String complType) {
		this.complType = complType;
	}
	public String getPastIllnessValue() {
		return pastIllnessValue;
	}
	public void setPastIllnessValue(String pastIllnessValue) {
		this.pastIllnessValue = pastIllnessValue;
	}
	public String getFamilyHistValue() {
		return familyHistValue;
	}
	public void setFamilyHistValue(String familyHistValue) {
		this.familyHistValue = familyHistValue;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getHabbits() {
		return habbits;
	}
	public void setHabbits(String habbits) {
		this.habbits = habbits;
	}
	public List<PreauthVO> getProcList() {
		return procList;
	}
	public void setProcList(List<PreauthVO> procList) {
		this.procList = procList;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getTestsCount() {
		return testsCount;
	}
	public void setTestsCount(String testsCount) {
		this.testsCount = testsCount;
	}
	public String getIpNo() {
		return ipNo;
	}
	public void setIpNo(String ipNo) {
		this.ipNo = ipNo;
	}
	public String getAdmissionType() {
		return admissionType;
	}
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}
	public String getIpDate() {
		return ipDate;
	}
	public void setIpDate(String ipDate) {
		this.ipDate = ipDate;
	}
	public String getDiagnosisCategory() {
		return diagnosisCategory;
	}
	public void setDiagnosisCategory(String diagnosisCategory) {
		this.diagnosisCategory = diagnosisCategory;
	}
	public String getTherapyCategory() {
		return therapyCategory;
	}
	public void setTherapyCategory(String therapyCategory) {
		this.therapyCategory = therapyCategory;
	}
	public String getTherapy() {
		return therapy;
	}
	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}
	public String getTherapyType() {
		return therapyType;
	}
	public void setTherapyType(String therapyType) {
		this.therapyType = therapyType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIpFinalDiagnosis() {
		return ipFinalDiagnosis;
	}
	public void setIpFinalDiagnosis(String ipFinalDiagnosis) {
		this.ipFinalDiagnosis = ipFinalDiagnosis;
	}
	public String getIpPrescription() {
		return ipPrescription;
	}
	public void setIpPrescription(String ipPrescription) {
		this.ipPrescription = ipPrescription;
	}
	public String getOpNo() {
		return opNo;
	}
	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public String getOpRemarks() {
		return opRemarks;
	}
	public void setOpRemarks(String opRemarks) {
		this.opRemarks = opRemarks;
	}
	public String getOpFinalDiagnosis() {
		return opFinalDiagnosis;
	}
	public void setOpFinalDiagnosis(String opFinalDiagnosis) {
		this.opFinalDiagnosis = opFinalDiagnosis;
	}
	public String[] getOpPrescription() {
		return opPrescription;
	}
	public void setOpPrescription(String[] opPrescription) {
		this.opPrescription = opPrescription;
	}
	public FormFile[] getAttach() {
		return attach;
	}
	public void setAttach(FormFile[] attach) {
		this.attach = attach;
	}
	public FormFile[] getGenAttach() {
		return genAttach;
	}
	public void setGenAttach(FormFile[] genAttach) {
		this.genAttach = genAttach;
	}

	public void setUpdateGenAttach(FormFile[] updateGenAttach) {
		this.updateGenAttach = updateGenAttach;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getTeleCallerDesgn() {
		return teleCallerDesgn;
	}
	public void setTeleCallerDesgn(String teleCallerDesgn) {
		this.teleCallerDesgn = teleCallerDesgn;
	}
	public String getTeleCallerName() {
		return teleCallerName;
	}
	public void setTeleCallerName(String teleCallerName) {
		this.teleCallerName = teleCallerName;
	}
	public String getTelePhoneNo() {
		return telePhoneNo;
	}
	public void setTelePhoneNo(String telePhoneNo) {
		this.telePhoneNo = telePhoneNo;
	}
	public String getTeleDocName() {
		return teleDocName;
	}
	public void setTeleDocName(String teleDocName) {
		this.teleDocName = teleDocName;
	}
	public String getTeleDocDesgn() {
		return teleDocDesgn;
	}
	public void setTeleDocDesgn(String teleDocDesgn) {
		this.teleDocDesgn = teleDocDesgn;
	}
	public String getTeleDocPhoneNo() {
		return teleDocPhoneNo;
	}
	public void setTeleDocPhoneNo(String teleDocPhoneNo) {
		this.teleDocPhoneNo = teleDocPhoneNo;
	}
	public String getRemarksProcedure() {
		return remarksProcedure;
	}
	public void setRemarksProcedure(String remarksProcedure) {
		this.remarksProcedure = remarksProcedure;
	}
	public String getRemarksDiagnosis() {
		return remarksDiagnosis;
	}
	public void setRemarksDiagnosis(String remarksDiagnosis) {
		this.remarksDiagnosis = remarksDiagnosis;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getICDCatName() {
		return ICDCatName;
	}
	public void setICDCatName(String iCDCatName) {
		ICDCatName = iCDCatName;
	}
	public String getICDSubCatName() {
		return ICDSubCatName;
	}
	public void setICDSubCatName(String iCDSubCatName) {
		ICDSubCatName = iCDSubCatName;
	}
	public String getICDProcName() {
		return ICDProcName;
	}
	public void setICDProcName(String iCDProcName) {
		ICDProcName = iCDProcName;
	}
	public String getICDCatCode() {
		return ICDCatCode;
	}
	public void setICDCatCode(String iCDCatCode) {
		ICDCatCode = iCDCatCode;
	}
	public String getICDSubCatCode() {
		return ICDSubCatCode;
	}
	public void setICDSubCatCode(String iCDSubCatCode) {
		ICDSubCatCode = iCDSubCatCode;
	}
	public String getICDProcCode() {
		return ICDProcCode;
	}
	public void setICDProcCode(String iCDProcCode) {
		ICDProcCode = iCDProcCode;
	}
	public String getProcCode() {
		return procCode;
	}
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}
	public String getDiagType() {
		return diagType;
	}
	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}
	public String getMainCatName() {
		return mainCatName;
	}
	public void setMainCatName(String mainCatName) {
		this.mainCatName = mainCatName;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDisAnatomicalName() {
		return disAnatomicalName;
	}
	public void setDisAnatomicalName(String disAnatomicalName) {
		this.disAnatomicalName = disAnatomicalName;
	}
	public String getDiagCode() {
		return diagCode;
	}
	public void setDiagCode(String diagCode) {
		this.diagCode = diagCode;
	}
	public String getMainCatCode() {
		return mainCatCode;
	}
	public void setMainCatCode(String mainCatCode) {
		this.mainCatCode = mainCatCode;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getSubCatCode() {
		return subCatCode;
	}
	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getDisAnatomicalCode() {
		return disAnatomicalCode;
	}
	public void setDisAnatomicalCode(String disAnatomicalCode) {
		this.disAnatomicalCode = disAnatomicalCode;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getTelephonicId() {
		return telephonicId;
	}
	public void setTelephonicId(String telephonicId) {
		this.telephonicId = telephonicId;
	}
	public String getTelephonicReg() {
		return telephonicReg;
	}
	public void setTelephonicReg(String telephonicReg) {
		this.telephonicReg = telephonicReg;
	}
	public String getBloodPrLt() {
		return bloodPrLt;
	}
	public void setBloodPrLt(String bloodPrLt) {
		this.bloodPrLt = bloodPrLt;
	}
	public String getBloodPrRt() {
		return bloodPrRt;
	}
	public void setBloodPrRt(String bloodPrRt) {
		this.bloodPrRt = bloodPrRt;
	}
	public String getPersonalHistVal() {
		return personalHistVal;
	}
	public void setPersonalHistVal(String personalHistVal) {
		this.personalHistVal = personalHistVal;
	}
	public String getExamFndsVal() {
		return examFndsVal;
	}
	public void setExamFndsVal(String examFndsVal) {
		this.examFndsVal = examFndsVal;
	}
	public String getMainSymptomCode() {
		return mainSymptomCode;
	}
	public void setMainSymptomCode(String mainSymptomCode) {
		this.mainSymptomCode = mainSymptomCode;
	}
	public String getMainSymptomName() {
		return mainSymptomName;
	}
	public void setMainSymptomName(String mainSymptomName) {
		this.mainSymptomName = mainSymptomName;
	}
	public String getSubSymptomName() {
		return subSymptomName;
	}
	public void setSubSymptomName(String subSymptomName) {
		this.subSymptomName = subSymptomName;
	}
	public String getSubSymptomCode() {
		return subSymptomCode;
	}
	public void setSubSymptomCode(String subSymptomCode) {
		this.subSymptomCode = subSymptomCode;
	}
	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	public String getSymptomCode() {
		return symptomCode;
	}
	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}
	public String getAsriCatName() {
		return asriCatName;
	}
	public void setAsriCatName(String asriCatName) {
		this.asriCatName = asriCatName;
	}
	public String getAsriCatCode() {
		return asriCatCode;
	}
	public void setAsriCatCode(String asriCatCode) {
		this.asriCatCode = asriCatCode;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}
	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}
	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}
	public String getAsriDrugCode() {
		return asriDrugCode;
	}
	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getpSubGrpName() {
		return pSubGrpName;
	}
	public void setpSubGrpName(String pSubGrpName) {
		this.pSubGrpName = pSubGrpName;
	}
	public String getpSubGrpCode() {
		return pSubGrpCode;
	}
	public void setpSubGrpCode(String pSubGrpCode) {
		this.pSubGrpCode = pSubGrpCode;
	}
	public String getcSubGrpName() {
		return cSubGrpName;
	}
	public void setcSubGrpName(String cSubGrpName) {
		this.cSubGrpName = cSubGrpName;
	}
	public String getcSubGrpCode() {
		return cSubGrpCode;
	}
	public void setcSubGrpCode(String cSubGrpCode) {
		this.cSubGrpCode = cSubGrpCode;
	}
	public String getPastHistryOthr() {
		return pastHistryOthr;
	}
	public void setPastHistryOthr(String pastHistryOthr) {
		this.pastHistryOthr = pastHistryOthr;
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
	public String getPastHistrySurg() {
		return pastHistrySurg;
	}
	public void setPastHistrySurg(String pastHistrySurg) {
		this.pastHistrySurg = pastHistrySurg;
	}
	public String getFamilyHistoryOthr() {
		return familyHistoryOthr;
	}
	public void setFamilyHistoryOthr(String familyHistoryOthr) {
		this.familyHistoryOthr = familyHistoryOthr;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	public String getStrengthType() {
		return strengthType;
	}
	public void setStrengthType(String strengthType) {
		this.strengthType = strengthType;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getMedicatPeriod() {
		return medicatPeriod;
	}
	public void setMedicatPeriod(String medicatPeriod) {
		this.medicatPeriod = medicatPeriod;
	}
	public String getOpCatName() {
		return opCatName;
	}
	public void setOpCatName(String opCatName) {
		this.opCatName = opCatName;
	}
	public String getOpCatCode() {
		return opCatCode;
	}
	public void setOpCatCode(String opCatCode) {
		this.opCatCode = opCatCode;
	}
	public String getOpPkgName() {
		return opPkgName;
	}
	public void setOpPkgName(String opPkgName) {
		this.opPkgName = opPkgName;
	}
	public String getOpPkgCode() {
		return opPkgCode;
	}
	public void setOpPkgCode(String opPkgCode) {
		this.opPkgCode = opPkgCode;
	}
	public String getOpIcdName() {
		return opIcdName;
	}
	public void setOpIcdName(String opIcdName) {
		this.opIcdName = opIcdName;
	}
	public String getOpIcdCode() {
		return opIcdCode;
	}
	public void setOpIcdCode(String opIcdCode) {
		this.opIcdCode = opIcdCode;
	}
	public String getMithra() {
		return mithra;
	}
	public void setMithra(String mithra) {
		this.mithra = mithra;
	}
	public String getDocSpecReg() {
		return docSpecReg;
	}
	public void setDocSpecReg(String docSpecReg) {
		this.docSpecReg = docSpecReg;
	}
	public String getLegalCase() {
		return legalCase;
	}
	public void setLegalCase(String legalCase) {
		this.legalCase = legalCase;
	}
	public String getLegalCaseNo() {
		return legalCaseNo;
	}
	public void setLegalCaseNo(String legalCaseNo) {
		this.legalCaseNo = legalCaseNo;
	}
	public String getPoliceStatName() {
		return policeStatName;
	}
	public void setPoliceStatName(String policeStatName) {
		this.policeStatName = policeStatName;
	}
	public String getProcUnits() {
		return procUnits;
	}
	public void setProcUnits(String procUnits) {
		this.procUnits = procUnits;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	
	List<ChronicOPVO>  chronincOPPatList;
	

	List<ChronicOPVO> chronicCSVList;
	public List<ChronicOPVO> getChronicCSVList() {
		return chronicCSVList;
	}
	public void setChronicCSVList(List<ChronicOPVO> chronicCSVList) {
		this.chronicCSVList = chronicCSVList;
	}
	

	

	public List<ChronicOPVO> getChronincOPPatList() {
		return chronincOPPatList;
	}
	public void setChronincOPPatList(List<ChronicOPVO> chronincOPPatList) {
		this.chronincOPPatList = chronincOPPatList;
	}
	
	public String getChronicID() {
		return chronicID;
	}
	public void setChronicID(String chronicID) {
		this.chronicID = chronicID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public BigDecimal getAge() {
		return age;
	}
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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

	public FormFile getUpdateGenAttach(int index) {
		return updateGenAttach[index];
	}
	public FormFile getGenAttach(int index) {
		return genAttach[index];
	}
	public FormFile getAttach(int index) {
		return attach[index];
	}
	public void setAttach(int index,FormFile value) {
		this.attach[index] = value;
	}
	public void setGenAttach(int index,FormFile value) {
		this.genAttach[index] = value;
	}
	public void setUpdateGenAttach(int index,FormFile value) {
		this.updateGenAttach[index] = value;
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
	
	
	
	
	
	
	public long getClaimAmt() {
		return claimAmt;
	}
	public void setClaimAmt(long claimAmt) {
		this.claimAmt = claimAmt;
	}
	public long getClaimNwhAmt() {
		return claimNwhAmt;
	}
	public void setClaimNwhAmt(long claimNwhAmt) {
		this.claimNwhAmt = claimNwhAmt;
	}
	public String getMedcoRemarks() {
		return medcoRemarks;
	}
	public void setMedcoRemarks(String medcoRemarks) {
		this.medcoRemarks = medcoRemarks;
	}
	public String getNamRemarks() {
		return namRemarks;
	}
	public void setNamRemarks(String namRemarks) {
		this.namRemarks = namRemarks;
	}
	public String getExeDisphotoChklst() {
		return exeDisphotoChklst;
	}
	public void setExeDisphotoChklst(String exeDisphotoChklst) {
		this.exeDisphotoChklst = exeDisphotoChklst;
	}
	public String getExeDisphotoremark() {
		return exeDisphotoremark;
	}
	public void setExeDisphotoremark(String exeDisphotoremark) {
		this.exeDisphotoremark = exeDisphotoremark;
	}
	public String getExePatphotoChklst() {
		return exePatphotoChklst;
	}
	public void setExePatphotoChklst(String exePatphotoChklst) {
		this.exePatphotoChklst = exePatphotoChklst;
	}
	public String getExePatphotoRemark() {
		return exePatphotoRemark;
	}
	public void setExePatphotoRemark(String exePatphotoRemark) {
		this.exePatphotoRemark = exePatphotoRemark;
	}
	public String getExeAcqvrifyChklst() {
		return exeAcqvrifyChklst;
	}
	public void setExeAcqvrifyChklst(String exeAcqvrifyChklst) {
		this.exeAcqvrifyChklst = exeAcqvrifyChklst;
	}
	public String getExeAcqverifyRemark() {
		return exeAcqverifyRemark;
	}
	public void setExeAcqverifyRemark(String exeAcqverifyRemark) {
		this.exeAcqverifyRemark = exeAcqverifyRemark;
	}
	public String getExeMedverifyChklst() {
		return exeMedverifyChklst;
	}
	public void setExeMedverifyChklst(String exeMedverifyChklst) {
		this.exeMedverifyChklst = exeMedverifyChklst;
	}
	public String getExeMedVerifyRemark() {
		return exeMedVerifyRemark;
	}
	public void setExeMedVerifyRemark(String exeMedVerifyRemark) {
		this.exeMedVerifyRemark = exeMedVerifyRemark;
	}
	public String getExeQuantverifyRemark() {
		return exeQuantverifyRemark;
	}
	public void setExeQuantverifyRemark(String exeQuantverifyRemark) {
		this.exeQuantverifyRemark = exeQuantverifyRemark;
	}
	public String getExeQuantverifyChklst() {
		return exeQuantverifyChklst;
	}
	public void setExeQuantverifyChklst(String exeQuantverifyChklst) {
		this.exeQuantverifyChklst = exeQuantverifyChklst;
	}
	public String getExereprtcheckChklst() {
		return exereprtcheckChklst;
	}
	public void setExereprtcheckChklst(String exereprtcheckChklst) {
		this.exereprtcheckChklst = exereprtcheckChklst;
	}
	public String getExeReprtcheckRemark() {
		return exeReprtcheckRemark;
	}
	public void setExeReprtcheckRemark(String exeReprtcheckRemark) {
		this.exeReprtcheckRemark = exeReprtcheckRemark;
	}
	public String getFtdRemarkvrifedChklst() {
		return ftdRemarkvrifedChklst;
	}
	public void setFtdRemarkvrifedChklst(String ftdRemarkvrifedChklst) {
		this.ftdRemarkvrifedChklst = ftdRemarkvrifedChklst;
	}
	public String getFtdRemarkvrifedRemark() {
		return ftdRemarkvrifedRemark;
	}
	public void setFtdRemarkvrifedRemark(String ftdRemarkvrifedRemark) {
		this.ftdRemarkvrifedRemark = ftdRemarkvrifedRemark;
	}
	public String getFtdBeneficiryChklst() {
		return ftdBeneficiryChklst;
	}
	public void setFtdBeneficiryChklst(String ftdBeneficiryChklst) {
		this.ftdBeneficiryChklst = ftdBeneficiryChklst;
	}
	public String getFtdBeneficiryRemark() {
		return ftdBeneficiryRemark;
	}
	public void setFtdBeneficiryRemark(String ftdBeneficiryRemark) {
		this.ftdBeneficiryRemark = ftdBeneficiryRemark;
	}
	public String getFtdRmks() {
		return ftdRmks;
	}
	public void setFtdRmks(String ftdRmks) {
		this.ftdRmks = ftdRmks;
	}
	public String getShowNam() {
		return showNam;
	}
	public void setShowNam(String showNam) {
		this.showNam = showNam;
	}
	public String getShowFcx() {
		return showFcx;
	}
	public void setShowFcx(String showFcx) {
		this.showFcx = showFcx;
	}
	public String getShowFtd() {
		return showFtd;
	}
	public void setShowFtd(String showFtd) {
		this.showFtd = showFtd;
	}
	public String getFollowUpId() {
		return followUpId;
	}
	public void setFollowUpId(String followUpId) {
		this.followUpId = followUpId;
	}
	public String getFollowUpDt() {
		return followUpDt;
	}
	public void setFollowUpDt(String followUpDt) {
		this.followUpDt = followUpDt;
	}
	
	
	public long getPackageAmt() {
		return packageAmt;
	}
	public void setPackageAmt(long packageAmt) {
		this.packageAmt = packageAmt;
	}
	public List<ClaimsFlowVO> getLstworkFlow() {
		return lstworkFlow;
	}
	public void setLstworkFlow(List<ClaimsFlowVO> lstworkFlow) {
		this.lstworkFlow = lstworkFlow;
	}
	

	public String getFcxRemark() {
		return fcxRemark;
	}
	public void setFcxRemark(String fcxRemark) {
		this.fcxRemark = fcxRemark;
	}
	public String getChRemark() {
		return chRemark;
	}
	public void setChRemark(String chRemark) {
		this.chRemark = chRemark;
	}
	public String getShowCh() {
		return showCh;
	}
	public void setShowCh(String showCh) {
		this.showCh = showCh;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getAcoRemark() {
		return acoRemark;
	}
	public void setAcoRemark(String acoRemark) {
		this.acoRemark = acoRemark;
	}
	public String getShowAco() {
		return showAco;
	}
	
	
	public long getClaimNamAmt() {
		return claimNamAmt;
	}
	public void setClaimNamAmt(long claimNamAmt) {
		this.claimNamAmt = claimNamAmt;
	}
	public long getClaimFcxAmt() {
		return claimFcxAmt;
	}
	public void setClaimFcxAmt(long claimFcxAmt) {
		this.claimFcxAmt = claimFcxAmt;
	}
	public long getClaimFtdAmt() {
		return claimFtdAmt;
	}
	public void setClaimFtdAmt(long claimFtdAmt) {
		this.claimFtdAmt = claimFtdAmt;
	}
	public long getClaimChAmt() {
		return claimChAmt;
	}
	public void setClaimChAmt(long claimChAmt) {
		this.claimChAmt = claimChAmt;
	}
	public long getAcoAprAmt() {
		return acoAprAmt;
	}
	public void setAcoAprAmt(long acoAprAmt) {
		this.acoAprAmt = acoAprAmt;
	}
	public void setShowAco(String showAco) {
		this.showAco = showAco;
	}
	public List<LabelBean> getCasesForPaymentList() {
		return casesForPaymentList;
	}
	public void setCasesForPaymentList(List<LabelBean> casesForPaymentList) {
		this.casesForPaymentList = casesForPaymentList;
	}
	public FormFile[] getUpdateGenAttach() {
		return updateGenAttach;
	}
	public List<LabelBean> getButtonList() {
		return buttonList;
	}
	public void setButtonList(List<LabelBean> buttonList) {
		this.buttonList = buttonList;
	}
	
	public String getShowMedco() {
		return showMedco;
	}
	public void setShowMedco(String showMedco) {
		this.showMedco = showMedco;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getClaimSubmit() {
		return claimSubmit;
	}
	public void setClaimSubmit(String claimSubmit) {
		this.claimSubmit = claimSubmit;
	}
	public String getAddrEnable() {
		return addrEnable;
	}
	public void setAddrEnable(String addrEnable) {
		this.addrEnable = addrEnable;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getExpiryDt() {
		return expiryDt;
	}
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}
	public String getPackageDrugAmt() {
		return packageDrugAmt;
	}
	public void setPackageDrugAmt(String packageDrugAmt) {
		this.packageDrugAmt = packageDrugAmt;
	}
	public String getCaseApprovalFlag() {
		return caseApprovalFlag;
	}
	public void setCaseApprovalFlag(String caseApprovalFlag) {
		this.caseApprovalFlag = caseApprovalFlag;
	}
	public String getChronicStatus() {
		return chronicStatus;
	}
	public void setChronicStatus(String chronicStatus) {
		this.chronicStatus = chronicStatus;
	}
	public String getRegCaseFlg() {
		return regCaseFlg;
	}
	public void setRegCaseFlg(String regCaseFlg) {
		this.regCaseFlg = regCaseFlg;
	}
	public String getAddPckgFlg() {
		return addPckgFlg;
	}
	public void setAddPckgFlg(String addPckgFlg) {
		this.addPckgFlg = addPckgFlg;
	}
	public String getCeoRemark() {
		return ceoRemark;
	}
	public void setCeoRemark(String ceoRemark) {
		this.ceoRemark = ceoRemark;
	}
	public String getChronicPackageCode() {
		return chronicPackageCode;
	}
	public void setChronicPackageCode(String chronicPackageCode) {
		this.chronicPackageCode = chronicPackageCode;
	}
	public String getChronicCatCode() {
		return chronicCatCode;
	}
	public void setChronicCatCode(String chronicCatCode) {
		this.chronicCatCode = chronicCatCode;
	}
	public String getHospCode() {
		return hospCode;
	}
	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}
	
	public String[] getChronicPkgList() {
		return chronicPkgList;
	}
	public void setChronicPkgList(String[] chronicPkgList) {
		this.chronicPkgList = chronicPkgList;
	}
	public int getChronicPkgCount() {
		return chronicPkgCount;
	}
	public void setChronicPkgCount(int chronicPkgCount) {
		this.chronicPkgCount = chronicPkgCount;
	}
List<ChronicOPVO> opPkgList;
public List<ChronicOPVO> getOpPkgList() {
	return opPkgList;
}
public void setOpPkgList(List<ChronicOPVO> opPkgList) {
	this.opPkgList = opPkgList;
}
public String getCheckType() {
	return checkType;
}
public void setCheckType(String checkType) {
	this.checkType = checkType;
}
public String getInvestOthers() {
	return investOthers;
}
public void setInvestOthers(String investOthers) {
	this.investOthers = investOthers;
}
public String getOtherInvName() {
	return otherInvName;
}
public void setOtherInvName(String otherInvName) {
	this.otherInvName = otherInvName;
}
public int getOtherInvestCount() {
	return otherInvestCount;
}
public void setOtherInvestCount(int otherInvestCount) {
	this.otherInvestCount = otherInvestCount;
}
public String getHospScheme() {
	return hospScheme;
}
public void setHospScheme(String hospScheme) {
	this.hospScheme = hospScheme;
}
public String getStateType() {
	return stateType;
}
public void setStateType(String stateType) {
	this.stateType = stateType;
}


	
	
}
