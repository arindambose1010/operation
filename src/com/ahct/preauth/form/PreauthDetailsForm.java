package com.ahct.preauth.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PreauthVO;

public class PreauthDetailsForm extends ActionForm{
private String docType;
private String docSpec;
private String docAval;
private String presentillness;
private String pasthistory;
private String examinationFindings;
private String provisionalDisg;
private String routine;
private String special;
private String finalDig;
private String category;
private String subCategory;
private String surgery;
private Long cycles;
private String medicalSpclty;
private String treatingDocRmks;
private List<AttachmentVO> lstAttachments;
private String splInvest ;
private String caseId;
private String caseNo;
private String telephonicId;
private String telephonicRemarks;
private String telephonicFlag;
private String teleRegDate;
private List<PreauthVO> lstPreauthVO;
private String casSugeryId;
private String totPkgAmt;
private String therapyType;
private String status;
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
private String patientId;
private String quesFlag;
private String genRemarks;
private List<PreauthVO> lstworkFlow;
private FormFile medclExpnsAttch[]= new FormFile[300];
private FormFile medclDrugsAttch[]= new FormFile[300];
private String docMobNo;
private String treatmntDt;
private String docName;
private String docReg;
private String docQual;
private String hospAddress;
private String hospContactNo;
private String hospitalName;
private String dischrgeDt;
private String patientInTime;
private String patientOutTime;
private String icdCatCode;
private String procCode;
private String icdProcCode;
private String admissionRadio;
private String admissionDate;
private String admissionType;

private String diagnosisType;
private String mainCatName;
private String catName;
private String subCatName;
private String disName;
private String disAnatomicalSitename;


private String cardNo;
private String mandal;
private String village;
private String regDate;
private String district;
private String schemeId;
private String ceoRemark;
private String msg;
/**
 * Enhancement in preauth
 */
private String hospStay;
private String hospQuantity;
private String hospQuantNo;
private String hospCode;
private String imageology;
private String imageCode;
private String imageQuantity;
private String labInvest;
private String labInvestCode;
private String labInvestQuantity;
private String drugs;
private String drugCode;
private String drugQuantity;
private String implants;
private String implantCode;
private String implantQuantity;
private String hospCodeUnit;
private String enhancementDtls;
private String imageCodeUnit;
private String labInvestCodeUnit;
private String drugCodeUnit;
private String implantCodeUnit;
private List<LabelBean> buttonsLst;
private String caseStatus;
private String prevConAmt;
private String enhAmt;
private String enhApprAmt;
private List<PreauthVO> enhamcementList;
private String remEnhList;
private String apprvdPckAmt;
private String ipRegDate;

private List<PreauthVO> lstEnhancementworkFlow;
private String enhancementFlag;
private List<LabelBean> enhButtonsLst;

private String enhReqAmt;
private String enhApprvAmt;
private String enhRemarks;
private String enhApprvDt;
private String comorBidAmt;
private String comorBidVals;
private String comorBidAppvAmt;
private String nabhFlg;
private String preauthPckgAmt;
private String nabhAmt;
private String ptdTotalApprvAmt;
private List<LabelBean> comorbidValues;

private String pendingFlag;
private String enhRejDate;
private String hospStayAmt;
private List<DrugsVO> drugLt;
private String drugTypeCode;
private String drugSubTypeCode;
private String drugSubTypeName;
private String asriDrugCode;
private String drugName;
private String route;
private String strength;
private String dosage;
private String medicatPeriod;
private String drugCode1;
private String drugAmount;
private String drugQuantity1;
private String drugDeletionString;
private String docSpecReg;
private String hospitalId;
private String preauthTotalPackageAmt;

private String treatDocName;
private String treatDocQualification;
private String treatDocRegNo;
private String treatDocContact;
private String activeYN;

private String typeOfProcedure;
private String procedureConsent;
private String medCardioClearence;
private String bloodTransfusion;
private String cancelRemarks;
private String procUnits;
private String state;
private String cochlearYN;
private String cochlearQuestionnaire;
private String disDate;
private String disRemarks;
private String medSurgEnhFlag;


/*for cochlear case*/
 
 
	private String childName;
	private String fatherName;
	private String childAge;
	
