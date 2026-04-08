package com.ahct.patient.vo;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.ahct.chronicOp.vo.ChronicOPVO;

import com.ahct.common.vo.LabelBean;


public class PreauthVO implements java.io.Serializable
{
	private String test;
	
	private String testKnown;
	private String examFindg;
	private List<PreauthVO> invList;
	private List<DrugsVO> drugList;
	private List<ChronicOPVO> therapyList;
	private String dentalFlg;
	private String ipOpFlag;
	private String admType;
	private static final String EMPTY_STRING="";
	private static final ArrayList EMPTY_LIST = new ArrayList();
	private static final String ZERO_STRING = "0";
	private static final HashMap EMPTY_MAP = new HashMap();
	private static final int ZERO_VAL = 0;
	private String caseId=EMPTY_STRING;
	private String patage=EMPTY_STRING;
	private String months=EMPTY_STRING;//025

	private String patientName=EMPTY_STRING;
	private String rationCard=EMPTY_STRING;
	private String cardType=EMPTY_STRING;
	private String age=EMPTY_STRING;
	private String gender=EMPTY_STRING;
	private String patientIPNo=EMPTY_STRING;
	private String contactNo=EMPTY_STRING;
	private String caseNo=EMPTY_STRING;
	private String scheme=EMPTY_STRING;
	private String schemeDesc=EMPTY_STRING;
	private String claimNo=EMPTY_STRING;
	private String address1=EMPTY_STRING;
	private String address2=EMPTY_STRING;
	private String regHospDate=EMPTY_STRING;

	private String patientID=EMPTY_STRING;
	private String caseStatusId=EMPTY_STRING;
	private String caseStatusDesc=EMPTY_STRING;
	private String srcRegistration=EMPTY_STRING;
	private String hospitalCode=EMPTY_STRING;
	private String hospType=EMPTY_STRING;
	private String hospDispCode=EMPTY_STRING;
	private String hospAddress=EMPTY_STRING;
	private String hospContactNo=EMPTY_STRING;
	private String hospitalName=EMPTY_STRING;
	private String disMainName=EMPTY_STRING;
	private String surgeryName=EMPTY_STRING;
	private String disMainId=EMPTY_STRING;
	private String surgeryId=EMPTY_STRING;
	private String caseStatusDt=EMPTY_STRING;
	private String phase=EMPTY_STRING;
	private String telephonicId=EMPTY_STRING;
	private String telInitDt=EMPTY_STRING;
	private ArrayList associatedCases=EMPTY_LIST;
	private String preauthDt=EMPTY_STRING;
	private String surgeryDt=EMPTY_STRING;
	private String patCrtDt=EMPTY_STRING;
	private ArrayList disMainLst=EMPTY_LIST;
	private ArrayList surgeryLst=EMPTY_LIST;
	private String doctorStat=EMPTY_STRING;
	private String doctorDtls=EMPTY_STRING;
	private String caseCrtDt=EMPTY_STRING;
	private String updType=EMPTY_STRING;
	private String caste=EMPTY_STRING;
	private String pincode=EMPTY_STRING;
	private String mitName=EMPTY_STRING;
	private String mitDate=EMPTY_STRING;
	private String caseAprvDt=EMPTY_STRING;//015
	private String telSurgId = EMPTY_STRING;//017
	private String patRegDate=EMPTY_STRING;//020
	private String auditName=EMPTY_STRING;
	private String auditDate=EMPTY_STRING;
	private String auditRole=EMPTY_STRING;
	private String auditRemarks=EMPTY_STRING;
	private String consultant;
	private String regNo;
	private String disSubId;
	private String splInvstId;
	private String spltType;
	private String cruUsr;

	// added for preauth drug details
	private String name;
	private String route;
	private String unitPrice;
	private String strength;
	private String dosage;
	private String days;
	private String stayType;
	private String aols_av;

	private String telephonicRemarks;
	private String telephonicFlag;
	private String amount;
	private String investRemarks;
	private String caseSurgId; 
	private String therapyType;
	private String remarks;

	private String enrollCardNo;
	private String patCardNo;
	private String enrollName;
	private String patName;
	private String enrollAge;
	private String patAge;
	private String enrollSex;
	private String patSex;
	private String enrollPotoAttach;
	private String patPotoAttch;
	private String enrollId;
	private String submitType;
	private String submitValue;

	private String actId;
	private String actby;
	private Date date;
	private String cmbDtlname;
	private String apprvdPckAmt;
	private String ipRegDate;
	private String totPackgAmt;
	private String auditAmt;
	private String enhancementFlag;
	private String enhReqAmt;
	private String enhApprvAmt;
	private String enhRemarks;
	private String enhCaseStatus;
	private String cardVillage;
	private String cardMandal;
	private String cardDistrict;
	private String cardPinNo;
	private String cardHNo;
	private String cardStreet;
	private String familyHistValue;
	private String medHistVal;
	private String MedicalHistoryOthr;
	private String symptoms;
	private String doctorSpeciality=EMPTY_STRING;
	private String hospDistrict;
	private String hospitalId;
	private String legalCaseCheck;
	private String legalCaseNo;
	private String policeStatName;

