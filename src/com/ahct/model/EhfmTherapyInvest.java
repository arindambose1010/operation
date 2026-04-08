package com.ahct.model;

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
@Table(name="EHFM_THERAPY_INVEST")
public class EhfmTherapyInvest implements java.io.Serializable{

	private EhfmTherapyInvestId id;
	//private String investigationId;
	private String investDesc;
	//private String ICDProcCode;
	private String activeYn;
	private String preOpPostOp;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
   
	
    
    @EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="investigationId", column=@Column(name="investigation_id", nullable=false) ), 
	   @AttributeOverride(name="ICDProcCode", column=@Column(name="ICD_PROC_CODE", nullable=false) ),
	   @AttributeOverride(name="asriSpec", column=@Column(name="asri_spec", nullable=false) ),
	   @AttributeOverride(name="schemeId", column=@Column(name="scheme_id", nullable=false) )
	   } )
	
	public EhfmTherapyInvestId getId() {
		return id;
	}
	public void setId(EhfmTherapyInvestId id) {
		this.id = id;
	}

	@Column(name="invest_desc",nullable=true)
	public String getInvestDesc() {
		return investDesc;
	}
	public void setInvestDesc(String investDesc) {
		this.investDesc = investDesc;
	}
	/*@Column(name="ICD_proc_code",nullable=false)
	 public String getICDProcCode() {
		return ICDProcCode;
	}
	public void setICDProcCode(String iCDProcCode) {
		ICDProcCode = iCDProcCode;
	}
	*/
	@Column(name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}
	
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="pre_or_post_op")
	public String getPreOpPostOp() {
		return preOpPostOp;
	}
	public void setPreOpPostOp(String preOpPostOp) {
		this.preOpPostOp = preOpPostOp;
	}
	
	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cr_dt", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
    
    
}
