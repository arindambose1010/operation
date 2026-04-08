package com.ahct.common.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ahct.common.filter.AutoCompleteDao;
import com.ahct.common.util.ServiceFinder;



@SuppressWarnings({"unused", "rawtypes"})
public class FillDropDownServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Logger oslogger = Logger.getLogger(FillDropDownServlet.class);
	private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
	private static long reloadTime = System.currentTimeMillis();
	private static final long relaodInterval = 30*60*1000;
	PrintWriter writer =null;
	private static boolean sendSms = true;
	public static boolean sendSmsYn(){
		return sendSms;
	}
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		System.out.println("After configuration  :: "+ServiceFinder.ctx);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request,response);
	}
	private static Level level = null;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionForm form = null;
		ActionMapping mapping = new ActionMapping();
		String smsAction = request.getParameter("smsAction");
		String enableSendSmsLog = request.getParameter("enableSendSmsLog");
		String adminSanctionLog = request.getParameter("enableAdminSanctionLog");
		/*if(adminSanctionLog != null) {
             if("true".equalsIgnoreCase(adminSanctionLog)) {
                 boolean isLogEnable = CommonDAOImpl.enableLog(true);
                 if(isLogEnable) 
                     response.getOutputStream().write(("Adminsanction Log Enabled").getBytes());
                 else
                     response.getOutputStream().write(("Adminsanction Log :Failed to enabled").getBytes());
             }else{
                 boolean isLogEnable = CommonDAOImpl.enableLog(false);
                 if(!isLogEnable)
                     response.getOutputStream().write(("Admin Sanction Log Disabled").getBytes());
                 else
                     response.getOutputStream().write(("Admin Sanction Log : Failed to disable").getBytes());
             }
             return;
         }*/

		if(smsAction!=null){
			if("true".equalsIgnoreCase(smsAction)){
				sendSms = true;
				response.getOutputStream().write(("Status changed to "+sendSms).getBytes());
			}else if("false".equalsIgnoreCase(smsAction)){
				sendSms = false;
				response.getOutputStream().write(("Status changed to "+sendSms).getBytes());
			}else{
				response.getOutputStream().write(("Old status is "+sendSms+" and status is not changed.").getBytes());
			}
			return;
		}
		try
		{
			//get the PrintWriter object to write the html page
			PrintWriter writer = response.getWriter();
			if(request.getParameter("ip")!=null){
				writer.print(request.getRemoteAddr()+":"+request.getRemotePort());
				return;
			}
			//set the content type
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			ArrayList lResultList=new ArrayList();
			String lStrXml="";
			String lStrActionVal="";
			if(request.getParameter("actionVal")!=null)
			{
				lStrActionVal=request.getParameter("actionVal");
			}
			if("reloadUrlFilters".equals(lStrActionVal)){
				AutoCompleteDao dao = (AutoCompleteDao)ServiceFinder.getContext(request).getBean("autoCompleteDao");
				dao.reloadURLFilters(request);
				writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?><refresh-response>Done reloading URL Filters.</refresh-response>");
			}
			
			writer.println(lStrXml);

		} catch(IllegalArgumentException e)
		{
			oslogger.error(e);
			// Http 1.1 Status Code 400 = "Bad Request"
			response.sendError(400, e.getMessage());
		}
		catch (Exception e)
		{
			oslogger.error(e);
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			if(writer != null)
				writer.close();
		}
	}
	private static final BigInteger M = new BigInteger("76856518363113003718194194210031027438211726761751316148078425215845661709461469599225976335517856204507917809305351526106744994882794156246997739115204762925595146113535392627536381130483232399616206445633419999144567529423636028588684308985387001267023767474928420492239652949322414277730227762377320802079");
	private static final BigInteger P = new BigInteger("72059307403543424872547310100751173706984144415997820802879146544007328080769383414027683576918286493072404318134766333011349267084204948737538698520642915394914505476383537013998756572039350989018645040508335966408444536608342019740039243522236863095625663446105392453917809512373338898284835982096372867799");

	public static String getString(String param) throws Exception{
		byte[] ba = Base64.decodeBase64(param.getBytes());
		if(ba.length%128!=0)throw new Exception("byte array length is not multiple of 128");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for(int i=0;i<ba.length;i+=128){
			byte[] b = new byte[128];
			System.arraycopy(ba, i, b, 0, 128);
			bos.write(new BigInteger(b).modPow(P, M).toByteArray());
		}
		return new String(bos.toByteArray());
	}
	private static final String CDS = "<![CDATA[", CDE="]]>", NDS="\n<node>\n", NDE="\n</node>\n", DS="<data>", DE="</data>";
	private String getXmlFromListOfMap(List<Map<String, Object>> mapList) {

		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<listdata>");
		Set<String> keys = null;
		for(Map<String, Object> mp : mapList){
			sb.append(NDS);
			if(keys==null)
				keys = mp.keySet();
			for(String key : keys){
				Object value = mp.get(key);
				sb.append(DS).append(CDS).append(value==null?"":""+value).append(CDE).append(DE);
			}
			sb.append(NDE);
		}
		sb.append("\n</listdata>");
		//         System.out.println(sb.toString());
		return sb.toString();
	}
	private static String getXml(List<List<String>> dlst){
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<listdata>");

		for(List<String> ls : dlst){
			sb.append(NDS);
			for(String s : ls){
				sb.append(DS).append(CDS);
				try{
					sb.append(new String(s.getBytes("UTF-8")));
				} catch(Exception e) {}
				sb.append(CDE).append(DE);
			}
			sb.append(NDE);
		}

		sb.append("\n</listdata>");
		return sb.toString();
	}
	private static final String EMPTY_STR = "";
	private static String getSetReqData(String query, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		StringBuilder sb = new StringBuilder(query);
		int i= -1, j=-1;
		while((i=sb.indexOf(DS) )> -1){
			j=sb.indexOf(DE);
			if(i>-1 && j>-1){
				String nm = sb.substring(i+6, j);
				String val = request.getParameter(nm);
				val = val==null?(String)session.getAttribute(nm):val;
				val = val==null?EMPTY_STR:val;
				sb.replace(i, j+7, val);
			}
		}
		query = sb.toString();
		return query;
	}

}
