package com.ahct.model;

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
@Table(name="EHF_ATTACH_TYPE")
public class EhfAttachType implements java.io.Serializable{
	private EhfAttachTypeId id;
	private String docName;
	private String activeYN;
	private String crtUser;
	private Date crtDt;
	private String lstUpdUser;
	private Date lstUpdDt;
	
	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="docType", column=@Column(name="doc_type", nullable=false, length=20) ), 
	   @AttributeOverride(name="updType", column=@Column(name="ATTCH_TYPE", nullable=false, length=20) ) } )
	public EhfAttachTypeId getId() {
		return id;
	}
	public void setId(EhfAttachTypeId id) {
		this.id = id;
	}
	@Column(name="mandatory", nullable=true, length=100)
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	@Column(name="ACTIVE_Y_N", nullable=true, length=2)
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name="CRT_USR", nullable=true, length=100)
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable=true, length=20)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LAST_UPD_USR", nullable=true, length=100)
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPD_DT", nullable=true, length=20)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	public EhfAttachType(EhfAttachTypeId id, String docName, String activeYN,
			String crtUser, Date crtDt, String lstUpdUser, Date lstUpdDt) {
		super();
		this.id = id;
		this.docName = docName;
		this.activeYN = activeYN;
		this.crtUser = crtUser;
		this.crtDt = crtDt;
		this.lstUpdUser = lstUpdUser;
		this.lstUpdDt = lstUpdDt;
	}
	public EhfAttachType() {
		super();
	}


}
