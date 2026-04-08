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
@Table(name = "EHF_AHC_EXAM_FINDING")
public class EhfAhcExamFind implements Serializable {

	private String ahcId;
	private String bmi;
	private String pallor;
	private String cyanosis;
	private String clubOfFingrToes;
	private String lymphadenopathy;
	private String oedemaInFeet;
	private String malnutrition;
	private String dehydration;
	private String temperature;
	private String pluseRate;
	private String respirationRate;
	private String bpLt;
	private String bpRt;
	private String height;
	private String weight;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
	    
	    @Id
	    @Column(name="AHC_ID", nullable = false)
	    public String getAhcId() {
	        return ahcId;
	    }

	    public void setAhcId(String ahcId) {
	        this.ahcId = ahcId;
	    }

		@Column(name="BMI")
       public String getBmi() {
			return bmi;
		}

		public void setBmi(String bmi) {
			this.bmi = bmi;
		}
		@Column(name="PALLOR")
		public String getPallor() {
			return pallor;
		}

		public void setPallor(String pallor) {
			this.pallor = pallor;
		}
		@Column(name="Cyanosis")
		public String getCyanosis() {
			return cyanosis;
		}

		public void setCyanosis(String cyanosis) {
			this.cyanosis = cyanosis;
		}
		@Column(name="Club_Of_FingrToes")
		public String getClubOfFingrToes() {
			return clubOfFingrToes;
		}

		public void setClubOfFingrToes(String clubOfFingrToes) {
			this.clubOfFingrToes = clubOfFingrToes;
		}
		@Column(name="Lymphadenopathy")
		public String getLymphadenopathy() {
			return lymphadenopathy;
		}

		public void setLymphadenopathy(String lymphadenopathy) {
			this.lymphadenopathy = lymphadenopathy;
		}
		@Column(name="Oedema_In_Feet")
		public String getOedemaInFeet() {
			return oedemaInFeet;
		}

		public void setOedemaInFeet(String oedemaInFeet) {
			this.oedemaInFeet = oedemaInFeet;
		}
		@Column(name="Malnutrition")
		public String getMalnutrition() {
			return malnutrition;
		}

		public void setMalnutrition(String malnutrition) {
			this.malnutrition = malnutrition;
		}
		@Column(name="Dehydration")
		public String getDehydration() {
			return dehydration;
		}

		public void setDehydration(String dehydration) {
			this.dehydration = dehydration;
		}
		@Column(name="Temperature")
		public String getTemperature() {
			return temperature;
		}

		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}
		@Column(name="Pluse_Rate")
		public String getPluseRate() {
			return pluseRate;
		}

		public void setPluseRate(String pluseRate) {
			this.pluseRate = pluseRate;
		}
		@Column(name="Respiration_Rate")
		public String getRespirationRate() {
			return respirationRate;
		}

		public void setRespirationRate(String respirationRate) {
			this.respirationRate = respirationRate;
		}
		@Column(name="BP_Lt")
		public String getBpLt() {
			return bpLt;
		}

		public void setBpLt(String bpLt) {
			this.bpLt = bpLt;
		}
		@Column(name="BP_RT")
		public String getBpRt() {
			return bpRt;
		}

		public void setBpRt(String bpRt) {
			this.bpRt = bpRt;
		}

		@Column(name="Height")
		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}
		@Column(name="Weight")
		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
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

}
