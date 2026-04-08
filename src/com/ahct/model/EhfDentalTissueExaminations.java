package com.ahct.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table

(name = "ehf_dental_tissue_examinations" )
public class EhfDentalTissueExaminations implements Serializable
{

	private String patientId ;  
    private String caseId;               
	private String deciduousCaries ;           
	private String deciduousMissing ;          
	private String grosslyDecayed ;            
	private String mobile       ;               
	private String permanentCaries ;           
	private String rootGrosslyDecayed  ;      
	private String mobility       ;             
	private String attritionAbrasion ;         
	private String permanentMissing ;          
	private String permanentothers ;           
	               
	private Date crtDt;
    private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	
	
	public EhfDentalTissueExaminations()
	{
		super();
	}
	
	@Id
    @Column ( name = "PATIENT_ID", nullable = false )
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column ( name = "CASE_ID" )
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column ( name = "Deciduous_Caries" )
	public String getDeciduousCaries() {
		return deciduousCaries;
	}
	public void setDeciduousCaries(String deciduousCaries) {
		this.deciduousCaries = deciduousCaries;
	}
	@Column ( name = "Deciduous_Missing" )
	public String getDeciduousMissing() {
		return deciduousMissing;
	}
	public void setDeciduousMissing(String deciduousMissing) {
		this.deciduousMissing = deciduousMissing;
	}
	@Column ( name = "Grossly_Decayed" )
	public String getGrosslyDecayed() {
		return grosslyDecayed;
	}
	public void setGrosslyDecayed(String grosslyDecayed) {
		this.grosslyDecayed = grosslyDecayed;
	}
	@Column ( name = "Mobile" )
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column ( name = "Permanent_Caries" )
	public String getPermanentCaries() {
		return permanentCaries;
	}
	public void setPermanentCaries(String permanentCaries) {
		this.permanentCaries = permanentCaries;
	}
	@Column ( name = "Root_Grossly_Decayed" )
	public String getRootGrosslyDecayed() {
		return rootGrosslyDecayed;
	}
	public void setRootGrosslyDecayed(String rootGrosslyDecayed) {
		this.rootGrosslyDecayed = rootGrosslyDecayed;
	}
	@Column ( name = "Mobility" )
	public String getMobility() {
		return mobility;
	}
	public void setMobility(String mobility) {
		this.mobility = mobility;
	}
	@Column ( name = "Attrition_Abrasion" )
	public String getAttritionAbrasion() {
		return attritionAbrasion;
	}
	public void setAttritionAbrasion(String attritionAbrasion) {
		this.attritionAbrasion = attritionAbrasion;
	}
	@Column ( name = "Permanent_Missing" )
	public String getPermanentMissing() {
		return permanentMissing;
	}
	public void setPermanentMissing(String permanentMissing) {
		this.permanentMissing = permanentMissing;
	}
	@Column ( name = "Permanent_others" )
	public String getPermanentothers() {
		return permanentothers;
	}
	public void setPermanentothers(String permanentothers) {
		this.permanentothers = permanentothers;
	}
	
	@Column ( name = "CRT_DT" )
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column ( name = "CRT_USR" )
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Column ( name = "LST_UPD_DT" )
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column ( name = "LST_UPD_USR" )
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	
   
}
   