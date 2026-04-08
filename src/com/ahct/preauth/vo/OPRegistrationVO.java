package com.ahct.preauth.vo;
public class OPRegistrationVO implements java.io.Serializable{
    public OPRegistrationVO() {
    }
    private String ID = null;
    private String VALUE = null;
    private Number COUNT = 0;
    private String PATIENT_CARD_NO=null;
    private String PATIENTNAME = null;
    private String PATIENTGENDER = null;
    private String OCCUPATION = null;
    private Number PATIENTAGE;
    private Number AGE_MNTHS;
    private Number AGE_DAYS;
    private String DISTRICT=null;
    private String PRESENT_DISTRICT = null;
    private String PRESENT_DOORNO = null;
    private String PRESENT_HABITATION = null;
    private String PRESENT_MANDAL = null;
    private String PRESENT_STREETNAME = null;
    private String PRESENT_COLONYNAME = null;
    private String PRESENT_VILLAGE = null;
    private String PRESENT_TOWN = null;
    private String PHC=null;
    private Number FPSHOPNO = 0;
    private String ADDRESS = null;
    private String NEWPATIENTFLAG = null;
    private String CARDTYPE = null;
    private String REFFERED_TO=null;
    private String ROOM_NO=null;
    private String COMPLAINT=null;
    private Long PHONE_NO=0L;
    private String FAMILY_HEAD_NAME=null;
    private String RELATION_SHIP=null;
    private String REFERAL_ROUTE=null;
    private String CHILD_FLAG=null;
    private String HOSP_ID=null;
    private String STATUS=null;
    private Number PHASE_ID=0;
    private Number SLNO=0;
    private String CARDNUM=null;
    private char GENDER;
    private Long CID=0L;
    

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

    public void setCOUNT(Number cOUNT) {
        this.COUNT = cOUNT;
    }

    public Number getCOUNT() {
        return COUNT;
    }

    public void setPATIENTNAME(String pATIENTNAME) {
        this.PATIENTNAME = pATIENTNAME;
    }

    public String getPATIENTNAME() {
        return PATIENTNAME;
    }

    public void setPATIENTGENDER(String pATIENTGENDER) {
        this.PATIENTGENDER = pATIENTGENDER;
    }

    public String getPATIENTGENDER() {
        return PATIENTGENDER;
    }

    public void setOCCUPATION(String oCCUPATION) {
        this.OCCUPATION = oCCUPATION;
    }

    public String getOCCUPATION() {
        return OCCUPATION;
    }

  

    public void setPRESENT_DISTRICT(String pRESENT_DISTRICT) {
        this.PRESENT_DISTRICT = pRESENT_DISTRICT;
    }

    public String getPRESENT_DISTRICT() {
        return PRESENT_DISTRICT;
    }

    public void setPRESENT_DOORNO(String pRESENT_DOORNO) {
        this.PRESENT_DOORNO = pRESENT_DOORNO;
    }

    public String getPRESENT_DOORNO() {
        return PRESENT_DOORNO;
    }

    public void setPRESENT_HABITATION(String pRESENT_HABITATION) {
        this.PRESENT_HABITATION = pRESENT_HABITATION;
    }

    public String getPRESENT_HABITATION() {
        return PRESENT_HABITATION;
    }

    public void setPRESENT_MANDAL(String pRESENT_MANDAL) {
        this.PRESENT_MANDAL = pRESENT_MANDAL;
    }

    public String getPRESENT_MANDAL() {
        return PRESENT_MANDAL;
    }

    public void setPRESENT_STREETNAME(String pRESENT_STREETNAME) {
        this.PRESENT_STREETNAME = pRESENT_STREETNAME;
    }

    public String getPRESENT_STREETNAME() {
        return PRESENT_STREETNAME;
    }

    public void setPRESENT_COLONYNAME(String pRESENT_COLONYNAME) {
        this.PRESENT_COLONYNAME = pRESENT_COLONYNAME;
    }

    public String getPRESENT_COLONYNAME() {
        return PRESENT_COLONYNAME;
    }

    public void setPRESENT_VILLAGE(String pRESENT_VILLAGE) {
        this.PRESENT_VILLAGE = pRESENT_VILLAGE;
    }

    public String getPRESENT_VILLAGE() {
        return PRESENT_VILLAGE;
    }

    public void setPRESENT_TOWN(String pRESENT_TOWN) {
        this.PRESENT_TOWN = pRESENT_TOWN;
    }

    public String getPRESENT_TOWN() {
        return PRESENT_TOWN;
    }

    public void setFPSHOPNO(Number fPSHOPNO) {
        this.FPSHOPNO = fPSHOPNO;
    }

