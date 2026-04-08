package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_PATIENT_HOS_DGNSIS database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_PATIENT_HOS_DGNSIS")
public class EhfChronicPatientHosDgnsi implements Serializable {
	private static final long serialVersionUID = 1L;
	private String chronicId;
	private String asriDrugCode;
	private String caseAdmType;
	private String categoryCode;
	private String complaintType;
	private Date crtDt;
	private String crtUsr;
	private String diagnosisType;
	private String diseaseAnatCode;
	private String diseaseCode;
	private String doctId;
	private String doctMobNo;
	private String doctName;
	private String doctQuali;
	private String doctRegNo;
	private String doctType;
	private String dosage;
	private String drugSubTypeCode;
	private String drugTypeCode;
	private String examntnFindings;
	private String familyHistory;
	private String familyHistoryOthr;
	private String historyIllness;
	private String hospDiagnosis;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String mainCategoryCode;
	private String mainSymptomCode;
	private String medicationPeriod;
	private String opCategoryCode;
	private String opIcdCode;
	private String opPackageCode;
	private String opRemarks;
	private String pastHistory;
	private String pastHistoryCancers;
	private String pastHistoryEnddis;
	private String pastHistoryOthr;
	private String pastHistorySurg;
	private String patientComplaint;
	private String personalHistory;
	private String policeStatName;
	private String route;
	private String strength;
	private String subCategoryCode;
	private String symptomCode;
	private String treatingDocRmks;

    public EhfChronicPatientHosDgnsi() {
    }


