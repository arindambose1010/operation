package com.ahct.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

 @Entity
 @Table
( name = "EHF_ENROLL_AUDIT" )
public class EhfEnrollAudit  implements Serializable
{
    private EhfEnrollAuditId id;
    private String enrollParentId;
    private String enrollStatus;
    private String enrollActnBy;
    private Date enrollActnDate;
    private String enrollRemarks;
    private String langId;
    private Date crtDt;
    private String crtUsr;
    private Date lstupdDt;
    private String lstUpdUsr;
   // private EhfmUsers ehfmUsers;
  
  public EhfEnrollAudit ()
  {
  }
  
  public void setId ( EhfEnrollAuditId id )
  {
    this.id = id;
  }
  @EmbeddedId
  @AttributeOverrides ( { @AttributeOverride ( name = "enrollId", column = @Column ( name = "ENROLL_ID", nullable = false, length = 10 )
      )
      , @AttributeOverride ( name = "enrollOrder", column = @Column ( name = "ENROLL_ORDER", nullable = false,length = 4)
      )
     
      } )
  public EhfEnrollAuditId getId ()
  {
    return id ;
  }


    public void setEnrollParentId(String enrollParentId) {
        this.enrollParentId = enrollParentId;
    }
    @Column(name="ENROLL_PARNT_ID")
    public String getEnrollParentId() {
        return enrollParentId;
    }

    public void setEnrollStatus(String enrollStatus) {
        this.enrollStatus = enrollStatus;
    }
    @Column(name="ENROLL_STATUS")
    public String getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollActnBy(String enrollActnBy) {
        this.enrollActnBy = enrollActnBy;
    }
    @Column(name="ENROLL_ACTN_BY")
    public String getEnrollActnBy() {
        return enrollActnBy;
    }

    public void setEnrollActnDate(Date enrollActnDate) {
        this.enrollActnDate = enrollActnDate;
    }
    @Column(name="ENROLL_ACTN_DT")
    public Date getEnrollActnDate() {
        return enrollActnDate;
    }

    public void setEnrollRemarks(String enrollRemarks) {
        this.enrollRemarks = enrollRemarks;
    }
    @Column(name="ENROLL_REMARKS")
    public String getEnrollRemarks() {
        return enrollRemarks;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }
    @Column(name="LANG_ID")
    public String getLangId() {
        return langId;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column(name="CRT_DT", length=7)
    @Temporal ( TemporalType.TIMESTAMP )
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Column(name="CRT_USR", length=7)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setLstupdDt(Date lstupdDt) {
        this.lstupdDt = lstupdDt;
    }
    @Column(name="LST_UPD_DT", length=7)
    @Temporal ( TemporalType.TIMESTAMP )
    public Date getLstupdDt() {
        return lstupdDt;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name="LST_UPD_USR", length=7)
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    /*@ManyToOne
    @JoinColumn(name="ENROLL_ACTN_BY", referencedColumnName="user_id",insertable=false,updatable=false)
	public EhfmUsers getEhfmUsers() {
		return ehfmUsers;
	}

	public void setEhfmUsers(EhfmUsers ehfmUsers) {
		this.ehfmUsers = ehfmUsers;
	}*/
    
    
}
