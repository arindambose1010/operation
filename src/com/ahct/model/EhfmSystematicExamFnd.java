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
@Table(name="EHFM_SYSTEMATIC_EXAM_FND")
public class EhfmSystematicExamFnd   implements java.io.Serializable{

	private EhfmSystematicExamFndId id;
	private String symptomName;
	private String subSymptomName;
	private String mainSymptomName;
	private String activeYn;
	private String langId;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    
    public EhfmSystematicExamFnd() {
		super();
	}
    
	public EhfmSystematicExamFnd(EhfmSystematicExamFndId id,
			String symptomName, String subSymptomName, String mainSymptomName,
			String activeYn, String langId, Date crtDt, String crtUsr,
			Date lstUpdDt, String lstUpdUsr) {
		super();
		this.id = id;
		this.symptomName = symptomName;
		this.subSymptomName = subSymptomName;
		this.mainSymptomName = mainSymptomName;
		this.activeYn = activeYn;
		this.langId = langId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
	}
	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="symptomCode", column=@Column(name="symptom_code", nullable=false) ), 
	   @AttributeOverride(name="subSymptomCode", column=@Column(name="sub_symptom_code", nullable=false) ),
	   @AttributeOverride(name="mainSymptomCode", column=@Column(name="main_symptom_code", nullable=false) )
	   } )
	public EhfmSystematicExamFndId getId() {
		return id;
	}

	public void setId(EhfmSystematicExamFndId id) {
		this.id = id;
	}

	@Column(name="symptom_name")
	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	
	@Column(name="sub_symptom_name")
	public String getSubSymptomName() {
		return subSymptomName;
	}
	public void setSubSymptomName(String subSymptomName) {
		this.subSymptomName = subSymptomName;
	}
	
	@Column(name="main_symptom_name")
	public String getMainSymptomName() {
		return mainSymptomName;
	}
	public void setMainSymptomName(String mainSymptomName) {
		this.mainSymptomName = mainSymptomName;
	}
	@Column(name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="lang_id")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
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
}
