package com.ahct.preauth.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahct.common.util.TimeStamp;
import com.ahct.preauth.vo.DrugsVO;

public class PreauthClinicalNotesVO implements java.io.Serializable{
    public PreauthClinicalNotesVO() {
    }
   private Number COUNT=0;
   private String ID;
   private String VALUE;
   private String CLINICALID;
   private String TEMPERATURE;
    private String PULSE;
    private String HEART_RATE;
    private String LUNGS;
    private String RESPIRATORY;
    private String CASEID;
    private String BLOODPRESSURE;
    private String USERID;
    private String PREPOSTFLAG;
    private String INVESTIGATIONDATE;
    private String SELECTEDATE;
    private String SURGERYDATE;
    private String TELEPHINT_DT;
    private String ADMIN_DT;
    private String DISCHARGEDT;
    private String NEXTFOLLOWUPDT;
    private String BLOCKNAME;
    private String ROOMNO;
    private String FLOORNO;
    private String SAVED_FILE_NAME;
    private String FILE_NAME;
    private String ATTACH_TYPE;
    private Long AFSURG_PHT_CNT;
    private Long OP_NOTES_CNT;
    private Long SATISF_LETTER_CNT;
    private Long TRANSPRT_LETTER_CNT;
    private Long DIS_PHT_CNT;
    private Long DIS_SUMRY_DOC_CNT;
    private Long DEATH_CERTI_CNT;
    private Long DEATH_SUMMRY_CNT;
    private String CHEMO_YN="N";
    private String BLOOD_YN="N";
    private String MEDICINES;
    private String RADIATN_YN="N";
    private String CNDNT_STR_INF;
    private String INF_STR_DT;
    private String INF_END_DT;
    private String INF_STR_TIME;
    private String INF_END_TIME;
    private String CNDNT_END_INF;
    private String INFSTRDT;
    private String INFENDDT;
    private String CONDTN_CMPLCTN_DESC;
    private String THERAPY_DTLS;
    private String INSTRUCTIONS;
    private String SITEFIELDNAME;
    private String FIELD;
    private String FSTRTDT;
    private String FTIME;
    private String ADMINESTEDBY;
    private String TEST_CHECK_YN="N";
    private String PHYSICIST_CHECK_YN="N";
    private String MD_CHECK_YN="N";
    private String DAILY_DOSE;
    private String WOUND_STATUS;
    private String WOUND_DTLS;
    private String REMARKS;
    private String WARD_TYPE;
    private String SURGEON_NAME;
    private String SURGEON_REGNO;
    private String SURGEON_QUAL;
    private String SURGEON_CNTCTNO;
    private String ASST_SURG_NAME;
    private String ASST_SURG_REGNO;
    private String ASST_SURG_QUAL;
    private String ASST_SURG_CNTCTNO;
    private String PARAMEDIC_NAME;
    private String SFLOOR;
    private String NURSE_NAME;
    private String SROOM_NO;
    private String EXP_HOSP_STAY;
    private String TREATMNT_GVN;
    private String OPERATION_FNDNGS;
    private String POST_OPERATVE_PERIOD;
    private String POST_SPL_INVSTGNS;
    private String STATUS_DISCARGE;
    private String ADVICE;
    private String REVIEW;
    private String DIS_SAVE_SUBMIT;
    private Timestamp MYTIME;
    private TimeStamp MYTIME1;
    private String FLUIDINPT;
    private String FLUIDOUTPUT;
    private String SURGSTARTTIME;
    private String SURGENDTIME;
    private String ANESTNAME;
    private String ANESTREGNO;
    private String ANESTMOBILENO;
    private String lStrLangID;
    private String DISCODE;
    private String DEATHDT;
    private String WARDINTIME;
    private String WARDOUTTIME;
    private String MEDMGMT_YN;
    private String CAUSE_OF_DEATH;
    
// added for enabling treatment block
	
	private String treatSurgeonName;
	private String treatSurgeonRegNo;
	private String treatSurgeonQual;
	private String treatSurgeonCnctNo;
	private String treatSurgStartDt;
	
	private String treatAsstSurName;
	private String treatAsstSurRegNo;
	private String treatAsstSurQual;
	private String treatAsstSurContctNo;
	private String treatParadMedicName;
	private String treatNurseName;
	private String treatExpHospStay;
	private String treatDeathDate;
	private String medicalFlag;
	// end of treatment block
    
