package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * SgvcEmpMst entity. @author MyEclipse Persistence Tools
 */
@NamedNativeQueries ( { @NamedNativeQuery ( name = "findEmployeesByDept", resultClass = SgvcEmpMst.class, query = "select  em.* from sgvc_work_allocation_mst w,sgvc_emp_mst em, sgvo_unit_mst um where w.emp_code=em.login_name and em.emp_id<>'EE0' and w.emp_dept_id=:deptId and um.unit_name= w.emp_asri_role and em.service_flg='Y' and um.dept_id= w.emp_dept_id " )
    , @NamedNativeQuery ( name = "SgvcEmpMst.getBirthdayList", resultClass = SgvcEmpMst.class, query = "select emp.* from sgvc_emp_mst emp, sgvo_unitemp_mpg uem, sgvo_unit_mst u1 where u1.unit_id = uem.unit_id  and uem.emp_id = emp.emp_id and u1.dept_id =:deptId and   to_char(sysdate, 'DD,MM') = to_char(emp.dob, 'DD,MM')" )
    , @NamedNativeQuery ( name = "SgvcEmpMst.getNewJoineesList", resultClass = SgvcEmpMst.class, query = "select emp.* from sgvc_emp_mst emp, sgvo_unitemp_mpg uem, sgvo_unit_mst u1 where u1.unit_id = uem.unit_id and uem.emp_id = emp.emp_id and u1.dept_id=:deptId and  sysdate between emp.doj-7 and emp.doj+ 7 " )
    , @NamedNativeQuery ( name = "SgvcEmpMst.getReportingEmpList", resultClass = SgvcEmpMst.class, query = "select distinct e.* from SGVC_EMP_MST e,SGVO_UNITEMP_MPG ue1,SGVO_UNITEMP_MPG ue2,SGVO_UNIT_MST u,sgvo_dept_mst dept where " + 
                                 "ue1.emp_id=e.emp_id and ue1.unit_id=u.unit_id and ue2.unit_id=u.parent_unit_id and  dept.DEPT_ID = u.DEPT_ID and e.SERVICE_FLG ='Y' and ue2.emp_id=:empId and u.dept_id=:deptId " )
    , @NamedNativeQuery ( name = "SgvcEmpMst.getBellowLevelReportingEmpList", resultClass = SgvcEmpMst.class, query = "select distinct c.* from sgvc_emp_mst c,sgvo_unitemp_mpg a,sgvo_unit_mst b   " + 
                                 "where a.unit_id=b.unit_id and  c.emp_id=a.emp_id and a.PRIMARY_FLAG =:primaryFlag and b.dept_id=:deptId and b.LEVEL_ID <:levelId and c.EMP_ID <> 'EE0' and c.SERVICE_FLG ='Y' " ) 
    , @NamedNativeQuery ( name = "SgvcEmpMst.getEmpNameByUnit", resultClass = SgvcEmpMst.class, query = "select e.* from sgvo_unitemp_mpg ue, sgvc_emp_mst e where ue.unit_id=:unitId and ue.emp_id=e.emp_id" )
    , @NamedNativeQuery ( name = "findEmployeeByDeptAndEmpNo", resultClass = SgvcEmpMst.class, query = "select distinct c.* from sgvc_emp_mst c,sgvo_unitemp_mpg a,sgvo_unit_mst b where a.unit_id=b.unit_id and  c.emp_id=a.emp_id and b.dept_id=:deptId and c.emp_id <>'EE0' and c.service_flg='Y' and c.login_name like :empNo and c.dsgn_id=:dsgnId" )
    , @NamedNativeQuery ( name = "findEmployeeByDeptAndEmpName", resultClass = SgvcEmpMst.class, query = "select distinct c.* from sgvc_emp_mst c,sgvo_unitemp_mpg a,sgvo_unit_mst b where a.unit_id=b.unit_id and  c.emp_id=a.emp_id and b.dept_id=:deptId and c.emp_id <>'EE0' and c.first_name||c.last_name like :empName and c.service_flg='Y' and c.dsgn_id=:dsgnId")
    , @NamedNativeQuery ( name = "findEmployeeByDeptEmpNoEmpName", resultClass = SgvcEmpMst.class, query = "select distinct c.* from sgvc_emp_mst c,sgvo_unitemp_mpg a,sgvo_unit_mst b where a.unit_id=b.unit_id and  c.emp_id=a.emp_id and b.dept_id=:deptId and c.emp_id <>'EE0' and c.login_name like :empNo and c.first_name||c.last_name like :empName and c.service_flg='Y' and c.dsgn_id=:dsgnId")                                                                                                         
    , @NamedNativeQuery ( name = "findEmployeeByUnitId", resultClass = SgvcEmpMst.class, query = "select * from sgvc_emp_mst emst,sgvo_unitemp_mpg uempg,sgvo_unit_mst umst where emst.emp_id=uempg.emp_id and uempg.unit_id= umst.unit_id and umst.unit_id=:unitId and emst.emp_id <>'EE0'" )    
    , @NamedNativeQuery ( name = "findEmployeesByDeptAndPRFLAG", resultClass = SgvcEmpMst.class, query = "select distinct c.* from sgvc_emp_mst c,sgvo_unitemp_mpg a,sgvo_unit_mst b where a.unit_id=b.unit_id and  c.emp_id=a.emp_id and a.primary_flag='Y' and c.service_flg='Y' and b.dept_id=:deptId and c.emp_id <>'EE0'" )
    , @NamedNativeQuery ( name = "SgvcEmpMst.getReportingEmpListForKPI", resultClass = SgvcEmpMst.class, query = "select distinct c.* from sgvc_emp_mst c,sgvo_unitemp_mpg a,sgvo_unit_mst b   " + 
                              "where a.unit_id=b.unit_id and  c.emp_id=a.emp_id and a.PRIMARY_FLAG =:primaryFlag and b.dept_id=:deptId and b.PARENT_UNIT_ID=:unitId and c.EMP_ID <> 'EE0' and c.SERVICE_FLG ='Y' " ) 
    , @NamedNativeQuery ( name = "getWAReportingEmpListForKPI", resultClass = SgvcEmpMst.class, query = "select distinct c.* from sgvc_emp_mst c,SGVC_WORK_ALLOCATION_MST a,sgvo_unit_mst b where a.EMP_ASRI_ROLE=b.UNIT_NAME and   " + 
                              "c.LOGIN_NAME=a.EMP_CODE and a.PRIMARY_FLAG =:primaryFlag and b.dept_id=:deptId and a.REPT_ASRI_ROLE in(select d.UNIT_NAME from SGVO_UNIT_MST d where d.UNIT_ID =:unitId) and upper(c.EMP_NO) <> :vacant" )
    , @NamedNativeQuery ( name = "SgvcEmpMst.getEmpMstBasedOnWorkAllocationByUnit", resultClass = SgvcEmpMst.class, query = " SELECT EM.*  FROM SGVC_WORK_ALLOCATION_MST W,SGVO_UNIT_MST U,SGVC_EMP_MST em WHERE upper(W.EMP_ASRI_ROLE) = upper(U.UNIT_NAME) AND U.UNIT_ID=:unitId and EM.login_name=W.EMP_CODE " )
    , @NamedNativeQuery ( name = "SgvcEmpMst.getEmpForDeptByWC", resultClass = SgvcEmpMst.class, query = "select * from sgvc_work_allocation_mst wc,sgvc_emp_mst emp,sgvo_unit_mst uni where wc.emp_code=emp.login_name and wc.emp_asri_role=uni.unit_name and uni.dept_id=:deptId " )
} )


