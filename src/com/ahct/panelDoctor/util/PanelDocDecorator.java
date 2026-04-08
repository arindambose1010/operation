package com.ahct.panelDoctor.util;

import org.displaytag.decorator.TableDecorator;

import com.ahct.panelDoctor.vo.PanelDocPayVO;

public class PanelDocDecorator  extends TableDecorator{
	
	public String getDOC_NAME(){
	PanelDocPayVO obj=(PanelDocPayVO) getCurrentRowObject();
	String DOC_ID="<a href=\"javascript:popUp('"+obj.getDOC_ID()+"','"+obj.getDOC_NAME()+"')\"><font style=\"font:normal;text-decoration:underline\"  color=black>"+obj.getDOC_NAME()+"</font></a>";
	 
	return DOC_ID;
	}
	
	public String getID(){
		PanelDocPayVO obj=(PanelDocPayVO) getCurrentRowObject();
		 String ID="<input type=checkbox name=checkId value='"+obj.getDOC_ID()+'@'+obj.getAMOUNT()+"' onclick=\"javascript:fn_addCheckValues('"+obj.getDOC_ID()+"','"+obj.getAMOUNT()+"')\"></input>";
	     return ID; 
		}
	
	public String getTDSID(){
		PanelDocPayVO obj=(PanelDocPayVO) getCurrentRowObject();
		 String TDSID="<input type=checkbox name=checkId value='"+obj.getCASE_ID()+'@'+obj.getAMOUNT()+"' onclick=\"javascript:fn_addCheckValues('"+obj.getCASE_ID()+"','"+obj.getAMOUNT()+"')\"></input>";
	     return TDSID; 
		}
	
	
	
}
