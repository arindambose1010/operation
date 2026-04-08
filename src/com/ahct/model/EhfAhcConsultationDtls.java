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
@Table(name = "ehf_ahc_consultation_dtls")
public class EhfAhcConsultationDtls implements Serializable {

	private String ahcId;
    private String consultationSpec;
    private String consultDoctor;
    private Date crtDt;
    private String crtUsr;
    private String remarks;
    private String consultFilePath;
    private String lstUpdUsr;
    private Date lstUpdDt;
    private long sno;
    
    
    
    
    @Id
    @Column(name="S_NO")
    public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}


    @Column(name="AHC_ID")
    public String getAhcId() {
		return ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
    return crtDt;
	}
	
	public void setCrtDt(Date crtDt) {
	    this.crtDt = crtDt;
	}
	
	@Column(name="consult_spec")
	public String getConsultationSpec() {
		return consultationSpec;
	}

	public void setConsultationSpec(String consultationSpec) {
		this.consultationSpec = consultationSpec;
	}
	@Column(name="consult_doc")
	public String getConsultDoctor() {
		return consultDoctor;
	}

	public void setConsultDoctor(String consultDoctor) {
		this.consultDoctor = consultDoctor;
	}
	@Column(name="consult_file_path")
	public String getConsultFilePath() {
		return consultFilePath;
	}

	public void setConsultFilePath(String consultFilePath) {
		this.consultFilePath = consultFilePath;
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

	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Column(name="REMARKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
