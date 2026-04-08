package com.ahct.caseSearch.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.claims.util.ClaimsConstants;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfCsvCaseSearch;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class CasesSearchDaoImpl implements CasesSearchDao{
	GenericDAO genericDao;
	static final Logger gLogger = Logger.getLogger(CasesSearchDaoImpl.class);
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
		
	public CasesSearchVO getListCases(CasesSearchVO casesSearchVO)
	{
		String flg="N";
		String organFlg="N";
			String acoCond="N";
			List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
			List<CasesSearchVO> lstFinalCases = new ArrayList<CasesSearchVO>();	
			CasesSearchVO retCasesSearchVO = new CasesSearchVO();
			StringBuffer query = new StringBuffer();
			StringBuffer query1 = new StringBuffer();
			List<String>  caseStatus = new ArrayList<String>();
			String fromDate;
			String sqlFromDate;
			String toDate;
			String sqlToDate;
			String ceoFlag=casesSearchVO.getCeoFlag();
			String dentalFlag=casesSearchVO.getDentalFlag();
			
			String database=config.getString("Database");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
			SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
			 SessionFactory factory= hibernateTemplate.getSessionFactory();
			  Session session=factory.openSession();
			  int startIndex=Integer.parseInt(casesSearchVO.getStartIndex());
			  int maxResult = (Integer.parseInt(casesSearchVO.getRowsPerPage()));
			   boolean ppdFlag = false; boolean ptdFlag=false;
			  boolean ppdComiteFlag = false; boolean ctdFlag=false;
			  boolean cpdFlag= false;
			  boolean cexFlag=false;
			  String moduleType = casesSearchVO.getModule();
			  String pendingFlag=casesSearchVO.getPendingFlag();
		  String hubFlg = "N";
		  String spokePendFlg = "N";
			  /**
			   * To get the groups to which the user is mapped
			   */
			  if(moduleType!=null && !moduleType.equalsIgnoreCase(""))
			  {
			  if(moduleType.contains("preauth"))
				{
					moduleType="preauth";
				}
				if(moduleType.contains("claim"))
				{
					moduleType="claim";
				}
			if(moduleType.contains("cochlearAco"))
			{
				moduleType="cochlearAco";
			}
				
			  }
			  if(moduleType==null || moduleType.equalsIgnoreCase(""))
				  moduleType="common";
			  
			  String mappedGroups="~";
			  try{
				 if(casesSearchVO.getShowPage() != null && Integer.parseInt(casesSearchVO.getShowPage()) > 1 )
				 {
					 startIndex =  Integer.parseInt(casesSearchVO.getRowsPerPage()) * (Integer.parseInt(casesSearchVO.getShowPage())-1);
				 }			 
				 
				 /**
				  * get cases status for approval
				  */
				 for(LabelBean labelBean:casesSearchVO.getGrpList())
	 		    {
					 
					 
					 
					 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP17")||labelBean.getID() != null &&   labelBean.getID().equals( "GP2")
							 ||labelBean.getID() != null &&   labelBean.getID().equals( "GP1")||labelBean.getID() != null &&   labelBean.getID().equals( "GP97"))
						 acoCond="Y";
					 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP71")||labelBean.getID() != null &&   labelBean.getID().equals( "GP72")||
							 labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))//For cochlear groups
						 flg="Y";
					 if( labelBean.getID() !=null && labelBean.getID().equals("GP1000") || labelBean.getID() !=null && labelBean.getID().equals("GP1001")||
							 labelBean.getID() != null &&   labelBean.getID().equals( "GP1002"))//For organ transplant groups
						 organFlg="Y";
					 String caseSts = "";
					 if(casesSearchVO.getCaseForDissFlg()!=null && casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")){
						 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP8"))
							 caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
						 else if (labelBean.getID() != null &&   labelBean.getID().equals( "GP9"))
							 caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
						 else if (labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))
							 {
							 	caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
							 	//flg="Y";
							 	
							 }
					 }
				else if(casesSearchVO.getDiaFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaFlg()))
				 {
					 caseSts = config.getString(moduleType+"_caseStatusHub_"+labelBean.getID());
					 hubFlg="Y";
				 }
				else if(casesSearchVO.getDiaPendFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaPendFlg()))
				{
					 caseSts = config.getString(moduleType+"_caseStatusSpoke_"+labelBean.getID());
					 spokePendFlg="Y";
				}
			
				 else if((hubFlg != null && hubFlg!="Y") || (spokePendFlg !=null && spokePendFlg!="Y")){
	 		     caseSts =  config.getString(moduleType+"_caseStatus_"+labelBean.getID());
	 		     	if(labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))
	 		     		{
	 		     			//if(caseSts.contains("CD149"))
	 		     				//flg="Y";
	 		     		}
					 }
	 		     if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ppd_role")) && !ppdFlag )
	 		     {
	 		    	ppdFlag = true; 
	 		     }
	 		    if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("claim_cpd_role")) && !cpdFlag )
			     {
	 		    	cpdFlag = true; 
			     }
	 		    if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ppdGrp_role")) && !ppdComiteFlag )
			     {
	 		    	ppdComiteFlag = true; 
			     } 
	             if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ptd_role")) && !ppdFlag && !cpdFlag && !ptdFlag)
			     {
	 			  ptdFlag = true; 
			     }
	 		  if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("EHF.Claims.CTD")) && !ppdFlag && !cpdFlag && !ctdFlag)
			     {
				  ctdFlag = true; 
			     }	
	 		 if (caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("EHF.Claims.CEX"))
						&& !ppdFlag && !cpdFlag && !ptdFlag) {
					cexFlag = true;
				}
	 		     if(caseSts != null && !caseSts.equalsIgnoreCase(""))
	 		     {
	 		     StringTokenizer str = new StringTokenizer(caseSts,"~");
	 			  while(str.hasMoreTokens())
	 			  {
	 				 caseStatus.add(str.nextElement().toString());  
	 			  }
	 			   
	 		     }
	 		     
	 		    mappedGroups=mappedGroups+labelBean.getID()+"~";
	 		    }
				 /**
				 * set view flag as N for Cases blocked by this user
				 */
			 	List<GenericDAOQueryCriteria>  criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			 	criteriaList.add(new GenericDAOQueryCriteria("caseBlockedUsr",casesSearchVO.getUserId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 	criteriaList.add(new GenericDAOQueryCriteria("viewFlag","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 	List<EhfCase> blockedCaseList=genericDao.findAllByCriteria(EhfCase.class,criteriaList);
			 	for(EhfCase ehfCase:blockedCaseList)
				{
					ehfCase.setViewFlag("N");
					ehfCase.setCaseBlockedUsr(casesSearchVO.getUserId());
					ehfCase.setCase_blocked_dt(new java.sql.Timestamp(new Date().getTime()));
					genericDao.save(ehfCase);
				}
				 /**
				  * end of getting status for cases for approval
				  */
			  query.append("  select distinct ac.caseId as caseId,ac.caseNo as caseNo ,ac.flagged as flagged,ap.name as patientName ,  ap.cardNo as wapNo , acb.cmbDtlName as claimStatus ,ah.hospName as hospName,ah.hospId as hospId,ac.schemeId as schemeId,ac.casePatientNo as patientId  ");
				  query.append(" , ac.caseStatus as caseStatusId , ac.lstUpdDt  as lstUpdDt,ac.errClaimStatus as errStatusId, ap.patientId as patientId, ac.pendingFlag as pendingFlag,ac.preauthPckgAmt as preauthPckgAmt,ac.enhFlag as enhFlag, nvl(ac.enhReqAmt,0) as enhReqAmt,nvl(ac.preauthTotalPackageAmt,0) as totPckgAmt,ac.clmSubDt as SUBMITTEDDATE,ac.caseRegnDate as CASEREGNDATE  ");
				  if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase(""))
				  	{
					  query.append(" ,	to_char(eccm.tdsPctAmt) as tdsPctAmt, to_char(eccm.trustPctAmt) as trustPctAmt ,to_char(eccm.hospPctAmt) as hospPctAmt ,to_char(eccm.tdsHospPctAmt) as tdsHospPctAmt ");
				  	} 
				  if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp"))
				  query.append("  , ap.patientIpop as patIpOp, ap.intimationId as teleStatus");
				  //if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
				  query.append(" ,ac.viewFlag as viewFlag, ac.caseBlockedUsr as caseBlockedUsr ");
				  //}
				  query.append("  ,ac.caseRegnDate as InpatientCaseSubDt , nvl(ac.csClAmount,0) as claimAmt ,ac.claimNo as claimNo, ac.grievanceFlag as grievanceFlag "); 
				  if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
					  query.append("  ,ac.csPreauthDt as csPreauthDt,ac.preauthAprvDate as preauthAprvDate,ac.csDisDt as csDisDt,ac.csDeathDt as csDeathDt,ac.clmSubDt as clmSubDt,ac.csTransId as csTransId, ac.csTransDt as csTransDt,ac.csRemarks as csRemarks,ac.csSbhDt as csSbhDt");
					  
					  if(casesSearchVO!=null && casesSearchVO.getTotRowCount()!=null && !"".equalsIgnoreCase(casesSearchVO.getTotRowCount()))
					  {
						  maxResult =   Integer.parseInt(casesSearchVO.getTotRowCount());
					  }
				  }
				 /* if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
				  {
					  query.append(" ,em.procName as procName ");
				  }*/
				  
				  query1.append("   from EhfmCmbDtls acb,  EhfmHospitals ah  ");
				 
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))
					  query1.append(",    EhfmMedcoDetails anu ");
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
					  query1.append(",    EhfmHospMithraDtls anu "); 
				  if(casesSearchVO.getCaseSearchType()!=null && !casesSearchVO.getCaseSearchType().equalsIgnoreCase("") && casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
					  query1.append(" ,  EhfPatient ap	,EhfChronicCase ac ");
					  
					  if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
						  query1.append(" left join ac.EhfChronicCaseClaim eccm 	");
					  }
				  }
				  
				  
				  else
				  {
	            	  query1.append(" ,   EhfPatient ap , 	EhfCase ac "); //EhfCasePatient ap ,
						if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
										  query1.append("	left join ac.ehfCaseClaim eccm 	");
										  }
				  }
				if((ppdFlag ||cpdFlag) && !ppdComiteFlag )
				{
						  query1.append(" ,EhfmUsrSpecialityMpg spm ,  EhfCaseTherapy acs1,EhfmUsrProcMpg pmg ");	
				}else if(ptdFlag || ctdFlag){
					query1.append(" ,EhfmUsrSpecialityMpg spm ,  EhfCaseTherapy acs1 ");
				}
				if(cexFlag && casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				{
					query1.append(" ,EhfCaseTherapy ect ");
				}
				
				if(casesSearchVO.getSearchFlag() != null && casesSearchVO.getSearchFlag().equalsIgnoreCase("Y"))
				 {
				if((casesSearchVO.getMainCatName() != null && !casesSearchVO.getMainCatName().equals("") && !casesSearchVO.getMainCatName().equals("-1")) || (casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1")))
			    {
				  query1.append("  , EhfCaseTherapy acs ,  EhfmTherapyCatMst emc ");  
				  if(casesSearchVO.getProcName() != null && !casesSearchVO.getProcName().equals("") && !casesSearchVO.getProcName().equals("-1"))
				    {
					  query1.append("   ,  EhfmTherapyProcMst etp ");  
				    }
			    }
				 }
				/*if(cpdFlag){
					query1.append(" ,EhfmUsrSpecialityMpg spm ,  EhfCaseTherapy acs1  ");	
				}
				 if(ppdComiteFlag || ppdFlag)
			      {
					 query1.append(" , EhfmUsrHospitalMpg hp "); 
			      }*/
				  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
				  {
					  query1.append("  , EhfCaseTherapy acs ,  EhfmDiagnosisCatMst adm ");  
				  }
				  
				  /* added by satish kola for erroneous claim cases*/
			      if((casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) || (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y"))){
					 
					   if(casesSearchVO.getRoleId()!=null && ((mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))||(mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~")) || (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~")) )){
							query1.append(" ,EhfErroneousClaim eec " );
					   }
				  }
			      
			     
				 /* if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
				  {
					  query1.append("   ,EhfCaseTherapy ecc,EhfmTherapyProcMst em ");
				  }*/
				  
				  
				  /*if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
				  {
					  query1.append("  , EhfmUsersUnitDtls eud  ");  
				  }
				  */
				  if(casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" ,EhfCaseTherapy ct "); 
					  }
				  }
				  if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" ,EhfCaseTherapy ct "); 
					  }
				  }
				  /**
				   * Added by ramalakshmi for hubspoke CR
				   */
				  if((casesSearchVO.getDiaFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaFlg())) || (casesSearchVO.getDiaPendFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaPendFlg())))
				  {
					  query1.append(",EhfmHospHubSpokeMpg hsm ");
				  }
				  /**
				   * End of hubspoke
				   */
				  query1.append("  where ap.patientId = ac.casePatientNo   and acb.id.cmbDtlId = ac.caseStatus  and ah.hospId = ac.caseHospCode  and ac.nabhHosp is null ");
				  
				  
			
				  /*if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
					  query1.append(" and ap1.patientId = ac.casePatientNo and ap.patientId= ap1.patientId ");
				  }*/
				  if(casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" and ct.caseId=ac.caseId and ct.asriCatCode in ('"+ config.getString("DentalSurgeryID") +"') and ct.activeyn='Y' "); 
					  }
				  }
				  if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" and ct.caseId=ac.caseId and ct.asriCatCode not in ('"+ config.getString("DentalSurgeryID") +"') "); 
					  }
				  }
				  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
				  {
					  query1.append(" and acs.caseId = ac.caseId ");
					  query1.append("  and adm.disCatId = acs.diaCatId  "); 
					 
					
				  }
				  
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
			  {
				if(casesSearchVO.getDiaFlg()==null || "".equalsIgnoreCase(casesSearchVO.getDiaFlg()))
				{
					query1.append("  and anu.id.userId = '"+casesSearchVO.getUserId()+"' and anu.id.hospId = ac.caseHospCode and anu.effEndDt is null ");
				}
				/*else if(casesSearchVO.getDiaFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaFlg()))
				{
					query1.append("  and anu.id.userId = '"+casesSearchVO.getUserId()+"' and anu.effEndDt is null ");
				}*/
				else if(casesSearchVO.getDiaPendFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaPendFlg()))
				{
					query1.append("  and anu.id.userId = '"+casesSearchVO.getUserId()+"' and anu.effEndDt is null ");
				}
			  }
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
					  query1.append("  and anu.id.mithraId = '"+casesSearchVO.getUserId()+"' and anu.id.hospId = ac.caseHospCode and anu.endDt is null ");
				  
				  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("N"))
					 {
				  if(casesSearchVO.getCaseSearchType()!=null && !casesSearchVO.getCaseSearchType().equalsIgnoreCase("") )
				  {
					  if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp"))
						  query1.append(" and ac.caseStatus not in ('"+config.getString("preauth_chronic_op_status")+"'");
					  /**
						 * Remove Case Drafted and OP Case Registered from Cases Search
						 */
					  if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("caseOp"))
						  query1.append(" ,'"+config.getString("CASE.OPCaseRegistered")+"'");
					  /*if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("caseDrafted"))
						  query1.append(" ,'"+config.getString("CASE.CaseDrafted")+"'");*/
					  query1.append(")");
				  }
					 }
				/**
				 * cases for approval appending case status to query
				 */
				  
				  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y")&& !("Y").equalsIgnoreCase(pendingFlag))
					 {
					  
				      if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")){
						  query1.append(" and nvl(ac.pckAppvAmt,0)+nvl(ac.comorbidAppvAmt,0)+nvl(ac.enhAppvAmt,0)-nvl(ac.csClAmount,0) > 0 ");
						  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))){  
							  query1.append(" and ac.caseStatus in ('CD51') and (ac.errClaimStatus in ('"+ config.getString("EHF.Claims.CHPendErr") +"','"+config.getString("EHF.Claims.CTDPendErr") +"') or ac.errClaimStatus is null)" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))){
								query1.append(" and ac.caseStatus in ('CD51') and spm.id.specialityId not in ('"+ config.getString("DentalSurgeryID") +"') and ac.errClaimStatus in ('"+ config.getString("EHF.Claims.ErrInit") +"','"+config.getString("EHF.Claims.ErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
								query1.append(" and ac.caseStatus in ('CD51') and ac.errClaimStatus in ('"+ config.getString("EHF.Claims.CTDAppr") +"','"+config.getString("EHF.Claims.chErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~"))){
								query1.append(" and ac.caseStatus in ('CD51') and ac.errClaimStatus in ('"+ config.getString("EHF.Claims.CHAppErr") +"')" );
							}
					  }
				      /* added by satish kola for erroneous dental claim cases*/
				      else if(casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")){
						  query1.append(" and nvl(ac.pckAppvAmt,0)+nvl(ac.comorbidAppvAmt,0)+nvl(ac.enhAppvAmt,0)-nvl(ac.csClAmount,0) > 0 ");
						  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))){  
							  query1.append(" and ac.caseStatus in ('CD51') and (ac.errClaimStatus in ('"+ config.getString("EHF.Claims.CHPendErr") +"','"+config.getString("EHF.Claims.CTDPendErr") +"') or ac.errClaimStatus is null)" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))){
								query1.append(" and ac.caseStatus in ('CD51') and spm.id.specialityId in ('"+ config.getString("DentalSurgeryID") +"') and ac.errClaimStatus in ('"+ config.getString("EHF.Claims.ErrInit") +"','"+config.getString("EHF.Claims.ErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
								query1.append(" and ac.caseStatus in ('CD51') and ac.errClaimStatus in ('"+ config.getString("EHF.Claims.CTDAppr") +"','"+config.getString("EHF.Claims.chErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~"))){
								query1.append(" and ac.caseStatus in ('CD51') and ac.errClaimStatus in ('"+ config.getString("EHF.Claims.CHAppErr") +"')" );
							}
					  }
					  else{
						  if(!moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserId()!=null && !casesSearchVO.getUserId().equalsIgnoreCase("") && (mappedGroups.contains(config.getString("userGroup_eo"))))
						  {
							  query1.append(" and ((ac.ceoApprovalFlag='Y' and ac.caseStatus in ('CD801','CD16')) or ");	  
						  }
						  else if(moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
						  {
							  query1.append(" and ah.hospActiveYN in ('Y','P','D','E','S','C','CB','SP','CP','CBP') and ");
						  }
					  else if(moduleType.equalsIgnoreCase("cochlearAco"))
					  {
						 
						  query1.append(" and  ac.caseStatus='CD425' ");
						 
					}
						  else 
						  {
							  query1.append(" and ");
						  }
						  if(caseStatus != null && caseStatus.size() >0)
							  query1.append("  ac.caseStatus in ( "); 
						  int statusCount=0;
						 for(int k=0; k<caseStatus.size() ;k++)
						 {
						   if(k!=0 && k!=caseStatus.size())
						     {
							   query1.append(" , ");  
						     }
						   query1.append(" '"+caseStatus.get(k)+"' ");
						   statusCount++;//Added to check atleast one Case Status exists for PPD/CTD
						 }
						 if(moduleType!=null && ((ptdFlag && moduleType.equalsIgnoreCase("preauth"))
									|| (ctdFlag && moduleType.equalsIgnoreCase("claim")))
									&& (casesSearchVO.getCaseForDissFlg()==null || (casesSearchVO.getCaseForDissFlg()!=null && !casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")))) 
							 	{
							 		if(statusCount>0)
							 			query1.append( " , ");
							 		
							 		query1.append( " "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+"   " );
							 	}
						 
						  if(caseStatus != null && caseStatus.size() >0)
						  {
						   query1.append(" ) ");  
						  if(!moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserId()!=null && !casesSearchVO.getUserId().equalsIgnoreCase("") && (mappedGroups.contains(config.getString("userGroup_eo"))))
							  {
							   query1.append(" ) ");  
							  }
						  
					  }
						  else if(moduleType.equalsIgnoreCase("cochlearAco"))
						  {
							  query1.append(" ");
						  }
							  else
								  query1.append("  ac.caseStatus in ('') ");
					  }
				      /**
				       * check fort ppd login
				       */
				      
					 }
				  if((ppdFlag||cpdFlag) && !ppdComiteFlag)
				  		{
				  		query1.append("  and spm.id.specialityId = acs1.asriCatCode and pmg.id.procId=acs1.icdProcCode and spm.id.userId=pmg.id.userId and pmg.activeYN='Y' "); 
					  		
					  		if(ppdFlag && cpdFlag)
					  			query1.append(" and spm.id.grpId in('"+config.getString("preauth_ppd_role")+"','"+config.getString("claim_cpd_role")+"') ");
					  		else if(cpdFlag)
					  			query1.append(" and spm.id.grpId in('"+config.getString("claim_cpd_role")+"') ");
					  		else if(ppdFlag)
					  			query1.append(" and spm.id.grpId in('"+config.getString("preauth_ppd_role")+"') " );
					  		
					  		query1.append(" and (ac.paneldocuserid in ('"+casesSearchVO.getUserId()+"') or ac.paneldocuserid is null)   ");
					  		
                            
					  		
			    	  
					  		query1.append(" and acs1.caseId = ac.caseId  and spm.id.userId = '"+casesSearchVO.getUserId()+"'  ");
					  		query1.append(" and (ac.ppdGrpFlg ='N' or ac.ppdGrpFlg is null) and spm.activeYN='Y'   ");
				  		}
				  else if(ptdFlag || ctdFlag)
				  		{
							query1.append(" and spm.id.specialityId = acs1.asriCatCode  ");
							if(ptdFlag && ctdFlag)
								query1.append(" and spm.id.grpId in('"+config.getString("preauth_ptd_role")+"','"+config.getString("EHF.Claims.CTD")+"') ");
							else if(ctdFlag)
								query1.append(" and spm.id.grpId in('"+config.getString("EHF.Claims.CTD")+"') ");
							else if(ptdFlag)
								query1.append(" and spm.id.grpId in('"+config.getString("preauth_ptd_role")+"') " );
							query1.append(" and acs1.caseId = ac.caseId  and spm.id.userId = '"+casesSearchVO.getUserId()+"'  ");
							query1.append(" and spm.activeYN='Y'   ");
							
							if(moduleType!=null && ((ptdFlag && moduleType.equalsIgnoreCase("preauth"))
									|| (ctdFlag && moduleType.equalsIgnoreCase("claim")))
									&& (casesSearchVO.getCaseForDissFlg()==null || (casesSearchVO.getCaseForDissFlg()!=null && !casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")))) 
								{
									query1.append( " and (case when (ac.caseStatus in ( "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+" )   " );
									query1.append( "          			and acs1.asriCatCode = '"+config.getString("DentalSurgeryID")+"' and acs1.activeyn = 'Y'  " );
									query1.append( "     			) then '1'     " );
									query1.append( "           when ((ac.caseStatus not in ( "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+" ) )" );
									query1.append( "     			) 	then '1'    " );
									query1.append( "     	   else '0' 	 " );
									query1.append( "     end) =1     " );
								}
				  		}
				  /*if(cpdFlag){
					  query1.append(" and spm.id.grpId = '"+config.getString(moduleType+"_cpd_role")+"' and spm.id.specialityId = acs1.asriCatCode  ");
			    	  query1.append(" and acs1.caseId = ac.caseId  and spm.id.userId = '"+casesSearchVO.getUserId()+"'  ");
					  query1.append(" and spm.activeYN='Y'   "); //and   ac.ppdGrpFlg ='N'
				  }*/
			      if(ppdComiteFlag)  
			      {
			    	  query1.append(" and ac.ppdGrpFlg ='Y' " );	  
			      }
			      if(ppdComiteFlag || ppdFlag || cpdFlag)
			      {
			    	  query1.append(" and ac.caseHospCode not in ( select  hp.id.hospitalId  from EhfmUsrHospitalMpg hp where  hp.id.userId = '"+casesSearchVO.getUserId()+"'  and hp.activeYN ='Y' ) " );	  
			      }
			      /*if(casesSearchVO.getEnhanceflag()!=null &&  casesSearchVO.getEnhanceflag().equals("Y"))
				  {
					  query1.append("and ac.enhFlag in('Y') ");
				  }
			      else
			      {
			    	  query1.append("and ac.enhFlag is  null ");
			      }*/
		      /**
		       * Added for hubspoke CR
		       */
		      if(casesSearchVO.getDiaFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaFlg()))
		      {
		    	  query1.append(" and ac.caseHospCode = hsm.id.spokeHospId and hsm.id.spokeHospId = ah.id.hospId and hsm.id.hubUserId = '"+casesSearchVO.getUserId()+"'");
		    	  //query1.append(" and anu.id.hospId = ah.id.hospId ");
		      }
		      if(casesSearchVO.getDiaPendFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaPendFlg()))
		      {
		    	  query1.append(" and ac.caseHospCode = hsm.id.spokeHospId and hsm.id.spokeHospId = ah.id.hospId ");
		    	  query1.append(" and anu.id.hospId = ah.id.hospId ");
		      }
		      /**
		       * End of hubspoke CR
		       */
				  if(casesSearchVO.getSearchFlag() != null && casesSearchVO.getSearchFlag().equalsIgnoreCase("Y"))
				 {				 			
					  
					 if(casesSearchVO.getClaimStatus() != null && !casesSearchVO.getClaimStatus().equals(""))
					  {
						  query1.append("  and ac.caseStatus = '"+casesSearchVO.getClaimStatus()+"' ");
					  }
					 if(casesSearchVO.getWapNo() != null && !casesSearchVO.getWapNo().equals(""))
					  {
						 query1.append("  and upper(ap.cardNo) like  upper('%"+casesSearchVO.getWapNo().trim()+"%') "); 
					  }
					 if(casesSearchVO.getCaseNo() != null && !casesSearchVO.getCaseNo().equals(""))
					  {
						 query1.append("  and upper(ac.caseId) like  upper('"+casesSearchVO.getCaseNo().trim()+"') "); 
					  }
					 if(casesSearchVO.getClaimNo() != null && !casesSearchVO.getClaimNo().equals(""))
					  {
						 query1.append("  and upper(ac.claimNo) like  upper('%"+casesSearchVO.getClaimNo().trim()+"%') "); 
					  }
					 if(casesSearchVO.getPatName() != null && !casesSearchVO.getPatName().equals(""))
					  {
						 query1.append("  and upper(ap.name) like  upper('%"+casesSearchVO.getPatName().trim()+"%') "); 
					  }
					 if(casesSearchVO.getCatId() != null && !casesSearchVO.getCatId().equals(""))
					  {
						 query1.append("  and upper(ac.csDisMainCode) like  upper('%"+casesSearchVO.getCatId().trim()+"%') "); 
					  }
					 if(casesSearchVO.getErrStatusId() != null && !casesSearchVO.getErrStatusId().equals(""))
					  {
						 query1.append("  and upper(ac.errClaimStatus) like  upper('%"+casesSearchVO.getErrStatusId().trim()+"%') "); 
					  }
					  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
					  {
						 query1.append("  and upper(acs.therapyId) like  upper('%"+casesSearchVO.getSurgyId().trim()+"%') "); 
					  }
					  if(casesSearchVO.getHospId() != null && !casesSearchVO.getHospId().equals(""))
					  {
						 query1.append("  and ac.caseHospCode='"+casesSearchVO.getHospId().trim()+"' "); 
					  }
					  if((casesSearchVO.getMainCatName() != null && !casesSearchVO.getMainCatName().equals("") && !casesSearchVO.getMainCatName().equals("-1")) || (casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1")))
				      {
						  query1.append("  and acs.caseId= ac.caseId and acs.asriCatCode= '"+casesSearchVO.getMainCatName()+"' and emc.id.asriCatCode= acs.asriCatCode and acs.activeyn='Y' "); 
						  if(casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1"))
							  query1.append(" and acs.icdCatCode= '"+casesSearchVO.getCatName()+"' and emc.id.icdCatCode= acs.icdCatCode ");  
						  if(casesSearchVO.getProcName() != null && !casesSearchVO.getProcName().equals("") && !casesSearchVO.getProcName().equals("-1"))
					      {
							  query1.append("  and acs.icdProcCode='"+casesSearchVO.getProcName()+"' and etp.id.icdProcCode= acs.icdProcCode  ");  
					      }
				      }
					 
					  if(casesSearchVO.getFromDate()!=null && !casesSearchVO.getFromDate().equals("") && casesSearchVO.getToDate()!=null && !casesSearchVO.getToDate().equals(""))
					  { 
							fromDate=casesSearchVO.getFromDate();
							sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
							toDate=casesSearchVO.getToDate().toString();
							
							//To include todate in search criteria--adding date+1 
							Calendar cal = Calendar.getInstance();  
				        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
				        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
				        	Date tomorrow = cal.getTime();  
				        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
				        	 //End of date+1 
							 
							sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
							
							if(database.equalsIgnoreCase("ORACLE"))
								query1.append("and ac.caseRegnDate between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
							else if(database.equalsIgnoreCase("MYSQL"))
								query1.append("and ac.caseRegnDate between '"+sqlFromDate+"' and '"+sqlToDate+"'");
						}
					  if(casesSearchVO.getTelephonicId() != null && !casesSearchVO.getTelephonicId().equals(""))
					  {
						 query1.append("  and ap.intimationId='"+casesSearchVO.getTelephonicId().trim()+"' "); 
					  }
				 }
				  
					/*added to separate dental and normal cases for  CEX*/
				  if(cexFlag&& casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ac.caseId=ect.caseId ");
				  }
				  if(cexFlag && ("Y").equalsIgnoreCase(dentalFlag)&& casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ect.asriCatCode  in ('S18')");
				  }
				  if(cexFlag && !("Y").equalsIgnoreCase(dentalFlag) && casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ect.asriCatCode  not in ('S18')");
				  }
				  
				  if((casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) || (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y"))){
						 
					   if(casesSearchVO.getRoleId()!=null && ((mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))||(mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~")) || (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~")) )){
							query1.append(" and ac.caseId=eec.caseId " );
					   }
				  }
				  if((casesSearchVO.getSchemeVal()!=null && !casesSearchVO.getSchemeVal().equalsIgnoreCase("")) && ((casesSearchVO.getErrCaseApprovalFlag()!=null && !casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) && (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && !casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")))){
					  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD202"))
						  query1.append(" and ac.schemeId in ('CD202') ");
					  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD203"))
					  query1.append(" and ac.schemeId in ('CD201','CD202','1') ");
					  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
					  query1.append(" and ac.schemeId in ('"+casesSearchVO.getSchemeVal()+"') ");
					 // else
						//  query1.append(" and ac.schemeId in ('"+casesSearchVO.getSchemeVal()+"','1') ");
				  }
				  else if((casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) || (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y"))){
						 
					   if(casesSearchVO.getRoleId()!=null && ((mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))||(mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~")) || (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~")) )){
						   if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD202"))
								  query1.append(" and eec.schemeId in ('CD202') ");
							  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD203"))
							  query1.append(" and eec.schemeId in ('CD201','CD202','1') ");
							  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
							  query1.append(" and eec.schemeId in ('"+casesSearchVO.getSchemeVal()+"') ");
					   }
				  } 
				  
				/*  
				  if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
				  {
					  query1.append("    and ac.caseId=ecc.caseId and ecc.icdProcCode=em.id.icdProcCode and em.id.state=ac.schemeId ");
				  }*/
				  
				
				  
				  if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
				  {
					  
					  String[] claimStatus=null;
					  if(("claim").equalsIgnoreCase(moduleType))
					  claimStatus =  ClaimsConstants.claimsCEOSentBackStatuses;
					  else if(("preauth").equalsIgnoreCase(moduleType) && !("Y").equalsIgnoreCase(dentalFlag))
					  claimStatus =  ClaimsConstants.preauthCEOSentBackStatuses;
					  else if(("Y").equalsIgnoreCase(dentalFlag))
					  claimStatus =  ClaimsConstants.preauthCEODentalSentBackStatuses;  
						  
						
					  query1.append(" and ac.caseStatus in ( ");
					  for (int i = 0; i < claimStatus.length; i++) {
							query1.append(" '" + claimStatus[i] + "' ");
							if (i != claimStatus.length - 1)
								query1.append(",");
					  }
							query1.append(" ) ");
					  
					  
					  query1.append("   and ac.pendingWith in ('"+casesSearchVO.getUserId()+"') ");  
				 
				  }
				  
					/*added for separating cases for JHS and EHS*/
				  if(casesSearchVO.getPatientScheme()!=null && !casesSearchVO.getPatientScheme().equalsIgnoreCase(""))
					{
						query1.append(" and ac.patientScheme in ('"
									+ casesSearchVO.getPatientScheme() + "') ");
					}
				  /*end of condition for JHS and EHS*/
				  
				 if(flg!=null && casesSearchVO.getCasesForApprovalFlag()!=null && !("Y").equalsIgnoreCase(pendingFlag) )
				  {
					  if(casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
					  
					 {
					  if(!acoCond.contains("Y"))
							 {
						  		if(flg.equalsIgnoreCase("Y"))
							 		query1.append(" and ac.cochlearYN='Y' ");
							 	/*else if(flg.equalsIgnoreCase("N"))
							 		query1.append(" and ac.cochlearYN is null ");*/
						 	}
					  
					 }}
				 if(organFlg!=null && casesSearchVO.getCasesForApprovalFlag()!=null && !("Y").equalsIgnoreCase(pendingFlag) )
				  {
					  if(casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
					  
						 {
						  if(!acoCond.contains("Y"))
								 {
							  		if(organFlg.equalsIgnoreCase("Y"))
								 		query1.append(" and ac.organTransYN='Y' ");
								 	/*else if(flg.equalsIgnoreCase("N"))
								 		query1.append(" and ac.cochlearYN is null ");*/
							 	}
						  
						 }
				  }
			 	if(casesSearchVO.getDiaPendFlg()!=null && casesSearchVO.getDiaPendFlg().equalsIgnoreCase("Y"))
			 	{
			 			query1.append(" order by ac.lstUpdDt asc ");
			 	}
			 	else if(casesSearchVO.getDiaFlg()!=null && casesSearchVO.getDiaFlg().equalsIgnoreCase("Y"))
			 	{
			 		query1.append(" order by ac.lstUpdDt desc ");
					 }
				  if(!casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y")){
				  query1.append(" order by ac.caseRegnDate desc ");} 
				  else
				  {
				  if(moduleType!=null && ("claim").equalsIgnoreCase(moduleType))
				  {
					  query1.append(" order by ac.clmSubDt asc ");   
				  }
				  else
				  {
				  query1.append(" order by ac.caseRegnDate asc "); 
				  }
				  }
				  
				
				  query.append(query1);
				 
				  lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();
		        
				 query = new StringBuffer();
		         query.append( " select count(distinct ac.caseId) ");
		         query.append(query1);
		         
		         Long count = (Long) session.createQuery(query.toString()).uniqueResult();
		         System.out.println(count);
		         
		         retCasesSearchVO.setTotRowCount(Long.toString(count));
			  }
				  catch(Exception e)
			  {
				e.printStackTrace();  
			  }
				  finally {
						session.close();
						factory.close();
					}
			  /*
			   * Incase of CSV/Excel Skip Color Coding.
			   */
			  if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
				  retCasesSearchVO.setLstCaseSearch(lstCases);
			  }
			  else
			  	{
			  /**
			   * assigning colors to the cases based on the criteria
			   */
			  
					  for(CasesSearchVO lStCasesSearchVO : lstCases)
					  {
						  
						  if(casesSearchVO.getErrCaseApprovalFlag()==null || (casesSearchVO.getErrCaseApprovalFlag()!=null && !casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")))
						  {
								if(lStCasesSearchVO.getCaseStatusId() != null && lStCasesSearchVO.getLstUpdDt() != null)
								{
								
									Long hours = (long) 0;
									Calendar startCal = GregorianCalendar.getInstance(); 
									startCal.setTime( lStCasesSearchVO.getLstUpdDt());
									long startTime = startCal.getTimeInMillis(); 
									hours=((new java.util.Date()).getTime() - startTime)/ (60 * 60 * 1000); 
									boolean flag = false;
									/**
									 * nam forwarded , medoc re-initiated 
									 */
									if(lStCasesSearchVO.getCaseStatusId().equalsIgnoreCase(config.getString(moduleType+"_nam_forwarded")) || lStCasesSearchVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_medco_preauth_reinitiated"))  )
									{
										lStCasesSearchVO.setColorFlag("ReddishPink");	
										flag = true;
									}
									/**
									 * check preauth nam forwarded after 6 hours	
									 */
									
									if(lStCasesSearchVO.getCaseStatusId().equalsIgnoreCase(config.getString(moduleType+"_nam_forwarded")) && hours > 6 )
									{
										lStCasesSearchVO.setColorFlag("grey");
										flag = true;
									}
									/**
									 * ptd pending 6 hours or more
									 */
									 if(lStCasesSearchVO.getCaseStatusId().equalsIgnoreCase(config.getString(moduleType+"_ptd_pending")) && hours > 6 )
									{
										lStCasesSearchVO.setColorFlag("green");	
										flag = true;
									}
									if(!flag)
									{
										lStCasesSearchVO.setColorFlag("0");
									}
									
									
									
								}// end of for
								System.out.println(lStCasesSearchVO.getCaseStatusId() + "  " +casesSearchVO.getUserRole() +"  "+ lStCasesSearchVO.getErrStatusId());
								String userRole = "";
					            if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("")){
									userRole= casesSearchVO.getUserRole();
								}
								else{
									userRole= casesSearchVO.getRoleId();
								}		
								System.out.println(userRole);
								 if(lStCasesSearchVO.getErrStatusId()!=null && !lStCasesSearchVO.getErrStatusId().equalsIgnoreCase("")){
										EhfmCmbDtls asrimCombo = genericDao.findById(EhfmCmbDtls.class, String.class, lStCasesSearchVO.getErrStatusId());
										if(asrimCombo!=null){
											lStCasesSearchVO.setErrStatus(asrimCombo.getCmbDtlName());
										}
									}
								  }
						  else{
							  //if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")){
								  lStCasesSearchVO.setColorFlag("0");
								  if(lStCasesSearchVO.getErrStatusId()!=null && !lStCasesSearchVO.getErrStatusId().equalsIgnoreCase("")){
										EhfmCmbDtls asrimCombo = genericDao.findById(EhfmCmbDtls.class, String.class, lStCasesSearchVO.getErrStatusId());
										if(asrimCombo!=null){
											lStCasesSearchVO.setErrStatus(asrimCombo.getCmbDtlName());
										}
									}
									}
						 // }
						  
						lstFinalCases.add(lStCasesSearchVO);
					  }
					  
					  retCasesSearchVO.setLstCaseSearch(lstFinalCases);
			  	}
			  /**
			   * end 
			   */
			  
			  retCasesSearchVO.setStartIndex(Integer.toString(startIndex));
			  retCasesSearchVO.setShowPage(casesSearchVO.getShowPage());
			
			return retCasesSearchVO;
		}
	/*
	 * Added by Srikalyan for CSV Downloads Query Insertion
	 */
	@Override
	public CasesSearchVO getListCasesForCSV(CasesSearchVO casesSearchVO)
	{
		String flg="N";
		String acoCond="N";
		CasesSearchVO retCasesSearchVO = new CasesSearchVO();
		retCasesSearchVO.setCsvMsg("Failed");
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		List<String>  caseStatus = new ArrayList<String>();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String dentalFlag=casesSearchVO.getDentalFlag();
		
		String database=config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
		boolean ppdFlag = false; boolean ptdFlag=false;
		boolean ppdComiteFlag = false; boolean ctdFlag=false;
		boolean cpdFlag= false;
		boolean cexFlag=false;
		String moduleType = casesSearchVO.getModule();
		String pendingFlag=casesSearchVO.getPendingFlag();
		  /**
		   * To get the groups to which the user is mapped
		   */
		  if(moduleType!=null && !moduleType.equalsIgnoreCase(""))
			  {
				  if(moduleType.contains("preauth"))
						{
							moduleType="preauth";
						}
					if(moduleType.contains("claim"))
						{
							moduleType="claim";
						}
			  }
		  if(moduleType==null || moduleType.equalsIgnoreCase(""))
			  moduleType="common";
		  
		  String mappedGroups="~";
		  try{
			 
			 /**
			  * get cases status for approval
			  */
			 for(LabelBean labelBean:casesSearchVO.getGrpList())
	 		    {
					 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP17")||labelBean.getID() != null &&   labelBean.getID().equals( "GP2")
							 ||labelBean.getID() != null &&   labelBean.getID().equals( "GP1")||labelBean.getID() != null &&   labelBean.getID().equals( "GP97"))
						 acoCond="Y";
					 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP71")||labelBean.getID() != null &&   labelBean.getID().equals( "GP72")
							)
						 flg="Y";
					 String caseSts = "";
					 if(casesSearchVO.getCaseForDissFlg()!=null && casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y"))
						 {
							 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP8"))
								 caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
							 else if (labelBean.getID() != null &&   labelBean.getID().equals( "GP9"))
								 caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
							 else if (labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))
								 {
								 	caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
								 	flg="Y";
								 }
						 }
					 else{
						 	caseSts =  config.getString(moduleType+"_caseStatus_"+labelBean.getID());
			 		     	if(labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))
			 		     		{
			 		     			if(caseSts.contains("CD149"))
			 		     				flg="Y";
			 		     		}
					 	 }
	 		     if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ppd_role")) && !ppdFlag )
		 		     {
		 		    	ppdFlag = true; 
		 		     }
	 		     if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("claim_cpd_role")) && !cpdFlag )
				     {
		 		    	cpdFlag = true; 
				     }
	 		     if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ppdGrp_role")) && !ppdComiteFlag )
				     {
		 		    	ppdComiteFlag = true; 
				     } 
	             if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ptd_role")) && !ppdFlag && !cpdFlag && !ptdFlag)
				     {
		 			  ptdFlag = true; 
				     }
	 		     if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("EHF.Claims.CTD")) && !ppdFlag && !cpdFlag && !ctdFlag)
				     {
					  ctdFlag = true; 
				     }	
	 		     if (caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("EHF.Claims.CEX"))
						&& !ppdFlag && !cpdFlag && !ptdFlag) 
	 		 		 {
	 			 		cexFlag = true;
	 		 		 }
	 		     if(caseSts != null && !caseSts.equalsIgnoreCase(""))
		 		     {
			 		     StringTokenizer str = new StringTokenizer(caseSts,"~");
			 			  while(str.hasMoreTokens())
				 			  {
				 				 caseStatus.add(str.nextElement().toString());  
				 			  }
		 		     }
		 		     
	 		     	mappedGroups=mappedGroups+labelBean.getID()+"~";
	 		    }
				 /**
				 * set view flag as N for Cases blocked by this user
				 */
			 	List<GenericDAOQueryCriteria>  criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			 	criteriaList.add(new GenericDAOQueryCriteria("caseBlockedUsr",casesSearchVO.getUserId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 	criteriaList.add(new GenericDAOQueryCriteria("viewFlag","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 	List<EhfCase> blockedCaseList=genericDao.findAllByCriteria(EhfCase.class,criteriaList);
			 	for(EhfCase ehfCase:blockedCaseList)
					{
						ehfCase.setViewFlag("N");
						ehfCase.setCaseBlockedUsr(casesSearchVO.getUserId());
						ehfCase.setCase_blocked_dt(new java.sql.Timestamp(new Date().getTime()));
						genericDao.save(ehfCase);
					}
				 /**
				  * end of getting status for cases for approval
				  */
				  query.append("  select distinct ac.CASE_ID ,ac.CASE_NO  ,ac.flagged ,ap.name ,  ap.card_no , acb.cmb_dtl_name  ,ah.hosp_name ,ah.hosp_id ,ac.SCHEME_ID ,ac.CASE_PATIENT_NO,ac.CLM_SUB_DT as SUBMITTEDDATE,ac.caseRegnDate as CASEREGNDATE   ");
				  query.append(" , ac.CASE_STATUS , ac.LST_UPD_DT  ,ac.err_clm_status , ap.PATIENT_ID , ac.pending_flag ,ac.preauth_pckg_amt ,ac.ENH_FLAG , nvl(ac.enh_req_amt,0) ,nvl(ac.preauth_total_package_amt,0) ");
				  query.append(" ,	to_char(eccm.TDS_PCT_AMT) , to_char(eccm.TRUST_PCT_AMT)  ,to_char(eccm.HOSP_PCT_AMT)  ,to_char(eccm.TDS_HOSP_PCT_AMT)  ");
				  
				  if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp"))
				  query.append("  , ap.patient_ipop , ap.intimation_id ");
				  //if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
				  query.append(" ,ac.view_flag , ac.case_blocked_usr  ");
				  //}
				  query.append("  ,ac.CASE_REGN_DATE  , nvl(ac.CS_CL_AMOUNT,0)  , nvl(eccm.claim_bill_amt,0),ac.CLAIM_NO , ac.grievance_flag  "); 
				  query.append("  ,ac.cs_preauth_dt ,ac.preauth_aprv_dt ,ac.CS_DIS_DT ,ac.CS_DEATH_DT ,ac.CLM_SUB_DT ,ac.cs_trans_id , ac.cs_trans_dt ,ac.cs_remarks ,ac.cs_sbh_dt ");
				  
				 /* if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
				  {
					  query.append(" ,em.procName as procName ");
				  }*/
				  
				  query1.append("   from ehfm_cmb_dtls acb,  EHFM_HOSPITALS ah  ");
				 
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))
					  query1.append(",    ehfm_medco_dtls anu ");
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
					  query1.append(",    EHFM_HOSP_MITHRA_DTLS anu "); 
				  if(casesSearchVO.getCaseSearchType()!=null && !casesSearchVO.getCaseSearchType().equalsIgnoreCase("") && casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp"))
					  {
						  query1.append(" ,  EHF_PATIENT ap	,EHF_CHRONIC_CASE_DTLS ac , EHF_CHRONIC_CASE_CLAIM eccm  ");
					  }
				  else
					  {
		            	  query1.append(" ,   EHF_PATIENT ap ,EHF_CASE ac , EHF_CASE_CLAIM eccm  "); //EhfCasePatient ap ,
					  }
				if((ppdFlag ||cpdFlag) && !ppdComiteFlag )
					{
						query1.append(" ,EHFM_USR_speciality_MPG spm ,  EHF_CASE_THERAPY acs1  ");	
					}
				else if(ptdFlag || ctdFlag)
					{
						query1.append(" ,EHFM_USR_speciality_MPG spm ,  EHF_CASE_THERAPY acs1 ");
					}
				if(cexFlag && casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
					{
						query1.append(" ,EHF_CASE_THERAPY ect ");
					}
				
				if(casesSearchVO.getSearchFlag() != null && casesSearchVO.getSearchFlag().equalsIgnoreCase("Y"))
				 	{
						if((casesSearchVO.getMainCatName() != null && !casesSearchVO.getMainCatName().equals("") && !casesSearchVO.getMainCatName().equals("-1")) || (casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1")))
						    {
							  	query1.append("  , EHF_CASE_THERAPY acs ,  EHFM_MAIN_CATEGORY emc ");  
								if(casesSearchVO.getProcName() != null && !casesSearchVO.getProcName().equals("") && !casesSearchVO.getProcName().equals("-1"))
								    {
									  	query1.append("   ,  EHFM_MAIN_THERAPY etp ");  
								    }
						    }
				 	}
				/*if(cpdFlag){
					query1.append(" ,EhfmUsrSpecialityMpg spm ,  EhfCaseTherapy acs1  ");	
				}
				 if(ppdComiteFlag || ppdFlag)
			      {
					 query1.append(" , EhfmUsrHospitalMpg hp "); 
			      }*/
			  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
				  {
					  query1.append("  , EHF_CASE_THERAPY acs ");//,  EhfmDiagnosisCatMst adm   
				  }
			  
			 /* if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
			  {
				  query1.append("   ,EhfCaseTherapy ecc,EhfmTherapyProcMst em ");
			  }*/
			  
			  
			  /*if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
			  {
				  query1.append("  , EhfmUsersUnitDtls eud  ");  
			  }
			  */
			  
			  query1.append("  where ap.PATIENT_ID = ac.CASE_PATIENT_NO and acb.cmb_dtl_id = ac.CASE_STATUS  and ah.hosp_id = ac.CASE_HOSP_CODE   ");
			  /*if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
				  query1.append(" and ap1.patientId = ac.casePatientNo and ap.patientId= ap1.patientId ");
			  }*/
			  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
				  {
					  query1.append(" and acs.case_id = ac.case_id ");
					  //query1.append("  and adm.disCatId = acs.diaCatId  "); 
				  }
			  
			  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
			    query1.append("  and anu.MEDCO_ID = '"+casesSearchVO.getUserId()+"' and anu.HOSP_ID = ac.CASE_HOSP_CODE and anu.END_DT is null "); 
			  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
				  query1.append("  and anu.mithra_id= '"+casesSearchVO.getUserId()+"' and anu.hosp_id = ac.CASE_HOSP_CODE and anu.end_dt is null ");
			  
			  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("N"))
				 {
					  if(casesSearchVO.getCaseSearchType()!=null && !casesSearchVO.getCaseSearchType().equalsIgnoreCase("") )
						  {
					  if(casesSearchVO.getClaimStatus()==null || casesSearchVO.getClaimStatus().equalsIgnoreCase("")  )
					  {
							  if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp"))
								  query1.append(" and ac.CASE_STATUS not in ('"+config.getString("preauth_chronic_op_status")+"'");
							  /**
								 * Remove Case Drafted and OP Case Registered from Cases Search
								 */
							  if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("caseOp"))
								  query1.append(" ,'"+config.getString("CASE.OPCaseRegistered")+"'");
							  /*if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("caseDrafted"))
								  query1.append(" ,'"+config.getString("CASE.CaseDrafted")+"'");*/
							  query1.append(")");
					  }
						  }
				 }
			/**
			 * cases for approval appending case status to query
			 */
			  
			  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y")&& !("Y").equalsIgnoreCase(pendingFlag))
				 {
				  
			      if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y"))
				      {
						  query1.append(" and nvl(ac.PCK_APPV_AMT,0)+nvl(ac.comorbid_appv_amt,0)+nvl(ac.enh_appv_amt,0)-nvl(ac.CS_CL_AMOUNT,0) > 0 ");
						  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
							  {  
								  	query1.append(" and ac.CASE_STATUS in ('CD51') and (ac.err_clm_status in ('"+ config.getString("EHF.Claims.CHPendErr") +"','"+config.getString("EHF.Claims.CTDPendErr") +"') or ac.err_clm_status is null)" );
							  }
						  else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~")))
						  	  {
							  		query1.append(" and ac.CASE_STATUS in ('CD51')  and ac.err_clm_status in ('"+ config.getString("EHF.Claims.ErrInit") +"','"+config.getString("EHF.Claims.ErrReInit") +"')" );
							  }
						  else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~")))
						  	  {
									query1.append(" and ac.CASE_STATUS in ('CD51') and ac.err_clm_status in ('"+ config.getString("EHF.Claims.CTDAppr") +"','"+config.getString("EHF.Claims.chErrReInit") +"')" );
							  }
						  else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~")))
						  	  {
									query1.append(" and ac.CASE_STATUS in ('CD51') and ac.err_clm_status in ('"+ config.getString("EHF.Claims.CHAppErr") +"')" );
							  }
					  }
				  else
				  	  {
						  if(!moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserId()!=null && !casesSearchVO.getUserId().equalsIgnoreCase("") && (mappedGroups.contains(config.getString("userGroup_eo"))))
							  {
								  query1.append(" and ((ac.ceo_approval_Flag='Y' and ac.CASE_STATUS in ('CD801','CD16')) or ");	  
							  }
						  else if(moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
							  {
								  query1.append(" and ah.hosp_active_yn in ('Y','P','D','E','S','C','CB','SP','CP','CBP') and ");
							  }
						  else 
							  {
								  query1.append(" and ");
							  }
						  if(caseStatus != null && caseStatus.size() >0)
							  query1.append("  ac.CASE_STATUS in ( ");  
						 for(int k=0; k<caseStatus.size() ;k++)
							 {
								   if(k!=0 && k!=caseStatus.size())
									     {
										   query1.append(" , ");  
									     }
								   query1.append(" '"+caseStatus.get(k)+"' ");	
							 }
						 
						  if(caseStatus != null && caseStatus.size() >0)
							  {
							  		query1.append(" ) ");  
							  		if(!moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserId()!=null && !casesSearchVO.getUserId().equalsIgnoreCase("") && (mappedGroups.contains(config.getString("userGroup_eo"))))
										  {
										   		query1.append(" ) ");  
										  }
							  }
							  else
								  query1.append("  ac.CASE_STATUS in ('') ");
				  	  }
			      /**
			       * check fort ppd login
			       */
			      
				 }
			  if((ppdFlag||cpdFlag) && !ppdComiteFlag)
					{
				    	  query1.append("  and spm.Speciality_id = acs1.asri_Cat_Code  "); 
				    	  if(ppdFlag && cpdFlag)
				    		  query1.append(" and spm.grp_id in('"+config.getString("preauth_ppd_role")+"','"+config.getString("claim_cpd_role")+"') ");
				    	  else if(cpdFlag)
				    		  query1.append(" and spm.grp_id in('"+config.getString("claim_cpd_role")+"') ");
				    	  else if(ppdFlag)
				    		  query1.append(" and spm.grp_id in('"+config.getString("preauth_ppd_role")+"') " );
				    	  
				    	  query1.append(" and acs1.case_id = ac.case_id  and spm.USER_ID = '"+casesSearchVO.getUserId()+"'  ");
						  query1.append(" and (ac.ppd_grp_flg ='N' or ac.ppd_grp_flg is null) and spm.activeYN='Y'   ");
					}
			  else if(ptdFlag || ctdFlag)
			  		{
							query1.append(" and spm.Speciality_id = acs1.asri_Cat_Code  ");
							 if(ptdFlag && ctdFlag)
					    		  query1.append(" and spm.grp_id in('"+config.getString("preauth_ptd_role")+"','"+config.getString("EHF.Claims.CTD")+"') ");
					    	  else if(ctdFlag)
					    		  query1.append(" and spm.grp_id in('"+config.getString("EHF.Claims.CTD")+"') ");
					    	  else if(ptdFlag)
					    		  query1.append(" and spm.grp_id in('"+config.getString("preauth_ptd_role")+"') " );
							query1.append(" and acs1.case_id = ac.case_id  and spm.USER_ID = '"+casesSearchVO.getUserId()+"'  ");
							query1.append(" and spm.activeYN='Y'   ");
			  		}
			  /*if(cpdFlag){
				  query1.append(" and spm.id.grpId = '"+config.getString(moduleType+"_cpd_role")+"' and spm.id.specialityId = acs1.asriCatCode  ");
		    	  query1.append(" and acs1.caseId = ac.caseId  and spm.id.userId = '"+casesSearchVO.getUserId()+"'  ");
				  query1.append(" and spm.activeYN='Y'   "); //and   ac.ppdGrpFlg ='N'
			  }*/
		      if(ppdComiteFlag)  
			      {
			    	  query1.append(" and ac.ppd_grp_flg ='Y' " );	  
			      }
		      if(ppdComiteFlag || ppdFlag || cpdFlag)
			      {
			    	  query1.append(" and ac.CASE_HOSP_CODE not in ( select  hp.hospital_id  from EHFM_USR_hospital_MPG hp where  hp.USER_ID = '"+casesSearchVO.getUserId()+"'  and hp.activeYN ='Y' ) " );	  
			      }
		      if(casesSearchVO.getEnhanceflag()!=null &&  casesSearchVO.getEnhanceflag().equals("Y"))
				  {
					  query1.append("and ac.ENH_FLAG in('Y') ");
				  }
		      else
			      {
			    	  query1.append("and ac.ENH_FLAG is  null ");
			      }
			  if(casesSearchVO.getSearchFlag() != null && casesSearchVO.getSearchFlag().equalsIgnoreCase("Y"))
				 {				 			
					  
					 if(casesSearchVO.getClaimStatus() != null && !casesSearchVO.getClaimStatus().equals(""))
						  {
							  query1.append("  and ac.CASE_STATUS = '"+casesSearchVO.getClaimStatus()+"' ");
						  }
					 if(casesSearchVO.getWapNo() != null && !casesSearchVO.getWapNo().equals(""))
						  {
							 query1.append("  and upper(ap.card_no) like  upper('%"+casesSearchVO.getWapNo().trim()+"%') "); 
						  }
					 if(casesSearchVO.getCaseNo() != null && !casesSearchVO.getCaseNo().equals(""))
						  {
							 query1.append("  and upper(ac.CASE_ID) like  upper('"+casesSearchVO.getCaseNo().trim()+"') "); 
						  }
					 if(casesSearchVO.getClaimNo() != null && !casesSearchVO.getClaimNo().equals(""))
						  {
							 query1.append("  and upper(ac.CLAIM_NO) like  upper('%"+casesSearchVO.getClaimNo().trim()+"%') "); 
						  }
					 if(casesSearchVO.getPatName() != null && !casesSearchVO.getPatName().equals(""))
						  {
							 query1.append("  and upper(ap.name) like  upper('%"+casesSearchVO.getPatName().trim()+"%') "); 
						  }
					 if(casesSearchVO.getCatId() != null && !casesSearchVO.getCatId().equals(""))
						  {
							 query1.append("  and upper(ac.CS_DIS_MAIN_CODE) like  upper('%"+casesSearchVO.getCatId().trim()+"%') "); 
						  }
					 if(casesSearchVO.getErrStatusId() != null && !casesSearchVO.getErrStatusId().equals(""))
						  {
							 query1.append("  and upper(ac.err_clm_status) like  upper('%"+casesSearchVO.getErrStatusId().trim()+"%') "); 
						  }
					  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
						  {
							 query1.append("  and upper(acs.case_therapy_id) like  upper('%"+casesSearchVO.getSurgyId().trim()+"%') "); 
						  }
					  if(casesSearchVO.getHospId() != null && !casesSearchVO.getHospId().equals(""))
						  {
							 query1.append("  and ac.CASE_HOSP_CODE='"+casesSearchVO.getHospId().trim()+"' "); 
						  }
					  if((casesSearchVO.getMainCatName() != null && !casesSearchVO.getMainCatName().equals("") && !casesSearchVO.getMainCatName().equals("-1")) || (casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1")))
					      {
							  query1.append("  and acs.case_id= ac.CASE_ID and acs.asri_Cat_Code= '"+casesSearchVO.getMainCatName()+"' and emc.ASRI_CAT_CODE= acs.asri_Cat_Code and acs.activeyn='Y' "); 
							  if(casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1"))
								  query1.append(" and acs.asri_Cat_Code= '"+casesSearchVO.getCatName()+"' and emc.ICD_CAT_CODE= acs.ICD_Cat_Code ");  
							  if(casesSearchVO.getProcName() != null && !casesSearchVO.getProcName().equals("") && !casesSearchVO.getProcName().equals("-1"))
							      {
									  query1.append("  and acs.ICD_Proc_Code='"+casesSearchVO.getProcName()+"' and etp.ICD_PROC_CODE= acs.ICD_Proc_Code  ");  
							      }
					      }
					 
					  if(casesSearchVO.getFromDate()!=null && !casesSearchVO.getFromDate().equals("") && casesSearchVO.getToDate()!=null && !casesSearchVO.getToDate().equals(""))
					    { 
							fromDate=casesSearchVO.getFromDate();
							sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
							toDate=casesSearchVO.getToDate().toString();
							
							//To include todate in search criteria--adding date+1 
							Calendar cal = Calendar.getInstance();  
				        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
				        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
				        	Date tomorrow = cal.getTime();  
				        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
				        	 //End of date+1 
							 
							sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
							
							if(database.equalsIgnoreCase("ORACLE"))
								query1.append("and ac.CASE_REGN_DATE between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
							else if(database.equalsIgnoreCase("MYSQL"))
								query1.append("and ac.CASE_REGN_DATE between '"+sqlFromDate+"' and '"+sqlToDate+"'");
						}
					  if(casesSearchVO.getTelephonicId() != null && !casesSearchVO.getTelephonicId().equals(""))
						  {
							 query1.append("  and ap.intimation_id='"+casesSearchVO.getTelephonicId().trim()+"' "); 
						  }
				 }
			  
				/*added to separate dental and normal cases for  CEX*/
			  if(cexFlag&& casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ac.CASE_ID=ect.case_id ");
				  }
			  if(cexFlag && ("Y").equalsIgnoreCase(dentalFlag)&& casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ect.asri_Cat_Code  in ('S18')");
				  }
			  if(cexFlag && !("Y").equalsIgnoreCase(dentalFlag) && casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ect.asri_Cat_Code  not in ('S18')");
				  }
			  
			  
			  if(casesSearchVO.getSchemeVal()!=null && !casesSearchVO.getSchemeVal().equalsIgnoreCase("")){
				  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
					
				  query1.append(" and ac.scheme_Id in ('"+casesSearchVO.getSchemeVal()+"') ");
				  else
					  query1.append(" and ac.scheme_Id in ('"+casesSearchVO.getSchemeVal()+"','1') ");
			  }
			  
			  
			  
			/*  
			  if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
			  {
				  query1.append("    and ac.caseId=ecc.caseId and ecc.icdProcCode=em.id.icdProcCode and em.id.state=ac.schemeId ");
			  }*/
			  
			
			  
			  if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
				  {
					  
					  String[] claimStatus=null;
					  if(("claim").equalsIgnoreCase(moduleType))
					  claimStatus =  ClaimsConstants.claimsCEOSentBackStatuses;
					  else if(("preauth").equalsIgnoreCase(moduleType) && !("Y").equalsIgnoreCase(dentalFlag))
					  claimStatus =  ClaimsConstants.preauthCEOSentBackStatuses;
					  else if(("Y").equalsIgnoreCase(dentalFlag))
					  claimStatus =  ClaimsConstants.preauthCEODentalSentBackStatuses;  
						  
						
					  query1.append(" and ac.CASE_STATUS in ( ");
					  for (int i = 0; i < claimStatus.length; i++) 
						  {
								query1.append(" '" + claimStatus[i] + "' ");
								if (i != claimStatus.length - 1)
									query1.append(",");
						  }
							query1.append(" ) ");
					  
					  
					  query1.append("   and ac.PENDING_WITH in ('"+casesSearchVO.getUserId()+"') ");  
				 
				  }
			  
				/*added for separating cases for JHS and EHS*/
			  if(casesSearchVO.getPatientScheme()!=null && !casesSearchVO.getPatientScheme().equalsIgnoreCase(""))
				{
					query1.append(" and ac.patient_scheme in ('"
								+ casesSearchVO.getPatientScheme() + "') ");
				}
			  /*end of condition for JHS and EHS*/
			  if(flg!=null && casesSearchVO.getCasesForApprovalFlag()!=null && !("Y").equalsIgnoreCase(pendingFlag) )
			  	 {
				  	 if(casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
						 {
							  if(!acoCond.contains("Y"))
									 {
								  		if(flg.equalsIgnoreCase("Y"))
									 		query1.append(" and ac.cochlear_YN='Y' ");
									 	else if(flg.equalsIgnoreCase("N"))
									 		query1.append(" and ac.cochlear_YN is null ");
								 	}
							  
						 }
				  }
			 		query1.append(" and ac.case_id = eccm.case_id(+) ");
			  if(!casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y")){
				  query1.append(" order by ac.CASE_REGN_DATE desc ");} 
			  else
			  {
			  if(moduleType!=null && ("claim").equalsIgnoreCase(moduleType))
			  {
				  query1.append(" order by ac.CLM_SUB_DT asc ");   
			  }
			  else
			  {
				  query1.append(" order by ac.CASE_REGN_DATE asc "); 
			  }
			  }
			  
			 
			
			  query.append(query1);
			  if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("") &&
					  casesSearchVO.getDownloadCSV()!=null && !casesSearchVO.getDownloadCSV().equalsIgnoreCase(""))
			  	{
				  	if(casesSearchVO.getExcelFlag().equalsIgnoreCase("Y") && casesSearchVO.getDownloadCSV().equalsIgnoreCase("Y"))
				  		{
				  			String requestId=nextSeqVal(config.getString("CASE.CSVSEQUENCE"));
				  			EhfCsvCaseSearch ehfCsvCaseSearch=new EhfCsvCaseSearch();
				  			if(requestId!=null)
				  				{
						  			ehfCsvCaseSearch.setRequestId("CSVFILE"+requestId);
						  			ehfCsvCaseSearch.setUserId(casesSearchVO.getUserId());
						  			ehfCsvCaseSearch.setCrtUsr(casesSearchVO.getUserId());
						  			ehfCsvCaseSearch.setCrtDt(new Date());
						  			ehfCsvCaseSearch.setCsvStatus("N");
						  			ehfCsvCaseSearch.setCsvQuery(query.toString());
						  			ehfCsvCaseSearch.setFileName("CSVFILE"+requestId+".CSV");
						  			
						  			ehfCsvCaseSearch=genericDao.save(ehfCsvCaseSearch);
						  			
						  			if(ehfCsvCaseSearch!=null)
						  				{
						  					retCasesSearchVO.setCsvMsg("Success");
						  					return retCasesSearchVO;
						  				}
				  				}	
				  		}
			  	}
		  }
		  catch(Exception e)
			  {
				e.printStackTrace();  
				gLogger.error("Error Occured in getListCasesForCSV method of CasesSearchDaoImpl "+e.getMessage());
			  }
		return retCasesSearchVO;
	}
	@Override
	public CasesSearchVO getTeleIntimationCases(CasesSearchVO casesSearchVO) {
		List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
		CasesSearchVO retCasesSearchVO = new CasesSearchVO();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database=config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		
		 SessionFactory factory= hibernateTemplate.getSessionFactory();
		  Session session=factory.openSession();
		  int startIndex=Integer.parseInt(casesSearchVO.getStartIndex());
		  int maxResult = (Integer.parseInt(casesSearchVO.getRowsPerPage()))*(Integer.parseInt(casesSearchVO.getShowPage()));
		  try{
			 if(casesSearchVO.getShowPage() != null && Integer.parseInt(casesSearchVO.getShowPage()) > 1 )
			 {
				 startIndex =  Integer.parseInt(casesSearchVO.getRowsPerPage()) * (Integer.parseInt(casesSearchVO.getShowPage())-1);
			 }
			 query.append("  select distinct case when (sysdate-trr.crtDt) <= 3 then 'Telephonic Intimation-Initiated' ");
			 query.append(" when (sysdate-trr.crtDt) > 3 then 'Telephonic Intimation Cancelled' end as teleStatus, ");
			 query.append(" trr.telephonicId as caseNo, trr.cardNo as wapNo, trr.name as patientName,trr.crtDt as inpatientCaseSubDt,");
			 query.append(" trr.callerName as patName,trr.callerMobileNo as claimNo,EL.locName as distId,EH.hospName as catId ,ETCM.icdCatName as errStatusId,ETPM.procName as surgyId, ");
			 query.append("  trr.doctorName as mandalId,trr.docMobileNo as villageId,trr.cardType as schemeVal ");
			 query1.append(" from EhfTelephonicRegn trr,EhfmHospitals EH ,EhfmLocations EL,EhfmHospMithraDtls EMU,EhfmTherapyProcMst ETPM,EhfmTherapyCatMst ETCM  ");
			 query1.append(" where trr.hospId=EH.hospId and EL.id.locId=trr.districtCode and EMU.id.hospId=trr.hospId and trr.ICDCatCode=ETCM.id.icdCatCode and trr.ICDProcCode=ETPM.id.icdProcCode" );
			 query1.append(" and trr.asriCatCode=ETCM.id.asriCatCode and trr.asriCatCode=ETPM.id.asriCode");
			 query1.append(" and trr.patientId is null and EMU.id.mithraId='"+ casesSearchVO.getUserId()+"' and trr.hospId=EMU.id.hospId  and EMU.endDt is null");
			 if(casesSearchVO.getCaseNo()!=null && !"".equals(casesSearchVO.getCaseNo()))
			 {
				 query1.append(" and trr.telephonicId='"+casesSearchVO.getCaseNo()+"'");
			 }
			 if(casesSearchVO.getFromDate()!=null && !casesSearchVO.getFromDate().equals("") && casesSearchVO.getToDate()!=null && !casesSearchVO.getToDate().equals(""))
			  { 
					fromDate=casesSearchVO.getFromDate();
					sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
					toDate=casesSearchVO.getToDate().toString();
					
					//To include todate in search criteria--adding date+1 
					Calendar cal = Calendar.getInstance();  
		        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
		        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
		        	Date tomorrow = cal.getTime();  
		        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
		        	 //End of date+1 
					 
					sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
					
					if(database.equalsIgnoreCase("ORACLE"))
						query1.append(" and trr.crtDt between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
					else if(database.equalsIgnoreCase("MYSQL"))
						query1.append(" and trr.crtDt between '"+sqlFromDate+"' and '"+sqlToDate+"'");
				}
			 query1.append(" order  by trr.crtDt");
			 query.append(query1);
			 lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();
		        
			 query = new StringBuffer();
	         query.append( " select count(*) ");
	         query.append(query1);
	         
	         Long count = (Long) session.createQuery(query.toString()).uniqueResult();
	         System.out.println(count);
	         retCasesSearchVO.setTotRowCount(Long.toString(count));
		  }catch(Exception e)
		  {
			e.printStackTrace();  
		  }
		  finally {
				session.close();
				factory.close();
			}
		  retCasesSearchVO.setLstCaseSearch(lstCases);
		  retCasesSearchVO.setStartIndex(Integer.toString(startIndex));
		  retCasesSearchVO.setShowPage(casesSearchVO.getShowPage());
		
		return retCasesSearchVO;
	}
	@Override
	public CasesSearchVO getRegTeleIntimationCases(CasesSearchVO casesSearchVO) {
		List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
		CasesSearchVO retCasesSearchVO = new CasesSearchVO();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database=config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		
		 SessionFactory factory= hibernateTemplate.getSessionFactory();
		  Session session=factory.openSession();
		  int startIndex=Integer.parseInt(casesSearchVO.getStartIndex());
		  int maxResult = (Integer.parseInt(casesSearchVO.getRowsPerPage()))*(Integer.parseInt(casesSearchVO.getShowPage()));
		  try{
			 if(casesSearchVO.getShowPage() != null && Integer.parseInt(casesSearchVO.getShowPage()) > 1 )
			 {
				 startIndex =  Integer.parseInt(casesSearchVO.getRowsPerPage()) * (Integer.parseInt(casesSearchVO.getShowPage())-1);
			 }
			 query.append("  select distinct case when (sysdate-trr.crtDt) <= 3 then 'Telephonic Intimation-Initiated' ");
			 query.append(" when (sysdate-trr.crtDt) > 3 then 'Telephonic Intimation Cancelled' end as teleStatus, ");
			 query.append(" trr.telephonicId as caseNo, trr.cardNo as wapNo, trr.name as patientName,trr.crtDt as inpatientCaseSubDt,");
			 query.append(" trr.callerName as patName,trr.callerMobileNo as claimNo,EL.locName as distId,EH.hospName as catId ,ETCM.icdCatName as errStatusId,ETPM.procName as surgyId, ");
			 query.append("  trr.doctorName as mandalId,trr.docMobileNo as villageId,trr.cardType as schemeVal ");
			 query1.append(" from EhfTelephonicRegn trr,EhfmHospitals EH ,EhfmLocations EL,EhfmTherapyProcMst ETPM,EhfmTherapyCatMst ETCM  ");
			 query1.append(" where trr.hospId=EH.hospId and EL.id.locId=trr.districtCode  and trr.ICDCatCode=ETCM.id.icdCatCode and trr.ICDProcCode=ETPM.id.icdProcCode" );
			 query1.append(" and trr.asriCatCode=ETCM.id.asriCatCode and trr.asriCatCode=ETPM.id.asriCode");
			 query1.append(" and trr.patientId is null ");
			 if(casesSearchVO.getCaseNo()!=null && !"".equals(casesSearchVO.getCaseNo()))
			 {
				 query1.append(" and trr.telephonicId='"+casesSearchVO.getCaseNo()+"'");
			 }
			 if(casesSearchVO.getFromDate()!=null && !casesSearchVO.getFromDate().equals("") && casesSearchVO.getToDate()!=null && !casesSearchVO.getToDate().equals(""))
			  { 
					fromDate=casesSearchVO.getFromDate();
					sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
					toDate=casesSearchVO.getToDate().toString();
					
					//To include todate in search criteria--adding date+1 
					Calendar cal = Calendar.getInstance();  
		        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
		        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
		        	Date tomorrow = cal.getTime();  
		        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
		        	 //End of date+1 
					 
					sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
					
					if(database.equalsIgnoreCase("ORACLE"))
						query1.append(" and trr.crtDt between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
					else if(database.equalsIgnoreCase("MYSQL"))
						query1.append(" and trr.crtDt between '"+sqlFromDate+"' and '"+sqlToDate+"'");
				}
			 query1.append(" order  by trr.crtDt");
			 query.append(query1);
			 lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();
		        
			  query = new StringBuffer();
	         query.append( " select count(*) ");
	         query.append(query1);
	         
	         Long count = (Long) session.createQuery(query.toString()).uniqueResult();
	         System.out.println(count);
	         retCasesSearchVO.setTotRowCount(Long.toString(count));
		  }catch(Exception e)
		  {
			e.printStackTrace();  
		  }
		  finally {
				session.close();
				factory.close();
			}
		  retCasesSearchVO.setLstCaseSearch(lstCases);
		  retCasesSearchVO.setStartIndex(Integer.toString(startIndex));
		  retCasesSearchVO.setShowPage(casesSearchVO.getShowPage());
		
		return retCasesSearchVO;
	}
	@Override
	public CasesSearchVO getListIssues(CasesSearchVO casesSearchVO) {
		// TODO Auto-generated method stub
		
		
		List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
		CasesSearchVO retCasesSearchVO = new CasesSearchVO();
		StringBuffer query = new StringBuffer();	
		//String searchvalue=null;
		 SessionFactory factory= hibernateTemplate.getSessionFactory();
		  Session session=factory.openSession();
		  int startIndex=Integer.parseInt(casesSearchVO.getStartIndex());
		  int maxResult = (Integer.parseInt(casesSearchVO.getRowsPerPage()));//*(Integer.parseInt(casesSearchVO.getShowPage()));
		  try{
			 if(casesSearchVO.getShowPage() != null && Integer.parseInt(casesSearchVO.getShowPage()) > 1 )
			 {
				 startIndex =  Integer.parseInt(casesSearchVO.getRowsPerPage()) * (Integer.parseInt(casesSearchVO.getShowPage())-1);
			 }
			
			 query.append("  select e.issueId as issueId,e.caseId as issuecaseId,ecd.cmbDtlName as issuestatus,e.issueTitle as issuetitle,e.crtDt as createddate from EhfIssue e,EhfmUsrHospitalMpg ehm,EhfmCmbDtls ecd where ehm.id.userId='"+casesSearchVO.getUserId()+"' and e.hospId=ehm.id.hospitalId and ecd.cmbDtlId=e.issueStatus ");
			 if(casesSearchVO.getSearchFlag()!=null)
			 {
				 if(casesSearchVO.getIssueId()!=null ||casesSearchVO.getIssuecaseId()!=""){
					 if(casesSearchVO.getIssueId()!=null){
						 query.append("and e.issueId='"+casesSearchVO.getIssueId()+"'"); 
					 }
					 if(casesSearchVO.getIssuecaseId()!=""){
						 query.append("and e.caseId='"+casesSearchVO.getIssuecaseId()+"'"); 
					 }
					 if(casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!=""){
						 query.append("and e.crtDt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')"); 
					 }
					 
				 }
				 else{
					 if(casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!=""){
						 query.append("and e.crtDt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')"); 
					 }
				 }
			 }
			 lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();   
			  query = new StringBuffer();
	         query.append( " select count(*) ");
	         query.append(" from EhfIssue e,EhfmUsrHospitalMpg ehm,EhfmCmbDtls ecd where ehm.id.userId='"+casesSearchVO.getUserId()+"' and e.hospId=ehm.id.hospitalId and ecd.cmbDtlId=e.issueStatus ");
	    	 if(casesSearchVO.getSearchFlag()!=null){
				 if(casesSearchVO.getIssueId()!=null ||casesSearchVO.getIssuecaseId()!=""){
					 if(casesSearchVO.getIssueId()!=null){
						 query.append("and e.issueId='"+casesSearchVO.getIssueId()+"'"); 
					 }
					 if(casesSearchVO.getIssuecaseId()!=""){
						 query.append("and e.caseId='"+casesSearchVO.getIssuecaseId()+"'"); 
					 }
					 if(casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!=""){
						 query.append("and e.crtDt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')"); 
					 }
					 
				 }
				 else{
					 if(casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!=""){
						 query.append("and e.crtDt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')"); 
					 }
				 }
			 }
	         
	         Long count = (Long) session.createQuery(query.toString()).uniqueResult();
	         System.out.println(count);
	         retCasesSearchVO.setTotRowCount(Long.toString(count));
		  }catch(Exception e)
		  {
			e.printStackTrace();  
		  }
		  finally {
				session.close();
				factory.close();
			}
		  retCasesSearchVO.setLstCaseSearch(lstCases);
		  retCasesSearchVO.setStartIndex(Integer.toString(startIndex));
		  retCasesSearchVO.setShowPage(casesSearchVO.getShowPage());
		  retCasesSearchVO.setColorFlag("green");	
		
		return retCasesSearchVO;
	}
	public CasesSearchVO getFeedbackList(CasesSearchVO casesSearchVO)
	{

		List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
		CasesSearchVO retCasesSearchVO = new CasesSearchVO();
		StringBuffer query = new StringBuffer();	
		//String searchvalue=null;
		 SessionFactory factory= hibernateTemplate.getSessionFactory();
		  Session session=factory.openSession();
		  int startIndex=Integer.parseInt(casesSearchVO.getStartIndex());
		  int maxResult = (Integer.parseInt(casesSearchVO.getRowsPerPage()));//*(Integer.parseInt(casesSearchVO.getShowPage()));
		  try{
			 if(casesSearchVO.getShowPage() != null && Integer.parseInt(casesSearchVO.getShowPage()) > 1 )
			 {
				 startIndex =  Integer.parseInt(casesSearchVO.getRowsPerPage()) * (Integer.parseInt(casesSearchVO.getShowPage())-1);
			 }
			
			 query.append("select f.id.feedback_id as FEEDBACKID,f.name as CREATEDBY,f.crt_dt as CREATEDON ,f.feedback as REMARKS from EhfFeedback f");
			 if(casesSearchVO.getSearchFlag()!=null)
			 {
					 if(casesSearchVO.getFEEDBACKID()!=null )
					 {
						 
						if( casesSearchVO.getFromDate()=="" && casesSearchVO.getToDate()=="")
					 {
						 query.append(" where f.id.feedback_id='"+casesSearchVO.getFEEDBACKID()+"'"); 
					 }
						else if (casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!="")
						{
							query.append(" where f.crt_dt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')");	
						}
							
					 }
					 if(casesSearchVO.getFEEDBACKID()==null )
					 {
						 if(casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!="")
					         {
						 query.append(" where f.crt_dt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')"); 
					          }
				     }
					
					 
					 
			 }
			 lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();   
			  query = new StringBuffer();
	         query.append( "select count(*)");
	         query.append("from EhfFeedback f ");
	         if(casesSearchVO.getSearchFlag()!=null)
			 {
					 if(casesSearchVO.getFEEDBACKID()!=null )
					 {
						 
						if( casesSearchVO.getFromDate()=="" && casesSearchVO.getToDate()=="")
					 {
						 query.append(" where f.id.feedback_id='"+casesSearchVO.getFEEDBACKID()+"'"); 
					 }
						else if (casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!="")
						{
							query.append(" where f.crt_dt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')");	
						}
							
					 }
					 if(casesSearchVO.getFEEDBACKID()==null )
					 {
						 if(casesSearchVO.getFromDate()!="" && casesSearchVO.getToDate()!="")
					         {
						 query.append(" where f.crt_dt between TO_DATE('"+casesSearchVO.getFromDate()+"','DD-MM-YYYY') and TO_DATE('"+casesSearchVO.getToDate()+"','DD-MM-YYYY')"); 
					          }
				     }
					
					 
					 
			 }
				
				 
			 
	         Long count = (Long) session.createQuery(query.toString()).uniqueResult();
	         System.out.println(count);
	         retCasesSearchVO.setTotRowCount(Long.toString(count));
		              }catch(Exception e)
		          {
			e.printStackTrace();  
		          }
		              finally {
		      			session.close();
		      			factory.close();
		      		}
		  retCasesSearchVO.setLstCaseSearch(lstCases);
		  retCasesSearchVO.setStartIndex(Integer.toString(startIndex));
		  retCasesSearchVO.setShowPage(casesSearchVO.getShowPage());
		  retCasesSearchVO.setColorFlag("green");	
		
		return retCasesSearchVO;
	}
	
	@SuppressWarnings("unchecked")
	public List<CasesSearchVO> getDeathCases(CasesSearchVO casesSearchVO)
	{
		List<CasesSearchVO> getListCases = null;
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database=config.getString("Database");
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
			StringBuffer query= new StringBuffer();
			query.append(" select ec.caseId as caseId, ec.caseNo as caseNo, ec.claimNo as claimNo, ep.name as patientName, ep.cardNo as wapNo, ecd.cmbDtlName as claimStatus, ");
			query.append(" eh.hospName as hospName, to_char(ec.caseRegnDate,'DD/MM/YYYY hh24:mi:ss') as caseBlockedUsr, to_char(ec.csDeathDt,'DD/MM/YYYY') as caseForDissFlg, ");
			query.append(" ep.intimationId as teleStatus, ep.patientIpop as patIpOp ");
			query.append(" from EhfCase ec, EhfPatient ep, EhfmHospitals eh, EhfmCmbDtls ecd ");
			if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))
				query.append(",    EhfmMedcoDetails anu ");
			if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
				query.append(",    EhfmHospMithraDtls anu "); 
			query.append(" where ec.casePatientNo= ep.patientId and eh.hospId= ec.caseHospCode and ec.csDeathDt is not null ");
			query.append(" and ecd.cmbDtlId= ec.caseStatus ");
			if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))
				query.append("  and anu.id.userId = '"+casesSearchVO.getUserId()+"' and anu.id.hospId = ec.caseHospCode and anu.effEndDt is null "); 
			if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
				query.append("  and anu.id.mithraId = '"+casesSearchVO.getUserId()+"' and anu.id.hospId = ec.caseHospCode and anu.endDt is null ");
			  
			if(casesSearchVO.getClaimStatus()!=null && !"".equalsIgnoreCase(casesSearchVO.getClaimStatus()))
			{
				query.append(" and ec.caseStatus= '"+casesSearchVO.getClaimStatus()+"'");
			}
			if(casesSearchVO.getWapNo()!=null && !"".equalsIgnoreCase(casesSearchVO.getWapNo()))
			{
				query.append(" and ep.cardNo= '"+casesSearchVO.getWapNo().toUpperCase()+"' ");
			}
			if(casesSearchVO.getCaseNo()!=null && !"".equalsIgnoreCase(casesSearchVO.getCaseNo()))
			{
				query.append(" and ec.caseId= '"+casesSearchVO.getCaseNo().toUpperCase()+"' ");
			}
			if(casesSearchVO.getClaimNo()!=null && !"".equalsIgnoreCase(casesSearchVO.getClaimNo()))
			{
				query.append(" and ec.claimNo= '"+casesSearchVO.getClaimNo()+"' ");
			}
			if(casesSearchVO.getPatName()!=null && !"".equalsIgnoreCase(casesSearchVO.getPatName()))
			{
				query.append(" and ep.name= '"+casesSearchVO.getPatName()+"' ");
			}
			if(casesSearchVO.getFromDate()!=null && !casesSearchVO.getFromDate().equals("") && casesSearchVO.getToDate()!=null && !casesSearchVO.getToDate().equals(""))
			{ 
				fromDate=casesSearchVO.getFromDate();
				sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
				toDate=casesSearchVO.getToDate().toString();
				
				//To include todate in search criteria--adding date+1 
				Calendar cal = Calendar.getInstance();  
	        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
	        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
	        	Date tomorrow = cal.getTime();  
	        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
	        	 //End of date+1 
				 
				sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
				
				if(database.equalsIgnoreCase("ORACLE"))
					query.append("and ec.csDeathDt between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
				else if(database.equalsIgnoreCase("MYSQL"))
					query.append("and ec.csDeathDt between '"+sqlFromDate+"' and '"+sqlToDate+"'");
			}
			 if(casesSearchVO.getSchemeVal()!=null && !casesSearchVO.getSchemeVal().equalsIgnoreCase("")){
			
					  query.append(" and ec.schemeId in ('"+casesSearchVO.getSchemeVal()+"','1') ");
			  }
			  
			query.append(" order by ec.caseRegnDate asc ");
			
			/*String args[]= new String[2];
			args[0]= "DD/MM/YYYY hh24:mi:ss";
			args[1]= "DD/MM/YYYY";
			getListCases= genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString(), args); Fetches all results*/
			if(casesSearchVO.getEnd_index()!=0)
			{
			getListCases=session.createQuery(query.toString()).setFirstResult(casesSearchVO.getStart_index()).setMaxResults(casesSearchVO.getEnd_index()).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();
			}
			else if((casesSearchVO.getStart_index()==0)&&(casesSearchVO.getEnd_index()==0))
			{
				getListCases=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
			factory.close();
		}
		return getListCases;
	}
	
	public CasesSearchVO getListCasesDirect(CasesSearchVO casesSearchVO)
	{

		List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();

		CasesSearchVO retCasesSearchVO = new CasesSearchVO();
		StringBuffer query = new StringBuffer();
		List<String> caseStatus = new ArrayList<String>();
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		Session session = factory.openSession();

		boolean ppdFlag = false;
		boolean ptdFlag = false;
		boolean ppdComiteFlag = false;
		boolean ctdFlag = false;
		boolean cpdFlag = false;
		boolean coc_com=false;
		boolean coctd=false;
		boolean chFlag=false;
		 boolean cexFlag=false;
		 boolean pexFlag=false;
		 boolean eoFlag=false;
		 boolean eocommiteeFlag=false;
		String moduleType = casesSearchVO.getModule();
		String dentalFlag=casesSearchVO.getDentalFlag();
		
		String hubFlg = "N";
		 String spokePendFlg = "N";
		/**
		 * To get the groups to which the user is mapped
		 */

		if(moduleType!=null && !moduleType.equalsIgnoreCase(""))
		  {
		  if(moduleType.contains("preauth"))
			{
				moduleType="preauth";
			}
			if(moduleType.contains("claim"))
			{
				moduleType="claim";
			}
		  }
		
		if (moduleType == null || moduleType.equalsIgnoreCase(""))
			moduleType = "common";

		String mappedGroups = "~";
		try {
			/**
			 * get cases status for approval
			 */
			for (LabelBean labelBean : casesSearchVO.getGrpList()) {
				String caseSts = "";String remCaseStats="";
				if (casesSearchVO.getCaseForDissFlg() != null
						&& casesSearchVO.getCaseForDissFlg().equalsIgnoreCase(
								"Y")) {
					
					
					if (labelBean.getID() != null
							&& labelBean.getID().equals("GP8"))
						caseSts = config.getString(moduleType
								+ "_caseStatus_DIS_" + labelBean.getID());
					else if (labelBean.getID() != null
							&& labelBean.getID().equals("GP9"))
						caseSts = config.getString(moduleType
								+ "_caseStatus_DIS_" + labelBean.getID());
					
					remCaseStats=config.getString(moduleType
							+ "_caseStatus_DIS_" + config.getString("preauth_ctd_role"));
					
					
				} 
				else if(dentalFlag!=null && "Y".equalsIgnoreCase(dentalFlag) && labelBean.getID() != null &&   labelBean.getID().equals( "GP9") )
				{
					 caseSts = config.getString(moduleType+"_caseStatusDent_"+labelBean.getID());
					 
				}
				
				else  if((hubFlg != null && hubFlg!="Y")){
					caseSts = config.getString(moduleType + "_caseStatus_"
							+ labelBean.getID());
					
					
					
				}
				
				
				if(casesSearchVO.getDiaFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaFlg()))
				 {
					 caseSts = config.getString(moduleType+"_caseStatusHub_"+labelBean.getID());
					 hubFlg="Y";
				 }
				/*if(dentalFlag!=null && ("Y").equalsIgnoreCase(dentalFlag))
				{
					remCaseStats=config.getString(moduleType + "_caseStatus_"+ config.getString("preauth_ctd_role"));
				}
				else
				{
					remCaseStats=config.getString(moduleType + "_caseStatus_"+ config.getString("Group.COCH"));	
				}
				*/
				
				
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("preauth_ppd_role"))
						&& !ppdFlag) {
					ppdFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("preauth_ch_role"))
						&& !chFlag) {
					chFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& (labelBean.getID().equals(
								config.getString("claim_cpd_role"))||labelBean.getID().equals(
										config.getString("claim_coc_com_role"))) && !cpdFlag) {
					if(labelBean.getID().equals(
							config.getString("claim_coc_com_role")))
						coc_com=true;
					cpdFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("EHF.Claims.EOComm"))
						&& !eocommiteeFlag) {
					eocommiteeFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("preauth_pex_role"))
						&& !pexFlag) {
					pexFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("Group.AHC-EO"))
						&& !eoFlag) {
					eoFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("preauth_ppdGrp_role"))
						&& !ppdComiteFlag) {
					ppdComiteFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("preauth_ptd_role"))
						&& !ppdFlag && !cpdFlag && !ptdFlag) {
					ptdFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&& labelBean.getID().equals(
								config.getString("EHF.Claims.CEX"))
						&& !ppdFlag && !cpdFlag && !ptdFlag) {
					cexFlag = true;
				}
				if (caseSts != null
						&& labelBean.getID() != null
						&&( labelBean.getID().equals(
								config.getString("EHF.Claims.CTD")) ||labelBean.getID().equals(
										config.getString("EHF.Claims.COCTD")))&& !ppdFlag
						&& !cpdFlag && !ctdFlag) {
					
					if(labelBean.getID().equals(
							config.getString("EHF.Claims.COCTD")))
						coctd=true;
					
					ctdFlag = true;
				}
				if (caseSts != null && !caseSts.equalsIgnoreCase("")) {
					StringTokenizer str = new StringTokenizer(caseSts, "~");
					while (str.hasMoreTokens()) {
						caseStatus.add(str.nextElement().toString());
					}

				}
				mappedGroups = mappedGroups + labelBean.getID() + "~";
			}
			/**
			 * end of getting status for cases for approval
			 */
			query.append("  select distinct ac.caseId as caseId,ac.caseRegnDate as InpatientCaseSubDt, ac.caseStatus as caseStatusId,ac.schemeId as schemeId,ac.clmSubDt as SUBMITTEDDATE,ac.lstUpdDt as csSbhDt ");
			query.append(" from EhfmCmbDtls acb,  EhfmHospitals ah ");

			if (casesSearchVO.getUserRole() != null
					&& !casesSearchVO.getUserRole().equalsIgnoreCase("")
					&& casesSearchVO.getUserRole().equalsIgnoreCase(
							config.getString("preauth_medco_role")))
				query.append(",    EhfmMedcoDetails anu ");
			if (casesSearchVO.getUserRole() != null
					&& !casesSearchVO.getUserRole().equalsIgnoreCase("")
					&& casesSearchVO.getUserRole().equalsIgnoreCase(
							config.getString("preauth_mithra_role")))
				query.append(",    EhfmHospMithraDtls anu ");
			if (casesSearchVO.getCaseSearchType() != null
					&& !casesSearchVO.getCaseSearchType().equalsIgnoreCase("")
					&& casesSearchVO.getCaseSearchType().equalsIgnoreCase(
							"ChronicOp")) {
				query.append(" ,EhfChronicCase ac,  EhfPatient ap ");
				
			} 
			
			
			else {
				query.append(" ,EhfCase ac,   EhfPatient ap "); // EhfCasePatient
																	// ap ,
			}
			if ((ppdFlag || cpdFlag) && !ppdComiteFlag) {
				query.append(" ,EhfmUsrSpecialityMpg spm ,  EhfCaseTherapy acs1,EhfmUsrProcMpg pmg  ");
			}  if (ptdFlag || ctdFlag) {
				query.append(" ,EhfmUsrSpecialityMpg spm ,  EhfCaseTherapy acs1 ");
			}
			if((chFlag || cexFlag || pexFlag || eoFlag || eocommiteeFlag) && !ctdFlag)
			
			{
				query.append(" ,EhfCaseTherapy ect ");
			}
            if(ppdComiteFlag)
				
			{
				query.append(" ,EhfCaseTherapy sct ");
			}
			   /** Added by ramalakshmi for hubspoke CR
			   */
			  if((casesSearchVO.getDiaFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaFlg())) || (casesSearchVO.getDiaPendFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaPendFlg())))
			  {
				  query.append(",EhfmHospHubSpokeMpg hsm ");
			  }
			  /**
			   * End of hubspoke
			   */
			query.append("  where ap.patientId = ac.casePatientNo   and acb.id.cmbDtlId = ac.caseStatus  and ah.hospId = ac.caseHospCode and ac.nabhHosp is null ");
			if(casesSearchVO.getOldCaseId() != null && !"".equalsIgnoreCase(casesSearchVO.getOldCaseId()) && !"false".equalsIgnoreCase(casesSearchVO.getOldCaseId()))
				query.append(" and ac.caseId='"+casesSearchVO.getOldCaseId()+"' ");
			if (casesSearchVO.getUserRole() != null
					&& !casesSearchVO.getUserRole().equalsIgnoreCase("")
					&& (casesSearchVO.getUserRole().equalsIgnoreCase(config
							.getString("preauth_medco_role"))))
				query.append("  and anu.id.userId = '"
						+ casesSearchVO.getUserId()
						+ "' and anu.id.hospId = ac.caseHospCode and anu.effEndDt is null ");
			if (casesSearchVO.getUserRole() != null
					&& !casesSearchVO.getUserRole().equalsIgnoreCase("")
					&& casesSearchVO.getUserRole().equalsIgnoreCase(
							config.getString("preauth_mithra_role")))
				query.append("  and anu.id.mithraId = '"
						+ casesSearchVO.getUserId()
						+ "' and anu.id.hospId = ac.caseHospCode and anu.endDt is null ");
			
			if(casesSearchVO.getDiaFlg()!=null && "Y".equalsIgnoreCase(casesSearchVO.getDiaFlg()))
		      {
		    	  query.append(" and ac.caseHospCode = hsm.id.spokeHospId and hsm.id.spokeHospId = ah.id.hospId and hsm.id.hubUserId = '"+casesSearchVO.getUserId()+"' ");
		    	  //query1.append(" and anu.id.hospId = ah.id.hospId ");
		      }
			if (casesSearchVO.getCasesForApprovalFlag() != null
					&& casesSearchVO.getCasesForApprovalFlag()
							.equalsIgnoreCase("N")) {
				if (casesSearchVO.getCaseSearchType() != null
						&& !casesSearchVO.getCaseSearchType().equalsIgnoreCase(
								"")) {
					if (!casesSearchVO.getCaseSearchType().equalsIgnoreCase(
							"ChronicOp"))
						query.append(" and ac.caseStatus not in ('"
								+ config.getString("preauth_chronic_op_status")
								+ "'");
					/**
					 * Remove Case Drafted and OP Case Registered from Cases
					 * Search
					 */
					
					if (!casesSearchVO.getCaseSearchType().equalsIgnoreCase(
							"caseOp"))
						query.append(" ,'"
								+ config.getString("CASE.OPCaseRegistered")
								+ "'");
					query.append(")");
				}
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "S".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && (pexFlag || chFlag || cexFlag || eoFlag || eocommiteeFlag))
			{
				query.append("  and ac.caseId=ect.caseId and ect.asriCatCode in ('S19') ");
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "O".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && (pexFlag || chFlag || cexFlag || eoFlag || eocommiteeFlag) && !ctdFlag)
			{
				query.append("  and ac.caseId=ect.caseId and ect.asriCatCode not in ('S19') ");
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "O".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && ((ppdFlag || ptdFlag || ctdFlag || cpdFlag) && !ppdComiteFlag))
			{
				query.append("  and acs1.asriCatCode not in ('S19') ");
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "S".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && ((ppdFlag || ptdFlag || ctdFlag || cpdFlag) && !ppdComiteFlag))
			{
				query.append("  and acs1.asriCatCode  in ('S19') ");
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "S".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && (ppdFlag) )
			{
				query.append("  and '"+ casesSearchVO.getUserId() +"' not in (select actBy from EhfAudit WHERE actId in ('CD1536','CD1537') and id.caseId=ac.caseId) ");
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "S".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && (cpdFlag) )
			{
				query.append("  and '"+ casesSearchVO.getUserId() +"' not in (select actBy from EhfAudit WHERE actId in ('CD1539','CD1540') and id.caseId=ac.caseId) ");
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "S".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && (ppdComiteFlag))
			{
				query.append(" and ac.caseId=sct.caseId and sct.asriCatCode  in ('S19') ");
			}
			if((casesSearchVO.getOrganFlagNew()!=null && "O".equalsIgnoreCase(casesSearchVO.getOrganFlagNew())) && (ppdComiteFlag))
			{
				query.append(" and ac.caseId=sct.caseId  and sct.asriCatCode not in ('S19') ");
			}
			/**
			 * cases for approval appending case status to query
			 */
			  /*if(casesSearchVO.getEnhanceflag()!=null && casesSearchVO.getEnhanceflag().equals("Y"))
			  {
				  query.append("and ac.enhFlag in('Y') ");
			  }
		      else
		      {
		    	  query.append("and ac.enhFlag is  null ");
		      }*/
			if (casesSearchVO.getCasesForApprovalFlag() != null
					&& casesSearchVO.getCasesForApprovalFlag()
							.equalsIgnoreCase("Y")) {

				if (casesSearchVO.getErrCaseApprovalFlag() != null
						&& casesSearchVO.getErrCaseApprovalFlag()
								.equalsIgnoreCase("Y")) {
					query.append(" and nvl(ac.pckAppvAmt,0)+nvl(ac.comorbidAppvAmt,0)+nvl(ac.enhAppvAmt,0)-nvl(ac.csClAmount,0) > 0 ");
					if (casesSearchVO.getUserRole() != null
							&& !casesSearchVO.getUserRole()
									.equalsIgnoreCase("")
							&& (casesSearchVO.getUserRole()
									.equalsIgnoreCase(config
											.getString("preauth_medco_role")))) {
						query.append(" and ac.caseStatus in ('CD51') and (ac.errClaimStatus in ('"
								+ config.getString("EHF.Claims.CHPendErr")
								+ "','"
								+ config.getString("EHF.Claims.CTDPendErr")
								+ "') or ac.errClaimStatus is null)");
					} else if (casesSearchVO.getRoleId() != null
							&& (mappedGroups.contains("~"
									+ config.getString("EHF.Claims.CTD") + "~"))) {
						query.append(" and ac.caseStatus in ('CD51')  and ac.errClaimStatus in ('"
								+ config.getString("EHF.Claims.ErrInit")
								+ "','"
								+ config.getString("EHF.Claims.ErrReInit")
								+ "')");
					} else if (casesSearchVO.getRoleId() != null
							&& (mappedGroups.contains("~"
									+ config.getString("EHF.Claims.CH") + "~"))) {
						query.append(" and ac.caseStatus in ('CD51') and ac.errClaimStatus in ('"
								+ config.getString("EHF.Claims.CTDAppr")
								+ "','"
								+ config.getString("EHF.Claims.chErrReInit")
								+ "')");
					} else if (casesSearchVO.getRoleId() != null
							&& (mappedGroups.contains("~"
									+ config.getString("EHF.Claims.ACO") + "~"))) {
						query.append(" and ac.caseStatus in ('CD51') and ac.errClaimStatus in ('"
								+ config.getString("EHF.Claims.CHAppErr")
								+ "')");
					}
				} else {
					if (!moduleType.equalsIgnoreCase("claim")
							&& casesSearchVO.getUserId() != null
							&& !casesSearchVO.getUserId().equalsIgnoreCase("")
							&& (mappedGroups.contains(config
									.getString("userGroup_eo")))) {
						query.append(" and ((ac.ceoApprovalFlag='Y' and ac.caseStatus in ('CD801','CD16')) or ");
					} else if (moduleType.equalsIgnoreCase("claim")
							&& casesSearchVO.getUserRole() != null
							&& !casesSearchVO.getUserRole()
									.equalsIgnoreCase("")
							&& (casesSearchVO.getUserRole()
									.equalsIgnoreCase(config
											.getString("preauth_medco_role")))) {
						query.append(" and ah.hospActiveYN in ('Y','P','D','E')  ");
					} else {
						query.append(" and ");
					}
					if (caseStatus != null && caseStatus.size() > 0)
						query.append("  ac.caseStatus in ( ");
					
					 int statusCount=0;						  
					for (int k = 0; k < caseStatus.size(); k++) {
						if (k != 0 && k != caseStatus.size()) {
							query.append(" , ");
						}
						query.append(" '" + caseStatus.get(k) + "' ");
						statusCount++;//Added to check atleast one Case Status exists for PPD/CTD
					}
					 if(moduleType!=null && ((ptdFlag && moduleType.equalsIgnoreCase("preauth"))
								|| (ctdFlag && moduleType.equalsIgnoreCase("claim")))
								&& (casesSearchVO.getCaseForDissFlg()==null || (casesSearchVO.getCaseForDissFlg()!=null && !casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")))) 
						 	{
						 		if(statusCount>0)
						 			query.append( " , ");
						 		
						 		query.append( " "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+"   " );
						 	}
					if (caseStatus != null && caseStatus.size() > 0) {
						query.append(" ) ");
						if (!moduleType.equalsIgnoreCase("claim")
								&& casesSearchVO.getUserId() != null
								&& !casesSearchVO.getUserId().equalsIgnoreCase(
										"")
								&& (mappedGroups.contains(config
										.getString("userGroup_eo")))) {
							query.append(" ) ");
						}

					} else
						query.append(" and ac.caseStatus in ('') ");
				}
				/**
				 * check fort ppd login
				 */

			}
			if ((ppdFlag || cpdFlag) && !ppdComiteFlag) {

				query.append("  and spm.id.specialityId = acs1.asriCatCode and pmg.id.procId=acs1.icdProcCode and spm.id.userId=pmg.id.userId and pmg.activeYN='Y'  ");

				if (ppdFlag && cpdFlag)
					{
						query.append(" and spm.id.grpId in('"
									+ config.getString("preauth_ppd_role") + "','"
									+ config.getString("claim_cpd_role") + "' ");
						if(coc_com)
							query.append(" ',"
									+ config.getString("EHF.Claims.COCCMT") + "'");
						
							query.append(" ) ");
					}	
				else if (cpdFlag)
					{
						if(!coc_com)
						query.append(" and spm.id.grpId in('"
							+ config.getString("claim_cpd_role") + "' ");
						if(coc_com)
							query.append(" ',"
									+ config.getString("EHF.Claims.COCCMT") + "'");
						query.append(" ) ");
						query.append(" and (ac.paneldocuserid in ('"+casesSearchVO.getUserId()+"') or ac.paneldocuserid is null)   ");
				  		

					}
				else if (ppdFlag)
					query.append(" and spm.id.grpId in('"
							+ config.getString("preauth_ppd_role") + "') ");

				query.append(" and acs1.caseId = ac.caseId  and spm.id.userId = '"
						+ casesSearchVO.getUserId() + "'  ");
				query.append(" and (ac.ppdGrpFlg ='N' or ac.ppdGrpFlg is null) and spm.activeYN='Y'   ");
			} else if (ptdFlag || ctdFlag) {
				query.append(" and spm.id.specialityId = acs1.asriCatCode  ");
				query.append(" and acs1.caseId=ac.caseId ");
				if (ptdFlag && ctdFlag)
					{
						query.append(" and spm.id.grpId in('"
							+ config.getString("preauth_ptd_role") + "','"
							+ config.getString("EHF.Claims.CTD") + "' ");
						if(coctd)
							query.append(" ',"
									+ config.getString("EHF.Claims.COCTD") + "'");
						query.append(" ) ");
					}	
				else if (ctdFlag)
					{
						if(!coctd)
							query.append(" and spm.id.grpId in('"
								+ config.getString("EHF.Claims.CTD") + "') ");
						if(coctd)
							query.append(" and spm.id.grpId in('"
									+ config.getString("EHF.Claims.COCTD") + "')");
						/*query.append(" ) ");*/
					}	
				else if (ptdFlag)
					query.append(" and spm.id.grpId in('"
							+ config.getString("preauth_ptd_role") + "') ");
				query.append(" and acs1.caseId = ac.caseId  and spm.id.userId = '"
						+ casesSearchVO.getUserId() + "'  ");
				query.append(" and spm.activeYN='Y'   ");
				
				if(moduleType!=null && ((ptdFlag && moduleType.equalsIgnoreCase("preauth"))
						|| (ctdFlag && moduleType.equalsIgnoreCase("claim")))
						&& (casesSearchVO.getCaseForDissFlg()==null || (casesSearchVO.getCaseForDissFlg()!=null && !casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")))) 
					{
						query.append( " and (case when (ac.caseStatus in ( "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+" )   " );
						query.append( "          			and acs1.asriCatCode = '"+config.getString("DentalSurgeryID")+"' and acs1.activeyn = 'Y'  " );
						query.append( "     			) then '1'     " );
						query.append( "           when ((ac.caseStatus not in ( "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+" ) )" );
						query.append( "     			) 	then '1'    " );
						query.append( "     	   else '0' 	 " );
						query.append( "     end) =1     " );
					}
			}

			if (ppdComiteFlag) {
				query.append(" and ac.ppdGrpFlg ='Y' ");
			}
			if (ppdComiteFlag || ppdFlag || cpdFlag) {
				query.append(" and ac.caseHospCode not in ( select  hp.id.hospitalId  from EhfmUsrHospitalMpg hp where  hp.id.userId = '"
						+ casesSearchVO.getUserId()
						+ "'  and hp.activeYN ='Y' ) ");

			}
			
			/*added to separate dental and normal cases for Claim Head & CEX*/
			if(!ctdFlag)
			{
if((chFlag || cexFlag))
{
	query.append(" and ac.caseId=ect.caseId ");
}
if((chFlag || cexFlag) && ("Y").equalsIgnoreCase(dentalFlag))
{
	if((casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201") && (chFlag || cexFlag))
			||(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD202") && (chFlag)))
	query.append(" and ect.asriCatCode  in ('S18')");
}
if((chFlag || cexFlag) && !("Y").equalsIgnoreCase(dentalFlag))
{
	if((casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201") && (chFlag || cexFlag))
			||(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD202") && (chFlag)))
	query.append(" and ect.asriCatCode  not in ('S18')");

}
			}
			
		
			 if(casesSearchVO.getSchemeVal()!=null && !casesSearchVO.getSchemeVal().equalsIgnoreCase("")){
				  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
					
				  query.append(" and ac.schemeId in ('"+casesSearchVO.getSchemeVal()+"') ");
				  else
					  query.append(" and ac.schemeId in ('"+casesSearchVO.getSchemeVal()+"','1') ");
			  }
			  
			 
			/*added for separating cases for JHS and EHS*/
			  if(casesSearchVO.getPatientScheme()!=null && !casesSearchVO.getPatientScheme().equalsIgnoreCase(""))
				{
					query.append(" and ac.patientScheme in ('"
								+ casesSearchVO.getPatientScheme() + "') ");
				}
			  /*end of condition for JHS and EHS*/
			  
			/*if(moduleType.equalsIgnoreCase("claim"))
				query.append(" and (ac.flagged='N' or ac.flagged is null)");*/
			  if(casesSearchVO.getDiaFlg()!=null && casesSearchVO.getDiaFlg().equalsIgnoreCase("Y"))
			 	{
			 		query.append(" order by ac.lstUpdDt asc ");
			 	}
			  else if(moduleType!=null && ("claim").equalsIgnoreCase(moduleType))
			  {
				  query.append(" order by ac.clmSubDt asc ");   
			  }
			  else
			  {
			query.append(" order by ac.caseRegnDate asc ");
			  }
			
			lstCases = session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		retCasesSearchVO.setLstCaseSearch(lstCases);
		return retCasesSearchVO;
	}
	
	private String nextSeqVal(String seqName)
		{
			String nextVal=null;
			StringBuffer query=new StringBuffer();
			query.append(" select to_char("+seqName+".nextval) as CREATEDBY from dual");
			List<CasesSearchVO> resLst=new ArrayList<CasesSearchVO>();
			resLst=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
			
			if(resLst!=null)
				{
					if(resLst.size()>0)
						{
							if(resLst.get(0)!=null)
								{
									if(resLst.get(0).getCREATEDBY()!=null)
										nextVal=resLst.get(0).getCREATEDBY();
								}
						}
				}
			
			return nextVal;
			
		}
	/**
	 * Added by ramalakshmi for cases search CSV
	 */
	@Override
	public CasesSearchVO getCasesSearchCSV(CasesSearchVO casesSearchVO) 
	{

		String flg="N";
		String organFlg="N";
			String acoCond="N";
			List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
			List<CasesSearchVO> lstFinalCases = new ArrayList<CasesSearchVO>();	
			CasesSearchVO retCasesSearchVO = new CasesSearchVO();
			StringBuffer query = new StringBuffer();
			StringBuffer query1 = new StringBuffer();
			List<String>  caseStatus = new ArrayList<String>();
			String fromDate;
			String sqlFromDate;
			String toDate;
			String sqlToDate;
			String ceoFlag=casesSearchVO.getCeoFlag();
			String dentalFlag=casesSearchVO.getDentalFlag();
			
			String database=config.getString("Database");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
			SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
			 SessionFactory factory= hibernateTemplate.getSessionFactory();
			  Session session=factory.openSession();
			  int startIndex=Integer.parseInt(casesSearchVO.getStartIndex());
			  int maxResult = (Integer.parseInt(casesSearchVO.getRowsPerPage()));
			   boolean ppdFlag = false; boolean ptdFlag=false;
			  boolean ppdComiteFlag = false; boolean ctdFlag=false;
			  boolean cpdFlag= false;
			  boolean cexFlag=false;
			  String moduleType = casesSearchVO.getModule();
			  String pendingFlag=casesSearchVO.getPendingFlag();
			  /**
			   * To get the groups to which the user is mapped
			   */
			  if(moduleType!=null && !moduleType.equalsIgnoreCase(""))
			  {
			  if(moduleType.contains("preauth"))
				{
					moduleType="preauth";
				}
				if(moduleType.contains("claim"))
				{
					moduleType="claim";
				}
				if(moduleType.contains("cochlearAco"))
				{
					moduleType="cochlearAco";
				}
				
			  }
			  if(moduleType==null || moduleType.equalsIgnoreCase(""))
				  moduleType="common";
			  
			  String mappedGroups="~";
			  try{
				 if(casesSearchVO.getShowPage() != null && Integer.parseInt(casesSearchVO.getShowPage()) > 1 )
				 {
					 startIndex =  Integer.parseInt(casesSearchVO.getRowsPerPage()) * (Integer.parseInt(casesSearchVO.getShowPage())-1);
				 }			 
				 
				 /**
				  * get cases status for approval
				  */
				 for(LabelBean labelBean:casesSearchVO.getGrpList())
	 		    {
					 
					 
					 
					 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP17")||labelBean.getID() != null &&   labelBean.getID().equals( "GP2")
							 ||labelBean.getID() != null &&   labelBean.getID().equals( "GP1")||labelBean.getID() != null &&   labelBean.getID().equals( "GP97"))
						 acoCond="Y";
					 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP71")||labelBean.getID() != null &&   labelBean.getID().equals( "GP72")||
							 labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))//For cochlear groups
						 flg="Y";
					 if( labelBean.getID() !=null && labelBean.getID().equals("GP1000") || labelBean.getID() !=null && labelBean.getID().equals("GP1001")||
							 labelBean.getID() != null &&   labelBean.getID().equals( "GP1002"))//For organ transplant groups
						 organFlg="Y";
					 String caseSts = "";
					 if(casesSearchVO.getCaseForDissFlg()!=null && casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")){
						 if(labelBean.getID() != null &&   labelBean.getID().equals( "GP8"))
							 caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
						 else if (labelBean.getID() != null &&   labelBean.getID().equals( "GP9"))
							 caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
						 else if (labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))
							 {
							 	caseSts =  config.getString(moduleType+"_caseStatus_DIS_"+labelBean.getID());
							 	//flg="Y";
							 	
							 }
					 }
					 else{
	 		     caseSts =  config.getString(moduleType+"_caseStatus_"+labelBean.getID());
	 		     	if(labelBean.getID() != null &&   labelBean.getID().equals( "GP73"))
	 		     		{
	 		     			//if(caseSts.contains("CD149"))
	 		     				//flg="Y";
	 		     		}
					 }
	 		     if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ppd_role")) && !ppdFlag )
	 		     {
	 		    	ppdFlag = true; 
	 		     }
	 		    if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("claim_cpd_role")) && !cpdFlag )
			     {
	 		    	cpdFlag = true; 
			     }
	 		    if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ppdGrp_role")) && !ppdComiteFlag )
			     {
	 		    	ppdComiteFlag = true; 
			     } 
	             if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("preauth_ptd_role")) && !ppdFlag && !cpdFlag && !ptdFlag)
			     {
	 			  ptdFlag = true; 
			     }
	 		  if(caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("EHF.Claims.CTD")) && !ppdFlag && !cpdFlag && !ctdFlag)
			     {
				  ctdFlag = true; 
			     }	
	 		 if (caseSts != null && labelBean.getID() != null && labelBean.getID().equals(config.getString("EHF.Claims.CEX"))
						&& !ppdFlag && !cpdFlag && !ptdFlag) {
					cexFlag = true;
				}
	 		     if(caseSts != null && !caseSts.equalsIgnoreCase(""))
	 		     {
	 		     StringTokenizer str = new StringTokenizer(caseSts,"~");
	 			  while(str.hasMoreTokens())
	 			  {
	 				 caseStatus.add(str.nextElement().toString());  
	 			  }
	 			   
	 		     }
	 		     
	 		    mappedGroups=mappedGroups+labelBean.getID()+"~";
	 		    }
				 /**
				 * set view flag as N for Cases blocked by this user
				 */
			 	List<GenericDAOQueryCriteria>  criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			 	criteriaList.add(new GenericDAOQueryCriteria("caseBlockedUsr",casesSearchVO.getUserId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 	criteriaList.add(new GenericDAOQueryCriteria("viewFlag","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 	List<EhfCase> blockedCaseList=genericDao.findAllByCriteria(EhfCase.class,criteriaList);
			 	for(EhfCase ehfCase:blockedCaseList)
				{
					ehfCase.setViewFlag("N");
					ehfCase.setCaseBlockedUsr(casesSearchVO.getUserId());
					ehfCase.setCase_blocked_dt(new java.sql.Timestamp(new Date().getTime()));
					genericDao.save(ehfCase);
				}
				 /**
				  * end of getting status for cases for approval
				  */
				  query.append("  select distinct ac.case_Id as CASEID,ac.case_No as CASENO ,ac.flagged as FLAGGED,ap.name as PATIENTNAME ,  ap.card_No as WAPNO , acb.cmb_Dtl_Name as CLAIMSTATUS ,ah.hosp_Name as HOSPNAME,ah.hosp_Id as HOSPID,ac.scheme_Id as SCHEMEID,ac.case_Patient_No as CASEPATIENTNO  ");
				  query.append(" , ac.case_Status as CASESTATUS , ac.lst_Upd_Dt  as LSTUPDDT,ac.err_clm_status as ERRSTATUSID, ap.patient_Id as PATIENTID, ac.pending_Flag as PENDINGFLAG,ac.preauth_Pckg_Amt as PREAUTHPCKGAMT,ac.enh_Flag as ENHFLAG,ac.clm_Sub_Dt as SUBMITTEDDATE,ac.case_Regn_Date as CASEREGNDATE  ");
				  if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase(""))
				  	{
					  query.append(" ,	to_char(eccm.TDS_PCT_AMT) as TDSPCTAMT, to_char(eccm.TRUST_PCT_AMT) as TRUSTPCTAMT ,to_char(eccm.HOSP_PCT_AMT) as HOSPPCTAMT ,to_char(eccm.TDS_HOSP_PCT_AMT) as TDSHOSPPCTAMT ");
				  	} 
				  if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp"))
				  query.append("  , ap.patient_Ipop as PATIPOP, ap.intimation_Id as TELESTATUS");
				  //if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
				  query.append(" ,ac.view_Flag as VIEWFLAG, ac.case_Blocked_Usr as CASEBLOCKEDUSR ");
				  //}
				  query.append("  ,ac.case_Regn_Date as INPATIENTCASESUBDT , nvl(ac.cs_Cl_Amount,0) as CLAIMAMT,  nvl(eccm.claim_bill_amt,0) as CLAIMINITAMT ,ac.claim_No as CLAIMNO, ac.grievance_Flag as GRIEVANCEFLAG "); 
				  if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
					  query.append("  ,ac.cs_Preauth_Dt as CSPREAUTHDT,ac.preauth_aprv_dt as PREAUTHAPRVDT,ac.CS_DIS_DT as CSDISDT,ac.cs_Death_Dt as CSDEATHDT,ac.CLM_SUB_DT as CLMSUBDT,ac.cs_trans_id as CSTRANSID, ac.cs_trans_dt as CSTRANSDT,ac.cs_remarks as CSREMARKS,ac.cs_sbh_dt as CSSBHDT");
					  
					  if(casesSearchVO!=null && casesSearchVO.getTotRowCount()!=null && !"".equalsIgnoreCase(casesSearchVO.getTotRowCount()))
					  {
						  maxResult =   Integer.parseInt(casesSearchVO.getTotRowCount());
					  }
				  }
				 /* if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
				  {
					  query.append(" ,em.procName as procName ");
				  }*/
				  
				  query1.append("   from ehfm_cmb_dtls acb,  EHFM_HOSPITALS ah  ");
				 
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))
					  query1.append(",    ehfm_medco_dtls anu ");
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
					  query1.append(",    Ehfm_Hosp_Mithra_Dtls anu "); 
				  if(casesSearchVO.getCaseSearchType()!=null && !casesSearchVO.getCaseSearchType().equalsIgnoreCase("") && casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
					  query1.append(" ,  Ehf_Patient ap	,Ehf_Chronic_Case ac ");
					  
					  /*if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
						  query1.append(" left join ac.EhfChronicCaseClaim eccm 	");
					  }*/
				  }
				  
				  
				  else
				  {
	            	  query1.append(" ,   Ehf_Patient ap , 	Ehf_Case ac "); //EhfCasePatient ap ,
						if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
											  query1.append(" ,ehf_Case_Claim eccm  ");
										  }
				  }
				if((ppdFlag ||cpdFlag) && !ppdComiteFlag )
				{
						  query1.append(" ,EHFM_USR_speciality_MPG spm ,  Ehf_Case_Therapy acs1,Ehfm_Usr_Proc_Mpg pmg ");	
				}else if(ptdFlag || ctdFlag){
					query1.append(" ,EHFM_USR_speciality_MPG spm ,  Ehf_Case_Therapy acs1 ");
				}
				if(cexFlag && casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				{
					query1.append(" ,Ehf_Case_Therapy ect ");
				}
				
				if(casesSearchVO.getSearchFlag() != null && casesSearchVO.getSearchFlag().equalsIgnoreCase("Y"))
				 {
				if((casesSearchVO.getMainCatName() != null && !casesSearchVO.getMainCatName().equals("") && !casesSearchVO.getMainCatName().equals("-1")) || (casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1")))
			    {
				  query1.append("  , Ehf_Case_Therapy acs ,  EHFM_MAIN_CATEGORY emc ");  
				  if(casesSearchVO.getProcName() != null && !casesSearchVO.getProcName().equals("") && !casesSearchVO.getProcName().equals("-1"))
				    {
					  query1.append("   ,  EHFM_MAIN_THERAPY etp ");  
				    }
			    }
				 }
				/*if(cpdFlag){
					query1.append(" ,EhfmUsrSpecialityMpg spm ,  EhfCaseTherapy acs1  ");	
				}
				 if(ppdComiteFlag || ppdFlag)
			      {
					 query1.append(" , EhfmUsrHospitalMpg hp "); 
			      }*/
				  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
				  {
					  query1.append("  , Ehf_Case_Therapy acs  ");  
				  }
				  
				  /* added by satish kola for erroneous claim cases*/
			      if((casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) || (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y"))){
					 
					   if(casesSearchVO.getRoleId()!=null && ((mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))||(mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~")) || (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~")) )){
							query1.append(" ,EHF_ERRONEOUS_CLAIM eec " );
					   }
				  }
			      
			     
				 /* if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
				  {
					  query1.append("   ,EhfCaseTherapy ecc,EhfmTherapyProcMst em ");
				  }*/
				  
				  
				  /*if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
				  {
					  query1.append("  , EhfmUsersUnitDtls eud  ");  
				  }
				  */
				  if(casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" ,Ehf_Case_Therapy ct "); 
					  }
				  }
				  if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" ,Ehf_Case_Therapy ct "); 
					  }
				  }
				  query1.append("  where ap.patient_Id = ac.case_Patient_No   and acb.cmb_Dtl_Id = ac.case_Status  and ah.hosp_Id = ac.case_Hosp_Code  and ac.nabh_Hosp is null ");
				  /*if(casesSearchVO.getCaseSearchType()==null || casesSearchVO.getCaseSearchType().equalsIgnoreCase("") || !casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp")){
					  query1.append(" and ap1.patientId = ac.casePatientNo and ap.patientId= ap1.patientId ");
				  }*/
				  if(casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" and ct.case_Id=ac.case_Id and ct.asri_Cat_Code in ('"+ config.getString("DentalSurgeryID") +"') and ct.active_yn='Y' "); 
					  }
				  }
				  if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")){
					  if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
						  query1.append(" and ct.case_Id=ac.case_Id and ct.asri_Cat_Code not in ('"+ config.getString("DentalSurgeryID") +"') "); 
					  }
				  }
				  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
				  {
					  query1.append(" and acs.case_Id = ac.case_Id ");
					//  query1.append("  and adm.disCatId = acs.diaCatId  "); 
					 
					
				  }
				  
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
				    query1.append("  and anu.medco_Id = '"+casesSearchVO.getUserId()+"' and anu.hosp_Id = ac.case_Hosp_Code and anu.End_Dt is null "); 
				  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
					  query1.append("  and anu.mithra_Id = '"+casesSearchVO.getUserId()+"' and anu.hosp_Id = ac.case_Hosp_Code and anu.end_Dt is null ");
				  
				  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("N"))
					 {
				  if(casesSearchVO.getCaseSearchType()!=null && !casesSearchVO.getCaseSearchType().equalsIgnoreCase("") )
				  {
					  if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("ChronicOp"))
						  query1.append(" and ac.case_Status not in ('"+config.getString("preauth_chronic_op_status")+"'");
					  /**
						 * Remove Case Drafted and OP Case Registered from Cases Search
						 */
					  if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("caseOp"))
						  query1.append(" ,'"+config.getString("CASE.OPCaseRegistered")+"'");
					  /*if(!casesSearchVO.getCaseSearchType().equalsIgnoreCase("caseDrafted"))
						  query1.append(" ,'"+config.getString("CASE.CaseDrafted")+"'");*/
					  query1.append(")");
				  }
					 }
				/**
				 * cases for approval appending case status to query
				 */
				  
				  if(casesSearchVO.getCasesForApprovalFlag() != null && casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y")&& !("Y").equalsIgnoreCase(pendingFlag))
					 {
					  
				      if(casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")){
						  query1.append(" and nvl(ac.pck_Appv_Amt,0)+nvl(ac.comorbid_Appv_Amt,0)+nvl(ac.enh_Appv_Amt,0)-nvl(ac.cs_Cl_Amount,0) > 0 ");
						  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))){  
							  query1.append(" and ac.case_Status in ('CD51') and (ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.CHPendErr") +"','"+config.getString("EHF.Claims.CTDPendErr") +"') or ac.err_Clm_Status is null)" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))){
								query1.append(" and ac.case_Status in ('CD51') and spm.speciality_Id not in ('"+ config.getString("DentalSurgeryID") +"') and ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.ErrInit") +"','"+config.getString("EHF.Claims.ErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
								query1.append(" and ac.case_Status in ('CD51') and ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.CTDAppr") +"','"+config.getString("EHF.Claims.chErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~"))){
								query1.append(" and ac.case_Status in ('CD51') and ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.CHAppErr") +"')" );
							}
					  }
				      /* added by satish kola for erroneous dental claim cases*/
				      else if(casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")){
						  query1.append(" and nvl(ac.pck_Appv_Amt,0)+nvl(ac.comorbid_Appv_Amt,0)+nvl(ac.enh_Appv_Amt,0)-nvl(ac.cs_Cl_Amount,0) > 0 ");
						  if(casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))){  
							  query1.append(" and ac.case_Status in ('CD51') and (ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.CHPendErr") +"','"+config.getString("EHF.Claims.CTDPendErr") +"') or ac.err_Clm_Status is null)" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))){
								query1.append(" and ac.case_Status in ('CD51') and spm.speciality_Id in ('"+ config.getString("DentalSurgeryID") +"') and ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.ErrInit") +"','"+config.getString("EHF.Claims.ErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~"))){
								query1.append(" and ac.case_Status in ('CD51') and ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.CTDAppr") +"','"+config.getString("EHF.Claims.chErrReInit") +"')" );
							}
							else if(casesSearchVO.getRoleId()!=null && (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~"))){
								query1.append(" and ac.case_Status in ('CD51') and ac.err_Clm_Status in ('"+ config.getString("EHF.Claims.CHAppErr") +"')" );
							}
					  }
					  else{
						  if(!moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserId()!=null && !casesSearchVO.getUserId().equalsIgnoreCase("") && (mappedGroups.contains(config.getString("userGroup_eo"))))
						  {
							  query1.append(" and ((ac.ceo_Approval_Flag='Y' and ac.case_Status in ('CD801','CD16')) or ");	  
						  }
						  else if(moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserRole()!=null && !casesSearchVO.getUserRole().equalsIgnoreCase("") && (casesSearchVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
						  {
							  query1.append(" and ah.hosp_Active_YN in ('Y','P','D','E','S','C','CB','SP','CP','CBP') and ");
						  }
						  else if(moduleType.equalsIgnoreCase("cochlearAco"))
						  {
							 
							  query1.append(" and  ac.case_Status='CD425' ");
						  }
						  else 
						  {
							  query1.append(" and ");
						  }
						  if(caseStatus != null && caseStatus.size() >0)
							  query1.append("  ac.case_Status in ( "); 
						  int statusCount=0;
						 for(int k=0; k<caseStatus.size() ;k++)
						 {
						   if(k!=0 && k!=caseStatus.size())
						     {
							   query1.append(" , ");  
						     }
						   query1.append(" '"+caseStatus.get(k)+"' ");
						   statusCount++;//Added to check atleast one Case Status exists for PPD/CTD
						 }
						 if(moduleType!=null && ((ptdFlag && moduleType.equalsIgnoreCase("preauth"))
									|| (ctdFlag && moduleType.equalsIgnoreCase("claim")))
									&& (casesSearchVO.getCaseForDissFlg()==null || (casesSearchVO.getCaseForDissFlg()!=null && !casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")))) 
							 	{
							 		if(statusCount>0)
							 			query1.append( " , ");
							 		
							 		query1.append( " "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+"   " );
							 	}
						 
						  if(caseStatus != null && caseStatus.size() >0)
						  {
						   query1.append(" ) ");  
						  if(!moduleType.equalsIgnoreCase("claim") && casesSearchVO.getUserId()!=null && !casesSearchVO.getUserId().equalsIgnoreCase("") && (mappedGroups.contains(config.getString("userGroup_eo"))))
							  {
							   query1.append(" ) ");  
							  }
						  
						  }
						  else if(moduleType.equalsIgnoreCase("cochlearAco"))
						  {
							  query1.append(" ");
						  }
							  else
								  query1.append("  ac.case_Status in ('') ");
					  }
				      /**
				       * check fort ppd login
				       */
				      
					 }
				  if((ppdFlag||cpdFlag) && !ppdComiteFlag)
				  		{
				  		query1.append("  and spm.speciality_Id = acs1.asri_Cat_Code and pmg.procedure_Id=acs1.icd_Proc_Code and spm.user_Id=pmg.user_Id and pmg.active_YN='Y' "); 
					  		
					  		if(ppdFlag && cpdFlag)
					  			query1.append(" and spm.grp_Id in('"+config.getString("preauth_ppd_role")+"','"+config.getString("claim_cpd_role")+"') ");
					  		else if(cpdFlag)
					  			query1.append(" and spm.grp_Id in('"+config.getString("claim_cpd_role")+"') ");
					  		else if(ppdFlag)
					  			query1.append(" and spm.grp_Id in('"+config.getString("preauth_ppd_role")+"') " );
					  		query1.append(" and (ac.panel_doc_user_id in ('"+casesSearchVO.getUserId()+"') or ac.panel_doc_user_id is null)   ");
					  		query1.append(" and acs1.case_Id = ac.case_Id  and spm.user_Id = '"+casesSearchVO.getUserId()+"'  ");
					  		query1.append(" and (ac.ppd_Grp_Flg ='N' or ac.ppd_Grp_Flg is null) and spm.activeYN='Y'   ");
				  		}
				  else if(ptdFlag || ctdFlag)
				  		{
							query1.append(" and spm.speciality_Id = acs1.asri_Cat_Code  ");
							if(ptdFlag && ctdFlag)
								query1.append(" and spm.grp_Id in('"+config.getString("preauth_ptd_role")+"','"+config.getString("EHF.Claims.CTD")+"') ");
							else if(ctdFlag)
								query1.append(" and spm.grp_Id in('"+config.getString("EHF.Claims.CTD")+"') ");
							else if(ptdFlag)
								query1.append(" and spm.grp_Id in('"+config.getString("preauth_ptd_role")+"') " );
							query1.append(" and acs1.case_Id = ac.case_Id  and spm.user_Id = '"+casesSearchVO.getUserId()+"'  ");
							query1.append(" and spm.activeYN='Y'   ");
							
							if(moduleType!=null && ((ptdFlag && moduleType.equalsIgnoreCase("preauth"))
									|| (ctdFlag && moduleType.equalsIgnoreCase("claim")))
									&& (casesSearchVO.getCaseForDissFlg()==null || (casesSearchVO.getCaseForDissFlg()!=null && !casesSearchVO.getCaseForDissFlg().equalsIgnoreCase("Y")))) 
								{
									query1.append( " and (case when (ac.case_Status in ( "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+" )   " );
									query1.append( "          			and acs1.asri_Cat_Code = '"+config.getString("DentalSurgeryID")+"' and acs1.activeyn = 'Y'  " );
									query1.append( "     			) then '1'     " );
									query1.append( "           when ((ac.case_Status not in ( "+config.getString(moduleType+"_panelDocStatus").replace("~", ",")+" ) )" );
									query1.append( "     			) 	then '1'    " );
									query1.append( "     	   else '0' 	 " );
									query1.append( "     end) =1     " );
								}
				  		}
				  /*if(cpdFlag){
					  query1.append(" and spm.id.grpId = '"+config.getString(moduleType+"_cpd_role")+"' and spm.id.specialityId = acs1.asriCatCode  ");
			    	  query1.append(" and acs1.caseId = ac.caseId  and spm.id.userId = '"+casesSearchVO.getUserId()+"'  ");
					  query1.append(" and spm.activeYN='Y'   "); //and   ac.ppdGrpFlg ='N'
				  }*/
			      if(ppdComiteFlag)  
			      {
			    	  query1.append(" and ac.ppd_Grp_Flg ='Y' " );	  
			      }
			      if(ppdComiteFlag || ppdFlag || cpdFlag)
			      {
			    	  query1.append(" and ac.case_Hosp_Code not in ( select  hp.hospital_Id  from Ehfm_Usr_Hospital_Mpg hp where  hp.user_Id = '"+casesSearchVO.getUserId()+"'  and hp.activeYN ='Y' ) " );	  
			      }
			      /*if(casesSearchVO.getEnhanceflag()!=null &&  casesSearchVO.getEnhanceflag().equals("Y"))
				  {
					  query1.append("and ac.enhFlag in('Y') ");
				  }
			      else
			      {
			    	  query1.append("and ac.enhFlag is  null ");
			      }*/
				  if(casesSearchVO.getSearchFlag() != null && casesSearchVO.getSearchFlag().equalsIgnoreCase("Y"))
				 {				 			
					  
					 if(casesSearchVO.getClaimStatus() != null && !casesSearchVO.getClaimStatus().equals(""))
					  {
						  query1.append("  and ac.case_Status = '"+casesSearchVO.getClaimStatus()+"' ");
					  }
					 if(casesSearchVO.getWapNo() != null && !casesSearchVO.getWapNo().equals(""))
					  {
						 query1.append("  and upper(ap.card_No) like  upper('%"+casesSearchVO.getWapNo().trim()+"%') "); 
					  }
					 if(casesSearchVO.getCaseNo() != null && !casesSearchVO.getCaseNo().equals(""))
					  {
						 query1.append("  and upper(ac.case_Id) like  upper('"+casesSearchVO.getCaseNo().trim()+"') "); 
					  }
					 if(casesSearchVO.getClaimNo() != null && !casesSearchVO.getClaimNo().equals(""))
					  {
						 query1.append("  and upper(ac.claim_No) like  upper('%"+casesSearchVO.getClaimNo().trim()+"%') "); 
					  }
					 if(casesSearchVO.getPatName() != null && !casesSearchVO.getPatName().equals(""))
					  {
						 query1.append("  and upper(ap.name) like  upper('%"+casesSearchVO.getPatName().trim()+"%') "); 
					  }
					 if(casesSearchVO.getCatId() != null && !casesSearchVO.getCatId().equals(""))
					  {
						 query1.append("  and upper(ac.cs_Dis_Main_Code) like  upper('%"+casesSearchVO.getCatId().trim()+"%') "); 
					  }
					 if(casesSearchVO.getErrStatusId() != null && !casesSearchVO.getErrStatusId().equals(""))
					  {
						 query1.append("  and upper(ac.err_Clm_Status) like  upper('%"+casesSearchVO.getErrStatusId().trim()+"%') "); 
					  }
					  if(casesSearchVO.getSurgyId() != null && !casesSearchVO.getSurgyId().equals(""))
					  {
						 query1.append("  and upper(acs.case_therapy_Id) like  upper('%"+casesSearchVO.getSurgyId().trim()+"%') "); 
					  }
					  if(casesSearchVO.getHospId() != null && !casesSearchVO.getHospId().equals(""))
					  {
						 query1.append("  and ac.case_Hosp_Code='"+casesSearchVO.getHospId().trim()+"' "); 
					  }
					  if((casesSearchVO.getMainCatName() != null && !casesSearchVO.getMainCatName().equals("") && !casesSearchVO.getMainCatName().equals("-1")) || (casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1")))
				      {
						  query1.append("  and acs.case_Id= ac.case_Id and acs.asri_Cat_Code= '"+casesSearchVO.getMainCatName()+"' and emc.asri_Cat_Code= acs.asri_Cat_Code and acs.activeyn='Y' "); 
						  if(casesSearchVO.getCatName() != null && !casesSearchVO.getCatName().equals("") && !casesSearchVO.getCatName().equals("-1"))
							  query1.append(" and acs.icd_Cat_Code= '"+casesSearchVO.getCatName()+"' and emc.icd_Cat_Code= acs.icd_Cat_Code ");  
						  if(casesSearchVO.getProcName() != null && !casesSearchVO.getProcName().equals("") && !casesSearchVO.getProcName().equals("-1"))
					      {
							  query1.append("  and acs.icd_Proc_Code='"+casesSearchVO.getProcName()+"' and etp.icd_Proc_Code= acs.icd_Proc_Code  ");  
					      }
				      }
					 
					  if(casesSearchVO.getFromDate()!=null && !casesSearchVO.getFromDate().equals("") && casesSearchVO.getToDate()!=null && !casesSearchVO.getToDate().equals(""))
					  { 
							fromDate=casesSearchVO.getFromDate();
							sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
							toDate=casesSearchVO.getToDate().toString();
							
							//To include todate in search criteria--adding date+1 
							Calendar cal = Calendar.getInstance();  
				        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
				        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
				        	Date tomorrow = cal.getTime();  
				        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
				        	 //End of date+1 
							 
							sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
							
							if(database.equalsIgnoreCase("ORACLE"))
								query1.append("and ac.case_Regn_Date between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
							else if(database.equalsIgnoreCase("MYSQL"))
								query1.append("and ac.case_Regn_Date between '"+sqlFromDate+"' and '"+sqlToDate+"'");
						}
					  if(casesSearchVO.getTelephonicId() != null && !casesSearchVO.getTelephonicId().equals(""))
					  {
						 query1.append("  and ap.intimation_Id='"+casesSearchVO.getTelephonicId().trim()+"' "); 
					  }
				 }
				  
					/*added to separate dental and normal cases for  CEX*/
				  if(cexFlag&& casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ac.case_Id=ect.cas_eId ");
				  }
				  if(cexFlag && ("Y").equalsIgnoreCase(dentalFlag)&& casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ect.asri_Cat_Code  in ('S18')");
				  }
				  if(cexFlag && !("Y").equalsIgnoreCase(dentalFlag) && casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
				  {
				  	query1.append(" and ect.asri_Cat_Code  not in ('S18')");
				  }
				  
				  if((casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) || (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y"))){
						 
					   if(casesSearchVO.getRoleId()!=null && ((mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))||(mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~")) || (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~")) )){
							query1.append(" and ac.case_Id=eec.case_Id " );
					   }
				  }
				  if((casesSearchVO.getSchemeVal()!=null && !casesSearchVO.getSchemeVal().equalsIgnoreCase("")) && ((casesSearchVO.getErrCaseApprovalFlag()!=null && !casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) && (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && !casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y")))){
					  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD202"))
						  query1.append(" and ac.scheme_Id in ('CD202') ");
					  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD203"))
					  query1.append(" and ac.scheme_Id in ('CD201','CD202','1') ");
					  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
					  query1.append(" and ac.scheme_Id in ('"+casesSearchVO.getSchemeVal()+"') ");
					 // else
						//  query1.append(" and ac.schemeId in ('"+casesSearchVO.getSchemeVal()+"','1') ");
				  }
				  else if((casesSearchVO.getErrCaseApprovalFlag()!=null && casesSearchVO.getErrCaseApprovalFlag().equalsIgnoreCase("Y")) || (casesSearchVO.getErrDentalCaseApprovalFlag()!=null && casesSearchVO.getErrDentalCaseApprovalFlag().equalsIgnoreCase("Y"))){
						 
					   if(casesSearchVO.getRoleId()!=null && ((mappedGroups.contains("~"+config.getString("EHF.Claims.CTD")+"~"))||(mappedGroups.contains("~"+config.getString("EHF.Claims.CH")+"~")) || (mappedGroups.contains("~"+config.getString("EHF.Claims.ACO")+"~")) )){
						   if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD202"))
								  query1.append(" and eec.scheme_Id in ('CD202') ");
							  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD203"))
							  query1.append(" and eec.scheme_Id in ('CD201','CD202','1') ");
							  if(casesSearchVO.getSchemeVal().equalsIgnoreCase("CD201"))
							  query1.append(" and eec.scheme_Id in ('"+casesSearchVO.getSchemeVal()+"') ");
					   }
				  } 
				  
				/*  
				  if(casesSearchVO!=null && ("Y").equalsIgnoreCase(casesSearchVO.getCeoFlag()))
				  {
					  query1.append("    and ac.caseId=ecc.caseId and ecc.icdProcCode=em.id.icdProcCode and em.id.state=ac.schemeId ");
				  }*/
				  
				
				  
				  if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
				  {
					  
					  String[] claimStatus=null;
					  if(("claim").equalsIgnoreCase(moduleType))
					  claimStatus =  ClaimsConstants.claimsCEOSentBackStatuses;
					  else if(("preauth").equalsIgnoreCase(moduleType) && !("Y").equalsIgnoreCase(dentalFlag))
					  claimStatus =  ClaimsConstants.preauthCEOSentBackStatuses;
					  else if(("Y").equalsIgnoreCase(dentalFlag))
					  claimStatus =  ClaimsConstants.preauthCEODentalSentBackStatuses;  
						  
						
					  query1.append(" and ac.caseStatus in ( ");
					  for (int i = 0; i < claimStatus.length; i++) {
							query1.append(" '" + claimStatus[i] + "' ");
							if (i != claimStatus.length - 1)
								query1.append(",");
					  }
							query1.append(" ) ");
					  
					  
					  query1.append("   and ac.pending_With in ('"+casesSearchVO.getUserId()+"') ");  
				 
				  }
				  
					/*added for separating cases for JHS and EHS*/
				  if(casesSearchVO.getPatientScheme()!=null && !casesSearchVO.getPatientScheme().equalsIgnoreCase(""))
					{
						query1.append(" and ac.patient_Scheme in ('"
									+ casesSearchVO.getPatientScheme() + "') ");
					}
				  /*end of condition for JHS and EHS*/
				  
				 if(flg!=null && casesSearchVO.getCasesForApprovalFlag()!=null && !("Y").equalsIgnoreCase(pendingFlag) )
				  {
					  if(casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
					  
					 {
					  if(!acoCond.contains("Y"))
							 {
						  		if(flg.equalsIgnoreCase("Y"))
							 		query1.append(" and ac.cochlear_YN='Y' ");
							 	/*else if(flg.equalsIgnoreCase("N"))
							 		query1.append(" and ac.cochlearYN is null ");*/
						 	}
					  
					 }}
				 if(organFlg!=null && casesSearchVO.getCasesForApprovalFlag()!=null && !("Y").equalsIgnoreCase(pendingFlag) )
				  {
					  if(casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
					  
						 {
						  if(!acoCond.contains("Y"))
								 {
							  		if(organFlg.equalsIgnoreCase("Y"))
								 		query1.append(" and ac.organ_Trans_YN='Y' ");
								 	/*else if(flg.equalsIgnoreCase("N"))
								 		query1.append(" and ac.cochlearYN is null ");*/
							 	}
						  
						 }
					 }
				 if(casesSearchVO.getExcelFlag()!=null && !casesSearchVO.getExcelFlag().equalsIgnoreCase("")){
					query1.append(" and ac.case_Id = eccm.case_Id(+) "); 
				 }
				 
				  if(!casesSearchVO.getCasesForApprovalFlag().equalsIgnoreCase("Y")){
				  query1.append(" order by ac.case_Regn_Date desc ");} 
				  else
				  {
				  if(moduleType!=null && ("claim").equalsIgnoreCase(moduleType))
				  {
					  query1.append(" order by ac.clm_Sub_Dt asc ");   
				  }
				  else
				  {
				  query1.append(" order by ac.case_Regn_Date asc "); 
				  }
				  }
				  
				
				  query.append(query1);
				 
				  lstCases=session.createSQLQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();
		        }
				  catch(Exception e)
			  {
				e.printStackTrace();  
			  }
				  finally {
						session.close();
						factory.close();
					}
			  retCasesSearchVO.setLstCaseSearch(lstCases);
			
			return retCasesSearchVO;		
	}
	/**
     * End of cases search CSV Download
     */
	@Override
	public List<CasesSearchVO> getPreauthApprovedCases(String empPenId,String healthCardNum) 
	{
		List<CasesSearchVO> empDetailsList = null;
		StringBuffer query = new StringBuffer();
		try {
			query.append(" select EC.CASE_ID as CASEID,EP.NAME as PNAME,EP.PATIENT_ID AS PATIENTID  ,erm.relation_name as RELATION,  EP.CARD_NO as CARDNO, EP.EMPLOYEE_NO as EMPLOYEENO,    ");    
			query.append(" eh.hosp_name as HOSPNAME, WM_CONCAT(DISTINCT sp.dis_main_name) as CATNAME, WM_CONCAT(DISTINCT mt.proc_name) as PROCDURENAME, ");
			query.append(" to_Char(t.ppd_apprv_date, 'dd/mm/yyyy') as PREAUTHDATE,  to_Char( sum(t.pck_appv_amt)) as PREAUTHAMT ");
			query.append(" from ehf_patient ep, ehf_case ec, ehfm_hospitals eh, ehf_case_therapy ect, ehfm_main_therapy mt, ehfm_specialities sp, ");
			query.append(" ehfm_relation_mst erm,(select ec.case_id,ECP.PTD_APPV_TOTAL_PACKAGE_AMT / count(ec.case_id) pck_appv_amt, ");
			query.append(" ecp.ppd_apprv_date  from ehf_patient  ep,ehf_case ec, ehf_case_therapy ect, ehf_case_preauth_amounts  ecp  where ep.patient_id = ec.case_patient_no ");
			query.append(" and ec.scheme_id in ('CD202') and EC.CASE_ID = ECP.CASE_ID  and ec.preaUth_aprv_dt is not null and ec.preauth_rej_dt is null ");
			query.append(" and ec.preauth_cancel_dt is null  and ec.case_id = ect.case_id  and ect.activeyn = 'Y' group by ec.case_id, ECP.PTD_APPV_TOTAL_PACKAGE_AMT, ");
			query.append(" ecp.ppd_apprv_date) t ");

			query.append(" where ep.patient_id = ec.case_patient_no   and ec.scheme_id in ('CD202') and ec.dme_flag is null ");
			query.append(" and ec.preaUth_aprv_dt is not null  and ec.preauth_rej_dt is null  and ec.preauth_cancel_dt is null  and ec.case_id = ect.case_id ");
			query.append(" and ect.activeyn = 'Y'  and ec.case_id = t.case_id and ec.case_hosp_code = eh.hosp_id and ect.asri_cat_code = mt.asri_code ");
			query.append(" and ect.icd_proc_code = mt.icd_proc_code and mt.state = 'CD202' and mt.asri_code = sp.dis_main_id and ep.relation = erm.relation_id ");
			if(empPenId!=null && !"".equalsIgnoreCase(empPenId)){
				query.append(" and ep.employee_no = '"+empPenId+"'");
			}
			if(healthCardNum!=null && !"".equalsIgnoreCase(healthCardNum)){
				query.append(" and ep.card_no like '%"+healthCardNum+"%'");
			}
			
			query.append(" group by EC.CASE_ID,EP.NAME,EP.PATIENT_ID,  EP.CARD_NO, eh.hosp_id, eh.hosp_name, erm.relation_name,EP.EMPLOYEE_NO, t.ppd_apprv_date ORDER BY EC.CASE_ID ");
			
			
			empDetailsList = genericDao.executeSQLQueryList(CasesSearchVO.class,query.toString());
 			
			
 			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDetailsList;
	}
}
