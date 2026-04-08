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
@Table(name = "ehfm_users_unit_dtls")
public class EhfmUsersUnitDtls implements Serializable {

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="unitId", column=@Column(name="UNIT_ID", nullable=false, length=50) ), 
        @AttributeOverride(name="userId", column=@Column(name="USER_ID", nullable=false, length=50) ), 
        @AttributeOverride(name="startDt", column=@Column(name="START_DT", nullable=false, length=7) ) } )
	
	private EhfmUsersUnitDtlsId id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_dt")
	private Date endDt;
	@Column(name = "lang_id", nullable = false)
    private String langId;
	@Column(name = "loc_id", nullable = false)
    private String locId;
     @Temporal(TemporalType.TIMESTAMP)
 	@Column(name = "crt_dt", nullable = true)
 	private Date crtDt;
 	@Column(name = "crt_usr", nullable = false)
 	private String crtUsr;
 	@Column(name = "lst_upd_usr", nullable = true)
 	private String lstUpdUsr;
 	@Temporal(TemporalType.TIMESTAMP)
 	@Column(name = "lst_upd_dt", nullable = true)
 	private Date lstUpdDt;
	
 	public EhfmUsersUnitDtls() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmUsersUnitDtls(EhfmUsersUnitDtlsId ehfmUserUnitDtlsId,
			Date endDt, String langId, String locId, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.id = ehfmUserUnitDtlsId;
		this.endDt = endDt;
		this.langId = langId;
		this.locId = locId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}

	public EhfmUsersUnitDtlsId getEhfmUserUnitDtlsId() {
		return id;
	}

	public void setEhfmUserUnitDtlsId(EhfmUsersUnitDtlsId ehfmUserUnitDtlsId) {
		this.id = ehfmUserUnitDtlsId;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
 	
 	
 	
}
