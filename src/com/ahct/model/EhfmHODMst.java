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
@Table(name = "EHFM_HOD_MST")

public class EhfmHODMst implements Serializable{
    
     private Date crtDt;
     private String crtUsr;
     private Date lstUpdDt;
     private String lstUpdUser;
     private String hodCode;
     private String hodName;
     private String deptCode;
     private String activeYN;
     private String goNum;
     private String goDept;
     private String hodShortName;
     private String scheme;
     
    public EhfmHODMst() {
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Column(name="CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdUser(String lstUpdUser) {
        this.lstUpdUser = lstUpdUser;
    }
    @Column(name="LST_UPD_USR")
    public String getLstUpdUser() {
        return lstUpdUser;
    }

    public void setHodCode(String hodCode) {
        this.hodCode = hodCode;
    }
    @Id
    @Column(name="HOD_CODE", nullable = false)
    public String getHodCode() {
        return hodCode;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }
    @Column(name="HOD_NAME")
    public String getHodName() {
        return hodName;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    @Column(name="DEPT_CODE")
    public String getDeptCode() {
        return deptCode;
    }

    public void setActiveYN(String activeYN) {
        this.activeYN = activeYN;
    }
    @Column(name="ACTIVE_YN")
    public String getActiveYN() {
        return activeYN;
    }
    @Column(name="HOD_DISP_CODE")
   	public String getHodShortName() {
   		return hodShortName;
   	}

   	public void setHodShortName(String hodShortName) {
   		this.hodShortName = hodShortName;
   	}
   	 @Column(name="GO_NUMBER")
   	public String getGoNum() {
   		return goNum;
   	}

   	public void setGoNum(String goNum) {
   		this.goNum = goNum;
   	}
   	 @Column(name="GO_ADMIN_DEPT_CODE")
   	public String getGoDept() {
   		return goDept;
   	}

   	public void setGoDept(String goDept) {
   		this.goDept = goDept;
   	}
   	@Column(name="scheme")
   	public String getScheme() {
   		return scheme;
   	}

   	public void setScheme(String scheme) {
   		this.scheme = scheme;
   	}
}
