package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
(name = "EHF_CASE_CLINICAL_NOTES")
public class EhfCaseClinicalNotes implements java.io.Serializable{
	 private String caseId;
	    private String clinicalId;
	    private String prePostOperative;
	    private String pulse;
	    private String bpSystolic;
	    private String temperature;
	    private String respiratory;
	    private String heartRate;
	    private String lungsCondition;  
	    private Date crtDt;
	    private String crtUsr;
	    private Date investigationDate;
	    private Date lstUpdDt;
	    private String lstUpdUsr;
	    private String therapyDtls;
	    private String woundStatus;
	    private String woundDtls;
	    private String remarks; 
	    private String wardType;
	    private String fluidInput;
	    private String fluidOutput; 
	    private String wardInTime;
	    private String wardOutTime;
	    private String docName;
     	private String plasmaBbf;
     	private String plasmaBl;
     	private String plasmaBd;
     	private String plasmaMn;
     	private String insulinBbf;
     	private String insulinSr;
     	private String insulinBd;
     	private String insulinMn;
	    
     	
     	@Column(name = "plasma_bbf")
	    public String getPlasmaBbf() {
			return plasmaBbf;
		}

		public void setPlasmaBbf(String plasmaBbf) {
			this.plasmaBbf = plasmaBbf;
		}
		@Column(name = "plasma_bl")
		public String getPlasmaBl() {
			return plasmaBl;
		}

		public void setPlasmaBl(String plasmaBl) {
			this.plasmaBl = plasmaBl;
		}
		@Column(name = "plasma_bd")
		public String getPlasmaBd() {
			return plasmaBd;
		}

		public void setPlasmaBd(String plasmaBd) {
			this.plasmaBd = plasmaBd;
		}
		@Column(name = "plasma_mn")
		public String getPlasmaMn() {
			return plasmaMn;
		}

		public void setPlasmaMn(String plasmaMn) {
			this.plasmaMn = plasmaMn;
		}
		@Column(name = "insulin_bbf")
		public String getInsulinBbf() {
			return insulinBbf;
		}

		public void setInsulinBbf(String insulinBbf) {
			this.insulinBbf = insulinBbf;
		}
		@Column(name = "insulin_sr")
		public String getInsulinSr() {
			return insulinSr;
		}

		public void setInsulinSr(String insulinSr) {
			this.insulinSr = insulinSr;
		}
		@Column(name = "insulin_bd")
		public String getInsulinBd() {
			return insulinBd;
		}

		public void setInsulinBd(String insulinBd) {
			this.insulinBd = insulinBd;
		}
		@Column(name = "insulin_mn")
		public String getInsulinMn() {
			return insulinMn;
		}

		public void setInsulinMn(String insulinMn) {
			this.insulinMn = insulinMn;
		}

		@Column(name = "doctorName")
	    public String getDocName() {
			return docName;
		}

		public void setDocName(String docName) {
			this.docName = docName;
		}
		

		public EhfCaseClinicalNotes()
	    {
	    	
	    }

	    @Id
	    @Column(name = "CLINICAL_ID", unique = true, nullable = false, length = 20)
	    public String getClinicalId() {
	        return clinicalId;
	    }

	    public void setClinicalId(String clinicalId) {
	        this.clinicalId = clinicalId;
	    }
	    @Column(name = "CASE_ID", nullable = false)
	    public String getCaseId() {
	        return caseId;
	    }

	    public void setCaseId(String caseId) {
	        this.caseId = caseId;
	    }

	   
	    @Column(name = "PRE_POST_OPERATIVE")
	    public String getPrePostOperative() {
	        return prePostOperative;
	    }
	    public void setPrePostOperative(String prePostOperative) {
	        this.prePostOperative = prePostOperative;
	    }
	    @Column(name = "PULSE")
	    public String getPulse() {
	        return pulse;
	    }

	    public void setPulse(String pulse) {
	        this.pulse = pulse;
	    }
	    @Column(name = "BP_SYSTOLIC")
	    public String getBpSystolic() {
	        return bpSystolic;
	    }

