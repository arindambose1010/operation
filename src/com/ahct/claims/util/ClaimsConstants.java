package com.ahct.claims.util;

public class ClaimsConstants {
	public static final String EMPTY = "";
	public static final boolean BOOLEAN_FALSE = false;
	public static final boolean BOOLEAN_TRUE = true;
	public static final String SLASH = "/";
	public static final String Y = "Y";
	public static final String N = "N";
	public static final String R = "R";
	public static final String P = "P";
	public static final String F = "F";
	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final int ZERO_VAL = 0;
    public static final String Failure="Failure";
    public static final String LANG_ID="en_US";
    public static final String EO_TG_ADMIN_USER_ID= "USR252363";
    public static final String EO_AP_ADMIN_USER_ID= "US23762";
    public static final String CEO_AP_USER_ID="USR96634";
    public static final String CEO_USER_ID="UR83590";    //CEO User Id updated with bankers'Id
    public static final String CEO_GRP_ID="GP40";
    public static final String CEO_GRP="GP16";
    public static final String EO_ADMIN_GRP_ID="GP97";
    public static final String TDSPAY_GRP_ID="GP67";
    public static final String PAYNOW_BUTTON = "CD99";
    
	public static final String G = "G";
	public static final String TDS = "TDS";
	public static final String RF = "RF";
	public static final String GD = "GD";
	public static final String DS = "DS";
	public static final String MEDICAL_FLAG = "IPM"; //Added by namratha for medicla flag
	public static final String SURGICAL_FLAG = "IP"; //Added by namratha for medicla flag

	public static final String I = "I"; // for Insurance
	public static final String T = "T"; // for Trust
	public static final String C = "C"; // for CMCO

	public static final String INSURANCE_DEDUCTOR = "CD524"; // Combo value for INSURANCE
	public static final String TRUST_DEDUCTOR = "CD525"; // Combo value for  TRUST
	public static final String CMCO_DEDUCTOR = "CD526"; // Combo value for CMCO

	public static final String TDSAS2 = "TDSAS2";
	public static final String TDSEHS = "TDSEHS";
	public static final String APTDSEHS = "AP-TDSEHS";
	public static final String TGTDSEHS = "TG-TDSEHS";
	public static final String TGTDSJHS = "TG-WJHS-TDS";
	public static final String TDSCMO = "TDSCMO";
	public static final String TRUST  = "TRUST";
	public static final String APTRUST  = "AP-TRUST";
	public static final String TGTRUST  = "TG-TRUST";
	public static final String JHSTGTRUST  = "TG-WJHS-TRUST";
	public static final String SLASH_G = "/G";
	public static final String M = "M";
	public static final String SLASH_GD = "/GD";
	public static final String SLASH_TDS = "/TDS";
	public static final String SURPLUS_AMOUNT="1";
	public static final String FollowUp = "FollowUp";
	public static final String ErroneousClaim = "ErroneousClaim";

	public static final String SENT = "SENT";
	public static final String TransReadyStatus="READY";
	public static final String TransPaidStatus="PAID";
	public static final String CLAIM_SENT_RMK = "Claim Sent For Payment";
	public static final String CLAIM_READY_RMK = "Claim Ready For Payment";
	public static final String CLAIM_SENT_BANK_RMK="Claim sent to bank for payment";
	public static final String CLAIM_PAY_DONE="Payment Done";
	public static final String CLAIM_ACKNOWLEDGED="Acknowledgement Given By Bank";
	
	public static final String CLAIM_PAID="CD51";
	public static final String CLAIM_REJ_BANK="CD98";
	public static final String CLAIM_READY_REJ_BANK="CD141";
	public static final String CLAIM_READY_BANK="CD140";
	public static final String CLAIM_SURPLUS_TDS_DED="CD157";
	public static final String CLAIM_SURPLUS_TDS_DED_SKIPPED="CD158";
	public static final String CLAIM_ERR_READY_REJ_BANK="CD143";
	public static final String CLAIM_ERR_READY_BANK="CD142";
	public static final String CLAIM_FP_READY_REJ_BANK="CD145";
	public static final String CLAIM_FP_READY_BANK="CD144";	
	public static final String CLAIM_ACK_REC="CD193";
	public static final String CLAIM_READY_PAYMENT = "CD103";
	public static final String CLAIM_ERR_SENT="CD116";
	public static final String CLAIM_ERR_REJ="CD117";
	public static final String CLAIM_FP_SENT="CD105";
	public static final String CLAIM_SENT="CD94";
	public static final String CLAIM_FORM_PAYMENT="CD125";
	public static final String FPCLAIM_FORM_PAYMENT="CD126";
	public static final String ERRCLAIM_FORM_PAYMENT="CD127";
	public static final String FPCLAIM_REJ_BANK="CD108";
	public static final String FPCLAIM_PAID="CD194";
	public static final String FPCLAIM_ACK="CD195";
	public static final String ERRCLAIM_PAID="CD196";
	public static final String ERRCLAIM_ACK="CD197";
	
