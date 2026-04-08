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

(name = "ehf_dental_oral_examinations" )
public class EhfDentalOralExaminations implements Serializable
{

	private String patientId ;  
    private String caseId;
    private String extraOralCheck;
    private String intraOralCheck;
    private String face ;           
    private String tmj ;            
    private String  jaws ;   
    private String jawsSub;
    private String lymphadenopathy ;            
    private String lymphadenopathySub ;
    private String gingiva ;  
    private String buccalMucosa;             
    private String frenalAttachment ;              
    private String tongue;              
    private String floorMouth;                         
    private String softPalate;      
    private String hardPalate;         
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    
  //Added for Swelling & Pus/Discharge details
    private String swSite;
    private String swSize;
    private String swExtension;
    private String swColour;
    private String swConsistency;
    private String swTenderness;
    private String swBorders;
    private String psSite;
    private String psDischarge;
    
    
    public EhfDentalOralExaminations()
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
	@Column ( name = "Face" )
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	@Column ( name = "Tmj" )
	public String getTmj() {
		return tmj;
	}
	public void setTmj(String tmj) {
		this.tmj = tmj;
	}
	@Column ( name = "Jaws" )
	public String getJaws() {
		return jaws;
	}
	public void setJaws(String jaws) {
		this.jaws = jaws;
	}
	@Column ( name = "Lymphadenopathy" )
	public String getLymphadenopathy() {
		return lymphadenopathy;
	}
	public void setLymphadenopathy(String lymphadenopathy) {
		this.lymphadenopathy = lymphadenopathy;
	}
	@Column ( name = "Gingiva" )
	public String getGingiva() {
		return gingiva;
	}
	public void setGingiva(String gingiva) {
		this.gingiva = gingiva;
	}
	@Column ( name = "Buccal_Mucosa" )
	public String getBuccalMucosa() {
		return buccalMucosa;
	}
	public void setBuccalMucosa(String buccalMucosa) {
		this.buccalMucosa = buccalMucosa;
	}
	@Column ( name = "Frenal_Attachment" )
	public String getFrenalAttachment() {
		return frenalAttachment;
	}
	public void setFrenalAttachment(String frenalAttachment) {
		this.frenalAttachment = frenalAttachment;
	}
	@Column ( name = "Tongue" )
	public String getTongue() {
		return tongue;
	}
	public void setTongue(String tongue) {
		this.tongue = tongue;
	}
	@Column ( name = "Floor_Mouth" )
	public String getFloorMouth() {
		return floorMouth;
	}
	public void setFloorMouth(String floorMouth) {
		this.floorMouth = floorMouth;
	}
	@Column ( name = "Soft_Palate" )
	public String getSoftPalate() {
		return softPalate;
	}
	public void setSoftPalate(String softPalate) {
		this.softPalate = softPalate;
	}
	@Column ( name = "Hard_Palate" )
	public String getHardPalate() {
		return hardPalate;
	}
	public void setHardPalate(String hardPalate) {
		this.hardPalate = hardPalate;
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
	@Column ( name = "extra_oral_check" )
	public String getExtraOralCheck() {
		return extraOralCheck;
	}

	public void setExtraOralCheck(String extraOralCheck) {
		this.extraOralCheck = extraOralCheck;
	}

	@Column ( name = "intra_oral_check" )
	public String getIntraOralCheck() {
		return intraOralCheck;
	}

	public void setIntraOralCheck(String intraOralCheck) {
		this.intraOralCheck = intraOralCheck;
	}

	@Column ( name = "JAWS_SUB" )
	public String getJawsSub() {
		return jawsSub;
	}

	public void setJawsSub(String jawsSub) {
		this.jawsSub = jawsSub;
	}

	@Column ( name = "LYMPHADENOPATHY_SUB" )
	public String getLymphadenopathySub() {
		return lymphadenopathySub;
	}

	public void setLymphadenopathySub(String lymphadenopathySub) {
		this.lymphadenopathySub = lymphadenopathySub;
	}

	@Column ( name = "LST_UPD_USR" )
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Column ( name = "SWELLING_SITE" )
	public String getSwSite() {
		return swSite;
	}
	public void setSwSite(String swSite) {
		this.swSite = swSite;
	}
	
	@Column ( name = "SWELLING_SIZE" )
	public String getSwSize() {
		return swSize;
	}
	public void setSwSize(String swSize) {
		this.swSize = swSize;
	}
	
	@Column ( name = "SWELLING_EXTENSION" )
	public String getSwExtension() {
		return swExtension;
	}
	public void setSwExtension(String swExtension) {
		this.swExtension = swExtension;
	}
	
	@Column ( name = "SWELLING_COLOUR" )
	public String getSwColour() {
		return swColour;
	}
	public void setSwColour(String swColour) {
		this.swColour = swColour;
	}
	
	@Column ( name = "SWELLING_CONSISTENCY" )
	public String getSwConsistency() {
		return swConsistency;
	}
	public void setSwConsistency(String swConsistency) {
		this.swConsistency = swConsistency;
	}
	
	@Column ( name = "SWELLING_TENDERNESS" )
	public String getSwTenderness() {
		return swTenderness;
	}
	public void setSwTenderness(String swTenderness) {
		this.swTenderness = swTenderness;
	}
	
	@Column ( name = "SWELLING_BORDERS" )
	public String getSwBorders() {
		return swBorders;
	}
	public void setSwBorders(String swBorders) {
		this.swBorders = swBorders;
	}
	
	@Column ( name = "PUS_DIS_SITE" )
	public String getPsSite() {
		return psSite;
	}
	public void setPsSite(String psSite) {
		this.psSite = psSite;
	}
	
	@Column ( name = "PUS_DIS_DSCHARGE" )
	public String getPsDischarge() {
		return psDischarge;
	}
	public void setPsDischarge(String psDischarge) {
		this.psDischarge = psDischarge;
	}
	
}
