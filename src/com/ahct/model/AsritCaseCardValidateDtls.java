package com.ahct.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
(name = "ASRIT_CASE_CARDVALIDATE_DTLS")
public class AsritCaseCardValidateDtls implements java.io.Serializable{
    private String patientId=null;

       private String onlineCardNo=null;
       private String regCardNo=null;
       
       private String opPatientName=null;
       private String onlinePatientName=null;
       private String idPatientName=null;
       private String chkPatientName=null;

       private Long opPatientAge;
       private Long onlinePatientAge;    
       private Long idPatientAge;
       private String chkPatientAge=null;
       
       private Long opPatientAgeMonths;
       private Long opPatientAgeDays;
       private Long onlinePatientAgeMonths;    
       private Long onlinePatientAgeDays;
       private Long idPatientAgeMonths;
       private Long idPatientAgeDays;
      
       private String chkPatientNum;

       
       private String opPatientGender=null;
       private String onlinePatientGender=null;
       private String idPatientGender=null;
       private String chkPatientGender=null;

       
       private String onbedPatientPhoto=null;
       private String onlinePatientPhoto=null;
       private String idPatientPhoto=null;
       private String chkPatientPhoto=null;

       private String opParentName=null;
       private String onlineParentName=null;
       private String idParentName=null;
       private String chkParentName=null;

       private String onbedParentPhoto=null;
       private String onlineParentPhoto=null;
       private String idParentPhoto=null;
       private String chkParentPhoto=null;

       private String isChildFlag=null;
       
    private String chkPreauthFormDoc=null;
    private String chkCCFormDoc=null;
    private String chkNamePatient=null;
    private String chkNameDoctor=null;
    private String chkNameRamco=null;
    private String chkSignPatient=null;
    private String chkSignDoctor=null;
    private String chkSignRamco=null;
    private String chkPackage=null;
    private String chkPrice=null;
    private String chkCardNo=null;
    private String chkPreNamePatient=null;
    private String chkPreNameDoctor=null;
    private String chkPreNameRamco=null;
    private String chkPreSignPatient=null;
    private String chkPreSignDoctor=null;
    private String chkPreSignRamco=null;
    private String crtUsr=null;
    private Date crtDt=null;
    private String vrfyDt=null;
    
    @Column(name = "chk_patient_num")
    public String getChkPatientNum() {
		return chkPatientNum;
	}
	public void setChkPatientNum(String chkPatientNum) {
		this.chkPatientNum = chkPatientNum;
	}
	public void setPatientId(String patientId) {
           this.patientId = patientId;
       }
       @Id
       @Column(name = "PATIENT_ID")
       public String getPatientId() {
           return patientId;
       }

       public void setOnlineCardNo(String onlineCardNo) {
           this.onlineCardNo = onlineCardNo;
       }
       @Column(name = "ONLINE_CARDNO")
       public String getOnlineCardNo() {
           return onlineCardNo;
       }

       public void setRegCardNo(String regCardNo) {
           this.regCardNo = regCardNo;
       }
       @Column(name = "REG_CARDNO")
       public String getRegCardNo() {
           return regCardNo;
       }

       public void setOpPatientName(String opPatientName) {
           this.opPatientName = opPatientName;
       }
       @Column(name = "OP_PATIENT_NAME")
       public String getOpPatientName() {
           return opPatientName;
       }

       public void setOnlinePatientName(String onlinePatientName) {
           this.onlinePatientName = onlinePatientName;
       }
       @Column(name = "ONLINE_PATIENT_NAME")
       public String getOnlinePatientName() {
           return onlinePatientName;
       }

       public void setIdPatientName(String idPatientName) {
           this.idPatientName = idPatientName;
       }
       @Column(name = "IDCARD_PATIENT_NAME")
       public String getIdPatientName() {
           return idPatientName;
       }

       public void setChkPatientName(String chkPatientName) {
           this.chkPatientName = chkPatientName;
       }
       @Column(name = "CHK_PATIENT_NAME")
       public String getChkPatientName() {
           return chkPatientName;
       }

