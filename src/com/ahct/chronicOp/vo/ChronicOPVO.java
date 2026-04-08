package com.ahct.chronicOp.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.patient.vo.AttachmentVO;
import com.ahct.patient.vo.CaseTherapyVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;

public class ChronicOPVO {
	
	
	private String chronicID;
	private String chronicSubID;
	public String getChronicSubID() {
		return chronicSubID;
	}
	public void setChronicSubID(String chronicSubID) {
		this.chronicSubID = chronicSubID;
	}

	private String name;
	private String employeeNo;
	private String district;

	private String registrationDate;
	private BigDecimal patAge;
	private String csvFlag;



	private String chronicNo;
	public String getChronicNo() {
		return chronicNo;
	}
	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}

	private String msg;
	private String empCode;
	private String cardNo;	
	private String patientId;
	private String processInstanceId;
	private String rationCard;
	private String firstName;
	private String middleName;
	private String lastName;
	private String eMailId;
	private String addr1;
	private String addr2;
	private String addr3;
	private String age;
	private String gender;
	private String relation;
	private String relOthers;
	private String state;
	private String city;
	private String districtCode;
	private String mdl_mpl;
	private String pincode;
	private String religion;
	private String castOthers;
	private String dateOfBirth;
	private String maritalStatus;
	private String occupationCd;
	private String occOthers;
	private String villageCode;
	private String mandalCode;
	private String hamletCode;
	private String hofName;
	private String slab;
	private String crtUsr;
	private String crtDt;
	private String lstUpdUsr;
	private String lstUpdDt;
	private String cardType;
	private String child_yn;
	private String patient_ipop;
	private String patient_ipop_no;
	private String srcRegistration;
	private String refCardNo;
	private String refHospNo;
	private String phcCode;
	private String src_hc_id;
	private String src_phc_id;
	private String familyHead;
	private String caste;
	private String contactNo;
	private String regHospId;
	private String regHospDt;
	private String regHCDt;
	private String ccUsr;
	private String phaseId;
	private String cid;
	private String renewal;
	private String schemeId;
	private int totRowCount;
	private List<PatientVO> registeredPatList;
	private String ageMonths;
	private String ageDays;
	private String non_nwh_name;
	private String suggestedSurgery;
	private String suggestedDissubId;
	private String non_nwh_mit_ph;
	private String nwh_yn;
	private String attachment;
	private String cmo_id_type;
	private String cmo_id;
	private String reportDt;
	private String cardIssueDt;
	private String cardAttachment;
	private String permAddr1;
	private String permAddr2;
	private String c_state;
	private String c_district_code;
	private String c_mdl_mpl;
	private String c_mandal_code;
	private String c_village_code;
	private String c_hamlet_code;
	private String c_phc_code;
	private String c_pin_code;
	private String c_relationshipof_code;
	private String screenedHosp;
	private String oldAttachName;
	private String oldCardAttachName;

	private String intimationId;
	private String oldPhaseRenewal;
	private String registeredUserId;
	private String oldJournRenewal;
	private String biometricVal;
	private String exemptionType;
	private String photo;
	private String employeeRenewal;
	private String pensionerRenewal;
	//case registering properties
	private List<AttachmentVO> attachmentsList=null;
	private List<AttachmentVO> genAttachmentsList=null;
	private List<AttachmentVO> updGenAttachmentsList=null;
	private String langId;
	private String userRole;
	private String complaints;
	private String[] patientComplaint;
	private String presentHistory;
	private String[] personalHistory;
	private String[] pastHistory;
	private String[] examinationFnd;
	private String[] familyHistory;
	private String provisionalDiagnosis;
	private String diagnosedBy;
	private String doctorName;
	private String otherDocName;
	private String docRegNo;
	private String docQual;
	private String docMobileNo;
	private String patientType;
	private String ipNo;
	private String admissionType;
	private String ipDate;
	private String finalDiagnosis;
	private String prescription;
	private String opNo;
	private String opDate;
	private String opRemarks;
	private String ipCaseRemarks;
	private String caseId;
	private String caseSurgId;
	//end of case properties
	//start of telephonic reg
	private String teleCallerDesgn;
	private String teleCallerName;
	private String telePhoneNo;
	private String teleDocName;
	private String teleDocDesgn;
	private String teleDocPhoneNo;
	private String teleDocremarks;
	private String surgeryName;
	private String diagnosisType;
	private String mainCatName;
	private String catName;
	private String subCatName;
	private String diseaseName;
	private String disAnatomicalName;
	private String ICDcatName;
	private String ICDsubCatName;
	private String ICDprocName;
	private String asriProcCode;
	private String indication;
	private String telephonicId;
	//end
	private String personalHistVal;
	private String examFndsVal;
	//for ehfCasetherapy
	private String therapyCatId;
	private String mainSymptomCode;
	private String symptomCode;
	private String asriCatCode;
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String asriDrugCode;
	private String route;
	private String strength;
	private String dosage;
	private String medicationPeriod;
	private String pastHistryOthr;
	private String pastHistryCancers;
	private String pastHistryEndDis;
	private String pastHistrySurg;
	private String familyHistoryOthr;
	private Long caseTherapyId;
	private Long caseTherInvestSeqId;
	private String caseProcCodes;
	private List<CaseTherapyVO> caseTherapy;
	private List<DrugsVO> drugs;
	private List<LabelBean> symptoms;
	private String hospitalCode;
	private String doctorType;
	private String chronicCaseId;
	private String opCatCode;
	private String opPkgCode;
	private String opIcdCode;
	private String smsSerialNo;
	private String saveFlag;
	private String legalCase;
	private String legalCaseNo;
	private String policeStatName;
	private String caseRegDate;
	private String regState;
    private Number COUNT=0;
    private String cochlearYN;
    private String opCatName;
    private String opPackageName;
    private String opIcdName;
    private String allergy;
    private String habbits;
    private String VALID;
    private String drugAmt;
    private Date caseRegnDt;

    private long PKGAMOUNT;
   
    private String actionDone;
    private String remarks;
    private String chronicStatus;
    private String userGroup;
    private long claimAmt;
    
    private String EMPLOYEENO;
    private Date date;
    private String deptHod;
    private String postDist;
    private String ddoOffUnit;
    private String postDdoCode;
    
    private String exeDisphotoChklst;
    private String exePatphotoChklst;
    private String exeDisphotoremark;
    private String exeAcqvrifyChklst;
    private String exeMedverifyChklst;
    private String exeQuantverifyChklst;
    private String exeAcqverifyRemark;
    private String exeMedVerifyRemark;
    private String exeQuantverifyRemark;
    private String exePatphotoRemark;
    private String exereprtcheckChklst;
    private String ftdRemarkvrifedChklst;
    private String ftdBeneficiryChklst;
    private String exeReprtcheckRemark;
    private String ftdRemarkvrifedRemark;
    private String ftdBeneficiryRemark;
    private String coexFlag;
    private String cotdFlag;
    private Number DIFF;
 
    
    
    private String benfActName;
    private String benficiaryId;
    private String benfAddress;
    private String bankName;
    private String bankBranch;
    private String accountNum;
    private String bankId;
    private String ifscCode;
    private String bankCategory;
    private String actOrder;
    
    private String consultAmt;
    private String investAmt;
    
    private String  startIndex;
    private String  rowsPerPage; 
    private List<ChronicOPVO> casesLst;
    private long count;
    
    private String fromDt;
    private String toDate;
    private String caseApprovalFlag;
    private String regCaseFlg;
    private String  regnDt;
    private String pendingFlag;
    private String userId;
    
    private String claimAmount;
    private Date claimSubDt;
    private String clmSubDt;
    
    private String ID;
    private String VALUE;
    
    private String[] chronicPkgList;
	private int chronicPkgCount;
	
	public String PACKAGECODE;
	public String ICDCATCODE;
	public String OPPKGNAME;
	public String ICDCATNAME;
	
    
  
	

  
    

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

	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getRationCard() {
		return rationCard;
	}
	public void setRationCard(String rationCard) {
		this.rationCard = rationCard;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMailId() {
		return eMailId;
	}
	public void seteMailId(String eMailId) {
		this.eMailId = eMailId;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getRelOthers() {
		return relOthers;
	}
	public void setRelOthers(String relOthers) {
		this.relOthers = relOthers;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getMdl_mpl() {
		return mdl_mpl;
	}
	public void setMdl_mpl(String mdl_mpl) {
		this.mdl_mpl = mdl_mpl;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCastOthers() {
		return castOthers;
	}
	public void setCastOthers(String castOthers) {
		this.castOthers = castOthers;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getOccupationCd() {
		return occupationCd;
	}
	public void setOccupationCd(String occupationCd) {
		this.occupationCd = occupationCd;
	}
	public String getOccOthers() {
		return occOthers;
	}
	public void setOccOthers(String occOthers) {
		this.occOthers = occOthers;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}
	public String getHamletCode() {
		return hamletCode;
	}
	public void setHamletCode(String hamletCode) {
		this.hamletCode = hamletCode;
	}
	public String getHofName() {
		return hofName;
	}
	public void setHofName(String hofName) {
		this.hofName = hofName;
	}
	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	public String getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(String lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getChild_yn() {
		return child_yn;
	}
	public void setChild_yn(String child_yn) {
		this.child_yn = child_yn;
	}
	public String getPatient_ipop() {
		return patient_ipop;
	}
	public void setPatient_ipop(String patient_ipop) {
		this.patient_ipop = patient_ipop;
	}
	public String getPatient_ipop_no() {
		return patient_ipop_no;
	}
	public void setPatient_ipop_no(String patient_ipop_no) {
		this.patient_ipop_no = patient_ipop_no;
	}
	public String getSrcRegistration() {
		return srcRegistration;
	}
	public void setSrcRegistration(String srcRegistration) {
		this.srcRegistration = srcRegistration;
	}
	public String getRefCardNo() {
		return refCardNo;
	}
	public void setRefCardNo(String refCardNo) {
		this.refCardNo = refCardNo;
	}
	public String getRefHospNo() {
		return refHospNo;
	}
	public void setRefHospNo(String refHospNo) {
		this.refHospNo = refHospNo;
	}
	public String getPhcCode() {
		return phcCode;
	}
	public void setPhcCode(String phcCode) {
		this.phcCode = phcCode;
	}
	public String getSrc_hc_id() {
		return src_hc_id;
	}
	public void setSrc_hc_id(String src_hc_id) {
		this.src_hc_id = src_hc_id;
	}
	public String getSrc_phc_id() {
		return src_phc_id;
	}
	public void setSrc_phc_id(String src_phc_id) {
		this.src_phc_id = src_phc_id;
	}
	public String getFamilyHead() {
		return familyHead;
	}
	public void setFamilyHead(String familyHead) {
		this.familyHead = familyHead;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getRegHospId() {
		return regHospId;
	}
	public void setRegHospId(String regHospId) {
		this.regHospId = regHospId;
	}
	public String getRegHospDt() {
		return regHospDt;
	}
	public void setRegHospDt(String regHospDt) {
		this.regHospDt = regHospDt;
	}
	public String getRegHCDt() {
		return regHCDt;
	}
	public void setRegHCDt(String regHCDt) {
		this.regHCDt = regHCDt;
	}
	public String getCcUsr() {
		return ccUsr;
	}
	public void setCcUsr(String ccUsr) {
		this.ccUsr = ccUsr;
	}
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getRenewal() {
		return renewal;
	}
	public void setRenewal(String renewal) {
		this.renewal = renewal;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public int getTotRowCount() {
		return totRowCount;
	}
	public void setTotRowCount(int totRowCount) {
		this.totRowCount = totRowCount;
	}
	public List<PatientVO> getRegisteredPatList() {
		return registeredPatList;
	}
	public void setRegisteredPatList(List<PatientVO> registeredPatList) {
		this.registeredPatList = registeredPatList;
	}
	public String getAgeMonths() {
		return ageMonths;
	}
	public void setAgeMonths(String ageMonths) {
		this.ageMonths = ageMonths;
	}
	public String getAgeDays() {
		return ageDays;
	}
	public void setAgeDays(String ageDays) {
		this.ageDays = ageDays;
	}
	public String getNon_nwh_name() {
		return non_nwh_name;
	}
	public void setNon_nwh_name(String non_nwh_name) {
		this.non_nwh_name = non_nwh_name;
	}
	public String getSuggestedSurgery() {
		return suggestedSurgery;
	}
	public void setSuggestedSurgery(String suggestedSurgery) {
		this.suggestedSurgery = suggestedSurgery;
	}
	public String getSuggestedDissubId() {
		return suggestedDissubId;
	}
	public void setSuggestedDissubId(String suggestedDissubId) {
		this.suggestedDissubId = suggestedDissubId;
	}
	public String getNon_nwh_mit_ph() {
		return non_nwh_mit_ph;
	}
	public void setNon_nwh_mit_ph(String non_nwh_mit_ph) {
		this.non_nwh_mit_ph = non_nwh_mit_ph;
	}
	public String getNwh_yn() {
		return nwh_yn;
	}
	public void setNwh_yn(String nwh_yn) {
		this.nwh_yn = nwh_yn;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getCmo_id_type() {
		return cmo_id_type;
	}
	public void setCmo_id_type(String cmo_id_type) {
		this.cmo_id_type = cmo_id_type;
	}
	public String getCmo_id() {
		return cmo_id;
	}
	public void setCmo_id(String cmo_id) {
		this.cmo_id = cmo_id;
	}
	public String getReportDt() {
		return reportDt;
	}
	public void setReportDt(String reportDt) {
		this.reportDt = reportDt;
	}
	public String getCardIssueDt() {
		return cardIssueDt;
	}
	public void setCardIssueDt(String cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}
	public String getCardAttachment() {
		return cardAttachment;
	}
	public void setCardAttachment(String cardAttachment) {
		this.cardAttachment = cardAttachment;
	}
	public String getPermAddr1() {
		return permAddr1;
	}
	public void setPermAddr1(String permAddr1) {
		this.permAddr1 = permAddr1;
	}
	public String getPermAddr2() {
		return permAddr2;
	}
	public void setPermAddr2(String permAddr2) {
		this.permAddr2 = permAddr2;
	}
	public String getC_state() {
		return c_state;
	}
	public void setC_state(String c_state) {
		this.c_state = c_state;
	}
	public String getC_district_code() {
		return c_district_code;
	}
	public void setC_district_code(String c_district_code) {
		this.c_district_code = c_district_code;
	}
	public String getC_mdl_mpl() {
		return c_mdl_mpl;
	}
	public void setC_mdl_mpl(String c_mdl_mpl) {
		this.c_mdl_mpl = c_mdl_mpl;
	}
	public String getC_mandal_code() {
		return c_mandal_code;
	}
	public void setC_mandal_code(String c_mandal_code) {
		this.c_mandal_code = c_mandal_code;
	}
	public String getC_village_code() {
		return c_village_code;
	}
	public void setC_village_code(String c_village_code) {
		this.c_village_code = c_village_code;
	}
	public String getC_hamlet_code() {
		return c_hamlet_code;
	}
	public void setC_hamlet_code(String c_hamlet_code) {
		this.c_hamlet_code = c_hamlet_code;
	}
	public String getC_phc_code() {
		return c_phc_code;
	}
	public void setC_phc_code(String c_phc_code) {
		this.c_phc_code = c_phc_code;
	}
	public String getC_pin_code() {
		return c_pin_code;
	}
	public void setC_pin_code(String c_pin_code) {
		this.c_pin_code = c_pin_code;
	}
	public String getC_relationshipof_code() {
		return c_relationshipof_code;
	}
	public void setC_relationshipof_code(String c_relationshipof_code) {
		this.c_relationshipof_code = c_relationshipof_code;
	}
	public String getScreenedHosp() {
		return screenedHosp;
	}
	public void setScreenedHosp(String screenedHosp) {
		this.screenedHosp = screenedHosp;
	}
	public String getOldAttachName() {
		return oldAttachName;
	}
	public void setOldAttachName(String oldAttachName) {
		this.oldAttachName = oldAttachName;
	}
	public String getOldCardAttachName() {
		return oldCardAttachName;
	}
	public void setOldCardAttachName(String oldCardAttachName) {
		this.oldCardAttachName = oldCardAttachName;
	}
	public String getIntimationId() {
		return intimationId;
	}
	public void setIntimationId(String intimationId) {
		this.intimationId = intimationId;
	}
	public String getOldPhaseRenewal() {
		return oldPhaseRenewal;
	}
	public void setOldPhaseRenewal(String oldPhaseRenewal) {
		this.oldPhaseRenewal = oldPhaseRenewal;
	}
	public String getRegisteredUserId() {
		return registeredUserId;
	}
	public void setRegisteredUserId(String registeredUserId) {
		this.registeredUserId = registeredUserId;
	}
	public String getOldJournRenewal() {
		return oldJournRenewal;
	}
	public void setOldJournRenewal(String oldJournRenewal) {
		this.oldJournRenewal = oldJournRenewal;
	}
	public String getBiometricVal() {
		return biometricVal;
	}
	public void setBiometricVal(String biometricVal) {
		this.biometricVal = biometricVal;
	}
	public String getExemptionType() {
		return exemptionType;
	}
	public void setExemptionType(String exemptionType) {
		this.exemptionType = exemptionType;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getEmployeeRenewal() {
		return employeeRenewal;
	}
	public void setEmployeeRenewal(String employeeRenewal) {
		this.employeeRenewal = employeeRenewal;
	}
	public String getPensionerRenewal() {
		return pensionerRenewal;
	}
	public void setPensionerRenewal(String pensionerRenewal) {
		this.pensionerRenewal = pensionerRenewal;
	}
	public List<AttachmentVO> getAttachmentsList() {
		return attachmentsList;
	}
	public void setAttachmentsList(List<AttachmentVO> attachmentsList) {
		this.attachmentsList = attachmentsList;
	}
	public List<AttachmentVO> getGenAttachmentsList() {
		return genAttachmentsList;
	}
	public void setGenAttachmentsList(List<AttachmentVO> genAttachmentsList) {
		this.genAttachmentsList = genAttachmentsList;
	}
	public List<AttachmentVO> getUpdGenAttachmentsList() {
		return updGenAttachmentsList;
	}
	public void setUpdGenAttachmentsList(List<AttachmentVO> updGenAttachmentsList) {
		this.updGenAttachmentsList = updGenAttachmentsList;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
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
	public String getPresentHistory() {
		return presentHistory;
	}
	public void setPresentHistory(String presentHistory) {
		this.presentHistory = presentHistory;
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
	public String[] getExaminationFnd() {
		return examinationFnd;
	}
	public void setExaminationFnd(String[] examinationFnd) {
		this.examinationFnd = examinationFnd;
	}
	public String[] getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String[] familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String getProvisionalDiagnosis() {
		return provisionalDiagnosis;
	}
	public void setProvisionalDiagnosis(String provisionalDiagnosis) {
		this.provisionalDiagnosis = provisionalDiagnosis;
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
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
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
	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}
	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
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
	public String getIpCaseRemarks() {
		return ipCaseRemarks;
	}
	public void setIpCaseRemarks(String ipCaseRemarks) {
		this.ipCaseRemarks = ipCaseRemarks;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseSurgId() {
		return caseSurgId;
	}
	public void setCaseSurgId(String caseSurgId) {
		this.caseSurgId = caseSurgId;
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
	public String getTeleDocremarks() {
		return teleDocremarks;
	}
	public void setTeleDocremarks(String teleDocremarks) {
		this.teleDocremarks = teleDocremarks;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public String getDiagnosisType() {
		return diagnosisType;
	}
	public void setDiagnosisType(String diagnosisType) {
		this.diagnosisType = diagnosisType;
	}
	public String getMainCatName() {
		return mainCatName;
	}
	public void setMainCatName(String mainCatName) {
		this.mainCatName = mainCatName;
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
	public String getICDcatName() {
		return ICDcatName;
	}
	public void setICDcatName(String iCDcatName) {
		ICDcatName = iCDcatName;
	}
	public String getICDsubCatName() {
		return ICDsubCatName;
	}
	public void setICDsubCatName(String iCDsubCatName) {
		ICDsubCatName = iCDsubCatName;
	}
	public String getICDprocName() {
		return ICDprocName;
	}
	public void setICDprocName(String iCDprocName) {
		ICDprocName = iCDprocName;
	}
	public String getAsriProcCode() {
		return asriProcCode;
	}
	public void setAsriProcCode(String asriProcCode) {
		this.asriProcCode = asriProcCode;
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
	public String getTherapyCatId() {
		return therapyCatId;
	}
	public void setTherapyCatId(String therapyCatId) {
		this.therapyCatId = therapyCatId;
	}
	public String getMainSymptomCode() {
		return mainSymptomCode;
	}
	public void setMainSymptomCode(String mainSymptomCode) {
		this.mainSymptomCode = mainSymptomCode;
	}
	public String getSymptomCode() {
		return symptomCode;
	}
	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
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
	public String getAsriDrugCode() {
		return asriDrugCode;
	}
	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
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
	public String getMedicationPeriod() {
		return medicationPeriod;
	}
	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
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
	public Long getCaseTherapyId() {
		return caseTherapyId;
	}
	public void setCaseTherapyId(Long caseTherapyId) {
		this.caseTherapyId = caseTherapyId;
	}
	public Long getCaseTherInvestSeqId() {
		return caseTherInvestSeqId;
	}
	public void setCaseTherInvestSeqId(Long caseTherInvestSeqId) {
		this.caseTherInvestSeqId = caseTherInvestSeqId;
	}
	public String getCaseProcCodes() {
		return caseProcCodes;
	}
	public void setCaseProcCodes(String caseProcCodes) {
		this.caseProcCodes = caseProcCodes;
	}
	public List<CaseTherapyVO> getCaseTherapy() {
		return caseTherapy;
	}
	public void setCaseTherapy(List<CaseTherapyVO> caseTherapy) {
		this.caseTherapy = caseTherapy;
	}
	public List<DrugsVO> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<DrugsVO> drugs) {
		this.drugs = drugs;
	}
	public List<LabelBean> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(List<LabelBean> symptoms) {
		this.symptoms = symptoms;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDoctorType() {
		return doctorType;
	}
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
	public String getChronicCaseId() {
		return chronicCaseId;
	}
	public void setChronicCaseId(String chronicCaseId) {
		this.chronicCaseId = chronicCaseId;
	}
	public String getOpCatCode() {
		return opCatCode;
	}
	public void setOpCatCode(String opCatCode) {
		this.opCatCode = opCatCode;
	}
	public String getOpPkgCode() {
		return opPkgCode;
	}
	public void setOpPkgCode(String opPkgCode) {
		this.opPkgCode = opPkgCode;
	}
	public String getOpIcdCode() {
		return opIcdCode;
	}
	public void setOpIcdCode(String opIcdCode) {
		this.opIcdCode = opIcdCode;
	}
	public String getSmsSerialNo() {
		return smsSerialNo;
	}
	public void setSmsSerialNo(String smsSerialNo) {
		this.smsSerialNo = smsSerialNo;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
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
	public String getCaseRegDate() {
		return caseRegDate;
	}
	public void setCaseRegDate(String caseRegDate) {
		this.caseRegDate = caseRegDate;
	}
	public String getRegState() {
		return regState;
	}
	public void setRegState(String regState) {
		this.regState = regState;
	}
	public Number getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}
	public String getCochlearYN() {
		return cochlearYN;
	}
	public void setCochlearYN(String cochlearYN) {
		this.cochlearYN = cochlearYN;
	}
	public void setAge(BigDecimal age2) {
		// TODO Auto-generated method stub
		
	}

	public String getOpCatName() {
		return opCatName;
	}
	public void setOpCatName(String opCatName) {
		this.opCatName = opCatName;
	}
	public String getOpPackageName() {
		return opPackageName;
	}
	public void setOpPackageName(String opPackageName) {
		this.opPackageName = opPackageName;
	}
	public String getOpIcdName() {
		return opIcdName;
	}
	public void setOpIcdName(String opIcdName) {
		this.opIcdName = opIcdName;
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


	public String getActionDone() {
		return actionDone;
	}
	public void setActionDone(String actionDone) {
		this.actionDone = actionDone;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getChronicStatus() {
		return chronicStatus;
	}
	public void setChronicStatus(String chronicStatus) {
		this.chronicStatus = chronicStatus;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	
	public long getPKGAMOUNT() {
		return PKGAMOUNT;
	}
	public void setPKGAMOUNT(long pKGAMOUNT) {
		PKGAMOUNT = pKGAMOUNT;
	}
	public long getClaimAmt() {
		return claimAmt;
	}
	public void setClaimAmt(long claimAmt) {
		this.claimAmt = claimAmt;
	}
	public String getEMPLOYEENO() {
		return EMPLOYEENO;
	}
	public void setEMPLOYEENO(String eMPLOYEENO) {
		EMPLOYEENO = eMPLOYEENO;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getDdoOffUnit() {
		return ddoOffUnit;
	}
	public void setDdoOffUnit(String ddoOffUnit) {
		this.ddoOffUnit = ddoOffUnit;
	}
	public String getPostDdoCode() {
		return postDdoCode;
	}
	public void setPostDdoCode(String postDdoCode) {
		this.postDdoCode = postDdoCode;
	}
	public String getExeDisphotoChklst() {
		return exeDisphotoChklst;
	}
	public void setExeDisphotoChklst(String exeDisphotoChklst) {
		this.exeDisphotoChklst = exeDisphotoChklst;
	}
	public String getExePatphotoChklst() {
		return exePatphotoChklst;
	}
	public void setExePatphotoChklst(String exePatphotoChklst) {
		this.exePatphotoChklst = exePatphotoChklst;
	}
	public String getExeDisphotoremark() {
		return exeDisphotoremark;
	}
	public void setExeDisphotoremark(String exeDisphotoremark) {
		this.exeDisphotoremark = exeDisphotoremark;
	}
	public String getExeAcqvrifyChklst() {
		return exeAcqvrifyChklst;
	}
	public void setExeAcqvrifyChklst(String exeAcqvrifyChklst) {
		this.exeAcqvrifyChklst = exeAcqvrifyChklst;
	}
	public String getExeMedverifyChklst() {
		return exeMedverifyChklst;
	}
	public void setExeMedverifyChklst(String exeMedverifyChklst) {
		this.exeMedverifyChklst = exeMedverifyChklst;
	}
	public String getExeQuantverifyChklst() {
		return exeQuantverifyChklst;
	}
	public void setExeQuantverifyChklst(String exeQuantverifyChklst) {
		this.exeQuantverifyChklst = exeQuantverifyChklst;
	}
	public String getExeAcqverifyRemark() {
		return exeAcqverifyRemark;
	}
	public void setExeAcqverifyRemark(String exeAcqverifyRemark) {
		this.exeAcqverifyRemark = exeAcqverifyRemark;
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
	public String getExePatphotoRemark() {
		return exePatphotoRemark;
	}
	public void setExePatphotoRemark(String exePatphotoRemark) {
		this.exePatphotoRemark = exePatphotoRemark;
	}
	public String getExereprtcheckChklst() {
		return exereprtcheckChklst;
	}
	public void setExereprtcheckChklst(String exereprtcheckChklst) {
		this.exereprtcheckChklst = exereprtcheckChklst;
	}
	public String getFtdRemarkvrifedChklst() {
		return ftdRemarkvrifedChklst;
	}
	public void setFtdRemarkvrifedChklst(String ftdRemarkvrifedChklst) {
		this.ftdRemarkvrifedChklst = ftdRemarkvrifedChklst;
	}
	public String getFtdBeneficiryChklst() {
		return ftdBeneficiryChklst;
	}
	public void setFtdBeneficiryChklst(String ftdBeneficiryChklst) {
		this.ftdBeneficiryChklst = ftdBeneficiryChklst;
	}
	public String getCoexFlag() {
		return coexFlag;
	}
	public void setCoexFlag(String coexFlag) {
		this.coexFlag = coexFlag;
	}
	public String getCotdFlag() {
		return cotdFlag;
	}
	public void setCotdFlag(String cotdFlag) {
		this.cotdFlag = cotdFlag;
	}
	public String getExeReprtcheckRemark() {
		return exeReprtcheckRemark;
	}
	public void setExeReprtcheckRemark(String exeReprtcheckRemark) {
		this.exeReprtcheckRemark = exeReprtcheckRemark;
	}
	public String getFtdRemarkvrifedRemark() {
		return ftdRemarkvrifedRemark;
	}
	public void setFtdRemarkvrifedRemark(String ftdRemarkvrifedRemark) {
		this.ftdRemarkvrifedRemark = ftdRemarkvrifedRemark;
	}
	public String getFtdBeneficiryRemark() {
		return ftdBeneficiryRemark;
	}
	public void setFtdBeneficiryRemark(String ftdBeneficiryRemark) {
		this.ftdBeneficiryRemark = ftdBeneficiryRemark;
	}
	public String getVALID() {
		return VALID;
	}
	public void setVALID(String vALID) {
		VALID = vALID;
	}
	public Number getDIFF() {
		return DIFF;
	}
	public void setDIFF(Number dIFF) {
		DIFF = dIFF;
	}
	public String getDrugAmt() {
		return drugAmt;
	}
	public void setDrugAmt(String drugAmt) {
		this.drugAmt = drugAmt;
	}
	public String getBenfActName() {
		return benfActName;
	}
	public void setBenfActName(String benfActName) {
		this.benfActName = benfActName;
	}
	public String getBenficiaryId() {
		return benficiaryId;
	}
	public void setBenficiaryId(String benficiaryId) {
		this.benficiaryId = benficiaryId;
	}
	public String getBenfAddress() {
		return benfAddress;
	}
	public void setBenfAddress(String benfAddress) {
		this.benfAddress = benfAddress;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankCategory() {
		return bankCategory;
	}
	public void setBankCategory(String bankCategory) {
		this.bankCategory = bankCategory;
	}
	public String getActOrder() {
		return actOrder;
	}
	public void setActOrder(String actOrder) {
		this.actOrder = actOrder;
	}
	public String getConsultAmt() {
		return consultAmt;
	}
	public void setConsultAmt(String consultAmt) {
		this.consultAmt = consultAmt;
	}
	public String getInvestAmt() {
		return investAmt;
	}
	public void setInvestAmt(String investAmt) {
		this.investAmt = investAmt;
	}
	public BigDecimal getPatAge() {
		return patAge;
	}
	public void setPatAge(BigDecimal patAge) {
		this.patAge = patAge;
	}
	public Date getCaseRegnDt() {
		return caseRegnDt;
	}
	public void setCaseRegnDt(Date caseRegnDt) {
		this.caseRegnDt = caseRegnDt;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public List<ChronicOPVO> getCasesLst() {
		return casesLst;
	}
	public void setCasesLst(List<ChronicOPVO> casesLst) {
		this.casesLst = casesLst;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getCsvFlag() {
		return csvFlag;
	}
	public void setCsvFlag(String csvFlag) {
		this.csvFlag = csvFlag;
	}
	public String getFromDt() {
		return fromDt;
	}
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getCaseApprovalFlag() {
		return caseApprovalFlag;
	}
	public void setCaseApprovalFlag(String caseApprovalFlag) {
		this.caseApprovalFlag = caseApprovalFlag;
	}
	public String getRegCaseFlg() {
		return regCaseFlg;
	}
	public void setRegCaseFlg(String regCaseFlg) {
		this.regCaseFlg = regCaseFlg;
	}
	public String getRegnDt() {
		return regnDt;
	}
	public void setRegnDt(String regnDt) {
		this.regnDt = regnDt;
	}
	public String getPendingFlag() {
		return pendingFlag;
	}
	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
	public Date getClaimSubDt() {
		return claimSubDt;
	}
	public void setClaimSubDt(Date claimSubDt) {
		this.claimSubDt = claimSubDt;
	}
	public String getClmSubDt() {
		return clmSubDt;
	}
	public void setClmSubDt(String clmSubDt) {
		this.clmSubDt = clmSubDt;
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
	
	public int getChronicPkgCount() {
		return chronicPkgCount;
	}
	public void setChronicPkgCount(int chronicPkgCount) {
		this.chronicPkgCount = chronicPkgCount;
	}
	public String[] getChronicPkgList() {
		return chronicPkgList;
	}
	public void setChronicPkgList(String[] chronicPkgList) {
		this.chronicPkgList = chronicPkgList;
	}
	public String getPACKAGECODE() {
		return PACKAGECODE;
	}
	public void setPACKAGECODE(String pACKAGECODE) {
		PACKAGECODE = pACKAGECODE;
	}
	public String getICDCATCODE() {
		return ICDCATCODE;
	}
	public void setICDCATCODE(String iCDCATCODE) {
		ICDCATCODE = iCDCATCODE;
	}
	public String getOPPKGNAME() {
		return OPPKGNAME;
	}
	public void setOPPKGNAME(String oPPKGNAME) {
		OPPKGNAME = oPPKGNAME;
	}
	public String getICDCATNAME() {
		return ICDCATNAME;
	}
	public void setICDCATNAME(String iCDCATNAME) {
		ICDCATNAME = iCDCATNAME;
	}
	


	
	
	
	
	

}
