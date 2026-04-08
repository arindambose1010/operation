package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
( name = "EHF_MEDICAL_AUDIT_WRKFLW_AUDIT" )

public class EhfMedAuditWorkFlowAudit implements Serializable {
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private EhfMedAuditWorkFlowAuditId id;
private String remarks;
private String grpId;
private String actor;
private String currentWorkFlowId;
private String status;
private Date assigndDt;
private Date actdDt;
private String nextWorkFlwId;



@EmbeddedId
@AttributeOverrides( {
		@AttributeOverride(name = "caseId", column = @Column(name = "CASE_ID", nullable = false, length = 25)),
		@AttributeOverride(name = "actOrder", column = @Column(name = "ACT_ORDER", nullable = false, length=5)) })
public EhfMedAuditWorkFlowAuditId getId() {
	return id;
}
public void setId(EhfMedAuditWorkFlowAuditId id) {
	this.id = id;
}

@Column(name = "CURRENT_WORKFLOW_ID", nullable = true, length = 50)
public String getCurrentWorkFlowId() {
	return currentWorkFlowId;
}
public void setCurrentWorkFlowId(String currentWorkFlowId) {
	this.currentWorkFlowId = currentWorkFlowId;
}
@Column(name = "REMARKS", nullable = true, length = 3100)
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}

@Column(name = "ACTED_GROUP_ID", nullable = true, length = 20)
public String getGrpId() {
	return grpId;
}
public void setGrpId(String grpId) {
	this.grpId = grpId;
}
@Column(name = "ACTED_BY", nullable = true, length = 20)
public String getActor() {
	return actor;
}
public void setActor(String actor) {
	this.actor = actor;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ASSIGNED_DATE", nullable = true, length = 7)

public Date getAssigndDt() {
	return assigndDt;
}
public void setAssigndDt(Date assigndDt) {
	this.assigndDt = assigndDt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ACTED_DATE", nullable = true, length = 7)
public Date getActdDt() {
	return actdDt;
}
public void setActdDt(Date actdDt) {
	this.actdDt = actdDt;
}
@Column(name = "NEXT_WORKFLOW_ID", nullable = true, length = 50)
public String getNextWorkFlwId() {
	return nextWorkFlwId;
}
public void setNextWorkFlwId(String nextWorkFlwId) {
	this.nextWorkFlwId = nextWorkFlwId;
}
@Column(name = "STATUS", nullable = true, length = 10)
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