       public void setOpPatientAge(Long opPatientAge) {
           this.opPatientAge = opPatientAge;
       }
       @Column(name = "OP_PATIENT_AGE")
       public Long getOpPatientAge() {
           return opPatientAge;
       }

       public void setOnlinePatientAge(Long onlinePatientAge) {
           this.onlinePatientAge = onlinePatientAge;
       }
       @Column(name = "ONLINE_PATIENT_AGE")
       public Long getOnlinePatientAge() {
           return onlinePatientAge;
       }

       public void setIdPatientAge(Long idPatientAge) {
           this.idPatientAge = idPatientAge;
       }
       @Column(name = "IDCARD_PATIENT_AGE")
       public Long getIdPatientAge() {
           return idPatientAge;
       }

     
       public void setOpPatientGender(String opPatientGender) {
           this.opPatientGender = opPatientGender;
       }
       @Column(name = "OP_PATIENT_GENDER")
       public String getOpPatientGender() {
           return opPatientGender;
       }

       public void setOnlinePatientGender(String onlinePatientGender) {
           this.onlinePatientGender = onlinePatientGender;
       }
       @Column(name = "ONLINE_PATIENT_GENDER")
       public String getOnlinePatientGender() {
           return onlinePatientGender;
       }

       public void setIdPatientGender(String idPatientGender) {
           this.idPatientGender = idPatientGender;
       }
       @Column(name = "IDCARD_PATIENT_GENDER")
       public String getIdPatientGender() {
           return idPatientGender;
       }

       public void setChkPatientGender(String chkPatientGender) {
           this.chkPatientGender = chkPatientGender;
       }
       @Column(name = "CHK_PATIENT_GENDER")
       public String getChkPatientGender() {
           return chkPatientGender;
       }

       public void setOnbedPatientPhoto(String onbedPatientPhoto) {
           this.onbedPatientPhoto = onbedPatientPhoto;
       }
       @Column(name = "ONBED_PATIENT_PHOTO")
       public String getOnbedPatientPhoto() {
           return onbedPatientPhoto;
       }

       public void setOnlinePatientPhoto(String onlinePatientPhoto) {
           this.onlinePatientPhoto = onlinePatientPhoto;
       }
       @Column(name = "ONLINE_PATIENT_PHOTO")
       public String getOnlinePatientPhoto() {
           return onlinePatientPhoto;
       }

       public void setIdPatientPhoto(String idPatientPhoto) {
           this.idPatientPhoto = idPatientPhoto;
       }
       @Column(name = "IDCARD_PATIENT_PHOTO")
       public String getIdPatientPhoto() {
           return idPatientPhoto;
       }

       public void setChkPatientPhoto(String chkPatientPhoto) {
           this.chkPatientPhoto = chkPatientPhoto;
       }
       @Column(name = "CHK_PATIENT_PHOTO")
       public String getChkPatientPhoto() {
           return chkPatientPhoto;
       }

       public void setOpParentName(String opParentName) {
           this.opParentName = opParentName;
       }
       @Column(name = "OP_PARENT_NAME")
       public String getOpParentName() {
           return opParentName;
       }

       public void setOnlineParentName(String onlineParentName) {
           this.onlineParentName = onlineParentName;
       }
       @Column(name = "ONLINE_PARENT_NAME")
       public String getOnlineParentName() {
           return onlineParentName;
       }

       public void setIdParentName(String idParentName) {
           this.idParentName = idParentName;
       }
       @Column(name = "IDCARD_PARENT_NAME")
       public String getIdParentName() {
           return idParentName;
       }

       public void setChkParentName(String chkParentName) {
           this.chkParentName = chkParentName;
       }
       @Column(name = "CHK_PARENT_NAME")
       public String getChkParentName() {
           return chkParentName;
       }

       public void setOnbedParentPhoto(String onbedParentPhoto) {
           this.onbedParentPhoto = onbedParentPhoto;
       }
       @Column(name = "ONBED_PARENT_PHOTO")
       public String getOnbedParentPhoto() {
           return onbedParentPhoto;
       }

