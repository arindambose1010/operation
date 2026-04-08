package com.ahct.patient.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;

public class PatientForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String msg;
	private String diag;
	public String getDiag() {
		return diag;
	}
	public void setDiag(String diag) {
		this.diag = diag;
	}
	private String empCode;
	private String medicalSpclty;
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
	private String gender;
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
	private String district;
	private String mdl_mcl;
	private String mandal;
	private String municipality;
	private String village;
	private String hamlet;
	private String pin;
	private String medicalCat;
	private String medicalDrug;
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
	private String fromDate;
	private String toDate;
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
    private String stateType;
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
	private FormFile attach[]= new FormFile[300];
	private FormFile genAttach[]= new FormFile[300];
	private FormFile updateGenAttach[]= new FormFile[300];
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
	private String addrEnable;
	private String telScheme;
	private String patientScheme;
	private FormFile childPhoto;
	private FormFile identificationAttachment;
	private String newBornBaby; 
	private String dentalProc;
	private String dentalSpecApproval;
	private long investPrice;
	private long totInvestPrice;
	private long consultFee;
	private long totalOpCost;
	
	private String otherInvName;
	private String otherInvBlockName;
	private String investOthers;
	private String drugsOthers;
	private String otherDrugName;
	private int otherInvestCount;
	private int otherDrugCount;
	private String opActiveMsg;
	private String unitName;
	private String unitHodName;
	
	private String height;
	private String weight;
	private String bmi;
	
	private String chronicNo;
	 
	   private String packageDrugAmt;
	   
	   private String caseApprovalFlag;
	   private String chronicStatus;
	   private String ceoRemark;

	   private String chronicID;
		private String name;
		private String employeeNo;
	
		private BigDecimal  age;
		private String registrationDate;

		private int startPage;
		private int endPage;

		private int endIndex;
		private String rowsPerPage;
		private String showPage;
		private String next;
		private String prev;

	    private String deptHod;
	    private String postDist;
	    private String stoCode;
	    private String ddoCode;

	 	private String batchNo;
	 	private String expiryDt;
		private String regCaseFlg;
		private String addPckgFlg;
		
		private  String otherComplaint;
		private String otherSymptomName;
		private String otherSymptoms;
		private int otherSymptomCount;
		
		private String diagOthers;
		private String diagnosisName;
		
		private List<LabelBean> consultData;
		List<CasesSearchVO> lstCaseSearch;
		private String bioFinger;
		private String bioCaptureDtlsYN;
		private String biometricValue;
		
		private String tgGovPatCond;
		private String selectedDrug;
		private String fingerPrint;
		private String diagOthersName;
		private String bioHand;
		private String totalRows;
		private String diagCondition;
		private String checkType;
		private String hosType;
		private String[] softTissue;
		private String[] childCaries;
		private String[] missingTeeth;
		private String[] caries;
		private String[] decayed;
		//private String[] mobile;
		private String[] attrition;
		private String previousDentalTreatment;
		private String occlusion;
		//private String tmj;
		private String[] probeDepth;
		private String diagnosis;
		private String admissionDetails;
		private String advancedInvestigations;
		private String followupAdv;
		private String medicationGiven;
		//private String face;
		//private String jaws;
		private String mandibleFracture;
		private String maxillaFracture;
		private String zygomaticFracture;
		private String occlusionType;
		private String occlusionOther;
		private String permanentDent;
		
		private String deciduousDent;
		private String lymphapathyType;
		private String lymphapathyText;
		
		//private String lymphadenopathy;
		private String faceCheck;
		private String tmjCheck;
		private String jawsCheck;
		private String lymphadenopathyCheck;
		
		private String otherPermntDent;
		private String otherPermText;
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
		/*added by pavan for dental case sheet new block*/
		private String drughistory;
		private String drughistoryid;
		private String medicalHistVal;
		private String showMedicalTextval;
		private List<LabelBean> extraoraldtls;
		private List<LabelBean> intraoraldtls;
		private List<LabelBean> occlusiondtls;
		private List<LabelBean> deciduousdentdtls;
		private List<LabelBean> permanentdentdtls;
		private List<LabelBean> medicalhsitorydtls;
		private String[] medicalDtlsid;
		private String[] extraoral;
		//private String[] medicalDtlsid;
		private String[] intraoral;
		private String[] deciduous;
		private String[] permanent;
		private String[] dntsublistoral;
		private String Subdental0;
		private String Subdental1;
	    private String Subdental2;
		private String Subdental3;
		private List<LabelBean> Dentalsublist;
		private List<LabelBean> Dentalmainlist0;
		private List<LabelBean> Dentalmainlist1;
		private List<LabelBean> Dentalmainlist2;
		private List<LabelBean> Dentalmainlist3;
		private List<LabelBean> Dentalsublistjaws1;
		private List<LabelBean> Dentalsublistrl0;
		private String subdentaljaws1;
		private String subdentaljaws2;
		private String subdentaljaws3;
		private String subdentaljaws0;
		private String subdentaljawstxt;
		private String subdentalrltxt;
		private String [] subdentalrl0;
		private String subdentalrl1;
		private String subdentalrl2;
		private String subdentalrl3;
		private String subdentalrl4;
		private String subdentalrl5;
		private String dntsublistoral0;
		private String dntsublistoral1;
		private String dntsublistoral2;
		private String dntsublistoral3;
		private String dntsublistoral4;
		private String dntsublistoral5;
		private String dntsublistoral6;
		private String deciduous0;
		private String deciduous1;
		private String deciduous2;
		private String deciduous3;
		private String permanent0;
		private String permanent1;
		private String permanent2;
		private String permanent3;
		private String permanent4;
		private String permanent5;
		private String treatmentDntl;

		private String carriesdecidous;
		private String missingdecidous;
		private String mobiledecidous;
		private String grosslydecadedecidous;
		private String carriespermanent;
		private String rootstumppermannet;
		private String mobilitypermanent;
		private String attritionpermanent;
		private String missingpermanent;
		private String otherpermanent;
		private String probingdepth;
	
		//Added for Patient Registration
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
	    private String fromDisp;
	    private String dispCode;		
	    
	    private String regionalLymphadenopathyDtrsTxt;
	    private String jawsDtrsTxt;
	    private String tmjDtrsTxt;
	    private String faceDtrsTxt;
	    
	    private String regionalLymphadenopathyDtrsSub;
	    private String jawsDtrsSub;
	    private String tmjDtrsSub;
	    private String faceDtrsSub;
	    
	    private String occlusionTypeTxt;
	    private String occlusionTxt;
	    private String occlusionOtherTxt;
	    private String EHS_EJHS_CardNo;
	    private String hospitalRegNo;
	    private String stage;
	    private String[] molecularMarkers;
	    private FormFile attachment;
	    private String availability;
	    private String[] drugList;
	    private String[] dosageList;
	    private String[] frequencyList;
	    private String approvalAuthority;
	    private String availabilityIndia;
	    private String palliativeSetting;
	    private String mcbsGrade;
	    private String section;
	    private String superintendentName;
	    private String crtUsr;
	    private String attachmentPath;
	    
	    //High-End Oncology Form
	    private String recommendedTreatment;
	    private String dosageBSA;
	    private String bodySurfaceArea;
	    private String startDate;
	    private String ecog;
	    private long temperature;
	    private long pulseRate;
	    private long respiratoryRate;
	    private long spo2;
	    private String bloodPressure;
	    private String generalExamination;
	    private String cardioVascular;
	    private String respiratorySystem;
	    private String nervousSystem;
	    private String abdominalExam;
	    private String musculoSkeletal;
	    private String cbp;
	    private String coagulationProfile;
	    private String pt;
	    private String aptt;
	    private String inr;
	    private String boneMarrow;
	    private String renal;
	    private String liver;
	    private String thyroid;
	    private String urine;
	    private String lipid;
	    private String echo;
	    private String pulmonary;
	    private String ecg;
	    private String cea;
	    private String ca125;
	    private String ca153;
	    private String afp;
	    private String ca199;
	    private String psa;
	    private String betahcg;
	    private String chromogranin;
	    private String cect;
	    private String mri;
	    private String petct;
	    private FormFile boneMarrowFile;
	    private FormFile renalFile;
	    private FormFile liverFile;
	    private FormFile thyroidFile;
	    private FormFile urineFile;
	    private FormFile lipidFile;
	    private FormFile echoFile;
	    private FormFile pulmonaryFile;
	    private FormFile ecgFile;
	    private FormFile ceaFile;
	    private FormFile ca125File;
	    private FormFile ca153File;
	    private FormFile afpFile;
	    private FormFile ca199File;
	    private FormFile psaFile;
	    private FormFile betahcgFile;
	    private FormFile chromograninFile;
	    private FormFile cectFile;
	    private FormFile mriFile;
	    private FormFile petctFile;
	    private String organId;
	    private String organName;
	    private String proformaId;
	    private String evalutionId;
	    //Chandana - 8755 - Added below new variables
	    private FormFile dischargeAttach[] = new FormFile[5];
	    private FormFile benAttach[] = new FormFile[5];
	    private FormFile caseSheetAttach[] = new FormFile[5];
	    private FormFile selfCertAttach[] = new FormFile[5];
	    private FormFile invAttach[] = new FormFile[5];
	    private long initiateAmount;
	    
	    //pravalika
	    private String dialysis;
	    
	    //pravalika CR 9043 admission details 
	    private String admissionDate;
	    private String admissionNote;
	    private long estAmount;
	    private FormFile delJrnlstFile;
	    private String fileName;
	    private String savedPath;
	    
		public String getProformaId() {
			return proformaId;
		}
		public void setProformaId(String proformaId) {
			this.proformaId = proformaId;
		}
		public String getEvalutionId() {
			return evalutionId;
		}
		public void setEvalutionId(String evalutionId) {
			this.evalutionId = evalutionId;
		}
		public String getOrganId() {
			return organId;
		}
		public void setOrganId(String organId) {
			this.organId = organId;
		}
		public String getOrganName() {
			return organName;
		}
		public void setOrganName(String organName) {
			this.organName = organName;
		}
		public String getRecommendedTreatment() {
			return recommendedTreatment;
		}
		public void setRecommendedTreatment(String recommendedTreatment) {
			this.recommendedTreatment = recommendedTreatment;
		}
		public String getDosageBSA() {
			return dosageBSA;
		}
		public void setDosageBSA(String dosageBSA) {
			this.dosageBSA = dosageBSA;
		}
		public String getBodySurfaceArea() {
			return bodySurfaceArea;
		}
		public void setBodySurfaceArea(String bodySurfaceArea) {
			this.bodySurfaceArea = bodySurfaceArea;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEcog() {
			return ecog;
		}
		public void setEcog(String ecog) {
			this.ecog = ecog;
		}
		public long getTemperature() {
			return temperature;
		}
		public void setTemperature(long temperature) {
			this.temperature = temperature;
		}
		public long getPulseRate() {
			return pulseRate;
		}
		public void setPulseRate(long pulseRate) {
			this.pulseRate = pulseRate;
		}
		public long getRespiratoryRate() {
			return respiratoryRate;
		}
		public void setRespiratoryRate(long respiratoryRate) {
			this.respiratoryRate = respiratoryRate;
		}
		public long getSpo2() {
			return spo2;
		}
		public void setSpo2(long spo2) {
			this.spo2 = spo2;
		}
		public String getBloodPressure() {
			return bloodPressure;
		}
		public void setBloodPressure(String bloodPressure) {
			this.bloodPressure = bloodPressure;
		}
		public String getGeneralExamination() {
			return generalExamination;
		}
		public void setGeneralExamination(String generalExamination) {
			this.generalExamination = generalExamination;
		}
		public String getCardioVascular() {
			return cardioVascular;
		}
		public void setCardioVascular(String cardioVascular) {
			this.cardioVascular = cardioVascular;
		}
		public String getRespiratorySystem() {
			return respiratorySystem;
		}
		public void setRespiratorySystem(String respiratorySystem) {
			this.respiratorySystem = respiratorySystem;
		}
		public String getNervousSystem() {
			return nervousSystem;
		}
		public void setNervousSystem(String nervousSystem) {
			this.nervousSystem = nervousSystem;
		}
		public String getAbdominalExam() {
			return abdominalExam;
		}
		public void setAbdominalExam(String abdominalExam) {
			this.abdominalExam = abdominalExam;
		}
		public String getMusculoSkeletal() {
			return musculoSkeletal;
		}
		public void setMusculoSkeletal(String musculoSkeletal) {
			this.musculoSkeletal = musculoSkeletal;
		}
		public String getCbp() {
			return cbp;
		}
		public void setCbp(String cbp) {
			this.cbp = cbp;
		}
		public String getCoagulationProfile() {
			return coagulationProfile;
		}
		public void setCoagulationProfile(String coagulationProfile) {
			this.coagulationProfile = coagulationProfile;
		}
		public String getPt() {
			return pt;
		}
		public void setPt(String pt) {
			this.pt = pt;
		}
		public String getAptt() {
			return aptt;
		}
		public void setAptt(String aptt) {
			this.aptt = aptt;
		}
		public String getInr() {
			return inr;
		}
		public void setInr(String inr) {
			this.inr = inr;
		}
		public String getBoneMarrow() {
			return boneMarrow;
		}
		public void setBoneMarrow(String boneMarrow) {
			this.boneMarrow = boneMarrow;
		}
		public String getRenal() {
			return renal;
		}
		public void setRenal(String renal) {
			this.renal = renal;
		}
		public String getLiver() {
			return liver;
		}
		public void setLiver(String liver) {
			this.liver = liver;
		}
		public String getThyroid() {
			return thyroid;
		}
		public void setThyroid(String thyroid) {
			this.thyroid = thyroid;
		}
		public String getUrine() {
			return urine;
		}
		public void setUrine(String urine) {
			this.urine = urine;
		}
		public String getLipid() {
			return lipid;
		}
		public void setLipid(String lipid) {
			this.lipid = lipid;
		}
		public String getEcho() {
			return echo;
		}
		public void setEcho(String echo) {
			this.echo = echo;
		}
		public String getPulmonary() {
			return pulmonary;
		}
		public void setPulmonary(String pulmonary) {
			this.pulmonary = pulmonary;
		}
		public String getEcg() {
			return ecg;
		}
		public void setEcg(String ecg) {
			this.ecg = ecg;
		}
		public String getCea() {
			return cea;
		}
		public void setCea(String cea) {
			this.cea = cea;
		}
		public String getCa125() {
			return ca125;
		}
		public void setCa125(String ca125) {
			this.ca125 = ca125;
		}
		public String getCa153() {
			return ca153;
		}
		public void setCa153(String ca153) {
			this.ca153 = ca153;
		}
		public String getAfp() {
			return afp;
		}
		public void setAfp(String afp) {
			this.afp = afp;
		}
		public String getCa199() {
			return ca199;
		}
		public void setCa199(String ca199) {
			this.ca199 = ca199;
		}
		public String getPsa() {
			return psa;
		}
		public void setPsa(String psa) {
			this.psa = psa;
		}
		public String getBetahcg() {
			return betahcg;
		}
		public void setBetahcg(String betahcg) {
			this.betahcg = betahcg;
		}
		public String getChromogranin() {
			return chromogranin;
		}
		public void setChromogranin(String chromogranin) {
			this.chromogranin = chromogranin;
		}
		public String getCect() {
			return cect;
		}
		public void setCect(String cect) {
			this.cect = cect;
		}
		public String getMri() {
			return mri;
		}
		public void setMri(String mri) {
			this.mri = mri;
		}
		public String getPetct() {
			return petct;
		}
		public void setPetct(String petct) {
			this.petct = petct;
		}
		public FormFile getBoneMarrowFile() {
			return boneMarrowFile;
		}
		public void setBoneMarrowFile(FormFile boneMarrowFile) {
			this.boneMarrowFile = boneMarrowFile;
		}
		public FormFile getRenalFile() {
			return renalFile;
		}
		public void setRenalFile(FormFile renalFile) {
			this.renalFile = renalFile;
		}
		public FormFile getLiverFile() {
			return liverFile;
		}
		public void setLiverFile(FormFile liverFile) {
			this.liverFile = liverFile;
		}
		public FormFile getThyroidFile() {
			return thyroidFile;
		}
		public void setThyroidFile(FormFile thyroidFile) {
			this.thyroidFile = thyroidFile;
		}
		public FormFile getUrineFile() {
			return urineFile;
		}
		public void setUrineFile(FormFile urineFile) {
			this.urineFile = urineFile;
		}
		public FormFile getLipidFile() {
			return lipidFile;
		}
		public void setLipidFile(FormFile lipidFile) {
			this.lipidFile = lipidFile;
		}
		public FormFile getEchoFile() {
			return echoFile;
		}
		public void setEchoFile(FormFile echoFile) {
			this.echoFile = echoFile;
		}
		public FormFile getPulmonaryFile() {
			return pulmonaryFile;
		}
		public void setPulmonaryFile(FormFile pulmonaryFile) {
			this.pulmonaryFile = pulmonaryFile;
		}
		public FormFile getEcgFile() {
			return ecgFile;
		}
		public void setEcgFile(FormFile ecgFile) {
			this.ecgFile = ecgFile;
		}
		public FormFile getCeaFile() {
			return ceaFile;
		}
		public void setCeaFile(FormFile ceaFile) {
			this.ceaFile = ceaFile;
		}
		public FormFile getCa125File() {
			return ca125File;
		}
		public void setCa125File(FormFile ca125File) {
			this.ca125File = ca125File;
		}
		public FormFile getCa153File() {
			return ca153File;
		}
		public void setCa153File(FormFile ca153File) {
			this.ca153File = ca153File;
		}
		public FormFile getAfpFile() {
			return afpFile;
		}
		public void setAfpFile(FormFile afpFile) {
			this.afpFile = afpFile;
		}
		public FormFile getCa199File() {
			return ca199File;
		}
		public void setCa199File(FormFile ca199File) {
			this.ca199File = ca199File;
		}
		public FormFile getPsaFile() {
			return psaFile;
		}
		public void setPsaFile(FormFile psaFile) {
			this.psaFile = psaFile;
		}
		public FormFile getBetahcgFile() {
			return betahcgFile;
		}
		public void setBetahcgFile(FormFile betahcgFile) {
			this.betahcgFile = betahcgFile;
		}
		public FormFile getChromograninFile() {
			return chromograninFile;
		}
		public void setChromograninFile(FormFile chromograninFile) {
			this.chromograninFile = chromograninFile;
		}
		public FormFile getCectFile() {
			return cectFile;
		}
		public void setCectFile(FormFile cectFile) {
			this.cectFile = cectFile;
		}
		public FormFile getMriFile() {
			return mriFile;
		}
		public void setMriFile(FormFile mriFile) {
			this.mriFile = mriFile;
		}
		public FormFile getPetctFile() {
			return petctFile;
		}
		public void setPetctFile(FormFile petctFile) {
			this.petctFile = petctFile;
		}
		
		public String getOcclusionTypeTxt() {
			return occlusionTypeTxt;
		}
		public void setOcclusionTypeTxt(String occlusionTypeTxt) {
			this.occlusionTypeTxt = occlusionTypeTxt;
		}
		public String getOcclusionTxt() {
			return occlusionTxt;
		}
		public void setOcclusionTxt(String occlusionTxt) {
			this.occlusionTxt = occlusionTxt;
		}
		public String getOcclusionOtherTxt() {
			return occlusionOtherTxt;
		}
		public void setOcclusionOtherTxt(String occlusionOtherTxt) {
			this.occlusionOtherTxt = occlusionOtherTxt;
		} 
	    
	    public String getRegionalLymphadenopathyDtrsSub() {
			return regionalLymphadenopathyDtrsSub;
		}
		public void setRegionalLymphadenopathyDtrsSub(
				String regionalLymphadenopathyDtrsSub) {
			this.regionalLymphadenopathyDtrsSub = regionalLymphadenopathyDtrsSub;
		}
		public String getJawsDtrsSub() {
			return jawsDtrsSub;
		}
		public void setJawsDtrsSub(String jawsDtrsSub) {
			this.jawsDtrsSub = jawsDtrsSub;
		}
		public String getTmjDtrsSub() {
			return tmjDtrsSub;
		}
		public void setTmjDtrsSub(String tmjDtrsSub) {
			this.tmjDtrsSub = tmjDtrsSub;
		}
		public String getFaceDtrsSub() {
			return faceDtrsSub;
		}
		public void setFaceDtrsSub(String faceDtrsSub) {
			this.faceDtrsSub = faceDtrsSub;
		}
		public String getRegionalLymphadenopathyDtrsTxt() {
			return regionalLymphadenopathyDtrsTxt;
		}
		public void setRegionalLymphadenopathyDtrsTxt(
				String regionalLymphadenopathyDtrsTxt) {
			this.regionalLymphadenopathyDtrsTxt = regionalLymphadenopathyDtrsTxt;
		}
		public String getJawsDtrsTxt() {
			return jawsDtrsTxt;
		}
		public void setJawsDtrsTxt(String jawsDtrsTxt) {
			this.jawsDtrsTxt = jawsDtrsTxt;
		}
		public String getTmjDtrsTxt() {
			return tmjDtrsTxt;
		}
		public void setTmjDtrsTxt(String tmjDtrsTxt) {
			this.tmjDtrsTxt = tmjDtrsTxt;
		}
		public String getFaceDtrsTxt() {
			return faceDtrsTxt;
		}
		public void setFaceDtrsTxt(String faceDtrsTxt) {
			this.faceDtrsTxt = faceDtrsTxt;
		}
		
		public String getFromDisp() {
			return fromDisp;
		}
		public void setFromDisp(String fromDisp) {
			this.fromDisp = fromDisp;
		}
		public String getDispCode() {
			return dispCode;
		}
		public void setDispCode(String dispCode) {
			this.dispCode = dispCode;
		}

		
		public String getDeciduous0() {
			return deciduous0;
		}
		public void setDeciduous0(String deciduous0) {
			this.deciduous0 = deciduous0;
		}
		public String getDeciduous1() {
			return deciduous1;
		}
		public void setDeciduous1(String deciduous1) {
			this.deciduous1 = deciduous1;
		}
		public String getDeciduous2() {
			return deciduous2;
		}
		public void setDeciduous2(String deciduous2) {
			this.deciduous2 = deciduous2;
		}
		public String getDeciduous3() {
			return deciduous3;
		}
		public void setDeciduous3(String deciduous3) {
			this.deciduous3 = deciduous3;
		}
		public String getPermanent0() {
			return permanent0;
		}
		public void setPermanent0(String permanent0) {
			this.permanent0 = permanent0;
		}
		public String getPermanent1() {
			return permanent1;
		}
		public void setPermanent1(String permanent1) {
			this.permanent1 = permanent1;
		}
		public String getPermanent2() {
			return permanent2;
		}
		public void setPermanent2(String permanent2) {
			this.permanent2 = permanent2;
		}
		public String getPermanent3() {
			return permanent3;
		}
		public void setPermanent3(String permanent3) {
			this.permanent3 = permanent3;
		}
		public String getPermanent4() {
			return permanent4;
		}
		public void setPermanent4(String permanent4) {
			this.permanent4 = permanent4;
		}
		public String getPermanent5() {
			return permanent5;
		}
		public void setPermanent5(String permanent5) {
			this.permanent5 = permanent5;
		}
		public String getSubdentaljaws3() {
			return subdentaljaws3;
		}
		public void setSubdentaljaws3(String subdentaljaws3) {
			this.subdentaljaws3 = subdentaljaws3;
		}
		public String getSubdentaljaws0() {
			return subdentaljaws0;
		}
		public void setSubdentaljaws0(String subdentaljaws0) {
			this.subdentaljaws0 = subdentaljaws0;
		}
		
		public String getSubdentalrl1() {
			return subdentalrl1;
		}
		public void setSubdentalrl1(String subdentalrl1) {
			this.subdentalrl1 = subdentalrl1;
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
		public String getSubdentaljaws1() {
			return subdentaljaws1;
		}
		public void setSubdentaljaws1(String subdentaljaws1) {
			this.subdentaljaws1 = subdentaljaws1;
		}
		public String getSubdentalrl4() {
			return subdentalrl4;
		}
		public void setSubdentalrl4(String subdentalrl4) {
			this.subdentalrl4 = subdentalrl4;
		}
		public String getSubdentalrl5() {
			return subdentalrl5;
		}
		public void setSubdentalrl5(String subdentalrl5) {
			this.subdentalrl5 = subdentalrl5;
		}
		public String getSubdentaljaws2() {
			return subdentaljaws2;
		}
		public void setSubdentaljaws2(String subdentaljaws2) {
			this.subdentaljaws2 = subdentaljaws2;
		}
		
		public String getSubdentalrl2() {
			return subdentalrl2;
		}
		public void setSubdentalrl2(String subdentalrl2) {
			this.subdentalrl2 = subdentalrl2;
		}
		public String getSubdentalrl3() {
			return subdentalrl3;
		}
		public void setSubdentalrl3(String subdentalrl3) {
			this.subdentalrl3 = subdentalrl3;
		}
		public List<LabelBean> getExtraoraldtls() {
			return extraoraldtls;
		}
		public void setExtraoraldtls(List<LabelBean> extraoraldtls) {
			this.extraoraldtls = extraoraldtls;
		}
		public List<LabelBean> getIntraoraldtls() {
			return intraoraldtls;
		}
		public void setIntraoraldtls(List<LabelBean> intraoraldtls) {
			this.intraoraldtls = intraoraldtls;
		}
		public List<LabelBean> getOcclusiondtls() {
			return occlusiondtls;
		}
		public void setOcclusiondtls(List<LabelBean> occlusiondtls) {
			this.occlusiondtls = occlusiondtls;
		}
		public List<LabelBean> getDeciduousdentdtls() {
			return deciduousdentdtls;
		}
		public void setDeciduousdentdtls(List<LabelBean> deciduousdentdtls) {
			this.deciduousdentdtls = deciduousdentdtls;
		}
		public List<LabelBean> getPermanentdentdtls() {
			return permanentdentdtls;
		}
		public void setPermanentdentdtls(List<LabelBean> permanentdentdtls) {
			this.permanentdentdtls = permanentdentdtls;
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
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
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
		public String getOtherPermntDent() {
			return otherPermntDent;
		}
		public void setOtherPermntDent(String otherPermntDent) {
			this.otherPermntDent = otherPermntDent;
		}
		public String getOtherPermText() {
			return otherPermText;
		}
		public void setOtherPermText(String otherPermText) {
			this.otherPermText = otherPermText;
		}
	public String getMandibleFracture() {
			return mandibleFracture;
		}
		public void setMandibleFracture(String mandibleFracture) {
			this.mandibleFracture = mandibleFracture;
		}
		public String getMaxillaFracture() {
			return maxillaFracture;
		}
		public void setMaxillaFracture(String maxillaFracture) {
			this.maxillaFracture = maxillaFracture;
		}
		public String getZygomaticFracture() {
			return zygomaticFracture;
		}
		public void setZygomaticFracture(String zygomaticFracture) {
			this.zygomaticFracture = zygomaticFracture;
		}
	public FormFile[] getUpdateGenAttach() {
			return updateGenAttach;
		}
		public void setUpdateGenAttach(FormFile[] updateGenAttach) {
			this.updateGenAttach = updateGenAttach;
		}
		public String getFace() {
			return face;
		}
		public void setFace(String face) {
			this.face = face;
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
		public String getFaceCheck() {
			return faceCheck;
		}
		public void setFaceCheck(String faceCheck) {
			this.faceCheck = faceCheck;
		}
		public String getTmjCheck() {
			return tmjCheck;
		}
		public void setTmjCheck(String tmjCheck) {
			this.tmjCheck = tmjCheck;
		}
		public String getJawsCheck() {
			return jawsCheck;
		}
		public void setJawsCheck(String jawsCheck) {
			this.jawsCheck = jawsCheck;
		}
		public String getLymphadenopathyCheck() {
			return lymphadenopathyCheck;
		}
		public void setLymphadenopathyCheck(String lymphadenopathyCheck) {
			this.lymphadenopathyCheck = lymphadenopathyCheck;
		}
	public String getMedicationGiven() {
			return medicationGiven;
		}
		public void setMedicationGiven(String medicationGiven) {
			this.medicationGiven = medicationGiven;
		}
	public String[] getCaries() {
			return caries;
		}
		public void setCaries(String[] caries) {
			this.caries = caries;
		}
	public String[] getSoftTissue() {
			return softTissue;
		}
		public void setSoftTissue(String[] softTissue) {
			this.softTissue = softTissue;
		}
		
		public String[] getChildCaries() {
			return childCaries;
		}
		public void setChildCaries(String[] childCaries) {
			this.childCaries = childCaries;
		}
		public String[] getMissingTeeth() {
			return missingTeeth;
		}
		public void setMissingTeeth(String[] missingTeeth) {
			this.missingTeeth = missingTeeth;
		}
		public String[] getDecayed() {
			return decayed;
		}
		public void setDecayed(String[] decayed) {
			this.decayed = decayed;
		}
		/*public String[] getMobile() {
			return mobile;
		}
		public void setMobile(String[] mobile) {
			this.mobile = mobile;
		}*/
		public String[] getAttrition() {
			return attrition;
		}
		public void setAttrition(String[] attrition) {
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
		public String[] getProbeDepth() {
			return probeDepth;
		}
		public void setProbeDepth(String[] probeDepth) {
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
	public String getAsriDrugCode() {
		return asriDrugCode;
	}
	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
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
	public String getAsriCatCode() {
		return asriCatCode;
	}
	public void setAsriCatCode(String asriCatCode) {
		this.asriCatCode = asriCatCode;
	}

	public String getAsriCatName() {
		return asriCatName;
	}
	public void setAsriCatName(String asriCatName) {
		this.asriCatName = asriCatName;
	}
	
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
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
	public String getMainSymptomName() {
		return mainSymptomName;
	}
	public void setMainSymptomName(String mainSymptomName) {
		this.mainSymptomName = mainSymptomName;
	}
	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
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
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getCommCheck() {
		return commCheck;
	}
	public void setCommCheck(String commCheck) {
		this.commCheck = commCheck;
	}
	public List<LabelBean> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<LabelBean> districtList) {
		this.districtList = districtList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	//Setters and Getters for IP Doc Details 
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
	//End Of Setters and Getters for IP Doc Details 
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
	public FormFile getAttach(int index) {
		return attach[index];
	}
	public void setAttach(int index,FormFile value) {
		this.attach[index] = value;
	}
	public FormFile[] getAttach() {
		return attach;
	}
	public void setAttach(FormFile[] attach) {
		this.attach = attach;
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
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public FormFile getGenAttach(int index) {
		return genAttach[index];
	}
	public void setGenAttach(int index,FormFile value) {
		this.genAttach[index] = value;
	}

	public FormFile getUpdateGenAttach(int index) {
		return updateGenAttach[index];
	}
	public void setUpdateGenAttach(int index,FormFile value) {
		this.updateGenAttach[index] = value;
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
	public FormFile[] getGenAttach() {
		return genAttach;
	}
	public void setGenAttach(FormFile[] genAttach) {
		this.genAttach = genAttach;
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
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDateIp() {
		return dateIp;
	}
	public void setDateIp(String dateIp) {
		this.dateIp = dateIp;
	}
	public String getHospaddr() {
		return hospaddr;
	}
	public void setHospaddr(String hospaddr) {
		this.hospaddr = hospaddr;
	}
	public String[] getPersonalHist() {
		return personalHist;
	}
	public void setPersonalHist(String[] personalHist) {
		this.personalHist = personalHist;
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
	public String getGenBlockInvestName() {
		return genBlockInvestName;
	}
	public void setGenBlockInvestName(String genBlockInvestName) {
		this.genBlockInvestName = genBlockInvestName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public String getAddrEnable() {
		return addrEnable;
	}
	public void setAddrEnable(String addrEnable) {
		this.addrEnable = addrEnable;
	}
	public String getTelScheme() {
		return telScheme;
	}
	public void setTelScheme(String telScheme) {
		this.telScheme = telScheme;
	}
	public String getPatientScheme() {
		return patientScheme;
	}
	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	public FormFile getChildPhoto() {
		return childPhoto;
	}
	public void setChildPhoto(FormFile childPhoto) {
		this.childPhoto = childPhoto;
	}
	public FormFile getIdentificationAttachment() {
		return identificationAttachment;
	}
	public void setIdentificationAttachment(FormFile identificationAttachment) {
		this.identificationAttachment = identificationAttachment;
	}
	public String getNewBornBaby() {
		return newBornBaby;
	}
	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}
	public String getDentalProc() {
		return dentalProc;
	}
	public void setDentalProc(String dentalProc) {
		this.dentalProc = dentalProc;
	}
	public String getDentalSpecApproval() {
		return dentalSpecApproval;
	}
	public void setDentalSpecApproval(String dentalSpecApproval) {
		this.dentalSpecApproval = dentalSpecApproval;
	}
	public long getInvestPrice() {
		return investPrice;
	}
	public void setInvestPrice(long investPrice) {
		this.investPrice = investPrice;
	}
	public long getTotInvestPrice() {
		return totInvestPrice;
	}
	public void setTotInvestPrice(long totInvestPrice) {
		this.totInvestPrice = totInvestPrice;
	}
	public long getConsultFee() {
		return consultFee;
	}
	public void setConsultFee(long consultFee) {
		this.consultFee = consultFee;
	}
	public long getTotalOpCost() {
		return totalOpCost;
	}
	public void setTotalOpCost(long totalOpCost) {
		this.totalOpCost = totalOpCost;
	}
	public String getOtherInvName() {
		return otherInvName;
	}
	public void setOtherInvName(String otherInvName) {
		this.otherInvName = otherInvName;
	}
	public String getOtherInvBlockName() {
		return otherInvBlockName;
	}
	public void setOtherInvBlockName(String otherInvBlockName) {
		this.otherInvBlockName = otherInvBlockName;
	}
	public String getInvestOthers() {
		return investOthers;
	}
	public void setInvestOthers(String investOthers) {
		this.investOthers = investOthers;
	}
	public String getDrugsOthers() {
		return drugsOthers;
	}
	public void setDrugsOthers(String drugsOthers) {
		this.drugsOthers = drugsOthers;
	}
	public String getOtherDrugName() {
		return otherDrugName;
	}
	public void setOtherDrugName(String otherDrugName) {
		this.otherDrugName = otherDrugName;
	}
	public int getOtherInvestCount() {
		return otherInvestCount;
	}
	public void setOtherInvestCount(int otherInvestCount) {
		this.otherInvestCount = otherInvestCount;
	}
	public int getOtherDrugCount() {
		return otherDrugCount;
	}
	public void setOtherDrugCount(int otherDrugCount) {
		this.otherDrugCount = otherDrugCount;
	}
	public String getOpActiveMsg() {
		return opActiveMsg;
	}
	public void setOpActiveMsg(String opActiveMsg) {
		this.opActiveMsg = opActiveMsg;
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
	public String getChronicNo() {
		return chronicNo;
	}
	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
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
	public String getCeoRemark() {
		return ceoRemark;
	}
	public void setCeoRemark(String ceoRemark) {
		this.ceoRemark = ceoRemark;
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
	public String getOtherComplaint() {
		return otherComplaint;
	}
	public void setOtherComplaint(String otherComplaint) {
		this.otherComplaint = otherComplaint;
	}
	public String getOtherSymptomName() {
		return otherSymptomName;
	}
	public void setOtherSymptomName(String otherSymptomName) {
		this.otherSymptomName = otherSymptomName;
	}
	public String getOtherSymptoms() {
		return otherSymptoms;
	}
	public void setOtherSymptoms(String otherSymptoms) {
		this.otherSymptoms = otherSymptoms;
	}
	public int getOtherSymptomCount() {
		return otherSymptomCount;
	}
	public void setOtherSymptomCount(int otherSymptomCount) {
		this.otherSymptomCount = otherSymptomCount;
	}
	public List<LabelBean> getConsultData() {
		return consultData;
	}
	public void setConsultData(List<LabelBean> consultData) {
		this.consultData = consultData;
	}
	public List<CasesSearchVO> getLstCaseSearch() {
		return lstCaseSearch;
	}
	public void setLstCaseSearch(List<CasesSearchVO> lstCaseSearch) {
		this.lstCaseSearch = lstCaseSearch;
	}
	public String getBioFinger() {
		return bioFinger;
	}
	public void setBioFinger(String bioFinger) {
		this.bioFinger = bioFinger;
	}
	public String getBiometricValue() {
		return biometricValue;
	}
	public void setBiometricValue(String biometricValue) {
		this.biometricValue = biometricValue;
	}
	public String getBioCaptureDtlsYN() {
		return bioCaptureDtlsYN;
	}
	public void setBioCaptureDtlsYN(String bioCaptureDtlsYN) {
		this.bioCaptureDtlsYN = bioCaptureDtlsYN;
	}

	public String getTgGovPatCond() {
		return tgGovPatCond;
	}
	public void setTgGovPatCond(String tgGovPatCond) {
		this.tgGovPatCond = tgGovPatCond;
	}
	public String getDiagOthers() {
		return diagOthers;
	}
	public void setDiagOthers(String diagOthers) {
		this.diagOthers = diagOthers;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getDiagOthersName() {
		return diagOthersName;
	}
	public void setDiagOthersName(String diagOthersName) {
		this.diagOthersName = diagOthersName;
	}
	public String getSelectedDrug() {
		return selectedDrug;
	}
	public void setSelectedDrug(String selectedDrug) {
		this.selectedDrug = selectedDrug;
	}
	public String getFingerPrint() {
		return fingerPrint;
	}
	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	public String getDiagCondition() {
		return diagCondition;
	}
	public void setDiagCondition(String diagCondition) {
		this.diagCondition = diagCondition;
	}

	public String getBioHand() {
		return bioHand;
	}
	public void setBioHand(String bioHand) {
		this.bioHand = bioHand;
	}
	public String getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getHosType() {
		return hosType;
	}
	public void setHosType(String hosType) {
		this.hosType = hosType;
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
	public String getPermanentDent() {
		return permanentDent;
	}
	public void setPermanentDent(String permanentDent) {
		this.permanentDent = permanentDent;
	}
	public String getDeciduousDent() {
		return deciduousDent;
	}
	public void setDeciduousDent(String deciduousDent) {
		this.deciduousDent = deciduousDent;
	}
	public String getLymphapathyType() {
		return lymphapathyType;
	}
	public void setLymphapathyType(String lymphapathyType) {
		this.lymphapathyType = lymphapathyType;
	}
	public String getLymphapathyText() {
		return lymphapathyText;
	}
	public void setLymphapathyText(String lymphapathyText) {
		this.lymphapathyText = lymphapathyText;
	}
	public String[] getExtraoral() {
		return extraoral;
	}
	public void setExtraoral(String[] extraoral) {
		this.extraoral = extraoral;
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
	
	
	public List<LabelBean> getDentalsublist() {
		return Dentalsublist;
	}
	public void setDentalsublist(List<LabelBean> dentalsublist) {
		Dentalsublist = dentalsublist;
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
	public String getSubdental0() {
		return Subdental0;
	}
	public void setSubdental0(String subdental0) {
		Subdental0 = subdental0;
	}
	public String[] getIntraoral() {
		return intraoral;
	}
	public void setIntraoral(String[] intraoral) {
		this.intraoral = intraoral;
	}
	public String[] getDntsublistoral() {
		return dntsublistoral;
	}
	public void setDntsublistoral(String[] dntsublistoral) {
		this.dntsublistoral = dntsublistoral;
	}
	public String getSubdentaljawstxt() {
		return subdentaljawstxt;
	}
	public void setSubdentaljawstxt(String subdentaljawstxt) {
		this.subdentaljawstxt = subdentaljawstxt;
	}
	public String getSubdentalrltxt() {
		return subdentalrltxt;
	}
	public void setSubdentalrltxt(String subdentalrltxt) {
		this.subdentalrltxt = subdentalrltxt;
	}
	public String[] getDeciduous() {
		return deciduous;
	}
	public void setDeciduous(String[] deciduous) {
		this.deciduous = deciduous;
	}
	public String[] getPermanent() {
		return permanent;
	}
	public void setPermanent(String[] permanent) {
		this.permanent = permanent;
	}
	public List<LabelBean> getMedicalhsitorydtls() {
		return medicalhsitorydtls;
	}
	public void setMedicalhsitorydtls(List<LabelBean> medicalhsitorydtls) {
		this.medicalhsitorydtls = medicalhsitorydtls;
	}
	public String[] getMedicalDtlsid() {
		return medicalDtlsid;
	}
	public void setMedicalDtlsid(String[] medicalDtlsid) {
		this.medicalDtlsid = medicalDtlsid;
	}
	public String getTreatmentDntl() {
		return treatmentDntl;
	}
	public void setTreatmentDntl(String treatmentDntl) {
		this.treatmentDntl = treatmentDntl;
	}
	public String getShowMedicalTextval() {
		return showMedicalTextval;
	}
	public void setShowMedicalTextval(String showMedicalTextval) {
		this.showMedicalTextval = showMedicalTextval;
	}
	public String [] getSubdentalrl0() {
		return subdentalrl0;
	}
	public void setSubdentalrl0(String [] subdentalrl0) {
		this.subdentalrl0 = subdentalrl0;
	}
	public List<LabelBean> getDentalmainlist0() {
		return Dentalmainlist0;
	}
	public void setDentalmainlist0(List<LabelBean> dentalmainlist0) {
		Dentalmainlist0 = dentalmainlist0;
	}
	public List<LabelBean> getDentalmainlist1() {
		return Dentalmainlist1;
	}
	public void setDentalmainlist1(List<LabelBean> dentalmainlist1) {
		Dentalmainlist1 = dentalmainlist1;
	}
	public List<LabelBean> getDentalmainlist2() {
		return Dentalmainlist2;
	}
	public void setDentalmainlist2(List<LabelBean> dentalmainlist2) {
		Dentalmainlist2 = dentalmainlist2;
	}
	public List<LabelBean> getDentalmainlist3() {
		return Dentalmainlist3;
	}
	public void setDentalmainlist3(List<LabelBean> dentalmainlist3) {
		Dentalmainlist3 = dentalmainlist3;
	}
	public List<LabelBean> getDentalsublistjaws1() {
		return Dentalsublistjaws1;
	}
	public void setDentalsublistjaws1(List<LabelBean> dentalsublistjaws1) {
		Dentalsublistjaws1 = dentalsublistjaws1;
	}
	public List<LabelBean> getDentalsublistrl0() {
		return Dentalsublistrl0;
	}
	public void setDentalsublistrl0(List<LabelBean> dentalsublistrl0) {
		Dentalsublistrl0 = dentalsublistrl0;
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
	public String getMedicalHistVal() {
		return medicalHistVal;
	}
	public void setMedicalHistVal(String medicalHistVal) {
		this.medicalHistVal = medicalHistVal;
	}
	public String getMedicalCat() {
		return medicalCat;
	}
	public void setMedicalCat(String medicalCat) {
		this.medicalCat = medicalCat;
	}
	public String getMedicalDrug() {
		return medicalDrug;
	}
	public void setMedicalDrug(String medicalDrug) {
		this.medicalDrug = medicalDrug;
	}
	public String getMedicalSpclty() {
		return medicalSpclty;
	}
	public void setMedicalSpclty(String medicalSpclty) {
		this.medicalSpclty = medicalSpclty;
	}
	public String getStateType() {
		return stateType;
	}
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}
	public String getEHS_EJHS_CardNo() {
		return EHS_EJHS_CardNo;
	}
	public void setEHS_EJHS_CardNo(String eHS_EJHS_CardNo) {
		EHS_EJHS_CardNo = eHS_EJHS_CardNo;
	}
	public String getHospitalRegNo() {
		return hospitalRegNo;
	}
	public void setHospitalRegNo(String hospitalRegNo) {
		this.hospitalRegNo = hospitalRegNo;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String[] getMolecularMarkers() {
		return molecularMarkers;
	}
	public void setMolecularMarkers(String[] molecularMarkers) {
		this.molecularMarkers = molecularMarkers;
	}
	public FormFile getAttachment() {
		return attachment;
	}
	public void setAttachment(FormFile attachment) {
		this.attachment = attachment;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String[] getDrugList() {
		return drugList;
	}
	public void setDrugList(String[] drugList) {
		this.drugList = drugList;
	}
	public String[] getDosageList() {
		return dosageList;
	}
	public void setDosageList(String[] dosageList) {
		this.dosageList = dosageList;
	}
	public String[] getFrequencyList() {
		return frequencyList;
	}
	public void setFrequencyList(String[] frequencyList) {
		this.frequencyList = frequencyList;
	}
	public String getApprovalAuthority() {
		return approvalAuthority;
	}
	public void setApprovalAuthority(String approvalAuthority) {
		this.approvalAuthority = approvalAuthority;
	}
	public String getAvailabilityIndia() {
		return availabilityIndia;
	}
	public void setAvailabilityIndia(String availabilityIndia) {
		this.availabilityIndia = availabilityIndia;
	}
	public String getPalliativeSetting() {
		return palliativeSetting;
	}
	public void setPalliativeSetting(String palliativeSetting) {
		this.palliativeSetting = palliativeSetting;
	}
	public String getMcbsGrade() {
		return mcbsGrade;
	}
	public void setMcbsGrade(String mcbsGrade) {
		this.mcbsGrade = mcbsGrade;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSuperintendentName() {
		return superintendentName;
	}
	public void setSuperintendentName(String superintendentName) {
		this.superintendentName = superintendentName;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	//Chandana - 8326 - for Abha
	private String abhaDoneYN;
	private String errMsgForEkyc;
	private String abhaId;
	private String ekycFlag;
	private String UserName;
	private String action;//Chandana - 8441 - Added this variable
	public String getAbhaDoneYN() {
		return abhaDoneYN;
	}
	public void setAbhaDoneYN(String abhaDoneYN) {
		this.abhaDoneYN = abhaDoneYN;
	}
	public String getErrMsgForEkyc() {
		return errMsgForEkyc;
	}
	public void setErrMsgForEkyc(String errMsgForEkyc) {
		this.errMsgForEkyc = errMsgForEkyc;
	}
	public String getAbhaId() {
		return abhaId;
	}
	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}
	public String getEkycFlag() {
		return ekycFlag;
	}
	public void setEkycFlag(String ekycFlag) {
		this.ekycFlag = ekycFlag;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	//Chandana - 8441 - Added getters and setter
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}	
	private String crNumber;//Chandana - 8606 - Added this new variable
	public String getCrNumber() {
		return crNumber;
	}
	public void setCrNumber(String crNumber) {
		this.crNumber = crNumber;
	}
	public String getDialysis() {
		return dialysis;
	}
	public void setDialysis(String dialysis) {
		this.dialysis = dialysis;
	}
	//Chandana - 8755 - Getter and setter
	public FormFile getDischargeAttach(int index){
	    	return dischargeAttach[index];
	    }
	    public FormFile setDischargeAttach(int index, FormFile value){
	    	return dischargeAttach[index] = value;
	    }
		public FormFile[] getDischargeAttach() {
			return dischargeAttach;
		}
		public void setDischargeAttach(FormFile[] dischargeAttach) {
			this.dischargeAttach = dischargeAttach;
		}
		
		public FormFile getBenAttach(int index){
	    	return benAttach[index];
	    }
	    public FormFile setBenAttach(int index, FormFile value){
	    	return benAttach[index] = value;
	    }
		public FormFile[] getBenAttach() {
			return benAttach;
		}
		public void setBenAttach(FormFile[] benAttach) {
			this.benAttach = benAttach;
		}
		
		public FormFile getCaseSheetAttach(int index){
	    	return caseSheetAttach[index];
	    }
	    public FormFile setCaseSheetAttach(int index, FormFile value){
	    	return caseSheetAttach[index] = value;
	    }
		public FormFile[] getCaseSheetAttach() {
			return caseSheetAttach;
		}
		public void setCaseSheetAttach(FormFile[] caseSheetAttach) {
			this.caseSheetAttach = caseSheetAttach;
		}
		
		public FormFile getSelfCertAttach(int index){
	    	return selfCertAttach[index];
	    }
	    public FormFile setSelfCertAttach(int index, FormFile value){
	    	return selfCertAttach[index] = value;
	    }
		public FormFile[] getSelfCertAttach() {
			return selfCertAttach;
		}
		public void setSelfCertAttach(FormFile[] selfCertAttach) {
			this.selfCertAttach = selfCertAttach;
		}
		
		public FormFile getInvAttach(int index){
	    	return invAttach[index];
	    }
	    public FormFile setInvAttach(int index, FormFile value){
	    	return invAttach[index] = value;
	    }
		public FormFile[] getInvAttach() {
			return invAttach;
		}
		public void setInvAttach(FormFile[] invAttach) {
			this.invAttach = invAttach;
		}
		public long getInitiateAmount() {
			return initiateAmount;
		}
		public void setInitiateAmount(long initiateAmount) {
			this.initiateAmount = initiateAmount;
		}
	
public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getAdmissionNote() {
		return admissionNote;
	}
	public void setAdmissionNote(String admissionNote) {
		this.admissionNote = admissionNote;
	}
	public long getEstAmount() {
		return estAmount;
	}
	public void setEstAmount(long estAmount) {
		this.estAmount = estAmount;
	}
	public FormFile getDelJrnlstFile() {
		return delJrnlstFile;
	}
	public void setDelJrnlstFile(FormFile delJrnlstFile) {
		this.delJrnlstFile = delJrnlstFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSavedPath() {
		return savedPath;
	}
	public void setSavedPath(String savedPath) {
		this.savedPath = savedPath;
	}
}