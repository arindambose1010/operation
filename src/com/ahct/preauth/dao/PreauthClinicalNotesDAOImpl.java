package com.ahct.preauth.dao;

import com.ahct.model.EhfCaseTherapy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
import com.ahct.preauth.vo.SQLPreauthTransVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseClinicalNotes;
import com.ahct.model.EhfCaseDocAttch;
import com.ahct.model.EhfCaseIpDrugs;
import com.ahct.model.EhfCaseMedicalDtls;
import com.ahct.model.EhfCaseSurgicalDtls;
import com.ahct.model.EhfDischargeSummary;
import com.ahct.model.EhfEnrollAudit;
import com.ahct.model.EhfEnrollAuditId;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfPatient;
//import com.ahct.model.EhfmPreauthWorkflow;
//import com.ahct.model.EhfmPreauthWorkflowId;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria.CriteriaOperator;


/**
 * 
 * @author Anitha
 * @version jdk1.5
 * @ClassDescription This class functions to get details for Login Information.
 */
public class PreauthClinicalNotesDAOImpl implements PreauthClinicalNotesDAO {
    GenericDAO genericDao;
    SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy");
    static Logger glogger=Logger.getLogger(PreauthClinicalNotesDAOImpl.class);
    public void setGenericDao(GenericDAO genericDao) {
        this.genericDao = genericDao;
    }
    private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
    public List<PreauthClinicalNotesVO> getTherapyDetails(String pStrClinicalId,String pStrCaseId) throws Exception{
    StringBuffer lQueryBuffer=new StringBuffer();
    String args[]=new String[1];
    args[0]=pStrClinicalId;
        List<PreauthClinicalNotesVO> clinicalNotesList= new ArrayList<PreauthClinicalNotesVO>();
    lQueryBuffer.append("   select a2.clinical_id CLINICALID,a2.condition_start_infusion CNDNT_STR_INF,a2.chemo_therapy_given_yn CHEMO_YN,a2.blood_products_given_yn BLOOD_YN,\n" + 
    "  a2.radiation_therapy_given_yn RADIATN_YN, to_char(a2.infusion_start_time, 'dd/mm/yyyy') INFSTRDT,\n" + 
    "         a2.infusion_started_time INF_STR_TIME,a2.infusion_ended_time INF_END_TIME,\n" + 
    "         nvl(to_char(a2.infusion_end_time, 'dd/MM/yyyy'),'--NA--') INFENDDT,a2.chemo_therapy_given_yn CHEMO_YN,\n" + 
    "         a2.radiation_therapy_given_yn RADIATN_YN, a2.blood_products_given_yn BLOOD_YN,\n" + 
    "         a2.condition_start_infusion CNDNT_STR_INF, a2.condition_end_infusion CNDNT_END_INF,a2.condition_end_infusion CNDNT_END_INF,    \n" + 
    "         a2.condition_complications_desc CONDTN_CMPLCTN_DESC from asrit_case_clinical_notes_2 a2\n" + 
    "         where a2.clinical_id = ? " );
        try{
 clinicalNotesList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString(),args);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return clinicalNotesList;
    }
    
