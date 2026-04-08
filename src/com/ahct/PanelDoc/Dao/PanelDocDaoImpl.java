package com.ahct.PanelDoc.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.PanelDoc.VO.panelDocVo;
import com.ahct.PanelDoc.Form.PanelDocForm;
//import com.ahct.bioMetric.vo.BioMetricVo;
import com.ahct.model.EhfmPnlDocAcctDtls;
import com.ahct.model.EhfmPnlDocAcctDtlsId;
import com.ahct.model.EhfmUsers;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class PanelDocDaoImpl implements PanelDocDao {

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
		PanelDocDaoImpl.configurationService = configurationService;
	}

	public static CompositeConfiguration getConfig() {
		return config;
	}

	public static void setConfig(CompositeConfiguration config) {
		PanelDocDaoImpl.config = config;
	}
	
	public panelDocVo getPanelDocLst(panelDocVo panelDocVo)
	{
		List<panelDocVo> panelDocLst1=null;
		List<panelDocVo> panelDocLst2=null;
		
		panelDocVo PanelDoc=new panelDocVo();
		StringBuffer query=new StringBuffer();
		StringBuffer query1=new StringBuffer();
		StringBuffer countQuery=new StringBuffer();
	    String State=panelDocVo.getState();
		String loginName=panelDocVo.getLOGINNAME();
		String empName=panelDocVo.getEMPLOYEENAME();
		//int startIndex=0;
		//int maxResult=30;
		int startIndex=panelDocVo.getStartIndex();
		int maxResult=panelDocVo.getMaxResults();
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		try
		{
	
			
			StringBuffer newQuery=new StringBuffer();
			
			newQuery.append("select b.user_Id as USERID, b.login_Name as LOGINNAME,b.first_Name || ' ' || b.middle_Name || ' ' || b.last_Name as EMPLOYEENAME, ");
			newQuery.append("  a.account_No as ACCOUNTNUMBER, c.bank_Name as BANKNAME ,a.CRT_USR as CREATEUSER ,to_char( a.CRT_DATE, 'DD/MM/YYYY HH:MI:SS AM') AS CREATEDATE , a.LST_UPD_USR as LASTUPDTDUSER, to_char(a.LST_UPD_DATE, 'DD/MM/YYYY HH:MI:SS AM') AS LASTUPDATEDDATE  from EHFM_PNLDOC_ACCT_DTLS a, Ehfm_Users b, Ehfm_Bank_Master c ");
			newQuery.append(" where a.user_Id = b.user_Id  and a.bank_Id = c.bank_Id  and a.active_yn = 'Y' and b.user_type='CD202' ");
			if(loginName!=null && !("").equalsIgnoreCase(loginName))
			{
				newQuery.append( " and b.login_Name like '%"+loginName+"%' ");
			}
			if(empName!=null && !("").equalsIgnoreCase(empName))
			{
				newQuery.append( "  and b.first_Name||' '||b.middle_Name||' '||b.last_Name like upper('%"+empName+"%')  ");
			}
			panelDocLst1=genericDao.executeSQLQueryList(panelDocVo.class, newQuery.toString());
			
			
			/*StringBuffer newCountQuery=new StringBuffer();
			newCountQuery.append("select count(*) as NEWCOUNT  from EHFM_PNLDOC_ACCT_DTLS a, Ehfm_Users b, Ehfm_Bank_Master c ");
			newCountQuery.append(" where a.user_Id = b.user_Id  and a.bank_Id = c.bank_Id  and a.active_yn = 'Y' ");
			panelDocLst2=genericDao.executeSQLQueryList(panelDocVo.class, newCountQuery.toString());
		*/	
		
		
		PanelDoc.setPanelDocLst(panelDocLst1);
		if(panelDocLst1!=null && panelDocLst1.size()>0){
			PanelDoc.setCount(panelDocLst1.size());
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			factory.close();
			session.close();
			
		}
		return PanelDoc;
	}
	
	public List<panelDocVo> banksList()
	{
		List<panelDocVo> bankLst=new ArrayList<panelDocVo>();
		try
		{
		StringBuffer query=new StringBuffer();
		query.append(" select distinct a.bankName as bankName from EhfmBankMaster a ");
		bankLst=genericDao.executeHQLQueryList(panelDocVo.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bankLst;
	}
	
	public List<panelDocVo> bankBranchList(String BankName)
	{
		List<panelDocVo> branchLst=new ArrayList<panelDocVo>();
		try
		{
		StringBuffer query=new StringBuffer();
		query.append(" select a.bankId as bankId, a.bankBranch as branchName from EhfmBankMaster a ");
		query.append(" where a.bankName='"+BankName+"' ");
		branchLst=genericDao.executeHQLQueryList(panelDocVo.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return branchLst;
	}
	
	public String getIfscCode(String bankId)
	{
		List<panelDocVo> IfscLst=new ArrayList<panelDocVo>();
		String ifscCode=null;
		try
		{
		StringBuffer query=new StringBuffer();
		query.append(" select a.ifcCode as ifscCode from EhfmBankMaster a ");
		query.append(" where a.bankId='"+bankId+"' ");
		IfscLst=genericDao.executeHQLQueryList(panelDocVo.class, query.toString());
		if(IfscLst!=null && IfscLst.size()>0)
		{
			ifscCode=IfscLst.get(0).getIfscCode();
		}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ifscCode;
	}
	
	public String validatePnlDoc(String loginName)
	{
		List<panelDocVo> usrLst=new ArrayList<panelDocVo>();
		boolean isUser=false;
		String loginNameTemp=null;
		try
		{
		StringBuffer query=new StringBuffer();
		query.append(" select a.loginName as loginName from EhfmUsers a ");
		query.append(" where a.loginName='"+loginName+"' and user_type='CD202' ");
		usrLst=genericDao.executeHQLQueryList(panelDocVo.class, query.toString());
		if(usrLst!=null && usrLst.size()>0)
		{
			isUser=true;
			loginNameTemp=usrLst.get(0).getLoginName();
		}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return loginNameTemp;
	}
	@Override
	public String saveAccountDetails(panelDocVo panelDocVo)
	{
		String result=null;
		Date date=new Date();
		String userId=panelDocVo.getUserId();
		String accountNo=panelDocVo.getAccountNum();
		EhfmPnlDocAcctDtls ehfmPnlDocAcctDtls = null;
		EhfmPnlDocAcctDtlsId   ehfmPnlDocAcctDtlsId=new EhfmPnlDocAcctDtlsId();
		
		try
		{
		if(userId!=null && accountNo!=null)
		{
		
			ehfmPnlDocAcctDtlsId.setAccountNo(accountNo);
			ehfmPnlDocAcctDtlsId.setUserId(userId);
			
		ehfmPnlDocAcctDtls=genericDao.findById(EhfmPnlDocAcctDtls.class,EhfmPnlDocAcctDtlsId.class,ehfmPnlDocAcctDtlsId);
		}
		if(ehfmPnlDocAcctDtls==null || "N".equalsIgnoreCase(ehfmPnlDocAcctDtls.getActive_yn()))
		{
			
			List<EhfmPnlDocAcctDtls> ehfmPnlDocAcctLst=new ArrayList<EhfmPnlDocAcctDtls>();
			List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.userId",userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			ehfmPnlDocAcctLst=genericDao.findAllByCriteria(EhfmPnlDocAcctDtls.class,criteriaList);
			
			if(ehfmPnlDocAcctLst!=null && ehfmPnlDocAcctLst.size()>0 && userId!=null)
			{
				SessionFactory factory=hibernateTemplate.getSessionFactory();
				Session session=factory.openSession();
				 Transaction tx = session.beginTransaction();
				try
				{
				StringBuffer query=new StringBuffer();
				query.append(" update ehfm_pnldoc_acct_dtls a set a.active_yn='N' where a.user_Id='"+userId+"' ");
				
				session.createSQLQuery(query.toString()).executeUpdate();
				tx.commit();
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
				
			}
			
			
			ehfmPnlDocAcctDtls=new EhfmPnlDocAcctDtls();
			String loginName=panelDocVo.getLoginName();
			List<GenericDAOQueryCriteria> criterialList=new ArrayList<GenericDAOQueryCriteria>();
			criterialList.add(new GenericDAOQueryCriteria("loginName",loginName,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmUsers> usersLst=new ArrayList<EhfmUsers>();
			usersLst=genericDao.findAllByCriteria(EhfmUsers.class,criterialList);
			if(usersLst!=null && usersLst.size()>0)
			{
				userId=usersLst.get(0).getUserId();
			}
			ehfmPnlDocAcctDtls.setId(new EhfmPnlDocAcctDtlsId(userId,accountNo));
			ehfmPnlDocAcctDtls.setActive_yn("Y");
			ehfmPnlDocAcctDtls.setCrtDt(date);
			ehfmPnlDocAcctDtls.setCrtUsr(panelDocVo.getCrtUsr());
		}
		else
		{
			ehfmPnlDocAcctDtls.setLstUpdDt(date);
			ehfmPnlDocAcctDtls.setLstUpdUsr(panelDocVo.getCrtUsr());
		}
	
		ehfmPnlDocAcctDtls.setBankId(panelDocVo.getBankId());
		ehfmPnlDocAcctDtls.setNameAsPerAcc(panelDocVo.getEmpName());
		ehfmPnlDocAcctDtls.setPanNo(panelDocVo.getPan());
		ehfmPnlDocAcctDtls.setPanHolderName(panelDocVo.getPanName());
		
		
		ehfmPnlDocAcctDtls=genericDao.save(ehfmPnlDocAcctDtls);
		result="Panel Doctor account details saved successfully";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public panelDocVo getPanelDocDetails(String UserId)
	{
		panelDocVo userDtls=new panelDocVo();
		List<panelDocVo> empLst=new  ArrayList<panelDocVo>();
		String name=null;
		try
		{
		StringBuffer query=new StringBuffer();
		query.append(" select b.loginName as loginName,b.userId as userId,a.panNo as pan,a.panHolderName as panName,  ");
		query.append(" a.nameAsPerAcc as empName,a.bankId as bankId,c.bankName as bankName,c.bankBranch as branchName,c.ifcCode as ifscCode,a.id.accountNo as accountNum ");
		query.append("  from EhfmPnlDocAcctDtls a, EhfmUsers b, EhfmBankMaster c");
		query.append("  where a.id.userId=b.userId  and a.bankId=c.bankId and a.active_yn='Y'  ");
		query.append("  and a.id.userId='"+UserId+"' ");
		empLst=genericDao.executeHQLQueryList(panelDocVo.class,query.toString());
		
		if(empLst!=null && empLst.size()>0)
		{
			userDtls.setUserId(empLst.get(0).getUserId());
			userDtls.setLoginName(empLst.get(0).getLoginName());
			userDtls.setPan(empLst.get(0).getPan());
			userDtls.setPanName(empLst.get(0).getPanName());
			userDtls.setEmpName(empLst.get(0).getEmpName());
			userDtls.setBankId(empLst.get(0).getBankId());
			userDtls.setBankName(empLst.get(0).getBankName());
			userDtls.setBranchName(empLst.get(0).getBranchName());
			userDtls.setIfscCode(empLst.get(0).getIfscCode());
			userDtls.setAccountNum(empLst.get(0).getAccountNum());
			
		}
		else
		{
			EhfmUsers ehfmUsers=new EhfmUsers();
			if(UserId!=null)
			ehfmUsers=genericDao.findById(EhfmUsers.class, String.class, UserId);
			if(ehfmUsers!=null && ehfmUsers.getUserId()!=null)
			{
				userDtls.setUserId(ehfmUsers.getUserId());
				userDtls.setLoginName(ehfmUsers.getLoginName());
				if(ehfmUsers.getFirstName()!=null)
					
					name=ehfmUsers.getFirstName();
					
				
				if(ehfmUsers.getMiddleName()!=null)
					if(name!=null)
					name=name+" "+ehfmUsers.getMiddleName();
					else
						name=ehfmUsers.getMiddleName();	
				if(ehfmUsers.getLastName()!=null)
					if(name!=null)
					name=name+" "+ehfmUsers.getLastName();
					else
						name=ehfmUsers.getLastName();
				
				
				userDtls.setEmpName(name);
				
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return userDtls;
	}
	
	public String getUserId(String loginName)
	{
		String userId=null;
		try
		{
		List<GenericDAOQueryCriteria> criterialList=new ArrayList<GenericDAOQueryCriteria>();
		criterialList.add(new GenericDAOQueryCriteria("loginName",loginName,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfmUsers> usersLst=new ArrayList<EhfmUsers>();
		usersLst=genericDao.findAllByCriteria(EhfmUsers.class,criterialList);
		if(usersLst!=null && usersLst.size()>0)
		{
			userId=usersLst.get(0).getUserId();
		}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		
		
		return userId;
	}
	
}
