package com.ahct.patient.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DrugsVO implements Serializable{

	private Long drugId;
	private String drugTypeName;
	private String drugSubTypeName;
	private String drugName;
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String drugCode;
	private String route;
	private String strength;
	private String dosage;
	private String medicationPeriod;
	private String issueFromDt;
	private String issueToDt;
	private String noOfUnit;
	private String amountPerUnit;
	private String totAmt;
	private String pSubGrpName;
	private String pSubGrpCode;
	private String cSubGrpName;
	private String cSubGrpCode;
	private String routeType;
	private String routeTypeName;
	private String strengthType;
	private String strengthTypeName;
	private String routeName;
	private String strengthName;
	private String clinicalId;
	private String installment;
	private String batchNo;
	private String expiryDt;
	private Date expDt;
	private String opPkgCode;
	private String otherDrugName;
	
	
	private Number DRUGID;
	private String DRUGTYPECODE;
	private String DRUGSUBTYPECODE;
	private String PSUBGRPCODE;
	private String CSUBGRPCODE;
	private String DRUGCODE;
	private String DRUGTYPENAME;
	private String DRUGSUBTYPENAME;
	private String PSUBGRPNAME;
	private String CSUBGRPNAME;
	private String DRUGNAME;
	private String ROUTETYPE;
	private String ROUTETYPENAME;
	private String ROUTE;
	private String ROUTENAME;
	private String STRENGTHTYPE;
	private String STRENGTHTYPENAME;
	private String STRENGTH;
	private String STRENGTHNAME;
	private String DOSAGE;
	private String MEDICATIONPERIOD;
	private String OTHERDRUGNAME;
	private String EXPIRYDATE;
	private String QUANTITY;
	
	public String getClinicalId() {
		return clinicalId;
	}
	public void setClinicalId(String clinicalId) {
		this.clinicalId = clinicalId;
	}
	public String getNoOfUnit() {
		return noOfUnit;
	}
	public void setNoOfUnit(String noOfUnit) {
		this.noOfUnit = noOfUnit;
	}
	public String getAmountPerUnit() {
		return amountPerUnit;
	}
	public void setAmountPerUnit(String amountPerUnit) {
		this.amountPerUnit = amountPerUnit;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public Long getDrugId() {
		return drugId;
	}
	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	public String getDrugTypeName() {
		return drugTypeName;
	}
	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}
	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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
	public String getMedicationPeriod() {
		return medicationPeriod;
	}
	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}
	public String getIssueFromDt() {
		return issueFromDt;
	}
	public void setIssueFromDt(String issueFromDt) {
		this.issueFromDt = issueFromDt;
	}
	public String getIssueToDt() {
		return issueToDt;
	}
	public void setIssueToDt(String issueToDt) {
		this.issueToDt = issueToDt;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}
	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
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
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	public String getStrengthType() {
		return strengthType;
	}
	public void setStrengthType(String strengthType) {
		this.strengthType = strengthType;
	}
	public String getRouteTypeName() {
		return routeTypeName;
	}
	public void setRouteTypeName(String routeTypeName) {
		this.routeTypeName = routeTypeName;
	}
	public String getStrengthTypeName() {
		return strengthTypeName;
	}
	public void setStrengthTypeName(String strengthTypeName) {
		this.strengthTypeName = strengthTypeName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getStrengthName() {
		return strengthName;
	}
	public void setStrengthName(String strengthName) {
		this.strengthName = strengthName;
	}
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getExpiryDt() {
		return expiryDt;
	}
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}
	public Date getExpDt() {
		return expDt;
	}
	public void setExpDt(Date expDt) {
		this.expDt = expDt;
	}
	public String getOpPkgCode() {
		return opPkgCode;
	}
	public void setOpPkgCode(String opPkgCode) {
		this.opPkgCode = opPkgCode;
	}
	public String getOtherDrugName() {
		return otherDrugName;
	}
	public void setOtherDrugName(String otherDrugName) {
		this.otherDrugName = otherDrugName;
	}
	public String getDRUGTYPECODE() {
		return DRUGTYPECODE;
	}
	public void setDRUGTYPECODE(String dRUGTYPECODE) {
		DRUGTYPECODE = dRUGTYPECODE;
	}
	public String getDRUGSUBTYPECODE() {
		return DRUGSUBTYPECODE;
	}
	public void setDRUGSUBTYPECODE(String dRUGSUBTYPECODE) {
		DRUGSUBTYPECODE = dRUGSUBTYPECODE;
	}
	public String getPSUBGRPCODE() {
		return PSUBGRPCODE;
	}
	public void setPSUBGRPCODE(String pSUBGRPCODE) {
		PSUBGRPCODE = pSUBGRPCODE;
	}
	public String getCSUBGRPCODE() {
		return CSUBGRPCODE;
	}
	public void setCSUBGRPCODE(String cSUBGRPCODE) {
		CSUBGRPCODE = cSUBGRPCODE;
	}
	public String getDRUGCODE() {
		return DRUGCODE;
	}
	public void setDRUGCODE(String dRUGCODE) {
		DRUGCODE = dRUGCODE;
	}
	public String getDRUGTYPENAME() {
		return DRUGTYPENAME;
	}
	public void setDRUGTYPENAME(String dRUGTYPENAME) {
		DRUGTYPENAME = dRUGTYPENAME;
	}
	public String getDRUGSUBTYPENAME() {
		return DRUGSUBTYPENAME;
	}
	public void setDRUGSUBTYPENAME(String dRUGSUBTYPENAME) {
		DRUGSUBTYPENAME = dRUGSUBTYPENAME;
	}
	public String getPSUBGRPNAME() {
		return PSUBGRPNAME;
	}
	public void setPSUBGRPNAME(String pSUBGRPNAME) {
		PSUBGRPNAME = pSUBGRPNAME;
	}
	public String getCSUBGRPNAME() {
		return CSUBGRPNAME;
	}
	public void setCSUBGRPNAME(String cSUBGRPNAME) {
		CSUBGRPNAME = cSUBGRPNAME;
	}
	public String getDRUGNAME() {
		return DRUGNAME;
	}
	public void setDRUGNAME(String dRUGNAME) {
		DRUGNAME = dRUGNAME;
	}
	public String getROUTETYPE() {
		return ROUTETYPE;
	}
	public void setROUTETYPE(String rOUTETYPE) {
		ROUTETYPE = rOUTETYPE;
	}
	public String getROUTETYPENAME() {
		return ROUTETYPENAME;
	}
	public void setROUTETYPENAME(String rOUTETYPENAME) {
		ROUTETYPENAME = rOUTETYPENAME;
	}
	public String getROUTE() {
		return ROUTE;
	}
	public void setROUTE(String rOUTE) {
		ROUTE = rOUTE;
	}
	public String getROUTENAME() {
		return ROUTENAME;
	}
	public void setROUTENAME(String rOUTENAME) {
		ROUTENAME = rOUTENAME;
	}
	public String getSTRENGTHTYPE() {
		return STRENGTHTYPE;
	}
	public void setSTRENGTHTYPE(String sTRENGTHTYPE) {
		STRENGTHTYPE = sTRENGTHTYPE;
	}
	public String getSTRENGTHTYPENAME() {
		return STRENGTHTYPENAME;
	}
	public void setSTRENGTHTYPENAME(String sTRENGTHTYPENAME) {
		STRENGTHTYPENAME = sTRENGTHTYPENAME;
	}
	public String getSTRENGTH() {
		return STRENGTH;
	}
	public void setSTRENGTH(String sTRENGTH) {
		STRENGTH = sTRENGTH;
	}
	public String getSTRENGTHNAME() {
		return STRENGTHNAME;
	}
	public void setSTRENGTHNAME(String sTRENGTHNAME) {
		STRENGTHNAME = sTRENGTHNAME;
	}
	public String getDOSAGE() {
		return DOSAGE;
	}
	public void setDOSAGE(String dOSAGE) {
		DOSAGE = dOSAGE;
	}
	public String getMEDICATIONPERIOD() {
		return MEDICATIONPERIOD;
	}
	public void setMEDICATIONPERIOD(String mEDICATIONPERIOD) {
		MEDICATIONPERIOD = mEDICATIONPERIOD;
	}
	public String getOTHERDRUGNAME() {
		return OTHERDRUGNAME;
	}
	public void setOTHERDRUGNAME(String oTHERDRUGNAME) {
		OTHERDRUGNAME = oTHERDRUGNAME;
	}
	public Number getDRUGID() {
		return DRUGID;
	}
	public void setDRUGID(Number dRUGID) {
		DRUGID = dRUGID;
	}
	public String getEXPIRYDATE() {
		return EXPIRYDATE;
	}
	public void setEXPIRYDATE(String eXPIRYDATE) {
		EXPIRYDATE = eXPIRYDATE;
	}
	public String getQUANTITY() {
		return QUANTITY;
	}
	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}
	
	
}
