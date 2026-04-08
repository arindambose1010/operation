package com.ahct.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "ehfm_edc_hosp_action_dtls")
public class EhfmEDCHospActionDtls  implements java.io.Serializable{
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hospId", column = @Column(name = "hosp_id", nullable = false, length = 12)),
			@AttributeOverride(name = "scheme", column = @Column(name = "scheme", nullable = false, length = 5)) })
	private EhfmEDCHospActionDtlsId id;
	
	@Column(name = "hosp_status", nullable = false)
	private String hospStatus;
	
	@Column(name = "crt_usr", nullable = false)
	private String crtUsr;
	@Column(name = "crt_dt", nullable = false)
	private Date crtDt;
	
	@Column(name = "lst_upd_usr", nullable = false)
	private String lstUpdUsr;
	
	@Column(name = "lst_upd_dt", nullable = false)
	private Date lstUpdDt;
	
	@Column(name = "nabh_flag", nullable = false)
	private String nabhFlag;
	
	
	public EhfmEDCHospActionDtlsId getId() {
		return id;
	}
	public void setId(EhfmEDCHospActionDtlsId id) {
		this.id = id;
	}
	public String getHospStatus() {
		return hospStatus;
	}
	public void setHospStatus(String hospStatus) {
		this.hospStatus = hospStatus;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
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
	
	public EhfmEDCHospActionDtls() {
		super();
	}
	public String getNabhFlag() {
		return nabhFlag;
	}
	public void setNabhFlag(String nabhFlag) {
		this.nabhFlag = nabhFlag;
	}
	

}