    public Number getFPSHOPNO() {
        return FPSHOPNO;
    }

    public void setADDRESS(String aDDRESS) {
        this.ADDRESS = aDDRESS;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setNEWPATIENTFLAG(String nEWPATIENTFLAG) {
        this.NEWPATIENTFLAG = nEWPATIENTFLAG;
    }

    public String getNEWPATIENTFLAG() {
        return NEWPATIENTFLAG;
    }

    public void setCARDTYPE(String cARDTYPE) {
        this.CARDTYPE = cARDTYPE;
    }

    public String getCARDTYPE() {
        return CARDTYPE;
    }

    public void setREFFERED_TO(String rEFFERED_TO) {
        this.REFFERED_TO = rEFFERED_TO;
    }

    public String getREFFERED_TO() {
        return REFFERED_TO;
    }

    public void setROOM_NO(String rOOM_NO) {
        this.ROOM_NO = rOOM_NO;
    }

    public String getROOM_NO() {
        return ROOM_NO;
    }

    public void setCOMPLAINT(String cOMPLAINT) {
        this.COMPLAINT = cOMPLAINT;
    }

    public String getCOMPLAINT() {
        return COMPLAINT;
    }

    public void setPHONE_NO(Long pHONE_NO) {
        this.PHONE_NO = pHONE_NO;
    }

    public Long getPHONE_NO() {
        return PHONE_NO;
    }

    public void setFAMILY_HEAD_NAME(String fAMILY_HEAD_NAME) {
        this.FAMILY_HEAD_NAME = fAMILY_HEAD_NAME;
    }

    public String getFAMILY_HEAD_NAME() {
        return FAMILY_HEAD_NAME;
    }

    public void setRELATION_SHIP(String rELATION_SHIP) {
        this.RELATION_SHIP = rELATION_SHIP;
    }

    public String getRELATION_SHIP() {
        return RELATION_SHIP;
    }

    public void setREFERAL_ROUTE(String rEFERAL_ROUTE) {
        this.REFERAL_ROUTE = rEFERAL_ROUTE;
    }

    public String getREFERAL_ROUTE() {
        return REFERAL_ROUTE;
    }

    public void setDISTRICT(String dISTRICT) {
        this.DISTRICT = dISTRICT;
    }

    public String getDISTRICT() {
        return DISTRICT;
    }

    public void setCHILD_FLAG(String cHILD_FLAG) {
        this.CHILD_FLAG = cHILD_FLAG;
    }

    public String getCHILD_FLAG() {
        return CHILD_FLAG;
    }

    public void setPATIENT_CARD_NO(String pATIENT_CARD_NO) {
        this.PATIENT_CARD_NO = pATIENT_CARD_NO;
    }

    public String getPATIENT_CARD_NO() {
        return PATIENT_CARD_NO;
    }

    public void setPATIENTAGE(Number pATIENTAGE) {
        this.PATIENTAGE = pATIENTAGE;
    }

    public Number getPATIENTAGE() {
        return PATIENTAGE;
    }

    public void setAGE_MNTHS(Number aGE_MNTHS) {
        this.AGE_MNTHS = aGE_MNTHS;
    }

    public Number getAGE_MNTHS() {
        return AGE_MNTHS;
    }

    public void setAGE_DAYS(Number aGE_DAYS) {
        this.AGE_DAYS = aGE_DAYS;
    }

    public Number getAGE_DAYS() {
        return AGE_DAYS;
    }

    public void setPHC(String pHC) {
        this.PHC = pHC;
    }

    public String getPHC() {
        return PHC;
    }

    public void setHOSP_ID(String hOSP_ID) {
        this.HOSP_ID = hOSP_ID;
    }

    public String getHOSP_ID() {
        return HOSP_ID;
    }

    public void setSTATUS(String sTATUS) {
        this.STATUS = sTATUS;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setPHASE_ID(Number pHASE_ID) {
        this.PHASE_ID = pHASE_ID;
    }

    public Number getPHASE_ID() {
        return PHASE_ID;
    }

    public void setSLNO(Number sLNO) {
        this.SLNO = sLNO;
    }

    public Number getSLNO() {
        return SLNO;
    }

    public void setCARDNUM(String cARDNUM) {
        this.CARDNUM = cARDNUM;
    }

    public String getCARDNUM() {
        return CARDNUM;
    }


    public void setGENDER(char gENDER) {
        this.GENDER = gENDER;
    }

    public char getGENDER() {
        return GENDER;
    }

    public void setCID(Long cID) {
        this.CID = cID;
    }

    public Long getCID() {
        return CID;
    }
}
