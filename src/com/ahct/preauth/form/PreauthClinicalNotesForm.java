package com.ahct.preauth.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
import com.ahct.preauth.vo.PreauthVO;


public class PreauthClinicalNotesForm extends ActionForm {
    
    
    private String fluidInput;
	private String fluidOutput;
	private String lungs;
	private String invesgtnDate;
	private String invesgtnDate1;
	private String remarks;
	private String lungs1;
	private String fluidInput1;
	private String fluidOutput1;
	private String remarks1;
	
	private String cndtnStrInf;
	private String medicines;
	private String dosages;	
	private String infsnStrtDtC;
	private String infsnStrtDtB;
	private String infsnStrtDtR;
	private String infsnEndDtC;
	private String infsnEndDtB;
	private String infsnEndDtR;
	private String field;
	private String siteFieldSize;
	private String fieldDt;
	private String dailyDose;
	private String instructions;
	private String admisteredBy;
	private String comments;
	private String resultMsg;

	private String surgeonName;
	private String surgeonRegNo;
	private String surgeonQual;
	private String surgeonCnctNo;
	
	private String surgStartDt;
	private String deathDate;
	
	// added for enabling treatment block
	
	private String treatSurgeonName;
	private String treatSurgeonRegNo;
	private String treatSurgeonQual;
	private String treatSurgeonCnctNo;
	private String treatSurgStartDt;
	private String treatDeathDate;
	private String treatAsstSurName;
	private String treatAsstSurRegNo;
	private String treatAsstSurQual;
	private String treatAsstSurContctNo;
	private String treatParadMedicName;
	private String treatNurseName;
	private String treatExpHospStay;
	
	// end of treatment block
	
	private String asstSurName;
	private String asstSurRegNo;
	private String asstSurQual;
	private String asstSurContctNo;
	private String paradMedicName;
	private String nurseName;
	private String expHospStay;
	
	private String anesthetistName;
	private String anesthetistRegNo;
	private String anesthetistMbNo;
	
	private List<PreauthClinicalNotesVO> clinicalNotesList;
	private List<PreauthClinicalNotesVO> postClinicalNotesList;

	private String treatmentGvn;
	private String operatveFindgs;
	private String postOperatvePerd;
	private String postSplInvstgtns;
	private String statusAtDischrg;
	private String review;
	private String advice;
	
	private String disDate;
	private String nxtFollUpDt;
	private String blockName;
	private String disfloor;
	private String disroomNo;
	private String disDeathDate;
	private String causeOfDeath;
	private String ipRegDate;
	private String teleRegDate;
	private String surgSaveDate;
	private String woundDtls;
	
	private String caseUnits;
	private String dentalSurg;
	
	/**
	 * 
	 * clinical notes fields added
	 */
	private String plasmaBloodGlucose;
	private String plasmaBloodGlucoseVal;
	private String insuliDosage;
	private String insuliDosageVal;
	private String docName;
	private String progressOfpat;
	
	private String docName1;
	
	
	
	private String plasmaBbf;
 	private String plasmaBl;
 	private String plasmaBd;
 	private String plasmaMn;
 	private String insulinBbf;
 	private String insulinSr;
 	private String insulinBd;
 	private String insulinMn;
 	
 	private String plasmaBbf1;
 	private String plasmaBl1;
 	private String plasmaBd1;
 	private String plasmaMn1;
 	private String insulinBbf1;
 	private String insulinSr1;
 	private String insulinBd1;
 	private String insulinMn1;
 	
 	private String drugTypeCode;
 	private String drugSubTypeName;
 	private String drugCode;
 	private String drugSubTypeCode;
 	private String pSubGrpName;
 	private String pSubGrpCode;
 	private String cSubGrpName;
 	private String cSubGrpCode;
 	private String drugName;
 	private String asriDrugCode;
 	private String routeType;
 	private String route;
 	private String strength;
 	private String dosage;
 	private String medicatPeriod;
 	private String strengthType;
 	private String drugs;
 	private List<DrugsVO> drugsLst;
 	private List<DrugsVO> drugsLstPre;
 	