       public void setOnlineParentPhoto(String onlineParentPhoto) {
           this.onlineParentPhoto = onlineParentPhoto;
       }
       @Column(name = "ONLINE_PARENT_PHOTO")
       public String getOnlineParentPhoto() {
           return onlineParentPhoto;
       }

       public void setIdParentPhoto(String idParentPhoto) {
           this.idParentPhoto = idParentPhoto;
       }
       @Column(name = "IDCARD_PARENT_PHOTO")
       public String getIdParentPhoto() {
           return idParentPhoto;
       }

       public void setChkParentPhoto(String chkParentPhoto) {
           this.chkParentPhoto = chkParentPhoto;
       }
       @Column(name = "CHK_PARENT_PHOTO")
       public String getChkParentPhoto() {
           return chkParentPhoto;
       }

       public void setIsChildFlag(String isChildFlag) {
           this.isChildFlag = isChildFlag;
       }
       @Column(name = "ISCHILDFLAG")
       public String getIsChildFlag() {
           return isChildFlag;
       }

       public void setChkPatientAge(String chkPatientAge) {
           this.chkPatientAge = chkPatientAge;
       }
       @Column(name = "CHK_PATIENT_AGE")
       public String getChkPatientAge() {
           return chkPatientAge;
       }

    public void setChkPreauthFormDoc(String chkPreauthFormDoc) {
        this.chkPreauthFormDoc = chkPreauthFormDoc;
    }
    @Column(name = "CHK_PREAUTHFORMDOC")
    public String getChkPreauthFormDoc() {
        return chkPreauthFormDoc;
    }

    public void setChkCCFormDoc(String chkCCFormDoc) {
        this.chkCCFormDoc = chkCCFormDoc;
    }
    @Column(name = "CHK_CCFORMDOC")
    public String getChkCCFormDoc() {
        return chkCCFormDoc;
    }

    public void setChkNamePatient(String chkNamePatient) {
        this.chkNamePatient = chkNamePatient;
    }
    @Column(name = "CHK_NAMEPATIENT")
    public String getChkNamePatient() {
        return chkNamePatient;
    }

    public void setChkNameDoctor(String chkNameDoctor) {
        this.chkNameDoctor = chkNameDoctor;
    }
    @Column(name = "CHK_NAMEDOCTOR")
    public String getChkNameDoctor() {
        return chkNameDoctor;
    }

    public void setChkNameRamco(String chkNameRamco) {
        this.chkNameRamco = chkNameRamco;
    }
    @Column(name = "CHK_NAMERAMCO")
    public String getChkNameRamco() {
        return chkNameRamco;
    }

    public void setChkSignPatient(String chkSignPatient) {
        this.chkSignPatient = chkSignPatient;
    }
    @Column(name = "CHK_SIGNPATIENT")
    public String getChkSignPatient() {
        return chkSignPatient;
    }

    public void setChkSignDoctor(String chkSignDoctor) {
        this.chkSignDoctor = chkSignDoctor;
    }
    @Column(name = "CHK_SIGNDOCTOR")
    public String getChkSignDoctor() {
        return chkSignDoctor;
    }

    public void setChkSignRamco(String chkSignRamco) {
        this.chkSignRamco = chkSignRamco;
    }
    @Column(name = "CHK_SIGNRAMCO")
    public String getChkSignRamco() {
        return chkSignRamco;
    }

    public void setChkPackage(String chkPackage) {
        this.chkPackage = chkPackage;
    }
    @Column(name = "CHK_PACKAGE")
    public String getChkPackage() {
        return chkPackage;
    }

    public void setChkPrice(String chkPrice) {
        this.chkPrice = chkPrice;
    }
    @Column(name = "CHK_PRICE")
    public String getChkPrice() {
        return chkPrice;
    }

    public void setChkCardNo(String chkCardNo) {
        this.chkCardNo = chkCardNo;
    }
    @Column(name = "CHK_CARDNO")
    public String getChkCardNo() {
        return chkCardNo;
    }

