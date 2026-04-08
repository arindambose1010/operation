package com.ahct.login.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.common.service.CommonService;
import com.ahct.common.util.LdapAuthentication;
import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfContactDtlsAudit;
import com.ahct.model.EhfContactDtlsAuditId;
import com.ahct.model.EhfEnrollment;
import com.ahct.model.EhfSmsData;
import com.ahct.model.EhfmUsers;
import com.ahct.model.SgvcPswdAudit;
import com.ahct.common.util.SMSServices;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailService;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;


//TODO: Auto-generated Javadoc
/**
* The Class ChangePwdRequestServiceImpl.
*/
public class ChangePwdRequestServiceImpl implements ChangePwdRequestService {

	
	
	/** The generic dao. */
	GenericDAO genericDao;

	/** The email service. */
	EmailService emailService;

	/** The oslogger. */
	Logger oslogger = Logger.getLogger(ChangePwdRequestServiceImpl.class);

	/** The common service. */
	CommonService commonService ;

	/**
	 * Sets the common service.
	 *
	 * @param commonService the new common service
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	/** The hibernate template. */
	HibernateTemplate hibernateTemplate ;

	/**
	 * Sets the hibernate template.
	 *
	 * @param hibernateTemplate the new hibernate template
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * Gets the hibernate template.
	 *
	 * @return the hibernate template
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/** The configuration service. */
	private static ConfigurationService configurationService;

	/** The config. */
	private static CompositeConfiguration config;
	static {
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
		ChangePwdRequestServiceImpl.configurationService = configurationService;
	}

	/**
	 * Sets the email service.
	 *
	 * @param emailService the new email service
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}


	/**
	 * Change password ehf users.
	 *
	 * @param pChangPswdVO the change password vo
	 * @return the saved ehfm users
	 */
	private EhfmUsers changePasswordEhf(LabelBean pChangPswdVO,HttpServletRequest httpRequest){
		EhfmUsers ehfmUsers =null; 
		
		LdapAuthentication ldapAuth = new LdapAuthentication();

		
		/**
		 * authenticate in ldap previous password and email ID
		 */
		boolean authLdap = true ;
		try{
		
		
		if(config.getString("LDAPlogin")!=null &&  config.getString("LDAPlogin").equalsIgnoreCase("true"))
		{
			authLdap = ldapAuth.authenticateUserLDAP(pChangPswdVO.getUNITNAME(), pChangPswdVO.getID());
			oslogger.info(" Inchange password EHF authenticate user return value="+authLdap);
			if(authLdap)
			{
				authLdap = ldapAuth.updateUserLDAP(pChangPswdVO.getUNITNAME(), pChangPswdVO.getVALUE(),pChangPswdVO.getEMPID(),pChangPswdVO.getHOUSENO(),httpRequest)  ;
				
				oslogger.info(" Inchange password EHF updateUserLDAP user return value="+authLdap);
			}
			else if(authLdap==false)
				return null;

			if(authLdap)
			{
				
					ehfmUsers= genericDao.findById(EhfmUsers.class, String.class,
							pChangPswdVO.getEMPID());
					if (pChangPswdVO.getEMAILID() != null
							&& !pChangPswdVO.getEMAILID().equalsIgnoreCase(""))
						ehfmUsers.setEmailId(pChangPswdVO.getEMAILID());
					if (pChangPswdVO.getHOUSENO() != null
							&& !pChangPswdVO.getHOUSENO().equalsIgnoreCase(""))
						ehfmUsers.setMobileNo(pChangPswdVO.getHOUSENO());
					ehfmUsers.setPasswd(pChangPswdVO.getVALUE());
					oslogger.info(" password that is set to ehfm users when Ldap flag is true="+pChangPswdVO.getVALUE());
					ehfmUsers.setLstUpdDt(new java.sql.Timestamp(new Date()
					.getTime()));
					ehfmUsers.setLstUpdUser(ehfmUsers.getUserId());
					ehfmUsers = genericDao.save(ehfmUsers);
				
			}
			else if(authLdap==false)
				return null;
		}
		else
		{
			
			/* changePassword 1.2 Checking old password */
			StringBuffer hqlQuery = new StringBuffer();
			ehfmUsers= genericDao.findById(EhfmUsers.class, String.class,
					pChangPswdVO.getEMPID());
			hqlQuery.append(" select loginName as ID from EhfmUsers where DECRYPT_USER_PSWD(?) = ? and loginName = ?  ");
			String[] args1 = new String[3];
			args1[0] = ehfmUsers.getLoginName();
			args1[1] = pChangPswdVO.getID();
			args1[2] = ehfmUsers.getLoginName();
			List<LabelBean>  pswdList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);

			if(!(pswdList!=null && pswdList.size()>0))
			{
				return null;
			}
			if (pChangPswdVO.getEMAILID() != null
					&& !pChangPswdVO.getEMAILID().equalsIgnoreCase(""))
				ehfmUsers.setEmailId(pChangPswdVO.getEMAILID());
			if (pChangPswdVO.getHOUSENO() != null
					&& !pChangPswdVO.getHOUSENO().equalsIgnoreCase(""))
				ehfmUsers.setMobileNo(pChangPswdVO.getHOUSENO());
			ehfmUsers.setPasswd(pChangPswdVO.getVALUE());
			oslogger.info(" password that is set to ehfm users when Ldap flag is false="+pChangPswdVO.getVALUE());
			ehfmUsers.setLstUpdDt(new java.sql.Timestamp(new Date()
			.getTime()));
			ehfmUsers.setLstUpdUser(ehfmUsers.getUserId());
			ehfmUsers = genericDao.save(ehfmUsers);

		}

		/* changePassword 1.2 END */
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
			//ldapAuth.updateUserLDAP(pChangPswdVO.getUNITNAME(), pChangPswdVO.getID(),pChangPswdVO.getEMPID(),pChangPswdVO.getHOUSENO(),httpRequest)  ;
		}
		
