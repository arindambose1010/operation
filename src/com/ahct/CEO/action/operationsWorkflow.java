package com.ahct.CEO.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.caseSearch.action.CasesSearchAction;
import com.ahct.caseSearch.form.CasesSearchForm;
import com.ahct.caseSearch.service.CasesSearchService;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.caseSearch.vo.casesSearchRecordVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.service.FlaggingService;
import com.ahct.preauth.service.PreauthService;
import com.ahct.preauth.vo.PreauthVO;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;

public class operationsWorkflow extends Action{
	static final Logger gLogger = Logger.getLogger(CasesSearchAction.class);
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	    {
		 response.setHeader("Cache-Control","no-cache");
	        response.setHeader("pragma","no-cache");
	        response.setDateHeader("Expires", 0);    
	        HttpSession session = request.getSession ( false ) ;
	        String lStrResultPage = HtmlEncode.verifySession(request, response);
	        if (lStrResultPage.length() > 0)
	            return mapping.findForward("regSessionExpire");
	        ResourceBundle bundle = ResourceBundle.getBundle ( "SGVConstants" ) ;
	        CasesSearchService casesSearchService = (CasesSearchService)ServiceFinder.getContext(request).getBean("casesSearchService");
	        FlaggingService flaggingService=(FlaggingService) ServiceFinder.getContext(request).getBean("flaggingService");
	        PreauthService preauthService = (PreauthService)ServiceFinder.getContext(request).getBean("preauthService");
	        CasesSearchForm casesSearchForm = (CasesSearchForm)form;
           String lStrRowsperpage=bundle.getString("case.RowsPerPage");
           String lStrStartIndex=bundle.getString("case.StartIndex0");
           String showPage= bundle.getString("case.ShowPage");
           String   lStrEmpId = null;
          // String lStrLocId  = null;
           String lStrLangId = null;
          // String lStrUserName = null;
           String next=bundle.getString("case.next");
           String lStrFlagStatus = request.getParameter("actionFlag");
           String lStrUserGroup = null;String lStrUserState=null;
           if(session == null || session.getAttribute("EmpID")==null)
           {
                request.setAttribute("Message","Your session has expired. Login again");
                return mapping.findForward("sessionExpire");
             }
           if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
			  lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
			    /*if ( ( session.getAttribute ( "LocID" ) != null ) && !( session.getAttribute ( "LocID" ).equals ( "" ) ) )
			    	lStrLocId = ( String ) session.getAttribute ( "LocID" ) ;*/
			    if ( ( session.getAttribute ( "LangID" ) != null ) && !( session.getAttribute ( "LangID" ).equals ( "" ) ) )
			    	lStrLangId = ( String ) session.getAttribute ( "LangID" ) ;
			    /*if ( ( session.getAttribute ( "userName" ) != null ) && !( session.getAttribute ( "userName" ).equals ( "" ) ) )
			    	lStrUserName = ( String ) session.getAttribute ( "userName" ) ;*/
			    if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
			    	lStrUserState = ( String ) session.getAttribute ( "userState" ) ;
			    request.setAttribute("langID", lStrLangId);
			 List<LabelBean> groupsList = null;
				   if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
						    	groupsList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
				   ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		            Configuration config = configurationService.getConfiguration();
		            CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");      
	        
		            
	/*Added for CEO PREAUTH WORKFLOW*/ 		
		            
			        if(lStrFlagStatus!= null && "submitNextLvlCeo".equals(lStrFlagStatus)) 
			        {
			        	PreauthVO preauthVO=new PreauthVO();
			        	PreauthVO preauthVOCase=new PreauthVO();
			        	String buttonVal = request.getParameter("buttonVal");
				    	String viewType = request.getParameter("viewType");
				    	String[] casesSelected=casesSearchForm.getCasesSelected();
				    	casesSelected=casesSelected[0].split(",");
				    	int caseCount=0;
				    	int successCount=0;
				    	
				    	 String caseSearchType=casesSearchForm.getCaseSearchType();
				         String caseForApproval=casesSearchForm.getCaseForApproval();
				    	 String module=casesSearchForm.getModule();
				         String ceoFlag=casesSearchForm.getCeoFlag();
				         
				         request.setAttribute("caseSearchType",caseSearchType);
				         request.setAttribute("casesForApproval",caseForApproval);
				         request.setAttribute("module",module);
				         request.setAttribute("ceoFlag",ceoFlag);
				    	
				    	if(casesSelected!=null){
				    	for(String caseId:casesSelected)
				    	{
				    	
				    		
				    	if(caseId!=null && !("").equalsIgnoreCase(caseId))
				    	{
				    	preauthVOCase=preauthService.getCaseDetails(caseId);
				    	if(preauthVOCase!=null)
				    	{
				    		preauthVO.setCaseId(caseId);
				    		preauthVO.setCruUsr(lStrEmpId);
				    	preauthVO.setViewType(viewType);
				    	preauthVO.setRemarks("CEO APPROVED");
				    	preauthVO.setButtonVal(buttonVal);
				    	preauthVO.setCaseStatusId(preauthVOCase.getCaseStatusId());
				   // 	preauthVO.setUserRole(lStrUserRole);
				    	preauthVO.setUserRole(config.getString("Group.CEO"));
				    	preauthVO.setCochlearYN(preauthVOCase.getCochlearYN());
				    	preauthVO.setScheme(preauthVOCase.getScheme());
				    	
				    	//preauthDetailsForm.setGenRemarks(preauthDetailsForm.getGenRemarks());
				    	/**
				    	 * save and enhancement approve amount and pckg approval amount
				    	 */
				    	preauthVO.setEnhApprAmt(preauthVOCase.getEnhApprAmt());
				    	
				    	/**
				    	 * save preauth package amount
				    	 */
				    	preauthVO.setApprvdPckAmt(preauthVOCase.getApprvdPckAmt());
				    	preauthVO.setComorBidAppvAmt(preauthVOCase.getComorBidAppvAmt());
				    	preauthVO.setPtdTotalApprvAmt(preauthVOCase.getPtdTotalApprvAmt());
				    	preauthVO.setNabhAmt(preauthVOCase.getNabhAmt());
				    	caseCount++;
				    	}
				    	String msg = preauthService.sentVerifyPanel(preauthVO);
				    	
				    	if(msg!=null && !"".equalsIgnoreCase(msg)  && !"failure".equalsIgnoreCase(msg))
				    		{
				    		request.setAttribute("ResultMsg", msg);
				    			if(msg!=null)
				    				{
				    					if(!msg.equalsIgnoreCase(config.getString("preauth_msg_CD213")))
					    					{
				    						successCount++;	
					    					}   
				    				}
				    		}
				    	if(successCount==caseCount)
				    	{
				    		request.setAttribute("successFlag", "Y");
				    		
				    	}
				    	else if(successCount==0)
				    	{
				    		request.setAttribute("successFlag", "N");
				    	}
				    	else if(successCount<caseCount && successCount!=0 )
				    	{
				    		request.setAttribute("successFlag", "P");
				    		int diff=caseCount-successCount;
				    		request.setAttribute("diff",diff);
				    	}
				    	
				    	}
				    	
				    	}}
				    	lStrFlagStatus="OnloadCaseSearch";
			        }         
		            
		            
	 /*End of CEO PREAUTH WORKFLOW*/ 		            
		            
		            
		            
		    if(lStrFlagStatus!= null && "OnloadCaseSearch".equals(lStrFlagStatus)) 
	        
		    {
	        	String searchType= request.getParameter("caseSearchType");	        		        
	        	lStrStartIndex=bundle.getString("case.StartIndex0");
	        	/**
	        	 * set view flag on click of back button
	        	 */
	        	String caseId = request.getParameter("CaseId");	
	 	        String flag = request.getParameter("flag");
	 	       String module = request.getParameter("module");
	 	        String excelFlag = request.getParameter("excelFlag");
	 	        String csvFlag = request.getParameter("csvFlag");
	 	        String caseForDissFlg = request.getParameter("disSearchType");
	 	        String casesForApprovalFlag = request.getParameter("casesForApproval");
	 	       String errCaseApprovalFlag = request.getParameter("errSearchType");
	 	       String lStrSchemeType = request.getParameter("schemeType");
	 	       String patientScheme=request.getParameter("patientScheme");
	 	       String ceoFlag=request.getParameter("ceoFlag");
	 	       
	 	       if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
	 	    	  casesForApprovalFlag = casesSearchForm.getCasesForApprovalFlag();
	 	        if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
	 	        	casesForApprovalFlag="N";
	 	        if(errCaseApprovalFlag==null || errCaseApprovalFlag.equalsIgnoreCase(""))
	 	        	errCaseApprovalFlag="N";
	 	        if(caseForDissFlg==null || caseForDissFlg.equalsIgnoreCase(""))
	 	        	caseForDissFlg="N";
	 	        if(caseId != null && !caseId.equals(""))
	 	          casesSearchService.setviewFlag(caseId,flag,lStrEmpId);
	 	        if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase(""))
	 	        	lStrSchemeType = lStrUserState;
	 	        /**
	 	         * end 
	 	         */
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
	        	casesSearchVO.setCaseSearchType(searchType);
	        	casesSearchVO.setClaimStatus(casesSearchForm.getClaimId());
	        	casesSearchVO.setCatId(casesSearchForm.getCatId());
	        	casesSearchVO.setErrStatusId(casesSearchForm.getErrStatusId());
	        	casesSearchVO.setWapNo(casesSearchForm.getWapNo());
	        	casesSearchVO.setSurgyId(casesSearchForm.getSurgyId());
	        	casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
	        	casesSearchVO.setClaimNo(casesSearchForm.getClaimNo());
	        	casesSearchVO.setPatName(casesSearchForm.getPatName());
	        	casesSearchVO.setFromDate(casesSearchForm.getFromDate());
	        	casesSearchVO.setToDate(casesSearchForm.getToDate());
	        	casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setRowsPerPage(lStrRowsperpage);
	        	casesSearchVO.setStartIndex(lStrStartIndex);
	        	casesSearchVO.setShowPage(showPage);
	        	casesSearchVO.setGrpList(groupsList);
	        	casesSearchVO.setCasesForApprovalFlag(casesForApprovalFlag);
	        	casesSearchVO.setErrCaseApprovalFlag(errCaseApprovalFlag);
	        	casesSearchVO.setCaseForDissFlg(caseForDissFlg);
	        	casesSearchVO.setModule(module);
	        	casesSearchVO.setHospId(casesSearchForm.getHospId());
	        	casesSearchVO.setMainCatName(casesSearchForm.getMainCatName());
	        	casesSearchVO.setCatName(casesSearchForm.getCatName());
	        	casesSearchVO.setProcName(casesSearchForm.getProcName());
	        	casesSearchVO.setTelephonicId(casesSearchForm.getTelephonicId());
	        	casesSearchVO.setExcelFlag(excelFlag);
	        	casesSearchForm.setCasesForApprovalFlag(casesForApprovalFlag);
	        	casesSearchVO.setSchemeVal(lStrSchemeType);
	        	casesSearchVO.setPatientScheme(patientScheme);
	        	
				if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
					casesSearchForm.setShowScheme("show");
					casesSearchForm.setSchemeType(lStrSchemeType);
				}
				else {
					casesSearchForm.setShowScheme("hide");
				}
				
