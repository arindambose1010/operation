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
@Table(name = "EHF_FOLLOWUP_TESTS")
public class EhfFollowupTests  implements Serializable {

	private String attachTotalPath;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String followupId;
    private String testId;
    private Long sno;
    private String clinicalId;
    
    
    @Id
    @Column(name="SNO", nullable = false)
    public Long getSno() {
            return sno;
        }
    public void setSno(Long sno) {
        this.sno = sno;
    }
    
    @Column(name="ATTACH_PATH")
    public String getAttachTotalPath() {
        return attachTotalPath;
    }

    public void setAttachTotalPath(String attachTotalPath) {
        this.attachTotalPath = attachTotalPath;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable=false, length=7)
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
    @Column(name="TEST_ID")
    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }    
    @Column(name="case_followup_id")
	public String getFollowupId() {
		return followupId;
	}
	public void setFollowupId(String followupId) {
		this.followupId = followupId;
	}

	@Column(name="clinical_id")
	public String getClinicalId() {
		return clinicalId;
	}
	public void setClinicalId(String clinicalId) {
		this.clinicalId = clinicalId;
	}
	public EhfFollowupTests(String attachTotalPath, Date crtDt, String crtUsr,
			Date lstUpdDt, String lstUpdUsr, String followupId, String testId,
			Long sno, String clinicalId) {
		super();
		this.attachTotalPath = attachTotalPath;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
		this.followupId = followupId;
		this.testId = testId;
		this.sno = sno;
		this.clinicalId = clinicalId;
	}
	public EhfFollowupTests() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attachTotalPath == null) ? 0 : attachTotalPath.hashCode());
		result = prime * result
				+ ((clinicalId == null) ? 0 : clinicalId.hashCode());
		result = prime * result + ((crtDt == null) ? 0 : crtDt.hashCode());
		result = prime * result + ((crtUsr == null) ? 0 : crtUsr.hashCode());
		result = prime * result
				+ ((followupId == null) ? 0 : followupId.hashCode());
		result = prime * result
				+ ((lstUpdDt == null) ? 0 : lstUpdDt.hashCode());
		result = prime * result
				+ ((lstUpdUsr == null) ? 0 : lstUpdUsr.hashCode());
		result = prime * result + ((sno == null) ? 0 : sno.hashCode());
		result = prime * result + ((testId == null) ? 0 : testId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfFollowupTests other = (EhfFollowupTests) obj;
		if (attachTotalPath == null) {
			if (other.attachTotalPath != null)
				return false;
		} else if (!attachTotalPath.equals(other.attachTotalPath))
			return false;
		if (clinicalId == null) {
			if (other.clinicalId != null)
				return false;
		} else if (!clinicalId.equals(other.clinicalId))
			return false;
		if (crtDt == null) {
			if (other.crtDt != null)
				return false;
		} else if (!crtDt.equals(other.crtDt))
			return false;
		if (crtUsr == null) {
			if (other.crtUsr != null)
				return false;
		} else if (!crtUsr.equals(other.crtUsr))
			return false;
		if (followupId == null) {
			if (other.followupId != null)
				return false;
		} else if (!followupId.equals(other.followupId))
			return false;
		if (lstUpdDt == null) {
			if (other.lstUpdDt != null)
				return false;
		} else if (!lstUpdDt.equals(other.lstUpdDt))
			return false;
		if (lstUpdUsr == null) {
			if (other.lstUpdUsr != null)
				return false;
		} else if (!lstUpdUsr.equals(other.lstUpdUsr))
			return false;
		if (sno == null) {
			if (other.sno != null)
				return false;
		} else if (!sno.equals(other.sno))
			return false;
		if (testId == null) {
			if (other.testId != null)
				return false;
		} else if (!testId.equals(other.testId))
			return false;
		return true;
	}     
}
