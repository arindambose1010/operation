package com.ahct.model;

import java.util.Date;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EHFM_HOSP_TL_DC_MPG")

public class EhfmHospTlDcMpg implements java.io.Serializable
{
	
	private String tlId;
	private String dcId;
	private String langId;
	private Date crtDt;
	private String crtUser;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String  hospId;
	private String dmId;
	private String hospDist;
	/**
	 * @return the hospId
	 */
	@Id
	@Column(name="HOSP_ID", nullable=false)
	public String getHospId() {
		return hospId;
	}
	/**
	 * @param hospId the hospId to set
	 */
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	/**
	 * @return the tlId
	 */
	@Column(name="TL_ID", nullable=true)
	public String getTlId() {
		return tlId;
	}
	/**
	 * @param tlId the tlId to set
	 */
	public void setTlId(String tlId) {
		this.tlId = tlId;
	}
	/**
	 * @return the dcId
	 */
	@Column(name="DC_ID", nullable=true)
	public String getDcId() {
		return dcId;
	}
	/**
	 * @param dcId the dcId to set
	 */
	public void setDcId(String dcId) {
		this.dcId = dcId;
	}
	/**
	 * @return the langId
	 */
	@Column(name="LANG_ID", nullable=true)
	public String getLangId() {
		return langId;
	}
	/**
	 * @param langId the langId to set
	 */
	public void setLangId(String langId) {
		this.langId = langId;
	}
	/**
	 * @return the crtDt
	 */
	@Column(name="CRT_DT", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	/**
	 * @param crtDt the crtDt to set
	 */
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	/**
	 * @return the crtUser
	 */
	@Column(name="CRT_USR", nullable=true)
	public String getCrtUser() {
		return crtUser;
	}
	/**
	 * @param crtUser the crtUser to set
	 */
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	/**
	 * @return the lstUpdUsr
	 */
	@Column(name="LST_UPD_USR", nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	/**
	 * @param lstUpdUsr the lstUpdUsr to set
	 */
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	/**
	 * @return the lstUpdDt
	 */
	@Column(name="LAST_UPD_DT", nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	/**
	 * @param lstUpdDt the lstUpdDt to set
	 */
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name="DM_ID",nullable=true)
	public String getDmId() {
		return dmId;
	}
	public void setDmId(String dmId) {
		this.dmId = dmId;
	}
	
	
	@Column(name="HOSP_DIST",nullable=true)
	public String getHospDist() {
		return hospDist;
	}
	public void setHospDist(String hospDist) {
		this.hospDist = hospDist;
	}
	public EhfmHospTlDcMpg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmHospTlDcMpg(String hospId, String dcId,
			String tlId,String langId, Date crtDt, String crtUser,String lstUpdUsr,Date lstUpdDt ) {
		super();
		this.hospId = hospId;
		this.dcId = dcId;
		this.tlId=tlId;
		this.dcId=dcId;
		this.langId = langId;
		this.crtDt = crtDt;
		this.crtUser = crtUser;
		this.lstUpdDt=lstUpdDt;
		this.lstUpdUsr=lstUpdUsr;
		
	}
}