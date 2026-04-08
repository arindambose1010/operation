package com.ahct.flagging.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.configuration.CompositeConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.Session;

import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.vo.FlaggingVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria.CriteriaOperator;
import com.ahct.model.EhfFlagDtls;
import com.ahct.model.EhfFlagAttach;
import com.ahct.model.EhfFlagAudit;
import com.ahct.model.EhfFlagAuditId;
import com.ahct.model.EhfCase;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class FlaggingDaoImpl implements FlaggingDao
{
	String caseId;
	String flagId;
	String ID;
	String flagDocId;
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	
	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	static
		{
			configurationService = ConfigurationService.getInstance();
			config = configurationService.getConfiguration();
		}
	/**
	 * Sets the configuration service.
	 *
	 * @param configurationService the new configuration service
	 */
	public static void setConfigurationService(
			ConfigurationService configurationService) {
		FlaggingDaoImpl.configurationService = configurationService;
	}
	/*
	 * Implementation
	 * to get flags
	 * for Interface Dao*/
	@Override
	public List<LabelBean> getFlagList()
		{
			List<LabelBean> flagList=new ArrayList<LabelBean>();
			StringBuffer query=new StringBuffer();
			query.append("select cmbDtlId as ID,cmbDtlName as flagName from EhfmCmbDtls where cmbHdrId='"+config.getString("flag_hdr_id")+"'");
			flagList=genericDao.executeHQLQueryList(LabelBean.class,query.toString());
			return flagList;
		}
	/*
	 * Implementation
	 * to get districts
	 * for Interface service*/
	@Override
	public List<FlaggingVO> getDistricts(String stateId)
		{
			List<FlaggingVO> hospDistList=new ArrayList<FlaggingVO>();
			StringBuffer sb=new StringBuffer();
			sb.append("select l.id.locId as ID,l.locName as dist from EhfmLocations l where");
			if(!"both".equalsIgnoreCase(stateId))
					sb.append(" l.id.locParntId='"+stateId+"' and l.activeYn='Y' ");
			else
					sb.append(" l.id.locParntId in ('"+config.getString("andhra_hdr_id")+"','"+config.getString("telangana_hdr_id")+"') and l.activeYn='Y' ");
			
			hospDistList=genericDao.executeHQLQueryList(FlaggingVO.class,sb.toString());
			return hospDistList;
		}
	/*
	 * Implementation
	 * to get districts
	 * for Interface service*/
	@Override
	public List<FlaggingVO> getHospList(String stateId,String distId,String hospType)
		{
			List<FlaggingVO> hospList=new ArrayList<FlaggingVO>();
			StringBuffer sb=new StringBuffer();
			sb.append("select eh.hospId as ID,eh.hospName as hospName from EhfmHospitals eh where eh.hospDist ='"+distId+"' and eh.hospType='"+hospType+"'");
			hospList=genericDao.executeHQLQueryList(FlaggingVO.class,sb.toString());
			return hospList;
		}
	
	/*
	 * Implementation 
	 * to save the 
	 * Flag Details for 
	 * Interface Dao*/
	@Override
	public String saveFlagDtls(FlaggingVO flaggingVO) 
		{
			long actionOrder=0;
		
            EhfFlagDtls ehfFlagDtls=new EhfFlagDtls();
            EhfFlagAttach ehfFlagAttach=new EhfFlagAttach();
            EhfFlagAudit ehfFlagAudit=new EhfFlagAudit();
            EhfCase ehfCase=new EhfCase();
            
            String caseId=flaggingVO.getCaseId();
            String curStatus=flaggingVO.getFlagStatus();
            String flgNature=flaggingVO.getFlagNature();
            String locId=config.getString("flg_locId");
            String langId=config.getString("flg_langId");
            String crtUsr=flaggingVO.getCrtUsr();
 			Date crtDt=flaggingVO.getCrtDt();
            
 			ehfCase=genericDao.findById(EhfCase.class, String.class, caseId);
 			
            if((flaggingVO.getDeFlagId()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getDeFlagId())))
            	{
            		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
            		criteriaList.add(new GenericDAOQueryCriteria("id.flagId",flaggingVO.getDeFlagId(),CriteriaOperator.EQUALS));
    	    		actionOrder=genericDao.getMaxByPropertyCriteria(EhfFlagAudit.class,"id.actionOrder", criteriaList);
    	    		actionOrder=actionOrder+1;
            	}
            
    		if(actionOrder==0)
    			{
    				StringBuffer query=new StringBuffer();
    				query.append("select ehfm_flagId.nextval||'' ID from dual");
    				
    				List<FlaggingVO> flaggingVO1=new ArrayList<FlaggingVO>();
    				flaggingVO1=genericDao.executeSQLQueryList(FlaggingVO.class,query.toString());
            
    				if( (flaggingVO1!=null) && (flaggingVO1.size()>0))
    					{
            				ID="FLAG".concat(flaggingVO1.get(0).getID());
            				ehfFlagDtls.setFlagId(ID); 
            				ehfFlagDtls.setCaseId(caseId);
            				ehfFlagDtls.setNatureOfFlag(flgNature);
            				ehfFlagDtls.setCurrentStatusOfFlag(curStatus);
            					if(flaggingVO.getFlagNature()!=config.getString("money_Collection") && flaggingVO.getAmount()!=0)         			
            						ehfFlagDtls.setAmount(flaggingVO.getAmount());
            					else
            						ehfFlagDtls.setAmount(0);
            				ehfFlagDtls.setLocId(locId);
            				ehfFlagDtls.setLangId(langId);
            				ehfFlagDtls.setCrtUsr(crtUsr);
            				ehfFlagDtls.setCrtDt(crtDt); 
            				ehfFlagDtls.setAmountRef(flaggingVO.getAmountRef());
            				ehfFlagDtls.setGmOpFlag("N");
            				
            				ehfFlagAudit.setActId(flaggingVO.getFlagStatus());
            				if(curStatus.equalsIgnoreCase(config.getString("verified_Status")))
            					ehfCase.setFlagged("N");
            				if(!curStatus.equalsIgnoreCase(config.getString("verified_Status")))
            					ehfCase.setFlagged("Y");
            				
            				actionOrder=actionOrder+1;
    					}
    			}	
            		
    		if(actionOrder>1)
         		{
         			ID=flaggingVO.getDeFlagId();
         			
         			ehfFlagDtls = genericDao.findById(EhfFlagDtls.class,
							String.class,ID);
         			ehfFlagDtls.setLstUpdUsr(crtUsr);
         			ehfFlagDtls.setLstUpdDt(crtDt);
         			if((flaggingVO.getFdf()!=null)&&(flaggingVO.getFdf()!=""))
         				{
         					if("df".equalsIgnoreCase(flaggingVO.getFdf()))
         						{
         							ehfFlagDtls.setGmOpFlag("N");
         							ehfFlagDtls.setCurrentStatusOfFlag(config.getString("deFlagged_Status"));
         							ehfFlagAudit.setActId(config.getString("deFlagged_Status"));
         						}	
         					else if("f".equalsIgnoreCase(flaggingVO.getFdf()))
         						{
         							ehfFlagDtls.setCurrentStatusOfFlag(config.getString("updated_Status"));
         							ehfFlagAudit.setActId(config.getString("updated_Status"));
         							ehfCase.setFlagged("Y");
         						}
         					else if("fwd".equalsIgnoreCase(flaggingVO.getFdf()))
     							{
     								ehfFlagDtls.setCurrentStatusOfFlag(config.getString("jeo_Forwarded_Status"));
     								ehfFlagAudit.setActId(config.getString("jeo_Forwarded_Status"));
     								ehfCase.setFlagged("Y");
     							}
         				}	
         		}
            String[] attachments=flaggingVO.getDoc(); 
            String[] flagDocId=flaggingVO.getFileDocArr();
            for(int i=0;i<attachments.length;i++)
            	{
            		if(attachments[i]!=null)
            			{
            				if( flagDocId[i]!=null && !"".equalsIgnoreCase(flagDocId[i])) 
            					{
            						ehfFlagAttach.setFlagDocId(flagDocId[i]);
            						ehfFlagAttach.setFlagId(ID); 
            						ehfFlagAttach.setCaseId(caseId);
            						ehfFlagAttach.setAttachPath(attachments[i]);
            						ehfFlagAttach.setLocId(locId);
            						ehfFlagAttach.setLangId(langId);
            						ehfFlagAttach.setCrtUsr(crtUsr);
            						ehfFlagAttach.setCrtDt(crtDt);
            			
            						try{genericDao.save(ehfFlagAttach);}
            						catch(Exception e)
            							{
            								System.out.println("Saving failed in Flag Attach");
            								e.printStackTrace();
            							}
            					}			
            			}
            	}	
            
            ehfFlagAudit.setId(new EhfFlagAuditId(ID,actionOrder));
            ehfFlagAudit.setCaseId(caseId);
            ehfFlagAudit.setRemarks(flaggingVO.getFlagRemarks());
            ehfFlagAudit.setLocId(locId);
            ehfFlagAudit.setLangId(langId);
            ehfFlagAudit.setCrtUsr(crtUsr);
            ehfFlagAudit.setCrtDt(crtDt);
            ehfFlagAudit.setActUsrGrp(flaggingVO.getLoggedUsrGrp());
            ehfFlagDtls.setAmountRef(flaggingVO.getAmountRef());
            try
            	{
            	
            		genericDao.save(ehfFlagDtls);
               		genericDao.save(ehfFlagAudit);
               		if("df".equalsIgnoreCase(flaggingVO.getFdf()))
               			{
               				StringBuffer subQuery=new StringBuffer();
               				subQuery.append(" select count(*) as FLAGGEDCASES from EhfFlagDtls e where caseId='"+caseId+"'");
               				subQuery.append(" and e.currentStatusOfFlag not in ('"+config.getString("deFlagged_Status")+"','"+config.getString("verified_Status")+"')");
               				List<FlaggingVO> fVO3=genericDao.executeHQLQueryList(FlaggingVO.class,subQuery.toString());
               				if(fVO3.size()>0)
               					{
               						if(fVO3.get(0).getFLAGGEDCASES().intValue()==0)
               							ehfCase.setFlagged("N");
               						else
               							ehfCase.setFlagged("Y");
               					}
               			}	
               		genericDao.save(ehfCase);
            	}
            catch(Exception e)
            	{
            		System.out.println("Save not successful in flagging");
            		e.printStackTrace();
            	}
            return ID;
		}
	
	/*
	 * Implementation 
	 * to retrieve the 
	 * Flag Details for 
	 * Interface Dao*/
	@Override
	public List<FlaggingVO> getFlaggedCases(FlaggingVO flaggingVO)
		{
			List<FlaggingVO> flaggingVOLst=null; 
			caseId=flaggingVO.getCaseId();
			flagId=flaggingVO.getFlagId();
			//String loggedUsrGrp=flaggingVO.getLoggedUsrGrp();
			
			StringBuffer query =new StringBuffer();
					query.append("select fe.caseId as caseId,fe.flagId as flagId,fe.amount as amount,fe.amountRef as amountRef,ecd2.cmbDtlName as flagStatus,");
					query.append("fa.crtDt as crtDt,fa.crtUsr as crtUsr,fe.natureOfFlag as flagNatureId,ed.dsgnName as role,");
					query.append("u.firstName as firstName,u.middleName as middleName,u.lastName as lastName,");
					query.append("fa.remarks as flagRemarks,fa.actId as actId,fa.id.actionOrder as actionOrder,fe.lstUpdUsr as lstUpdUsr,");
					query.append("ecd1.cmbDtlName as flagNature,fe.gmOpFlag as gmOpFlag,ecd3.cmbDtlName as currentStatus,(sysdate - fe.crtDt) as dateDif ");
					query.append("from EhfFlagDtls fe,EhfmUsers u,EhfmDesignation ed,EhfFlagAudit fa,");
					query.append("EhfmCmbDtls ecd1,EhfmCmbDtls ecd2,EhfmCmbDtls ecd3 where ");
					query.append("fe.caseId='"+caseId+"'and fa.caseId='"+caseId+"' and fa.crtUsr=u.userId and ed.id.dsgnId=u.dsgnId ");
					query.append("and fe.caseId=fa.caseId and fe.flagId=fa.id.flagId and fe.natureOfFlag=ecd1.cmbDtlId ");
					query.append("and fa.actId=ecd2.cmbDtlId and fe.currentStatusOfFlag=ecd3.cmbDtlId ");
					query.append(" order by fe.flagId,fa.id.actionOrder ");
					
					flaggingVOLst=genericDao.executeHQLQueryList(FlaggingVO.class, query.toString());
					
			return flaggingVOLst;
		}
	
	/*
	 * Implementation
	 * to get the
	 * FlagAttachments */
	@Override
	public List<FlaggingVO> getflagAttach(String caseId,String flagId,String flagDocId)
		{
			this.caseId=caseId;
			this.flagId=flagId;
			this.flagDocId=flagDocId;
			
			List<FlaggingVO> flaggingVOLst=null; 
			StringBuffer query=new StringBuffer();
			query.append("select flagId as flagId,caseId as caseId,flagDocId as flagDocId,attachPath as attachPath ");
			query.append("from EhfFlagAttach where caseId='"+caseId+"' and flagId='"+flagId+"'");
			if(flagDocId!=null&&flagDocId!="")
				query.append(" and flagDocId='"+flagDocId+"'");
			flaggingVOLst=genericDao.executeHQLQueryList(FlaggingVO.class,query.toString());
			
		return flaggingVOLst;	
		}
	
	/*
	 * Implementation
	 * to get all
	 * Flagged Cases */
	@SuppressWarnings("unchecked")
	@Override
	public List<FlaggingVO> getAllFlaggedCases(FlaggingVO flaggingVO)
		{
			List<FlaggingVO> flaggingVOLst=null;
			StringBuffer sb=new StringBuffer();
			SessionFactory factory=hibernateTemplate.getSessionFactory();
			Session session=factory.openSession();
			String patState=null;
			//sb.append("select efd.flagId as flagId,efd.caseId as caseId,ecd.cmbDtlName as flagNature,efd.currentStatusOfFlag as flagStatus,");
			//sb.append("efd.crtDt as crtDt from ehfFlagDtls efd,EhfmCmbDtls ecd where efd.natureOfFlag=ecd.cmbDtlId");
			try{
			sb.append(" select ef.flagId as flagId,ecd.cmbDtlName as currentStatus,ef.caseId as caseId,");
			sb.append(" ep.cardNo as cardNo,ef.crtDt as crtDt,el1.locName as patDist,");
			sb.append(" eh.hospName as hospName,el2.locName as hospDist,eh.hospType as hospType ");
			sb.append(" from EhfFlagDtls ef,EhfCase ec,EhfPatient ep,EhfmHospitals eh,EhfmLocations el1,EhfmLocations el2,EhfmCmbDtls ecd");
			if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DC_Grp"))  || 
					flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DM_Grp")) ||
					flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("NTL_Grp")))
				sb.append(" ,EhfmHospTlDcMpg ehtm ");
			
					
			if(flaggingVO.getFlaggedCasesForApproval()==null || "".equalsIgnoreCase(flaggingVO.getFlaggedCasesForApproval())
					|| !flaggingVO.getFlaggedCasesForApproval().equalsIgnoreCase("Y"))
				{
					if(flaggingVO.getLoggedUsrGrp()!=null && !"".equalsIgnoreCase(flaggingVO.getLoggedUsrGrp()))
						{
							if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("Medco_Grp")))
								sb.append(",EhfmMedcoDtls emdl" );
							if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("Mithra_Grp")))
								sb.append(",EhfmHospMithraDtls ehmd" );
						}	
				}
			
			
			sb.append(" where ef.caseId=ec.caseId and ec.casePatientNo=ep.patientId "); 
			sb.append(" and ec.caseHospCode=eh.hospId and ep.districtCode=el1.id.locId and eh.hospDist=el2.id.locId");
			sb.append(" and ef.currentStatusOfFlag=ecd.cmbDtlId ");
		            
			if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DC_Grp")))
				sb.append( " and ehtm.hospId=ec.caseHospCode and ehtm.dcId='"+flaggingVO.getLoggedUsrId()+"' " );
			if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DM_Grp")))
				sb.append( " and ehtm.hospId=ec.caseHospCode and ehtm.dmId='"+flaggingVO.getLoggedUsrId()+"' " );
			if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("NTL_Grp")))
				sb.append( " and ehtm.hospId=ec.caseHospCode and ehtm.tlId='"+flaggingVO.getLoggedUsrId()+"' " );
		            
			if((flaggingVO.getCaseId()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getCaseId())))
				sb.append(" and ef.caseId='"+flaggingVO.getCaseId()+"'");
			
			if((flaggingVO.getCardNo()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getCardNo())))
				sb.append(" and ep.cardNo='"+flaggingVO.getCardNo()+"'");
			
			if((flaggingVO.getPatState()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getPatState()))&&(!"-1".equalsIgnoreCase(flaggingVO.getPatState())))
				{
					if("S17".equalsIgnoreCase(flaggingVO.getPatState()))
						patState=config.getString("AP");	
					else if("S35".equalsIgnoreCase(flaggingVO.getPatState()))
						patState=config.getString("TG");	
					else if("both".equalsIgnoreCase(flaggingVO.getPatState()))
						patState=config.getString("COMMON");	
					
					sb.append(" and ep.schemeId='"+patState+"'");
				}
				
			
			if((flaggingVO.getPatDist()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getPatDist()))&&(!"-1".equalsIgnoreCase(flaggingVO.getPatDist())))
				sb.append(" and ep.districtCode='"+flaggingVO.getPatDist()+"'");
			
			if((flaggingVO.getFromDt()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getFromDt())))
				sb.append(" and ef.crtDt like TO_DATE('"+flaggingVO.getFromDt()+"','DD-MM-YYYY')");
			
			if((flaggingVO.getHospState()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getHospState()))&&(!"-1".equalsIgnoreCase(flaggingVO.getHospState())))
				sb.append(" and eh.stateCode='"+flaggingVO.getHospState()+"'");
			
			if((flaggingVO.getHospDist()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getHospDist()))&&(!"-1".equalsIgnoreCase(flaggingVO.getHospDist())))
				sb.append(" and eh.hospDist='"+flaggingVO.getHospDist()+"'");
			
			if((flaggingVO.getHospType()!='\0')&&(flaggingVO.getHospType()!=' '))
				sb.append(" and eh.hospType='"+flaggingVO.getHospType()+"'");
			
			if((flaggingVO.getHospName()!=null)&&(!"".equalsIgnoreCase(flaggingVO.getHospName()))&&(!"-1".equalsIgnoreCase(flaggingVO.getHospName())))
				sb.append(" and eh.hospId='"+flaggingVO.getHospName()+"'");
			
			if(flaggingVO.getFlaggedCasesForApproval()!=null && !"".equalsIgnoreCase(flaggingVO.getFlaggedCasesForApproval())
														&& flaggingVO.getFlaggedCasesForApproval().equalsIgnoreCase("Y"))
				{
				if(flaggingVO.getLoggedUsrGrp()!=null && !"".equalsIgnoreCase(flaggingVO.getLoggedUsrGrp()))
					{
						if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("GMOP_Grp")))
							{
							sb.append(" and( (ef.gmOpFlag='Y' and ef.currentStatusOfFlag not in ('"+config.getString("jeo_Forwarded_Status")+"'))");
							sb.append(" or (ef.crtUsr='"+flaggingVO.getLoggedUsrId()+"' and ef.natureOfFlag not in ('"+config.getString("money_Collection")+"')))");
							}	
						if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("JEO_Empanel_Grp")))
								sb.append(" and ef.gmOpFlag='Y' and ef.currentStatusOfFlag='"+config.getString("jeo_Forwarded_Status")+"'");
					
						if(!flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DC_Grp"))  && 
								!flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DM_Grp")) &&
								!flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("NTL_Grp")) &&
								!flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("JEO_Empanel_Grp"))&&
								!flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("GMOP_Grp")))
									sb.append(" and ef.crtUsr='"+flaggingVO.getLoggedUsrId()+"' and ef.natureOfFlag not in ('"+config.getString("money_Collection")+"')");
						if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DC_Grp"))  || 
								flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("DM_Grp")) ||
								flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("NTL_Grp")))
									sb.append(" and ef.gmOpFlag='N' ");
					/*	
						if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("Mithra_Grp")))
								sb.append(" and ef.crtUsr='"+flaggingVO.getLoggedUsrId()+"'");
						if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("Medco_Grp")))
							sb.append(" and ef.crtUsr='"+flaggingVO.getLoggedUsrId()+"'");*/
					}
				}
			if(flaggingVO.getFlaggedCasesForApproval()!=null && !"".equalsIgnoreCase(flaggingVO.getFlaggedCasesForApproval())
					&& flaggingVO.getLoggedUsrGrp()!=null && flaggingVO.getFlaggedCasesForApproval().equalsIgnoreCase("Y") && !flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("JEO_Empanel_Grp")))
				{
								sb.append(" and ef.currentStatusOfFlag in ('"+config.getString("flagged_Status")+"','"+config.getString("updated_Status")+"')" );
				}
			
			if(flaggingVO.getFlaggedCasesForApproval()==null || "".equalsIgnoreCase(flaggingVO.getFlaggedCasesForApproval())
					|| !flaggingVO.getFlaggedCasesForApproval().equalsIgnoreCase("Y"))
				{
					if(flaggingVO.getLoggedUsrGrp()!=null && !"".equalsIgnoreCase(flaggingVO.getLoggedUsrGrp()))
						{
							if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("Mithra_Grp")))
								sb.append(" and ec.caseHospCode=ehmd.id.hospId and ehmd.id.mithraId='"+flaggingVO.getLoggedUsrId()+"' and ehmd.activeYN='Y' ");
							if(flaggingVO.getLoggedUsrGrp().equalsIgnoreCase(config.getString("Medco_Grp")))
								sb.append(" and ec.caseHospCode=emdl.id.hospId and emdl.id.medcoId='"+flaggingVO.getLoggedUsrId()+"' ");
						}	
				}
			
			if(flaggingVO.getSchemeId()!=null && !"".equalsIgnoreCase(flaggingVO.getSchemeId()))
				{
					String append=null;
					if(flaggingVO.getSchemeId().equalsIgnoreCase(config.getString("COMMON")))
						append="'"+config.getString("AP")+"','"+config.getString("TG")+"'";
					else
						append="'"+flaggingVO.getSchemeId()+"'";
					
						sb.append( " and ec.schemeId in ( "+append+" ) " );
				}
			
			sb.append(" order by ef.currentStatusOfFlag desc");
			
			if(flaggingVO.getEnd_index()!=0)
				flaggingVOLst=session.createQuery(sb.toString()).setFirstResult(flaggingVO.getStart_index()).setMaxResults(flaggingVO.getEnd_index()).setResultTransformer(Transformers.aliasToBean(FlaggingVO.class)).list();
			
			else if((flaggingVO.getStart_index()==0)&&(flaggingVO.getEnd_index()==0))
				flaggingVOLst=session.createQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(FlaggingVO.class)).list();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				session.close();
				factory.close();
			}
			return flaggingVOLst;
		}
	/*
	 * Implementation
	 * to get 
	 * Authority*/
	@Override
	public FlaggingVO checkAuthority(List<LabelBean> rolesList)
		{ 
			FlaggingVO flaggingVO=new FlaggingVO();
			flaggingVO.setAuthority("S");
			if(rolesList==null)
				return flaggingVO;
			for(int i=0;i<rolesList.size();i++)
				{
					String msg=rolesList.get(i).getID();
					if(		msg.equalsIgnoreCase(config.getString("DC_Grp"))||msg.equalsIgnoreCase(config.getString("DM_Grp"))||
							msg.equalsIgnoreCase(config.getString("NTL_Grp"))||msg.equalsIgnoreCase(config.getString("GMOP_Grp"))||
							msg.equalsIgnoreCase(config.getString("Mithra_Grp"))||msg.equalsIgnoreCase(config.getString("PTD_Grp"))||
							msg.equalsIgnoreCase(config.getString("CTD_Grp"))||msg.equalsIgnoreCase(config.getString("CH_Grp"))||
							msg.equalsIgnoreCase(config.getString("CMA_Grp"))||msg.equalsIgnoreCase(config.getString("DyEO_Grp"))||	   
							msg.equalsIgnoreCase(config.getString("GEX_Grp"))||
							msg.equalsIgnoreCase(config.getString("GM_Grp"))||msg.equalsIgnoreCase(config.getString("PAO_Grp"))||
							msg.equalsIgnoreCase(config.getString("DEO_MA_Grp"))||msg.equalsIgnoreCase(config.getString("JEO_MA_Grp"))||
							msg.equalsIgnoreCase(config.getString("PPD_Grp"))||msg.equalsIgnoreCase(config.getString("CPD_Grp")))
						{	
							flaggingVO.setLoggedUsrGrp(msg);
							flaggingVO.setAuthority("Y");
							break;
						}
					else if(msg.equalsIgnoreCase(config.getString("Medco_Grp"))||msg.equalsIgnoreCase(config.getString("JEO_Empanel_Grp")))
						{
							flaggingVO.setLoggedUsrGrp(msg);
							flaggingVO.setAuthority("N");
						}
					else
							flaggingVO.setAuthority("N");
					
				}
						
			return flaggingVO;
		}
	/*
	 * Implementation
	 * to get 
	 * Non Deflagged Cases*/
	@Override
	public Number getNoOfFlaggedCases(String lStrEmpId)
		{
			List<FlaggingVO> flaggingVOLst;
			StringBuffer sb=new StringBuffer();
			sb.append(" select count(*) as FLAGGEDCASES from Ehf_Flag_Dtls efd,Ehf_Case ec where ");
			sb.append(" efd.current_Status_Of_Flag NOT IN ('"+config.getString("verified_Status")+"','"+config.getString("deFlagged_Status")+"') ");
            sb.append(" and efd.case_Id=ec.case_Id and ec.case_Hosp_Code= ");
            sb.append(" (select emd1.hosp_Id from ehfm_Medco_Dtls emd1 where emd1.medco_Id='"+lStrEmpId+"' and active_yn='Y')");
            
			flaggingVOLst=genericDao.executeSQLQueryList(FlaggingVO.class,sb.toString());
			if(flaggingVOLst.size()>0)
				return flaggingVOLst.get(0).getFLAGGEDCASES();
			else
				return 0;
		}
	
	public FlaggingVO getFlaggedCasesForColor(String caseId)
		{
			List<FlaggingVO> flaggingVOLst;
			FlaggingVO flaggingVO=new FlaggingVO();
			StringBuffer sb=new StringBuffer();
			sb.append(" select count(*) as FLAGGEDCASES from EhfFlagDtls efd where efd.caseId='"+caseId+"' ");
			sb.append(" and efd.currentStatusOfFlag not in ('"+config.getString("verified_Status")+"','"+config.getString("deFlagged_Status")+"') ");
			flaggingVOLst=genericDao.executeHQLQueryList(FlaggingVO.class,sb.toString());
			Number flaggedCases=flaggingVOLst.get(0).getFLAGGEDCASES();
			flaggingVO.setFlagColour(config.getString("red_Colour"));
			
			if(flaggedCases.intValue()==0)
				{
					StringBuffer sb1=new StringBuffer();
					sb1.append(" select count(*) as FLAGGEDCASES from EhfFlagDtls efd where efd.caseId='"+caseId+"' ");
					sb1.append(" and efd.currentStatusOfFlag in ('"+config.getString("verified_Status")+"','"+config.getString("deFlagged_Status")+"') ");
					
					flaggingVOLst=genericDao.executeHQLQueryList(FlaggingVO.class,sb1.toString());
					flaggedCases=flaggingVOLst.get(0).getFLAGGEDCASES();
					flaggingVO.setFlagColour(config.getString("green_Colour"));
				}	
			
			flaggingVO.setFLAGGEDCASES(flaggedCases);
			
			if(flaggedCases.intValue()==0)
				flaggingVO=null;
			
			//AutoCancelCasesScheduler autoCancelCasesScheduler=new AutoCancelCasesScheduler();
			
			//autoCancelCasesScheduler.validatePendingPreauth("CD217",5);
			

				return flaggingVO;
		}
	@Override
	public String getFlagDocId()
	{
		String query1="select ehfm_flag_docId.nextval||'' ID1 from dual";
   		
		List<FlaggingVO> flaggingVO2=new ArrayList<FlaggingVO>();
		flaggingVO2=genericDao.executeSQLQueryList(FlaggingVO.class,query1);
		return flaggingVO2.get(0).getID1();
	}
	@Override
	public void changeMoneyCollectionFlow()
	{
		System.out.println("Scheduler to Redirect Money Collection Casesd started in TS");
		List<FlaggingVO> flaggingVO=new ArrayList<FlaggingVO>();
		EhfFlagDtls ehfFlagDtls2=null;
		try{
			/*sb.append(" loc_Id as LOCID,lang_Id as LANGID,crt_Usr as CRTUSR,crt_Dt as CRTDT,lst_Upd_Usr as LSTUPDUSR,lst_Upd_Dt as LSTUPDDT ," );
			sb.append(" gm_Op_Flag as GMOPFLAG,amount_Ref as AMOUNTREF*/
			StringBuffer sb= new StringBuffer();
			sb.append(" select flag_Id as FLAGID,case_Id as CASEID from Ehf_Flag_Dtls  where (sysdate - crt_Dt) > 15 ");
			sb.append(" and nature_Of_Flag='"+config.getString("money_Collection")+"' and gm_Op_Flag='N' ");
			sb.append(" and current_Status_Of_Flag in ('"+config.getString("flagged_Status")+"','"+config.getString("updated_Status")+"')");
			flaggingVO=genericDao.executeSQLQueryList(FlaggingVO.class, sb.toString());

		for(FlaggingVO flaggingVO1:flaggingVO)
		{
			ehfFlagDtls2= genericDao.findById(EhfFlagDtls.class, String.class, flaggingVO1.getFLAGID());
			ehfFlagDtls2.setGmOpFlag("Y");
			genericDao.save(ehfFlagDtls2);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Scheduler to Redirect Money Collection Casesd ended in TS");
	}
	
	
	/*
	 * Function
	 * to check 
	 * Case is Flagged*/
	@Override
	public FlaggingVO checkCaseFlagged(String caseId)
	{
		List<FlaggingVO> flaggingVOLst=new ArrayList<FlaggingVO>();
		FlaggingVO flaggingVO=new FlaggingVO();
		try
		{
			StringBuffer sb= new StringBuffer();
			sb.append(" select ec.caseId as caseId,ec.flagged as flagged from EhfCase ec where caseId='"+caseId+"'");
			flaggingVOLst=genericDao.executeHQLQueryList(FlaggingVO.class, sb.toString());
			if(flaggingVOLst.size()>0)
				flaggingVO=flaggingVOLst.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flaggingVO;
	}
	/*
	 * Function to execute 
	 * Normal HQL Query */
	public Map<String,Object> executeNormalHQLQuery(String query , String classPath)
		{
			Map<String,Object> returnMap = new HashMap<String,Object>();
			if(classPath != null && query != null)
				{
					try
						{
							List<Class<?>> finalLst=genericDao.executeHQLQueryList
										(Class.forName(classPath).newInstance().getClass(),query);
							if(finalLst != null)
								returnMap.put(classPath, finalLst);
						}
					catch(Exception e)
						{
							e.printStackTrace();
						}
				}
			
			return returnMap;
		}
	@Override
	public List<FlaggingVO> getDistrictsNew(String stateId, String patStateType) 
	{
		List<FlaggingVO> hospDistList=new ArrayList<FlaggingVO>();
		StringBuffer sb=new StringBuffer();
		sb.append("select l.id.locId as ID,l.locName as dist from EhfmLocations l where");
		if(!"both".equalsIgnoreCase(stateId))
		{
			if(patStateType!=null && "O".equalsIgnoreCase(patStateType))
				sb.append(" l.id.locParntId='"+stateId+"' and l.activeYn='N' ");
			else
				sb.append(" l.id.locParntId='"+stateId+"' and l.activeYn='Y' ");
		}
		else
				sb.append(" l.id.locParntId in ('"+config.getString("andhra_hdr_id")+"','"+config.getString("telangana_hdr_id")+"')");
		
		hospDistList=genericDao.executeHQLQueryList(FlaggingVO.class,sb.toString());
		return hospDistList;
	}
}
