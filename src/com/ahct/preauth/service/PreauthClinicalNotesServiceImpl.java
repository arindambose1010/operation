package com.ahct.preauth.service;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.dao.PreauthClinicalNotesDAO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
//import com.ahct.model.AsrimHospitals;
//import com.ahct.model.AsritBiomFinger;
//import com.ahct.model.AsritCaseProcess;
import com.ahct.model.EhfCase;
//import com.ahct.model.EhfCaseClinicalNotes;
import com.ahct.model.EhfCaseMedicalDtls;
import com.ahct.model.EhfCaseSurgicalDtls;
import com.ahct.model.EhfDischargeSummary;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfmComorbid;
import com.ahct.model.EhfTelephonicRegn;
//import com.ahct.model.EhfmMedcoDetails;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
//import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
//import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria.CriteriaOperator;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;


/**
 * 
 * @author Anitha
 * @version jdk1.5
 * @ClassDescription This class functions to get HomePage Login Information.
 */
public class PreauthClinicalNotesServiceImpl implements PreauthClinicalNotesService {
	GenericDAO genericDao;
	PreauthClinicalNotesDAO preauthClinicalNotesDao;

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	public void setPreauthClinicalNotesDao(PreauthClinicalNotesDAO preauthClinicalNotesDao) {
		this.preauthClinicalNotesDao = preauthClinicalNotesDao;
	}
	/* public Long getClinicalNotesCOUNT(String pStrCaseId,String pStrNotesType) throws Exception{
        Long lClinicalNotes=0L;
        try{
        lClinicalNotes=preauthClinicalNotesDao.getClinicalNotesCOUNT(pStrCaseId,pStrNotesType);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lClinicalNotes;

    }*/
	public List<PreauthClinicalNotesVO> getTherapyDetails(String pStrCaseId,String pStrNotesType) throws Exception{

		List<PreauthClinicalNotesVO> clinicalNotesList=new ArrayList<PreauthClinicalNotesVO>();  
		try{
			clinicalNotesList=preauthClinicalNotesDao.getTherapyDetails(pStrCaseId,pStrNotesType);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return clinicalNotesList;
	}
	public List<PreauthClinicalNotesVO> getClinicalNotesForCase(String pStrCaseId,String pStrNotesType) throws Exception{

		List<PreauthClinicalNotesVO> clinicalNotesList=null;  
		try{
			//clinicalNotesList=preauthClinicalNotesDao.getClinicalNotesForCase(pStrCaseId,pStrNotesType);
			StringBuffer query = new StringBuffer();

			query.append(" select c.bpSystolic as BLOODPRESSURE , c.clinicalId as CLINICALID,c.pulse as PULSE, c.wardInTime as WARDINTIME ");
			query.append(" , c.wardOutTime as WARDOUTTIME  , c.respiratory as  RESPIRATORY, c.heartRate as HEART_RATE , c.lungsCondition as LUNGS ");
			query.append(" ,c.temperature as TEMPERATURE, c.fluidInput as FLUIDINPT , c.fluidOutput as FLUIDOUTPUT , c.remarks as REMARKS , c.wardType as WARD_TYPE  ");
			query.append(" ,c.therapyDtls as THERAPY_DTLS , c.woundDtls as WOUND_DTLS , c.investigationDate as investGnDate ");
			query.append(", c.plasmaBbf as plasmaBbf ,c.plasmaBl as plasmaBl ,c.plasmaBd as plasmaBd,c.plasmaMn as plasmaMn ,c.docName as docName");
			query.append(" , c.insulinBbf as insulinBbf ,c.insulinSr as insulinSr,c.insulinBd as insulinBd,c.insulinMn as insulinMn ");
			query.append(" from EhfCaseClinicalNotes c where c.caseId ='"+pStrCaseId+"' and c.prePostOperative ='"+pStrNotesType+"' ");
			query.append(" order by c.crtDt desc   ");
			clinicalNotesList = genericDao.executeHQLQueryList(PreauthClinicalNotesVO.class, query.toString());
			/*List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
        	criteriaList.add(new GenericDAOQueryCriteria("caseId",pStrCaseId,CriteriaOperator.EQUALS));
        	criteriaList.add(new GenericDAOQueryCriteria("prePostOperative",pStrNotesType,CriteriaOperator.EQUALS));
        	criteriaList.add(new GenericDAOQueryCriteria("crtDt",null,CriteriaOperator.DESC));
        	List<EhfCaseClinicalNotes> list=genericDao.findAllByCriteria(EhfCaseClinicalNotes.class, criteriaList);
        	if(list!=null && !list.isEmpty())
        	{
        		clinicalNotesList=new ArrayList<PreauthClinicalNotesVO>(list.size());
        		for(EhfCaseClinicalNotes asritCaseClinicalNotes:list)
        		{        			
        			PreauthClinicalNotesVO preauthClinicalNotesVO=new PreauthClinicalNotesVO();
        			preauthClinicalNotesVO.setBLOODPRESSURE(asritCaseClinicalNotes.getBpSystolic());
        			preauthClinicalNotesVO.setCLINICALID(asritCaseClinicalNotes.getClinicalId());
        			preauthClinicalNotesVO.setPULSE(asritCaseClinicalNotes.getPulse());
        			preauthClinicalNotesVO.setWARDINTIME(asritCaseClinicalNotes.getWardInTime());
        			preauthClinicalNotesVO.setWARDOUTTIME(asritCaseClinicalNotes.getWardOutTime());
        			preauthClinicalNotesVO.setRESPIRATORY(asritCaseClinicalNotes.getRespiratory());
        			preauthClinicalNotesVO.setHEART_RATE(asritCaseClinicalNotes.getHeartRate());
        			preauthClinicalNotesVO.setLUNGS(asritCaseClinicalNotes.getLungsCondition());
        			preauthClinicalNotesVO.setTEMPERATURE(asritCaseClinicalNotes.getTemperature());        			
        			preauthClinicalNotesVO.setFLUIDINPT(asritCaseClinicalNotes.getFluidInput());
        			preauthClinicalNotesVO.setFLUIDOUTPUT(asritCaseClinicalNotes.getFluidOutput());
        			preauthClinicalNotesVO.setREMARKS(asritCaseClinicalNotes.getRemarks());

        			preauthClinicalNotesVO.setWARD_TYPE(asritCaseClinicalNotes.getWardType());
        			preauthClinicalNotesVO.setREMARKS(asritCaseClinicalNotes.getRemarks());
        			if(asritCaseClinicalNotes.getInvestigationDate()!=null)
        				preauthClinicalNotesVO.setINVESTIGATIONDATE(new SimpleDateFormat("dd/MM/yyyy").format(asritCaseClinicalNotes.getInvestigationDate()));
        			preauthClinicalNotesVO.setTHERAPY_DTLS(asritCaseClinicalNotes.getTherapyDtls());        			
        			if(asritCaseClinicalNotes.getWoundDtls()!=null)
        				preauthClinicalNotesVO.setWOUND_DTLS(asritCaseClinicalNotes.getWoundDtls());
        			else
        				preauthClinicalNotesVO.setWOUND_DTLS("-NA-");
        			preauthClinicalNotesVO.setDocName(asritCaseClinicalNotes.getDocName());
        			preauthClinicalNotesVO.
        			clinicalNotesList.add(preauthClinicalNotesVO);
        		}
        	}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return clinicalNotesList;
	}
	public String saveClinicalNotes(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId){
		String lstrMsg=preauthClinicalNotesDao.saveClinicalNotes(preauthClinicalNotesVO,pStrUserId);
		return lstrMsg;
	}
	public EhfCase getCaseDetails(String pStrCaseId) throws Exception{
		EhfCase ehfCase=new EhfCase();
		try{
			System.out.println("case id in service impl" + pStrCaseId);
			ehfCase=genericDao.findById(EhfCase.class,String.class,pStrCaseId);
			System.out.println("case id ehfCase" + ehfCase);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ehfCase;
	}
	
	public EhfPatient getPatientDetails(String pStrPatientId) throws Exception{
		EhfPatient ehfPatient=new EhfPatient();
	try{
		System.out.println("case id in service impl" + pStrPatientId);
		ehfPatient=genericDao.findById(EhfPatient.class,String.class,pStrPatientId);
		System.out.println("case id ehfCase" + ehfPatient);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return ehfPatient;
}
	public EhfTelephonicRegn getTeleCaseDetails(String pStrPatientId) throws Exception{
		List<EhfTelephonicRegn> ehfTelephonicRegnLst =new ArrayList<EhfTelephonicRegn>();
		EhfTelephonicRegn ehfTelephonicRegn=new EhfTelephonicRegn();
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		try{
			System.out.println("patient id in service impl" + pStrPatientId);
			criteriaList.add(new GenericDAOQueryCriteria("patientId",pStrPatientId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			ehfTelephonicRegnLst=genericDao.findAllByCriteria(EhfTelephonicRegn.class, criteriaList);
			System.out.println("case id ehfCase" + ehfTelephonicRegn);
			ehfTelephonicRegn=ehfTelephonicRegnLst.get(0);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ehfTelephonicRegn;
	}
	/*public AsritCaseProcess getCaseProcessDtls(String pStrCaseId) throws Exception{
        AsritCaseProcess asritCaseProcess=new AsritCaseProcess();
        try{
        asritCaseProcess=genericDao.findById(AsritCaseProcess.class,String.class,pStrCaseId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return asritCaseProcess;
    }*/
	public boolean saveSurgeryDetails(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserRole,String pStrUserId) throws Exception{
		boolean statusFlag=true;
		try{
			statusFlag= preauthClinicalNotesDao.saveSurgeryDetails(preauthClinicalNotesVO,pStrUserRole,pStrUserId);
		}
		catch(Exception e){
			statusFlag=false;
			e.printStackTrace();
		}
		return statusFlag;
	}
	public String getTelephneIntimationDate(String pStrCaseId) throws Exception{
		String telDt=null;
		try{
			telDt=preauthClinicalNotesDao.getTelephneIntimationDate(pStrCaseId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return telDt;
	}
	/*public AsrimHospitals getUserHospType(String pStrUserId) throws Exception
    {
        String lStrUserHospID=null;     
        AsrimHospitals asrimHospitals=new AsrimHospitals();
        try{
            List<GenericDAOQueryCriteria> criteriaList = 
                new ArrayList<GenericDAOQueryCriteria>();
            criteriaList.add(new GenericDAOQueryCriteria("id.userId", 
                                                         pStrUserId, 
                                                         GenericDAOQueryCriteria.CriteriaOperator.EQUALS));                                                 
            criteriaList.add(new GenericDAOQueryCriteria("effEndDt", 
                                                         null, 
                                                         GenericDAOQueryCriteria.CriteriaOperator.IS_NULL));
            List<EhfmMedcoDetails> lUserDtlsList = 
                genericDao.findAllByCriteria(EhfmMedcoDetails.class, 
                                             criteriaList);
            if(!lUserDtlsList.isEmpty()) {
                lStrUserHospID= lUserDtlsList.get(0).getId().getHospId();
            }                                           
           if(lStrUserHospID!=null){
               asrimHospitals=genericDao.findById(AsrimHospitals.class,String.class,lStrUserHospID);
           } 
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return asrimHospitals;
    }*/
	public boolean saveDischargeSummaryDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId,String pStrUserRole,String pStrDisDeathFlag) throws Exception{
		boolean disUpdFlag=true;
		try{
			disUpdFlag=preauthClinicalNotesDao.saveDischargeSummaryDtls(preauthClinicalNotesVO,pStrUserId,pStrUserRole,pStrDisDeathFlag);
		}
		catch(Exception e){
			disUpdFlag=false;
			e.printStackTrace();
		}
		return disUpdFlag;
	}
	public EhfDischargeSummary getDischargeSummary(String pStrCaseId){
		EhfDischargeSummary asritDischargeSummary=new EhfDischargeSummary();
		try{
        	if(pStrCaseId!=null && !"".equalsIgnoreCase(pStrCaseId))
			asritDischargeSummary=genericDao.findById(EhfDischargeSummary.class,String.class,pStrCaseId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return asritDischargeSummary;

	}
	public Long getPostOPCheckForSurgery(String pStrCaseId) throws Exception{
		Long lPostOpCheck=0L;
		try{
			lPostOpCheck=preauthClinicalNotesDao.getPostOPCheckForSurgery(pStrCaseId);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return lPostOpCheck;
	}
	public boolean saveAttachments(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId) throws Exception{
		boolean uploadFlag=true;
		try{
			uploadFlag=preauthClinicalNotesDao.saveAttachments(preauthClinicalNotesVO,pStrUserId);
		}
		catch(Exception e){
			uploadFlag=false;
			e.printStackTrace();
		}
		return uploadFlag;
	}
	public PreauthClinicalNotesVO getAttachmentsCntByType(String pStrCaseId) throws Exception{
		Long lSurgeryPhoCnt=0L;
		Long lOpNotesAttachCnt=0L;
		Long lSatisFctryLttrCnt=0L;
		Long lTransprtLetterCnt=0L;
		Long lDisPhotCnt=0L;
		Long lDisSummaryDocCnt=0L;
		Long lDthCertiCnt=0L;
		Long lDthSumryAttch=0L;
		PreauthClinicalNotesVO preauthClinicalNotesVO=new PreauthClinicalNotesVO();
		try{
			lSurgeryPhoCnt=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD130");
			lOpNotesAttachCnt=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD345");
			lSatisFctryLttrCnt=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD17002");
			lTransprtLetterCnt=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD17003");
			lDisPhotCnt=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD144");
			lDisSummaryDocCnt=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD17001");
			lDthCertiCnt=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD17004");
			lDthSumryAttch=preauthClinicalNotesDao.getAttachmentsCntByType(pStrCaseId,"CD757");
			preauthClinicalNotesVO.setAFSURG_PHT_CNT(lSurgeryPhoCnt);
			preauthClinicalNotesVO.setOP_NOTES_CNT(lOpNotesAttachCnt);
			preauthClinicalNotesVO.setSATISF_LETTER_CNT(lSatisFctryLttrCnt);
			preauthClinicalNotesVO.setTRANSPRT_LETTER_CNT(lTransprtLetterCnt);
			preauthClinicalNotesVO.setDIS_PHT_CNT(lDisPhotCnt);
			preauthClinicalNotesVO.setDIS_SUMRY_DOC_CNT(lDisSummaryDocCnt);
			preauthClinicalNotesVO.setDEATH_CERTI_CNT(lDthCertiCnt);
			preauthClinicalNotesVO.setDEATH_SUMMRY_CNT(lDthSumryAttch);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return preauthClinicalNotesVO;
	}
	/*  public List<AsritCaseClinicalDose> getAdditionlCliniclDtls(String pStrClinicalId,String pStrDoseFor){
        List<AsritCaseClinicalDose> additionlCliniclDtls=preauthClinicalNotesDao.getAdditionlCliniclDtls(pStrClinicalId,pStrDoseFor);
        return additionlCliniclDtls;
    }*/

	public EhfCaseMedicalDtls getCaseMedmgntDtls(String pStrCaseId) throws Exception{
		EhfCaseMedicalDtls asritCaseMedmgntDtls=null;

		try{
			asritCaseMedmgntDtls=genericDao.findById(EhfCaseMedicalDtls.class,String.class,pStrCaseId) ;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return asritCaseMedmgntDtls;
	}

	public EhfCaseSurgicalDtls getCasePacOperationDtls(String pStrCaseId) throws Exception{
		EhfCaseSurgicalDtls asritCasePacOperationDtls=null;

		try{
			asritCasePacOperationDtls=genericDao.findById(EhfCaseSurgicalDtls.class,String.class,pStrCaseId) ;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return asritCasePacOperationDtls;
	}
	public Long checkForDeathDate(String pStrPatientId)throws Exception{
		Long lDeathDtCnt=0L;
		try{
			lDeathDtCnt=preauthClinicalNotesDao.checkForDeathDate(pStrPatientId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return lDeathDtCnt;
	}
	public Long getPhaseStatus(String pStrCaseId)throws Exception{
		Long lPhaseClosed=0L;
		try{
			lPhaseClosed=preauthClinicalNotesDao.getPhaseStatus(pStrCaseId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return lPhaseClosed;

	}
	/*public AsritBiomFinger getPatBioFinger(String pStrPatientId) throws Exception{
    AsritBiomFinger asritBiomFinger=new AsritBiomFinger();

        try{
            asritBiomFinger=genericDao.findById(AsritBiomFinger.class,String.class,pStrPatientId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return asritBiomFinger;
    }*/
	public String getServerDate(String pStrDtFormat) throws Exception{
		String lStrCurntDate=null;
		try{
			lStrCurntDate=preauthClinicalNotesDao.getServerDate(pStrDtFormat);
		}catch(Exception e){
			e.printStackTrace();
		}
		return lStrCurntDate;
	}
	public String getFollowUpFlag(String pStrCaseId) throws Exception{
		String lStrFolwUpFlag=null;
		try{
			lStrFolwUpFlag=preauthClinicalNotesDao.getFollowUpFlag(pStrCaseId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return lStrFolwUpFlag;  
	}
	/*
	  * Added by Srikalyan to Check the
	  * Cochlear FollowUp Conditions
	  */
	 public String getCochlearFollowUpFlag(String caseId,int cochlearFollowUpCount)
	 	{
		 String cochlearFollowUpFlag=null;
				try{
					StringBuffer query=new StringBuffer();
					query.append( " select c.id.cochlearSurgeryId as procCode , c.id.icdCodeProcFp as VALUE " );
					query.append( " from EhfCase a , EhfCaseTherapy b ,  " );
					query.append( " EhfmCochFollowupPackage c , EhfmTherapyProcMst d " );
					query.append( " where a.caseId = b.caseId and b.icdProcCode = c.id.cochlearSurgeryId " );
					query.append( " and c.id.icdCodeProcFp = d.id.icdProcCode and a.schemeId = c.id.schemeId " );
					query.append( " and d.id.state = a.schemeId and a.caseId = '"+caseId+"'" );
					query.append( " and b.activeyn = 'Y' and c.effEndDt is null and c.activeYn='Y' " );
					//query.append( " and c.cochlearCount=1 " );
					query.append( " and d.activeYN='Y' and d.id.process='FU' " );
					
					List<PreauthClinicalNotesVO> mainVO=genericDao.executeHQLQueryList(PreauthClinicalNotesVO.class,query.toString());
					if(mainVO!=null)
						{
							if(mainVO.size()>0)
								{
									cochlearFollowUpFlag="Y";
								}
							else
								return cochlearFollowUpFlag;
						}
					else
						return cochlearFollowUpFlag;
				}
			catch(Exception e)
				{
					e.printStackTrace();
				}
			return cochlearFollowUpFlag; 	
	 	}
	 
	public String getMedMngmtFlag(String pStrCaseId)throws Exception{
		String lStrMedMngntFlag=null;
		try{
			lStrMedMngntFlag=preauthClinicalNotesDao.getMedMngmtFlag(pStrCaseId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return lStrMedMngntFlag;
	}
	public String getMedicalFlag(String pStrCaseId)
	{
		String lStrMedMngntFlag=null;
		try{
			lStrMedMngntFlag=preauthClinicalNotesDao.getMedicalFlag(pStrCaseId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return lStrMedMngntFlag;	
	}
	public List<PreauthClinicalNotesVO> getDisSumDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrCaseId,String pStrNotesType) throws Exception{

		List<PreauthClinicalNotesVO> disSumList=new ArrayList<PreauthClinicalNotesVO>();  
		try{
			disSumList=preauthClinicalNotesDao.getDisSumDtls( preauthClinicalNotesVO,pStrCaseId,pStrNotesType);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return disSumList;
	}
	public LabelBean getHospitalDetails(String pStrCaseId) throws Exception{
		LabelBean hospDtls= new LabelBean();
		try{
			hospDtls=preauthClinicalNotesDao.getHospitalDetails(pStrCaseId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return hospDtls;
	}
	public LabelBean getUserDetails(String pStrUserId) throws Exception{
		LabelBean hospDtls= new LabelBean();
		try{
			hospDtls=preauthClinicalNotesDao.getUserDetails(pStrUserId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return hospDtls;
	}
	public List<LabelBean> getComorbidDtls(String comorbidVal) throws Exception
	{
		List<LabelBean> comorbidDtls= new ArrayList<LabelBean>();
		try{
			if(comorbidVal!=null && !"".equalsIgnoreCase(comorbidVal))
			{
				StringTokenizer str = new StringTokenizer(comorbidVal,"~");
				while(str.hasMoreTokens())
				{
					EhfmComorbid ehfmComorbid = genericDao.findById(EhfmComorbid.class, String.class, str.nextToken());	
					if(ehfmComorbid != null)
					{
						comorbidDtls.add(new LabelBean(ehfmComorbid.getComorbidName(),ehfmComorbid.getComorbidVal(), ehfmComorbid.getComorbidId()));	
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return comorbidDtls;
	}
	public List<DrugsVO> getIPDrugs(String pStrCaseId,String pStrPrePostFlag)
	{
		StringBuffer query = new StringBuffer();
		List<DrugsVO> drugs=null;
		try
		{
			query.append(" select EDM.mainGrpCode as drugTypeCode,EDM.therMainGrpCode as drugSubTypeCode,EDM.pharSubGrpCode as pSubGrpCode,EDM.cheSubGrpCode as cSubGrpCode,EDM.chemicalCode as drugCode, EPD.clinicalId as clinicalId , ");
			query.append(" EDM.mainGrpName as drugTypeName,EDM.therMainGrpName as drugSubTypeName,EDM.pharSubGrpName as pSubGrpName,EDM.cheSubGrpName as cSubGrpName,EDM.chemicalName as drugName, ");
			query.append(" ER.routeTypeCode as routeType,ER.routeTypeName as routeTypeName,ER.routeCode as route,ER.routeName as routeName,ES.strengthUnitCode as strengthType,ES.strengthUnitName as strengthTypeName ,");
			query.append(" ES.strengthCode as strength,ES.strengthName as strengthName,EPD.dosage as dosage,EPD.medicationPeriod as medicationPeriod from EhfCaseIpDrugs EPD,EhfmIpDrugMst EDM,EhfmIpStrengthMst ES, ");
			query.append(" EhfmIpRouteMst ER ,EhfCaseClinicalNotes c where EPD.drugCode=EDM.chemicalCode and ER.routeCode=EPD.route and ES.strengthCode=EPD.strength ");
			query.append(" and c.clinicalId = EPD.clinicalId and c.caseId ='"+pStrCaseId+"' and c.prePostOperative='"+pStrPrePostFlag+"' ");
			query.append("  and EPD.caseId='"+pStrCaseId+"' order by EPD.crtDt desc ");
			drugs=genericDao.executeHQLQueryList(DrugsVO.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugs;
	}
	public List<String> getOpDrugSubList(String drugTypeCode ,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=preauthClinicalNotesDao.getOpDrugSubList(drugTypeCode,pStrIpOpType);
			for (LabelBean labelBean: drugSubList) 
			{
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					drugSubList1.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugSubList1;
	}
	public List<String> getOpPharSubList(String drugTypeCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=preauthClinicalNotesDao.getOpPharSubList(drugTypeCode,pStrIpOpType);
			for (LabelBean labelBean: drugSubList) 
			{
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					drugSubList1.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugSubList1;
	}
	public List<String> getOpChemSubList(String pharSubCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=preauthClinicalNotesDao.getOpChemSubList(pharSubCode,pStrIpOpType);
			for (LabelBean labelBean: drugSubList) 
			{
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					drugSubList1.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugSubList1;
	}
	public List<String> getChemSubList(String cSubGrpCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=preauthClinicalNotesDao.getChemSubList(cSubGrpCode,pStrIpOpType);
			for (LabelBean labelBean: drugSubList) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					drugSubList1.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugSubList1;
	}
	public List<String> getRouteList(String routeTypeCode,String pStrIpOpType) {
		List<LabelBean> routeList=null;
		List<String> routeList1 = new ArrayList<String>();
		try
		{
			routeList=preauthClinicalNotesDao.getRouteList(routeTypeCode,pStrIpOpType);
			for (LabelBean labelBean: routeList) 
			{
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					routeList1.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return routeList1;
	}
	public List<String> getStrengthList(String strengthTypeCode,String pStrIpOpType) {
		List<LabelBean> strengthList=null;
		List<String> strengthList1 = new ArrayList<String>();
		try
		{
			strengthList=preauthClinicalNotesDao.getStrengthList(strengthTypeCode,pStrIpOpType);
			for (LabelBean labelBean: strengthList) 
			{
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					strengthList1.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strengthList1;
	}
	/*
	 * Used to get all the therapies of the case
	 */
	public List<CasesSearchVO> getDopDtls(String caseId)
	{
		
		StringBuffer query= new StringBuffer();
		query.append(" select ect.caseId as caseId,ect.activeyn as activeyn,ect.asriCatCode as speciality, ");
		query.append(" ect.icdProcCode as procCode,etpm.id.process as process ");
		query.append(" ,ec.caseStatus as caseStatus,cast(ec.csSurgDt as string) as csSurgDt ");
		query.append(" from EhfCaseTherapy ect,EhfmTherapyProcMst etpm,EhfCase ec where ");
		query.append(" etpm.id.icdProcCode=ect.icdProcCode and ect.caseId='"+caseId+"'");
		query.append(" and ect.caseId=ec.caseId and ec.caseId='"+caseId+"'");
		query.append(" and etpm.id.state=ec.schemeId");
		List<CasesSearchVO> list=genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
		return list;
	}

	/***
	  * @author Kalyan
	  * @param Case ID
	  * @return Dental Flag
	  * @method Checks Whether the case is Dental or not  
	  */
	@Override
	 public String checkDentalCase(String caseId)
	 	{
		 	String returnMsg="N";
		 	try 	
		 		{
		 			StringBuffer query=new StringBuffer();
		 			query.append( " select a.asriCatCode as CATID " );
		 			query.append( " from EhfCaseTherapy a " );
		 			query.append( " where a.caseId = '"+caseId+"' " );
		 			query.append( " and a.activeyn = 'Y' and a.asriCatCode ='S18'  " );
		 			
		 			List<CasesSearchVO> list=genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
		 			if(list!=null && list.size()>0)
		 				returnMsg="Y";
		 			
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
		 			return returnMsg;
		 		}
		 	return returnMsg;
	 	}
	
	/***
	  * @author Kalyan
	  * @param Case ID
	  * @return Slab Amount
	  * @method Obtain Slab Amount for the Case  
	  */
	 @Override
	 public String getSlabAmount(String caseId)
	 	{
		 	String slabAmt="0";
			StringBuffer query= new StringBuffer();
			try	
				{
					query.append ( " select c.amount as wapNo  " );
					query.append( "  from EhfCase a , EhfPatient b , EhfmSlabPackage c" );
					query.append( "  where a.casePatientNo = b.patientId  " );
					query.append( "  and a.caseId = '"+caseId+"' ");
					query.append( "  and b.slab = c.id.slabType and c.id.nabhFlag = 'N' " );//For AP Default Nabh Condition is N
					List<CasesSearchVO> amtLst =genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
					
					if(amtLst!=null && amtLst.size()>0 && amtLst.get(0)!=null && amtLst.get(0).getWapNo()!=null)
						slabAmt=amtLst.get(0).getWapNo();
					else
						slabAmt="0";
				}	
			catch(Exception e)
		 		{
		 			e.printStackTrace();
		 			return slabAmt;
		 		}
			return slabAmt;
	 	}
}
