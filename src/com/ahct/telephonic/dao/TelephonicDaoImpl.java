package com.ahct.telephonic.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.telephonic.vo.TelephonicVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

public class TelephonicDaoImpl implements TelephonicDao {

	private final static Logger GLOGGER = Logger.getLogger ( TelephonicDaoImpl.class ) ;
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	private static ConfigurationService configurationService;
	@SuppressWarnings("unused")
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
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered to be displayed to MITHRA
     */
	@SuppressWarnings("unchecked")
	@Override
	public TelephonicVO getTeleIntimationCases(TelephonicVO telephonicVO) throws Exception{
		List<TelephonicVO> lstCases = new ArrayList<TelephonicVO>();	
		TelephonicVO retTelephonicVO = new TelephonicVO();
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
		int startIndex=Integer.parseInt(telephonicVO.getStartIndex());
		int maxResult = (Integer.parseInt(telephonicVO.getRowsPerPage()))*(Integer.parseInt(telephonicVO.getShowPage()));
		try{
			if(telephonicVO.getShowPage() != null && Integer.parseInt(telephonicVO.getShowPage()) > 1 )
			{
				startIndex =  Integer.parseInt(telephonicVO.getRowsPerPage()) * (Integer.parseInt(telephonicVO.getShowPage())-1);
			}
			query.append("  select distinct case when ((sysdate - trr.crtDt <= 3) AND trr.asriCatCode <> 'S19') OR ((sysdate - trr.crtDt <=7) AND trr.asriCatCode = 'S19') then 'Telephonic Intimation-Initiated' ");
			query.append(" when ((sysdate - trr.crtDt > 3) AND trr.asriCatCode <> 'S19') OR ((sysdate - trr.crtDt >7) AND trr.asriCatCode = 'S19')  then 'Telephonic Intimation Cancelled' end as teleStatus, ");
			query.append(" trr.telephonicId as telephonicId, trr.cardNo as healthCardNo, trr.name as patientName,trr.crtDt as crtDt,");
			query.append(" trr.callerName as callerName,trr.callerMobileNo as callerMobileNo,EL.locName as locName,EH.hospName as hospName ,ETCM.icdCatName as icdCatName,ETPM.procName as procName, ");
			query.append("  trr.doctorName as doctorName,trr.docMobileNo as mobileNo,trr.cardType as cardType ");
			query1.append(" from EhfTelephonicRegn trr,EhfmHospitals EH ,EhfmLocations EL,EhfmHospMithraDtls EMU,EhfmTherapyProcMst ETPM,EhfmTherapyCatMst ETCM  ");
			query1.append(" where trr.hospId=EH.hospId and EL.id.locId=trr.districtCode and EMU.id.hospId=trr.hospId and trr.ICDCatCode=ETCM.id.icdCatCode and trr.ICDProcCode=ETPM.id.icdProcCode" );
			query1.append(" and trr.asriCatCode=ETCM.id.asriCatCode and trr.asriCatCode=ETPM.id.asriCode and ETPM.id.process in ('IP','FU') and ETPM.id.state=trr.schemeId and trr.nabhHosp is null ");
			query1.append(" and trr.patientId is null and EMU.id.mithraId='"+ telephonicVO.getUserId()+"' and trr.hospId=EMU.id.hospId  and EMU.endDt is null ");
			if(telephonicVO.getTelephonicId()!=null && !"".equals(telephonicVO.getTelephonicId()))
			{
				query1.append(" and trr.telephonicId='"+telephonicVO.getTelephonicId()+"'");
			}
			if(telephonicVO.getFromDate()!=null && !telephonicVO.getFromDate().equals("") && telephonicVO.getToDate()!=null && !telephonicVO.getToDate().equals(""))
			  { 
					fromDate=telephonicVO.getFromDate();
					sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
					toDate=telephonicVO.getToDate().toString();
					
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
			lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(TelephonicVO.class)).list();

			query = new StringBuffer();
			query.append( " select distinct count(*) ");
			query.append(query1);

			Long count = (Long) session.createQuery(query.toString()).uniqueResult();
			System.out.println(count);
			retTelephonicVO.setTotRowCount(Long.toString(count));
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getTeleIntimationCases() in TelephonicDaoImpl class."+e.getMessage()); 
		}
		finally
		{
			session.close();
			factory.close();
		}
		retTelephonicVO.setLstCaseSearch(lstCases);
		retTelephonicVO.setStartIndex(Integer.toString(startIndex));
		retTelephonicVO.setShowPage(telephonicVO.getShowPage());
		return retTelephonicVO;
	}
	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered
     */
	@SuppressWarnings("unchecked")
	@Override
	public TelephonicVO getRegTeleIntimationCases(TelephonicVO telephonicVO)throws Exception {
		List<TelephonicVO> lstCases = new ArrayList<TelephonicVO>();
		List<TelephonicVO> lstCasesSize = new ArrayList<TelephonicVO>();	
		TelephonicVO retTelephonicVO = new TelephonicVO();
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

		try{
			query.append("  select distinct case when (sysdate-trr.crtDt) <= 3 then 'Telephonic Intimation-Initiated' ");
			query.append(" when (sysdate-trr.crtDt) > 3 then 'TelePhonic Intimation Cancelled' end as teleStatus, ");
			query.append(" trr.telephonicId as telephonicId, trr.cardNo as healthCardNo, trr.name as patientName,trr.crtDt as crtDt,");
			query.append(" trr.callerName as callerName,trr.callerMobileNo as callerMobileNo,EL.locName as locName,EH.hospName as hospName ,ETCM.icdCatName as icdCatName,ETPM.procName as procName, ");
			query.append("  trr.doctorName as doctorName,trr.docMobileNo as mobileNo,trr.cardType as cardType ");
			query1.append(" from EhfTelephonicRegn trr,EhfmHospitals EH ,EhfmLocations EL,EhfmTherapyProcMst ETPM,EhfmTherapyCatMst ETCM  ");
			query1.append(" where trr.hospId=EH.hospId and trr.nabhHosp is null and EL.id.locId=trr.districtCode  and trr.ICDCatCode=ETCM.id.icdCatCode and trr.ICDProcCode=ETPM.id.icdProcCode" );
			query1.append(" and trr.asriCatCode=ETCM.id.asriCatCode and trr.asriCatCode=ETPM.id.asriCode and trr.schemeId=ETPM.id.state and ETPM.id.process in ('IP','DOP','FU') ");
			
			
			if(telephonicVO.getSchemeId()!=null &&  !("").equalsIgnoreCase(telephonicVO.getSchemeId()))
			{
				query1.append(" and trr.schemeId='"+telephonicVO.getSchemeId()+"' ");
			}
			//query1.append(" and trr.patientId is null ");
			if(telephonicVO.getTelephonicId()!=null && !"".equals(telephonicVO.getTelephonicId()))
			{
				query1.append(" and trr.telephonicId='"+telephonicVO.getTelephonicId()+"'");
			}
			if(telephonicVO.getFromDate()!=null && !telephonicVO.getFromDate().equals("") && telephonicVO.getToDate()!=null && !telephonicVO.getToDate().equals(""))
			  { 
					fromDate=telephonicVO.getFromDate();
					sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
					toDate=telephonicVO.getToDate().toString();
					
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
			//lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(TelephonicVO.class)).list();
			
			 if(telephonicVO.getEnd_index()!=0)
			 {
				 lstCases=session.createQuery(query.toString()).setFirstResult(telephonicVO.getStart_index()).setMaxResults(telephonicVO.getEnd_index()).setResultTransformer(Transformers.aliasToBean(TelephonicVO.class)).list();
			 }
			else if((telephonicVO.getStart_index()==0)&&(telephonicVO.getEnd_index()==0))
			 {
				 lstCases=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(TelephonicVO.class)).list();
			 }

			//query = new StringBuffer();
			//query.append( " select count(*) ");
			//query.append(query1);
			
			lstCasesSize=session.createQuery(query.toString()).list();
			//Long count = (Long) session.createQuery(query.toString()).uniqueResult();
			int count=lstCasesSize.size();
			System.out.println(count);
			retTelephonicVO.setTotRowCount(Integer.toString(count));
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRegTeleIntimationCases() in TelephonicDaoImpl class."+e.getMessage());  
		}
		finally
		{
			session.close();
			factory.close();
		}
		retTelephonicVO.setLstCaseSearch(lstCases);
		//retTelephonicVO.setStartIndex(Integer.toString(startIndex));
		retTelephonicVO.setShowPage(telephonicVO.getShowPage());
		return retTelephonicVO;
	}


}
