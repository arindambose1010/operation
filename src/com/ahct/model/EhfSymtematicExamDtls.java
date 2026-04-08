package com.ahct.model;

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
@Table(name="Ehf_Symtematic_Exam_Dtls")
public class EhfSymtematicExamDtls implements java.io.Serializable{

	private EhfSymtematicExamDtlsId id;
	private String subSymptomCode;
	private String mainSymptomCode;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String otherSymptomName;
    
    @EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="symptomCode", column=@Column(name="symptom_code", nullable=false) ), 
	   @AttributeOverride(name="caseId", column=@Column(name="case_id", nullable=false))
	   } )
	public EhfSymtematicExamDtlsId getId() {
		return id;
	}

	public void setId(EhfSymtematicExamDtlsId id) {
		this.id = id;
	}
    
    @Column(name="sub_symptom_code")
	public String getSubSymptomCode() {
		return subSymptomCode;
	}
	public void setSubSymptomCode(String subSymptomCode) {
		this.subSymptomCode = subSymptomCode;
	}
	@Column(name="main_symptom_code")
	public String getMainSymptomCode() {
		return mainSymptomCode;
	}
	public void setMainSymptomCode(String mainSymptomCode) {
		this.mainSymptomCode = mainSymptomCode;
	}
    @Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
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

	@Column(name="OTHER_SYMPTOM_NAME", nullable = true)
	public String getOtherSymptomName() {
		return otherSymptomName;
	}

	public void setOtherSymptomName(String otherSymptomName) {
		this.otherSymptomName = otherSymptomName;
	}
	
	
}
