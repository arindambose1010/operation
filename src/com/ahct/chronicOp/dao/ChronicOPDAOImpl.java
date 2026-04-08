package com.ahct.chronicOp.dao;

import java.util.Date;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.ahct.patient.dao.PatientDaoImpl;
import com.ahct.patient.vo.AttachmentVO;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.chronicOp.vo.ChronicOPVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfChronicAudit;
import com.ahct.model.EhfChronicAuditPK;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfChronicCaseClaim;
import com.ahct.model.EhfChronicCaseDtl;
import com.ahct.model.EhfChronicCaseDtlPK;
import com.ahct.model.EhfChronicPatientDrug;
import com.ahct.model.EhfChronicPatientDrugPK;
import com.ahct.model.EhfChronicPatientDtl;
import com.ahct.model.EhfChronicPatientExamFind;
import com.ahct.model.EhfChronicPatientHosDgnsi;
import com.ahct.model.EhfChronicPatientPerHstry;
import com.ahct.model.EhfChronicPatientTest;
import com.ahct.model.EhfChronicTherapyInvest;
import com.ahct.model.EhfChronicTherapyInvestPK;
import com.ahct.model.EhfCoexChklst;
import com.ahct.model.EhfCoexChklstPK;
import com.ahct.model.EhfCotdChklst;
import com.ahct.model.EhfCotdChklstPK;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfEnrollment;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfmDutyDctrs;
import com.ahct.model.EhfmHospMithraDtls;
import com.ahct.model.EhfmHospMithraDtlsId;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmMedcoDtls;
import com.ahct.model.EhfmMedcoDtlsId;
import com.ahct.model.EhfmMedcoMaster;
import com.ahct.model.EhfmQualMst;
import com.ahct.model.EhfmRelationMst;
import com.ahct.model.EhfmSplstDctrs;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;


public class ChronicOPDAOImpl implements ChronicOPDAO{
	
	private final static Logger GLOGGER = Logger.getLogger ( ChronicOPDAOImpl.class ) ;
	GenericDAO genericDao;
	
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	
	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	public static ConfigurationService getConfigurationService() {
		return configurationService;
	}




	public static void setConfigurationService(
			ConfigurationService configurationService) {
		ChronicOPDAOImpl.configurationService = configurationService;
	}



	public GenericDAO getGenericDao() {
		return genericDao;
	}



	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	
	HibernateTemplate hibernateTemplate;
	GenericDAOQueryCriteria genericDaoqueryCrit;
	
	
	
	
	public GenericDAOQueryCriteria getGenericDaoqueryCrit() {
		return genericDaoqueryCrit;
	}