	private String opProcUnits;
	/**
	 * 
	 * IP-Op tab details
	 */
	private String cardIssueDt;
	private String refCardNo=EMPTY_STRING;
	private String occName;
	private String relationName;
	private String houseNo;
	private String street;
	private String hamlet=EMPTY_STRING;
	private String village=EMPTY_STRING;
	private String mandal=EMPTY_STRING;
	private String district=EMPTY_STRING;
	private String complaintType;
	private String complaintCode;
	private String patComplaint;
	private String patCompCode;
	private String presentIllness;
	private String pastIllness;
	private String pastIllnessValue;
	private String personalHis;
	private String familyHis;
	private String height;
	private String weight;
	private String bmi;
	private String pallor;
	private String cyanosis;
	private String clubbingOfFingers;
	private String lymphadenopathy;
	private String edema;
	private String malNutrition;
	private String dehydration;
	private String temperature;
	private String pulseRate;
	private String respirationRate;
	private String bpLmt;
	private String bpRmt;
	private String docName;
	private String docMobNo;
	private String docReg;
	private String docQual;
	private String docType;
	private String disSubName;
	private String therapyId;
	private String therapyName;
	private String patientType;

	private String catId;
	private String icdCatCode;
	private String procCode;
	private String icdProcCode;
	private String asriCatName;
	private String procName;
	private String hospStayAmt;
	private String commonCatAmt;
	private String icdAmt;




	private String seqNo;
	private String filename;
	private String filePath;
	private List<LabelBean> lstPersonalHistory;
	private List<PreauthVO> lstSplInvet;
	private String diagnosisType;
	private String mainCatName;
	private String catName;
	private String subCatName;
	private String disName;
	private String disAnatomicalSitename;
	private String langId;
	private String symCode;
	private String symName;
	private String mainSymCode;
	private String mainSymName;

	private String familyHistoryOthr;
	private String pastIllenesOthr;
	private String pastIllenesCancers;
	private String pastIllenesEndDis;
	private String pastIllenesSurg;

	private String dehydrationType;
	private Integer noOfDays;
	
	private String opCatCode;
	private String opPkgCode;
	private String opIcdCode;
	private String opCatName;
    private String opPackageName;
    private String opIcdName;
    private String complaintTypeName;
    private String complaintName;
    private String installment;
    private String newBornBaby;

    private BigDecimal PREAUTHAPPVAMT;
    private BigDecimal COMORBIDAPPVAMT;
    private BigDecimal ENHAPPVAMT;
    private long totAmount;
    
    private String nabhAmt;
    private String enhFlag;
    private String slabAmt;


    private String startDt;
    private String endDt;
    private String process;
    private String dentalLimit;
    private int dentalLimitCount;
    private int procCount;
    private int flpCount;
    private String surgCount;
    private String flpSurgCount;
    private String ceoApprovalFlag;
    
    private String PastHisOthers;
    private String FamilyHisOthers;
    private String price;
    private String addedProcs;
    private String comboProcCode;
    private String nonComboProcCode;
    
    private String TESTKNOWN;
    private String TEST;
    private String NAME;
    private String SPLINVSTID;
    private String ROUTE;
    private String PRICE;
    private String INSTALLMENT;
    
    
    private Number AGE;
    private String ageLimit;
    private String unitName;
    private String unitHodName;
    private String SYMPTOMS;
    
    private String postDist;
    private String stoCode;
    private String ddoCode;
    private String deptHod;
    
    List<ChronicOPVO> chronicPkgLst;
    


    /*
     * Added By Srikalyan for TG dental Changes
     */
    private String activeYn;
	private String frameworkPrice;
	private String ipOp;
	private String lifetimeUnitsLimit;
	private String standaloneProc;
	private String standaloneProcNames;
	private String subsequentPrice;
	private String unitsLimit;
    private String lifeTimeUnitsLeft;
    private String ageLimitMsg;
	private String lifeTimeMonths;
	private String ageMsg;
	private String actualUnitsLeft;
	private String comboProcNames;
	private String comboNonProcNames;
	

    private String otherComplaintName;
    private String otherDiagName;
    
    //added by pavan for dental case sheet cr
    private String treatmentDntl;
    private String drugHst;
    private String drugHstVal;
    private String[] medicalDtlsid;
    private String medicalDtlsidStr;
    private String showMedicalTextval;
    private String[] extraoral;
    private String extraoralStr;
	private String[] intraoral;
	private String intraoralStr;
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
	private String subdentalrl0Str;
	private String subdentaljaws1;
	private String subdentalrltxt;
	private String subdentaljawstxt;
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
	private String previousDentalTreatment;
	private String occlusion;
	private String occlusionType;
	private String occlusionOther;
	private String advancedInvestigations;
	private String admissionDetails;
	private String followupAdv;
	private String diagnosis;
	private String medicationGiven;
	
	private String regionalLymphadenopathyDtrsTxt;
    private String jawsDtrsTxt;
    private String tmjDtrsTxt;
    private String faceDtrsTxt;
	
    
    
    private String regionalLymphadenopathyDtrsSub;
    private String jawsDtrsSub;
    private String tmjDtrsSub;
    private String faceDtrsSub;
    
    
    
