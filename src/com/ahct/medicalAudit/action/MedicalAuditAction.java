package com.ahct.medicalAudit.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ahct.common.vo.LabelBean;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.ahct.medicalAudit.form.MedicalAuditForm;
import com.ahct.medicalAudit.service.MedicalAuditService;
import com.ahct.medicalAudit.valueobject.MedicalAuditVO;
import com.ahct.common.service.WorkFlowCommonService;

public class MedicalAuditAction extends Action{
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		
		response.setHeader("Cache-Control","no-cache");
	    response.setHeader("pragma","no-cache");
	    response.setDateHeader("Expires", 0);    
	    HttpSession session = request.getSession ( false ) ;
	    String lStrResultPage = HtmlEncode.verifySession(request, response);
	    if (lStrResultPage.length() > 0){
	        return mapping.findForward("regSessionExpire");
	    }
	    MedicalAuditForm medicalAuditForm = (MedicalAuditForm) form;
	    MedicalAuditService medicalAuditServiceObj = (MedicalAuditService)ServiceFinder.getContext(request).getBean("medicalAuditService");
	    ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
	    WorkFlowCommonService workFlowCommonService=(WorkFlowCommonService)ServiceFinder.getContext(request).getBean("workFlowCommonService");
	    lStrResultPage = "";
		String lStrFlagStatus = request.getParameter("actionFlag");
		String showBtn = "display:none";
		String showRemarks= "display:none";
		String showWrkFlwSubBtn = "display:none";
		String showRemarksTextArea ="display:none";
		
		Configuration config = configurationService.getConfiguration();
		if (lStrFlagStatus != null && "getQuestionnaire".equals(lStrFlagStatus)) {
			String[] status = null;
			 String caseId=request.getParameter("caseId");
			 System.out.println("case id received from request is" +caseId);
			 String purpose=request.getParameter("purpose");
			
			 if(purpose.equalsIgnoreCase("show")){
				 showBtn="display:block";
				 request.setAttribute("showBtn",showBtn);
				 request.setAttribute("showRemarks", showRemarks);
				 request.setAttribute("showWrkFlwSubBtn",showWrkFlwSubBtn);
				 request.setAttribute("showRemarksTextArea",showRemarksTextArea);
				 request.setAttribute("backTo","initiateMedicalAudit");
				 request.setAttribute("disabled","no");
			 }
			 else {
				 
				 request.setAttribute("showBtn",showBtn);
				 List<MedicalAuditVO> remarksList=medicalAuditServiceObj.getPreviousRemarks(caseId);
				 medicalAuditForm.setRemarksList(remarksList);
				 if(request.getParameter("fromPage").equalsIgnoreCase("worklist")){
						 showWrkFlwSubBtn="display:block";
						 showRemarksTextArea ="display:block";
						 request.setAttribute("backTo","workList");
				 }
				 else {
					 
					 request.setAttribute("backTo","auditCases");
				 }
				 showRemarks="display:block";
				 request.setAttribute("showWrkFlwSubBtn",showWrkFlwSubBtn);
				 request.setAttribute("showRemarks", showRemarks);
				 request.setAttribute("showRemarksTextArea",showRemarksTextArea);
				 request.setAttribute("disabled","yes");
			 }
			 String userId=(String)session.getAttribute("UserID");
			String details= medicalAuditServiceObj.findAuditAlreadyStarted(caseId,userId);
			if(details!=null){
			 status=details.split("~");
			}
			if(status==null){
			 List<LabelBean> quesAnsList=null;
			 quesAnsList= medicalAuditServiceObj.getQuestionAnswers(caseId);
			 System.out.println("total number of questions in questionnaire"+quesAnsList.size());
			 request.setAttribute("questionList", quesAnsList);
			 
			 medicalAuditForm.setFinalRemarks(medicalAuditServiceObj.fetchRemarks(caseId));
			 request.setAttribute("showAlert", "no");
			 
			}
			else {
				request.setAttribute("showAlert", "yes");
				request.setAttribute("userName",status[1]);
			}
			medicalAuditForm.setCaseId(caseId);
			 lStrResultPage = "questionnaire";
		}
		
