package com.ahct.followup.service;

import org.displaytag.decorator.TableDecorator;

import com.ahct.common.vo.LabelBean;


public class FpClaimPaymentDecorator extends TableDecorator{

	private String LEVELID;
	
	public String getLEVELID() {
		LabelBean obj=(LabelBean) getCurrentRowObject();
		LEVELID="<a href=\"javascript:viewCaseDtls('"+obj.getLEVELID()+"','"+obj.getSUBID()+"','"+obj.getCochlearYN()+"')\"title=\"Click here to view Follow Up details\"><font style=\"font:normal\"  color=navy>"+obj.getLEVELID()+"</font></a>";
		 return LEVELID;
	}
}
