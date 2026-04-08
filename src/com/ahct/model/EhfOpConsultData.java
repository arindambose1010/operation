package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_OP_CONSULT_DATA database table.
 * 
 */
@Entity
@Table(name="EHF_OP_CONSULT_DATA")
public class EhfOpConsultData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sNo;
	private Date consultationTime;
	private Date crtDt;
	private String crtUser;
	private String diagnoisedBy;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String patientId;
	private String unitHodName;
	private String unitName;

    public EhfOpConsultData() {
    }


    @Id
    @Column(name="S_NO")
    public String getSNo() {
		return sNo;
	}




	public void setSNo(String sNo) {
		this.sNo = sNo;
	}




	@Temporal( TemporalType.DATE)
	@Column(name="CONSULTATION_TIME")
	public Date getConsultationTime() {
		return this.consultationTime;
	}

	public void setConsultationTime(Date consultationTime) {
		this.consultationTime = consultationTime;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USER")
	public String getCrtUser() {
		return this.crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}


	@Column(name="DIAGNOISED_BY")
	public String getDiagnoisedBy() {
		return this.diagnoisedBy;
	}

	public void setDiagnoisedBy(String diagnoisedBy) {
		this.diagnoisedBy = diagnoisedBy;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="PATIENT_ID")
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	@Column(name="UNIT_HOD_NAME")
	public String getUnitHodName() {
		return this.unitHodName;
	}

	public void setUnitHodName(String unitHodName) {
		this.unitHodName = unitHodName;
	}


	@Column(name="UNIT_NAME")
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}