package com.ahct.annualCheckUp.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

import org.apache.struts.upload.FormFile;

import com.ahct.common.vo.LabelBean;
import com.ahct.patient.vo.AttachmentVO;
import com.ahct.patient.vo.CaseTherapyVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;


public class AnnualCheckUpVo 
{
	private String cardNo;
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
	private String houseNo;
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
	private String caste;
	private String addr1;
	private String addr2;
	private String photo;
	private String msg;
	private String disableFlag;
	private String errMsg;
	private String patientId;
	private String processInstanceId;
	private String rationCard;
	private String firstName;
	private String middleName;
	private String lastName;
	private String addr3;
	private String age;
	private String relOthers;
	private String city;
	private String districtCode;
	private String mdl_mpl;
	private String pincode;
	private String religion;
	private String castOthers;
	private String occupationCd;
	private String occOthers;
	private String villageCode;
	private String mandalCode;
	private String hamletCode;
	private String hofName;
	private String crtUsr;
	private String crtDt;
	private String lstUpdUsr;
	private String lstUpdDt;
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
	private String examFindg;
	private String intimationId;
	private String oldPhaseRenewal;
	private String registeredUserId;
	private String oldJournRenewal;
	private String biometricVal;
	private String exemptionType;
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
	private String[] consultRemLst;
	private String[] investRemLst;
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
	private String personalHis;
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
	private String familyHis;
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
	private String patientNo;
	private String patientName;
	private String ahcId;
	private String employeeNo;
	private String registrationDate;
	private String haemoglobin;
    private String dept_Hod;
	private String polymorphs;
	private String lymphocytes;
	private String eosinophils;
    private String post_Dist;
	private String monocytes;
	private String rbc;
	private String tlc;
	private String basophils;
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
    private String ddoOffUnit;
	private String sgot;
	private String sgpt;
	private String sTotalBilirubin;
	private String sDirectBilirubin;
	private String  liverSgot;
	private String  liverSgpt;
	private List<String> lstPerHis;
    private String urineColor;
    private String urineAlbumin;
    private String urineSugar;
    private String urineMicroscopicExam;
    private String abdomenUltrasound;
    private String filePath;
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
    private String personalHist;
	private String status;
	private Date regdt;
	private String postDist;
	private String deptHod;
    private String post_Ddo_Code;
	private String postDdoCode;
	private List<AnnualCheckUpVo> testsList;
	private List<AnnualCheckUpVo> consultList;
	private List<String> personalHistList;
	private String presentIllness;
	private String pastIllness;
	private String pastIllenesCancers;
	private String pastIllenesEndDis;
	private String pastIllenesSurg;
	private String pastIllenesOthr;
	private String pastIllnessValue;
	private String allergy;
	private String addiction;
	 private String bmi;
	   private String pallor;
	   private String cyanosis;
	   private String clubbingOfFingers;
	   private String lymphadenopathy;
	   private String oedema;
	   private String malNutrition;
	   private String dehydration;
	   private String temperature;
	   private String pulseRate;
	   private String respirationRate;
	   private String bpLmt;
	   private String bpRmt;
	   private String height;
	   private String weight;
	   private String dehydrationType;
	   private List<LabelBean> lstPersonalHistory;
	   private String haemoglobinReport;
		private String bloodSugarReport;
		private String cholesterolReport;
		private String liverFunctionReport;
		private String kidneyReport;
		private String cardiacReport;
		private String overallHealthRemarks;
		private String summary;
		private String healthGrade;
		private int investLength;
		private String[] investAttach=new String[100]; 
		private String[] testCheck;

		
		
		public String[] getInvestRemLst() {
			return investRemLst;
		}

		public void setInvestRemLst(String[] investRemLst) {
			this.investRemLst = investRemLst;
		}

		public String[] getConsultRemLst() {
			return consultRemLst;
		}

		public void setConsultRemLst(String[] consultRemLst) {
			this.consultRemLst = consultRemLst;
		}

		public List<AnnualCheckUpVo> getConsultList() {
			return consultList;
		}

