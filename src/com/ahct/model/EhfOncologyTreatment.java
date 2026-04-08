package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the EHF_ONCOLOGY_TREATMENT database table.
 * 
 */
@Entity
@Table(name="EHF_ONCOLOGY_TREATMENT")
@NamedQuery(name="EhfOncologyTreatment.findAll", query="SELECT e FROM EhfOncologyTreatment e")
public class EhfOncologyTreatment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PATIENT_ID")
	private String patientId;
	
	@Column(name="RECOMMENDED_TREATMENT")
	private String recommendedTreatment;
	
	@Column(name="DOSAGE_BSA")
	private String dosageBsa;

	@Column(name = "WEIGHT")
    private String weight;
	
	@Column(name = "HEIGHT")
    private String height;
	
	@Column(name = "BSA")
	private String bsa;
	
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;
	
    @Column(name = "STAGE", length = 10)
    private String stage;

    @Column(name = "TEMPERATURE", length = 20)
    private String temperature;

    @Column(name = "PULSE_RATE", length = 20)
    private String pulseRate;

    @Column(name = "RESPIRATORY_RATE", length = 20)
    private String respiratoryRate;

    @Column(name = "SPO2", length = 20)
    private String spo2;

    @Column(name = "BLOOD_PRESSURE", length = 20)
    private String bloodPressure;

    @Column(name = "GENERAL_EXAMINATION", length = 2000)
    private String generalExamination;

    @Column(name = "CARDIOVASCULAR_SYSTEM", length = 2000)
    private String cardiovascularSystem;

    @Column(name = "RESPIRATORY_SYSTEM", length = 2000)
    private String respiratorySystem;

    @Column(name = "NERVOUS_SYSTEM", length = 2000)
    private String nervousSystem;

    @Column(name = "ABDOMINAL_EXAMINATION", length = 2000)
    private String abdominalExamination;

    @Column(name = "MUSCULOSKELETAL_SYSTEM", length = 2000)
    private String musculoskeletalSystem;

    @Column(name = "CBP", length = 20)
    private String cbp;

    @Column(name = "COAGULATION_PROFILE", length = 2000)
    private String coagulationProfile;

    @Column(name = "PT", length = 200)
    private String pt;

    @Column(name = "APTT", length = 200)
    private String aptt;

    @Column(name = "INR", length = 20)
    private String inr;

    @Column(name = "BONE_MARROW_EXAM", length = 20)
    private String boneMarrowExam;

    @Column(name = "BONE_MARROW_ATTACHMENT", length = 1000)
    private String boneMarrowAttachment;

    @Column(name = "BONE_MARROW_ATTACHMENT_PATH", length = 2000)
    private String boneMarrowAttachmentPath;

    @Column(name = "RENAL_FUNCTIONAL_TEST", length = 20)
    private String renalFunctionalTest;

    @Column(name = "RENAL_ATTACHMENT", length = 1000)
    private String renalAttachment;

    @Column(name = "RENAL_ATTACHMENT_PATH", length = 2000)
    private String renalAttachmentPath;

    @Column(name = "LIVER_FUNCTIONAL_TEST", length = 20)
    private String liverFunctionalTest;

    @Column(name = "LIVER_ATTACHMENT", length = 1000)
    private String liverAttachment;

    @Column(name = "LIVER_ATTACHMENT_PATH", length = 2000)
    private String liverAttachmentPath;

    @Column(name = "THYROID_FUNCTION_TEST", length = 20)
    private String thyroidFunctionTest;

    @Column(name = "THYROID_ATTACHMENT", length = 1000)
    private String thyroidAttachment;

    @Column(name = "THYROID_ATTACHMENT_PATH", length = 2000)
    private String thyroidAttachmentPath;

    @Column(name = "COMPLETE_URINE_EXAM", length = 20)
    private String completeUrineExam;

    @Column(name = "URINE_ATTACHMENT", length = 1000)
    private String urineAttachment;

    @Column(name = "URINE_ATTACHMENT_PATH", length = 2000)
    private String urineAttachmentPath;

    @Column(name = "LIPID_PROFILE", length = 20)
    private String lipidProfile;

    @Column(name = "LIPID_ATTACHMENT", length = 1000)
    private String lipidAttachment;

    @Column(name = "LIPID_ATTACHMENT_PATH", length = 2000)
    private String lipidAttachmentPath;

    @Column(name = "D_ECHO", length = 20)
    private String dEcho;

    @Column(name = "D_ECHO_ATTACHMENT", length = 1000)
    private String dEchoAttachment;

    @Column(name = "D_ECHO_ATTACHMENT_PATH", length = 2000)
    private String dEchoAttachmentPath;

    @Column(name = "PULMONARY_FUNCTION_TEST", length = 20)
    private String pulmonaryFunctionTest;

    @Column(name = "PULMONARY_ATTACHMENT", length = 1000)
    private String pulmonaryAttachment;

    @Column(name = "PULMONARY_ATTACHMENT_PATH", length = 2000)
    private String pulmonaryAttachmentPath;

    @Column(name = "ECG", length = 20)
    private String ecg;

    @Column(name = "ECG_ATTACHMENT", length = 1000)
    private String ecgAttachment;

    @Column(name = "ECG_ATTACHMENT_PATH", length = 2000)
    private String ecgAttachmentPath;

    @Column(name = "CEA", length = 20)
    private String cea;

    @Column(name = "CEA_ATTACHMENT", length = 1000)
    private String ceaAttachment;

    @Column(name = "CEA_ATTACHMENT_PATH", length = 2000)
    private String ceaAttachmentPath;

    @Column(name = "CA_125", length = 20)
    private String ca125;

    @Column(name = "CA_125_ATTACHMENT", length = 1000)
    private String ca125Attachment;

    @Column(name = "CA_125_ATTACHMENT_PATH", length = 2000)
    private String ca125AttachmentPath;

    @Column(name = "CA_153", length = 20)
    private String ca153;

    @Column(name = "CA_153_ATTACHMENT", length = 1000)
    private String ca153Attachment;

    @Column(name = "CA_153_ATTACHMENT_PATH", length = 2000)
    private String ca153AttachmentPath;

    @Column(name = "AFP", length = 20)
    private String afp;

    @Column(name = "AFP_ATTACHMENT", length = 1000)
    private String afpAttachment;

    @Column(name = "AFP_ATTACHMENT_PATH", length = 2000)
    private String afpAttachmentPath;
    
    
    @Column(name = "CA_199", length = 20)
    private String ca199;

    @Column(name = "CA_199_ATTACHMENT", length = 1000)
    private String ca199Attachment;

    @Column(name = "CA_199_ATTACHMENT_PATH", length = 2000)
    private String ca199AttachmentPath;

    @Column(name = "PSA", length = 20)
    private String psa;

    @Column(name = "PSA_ATTACHMENT", length = 1000)
    private String psaAttachment;

    @Column(name = "PSA_ATTACHMENT_PATH", length = 2000)
    private String psaAttachmentPath;

    @Column(name = "BETA_HCG", length = 20)
    private String betaHcg;

    @Column(name = "BETA_HCG_ATTACHMENT", length = 1000)
    private String betaHcgAttachment;

    @Column(name = "BETA_HCG_ATTACHMENT_PATH", length = 2000)
    private String betaHcgAttachmentPath;

    @Column(name = "CHROMOGRANIN_A", length = 20)
    private String chromograninA;

    @Column(name = "CHROMOGRANIN_A_ATTACHMENT", length = 1000)
    private String chromograninAAttachment;

    @Column(name = "CHROMOGRANIN_A_ATTACHMENT_PATH", length = 2000)
    private String chromograninAAttachmentPath;

    @Column(name = "CECT_SCAN", length = 20)
    private String cectScan;

    @Column(name = "CECT_ATTACHMENT", length = 1000)
    private String cectAttachment;

    @Column(name = "CECT_ATTACHMENT_PATH", length = 2000)
    private String cectAttachmentPath;

    @Column(name = "MRI_SCAN", length = 20)
    private String mriScan;

    @Column(name = "MRI_ATTACHMENT", length = 1000)
    private String mriAttachment;

    @Column(name = "MRI_ATTACHMENT_PATH", length = 2000)
    private String mriAttachmentPath;

    @Column(name = "PET_CT_SCAN", length = 20)
    private String petCtScan;

    @Column(name = "PET_CT_ATTACHMENT", length = 1000)
    private String petCtAttachment;

    @Column(name = "PET_CT_ATTACHMENT_PATH", length = 2000)
    private String petCtAttachmentPath;

	@Column(name="CRT_DT")
	private Timestamp crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;
	
    @Column(name = "LST_UPD_DT")
    private Timestamp lstUpdDt;
    
    @Column(name="LST_UPD_USR")
    private String lstUpdUsr;

    @Column(name = "SUBMIT_FLAG")
    private String submit_Flag;
    
	public EhfOncologyTreatment() {
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getAbdominalExamination() {
		return this.abdominalExamination;
	}

	public void setAbdominalExamination(String abdominalExamination) {
		this.abdominalExamination = abdominalExamination;
	}

	public String getAfp() {
		return this.afp;
	}

	public void setAfp(String afp) {
		this.afp = afp;
	}

	public String getAfpAttachment() {
		return this.afpAttachment;
	}

	public void setAfpAttachment(String afpAttachment) {
		this.afpAttachment = afpAttachment;
	}

	public String getAfpAttachmentPath() {
		return this.afpAttachmentPath;
	}

	public void setAfpAttachmentPath(String afpAttachmentPath) {
		this.afpAttachmentPath = afpAttachmentPath;
	}

	public String getAptt() {
		return this.aptt;
	}

	public void setAptt(String aptt) {
		this.aptt = aptt;
	}

	public String getBetaHcg() {
		return this.betaHcg;
	}

	public void setBetaHcg(String betaHcg) {
		this.betaHcg = betaHcg;
	}

	public String getBetaHcgAttachment() {
		return this.betaHcgAttachment;
	}

	public void setBetaHcgAttachment(String betaHcgAttachment) {
		this.betaHcgAttachment = betaHcgAttachment;
	}

	public String getBetaHcgAttachmentPath() {
		return this.betaHcgAttachmentPath;
	}

	public void setBetaHcgAttachmentPath(String betaHcgAttachmentPath) {
		this.betaHcgAttachmentPath = betaHcgAttachmentPath;
	}

	public String getBloodPressure() {
		return this.bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getBoneMarrowExam() {
		return this.boneMarrowExam;
	}

	public void setBoneMarrowExam(String boneMarrowExam) {
		this.boneMarrowExam = boneMarrowExam;
	}

	public String getBsa() {
		return this.bsa;
	}

	public void setBsa(String bsa) {
		this.bsa = bsa;
	}

	public String getCa125() {
		return this.ca125;
	}

	public void setCa125(String ca125) {
		this.ca125 = ca125;
	}

	public String getCa125Attachment() {
		return this.ca125Attachment;
	}

	public void setCa125Attachment(String ca125Attachment) {
		this.ca125Attachment = ca125Attachment;
	}

	public String getCa125AttachmentPath() {
		return this.ca125AttachmentPath;
	}

	public void setCa125AttachmentPath(String ca125AttachmentPath) {
		this.ca125AttachmentPath = ca125AttachmentPath;
	}

	public String getCa153() {
		return this.ca153;
	}

	public void setCa153(String ca153) {
		this.ca153 = ca153;
	}

	public String getCa153Attachment() {
		return this.ca153Attachment;
	}

	public void setCa153Attachment(String ca153Attachment) {
		this.ca153Attachment = ca153Attachment;
	}

	public String getCa153AttachmentPath() {
		return this.ca153AttachmentPath;
	}

	public void setCa153AttachmentPath(String ca153AttachmentPath) {
		this.ca153AttachmentPath = ca153AttachmentPath;
	}

	public String getCardiovascularSystem() {
		return this.cardiovascularSystem;
	}

	public void setCardiovascularSystem(String cardiovascularSystem) {
		this.cardiovascularSystem = cardiovascularSystem;
	}

	public String getCbp() {
		return this.cbp;
	}

	public void setCbp(String cbp) {
		this.cbp = cbp;
	}

	public String getCea() {
		return this.cea;
	}

	public void setCea(String cea) {
		this.cea = cea;
	}

	public String getCeaAttachment() {
		return this.ceaAttachment;
	}

	public void setCeaAttachment(String ceaAttachment) {
		this.ceaAttachment = ceaAttachment;
	}

	public String getCeaAttachmentPath() {
		return this.ceaAttachmentPath;
	}

	public void setCeaAttachmentPath(String ceaAttachmentPath) {
		this.ceaAttachmentPath = ceaAttachmentPath;
	}

	public String getCectAttachment() {
		return this.cectAttachment;
	}

	public void setCectAttachment(String cectAttachment) {
		this.cectAttachment = cectAttachment;
	}

	public String getCectAttachmentPath() {
		return this.cectAttachmentPath;
	}

	public void setCectAttachmentPath(String cectAttachmentPath) {
		this.cectAttachmentPath = cectAttachmentPath;
	}

	public String getCectScan() {
		return this.cectScan;
	}

	public void setCectScan(String cectScan) {
		this.cectScan = cectScan;
	}

	public String getChromograninA() {
		return this.chromograninA;
	}

	public void setChromograninA(String chromograninA) {
		this.chromograninA = chromograninA;
	}

	public String getChromograninAAttachment() {
		return this.chromograninAAttachment;
	}

	public void setChromograninAAttachment(String chromograninAAttachment) {
		this.chromograninAAttachment = chromograninAAttachment;
	}

	public String getChromograninAAttachmentPath() {
		return this.chromograninAAttachmentPath;
	}

	public void setChromograninAAttachmentPath(String chromograninAAttachmentPath) {
		this.chromograninAAttachmentPath = chromograninAAttachmentPath;
	}

	public String getCoagulationProfile() {
		return this.coagulationProfile;
	}

	public void setCoagulationProfile(String coagulationProfile) {
		this.coagulationProfile = coagulationProfile;
	}

	public String getCompleteUrineExam() {
		return this.completeUrineExam;
	}

	public void setCompleteUrineExam(String completeUrineExam) {
		this.completeUrineExam = completeUrineExam;
	}

	public Timestamp getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Timestamp crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getDEcho() {
		return this.dEcho;
	}

	public void setDEcho(String dEcho) {
		this.dEcho = dEcho;
	}

	public String getDEchoAttachment() {
		return this.dEchoAttachment;
	}

	public void setDEchoAttachment(String dEchoAttachment) {
		this.dEchoAttachment = dEchoAttachment;
	}

	public String getDEchoAttachmentPath() {
		return this.dEchoAttachmentPath;
	}

	public void setDEchoAttachmentPath(String dEchoAttachmentPath) {
		this.dEchoAttachmentPath = dEchoAttachmentPath;
	}

	public String getDosageBsa() {
		return this.dosageBsa;
	}

	public void setDosageBsa(String dosageBsa) {
		this.dosageBsa = dosageBsa;
	}

	public String getEcg() {
		return this.ecg;
	}

	public void setEcg(String ecg) {
		this.ecg = ecg;
	}

	public String getEcgAttachment() {
		return this.ecgAttachment;
	}

	public void setEcgAttachment(String ecgAttachment) {
		this.ecgAttachment = ecgAttachment;
	}

	public String getEcgAttachmentPath() {
		return this.ecgAttachmentPath;
	}

	public void setEcgAttachmentPath(String ecgAttachmentPath) {
		this.ecgAttachmentPath = ecgAttachmentPath;
	}

	public String getGeneralExamination() {
		return this.generalExamination;
	}

	public void setGeneralExamination(String generalExamination) {
		this.generalExamination = generalExamination;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getInr() {
		return this.inr;
	}

	public void setInr(String inr) {
		this.inr = inr;
	}

	public String getLipidAttachment() {
		return this.lipidAttachment;
	}

	public void setLipidAttachment(String lipidAttachment) {
		this.lipidAttachment = lipidAttachment;
	}

	public String getLipidAttachmentPath() {
		return this.lipidAttachmentPath;
	}

	public void setLipidAttachmentPath(String lipidAttachmentPath) {
		this.lipidAttachmentPath = lipidAttachmentPath;
	}

	public String getLipidProfile() {
		return this.lipidProfile;
	}

	public void setLipidProfile(String lipidProfile) {
		this.lipidProfile = lipidProfile;
	}

	public String getLiverAttachment() {
		return this.liverAttachment;
	}

	public void setLiverAttachment(String liverAttachment) {
		this.liverAttachment = liverAttachment;
	}

	public String getLiverAttachmentPath() {
		return this.liverAttachmentPath;
	}

	public void setLiverAttachmentPath(String liverAttachmentPath) {
		this.liverAttachmentPath = liverAttachmentPath;
	}

	public String getLiverFunctionalTest() {
		return this.liverFunctionalTest;
	}

	public void setLiverFunctionalTest(String liverFunctionalTest) {
		this.liverFunctionalTest = liverFunctionalTest;
	}

	public Timestamp getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Timestamp lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getMriAttachment() {
		return this.mriAttachment;
	}

	public void setMriAttachment(String mriAttachment) {
		this.mriAttachment = mriAttachment;
	}

	public String getMriAttachmentPath() {
		return this.mriAttachmentPath;
	}

	public void setMriAttachmentPath(String mriAttachmentPath) {
		this.mriAttachmentPath = mriAttachmentPath;
	}

	public String getMriScan() {
		return this.mriScan;
	}

	public void setMriScan(String mriScan) {
		this.mriScan = mriScan;
	}

	public String getMusculoskeletalSystem() {
		return this.musculoskeletalSystem;
	}

	public void setMusculoskeletalSystem(String musculoskeletalSystem) {
		this.musculoskeletalSystem = musculoskeletalSystem;
	}

	public String getNervousSystem() {
		return this.nervousSystem;
	}

	public void setNervousSystem(String nervousSystem) {
		this.nervousSystem = nervousSystem;
	}

	public String getPetCtAttachment() {
		return this.petCtAttachment;
	}

	public void setPetCtAttachment(String petCtAttachment) {
		this.petCtAttachment = petCtAttachment;
	}

	public String getPetCtAttachmentPath() {
		return this.petCtAttachmentPath;
	}

	public void setPetCtAttachmentPath(String petCtAttachmentPath) {
		this.petCtAttachmentPath = petCtAttachmentPath;
	}

	public String getPetCtScan() {
		return this.petCtScan;
	}

	public void setPetCtScan(String petCtScan) {
		this.petCtScan = petCtScan;
	}

	public String getPsa() {
		return this.psa;
	}

	public void setPsa(String psa) {
		this.psa = psa;
	}

	public String getPsaAttachment() {
		return this.psaAttachment;
	}

	public void setPsaAttachment(String psaAttachment) {
		this.psaAttachment = psaAttachment;
	}

	public String getPsaAttachmentPath() {
		return this.psaAttachmentPath;
	}

	public void setPsaAttachmentPath(String psaAttachmentPath) {
		this.psaAttachmentPath = psaAttachmentPath;
	}

	public String getPt() {
		return this.pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public String getPulmonaryAttachment() {
		return this.pulmonaryAttachment;
	}

	public void setPulmonaryAttachment(String pulmonaryAttachment) {
		this.pulmonaryAttachment = pulmonaryAttachment;
	}

	public String getPulmonaryAttachmentPath() {
		return this.pulmonaryAttachmentPath;
	}

	public void setPulmonaryAttachmentPath(String pulmonaryAttachmentPath) {
		this.pulmonaryAttachmentPath = pulmonaryAttachmentPath;
	}

	public String getPulmonaryFunctionTest() {
		return this.pulmonaryFunctionTest;
	}

	public void setPulmonaryFunctionTest(String pulmonaryFunctionTest) {
		this.pulmonaryFunctionTest = pulmonaryFunctionTest;
	}

	public String getPulseRate() {
		return this.pulseRate;
	}

	public void setPulseRate(String pulseRate) {
		this.pulseRate = pulseRate;
	}

	public String getRecommendedTreatment() {
		return this.recommendedTreatment;
	}

	public void setRecommendedTreatment(String recommendedTreatment) {
		this.recommendedTreatment = recommendedTreatment;
	}

	public String getRenalAttachment() {
		return this.renalAttachment;
	}

	public void setRenalAttachment(String renalAttachment) {
		this.renalAttachment = renalAttachment;
	}

	public String getRenalAttachmentPath() {
		return this.renalAttachmentPath;
	}

	public void setRenalAttachmentPath(String renalAttachmentPath) {
		this.renalAttachmentPath = renalAttachmentPath;
	}

	public String getRenalFunctionalTest() {
		return this.renalFunctionalTest;
	}

	public void setRenalFunctionalTest(String renalFunctionalTest) {
		this.renalFunctionalTest = renalFunctionalTest;
	}

	public String getRespiratoryRate() {
		return this.respiratoryRate;
	}

	public void setRespiratoryRate(String respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public String getRespiratorySystem() {
		return this.respiratorySystem;
	}

	public void setRespiratorySystem(String respiratorySystem) {
		this.respiratorySystem = respiratorySystem;
	}

	public String getSpo2() {
		return this.spo2;
	}

	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getThyroidAttachment() {
		return this.thyroidAttachment;
	}

	public void setThyroidAttachment(String thyroidAttachment) {
		this.thyroidAttachment = thyroidAttachment;
	}

	public String getThyroidAttachmentPath() {
		return this.thyroidAttachmentPath;
	}

	public void setThyroidAttachmentPath(String thyroidAttachmentPath) {
		this.thyroidAttachmentPath = thyroidAttachmentPath;
	}

	public String getThyroidFunctionTest() {
		return this.thyroidFunctionTest;
	}

	public void setThyroidFunctionTest(String thyroidFunctionTest) {
		this.thyroidFunctionTest = thyroidFunctionTest;
	}

	public String getUrineAttachment() {
		return this.urineAttachment;
	}

	public void setUrineAttachment(String urineAttachment) {
		this.urineAttachment = urineAttachment;
	}

	public String getUrineAttachmentPath() {
		return this.urineAttachmentPath;
	}

	public void setUrineAttachmentPath(String urineAttachmentPath) {
		this.urineAttachmentPath = urineAttachmentPath;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBoneMarrowAttachment() {
		return boneMarrowAttachment;
	}

	public void setBoneMarrowAttachment(String boneMarrowAttachment) {
		this.boneMarrowAttachment = boneMarrowAttachment;
	}

	public String getBoneMarrowAttachmentPath() {
		return boneMarrowAttachmentPath;
	}

	public void setBoneMarrowAttachmentPath(String boneMarrowAttachmentPath) {
		this.boneMarrowAttachmentPath = boneMarrowAttachmentPath;
	}

	public String getdEcho() {
		return dEcho;
	}

	public void setdEcho(String dEcho) {
		this.dEcho = dEcho;
	}

	public String getdEchoAttachment() {
		return dEchoAttachment;
	}

	public void setdEchoAttachment(String dEchoAttachment) {
		this.dEchoAttachment = dEchoAttachment;
	}

	public String getdEchoAttachmentPath() {
		return dEchoAttachmentPath;
	}

	public void setdEchoAttachmentPath(String dEchoAttachmentPath) {
		this.dEchoAttachmentPath = dEchoAttachmentPath;
	}

	public String getCa199() {
		return ca199;
	}

	public void setCa199(String ca199) {
		this.ca199 = ca199;
	}

	public String getCa199Attachment() {
		return ca199Attachment;
	}

	public void setCa199Attachment(String ca199Attachment) {
		this.ca199Attachment = ca199Attachment;
	}

	public String getCa199AttachmentPath() {
		return ca199AttachmentPath;
	}

	public void setCa199AttachmentPath(String ca199AttachmentPath) {
		this.ca199AttachmentPath = ca199AttachmentPath;
	}

	public String getSubmit_Flag() {
		return submit_Flag;
	}

	public void setSubmit_Flag(String submit_Flag) {
		this.submit_Flag = submit_Flag;
	}
}