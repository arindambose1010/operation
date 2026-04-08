package com.ahct.cardSearch.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;















import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.cardSearch.form.cardSearchForm;
import com.ahct.cardSearch.service.cardSearchService;
import com.ahct.cardSearch.vo.cardSearchVO;
import com.ahct.common.vo.LabelBean;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class cardSearchAction extends Action
	{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				response.setHeader("Cache-Control","no-cache");
				response.setHeader("pragma","no-cache");
				response.setDateHeader("Expires", 0);    
				HttpSession session = request.getSession ( false ) ;
				
				ConfigurationService configurationService=(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		        Configuration config = configurationService.getConfiguration();
		        
		        cardSearchForm cardSearchForm = (cardSearchForm)form;
		        cardSearchService cardSearchService = (cardSearchService) ServiceFinder
						.getContext(request).getBean("cardSearchService");
		        
		        String lsrActionFlag= request.getParameter("actionFlag");
				String lstrRstPage= null;
				String lStrUserId = (String) session.getAttribute("UserID");
				
				if ( lsrActionFlag!=null && lsrActionFlag.equalsIgnoreCase ( "cardSearch" ) ) 
			 	{
					lstrRstPage="cardSearch";
			 	}
				List<String> photoList =new ArrayList<String>();
			 	if ( lsrActionFlag!=null && lsrActionFlag.equalsIgnoreCase ( "searchCardDetails" ) ) 
			 	{
			 		String empRadio = request.getParameter("empRadio");
			 		String cardNo = request.getParameter("cardNo");
			 		
			 		if(cardNo!=null)
			 		{
			 			String usrId=cardSearchService.getUsrId(cardNo);
						request.setAttribute("usrId",usrId);
						String empUsrId=cardSearchService.getEmpUsrId(cardNo);
						request.setAttribute("empUsrId",empUsrId);
			 		}
			 		
			 		List<cardSearchVO> cardList = cardSearchService.getCardStatusList(empRadio,cardNo);
		        	if(cardList.size()<0)
		        	{
		        		cardSearchForm.setSuccessFlag("NoRecords");
		        		request.setAttribute("size", 0);
		        	}
		        	else
		        	{
		        		cardSearchForm.setCardList(cardList);
		        	}
		        	if (cardList.size()>0) 
		        	{
		     			for(int i=0;i<cardList.size();i++) 
		     				{
		     				
		     				if(cardList.get(i).getPHOTO()!=null && !"".equalsIgnoreCase(cardList.get(i).getPHOTO()))
		                 	{
				            	try
				            	{
									// Reading a Image file from file system
				            		File photo= new File(cardList.get(i).getPHOTO());
			                 		if(photo.getPath()!=null)
			                 		{
			     					FileInputStream newPhotoFile = new FileInputStream(cardList.get(i).getPHOTO());
			     					
			     					DataInputStream dis1= new DataInputStream(newPhotoFile);
			     					byte imageDataNew[]=null;
			     					imageDataNew = new byte[dis1.available()];
			     					newPhotoFile.read(imageDataNew);
			      
			     					// Converting Image byte array into Base64 String
			     					byte[] encodedDataNew=org.apache.commons.codec.binary.Base64.encodeBase64(imageDataNew);
			     					String photos= new String(encodedDataNew,"UTF8");
			     					newPhotoFile.close();
			     					
									request.setAttribute("photo"+i, photos);
			     					//photoList.add(photos);
			     					//request.setAttribute("photoList"+i, photoList);
			     					System.out.println("photo"+i);
			     					}
				            	}
			     				catch(Exception e){
			     					e.printStackTrace();
			     				}
		                 	} 
		     				}
		        	}
		        	lstrRstPage="cardSearch";
			 	}
			 	//Print card for journalist
			 	 if (lsrActionFlag != null && lsrActionFlag.equalsIgnoreCase("printCard")) {
						
						String lStrEnrolId=request.getParameter("enrolId");
						String lStrSeqNo=request.getParameter("seqNo");
						String empRadio=request.getParameter("empRadio");

						String usrId=null;
						if(request.getParameter("usrId")!=null)
						 	{
								usrId=request.getParameter("usrId");
								request.setAttribute("usrId",usrId);
						 	}
						
						LabelBean lCardIssueVo=new LabelBean();
						if(usrId!=null)
							 lCardIssueVo=cardSearchService.getCardDetails(usrId,lStrEnrolId,lStrSeqNo,empRadio);
						else
							 lCardIssueVo=cardSearchService.getCardDetails(lStrUserId,lStrEnrolId,lStrSeqNo,empRadio);
						
						if(lCardIssueVo!=null){
						request.setAttribute("empNo",lCardIssueVo.getEMPID());
						request.setAttribute("benificiaryName",lCardIssueVo.getEMPNAME());
						request.setAttribute("email",lCardIssueVo.getHOSPEMAIL());
						request.setAttribute("mobile",lCardIssueVo.getMOBILENO());
						request.setAttribute("AccNo",lCardIssueVo.getDisMainId());
						request.setAttribute("empName",lCardIssueVo.getNEWEMPCODE());
						request.setAttribute("cardNo",lCardIssueVo.getID());
						request.setAttribute("address",lCardIssueVo.getEMAILID());
						request.setAttribute("issuedBy",lCardIssueVo.getPARENT_UNITID());
						if(lCardIssueVo.getWFTYPE()!=null && "Y".equalsIgnoreCase(lCardIssueVo.getWFTYPE()))
							request.setAttribute("apEmployee", "Y");
						request.setAttribute("district","label.EHF.Card.Dist."+lCardIssueVo.getCUGNUM());
						request.setAttribute("empType",lCardIssueVo.getVALUE());
						request.setAttribute("relation","label.EHF.Card.Relation."+lCardIssueVo.getCOUNT());
						request.setAttribute("dateOfBirth",lCardIssueVo.getREMARKS());
						//request.setAttribute("photo",lCardIssueVo.getPATH());
						if(lCardIssueVo.getPATH()!=null && !"".equalsIgnoreCase(lCardIssueVo.getPATH()))
			        	{
			        	try{
							// Reading a Image file from file system
			        		File photo= new File(lCardIssueVo.getPATH());
			        		if(photo.exists()){
							FileInputStream newPhotoFile = new FileInputStream(lCardIssueVo.getPATH());
							
							DataInputStream dis1= new DataInputStream(newPhotoFile);
							byte imageDataNew[]=null;
							imageDataNew = new byte[dis1.available()];
							newPhotoFile.read(imageDataNew);

							// Converting Image byte array into Base64 String
							byte[] encodedDataNew=org.apache.commons.codec.binary.Base64.encodeBase64(imageDataNew);
							String newPhoto = new String(encodedDataNew,"UTF8");
							newPhotoFile.close();
							request.setAttribute("photo", newPhoto);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
			        	}
			        	
						request.setAttribute("gender",lCardIssueVo.getCONST());
						request.setAttribute("idName", lCardIssueVo.getFlagName());
						request.setAttribute("SUBDESC", lCardIssueVo.getSUBDESC());
						request.setAttribute("SUBCODE", lCardIssueVo.getSUBCODE());
						request.setAttribute("adhrExts", lCardIssueVo.getDISNAME());
						request.setAttribute("seqNo", lStrSeqNo);
						request.setAttribute("scheme", lCardIssueVo.getSCHEMENAME());
						request.setAttribute("enrolId", lStrEnrolId);
						request.setAttribute("idType", lCardIssueVo.getUNITTYPE());
						if(lCardIssueVo.getAADHARID()!=null && !lCardIssueVo.getAADHARID().equalsIgnoreCase("")){
							request.setAttribute("aadharId",lCardIssueVo.getAADHARID());
						}
						if(lCardIssueVo.getAADHAREID()!=null && !lCardIssueVo.getAADHAREID().equalsIgnoreCase("")){
							request.setAttribute("aadharEId",lCardIssueVo.getAADHAREID());
						}
						}
						request.setAttribute("lang_id", request.getParameter("langId"));
						return mapping.findForward("jrnlstCardPage");
					}
			 	 //Print card for employee
			 	if (lsrActionFlag != null && lsrActionFlag.equalsIgnoreCase("printCardEmployee")) {
					
					String lStrEnrolId=request.getParameter("enrolId");
					String lStrSeqNo=request.getParameter("seqNo");
					String empRadio=request.getParameter("empRadio");
					String empUsrId=request.getParameter("usrId");
					LabelBean lCardIssueVo=cardSearchService.getCardDetails(empUsrId,lStrEnrolId,lStrSeqNo,empRadio);
					
					if(lCardIssueVo!=null){
					request.setAttribute("empNo",lCardIssueVo.getEMPID());
					request.setAttribute("benificiaryName",lCardIssueVo.getEMPNAME());
					request.setAttribute("empName",lCardIssueVo.getNEWEMPCODE());
					request.setAttribute("cardNo",lCardIssueVo.getID());
					request.setAttribute("address",lCardIssueVo.getEMAILID());
					request.setAttribute("issuedBy",lCardIssueVo.getPARENT_UNITID());
					if(lCardIssueVo.getWFTYPE()!=null && "Y".equalsIgnoreCase(lCardIssueVo.getWFTYPE()))
						request.setAttribute("apEmployee", "Y");
					request.setAttribute("district","label.EHF.Card.Dist."+lCardIssueVo.getCUGNUM());
					request.setAttribute("empType",lCardIssueVo.getVALUE());
					request.setAttribute("relation","label.EHF.Card.Relation."+lCardIssueVo.getCOUNT());
					request.setAttribute("dateOfBirth",lCardIssueVo.getREMARKS());
					request.setAttribute("photo",lCardIssueVo.getPATH());
					request.setAttribute("gender",lCardIssueVo.getCONST());
					request.setAttribute("seqNo", lStrSeqNo);
					request.setAttribute("scheme", lCardIssueVo.getSCHEMENAME());
					request.setAttribute("enrolId", lStrEnrolId);
					request.setAttribute("idType", lCardIssueVo.getUNITTYPE());
					if(lCardIssueVo.getAADHARID()!=null && !lCardIssueVo.getAADHARID().equalsIgnoreCase("")){
						request.setAttribute("aadharId",lCardIssueVo.getAADHARID());
					}
					if(lCardIssueVo.getAADHAREID()!=null && !lCardIssueVo.getAADHAREID().equalsIgnoreCase("")){
						request.setAttribute("aadharEId",lCardIssueVo.getAADHAREID());
					}
			
					}
					
					request.setAttribute("lang_id", request.getParameter("langId"));
					return mapping.findForward("empCardPage");
					
				}
			 // to view photos
				if (lsrActionFlag != null && lsrActionFlag.equalsIgnoreCase("viewPhoto")) {
					String filePath = request.getParameter("fileName");

					File sourceFile = null;
					try {
						ServletOutputStream out = response.getOutputStream();
						String fileExt = filePath
								.substring((filePath.lastIndexOf(".") + 1));
						String fileName = filePath
								.substring((filePath.lastIndexOf("/") + 1));
						String attachMode = ASRIConstants.ATTACHMENT_MODE_ATTACH;

						sourceFile = new File(filePath);
						if (!sourceFile.exists()) {
							String lStrNoFileUrl = request.getSession(false)
									.getServletContext().getRealPath("/")
									+ "images/404.jpg";
							sourceFile = new File(lStrNoFileUrl);
							fileExt = lStrNoFileUrl.substring((lStrNoFileUrl
									.lastIndexOf(".") + 1));
							fileName = lStrNoFileUrl.substring((lStrNoFileUrl
									.lastIndexOf("/") + 1));

						}
						if (fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPG)
								|| fileExt
										.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPEG)
								|| fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_GIF)) {
							attachMode = ASRIConstants.ATTACHMENT_MODE_INLINE;
						}
						DataInputStream dis = new DataInputStream(new FileInputStream(
								sourceFile));
						
						byte[] bytes = new byte[dis.available()];

						dis.readFully(bytes);
						int size = bytes.length;
						dis.close();
						String contentType = ASRIConstants.FILE_EXT.get(fileExt);
						response.setContentType(contentType);
						response.setHeader("Content-Disposition", "" + attachMode
								+ "; filename=" + fileName); // 006
						out.write(bytes);
						out.close();

					} catch (Exception e) {
						e.printStackTrace();
					} finally {

					}
					return mapping.findForward(null);

				}
				return mapping.findForward(lstrRstPage);
			}
	}
