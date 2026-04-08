package com.ahct.preauth.vo;

import java.util.Date;
import java.util.List;

public class CommonDtlsVO implements java.io.Serializable{
	private String PATIENTNAME;
	private String GENDER;
	private String AGE;
	private String CARDNO;
	private String VILLAGE;
	private String MANDAL;
	private String DISTRICT;
	private String CONTACT;
	private String CASENO;
	private String CLAIMNO;
	private String INTIID;
	private String HOSPNAME;
	private String CRTDT;
	private String DISNAME;
	private String SURGNAME;
	private String STATUS;
	private String CASESTAT;
	private String PATID;
	private String CASEID;
	//Added by Sourav
	private String PATTYPE;
	private String PATDT;
	private String PATADDR;
	private String ADMTYPE;
	private String HOSPADDR;
	private String DOCID;
	private String DOCNAME;
	private String DOCREGNO;
	private String DOCQUAL;
	private String DOCCONTACT;
	private String allergy;
	private String habbits;
	private List<PreauthVO> investigations;
	//End Sourav
	private Date date;
	private String IPNO;
	private String cardType;
	private String surgeryDate;
	private String disDate;
	private String claimAmt;
	private String regdate;
	private String comorbidId;
	private String comorbidName;
	private String comorbidVal;
	private String telephonicId;
	private String diagnosisType;
	private String mainCatName;
	private String catName;
	private String subCatName;
	private String disName;
	private String disAnatomicalSitename;
	private String mithra;
	private String slabType;
	private String doctType;
	private String AGEYEARS;
	private String EMPLOYEENO;
	private String scheme;
	private String cochlear;
    private String csAdmDt;
    private String csSurgDt;
    private String csDisDt;
    private String patientIpop; 
    private String patientScheme;
    private String newBornBaby;
    private String relation;
    
