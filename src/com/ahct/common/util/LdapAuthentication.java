package com.ahct.common.util;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ahct.model.EhfSmsData;
import com.ahct.login.vo.LoginCreationVO;

public class LdapAuthentication {
	
	
	//Adding for receiving ldap details ---start
		public static ConfigurationService getConfigurationService() {
			return configurationService;
		}
		public static CompositeConfiguration getConfig() {
			return config;
		}
		
	    private static ConfigurationService configurationService;
		private static CompositeConfiguration config;

		static {
			configurationService = ConfigurationService.getInstance(); 
			config = configurationService.getConfiguration();
		}
		
		
		Logger oslogger = Logger.getLogger (LdapAuthentication.class );
		GenericDAO genericDao;
		
		final String ldapAdServer = config.getString("ldapAdServer");
		
		final String ldapSearchBase = config.getString("ldapSearchBase1")+config.getString("specialValue")+config.getString("ldapSearchBase2")+config.getString("specialValue")+config.getString("ldapSearchBase3");
		
		final String ldapUsername = config.getString("ldapUsername1")+config.getString("specialValue")+config.getString("ldapUsername2")+config.getString("specialValue")+config.getString("ldapUsername3");

		final String ldapPassword = config.getString("ldapPassword");
		
		//end
		/** The ldap server. *//*
		final String ldapAdServer = "ldap://10.10.12.100:389";

		*//** The ldap search base. *//*
		final String ldapSearchBase = "dc=aarogyasri,dc=gov,dc=in";

		*//** The ldap username. *//*
		final String ldapUsername = "uid=zimbra,cn=admins,cn=zimbra";

		*//** The ldap password. *//*
		final String ldapPassword = "zcsldapadmin";
	*/
	/**
	 * LDAP connection.
	 *
	 * @return the dir context
	 */
	public DirContext LDAPConnection(){
		DirContext authContext = null ;
		try {
			Hashtable<String, Object> env = new Hashtable<String, Object>();
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			if(ldapUsername != null) {
				env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
			}
			if(ldapPassword != null) {
				env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
			}
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, ldapAdServer);
			env.put("java.naming.ldap.attributes.binary", "objectSID");      

			authContext = new InitialDirContext(env);    
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return authContext;           
	}