	    public void setBpSystolic(String bpSystolic) {
	        this.bpSystolic = bpSystolic;
	    }
	    @Column(name = "TEMPERATURE")
	    public String getTemperature() {
	        return temperature;
	    }


	    public void setTemperature(String temperature) {
	        this.temperature = temperature;
	    }
	    @Column(name = "RESPIRATORY")
	    public String getRespiratory() {
	        return respiratory;
	    }
	    public void setRespiratory(String respiratory) {
	        this.respiratory = respiratory;
	    }


	    @Column(name = "HEART_RATE")
	    public String getHeartRate() {
	        return heartRate;
	    }
	    public void setHeartRate(String heartRate) {
	        this.heartRate = heartRate;
	    }

	    @Column(name = "LUNGS_CONDITION")
	    public String getLungsCondition() {
	        return lungsCondition;
	    }

	    public void setLungsCondition(String lungsCondition) {
	        this.lungsCondition = lungsCondition;
	    } 
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "CRT_DT", nullable = false)
	    public Date getCrtDt() {
	        return crtDt;
	    }

	  
	    public void setCrtDt(Date crtDt) {
	        this.crtDt = crtDt;
	    }
	    @Column(name = "CRT_USR", nullable = false)
	    public String getCrtUsr() {
	        return crtUsr;
	    }
	    public void setCrtUsr(String crtUsr) {
	        this.crtUsr = crtUsr;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "CLINIC_INVEST_DONE_DT")
	    public Date getInvestigationDate() {
	        return investigationDate;
	    }
	    public void setInvestigationDate(Date investigationDate) {
	        this.investigationDate = investigationDate;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "LST_UPD_DT")
	    public Date getLstUpdDt() {
	        return lstUpdDt;
	    }
	    public void setLstUpdDt(Date lstUpdDt) {
	        this.lstUpdDt = lstUpdDt;
	    }
	    @Column(name = "LST_UPD_USR")
	    public String getLstUpdUsr() {
	        return lstUpdUsr;
	    }
	    public void setLstUpdUsr(String lstUpdUsr) {
	        this.lstUpdUsr = lstUpdUsr;
	    }
	    @Column(name = "THERAPY_DTLS")
	    public String getTherapyDtls() {
	        return therapyDtls;
	    }
	    public void setTherapyDtls(String therapyDtls) {
	        this.therapyDtls = therapyDtls;
	    }
	    @Column(name = "WOUND_STATUS")
	    public String getWoundStatus() {
	        return woundStatus;
	    }

	    public void setWoundStatus(String woundStatus) {
	        this.woundStatus = woundStatus;
	    }
	   
	    @Column(name = "WOUND_DTLS",length=750)
	    public String getWoundDtls() {
	        return woundDtls;
	    }
	    public void setWoundDtls(String woundDtls) {
	        this.woundDtls = woundDtls;
	    }

	   
	    @Column(name = "REMARKS")
	    public String getRemarks() {
	        return remarks;
	    }
	    public void setRemarks(String remarks) {
	        this.remarks = remarks;
	    }

	    public void setWardType(String wardType) {
	        this.wardType = wardType;
	    }
	    @Column(name="WARD_TYPE")
	    public String getWardType() {
	        return wardType;
	    }
	    @Column(name = "FLUID_INPUT")
	    public String getFluidInput() {
	        return fluidInput;
	    }
	    public void setFluidInput(String fluidInput) {
	        this.fluidInput = fluidInput;
	    }
	    
	    @Column(name = "FLUID_OUTPUT")
	    public String getFluidOutput() {
	        return fluidOutput;
	    }
	    public void setFluidOutput(String fluidOutput) {
	        this.fluidOutput = fluidOutput;
	    }
	    
	    @Column(name = "ward_in_time")
		public String getWardInTime() {
			return wardInTime;
		}

		public void setWardInTime(String wardInTime) {
			this.wardInTime = wardInTime;
		}
		
		@Column(name = "ward_out_time")
		public String getWardOutTime() {
			return wardOutTime;
		}

		public void setWardOutTime(String wardOutTime) {
			this.wardOutTime = wardOutTime;
		}
}
