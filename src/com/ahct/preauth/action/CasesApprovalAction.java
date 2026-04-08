package com.ahct.preauth.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
//import java.util.Map;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.preauth.service.CasesApprovalService;
import com.ahct.caseSearch.form.CasesSearchForm;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.service.FlaggingService;
import com.ahct.flagging.vo.FlaggingVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class CasesApprovalAction  extends Action{
	static final Logger gLogger = Logger.getLogger(CasesApprovalAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws IOException, FileNotFoundException, ParseException, Exception
	{
		response.setHeader("Pragma", "");
		response.setHeader("Cache-Control", "private");
		response.setDateHeader("Expires", Long.MAX_VALUE);
		HttpSession session=request.getSession(false);		      
		CasesApprovalService casesApprovalService = ( CasesApprovalService ) ServiceFinder.getContext ( request ).getBean ( "casesApprovalService" ) ;
		FlaggingService flaggingService=(FlaggingService) ServiceFinder.getContext(request).getBean("flaggingService");
		CasesSearchForm casesSearchForm = (CasesSearchForm)form;
		String lStrActionVal = request.getParameter ( "actionFlag" ) ;     
		String lStrResultPage=null;        
		String lStrUserRole="";        
		String lStrUserId="";
		String lStrLangID="";
		HashMap<String,String> lparamMap=new HashMap<String,String>();  
		String userName="";
		if(session.getAttribute("userName")!=null && !"".equalsIgnoreCase((String)session.getAttribute("userName")))
			userName=session.getAttribute("userName").toString();

		String caseId =null;
		if(request.getParameter ( "CaseId" ) !=null)
		{
			caseId=request.getParameter ( "CaseId" ) ;
		}
		request.setAttribute ( "CaseId", caseId ) ;

		try
		{
			if(session.getAttribute("UserID") != null)
			{
				lStrUserId=(String)session.getAttribute("UserID");
				lparamMap.put("UserID",lStrUserId);
			}        
			if(session.getAttribute("UserRole") != null)
			{
				lStrUserRole=(String)session.getAttribute("UserRole");
				lparamMap.put("UserRole",lStrUserRole);
			}
			if(session.getAttribute("LangID") != null)
			{
				lStrLangID = session.getAttribute("LangID").toString();
			} 
			if(session.getAttribute("UserRole") != null)
			{
				lStrUserRole=(String)session.getAttribute("UserRole");
				lparamMap.put("UserRole",lStrUserRole);
			}
			request.setAttribute("langID", lStrLangID);
			/*
			 * To load preauth frame display page
			 */
			if ( "getCaseDtls".equalsIgnoreCase ( lStrActionVal ) )
			{
				String casesForApprovalFlag = request.getParameter("casesForApproval");
				int dop=0,nonDop=0;
				String caseStatus=null,csSurgDt=null,clinicalTab="show";
				request.setAttribute("casesForApprovalFlag", casesForApprovalFlag);
				String errSearchType = request.getParameter("errSearchType");
				request.setAttribute("errSearchType", errSearchType);
				String errDentalSearchType = request.getParameter("errDentalSearchType");
				request.setAttribute("errDentalSearchType", errDentalSearchType);
				String disSearchType = request.getParameter("disSearchType");
				request.setAttribute("disSearchType", disSearchType);	        	 
				String lStrIpOpType = request.getParameter("ipOpType");
				request.setAttribute("ipOpType", lStrIpOpType);
				String module = request.getParameter("module");
				request.setAttribute("module", module);
				String ceoFlag=request.getParameter("ceoFlag");
				request.setAttribute("ceoFlag", ceoFlag);
				 String fromPage = request.getParameter("redirectedFrom");
	        	 request.setAttribute("fromPage", fromPage);
				String onlineCaseSheetFlag= casesApprovalService.getOnlinecaseSheetFlag(caseId);
				request.setAttribute("onlineCaseSheetFlag", onlineCaseSheetFlag);
				List<LabelBean> rolesList=null;
				if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
			    	rolesList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
				FlaggingVO fVO=new FlaggingVO();
				fVO=flaggingService.checkAuthority(rolesList);
				if(fVO!=null)
				{
					String authority=fVO.getAuthority();
						if("Y".equalsIgnoreCase(authority))
							{
								FlaggingVO fVO1=new FlaggingVO();
								fVO1=flaggingService.getFlaggedCasesForColor(caseId);
								if(fVO1!=null)
									{
										request.setAttribute("flagColour",fVO1.getFlagColour());
										System.out.println(fVO1.getFlagColour());
									}
							}
				}
				fVO=new FlaggingVO();
				fVO=flaggingService.checkCaseFlagged(caseId);
				if(fVO!=null)
				{
					if(fVO.getFlagged()!=null)
						request.setAttribute("flagged",fVO.getFlagged());
					else
						request.setAttribute("flagged",null);
				}
				
				List<CasesSearchVO> list=casesApprovalService.getDopDtls(caseId);
				if(list!=null)
					{
						if(list.size()>0)
							{
								for(CasesSearchVO casesSearchVODOP:list)
									{
										if(casesSearchVODOP.getProcess()!=null)
											{
												if(casesSearchVODOP.getProcess().equalsIgnoreCase("DOP"))
													{
														dop=dop+1;
													}
												else
													{
														if(casesSearchVODOP.getActiveyn()!=null)
														{
															if(casesSearchVODOP.getActiveyn().equalsIgnoreCase("Y"))
																nonDop=nonDop+1;
														}
													}
											}
									}
								if(list.get(0).getCaseStatus()!=null)
									caseStatus=list.get(0).getCaseStatus();
								if(list.get(0).getCsSurgDt()!=null)
									csSurgDt=list.get(0).getCsSurgDt();
								//sai Krishna :#CR8566:setting the flg to hide the non dialysis.
								if(list.get(0).getProcCode()!=null){
									request.setAttribute("speciality",list.get(0).getProcCode());
								}
							}
					}
				/*if(nonDop==0 && dop>0)
					{
						clinicalTab="hide";
						if(csSurgDt!=null)
							{
								clinicalTab="show";
							}
						if(caseStatus!=null)
							{
								if((caseStatus.equalsIgnoreCase("CD8")||caseStatus.equalsIgnoreCase("CD207")))
									clinicalTab="show";
							}
					}
				else if(nonDop>0)
					{
						clinicalTab="show";
					}
				
				request.setAttribute("clinicalTab", clinicalTab);*/
				lStrResultPage="PreauthCaseDisplayFrame";
			} 
			/*if (lStrActionVal!= null && "getPastHistory".equalsIgnoreCase ( lStrActionVal ) )
	         {
	        	 caseId= request.getParameter("caseId");
	        	 String rationCardNo=request.getParameter("cardNo");
	        	 //PastHistoryVO pastHistoryVO = new PastHistoryVO();
	        	 List<PastHistoryVO> cardUtilList = casesApprovalService.getpastHistory(rationCardNo);
	        	 casesApprovalActionForm.setCardUtilList(cardUtilList);
			 *//**
			 * get hospital history
			 *//*
	        	 PastHistoryVO hospHis = casesApprovalService.getHospHis("HS10");
	        	 List<LabelBean> lstSpecialities = casesApprovalService.getSpecialitiesOfHosp("HS10");
	        	 casesApprovalActionForm.setNwhname(hospHis.getNwhname());
	        	 casesApprovalActionForm.setAddress(hospHis.getAddress());
	        	 casesApprovalActionForm.setContactNo(hospHis.getContactNo());
	        	 casesApprovalActionForm.setHospBedCap(hospHis.getHospBedCap());
	        	 casesApprovalActionForm.setCurrBedOccu(hospHis.getCurrBedOccu());
	        	 request.setAttribute("lstSpecialities", lstSpecialities);

	        	 lStrResultPage="pasthistory"; 
	         }*/

			if(lStrActionVal!= null && "getPastHistory".equalsIgnoreCase(lStrActionVal))
			{
				CasesSearchVO casesSearchVO = new CasesSearchVO();
				casesSearchVO.setCaseId(request.getParameter("caseId"));
				if(request.getParameter("employeeNo")!=null && !"".equalsIgnoreCase(request.getParameter("employeeNo")))
					casesSearchVO.setLoginName(request.getParameter("employeeNo"));
				else
					casesSearchVO.setLoginName(userName);
				request.setAttribute("fromPastHistory", request.getParameter("fromPastHistory"));
				request.setAttribute("caseId", request.getParameter("caseId"));
				//request.setAttribute("colorCaseId", request.getParameter("caseId"));
				if(request.getParameter("caseId") != null && !"".equalsIgnoreCase(request.getParameter("caseId")))
				{
					casesSearchForm.setCaseId(request.getParameter("caseId"));
					request.setAttribute("colorCaseId", request.getParameter("caseId"));
				}
				casesSearchVO.setCaseForDissFlg(request.getParameter("fromPastHistory"));
				request.setAttribute("employeeNo", request.getParameter("employeeNo"));
				List<CasesSearchVO> casesList = null;
				
				List<CasesSearchVO> cardType = casesApprovalService.getCardType(casesSearchVO.getLoginName(),casesSearchVO.getCaseId());
				if(cardType!=null && cardType.size()>0 && "P".equalsIgnoreCase(cardType.get(0).getCARDTYPE()))
				{
					casesSearchVO.setLoginName(cardType.get(0).getEMPLOYEENO());
					List<CasesSearchVO> aadharList=casesApprovalService.getAadharDtlsForPen(cardType.get(0).getCARDNO());
					List<CasesSearchVO> aadharEidList=casesApprovalService.getAadharEidDtlsForPen(cardType.get(0).getCARDNO());
					if(aadharList.size()>0 && aadharList!=null)
					{
						if(aadharList.get(0).getAADHARID()!=null && !"".equalsIgnoreCase(aadharList.get(0).getAADHARID()))
						{
						
							if(aadharList.get(0).getCOUNT()!=null && "2".equalsIgnoreCase(aadharList.get(0).getCOUNT().toString()))
							{
								casesSearchVO.setAADHARID(aadharList.get(0).getAADHARID());
								
								List<CasesSearchVO> cardNoList = casesApprovalService.getCardNoList(casesSearchVO.getAADHARID());
								
								if(cardNoList.size()>0){
									casesSearchVO.setCardNo1(cardNoList.get(0).getEMPNO());
									casesSearchVO.setCardNo2(cardNoList.get(1).getEMPNO());
									
									casesList=casesApprovalService.getAllCaseSearchDetailsForPen(casesSearchVO);
								}
							}
							else{
								casesList=casesApprovalService.getAllCaseSearchDetails(casesSearchVO);
							}
						}
					}
					else if(aadharEidList.size()>0 && aadharEidList!=null)
					{
						if(aadharEidList.get(0).getAADHAREID()!=null && !"".equalsIgnoreCase(aadharEidList.get(0).getAADHAREID()))
						{
								if(aadharEidList.get(0).getCOUNT()!=null && "2".equalsIgnoreCase(aadharEidList.get(0).getCOUNT().toString()))
								{
									casesSearchVO.setAADHAREID(aadharEidList.get(0).getAADHAREID());
									
									List<CasesSearchVO> cardNoList = casesApprovalService.getCardNoList(casesSearchVO.getAADHAREID());
									
									if(cardNoList.size()>0){
										casesSearchVO.setCardNo1(cardNoList.get(0).getEMPNO());
										casesSearchVO.setCardNo2(cardNoList.get(1).getEMPNO());
										
										casesList=casesApprovalService.getAllCaseSearchDetailsForPen(casesSearchVO);
									}
								}
								else{
									casesList=casesApprovalService.getAllCaseSearchDetails(casesSearchVO);
								}
							
						}
						
					}
					else
					{
						casesList=casesApprovalService.getAllCaseSearchDetails(casesSearchVO);
					}
				}
				
				else
				 casesList=casesApprovalService.getAllCaseSearchDetails(casesSearchVO);
				if(casesList.size()==0)
				{
					casesSearchForm.setFlag("false");
				}
				else
				{
					casesSearchForm.setFlag("true");
					casesSearchForm.setLstCaseSearch(casesList);
				}
				if (casesList!= null && casesList.size() > 9) {
					casesSearchForm.setLstCaseSearch(casesList.subList(0, 10));
					casesSearchForm.setCurPage("10");
					casesSearchForm.setPageStats("1-10 of " + casesList.size());
					casesSearchForm.setPaginFlag("prev");
				} else if(casesList!= null && casesList.size() > 0){
					casesSearchForm.setLstCaseSearch(casesList);
					casesSearchForm.setCurPage(casesList.size() + "");
					if (casesList .size() > 0){
						casesSearchForm.setPageStats("1-" + casesList.size() + " of "
								+ casesList.size());
					}else{
						casesSearchForm.setPageStats("No records");
					}casesSearchForm.setPaginFlag("false");
				}
				casesSearchForm.setFromType("basic");
				request.setAttribute("listsize", casesList.size());




				return mapping.findForward("empPenCaseSearch");
			}

			if (lStrActionVal != null && "searchFormPagin".equalsIgnoreCase(lStrActionVal)) {

				CasesSearchVO casesSearchVO = new CasesSearchVO();
				casesSearchVO.setCaseId(request.getParameter("caseId"));
				String lStrPaginStats = (String) request.getParameter("paginStatus");
				List<CasesSearchVO> casesList=null;
				if(request.getParameter("employeeNo")!=null && !"".equalsIgnoreCase(request.getParameter("employeeNo")))
					casesSearchVO.setLoginName(request.getParameter("employeeNo"));
				else
					casesSearchVO.setLoginName(userName);
				request.setAttribute("fromPastHistory", request.getParameter("fromPastHistory"));
				request.setAttribute("employeeNo", request.getParameter("employeeNo"));
				request.setAttribute("caseId", request.getParameter("caseId"));

				casesSearchVO.setCaseForDissFlg(request.getParameter("fromPastHistory"));
				casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
				casesSearchVO.setCaseStatus(casesSearchForm.getCaseStatus());
				casesSearchVO.setFromDate(casesSearchForm.getFromDate());
				casesSearchVO.setToDate(casesSearchForm.getToDate());
				//request.setAttribute("colorCaseId", request.getParameter("caseId"));
				if(request.getParameter("caseId") != null && !"".equalsIgnoreCase(request.getParameter("caseId")))
				{
					casesSearchForm.setCaseId(request.getParameter("caseId"));
					request.setAttribute("colorCaseId", request.getParameter("caseId"));
				}
				if(casesSearchForm.getFromType()!=null && casesSearchForm.getFromType().equalsIgnoreCase("basic"))
					casesList=casesApprovalService.getAllCaseSearchDetails(casesSearchVO);	
				if(casesSearchForm.getFromType()!=null && casesSearchForm.getFromType().equalsIgnoreCase("criteria"))
					casesList=casesApprovalService.getCaseSearchDetails(casesSearchVO);	
				int lStrCurPage = 0;
				if (casesSearchForm.getCurPage() != null && !"".equalsIgnoreCase(casesSearchForm.getCurPage().trim()))
				{
					lStrCurPage = Integer.parseInt(casesSearchForm.getCurPage());
				}
				casesSearchForm.setLstCaseSearch(casesList);

				if (lStrPaginStats != null && "next".equalsIgnoreCase(lStrPaginStats))
				{

					if (casesList != null)
					{
						if (casesList.size() >= lStrCurPage) 
						{
							if((lStrCurPage + 10)>casesList.size())
							{
								casesSearchForm.setLstCaseSearch(casesList.subList(
										lStrCurPage, casesList.size()));
								casesSearchForm.setPageStats((lStrCurPage + 1) + "-"
										+ (casesList.size()) + " of "
										+ casesList.size());
								casesSearchForm.setCurPage((casesList.size()) + "");
							}
							else
							{
								casesSearchForm.setLstCaseSearch(casesList.subList(
										lStrCurPage, lStrCurPage + 10));
								casesSearchForm.setPageStats((lStrCurPage + 1) + "-"
										+ (lStrCurPage + 10) + " of "
										+ casesList.size());
								casesSearchForm.setCurPage((lStrCurPage + 10) + "");
							}
						}
					} 
					/*else
					{
						casesSearchForm.setLstCaseSearch(casesList.subList(
								lStrCurPage, casesList.size()));
						casesSearchForm.setPageStats(lStrCurPage + 1 + "-"
								+ casesList.size() + " of "
								+ casesList.size());
						casesSearchForm.setCurPage(casesList.size() + "");
					}*/
				}
				if (lStrPaginStats != null && "prev".equalsIgnoreCase(lStrPaginStats)) {

					if(casesList.size()>0 && casesList.size()>=lStrCurPage)
					{

						if(casesList.size()==lStrCurPage)
						{
							if(lStrCurPage%10==0)
							{ 
								lStrCurPage=lStrCurPage-10;
								casesSearchForm.setCurPage((lStrCurPage) + "");

								casesSearchForm.setLstCaseSearch(casesList.subList(lStrCurPage-10, lStrCurPage));
								casesSearchForm.setPageStats((lStrCurPage-9) + "-"
										+ (lStrCurPage) + " of "
										+ casesList.size());
							}
							else
							{
								lStrCurPage=lStrCurPage-lStrCurPage%10;
								casesSearchForm.setCurPage((lStrCurPage) + "");
								casesSearchForm
								.setLstCaseSearch(casesList.subList(lStrCurPage-10, lStrCurPage));
								casesSearchForm.setPageStats((lStrCurPage-9) + "-"
										+ (lStrCurPage) + " of "
										+ casesList.size());

							}
						}
						else 
						{
							lStrCurPage=lStrCurPage-10;
							casesSearchForm.setCurPage((lStrCurPage) + "");

							casesSearchForm.setLstCaseSearch(casesList.subList(lStrCurPage-10, lStrCurPage));
							casesSearchForm.setPageStats((lStrCurPage-9) + "-"
									+ (lStrCurPage) + " of "
									+ casesList.size());
						}
					}



				}
				request.setAttribute("listsize", casesList.size());

				return mapping.findForward("empPenCaseSearch");

			}
			if(lStrActionVal!= null && "caseSearchCriteria".equalsIgnoreCase(lStrActionVal))
			{
				CasesSearchVO casesSearchVO = new CasesSearchVO();
				if(request.getParameter("employeeNo")!=null && !"".equalsIgnoreCase(request.getParameter("employeeNo")))
					casesSearchVO.setLoginName(request.getParameter("employeeNo"));
				else
					casesSearchVO.setLoginName(userName);
				request.setAttribute("fromPastHistory", request.getParameter("fromPastHistory"));
				request.setAttribute("employeeNo", request.getParameter("employeeNo"));
				request.setAttribute("caseId", request.getParameter("caseId"));
				casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
				casesSearchVO.setCaseStatus(casesSearchForm.getCaseStatus());
				casesSearchVO.setFromDate(casesSearchForm.getFromDate());
				casesSearchVO.setToDate(casesSearchForm.getToDate());
			
				casesSearchForm.setCaseNo(casesSearchForm.getCaseNo());
				casesSearchForm.setCaseStatus(casesSearchForm.getCaseStatus());
				casesSearchForm.setFromDate(casesSearchForm.getFromDate());
				casesSearchForm.setToDate(casesSearchForm.getToDate());
				casesSearchVO.setCaseForDissFlg(request.getParameter("fromPastHistory"));
				List<CasesSearchVO> casesList=casesApprovalService.getCaseSearchDetails(casesSearchVO);
				casesSearchForm.setLstCaseSearch(casesList);
				if(casesList.size()==0)
				{
					casesSearchForm.setFlag("false");
				}
				else
					casesSearchForm.setFlag("true");
				if (casesList!= null && casesList.size() > 9) {
					casesSearchForm.setLstCaseSearch(casesList.subList(0, 10));
					casesSearchForm.setCurPage("10");
					casesSearchForm.setPageStats("1-10 of " + casesList.size());
					casesSearchForm.setPaginFlag("prev");
				} else {
					casesSearchForm.setLstCaseSearch(casesList);
					casesSearchForm.setCurPage(casesList.size() + "");
					if (casesList .size() > 0){
						casesSearchForm.setPageStats("1-" + casesList.size() + " of "
								+ casesList.size());
					}else{
						casesSearchForm.setPageStats("No records");
					}casesSearchForm.setPaginFlag("false");
				}
				
				request.setAttribute("listsize", casesList.size());
				request.setAttribute("fromType", "criteria");
				casesSearchForm.setFromType("criteria");
				return mapping.findForward("empPenCaseSearch");
				
				
			}

		}	
		catch(Exception e)
		{
			e.printStackTrace();	
		}		
		return mapping.findForward(lStrResultPage);
	}
}
