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
@Table(name="EHF_EDC_AUDIT")
public class EhfEdcAudit implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private EhfEdcAuditId id;
private String remarks;

private String grpId;
private String actor;
private String currentWorkFlowId;
private String status;
private Date assigndDt;
private Date actdDt;
private String nextWorkFlwId;
private String articles;
private String actionType;
private Date startDate;
private Date endDate;
private String medcoReply;


@EmbeddedId
@AttributeOverrides( {
		@AttributeOverride(name = "actionId", column = @Column(name = "ACTION_ID", nullable = false)),
		@AttributeOverride(name = "actOrder", column = @Column(name = "ACT_ORDER", nullable = false)),
		@AttributeOverride(name = "remarksOrder", column = @Column(name = "REMARKS_ORDER", nullable = false))})
public EhfEdcAuditId getId() {
	return id;
}
public void setId(EhfEdcAuditId id) {
	this.id = id;
}

@Column(name = "WORKFLOW_ID", nullable = true, length = 50)
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

@Column(name = "GROUP_ID", nullable = true, length = 20)
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
@Column(name = "ASSIGNED_DATE", nullable = true)

public Date getAssigndDt() {
	return assigndDt;
}
public void setAssigndDt(Date assigndDt) {
	this.assigndDt = assigndDt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ACTED_DATE", nullable = true)
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
@Column(name = "ARTICLES", nullable = true,length = 20)
public String getArticles() {
	return articles;
}
public void setArticles(String articles) {
	this.articles = articles;
}
@Column(name = "ACTION_TYPE", nullable = true,length = 30)
public String getActionType() {
	return actionType;
}
public void setActionType(String actionType) {
	this.actionType = actionType;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "START_DATE", nullable = true)
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "END_DATE", nullable = true)
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
@Column(name = "MEDCO_REPLY", nullable = true,length=10)
public String getMedcoReply() {
	return medcoReply;
}
public void setMedcoReply(String medcoReply) {
	this.medcoReply = medcoReply;
}




}
