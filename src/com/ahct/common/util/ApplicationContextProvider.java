package com.ahct.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext ctx=null;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		ApplicationContextProvider.ctx=arg0;
	}
	public static ApplicationContext getCtx() {
		return ctx;
	}
}