    public void setChkPreNamePatient(String chkPreNamePatient) {
        this.chkPreNamePatient = chkPreNamePatient;
    }
    @Column(name = "CHK_PRENAMEPATIENT")
    public String getChkPreNamePatient() {
        return chkPreNamePatient;
    }

    public void setChkPreNameDoctor(String chkPreNameDoctor) {
        this.chkPreNameDoctor = chkPreNameDoctor;
    }
    @Column(name = "CHK_PRENAMEDOCTOR")
    public String getChkPreNameDoctor() {
        return chkPreNameDoctor;
    }

    public void setChkPreNameRamco(String chkPreNameRamco) {
        this.chkPreNameRamco = chkPreNameRamco;
    }
    @Column(name = "CHK_PRENAMERAMCO")
    public String getChkPreNameRamco() {
        return chkPreNameRamco;
    }

    public void setChkPreSignPatient(String chkPreSignPatient) {
        this.chkPreSignPatient = chkPreSignPatient;
    }
    @Column(name = "CHK_PRESIGNPATIENT")
    public String getChkPreSignPatient() {
        return chkPreSignPatient;
    }

    public void setChkPreSignDoctor(String chkPreSignDoctor) {
        this.chkPreSignDoctor = chkPreSignDoctor;
    }
    @Column(name = "CHK_PRESIGNDOCTOR")
    public String getChkPreSignDoctor() {
        return chkPreSignDoctor;
    }

    public void setChkPreSignRamco(String chkPreSignRamco) {
        this.chkPreSignRamco = chkPreSignRamco;
    }
    @Column(name = "CHK_PRESIGNRAMCO")
    public String getChkPreSignRamco() {
        return chkPreSignRamco;
    }
     
    public void setOnlinePatientAgeMonths(Long onlinePatientAgeMonths) {
        this.onlinePatientAgeMonths = onlinePatientAgeMonths;
    }
    @Column(name = "ONLINE_PATIENT_AGE_MONTHS")
    public Long getOnlinePatientAgeMonths() {
        return onlinePatientAgeMonths;
    }
    
    public void setOnlinePatientAgeDays(Long onlinePatientAgeDays) {
        this.onlinePatientAgeDays = onlinePatientAgeDays;
    }
    @Column(name = "ONLINE_PATIENT_AGE_DAYS")
    public Long getOnlinePatientAgeDays() {
        return onlinePatientAgeDays;
    }

    public void setOpPatientAgeMonths(Long opPatientAgeMonths) {
        this.opPatientAgeMonths = opPatientAgeMonths;
    }
    @Column(name = "OP_PATIENT_AGE_MONTHS")
    public Long getOpPatientAgeMonths() {
        return opPatientAgeMonths;
    }

    public void setOpPatientAgeDays(Long opPatientAgeDays) {
        this.opPatientAgeDays = opPatientAgeDays;
    }
    @Column(name = "OP_PATIENT_AGE_DAYS")
    public Long getOpPatientAgeDays() {
        return opPatientAgeDays;
    }

    public void setIdPatientAgeMonths(Long idPatientAgeMonths) {
        this.idPatientAgeMonths = idPatientAgeMonths;
    }
    @Column(name = "IDCARD_PATIENT_AGE_MONTHS")
    public Long getIdPatientAgeMonths() {
        return idPatientAgeMonths;
    }

    public void setIdPatientAgeDays(Long idPatientAgeDays) {
        this.idPatientAgeDays = idPatientAgeDays;
    }
    @Column(name = "IDCARD_PATIENT_AGE_DAYS")
    public Long getIdPatientAgeDays() {
        return idPatientAgeDays;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Column(name = "CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }


    public void setVrfyDt(String vrfyDt) {
        this.vrfyDt = vrfyDt;
    }
    @Column(name = "VRFY_DT")
    public String getVrfyDt() {
        return vrfyDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column(name = "CRT_DT")
    public Date getCrtDt() {
        return crtDt;
    }
}