@Entity
@Table

( name = "SGVC_EMP_MST" )
public class SgvcEmpMst implements java.io.Serializable
{


  // Fields    

  private SgvcEmpMstId id ;
 
  private String empNo ;
  private String loginName ;
  private String firstName ;
  private String middleName ;
  private String lastName ;
  private String dsgnId ;
  private String emailId ;
  private Date dob ;
  private Date doj ;
  private String phoneNo ;
  private String addr1 ;
  private String addr2 ;
  private String city ;
  private String state ;
  private String country ;
  private String pin ;
  private String serviceFlg ;
  private Date crtDt ;
  private String crtUsr ;
  private String lstUpdUsr ;
  private Date lstUpdDt ;
  private String mobileNo ;
  private String city1 ;
  private String state1 ;
  private String pin1 ;
  private Date serviceExpiryDt ;
  private String forgotPwd ;
  private String frstLoginFlg ;
  private String country1 ;
  private String reason ;
  private Date periodFrom ;
  private Date periodTo ;
  private String passwd ;
  private String dsgnName ;
  private String digiVerifyReq;
  private String theme;
  private String bioAuthRequired;
  private String district;
  private String empType;
  // Constructors

  /** default constructor */
  public SgvcEmpMst ()
  {
  }

  /** minimal constructor */
  public SgvcEmpMst ( SgvcEmpMstId id,  String empNo, String loginName, String firstName, String lastName, String dsgnId, String city, String state, String country, String serviceFlg, Date crtDt, String crtUsr )
  {
    this.id = id ;
    this.empNo = empNo ;
    this.loginName = loginName ;
    this.firstName = firstName ;
    this.lastName = lastName ;
    this.dsgnId = dsgnId ;
    this.city = city ;
    this.state = state ;
    this.country = country ;
    this.serviceFlg = serviceFlg ;
    this.crtDt = crtDt ;
    this.crtUsr = crtUsr ;
  }

