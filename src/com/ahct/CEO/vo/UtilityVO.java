

package com.ahct.CEO.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class UtilityVO implements Serializable
{   
    private String prop1;
    private  String prop2;
    private String prop3;
    private String prop4;
    private String prop5;
    private String LBL;
    private String VALUE;    
    private List<UtilityVO> locationsList;
    private List<UtilityVO> districtList;
    private boolean bResult;
    private byte[] allBytes;
    private String STATUS;
    private String HOSPNAME;
    private String HOSPID;
    private String HOSPLOCATION;
    private Number INTVALUE;
    private  List<UtilityVO> StatusList;
     private String CMBDTLNAME;
     private String EMPNAME;
     private String REGNO;
     private String SUPER_SPEC;
     private String EXPERIENCE;
     private String CONTACT_NO;
     private String CRT_USER;
     private Date SYSDATE1;
     private Date SYSDATE2;
     private List<UtilityVO> statuslist1;
     private String SPECIALITY;
     private String LVL;
     private String INTERNALSTATUS;
	 private Date REMARKSDATE;
	
	private String HOSPMDMAIL;
	 private String HOSPMDMOBILE;
	 private String IS_CONSUL;
	 
	 public String getIS_CONSUL() {
			return IS_CONSUL;
		}
		public void setIS_CONSUL(String iS_CONSUL) {
			IS_CONSUL = iS_CONSUL;
		}
	public String getLVL() {
		return LVL;
	}
	public void setLVL(String lVL) {
		LVL = lVL;
	}
	public String getINTERNALSTATUS() {
		return INTERNALSTATUS;
	}
	public void setINTERNALSTATUS(String iNTERNALSTATUS) {
		INTERNALSTATUS = iNTERNALSTATUS;
	}
	public String getCMBDTLNAME() {
		return CMBDTLNAME;
	}
	public String getEMPNAME() {
		return EMPNAME;
	}
	public void setEMPNAME(String eMPNAME) {
		EMPNAME = eMPNAME;
	}
	public String getREGNO() {
		return REGNO;
	}
	public void setREGNO(String rEGNO) {
		REGNO = rEGNO;
	}
	public String getSUPER_SPEC() {
		return SUPER_SPEC;
	}
	public void setSUPER_SPEC(String sUPER_SPEC) {
		SUPER_SPEC = sUPER_SPEC;
	}
	public String getEXPERIENCE() {
		return EXPERIENCE;
	}
	public void setEXPERIENCE(String eXPERIENCE) {
		EXPERIENCE = eXPERIENCE;
	}
	public String getCONTACT_NO() {
		return CONTACT_NO;
	}
	public void setCONTACT_NO(String cONTACT_NO) {
		CONTACT_NO = cONTACT_NO;
	}
	public String getCRT_USER() {
		return CRT_USER;
	}
	public void setCRT_USER(String cRT_USER) {
		CRT_USER = cRT_USER;
	}
	public Date getSYSDATE1() {
		return SYSDATE1;
	}
	public void setSYSDATE1(Date sYSDATE1) {
		SYSDATE1 = sYSDATE1;
	}
	public Date getSYSDATE2() {
		return SYSDATE2;
	}
	public void setSYSDATE2(Date sYSDATE2) {
		SYSDATE2 = sYSDATE2;
	}
	public List<UtilityVO> getStatuslist1() {
		return statuslist1;
	}
	public void setStatuslist1(List<UtilityVO> statuslist1) {
		this.statuslist1 = statuslist1;
	}
	public String getSPECIALITY() {
		return SPECIALITY;
	}
	public void setSPECIALITY(String sPECIALITY) {
		SPECIALITY = sPECIALITY;
	}
	public void setCMBDTLNAME(String cMBDTLNAME) {
		CMBDTLNAME = cMBDTLNAME;
	}
	public boolean isbResult() {
		return bResult;
	}
	public void setbResult(boolean bResult) {
		this.bResult = bResult;
	}
	public List<UtilityVO> getStatusList() {
		return StatusList;
	}
	public void setStatusList(List<UtilityVO> statusList) {
		StatusList = statusList;
	}
	public Number getINTVALUE() {
		return INTVALUE;
	}
	public void setINTVALUE(Number iNTVALUE) {
		INTVALUE = iNTVALUE;
	}
	public UtilityVO(List<UtilityVO> locationsList, List<UtilityVO> districtList){
        
        this.locationsList = locationsList;
        this.districtList = districtList;
    }
    public UtilityVO(){
        
    }
    
    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp1() {
        return prop1;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp4(String prop4) {
        this.prop4 = prop4;
    }

    public String getProp4() {
        return prop4;
    }

    public void setProp5(String prop5) {
        this.prop5 = prop5;
    }

    public String getProp5() {
        return prop5;
    }


    public void setLBL(String lBL) {
        this.LBL = lBL;
    }

    public String getLBL() {
        return LBL;
    }

    public void setVALUE(String vALUE) {
        this.VALUE = vALUE;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setLocationsList(List<UtilityVO> locationsList) {
        this.locationsList = locationsList;
    }

    public List<UtilityVO> getLocationsList() {
        return locationsList;
    }

    public void setDistrictList(List<UtilityVO> districtList) {
        this.districtList = districtList;
    }

    public List<UtilityVO> getDistrictList() {
        return districtList;
    }
    public void setBResult(boolean bResult) {
        this.bResult = bResult;
    }

    public boolean isBResult() {
        return bResult;
    }

    public void setAllBytes(byte[] allBytes) {
        this.allBytes = allBytes;
    }

    public byte[] getAllBytes() {
        return allBytes;
    }

   public void setSTATUS(String sTATUS) {
        this.STATUS = sTATUS;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setHOSPNAME(String hOSPNAME) {
        this.HOSPNAME = hOSPNAME;
    }

    public String getHOSPNAME() {
        return HOSPNAME;
    }

    public void setHOSPID(String hOSPID) {
        this.HOSPID = hOSPID;
    }

    public String getHOSPID() {
        return HOSPID;
    }

    public void setHOSPLOCATION(String hOSPLOCATION) {
        this.HOSPLOCATION = hOSPLOCATION;
    }

    public String getHOSPLOCATION() {
        return HOSPLOCATION;
    }
	
	public void setREMARKSDATE(Date rEMARKSDATE) {
        this.REMARKSDATE = rEMARKSDATE;
    }

    public Date getREMARKSDATE() {
        return REMARKSDATE;
    }
	public String getHOSPMDMAIL() {
		return HOSPMDMAIL;
	}
	public void setHOSPMDMAIL(String hOSPMDMAIL) {
		HOSPMDMAIL = hOSPMDMAIL;
	}
	public String getHOSPMDMOBILE() {
		return HOSPMDMOBILE;
	}
	public void setHOSPMDMOBILE(String hOSPMDMOBILE) {
		HOSPMDMOBILE = hOSPMDMOBILE;
	}
    
	
}
