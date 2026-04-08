package com.ahct.login.service;

import javax.servlet.http.HttpServletRequest;

import com.ahct.common.vo.LabelBean;

public interface ChangePwdRequestService {

	/**
	 * Change password.
	 *
	 * @param pChangPswdVO the change password vo
	 * @return the string message
	 */
	public String changePassword ( LabelBean pChangPswdVO ,HttpServletRequest httpRequest);

	/**
	 * Validate and send password sms.
	 *
	 * @param pChangPswdVO the change password vo
	 * @return the string
	 */
	public String validateAndSendPswdSms(LabelBean pChangPswdVO);

}