 	/**
 	 * surgery details
 	 * @return
 	 */
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
 	private String enableAddClinicalNotes;
 	
 	private String teleFlag;
 	private List<PreauthVO> lstPreauthVO;
 	private String toothedUnits;
	
 	
 	
	public List<DrugsVO> getDrugsLstPre() {
		return drugsLstPre;
	}
	public void setDrugsLstPre(List<DrugsVO> drugsLstPre) {
		this.drugsLstPre = drugsLstPre;
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
	public String getPostOperativeInst() {
		return postOperativeInst;
	}
	public void setPostOperativeInst(String postOperativeInst) {
		this.postOperativeInst = postOperativeInst;
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
	public List<DrugsVO> getDrugsLst() {
		return drugsLst;
	}
	public void setDrugsLst(List<DrugsVO> drugsLst) {
		this.drugsLst = drugsLst;
	}
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	public String getStrengthType() {
		return strengthType;
	}
	public void setStrengthType(String strengthType) {
		this.strengthType = strengthType;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}
	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}
	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
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
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getAsriDrugCode() {
		return asriDrugCode;
	}
	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
	}
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
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
	public String getPlasmaBbf1() {
		return plasmaBbf1;
	}
	public void setPlasmaBbf1(String plasmaBbf1) {
		this.plasmaBbf1 = plasmaBbf1;
	}
	public String getPlasmaBl1() {
		return plasmaBl1;
	}
	public void setPlasmaBl1(String plasmaBl1) {
		this.plasmaBl1 = plasmaBl1;
	}
	public String getPlasmaBd1() {
		return plasmaBd1;
	}
	public void setPlasmaBd1(String plasmaBd1) {
		this.plasmaBd1 = plasmaBd1;
	}
	public String getPlasmaMn1() {
		return plasmaMn1;
	}
	public void setPlasmaMn1(String plasmaMn1) {
		this.plasmaMn1 = plasmaMn1;
	}
	public String getInsulinBbf1() {
		return insulinBbf1;
	}
	public void setInsulinBbf1(String insulinBbf1) {
		this.insulinBbf1 = insulinBbf1;
	}
	public String getInsulinSr1() {
		return insulinSr1;
	}
	public void setInsulinSr1(String insulinSr1) {
		this.insulinSr1 = insulinSr1;
	}
	public String getInsulinBd1() {
		return insulinBd1;
	}
	public void setInsulinBd1(String insulinBd1) {
		this.insulinBd1 = insulinBd1;
	}
	public String getInsulinMn1() {
		return insulinMn1;
	}
	public void setInsulinMn1(String insulinMn1) {
		this.insulinMn1 = insulinMn1;
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
	public String getPlasmaBloodGlucoseVal() {
		return plasmaBloodGlucoseVal;
	}
	public void setPlasmaBloodGlucoseVal(String plasmaBloodGlucoseVal) {
		this.plasmaBloodGlucoseVal = plasmaBloodGlucoseVal;
	}
	public String getInsuliDosageVal() {
		return insuliDosageVal;
	}
	public void setInsuliDosageVal(String insuliDosageVal) {
		this.insuliDosageVal = insuliDosageVal;
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

	public String getDocName1() {
		return docName1;
	}
	public void setDocName1(String docName1) {
		this.docName1 = docName1;
	}
	
	public String getWoundDtls() {
		return woundDtls;
	}
	public void setWoundDtls(String woundDtls) {
		this.woundDtls = woundDtls;
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
	public String getSurgSaveDate() {
		return surgSaveDate;
	}
	public void setSurgSaveDate(String surgSaveDate) {
		this.surgSaveDate = surgSaveDate;
	}
	public String getIpRegDate() {
		return ipRegDate;
	}
	public void setIpRegDate(String ipRegDate) {
		this.ipRegDate = ipRegDate;
	}
	public List<PreauthClinicalNotesVO> getPostClinicalNotesList() {
		return postClinicalNotesList;
	}
	public void setPostClinicalNotesList(
			List<PreauthClinicalNotesVO> postClinicalNotesList) {
		this.postClinicalNotesList = postClinicalNotesList;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCndtnStrInf() {
		return cndtnStrInf;
	}

	public void setCndtnStrInf(String cndtnStrInf) {
		this.cndtnStrInf = cndtnStrInf;
	}

	public String getInfsnStrtDtC() {
		return infsnStrtDtC;
	}

	public void setInfsnStrtDtC(String infsnStrtDtC) {
		this.infsnStrtDtC = infsnStrtDtC;
	}

	public String getInfsnEndDtC() {
		return infsnEndDtC;
	}

	public void setInfsnEndDtC(String infsnEndDtC) {
		this.infsnEndDtC = infsnEndDtC;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSiteFieldSize() {
		return siteFieldSize;
	}

	public void setSiteFieldSize(String siteFieldSize) {
		this.siteFieldSize = siteFieldSize;
	}

	public String getFieldDt() {
		return fieldDt;
	}

	public void setFieldDt(String fieldDt) {
		this.fieldDt = fieldDt;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getAdmisteredBy() {
		return admisteredBy;
	}

	public void setAdmisteredBy(String admisteredBy) {
		this.admisteredBy = admisteredBy;
	}

	public String getMedicines() {
		return medicines;
	}

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}

	public String getDosages() {
		return dosages;
	}

	public void setDosages(String dosages) {
		this.dosages = dosages;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<PreauthClinicalNotesVO> getClinicalNotesList() {
		return clinicalNotesList;
	}

	public void setClinicalNotesList(List<PreauthClinicalNotesVO> clinicalNotesList) {
		this.clinicalNotesList = clinicalNotesList;
	}

	public String getDailyDose() {
		return dailyDose;
	}

	public void setDailyDose(String dailyDose) {
		this.dailyDose = dailyDose;
	}

	public String getFluidInput() {
		return fluidInput;
	}

	public void setFluidInput(String fluidInput) {
		this.fluidInput = fluidInput;
	}

	public String getFluidOutput() {
		return fluidOutput;
	}

	public void setFluidOutput(String fluidOutput) {
		this.fluidOutput = fluidOutput;
	}

	public String getLungs() {
		return lungs;
	}

	public void setLungs(String lungs) {
		this.lungs = lungs;
	}

	public String getInvesgtnDate() {
		return invesgtnDate;
	}

	public void setInvesgtnDate(String invesgtnDate) {
		this.invesgtnDate = invesgtnDate;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getInfsnStrtDtB() {
		return infsnStrtDtB;
	}

	public void setInfsnStrtDtB(String infsnStrtDtB) {
		this.infsnStrtDtB = infsnStrtDtB;
	}

	public String getInfsnStrtDtR() {
		return infsnStrtDtR;
	}

	public void setInfsnStrtDtR(String infsnStrtDtR) {
		this.infsnStrtDtR = infsnStrtDtR;
	}

	public String getInfsnEndDtB() {
		return infsnEndDtB;
	}

	public void setInfsnEndDtB(String infsnEndDtB) {
		this.infsnEndDtB = infsnEndDtB;
	}

	public String getInfsnEndDtR() {
		return infsnEndDtR;
	}

	public void setInfsnEndDtR(String infsnEndDtR) {
		this.infsnEndDtR = infsnEndDtR;
	}

	public String getSurgeonName() {
		return surgeonName;
	}

	public void setSurgeonName(String surgeonName) {
		this.surgeonName = surgeonName;
	}

	public String getSurgeonRegNo() {
		return surgeonRegNo;
	}

	public void setSurgeonRegNo(String surgeonRegNo) {
		this.surgeonRegNo = surgeonRegNo;
	}

	public String getSurgeonQual() {
		return surgeonQual;
	}

	public void setSurgeonQual(String surgeonQual) {
		this.surgeonQual = surgeonQual;
	}

	public String getSurgeonCnctNo() {
		return surgeonCnctNo;
	}

	public void setSurgeonCnctNo(String surgeonCnctNo) {
		this.surgeonCnctNo = surgeonCnctNo;
	}

	public String getSurgStartDt() {
		return surgStartDt;
	}

	public void setSurgStartDt(String surgStartDt) {
		this.surgStartDt = surgStartDt;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public String getAsstSurName() {
		return asstSurName;
	}

	public void setAsstSurName(String asstSurName) {
		this.asstSurName = asstSurName;
	}

	public String getAsstSurRegNo() {
		return asstSurRegNo;
	}

	public void setAsstSurRegNo(String asstSurRegNo) {
		this.asstSurRegNo = asstSurRegNo;
	}

	public String getAsstSurQual() {
		return asstSurQual;
	}

	public void setAsstSurQual(String asstSurQual) {
		this.asstSurQual = asstSurQual;
	}

	public String getAsstSurContctNo() {
		return asstSurContctNo;
	}

	public void setAsstSurContctNo(String asstSurContctNo) {
		this.asstSurContctNo = asstSurContctNo;
	}

	public String getParadMedicName() {
		return paradMedicName;
	}

	public void setParadMedicName(String paradMedicName) {
		this.paradMedicName = paradMedicName;
	}

	public String getNurseName() {
		return nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	public String getExpHospStay() {
		return expHospStay;
	}

	public void setExpHospStay(String expHospStay) {
		this.expHospStay = expHospStay;
	}

	public String getAnesthetistName() {
		return anesthetistName;
	}

	public void setAnesthetistName(String anesthetistName) {
		this.anesthetistName = anesthetistName;
	}

	public String getAnesthetistRegNo() {
		return anesthetistRegNo;
	}

	public void setAnesthetistRegNo(String anesthetistRegNo) {
		this.anesthetistRegNo = anesthetistRegNo;
	}

	public String getAnesthetistMbNo() {
		return anesthetistMbNo;
	}

	public void setAnesthetistMbNo(String anesthetistMbNo) {
		this.anesthetistMbNo = anesthetistMbNo;
	}
	
	public String getRemarks1() {
		return remarks1;
	}
	
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	/**
	 * @return the treatmentGvn
	 */
	public String getTreatmentGvn() {
		return treatmentGvn;
	}
	/**
	 * @param treatmentGvn the treatmentGvn to set
	 */
	public void setTreatmentGvn(String treatmentGvn) {
		this.treatmentGvn = treatmentGvn;
	}
	/**
	 * @return the operatveFindgs
	 */
	public String getOperatveFindgs() {
		return operatveFindgs;
	}
	/**
	 * @param operatveFindgs the operatveFindgs to set
	 */
	public void setOperatveFindgs(String operatveFindgs) {
		this.operatveFindgs = operatveFindgs;
	}
	/**
	 * @return the postOperatvePerd
	 */
	public String getPostOperatvePerd() {
		return postOperatvePerd;
	}
	/**
	 * @param postOperatvePerd the postOperatvePerd to set
	 */
	public void setPostOperatvePerd(String postOperatvePerd) {
		this.postOperatvePerd = postOperatvePerd;
	}
	/**
	 * @return the postSplInvstgtns
	 */
	public String getPostSplInvstgtns() {
		return postSplInvstgtns;
	}
	/**
	 * @param postSplInvstgtns the postSplInvstgtns to set
	 */
	public void setPostSplInvstgtns(String postSplInvstgtns) {
		this.postSplInvstgtns = postSplInvstgtns;
	}
	/**
	 * @return the statusAtDischrg
	 */
	public String getStatusAtDischrg() {
		return statusAtDischrg;
	}
	/**
	 * @param statusAtDischrg the statusAtDischrg to set
	 */
	public void setStatusAtDischrg(String statusAtDischrg) {
		this.statusAtDischrg = statusAtDischrg;
	}
	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}
	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review = review;
	}
	/**
	 * @return the advice
	 */
	public String getAdvice() {
		return advice;
	}
	/**
	 * @param advice the advice to set
	 */
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	/**
	 * @return the disDate
	 */
	public String getDisDate() {
		return disDate;
	}
	/**
	 * @param disDate the disDate to set
	 */
	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}
	/**
	 * @return the nxtFollUpDt
	 */
	public String getNxtFollUpDt() {
		return nxtFollUpDt;
	}
	/**
	 * @param nxtFollUpDt the nxtFollUpDt to set
	 */
	public void setNxtFollUpDt(String nxtFollUpDt) {
		this.nxtFollUpDt = nxtFollUpDt;
	}
	/**
	 * @return the blockName
	 */
	public String getBlockName() {
		return blockName;
	}
	/**
	 * @param blockName the blockName to set
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	/**
	 * @return the disfloor
	 */
	public String getDisfloor() {
		return disfloor;
	}
	/**
	 * @param disfloor the disfloor to set
	 */
	public void setDisfloor(String disfloor) {
		this.disfloor = disfloor;
	}
	/**
	 * @return the disroomNo
	 */
	public String getDisroomNo() {
		return disroomNo;
	}
	/**
	 * @param disroomNo the disroomNo to set
	 */
	public void setDisroomNo(String disroomNo) {
		this.disroomNo = disroomNo;
	}
	/**
	 * @return the causeOfDeath
	 */
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	/**
	 * @param causeOfDeath the causeOfDeath to set
	 */
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	/**
	 * @return the disDeathDate
	 */
	public String getDisDeathDate() {
		return disDeathDate;
	}
	/**
	 * @param disDeathDate the disDeathDate to set
	 */
	public void setDisDeathDate(String disDeathDate) {
		this.disDeathDate = disDeathDate;
	}
	/**
	 * @return the lungs1
	 */
	public String getLungs1() {
		return lungs1;
	}
	/**
	 * @param lungs1 the lungs1 to set
	 */
	public void setLungs1(String lungs1) {
		this.lungs1 = lungs1;
	}
	/**
	 * @return the fluidInput1
	 */
	public String getFluidInput1() {
		return fluidInput1;
	}
	/**
	 * @param fluidInput1 the fluidInput1 to set
	 */
	public void setFluidInput1(String fluidInput1) {
		this.fluidInput1 = fluidInput1;
	}
	/**
	 * @return the fluidOutput1
	 */
	public String getFluidOutput1() {
		return fluidOutput1;
	}
	/**
	 * @param fluidOutput1 the fluidOutput1 to set
	 */
	public void setFluidOutput1(String fluidOutput1) {
		this.fluidOutput1 = fluidOutput1;
	}
	public String getInvesgtnDate1() {
		return invesgtnDate1;
	}
	public void setInvesgtnDate1(String invesgtnDate1) {
		this.invesgtnDate1 = invesgtnDate1;
	}
	public String getEnableAddClinicalNotes() {
		return enableAddClinicalNotes;
	}
	public void setEnableAddClinicalNotes(String enableAddClinicalNotes) {
		this.enableAddClinicalNotes = enableAddClinicalNotes;
	}
	public String getTeleRegDate() {
		return teleRegDate;
	}
	public void setTeleRegDate(String teleRegDate) {
		this.teleRegDate = teleRegDate;
	}
	public String getTeleFlag() {
		return teleFlag;
	}
	public void setTeleFlag(String teleFlag) {
		this.teleFlag = teleFlag;
	}
	public List<PreauthVO> getLstPreauthVO() {
		return lstPreauthVO;
	}
	public void setLstPreauthVO(List<PreauthVO> lstPreauthVO) {
		this.lstPreauthVO = lstPreauthVO;
	}
	public String getCaseUnits() {
		return caseUnits;
	}
	public void setCaseUnits(String caseUnits) {
		this.caseUnits = caseUnits;
	}
	public String getDentalSurg() {
		return dentalSurg;
	}
	public void setDentalSurg(String dentalSurg) {
		this.dentalSurg = dentalSurg;
	}
	public String getToothedUnits() {
		return toothedUnits;
	}
	public void setToothedUnits(String toothedUnits) {
		this.toothedUnits = toothedUnits;
	}	

}