	public static final double ZERO_DBL_VAL = 0;
	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String FOUR = "4";
	public static final String FIVE = "5";
	public static final String TDS_HOSP_PERCENT = "90";
	public static final String TDS_PERCENT = "10";
	public static final String RF_HOSP_PER="80";
	public static final String RF_PER="20";
	
	public static final String ERR_CLAIM_PAYMENT="ERR CLAIMPAYMENT";
	public static final String TDS_PAYMENT = "TDS PAYMENT";
	public static final String RF_PAYMENT = "RF PAYMENT";
	public static final String CLAIMPAYMENT = "CLAIMPAYMENT";
	public static final String ERR_CLAIM = "ERR CLAIM";
	public static final String FOLLOW_UP = "FOLLOW-UP";
	public static final String FOLLOWUP_PAYMENT  = "FOLLOW-UP PAYMENT";
	public static final String FOLLOWUP_CLAIM = "FOLLOW-UP CLAIM";
	public static final String COCHLEAR_FOLLOWUP_CLAIM = "COCHLEAR FOLLOW-UP CLAIM";
	
	public static final String MSG_0="Claim Of Hospital Was Not Processed Due To Unavailability of Bank Details";
	public static final String MSG_2="Claim Of Hospital Was Not Processed Due To Unavailability of Hospital Details";
	public static final String MSG_1="The normal time taken for your current transactions is 2-4 hours  and for RTGS or NEFT transactions  it will take 1 to 4 days based on RBI Instructions ";
	public static final String MSG_3="Cannot be Uploaded";
	public static final String MSG_4="Selected claims sent back to CH for Reverification";
	public static final String MSG_5="Selected claims Verified successfully";
	public static final String NOT_Auth_MSG = "You are not authorized to view.";
	public static final String CLAIM_VALID_MSG = "Cannot initiate claims for this case.Claim will be initiated after 11 days from discharge updated date.";
	public static final String CLAIM_NOTINIT = "Claim not initiated for this case";
	public static final String CLAIM_DISSPEND = "Cannot process claims until discussed cases are cleared.";
	public static final String CLAIM_FLAGED_MSG = "Case has been flagged. Need to deflag the case by DC to process further.";
	public static final String CLAIM_MONEYFLAGED_MSG = "Case has been flagged under MoneyCollection. Need to deflag the case by DC to raise the claim for the case";
	public static final String CLAIM_HOSP_SUSPENSION_MSG = "Please resolve the EDC action to initiate Claims.";
	public static final String CLAIM_CB_HOSP_SUSPENSION_MSG="Claims process has been blocked because the hospital is under Claims Blockage status as Medco is not responding";
	public static final String CLAIM_SP_HOSP_SUSPENSION_MSG="Claims process and Payments have been blocked as the hospital is under Suspension";
	public static final String CLAIM_CP_HOSP_SUSPENSION_MSG="Claims process and Payments have been blocked as the hospital is under Continous Suspension";
	public static final String CLAIM_CBP_HOSP_SUSPENSION_MSG="Claims process and Payments have been blocked because the hospital is under Claims Blockage status as Medco is not responding";
	public static final String CLAIM_HOSP_SUSPENSION_MSG_NULL="You are not authorised to view this page due to the Hospitals Inactive Status to raise claims";
	
	public static final String CLAIM_FILE_FLAG="EH";
	public static final String CLAIM_FILE_FLAG_JHS="JH";
	public static final String CLAIM_CLIENT_CODE="EHSN";
	public static final String CLAIM_CLIENT_CODE_JHS="JHSN";
	public static final String CLAIM_JHS_CLIENT_TG_CODE="JHSNTG";
	public static final String CLAIM_CLIENT_AP_CODE="EHSNAP";
	public static final String CLAIM_CLIENT_TG_CODE="EHSNTG";
	
