package com.ahct.CEO.vo;

import java.io.Serializable;
import java.util.List;

public class EmpanelHospVO implements Serializable
{   

    	

    private String COMB_DTL_NAME;
    private String APPSTATUSDATE;
	private String EMPANELSTATUSDATE;
	private String SPECIALITYAPPLIED;
	private String EOJEORECOMMENDED;
	private String APPLICATIONRECEIVEDDATE;
	private String APPLICATIONAPPROVEDDATE;
	private Number DURATION;    
    private String LOCATION;
    private String DISTRICT;
    private String HOSPNAME;
    private String HOSPID;
    private String HOSPLOCATION;
    private String LBL;
    private String VALUE;  
    private String STATE;
    private String LOCKEDBY;
    private String LOCKEDDATE;
    private String LOCKEDBYNAME;
    private String BEDSTRNGTH;
    private String MDMOBILE;
    private String PANNUMBER;
    private String INSPRMKS;
    private String EDCRMKS;
    private String SCARMKS;
    private String CEORMKS;
    private String HOSPTYPE;
    private String APPTYPE;
    private Number COUNT;
  
    
    
	public String getLOCKEDBYNAME() {
		return LOCKEDBYNAME;
	}
	public void setLOCKEDBYNAME(String lOCKEDBYNAME) {
		LOCKEDBYNAME = lOCKEDBYNAME;
	}
	public String getLOCKEDBY() {
		return LOCKEDBY;
	}
	public void setLOCKEDBY(String lOCKEDBY) {
		LOCKEDBY = lOCKEDBY;
	}
	public String getLOCKEDDATE() {
		return LOCKEDDATE;
	}
	public void setLOCKEDDATE(String lOCKEDDATE) {
		LOCKEDDATE = lOCKEDDATE;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getLBL() {
		return LBL;
	}
	public void setLBL(String lBL) {
		LBL = lBL;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	
    
	public String getCOMB_DTL_NAME() {
		return COMB_DTL_NAME;
	}
	public void setCOMB_DTL_NAME(String cOMB_DTL_NAME) {
		COMB_DTL_NAME = cOMB_DTL_NAME;
	}
	public String getAPPSTATUSDATE() {
		return APPSTATUSDATE;
	}
	public void setAPPSTATUSDATE(String aPPSTATUSDATE) {
		APPSTATUSDATE = aPPSTATUSDATE;
	}
	public String getEMPANELSTATUSDATE() {
		return EMPANELSTATUSDATE;
	}
	public void setEMPANELSTATUSDATE(String eMPANELSTATUSDATE) {
		EMPANELSTATUSDATE = eMPANELSTATUSDATE;
	}
	public String getSPECIALITYAPPLIED() {
		return SPECIALITYAPPLIED;
	}
	public void setSPECIALITYAPPLIED(String sPECIALITYAPPLIED) {
		SPECIALITYAPPLIED = sPECIALITYAPPLIED;
	}
	public String getEOJEORECOMMENDED() {
		return EOJEORECOMMENDED;
	}
	public void setEOJEORECOMMENDED(String eOJEORECOMMENDED) {
		EOJEORECOMMENDED = eOJEORECOMMENDED;
	}
	public String getAPPLICATIONRECEIVEDDATE() {
		return APPLICATIONRECEIVEDDATE;
	}
	public void setAPPLICATIONRECEIVEDDATE(String aPPLICATIONRECEIVEDDATE) {
		APPLICATIONRECEIVEDDATE = aPPLICATIONRECEIVEDDATE;
	}
	public String getAPPLICATIONAPPROVEDDATE() {
		return APPLICATIONAPPROVEDDATE;
	}
	public void setAPPLICATIONAPPROVEDDATE(String aPPLICATIONAPPROVEDDATE) {
		APPLICATIONAPPROVEDDATE = aPPLICATIONAPPROVEDDATE;
	}
	
	
	public Number getDURATION() {
		return DURATION;
	}
	public void setDURATION(Number dURATION) {
		DURATION = dURATION;
	}
	public String getHOSPNAME() {
		return HOSPNAME;
	}
	public void setHOSPNAME(String hOSPNAME) {
		HOSPNAME = hOSPNAME;
	}
	public String getHOSPID() {
		return HOSPID;
	}
	public void setHOSPID(String hOSPID) {
		HOSPID = hOSPID;
	}
	public String getHOSPLOCATION() {
		return HOSPLOCATION;
	}
	public void setHOSPLOCATION(String hOSPLOCATION) {
		HOSPLOCATION = hOSPLOCATION;
	}
	public String getBEDSTRNGTH() {
		return BEDSTRNGTH;
	}
	public void setBEDSTRNGTH(String bEDSTRNGTH) {
		BEDSTRNGTH = bEDSTRNGTH;
	}
	public String getMDMOBILE() {
		return MDMOBILE;
	}
	public void setMDMOBILE(String mDMOBILE) {
		MDMOBILE = mDMOBILE;
	}
	public String getPANNUMBER() {
		return PANNUMBER;
	}
	public void setPANNUMBER(String pANNUMBER) {
		PANNUMBER = pANNUMBER;
	}
	public String getINSPRMKS() {
		return INSPRMKS;
	}
	public void setINSPRMKS(String iNSPRMKS) {
		INSPRMKS = iNSPRMKS;
	}
	public String getEDCRMKS() {
		return EDCRMKS;
	}
	public void setEDCRMKS(String eDCRMKS) {
		EDCRMKS = eDCRMKS;
	}
	public String getSCARMKS() {
		return SCARMKS;
	}
	public void setSCARMKS(String sCARMKS) {
		SCARMKS = sCARMKS;
	}
	public String getCEORMKS() {
		return CEORMKS;
	}
	public void setCEORMKS(String cEORMKS) {
		CEORMKS = cEORMKS;
	}
	public String getHOSPTYPE() {
		return HOSPTYPE;
	}
	public void setHOSPTYPE(String hOSPTYPE) {
		HOSPTYPE = hOSPTYPE;
	}
	public String getAPPTYPE() {
		return APPTYPE;
	}
	public void setAPPTYPE(String aPPTYPE) {
		APPTYPE = aPPTYPE;
	}
	public Number getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}

    
   
}
