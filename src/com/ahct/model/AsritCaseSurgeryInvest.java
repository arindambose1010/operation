package com.ahct.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ASRIT_CASE_SURGERY_INVEST")
public class AsritCaseSurgeryInvest implements java.io.Serializable{
private Long docSeqId;
private String caseId;
private String caseSurgryId;
private String surgeryInvest;
private String preOpPostOp;
@Id
@Column(name="DOC_SEQ_ID",nullable=false)
public Long getDocSeqId() {
	return docSeqId;
}
public void setDocSeqId(Long docSeqId) {
	this.docSeqId = docSeqId;
}
@Column(name="CASE_ID",nullable=false)
public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
@Column(name="CASE_SURG_ID",nullable=false)
public String getCaseSurgryId() {
	return caseSurgryId;
}
public void setCaseSurgryId(String caseSurgryId) {
	this.caseSurgryId = caseSurgryId;
}
@Column(name="SURGERY_INVEST",nullable=false)
public String getSurgeryInvest() {
	return surgeryInvest;
}

public void setSurgeryInvest(String surgeryInvest) {
	this.surgeryInvest = surgeryInvest;
}
@Column(name="PRE_OR_POST_OP",nullable=false)
public String getPreOpPostOp() {
	return preOpPostOp;
}
public void setPreOpPostOp(String preOpPostOp) {
	this.preOpPostOp = preOpPostOp;
}
public AsritCaseSurgeryInvest(Long docSeqId, String caseId,
		String caseSurgryId, String surgeryInvest, String preOpPostOp) {
	super();
	this.docSeqId = docSeqId;
	this.caseId = caseId;
	this.caseSurgryId = caseSurgryId;
	this.surgeryInvest = surgeryInvest;
	this.preOpPostOp = preOpPostOp;
}
public AsritCaseSurgeryInvest() {
	super();
}


}
