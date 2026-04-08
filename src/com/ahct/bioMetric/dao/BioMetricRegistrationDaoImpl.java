package com.ahct.bioMetric.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.bioMetric.vo.BioMetricVo;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfEmpBiomOthLoginDtl;
import com.ahct.model.EhfEmpBiomOthLoginDtlPK;
import com.ahct.model.EhfEmpBiometricData;
import com.ahct.model.EhfEmpBioselfloginDtl;
import com.ahct.model.EhfEmpBioselfloginDtlPK;
import com.ahct.model.EhfmUserDtls;
import com.ahct.model.EhfmUsers;
/*import com.sun.identity.plugin.log.Logger;*/
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

public class BioMetricRegistrationDaoImpl implements BioMetricRegistrationDao  {

	
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
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

	public static ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public static void setConfigurationService(
			ConfigurationService configurationService) {
		BioMetricRegistrationDaoImpl.configurationService = configurationService;
	}

	public static CompositeConfiguration getConfig() {
		return config;
	}

	public static void setConfig(CompositeConfiguration config) {
		BioMetricRegistrationDaoImpl.config = config;
	}

	public String uploadEmpPhoto(BioMetricVo bioMetricVo )
	{
		String msg=null;
		String userId=null;
		if(bioMetricVo!=null){
		userId=bioMetricVo.getUserId();
		
		EhfmUserDtls ehfmUserDtls=new EhfmUserDtls();
		if(userId!=null)
			ehfmUserDtls=genericDao.findById(EhfmUserDtls.class, String.class, userId);
		if(ehfmUserDtls!=null)
		{
			ehfmUserDtls.setPhoto(bioMetricVo.getPHOTO());
			ehfmUserDtls.setLstUpdUsr(userId);
		
			try{
				genericDao.save(ehfmUserDtls);
				msg="success";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				msg="fail";
				
			}
			
		}
	}
		return msg;
}
	@Transactional
	public boolean biometricEnroll(BioMetricVo bioMetricVo)
	{
		boolean isEnroll=false;
		String userId=null;
		Date date=new Date();
		userId=bioMetricVo.getUserId();
		EhfmUsers ehfmUsers=new EhfmUsers();
		EhfmUserDtls ehfmUserDtls=new EhfmUserDtls();
		EhfEmpBiometricData  ehfEmpBiometricData=new EhfEmpBiometricData();
		
		if(userId!=null){
			
			try{
				ehfmUsers=genericDao.findById(EhfmUsers.class, String.class, userId);
				ehfmUserDtls=genericDao.findById(EhfmUserDtls.class, String.class, userId);
				ehfEmpBiometricData=genericDao.findById(EhfEmpBiometricData.class, String.class, userId);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		if(ehfmUsers!=null && ehfmUserDtls!=null  )
		{
			ehfmUsers.setMobileNo(bioMetricVo.getMOBILE());
			ehfmUsers.setLstUpdDt(date);
			String password=getDecryptedPswd(ehfmUsers.getLoginName());
			ehfmUsers.setPasswd(password);
			ehfmUsers.setLstUpdUser(bioMetricVo.getUserId());
			
			ehfmUserDtls.setPhoneNo(bioMetricVo.getMOBILE());
			ehfmUserDtls.setAddr1(bioMetricVo.getADDRESS());
			ehfmUserDtls.setAddr2(bioMetricVo.getPERMADDRESS());
			ehfmUserDtls.setLstUpdUsr(bioMetricVo.getUserId());
		}
			
		    if(ehfEmpBiometricData==null)
		    {
		    	ehfEmpBiometricData=new EhfEmpBiometricData();
		    	ehfEmpBiometricData.setCrtDt(new Timestamp(new Date().getTime()));
				ehfEmpBiometricData.setCrtUsr(bioMetricVo.getUserId());
		    }
		    if(ehfEmpBiometricData!=null)
		    {
		    	ehfEmpBiometricData.setLstUpdDt(new Timestamp(new Date().getTime()));
		    	ehfEmpBiometricData.setLstUpdUsr(bioMetricVo.getUserId());
		    }
		    ehfEmpBiometricData.setUserId(bioMetricVo.getUserId());
			ehfEmpBiometricData.setEncryptedFingerprint(bioMetricVo.getFINGERPRINT());
			ehfEmpBiometricData.setHospitalCode(bioMetricVo.getHospId());
			ehfEmpBiometricData.setLoginName(bioMetricVo.getLOGINNAME());
			ehfEmpBiometricData.setMacAddress(bioMetricVo.getHospMacId());
			
			
			try{
				genericDao.save(ehfmUsers);
				genericDao.save(ehfmUserDtls);
				genericDao.save(ehfEmpBiometricData);
				isEnroll=true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			

		
		
		return isEnroll;
	}
	
    public String getDecryptedPswd(String pStrLoginName){

		StringBuffer query = new StringBuffer();
		String[] args=new String[1];
		args[0]=pStrLoginName;
		query.append("  select DECRYPT_USER_PSWD(?) as ID from dual  ");
		List<LabelBean> lStPswd=genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
		if(lStPswd!=null)	
		return lStPswd.get(0).getID();
		return null;
	}
	public List<LabelBean> getOthersList()
	{
		List<LabelBean> othersList=new ArrayList<LabelBean>();
		StringBuffer query=new StringBuffer();
		
		query.append("select grp_name as ID,grp_id as VALUE from ehfm_grps where grp_id in ('GP41','GP42','GP43','GP49')");
		othersList=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		return othersList;
	}
	public String biometricAttendance(BioMetricVo bioMetricVo){
		
		String isRecord="";
		String userType=bioMetricVo.getATTENDANCEUSER();
		String attendanceType=bioMetricVo.getATTENDTYPE();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		String dateTemp=sdf1.format(new Date());
		/*Calendar calendar = Calendar.getInstance();
		Date date=calendar.getTime();*/
		
		Date date=new Date();
		SessionFactory factory=null;
		Session session=null;
		
		
		
		if(userType!=null && ("s").equalsIgnoreCase(userType)){
			
			try{
				if(("0").equals(attendanceType))
				{
					EhfEmpBioselfloginDtl ehfEmpBioselfloginDtl=new EhfEmpBioselfloginDtl();
					EhfEmpBioselfloginDtlPK ehfEmpBioselfloginDtlPK=new EhfEmpBioselfloginDtlPK();
					ehfEmpBioselfloginDtlPK.setUserId(bioMetricVo.getUSERID());
					ehfEmpBioselfloginDtlPK.setLoginDate(date);
					
					ehfEmpBioselfloginDtl.setId(ehfEmpBioselfloginDtlPK);
					ehfEmpBioselfloginDtl.setHospitalCode(bioMetricVo.getHospId());
					ehfEmpBioselfloginDtl.setMacAddress(bioMetricVo.getHospMacId());		
					ehfEmpBioselfloginDtl.setLoginTime(new Timestamp(new Date().getTime()));
					ehfEmpBioselfloginDtl.setCrtDt(new Timestamp(new Date().getTime()));
					ehfEmpBioselfloginDtl.setCrtUsr(bioMetricVo.getUSERID());
					ehfEmpBioselfloginDtl.setAttendancetype(bioMetricVo.getATTENDTYPE());
					ehfEmpBioselfloginDtl=genericDao.save(ehfEmpBioselfloginDtl);
					if(ehfEmpBioselfloginDtl!=null)
					{
						isRecord="LogIn Details Saved Successfully";
					}
				}
				else if(("1").equals(attendanceType))
				{
					
					
					Query hQuery=null;
					StringBuffer query=new StringBuffer();
					String[] args=new String[2];
					args[0]=bioMetricVo.getUSERID();
					args[1]=dateTemp;
					factory= hibernateTemplate.getSessionFactory();
					session=factory.openSession();
					EhfEmpBioselfloginDtl ehfEmpBioselfloginDtl=new EhfEmpBioselfloginDtl();
					
					 List<EhfEmpBioselfloginDtl> listEmp=new ArrayList<EhfEmpBioselfloginDtl> ();
					query.append("  from EhfEmpBioselfloginDtl e where e.id.userId='"+bioMetricVo.getUSERID()+"' and e.id.loginDate=to_date('"+dateTemp+"','mm/DD/YYYY')");
					
					
					hQuery=session.createQuery(query.toString());
					if(hQuery.list().size()>0)
						{
						ehfEmpBioselfloginDtl=(EhfEmpBioselfloginDtl) hQuery.list().get(0);
						}
					
					
					
						if(ehfEmpBioselfloginDtl!=null && !("").equals(ehfEmpBioselfloginDtl))
						{
						ehfEmpBioselfloginDtl.setLogoutDate(date);
						ehfEmpBioselfloginDtl.setLogoutTime(new java.sql.Timestamp(new Date().getTime()));
						ehfEmpBioselfloginDtl.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
						ehfEmpBioselfloginDtl.setLstUpdUsr(bioMetricVo.getUSERID());
						ehfEmpBioselfloginDtl=genericDao.save(ehfEmpBioselfloginDtl);
						if(ehfEmpBioselfloginDtl!=null)
						{
							isRecord="LogOut Details Saved Successfully";
						}
						}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			finally {
				if(session!=null)
				{
				session.close();
				}
				if(factory!=null)
				{
				factory.close();
				}
			}
			
		}
		
		
		
       if(userType!=null && ("o").equalsIgnoreCase(userType)){
			
			try{
			if(("0").equals(attendanceType))
			{
				EhfEmpBiomOthLoginDtl  ehfEmpBiomOthLoginDtl=new EhfEmpBiomOthLoginDtl();
				EhfEmpBiomOthLoginDtlPK  ehfEmpBiomOthLoginDtlPK=new EhfEmpBiomOthLoginDtlPK();
				ehfEmpBiomOthLoginDtlPK.setUserId(bioMetricVo.getUSERID());
				ehfEmpBiomOthLoginDtlPK.setLoginDate(date);
				
				ehfEmpBiomOthLoginDtl.setId(ehfEmpBiomOthLoginDtlPK);
				ehfEmpBiomOthLoginDtl.setHospitalCode(bioMetricVo.getHospId());
				ehfEmpBiomOthLoginDtl.setMacAddress(bioMetricVo.getHospMacId());	
				ehfEmpBiomOthLoginDtl.setLoginTime(new java.sql.Timestamp(new Date().getTime()));
				ehfEmpBiomOthLoginDtl.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				ehfEmpBiomOthLoginDtl.setCrtUsr(bioMetricVo.getUSERID());
				ehfEmpBiomOthLoginDtl=genericDao.save(ehfEmpBiomOthLoginDtl);
				if(ehfEmpBiomOthLoginDtl!=null)
				{
					isRecord="LogIn Details Saved Successfully";
				}
			}
			else if(("1").equals(attendanceType))
			{

				
				Query hQuery=null;
				StringBuffer query=new StringBuffer();
				String[] args=new String[2];
				args[0]=bioMetricVo.getUSERID();
				args[1]=dateTemp;
				factory= hibernateTemplate.getSessionFactory();
				EhfEmpBiomOthLoginDtl ehfEmpBiomOthLoginDtl=new EhfEmpBiomOthLoginDtl();
				session=factory.openSession();
				 List<EhfEmpBioselfloginDtl> listEmp=new ArrayList<EhfEmpBioselfloginDtl> ();
				query.append("  from EhfEmpBiomOthLoginDtl e where e.id.userId='"+bioMetricVo.getUSERID()+"' and e.id.loginDate=to_date('"+dateTemp+"','mm/DD/YYYY')");
				
				
				hQuery=session.createQuery(query.toString());
				if(hQuery.list().size()>0)
					{
					ehfEmpBiomOthLoginDtl=(EhfEmpBiomOthLoginDtl) hQuery.list().get(0);
					}
				
				
				
				if(ehfEmpBiomOthLoginDtl!=null && !("").equals(ehfEmpBiomOthLoginDtl))
				{
					ehfEmpBiomOthLoginDtl.setLogoutDate(date);
					ehfEmpBiomOthLoginDtl.setLogoutTime(new java.sql.Timestamp(new Date().getTime()));
					ehfEmpBiomOthLoginDtl.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
					ehfEmpBiomOthLoginDtl.setLstUpdUsr(bioMetricVo.getUSERID());
					ehfEmpBiomOthLoginDtl=genericDao.save(ehfEmpBiomOthLoginDtl);
				if(ehfEmpBiomOthLoginDtl!=null)
				{
					isRecord="LogOut Details Saved Successfully";
				}
			}}}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			finally {
				if(session!=null)
				session.close();
				if(factory!=null)
				factory.close();
			}
			
		}
		
		return isRecord;
		
	}
	
	
	public String validateAttendance(BioMetricVo bioMetricVo)
	{
		String status=null;
		String userId=bioMetricVo.getUSERID();
		String userType=bioMetricVo.getATTENDANCEUSER();
		String attendanceType=bioMetricVo.getATTENDTYPE();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		String dateTemp=sdf1.format(new Date());
		Date date=null;
		try {
			date = sdf1.parse(dateTemp);
		
       if(userType!=null && ("s").equalsIgnoreCase(userType)){
			
			
				List<BioMetricVo> BioMetricVo=new ArrayList<BioMetricVo>();
				EhfEmpBioselfloginDtl ehfEmpBioselfloginDtl=new EhfEmpBioselfloginDtl();
				EhfEmpBioselfloginDtlPK ehfEmpBioselfloginDtlPK=new EhfEmpBioselfloginDtlPK(bioMetricVo.getUSERID(),date);
				/*ehfEmpBioselfloginDtlPK.setUserId(bioMetricVo.getUSERID());
				ehfEmpBioselfloginDtlPK.setLoginDate(date);*/
				StringBuffer query=new StringBuffer();
				String[] args=new String[3];
				args[0]=bioMetricVo.getUSERID();
				args[1]=dateTemp;
				args[2]=bioMetricVo.getHospId();
				query.append("select e.user_id as USERID,e.LOGOUT_DATE as LOGOUTTIME  from EHF_EMP_BIOSELFLOGIN_DTLS e where e.user_id=? and login_date=to_date(?,'mm/DD/YYYY') and e.hospital_code=? ");
				BioMetricVo=genericDao.executeSQLQueryList(BioMetricVo.class, query.toString(),args);
				//ehfEmpBioselfloginDtl=genericDao.findById(EhfEmpBioselfloginDtl.class, EhfEmpBioselfloginDtlPK.class, ehfEmpBioselfloginDtlPK);
				
				
				if(("0").equals(attendanceType))
				{
					
					if(BioMetricVo!=null && BioMetricVo.size()>0)
						status="0";  /*user already  entered login details for the same day*/
				
				}
				else
				{
					
				
					if(BioMetricVo==null || BioMetricVo.size()==0)
						status="1";  /*user have to login prior to logout*/
					else if(BioMetricVo!=null && BioMetricVo.size()>0)
						if(BioMetricVo.get(0).getLOGOUTTIME()!=null)
						status="2";  /*user already entered logout details for the day*/
						
				}
				
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
       
       
       if(userType!=null && ("o").equalsIgnoreCase(userType)){
			
			try{
				
				EhfEmpBiomOthLoginDtl  ehfEmpBiomOthLoginDtl=new EhfEmpBiomOthLoginDtl();
				EhfEmpBiomOthLoginDtlPK  ehfEmpBiomOthLoginDtlPK=new EhfEmpBiomOthLoginDtlPK(bioMetricVo.getUSERID(),date);
				List<BioMetricVo> BioMetricVo=new ArrayList<BioMetricVo>();
				StringBuffer query=new StringBuffer();
				String[] args=new String[3];
				args[0]=bioMetricVo.getUSERID();
				args[1]=dateTemp;
				args[2]=bioMetricVo.getHospId();
				query.append("select e.user_id as USERID,e.LOGOUT_DATE as LOGOUTTIME  from ehf_emp_biom_oth_login_dtls e where e.user_id=? and login_date=to_date(?,'mm/DD/YYYY') and e.hospital_code=? ");
				BioMetricVo=genericDao.executeSQLQueryList(BioMetricVo.class, query.toString(),args);
				
				
				if(("0").equals(attendanceType))
				{
					
					if(BioMetricVo!=null && BioMetricVo.size()>0)
						status="0";  /*user already  entered login details for the same day*/
				
				}
				else
				{
					
				
					if(BioMetricVo==null || BioMetricVo.size()==0)
						status="1";  /*user have to login prior to logout*/
					else if(BioMetricVo!=null && BioMetricVo.size()>0)
						if(BioMetricVo.get(0).getLOGOUTTIME()!=null)
						status="2";  /*user already entered logout details for the day*/
						
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
      }
       
       
       return status;
}
	
	public String getUserIdByRole(BioMetricVo biometricVO)
	{
		String userId=null;
		
		try{
		List<BioMetricVo> userList=new ArrayList<BioMetricVo>();
		StringBuffer query=new StringBuffer();
		String userType=biometricVO.getOtherUser();
		String[] args=new String[1];
		args[0]=biometricVO.getHospId();
		query.append("select c.userId as userId from EhfmHospTlDcMpg a,EhfmUsrGrpsMpg b,EhfmUsers c ");
		query.append(" where a.hospId=?  and b.id.usergrpId=c.userId");
		
		if(userType!=null && config.getString("DC_Grp").equalsIgnoreCase(userType))
			query.append(" and b.id.usergrpId=a.dcId");
		
		else if(userType!=null && config.getString("DM_Grp").equalsIgnoreCase(userType))
			query.append(" and b.id.usergrpId=a.dmId");
		
		else if(userType!=null && config.getString("NTL_Grp").equalsIgnoreCase(userType))
			query.append(" and b.id.usergrpId=a.tlId");
		
		userList=genericDao.executeHQLQueryList(BioMetricVo.class, query.toString(),args);
		
		if(userList!=null && userList.size()>0)
		{
			userId=userList.get(0).getUserId();
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return userId;
	}
	
	@SuppressWarnings("unchecked")
	public BioMetricVo getBiometricReport(BioMetricVo bioMetricVo)
	{
		StringBuffer query=new StringBuffer();
		StringBuffer query1=new StringBuffer();
		StringBuffer countQuery=new StringBuffer();
		List<BioMetricVo> biometricRepotLst=new ArrayList<BioMetricVo>();
		BioMetricVo biometricReport =new BioMetricVo();
		//int startIndex=0;
		//int maxResult=15;
		int startIndex=bioMetricVo.getStartIndex();
		int maxResult=bioMetricVo.getMaxResults();
		String deptId=bioMetricVo.getDeptId();
		String dsgnId=bioMetricVo.getDsgnId();
		String fromDt=bioMetricVo.getFromDate();
		String toDate=bioMetricVo.getToDate();
		String csvFlag=bioMetricVo.getCsvFlag();
		//String fromDt="01/01/2015";
		//String toDate="07/07/2015";
		String distId=bioMetricVo.getDistId();
		
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		
		try
		{
		
		
		query.append( "select d.id.userId as userId,a.loginName as LOGINNAME,a.firstName||''||a.middleName||''||a.lastName as empName, " );
		query.append( " b.dsgnName as DESIGNATION,c.hospName as hospitalName,d.macAddress as hospMacId,count(d.id.userId) as loginDays," );
		query.append( " trunc(floor(to_date('"+toDate+"','mm/dd/yyyy')-to_date('"+fromDt+"','mm/dd/yyyy')-count(d.id.userId))) as notLoginDays" );
		query1.append( " from EhfmUsers a,EhfmDesignation b,EhfmHospitals c,EhfEmpBioselfloginDtl d,EhfmWorkAllocation e" );
		query1.append( " where a.userId=d.id.userId and c.hospId=d.hospitalCode" );
		query1.append( " and a.dsgnId=b.id.dsgnId and d.id.loginDate>=to_date('"+fromDt+"','mm/dd/yyyy') " );
		query1.append( " and d.id.loginDate<=to_date('"+toDate+"','mm/dd/yyyy')" );
		query1.append( " and e.userCode=a.loginName ");
		
		if(deptId!=null && !("").equalsIgnoreCase(deptId))
		{
			query1.append( " and e.id.userDeptId='"+deptId+"' ");
		}
		if(dsgnId!=null && !("").equalsIgnoreCase(dsgnId))
		{
			query1.append( " and e.userDsgnId='"+dsgnId+"' ");
		}
		if(distId!=null && !("").equalsIgnoreCase(distId))
		{
			query1.append( " and c.hospDist='"+distId+"' ");
		}
		
		countQuery.append(" select distinct d.id.userId as userId,c.hospId as hospId ");
		countQuery.append(query1);
		List<BioMetricVo> countList=new ArrayList<BioMetricVo>();
		
		countList=genericDao.executeHQLQueryList(BioMetricVo.class,countQuery.toString());
		Long count=null;
		if(countList!=null && countList.size()>0)
		count=(long) countList.size();
		
		query1.append( " group by d.id.userId,a.loginName,a.firstName,a.middleName,a.lastName,b.dsgnName,c.hospName,d.macAddress" );
		
		query.append(query1);
		
		if(csvFlag!=null && ("Y").equalsIgnoreCase(csvFlag))
			biometricRepotLst=genericDao.executeHQLQueryList(BioMetricVo.class,query.toString());
				
		else
		biometricRepotLst=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(BioMetricVo.class)).list();
		
		
		
		biometricReport.setAttendanceReport(biometricRepotLst);
		
		if(count!=null)
			biometricReport.setCount(count);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
		
		return biometricReport;
	}
}