		if(lStrFlagStatus != null && "saveAnswers".equals(lStrFlagStatus)) {
			
			String initialWorkFlowId =null;
			String nextWorkFlowId=null;
			String nextOwnerGrp=null;
			
			String result= request.getParameter("ques");
            String remarks= request.getParameter("remarks");
            String actionType=request.getParameter("actionType");
            String userId=(String) session.getAttribute("UserID");
            String userState=(String) session.getAttribute("userState");
            String currentOwnerGrp=null;
            List<LabelBean> grplist=( List<LabelBean> )session.getAttribute("groupList");
            List<String> grps1=new ArrayList<String>();
            for(int i=0;i<grplist.size();i++)
            	{
            		grps1.add(grplist.get(i).getID());
            	}
            
            for(int i=0;i<grplist.size();i++){
				
				if ((config.getString("DEO")).equalsIgnoreCase(grplist.get(i).getID())) {
					initialWorkFlowId = config.getString("MedicalAuditDeoInit.WorkFlowId").trim();
					currentOwnerGrp=config.getString("DEO");
					break;
				}
				if ((config.getString("JEO")).equalsIgnoreCase(grplist.get(i).getID())) {
					initialWorkFlowId = config.getString("MedicalAuditJeoInit.WorkFlowId").trim();
					currentOwnerGrp=config.getString("JEO");
					break;
				}
				if ((config.getString("CMA")).equalsIgnoreCase(grplist.get(i).getID())) {
					initialWorkFlowId = config.getString("MedicalAuditCmaInit.WorkFlowId").trim();
					currentOwnerGrp=config.getString("CMA");
					break;
				}
				
				}
            MedicalAuditVO medicalAuditVO=new MedicalAuditVO();
            medicalAuditVO.setGpLst(grps1);
            medicalAuditVO.setResult(result);
            medicalAuditVO.setRemarks(remarks);
            medicalAuditVO.setUserId(userId);
            medicalAuditVO.setSchemeId(userState);
            medicalAuditVO.setCASEID(medicalAuditForm.getCaseId());
            medicalAuditVO.setFinalRemarks(medicalAuditForm.getFinalRemarks());
           
            if(actionType.equalsIgnoreCase("Save")){
            	
            	medicalAuditVO.setPrevWorkflowId(initialWorkFlowId);
                 medicalAuditVO.setCurrentWorkflowId(initialWorkFlowId);
                 medicalAuditVO.setPreviousOwnerGrp(currentOwnerGrp);
                 medicalAuditVO.setCurrentOwnerGrp(currentOwnerGrp);
                 medicalAuditVO.setActionType(actionType);
                 request.setAttribute("disabled","no");
                 request.setAttribute("showStatus", "saved");
                
            }
            if(actionType.equalsIgnoreCase("Submit")){
            	
            	if(config.getString("MedicalAuditCmaInit.WorkFlowId").equalsIgnoreCase(initialWorkFlowId))
            			initialWorkFlowId=config.getString("CMA_APRVL");
            	 nextWorkFlowId=workFlowCommonService.getNextWorkFlowId(initialWorkFlowId,"Approve");
            	 nextOwnerGrp=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(nextWorkFlowId);
            	 medicalAuditVO.setPrevWorkflowId(initialWorkFlowId);
                 medicalAuditVO.setCurrentWorkflowId(nextWorkFlowId);
                 medicalAuditVO.setPreviousOwnerGrp(currentOwnerGrp);
                 medicalAuditVO.setCurrentOwnerGrp(nextOwnerGrp);
                 medicalAuditVO.setActionType(actionType);
                 request.setAttribute("showStatus", "auditInitiated");
                
                 
            }
            medicalAuditServiceObj.saveMedicalAuditAnswers(medicalAuditVO);
            List<LabelBean> quesAnsList= medicalAuditServiceObj.getQuestionAnswers(medicalAuditForm.getCaseId());
			 request.setAttribute("questionList", quesAnsList);
			 request.setAttribute("showAlert", "no");
			 request.setAttribute("showRemarks", showRemarks);
			 request.setAttribute("showRemarksTextArea",showRemarksTextArea);
			 request.setAttribute("showWrkFlwSubBtn",showWrkFlwSubBtn);
			 medicalAuditForm.setCaseId(medicalAuditForm.getCaseId());
				
			 lStrResultPage = "questionnaire";
			 
				 	
		}
		if (lStrFlagStatus != null && "medicalAuditSearchPage".equals(lStrFlagStatus)) {
			
			List<LabelBean> distList =medicalAuditServiceObj.getDistricts();
			request.setAttribute("districtCombo", distList);
			List<LabelBean> specialitiesList= medicalAuditServiceObj.getSpecialities();
			request.setAttribute("specialityCombo", specialitiesList);
			List<LabelBean> hospList =new ArrayList<LabelBean>();
			request.setAttribute("hospCombo", hospList);
			List<LabelBean> categoriesList =new ArrayList<LabelBean>();
			request.setAttribute("categoryCombo", categoriesList);
			List<LabelBean> proceduresList =new ArrayList<LabelBean>();
			request.setAttribute("procedureCombo", proceduresList);
			 String userState=(String) session.getAttribute("userState");
	            
	            if(userState==null || userState==""){
					
	            	userState=config.getString("COMMON");
				}
	            if(userState.equalsIgnoreCase(config.getString("COMMON"))){
					medicalAuditForm.setShowScheme("show");
				 }
				 else {
					 medicalAuditForm.setShowScheme("hide");
				 }
			 lStrResultPage = "auditSearch";
		}
		
