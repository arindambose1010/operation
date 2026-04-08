package com.ahct.common.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.commons.configuration.CompositeConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.SmsPullVO;
import com.ahct.login.vo.EmployeeDetailsVO;
import com.ahct.common.util.SMSServices;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

public class SmsPullServiceImpl implements SmsPullService {
	/** The configuration service. */
	private static ConfigurationService configurationService;

	/** The config. */
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	

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
	public String getStatus(SmsPullVO smsPullVO) {

		String templateId="";
		String mobile = smsPullVO.getMobilenumber();
		String status = null;
		try {
			if (mobile != null) {
				//SendSMS sendSms = new SendSMS();
				SMSServices SMSServicesobj = new SMSServices();
				String[] msg = smsPullVO.getMessage().split(" ");
				
				smsPullVO.setKeyWord(msg[0]);
				smsPullVO.setSubKeyWord(msg[1]);
				
				if (smsPullVO.getKeyWord() != null
						&& !smsPullVO.getKeyWord().equalsIgnoreCase("")
						&& smsPullVO.getKeyWord().equalsIgnoreCase("AP")) {
					if (smsPullVO.getSubKeyWord() != null
							&& !smsPullVO.getSubKeyWord().equalsIgnoreCase("")
							&& smsPullVO.getSubKeyWord()
									.equalsIgnoreCase("EHSAPP")) {
						if(msg.length>2){
						smsPullVO.setAckno(msg[2]);	
						String lStrStatus=validateAndGetStatus(smsPullVO.getAckno());
						templateId="1407161769831606962";
						SMSServicesobj.sendSingleSMS("Your Card Status is : "+lStrStatus+"\n\nAHCT, Govt. of Telangana",
								mobile,templateId);
						}
						else{
							templateId="1407161769848299037";
							String msg1="Invalid Employee/Pensioner ID. ";
							msg1=msg1+"The following services are available for Employees Health Scheme. ";
							msg1=msg1+"1. EHSAPP (To know the Health Card Status) ";
							msg1=msg1+"To avail  these Services Type \"AP <Service> <PenID/EmpID> \" and Send SMS to 51969. Eg:- AP EHSAPP 1350125\n\nAHCT, Govt. of Telangana";
							SMSServicesobj.sendSingleSMS(msg1,mobile,templateId);
						}
						
					}
					else if(smsPullVO.getSubKeyWord() != null
							&& !smsPullVO.getSubKeyWord().equalsIgnoreCase("")
							&& smsPullVO.getSubKeyWord()
									.equalsIgnoreCase("EHSHELP")){
						templateId="1407161769851431581";
						String msg1="Thanks for choosing Employee Health Scheme Services. ";
						 	msg1	=msg1+"The following services are available for Employees Health Scheme. ";
							msg1=msg1+"1. EHSAPP (To know the Health Card Status) ";
							msg1=msg1+"To avail these Services Type \"AP <Service> <PenID/EmpID> \" and Send SMS to 51969. Eg:- AP EHSAPP 1350125\n\nAHCT, Govt. of Telangana";
								
						
							SMSServicesobj.sendSingleSMS(msg1,mobile,templateId);
						
					}
					else{
						templateId="1407161769855630452";
						String msg1="Invalid service details. ";
						 	msg1=msg1+"The following services are available for Employees Health Scheme. ";
							msg1=msg1+"1. EHSAPP (To know the Health Card Status) ";
							msg1=msg1+"To avail  these Services Type \"AP <Service> <PenID/EmpID> \" and Send SMS to 51969. Eg:- AP EHSAPP 1350125\n\nAHCT, Govt. of Telangana";
							SMSServicesobj.sendSingleSMS(msg1,mobile,templateId);
						
					}
				}
				
			}
		} 
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	private String validateAndGetStatus(String empId){
		StringBuffer loginHqlQuery = new StringBuffer();
		Session session = null;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		session = factory.openSession();
		List<EmployeeDetailsVO>  loginDtlsList=new ArrayList<EmployeeDetailsVO>();
		EmployeeDetailsVO labelBean = new EmployeeDetailsVO();
		loginHqlQuery.append(" select ee.loginName as ID from AsrimUsers ee where upper(ee.loginName) = ? " );
	 	String[] args2 = new String[1];
	 	args2[0] = empId.toUpperCase();
	 	try{
	 		loginDtlsList= genericDao.executeHQLQueryList(EmployeeDetailsVO.class, loginHqlQuery.toString(),args2);
		
		if(loginDtlsList.size()>0){
			
		StringBuffer hqlQuery = new StringBuffer();
		
		session = factory.openSession();
		List<EmployeeDetailsVO>  empDtlsList=new ArrayList<EmployeeDetailsVO>();
		List<EmployeeDetailsVO>  cardDet=new ArrayList<EmployeeDetailsVO>();
		
		hqlQuery.append(" select ee.empCode as ID from EhfEnrollment ee where upper(ee.empCode) = ? " );
	 	String[] args1 = new String[1];
	 	args1[0] = empId.toUpperCase();
	 	
		
	 	 empDtlsList= genericDao.executeHQLQueryList(EmployeeDetailsVO.class, hqlQuery.toString(),args1);
		
		if(empDtlsList.size()>0){
			
			StringBuffer query = new StringBuffer();
			query.append("select ac.cmbDtlName from EhfEnrollment ee,EhfEnrollmentFamily eef,AsrimCombo ac where  ");
			query.append(" ee.enrollPrntId=eef.enrollPrntId and eef.enrollStatus=ac.id.cmbDtlId and eef.enrollSno=0 ");
			query.append("   and upper(ee.empCode)=?");
			String[] args = new String[1];
		 	args[0] = empId.toUpperCase();
			Query query1 = session.createQuery(query.toString());
			query1.setParameter ( 0, empId.toUpperCase() ) ;
			Iterator ite1 = query1.list().iterator();
			
				while (ite1.hasNext()) {
					Object obj = (Object) ite1.next();
					if(obj.toString()==null)
						labelBean.setID("Enrollment Application Not Initiated");
						else
							labelBean.setID(obj.toString());
			
					}
				}
		
		else
			labelBean.setID("Enrollment Application Not Initiated");

			
		}
		else
			labelBean.setID("Invalid Employee/Pensioner ID");	
			
		}
	 	catch(Exception e){
			e.printStackTrace();
		}
		 finally {
				session.close();
			factory.close();
			}
		
		return labelBean.getID();
	}
}
