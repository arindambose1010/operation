package com.ahct.preauth.util;


public class PreauthConstants 
{
    public static final String PREAUTH_APPROVED="Preauthorisation Approved by ";
    public static final String PREAUTH_REJECTED="Preauthorisation Rejected by ";
    public static final String PREAUTH_PENDING="Preauthorisation kept Pending by ";
    public static final String PREAUTH_CANCELLATION="Preauthorisation Cancelled by ";
    public static final String PREAUTH_CANCELLATION_PEND="Cancellation kept Pending by ";
    public static final String PREAUTH_TERMINATION="Preauthorisation Terminated by ";
    public static final String PREAUTH_TERMINATION_PEND="Termination kept Pending by ";
    public static final String PREAUTH_SENT_TO_CEO="Preauthorisation Sent to PAO for approval";
    public static final String ENHANCE_APPROVED="Enhancement Request  Recommended for Approval by ";
    public static final String ENHANCE_PAO_APPROVED="Enhancement Request Approved by ";
    public static final String ENHANCE_REJECTED="Enhancement Request Rejected by ";
    public static final String ENHANCE_PENDING="Enhancement Request kept Pending by ";   
    public static final String PREAUTH_CMO_GRP_APRV="Preauthorisation Approved by CMO";
    public static final String PREAUTH_CMO_GRP_REJ="Preauthorisation Rejected by CMO";
    public static final String PREAUTH_CMO_GRP_CNCL="Preauthorisation Cancelled by CMO";
    public static final String PREAUTH_CMO_GRP_TER="Preauthorisation Terminated by CMO";   
    public static final String PREAUTH_VERIFIED="Preauthorisation Verified by ";
    
    
    
    /*-----------Start Success Messages------------*/
    public static final String NWH_CNCL_REQ_MSG="Claim Requested for Cancellation by Network Hospital";
    public static final String NWH_ENH_REQ_MSG="Enhancement Request Raised by NWH";  
    public static final String ADMN_REQ_MSG="Admission notes submitted successfully";//016
    public static final String TRTMT_SCHEDULE_SUCCESS="Treatment Schedule Updated Successfully";
    public static final String OPNOTES_SUCCESS="Surgery Details Updated Successfully";
    public static final String UPD_DTH_CASE_SUCCESS= "Death Details Updated Successfully";
    public static final String ATTCH_UPLD_SUCCESS="Uploaded successfully.";
    /*-----------End Success Messages--------------*/
    
    /*------------Start Failure Messages-----------*/
    public static final String NWH_CNCL_FAIL_MSG="Unable to update Claim Request for Cancellation by Network Hospital.Please try again";
    public static final String PROCESS_PREAUTH_FAIL_MSG="Unable to process Preauth.Please try again ";
    public static final String PROCESS_ENHANCE_FAIL_MSG="Unable to process Enhancement, Please try again ";
    public static final String CLAIM_NO_GEN_FAIL_MSG="Unable to generate Claim No.";
    public static final String NWH_ENH_FAIL_MSG="Unable to update Enhancement Request Raised by NWH.Please try again";    
    public static final String ADMN_REQ_FAIL_MSG="Unable to update Admission Notes.Please try again";
    public static final String TRTMT_SCHEDULE_FAILURE="Unable to update Treatment Schedule.Please try again";    
    public static final String OPNOTES_FAILURE="Unable to update details.Please try again";  
    public static final String UPD_DTH_CASE_FAILURE="Unable to update death details.Please try again";
    public static final String ATTCH_UPLD_FAILURE="Failed to Upload.Please try again";
    public static final String ATTCH_SIZE_EXCEED_ERROR="ERROR : Attachment size exceeded ";
    /*-------------End Failure Messages------------*/
    
