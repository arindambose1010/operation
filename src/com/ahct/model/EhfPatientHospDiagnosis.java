package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_PATIENT_HOSP_DIAGNOSIS")
public class EhfPatientHospDiagnosis implements Serializable {

	    private Date crtDt;
        private String crtUsr;
	    private String patientId;
	    private String caseId;
	    private String complaintType;
	    private String patientComplaint;
	    private String historyIllness;
	    private String pastHistory;
	    private String pastHistoryOthr;
	    private String pastHistoryCancers;
	    private String pastHistoryEndDis;
	    private String pastHistorySurg;
	    private String examntnFindings;
	    private String personalHistory;
	    private String familyHistory;
	    private String familyHistoryOthr;
	    private String mainSymptomCode;
	    private String symptomCode;
	    private String hospDiagnosis;
	    private String doctId;
	    private String doctMobNo;
	    private String doctName;
	    private String doctQuali;
	    private String doctRegNo;
	    private String doctType;
	    private Date lstUpdDt;
	    private String lstUpdUsr;
	    private String caseAdmType;
	    private Date casePropSurgDate;
	    private String ipCaseRemarks;
	    private String diagnosisType;
	    private String mainCatCode;
	    private String categoryCode;
	    private String subCatCode;
	    private String diseaseCode;
	    private String diseaseAnatCode;
	    private String drugTypeCode;
	    private String drugSubTypeCode;
	    private String asriDrugCode;
	    private String route;
	    private String strength;
	    private String dosage;
	    private String medicationPeriod;
	    private String opCategoryCode;
	    private String opPackageCode;
	    private String opIcdCode;
	    private String treatingDocRmks;
	    private String legalCaseCheck;
	    private String legalCaseNo;
	    private String policeStatName;
	    private String opRemarks;
	    private String unitName;
	    private String unitHodName;
	    private String otherComplaintName;
	    private String otherDiagnosisName;
	    
	    
	    public EhfPatientHospDiagnosis() {
			super();
			// TODO Auto-generated constructor stub
		}

		

		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name="CRT_DT", nullable = false)
	    public Date getCrtDt() {
	        return crtDt;
	    }

	    public void setCrtDt(Date crtDt) {
	        this.crtDt = crtDt;
	    }

	    @Column(name="CRT_USR", nullable = false)
	    public String getCrtUsr() {
	        return crtUsr;
	    }

	    public void setCrtUsr(String crtUsr) {
	        this.crtUsr = crtUsr;
	    }
	    @Column(name="DOCT_ID")
	    public String getDoctId() {
	        return doctId;
	    }

	    public void setDoctId(String doctId) {
	        this.doctId = doctId;
	    }

	    @Column(name="DOCT_MOB_NO")
	    public String getDoctMobNo() {
	        return doctMobNo;
	    }

	    public void setDoctMobNo(String doctMobNo) {
	        this.doctMobNo = doctMobNo;
	    }

	    @Column(name="DOCT_NAME")
	    public String getDoctName() {
	        return doctName;
	    }

	    public void setDoctName(String doctName) {
	        this.doctName = doctName;
	    }

	    @Column(name="DOCT_QUALI")
	    public String getDoctQuali() {
	        return doctQuali;
	    }

	    public void setDoctQuali(String doctQuali) {
	        this.doctQuali = doctQuali;
	    }

	    @Column(name="DOCT_REG_NO")
	    public String getDoctRegNo() {
	        return doctRegNo;
	    }

	    public void setDoctRegNo(String doctRegNo) {
	        this.doctRegNo = doctRegNo;
	    }

	    @Column(name="DOCT_TYPE")
	    public String getDoctType() {
	        return doctType;
	    }

	    public void setDoctType(String doctType) {
	        this.doctType = doctType;
	    }

	    @Column(name="EXAMNTN_FINDINGS")
	    public String getExamntnFindings() {
	        return examntnFindings;
	    }

	    public void setExamntnFindings(String examntnFindings) {
	        this.examntnFindings = examntnFindings;
	    }

	    @Column(name="HISTORY_ILLNESS")
	    public String getHistoryIllness() {
	        return historyIllness;
	    }

	    public void setHistoryIllness(String historyIllness) {
	        this.historyIllness = historyIllness;
	    }
	    @Column(name="HOSP_DIAGNOSIS")
	    public String getHospDiagnosis() {
	        return hospDiagnosis;
	    }

	    public void setHospDiagnosis(String hospDiagnosis) {
	        this.hospDiagnosis = hospDiagnosis;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="LST_UPD_DT")
	    public Date getLstUpdDt() {
	        return lstUpdDt;
	    }

	    public void setLstUpdDt(Date lstUpdDt) {
	        this.lstUpdDt = lstUpdDt;
	    }

	    @Column(name="LST_UPD_USR")
	    public String getLstUpdUsr() {
	        return lstUpdUsr;
	    }

	    public void setLstUpdUsr(String lstUpdUsr) {
	        this.lstUpdUsr = lstUpdUsr;
	    }

	    @Column(name="PAST_HISTORY")
	    public String getPastHistory() {
	        return pastHistory;
	    }

	    public void setPastHistory(String pastHistory) {
	        this.pastHistory = pastHistory;
	    }

	    @Column(name="PATIENT_COMPLAINT")
	    public String getPatientComplaint() {
	        return patientComplaint;
	    }

	    public void setPatientComplaint(String patientComplaint) {
	        this.patientComplaint = patientComplaint;
	    }

	    @Id
	    @Column(name="PATIENT_ID", nullable = false)
	    public String getPatientId() {
	        return patientId;
	    }

	    public void setPatientId(String patientId) {
	        this.patientId = patientId;
	    }
	    @Column(name="CASE_ID")
	    public String getCaseId() {
			return caseId;
		}
	    public void setCaseId(String caseId) {
			this.caseId = caseId;
		}
		@Column(name="PERSONAL_HISTORY")
		public String getPersonalHistory() {
			return personalHistory;
		}
		public void setPersonalHistory(String personalHistory) {
			this.personalHistory = personalHistory;
		}
		@Column(name="FAMILY_HISTORY")
		public String getFamilyHistory() {
			return familyHistory;
		}
		public void setFamilyHistory(String familyHistory) {
			this.familyHistory = familyHistory;
		}
		@Column(name="COMPLAINT_TYPE")
		public String getComplaintType() {
			return complaintType;
		}

		public void setComplaintType(String complaintType) {
			this.complaintType = complaintType;
		}
		@Column(name="case_adm_type")
		public String getCaseAdmType() {
			return caseAdmType;
		}
		public void setCaseAdmType(String caseAdmType) {
			this.caseAdmType = caseAdmType;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="case_prop_surgery_date")
		public Date getCasePropSurgDate() {
			return casePropSurgDate;
		}
		public void setCasePropSurgDate(Date casePropSurgDate) {
			this.casePropSurgDate = casePropSurgDate;
		}
		@Column(name="ip_case_remarks")
		public String getIpCaseRemarks() {
			return ipCaseRemarks;
		}
		public void setIpCaseRemarks(String ipCaseRemarks) {
			this.ipCaseRemarks = ipCaseRemarks;
		}
		@Column(name="past_history_othr")
		public String getPastHistoryOthr() {
			return pastHistoryOthr;
		}
		public void setPastHistoryOthr(String pastHistoryOthr) {
			this.pastHistoryOthr = pastHistoryOthr;
		}
		@Column(name="past_history_cancers")
		public String getPastHistoryCancers() {
			return pastHistoryCancers;
		}
		public void setPastHistoryCancers(String pastHistoryCancers) {
			this.pastHistoryCancers = pastHistoryCancers;
		}
		@Column(name="past_history_enddis")
		public String getPastHistoryEndDis() {
			return pastHistoryEndDis;
		}
		public void setPastHistoryEndDis(String pastHistoryEndDis) {
			this.pastHistoryEndDis = pastHistoryEndDis;
		}
		@Column(name="past_history_surg")
		public String getPastHistorySurg() {
			return pastHistorySurg;
		}
		public void setPastHistorySurg(String pastHistorySurg) {
			this.pastHistorySurg = pastHistorySurg;
		}
		@Column(name="diagnosis_type")
		public String getDiagnosisType() {
			return diagnosisType;
		}
		public void setDiagnosisType(String diagnosisType) {
			this.diagnosisType = diagnosisType;
		}
		@Column(name="main_category_code")
		public String getMainCatCode() {
			return mainCatCode;
		}
		public void setMainCatCode(String mainCatCode) {
			this.mainCatCode = mainCatCode;
		}
		@Column(name="category_code")
		public String getCategoryCode() {
			return categoryCode;
		}
		public void setCategoryCode(String categoryCode) {
			this.categoryCode = categoryCode;
		}
		@Column(name="sub_category_code")
		public String getSubCatCode() {
			return subCatCode;
		}
		public void setSubCatCode(String subCatCode) {
			this.subCatCode = subCatCode;
		}
		@Column(name="disease_code")
		public String getDiseaseCode() {
			return diseaseCode;
		}
		public void setDiseaseCode(String diseaseCode) {
			this.diseaseCode = diseaseCode;
		}
		@Column(name="disease_anat_code")
		public String getDiseaseAnatCode() {
			return diseaseAnatCode;
		}
		public void setDiseaseAnatCode(String diseaseAnatCode) {
			this.diseaseAnatCode = diseaseAnatCode;
		}
		@Column(name="drug_type_code")
		public String getDrugTypeCode() {
			return drugTypeCode;
		}
		public void setDrugTypeCode(String drugTypeCode) {
			this.drugTypeCode = drugTypeCode;
		}
		@Column(name="drug_sub_type_code")
		public String getDrugSubTypeCode() {
			return drugSubTypeCode;
		}
		public void setDrugSubTypeCode(String drugSubTypeCode) {
			this.drugSubTypeCode = drugSubTypeCode;
		}
		@Column(name="asri_drug_code")
		public String getAsriDrugCode() {
			return asriDrugCode;
		}
		public void setAsriDrugCode(String asriDrugCode) {
			this.asriDrugCode = asriDrugCode;
		}

		@Column(name="route")
		public String getRoute() {
			return route;
		}
		public void setRoute(String route) {
			this.route = route;
		}
		@Column(name="strength")
		public String getStrength() {
			return strength;
		}
		public void setStrength(String strength) {
			this.strength = strength;
		}
		@Column(name="dosage")
		public String getDosage() {
			return dosage;
		}
		public void setDosage(String dosage) {
			this.dosage = dosage;
		}
		@Column(name="medication_period")
		public String getMedicationPeriod() {
			return medicationPeriod;
		}
		public void setMedicationPeriod(String medicationPeriod) {
			this.medicationPeriod = medicationPeriod;
		}
		@Column(name="family_history_othr")
		public String getFamilyHistoryOthr() {
			return familyHistoryOthr;
		}
		public void setFamilyHistoryOthr(String familyHistoryOthr) {
			this.familyHistoryOthr = familyHistoryOthr;
		}
		@Column(name="main_symptom_code")
		public String getMainSymptomCode() {
			return mainSymptomCode;
		}
		public void setMainSymptomCode(String mainSymptomCode) {
			this.mainSymptomCode = mainSymptomCode;
		}
		@Column(name="symptom_code")
		public String getSymptomCode() {
			return symptomCode;
		}
		public void setSymptomCode(String symptomCode) {
			this.symptomCode = symptomCode;
		}
		@Column(name="op_category_code")
		public String getOpCategoryCode() {
			return opCategoryCode;
		}
		public void setOpCategoryCode(String opCategoryCode) {
			this.opCategoryCode = opCategoryCode;
		}
		@Column(name="op_package_code")
		public String getOpPackageCode() {
			return opPackageCode;
		}
		public void setOpPackageCode(String opPackageCode) {
			this.opPackageCode = opPackageCode;
		}
		@Column(name="op_icd_code")
		public String getOpIcdCode() {
			return opIcdCode;
		}
		public void setOpIcdCode(String opIcdCode) {
			this.opIcdCode = opIcdCode;
		}
		@Column(name="treating_doc_rmks")
		public String getTreatingDocRmks() {
			return treatingDocRmks;
		}
		public void setTreatingDocRmks(String treatingDocRmks) {
			this.treatingDocRmks = treatingDocRmks;
		}
		@Column(name="legal_case_check")
		public String getLegalCaseCheck() {
			return legalCaseCheck;
		}
		public void setLegalCaseCheck(String legalCaseCheck) {
			this.legalCaseCheck = legalCaseCheck;
		}
		@Column(name="legal_case_no")
		public String getLegalCaseNo() {
			return legalCaseNo;
		}
		public void setLegalCaseNo(String legalCaseNo) {
			this.legalCaseNo = legalCaseNo;
		}
		@Column(name="police_stat_name")
		public String getPoliceStatName() {
			return policeStatName;
		}
		public void setPoliceStatName(String policeStatName) {
			this.policeStatName = policeStatName;
		}
		@Column(name="op_remarks")
		public String getOpRemarks() {
			return opRemarks;
		}
		public void setOpRemarks(String opRemarks) {
			this.opRemarks = opRemarks;
		}


		@Column(name="unit_name")
		public String getUnitName() {
			return unitName;
		}



		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}


		@Column(name="unit_hod_name")
		public String getUnitHodName() {
			return unitHodName;
		}



		public void setUnitHodName(String unitHodName) {
			this.unitHodName = unitHodName;
		}


		@Column(name="other_complaint_name")
		public String getOtherComplaintName() {
			return otherComplaintName;
		}



		public void setOtherComplaintName(String otherComplaintName) {
			this.otherComplaintName = otherComplaintName;
		}


		@Column(name="other_diagnosis_name")
		public String getOtherDiagnosisName() {
			return otherDiagnosisName;
		}



		public void setOtherDiagnosisName(String otherDiagnosisName) {
			this.otherDiagnosisName = otherDiagnosisName;
		}


		
		
		
		
		

}
