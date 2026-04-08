package com.ahct.claims.service;

import org.displaytag.decorator.TableDecorator;

import com.ahct.common.vo.LabelBean;



public class chronicClaimPaymentDecorator extends TableDecorator{

	private String ID;
	//private String CONST;
	
	public String getID() {
		LabelBean obj=(LabelBean) getCurrentRowObject();
		ID="<a href=\"javascript:viewChronicDtls('"+obj.getSUBID()+"')\"title=\"Click here to view Chronic Case details\"><font style=\"font:normal\"  color=navy>"+obj.getID()+"</font></a>";
		 return ID;
	}
//	
//	public String getCONST() {
//		LabelBean obj=(LabelBean) getCurrentRowObject();
//		CONST = "<html:checkbox   name='claimsFlowForm' property='caseSelected' value='"+obj.getID()+"' />";
//			return CONST;
//	}

}