  /** full constructor */
  public SgvcEmpMst ( SgvcEmpMstId id,String empNo, String loginName, String firstName, String middleName, String lastName, String dsgnId, String emailId, Date dob, Date doj, String phoneNo, String addr1, String addr2, String city, String state, String country, String pin, String serviceFlg, Date crtDt, String crtUsr, String lstUpdUsr, Date lstUpdDt, String mobileNo, String city1, String state1, String pin1, Date serviceExpiryDt, String forgotPwd, String frstLoginFlg, String country1, String reason, Date periodFrom, Date periodTo, String passwd)
  {
    this.id = id ;
    this.empNo = empNo ;
    this.loginName = loginName ;
    this.firstName = firstName ;
    this.middleName = middleName ;
    this.lastName = lastName ;
    this.dsgnId = dsgnId ;
    this.emailId = emailId ;
    this.dob = dob ;
    this.doj = doj ;
    this.phoneNo = phoneNo ;
    this.addr1 = addr1 ;
    this.addr2 = addr2 ;
    this.city = city ;
    this.state = state ;
    this.country = country ;
    this.pin = pin ;
    this.serviceFlg = serviceFlg ;
    this.crtDt = crtDt ;
    this.crtUsr = crtUsr ;
    this.lstUpdUsr = lstUpdUsr ;
    this.lstUpdDt = lstUpdDt ;
    this.mobileNo = mobileNo ;
    this.city1 = city1 ;
    this.state1 = state1 ;
    this.pin1 = pin1 ;
    this.serviceExpiryDt = serviceExpiryDt ;
    this.forgotPwd = forgotPwd ;
    this.frstLoginFlg = frstLoginFlg ;
    this.country1 = country1 ;
    this.reason = reason ;
    this.periodFrom = periodFrom ;
    this.periodTo = periodTo ;
    this.passwd = passwd ;
   
  }


  // Property accessors
  @EmbeddedId

  @AttributeOverrides ( { @AttributeOverride ( name = "empId", column = @Column ( name = "EMP_ID", nullable = false, length = 12 )
      )
      , @AttributeOverride ( name = "locId", column = @Column ( name = "LOC_ID", nullable = false, length = 5 )
      )
      , @AttributeOverride ( name = "langId", column = @Column ( name = "LANG_ID", nullable = false, length = 5 )
      )
      } )
  public

  SgvcEmpMstId getId ()
  {
    return this.id ;
  }

  public void setId ( SgvcEmpMstId id )
  {
    this.id = id ;
  }
  
  @Column ( name = "EMP_NO", nullable = false, length = 12 )
  public

  String getEmpNo ()
  {
    return this.empNo ;
  }

  public void setEmpNo ( String empNo )
  {
    this.empNo = empNo ;
  }

  @Column ( name = "LOGIN_NAME", nullable = false, length = 15 )
  public

  String getLoginName ()
  {
    return this.loginName ;
  }

  public void setLoginName ( String loginName )
  {
    this.loginName = loginName ;
  }

  @Column ( name = "FIRST_NAME", nullable = false, length = 200 )
  public

  String getFirstName ()
  {
    return this.firstName ;
  }

  public void setFirstName ( String firstName )
  {
    this.firstName = firstName ;
  }

  @Column ( name = "MIDDLE_NAME", length = 200 )
  public

  String getMiddleName ()
  {
    return this.middleName ;
  }

