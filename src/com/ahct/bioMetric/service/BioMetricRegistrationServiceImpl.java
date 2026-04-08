package com.ahct.bioMetric.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import  com.ahct.bioMetric.dao.BioMetricRegistrationDao;
import com.ahct.bioMetric.vo.BioMetricVo;
import com.ahct.chronicOp.vo.ChronicOPVO;
import com.ahct.common.service.AsriBiomServiceImplServiceStub;
import com.ahct.common.service.AsriBiomServiceImplServiceStub.CompareBiometricValues;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfDeptDsgnMpg;
import com.ahct.model.EhfEmpBiometricData;
import com.ahct.model.EhfPatientBioReg;
import com.ahct.model.EhfmLocations;
import com.ahct.patient.dao.PatientDaoImpl;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class BioMetricRegistrationServiceImpl implements  BioMetricRegistrationService{

	BioMetricRegistrationDao bioMetricDao;
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	public static Logger getGlogger() {
		return gLogger;
	}

	private final static Logger gLogger = Logger.getLogger ( BioMetricRegistrationServiceImpl.class ) ;
	public BioMetricRegistrationDao getBioMetricDao() {
		return bioMetricDao;
	}
	public void setBioMetricDao(BioMetricRegistrationDao bioMetricDao) {
		this.bioMetricDao = bioMetricDao;
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

	public String getMacIdForHosp(String userId)
	{
		String hospMacId=null;
		String hospId=null;
		String hospDetails=null;
		String[] args=new String[1];
		args[0]=userId;
		List<BioMetricVo> bioMetricVo=new ArrayList<BioMetricVo>();
		StringBuffer query=new StringBuffer();
		query.append("select a.hospId as hospId,a.hospMacId as hospMacId from EhfmHospitals a where a.hospId in ");
		query.append("(select b.id.hospId from EhfmHospMithraDtls b where b.id.mithraId=? and b.activeYN='Y')");
		
		bioMetricVo=genericDao.executeHQLQueryList(BioMetricVo.class, query.toString(), args);
		
		if(bioMetricVo!=null && bioMetricVo.size()>0)
		{
			hospMacId=bioMetricVo.get(0).getHospMacId();
			hospId=bioMetricVo.get(0).getHospId();		
			hospDetails=hospId+"~"+hospMacId;
			
		}
		return hospDetails;
		
	}
	@Override
	public List<BioMetricVo> getMacIdForOthers(String userId) 
	{
		String hospMacId=null;
		String hospId=null;
		String hospDetails=null;
		List<BioMetricVo> hospMacList = null;
		String[] args=new String[3];
		args[0]=userId;
		args[1]=userId;
		args[2]=userId;
		List<BioMetricVo> bioMetricVo=new ArrayList<BioMetricVo>();
		StringBuffer query=new StringBuffer();
		query.append("select a.hospId as hospId,a.hospMacId as hospMacId from EhfmHospitals a where a.hospId in ");
		query.append("(select b.hospId from EhfmHospTlDcMpg b where b.tlId=? or b.dcId=? or b.dmId=? )");
		
		hospMacList=genericDao.executeHQLQueryList(BioMetricVo.class, query.toString(), args);
		
	
		return hospMacList;
	}
	public List<BioMetricVo> getUserDetails(String userId)
	{
		List<BioMetricVo> bioMetricVo=new ArrayList<BioMetricVo>();
		StringBuffer query=new StringBuffer();
		String[] args=new String[1];
		args[0]=userId;
		query.append("select distinct a.user_Id as USERID,a.login_name as LOGINNAME,(a.first_Name||' '||a.middle_name||' '||a.last_name) as EMPLOYEENAME,a.mobile_no as MOBILE,");
		query.append(" d.dsgn_name as DESIGNATION,e.dept_name as DEPARTMENT,c.addr1 as ADDRESS,c.addr2 as PERMADDRESS,c.photo as PHOTO");
		query.append(" from ehfm_users a,ehfm_work_allocation b,ehfm_user_dtls c,ehfm_designation d,ehfm_depts e");
		query.append(" where a.login_name=b.user_code and a.user_id=c.user_id and a.dsgn_id=d.dsgn_id");
		query.append(" and e.dept_id=b.user_dept_id and a.user_id=?");
		
		bioMetricVo=genericDao.executeSQLQueryList(BioMetricVo.class, query.toString(),args);
		
		return bioMetricVo;
	}
	public String uploadEmpPhoto(BioMetricVo bioMetricVo )
	{
		String msg=null;
		msg=bioMetricDao.uploadEmpPhoto(bioMetricVo);
		return msg;
	}
	
	public boolean bioEnrollStatus(String userId)
	{
		boolean registered=false;
		EhfEmpBiometricData ehfEmpBiometricData=new EhfEmpBiometricData();
		if(userId!=null && !"".equalsIgnoreCase(userId))
		try{
			ehfEmpBiometricData=genericDao.findById(EhfEmpBiometricData.class, String.class, userId);
		if(ehfEmpBiometricData!=null)
		{
			registered=true;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return registered;
	}
	public boolean biometricEnroll(BioMetricVo bioMetricVo)
	{
		boolean isEnroll=false;
		isEnroll=bioMetricDao.biometricEnroll(bioMetricVo);
		return isEnroll;
		
	}
	public String biometricAttendance(BioMetricVo bioMetricVo){
		
		String isRecord="";
		isRecord=bioMetricDao.biometricAttendance(bioMetricVo);
		return isRecord;
	}
	public String validateAttendance(BioMetricVo bioMetricVo)
	{
		String status=null;
		status=bioMetricDao.validateAttendance(bioMetricVo);
		return status;
	}
	
	public List<LabelBean> getOthersList()
	{
		 List<LabelBean> othersList=new ArrayList<LabelBean>();
		 othersList=bioMetricDao.getOthersList();
		 return othersList;
	}
	
	public String getUserIdByRole(BioMetricVo biometricVO){
		
		String userId=bioMetricDao.getUserIdByRole(biometricVO);
		return userId;
	}
	//Webservice for matching fingerprint in biometric attendance
	public boolean matchFingerPrint(BioMetricVo bioMetricVo)
	{
		/*Boolean isMatched=false;
		String[] args=new String[1];
		args[0]=bioMetricVo.getUSERID();
		List<BioMetricVo> fingerPrintList=new ArrayList<BioMetricVo>();
		try{
		StringBuffer query=new StringBuffer();
		query.append("select ENCRYPTED_FINGERPRINT as FINGERPRINT FROM ehf_emp_biometric_data WHERE USER_ID=?");
		fingerPrintList=genericDao.executeSQLQueryList(BioMetricVo.class, query.toString(),args);
		
		if(fingerPrintList!=null && fingerPrintList.size()>0)
		{
			if((bioMetricVo.getFINGERPRINT()).equalsIgnoreCase(fingerPrintList.get(0).getFINGERPRINT()))
				isMatched=true;	
			
				
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isMatched;*/
		
		boolean isMatched=false; 
		String userId=null;
		try
			{
				EhfEmpBiometricData ehfEmpBioData = genericDao.findById(EhfEmpBiometricData.class, String.class, bioMetricVo.getUSERID());
				if(ehfEmpBioData!=null && bioMetricVo.getFINGERPRINT() !=null)
					{
						//Start for the Web Service Code
					//gLogger.info("After getting fingerprint start of biomwebservurl");
					
						String biomWebServURL=config.getString("BiomWebServURL");
						if(biomWebServURL!=null && !"".equalsIgnoreCase(biomWebServURL))
							{
							gLogger.info("In biometricserviceImpl webservice code");
								URL asriUrl=new URL(biomWebServURL);
								if(asriUrl!=null)
									{
										HttpURLConnection connectionObject=(HttpURLConnection)asriUrl.openConnection();
										if(connectionObject!=null)
											{
											gLogger.info("After connection establishment");
												connectionObject.setRequestMethod("GET");
												connectionObject.connect();
												int response = connectionObject.getResponseCode();
												if(response==200)
													{
														int timeOut=120000;//2Minutes
														AsriBiomServiceImplServiceStub webServiceStub=new AsriBiomServiceImplServiceStub(biomWebServURL);
														webServiceStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOut);
														AsriBiomServiceImplServiceStub.CompareBiometricValues requestObj=new CompareBiometricValues();
														requestObj.setLStrDBResult(ehfEmpBioData.getEncryptedFingerprint());
														requestObj.setLStrNewFingerPrint(bioMetricVo.getFINGERPRINT());
														AsriBiomServiceImplServiceStub.CompareBiometricValuesResponse responseObj = webServiceStub.compareBiometricValues(requestObj);
														if(responseObj!=null)
															{
																boolean resultFromWeb=responseObj.getCompareBiometricValuesReturn();
																isMatched=resultFromWeb;
																gLogger.info("After finger print comparision");
															}
													}
												connectionObject.disconnect();
											}
										else
											gLogger.error("Exception occured in matchFingerPrint() method in biometricserviceImpl Class as Connection created using biomWebServURL is null");
									}
							}
						else
							gLogger.error("Exception occured in matchFingerPrint() method in biometricserviceImpl Class as biomWebServURL from config is null ");
							
					
						//End for the Web Service Code
					}
			}
		catch(Exception e )
			{
				e.printStackTrace();
				gLogger.error("Exception occured in matchFingerPrint() method in biometricserviceImpl Class"+e.getMessage());
				return isMatched;
			}
		return isMatched;
	}
	
	public List<LabelBean> getDistrictList(String scheme) throws Exception
    {
    	List<LabelBean> districtList = new ArrayList<LabelBean>();
    	Iterator asrimListItr=null;
    	EhfmLocations asrimLocations=null;
    	try
    	{
//    		HashMap<String,Object> map=new HashMap<String,Object>();
//    		map.put("locHdrId", "LH6");
//    		map.put("activeYn", "Y");
//    		List<EhfmLocations> asrimLocationsList=(List<EhfmLocations>)genericDao.findAllByPropertyMatch(EhfmLocations.class,map);
    		
    		
    		
    		List<GenericDAOQueryCriteria> criteriaList = 
                    new ArrayList<GenericDAOQueryCriteria>();

                criteriaList.add(new GenericDAOQueryCriteria("locName", null, 
                        GenericDAOQueryCriteria.CriteriaOperator.ASC));
                criteriaList.add(new GenericDAOQueryCriteria("locHdrId", "LH6", 
                        GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
                criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y", 
                        GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
                criteriaList.add(new GenericDAOQueryCriteria("id.locParntId", scheme, 
                        GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
                List<EhfmLocations> asrimLocationsList = 
                        genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
    		
    		
    		
    		asrimListItr=asrimLocationsList.iterator();
    		while(asrimListItr.hasNext())
    		{
    			asrimLocations=(EhfmLocations)asrimListItr.next();
    			LabelBean labelBean =new LabelBean();
    			labelBean.setID(asrimLocations.getId().getLocId());
    			labelBean.setVALUE(asrimLocations.getLocName());
    			districtList.add(labelBean);
    		}
    		
        String args[] = new String[2];
        args[0]="LH6";
        args[1]="Y";
        StringBuffer query = new StringBuffer();
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
       //districtList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        return districtList;
    }
	
	public BioMetricVo getBiometricReport(BioMetricVo bioMetricVo)
	{
		BioMetricVo biometricReportLst=new BioMetricVo();
		biometricReportLst=bioMetricDao.getBiometricReport(bioMetricVo);
		return biometricReportLst;
	}
	
	public List<BioMetricVo> getBiomDepts(String SchemeId)
	{
		List<BioMetricVo> deptLst=new ArrayList<BioMetricVo>();
		
		try
		{
		StringBuffer query=new StringBuffer();
		String[] args=new String[2];
		args[0]="Y";
		args[1]=SchemeId;
		query.append(" select distinct a.id.deptId as deptId,a.deptName as deptName from EhfDeptDsgnMpg a ");
		query.append( " where a.biomYn=? and a.id.schemeId=? ");
		deptLst=genericDao.executeHQLQueryList(BioMetricVo.class,query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error occured in getBiomDepts() method in BioMetricRegistrationServiceImpl() class ");
		}
		
		return deptLst;
	}
	
	public List<String> getBiomDsgnLst(String SchemeId,String deptId)
	{
		List<EhfDeptDsgnMpg> dsgnLst=new ArrayList<EhfDeptDsgnMpg>();
		List<String> deptLst=new ArrayList<String>();
		try
		{
		StringBuffer query=new StringBuffer();
		String[] args=new String[3];
		args[0]="Y";
		args[1]=SchemeId;
		args[2]=deptId;
		
		List<GenericDAOQueryCriteria> criteriaList = 
            new ArrayList<GenericDAOQueryCriteria>();

        criteriaList.add(new GenericDAOQueryCriteria("biomYn",args[0], GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
        criteriaList.add(new GenericDAOQueryCriteria("id.schemeId",args[1], GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
        criteriaList.add(new GenericDAOQueryCriteria("id.deptId",args[2], GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

		dsgnLst=genericDao.findAllByCriteria(EhfDeptDsgnMpg.class, criteriaList);
		
		if(dsgnLst!=null)
		{
			for(EhfDeptDsgnMpg dsgn:dsgnLst)
			{
				deptLst.add(dsgn.getId().getDsgnId()+"~"+dsgn.getDsgnName()+"@");
			}
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error occured in getBiomDsgnLst() method in BioMetricRegistrationServiceImpl() class  "+e.getMessage());
		}
		
		return deptLst;
	}
	
	public List<BioMetricVo> getBiomDsgns(String SchemeId,String deptId)
	{
		List<BioMetricVo> deptLst=new ArrayList<BioMetricVo>();
		
		try
		{
		StringBuffer query=new StringBuffer();
		String[] args=new String[3];
		args[0]="Y";
		args[1]=SchemeId;
		args[2]=deptId;
		query.append(" select distinct a.id.dsgnId as dsgnId,a.dsgnName as dsgnName from EhfDeptDsgnMpg a ");
		query.append( " where a.biomYn=? and a.id.schemeId=? and a.id.deptId=? ");
		deptLst=genericDao.executeHQLQueryList(BioMetricVo.class,query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error occured in getBiomDepts() method in BioMetricRegistrationServiceImpl() class ");
		}
		
		return deptLst;
	}
	/*Added by venkatesh for getting individual Attendance reports*/
	
	public List<BioMetricVo> getAttendanceReport(BioMetricVo bioMetricVo)
	{
		List<BioMetricVo> attendanceReport=new ArrayList<BioMetricVo>();
		StringBuffer query=new StringBuffer();
		
		String fromDt=bioMetricVo.getFromDate();
		String toDate=bioMetricVo.getToDate();
		String UserId=bioMetricVo.getUserId();
		try
		{
		query.append( " select user_id as USERID,login_date AS LOGINDATE,empName AS EMPNAME,dsgnName as DESIGNATION,login_name as LOGINNAME,  " );
		query.append( " first_login as FIRSTLOGIN,last_logout as LASTLOGOUT,hosp_name AS HOSPNAME,mac_address AS MACADDRESS, " );
		query.append( " case when (hours is null and minutes is null and seconds is null) then '--NA--' " );
		query.append( " else hours||'Hours '||minutes||'Minutes '||seconds||'Seconds ' end as TOTALHOURSWORKED " );
		query.append( " from (  select a.user_id as user_id,d.login_name as login_name,to_char(login_date,'mm/dd/yyyy') as login_date,'09:00' as schedule_hours, " );
		query.append( " a.login_time as first_login,a.logout_time,null,a.logout_time as last_logout, " );
		query.append( " hosp_name as hosp_name,mac_address as mac_address, " );
		query.append( " d.first_name||''||d.middle_name||''||d.last_name as empName,e.dsgn_name as dsgnName, " );
		query.append( " trunc(diff) as hours,trunc((diff-trunc(diff))*60) as minutes, " );
		query.append( " trunc(((diff-trunc(diff))*60-trunc((diff-trunc(diff))*60))*60) as seconds " );
		query.append( " from ( select user_id,login_time,hospital_code,((logout_time-login_time)*24) as diff " );
		query.append( "  from EHF_EMP_BIOSELFLOGIN_DTLS where  user_id='"+UserId+"') c , " );
		query.append( " EHF_EMP_BIOSELFLOGIN_DTLS a,ehfm_hospitals b,ehfm_users d,ehfm_designation e " );
		query.append( " where a.hospital_code=b.hosp_id  and a.user_id='"+UserId+"' and a.user_id=d.user_id  " );
		query.append( " and e.dsgn_id=d.dsgn_id " );
		query.append( " and a.user_id=c.user_id and a.login_time=c.login_time and a.hospital_code=c.hospital_code " );
		query.append( " and a.login_date>=to_date('"+fromDt+"','mm/dd/yyyy') and  a.login_date<=to_date('"+toDate+"','mm/dd/yyyy') ) " );
		
		attendanceReport=genericDao.executeSQLQueryList(BioMetricVo.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Exception occured in getAttendanceReport() method in  BioMetricRegistrationServiceImpl() class "+ e.getMessage());
		}
		
		return attendanceReport;
	}
	@Override
	public List<BioMetricVo> getNewOthersList(BioMetricVo bioMetricVo)
	{
		StringBuffer query = new StringBuffer();
		List<BioMetricVo> othersList=new ArrayList<BioMetricVo>();
		
		try
		{
			query.append("select distinct case b.grp_id  when 'GP43' then 'NetworkTeam Leader - ' || c.last_name ");
			query.append("when 'GP42' then 'District Manager - ' || c.last_name ");
			query.append("when 'GP41' then 'District Coordinator - ' || c.last_name ");
			query.append("when 'GP49' then 'Divisional Team Leader - ' || c.last_name ");
			query.append("end as VALUE,c.user_id as ID from Ehfm_Hosp_Tl_Dc_Mpg a, Ehfm_Usr_Grps_Mpg b,Ehfm_Users c ");
			query.append("where a.hosp_Id = '"+bioMetricVo.getHospId()+"' and b.usergrp_Id = c.user_Id and (c.user_id = a.tl_Id ");
			query.append("or c.user_id = a.dc_id  or c.user_id=a.dm_id)  and b.grp_id in ('GP41', 'GP42', 'GP43','GP49') and c.user_type='CD202' order by VALUE");
			
			othersList = genericDao.executeSQLQueryList(BioMetricVo.class, query.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return othersList;
	}
}
