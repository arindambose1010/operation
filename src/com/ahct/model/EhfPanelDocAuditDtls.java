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
@Table(name = "EHF_PNLDOC_PMNT_AUDIT_DTLS")
public class EhfPanelDocAuditDtls implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfPanelDocAuditDtlsId id;
	private String doc_id;
	private Date assigndDt;
	private String actedBy;
	private Date actDt;
	private String remarks;
	private String groupId;
	private String currWrkflwId;
	private String regCode;
	private String fileId;
	private String paymentUID;
	
	
	@EmbeddedId
	 @AttributeOverrides ( { @AttributeOverride ( name = "pmtId", column = @Column ( name = "W_ID", nullable = false ))
	     , @AttributeOverride ( name = "actOrder", column = @Column ( name = "ACT_ORDER", nullable = false )) } )
	
	public EhfPanelDocAuditDtlsId getId() {
		return id;
	}
	public void setId(EhfPanelDocAuditDtlsId id) {
		this.id = id;
	}
	
	@Column(name = "DOC_ID", nullable = false, length = 12)
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ASSIGNED_DATE", nullable = false)
	public Date getAssigndDt() {
		return assigndDt;
	}
	
	public void setAssigndDt(Date assigndDt) {
		this.assigndDt = assigndDt;
	}
	
	@Column(name = "ACTED_BY", nullable = false, length = 12)
	public String getActedBy() {
		return actedBy;
	}
	public void setActedBy(String actedBy) {
		this.actedBy = actedBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACT_DATE", nullable = false)
	public Date getActDt() {
		return actDt;
	}
	public void setActDt(Date actDt) {
		this.actDt = actDt;
	}
	
	
	@Column(name = "REMARKS",  length = 3000)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "GROUP_ID", length = 20)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	
	@Column(name = "CURRENT_WORKFLOW_ID",length = 50)
	public String getCurrWrkflwId() {
		return currWrkflwId;
	}
	public void setCurrWrkflwId(String currWrkflwId) {
		this.currWrkflwId = currWrkflwId;
	}
	
	@Column(name = "REG_CODE",  length = 20)
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
	@Column(name = "FILE_NAME",  length = 20)
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	@Column(name = "PAYMENT_UID",  length = 20)
	public String getPaymentUID() {
		return paymentUID;
	}
	public void setPaymentUID(String paymentUID) {
		this.paymentUID = paymentUID;
	}
	
}
