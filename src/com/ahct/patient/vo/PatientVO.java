package com.ahct.patient.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;

public class PatientVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private String empCode;
	private String cardNo;	
	private String patientId;
	private String processInstanceId;
	private String rationCard;
	private String firstName;
	private String middleName;
	private String roleId;
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
	private String medicalSplty;
	private String contactNo;
	private String regHospId;
	private String regHospDt;
	private String regHCDt;
	private String ccUsr;
	private String phaseId;
	private String cid;
	private String renewal;
	private String patientScheme;
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
	private List<LabelBean> investList;
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
	private Date caseRegnDate;
	private String regState;
    private Number COUNT=0;
    private String cochlearYN;
	private String dentalHospType;
	private Number daysDiff;
	private String organTransYN;
	private long pckAppvAmt;
	private long comorbidAppvAmt;
	private long enhAppvAmt;
	private String ceoApprovalFlag;
	private String hospType;
	
	private String opTotPkgAmt;
	
	private String unitName;
	private String unitHodName;
	private String otherComplaint;
	private String otherSymptom;
	
	private String postDist;
	private String stoCode;
	private String ddoCode;
	private String deptHod;
	
	private String otherDiagName;
	private String hospGov;

    
    /*added for chronic op case*/
    
    private String chronicId;
    

    private String ENROLLPRNTID;
    private String ENROLLPHOTO;
    
    private String consultFee;
    private String totInvestAmt;
    
    private List<LabelBean> consultList;
    
    /*Added by Kalyan for Biometric Registration*/
    private String fingerPrint;
    private String tgGovPatCond;
    private String fingerCaptured;
    private String bioRegFlag;
    private String bioHand;
	
    private String softTissue;
	private String deciduosDentition;
	private String missing;
	private String caries;
	private String decayed;
	
	private String attrition;
	private String previousDentalTreatment;
	private String occlusion;
	
	private String probeDepth;
	private String diagnosis;
	private String admissionDetails;
	private String advancedInvestigations;
	private String followupAdv;
	private String dentalFlg;
//Added by Pavan
	private String journalEnrollId;
	
