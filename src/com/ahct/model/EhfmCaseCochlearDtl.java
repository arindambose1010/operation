package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_CASE_COCHLEAR_DTLS database table.
 * 
 */
@Entity
@Table(name="EHFM_CASE_COCHLEAR_DTLS")
public class EhfmCaseCochlearDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACT_ORDER")
	private String actOrder;

	@Column(name="AGEOFINTERVENTION")
	private String ageofintervention;

	@Column(name="AGEOFONSETPROB")
	private String ageofonsetprob;

    @Temporal( TemporalType.DATE)
	private Date assessdate;

    @Column(name="AUTISTEND")
	private String autistend;

	@Column(name="AUTISTEND_RMRKS")
	private String autistendRmrks;

	@Column(name="AWAREOFIMPTECH")
	private String awareofimptech;

	@Column(name="AWAREOFIMPTECH_RMRKS")
	private String awareofimptechRmrks;

	@Column(name="AWARNESSMOTHERABTPROB")
	private String awarnessmotherabtprob;

	@Column(name="AWARNESSMOTHERABTPROB_RMRKS")
	private String awarnessmotherabtprobRmrks;

	@Column(name="BEHAVPROB")
	private String behavprob;

	@Column(name="BEHAVPROB_RMRKS")
	private String behavprobRmrks;

	@Column(name="BENFOFCONVENHAID")
	private String benfofconvenhaid;

	@Column(name="BENFOFCONVENHAID_RMRKS")
	private String benfofconvenhaidRmrks;

	@Id
	@Column(name="CASE_ID")
	private String caseId;

	@Column(name="CASE_STATUS")
	private String caseStatus;

	@Column(name="CHILDSPEECHREAD")
	private String childspeechread;

	@Column(name="CHILDSPEECHREAD_RMRKS")
	private String childspeechreadRmrks;

	@Column(name="CONGENABNORM")
	private String congenabnorm;

	@Column(name="CONGENABNORM_RMRKS")
	private String congenabnormRmrks;

	@Column(name="CONVENHAID")
	private String convenhaid;

	@Column(name="CONVENHAID_RMRKS")
	private String convenhaidRmrks;

	@Column(name="DEVOFAUDCOMP")
	private String devofaudcomp;

	@Column(name="DEVOFAUDCOMP_RMRKS")
	private String devofaudcompRmrks;

	@Column(name="FITORUNFIT")
	private String fitorunfit;

	@Column(name="FITORUNFIT_RMRKS")
	private String fitorunfitRmrks;

	@Column(name="FLAG")
	private String flag;

	@Column(name="IMITATIVBEHAVR")
	private String imitativbehavr;

	@Column(name="IMITATIVBEHAVR_RMRKS")
	private String imitativbehavrRmrks;

	@Column(name="INORAL_AURAL")
	private String inoralAural;

	@Column(name="INORAL_AURAL_RMRKS")
	private String inoralAuralRmrks;

	@Column(name="MIDEARINF")
	private String midearinf;

	@Column(name="MIDEARINF_RMRKS")
	private String midearinfRmrks;

	@Column(name="MOTIVERBAL")
	private String motiverbal;

	@Column(name="MOTIVERBAL_RMRKS")
	private String motiverbalRmrks;

	@Column(name="MOTIVSPEECH")
	private String motivspeech;

	@Column(name="MOTIVSPEECH_RMRKS")
	private String motivspeechRmrks;

	@Column(name="NORMALBONYCAPSULE")
	private String normalbonycapsule;

	@Column(name="NORMALBONYCAPSULE_RMRKS")
	private String normalbonycapsuleRmrks;

	@Column(name="NORMALMILE")
	private String normalmile;

	@Column(name="NORMALMILE_RMRKS")
	private String normalmileRmrks;

	@Column(name="NORMSPMECH")
	private String normspmech;

	@Column(name="NORMSPMECH_RMRKS")
	private String normspmechRmrks;

	
	@Column(name="REALEXPCT")
	private String realexpct;

	@Column(name="REALEXPCT_RMRKS")
	private String realexpctRmrks;

	@Column(name="STABLEBEHAVR")
	private String stablebehavr;

	@Column(name="STABLEBEHAVR_RMRKS")
	private String stablebehavrRmrks;

	@Column(name="STUBBORNBEHAVR")
	private String stubbornbehavr;

	@Column(name="STUBBORNBEHAVR_RMRKS")
	private String stubbornbehavrRmrks;

	@Column(name="TXTAREA1")
	private String txtarea1;

	@Column(name="TXTAREA2")
	private String txtarea2;
	
	@Column(name="FATHER_NAME")
	private String fatherName;
    public EhfmCaseCochlearDtl() {
    }

	public String getActOrder() {
		return this.actOrder;
	}

	public void setActOrder(String actOrder) {
		this.actOrder = actOrder;
	}

	public String getAgeofintervention() {
		return this.ageofintervention;
	}

	public void setAgeofintervention(String ageofintervention) {
		this.ageofintervention = ageofintervention;
	}

	public String getAgeofonsetprob() {
		return this.ageofonsetprob;
	}

	public void setAgeofonsetprob(String ageofonsetprob) {
		this.ageofonsetprob = ageofonsetprob;
	}

	public Date getAssessdate() {
		return this.assessdate;
	}

	public void setAssessdate(Date assessdate) {
		this.assessdate = assessdate;
	}

	public String getAutistend() {
		return this.autistend;
	}

	public void setAutistend(String autistend) {
		this.autistend = autistend;
	}

	public String getAutistendRmrks() {
		return this.autistendRmrks;
	}

	public void setAutistendRmrks(String autistendRmrks) {
		this.autistendRmrks = autistendRmrks;
	}

	public String getAwareofimptech() {
		return this.awareofimptech;
	}

	public void setAwareofimptech(String awareofimptech) {
		this.awareofimptech = awareofimptech;
	}

	public String getAwareofimptechRmrks() {
		return this.awareofimptechRmrks;
	}

	public void setAwareofimptechRmrks(String awareofimptechRmrks) {
		this.awareofimptechRmrks = awareofimptechRmrks;
	}

	public String getAwarnessmotherabtprob() {
		return this.awarnessmotherabtprob;
	}

	public void setAwarnessmotherabtprob(String awarnessmotherabtprob) {
		this.awarnessmotherabtprob = awarnessmotherabtprob;
	}

	public String getAwarnessmotherabtprobRmrks() {
		return this.awarnessmotherabtprobRmrks;
	}

	public void setAwarnessmotherabtprobRmrks(String awarnessmotherabtprobRmrks) {
		this.awarnessmotherabtprobRmrks = awarnessmotherabtprobRmrks;
	}

	public String getBehavprob() {
		return this.behavprob;
	}

	public void setBehavprob(String behavprob) {
		this.behavprob = behavprob;
	}

	public String getBehavprobRmrks() {
		return this.behavprobRmrks;
	}

	public void setBehavprobRmrks(String behavprobRmrks) {
		this.behavprobRmrks = behavprobRmrks;
	}

	public String getBenfofconvenhaid() {
		return this.benfofconvenhaid;
	}

	public void setBenfofconvenhaid(String benfofconvenhaid) {
		this.benfofconvenhaid = benfofconvenhaid;
	}

	public String getBenfofconvenhaidRmrks() {
		return this.benfofconvenhaidRmrks;
	}

	public void setBenfofconvenhaidRmrks(String benfofconvenhaidRmrks) {
		this.benfofconvenhaidRmrks = benfofconvenhaidRmrks;
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseStatus() {
		return this.caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getChildspeechread() {
		return this.childspeechread;
	}

	public void setChildspeechread(String childspeechread) {
		this.childspeechread = childspeechread;
	}

	public String getChildspeechreadRmrks() {
		return this.childspeechreadRmrks;
	}

	public void setChildspeechreadRmrks(String childspeechreadRmrks) {
		this.childspeechreadRmrks = childspeechreadRmrks;
	}

	public String getCongenabnorm() {
		return this.congenabnorm;
	}

	public void setCongenabnorm(String congenabnorm) {
		this.congenabnorm = congenabnorm;
	}

	public String getCongenabnormRmrks() {
		return this.congenabnormRmrks;
	}

	public void setCongenabnormRmrks(String congenabnormRmrks) {
		this.congenabnormRmrks = congenabnormRmrks;
	}

	public String getConvenhaid() {
		return this.convenhaid;
	}

	public void setConvenhaid(String convenhaid) {
		this.convenhaid = convenhaid;
	}

	public String getConvenhaidRmrks() {
		return this.convenhaidRmrks;
	}

	public void setConvenhaidRmrks(String convenhaidRmrks) {
		this.convenhaidRmrks = convenhaidRmrks;
	}

	public String getDevofaudcomp() {
		return this.devofaudcomp;
	}

	public void setDevofaudcomp(String devofaudcomp) {
		this.devofaudcomp = devofaudcomp;
	}

	public String getDevofaudcompRmrks() {
		return this.devofaudcompRmrks;
	}

	public void setDevofaudcompRmrks(String devofaudcompRmrks) {
		this.devofaudcompRmrks = devofaudcompRmrks;
	}

	public String getFitorunfit() {
		return this.fitorunfit;
	}

	public void setFitorunfit(String fitorunfit) {
		this.fitorunfit = fitorunfit;
	}

	public String getFitorunfitRmrks() {
		return this.fitorunfitRmrks;
	}

	public void setFitorunfitRmrks(String fitorunfitRmrks) {
		this.fitorunfitRmrks = fitorunfitRmrks;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getImitativbehavr() {
		return this.imitativbehavr;
	}

	public void setImitativbehavr(String imitativbehavr) {
		this.imitativbehavr = imitativbehavr;
	}

	public String getImitativbehavrRmrks() {
		return this.imitativbehavrRmrks;
	}

	public void setImitativbehavrRmrks(String imitativbehavrRmrks) {
		this.imitativbehavrRmrks = imitativbehavrRmrks;
	}

	public String getInoralAural() {
		return this.inoralAural;
	}

	public void setInoralAural(String inoralAural) {
		this.inoralAural = inoralAural;
	}

	public String getInoralAuralRmrks() {
		return this.inoralAuralRmrks;
	}

	public void setInoralAuralRmrks(String inoralAuralRmrks) {
		this.inoralAuralRmrks = inoralAuralRmrks;
	}

	public String getMidearinf() {
		return this.midearinf;
	}

	public void setMidearinf(String midearinf) {
		this.midearinf = midearinf;
	}

	public String getMidearinfRmrks() {
		return this.midearinfRmrks;
	}

	public void setMidearinfRmrks(String midearinfRmrks) {
		this.midearinfRmrks = midearinfRmrks;
	}

	public String getMotiverbal() {
		return this.motiverbal;
	}

	public void setMotiverbal(String motiverbal) {
		this.motiverbal = motiverbal;
	}

	public String getMotiverbalRmrks() {
		return this.motiverbalRmrks;
	}

	public void setMotiverbalRmrks(String motiverbalRmrks) {
		this.motiverbalRmrks = motiverbalRmrks;
	}

	public String getMotivspeech() {
		return this.motivspeech;
	}

	public void setMotivspeech(String motivspeech) {
		this.motivspeech = motivspeech;
	}

	public String getMotivspeechRmrks() {
		return this.motivspeechRmrks;
	}

	public void setMotivspeechRmrks(String motivspeechRmrks) {
		this.motivspeechRmrks = motivspeechRmrks;
	}

	public String getNormalbonycapsule() {
		return this.normalbonycapsule;
	}

	public void setNormalbonycapsule(String normalbonycapsule) {
		this.normalbonycapsule = normalbonycapsule;
	}

	public String getNormalbonycapsuleRmrks() {
		return this.normalbonycapsuleRmrks;
	}

	public void setNormalbonycapsuleRmrks(String normalbonycapsuleRmrks) {
		this.normalbonycapsuleRmrks = normalbonycapsuleRmrks;
	}

	public String getNormalmile() {
		return this.normalmile;
	}

	public void setNormalmile(String normalmile) {
		this.normalmile = normalmile;
	}

	public String getNormalmileRmrks() {
		return this.normalmileRmrks;
	}

	public void setNormalmileRmrks(String normalmileRmrks) {
		this.normalmileRmrks = normalmileRmrks;
	}

	public String getNormspmech() {
		return this.normspmech;
	}

	public void setNormspmech(String normspmech) {
		this.normspmech = normspmech;
	}

	public String getNormspmechRmrks() {
		return this.normspmechRmrks;
	}

	public void setNormspmechRmrks(String normspmechRmrks) {
		this.normspmechRmrks = normspmechRmrks;
	}

	public String getRealexpct() {
		return this.realexpct;
	}

	public void setRealexpct(String realexpct) {
		this.realexpct = realexpct;
	}

	public String getRealexpctRmrks() {
		return this.realexpctRmrks;
	}

	public void setRealexpctRmrks(String realexpctRmrks) {
		this.realexpctRmrks = realexpctRmrks;
	}

	public String getStablebehavr() {
		return this.stablebehavr;
	}

	public void setStablebehavr(String stablebehavr) {
		this.stablebehavr = stablebehavr;
	}

	public String getStablebehavrRmrks() {
		return this.stablebehavrRmrks;
	}

	public void setStablebehavrRmrks(String stablebehavrRmrks) {
		this.stablebehavrRmrks = stablebehavrRmrks;
	}

	public String getStubbornbehavr() {
		return this.stubbornbehavr;
	}

	public void setStubbornbehavr(String stubbornbehavr) {
		this.stubbornbehavr = stubbornbehavr;
	}

	public String getStubbornbehavrRmrks() {
		return this.stubbornbehavrRmrks;
	}

	public void setStubbornbehavrRmrks(String stubbornbehavrRmrks) {
		this.stubbornbehavrRmrks = stubbornbehavrRmrks;
	}

	public String getTxtarea1() {
		return this.txtarea1;
	}

	public void setTxtarea1(String txtarea1) {
		this.txtarea1 = txtarea1;
	}

	public String getTxtarea2() {
		return this.txtarea2;
	}

	public void setTxtarea2(String txtarea2) {
		this.txtarea2 = txtarea2;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

}