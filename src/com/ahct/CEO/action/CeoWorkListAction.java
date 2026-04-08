package com.ahct.CEO.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.CEO.form.CeoWorkListForm;
import com.ahct.CEO.service.CeoWorkListService;
import com.ahct.CEO.vo.EdcHospitalListVO;
import com.ahct.CEO.vo.EdcRequestRemarksVO;
import com.ahct.CEO.vo.EdcVO;
import com.ahct.CEO.vo.UtilityVO;
import com.ahct.common.constants.EmpanelmentConstants;
import com.ahct.common.vo.LabelBean;
import com.ahct.CEO.vo.EmpanelHospVO;

import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class CeoWorkListAction extends Action{
	
	  public enum ActionVal {
	        GETCEOWORKLIST,SAVEDTLS,GETEDCCEOWORKLIST,SAVEEDCCEODTLS,GETHOSPDTLS,EDCCEOREMARKS,SHOWATTACHMENTS ;
	    }
	
	 public ActionForward execute(ActionMapping mapping, ActionForm form, 
             HttpServletRequest request, 
             HttpServletResponse response) throws Exception {
		 
		      
		 ActionVal actionVal = ActionVal.valueOf(request.getParameter(ASRIConstants.ACTION_VAL));
		 ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
			Configuration config = configurationService.getConfiguration();
		 CeoWorkListService CeoWorkListServiceObj = (CeoWorkListService)ServiceFinder.getContext(request).getBean("ceoWorkListService");
		CeoWorkListForm ceoWorkListForm = null;
		ceoWorkListForm = (CeoWorkListForm)form;    
	    HttpSession session = request.getSession ( false ) ;
	    String lStrResultPage = HtmlEncode.verifySession(request, response);
	    if (lStrResultPage.length() > 0){
	        return mapping.findForward("regSessionExpire");
	    }
	     lStrResultPage = "";
         String userId=(String)session.getAttribute("UserID");
         String lStrUserState = "";
      	if ((session.getAttribute ("userState") != null ) && !(session.getAttribute ("userState").equals ( "" )))
 	    	lStrUserState = ( String ) session.getAttribute ( "userState" ) ;
      	String userName = ( String ) session.getAttribute ( "userName" ) ;
      	String lStrMenu = request.getParameter("menu");
      	ceoWorkListForm.setMenu(lStrMenu);
        String deempanelSplties="";
        deempanelSplties = config
				.getString("DE-EmpanelmentofSpecialties.VALUE");
        String showSpecialities = "display:none";
        String showRemarksTextArea = "display:none";
		 switch(actionVal)
		 {
			 case GETCEOWORKLIST:
			 {
	    			int  totalRecords=1,pageNo=1,showPage=1,rowsPerPage=10,pages=1;
	    			int startIndex=1;
	    			int endIndex=10;
	    			int startPage=1;
	    			int endPage=10;
	    			
	    			if("othrSt".equalsIgnoreCase(lStrMenu))
	    			{
	    				 if(EmpanelmentConstants.AP.equalsIgnoreCase(lStrUserState))
	  		           {
	    					 lStrUserState = EmpanelmentConstants.TG;
	  		           }
	  		           else if(EmpanelmentConstants.TG.equalsIgnoreCase(lStrUserState))
	  		           {
	  		        	   	lStrUserState = EmpanelmentConstants.AP;
	  		        	   	
	  		           }
	  		          
	    			}
	    			
				 List<EmpanelHospVO> ceoWorklist=CeoWorkListServiceObj.getCeoWorkList(lStrUserState,lStrMenu);
				 ceoWorkListForm.setWorkList(ceoWorklist);
				 
				 if(ceoWorkListForm.getWorkList()!=null &&ceoWorkListForm.getWorkList().size()>0){
					 totalRecords=ceoWorkListForm.getWorkList().size();
				 if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage"))){
						
						rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
					}
		         
		         if(rowsPerPage==0){
		        	 
		        	 rowsPerPage=10;
		         }
					if(totalRecords>rowsPerPage){
						
						
						if((totalRecords%rowsPerPage)==0){
							pages=totalRecords/rowsPerPage;
							
						}else{
							
							pages=(totalRecords/rowsPerPage)+1;
						}
						
					}else{
						
						pages=1;
					}
					
					if(request.getParameter("pageNo")==null ||"".equalsIgnoreCase(request.getParameter("pageNo"))){
						
						
						 if(pages>10 && showPage<=10){
								
								request.setAttribute("next", "next");
								ceoWorkListForm.setNext("next");
								
							}
				              if(pages>10 && (showPage-10)>1){
								
								request.setAttribute("prev", "prev");
								ceoWorkListForm.setPrev("prev");
								
				              }
						
						
						
					}
					
		      
					if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
						
							String str=request.getParameter("pageNo");				
						showPage=Integer.parseInt(str);
						
						startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
						
						
			           int x=ceoWorkListForm.getStartPage();
						
						int y=ceoWorkListForm.getEndPage();
						
						if(y>pages){
						y=pages;
						}
						
						if(pages>10 && pages>y){
							
							
							request.setAttribute("next", "next");
							ceoWorkListForm.setNext("next");
							
						}
						
						if(pages>10 && x>10){
							request.setAttribute("prev", "prev");
							ceoWorkListForm.setPrev("prev");
						}
						
						
						startPage=x;
						endPage=y;
						
						
					}
					
					if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
						int x=ceoWorkListForm.getStartPage();
						
						int y=ceoWorkListForm.getEndPage();
						if(x>=10 && pages>10){
							
							request.setAttribute("next", "next");
							ceoWorkListForm.setNext("next");
							
						}
						
						if(x-10>10 && pages>10){
							
							request.setAttribute("prev", "prev");
							ceoWorkListForm.setPrev("prev");
						}
						
						if(showPage==1){
							
							if(totalRecords>rowsPerPage){
								
								startIndex=1;
								endIndex=rowsPerPage;
							}else{
							
								startIndex=(showPage-1)*rowsPerPage+1;
								if((startIndex+rowsPerPage)<totalRecords){
									
									endIndex=startIndex+rowsPerPage;
								}else{
									
									endIndex=totalRecords;
								}
								
								
							}
						}
						startPage=x-10;
						endPage=x-1;
						
						
						ceoWorkListForm.setStartPage(startPage);
						ceoWorkListForm.setEndPage(endPage);
						
			startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
					}
					
					
					if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
		                int x=ceoWorkListForm.getStartPage();
						
						int y=ceoWorkListForm.getEndPage();
					
						
						startPage=y+1;
						endPage=y+10;
						if(endPage>pages){
							
							endPage=pages;
						}
						
						if(pages>startPage+10){
							request.setAttribute("next", "next");
							ceoWorkListForm.setNext("next");
							
						}
						
						if(startPage-10>=1){
							
							request.setAttribute("prev", "prev");
							ceoWorkListForm.setPrev("prev");
						}
						ceoWorkListForm.setStartPage(startPage);
						ceoWorkListForm.setEndPage(endPage);
						showPage=startPage;
		              startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
					
						
						
						
					}
					
					if(showPage==1){
						
						if(totalRecords>rowsPerPage){
							
							startIndex=1;
							endIndex=rowsPerPage;
						}else{
						
							startIndex=(showPage-1)*rowsPerPage+1;
							if((startIndex+rowsPerPage)<totalRecords){
								
								endIndex=startIndex+rowsPerPage;
							}else{
								
								endIndex=totalRecords;
							}
							
							
						}
					}
					startIndex=startIndex-1;
   					List<EmpanelHospVO> listVo=new ArrayList<EmpanelHospVO>();
   					
   				    for(int j=startIndex;j<endIndex;j++){
   						
   				    	EmpanelHospVO vo=new EmpanelHospVO();
                       	
                       	vo.setHOSPID(ceoWorkListForm.getWorkList().get(j).getHOSPID());
                       	vo.setHOSPNAME(ceoWorkListForm.getWorkList().get(j).getHOSPNAME());
                       	vo.setDISTRICT(ceoWorkListForm.getWorkList().get(j).getDISTRICT());
                       	vo.setHOSPLOCATION(ceoWorkListForm.getWorkList().get(j).getHOSPLOCATION());
                        vo.setSTATE(ceoWorkListForm.getWorkList().get(j).getSTATE()); 
                        vo.setVALUE(ceoWorkListForm.getWorkList().get(j).getVALUE());      
                       	listVo.add(vo);

   				}
   				    
   				    if(listVo!=null && listVo.size()>0){    	
   				    	ceoWorkListForm.setWorkList(listVo);
   				    	 				    	
   				    }
   				
   				    request.setAttribute("liPageNo",showPage);
   					if(ceoWorkListForm.getWorkList().size()==0){
   						request.setAttribute("startIndex", 0);
   					}else{
   						
   						request.setAttribute("startIndex", startIndex+1);
   					}
   					if(ceoWorkListForm.getWorkList().size()==0){
   						request.setAttribute("endIndex", 0);
   					}else{
   						request.setAttribute("endIndex", endIndex);
   						
   					}
   					
   					request.setAttribute("rowsPerPage", rowsPerPage);
   					request.setAttribute("pages", pages);
   					request.setAttribute("startPage", startPage);
   					request.setAttribute("endPage", endPage);
   					request.setAttribute("endPage", endPage);
   					if(ceoWorkListForm.getWorkList().size()==0){
   						
   						request.setAttribute("totalRecords", 0);
   					}else{
   						request.setAttribute("totalRecords", totalRecords);
   					}
   				    
				 }	    
   			
				 lStrResultPage="ceoWorkList";
				 
			 }
			 break;
			 case SAVEDTLS:
			 {
				 String lStrBtntype = request.getParameter("btnType");
				 request.setAttribute("btn",lStrBtntype);
				 String fromPage = request.getParameter("fromPage");
				 String hospInfoIdList = (String)request.getParameter("hospInfoIdList"); 
				 boolean rslt=false;
				 Map<String,Object> lParamMap = new HashMap<String,Object>();
				 lParamMap.put("btnType", lStrBtntype);
				 lParamMap.put("hospInfoIdList", hospInfoIdList);
				 lParamMap.put("userId", userId);
				 lParamMap.put("scheme",lStrUserState);
				 if("othrSt".equalsIgnoreCase(lStrMenu))
				 {
					 rslt = CeoWorkListServiceObj.saveOthrStDtls(lParamMap);
				 }
				 else
				 {
					 rslt = CeoWorkListServiceObj.saveDtls(lParamMap);
				 }
				
				 request.setAttribute("msg", rslt);
				 lStrResultPage="ceoWorkList";
			 }
			 break;
			 case GETEDCCEOWORKLIST:
			 {
				 
				 
					int  totalRecords=1,pageNo=1,showPage=1,rowsPerPage=10,pages=1;
	    			int startIndex=1;
	    			int endIndex=10;
	    			int startPage=1;
	    			int endPage=10;
	    			String ceoGrp="GP16";
				 List<EdcVO> edcCeoWorklist=CeoWorkListServiceObj.getEdcCeoWorklist(ceoGrp, lStrUserState);
				
				 ceoWorkListForm.setEdcCeoWorklist(edcCeoWorklist);
				 if(ceoWorkListForm.getEdcCeoWorklist()!=null &&ceoWorkListForm.getEdcCeoWorklist().size()>0){
					 totalRecords=ceoWorkListForm.getEdcCeoWorklist().size();
				 if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage"))){
						
						rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
					}
		         
		         if(rowsPerPage==0){
		        	 
		        	 rowsPerPage=10;
		         }
					if(totalRecords>rowsPerPage){
						
						
						if((totalRecords%rowsPerPage)==0){
							pages=totalRecords/rowsPerPage;
							
						}else{
							
							pages=(totalRecords/rowsPerPage)+1;
						}
						
					}else{
						
						pages=1;
					}
					
					if(request.getParameter("pageNo")==null ||"".equalsIgnoreCase(request.getParameter("pageNo"))){
						
						
						 if(pages>10 && showPage<=10){
								
								request.setAttribute("next", "next");
								ceoWorkListForm.setNext("next");
								
							}
				              if(pages>10 && (showPage-10)>1){
								
								request.setAttribute("prev", "prev");
								ceoWorkListForm.setPrev("prev");
								
				              }
						
						
						
					}
					
		      
					if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
						
							String str=request.getParameter("pageNo");				
						showPage=Integer.parseInt(str);
						
						startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
						
						
			           int x=ceoWorkListForm.getStartPage();
						
						int y=ceoWorkListForm.getEndPage();
						
						if(y>pages){
						y=pages;
						}
						
						if(pages>10 && pages>y){
							
							
							request.setAttribute("next", "next");
							ceoWorkListForm.setNext("next");
							
						}
						
						if(pages>10 && x>10){
							request.setAttribute("prev", "prev");
							ceoWorkListForm.setPrev("prev");
						}
						
						
						startPage=x;
						endPage=y;
						
						
					}
					
					if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
						int x=ceoWorkListForm.getStartPage();
						
						int y=ceoWorkListForm.getEndPage();
						if(x>=10 && pages>10){
							
							request.setAttribute("next", "next");
							ceoWorkListForm.setNext("next");
							
						}
						
						if(x-10>10 && pages>10){
							
							request.setAttribute("prev", "prev");
							ceoWorkListForm.setPrev("prev");
						}
						
						if(showPage==1){
							
							if(totalRecords>rowsPerPage){
								
								startIndex=1;
								endIndex=rowsPerPage;
							}else{
							
								startIndex=(showPage-1)*rowsPerPage+1;
								if((startIndex+rowsPerPage)<totalRecords){
									
									endIndex=startIndex+rowsPerPage;
								}else{
									
									endIndex=totalRecords;
								}
								
								
							}
						}
						startPage=x-10;
						endPage=x-1;
						
						
						ceoWorkListForm.setStartPage(startPage);
						ceoWorkListForm.setEndPage(endPage);
						
			startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
					}
					
					
					if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
		                int x=ceoWorkListForm.getStartPage();
						
						int y=ceoWorkListForm.getEndPage();
					
						
						startPage=y+1;
						endPage=y+10;
						if(endPage>pages){
							
							endPage=pages;
						}
						
						if(pages>startPage+10){
							request.setAttribute("next", "next");
							ceoWorkListForm.setNext("next");
							
						}
						
						if(startPage-10>=1){
							
							request.setAttribute("prev", "prev");
							ceoWorkListForm.setPrev("prev");
						}
						ceoWorkListForm.setStartPage(startPage);
						ceoWorkListForm.setEndPage(endPage);
						showPage=startPage;
		              startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
					
						
						
						
					}
					
					if(showPage==1){
						
						if(totalRecords>rowsPerPage){
							
							startIndex=1;
							endIndex=rowsPerPage;
						}else{
						
							startIndex=(showPage-1)*rowsPerPage+1;
							if((startIndex+rowsPerPage)<totalRecords){
								
								endIndex=startIndex+rowsPerPage;
							}else{
								
								endIndex=totalRecords;
							}
							
							
						}
					}
					startIndex=startIndex-1;
   					List<EdcVO> listVo=new ArrayList<EdcVO>();
   					
   				    for(int j=startIndex;j<endIndex;j++){
   						
   				    	EdcVO vo=new EdcVO();
                       	
                      vo.setActionId(ceoWorkListForm.getEdcCeoWorklist().get(j).getActionId());
                      
                      vo.setActionType(config.getString(edcCeoWorklist.get(j).getActionType()));
                      vo.setHospName(ceoWorkListForm.getEdcCeoWorklist().get(j).getHospId());
                      vo.setHospId(ceoWorkListForm.getEdcCeoWorklist().get(j).getHospCode());
                      vo.setCrtDt(ceoWorkListForm.getEdcCeoWorklist().get(j).getCrtDt());
                     
                     vo.setActionAndHospId(ceoWorkListForm.getEdcCeoWorklist().get(j).getHospCode()+"@"+ceoWorkListForm.getEdcCeoWorklist().get(j).getActionId());
                       	listVo.add(vo);

   				}
   				    
   				    if(listVo!=null && listVo.size()>0){    	
   				    	ceoWorkListForm.setEdcCeoWorklist(listVo);
   				    	 				    	
   				    }
   				
   				    request.setAttribute("liPageNo",showPage);
   					if(ceoWorkListForm.getEdcCeoWorklist().size()==0){
   						request.setAttribute("startIndex", 0);
   					}else{
   						
   						request.setAttribute("startIndex", startIndex+1);
   					}
   					if(ceoWorkListForm.getEdcCeoWorklist().size()==0){
   						request.setAttribute("endIndex", 0);
   					}else{
   						request.setAttribute("endIndex", endIndex);
   						
   					}
   					
   					request.setAttribute("rowsPerPage", rowsPerPage);
   					request.setAttribute("pages", pages);
   					request.setAttribute("startPage", startPage);
   					request.setAttribute("endPage", endPage);
   					request.setAttribute("endPage", endPage);
   					if(ceoWorkListForm.getEdcCeoWorklist().size()==0){
   						
   						request.setAttribute("totalRecords", 0);
   					}else{
   						request.setAttribute("totalRecords", totalRecords);
   					}
				 } 
				 lStrResultPage="EdcCeoWorklist";
				 
			 }
			 break;
			 
			 case SAVEEDCCEODTLS:
			 {
				 String sucessMsg="";
				 boolean msg=false;
				 String lStrBtntype = (String)request.getParameter("btnType");
				 String actionIdList = (String)request.getParameter("actionIdList"); 
				/* boolean rslt = CeoWorkListServiceObj.saveDtls(lStrBtntype,hospInfoIdList,userId);*/
				  sucessMsg=CeoWorkListServiceObj.saveEdsCeoDtls(actionIdList, lStrBtntype, userName);
				 msg=CeoWorkListServiceObj.updateCeoStatus(lStrBtntype, userName, actionIdList, lStrUserState);
				 request.setAttribute("msg", msg);
				 lStrResultPage="EdcCeoWorklist";
			
				 
			 }
			 break;

			 case GETHOSPDTLS:
			 {
				 String lStrHospInfoId = request.getParameter("hospInfoId");
				 String lStrFrmMenu = request.getParameter("menu");
				 ceoWorkListForm.setMenu(lStrFrmMenu);
				 List<EmpanelHospVO> hospDtlsList = CeoWorkListServiceObj.getHospDtls(lStrHospInfoId);
				 ceoWorkListForm.setHospDtlsList(hospDtlsList);
				 Number dutyDctrsCnt =  CeoWorkListServiceObj.getDutyDctrsCnt(lStrHospInfoId);
				 Number paramdcsCnt =  CeoWorkListServiceObj.getParamdcsCnt(lStrHospInfoId);
				 Number splstsCnt =  CeoWorkListServiceObj.getSplstsCnt(lStrHospInfoId);
				 ceoWorkListForm.setDutyDctrCnt(dutyDctrsCnt);
				 ceoWorkListForm.setParamdcCnt(paramdcsCnt);
				 ceoWorkListForm.setSplstCnt(splstsCnt);
				 lStrResultPage="aprvlsPage";
				 
			 }
			 break;

		  case EDCCEOREMARKS:
			 {
				 List<UtilityVO> spltyList1 = null;
				 
				 String actionTypeTemp=null;
				 List<EdcRequestRemarksVO> edcActionDtls = null;
				 List<EdcRequestRemarksVO>  edcRequestDtls=null;
					List<UtilityVO> articleList = null;
				 if(request.getParameter("actionId")!=null && "".equalsIgnoreCase(request.getParameter("actionId"))){
					 
					 ceoWorkListForm.setActionId(request.getParameter("actionId"));
				 }
                if(request.getParameter("hospName")!=null && "".equalsIgnoreCase(request.getParameter("hospName"))){
					 
					 ceoWorkListForm.setHospName(request.getParameter("hospName"));
				 }
				 
                if(request.getParameter("hospId")!=null && "".equalsIgnoreCase(request.getParameter("hospId"))){
					 
					 ceoWorkListForm.setHospId(request.getParameter("hospId"));
				 }
                
                ceoWorkListForm.setScheme(lStrUserState);
                if(ceoWorkListForm.getScheme().equalsIgnoreCase("CD201")){
                	ceoWorkListForm.setScheme("Andhra Pradesh");
                	
                	
                }
                
                
                if(ceoWorkListForm.getScheme().equalsIgnoreCase("CD202")){
                	ceoWorkListForm.setScheme("Telangana");
                	
                	
                }
                
                
                ceoWorkListForm.setHospType(CeoWorkListServiceObj.getHospType(ceoWorkListForm.getHospId())) ;
             String spltyList= CeoWorkListServiceObj.getHospSplties(ceoWorkListForm.getHospId());
                ceoWorkListForm.setHospSpltyString(spltyList);
                String workFlowId = CeoWorkListServiceObj.getTypeOfCR(ceoWorkListForm.getActionId());
                
                int currentGrplevel = CeoWorkListServiceObj.getGroupHierarchy(
    					workFlowId, "GP16");
                
                List<List<EdcRequestRemarksVO>> remarksList = CeoWorkListServiceObj
    					.getPreviousRemarks(ceoWorkListForm.getActionId(), currentGrplevel);
                ceoWorkListForm.setRemarksList(remarksList);
                edcActionDtls = CeoWorkListServiceObj.getEdcActionDtls(ceoWorkListForm.getActionId());
                
                if (edcActionDtls != null && edcActionDtls.size() > 0) {
    				edcRequestDtls = edcActionDtls;

    			}
            	String isMedco = CeoWorkListServiceObj.verifyIfMedco(userId);
    			if (isMedco != null) {
    				if (remarksList.size() > 0) {
    					articleList = CeoWorkListServiceObj.getArticleList(ceoWorkListForm.getActionId());
    				}
    				if (articleList.size() > 0) {
    					ceoWorkListForm.setArticleList(articleList);
    				}
    			}
    			
    			ceoWorkListForm.setEdcRequestDtls(edcRequestDtls);
                actionTypeTemp = CeoWorkListServiceObj.getActionType(ceoWorkListForm.getActionId());
    			if (actionTypeTemp.equalsIgnoreCase(deempanelSplties)) {
    				spltyList1 = CeoWorkListServiceObj.getSpltyDempanelList(ceoWorkListForm.getActionId());
    				ceoWorkListForm.setSpltyList(spltyList1);
    				showSpecialities = "display:block";
    			}
                ceoWorkListForm.setHospLocation(CeoWorkListServiceObj.getHospLocation(ceoWorkListForm.getHospId()));
                List<AttachmentVO> attachList = new ArrayList<AttachmentVO>();
    			List<AttachmentVO> attchPres = new ArrayList<AttachmentVO>();
        		List<AttachmentVO> attachViewList = CeoWorkListServiceObj
    					.viewAttachments(ceoWorkListForm.getActionId());

    			if (attachViewList.size() > 0) {

    				for (int i = 0; i < attachViewList.size(); i++) {

    					AttachmentVO attachmentVO = new AttachmentVO();

    					attachmentVO.setACTUAL_NAME(attachViewList.get(i).getACTUAL_NAME());
    						
    					attachmentVO.setAttachPath(attachViewList.get(i)
    							.getAttachPath());
    					attachmentVO.setActOrder(attachViewList.get(i)
    							.getActOrder());
    					attchPres.add(attachmentVO);
    				}
    			}
                
    			ceoWorkListForm.setAttPres(attchPres);
    			
    			
    			 showRemarksTextArea = "display:block";
    			 request.setAttribute("showRemarksTextArea", showRemarksTextArea);
    			request.setAttribute("showSpecialities", showSpecialities);
				 lStrResultPage="edcCeoRemarksPage";
				 
			 }
			 break;
			 
			 case SHOWATTACHMENTS: {
					String lStrFilePath = null;

					String lStrContentType = request.getContentType();// start 005
					lStrContentType = (lStrContentType == null) ? ASRIConstants.EMPTY_STRING
							: lStrContentType;
					lStrFilePath = request.getParameter("path");
					File file = null;
					FileInputStream fis = null;
					DataInputStream dis = null;
					String lStrType = null;
					OutputStream out = response.getOutputStream();
					/**
			           * 
			           */
					String fileExt = lStrFilePath.substring((lStrFilePath
							.lastIndexOf(".") + 1));
					String lStrFileName = lStrFilePath.substring((lStrFilePath
							.lastIndexOf("/") + 1));
					String attachMode = "attachment";
					if (fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPG)
							|| fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPEG)
							|| fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_GIF)) {
						attachMode = "inline";
					}
					lStrContentType = ASRIConstants.FILE_EXT.get(fileExt);

					/**
			           * 
			           */
					String dir = lStrFilePath;
					byte[] lbBytes = null;
					try {
						if (lStrFileName != null && !lStrFileName.equals("")) {
							file = new File(dir);
							fis = new FileInputStream(file);
							dis = new DataInputStream(fis);
							lbBytes = new byte[dis.available()];
							fis.read(lbBytes);
							request.setAttribute("File", lbBytes);
							request.setAttribute("ContentType", lStrContentType);
							request.setAttribute("FileName", lStrFileName);
							request.setAttribute("Extn", lStrType);
							response.setContentType("image/jpeg" );
							response.setHeader("Content-Disposition", "" + attachMode
									+ "; filename=" + lStrFileName);// 006
							out.write(lbBytes);
						} else {
							lbBytes = new byte[0];
						}
					} catch (Exception e) {
						e.getMessage();
						e.printStackTrace();
					} finally {
						out.close();
					}
				}
					break;
		 }
		  
		 return mapping.findForward(lStrResultPage);
		 
	 }

}