		public void setConsultList(List<AnnualCheckUpVo> consultList) {
			this.consultList = consultList;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public String[] getTestCheck() {
			return testCheck;
		}

		public void setTestCheck(String[] testCheck) {
			this.testCheck = testCheck;
		}

		public List<LabelBean> getLstPersonalHistory() {
			return lstPersonalHistory;
		}

		public void setLstPersonalHistory(List<LabelBean> lstPersonalHistory) {
			this.lstPersonalHistory = lstPersonalHistory;
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
	
	public String getFamilyHis() {
		return familyHis;
	}
	public void setFamilyHis(String familyHis) {
		this.familyHis = familyHis;
	}
	public String getDehydrationType() {
		return dehydrationType;
	}
	public void setDehydrationType(String dehydrationType) {
		this.dehydrationType = dehydrationType;
	}
	public String getLymphadenopathy() {
		return lymphadenopathy;
	}
	public void setLymphadenopathy(String lymphadenopathy) {
		this.lymphadenopathy = lymphadenopathy;
	}
	public String getOedema() {
		return oedema;
	}
	public void setOedema(String oedema) {
		this.oedema = oedema;
	}
	public String getMalNutrition() {
		return malNutrition;
	}
	public void setMalNutrition(String malNutrition) {
		this.malNutrition = malNutrition;
	}
	public String getDehydration() {
		return dehydration;
	}
	public void setDehydration(String dehydration) {
		this.dehydration = dehydration;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getPulseRate() {
		return pulseRate;
	}
	public void setPulseRate(String pulseRate) {
		this.pulseRate = pulseRate;
	}
	public String getRespirationRate() {
		return respirationRate;
	}
	public void setRespirationRate(String respirationRate) {
		this.respirationRate = respirationRate;
	}
	public String getBpLmt() {
		return bpLmt;
	}
	public void setBpLmt(String bpLmt) {
		this.bpLmt = bpLmt;
	}
	public String getBpRmt() {
		return bpRmt;
	}
	public void setBpRmt(String bpRmt) {
		this.bpRmt = bpRmt;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getPallor() {
		return pallor;
	}
	public void setPallor(String pallor) {
		this.pallor = pallor;
	}
	public String getCyanosis() {
		return cyanosis;
	}
	public void setCyanosis(String cyanosis) {
		this.cyanosis = cyanosis;
	}
	public String getClubbingOfFingers() {
		return clubbingOfFingers;
	}
	public void setClubbingOfFingers(String clubbingOfFingers) {
		this.clubbingOfFingers = clubbingOfFingers;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getAddiction() {
		return addiction;
	}
	public void setAddiction(String addiction) {
		this.addiction = addiction;
	}
	public List<String> getLstPerHis() {
		return lstPerHis;
	}
	public void setLstPerHis(List<String> lstPerHis) {
		this.lstPerHis = lstPerHis;
	}
	public String getPastIllnessValue() {
		return pastIllnessValue;
	}
	public void setPastIllnessValue(String pastIllnessValue) {
		this.pastIllnessValue = pastIllnessValue;
	}
	public String getPresentIllness() {
		return presentIllness;
	}
	public void setPresentIllness(String presentIllness) {
		this.presentIllness = presentIllness;
	}
	public String getPastIllness() {
		return pastIllness;
	}
	public void setPastIllness(String pastIllness) {
		this.pastIllness = pastIllness;
	}
	public String getPastIllenesCancers() {
		return pastIllenesCancers;
	}
	public void setPastIllenesCancers(String pastIllenesCancers) {
		this.pastIllenesCancers = pastIllenesCancers;
	}
	public String getPastIllenesEndDis() {
		return pastIllenesEndDis;
	}
	public void setPastIllenesEndDis(String pastIllenesEndDis) {
		this.pastIllenesEndDis = pastIllenesEndDis;
	}
	public String getPastIllenesSurg() {
		return pastIllenesSurg;
	}
	public void setPastIllenesSurg(String pastIllenesSurg) {
		this.pastIllenesSurg = pastIllenesSurg;
	}
	public String getPastIllenesOthr() {
		return pastIllenesOthr;
	}
	public void setPastIllenesOthr(String pastIllenesOthr) {
		this.pastIllenesOthr = pastIllenesOthr;
	}
	public List<String> getPersonalHistList() {
		return personalHistList;
	}
	public void setPersonalHistList(List<String> personalHistList) {
		this.personalHistList = personalHistList;
	}
	public List<AnnualCheckUpVo> getTestsList() {
		return testsList;
	}
	public void setTestsList(List<AnnualCheckUpVo> testsList) {
		this.testsList = testsList;
	}
	public String getPostDist() {
		return postDist;
	}
	public String getDeptHod() {
		return deptHod;
	}
	public String getPostDdoCode() {
		return postDdoCode;
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
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public String getRelOthers() {
		return relOthers;
	}
	public void setRelOthers(String relOthers) {
		this.relOthers = relOthers;
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
	public String getPersonalHis() {
		return personalHis;
	}
	public void setPersonalHis(String personalHis) {
		this.personalHis = personalHis;
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
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getDdoOffUnit() {
		return ddoOffUnit;
	}
	public void setDdoOffUnit(String ddoOffUnit) {
		this.ddoOffUnit = ddoOffUnit;
	}

	public String getAhcId() {
		return ahcId;
	}
	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}
	public String getHaemoglobin() {
		return haemoglobin;
	}
	public void setHaemoglobin(String haemoglobin) {
		this.haemoglobin = haemoglobin;
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
	public String getTlc() {
		return tlc;
	}
	public void setTlc(String tlc) {
		this.tlc = tlc;
	}
	public String getBasophils() {
		return basophils;
	}
	public void setBasophils(String basophils) {
		this.basophils = basophils;
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
	public String getPersonalHist() {
		return personalHist;
	}
	public void setPersonalHist(String personalHist) {
		this.personalHist = personalHist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public void setPostDist(String postDist) {
		this.postDist = postDist;
	}
	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}
	public void setPostDdoCode(String postDdoCode) {
		this.postDdoCode = postDdoCode;
	}
	public String getDept_Hod() {
		return dept_Hod;
	}
	public void setDept_Hod(String dept_Hod) {
		this.dept_Hod = dept_Hod;
	}
	public String getPost_Dist() {
		return post_Dist;
	}
	public void setPost_Dist(String post_Dist) {
		this.post_Dist = post_Dist;
	}
	public String getPost_Ddo_Code() {
		return post_Ddo_Code;
	}
	public void setPost_Ddo_Code(String post_Ddo_Code) {
		this.post_Ddo_Code = post_Ddo_Code;
	}

	public String getExamFindg() {
		return examFindg;
	}

	public void setExamFindg(String examFindg) {
		this.examFindg = examFindg;
	}
	
	public String getInvestAttach(int index)
		{
			return investAttach[index];
		}
	public void setInvestAttach(int index,String value)
		{
			investAttach[index]=value;
		}

	public int getInvestLength() {
		return investLength;
	}

	public void setInvestLength(int investLength) {
		this.investLength = investLength;
	}
	
}