	        	/**
	        	 * set user role
	        	 */
	        	 for(LabelBean labelBean:groupsList)
	     	    {
	     	   if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) || labelBean.getID().equalsIgnoreCase(config.getString("preauth_mithra_role")) ))
	     	     {
	     		    request.setAttribute("is_medco_mithra", "Y");
	     			lStrUserGroup = labelBean.getID() ;
	     			break;
	     	     }
	     	     else{
	     		lStrUserGroup = null;
	     		casesSearchVO.setRoleId(labelBean.getID());
	     	     }
	     	     }
	        	//casesSearchVO = casesSearchService.getUserRole(casesSearchVO);
	        	 casesSearchVO.setUserRole(lStrUserGroup);
	        	if(casesSearchForm.getRowsPerPage() != null && !casesSearchForm.getRowsPerPage().equalsIgnoreCase(""))
	        	{
	        		lStrRowsperpage = casesSearchForm.getRowsPerPage();
	        		casesSearchVO.setRowsPerPage(casesSearchForm.getRowsPerPage() );
	        		casesSearchForm.setRowsPerPage( casesSearchForm.getRowsPerPage());
	        	}
	        	if(casesSearchForm.getShowPage() != null && !casesSearchForm.getShowPage().equals(""))
	        	{
	        		casesSearchVO.setShowPage(casesSearchForm.getShowPage());
	        		casesSearchForm.setShowPage(casesSearchForm.getShowPage());
	        		if(Integer.parseInt(casesSearchForm.getShowPage()) >Integer.parseInt(bundle.getString("case.RowsPerPage")) )
	        		{
	        			casesSearchForm.setPrev(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())-1)));
	        			casesSearchForm.setNext(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())+1)));	
	        		
	        		}
	        	}
	        	casesSearchVO.setTotRowCount(casesSearchForm.getTotalRows());
	        	CasesSearchVO  lstCasesSearchVO  = casesSearchService.getListCases(casesSearchVO);
	        	
	        	if(excelFlag!=null && excelFlag.equalsIgnoreCase("Y")){
	        	 //for excel 
				List<casesSearchRecordVO> expList = new ArrayList<casesSearchRecordVO>();
				casesSearchRecordVO row1 = new casesSearchRecordVO();
				row1.setSno("SL No.");
				row1.setCaseNo("Case Number");
				row1.setClaimNo("Claim Number");
				row1.setPatName("Patient Name");
				row1.setWapNo("Card Number");
				row1.setCaseStatusId("Claim Status");
				row1.setLstUpdDt("Status Date");
				row1.setInpatientCaseSubDt("Inpatient Case Registration Date");
				row1.setCsPreauthDt("PreAuth Date");
				row1.setPreauthAprvDt("PreAuth Apprv Date");
				row1.setCsDisDt("Discharge Date");
				row1.setCsDeathDt("Death Date");
				row1.setHospName("Claim Amount");
				row1.setClmSubDt("Claim Submitted Date");
				row1.setApprovedAmount("Hospital Amount");
				row1.setHospAccountName("TDS Amount");
				row1.setDistrict("RF Amount");
				row1.setCsTransId("Transaction Id");
				row1.setCsTransDt("Transaction Date");
				row1.setCsRemarks("Remarks");
				row1.setCsSbhDt("SBH Paid Date");
				expList.add(row1);
				int i = 0;
				for (CasesSearchVO row : lstCasesSearchVO.getLstCaseSearch()) {
					row1 = new casesSearchRecordVO();
					row1.setSno(++i + "");
					row1.setCaseNo(row.getCaseNo());
					row1.setClaimNo(row.getClaimNo());
					row1.setPatName(row.getPatientName());
					row1.setWapNo(row.getWapNo());
					row1.setCaseStatusId(row.getClaimStatus());
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
					if(row.getLstUpdDt() != null )
						try {
							row1.setLstUpdDt(sdf.format(row.getLstUpdDt()));
						} catch (Exception e) {
							e.printStackTrace();
						}					
					row1.setInpatientCaseSubDt(row.getInpatientCaseSubDt());
					row1.setCsPreauthDt(row.getCsPreauthDt());
					row1.setPreauthAprvDt(row.getPreauthAprvDate());
					row1.setCsDisDt(row.getCsDisDt());
					row1.setCsDeathDt(row.getCsDeathDt());
					row1.setHospName(row.getClaimAmt()+"");
					row1.setClmSubDt(row.getClmSubDt());
					CasesSearchVO  caseSearchVO  = casesSearchService.getAccountDetails(row);
					row1.setApprovedAmount(caseSearchVO.getIssueResultFlagSize());
					row1.setHospAccountName(caseSearchVO.getIssuestatus());
					row1.setDistrict(caseSearchVO.getIssuetitle());
					row1.setCsTransId(row.getCsTransId());
					row1.setCsTransDt(row.getCsTransDt());
					row1.setCsRemarks(row.getCsRemarks());
					row1.setCsSbhDt(row.getCsSbhDt());
					expList.add(row1);
				}

				String lStrDirPath = config.getString("CASESSEARCH.MapPath");
				String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE");
				
				if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
					lStrFileName = lStrFileName	+ "CSV";
				else
					lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");

					// Creates file in EHFTemp folder of client machine
					File xlFile = createFile(lStrDirPath, lStrFileName);
					if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y")){
						 char separator = ',';
				         StringBuilder line = new StringBuilder();  
						for(casesSearchRecordVO caseSearchRVO :  expList){
							line.append(caseSearchRVO.getSno());
							line.append(separator);
							line.append(caseSearchRVO.getCaseNo());
							line.append(separator);
							if(caseSearchRVO.getClaimNo()==null)
								line.append("");
							else
							    line.append(caseSearchRVO.getClaimNo());
							line.append(separator);
							line.append(caseSearchRVO.getPatName());
							line.append(separator);
							line.append(caseSearchRVO.getWapNo());
							line.append(separator);
							line.append(caseSearchRVO.getCaseStatusId());
							line.append(separator);
							line.append(caseSearchRVO.getLstUpdDt());
							line.append(separator);
							line.append(caseSearchRVO.getInpatientCaseSubDt());
							line.append(separator);
							if(caseSearchRVO.getCsPreauthDt()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getCsPreauthDt());
							line.append(separator);
							if(caseSearchRVO.getPreauthAprvDt()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getPreauthAprvDt());
							line.append(separator);
							if(caseSearchRVO.getCsDisDt()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getCsDisDt());
							line.append(separator);
							if(caseSearchRVO.getCsDeathDt()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getCsDeathDt());
							line.append(separator);
							if(caseSearchRVO.getHospName()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getHospName());
							line.append(separator);
							if(caseSearchRVO.getClmSubDt()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getClmSubDt());
							line.append(separator);
							if(caseSearchRVO.getApprovedAmount()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getApprovedAmount());
							line.append(separator);
							if(caseSearchRVO.getHospAccountName()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getHospAccountName());
							line.append(separator);
							if(caseSearchRVO.getDistrict()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getDistrict());
							line.append(separator);							
							if(caseSearchRVO.getCsTransId()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getCsTransId());
							line.append(separator);
							if(caseSearchRVO.getCsTransDt()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getCsTransDt());
							line.append(separator);
							if(caseSearchRVO.getCsRemarks()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getCsRemarks());
							line.append(separator);
							if(caseSearchRVO.getCsSbhDt()==null)
								line.append("");
							else
							line.append(caseSearchRVO.getCsSbhDt());
							line.append("\n");
						}
						    request.setAttribute("File", line.toString().getBytes());    
						    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
						    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
						    response.setCharacterEncoding("UTF-8");
					}else{					
					/*ExcelWriter.prepareExl(xlFile, expList);
					byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);
					
					request.setAttribute("File", lbBytes);
					response.setContentType(config
							.getString("REPORT.ExcelContentType"));
					response.setHeader("Content-Disposition",
							"Attachment; filename=" + lStrFileName);*/
					}
					
					return mapping.findForward("openFile");
				
	        	}        	
	        	casesSearchForm.setStartIndex(lstCasesSearchVO.getStartIndex());
	        	casesSearchForm.setRowsPerPage(casesSearchVO.getRowsPerPage());
	        	    request.setAttribute("StatusList", casesSearchService.getCaseStatus(module));
	        	    request.setAttribute("ErrStatusList", casesSearchService.getCaseErrStatus());
	        	    request.setAttribute("HospList", commonService.getHospitals());
	                request.setAttribute("ErrStatList", casesSearchService.getErroneousList());
	               // request.setAttribute("SurgeryList", casesSearchService.getListOfSurgery());	
	                casesSearchForm.setLstCaseSearch(lstCasesSearchVO.getLstCaseSearch());
	                request.setAttribute("catList", commonService.getCategoryList(lStrEmpId,casesSearchVO.getUserRole()));
	                request.setAttribute("icdCatList", new ArrayList<LabelBean>());
	                request.setAttribute("procList", new ArrayList<LabelBean>());
	                if(casesSearchVO.getMainCatName()!=null && !"".equalsIgnoreCase(casesSearchVO.getMainCatName()))
	                {
	                	request.setAttribute("icdCatList", commonService.getCategorys(casesSearchVO.getMainCatName(),lStrEmpId));
	                	if(casesSearchVO.getCatName()!=null && !"".equalsIgnoreCase(casesSearchVO.getCatName()))
		                {
	                		if(lStrSchemeType.equalsIgnoreCase(config.getString("COMMON")))
	                			lStrSchemeType=config.getString("AP");
	  
		                	request.setAttribute("procList", commonService.getProcedures(casesSearchVO.getCatName(), casesSearchVO.getMainCatName(),casesSearchVO.getHospId(), lStrSchemeType,null));
		                }
	                }
	                
	                /**
	            	 * pagination code starting
	            	 */
	               
	                casesSearchForm.setEndIndex(Integer.toString((Integer.parseInt(casesSearchForm.getStartIndex())+lstCasesSearchVO.getLstCaseSearch().size())));
	                casesSearchForm.setTotalRows(casesSearchVO.getTotRowCount());
	                int liTotalRows =0;
	                if(casesSearchVO.getTotRowCount()!=null)
	                	liTotalRows = Integer.parseInt( casesSearchVO.getTotRowCount());
	            	int liRowsPerPage =Integer.parseInt( casesSearchForm.getRowsPerPage());
	            	int liStartIndex = Integer.parseInt( casesSearchForm.getStartIndex());
	                int liPageNo=Integer.parseInt(casesSearchVO.getShowPage());
	                int liStartPage=Integer.parseInt(bundle.getString("case.StartIndex1"));
	                int liEndPage=Integer.parseInt(bundle.getString("case.RowsPerPage"));
	                int liTotalPages=Integer.parseInt(bundle.getString("case.StartIndex0"));
	           	    if((liTotalRows != Integer.parseInt(bundle.getString("case.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(bundle.getString("case.StartIndex0"))))
	                     { 
	                       liTotalPages=liTotalRows/liRowsPerPage;
	                       if((liTotalRows%liRowsPerPage)>Integer.parseInt(bundle.getString("case.StartIndex0"))) 
	                          liTotalPages++;
	                       }
	           	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(bundle.getString("case.StartIndex1"));
	           	  if(liPageNo > Integer.parseInt(bundle.getString("case.StartIndex1")) && liPageNo <=liTotalPages)
	                 {
	                    if(liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"))==1)
	                    {
	                        liStartPage=liPageNo;
	                        liEndPage=liPageNo+9;
	                    }
	                    else if(liPageNo >Integer.parseInt(bundle.getString("case.RowsPerPage")))
	                    {
	                        int liTemp=Integer.parseInt(bundle.getString("case.StartIndex0"));
	                        liTemp=liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"));
	                        if(liTemp == Integer.parseInt(bundle.getString("case.StartIndex0")))
	                          liTemp=liPageNo-9;                  
	                        else
	                          liTemp=(liPageNo-liTemp)+Integer.parseInt(bundle.getString("case.StartIndex1"));
	                        liStartPage=liTemp;
	                        liEndPage=liTemp+9;                
	                    }
	                 } 
	           	  if(liEndPage > liTotalPages)
	                     liEndPage=liTotalPages;
	                 request.setAttribute("liEndPage", liEndPage);
	                 request.setAttribute("liTotalPages", liTotalPages);
	                 request.setAttribute("liStartPage", liStartPage);
	                 request.setAttribute("liPageNo", liPageNo);
	                 request.setAttribute("lStrRowsperpage", lStrRowsperpage);
	                 
	                 List<Integer> pages = new ArrayList<Integer>();
	                 if(liTotalPages >Integer.parseInt(bundle.getString("case.StartIndex0")))
	                 {
	                	for(int i=1 ; i<=liTotalPages ;i++)
	                	{
	                		pages.add(i);
	                	}
	                 }
	                request.setAttribute("pages", pages);
	                if(pages.size() > Integer.parseInt(bundle.getString("case.RowsPerPage")))
	                {
	                if(casesSearchForm.getNext()!= null)
	                {
	                if(!(Integer.parseInt(casesSearchForm.getNext()) <= pages.size()))
	                {
	                	casesSearchForm.setNext(null);	
	                }
	                }
	                else
	                	 casesSearchForm.setNext(next);
	                }
	              
	               
	                /**
	                 * pagination code ending
	                 */

	                request.setAttribute("autoActionFlag", "OnloadCaseSearch");
	                request.setAttribute("caseSearchType", searchType);
	                request.setAttribute("errSearchType", casesSearchVO.getErrCaseApprovalFlag());
	                request.setAttribute("disSearchType", casesSearchVO.getCaseForDissFlg());
	                request.setAttribute("module", module);
	                request.setAttribute("patientScheme", patientScheme);
	                if(searchType!=null && !searchType.equalsIgnoreCase("") && searchType.equalsIgnoreCase("ChronicOp"))
	                return mapping.findForward("searchChronicOP");
	                else if(("Y").equalsIgnoreCase(ceoFlag))
	                return mapping.findForward("ceoSearch");	
	                else
	                return mapping.findForward("search");
	        }
		    return null;   
	    }
	 
	 /**
		 * Creates the file.
		 *
		 * @param lStrDirPath the l str dir path
		 * @param lStrFileName the l str file name
		 * @return the file
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		private File createFile(String lStrDirPath, String lStrFileName)
				throws IOException {
			// Making Directory
			File file = new File(lStrDirPath);
			if (!file.exists()) {
				file.mkdir();
			}
			File lfile = new File(lStrFileName);
			if (!lfile.exists()) {
				lfile.createNewFile();
			} else {
				lfile.delete();
				lfile.createNewFile();
			}
			return lfile;
		}
		
}