  public void setMiddleName ( String middleName )
  {
    this.middleName = middleName ;
  }

  @Column ( name = "LAST_NAME", nullable = false, length = 200 )
  public

  String getLastName ()
  {
    return this.lastName ;
  }

  public void setLastName ( String lastName )
  {
    this.lastName = lastName ;
  }

  @Column ( name = "DSGN_ID", nullable = false, length = 12 )
  public

  String getDsgnId ()
  {
    return this.dsgnId ;
  }

  public void setDsgnId ( String dsgnId )
  {
    this.dsgnId = dsgnId ;
  }

  @Column ( name = "EMAIL_ID", length = 60 )
  public

  String getEmailId ()
  {
    return this.emailId ;
  }

  public void setEmailId ( String emailId )
  {
    this.emailId = emailId ;
  }
  @Temporal ( TemporalType.DATE )
  @Column ( name = "DOB", length = 7 )
  public

  Date getDob ()
  {
    return this.dob ;
  }

  public void setDob ( Date dob )
  {
    this.dob = dob ;
  }
  @Temporal ( TemporalType.DATE )
  @Column ( name = "DOJ", length = 7 )
  public

  Date getDoj ()
  {
    return this.doj ;
  }

  public void setDoj ( Date doj )
  {
    this.doj = doj ;
  }

  @Column ( name = "PHONE_NO", length = 40 )
  public

  String getPhoneNo ()
  {
    return this.phoneNo ;
  }

  public void setPhoneNo ( String phoneNo )
  {
    this.phoneNo = phoneNo ;
  }

  @Column ( name = "ADDR1",length = 160 )
  public

  String getAddr1 ()
  {
    return this.addr1 ;
  }

  public void setAddr1 ( String addr1 )
  {
    this.addr1 = addr1 ;
  }

  @Column ( name = "ADDR2", length = 160 )
  public

  String getAddr2 ()
  {
    return this.addr2 ;
  }

  public void setAddr2 ( String addr2 )
  {
    this.addr2 = addr2 ;
  }

  @Column ( name = "CITY", nullable = false, length = 60 )
  public

  String getCity ()
  {
    return this.city ;
  }

  public void setCity ( String city )
  {
    this.city = city ;
  }

  @Column ( name = "STATE", nullable = false, length = 60 )
  public

  String getState ()
  {
    return this.state ;
  }

  public void setState ( String state )
  {
    this.state = state ;
  }

  @Column ( name = "COUNTRY", nullable = false, length = 60 )
  public

  String getCountry ()
  {
    return this.country ;
  }

  public void setCountry ( String country )
  {
    this.country = country ;
  }

  @Column ( name = "PIN", length = 40 )
  public

  String getPin ()
  {
    return this.pin ;
  }

  public void setPin ( String pin )
  {
    this.pin = pin ;
  }

  @Column ( name = "SERVICE_FLG", nullable = false, length = 1 )
  public

  String getServiceFlg ()
  {
    return this.serviceFlg ;
  }