	public static final String ERRCLAIM_CLIENT_CODE="EHSE";
	public static final String ERRCLAIM_CLIENT_AP_CODE="EHSEAP";
	public static final String ERRCLAIM_CLIENT_TG_CODE="EHSETG";
	public static final String FPCLAIM_CLIENT_CODE="EHSF";
	public static final String FPCLAIM_CLIENT_AP_CODE="EHSFAP";
	public static final String FPCLAIM_CLIENT_TG_CODE="EHSFTG";
	public static final String CHRONIC_CLAIM_CLIENT_CODE="EHSC";
	public static final String CHRONIC_CLIENT_AP_CODE="EHSCAP";
	public static final String CHRONIC_CLIENT_TG_CODE="EHSCTS";
	public static final String CLAIM_CLIENT_AP_ACCNO="62313018139";
	public static final String CLAIM_JHS_CLIENT_TG_ACCNO="62484595607";
	public static final String CLAIM_CLIENT_TG_ACCNO="62344397516";
	public static final String ERRCLAIM_CLIENT_AP_ACCNO="62313018139";
	public static final String ERRCLAIM_CLIENT_TG_ACCNO="62344397516";
	public static final String FPCLAIM_CLIENT_AP_ACCNO="62313018139";
	public static final String FPCLAIM_CLIENT_TG_ACCNO="62344397516";
	public static final String CLAIMTDS_JHS_CLIENT_AP_CODE="JHSTDSTG";
	public static final String CLAIMTDS_CLIENT_CODE="EHSTDS";
	public static final String CLAIMTDS_CLIENT_CODE_JHS="JHSTDS";
	public static final String CLAIMTDS_CLIENT_AP_CODE="EHSTDSAP";
	public static final String CLAIMTDS_CLIENT_TG_CODE="EHSTDSTG";
	public static final String FPTDS_CLIENT_CODE="EHSFTDS";
	public static final String FPTDS_CLIENT_AP_CODE="EHSFTDSAP";
	public static final String FPTDS_CLIENT_TG_CODE="EHSFTDSTG";
	public static final String ERRTDS_CLIENT_CODE="EHSETDS";
	public static final String ERRTDS_CLIENT_AP_CODE="EHSETDSAP"; 
	public static final String ERRTDS_CLIENT_TG_CODE="EHSETDSTG"; 
	public static final String[] followUpStatus={"CD62","CD63","CD64","CD65","CD66","CD67","CD68","CD69","CD70","CD104","CD105","CD106","CD107","CD131","CD132","CD511","CD512","CD513","CD514","CD520","CD523","CD6325","CD6326","CD180"};
	public static final String[] CHRONICStatus={"CD401","CD402","CD403","CD404","CD405","CD406","CD407","CD408","CD409","CD410","CD411","CD412","CD413","CD414","CD415","CD550","CD551","CD552","CD553","CD554","CD555","CD556","CD557"};
	public static final String[] claimWorkFlowStatus={"CD40","CD400","CD420","CD421","CD422","CD423","CD424","CD425","CD426","CD427","CD41","CD42","CD43","CD44","CD45","CD46","CD47","CD48","CD49","CD50","CD21","CD51","CD52","CD53","CD54","CD55","CD56","CD57","CD58","CD59","CD60","CD93","CD95","CD96","CD94","CD109","CD116","CD117","CD119","CD128","CD149","CD150","CD151","CD152","CD153","CD154","CD155","CD156","CD193","CD98","CD400","CD424","CD420","CD515","CD516","CD517","CD518","CD519","CD521","CD522","CD1111","CD1112","CD1113","CD1114","CD1115","CD196","CD37","CD92","CD1351","CD1352","CD1116","CD3515","CD3516","CD360","CD755","CD758","CD759","CD760","CD761","CD1539","CD1540","CD1543","CD1544","CD1325","CD1326"};
	public static final String[] claimStatues = {"CD93","CD94","CD96","CD51","CD1411"};
	public static final String[] chronicStatues = {"CD412","CD416","CD417","CD418"};
	public static final String[] claimStatuesRej = {"CD98"};
	public static final String[] ErrClaimStatues = {"CD109","CD116","CD196"};
	public static final String[] ErrClaimStatuesRej = {"CD117"};
	public static final String[] followUpStatues = {"CD104","CD105","CD107","CD194","CD280","CD290"};
	public static final String[] followUpStatuesRej = {"CD108"};
	public static final String[] followUpStatuesCH = {"CD106","CD65","CD6325","CD180"};
	public static final String[] claimsCEOSentBackStatuses = {"CD515","CD516","CD517","CD518","CD95","CD519","CD521"};
	public static final String[] preauthCEOSentBackStatuses = {"CD524","CD534","CD526","CD527","CD528","CD529","CD530","CD531","CD532","CD533","CD536"};
	public static final String[] followupCEOSentBackStatuses = {"CD511","CD512","CD513","CD514","CD106","CD520","CD523"};
	public static final String[]  preauthCEODentalSentBackStatuses={"CD2003"};
	public static final String[]  preauthCEOChronicSentBackStatuses={"CD550","CD551","CD552","CD553","CD554","CD555","CD556"};
	public static final String DOT = ".";
	public static final String TRANSACKSTATUS="ACK";
	public static final String TRANSREJSTATUS="REJ";
	public static final String REVFUND_REMARK="Revolving fund adjustment (10%)";
	public static final String CLOSED="CLOSED";	
	public static final String ALREADYMESSAGE="Case has already been updated by other User.";
	
	
	public static final String CHRONICOP = "CHRONICOP";
	public static final String CHRONICOP_CLAIM = "CHRONIC-CLAIM";
	