	/**
	 * Authenticate user ldap.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean authenticateUserLDAP(String username,String password){
		DirContext authContext = LDAPConnection();
		/*try
			{
				//DirContext authContextNew =new InitialDirContext();
				Attributes attrs = authContext.getAttributes("supportedSASLMechanisms");
				oslogger.info("Supported SASL Mechanisms: "+attrs);
				
				DirContext authContextNew =new InitialDirContext();
				Attributes attrsN = authContextNew.getAttributes(
						"ldap://localhost:389", new String[]{"supportedSASLMechanisms"});
				oslogger.info("Supported SASL Mechanisms: "+attrsN);
				Attributes attrsNew = authContextNew.getAttributes(
						"ldap://10.10.12.100:389", new String[]{"supportedSASLMechanisms"});
				oslogger.info("Supported SASL Mechanisms: "+attrsNew);
				
			} 
		catch(NamingException e)
			{
				e.printStackTrace();
				oslogger.error("naming Exception in supportedSASLMechanisms"+e.getMessage());
				return false;
			}*/
		//String mailId = username+"@aarogyasri.gov.in";
		//System.out.println("mail id :"+mailId);
		String searchFilter = "(&(objectClass=person) (uid="+username+"))";
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration<SearchResult> results;
		try {
				results = authContext.search(ldapSearchBase, searchFilter, searchControls);
				SearchResult searchResult = null;
	         	   while(results.hasMoreElements()) 
		         	   {
						searchResult = (SearchResult) results.nextElement();
						String uid = (String)searchResult.getAttributes().get("uid").get();
						
						String base = "ou=people,dc=aarogyasri,dc=gov,dc=in"; 
						String dn = "uid=" + uid + "," + base;
						
						Hashtable<String, Object> authEnv = new Hashtable<String, Object>();
						
						//Simple Authentication
						//authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
						
						//Anonymous Authentication
						/*authEnv.put(Context.SECURITY_AUTHENTICATION, "none"); //Not Mandatory in Anonymous
						authEnv.put(Context.SECURITY_PRINCIPAL, dn);*/
						
						//DIGEST-MD5 Authentication
						/*authEnv.put(Context.SECURITY_AUTHENTICATION, "DIGEST-MD5");
						authEnv.put("javax.security.sasl.qop","auth-conf"); //Privacy Protection(Also Integrity Protection)*/						
						//Using SSL
						/*authEnv.put(Context.SECURITY_PROTOCOL, "ssl");*/
						
						//CRAM-MD5 Authentication
						//authEnv.put(Context.SECURITY_AUTHENTICATION, "CRAM-MD5");
						
						//EXTERNAL Authentication
						authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
						//authEnv.put(Context.SECURITY_PROTOCOL, "ssl");
						
						authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
						authEnv.put(Context.PROVIDER_URL, ldapAdServer);
						authEnv.put(Context.SECURITY_PRINCIPAL, dn);
						authEnv.put(Context.SECURITY_CREDENTIALS, password);
						authEnv.put("com.sun.jndi.ldap.trace.ber", System.out);
						//authEnv.put("javax.security.sasl.server.authentication","true");
						
						try 
							{
								DirContext context = new InitialDirContext(authEnv); 
								oslogger.info("Authentication Success!");
								context.close();
								authContext.close();
								return true;
							}
							catch (Exception ex) 
							{
								ex.printStackTrace();
								oslogger.info("Something went wrong in change Password Authentication!");
								authContext.close();
								return false;
							}
		         	   }
			}
		catch (NamingException e) 
			{
			// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		return false;	
	}