	public void setGenericDaoqueryCrit(GenericDAOQueryCriteria genericDaoqueryCrit) {
		this.genericDaoqueryCrit = genericDaoqueryCrit;
	}


	

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	
	
	
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<ChronicOPVO> getChronicOPPatients(String hospId,String roleId,String userState) {
		
		
		
		List<ChronicOPVO> chrOpList=new ArrayList<ChronicOPVO>();
		
		List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		
		
		if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
			crList.add(new GenericDAOQueryCriteria("hospCode",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
		}
		
		if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
			
			List<String> li=new ArrayList<String>();
			li.add("CD201");
			li.add("CD202");
			crList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
			
		}else if(userState!=null && !"".equalsIgnoreCase(userState)){
			crList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			
		}
		
		
		if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.Mithra").equalsIgnoreCase(roleId)){
			
			
			
			crList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("CO-MITHRA-CASE-REG"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			
		}
		if((roleId!=null && !"".equalsIgnoreCase(roleId)) && config.getString("Group.Medco").equalsIgnoreCase(roleId)){
		
			List<String> li=new ArrayList<String>();
			li.add(config.getString("CO-MITHRA-CASE-REG"));
			li.add(config.getString("CO-MEDCO-SAVE"));
			
			crList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
			
			
		}
		crList.add(new GenericDAOQueryCriteria("chronicRegnDate","",GenericDAOQueryCriteria.CriteriaOperator.ASC));
		        
         List<EhfChronicCaseDtl>  dtls=genericDao.findAllByCriteria(EhfChronicCaseDtl.class,crList );
         
         
        if(dtls!=null && dtls.size()>0){
        	
        	for(int i=0;i<dtls.size();i++){
        		
        		EhfChronicPatientDtl ecpd=genericDao.findById(EhfChronicPatientDtl.class, String.class, dtls.get(i).getId().getChronicId());
        		
        		if(ecpd!=null){
        			ChronicOPVO vo=new ChronicOPVO();
        			vo.setChronicID(ecpd.getChronicId());
        			vo.setChronicSubID(ecpd.getChronicId().substring((ecpd.getChronicId().lastIndexOf('/')+1),ecpd.getChronicId().length()));
    				vo.setName(ecpd.getName());
    				ecpd.getDistrictCode();
    				if(ecpd.getDistrictCode()!=null && !"".equalsIgnoreCase(ecpd.getDistrictCode())){
						List<GenericDAOQueryCriteria> critList1=new ArrayList<GenericDAOQueryCriteria>();
						critList1.add(new GenericDAOQueryCriteria("id.locId",ecpd.getDistrictCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						List<EhfmLocations>  loc=genericDao.findAllByCriteria(EhfmLocations.class,critList1 );
						if(loc!=null && loc.size()>0){
							vo.setDistrict(loc.get(0).getLocName());	
							
						}
						}
    				vo.setEmployeeNo(ecpd.getEmployeeNo());
    				vo.setAge(ecpd.getAge().toString());
    				vo.setGender(ecpd.getGender());
    				vo.setRegistrationDate(ecpd.getRegHospDate().toString());
    				chrOpList.add(vo);
        			
        		}
        		
        		
        		
        	}
        }
         
				
		return chrOpList;
	}
	@Override
	public List<ChronicOPVO> searchChronicOPPatients(String chronicId,
			String patName, String state, String district, String fromDt,
			String toDate,String userState,String hospId,String roleId) {
		
		List<ChronicOPVO> chronicList=new ArrayList<ChronicOPVO>();
		
		try{
			
			List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
			crList.add(new GenericDAOQueryCriteria("hospCode",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			
			if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
				
				List<String> li=new ArrayList<String>();
				li.add("CD201");
				li.add("CD202");
				crList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
				
			}else if(userState!=null && !"".equalsIgnoreCase(userState)){
				crList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
				
			}
			
			if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.Mithra").equalsIgnoreCase(roleId)){
				
				
				
				crList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("CO-MITHRA-CASE-REG"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
				
			}
			if((roleId!=null && !"".equalsIgnoreCase(roleId)) && config.getString("Group.Medco").equalsIgnoreCase(roleId)){
			
				List<String> li=new ArrayList<String>();
				li.add(config.getString("CO-MITHRA-CASE-REG"));
				li.add(config.getString("CO-MEDCO-SAVE"));
				
				crList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
				
				
			}
			
			        
	         List<EhfChronicCaseDtl>  dtls=genericDao.findAllByCriteria(EhfChronicCaseDtl.class,crList );
	         
	         if(dtls!=null && dtls.size()>0){
	        	 
			
			 
			 for(int i=0;i<dtls.size();i++){
				 List<GenericDAOQueryCriteria> critList=new ArrayList<GenericDAOQueryCriteria>();
				 
				 critList.add(new GenericDAOQueryCriteria("chronicId",dtls.get(i).getId().getChronicId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 
				 if(chronicId!=null && !"".equalsIgnoreCase(chronicId)){
					 critList.add(new GenericDAOQueryCriteria("chronicId",chronicId.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
					 
				 }
				 
			 
			if(hospId!=null && !"".equalsIgnoreCase(hospId)){
				critList.add(new GenericDAOQueryCriteria("regHospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
			}
			 
			 
			 
			 
			 if(patName!=null && !"".equalsIgnoreCase(patName)){
					critList.add(new GenericDAOQueryCriteria("name",patName.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
					
				}
			 
			 if(state!=null && !"-1".equalsIgnoreCase(state)){
					
					
					critList.add(new GenericDAOQueryCriteria("state",state,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					
				}
				
				if(district!=null &&  !"-1".equalsIgnoreCase(district)){
					
					critList.add(new GenericDAOQueryCriteria("districtCode",district,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					
				}
		       if((fromDt!=null && !"".equalsIgnoreCase(fromDt)) && (toDate!=null && !"".equalsIgnoreCase(toDate)) ){
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					Date frmDt=formatter.parse(fromDt);
					Date toDt=formatter.parse(toDate);
					
			
					
					critList.add(new GenericDAOQueryCriteria("regHospDate",frmDt,GenericDAOQueryCriteria.CriteriaOperator.GE));
					
					critList.add(new GenericDAOQueryCriteria("regHospDate",toDt,GenericDAOQueryCriteria.CriteriaOperator.LE));
					
					critList.add(new GenericDAOQueryCriteria("regHospDate","",GenericDAOQueryCriteria.CriteriaOperator.ASC));
				}
			 
		       List<EhfChronicPatientDtl>  chrList=genericDao.findAllByCriteria(EhfChronicPatientDtl.class,critList );
		
		      
		       for(int j=0;j<chrList.size();j++){
		    	   ChronicOPVO vo=new ChronicOPVO();
		    	   vo.setChronicID(chrList.get(j).getChronicId());
		    	   vo.setChronicSubID(chrList.get(j).getChronicId().substring((chrList.get(j).getChronicId().lastIndexOf('/')+1),chrList.get(j).getChronicId().length()));
		    	  
		    	   vo.setEmployeeNo(chrList.get(j).getEmployeeNo());
		    	   vo.setName(chrList.get(j).getName());
		    	   vo.setAge(chrList.get(j).getAge().toString());
		    	   vo.setGender(chrList.get(j).getGender());
		    	   if(chrList.get(j).getDistrictCode()!=null && !"".equalsIgnoreCase(chrList.get(j).getDistrictCode())){
						List<GenericDAOQueryCriteria> critList1=new ArrayList<GenericDAOQueryCriteria>();
						critList1.add(new GenericDAOQueryCriteria("id.locId",chrList.get(j).getDistrictCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						List<EhfmLocations>  loc=genericDao.findAllByCriteria(EhfmLocations.class,critList1 );
						if(loc!=null && loc.size()>0){
							vo.setDistrict(loc.get(0).getLocName());	
							
						}
						}
		    	   
		    	   vo.setRegistrationDate(chrList.get(j).getRegHospDate().toString());
		    	   chronicList.add(vo);
		       }
		       
	         }
	         }
		       
		}catch(Exception e){
			
			e.printStackTrace();
		}
			
		
			return chronicList;	
	}
	
	/**
	 * ;
	 * @return List<LabelBean> relationList
	 * @function This Method is Used For getting Relations List from RelationMst table
	 */
	@Override
	public List<LabelBean> getRelations() throws Exception {
		List<LabelBean> relationList= new ArrayList<LabelBean>();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
		criteriaList.add(new GenericDAOQueryCriteria("relationName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		List<EhfmRelationMst> relationMstList=genericDao.findAllByCriteria(EhfmRelationMst.class,criteriaList);
		for(EhfmRelationMst relationMst:relationMstList)
		{
			LabelBean labelBean=new LabelBean();
			labelBean.setID(relationMst.getRelationId().toString());
			labelBean.setVALUE(relationMst.getRelationName());
			relationList.add(labelBean);
		}

		return relationList;
	}
	
	@Override
	public List<ChronicOPVO> getChronicClaimCases(String roleId,String hospId,String userState) {
		
		
		List<ChronicOPVO> chrList=new ArrayList<ChronicOPVO>();
		
		List<GenericDAOQueryCriteria> critList=new ArrayList<GenericDAOQueryCriteria>();
		
		if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
			
			critList.add(new GenericDAOQueryCriteria("hospCode",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		}
		
if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
			
			List<String> li=new ArrayList<String>();
			li.add("CD201");
			li.add("CD202");
			critList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
			
		}else if(userState!=null && !"".equalsIgnoreCase(userState)){
			critList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			
		}
		
     if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.Mithra").equalsIgnoreCase(roleId)){
			List<String> li=new ArrayList<String>();
			
			li.add(config.getString("CO-MEDCO-CLAIM-INTAIATED"));
			
    	 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
    	
		}
		
		
		
     if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.Medco").equalsIgnoreCase(roleId)){
    	 
    	 List<String> li=new ArrayList<String>();
			
			li.add(config.getString("CO-MEDCO-SCREENING"));
			li.add(config.getString("COTD-REC-PEN"));
			li.add(config.getString("COCH-PEN"));
    	 
      	 
    	 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
    	
    	 
    	 
    	 
     }
		
		
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.COEX").equalsIgnoreCase(roleId)){
    	 
    	 critList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("CO-MITHRA-VERIFIED"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    	
    	 
    	 
     }
	
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.COTD").equalsIgnoreCase(roleId)){
    	 List<String> li=new ArrayList<String>();
    	 li.add(config.getString("COTD-PENDING-UPDATED-BY-MEDCO"));
    	 li.add(config.getString("COEX-VERIFIED"));
   	 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
   	
   	 
   	 
    }
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.COCH").equalsIgnoreCase(roleId)){
    	List<String> li=new ArrayList<String>();
    	li.add(config.getString("COCH-PENDING-UPDATED-BY-MEDCO"));
    	li.add(config.getString("COTD-REC-APPRV"));
    	li.add(config.getString("CO-CLAIM-SENT-BACK-TO-CH"));
      	 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
      	
      	 
      	 
       }
    
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("GROUP.COACO").equalsIgnoreCase(roleId)){
     	 
     	 critList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("COCH-APPRV"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
     	
     	 
     	 
      }
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("GROUP.COEO").equalsIgnoreCase(roleId)){
    	 
    	 critList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("COACO-VERIFY"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    	
    	 
    	 
     }
		
    critList.add(new GenericDAOQueryCriteria("chronicRegnDate","",GenericDAOQueryCriteria.CriteriaOperator.ASC));
		
		List<EhfChronicCaseDtl>  dtls=genericDao.findAllByCriteria(EhfChronicCaseDtl.class,critList );
		
		if(dtls!=null){
			
			for(int i=0;i<dtls.size();i++){
			
				
			EhfChronicPatientDtl patDtl=new EhfChronicPatientDtl();
			patDtl=genericDao.findById(EhfChronicPatientDtl.class, String.class,dtls.get(i).getId().getChronicId());
			
			if(patDtl!=null){
				ChronicOPVO vo=new ChronicOPVO();
		
				vo.setChronicID(dtls.get(i).getId().getChronicNo());
				int n=dtls.get(i).getId().getChronicNo().indexOf("/");
				int n1=dtls.get(i).getId().getChronicNo().substring(n+1, dtls.get(i).getId().getChronicNo().length()).toString().indexOf("/");
				n=n+n1;
				vo.setChronicSubID(dtls.get(i).getId().getChronicNo().substring(n+2,dtls.get(i).getId().getChronicNo().length()));
				vo.setName(patDtl.getName());
				vo.setEmployeeNo(patDtl.getEmployeeNo());
				vo.setAge(patDtl.getAge().toString());
				vo.setGender(patDtl.getGender());
				
				
				if(patDtl.getDistrictCode()!=null && !"".equalsIgnoreCase(patDtl.getDistrictCode())){
				List<GenericDAOQueryCriteria> critList1=new ArrayList<GenericDAOQueryCriteria>();
				critList1.add(new GenericDAOQueryCriteria("id.locId",patDtl.getDistrictCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfmLocations>  loc=genericDao.findAllByCriteria(EhfmLocations.class,critList1 );
				if(loc!=null && loc.size()>0){
					vo.setDistrict(loc.get(0).getLocName());	
					
				}
				}
				vo.setRegistrationDate(patDtl.getRegHospDate().toString());
				chrList.add(vo);
				
				
			}
			
				
			
		}
		}
		return chrList;
	}


	@Override
	public String getHospitalID(String userId,String roleId) {

           String hospId=null;
		List<GenericDAOQueryCriteria> critList=new ArrayList<GenericDAOQueryCriteria>();
		if("GP2".equalsIgnoreCase(roleId)){
		critList.add(new GenericDAOQueryCriteria("id.medcoId",userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		critList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfmMedcoDtls> medcoDtl=genericDao.findAllByCriteria(EhfmMedcoDtls.class,critList );
		
		if(medcoDtl!=null && medcoDtl.size()>0){
			
			hospId=medcoDtl.get(0).getId().getHospId();
		}
		}
		
		if("GP1".equalsIgnoreCase(roleId)){
			critList.add(new GenericDAOQueryCriteria("id.mithraId",userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			critList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmHospMithraDtls> mithraDtl=genericDao.findAllByCriteria(EhfmHospMithraDtls.class,critList );
			if(mithraDtl!=null && mithraDtl.size()>0){
				
				hospId=	mithraDtl.get(0).getId().getHospId();
			}
			
		}
		return hospId;
	}


		
	
	/**
	 * ;
	 * @param String userId
	 * @param String roleId
	 * @return List<LabelBean> hospitalList
	 * @function This Method is Used For getting Hospital List for given user and role
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LabelBean> getHospital(String userId,String roleId)throws Exception {
		List<LabelBean> hospitalList = new ArrayList<LabelBean>();
		SessionFactory factory=null;
		Session session=null;
		try
		{
			factory= hibernateTemplate.getSessionFactory();
			session=factory.openSession();
			StringBuffer query = new StringBuffer();
			StringBuffer query1=new StringBuffer();
			Query hQuery;
			List hospList=null;
			ArrayList<String> hospIdList=null;
			Iterator hospItr=null;
			List resultList=null;
			Iterator resultItr=null;
			if(roleId.equals(config.getString("Group.Mithra")))
			{
				query.append("from EhfmHospMithraDtls amu where amu.id.mithraId='"+userId+"' and amu.endDt="+null);
				hQuery=session.createQuery(query.toString());
				hospList=hQuery.list();
				if(hospList.size()>0)
				{
					hospIdList=new ArrayList<String>();
					hospItr=hospList.iterator();
					while(hospItr.hasNext())
					{
						EhfmHospMithraDtls ehfmHospMithraDtls=(EhfmHospMithraDtls)hospItr.next();
						EhfmHospMithraDtlsId ehfmHospMithraDtlsId=ehfmHospMithraDtls.getId();
						GLOGGER.info("hospIds "+ehfmHospMithraDtlsId.getHospId());
						hospIdList.add(ehfmHospMithraDtlsId.getHospId());
					}
				}
				else
				{
					GLOGGER.info("No associated hosp for this user");
					System.out.println("No associated hosp for this user");
				}
			}
			else if(roleId.equals(config.getString("Group.Medco")))
			{
				query.append("from EhfmMedcoDtls anu where anu.id.medcoId='"+userId+"' and anu.endDate="+null);

				hQuery=session.createQuery(query.toString());
				hospList=hQuery.list();
				if(hospList.size()>0)
				{
					hospIdList=new ArrayList();
					hospItr=hospList.iterator();
					while(hospItr.hasNext())
					{
						EhfmMedcoDtls ehfmMedcoDetails=(EhfmMedcoDtls)hospItr.next();
						EhfmMedcoDtlsId ehfmMedcoDetailsId=ehfmMedcoDetails.getId();
						GLOGGER.info("hospIds "+ehfmMedcoDetailsId.getHospId());
						hospIdList.add(ehfmMedcoDetailsId.getHospId());
					}
				}
				else
				{
					System.out.println("No associated hosp for this user");
				}
			}
			String activeYn=config.getString("ActiveYn");
			if(hospIdList!=null)
			{
	    		//query1.append("from EhfmHospitals ah where ah.hospId in (:param) and ah.hospActiveYN='"+activeYn+"'");
	    		query1.append("from EhfmHospitals ah where ah.hospId in (:param) ");
				Query hQuery1=session.createQuery(query1.toString());
				hQuery1.setParameterList("param", hospIdList);
				resultList=hQuery1.list();
				if(resultList.size()>0)
					GLOGGER.info("Hosp details retrieved");
				resultItr=resultList.iterator();
				while(resultItr.hasNext())
				{
					EhfmHospitals ehfmHospitals=(EhfmHospitals)resultItr.next();
					LabelBean labelBean=new LabelBean();
					labelBean.setID(ehfmHospitals.getHospId());
					labelBean.setVALUE(ehfmHospitals.getHospName()+","+ehfmHospitals.getHouseNumber()+","+ehfmHospitals.getHospCity());
					labelBean.setNATURE(ehfmHospitals.getHospType()+"");
					hospitalList.add(labelBean);
				}
			}   		
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getHospital() in ChronicOPDAOImpl class."+e.getMessage());
		}
		finally
		{
			session.close();
			factory.close();
		}
		return hospitalList;
	}
	public String getHospActiveStatus(String userId, String roleId)
	{
		String actStatus="";
		StringBuffer query= new StringBuffer();
		try
		{
			if(roleId.equals(config.getString("Group.Mithra")))
			{
				query.append(" select h.hospActiveYN as ID from EhfmHospitals h, EhfmHospMithraDtls d ");
				query.append(" where d.id.mithraId=? and d.endDt is null and h.hospId= d.id.hospId ");
				String args[]= new String[1];
				args[0]= userId;
				
				List<LabelBean> resultLst= genericDao.executeHQLQueryList(LabelBean.class, query.toString(), args);
				if(resultLst!=null && resultLst.size()>0)
				{
					actStatus= resultLst.get(0).getID();
				}
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Error in method getHospActiveStatus() of ChronicOPDAOImpl "+e.getMessage());
		}
		return actStatus;
	}
	
	
	/**
	 * ;
	 * @param ChronicOPVO chronicOPVO
	 * @return chronicOPVO
	 * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
	 */	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ChronicOPVO retrieveCardDetails(ChronicOPVO chronicOPVO) throws Exception {
		ChronicOPVO chronicOPVO1=null;
		EhfEnrollmentFamily ehfEnrollmentFamily=null;
		SessionFactory factory=null;
		Session session=null;
		Query hQuery=null;
		String cardValidStatus=config.getString("Card.ValidStatus");
		try
		{
			factory= hibernateTemplate.getSessionFactory();
			session=factory.openSession();
			StringBuffer query = new StringBuffer();
			query.append("from EhfEnrollmentFamily ef where ef.ehfCardNo='"+chronicOPVO.getCardNo()+"' and ef.enrollStatus  in ("+cardValidStatus.replace("~", ",")+")");
			hQuery=session.createQuery(query.toString());
			if(hQuery.list().size()>0)
			{
				ehfEnrollmentFamily=(EhfEnrollmentFamily) hQuery.list().get(0);
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in retrieveCardDetails() in ChronicOPDAOImpl class."+e.getMessage());
		}
		finally
		{
			session.close();
			factory.close();
		}
		if(ehfEnrollmentFamily!=null)
		{
			//To check if card is deactivated due to patient's death
			if(ehfEnrollmentFamily.getEnrollStatus()!=null && !"".equalsIgnoreCase(ehfEnrollmentFamily.getEnrollStatus())
					&& !(config.getString("inactivate_card_death_status").equalsIgnoreCase(ehfEnrollmentFamily.getEnrollStatus())))
			{
				String enrollParntId=ehfEnrollmentFamily.getEnrollPrntId();
				StringBuffer query1=new StringBuffer(); 
				List<String> l=null;
				query1.append("from EhfEnrollment where enrollPrntId='"+enrollParntId+"'");
				if(chronicOPVO.getCardType()!=null && !"".equalsIgnoreCase(chronicOPVO.getCardType()))
				{
					if(chronicOPVO.getCardType().equalsIgnoreCase("E"))
					{
		
						query1.append(" and empType='"+config.getString("Role.Employee")+"'");
					}
					else if(chronicOPVO.getCardType().equalsIgnoreCase("P"))
					{
						query1.append(" and empType in('"+config.getString("Role.Pensioner")+"','"+config.getString("Role.ServicePensioner")+"')");
					}
				}
	
				try{
					if ( !hibernateTemplate.getSessionFactory ().getCurrentSession ().isOpen () )
					{
						throw new RuntimeException ( "No Session open for executing the query " + query1 ) ;
					}
					else
						l = hibernateTemplate.getSessionFactory ().getCurrentSession().createQuery ( query1.toString() ).list();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					GLOGGER.error("Exception occurred in retrieveCardDetails() while creating session factory instance in ChronicOPDAOImpl class."+e.getMessage());
				}
				Iterator ite=l.iterator();
				EhfEnrollment ehfEnrollment=null;
				if(ite.hasNext())
				{
					ehfEnrollment= (EhfEnrollment)ite.next();
				}
				if(ehfEnrollment!=null)
				{
					chronicOPVO1=new ChronicOPVO();
					
					if(ehfEnrollment.getScheme()!=null && !"".equalsIgnoreCase(ehfEnrollment.getScheme()))
					{
						if(chronicOPVO.getSchemeId()!=null && !"".equalsIgnoreCase(chronicOPVO.getSchemeId())
								&& !config.getString("COMMON").equalsIgnoreCase(chronicOPVO.getSchemeId()))
							{
								if(!ehfEnrollment.getScheme().equalsIgnoreCase(chronicOPVO.getSchemeId()))
									{
										chronicOPVO1=new ChronicOPVO();
										chronicOPVO1.setMsg(config.getString("invalid_state_remarks"));
										return chronicOPVO1;
									}
							}
					}
					
					chronicOPVO1.setEmpCode(ehfEnrollment.getEmpCode());
					chronicOPVO1.setDateOfBirth(ehfEnrollmentFamily.getEnrollDob().toString());
					chronicOPVO1.setGender(Character.toString(ehfEnrollmentFamily.getEnrollGender()));
					chronicOPVO1.setFirstName(ehfEnrollmentFamily.getEnrollName());
					chronicOPVO1.setRelation(ehfEnrollmentFamily.getEnrollRelation());
					chronicOPVO1.setCaste(ehfEnrollment.getEmpCommu());
					chronicOPVO1.setContactNo(ehfEnrollment.getEmpHphone());
					chronicOPVO1.setAddr1(ehfEnrollment.getEmpHno());
					chronicOPVO1.setAddr2(ehfEnrollment.getEmpHstreetno());
					chronicOPVO1.setState(ehfEnrollment.getEmpHstate());
					chronicOPVO1.setDistrictCode(ehfEnrollment.getEmpHdist());
					chronicOPVO1.setMdl_mpl(ehfEnrollment.getEmpHmandMunciSel());
					chronicOPVO1.setMandalCode(ehfEnrollment.getEmpHmandMunci());
					chronicOPVO1.setVillageCode(ehfEnrollment.getEmpHvillTwn());
					
					chronicOPVO1.setDeptHod(ehfEnrollment.getDeptHod());
					chronicOPVO1.setPostDist(ehfEnrollment.getPostDist());
					chronicOPVO1.setDdoOffUnit(ehfEnrollment.getDdoOffUnit());
					chronicOPVO1.setPostDdoCode(ehfEnrollment.getPostDdoCode());
					
					String slab=getSlabType(ehfEnrollment.getPayScale());
					if(slab!=null)
					{
						chronicOPVO1.setSlab(slab);
					}
					else
					{
						chronicOPVO1.setSlab(config.getString("Slab.SemiPrivateWard"));
					}
					if(ehfEnrollment.getScheme()!=null)
					{
						chronicOPVO1.setSchemeId(ehfEnrollment.getScheme());
					}
					else
					{
						chronicOPVO1.setSchemeId(config.getString("Scheme.ap"));
					}
					if(ehfEnrollment.getEmpHemail()!=null)
					{
						chronicOPVO1.seteMailId(ehfEnrollment.getEmpHemail());
					}
	
					if(chronicOPVO.getCardNo().endsWith("01"))
					{
						chronicOPVO1.setMaritalStatus(ehfEnrollment.getEmpMaritalStat());
						if(chronicOPVO.getCardType()!=null && !"".equalsIgnoreCase(chronicOPVO.getCardType()) && chronicOPVO.getCardType().equalsIgnoreCase("E"))
						{
							String service=ehfEnrollment.getService();
							String categoryName=ehfEnrollment.getServDsgn();
							String hod=ehfEnrollment.getDeptHod();
							if(ehfEnrollment.getDeptDesignation()!=null && !"".equals(ehfEnrollment.getDeptDesignation()))
							{
								Long desgnId=Long.parseLong(ehfEnrollment.getDeptDesignation());
	
								List<LabelBean> designationList= new ArrayList<LabelBean>();
								StringBuffer query =null;
								try
								{
									query = new StringBuffer();
									query.append("select distinct a.deptDesignation as VALUE from EhfDesignationMst a where a.id.hod='"+hod+"' and a.id.service='"+service+"' and a.id.categoryName='"+categoryName+"' and a.id.dsgnId="+desgnId);     
									designationList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
									if(designationList!=null && designationList.size()>0)
									{
										chronicOPVO1.setOccupationCd(designationList.get(0).getVALUE());
									}
								}
								catch(Exception e)
								{
									GLOGGER.error("Exception Occurred in retrieveCardDetails() in ChronicOPDAOImpl class."+e.getMessage());
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
							chronicOPVO1.setPhoto(ehfEmployeeDocAttach.getSavedName());
						}
					}
					else
					{
						chronicOPVO1.setPhoto(ehfEnrollmentFamily.getEnrollPhoto());
					}
				}
			}
			else
			{
				chronicOPVO1=new ChronicOPVO();
				chronicOPVO1.setMsg(config.getString("inactivate_card_death_remarks"));
			}
		}
		return chronicOPVO1;
	}
	
	/**
	 * ;
	 * @param String payGrade
	 * @return String slab
	 * @function This Method is Used For Retrieving Slab type for given payGrade
	 */	
	@Override
	public String getSlabType(String payGrade) throws Exception {
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
			GLOGGER.error("Exception Occurred in getSlabType() in ChronicOPDAOImpl class."+e.getMessage());
		}
		return slabType;
	} 
	
	
	
	
	/**
	 * ;
	 * @param String hospId
	 * @return String hospName
	 * @function This Method is Used For getting Hospital Name
	 */
	@Override
	public String getHospName(String hospId) throws Exception {
		String hospitalName=null;
		try
		{
			EhfmHospitals asrimHospitals=genericDao.findById(EhfmHospitals.class,String.class,hospId);
			hospitalName=asrimHospitals.getHospName();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getHospName() in ChronicOPDAOImpl class."+e.getMessage());
		}
		return hospitalName;
	}
	@Override
	/**
	 * ;
	 * @param ChronicOPVO chronicOPVO
	 * @return int noOfRecords
	 * @function This Method is Used For Registering Direct Patient
	 */
	public int registerPatient(ChronicOPVO chronicOPVO) throws Exception {
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date crtDt;
		Date dateOfBirth;
		Date cardIssueDt;
		try {
			EhfChronicPatientDtl ehfChronicPatientDtl = null;
			ehfChronicPatientDtl= new EhfChronicPatientDtl();
			crtDt=sdfw.parse(chronicOPVO.getCrtDt());

			if(chronicOPVO.getCardIssueDt()!=null && !chronicOPVO.getCardIssueDt().equalsIgnoreCase(""))
			{
				cardIssueDt = sdf.parse(chronicOPVO.getCardIssueDt());
				ehfChronicPatientDtl.setCardIssueDt(cardIssueDt);
			}
			if(chronicOPVO.getDateOfBirth()!=null && !chronicOPVO.getDateOfBirth().equalsIgnoreCase(""))
			{
				dateOfBirth = sdf.parse(chronicOPVO.getDateOfBirth());
				ehfChronicPatientDtl.setEnrollDob(dateOfBirth);
			}
			
			
			BigDecimal contactNo=null;
			BigDecimal age=null;
			BigDecimal ageMonths=null;
			BigDecimal ageDays=null;
			
			if(chronicOPVO.getContactNo()!=null && !"".equalsIgnoreCase(chronicOPVO.getContactNo()))
				contactNo=new BigDecimal(chronicOPVO.getContactNo());
			if(chronicOPVO.getAge()!=null && !"".equalsIgnoreCase(chronicOPVO.getAge()))
				age=new BigDecimal(chronicOPVO.getAge());
			if(chronicOPVO.getAgeMonths()!=null && !"".equalsIgnoreCase(chronicOPVO.getAgeMonths()))
				ageMonths=new BigDecimal(chronicOPVO.getAgeMonths());
			if(chronicOPVO.getAgeDays()!=null && !"".equalsIgnoreCase(chronicOPVO.getAgeDays()))
				ageDays=new BigDecimal(chronicOPVO.getAgeDays());
			
			ehfChronicPatientDtl.setChronicId(chronicOPVO.getPatientId());
			ehfChronicPatientDtl.setCardType(chronicOPVO.getCardType());
			ehfChronicPatientDtl.setEmployeeNo(chronicOPVO.getEmpCode());
			ehfChronicPatientDtl.setCardNo(chronicOPVO.getRationCard());
			ehfChronicPatientDtl.setFamilyHead(chronicOPVO.getFamilyHead());
			ehfChronicPatientDtl.setChildYn(chronicOPVO.getChild_yn());
			ehfChronicPatientDtl.setCrtDt(crtDt);
			ehfChronicPatientDtl.setCrtUsr(chronicOPVO.getCrtUsr());
			ehfChronicPatientDtl.setName(chronicOPVO.getFirstName());
			ehfChronicPatientDtl.setGender(chronicOPVO.getGender());
			ehfChronicPatientDtl.setOccupationCd(chronicOPVO.getOccupationCd());
			ehfChronicPatientDtl.setContactNo(contactNo);
			ehfChronicPatientDtl.setRelation(chronicOPVO.getRelation());
			ehfChronicPatientDtl.setAge(age);
			ehfChronicPatientDtl.setAgeMonths(ageMonths);
			ehfChronicPatientDtl.setAgeDays(ageDays);
			ehfChronicPatientDtl.setCaste(chronicOPVO.getCaste());
			ehfChronicPatientDtl.setMaritalstatus(chronicOPVO.getMaritalStatus());
			ehfChronicPatientDtl.setSlab(chronicOPVO.getSlab());
			if(chronicOPVO.geteMailId()!=null && !chronicOPVO.geteMailId().equals(""))
			{
				ehfChronicPatientDtl.setEmailid(chronicOPVO.geteMailId());
			}
			ehfChronicPatientDtl.setHouseNo(chronicOPVO.getAddr1());
			ehfChronicPatientDtl.setStreet(chronicOPVO.getAddr2());
			ehfChronicPatientDtl.setState(chronicOPVO.getState());
			ehfChronicPatientDtl.setDistrictCode(chronicOPVO.getDistrictCode());
			ehfChronicPatientDtl.setcMdlMpl(chronicOPVO.getMdl_mpl());
			ehfChronicPatientDtl.setMandalCode(chronicOPVO.getMandalCode());
			ehfChronicPatientDtl.setVillageCode(chronicOPVO.getVillageCode());
			ehfChronicPatientDtl.setPinCode(chronicOPVO.getPincode());
			//ehfChronicPatientDtl.setSrcRegistration(chronicOPVO.getSrcRegistration());
			//ehfChronicPatientDtl.setPatientIpop(chronicOPVO.getPatient_ipop());
			//ehfChronicPatientDtl.setPhaseId(Long.parseLong(chronicOPVO.getPhaseId()));
			//ehfChronicPatientDtl.setRenewal(Long.parseLong(chronicOPVO.getRenewal()));
			ehfChronicPatientDtl.setSchemeId(chronicOPVO.getSchemeId());
			//ehfChronicPatientDtl.setSourceId(Long.parseLong(chronicOPVO.getCid()));

			ehfChronicPatientDtl.setcHouseNo(chronicOPVO.getPermAddr1());
			ehfChronicPatientDtl.setcStreet(chronicOPVO.getPermAddr2());
			ehfChronicPatientDtl.setcState(chronicOPVO.getC_state());
			ehfChronicPatientDtl.setcDistrictCode(chronicOPVO.getC_district_code());
			ehfChronicPatientDtl.setMdlMpl(chronicOPVO.getC_mdl_mpl());
			ehfChronicPatientDtl.setcMandalCode(chronicOPVO.getC_mandal_code());
			ehfChronicPatientDtl.setcVillageCode(chronicOPVO.getC_village_code());
			ehfChronicPatientDtl.setcPinCode(chronicOPVO.getC_pin_code());

			ehfChronicPatientDtl.setRegHospId(chronicOPVO.getRegHospId());
			ehfChronicPatientDtl.setRegHospDate(crtDt);
			
			ehfChronicPatientDtl.setPostDist(chronicOPVO.getPostDist());
			ehfChronicPatientDtl.setStoCode(chronicOPVO.getDdoOffUnit());
			ehfChronicPatientDtl.setDdoCode(chronicOPVO.getPostDdoCode());
			ehfChronicPatientDtl.setDeptHod(chronicOPVO.getDeptHod());
			
			EhfChronicCaseDtl ehfChronicCaseDtl= null; 
			String chronicNo=chronicOPVO.getPatientId()+"/1";
			EhfChronicCaseDtlPK casePk=new EhfChronicCaseDtlPK(chronicOPVO.getPatientId(),chronicNo);
			//casePk.setChronicId(chronicOPVO.getPatientId());
			ehfChronicCaseDtl=new EhfChronicCaseDtl();
			//ehfChronicCaseDtl.setChronicId(chronicOPVO.getPatientId());
			ehfChronicCaseDtl.setId(casePk);
			ehfChronicCaseDtl.setHospCode(chronicOPVO.getRegHospId());
			ehfChronicCaseDtl.setChronicStatus(config.getString("CASE.ChronicCaseRegistered"));
			ehfChronicCaseDtl.setSchemeId(chronicOPVO.getSchemeId());
			ehfChronicCaseDtl.setChronicRegnDate(crtDt);
			ehfChronicCaseDtl.setCrtUsr(chronicOPVO.getCrtUsr());
			ehfChronicCaseDtl.setCrtDt(crtDt);
			
			
			/*if("Telephonic Registration".equalsIgnoreCase(chronicOPVO.getRegState()))
			{
				ehfChronicPatientDtl.setIntimationId(chronicOPVO.getTelephonicId());
				EhfTelephonicRegn ehfmTelephonicRegn=genericDao.findById(EhfTelephonicRegn.class, String.class, chronicOPVO.getTelephonicId());
				ehfmTelephonicRegn.setPatientId(chronicOPVO.getPatientId());
				ehfmTelephonicRegn.setEmployeeNo(chronicOPVO.getEmpCode());
				ehfmTelephonicRegn.setLstUpdDt(crtDt);
				ehfmTelephonicRegn.setLstUpdUsr(chronicOPVO.getCrtUsr());
				genericDao.save(ehfmTelephonicRegn);
			}*/
		//EhfPatJbpmProcess ehfPatJbpmProcess= new EhfPatJbpmProcess();
		//ehfPatJbpmProcess.setPatientId(chronicOPVO.getPatientId());
		//ehfPatJbpmProcess.setProcessInstanceId(chronicOPVO.getProcessInstanceId());
		//genericDao.save(ehfPatJbpmProcess);

		/*if(ehfChronicPatientDtl!=null){
			return 1;
		}*/
		try 
		{
			ehfChronicPatientDtl = genericDao.save(ehfChronicPatientDtl);
			if(ehfChronicPatientDtl!=null)
			{
				ehfChronicCaseDtl = genericDao.save(ehfChronicCaseDtl);
				if(ehfChronicCaseDtl!=null)
					return 1;
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		} 
		catch (ParseException e) {
			GLOGGER.error("Error occured in registerPatient() in ChronicOPDAOImpl class."+e.getMessage());
			return 0;
		}
		return 1;
	}
	
	/**
	 * ;
	 * @param String chronicId
	 * @return EhfChronicPatientDtl ehfChronicPatientDtl
	 * @function This Method is Used For retrieving PatientDetails for given chronicId
	 */
	@Override
	public EhfChronicPatientDtl getPatientDetails(String chronicId) throws Exception {
		EhfChronicPatientDtl ehfChronicPatientDtl=new EhfChronicPatientDtl();
		try{
			ehfChronicPatientDtl=genericDao.findById(EhfChronicPatientDtl.class,String.class,chronicId);
		}
		catch(Exception e){
			GLOGGER.error("Exception Occurred in getPatientDetails() in ChronicOPDAOImpl class."+e.getMessage());
		}
		return ehfChronicPatientDtl;
	}
	
	/**
	 * ;
	 * @param String relationId
	 * @return String relationName
	 * @function This Method is Used For getting relationName for given relationId from RelationMst table
	 */
	@Override
	public String getRelationName(String relationId) throws Exception {
		String relationName=null;
		//Long relationIdVal=new Long(relationId);
		Integer relationIdVal=Integer.valueOf(relationId);
		EhfmRelationMst relationMst=genericDao.findById(EhfmRelationMst.class, Integer.class,relationIdVal);
		relationName=relationMst.getRelationName();
		return relationName;
	}
	
	
	/**
	 * ;
	 * @param String locId
	 * @return String locName
	 * @function This Method is Used For getting Location Name for given Location Id
	 */
	@Override
	public String getLocationName(String locId) throws Exception {
		String locName=null;
		try
		{
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("id.locId",locId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmLocations> ehfmLocations = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
			for(EhfmLocations el:ehfmLocations)
			{
				locName=el.getLocName();
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getLocationName() in ChronicOPDAOImpl class."+e.getMessage());
		}
		return locName;
	}
	
	/**
	 * ;
	 * @param String cardNo
	 * @return String photoUrl
	 * @function This Method is Used For getting photoUrl for given cardNo
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String getPatientPhoto(String cardNo) throws Exception {
		String photoUrl=null;
		String enrollPrntId=null;
		List<String> l=null;
		StringBuffer query = new StringBuffer();
		//String enrollDdoReject=config.getString("ENROLLMENT_DDO_REJECTION");
		//String cardValidStatus=config.getString("Card.ValidStatus");
		//query.append("select ef.enrollPrntId,ef.enrollPhoto from EhfEnrollmentFamily ef where ef.ehfCardNo='"+cardNo+"' and ef.enrollStatus not in ("+cardValidStatus.replace("~", ",")+") and ef.displayFlag||ef.rejected <>'NN'");
		query.append("select ef.enrollPrntId,ef.enrollPhoto from EhfEnrollmentFamily ef where ef.ehfCardNo='"+cardNo+"'");
		try{
		    if ( !hibernateTemplate.getSessionFactory ().getCurrentSession ().isOpen () )
		    {
		      throw new RuntimeException ( "No Session open for executing the query " + query ) ;
		    }
		    else
		    	l = hibernateTemplate.getSessionFactory ().getCurrentSession().createQuery ( query.toString() ).list();
		    }
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getPatientPhoto() while creating session factory instance in ChronicOPDAOImpl class."+e.getMessage());
		}
		@SuppressWarnings("rawtypes")
		Iterator ite=l.iterator();
		if(ite.hasNext())
		{
			Object[] obj= (Object[])ite.next();
			if(obj[0]!=null)
			{
				enrollPrntId=obj[0].toString();
			}
			if(obj[1]!=null)
			{
				photoUrl=obj[1].toString();
			}
		}
		System.out.println(photoUrl);
		if(cardNo.endsWith("01"))
		{
			Map<String, Object> photoMap=new HashMap<String, Object>();
			photoMap.put("employeeParntId", enrollPrntId);
			photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
			photoMap.put("attachactiveYN","Y");
			EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
			photoUrl=ehfEmployeeDocAttach.getSavedName();
		}
		System.out.println(photoUrl);
		return photoUrl;
	}


	@Override
	public List<ChronicOPVO> searchChronicClaimCases(String chronicId,
			String patName, String state, String district, String fromDt,
			String toDate, String userState, String hospId, String roleId) {
	
		List<ChronicOPVO> chronicList=new ArrayList<ChronicOPVO>();
		try{

		
		List<GenericDAOQueryCriteria> critList=new ArrayList<GenericDAOQueryCriteria>();
		
		if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
			
			List<String> li=new ArrayList<String>();
			li.add("CD201");
			li.add("CD202");
			critList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
			
		}else if(userState!=null && !"".equalsIgnoreCase(userState)){
			critList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			
		}
		
		if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
			
			critList.add(new GenericDAOQueryCriteria("hospCode",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		}
		
		if(chronicId!=null &&!"".equalsIgnoreCase(chronicId)){
			critList.add(new GenericDAOQueryCriteria("id.chronicNo",chronicId.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
	 		 
	 	 }
     if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.Mithra").equalsIgnoreCase(roleId)){
			
			
			List<String> li=new ArrayList<String>();
			li.add(config.getString("CO-MEDCO-CLAIM-INTAIATED"));
			
			
    	 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
    	
		}
		
		
		
     if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.Medco").equalsIgnoreCase(roleId)){
    	 
    	 
    	 List<String> li=new ArrayList<String>();
			li.add(config.getString("CO-MEDCO-SCREENING"));
			li.add(config.getString(config.getString("COTD-REC-PEN")));
			li.add(config.getString(config.getString("COCH-PEN")));
			 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
    	 
    	 
    	 
     }
		
		
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.COEX").equalsIgnoreCase(roleId)){
    	 
    	 critList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("CO-MITHRA-VERIFIED"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    	
    	 
    	 
     }
	
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.COTD").equalsIgnoreCase(roleId)){
   	 
    	 List<String> li=new ArrayList<String>();
    	li.add(config.getString("COTD-PENDING-UPDATED-BY-MEDCO"));
    	li.add(config.getString("COEX-VERIFIED"));
   	 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
   	
   	 
   	 
    }
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("Group.COCH").equalsIgnoreCase(roleId)){
      	 
    	
    	 List<String> li=new ArrayList<String>();
    	 li.add(config.getString("COCH-PENDING-UPDATED-BY-MEDCO"));
    	 li.add(config.getString("COTD-REC-APPRV"));
      	 critList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));
      	
      	 
      	 
       }
    
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("GROUP.COACO").equalsIgnoreCase(roleId)){
     	 
     	 critList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("COCH-APPRV"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
     	
     	 
     	 
      }
    if((roleId!=null && !"".equalsIgnoreCase(roleId))&& config.getString("GROUP.COEO").equalsIgnoreCase(roleId)){
    	 
    	 critList.add(new GenericDAOQueryCriteria("chronicStatus",config.getString("COACO-VERIFY"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    	
    	 
    	 
     }
		
		
		
		List<EhfChronicCaseDtl>  dtls=genericDao.findAllByCriteria(EhfChronicCaseDtl.class,critList );
		
		List<EhfChronicCaseDtl>  dtlsFinal=new ArrayList<EhfChronicCaseDtl>();
		if(dtls!=null && dtls.size()>0){
			
			for(int i=0;i<dtls.size();i++){
				String str=dtls.get(i).getId().getChronicNo();
			 	 char a='1';
			
			 	 if(str.charAt(str.length()-1)==a){
			 		dtlsFinal.add(dtls.get(i));
			 		 
			 	 }
			}
			
			
		}
		
         
         if(dtlsFinal!=null && dtlsFinal.size()>0){
        	 	 
       
        	 
		
		 
		
		 
		 for(int i=0;i<dtlsFinal.size();i++){
			
			 List<GenericDAOQueryCriteria> criList=new ArrayList<GenericDAOQueryCriteria>();	
			 criList.add(new GenericDAOQueryCriteria("chronicId",dtlsFinal.get(i).getId().getChronicId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 
		 
		if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
			 criList.add(new GenericDAOQueryCriteria("regHospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
		}
		
		
		 
		 if(patName!=null && !"".equalsIgnoreCase(patName)){
			 criList.add(new GenericDAOQueryCriteria("name",patName.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
				
			}
		 
		 if(state!=null && !"-1".equalsIgnoreCase(state)){
				
				
			 criList.add(new GenericDAOQueryCriteria("state",state,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
			}
			
			if(district!=null &&  !"-1".equalsIgnoreCase(district)){
				
				criList.add(new GenericDAOQueryCriteria("districtCode",district,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
			}
	       if((fromDt!=null && !"".equalsIgnoreCase(fromDt)) && (toDate!=null && !"".equalsIgnoreCase(toDate)) ){
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date frmDt=formatter.parse(fromDt);
				Date toDt=formatter.parse(toDate);
				
		
				
				criList.add(new GenericDAOQueryCriteria("regHospDate",frmDt,GenericDAOQueryCriteria.CriteriaOperator.GE));
				
				criList.add(new GenericDAOQueryCriteria("regHospDate",toDt,GenericDAOQueryCriteria.CriteriaOperator.LE));
				criList.add(new GenericDAOQueryCriteria("regHospDate","",GenericDAOQueryCriteria.CriteriaOperator.ASC));
			}
			
	       List<EhfChronicPatientDtl>  chrList=genericDao.findAllByCriteria(EhfChronicPatientDtl.class,criList );
	
		
	       for(int j=0;j<chrList.size();j++){
	    	   ChronicOPVO vo=new ChronicOPVO();
	    	   vo.setChronicID(dtlsFinal.get(i).getId().getChronicNo());
	    	   int n=dtlsFinal.get(i).getId().getChronicNo().indexOf("/");
				int n1=dtlsFinal.get(i).getId().getChronicNo().substring(n+1, dtlsFinal.get(i).getId().getChronicNo().length()).toString().indexOf("/");
				n=n+n1;
				vo.setChronicSubID(dtlsFinal.get(i).getId().getChronicNo().substring(n+2,dtlsFinal.get(i).getId().getChronicNo().length()));
	    	  
	    	
	    	  // vo.setChronicID(chrList.get(j).getChronicId());
	    	   
	    	   vo.setEmployeeNo(chrList.get(j).getEmployeeNo());
	    	   vo.setName(chrList.get(j).getName());
	    	   vo.setAge(chrList.get(j).getAge());
	    	   vo.setGender(chrList.get(j).getGender());
	    	   if(chrList.get(j).getDistrictCode()!=null && !"".equalsIgnoreCase(chrList.get(j).getDistrictCode())){
					List<GenericDAOQueryCriteria> critList1=new ArrayList<GenericDAOQueryCriteria>();
					critList1.add(new GenericDAOQueryCriteria("id.locId",chrList.get(j).getDistrictCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					List<EhfmLocations>  loc=genericDao.findAllByCriteria(EhfmLocations.class,critList1 );
					if(loc!=null && loc.size()>0){
						vo.setDistrict(loc.get(0).getLocName());	
						
					}
					}
	    	   vo.setDistrict(chrList.get(j).getDistrictCode());
	    	   vo.setRegistrationDate(chrList.get(j).getRegHospDate().toString());
	    	   chronicList.add(vo);
	       }
         
	
		 
		 }
		 
		 
         }  
	}catch(Exception e){
		
		e.printStackTrace();
	}
		
	
		return chronicList;	
	}


	@Override
	public ChronicOPVO getChronicOPCasesView(ChronicOPVO chronicOPVO) {
	
		List<ChronicOPVO> chrCasesList=new ArrayList<ChronicOPVO>();
		ChronicOPVO chronicCases=new ChronicOPVO();
		List<EhfChronicCaseDtl>  chrList=new ArrayList<EhfChronicCaseDtl>();
		 List<GenericDAOQueryCriteria> criList=new ArrayList<GenericDAOQueryCriteria>();
		  int startIndex=Integer.parseInt(chronicOPVO.getStartIndex());
		  int maxResult = (Integer.parseInt(chronicOPVO.getRowsPerPage()));
		  String hospId=chronicOPVO.getHospitalCode();
		  String roleId=chronicOPVO.getUserRole();
		  String userState=chronicOPVO.getSchemeId();
		  String caseApprovalFlag=chronicOPVO.getCaseApprovalFlag();
		  String chronicStat=chronicOPVO.getChronicStatus();
		  String regCaseFlg=chronicOPVO.getRegCaseFlg();
		  String pendingFlag=chronicOPVO.getPendingFlag();
		  
		 String[] args=new String[2];
		 String chronicStatus="";
		 args[0]=config.getString("CO-MITHRA-CASE-REG");
		 args[1]=config.getString("CO-MEDCO-SAVE");
		 SessionFactory factory=hibernateTemplate.getSessionFactory();
		 Session session =factory.openSession();
		 StringTokenizer st = null;
		 
		 String[] userRoles;
		 
		 if(roleId!=null)
		 {
			st=new StringTokenizer(roleId,"~");
			 
		 }
		 try
		 {
		
		 StringBuffer query=new StringBuffer();
		 StringBuffer countQuery=new StringBuffer();
		 StringBuffer query1=new StringBuffer();
		 countQuery.append(" select count(distinct b.id.chronicNo)   ");
		    query.append( " select distinct a.chronicId as chronicSubID,b.id.chronicNo as chronicNo,a.employeeNo as employeeNo,a.name as Name ,a.cardNo as cardNo, " );
			query.append( " d.cmbDtlName as chronicStatus,c.locName as district," );
			query.append( " a.gender as gender,a.age as patAge,to_char(a.regHospDate,'DD/mm/YYYY') as regnDt, a.regHospDate as caseRegnDt, b.claimAmount as claimAmount ,to_char(b.clmSubDt,'DD/mm/YYYY') as clmSubDt ,b.clmSubDt as claimSubDt   " );
			query1.append( " from EhfChronicPatientDtl a,EhfChronicCaseDtl b,EhfmLocations c,EhfmCmbDtls d" );
			query1.append( " where a.chronicId=b.id.chronicId and a.districtCode=c.id.locId and b.chronicStatus=d.cmbDtlId " );
			
			if(regCaseFlg!=null && ("Y").equalsIgnoreCase(regCaseFlg))
			{
				if(roleId.equalsIgnoreCase(config.getString("Group.Mithra")))
					query1.append( " and b.chronicStatus  in ('"+args[0]+"','"+args[1]+"') ");
				else if(roleId.equalsIgnoreCase(config.getString("Group.Medco")))
				query1.append( " and b.chronicStatus  in ('"+args[0]+"','"+args[1]+"') ");
					
			}
			else
			query1.append( " and b.chronicStatus not in ('"+args[0]+"','"+args[1]+"') ");	
			
			if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
				
				query1.append( " and b.hospCode='"+hospId+"' ");
				 
				
				}
			
			 if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
				 query1.append( " and b.schemeId in ('CD201','CD202') ");
					
				}
			 else if(userState!=null && !"-1".equalsIgnoreCase(userState)){
					
				 query1.append( " and b.schemeId in ('"+userState+"') ");
				 		
				}
			 else if(chronicStat!=null && !"-1".equalsIgnoreCase(chronicStat))
			 {
				 query1.append( " and b.chronicStatus ='"+chronicStat+"' ");
			 }
			if(caseApprovalFlag!=null && ("Y").equalsIgnoreCase(caseApprovalFlag))
			{
			    int statusCount=0;
				while(st.hasMoreTokens())
				{
					if(statusCount==0)
					 chronicStatus=config.getString("chronic_status_"+st.nextToken());
					else
					 chronicStatus=chronicStatus+"~"+config.getString("chronic_status_"+st.nextToken());
					statusCount++;
						
				}
				
				/*if((roleId!=null && !"".equalsIgnoreCase(roleId)))
					 {
				 chronicStatus=config.getString("chronic_status_"+roleId);
					 }*/
			 
			 if(chronicStatus!=null && !("").equalsIgnoreCase(chronicStatus) && !("Y").equalsIgnoreCase(pendingFlag) )
			 {
				 query1.append( " and b.chronicStatus in( ");
				 String[] status=chronicStatus.split("~");
				 int count=0;
				 for(String stat:status)
				 {
					 if(count>0)
						 query1.append(" ,'"+stat+"' " );
					 else
					 query1.append(" '"+stat+"' " );
					 
					 count++;
					 
				 }
				 query1.append( " ) ");
			 }
			 
			}
			
			 if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
			  {
				  
				  String[] Status=null;
			
				  Status =  ClaimsConstants.preauthCEOChronicSentBackStatuses;  
					  
					
				  query1.append( " and b.chronicStatus in( ");
				  for (int i = 0; i < Status.length; i++) {
						query1.append(" '" + Status[i] + "' ");
						if (i != Status.length - 1)
							query1.append(",");
				  }
						query1.append(" ) ");
				  
				  
				  query1.append("   and b.pendingWith in ('"+chronicOPVO.getUserId()+"') ");  
			 
			  }
			 
			 if(regCaseFlg!=null && ("Y").equalsIgnoreCase(regCaseFlg))
				{
		        query1.append(" order by a.regHospDate ");
				}
		     else
		     {
		    	 query1.append(" order by b.clmSubDt ");
		     }
			countQuery.append(query1);
			long Count=(Long) session.createQuery(countQuery.toString()).uniqueResult();
			if(chronicOPVO.getCsvFlag()!=null && ("Y").equalsIgnoreCase(chronicOPVO.getCsvFlag()))
			{
				maxResult=(int) Count;
			}
			query.append(query1);
			//chrCasesList=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString(),args);
			chrCasesList=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(ChronicOPVO.class)).list();
			
			chronicCases.setCasesLst(chrCasesList);
			chronicCases.setCount(Count);
		 /*if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
				
			 criList.add(new GenericDAOQueryCriteria("hospCode",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			}
		 if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
				
				List<String> li=new ArrayList<String>();
				li.add("CD201");
				li.add("CD202");
				criList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
				
			}else if(userState!=null && !"".equalsIgnoreCase(userState)){
				criList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
				
			}
		 
		 List<String> li=new ArrayList<String>();
			li.add(config.getString("CO-MITHRA-CASE-REG"));
			li.add(config.getString("CO-MEDCO-SAVE"));
			
			criList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
		 chrList=genericDao.findAllByCriteria(EhfChronicCaseDtl.class,criList);
	   
		 List<String> a=new ArrayList<String>();
		 if(chrList!=null && chrList.size()>0) {
		 for(int j=0;j<chrList.size();j++){
			 
			 if(!a.contains(chrList.get(j).getId().getChronicId())){
				 
				 a.add(chrList.get(j).getId().getChronicId());
			 }
			 
		 }
			 
		 List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		 
		 crList.add(new GenericDAOQueryCriteria("chronicId",a,GenericDAOQueryCriteria.CriteriaOperator.IN));
		 crList.add(new GenericDAOQueryCriteria("regHospDate","",GenericDAOQueryCriteria.CriteriaOperator.ASC));
		 
		List<EhfChronicPatientDtl> crLst=genericDao.findAllByCriteria(EhfChronicPatientDtl.class,crList);
	 if(crLst!=null && crLst.size()>0){
	
		 for(int i=0;i<crLst.size();i++){
			
			 ChronicOPVO vo=new ChronicOPVO();
			 vo.setChronicID(crLst.get(i).getChronicId());
			 vo.setChronicSubID(crLst.get(i).getChronicId().substring((crLst.get(i).getChronicId().lastIndexOf('/')+1),crLst.get(i).getChronicId().length()));
			 
			 vo.setEMPLOYEENO(crLst.get(i).getEmployeeNo());
			 vo.setName(crLst.get(i).getName());
			 vo.setGender(crLst.get(i).getGender());
			 vo.setAge(crLst.get(i).getAge().toString());
			 if(crLst.get(i).getDistrictCode()!=null && !"".equalsIgnoreCase(crLst.get(i).getDistrictCode())){
					List<GenericDAOQueryCriteria> critList1=new ArrayList<GenericDAOQueryCriteria>();
					critList1.add(new GenericDAOQueryCriteria("id.locId",crLst.get(i).getDistrictCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					
					
					List<EhfmLocations>  loc=genericDao.findAllByCriteria(EhfmLocations.class,critList1 );
					if(loc!=null && loc.size()>0){
						vo.setDistrict(loc.get(0).getLocName());	
						
					}
					} 
			 
			 vo.setRegistrationDate(crLst.get(i).getRegHospDate().toString());
			 chrCasesList.add(vo);
			 
		 }
	 }
		 
		 
		 } */
		 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 GLOGGER.error("error occured in getChronicOPCasesView() method in chronicOpDaoImpl.class"+e.getMessage());
		 }
		 finally {
				session.close();
				factory.close();
			}
		return chronicCases;
	}


	@Override
	public ChronicOPVO searchChroniOPCases(ChronicOPVO chronicOPVO) {
		List<ChronicOPVO> chrCasesList=new ArrayList<ChronicOPVO>();
		ChronicOPVO chronicCases=new ChronicOPVO();
		 SessionFactory factory=hibernateTemplate.getSessionFactory();
		 Session session =factory.openSession();
		
		try
		{
			
			String chronicId=chronicOPVO.getChronicID();
			String patName=chronicOPVO.getName();
			String state=chronicOPVO.getState();
			String district=chronicOPVO.getDistrict();
			String fromDt=chronicOPVO.getFromDt();
			String toDate=chronicOPVO.getToDate();
			String hospId=chronicOPVO.getHospitalCode();
			String roleId=chronicOPVO.getUserRole();
			String userState=chronicOPVO.getSchemeId();
			String chronicStat=chronicOPVO.getChronicStatus();
			String regCaseFlg=chronicOPVO.getRegCaseFlg();
			String pendingFlag=chronicOPVO.getPendingFlag();
			
		 int startIndex=Integer.parseInt(chronicOPVO.getStartIndex());
		 int maxResult = (Integer.parseInt(chronicOPVO.getRowsPerPage()));
			StringTokenizer st=null;
		 if(roleId!=null)
		 {
			st=new StringTokenizer(roleId,"~");
			 
		 }								
			
		 String[] args=new String[2];
		 args[0]=config.getString("CO-MITHRA-CASE-REG");
		 args[1]=config.getString("CO-MEDCO-SAVE");
		 		
		 
		
		 
		    StringBuffer query=new StringBuffer();
		    StringBuffer query1=new StringBuffer();
		    StringBuffer countQuery=new StringBuffer();
			String caseApprovalFlag=chronicOPVO.getCaseApprovalFlag();	
			String chronicStatus="";
		    countQuery.append(" select count(distinct b.id.chronicNo)   ");
		    query.append( " select distinct a.chronicId as chronicSubID,b.id.chronicNo as chronicNo,a.employeeNo as employeeNo,a.name as Name ,a.cardNo as cardNo, " );
			query.append( " d.cmbDtlName as chronicStatus,c.locName as district," );
			query.append( " a.gender as gender,a.age as patAge,to_char(a.regHospDate,'DD/mm/YYYY') as regnDt ,a.regHospDate as caseRegnDt,b.claimAmount as claimAmount , b.clmSubDt as claimSubDt, to_char(b.clmSubDt,'DD/mm/YYYY') as clmSubDt ,b.clmSubDt as claimSubDt   " );
			query1.append( " from EhfChronicPatientDtl a,EhfChronicCaseDtl b,EhfmLocations c,EhfmCmbDtls d" );
			query1.append( " where a.chronicId=b.id.chronicId and a.districtCode=c.id.locId and b.chronicStatus=d.cmbDtlId " );
			
			
			if(regCaseFlg!=null && ("Y").equalsIgnoreCase(regCaseFlg))
			{
				if(roleId.equalsIgnoreCase(config.getString("Group.Mithra")))
					query1.append( " and b.chronicStatus  in ('"+args[0]+"','"+args[1]+"') ");
				else if(roleId.equalsIgnoreCase(config.getString("Group.Medco")))
				query1.append( " and b.chronicStatus  in ('"+args[0]+"','"+args[1]+"') ");
					
			}
			else
			query1.append( " and b.chronicStatus not in ('"+args[0]+"','"+args[1]+"') ");	
			
			if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
				
				query1.append( " and b.hospCode='"+hospId+"' ");
				 
				
				}
			
			 if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
					query1.append( " and b.schemeId in ('CD201','CD202') ");
					
				}
			 else if(userState!=null && !"-1".equalsIgnoreCase(userState)){
					
				 query1.append( " and b.schemeId in ('"+userState+"') ");
				 		
				}
			 
			 
				if(caseApprovalFlag!=null && ("Y").equalsIgnoreCase(caseApprovalFlag))
				{
				

					 int statusCount=0;
									while(st.hasMoreTokens())
									{
										if(statusCount==0)
										 chronicStatus=config.getString("chronic_status_"+st.nextToken());
										else
										 chronicStatus=chronicStatus+"~"+config.getString("chronic_status_"+st.nextToken());
										statusCount++;
											
									}
									
					
					/* if((roleId!=null && !"".equalsIgnoreCase(roleId)))
						 {
					 chronicStatus=config.getString("chronic_status_"+roleId);
						 }*/
				 
				 if(chronicStatus!=null && !("").equalsIgnoreCase(chronicStatus))
				 {
					 query1.append( " and b.chronicStatus in( ");
					 String[] status=chronicStatus.split("~");
					 int count=0;
					 for(String stat:status)
					 {
						 if(count>0)
							 query1.append(" ,'"+stat+"' " );
						 else
						 query1.append(" '"+stat+"' " );
						 
						 count++;
						 
					 }
					 query1.append( " ) ");
				 }
				 
				}
			 
			 
			 if(chronicId!=null && !"".equalsIgnoreCase(chronicId)){
				 
				 query1.append( " and b.id.chronicId ='"+chronicId+"' ");
				 
				 
			 }
			 
			 
			 if(patName!=null && !"".equalsIgnoreCase(patName)){
				 
				 query1.append( " and upper(a.name) like '%"+patName.toUpperCase()+"%' ");
				 
					
				}
			 
			 if(state!=null && !"-1".equalsIgnoreCase(state)){
					
				 query1.append( " and a.state='"+state+"' ");
				
					
				}
				
				if(district!=null &&  !"-1".equalsIgnoreCase(district)){
					
					query1.append( " and a.districtCode='"+district+"' ");
					
				}
		       if((fromDt!=null && !"".equalsIgnoreCase(fromDt)) && (toDate!=null && !"".equalsIgnoreCase(toDate)) ){
					
					SimpleDateFormat formatter = new SimpleDateFormat("DD-mm-yyyy");
					Date frmDt=formatter.parse(fromDt);
					Date toDt=formatter.parse(toDate);
					
					
					//To include todate in search criteria--adding date+1 
					Calendar cal = Calendar.getInstance();  
		        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
		        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
		        	Date tomorrow = cal.getTime();  
		        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
		        	 //End of date+1 
		        	 
			        query1.append( " and a.regHospDate between TO_DATE('"+fromDt+"','DD-mm-yyyy') and  TO_DATE('"+lStrToDate+"','dd-mm-yyyy') "); 
					
					/*crList.add(new GenericDAOQueryCriteria("regHospDate",frmDt,GenericDAOQueryCriteria.CriteriaOperator.GE));
					
					crList.add(new GenericDAOQueryCriteria("regHospDate",toDt,GenericDAOQueryCriteria.CriteriaOperator.LE));
					crList.add(new GenericDAOQueryCriteria("regHospDate","",GenericDAOQueryCriteria.CriteriaOperator.ASC));*/
				}
		     if(chronicStat!=null && !"-1".equalsIgnoreCase(chronicStat))
			 {
				 query1.append( " and b.chronicStatus ='"+chronicStat+"' ");
			 }
		     
		     
		     if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
			  {
				  
				  String[] Status=null;
			
				  Status =  ClaimsConstants.preauthCEOChronicSentBackStatuses;  
					  
					
				  query1.append( " and b.chronicStatus in( ");
				  for (int i = 0; i < Status.length; i++) {
						query1.append(" '" + Status[i] + "' ");
						if (i != Status.length - 1)
							query1.append(",");
				  }
						query1.append(" ) ");
				  
				  
				  query1.append("   and b.pendingWith in ('"+chronicOPVO.getUserId()+"') ");  
			 
			  }
		     
		     if(regCaseFlg!=null && ("Y").equalsIgnoreCase(regCaseFlg))
				{
		        query1.append(" order by a.regHospDate ");
				}
		     else
		     {
		    	 query1.append(" order by b.clmSubDt ");
		     }
		        countQuery.append(query1);
				long Count=(Long) session.createQuery(countQuery.toString()).uniqueResult();
				if(chronicOPVO.getCsvFlag()!=null && ("Y").equalsIgnoreCase(chronicOPVO.getCsvFlag()))
				{
					maxResult=(int) Count;
				}
				query.append(query1);
				//chrCasesList=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString(),args);
				chrCasesList=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(ChronicOPVO.class)).list();
				
				chronicCases.setCasesLst(chrCasesList);
				chronicCases.setCount(Count);
			
		 
		
		/*List<EhfChronicCaseDtl>  chrList=new ArrayList<EhfChronicCaseDtl>();
		chrList=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString());
		
		List<GenericDAOQueryCriteria> criList=new ArrayList<GenericDAOQueryCriteria>();
		
		 if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
				
			 criList.add(new GenericDAOQueryCriteria("hospCode",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 
			
			}
		
		 
		 if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
				
				List<String> li=new ArrayList<String>();
				li.add("CD201");
				li.add("CD202");
				criList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
				
			}else if(userState!=null && !"-1".equalsIgnoreCase(userState)){
				criList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
				
			}
			
		 List<String> li=new ArrayList<String>();
			li.add(config.getString("CO-MITHRA-CASE-REG"));
			li.add(config.getString("CO-MEDCO-SAVE"));
			criList.add(new GenericDAOQueryCriteria("chronicStatus",li,GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
			
			
		 chrList=genericDao.findAllByCriteria(EhfChronicCaseDtl.class,criList);
		 List<String> a=new ArrayList<String>();
		 if(chrList!=null && chrList.size()>0) {
		 for(int j=0;j<chrList.size();j++){
			 
			 if(!a.contains(chrList.get(j).getId().getChronicId())){
				 
				 a.add(chrList.get(j).getId().getChronicId());
			 }
			 
		 }
         List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		 
		 crList.add(new GenericDAOQueryCriteria("chronicId",a,GenericDAOQueryCriteria.CriteriaOperator.IN));
		 
		 if(chronicId!=null && !"".equalsIgnoreCase(chronicId)){
			 crList.add(new GenericDAOQueryCriteria("chronicId",chronicId.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
			 
		 }
		 
		 
		 if(patName!=null && !"".equalsIgnoreCase(patName)){
			 crList.add(new GenericDAOQueryCriteria("name",patName.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
				
			}
		 
		 if(state!=null && !"-1".equalsIgnoreCase(state)){
				
				
			 crList.add(new GenericDAOQueryCriteria("state",state,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
			}
			
			if(district!=null &&  !"-1".equalsIgnoreCase(district)){
				
				crList.add(new GenericDAOQueryCriteria("districtCode",district,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
			}
	       if((fromDt!=null && !"".equalsIgnoreCase(fromDt)) && (toDate!=null && !"".equalsIgnoreCase(toDate)) ){
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date frmDt=formatter.parse(fromDt);
				Date toDt=formatter.parse(toDate);
				
		
				
				crList.add(new GenericDAOQueryCriteria("regHospDate",frmDt,GenericDAOQueryCriteria.CriteriaOperator.GE));
				
				crList.add(new GenericDAOQueryCriteria("regHospDate",toDt,GenericDAOQueryCriteria.CriteriaOperator.LE));
				crList.add(new GenericDAOQueryCriteria("regHospDate","",GenericDAOQueryCriteria.CriteriaOperator.ASC));
			}
		 List<EhfChronicPatientDtl> crLst=genericDao.findAllByCriteria(EhfChronicPatientDtl.class,crList);
		 
		 if(crLst!=null && crLst.size()>0){
				
			 for(int i=0;i<crLst.size();i++){
				
				 ChronicOPVO vo=new ChronicOPVO();
				 vo.setChronicID(crLst.get(i).getChronicId());
				 vo.setChronicSubID(crLst.get(i).getChronicId().substring((crLst.get(i).getChronicId().lastIndexOf('/')+1),crLst.get(i).getChronicId().length()));
				 
				 vo.setEMPLOYEENO(crLst.get(i).getEmployeeNo());
				 vo.setName(crLst.get(i).getName());
				 vo.setGender(crLst.get(i).getGender());
				 vo.setAge(crLst.get(i).getAge());
				 if(crLst.get(i).getDistrictCode()!=null && !"".equalsIgnoreCase(crLst.get(i).getDistrictCode())){
						List<GenericDAOQueryCriteria> critList1=new ArrayList<GenericDAOQueryCriteria>();
						critList1.add(new GenericDAOQueryCriteria("id.locId",crLst.get(i).getDistrictCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						List<EhfmLocations>  loc=genericDao.findAllByCriteria(EhfmLocations.class,critList1 );
						if(loc!=null && loc.size()>0){
							vo.setDistrict(loc.get(0).getLocName());	
							
						}
						} 
				 
				 vo.setRegistrationDate(crLst.get(i).getRegHospDate().toString());
				 chrCasesList.add(vo);
				 
			 }
		 }
		 
		 
		 }*/}
	    catch(Exception e){
			 
			 e.printStackTrace();
		 }
	    finally {
			session.close();
			factory.close();
		}
		return chronicCases;
	

	}




	
	
	/*added by venkatesh*/
	@Transactional	
	public String saveCaseDetails(ChronicOPVO ChronicOpVO) throws Exception {
		String lStrChronicId=null;
		String chronicNo=null;
		//String lStrChronicCaseId=null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date lCurrentDate=sdfw.parse(ChronicOpVO.getCrtDt());
		Date date=new Date();
		String temp=null;
		SessionFactory factory= hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		//EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
		List<AttachmentVO> lSplAttachList=ChronicOpVO.getAttachmentsList();
		EhfChronicAudit ehfChronicAudit = new EhfChronicAudit();
		//EhfEmpCaseDtls ehfEmpCaseDtls = new EhfEmpCaseDtls();
		chronicNo=ChronicOpVO.getChronicID()+config.getString("SLASH_VALUE")+"1";
		EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(ChronicOpVO.getChronicID(),chronicNo);
		
		EhfChronicCaseDtl ehfChronicCaseDtl=genericDao.findFirstByPropertyMatch(EhfChronicCaseDtl.class, "id",ehfChronicCaseDtlPK);
		
		
		
		/*if(ehfChronicCaseCheck==null )
		{
			
			//int seqNextVal=Integer.parseInt(liNextVal);
			//ehfmSeqCase.setSeqNextVal(Long.valueOf(seqNextVal + 1));
			//updateSequenceVal(ehfmSeqCase);
		
			lStrChronicId=ChronicOpVO.getChronicID();
			ChronicOpVO.setChronicID(lStrChronicId);
			ehfChronicCaseDtl = new EhfChronicCaseDtl();
			ehfChronicCaseDtl.setCrtUsr(ChronicOpVO.getCrtUsr());
			ehfChronicCaseDtl.setCrtDt(lCurrentDate);
			ehfChronicCaseDtl.setLstUpdDt(lCurrentDate);
			ehfChronicCaseDtl.setLstUpdUsr(ChronicOpVO.getCrtUsr());
			
		}*/
		if(ehfChronicCaseDtl!=null)
		{
			
			//ehfChronicCaseDtl=ehfChronicCaseCheck;
			lStrChronicId=ChronicOpVO.getChronicID();
			//ehfChronicCaseDtl=ehfChronicCaseCheck;
			ehfChronicCaseDtl.setLstUpdDt(lCurrentDate);
			ehfChronicCaseDtl.setLstUpdUsr(ChronicOpVO.getCrtUsr());
		}
		
		
		
		EhfChronicPatientHosDgnsi ehfChronicPatientHosDgnsi = genericDao.findById(EhfChronicPatientHosDgnsi.class, String.class, ChronicOpVO.getChronicID());
		if (ehfChronicPatientHosDgnsi==null){
			ehfChronicPatientHosDgnsi = new EhfChronicPatientHosDgnsi();
			ehfChronicPatientHosDgnsi.setCrtUsr(ChronicOpVO.getCrtUsr());
			ehfChronicPatientHosDgnsi.setCrtDt(lCurrentDate);
		}
		else{
			ehfChronicPatientHosDgnsi.setLstUpdDt(lCurrentDate);
			ehfChronicPatientHosDgnsi.setLstUpdUsr(ChronicOpVO.getCrtUsr());
		}
		
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			
			EhfChronicPatientDtl ehfChronicPatientDtl=new EhfChronicPatientDtl();			
			
			try{

				if(ChronicOpVO.getChronicID()!=null){
					ehfChronicPatientDtl=genericDao.findById(EhfChronicPatientDtl.class,String.class,ChronicOpVO.getChronicID());
					
					String lStrTestDesc=null;
					List<AttachmentVO> lAttachList=ChronicOpVO.getGenAttachmentsList();
					for(AttachmentVO attachmentVO:lAttachList){

						EhfChronicPatientTest ehfChronicPatientTest=new EhfChronicPatientTest();
						ehfChronicPatientTest.setSno(Long.parseLong(attachmentVO.getAttachId()));
						ehfChronicPatientTest.setTestId(attachmentVO.getTestId());
						if(attachmentVO.getTestId()!=null && attachmentVO.getTestId().contains("OI"))
						{
							ehfChronicPatientTest.setTestName(attachmentVO.getTestName());
						}
						if(lStrTestDesc!=null){
							lStrTestDesc=lStrTestDesc+","+attachmentVO.getTestName();
						}
						else{
							lStrTestDesc=attachmentVO.getTestName();  
						}
						ehfChronicPatientTest.setChronicId(lStrChronicId);
						ehfChronicPatientTest.setChronicNo(chronicNo);
						ehfChronicPatientTest.setAttachPath(attachmentVO.getFileReportPath());                	            
						ehfChronicPatientTest.setCrtUsr(ChronicOpVO.getCrtUsr());
						ehfChronicPatientTest.setCrtDt(lCurrentDate);
						genericDao.save(ehfChronicPatientTest);
					}
					//Updating existing test file path
					List<AttachmentVO> lUpdGenAttachList=ChronicOpVO.getUpdGenAttachmentsList();
					EhfChronicPatientTest ehfChronicPatientTest=null;
					for(AttachmentVO attachmentVO:lUpdGenAttachList)
					{
						Map<String,Object> testMap=new HashMap<String,Object>();
						testMap.put("chronicId",lStrChronicId );
						testMap.put("chronicNo",chronicNo );
						testMap.put("testId",attachmentVO.getTestId());
						List<EhfChronicPatientTest> ehfChronicPatientTestList=genericDao.findAllByPropertyMatch(EhfChronicPatientTest.class, testMap);
						if(ehfChronicPatientTestList!=null && ehfChronicPatientTestList.size()>0)
						{
							ehfChronicPatientTest=ehfChronicPatientTestList.get(0);
							ehfChronicPatientTest.setAttachPath(attachmentVO.getFileReportPath());
							ehfChronicPatientTest.setLstUpdUsr(ChronicOpVO.getCrtUsr());
							ehfChronicPatientTest.setLstUpdDt(lCurrentDate);
							genericDao.save(ehfChronicPatientTest);
						}
					}
					
					String lStrPastHistory="";
					if(ChronicOpVO.getPastHistory()!=null)
					{
						for(int i=0;i<ChronicOpVO.getPastHistory().length;i++){
							lStrPastHistory = lStrPastHistory+ChronicOpVO.getPastHistory()[i];
							if(i!=ChronicOpVO.getPastHistory().length-1){
								lStrPastHistory = lStrPastHistory+"~";
							}
						}
					}

					String lStrExamFind="";	   
					if(ChronicOpVO.getExaminationFnd()!=null){
					for(int i=0;i<ChronicOpVO.getExaminationFnd().length;i++){
						lStrExamFind = lStrExamFind+ChronicOpVO.getExaminationFnd()[i];
						if(i!=ChronicOpVO.getExaminationFnd().length-1){
							lStrExamFind = lStrExamFind+"~";
						}
					}
					}					
					//saving in ehfChroniccase for chronic op
						String lStrHospitalCodeInCaseNo = ehfChronicPatientDtl.getRegHospId().substring(3);
						String nflag=config.getString("NFlag");
						if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Yes"))
						{
							ehfChronicPatientDtl.setRegisterYN(null);
							chronicNo=lStrChronicId+config.getString("SLASH_VALUE")+"1";
						}
						else if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit"))
						{
							chronicNo=lStrChronicId+config.getString("SLASH_VALUE")+"1";
								//config.getString("CASE")+config.getString("SLASH_VALUE")+lStrHospitalCodeInCaseNo+config.getString("SLASH_VALUE")+lStrChronicId;
							ehfChronicPatientDtl.setRegisterYN("Y");
							ehfChronicCaseDtl.setChronicRegnDate(lCurrentDate);
							
							/*added to save total Drug amount after submitting*/
							String totPckgAmt=null;
							String pkgCode=ChronicOpVO.getOpPkgCode();
							String actOrder="1";
							String schemeId=ehfChronicCaseDtl.getSchemeId();
							totPckgAmt=calculatePackageAmt( pkgCode, actOrder, schemeId);
							ehfChronicCaseDtl.setTotPckgAmt(totPckgAmt);
							
						}
						else
						{
							ehfChronicPatientDtl.setRegisterYN(null);
							//caseNo=config.getString("CASE")+config.getString("SLASH_VALUE")+lStrHospitalCodeInCaseNo+config.getString("SLASH_VALUE")+lStrChronicId+config.getString("SLASH_VALUE")+"D";
						}
					
						ehfChronicCaseDtl.setId(new EhfChronicCaseDtlPK(lStrChronicId,chronicNo));
						//ehfChronicCaseDtl.setCaseNo(caseNo);
						ehfChronicCaseDtl.setHospCode(ehfChronicPatientDtl.getRegHospId());
							
						ehfChronicCaseDtl.setSchemeId(ehfChronicPatientDtl.getSchemeId());
						ehfChronicCaseDtl.setChronicRegnDate(ehfChronicPatientDtl.getCrtDt());
						
						if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Yes"))
						{
							ehfChronicCaseDtl.setChronicStatus(config.getString("CO-MEDCO-SAVE"));
						}
						else if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit"))
						{
							ehfChronicCaseDtl.setChronicStatus(config.getString("CO-MEDCO-SCREENING"));	
						}
						
						EhfEnrollment ehfEnrollment=null;
						List<EhfEnrollment> ehfEnrollmentList=genericDao.findAllByPropertyMatch(EhfEnrollment.class,"empCode",ehfChronicPatientDtl.getEmployeeNo());
						if(ehfEnrollmentList!=null && ehfEnrollmentList.size()>0)
						{
							ehfEnrollment=ehfEnrollmentList.get(0);
						}
						

						//saving in audit for ip/op/chronic op 
						
						Long lActOrder = 1L;
						StringBuffer lQueryBuffer = new StringBuffer();
						String args[] = new String[1];
						args[0] = chronicNo;
						lQueryBuffer
								.append(" select max(au.id.actOrder) as COUNT from EhfChronicAudit au where au.id.chronicNo=? ");
					
						List<ChronicOPVO> actOrderList = genericDao.executeHQLQueryList(
								ChronicOPVO.class, lQueryBuffer.toString(), args);
						if (actOrderList != null && !actOrderList.isEmpty()
								&& actOrderList.get(0).getCOUNT() != null) {
							if (actOrderList.get(0).getCOUNT().longValue() >= 0)
								lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
						}
						ehfChronicAudit.setId(new EhfChronicAuditPK(lStrChronicId,chronicNo,lActOrder));
						ehfChronicAudit.setActBy(ChronicOpVO.getCrtUsr());
/*						
 * 
 * if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Yes")){
							ehfAudit.setRemarks("Case Drafted");
						}else{
						ehfAudit.setRemarks(ChronicOpVO.getIpCaseRemarks());
						}*/
						ehfChronicAudit.setCrtUsr(ChronicOpVO.getCrtUsr());
						ehfChronicAudit.setCrtDt(date);
						ehfChronicAudit.setLangId(config.getString("en_US"));
						
						if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Yes")){
						
						//ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.CaseDrafted"));
							
							ehfChronicAudit.setActId(config.getString("CO-MEDCO-SAVE"));
						}
						if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit")){
							
							ehfChronicAudit.setActId(config.getString("CO-MEDCO-SCREENING"));
							
							}
						
						
						EhfChronicPatientPerHstry ehfChronicPatientPerHstry = genericDao.findById(EhfChronicPatientPerHstry.class, String.class, ChronicOpVO.getChronicID());
						if(ehfChronicPatientPerHstry!=null){
							ehfChronicPatientPerHstry.setLstUpdDt(lCurrentDate);
							ehfChronicPatientPerHstry.setLstUpdUsr(ChronicOpVO.getCrtUsr());
						}else{
							ehfChronicPatientPerHstry = new EhfChronicPatientPerHstry();
							ehfChronicPatientPerHstry.setCrtDt(lCurrentDate);
							ehfChronicPatientPerHstry.setCrtUsr(ChronicOpVO.getCrtUsr());
						}
						
						ehfChronicPatientPerHstry.setChronicId(lStrChronicId);
						String[] result = ChronicOpVO.getPersonalHistVal().split("#");
						for (int x=0; x<result.length; x++)
						{
							String[] result1 = result[x].split("~");
							if(result1[0].equalsIgnoreCase("PR1"))
								ehfChronicPatientPerHstry.setAppetite(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR2"))
								ehfChronicPatientPerHstry.setDiet(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR3"))
								ehfChronicPatientPerHstry.setBowels(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR4"))
								ehfChronicPatientPerHstry.setNutrition(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR5"))
								ehfChronicPatientPerHstry.setKnownAllergies(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR6"))
								ehfChronicPatientPerHstry.setAddictions(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR7"))
								ehfChronicPatientPerHstry.setMaritalStatus(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR8"))
								ehfChronicPatientPerHstry.setOccupation(result1[1]);
						}
                        genericDao.save(ehfChronicPatientPerHstry);

                        
                        
                                       
                        
                        EhfChronicPatientExamFind ehfChronicPatientExamFind = genericDao.findById(EhfChronicPatientExamFind.class, String.class, ChronicOpVO.getChronicID());
						if(ehfChronicPatientExamFind==null){
							ehfChronicPatientExamFind = new EhfChronicPatientExamFind();
							ehfChronicPatientExamFind.setCrtDt(lCurrentDate);
							ehfChronicPatientExamFind.setCrtUsr(ChronicOpVO.getCrtUsr());
						}else{
							ehfChronicPatientExamFind.setLstUpdDt(lCurrentDate);
							ehfChronicPatientExamFind.setLstUpdUsr(ChronicOpVO.getCrtUsr());
						}
						
						ehfChronicPatientExamFind.setChronicId(lStrChronicId);
						
						String[] result2 = ChronicOpVO.getExamFndsVal().split("#");
						for (int x=0; x<result2.length; x++)
						{
							String[] result1 = result2[x].split("~");
							if(result1.length>1){
							if(result1[0].equalsIgnoreCase("GE3"))
								ehfChronicPatientExamFind.setBmi(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE4"))
								ehfChronicPatientExamFind.setPallor(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE5"))
								ehfChronicPatientExamFind.setCyanosis(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE6"))
								ehfChronicPatientExamFind.setClubOfFingrtoes(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE7"))
								ehfChronicPatientExamFind.setLymphadenopathy(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE8"))
								ehfChronicPatientExamFind.setOedemaInFeet(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE9"))
								ehfChronicPatientExamFind.setMalnutrition(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE10"))
								ehfChronicPatientExamFind.setDehydration(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE11"))
								ehfChronicPatientExamFind.setTemperature(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE12"))
								ehfChronicPatientExamFind.setPluseRate(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE13"))
								ehfChronicPatientExamFind.setRespirationRate(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE14"))
								ehfChronicPatientExamFind.setBpLt(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE1"))
								ehfChronicPatientExamFind.setHeight(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE2"))
								ehfChronicPatientExamFind.setWeight(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE15"))
								ehfChronicPatientExamFind.setBpRt(result1[1]);
							}
						}
						genericDao.save(ehfChronicPatientExamFind);
						
					
						ehfChronicPatientHosDgnsi.setChronicId(lStrChronicId);
						ehfChronicPatientHosDgnsi.setPastHistory(lStrPastHistory);
						ehfChronicPatientHosDgnsi.setHistoryIllness(ChronicOpVO.getPresentHistory());
						ehfChronicPatientHosDgnsi.setExamntnFindings(lStrExamFind);
						
						String lStrValue="";	
						if(ChronicOpVO.getPatientComplaint()!=null && ChronicOpVO.getPatientComplaint().length>0){
						for(int i=0;i<ChronicOpVO.getPatientComplaint().length;i++){
							lStrValue = lStrValue+ChronicOpVO.getPatientComplaint()[i];
							if(i!=ChronicOpVO.getPatientComplaint().length-1){
								lStrValue = lStrValue+"~";
							}
						}
						}
						ehfChronicPatientHosDgnsi.setPatientComplaint(lStrValue);
						
						ehfChronicPatientHosDgnsi.setComplaintType(ChronicOpVO.getComplaints());
						lStrValue="";
						if(ChronicOpVO.getPersonalHistory()!=null && ChronicOpVO.getPersonalHistory().length>0){
						for(int i=0;i<ChronicOpVO.getPersonalHistory().length;i++){
							lStrValue = lStrValue+ChronicOpVO.getPersonalHistory()[i];
							if(i!=ChronicOpVO.getPersonalHistory().length-1){
								lStrValue = lStrValue+"~";
							}
						}}
						ehfChronicPatientHosDgnsi.setPersonalHistory(lStrValue);
						lStrValue="";	  
						if(ChronicOpVO.getFamilyHistory()!=null)
						{
							for(int i=0;i<ChronicOpVO.getFamilyHistory().length;i++){
								lStrValue = lStrValue+ChronicOpVO.getFamilyHistory()[i];
								if(i!=ChronicOpVO.getFamilyHistory().length-1){
									lStrValue = lStrValue+"~";
								}
							}
						}
						ehfChronicPatientHosDgnsi.setFamilyHistory(lStrValue);
						ehfChronicPatientHosDgnsi.setPastHistoryOthr(ChronicOpVO.getPastHistryOthr());
						ehfChronicPatientHosDgnsi.setPastHistoryCancers(ChronicOpVO.getPastHistryCancers());
						ehfChronicPatientHosDgnsi.setPastHistoryEnddis(ChronicOpVO.getPastHistryEndDis());
						ehfChronicPatientHosDgnsi.setPastHistorySurg(ChronicOpVO.getPastHistrySurg());
						ehfChronicPatientHosDgnsi.setFamilyHistoryOthr(ChronicOpVO.getFamilyHistoryOthr());
						
						

						
						
						

						for(AttachmentVO attachmentVO:lSplAttachList)
							{
							EhfChronicTherapyInvest ehfChronicTherapyInvest=new EhfChronicTherapyInvest();
							EhfChronicTherapyInvestPK ehfChronicTherapyInvestPK=new EhfChronicTherapyInvestPK(lStrChronicId,attachmentVO.getTestId());
							ehfChronicTherapyInvest.setId(ehfChronicTherapyInvestPK);
							//ehfChronicTherapyInvest.setChronicCatCode(attachmentVO.getIcdProcCode());
							ehfChronicTherapyInvest.setActiveyn("Y");
							ehfChronicTherapyInvest.setCrtDt(lCurrentDate);
							ehfChronicTherapyInvest.setCrtUsr(ChronicOpVO.getCrtUsr());
							
							ehfChronicTherapyInvest.setFilename(attachmentVO.getFileName());
							ehfChronicTherapyInvest.setInvestigationStage("CaseReg");
							ehfChronicTherapyInvest.setAttachTotalPath(attachmentVO.getFileReportPath());

								genericDao.save(ehfChronicTherapyInvest);
							}
							if(ChronicOpVO.getTeleDocremarks()!=null)
							{
								ehfChronicPatientHosDgnsi.setTreatingDocRmks(ChronicOpVO.getTeleDocremarks());
							}
						}
						
				
				/*removing the existing prescription details*/
				
							String delQuery="";
							delQuery = "DELETE FROM EhfChronicPatientDrug epd where epd.id.chronicId='"+lStrChronicId+"'";
							session.createQuery( delQuery ).executeUpdate();
							session.flush();
							session.clear();
							//Setting Prescription Details
							float totDrugAmount=0;
							EhfChronicPatientDrug ehfDrugs=null;
							
							for(DrugsVO drugsVO : ChronicOpVO.getDrugs())
							{
								ehfDrugs = new EhfChronicPatientDrug(); 
								
								
								/*String caseStatus=config.getString("CO-MEDCO-SAVE");
								StringBuffer query =null;
								EhfChronicPatientDrug existPatientDrugs = null;
								
								try
								{
									query = new StringBuffer();
						    	    query.append("select epd.id.drugId ");
						    	    query.append(" from EhfChronicPatientDtl ep,EhfChronicCaseDtl ec,EhfChronicPatientDrug epd");
						    	    query.append(" where ep.cardNo='"+ehfChronicPatientDtl.getCardNo()+"'  ");
						    	    query.append(" and ec.chronicStatus='"+caseStatus+"' and ep.chronicId=epd.id.chronicId and epd.asriDrugCode='"+drugsVO.getDrugName()+"'");
						    	    
						    	    Long drugId = (Long) session.createQuery(query.toString()).uniqueResult();
						    	    if(drugId!=null && drugId>0)
						    	    {
						    	    	existPatientDrugs = genericDao.findById(EhfChronicPatientDrug.class, Long.class, drugId);
						    	    }
						    	   
									}
									
						    	
								catch(Exception e)
						    	{
						    		e.printStackTrace();
									GLOGGER.error("Exception Occurred in saveCaseDetails() while registering patient drugs in PatientDaoImpl class."+e.getMessage());
						    	}
							*/
						
								/*EhfChronicPatientDrugPK ehfChronicPatientDrugPK=new EhfChronicPatientDrugPK();
								ehfChronicPatientDrugPK.setChronicId(lStrChronicId);
								ehfChronicPatientDrugPK.setDrugId(drugsVO.getDrugId());
								*/
								
								/*added for calculating drug amount*/
								float drugAmount=0;
								if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit"))
								{
								String pkgCode=ChronicOpVO.getOpPkgCode();
								String drugCode=drugsVO.getDrugName();
								StringBuffer query =null;
								//float drugAmount=0;
								
								String dosage=drugsVO.getDosage();
								int dos=0;
								String medicationPeriod=drugsVO.getMedicationPeriod();
								try
								{
									query = new StringBuffer();
						    	    query.append("select b.unitPrice from EhfmChronicDrugMst a,EhfmChronicDrugPricMst b");
						    	    query.append(" WHERE a.id.chemicalCode=b.id.drugCode AND a.id.chemicalCode='"+drugCode+"' AND a.id.chronicPkgCode='"+pkgCode+"'");
						    	    query.append(" AND b.id.active='Y' AND a.activeYn='Y' ");
						    	    String unitPrice = (String) session.createQuery(query.toString()).uniqueResult();
						    	    
						    	    if(("OD").equalsIgnoreCase(dosage))
						    	    	dos=1;
						    	    else if(("BD").equalsIgnoreCase(dosage))
						    	    	dos=2;
						    	    else if(("TID").equalsIgnoreCase(dosage))
						    	    	dos=3;
						    	    else if(("QID").equalsIgnoreCase(dosage))
						    	    	dos=4;
						    	    if(medicationPeriod!=null && unitPrice!=null)
						    	    drugAmount=dos*Float.parseFloat(medicationPeriod)*Float.parseFloat(unitPrice);
								}
								catch(Exception e)
								{
									e.printStackTrace();
									GLOGGER.info("chronic Id is :"+lStrChronicId);
									GLOGGER.error("Exception Occurred in getting chronic drug unit price while registering patient drugs in chronicDaoImpl class."+e.getMessage());
								}
								
								if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit"))
								{
									totDrugAmount=Math.round(totDrugAmount+drugAmount);
								}
								}
								/*saving in drugs table*/			
								if(drugsVO!=null  )
								{
								ehfDrugs.setId( new EhfChronicPatientDrugPK(lStrChronicId,chronicNo,drugsVO.getDrugId()));								
								ehfDrugs.setDrugTypeCode(drugsVO.getDrugTypeName());
								ehfDrugs.setDrugSubTypeCode(drugsVO.getDrugSubTypeName());
								ehfDrugs.setAsriDrugCode(drugsVO.getDrugName());
								ehfDrugs.setRoute(drugsVO.getRoute());
								ehfDrugs.setStrength(drugsVO.getStrength());
								ehfDrugs.setDosage(drugsVO.getDosage());
								ehfDrugs.setMedicationPeriod(drugsVO.getMedicationPeriod());
								
								/*Date expDt=new Date();
								SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
								String dateInString = drugsVO.getExpiryDt();
								try{
								expDt=sd.parse(dateInString);
						
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
								
								ehfDrugs.setBatchNo(drugsVO.getBatchNo());
								ehfDrugs.setExpiryDt(expDt);
								*/
								ehfDrugs.setDrugAmount(String.valueOf(drugAmount));
								}
								/*if(existPatientDrugs!=null)
								{
									ehfDrugs.setIssueFromDt(existPatientDrugs.getIssueFromDt());
									ehfDrugs.setIssueToDt(existPatientDrugs.getIssueToDt());
								}*/
								ehfDrugs.setCrtDt(lCurrentDate);
								ehfDrugs.setCrtUsr(ChronicOpVO.getCrtUsr());
								genericDao.save(ehfDrugs);
							}
if(totDrugAmount!=0)
	
{
	
	ehfChronicCaseDtl.setTotDrugAmount(String.valueOf(Math.round(totDrugAmount)));
}
							//Setting Therapy Details
							ehfChronicPatientHosDgnsi.setOpCategoryCode(ChronicOpVO.getOpCatCode());
							ehfChronicPatientHosDgnsi.setOpPackageCode(ChronicOpVO.getOpPkgCode());
							ehfChronicPatientHosDgnsi.setOpIcdCode(ChronicOpVO.getOpIcdCode());

							//ehfChronicCaseDtl.setChronicStatus(config.getString("CO-MEDCO-SAVE"));
							//ehfEmpCaseDtls.setCaseStatus(config.getString("CO-MEDCO-SAVE"));
							
							
							
						ehfChronicPatientHosDgnsi.setDiagnosisType(ChronicOpVO.getDiagnosisType());
						ehfChronicPatientHosDgnsi.setMainCategoryCode(ChronicOpVO.getMainCatName());
						ehfChronicPatientHosDgnsi.setCategoryCode(ChronicOpVO.getCatName());
						ehfChronicPatientHosDgnsi.setSubCategoryCode(ChronicOpVO.getSubCatName());
						ehfChronicPatientHosDgnsi.setDiseaseCode(ChronicOpVO.getDiseaseName());
						ehfChronicPatientHosDgnsi.setDiseaseAnatCode(ChronicOpVO.getDisAnatomicalName());
						GLOGGER.error("Info Message after setting Diagnosis details in Ehf Patient Hosp Diagnosis for case Id: "+lStrChronicId +" and patient Id : "+ehfChronicPatientDtl.getChronicId());
						
						if(ChronicOpVO.getDiagnosedBy()!=null){
						String selDocType=ChronicOpVO.getDiagnosedBy();
						ehfChronicPatientHosDgnsi.setDoctType(selDocType);
						}
						List<LabelBean> docLst = null;
						if(ChronicOpVO.getDoctorName()!=null){
						if(!"0".equalsIgnoreCase(ChronicOpVO.getDoctorName()))
						{
							docLst=getSelDocDetails(ChronicOpVO.getDiagnosedBy(),ehfChronicPatientDtl.getRegHospId(),ChronicOpVO.getDoctorName());                

						}
						if(!"0".equalsIgnoreCase(ChronicOpVO.getDoctorName()))
						{
							ehfChronicPatientHosDgnsi.setDoctId(ChronicOpVO.getDoctorName());
							if(docLst!=null && docLst.size()>0){
								ehfChronicPatientHosDgnsi.setDoctName(docLst.get(0).getLVL());
								ehfChronicPatientHosDgnsi.setDoctRegNo(docLst.get(0).getID());
								ehfChronicPatientHosDgnsi.setDoctQuali(docLst.get(0).getDSGNNAME());
								ehfChronicPatientHosDgnsi.setDoctMobNo(docLst.get(0).getVALUE());
							}
						}
						else
						{
							ehfChronicPatientHosDgnsi.setDoctId(ChronicOpVO.getDoctorName());
							ehfChronicPatientHosDgnsi.setDoctName(ChronicOpVO.getOtherDocName());
							ehfChronicPatientHosDgnsi.setDoctRegNo(ChronicOpVO.getDocRegNo());
							ehfChronicPatientHosDgnsi.setDoctQuali(ChronicOpVO.getDocQual());
							ehfChronicPatientHosDgnsi.setDoctMobNo(ChronicOpVO.getDocMobileNo());
						}
						}
						

						ehfChronicPatientHosDgnsi.setCaseAdmType(ChronicOpVO.getAdmissionType());
						
						
						ehfChronicPatientHosDgnsi.setPoliceStatName(ChronicOpVO.getPoliceStatName());
						
						
						
						ehfChronicPatientDtl.setLstUpdUsr(ChronicOpVO.getCrtUsr());            
						ehfChronicPatientDtl.setLstUpdDt(lCurrentDate);						
					
						genericDao.save(ehfChronicPatientHosDgnsi);
						genericDao.save(ehfChronicCaseDtl); 
						//genericDao.save(ehfEmpCaseDtls);
						genericDao.save(ehfChronicPatientDtl);
						if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit"))
						genericDao.save(ehfChronicAudit);
						chronicNo=chronicNo;
						
				}
				
			
			catch(Exception e)
			{
				e.printStackTrace();
				GLOGGER.error("Exception Occurred in saveCaseDetails() in PatientDaoImpl class."+e.getMessage());
				chronicNo=null;
			}
			finally
			{
				if(session!=null)
					session.close();
				if(factory!=null)
					factory.close();
			}
	
		return chronicNo;
	}
	
	
	
	/*added by venkatesh*/
	@Transactional	
	public String saveChronicInstallment(ChronicOPVO ChronicOpVO) throws Exception {
		String lStrChronicId=null;
		String chronicNo=null;
		String msg=null;
	
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date lCurrentDate=sdfw.parse(ChronicOpVO.getCrtDt());
		Date date = new Date();
		
		String temp=null;
		SessionFactory factory= hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		List<AttachmentVO> lSplAttachList=ChronicOpVO.getAttachmentsList();
		EhfChronicAudit ehfChronicAudit = new EhfChronicAudit();
		chronicNo=ChronicOpVO.getChronicNo();
	
		EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(ChronicOpVO.getChronicID(),chronicNo);	
		EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl(); 
			
		
			lStrChronicId=ChronicOpVO.getChronicID();
			
			
		
		
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			
			EhfChronicPatientDtl ehfChronicPatientDtl=new EhfChronicPatientDtl();			
			
			try{

				if(ChronicOpVO.getChronicID()!=null){
					ehfChronicPatientDtl=genericDao.findById(EhfChronicPatientDtl.class,String.class,ChronicOpVO.getChronicID());
					
					String lStrTestDesc=null;
					List<AttachmentVO> lAttachList=ChronicOpVO.getGenAttachmentsList();
					for(AttachmentVO attachmentVO:lAttachList){

						EhfChronicPatientTest ehfChronicPatientTest=new EhfChronicPatientTest();
						ehfChronicPatientTest.setSno(Long.parseLong(attachmentVO.getAttachId()));
						ehfChronicPatientTest.setTestId(attachmentVO.getTestId());
						if(lStrTestDesc!=null){
							lStrTestDesc=lStrTestDesc+","+attachmentVO.getTestName();
						}
						else{
							lStrTestDesc=attachmentVO.getTestName();  
						}
						ehfChronicPatientTest.setChronicId(lStrChronicId);
						ehfChronicPatientTest.setChronicNo(chronicNo);
						ehfChronicPatientTest.setAttachPath(attachmentVO.getFileReportPath());                	            
						ehfChronicPatientTest.setCrtUsr(ChronicOpVO.getCrtUsr());
						ehfChronicPatientTest.setCrtDt(lCurrentDate);
						genericDao.save(ehfChronicPatientTest);
					}
					
					
						
					//saving in ehfChroniccase for chronic op
						String lStrHospitalCodeInCaseNo = ehfChronicPatientDtl.getRegHospId().substring(3);
						String nflag=config.getString("NFlag");
				
					
						ehfChronicCaseDtl.setId(new EhfChronicCaseDtlPK(lStrChronicId,chronicNo));
						ehfChronicCaseDtl.setHospCode(ehfChronicPatientDtl.getRegHospId());
						ehfChronicCaseDtl.setTotPckgAmt(String.valueOf(ChronicOpVO.getPKGAMOUNT()));
						ehfChronicCaseDtl.setSchemeId(ehfChronicPatientDtl.getSchemeId());
						ehfChronicCaseDtl.setChronicRegnDate(date);
						ehfChronicCaseDtl.setCrtDt(date);
						ehfChronicCaseDtl.setCrtUsr(ChronicOpVO.getCrtUsr());
					  if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit"))
						{
							ehfChronicCaseDtl.setChronicStatus(config.getString("CO-MEDCO-SCREENING"));	
						}
					  
						
						
	
						//saving in audit for chronic op 
						
						Long lActOrder = 1L;
						StringBuffer lQueryBuffer = new StringBuffer();
						String args[] = new String[1];
						args[0] = chronicNo;
						lQueryBuffer
								.append(" select max(au.id.actOrder) as COUNT from EhfChronicAudit au where au.id.chronicNo=? ");
					
						List<ChronicOPVO> actOrderList = genericDao.executeHQLQueryList(
								ChronicOPVO.class, lQueryBuffer.toString(), args);
						if (actOrderList != null && !actOrderList.isEmpty()
								&& actOrderList.get(0).getCOUNT() != null) {
							if (actOrderList.get(0).getCOUNT().longValue() >= 0)
								lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
						}
						ehfChronicAudit.setId(new EhfChronicAuditPK(lStrChronicId,chronicNo,lActOrder));
						ehfChronicAudit.setActBy(ChronicOpVO.getCrtUsr());
/*						
 * 
 * if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Yes")){
							ehfAudit.setRemarks("Case Drafted");
						}else{
						ehfAudit.setRemarks(ChronicOpVO.getIpCaseRemarks());
						}*/
						ehfChronicAudit.setCrtUsr(ChronicOpVO.getCrtUsr());
						ehfChronicAudit.setCrtDt(date);
						ehfChronicAudit.setLangId(config.getString("en_US"));
						
					
						if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit")){
							
							ehfChronicAudit.setActId(config.getString("CO-MEDCO-SCREENING"));
							
							}
						
						
						for(AttachmentVO attachmentVO:lSplAttachList)
							{
							EhfChronicTherapyInvest ehfChronicTherapyInvest=new EhfChronicTherapyInvest();
							EhfChronicTherapyInvestPK ehfChronicTherapyInvestPK=new EhfChronicTherapyInvestPK(lStrChronicId,attachmentVO.getTestId());
							ehfChronicTherapyInvest.setId(ehfChronicTherapyInvestPK);
							//ehfChronicTherapyInvest.setChronicCatCode(attachmentVO.getIcdProcCode());
							ehfChronicTherapyInvest.setActiveyn("Y");
							ehfChronicTherapyInvest.setCrtDt(lCurrentDate);
							ehfChronicTherapyInvest.setCrtUsr(ChronicOpVO.getCrtUsr());
							
							ehfChronicTherapyInvest.setFilename(attachmentVO.getFileName());
							ehfChronicTherapyInvest.setInvestigationStage("CaseReg");
							ehfChronicTherapyInvest.setAttachTotalPath(attachmentVO.getFileReportPath());

								genericDao.save(ehfChronicTherapyInvest);
							}
							
						}
						
				
	
							//Setting Prescription Details
							float totDrugAmount=0;
							EhfChronicPatientDrug ehfDrugs=null;
							
							for(DrugsVO drugsVO : ChronicOpVO.getDrugs())
							{
								ehfDrugs = new EhfChronicPatientDrug(); 
								
								/*added for calculating drug amount*/
								float drugAmount=0;
								
								String pkgCode=ChronicOpVO.getOpPkgCode();
								String drugCode=drugsVO.getDrugName();
								StringBuffer query =null;
								//float drugAmount=0;
								
								String dosage=drugsVO.getDosage();
								int dos=0;
								String medicationPeriod=drugsVO.getMedicationPeriod();
								try
								{
									query = new StringBuffer();
						    	    query.append("select b.unitPrice from EhfmChronicDrugMst a,EhfmChronicDrugPricMst b");
						    	    query.append(" WHERE a.id.chemicalCode=b.id.drugCode AND a.id.chemicalCode='"+drugCode+"' AND a.id.chronicPkgCode='"+pkgCode+"'");
						    	    query.append(" AND b.id.active='Y' AND a.activeYn='Y' ");
						    	    String unitPrice = (String) session.createQuery(query.toString()).uniqueResult();
						    	    
						    	    if(("OD").equalsIgnoreCase(dosage))
						    	    	dos=1;
						    	    else if(("BD").equalsIgnoreCase(dosage))
						    	    	dos=2;
						    	    else if(("TID").equalsIgnoreCase(dosage))
						    	    	dos=3;
						    	    else if(("QID").equalsIgnoreCase(dosage))
						    	    	dos=4;
						    	    
						    	    drugAmount=dos*Float.parseFloat(medicationPeriod)*Float.parseFloat(unitPrice);
								}
								catch(Exception e)
								{
									e.printStackTrace();
									GLOGGER.error("Exception Occurred in getting chronic drug unit price while registering patient drugs in chronicDaoImpl class."+e.getMessage());
								}
								
								if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit"))
								{
									totDrugAmount=totDrugAmount+drugAmount;
								}
								
								/*saving in drugs table*/			
								if(drugsVO!=null  )
								{
								ehfDrugs.setId( new EhfChronicPatientDrugPK(lStrChronicId,chronicNo,drugsVO.getDrugId()));								
								ehfDrugs.setDrugTypeCode(drugsVO.getDrugTypeName());
								ehfDrugs.setDrugSubTypeCode(drugsVO.getDrugSubTypeName());
								ehfDrugs.setAsriDrugCode(drugsVO.getDrugName());
								ehfDrugs.setRoute(drugsVO.getRoute());
								ehfDrugs.setStrength(drugsVO.getStrength());
								ehfDrugs.setDosage(drugsVO.getDosage());
								ehfDrugs.setMedicationPeriod(drugsVO.getMedicationPeriod());
								
								/*Date expDt=new Date();
								SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
								String dateInString = drugsVO.getExpiryDt();
								try{
								expDt=sd.parse(dateInString);
						
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
								
								ehfDrugs.setBatchNo(drugsVO.getBatchNo());
								ehfDrugs.setExpiryDt(expDt);*/
								ehfDrugs.setDrugAmount(String.valueOf(drugAmount));
								}
								
								ehfDrugs.setCrtDt(lCurrentDate);
								ehfDrugs.setCrtUsr(ChronicOpVO.getCrtUsr());
								genericDao.save(ehfDrugs);
							}
if(totDrugAmount!=0)
	
{
	
	ehfChronicCaseDtl.setTotDrugAmount(String.valueOf(Math.round(totDrugAmount)));
}
													
						
						
						genericDao.save(ehfChronicCaseDtl); 
						
						genericDao.save(ehfChronicAudit);
						msg="Y";
						
				}
				
			
			catch(Exception e)
			{
				e.printStackTrace();
				msg="N";
				GLOGGER.error("Exception Occurred in saveCaseDetails() in PatientDaoImpl class."+e.getMessage());
				chronicNo=null;
			}
			finally
			{
				session.close();
				factory.close();
			}
	//}
		//else
		//{
		//	caseNo="Already Registered";
		//}
		return msg;
	}
	public String getSequence(String pStrSeqName)throws Exception
	  {
	    String lStrSeqRetVal = "";   
	    
	    try{
	     
	    	StringBuffer query = new StringBuffer();
	        query.append(" SELECT "+pStrSeqName+".NEXTVAL NEXTVAL  FROM DUAL ");
	        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());

	        if(seqList != null){
	        	
	          lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
	        }
	    }catch(Exception e){
	    
	    	e.printStackTrace();
	    	
	    }
	    
	    return lStrSeqRetVal;
	  }
	
	public List<LabelBean> getSelDocDetails(String doctorType,String hospId,String docId)throws Exception
	{
		String activeYn=config.getString("ActiveYn");
		String apprvStatus=config.getString("Doctor.ApprvStatus");
		SessionFactory factory=null;
		Session session=null;
		Query hQuery=null;
		List<LabelBean> doctorsList = new ArrayList<LabelBean>();
		try
		{
			String ramcoRole=config.getString("Group.Medco");
			if((config.getString("doctorType.Ramco")).equalsIgnoreCase(doctorType))
			{
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
				criteriaList.add(new GenericDAOQueryCriteria("hospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("regNo",docId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("cordType",ramcoRole,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfmMedcoMaster> ehfmMedcoMasterLst = genericDao.findAllByCriteria(EhfmMedcoMaster.class, criteriaList);
				for(EhfmMedcoMaster ehfmMedcoMaster:ehfmMedcoMasterLst)
				{
					LabelBean labelBean =new LabelBean();
					labelBean.setID(ehfmMedcoMaster.getRegNo());
					labelBean.setDSGNNAME(ehfmMedcoMaster.getQualification());
					labelBean.setVALUE(ehfmMedcoMaster.getMobileNo());
					labelBean.setLVL(ehfmMedcoMaster.getCordName());
					doctorsList.add(labelBean);
				}
			}
    		else if((config.getString("doctorType.DutyDoctor")).equalsIgnoreCase(doctorType))
    		{
    			try{
    			factory= hibernateTemplate.getSessionFactory();
    			session=factory.openSession();
    			StringBuffer query = new StringBuffer();
    			query.append("from EhfmDutyDctrs asd,EhfmDoctorQlfctns adq,EhfmQualMst aqm where asd.isActiveYn='"+activeYn+"' and asd.id.hospId='"+hospId+"'and asd.apprvStatus='"+apprvStatus+"'and asd.id.regNum = adq.id.regNum and adq.id.qualId = aqm.qualId and  adq.isActiveYn = '"+activeYn+"' and asd.id.regNum ='"+docId+"' order by asd.doctorName");
    			hQuery=session.createQuery(query.toString());
    			Iterator ite = hQuery.list().iterator();
    			while(ite.hasNext())
    			{
    			Object[] pair = (Object[]) ite.next();
    			EhfmDutyDctrs asrimDutyDctrs=(EhfmDutyDctrs)pair[0];
    			EhfmQualMst asrimQualMst= (EhfmQualMst)pair[2];
    			LabelBean labelBean =new LabelBean();
    			labelBean.setID(asrimDutyDctrs.getId().getRegNum());
    			labelBean.setDSGNNAME(asrimQualMst.getQual());
    			labelBean.setVALUE(asrimDutyDctrs.getMobile());
    			labelBean.setLVL(asrimDutyDctrs.getDoctorName());
    			doctorsList.add(labelBean);
    			}
    		}
    			catch(Exception e){
    				e.printStackTrace();
    			}
    			
    		}
			else if((config.getString("doctorType.InHouseDoctor")).equalsIgnoreCase(doctorType) || (config.getString("doctorType.Consultant")).equalsIgnoreCase(doctorType))
			{
				String consultant_Yn=null;
				if((config.getString("doctorType.InHouseDoctor")).equalsIgnoreCase(doctorType))
					consultant_Yn="N";
				else
					consultant_Yn="Y";  
				factory= hibernateTemplate.getSessionFactory();
				session=factory.openSession();
				try{
				StringBuffer query = new StringBuffer();
				query.append("from EhfmSplstDctrs asd,EhfmDoctorQlfctns adq,EhfmQualMst aqm where asd.isConsultant='"+consultant_Yn+"'and asd.isActiveYn='"+activeYn+"' and asd.id.hospId='"+hospId+"'and asd.apprvStatus='"+apprvStatus+"'and asd.id.regNum = adq.id.regNum and adq.id.qualId = aqm.qualId and  adq.isActiveYn = '"+activeYn+"' and asd.id.regNum ='"+docId+"' order by asd.splstName");
				hQuery=session.createQuery(query.toString());
				@SuppressWarnings("unchecked")
				Iterator<Object[]> ite = hQuery.list().iterator();
				while(ite.hasNext())
				{
					Object[] pair = (Object[]) ite.next();
					EhfmSplstDctrs ehfmSplstDctrs=(EhfmSplstDctrs)pair[0];
					EhfmQualMst ehfmQualMst= (EhfmQualMst)pair[2];
					LabelBean labelBean =new LabelBean();
					labelBean.setID(ehfmSplstDctrs.getId().getRegNum());
					labelBean.setDSGNNAME(ehfmQualMst.getQual());
					labelBean.setVALUE(ehfmSplstDctrs.getMobile());
					labelBean.setLVL(ehfmSplstDctrs.getSplstName());
					doctorsList.add(labelBean);
				}
			}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getSelDocDetails() in PatientDaoImpl class."+e.getMessage());
		}
		finally
		{
			if(factory!=null && session!=null)
			{
				session.close();
				factory.close();
			}
		}
		return doctorsList;

	}

	/*public void updateOldChronicCase(String cardNo,String userId) throws Exception
	{
		String chronicOP=config.getString("PatientIPOP.ChronicOP");
		String caseStatus=config.getString("CO-MEDCO-SAVE");
		List<PatientVO> caseList = new ArrayList<PatientVO>();
		String existCaseId=null;
		StringBuffer query =null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date crtDt = sdfw.parse(sdfw.format(new Date()));
		try
		{
			query = new StringBuffer();
    	    query.append("select ec.caseId as caseId");
    	    query.append(" from EhfPatient ep,EhfCase ec");
    	    query.append(" where ep.cardNo='"+cardNo+"' and ep.patientIpop='"+chronicOP+"' and  ep.patientId=ec.casePatientNo");
    	    query.append(" and ec.caseStatus='"+caseStatus+"'");
    	    caseList=genericDao.executeHQLQueryList ( PatientVO.class,query.toString());
    	    if(caseList.size()>0)
    	    {
    	    	existCaseId=caseList.get(0).getCaseId();
    	    }
    	    
    	    if(existCaseId!=null)
    	    {
    	    EhfCase ehfChronicCaseDtl=genericDao.findById(EhfCase.class, String.class, existCaseId);
    	    ehfChronicCaseDtl.setChronicStatus(config.getString("CASE.ChronicCaseClosed"));
    	    ehfChronicCaseDtl.setLstUpdDt(crtDt);
    	    ehfChronicCaseDtl.setLstUpdUsr(userId);
    	    ehfChronicCaseDtl.setChronicEndDt(crtDt);
    	    genericDao.save(ehfChronicCaseDtl);
    	    }
    	}
		catch(Exception e)
    	{
    		e.printStackTrace();
    		GLOGGER.error("Exception Occurred in getChronicDetails() in PatientDaoImpl class."+e.getMessage());
    	}
	
	}*/
	public String checkCaseTherapy(String caseId, String asriCode,String icdCatCode,String icdProcCode) {
	    String flag="true";
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
		criteriaList.add(new GenericDAOQueryCriteria("caseId",caseId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("asriCatCode",asriCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("icdCatCode",icdCatCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("icdProcCode",icdProcCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfCaseTherapy> ehfTherapyLst = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
				if(ehfTherapyLst.size()>0)
					flag="false";
		return flag;
		}
	
	
	
	
	
	@Override
	public PreauthVO getPatientFullDtls(String lStrChronicId,String chronicNo,String showAll) {
		PreauthVO preauthVO = new PreauthVO();
		EhfChronicPatientDtl ehfChronicPatientDtl=genericDao.findById(EhfChronicPatientDtl.class,String.class,lStrChronicId);
		EhfChronicPatientHosDgnsi ehfChronicPatientHosDgnsi = genericDao.findById(EhfChronicPatientHosDgnsi.class, String.class,lStrChronicId);
		if(ehfChronicPatientHosDgnsi != null)
		{
			preauthVO.setPresentIllness(ehfChronicPatientHosDgnsi.getHistoryIllness());	
			preauthVO.setDocName(ehfChronicPatientHosDgnsi.getDoctName());
			preauthVO.setDoctorDtls(ehfChronicPatientHosDgnsi.getDoctId());
			preauthVO.setDocQual(ehfChronicPatientHosDgnsi.getDoctQuali());
			preauthVO.setDocMobNo(ehfChronicPatientHosDgnsi.getDoctMobNo());
			preauthVO.setDocReg(ehfChronicPatientHosDgnsi.getDoctRegNo());;
			preauthVO.setDocType(ehfChronicPatientHosDgnsi.getDoctType());
			preauthVO.setAdmType(ehfChronicPatientHosDgnsi.getCaseAdmType());
			preauthVO.setOpCatCode(ehfChronicPatientHosDgnsi.getOpCategoryCode());
			preauthVO.setOpPkgCode(ehfChronicPatientHosDgnsi.getOpPackageCode());
			preauthVO.setOpIcdCode(ehfChronicPatientHosDgnsi.getOpIcdCode());
			preauthVO.setPastIllness(ehfChronicPatientHosDgnsi.getPastHistory());
			preauthVO.setFamilyHis(ehfChronicPatientHosDgnsi.getFamilyHistory());
			
			preauthVO.setPastHisOthers(ehfChronicPatientHosDgnsi.getPastHistoryOthr());
			preauthVO.setFamilyHisOthers(ehfChronicPatientHosDgnsi.getFamilyHistoryOthr());
			
			StringBuffer therapyQuery=new StringBuffer();
			therapyQuery.append("select b.icdOpPkgName as opPackageName ,b.icdCatName as opCatName from EhfChronicPatientHosDgnsi a, ");
			therapyQuery.append(" EhfmChronicPkgMst b where a.chronicId='"+lStrChronicId+"' and " );
			therapyQuery.append("  a.opPackageCode=b.id.icdChronicPkgCode");
			therapyQuery.append(" and a.opIcdCode=b.id.icdCatCode ");
			List<ChronicOPVO> therapyList=genericDao.executeHQLQueryList(ChronicOPVO.class, therapyQuery.toString());		
			preauthVO.setTherapyList(therapyList);
			if(therapyList.size()>0)
			{
			preauthVO.setOpCatName(therapyList.get(0).getOpCatName());
			preauthVO.setOpPackageName(therapyList.get(0).getOpPackageName());
			preauthVO.setOpIcdName(therapyList.get(0).getOpIcdName());
			}	
			
			
			
			
			
			
			
			
			preauthVO.setComplaintType(ehfChronicPatientHosDgnsi.getComplaintType());
			preauthVO.setPatComplaint(ehfChronicPatientHosDgnsi.getPatientComplaint());
			StringTokenizer str = null;
			StringTokenizer str1 = null;
			
			
			if(ehfChronicPatientHosDgnsi.getComplaintType()!="" && ehfChronicPatientHosDgnsi.getPatientComplaint()!="" 
				&& ehfChronicPatientHosDgnsi.getPatientComplaint()!=null && ehfChronicPatientHosDgnsi.getPatientComplaint()!=null)
			{
				str =  new StringTokenizer(ehfChronicPatientHosDgnsi.getComplaintType(),"~");
				str1=  new StringTokenizer(ehfChronicPatientHosDgnsi.getPatientComplaint(),"~");
				
				
				List<LabelBean> complaintType=new ArrayList<LabelBean>();
				List<LabelBean> complaint=new ArrayList<LabelBean>();
				
				
				if(str!=null)
				{
					while(str.hasMoreTokens())
					{
						String code=str.nextToken();
						StringBuffer compTypeQuery=new StringBuffer();
						compTypeQuery.append("select distinct ecm.mainComplName as VALUE from EhfmComplaintMst ecm where ecm.id.mainComplCode='"+code+"'");
						try{
						complaintType = genericDao.executeHQLQueryList(LabelBean.class, compTypeQuery.toString());
						}
							
						catch(Exception e)
						{
							e.printStackTrace();
							GLOGGER.error("error occured in getting complaint name in chronicOpDaoImpl()");
						}
						if(complaintType!=null && !complaintType.isEmpty())
						{
							if(preauthVO.getComplaintTypeName() == null || preauthVO.getComplaintTypeName().equalsIgnoreCase(""))
							{
								//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
								preauthVO.setComplaintTypeName(complaintType.get(0).getVALUE() + "( "+code+" )")	;
							}
							else
							{
								//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
								preauthVO.setComplaintTypeName( preauthVO.getComplaintTypeName() +" , "+ complaintType.get(0).getVALUE() + "( "+code+" )")	;
							}
							
						}
					}
				}
				
				
			
					
					if(str1!=null)
					{
						while(str1.hasMoreTokens())
						{
							String code=str1.nextToken();
							StringBuffer compQuery=new StringBuffer();
							compQuery.append("select distinct ecm.complName as VALUE from EhfmComplaintMst ecm where ecm.id.complCode='"+code+"'");
							try{
								complaint = genericDao.executeHQLQueryList(LabelBean.class, compQuery.toString());
								
							}
							catch(Exception e)
							{
								e.printStackTrace();
								GLOGGER.error("error occured in getting complaint name in chronicOpDaoImpl()");
							}
							if(complaint!=null && !complaint.isEmpty())
							{
								if(preauthVO.getComplaintName() == null || preauthVO.getComplaintName().equalsIgnoreCase(""))
								{
									//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
									preauthVO.setComplaintName(complaint.get(0).getVALUE() + "( "+code+" )")	;
								}
								else
								{
									//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
									preauthVO.setComplaintName(preauthVO.getComplaintName() +" , "+ complaint.get(0).getVALUE() + "( "+code+" )")	;
								}
								
							}
						}
					}
					
			
			}
			
			preauthVO.setPastIllness(ehfChronicPatientHosDgnsi.getPastHistory());
			preauthVO.setExamFindg(ehfChronicPatientHosDgnsi.getExamntnFindings());
			
			if(ehfChronicPatientHosDgnsi.getPastHistoryOthr()!=null && !ehfChronicPatientHosDgnsi.getPastHistoryOthr().equalsIgnoreCase(""))
				preauthVO.setPastIllenesOthr(ehfChronicPatientHosDgnsi.getPastHistoryOthr());
			if(ehfChronicPatientHosDgnsi.getPastHistoryCancers()!=null && !ehfChronicPatientHosDgnsi.getPastHistoryCancers().equalsIgnoreCase(""))
				preauthVO.setPastIllenesCancers(ehfChronicPatientHosDgnsi.getPastHistoryCancers());
			if(ehfChronicPatientHosDgnsi.getPastHistoryEnddis()!=null && !ehfChronicPatientHosDgnsi.getPastHistoryEnddis().equalsIgnoreCase(""))
				preauthVO.setPastIllenesEndDis(ehfChronicPatientHosDgnsi.getPastHistoryEnddis());
			if(ehfChronicPatientHosDgnsi.getPastHistorySurg()!=null && !ehfChronicPatientHosDgnsi.getPastHistorySurg().equalsIgnoreCase(""))
				preauthVO.setPastIllenesSurg(ehfChronicPatientHosDgnsi.getPastHistorySurg());
			
			preauthVO.setPersonalHis( ehfChronicPatientHosDgnsi.getPersonalHistory());
			
			EhfChronicPatientPerHstry ehfChronicPatientPerHstry= genericDao.findById(EhfChronicPatientPerHstry.class, String.class, lStrChronicId);
			List<String> lstPerHis = new ArrayList<String>();
			if(ehfChronicPatientPerHstry != null)
			{
				if(ehfChronicPatientPerHstry.getAppetite() != null)
				{
					lstPerHis.add(ehfChronicPatientPerHstry.getAppetite());	
				}
				if(ehfChronicPatientPerHstry.getDiet() != null)
				{
					lstPerHis.add(ehfChronicPatientPerHstry.getDiet());	
				}
				if(ehfChronicPatientPerHstry.getBowels() !=null)
				{
					lstPerHis.add(ehfChronicPatientPerHstry.getBowels());		
				}
				if(ehfChronicPatientPerHstry.getNutrition() != null)
				{
					lstPerHis.add(ehfChronicPatientPerHstry.getNutrition());
				}
				if(ehfChronicPatientPerHstry.getKnownAllergies() != null)
				{
					lstPerHis.add(ehfChronicPatientPerHstry.getKnownAllergies());	
					if(ehfChronicPatientPerHstry.getKnownAllergies().contains("PR5.1"))
						preauthVO.setTestKnown(ehfChronicPatientPerHstry.getKnownAllergies());
				}
				if(ehfChronicPatientPerHstry.getAddictions() != null)
				{
					lstPerHis.add(ehfChronicPatientPerHstry.getAddictions());	
					if(ehfChronicPatientPerHstry.getAddictions().contains("PR6.1"))
					preauthVO.setTest(ehfChronicPatientPerHstry.getAddictions());
				}
				if(ehfChronicPatientPerHstry.getMaritalStatus() != null)
				{
					lstPerHis.add(ehfChronicPatientPerHstry.getMaritalStatus());	
				}
			}
			preauthVO.setLstPerHis(lstPerHis);
			
			/**
			 * get Examination findings
			 */
			EhfChronicPatientExamFind ehfPatientExamFind = genericDao.findById(EhfChronicPatientExamFind.class, String.class, lStrChronicId);
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
				preauthVO.setClubbingOfFingers(ehfPatientExamFind.getClubOfFingrtoes());
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
			
			preauthVO.setFamilyHis(ehfChronicPatientHosDgnsi.getFamilyHistory());	
			if(ehfChronicPatientHosDgnsi.getFamilyHistoryOthr()!=null && !ehfChronicPatientHosDgnsi.getFamilyHistoryOthr().equalsIgnoreCase(""))
				preauthVO.setFamilyHistoryOthr(ehfChronicPatientHosDgnsi.getFamilyHistoryOthr());
			
			/**
			 * Investigation Details--wm_concate(gim.invName );
			 */
			StringBuffer query=new StringBuffer();
			
			
			/*Union Query to fetch other investigations*/
			
			query.append( "select gim.inv_Main_Code as TESTKNOWN, gim.inv_Main_Name   as TEST,gim.inv_Name  as NAME, ");
			query.append( " substr(pt.chronic_No,-1,1) as INSTALLMENT,gim.inv_Code as SPLINVSTID,pt.attach_Path as ROUTE  ");
			query.append( " from Ehfm_Chronic_Invest_Mst gim,Ehf_Chronic_Patient_Tests pt, Ehf_Chronic_Patient_Hos_Dgnsis ecp        ");
			query.append( " where pt.test_Id=gim.inv_Code  and pt.chronic_Id='"+lStrChronicId+"' ");
			query.append( " and pt.chronic_No='"+chronicNo+"'  and gim.chronic_Dis_Code=ecp.op_Package_Code ");
			query.append( " and pt.chronic_Id=ecp.chronic_Id and gim.installment  in ( select substr(chronic_No,-1,1) from Ehf_Chronic_Patient_Tests where chronic_No='"+chronicNo+"' ) ");
			query.append( " union ");
			query.append( " select 'Others' as TESTKNOWN, 'others' as TEST,pt.test_name as NAME,substr(pt.chronic_No,-1,1) as INSTALLMENT, ");
			query.append( " pt.test_id as SPLINVSTID,pt.attach_Path as ROUTE  from ehf_chronic_patient_tests pt where chronic_no='"+chronicNo+"'  and pt.test_id like '%OI%'");
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			query.append("select gim.invMainCode as testKnown, gim.invMainName as test, gim.invName as name,substring(pt.chronicNo,-1,1) as installment,"); 
			query.append(" gim.id.invCode as splInvstId,pt.attachPath as route from EhfmChronicInvestMst gim,EhfChronicPatientTest pt,EhfChronicPatientHosDgnsi ecp ");			
			query.append(" where pt.testId=gim.id.invCode and pt.chronicId='"+lStrChronicId+"' ");
			
			/*if(!("Y").equalsIgnoreCase(showAll))
			{*/
			/*query.append(" and pt.chronicNo='"+chronicNo+"' ");
			query.append( " and gim.id.chronicDisCode=ecp.opPackageCode ");
			query.append( " and pt.chronicId=ecp.chronicId ");
			query.append( " and gim.id.installment  in ( select substring(chronicNo,-1,1) from EhfChronicPatientTest where chronicNo='"+chronicNo+"') ");
			
/*			}*/
			

			
			List<PreauthVO> list1=genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
			if(list1!=null && !list1.isEmpty())
			{
				preauthVO.setInvList(list1);
			}	
			
			query=new StringBuffer();
			query.append(" select EDM.id.chemicalCode as drugCode, ");
			query.append(" EDM.chemicalName as drugName,substring(EPD.id.chronicNo,-1,1) as installment,  ");
			
			query.append(" EPD.dosage as dosage,EPD.medicationPeriod as medicationPeriod,EPD.batchNo as batchNo,EPD.expiryDt as expDt, ");
			query.append(" concat(str(day(EPD.expiryDt)),'-',str(month(EPD.expiryDt)),'-',str(year(EPD.expiryDt))) as expiryDt from EhfChronicPatientDrug EPD,EhfmChronicDrugMst EDM, ");
			query.append("  EhfChronicPatientHosDgnsi ECP where EPD.asriDrugCode=EDM.id.chemicalCode ");
			query.append("  and  EPD.id.chronicId=ECP.chronicId and  ECP.opPackageCode=EDM.id.chronicPkgCode ");
			query.append("  and EPD.id.chronicId='"+lStrChronicId+"' ");
			if(!("Y").equalsIgnoreCase(showAll))
			{
			query.append("  and EPD.id.chronicNo='"+chronicNo+"' ");
			}
			List<DrugsVO> drugList=genericDao.executeHQLQueryList(DrugsVO.class, query.toString());
			if(drugList!=null && drugList.size()>0){
				preauthVO.setDrugList(drugList);
			}
		}
		
		
		return preauthVO;
	}
	
	/*to delete general investigations*/
	
	@Override
	public String deleteGenInvest(String chronicId, String investId) {
		String result="false";
		List<GenericDAOQueryCriteria> criteria=new ArrayList<GenericDAOQueryCriteria>();
		criteria.add(new GenericDAOQueryCriteria("chronicId",chronicId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteria.add(new GenericDAOQueryCriteria("testId",investId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));		
		List<EhfChronicPatientTest> list=genericDao.findAllByCriteria(EhfChronicPatientTest.class, criteria);
		for(EhfChronicPatientTest row: list){
			System.out.println("deleting");
			genericDao.delete(row);
			result="true";
		}
		return result;
	}
	
	
	@Override
	public List<CommonDtlsVO> getPatientCommonDtls(String chronicId){
		StringBuffer queryBuff = new StringBuffer();
		HashMap<Object,String> lQueryVal=new HashMap<Object,String>();
		int i=0;
		List<CommonDtlsVO> caseDetailsList = null;
		
		
		try
		{
			queryBuff.append(" select distinct p.name as PATIENTNAME , p.employeeNo as EMPLOYEENO, p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE  ");
			queryBuff.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT , cast(p.age as string) as AGEYEARS , case when p.slab ='P' then 'Private Ward' else 'Semi Private Ward' end as slabType ");
			queryBuff.append(" ,ec.id.chronicId  as CHRONICID , ec.claimNo as CLAIMNO  , ec.id.chronicId as CHRONICID , p.cardType as cardType ");
			queryBuff.append("  ,p.regHospId as INTIID, hm.hospName||''||hm.hospDispCode as HOSPNAME , case when p.gender='M' then 'Male' else case when p.gender='F' then 'Female' else p.gender end end as GENDER , a.cmbDtlName as STATUS ,ec.chronicStatus as CASESTAT   ");
			queryBuff.append("  , p.houseNo || ' ' || p.street as PATADDR, p.crtDt||'' as PATDT ");
			queryBuff.append(" , hm.houseNumber || ' ' || hm.street || ' ' || hm.hospCity as HOSPADDR , ");
			queryBuff.append(" ph.doctType as doctType,ph.doctId as DOCID,ph.doctName as DOCNAME,ph.doctQuali as DOCQUAL,ph.doctMobNo as DOCCONTACT,ph.doctRegNo as DOCREGNO,p.crtUsr as mithra,ec.schemeId as  scheme ");
			queryBuff.append(" from EhfChronicPatientDtl p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfChronicCaseDtl ec , EhfmHospitals hm ");
			queryBuff.append(" , EhfmCmbDtls a , EhfChronicPatientHosDgnsi ph ");
			queryBuff.append(" where p.chronicId = ec.id.chronicId and  p.districtCode = l1.id.locId and  ");
			queryBuff.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId  and ec.id.chronicId = '"+chronicId+"' ");
			queryBuff.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ec.chronicStatus ");
			queryBuff.append(" and p.chronicId =ph.chronicId ");

			caseDetailsList = genericDao.executeHQLQueryList(CommonDtlsVO.class, queryBuff.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return caseDetailsList;		
	}
	public String  getChronicInstallment(String chronicId){
		
		StringBuffer query = new StringBuffer();
		String installment = null;
		
		try
		{
			query.append("select MAX(TO_NUMBER(SUBSTR(a.CHRONIC_NO,LENGTH(a.CHRONIC_NO)))) as INSTALLMENT FROM EHF_CHRONIC_CASE_DTLS a");
			query.append(" where SUBSTR(a.CHRONIC_NO,LENGTH(a.CHRONIC_NO))!='D' and a.chronic_id='"+chronicId+"'");
			List<CommonDtlsVO> instList = genericDao.executeSQLQueryList(CommonDtlsVO.class, query.toString());
		if(instList!=null && instList.size()>0)
		{
			installment=String.valueOf(instList.get(0).getINSTALLMENT());
			
		}
		else
			installment=null; 
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		
		return installment;
}
	public boolean validateAttachments(String chronicNo,String caseStatus,String usrRole)
	{
		boolean status=false;
		StringBuffer query1 = new StringBuffer();
		StringBuffer query2 = new StringBuffer();
		int totAttach=0;
		Long chronicAttach = null;
		try
		{
		query1.append(" select count(distinct a.attachType) as chronicAttch  from EhfChronicDocAttch a,EhfAttachType b where a.chronicNo='"+chronicNo+"' and a.activeYN='Y' ");
		query1.append( " and a.attachType=b.id.docType and b.id.updType='chronicAttach' and b.docName='M' ");
		List<CommonDtlsVO> chronicAttachLst = genericDao.executeHQLQueryList(CommonDtlsVO.class, query1.toString());
		if(chronicAttachLst.size()>0)
		chronicAttach=chronicAttachLst.get(0).getChronicAttch();
		//chronicAttach = (Long) session.createQuery(query1.toString()).uniqueResult();
		
		query2.append(" select count(distinct b.id.attachType) as totAttach from EhfCaseStatusAttach b,EhfAttachType c where b.id.caseStatus='"+caseStatus+"' and b.id.userRole='"+usrRole+"'  ");
		query2.append( " and b.id.attachType=c.id.docType and c.id.updType='chronicAttach' and c.docName='M' and b.id.attachType<>'CD1666' and b.id.attachType<>'CD4455' ");
		List<CommonDtlsVO> totAttachLst = genericDao.executeHQLQueryList(CommonDtlsVO.class, query2.toString());
		totAttach=(int) totAttachLst.get(0).getTotAttach();

			
		//totAttach = (Long) session.createQuery(query1.toString()).uniqueResult();
		
		if(chronicAttach<totAttach)
		{
			status=false;
		}
		else
		{
			status=true;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		
		return  status;
	}

	
	public long getPackageAmount(String chronicId,String installment,String scheme)
	{
		StringBuffer query = new StringBuffer();
		long pkgAmount=0;
		try
		{
			
		
		query.append("select (to_number(a.consult_amt)+to_number(a.invest_amt)) as PKGAMOUNT  from EHFM_CHRONIC_FOLLOWUP_PACKAGES a, ");
		query.append(" ehf_chronic_patient_hos_dgnsis b");
		query.append(" where b.chronic_id='"+chronicId+"' and a.scheme='"+scheme+"' ");
		query.append(" and b.op_package_code=a.chronic_dis_code and a.act_order='"+installment+"'");
		List<CommonDtlsVO> pkgAmountLst = genericDao.executeSQLQueryList(CommonDtlsVO.class, query.toString());
		if(pkgAmountLst.size()>0)
		{
			pkgAmount=pkgAmountLst.get(0).getPKGAMOUNT().longValue();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pkgAmount;
		
		
	}
	
	
	public long getCasePackageAmount(String chronicId,String chronicNo)
	{
		StringBuffer query = new StringBuffer();
		long pkgAmount=0;
		try
		{
			
			query.append("select to_number(a.tot_Pckg_Amt) as PKGAMOUNT from EHF_CHRONIC_case_dtls a " );
			query.append( " where a.chronic_id='"+chronicId+"' and a.chronic_no='"+chronicNo+"'");
		
		List<CommonDtlsVO> pkgAmountLst = genericDao.executeSQLQueryList(CommonDtlsVO.class, query.toString());
		if(pkgAmountLst!=null && pkgAmountLst.size()>0)
		{
			pkgAmount=pkgAmountLst.get(0).getPKGAMOUNT().longValue();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pkgAmount;
		
	}
	@Override
	public List<ClaimsFlowVO> getAuditTrail(String chronicNo) {
		List<ClaimsFlowVO> lstWorkList = new ArrayList<ClaimsFlowVO>();
		String args[] = new String[1];
		args[0] =chronicNo;
		try{
			StringBuffer query = new StringBuffer();
			query.append(" select au.firstName ||''|| au.lastName as auditName ,a.remarks as cexRemark ,");
			query.append(" a.crtDt as chronicAuditDate,ac.grpShortName as auditRole,ac1.cmbDtlName as auditAction,a.apprvAmt as COUNT,a.userRole as roleId ");
			query.append(" from EhfChronicAudit a , EhfmGrps ac , EhfmUsers au,EhfmCmbDtls ac1   ");
			query.append(" where a.actId=ac1.cmbDtlId and  a.actBy = au.userId and ac.grpId=a.userRole ");
			//query.append(" and a.actId in ('CD62','CD63','CD64','CD65','CD66','CD67','CD68','CD69','CD70','CD104','CD105','CD106','CD107')");
			query.append(" and a.actId in ( ");
			String[] ChronicStatus = ClaimsConstants.CHRONICStatus;
			for(int i=0;i<ChronicStatus.length;i++){
				query.append(" '" + ChronicStatus[i]+"' ");
				if(i!=ChronicStatus.length-1)
					query.append(",");
			}
			query.append(" ) and a.id.chronicNo = ? ");
			query.append(" order by a.id.actOrder ");
			
			lstWorkList = genericDao.executeHQLQueryList(ClaimsFlowVO.class, query.toString(), args);
		}catch(Exception e)
		{
			//LOGGER.error("Error occured in getAuditTrail() in ClaimsFlowServiceImpl class."+e.getMessage());
			e.printStackTrace();	
			}
		return lstWorkList;
	}
public boolean validateChronicClaim(String chronicId,String chronicNo)
{
	String claimAmt;
	boolean claimInit=false;
	EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
	EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
	ehfChronicCaseDtl=genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
	claimAmt=ehfChronicCaseDtl.getClaimAmount();
	if(claimAmt!=null && claimAmt!="")
	{
		claimInit=true;
	}
	return claimInit;
	
}
@Transactional
public String saveChronicClaim(ChronicOPVO chronicOPVO)
{
	
	String msg=null;
	EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
	EhfChronicAudit ehfChronicAudit=new EhfChronicAudit();
	EhfCoexChklst ehfCoexChklst=new EhfCoexChklst();
	EhfCotdChklst ehfCotdChklst=new EhfCotdChklst();
	EhfChronicCaseClaim  ehfChronicCaseClaim=null;
	String totPackageAmt=null;
	Date date=new Date();
	if(chronicOPVO.getChronicNo()!=null)
	{
		ehfChronicCaseClaim=genericDao.findById(EhfChronicCaseClaim.class,String.class,chronicOPVO.getChronicNo());
	}
	if(ehfChronicCaseClaim==null)
	{
		ehfChronicCaseClaim=new EhfChronicCaseClaim();
		ehfChronicCaseClaim.setChronicId(chronicOPVO.getChronicID());
		ehfChronicCaseClaim.setChronicNo(chronicOPVO.getChronicNo());
		ehfChronicCaseClaim.setCrtDt(date);
		ehfChronicCaseClaim.setCrtUsr(chronicOPVO.getEMPLOYEENO());
	}
	
	
	EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicOPVO.getChronicID(),chronicOPVO.getChronicNo());
	ehfChronicCaseDtl=genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
	
	
	/*ADDED TO GET NEXT CHRONIC STATUS BASED ON ACTION PERFORMED*/
	
	String chronicStatus = chronicOPVO.getChronicStatus();
	EhfmWorkflowStatus ehfmWorkflowNextStatus = getEhfmWorkflowStatus(chronicOPVO);

	if(chronicStatus != null && ehfmWorkflowNextStatus != null )
	{
		msg = config.getString("chronic_msg_"+ehfmWorkflowNextStatus.getNextStatus());	
		
	}
	
	String nextChronicStatus=ehfmWorkflowNextStatus.getNextStatus();
	
	
	/*updating EHF_CHRONIC_CASE_DTLS*/
	if(ehfChronicCaseDtl!=null)
	{
		ehfChronicCaseDtl.setLstUpdDt(chronicOPVO.getDate());
		ehfChronicCaseDtl.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
		
		totPackageAmt=String.valueOf(chronicOPVO.getPKGAMOUNT());
		ehfChronicCaseDtl.setTotPckgAmt(totPackageAmt);
		ehfChronicCaseDtl.setChronicStatus(nextChronicStatus);
		if(chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("GROUP.COACO")))
		{
			long pckAppvAmt=Long.valueOf(chronicOPVO.getClaimAmt());
			ehfChronicCaseDtl.setPckAppvAmt((double)pckAppvAmt);
		}
		if(chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("Chronic.Medco")))
				{
			if(chronicOPVO.getActionDone()!=null && !config.getString("EHF.UpdateButton").equalsIgnoreCase(chronicOPVO.getActionDone()))
			ehfChronicCaseDtl.setClmSubDt(date);
				}
	
	}
	
	
	/*saving in EHF_CHRONIC_AUDIT*/
	
	Long lActOrder = 1L;
	StringBuffer lQueryBuffer = new StringBuffer();
	String args[] = new String[1];
	args[0] = chronicOPVO.getChronicNo();
	lQueryBuffer
			.append(" select max(au.id.actOrder) as COUNT from EhfChronicAudit au where au.id.chronicNo=? ");

	List<ChronicOPVO> actOrderList = genericDao.executeHQLQueryList(
			ChronicOPVO.class, lQueryBuffer.toString(), args);
	if (actOrderList != null && !actOrderList.isEmpty()
			&& actOrderList.get(0).getCOUNT() != null) {
		if (actOrderList.get(0).getCOUNT().longValue() >= 0)
			lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
	}
	ehfChronicAudit.setId(new EhfChronicAuditPK(chronicOPVO.getChronicID(),chronicOPVO.getChronicNo(),lActOrder));
	ehfChronicAudit.setActBy(chronicOPVO.getEMPLOYEENO());
	ehfChronicAudit.setApprvAmt(chronicOPVO.getClaimAmt());
	ehfChronicAudit.setUserRole(chronicOPVO.getUserGroup());
	ehfChronicAudit.setCrtUsr(chronicOPVO.getEMPLOYEENO());
	ehfChronicAudit.setCrtDt(date);
	ehfChronicAudit.setLangId(config.getString("en_US"));
	ehfChronicAudit.setActId(nextChronicStatus);
	ehfChronicAudit.setRemarks(chronicOPVO.getRemarks());

	/*Added to save details in EHF_CHRONIC_CASE_CLAIM */
	
	if(chronicOPVO.getUserGroup()!=null && chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("Chronic.Medco")))
	{
		if(chronicOPVO.getClaimAmt()!=0)
			ehfChronicCaseDtl.setClaimAmount(String.valueOf(chronicOPVO.getClaimAmt()));
		ehfChronicCaseClaim.setMedcoRemarks(chronicOPVO.getRemarks());
		ehfChronicCaseClaim.setClaiminitamt(chronicOPVO.getClaimAmt()+"");
		ehfChronicCaseClaim.setLstUpdDt(date);
		ehfChronicCaseClaim.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
		
		ehfChronicCaseClaim.setClaimBillAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
		if(chronicOPVO.getActionDone()!=null && !config.getString("EHF.UpdateButton").equalsIgnoreCase(chronicOPVO.getActionDone()))
		ehfChronicCaseClaim.setClaimBillDate(date);
		
		
		if(totPackageAmt!=null)
		{
		BigDecimal bg=new BigDecimal(totPackageAmt);
		
		ehfChronicCaseClaim.setTotClaimAmt(bg);
		
		}
		
	}
	else  if(chronicOPVO.getUserGroup()!=null && chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("Chronic.Mithra")))
	{
		ehfChronicCaseClaim.setMithraRemarks(chronicOPVO.getRemarks());
		ehfChronicCaseClaim.setLstUpdDt(date);
		ehfChronicCaseClaim.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
	}
	else  if(chronicOPVO.getUserGroup()!=null && chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("Group.COEX")))
	{
		ehfChronicCaseClaim.setCexRemarks(chronicOPVO.getRemarks());
		ehfChronicCaseClaim.setCexAprvAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
		ehfChronicCaseClaim.setLstUpdDt(date);
		ehfChronicCaseClaim.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
		
	}
	else  if(chronicOPVO.getUserGroup()!=null && chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("Group.COTD")))
	{
		ehfChronicCaseClaim.setCtdRemarks(chronicOPVO.getRemarks());
		ehfChronicCaseClaim.setCtdAprvAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
		ehfChronicCaseClaim.setLstUpdDt(date);
		ehfChronicCaseClaim.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
		
	}
	else  if(chronicOPVO.getUserGroup()!=null && chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("Group.COCH")))
	{
		ehfChronicCaseClaim.setChRemarks(chronicOPVO.getRemarks());
		ehfChronicCaseClaim.setChAprvAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
		ehfChronicCaseClaim.setLstUpdDt(date);
		ehfChronicCaseClaim.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
		if(chronicOPVO.getActionDone()!=null && config.getString("EHF.VerifyButton").equalsIgnoreCase(chronicOPVO.getActionDone()))
		{
			ehfChronicCaseClaim.setTotClaimAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
			ehfChronicCaseDtl.setPckAppvAmt((double)(chronicOPVO.getClaimAmt()));
		}
		ehfChronicCaseDtl.setChClAmount(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
	}
	else  if(chronicOPVO.getUserGroup()!=null && chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("GROUP.COACO")))
	{
		ehfChronicCaseClaim.setAcoRemarks(chronicOPVO.getRemarks());
		ehfChronicCaseClaim.setAcoAprvAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
		ehfChronicCaseClaim.setTotClaimAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
		ehfChronicCaseClaim.setLstUpdDt(date);
		ehfChronicCaseClaim.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
		
	}
	else  if(chronicOPVO.getUserGroup()!=null && chronicOPVO.getUserGroup().equalsIgnoreCase(config.getString("Group.CEO")))
	{
		ehfChronicCaseClaim.setCeoRemarks(chronicOPVO.getRemarks());
		ehfChronicCaseClaim.setCeoAprvAmt(BigDecimal.valueOf(chronicOPVO.getClaimAmt()));
		ehfChronicCaseClaim.setLstUpdDt(date);
		ehfChronicCaseClaim.setLstUpdUsr(chronicOPVO.getEMPLOYEENO());
		
	}

	
	
	
	/*END OF AUDIT*/
	
	/*saving in EhfCoexChklst*/
	
	if(("Y").equals(chronicOPVO.getCoexFlag()))
		
	{
		ehfCoexChklst.setAcquaintanceAttached(chronicOPVO.getExeAcqvrifyChklst());
		ehfCoexChklst.setAcquaintanceRemarks(chronicOPVO.getExeAcqverifyRemark());
		ehfCoexChklst.setCexRemarks(chronicOPVO.getRemarks());
		ehfCoexChklst.setCrtDt(chronicOPVO.getDate());
		ehfCoexChklst.setCrtUsr(chronicOPVO.getEMPLOYEENO());
		//ehfCoexChklst.setDatePatnameYn(datePatnameYn);
		
		ehfCoexChklst.setId(new EhfCoexChklstPK(chronicOPVO.getChronicID(),chronicOPVO.getChronicNo()));
		//ehfCoexChklst.setMandrprtYn(mandrprtYn);
		//ehfCoexChklst.setPatsatletterYn(patsatletterYn);
		//ehfCoexChklst.setPatsignYn(patsignYn);
		ehfCoexChklst.setPhotoMatching(chronicOPVO.getExeDisphotoChklst()); 
		ehfCoexChklst.setPhotomatchRemarks(chronicOPVO.getExeDisphotoremark()); 
		ehfCoexChklst.setPhotoRemarks(chronicOPVO.getExePatphotoRemark()); 
		ehfCoexChklst.setPhotoYn(chronicOPVO.getExePatphotoChklst());  
		ehfCoexChklst.setReportsAttached(chronicOPVO.getExereprtcheckChklst()); 
		ehfCoexChklst.setReportsRemarks(chronicOPVO.getExeReprtcheckRemark()); 
		//ehfCoexChklst.setRprtsignYn(rprtsignYn);
		
	}
		
    if(("Y").equals(chronicOPVO.getCotdFlag()))
		
	{
    	ehfCotdChklst.setCrtDt(chronicOPVO.getDate());
    	ehfCotdChklst.setCrtUsr(chronicOPVO.getEMPLOYEENO());
    	ehfCotdChklst.setExeRmksVerified(chronicOPVO.getFtdRemarkvrifedChklst());
    	//ehfCotdChklst.setBenificiaryEnq(chronicOPVO.getFtdBeneficiryChklst());
    	ehfCotdChklst.setExeRmksRemarks(chronicOPVO.getFtdRemarkvrifedRemark());
    	//ehfCotdChklst.setBenificiaryRmrks(chronicOPVO.getFtdBeneficiryRemark());
    	
    	ehfCotdChklst.setId(new EhfCotdChklstPK(chronicOPVO.getChronicID(),chronicOPVO.getChronicNo()));
    	ehfCotdChklst.setRemarks(chronicOPVO.getRemarks());
    	
	}
		
		
		
		try{
			genericDao.save(ehfChronicCaseDtl);
			genericDao.save(ehfChronicAudit);
			genericDao.save(ehfChronicCaseClaim);
			if(("Y").equals(chronicOPVO.getCoexFlag()))
			{
			genericDao.save(ehfCoexChklst);
			}
			if(("Y").equals(chronicOPVO.getCotdFlag()))
			{
			genericDao.save(ehfCotdChklst);
			}
			
			return msg;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
		
	
}
private EhfmWorkflowStatus getEhfmWorkflowStatus(ChronicOPVO chronicOPVO)
{
	EhfmWorkflowStatus ehfmWorkflowStatus = genericDao.findById(EhfmWorkflowStatus.class, EhfmWorkflowStatusId.class,new EhfmWorkflowStatusId(chronicOPVO.getChronicStatus(),chronicOPVO.getUserGroup(),chronicOPVO.getActionDone()));	
    return ehfmWorkflowStatus;
}


public List<LabelBean> getOpPkgList() throws Exception {
	List<LabelBean> opPkgList= new ArrayList<LabelBean>();
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct a.id.icdChronicPkgCode as ID , a.icdOpPkgName as VALUE from EhfmChronicPkgMst a where a.activeYn='"+config.getString("ActiveYn")+"'  order by a.icdOpPkgName asc");     
		opPkgList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		GLOGGER.error("Exception Occurred in getOpPkgList() in chronicOpDaoImpl class."+e.getMessage());
	}
	return opPkgList;
}

@Override
public List<LabelBean> getOpDrugSubList(String drugTypeCode) {
	List<LabelBean> drugSubList = null;	
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append(" select distinct edm.therMainGroupCode as  ID , edm.therMainGrpName as VALUE from ");
		
			query.append(" EhfmChronicDrugMst edm ");
		query.append("  where edm.mainGrpCode='"+drugTypeCode+"' and edm.activeYn='Y' order by edm.therMainGrpName asc");
		drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
	}
	
	return drugSubList;
}
@Override
public List<LabelBean> getOpPharSubList(String drugTypeCode) {
	List<LabelBean> drugSubList = null;	
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.pharSubGrpCode as  ID , edm.pharSubGrpName as VALUE from ");
		
			query.append(" EhfmChronicDrugMst edm ");
		query.append(" where edm.therMainGroupCode='"+drugTypeCode+"' and edm.activeYn='Y' order by edm.pharSubGrpName asc");
		drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
	}
	
	return drugSubList;
}
@Override
public List<LabelBean> getOpChemSubList(String pharSubCode) {
	List<LabelBean> drugSubList = null;	
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.cheSubGrpCode as  ID , edm.cheSubGrpName as VALUE from ");
		
			query.append(" EhfmChronicDrugMst edm ");
		query.append(" where edm.pharSubGrpCode='"+pharSubCode+"' and edm.activeYn='Y' order by edm.cheSubGrpName asc");
		drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
	}
	
	return drugSubList;
}
@Override
public List<LabelBean> getChemSubList(String cSubGrpCode) {
	List<LabelBean> drugSubList = null;	
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.id.chemicalCode as  ID , edm.chemicalName as VALUE from ");
		
			query.append(" EhfmChronicDrugMst edm ");
		query.append("  where edm.cheSubGrpCode='"+cSubGrpCode+"' and edm.activeYn='Y' order by edm.chemicalName asc");
		drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
	}
	
	return drugSubList;
}












/*added to get drugs List based on packages for chronic op*/
public List<LabelBean> getChronicDrugs(String opPkgCode) throws Exception {
	List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.mainGrpCode as ID,edm.mainGrpName as VALUE from ");
		query.append(" EhfmChronicDrugMst edm ");
		query.append(" where edm.activeYn='Y' and edm.id.chronicPkgCode='"+opPkgCode+"'order by edm.mainGrpName asc");
		drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
	}
	return drugsList;
}
/**
 * ;
 * @param opPkgCode
 * @return List<LabelBean> opIcdList
 * @function This Method is Used For getting Therapy OP ICD Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
 */
@Override
public List<LabelBean> getOpIcdList(String opCode,String scheme) throws Exception {
	List<LabelBean> opIcdList= new ArrayList<LabelBean>();
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct a.id.icdCatCode as ID , a.icdCatName as VALUE from EhfmChronicPkgMst a where a.activeYn='"+config.getString("ActiveYn")+"' and a.id.icdChronicPkgCode='"+opCode+"' and a.id.scheme='"+scheme+"' order by a.icdCatName asc");     
		opIcdList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		GLOGGER.error("Exception Occurred in getOpIcdList() in PatientDaoImpl class."+e.getMessage());
	}
	return opIcdList;
}
/*added to get investigations for chronic op*/
public List<LabelBean> getChronicInvestBlockName(String opPkgCode,String installment){
	List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct eim.invMainCode as ID,eim.invMainName as VALUE from EhfmChronicInvestMst eim where eim.activeYn='Y' and eim.id.chronicDisCode='"+opPkgCode+"' and eim.id.installment='"+installment+"' order by eim.invMainName asc");
		investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception occurred in getInvestBlockName() in CommonServiceImpl class."+e.getMessage());
	}
	return investigationsList;
}


@Override
public List<String> getchronicInvestigations(String lStrBlockId,String packageCode,String installment) {
	List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
	List<String> investList=new ArrayList<String>(); 
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct eim.id.invCode as ID,eim.invName as VALUE from EhfmChronicInvestMst eim where eim.activeYn='Y'  ");
		query.append(" and eim.invMainCode='"+lStrBlockId+"' and eim.id.installment='"+installment+"' and eim.id.chronicDisCode='"+packageCode+"' order by eim.invName asc");
		investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		for(LabelBean invest:investigationsList)
		{
			investList.add(invest.getID()+"~"+invest.getVALUE()+"@");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception occurred in getchronicInvestigations() ."+e.getMessage());
	}
	return investList;
}
public List<LabelBean> getChronicChemSubstance(String opPkgCode) throws Exception {
	List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.id.chemicalCode as ID,edm.chemicalName as VALUE from ");
		query.append(" EhfmChronicDrugMst edm ");
		query.append(" where edm.activeYn='Y' and edm.id.chronicPkgCode='"+opPkgCode+"'order by edm.chemicalName asc");
		drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
	}
	return drugsList;
}
public ChronicOPVO getCoexChkList(String chronicId,String chronicNo) throws Exception
{
	
	ChronicOPVO chronicOPVO=new ChronicOPVO();
	EhfCoexChklst ehfCoexChklst=new EhfCoexChklst();
	EhfCoexChklstPK ehfCoexChklstPK=new EhfCoexChklstPK(chronicId,chronicNo);
	ehfCoexChklst=genericDao.findById(EhfCoexChklst.class, EhfCoexChklstPK.class, ehfCoexChklstPK);
	if(ehfCoexChklst!=null)
	{
		chronicOPVO.setExeDisphotoChklst(ehfCoexChklst.getPhotoMatching());
		chronicOPVO.setExePatphotoChklst(ehfCoexChklst.getPhotoYn());
		chronicOPVO.setExeDisphotoremark(ehfCoexChklst.getPhotomatchRemarks());
		chronicOPVO.setExeAcqvrifyChklst(ehfCoexChklst.getAcquaintanceAttached());
		chronicOPVO.setExeAcqverifyRemark(ehfCoexChklst.getAcquaintanceRemarks());
		chronicOPVO.setExePatphotoRemark(ehfCoexChklst.getPhotoRemarks());
		chronicOPVO.setExereprtcheckChklst(ehfCoexChklst.getReportsAttached());
		chronicOPVO.setExeReprtcheckRemark(ehfCoexChklst.getReportsRemarks());
		

	}
	
	return chronicOPVO;
}
public Number getMonthsbetween(String chronicId,String chronicNo) throws Exception
{
	long months=0;
	Date regDate=null;
	Number diff=null;
	EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
	EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
	ehfChronicCaseDtl=genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
	if(ehfChronicCaseDtl!=null)
	{
		regDate=ehfChronicCaseDtl.getCrtDt();
	}
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("Select months_between(sysdate,a.CHRONIC_REGN_DATE) as DIFF from EHF_CHRONIC_CASE_DTLS a where a.CHRONIC_ID='"+chronicId+"' ORDER BY a.CRT_DT "); 
		List<LabelBean> monthsDiff = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
		if(monthsDiff!=null)
		{
			diff=monthsDiff.get(0).getDIFF();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("error occured in getMonthsbetween() in chronicOpDaoImpl"+e.getMessage());
	}
	return diff;
}
public ChronicOPVO getCotdChkList(String chronicId,String chronicNo) throws Exception
{
	ChronicOPVO chronicOPVO=new ChronicOPVO();
	EhfCotdChklst ehfCotdChklst=new EhfCotdChklst();
	EhfCotdChklstPK ehfCotdChklstPK=new EhfCotdChklstPK(chronicId,chronicNo);
	ehfCotdChklst=genericDao.findById(EhfCotdChklst.class, EhfCotdChklstPK.class, ehfCotdChklstPK);
	
	if(ehfCotdChklst!=null)
	{
		chronicOPVO.setFtdRemarkvrifedChklst(ehfCotdChklst.getExeRmksVerified());
		//chronicOPVO.setFtdBeneficiryChklst(ehfCotdChklst.getBenificiaryEnq());
		chronicOPVO.setFtdRemarkvrifedRemark(ehfCotdChklst.getExeRmksRemarks());
		//chronicOPVO.setFtdBeneficiryRemark(ehfCotdChklst.getBenificiaryRmrks());
		
	}
	return chronicOPVO;
}
public boolean cancelCase(String chronicId,String chronicNo,String user)
{
	Boolean status=false;
	Date date =new Date();
	EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
	EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
	ehfChronicCaseDtl=genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
	if(ehfChronicCaseDtl!=null)
	{
		ehfChronicCaseDtl.setLstUpdDt(date);
		ehfChronicCaseDtl.setLstUpdUsr(user);
		ehfChronicCaseDtl.setChronicStatus(config.getString("CHRONICOP-CASE-CANCELLED"));
		
	}
	try
	{
		genericDao.save(ehfChronicCaseDtl);
		status=true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("error occured in method cancelCase() in ChronicOpDaoImpl"+e.getMessage());
		
	}
	return status;
	
	
}

public boolean getUserPkgCode(String pkgCode,String cardNo)
{
	List<ChronicOPVO> chronicOpVo=new ArrayList<ChronicOPVO>();
	StringBuffer query =null;
	String[] args=new String[3];
	args[0]=config.getString("CO-MITHRA-CASE-REG");
	args[1]=config.getString("CO-MEDCO-SAVE");
	args[2]=config.getString("CHRONICOP-CASE-CANCELLED");
	try
	{
		query = new StringBuffer();
		query.append("SELECT A.OP_PACKAGE_CODE as VALID  FROM EHF_CHRONIC_PATIENT_HOS_DGNSIS A,EHF_CHRONIC_PATIENT_DTLS B,EHF_CHRONIC_CASE_DTLS C "); 
		query.append("WHERE A.CHRONIC_ID=B.CHRONIC_ID AND B.CHRONIC_ID=C.CHRONIC_ID AND B.CARD_NO='"+cardNo+"' and a.op_package_code='"+pkgCode+"' and C.chronic_status not in ('"+args[0]+"','"+args[1]+"','"+args[2]+"')");
		query.append(" and sysdate-C.chronic_regn_date <= 365");
		chronicOpVo = genericDao.executeSQLQueryList(ChronicOPVO.class,query.toString());
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("error occured in getUserPkgCode() in chronicOpDaoImpl"+e.getMessage());
	}
	if(chronicOpVo.size()>0)
	{
		return true;
	}
	else
	{
		return false;
	}
	
}

public Number getDaysBetween(String chronicId,String chronicNo)
{
	
	String chronicStatus = null;
	
	Number Diff=null;
	List<ChronicOPVO> chronicOPVO=new ArrayList<ChronicOPVO>();
	EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
	try{
		EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
		ehfChronicCaseDtl= genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
	if(ehfChronicCaseDtl != null)
	{
		chronicStatus = ehfChronicCaseDtl.getChronicStatus();
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("error occured in method getDaysBetween() in chronicOpDaoImpl " +e.getMessage());
	}
	
	chronicStatus=config.getString("CHRONIC_INSTALLMENT_READY_STATUS");
	if(chronicStatus!=null)
	{
	StringTokenizer chronicstat=new StringTokenizer(chronicStatus,"~");
	
	chronicNo=chronicId+"/1";
	int count=0;
	StringBuffer query=new StringBuffer();
	query.append("select (sysdate-a.chronic_regn_date) as DIFF from ehf_chronic_case_dtls a");
	query.append(" where a.chronic_id='"+chronicId+"' and a.chronic_no='"+chronicNo+"' ");
	/*query.append( " and a.chronic_status in ( ");
	while(chronicstat.hasMoreTokens())
	{
		if(count==0)
		query.append(" '"+chronicstat.nextToken()+"' ");
		else
		query.append(" , '"+chronicstat.nextToken()+"' ");
		count++;
	}
	query.append( " ) ");*/
	chronicOPVO=genericDao.executeSQLQueryList(ChronicOPVO.class,query.toString());
	
	}
	if(chronicOPVO!=null &&( chronicOPVO.size()>0 ))
	{
		 Diff=chronicOPVO.get(0).getDIFF();
	}
	return Diff;
	
}
/*added by venkatesh for getting scheme of medco as per mapped hospital*/ 

public String getMedcoScheme(String userId)
{
	String scheme=null;
	
	try
	{
	StringBuffer query=new  StringBuffer();
	List<LabelBean> SchemeList=new ArrayList<LabelBean>();
	query.append(" select b.scheme as schemeId from EhfmMedcoDtls a,EhfmHospitals b");
	query.append( " where a.id.hospId=b.hospId and a.id.medcoId='"+userId+"' ");
	SchemeList=genericDao.executeHQLQueryList(LabelBean.class,query.toString());
	
	if(SchemeList!=null)
	{
		scheme=SchemeList.get(0).getSchemeId();
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return scheme;
	
}
public long getPackageDrugAmount(ChronicOPVO  chronicOPVO)
{
	long drugAmount=0;
	String scheme=chronicOPVO.getSchemeId();
	String actOrder=chronicOPVO.getActOrder();
	String opPkgCode=chronicOPVO.getOpPkgCode();
	StringBuffer query=new StringBuffer();
	try
	{
	List<ChronicOPVO> drugAmtLst=new ArrayList<ChronicOPVO>();
	query.append(" SELECT a.drugAmt as drugAmt FROM EhfmChronicFollowupPackage a ");
	query.append(" where a.id.scheme='"+scheme+"' and a.activeYn='Y' and a.id.chronicDisCode='"+opPkgCode+"' and a.id.actOrder='"+actOrder+"' ");
	
	drugAmtLst=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString());
	
	if(drugAmtLst!=null && drugAmtLst.size()>0)
	{
		if(drugAmtLst.get(0).getDrugAmt()!=null)
		drugAmount=Long.valueOf(drugAmtLst.get(0).getDrugAmt());
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Error occured  in getPackageDrugAmount() method in chronicOpDaoImpl.java clas** ");
	}
	
	return drugAmount;
}

public float calculateDrugAmount(DrugsVO  drugsVO)
{
	float drugAmt=0;
	

	StringBuffer query=new StringBuffer();
	String dosage=drugsVO.getDosage();
	int dos=0;
	String medicationPeriod=drugsVO.getMedicationPeriod();
	String drugCode=drugsVO.getDrugName();
	String  pkgCode=drugsVO.getOpPkgCode();
	SessionFactory factory=hibernateTemplate.getSessionFactory();
	Session session=factory.openSession();
	try
	{
		query = new StringBuffer();
	    query.append("select b.unitPrice from EhfmChronicDrugMst a,EhfmChronicDrugPricMst b");
	    query.append(" WHERE a.id.chemicalCode=b.id.drugCode AND a.id.chemicalCode='"+drugCode+"' AND a.id.chronicPkgCode='"+pkgCode+"'");
	    query.append(" AND b.id.active='Y' AND a.activeYn='Y' ");
	    String unitPrice = (String) session.createQuery(query.toString()).uniqueResult();
	    
	    if(("OD").equalsIgnoreCase(dosage))
	    	dos=1;
	    else if(("BD").equalsIgnoreCase(dosage))
	    	dos=2;
	    else if(("TID").equalsIgnoreCase(dosage))
	    	dos=3;
	    else if(("QID").equalsIgnoreCase(dosage))
	    	dos=4;
	    if(medicationPeriod!=null && unitPrice!=null)
	    drugAmt=dos*Float.parseFloat(medicationPeriod)*Float.parseFloat(unitPrice);
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
	return drugAmt;
	
}

/*@Override
public List<LabelBean> getRouteList(String actCode) {
	List<LabelBean> routeList = null;	
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.routeTypeCode as ID,edm.routeTypeName as VALUE from ");
		
			query.append(" EhfmChronicDrugsMst eem,EhfmChronicRouteMst edm ");
		query.append("  where eem.actCode='"+actCode+"' and eem.id.routeCode=edm.routeCode and  eem.activeYn='Y' and edm.activeYn='Y'order by edm.routeTypeName asc");
		routeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception Occurred in getRouteList() in PatientDaoImpl class."+e.getMessage());
	}
	
	return routeList;
}
@Override
public List<LabelBean> getStrengthList(String actCode) {
	List<LabelBean> routeList = null;	
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.strengthUnitCode as ID,edm.strengthUnitName as VALUE from ");
		
			query.append(" EhfmChronicDrugsMst eem,EhfmChronicStrengthMst edm ");
		query.append(" where  eem.actCode='"+actCode+"' and eem.id.strengthCode=edm.strengthCode and edm.activeYn='Y' and eem.activeYn='Y' order by edm.strengthUnitName asc");
		routeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Exception Occurred in getRouteList() in PatientDaoImpl class."+e.getMessage());
	}
	
	return routeList;
}
*/

String getLatestChronicId(){

	
	return null;
}

public String calculatePackageAmt(String pkgCode,String actOrder,String scheme)
{
	String pkgAmount=null;
	long amt=0;
	
	try
	{
	StringBuffer  query=new StringBuffer();
	query.append( " select a.consultAmt as consultAmt,a.investAmt as investAmt from EhfmChronicFollowupPackage a  " );
	query.append( " where a.id.chronicDisCode='"+pkgCode+"' and a.id.scheme='"+scheme+"' and a.id.actOrder=1 and a.activeYn='Y'  ");
	
	List<ChronicOPVO> pkgLst=new ArrayList<ChronicOPVO>();
	pkgLst=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString());
	if(pkgLst!=null && pkgLst.size()>0)
	{
		if(pkgLst.get(0).getConsultAmt()!=null && pkgLst.get(0).getInvestAmt()!=null)
		amt=Long.valueOf(pkgLst.get(0).getConsultAmt())+Long.valueOf(pkgLst.get(0).getInvestAmt());
	}
	pkgAmount=amt+"";
	}
	catch(Exception e)
	{
		e.printStackTrace();
		GLOGGER.error("Error occured in Method calculatePackageAmt() in ChronicopDaoImpl class");
		
	}
	return pkgAmount;
}

public List<LabelBean> getChronicStatus() {

	 List<LabelBean> statusLst=new ArrayList<LabelBean>();
	 
	 try
	 {
	 StringBuffer query=new StringBuffer();
	 query.append(" select a.cmbDtlId as ID,a.cmbDtlName as VALUE from EhfmCmbDtls a ");
	 query.append(" where a.cmbDtlId in ( ");
	 
	 String status=config.getString("chronic_status");
	 String[] statLst=null;
	 if(status!=null)
	 statLst=status.split("~");
	 int count=0;
	 for(String stat:statLst)
		 {
		 
		 if(count>0)
			 query.append(" ,'"+stat+"' " );
		 else
		     query.append(" '"+stat+"' " );
		 
		 count++;
		 
	 }
	 query.append( " ) ");
	 
	 statusLst=genericDao.executeHQLQueryList(LabelBean.class,query.toString());
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 GLOGGER.error("error occured in getChronicStatus() method in chronicOPDaoImpl.class :"+e.getMessage());
	 }
	 
		return statusLst;
	 
	 }
	 
	
@Transactional
public String updateSentBackClaims(ChronicOPVO  chronicOPVO)
{
	
	
	String pendingFlag=chronicOPVO.getPendingFlag();
	String remarks=chronicOPVO.getRemarks();
	String actionDone=chronicOPVO.getActionDone();
	String chronicId="";
	
	String nextStatus=null;
	Date date=new Date();
	String msg=null;
	
	
		String chronicNo=chronicOPVO.getChronicNo();
		nextStatus=config.getString("EHF.chronicop.claims.CEOPendingUpdated");;
	
		
		
		EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
		int occurance = StringUtils.countOccurrencesOf(chronicNo, "/");
		if(occurance==3)
		{
			chronicId=chronicNo.substring(0, chronicNo.lastIndexOf('/'));	
			chronicOPVO.setChronicID(chronicId);
		
		}
		try
		{
			EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
			ehfChronicCaseDtl=genericDao.findById(EhfChronicCaseDtl.class,EhfChronicCaseDtlPK.class,ehfChronicCaseDtlPK);
		if(ehfChronicCaseDtl!=null)
		{
			ehfChronicCaseDtl.setChronicStatus(nextStatus);
			ehfChronicCaseDtl.setLstUpdDt(date);
			ehfChronicCaseDtl.setLstUpdUsr(chronicOPVO.getUserId());
			ehfChronicCaseDtl.setPendingWith("");
		}

		
		//saving in audit for ip/op/chronic op 
		
		Long lActOrder = 1L;
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		args[0] = chronicNo;
		lQueryBuffer
				.append(" select max(au.id.actOrder) as COUNT from EhfChronicAudit au where au.id.chronicNo=? ");
	
		List<ChronicOPVO> actOrderList = genericDao.executeHQLQueryList(
				ChronicOPVO.class, lQueryBuffer.toString(), args);
		if (actOrderList != null && !actOrderList.isEmpty()
				&& actOrderList.get(0).getCOUNT() != null) {
			if (actOrderList.get(0).getCOUNT().longValue() >= 0)
				lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
		}
		EhfChronicAudit ehfChronicAudit=new EhfChronicAudit();
		
		ehfChronicAudit.setId(new EhfChronicAuditPK(chronicId,chronicNo,lActOrder));
		ehfChronicAudit.setActBy(chronicOPVO.getUserId());

		ehfChronicAudit.setCrtUsr(chronicOPVO.getUserId());
		ehfChronicAudit.setCrtDt(date);
		ehfChronicAudit.setLangId(config.getString("en_US"));
		ehfChronicAudit.setActId(nextStatus);
		ehfChronicAudit.setRemarks(chronicOPVO.getRemarks());
		ehfChronicAudit.setUserRole(chronicOPVO.getUserRole());
		
	    
	    genericDao.save(ehfChronicCaseDtl);
	    genericDao.save(ehfChronicAudit);
	     
	    msg="Case Updated and Sent to CEO Successfully";
		}
		catch(Exception e)
		{
			msg="Failed to Save Remarks.Please try Again";
			e.printStackTrace();
			GLOGGER.error("Error occured in updateSentBackClaims() method in chronicopdaoimpl"+e.getMessage());
		}
	    
	    
	
	
	

	
	return msg;
	
}


}