	private String assessDate;
	private String claimNo;
	private String enhAmounts;
	private String onsetAge;
	private String interventionAge;
	private String conventionalAids;
	private String benifitFromConventional;
	private String auditory;
	private String oralaural;
	private String speakRead;
	private String motherAwareness;
	private String audioVerbal;
	private String motivationSpeech;
	private String motivationAudio;
	private String realisticExpect;
	private String middleEar;
	private String congenital;
	private String bonyCapsule;
	private String mileStones;
	private String speechMechanism;
	private String ADHD;
	private String stableQuiet;
	private String autistic;
	private String stubborn;
	private String imitative;
	private String fitUnfit;
	private String medcoRmrks;
	
	private String onsetAge_remarks;
	private String interventionAge_remarks;
	private String conventionalAids_remarks;
	private String benifitFromConventional_remarks;
	private String auditory_remarks;
	private String oralaural_remarks;
	private String speakRead_remarks;
	private String motherAwareness_remarks;
	private String motivationSpeech_remarks;
	private String motivationAudio_remarks;
	private String realisticExpect_remarks;
	private String middleEar_remarks;
	private String congenital_remarks;
	private String bonyCapsule_remarks;
	private String mileStones_remarks;
	private String speechMechanism_remarks;
	private String ADHD_remarks;
	private String stableQuiet_remarks;
	private String autistic_remarks;
	private String stubborn_remarks;
	private String imitative_remarks;
	private String fitUnfit_remarks;
	private String audioVerbal_remarks;
	private int preauthNabhAmt;
	private String patientScheme;
	
	private String fname;
	private String gender;
	private String dateOfBirth;
	private String years;
	private String months;
	private String hno;
	private String street;
	private String pin;
	private String photoUrl;
	private String days;
	private String slab;
	private String occupation;
	private String contactno;
private String scheme;
private String telScheme;
private String dtTime;
private String caste;
private String ipNo;
	
private String medicalCat;
private String organTransYN;
private String holdRemarks;
public FormFile getMedclDrugsAttch(int index) {
	return medclDrugsAttch[index];
}
public void setMedclDrugsAttch(int index,FormFile value) {
	this.medclDrugsAttch[index] = value;
}
public FormFile[] getMedclDrugsAttch() {
	return medclDrugsAttch;
}
public void setMedclDrugsAttch(FormFile[] medclDrugsAttch) {
	this.medclDrugsAttch = medclDrugsAttch;
}


public FormFile getMedclExpnsAttch(int index) {
	return medclExpnsAttch[index];
}
public void setMedclExpnsAttch(int index,FormFile value) {
	this.medclExpnsAttch[index] = value;
}
public FormFile[] getMedclExpnsAttch() {
	return medclExpnsAttch;
}
public void setMedclExpnsAttch(FormFile[] medclExpnsAttch) {
	this.medclExpnsAttch = medclExpnsAttch;
}

public String getMedicalCat() {
	return medicalCat;
}

public void setMedicalCat(String medicalCat) {
	this.medicalCat = medicalCat;
}
	
 

public int getPreauthNabhAmt() {
		return preauthNabhAmt;
	}

