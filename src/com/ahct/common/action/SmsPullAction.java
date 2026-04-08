package com.ahct.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.common.service.SmsPullService;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.SmsPullVO;
import com.tcs.framework.configuration.ConfigurationService;


public class SmsPullAction extends Action {
	private final static Logger GLOGGER = Logger.getLogger(SmsPullAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
				String lStrActionFlag = request.getParameter("actionFlag");
				SmsPullService smsService = (SmsPullService) ServiceFinder
						.getContext(request).getBean("smsPullService");
				ConfigurationService configurationService = (ConfigurationService) ServiceFinder
				.getContext(request).getBean("configurationService");
		       Configuration config = configurationService.getConfiguration();
		       /** The Constant LOGGER. */
		       final Logger logger = Logger.getLogger(SmsPullAction.class);
		       
				String Sender = request.getParameter("mobileNumber");
				String Destination = request.getParameter("Destination");
				String Message = request.getParameter("message");
				String Time = request.getParameter("Time");
				//logger.error("Sender"+Sender+"Destination"+Destination+"Message"+Message+"Time"+Time);
				//System.out.println("Sender"+Sender+"Destination"+Destination+"Message"+Message+"Time"+Time);
				if(Sender!=null && Message!=null )
				{
					SmsPullVO smsvo = new SmsPullVO();
					smsvo.setMessage(Message);
					smsvo.setMobilenumber(Sender);
					smsService.getStatus(smsvo);
				}
				return null;
		
			 }
}
