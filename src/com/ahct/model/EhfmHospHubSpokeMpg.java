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
@Table( name = "ehfm_hosp_hub_spoke_mpg" )
public class EhfmHospHubSpokeMpg implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfmHospHubSpokeMpgId id;
	
	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="hubHospId", column=@Column(name="HUB_HOSP_ID", nullable=false)),
	   @AttributeOverride(name="spokeHospId", column=@Column(name="SPOKE_HOSP_ID", nullable=false)),
	   @AttributeOverride(name="spokeUserId", column=@Column(name="SPOKE_USER_ID", nullable=false)),
	   @AttributeOverride(name="hubUserId", column=@Column(name="HUB_USER_ID", nullable=false))
	} )
	
	public EhfmHospHubSpokeMpgId getId() {
		return id;
	}
	public void setId(EhfmHospHubSpokeMpgId id) {
		this.id = id;
	}

	

	private String activeYn;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;


	@Column(name = "ACTIVE_YN")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT",nullable=false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT",nullable=false)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	public EhfmHospHubSpokeMpg() {
		super();
	}
	
	
	public EhfmHospHubSpokeMpg(EhfmHospHubSpokeMpgId id,
			String activeYn, Date crtDt,String crtUsr,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.id = id;
		this.activeYn = activeYn;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}
	
}
