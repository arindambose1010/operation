package com.ahct.login.service;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.LdapAuthentication;
import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.LabelBean;
import com.ahct.login.dao.LoginDao;
import com.ahct.login.vo.EmployeeDetailsVO;
import com.ahct.login.vo.LoginDtlsVO;
import com.ahct.login.vo.MenuVo;
import com.ahct.model.EhfContactDtlsAudit;
import com.ahct.model.EhfContactDtlsAuditId;
import com.ahct.model.EhfSmsData;
import com.ahct.model.EhfmDesignation;
import com.ahct.model.EhfmUserLoginDtls;
import com.ahct.model.EhfmUsers;
import com.ahct.model.SgvcPswdAudit;
import com.ahct.common.util.SMSServices;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;



public class LoginServiceImpl implements LoginService{
	
	CommonService asriCommonService;
	GenericDAO genericDao;
	LoginDao loginDao;
	
	Logger oslogger = Logger.getLogger(ChangePwdRequestServiceImpl.class);
	
	public CommonService getAsriCommonService() {
		return asriCommonService;
	}
	public GenericDAO getGenericDao() {
		return genericDao;
	}
	public LoginDao getLoginDao() {
		return loginDao;
	}
	public static ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public static CompositeConfiguration getConfig() {
		return config;
	}
	public void setAsriCommonService(CommonService asriCommonService) {
		this.asriCommonService = asriCommonService;
	}
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	HibernateTemplate hibernateTemplate ;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance(); 
		config = configurationService.getConfiguration();
	}

	public boolean authenticateUser ( String userID, String password ){
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		boolean lStrResult = false ;
		SessionFactory factory=hibernateTemplate.getSessionFactory();
	    Session session = factory.openSession();
		try
	      {
			
		if(!(userID==null || "".equalsIgnoreCase(userID) || password==null || "".equalsIgnoreCase(password)))
		{
			StringBuffer hqlQuery = new StringBuffer();
			hqlQuery.append(" select loginName as ID from EhfmUsers where upper(loginName) = ? and DECRYPT_USER_PSWD(?)=? and serviceFlag=? and userType=?  and  serviceExpiryDt is null " );
		 	String[] args1 = new String[5];
		 	userID=userID.toUpperCase();
		 	args1[0] = userID;
			args1[1] = userID;
			args1[2] = password;
			args1[3]="Y";
			args1[4] = config.getString("Scheme.TG.State");
			
			
		 	List<LabelBean>  empDtlsList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);

			 if(empDtlsList != null && empDtlsList.size()>0 )
	          {
	            lStrResult = true;
	          }
	          else
	          {
	        	  lStrResult = false;
	          }
	          
	      }
		else{
			
			lStrResult = false;
		}
	      }
		catch ( Exception ex )
	      {
	       ex.printStackTrace();
			 
	        return false ;
	      }
		finally
		{
			session.close();
		}
		return lStrResult ;
	}
	 /**
     * 
     * @param textPwd
     * @return String
     * @method SHA1
     * @description This method is used to encrypt the raw password of the user
     */
      public static String SHA1(String textPwd) throws NoSuchAlgorithmException, UnsupportedEncodingException  
      {
          MessageDigest md;
          md = MessageDigest.getInstance("SHA-1");
          byte[] sha1hash = new byte[40];
          md.update(textPwd.getBytes("UTF-8"), 0, textPwd.length());
          sha1hash = md.digest();
          return convertToHex(sha1hash);
      }
	 
      private static String convertToHex(byte[] data) 
      {
          StringBuffer buf = new StringBuffer();
          for (int i = 0; i < data.length; i++) 
          {
              int halfbyte = (data[i] >>> 4) & 0x0F;
              int two_halfs = 0;
              do {
                  if ((0 <= halfbyte) && (halfbyte <= 9))
                      buf.append((char) ('0' + halfbyte));
                  else
                      buf.append((char) ('a' + (halfbyte - 10)));
                  halfbyte = data[i] & 0x0F;
              } while(two_halfs++ < 1);
          }
          return buf.toString();
      }
	 
      public List<MenuVo> getRecSubMenuListForEMP(String pStrParntModId,List<String> grpIdList, String pStrUserId, String pStrLangId)
  	{
    	  StringBuffer hqlQuery = new StringBuffer();
    	  List<LabelBean>  hospList=null;
    	  String hospType=null;
    	  String ahcFlag=null,chronicFlag=null;
    	  String blockHospAHC=null,blockHospChr=null;
    	  if(grpIdList!=null)
    	  {
    	  String args[] = new String[2];
    	  args[0]=pStrUserId;
    	  args[1]="Y";
    	  if(grpIdList.contains("GP1"))
		  {
			  hqlQuery.append("select cast(ah.hospType as string) as VALUE,ah.ahcFlag as ahcFlag, ");
			  hqlQuery.append(" ah.chronicFlag as chronicFlag from EhfmHospitals ah,");
			  hqlQuery.append(" EhfmHospMithraDtls amu where amu.id.mithraId=? and amu.endDt is null and ah.hospId = amu.id.hospId ");
			  hqlQuery.append(" and amu.activeYN=?");
			  //hqlQuery.append("  and ah.hospActiveYN='Y'");
			  hospList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args);
		  }
		  else if(grpIdList.contains("GP2"))
		  {
			  hqlQuery.append("select cast(ah.hospType as string) as VALUE,ah.ahcFlag as ahcFlag, ");
			  hqlQuery.append(" ah.chronicFlag as chronicFlag from EhfmHospitals ah,");
			  hqlQuery.append(" EhfmMedcoDtls anu where anu.id.medcoId=? and anu.endDate is null and ah.hospId = anu.id.hospId ");
			  hqlQuery.append(" and anu.activeYN=? ");
			  //hqlQuery.append("  and ah.hospActiveYN='Y'");
			  hospList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args);
		  }
    	  
    	  if(hospList != null && hospList.size()>0 )
		  {
			  if(hospList.get(0).getVALUE()!=null)
				  hospType=hospList.get(0).getVALUE();
			  if(hospList.get(0).getAhcFlag()!=null && !"".equalsIgnoreCase(hospList.get(0).getAhcFlag()))
				  ahcFlag=hospList.get(0).getAhcFlag();
			  else
				  blockHospAHC="Y";
			  if(hospList.get(0).getChronicFlag()!=null && !"".equalsIgnoreCase(hospList.get(0).getChronicFlag()))
				  chronicFlag=hospList.get(0).getChronicFlag();
			  else
				  blockHospChr="Y";
		  }
    	  
    	  }
  		  List<MenuVo> list=new ArrayList<MenuVo>();
  	        List<MenuVo> listMenu=loginDao.getActionBarModulesForEMP(pStrParntModId,pStrUserId,pStrLangId);
  	        for(MenuVo menuVo:listMenu)
  	        {
  	        	if((chronicFlag!=null && !chronicFlag.equalsIgnoreCase("Y") && "MD40".equalsIgnoreCase(menuVo.getMODID()))
  	        		||blockHospChr!=null && blockHospChr.equalsIgnoreCase("Y") && "MD40".equalsIgnoreCase(menuVo.getMODID()))
	        	{
	        		continue;	
	        	}
  	        	else if((ahcFlag!=null && !ahcFlag.equalsIgnoreCase("Y") && "MD10A".equalsIgnoreCase(menuVo.getMODID()))
  	        		||blockHospAHC!=null && blockHospAHC.equalsIgnoreCase("Y") && "MD10A".equalsIgnoreCase(menuVo.getMODID()))
	        	{
	        		continue;	
	        	}
	        	else
	        	{
	  	        	MenuVo subMenuVo=new MenuVo();
	  	        	subMenuVo.setMODID(menuVo.getMODID());
	  	        	subMenuVo.setMODNAME(menuVo.getMODNAME());
	  	        	subMenuVo.setMODURL(menuVo.getMODURL());
	  	            List<MenuVo> listSubMenus=getRecSubMenuListForEMP(menuVo.getMODID(),grpIdList,pStrUserId, pStrLangId);
	  	            subMenuVo.setListSubMenu(listSubMenus);
	  	            list.add(subMenuVo);
	        	}  
  	        }
  	         return list;
  	}
	
	public EmployeeDetailsVO checkEmpDetails(String pStrUserName,
			String pStrPassword) {
		EmployeeDetailsVO empDetails = new EmployeeDetailsVO();
		List<LabelBean> grpList=new ArrayList<LabelBean>();
		List<LabelBean> moduleList=new ArrayList<LabelBean>();
		StringBuffer  sql_query = new StringBuffer();
		String args[] = new String[1];
		if(pStrUserName!=null && !"".equalsIgnoreCase(pStrUserName))
			args[0] = pStrUserName.toUpperCase() ;
		else
			args[0]=pStrUserName;
		Session session = null;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		try{
			sql_query.append(" from EhfmUsers EU ,EhfmDesignation ED where ED.id.dsgnId=EU.dsgnId ");
			if(config.getString("EMPNL.LDAPFlag")!=null &&  config.getString("EMPNL.LDAPFlag").equalsIgnoreCase("true"))
				sql_query.append(" and upper(EU.emailId)=?");
				else
			    sql_query.append(" and upper(EU.loginName)=?");   //pStrUserName.toUpperCase()
			System.out.print("Sql Query"+sql_query);
			session = factory.openSession();
		    Query query1 = session.createQuery(sql_query.toString());
		    query1.setParameter(0, args[0]);
			Iterator ite = query1.list().iterator();
			while(ite.hasNext())
			{
			Object[] pair = (Object[]) ite.next();
			EhfmUsers ehfmUsers = (EhfmUsers)pair[0];
			EhfmDesignation ehfmDesg = (EhfmDesignation)pair[1];
			empDetails.setUSERID(ehfmUsers.getUserId());
			empDetails.setLOGINNAME(ehfmUsers.getLoginName());
			empDetails.setNAME(ehfmUsers.getFirstName()+" "+ehfmUsers.getLastName());
			empDetails.setDSGNNAME(ehfmDesg.getDsgnName());
			empDetails.setDIGILOGIN(ehfmUsers.getDigiVerifyReq());
			empDetails.setUSERNO(ehfmUsers.getUserNo());
			empDetails.setDESIGNATIONID(ehfmUsers.getDsgnId());
			empDetails.setSERVICEFLAG(ehfmUsers.getServiceFlag());
			empDetails.setUserState(ehfmUsers.getUserType());
			empDetails.setMobileNo(ehfmUsers.getMobileNo());
			empDetails.setEmail(ehfmUsers.getEmailId());
			empDetails.setUserState(ehfmUsers.getUserType());
			empDetails.setVpnAccess(ehfmUsers.getVpnAccess());
			}
			
			StringBuffer grpQuery = new StringBuffer();
			String args1[] = new String[1];
			args1[0]=empDetails.getUSERID();
		 	grpQuery.append("  select sg.id.grpId as ID from EhfmUsrGrpsMpg sg where sg.id.usergrpId= ?");
		 	grpList = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),args1);
		 	empDetails.setGrpList(grpList);
			
			grpQuery = new StringBuffer();
			grpQuery.append("  select distinct ecd.cmbDtlName as ID,ecd.cmbAttrVal as VALUE from EhfmUsrGrpsMpg sg,EhfmGrps eg,EhfmModuleGrpMpg emg,EhfmCmbDtls ecd ,EhfmUsers eu ");
			grpQuery.append("  where sg.id.usergrpId= ? and ecd.cmbDtlId=emg.id.moduleName and sg.id.grpId=eg.grpId and eg.grpId=emg.id.grpId and sg.id.usergrpId=eu.userId and eu.userType=emg.schemeId order by ecd.cmbDtlName");
			
			moduleList = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),args1);
			empDetails.setModuleList(moduleList);
		}
		catch(Exception e)
		{
		//empDetails.setMsg("Invalid User Name/Password");    
		e.printStackTrace();	
		}
		finally
		{
			session.close();
		}
		return empDetails;
	}
	
	/**
     * 
     * @param user id
     * @return String-theme name
     * @function Used to save the get theme name of a user by their user id
     */
	
	public String getTheme(String pStrUserId) {
		System.out.println("getTheme");
		System.out.println(pStrUserId);
		if(pStrUserId!=null){
		EhfmUsers lstEmpDtls =  genericDao.findById(EhfmUsers.class,String.class,pStrUserId);
		if(lstEmpDtls!=null && lstEmpDtls.getTheme()!=null && !"".equalsIgnoreCase(lstEmpDtls.getTheme()))
			return lstEmpDtls.getTheme();
			else
				return "default";
		}
		else
			return "default";
	}
	
	
	/**
     * 
     * @param user name
     * @return mobile number if user exists
     * @function This function is used to get the mobile number of a user.
     */
	
	public String getPhoneNumber(String pStrUserName){
		EhfmUsers lEhfmUsers = null;
		lEhfmUsers = genericDao.findById(EhfmUsers.class, String.class, pStrUserName.trim());
				if(lEhfmUsers!=null)
			return lEhfmUsers.getMobileNo();
		return null;
	}
	
	
	/**
     * 
     * @param theme name and user name
     * @return updates the theme name of the user
     * @function This function is used to update the theme of the user.
     */
	public boolean saveTheme(String pStrTheme,String pStrUserId){
		 List<GenericDAOQueryCriteria> criteraiList = new ArrayList<GenericDAOQueryCriteria>();
		 EhfmUsers ehfmUsers=null;;
		 try{
		 criteraiList.add(new GenericDAOQueryCriteria("userId",pStrUserId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS) );
  	   List<EhfmUsers> lstEmpDtls = genericDao.findAllByCriteria(EhfmUsers.class, criteraiList);
		if(lstEmpDtls!=null && lstEmpDtls.size()>0){
			ehfmUsers=lstEmpDtls.get(0);
			ehfmUsers.setTheme(pStrTheme);
			//String lStrPswd=asriCommonService.getDecryptedPswd(ehfmUsers.getLoginName());
			//if(lStrPswd!=null)
			//	ehfmUsers.setPasswd(lStrPswd);
			//	ehfmUsers.setTheme(pStrTheme);
			//ehfmUsers=genericDao.save(ehfmUsers);
			}
			if(ehfmUsers!=null)
				return true;
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return false;
	}
	
	
	/**
     * 
     * @param String emp id
     * @return List of side menu with its relevant data
     * @function This function is Used to fetch side menu.
     */
	public List<LabelBean> getMainMods(String pStrEmpId){
		StringBuffer grpQuery = new StringBuffer();
		grpQuery.append("  select distinct EM.modId as ID ,EM.modName as VALUE , EM.subUrl as ACTION,EM.modOrder as COUNT FROM EhfmUsrGrpsMpg EUGM,EhfmModGrpMpg EMGM,EhfmUsers EU,EhfmMod EM where EU.userId=? and EU.userId=EUGM.id.usergrpId and EUGM.id.grpId=EMGM.id.grpId and EMGM.id.modId=EM.modId order by EM.modOrder asc ");
	 	String[] args=new String[1];
	 	args[0]=pStrEmpId;
		List<LabelBean> lStModMenu = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),args);

		return lStModMenu;
		
		
		
		
	}
	
	
	public List<EmployeeDetailsVO> getContactInfoForEhfmUsers(String login) {
		StringBuffer hqlQuery = new StringBuffer();
		List<EmployeeDetailsVO>  empDtlsList=new ArrayList<EmployeeDetailsVO>();
		hqlQuery.append(" select case when mobileNo is null then ? else mobileNo end as ID,case when emailId is null then ? else emailId end as  VALUE from EhfmUsers where upper(loginName) = ? " );
	 	String[] args1 = new String[3];
	 	args1[0] = login.toUpperCase();
	 	args1[1] = "-NA-";
	 	args1[2] = "-NA-";
		try{
		
	 	 empDtlsList= genericDao.executeHQLQueryList(EmployeeDetailsVO.class, hqlQuery.toString(),args1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return empDtlsList;
	}
	      public List<EmployeeDetailsVO> checkCasesForClinicalNotes(String userId)
	      {
	    	  List<EmployeeDetailsVO> list=new ArrayList<EmployeeDetailsVO>();
	    	  try
	    	  {
	    		  list=loginDao.checkCasesForClinicalNotes(userId);
		        
	    	  }
	    	  catch(Exception e)
	    	  {
	    		  e.printStackTrace();
	    	  }
		         return list;
	      }
		@Override
		public List<LabelBean> getUserMessage(String userId,String moduleId) {
			
		List<LabelBean> list=null;
		 list=loginDao.getUserMessage(userId,moduleId);
		 
		return list;
		
		}
		@Override
		public List<LabelBean> getGroupMessage(List grpId,String moduleId) {
			
			List<LabelBean> list=null;
			 list=loginDao.getGroupMessage(grpId,moduleId);
			 
			return list;
			
			
		}
		@Override
		public List<LabelBean> getHospitalMessage(String hospId,String grpId,String moduleId) {
			List<LabelBean> list=null;
			 list=loginDao.getHospitalMessage(hospId,grpId,moduleId);
			return list;
			
		}
		@Override
		public String getHospitslId(String userId, String grpId) {
			
			String hospId=null;
			hospId=hospId=loginDao.getHospitslId(userId, grpId);
			
			
			return hospId;
		}
		
		
		
		
		
		/**
		 * This validates user-id with registered mobile number and send the
		 * OTP to that mobile
		 * @param User-id and phone number
		 * @return Returns the result of success or failure return type is a string
		 */
		@Override
		@Transactional
		public String generateOTPAndSendPswdSms(EmployeeDetailsVO employeeDetailsVO) {
			String pStrUserId=null;
			if(employeeDetailsVO.getUSERID()!=null)
			pStrUserId = employeeDetailsVO.getUSERID().trim();
			String lStrReturnMsg = null;
			String templateId="1407161769810700722";
			EhfmUsers ehfmUsers = null;
			String OTP = "";
			try {
				/*
				 * Generating a randon 8-digit alpha-numeric string for new
				 * password
				 */
				OTP = RandomStringUtils.randomNumeric(8);
				employeeDetailsVO.setVALUE(OTP);
		
				String lStrPhone=employeeDetailsVO.getMobileNo();
				
				String lStrUserId=employeeDetailsVO.getUSERID();
			
				lStrReturnMsg = OTP;
				
				

				
						EhfSmsData ehfSmsData =null;
						
						if (lStrPhone != null && !"".equalsIgnoreCase(lStrPhone.trim()) && "Y".equalsIgnoreCase(config.getString("sendPasswordSMS"))) {
						try {
								ehfSmsData = new EhfSmsData();
								//ehfSmsData.setSerialNo(getMaxSno());
								ehfSmsData.setCrtDt(new java.sql.Timestamp(
										new Date().getTime()));
								ehfSmsData.setCrtUsr(lStrUserId);
								ehfSmsData.setUserId(lStrUserId);
								ehfSmsData.setPhoneNo(lStrPhone);
								ehfSmsData.setEhfPasswod(OTP);
								String lStrMsg = "Your One-Time Password(OTP) for logging into \"Employee Health Scheme\" is "+OTP+"\n\nAHCT, Govt. of Telangana";
										
										
								//SendSMS sendSms =new SendSMS();
								SMSServices SMSServicesobj = new SMSServices();
								SMSServicesobj.sendSingleSMS(lStrMsg,
										lStrPhone,templateId);
								lStrReturnMsg = OTP;
								ehfSmsData.setSmsText(lStrMsg);
						} catch (Exception e) {
							e.printStackTrace();

//							oslogger.error("Exception Occured while reseting password and sending it through SMS in function-->>validateAndSendPswdSmvalidateAndSendPswdSms");
							ehfSmsData.setSmsText("Msg Delivery Failed");
							
						} finally {
							genericDao.save(ehfSmsData);
						}
						}
						/* END of sending SMS */
						/** Saving all the data */
						
						
						/*  Sending E-mail on change of password */
						
						
				}
			 
			
			catch (Exception e) {
				e.printStackTrace();
//				oslogger.error("Exception Occured while generating OTP and sending it through SMS");
				
			}

			return lStrReturnMsg;
		}
	/*added by venkatesh for getting scheme of medco as per mapped hospital*/ 
		
		public String getMedcoScheme(String userId)
		{
			String scheme=null;
			
			try
			{
				String[] args1 = new String[1];
			 	args1[0] = userId;
			StringBuffer query=new  StringBuffer();
			List<LabelBean> SchemeList=new ArrayList<LabelBean>();
			query.append(" select b.scheme as schemeId from EhfmMedcoDtls a,EhfmHospitals b");
			query.append( " where a.id.hospId=b.hospId and a.id.medcoId=?");
			SchemeList=genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args1);
			
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
		
		/*
		 * Added for User Service Flag Validation after 
		 * Successful Open AM Authentication
		 */
		@Override
		public boolean authenticateUserServiceFlagForOpenAM ( String userID ){
			StringBuffer query = new StringBuffer();
			String args[] = new String[2];
			boolean lStrResult = false ;
			SessionFactory factory=hibernateTemplate.getSessionFactory();
		    Session session = factory.openSession();
			try
		      {
				
			if(!(userID==null || "".equalsIgnoreCase(userID)))
				{
					StringBuffer hqlQuery = new StringBuffer();
					hqlQuery.append(" select loginName as ID from EhfmUsers where upper(loginName) = ? and serviceFlag=? and userType=? and  serviceExpiryDt is null " );
				 	String[] args1 = new String[3];
				 	userID=userID.toUpperCase();
				 	args1[0] = userID;
					args1[1]="Y";
					args1[2] = config.getString("Scheme.TG.State");
					
				 	List<LabelBean>  empDtlsList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);

					 if(empDtlsList != null && empDtlsList.size()>0 )
			          {
			            lStrResult = true;
			          }
			          else
			          {
			        	  lStrResult = false;
			          }
			          
			      }
			else{
				
				lStrResult = false;
			}
		      }
			catch ( Exception ex )
		      {
		       ex.printStackTrace();
		        return false ;
		      }
			finally
			{
				session.close();
			}
			return lStrResult ;
		}

	/*
	 * Added for Checking Hospital Type 
	 */
	public String checkHospType(String userID)
		{
			String returnMsg=null;
			try
				{
					StringBuffer query =new StringBuffer();
					String[] args1 = new String[1];
				 	args1[0] = userID;
					query.append( " select c.applictn_type ACTION " );
					query.append( " from ehfm_medco_dtls a,ehfm_hospitals b," );
					query.append( " ehf_empnl_hospinfo c,ehfm_cmb_dtls d" );
					query.append( " where a.medco_id =? and a.hosp_id = b.hosp_id " );
					query.append( " and b.hosp_empnl_ref_num = c.hospinfo_id and c.applictn_type = d.cmb_dtl_id " );
					List<LabelBean> lst= genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args1);
					if(lst!=null && lst.size()>0 && lst.get(0).getACTION()!=null)
						{
							if(lst.get(0).getACTION().equalsIgnoreCase(config.getString("dentalClinic")))
								returnMsg="clinic";
							else if(lst.get(0).getACTION().equalsIgnoreCase(config.getString("dentalHospital")))
								returnMsg="hospital";
							else
								returnMsg="N";
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					oslogger.error("Exception Occured in checkHospType method of LoginServiceImpl class");
					return returnMsg; 
				}
			return returnMsg;
		}
	public String getNimsMedco(String userId){
		StringBuffer grpQuery = new StringBuffer();
		List<LabelBean> hosp_list=new ArrayList<LabelBean>();
		String[] args1 = new String[2];
	 	args1[0] = userId;
	 	args1[1] = "Y";
		grpQuery.append("  select sg.id.hospId as ID from EhfmMedcoDtls sg where sg.id.medcoId= ? and sg.activeYN=? ");
		
		hosp_list = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),args1);
	 	//empDetails.setGrpList(grpList);
				if(hosp_list.size()>0 && hosp_list!=null)
			return hosp_list.get(0).getID();
		return null;
	}
	@Override
	public boolean checkNimsMedco(String userid) {

		StringBuffer query = new StringBuffer();
		boolean lStrResult = false ;
		SessionFactory factory=hibernateTemplate.getSessionFactory();
	    Session session = factory.openSession();
		try
	      {
			
					if(!(userid==null || "".equalsIgnoreCase(userid)))
						{
							StringBuffer hqlQuery = new StringBuffer();
							String[] args1 = new String[5];
						 	args1[0] = "Y";
						 	args1[1] = userid;
						 	args1[2] = "Y";
						 	args1[3] =config.getString("HOSP_NIMS");
						 	args1[4] =config.getString("HOSP_MNJ");  // NON Listed diseases for MNJ
							hqlQuery.append(" select em.HOSP_ID as ID from ehfm_hospitals eh, ehfm_medco_dtls em, ehfm_users ee " );
							hqlQuery.append(" WHERE ee.user_id = em.medco_id and em.hosp_id = eh.hosp_id  and em.end_dt is null  " );
							hqlQuery.append(" and em.active_yn = ? and ee.user_id=?  and ee.service_flg =? and ( eh.hosp_id= ? or eh.hosp_id = ? ) " );
							 	
						
					 	List<LabelBean>  empDtlsList= genericDao.executeSQLQueryList(LabelBean.class, hqlQuery.toString(),args1);
		
						 if(empDtlsList != null && empDtlsList.size()>0 )
				          {
				            lStrResult = true;
				          }
				          else
				          {
				        	  lStrResult = false;
				          }
				          
				      }
					else{
						
						lStrResult = false;
						}
	      }
		catch ( Exception ex )
			      {
					ex.printStackTrace();
			        return false ;
			      }
		finally
		{
			session.close();
		}
		return lStrResult ;
	
	}