	@Id
	@Column(name="CHRONIC_ID")
	public String getChronicId() {
		return this.chronicId;
	}

	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}


	@Column(name="ASRI_DRUG_CODE")
	public String getAsriDrugCode() {
		return this.asriDrugCode;
	}

	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
	}


	@Column(name="CASE_ADM_TYPE")
	public String getCaseAdmType() {
		return this.caseAdmType;
	}

	public void setCaseAdmType(String caseAdmType) {
		this.caseAdmType = caseAdmType;
	}


	@Column(name="CATEGORY_CODE")
	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	@Column(name="COMPLAINT_TYPE")
	public String getComplaintType() {
		return this.complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	@Column(name="DIAGNOSIS_TYPE")
	public String getDiagnosisType() {
		return this.diagnosisType;
	}

	public void setDiagnosisType(String diagnosisType) {
		this.diagnosisType = diagnosisType;
	}


	@Column(name="DISEASE_ANAT_CODE")
	public String getDiseaseAnatCode() {
		return this.diseaseAnatCode;
	}

	public void setDiseaseAnatCode(String diseaseAnatCode) {
		this.diseaseAnatCode = diseaseAnatCode;
	}


	@Column(name="DISEASE_CODE")
	public String getDiseaseCode() {
		return this.diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}


	@Column(name="DOCT_ID")
	public String getDoctId() {
		return this.doctId;
	}

	public void setDoctId(String doctId) {
		this.doctId = doctId;
	}


	@Column(name="DOCT_MOB_NO")
	public String getDoctMobNo() {
		return this.doctMobNo;
	}

	public void setDoctMobNo(String doctMobNo) {
		this.doctMobNo = doctMobNo;
	}


	@Column(name="DOCT_NAME")
	public String getDoctName() {
		return this.doctName;
	}

	public void setDoctName(String doctName) {
		this.doctName = doctName;
	}


	@Column(name="DOCT_QUALI")
	public String getDoctQuali() {
		return this.doctQuali;
	}

	public void setDoctQuali(String doctQuali) {
		this.doctQuali = doctQuali;
	}


	@Column(name="DOCT_REG_NO")
	public String getDoctRegNo() {
		return this.doctRegNo;
	}

	public void setDoctRegNo(String doctRegNo) {
		this.doctRegNo = doctRegNo;
	}


	@Column(name="DOCT_TYPE")
	public String getDoctType() {
		return this.doctType;
	}

	public void setDoctType(String doctType) {
		this.doctType = doctType;
	}


	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}


	@Column(name="DRUG_SUB_TYPE_CODE")
	public String getDrugSubTypeCode() {
		return this.drugSubTypeCode;
	}

	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}


	@Column(name="DRUG_TYPE_CODE")
	public String getDrugTypeCode() {
		return this.drugTypeCode;
	}

	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}


	@Column(name="EXAMNTN_FINDINGS")
	public String getExamntnFindings() {
		return this.examntnFindings;
	}

	public void setExamntnFindings(String examntnFindings) {
		this.examntnFindings = examntnFindings;
	}


	@Column(name="FAMILY_HISTORY")
	public String getFamilyHistory() {
		return this.familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}


	@Column(name="FAMILY_HISTORY_OTHR")
	public String getFamilyHistoryOthr() {
		return this.familyHistoryOthr;
	}

	public void setFamilyHistoryOthr(String familyHistoryOthr) {
		this.familyHistoryOthr = familyHistoryOthr;
	}


	@Column(name="HISTORY_ILLNESS")
	public String getHistoryIllness() {
		return this.historyIllness;
	}

	public void setHistoryIllness(String historyIllness) {
		this.historyIllness = historyIllness;
	}


	@Column(name="HOSP_DIAGNOSIS")
	public String getHospDiagnosis() {
		return this.hospDiagnosis;
	}

	public void setHospDiagnosis(String hospDiagnosis) {
		this.hospDiagnosis = hospDiagnosis;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="MAIN_CATEGORY_CODE")
	public String getMainCategoryCode() {
		return this.mainCategoryCode;
	}

	public void setMainCategoryCode(String mainCategoryCode) {
		this.mainCategoryCode = mainCategoryCode;
	}


	@Column(name="MAIN_SYMPTOM_CODE")
	public String getMainSymptomCode() {
		return this.mainSymptomCode;
	}

	public void setMainSymptomCode(String mainSymptomCode) {
		this.mainSymptomCode = mainSymptomCode;
	}


	@Column(name="MEDICATION_PERIOD")
	public String getMedicationPeriod() {
		return this.medicationPeriod;
	}

	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}


	@Column(name="OP_CATEGORY_CODE")
	public String getOpCategoryCode() {
		return this.opCategoryCode;
	}

	public void setOpCategoryCode(String opCategoryCode) {
		this.opCategoryCode = opCategoryCode;
	}


	@Column(name="OP_ICD_CODE")
	public String getOpIcdCode() {
		return this.opIcdCode;
	}

	public void setOpIcdCode(String opIcdCode) {
		this.opIcdCode = opIcdCode;
	}


	@Column(name="OP_PACKAGE_CODE")
	public String getOpPackageCode() {
		return this.opPackageCode;
	}

	public void setOpPackageCode(String opPackageCode) {
		this.opPackageCode = opPackageCode;
	}


	@Column(name="OP_REMARKS")
	public String getOpRemarks() {
		return this.opRemarks;
	}

	public void setOpRemarks(String opRemarks) {
		this.opRemarks = opRemarks;
	}


	@Column(name="PAST_HISTORY")
	public String getPastHistory() {
		return this.pastHistory;
	}

	public void setPastHistory(String pastHistory) {
		this.pastHistory = pastHistory;
	}


	@Column(name="PAST_HISTORY_CANCERS")
	public String getPastHistoryCancers() {
		return this.pastHistoryCancers;
	}

	public void setPastHistoryCancers(String pastHistoryCancers) {
		this.pastHistoryCancers = pastHistoryCancers;
	}


	@Column(name="PAST_HISTORY_ENDDIS")
	public String getPastHistoryEnddis() {
		return this.pastHistoryEnddis;
	}

	public void setPastHistoryEnddis(String pastHistoryEnddis) {
		this.pastHistoryEnddis = pastHistoryEnddis;
	}


	@Column(name="PAST_HISTORY_OTHR")
	public String getPastHistoryOthr() {
		return this.pastHistoryOthr;
	}

	public void setPastHistoryOthr(String pastHistoryOthr) {
		this.pastHistoryOthr = pastHistoryOthr;
	}


	@Column(name="PAST_HISTORY_SURG")
	public String getPastHistorySurg() {
		return this.pastHistorySurg;
	}

	public void setPastHistorySurg(String pastHistorySurg) {
		this.pastHistorySurg = pastHistorySurg;
	}


	@Column(name="PATIENT_COMPLAINT")
	public String getPatientComplaint() {
		return this.patientComplaint;
	}

	public void setPatientComplaint(String patientComplaint) {
		this.patientComplaint = patientComplaint;
	}


	@Column(name="PERSONAL_HISTORY")
	public String getPersonalHistory() {
		return this.personalHistory;
	}

	public void setPersonalHistory(String personalHistory) {
		this.personalHistory = personalHistory;
	}


	@Column(name="POLICE_STAT_NAME")
	public String getPoliceStatName() {
		return this.policeStatName;
	}

	public void setPoliceStatName(String policeStatName) {
		this.policeStatName = policeStatName;
	}


	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
	}


	public String getStrength() {
		return this.strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}


	@Column(name="SUB_CATEGORY_CODE")
	public String getSubCategoryCode() {
		return this.subCategoryCode;
	}

	public void setSubCategoryCode(String subCategoryCode) {
		this.subCategoryCode = subCategoryCode;
	}


	@Column(name="SYMPTOM_CODE")
	public String getSymptomCode() {
		return this.symptomCode;
	}

	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}


	@Column(name="TREATING_DOC_RMKS")
	public String getTreatingDocRmks() {
		return this.treatingDocRmks;
	}

	public void setTreatingDocRmks(String treatingDocRmks) {
		this.treatingDocRmks = treatingDocRmks;
	}

}