	public String getSlabType() {
		return slabType;
	}
	public void setSlabType(String slabType) {
		this.slabType = slabType;
	}
	public String getTelephonicId() {
		return telephonicId;
	}
	public void setTelephonicId(String telephonicId) {
		this.telephonicId = telephonicId;
	}
	public String getComorbidId() {
		return comorbidId;
	}
	public void setComorbidId(String comorbidId) {
		this.comorbidId = comorbidId;
	}
	public String getComorbidName() {
		return comorbidName;
	}
	public void setComorbidName(String comorbidName) {
		this.comorbidName = comorbidName;
	}
	public String getComorbidVal() {
		return comorbidVal;
	}
	public void setComorbidVal(String comorbidVal) {
		this.comorbidVal = comorbidVal;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getIPNO() {
		return IPNO;
	}
	public void setIPNO(String iPNO) {
		IPNO = iPNO;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPATIENTNAME() {
		return PATIENTNAME;
	}
	public void setPATIENTNAME(String pATIENTNAME) {
		PATIENTNAME = pATIENTNAME;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public String getAGE() {
		return AGE;
	}
	public void setAGE(String aGE) {
		AGE = aGE;
	}
	public String getCARDNO() {
		return CARDNO;
	}
	public void setCARDNO(String cARDNO) {
		CARDNO = cARDNO;
	}
	public String getVILLAGE() {
		return VILLAGE;
	}
	public void setVILLAGE(String vILLAGE) {
		VILLAGE = vILLAGE;
	}
	public String getMANDAL() {
		return MANDAL;
	}
	public void setMANDAL(String mANDAL) {
		MANDAL = mANDAL;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public String getCONTACT() {
		return CONTACT;
	}
	public void setCONTACT(String cONTACT) {
		CONTACT = cONTACT;
	}
	public String getCASENO() {
		return CASENO;
	}
	public void setCASENO(String cASENO) {
		CASENO = cASENO;
	}
	public String getCLAIMNO() {
		return CLAIMNO;
	}
	public void setCLAIMNO(String cLAIMNO) {
		CLAIMNO = cLAIMNO;
	}
	public String getINTIID() {
		return INTIID;
	}
	public void setINTIID(String iNTIID) {
		INTIID = iNTIID;
	}
	public String getHOSPNAME() {
		return HOSPNAME;
	}
	public void setHOSPNAME(String hOSPNAME) {
		HOSPNAME = hOSPNAME;
	}
	public String getCRTDT() {
		return CRTDT;
	}
	public void setCRTDT(String cRTDT) {
		CRTDT = cRTDT;
	}
	public String getDISNAME() {
		return DISNAME;
	}
	public void setDISNAME(String dISNAME) {
		DISNAME = dISNAME;
	}
	public String getSURGNAME() {
		return SURGNAME;
	}
	public void setSURGNAME(String sURGNAME) {
		SURGNAME = sURGNAME;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public void setCASESTAT(String cASESTAT) {
		CASESTAT = cASESTAT;
	}
	public String getCASESTAT() {
		return CASESTAT;
	}
	public String getPATID() {
		return PATID;
	}
	public void setPATID(String pATID) {
		PATID = pATID;
	}
	public String getCASEID() {
		return CASEID;
	}
	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}
	public String getSurgeryDate() {
		return surgeryDate;
	}
	public void setSurgeryDate(String surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
	public String getDisDate() {
		return disDate;
	}
	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}
	public String getClaimAmt() {
		return claimAmt;
	}
	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPATTYPE() {
		return PATTYPE;
	}
	public void setPATTYPE(String pATTYPE) {
		PATTYPE = pATTYPE;
	}
	public String getPATDT() {
		return PATDT;
	}
	public void setPATDT(String pATDT) {
		PATDT = pATDT;
	}
	public String getPATADDR() {
		return PATADDR;
	}
	public void setPATADDR(String pATADDR) {
		PATADDR = pATADDR;
	}
	public String getADMTYPE() {
		return ADMTYPE;
	}
	public void setADMTYPE(String aDMTYPE) {
		ADMTYPE = aDMTYPE;
	}
	public String getHOSPADDR() {
		return HOSPADDR;
	}
	public void setHOSPADDR(String hOSPADDR) {
		HOSPADDR = hOSPADDR;
	}
	public String getDOCNAME() {
		return DOCNAME;
	}
	public void setDOCNAME(String dOCNAME) {
		DOCNAME = dOCNAME;
	}
	public String getDOCREGNO() {
		return DOCREGNO;
	}
	public void setDOCREGNO(String dOCREGNO) {
		DOCREGNO = dOCREGNO;
	}
	public String getDOCQUAL() {
		return DOCQUAL;
	}
	public void setDOCQUAL(String dOCQUAL) {
		DOCQUAL = dOCQUAL;
	}
	public String getDOCCONTACT() {
		return DOCCONTACT;
	}
	public void setDOCCONTACT(String dOCCONTACT) {
		DOCCONTACT = dOCCONTACT;
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
	public List<PreauthVO> getInvestigations() {
		return investigations;
	}
	public void setInvestigations(List<PreauthVO> investigations) {
		this.investigations = investigations;
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
	public String getMithra() {
		return mithra;
	}
	public void setMithra(String mithra) {
		this.mithra = mithra;
	}
	public String getDOCID() {
		return DOCID;
	}
	public void setDOCID(String dOCID) {
		DOCID = dOCID;
	}
	public String getDoctType() {
		return doctType;
	}
	public void setDoctType(String doctType) {
		this.doctType = doctType;
	}
	public String getAGEYEARS() {
		return AGEYEARS;
	}
	public void setAGEYEARS(String aGEYEARS) {
		AGEYEARS = aGEYEARS;
	}
	public String getEMPLOYEENO() {
		return EMPLOYEENO;
	}
	public void setEMPLOYEENO(String eMPLOYEENO) {
		EMPLOYEENO = eMPLOYEENO;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getCochlear() {
		return cochlear;
	}
	public void setCochlear(String cochlear) {
		this.cochlear = cochlear;
	}
	public String getCsAdmDt() {
		return csAdmDt;
	}
	public void setCsAdmDt(String csAdmDt) {
		this.csAdmDt = csAdmDt;
	}
	public String getCsSurgDt() {
		return csSurgDt;
	}
	public void setCsSurgDt(String csSurgDt) {
		this.csSurgDt = csSurgDt;
	}
	public String getCsDisDt() {
		return csDisDt;
	}
	public void setCsDisDt(String csDisDt) {
		this.csDisDt = csDisDt;
	}
	public String getPatientIpop() {
		return patientIpop;
	}
	public void setPatientIpop(String patientIpop) {
		this.patientIpop = patientIpop;
	}
	public String getPatientScheme() {
		return patientScheme;
	}
	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	public String getNewBornBaby() {
		return newBornBaby;
	}
	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}


	}