		if(lStrFlagStatus != null && "getCategory".equals(lStrFlagStatus)){
			List<String> catList = new ArrayList<String>();
			String specialityType=request.getParameter("specialityType");
			List<LabelBean> categoriesList=	medicalAuditServiceObj.getCategories(specialityType);
			 if (categoriesList != null && categoriesList.size() > 0) {
				  for (LabelBean labelBean : categoriesList) {
					 
							  if (catList != null && catList.size() > 0) {
								  catList.add("@" + labelBean.getID() + "~"
											+ labelBean.getVALUE());
								} else{
									catList.add(labelBean.getID() + "~"
											+ labelBean.getVALUE());
							
									  
					  }
					 
				  }
			     }
			request.setAttribute("AjaxMessage", catList);
            lStrResultPage = "ajaxResult";
			
			
		}
		if(lStrFlagStatus != null && "getProcedure".equals(lStrFlagStatus)){
			List<String> procList = new ArrayList<String>();
			String categoryType=request.getParameter("categoryType");
			List<LabelBean> proceduresList=	medicalAuditServiceObj.getProcedures(categoryType);
			 if (proceduresList != null && proceduresList.size() > 0) {
				  for (LabelBean labelBean : proceduresList) {
					 
							  if (procList != null && procList.size() > 0) {
								  procList.add("@" + labelBean.getID() + "~"
											+ labelBean.getVALUE());
								} else{
									procList.add(labelBean.getID() + "~"
											+ labelBean.getVALUE());
							
									  
					  }
					 
				  }
			     }
			request.setAttribute("AjaxMessage", procList);
            lStrResultPage = "ajaxResult";
			
			
		}
		if(lStrFlagStatus != null && "gethospitals".equals(lStrFlagStatus)){
			List<String> hospitalsList = new ArrayList<String>();
			String districtCode=request.getParameter("districtCode");
			String hospType=request.getParameter("hospType");
			List<LabelBean> hospList=medicalAuditServiceObj.getHospitals(districtCode,hospType);
			if (hospList != null && hospList.size() > 0) {
				  for (LabelBean labelBean : hospList) {
					 
							  if (hospitalsList != null && hospitalsList.size() > 0) {
								  hospitalsList.add("@" + labelBean.getID() + "~"
											+ labelBean.getVALUE());
								} else{
									hospitalsList.add(labelBean.getID() + "~"
											+ labelBean.getVALUE());
							
									  
					  }
					 
				  }
			     }
			request.setAttribute("AjaxMessage", hospitalsList);
          lStrResultPage = "ajaxResult";
		}
		