		return ehfmUsers;

	}

	/**
	 * Validate and send password sms ehf.
	 *
	 * @param pChangPswdVO the change password vo
	 * @return saved EhfmUsers persistance class
	 * @desc This function is used to save data to EhfmUsers
	 */
	@SuppressWarnings("unchecked")
	private EhfmUsers validateAndSendPswdSmsEhf(LabelBean pChangPswdVO){
		String lStrUserId=pChangPswdVO.getEMPID().trim();
		List<EhfmUsers> lstEhfmUsers = null;
		Session session = null;
		SessionFactory factory =null;
		EhfmUsers ehfmUsers=new EhfmUsers();
		if (lStrUserId != null)
			factory = hibernateTemplate.getSessionFactory();
		session = factory.openSession();
		StringBuffer  sql_query2 = new StringBuffer();
		try{
			/** 1.1 fetch details of user */
			sql_query2.append("from EhfmUsers where loginName=? and serviceFlag='Y'");
			Query query2 = session.createQuery(sql_query2.toString());
			query2.setParameter ( 0, lStrUserId.toUpperCase() ) ;
			lstEhfmUsers = query2.list();
			session.close(); 
			if(lstEhfmUsers!=null && lstEhfmUsers.size()>0){
				ehfmUsers=lstEhfmUsers.get(0);
				if (pChangPswdVO.getEMAILID() != null
						&& !pChangPswdVO.getEMAILID().equalsIgnoreCase(""))
					ehfmUsers.setEmailId(pChangPswdVO.getEMAILID());
				if (pChangPswdVO.getHOUSENO() != null
						&& !pChangPswdVO.getHOUSENO().equalsIgnoreCase(""))
					ehfmUsers.setMobileNo(pChangPswdVO.getHOUSENO());
				/** 1.1 End */
				/** 1.2 if phone number doesn't exist,go  to fetch details */
				if(ehfmUsers!=null && !(ehfmUsers.getMobileNo()!=null && !"".equalsIgnoreCase(ehfmUsers.getMobileNo().trim()))){
					String lstLstName=(ehfmUsers.getLastName()!=null) ? ehfmUsers.getLastName() : "";
					ehfmUsers.setLoginName(null);
					ehfmUsers.setUserType("noPhoneNo@"+ehfmUsers.getFirstName()+" "+ lstLstName);
					return ehfmUsers;	
				}
				/** 1.2 End */
				/** 1.3  Saving data to EhfmUsers*/
				ehfmUsers.setPasswd(pChangPswdVO.getVALUE());
				ehfmUsers.setLstUpdDt(new java.sql.Timestamp(new Date()
				.getTime()));
				ehfmUsers.setLstUpdUser(ehfmUsers.getUserId());
				ehfmUsers = genericDao.save(ehfmUsers);
				/** 1.3 End */
			}
			else{
				ehfmUsers.setLoginName(null);
				ehfmUsers.setUserType("User-ID not found");
			}
		}
		catch(Exception e){
			ehfmUsers.setLoginName(null);
			ehfmUsers.setUserType("Passwod cannot be reset");
			return ehfmUsers;	
		}
		finally{
			if(session.isOpen())
				session.close();
		}
		return ehfmUsers;
	}



	/**
	 * Change password.
	 *
	 * @param pChangPswdVO the change password vo
	 * @return Returns a string i.e the status of the password change
	 * @desc This function is used to reset password.
	 */
	@Transactional
	public String changePassword(LabelBean pChangPswdVO,HttpServletRequest httpRequest) {
		SgvcPswdAudit sgvcPswdAudit = null;

		EhfmUsers ehfmUsers=null;
		String lStrEncriptedPassword=null;
		Collection<Object> saveContent=new ArrayList<Object>();
		String lStrLoginName=null;
		String lStrUserId=null;
		String lStrPhone=null;
		String lStrEmail=null;
		String lStrName=null;
		oslogger.info("in changePassword method password that has be updated="+pChangPswdVO.getVALUE()+"    old password="+pChangPswdVO.getID());
		ehfmUsers=changePasswordEhf(pChangPswdVO,httpRequest);
		if(ehfmUsers==null){
			return "WrongPswd";
		}
		lStrEncriptedPassword=ehfmUsers.getPasswd();
		lStrLoginName=ehfmUsers.getLoginName();
		lStrUserId=ehfmUsers.getUserId();
		lStrPhone=ehfmUsers.getMobileNo();
		lStrEmail=ehfmUsers.getEmailId();
		lStrName=ehfmUsers.getFirstName();


		/*1.1 End */
		try{
			if( ehfmUsers!=null){
				List<EhfEnrollment> lStEhfEnrollment=genericDao.findAllByPropertyMatch(EhfEnrollment.class, "empCode", lStrLoginName);
				if(lStEhfEnrollment!=null && lStEhfEnrollment.size()>0){ 
					EhfEnrollment ehfEnrollment=lStEhfEnrollment.get(0);
					ehfEnrollment.setEmpHphone(pChangPswdVO.getHOUSENO());
					ehfEnrollment.setLstUpdDt(new java.sql.Timestamp(new Date()
					.getTime()));

					ehfEnrollment.setLstUpdUsr(lStrUserId);
					saveContent.add(ehfEnrollment);
				}
			}
			/* Adding audit in 'SgvcPswdAudit' table */
			sgvcPswdAudit = new SgvcPswdAudit();
			String lStrSerNo = "1";


			StringBuffer lQueryBuffer = new StringBuffer();
			lQueryBuffer
			.append("select MAX(cast(SCPA.id.seq_no  as integer )) as  COUNT  from SgvcPswdAudit SCPA ");
			List<LabelBean> orderList = genericDao
					.executeHQLQueryList(LabelBean.class,
							lQueryBuffer.toString());
			if (orderList != null && orderList.size() > 0) {
				if(orderList.get(0).getCOUNT() != null && !orderList.get(0).getCOUNT() .equals(""))
					lStrSerNo = Integer.toString(orderList.get(0).getCOUNT().intValue() + 1);
			}

			sgvcPswdAudit.setEmpId(lStrUserId);
			sgvcPswdAudit.setChangeType("R");
			sgvcPswdAudit.setPassword(lStrEncriptedPassword);
			sgvcPswdAudit.setCrtDt(new java.sql.Timestamp(new Date()
			.getTime()));
			sgvcPswdAudit.setCrtUsr(pChangPswdVO.getEMPID());
			saveContent.add(sgvcPswdAudit);
			/* changePassword 1.3 END */

		} catch (Exception e) {
			e.printStackTrace();
//			oslogger.error(" Error occured while resetinf password in ChangePwdRequestServiceImpl 'changePassword' function ");
			return "no";
		}
		EhfSmsData ehfSmsData = null;
		if (lStrPhone != null && !"".equalsIgnoreCase(lStrPhone.trim()) && "Y".equalsIgnoreCase(config.getString("sendPasswordSMS")))
		{
			try {
				/* 1.4 Sending SMS on change of password */
				String templateId="1407161743205267054";
				ehfSmsData = new EhfSmsData();
				ehfSmsData
				.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				ehfSmsData.setCrtUsr(lStrUserId);
				ehfSmsData.setUserId(lStrUserId);
				ehfSmsData.setPhoneNo(pChangPswdVO.getHOUSENO());
				ehfSmsData.setEhfPasswod(pChangPswdVO.getVALUE());
				String lStrMsg = "Your password for \"Employee Health Scheme \" portal has been reset to - "+ pChangPswdVO.getVALUE()+".";
				//SendSMS sendSms =new SendSMS();
				SMSServices SMSServicesobj = new SMSServices();

				SMSServicesobj.sendSingleSMS(lStrMsg, lStrPhone,templateId);
				/** saving details in EhfSmsData*/
				ehfSmsData.setSmsText(lStrMsg);
				/* END 1.4 */
			} catch (Exception e) {
				e.printStackTrace();
				ehfSmsData.setSmsText("Msg Delivery Failed");
			} finally {
				saveContent.add(ehfSmsData);
			}
		}

		/** Saving the tables */
		saveContent=genericDao.saveAll(saveContent);
		/* 1.5 Sending E-mail on change of password */
		try{
			if (config.getString("changePwdEmailFlag") != null &&
					config.getString("changePwdEmailFlag") .equalsIgnoreCase("Y")) {
				if(lStrEmail!=null && !lStrEmail.equalsIgnoreCase(""))
				{
					String[] emailToArray = {lStrEmail};
					EmailVO emailVO = new EmailVO();
					emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
					emailVO.setTemplateName(config.getString("EHFPasswordTemplateName"));
					emailVO.setFromEmail(config.getString("emailFrom"));
					emailVO.setToEmail(emailToArray);
					emailVO.setSubject(config.getString("passwordResetEmailSubject"));
					Map<String, String> model = new HashMap<String, String>();
					model.put("Name", lStrName.trim());
					model.put("ChangPassword", pChangPswdVO.getVALUE());
					commonService.generateMail(emailVO, model);
				}
			}
		}
		catch(Exception e){
			oslogger.error("Exception Occured while reseting password and sending it through E-mail in function-->>validateAndSendPswdSmvalidateAndSendPswdSms");
		}
		/* 1.5 End */

		saveContent=genericDao.saveAll(saveContent);
		if (ehfmUsers!=null)
			return "yes";
		else
			return "no";
	}

	/**
	 * This validates user-id with registered mobile number and send the
	 * password to that mobile.
	 *
	 * @param pChangPswdVO the chang pswd vo
	 * @return Returns the result of success or failure return type is a string
	 */
	@Transactional
	public String validateAndSendPswdSms(LabelBean pChangPswdVO) {
		String templateId="1407161743205267054";
		String pStrUserId=null;
		if(pChangPswdVO.getEMPID()!=null)
			pStrUserId = pChangPswdVO.getEMPID().trim();
		String lStrReturnMsg = "true";

		EhfmUsers ehfmUsers = null;
		String lStrNewPswd = "";
		try {
			/*
			 * Generating a randon 8-digit alpha-numeric string for new
			 * password
			 */
			lStrNewPswd = RandomStringUtils.randomAlphanumeric(8);
			pChangPswdVO.setVALUE(lStrNewPswd);
			String lStrLogin=null;
			String lStrStatus=null;
			String lStrPhone=null;
			String lStrEmail=null;
			String lStrUserId=null;
			String lStrName=null;
			String lStrEncriptedPassword=null;
			/* 1.1 Redirecting for type of password reset based on type of login */
			if(pChangPswdVO.getWFTYPE()!=null && ("Hospital".equalsIgnoreCase(pChangPswdVO.getWFTYPE()) || "Trust".equalsIgnoreCase(pChangPswdVO.getWFTYPE()))){
				ehfmUsers=validateAndSendPswdSmsEhf(pChangPswdVO);
				lStrLogin=ehfmUsers.getLoginName();
				lStrStatus=ehfmUsers.getUserType();
				lStrPhone=ehfmUsers.getMobileNo();
				lStrEmail=ehfmUsers.getEmailId();
				lStrUserId=ehfmUsers.getUserId();
				lStrName=ehfmUsers.getFirstName();
				lStrEncriptedPassword=ehfmUsers.getPasswd();
			}

			/* 1.1 End */

			if(lStrLogin==null){
				return lStrStatus;
			}

			/*
			 * if user exists and given mobile number matches registered mobile
			 * number
			 */
			if (ehfmUsers!=null) {
				Collection<Object> saveObjects=new ArrayList<Object>();
				if(pChangPswdVO.getHOUSENO()!=null && !"".equalsIgnoreCase(pChangPswdVO.getHOUSENO())){
					EhfContactDtlsAudit ehfContactDtlsAudit=null;
					StringBuffer lQueryBuffer1 = new StringBuffer();
					lQueryBuffer1
					.append("select nvl(MAX(EEMA.id.seqNo),0) as  COUNT  from EhfContactDtlsAudit EEMA where EEMA.id.empNo=?");
					String[] args=new String[1];
					args[0]=lStrUserId;
					List<LabelBean> orderList1 = genericDao
							.executeHQLQueryList(LabelBean.class,
									lQueryBuffer1.toString(),args);
					if(orderList1!=null && orderList1.size()>0){
						ehfContactDtlsAudit =new EhfContactDtlsAudit();
						long lSeqCount=orderList1.get(0).getCOUNT().longValue();
						EhfContactDtlsAuditId ehfContactDtlsAuditId=new EhfContactDtlsAuditId(pStrUserId,++lSeqCount);
						ehfContactDtlsAudit.setId(ehfContactDtlsAuditId);
						ehfContactDtlsAudit.setManuType("F");
						ehfContactDtlsAudit.setPhone(pChangPswdVO.getHOUSENO());
						ehfContactDtlsAudit.setEmail(pChangPswdVO.getEMAILID());
						ehfContactDtlsAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
						ehfContactDtlsAudit.setCrtUser("Admin");
						saveObjects.add(ehfContactDtlsAudit);
					}
				}
				SgvcPswdAudit sgvcPswdAudit = null;
				/* Adding audit in 'SgvcPswdAudit' table */
				sgvcPswdAudit = new SgvcPswdAudit();
				String lStrSerNo = "1";
				StringBuffer lQueryBuffer = new StringBuffer();
				lQueryBuffer
				.append("select MAX(cast(SCPA.id.seq_no  as integer )) as  COUNT  from SgvcPswdAudit SCPA ");
				List<LabelBean> orderList = genericDao
						.executeHQLQueryList(LabelBean.class,
								lQueryBuffer.toString());
				if (orderList != null && orderList.size() > 0 && orderList.get(0)
						.getCOUNT()!=null) {
					lStrSerNo = Integer.toString(orderList.get(0)
							.getCOUNT().intValue() + 1);
				}

				sgvcPswdAudit.setEmpId(lStrUserId);
				sgvcPswdAudit.setChangeType("F");
				sgvcPswdAudit.setPassword(lStrEncriptedPassword);
				sgvcPswdAudit.setCrtDt(new java.sql.Timestamp(new Date()
				.getTime()));
				sgvcPswdAudit.setCrtUsr(lStrUserId);
				saveObjects.add(sgvcPswdAudit);
				/* Sending SMS */
				EhfSmsData ehfSmsData =null;
				boolean bSmsSent=true;
				if (lStrPhone != null && !"".equalsIgnoreCase(lStrPhone.trim()) && "Y".equalsIgnoreCase(config.getString("sendPasswordSMS"))) {
					try {
						ehfSmsData = new EhfSmsData();
						//ehfSmsData.setSerialNo(getMaxSno());
						ehfSmsData.setCrtDt(new java.sql.Timestamp(
								new Date().getTime()));
						ehfSmsData.setCrtUsr(lStrUserId);
						ehfSmsData.setUserId(lStrUserId);
						ehfSmsData.setPhoneNo(lStrPhone);
						ehfSmsData.setEhfPasswod(lStrNewPswd);
						String lStrMsg = "Your password for \"Employee Health Scheme \" portal has been reset to - "+lStrNewPswd+".";
						//SendSMS sendSms =new SendSMS();
						SMSServices SMSServicesobj = new SMSServices();
						SMSServicesobj.sendSingleSMS(lStrMsg,
								lStrPhone,templateId);
						lStrReturnMsg = "true@@"+lStrPhone;
						ehfSmsData.setSmsText(lStrMsg);
					} catch (Exception e) {
						e.printStackTrace();
//						oslogger.error("Exception Occured while reseting password and sending it through SMS in function-->>validateAndSendPswdSmvalidateAndSendPswdSms");
						ehfSmsData.setSmsText("Msg Delivery Failed");
						bSmsSent=false;
					} finally {
						saveObjects.add(ehfSmsData);
					}
				}
				/* END of sending SMS */
				/** Saving all the data */
				saveObjects=genericDao.saveAll(saveObjects);

				/*  Sending E-mail on change of password */

				if (config.getString("changePwdEmailFlag") != null &&
						config.getString("changePwdEmailFlag") .equalsIgnoreCase("Y")) {
					if(lStrEmail!=null && !lStrEmail.equalsIgnoreCase(""))
					{
						Map<String, String> model = new HashMap<String, String>();
						String[] emailToArray = {lStrEmail};
						EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);


						emailVO.setTemplateName(config.getString("EHFPasswordTemplateName"));
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("passwordResetEmailSubject"));
						model.put("Name", lStrName);
						model.put("ChangPassword", lStrNewPswd);
						System.out.println(model);

						try {
							commonService.generateMail(emailVO, model);
							if(bSmsSent)
								lStrReturnMsg = lStrReturnMsg+"##"+lStrEmail;
							else
								lStrReturnMsg = lStrReturnMsg+"@@##"+lStrEmail;
						}
						catch(Exception e){
							e.printStackTrace();
//							oslogger.error("Exception Occured while reseting password and sending it through E-mail in function-->>validateAndSendPswdSmvalidateAndSendPswdSms");
						}	
					}
				}
				/*  End of sending E-mail */
			}
			else {
				lStrReturnMsg = "User-ID not found";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
//			oslogger.error("Exception Occured while reseting password and sending it through SMS");
		}

		return lStrReturnMsg;
	}

	/**
	 * Gets the max sno.
	 *
	 * @return Returns the result of current max. serial number in EhfSmsData table
	 */
	private int getMaxSno() {
		int sno = 0;
		try {
			StringBuffer lQueryBuffer = new StringBuffer();
			lQueryBuffer
			.append("select MAX(d.serialNo) as  COUNT  from EhfSmsData d ");
			List<LabelBean> orderList = genericDao.executeHQLQueryList(
					LabelBean.class, lQueryBuffer.toString());
			if (!orderList.isEmpty())
				if (orderList.get(0).getCOUNT() != null)
					sno = orderList.get(0).getCOUNT().intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ++sno;
	}


	/**
	 * Sets the generic dao.
	 *
	 * @param genericDao the new generic dao
	 */
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

}