  public void setServiceFlg ( String serviceFlg )
  {
    this.serviceFlg = serviceFlg ;
  }
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "CRT_DT", nullable = false, length = 7 )
  public

  Date getCrtDt ()
  {
    return this.crtDt ;
  }

  public void setCrtDt ( Date crtDt )
  {
    this.crtDt = crtDt ;
  }

  @Column ( name = "CRT_USR", nullable = false, length = 12 )
  public

  String getCrtUsr ()
  {
    return this.crtUsr ;
  }

  public void setCrtUsr ( String crtUsr )
  {
    this.crtUsr = crtUsr ;
  }

  @Column ( name = "LST_UPD_USR", length = 12 )
  public

  String getLstUpdUsr ()
  {
    return this.lstUpdUsr ;
  }

  public void setLstUpdUsr ( String lstUpdUsr )
  {
    this.lstUpdUsr = lstUpdUsr ;
  }
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "LST_UPD_DT", length = 7 )
  public

  Date getLstUpdDt ()
  {
    return this.lstUpdDt ;
  }

  public void setLstUpdDt ( Date lstUpdDt )
  {
    this.lstUpdDt = lstUpdDt ;
  }

  @Column ( name = "MOBILE_NO", length = 30 )
  public

  String getMobileNo ()
  {
    return this.mobileNo ;
  }

  public void setMobileNo ( String mobileNo )
  {
    this.mobileNo = mobileNo ;
  }

  @Column ( name = "CITY1", length = 60 )
  public

  String getCity1 ()
  {
    return this.city1 ;
  }

  public void setCity1 ( String city1 )
  {
    this.city1 = city1 ;
  }

  @Column ( name = "STATE1", length = 60 )
  public

  String getState1 ()
  {
    return this.state1 ;
  }

  public void setState1 ( String state1 )
  {
    this.state1 = state1 ;
  }

  @Column ( name = "PIN1", length = 40 )
  public

  String getPin1 ()
  {
    return this.pin1 ;
  }

  public void setPin1 ( String pin1 )
  {
    this.pin1 = pin1 ;
  }
  @Temporal ( TemporalType.DATE )
  @Column ( name = "SERVICE_EXPIRY_DT", length = 7 )
  public

  Date getServiceExpiryDt ()
  {
    return this.serviceExpiryDt ;
  }

  public void setServiceExpiryDt ( Date serviceExpiryDt )
  {
    this.serviceExpiryDt = serviceExpiryDt ;
  }

  @Column ( name = "FORGOT_PWD", length = 1 )
  public

  String getForgotPwd ()
  {
    return this.forgotPwd ;
  }

  public void setForgotPwd ( String forgotPwd )
  {
    this.forgotPwd = forgotPwd ;
  }

  @Column ( name = "FRST_LOGIN_FLG", length = 1 )
  public

  String getFrstLoginFlg ()
  {
    return this.frstLoginFlg ;
  }

  public void setFrstLoginFlg ( String frstLoginFlg )
  {
    this.frstLoginFlg = frstLoginFlg ;
  }

  @Column ( name = "COUNTRY1", length = 60 )
  public

  String getCountry1 ()
  {
    return this.country1 ;
  }

  public void setCountry1 ( String country1 )
  {
    this.country1 = country1 ;
  }

  @Column ( name = "REASON", length = 100 )
  public

  String getReason ()
  {
    return this.reason ;
  }

  public void setReason ( String reason )
  {
    this.reason = reason ;
  }
  @Temporal ( TemporalType.DATE )
  @Column ( name = "PERIOD_FROM", length = 7 )
  public

  Date getPeriodFrom ()
  {
    return this.periodFrom ;
  }

  public void setPeriodFrom ( Date periodFrom )
  {
    this.periodFrom = periodFrom ;
  }
  @Temporal ( TemporalType.DATE )
  @Column ( name = "PERIOD_TO", length = 7 )
  public

  Date getPeriodTo ()
  {
    return this.periodTo ;
  }

  public void setPeriodTo ( Date periodTo )
  {
    this.periodTo = periodTo ;
  }

  @Column ( name = "PASSWD", length = 16 )
  public

  String getPasswd ()
  {
    return this.passwd ;
  }

  public void setPasswd ( String passwd )
  {
    this.passwd = passwd ;
  }


  public void setDsgnName ( String dsgnName )
  {
    this.dsgnName = dsgnName ;
  }

  @Transient
  public String getDsgnName ()
  {
    return dsgnName ;
  }

  @Column(name="THEME",length=20,nullable=true)
  public String getTheme() {
	return theme;
}

public void setTheme(String theme) {
	this.theme = theme;
}

public void setBioAuthRequired ( String bioAuthRequired )
  {
    this.bioAuthRequired = bioAuthRequired;
  }
  
  @Column(name="BIO_AUTH_REQ",length=4,nullable=true)
  public String getBioAuthRequired ()
  {
    return bioAuthRequired ;
  }
  public void setDistrict ( String district )
  {
    this.district = district;
  }
  @Column(name="DISTRICT",length=60,nullable=true)
  public String getDistrict ()
  {
    return district ;
  }

    public void setDigiVerifyReq(String digiVerifyReq) {
        this.digiVerifyReq = digiVerifyReq;
    }
    
    @Column(name="DIGI_VERIFY_REQ",length=4,nullable=true)
    public String getDigiVerifyReq() {
        return digiVerifyReq;
    }
    
    public void setEmpType(String empType) {
            this.empType = empType;
        }

        @Column(name="EMP_TYPE",length=10,nullable=true)
        public String getEmpType() {
            return empType;
        }
}
