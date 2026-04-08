package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_PATIENT_BIO_REG database table.
 * 
 */
@Entity
@Table(name="EHF_PATIENT_BIO_REG")
public class EhfPatientBioReg implements Serializable {
	private static final long serialVersionUID = 1L;
	private String patientId;
	private String cardNo;
	private String cardType;
	private Date crtDt;
	private String crtUsr;
	private String employeeNo;
	private String fingerCaptured;
	private String fingerPrint;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String name;
	private String patientScheme;
	private String regHospId;
	private String schemeId;
	private String handCaptured;
	
    public EhfPatientBioReg() {
    }


	@Id
	@Column(name="PATIENT_ID")
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	@Column(name="CARD_NO")
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	@Column(name="CARD_TYPE")
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	@Column(name="EMPLOYEE_NO")
	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}


	@Column(name="FINGER_CAPTURED")
	public String getFingerCaptured() {
		return this.fingerCaptured;
	}

	public void setFingerCaptured(String fingerCaptured) {
		this.fingerCaptured = fingerCaptured;
	}


	@Column(name="FINGER_PRINT")
	public String getFingerPrint() {
		return this.fingerPrint;
	}

	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
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


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="PATIENT_SCHEME")
	public String getPatientScheme() {
		return this.patientScheme;
	}

	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}


	@Column(name="REG_HOSP_ID")
	public String getRegHospId() {
		return this.regHospId;
	}

	public void setRegHospId(String regHospId) {
		this.regHospId = regHospId;
	}


	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name="HAND_CAPTURED")
	public String getHandCaptured() {
		return handCaptured;
	}


	public void setHandCaptured(String handCaptured) {
		this.handCaptured = handCaptured;
	}
	
	
	

}