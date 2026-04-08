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
@Table( name = "EHF_DIGITAL_SIGN" ) 
public class EhfDigitalSign  implements Serializable {
	   
    private String signId;
    private String userData;
    private String USER_SIGN;
    private String CRT_USR;
    private Date CRT_DT;
    private String LST_UPD_USR;
    private Date LST_UPD_DT;
    private String USER_ID;
    private Date SIGNED_TIME;
    private String ISSUER_DN;
    private String SERIAL_NO;
    private Date EXPIRY_DATE;
    private String activeYn;


 public EhfDigitalSign()
 {
 }




 public void setSignId(String signId) {
     this.signId = signId;
 }
 @Id
 @Column(name="SIGN_ID", nullable=false, length=10)
 public String getSignId() {
     return signId;
 }

 public void setUserData(String userData) {
     this.userData = userData;
 }

 @Column(name="USER_DATA", nullable=true, length=200)
 public String getUserData() {
     return userData;
 }

 public void setUSER_SIGN(String uSER_SIGN) {
     this.USER_SIGN = uSER_SIGN;
 }

 @Column(name="USER_SIGN", nullable=true, length=4000)
 public String getUSER_SIGN() {
     return USER_SIGN;
 }

 public void setCRT_USR(String cRT_USR) {
     this.CRT_USR = cRT_USR;
 }

 @Column(name="CRT_USR", nullable=false, length=11)
 public String getCRT_USR() {
     return CRT_USR;
 }

 public void setCRT_DT(Date cRT_DT) {
     this.CRT_DT = cRT_DT;
 }

 @Temporal(TemporalType.TIMESTAMP)
 @Column(name="CRT_DT", nullable=false, length=12)
 public Date getCRT_DT() {
     return CRT_DT;
 }

 public void setLST_UPD_USR(String lST_UPD_USR) {
     this.LST_UPD_USR = lST_UPD_USR;
 }

 @Column(name="LST_UPD_USR", nullable=true, length=11)
 public String getLST_UPD_USR() {
     return LST_UPD_USR;
 }

 public void setLST_UPD_DT(Date lST_UPD_DT) {
     this.LST_UPD_DT = lST_UPD_DT;
 }

 @Temporal(TemporalType.TIMESTAMP)
 @Column(name="LST_UPD_DT", nullable=true, length=12)
 public Date getLST_UPD_DT() {
     return LST_UPD_DT;
 }

 public void setUSER_ID(String uSER_ID) {
     this.USER_ID = uSER_ID;
 }

 @Column(name="USER_ID", nullable=true, length=11)
 public String getUSER_ID() {
     return USER_ID;
 }

 public void setSIGNED_TIME(Date sIGNED_TIME) {
     this.SIGNED_TIME = sIGNED_TIME;
 }

 @Temporal(TemporalType.TIMESTAMP)
 @Column(name="SIGNED_TIME", nullable=true, length=20)
 public Date getSIGNED_TIME() {
     return SIGNED_TIME;
 }

 public void setISSUER_DN(String iSSUER_DN) {
     this.ISSUER_DN = iSSUER_DN;
 }

 @Column(name="ISSUER_DN", nullable=true, length=11)
 public String getISSUER_DN() {
     return ISSUER_DN;
 }

 public void setSERIAL_NO(String sERIAL_NO) {
     this.SERIAL_NO = sERIAL_NO;
 }
 
 @Column(name="SERIAL_NO", nullable=true, length=11)
 public String getSERIAL_NO() {
     return SERIAL_NO;
 }

 public void setEXPIRY_DATE(Date eXPIRY_DATE) {
     this.EXPIRY_DATE = eXPIRY_DATE;
 }

 @Temporal(TemporalType.TIMESTAMP)
 @Column(name="EXPIRY_DATE", nullable=true, length=12)
 public Date getEXPIRY_DATE() {
     return EXPIRY_DATE;
 }



 @Column(name="ACTIVE_YN", nullable=true)
public String getActiveYn() {
	return activeYn;
}




public void setActiveYn(String activeYn) {
	this.activeYn = activeYn;
}
 
 
}