    /*---------------Start Actionvals----------------*/
    //start PreauthForwardRH
    public static final String SENDPREAUTH="sendPreAuth";
    public static final String PROCESSPREAUTH="processPreauth";
    public static final String PROCESSENHANCE="processEnhance";
    public static final String PROCESSNWHENHREQ="processNwhEnhReq";
    public static final String PROCESSCMO="processCmo";
    public static final String PROCESSNWHCNCL="processNWHCncl";
    //end PreauthForwardRH
    //start AdmissionNotesRH
    public static final String SUBMITADMNNOTES="submitAdmnNotes";
    public static final String GETADMNNOTES="getAdmissionNotes";
    //end AdmissionNotesRH
    //start SurgeryUpdateRH
    public static final String GETTRMTSCHEDULE="getTrmtSchedule";
    public static final String SUBMITTRMTSCHEDULE="submitTrmtSchedule";
    public static final String PAC_OP_NOTES="PacOpNotes";
    public static final String SAVEOPNOTES="saveOpNotes";
    //end SurgeryUpdateRH
    //start AttachmentRH
    public static final String UPLD_INSP_ATTACH="uploadInspectionAttach";
    public static final String GET_INSP_ATTACH="getInspectionAttachments";
    public static final String UPLD_CASE_ATTACH="uploadCaseAttach";//003
    public static final String GET_CASE_ATTACH="getCaseAttachments";//003
    //end AttachmentRH
  public static final String UPDATENOTESCLINICDATA = "UpdateNotesClinicData" ;
  public static final String GETCASEDTLS = "getCaseDtls" ;
  public static final String GETPATIENTTOTALDTLS = "getPatientTotalDtls" ;
  public static final String GETHOSPSURGDTLS = "getHospSurgDtls" ;
  public static final String GETDTHCASEDTLS = "getDthCaseDtls" ;
  public static final String UPDDTHCASEDTLS = "UpdDthCaseDtls" ;
  public static final String GETCASESURGERYDTLS = "getCaseSurgeryDtls" ;
  public static final String GETAPVREJCASEDTLS = "getApvRejCaseDtls" ;
  public static final String GETINPROCESSCASEDTLS = "getInprocessCaseDtls" ;
  public static final String GENERATELETTER="GenerateLetter";//001
   public static final String GETINPATIENTCARD="getInPatientCard"; //014
    /*-----------------End Actionvals-----------------*/
    
    /*---------------Start Pagenames-------------------*/
    public static final String VIEWSUCCESSPAGE="PreAuthSuccessPage";
    public static final String VIEWADMISSIONNOTES="ViewAdmissionNotes";
    public static final String VIEWTRMTSCHEDULE="ViewTrmtSchedule";
    public static final String PAC_OP_NOTES_PAGE="PACOpNotes";
    public static final String VIEWCASETABS = "ViewCaseTabs" ;
    public static final String VIEWCLAIMPATIENTINFO = "ViewClaimPatientInfo" ;
    public static final String VIEWHOSPSCORECARD = "ViewHospScoreCard" ;
    public static final String VIEWDEATHCASE = "ViewDeathCase" ;
    public static final String CASESURGERYDISCHARGEDATEUPDATE = "CaseSurgeryDischargeDateUpdate" ;
    public static final String INSPECTION_ATTACHMENTS="InspectionAttachments";
    public static final String EXCEPTION_PAGE="Exception";
    public static final String SHOW_LETTER="showLetter";//001
    public static final String SHOW_SATIS_LETTER="showSatisLetter";//001
    public static final String CASE_ATTACHMENTS="CaseAttachments";//003
     public static final String VIEWINPATIENTCARD="InPatientCard"; //014
    /*-----------------End Pagenames-------------------*/ 
    
    /*---------------Start Attachment Types-------------------*/
    public static final String CARD_DOC_TYPE="CD98";
    public static final String AFTER_SURG_DOC_TYPE="CD130";
    public static final String CASE_DTH_DT_UPD_DOC_TYPE="CD138";
    public static final String DTH_SUMMARY_DOC_TYPE="CD757";//003
    public static final String OP_NOTES_DOC_TYPE="CD345";
    public static final String TRANSCONST_ACK_DOC_TYPE="CD17003";//001
    public static final String COCH_CERTI_DOC_TYPE="CD864";//004
     public static final String PREAUTH_ATTACH_DOC_TYPE="CD95";//008
      public static final String COLL_CARD_DOC_TYPE="CD764";
    public static final String PREAUTH_PNDATCH_DOC_TYPE="CD692";
    /*---------------End Attachment Types-------------------*/ 
    
