package com.ahct.login.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;


@Controller
public class LoginController {
	
	
	@RequestMapping("/login.htm")
	public ModelAndView loginRequest(HttpServletRequest request) {
		ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		
		Map<String,Object> data=new HashMap<String,Object>();
			
		if(config.getString("openAMRequired")!=null &&  config.getString("openAMRequired").equalsIgnoreCase("true"))
		return new ModelAndView("indexOpenAM",data);
		else
		return new ModelAndView("login/login",data);	
	}
	
	
	@RequestMapping("logout.htm")
	public ModelAndView logOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
            @SuppressWarnings("rawtypes")
			Enumeration lenum = session.getAttributeNames();
            while (lenum.hasMoreElements()) {
                String lStrSessionAttr = (String)lenum.nextElement();
                if (lStrSessionAttr != null)
                    session.removeAttribute(lStrSessionAttr);
            }
            session.invalidate();
        }
		return new ModelAndView("login/login");
	}
	}

