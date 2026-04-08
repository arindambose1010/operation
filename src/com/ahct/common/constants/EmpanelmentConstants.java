package com.ahct.common.constants;

import java.util.HashMap;
import java.util.Map;

/**********************************************************************************************
 * Date Changed   Change Id    Name                             Description
 * ************   ******      ************           ******************************************
 * 20-AUG-2009                  V T Reddy(207395)             Initial Creation.
 * 16-DEC-2009                  V T Reddy(207395)             CR Changes.
 * 08-JUN-2010					Samar(207821)				  Added Constants
 * 11-Jun-2010                  Madhavo                       Code revamp changes
 ********************************************************************************************** 
 */


 public class EmpanelmentConstants 
 {
    public static final String EMPTY_STRING = "";
    
    public static final String HIFUN_STRING = "-";
    public static final String TILDA_STRING = "~";
    public static final String CAMA_STRING = ",";
    public static final String SLASH_STRING = "/";
    public static final String TRUE_STRING = "true";
    public static final String FALSE_STRING = "false";
    public static final String Y_STRING = "Y";
    public static final String N_STRING = "N";
    public static final String R_STRING = "R";
    public static final String D_STRING = "D";
    public static final String S_STRING = "S";
    public static final String C_STRING = "C";
    public static final String L_STRING = "L";
    public static final String CE_STRING = "CE";
    public static final String MINUS_ONE_STRING = "-1";
    public static final String ZERO_STRING = "0"; 
    public static final String ONE_STRING = "1"; 
    public static final String TWO_STRING = "2";
    public static final String SPACE = " ";
    public static final String H_STRING = "H";
    
    public static final String EMPANELTYPE_N = "N";  // for New Applications i.e from homepage applications Empanelment Type is N 
    public static final String EMPANELTYPE_E = "E";  // for Inclusion of Specialities Empanelment Type is E
    public static final String SUCCESS_STRING = "Success";
    public static final String FAILURE_STRING = "Failure";
    public static final String PASS_STRING = "Pass";
    public static final String UPDATE_STRING = "Updated";
    public static final String DELETE_PASS_STRING = "PDelete";
    public static final String DELETE_FAIL_STRING = "FDelete";
    public static final String FAIL_STRING = "Fail";
    public static final String GOOD_STRING = "Good";
    public static final String BAD_STRING = "Bad";
    public static final String BLOCKED_STRING = "Blocked";
    public static final String APPLIED_STRING = "Applied";
    public static final String PRINT_STRING = "Print";
    public static final String HOMEPAGE_STRING = "HomePage";
    public static final String INSPECTION_ASSIGNED = "IA";
    public static final String INSPECTION_CONDUCTED = "IC";
    public static final String INSPECTION_SAVED = "CD932";
    //    public static final String INSPECTION_ASSIGNED = "Inspection Assigned";
    //    public static final String INSPECTION_CONDUCTED = "Inspection Conducted";
    public static final String GET_APPROVEDDATA_TAB = "getApprovedData";
    public static final String GET_REMARKS_TAB = "getRemarks";
    public static final String GET_EMPANEL_PREVINFO="getEmpanelPrevInfo";
    // START OF HOSPITAL STATUSES
    public static final String HOSP_ACTIVE = "Y";
    public static final String HOSP_DELIST = "D";
    public static final String HOSP_CON_DELIST = "L";
    public static final String HOSP_SUSP = "S";
    public static final String HOSP_CON_SUSP = "C";
    public static final String HOSP_DEMPANEL = "E";
    // END
    
    // START OF ASRIM_SEQUENCES 
    public static final String HSI_SEQ = "HSI";
    public static final String DC_SEQ = "DOC_ATTCH_SEQ";
    public static final String RM_SEQ = "RM";
    public static final String CR_SEQ = "CR"; //002
    public static final String CRM_SEQ = "CRM";
    public static final String ADR_SEQ = "ADR";
    public static final String STF_SEQ = "STF";
    public static final String PRMDC_SEQ = "PRMDC";
    public static final String INSP_SEQ = "INSP";  
	
    public static final String HSIN_SEQ = "HOSP_INFO_NEW_SEQ";
    public static final String HSID_SEQ = "DENTAL_SEQ";
    public static final String HSIP_SEQ = "PSYCHIATRY_SEQ";    
    public static final String HOSP_SEQ = "HS_SEQ";    
    public static final String HSIDC_SEQ = "DENTAL_CLINIC_SEQ";
    public static final String HSIDH_SEQ = "DENTAL_HOSPITAL_SEQ";
    public static final String HSIO_SEQ = "OPTHMLGY_SEQ";
    public static final String TDS_SEQ = "TDS_EXMPTN_SEQ";
    public static final String NABH_SEQ = "NABH_SEQ";
	
    public static final String HSIN_LBL = "HSINE";
    public static final String HSID_LBL = "HSIDE";
    public static final String HSIP_LBL = "HSIPE";
    public static final String HSIDC_LBL = "HSIDC";
    public static final String HSIDH_LBL = "HSIDH";
    public static final String HSIO_LBL = "HSIO";
	public static final String HS_MSTR_LBL = "EHS";
	public static final String TDS_EXMPTN_LBL = "ER";
	public static final String NABH_LBL = "NABHREQ";
    // END
    
    public static final String TCS_STRING = "TCS";
    public static final String EN_US_STRING = "en_US";
    public static final String ADMIN_STRING = "ADMIN";
    public static final String EMPANELUSER = "EmpanelUser";
    
    public static final String ALL_SELECT = "----ALL----";
    //Hospital Master 
    public static final String STOP_PAY_INIT = "CD590";
    public static final String STOP_PAYMENT ="CD591";
    public static final String RCVD_FOR_STOP_PAY ="CD592";
    public static final String SHOWCAUSEINITIATED = "CD593";
    public static final String SHOW_CAUSE ="CD594";
    public static final String SHOW_CAUSE_SC ="SC";
    public static final String EXPL_FOR_SHOW_CAUSE ="CD595";
    public static final String SUSPENSIONINITIATED = "CD596";
    public static final String RCVDFORSUSPENTION ="CD598";
    public static final String CONT_SUSP_INIT = "CD599";
    public static final String RCVD_FOR_CONT_SUSP ="CD600";
    public static final String RELEASE_SP_INIT ="CD601";
    public static final String GENCOMMUNICINITIATED = "CD679";
    public static final String EXPL_FOR_GEN_COMM="CD680";
    public static final String GEN_COMM = "CD638";
    public static final String DELISTINIT = "CD597";
    public static final String CONTDELISTINIT = "CD706";
    public static final String DEEMPANELINIT ="CD644";
    public static final String DE_EMPANEL_REPLY ="CD645";
    public static final String REEMPANELINIT ="RE";
    public static final String REVOCATIONINIT ="PR";
    public static final String DIRECT_STOP_PAY ="DSP";
    public static final String CALLFOREXPINITIATED= "CD640";
    public static final String CONTINUE_STOP_PAY= "CSP";
    public static final String CONT_SP_INIT= "CD642";
    public static final String RELEASESTOPPAYMENT="RSP";
    public static final String STOPPAYMENTSIGN="SP";
    public static final String CEOROLE ="CD6";
    public static final String EOROLE ="CD261";
    public static final String CMAROLE ="CD1500";
    public static final String ADMINROLE ="CD5";
    public static final String NTW_HOSP_ROLE ="CD9";
    public static final String CALENDAR_DATE="CalendarDate";
    public static final String FROM_PAGE ="fromPage";
    public static final String COMPLAINT_TYPE="ComplaintType";
    public static final String EDIT_EDC_RMKS = "editEDCRmksFields";
    public static final String MASTER_ID="MasterId";
    public static final String COMPLAINT_TYPE_SIGN="CT";
    public static final String HOSPITAL_MASTER_VO= "HospMstVO";
    public static final String RESULT_MAP ="ResultMap";
    public static final String  HOSP_MASTER_SESSION = "HospMasterSession";
    public static final String MESSAGE ="Message";
    public static final String ACTIVE_FRM_DELIST ="DY";
    public static final String ACTIVE_FRM_DEEMPANEL ="EY";
    public static final String	RESULT_STRING ="Result";
    public static final String DSP_REMARKS = "DIRECT STOP PAYMENTS BY CEO";
    public static final  String  GET_HOSP_MST_ATTCH = "getHospMasterAtachments";
    public static final  String  GET_HOSP_MST_MOU_ATTCH = "getHospMasterMouAtachments";
    
    public static final  String  RCVD_FOR_CONT_SP= "CD643";
    public static final  String  RCVD_FOR_CALL_FOR_EXP = "CD641";
    public static final String HOSPINFO_PAGE="HospInfoPage";
    public static final String HOSP_INFO="HospInfo";
    public static final String GET_BASIC_HOSPINFO="getBasicHospinfo";
    public static final String UPDATE_HOSPINFO="UpdateHospInfo";
    public static final String UPDATE_ENHANCEINFO="UpdateEnhanceInfo";
    public static final String EMPANEL_INFO="EmpanelInfo";
    public static final String GET_EMPANEL_HOSPINFO="getEmpanelHospInfo";
    public static final String INITIATE_NEW_SPECIALITIES="InitiateNewSpecialities";
    public static final String ENHANCE_INFO="EnhanceInfo";
    public static final String UPDATE_SUSDEL_REMARKS="updateSusDelRemarks";
    public static final String UPDATE_INCOMETAX_DETAILS="updateIncomeTaxDetails";
    public static final String UPDATE_ENHANCE_APPSTATUS="UpdateEnhanceAppStatus";
    public static final String PRINT_ENHANCED_INFO="PrintEnhancedInfo";
    public static final String GET_ENHANCED_HISTORY="getEnhancedHistory";
    public static final String GET_HOSPAPPLICATIONS_LIST="getHospApplicationsList";
    public static final String GET_ENHANCE_SPECIALITIES_LIST="getEnhanceSpecialitiesList";
    public static final String GET_HOSPAPPLICATIONS_EXCELLIST="getHospApplicationsExcelList";
    public static final String EMPANEL_SECURITY_PAGE="EmpanelSecurityPage";
    public static final String GET_HOSPITAL_INFORMATION="getHospitalInformation";
    public static final String APP_EXPIRY="AppExpiry";
    public static final String GET_EMPANEL_INFORMATION="getEmpanelInformation";
    public static final String PRINT_HOSPINFO="PrintHospInfo";
    public static final String EMPEXECUTIVE_STATUS="EmpExecutiveStatus";
    public static final String APPLICATION_STATUS="ApplicationStatus";
    public static final String EMPANEL_APPROVALS="EmpanelApprovals";
    public static final String PRINT_APPROVALFROM="PrintApprovalFrom";
    public static final String SHOW_HOSP_RECORDS="ShowHospRecords";
    public static final String SHOW_OLD_RECORDS="ShowOldRecords";
    public static final String GET_HOSPITAL_INFRASTRUCTURE="getHospInfraStructure";
    public static final String UPD_HOSPITAL_INFRASTRUCTURE="UpdHospInfraStructure"; 
    public static final String GET_AAROGYAMITHRA_SERVICE="getAarogyaMithraService";
    public static final String UPD_AAROGYAMITHRA_SERVICE="UpdAarogyaMithraService";
    public static final String UPD_AAROGYAMIT_REMARKS="UpdAarogyaMitRemarks";
    public static final String UPD_AAROGYAMITHRA_STATUS="updAarogyaMithraStatus";
    public static final String CEO_APPROVED="CeoApproved";
    public static final String UPDATE_HOSPINFO_APPSTATUS="UpdateHospInfoAppStatus";
    public static final String CLOSED_STATUS="Closed_Status";
    public static final String UPD_DOCTORS_MASTER="updDoctorsMaster";
    public static final String GET_DOCTORS_MASTER="getDoctorsMaster";
    public static final String GET_DOCTORS_MASTER_REPORT="getDoctorsMasterReport";    
    public static final String CHECK_REGID_EXISTS="checkRegIDExists";
    public static final String UPDATE_DOCTOR_MASTER="UpdateDoctorMaster";
    public static final String GET_ADDED_DOCTORS_MASTER="getAddedDoctorsMaster";
    public static final String ADD_DOCTORS_MASTER="addDoctorMaster";
    public static final String GET_ALL_DOCTORS_MASTER="getAllDoctorsMaster";
    public static final String DELETE_DOCTORS_MASTER="deleteDoctorsMaster";
    public static final String NO_RECORDS="NoRecords";
    public static final String EXISTS="Doctor Exists";
    public static final String UPD_EOEDC_RMKS="UpdateEOEDCRmks";
    public static final String EO_STATUS_PENDING="P";
    public static final String EO_STATUS_VERIFIED="A";
    public static final String EO_REMARKS_STATUS_PENDING="EOP";
    public static final String RAMCO_ROLE ="CD9";
    public static final String AMCCO_ROLE ="CD1799";
    public static final String REQ_SUCCESS ="CD865";
    public static final String SAVE_HOSP_STAFF="saveHospStaff";
    public static final String DEMPANEL_SPECIALITIES ="DS";
    public static final String SHOW_HOSPWISE_RAMCOS_AMCCOS="showHospWiseRmcAmcs";
    public static final String ROWS_PER_PAGE = "10";
    public static final String START_INDEX = "1";
    public static final String DISEASE_ACTIVE = "DISEASE_ACTIVE";
    
    
    
    public static final String GET_HOSPITALS_FORINSP_ASSIGN = "getHospitalsForInspAssign";
    public static final String GET_TEAMS_INFORMATION = "getTeamsInformation";
    public static final String GET_TEAMMEMBERS = "getTeamMembersBasedOnTeam";
    public static final String GET_INSPECTION_ASSIGNED_HOSPITALS =  "getInspectionAssignedHospitals";
    public static final String INSERT_INSPTEAM_FORHOSPITAL = "insertInspTeamForHospital"; 
    public static final String EMPNL_USRROLE = "CD458";
    public static final String SR_EMPNL_USRROLE = "CD478";
    public static final String JEO_USRROLE = "CD212";
    public static final String EO_USRROLE = "CD261";
    public static final String SAVE_DOC_DTLS="saveDocDtls";
    public static final String ANESTHST="ANESTHST";
    public static final String SAVE_DEPT_DTLS="saveDeptDtls";
    public static final String CASUALTY="C1";
    public static final String ACUTE_MEDICAL_CARE="C2";
    public static final String INTENSIVE_CARE_UNIT="C3";
    public static final String POST_OPERATIVE_WARD="C4";
    public static final String STEP_DOWN_WARD="C5";
    public static final String OPERATION_THEATRE="C6";
    public static final String CT_ICU="CI";
    public static final String NEURO_ICU="NI";
    public static final String BURNS_WARD="BW";
    public static final String PHYSIOTHERAPY="C7";
    public static final String PHYSIOTHERAPIST="PHYSIOTHERAPIST";
    public static final String PHARMACY="C8";
    public static final String PHARMACIST="PHARMACIST";
    public static final String BASIC_DIAGNOSTIC="C9";
    public static final String ADVANCED_DIAGNOSTIC="C10";
    public static final String BIO_CHEMISTRY="BIOCHEMISTRY";
    public static final String MICRO_BIOLOGY="MICROBIOLOGY";
    public static final String PATHOLOGY="PATHOLOGY";
    public static final String RADIOLOGY="RADIOLOGY";
    public static final String S1="S1";
    public static final String S1A="S1A";
    public static final String S1B="S1B";
    public static final String S2="S2";
    public static final String S3="S3";
    public static final String S4="S4";
    public static final String S5="S5";
    public static final String S6="S6";
    public static final String S7="S7";
    public static final String S71="S71";
    public static final String S72="S72";
    public static final String S73="S73";
    public static final String S8="S8";
    public static final String S81="S81";
    public static final String S82="S82";
    public static final String S9="S9";
    public static final String S10="S10";
    public static final String S101="S101";
    public static final String S102="S102";
    public static final String S13="S13";
    public static final String S14="S14";
    public static final String S141="S141";
    public static final String S142="S142";
    public static final String S15="S15";
    public static final String S16="S16";
    public static final String M4="M4";
    public static final String M41="M41";
    public static final String M42="M42";
    public static final String M5="M5";
    public static final String M6="M6";
    public static final String M8="M8";
    public static final String SaveSpecltyMedicalDtls="saveSurgeryDetails";
    public static final String SaveInclusionSpecltyMedicalDtls="saveIncluSurgeryDetails";
    public static final String STERILIZATION="SZ";
    public static final String GET_SPLTY="getSplty";    
    public static final String S11="S11";
    public static final String S12="S12";
    public static final String S17="S17";
    
    public static final String M1="M1";
    public static final String M2="M2";
    public static final String M3="M3";
    public static final String M7="M7";
    public static final String M9="M9";
    public static final String M10="M10";
    public static final String M11="M11";
    public static final String M12="M12";
    public static final String ON_CHANGE_NAME="OnChangeName";
    public static final String ON_CHANGE_EQUIP="OnChangeEquip";
    public static final String ON_CHANGE_BLOCKNAME="OnChangeBlockName";
    public static final String GET_EMPNL_APPDATA="getEmpanelAppData";
    public static final String ASSIGN_INSPTEAM="getInspAssignedNames";
    public static final String GET_COMMON_DATA="getCommonData";
    public static final String GET_HOSPMST_DATA="getHospMstData";
    public static final String GET_DOCDEC_DATA="getDocDecData";
    public static final String GET_DIAGN_FACILITIES="getDiagnFacilities";
    public static final String GET_INFRA_MANPOWER="getInfraManPower";
    public static final String GET_EMPNL_RAMCO_HOSPINFO="getEmpanelRamcoHospInfo";
    public static final String SPECIALIST = "SPECIALIST";
    public static final String DUTY_DOCTOR = "DUTYDOCTOR";
    public static final String PARAMEDIC = "PARAMEDIC";
    public static final String DEACTIVATION = "DACTVT";
    public static final String CHANGE_IN_SHIFT = "SHIFT";
    public static final String SPL_REQ = "SPLST";    
    public static final String DUTYDCTR_REQ = "DCTR";    
    public static final String PRMDC_REQ = "PRMDC";    
    public static final String GET_ALL_STAFF_REQSTS = "getAllStaffReqsts";
    public static final String GET_HOSP_STAFF_REQSTS = "getHospStaffReqsts";
    public static final String GET_HOSP_STAFF_REQ_DTLS = "getHospStaffReqstDtls";
    public static final String UPD_HOSPSTAFF_APPRV_DTLS = "updHospStaffApprvDtls";
    public static final String ADD_HOSP_STAFF = "addHospStaff"; 
    public static final String DISABLED = "disabled"; 
    public static final String READONLY = "readonly"; 
    public static final String HIDDEN = "none"; 
    public static final String IS_DACTV_RQSTD = "deactive"; 
    public static final String CHANGE_SHFT_FLAG = "ChangeShift"; 
    public static final String NOT_APPLICABLE = "NA"; 
    public static final String STF_RQST = "Y";
    public static final String DCTR_PHOTO = "CD905";  
    public static final String PHOTO_PATH = "EXT_IMAGES/DoctorsPhoto/";
    public static final String REQ_SAVED = "CD906";
    public static final String REQ_SUBMITTED = "CD907";
    public static final String REQ_CLOSED = "CD908";
    public static final String RECORD_SAVED = "CD888";
    public static final String RECORD_SUBMITTED = "CD889";
    public static final String RECORD_PENDING = "CD890";
    public static final String RECORD_PENDING_UPDATED = "CD891";
    public static final String EMPNL_TEAM_ACCEPTED = "CD892";
    public static final String EMPNL_TEAM_REJECTED = "CD893";
    public static final String JEO_ACCEPTED = "CD894";
    public static final String JEO_REJECTED = "CD895";
    public static final String EO_ACCEPTED = "CD896";
    public static final String EO_REJECTED = "CD897";
    public static final String REQ_FRST_SUBMITTED="CD890";    
    public static final String ADD_EDIT_HOSP_DEPT="addEditHospDept";
    public static final String EDIT_HOSP_DEPT="editHospDept";
    public static final String ADV_STRING = "A";
    public static final String BAS_STRING = "B";
    public static final String EXPERTISE = "Expertise";
    public static final String WORKLIST = "WorkList";
    public static final String CHANGE_SHIFT_UPDATED_FLAG = "Y";
    public static final String IS_ACCPTED = "Y";
    public static final String SR_MDCL_OFFICER = "CD113";
    public static final String CASUALTY_DEPT = "D01";
    public static final String ACUTE_MEDICAL_CARE_DEPT = "D02";
    public static final String INTENSIVE_CARE_UNIT_DEPT = "D03";
    public static final String POST_OPERATIVE_WARD_DEPT = "D04";
    public static final String STEP_DOWN_WARD_DEPT = "D05";
    public static final String CT_ICU_DEPT = "D08";
    public static final String NICU_DEPT = "D09";
    public static final String PICU_DEPT = "D10";
    public static final String RICU_DEPT = "D11";
    public static final String ICCU_DEPT = "D12";
    public static final String OPERATION_THEATRE_DEPT = "D14";
    public static final String PATHOLOGY_DEPT = "D16";
    public static final String RADIOLOGY_DEPT = "D17";
    public static final String VIEW_BED_STRENGTH = "viewBedStrength"; 
    public static final String DIAG_DOCTOR = "DIAGDOCTOR"; 
    public static final String DIAG = "DIAG"; 
    public static final String GET_DEPT="getDept";    
    public static final String INHOUSE_STRING = "INHOUSE";
    public static final String CONSULTANT_STRING = "CONSULTANT";
    public static final String CR_EXPERTISE_STRING = "ChangeExpertise";
    public static final String CR_EXPERTISE_RQST = "CR";
    public static final String EXPERTISE_WRKFLOW_STRING = "EXPERTISE";
    public static final String INSURANCE_DCTR_USRROLE = "CD8";//LEVEL 2 APPROVAL
    public static final String DY_EO_USRROLE = "CD931";//LEVEL 2 APPROVAL
    public static final String CLAIMS_HEAD_USRROLE = "CD116"; //LEVEL 3 APPROVAL
    public static final String GM_MEDICAL_USRROLE = "CD224"; //LEVEL 3 APPROVAL
    public static final String ON_CHANGE_GETHOSPNAME = "onChangeGetHospName"; //for govt hosp name on district and hosptype selection  
    public static final String DLST_CLAIM_CONF = "dlstClaimConf";
    
    public static final String MAIN_HEADNG = "MH";
    public static final String HEADNG = "H";       
    public static final String OPN_BRCKT = "(";
    public static final String YN_STRING = "Y/N";  
    public static final String CLSE_BRCKT = ")";
    public static final String T_STRING = "T";
    public static final String I_STRING = "I";
    public static final String P_STRING = "P";
    public static final String M_STRING = "M";
    public static final String F_STRING = "F";
    public static final String GNL_FSLTY = "CH100";
    public static final String BANKDETAILS_CR_SEQ="BANK_DETAILS_CR";
    public static final String MEDCODETAILS_CR_SEQ="MEDCO_DETAILS_CR";
    public static final String SPECIALIST_CODE= "SDOC";
    public static final String DUTYDOC_CODE= "DDOC";
    public static final String PARAMEDICAL_CODE = "PDOC";
    public static final String SPECIALIST_SEQ="EXP_CASE_SPECIALIST";
    public static final String DUTYDOC_SEQ="EXP_CASE_DUTY_DOC";
    public static final String PARAMEDIC_SEQ="EXP_CASE_PARAMEDIC";
    public static final String BASE_SEQ="EHS";
    public static final String DOCTOR_SEQUENCE="DOCTOR_SEQUENCE";
    public static final String PARAMEDICAL_SEQUENCE="PARAMEDICAL_SEQUENCE";
    
    public static final String HSIE_LBL = "HSIE";
    public static final String HSIE_SEQ = "ENT_CLINIC_SEQ";
	public static final String EHF_COMPLAINT = "COMP";
    public static final String EHF_COMP_SEQ="EHF_COMPLAINT_SEQ";
    public static final String ACTION_ATCH="EHF_ACTION_ATCH_SEQ";

    
    public static final String TDS_EXMP_NIL_RATE = "0"; 
    public static final String EL ="EL"; 
    public static final String EN ="EN";
    public static final String TDS_RAMCO_ATTACH="RAMCO";
    public static final String APPROVE="Approve";
    public static final String PENDING="Pending";
    public static final String UPDATE="Update";
    public static final String DEDUCTOR_TYPE_TRUST="TRUST";
    public static final String MEDCO_UPDATE="WF_TDS_EXMPTN_MEDCO_UPDATE";
    public static final String NA_STRING="NA";
    public static final String ACO_APPROVE="WF_TDS_EXMPTN_ACO_APRVL";
    public static final String CEO_APPROVE="WF_TDS_EXMPTN_CEO_APRVL";
    public static final String TDS_PENDING="Request Kept Pending";
    public static final String TDS_REJECT="Request Rejected";
    public static final String TDS_MEDCO_UPDATE="";
    

    public static final String NORMAL_APP="CD10000";
    public static final String DENTAL_APP="CD10001";
    public static final String DENTAL_APP_HOSP="CD10002";
    public static final String PSYCHIATRIC_APP="CD10003";
    public static final String OPTHA_APP="CD10003";
    public static final String ENT_APP="CD10004";

	
	public static final String EDC_ACTION_SEQ="EDC_ACTION_SEQ";
    public static final String EDC_ACTION_LBL="EDC";
    public static final String APPROVE_VALUE="A";
	public static final String EDC_ATTACH="EDC";
	
	public static final String NABH_UPDATE="WF_NABH_MEDCO_UPDATE";
	public static final String NABH_APRV="WF_NABH_JEO_EDM_APRV";
	
	public static final String AP="CD201";
	public static final String TG="CD202";
	public static final String BOTH_STATES="CD203";
	
	public static final String ACCTS_HEAD_SEQ="ACC_HEAD_ID";
	public static final String ACCTS_HEAD_CODE_SEQ="ACC_HEAD_CODE";
	
	  public static final String FILE_EXT_XLS="xls";
	  public static  Map<String,String> FILE_EXT=new HashMap<String,String>();
	  static{
	          FILE_EXT.put("xls","application/vnd.ms-excel");
	        }
}
