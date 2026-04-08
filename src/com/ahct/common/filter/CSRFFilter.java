package com.ahct.common.filter;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;

import com.tcs.framework.configuration.ConfigurationService;

/**
 * Servlet Filter implementation class CSRFFilter
 */
public class CSRFFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public CSRFFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;

		Configuration config = ConfigurationService.getInstance().getConfiguration();

		boolean csrfFlag = true;

		String validReferrer = (config.getString("VALID_REFERRER") != null && config.getString("VALID_REFERRER").trim().length() > 0) ? config.getString("VALID_REFERRER") : "";
		String referrerVal = (req.getHeader("referer") != null && ((String)req.getHeader("referer")).trim().length() > 0) ? (String)req.getHeader("referer") : "";
		String HostVal = (req.getHeader("Host") != null && ((String)req.getHeader("Host")).trim().length() > 0) ? (String)req.getHeader("Host") : "";

		//System.out.println("referrerVal -- " + referrerVal);
		//System.out.println("validReferrer --> " + validReferrer);

		List<String> acceptableDomains = null;
		if(validReferrer != null && validReferrer.trim().length() > 0)
			acceptableDomains = Arrays.asList(validReferrer.split("#"));
		httpResponse.setHeader("Server", "");
		//        	httpResponse.setHeader("SET-COOKIE", "JSESSIONID=" + req.getSession().getId() + "; HttpOnly");
		/*Cookie[] cookieArr = req.getCookies();
		if(cookieArr!=null)
			for(Cookie tmp:cookieArr){
				//        		if(tmp.getName().equalsIgnoreCase("JSESSIONID"))
				httpResponse.addHeader("SET-COOKIE", tmp.getName()+"=" + tmp.getValue() + "; HttpOnly");
	
			}*/
		if(HostVal!=null && !HostVal.equalsIgnoreCase(""))
		{
			System.out.println(HostVal);
			boolean hostFlag=false;
			if(HostVal.contains(":"))
				HostVal=HostVal.substring(0, HostVal.lastIndexOf(":"));
			// split the host name by the '.' character (but quote that as it is a regex special char)
			String[] hostParts1 = HostVal.split(Pattern.quote("."));
			System.out.println(hostParts1);
			if(hostParts1 !=null ){
				if(hostParts1.length == 1){
					if(!acceptableDomains.contains(hostParts1[0])){
						hostFlag = true;
					}
				}
				else if(hostParts1.length == 2){
					if(!acceptableDomains.contains(hostParts1[hostParts1.length - 2] + "." + hostParts1[hostParts1.length - 1])){
						hostFlag = true;
					}
				}
				else if(hostParts1.length > 2){
					if(!acceptableDomains.contains(hostParts1[hostParts1.length - 3] + "." + hostParts1[hostParts1.length - 2] + "." + hostParts1[hostParts1.length - 1])){
						hostFlag = true;
					}
				}
			}

			if(hostFlag){
				System.out.println("## Host header did not match.. host --> " + HostVal);        		        	
				httpResponse.addHeader("X-NHPS-InvalidRequest", Boolean.toString(true));
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing Parameter values");
			}

		}
		StringBuffer URL = req.getRequestURL();
		System.out.println(URL);
		if(referrerVal == null ){
			System.out.println("Referrer value is null");
			csrfFlag = false;
		}
		else{
			// parse the given referrer
			//referrerVal = "https://testtms.abnhpm.gov.in/TMS";
			URL refererURL = new URL(referrerVal);
			// split the host name by the '.' character (but quote that as it is a regex special char)
			String[] hostParts = refererURL.getHost().split(Pattern.quote("."));

			if(hostParts !=null ){
				if(hostParts.length == 1){
					if(!acceptableDomains.contains(hostParts[0])){
						csrfFlag = false;
					}
				}
				else if(hostParts.length == 2){
					if(!acceptableDomains.contains(hostParts[hostParts.length - 2] + "." + hostParts[hostParts.length - 1])){
						csrfFlag = false;
					}
				}
				else if(hostParts.length > 2){
					if(!acceptableDomains.contains(hostParts[hostParts.length - 3] + "." + hostParts[hostParts.length - 2] + "." + hostParts[hostParts.length - 1])){
						csrfFlag = false;
					}
				}
			}
		}

     	if(!csrfFlag){
     		System.out.println("## Referrer header did not match.. reffererVal --> " + referrerVal);
     		
     		
        	try{
	        	if(req.getSession() != null){
	        		Enumeration lenum = req.getSession().getAttributeNames();
					 while(lenum.hasMoreElements()){
						 String lStrSessionAttr = (String)lenum.nextElement();
						 if(lStrSessionAttr != null)
							 req.getSession().removeAttribute(lStrSessionAttr);
					 }
					 req.getSession().invalidate();
	        	}
        	}
        	catch(Exception e){
        		System.out.println("## Exception while invalidating session in CSRF Filter");
        	}
     		
            httpResponse.addHeader("X-EHF-InvalidRequest", Boolean.toString(true));
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing Parameter values");
     	}
     	else{
    		chain.doFilter(request, response);
     	}
}

/**
 * @see Filter#init(FilterConfig)
 */
public void init(FilterConfig fConfig) throws ServletException {
	// TODO Auto-generated method stub
}

}
