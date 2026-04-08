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
@Table

( name = "ASRIM_USERS" )
public class AsrimUsers implements Serializable
{
        private String  userID ;  
        private String loginName;
        private Date crtDt;
        private String crtUsr;
        private String firstName ;           
        private String lastName ;            
        private String  activeYn ;            
        private String userRole ;            
        private String email    ;            
        private String fax     ;             
        private String   phone1 ;              
        private String   phone2 ;              
        private String    cug  ;                         
        private String    accountNo   ;      
        private String    bankId   ;         
        private String    nameAsPerAcc ;         
        private String    gender ;           
        private String     digSignReq;
        private String      isDigLog ; 
        private String      teamId      ;        
        private String      statusRem  ;         
        private String      statusReason ;       
        private String      biomReq ;  
        private String companyId;
        private String qual;
        private String regNo;
        private String empNo;
        private String exactCid;
        private String encryptedPasswd;
        private String lstUpdUser;
        private Date lstUpdDt;
        private String is_macId_check;
    private String userMacId;
    private String primaryLogin;
    private String newEmployeeCode;
    private String panNumber;
        
    public AsrimUsers() {
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    @Id
    @Column ( name = "USER_ID", nullable = false, length = 12 )
    public String getUserID() {
        return userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column ( name = "FIRST_NAME",  length = 1200 )
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column ( name = "LAST_NAME",  length = 12 )
    public String getLastName() {
        return lastName;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }
    
    @Column ( name = "ACTIVE_YN ", length = 1 )
    public String getActiveYn() {
        return activeYn;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    @Column ( name = "USER_ROLE", length = 12 )
    public String getUserRole() {
        return userRole;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column ( name = "EMAIL",  length = 12 )
    public String getEmail() {
        return email;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
    @Column ( name = "FAX",  length = 20 )
    public String getFax() {
        return fax;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
    @Column ( name = "PHONE1",  length = 12 )
    public String getPhone1() {
        return phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    @Column ( name = "PHONE2",  length = 12 )
    public String getPhone2() {
        return phone2;
    }

    public void setCug(String cug) {
        this.cug = cug;
    }
    @Column ( name = "CUG",  length = 20 )
    public String getCug() {
        return cug;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    @Column ( name = "ACCOUNT_NO",  length = 20 )
    public String getAccountNo() {
        return accountNo;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    @Column ( name = "BANK_ID",  length = 12 )
    public String getBankId() {
        return bankId;
    }

    public void setNameAsPerAcc(String nameAsPerAcc) {
        this.nameAsPerAcc = nameAsPerAcc;
    }
    @Column ( name = "NAME_ASPERACC",length = 200 )
    public String getNameAsPerAcc() {
        return nameAsPerAcc;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column ( name = "GENDER",length = 1 )
    public String getGender() {
        return gender;
    }

    public void setDigSignReq(String digSignReq) {
        this.digSignReq = digSignReq;
    }
    @Column ( name = "DIGITALSIGN_REQUIRED",length = 12 )
    public String getDigSignReq() {
        return digSignReq;
    }

    public void setIsDigLog(String isDigLog) {
        this.isDigLog = isDigLog;
    }
    @Column ( name = "IS_DIGITALSIGN_LOGIN",length = 12 )
    public String getIsDigLog() {
        return isDigLog;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    @Column ( name = "TEAM_ID",length = 12 )
    public String getTeamId() {
        return teamId;
    }

    public void setStatusRem(String statusRem) {
        this.statusRem = statusRem;
    }
    @Column ( name = "STATUS_REM",length = 20 )
    public String getStatusRem() {
        return statusRem;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }
    @Column ( name = "STATUS_REASON",length = 20 )
    public String getStatusReason() {
        return statusReason;
    }
   

    public void setBiomReq(String biomReq) {
        this.biomReq = biomReq;
    }
    @Column ( name = "BIOM_REQUIRED",length = 1 )
    public String getBiomReq() {
        return biomReq;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Column ( name = "CID",length = 1 )
    public String getCompanyId() {
        return companyId;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }
    @Column ( name = "QUALIFICATION",length = 100 )
    public String getQual() {
        return qual;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    @Column ( name = "REGNO",length = 50 )
    public String getRegNo() {
        return regNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    @Column ( name = "USER_CODE",length = 12)
    public String getEmpNo() {
        return empNo;
    }

    public void setExactCid(String exactCid) {
        this.exactCid = exactCid;
    }
    @Column ( name = "EXACT_CID",length =2)
    public String getExactCid() {
        return exactCid;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    @Column ( name = "LOGIN_NAME",length =200)
    public String getLoginName() {
        return loginName;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Temporal ( TemporalType.TIMESTAMP )
    @Column(name="CRT_DT",  length=7)

    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Column ( name = "CRT_USR",length =12)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setEncryptedPasswd(String encryptedPasswd) {
        this.encryptedPasswd = encryptedPasswd;
    }
    @Column ( name = "ENCRIPTED_PASSWORD",length =200)
    public String getEncryptedPasswd() {
        return encryptedPasswd;
    }

    public void setLstUpdUser(String lstUpdUser) {
        this.lstUpdUser = lstUpdUser;
    }
    @Column ( name = "LST_UPD_USR",length =12)
    public String getLstUpdUser() {
        return lstUpdUser;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    
    @Temporal ( TemporalType.TIMESTAMP )
    @Column(name="LST_UPD_DT",  length=7)
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setIs_macId_check(String is_macId_check) {
        this.is_macId_check = is_macId_check;
    }
    @Column ( name = "IS_MACID_CHECK_REQ",length = 1)
    public String getIs_macId_check() {
        return is_macId_check;
    }

    public void setUserMacId(String userMacId) {
        this.userMacId = userMacId;
    }
    @Column ( name = "USR_MACID",length = 90)
    public String getUserMacId() {
        return userMacId;
    }

    public void setPrimaryLogin(String primaryLogin) {
        this.primaryLogin = primaryLogin;
    }

    @Column ( name = "PRIMARY_FLAG", nullable = false, length = 5 )
    public String getPrimaryLogin() {
        return primaryLogin;
    }

    public void setNewEmployeeCode(String newEmployeeCode) {
        this.newEmployeeCode = newEmployeeCode;
    }

    @Column ( name = "NEW_EMP_CODE", nullable = false, length = 50 )
    public String getNewEmployeeCode() {
        return newEmployeeCode;
    }
    @Column ( name = "PAN_NUMBER", nullable = false, length = 50 )
	public String getPanNumber() {
		return panNumber;
	}
	
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
    
}


