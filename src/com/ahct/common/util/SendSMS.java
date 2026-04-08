
package com.ahct.common.util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import com.ahct.common.service.CommonService;
import com.tcs.framework.configuration.ConfigurationService;


// TODO: Auto-generated Javadoc
/**
 * The Class SendSMS.
 */
public class SendSMS {  

	/** The Constant SmsServiceLogger. */
	private final static Logger SmsServiceLogger = Logger
			.getLogger(SendSMS.class);

	/** The configuration service. */
	private ConfigurationService configurationService;

	/** The config. */
	private CompositeConfiguration config;

	/** The common service. */
	private CommonService commonService;

	/**
	 * Instantiates a new send sms.
	 */
	public SendSMS() {

		commonService = (CommonService) ApplicationContextProvider.getCtx().getBean("commonService");
		configurationService = (ConfigurationService) ApplicationContextProvider.getCtx().getBean("configurationService");
		config = configurationService.getConfiguration();
	}

	/**
	 * Send sms.
	 *
	 * @param message the message
	 * @param phoneList the phone list
	 * @return the string
	 * @throws AxisFault the axis fault
	 * @throws RemoteException the remote exception
	 */
	synchronized public String sendSMS(String message,String phoneList) throws RemoteException {
		String msg = null;
		String username = config.getString("smsusername");
		String password = config.getString("smspassword");
		String senderid = config.getString("smssenderId");
		String smsURL = config.getString("smsURL");
		String templateId = config.getString("templateId");
		String mobileNos =phoneList;
		HttpURLConnection connection = null;
		if(checkSMSConnection(smsURL))
		{
			try
			{
				//		 			System.setProperty("http.proxyHost", "proxy.tcs.com");
				//		 			System.setProperty("http.proxyPort", "8080");
				//		 			System.setProperty("http.proxyUser", "472207");
				//		 			System.setProperty("http.proxyPassword", "September@2013");
				URL url = new URL(smsURL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setFollowRedirects(true);

				connection = sendSingleSMS(connection,username, password, senderid,
						mobileNos, message,templateId);


				SmsServiceLogger.info("Resp Code:" + connection.getResponseCode());
				SmsServiceLogger.info("Resp Message:"
						+ connection.getResponseMessage());
				msg = "success";
			}
			catch (MalformedURLException e) {
				msg = "failure";
				SmsServiceLogger.error("MalformedURLException occured in sendSMSCG() of CommonServiceImpl.");
				e.printStackTrace();
			} catch (IOException e) {
				msg = "failure";
				SmsServiceLogger.error("IOException occured in sendSMSCG() of CommonServiceImpl(connection error).");
				e.printStackTrace();
			}
		}
		else
		{
			msg="serviceDown";
			SmsServiceLogger.error("SMS Service Down");
			commonService.saveToBuffer(message,phoneList);
		}
		return msg;
	}

	/**
	 * Send sms scheduler.
	 *
	 * @param message the message
	 * @param phoneList the phone list
	 * @return the string
	 * @throws AxisFault the axis fault
	 * @throws RemoteException the remote exception
	 */
	synchronized public String sendSMSScheduler(String message,String phoneList) throws RemoteException {
		String msg = null;
		String username = config.getString("smsusername");
		String password = config.getString("smspassword");
		String senderid = config.getString("smssenderId");
		String smsURL = config.getString("smsURL");
		String templateId = config.getString("templateId");
		String mobileNos =phoneList;
		HttpURLConnection connection = null;
		if(checkSMSConnection(smsURL))
		{
			try
			{
				/**
				 * To Check from the local environment
				 */
				/*		 			System.setProperty("http.proxyHost", "proxy.tcs.com");
			 			System.setProperty("http.proxyPort", "8080");
			 			System.setProperty("http.proxyUser", "472207");
			 			System.setProperty("http.proxyPassword", "September@2013");
				 */			 			URL url = new URL(smsURL);
				 connection = (HttpURLConnection) url.openConnection();
				 connection.setDoInput(true);
				 connection.setDoOutput(true);
				 connection.setRequestMethod("POST");
				 connection.setFollowRedirects(true);

				 connection = sendSingleSMS(connection,username, password, senderid,
						 mobileNos, message,templateId);


				 SmsServiceLogger.info("Resp Code:" + connection.getResponseCode());
				 SmsServiceLogger.info("Resp Message:"
						 + connection.getResponseMessage());
				 msg = "success";
			}
			catch (MalformedURLException e) {
				msg = "failure";
				SmsServiceLogger.error("MalformedURLException occured in sendSMSCG() of CommonServiceImpl.");
				e.printStackTrace();
			} catch (IOException e) {
				msg = "failure";
				SmsServiceLogger.error("IOException occured in sendSMSCG() of CommonServiceImpl(connection error).");
				e.printStackTrace();
			}
		}
		else
		{
			msg="serviceDown";
		}
		return msg;
	}


	// Method for sending single SMS.
	/**
	 * Send single sms.
	 *
	 * @param connection the connection
	 * @param username the username
	 * @param password the password
	 * @param senderId the sender id
	 * @param mobileNo the mobile no
	 * @param message the message
	 * @return the http url connection
	 */
	public static HttpURLConnection sendSingleSMS(HttpURLConnection connection,String username,
			String password, String senderId,
			String mobileNo, String message,String templateId) {
		try {

			String smsservicetype = "singlemsg"; // For single message.
			String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message) + "&mobileno="
					+ URLEncoder.encode(mobileNo) + "&senderid="
					+ URLEncoder.encode(senderId) + "&templateid="+URLEncoder.encode(templateId);

			connection.setRequestProperty("Content-length", String
					.valueOf(query.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(connection
					.getOutputStream());

			// write out the data
			int queryLength = query.length();
			output.writeBytes(query);
			// output.close();

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(connection
					.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
		} catch (Exception e) {
			SmsServiceLogger.error("Exception occured in sendSingleSMS() of CommonServiceImpl.");
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * Send sms bulk.
	 *
	 * @param message the message
	 * @param phoneList the phone list
	 * @return the string
	 * @throws AxisFault the axis fault
	 * @throws RemoteException the remote exception
	 */
	public String sendSMSBulk(String message,String phoneList) throws RemoteException {
		String msg = null;
		String username = config.getString("smsusername");
		String password = config.getString("smspassword");
		String senderid = config.getString("smssenderId");
		String smsURL = config.getString("smsURL");
		String templateId = config.getString("templateId");
		String mobileNos =phoneList;
		HttpURLConnection connection = null;
		try
		{
			/*System.setProperty("http.proxyHost", "proxy.tcs.com");
		 			System.setProperty("http.proxyPort", "8080");
		 			System.setProperty("http.proxyUser", "381903");
		 			System.setProperty("http.proxyPassword", "Aug@2226");*/
			URL url = new URL(smsURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFollowRedirects(true);

			connection = sendBulkSMS(connection,username, password, senderid,
					mobileNos, message,templateId);


			SmsServiceLogger.info("Resp Code:" + connection.getResponseCode());
			SmsServiceLogger.info("Resp Message:"
					+ connection.getResponseMessage());
			msg = "success";
		}
		catch (MalformedURLException e) {
			msg = "failure";
			SmsServiceLogger.error("MalformedURLException occured in sendSMSCG() of CommonServiceImpl.");
			e.printStackTrace();
		} catch (IOException e) {
			msg = "failure";
			SmsServiceLogger.error("IOException occured in sendSMSCG() of CommonServiceImpl(connection error).");
			e.printStackTrace();
		}
		return msg;
	}
	// method for sending bulk SMS
	/**
	 * Send bulk sms.
	 *
	 * @param connection the connection
	 * @param username the username
	 * @param password the password
	 * @param senderId the sender id
	 * @param mobileNos the mobile nos
	 * @param message the message
	 * @return the http url connection
	 */
	public static HttpURLConnection sendBulkSMS(HttpURLConnection connection,String username,
			String password, String senderId, String mobileNos, String message,String templateId) {
		try {
			String smsservicetype = "bulkmsg"; // For bulk msg
			String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message)
					+ "&bulkmobno=" + URLEncoder.encode(mobileNos, "UTF-8")
					+ "&senderid=" + URLEncoder.encode(senderId)
					+ "&templateid=" + URLEncoder.encode(templateId);

			connection.setRequestProperty("Content-length", String
					.valueOf(query.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(connection
					.getOutputStream());

			// write out the data
			int queryLength = query.length();
			output.writeBytes(query);
			// output.close();

			System.out.println("Resp Code:" + connection.getResponseCode());
			System.out.println("Resp Message:" + connection.getResponseMessage());

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(connection
					.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
		} catch (Exception e) {
			SmsServiceLogger.error("Exception occured in sendBulkSMS() of CommonServiceImpl.");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Send sms single uicode.
	 *
	 * @param message the message
	 * @param phoneList the phone list
	 * @return the string
	 * @throws AxisFault the axis fault
	 * @throws RemoteException the remote exception
	 */
	public String sendSMSSingleUicode(String message,String phoneList) throws RemoteException {
		String msg = null;
		String username = config.getString("smsusername");
		String password = config.getString("smspassword");
		String senderid = config.getString("smssenderId");
		String smsURL = config.getString("smsURL");
		String templateId = config.getString("templateId");
		String mobileNos =phoneList;
		HttpURLConnection connection = null;
		try
		{
			/*System.setProperty("http.proxyHost", "proxy.tcs.com");
		 			System.setProperty("http.proxyPort", "8080");
		 			System.setProperty("http.proxyUser", "381903");
		 			System.setProperty("http.proxyPassword", "Aug@2226");*/
			URL url = new URL(smsURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFollowRedirects(true);

			connection = sendSingleUicodeSMS(connection,username, password, senderid,
					mobileNos, message,templateId);


			SmsServiceLogger.info("Resp Code:" + connection.getResponseCode());
			SmsServiceLogger.info("Resp Message:"
					+ connection.getResponseMessage());
			msg = "success";
		}
		catch (MalformedURLException e) {
			msg = "failure";
			SmsServiceLogger.error("MalformedURLException occured in sendSMSCG() of CommonServiceImpl.");
			e.printStackTrace();
		} catch (IOException e) {
			msg = "failure";
			SmsServiceLogger.error("IOException occured in sendSMSCG() of CommonServiceImpl(connection error).");
			e.printStackTrace();
		}
		return msg;
	}


	/**
	 * Send single uicode sms.
	 *
	 * @param connection the connection
	 * @param username the username
	 * @param password the password
	 * @param senderId the sender id
	 * @param mobileNo the mobile no
	 * @param message the message
	 * @return the http url connection
	 */
	public static HttpURLConnection sendSingleUicodeSMS(HttpURLConnection connection, String username,
			String password, String senderId, String mobileNo, String message,String templateId) {
		try {
			URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");

			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFollowRedirects(true);

			String finalmessage = "";
			String unicodemsg = "";
			char ch = 0;
			//	String smsservicetype = "singlemsg"; // For single message.
			String smsservicetype = "unicodemsg";

			for(int i = 0 ; i < message.length();i++){

				ch = message.charAt(i);
				int j = (int) ch;
				//	System.out.println("iiii::"+j);

				unicodemsg = "&#"+j+";";
				finalmessage = finalmessage+unicodemsg;
			}
			System.out.println("ddd"+finalmessage);


			message=finalmessage;
			System.out.println("unicoded message=="+message);
			String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message) + "&mobileno="
					+ URLEncoder.encode(mobileNo) + "&senderid="
					+ URLEncoder.encode(senderId)  + "&templateid="+ URLEncoder.encode(templateId);

			connection.setRequestProperty("Content-length", String
					.valueOf(query.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(connection
					.getOutputStream());

			// write out the data
			int queryLength = query.length();
			output.writeBytes(query);
			// output.close();

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(connection
					.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
		} catch (Exception e) {
			SmsServiceLogger.error("Exception occured in sendSingleUicodeSMS() of CommonServiceImpl.");
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * Send sms bulk unicode.
	 *
	 * @param message the message
	 * @param phoneList the phone list
	 * @return the string
	 * @throws AxisFault the axis fault
	 * @throws RemoteException the remote exception
	 */
	public String sendSMSBulkUnicode(String message,String phoneList) throws RemoteException {
		String msg = null;
		String username = config.getString("smsusername");
		String password = config.getString("smspassword");
		String senderid = config.getString("smssenderId");
		String smsURL = config.getString("smsURL");
		String templateId = config.getString("templateId");
		String mobileNos =phoneList;
		HttpURLConnection connection = null;
		try
		{
			/*System.setProperty("http.proxyHost", "proxy.tcs.com");
		 			System.setProperty("http.proxyPort", "8080");
		 			System.setProperty("http.proxyUser", "381903");
		 			System.setProperty("http.proxyPassword", "Aug@2226");*/
			URL url = new URL(smsURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFollowRedirects(true);

			connection = sendBulkUnicodeSMS(connection,username, password, senderid,
					mobileNos, message,templateId);


			SmsServiceLogger.info("Resp Code:" + connection.getResponseCode());
			SmsServiceLogger.info("Resp Message:"
					+ connection.getResponseMessage());
			msg = "success";
		}
		catch (MalformedURLException e) {
			msg = "failure";
			SmsServiceLogger.error("MalformedURLException occured in sendSMSCG() of CommonServiceImpl.");
			e.printStackTrace();
		} catch (IOException e) {
			msg = "failure";
			SmsServiceLogger.error("IOException occured in sendSMSCG() of CommonServiceImpl(connection error).");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * Send bulk unicode sms.
	 *
	 * @param connection the connection
	 * @param username the username
	 * @param password the password
	 * @param senderId the sender id
	 * @param mobileNos the mobile nos
	 * @param message the message
	 * @return the http url connection
	 */
	public static HttpURLConnection sendBulkUnicodeSMS(HttpURLConnection connection , String username,
			String password, String senderId, String mobileNos, String message,String templateId) {
		try {

			SmsServiceLogger.info(message);
			String finalmessage = "";
			String unicodemsg = "";
			char ch = 0;

			for(int i = 0 ; i < message.length();i++){

				ch = message.charAt(i);
				int j = (int) ch;
				//	System.out.println("iiii::"+j);

				unicodemsg = "&#"+j+";";
				finalmessage = finalmessage+unicodemsg;
			}
			SmsServiceLogger.info("ddd"+finalmessage);

			message=finalmessage;
			SmsServiceLogger.info("unicoded message=="+message);

			String smsservicetype = "unicodemsg"; // For bulk msg
			String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message) 
					+ "&bulkmobno=" + URLEncoder.encode(mobileNos, "UTF-8") 
					+ "&senderid=" + URLEncoder.encode(senderId)
					+ "&templateid=" + URLEncoder.encode(templateId);;

			connection.setRequestProperty("Content-length", String
					.valueOf(query.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(connection
					.getOutputStream());

			// write out the data
			int queryLength = query.length();
			output.writeBytes(query);
			// output.close();

			SmsServiceLogger.info("Resp Code:" + connection.getResponseCode());
			SmsServiceLogger.info("Resp Message:" + connection.getResponseMessage());

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(connection
					.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
		} catch (Exception e) {
			SmsServiceLogger.error("Exception occured in sendBulkUnicodeSMS() of CommonServiceImpl.");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Check sms connection.
	 *
	 * @param url the url
	 * @return true, if successful
	 */
	private boolean checkSMSConnection(String url)
	{
		int responseCode=0;
		HttpURLConnection conn=null;
		boolean connection=false;
		try
		{
			conn = (HttpURLConnection)new URL(url).openConnection();
			conn.setConnectTimeout(config.getInt("SMS_URL_CONNECT_TIMEOUT"));
			conn.setReadTimeout(config.getInt("SMS_URL_READ_TIMEOUT"));
			conn.connect();
			responseCode=conn.getResponseCode();
			if(responseCode == 200)
			{
				connection=true;
			}
		}
		catch(IOException e)
		{
			System.out.println("Could not Connect to SMS Server");
		}
		finally
		{
			try
			{
				conn.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return connection;
	}

}