	private String caseUnits;
	private String dentCond;
	/**
	 * 
	 * clinical notes fields added
	 */
	private String plasmaBloodGlucose;
	private String insuliDosage;
	private String docName;
	private String progressOfpat;
	
	private String plasmaBloodGlucose1;
	private String insuliDosage1;
	private String docName1;
	private String progressOfpat1;
	private String role;
	
	
	
	private String plasmaBbf;
 	private String plasmaBl;
 	private String plasmaBd;
 	private String plasmaMn;
 	private String insulinBbf;
 	private String insulinSr;
 	private String insulinBd;
 	private String insulinMn;
 	private List<DrugsVO> drugs;
 	
 	private String anesthesiaType;
 	private String incisionType;
 	private String intraOpPotos;
 	private String videoRec;
 	private String swabCount;
 	private String sutureLigature;
 	private String specimenRem;
 	private String drainageCnt;
 	private String bloodLosss;
 	private String complications;
 	private String conditiOfPat;
 	private String postOperativeInst;
 	private String specimenName;
 	private String complicationsRemarks;
 	private String investGnDate;
 	private String categoryCode;
 	private String procCode;
 	private String SERVERDT;
 	private String toothedUnits;
 	
	public String getInvestGnDate() {
		return investGnDate;
	}
	public void setInvestGnDate(Date investGnDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(investGnDate != null)
		this.investGnDate= sdf.format(investGnDate);
	}
	public String getSpecimenName() {
		return specimenName;
	}
	public void setSpecimenName(String specimenName) {
		this.specimenName = specimenName;
	}
	public String getComplicationsRemarks() {
		return complicationsRemarks;
	}
	public void setComplicationsRemarks(String complicationsRemarks) {
		this.complicationsRemarks = complicationsRemarks;
	}
	public String getAnesthesiaType() {
		return anesthesiaType;
	}
	public void setAnesthesiaType(String anesthesiaType) {
		this.anesthesiaType = anesthesiaType;
	}
	public String getIncisionType() {
		return incisionType;
	}
	public void setIncisionType(String incisionType) {
		this.incisionType = incisionType;
	}
	public String getIntraOpPotos() {
		return intraOpPotos;
	}
	public void setIntraOpPotos(String intraOpPotos) {
		this.intraOpPotos = intraOpPotos;
	}
	public String getVideoRec() {
		return videoRec;
	}
	public void setVideoRec(String videoRec) {
		this.videoRec = videoRec;
	}
	public String getSwabCount() {
		return swabCount;
	}
	public void setSwabCount(String swabCount) {
		this.swabCount = swabCount;
	}
	public String getSutureLigature() {
		return sutureLigature;
	}
	public void setSutureLigature(String sutureLigature) {
		this.sutureLigature = sutureLigature;
	}
	public String getSpecimenRem() {
		return specimenRem;
	}
	public void setSpecimenRem(String specimenRem) {
		this.specimenRem = specimenRem;
	}
	public String getDrainageCnt() {
		return drainageCnt;
	}
	public void setDrainageCnt(String drainageCnt) {
		this.drainageCnt = drainageCnt;
	}
	public String getBloodLosss() {
		return bloodLosss;
	}
	public void setBloodLosss(String bloodLosss) {
		this.bloodLosss = bloodLosss;
	}
	public String getComplications() {
		return complications;
	}
	public void setComplications(String complications) {
		this.complications = complications;
	}
	public String getConditiOfPat() {
		return conditiOfPat;
	}
	public void setConditiOfPat(String conditiOfPat) {
		this.conditiOfPat = conditiOfPat;
	}
	public String getPostOperativeInst() {
		return postOperativeInst;
	}
	public void setPostOperativeInst(String postOperativeInst) {
		this.postOperativeInst = postOperativeInst;
	}
	public List<DrugsVO> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<DrugsVO> drugs) {
		this.drugs = drugs;
	}
	public String getPlasmaBbf() {
		return plasmaBbf;
	}
	public void setPlasmaBbf(String plasmaBbf) {
		this.plasmaBbf = plasmaBbf;
	}
	public String getPlasmaBl() {
		return plasmaBl;
	}
	public void setPlasmaBl(String plasmaBl) {
		this.plasmaBl = plasmaBl;
	}
	public String getPlasmaBd() {
		return plasmaBd;
	}
	public void setPlasmaBd(String plasmaBd) {
		this.plasmaBd = plasmaBd;
	}
	public String getPlasmaMn() {
		return plasmaMn;
	}
	public void setPlasmaMn(String plasmaMn) {
		this.plasmaMn = plasmaMn;
	}
	public String getInsulinBbf() {
		return insulinBbf;
	}
	public void setInsulinBbf(String insulinBbf) {
		this.insulinBbf = insulinBbf;
	}
	public String getInsulinSr() {
		return insulinSr;
	}
	public void setInsulinSr(String insulinSr) {
		this.insulinSr = insulinSr;
	}
	public String getInsulinBd() {
		return insulinBd;
	}
	public void setInsulinBd(String insulinBd) {
		this.insulinBd = insulinBd;
	}
	public String getInsulinMn() {
		return insulinMn;
	}
	public void setInsulinMn(String insulinMn) {
		this.insulinMn = insulinMn;
	}
	
	public String getPlasmaBloodGlucose() {
		return plasmaBloodGlucose;
	}
	public void setPlasmaBloodGlucose(String plasmaBloodGlucose) {
		this.plasmaBloodGlucose = plasmaBloodGlucose;
	}
	public String getInsuliDosage() {
		return insuliDosage;
	}
	public void setInsuliDosage(String insuliDosage) {
		this.insuliDosage = insuliDosage;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getProgressOfpat() {
		return progressOfpat;
	}
	public void setProgressOfpat(String progressOfpat) {
		this.progressOfpat = progressOfpat;
	}
	public String getPlasmaBloodGlucose1() {
		return plasmaBloodGlucose1;
	}
	public void setPlasmaBloodGlucose1(String plasmaBloodGlucose1) {
		this.plasmaBloodGlucose1 = plasmaBloodGlucose1;
	}
	public String getInsuliDosage1() {
		return insuliDosage1;
	}
	public void setInsuliDosage1(String insuliDosage1) {
		this.insuliDosage1 = insuliDosage1;
	}
	public String getDocName1() {
		return docName1;
	}
	public void setDocName1(String docName1) {
		this.docName1 = docName1;
	}
	public String getProgressOfpat1() {
		return progressOfpat1;
	}
	public void setProgressOfpat1(String progressOfpat1) {
		this.progressOfpat1 = progressOfpat1;
	}
	public Number getCOUNT() {
		return COUNT;
	}
	public String getMedicalFlag() {
		return medicalFlag;
	}
	public void setMedicalFlag(String medicalFlag) {
		this.medicalFlag = medicalFlag;
	}
	public String getTreatDeathDate() {
		return treatDeathDate;
	}
	public void setTreatDeathDate(String treatDeathDate) {
		this.treatDeathDate = treatDeathDate;
	}
	public String getTreatSurgeonName() {
		return treatSurgeonName;
	}
	public void setTreatSurgeonName(String treatSurgeonName) {
		this.treatSurgeonName = treatSurgeonName;
	}
	public String getTreatSurgeonRegNo() {
		return treatSurgeonRegNo;
	}
	public void setTreatSurgeonRegNo(String treatSurgeonRegNo) {
		this.treatSurgeonRegNo = treatSurgeonRegNo;
	}
	public String getTreatSurgeonQual() {
		return treatSurgeonQual;
	}
	public void setTreatSurgeonQual(String treatSurgeonQual) {
		this.treatSurgeonQual = treatSurgeonQual;
	}
	public String getTreatSurgeonCnctNo() {
		return treatSurgeonCnctNo;
	}
	public void setTreatSurgeonCnctNo(String treatSurgeonCnctNo) {
		this.treatSurgeonCnctNo = treatSurgeonCnctNo;
	}
	public String getTreatSurgStartDt() {
		return treatSurgStartDt;
	}
	public void setTreatSurgStartDt(String treatSurgStartDt) {
		this.treatSurgStartDt = treatSurgStartDt;
	}
	public String getTreatAsstSurName() {
		return treatAsstSurName;
	}
	public void setTreatAsstSurName(String treatAsstSurName) {
		this.treatAsstSurName = treatAsstSurName;
	}
	public String getTreatAsstSurRegNo() {
		return treatAsstSurRegNo;
	}
	public void setTreatAsstSurRegNo(String treatAsstSurRegNo) {
		this.treatAsstSurRegNo = treatAsstSurRegNo;
	}
	public String getTreatAsstSurQual() {
		return treatAsstSurQual;
	}
	public void setTreatAsstSurQual(String treatAsstSurQual) {
		this.treatAsstSurQual = treatAsstSurQual;
	}
	public String getTreatAsstSurContctNo() {
		return treatAsstSurContctNo;
	}
	public void setTreatAsstSurContctNo(String treatAsstSurContctNo) {
		this.treatAsstSurContctNo = treatAsstSurContctNo;
	}
	public String getTreatParadMedicName() {
		return treatParadMedicName;
	}
	public void setTreatParadMedicName(String treatParadMedicName) {
		this.treatParadMedicName = treatParadMedicName;
	}
	public String getTreatNurseName() {
		return treatNurseName;
	}
	public void setTreatNurseName(String treatNurseName) {
		this.treatNurseName = treatNurseName;
	}
	public String getTreatExpHospStay() {
		return treatExpHospStay;
	}
	public void setTreatExpHospStay(String treatExpHospStay) {
		this.treatExpHospStay = treatExpHospStay;
	}
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
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
	public String getCLINICALID() {
		return CLINICALID;
	}
	public void setCLINICALID(String cLINICALID) {
		CLINICALID = cLINICALID;
	}
	public String getTEMPERATURE() {
		return TEMPERATURE;
	}
	public void setTEMPERATURE(String tEMPERATURE) {
		TEMPERATURE = tEMPERATURE;
	}
	public String getPULSE() {
		return PULSE;
	}
	public void setPULSE(String pULSE) {
		PULSE = pULSE;
	}
	public String getHEART_RATE() {
		return HEART_RATE;
	}
	public void setHEART_RATE(String hEART_RATE) {
		HEART_RATE = hEART_RATE;
	}
	public String getLUNGS() {
		return LUNGS;
	}
	public void setLUNGS(String lUNGS) {
		LUNGS = lUNGS;
	}
	public String getRESPIRATORY() {
		return RESPIRATORY;
	}
	public void setRESPIRATORY(String rESPIRATORY) {
		RESPIRATORY = rESPIRATORY;
	}
	public String getCASEID() {
		return CASEID;
	}
	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}
	public String getBLOODPRESSURE() {
		return BLOODPRESSURE;
	}
	public void setBLOODPRESSURE(String bLOODPRESSURE) {
		BLOODPRESSURE = bLOODPRESSURE;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getPREPOSTFLAG() {
		return PREPOSTFLAG;
	}
	public void setPREPOSTFLAG(String pREPOSTFLAG) {
		PREPOSTFLAG = pREPOSTFLAG;
	}
	public String getINVESTIGATIONDATE() {
		return INVESTIGATIONDATE;
	}
	public void setINVESTIGATIONDATE(String iNVESTIGATIONDATE) {
		INVESTIGATIONDATE = iNVESTIGATIONDATE;
	}
	public String getSELECTEDATE() {
		return SELECTEDATE;
	}
	public void setSELECTEDATE(String sELECTEDATE) {
		SELECTEDATE = sELECTEDATE;
	}
	public String getTELEPHINT_DT() {
		return TELEPHINT_DT;
	}
	public void setTELEPHINT_DT(String tELEPHINT_DT) {
		TELEPHINT_DT = tELEPHINT_DT;
	}
	public String getADMIN_DT() {
		return ADMIN_DT;
	}
	public void setADMIN_DT(String aDMIN_DT) {
		ADMIN_DT = aDMIN_DT;
	}
	public String getDISCHARGEDT() {
		return DISCHARGEDT;
	}
	public void setDISCHARGEDT(String dISCHARGEDT) {
		DISCHARGEDT = dISCHARGEDT;
	}
	public String getNEXTFOLLOWUPDT() {
		return NEXTFOLLOWUPDT;
	}
	public void setNEXTFOLLOWUPDT(String nEXTFOLLOWUPDT) {
		NEXTFOLLOWUPDT = nEXTFOLLOWUPDT;
	}
	public String getBLOCKNAME() {
		return BLOCKNAME;
	}
	public void setBLOCKNAME(String bLOCKNAME) {
		BLOCKNAME = bLOCKNAME;
	}
	public String getROOMNO() {
		return ROOMNO;
	}
	public void setROOMNO(String rOOMNO) {
		ROOMNO = rOOMNO;
	}
	public String getFLOORNO() {
		return FLOORNO;
	}
	public void setFLOORNO(String fLOORNO) {
		FLOORNO = fLOORNO;
	}
	public String getSAVED_FILE_NAME() {
		return SAVED_FILE_NAME;
	}
	public void setSAVED_FILE_NAME(String sAVED_FILE_NAME) {
		SAVED_FILE_NAME = sAVED_FILE_NAME;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public String getATTACH_TYPE() {
		return ATTACH_TYPE;
	}
	public void setATTACH_TYPE(String aTTACH_TYPE) {
		ATTACH_TYPE = aTTACH_TYPE;
	}
	public Long getAFSURG_PHT_CNT() {
		return AFSURG_PHT_CNT;
	}
	public void setAFSURG_PHT_CNT(Long aFSURG_PHT_CNT) {
		AFSURG_PHT_CNT = aFSURG_PHT_CNT;
	}
	public Long getOP_NOTES_CNT() {
		return OP_NOTES_CNT;
	}
	public void setOP_NOTES_CNT(Long oP_NOTES_CNT) {
		OP_NOTES_CNT = oP_NOTES_CNT;
	}
	public Long getSATISF_LETTER_CNT() {
		return SATISF_LETTER_CNT;
	}
	public void setSATISF_LETTER_CNT(Long sATISF_LETTER_CNT) {
		SATISF_LETTER_CNT = sATISF_LETTER_CNT;
	}
	public Long getTRANSPRT_LETTER_CNT() {
		return TRANSPRT_LETTER_CNT;
	}
	public void setTRANSPRT_LETTER_CNT(Long tRANSPRT_LETTER_CNT) {
		TRANSPRT_LETTER_CNT = tRANSPRT_LETTER_CNT;
	}
	public Long getDIS_PHT_CNT() {
		return DIS_PHT_CNT;
	}
	public void setDIS_PHT_CNT(Long dIS_PHT_CNT) {
		DIS_PHT_CNT = dIS_PHT_CNT;
	}
	public Long getDIS_SUMRY_DOC_CNT() {
		return DIS_SUMRY_DOC_CNT;
	}
	public void setDIS_SUMRY_DOC_CNT(Long dIS_SUMRY_DOC_CNT) {
		DIS_SUMRY_DOC_CNT = dIS_SUMRY_DOC_CNT;
	}
	public Long getDEATH_CERTI_CNT() {
		return DEATH_CERTI_CNT;
	}
	public void setDEATH_CERTI_CNT(Long dEATH_CERTI_CNT) {
		DEATH_CERTI_CNT = dEATH_CERTI_CNT;
	}
	public Long getDEATH_SUMMRY_CNT() {
		return DEATH_SUMMRY_CNT;
	}
	public void setDEATH_SUMMRY_CNT(Long dEATH_SUMMRY_CNT) {
		DEATH_SUMMRY_CNT = dEATH_SUMMRY_CNT;
	}
	public String getCHEMO_YN() {
		return CHEMO_YN;
	}
	public void setCHEMO_YN(String cHEMO_YN) {
		CHEMO_YN = cHEMO_YN;
	}
	public String getBLOOD_YN() {
		return BLOOD_YN;
	}
	public void setBLOOD_YN(String bLOOD_YN) {
		BLOOD_YN = bLOOD_YN;
	}
	public String getMEDICINES() {
		return MEDICINES;
	}
	public void setMEDICINES(String mEDICINES) {
		MEDICINES = mEDICINES;
	}
	public String getRADIATN_YN() {
		return RADIATN_YN;
	}
	public void setRADIATN_YN(String rADIATN_YN) {
		RADIATN_YN = rADIATN_YN;
	}
	public String getCNDNT_STR_INF() {
		return CNDNT_STR_INF;
	}
	public void setCNDNT_STR_INF(String cNDNT_STR_INF) {
		CNDNT_STR_INF = cNDNT_STR_INF;
	}
	public String getINF_STR_DT() {
		return INF_STR_DT;
	}
	public void setINF_STR_DT(String iNF_STR_DT) {
		INF_STR_DT = iNF_STR_DT;
	}
	public String getINF_END_DT() {
		return INF_END_DT;
	}
	public void setINF_END_DT(String iNF_END_DT) {
		INF_END_DT = iNF_END_DT;
	}
	public String getINF_STR_TIME() {
		return INF_STR_TIME;
	}
	public void setINF_STR_TIME(String iNF_STR_TIME) {
		INF_STR_TIME = iNF_STR_TIME;
	}
	public String getINF_END_TIME() {
		return INF_END_TIME;
	}
	public void setINF_END_TIME(String iNF_END_TIME) {
		INF_END_TIME = iNF_END_TIME;
	}
	public String getCNDNT_END_INF() {
		return CNDNT_END_INF;
	}
	public void setCNDNT_END_INF(String cNDNT_END_INF) {
		CNDNT_END_INF = cNDNT_END_INF;
	}
	public String getINFSTRDT() {
		return INFSTRDT;
	}
	public void setINFSTRDT(String iNFSTRDT) {
		INFSTRDT = iNFSTRDT;
	}
	public String getINFENDDT() {
		return INFENDDT;
	}
	public void setINFENDDT(String iNFENDDT) {
		INFENDDT = iNFENDDT;
	}
	public String getCONDTN_CMPLCTN_DESC() {
		return CONDTN_CMPLCTN_DESC;
	}
	public void setCONDTN_CMPLCTN_DESC(String cONDTN_CMPLCTN_DESC) {
		CONDTN_CMPLCTN_DESC = cONDTN_CMPLCTN_DESC;
	}
	public String getTHERAPY_DTLS() {
		return THERAPY_DTLS;
	}
	public void setTHERAPY_DTLS(String tHERAPY_DTLS) {
		THERAPY_DTLS = tHERAPY_DTLS;
	}
	public String getINSTRUCTIONS() {
		return INSTRUCTIONS;
	}
	public void setINSTRUCTIONS(String iNSTRUCTIONS) {
		INSTRUCTIONS = iNSTRUCTIONS;
	}
	public String getSITEFIELDNAME() {
		return SITEFIELDNAME;
	}
	public void setSITEFIELDNAME(String sITEFIELDNAME) {
		SITEFIELDNAME = sITEFIELDNAME;
	}
	public String getFIELD() {
		return FIELD;
	}
	public void setFIELD(String fIELD) {
		FIELD = fIELD;
	}
	public String getFSTRTDT() {
		return FSTRTDT;
	}
	public void setFSTRTDT(String fSTRTDT) {
		FSTRTDT = fSTRTDT;
	}
	public String getFTIME() {
		return FTIME;
	}
	public void setFTIME(String fTIME) {
		FTIME = fTIME;
	}
	public String getADMINESTEDBY() {
		return ADMINESTEDBY;
	}
	public void setADMINESTEDBY(String aDMINESTEDBY) {
		ADMINESTEDBY = aDMINESTEDBY;
	}
	public String getTEST_CHECK_YN() {
		return TEST_CHECK_YN;
	}
	public void setTEST_CHECK_YN(String tEST_CHECK_YN) {
		TEST_CHECK_YN = tEST_CHECK_YN;
	}
	public String getPHYSICIST_CHECK_YN() {
		return PHYSICIST_CHECK_YN;
	}
	public void setPHYSICIST_CHECK_YN(String pHYSICIST_CHECK_YN) {
		PHYSICIST_CHECK_YN = pHYSICIST_CHECK_YN;
	}
	public String getMD_CHECK_YN() {
		return MD_CHECK_YN;
	}
	public void setMD_CHECK_YN(String mD_CHECK_YN) {
		MD_CHECK_YN = mD_CHECK_YN;
	}
	public String getDAILY_DOSE() {
		return DAILY_DOSE;
	}
	public void setDAILY_DOSE(String dAILY_DOSE) {
		DAILY_DOSE = dAILY_DOSE;
	}
	public String getWOUND_STATUS() {
		return WOUND_STATUS;
	}
	public void setWOUND_STATUS(String wOUND_STATUS) {
		WOUND_STATUS = wOUND_STATUS;
	}
	public String getWOUND_DTLS() {
		return WOUND_DTLS;
	}
	public void setWOUND_DTLS(String wOUND_DTLS) {
		WOUND_DTLS = wOUND_DTLS;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getWARD_TYPE() {
		return WARD_TYPE;
	}
	public void setWARD_TYPE(String wARD_TYPE) {
		WARD_TYPE = wARD_TYPE;
	}
	public String getSURGEON_NAME() {
		return SURGEON_NAME;
	}
	public void setSURGEON_NAME(String sURGEON_NAME) {
		SURGEON_NAME = sURGEON_NAME;
	}
	public String getSURGEON_REGNO() {
		return SURGEON_REGNO;
	}
	public void setSURGEON_REGNO(String sURGEON_REGNO) {
		SURGEON_REGNO = sURGEON_REGNO;
	}
	public String getSURGEON_QUAL() {
		return SURGEON_QUAL;
	}
	public void setSURGEON_QUAL(String sURGEON_QUAL) {
		SURGEON_QUAL = sURGEON_QUAL;
	}
	public String getSURGEON_CNTCTNO() {
		return SURGEON_CNTCTNO;
	}
	public void setSURGEON_CNTCTNO(String sURGEON_CNTCTNO) {
		SURGEON_CNTCTNO = sURGEON_CNTCTNO;
	}
	public String getASST_SURG_NAME() {
		return ASST_SURG_NAME;
	}
	public void setASST_SURG_NAME(String aSST_SURG_NAME) {
		ASST_SURG_NAME = aSST_SURG_NAME;
	}
	public String getASST_SURG_REGNO() {
		return ASST_SURG_REGNO;
	}
	public void setASST_SURG_REGNO(String aSST_SURG_REGNO) {
		ASST_SURG_REGNO = aSST_SURG_REGNO;
	}
	public String getASST_SURG_QUAL() {
		return ASST_SURG_QUAL;
	}
	public void setASST_SURG_QUAL(String aSST_SURG_QUAL) {
		ASST_SURG_QUAL = aSST_SURG_QUAL;
	}
	public String getASST_SURG_CNTCTNO() {
		return ASST_SURG_CNTCTNO;
	}
	public void setASST_SURG_CNTCTNO(String aSST_SURG_CNTCTNO) {
		ASST_SURG_CNTCTNO = aSST_SURG_CNTCTNO;
	}
	public String getPARAMEDIC_NAME() {
		return PARAMEDIC_NAME;
	}
	public void setPARAMEDIC_NAME(String pARAMEDIC_NAME) {
		PARAMEDIC_NAME = pARAMEDIC_NAME;
	}
	public String getSFLOOR() {
		return SFLOOR;
	}
	public void setSFLOOR(String sFLOOR) {
		SFLOOR = sFLOOR;
	}
	public String getNURSE_NAME() {
		return NURSE_NAME;
	}
	public void setNURSE_NAME(String nURSE_NAME) {
		NURSE_NAME = nURSE_NAME;
	}
	public String getSROOM_NO() {
		return SROOM_NO;
	}
	public void setSROOM_NO(String sROOM_NO) {
		SROOM_NO = sROOM_NO;
	}
	public String getEXP_HOSP_STAY() {
		return EXP_HOSP_STAY;
	}
	public void setEXP_HOSP_STAY(String eXP_HOSP_STAY) {
		EXP_HOSP_STAY = eXP_HOSP_STAY;
	}
	public String getTREATMNT_GVN() {
		return TREATMNT_GVN;
	}
	public void setTREATMNT_GVN(String tREATMNT_GVN) {
		TREATMNT_GVN = tREATMNT_GVN;
	}
	public String getOPERATION_FNDNGS() {
		return OPERATION_FNDNGS;
	}
	public void setOPERATION_FNDNGS(String oPERATION_FNDNGS) {
		OPERATION_FNDNGS = oPERATION_FNDNGS;
	}
	public String getPOST_OPERATVE_PERIOD() {
		return POST_OPERATVE_PERIOD;
	}
	public void setPOST_OPERATVE_PERIOD(String pOST_OPERATVE_PERIOD) {
		POST_OPERATVE_PERIOD = pOST_OPERATVE_PERIOD;
	}
	public String getPOST_SPL_INVSTGNS() {
		return POST_SPL_INVSTGNS;
	}
	public void setPOST_SPL_INVSTGNS(String pOST_SPL_INVSTGNS) {
		POST_SPL_INVSTGNS = pOST_SPL_INVSTGNS;
	}
	public String getSTATUS_DISCARGE() {
		return STATUS_DISCARGE;
	}
	public void setSTATUS_DISCARGE(String sTATUS_DISCARGE) {
		STATUS_DISCARGE = sTATUS_DISCARGE;
	}
	public String getADVICE() {
		return ADVICE;
	}
	public void setADVICE(String aDVICE) {
		ADVICE = aDVICE;
	}
	public String getREVIEW() {
		return REVIEW;
	}
	public void setREVIEW(String rEVIEW) {
		REVIEW = rEVIEW;
	}
	public String getDIS_SAVE_SUBMIT() {
		return DIS_SAVE_SUBMIT;
	}
	public void setDIS_SAVE_SUBMIT(String dIS_SAVE_SUBMIT) {
		DIS_SAVE_SUBMIT = dIS_SAVE_SUBMIT;
	}
	public Timestamp getMYTIME() {
		return MYTIME;
	}
	public void setMYTIME(Timestamp mYTIME) {
		MYTIME = mYTIME;
	}
	public TimeStamp getMYTIME1() {
		return MYTIME1;
	}
	public void setMYTIME1(TimeStamp mYTIME1) {
		MYTIME1 = mYTIME1;
	}
	public String getFLUIDINPT() {
		return FLUIDINPT;
	}
	public void setFLUIDINPT(String fLUIDINPT) {
		FLUIDINPT = fLUIDINPT;
	}
	public String getFLUIDOUTPUT() {
		return FLUIDOUTPUT;
	}
	public void setFLUIDOUTPUT(String fLUIDOUTPUT) {
		FLUIDOUTPUT = fLUIDOUTPUT;
	}
	public String getSURGSTARTTIME() {
		return SURGSTARTTIME;
	}
	public void setSURGSTARTTIME(String sURGSTARTTIME) {
		SURGSTARTTIME = sURGSTARTTIME;
	}
	public String getSURGENDTIME() {
		return SURGENDTIME;
	}
	public void setSURGENDTIME(String sURGENDTIME) {
		SURGENDTIME = sURGENDTIME;
	}
	public String getANESTNAME() {
		return ANESTNAME;
	}
	public void setANESTNAME(String aNESTNAME) {
		ANESTNAME = aNESTNAME;
	}
	public String getANESTREGNO() {
		return ANESTREGNO;
	}
	public void setANESTREGNO(String aNESTREGNO) {
		ANESTREGNO = aNESTREGNO;
	}
	public String getANESTMOBILENO() {
		return ANESTMOBILENO;
	}
	public void setANESTMOBILENO(String aNESTMOBILENO) {
		ANESTMOBILENO = aNESTMOBILENO;
	}
	public String getlStrLangID() {
		return lStrLangID;
	}
	public void setlStrLangID(String lStrLangID) {
		this.lStrLangID = lStrLangID;
	}
	public String getDISCODE() {
		return DISCODE;
	}
	public void setDISCODE(String dISCODE) {
		DISCODE = dISCODE;
	}	
	
	public String getDEATHDT() {
		return DEATHDT;
	}
	public void setDEATHDT(String dEATHDT) {
		DEATHDT = dEATHDT;
	}
	
	public String getSURGERYDATE() {
		return SURGERYDATE;
	}
	public void setSURGERYDATE(String sURGERYDATE) {
		SURGERYDATE = sURGERYDATE;
	}
	public String getWARDINTIME() {
		return WARDINTIME;
	}
	public void setWARDINTIME(String wARDINTIME) {
		WARDINTIME = wARDINTIME;
	}
	public String getWARDOUTTIME() {
		return WARDOUTTIME;
	}
	public void setWARDOUTTIME(String wARDOUTTIME) {
		WARDOUTTIME = wARDOUTTIME;
	}
	public String getMEDMGMT_YN() {
		return MEDMGMT_YN;
	}
	public void setMEDMGMT_YN(String mEDMGMT_YN) {
		MEDMGMT_YN = mEDMGMT_YN;
	}
	public String getCAUSE_OF_DEATH() {
		return CAUSE_OF_DEATH;
	}
	public void setCAUSE_OF_DEATH(String cAUSE_OF_DEATH) {
		CAUSE_OF_DEATH = cAUSE_OF_DEATH;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getProcCode() {
		return procCode;
	}
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}
	public String getSERVERDT() {
		return SERVERDT;
	}
	public void setSERVERDT(String sERVERDT) {
		SERVERDT = sERVERDT;
	}
	public String getCaseUnits() {
		return caseUnits;
	}
	public void setCaseUnits(String caseUnits) {
		this.caseUnits = caseUnits;
	}
	public String getDentCond() {
		return dentCond;
	}
	public void setDentCond(String dentCond) {
		this.dentCond = dentCond;
	}
	public String getToothedUnits() {
		return toothedUnits;
	}
	public void setToothedUnits(String toothedUnits) {
		this.toothedUnits = toothedUnits;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