    private String swSite;
    private String swSize;
    private String swExtension;
    private String swColour;
    private String swConsistency;
    private String swTenderness;
    private String swBorders;
    private String psSite;
    private String psDischarge;
    private String occlusionTypeTxt;
    private String occlusionTxt;
    private String occlusionOtherTxt;
    
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
	public String getDentalFlg() {
		return dentalFlg;
	}
	public void setDentalFlg(String dentalFlg) {
		this.dentalFlg = dentalFlg;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDoctorSpeciality() {
		return doctorSpeciality;
	}
	public void setDoctorSpeciality(String doctorSpeciality) {
		this.doctorSpeciality = doctorSpeciality;
	}
	public String getHospDistrict() {
		return hospDistrict;
	}
	public void setHospDistrict(String hospDistrict) {
		this.hospDistrict = hospDistrict;
	}
	public String getCardHNo() {
		return cardHNo;
	}
	public void setCardHNo(String cardHNo) {
		this.cardHNo = cardHNo;
	}
	public String getCardStreet() {
		return cardStreet;
	}
	public void setCardStreet(String cardStreet) {
		this.cardStreet = cardStreet;
	}
	public String getCardVillage() {
		return cardVillage;
	}
	public void setCardVillage(String cardVillage) {
		this.cardVillage = cardVillage;
	}
	public String getCardMandal() {
		return cardMandal;
	}
	public void setCardMandal(String cardMandal) {
		this.cardMandal = cardMandal;
	}
	public String getCardDistrict() {
		return cardDistrict;
	}
	public void setCardDistrict(String cardDistrict) {
		this.cardDistrict = cardDistrict;
	}
	public String getCardPinNo() {
		return cardPinNo;
	}
	public void setCardPinNo(String cardPinNo) {
		this.cardPinNo = cardPinNo;
	}

	public String getEnhCaseStatus() {
		return enhCaseStatus;
	}

	public void setEnhCaseStatus(String enhCaseStatus) {
		this.enhCaseStatus = enhCaseStatus;
	}

	public String getEnhReqAmt() {
		return enhReqAmt;
	}

	public void setEnhReqAmt(String enhReqAmt) {
		this.enhReqAmt = enhReqAmt;
	}

	public String getEnhApprvAmt() {
		return enhApprvAmt;
	}

	public void setEnhApprvAmt(String enhApprvAmt) {
		this.enhApprvAmt = enhApprvAmt;
	}

	public String getEnhRemarks() {
		return enhRemarks;
	}

	public void setEnhRemarks(String enhRemarks) {
		this.enhRemarks = enhRemarks;
	}

	public String getEnhancementFlag() {
		return enhancementFlag;
	}

	public void setEnhancementFlag(String enhancementFlag) {
		this.enhancementFlag = enhancementFlag;
	}

	public String getAuditAmt() {
		return auditAmt;
	}

	public void setAuditAmt(String auditAmt) {
		this.auditAmt = auditAmt;
	}

	public String getTotPackgAmt() {
		return totPackgAmt;
	}

	public void setTotPackgAmt(String totPackgAmt) {
		this.totPackgAmt = totPackgAmt;
	}

	public String getIpRegDate() {
		return ipRegDate;
	}

	public void setIpRegDate(String ipRegDate) {
		this.ipRegDate = ipRegDate;
	}

	public String getApprvdPckAmt() {
		return apprvdPckAmt;
	}

	public void setApprvdPckAmt(String apprvdPckAmt) {
		this.apprvdPckAmt = apprvdPckAmt;
	}
	public String getDehydrationType() {
		return dehydrationType;
	}
	public void setDehydrationType(String dehydrationType) {
		this.dehydrationType = dehydrationType;
	}
	public String getPastIllenesOthr() {
		return pastIllenesOthr;
	}

	public void setPastIllenesOthr(String pastIllenesOthr) {
		this.pastIllenesOthr = pastIllenesOthr;
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
	public String getFamilyHistoryOthr() {
		return familyHistoryOthr;
	}

	public void setFamilyHistoryOthr(String familyHistoryOthr) {
		this.familyHistoryOthr = familyHistoryOthr;
	}

	public String getAsriCatName() {
		return asriCatName;
	}

	public void setAsriCatName(String asriCatName) {
		this.asriCatName = asriCatName;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getHospStayAmt() {
		return hospStayAmt;
	}

	public void setHospStayAmt(String hospStayAmt) {
		this.hospStayAmt = hospStayAmt;
	}

	public String getCommonCatAmt() {
		return commonCatAmt;
	}

	public void setCommonCatAmt(String commonCatAmt) {
		this.commonCatAmt = commonCatAmt;
	}

	public String getIcdAmt() {
		return icdAmt;
	}

	public void setIcdAmt(String icdAmt) {
		this.icdAmt = icdAmt;
	}

	public String getSymCode() {
		return symCode;
	}

	public void setSymCode(String symCode) {
		this.symCode = symCode;
	}

	public String getSymName() {
		return symName;
	}

	public void setSymName(String symName) {
		this.symName = symName;
	}

	public String getMainSymCode() {
		return mainSymCode;
	}

	public void setMainSymCode(String mainSymCode) {
		this.mainSymCode = mainSymCode;
	}

	public String getMainSymName() {
		return mainSymName;
	}

	public void setMainSymName(String mainSymName) {
		this.mainSymName = mainSymName;
	}

	private List<String> lstPerHis;


	public List<String> getLstPerHis() {
		return lstPerHis;
	}

	public void setLstPerHis(List<String> lstPerHis) {
		this.lstPerHis = lstPerHis;
	}

	private String remEnhList;

	private String enhApprAmt;


	public String getEnhApprAmt() {
		return enhApprAmt;
	}

	public void setEnhApprAmt(String enhApprAmt) {
		this.enhApprAmt = enhApprAmt;
	}

	public String getRemEnhList() {
		return remEnhList;
	}

	public void setRemEnhList(String remEnhList) {
		this.remEnhList = remEnhList;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
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

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public String getDisAnatomicalSitename() {
		return disAnatomicalSitename;
	}

	public void setDisAnatomicalSitename(String disAnatomicalSitename) {
		this.disAnatomicalSitename = disAnatomicalSitename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<PreauthVO> getLstSplInvet() {
		return lstSplInvet;
	}

	public void setLstSplInvet(List<PreauthVO> lstSplInvet) {
		this.lstSplInvet = lstSplInvet;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getIcdCatCode() {
		return icdCatCode;
	}

	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}

	public String getProcCode() {
		return procCode;
	}

	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}

	public String getIcdProcCode() {
		return icdProcCode;
	}

	public void setIcdProcCode(String icdProcCode) {
		this.icdProcCode = icdProcCode;
	}

	private List<PreauthVO> lstPreauthVO;




	public List<PreauthVO> getLstPreauthVO() {
		return lstPreauthVO;
	}

	public void setLstPreauthVO(List<PreauthVO> lstPreauthVO) {
		this.lstPreauthVO = lstPreauthVO;
	}

	public String getTherapyId() {
		return therapyId;
	}

	public void setTherapyId(String therapyId) {
		this.therapyId = therapyId;
	}

	public String getTherapyName() {
		return therapyName;
	}

	public void setTherapyName(String therapyName) {
		this.therapyName = therapyName;
	}

	public String getDisSubName() {
		return disSubName;
	}

	public void setDisSubName(String disSubName) {
		this.disSubName = disSubName;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocMobNo() {
		return docMobNo;
	}

	public void setDocMobNo(String docMobNo) {
		this.docMobNo = docMobNo;
	}

	public String getDocReg() {
		return docReg;
	}

	public void setDocReg(String docReg) {
		this.docReg = docReg;
	}

	public String getDocQual() {
		return docQual;
	}

	public void setDocQual(String docQual) {
		this.docQual = docQual;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public static int getZeroVal() {
		return ZERO_VAL;
	}

	public String getOccName() {
		return occName;
	}

	public void setOccName(String occName) {
		this.occName = occName;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
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

	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaintCode() {
		return complaintCode;
	}

	public void setComplaintCode(String complaintCode) {
		this.complaintCode = complaintCode;
	}

	public String getPatComplaint() {
		return patComplaint;
	}

	public void setPatComplaint(String patComplaint) {
		this.patComplaint = patComplaint;
	}

	public String getPatCompCode() {
		return patCompCode;
	}

	public void setPatCompCode(String patCompCode) {
		this.patCompCode = patCompCode;
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

	public String getPersonalHis() {
		return personalHis;
	}

	public void setPersonalHis(String personalHis) {
		this.personalHis = personalHis;
	}

	public String getFamilyHis() {
		return familyHis;
	}

	public void setFamilyHis(String familyHis) {
		this.familyHis = familyHis;
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

	public String getLymphadenopathy() {
		return lymphadenopathy;
	}

	public void setLymphadenopathy(String lymphadenopathy) {
		this.lymphadenopathy = lymphadenopathy;
	}

	public String getEdema() {
		return edema;
	}

	public void setEdema(String edema) {
		this.edema = edema;
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

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getActby() {
		return actby;
	}

	public void setActby(String actby) {
		this.actby = actby;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCmbDtlname() {
		return cmbDtlname;
	}

	public void setCmbDtlname(String cmbDtlname) {
		this.cmbDtlname = cmbDtlname;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getSubmitValue() {
		return submitValue;
	}

	public void setSubmitValue(String submitValue) {
		this.submitValue = submitValue;
	}

	public String getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}

	public String getEnrollPotoAttach() {
		return enrollPotoAttach;
	}

	public void setEnrollPotoAttach(String enrollPotoAttach) {
		this.enrollPotoAttach = enrollPotoAttach;
	}

	public String getPatPotoAttch() {
		return patPotoAttch;
	}

	public void setPatPotoAttch(String patPotoAttch) {
		this.patPotoAttch = patPotoAttch;
	}

	public String getEnrollCardNo() {
		return enrollCardNo;
	}

	public void setEnrollCardNo(String enrollCardNo) {
		this.enrollCardNo = enrollCardNo;
	}

	public String getPatCardNo() {
		return patCardNo;
	}

	public void setPatCardNo(String patCardNo) {
		this.patCardNo = patCardNo;
	}

	public String getEnrollName() {
		return enrollName;
	}

	public void setEnrollName(String enrollName) {
		this.enrollName = enrollName;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getEnrollAge() {
		return enrollAge;
	}

	public void setEnrollAge(String enrollAge) {
		this.enrollAge = enrollAge;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getEnrollSex() {
		return enrollSex;
	}

	public void setEnrollSex(String enrollSex) {
		this.enrollSex = enrollSex;
	}

	public String getPatSex() {
		return patSex;
	}

	public void setPatSex(String patSex) {
		this.patSex = patSex;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTherapyType() {
		return therapyType;
	}

	public void setTherapyType(String therapyType) {
		this.therapyType = therapyType;
	}

	public String getCaseSurgId() {
		return caseSurgId;
	}

	public void setCaseSurgId(String caseSurgId) {
		this.caseSurgId = caseSurgId;
	}

	public String getInvestRemarks() {
		return investRemarks;
	}

	public void setInvestRemarks(String investRemarks) {
		this.investRemarks = investRemarks;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTelephonicFlag() {
		return telephonicFlag;
	}

	public void setTelephonicFlag(String telephonicFlag) {
		this.telephonicFlag = telephonicFlag;
	}

	public String getTelephonicRemarks() {
		return telephonicRemarks;
	}

	public void setTelephonicRemarks(String telephonicRemarks) {
		this.telephonicRemarks = telephonicRemarks;
	}

	public String getAols_av() {
		return aols_av;
	}

	public void setAols_av(String aols_av) {
		this.aols_av = aols_av;
	}

	public String getStayType() {
		return stayType;
	}

	public void setStayType(String stayType) {
		this.stayType = stayType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getCruUsr() {
		return cruUsr;
	}

	public void setCruUsr(String cruUsr) {
		this.cruUsr = cruUsr;
	}

	public String getSplInvstId() {
		return splInvstId;
	}

	public void setSplInvstId(String splInvstId) {
		this.splInvstId = splInvstId;
	}

	public String getSpltType() {
		return spltType;
	}

	public void setSpltType(String spltType) {
		this.spltType = spltType;
	}

	public String getDisSubId() {
		return disSubId;
	}

	public void setDisSubId(String disSubId) {
		this.disSubId = disSubId;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getConsultant() {
		return consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	public void setCaseId(String caseId)
	{
		this.caseId = caseId;
	}

	public String getCaseId()
	{
		return caseId;
	}

	public void setPatientName(String patientName)
	{
		this.patientName = patientName;
	}

	public String getPatientName()
	{
		return patientName;
	}

	public void setRationCard(String rationCard)
	{
		this.rationCard = rationCard;
	}

	public String getRationCard()
	{
		return rationCard;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getAge()
	{
		return age;
	}
	//Start 024
	public String setAgePat(String patage)
	{
		this.patage=patage;
		return patage;

	}
	public String getAgePat()
	{

		return patage;

	}
	//025
	public String setMonths(String months)
	{
		this.months=months;

		return months;
	}
	public String getMonths()
	{

		return months;

	}
	public String setDays(String days)
	{
		this.days=days;

		return days;
	}
	public String getDays()
	{

		return days;

	}
	//End 024,025

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getGender()
	{
		return gender;
	}

	public void setPatientIPNo(String patientIPNo)
	{
		this.patientIPNo = patientIPNo;
	}

	public String getPatientIPNo()
	{
		return patientIPNo;
	}

	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}

	public String getContactNo()
	{
		return contactNo;
	}

	public void setCaseNo(String caseNo)
	{
		this.caseNo = caseNo;
	}

	public String getCaseNo()
	{
		return caseNo;
	}

	public void setScheme(String scheme)
	{
		this.scheme = scheme;
	}

	public String getScheme()
	{
		return scheme;
	}

	public void setClaimNo(String claimNo)
	{
		this.claimNo = claimNo;
	}

	public String getClaimNo()
	{
		return claimNo;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setRegHospDate(String regHospDate)
	{
		this.regHospDate = regHospDate;
	}

	public String getRegHospDate()
	{
		return regHospDate;
	}

	public void setHamlet(String hamlet)
	{
		this.hamlet = hamlet;
	}

	public String getHamlet()
	{
		return hamlet;
	}

	public void setVillage(String village)
	{
		this.village = village;
	}

	public String getVillage()
	{
		return village;
	}

	public void setMandal(String mandal)
	{
		this.mandal = mandal;
	}

	public String getMandal()
	{
		return mandal;
	}

	public void setDistrict(String district)
	{
		this.district = district;
	}

	public String getDistrict()
	{
		return district;
	}

	public void setPatientID(String patientID)
	{
		this.patientID = patientID;
	}

	public String getPatientID()
	{
		return patientID;
	}

	public void setCaseStatusId(String caseStatusId)
	{
		this.caseStatusId = caseStatusId;
	}

	public String getCaseStatusId()
	{
		return caseStatusId;
	}

	public void setCaseStatusDesc(String caseStatusDesc)
	{
		this.caseStatusDesc = caseStatusDesc;
	}

	public String getCaseStatusDesc()
	{
		return caseStatusDesc;
	}
	public void setRefCardNo(String refCardNo)
	{
		this.refCardNo = refCardNo;
	}

	public String getRefCardNo()
	{
		return refCardNo;
	}

	public void setSrcRegistration(String srcRegistration)
	{
		this.srcRegistration = srcRegistration;
	}

	public String getSrcRegistration()
	{
		return srcRegistration;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalName(String hospitalName)
	{
		this.hospitalName = hospitalName;
	}

	public String getHospitalName()
	{
		return hospitalName;
	}

	public void setDisMainName(String disMainName)
	{
		this.disMainName = disMainName;
	}

	public String getDisMainName()
	{
		return disMainName;
	}

	public void setSurgeryName(String surgeryName)
	{
		this.surgeryName = surgeryName;
	}

	public String getSurgeryName()
	{
		return surgeryName;
	}

	public void setDisMainId(String disMainId)
	{
		this.disMainId = disMainId;
	}

	public String getDisMainId()
	{
		return disMainId;
	}

	public void setSurgeryId(String surgeryId)
	{
		this.surgeryId = surgeryId;
	}

	public String getSurgeryId()
	{
		return surgeryId;
	}

	public void setCaseStatusDt(String caseStatusDt)
	{
		this.caseStatusDt = caseStatusDt;
	}

	public String getCaseStatusDt()
	{
		return caseStatusDt;
	}

	public void setPhase(String phase)
	{
		this.phase = phase;
	}

	public String getPhase()
	{
		return phase;
	}

	public void setTelephonicId(String telephonicId)
	{
		this.telephonicId = telephonicId;
	}

	public String getTelephonicId()
	{
		return telephonicId;
	}

	public void setTelInitDt(String telInitDt)
	{
		this.telInitDt = telInitDt;
	}

	public String getTelInitDt()
	{
		return telInitDt;
	}

	public void setAssociatedCases(ArrayList associatedCases)
	{
		this.associatedCases = associatedCases;
	}

	public ArrayList getAssociatedCases()
	{
		return associatedCases;
	}

	public void setSchemeDesc(String schemeDesc)
	{
		this.schemeDesc = schemeDesc;
	}

	public String getSchemeDesc()
	{
		return schemeDesc;
	}

	public void setHospDispCode(String hospDispCode)
	{
		this.hospDispCode = hospDispCode;
	}

	public String getHospDispCode()
	{
		return hospDispCode;
	}

	public void setHospAddress(String hospAddress)
	{
		this.hospAddress = hospAddress;
	}

	public String getHospAddress()
	{
		return hospAddress;
	}

	public void setHospContactNo(String hospContactNo)
	{
		this.hospContactNo = hospContactNo;
	}

	public String getHospContactNo()
	{
		return hospContactNo;
	}

	public void setPatCrtDt(String patCrtDt)
	{
		this.patCrtDt = patCrtDt;
	}

	public String getPatCrtDt()
	{
		return patCrtDt;
	}

	public void setDisMainLst(ArrayList disMainLst)
	{
		this.disMainLst = disMainLst;
	}

	public ArrayList getDisMainLst()
	{
		return disMainLst;
	}

	public void setSurgeryLst(ArrayList surgeryLst)
	{
		this.surgeryLst = surgeryLst;
	}

	public ArrayList getSurgeryLst()
	{
		return surgeryLst;
	}


	public void setDoctorStat(String doctorStat)
	{
		this.doctorStat = doctorStat;
	}

	public String getDoctorStat()
	{
		return doctorStat;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}

	public String getCardType()
	{
		return cardType;
	}
	public void setCaseCrtDt(String caseCrtDt)
	{
		this.caseCrtDt = caseCrtDt;
	}

	public String getCaseCrtDt()
	{
		return caseCrtDt;
	}

	public void setPreauthDt(String preauthDt)
	{
		this.preauthDt = preauthDt;
	}

	public String getPreauthDt()
	{
		return preauthDt;
	}

	public void setSurgeryDt(String surgeryDt)
	{
		this.surgeryDt = surgeryDt;
	}

	public String getSurgeryDt()
	{
		return surgeryDt;
	}


	public void setDoctorDtls(String doctorDtls)
	{
		this.doctorDtls = doctorDtls;
	}

	public String getDoctorDtls()
	{
		return doctorDtls;
	}

	public void setUpdType(String updType)
	{
		this.updType = updType;
	}

	public String getUpdType()
	{
		return updType;
	}
	public void setCaste(String caste)
	{
		this.caste = caste;
	}

	public String getCaste()
	{
		return caste;
	}

	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}

	public String getPincode()
	{
		return pincode;
	}

	public void setMitName(String mitName)
	{
		this.mitName = mitName;
	}

	public String getMitName()
	{
		return mitName;
	}

	public void setMitDate(String mitDate)
	{
		this.mitDate = mitDate;
	}

	public String getMitDate()
	{
		return mitDate;
	}

	public void setCaseAprvDt(String caseAprvDt) {
		this.caseAprvDt = caseAprvDt;
	}

	public String getCaseAprvDt() {
		return caseAprvDt;
	}
	public void setTelSurgId(String telSurgId) {
		this.telSurgId = telSurgId;
	}

	public String getTelSurgId() {
		return telSurgId;
	}
	public void setPatRegDate(String patRegDate) {
		this.patRegDate = patRegDate;
	}

	public String getPatRegDate() {
		return patRegDate;
	}

	public void setHospType(String hospType) {
		this.hospType = hospType;
	}

	public String getHospType() {
		return hospType;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditDate(Date auditDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss a");
		if(auditDate != null )
			this.auditDate= sdf.format(auditDate);
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}

	public String getAuditRemarks() {
		return auditRemarks;
	}

	public void setAuditRole(String auditRole) {
		this.auditRole = auditRole;
	}

	public String getAuditRole() {
		return auditRole;
	}

	public String getPatage() {
		return patage;
	}

	public void setPatage(String patage) {
		this.patage = patage;
	}

	public String getCardIssueDt() {
		return cardIssueDt;
	}

	public void setCardIssueDt(Date cardIssueDt) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
		if(cardIssueDt != null )
			this.cardIssueDt= sdf.format(cardIssueDt);
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public List<LabelBean> getLstPersonalHistory() {
		return lstPersonalHistory;
	}

	public void setLstPersonalHistory(List<LabelBean> lstPersonalHistory) {
		this.lstPersonalHistory = lstPersonalHistory;
	}

	public String getBpRmt() {
		return bpRmt;
	}

	public void setBpRmt(String bpRmt) {
		this.bpRmt = bpRmt;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	public String getTestKnown() {
		return testKnown;
	}
	public void setTestKnown(String testKnown) {
		this.testKnown = testKnown;
	}
	public String getExamFindg() {
		return examFindg;
	}
	public void setExamFindg(String examFindg) {
		this.examFindg = examFindg;
	}
	public List<PreauthVO> getInvList() {
		return invList;
	}
	public void setInvList(List<PreauthVO> invList) {
		this.invList = invList;
	}
	public String getIpOpFlag() {
		return ipOpFlag;
	}
	public void setIpOpFlag(String ipOpFlag) {
		this.ipOpFlag = ipOpFlag;
	}
	public List<DrugsVO> getDrugList() {
		return drugList;
	}
	public void setDrugList(List<DrugsVO> drugList) {
		this.drugList = drugList;
	}
	public String getAdmType() {
		return admType;
	}
	public void setAdmType(String admType) {
		this.admType = admType;
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
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getLegalCaseCheck() {
		return legalCaseCheck;
	}
	public void setLegalCaseCheck(String legalCaseCheck) {
		this.legalCaseCheck = legalCaseCheck;
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
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getOpProcUnits() {
		return opProcUnits;
	}
	public void setOpProcUnits(String opProcUnits) {
		this.opProcUnits = opProcUnits;
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
	public List<ChronicOPVO> getTherapyList() {
		return therapyList;
	}
	public void setTherapyList(List<ChronicOPVO> therapyList) {
		this.therapyList = therapyList;
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
	public String getComplaintTypeName() {
		return complaintTypeName;
	}
	public void setComplaintTypeName(String complaintTypeName) {
		this.complaintTypeName = complaintTypeName;
	}
	public String getComplaintName() {
		return complaintName;
	}
	public void setComplaintName(String complaintName) {
		this.complaintName = complaintName;
	}
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	public String getNewBornBaby() {
		return newBornBaby;
	}
	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}

	
	public BigDecimal getPREAUTHAPPVAMT() {
		return PREAUTHAPPVAMT;
	}
	public void setPREAUTHAPPVAMT(BigDecimal pREAUTHAPPVAMT) {
		PREAUTHAPPVAMT = pREAUTHAPPVAMT;
	}
	public BigDecimal getCOMORBIDAPPVAMT() {
		return COMORBIDAPPVAMT;
	}
	public void setCOMORBIDAPPVAMT(BigDecimal cOMORBIDAPPVAMT) {
		COMORBIDAPPVAMT = cOMORBIDAPPVAMT;
	}
	public BigDecimal getENHAPPVAMT() {
		return ENHAPPVAMT;
	}
	public void setENHAPPVAMT(BigDecimal eNHAPPVAMT) {
		ENHAPPVAMT = eNHAPPVAMT;
	}
	
	public long getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(long totAmount) {
		this.totAmount = totAmount;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getNabhAmt() {
		return nabhAmt;
	}
	public void setNabhAmt(String nabhAmt) {
		this.nabhAmt = nabhAmt;
	}
	public String getEnhFlag() {
		return enhFlag;
	}
	public void setEnhFlag(String enhFlag) {
		this.enhFlag = enhFlag;
	}
	public String getSlabAmt() {
		return slabAmt;
	}
	public void setSlabAmt(String slabAmt) {
		this.slabAmt = slabAmt;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getDentalLimit() {
		return dentalLimit;
	}
	public void setDentalLimit(String dentalLimit) {
		this.dentalLimit = dentalLimit;
	}
	public int getDentalLimitCount() {
		return dentalLimitCount;
	}
	public void setDentalLimitCount(int dentalLimitCount) {
		this.dentalLimitCount = dentalLimitCount;
	}
	public int getProcCount() {
		return procCount;
	}
	public void setProcCount(int procCount) {
		this.procCount = procCount;
	}
	public String getCeoApprovalFlag() {
		return ceoApprovalFlag;
	}
	public void setCeoApprovalFlag(String ceoApprovalFlag) {
		this.ceoApprovalFlag = ceoApprovalFlag;
	}
	public String getPastHisOthers() {
		return PastHisOthers;
	}
	public void setPastHisOthers(String pastHisOthers) {
		PastHisOthers = pastHisOthers;
	}
	public String getFamilyHisOthers() {
		return FamilyHisOthers;
	}
	public void setFamilyHisOthers(String familyHisOthers) {
		FamilyHisOthers = familyHisOthers;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public Number getAGE() {
		return AGE;
	}
	public void setAGE(Number aGE) {
		AGE = aGE;
	}
	public String getAgeLimit() {
		return ageLimit;
	}
	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}
	public String getAddedProcs() {
		return addedProcs;
	}
	public void setAddedProcs(String addedProcs) {
		this.addedProcs = addedProcs;
	}
	public String getComboProcCode() {
		return comboProcCode;
	}
	public void setComboProcCode(String comboProcCode) {
		this.comboProcCode = comboProcCode;
	}
	public String getNonComboProcCode() {
		return nonComboProcCode;
	}
	public void setNonComboProcCode(String nonComboProcCode) {
		this.nonComboProcCode = nonComboProcCode;
	}
	public String getTESTKNOWN() {
		return TESTKNOWN;
	}
	public void setTESTKNOWN(String tESTKNOWN) {
		TESTKNOWN = tESTKNOWN;
	}
	public String getTEST() {
		return TEST;
	}
	public void setTEST(String tEST) {
		TEST = tEST;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getSPLINVSTID() {
		return SPLINVSTID;
	}
	public void setSPLINVSTID(String sPLINVSTID) {
		SPLINVSTID = sPLINVSTID;
	}
	public String getROUTE() {
		return ROUTE;
	}
	public void setROUTE(String rOUTE) {
		ROUTE = rOUTE;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
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

	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	public String getFrameworkPrice() {
		return frameworkPrice;
	}
	public void setFrameworkPrice(String frameworkPrice) {
		this.frameworkPrice = frameworkPrice;
	}
	public String getIpOp() {
		return ipOp;
	}
	public void setIpOp(String ipOp) {
		this.ipOp = ipOp;
	}
	public String getLifetimeUnitsLimit() {
		return lifetimeUnitsLimit;
	}
	public void setLifetimeUnitsLimit(String lifetimeUnitsLimit) {
		this.lifetimeUnitsLimit = lifetimeUnitsLimit;
	}

	public String getSubsequentPrice() {
		return subsequentPrice;
	}
	public void setSubsequentPrice(String subsequentPrice) {
		this.subsequentPrice = subsequentPrice;
	}
	public String getUnitsLimit() {
		return unitsLimit;
	}
	public void setUnitsLimit(String unitsLimit) {
		this.unitsLimit = unitsLimit;
	}
	public String getLifeTimeUnitsLeft() {
		return lifeTimeUnitsLeft;
	}
	public void setLifeTimeUnitsLeft(String lifeTimeUnitsLeft) {
		this.lifeTimeUnitsLeft = lifeTimeUnitsLeft;
	}
	public String getAgeLimitMsg() {
		return ageLimitMsg;
	}
	public void setAgeLimitMsg(String ageLimitMsg) {
		this.ageLimitMsg = ageLimitMsg;
	}
	public String getLifeTimeMonths() {
		return lifeTimeMonths;
	}
	public void setLifeTimeMonths(String lifeTimeMonths) {
		this.lifeTimeMonths = lifeTimeMonths;
	}
	public String getAgeMsg() {
		return ageMsg;
	}
	public void setAgeMsg(String ageMsg) {
		this.ageMsg = ageMsg;
	}
	public String getActualUnitsLeft() {
		return actualUnitsLeft;
	}
	public void setActualUnitsLeft(String actualUnitsLeft) {
		this.actualUnitsLeft = actualUnitsLeft;
	}
	public String getComboProcNames() {
		return comboProcNames;
	}
	public void setComboProcNames(String comboProcNames) {
		this.comboProcNames = comboProcNames;
	}
	public String getComboNonProcNames() {
		return comboNonProcNames;
	}
	public void setComboNonProcNames(String comboNonProcNames) {
		this.comboNonProcNames = comboNonProcNames;
	}

	public String getOtherComplaintName() {
		return otherComplaintName;
	}
	public void setOtherComplaintName(String otherComplaintName) {
		this.otherComplaintName = otherComplaintName;
	}
	public String getSYMPTOMS() {
		return SYMPTOMS;
	}
	public void setSYMPTOMS(String sYMPTOMS) {
		SYMPTOMS = sYMPTOMS;
	}

	public String getStandaloneProc() {
		return standaloneProc;
	}
	public void setStandaloneProc(String standaloneProc) {
		this.standaloneProc = standaloneProc;
	}
	public String getStandaloneProcNames() {
		return standaloneProcNames;
	}
	public void setStandaloneProcNames(String standaloneProcNames) {
		this.standaloneProcNames = standaloneProcNames;
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
	public List<ChronicOPVO> getChronicPkgLst() {
		return chronicPkgLst;
	}
	public void setChronicPkgLst(List<ChronicOPVO> chronicPkgLst) {
		this.chronicPkgLst = chronicPkgLst;
	}
	public String getOtherDiagName() {
		return otherDiagName;
	}
	public void setOtherDiagName(String otherDiagName) {
		this.otherDiagName = otherDiagName;
	}

	public String getINSTALLMENT() {
		return INSTALLMENT;
	}
	public void setINSTALLMENT(String iNSTALLMENT) {
		INSTALLMENT = iNSTALLMENT;
	}
	public int getFlpCount() {
		return flpCount;
	}
	public void setFlpCount(int flpCount) {
		this.flpCount = flpCount;
	}
	public String getSurgCount() {
		return surgCount;
	}
	public void setSurgCount(String surgCount) {
		this.surgCount = surgCount;
	}
	public String getFlpSurgCount() {
		return flpSurgCount;
	}
	public void setFlpSurgCount(String flpSurgCount) {
		this.flpSurgCount = flpSurgCount;
	}
	public String getTreatmentDntl() {
		return treatmentDntl;
	}
	public void setTreatmentDntl(String treatmentDntl) {
		this.treatmentDntl = treatmentDntl;
	}
	public String getDrugHst() {
		return drugHst;
	}
	public void setDrugHst(String drugHst) {
		this.drugHst = drugHst;
	}
	public String getDrugHstVal() {
		return drugHstVal;
	}
	public void setDrugHstVal(String drugHstVal) {
		this.drugHstVal = drugHstVal;
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
	public String getMedicalDtlsidStr() {
		return medicalDtlsidStr;
	}
	public void setMedicalDtlsidStr(String medicalDtlsidStr) {
		this.medicalDtlsidStr = medicalDtlsidStr;
	}
	public String getExtraoralStr() {
		return extraoralStr;
	}
	public void setExtraoralStr(String extraoralStr) {
		this.extraoralStr = extraoralStr;
	}
	public String getIntraoralStr() {
		return intraoralStr;
	}
	public void setIntraoralStr(String intraoralStr) {
		this.intraoralStr = intraoralStr;
	}
	public String getSubdentalrl0Str() {
		return subdentalrl0Str;
	}
	public void setSubdentalrl0Str(String subdentalrl0Str) {
		this.subdentalrl0Str = subdentalrl0Str;
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
	public String getPreviousDentalTreatment() {
		return previousDentalTreatment;
	}
	public void setPreviousDentalTreatment(String previousDentalTreatment) {
		this.previousDentalTreatment = previousDentalTreatment;
	}
	public String getOcclusionType() {
		return occlusionType;
	}
	public void setOcclusionType(String occlusionType) {
		this.occlusionType = occlusionType;
	}
	public String getOcclusion() {
		return occlusion;
	}
	public void setOcclusion(String occlusion) {
		this.occlusion = occlusion;
	}
	public String getOcclusionOther() {
		return occlusionOther;
	}
	public void setOcclusionOther(String occlusionOther) {
		this.occlusionOther = occlusionOther;
	}
	public String getAdvancedInvestigations() {
		return advancedInvestigations;
	}
	public void setAdvancedInvestigations(String advancedInvestigations) {
		this.advancedInvestigations = advancedInvestigations;
	}
	public String getAdmissionDetails() {
		return admissionDetails;
	}
	public void setAdmissionDetails(String admissionDetails) {
		this.admissionDetails = admissionDetails;
	}
	public String getFollowupAdv() {
		return followupAdv;
	}
	public void setFollowupAdv(String followupAdv) {
		this.followupAdv = followupAdv;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getMedicationGiven() {
		return medicationGiven;
	}
	public void setMedicationGiven(String medicationGiven) {
		this.medicationGiven = medicationGiven;
	}
	public String getMedHistVal() {
		return medHistVal;
	}
	public void setMedHistVal(String medHistVal) {
		this.medHistVal = medHistVal;
	}
	public String getMedicalHistoryOthr() {
		return MedicalHistoryOthr;
	}
	public void setMedicalHistoryOthr(String medicalHistoryOthr) {
		MedicalHistoryOthr = medicalHistoryOthr;
	}



	
	
	


}

