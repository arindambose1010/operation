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
@Table(name = "EHF_AHC_HOSP_DIAGNOSIS")
public class EhfAhcHospDiagnosis implements Serializable {

	    private Date crtDt;
        private String crtUsr;
	    private String ahcId;
	    private String historyIllness;
	    private String pastHistory;
	    private String pastHistoryOthr;
	    private String pastHistoryCancers;
	    private String pastHistoryEndDis;
	    private String pastHistorySurg;
	    private String examntnFindings;
	    private String personalHistory;
	    private String familyHistory;
	    private String familyHistoryOthr;
	    private String diagnosedBy;
	 
	    private Date lstUpdDt;
	    private String lstUpdUsr;
	   
	    
	    public EhfAhcHospDiagnosis() {
			super();
			// TODO Auto-generated constructor stub
		}

		

		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name="CRT_DT", nullable = false)
	    public Date getCrtDt() {
	        return crtDt;
	    }

	    public void setCrtDt(Date crtDt) {
	        this.crtDt = crtDt;
	    }

	    @Column(name="CRT_USR", nullable = false)
	    public String getCrtUsr() {
	        return crtUsr;
	    }

	    public void setCrtUsr(String crtUsr) {
	        this.crtUsr = crtUsr;
	    }
	   

	    

	    @Column(name="EXAMNTN_FINDINGS")
	    public String getExamntnFindings() {
	        return examntnFindings;
	    }

	    public void setExamntnFindings(String examntnFindings) {
	        this.examntnFindings = examntnFindings;
	    }

	    @Column(name="HISTORY_ILLNESS")
	    public String getHistoryIllness() {
	        return historyIllness;
	    }

	    public void setHistoryIllness(String historyIllness) {
	        this.historyIllness = historyIllness;
	    }
	   
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="LST_UPD_DT")
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

	    @Column(name="PAST_HISTORY")
	    public String getPastHistory() {
	        return pastHistory;
	    }

	    public void setPastHistory(String pastHistory) {
	        this.pastHistory = pastHistory;
	    }

	   
	    @Id
	    @Column(name="AHC_ID", nullable = false)
	    public String getAhcId() {
	        return ahcId;
	    }

	    public void setAhcId(String ahcId) {
	        this.ahcId = ahcId;
	    }
	   
		@Column(name="PERSONAL_HISTORY")
		public String getPersonalHistory() {
			return personalHistory;
		}
		public void setPersonalHistory(String personalHistory) {
			this.personalHistory = personalHistory;
		}
		@Column(name="FAMILY_HISTORY")
		public String getFamilyHistory() {
			return familyHistory;
		}
		public void setFamilyHistory(String familyHistory) {
			this.familyHistory = familyHistory;
		}
		@Column(name="past_history_othr")
		public String getPastHistoryOthr() {
			return pastHistoryOthr;
		}
		public void setPastHistoryOthr(String pastHistoryOthr) {
			this.pastHistoryOthr = pastHistoryOthr;
		}
		@Column(name="past_history_cancers")
		public String getPastHistoryCancers() {
			return pastHistoryCancers;
		}
		public void setPastHistoryCancers(String pastHistoryCancers) {
			this.pastHistoryCancers = pastHistoryCancers;
		}
		@Column(name="past_history_enddis")
		public String getPastHistoryEndDis() {
			return pastHistoryEndDis;
		}
		public void setPastHistoryEndDis(String pastHistoryEndDis) {
			this.pastHistoryEndDis = pastHistoryEndDis;
		}
		@Column(name="past_history_surg")
		public String getPastHistorySurg() {
			return pastHistorySurg;
		}
		public void setPastHistorySurg(String pastHistorySurg) {
			this.pastHistorySurg = pastHistorySurg;
		}
		
		@Column(name="family_history_othr")
		public String getFamilyHistoryOthr() {
			return familyHistoryOthr;
		}
		public void setFamilyHistoryOthr(String familyHistoryOthr) {
			this.familyHistoryOthr = familyHistoryOthr;
		}


		@Column(name="diagnosed_by")
		public String getDiagnosedBy() {
			return diagnosedBy;
		}

		public void setDiagnosedBy(String diagnosedBy) {
			this.diagnosedBy = diagnosedBy;
		}
		

}
