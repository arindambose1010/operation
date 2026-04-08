package com.ahct.claims.valueobject;

import java.io.Serializable;
/**
 * @author Ishank Paharia
 * @class This Class is used as a ValueObject in claim for excel n PDF. 
 * @version jdk 1.6
 * @Date  4 April 2013
 *
 */
public class PaymentReportVO implements Serializable {

	private String sno;
	private String followUpId;
	private String caseNo;
	private String patName;
	private String wapNo;
	private String hospName;
	private String approvedAmount;

	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getFollowUpId() {
		return followUpId;
	}
	public void setFollowUpId(String followUpId) {
		this.followUpId = followUpId;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getWapNo() {
		return wapNo;
	}
	public void setWapNo(String wapNo) {
		this.wapNo = wapNo;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	
	public String getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(String approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	

}
