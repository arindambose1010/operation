package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/*@NamedNativeQueries ({ 
@NamedNativeQuery ( 
name = "findRamcoDtlsForHosp", resultClass = AsritRamcoAamcoMaster.class,
query = "select * from ASRIT_RAMCO_AAMCO_MASTER where ASRIT_RAMCO_AAMCO_MASTER.cord_type=:cordType and hosp_id=:hospId " )
})
*/

@SuppressWarnings("serial")
@Entity
@Table
( name = "EHFM_MEDCO_MASTER" )
public class EhfmMedcoMaster implements Serializable
{
  public EhfmMedcoMaster  ()
  {
  }
  private String pkId;
  private String hospId;
  private String cordName;
  private String qualification;
  private String regNo;
  private String experience;
  private String university;
  private String cugNo;
  private String mobileNo;
  private String emailId;
  private String cordType;
  private String status;
  private String crNo;
  private String loginId;
  private String diGiSignDtls;
  private String crtUsr;
  private String lstUpdUsr;
  private String typeOfRamco;
  private String shiftRmcFromHh;
  private String shiftRmcFromMm;
  private String shiftRmcToHh;
  private String shiftRmcToMm;
  private Date crtDt;
  private Date lstUpdDt;
  private String scheme;


    public void setPkId(String pkId) {
        this.pkId = pkId;
    }
    @Id
    @Column ( name = "PK_ID",  nullable = false )
    public String getPkId() {
        return pkId;
    }
  public void setHospId ( String hospId )
  {
    this.hospId = hospId;
  }
  @Column ( name = "HOSP_ID", length = 10 , nullable = false )
  public String getHospId ()
  {
    return hospId ;
  }
  public void setCordName ( String cordName )
  {
    this.cordName = cordName;
  }
  @Column ( name = "CORD_NAME", length = 400 , nullable = true )
  public String getCordName ()
  {
    return cordName ;
  }
  public void setQualification ( String qualification )
  {
    this.qualification = qualification;
  }
  @Column ( name = "QUALIFICATION", length = 500 , nullable = true )
  public String getQualification ()
  {
    return qualification ;
  }
  public void setRegNo ( String regNo )
  {
    this.regNo = regNo;
  }
  @Column ( name = "REGN_NO", length = 500 , nullable = true )
  public String getRegNo ()
  {
    return regNo ;
  }
  public void setExperience ( String experience )
  {
    this.experience = experience;
  }
  @Column ( name = "EXPERIENCE", length = 500 , nullable = true )
  public String getExperience ()
  {
    return experience ;
  }
  public void setUniversity ( String university )
  {
    this.university = university;
  }
  @Column ( name = "UNIVERSITY", length = 500 , nullable = true )
  public String getUniversity ()
  {
    return university ;
  }
  public void setCugNo ( String cugNo )
  {
    this.cugNo = cugNo;
  }
  @Column ( name = "CUG_NO", length = 50 , nullable = true )
  public String getCugNo ()
  {
    return cugNo ;
  }
  public void setMobileNo ( String mobileNo )
  {
    this.mobileNo = mobileNo;
  }
  @Column ( name = "MOBILE_NO", length = 20 , nullable = true )
  public String getMobileNo ()
  {
    return mobileNo ;
  }
  public void setEmailId ( String emailId )
  {
    this.emailId = emailId;
  }
  @Column ( name = "EMAIL_ID", length = 200 , nullable = true )
  public String getEmailId ()
  {
    return emailId ;
  }
  public void setCordType ( String cordType )
  {
    this.cordType = cordType;
  }
  @Column ( name = "CORD_TYPE", length = 6 , nullable = true )
  public String getCordType ()
  {
    return cordType ;
  }
  public void setStatus ( String status )
  {
    this.status = status;
  }
  @Column ( name = "STATUS", length = 10 , nullable = false )
  public String getStatus ()
  {
    return status ;
  }
  public void setCrNo ( String crNo )
  {
    this.crNo = crNo;
  }
  
  @Column ( name = "CRNO", length = 50 , nullable = true )
  public String getCrNo ()
  {
    return crNo ;
  }
  public void setLoginId ( String loginId )
  {
    this.loginId = loginId;
  }
  @Column ( name = "LOGIN_ID", length = 200 , nullable = true )
  public String getLoginId ()
  {
    return loginId ;
  }
  public void setDiGiSignDtls ( String diGiSignDtls )
  {
    this.diGiSignDtls = diGiSignDtls;
  }
  @Column ( name = "DIGISIGN_DTLS", length = 200 , nullable = true )
  public String getDiGiSignDtls ()
  {
    return diGiSignDtls ;
  }
  public void setCrtUsr ( String crtUsr )
  {
    this.crtUsr = crtUsr;
  }
  @Column ( name = "CRT_USER", length = 12 , nullable = true )
  public String getCrtUsr ()
  {
    return crtUsr ;
  }
  public void setLstUpdUsr ( String lstUpdUsr )
  {
    this.lstUpdUsr = lstUpdUsr;
  }
  @Column ( name = "LST_UPD_USR", length = 12 , nullable = true )
  public String getLstUpdUsr ()
  {
    return lstUpdUsr ;
  }
  public void setTypeOfRamco ( String typeOfRamco )
  {
    this.typeOfRamco = typeOfRamco;
  }
  @Column ( name = "TYPE_OF_RAMCO", length = 10 , nullable = true )
  public String getTypeOfRamco ()
  {
    return typeOfRamco ;
  }
  public void setShiftRmcFromHh ( String shiftRmcFromHh )
  {
    this.shiftRmcFromHh = shiftRmcFromHh;
  }
  @Column ( name = "SHIFT_RMC_FRM_HH", length = 3 , nullable = true )
  public String getShiftRmcFromHh ()
  {
    return shiftRmcFromHh ;
  }
  public void setShiftRmcFromMm ( String shiftRmcFromMm )
  {
    this.shiftRmcFromMm = shiftRmcFromMm;
  }
  @Column ( name = "SHIFT_RMC_FRM_MM", length = 3 , nullable = true )
  public String getShiftRmcFromMm ()
  {
    return shiftRmcFromMm ;
  }
  public void setShiftRmcToHh ( String shiftRmcToHh )
  {
    this.shiftRmcToHh = shiftRmcToHh;
  }
  @Column ( name = "SHIFT_RMC_TO_HH", length = 3 , nullable = true )
  public String getShiftRmcToHh ()
  {
    return shiftRmcToHh ;
  }
  public void setShiftRmcToMm ( String shiftRmcToMm )
  {
    this.shiftRmcToMm = shiftRmcToMm;
  }
  @Column ( name = "SHIFT_RMC_TO_MM", length = 3 , nullable = true )
  public String getShiftRmcToMm ()
  {
    return shiftRmcToMm ;
  }
  public void setCrtDt ( Date crtDt )
  {
    this.crtDt = crtDt;
  }
  @Temporal(TemporalType.TIMESTAMP)
  @Column ( name = "CRT_DT",  nullable = true )
  public Date getCrtDt ()
  {
    return crtDt ;
  }
  public void setLstUpdDt ( Date lstUpdDt )
  {
    this.lstUpdDt = lstUpdDt;
  }
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column ( name = "LST_UPD_DT",  nullable = true )
  public Date getLstUpdDt ()
  {
    return lstUpdDt ;
  }
  @Column(name="SCHEME")
public String getScheme() {
	return scheme;
}
public void setScheme(String scheme) {
	this.scheme = scheme;
}
  
  

   
}