		if(lStrFlagStatus != null && "getSampleCasesForAudit".equals(lStrFlagStatus)){
			
			int start_index=0;
			int end_index=0;
			int pageId;	
			int noOfRecords;
			int noOfPages=0;
			
			MedicalAuditVO medicalAuditVO=new MedicalAuditVO();
			
            if(request.getParameter("backFlag").equalsIgnoreCase("yes")){
            	medicalAuditVO=(MedicalAuditVO) session.getAttribute("searchMedicalAuditNewVO");
            	medicalAuditForm.setSelectionType(medicalAuditVO.getSelectionType());
            	medicalAuditForm.setDistName(medicalAuditVO.getDistrict());
            	medicalAuditForm.setHospType(medicalAuditVO.getHospType());
            	medicalAuditForm.setGroupType(medicalAuditVO.getGroupType());
            	medicalAuditForm.setCategory(medicalAuditVO.getCategoryId());
            	medicalAuditForm.setProcedure(medicalAuditVO.getProcedureId());
            	medicalAuditForm.setCaseNumber(medicalAuditVO.getCASENO());
            	medicalAuditForm.setClaimNumber(medicalAuditVO.getCLAIMNO());
            	medicalAuditForm.setSchemeType(medicalAuditVO.getSchemeId());
            	medicalAuditForm.setHospName(medicalAuditVO.getHospId());
            	medicalAuditVO.setStart_index(start_index);
    			medicalAuditVO.setEnd_index(end_index);
            }
            else {
			medicalAuditVO.setSelectionType(medicalAuditForm.getSelectionType());
			medicalAuditVO.setDistrict(medicalAuditForm.getDistName());
			medicalAuditVO.setHospType(medicalAuditForm.getHospType());
			medicalAuditVO.setGroupType(medicalAuditForm.getGroupType());
			
			if(medicalAuditForm.getSelectionType()!=null
					&&!medicalAuditForm.getSelectionType().equalsIgnoreCase(""))
				{
					if(medicalAuditForm.getSelectionType().equalsIgnoreCase("DEN"))
						medicalAuditVO.setSpecialityId(medicalAuditForm.getSpecialityDen());
					else
						medicalAuditVO.setSpecialityId(medicalAuditForm.getSpeciality());
				}
			else
				medicalAuditVO.setSpecialityId(medicalAuditForm.getSpeciality());
			
			medicalAuditVO.setCategoryId(medicalAuditForm.getCategory());
			medicalAuditVO.setProcedureId(medicalAuditForm.getProcedure());
			medicalAuditVO.setCASENO(medicalAuditForm.getCaseNumber());
			medicalAuditVO.setCLAIMNO(medicalAuditForm.getClaimNumber());
			medicalAuditVO.setSchemeId(medicalAuditForm.getSchemeType());
			medicalAuditVO.setHospId(medicalAuditForm.getHospName());
			
			
			session.removeAttribute("searchMedicalAuditNewVO");
            session.setAttribute("searchMedicalAuditNewVO", (Object)medicalAuditVO);
            }
            List<LabelBean> hospList=medicalAuditServiceObj.getHospitals(medicalAuditForm.getDistName(),medicalAuditForm.getHospType());
			request.setAttribute("hospCombo", hospList);
			List<LabelBean> categoriesList=	medicalAuditServiceObj.getCategories(medicalAuditForm.getSpeciality());
			request.setAttribute("categoryCombo", categoriesList);
			List<LabelBean> proceduresList=	medicalAuditServiceObj.getProcedures(medicalAuditForm.getCategory());
			request.setAttribute("procedureCombo", proceduresList);
            String userState=(String) session.getAttribute("userState");
            
            if(userState==null || userState==""){
				
            	userState=config.getString("COMMON");
			}
            medicalAuditVO.setUserState(userState);
			List<MedicalAuditVO> auditCasesListTemp=medicalAuditServiceObj.getSampleCasesForAuditNew(medicalAuditVO);
			
			if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
			{
				pageId=Integer.parseInt(request.getParameter("pageId"));
					
				if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
						end_index=Integer.parseInt(request.getParameter("end_index"));
					else
						end_index=10;
						
				start_index=end_index*(pageId-1);
			}
			else
			{
				pageId=1;
				end_index=10;
				start_index=0;
			}
			
			noOfRecords=auditCasesListTemp.size();
			int div=noOfRecords%end_index;
			if(div==0)
				noOfPages=noOfRecords/end_index;
			if(div!=0)
				noOfPages=(noOfRecords/end_index)+1;
			
			medicalAuditVO.setStart_index(start_index);
			medicalAuditVO.setEnd_index(end_index);

			auditCasesListTemp=medicalAuditServiceObj.getSampleCasesForAuditNew(medicalAuditVO);
			//List<MedicalAuditVO> auditCasesList =medicalAuditServiceObj.auditCasesForDisplay(auditCasesListTemp);
			
			
			
			medicalAuditForm.setAuditList(auditCasesListTemp);
			request.setAttribute("selectType",medicalAuditForm.getSelectionType());
			List<LabelBean> distList =medicalAuditServiceObj.getDistricts();
			request.setAttribute("districtCombo", distList);
			List<LabelBean> specialitiesList= medicalAuditServiceObj.getSpecialities();
			request.setAttribute("specialityCombo", specialitiesList);
			if(userState.equalsIgnoreCase(config.getString("COMMON"))){
				medicalAuditForm.setShowScheme("show");
			 }
			 else {
				 medicalAuditForm.setShowScheme("hide");
			 }
			request.setAttribute("pageId",pageId);
			request.setAttribute("noOfRecords",noOfRecords);
			request.setAttribute("lastPage",noOfPages);
			request.setAttribute("start_index",start_index);
			request.setAttribute("end_index",end_index);
			request.setAttribute("backFlag",request.getParameter("backFlag"));
			request.setAttribute("endresults",start_index+auditCasesListTemp.size());
			
			 lStrResultPage = "auditSearch";
			
		}
		
		
		