    /**
     * To save pre and post clinical data
     */
    @Transactional
    public String saveClinicalNotes(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId)
    {
    	String lstrResultMsg="success";
    	try{
        SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy"); 
        
        
 /*lQueryBuffer=new StringBuffer();
        
        lQueryBuffer.append(" SELECT clinical_id_seq.NEXTVAL||'' ID  FROM DUAL");
        List<PreauthClinicalNotesVO> clinicalIdList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString());
        if(!clinicalIdList.isEmpty() && clinicalIdList.get(0).getID()!=null){
            lStrClinicalId=clinicalIdList.get(0).getID();            
        }*/
        Long lStrClinicalId = getNextVal(config.getString("EHF_CASE_CLINICAL_NOTES_seq"));
        
       
        EhfCaseClinicalNotes asritCaseClinicalNotes=new EhfCaseClinicalNotes();
        asritCaseClinicalNotes.setClinicalId("CC"+lStrClinicalId);
        asritCaseClinicalNotes.setCaseId(preauthClinicalNotesVO.getCASEID());
        if(preauthClinicalNotesVO.getINVESTIGATIONDATE()!=null)
        	asritCaseClinicalNotes.setInvestigationDate(sdfw.parse(preauthClinicalNotesVO.getINVESTIGATIONDATE()));
        asritCaseClinicalNotes.setBpSystolic(preauthClinicalNotesVO.getBLOODPRESSURE());
        asritCaseClinicalNotes.setPulse(preauthClinicalNotesVO.getPULSE());
        asritCaseClinicalNotes.setTemperature(preauthClinicalNotesVO.getTEMPERATURE());
        asritCaseClinicalNotes.setHeartRate(preauthClinicalNotesVO.getHEART_RATE());
        asritCaseClinicalNotes.setWardType(preauthClinicalNotesVO.getWARD_TYPE());
        asritCaseClinicalNotes.setLungsCondition(preauthClinicalNotesVO.getLUNGS());
        asritCaseClinicalNotes.setRespiratory(preauthClinicalNotesVO.getRESPIRATORY());
        asritCaseClinicalNotes.setFluidInput(preauthClinicalNotesVO.getFLUIDINPT());
        asritCaseClinicalNotes.setFluidOutput(preauthClinicalNotesVO.getFLUIDOUTPUT());
        //asritCaseClinicalNotes.setWardInTime(preauthClinicalNotesVO.getWARDINTIME());
        //asritCaseClinicalNotes.setWardOutTime(preauthClinicalNotesVO.getWARDOUTTIME());
        asritCaseClinicalNotes.setCrtDt( new java.sql.Timestamp ( new Date().getTime () ) ) ;
        asritCaseClinicalNotes.setCrtUsr(pStrUserId);
        asritCaseClinicalNotes.setPrePostOperative(preauthClinicalNotesVO.getPREPOSTFLAG());
        asritCaseClinicalNotes.setLstUpdUsr(pStrUserId);
        asritCaseClinicalNotes.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
        asritCaseClinicalNotes.setWoundStatus(preauthClinicalNotesVO.getWOUND_STATUS());
        if(preauthClinicalNotesVO.getWOUND_DTLS()!=null){
            asritCaseClinicalNotes.setWoundDtls(preauthClinicalNotesVO.getWOUND_DTLS());
        }
        asritCaseClinicalNotes.setRemarks(preauthClinicalNotesVO.getREMARKS());

        if((preauthClinicalNotesVO.getCHEMO_YN()!=null && preauthClinicalNotesVO.getCHEMO_YN().equals("Y"))){
            asritCaseClinicalNotes.setTherapyDtls("chemo");
        }
        else if(preauthClinicalNotesVO.getBLOOD_YN()!=null && preauthClinicalNotesVO.getBLOOD_YN().equals("Y")){
            asritCaseClinicalNotes.setTherapyDtls("blood");
        }
        else if(preauthClinicalNotesVO.getRADIATN_YN()!=null && preauthClinicalNotesVO.getRADIATN_YN().equals("Y")){
            asritCaseClinicalNotes.setTherapyDtls("radia");
        }
		if(preauthClinicalNotesVO.getCHEMO_YN()!=null && preauthClinicalNotesVO.getCHEMO_YN().equals("Y") ||
		preauthClinicalNotesVO.getBLOOD_YN()!=null && preauthClinicalNotesVO.getBLOOD_YN().equals("Y") ||
		preauthClinicalNotesVO.getRADIATN_YN()!=null && preauthClinicalNotesVO.getRADIATN_YN().equals("Y")){
		    asritCaseClinicalNotes.setTherapyDtls("Y");
		}  
		else
            asritCaseClinicalNotes.setTherapyDtls("N");
		if(preauthClinicalNotesVO.getINVESTIGATIONDATE()!=null)	
			asritCaseClinicalNotes.setInvestigationDate(sdfw.parse(preauthClinicalNotesVO.getINVESTIGATIONDATE()));        
		asritCaseClinicalNotes.setDocName(preauthClinicalNotesVO.getDocName());
		asritCaseClinicalNotes.setPlasmaBbf(preauthClinicalNotesVO.getPlasmaBbf());
		asritCaseClinicalNotes.setPlasmaBd(preauthClinicalNotesVO.getPlasmaBd());
		asritCaseClinicalNotes.setPlasmaBl(preauthClinicalNotesVO.getPlasmaBl());
		asritCaseClinicalNotes.setPlasmaMn(preauthClinicalNotesVO.getPlasmaMn());
		asritCaseClinicalNotes.setInsulinBbf(preauthClinicalNotesVO.getInsulinBbf());
		asritCaseClinicalNotes.setInsulinBd(preauthClinicalNotesVO.getInsulinBd());
		asritCaseClinicalNotes.setInsulinSr(preauthClinicalNotesVO.getInsulinSr());
		asritCaseClinicalNotes.setInsulinMn(preauthClinicalNotesVO.getInsulinMn());
		genericDao.save(asritCaseClinicalNotes);
       
		
		
		
		EhfCaseIpDrugs ehfDrugs=null;
		if(preauthClinicalNotesVO.getDrugs()!=null){
		for(DrugsVO drugsVO : preauthClinicalNotesVO.getDrugs())
		{
			ehfDrugs = new EhfCaseIpDrugs(); 
			ehfDrugs.setDrugId(getNextVal("ip_drug_seq"));
			ehfDrugs.setCaseId(preauthClinicalNotesVO.getCASEID());
			ehfDrugs.setClinicalId("CC"+lStrClinicalId);
			ehfDrugs.setDrugTypeCode(drugsVO.getDrugTypeName());
			ehfDrugs.setDrugSubTypeCode(drugsVO.getDrugSubTypeName());
			ehfDrugs.setPharSubGrpCode(drugsVO.getpSubGrpCode());
			ehfDrugs.setCheSubGrpCode(drugsVO.getcSubGrpCode());
			ehfDrugs.setDrugCode(drugsVO.getDrugName());
			ehfDrugs.setRouteType(drugsVO.getRouteType());
			ehfDrugs.setRoute(drugsVO.getRoute());
			ehfDrugs.setStrengthType(drugsVO.getStrengthType());
			ehfDrugs.setStrength(drugsVO.getStrength());
			ehfDrugs.setDosage(drugsVO.getDosage());
			ehfDrugs.setMedicationPeriod(drugsVO.getMedicationPeriod());
			ehfDrugs.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			ehfDrugs.setCrtUsr(pStrUserId);
			genericDao.save(ehfDrugs);
		}
		}
		
        if(preauthClinicalNotesVO.getPREPOSTFLAG()!=null && preauthClinicalNotesVO.getPREPOSTFLAG().equalsIgnoreCase("PRE"))
        	lstrResultMsg="Clinical Notes added successfully";
        else if(preauthClinicalNotesVO.getPREPOSTFLAG()!=null && preauthClinicalNotesVO.getPREPOSTFLAG().equalsIgnoreCase("POST"))
        	lstrResultMsg="Post clinical notes added successfully";
    }     
    catch(Exception e)
    {
    	lstrResultMsg="failure";
    	e.printStackTrace();
    	//glogger.error("Error in saveClinicalNotes() of PreauthClinicalNotesDAOImpl"+e.getMessage());
    }
    	return lstrResultMsg;
 }
    
    
    @Transactional
    public boolean saveSurgeryDetails(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserRole,String pStrUserId) throws Exception{
        boolean statusFlag=true;     
      
        //String lStrNextStatus=null;
        Long lActOrder=0L;
        String lStrMedMntFlag=null;
        //String lServrDt=null;       
        lStrMedMntFlag=getMedMngmtFlag(preauthClinicalNotesVO.getCASEID());
        StringBuffer lQueryBuffer=new StringBuffer();
        String args[]=new String[1];  
        
        
        /**
         * Removed By Srikalyan to Save the Actual Treated Units for Dental Surgery Case at the time of Surgery Updation
         * Code has been moved to Discharge Level. 
         */
        /*if(preauthClinicalNotesVO.getDentCond()!=null && "Y".equalsIgnoreCase(preauthClinicalNotesVO.getDentCond())
        		&& preauthClinicalNotesVO.getCaseUnits()!=null && !"".equalsIgnoreCase(preauthClinicalNotesVO.getCaseUnits()))
        	{
        		String[] mainList = preauthClinicalNotesVO.getCaseUnits().split("~");
        		String[] toothedList = preauthClinicalNotesVO.getToothedUnits().split("~");
        		for (int i=0;i<mainList.length;i++)
        			{
        				String[] caseThe=mainList[i].split("@");
        				String[] toothedUnits=toothedList[i].split("@");
        				if(caseThe!=null && caseThe[0]!=null && caseThe[1]!=null)
        					{
        						EhfCaseTherapy ehfCaseTherapy=genericDao.findById(EhfCaseTherapy.class, Long.class, Long.parseLong(caseThe[0]));
        						if(ehfCaseTherapy!=null)
        							{
        								if(toothedUnits[0]!=null && toothedUnits[1]!=null && toothedUnits[0].contains(caseThe[0]))
        									ehfCaseTherapy.setToothedUnits(toothedUnits[1]);
        								
        								ehfCaseTherapy.setSurgProcUnits(caseThe[1]);
        								ehfCaseTherapy=genericDao.save(ehfCaseTherapy);
        							}
        					}
        			}
        	}*/
    	try
    	{
    		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
    	    criteriaList.add(new GenericDAOQueryCriteria("id.caseId",preauthClinicalNotesVO.getCASEID(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
    	    lActOrder= genericDao.getMaxByPropertyCriteria(EhfAudit.class, "id.actOrder",criteriaList);
    	    lActOrder=lActOrder+1;
    		
    	}
    	catch(Exception e)
    	{
//    		// glogger.error("actOrder"+lActOrder);
//   		 	glogger.error("CaseId"+preauthClinicalNotesVO.getCASEID());
    		glogger.error("Error in saveSurgeryDetails class PreauthClinicalNotesDaoImpl.java--> "+ e.getMessage());
    	}
        
        if(lActOrder.intValue()==0)
        	{
	        	lQueryBuffer.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
	        	args[0]=preauthClinicalNotesVO.getCASEID();
	        	List<PreauthClinicalNotesVO> actOrderList=new ArrayList<PreauthClinicalNotesVO>();
	        	try
	        	{
	        		actOrderList=genericDao.executeHQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString(),args);
	        		if(actOrderList!=null && !actOrderList.isEmpty() && actOrderList.get(0).getCOUNT()!=null)
			        {
			            if(actOrderList.get(0).getCOUNT().longValue()>=0)
			            	lActOrder=actOrderList.get(0).getCOUNT().longValue()+1;
			        }
	        	}
	        	catch(Exception e)
	        	{
//	        		// glogger.error("actOrder"+lActOrder);
//	       		 	glogger.error("CaseId"+preauthClinicalNotesVO.getCASEID());
	        		glogger.error("Error in saveSurgeryDetails class PreauthClinicalNotesDaoImpl.java--> "+ e.getMessage());
	        	}
			        
        	}       
        try{
        EhfCase asritCase=genericDao.findById(EhfCase.class,String.class,preauthClinicalNotesVO.getCASEID());
        
        if(preauthClinicalNotesVO.getCASEID()!=null && asritCase.getCaseStatus()!=null){
       // lStrNextStatus=getNextStatusForCase(preauthClinicalNotesVO.getCASEID(),pStrUserRole);
        }              
        
        // update asrit_case
        if(preauthClinicalNotesVO.getDEATHDT()!=null && !"".equals(preauthClinicalNotesVO.getDEATHDT())){
            asritCase.setCsDeathDt(sdfw.parse(preauthClinicalNotesVO.getDEATHDT()));
        }
        asritCase.setCaseStatus(config.getString("ehf_clinical_surgeryUpdate"));
        if(preauthClinicalNotesVO.getSURGERYDATE()!=null)
        asritCase.setCsSurgDt(sdfw.parse( preauthClinicalNotesVO.getSURGERYDATE()));
        asritCase.setCsSurgUpdBy(pStrUserId);
        asritCase.setCsSurgUpdDt(new java.sql.Timestamp ( new Date().getTime () ));
        asritCase.setLstUpdDt(new java.sql.Timestamp ( new Date().getTime () ));
        asritCase.setLstUpdUsr(pStrUserId);
        genericDao.save(asritCase);
        // insert into asrit_audit
        EhfAudit asritAudit=new EhfAudit();
        EhfAuditId asritAuditPK=new EhfAuditId(lActOrder,preauthClinicalNotesVO.getCASEID());
        asritAudit.setId(asritAuditPK);               
        asritAudit.setActId(config.getString("ehf_clinical_surgeryUpdate"));
        asritAudit.setActBy(pStrUserId);
        asritAudit.setCrtUsr(pStrUserId);
        asritAudit.setCrtDt(new java.sql.Timestamp ( new Date().getTime () ));
        asritAudit.setLangId("en_US");
        //asritAudit.setAcctsConsumed("N");
        try{
        genericDao.save(asritAudit);}
        catch(Exception e)
        {
//        	// glogger.error("actOrder"+lActOrder);
//        	glogger.error("actOrder"+preauthClinicalNotesVO.getCASEID());
        	glogger.error("Error in method getMaxActOrder of class PreauthClinicalNotesDaoImpl.java--> "+ e.getMessage());
        	}
    
       /* // update asrit_case_process
        AsritCaseProcess asritCaseProcess=new AsritCaseProcess();
            asritCaseProcess=genericDao.findById(AsritCaseProcess.class,String.class,asritCase.getCaseId());
            asritCaseProcess.setCaseDtSurgery(sdfw.parse(preauthClinicalNotesVO.getSURGERYDATE()));    
        asritCaseProcess.setSurgeryUpdBy(pStrUserId);
        asritCaseProcess.setSurgeryUpdDt(new java.sql.Timestamp ( new Date().getTime () ));
        asritCaseProcess.setLstUpdDt(new java.sql.Timestamp ( new Date().getTime () ));
        asritCaseProcess.setLstUpdUsr(pStrUserId);
            if(preauthClinicalNotesVO.getDEATHDT()!=null && !"".equals(preauthClinicalNotesVO.getDEATHDT())){
                asritCaseProcess.setDeathDt(sdfw.parse(preauthClinicalNotesVO.getDEATHDT()));
            }
        genericDao.save(asritCaseProcess);*/
        
        if(preauthClinicalNotesVO.getMedicalFlag()!=null && preauthClinicalNotesVO.getMedicalFlag().equals("Y")){
            // insert into asrit_case_medmgnt_dtls
             EhfCaseMedicalDtls asritCaseMedmgntDtls=new EhfCaseMedicalDtls();
            asritCaseMedmgntDtls=genericDao.findById(EhfCaseMedicalDtls.class,String.class,preauthClinicalNotesVO.getCASEID());
           if(asritCaseMedmgntDtls==null){            
              asritCaseMedmgntDtls=new EhfCaseMedicalDtls();
               asritCaseMedmgntDtls.setCrtDt( new java.sql.Timestamp ( new Date().getTime () ) ) ;
               asritCaseMedmgntDtls.setCrtUsr(pStrUserId);
           }
            asritCaseMedmgntDtls.setCaseId(preauthClinicalNotesVO.getCASEID());
            asritCaseMedmgntDtls.setSurgeonName(preauthClinicalNotesVO.getTreatSurgeonName());
            asritCaseMedmgntDtls.setSurgeonRegno(preauthClinicalNotesVO.getTreatSurgeonRegNo());
            asritCaseMedmgntDtls.setSurgeonQual(preauthClinicalNotesVO.getTreatSurgeonQual());
            asritCaseMedmgntDtls.setSurgeonCntctNo(preauthClinicalNotesVO.getTreatSurgeonCnctNo());
          //  asritCaseMedmgntDtls.setAdmnDt(sdfw.parse(preauthClinicalNotesVO.getTreatSurgStartDt()));
        /*    asritCaseMedmgntDtls.setSurgStartTime(preauthClinicalNotesVO.getSURGSTARTTIME());
            asritCaseMedmgntDtls.setSurgEndTime(preauthClinicalNotesVO.getSURGENDTIME());*/
            asritCaseMedmgntDtls.setSurgAsstName(preauthClinicalNotesVO.getTreatAsstSurName());
            asritCaseMedmgntDtls.setSurgAsstRegno(preauthClinicalNotesVO.getTreatAsstSurRegNo());
            asritCaseMedmgntDtls.setSurgAsstQual(preauthClinicalNotesVO.getTreatAsstSurQual());
            asritCaseMedmgntDtls.setSurgAsstCntctno(preauthClinicalNotesVO.getTreatAsstSurContctNo());
            asritCaseMedmgntDtls.setParamedicName(preauthClinicalNotesVO.getTreatParadMedicName());
            asritCaseMedmgntDtls.setNurseName(preauthClinicalNotesVO.getTreatNurseName());
            asritCaseMedmgntDtls.setFloor(preauthClinicalNotesVO.getSFLOOR());
            asritCaseMedmgntDtls.setRoomNo(preauthClinicalNotesVO.getSROOM_NO());
            asritCaseMedmgntDtls.setExpectdHospStay(preauthClinicalNotesVO.getTreatExpHospStay()); 
            asritCaseMedmgntDtls.setLstUpdDt(new java.sql.Timestamp ( new Date().getTime () ));
            asritCaseMedmgntDtls.setLstUpdUsr(pStrUserId);
            genericDao.save(asritCaseMedmgntDtls);
            
            
        }
         if(lStrMedMntFlag!=null && lStrMedMntFlag.equals("N")){
            //insert into asrit_case_pac_operation_dtls
            
            EhfCaseSurgicalDtls asritCasePacOperationDtls =new EhfCaseSurgicalDtls();
            asritCasePacOperationDtls=genericDao.findById(EhfCaseSurgicalDtls.class,String.class,preauthClinicalNotesVO.getCASEID());
            if(asritCasePacOperationDtls==null){            
               asritCasePacOperationDtls=new EhfCaseSurgicalDtls();
                asritCasePacOperationDtls.setCrtDt(new java.sql.Timestamp ( new Date().getTime () ));
                asritCasePacOperationDtls.setCrtUsr(pStrUserId);
            }
            asritCasePacOperationDtls.setCaseId(preauthClinicalNotesVO.getCASEID());
            asritCasePacOperationDtls.setSurgeonName(preauthClinicalNotesVO.getSURGEON_NAME());
            asritCasePacOperationDtls.setSurgeryRegno(preauthClinicalNotesVO.getSURGEON_REGNO());
            asritCasePacOperationDtls.setSurgeonQual(preauthClinicalNotesVO.getSURGEON_QUAL());
            asritCasePacOperationDtls.setSurgeryCntctNo(preauthClinicalNotesVO.getSURGEON_CNTCTNO());
            asritCasePacOperationDtls.setSurgeryAsst1Name(preauthClinicalNotesVO.getASST_SURG_NAME());
            asritCasePacOperationDtls.setSurgeryAsst1Regno(preauthClinicalNotesVO.getASST_SURG_REGNO());
            asritCasePacOperationDtls.setSurgeryAsst1Qual(preauthClinicalNotesVO.getASST_SURG_QUAL());
            asritCasePacOperationDtls.setSurgeryAsst1CntctNo(preauthClinicalNotesVO.getASST_SURG_CNTCTNO());
            asritCasePacOperationDtls.setParamedicName(preauthClinicalNotesVO.getPARAMEDIC_NAME());
            asritCasePacOperationDtls.setNurseName(preauthClinicalNotesVO.getNURSE_NAME());
            asritCasePacOperationDtls.setFloor(preauthClinicalNotesVO.getSFLOOR());
            asritCasePacOperationDtls.setRoomNo(preauthClinicalNotesVO.getSROOM_NO());
            asritCasePacOperationDtls.setExpectedHospStay(preauthClinicalNotesVO.getEXP_HOSP_STAY()); 
            asritCasePacOperationDtls.setAnaesthetistName(preauthClinicalNotesVO.getANESTNAME()); 
            asritCasePacOperationDtls.setAnaesthetistRegNo(preauthClinicalNotesVO.getANESTREGNO()); 
            asritCasePacOperationDtls.setAnaesthetistMbNo(preauthClinicalNotesVO.getANESTMOBILENO()); 
            asritCasePacOperationDtls.setSurgStartTime(preauthClinicalNotesVO.getSURGSTARTTIME());
            asritCasePacOperationDtls.setSurgEndTime(preauthClinicalNotesVO.getSURGENDTIME());
            
            asritCasePacOperationDtls.setAnesthesiaType(preauthClinicalNotesVO.getAnesthesiaType());
            asritCasePacOperationDtls.setIncisionType(preauthClinicalNotesVO.getIncisionType());
            asritCasePacOperationDtls.setOpPhotoWebEx(preauthClinicalNotesVO.getIntraOpPotos());
            asritCasePacOperationDtls.setVideorec(preauthClinicalNotesVO.getVideoRec());
            asritCasePacOperationDtls.setSwabCnt(preauthClinicalNotesVO.getSwabCount());
            asritCasePacOperationDtls.setSurturesLigatures(preauthClinicalNotesVO.getSutureLigature());
            asritCasePacOperationDtls.setSpecimenRem(preauthClinicalNotesVO.getSpecimenRem());
            asritCasePacOperationDtls.setDrainageCnt(preauthClinicalNotesVO.getDrainageCnt());
            asritCasePacOperationDtls.setBloodLoss(preauthClinicalNotesVO.getBloodLosss());
            asritCasePacOperationDtls.setComplications(preauthClinicalNotesVO.getComplications());
            asritCasePacOperationDtls.setPostOperInstru(preauthClinicalNotesVO.getPostOperativeInst());
            asritCasePacOperationDtls.setCondOfPat(preauthClinicalNotesVO.getConditiOfPat());
            asritCasePacOperationDtls.setSpecimenName(preauthClinicalNotesVO.getSpecimenName());
            asritCasePacOperationDtls.setComplicationsRemarks(preauthClinicalNotesVO.getComplicationsRemarks());
            
            
            genericDao.save(asritCasePacOperationDtls);
        }
        
        }
        catch(Exception e){
            statusFlag=false;
            e.printStackTrace();
//            glogger.error("Error in saveSurgeryDate() of PreauthClinicalNotesDAOImpl"+e.getMessage());
        }
        return statusFlag;
    }
    
    /**
     * 
     * @param pStrCaseId
     * @param pStrUserRole
     * @return Next Status--- To retrieve next status from preauth workflow table
     */
    /*private String getNextStatusForCase(String pStrCaseId,String pStrUserRole)
    {
    //StringBuffer lStringBuffer=new StringBuffer();
    String lStrNextStatus=null;
    String lStrMediMngmntFlag=null;
	String lStrActionDone=null;  
	try{
	          lStrMediMngmntFlag=getMedMngmtFlag(pStrCaseId);
	}
	catch(Exception e){
	    e.printStackTrace();
	}
      if(lStrMediMngmntFlag!=null && lStrMediMngmntFlag.equalsIgnoreCase("Y")){
          lStrActionDone="TRMT";
      }
      else if(lStrMediMngmntFlag!=null && lStrMediMngmntFlag.equalsIgnoreCase("N")){
          lStrActionDone="UPD";
      }               
      try{
        
        
        lStringBuffer=new StringBuffer(); 
        lStringBuffer.append("  select apw.nextStatus as VALUE  from AsrimPreauthWorkflow apw,AsritCase ac ");
        lStringBuffer.append("  where apw.id.currentStatus = ac.caseStatus and ac.caseId = '"+pStrCaseId+"' and ");
        lStringBuffer.append("  apw.id.actionUsr = '"+pStrUserRole+"' and apw.id.actionDone = upper('"+lStrActionDone+"') ");
        
        List<PreauthClinicalNotesVO> nextStatusList=genericDao.executeHQLQueryList(PreauthClinicalNotesVO.class,lStringBuffer.toString());
        if(nextStatusList!=null && !nextStatusList.isEmpty() && nextStatusList.get(0).getVALUE()!=null){
            lStrNextStatus=nextStatusList.get(0).getVALUE();
        }
        	EhfmPreauthWorkflow asrimPreauthWorkflow=null;
        	EhfCase ehfCase=genericDao.findById(EhfCase.class,String.class,pStrCaseId);
        	if(ehfCase!=null)
        		asrimPreauthWorkflow=genericDao.findById(EhfmPreauthWorkflow.class,EhfmPreauthWorkflowId.class,new EhfmPreauthWorkflowId(ehfCase.getCaseStatus(),pStrUserRole,lStrActionDone));
        	if(asrimPreauthWorkflow!=null)
        		lStrNextStatus=asrimPreauthWorkflow.getNextStatus();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(lStrNextStatus==null)
        	lStrNextStatus="CD329";
        return lStrNextStatus;
        
    }*/
    public String getTelephneIntimationDate(String pStrCaseId) throws Exception{
        StringBuffer lQueryBuffer=new StringBuffer();
        String telDate=null;
        lQueryBuffer.append(" select to_char(atr.crt_date,'dd/MM/yyyy') VALUE from asrit_telephonic_regn atr,asrit_case ac ");
           lQueryBuffer.append(" where atr.patient_id=ac.case_patient_no  and ac.case_id=? ");
           String args[]=new String[1];
           args[0]=pStrCaseId;
           try{
           List<PreauthClinicalNotesVO> dateList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString(),args);
           if(!dateList.isEmpty() && dateList.get(0).getVALUE()!=null){
               telDate=dateList.get(0).getVALUE();
           }}
           catch(Exception e){
               e.printStackTrace();
           }
           return telDate;
    }
   
    
    @Transactional
    public boolean saveDischargeSummaryDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId,String pStrUserRole,String pStrDisDeathFlag) throws Exception{
    Date lCurrentDt=new Date();
    SimpleDateFormat sdformat=new SimpleDateFormat("dd/MM/yyyy");
    boolean disUpdFlag=true;
    Long lActOrder=0L;
    Date lCurrentDate1=new java.sql.Timestamp(new Date().getTime()); 
     try{
        /*String args[]=new String[1];        
        lQueryBuffer.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
        args[0]=preauthClinicalNotesVO.getCASEID();        
        List<PreauthClinicalNotesVO> actOrderList=genericDao.executeHQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString(),args);
        if(!actOrderList.isEmpty() && actOrderList.get(0)!=null && actOrderList.get(0).getCOUNT().longValue()>=0){
            lActOrder=actOrderList.get(0).getCOUNT().longValue()+1;
        }*/
    	 
		    	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		    	 criteriaList.add(new GenericDAOQueryCriteria("id.caseId",preauthClinicalNotesVO.getCASEID(),CriteriaOperator.EQUALS));
		    	 try{
		    	 lActOrder=genericDao.getMaxByPropertyCriteria(EhfAudit.class,"id.actOrder",criteriaList);
		    	 lActOrder=lActOrder+1;
    	 	}
    	 catch(Exception e)
    	 	{
    		 // glogger.error("actOrder"+lActOrder);
//    		 glogger.error("CaseId"+preauthClinicalNotesVO.getCASEID());
    		 glogger.error("Error in method saveDischargeSummaryDtls of class PreauthClinicalNotesDAOImpl.java and actOrder-->"+ e.getMessage());
    	 	}
		    	 
    	 /**
          * Added By Srikalyan to Save the Actual Treated Units for Dental Surgery Case at the time of Surgery Updation 
          */
         if(preauthClinicalNotesVO.getDentCond()!=null && "Y".equalsIgnoreCase(preauthClinicalNotesVO.getDentCond())
         		&& preauthClinicalNotesVO.getCaseUnits()!=null && !"".equalsIgnoreCase(preauthClinicalNotesVO.getCaseUnits()))
         	{
         		String[] mainList = preauthClinicalNotesVO.getCaseUnits().split("~");
         		String[] toothedList = preauthClinicalNotesVO.getToothedUnits().split("~");
         		for (int i=0;i<mainList.length;i++)
         			{
         				String[] caseThe=mainList[i].split("@");
         				String[] toothedUnits=toothedList[i].split("@");
         				if(caseThe!=null && caseThe[0]!=null && caseThe[1]!=null)
         					{
         						EhfCaseTherapy ehfCaseTherapy=genericDao.findById(EhfCaseTherapy.class, Long.class, Long.parseLong(caseThe[0]));
         						if(ehfCaseTherapy!=null)
         							{
         								if(toothedUnits.length>1 && toothedUnits[0]!=null && toothedUnits[1]!=null && toothedUnits[0].contains(caseThe[0]))
         									ehfCaseTherapy.setToothedUnits(toothedUnits[1]);
         								
         								if(!caseThe[1].equalsIgnoreCase("-1") && (!"".equalsIgnoreCase(preauthClinicalNotesVO.getRole()) && "GP2".equalsIgnoreCase(preauthClinicalNotesVO.getRole())))
         								{
         									ehfCaseTherapy.setSurgProcUnits(caseThe[1]);
         									ehfCaseTherapy.setMedcoProcUnits(caseThe[1]);
         									ehfCaseTherapy.setLstUpdUsr(pStrUserId);
         									ehfCaseTherapy.setLstUpdDt(lCurrentDate1);
         								}
         								ehfCaseTherapy=genericDao.save(ehfCaseTherapy);
         							}
         					}
         			}
         	} 	 
        
    	 //  insert or update asrit_discharge_summary
         EhfDischargeSummary asritDischargeSummary=genericDao.findById(EhfDischargeSummary.class,String.class,preauthClinicalNotesVO.getCASEID());
        if(asritDischargeSummary==null){
            asritDischargeSummary=new EhfDischargeSummary();
        }
        asritDischargeSummary.setCaseId(preauthClinicalNotesVO.getCASEID());
        if(preauthClinicalNotesVO.getNEXTFOLLOWUPDT()!=null && !preauthClinicalNotesVO.getNEXTFOLLOWUPDT().equals(""))
        {
            Date lDate=sdformat.parse(preauthClinicalNotesVO.getNEXTFOLLOWUPDT());
             asritDischargeSummary.setNextFollowupDt(new java.sql.Timestamp (lDate.getTime() )); 
        }
        if(preauthClinicalNotesVO.getROOMNO()!=null)
        asritDischargeSummary.setRoomNo(preauthClinicalNotesVO.getROOMNO());
        if(preauthClinicalNotesVO.getFLOORNO()!=null)
        asritDischargeSummary.setFloorNo(preauthClinicalNotesVO.getFLOORNO());
        if(preauthClinicalNotesVO.getBLOCKNAME()!=null)
        asritDischargeSummary.setBlockName(preauthClinicalNotesVO.getBLOCKNAME());
        
        if(preauthClinicalNotesVO.getTREATMNT_GVN()!=null)
        asritDischargeSummary.setTreatmentGiven(preauthClinicalNotesVO.getTREATMNT_GVN());
        
        if(preauthClinicalNotesVO.getOPERATION_FNDNGS()!=null)
        asritDischargeSummary.setOperativeFindings(preauthClinicalNotesVO.getOPERATION_FNDNGS());
        
        if(preauthClinicalNotesVO.getPOST_OPERATVE_PERIOD()!=null)
        asritDischargeSummary.setPostOperPeriod(preauthClinicalNotesVO.getPOST_OPERATVE_PERIOD());
        
        if(preauthClinicalNotesVO.getPOST_SPL_INVSTGNS()!=null)
        asritDischargeSummary.setPostSplInvestgtns(preauthClinicalNotesVO.getPOST_SPL_INVSTGNS());
        
        if(preauthClinicalNotesVO.getSTATUS_DISCARGE()!=null)
        asritDischargeSummary.setDischargeStatus(preauthClinicalNotesVO.getSTATUS_DISCARGE());
        
        if(preauthClinicalNotesVO.getADVICE()!=null)
        asritDischargeSummary.setAdvise(preauthClinicalNotesVO.getADVICE());
        if(preauthClinicalNotesVO.getREVIEW()!=null)
        asritDischargeSummary.setReview(preauthClinicalNotesVO.getREVIEW());
        
        if(preauthClinicalNotesVO.getCAUSE_OF_DEATH()!=null)
            asritDischargeSummary.setCauseOfDeath(preauthClinicalNotesVO.getCAUSE_OF_DEATH());
                   
        if(asritDischargeSummary.getCrtDt()==null)
        asritDischargeSummary.setCrtDt(lCurrentDate1);
        if(asritDischargeSummary.getCrtDt()==null)
        asritDischargeSummary.setCrtUsr(pStrUserId);
        asritDischargeSummary.setLstUpdDt(lCurrentDate1);
        asritDischargeSummary.setLstUpdUsr(pStrUserId);
        
        genericDao.save(asritDischargeSummary);   
        // update asrit_case_proces        
        /*AsritCaseProcess asritCaseProcess=genericDao.findById(AsritCaseProcess.class,String.class,preauthClinicalNotesVO.getCASEID());
        if(asritCaseProcess!=null)
        {
	        if(pStrDisDeathFlag!=null && pStrDisDeathFlag.equalsIgnoreCase("discharge"))
	        	asritCaseProcess.setDischargeDt(sdfw.parse(preauthClinicalNotesVO.getDISCHARGEDT()));
	        else if (pStrDisDeathFlag!=null && pStrDisDeathFlag.equalsIgnoreCase("death")){
	            asritCaseProcess.setDeathDt(sdfw.parse(preauthClinicalNotesVO.getDISCHARGEDT()));
	        }
        }*/
        //update asrit_case
        EhfCase asritcase=genericDao.findById(EhfCase.class,String.class,preauthClinicalNotesVO.getCASEID());
             asritcase.setLstUpdDt(lCurrentDt);
             asritcase.setLstUpdUsr(pStrUserId);
        if(pStrDisDeathFlag!=null && pStrDisDeathFlag.equalsIgnoreCase("discharge") && preauthClinicalNotesVO.getDISCHARGEDT()!=null)
        	asritcase.setCsDisDt(sdfw.parse(preauthClinicalNotesVO.getDISCHARGEDT()));
        else if (pStrDisDeathFlag!=null && "death".equalsIgnoreCase(pStrDisDeathFlag) && preauthClinicalNotesVO.getDISCHARGEDT()!=null){
            asritcase.setCsDeathDt(sdfw.parse(preauthClinicalNotesVO.getDISCHARGEDT()));
        }
        
       //Sneha-update EHF_ENROLLMENT_FAMILY for death case
        if(preauthClinicalNotesVO.getDIS_SAVE_SUBMIT()!=null && preauthClinicalNotesVO.getDIS_SAVE_SUBMIT().equalsIgnoreCase("Submit"))
        { 
	        if (asritcase!=null && pStrDisDeathFlag!=null && "death".equalsIgnoreCase(pStrDisDeathFlag) && preauthClinicalNotesVO.getDISCHARGEDT()!=null)
	        {
	        	EhfPatient ehfPatient= genericDao.findById(EhfPatient.class, String.class, asritcase.getCasePatientNo());
	        	
	        	if(ehfPatient!=null)
	        	{
		        	criteriaList.removeAll(criteriaList);
		        	criteriaList.add(new GenericDAOQueryCriteria("ehfCardNo",ehfPatient.getCardNo(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
		        	List<EhfEnrollmentFamily> cardList= genericDao.findAllByCriteria(EhfEnrollmentFamily.class, criteriaList);
		        	if(cardList!=null && cardList.size()>0)
		        	{
		        		
		        			
		        			//Start- Insertion in Ehf_Enrollment_Family and Ehf_Enroll_Audit
		        			int sno = 0;
		        			String arg[]= new String[1];
		        			arg[0]= cardList.get(0).getEnrollId();
		        			String query= "select MAX(id.enrollOrder) as COUNT  from EhfEnrollAudit where id.enrollId=?";
		        			List<LabelBean> orderList= genericDao.executeHQLQueryList(LabelBean.class, query, arg);
		        			if(orderList!=null && orderList.size()>0)
		        			{
		        				if (orderList.get(0).getCOUNT() != null)
									sno = orderList.get(0).getCOUNT().intValue();
		        			}
		        			String enrollParntId= cardList.get(0).getEnrollPrntId();
		        			
		        			cardList.get(0).setEnrollStatus(config.getString("inactivate_card_death_status"));
		        			cardList.get(0).setLstUpdDt(lCurrentDt);
		        			cardList.get(0).setLstUpdUsr(pStrUserId);
		        			genericDao.save(cardList.get(0));
		        			
		        			EhfEnrollAudit ehfEnrollAudit = new EhfEnrollAudit();
		        			ehfEnrollAudit.setId(new EhfEnrollAuditId(arg[0],sno + 1));
		        			ehfEnrollAudit.setEnrollParentId(enrollParntId);
		        			ehfEnrollAudit.setEnrollStatus(config.getString("inactivate_card_death_status"));
		        			ehfEnrollAudit.setEnrollActnBy(pStrUserId);
		        			ehfEnrollAudit.setEnrollActnDate(lCurrentDt);
		        			ehfEnrollAudit.setEnrollRemarks(config.getString("inactivate_card_death_remarks"));
		        			ehfEnrollAudit.setLangId("en_US");
		        			ehfEnrollAudit.setCrtUsr(pStrUserId);
		        			ehfEnrollAudit.setCrtDt(lCurrentDt);
		        			
		        			
		        			genericDao.save(ehfEnrollAudit);
		        			
		        			//End- Insertion in Ehf_Enroll_Audit table
		        		
		        	}
	        	}
	        }
	     }
        //End- update EHF_ENROLLMENT_FAMILY for death case
        
        if(preauthClinicalNotesVO.getDIS_SAVE_SUBMIT()!=null && preauthClinicalNotesVO.getDIS_SAVE_SUBMIT().equalsIgnoreCase("Submit"))
        { 
            asritcase.setCaseStatus(config.getString("ehf_clinical_dischargeUpdate"));
            asritcase.setCsDisUpdBy(pStrUserId);
            asritcase.setCsDisUpdDt(lCurrentDate1);
            
        // insert into Asrit_Audit
            
            EhfAudit asritAudit=new EhfAudit();
            EhfAuditId asritAuditPK=new EhfAuditId(lActOrder,preauthClinicalNotesVO.getCASEID());
             asritAudit.setId(asritAuditPK);
             asritAudit.setActId(config.getString("ehf_clinical_dischargeUpdate"));
             asritAudit.setActBy(pStrUserId);
             asritAudit.setCrtUsr(pStrUserId);
             asritAudit.setCrtDt(lCurrentDate1);
             asritAudit.setLangId("en_US");
             //asritAudit.setAcctsConsumed("N");
             try{
             genericDao.save(asritAudit);   
             }catch(Exception e)
             {
            	e.printStackTrace(); 
            	// glogger.error("actOrder"+lActOrder);
//            	glogger.error("actOrder"+preauthClinicalNotesVO.getCASEID());
//            	glogger.error("Error in method getMaxActOrder of class PreauthClinicalNotesDaoImpl.java--> "+ e.getMessage());
             }
             
        }
        genericDao.save(asritcase);     
        /*if(asritCaseProcess!=null)        
        	genericDao.save(asritCaseProcess); */
    }
    catch(Exception e){
        disUpdFlag=false;
//    	glogger.error("Error in saveDischargeSummaryDtls() of PreauthClinicalNotesDAOImpl"+e.getMessage());        
        e.printStackTrace();
    }
return disUpdFlag;
    } 
    
    
    
    public Long getPostOPCheckForSurgery(String pStrCaseId) throws Exception{
        StringBuffer lQueryBuffer=new StringBuffer();
        Long lPostOpChk=0L;
        String[] args=new String[4];
        args[0]="POST";
        args[1]=pStrCaseId;
        args[2]=pStrCaseId;
        args[3]="Y";
        
        lQueryBuffer.append(" select nvl(ceil((sysdate - acp.cs_surg_dt) - notes),0) COUNT ");
        lQueryBuffer.append("   from ehf_case acp,  EHFM_MAIN_THERAPY asm,ehf_case_therapy acs, ");
        lQueryBuffer.append(" (select acc.case_id, count(1) notes  from ehf_case_clinical_notes acc ");      
        lQueryBuffer.append(" where acc.pre_post_operative = ? and case_id = ? group by case_id) acc ");     
        lQueryBuffer.append("  where acc.case_id = acp.case_id and acp.case_id = ?");
        lQueryBuffer.append(" and asm.postopchk = ? and acp.case_id = acs.case_id "); 
        lQueryBuffer.append(" and acs.icd_Proc_Code = asm.ICD_PROC_CODE "); 
        lQueryBuffer.append(" group by acp.cs_surg_dt,notes"); 
                         
        try{
        List<PreauthClinicalNotesVO> surgeryDtlsList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString(),args);
            if(!surgeryDtlsList.isEmpty() && surgeryDtlsList.size()>0){    
            if(surgeryDtlsList.get(0).getCOUNT()!=null)
                lPostOpChk=surgeryDtlsList.get(0).getCOUNT().longValue();
            }
        }
        catch(Exception e){
          e.printStackTrace()  ;
        }
       return lPostOpChk;
    }
    
    
    
    public boolean saveAttachments(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId) throws Exception {
    boolean lResult=true;
    Date lCurrentDt=new Date();
    Long lDocSeqId=0L;
    String lTempVariable=null;
    StringBuffer lQueryBuffer=new StringBuffer();
    try{
        lQueryBuffer.append(" select to_char(sysdate,'yyyy') VALUE from dual");
        List<PreauthClinicalNotesVO> dateList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString());
        if(!dateList.isEmpty()){
            lTempVariable=dateList.get(0).getVALUE();
        }
      lQueryBuffer=new StringBuffer();
        lQueryBuffer.append(" SELECT clinical_id_seq.NEXTVAL||'' ID  FROM DUAL");
        List<PreauthClinicalNotesVO> docSeqList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString());
        if(!docSeqList.isEmpty()){
            lTempVariable=lTempVariable+docSeqList.get(0).getID();
        }
        if(lTempVariable!=null){
            lDocSeqId=new Long(lTempVariable);
        }
        EhfCaseDocAttch asritCaseDocAttch=new EhfCaseDocAttch();
        asritCaseDocAttch.setDocSeqId(lDocSeqId);
        asritCaseDocAttch.setSavedName(preauthClinicalNotesVO.getSAVED_FILE_NAME());
        asritCaseDocAttch.setActualName(preauthClinicalNotesVO.getFILE_NAME());
        asritCaseDocAttch.setCrtDt(lCurrentDt);
        asritCaseDocAttch.setCrtUsr(pStrUserId);
        asritCaseDocAttch.setLstUpdDt(lCurrentDt);
        asritCaseDocAttch.setLstUpdUsr(pStrUserId);
        genericDao.save(asritCaseDocAttch);
    }
    catch(Exception e){
        lResult=false;
        e.printStackTrace();
    }
      return lResult;
    }
    
    
    public Long getAttachmentsCntByType(String pStrCaseId,String pStrAttchType) throws Exception{        
        Long lAttchCnt=0L;        
        try{        
        List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
        criteriaList.add(new GenericDAOQueryCriteria("caseId",pStrCaseId,CriteriaOperator.EQUALS));
        criteriaList.add(new GenericDAOQueryCriteria("attachType",pStrAttchType,CriteriaOperator.EQUALS));
        List<EhfCaseDocAttch> attchList=genericDao.findAllByCriteria(EhfCaseDocAttch.class,criteriaList);
        if(!attchList.isEmpty()){
            lAttchCnt=Long.parseLong(attchList.size()+"");
        }
        }
        catch(Exception e){
//        	glogger.error("Error in getAttachmentsCntByType() of PreauthClinicalNotesDAOImpl"+e.getMessage());
            e.printStackTrace();
        }
        return lAttchCnt;
    }
    
