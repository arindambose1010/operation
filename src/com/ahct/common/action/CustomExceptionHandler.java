package com.ahct.common.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import com.ahct.common.service.CommonService;
import com.ahct.common.util.ServiceFinder;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;

public class CustomExceptionHandler extends ExceptionHandler {

	private static final Logger logger = 
			Logger.getLogger(CustomExceptionHandler.class);

	@Override
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException {

		ConfigurationService configurationService = 
				(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		//log the error message
		ex.printStackTrace();
		logger.error(ex);
		StackTraceElement[] ss=ex.getStackTrace();
		System.out.println("Exception occured in FileName: "+ss[0].getFileName()+" with ClassName : "+ss[0].getClassName()+" in MethodName : "+ss[0].getMethodName()+" at LineNumber : "+ss[0].getLineNumber()+" and cause is : "+ex.getMessage());
		String errorMsg="Exception occured in FileName: "+ss[0].getFileName()+" with ClassName : "+ss[0].getClassName()+" in MethodName : "+ss[0].getMethodName()+" at LineNumber : "+ss[0].getLineNumber()+" and cause is : "+ex.getMessage();

		Date date = new Date();
		String crtDate=sdfw.format(date);
		if (config.getBoolean("EmailRequired")) 
		{
			String mailId = config.getString("ErrorMsgEmailList");
			String[] emailToArray = { mailId };
			EmailVO emailVO = new EmailVO();
			emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
			emailVO.setTemplateName(config.getString("EHFErrorTemplateName"));
			emailVO.setFromEmail(config.getString("emailFrom"));
			emailVO.setToEmail(emailToArray);
			emailVO.setSubject(config.getString("ErrorEmailSubject"));
			Map<String, String> model = new HashMap<String, String>();
			model.put("errorTime", crtDate);
			model.put("errorMsg", errorMsg);
			commonService.generateMail(emailVO, model);
		}
		return super.execute(ex, ae, mapping, formInstance, request, response);
	}

}