	//AHC Claims constants
	public static final String AHC_CLAIM_FORM_PAYMENT="AHC Claim";
	public static final String AHC_CLAIM_READY_BANK="CD389";
	public static final String AHC_CLAIM_SENT="CD386";
	public static final String AHC_CLAIM_PAID="CD390";
	public static final String AHC_CLAIM_REJ_BANK="CD391";
	public static final String AHC_CLAIM_ACK_REC="CD392";
	public static final String AHC_CLAIM_REJ_READY_BANK="CD393";
	public static final String AHC_CLAIM_CLIENT_CODE="EHSAH";
	public static final String AHC_CLAIM_CLIENT_TG_CODE="EHSAHTG";
	public static final String AHC_CLAIM_CLIENT_AP_CODE="EHSAHAP";
	public static final String AHC_CLAIM_CLIENT_AP_ACCNO="62313018139";
	public static final String AHC_CLAIM_CLIENT_TG_ACCNO="62344397516";
	
	public static final String PREAUTH_SURGICAL="S";
	
	
	//chronic op Claims constants
	public static final String CHRONIC_CLAIM_FORM_PAYMENT="CHRONIC Claim";
	public static final String CHRONIC_CLAIM_READY_BANK="CD417";
	public static final String CHRONIC_CLAIM_SENT="CD428";
	public static final String CHRONIC_CLAIM_PAID="CD418";
	public static final String CHRONIC_CLAIM_REJ_BANK="CD429";
	public static final String CHRONIC_CLAIM_ACK_REC="CD430";
	public static final String CHRONIC_CLAIM_REJ_READY_BANK="CD431";
	public static final String CHRONIC_CLAIM_CLIENT_TG_CODE="CHRONICEHSNTG";
	public static final String CHRONIC_CLAIM_CLIENT_AP_CODE="CHRONICEHSNAP";
	
	public static final String preauth="preauth";
	public static final String preauthjournal="preauthJournal";
	public static final String claim="claim";
	public static final String claimJournal="claimJournal";
	public static final String NABH_USERS_GRP="GP190";
	
	//Act-types for claim payments
	
	public static final String  ERRCLAIM_CLIENT_TG_ACTTYPE="TG-TRUSTCLIENT";
	public static final String  CLAIM_CLIENT_TG_ACTTYPE="TG-TRUSTCLIENT";
	public static final String  CLAIM_JHS_CLIENT_TG_ACTTYPE="TG-WJHS-TRUST";
	public static final String  FPCLAIM_CLIENT_TG_ACTTYPE="TG-TRUSTCLIENT";
	
	
	//SBI Claim Payments
	public static final String NPCI_USER_ID="36ALVSB";
	public static final String NPCI_USER_NAME="DIRECT OF PENSIONS D";
	public static final String DISTRICT="HYDERABAD";
	public static final String STATE="TELANGANA";
	public static final String EMAIL="accounts@aarogyasri.gov.in";
	public static final String BANK_NAME="STATE BANK OF INDIA";
	public static final String CLIENT_IFSC="SBIN0020077";
	
}