   public Long checkForDeathDate(String pStrPatientId)throws Exception{
       StringBuffer lStrBuffrQuery=new StringBuffer();     
       Long lDeathDtCount=0L;
       String[] args=new String[1];
       args[0]=pStrPatientId;    
       try{
       lStrBuffrQuery.append(" select count(*) COUNT from asrit_case ac,asrit_patient ap where ac.case_patient_no=ap.patient_id\n" + 
       "and ap.ration_card_no=? and ac.cs_death_dt is not null");
       List<PreauthClinicalNotesVO> lPatientList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lStrBuffrQuery.toString(),args);
       if(lPatientList!=null){
           if(lPatientList.get(0).getCOUNT()!=null){
               lDeathDtCount=lPatientList.get(0).getCOUNT().longValue();
           }
       }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       return lDeathDtCount;
       
   }
   public Long getPhaseStatus(String pStrCaseId)throws Exception{
       StringBuffer lStrBuffer=new StringBuffer();
       Long lPhaseClose=0L;
       String[] args=new String[4];
       args[0]=pStrCaseId;
       args[1]="1";
       args[2]="J";
       args[3]="C";
       lStrBuffer.append(" select count(1) COUNT");
       lStrBuffer.append(" from asrit_case ac, asrim_phase_duration apd,asrit_patient ap ");
       lStrBuffer.append(" where ac.case_id = ? and apd.phase_id = ac.phase_id and ");
       lStrBuffer.append(" apd.renewal = ac.phase_renewal and apd.scheme_id = ? and ");
       lStrBuffer.append(" trunc(apd.end_dt) + 30 < trunc(sysdate) and ac.case_patient_no = ap.patient_id ");
       lStrBuffer.append(" and ap.card_type != ?  and ap.card_type != ? ");
       try{
           List<PreauthClinicalNotesVO> lPhaseList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lStrBuffer.toString(),args);
           if(lPhaseList!=null){
               if(lPhaseList.get(0).getCOUNT()!=null){
                   lPhaseClose =lPhaseList.get(0).getCOUNT().longValue();
               }
           }
       
       }catch(Exception e){
           e.printStackTrace();
       }
      
