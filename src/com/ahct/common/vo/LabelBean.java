package com.ahct.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;

public class LabelBean implements Serializable
{   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
    private String VALUE;
    private Date DOB;
    private char GENDER;
    private String CONST;
    private String unitsLimit;
    private String LVL;
    private String WFTYPE;
    private String UNITID;
    private String EMPID;
    private String EMPNAME;
    private String DSGNID;
    private String DSGNNAME;
    private String LOGINNAME;
    private String LEVELID;   
    private String PATIENTTYPE;   
    private Long IDVAL;
    private Number COUNT ;
    private String USERID;
    private String MODULE;
    private String EMAILID;
    private String CUGNUM;
    private String SUBID;
	private String NEW_DIST;
	private String NEW_MAND;
	private String NEW_VILLAGE;
	private String lStrNextStatus; 
	private String msg;
	private String deptId;
	private String loginName;
    private String desingName;
    private String deptName;
	private String SUBNAME;
    private String SUBDESC;
    private String SUBCODE;
    private String APPSPE;
    private String PRIORITYID;
    private String APPRVAUTHORITY;
    private String CALLID;
    private String DISTRICT;
    private String HOSPITAL;
    private String CONTACTNO;
    private String COMPROLE;
    private String COMPON;
    private String MCINO;
    private String COMPDESC;
    private String NATURE;
    private String LINENO;
    private String NATUREID;
    private String REMARKS;
    private Number DIFF;
    private String LSTUPDDT;
    private String PATIENTSCHEME;
    private String RELATION;
    private String ENORLLNAME;
    private String EHFCARDNO;
    private String EGENDER;
    private String ENROLLSTATUS;
    private String RELATIONNAME;
    private Number SNO;
	private String DISTRICTNAME;
	private String EMPRESMAIL;
	private String EMPRESPH;
	private String EMPRATNCRDNO;
	private String DDO;
	private String empHstate;
	private String NAME;
	private String ATTACHNAME;
	private String ATTACHPATH;
	private String CRTDT;
	private String AADHAREXISTS;
	private String LSTUPDUSER;
	private String DRUGAMOUNT;
	private String SEQID;
	private Date DOS;
	private String ADDRESS;
	private String BENDOB;
	private String CARDTYPE;
	private String ACCRIDNO;
	private String IDTYPE;
	private String CLAIMNO;
	private String CASEHOSPCODE;
	private Long CSCLAMT;
	private String PATIENTID;
	private String CASEID;
	private String CASENO;
    private String CASESTATUS;
    private Date REGNDATE;
    private String ACTION;
    private String NAMEOFESTABLISHMENT;
    private String FOODACTION;
    private String ICDCODE;
    private String ICDNAME;
    private String MOBILENO;
    private String ATTACH;
    private String IcdCatName;
    private char activeYn;
    private String hospActiveYn;
    private String icdCatCode;
    private Date crtDt;
    private Date DT;
    private String INTERNALSTATUS ;
    List<LabelBean>   lstSub;
    private Number NEXTVAL;
    private String flagName;
    private int dopUnits;
    private String ahcFlag;
    private String chronicFlag;
    private String validity;
    private String caseId;
    private String schemeId;
    private String cochlearYN;
 	private String followUpType;
    private String price;
    private String otherSymptomName;
    private String MEDISURGFLAG;
    private String unitName;
    private String diagnoisedBy;
    private String hodName;
    private Date consultationTime;
    
    private String ANATOMICALNAME;
    private String ANATOMICALCODE;
    private String DISEASECODE;
    private String DISEASENAME;
    private String SUBCATCODE;
    private String SUBCATNAME;
    private String CATCODE;
    private String CATNAME;
    private String MAINCATCODE;
    private String MAINCATNAME;
    private String DIAGNOSISCODE;
    private String DIAGNOSISNAME;
    private String HOSPID;
    
    private String HOUSENO;
    private String UNITNAME ;
    private String HOSPTYPE;
    private String HOSPEMAIL;
    private String disMainId;
    private String NEWEMPCODE;
    private String PARENT_UNITID;
    private String PATH;
    private String AADHARID;
    private String AADHAREID;
    private String DISNAME;
    private String SCHEMENAME;
    private String UNITTYPE;
    private String JOBID;
    private String EMPHNO;
    private String EMPSTRTNO;
    private String EMPRESVLGE;
    private String EMPRESMDL;
    private String EMPRESDIST;
    private String TYPE;
    private String POSTCODE;
    private String EMPIDM1;
    private String EMPIDM2;
    private String EMPOFCHNO;
    private String EMPOFCSTRTNO;
    private String EMPOFCVLGE;
    private String EMPOFCDIST;
    private String EMPOFCMDL;
    private String empOstate;
    private String EMPOFCPH;
    private String EMPOFCMAIL;
    