@Override
	public String getNimsMedcoDoc(String caseId)
	{
		StringBuffer grpQuery = new StringBuffer();
		List<LabelBean> hosp_list=new ArrayList<LabelBean>();
		String[] args1 = new String[1];
	 	args1[0] = caseId;
		grpQuery.append("  select sg.caseHospCode as ID from EhfCase sg where sg.id.caseId=?");
		hosp_list = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),args1);
		if(hosp_list.size()>0 && hosp_list!=null)
			return hosp_list.get(0).getID();
		return null;

	}	@Override
	public EmployeeDetailsVO saveLoginDtls(LoginDtlsVO vo)
	{
		String msg="";
		EmployeeDetailsVO detailsVO = new EmployeeDetailsVO();
		try{
		EhfmUserLoginDtls efmUserLoginDtls =  genericDao.findById(EhfmUserLoginDtls.class,String.class,vo.getSeqId());
		if(efmUserLoginDtls == null)
		{
			String liNextVal="";
			Calendar calendar = null;
			calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, calendar.get( Calendar.MONTH ) + 1 );
			liNextVal = "TG"+"/"+calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+getSequence("EHF_SESSION_SEQ");
			efmUserLoginDtls = new EhfmUserLoginDtls();
			
			if(vo.getSeqId() != null && !"".equalsIgnoreCase(vo.getSeqId()))
				detailsVO.setSeqId(vo.getSeqId());
			else
				detailsVO.setSeqId(liNextVal);
			detailsVO.setSessionId(vo.getSessionId());
			
			if(vo.getSeqId() != null && !"".equalsIgnoreCase(vo.getSeqId()))
				efmUserLoginDtls.setSeqId(vo.getSeqId());
			else
				efmUserLoginDtls.setSeqId(liNextVal);
			efmUserLoginDtls.setSessionId(vo.getSessionId());
			efmUserLoginDtls.setIpAddress(vo.getIpAddress());
			//efmUserLoginDtls.setMacAddress(vo.getMacAddress());
			efmUserLoginDtls.setUserId(vo.getUserId());
			efmUserLoginDtls.setLoginTime(new java.sql.Timestamp(new Date().getTime()));
			efmUserLoginDtls.setOperations(new java.sql.Timestamp(new Date().getTime()));
			efmUserLoginDtls.setCrtdt(new java.sql.Timestamp(new Date().getTime()));
			efmUserLoginDtls.setCrtusr(vo.getUserId());
			genericDao.save(efmUserLoginDtls);
		}
		else
		{
			detailsVO.setSessionId(efmUserLoginDtls.getSessionId());
			detailsVO.setSeqId(efmUserLoginDtls.getSeqId());
			efmUserLoginDtls.setOperations(new java.sql.Timestamp(new Date().getTime()));
			genericDao.save(efmUserLoginDtls);
		}
		
		msg="Success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			oslogger.error("Exception Occured at saveLoginDtls method in LoginServiceImpl class");
			msg="fail";
		}
		return detailsVO;
	}
	@Override
	public EmployeeDetailsVO saveLogOutDtls(LoginDtlsVO vo) 
	{
		EmployeeDetailsVO detailsVO = new EmployeeDetailsVO();
		try
		{
			EhfmUserLoginDtls efmUserLoginDtls =  genericDao.findById(EhfmUserLoginDtls.class,String.class,vo.getSeqId());
			if(efmUserLoginDtls != null && (efmUserLoginDtls.getSeqId() != null && !"".equalsIgnoreCase(efmUserLoginDtls.getSeqId())))
			{
				efmUserLoginDtls.setLogoutTime(new java.sql.Timestamp(new Date().getTime()));
				genericDao.save(efmUserLoginDtls);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			oslogger.error("Exception Occured at saveLogOutDtls method in LoginServiceImpl class");
		}
		return detailsVO;
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
	@Override
	public int getVisitorCount(String userID)
	{
		StringBuffer query = new StringBuffer();
		List<CasesSearchVO> list = null;
		int count=0;
		try{
			query.append("select count(user_id) as COUNT from ehfm_user_login_dtls where user_id='"+userID+"' " );
			list=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
			if(list!=null && list.size()>0)
			{
				count=list.get(0).getCOUNT().intValue();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public String getorgFlag(String caseId) {
		// TODO Auto-generated method stub
		StringBuffer grpQuery = new StringBuffer();
		List<LabelBean> hosp_list=new ArrayList<LabelBean>();
		grpQuery.append("  select sg.secFlag as ID from EhfCase sg where sg.id.caseId= '"+caseId+"'");
		
		hosp_list = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString());
	 	//empDetails.setGrpList(grpList);
				if(hosp_list.size()>0 && hosp_list!=null)
			return hosp_list.get(0).getID();
		return null;
	}
}
