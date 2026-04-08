package com.ahct.common.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ahct.common.util.ServiceFinder;
import com.ahct.model.MUrlFilter;

public class XSSFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(XSSFilter.class);

    private AutoCompleteDao autoCompleteDao;
    public void init(FilterConfig filterConfig) throws ServletException {}
    public void destroy() {}    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	if(autoCompleteDao==null){
    		autoCompleteDao = (AutoCompleteDao)ServiceFinder.getContext((HttpServletRequest)request).getBean("autoCompleteDao");
        }
    	String url = ((HttpServletRequest)request).getRequestURI();
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        
       httpResponse.addHeader("X-Frame-Options", "SAMEORIGIN");
       httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpResponse.setHeader("Expires", "0"); // Proxies.
        httpResponse.setHeader("Content-Security-Policy", "default-src 'self' 'unsafe-inline'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; font-src 'self' data:; frame-ancestors 'self';img-src 'self' data:;object-src 'self' data:; connect-src 'self' http: https:;child-src 'self' https://www.google.com/maps/embed data: ;");
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
        httpResponse.setHeader("Strict-Transport-Security", "max-age=63072000; includeSubdomains; preload");
        
       
			
        Map<String, Object> urlFilterMap = new HashMap<String, Object>();
        
        List<MUrlFilter> patternList = new ArrayList<MUrlFilter>();
        List<MUrlFilter> _SQLPatternList = new ArrayList<MUrlFilter>();
        Map<String, String> paramReservedMap = new HashMap<String, String>();
        Map<String, String> valueReservedMap = new HashMap<String, String>();
        Map<String, String> paramExceptionMap = new HashMap<String, String>();
        try{
            if(AutoCompleteDao.urlFilterMap == null || AutoCompleteDao.urlFilterMap.size() == 0) {
            	autoCompleteDao.reloadURLFilters(req);
            }
        }
        catch(Exception e){
            LOGGER.error("## Exception while fetching URL Filter Map from AutoCompleteDao in XSSFilter.java --> " + e.getMessage());
            e.printStackTrace();
        }
        urlFilterMap = AutoCompleteDao.urlFilterMap;
        
        patternList = (List<MUrlFilter>)urlFilterMap.get("PatternList");
        _SQLPatternList = (List<MUrlFilter>)urlFilterMap.get("SQLPatternList");
        paramReservedMap = (Map<String, String>)urlFilterMap.get("ParamReservedMap");
        valueReservedMap = (Map<String, String>)urlFilterMap.get("ValueReservedMap");
        paramExceptionMap = (Map<String, String>)urlFilterMap.get("ParamExceptionMap");
        
       boolean urlStringFlg=false;
        /*if(req.getRequestURI().contains(".do"))
        {
        	if(req.getQueryString()!=null && req.getQueryString().length()>0)
        	{
        		String[] urlParamArr=req.getQueryString().split("&");
        			if(urlParamArr.length==1)
        			{
        				if(!paramExceptionMap.containsKey(urlParamArr[0].substring(0,urlParamArr[0].indexOf("="))))
                		{
        					System.out.println("url String"+req.getRequestURI()+" : "+req.getQueryString());
        					HttpServletResponse lHttpserres = (HttpServletResponse)response;
        					lHttpserres.addHeader("X-MHSRB-InvalidRequest", Boolean.toString(true));
        					lHttpserres.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing Parameter values");
        					urlStringFlg=true;
                		}
        			}
        			else
        			{
        				System.out.println("url String"+req.getRequestURI()+" : "+req.getQueryString());
    					HttpServletResponse lHttpserres = (HttpServletResponse)response;
    					lHttpserres.addHeader("X-MHSRB-InvalidRequest", Boolean.toString(true));
    					lHttpserres.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing Parameter values");
    					urlStringFlg=true;
        			}
        		System.out.println("url String"+req.getRequestURI()+" : "+req.getQueryString());
				HttpServletResponse lHttpserres = (HttpServletResponse)response;
				lHttpserres.addHeader("X-MHSRB-InvalidRequest", Boolean.toString(true));
				lHttpserres.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing Parameter values");
				urlStringFlg=true;	
        		
        	}
        
        }*/
        if(!urlStringFlg)
        {
        	
            boolean containsXSS = false;

            if (url != null) {
                for(MUrlFilter pattern : patternList){
                    Pattern scriptPattern = Pattern.compile(pattern.getId().getValue() != null ? pattern.getId().getValue().replace("\\\\","\\") : "", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    if(scriptPattern.matcher(url).matches()){
                        System.out.println(scriptPattern.toString() + " scriptPattern.matcher(url).matches() " + scriptPattern.matcher(url).matches());
                        containsXSS = true;
                        break;
                    }
                    else{
                        containsXSS = false;
                    }
                }
                
                Enumeration<String> params = req.getParameterNames();  
                while(params.hasMoreElements()){
                    String name = params.nextElement();
                    String value = request.getParameter(name);
                    String key = "";
                    if(!paramExceptionMap.containsKey(name)){
                        if(name != null){
    						Iterator iterator = paramReservedMap.entrySet().iterator();
                            while(iterator.hasNext()){
    							Map.Entry pair = (Map.Entry)iterator.next();
                                key = (String)pair.getKey();
                                if(name.toUpperCase().contains(key)){
                                    System.out.println("Parameter name contains :: {" + name + " = " + value + " } and parameterReservedChar is :: " + key);
                                    containsXSS = true;
                                    break;
                                }
                            }
                        }
                        if(value != null){
    						Iterator iterator = valueReservedMap.entrySet().iterator();
                             while(iterator.hasNext()){
    							Map.Entry pair = (Map.Entry)iterator.next();
                                 key = (String)pair.getKey();
                                 if (value.toUpperCase().contains(key)) {
                                     System.out.println("Value contains :: {" + name + " = " + value + " } and valueReservedChar is :: " + key);
                                     containsXSS = true;
                                     break;
                                 }
                             }
                             
                            if(!containsXSS){
                                for(MUrlFilter pattern : _SQLPatternList){
                                    Pattern scriptPattern = Pattern.compile(pattern.getId().getValue() != null ? pattern.getId().getValue().replace("\\\\","\\") : "", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                                    if(scriptPattern.matcher(value).matches()){
                                        System.out.println(scriptPattern.toString() + " scriptPattern.matcher(value).matches() " + scriptPattern.matcher(value).matches() + " (SQL Pattern) and value is --> " + value);
                                        containsXSS = true;
                                        break;
                                    }
                                    else{
                                        containsXSS = false;
                                    }
                                }
                            }
                        }
                    }
                    if(containsXSS)
                        break;
                }
                
                if(!containsXSS)
                    chain.doFilter(request, response);
                else{
                    System.out.println("## URL contains XSS.. URL --> " + url);
                    HttpServletResponse lHttpserres = (HttpServletResponse)response;
                    lHttpserres.addHeader("X-EHS-InvalidRequest", Boolean.toString(true));
                    lHttpserres.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing Parameter values");
                }
            }
            else{
                chain.doFilter(request, response);
            }
        }
        }
        
}