//added by pavan dental case sheet
	
    private String face ;           
    private String tmj ;            
    private String  jaws ;            
    private String lymphadenopathy ;            
    private String gingiva ;  
    private String buccalMucosa;             
    private String frenalAttachment ;              
    private String tongue;              
    private String floorMouth;                         
    private String softPalate;      
    private String hardPalate; 
    private String deciduousCaries ;           
	private String deciduousMissing ;          
	private String grosslyDecayed ;            
	private String mobile       ;               
	private String permanentCaries ;           
	private String rootGrosslyDecayed  ;      
	private String mobility       ;             
	private String attritionAbrasion ;         
	private String permanentMissing ;          
	private String permanentothers ;           
	         
	private String treatmentDntl;
	private String drughistory;
	private String drughistoryid;
	private String denatlcasesheet;
	private String[] medicalDtlsid;
	private String[] extraoral;
	private String[] intraoral;
	private String showMedicalTextval;
	private String Subdental0;
	private String Subdental1;
    private String Subdental2;
	private String Subdental3;
	private String dntsublistoral0;
	private String dntsublistoral1;
	private String dntsublistoral2;
	private String dntsublistoral3;
	private String dntsublistoral4;
	private String dntsublistoral5;
	private String dntsublistoral6;
	private String[] subdentalrl0;
	private String subdentaljaws1;
	private String subdentalrltxt;
	private String subdentaljawstxt;
	private String[] childCaries;
	private String carriesdecidous;
	private String missingdecidous;
	private String mobiledecidous;
	private String grosslydecadedecidous;
	private String carriespermanent;
	private String rootstumppermannet;
	private String mobilitypermanent;
	private String attritionpermanent;
	private String missingpermanent;
	private String probingdepth;
	private String otherpermanent;
	private String occlusionType;
	private String occlusionOther;
	private String medicationGiven;
	private String fromDisp;
	 private String prc;
	 private String payScale;
	 private String dept;
	 private String postDDOcode;
	 private String servDsgn;
	 private String ddoOffUnit;
	 private String currPay;
	 private String designation;
	 private String aadharID;
	 private String aadharEID;
	 private String refSpclty;
	 private String refDist;
	 private String refHospId;
	 private String category;
	 private Long caseThrpySeq;
	 private String highEndProformaId;
	 
	//Chandana - 8326
	private String abhaMsgFlag;
	private String ekycFlag;
	private String abhaId;
	 
	public String getHighEndProformaId() {
		return highEndProformaId;
	}
	public void setHighEndProformaId(String highEndProformaId) {
		this.highEndProformaId = highEndProformaId;
	}
	public String getRefSpclty() {
		return refSpclty;
	}
	public void setRefSpclty(String refSpclty) {
		this.refSpclty = refSpclty;
	}
	public String getRefDist() {
		return refDist;
	}
	public void setRefDist(String refDist) {
		this.refDist = refDist;
	}
	public String getRefHospId() {
		return refHospId;
	}
	public void setRefHospId(String refHospId) {
		this.refHospId = refHospId;
	}
	//Added for Swelling & Pus/Discharge details
	    private String swSite;
	    private String swSize;
	    private String swExtension;
	    private String swColour;
	    private String swConsistency;
	    private String swTenderness;
	    private String swBorders;
	    private String psSite;
	    private String psDischarge;
	   
	    /**
	     * Added for hub spoc mpg
	     * @return
	     */
	    private String groupId;
	    private String hubHospId;
	    public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
		public String getHubHospId() {
			return hubHospId;
		}
		public void setHubHospId(String hubHospId) {
			this.hubHospId = hubHospId;
		}
		/**
		 * end of hubspoke cr
		 * @return
		 */
	public String[] getSubdentalrl0() {
		return subdentalrl0;
	}
	public void setSubdentalrl0(String[] subdentalrl0) {
		this.subdentalrl0 = subdentalrl0;
	}
	public String getSubdentaljaws1() {
		return subdentaljaws1;
	}
	public void setSubdentaljaws1(String subdentaljaws1) {
		this.subdentaljaws1 = subdentaljaws1;
	}
	public String getSubdentalrltxt() {
		return subdentalrltxt;
	}
	public void setSubdentalrltxt(String subdentalrltxt) {
		this.subdentalrltxt = subdentalrltxt;
	}
	public String getSubdentaljawstxt() {
		return subdentaljawstxt;
	}
	public void setSubdentaljawstxt(String subdentaljawstxt) {
		this.subdentaljawstxt = subdentaljawstxt;
	}
	public String getSubdental0() {
		return Subdental0;
	}
	public void setSubdental0(String subdental0) {
		Subdental0 = subdental0;
	}
	public String getSubdental1() {
		return Subdental1;
	}
	public void setSubdental1(String subdental1) {
		Subdental1 = subdental1;
	}
	public String getSubdental2() {
		return Subdental2;
	}
	public void setSubdental2(String subdental2) {
		Subdental2 = subdental2;
	}
	public String getSubdental3() {
		return Subdental3;
	}
	public void setSubdental3(String subdental3) {
		Subdental3 = subdental3;
	}
	public String getDntsublistoral0() {
		return dntsublistoral0;
	}
	public void setDntsublistoral0(String dntsublistoral0) {
		this.dntsublistoral0 = dntsublistoral0;
	}
	public String getDntsublistoral1() {
		return dntsublistoral1;
	}
	public void setDntsublistoral1(String dntsublistoral1) {
		this.dntsublistoral1 = dntsublistoral1;
	}
	public String getDntsublistoral2() {
		return dntsublistoral2;
	}
	public void setDntsublistoral2(String dntsublistoral2) {
		this.dntsublistoral2 = dntsublistoral2;
	}
	public String getDntsublistoral3() {
		return dntsublistoral3;
	}
	public void setDntsublistoral3(String dntsublistoral3) {
		this.dntsublistoral3 = dntsublistoral3;
	}
	public String getDntsublistoral4() {
		return dntsublistoral4;
	}
	public void setDntsublistoral4(String dntsublistoral4) {
		this.dntsublistoral4 = dntsublistoral4;
	}
	public String getDntsublistoral5() {
		return dntsublistoral5;
	}
	public void setDntsublistoral5(String dntsublistoral5) {
		this.dntsublistoral5 = dntsublistoral5;
	}
	public String getDntsublistoral6() {
		return dntsublistoral6;
	}
	public void setDntsublistoral6(String dntsublistoral6) {
		this.dntsublistoral6 = dntsublistoral6;
	}
	public String getDeciduousCaries() {
		return deciduousCaries;
	}
	public void setDeciduousCaries(String deciduousCaries) {
		this.deciduousCaries = deciduousCaries;
	}
	public String getDeciduousMissing() {
		return deciduousMissing;
	}
	public void setDeciduousMissing(String deciduousMissing) {
		this.deciduousMissing = deciduousMissing;
	}
	public String getGrosslyDecayed() {
		return grosslyDecayed;
	}
	public void setGrosslyDecayed(String grosslyDecayed) {
		this.grosslyDecayed = grosslyDecayed;
	}
	public String getPermanentCaries() {
		return permanentCaries;
	}
	public void setPermanentCaries(String permanentCaries) {
		this.permanentCaries = permanentCaries;
	}
	public String getRootGrosslyDecayed() {
		return rootGrosslyDecayed;
	}
	public void setRootGrosslyDecayed(String rootGrosslyDecayed) {
		this.rootGrosslyDecayed = rootGrosslyDecayed;
	}
	public String getMobility() {
		return mobility;
	}
	public void setMobility(String mobility) {
		this.mobility = mobility;
	}
	public String getAttritionAbrasion() {
		return attritionAbrasion;
	}
	public void setAttritionAbrasion(String attritionAbrasion) {
		this.attritionAbrasion = attritionAbrasion;
	}
	public String getPermanentMissing() {
		return permanentMissing;
	}
	public void setPermanentMissing(String permanentMissing) {
		this.permanentMissing = permanentMissing;
	}
	public String getPermanentothers() {
		return permanentothers;
	}
	public void setPermanentothers(String permanentothers) {
		this.permanentothers = permanentothers;
	}
	public String getJaws() {
		return jaws;
	}
	public void setJaws(String jaws) {
		this.jaws = jaws;
	}
	public String getLymphadenopathy() {
		return lymphadenopathy;
	}
	public void setLymphadenopathy(String lymphadenopathy) {
		this.lymphadenopathy = lymphadenopathy;
	}
	public String getGingiva() {
		return gingiva;
	}
	public void setGingiva(String gingiva) {
		this.gingiva = gingiva;
	}
	public String getBuccalMucosa() {
		return buccalMucosa;
	}
	public void setBuccalMucosa(String buccalMucosa) {
		this.buccalMucosa = buccalMucosa;
	}
	public String getFrenalAttachment() {
		return frenalAttachment;
	}
	public void setFrenalAttachment(String frenalAttachment) {
		this.frenalAttachment = frenalAttachment;
	}
	public String getTongue() {
		return tongue;
	}
	public void setTongue(String tongue) {
		this.tongue = tongue;
	}
	public String getFloorMouth() {
		return floorMouth;
	}
	public void setFloorMouth(String floorMouth) {
		this.floorMouth = floorMouth;
	}
	public String getSoftPalate() {
		return softPalate;
	}
	public void setSoftPalate(String softPalate) {
		this.softPalate = softPalate;
	}
	public String getHardPalate() {
		return hardPalate;
	}
	public void setHardPalate(String hardPalate) {
		this.hardPalate = hardPalate;
	}
	public String getDentalFlg() {
		return dentalFlg;
	}
	public void setDentalFlg(String dentalFlg) {
		this.dentalFlg = dentalFlg;
	}
	public String getSoftTissue() {
		return softTissue;
	}
	public void setSoftTissue(String softTissue) {
		this.softTissue = softTissue;
	}
	public String getDeciduosDentition() {
		return deciduosDentition;
	}
	public void setDeciduosDentition(String deciduosDentition) {
		this.deciduosDentition = deciduosDentition;
	}
	public String getMissing() {
		return missing;
	}
	public void setMissing(String missing) {
		this.missing = missing;
	}
	public String getCaries() {
		return caries;
	}
	public void setCaries(String caries) {
		this.caries = caries;
	}
	public String getDecayed() {
		return decayed;
	}
	public void setDecayed(String decayed) {
		this.decayed = decayed;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAttrition() {
		return attrition;
	}
	public void setAttrition(String attrition) {
		this.attrition = attrition;
	}
	public String getPreviousDentalTreatment() {
		return previousDentalTreatment;
	}
	public void setPreviousDentalTreatment(String previousDentalTreatment) {
		this.previousDentalTreatment = previousDentalTreatment;
	}
	public String getOcclusion() {
		return occlusion;
	}
	public void setOcclusion(String occlusion) {
		this.occlusion = occlusion;
	}
	public String getTmj() {
		return tmj;
	}
	public void setTmj(String tmj) {
		this.tmj = tmj;
	}
	public String getProbeDepth() {
		return probeDepth;
	}
	public void setProbeDepth(String probeDepth) {
		this.probeDepth = probeDepth;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getAdmissionDetails() {
		return admissionDetails;
	}
	public void setAdmissionDetails(String admissionDetails) {
		this.admissionDetails = admissionDetails;
	}
	public String getAdvancedInvestigations() {
		return advancedInvestigations;
	}
	public void setAdvancedInvestigations(String advancedInvestigations) {
		this.advancedInvestigations = advancedInvestigations;
	}
	public String getFollowupAdv() {
		return followupAdv;
	}
	public void setFollowupAdv(String followupAdv) {
		this.followupAdv = followupAdv;
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String[] getPersonalHistory() {
		return personalHistory;
	}
	public void setPersonalHistory(String[] personalHistory) {
		this.personalHistory = personalHistory;
	}
	public String[] getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String[] familyHistory) {
		this.familyHistory = familyHistory;
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
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getTherapyCatId() {
		return therapyCatId;
	}
	public void setTherapyCatId(String therapyCatId) {
		this.therapyCatId = therapyCatId;
	}
	public String getIpCaseRemarks() {
		return ipCaseRemarks;
	}
	public void setIpCaseRemarks(String ipCaseRemarks) {
		this.ipCaseRemarks = ipCaseRemarks;
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

	
	
	
	
	public String getENROLLPRNTID() {
		return ENROLLPRNTID;
	}
	public void setENROLLPRNTID(String eNROLLPRNTID) {
		ENROLLPRNTID = eNROLLPRNTID;
	}
	public String getENROLLPHOTO() {
		return ENROLLPHOTO;
	}
	public void setENROLLPHOTO(String eNROLLPHOTO) {
		ENROLLPHOTO = eNROLLPHOTO;
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
	public String getCaseProcCodes() {
		return caseProcCodes;
	}
	public void setCaseProcCodes(String caseProcCodes) {
		this.caseProcCodes = caseProcCodes;
	}
	public List<LabelBean> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(List<LabelBean> symptoms) {
		this.symptoms = symptoms;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public void setCaseRegDate(Date caseRegDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		if(caseRegDate != null )
			try {
				this.caseRegDate =  sdf.format(caseRegDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Number getCOUNT() {
		return COUNT;
	}
	
	/**
	 * Sets the count.
	 *
	 * @param cOUNT the new count
	 */
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}
	public String getRegState() {
		return regState;
	}
	public void setRegState(String regState) {
		this.regState = regState;
	}
	public String getCochlearYN() {
		return cochlearYN;
	}
	public void setCochlearYN(String cochlearYN) {
		this.cochlearYN = cochlearYN;
	}
	public String getPatientScheme() {
		return patientScheme;
	}
	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	public String getDentalHospType() {
		return dentalHospType;
	}
	public void setDentalHospType(String dentalHospType) {
		this.dentalHospType = dentalHospType;
	}
	public long getPckAppvAmt() {
		return pckAppvAmt;
	}
	public void setPckAppvAmt(long pckAppvAmt) {
		this.pckAppvAmt = pckAppvAmt;
	}
	public long getComorbidAppvAmt() {
		return comorbidAppvAmt;
	}
	public void setComorbidAppvAmt(long comorbidAppvAmt) {
		this.comorbidAppvAmt = comorbidAppvAmt;
	}
	public long getEnhAppvAmt() {
		return enhAppvAmt;
	}
	public void setEnhAppvAmt(long enhAppvAmt) {
		this.enhAppvAmt = enhAppvAmt;
	}
	public String getCeoApprovalFlag() {
		return ceoApprovalFlag;
	}
	public void setCeoApprovalFlag(String ceoApprovalFlag) {
		this.ceoApprovalFlag = ceoApprovalFlag;
	}
	public String getOpTotPkgAmt() {
		return opTotPkgAmt;
	}
	public void setOpTotPkgAmt(String opTotPkgAmt) {
		this.opTotPkgAmt = opTotPkgAmt;
	}
	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	public Number getDaysDiff() {
		return daysDiff;
	}
	public void setDaysDiff(Number daysDiff) {
		this.daysDiff = daysDiff;
	}
	public String getConsultFee() {
		return consultFee;
	}
	public void setConsultFee(String consultFee) {
		this.consultFee = consultFee;
	}
	public String getTotInvestAmt() {
		return totInvestAmt;
	}
	public void setTotInvestAmt(String totInvestAmt) {
		this.totInvestAmt = totInvestAmt;
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
	public String getOtherComplaint() {
		return otherComplaint;
	}
	public void setOtherComplaint(String otherComplaint) {
		this.otherComplaint = otherComplaint;
	}
	public String getOtherSymptom() {
		return otherSymptom;
	}
	public void setOtherSymptom(String otherSymptom) {
		this.otherSymptom = otherSymptom;
	}
	public String getChronicId() {
		return chronicId;
	}
	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}
	public void setCaseRegDate(String caseRegDate) {
		this.caseRegDate = caseRegDate;
	}
	public List<LabelBean> getConsultList() {
		return consultList;
	}
	public void setConsultList(List<LabelBean> consultList) {
		this.consultList = consultList;
	}
	public Date getCaseRegnDate() {
		return caseRegnDate;
	}
	public void setCaseRegnDate(Date caseRegnDate) {
		this.caseRegnDate = caseRegnDate;
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
	public String getDeptHod() {
		return deptHod;
	}
	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}
	public String getFingerPrint() {
		return fingerPrint;
	}
	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}
	public String getTgGovPatCond() {
		return tgGovPatCond;
	}
	public void setTgGovPatCond(String tgGovPatCond) {
		this.tgGovPatCond = tgGovPatCond;
	}
	public String getFingerCaptured() {
		return fingerCaptured;
	}
	public void setFingerCaptured(String fingerCaptured) {
		this.fingerCaptured = fingerCaptured;
	}
	public String getOtherDiagName() {
		return otherDiagName;
	}
	public void setOtherDiagName(String otherDiagName) {
		this.otherDiagName = otherDiagName;
	}
	public String getBioRegFlag() {
		return bioRegFlag;
	}
	public void setBioRegFlag(String bioRegFlag) {
		this.bioRegFlag = bioRegFlag;
	}
	public String getBioHand() {
		return bioHand;
	}
	public void setBioHand(String bioHand) {
		this.bioHand = bioHand;
	}
	public String getJournalEnrollId() {
		return journalEnrollId;
	}
	public void setJournalEnrollId(String journalEnrollId) {
		this.journalEnrollId = journalEnrollId;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getTreatmentDntl() {
		return treatmentDntl;
	}
	public void setTreatmentDntl(String treatmentDntl) {
		this.treatmentDntl = treatmentDntl;
	}

	public String getDrughistory() {
		return drughistory;
	}
	public void setDrughistory(String drughistory) {
		this.drughistory = drughistory;
	}
	public String getDrughistoryid() {
		return drughistoryid;
	}
	public void setDrughistoryid(String drughistoryid) {
		this.drughistoryid = drughistoryid;
	}
	public String getDenatlcasesheet() {
		return denatlcasesheet;
	}
	public void setDenatlcasesheet(String denatlcasesheet) {
		this.denatlcasesheet = denatlcasesheet;
	}
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getPayScale() {
		return payScale;
	}
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPostDDOcode() {
		return postDDOcode;
	}
	public void setPostDDOcode(String postDDOcode) {
		this.postDDOcode = postDDOcode;
	}
	public String getServDsgn() {
		return servDsgn;
	}
	public void setServDsgn(String servDsgn) {
		this.servDsgn = servDsgn;
	}
	public String getDdoOffUnit() {
		return ddoOffUnit;
	}
	public void setDdoOffUnit(String ddoOffUnit) {
		this.ddoOffUnit = ddoOffUnit;
	}
	public String getCurrPay() {
		return currPay;
	}
	public void setCurrPay(String currPay) {
		this.currPay = currPay;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String[] getMedicalDtlsid() {
		return medicalDtlsid;
	}
	public void setMedicalDtlsid(String[] medicalDtlsid) {
		this.medicalDtlsid = medicalDtlsid;
	}
	public String getShowMedicalTextval() {
		return showMedicalTextval;
	}
	public void setShowMedicalTextval(String showMedicalTextval) {
		this.showMedicalTextval = showMedicalTextval;
	}
	public String[] getExtraoral() {
		return extraoral;
	}
	public void setExtraoral(String[] extraoral) {
		this.extraoral = extraoral;
	}
	public String[] getIntraoral() {
		return intraoral;
	}
	public void setIntraoral(String[] intraoral) {
		this.intraoral = intraoral;
	}
	public String[] getChildCaries() {
		return childCaries;
	}
	public void setChildCaries(String[] childCaries) {
		this.childCaries = childCaries;
	}
	public String getCarriesdecidous() {
		return carriesdecidous;
	}
	public void setCarriesdecidous(String carriesdecidous) {
		this.carriesdecidous = carriesdecidous;
	}
	public String getProbingdepth() {
		return probingdepth;
	}
	public void setProbingdepth(String probingdepth) {
		this.probingdepth = probingdepth;
	}
	public String getMissingdecidous() {
		return missingdecidous;
	}
	public void setMissingdecidous(String missingdecidous) {
		this.missingdecidous = missingdecidous;
	}
	public String getMobiledecidous() {
		return mobiledecidous;
	}
	public void setMobiledecidous(String mobiledecidous) {
		this.mobiledecidous = mobiledecidous;
	}
	public String getGrosslydecadedecidous() {
		return grosslydecadedecidous;
	}
	public void setGrosslydecadedecidous(String grosslydecadedecidous) {
		this.grosslydecadedecidous = grosslydecadedecidous;
	}
	public String getCarriespermanent() {
		return carriespermanent;
	}
	public void setCarriespermanent(String carriespermanent) {
		this.carriespermanent = carriespermanent;
	}
	public String getRootstumppermannet() {
		return rootstumppermannet;
	}
	public void setRootstumppermannet(String rootstumppermannet) {
		this.rootstumppermannet = rootstumppermannet;
	}
	public String getMobilitypermanent() {
		return mobilitypermanent;
	}
	public void setMobilitypermanent(String mobilitypermanent) {
		this.mobilitypermanent = mobilitypermanent;
	}
	public String getAttritionpermanent() {
		return attritionpermanent;
	}
	public void setAttritionpermanent(String attritionpermanent) {
		this.attritionpermanent = attritionpermanent;
	}
	public String getMissingpermanent() {
		return missingpermanent;
	}
	public void setMissingpermanent(String missingpermanent) {
		this.missingpermanent = missingpermanent;
	}
	public String getOtherpermanent() {
		return otherpermanent;
	}
	public void setOtherpermanent(String otherpermanent) {
		this.otherpermanent = otherpermanent;
	}
	public String getOcclusionType() {
		return occlusionType;
	}
	public void setOcclusionType(String occlusionType) {
		this.occlusionType = occlusionType;
	}
	public String getOcclusionOther() {
		return occlusionOther;
	}
	public void setOcclusionOther(String occlusionOther) {
		this.occlusionOther = occlusionOther;
	}
	public String getMedicationGiven() {
		return medicationGiven;
	}
	public void setMedicationGiven(String medicationGiven) {
		this.medicationGiven = medicationGiven;
	}
	public String getAadharID() {
		return aadharID;
	}
	public void setAadharID(String aadharID) {
		this.aadharID = aadharID;
	}
	public String getAadharEID() {
		return aadharEID;
	}
	public void setAadharEID(String aadharEID) {
		this.aadharEID = aadharEID;
	}
	public String getSwSite() {
		return swSite;
	}
	public void setSwSite(String swSite) {
		this.swSite = swSite;
	}
	public String getSwSize() {
		return swSize;
	}
	public void setSwSize(String swSize) {
		this.swSize = swSize;
	}
	public String getSwExtension() {
		return swExtension;
	}
	public void setSwExtension(String swExtension) {
		this.swExtension = swExtension;
	}
	public String getHospGov() {
		return hospGov;
	}
	public void setHospGov(String hospGov) {
		this.hospGov = hospGov;
	}
	public String getSwColour() {
		return swColour;
	}
	public void setSwColour(String swColour) {
		this.swColour = swColour;
	}
	public String getSwConsistency() {
		return swConsistency;
	}
	public void setSwConsistency(String swConsistency) {
		this.swConsistency = swConsistency;
	}
		public String getSwTenderness() {
		return swTenderness;
	}
	public void setSwTenderness(String swTenderness) {
		this.swTenderness = swTenderness;
	}
	public String getSwBorders() {
		return swBorders;
	}
	public void setSwBorders(String swBorders) {
		this.swBorders = swBorders;
	}
	public String getPsSite() {
		return psSite;
	}
	public void setPsSite(String psSite) {
		this.psSite = psSite;
	}
	public String getPsDischarge() {
		return psDischarge;
	}
	public void setPsDischarge(String psDischarge) {
		this.psDischarge = psDischarge;
	}
	public String getFromDisp() {
		return fromDisp;
	}
	public void setFromDisp(String fromDisp) {
		this.fromDisp = fromDisp;
	}
	public List<LabelBean> getInvestList() {
		return investList;
	}
	public void setInvestList(List<LabelBean> investList) {
		this.investList = investList;
	}
	public String getMedicalSplty() {
		return medicalSplty;
	}
	public void setMedicalSplty(String medicalSplty) {
		this.medicalSplty = medicalSplty;
	}
	public String getOrganTransYN() {
		return organTransYN;
	}
	public void setOrganTransYN(String organTransYN) {
		this.organTransYN = organTransYN;
	}
	public Long getCaseThrpySeq() {
		return caseThrpySeq;
	}
	public void setCaseThrpySeq(Long caseThrpySeq) {
		this.caseThrpySeq = caseThrpySeq;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAbhaMsgFlag() {
		return abhaMsgFlag;
	}
	public void setAbhaMsgFlag(String abhaMsgFlag) {
		this.abhaMsgFlag = abhaMsgFlag;
	}
	public String getEkycFlag() {
		return ekycFlag;
	}
	public void setEkycFlag(String ekycFlag) {
		this.ekycFlag = ekycFlag;
	}
	public String getAbhaId() {
		return abhaId;
	}
	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}
	
}
