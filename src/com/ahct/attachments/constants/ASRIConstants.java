
package com.ahct.attachments.constants;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ASRIConstants
{

  public static final String FALSE_STRING = "false" ;
  public static final String EMPTY_STRING = "" ;
  public static final String MINUS_ONE = "-1" ;
  public static final String TRUE_STRING = "true" ;
  public static final String INTEGER_ONE_STRING = "1" ;
  public static final String INTEGER_TWO_STRING = "2" ;
  public static final String INTEGER_TEN_STRING = "10";//0019
  public static final String SPACE_STRING = " " ;
  public static final String LANG_EN_US = "en_US" ;
  public static final int ZERO_VALUE = 0 ;
  public static final int ONE_VALUE = 1 ;
  public static final String INTEGER_ZERO_STRING = "0" ;
  public static final boolean BOOLEAN_FALSE = false ;
  public static final boolean BOOLEAN_TRUE = true ;
  public static final String CAMA = ",";
  public static final String EDC_ATTACH_PATH="edcattachments/"; 
  public static final String FILESYS_FIXED_DATE = "26/12/2008" ;
  public static final String FILESYS_NEXT_FIXED_DATE = "31/03/2009" ;
  public static final String FILESYS_STORAGE1 = "true" ;
  public static final String SHAREPATH1="/storageNAS/argfilesystem";//0005
  public static final String SHAREPATHP="/storageNAS/phase";//0005
  public static final String SHAREPATH="/storage/argfilesystem";//0005
  public static final String ASRI_TEMP="/ASRITemp";//0005
  public final static SimpleDateFormat ddmmyyyy=new SimpleDateFormat( "dd/MM/yyyy");
  public final static SimpleDateFormat hhmmss=new SimpleDateFormat( "HH:mm:ss ");
  public final static SimpleDateFormat hhmi=new SimpleDateFormat( "HH:mm ");//0019
   public final static SimpleDateFormat ddmmyyyyhhmm=new SimpleDateFormat( "dd/MM/yyyy HH:mm");//0022
  public final static SimpleDateFormat ddmmyyyyhhmmss=new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss");//0022
  public final static int TRANSACTION_TIMEOUT=300;  
  public static final String FLAG_Y = "Y" ;
  public static final String FLAG_N = "N" ;//020
  public static final String STATUS_PRE = "PRE" ;
  public static final String STATUS_COCH = "COCH" ;
  public static final String STATUS_HEMO = "HEMO" ;
  public static final String STATUS_CL = "CL" ;
  public final static String DT_FORMAT_DDMMYYYY="dd/MM/yyyy";
  public final static String DT_FORMAT_DDMMYYYY_HH24="DD/MM/YYYY HH24:MI:SS";
    public final static String DT_FORMAT_DDMMYYYY_HHMM24="DD/MM/YYYY HH24:MI";
  public static final String CARD_TYPE_W = "W" ;
  public static final String CARD_TYPE_J = "J" ;
  public static final String STATUS_REJ = "REJ" ;
  public static final String STATUS_APV = "APV" ;
  public static final String CARD_TYPE_C = "C" ;//0018
  public static final String SEATCH_CATEGORY_C = "C" ;
  public static final String SEATCH_CATEGORY_W = "W" ;
  public static final String SEATCH_CATEGORY_M = "M" ;
  public static final String SEATCH_CATEGORY_O = "O" ;
  public static final String HOSP_SURG_DET_DATE = "01-APR-2007" ;
  public static final String TODAY = "Today" ;
  public static final String INTEGER_SEVEN_STRING = "7" ;
  
  public static final String ACTION_VAL="actionVal";
  public static final String USER_ID = "UserID";
  public static final String USER_ROLE ="UserRole";
  //start 0005
  public static final String CASE_ID="CASE_ID";
  public static final String CASE_ID_1="CaseId";
  public static final String CRT_USR="CRT_USR";
  public static final String CRT_DT="CRT_DT";
  public static final String ATTACH_TYPE="ATTACH_TYPE";
  public static final String PATIENT_ID="PatId";
  public static final String PATIENT_ID_1="PatientId";
  public static final String TELEPHONIC_ID="TelephonicId";
  public static final String NEWSTORAGE1="NewStorage1";
  public static final String SLASH_VALUE="/" ;
  public static final String UNDERSCORE_VALUE="_" ;  
  public static final String ATTACHMENT_SIZE="204800";
  public static final String DOWNLOAD_RETURN_PAGE="showDocument";
    public static final String ATTACHMENT_MODE_ATTACH="attachment";
    public static final String ATTACHMENT_MODE_INLINE="inline";
 //end 0005 & start 0012
  public static final String ROLE_CMA="CD1500";   
  public static final String ROLE_CEO="CD6";   
  //  public static final String APPROVER_DOCTORS="CD301,CD8,CD13,CD113,CD7,CD212,CD261,CD116,CD224,CD432";   //0013
  //public static final String DIS_SUMM_DOCTORS="CD7,CD8,CD13,CD113,CD301,CD115,CD116,CD117,CD212,CD261,CD424,CD561,CD114,CD306,CD318,CD308,CD997,CD215,CD259";//015
  public static final String APPROVER_DOCTORS="CD301,CD8,CD212,CD317,CD116,CD307,CD113,CD7,CD261,CD13,CD224,CD432,CD114,CD15653";//0026
  public static final String FLAGGED_APPROVER_DOCTORS="CD301,CD8,CD212,CD317,CD116,CD307,CD113,CD7,CD261,CD13,CD224,CD432,CD586,CD15200,CD451,CD1302,CD15531,CD15509,CD15510,CD114,CD306,CD217,CD15308"; //0025
  public static final String DIS_SUMM_DOCTORS= "CD301,CD8,CD212,CD317,CD116,CD307,CD113,CD7,CD424,CD15451,CD15454,CD262,CD158,CD15307,CD15060,CD217,CD15509,CD15510,CD15452,CD15306,CD15200,CD451,CD6,CD1500,CD261,CD114,CD306,CD13,CD224,CD432,CD446,CD1112,CD15653";//FB 27082,0027
  public static final String FLAGGED_DIS_SUMM_DOCTORS= "CD301,CD8,CD212,CD317,CD116,CD307,CD113,CD7,CD424,CD15451,CD15454,CD262,CD158,CD15307,CD15060,CD217,CD15509,CD15510,CD15452,CD15306,CD15200,CD451,CD6,CD1500,CD261,CD114,CD306,CD13,CD224,CD432,CD446,CD1112,CD586,CD15200,CD451,CD1302,CD15531,CD15509,CD15510,CD114,CD306,CD217,CD15308,CD15653"; //0025,0027
//end 0012
  public static final String ATTACHMENT_SIZE_100MB="104857600";
  public static final String PHOTO="photo";
 //end 0005
  public static final String OLD_STORAGE_BOX="/storageNAS2-Production/";//16-04-2012 
  public static final String STORAGE_BOX="/storageNAS-TS-Production/";  //0014 //16-04-2012 
  public static final String CC_STORAGEPATH="callcenter";
  public static final String GV_STORAGEPATH="grievance";
  public static final String PR_STORAGEPATH="praja";
  public static final String RB_STORAGEPATH="ratchabanda";//0019
  public static final String STORAGE_BOX_CASES=STORAGE_BOX+"Phase";
  public static final String PHOTOGALLERY_PATH="photogallery/";
  public static final String NEWSCUTTINGS_PATH="cuttings/";
  public static final String UPLOADED="Uploaded Successfully";
  public static final String BLOB_SESSION="BlobSession";
  public static final String PATIENTFB_PATH="PatientFeedBack";//0010

 public static final String ADMIN_PATH="admin/";
 public static final String CMO_ATTACH="CMO";//0008
 public static final String TESTS_ATTACH="TestsAttachments";//0008
 public static final String FILE_EXT_JPG="jpg";
 public static final String FILE_EXT_JPEG="jpeg";
 public static final String FILE_EXT_PDF="pdf";
 public static final String FILE_EXT_GIF="gif";
 public static final String JOURNALIST_RENEWAL="1";//0016
 public static final String Role_SRDGMHEALTHCAMP="CD15349";  //0017
 public static final String NO_NAMES_FOUND="----No Names Found-----";//0018
 public static final String SELECT="----Select-----";//0018
 public static final String ASCENDING_ORDER = "ASC";//0019
 public static final String DESENDING_ORDER = "DESC";//0019
  public final static String DT_FORMAT_DDMMYYYYHHMI_AM="DD/MM/YYYY HH12:MI AM";//0019
 public static final String NONE_STRING = "none";//0022
//  public static final String INPATIENT_CASE_REG = "CD73";
//  public static final String ISSUE_TRACKER = "P"; //0024 
  public static final String FEEDBACK = "R"; 
  public static final String UPDATE_STRING = "Update";//0024 
 
      public static final String INTEGER_TWELVE_STRING = "12" ;
    public static final String ENROL_FAM_DIS_CERF = "Disability Cerfs" ;
    public static final String ENROL_FAM_MEM_PHOTO = "FamilyMembersPhotos" ;
    public static final String ENROL_FAM_MEM_DOB_CERTI = "FamilyMembersDobCerti" ;
    public static final String ENROL_FAM_MEM_AADHAR_CERTI = "FamilyMembersAadharCerti" ;
      public static final String ENROL_PARNT_ID_SEQUENCE = "EMP";
     public static final String   ENROL_ID_SEQUENCE="EHF";
    public static final String   EHF_CARD_SEQUENCE="EHFCARDNO";
    public static final String ENROLLMENT_SERVICEREGISTER="CD3001";
    public static final String ENROLLMENT_EMP_PHOTO="CD3002";
    public static final String ENROLLMENT_AADHAR="CD3005";
    public static final String ENROLLMENT_DISABLITY="CD3008";
    public static final String ENROLLMENT_NATIONALITY="CD3011";
    public static final String  ENROLLMENT_INITIATED ="CD3014";
    public static final String  ENROLLMENT_DOC_PENDING ="CD3015";
    public static final String  ENROLLMENT_DOC_SUBMITTED ="CD3016";
    public static final String  ENROLLMENT_PENDWITH_VENDOR ="CD3020";
    public static final String  ENROLLMENT_DDO_REJECTION ="CD3018";
    public static final String  ENROLLMENT_EMP ="CD3022";
    public static final String  ENROLLMENT_APPROVAL_LGSB ="_APPROVE_ENROLL_LGSB";
     public static final String  ENROLLMENT_REJECT_LGSB ="_REJECT_ENROLL_LGSB";
    public static final String EXCEPTION_PAGE="Exception";
    public static final String ATTCH_SIZE_EXCEED_ERROR="Attachment size exceeded ";
    public static final String ATTCH_UPLD_FAILURE="Failed to Upload.Please try again";
    public static final String ATTCH_UPLD_SUCCESS="Uploaded successfully.";
public static  Map<String,String> FILE_EXT=new HashMap<String,String>();
static{
        FILE_EXT.put("doc","application/msword");
        FILE_EXT.put("rtf","application/msword");
        FILE_EXT.put("xls","application/vnd.ms-excel");
        FILE_EXT.put("ppt","application/vnd.ms-powerpoint");
        FILE_EXT.put("pdf","application/pdf");
        FILE_EXT.put("txt","text/plain");
        FILE_EXT.put("sxw","application/vnd.sun.xml.writer");
        FILE_EXT.put("stw","application/vnd.sun.xml.writer.template");
        FILE_EXT.put("sxd","application/vnd.sun.xml.draw");
        FILE_EXT.put("std","application/vnd.sun.xml.draw.template");
        FILE_EXT.put("sxc","application/vnd.sun.xml.calc");
        FILE_EXT.put("sxi","application/vnd.sun.xml.impress");
        FILE_EXT.put("zip","application/x-zip-compressed");
        FILE_EXT.put("html","message/rfc822");
        FILE_EXT.put("text/html","message/rfc822");
        FILE_EXT.put("htm","message/rfc822");
        FILE_EXT.put("mht","message/rfc822");
        FILE_EXT.put("wmv","application/octet-stream");
        FILE_EXT.put("video/x-ms-wmv","application/octet-stream");
		FILE_EXT.put("jpg","image/jpg");
        FILE_EXT.put("jpeg","image/jpeg");
        FILE_EXT.put("png","image/png");
        FILE_EXT.put("gif","image/gif");
        FILE_EXT.put("bmp","image/bmp");
        FILE_EXT.put("JPG","image/jpg");
        FILE_EXT.put("JPEG","image/jpeg");
        FILE_EXT.put("PNG","image/png");
        FILE_EXT.put("GIF","image/gif");
        FILE_EXT.put("BMP","image/bmp");
		FILE_EXT.put("PDF","application/pdf");
      }

}
