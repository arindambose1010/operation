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
@Table (name="EHFM_FOLLOWUP_PACKAGES")
public class EhfmFollowupPackages  implements Serializable{

	private EhfmFollowupPackagesId id;
    private Long packageAmt;
    private Long firstInstPkg;
    private Long subsequentInstPkg;
    private String followUpMstId;    
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String activeYN;
    private Date effStartDt;
    private Date effEndDt;
    private String icdCodeProcFP;
     
    
    @EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "surgeryId", column = @Column(name = "SURGERY_ID", nullable = false, length = 12)),
		@AttributeOverride(name = "schemeId", column = @Column(name = "SCHEME_ID", nullable = false, length = 20))
		})
    public EhfmFollowupPackagesId getId() {
	return id;
    }

   	public void setId(EhfmFollowupPackagesId id) {
   	this.id = id;
   	}
    
    
    
    /*@Column(name="surgery_id",nullable= false)
    public String getSurgeryId() {
		return surgeryId;
	}
	public void setSurgeryId(String surgeryId) {
		this.surgeryId = surgeryId;
	}*/
	 @Column(name="package_amt",nullable= false)
	public Long getPackageAmt() {
		return packageAmt;
	}
	public void setPackageAmt(Long packageAmt) {
		this.packageAmt = packageAmt;
	}
	@Column(name="first_inst_pkg",nullable= false)
	public Long getFirstInstPkg() {
		return firstInstPkg;
	}
	public void setFirstInstPkg(Long firstInstPkg) {
		this.firstInstPkg = firstInstPkg;
	}
	@Column(name="subsequent_inst_pkg",nullable= false)
	public Long getSubsequentInstPkg() {
		return subsequentInstPkg;
	}
	public void setSubsequentInstPkg(Long subsequentInstPkg) {
		this.subsequentInstPkg = subsequentInstPkg;
	}
	
	@Column(name="followup_mst_id",nullable= false)
	public String getFollowUpMstId() {
		return followUpMstId;
	}
	public void setFollowUpMstId(String followUpMstId) {
		this.followUpMstId = followUpMstId;
	}
	@Column(name="eff_strt_dt")
	public Date getEffStartDt() {
		return effStartDt;
	}
	public void setEffStartDt(Date effStartDt) {
		this.effStartDt = effStartDt;
	}
	@Column(name="eff_end_dt")
	public Date getEffEndDt() {
		return effEndDt;
	}
	public void setEffEndDt(Date effEndDt) {
		this.effEndDt = effEndDt;
	}
	@Column(name="active_yn")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="icd_code_proc_fp")
	public String getIcdCodeProcFP() {
		return icdCodeProcFP;
	}
	public void setIcdCodeProcFP(String icdCodeProcFP) {
		this.icdCodeProcFP = icdCodeProcFP;
	}
	
	
}