	public void setPreauthNabhAmt(int preauthNabhAmt) {
		this.preauthNabhAmt = preauthNabhAmt;
	}

public String getCancelRemarks() {
	return cancelRemarks;
}

public void setCancelRemarks(String cancelRemarks) {
	this.cancelRemarks = cancelRemarks;
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

public String getTreatDocName() {
	return treatDocName;
}

public void setTreatDocName(String treatDocName) {
	this.treatDocName = treatDocName;
}

public String getTreatDocQualification() {
	return treatDocQualification;
}

public void setTreatDocQualification(String treatDocQualification) {
	this.treatDocQualification = treatDocQualification;
}

public String getTreatDocRegNo() {
	return treatDocRegNo;
}

public void setTreatDocRegNo(String treatDocRegNo) {
	this.treatDocRegNo = treatDocRegNo;
}

public String getTreatDocContact() {
	return treatDocContact;
}

public void setTreatDocContact(String treatDocContact) {
	this.treatDocContact = treatDocContact;
}

public String getHospitalId() {
	return hospitalId;
}

public void setHospitalId(String hospitalId) {
	this.hospitalId = hospitalId;
}

public String getDocSpecReg() {
	return docSpecReg;
}

public void setDocSpecReg(String docSpecReg) {
	this.docSpecReg = docSpecReg;
}

public String getDrugDeletionString() {
	return drugDeletionString;
}

public void setDrugDeletionString(String drugDeletionString) {
	this.drugDeletionString = drugDeletionString;
}

public String getDrugCode1() {
	return drugCode1;
}

public void setDrugCode1(String drugCode1) {
	this.drugCode1 = drugCode1;
}

public String getDrugQuantity1() {
	return drugQuantity1;
}

public void setDrugQuantity1(String drugQuantity1) {
	this.drugQuantity1 = drugQuantity1;
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

public void setDrugQuantity(String drugQuantity) {
	this.drugQuantity = drugQuantity;
}

public List<DrugsVO> getDrugLt() {
	return drugLt;
}

public void setDrugLt(List<DrugsVO> drugLt) {
	this.drugLt = drugLt;
}

public String getHospStayAmt() {
	return hospStayAmt;
}

public void setHospStayAmt(String hospStayAmt) {
	this.hospStayAmt = hospStayAmt;
}

public String getPendingFlag() {
	return pendingFlag;
}

public void setPendingFlag(String pendingFlag) {
	this.pendingFlag = pendingFlag;
}

public String getEnhRejDate() {
	return enhRejDate;
}

public void setEnhRejDate(String enhRejDate) {
	this.enhRejDate = enhRejDate;
}

public List<LabelBean> getComorbidValues() {
	return comorbidValues;
}

public void setComorbidValues(
		List<LabelBean> comorbidValues) {
	this.comorbidValues = comorbidValues;
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

public String getEnhApprvDt() {
	return enhApprvDt;
}

public void setEnhApprvDt(String enhApprvDt) {
	this.enhApprvDt = enhApprvDt;
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

public List<LabelBean> getEnhButtonsLst() {
	return enhButtonsLst;
}

public void setEnhButtonsLst(List<LabelBean> enhButtonsLst) {
	this.enhButtonsLst = enhButtonsLst;
}

public List<PreauthVO> getLstEnhancementworkFlow() {
	return lstEnhancementworkFlow;
}

public void setLstEnhancementworkFlow(List<PreauthVO> lstEnhancementworkFlow) {
	this.lstEnhancementworkFlow = lstEnhancementworkFlow;
}

public String getEnhancementFlag() {
	return enhancementFlag;
}

public void setEnhancementFlag(String enhancementFlag) {
	this.enhancementFlag = enhancementFlag;
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

public String getRemEnhList() {
	return remEnhList;
}

public void setRemEnhList(String remEnhList) {
	this.remEnhList = remEnhList;
}

public List<PreauthVO> getEnhamcementList() {
	return enhamcementList;
}

public void setEnhamcementList(List<PreauthVO> enhamcementList) {
	this.enhamcementList = enhamcementList;
}

public String getEnhAmt() {
	return enhAmt;
}

public void setEnhAmt(String enhAmt) {
	this.enhAmt = enhAmt;
}

public String getEnhApprAmt() {
	return enhApprAmt;
}

public void setEnhApprAmt(String enhApprAmt) {
	this.enhApprAmt = enhApprAmt;
}

public String getPrevConAmt() {
	return prevConAmt;
}

public void setPrevConAmt(String prevConAmt) {
	this.prevConAmt = prevConAmt;
}

public String getCaseStatus() {
	return caseStatus;
}

public void setCaseStatus(String caseStatus) {
	this.caseStatus = caseStatus;
}

public List<LabelBean> getButtonsLst() {
	return buttonsLst;
}

public void setButtonsLst(List<LabelBean> buttonsLst) {
	this.buttonsLst = buttonsLst;
}

public String getAdmissionType() {
	return admissionType;
}

public void setAdmissionType(String admissionType) {
	this.admissionType = admissionType;
}

public String getImageCodeUnit() {
	return imageCodeUnit;
}

public void setImageCodeUnit(String imageCodeUnit) {
	this.imageCodeUnit = imageCodeUnit;
}

public String getLabInvestCodeUnit() {
	return labInvestCodeUnit;
}

public void setLabInvestCodeUnit(String labInvestCodeUnit) {
	this.labInvestCodeUnit = labInvestCodeUnit;
}

public String getDrugCodeUnit() {
	return drugCodeUnit;
}

public void setDrugCodeUnit(String drugCodeUnit) {
	this.drugCodeUnit = drugCodeUnit;
}

public String getImplantCodeUnit() {
	return implantCodeUnit;
}

public void setImplantCodeUnit(String implantCodeUnit) {
	this.implantCodeUnit = implantCodeUnit;
}

public String getEnhancementDtls() {
	return enhancementDtls;
}

public void setEnhancementDtls(String enhancementDtls) {
	this.enhancementDtls = enhancementDtls;
}

public String getHospCodeUnit() {
	return hospCodeUnit;
}

public void setHospCodeUnit(String hospCodeUnit) {
	this.hospCodeUnit = hospCodeUnit;
}

public String getHospQuantNo() {
	return hospQuantNo;
}

public void setHospQuantNo(String hospQuantNo) {
	this.hospQuantNo = hospQuantNo;
}

public String getHospCode() {
	return hospCode;
}

public void setHospCode(String hospCode) {
	this.hospCode = hospCode;
}

public String getImageology() {
	return imageology;
}

public void setImageology(String imageology) {
	this.imageology = imageology;
}

public String getImageCode() {
	return imageCode;
}

public void setImageCode(String imageCode) {
	this.imageCode = imageCode;
}

public String getImageQuantity() {
	return imageQuantity;
}

public void setImageQuantity(String imageQuantity) {
	this.imageQuantity = imageQuantity;
}

public String getLabInvest() {
	return labInvest;
}

public void setLabInvest(String labInvest) {
	this.labInvest = labInvest;
}

public String getLabInvestCode() {
	return labInvestCode;
}

public void setLabInvestCode(String labInvestCode) {
	this.labInvestCode = labInvestCode;
}

public String getLabInvestQuantity() {
	return labInvestQuantity;
}

public void setLabInvestQuantity(String labInvestQuantity) {
	this.labInvestQuantity = labInvestQuantity;
}

public String getDrugs() {
	return drugs;
}

public void setDrugs(String drugs) {
	this.drugs = drugs;
}

public String getDrugCode() {
	return drugCode;
}

public void setDrugCode(String drugCode) {
	this.drugCode = drugCode;
}

public String getDrugQuantity() {
	return drugQuantity;
}

public void setDrugQuan(String drugQuantity) {
	this.drugQuantity = drugQuantity;
}

public String getImplants() {
	return implants;
}

public void setImplants(String implants) {
	this.implants = implants;
}

public String getImplantCode() {
	return implantCode;
}

public void setImplantCode(String implantCode) {
	this.implantCode = implantCode;
}

public String getImplantQuantity() {
	return implantQuantity;
}

public void setImplantQuantity(String implantQuantity) {
	this.implantQuantity = implantQuantity;
}

public String getHospQuantity() {
	return hospQuantity;
}

public void setHospQuantity(String hospQuantity) {
	this.hospQuantity = hospQuantity;
}

public String getHospStay() {
	return hospStay;
}

public void setHospStay(String hospStay) {
	this.hospStay = hospStay;
}

public String getDistrict() {
	return district;
}

public void setDistrict(String district) {
	this.district = district;
}

public String getCardNo() {
	return cardNo;
}

public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}

public String getMandal() {
	return mandal;
}

public void setMandal(String mandal) {
	this.mandal = mandal;
}

public String getVillage() {
	return village;
}

public void setVillage(String village) {
	this.village = village;
}

public String getRegDate() {
	return regDate;
}

public void setRegDate(String regDate) {
	this.regDate = regDate;
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

public String getHospAddress() {
	return hospAddress;
}

public void setHospAddress(String hospAddress) {
	this.hospAddress = hospAddress;
}

public String getHospContactNo() {
	return hospContactNo;
}

public void setHospContactNo(String hospContactNo) {
	this.hospContactNo = hospContactNo;
}

public String getHospitalName() {
	return hospitalName;
}

public void setHospitalName(String hospitalName) {
	this.hospitalName = hospitalName;
}

public List<PreauthVO> getLstworkFlow() {
	return lstworkFlow;
}

public void setLstworkFlow(List<PreauthVO> lstworkFlow) {
	this.lstworkFlow = lstworkFlow;
}

public String getGenRemarks() {
	return genRemarks;
}

public void setGenRemarks(String genRemarks) {
	this.genRemarks = genRemarks;
}

public String getQuesFlag() {
	return quesFlag;
}

public void setQuesFlag(String quesFlag) {
	this.quesFlag = quesFlag;
}

public String getPatientId() {
	return patientId;
}

public void setPatientId(String patientId) {
	this.patientId = patientId;
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

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getTherapyType() {
	return therapyType;
}

public void setTherapyType(String therapyType) {
	this.therapyType = therapyType;
}

public String getTotPkgAmt() {
	return totPkgAmt;
}

public void setTotPkgAmt(String totPkgAmt) {
	this.totPkgAmt = totPkgAmt;
}

public String getCasSugeryId() {
	return casSugeryId;
}

public void setCasSugeryId(String casSugeryId) {
	this.casSugeryId = casSugeryId;
}

public List<PreauthVO> getLstPreauthVO() {
	return lstPreauthVO;
}

public void setLstPreauthVO(List<PreauthVO> lstPreauthVO) {
	this.lstPreauthVO = lstPreauthVO;
}

public String getTelephonicId() {
	return telephonicId;
}

public void setTelephonicId(String telephonicId) {
	this.telephonicId = telephonicId;
}

public String getTelephonicRemarks() {
	return telephonicRemarks;
}

public void setTelephonicRemarks(String telephonicRemarks) {
	this.telephonicRemarks = telephonicRemarks;
}

public String getTelephonicFlag() {
	return telephonicFlag;
}

public void setTelephonicFlag(String telephonicFlag) {
	this.telephonicFlag = telephonicFlag;
}

public String getCaseId() {
	return caseId;
}

public void setCaseId(String caseId) {
	this.caseId = caseId;
}

public String getCaseNo() {
	return caseNo;
}

public void setCaseNo(String caseNo) {
	this.caseNo = caseNo;
}

public List<AttachmentVO> getLstAttachments() {
	return lstAttachments;
}

public String getSplInvest (  )
{
  return splInvest ;
}
public void setSplInvest (  String value )
{
	splInvest = value ;
}
public void setLstAttachments(List<AttachmentVO> lstAttachments) {
	this.lstAttachments = lstAttachments;
}
private FormFile attachAry[] = new FormFile[200];
public void setAttachAry ( FormFile[] attachAry )
  {
    this.attachAry = attachAry;
  }
  public FormFile[] getAttachAry ()
  {
    return attachAry ;
  }
  public FormFile getAttachedIndex ( int index )
    {
      return attachAry[ index ] ;
    }
    public void setAttachedIndex ( int index, FormFile value )
    {
      attachAry[ index ] = value ;
    }
   
    public FormFile getSgndApplnForm ( int index )
    {
      return attachAry[ index ] ;
    }
    public void setSgndApplnForm ( int index, FormFile value )
    {
      attachAry[ index ] = value ;
    } 
    
  


public String getDocType() {
	return docType;
}
public void setDocType(String docType) {
	this.docType = docType;
}
public String getDocSpec() {
	return docSpec;
}
public void setDocSpec(String docSpec) {
	this.docSpec = docSpec;
}
public String getDocAval() {
	return docAval;
}
public void setDocAval(String docAval) {
	this.docAval = docAval;
}
public String getPresentillness() {
	return presentillness;
}
public void setPresentillness(String presentillness) {
	this.presentillness = presentillness;
}
public String getPasthistory() {
	return pasthistory;
}
public void setPasthistory(String pasthistory) {
	this.pasthistory = pasthistory;
}
public String getExaminationFindings() {
	return examinationFindings;
}
public void setExaminationFindings(String examinationFindings) {
	this.examinationFindings = examinationFindings;
}
public String getProvisionalDisg() {
	return provisionalDisg;
}
public void setProvisionalDisg(String provisionalDisg) {
	this.provisionalDisg = provisionalDisg;
}
public String getRoutine() {
	return routine;
}
public void setRoutine(String routine) {
	this.routine = routine;
}
public String getSpecial() {
	return special;
}
public void setSpecial(String special) {
	this.special = special;
}
public String getFinalDig() {
	return finalDig;
}
public void setFinalDig(String finalDig) {
	this.finalDig = finalDig;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getSubCategory() {
	return subCategory;
}
public void setSubCategory(String subCategory) {
	this.subCategory = subCategory;
}
public String getSurgery() {
	return surgery;
}
public void setSurgery(String surgery) {
	this.surgery = surgery;
}
public String getTreatingDocRmks() {
	return treatingDocRmks;
}
public void setTreatingDocRmks(String treatingDocRmks) {
	this.treatingDocRmks = treatingDocRmks;
}

public String getPreauthTotalPackageAmt() {
	return preauthTotalPackageAmt;
}

public void setPreauthTotalPackageAmt(String preauthTotalPackageAmt) {
	this.preauthTotalPackageAmt = preauthTotalPackageAmt;
}

public String getProcUnits() {
	return procUnits;
}

public void setProcUnits(String procUnits) {
	this.procUnits = procUnits;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCochlearYN() {
	return cochlearYN;
}

public void setCochlearYN(String cochlearYN) {
	this.cochlearYN = cochlearYN;
}

public String getActiveYN() {
	return activeYN;
}

public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}

public String getSchemeId() {
	return schemeId;
}

public void setSchemeId(String schemeId) {
	this.schemeId = schemeId;
}

public String getChildName() {
	return childName;
}

public void setChildName(String childName) {
	this.childName = childName;
}

public String getFatherName() {
	return fatherName;
}

public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}

public String getChildAge() {
	return childAge;
}

public void setChildAge(String childAge) {
	this.childAge = childAge;
}

public String getAssessDate() {
	return assessDate;
}

public void setAssessDate(String assessDate) {
	this.assessDate = assessDate;
}

public String getClaimNo() {
	return claimNo;
}

public void setClaimNo(String claimNo) {
	this.claimNo = claimNo;
}

public String getOnsetAge() {
	return onsetAge;
}

public void setOnsetAge(String onsetAge) {
	this.onsetAge = onsetAge;
}

public String getInterventionAge() {
	return interventionAge;
}

public void setInterventionAge(String interventionAge) {
	this.interventionAge = interventionAge;
}

public String getConventionalAids() {
	return conventionalAids;
}

public void setConventionalAids(String conventionalAids) {
	this.conventionalAids = conventionalAids;
}

public String getBenifitFromConventional() {
	return benifitFromConventional;
}

public void setBenifitFromConventional(String benifitFromConventional) {
	this.benifitFromConventional = benifitFromConventional;
}

public String getAuditory() {
	return auditory;
}

public void setAuditory(String auditory) {
	this.auditory = auditory;
}

public String getOralaural() {
	return oralaural;
}

public void setOralaural(String oralaural) {
	this.oralaural = oralaural;
}

public String getSpeakRead() {
	return speakRead;
}

public void setSpeakRead(String speakRead) {
	this.speakRead = speakRead;
}

public String getMotherAwareness() {
	return motherAwareness;
}

public void setMotherAwareness(String motherAwareness) {
	this.motherAwareness = motherAwareness;
}

public String getAudioVerbal() {
	return audioVerbal;
}

public void setAudioVerbal(String audioVerbal) {
	this.audioVerbal = audioVerbal;
}

public String getMotivationSpeech() {
	return motivationSpeech;
}

public void setMotivationSpeech(String motivationSpeech) {
	this.motivationSpeech = motivationSpeech;
}

public String getMotivationAudio() {
	return motivationAudio;
}

public void setMotivationAudio(String motivationAudio) {
	this.motivationAudio = motivationAudio;
}

public String getRealisticExpect() {
	return realisticExpect;
}

public void setRealisticExpect(String realisticExpect) {
	this.realisticExpect = realisticExpect;
}

public String getMiddleEar() {
	return middleEar;
}

public void setMiddleEar(String middleEar) {
	this.middleEar = middleEar;
}

public String getCongenital() {
	return congenital;
}

public void setCongenital(String congenital) {
	this.congenital = congenital;
}

public String getBonyCapsule() {
	return bonyCapsule;
}

public void setBonyCapsule(String bonyCapsule) {
	this.bonyCapsule = bonyCapsule;
}

public String getMileStones() {
	return mileStones;
}

public void setMileStones(String mileStones) {
	this.mileStones = mileStones;
}

public String getSpeechMechanism() {
	return speechMechanism;
}

public void setSpeechMechanism(String speechMechanism) {
	this.speechMechanism = speechMechanism;
}

public String getADHD() {
	return ADHD;
}

public void setADHD(String aDHD) {
	ADHD = aDHD;
}

public String getStableQuiet() {
	return stableQuiet;
}

public void setStableQuiet(String stableQuiet) {
	this.stableQuiet = stableQuiet;
}

public String getAutistic() {
	return autistic;
}

public void setAutistic(String autistic) {
	this.autistic = autistic;
}

public String getStubborn() {
	return stubborn;
}

public void setStubborn(String stubborn) {
	this.stubborn = stubborn;
}

public String getImitative() {
	return imitative;
}

public void setImitative(String imitative) {
	this.imitative = imitative;
}

public String getFitUnfit() {
	return fitUnfit;
}

public void setFitUnfit(String fitUnfit) {
	this.fitUnfit = fitUnfit;
}

public String getOnsetAge_remarks() {
	return onsetAge_remarks;
}

public void setOnsetAge_remarks(String onsetAge_remarks) {
	this.onsetAge_remarks = onsetAge_remarks;
}

public String getInterventionAge_remarks() {
	return interventionAge_remarks;
}

public void setInterventionAge_remarks(String interventionAge_remarks) {
	this.interventionAge_remarks = interventionAge_remarks;
}

public String getConventionalAids_remarks() {
	return conventionalAids_remarks;
}

public void setConventionalAids_remarks(String conventionalAids_remarks) {
	this.conventionalAids_remarks = conventionalAids_remarks;
}

public String getBenifitFromConventional_remarks() {
	return benifitFromConventional_remarks;
}

public void setBenifitFromConventional_remarks(
		String benifitFromConventional_remarks) {
	this.benifitFromConventional_remarks = benifitFromConventional_remarks;
}

public String getAuditory_remarks() {
	return auditory_remarks;
}

public void setAuditory_remarks(String auditory_remarks) {
	this.auditory_remarks = auditory_remarks;
}

public String getOralaural_remarks() {
	return oralaural_remarks;
}

public void setOralaural_remarks(String oralaural_remarks) {
	this.oralaural_remarks = oralaural_remarks;
}

public String getSpeakRead_remarks() {
	return speakRead_remarks;
}

public void setSpeakRead_remarks(String speakRead_remarks) {
	this.speakRead_remarks = speakRead_remarks;
}

public String getMotherAwareness_remarks() {
	return motherAwareness_remarks;
}

public void setMotherAwareness_remarks(String motherAwareness_remarks) {
	this.motherAwareness_remarks = motherAwareness_remarks;
}

public String getMotivationSpeech_remarks() {
	return motivationSpeech_remarks;
}

public void setMotivationSpeech_remarks(String motivationSpeech_remarks) {
	this.motivationSpeech_remarks = motivationSpeech_remarks;
}

public String getMotivationAudio_remarks() {
	return motivationAudio_remarks;
}

public void setMotivationAudio_remarks(String motivationAudio_remarks) {
	this.motivationAudio_remarks = motivationAudio_remarks;
}

public String getRealisticExpect_remarks() {
	return realisticExpect_remarks;
}

public void setRealisticExpect_remarks(String realisticExpect_remarks) {
	this.realisticExpect_remarks = realisticExpect_remarks;
}

public String getMiddleEar_remarks() {
	return middleEar_remarks;
}

public void setMiddleEar_remarks(String middleEar_remarks) {
	this.middleEar_remarks = middleEar_remarks;
}

public String getCongenital_remarks() {
	return congenital_remarks;
}

public void setCongenital_remarks(String congenital_remarks) {
	this.congenital_remarks = congenital_remarks;
}

public String getBonyCapsule_remarks() {
	return bonyCapsule_remarks;
}

public void setBonyCapsule_remarks(String bonyCapsule_remarks) {
	this.bonyCapsule_remarks = bonyCapsule_remarks;
}

public String getMileStones_remarks() {
	return mileStones_remarks;
}

public void setMileStones_remarks(String mileStones_remarks) {
	this.mileStones_remarks = mileStones_remarks;
}

public String getSpeechMechanism_remarks() {
	return speechMechanism_remarks;
}

public void setSpeechMechanism_remarks(String speechMechanism_remarks) {
	this.speechMechanism_remarks = speechMechanism_remarks;
}

public String getADHD_remarks() {
	return ADHD_remarks;
}

public void setADHD_remarks(String aDHD_remarks) {
	ADHD_remarks = aDHD_remarks;
}

public String getStableQuiet_remarks() {
	return stableQuiet_remarks;
}

public void setStableQuiet_remarks(String stableQuiet_remarks) {
	this.stableQuiet_remarks = stableQuiet_remarks;
}

public String getAutistic_remarks() {
	return autistic_remarks;
}

public void setAutistic_remarks(String autistic_remarks) {
	this.autistic_remarks = autistic_remarks;
}

public String getStubborn_remarks() {
	return stubborn_remarks;
}

public void setStubborn_remarks(String stubborn_remarks) {
	this.stubborn_remarks = stubborn_remarks;
}

public String getImitative_remarks() {
	return imitative_remarks;
}

public void setImitative_remarks(String imitative_remarks) {
	this.imitative_remarks = imitative_remarks;
}

public String getFitUnfit_remarks() {
	return fitUnfit_remarks;
}

public void setFitUnfit_remarks(String fitUnfit_remarks) {
	this.fitUnfit_remarks = fitUnfit_remarks;
}

public String getAudioVerbal_remarks() {
	return audioVerbal_remarks;
}

public void setAudioVerbal_remarks(String audioVerbal_remarks) {
	this.audioVerbal_remarks = audioVerbal_remarks;
}

public String getCochlearQuestionnaire() {
	return cochlearQuestionnaire;
}

public void setCochlearQuestionnaire(String cochlearQuestionnaire) {
	this.cochlearQuestionnaire = cochlearQuestionnaire;
}

public String getDisDate() {
	return disDate;
}

public void setDisDate(String disDate) {
	this.disDate = disDate;
}

public String getDisRemarks() {
	return disRemarks;
}

public void setDisRemarks(String disRemarks) {
	this.disRemarks = disRemarks;
}

public String getPatientScheme() {
	return patientScheme;
}

public void setPatientScheme(String patientScheme) {
	this.patientScheme = patientScheme;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
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

public String getPin() {
	return pin;
}

public void setPin(String pin) {
	this.pin = pin;
}

public String getPhotoUrl() {
	return photoUrl;
}

public void setPhotoUrl(String photoUrl) {
	this.photoUrl = photoUrl;
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

public String getSlab() {
	return slab;
}

public void setSlab(String slab) {
	this.slab = slab;
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

public String getScheme() {
	return scheme;
}

public void setScheme(String scheme) {
	this.scheme = scheme;
}

public String getTelScheme() {
	return telScheme;
}

public void setTelScheme(String telScheme) {
	this.telScheme = telScheme;
}

public String getDtTime() {
	return dtTime;
}

public void setDtTime(String dtTime) {
	this.dtTime = dtTime;
}

public String getCaste() {
	return caste;
}

public void setCaste(String caste) {
	this.caste = caste;
}

public String getIpNo() {
	return ipNo;
}

public void setIpNo(String ipNo) {
	this.ipNo = ipNo;
}

public String getCeoRemark() {
	return ceoRemark;
}

public void setCeoRemark(String ceoRemark) {
	this.ceoRemark = ceoRemark;
}

public String getMedSurgEnhFlag() {
	return medSurgEnhFlag;
}

public void setMedSurgEnhFlag(String medSurgEnhFlag) {
	this.medSurgEnhFlag = medSurgEnhFlag;
}

public String getOrganTransYN() {
	return organTransYN;
}

public void setOrganTransYN(String organTransYN) {
	this.organTransYN = organTransYN;
}
public String getMedicalSpclty() {
	return medicalSpclty;
}
public void setMedicalSpclty(String medicalSpclty) {
	this.medicalSpclty = medicalSpclty;
}
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
public String getPatientInTime() {
	return patientInTime;
}
public void setPatientInTime(String patientInTime) {
	this.patientInTime = patientInTime;
}
public String getPatientOutTime() {
	return patientOutTime;
}
public void setPatientOutTime(String patientOutTime) {
	this.patientOutTime = patientOutTime;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public String getEnhAmounts() {
	return enhAmounts;
}
public void setEnhAmounts(String enhAmounts) {
	this.enhAmounts = enhAmounts;
}
public String getMedcoRmrks() {
	return medcoRmrks;
}
public void setMedcoRmrks(String medcoRmrks) {
	this.medcoRmrks = medcoRmrks;
}
public String getHoldRemarks() {
	return holdRemarks;
}
public void setHoldRemarks(String holdRemarks) {
	this.holdRemarks = holdRemarks;
}
public Long getCycles() {
	return cycles;
}
public void setCycles(Long cycles) {
	this.cycles = cycles;
}
public String getTeleRegDate() {
	return teleRegDate;
}
public void setTeleRegDate(String teleRegDate) {
	this.teleRegDate = teleRegDate;
}
public String getDrugAmount() {
	return drugAmount;
}
public void setDrugAmount(String drugAmount) {
	this.drugAmount = drugAmount;
}

}