    private Number UNITAMOUNT;
    private String DRUGUNITAMOUNT;
    private Number AMOUNT;
    private Number TOTCONSUMABLEAMOUNT;
    private Number TOTALDRUGAMT;
    private String QUANTITY;
    private String MEDFLG;
       
    //Added by ramalakshmi for DME login   
    private String PNAME;
    private String CARDNO;
    private String EMPLOYEENO;
    private String HOSPNAME;
    private String PROCDURENAME;
    private String PREAUTH_DATE;
    private String PREAUTH_AMT;   
    private String IFSCCODE;
    private String ACCOUNTNO;
	private String ADDRESSOFESTABLISHMENT;
    
	
	//OP Claim Cases
    private String CLAIMDT;
    private String CLAIMORG;
	private String LETTERNO;
	private String BILLNO;
	private String SAMPLENO;
	private String BILLDT;
	private List<LabelBean> OPCLAIMATTACHLIST;
	private Number TOTALAMT;
	//OncologyForms
	private String gender;
	private String ONCOLOGYFLAG;
	private BigDecimal COUNT1;
		
	public BigDecimal getCOUNT1() {
		return COUNT1;
	}
	public void setCOUNT1(BigDecimal cOUNT1) {
		COUNT1 = cOUNT1;
	}
	public String getONCOLOGYFLAG() {
		return ONCOLOGYFLAG;
	}
	public void setONCOLOGYFLAG(String oNCOLOGYFLAG) {
		ONCOLOGYFLAG = oNCOLOGYFLAG;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNEW_DIST() {
		return NEW_DIST;
	}
	public void setNEW_DIST(String nEW_DIST) {
		NEW_DIST = nEW_DIST;
	}
	public String getNEW_MAND() {
		return NEW_MAND;
	}
	public void setNEW_MAND(String nEW_MAND) {
		NEW_MAND = nEW_MAND;
	}
	public String getNEW_VILLAGE() {
		return NEW_VILLAGE;
	}
	public void setNEW_VILLAGE(String nEW_VILLAGE) {
		NEW_VILLAGE = nEW_VILLAGE;
	}
	public String getDDO() {
		return DDO;
	}
	public void setDDO(String dDO) {
		DDO = dDO;
	}
	public String getCASEID() {
		return CASEID;
	}
	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}
	public String getCASENO() {
		return CASENO;
	}
	public void setCASENO(String cASENO) {
		CASENO = cASENO;
	}
	public String getCASESTATUS() {
		return CASESTATUS;
	}
	public void setCASESTATUS(String cASESTATUS) {
		CASESTATUS = cASESTATUS;
	}
    public String getLSTUPDDT() {
		return LSTUPDDT;
	}
	public void setLSTUPDDT(String lSTUPDDT) {
		LSTUPDDT = lSTUPDDT;
	}
	public String getINTERNALSTATUS() {
		return INTERNALSTATUS;
	}
	public void setINTERNALSTATUS(String iNTERNALSTATUS) {
		INTERNALSTATUS = iNTERNALSTATUS;
	}
    public String getIFSCCODE() {
		return IFSCCODE;
	}
	public void setIFSCCODE(String iFSCCODE) {
		IFSCCODE = iFSCCODE;
	}
	public String getACCOUNTNO() {
		return ACCOUNTNO;
	}
	public void setACCOUNTNO(String aCCOUNTNO) {
		ACCOUNTNO = aCCOUNTNO;
	}
	public String getPNAME() {
		return PNAME;
    }
	public void setPNAME(String pNAME) {
		PNAME = pNAME;
	}
	public String getCARDNO() {
		return CARDNO;
	}
	public void setCARDNO(String cARDNO) {
		CARDNO = cARDNO;
	}
	public String getEMPLOYEENO() {
		return EMPLOYEENO;
	}
	public void setEMPLOYEENO(String eMPLOYEENO) {
		EMPLOYEENO = eMPLOYEENO;
	}
	public String getHOSPNAME() {
		return HOSPNAME;
	}
	public void setHOSPNAME(String hOSPNAME) {
		HOSPNAME = hOSPNAME;
	}
	public String getPROCDURENAME() {
		return PROCDURENAME;
	}
	public void setPROCDURENAME(String pROCDURENAME) {
		PROCDURENAME = pROCDURENAME;
	}
	public String getPREAUTH_DATE() {
		return PREAUTH_DATE;
	}
	public void setPREAUTH_DATE(String pREAUTH_DATE) {
		PREAUTH_DATE = pREAUTH_DATE;
	}
	public String getPREAUTH_AMT() {
		return PREAUTH_AMT;
	}
	public void setPREAUTH_AMT(String pREAUTH_AMT) {
		PREAUTH_AMT = pREAUTH_AMT;
	}
    public String getIcdCatCode() {
		return icdCatCode;
	}
	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}
	public char getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(char activeYn) {
		this.activeYn = activeYn;
	}
	public String getIcdCatName() {
		return IcdCatName;
	}
	public void setIcdCatName(String icdCatName) {
		IcdCatName = icdCatName;
	}
       
    public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getICDCODE() {
		return ICDCODE;
	}
	public void setICDCODE(String iCDCODE) {
		ICDCODE = iCDCODE;
	}
	public String getICDNAME() {
		return ICDNAME;
	}
	public void setICDNAME(String iCDNAME) {
		ICDNAME = iCDNAME;
	}
	public String getFOODACTION() {
		return FOODACTION;
	}
	public void setFOODACTION(String fOODACTION) {
		FOODACTION = fOODACTION;
	}
	public String getNAMEOFESTABLISHMENT() {
		return NAMEOFESTABLISHMENT;
	}
	public void setNAMEOFESTABLISHMENT(String nAMEOFESTABLISHMENT) {
		NAMEOFESTABLISHMENT = nAMEOFESTABLISHMENT;
	}
	public String getADDRESSOFESTABLISHMENT() {
		return ADDRESSOFESTABLISHMENT;
	}
	public void setADDRESSOFESTABLISHMENT(String aDDRESSOFESTABLISHMENT) {
		ADDRESSOFESTABLISHMENT = aDDRESSOFESTABLISHMENT;
	}
       
    public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getACTION() {
		return ACTION;
	}
	public void setACTION(String aCTION) {
		ACTION = aCTION;
	}
	public String getNATUREID() {
		return NATUREID;
	}
	public void setNATUREID(String nATUREID) {
		NATUREID = nATUREID;
	}
	public String getLINENO() {
		return LINENO;
	}
	public void setLINENO(String lINENO) {
		LINENO = lINENO;
	}
	public String getNATURE() {
		return NATURE;
	}
	public void setNATURE(String nATURE) {
		NATURE = nATURE;
	}
	public String getCALLID() {
		return CALLID;
	}
	public void setCALLID(String cALLID) {
		CALLID = cALLID;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public String getHOSPITAL() {
		return HOSPITAL;
	}
	public void setHOSPITAL(String hOSPITAL) {
		HOSPITAL = hOSPITAL;
	}
	public String getCOMPON() {
		return COMPON;
	}
	public void setCOMPON(String cOMPON) {
		COMPON = cOMPON;
	}
	public String getCOMPROLE() {
		return COMPROLE;
	}
	public void setCOMPROLE(String cOMPROLE) {
		COMPROLE = cOMPROLE;
	}
	public String getMCINO() {
		return MCINO;
	}
	public void setMCINO(String mCINO) {
		MCINO = mCINO;
	}
	
	public String getCOMPDESC() {
		return COMPDESC;
	}
	public void setCOMPDESC(String cOMPDESC) {
		COMPDESC = cOMPDESC;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPRIORITYID() {
		return PRIORITYID;
	}
	public void setPRIORITYID(String pRIORITYID) {
		PRIORITYID = pRIORITYID;
	}
	public String getAPPRVAUTHORITY() {
		return APPRVAUTHORITY;
	}
	public void setAPPRVAUTHORITY(String aPPRVAUTHORITY) {
		APPRVAUTHORITY = aPPRVAUTHORITY;
	}
	public LabelBean(String ID,String VALUE)
    {
      this.ID=ID;
      this.VALUE=VALUE;
    }
    public LabelBean(Long IDVAL,String VALUE)
    {
      this.IDVAL=IDVAL;
      this.VALUE=VALUE;
    }
    public LabelBean(String ID,String VALUE,String CONST)
    {
      this.ID=ID;
      this.VALUE=VALUE;
      this.CONST=CONST;
    }
    public LabelBean(String ID,String VALUE,String CONST,String LVL)
    {
      this.ID=ID;
      this.VALUE=VALUE;
      this.CONST=CONST;
      this.LVL=LVL;
    }
    public LabelBean() {
    }

    public void setID(String iD) {
        this.ID = iD;
    }

    public String getID() {
        return ID;
    }

    public void setVALUE(String vALUE) {
        this.VALUE = vALUE;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setCONST(String cONST) {
        this.CONST = cONST;
    }

    public String getCONST() {
        return CONST;
    }

    public void setLVL(String lVL) {
        this.LVL = lVL;
    }

    public String getLVL() {
        return LVL;
    }

    public void setWFTYPE(String wFTYPE) {
        this.WFTYPE = wFTYPE;
    }

    public String getWFTYPE() {
        return WFTYPE;
    }

    public void setUNITID(String uNITID) {
        this.UNITID = uNITID;
    }

    public String getUNITID() {
        return UNITID;
    }

    public void setEMPID(String eMPID) {
        this.EMPID = eMPID;
    }

    public String getEMPID() {
        return EMPID;
    }

    public void setEMPNAME(String eMPNAME) {
        this.EMPNAME = eMPNAME;
    }

    public String getEMPNAME() {
        return EMPNAME;
    }

    public void setDSGNID(String dSGNID) {
        this.DSGNID = dSGNID;
    }

    public String getDSGNID() {
        return DSGNID;
    }

    public void setLOGINNAME(String lOGINNAME) {
        this.LOGINNAME = lOGINNAME;
    }

    public String getLOGINNAME() {
        return LOGINNAME;
    }

    public void setLEVELID(String lEVELID) {
        this.LEVELID = lEVELID;
    }

    public String getLEVELID() {
        return LEVELID;
    }

    public void setIDVAL(Long iDVAL) {
        this.IDVAL = iDVAL;
    }

    public Long getIDVAL() {
        return IDVAL;
    }

    public void setCOUNT(Number cOUNT) {
        this.COUNT = cOUNT;
    }

    public Number getCOUNT() {
        return COUNT;
    }

    public void setUSERID(String uSERID) {
        this.USERID = uSERID;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setEMAILID(String eMAILID) {
        this.EMAILID = eMAILID;
    }

    public String getEMAILID() {
        return EMAILID;
    }

    public void setCUGNUM(String cUGNUM) {
        this.CUGNUM = cUGNUM;
    }

    public String getCUGNUM() {
        return CUGNUM;
    }

    public void setSUBID(String sUBID) {
        this.SUBID = sUBID;
    }

    public String getSUBID() {
        return SUBID;
    }

    public void setSUBNAME(String sUBNAME) {
        this.SUBNAME = sUBNAME;
    }

    public String getSUBNAME() {
        return SUBNAME;
    }

    public void setSUBDESC(String sUBDESC) {
        this.SUBDESC = sUBDESC;
    }

    public String getSUBDESC() {
        return SUBDESC;
    }

    public void setSUBCODE(String sUBCODE) {
        this.SUBCODE = sUBCODE;
    }

    public String getSUBCODE() {
        return SUBCODE;
    }

    public void setAPPSPE(String aPPSPE) {
        this.APPSPE = aPPSPE;
    }

    public String getAPPSPE() {
        return APPSPE;
    }
	public String getDSGNNAME() {
		return DSGNNAME;
	}
	public void setDSGNNAME(String dSGNNAME) {
		DSGNNAME = dSGNNAME;
	}
	public String getMOBILENO() {
		return MOBILENO;
	}
	public void setMOBILENO(String mOBILENO) {
			MOBILENO = mOBILENO;
	}
	public String getATTACH() {
		return ATTACH;
	}
	public void setATTACH(String aTTACH) {
		ATTACH = aTTACH;
	}
	public List<LabelBean> getLstSub() {
		return lstSub;
	}
	public void setLstSub(List<LabelBean> lstSub) {
		this.lstSub = lstSub;
	}
	public Number getNEXTVAL() {
		return NEXTVAL;
	}
	public void setNEXTVAL(Number nEXTVAL) {
		NEXTVAL = nEXTVAL;
	}
	public String getFlagName() {
		return flagName;
	}
	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}
	public Date getDT() {
		return DT;
	}
	public void setDT(Date dT) {
		DT = dT;
	}
	public String getHospActiveYn() {
		return hospActiveYn;
	}
	public void setHospActiveYn(String hospActiveYn) {
		this.hospActiveYn = hospActiveYn;
	}
	public Number getDIFF() {
		return DIFF;
	}
	public void setDIFF(Number dIFF) {
		DIFF = dIFF;
	}
	public int getDopUnits() {
		return dopUnits;
	}
	public void setDopUnits(int dopUnits) {
		this.dopUnits = dopUnits;
	}
	public String getAhcFlag() {
		return ahcFlag;
	}
	public void setAhcFlag(String ahcFlag) {
		this.ahcFlag = ahcFlag;
	}
	public String getChronicFlag() {
		return chronicFlag;
	}
	public void setChronicFlag(String chronicFlag) {
		this.chronicFlag = chronicFlag;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	public String getFollowUpType() {
		return followUpType;
	}
	public void setFollowUpType(String followUpType) {
		this.followUpType = followUpType;
	}
	public String getCochlearYN() {
		return cochlearYN;
	}
	public void setCochlearYN(String cochlearYN) {
		this.cochlearYN = cochlearYN;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOtherSymptomName() {
		return otherSymptomName;
	}
	public void setOtherSymptomName(String otherSymptomName) {
		this.otherSymptomName = otherSymptomName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDiagnoisedBy() {
		return diagnoisedBy;
	}
	public void setDiagnoisedBy(String diagnoisedBy) {
		this.diagnoisedBy = diagnoisedBy;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	public Date getConsultationTime() {
		return consultationTime;
	}
	public void setConsultationTime(Date consultationTime) {
		this.consultationTime = consultationTime;
	}
	public String getANATOMICALNAME() {
		return ANATOMICALNAME;
	}
	public void setANATOMICALNAME(String aNATOMICALNAME) {
		ANATOMICALNAME = aNATOMICALNAME;
	}
	public String getANATOMICALCODE() {
		return ANATOMICALCODE;
	}
	public void setANATOMICALCODE(String aNATOMICALCODE) {
		ANATOMICALCODE = aNATOMICALCODE;
	}
	public String getDISEASECODE() {
		return DISEASECODE;
	}
	public void setDISEASECODE(String dISEASECODE) {
		DISEASECODE = dISEASECODE;
	}
	public String getDISEASENAME() {
		return DISEASENAME;
	}
	public void setDISEASENAME(String dISEASENAME) {
		DISEASENAME = dISEASENAME;
	}
	public String getSUBCATCODE() {
		return SUBCATCODE;
	}
	public void setSUBCATCODE(String sUBCATCODE) {
		SUBCATCODE = sUBCATCODE;
	}
	public String getSUBCATNAME() {
		return SUBCATNAME;
	}
	public void setSUBCATNAME(String sUBCATNAME) {
		SUBCATNAME = sUBCATNAME;
	}
	public String getCATCODE() {
		return CATCODE;
	}
	public void setCATCODE(String cATCODE) {
		CATCODE = cATCODE;
	}
	public String getCATNAME() {
		return CATNAME;
	}
	public void setCATNAME(String cATNAME) {
		CATNAME = cATNAME;
	}
	public String getMAINCATCODE() {
		return MAINCATCODE;
	}
	public void setMAINCATCODE(String mAINCATCODE) {
		MAINCATCODE = mAINCATCODE;
	}
	public String getMAINCATNAME() {
		return MAINCATNAME;
	}
	public void setMAINCATNAME(String mAINCATNAME) {
		MAINCATNAME = mAINCATNAME;
	}
	public String getDIAGNOSISCODE() {
		return DIAGNOSISCODE;
	}
	public void setDIAGNOSISCODE(String dIAGNOSISCODE) {
		DIAGNOSISCODE = dIAGNOSISCODE;
	}
	public String getDIAGNOSISNAME() {
		return DIAGNOSISNAME;
	}
	public void setDIAGNOSISNAME(String dIAGNOSISNAME) {
		DIAGNOSISNAME = dIAGNOSISNAME;
	}
	public String getHOSPID() {
		return HOSPID;
	}
	public void setHOSPID(String hOSPID) {
		HOSPID = hOSPID;
	}

	public Date getREGNDATE() {
		return REGNDATE;
	}
	public void setREGNDATE(Date rEGNDATE) {
		REGNDATE = rEGNDATE;
	}

	public String getHOUSENO() {
		return HOUSENO;
	}
	public void setHOUSENO(String hOUSENO) {
		HOUSENO = hOUSENO;
	}
	public String getUNITNAME() {
		return UNITNAME;
	}
	public void setUNITNAME(String uNITNAME) {
		UNITNAME = uNITNAME;
	}
	public String getHOSPTYPE() {
		return HOSPTYPE;
	}
	public void setHOSPTYPE(String hOSPTYPE) {
		HOSPTYPE = hOSPTYPE;
	}
	public String getPATIENTSCHEME() {
		return PATIENTSCHEME;
	}
	public void setPATIENTSCHEME(String pATIENTSCHEME) {
		PATIENTSCHEME = pATIENTSCHEME;
	}
	public String getHOSPEMAIL() {
		return HOSPEMAIL;
	}
	public void setHOSPEMAIL(String hOSPEMAIL) {
		HOSPEMAIL = hOSPEMAIL;
	}
	public String getDisMainId() {
		return disMainId;
	}
	public void setDisMainId(String disMainId) {
		this.disMainId = disMainId;
	}
	public String getNEWEMPCODE() {
		return NEWEMPCODE;
	}
	public void setNEWEMPCODE(String nEWEMPCODE) {
		NEWEMPCODE = nEWEMPCODE;
	}
	
	public String getPATH() {
		return PATH;
	}
	public void setPATH(String pATH) {
		PATH = pATH;
	}
	public String getPARENT_UNITID() {
		return PARENT_UNITID;
	}
	public void setPARENT_UNITID(String pARENT_UNITID) {
		PARENT_UNITID = pARENT_UNITID;
	}
	public String getAADHARID() {
		return AADHARID;
	}
	public void setAADHARID(String aADHARID) {
		AADHARID = aADHARID;
	}
	public String getAADHAREID() {
		return AADHAREID;
	}
	public void setAADHAREID(String aADHAREID) {
		AADHAREID = aADHAREID;
	}
	public String getDISNAME() {
		return DISNAME;
	}
	public void setDISNAME(String dISNAME) {
		DISNAME = dISNAME;
	}
	public String getSCHEMENAME() {
		return SCHEMENAME;
	}
	public void setSCHEMENAME(String sCHEMENAME) {
		SCHEMENAME = sCHEMENAME;
	}
	public String getUNITTYPE() {
		return UNITTYPE;
	}
	public void setUNITTYPE(String uNITTYPE) {
		UNITTYPE = uNITTYPE;
	}
	public String getJOBID() {
		return JOBID;
	}
	public void setJOBID(String jOBID) {
		JOBID = jOBID;
	}
	public String getEMPHNO() {
		return EMPHNO;
	}
	public void setEMPHNO(String eMPHNO) {
		EMPHNO = eMPHNO;
	}
	public String getEMPSTRTNO() {
		return EMPSTRTNO;
	}
	public void setEMPSTRTNO(String eMPSTRTNO) {
		EMPSTRTNO = eMPSTRTNO;
	}
	public String getEMPRESVLGE() {
		return EMPRESVLGE;
	}
	public void setEMPRESVLGE(String eMPRESVLGE) {
		EMPRESVLGE = eMPRESVLGE;
	}
	public String getEMPRESMDL() {
		return EMPRESMDL;
	}
	public void setEMPRESMDL(String eMPRESMDL) {
		EMPRESMDL = eMPRESMDL;
	}
	public String getEMPRESDIST() {
		return EMPRESDIST;
	}
	public void setEMPRESDIST(String eMPRESDIST) {
		EMPRESDIST = eMPRESDIST;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getPOSTCODE() {
		return POSTCODE;
	}
	public void setPOSTCODE(String pOSTCODE) {
		POSTCODE = pOSTCODE;
	}
	public String getDISTRICTNAME() {
		return DISTRICTNAME;
	}
	public void setDISTRICTNAME(String dISTRICTNAME) {
		DISTRICTNAME = dISTRICTNAME;
	}
	public String getEMPRESMAIL() {
		return EMPRESMAIL;
	}
	public void setEMPRESMAIL(String eMPRESMAIL) {
		EMPRESMAIL = eMPRESMAIL;
	}
	public String getEMPRESPH() {
		return EMPRESPH;
	}
	public void setEMPRESPH(String eMPRESPH) {
		EMPRESPH = eMPRESPH;
	}
	public String getEMPRATNCRDNO() {
		return EMPRATNCRDNO;
	}
	public void setEMPRATNCRDNO(String eMPRATNCRDNO) {
		EMPRATNCRDNO = eMPRATNCRDNO;
	}
	public String getEMPIDM1() {
		return EMPIDM1;
	}
	public void setEMPIDM1(String eMPIDM1) {
		EMPIDM1 = eMPIDM1;
	}
	public String getEMPIDM2() {
		return EMPIDM2;
	}
	public void setEMPIDM2(String eMPIDM2) {
		EMPIDM2 = eMPIDM2;
	}
	public String getEmpHstate() {
		return empHstate;
	}
	public void setEmpHstate(String empHstate) {
		this.empHstate = empHstate;
	}
	public String getEMPOFCHNO() {
		return EMPOFCHNO;
	}
	public void setEMPOFCHNO(String eMPOFCHNO) {
		EMPOFCHNO = eMPOFCHNO;
	}
	public String getEMPOFCSTRTNO() {
		return EMPOFCSTRTNO;
	}
	public void setEMPOFCSTRTNO(String eMPOFCSTRTNO) {
		EMPOFCSTRTNO = eMPOFCSTRTNO;
	}
	public String getEMPOFCVLGE() {
		return EMPOFCVLGE;
	}
	public void setEMPOFCVLGE(String eMPOFCVLGE) {
		EMPOFCVLGE = eMPOFCVLGE;
	}
	public String getEMPOFCDIST() {
		return EMPOFCDIST;
	}
	public void setEMPOFCDIST(String eMPOFCDIST) {
		EMPOFCDIST = eMPOFCDIST;
	}
	public String getEMPOFCMDL() {
		return EMPOFCMDL;
	}
	public void setEMPOFCMDL(String eMPOFCMDL) {
		EMPOFCMDL = eMPOFCMDL;
	}
	public String getEmpOstate() {
		return empOstate;
	}
	public void setEmpOstate(String empOstate) {
		this.empOstate = empOstate;
	}
	public String getEMPOFCPH() {
		return EMPOFCPH;
	}
	public void setEMPOFCPH(String eMPOFCPH) {
		EMPOFCPH = eMPOFCPH;
	}
	public String getEMPOFCMAIL() {
		return EMPOFCMAIL;
	}
	public void setEMPOFCMAIL(String eMPOFCMAIL) {
		EMPOFCMAIL = eMPOFCMAIL;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public char getGENDER() {
		return GENDER;
	}
	public void setGENDER(char gENDER) {
		GENDER = gENDER;
	}
	public String getCONTACTNO() {
		return CONTACTNO;
	}
	public void setCONTACTNO(String cONTACTNO) {
		CONTACTNO = cONTACTNO;
	}
	public String getRELATION() {
		return RELATION;
	}
	public void setRELATION(String rELATION) {
		RELATION = rELATION;
	}
	public String getENORLLNAME() {
		return ENORLLNAME;
	}
	public void setENORLLNAME(String eNORLLNAME) {
		ENORLLNAME = eNORLLNAME;
	}
	public String getEHFCARDNO() {
		return EHFCARDNO;
	}
	public void setEHFCARDNO(String eHFCARDNO) {
		EHFCARDNO = eHFCARDNO;
	}
	public String getEGENDER() {
		return EGENDER;
	}
	public void setEGENDER(String eGENDER) {
		EGENDER = eGENDER;
	}
	public String getENROLLSTATUS() {
		return ENROLLSTATUS;
	}
	public void setENROLLSTATUS(String eNROLLSTATUS) {
		ENROLLSTATUS = eNROLLSTATUS;
	}
	public String getRELATIONNAME() {
		return RELATIONNAME;
	}
	public void setRELATIONNAME(String rELATIONNAME) {
		RELATIONNAME = rELATIONNAME;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getBENDOB() {
		return BENDOB;
	}
	public void setBENDOB(String bENDOB) {
		BENDOB = bENDOB;
	}
	public String getCARDTYPE() {
		return CARDTYPE;
	}
	public void setCARDTYPE(String cARDTYPE) {
		CARDTYPE = cARDTYPE;
	}
	public String getACCRIDNO() {
		return ACCRIDNO;
	}
	public void setACCRIDNO(String aCCRIDNO) {
		ACCRIDNO = aCCRIDNO;
	}
	public String getIDTYPE() {
		return IDTYPE;
	}
	public void setIDTYPE(String iDTYPE) {
		IDTYPE = iDTYPE;
	}
	public String getMEDISURGFLAG() {
		return MEDISURGFLAG;
	}
	public void setMEDISURGFLAG(String mEDISURGFLAG) {
		MEDISURGFLAG = mEDISURGFLAG;
	}
	public Number getUNITAMOUNT() {
		return UNITAMOUNT;
	}
	public void setUNITAMOUNT(Number uNITAMOUNT) {
		UNITAMOUNT = uNITAMOUNT;
	}
	public String getDRUGUNITAMOUNT() {
		return DRUGUNITAMOUNT;
	}
	public void setDRUGUNITAMOUNT(String dRUGUNITAMOUNT) {
		DRUGUNITAMOUNT = dRUGUNITAMOUNT;
	}
	public Number getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Number aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public Number getTOTCONSUMABLEAMOUNT() {
		return TOTCONSUMABLEAMOUNT;
	}
	public void setTOTCONSUMABLEAMOUNT(Number tOTCONSUMABLEAMOUNT) {
		TOTCONSUMABLEAMOUNT = tOTCONSUMABLEAMOUNT;
	}
	public Number getTOTALDRUGAMT() {
		return TOTALDRUGAMT;
	}
	public void setTOTALDRUGAMT(Number tOTALDRUGAMT) {
		TOTALDRUGAMT = tOTALDRUGAMT;
	}
	public String getQUANTITY() {
		return QUANTITY;
	}
	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}
	public String getMEDFLG() {
		return MEDFLG;
	}
	public void setMEDFLG(String mEDFLG) {
		MEDFLG = mEDFLG;
	}
	public Number getSNO() {
		return SNO;
	}
	public void setSNO(Number sNO) {
		SNO = sNO;
	}
	public String getATTACHNAME() {
		return ATTACHNAME;
	}
	public void setATTACHNAME(String aTTACHNAME) {
		ATTACHNAME = aTTACHNAME;
	}
	public String getATTACHPATH() {
		return ATTACHPATH;
	}
	public void setATTACHPATH(String aTTACHPATH) {
		ATTACHPATH = aTTACHPATH;
	}
	public String getCRTDT() {
		return CRTDT;
	}
	public void setCRTDT(String cRTDT) {
		CRTDT = cRTDT;
	}
	public String getAADHAREXISTS() {
		return AADHAREXISTS;
	}
	public void setAADHAREXISTS(String aADHAREXISTS) {
		AADHAREXISTS = aADHAREXISTS;
	}
	public String getLSTUPDUSER() {
		return LSTUPDUSER;
	}
	public void setLSTUPDUSER(String lSTUPDUSER) {
		LSTUPDUSER = lSTUPDUSER;
	}
	public String getDRUGAMOUNT() {
		return DRUGAMOUNT;
	}
	public void setDRUGAMOUNT(String dRUGAMOUNT) {
		DRUGAMOUNT = dRUGAMOUNT;
	}
	public String getSEQID() {
		return SEQID;
	}
	public void setSEQID(String sEQID) {
		SEQID = sEQID;
	}
	public Date getDOS() {
		return DOS;
	}
	public void setDOS(Date dOS) {
		DOS = dOS;
	}
	public String getPATIENTID() {
		return PATIENTID;
	}
	public void setPATIENTID(String pATIENTID) {
		PATIENTID = pATIENTID;
	}
	public String getUnitsLimit() {
		return unitsLimit;
	}
	public void setUnitsLimit(String unitsLimit) {
		this.unitsLimit = unitsLimit;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPATIENTTYPE() {
		return PATIENTTYPE;
	}
	public void setPATIENTTYPE(String pATIENTTYPE) {
		PATIENTTYPE = pATIENTTYPE;
	}
	public String getMODULE() {
		return MODULE;
	}
	public void setMODULE(String mODULE) {
		MODULE = mODULE;
	}
	public String getCLAIMNO() {
		return CLAIMNO;
	}
	public void setCLAIMNO(String cLAIMNO) {
		CLAIMNO = cLAIMNO;
	}
	public String getCASEHOSPCODE() {
		return CASEHOSPCODE;
	}
	public void setCASEHOSPCODE(String cASEHOSPCODE) {
		CASEHOSPCODE = cASEHOSPCODE;
	}
	public Long getCSCLAMT() {
		return CSCLAMT;
	}
	public void setCSCLAMT(Long cSCLAMT) {
		CSCLAMT = cSCLAMT;
	}
	public String getlStrNextStatus() {
		return lStrNextStatus;
	}
	public void setlStrNextStatus(String lStrNextStatus) {
		this.lStrNextStatus = lStrNextStatus;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getDesingName() {
		return desingName;
	}
	public void setDesingName(String desingName) {
		this.desingName = desingName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	

public Number getTOTALAMT() {
		return TOTALAMT;
	}
	public void setTOTALAMT(Number tOTALAMT) {
		TOTALAMT = tOTALAMT;
	}
	public List<LabelBean> getOPCLAIMATTACHLIST() {
		return OPCLAIMATTACHLIST;
	}
	public void setOPCLAIMATTACHLIST(List<LabelBean> oPCLAIMATTACHLIST) {
		OPCLAIMATTACHLIST = oPCLAIMATTACHLIST;
	}
	public String getBILLNO() {
		return BILLNO;
	}
	public void setBILLNO(String bILLNO) {
		BILLNO = bILLNO;
	}
	public String getSAMPLENO() {
		return SAMPLENO;
	}
	public void setSAMPLENO(String sAMPLENO) {
		SAMPLENO = sAMPLENO;
	}
	public String getBILLDT() {
		return BILLDT;
	}
	public void setBILLDT(String bILLDT) {
		BILLDT = bILLDT;
	}
	public String getCLAIMDT() {
		return CLAIMDT;
	}
	public void setCLAIMDT(String cLAIMDT) {
		CLAIMDT = cLAIMDT;
	}
	public String getCLAIMORG() {
		return CLAIMORG;
	}
	public void setCLAIMORG(String cLAIMORG) {
		CLAIMORG = cLAIMORG;
	}
	public String getLETTERNO() {
		return LETTERNO;
	}
	public void setLETTERNO(String lETTERNO) {
		LETTERNO = lETTERNO;
	}
}
