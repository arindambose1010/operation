package com.ahct.annualCheckUp.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfAnnualPatientDtls;

public class AhcScheduler 

{
	
	GenericDAO genericDao ;
	
	public GenericDAO getGenericDao() {
		return genericDao;
	}
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

	/**
     * ;
     * @function This method is used to fetch the Cases that are annual checked in the current Year.
     */
	@Scheduled(cron="00 30 16 * * * ")
	public void fetchAhcData()
	{
		try
		{
			double diff=0;
			List<EhfEnrollmentFamily> ehfEnrollmentFamilyLst=new ArrayList<EhfEnrollmentFamily>();
			List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			
			criteriaList.add(new GenericDAOQueryCriteria("annualCheckup", "A",
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			ehfEnrollmentFamilyLst=genericDao.findAllByCriteria(EhfEnrollmentFamily.class, criteriaList);
			for(EhfEnrollmentFamily ehfEnrollmentFamily : ehfEnrollmentFamilyLst )
				{
					if(ehfEnrollmentFamily!=null)
						{
					        criteriaList.removeAll(criteriaList);
					        List<EhfAnnualPatientDtls> ehfAnnualPatientDtlsLst=new ArrayList<EhfAnnualPatientDtls>();
					        EhfAnnualPatientDtls ehfAnnualPatientDtls=new EhfAnnualPatientDtls();
					        
					        criteriaList.add(new GenericDAOQueryCriteria("cardNo",ehfEnrollmentFamily.getEhfCardNo() ,
					    			GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					        criteriaList.add(new GenericDAOQueryCriteria("crtDt","" ,
					    			GenericDAOQueryCriteria.CriteriaOperator.DESC));
					        
					        ehfAnnualPatientDtlsLst=genericDao.findAllByCriteria(EhfAnnualPatientDtls.class,criteriaList);
					        	if(ehfAnnualPatientDtlsLst!=null)
					        		ehfAnnualPatientDtls=ehfAnnualPatientDtlsLst.get(0);
					        	
					        	StringBuffer query = new StringBuffer();
								query.append("select sysdate-crt_Dt as DIFF from EHF_ANNUAL_PATIENT_DTLS ");
								query.append(" where AHC_ID='"+ehfAnnualPatientDtls.getAhcId()+"'");
								
								List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
				
						        if(seqList != null)
						        	{
						        		diff = seqList.get(0).getDIFF().doubleValue();
						        		if(diff>365)
						        			{
						        				ehfEnrollmentFamily.setAnnualCheckup("Y");
						        				genericDao.save(ehfEnrollmentFamily);
						        			}
						        	}
						        
						}
				}
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
}
