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
@Table(name="EHF_CR_ATTACHMENTS")
public class EhfCrAttachments implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfCrAttachmentsId id;
	private String attachName;
	private String attachPath;
	private String crtUser;
	private Date crtDt;
	private String actualName;
	private String lstUpdUsr;
	private Date lstUpdDt;
	
	
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "crRefId", column = @Column(name = "CR_ID", nullable = false, length = 25)),
			@AttributeOverride(name = "attachOrder", column = @Column(name = "ATTACH_ORDER", nullable = false, length=5)) })
	public EhfCrAttachmentsId getId() {
		return id;
	}
	public void setId(EhfCrAttachmentsId id) {
		this.id = id;
	}
	@Column(name = "ATTACH_NAME", nullable = true, length = 100)
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	@Column(name = "ATTACH_PATH", nullable = true, length = 100)
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	@Column(name = "CRT_USR", nullable = true, length = 10)
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CRT_DT", nullable = true, length = 7)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name = "ACTUAL_NAME", nullable = true, length = 100)
	public String getActualName() {
		return actualName;
	}
	public void setActualName(String actualName) {
		this.actualName = actualName;
	}
	@Column(name = "LST_UPD_USR", nullable = true, length = 10)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "LST_UPD_DT", nullable = true, length = 7)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	
	
	

}