	/**
	 * Update user ldap.
	 *
	 * @param mail the mail
	 * @param modifiedVal the modified val
	 * @return true, if successful
	 */
	public boolean updateUserLDAP(String mail,String modifiedVal,String userId,String phn,HttpServletRequest httpRequest){
		DirContext authContext = LDAPConnection();
		System.out.println("after connection : "+userId);
		ModificationItem[] mods = new ModificationItem[1];
		Attribute mod0 = new BasicAttribute("userPassword", modifiedVal);
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
		//String mailId = mail+"@aarogyasri.gov.in";
		//System.out.println("mail id :"+mailId);
	     String searchFilter = "(&(objectClass=person) (uid="+mail+"))";
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration<SearchResult> results;
		ApplicationContext applCont=WebApplicationContextUtils.getRequiredWebApplicationContext(httpRequest.getSession().getServletContext());
		GenericDAO genericDao=(GenericDAO)applCont.getBean("genericDao");
		EhfSmsData sms = new EhfSmsData();
		System.out.println("New passwd : "+modifiedVal);
		sms.setCrtDt(new java.sql.Timestamp(
		new Date().getTime()));
		sms.setCrtUsr("ADMIN");
		sms.setUserId(userId);
		sms.setPhoneNo(phn);
		sms.setEhfPasswod(modifiedVal);
		System.out.println("before save sms data: "+modifiedVal);
		oslogger.info("before save sms data:"    +   modifiedVal );
		genericDao.save(sms);
		oslogger.info("after save sms data:"    +   modifiedVal );
		System.out.println("after save sms data: "+modifiedVal);
		try {
			results = authContext.search(ldapSearchBase, searchFilter, searchControls);
			SearchResult searchResult = null;
        	while(results.hasMoreElements()) {
				searchResult = (SearchResult) results.nextElement();
				System.out.println(searchResult.getAttributes());
				oslogger.info("these are attributes"+searchResult.getAttributes());
				String uidVal = (String) searchResult.getAttributes().get("uid").get();
				System.out.println(uidVal);
				oslogger.info("uidVal"+uidVal);
				String modificationAtt = "uid="+uidVal+",ou=people,dc=aarogyasri,dc=gov,dc=in" ;
				authContext.modifyAttributes(modificationAtt, mods);
				System.out.println("modification sucessful");
				oslogger.info("modification sucessful");
				return true;
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Removes the user ldap.
	 *
	 * @param mail the mail
	 * @param modifiedVal the modified val
	 * @return true, if successful
	 */
	public boolean removeUserLDAP(String mail,String modifiedVal){
		DirContext authContext = LDAPConnection();
		oslogger.info("Ldap connect return value in removeUserLDAP"+authContext);
		ModificationItem[] mods = new ModificationItem[1];
		Attribute mod0 = new BasicAttribute("userPassword", modifiedVal);
		oslogger.info("userPassword="+modifiedVal);
		mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, mod0);

		String searchFilter = "(&(objectClass=person) (mail="+mail+"))";
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration<SearchResult> results;
		try {
			results = authContext.search(ldapSearchBase, searchFilter, searchControls);
			SearchResult searchResult = null;

			while(results.hasMoreElements()) {
        	searchResult = (SearchResult) results.nextElement();
				String uidVal = (String) searchResult.getAttributes().get("uid").get();
				System.out.println(uidVal);
				oslogger.info(uidVal);
				String modificationAtt = "uid="+uidVal+",ou=people,dc=aarogyasri,dc=gov,dc=in" ;
				authContext.modifyAttributes(modificationAtt, mods);
				System.out.println("modification sucessful");
				oslogger.info("modification sucessful");
				return true;
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Creates the user ldap.
	 *
	 * @param loginCreationVO the login creation vo
	 * @return true, if successful
	 */
	public boolean createUserLDAP(LoginCreationVO loginCreationVO){
		DirContext authContext = LDAPConnection();
		oslogger.info("Ldap connect return value in createUserLDAP"+authContext);
		try {
			Attribute amod0 = new BasicAttribute("mail", loginCreationVO.getEmailId());
			Attribute amod1 = new BasicAttribute("userPassword", loginCreationVO.getPassword());
			Attribute amod2 = new BasicAttribute("givenName", loginCreationVO.getEmpCode());
			Attribute amod3 = new BasicAttribute("cn", loginCreationVO.getFirstName());

			Attribute amod4 = new BasicAttribute("objectClass");
			amod4.add("top");
			amod4.add("person");
			amod4.add("inetOrgPerson");
			amod4.add("organizationalPerson");	      

			Attribute amod5 = new BasicAttribute("sn", loginCreationVO.getFirstName());
			Attribute amod6 = new BasicAttribute("uid", loginCreationVO.getFirstName());

			
			oslogger.info("sn"+ loginCreationVO.getFirstName());
			BasicAttributes b = new BasicAttributes(true);
			b.put(amod0);
			b.put(amod1);
			b.put(amod2);
			b.put(amod3);
			b.put(amod4);
			b.put(amod5);
			b.put(amod6);

			String modificationAtt = "uid="+loginCreationVO.getFirstName()+",ou=people,dc=aarogyasri,dc=gov,dc=in" ;       
			Context result = authContext.createSubcontext(modificationAtt, b);
			
			oslogger.info("result="+ result);
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Removes the user ldap.
	 *
	 * @param loginCreationVO the login creation vo
	 * @return true, if successful
	 */
	public boolean removeUserLDAP(LoginCreationVO loginCreationVO){
		DirContext authContext = LDAPConnection();
		oslogger.info("Ldap connect return value in removeUserLDAP parameter Vo"+authContext);
		try {        
			String modificationAtt = "uid="+loginCreationVO.getFirstName()+",ou=people,dc=aarogyasri,dc=gov,dc=in" ;    
			oslogger.info(modificationAtt);
			authContext.destroySubcontext(modificationAtt);
		} catch (NamingException e) {
			e.printStackTrace();
		}		
		return true;
	}
}
