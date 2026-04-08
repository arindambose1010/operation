package com.ahct.preauth.dao;

//import java.util.ArrayList;
//import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import java.util.List;
//import java.util.Map;







import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.constants.ASRIConstants;
import com.ahct.preauth.util.PreauthConstants;
//import com.ahct.preauth.vo.PreauthVO;
import com.ahct.preauth.vo.SQLPreauthTransVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.AsrimCombo;
import com.ahct.model.AsrimComboId;
import com.tcs.framework.configuration.ConfigurationService;
//import com.ahct.model.AsritBiomFinger;
//import com.ahct.model.AsritCasePatientBiometric;
//import com.ahct.model.AsritCasePatientBiometricId;
//import com.ahct.model.AsritErroneousClaim;
//import com.ahct.model.EhfCase;
//import com.ahct.model.EhfCaseDocAttch;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
//import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class CasesApprovalDAOImpl implements CasesApprovalDAO
{
	static final Logger gLogger = Logger.getLogger(CasesApprovalDAOImpl.class);
	
	GenericDAO genericDao;

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	String sdfFromDate = null;
	String sdfToDate = null;
	String database=config.getString("Database");

	 /*public Map getCaseDtls(HashMap lparamMap)
	 {
        HashMap lResMap=new HashMap();//025
        PreauthVO preVO = new PreauthVO();       
        String lFlag=null;
        ArrayList lNewLst=null;        
        String old_Renewal="";
        String phase_Id="";
        String Current_Renewal ="";
        try
        {                                          
                preVO.setCaseId((String)lparamMap.get("caseId"));
                preVO.setPayee((String)lparamMap.get("payee"));
                String[] args=new String[1];
                //start 032
                StringBuffer queryBuff=new StringBuffer();
                queryBuff.append(" SELECT to_char(P.CRT_DT,'dd/mm/yyyy HH12:MI:SS am') CRT_DT,A.CASE_STATUS,A.CLAIM_NO,P.PATIENT_ID, ");
                queryBuff.append("        to_char(A.LST_UPD_DT,'dd/mm/yyyy HH12:MI:SS am') LST_UPD_DT,B.CMB_DTL_NAME CASE_STATUS_DESC, ");
                queryBuff.append("        CASE WHEN A.SCHEME_ID = 1 THEN 'AS-I' WHEN A.SCHEME_ID = 2 THEN 'AS-II' END SCHEMEDESC, ");
                queryBuff.append("        A.SCHEME_ID,A.PHASE_ID,A.phase_renewal,A.RENEWAL,A.DEDUCTOR_TYPE ");//098,022
                queryBuff.append("   FROM EHF_CASE A, ASRIT_PATIENT P, ASRIM_COMBO B ");//, ASRIM_PHASE_DURATION C ");
                queryBuff.append("  WHERE A.CASE_STATUS = B.CMB_DTL_ID AND P.PATIENT_ID = A.CASE_PATIENT_NO AND A.CASE_ID = ? "); //A.PHASE_ID = C.PHASE_ID AND ");
                args[0]=preVO.getCaseId();                
                List<SQLPreauthTransVO> list= genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuff.toString(), args);
                if(!list.isEmpty() && list!=null)
                {
                	SQLPreauthTransVO sQLPreauthTransVO=list.get(0);
                	preVO.setPatCrtDt(sQLPreauthTransVO.getCRT_DT());
                  String caseStatus=sQLPreauthTransVO.getCASE_STATUS();
                    preVO.setCaseStatusId(caseStatus);
                      if(sQLPreauthTransVO.getCLAIM_NO() != null) 
                    preVO.setClaimNo(sQLPreauthTransVO.getCLAIM_NO());
            
                    preVO.setPatientID(sQLPreauthTransVO.getPATIENT_ID());
            
                    preVO.setCaseStatusDt(sQLPreauthTransVO.getLST_UPD_DT());
            
                    preVO.setCaseStatusDesc(sQLPreauthTransVO.getCASE_STATUS_DESC());
            
                    preVO.setSchemeDesc(sQLPreauthTransVO.getSCHEMEDESC());
            
                    preVO.setScheme(sQLPreauthTransVO.getSCHEME_ID());
            
                    preVO.setPhase(sQLPreauthTransVO.getPHASE_ID().toString());
            
                    preVO.setRenewal(sQLPreauthTransVO.getPHASE_RENEWAL());//098
            
                    preVO.setCaseRenewal(sQLPreauthTransVO.getRENEWAL());                 
                    preVO.setDeductorType(sQLPreauthTransVO.getDEDUCTOR_TYPE()); //022 
                }
                preVO = getRenewalFlag(preVO);
                preVO= getFieldAttachmentFlag(preVO);//001
            lFlag=(String)lparamMap.get("flag") ;
            //Start Chandu
          if(("CD10".equalsIgnoreCase((String)lparamMap.get("userRole")) && "CD75".equalsIgnoreCase(preVO.getCaseStatusId())  &&("PRE".equalsIgnoreCase((String)lparamMap.get("Status")) || "HEMO".equalsIgnoreCase((String)lparamMap.get("Status")) || "COCH".equalsIgnoreCase((String)lparamMap.get("Status")))) || ("CD9".equalsIgnoreCase((String)lparamMap.get("userRole")) && "CD73".equalsIgnoreCase(preVO.getCaseStatusId())) )
            {
            if(preVO.getClosedPhase() == 1)
            {
                args=new String[1];
                queryBuff=new StringBuffer();
                queryBuff.append(" select  ac.phase_id PHASE_ID,ac.phase_renewal PHASE_RENEWAL  from EHF_case ac where ac.case_id=? ");
                args[0]=preVO.getCaseId();
                List<SQLPreauthTransVO> list1=genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuff.toString(),args);
                
                if(!list1.isEmpty() && list1!=null) 
                {
                    old_Renewal = list1.get(0).getPHASE_RENEWAL();
                    phase_Id = list1.get(0).getPHASE_ID().toString();
                }
                args=new String[1];
                queryBuff=new StringBuffer();
                queryBuff.append("select max(renewal) RENEWAL from asrim_phase_duration where phase_id = ? ");
                args[0]=phase_Id;
                List<SQLPreauthTransVO> list2=genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuff.toString(),args);
                  if(!list2.isEmpty() && list2!=null)
                  {
                      Current_Renewal = list2.get(0).getRENEWAL();
                  }                  
                
                if(!old_Renewal.equalsIgnoreCase(Current_Renewal)) 
                {
//            Updating Patient table.
                	  args=new String[2];  
                	  queryBuff=new StringBuffer();
                      queryBuff.append("update asrit_patient ap SET ap.Renewal = ? WHERE ap.patient_id = ? ");                      
                      args[0]=Current_Renewal;
                      args[1]=preVO.getPatientID();
                      List<SQLPreauthTransVO> list3=genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuff.toString(),args);
                      
//            Updating Case table.                        
                      queryBuff=new StringBuffer();
                      args=new String[3];  
                      queryBuff.append("update EHF_case ac SET ac.renewal = ?, ac.phase_renewal=? WHERE ac.case_id = ? ");
                      args[0]=Current_Renewal;
                      args[1]=Current_Renewal;
                      args[2]=preVO.getCaseId();
                      List<SQLPreauthTransVO> list4=genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuff.toString(),args);
                      
//              Inserting in asrit_patient_phase_renewal table.                        
                    queryBuff=new StringBuffer();
                    args=new String[5];  
                    queryBuff.append("insert into asrit_patient_phase_renewal (patient_id,phase_id,renewal,org_renewal,rnwl_upd_dt,status)values(?,?,?,?,sysdate,?) ");
                    args[0]=preVO.getPatientID();
                    args[1]=phase_Id;
                    args[2]=Current_Renewal;
                    args[3]=old_Renewal;
                    args[4]=preVO.getCaseStatusId();
                    List<SQLPreauthTransVO> list5=genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuff.toString(),args);
                }
                
                preVO.setClosedPhase(0);
            }
            }
            //End Chandu
            if(lparamMap.get("flag") != null && ASRIConstants.FLAG_Y.equalsIgnoreCase(lFlag)  )//061
            {   
                preVO=getPatientCommonDtls(preVO);
                
              lNewLst=(ArrayList)getPatientCases(preVO);//109
                if(lNewLst != null)
                    preVO.setAssociatedCases(lNewLst);
                preVO=getPreDates(preVO);
                preVO=getHemoCases(preVO);//006
                preVO=getRenalCases(preVO);//006
                
                if(lparamMap.get("Status") != null)
                {
                    String lStrStatus = (String)lparamMap.get("Status");
                    if(!(PreauthConstants.ROLE_CEO).equalsIgnoreCase((String)lparamMap.get("userRole")) && (ASRIConstants.STATUS_CL.equalsIgnoreCase(lStrStatus) && !(PreauthConstants.ROLE_NWH).equalsIgnoreCase((String)lparamMap.get("userRole"))) || ASRIConstants.STATUS_PRE.equalsIgnoreCase(lStrStatus) || ASRIConstants.STATUS_COCH.equalsIgnoreCase(lStrStatus) || ASRIConstants.STATUS_HEMO.equalsIgnoreCase(lStrStatus)) //066
                    {
                        preVO =  getUserBlockedInfo(lparamMap,preVO);
                    }
                }
                //end 051
                //start 060
                 preVO=getMedMgntFlg(preVO);
                 preVO=getProcessFlag(preVO);//107
                 preVO=getErroneousStatus(preVO);//004
                //end 060             
            }
            preVO = getTelDtls(preVO);//013
            //end 025
             if(PreauthConstants.PENDING_DOCS.equalsIgnoreCase(preVO.getCaseStatusId()))//Start Id:019
             {
                 String prevDtlName=getPrevDtlForPending(preVO.getCaseId());
                 if(!ASRIConstants.EMPTY_STRING.equalsIgnoreCase(prevDtlName)) {
                   preVO.setCaseStatusDesc(prevDtlName.split(",")[1]);
               }
             
             }//End Id:019
             preVO=getPatientBiometricDisplay(preVO);
             preVO=getPatientBiomEmrChkRmks(preVO);//023             
           lResMap.put("PreauthVO",preVO);//016
           lResMap.put("AttachLst",getAttachmentNamesLst());
            String feedbackCnt=getPatientFeedbackFlag(preVO);
            lResMap.put("PatientFeedbackCnt",feedbackCnt);
        }
        catch(Exception e)
        {
        	gLogger.error("Exception in method getCaseDtls() of casesApprovalDaoImpl->"+e.getMessage());     //028
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }        
        return lResMap;
}*/

	 /*
	  * getRenewalFlag
	  */
	 
	 /* private PreauthVO getRenewalFlag(PreauthVO preVO)
	  {	   
	   try
	   {
	     StringBuffer queryBuffer = new StringBuffer();
	     queryBuffer.append("     select flag FLAG,apd.start_dt START_DT,apd.end_dt END_DT,case when apd.end_dt < sysdate then 1 else 0 end close_phase CLOSE_PHASE");
	     queryBuffer.append("       from asrim_phase_duration apd ");
	     queryBuffer.append("      where apd.phase_id = ? and apd.scheme_id = ? and ");
	     queryBuffer.append("      apd.renewal=? ");	     
	     String[] args=new String[3];
	     args[0]=preVO.getPhase();
	     args[1]=ASRIConstants.INTEGER_ONE_STRING;
	     args[2]=preVO.getRenewal();
	     List<SQLPreauthTransVO> list1=genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuffer.toString(),args);
	     
	     if(!list1.isEmpty() && list1!=null)
	     {
	       if(list1.get(0).getFLAG()!=null)
	         preVO.setRenewalFlag(list1.get(0).getFLAG());
	       if(list1.get(0).getSTART_DT()!=null)
	         preVO.setPhaseStDt((ASRIConstants.ddmmyyyy).format(list1.get(0).getSTART_DT()) + " "+ (ASRIConstants.hhmmss).format(list1.get(0).getSTART_DT()));
	       if(list1.get(0).getEND_DT()!=null)
	         preVO.setPhaseEdDt((ASRIConstants.ddmmyyyy).format(list1.get(0).getEND_DT()) + " "+ (ASRIConstants.hhmmss).format(list1.get(0).getEND_DT()));
	       if(list1.get(0).getCLOSE_PHASE()!=null)
	         preVO.setClosedPhase(Integer.parseInt(list1.get(0).getCLOSE_PHASE()));
	     } 	   
	   }  
	   catch(Exception e)
	   {
		   gLogger.error("Exception in method getRenewalFlag() of casesApprovalDaoImpl->"+e.getMessage());     //028
	   }	   
	  return preVO;
	  }
	  */
	  /*
	   * getFieldAttachmentFlag
	   
	   private PreauthVO getFieldAttachmentFlag(PreauthVO preVO) 
	   {	           
	       try
	       {
	           StringBuffer lStrBuffer = new StringBuffer();
	           String[] args=new String[1];
	           lStrBuffer.append(" select case ");
	           lStrBuffer.append(" when count(1) > 0 then 'TRUE' ");
	           lStrBuffer.append(" else 'FALSE' end flag ");
	           lStrBuffer.append(" from asrit_case_inspection_attach acia ");
	           lStrBuffer.append(" where acia.case_id=? ");
	           args[0]=preVO.getCaseId();
	           List<SQLPreauthTransVO> list1=genericDao.executeSQLQueryList(SQLPreauthTransVO.class,lStrBuffer.toString(),args);
	           if(list1!=null && !list1.isEmpty())
	           {
	               preVO.setFieldAttachFlag(list1.get(0).getCASE());
	           }
	       }	       
	       catch (Exception e) 
	       {
	    	   gLogger.error("Exception in method getFieldAttachmentFlag() of casesApprovalDaoImpl->" +e.getMessage());
	       } 	       
	       return preVO;   
	   }*/
	   
	   /*
	    * getPatientCommonDtls
	    */	   
	/*   private PreauthVO getPatientCommonDtls(PreauthVO lPreVO)//025
	   {
	     StringBuffer queryBuff=new StringBuffer();//030
	     HashMap lQueryVal=new HashMap();	     
	     int licnt=0;
	     try
	     {
	         queryBuff.append("   select ap.first_name || ' ' || ap.middle_name || ' ' || ap.last_name PATIENT_NAME, "); 
	         queryBuff.append("          ap.ration_card_no RATION_CARD,ac.case_status CASE_STATUS_ID, "); 
	         queryBuff.append("          (Decode(ap.AGE, '0', '', null, '', '', '', ap.AGE || 'Years') || ");
	         queryBuff.append("          Decode(ap.age_months,'0','',null,'','','',ap.age_months || 'Months') || ");
	         queryBuff.append("          Decode(ap.age_days, '0', '', null, '', '', '', ap.age_days || 'Days')) AGE, ");
	         queryBuff.append("          (case when ap.child_yn = 'Y' then gender.cmb_dtl_name || '(Child)' else gender.cmb_dtl_name end) GENDER, ");
	         queryBuff.append("          ap.patient_ipop_no PATIENT_IPNO, ");
	         queryBuff.append("          ap.contact_no CONTACT_NO,status.cmb_dtl_name CASE_STATUS_DESC, ");
	         queryBuff.append("          ac.case_no CASE_NO,caste.cmb_dtl_name CASTE, ");
	         queryBuff.append("          ap.addr1 ADDRESS1, ");
	         queryBuff.append("          ap.addr2 ADDRESS2,ap.pin_code PINCODE,to_char(ap.reg_hosp_date,'dd/mm/yyyy HH12:MI:SS am') PAT_REG_DATE, ");//018
	         queryBuff.append("          to_char(ac.case_regn_date,'dd/mm/yyyy HH12:MI:SS am') REG_HOSP_DATE,ac.claim_no CLAIM_NO, ");
	         queryBuff.append("          hamlet.loc_desc HAMLET, ");
	         queryBuff.append("          village.loc_desc VILLAGE, ");
	         queryBuff.append("          mandal.loc_desc MANDAL, ");
	         queryBuff.append("          district.loc_desc DISTRICT, ");
	         queryBuff.append("          ap.patient_id PATIENT_ID, ");
	         queryBuff.append("          phc.phc_name PHC_NAME, ");
	         queryBuff.append("          ap.ref_card_no REF_CARD_NO, ");
	         queryBuff.append("          case when src_registration = 'D' then 'Direct' ");
	         queryBuff.append("               when src_registration = 'P' then 'PHC' ");
	         queryBuff.append("               when src_registration = 'MC' then 'Health Camp' ");
	         queryBuff.append("               when src_registration = 'CMO' then 'Cheif Minister Office' "); 
	         queryBuff.append("               else src_registration end SRC_REGISTRATION, ");
	         queryBuff.append("          ac.case_hosp_code HOSP_CODE, ");
	         queryBuff.append("          ah.hosp_name HOSP_NAME, ");
	         queryBuff.append("          adm.dis_main_name DIS_MAIN_NAME, ");
	         queryBuff.append("          ac.phase_id PHASE, ");
	         queryBuff.append("          ac.phase_renewal RENEWAL, ");//098
	         queryBuff.append("          ac.scheme_id SCHEME, ");
	         queryBuff.append("          ac.blocked_usr BLOCKED_USER, ");
	         queryBuff.append("          ah.hosp_disp_code HOSP_DISP_CODE,ah.hosp_type HOSP_TYPE, ");//022
	         queryBuff.append("          ah.hosp_addr1 || ' ' || ah.hosp_addr2 HOSP_ADRESS, ");
	         queryBuff.append("          ah.hosp_contact_no HOSP_CONTACT_NO, ");
	         queryBuff.append("          to_char(ap.crt_dt,'dd/mm/yyyy HH12:MI:SS am') PAT_CRT_DATE, ");
	         queryBuff.append("          to_char(ac.crt_dt,'dd/mm/yyyy HH12:MI:SS am') CASE_CRT_DATE, ");
	         queryBuff.append("          ap.card_type CARD_TYPE,to_char(ac.LST_UPD_DT,'dd/mm/yyyy HH12:MI:SS am') CASE_STATUS_DATE, ");
	         queryBuff.append("          case when asm.surgery_desc is not null then asm.surgery_desc || '( ' || asm.surg_disp_code || ' )'  else '' end SURGERY_NAME ");//012
	         queryBuff.append("          ,ac.journalist_renewal JOURNALIST_RENEWAL,ac.cmco_renewal CMCO_RENEWAL ");//006 , 008
	         queryBuff.append("     from asrit_patient      ap, ");
	         queryBuff.append("          EHF_case         ac, ");
	         queryBuff.append("          asrim_hospitals    ah, ");
	         queryBuff.append("          asrit_case_surgery acs, ");
	         queryBuff.append("          asrim_combo        gender, ");
	         queryBuff.append("          asrim_locations    district, ");
	         queryBuff.append("          asrim_locations    mandal, ");
	         queryBuff.append("          asrim_locations    village, ");
	         queryBuff.append("          asrim_locations    hamlet, ");
	         queryBuff.append("          asrim_phc          phc, ");
	         queryBuff.append("          ehfm_specialities adm, ");
	         queryBuff.append("          asrim_combo        status, ");
	         queryBuff.append("          asrim_combo        caste, ");
	         queryBuff.append("          asrim_surgery      asm ");
	         queryBuff.append("    where ac.case_id = ? and ap.patient_id = ac.case_patient_no and  ");
	         lQueryVal.put(++licnt, lPreVO.getCaseId());
	         queryBuff.append("          ac.case_hosp_code = ah.hosp_id and ac.case_id = acs.case_id(+) and  ");
	         queryBuff.append("          acs.isapproved(+) = ? and gender.cmb_dtl_id = ap.gender and  ");
	         lQueryVal.put(++licnt, ASRIConstants.FLAG_Y);
	         queryBuff.append("          hamlet.loc_id = ap.hamlet_code and hamlet.loc_hdr_id = ? and  ");
	         lQueryVal.put(++licnt, lPreVO.getHamletHdr());
	         queryBuff.append("          village.loc_id = ap.village_code and village.loc_hdr_id = ? and  ");
	         lQueryVal.put(++licnt, lPreVO.getVillageHdr());
	         queryBuff.append("          mandal.loc_id = ap.mandal_code and mandal.loc_hdr_id = ? and  ");
	         lQueryVal.put(++licnt, lPreVO.getMandalHdr());
	         queryBuff.append("          district.loc_id = ap.district_code and district.loc_hdr_id = ? and  ");
	         lQueryVal.put(++licnt, lPreVO.getDistrictHdr());
	         queryBuff.append("          phc.phc_id(+) = ap.phc_code and phc.phc_mandal_cd(+) = ap.mandal_code and  ");
	         queryBuff.append("          status.cmb_dtl_id = ac.case_status and caste.cmb_dtl_id(+) = ap.caste and  ");
	         queryBuff.append("          adm.dis_main_id(+) = acs.dis_main_code and asm.surgery_id(+) = acs.surgery_code  ");
	             //030         
	         String[] args=new String[licnt]; 
	         for (int i = 1; i <= licnt; i++) 
	          {
	              args[i-1]=lQueryVal.get(i).toString();
	          }	          
	         List<SQLPreauthTransVO> list1=genericDao.executeSQLQueryList(SQLPreauthTransVO.class, queryBuff.toString(),args);
	         SQLPreauthTransVO sQLPreauthTransVO=null;
	         if(list1!=null && !list1.isEmpty())
	        	 sQLPreauthTransVO=list1.get(0);
	             if(sQLPreauthTransVO!=null)
	             {
	                lPreVO.setPatientName(sQLPreauthTransVO.getPATIENT_NAME());
	                lPreVO.setRationCard(sQLPreauthTransVO.getRATION_CARD());
	                lPreVO.setCaseStatusId(sQLPreauthTransVO.getCASE_STATUS_ID());
	                lPreVO.setAge(sQLPreauthTransVO.getAGE());
	                lPreVO.setGender(sQLPreauthTransVO.getGENDER());
	                lPreVO.setPatientIPNo(sQLPreauthTransVO.getPATIENT_IPNO());
	                if(sQLPreauthTransVO.getCONTACT_NO() != null) 
	                lPreVO.setContactNo(sQLPreauthTransVO.getCONTACT_NO().toString());
	                lPreVO.setCaseStatusDesc(sQLPreauthTransVO.getCASE_STATUS_DESC());
	                lPreVO.setCaseNo(sQLPreauthTransVO.getCASE_NO());
	                lPreVO.setCaste(sQLPreauthTransVO.getCASTE());
	                lPreVO.setAddress1(sQLPreauthTransVO.getADDRESS1());
	                lPreVO.setAddress2(sQLPreauthTransVO.getADDRESS2());
	                lPreVO.setPincode(sQLPreauthTransVO.getPINCODE());
	                lPreVO.setPatRegDate(sQLPreauthTransVO.getCASE_CRT_DATE());//018
	                lPreVO.setRegHospDate(sQLPreauthTransVO.getREG_HOSP_DATE());
	                if(sQLPreauthTransVO.getCLAIM_NO()!=null) 
	                lPreVO.setClaimNo(sQLPreauthTransVO.getCLAIM_NO());
	                lPreVO.setHamlet(sQLPreauthTransVO.getHAMLET());
	                lPreVO.setVillage(sQLPreauthTransVO.getVILLAGE());
	                lPreVO.setMandal(sQLPreauthTransVO.getMANDAL());
	                lPreVO.setDistrict(sQLPreauthTransVO.getDISTRICT());
	                lPreVO.setPatientID(sQLPreauthTransVO.getPATIENT_ID());
	                lPreVO.setPhcName(sQLPreauthTransVO.getPHC_NAME());
	                if(sQLPreauthTransVO.getREF_CARD_NO() != null) 
	                lPreVO.setRefCardNo(sQLPreauthTransVO.getREF_CARD_NO());
	                lPreVO.setSrcRegistration(sQLPreauthTransVO.getSRC_REGISTRATION());
	                lPreVO.setHospitalCode(sQLPreauthTransVO.getHOSP_CODE());
	                lPreVO.setHospType(sQLPreauthTransVO.getHOSP_TYPE()); //022
	                lPreVO.setHospitalName(sQLPreauthTransVO.getHOSP_NAME());
	                if(sQLPreauthTransVO.getDIS_MAIN_NAME() != null)
	                lPreVO.setDisMainName(sQLPreauthTransVO.getDIS_MAIN_NAME());
	                lPreVO.setPhase(sQLPreauthTransVO.getPHASE().toString());
	                lPreVO.setRenewal(sQLPreauthTransVO.getPHASE_RENEWAL());//098
	                lPreVO.setScheme(sQLPreauthTransVO.getSCHEME());
	                if(sQLPreauthTransVO.getBLOCKED_USER() != null)  
	                lPreVO.setBlockedUser(sQLPreauthTransVO.getBLOCKED_USER());
	                lPreVO.setHospDispCode(sQLPreauthTransVO.getHOSP_DISP_CODE());
	                lPreVO.setHospAddress(sQLPreauthTransVO.getHOSP_ADRESS());
	                if(sQLPreauthTransVO.getHOSP_CONTACT_NO()!=null)
	                lPreVO.setHospContactNo(sQLPreauthTransVO.getHOSP_CONTACT_NO().toString());
	                lPreVO.setPatCrtDt(sQLPreauthTransVO.getPAT_CRT_DATE());
	                lPreVO.setCaseCrtDt(sQLPreauthTransVO.getCASE_CRT_DATE());
	                lPreVO.setCardType(sQLPreauthTransVO.getCARD_TYPE());
	                lPreVO.setCaseStatusDt(sQLPreauthTransVO.getCASE_STATUS_DATE());
	                lPreVO.setJournalistRenewal(sQLPreauthTransVO.getJOURNALIST_RENEWAL());//006
	                lPreVO.setCmcoRenewal(sQLPreauthTransVO.getCMCO_RENEWAL()); // 008
	                 if(sQLPreauthTransVO.getSURGERY_NAME() != null)
	                lPreVO.setSurgeryName(lPreVO.getSurgeryName()+sQLPreauthTransVO.getSURGERY_NAME());
	               
	               while(lRs.next())
	               {
	                   if(sQLPreauthTransVO.getSURGERY_NAME() != null!= null)     
	                     lPreVO.setSurgeryName(lPreVO.getSurgeryName() +" , "+lRs.getString("surgery"));
	               }
	             }

	             List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	             criteriaList.add(new GenericDAOQueryCriteria("attachType",PreauthConstants.PATIENT_FEEDBACK ,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	             criteriaList.add(new GenericDAOQueryCriteria("caseId",lPreVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	             List<EhfCaseDocAttch> asritCaseDocAttchList=genericDao.findAllByCriteria(EhfCaseDocAttch.class,criteriaList);	             
	             if(asritCaseDocAttchList!=null && !asritCaseDocAttchList.isEmpty())
	             {
	                 if(asritCaseDocAttchList.get(0).getSavedName() != null)     
	                   lPreVO.setFeedbackFile(asritCaseDocAttchList.get(0).getSavedName());
	             }	       
	         queryBuff = new StringBuffer();	        	         
	         queryBuff.append("select ap.* from asrit_patient ap,EHF_case ac ");
	         queryBuff.append("where ap.patient_id=ac.case_patient_no and ac.case_id = ?");	                     
	         args=new String[1];
	         args[0]=lPreVO.getCaseId();
	         List<AsritPatient> asritPatientList=genericDao.executeSQLQueryList(AsritPatient.class,queryBuff.toString(),args);
	         if(asritPatientList!=null && !asritPatientList.isEmpty())
	         {
	             if(asritPatientList.get(0).getIntimationId() != null)     
	               lPreVO.setTelephonicId(asritPatientList.get(0).getIntimationId());
	         }
	        //End 013
	        //Start 021 for CMA WorkFlow
	         queryBuff = new StringBuffer();
	         queryBuff.append("select count(1) COUNT from asrit_case_cma_audit aa  where aa.case_id= ?");
	         args=new String[1];
	         args[0]=lPreVO.getCaseId();
	         List<LabelBean> countList=genericDao.executeSQLQueryList(LabelBean.class, queryBuff.toString()   , args);
	         
	         if(countList!=null && !countList.isEmpty())
	         {
	             if(countList.get(0).getCOUNT() != null)     
	               lPreVO.setCmaAuditCnt(countList.get(0).getCOUNT().intValue());
	         }

	        
	        //End 021 For CMA Workflow	      
	         StringBuffer queryBuff3 = new StringBuffer();
	         String caseNumber="";
	         EhfCase ehfCase=genericDao.findById(EhfCase.class,String.class,(String)lPreVO.getCaseId());
             
	         if(ehfCase!=null)
	         {
	            if(ehfCase.getCaseNo() != null)     
	            caseNumber=ehfCase.getCaseNo();  
	         }


	         queryBuff3.append("select count(1) COUNT from asrit_discharge_patfdbk adp where adp.case_no=?");
	         args=new String[1];
	         args[0]=caseNumber;
	         List<LabelBean> countList1=genericDao.executeSQLQueryList(LabelBean.class, queryBuff3.toString()   , args);
	         
	         if(countList1!=null && !countList1.isEmpty())
	         {
	        	 if(countList1.get(0).getCOUNT() != null)         
	               lPreVO.setFeedbckcnt(countList1.get(0).getCOUNT().intValue());
	         }
	     }
	     catch(Exception e)
	     {
	    	 gLogger.error("Exception in method getPatientCommonDtls() of casesApprovalDaoImpl->"+e.getMessage());     //028
	    	 System.out.println(e.getMessage());
	         e.printStackTrace();
	     }	   
	     return lPreVO;
	   }*/
	   
	   /*
	    * getPatientCases
	    	   	  
	   private List getPatientCases(PreauthVO preVO)//025 //109
	   {	   
	   List lCasesList=new ArrayList();
	   List<Object> temp = null;//016
	       try
	       {
	           StringBuffer lStrBuff = new StringBuffer();
	           lStrBuff.append(" select distinct ac.case_no CASE_NO, acp.case_id CASE_ID");// Start 109
	           lStrBuff.append("   from EHF_case ac, asrit_patient ap, asrit_case_proces acp ");
	           lStrBuff.append("  where ap.patient_id = ac.case_patient_no and ");
	           lStrBuff.append("        ap.ration_card_no = ? and");
	           lStrBuff.append("        ac.case_hosp_code = ? and ac.case_id != ? and ");
	           lStrBuff.append("        acp.case_id = ac.case_id and acp.case_discharge_dt is  null and acp.death_dt is null ");
	           String[] args=new String[3];
	           args[0]=preVO.getRationCard();
	           args[1]=preVO.getHospitalCode();
	           args[2]=preVO.getCaseId(); //End 109
	           List<SQLPreauthTransVO> list=genericDao.executeSQLQueryList(SQLPreauthTransVO.class,lStrBuff.toString(),args);
	           String lStrtemp = null;
	           for(SQLPreauthTransVO sQLPreauthTransVO:list)
	           {
	               temp = new ArrayList<Object>();//016
	               lStrtemp = sQLPreauthTransVO.getCASE_NO();
	               if(lStrtemp == null) lStrtemp = ASRIConstants.EMPTY_STRING;
	               temp.add(lStrtemp);
	               
	               lStrtemp = sQLPreauthTransVO.getCASE_ID();
	               if(lStrtemp == null) lStrtemp = ASRIConstants.EMPTY_STRING;
	               temp.add(lStrtemp);
	               
	               lCasesList.add(temp);
	               temp=null;
	           }
	           lStrtemp=null;
	   }
	   catch(Exception e)
	   {
		   gLogger.error("Exception in method getPatientCases() of casesApprovalDaoImpl->"+e.getMessage());     //028     
	   }	   
	   return lCasesList;
	   }*/
	   
	   
	   /*
	    * getPreDates
	    */
	   /* private PreauthVO getPreDates(PreauthVO preVO)
	     {	      
	      try
	      {
	        StringBuffer queryBuffer = new StringBuffer();
	        queryBuffer.append("   select acp.billhead_name billHeadName, ");
	        queryBuffer.append("          acp.bill_dt billDt,");
	        //queryBuffer.append("          acp.bill_time, ");
	        queryBuffer.append("          acp.case_dt_pre_auth preauthDt, ");
	        queryBuffer.append("          acp.case_apprv_rej_dt caseAprvDt, ");
	        queryBuffer.append("          acp.case_dt_surgery surgeryDt, ");
	        queryBuffer.append("          acp.case_discharge_dt dischargeDt, ");
	        queryBuffer.append("          acp.death_dt deathDt deathDt, ");
	        queryBuffer.append("          acp.case_enh_req_dt enhReqDt, ");//108
	        queryBuffer.append("          acp.case_enh_apv_dt enhApvDt, ");//108
	        queryBuffer.append("          acp.case_enh_rej_dt enhRejDt, ");//108
	        queryBuffer.append("          acp.case_cncl_req_dt cnclReqDt, ");//104
	        queryBuffer.append("          acp.case_cncl_apv_dt cnclApvDt");//104
	        queryBuffer.append("     from asrit_case_proces acp ");
	        queryBuffer.append("    where acp.case_id = ? ");	        
	        String[] args=new String[1];
	        args[0]=preVO.getCaseId();
	        List<PreauthVO> list=genericDao.executeSQLQueryList(PreauthVO.class, queryBuffer.toString(),args);
	        if(list!=null && !list.isEmpty())
	        	preVO=list.get(0);
	        if(pRltSet.next())
	        {
	          if(pRltSet.getString("billhead_name") != null)     
	            preVO.setBillHeadName(pRltSet.getString("billhead_name"));
	          if(pRltSet.getString("bill_dt") != null)     
	            preVO.setBillDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("bill_dt"))+" "+(ASRIConstants.hhmmss).format(pRltSet.getTime("bill_dt")));
	          if(pRltSet.getString("case_dt_pre_auth") != null)     
	            preVO.setPreauthDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_dt_pre_auth")));
	          if(pRltSet.getString("case_dt_surgery") != null)
	            preVO.setSurgeryDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_dt_surgery")));
	          if(pRltSet.getString("case_apprv_rej_dt") != null)
	          {
	            preVO.setIsApproved(ASRIConstants.INTEGER_ONE_STRING);
	            preVO.setCaseAprvDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_apprv_rej_dt"))+" "+(ASRIConstants.hhmmss).format(pRltSet.getTime("case_apprv_rej_dt")));//016
	          }
	          if(pRltSet.getString("case_discharge_dt") != null)     
	            preVO.setDischargeDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_discharge_dt")));
	          if(pRltSet.getString("death_dt") != null)     
	            preVO.setDeathDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("death_dt")));
	            //start 104
	          if(pRltSet.getString("case_cncl_req_dt") != null)     
	            preVO.setCnclReqDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_cncl_req_dt"))+" "+(ASRIConstants.hhmmss).format(pRltSet.getTime("case_cncl_req_dt")));
	          if(pRltSet.getString("case_cncl_apv_dt") != null)     
	            preVO.setCnclApvDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_cncl_apv_dt"))+" "+(ASRIConstants.hhmmss).format(pRltSet.getTime("case_cncl_apv_dt")));
	          //start 108
	           if(pRltSet.getString("case_enh_req_dt") != null)     
	             preVO.setEnhReqDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_enh_req_dt"))+" "+(ASRIConstants.hhmmss).format(pRltSet.getTime("case_enh_req_dt")));
	           if(pRltSet.getString("case_enh_apv_dt") != null)     
	             preVO.setEnhApvDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_enh_apv_dt"))+" "+(ASRIConstants.hhmmss).format(pRltSet.getTime("case_enh_apv_dt")));
	           if(pRltSet.getString("case_enh_rej_dt") != null)     
	             preVO.setEnhRejDt((ASRIConstants.ddmmyyyy).format(pRltSet.getDate("case_enh_rej_dt"))+" "+(ASRIConstants.hhmmss).format(pRltSet.getTime("case_enh_rej_dt")));
	            //end 104 & 108
	        } 
	      }          
	      catch(Exception e)
	      {
	    	  gLogger.error("SQL Exception in method getPreDates() of casesApprovalDaoImpl->"+e.getMessage());     //028
	      }	      
	     return preVO;
	     }   
	    */
	    
	    /*
	     * getHemoCases
	     
	    private PreauthVO getHemoCases(PreauthVO preVO)//025//006
	    {	       
	        Map lQueryVal = new HashMap();   
	        int licnt = 0;
	            try
	            {
	                StringBuffer lStrBuff = new StringBuffer();
	                //start 020
	                lStrBuff.append("  select count(1) COUNT");
	                lStrBuff.append("    from EHF_case         ac, ");
	                lStrBuff.append("         asrit_patient      ap, ");
	                lStrBuff.append("         asrit_case_surgery acs, ");
	                lStrBuff.append("         asrit_case_proces  acp ");
	                lStrBuff.append("   where ap.patient_id = ac.case_patient_no and ");
	                lStrBuff.append("         ap.ration_card_no = ");
	                lStrBuff.append("         (select ap.ration_card_no ");
	                lStrBuff.append("            from asrit_patient ap, EHF_case ac ");
	                lStrBuff.append("           where ac.case_patient_no = ap.patient_id and ac.case_id = ?) and ");
	                lQueryVal.put(++licnt, preVO.getCaseId());
	                lStrBuff.append("         acs.case_id = ac.case_id and acp.case_id = ac.case_id and ");
	                lStrBuff.append("         acp.case_apprv_rej_dt > (sysdate - ?) and ac.case_status not in (?, ?, ?) and acp.case_cncl_apv_dt is null ");//038//start 094//102//016
	                
	                lQueryVal.put(++licnt, PreauthConstants.HEMO_CHK_DAYS);//102
	                lQueryVal.put(++licnt, PreauthConstants.CEO_REJECTED);
	                lQueryVal.put(++licnt, PreauthConstants.CMO_REJECTED);
	                lQueryVal.put(++licnt, PreauthConstants.TRUST_REJECTED);
	                lStrBuff.append("          and ac.case_id != ? and acs.surgery_code = ? "); //in (");
	                lQueryVal.put(++licnt, preVO.getCaseId()); 
	                    lQueryVal.put(++licnt, PreauthConstants.SURG_TO_RESTRCT_CLAIM);
	      
	                 String[] args=new String[licnt];
	                 for (int i = 1; i <= licnt; i++) 
	                 {
	                	 args[i]=(String)(lQueryVal.get(i));
	                 }
	                
	                List<LabelBean> list=genericDao.executeSQLQueryList(LabelBean.class,lStrBuff.toString(),args);
	                if(list!=null && !list.isEmpty())
	                {	                    
	                      preVO.setHemoCount(list.get(0).getCOUNT().intValue());
	                }
	          licnt=0;
	          lQueryVal=new HashMap();
	                lStrBuff = new StringBuffer();
	                lStrBuff.append("  select count(1) COUNT");
	                lStrBuff.append("    from EHF_case         ac, ");
	                lStrBuff.append("         asrit_case_surgery acs, ");
	                lStrBuff.append("         asrit_case_proces  acp ");
	                lStrBuff.append("   where acs.case_id = ac.case_id and acp.case_id = ac.case_id and ");
	                lStrBuff.append("         acp.case_apprv_rej_dt is null ");
	                lStrBuff.append("         and ac.case_id = ? and acs.surgery_code = ? ");// in (");//start 016
	                lQueryVal.put(++licnt, preVO.getCaseId());
	                lQueryVal.put(++licnt,PreauthConstants.SURG_TO_RESTRCT_CLAIM);
	          
	                args=new String[licnt];
	                 for (int i = 1; i <= licnt; i++) 
	                 {
	                	 args[i]=(String)(lQueryVal.get(i));
	                 }

		            list=genericDao.executeSQLQueryList(LabelBean.class,lStrBuff.toString(),args);
		            if(list!=null && !list.isEmpty())
	                {
	                        preVO.setHemoCheck(list.get(0).getCOUNT().intValue());	                 
	                }
	              //end 020
	        }     
	        catch(Exception e)
	        {
	        	gLogger.error("Exception in method getHemoCases() of casesApprovalDaoImpl->"+e.getMessage());     //028     
	        }	      
	        return preVO;
	    }
	    */
	    
	    /*
	     * getRenalCases
	     */
	   /* private PreauthVO getRenalCases(PreauthVO preVO)//006
	    {	        
	        String surgList = PreauthConstants.RENAL_SURG_LST;
	        String aprvSurgList = PreauthConstants.RENAL_SELECT_SURG;
	        String cardType=preVO.getCardType();//016
	        Map lHshQryVal = new HashMap();
	        int licnt = 0;
	        try
	        {
	            StringBuffer lStrBuff = new StringBuffer();
	            lStrBuff.append("select count(1) COUNT");
	            lStrBuff.append(" from EHF_case ac, asrit_patient ap, asrit_case_surgery acs ");
	            lStrBuff.append(" where ap.patient_id = ac.case_patient_no and ");
	            lStrBuff.append(" ap.ration_card_no = ? and ");
	            lHshQryVal.put(++licnt, preVO.getRationCard());
	            lStrBuff.append(" acs.case_id = ac.case_id and ");
	            lStrBuff.append(" ac.case_id != ? and  ");//006
	            lHshQryVal.put(++licnt, preVO.getCaseId());
	            if((ASRIConstants.CARD_TYPE_J).equalsIgnoreCase(cardType)) //start 016
	            {
	                lStrBuff.append(" ac.journalist_renewal = ? ");  
	                lHshQryVal.put(++licnt,preVO.getJournalistRenewal());
	            }
	            else if((ASRIConstants.CARD_TYPE_C).equalsIgnoreCase(cardType)) 
	            {
	                lStrBuff.append(" ac.cmco_renewal = ? ");  
	                lHshQryVal.put(++licnt,preVO.getCmcoRenewal());
	            }
	            else
	            {
	                lStrBuff.append(" ac.phase_id = ? and ac.phase_renewal = ? ");   
	                lHshQryVal.put(++licnt, preVO.getPhase());//006
	                lHshQryVal.put(++licnt,preVO.getRenewal());
	            }
	            lStrBuff.append("  and acs.surgery_code in (");
	            //end 035
	            if(!ASRIConstants.EMPTY_STRING.equalsIgnoreCase(surgList)) {
	                     String str[]=surgList.split(",");
	                     for(int count=0; count<str.length; count++)
	                     {
	                       if(count == 0)
	                       {
	                        lStrBuff.append("?");
	                       }
	                       else
	                       {
	                        lStrBuff.append(",?");
	                      }
	                      lHshQryVal.put(++licnt, str[count]);
	                     }
	            }
	            lStrBuff.append(") "); 
	            lStrBuff.append(" and (select count(1) COUNT");
	            lStrBuff.append(" from asrit_case_surgery acs1 ");
	            lStrBuff.append(" where acs1.case_id =? and acs1.surgery_code in (");
	            lHshQryVal.put(++licnt, preVO.getCaseId());
	            if(!ASRIConstants.EMPTY_STRING.equalsIgnoreCase(aprvSurgList)) {
	                     String str1[]=aprvSurgList.split(",");
	                     for(int count=0; count<str1.length; count++)
	                     {
	                       if(count == 0)
	                       {
	                        lStrBuff.append("?");
	                       }
	                       else
	                       {
	                        lStrBuff.append(",?");
	                      }
	                      lHshQryVal.put(++licnt, str1[count]);
	                     }
	            }
	            lStrBuff.append("))>0 ");
	            
	            String[] args=new String[licnt];
                for (int i = 1; i <= licnt; i++) 
                {
               	 args[i]=(String)(lHshQryVal.get(i));
                }
                
                List<LabelBean> list=genericDao.executeSQLQueryList(LabelBean.class,lStrBuff.toString(),args);
	            if(list!=null && !list.isEmpty())
                {
                        preVO.setRenalCount(list.get(0).getCOUNT().intValue());	                 
                }	           
	    }
	    catch(Exception e)
	    {
	        gLogger.error("Exception in method getRenalCases() of CasesApprovalDAOImpl->"+e.getMessage());     //028     
	    }	   
	    return preVO;
	    }*/
	    
	    /*
	     * getMedMgntFlg
	     
	    private PreauthVO getMedMgntFlg(PreauthVO preVO) 
	    {	 
	       StringBuffer lStrBuff=new StringBuffer();
	       try
	       {           
	           lStrBuff.append(" select (case when count(1) = null then 0 when count(1) = 0 then 0 else 1 end) COUNT ");
	           lStrBuff.append(" from asrit_case_surgery acs, asrim_surgery asr ");
	           lStrBuff.append(" where acs.surgery_code = asr.surgery_id and asr.medmgmty_n = ? and acs.case_id = ?");           	                    
	           String[] args=new String[2];
	           args[0]=ASRIConstants.FLAG_Y;
	           args[1]=preVO.getCaseId();
	           List<LabelBean> list=genericDao.executeSQLQueryList(LabelBean.class,lStrBuff.toString(),args);
	            if(list!=null && !list.isEmpty())
               {
	               if(list.get(0).getCOUNT()!=null)
	                    preVO.setMedicalMngCase(list.get(0).getCOUNT().intValue());               
	           }          
	       }   
	       catch(Exception e) 
	       {
	    	   gLogger.error("Exception in method getMedMgntFlg() of CasesApprovalDAOImpl->"+e.getMessage());    
	       }	       
	       return preVO;
	    }*/
	    /*
	     * getProcessFlag
	     */
	/* private PreauthVO getProcessFlag(PreauthVO preVO)
	    {	     
	        try
	        {
	            StringBuffer lStrBuffer = new StringBuffer();
	            lStrBuffer.append("select pd.flag FLAG from asrim_phase_duration pd where pd.phase_id=? and pd.renewal=?");
	            String[] args=new String[2];
		        args[0]=preVO.getPhase();
		        args[1]=preVO.getCaseRenewal();
		        List<SQLPreauthTransVO> list=genericDao.executeSQLQueryList(SQLPreauthTransVO.class,lStrBuffer.toString(),args);
	            if(list!=null && !list.isEmpty())
                {
	               if(list.get(0).getFLAG()!=null)
	            	   preVO.setProcessFlag(list.get(0).getFLAG());               
	            }       
	        }     
	        catch (Exception e) 
	        {
	        	gLogger.error("Exception in method getProcessFlag() of CasesApprovalDAOImpl->"+e.getMessage()); 
	        } 
	       return preVO;
	    } */
	    /*
	     * getErroneousStatus
	     */
	/*private PreauthVO getErroneousStatus(PreauthVO preVO)
	    {
	        try
	        {	        	        	
	        	AsritErroneousClaim asritErroneousClaim=genericDao.findById(AsritErroneousClaim.class,String.class,(String)preVO.getCaseId());
	             
		         if(asritErroneousClaim!=null)
		         {
		            if(asritErroneousClaim.getErrClaimStatus() != null)     
		            	preVO.setErrStatus(asritErroneousClaim.getErrClaimStatus());  
		         }
	        }               
	        catch (Exception e) 
	        {
	        	gLogger.error("Exception in method getErroneousStatus() of CasesApprovalDAOImpl->"+e.getMessage()); 
	        } 	      
	       return preVO;
	    }*/
	    /*
	     * getTelDtls
	     */
	    /*private PreauthVO getTelDtls(PreauthVO preVO)
	    {	     
	     try
	     {
	       StringBuffer queryBuffer = new StringBuffer();
	       queryBuffer=new StringBuffer();
	       queryBuffer.append(" select atr.crt_date START_DT from asrit_telephonic_regn atr  ");//062
	       queryBuffer.append(" where atr.telephonic_id = ?");//013
	       String[] args=new String[1];
	       args[0]=preVO.getTelephonicId();	        
	       List<SQLPreauthTransVO> list=genericDao.executeSQLQueryList(SQLPreauthTransVO.class,queryBuffer.toString(),args);
           if(list!=null && !list.isEmpty())
           {
              if(list.get(0).getSTART_DT()!=null)           	   
              preVO.setTelInitDt((ASRIConstants.ddmmyyyy).format(list.get(0).getSTART_DT()) + " "+ (ASRIConstants.hhmmss).format(list.get(0).getSTART_DT()));
           }
	     }
	     catch(Exception e)
	     {
	    	 gLogger.error("Exception in method getTelDtls() of CasesApprovalDAOImpl->"+e.getMessage());     //028
	     }
	    return preVO;
	    }*/
	    
	    /**
	     * getPatientBiometricDisplay
	     * @param preVO
	     * @return
	     */
	     /*private PreauthVO getPatientBiometricDisplay(PreauthVO preVO)
	     {	         
	          try{	              
	        	  AsritCasePatientBiometric asritCasePatientBiometric=genericDao.findById(AsritCasePatientBiometric.class,AsritCasePatientBiometricId.class,new AsritCasePatientBiometricId(preVO.getCaseId(),"1"));		             			        
			            if(asritCasePatientBiometric!= null)     
			            	preVO.setDisplayBiometric("display");
			            else
			                  preVO.setDisplayBiometric("none");			              			      			         
	          }
	         catch(Exception e){
	        	 gLogger.error("Exception in method getPatientBiometricDisplay() of CasesApprovalDAOImpl->"+e.getMessage());
	         }	         
	         return preVO;
	     }*/
	     /**
	      * 
	      * @param caseId
	      * @return
	      */
	     public String getPrevDtlForPending(String caseId) {
	        
	         String prevDtlName=ASRIConstants.EMPTY_STRING;
	         try{
	             String prevStat= getReqStatForDocumentPending(caseId);
	             // StringBuffer lsb=new StringBuffer();
	              if(PreauthConstants.PENDING_STAT_SEQ.indexOf(prevStat+",")!=-1)
	              {	                       	              
	              AsrimCombo asrimCombo=genericDao.findById(AsrimCombo.class,AsrimComboId.class,new AsrimComboId(prevStat,"en_US"));
	              if(asrimCombo!=null)
	               prevDtlName=prevStat+","+asrimCombo.getCmbDtlName();
	              }
	         }
	         catch(Exception e)
	         {
	                 gLogger.error("Exception in method getPrevDtlForPending() of PreauthEntityBean caseid : "+caseId+""+e.getMessage());
	         }
	         return prevDtlName;
	     }
	     
	     /**
	      * @param caseId
	      * @return
	      */
	     public String getReqStatForDocumentPending(String caseId) {//Start Id:145	         
	         String prevstat=ASRIConstants.EMPTY_STRING;
	         try{
	         StringBuffer queryBuffer = new StringBuffer();
	         queryBuffer.append("  select act_id ACT_ID from  asrit_audit where act_order=(select max(act_order)-1 act_order from asrit_audit where case_id = ?) and case_id = ?");
	         String[] args=new String[2];
	         args[1]=caseId;
	         args[1]=caseId;
	         List<SQLPreauthTransVO> list=genericDao.executeSQLQueryList(SQLPreauthTransVO.class,queryBuffer.toString(),args);
	          if(list!=null && !list.isEmpty())	           
	          {
	           prevstat = list.get(0).getACT_ID();
	          }
	         }
	         catch(Exception e)
	         {
	                 gLogger.error("Exception in method getReqStatForDocumentPending() of caseApprovalDaoiMpl caseid : "+caseId+""+e.getMessage());
	         }
	         return prevstat;
	     }
	     /**
	      * 
	      * @param preVO
	      * @return
	      */
	     /*private PreauthVO getPatientBiomEmrChkRmks(PreauthVO preVO)
	     {	          
	          StringBuffer lsb = new StringBuffer();	         
	          try{	              	              	              	             
	              AsritBiomFinger asritBiomFinger=genericDao.findById(AsritBiomFinger.class,String.class,preVO.getPatientID());
	              if(asritBiomFinger!=null){
	              preVO.setBiomEmrChkRmks(asritBiomFinger.getBiomEmrchkRemarks());
	              preVO.setBiomEmrChkFlag(asritBiomFinger.getBiomEmrchkYn());
	              }	             
	          }
	         catch(Exception e){
	        	 gLogger.error("Exception in method getPatientBiomEmrChkRmks() of CasesApprovalDAOImpl->"+e.getMessage());
	         }
	         return preVO;	    
	     }*/
	     
		/**
		 * 
		 * @param preVO
		 * @return
		 */
	     /*private String getPatientFeedbackFlag(PreauthVO preVO){	           
	           StringBuffer lsb = new StringBuffer();	           
	           String cnt=null;	           
	           try{	                
	               lsb.append(" select count(1) count COUNT from  ASRIT_DISCHARGE_PATFDBK  where CASE_NO=? ");
	               
	               String[] args=new String[1];
	    	       args[0]=preVO.getCaseNo();	        
	    	       List<LabelBean> list=genericDao.executeSQLQueryList(LabelBean.class,lsb.toString(),args);
	               if(list!=null && !list.isEmpty())
	               {
	               cnt=list.get(0).getCOUNT().toString();
	               }	             
	           }
	          catch(Exception e){
	              gLogger.error("Exception in method getPatientFeedbackFlag() of CasesApprovalDAOImpl->"+e.getMessage());
	          }	         	          
	          return cnt;	      
	      }*/
	      
		/**
		 * @return
		 */
	     /*private List<String> getAttachmentNamesLst() 
	      {
	          List<String> attachNameLst = new ArrayList<String>();
	          StringBuffer sb = new StringBuffer();	          
	          try
	          {
	        	  List<AsrimCombo> list=genericDao.findAllByPropertyMatch(AsrimCombo.class,"cmbHdrId","CH74");
	              if(list!=null && !list.isEmpty())
	              {
	            	  for(AsrimCombo asrimCombo:list)
	            	  {
	            	  attachNameLst.add(asrimCombo.getCmbDtlVal());
	            	  }
	              }
	          }
	          catch(Exception e) {
	              e.printStackTrace();
	          }
	          return attachNameLst;
	      }*/
	      /**
	       * 
	       * @param lparamMap
	       * @param preVO
	       * @return
	       */
	     /*private PreauthVO getUserBlockedInfo(HashMap lparamMap,PreauthVO preVO) 
	      {	         
	          String lStrUsrId=(String)lparamMap.get("userId");	          
	          StringBuffer lStrBuff = new StringBuffer();
	          try
	          {
	              lStrBuff.append(" select nvl(blocked_usr,'') blocked_usr from EHF_case where case_id = ? ");	              	              	              
	              String[] args=new String[1]; 
	              args[0]=preVO.getCaseId();                
	              List<EhfCase> list= genericDao.executeSQLQueryList(EhfCase.class, lStrBuff.toString(), args);
	              if(!list.isEmpty() && list!=null)	                		             
	              {
	                 if(list.get(0).getBlockedUsr()!= null)
	                   preVO.setBlockedUser(list.get(0).getBlockedUsr());
	              }
	              if(ASRIConstants.EMPTY_STRING.equalsIgnoreCase(preVO.getBlockedUser())) 
	              {
	            	  lStrBuff = null;
	                  lStrBuff = new StringBuffer();
	                  lStrBuff.append(" update EHF_case set blocked_usr=? , blocked_dt=sysdate ");
	                  lStrBuff.append(" where case_id=?");	                  
	                  args=new String[2];
	                  args[0]=lStrUsrId;
		              args[1]=preVO.getCaseId();
	                  list= genericDao.executeSQLQueryList(EhfCase.class, lStrBuff.toString(), args);
	              }
	          }     
	          catch(Exception e) 
	          {
	        	  gLogger.error("Exception in Method getUserBlockedInfo() of casesApprovalDAOImpl->"+e.getMessage());
	          }
	          return preVO;	          
	      }	    */ 
	      
	      public String getOnlinecaseSheetFlag(String caseId)
	      {
	    	  String flag="N";
	    	  StringBuffer lStrBuff = new StringBuffer();
	    	  try
	    	  {
	    		  lStrBuff.append("select count(1) COUNT from ehf_audit a where a.case_id=? and a.act_id=? ");
	 	         String[] args=new String[2];
	 	         args[0]=caseId;
	 	         args[1]="CD21";  // Get Case sheet after Discharge Updated
	 	         List<LabelBean> countList=genericDao.executeSQLQueryList(LabelBean.class, lStrBuff.toString(), args);
	 	        if(countList!=null && !countList.isEmpty())
		         {
		             if(countList.get(0).getCOUNT() != null ) 
		             {
		            	 if(countList.get(0).getCOUNT().intValue()!=0)
		            		 flag="Y";
		             }
		         }
	    	  }
	    	  catch(Exception e)
	    	  {
	    		  gLogger.error("Exception in Method getOnlinecaseSheetFlag() of casesApprovalDAOImpl->"+e.getMessage());
	    	  }
	    	  return flag;
	      }
	      
	      
	  	public List<CasesSearchVO> getAllCaseSearchDetails(CasesSearchVO vo) {
	  		
	  		List<CasesSearchVO> list=null;
	  		String caseId=vo.getCaseId();
	  		StringBuffer query = new StringBuffer();
	  		query.append(" select CATID,PATIPOP,ISSUESTATUS,CASENO,PATIENTID,PATNAME,HOSPNAME,caseStatus,CASESTATUSID,SUBMITTEDDATE,CLAIMAMT,PREAUTHAPPAMT,stragg(PROC) PROC from ( select distinct(c.case_Id) as CATID,p.patient_ipop as PATIPOP, ecb.cmb_Dtl_Name as ISSUESTATUS, c.case_No as CASENO,p.patient_Id as PATIENTID,p.name as PATNAME,h.hosp_Name as HOSPNAME,asri.cmb_Dtl_Name as caseStatus, " );
	  		query.append(" c.case_Status as CASESTATUSID,p.reg_Hosp_Date  as SUBMITTEDDATE, nvl(ec.cs_Cl_Amount,0) as CLAIMAMT, (nvl(ec.pck_Appv_Amt,0)+ nvl(ec.comorbid_Appv_Amt,0)  + nvl(ec.enh_Appv_Amt,0)) as PREAUTHAPPAMT ,emt1.proc_name ||'('||ect.icd_Proc_Code||')' as PROC");
	  		query.append(" from Ehf_Emp_Case_Dtls c,Ehf_Patient p ,Ehfm_Hospitals h,Ehfm_Cmb_Dtls asri, Ehf_Case ec, Ehfm_Cmb_Dtls ecb,Ehf_Case_Therapy ect,ehfm_main_therapy emt1  ");
	  		if(vo.getCaseForDissFlg()==null || "".equals(vo.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(vo.getCaseForDissFlg()))
	  		{
	  			query.append(" ,Ehf_Audit ea ");
	  		}
	  		query.append(" where asri.cmb_Dtl_Id=c.case_Status and c.case_Hosp_Code=h.hosp_Id and  c.case_Patient_No=p.patient_Id and c.employee_No='"+vo.getLoginName()+"' ");
	  		query.append(" and ec.case_Id=c.case_Id and ecb.cmb_Dtl_Id= ec.case_Status and ec.case_Id=ect.case_Id and emt1.icd_proc_code=ect.icd_proc_code and emt1.state=ec.scheme_id and emt1.process in ('IP','DOP')");
	  		
	  		if(vo.getCaseForDissFlg()==null || "".equals(vo.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(vo.getCaseForDissFlg()))
	  		{
	  			query.append(" and ea.case_Id= c.case_Id and (ea.act_Id='"+config.getString("preauth_discharge_updated")+"' or c.case_Status = '"+config.getString("CASE.OPCaseRegistered")+"')");
	  		}
	  		query.append(" and ecb.cmb_Dtl_Name not in ('"+config.getString("op_case_status")+"')) ");
	  		query.append(" group by CATID,PATIPOP,ISSUESTATUS,CASENO,PATIENTID,PATNAME,HOSPNAME,caseStatus,CASESTATUSID,SUBMITTEDDATE,CLAIMAMT,PREAUTHAPPAMT ");
	  		query.append(" order by SUBMITTEDDATE  ");
	  		try{
	  			list=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
	  			
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return list;
	  	}
	  	public List<CasesSearchVO> getCaseSearchDetails(CasesSearchVO vo) {
			List<CasesSearchVO> list=null;
			StringBuffer query = new StringBuffer();
			
			
			query.append(" select distinct(c.case_Id) as CATID, ecb.cmb_Dtl_Name as ISSUESTATUS, c.case_No as CASENO,p.patient_Id as PATIENTID,p.name as PATNAME,h.hosp_Name as HOSPNAME,asri.cmb_Dtl_Name as CASESTATUS, " );
			query.append("  c.case_Status as CASESTATUSID,p.reg_Hosp_Date  as SUBMITTEDDATE, nvl(ec.cs_Cl_Amount,0) as CLAIMAMT, (nvl(ec.pck_Appv_Amt,0)+ nvl(ec.comorbid_Appv_Amt,0) + nvl(ec.enh_Appv_Amt,0)) as PREAUTHAPPAMT,stragg(emt1.proc_name ||'('||ect.icd_Proc_Code||')') as PROC  ");
			query.append("  from Ehf_Emp_Case_Dtls c,Ehf_Patient p ,Ehfm_Hospitals h,Ehfm_Cmb_Dtls asri, Ehf_Case ec, Ehfm_Cmb_Dtls ecb,Ehf_Case_Therapy ect,ehfm_main_therapy emt1  ");				
			if(vo.getCaseForDissFlg()==null || "".equals(vo.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(vo.getCaseForDissFlg()))
			{
				query.append(" ,Ehf_Audit ea ");
			}
			query.append(" where asri.cmb_Dtl_Id=c.case_Status and c.case_Hosp_Code=h.hosp_Id and  c.case_Patient_No=p.patient_Id and c.employee_No='"+vo.getLoginName()+"' ");
	  		query.append(" and ec.case_Id=c.case_Id and ecb.cmb_Dtl_Id= ec.case_Status and ec.case_Id=ect.case_Id and emt1.icd_proc_code=ect.icd_proc_code and emt1.state=ec.scheme_id and emt1.process in ('IP','DOP') ");
			
			if(vo.getCaseForDissFlg()==null || "".equals(vo.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(vo.getCaseForDissFlg()))
			{
				query.append(" and ea.case_Id= c.case_Id and (ea.act_Id='"+config.getString("preauth_discharge_updated")+"' or c.case_Status = '"+config.getString("CASE.OPCaseRegistered")+"')");
			}
			
			if(vo.getCaseNo()!=null && !vo.getCaseNo().equals(""))
			{
				query.append(" and  c.case_No like '%"+vo.getCaseNo()+"%' ");
			}
				
			if(vo.getCaseStatus()!=null && !vo.getCaseStatus().equals("-1"))
			{
				query.append(" and c.case_Status='"+vo.getCaseStatus()+"' ");
			}
			try{
			if(vo.getFromDate()!=null && !vo.getFromDate().equals(""))
			{
				String fromDate=vo.getFromDate().toString();
				sdfFromDate=sdf1.format(sdf.parse(fromDate).getTime());
				String toDate=vo.getToDate().toString();
				sdfToDate=sdf1.format(sdf.parse(toDate).getTime());
				Calendar cal = Calendar.getInstance();  
	        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(vo.getToDate())); 
	        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
	        	//Date tomorrow = cal.getTime();  
	        	 //String lStrToDate = new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
				
				 if(database.equalsIgnoreCase("MYSQL"))
	 				query.append(" and  p.reg_Hosp_Date  between '"+sdfFromDate+"' and '"+sdfToDate+"'");
				
				 if(database.equalsIgnoreCase("ORACLE"))
				query.append(" and trunc(p.reg_Hosp_Date)  between  TO_DATE('"+sdfFromDate+"','YYYY-MM-DD') and TO_DATE('"+sdfToDate+"','YYYY-MM-DD')");

			}
			
			
			query.append(" group by c.case_Id,ecb.cmb_Dtl_Name,c.case_No,p.patient_Id,p.name,h.hosp_Name,asri.cmb_Dtl_Name,c.case_Status,p.reg_Hosp_Date,ec.cs_Cl_Amount,ec.pck_Appv_Amt,ec.comorbid_Appv_Amt,ec.enh_Appv_Amt");
		
				list=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return list;
		}
		@Override
		public List<CasesSearchVO> getAllCaseSearchDetailsForPen(CasesSearchVO casesSearchVO) {
			// TODO Auto-generated method stub

	  		List<CasesSearchVO> list=null;
	  		String caseId=casesSearchVO.getCaseId();
	  		StringBuffer query = new StringBuffer();
	  		query.append(" select CATID,PATIPOP,ISSUESTATUS,CASENO,PATIENTID,PATNAME,HOSPNAME,caseStatus,CASESTATUSID,SUBMITTEDDATE,CLAIMAMT,PREAUTHAPPAMT,stragg(PROC) PROC from (  select distinct(c.case_Id) as CATID,p.patient_ipop as PATIPOP, ecb.cmb_Dtl_Name as ISSUESTATUS, c.case_No as CASENO,p.patient_Id as PATIENTID,p.name as PATNAME,h.hosp_Name as HOSPNAME,asri.cmb_Dtl_Name as caseStatus, " );
	  		query.append(" c.case_Status as CASESTATUSID,p.reg_Hosp_Date  as SUBMITTEDDATE, nvl(ec.cs_Cl_Amount,0) as CLAIMAMT, (nvl(ec.pck_Appv_Amt,0)+ nvl(ec.comorbid_Appv_Amt,0)  + nvl(ec.enh_Appv_Amt,0)) as PREAUTHAPPAMT ,emt1.proc_name ||'('||ect.icd_Proc_Code||')' as PROC");
	  		query.append(" from Ehf_Emp_Case_Dtls c,Ehf_Patient p ,Ehfm_Hospitals h,Ehfm_Cmb_Dtls asri, Ehf_Case ec, Ehfm_Cmb_Dtls ecb,Ehf_Case_Therapy ect,ehfm_main_therapy emt1,ehf_case ap,ehf_patient ap1  ");
	  		if(casesSearchVO.getCaseForDissFlg()==null || "".equals(casesSearchVO.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(casesSearchVO.getCaseForDissFlg()))
	  		{
	  			query.append(" ,Ehf_Audit ea ");
	  		}
	  		query.append(" where asri.cmb_Dtl_Id=c.case_Status and c.case_Hosp_Code=h.hosp_Id and  c.case_Patient_No=p.patient_Id and c.employee_No in ('"+casesSearchVO.getCardNo1()+"','"+casesSearchVO.getCardNo2()+"') ");
	  		query.append(" and ec.case_Id=c.case_Id and ecb.cmb_Dtl_Id= ec.case_Status and ec.case_Id=ect.case_Id and emt1.icd_proc_code=ect.icd_proc_code and emt1.state=ec.scheme_id and emt1.process in ('IP','DOP')");
	  		
	  		if(casesSearchVO.getCaseForDissFlg()==null || "".equals(casesSearchVO.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(casesSearchVO.getCaseForDissFlg()))
	  		{
	  			query.append(" and ea.case_Id= c.case_Id and (ea.act_Id='"+config.getString("preauth_discharge_updated")+"' or c.case_Status = '"+config.getString("CASE.OPCaseRegistered")+"')");
	  		}
	  		query.append("  and ap1.patient_id = ap.case_patient_no and ap1.card_no = p.card_no and ect.activeyn='Y' and ecb.cmb_Dtl_Name not in ('"+config.getString("op_case_status")+"')) ");
	  		query.append(" group by CATID,PATIPOP,ISSUESTATUS,CASENO,PATIENTID,PATNAME,HOSPNAME,caseStatus,CASESTATUSID,SUBMITTEDDATE,CLAIMAMT,PREAUTHAPPAMT ");
	  		query.append(" order by  SUBMITTEDDATE ");
	  		try{
	  			list=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return list;
	  	
		}
}