		if(lStrFlagStatus != null && "getAuditedCaseslist".equals(lStrFlagStatus)){
			
			int start_index=0;
			int end_index=0;
			int pageId;	
			int noOfRecords;
			int noOfPages=0;
			
			 String userState=(String) session.getAttribute("userState");
	            
			MedicalAuditVO medicalAuditVO=new MedicalAuditVO();
			 
			 if(request.getParameter("backFlag").equalsIgnoreCase("yes")){ 
				 
				medicalAuditVO=(MedicalAuditVO) session.getAttribute("searchMedicalAuditWorkList");
				medicalAuditForm.setSelectionType(medicalAuditVO.getSelectionType());
	            medicalAuditForm.setDistName(medicalAuditVO.getDistrict());
	            medicalAuditForm.setHospType(medicalAuditVO.getHospType());
	            medicalAuditForm.setGroupType(medicalAuditVO.getGroupType());
	            medicalAuditForm.setCategory(medicalAuditVO.getCategoryId());
	            medicalAuditForm.setProcedure(medicalAuditVO.getProcedureId());
	            medicalAuditForm.setCaseNumber(medicalAuditVO.getCASENO());
	            medicalAuditForm.setClaimNumber(medicalAuditVO.getCLAIMNO());
	            medicalAuditForm.setSchemeType(medicalAuditVO.getSchemeId());
	            medicalAuditForm.setHospName(medicalAuditVO.getHospId());
	            medicalAuditForm.setAuditedBy(medicalAuditVO.getAuditedBy());
	            medicalAuditVO.setFromDate(medicalAuditForm.getFromDate());
				medicalAuditVO.setToDate(medicalAuditForm.getToDate());
	            
	            medicalAuditVO.setStart_index(0);
    			medicalAuditVO.setEnd_index(0);
	            
			 }
			 else {
		
			medicalAuditVO.setSelectionType(medicalAuditForm.getSelectionType());
			medicalAuditVO.setDistrict(medicalAuditForm.getDistName());
			medicalAuditVO.setHospType(medicalAuditForm.getHospType());
			medicalAuditVO.setGroupType(medicalAuditForm.getGroupType());
			
			if(medicalAuditForm.getSelectionType()!=null
					&&!medicalAuditForm.getSelectionType().equalsIgnoreCase(""))
				{
					if(medicalAuditForm.getSelectionType().equalsIgnoreCase("DEN"))
						medicalAuditVO.setSpecialityId(medicalAuditForm.getSpecialityDen());
					else
						medicalAuditVO.setSpecialityId(medicalAuditForm.getSpeciality());
				}
			else
				medicalAuditVO.setSpecialityId(medicalAuditForm.getSpeciality());
			
			medicalAuditVO.setCategoryId(medicalAuditForm.getCategory());
			medicalAuditVO.setProcedureId(medicalAuditForm.getProcedure());
			medicalAuditVO.setCASENO(medicalAuditForm.getCaseNumber());
			medicalAuditVO.setCLAIMNO(medicalAuditForm.getClaimNumber());
			medicalAuditVO.setSchemeId(medicalAuditForm.getSchemeType());
			medicalAuditVO.setHospId(medicalAuditForm.getHospName());
			medicalAuditVO.setAuditedBy(medicalAuditForm.getAuditedBy());
			medicalAuditVO.setFromDate(medicalAuditForm.getFromDate());
			medicalAuditVO.setToDate(medicalAuditForm.getToDate());
			
			medicalAuditVO.setActionType("worklist");
			session.removeAttribute("searchMedicalAuditWorkList");
            session.setAttribute("searchMedicalAuditWorkList", (Object)medicalAuditVO);
           
            if(userState==null || userState==""){
				
            	userState=config.getString("COMMON");
			}
            medicalAuditVO.setUserState(userState);
           
			 }
			
			 List<MedicalAuditVO> auditCasesListTemp=medicalAuditServiceObj.getAuditedCasesList(medicalAuditVO); 
			 
			 if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
				{
					pageId=Integer.parseInt(request.getParameter("pageId"));
						
					if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
							end_index=Integer.parseInt(request.getParameter("end_index"));
						else
							end_index=10;
							
					start_index=end_index*(pageId-1);
				}
				else
				{
					pageId=1;
					end_index=10;
					start_index=0;
				}
				
				noOfRecords=auditCasesListTemp.size();
				int div=noOfRecords%end_index;
				if(div==0)
					noOfPages=noOfRecords/end_index;
				if(div!=0)
					noOfPages=(noOfRecords/end_index)+1;
				
				medicalAuditVO.setStart_index(start_index);
				medicalAuditVO.setEnd_index(end_index);
			 List<LabelBean> hospList=medicalAuditServiceObj.getHospitals(medicalAuditForm.getDistName(),medicalAuditForm.getHospType());
				request.setAttribute("hospCombo", hospList);
				List<LabelBean> categoriesList=	medicalAuditServiceObj.getCategories(medicalAuditForm.getSpeciality());
				request.setAttribute("categoryCombo", categoriesList);
				List<LabelBean> proceduresList=	medicalAuditServiceObj.getProcedures(medicalAuditForm.getCategory());
				request.setAttribute("procedureCombo", proceduresList);
				
			auditCasesListTemp=medicalAuditServiceObj.getAuditedCasesList(medicalAuditVO);
			List<MedicalAuditVO> auditCasesList =medicalAuditServiceObj.auditCasesForDisplay(auditCasesListTemp);

			medicalAuditForm.setAuditList(auditCasesList);
			List<LabelBean> distList =medicalAuditServiceObj.getDistricts();
			request.setAttribute("districtCombo", distList);
			 if(userState.equalsIgnoreCase(config.getString("COMMON"))){
					medicalAuditForm.setShowScheme("show");
				 }
				 else {
					 medicalAuditForm.setShowScheme("hide");
				 }
			List<LabelBean> specialitiesList= medicalAuditServiceObj.getSpecialities();
			request.setAttribute("specialityCombo", specialitiesList);
			request.setAttribute("pageId",pageId);
			request.setAttribute("noOfRecords",noOfRecords);
			request.setAttribute("lastPage",noOfPages);
			request.setAttribute("start_index",start_index);
			request.setAttribute("end_index",end_index);
			request.setAttribute("backFlag",request.getParameter("backFlag"));
			request.setAttribute("endresults",start_index+auditCasesListTemp.size());
			request.setAttribute("fromPage", "auditedCases");
			request.setAttribute("flag","auditedList");
			lStrResultPage = "auditedCasesView";
		}
		if(lStrFlagStatus != null && "getMAworklist".equals(lStrFlagStatus)){
			
			int start_index=0;
			int end_index=0;
			int pageId;	
			int noOfRecords;
			int noOfPages=0;
			
			String cmaDeoFlag=request.getParameter("cmaDeoFlag");
				
			if(request.getParameter("cmaDeoFlag")==null){
				if(session.getAttribute("cmaDeoFlag")!=null)
					cmaDeoFlag=(String)session.getAttribute("cmaDeoFlag");
			}
			session.setAttribute("cmaDeoFlag", cmaDeoFlag);
			
			List<String> grps=new ArrayList<String>();
			String userState=(String) session.getAttribute("userState");
			MedicalAuditVO medicalAuditVO=new MedicalAuditVO();
			 
			 if(request.getParameter("backFlag").equalsIgnoreCase("yes")){ 
				 
				 medicalAuditVO=(MedicalAuditVO) session.getAttribute("searchMedicalAuditWorkList");
				 medicalAuditForm.setSelectionType(medicalAuditVO.getSelectionType());
	            	medicalAuditForm.setDistName(medicalAuditVO.getDistrict());
	            	medicalAuditForm.setHospType(medicalAuditVO.getHospType());
	            	medicalAuditForm.setGroupType(medicalAuditVO.getGroupType());
	            	medicalAuditForm.setCategory(medicalAuditVO.getCategoryId());
	            	medicalAuditForm.setProcedure(medicalAuditVO.getProcedureId());
	            	medicalAuditForm.setCaseNumber(medicalAuditVO.getCASENO());
	            	medicalAuditForm.setClaimNumber(medicalAuditVO.getCLAIMNO());
	            	medicalAuditForm.setSchemeType(medicalAuditVO.getSchemeId());
	            	medicalAuditForm.setHospName(medicalAuditVO.getHospId());
	            	medicalAuditVO.setStart_index(0);
	     			medicalAuditVO.setEnd_index(0);
			 }
			 else {
		List<LabelBean> grplist=( List<LabelBean> )session.getAttribute("groupList");
				  for(int i=0;i<grplist.size();i++){
					  
					  grps.add(grplist.get(i).getID());
				  }
			medicalAuditVO.setGrpLst(grps);
			medicalAuditVO.setSelectionType(medicalAuditForm.getSelectionType());
			medicalAuditVO.setDistrict(medicalAuditForm.getDistName());
			medicalAuditVO.setHospType(medicalAuditForm.getHospType());
			medicalAuditVO.setGroupType(medicalAuditForm.getGroupType());

			if(medicalAuditForm.getSelectionType()!=null
					&&!medicalAuditForm.getSelectionType().equalsIgnoreCase(""))
				{
					if(medicalAuditForm.getSelectionType().equalsIgnoreCase("DEN"))
						medicalAuditVO.setSpecialityId(medicalAuditForm.getSpecialityDen());
					else
						medicalAuditVO.setSpecialityId(medicalAuditForm.getSpeciality());
				}
			else
				medicalAuditVO.setSpecialityId(medicalAuditForm.getSpeciality());
			
			medicalAuditVO.setCategoryId(medicalAuditForm.getCategory());
			medicalAuditVO.setProcedureId(medicalAuditForm.getProcedure());
			medicalAuditVO.setCASENO(medicalAuditForm.getCaseNumber());
			medicalAuditVO.setCLAIMNO(medicalAuditForm.getClaimNumber());
			medicalAuditVO.setSchemeId(medicalAuditForm.getSchemeType());
			medicalAuditVO.setHospId(medicalAuditForm.getHospName());
			
			medicalAuditVO.setActionType("worklist");
			session.removeAttribute("searchMedicalAuditWorkList");
            session.setAttribute("searchMedicalAuditWorkList", (Object)medicalAuditVO);
			 }
            
            if(userState==null || userState==""){
				
            	userState=config.getString("COMMON");
			}
            medicalAuditVO.setUserState(userState);
            medicalAuditVO.setCmaDeoFlag(cmaDeoFlag);
            List<MedicalAuditVO> auditCasesListTemp=medicalAuditServiceObj.getMedicalAuditWorklist(medicalAuditVO);
            
            if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
			{
				pageId=Integer.parseInt(request.getParameter("pageId"));
					
				if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
						end_index=Integer.parseInt(request.getParameter("end_index"));
					else
						end_index=10;
						
				start_index=end_index*(pageId-1);
			}
			else
			{
				pageId=1;
				end_index=10;
				start_index=0;
			}
			
			noOfRecords=auditCasesListTemp.size();
			int div=noOfRecords%end_index;
			if(div==0)
				noOfPages=noOfRecords/end_index;
			if(div!=0)
				noOfPages=(noOfRecords/end_index)+1;
			
			medicalAuditVO.setStart_index(start_index);
			medicalAuditVO.setEnd_index(end_index);
            
            
            
            
			 
			 List<LabelBean> hospList=medicalAuditServiceObj.getHospitals(medicalAuditForm.getDistName(),medicalAuditForm.getHospType());
				request.setAttribute("hospCombo", hospList);
				List<LabelBean> categoriesList=	medicalAuditServiceObj.getCategories(medicalAuditForm.getSpeciality());
				request.setAttribute("categoryCombo", categoriesList);
				List<LabelBean> proceduresList=	medicalAuditServiceObj.getProcedures(medicalAuditForm.getCategory());
				request.setAttribute("procedureCombo", proceduresList);
			 if(userState.equalsIgnoreCase(config.getString("COMMON"))){
					medicalAuditForm.setShowScheme("show");
				 }
				 else {
					 medicalAuditForm.setShowScheme("hide");
				 }
			 
			     //auditCasesListTemp=medicalAuditServiceObj.getSampleCasesForAuditNew(medicalAuditVO);
				//List<MedicalAuditVO> auditCasesList =medicalAuditServiceObj.auditCasesForDisplay(auditCasesListTemp);
			 
			 
			auditCasesListTemp=medicalAuditServiceObj.getMedicalAuditWorklist(medicalAuditVO);
			List<MedicalAuditVO> auditCasesList =medicalAuditServiceObj.auditCasesForDisplay(auditCasesListTemp);
			
			medicalAuditForm.setAuditList(auditCasesList);
			List<LabelBean> distList =medicalAuditServiceObj.getDistricts();
			request.setAttribute("districtCombo", distList);
			List<LabelBean> specialitiesList= medicalAuditServiceObj.getSpecialities();
			request.setAttribute("specialityCombo", specialitiesList);
			request.setAttribute("fromPage", "worklist");
			request.setAttribute("flag","worklist");
			request.setAttribute("pageId",pageId);
			request.setAttribute("noOfRecords",noOfRecords);
			request.setAttribute("lastPage",noOfPages);
			request.setAttribute("start_index",start_index);
			request.setAttribute("end_index",end_index);
			request.setAttribute("backFlag",request.getParameter("backFlag"));
			request.setAttribute("endresults",start_index+auditCasesListTemp.size());
			
			lStrResultPage = "maWorklist";
		}
		