    /*---------------Start Role Values-------------------*/
    public static final String ROLE_NWH="CD9";      
    public static final String ROLE_INSURANCE="CD8";
    public static final String ROLE_PANEL_DOC="CD301";
    public static final String ROLE_SRMEDOFCR="CD113";
    public static final String ROLE_COMMITTEE="CD13";
    public static final String ROLE_TRUST="CD7";
    public static final String ROLE_CEO="CD6";
    public static final String ROLE_CMO="CD220";
    public static final String ROLE_TECH_COMMITTEE="CD432";
    public static final String ROLE_CTD="CD212";
    public static final String ROLE_GM_MED="CD224";
    public static final String ROLE_MITHRA="CD10";
    public static final String ROLE_JEO_AS1="CD317";//004
    
    
    /*---------------End Role Values-------------------*/ 
    
    /*---------------Start Status Values-------------------*/
    public static final String CASE_REGISTERED="CD73";
    public static final String NWH_CNCL_REQ="CD110";
    public static final String PANELDOC_APPRVD="CD302";
    public static final String PANELDOC_REJ="CD303";
    public static final String COMMITTEE_APPRVD="CD101";
    public static final String COMMITTEE_REJ="CD102";
    public static final String COMMITTEE_PEND="CD103";
    public static final String PANELDOC_PEND="CD304";
    public static final String TRUST_REJECTED="CD82";
    public static final String CEO_REJECTED="CD88";
    public static final String CMO_REJECTED="CD347";
    public static final String CMO_APRVD="CD346";
    public static final String CMO_TERMINATION="CD483";
    public static final String CMO_CNCL="CD482";
    public static final String CEO_ENHANCE_APPRVD="CD430";
    public static final String CMO_CONSOLI_APPRV="CD84,CD81";
    public static final String CMO_CONSOLI_CNCL ="CD135,CD127";
    public static final String CMO_CONSOLI_TER="CD136,CD128";
    public static final String CMO_CONSOLI_REJ="CD82,CD88";
    public static final String ON_BED="CD438";
    public static final String AFTER_DISCHARGE="CD144";
    public static final String PATIENT_FEEDBACK="CD129";
    public static final String TRUST_CNCL="CD127";
    public static final String TRUST_TERMINATION="CD128";
    public static final String CEO_TERMINATION="CD136";
    public static final String CEO_CNCL="CD135";
    public static final String PREAUTH_UPDATED="CD75";//005
    public static final String PENDING_DOCS="CD691";//018
    /*---------------End Status Values-------------------*/ 
   /*---------------start session calls------------------*/
   public static final String COMMONSESSION="CommonSession";
   public static final String CASELETTERSSESSION="CaseLettersSession";//001
   /*-----------------End session calls------------------*/
   /*---------------start TABLE names------------------*/
   public static final String ASRIT_CASE_INSPECTION_ATTACH="asrit_case_inspection_attach";
   public static final String ASRIT_CASE_DOC_ATTACH="asrit_case_doc_attch";
   /*-----------------End TABLE names------------------*/
  public final static String CEO_BUFFER_AMT = "50000";
  public final static String ASRI_INSRD_AMT = "150000";
  public final static String ASRI_LIMITED_AMT = "200000";
  public final static String ASRI_COCH_LIMITED_AMT = "650000";
  public final static String DIS_COCHLEAR_ID = "S16";
  public final static String DIS_OLD_COCHLEAR_ID = "7";
  public final static String DIS_POLYTRAUMA_ID = "S15";
  public final static String DIS_OLD_POLYTRAUMA_ID = "6";
  public static final String PREV_CONSUME_HDR = "CASE NUMBER,CLAIM NUMBER,CLAIM STATUS,PATIENT_ID,PATIENT NAME,RATION CARD NUMBER,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,STATUS DATE,REG DATE,SURGERY/THERAPY DATE,DISCHARGE DATE,HOSPITAL NAME,AMOUNT,CATEGORY_NAME,SURGERY/THERAPY" ;
  public static final String PREV_CASES_HDR = "CASE NUMBER,CLAIM NUMBER,CLAIM STATUS,PATIENT_ID,PATIENT NAME,RATION CARD NUMBER,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,CATEGORY_NAME,STATUS DATE,REG DATE,HOSPITAL NAME,PACKAGE AMOUNT" ;
  public static final String PAT_DETS_LIST_HDR = "CASE NUMBER,RATION CARD NUMBER,PATIENT NAME,CLAIM STATUS,CLAIM NUMBER,HOSPITAL NAME,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,REG DATE,SURGERY/THERAPY DATE,DISCHARGE DATE,AMOUNT,PHASE,RENEWAL,SURGERY/THERAPHY" ;
  public static final String PAT_DETS_JOURN_LIST_HDR = "CASE NUMBER,RATION CARD NUMBER,PATIENT NAME,CLAIM STATUS,CLAIM NUMBER,HOSPITAL NAME,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,REG DATE,SURGERY/THERAPY DATE,DISCHARGE DATE,AMOUNT,PHASE,RENEWAL,JOURNALIST RENEWAL,SURGERY/THERAPHY" ;//002
  public static final String PAT_DETS_CMCO_LIST_HDR = "CASE NUMBER,RATION CARD NUMBER,PATIENT NAME,CLAIM STATUS,CLAIM NUMBER,HOSPITAL NAME,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,REG DATE,SURGERY/THERAPY DATE,DISCHARGE DATE,AMOUNT,PHASE,RENEWAL,CMCO RENEWAL,SURGERY/THERAPHY" ;//007
 // public static final String PASTHISTORY_CMCO_LIST_HDR = "PAST PRE-AUTHS,HOSPITAL,DATE,CLAIM PAID,STATUS OF CLAIM,PHASE,RENEWAL,CMCO RENEWAL,CATEGORY,PROCEDURE,PROCEDURE PERFORMED" ;  
 // public static final String PASTHISTORY_JOURN_LIST_HDR = "PAST PRE-AUTHS,HOSPITAL,DATE,CLAIM PAID,STATUS OF CLAIM,PHASE,RENEWAL,JOURNALIST RENEWAL,CATEGORY,PROCEDURE,PROCEDURE PERFORMED" ;
  //public static final String PASTHISTORY_LIST_HDR = "PAST PRE-AUTHS,HOSPITAL,DATE,CLAIM PAID,STATUS OF CLAIM,PHASE,RENEWAL,CATEGORY,PROCEDURE,PROCEDURE PERFORMED" ;
  public static final String PASTHISTORY_CMCO_LIST_HDR =  "CASE NO,NAME,REG DT,PHASE,POLICY PERIOD,CMCO POLICY PERIOD,PREAUTH STATUS,ONBED STATUS,CASE STATUS,CLAIM PAID COST,PKG PRICE,CATEGORY,SUB CATEGORY,THERAPY";
  public static final String PASTHISTORY_JOURN_LIST_HDR = "CASE NO,NAME,REG DT,PHASE,POLICY PERIOD,JOURNALIST POLICY PERIOD,PREAUTH STATUS,ONBED STATUS,CASE STATUS,CLAIM PAID COST,PKG PRICE,CATEGORY,SUB CATEGORY,THERAPY";
  public static final String PASTHISTORY_LIST_HDR = "CASE NO,NAME,REG DT,PHASE,POLICY PERIOD,PREAUTH STATUS,ONBED STATUS,CASE STATUS,CLAIM PAID COST,PKG PRICE,CATEGORY,SUB CATEGORY,THERAPY";
  public static final String SURGERY = "S15.1.1,S5.1.4" ;
  public static final String SURG_TO_RESTRCT_CLAIM = "M6.5" ;
  public static final String RENAL_MAIN_CATEGORY="S9";//009
  public static final String RENAL_SURG_LST = "S9.1.2,S9.1.3" ;
  public static final String RENAL_SELECT_SURG = "S9.5.1,S9.1.4" ;
  public static final String HEMO_CHK_DAYS = "14" ;
  public static final String COMPLAINT = "CD107" ;
  public static final String BLOCK_PREAUTH_HRS = "3" ;
  public static final String PREAUTHONE = "CD228,CD50,CD324,CD229,CD64,CD325,CD348,CD349,CD256,CD326,CD257,CD327,CD94,CD109,CD103,CD69,CD299,CD226,CD70,CD300,CD48,CD49,CD79,CD65,CD260,CD92,CD66,CD267,CD304,CD67,CD275,CD305,CD68,CD298,CD3,CD15,CD93,CD219,CD319,CD108,CD227,CD323,CD691," ;
  public static final String PREAUTHTWO = "CD7,CD13,CD14,CD113,CD6,CD220,CD5" ;
  public static final String PREAUTHTHREE = "CD116,CD117,CD115,CD261,CD307,CD308" ;
  public static final String PREAUTHFOUR = "CD261,CD212,CD9" ;
  public static final String PREAUTHFIVE = "CD317,CD318,CD258,CD158" ;
  public static final String DISTICT_HEADER="LH6";
  public static final String MANDAL_HEADER="LH7";
  public static final String VILLAGE_HEADER="LH8";
  public static final String HAMLET_HEADER="LH9";//012
  