       return lPhaseClose;
   }
   
    public String getMedMngmtFlag(String pStrCaseId){
    String lStrMedResultFlag="YY";
        StringBuffer lQueryBuffer=new StringBuffer();
        try{       
        	lQueryBuffer.append("select distinct asr.medical_surg as FLAG from EhfCaseTherapy acs, EhfmTherapyProcMst asr where  asr.id.asriCode = acs.asriCatCode and  acs.icdProcCode = asr.id.icdProcCode and  acs.caseId = '"+pStrCaseId+"'" );
        	lQueryBuffer.append(" and 	acs.activeyn='Y'	");
        	List<SQLPreauthTransVO> list=genericDao.executeHQLQueryList(SQLPreauthTransVO.class,lQueryBuffer.toString());
        	if(list!=null && !list.isEmpty())
        	{
        		for(SQLPreauthTransVO sQLPreauthTransVO:list)
        		{
        		if(sQLPreauthTransVO.getFLAG() != null && sQLPreauthTransVO.getFLAG().equalsIgnoreCase("S"))
        		{
        			lStrMedResultFlag="N";
        			break;
        		}
        		}
        		//if(list.get(0).getFLAG()!=null && list.get(0).getFLAG().equalsIgnoreCase("M"))
        			
        	}
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return lStrMedResultFlag;    
    }
    public String getMedicalFlag(String pStrCaseId){
        String lStrMedResultFlag="N";
            StringBuffer lQueryBuffer=new StringBuffer();
            try{       
            	lQueryBuffer.append("select distinct asr.medical_surg as FLAG from EhfCaseTherapy acs, EhfmTherapyProcMst asr where  asr.id.asriCode = acs.asriCatCode and  acs.icdProcCode = asr.id.icdProcCode and  acs.caseId = '"+pStrCaseId+"'");
            	lQueryBuffer.append(" and 	acs.activeyn='Y'	");
            	List<SQLPreauthTransVO> list=genericDao.executeHQLQueryList(SQLPreauthTransVO.class,lQueryBuffer.toString());
            	if(list!=null && !list.isEmpty())
            	{
            		for(SQLPreauthTransVO sQLPreauthTransVO:list)
            		{
            		if(sQLPreauthTransVO.getFLAG() != null && sQLPreauthTransVO.getFLAG().equalsIgnoreCase("M"))
            		{
            			lStrMedResultFlag="Y";
            			break;
            		}
            		}
            		//if(list.get(0).getFLAG()!=null && list.get(0).getFLAG().equalsIgnoreCase("M"))
            			
            	}
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            return lStrMedResultFlag;    
        }
    
    
    public String getServerDate(String pStrDtFormat) throws Exception{
        String lStrCurentDate=null;
        try{
           /* lStrBuffer.append(" select to_char(sysdate,'");
                    lStrBuffer.append(pStrDtFormat);
                        lStrBuffer.append("') VALUE  from dual ");
        List<PreauthClinicalNotesVO> lDateList=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lStrBuffer.toString());
        if(!lDateList.isEmpty() && lDateList.size()>0){
            if(lDateList.get(0).getVALUE()!=null){
                lStrCurentDate=lDateList.get(0).getVALUE();
            }
        }*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH24:mm");
        Date date = new Date();
        //System.out.println(sdf.format(date));
        lStrCurentDate = sdf.format(date);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lStrCurentDate;
    }
    
    
	public String getFollowUpFlag(String pStrCaseId) throws Exception {
		StringBuffer lStrStrngBuffer = new StringBuffer();
		String[] args = new String[1];
		args[0] = pStrCaseId;
		String lFolwUpFlag = "N";
		/** The millis per day. */
		final long MILLIS_PER_DAY = 24 * 3600 * 1000;
		try {
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,
					pStrCaseId);
			if (ehfCase != null) {
				long msDiff;
				if (ehfCase.getCsDisDt() != null)
					msDiff = new Date().getTime()
							- ehfCase.getCsDisDt().getTime();
				else
					msDiff = new Date().getTime()
							- ehfCase.getCsDeathDt().getTime();
				long daysDiff = Math.round(msDiff / ((double) MILLIS_PER_DAY));
				if (daysDiff > 11) {
					lStrStrngBuffer
							.append(" select AFP2.id.surgeryId as ID from EhfCaseTherapy ACS, EhfmFollowupPackages AFP2, EhfCase EC,EhfmTherapyProcMst emf  ");
					if(ehfCase.getSchemeId().equalsIgnoreCase("1"))
						{
							lStrStrngBuffer.append(",EhfPatient ep,EhfEnrollment ee,EhfEnrollmentFamily eef");
						}
					lStrStrngBuffer
							.append(" WHERE ACS.icdProcCode = AFP2.id.surgeryId and EC.caseId=ACS.caseId and EC.csDeathDt is null ");
					lStrStrngBuffer.append(" and ACS.caseId = ? and ACS.activeyn='Y'");
					lStrStrngBuffer.append(" and AFP2.activeYN = 'Y' and AFP2.effEndDt is null  ");
					lStrStrngBuffer.append(" and emf.id.state=AFP2.id.schemeId and emf.id.icdProcCode = AFP2.icdCodeProcFP");
					lStrStrngBuffer.append(" and emf.activeYN='Y' and emf.id.process='FU'");
					
					if(ehfCase.getSchemeId().equalsIgnoreCase("1"))
					{
						lStrStrngBuffer.append(" and ep.patientId = EC.casePatientNo and ep.cardNo=eef.ehfCardNo");
						lStrStrngBuffer.append(" and ee.enrollPrntId=eef.enrollPrntId and ee.scheme=AFP2.id.schemeId "); 
					}
					else
					{
						lStrStrngBuffer.append(" and EC.schemeId=AFP2.id.schemeId");
					}
					
					List<PreauthClinicalNotesVO> lFollwUpDtlsList = genericDao
							.executeHQLQueryList(PreauthClinicalNotesVO.class,
									lStrStrngBuffer.toString(), args);
					if (!lFollwUpDtlsList.isEmpty()
							&& lFollwUpDtlsList.size() > 0) {
						if (lFollwUpDtlsList.get(0).getID() != null) {
							lFolwUpFlag = "Y";
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lFolwUpFlag;

	}
  
    public List<PreauthClinicalNotesVO> getDisSumDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrCaseId,String pStrNotesType) throws Exception{
    StringBuffer lQueryBuffer=new StringBuffer();
    String args[]=new String[1];
    args[0]=pStrCaseId;
    
        List<PreauthClinicalNotesVO> dissumList1= new ArrayList<PreauthClinicalNotesVO>();
        lQueryBuffer.append("  select cssr.DIS_MAIN_CODE  DISCODE "); 
          lQueryBuffer.append(" from ASRIT_PATIENT pt, ASRIT_CASE cs, ASRIT_CASE_PROCES csp, ASRIT_CASE_INVEST_DTLS csinvst, asrim_combo acb, ");
          lQueryBuffer.append("  ASRIT_CASE_SURGERY cssr  where cs.case_id=? and cs.CASE_PATIENT_NO = pt.PATIENT_ID and acb.cmb_dtl_id = pt.gender and ");
          lQueryBuffer.append("  cs.CASE_ID = cssr.CASE_ID(+)  and csp.CASE_ID(+)=cs.CASE_ID AND csinvst.case_id(+)=cs.case_id and  cssr.isapproved(+)='Y' ");
        try{
    dissumList1=genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,lQueryBuffer.toString(),args);
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
        return dissumList1;
    }
    
    public LabelBean getHospitalDetails(String pStrCaseId) throws Exception{
        StringBuffer lQueryBuffer=new StringBuffer();
        LabelBean hospDtls= new LabelBean();
        String args[]= new String[7];
        args[0]= "-NA-";
        args[1]= "-NA-";
        args[2]= "-NA-";
        args[3]= "-NA-";
        args[4]= "-NA-";
        args[5]= pStrCaseId;
        args[6]= "Y";
        List<LabelBean> hospDtlsList= new ArrayList<LabelBean>();
              lQueryBuffer.append(" SELECT distinct h.hosp_Name as ID, nvl(l1.loc_Name,?) as VALUE, nvl(l2.loc_Name,?) as CONST, nvl(l3.loc_Name,?) as LVL, h.hosp_Id as HOSPID, cast(h.hosp_type as varchar2(10)) as HOSPTYPE,"); 
              lQueryBuffer.append(" h.cug_No as WFTYPE, nvl(u.first_Name || ' ' || u.middle_Name || ' ' || u.last_Name,?) as EMPNAME, "); 
              lQueryBuffer.append(" nvl(u.mobile_No,?) as DSGNID "); 
              lQueryBuffer.append(" from Ehf_Case c, Ehfm_Hospitals h, Ehfm_Locations l1, Ehfm_Locations l2, Ehfm_Locations l3, ");
              lQueryBuffer.append(" Ehfm_Users u, Ehfm_Hosp_Mithra_Dtls d ");
              lQueryBuffer.append(" where c.case_Id = ? and h.hosp_Id = c.case_Hosp_Code and l1.loc_Id(+) = h.village ");
              lQueryBuffer.append(" and l2.loc_Id (+)= h.mandal_municipality and l3.loc_Id(+) = h.hosp_Dist ");
              lQueryBuffer.append(" and d.hosp_Id(+) = h.hosp_Id and d.end_Dt is null and d.active_YN = ? and u.user_Id(+) = d.mithra_Id ");
            try{
            	hospDtlsList=genericDao.executeSQLQueryList(LabelBean.class,lQueryBuffer.toString(), args);
            	if(hospDtlsList!=null && hospDtlsList.size()>0)
            		hospDtls= hospDtlsList.get(0);
            }
            catch(Exception e){
                e.printStackTrace();
            }
           
            return hospDtls;
        }
    public LabelBean getUserDetails(String pStrUserId) throws Exception{
    	StringBuffer lQueryBuffer=new StringBuffer();
        LabelBean usrDtls= new LabelBean();
        List<LabelBean> usrDtlsList= new ArrayList<LabelBean>();
              lQueryBuffer.append(" SELECT u.userId as ID, u.firstName||' "+"'||u.middleName||' "+"'||u.lastName as VALUE, u.mobileNo as CONST "); 
              lQueryBuffer.append(" from EhfmUsers u ");
              lQueryBuffer.append(" where u.userId='"+pStrUserId+"'");
              
            try{
            	usrDtlsList=genericDao.executeHQLQueryList(LabelBean.class,lQueryBuffer.toString());
            	if(usrDtlsList!=null && usrDtlsList.size()>0)
            		usrDtls= usrDtlsList.get(0);
            }
            catch(Exception e){
                e.printStackTrace();
            }
           
            return usrDtls;
    }
    public List<LabelBean> getOpDrugSubList(String drugTypeCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append(" select distinct edm.therMainGrpCode as  ID , edm.therMainGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append("  where edm.mainGrpCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.therMainGrpName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		glogger.error("Exception Occurred in getOpDrugSubList() in PreauthClinicalNotesDAOImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	public List<LabelBean> getOpPharSubList(String drugTypeCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.pharSubGrpCode as  ID , edm.pharSubGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append(" where edm.therMainGrpCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.pharSubGrpName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		glogger.error("Exception Occurred in getOpPharSubList() in PreauthClinicalNotesDAOImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	public List<LabelBean> getOpChemSubList(String pharSubCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.cheSubGrpCode as  ID , edm.cheSubGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append(" where edm.pharSubGrpCode='"+pharSubCode+"' and edm.activeYN='Y' order by edm.cheSubGrpName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		glogger.error("Exception Occurred in getOpChemSubList() in PreauthClinicalNotesDAOImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	public List<LabelBean> getChemSubList(String cSubGrpCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.chemicalCode as  ID , edm.chemicalName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append("  where edm.cheSubGrpCode='"+cSubGrpCode+"' and edm.activeYN='Y' order by edm.chemicalName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		glogger.error("Exception Occurred in getChemSubList() in PreauthClinicalNotesDAOImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	public List<LabelBean> getRouteList(String routeTypeCode,String pStrIpOpType) {
		List<LabelBean> routeList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.routeCode as  ID , edm.routeName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpRouteMst edm ");
			else
				query.append(" EhfmOpRouteMst edm ");
			query.append("  where edm.routeTypeCode='"+routeTypeCode+"' and edm.activeYN='Y' order by edm.routeName asc");
			routeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		glogger.error("Exception Occurred in getRouteList() in PreauthClinicalNotesDAOImpl class."+e.getMessage());
    	}
		
		return routeList;
	}
	public List<LabelBean> getStrengthList(String strengthTypeCode,String pStrIpOpType) {
		List<LabelBean> strengthList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.strengthCode as  ID , edm.strengthName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpStrengthMst edm ");
			else
				query.append(" EhfmOpStrengthMst edm ");
			query.append(" where edm.strengthUnitCode='"+strengthTypeCode+"' and edm.activeYN='Y' order by edm.strengthName asc");
			strengthList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		glogger.error("Exception Occurred in getStrengthList() in PreauthClinicalNotesDAOImpl class."+e.getMessage());
    	}
		
		return strengthList;
	}
    private Long getNextVal(String SeqName)
    {
    	String lStrSeqRetVal = "";   
    	Long cnt = (long) 0;
    	  try{
    		  StringBuffer query = new StringBuffer();
    		  query.append(" SELECT "+SeqName+".NEXTVAL NEXTVAL  FROM DUAL ");
     	        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
     	        if(seqList != null){	        	
     	          lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
     	         cnt = Long.parseLong(lStrSeqRetVal);
    	  }
    	  }
    catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	  return cnt;
    }
   
}


