package com.ahct.CEO.action;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;












import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;












import com.ahct.CEO.form.CeoApprovalsForm;
import com.ahct.CEO.service.CeoApprovalsService;


import com.ahct.CEO.vo.SQLChangeMgmtTransVO;
import com.ahct.common.util.HtmlEncode;



import com.ahct.common.util.ServiceFinder;
import com.ahct.CEO.vo.ChangeMgmtVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.constants.ASRIConstants;
import com.ahct.model.SgvaCRMgmtAttach;
import com.tcs.framework.configuration.ConfigurationService;


public class PendingApprovals extends Action {
	 private static Logger glogger = Logger.getLogger ( PendingApprovals.class ) ;    
	   
		public ActionForward execute(ActionMapping mapping,ActionForm form,
	                                 HttpServletRequest request,HttpServletResponse response) throws Exception
     {
	    HttpSession session = null;
	         session = request.getSession ( false ) ;
	        String lStrUserState = null;
	        String lStrUser = null;
	        ChangeMgmtVO changeMgmtVO = null;
	        if  ((session.getAttribute ("userState") != null ) && !(session.getAttribute ("userState").equals ( "" )))
	 	    	lStrUserState = ( String ) session.getAttribute ( "userState" ) ;
	        if  ((session.getAttribute ("UserID") != null ) && !(session.getAttribute ("UserID").equals ( "" )))
	 	    	lStrUser = ( String ) session.getAttribute ( "UserID" ) ;
	        String lstrSessionVerify = HtmlEncode.verifySession ( request, response ) ;
	      
	        if ( lstrSessionVerify.length () > 0 )
	             return mapping.findForward("sessionExpire");
	       
	         response.setHeader("Pragma", "");
	          response.setHeader("Cache-Control", "private");
	          response.setDateHeader("Expires", Long.MAX_VALUE);
	          String lStrFlag = null;
	          String lStrCrId="";
	          String lStrEmpId = null;
	          String lStrResultPage = null; 
	          SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
	          try
	          {
	              CeoApprovalsForm ceoApprovalsForm=(CeoApprovalsForm)form;                                    
	              CeoApprovalsService ceoWorklistService=(CeoApprovalsService)ServiceFinder.getContext(request).getBean("ceoApprovalService");
	              ConfigurationService configurationService=(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
	              Configuration config = configurationService.getConfiguration();
	              
				if ( session.getAttribute ( "UserID" ) != null ) 
	                  lStrEmpId = ( String ) session.getAttribute ( "UserID" ) ;  
	              
	         if ( request.getParameter ( "flag") != null )
	              lStrFlag = request.getParameter( "flag" ) ;
	          
	          if(request.getParameter("crId")!=null && !request.getParameter("crId").equals("")) 
	              lStrCrId=request.getParameter("crId");
	          if(ceoApprovalsForm.getCrId()!=null && !ceoApprovalsForm.getCrId().equals(""))
	              lStrCrId=ceoApprovalsForm.getCrId();
	          
	          if(lStrFlag!=null && lStrFlag.equalsIgnoreCase("pendingApprvls"))
	          {
	        	  int  totalRecords=1,pageNo=1,showPage=1,rowsPerPage=10,pages=1;
	    			int startIndex=1;
	    			int endIndex=10;
	    			int startPage=1;
	    			int endPage=10;
	        	  List<SQLChangeMgmtTransVO> myCrRqstList =ceoWorklistService.getCeoWorkList(lStrUserState);
	        	  ceoApprovalsForm.setMyCrRqstAprvlList(myCrRqstList);
	        	  
	        	  if(myCrRqstList!=null)
	        	  {
						request.setAttribute("crIdList", myCrRqstList.size());
	        	  }
					else
					{
						request.setAttribute("crIdList", "0");
					}
	        	  if(ceoApprovalsForm.getMyCrRqstAprvlList()!=null &&ceoApprovalsForm.getMyCrRqstAprvlList().size()>0){
						 totalRecords=ceoApprovalsForm.getMyCrRqstAprvlList().size();
	        	  }
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
								ceoApprovalsForm.setNext("next");
								
							}
				              if(pages>10 && (showPage-10)>1){
								
								request.setAttribute("prev", "prev");
								ceoApprovalsForm.setPrev("prev");
								
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
						
						
			           int x=ceoApprovalsForm.getStartPage();
						
						int y=ceoApprovalsForm.getEndPage();
						
						if(y>pages){
						y=pages;
						}
						
						if(pages>10 && pages>y){
							
							
							request.setAttribute("next", "next");
							ceoApprovalsForm.setNext("next");
							
						}
						
						if(pages>10 && x>10){
							request.setAttribute("prev", "prev");
							ceoApprovalsForm.setPrev("prev");
						}
						
						
						startPage=x;
						endPage=y;
						
						
					}
					
					if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
						int x=ceoApprovalsForm.getStartPage();
						
						int y=ceoApprovalsForm.getEndPage();
						if(x>=10 && pages>10){
							
							request.setAttribute("next", "next");
							ceoApprovalsForm.setNext("next");
							
						}
						
						if(x-10>10 && pages>10){
							
							request.setAttribute("prev", "prev");
							ceoApprovalsForm.setPrev("prev");
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
						
						
						ceoApprovalsForm.setStartPage(startPage);
						ceoApprovalsForm.setEndPage(endPage);
						
			startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
					}
					
					
					if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
		                int x=ceoApprovalsForm.getStartPage();
						
						int y=ceoApprovalsForm.getEndPage();
					
						
						startPage=y+1;
						endPage=y+10;
						if(endPage>pages){
							
							endPage=pages;
						}
						
						if(pages>startPage+10){
							request.setAttribute("next", "next");
							ceoApprovalsForm.setNext("next");
							
						}
						
						if(startPage-10>=1){
							
							request.setAttribute("prev", "prev");
							ceoApprovalsForm.setPrev("prev");
						}
						ceoApprovalsForm.setStartPage(startPage);
						ceoApprovalsForm.setEndPage(endPage);
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
							
							List<SQLChangeMgmtTransVO> listVo=new ArrayList<SQLChangeMgmtTransVO>();
		   					
		   				    for(int j=startIndex;j<endIndex;j++){
		   						
		   				    	SQLChangeMgmtTransVO vo=new SQLChangeMgmtTransVO();
		                       	
		                       	vo.setCR_ID(ceoApprovalsForm.getMyCrRqstAprvlList().get(j).getCR_ID());
		                       	vo.setCR_DESC(ceoApprovalsForm.getMyCrRqstAprvlList().get(j).getCR_DESC());
		                       	vo.setCR_TITLE(ceoApprovalsForm.getMyCrRqstAprvlList().get(j).getCR_TITLE());
		                       	vo.setCR_RAISED_DEPT(ceoApprovalsForm.getMyCrRqstAprvlList().get(j).getCR_RAISED_DEPT());
		                        vo.setEMP_FULL_NAME(ceoApprovalsForm.getMyCrRqstAprvlList().get(j).getEMP_FULL_NAME());                       	
		                       	listVo.add(vo);

		   				}
		   				    
		   				    if(listVo!=null && listVo.size()>0){    	
		   				    	ceoApprovalsForm.setMyCrRqstAprvlList(listVo);
		   				    	 				    	
		   				    }
		   				
		   				    request.setAttribute("liPageNo",showPage);
		   					if(ceoApprovalsForm.getMyCrRqstAprvlList().size()==0){
		   						request.setAttribute("startIndex", 0);
		   					}else{
		   						
		   						request.setAttribute("startIndex", startIndex+1);
		   					}
		   					if(ceoApprovalsForm.getMyCrRqstAprvlList().size()==0){
		   						request.setAttribute("endIndex", 0);
		   					}else{
		   						request.setAttribute("endIndex", endIndex);
		   						
		   					}
		   					
		   					request.setAttribute("rowsPerPage", rowsPerPage);
		   					request.setAttribute("pages", pages);
		   					request.setAttribute("startPage", startPage);
		   					request.setAttribute("endPage", endPage);
		   					request.setAttribute("endPage", endPage);
		   					if(ceoApprovalsForm.getMyCrRqstAprvlList().size()==0){
		   						
		   						request.setAttribute("totalRecords", 0);
		   					}else{
		   						request.setAttribute("totalRecords", totalRecords);
		   					}
					 lStrResultPage="pendingCrApprvls";
					 
				 }
	        else if(lStrFlag!=null && lStrFlag.equalsIgnoreCase("requestApprove"))
	          {
	        	 String lStrBtntype = (String)request.getParameter("btnType");
				 String crIdList = (String)request.getParameter("crIdList"); 
				 boolean lStrResult = ceoWorklistService.getNextUnitAndSave(lStrBtntype,crIdList,lStrCrId,lStrUser,lStrUserState);
				 request.setAttribute("msg", lStrBtntype); 
				 lStrResultPage="pendingCrApprvls";
			}
	        else if(lStrFlag!=null && lStrFlag.equalsIgnoreCase("CRView")) 
	        {
	        	
	        	String crId = request.getParameter("crId");
	        	 request.setAttribute("crId",crId);  
	        	  ChangeMgmtVO crViewDetails =ceoWorklistService.getCrRequestDetails(crId);
	              if(crViewDetails!=null)
	                {
	                    //Need to show view attach and give browse option again to attach
	                    ceoApprovalsForm.setCrAppName(crViewDetails.getCrAppName());    
	                    ceoApprovalsForm.setCrDesc(crViewDetails.getCrDesc());
	                    ceoApprovalsForm.setCrOrgName(crViewDetails.getCrOrgName());
	                    ceoApprovalsForm.setCrTitle(crViewDetails.getCrTitle());
	                    ceoApprovalsForm.setCrType(crViewDetails.getCrType());
	                    ceoApprovalsForm.setCrId(crViewDetails.getCrId());
	                    ceoApprovalsForm.setCrReqTypeId(crViewDetails.getCrReqTypeId());
	                    ceoApprovalsForm.setCrOrgFullName(crViewDetails.getCrOrgFullName());
	                    ceoApprovalsForm.setCrWorkFlowId(crViewDetails.getCrWorkflowId());
	                    ceoApprovalsForm.setStatus(crViewDetails.getStatus());
	                    ceoApprovalsForm.setCrOrgFullName(crViewDetails.getCrOrgFullName());
	                    ceoApprovalsForm.setSourceDeptName(crViewDetails.getSourceDeptName());
	                } 
	              if(crViewDetails.getCrPriorityId()!=null && !crViewDetails.getCrPriorityId().equals(""))
                      ceoApprovalsForm.setCrPriorityId(crViewDetails.getCrPriorityId());
                  if(crViewDetails.getCrRequiredDate()!=null && !crViewDetails.getCrRequiredDate().equals(""))    
                      ceoApprovalsForm.setCrRequiredDate(sdf.format(crViewDetails.getCrRequiredDate()));
                  List<LabelBean> crPriority=new ArrayList<LabelBean>();
                  crPriority.add(new LabelBean("Low","Low"));
                  crPriority.add(new LabelBean("Medium","Medium"));
                  crPriority.add(new LabelBean("High","High"));
                  request.setAttribute("crPriority",crPriority);
               
	              ArrayList actionWiseRemarksList=ceoWorklistService.getActionWiseAllRemarks(request.getParameter("crId"),lStrEmpId);
	                 if(actionWiseRemarksList!=null && !actionWiseRemarksList.isEmpty())
	                 {
	                      if(actionWiseRemarksList.size()>=2)
	                      {
	                          if(actionWiseRemarksList.get(0)!=null)
	                        	  ceoApprovalsForm.setOtherActionRemarkList((List<SQLChangeMgmtTransVO>)actionWiseRemarksList.get(0));
	                          if (actionWiseRemarksList.get(1)!=null) 
	                        	  ceoApprovalsForm.setSignOffActionRemarkList((List<SQLChangeMgmtTransVO>)actionWiseRemarksList.get(1));
	                      }
	                      else 
	                      {
	                          if(actionWiseRemarksList.get(0)!=null)
	                        	  ceoApprovalsForm.setOtherActionRemarkList((List<SQLChangeMgmtTransVO>)actionWiseRemarksList.get(0));
	                      }
	                      
	                 } 
	       
	              if(request.getParameter("addAttachFlag")!=null && request.getParameter("addAttachFlag").equalsIgnoreCase("N"))
	            	  ceoApprovalsForm.setAddAttachFlag(request.getParameter("addAttachFlag"));
	              List<ChangeMgmtVO> attachVOList=null;
	              if(request.getParameter("crIdForView")!=null && !request.getParameter("crIdForView").equals(""))
	              {
	            	  ceoApprovalsForm.setCrId(request.getParameter("crIdForView"));
	                   attachVOList = ceoWorklistService.getAllAttachments(request.getParameter("crIdForView")) ;
	              }
	              else 
	              {   if(ceoApprovalsForm.getCrId()!=null)
	                     attachVOList = ceoWorklistService.getAllAttachments(ceoApprovalsForm.getCrId()) ;
	              }
	              if(attachVOList!=null && !attachVOList.isEmpty())
	              {
	            	  ceoApprovalsForm.setAttachVOList(attachVOList);
	              }
	              request.setAttribute("attachVOList", attachVOList);
        	      if(attachVOList!=null && attachVOList.size()>0)
        	       {
        	    	 request.setAttribute("showFiles","Y");
        	       }
        	      else
        	      {
        	    	  request.setAttribute("showFiles","N");  
        	      }
        	      
        	     
        	  	 lStrResultPage="formViewDetails";
	        }
	          
	 
	          if ( lStrFlag != null && lStrFlag.equalsIgnoreCase ( "viewAttachment" ) )
	          {
	            File file = null ;
	            FileInputStream fis = null ;
	            DataInputStream dis = null ;
	            String lStrFileName = "" ;
	            String lStrFileType = "" ;
	            String lStrContentType = "" ;
	            ServletOutputStream out = response.getOutputStream();
	              SgvaCRMgmtAttach SgvaCRMgmtAttach=null;
	            if(request.getParameter("crIdView")!=null && !request.getParameter("crIdView").equals("") && 
	                 request.getParameter("lineItemNo")!=null && !request.getParameter("lineItemNo").equals(""))
	            {
	               SgvaCRMgmtAttach = ceoWorklistService.getAttachment(request.getParameter("crIdView"),request.getParameter("lineItemNo")) ;
	            }
	            if ( SgvaCRMgmtAttach != null )
	            {
	              lStrFileName = SgvaCRMgmtAttach.getAttachFileName() ;
	              int pos=SgvaCRMgmtAttach.getAttachFileName().lastIndexOf(".")+1;
	              lStrFileType = SgvaCRMgmtAttach.getAttachFileName().substring(pos);
	              lStrContentType= SgvaCRMgmtAttach.getAttachFileContentType();
	              byte[] lbBytes = null ;
	              String attachMode="attachment";
	 	          if(lStrFileType.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPG) || lStrFileType.equalsIgnoreCase

	 (ASRIConstants.FILE_EXT_JPEG) || lStrFileType.equalsIgnoreCase(ASRIConstants.FILE_EXT_GIF)){
	 	              attachMode="inline";
	 	          }
	 	         lStrContentType=ASRIConstants.FILE_EXT.get(lStrFileType);
	 	          
	 	          /**
	 	           * 
	 	           */
	 	      
	 	         
	 	          try
	 	          {
	 	            if ( lStrFileName != null && !lStrFileName.equals ( "" ) )
	 	            {
	 	              file = new File ( SgvaCRMgmtAttach.getAttachFilePath() ) ; 
	 	              fis = new FileInputStream ( file ) ;
	 	              dis = new DataInputStream ( fis ) ;
	 	              lbBytes = new byte[ dis.available () ] ;
	 	              fis.read ( lbBytes ) ;
	 	              request.setAttribute ( "File", lbBytes ) ;
	 	              request.setAttribute ( "ContentType", lStrContentType ) ;
	 	              request.setAttribute ( "FileName", lStrFileName ) ;
	 	              request.setAttribute ( "Extn", lStrFileType ) ;
	 	              response.setContentType ( lStrContentType ) ;
	 	              response.setHeader("Content-Disposition",""+attachMode+"; filename="+lStrFileName);//006
	 	              out.write(lbBytes);
	 	            }
	 	            else
	 	            {
	 	              lbBytes = new byte[ 0 ] ;
	 	            }
	 	          }
	 	          finally
	 	          {
	 	        	  out.close();
	 	          }
	            }
	            return mapping.findForward(null) ;  
	          }
	     
	          }  
	          catch(Exception e) 
	          {
	             e.printStackTrace();
	             //glogger.error("Exception occured at the time of CeoWorklistAction");
	         }
	          
	          return mapping.findForward(lStrResultPage);  
	          }
}

	      