  public static final String ATTACHMENT_FIELD_ATTACH_ACTION_USRS="CD270,CD198,CD278,CD199,CD210,CD15120,CD196,CD8,CD301,CD7,CD113,CD261,CD212,CD1500,CD6,CD224,CD452,CD116,CD2000,CD5,CD15264,CD15263,CD15262,CD15261,CD15260,CD12,CD276,CD209,CD561,CD15306,CD454,CD603,CD602,CD621,CD620,CD619,CD15291,CD15147,CD279,CD432,CD424,CD586,CD15200,CD451,CD1302,CD15531,CD15509,CD15510,CD114,CD306,CD217,CD15308,CD15653,CD15675";//added area manager-networks as given by jaishankar //added technical committee as given by Dr.ChandraSekhar//010 added PreauthExecutiveFB25001 //019//020 //022added District Manager
  public static final String ATTACHMENT_FIELD_VISIT_ATTACH="CD431,CD443,CD479,CD488,CD449,CD489,CD502";
  public static final String ASRIT_CASE_SURGERY_INVEST="ASRIT_CASE_SURGERY_INVEST";
  public static final String HC_DAYS_DIFFERENCE="6";
  public static final String SURG_COCH_SURG="S16.3.1";
  public static final String COCH_DISEASES="S16,7";
  public static final String COCH_SURG_ID="S16.1.1";
  public static final String MSG_FOR_MEMO="You have a MEMO in the mailbox. Please respond to the MEMO with all details. The response will be scrutinized by the Trust before activating the online preauthorization flow. However your hospital can continue registering inpatients and outpatients.";
  public static final String MSG_FOR_MEMO_REJECT="Your Response to a MEMO was not satisfactory.";
  public static final String MSG_FOR_NOTIFICATION="You have a NOTIFICATION in the mailbox. Please respond  to the NOTIFICATION with all details.The online preauthorization flow will be activated automatically after you respond with details. However your hospital can continue registering inpatients and outpatients.";
  public static final String MSG_FOR_CIRCULAR="You have a CIRCULAR in the mailbox. Please respond  to the CIRCULAR.The online preauthorization flow will be activated automatically after you respond. However your hospital can continue registering inpatients and outpatients.";
  public static final String  TOLLFREE_NO="104";
  public static final String COCH_CERTI_DOC_PATH="CochCerti";//004
  public static final String OFFICER_HOSP_COORD_LOGIN="US12441";//004
  public static final String EMPNL_BLOCK_PREAUTH="7";//005
  public static final String GENERAL_COMMUNICATION="CD638";//005
  public static final String SHOW_CAUSE="CD594";  //005
  //start 008
  public static final String OUTPATIENT="OP";  
  public static final String PAT_DETS_OP_LIST_HDR = "PATIENT NUMBER,RATION CARD NUMBER,PATIENT NAME,HOSPITAL NAME,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,COMPLAINT,DIAGNOSIS,CATEGORY,FINAL DIAGNOSIS,REG DATE,OP DATE,PHASE,RENEWAL" ;
  public static final String PAT_DETS_OP_JOURN_LIST_HDR = "PATIENT NUMBER,RATION CARD NUMBER,PATIENT NAME,HOSPITAL NAME,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,COMPLAINT,DIAGNOSIS,CATEGORY,FINAL DIAGNOSIS,REG DATE,OP DATE,PHASE,RENEWAL,JOURNALIST RENEWAL" ;
  public static final String PAT_DETS_OP_CMCO_LIST_HDR = "PATIENT NUMBER,RATION CARD NUMBER,PATIENT NAME,HOSPITAL NAME,AGE,GENDER_NAME,CASTE_NAME,DISTRICT NAME,MANDAL_NAME,COMPLAINT,DIAGNOSIS,CATEGORY,FINAL DIAGNOSIS,REG DATE,OP DATE,PHASE,RENEWAL,CMCO RENEWAL" ;
  //end 008
  public static final String CLAIM_NO_SUCCESS="is generated successfully.Aarogyamithra should note down the claim number in the helpdesk register.The Claim Number generated shall be used by the hospital authorities for any future references.";//011
  public static final String SATISFACTORY_LETTER_SURGERIES="S10.2.7,S10.2.8,S10.2.2,S10.2.3,S10.2.4,S10.5.1,S10.5.2";//015
  public static final String TAP_ID_PROOF="CD763";
  public static final String CARD_TYPE_T="T";
  public static final String EOTech_PC ="US7625";//015
  public static final String NWH_ACT_SUCCESS_MSG=". Take a printout of Preauthorisation Request Form (PRF) by clicking print icon in the last column of top/bottom of the form. Obtain the signature/thumb impression of the patient / guardian / Attendants / Relative and the signature and Stamp of the Treating doctor, Ramco, Aarogyamithra in the prescribed slots(Preauth Attachments) of PRF. Also put the seal of Network Hospital.After Obtaining signatures and stamps upload the scanned copy of the same in single PDF document in the Preauthorisation document attachments slot. For generating the PRF form press OK Button";//017  
  public static final String PENDING_STAT_SEQ="CD79,CD304,CD103,CD93,CD94,CD348,";
  public static final String PENDING_NAPRVL_SEQ="CD228,CD324,CD65,CD260,CD67,CD275,CD69,CD299,CD256,CD326,CD219,CD319,CD3,CD48,CD933,CD50,";
  public static final String PrincipalSecretary_No ="7000000000"; 
  public static final String DirectorOfHealth_No ="7000000000";
  public static final String BioEmrChkDisMainLst ="S13,S15,S1,S5"; //021
  public static final String PREAUTH_ATTACHMENTS="PreauthAttachments";
  public static final String SURG_Renal="S9";//023
  public static final String SURG_Cancer="S11,S12,S13";//023
  public static final String HOLD_STATUS = "HD1";
  public static final String CEO_PREAUTH_APPROVED_STATUS = "CD207";
  public static final String PTD_PREAUTH_APPROVED_STATUS = "CD8";
  public static final String MEDCO_PREAUTH_APPROVED_STATUS = "CD651";
}
