package com.ahct.preauth.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.model.EhfmHospitals;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfDesignationMst;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfEnrollment;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfJrnlstFamily;
import com.ahct.model.EhfPatientDocAttach;
import com.ahct.model.EhfPatientExamFind;
import com.ahct.model.EhfPatientHospDiagnosis;
import com.ahct.model.EhfPatientPersonalHistory;
import com.ahct.model.EhfSsrMedinpData;
import com.ahct.model.EhfTelephonicRegn;
import com.ahct.model.EhfmDiagCategoryMst;
import com.ahct.model.EhfmDiagCategoryMstId;
import com.ahct.model.EhfmDiagDisAnatomicalMst;
import com.ahct.model.EhfmDiagDiseaseMst;
import com.ahct.model.EhfmDiagMainCatMst;
import com.ahct.model.EhfmDiagMainCatMstId;
import com.ahct.model.EhfmDiagSubCatMst;
import com.ahct.model.EhfmDiagSubCatMstId;
import com.ahct.model.EhfmDiagnosisMst;
import com.ahct.model.EhfmDrugsMst;
import com.ahct.model.EhfmPersonalHistoryMst;
import com.ahct.model.EhfmRelationMst;
import com.ahct.model.EhfmSpecialities;
import com.ahct.model.EhfmTherapyCatMst;
import com.ahct.model.EhfmTherapyCatMstId;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmTherapyProcMstId;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class PreauthDtlsDaoImpl implements PreauthDtlsDao{
Logger glogger=Logger.getLogger(PreauthDtlsDao.class);
HibernateTemplate hibernateTemplate;
private static ConfigurationService configurationService;
private static CompositeConfiguration config;

static {
	configurationService = ConfigurationService.getInstance();
	config = configurationService.getConfiguration();
}
public HibernateTemplate getHibernateTemplate() {
	return hibernateTemplate;
}
public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	this.hibernateTemplate = hibernateTemplate;
}
	public GenericDAO getGenericDao() 
	{
		return genericDao;
	}
	public void setGenericDao(GenericDAO genericDao)
	{
		this.genericDao = genericDao;
	}
	GenericDAO genericDao;
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	/* public Map getPatientOP(HashMap lParamMap) 
	 {
	    Map patientOp=new HashMap();	    
	      StringBuffer lStrBuff = new StringBuffer();
	     Map lHashQValues = new HashMap();
	     int liCnt=0;
	    
	     try
	     {	         	        
	    	 lStrBuff.append(" from AsritPatient   ap, ");
	         lStrBuff.append(" AsrimCombo     ac, ");
	         lStrBuff.append(" AsrimCombo     acm, ");
	         lStrBuff.append(" AsrimLocations al, ");
	         lStrBuff.append(" AsrimLocations alm, ");
	         lStrBuff.append(" AsrimLocations alv, ");	         
	         lStrBuff.append(" AsrimLocations alc, ");
	         lStrBuff.append(" AsrimLocations almc, ");
	         lStrBuff.append(" AsrimLocations alvc ");	         
	         lStrBuff.append(" where ap.caste = ac.id.cmbDtlId and ap.patientId = '"+lParamMap.get("PatientID")+"' and ");
	         lStrBuff.append(" acm.id.cmbDtlId = ap.relation and al.id.locId = ap.districtCode and ");
	         lStrBuff.append(" al.locHdrId = '"+"LH6"+"' and alm.id.locId = ap.mandalCode and ");	         
	         lStrBuff.append(" alm.locHdrId = '"+"LM7"+"' and alv.id.locId = ap.villageCode and ");	         
	         lStrBuff.append(" alv.locHdrId = '"+"LM8"+"' and alc.locHdrId = '"+"LH6"+"' and ");	        
	         lStrBuff.append(" alc.locHdrId = ap.cDistrictCode and ");	         
	         lStrBuff.append(" almc.locHdrId = '"+"LM7"+"' and almc.locHdrId = ap.cMandalCode and ");	         
	         lStrBuff.append(" alvc.locHdrId = '"+"LM8"+"' and alvc.locHdrId = ap.cVillageCode ");	         	         	        
	         lStrBuff.append(" order by ap.crtDt desc ");	  
	         
	         Object[] pairs=genericDao.executeHQLQueryObjectPair(lStrBuff.toString());
	         if(pairs!=null)
	         {
		         AsritPatient asritPatient=(AsritPatient)pairs[0];
		         AsrimCombo asrimCombo1=(AsrimCombo)pairs[1];
		         AsrimCombo asrimCombo2=(AsrimCombo)pairs[2];
		         EhfmLocations asrimLocations1=(EhfmLocations)pairs[3];
		         EhfmLocations asrimLocations2=(EhfmLocations)pairs[4];
		         EhfmLocations asrimLocations3=(EhfmLocations)pairs[5];
		         EhfmLocations asrimLocations4=(EhfmLocations)pairs[6];
		         EhfmLocations asrimLocations5=(EhfmLocations)pairs[7];
		         EhfmLocations asrimLocations6=(EhfmLocations)pairs[8];
		         EhfmLocations asrimLocations7=(EhfmLocations)pairs[9];
		         EhfmLocations asrimLocations8=(EhfmLocations)pairs[10];	         	         
	         
	             if(asritPatient.getCardIssueDt()!=null)
	             patientOp.put("IssueDate",sdf.format(asritPatient.getCardIssueDt()));
	                        
	             if(asrimCombo1.getCmbDtlName()!=null)
	                 patientOp.put("Caste",asrimCombo1.getCmbDtlName());
	                    
	             if(asritPatient.getOccupationCd()!=null)
	                 patientOp.put("occupation",asritPatient.getOccupationCd());  
	           
                if(asritPatient.getAddr1()!=null)
                    patientOp.put("addr1",asritPatient.getAddr1());  
	           
	             if(asritPatient.getAddr2()!=null)
	                 patientOp.put("addr2",asritPatient.getAddr2());  
	         
	             if(asritPatient.getPermAddr1()!=null)
	                 patientOp.put("C_HNo",asritPatient.getPermAddr1()); 
	                 
	             if(asritPatient.getPermAddr2()!=null)
	                 patientOp.put("C_Street",asritPatient.getPermAddr2());  
	         
	             if(asritPatient.getContactNo()!=null)
	                 patientOp.put("contact",asritPatient.getContactNo());  
	             
	             if(asrimCombo2.getCmbDtlName()!=null)
	                 patientOp.put("Relation",asrimCombo2.getCmbDtlName());  
	          
	             if(asrimLocations1.getLocName()!=null)
	                 patientOp.put("district",asrimLocations1.getLocName());  
	           
	             if(asrimLocations2.getLocName()!=null)
	                 patientOp.put("mandal",asrimLocations2.getLocName());  
	            
	             if(asrimLocations3.getLocName()!=null)
	                 patientOp.put("village",asrimLocations3.getLocName());  
	          
	             if(asrimLocations4.getLocName()!=null)
	                 patientOp.put("hamlet",asrimLocations4.getLocName());  
	          
	             if(asrimLocations5.getLocName()!=null)
	                 patientOp.put("Cdistrict",asrimLocations5.getLocName());  
	             
	             if(asrimLocations6.getLocName()!=null)
	                 patientOp.put("Cmandal",asrimLocations6.getLocName());  
	             
	             if(asrimLocations7.getLocName()!=null)
	                 patientOp.put("Cvillage",asrimLocations7.getLocName());  
	             
	             if(asrimLocations8.getLocName()!=null)
	                 patientOp.put("Chamlet",asrimLocations8.getLocName());
	            
	             if(asritPatient.getSrcRegistration()!=null)
	             {
	                 if(asritPatient.getSrcRegistration().equalsIgnoreCase("D"))
	                	 patientOp.put("src","Direct");
	                 else if(asritPatient.getSrcRegistration().equalsIgnoreCase("MC"))
	                	 patientOp.put("src","Medical");
	                 else if(asritPatient.getSrcRegistration().equalsIgnoreCase("P"))
	                	 patientOp.put("src","PHC");
	                 else
	                	 patientOp.put("src","CMCO");	                 
	             }
	             
	             if(asritPatient.getRefCardNo()!=null)
	                 patientOp.put("RefCard",asritPatient.getRefCardNo());  	          
	         }	    
	         liCnt=0;  
	         lStrBuff = null;
	         lStrBuff = new StringBuffer();
	         lHashQValues = null;
	         lHashQValues = new HashMap();
	        	          	        
	         lStrBuff.append(" from AsritPatientHospDiagnosis hp,AsritPatient ap,AsritPatientTests apt,AsrimTests ast,EhfCase ac  ");
	         lStrBuff.append(" where hp.patientId = ap.patientId  and ap.patientId = apt.patientId and ast.testId = apt.testId   and ");
	         lStrBuff.append(" ast.testId = apt.testId and apt.patientId = ap.patientId and  ");
	         lStrBuff.append("  ac.casePatientNo = ap.patientId   and inv.caseId = ac.caseId and ac.caseId ='"+lParamMap.get("CaseId")+"' ");
	        	         	         
	         Object[] pair=genericDao.executeHQLQueryObjectPair(lStrBuff.toString());	                 	
	        if(pair!=null)
	        {
		        AsritPatientHospDiagnosis asritPatientHospDiagnosis=(AsritPatientHospDiagnosis)pair[0];
	         	AsritPatient asritPatient1=(AsritPatient)pair[1];
	         	AsritPatientTests asritPatientTests=(AsritPatientTests)pair[2];
	         	AsrimTests asrimTests=(AsrimTests)pair[3];
	         	EhfCase ehfCase=(EhfCase)pair[4];
	         	    	
		        	         
	             if(asritPatientHospDiagnosis.getHistoryIllness()!=null)
	             patientOp.put("history_illness",asritPatientHospDiagnosis.getHistoryIllness());
				 
				  if(asritPatientHospDiagnosis.getPastHistory()!=null)
	             patientOp.put("past_history",asritPatientHospDiagnosis.getPastHistory());
				 
	             if(asritPatientHospDiagnosis.getHospOpinion()!=null)
	             patientOp.put("hosp_diagnosis",asritPatientHospDiagnosis.getHospOpinion());
	             
				 if(asritPatientHospDiagnosis.getExamntnFindings()!=null)
	             patientOp.put("examntn_findings",asritPatientHospDiagnosis.getExamntnFindings());
				 
				  if(asritPatientHospDiagnosis.getHospComplaint()!=null)
	             patientOp.put("patient_complaint",asritPatientHospDiagnosis.getHospComplaint());				 
				 
				  if(asritPatientHospDiagnosis.getHospOpinion()!=null)
	             patientOp.put("hosp_opinion",asritPatientHospDiagnosis.getHospOpinion());
				 
				  if(asritPatientHospDiagnosis.getDoctType()!=null)
	             patientOp.put("doct_type",asritPatientHospDiagnosis.getDoctType());
				 
				  if(asritPatientHospDiagnosis.getHospDiagnosis()!=null)
	             patientOp.put("prov_diagnosis",asritPatientHospDiagnosis.getHospDiagnosis());
				 
				  if(asritPatientHospDiagnosis.getDoctName()!=null)
	             patientOp.put("doct_name",asritPatientHospDiagnosis.getDoctName());
	             
			      if(asritPatientHospDiagnosis.getDoctRegNo()!=null)
			      patientOp.put("doct_reg_no",asritPatientHospDiagnosis.getDoctRegNo());
		              
			      if(asritPatientHospDiagnosis.getDoctQuali()!=null)
			      patientOp.put("doct_quali",asritPatientHospDiagnosis.getDoctQuali());
		              
			      if(asritPatientHospDiagnosis.getDoctMobNo()!=null)
			      patientOp.put("doct_mob_no",asritPatientHospDiagnosis.getDoctMobNo());
	              			             	             				  
				  if(ehfCase.getCrtDt()!=null)
					  patientOp.put("dt",sdf.format(ehfCase.getCrtDt()));	
	        }
	        lStrBuff=new StringBuffer();
	        lStrBuff.append("SELECT ast.testDesc as VALUE from AsritPatientTests apt,AsrimTests ast ");
	        lStrBuff.append("where ast.testId=apt.testId and apt.patientId=? ");
	        String[] args=new String[1];
	        args[0]=(String)lParamMap.get("PatientID");	       
	        List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, lStrBuff.toString(),args);
	        
	        if(list!=null && !list.isEmpty())
			  {
				  //replace(stragg(asrimTests.test_desc()), '~', ',')  ;
	        	String lStrTestDesc=null;
	        	for(LabelBean labelBean:list)
	        	{
	        		if(lStrTestDesc!=null)
	        			lStrTestDesc=lStrTestDesc+","+labelBean.getVALUE();
	        		else
	        			lStrTestDesc=labelBean.getVALUE();
	        	}
				  patientOp.put("Investigation",lStrTestDesc);				 			
			  }
	     }
	     catch(Exception e)
	     {
	    	 //e.printStackTrace();
	    	 glogger.error("Error in PreauthDtlsDao getPatientOP():"+e.getMessage());
	     }	   
	     return patientOp;
	 }	*/

	 /* (non-Javadoc)
	 * @see com.tcs.Webframework.preauth.dao.PreauthDtlsDao#getPatientCommonDtls(java.lang.String)
	 */
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId){
			StringBuffer queryBuff = new StringBuffer();
			List<CommonDtlsVO> resLst = null;
			try
			{
				
				
				queryBuff.append(" select distinct p.name as PATIENTNAME , p.employeeNo as EMPLOYEENO, p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE , ec.caseRegnDate as date ");
				queryBuff.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT , cast(p.age as string) as AGEYEARS , case when p.slab ='P' then 'Private Ward' else 'Semi Private Ward' end as slabType ");
				queryBuff.append(" ,ec.caseNo  as CASENO , ec.claimNo as CLAIMNO  , ec.caseId as CASEID , p.patientId as PATID , p.cardType as cardType ");
				queryBuff.append("  ,p.regHospId as INTIID, hm.hospName||''||hm.hospDispCode as HOSPNAME , case when p.gender='M' then 'Male' else case when p.gender='F' then 'Female' else p.gender end end as GENDER , a.cmbDtlName as STATUS ,ec.caseStatus as CASESTAT   ");
				queryBuff.append(" , p.patientIpopNo as IPNO ,  p.intimationId as telephonicId , p.patientIpop as PATTYPE, p.houseNo || ' ' || p.street as PATADDR, p.crtDt||'' as PATDT , ");
				queryBuff.append(" case when ph.caseAdmType='EMG' then 'Emergency' else case when ph.caseAdmType='PLN' then 'Planned' else ph.caseAdmType end end as ADMTYPE , hm.houseNumber || ' ' || hm.street || ' ' || hm.hospCity as HOSPADDR , ");
				queryBuff.append(" ph.doctType as doctType,ph.doctId as DOCID,ph.doctName as DOCNAME,ph.doctQuali as DOCQUAL,ec.newBornBaby as newBornBaby,ph.doctMobNo as DOCCONTACT,ph.doctRegNo as DOCREGNO,p.crtUsr as mithra,ec.schemeId as  scheme,p.patientScheme as patientScheme,erm.relationName as relation");
				/**
				 * Removed below code to get rows in absence of category code and sub category code
				 */
				//queryBuff.append(" ,cm.catName as DISNAME, sm.subCatName as SURGNAME");
				
				queryBuff.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfCase ec , EhfmHospitals hm ");
				queryBuff.append(" , EhfmCmbDtls a , EhfPatientHospDiagnosis ph,EhfmRelationMst erm ");
				
				/**
				 * Removed below code to get rows in absence of category code and sub category code
				 */
				//queryBuff.append(" , EhfmDiagCategoryMst cm , EhfmDiagSubCatMst sm  ");
				
				
				queryBuff.append(" where p.patientId = ec.casePatientNo and  p.districtCode = l1.id.locId and p.relation=erm.relationId and ");
				queryBuff.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId  and ec.caseId = '"+caseId+"' ");
				queryBuff.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ec.caseStatus ");
				queryBuff.append(" and p.patientId =ph.patientId ");
				/**
				 * Removed below code to get rows in absence of category code and sub category code
				 */
				//queryBuff.append(" and ph.categoryCode = cm.id.catCode and ph.mainCatCode = cm.id.mainCatCode ");
				//queryBuff.append(" and sm.id.catCode =ph.categoryCode and sm.id.subCatCode = ph.subCatCode ");
				
				resLst = genericDao.executeHQLQueryList(CommonDtlsVO.class, queryBuff.toString());
				
				
			}
			catch(Exception e)
			{
				glogger.error("Error in PreauthDtlsDao getPatientCommonDtls():"+e.getMessage());
			}
			return resLst;		
		}
		
	
	public List<EhfPatientDocAttach> getOnBedPhotoDtls(String patId)
	{
		List<EhfPatientDocAttach> asritPatientDocAttach = null ;
	    try
	    {	  
	    	List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
			critList.add(new GenericDAOQueryCriteria("patientId",patId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));		
	    	asritPatientDocAttach = genericDao.findAllByCriteria( EhfPatientDocAttach.class, critList) ;	    	    		
	    }
	    catch ( Exception e )
	    {
	    	glogger.error("Error in PreauthDtlsDao getOnBedPhotoDtls():"+e.getMessage());
	    }
	    return asritPatientDocAttach ;
	}
	
	/*public Map saveFraudCrDtls(CmaAuditVO cmaVO)
    {      
		Map lResMap=null;
		String resData ="Failed";		//@transactional       
		EhfCaseCmaAudit asritCaseCmaAudit=genericDao.findById(EhfCaseCmaAudit.class,String.class,cmaVO.getCASEID());
		if(asritCaseCmaAudit!=null)
		{
			asritCaseCmaAudit.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			asritCaseCmaAudit=genericDao.save(asritCaseCmaAudit);			
            if("CD15664".equalsIgnoreCase(cmaVO.getCRTUSERROLE()))
            {
            	asritCaseCmaAudit.setJeoUserId(cmaVO.getJEOUSERID());
            	asritCaseCmaAudit.setJeoRemarks(cmaVO.getJEORMKS());
            	asritCaseCmaAudit.setJeoCrtDt(new java.sql.Timestamp(new Date().getTime()));
            }
            else if("CD1500".equalsIgnoreCase(cmaVO.getCRTUSERROLE()))
             {
            	asritCaseCmaAudit.setCmaUserId(cmaVO.getCMAUSERID());
            	asritCaseCmaAudit.setCmaRemarks(cmaVO.getCMARMKS());
            	asritCaseCmaAudit.setCmaCrtDt(new java.sql.Timestamp(new Date().getTime()));
             }
            asritCaseCmaAudit=genericDao.save(asritCaseCmaAudit);
		}
		else
		{
			asritCaseCmaAudit=new EhfCaseCmaAudit();		
			asritCaseCmaAudit.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			asritCaseCmaAudit.setCaseId(cmaVO.getCASEID());
			asritCaseCmaAudit.setAuditStatus("SUB");
            if("CD15663".equalsIgnoreCase(cmaVO.getCRTUSERROLE()))
            {
            	asritCaseCmaAudit.setDeoUserId(cmaVO.getDEOUSERID());
            	asritCaseCmaAudit.setDeoRemarks(cmaVO.getDEORMKS());
            	asritCaseCmaAudit.setDeoCrtDt(new java.sql.Timestamp(new Date().getTime()));
            }
			asritCaseCmaAudit=genericDao.save(asritCaseCmaAudit);
		}
		if(asritCaseCmaAudit!=null)
			lResMap=getFraudCrDtls(cmaVO.getCASEID());
		return lResMap;      				
    }
	
	public Map getFraudCrDtls(String pStrCaseId)
	{
		CmaAuditVO cmaVO =null;
		Map lResMap=new HashMap();
		try
		{								
			  StringBuffer sb=new StringBuffer();
	          sb.append("select acp.cardNo as RATIONCARD,ac.caseNo  as CASENO,ac.claimNo as  CLAIMNO, acp.patName as  PATIENTNAME,");
	          sb.append("  acp.ageDesc || '(' || acp.ptGenderDesc || ')'  as AGE , aco.cmbDtlName  as CASESTATUS,ac.caseStatus  as CASESTATUSCD ");
	          sb.append(" from   EhfCase ac, AsritCasePatient acp, AsrimCombo aco ");
	          sb.append(" where  ac.casePatientNo = acp.patientId and ac.caseStatus = aco.id.cmbDtlId and ac.caseId = ?");
	          String[] args=new String[1];
	          args[0]=pStrCaseId;
	          List<CmaAuditVO> cmaVOLst=genericDao.executeHQLQueryList(CmaAuditVO.class, sb.toString(),args);
	          if(cmaVOLst!=null && !cmaVOLst.isEmpty())
	          {
	        	  cmaVO =cmaVOLst.get(0);
	          }
	          
	          EhfCaseCmaAudit asritCaseCmaAudit=genericDao.findById(EhfCaseCmaAudit.class, String.class, pStrCaseId);
	          if(asritCaseCmaAudit!=null)
				{
		            cmaVO.setCASEID((asritCaseCmaAudit.getCaseId()!= null ? asritCaseCmaAudit.getCaseId() : ""));
		            cmaVO.setDEOUSERID(asritCaseCmaAudit.getDeoUserId());
		            cmaVO.setDEORMKS(asritCaseCmaAudit.getDeoRemarks());
		            cmaVO.setDRCRTDT(sdf.format(asritCaseCmaAudit.getDeoCrtDt()));
		            cmaVO.setJEOUSERID(asritCaseCmaAudit.getJeoUserId());
		            cmaVO.setJEORMKS(asritCaseCmaAudit.getJeoRemarks());
		            cmaVO.setJEOCRTDT(sdf.format(asritCaseCmaAudit.getJeoCrtDt()));
		            cmaVO.setCMAUSERID(asritCaseCmaAudit.getCmaUserId());
		            cmaVO.setCMARMKS(asritCaseCmaAudit.getCmaRemarks());
		            cmaVO.setCMACRTDT(sdf.format(asritCaseCmaAudit.getCmaCrtDt()));
		            cmaVO.setLSTUPDDT(sdf.format(asritCaseCmaAudit.getLstUpdDt()));            
				}
	          //For discussion notes  of claims
	            sb = new StringBuffer();
	            sb.append("select da.actId as D_ACTID, ");
	            sb.append("da.remarks as D_REMARKS,da.actionValue as D_ACTIONVALUE, ");
	            sb.append("au.firstName ||''|| au.lastName as D_ACTBY , ");
	            sb.append("da.crtDt as D_ACTDT, ");//'DD-Mon-YYYY HH:MI AM') 
	            sb.append("au.userRole as D_USERROLE, ac.cmbDtlName as D_CMBDTLNAME ");
	            sb.append("from AsritCaseDiscussionAudit da, AsrimUsers au , AsrimCombo ac ");
	            sb.append("where da.id.caseId = ? and da.actBy = au.userID and ac.id.cmbDtlId=au.userRole ");
	            sb.append("order by da.id.actOrder");
	            List<ClaimsFlowVO> discussionList=genericDao.executeHQLQueryList(CmaAuditVO.class, sb.toString(),args);
	           
	             lResMap.put("cmaAuditVO",cmaVO);
	             lResMap.put("discussionList",discussionList);
		}
		catch(Exception e){
			glogger.error("Error in PreauthDtlsDao getFraudCrDtls():"+e.getMessage());
		}
		return lResMap;
	}*/
	/**
	 * For IP tab
	 */
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId)
	{
		PreauthVO preauthVO = new PreauthVO(); 	
		StringBuffer query = new StringBuffer();
		try
		{
			/**
			 * query to get registration details
			 */
			query.append(" select distinct ep.patientIpop as patientType, ep.name as patientName , cast(ep.age as string) as age ,ep.gender as gender , ep.cardNo as rationCard , ep.patientIpopNo as patientIPNo , ");
			query.append(" case when ep.srcRegistration='D' then 'Direct' else case when ep.srcRegistration='MC'  then 'Medical' else case when ep.srcRegistration='P' then 'PHC' end end end  as srcRegistration, ");
			query.append(" ep.pinCode as contactNo,ep.cardIssueDt as cardIssueDt,ep.cardNo as refCardNo , ep.occupationCd as occName , rm.relationName as relationName  , cast(ep.contactNo as string) as contactNo ,  ");	
			//query.append(" ep.houseNo as houseNo , ep.street as street  ,lm.locName as district ,lm1.locName as mandal , lm2.locName as village ");	
			// card address
			query.append(" ep.houseNo as cardHNo , ep.street as cardStreet  ,lm.locName as cardDistrict ,lm1.locName as cardMandal , lm2.locName as cardVillage, ep.crtUsr as cruUsr ");
			//query.append("   , s1.id.symptomCode as symCode ,s1.symptomName as symName ,s2.id.mainSymptomCode as mainSymCode ,s2.mainSymptomName as mainSymName  ");	
			
			// get communication details
			
			query.append(" ,l4.locName as district , l5.locName  as mandal , l6.locName as village ");
			query.append(" , ep.cHouseNo as houseNo , ep.cStreet as street , ep.cPinCode as pincode ");
			query.append(" , eh.hospName as hospitalName, l7.locName as hospDistrict ");
			
			query.append(" from EhfPatient ep , EhfmRelationMst rm , " );//AsrimCombo ac ,, EhfmOccupationMst om 
			query.append(" EhfmLocations lm ,EhfmLocations lm1 , EhfmLocations lm2   "); //,EhfmSystematicExamFnd s1 ,EhfmSystematicExamFnd s2 ,EhfPatientHospDiagnosis hd
			query.append("  , EhfmLocations l4, EhfmLocations l5 , EhfmLocations l6  "); //,EhfEnrollment ee , EhfEnrollmentFamily ef 
			query.append("  , EhfmLocations    l7, EhfmHospitals    eh  "); //,EhfEnrollment ee , EhfEnrollmentFamily ef 
			
			query.append("	where ep.patientId = '"+pStrPatientId+"'   ");	
			query.append(" and ep.districtCode = lm.id.locId and ep.mandalCode =lm1.id.locId and ep.villageCode = lm2.id.locId ");
			query.append(" and ep.relation = rm.relationId  "); //and ac.id.cmbDtlId =ep.caste
			//query.append(" and s1.id.symptomCode =hd.symptomCode and s2.id.mainSymptomCode =hd.mainSymptomCode  and hd.patientId =ep.patientId ");
			query.append(" and ep.cDistrictCode = l4.id.locId  "); //ef.ehfCardNo = ep.cardNo and ef.enrollPrntId = ee.enrollPrntId and 
			query.append(" and ep.cMandalCode = l5.id.locId and ep.cVillageCode = l6.id.locId  ");
			query.append(" and eh.hospId= ep.regHospId and (l7.id.locId= eh.hospDist  or   l7.id.locId= eh.oldHospDistCode)  ");
			
			List<PreauthVO> lstPreauthVO = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(lstPreauthVO != null && lstPreauthVO.size() > 0)
	{
		preauthVO  = lstPreauthVO.get(0); 	// assigning registration details to vo
	}
	if(preauthVO.getCruUsr()!=null && !"".equalsIgnoreCase(preauthVO.getCruUsr()))
		preauthVO.setCruUsr(getEmpNameById(preauthVO.getCruUsr()));
	/**
	 * query to get Admission Details 
	 */
	  
	  EhfPatientHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class, pStrPatientId);
	if(ehfPatientHospDiagnosis != null)
	{
		preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());	
		if(ehfPatientHospDiagnosis.getDoctId()!=null && !"".equalsIgnoreCase(ehfPatientHospDiagnosis.getDoctId()))
		{
			if(preauthVO.getPatientType()!=null && !"".equalsIgnoreCase(preauthVO.getPatientType()) && "IP".equalsIgnoreCase(preauthVO.getPatientType()))
				preauthVO.setDocName(getEmpNameById(ehfPatientHospDiagnosis.getDoctId()));
			else if(preauthVO.getPatientType()!=null && !"".equalsIgnoreCase(preauthVO.getPatientType()) && "OP".equalsIgnoreCase(preauthVO.getPatientType()))
				preauthVO.setDocName(getDoctorById(ehfPatientHospDiagnosis.getDoctId()));
		}
		else
			preauthVO.setDocName("-NA-");
		preauthVO.setDocQual(ehfPatientHospDiagnosis.getDoctQuali());
		preauthVO.setDocMobNo(ehfPatientHospDiagnosis.getDoctMobNo());
		preauthVO.setDocReg(ehfPatientHospDiagnosis.getDoctRegNo());;
		preauthVO.setDocType(ehfPatientHospDiagnosis.getDoctType());
		preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());
		preauthVO.setRemarks(ehfPatientHospDiagnosis.getIpCaseRemarks());
		String complaintCode = ehfPatientHospDiagnosis.getComplaintType();
		String patComplaint = ehfPatientHospDiagnosis.getPatientComplaint();
			
		StringTokenizer str = null;
		StringTokenizer str1 = null;
		if(patComplaint!=null && !"".equalsIgnoreCase(patComplaint))
			str = new StringTokenizer(patComplaint,"~");
		if(complaintCode!=null && !"".equalsIgnoreCase(complaintCode))
			str1 = new StringTokenizer(complaintCode,"~");
	//EhfmSymptomsMst ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, complaintCode);
		StringBuffer query1=null;
		if(str1!=null)
		{
			while(str1.hasMoreTokens())
			{
				String code=str1.nextToken();
				query1 = new StringBuffer();
				query1.append(" select distinct ecm.id.mainComplCode as ID,ecm.mainComplName as VALUE from EhfmComplaintMst ecm where ecm.id.mainComplCode='"+code+"'");
				List<LabelBean> ehfComplaintList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
				if(ehfComplaintList!=null && !ehfComplaintList.isEmpty())
				{
					if(preauthVO.getComplaintType() == null || preauthVO.getComplaintType().equalsIgnoreCase(""))
					{
						//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
						preauthVO.setComplaintType(ehfComplaintList.get(0).getVALUE() + "( "+ehfComplaintList.get(0).getID()+" )")	;
					}
					else
					{
						//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
						preauthVO.setComplaintType( preauthVO.getComplaintType() +" , "+ ehfComplaintList.get(0).getVALUE() + "( "+ehfComplaintList.get(0).getID()+" )")	;
					}
					
				}
			}
		}
	preauthVO.setComplaintCode(complaintCode);
	if(str!=null)
	{
		while(str.hasMoreTokens())
		{
			query1 = new StringBuffer();
			query1.append(" select ecm.id.complCode as ID,ecm.complName as VALUE from EhfmComplaintMst ecm where ecm.id.complCode='"+str.nextToken()+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			//ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, str.nextToken());	
			if(ehfComplList != null && ehfComplList.size()>0)
			{
				if(preauthVO.getPatComplaint() == null || preauthVO.getPatComplaint().equalsIgnoreCase(""))
				{
					//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
					preauthVO.setPatComplaint(ehfComplList.get(0).getVALUE() + "( "+ehfComplList.get(0).getID()+" )")	;
				}
				else
				{
					//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
					preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfComplList.get(0).getVALUE() + "( "+ehfComplList.get(0).getID()+" )")	;
				}
				
			}
		} // setting list of patient complaints
	}
	
	/**
	 * get past illness
	 */	
	preauthVO.setPastIllness(ehfPatientHospDiagnosis.getPastHistory());
	preauthVO.setPastIllenesCancers(ehfPatientHospDiagnosis.getPastHistoryCancers());
	preauthVO.setPastIllenesEndDis(ehfPatientHospDiagnosis.getPastHistoryEndDis());
	preauthVO.setPastIllenesSurg(ehfPatientHospDiagnosis.getPastHistorySurg());
	if(ehfPatientHospDiagnosis.getPastHistoryOthr()!=null && !ehfPatientHospDiagnosis.getPastHistoryOthr().equalsIgnoreCase(""))
		preauthVO.setPastIllenesOthr(ehfPatientHospDiagnosis.getPastHistoryOthr());
	else
		preauthVO.setPastIllenesOthr("Past Illness- Others not found");
	/**
	 * get medco legal case details
	 */
	preauthVO.setLegalCaseCheck(ehfPatientHospDiagnosis.getLegalCaseCheck());
	preauthVO.setLegalCaseNo(ehfPatientHospDiagnosis.getLegalCaseNo());
	preauthVO.setPoliceStatName(ehfPatientHospDiagnosis.getPoliceStatName());
	
	/**
	 * get past history values
	 */
	String pastHist=ehfPatientHospDiagnosis.getPastHistory();
	if(pastHist != null && !pastHist.equals(""))
	{
	str1 = new StringTokenizer(pastHist,"~");
	while(str1.hasMoreTokens())
	{
		query1 = new StringBuffer();
		String disCode=str1.nextToken();
		if(disCode!=null && !disCode.equalsIgnoreCase("PH11"))
		{
			query1.append(" select hm.diseaseName as ID from EhfmPastHistoryMst hm where hm.diseaseCode='"+disCode+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			//ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, str.nextToken());	
			if(ehfComplList != null && ehfComplList.size()>0)
			{	

				if(preauthVO.getPastIllnessValue() == null || preauthVO.getPastIllnessValue().equalsIgnoreCase(""))
				{	
					preauthVO.setPastIllnessValue(ehfComplList.get(0).getID());
				}
				else
				{
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" , "+ehfComplList.get(0).getID())	;
				}
				String concatRemarks="";
				if(disCode.equalsIgnoreCase("PH8"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistoryCancers();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH10"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistorySurg();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH12"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistoryEndDis();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				
			}
		}
	}
	}
	else
		preauthVO.setPastIllnessValue("Past Illness not found");
	/**
	 * get personal History
	 */
	
	/*String personalHis = ehfPatientHospDiagnosis.getPersonalHistory();
	str = new StringTokenizer(personalHis,"~");
	while(str.hasMoreTokens())
	{
		EhfmPersonalHistoryMst ehfmPersonalHistoryMst = genericDao.findById(EhfmPersonalHistoryMst.class, String.class, str.nextToken())	;
	if(ehfmPersonalHistoryMst != null )
	{
		if(preauthVO.getPersonalHis() == null || preauthVO.getPersonalHis().equalsIgnoreCase(""))
		{
			preauthVO.setPersonalHis(ehfmPersonalHistoryMst.getDescription() + "( "+ehfmPersonalHistoryMst.getParntCode()+" )")	;	
		}
		else
		{
			preauthVO.setPersonalHis( preauthVO.getPersonalHis() +" , "+ ehfmPersonalHistoryMst.getDescription() + "( "+ehfmPersonalHistoryMst.getParntCode()+" )")	;
		}	
	}
	}*/
	preauthVO.setPersonalHis( ehfPatientHospDiagnosis.getPersonalHistory());
	String lDesc=null;
	List<LabelBean> personalHisList=new ArrayList<LabelBean>();
	List<LabelBean> personalHis=new ArrayList<LabelBean>();
	
	List<EhfmPersonalHistoryMst> parentList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode","PR");
	if(parentList!=null && !parentList.isEmpty())
	{
		
		for(EhfmPersonalHistoryMst ehfmPersonalHistoryMst1:parentList)
		{
			LabelBean lstPersonal = new LabelBean();
			lstPersonal.setID(ehfmPersonalHistoryMst1.getCode());
			lstPersonal.setVALUE(ehfmPersonalHistoryMst1.getDescription());
			List<EhfmPersonalHistoryMst> childList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode",ehfmPersonalHistoryMst1.getCode());
			List<LabelBean> personalsub=new ArrayList<LabelBean>();
			if(childList!=null && !childList.isEmpty())
			{
				
				for(EhfmPersonalHistoryMst ehfmPersonalHistoryMs2: childList)
				{
					LabelBean personalHisSub=new LabelBean();
					if(ehfmPersonalHistoryMs2!=null)
					{						
						if( lDesc== null || lDesc.equalsIgnoreCase(""))
						{
							lDesc=ehfmPersonalHistoryMs2.getDescription() ;	
						}
						else
						{
							lDesc=lDesc + "~" + ehfmPersonalHistoryMs2.getDescription() 	;	
						}
						personalHisSub.setID(ehfmPersonalHistoryMs2.getCode());
						personalHisSub.setVALUE(ehfmPersonalHistoryMs2.getDescription());
						}
					personalsub.add(personalHisSub);
					}
				
				}
			lstPersonal.setLstSub(personalsub);
			personalHisList.add(new LabelBean(ehfmPersonalHistoryMst1.getCode(),ehfmPersonalHistoryMst1.getDescription()+"^"+lDesc));
			lDesc="";
			personalHis.add(lstPersonal);
		}
	}
	preauthVO.setLstPersonalHistory(personalHis);
	
	EhfPatientPersonalHistory ehfPatientPersonalHistory= genericDao.findById(EhfPatientPersonalHistory.class, String.class, pStrPatientId);
	List<String> lstPerHis = new ArrayList<String>();
	if(ehfPatientPersonalHistory != null)
	{
		if(ehfPatientPersonalHistory.getAppetite() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getAppetite());	
		}
		if(ehfPatientPersonalHistory.getDiet() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getDiet());	
		}
		if(ehfPatientPersonalHistory.getBowels() !=null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getBowels());		
		}
		if(ehfPatientPersonalHistory.getNutrition() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getNutrition());
		}
		if(ehfPatientPersonalHistory.getKnownAllergies() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getKnownAllergies());	
			if(ehfPatientPersonalHistory.getKnownAllergies().contains("PR5.1"))
				preauthVO.setTestKnown(ehfPatientPersonalHistory.getKnownAllergies());
		}
		if(ehfPatientPersonalHistory.getAddictions() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getAddictions());	
			if(ehfPatientPersonalHistory.getAddictions().contains("PR6.1"))
			preauthVO.setTest(ehfPatientPersonalHistory.getAddictions());
		}
		if(ehfPatientPersonalHistory.getMaritalStatus() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getMaritalStatus());	
		}
	}
	preauthVO.setLstPerHis(lstPerHis);
	/**
	 * get family history
	 */		
	if(ehfPatientHospDiagnosis.getFamilyHistory()!=null && !ehfPatientHospDiagnosis.getFamilyHistory().equalsIgnoreCase("")){
		preauthVO.setFamilyHis(ehfPatientHospDiagnosis.getFamilyHistory());
	}//end of ehfPatientHospDiagnosis!=null
	
	if(ehfPatientHospDiagnosis.getFamilyHistoryOthr()!=null && !ehfPatientHospDiagnosis.getFamilyHistoryOthr().equalsIgnoreCase(""))
		preauthVO.setFamilyHistoryOthr(ehfPatientHospDiagnosis.getFamilyHistoryOthr());
	else
		preauthVO.setFamilyHistoryOthr("Family History -Others not found");
	
	
	
	/**
	 * get family history values
	 */
	String famhist=ehfPatientHospDiagnosis.getFamilyHistory();
	if(famhist != null && !famhist.equals(""))
	{
	str1 = new StringTokenizer(famhist,"~");
	while(str1.hasMoreTokens())
	{
		 query1 = new StringBuffer();
		String code=str1.nextToken();
		if(code!=null && !code.equalsIgnoreCase("FH11"))
		{
			query1.append(" select hm.description as ID from EhfmFamilyHistoryMst hm where hm.code='"+code+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			if(ehfComplList != null && ehfComplList.size()>0)
			{
				if(preauthVO.getFamilyHistValue() == null || preauthVO.getFamilyHistValue().equalsIgnoreCase(""))
				{	
					preauthVO.setFamilyHistValue(ehfComplList.get(0).getID());
				}
				else
				{
					preauthVO.setFamilyHistValue( preauthVO.getFamilyHistValue() +" , "+ehfComplList.get(0).getID())	;
				}
				
			}
		}
	}
	}
	else
		preauthVO.setFamilyHistValue("Family History not found");
	}//end of ehfPatientHospDiagnosis!=null
	/**
	 * get Examination findings
	 */
	EhfPatientExamFind ehfPatientExamFind = genericDao.findById(EhfPatientExamFind.class, String.class, pStrPatientId);
	if(ehfPatientExamFind != null)
	{
		if(ehfPatientExamFind.getBmi()!=null && !ehfPatientExamFind.getBmi().equalsIgnoreCase(""))
		preauthVO.setBmi(ehfPatientExamFind.getBmi());
		else
		preauthVO.setBmi("NA");
		if(ehfPatientExamFind.getHeight()!=null && !ehfPatientExamFind.getHeight().equalsIgnoreCase(""))
		preauthVO.setHeight(ehfPatientExamFind.getHeight());
		else
		preauthVO.setHeight("NA");
		if(ehfPatientExamFind.getWeight()!=null && !ehfPatientExamFind.getWeight().equalsIgnoreCase(""))
		preauthVO.setWeight(ehfPatientExamFind.getWeight());
		else
		preauthVO.setWeight("NA");
		preauthVO.setPallor(ehfPatientExamFind.getPallor());
		preauthVO.setCyanosis(ehfPatientExamFind.getCyanosis());
		preauthVO.setClubbingOfFingers(ehfPatientExamFind.getClubOfFingrToes());
		preauthVO.setLymphadenopathy(ehfPatientExamFind.getLymphadenopathy());
		preauthVO.setEdema(ehfPatientExamFind.getOedemaInFeet());
		preauthVO.setMalNutrition(ehfPatientExamFind.getMalnutrition());
		if(ehfPatientExamFind.getDehydration() != null && ehfPatientExamFind.getDehydration().equalsIgnoreCase("N"))
		preauthVO.setDehydration(ehfPatientExamFind.getDehydration());
		else if(ehfPatientExamFind.getDehydration() != null &&  !ehfPatientExamFind.getDehydration().equals("") && ehfPatientExamFind.getDehydration().contains("Y"))
		{
				preauthVO.setDehydration(ehfPatientExamFind.getDehydration().charAt(0)+"");
				preauthVO.setDehydrationType(ehfPatientExamFind.getDehydration().substring(1));
		}
		if(ehfPatientExamFind.getTemperature()!=null && !ehfPatientExamFind.getTemperature().equalsIgnoreCase(""))
		preauthVO.setTemperature(ehfPatientExamFind.getTemperature());
		else
		preauthVO.setTemperature("NA");	
		if(ehfPatientExamFind.getPluseRate()!=null && !ehfPatientExamFind.getPluseRate().equalsIgnoreCase(""))
		preauthVO.setPulseRate(ehfPatientExamFind.getPluseRate());
		else
		preauthVO.setPulseRate("NA");
		if(ehfPatientExamFind.getRespirationRate()!=null && !ehfPatientExamFind.getRespirationRate().equalsIgnoreCase(""))
		preauthVO.setRespirationRate(ehfPatientExamFind.getRespirationRate());
		else
			preauthVO.setRespirationRate("NA");	
		if(ehfPatientExamFind.getBpLt()!=null && !ehfPatientExamFind.getBpLt().equalsIgnoreCase(""))
		preauthVO.setBpLmt(ehfPatientExamFind.getBpLt());
		else
			preauthVO.setBpLmt("NA");
		if(ehfPatientExamFind.getBpRt()!=null && !ehfPatientExamFind.getBpRt().equalsIgnoreCase(""))
		preauthVO.setBpRmt(ehfPatientExamFind.getBpRt());
		else
			preauthVO.setBpRmt("NA");
	}
	
	/**
	 * get system Examination findings
	 */
	
	//genericDao.findById(ehfm_systematic_exam_fnd, idClass, id);
	

	/**
	 * Investigation Details--wm_concate(gim.invName );
	 */
	query=new StringBuffer();
	query.append("select gim.invName as investRemarks from EhfmGenInvestigationsMst gim,EhfPatientTests pt"); 
			query.append("      where pt.testId=gim.invCode and pt.patientId='"+pStrPatientId+"' ");
	List<PreauthVO> list1=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(list1!=null && !list1.isEmpty())
	{
		for(PreauthVO preauthVO1:list1)
		{
			if(preauthVO.getInvestRemarks() == null || preauthVO.getInvestRemarks().equalsIgnoreCase(""))
			{
				preauthVO.setInvestRemarks(preauthVO1.getInvestRemarks());	
			}
			else
			{
				preauthVO.setInvestRemarks(preauthVO.getInvestRemarks()+" , "+preauthVO1.getInvestRemarks());	
			}			
		}
	}
	
	/**
	 * Main Signs and Symptoms
	 */
	query=new StringBuffer();
	query.append("select e.symptomName as symptoms from EhfmSystematicExamFnd e,EhfSymtematicExamDtls d where e.id.symptomCode=d.id.symptomCode and d.id.caseId='"+pStrCaseId+"'");
	List<PreauthVO> list2=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(list2!=null && !list2.isEmpty())
	{
		for(PreauthVO preauthVO1:list2)
		{
			if(preauthVO.getSymName() == null || preauthVO.getSymName().equalsIgnoreCase(""))
			{
				preauthVO.setSymName(preauthVO1.getSymptoms());	
			}
			else
			{
				preauthVO.setSymName(preauthVO.getSymName()+" , "+preauthVO1.getSymptoms());	
			}
		}
	}
	else
	{
		preauthVO.setSymName("NA");
	}
		
	/**
	 * Final Diagnosis
	 */
	 /* query = new StringBuffer();
	  query.append("  select et.diaCatId as disMainId , dcm.diaCatName as disMainName , ec.diaSubcatId as disSubId , scm.diaSubCatName as  disSubName  ");
	  query.append(" ,ec.therapyType as therapyType , ec.therapyId as therapyId ,tm.therapyName as therapyName   ");
	  query.append("  from EhfCaseTherapy et , EhfmDiagnosisCatMst dcm , EhfmDiagnosisSubCatMst scm , EhfmTherapyMst tm  ");
	  query.append("  where et.caseId ='"+pStrCaseId+"' and  et.diaCatId =dcm.disCatId and ec.diaSubcatId =scm.diaSubCatId   ");
	  query.append("  and ec.therapyId = tm.therapyId  ");
	  List<PreauthVO> lstCat = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	  if(lstCat!=null)
		  preauthVO.setLstPreauthVO(lstCat);*/	
	
	/*
	 select et.dia_cat_id       as disMainId,
       dcm.diaCatName    as disMainName,
       ec.diaSubcatId    as disSubId,
       scm.diaSubCatName as disSubName,
       ec.therapyType    as therapyType,
       ec.therapyId      as therapyId,
       tm.therapyName    as therapyName
  from Ehf_Case_Therapy         et,
       EHFM_THERAPY_CAT_MST    dcm,
       EHFM_THERAPY_SUBCAT_MST scm,
       EHFM_THERAPY_PROC_MST        tm
 where et.case_Id = '"+pStrCaseId+"'
 and et.dia_cat_id=dcm.asri_cat_code and et.dia_subcat_id=scm.asri_subcat_code and 
 et.therapy_id=tm.asri_proc_code and scm.icd_cat_code=dcm.icd_cat_code 
 and tm.icd_cat_code= and tm.icd_subcat_code=
	 */
		}catch(Exception e)
		{
			e.printStackTrace();	
		}		
		return preauthVO;
	}
	public List<PreauthVO> getCasesWorkList(String caseId)
	{
		List<PreauthVO> casesList= new ArrayList<PreauthVO>();
		StringBuffer query = new StringBuffer();
		try
		{
			query.append(" select ea.actId as actId, eu.firstName ||' '|| eu.middleName ||' '|| eu.lastName as auditName, ");
			query.append(" ea.crtDt as auditDate, ea.remarks as remarks ");
			query.append(" from EhfAudit ea, EhfmUsers eu ");
			query.append(" where ea.id.caseId = '"+caseId+"' and eu.userId = ea.crtUsr order by ea.id.actOrder ");
			casesList= genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
			
		}
		catch(Exception e)
		{
			glogger.error("Exception in method getCasesWorkList in PreauthServiceImpl"+ e.getMessage());
		}
		return casesList;
	}
	public String getEmpNameById(String id)
	{
		String q="select firstName||' '||lastName as ID from EhfmUsers where userId='"+id+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			glogger.error("Error in PreauthDtlsDao getEmpNameById():"+e.getMessage());
		}
		return user;
	}
	
	
	public String getDoctorById(String id) {
		String q="select distinct title||' '||splstName as ID from EhfmSplstDctrs where id.regNum='"+id+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			glogger.error("Error in PreauthDtlsDao getDoctorById():"+e.getMessage());
		}
		return user;
	}
	
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	public String getHospName(String hospId) throws Exception {
		String hospitalName=null;
		try
		{
			EhfmHospitals asrimHospitals=genericDao.findById(EhfmHospitals.class,String.class,hospId);
		hospitalName=asrimHospitals.getHospName();
		}
		catch(Exception e)
		{
			glogger.error("Exception Occurred in getHospName() in PreauthDtlsDaoImpl class."+e.getMessage());
		}
		return hospitalName;
	}
	/**
     * ;
     * @param PatientVO patientVo
     * @return PatientVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */	
	
	
	public String getPatientScheme(String lStrCaseId)
	{
		String patientScheme=null;
		EhfCase ehfCase=new EhfCase();
		try
		{
		if(lStrCaseId!=null)
		{
		ehfCase=genericDao.findById(EhfCase.class,String.class,lStrCaseId);
		}
		if(ehfCase!=null)
		{
			patientScheme=ehfCase.getPatientScheme();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return patientScheme;
		
		
	}
	
	
	@SuppressWarnings({ "unused", "null" })
	public PatientVO retrieveCardDetails(PatientVO patientVo) throws Exception {
		PatientVO patientVO=new PatientVO();;
		EhfEnrollmentFamily ehfEnrollmentFamily=null;
		List<EhfEnrollmentFamily> ehfEnrollmentFamilyList=null;
		
		EhfJrnlstFamily ehfJrnlstFamily=null;
		List<EhfJrnlstFamily> ehfJrnlstFamilyList=null;
		
		String patientScheme=patientVo.getPatientScheme();
		List<String> statusList=new ArrayList<String>();
		try
		{
			List<GenericDAOQueryCriteria> criteriaList= new ArrayList<GenericDAOQueryCriteria>();
			
			
			
			if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS")))
			{
			if(config.getString("Card.ValidStatus")!=null && !"".equalsIgnoreCase(config.getString("Card.ValidStatus")))
			{
				String[] arrCardStatus= config.getString("Card.ValidStatus").split("~");
				if(arrCardStatus!=null)
				{
					for(int i=0; i<arrCardStatus.length; i++)
					{
						statusList.add(arrCardStatus[i]);
					}
				}
				criteriaList.add(new GenericDAOQueryCriteria("journalCradNo", patientVo.getCardNo(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				//criteriaList.add(new GenericDAOQueryCriteria("enrollStatus",statusList,GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
				ehfJrnlstFamilyList= genericDao.findAllByCriteria(EhfJrnlstFamily.class, criteriaList);
			}
			
			if(ehfJrnlstFamilyList!=null && ehfJrnlstFamilyList.size()>0)
			{
				ehfJrnlstFamily= ehfJrnlstFamilyList.get(0);
				String enrollParntId=ehfJrnlstFamily.getJournalEnrollParntId();
				
				EhfEnrollment ehfEnrollment= genericDao.findById(EhfEnrollment.class, String.class, enrollParntId);
				if(ehfJrnlstFamily!=null)
				{
					patientVO=new PatientVO();
				patientVO.setPhoto(ehfJrnlstFamily.getEnrollPhoto());
				}
/*				if(ehfEnrollment!=null)
				{
					patientVO=new PatientVO();
					patientVO.setEmpCode(ehfEnrollment.getEmpCode());
					patientVO.setDateOfBirth(ehfEnrollmentFamily.getEnrollDob().toString());
					patientVO.setGender(Character.toString(ehfEnrollmentFamily.getEnrollGender()));
					patientVO.setFirstName(ehfEnrollmentFamily.getEnrollName());
					patientVO.setRelation(ehfEnrollmentFamily.getEnrollRelation());
					patientVO.setCaste(ehfEnrollment.getEmpCommu());
					patientVO.setContactNo(ehfEnrollment.getEmpHphone());
					patientVO.setAddr1(ehfEnrollment.getEmpHno());
					patientVO.setAddr2(ehfEnrollment.getEmpHstreetno());
					patientVO.setDistrictCode(ehfEnrollment.getEmpHdist());
					patientVO.setMdl_mpl(ehfEnrollment.getEmpHmandMunciSel());
					patientVO.setMandalCode(ehfEnrollment.getEmpHmandMunci());
					patientVO.setVillageCode(ehfEnrollment.getEmpHvillTwn());
					String slab=getSlabType(ehfEnrollment.getPayScale());
					if(slab!=null)
					{
						patientVO.setSlab(slab);
					}
					else
					{
						patientVO.setSlab(config.getString("Slab.SemiPrivateWard"));
					}
					if(ehfEnrollment.getScheme()!=null)
					{
						patientVO.setSchemeId(ehfEnrollment.getScheme());
					}
					else
					{
						patientVO.setSchemeId(config.getString("Scheme.ap"));
					}
					if(ehfEnrollment.getEmpHemail()!=null)
					{
						patientVO.seteMailId(ehfEnrollment.getEmpHemail());
					}
	
					if(patientVo.getCardNo().endsWith("01"))
					{
						patientVO.setMaritalStatus(ehfEnrollment.getEmpMaritalStat());
						if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) && patientVo.getCardType().equalsIgnoreCase("E"))
						{
							String service=ehfEnrollment.getService();
							String categoryName=ehfEnrollment.getServDsgn();
							String hod=ehfEnrollment.getDeptHod();
							if(ehfEnrollment.getDeptDesignation()!=null && !"".equals(ehfEnrollment.getDeptDesignation()))
							{
								criteriaList.removeAll(criteriaList);
								Long desgnId=Long.parseLong(ehfEnrollment.getDeptDesignation());
								
								criteriaList.add(new GenericDAOQueryCriteria("id.hod",hod,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.service",service,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.categoryName",categoryName,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.dsgnId",desgnId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								
								List<EhfDesignationMst> designationList= genericDao.findAllByCriteria(EhfDesignationMst.class, criteriaList);
								
								if(designationList!=null && designationList.size()>0)
								{
									patientVO.setOccupationCd(designationList.get(0).getDeptDesignation());
								}
								
							}
						}
						Map<String, Object> photoMap=new HashMap<String, Object>();
						photoMap.put("employeeParntId", enrollParntId);
						photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
						photoMap.put("attachactiveYN","Y");
						EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
						if(ehfEmployeeDocAttach!=null)
						{
							patientVO.setPhoto(ehfEmployeeDocAttach.getSavedName());
						}
					}
					else
					{
						patientVO.setPhoto(ehfEnrollmentFamily.getEnrollPhoto());
					}
				}*/
			}
			
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			else
			{
			if(config.getString("Card.ValidStatus")!=null && !"".equalsIgnoreCase(config.getString("Card.ValidStatus")))
			{
				String[] arrCardStatus= config.getString("Card.ValidStatus").split("~");
				if(arrCardStatus!=null)
				{
					for(int i=0; i<arrCardStatus.length; i++)
					{
						statusList.add(arrCardStatus[i]);
					}
				}
				criteriaList.add(new GenericDAOQueryCriteria("ehfCardNo", patientVo.getCardNo(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				//criteriaList.add(new GenericDAOQueryCriteria("enrollStatus",statusList,GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
				ehfEnrollmentFamilyList= genericDao.findAllByCriteria(EhfEnrollmentFamily.class, criteriaList);
			}
			
			if(ehfEnrollmentFamilyList!=null && ehfEnrollmentFamilyList.size()>0)
			{
				ehfEnrollmentFamily= ehfEnrollmentFamilyList.get(0);
				String enrollParntId=ehfEnrollmentFamily.getEnrollPrntId();
				
				EhfEnrollment ehfEnrollment= genericDao.findById(EhfEnrollment.class, String.class, enrollParntId);
				
				if(ehfEnrollment!=null)
				{
					patientVO=new PatientVO();
					patientVO.setEmpCode(ehfEnrollment.getEmpCode());
					patientVO.setDateOfBirth(ehfEnrollmentFamily.getEnrollDob().toString());
					patientVO.setGender(Character.toString(ehfEnrollmentFamily.getEnrollGender()));
					patientVO.setFirstName(ehfEnrollmentFamily.getEnrollName());
					patientVO.setRelation(ehfEnrollmentFamily.getEnrollRelation());
					patientVO.setCaste(ehfEnrollment.getEmpCommu());
					patientVO.setContactNo(ehfEnrollment.getEmpHphone());
					patientVO.setAddr1(ehfEnrollment.getEmpHno());
					patientVO.setAddr2(ehfEnrollment.getEmpHstreetno());
					patientVO.setDistrictCode(ehfEnrollment.getEmpHdist());
					patientVO.setMdl_mpl(ehfEnrollment.getEmpHmandMunciSel());
					patientVO.setMandalCode(ehfEnrollment.getEmpHmandMunci());
					patientVO.setVillageCode(ehfEnrollment.getEmpHvillTwn());
					String slab=getSlabType(ehfEnrollment.getPayScale());
					if(slab!=null)
					{
						patientVO.setSlab(slab);
					}
					else
					{
						patientVO.setSlab(config.getString("Slab.SemiPrivateWard"));
					}
					if(ehfEnrollment.getScheme()!=null)
					{
						patientVO.setSchemeId(ehfEnrollment.getScheme());
					}
					else
					{
						patientVO.setSchemeId(config.getString("Scheme.ap"));
					}
					if(ehfEnrollment.getEmpHemail()!=null)
					{
						patientVO.seteMailId(ehfEnrollment.getEmpHemail());
					}
	
					if(patientVo.getCardNo().endsWith("01"))
					{
						patientVO.setMaritalStatus(ehfEnrollment.getEmpMaritalStat());
						if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) && patientVo.getCardType().equalsIgnoreCase("E"))
						{
							String service=ehfEnrollment.getService();
							String categoryName=ehfEnrollment.getServDsgn();
							String hod=ehfEnrollment.getDeptHod();
							if(ehfEnrollment.getDeptDesignation()!=null && !"".equals(ehfEnrollment.getDeptDesignation()))
							{
								criteriaList.removeAll(criteriaList);
								Long desgnId=Long.parseLong(ehfEnrollment.getDeptDesignation());
								
								criteriaList.add(new GenericDAOQueryCriteria("id.hod",hod,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.service",service,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.categoryName",categoryName,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.dsgnId",desgnId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								
								List<EhfDesignationMst> designationList= genericDao.findAllByCriteria(EhfDesignationMst.class, criteriaList);
								
								if(designationList!=null && designationList.size()>0)
								{
									patientVO.setOccupationCd(designationList.get(0).getDeptDesignation());
								}
								
							}
						}
					}
				}
				
				if(patientVo.getCardNo().endsWith("01"))
					{
						Map<String, Object> photoMap=new HashMap<String, Object>();
						photoMap.put("employeeParntId", enrollParntId);
						photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
						photoMap.put("attachactiveYN","Y");
						EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
						if(ehfEmployeeDocAttach!=null)
						{
							patientVO.setPhoto(ehfEmployeeDocAttach.getSavedName());
						}
					}
				else
					{
						patientVO.setPhoto(ehfEnrollmentFamily.getEnrollPhoto());
					}
			}
			}
		}
		catch(Exception e)
		{
//			glogger.error("Exception Occurred in retrieveCardDetails() in PreauthDtlsDaoImpl class."+e.getMessage());
			e.printStackTrace();
		}
		
		return patientVO;
	}
	
	public List<LabelBean> getDrugSubList(String drugTypeCode) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.drugSubTypeCode as  ID , edm.drugSubTypeName as VALUE from EhfmDrugsMst edm where edm.id.drugTypeCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.drugSubTypeName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		glogger.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	/**
     * ;
     * @param String drugCode
     * @return List<String> drugDetailsList
     * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public String getDrugDetails(String drugCode) {
		String drugDetails="";
    	Iterator<EhfmDrugsMst> drugItr=null;
    	EhfmDrugsMst ehfmDrugsMst=null;
    	try
    	{
    		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    		criteriaList.add(new GenericDAOQueryCriteria("activeYN",config.getString("ActiveYn"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.drugCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmDrugsMst> drugsList = genericDao.findAllByCriteria(EhfmDrugsMst.class, criteriaList);
    		drugItr=drugsList.iterator();
    		if(drugItr.hasNext())
    		{
    			ehfmDrugsMst=(EhfmDrugsMst)drugItr.next();   
    			drugDetails=ehfmDrugsMst.getRouteCode()+"("+ehfmDrugsMst.getRouteName()+")~"+ehfmDrugsMst.getStrength();
    			criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    			criteriaList.add(new GenericDAOQueryCriteria("id.inpCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			List<EhfSsrMedinpData> lstEhfSsrMedinpData = genericDao.findAllByCriteria(EhfSsrMedinpData.class, criteriaList);
    			if(lstEhfSsrMedinpData != null && lstEhfSsrMedinpData.size() > 0)
    			drugDetails= drugDetails+"~"+lstEhfSsrMedinpData.get(0).getUnitPrice();
    		}
    	}
    	catch(Exception e)
    	{
    		glogger.error("Exception Occurred in getDrugDetails() in PatientDaoImpl class."+e.getMessage());
    	}
		return drugDetails;
	}
	
	private String getSlabType(String payGrade) throws Exception {
		List<LabelBean> payGradeList= new ArrayList<LabelBean>();
		String slabType=null;
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.slab as VALUE from EhfmPayGradeMst a where a.id.payGrade='"+payGrade+"'");     
			payGradeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
			if(payGradeList!=null && payGradeList.size()>0)
			{
				slabType=payGradeList.get(0).getVALUE();
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception Occurred in getSlabType() in PreauthDtlsDaoImpl.class."+e.getMessage());
		}
		return slabType;
	}  

	public CasesSearchVO getCaseCommonDtls(String caseId) {
		StringBuffer queryBuff = new StringBuffer();
		List<CasesSearchVO> resLst = null;CasesSearchVO caseSearchVO = new CasesSearchVO();
		try
		{		
			queryBuff.append(" select ec.caseNo  as caseNo, ec.claimNo as claimNo,p.name as patientName, p.cardNo as wapNo, a.cmbDtlName as claimStatus,ec.lstUpdDt as lstUpdDt, ec.caseRegnDate as inpatientCaseSubDt,  "); 
			queryBuff.append(" ec.csPreauthDt as csPreauthDt, ec.preauthAprvDate as preauthAprvDate, ec.csDisDt as csDisDt,ec.csDeathDt as csDeathDt,nvl(ec.csClAmount,0) as claimAmt ,ec.clmSubDt as clmSubDt, ec.csTransId as csTransId,ec.csTransDt as csTransDt, ec.csRemarks as csRemarks,ec.csSbhDt as csSbhDt");
			queryBuff.append(" from EhfPatient p ,EhfCase ec ,EhfmCmbDtls a where p.patientId = ec.casePatientNo and a.cmbDtlId = ec.caseStatus and ec.caseId = '"+caseId+"' ");
			resLst = genericDao.executeHQLQueryList(CasesSearchVO.class, queryBuff.toString());
			if(resLst!=null && resLst.size()>0)
				caseSearchVO = resLst.get(0);
		}
		catch(Exception e)
		{
			glogger.error("Error in PreauthDtlsDao getCaseCommonDtls():"+e.getMessage());
		}
		return caseSearchVO;		
	}
	
public PatientVO getTelephonicIntimationDtls(PatientVO patientVo) { 
		
		PatientVO patientVO = new PatientVO();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateformat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SessionFactory factory=null;
		Session session=null;
		try {
			factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
			Query hQuery;
			StringBuffer query = new StringBuffer();
             query.append(" from EhfTelephonicRegn trr,EhfmHospitals EH ,EhfmLocations EL");
             query.append(" where trr.hospId=EH.hospId and EL.id.locId=trr.districtCode " );
             query.append(" and trr.telephonicId='"+patientVo.getTelephonicId()+"'");
             hQuery=session.createQuery(query.toString());
             Iterator ite = hQuery.list().iterator();
    			while(ite.hasNext())
    			{
				Object[] pair = (Object[]) ite.next();
				EhfTelephonicRegn ehfmTelephonicRegn=(EhfTelephonicRegn)pair[0];
				EhfmHospitals asrimHosp=(EhfmHospitals)pair[1];
				patientVO.setTelephonicId(ehfmTelephonicRegn.getTelephonicId());
				patientVO.setCardNo(ehfmTelephonicRegn.getCardNo());
				patientVO.setCardType(ehfmTelephonicRegn.getCardType());
				patientVO.setEmpCode(ehfmTelephonicRegn.getEmployeeNo());
				if(ehfmTelephonicRegn.getName()!=null && !ehfmTelephonicRegn.getName().equalsIgnoreCase(""))
				patientVO.setFirstName(ehfmTelephonicRegn.getName());
				else
					patientVO.setFirstName("NA");
				patientVO.setGender(ehfmTelephonicRegn.getGender());
				if(ehfmTelephonicRegn.getAge()!=null)
				patientVO.setAge(ehfmTelephonicRegn.getAge().toString());
				else
					patientVO.setAge("NA");
				if(ehfmTelephonicRegn.getAgeDays()!=null)
				patientVO.setAgeDays(ehfmTelephonicRegn.getAgeDays().toString());
				else
					patientVO.setAgeDays("NA");
				if(ehfmTelephonicRegn.getAgeMonths()!=null)
				patientVO.setAgeMonths(ehfmTelephonicRegn.getAgeMonths().toString());
				else
					patientVO.setAgeMonths("NA");
				patientVO.setRelation(ehfmTelephonicRegn.getRelation());
				if(ehfmTelephonicRegn.getRelation()!=null && !ehfmTelephonicRegn.getRelation().equalsIgnoreCase("-1")){
					EhfmRelationMst relationmst = genericDao.findById(EhfmRelationMst.class, Integer.class,Integer.parseInt(ehfmTelephonicRegn.getRelation()));
					if(relationmst!=null)
					patientVO.setRelOthers(relationmst.getRelationName());					
				}
				else
					patientVO.setRelOthers("NA");
					
				patientVO.setOccupationCd(ehfmTelephonicRegn.getOccupationCd());
				patientVO.setOccOthers(ehfmTelephonicRegn.getOccupationCd());
				patientVO.setSlab(ehfmTelephonicRegn.getSlab());
				//setting card address
				if(ehfmTelephonicRegn.gethNo()!=null)
					patientVO.setAddr1(ehfmTelephonicRegn.gethNo());
				else
					patientVO.setAddr1("NA");
				if(ehfmTelephonicRegn.getStreet()!=null)
					patientVO.setAddr2(ehfmTelephonicRegn.getStreet());
				else
					patientVO.setAddr2("NA");
				if(ehfmTelephonicRegn.getState()!=null && !ehfmTelephonicRegn.getState().equalsIgnoreCase("-1")){

					patientVO.setState(ehfmTelephonicRegn.getState());
				}
				if(ehfmTelephonicRegn.getDistrictCode()!=null && !ehfmTelephonicRegn.getDistrictCode().equalsIgnoreCase("-1")){

					patientVO.setDistrictCode(ehfmTelephonicRegn.getDistrictCode());
				}
				if(ehfmTelephonicRegn.getMdl_mpl()!=null)
					patientVO.setMdl_mpl(ehfmTelephonicRegn.getMdl_mpl());

				if(ehfmTelephonicRegn.getMandalCode()!=null && !ehfmTelephonicRegn.getMandalCode().equalsIgnoreCase("-1"))
				{
					patientVO.setMandalCode(ehfmTelephonicRegn.getMandalCode());

					if(ehfmTelephonicRegn.getVillageCode()!=null && !ehfmTelephonicRegn.getVillageCode().equalsIgnoreCase("-1"))
					{
						patientVO.setVillageCode(ehfmTelephonicRegn.getVillageCode());
					}
					else
						patientVO.setVillageCode("NA");
				}
				else
				{
					patientVO.setMandalCode("NA");
					patientVO.setVillageCode("NA");
				}
				if(ehfmTelephonicRegn.getPinCode()!=null)
					patientVO.setPincode(ehfmTelephonicRegn.getPinCode());
				else
					patientVO.setPincode("NA");
                
                //Setting Communication address
                
                if(ehfmTelephonicRegn.getcHouseNo()!=null)
                	patientVO.setPermAddr1(ehfmTelephonicRegn.getcHouseNo());
                else
                	patientVO.setPermAddr1("NA");
                if(ehfmTelephonicRegn.getcStreet()!=null)
                	patientVO.setPermAddr2(ehfmTelephonicRegn.getcStreet());
                else
                	patientVO.setPermAddr2("NA");
				if(ehfmTelephonicRegn.getcState()!=null && !ehfmTelephonicRegn.getcState().equalsIgnoreCase("-1"))
				{
					patientVO.setC_state(ehfmTelephonicRegn.getcState());
				}
                if(ehfmTelephonicRegn.getcDistrictCode()!=null && !ehfmTelephonicRegn.getcDistrictCode().equalsIgnoreCase("-1"))
                {
                	patientVO.setC_district_code(ehfmTelephonicRegn.getcDistrictCode());
                }
                if(ehfmTelephonicRegn.getC_mdl_mpl()!=null)
                	patientVO.setC_mdl_mpl(ehfmTelephonicRegn.getC_mdl_mpl());

                if(ehfmTelephonicRegn.getcMandalCode()!=null && !ehfmTelephonicRegn.getcMandalCode().equalsIgnoreCase("-1"))
                {
                	patientVO.setC_mandal_code(ehfmTelephonicRegn.getcMandalCode());
                	if(ehfmTelephonicRegn.getcVillageCode()!=null && !ehfmTelephonicRegn.getcVillageCode().equalsIgnoreCase("-1"))
                	{
                		patientVO.setC_village_code(ehfmTelephonicRegn.getcVillageCode());
                	}
                	else
                		patientVO.setC_village_code("NA");
                }
                else
                {
                	patientVO.setC_mandal_code("NA");
                	patientVO.setC_village_code("NA");
                }
                if(ehfmTelephonicRegn.getcPinCode()!=null)
                	patientVO.setC_pin_code(ehfmTelephonicRegn.getcPinCode());
                else
                	patientVO.setC_pin_code("NA");
                if(ehfmTelephonicRegn.getContactNo()!=null)
                	patientVO.setContactNo(ehfmTelephonicRegn.getContactNo().toString());	
                else
                	patientVO.setContactNo("NA");
                patientVO.setRegHospId(ehfmTelephonicRegn.getHospId());
                patientVO.setFamilyHead(ehfmTelephonicRegn.getFamilyHead());
                if(ehfmTelephonicRegn.getEnrollDob()!=null)
                	patientVO.setDateOfBirth(dateformat.format(ehfmTelephonicRegn.getEnrollDob()));
                if(ehfmTelephonicRegn.getCardIssueDt()!=null && !ehfmTelephonicRegn.getCardIssueDt().equals(""))
                {
                patientVO.setCardIssueDt(dateformat.format(ehfmTelephonicRegn.getCardIssueDt()));
                }
                patientVO.setCaste(ehfmTelephonicRegn.getCaste());
                patientVO.setTeleCallerDesgn(ehfmTelephonicRegn.getCallerDesig());
                patientVO.setTeleCallerName(ehfmTelephonicRegn.getCallerName());
                patientVO.setTeleDocDesgn(ehfmTelephonicRegn.getDoctorDesig());
                patientVO.setTeleDocName(ehfmTelephonicRegn.getDoctorName());
                patientVO.setTeleDocPhoneNo(ehfmTelephonicRegn.getDocMobileNo());
                patientVO.setTeleDocremarks(ehfmTelephonicRegn.getDocRemarks());
                patientVO.setTelePhoneNo(ehfmTelephonicRegn.getCallerMobileNo());
                patientVO.setCrtDt(dateformat1.format(ehfmTelephonicRegn.getCrtDt()));
                patientVO.setIndication(ehfmTelephonicRegn.getTeleIntimRemarks());
                patientVO.setRegHospDt(asrimHosp.getHospName());
				
                //Setting Therapy details
                if(ehfmTelephonicRegn.getAsriCatCode()!=null)
                {
                	EhfmSpecialities ehfmSpecialities=genericDao.findById(EhfmSpecialities.class,String.class,ehfmTelephonicRegn.getAsriCatCode());
                	if(ehfmSpecialities!=null)
                	{
                		patientVO.setAsriCatCode(ehfmSpecialities.getDisMainName());
                	}
                	else
                		patientVO.setAsriCatCode("NA");
                }
				if(ehfmTelephonicRegn.getICDCatCode()!=null && !ehfmTelephonicRegn.getICDCatCode().equalsIgnoreCase("-1"))
				{
					EhfmTherapyCatMstId ehfmTherapyCatMstId=new EhfmTherapyCatMstId(ehfmTelephonicRegn.getAsriCatCode(),ehfmTelephonicRegn.getICDCatCode());
					EhfmTherapyCatMst ehfmTherapyCatMst=genericDao.findById(EhfmTherapyCatMst.class,EhfmTherapyCatMstId.class,ehfmTherapyCatMstId);
					if(ehfmTherapyCatMst!=null)
						patientVO.setICDcatName(ehfmTherapyCatMst.getIcdCatName());
					else
						patientVO.setICDcatName("NA");
				}
				
				if(ehfmTelephonicRegn.getICDProcCode()!=null && !ehfmTelephonicRegn.getICDProcCode().equalsIgnoreCase("-1"))
				{
					patientVO.setICDprocName(ehfmTelephonicRegn.getICDProcCode());
					EhfmTherapyProcMstId ehfmTherapyProcMstId=new EhfmTherapyProcMstId(ehfmTelephonicRegn.getAsriCatCode(),ehfmTelephonicRegn.getICDProcCode(), null,null);
					EhfmTherapyProcMst ehfmTherapyProcMst=genericDao.findById(EhfmTherapyProcMst.class,EhfmTherapyProcMstId.class,ehfmTherapyProcMstId);
					if(ehfmTherapyProcMst!=null)
					{
						patientVO.setICDprocName(ehfmTherapyProcMst.getProcName());
						patientVO.setTherapyCatId(ehfmTherapyProcMst.getId().getIcdProcCode());
					}
					else
						patientVO.setICDprocName("NA");
				}
				//Setting Diagnosis Details
				if(ehfmTelephonicRegn.getDiaType()!=null && !ehfmTelephonicRegn.getDiaType().equalsIgnoreCase("-1"))
				{
					patientVO.setDiagnosisType(ehfmTelephonicRegn.getDiaType());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
	        		criteriaList.add(new GenericDAOQueryCriteria("id.diagnosisCode",ehfmTelephonicRegn.getDiaType(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    			List<EhfmDiagnosisMst> ehfmDiagnosisMstList = genericDao.findAllByCriteria(EhfmDiagnosisMst.class, criteriaList);
	    			for(EhfmDiagnosisMst ehfmDiagnosisMst:ehfmDiagnosisMstList)
	    			{
	    				patientVO.setDiagnosisType(ehfmDiagnosisMst.getDiagnosisName());
	    			}
				}
				if(ehfmTelephonicRegn.getDiaMainCatCode()!=null && !ehfmTelephonicRegn.getDiaMainCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setMainCatName(ehfmTelephonicRegn.getDiaMainCatCode());
					EhfmDiagMainCatMst ehfmDiagMainCatMst=genericDao.findById(EhfmDiagMainCatMst.class,EhfmDiagMainCatMstId.class,new EhfmDiagMainCatMstId(ehfmTelephonicRegn.getDiaMainCatCode(),ehfmTelephonicRegn.getDiaType()));
					if(ehfmDiagMainCatMst!=null)
						patientVO.setMainCatName(ehfmDiagMainCatMst.getMainCatName());
					else
						patientVO.setMainCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaCatCode()!=null && !ehfmTelephonicRegn.getDiaCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setCatName(ehfmTelephonicRegn.getDiaCatCode());
					EhfmDiagCategoryMst ehfmDiagCategoryMst=genericDao.findById(EhfmDiagCategoryMst.class,EhfmDiagCategoryMstId.class,new EhfmDiagCategoryMstId(ehfmTelephonicRegn.getDiaCatCode(),ehfmTelephonicRegn.getDiaMainCatCode()));
					if(ehfmDiagCategoryMst!=null)
						patientVO.setCatName(ehfmDiagCategoryMst.getCatName());
					else
						patientVO.setCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaSubCatCode()!=null && !ehfmTelephonicRegn.getDiaSubCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setSubCatName(ehfmTelephonicRegn.getDiaSubCatCode());
					EhfmDiagSubCatMst ehfmDiagSubCatMst=genericDao.findById(EhfmDiagSubCatMst.class,EhfmDiagSubCatMstId.class,new EhfmDiagSubCatMstId(ehfmTelephonicRegn.getDiaSubCatCode(),ehfmTelephonicRegn.getDiaCatCode()));
					if(ehfmDiagSubCatMst!=null)
						patientVO.setSubCatName(ehfmDiagSubCatMst.getSubCatName());
					else
						patientVO.setSubCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaDiseaseCode()!=null && !ehfmTelephonicRegn.getDiaDiseaseCode().equalsIgnoreCase("-1"))
				{
					patientVO.setDiseaseName(ehfmTelephonicRegn.getDiaDiseaseCode());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("id.subCatCode",ehfmTelephonicRegn.getDiaSubCatCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        		criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfmTelephonicRegn.getDiaDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    			List<EhfmDiagDiseaseMst> ehfmDiagDiseaseMstList = genericDao.findAllByCriteria(EhfmDiagDiseaseMst.class, criteriaList);
	    			for(EhfmDiagDiseaseMst ehfmDiagDiseaseMst:ehfmDiagDiseaseMstList)
	    			{
	    				patientVO.setDiseaseName(ehfmDiagDiseaseMst.getDiseaseName());
	    			}
				}
				if(ehfmTelephonicRegn.getDiaDisAnatomicalCode()!=null && !ehfmTelephonicRegn.getDiaDisAnatomicalCode().equalsIgnoreCase("-1"))
				{
					patientVO.setDisAnatomicalName(ehfmTelephonicRegn.getDiaDisAnatomicalCode());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("id.disAnatomicalCode",ehfmTelephonicRegn.getDiaDisAnatomicalCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        		criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfmTelephonicRegn.getDiaDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    			List<EhfmDiagDisAnatomicalMst> ehfmDiagDisAnatomicalMstList = genericDao.findAllByCriteria(EhfmDiagDisAnatomicalMst.class, criteriaList);
	    			for(EhfmDiagDisAnatomicalMst ehfmDiagDisAnatomicalMst:ehfmDiagDisAnatomicalMstList)
	    			{
	    				patientVO.setDisAnatomicalName(ehfmDiagDisAnatomicalMst.getDisAnatomicalName());
	    			}
				}
				patientVO.setTeleDocremarks(ehfmTelephonicRegn.getRemarksProcedure()+"~"+ehfmTelephonicRegn.getRemarksDiagnosis());
				}
		} 
		catch (Exception e) {
			glogger.error("Error in PreauthDtlsDao getCaseCommonDtls():"+e.getMessage());
			return patientVO;
		}  
		finally
		{
			session.close();
			factory.close();
		}
		return patientVO;

	}

	/**
	 * @author Kalyan
	 * @param ClassPath-Result class after Query Execution and Query
	 * @method This method is used to Execute HQL Query in Dao
	 * @return Map That Contains Result class path and Result Object  
	 */
	@Override
	public Map<String,Object> executeNormalQuery(String classPath,String query)
		{
		 	Map<String,Object> returnMap=new HashMap<String,Object>();
		 	try
		 		{
		 			List<?> retLst=genericDao.executeHQLQueryList(Class.forName(classPath), query);
		 			if(retLst!=null)
		 				returnMap.put(classPath, retLst);
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			glogger.error("Exception Occured in executeNormalQuery method of PreauthDtlsDao"+e.getMessage());
		 		}
		 	return returnMap;
		}

}