		if(lStrFlagStatus != null && "startMedicalAuditWorkFlow".equals(lStrFlagStatus)){
			
			String nextWorkFlowId=null;
			String currentOwnerGrp =null;
			String caseId=medicalAuditForm.getCaseId();
			String currentWorkFlowId=medicalAuditServiceObj.getCurrentWorkFlowId(caseId);
			List<LabelBean> grplist=( List<LabelBean> )session.getAttribute("groupList");
            for(int i=0;i<grplist.size();i++)
            	{
            		if(currentWorkFlowId.equalsIgnoreCase(config.getString("JEO_APRVL")) && config.getString("CMA").equalsIgnoreCase(grplist.get(i).getID()))
            			currentWorkFlowId=config.getString("CMA_APRVL");
            	}
            
            nextWorkFlowId=workFlowCommonService.getNextWorkFlowId(currentWorkFlowId,"Approve");
            
			if(nextWorkFlowId.equalsIgnoreCase("NA")){
				
				 currentOwnerGrp="";
			 }
			 else{
				 currentOwnerGrp=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(nextWorkFlowId);
			 }
			MedicalAuditVO medicalAuditVO=new MedicalAuditVO();
			String userId=(String) session.getAttribute("UserID");
			medicalAuditVO.setUserId(userId);
			medicalAuditVO.setCurrentWorkflowId(nextWorkFlowId);
			medicalAuditVO.setPrevWorkflowId(currentWorkFlowId);
			medicalAuditVO.setCurrentOwnerGrp(currentOwnerGrp);
			String prevOwnerGrp=medicalAuditServiceObj.getPreviousOwnerGrp(currentWorkFlowId);
			medicalAuditVO.setPreviousOwnerGrp(prevOwnerGrp);
			medicalAuditVO.setCASEID(caseId);
			medicalAuditVO.setWorkFlowRemarks(medicalAuditForm.getWorkFlowRemarks());
			medicalAuditServiceObj.saveMedAuditWrkFlowDtls(medicalAuditVO);
			 request.setAttribute("showStatus", "workFlowSubmitted");
             lStrResultPage = "questionnaire";
		}
		
		
		return mapping.findForward(lStrResultPage);
}
}
