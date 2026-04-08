
package com.ahct.claims.valueobject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;



/**
 * @author Chandana Daruri
 * @class This Class is used as a ValueObject in claim. 
 * @version jdk 1.7
 * @Date  12 Feb 2025
 *
 */
public class FileDataVO implements Serializable {	
	
	//claim payment
	private String hospAdd;
	private String bankId;
	private String bankBranch;
	private String bankName;
	private String ifscCode;
	private String bankCategory;
	private String accNo;
	private String hospAccName;
	private String hospId;
	private String hospActiveYN;
	private String HOSPTYPE;
	private String email;
	private String caseId;
	private String totalClaim;
	private Double trustPctAmt;
	private Double tdsPctAmt;
	private Double tdsHospPctAmt;
	private Double hospPctAmt;
	private String schemeType;
	/*private Double hubAmt;
	private Double spokeAmt;
	private Double hubMnt;
	private Double spokeMnt;*/
	private String hubSpokeFlg;
	private String amount;
	private String NAMEASPERACT;
	
	private String lclaint_ac_code;
	private String lclaint_ac_number;
	private String lbeneficiary_addr;
	
	private String uniqueId;
	public String getHospAdd() {
		return hospAdd;
	}
	public void setHospAdd(String hospAdd) {
		this.hospAdd = hospAdd;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankCategory() {
		return bankCategory;
	}
	public void setBankCategory(String bankCategory) {
		this.bankCategory = bankCategory;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getHospAccName() {
		return hospAccName;
	}
	public void setHospAccName(String hospAccName) {
		this.hospAccName = hospAccName;
	}
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	public String getHospActiveYN() {
		return hospActiveYN;
	}
	public void setHospActiveYN(String hospActiveYN) {
		this.hospActiveYN = hospActiveYN;
	}
	public String getHOSPTYPE() {
		return HOSPTYPE;
	}
	public void setHOSPTYPE(String hOSPTYPE) {
		HOSPTYPE = hOSPTYPE;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getTotalClaim() {
		return totalClaim;
	}
	public void setTotalClaim(String totalClaim) {
		this.totalClaim = totalClaim;
	}
	public Double getTrustPctAmt() {
		return trustPctAmt;
	}
	public void setTrustPctAmt(Double trustPctAmt) {
		this.trustPctAmt = trustPctAmt;
	}
	public Double getTdsPctAmt() {
		return tdsPctAmt;
	}
	public void setTdsPctAmt(Double tdsPctAmt) {
		this.tdsPctAmt = tdsPctAmt;
	}
	public Double getTdsHospPctAmt() {
		return tdsHospPctAmt;
	}
	public void setTdsHospPctAmt(Double tdsHospPctAmt) {
		this.tdsHospPctAmt = tdsHospPctAmt;
	}
	public Double getHospPctAmt() {
		return hospPctAmt;
	}
	public void setHospPctAmt(Double hospPctAmt) {
		this.hospPctAmt = hospPctAmt;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	/*public Double getHubAmt() {
		return hubAmt;
	}
	public void setHubAmt(Double hubAmt) {
		this.hubAmt = hubAmt;
	}
	public Double getSpokeAmt() {
		return spokeAmt;
	}
	public void setSpokeAmt(Double spokeAmt) {
		this.spokeAmt = spokeAmt;
	}
	public Double getHubMnt() {
		return hubMnt;
	}
	public void setHubMnt(Double hubMnt) {
		this.hubMnt = hubMnt;
	}
	public Double getSpokeMnt() {
		return spokeMnt;
	}
	public void setSpokeMnt(Double spokeMnt) {
		this.spokeMnt = spokeMnt;
	}*/
	public String getHubSpokeFlg() {
		return hubSpokeFlg;
	}
	public void setHubSpokeFlg(String hubSpokeFlg) {
		this.hubSpokeFlg = hubSpokeFlg;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getLclaint_ac_code() {
		return lclaint_ac_code;
	}
	public void setLclaint_ac_code(String lclaint_ac_code) {
		this.lclaint_ac_code = lclaint_ac_code;
	}
	public String getLclaint_ac_number() {
		return lclaint_ac_number;
	}
	public void setLclaint_ac_number(String lclaint_ac_number) {
		this.lclaint_ac_number = lclaint_ac_number;
	}
	public String getLbeneficiary_addr() {
		return lbeneficiary_addr;
	}
	public void setLbeneficiary_addr(String lbeneficiary_addr) {
		this.lbeneficiary_addr = lbeneficiary_addr;
	}
	public String getNAMEASPERACT() {
		return NAMEASPERACT;
	}
	public void setNAMEASPERACT(String nAMEASPERACT) {
		NAMEASPERACT = nAMEASPERACT;
	}
	
	
	
	
	
}
