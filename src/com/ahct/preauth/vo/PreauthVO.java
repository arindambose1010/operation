package com.ahct.preauth.vo;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.common.vo.LabelBean;


public class PreauthVO implements java.io.Serializable
{
 public String getTreatmntDt() {
		return treatmntDt;
	}
	public void setTreatmntDt(String treatmntDt) {
		this.treatmntDt = treatmntDt;
	}
	public String getDischrgeDt() {
		return dischrgeDt;
	}
	public void setDischrgeDt(String dischrgeDt) {
		this.dischrgeDt = dischrgeDt;
	}
	public String getEnhAmounts() {
		return enhAmounts;
	}
	public void setEnhAmounts(String enhAmounts) {
		this.enhAmounts = enhAmounts;
	}
	public List<AttachmentVO> getAttachmentsList() {
		return attachmentsList;
	}
	public void setAttachmentsList(List<AttachmentVO> attachmentsList) {
		this.attachmentsList = attachmentsList;
	}
	public List<AttachmentVO> getDrugsAttchList() {
		return drugsAttchList;
	}
	public void setDrugsAttchList(List<AttachmentVO> drugsAttchList) {
		this.drugsAttchList = drugsAttchList;
	}
	public String getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(String cOUNT) {
		COUNT = cOUNT;
	}
	public String getExceedFlag() {
		return exceedFlag;
	}
	public void setExceedFlag(String exceedFlag) {
		this.exceedFlag = exceedFlag;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getHoldRemarks() {
		return holdRemarks;
	}
	public void setHoldRemarks(String holdRemarks) {
		this.holdRemarks = holdRemarks;
	}
	public String getICDAMT() {
		return ICDAMT;
	}
	public void setICDAMT(String iCDAMT) {
		ICDAMT = iCDAMT;
	}
	public String getICDPROCCODE() {
		return ICDPROCCODE;
	}
	public void setICDPROCCODE(String iCDPROCCODE) {
		ICDPROCCODE = iCDPROCCODE;
	}
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	
	public void setCardIssueDt(String cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}
 private String test;
 private String testKnown;
 private String examFindg;
 private List<PreauthVO> invList;
 private List<DrugsVO> drugList;
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
 private String phcName=EMPTY_STRING;
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
 private String feedbackFile=EMPTY_STRING;
 private String renewalFlag=EMPTY_STRING;
 private String caseStatusDt=EMPTY_STRING;
 private String phase=EMPTY_STRING;
 private String renewal=EMPTY_STRING;
 private String districtHdr="LH6";
 private String mandalHdr="LH7";
 private String villageHdr="LH8";
 private String hamletHdr="LH9";
 private String telephonicId=EMPTY_STRING;
 private String telInitDt=EMPTY_STRING;
 private ArrayList associatedCases=EMPTY_LIST;
 private int renalCount=ZERO_VAL;
 private int hemoCount=ZERO_VAL;
 private int hemoCheck=ZERO_VAL;
 private int complaintCount=ZERO_VAL;
 private int medicalMngCase=ZERO_VAL;
 private String blockedUser=EMPTY_STRING;
 private String payee=EMPTY_STRING;
 private String billHeadName=EMPTY_STRING;
 private String billDt=EMPTY_STRING;
 private String billTime=EMPTY_STRING;
 private String preauthDt=EMPTY_STRING;
 private String surgeryDt=EMPTY_STRING;
 private String dischargeDt=EMPTY_STRING;
 private String deathDt=EMPTY_STRING;
 private String hcFlag="False";
 private String trustPaid=EMPTY_STRING;
 private String MedicalMng=EMPTY_STRING;
 private String patCrtDt=EMPTY_STRING;
 private ArrayList disMainLst=EMPTY_LIST;
 private ArrayList surgeryLst=EMPTY_LIST;
 private int cmoAprvAmt=ZERO_VAL;
 private int ceoAprvAmt=ZERO_VAL;
 private int trustAprvAmt=ZERO_VAL;
 private int insAprvAmt=ZERO_VAL;
 private int cooApprvAMT=ZERO_VAL;
 private int trustEnhApprvAmt=ZERO_VAL;
 private int committeeEnhAmt=ZERO_VAL;
 private int ceoEnhApprvAmt=ZERO_VAL;
 private int cmoEnhApprvAmt=ZERO_VAL;//021
 private int nwhEnhAmt=ZERO_VAL;
 private int claimAmount=ZERO_VAL;
 private String doctorStat=EMPTY_STRING;
 private String doctorRmks=EMPTY_STRING;
 private String doctorDtls=EMPTY_STRING;
 private String trustStat=EMPTY_STRING;
 private String trustRmks=EMPTY_STRING;
 private String trustDtls=EMPTY_STRING;
 private String ceoStat=EMPTY_STRING;
 private String ceoRmks=EMPTY_STRING;
 private String ceoDtls=EMPTY_STRING;
 private String cmoStat=EMPTY_STRING;
 private String cmoRmks=EMPTY_STRING;
 private String cmoDtls=EMPTY_STRING;
 private String techComStat=EMPTY_STRING;
 private String techComRmks=EMPTY_STRING;
 private String techComDtls=EMPTY_STRING;
 private String gmMedStat=EMPTY_STRING;
 private String gmMedRmks=EMPTY_STRING;
 private String gmMedDtls=EMPTY_STRING;
 private String ceoEnStat=EMPTY_STRING;
 private String ceoEnRmks=EMPTY_STRING;
 private String ceoEnDtls=EMPTY_STRING;
 private String cmoEnhStat=EMPTY_STRING;//021
 private String cmoEnhRmks=EMPTY_STRING;//021
 private String cmoEnhDtls=EMPTY_STRING;//021
 private ArrayList caseRmks=EMPTY_LIST;
 private String phaseStDt=EMPTY_STRING;
 private String phaseEdDt=EMPTY_STRING;
 private int preAmount=ZERO_VAL;
 private int preCochAmount=ZERO_VAL;
 private int consCount=ZERO_VAL;
 private int counsCount=ZERO_VAL;
 private int cardCount=ZERO_VAL;
 private int cnclCount=ZERO_VAL;
 private int techComCount=ZERO_VAL;
 private int admCount=ZERO_VAL;
 private int preClnCount=ZERO_VAL;
 private int rmnClnCount=ZERO_VAL;
 private String caseCrtDt=EMPTY_STRING;
 private ArrayList clinicalLst=EMPTY_LIST;
 private HashMap polyDtls=EMPTY_MAP;
 private int rejFlag=ZERO_VAL;
 private String bedUpdFlag="True";
 private HashMap deactDtls=EMPTY_MAP;
 private int inApvCount=ZERO_VAL;
 private ArrayList surgCnt=EMPTY_LIST;
 private int cochSurgCnt=ZERO_VAL;
 private int closedPhase=ZERO_VAL;
 private String surgScheme=EMPTY_STRING;
 private String isApproved=ZERO_STRING;
 private String updType=EMPTY_STRING;
 //start 001
 private String storage=EMPTY_STRING;
 private int surgCount=ZERO_VAL;
 private int deathCount=ZERO_VAL;
 private int disCount=ZERO_VAL;
 private int disSumCount=ZERO_VAL;
 private String blockSurgDt=EMPTY_STRING;
 //end 001
 private String splCommittee=EMPTY_STRING;//002
 private String scheduleId=EMPTY_STRING;//002
 private String committeeMem=EMPTY_STRING;//002
 //start 003
 private String caste=EMPTY_STRING;
 private String pincode=EMPTY_STRING;
 private String mitName=EMPTY_STRING;
 private String mitDate=EMPTY_STRING;
 //end 003
 //start 004
 private String treatmntDt;
 private String dischrgeDt;
 private String cnclReqDt=EMPTY_STRING;
 private String cnclApvDt=EMPTY_STRING;
 //end 004 start 005
 private String enhReqDt=EMPTY_STRING;
 private String enhApvDt=EMPTY_STRING;
 private String enhRejDt=EMPTY_STRING;
 private int apvCount=ZERO_VAL;//end 005
  private String userRole=EMPTY_STRING;//006
 private String caseRenewal=EMPTY_STRING;//007
 private String processFlag=EMPTY_STRING;//007
 private String fieldAttachFlag=EMPTY_STRING;//008
 private String cochCnt=EMPTY_STRING;//009
 private String errStatus=EMPTY_STRING;//010
 private String journalistRenewal=EMPTY_STRING;//011
// private String empnl_cmb_name=EMPTY_STRING;//012
 private String cmcoRenewal=EMPTY_STRING;//013
 private int preAttachCount=ZERO_VAL;//014
 private String caseAprvDt=EMPTY_STRING;//015
 private int enhCount=ZERO_VAL;//016
 private String telSurgId = EMPTY_STRING;//017
 private ArrayList healthCardAttach=EMPTY_LIST;//018
 private int cochAttachCount=ZERO_VAL;//019
 private String patRegDate=EMPTY_STRING;//020
  private int documentPendingCnt=ZERO_VAL;//022
 private String allSurgeries=EMPTY_STRING;//024
  private String deductorType=EMPTY_STRING;
  private String prevMnthPreSurgery=EMPTY_STRING;
   private int cmaAuditCnt=ZERO_VAL;//By 023
   private String displayBiometric=EMPTY_STRING;
   private int feedbckcnt=ZERO_VAL;
   private String biomEmrChkFlag=EMPTY_STRING;//026
   private String biomEmrChkRmks=EMPTY_STRING;//026
   private String auditName=EMPTY_STRING;
   private String auditDate=EMPTY_STRING;
   private String auditRole=EMPTY_STRING;
   private String auditRemarks=EMPTY_STRING;
   private Long auditAmount;
   private String enhAmounts;
   private String auditAction=EMPTY_STRING;
   private String auditComboRole=EMPTY_STRING;
   private String isFlwExtflg="false";
   private String oldClaimBill;
   private int fdtdAttachCount=ZERO_VAL;
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
   private String numRadio;
   private String nameRadio;
   private String ageRadio;
   private String genderRadio;
   private String prePat1Radio;
   private String preDoc1Radio;
   private String preRamco1Radio;
   private String prePat2Radio;
   private String preDoc2Radio;
   private String preRamco2Radio;
   private String conPat1Radio;
   private String conDoc1Radio;
   private String conRamco1Radio;
   private String conPat2Radio;
   private String conDoc2Radio;
   private String conRamco2Radio;
    private String photoradio;
   private String quesMsg;
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
   private String nabhFlg;
   private String caseNabhFlg;
   private String preauthPckgAmt;
   private String ptdTotalApprvAmt;
   private String nabhAmt;
   private String pendingFlag;
   private String familyHistValue;
   private String symptoms;
   private String doctorSpeciality=EMPTY_STRING;
   private String hospDistrict;
   private String drugDeletionString;;
   private String hospitalId;
   private String preauthTotalPackageAmt;
   private List<AttachmentVO> attachmentsList=null;
   private List<AttachmentVO> drugsAttchList=null;
   
   private String typeOfProcedure;
   private String procedureConsent;
   private String medCardioClearence;
   private String bloodTransfusion;
   private String legalCaseCheck;
   private String legalCaseNo;
   private String policeStatName;
   private String surgProcUnits;
   private String preauthInitiatedDt;
   private String preauthAppvDt;
   private String preauthRejDt;
   private String preauthCancelDt;

   private String opProcUnits;
   private String activeYN;
   private String cochlearYN;
   private String cochlearQues;
   private String medcoId;
   private String process;
   private String patientScheme;
   private String enhFlag;
   private String slabAmt;
   private String COUNT ;
   private long PREAUTHAPPVAMT;
   private long COMORBIDAPPVAMT;
   private long ENHAPPVAMT;
   private long totAmount;
   private String flpSurgCount;
   private String surgCountStr;
   private String toothedUnits;
   private String exceedFlag;
   private String attachPath;
   private String attachName;
   
	private String TESTKNOWN;
    private String TEST;
    private String NAME;
    private String SPLINVSTID;
    private String ROUTE;
    private String PRICE;
    private String INSTALLMENT;

	private String holdRemarks;
	private String organTransYN;
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
	public String getINSTALLMENT() {
		return INSTALLMENT;
	}
	public void setINSTALLMENT(String iNSTALLMENT) {
		INSTALLMENT = iNSTALLMENT;
	}
   public String getPreauthCancelDt() {
	return preauthCancelDt;
}
public void setPreauthCancelDt(String preauthCancelDt) {
	this.preauthCancelDt = preauthCancelDt;
}
public String getPreauthInitiatedDt() {
	return preauthInitiatedDt;
}
public void setPreauthInitiatedDt(String preauthInitiatedDt) {
	this.preauthInitiatedDt = preauthInitiatedDt;
}
public String getPreauthAppvDt() {
	return preauthAppvDt;
}
public void setPreauthAppvDt(String preauthAppvDt) {
	this.preauthAppvDt = preauthAppvDt;
}
public String getPreauthRejDt() {
	return preauthRejDt;
}
public void setPreauthRejDt(String preauthRejDt) {
	this.preauthRejDt = preauthRejDt;
}
public String getTypeOfProcedure() {
	return typeOfProcedure;
}
public void setTypeOfProcedure(String typeOfProcedure) {
	this.typeOfProcedure = typeOfProcedure;
}
public String getProcedureConsent() {
	return procedureConsent;
}
public void setProcedureConsent(String procedureConsent) {
	this.procedureConsent = procedureConsent;
}
public String getMedCardioClearence() {
	return medCardioClearence;
}
public void setMedCardioClearence(String medCardioClearence) {
	this.medCardioClearence = medCardioClearence;
}
public String getBloodTransfusion() {
	return bloodTransfusion;
}
public void setBloodTransfusion(String bloodTransfusion) {
	this.bloodTransfusion = bloodTransfusion;
}
public String getHospitalId() {
	return hospitalId;
}
public void setHospitalId(String hospitalId) {
	this.hospitalId = hospitalId;
}
public String getDrugDeletionString() {
	return drugDeletionString;
}
public void setDrugDeletionString(String drugDeletionString) {
	this.drugDeletionString = drugDeletionString;
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
public String getPendingFlag() {
	return pendingFlag;
}
public void setPendingFlag(String pendingFlag) {
	this.pendingFlag = pendingFlag;
}
public String getNabhAmt() {
	return nabhAmt;
}
public void setNabhAmt(String nabhAmt) {
	this.nabhAmt = nabhAmt;
}
public String getPtdTotalApprvAmt() {
	return ptdTotalApprvAmt;
}
public void setPtdTotalApprvAmt(String ptdTotalApprvAmt) {
	this.ptdTotalApprvAmt = ptdTotalApprvAmt;
}
public String getPreauthPckgAmt() {
	return preauthPckgAmt;
}
public void setPreauthPckgAmt(String preauthPckgAmt) {
	this.preauthPckgAmt = preauthPckgAmt;
}
public String getNabhFlg() {
	return nabhFlg;
}
public void setNabhFlg(String nabhFlg) {
	this.nabhFlg = nabhFlg;
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
	private String medcoProcUnits;
	private String ctdProcUnits;
	private String chProcUnits;
	   private String ICDAMT;
	   private String ICDPROCCODE;
   
   
   private String seqNo;
   private String filename;
   private String filePath;
   private List<LabelBean> lstPersonalHistory;
   private List<PreauthVO> lstSplInvet;
   private List<LabelBean> comorbidValues;
   private String diagnosisType;
   private String mainCatName;
   private String catName;
   private String subCatName;
   private String disName;
   private String disAnatomicalSitename;
   
   private String admissionRadio;
   private String admissionDate;
   private String enhancementDtls;
   private String drugs;
   private String buttonVal;
   private String statusToSave;
   private String langId;
   
   private String enhType;
   private String enhCode;
   private String enhQuant;
   private String enhUnits;
   private String enhAmt;
   private String enhSno;
   private String symCode;
   private String symName;
   private String mainSymCode;
   private String mainSymName;
   private String enhQuantCode;
   
   private String familyHistoryOthr;
   private String pastIllenesOthr;
   private String pastIllenesCancers;
   private String pastIllenesEndDis;
   private String pastIllenesSurg;
   
   private String comorBidAmt;
   private String comorBidVals;
   private String comorBidAppvAmt;
   private String dehydrationType;
   private String ceoApprvFlag;
   private String medicalSurgicalFlag;
   private Integer noOfDays;
   
   private String viewType; 
   private String CASEID;
   
   
	private String opCatCode;
	private String opPkgCode;
	private String opIcdCode;
	private String opCatName;
    private String opPackageName;
    private String opIcdName;
    private String complaintTypeName;
    private String complaintName;
    private String installment;
    
    private String startDt;
    private String endDt;
   
   
   public String getEnhQuantCode() {
	return enhQuantCode;
}
public void setEnhQuantCode(String enhQuantCode) {
	this.enhQuantCode = enhQuantCode;
}
public String getCeoApprvFlag() {
	return ceoApprvFlag;
}
public void setCeoApprvFlag(String ceoApprvFlag) {
	this.ceoApprvFlag = ceoApprvFlag;
}
public String getMedicalSurgicalFlag() {
	return medicalSurgicalFlag;
}
public void setMedicalSurgicalFlag(String medicalSurgicalFlag) {
	this.medicalSurgicalFlag = medicalSurgicalFlag;
}
public List<LabelBean> getComorbidValues() {
	return comorbidValues;
}
public void setComorbidValues(List<LabelBean> comorbidValues) {
	this.comorbidValues = comorbidValues;
}
public String getDehydrationType() {
	return dehydrationType;
}
public void setDehydrationType(String dehydrationType) {
	this.dehydrationType = dehydrationType;
}
public String getComorBidAppvAmt() {
	return comorBidAppvAmt;
}
public void setComorBidAppvAmt(String comorBidAppvAmt) {
	this.comorBidAppvAmt = comorBidAppvAmt;
}
public String getComorBidAmt() {
	return comorBidAmt;
}
public void setComorBidAmt(String comorBidAmt) {
	this.comorBidAmt = comorBidAmt;
}
public String getComorBidVals() {
	return comorBidVals;
}
public void setComorBidVals(String comorBidVals) {
	this.comorBidVals = comorBidVals;
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

public String getEnhType() {
	return enhType;
}

public void setEnhType(String enhType) {
	this.enhType = enhType;
}

public String getEnhCode() {
	return enhCode;
}

public void setEnhCode(String enhCode) {
	this.enhCode = enhCode;
}

public String getEnhQuant() {
	return enhQuant;
}

public void setEnhQuant(String enhQuant) {
	this.enhQuant = enhQuant;
}

public String getEnhUnits() {
	return enhUnits;
}

public void setEnhUnits(String enhUnits) {
	this.enhUnits = enhUnits;
}

public String getEnhAmt() {
	return enhAmt;
}

public void setEnhAmt(String enhAmt) {
	this.enhAmt = enhAmt;
}

public String getEnhSno() {
	return enhSno;
}

public void setEnhSno(String enhSno) {
	this.enhSno = enhSno;
}

public String getLangId() {
	return langId;
}

public void setLangId(String langId) {
	this.langId = langId;
}

public String getStatusToSave() {
	return statusToSave;
}

public void setStatusToSave(String statusToSave) {
	this.statusToSave = statusToSave;
}

public String getButtonVal() {
	return buttonVal;
}

public void setButtonVal(String buttonVal) {
	this.buttonVal = buttonVal;
}

public String getEnhancementDtls() {
	return enhancementDtls;
}

public void setEnhancementDtls(String enhancementDtls) {
	this.enhancementDtls = enhancementDtls;
}

public String getAdmissionRadio() {
	return admissionRadio;
}

public void setAdmissionRadio(String admissionRadio) {
	this.admissionRadio = admissionRadio;
}

public String getAdmissionDate() {
	return admissionDate;
}

public void setAdmissionDate(String admissionDate) {
	this.admissionDate = admissionDate;
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

public String getQuesMsg() {
	return quesMsg;
}

public void setQuesMsg(String quesMsg) {
	this.quesMsg = quesMsg;
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

public String getPhotoradio() {
		return photoradio;
	}

	public void setPhotoradio(String photoradio) {
		this.photoradio = photoradio;
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

public String getNumRadio() {
	return numRadio;
}

public void setNumRadio(String numRadio) {
	this.numRadio = numRadio;
}

public String getNameRadio() {
	return nameRadio;
}

public void setNameRadio(String nameRadio) {
	this.nameRadio = nameRadio;
}

public String getAgeRadio() {
	return ageRadio;
}

public void setAgeRadio(String ageRadio) {
	this.ageRadio = ageRadio;
}

public String getGenderRadio() {
	return genderRadio;
}

public void setGenderRadio(String genderRadio) {
	this.genderRadio = genderRadio;
}

public String getPrePat1Radio() {
	return prePat1Radio;
}

public void setPrePat1Radio(String prePat1Radio) {
	this.prePat1Radio = prePat1Radio;
}

public String getPreDoc1Radio() {
	return preDoc1Radio;
}

public void setPreDoc1Radio(String preDoc1Radio) {
	this.preDoc1Radio = preDoc1Radio;
}

public String getPreRamco1Radio() {
	return preRamco1Radio;
}

public void setPreRamco1Radio(String preRamco1Radio) {
	this.preRamco1Radio = preRamco1Radio;
}

public String getPrePat2Radio() {
	return prePat2Radio;
}

public void setPrePat2Radio(String prePat2Radio) {
	this.prePat2Radio = prePat2Radio;
}

public String getPreDoc2Radio() {
	return preDoc2Radio;
}

public void setPreDoc2Radio(String preDoc2Radio) {
	this.preDoc2Radio = preDoc2Radio;
}

public String getPreRamco2Radio() {
	return preRamco2Radio;
}

public void setPreRamco2Radio(String preRamco2Radio) {
	this.preRamco2Radio = preRamco2Radio;
}

public String getConPat1Radio() {
	return conPat1Radio;
}

public void setConPat1Radio(String conPat1Radio) {
	this.conPat1Radio = conPat1Radio;
}

public String getConDoc1Radio() {
	return conDoc1Radio;
}

public void setConDoc1Radio(String conDoc1Radio) {
	this.conDoc1Radio = conDoc1Radio;
}

public String getConRamco1Radio() {
	return conRamco1Radio;
}

public void setConRamco1Radio(String conRamco1Radio) {
	this.conRamco1Radio = conRamco1Radio;
}

public String getConPat2Radio() {
	return conPat2Radio;
}

public void setConPat2Radio(String conPat2Radio) {
	this.conPat2Radio = conPat2Radio;
}

public String getConDoc2Radio() {
	return conDoc2Radio;
}

public void setConDoc2Radio(String conDoc2Radio) {
	this.conDoc2Radio = conDoc2Radio;
}

public String getConRamco2Radio() {
	return conRamco2Radio;
}

public void setConRamco2Radio(String conRamco2Radio) {
	this.conRamco2Radio = conRamco2Radio;
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

 public void setPhcName(String phcName)
 {
   this.phcName = phcName;
 }

 public String getPhcName()
 {
   return phcName;
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

 public void setFeedbackFile(String feedbackFile)
 {
   this.feedbackFile = feedbackFile;
 }

 public String getFeedbackFile()
 {
   return feedbackFile;
 }

 public void setRenewalFlag(String renewalFlag)
 {
   this.renewalFlag = renewalFlag;
 }

 public String getRenewalFlag()
 {
   return renewalFlag;
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

 public void setRenewal(String renewal)
 {
   this.renewal = renewal;
 }

 public String getRenewal()
 {
   return renewal;
 }

 public void setDistrictHdr(String districtHdr)
 {
   this.districtHdr = districtHdr;
 }

 public String getDistrictHdr()
 {
   return districtHdr;
 }

 public void setMandalHdr(String mandalHdr)
 {
   this.mandalHdr = mandalHdr;
 }

 public String getMandalHdr()
 {
   return mandalHdr;
 }

 public void setVillageHdr(String villageHdr)
 {
   this.villageHdr = villageHdr;
 }

 public String getVillageHdr()
 {
   return villageHdr;
 }

 public void setHamletHdr(String hamletHdr)
 {
   this.hamletHdr = hamletHdr;
 }

 public String getHamletHdr()
 {
   return hamletHdr;
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

 public void setHemoCount(int hemoCount)
 {
   this.hemoCount = hemoCount;
 }

 public int getHemoCount()
 {
   return hemoCount;
 }

 public void setHemoCheck(int hemoCheck)
 {
   this.hemoCheck = hemoCheck;
 }

 public int getHemoCheck()
 {
   return hemoCheck;
 }

 public void setComplaintCount(int complaintCount)
 {
   this.complaintCount = complaintCount;
 }

 public int getComplaintCount()
 {
   return complaintCount;
 }

 public void setBlockedUser(String blockedUser)
 {
   this.blockedUser = blockedUser;
 }

 public String getBlockedUser()
 {
   return blockedUser;
 }

 public void setMedicalMngCase(int medicalMngCase)
 {
   this.medicalMngCase = medicalMngCase;
 }

 public int getMedicalMngCase()
 {
   return medicalMngCase;
 }

 public void setPayee(String payee)
 {
   this.payee = payee;
 }

 public String getPayee()
 {
   return payee;
 }

 public void setRenalCount(int renalCount)
 {
   this.renalCount = renalCount;
 }

 public int getRenalCount()
 {
   return renalCount;
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

 public void setHcFlag(String hcFlag)
 {
   this.hcFlag = hcFlag;
 }

 public String getHcFlag()
 {
   return hcFlag;
 }

 public void setTrustPaid(String trustPaid)
 {
   this.trustPaid = trustPaid;
 }

 public String getTrustPaid()
 {
   return trustPaid;
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

 public void setCmoAprvAmt(int cmoAprvAmt)
 {
   this.cmoAprvAmt = cmoAprvAmt;
 }

 public int getCmoAprvAmt()
 {
   return cmoAprvAmt;
 }

 public void setCeoAprvAmt(int ceoAprvAmt)
 {
   this.ceoAprvAmt = ceoAprvAmt;
 }

 public int getCeoAprvAmt()
 {
   return ceoAprvAmt;
 }

 public void setTrustAprvAmt(int trustAprvAmt)
 {
   this.trustAprvAmt = trustAprvAmt;
 }

 public int getTrustAprvAmt()
 {
   return trustAprvAmt;
 }

 public void setInsAprvAmt(int insAprvAmt)
 {
   this.insAprvAmt = insAprvAmt;
 }

 public int getInsAprvAmt()
 {
   return insAprvAmt;
 }

 public void setCooApprvAMT(int cooApprvAMT)
 {
   this.cooApprvAMT = cooApprvAMT;
 }

 public int getCooApprvAMT()
 {
   return cooApprvAMT;
 }

 public void setTrustEnhApprvAmt(int trustEnhApprvAmt)
 {
   this.trustEnhApprvAmt = trustEnhApprvAmt;
 }

 public int getTrustEnhApprvAmt()
 {
   return trustEnhApprvAmt;
 }

 public void setCommitteeEnhAmt(int committeeEnhAmt)
 {
   this.committeeEnhAmt = committeeEnhAmt;
 }

 public int getCommitteeEnhAmt()
 {
   return committeeEnhAmt;
 }

 public void setCeoEnhApprvAmt(int ceoEnhApprvAmt)
 {
   this.ceoEnhApprvAmt = ceoEnhApprvAmt;
 }

 public int getCeoEnhApprvAmt()
 {
   return ceoEnhApprvAmt;
 }

 public void setNwhEnhAmt(int nwhEnhAmt)
 {
   this.nwhEnhAmt = nwhEnhAmt;
 }

 public int getNwhEnhAmt()
 {
   return nwhEnhAmt;
 }

 public void setClaimAmount(int claimAmount)
 {
   this.claimAmount = claimAmount;
 }

 public int getClaimAmount()
 {
   return claimAmount;
 }

 public void setDoctorStat(String doctorStat)
 {
   this.doctorStat = doctorStat;
 }

 public String getDoctorStat()
 {
   return doctorStat;
 }

 public void setDoctorRmks(String doctorRmks)
 {
   this.doctorRmks = doctorRmks;
 }

 public String getDoctorRmks()
 {
   return doctorRmks;
 }

 public void setTrustStat(String trustStat)
 {
   this.trustStat = trustStat;
 }

 public String getTrustStat()
 {
   return trustStat;
 }

 public void setTrustRmks(String trustRmks)
 {
   this.trustRmks = trustRmks;
 }

 public String getTrustRmks()
 {
   return trustRmks;
 }

 public void setCeoStat(String ceoStat)
 {
   this.ceoStat = ceoStat;
 }

 public String getCeoStat()
 {
   return ceoStat;
 }

 public void setCeoRmks(String ceoRmks)
 {
   this.ceoRmks = ceoRmks;
 }

 public String getCeoRmks()
 {
   return ceoRmks;
 }

 public void setCmoStat(String cmoStat)
 {
   this.cmoStat = cmoStat;
 }

 public String getCmoStat()
 {
   return cmoStat;
 }

 public void setCmoRmks(String cmoRmks)
 {
   this.cmoRmks = cmoRmks;
 }

 public String getCmoRmks()
 {
   return cmoRmks;
 }

 public void setTechComStat(String techComStat)
 {
   this.techComStat = techComStat;
 }

 public String getTechComStat()
 {
   return techComStat;
 }

 public void setTechComRmks(String techComRmks)
 {
   this.techComRmks = techComRmks;
 }

 public String getTechComRmks()
 {
   return techComRmks;
 }

 public void setGmMedStat(String gmMedStat)
 {
   this.gmMedStat = gmMedStat;
 }

 public String getGmMedStat()
 {
   return gmMedStat;
 }

 public void setGmMedRmks(String gmMedRmks)
 {
   this.gmMedRmks = gmMedRmks;
 }

 public String getGmMedRmks()
 {
   return gmMedRmks;
 }

 public void setCeoEnStat(String ceoEnStat)
 {
   this.ceoEnStat = ceoEnStat;
 }

 public String getCeoEnStat()
 {
   return ceoEnStat;
 }

 public void setCeoEnRmks(String ceoEnRmks)
 {
   this.ceoEnRmks = ceoEnRmks;
 }

 public String getCeoEnRmks()
 {
   return ceoEnRmks;
 }

 public void setCaseRmks(ArrayList caseRmks)
 {
   this.caseRmks = caseRmks;
 }

 public ArrayList getCaseRmks()
 {
   return caseRmks;
 }

 public void setCardType(String cardType)
 {
   this.cardType = cardType;
 }

 public String getCardType()
 {
   return cardType;
 }

 public void setPhaseStDt(String phaseStDt)
 {
   this.phaseStDt = phaseStDt;
 }

 public String getPhaseStDt()
 {
   return phaseStDt;
 }

 public void setPhaseEdDt(String phaseEdDt)
 {
   this.phaseEdDt = phaseEdDt;
 }

 public String getPhaseEdDt()
 {
   return phaseEdDt;
 }

 public void setPreAmount(int preAmount)
 {
   this.preAmount = preAmount;
 }

 public int getPreAmount()
 {
   return preAmount;
 }

 public void setPreCochAmount(int preCochAmount)
 {
   this.preCochAmount = preCochAmount;
 }

 public int getPreCochAmount()
 {
   return preCochAmount;
 }

 public void setConsCount(int consCount)
 {
   this.consCount = consCount;
 }

 public int getConsCount()
 {
   return consCount;
 }

 public void setCounsCount(int counsCount)
 {
   this.counsCount = counsCount;
 }

 public int getCounsCount()
 {
   return counsCount;
 }

 public void setCardCount(int cardCount)
 {
   this.cardCount = cardCount;
 }

 public int getCardCount()
 {
   return cardCount;
 }

 public void setCnclCount(int cnclCount)
 {
   this.cnclCount = cnclCount;
 }

 public int getCnclCount()
 {
   return cnclCount;
 }

 public void setTechComCount(int techComCount)
 {
   this.techComCount = techComCount;
 }

 public int getTechComCount()
 {
   return techComCount;
 }

 public void setAdmCount(int admCount)
 {
   this.admCount = admCount;
 }

 public int getAdmCount()
 {
   return admCount;
 }

 public void setCaseCrtDt(String caseCrtDt)
 {
   this.caseCrtDt = caseCrtDt;
 }

 public String getCaseCrtDt()
 {
   return caseCrtDt;
 }

 public void setPreClnCount(int preClnCount)
 {
   this.preClnCount = preClnCount;
 }

 public int getPreClnCount()
 {
   return preClnCount;
 }

 public void setRmnClnCount(int rmnClnCount)
 {
   this.rmnClnCount = rmnClnCount;
 }

 public int getRmnClnCount()
 {
   return rmnClnCount;
 }

 public void setClinicalLst(ArrayList clinicalLst)
 {
   this.clinicalLst = clinicalLst;
 }

 public ArrayList getClinicalLst()
 {
   return clinicalLst;
 }

 public void setPolyDtls(HashMap polyDtls)
 {
   this.polyDtls = polyDtls;
 }

 public HashMap getPolyDtls()
 {
   return polyDtls;
 }

 public void setRejFlag(int rejFlag)
 {
   this.rejFlag = rejFlag;
 }

 public int getRejFlag()
 {
   return rejFlag;
 }

 public void setBedUpdFlag(String bedUpdFlag)
 {
   this.bedUpdFlag = bedUpdFlag;
 }

 public String getBedUpdFlag()
 {
   return bedUpdFlag;
 }

 public void setDeactDtls(HashMap deactDtls)
 {
   this.deactDtls = deactDtls;
 }

 public HashMap getDeactDtls()
 {
   return deactDtls;
 }

 public void setInApvCount(int inApvCount)
 {
   this.inApvCount = inApvCount;
 }

 public int getInApvCount()
 {
   return inApvCount;
 }

 public void setSurgCnt(ArrayList surgCnt)
 {
   this.surgCnt = surgCnt;
 }

 public ArrayList getSurgCnt()
 {
   return surgCnt;
 }

 public void setCochSurgCnt(int cochSurgCnt)
 {
   this.cochSurgCnt = cochSurgCnt;
 }

 public int getCochSurgCnt()
 {
   return cochSurgCnt;
 }

 public void setBillHeadName(String billHeadName)
 {
   this.billHeadName = billHeadName;
 }

 public String getBillHeadName()
 {
   return billHeadName;
 }

 public void setBillDt(String billDt)
 {
   this.billDt = billDt;
 }

 public String getBillDt()
 {
   return billDt;
 }

 public void setBillTime(String billTime)
 {
   this.billTime = billTime;
 }

 public String getBillTime()
 {
   return billTime;
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

 public void setDischargeDt(String dischargeDt)
 {
   this.dischargeDt = dischargeDt;
 }

 public String getDischargeDt()
 {
   return dischargeDt;
 }

 public void setDeathDt(String deathDt)
 {
   this.deathDt = deathDt;
 }

 public String getDeathDt()
 {
   return deathDt;
 }

 public void setDoctorDtls(String doctorDtls)
 {
   this.doctorDtls = doctorDtls;
 }

 public String getDoctorDtls()
 {
   return doctorDtls;
 }

 public void setTrustDtls(String trustDtls)
 {
   this.trustDtls = trustDtls;
 }

 public String getTrustDtls()
 {
   return trustDtls;
 }

 public void setCeoDtls(String ceoDtls)
 {
   this.ceoDtls = ceoDtls;
 }

 public String getCeoDtls()
 {
   return ceoDtls;
 }

 public void setCmoDtls(String cmoDtls)
 {
   this.cmoDtls = cmoDtls;
 }

 public String getCmoDtls()
 {
   return cmoDtls;
 }

 public void setTechComDtls(String techComDtls)
 {
   this.techComDtls = techComDtls;
 }

 public String getTechComDtls()
 {
   return techComDtls;
 }

 public void setGmMedDtls(String gmMedDtls)
 {
   this.gmMedDtls = gmMedDtls;
 }

 public String getGmMedDtls()
 {
   return gmMedDtls;
 }

 public void setCeoEnDtls(String ceoEnDtls)
 {
   this.ceoEnDtls = ceoEnDtls;
 }

 public String getCeoEnDtls()
 {
   return ceoEnDtls;
 }

 public void setClosedPhase(int closedPhase)
 {
   this.closedPhase = closedPhase;
 }

 public int getClosedPhase()
 {
   return closedPhase;
 }

 public void setSurgScheme(String surgScheme)
 {
   this.surgScheme = surgScheme;
 }

 public String getSurgScheme()
 {
   return surgScheme;
 }

 public void setIsApproved(String isApproved)
 {
   this.isApproved = isApproved;
 }

 public String getIsApproved()
 {
   return isApproved;
 }

 public void setMedicalMng(String medicalMng)
 {
   this.MedicalMng = medicalMng;
 }

 public String getMedicalMng()
 {
   return MedicalMng;
 }

 public void setUpdType(String updType)
 {
   this.updType = updType;
 }

 public String getUpdType()
 {
   return updType;
 }
//start 001
 public void setStorage(String storage)
 {
   this.storage = storage;
 }

 public String getStorage()
 {
   return storage;
 }

 public void setSurgCount(int surgCount)
 {
   this.surgCount = surgCount;
 }

 public int getSurgCount()
 {
   return surgCount;
 }

 public void setDeathCount(int deathCount)
 {
   this.deathCount = deathCount;
 }

 public int getDeathCount()
 {
   return deathCount;
 }

 public void setDisCount(int disCount)
 {
   this.disCount = disCount;
 }

 public int getDisCount()
 {
   return disCount;
 }

 public void setDisSumCount(int disSumCount)
 {
   this.disSumCount = disSumCount;
 }

 public int getDisSumCount()
 {
   return disSumCount;
 }

 public void setBlockSurgDt(String blockSurgDt)
 {
   this.blockSurgDt = blockSurgDt;
 }

 public String getBlockSurgDt()
 {
   return blockSurgDt;
 }
 //end 001

//start 002
   public void setSplCommittee(String splCommittee) {
       this.splCommittee = splCommittee;
   }

   public String getSplCommittee() {
       return splCommittee;
   }
//end 002
//start 003
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
 //end 003

 public void setScheduleId(String scheduleId)
 {
   this.scheduleId = scheduleId;
 }

 public String getScheduleId()
 {
   return scheduleId;
 }

 public void setCommitteeMem(String committeeMem)
 {
   this.committeeMem = committeeMem;
 }

 public String getCommitteeMem()
 {
   return committeeMem;
 }
//start 004
 public void setCnclReqDt(String cnclReqDt)
 {
   this.cnclReqDt = cnclReqDt;
 }

 public String getCnclReqDt()
 {
   return cnclReqDt;
 }

 public void setCnclApvDt(String cnclApvDt)
 {
   this.cnclApvDt = cnclApvDt;
 }

 public String getCnclApvDt()
 {
   return cnclApvDt;
 }//end 004
//start 005
 public void setEnhReqDt(String enhReqDt)
 {
   this.enhReqDt = enhReqDt;
 }

 public String getEnhReqDt()
 {
   return enhReqDt;
 }

 public void setEnhApvDt(String enhApvDt)
 {
   this.enhApvDt = enhApvDt;
 }

 public String getEnhApvDt()
 {
   return enhApvDt;
 }

 public void setEnhRejDt(String enhRejDt)
 {
   this.enhRejDt = enhRejDt;
 }

 public String getEnhRejDt()
 {
   return enhRejDt;
 }

 public void setApvCount(int apvCount)
 {
   this.apvCount = apvCount;
 }

 public int getApvCount()
 {
   return apvCount;
 }//end 005
 //Start 006
 public void setUserRole ( String userRole )
   {
     this.userRole = userRole;
   }
   public String getUserRole ()
   {
     return userRole ;
   }//End 006
//start 007
   public void setCaseRenewal(String caseRenewal) {
       this.caseRenewal = caseRenewal;
   }

   public String getCaseRenewal() {
       return caseRenewal;
   }

   public void setProcessFlag(String processFlag) {
       this.processFlag = processFlag;
   }

   public String getProcessFlag() {
       return processFlag;
   }//end 007
//start 008
   public void setFieldAttachFlag(String fieldAttachFlag) {
       this.fieldAttachFlag = fieldAttachFlag;
   }

   public String getFieldAttachFlag() {
       return fieldAttachFlag;
   }//end 008


   public void setCochCnt(String cochCnt) {
       this.cochCnt = cochCnt;
   }

   public String getCochCnt() {
       return cochCnt;
   }
   //start:010
   public void setErrStatus(String errStatus) {
       this.errStatus = errStatus;
   }

   public String getErrStatus() {
       return errStatus;
   }//end:010
   //start 011
   public void setJournalistRenewal(String journalistRenewal) {
       this.journalistRenewal = journalistRenewal;
   }

   public String getJournalistRenewal() {
       return journalistRenewal;
   }//end 011
//start 012
   /*public void setEmpnl_cmb_name(String empnl_cmb_name) {
       this.empnl_cmb_name = empnl_cmb_name;
   }

   public String getEmpnl_cmb_name() {
       return empnl_cmb_name;
   }*///end 012
    //Start 013
   public void setCmcoRenewal(String cmcoRenewal) {
       this.cmcoRenewal = cmcoRenewal;
   }

   public String getCmcoRenewal() {
       return cmcoRenewal;
   }
   //End 013
   //start 014
   public void setPreAttachCount(int preAttachCount) {
       this.preAttachCount = preAttachCount;
   }

   public int getPreAttachCount() {
       return preAttachCount;
   }//end 014
    //start 015
   public void setCaseAprvDt(String caseAprvDt) {
       this.caseAprvDt = caseAprvDt;
   }

   public String getCaseAprvDt() {
       return caseAprvDt;
   }//end 015

   //start 016
   public void setEnhCount(int enhCount) {
       this.enhCount = enhCount;
   }

   public int getEnhCount() {
       return enhCount;
   }//end 016
	//Start 017
	public void setTelSurgId(String telSurgId) {
       this.telSurgId = telSurgId;
   }

   public String getTelSurgId() {
       return telSurgId;
   }// End 017

   //Start 018
   public void setHealthCardAttach(ArrayList healthCardAttach) {
       this.healthCardAttach = healthCardAttach;
   }

   public ArrayList getHealthCardAttach() {
       return healthCardAttach;
   }
   //End 018
//Start 019
   public void setCochAttachCount(int cochAttachCount) {
       this.cochAttachCount = cochAttachCount;
   }

   public int getCochAttachCount() {
       return cochAttachCount;
   }
   //End 019
   //Start 020
   public void setPatRegDate(String patRegDate) {
       this.patRegDate = patRegDate;
   }

   public String getPatRegDate() {
       return patRegDate;
   }
   //End 020
   public void setCmoEnhStat(String cmoEnhStat) {
       this.cmoEnhStat = cmoEnhStat;
   }

   public String getCmoEnhStat() {
       return cmoEnhStat;
   }
       //start 021
   public void setCmoEnhRmks(String cmoEnhRmks) {
       this.cmoEnhRmks = cmoEnhRmks;
   }

   public String getCmoEnhRmks() {
       return cmoEnhRmks;
   }

   public void setCmoEnhDtls(String cmoEnhDtls) {
       this.cmoEnhDtls = cmoEnhDtls;
   }

   public String getCmoEnhDtls() {
       return cmoEnhDtls;
   }

   public void setCmoEnhApprvAmt(int cmoEnhApprvAmt) {
       this.cmoEnhApprvAmt = cmoEnhApprvAmt;
   }

   public int getCmoEnhApprvAmt() {
       return cmoEnhApprvAmt;
   }//end 021


   public void setDocumentPendingCnt(int documentPendingCnt) {//start 022
       this.documentPendingCnt = documentPendingCnt;
   }

   public int getDocumentPendingCnt() {
       return documentPendingCnt;
   }//End 022

   public void setAllSurgeries(String allSurgeries) {
       this.allSurgeries = allSurgeries;
   }

   public String getAllSurgeries() {
       return allSurgeries;
   }

   public void setDeductorType(String deductorType) {
       this.deductorType = deductorType;
   }

   public String getDeductorType() {
       return deductorType;
   }

   public void setPrevMnthPreSurgery(String prevMnthPreSurgery) {
       this.prevMnthPreSurgery = prevMnthPreSurgery;
   }

   public String getPrevMnthPreSurgery() {
       return prevMnthPreSurgery;
   }

   public void setCmaAuditCnt(int cmaAuditCnt) {
       this.cmaAuditCnt = cmaAuditCnt;
   }

   public int getCmaAuditCnt() {
       return cmaAuditCnt;
   }

   public void setHospType(String hospType) {
       this.hospType = hospType;
   }

   public String getHospType() {
       return hospType;
   }

   public void setDisplayBiometric(String displayBiometric) {
       this.displayBiometric = displayBiometric;
   }

   public String getDisplayBiometric() {
       return displayBiometric;
   }
   public void setFeedbckcnt(int feedbckcnt) {
       this.feedbckcnt = feedbckcnt;
   }

   public int getFeedbckcnt() {
       return feedbckcnt;
   }

   public void setBiomEmrChkFlag(String biomEmrChkFlag) {
       this.biomEmrChkFlag = biomEmrChkFlag;
   }

   public String getBiomEmrChkFlag() {
       return biomEmrChkFlag;
   }

   public void setBiomEmrChkRmks(String biomEmrChkRmks) {
       this.biomEmrChkRmks = biomEmrChkRmks;
   }

   public String getBiomEmrChkRmks() {
       return biomEmrChkRmks;
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

 

   public void setAuditAction(String auditAction) {
       this.auditAction = auditAction;
   }

   public String getAuditAction() {
       return auditAction;
   }

   public void setAuditRole(String auditRole) {
       this.auditRole = auditRole;
   }

   public String getAuditRole() {
       return auditRole;
   }

   public void setAuditAmount(Long i) {
       this.auditAmount = i;
   }

   public Long getAuditAmount() {
       return auditAmount;
   }

   public void setAuditComboRole(String auditComboRole) {
       this.auditComboRole = auditComboRole;
   }

   public String getAuditComboRole() {
       return auditComboRole;
   }

	public String getIsFlwExtflg() {
		return isFlwExtflg;
	}
	
	public void setIsFlwExtflg(String isFlwExtflg) {
		this.isFlwExtflg = isFlwExtflg;
	}

	public String getOldClaimBill() {
		return oldClaimBill;
	}

	public void setOldClaimBill(String oldClaimBill) {
		this.oldClaimBill = oldClaimBill;
	}

	public int getFdtdAttachCount() {
		return fdtdAttachCount;
	}

	public void setFdtdAttachCount(int fdtdAttachCount) {
		this.fdtdAttachCount = fdtdAttachCount;
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
	public String getPreauthTotalPackageAmt() {
		return preauthTotalPackageAmt;
	}
	public void setPreauthTotalPackageAmt(String preauthTotalPackageAmt) {
		this.preauthTotalPackageAmt = preauthTotalPackageAmt;
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
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	public String getCochlearYN() {
		return cochlearYN;
	}
	public void setCochlearYN(String cochlearYN) {
		this.cochlearYN = cochlearYN;
	}
	public String getCochlearQues() {
		return cochlearQues;
	}
	public void setCochlearQues(String cochlearQues) {
		this.cochlearQues = cochlearQues;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getMedcoId() {
		return medcoId;
	}
	public void setMedcoId(String medcoId) {
		this.medcoId = medcoId;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getCaseNabhFlg() {
		return caseNabhFlg;
	}
	public void setCaseNabhFlg(String caseNabhFlg) {
		this.caseNabhFlg = caseNabhFlg;
	}
	public String getPatientScheme() {
		return patientScheme;
	}
	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	public String getCASEID() {
		return CASEID;
	}
	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}
	public String getEnhFlag() {
		return enhFlag;
	}
	public void setEnhFlag(String enhFlag) {
		this.enhFlag = enhFlag;
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
	public String getSlabAmt() {
		return slabAmt;
	}
	public void setSlabAmt(String slabAmt) {
		this.slabAmt = slabAmt;
	}
	public long getPREAUTHAPPVAMT() {
		return PREAUTHAPPVAMT;
	}
	public void setPREAUTHAPPVAMT(long pREAUTHAPPVAMT) {
		PREAUTHAPPVAMT = pREAUTHAPPVAMT;
	}
	public long getCOMORBIDAPPVAMT() {
		return COMORBIDAPPVAMT;
	}
	public void setCOMORBIDAPPVAMT(long cOMORBIDAPPVAMT) {
		COMORBIDAPPVAMT = cOMORBIDAPPVAMT;
	}
	public long getENHAPPVAMT() {
		return ENHAPPVAMT;
	}
	public void setENHAPPVAMT(long eNHAPPVAMT) {
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
	public String getSurgProcUnits() {
		return surgProcUnits;
	}
	public void setSurgProcUnits(String surgProcUnits) {
		this.surgProcUnits = surgProcUnits;
	}
	public String getFlpSurgCount() {
		return flpSurgCount;
	}
	public void setFlpSurgCount(String flpSurgCount) {
		this.flpSurgCount = flpSurgCount;
	}
	public String getSurgCountStr() {
		return surgCountStr;
	}
	public void setSurgCountStr(String surgCountStr) {
		this.surgCountStr = surgCountStr;
	}
	public String getToothedUnits() {
		return toothedUnits;
	}
	public void setToothedUnits(String toothedUnits) {
		this.toothedUnits = toothedUnits;
	}
	public String getMedcoProcUnits() {
		return medcoProcUnits;
	}
	public void setMedcoProcUnits(String medcoProcUnits) {
		this.medcoProcUnits = medcoProcUnits;
	}
	public String getCtdProcUnits() {
		return ctdProcUnits;
	}
	public void setCtdProcUnits(String ctdProcUnits) {
		this.ctdProcUnits = ctdProcUnits;
	}
	public String getChProcUnits() {
		return chProcUnits;
	}
	public void setChProcUnits(String chProcUnits) {
		this.chProcUnits = chProcUnits;
	}
	public String getOrganTransYN() {
		return organTransYN;
	}
	public void setOrganTransYN(String organTransYN) {
		this.organTransYN = organTransYN;
	}

	/**
	 * Added by ramalakshmi for hubspoke CR
	 */
	private String hubHospId;
	private String groupId;


	public String getHubHospId() {
		return hubHospId;
	}
	public void setHubHospId(String hubHospId) {
		this.hubHospId = hubHospId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
}

