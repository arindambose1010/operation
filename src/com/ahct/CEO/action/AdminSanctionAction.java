package com.ahct.CEO.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.CEO.form.AdminSanctionForm;
import com.ahct.CEO.service.AdminSanctionService;
import com.ahct.CEO.service.CeoApprovalsService;
import com.ahct.CEO.service.CeoWorkListService;
import com.ahct.CEO.vo.AdminSanctionRemarksVO;
import com.ahct.CEO.vo.AdminSanctionVO;
import com.ahct.CEO.vo.EdcVO;
import com.ahct.CEO.vo.EmpanelHospVO;
import com.ahct.CEO.vo.SQLChangeMgmtTransVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.constants.ASRIConstants;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.ahct.model.EhfAdminSanctionsMetaData;
import com.ahct.workflow.common.service.WorkFlowCommonService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminSanctionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// To retrieve session object from request
		HttpSession session = null;
		session = request.getSession();

		AdminSanctionForm adminSanctionForm = (AdminSanctionForm) form;
		AdminSanctionService adminSanctionService = (AdminSanctionService) ServiceFinder
				.getContext(request).getBean("adminSanctionService");
		WorkFlowCommonService workFlowCommonService = (WorkFlowCommonService) ServiceFinder
				.getContext(request).getBean("workFlowCommonServiceCEO");
		ConfigurationService configurationService = (ConfigurationService) ServiceFinder
				.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		 CeoApprovalsService ceoWorklistService=(CeoApprovalsService)ServiceFinder.getContext(request).getBean("ceoApprovalService");
		 CeoWorkListService CeoWorkListServiceObj = (CeoWorkListService)ServiceFinder.getContext(request).getBean("ceoWorkListService");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// For Seesion Expiry
		if (session == null || session.getAttribute("UserID") == null) {
			request.setAttribute("Message",
					"Your session has expired. Login again");
			return mapping.findForward("sessionExpire");
		}
		// End of session expiry

		// For avoidin IE8 issues and to remove cachy data
		response.setHeader("Pragma", "");
		response.setHeader("Cache-Control", "private");
		response.setDateHeader("Expires", Long.MAX_VALUE);
		// End

		// Declarations
		String lStrEmpId = null;
		String lStrFlag = null;
		String lStrResultPage = null;
		String lStrUserState = null;
		String lstrSchemeId = null;
		String showSave = "display:none";
		String showSubmit = "display:none";
		String showReset = "display:none";
		String showClose = "display:none";
		String showActionBtn = "display:none";
		String showActionBtn1 = "display:none";
		String showRecApprBtn = "display:none";
		String readonly = "true";
		String showRemarksTextArea = "display:none";
		String showRemarks = "display:none";
		String showUpdateBtn = "display:none";
		String showSubBtn = "display:none";
		String showResetBtn = "display:none";
		String btnAppr = null;
		String btnRecAppr = null;
		String btnPen = null;
		String butDisabled = "";
		String btnRej = null;
		String stateSeqId = null;
		String disabled = "false";
		String showBack = "display:none";
		String alertMsg = "absent";
		String showPrint = "display:none";
		LabelBean labelBean = null;
		String specString = "";
		String costString = "";
		List<LabelBean> deptList = adminSanctionService.getDeptDetails();
		List<LabelBean> schemeList = new ArrayList<LabelBean>();
		List<LabelBean> detailedHeadsList = new ArrayList<LabelBean>();
		List<LabelBean> sourceOfBudgetList = new ArrayList<LabelBean>();
		List<LabelBean> vendorNamesList = new ArrayList<LabelBean>();
		List<LabelBean> vendorCodesList = new ArrayList<LabelBean>();
		List<LabelBean> issuingAuthNamesList = new ArrayList<LabelBean>();
		request.setAttribute("issuingAuthNamesList", issuingAuthNamesList);
		
		List<LabelBean> grplist = (List<LabelBean>) session.getAttribute("groupList");
		String showDetailedList = "N";
		String lStrFlagStatus = request.getParameter("actionFlag");
		if (session.getAttribute("UserID") != null)
			lStrEmpId = (String) session.getAttribute("UserID");

		// To get the state of user
		if ((session.getAttribute("userState") != null)
				&& !(session.getAttribute("userState").equals("")))
			lStrUserState = (String) session.getAttribute("userState");
		request.setAttribute("userState", lStrUserState);

		// To set the scheme based on state
		if (lStrUserState.equals(config.getString("ACC.APState"))) {

			lstrSchemeId = config.getString("ACC.EHFAPSchemeID");
			stateSeqId = "AP";
		}
		if (lStrUserState.equals(config.getString("ACC.TGState"))) {
			lstrSchemeId = config.getString("ACC.EHFTGSchemeID");
			stateSeqId = "TG";
		}
		
		
		
		
		if (lStrFlagStatus != null
		&& lStrFlagStatus.equalsIgnoreCase("getDashBoard")) {
			List<AdminSanctionVO> adminSancListTemp = null;

				 adminSancListTemp = adminSanctionService.getCeoAdminSancRequests(grplist, lStrUserState);
				if(adminSancListTemp!=null)
					request.setAttribute("sanctionsListSize", adminSancListTemp.size());
				else
					request.setAttribute("sanctionsListSize", "0");
				
				 List<SQLChangeMgmtTransVO> myCrRqstList =ceoWorklistService.getCeoWorkList(lStrUserState);

	        	  
	        	  if(myCrRqstList!=null)
	        	  {
						request.setAttribute("crIdList", myCrRqstList.size());
	        	  }
					else
					{
						request.setAttribute("crIdList", "0");
					}
	        	  //Empanelment
	        	  List<EmpanelHospVO> ceoWorklist=CeoWorkListServiceObj.getCeoWorkList(lStrUserState,"");
	        	  if(ceoWorklist.size() > 0)
	        		  request.setAttribute("EmpnlListSize", ceoWorklist.size());
	        	  else
	        		  request.setAttribute("EmpnlListSize","0");
	        	  String ceoGrp="GP16";
				  List<EdcVO> edcCeoWorklist=CeoWorkListServiceObj.getEdcCeoWorklist(ceoGrp, lStrUserState);
				  if(edcCeoWorklist.size() > 0)
	        		  request.setAttribute("EDCListSize", edcCeoWorklist.size());
	        	  else
	        		  request.setAttribute("EDCListSize","0");
				  List<EmpanelHospVO> OthrStCeoWorklist=CeoWorkListServiceObj.getCeoWorkList(lStrUserState,"othrSt");
	        	  if(OthrStCeoWorklist.size() > 0)
	        		  request.setAttribute("OthrStListSize", OthrStCeoWorklist.size());
	        	  else
	        		  request.setAttribute("OthrStListSize","0");
				  
				  
				  //operations
				  int count=0;
				  count=CeoWorkListServiceObj.operationsWorkflow(ceoGrp,lStrUserState);
				  if(count>0)
					  request.setAttribute("OperationsListSize",count); 
				  else
					  request.setAttribute("OperationsListSize","0"); 
					  
			return mapping.findForward("dashBoard");
		}
		

		// To save admin sanction details
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("saveAdminSancDetails")) {
			AdminSanctionVO adminSanctionVO = new AdminSanctionVO();
			String nextWorkFlowId = null;
			String currentOwner = null;
			String sanctionId = null;
			String buttonValue = request.getParameter("buttonValue");

			if (lStrUserState.equals(config.getString("ACC.APTGState"))) {
				if (adminSanctionForm.getScheme().equals(
						config.getString("ACC.EHFAPSchemeID"))) {
					stateSeqId = "AP";
				}
				if (adminSanctionForm.getScheme().equals(
						config.getString("ACC.EHFTGSchemeID"))) {
					stateSeqId = "TG";
				}
			}

			if (adminSanctionForm.getSanctionId() == null
					|| "".equals(adminSanctionForm.getSanctionId())) {
				sanctionId = adminSanctionService.getSanctionId(stateSeqId);
				adminSanctionForm.setSanctionId(sanctionId);
			} else
				sanctionId = adminSanctionForm.getSanctionId();

			adminSanctionVO.setIssuingAuthority(adminSanctionForm.getIssuingAuthority());
			adminSanctionVO.setIssuingAuthorityName(adminSanctionForm
					.getIssuingAuthorityName());
			try {
				adminSanctionVO.setDate(sdf.parse(adminSanctionForm.getDate()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			adminSanctionVO.setSubject(adminSanctionForm.getSubject());
			adminSanctionVO.setDeptName(adminSanctionForm.getDeptName());
			adminSanctionVO.setReference(adminSanctionForm.getReference());
			if (lStrUserState.equals(config.getString("ACC.APTGState")))
				adminSanctionVO.setScheme(adminSanctionForm.getScheme());
			else {
				adminSanctionVO.setScheme((adminSanctionForm.getScheme()
						.equals(config.getString("ACC.EHFAP"))) ? config
						.getString("ACC.EHFAPSchemeID") : config
						.getString("ACC.EHFTGSchemeID"));
			}

			adminSanctionVO.setSanctionAmount(adminSanctionForm
					.getSanctionAmount());
			adminSanctionVO
					.setDetailedHead(adminSanctionForm.getDetailedHead());
			adminSanctionVO.setDetailedHeadName(adminSanctionForm
					.getDetailedHeadName());
			adminSanctionVO.setSubHead(adminSanctionForm.getSubHead());
			adminSanctionVO.setSubHeadName(adminSanctionForm.getSubHeadName());
			adminSanctionVO.setMajorHead(adminSanctionForm.getMajorHead());
			adminSanctionVO.setMajorHeadName(adminSanctionForm
					.getMajorHeadName());
			adminSanctionVO
					.setSourceOfBudget(adminSanctionForm.getSourceName());
			adminSanctionVO.setSourceCode(adminSanctionForm.getSourceCode());

			if (adminSanctionForm.getSanctionOrderDate().equals("")
					|| adminSanctionForm.getSanctionOrderDate() == "")
				adminSanctionVO.setSanctionOrderDate(null);
			else
				try {
					adminSanctionVO.setSanctionOrderDate(sdf
							.parse(adminSanctionForm.getSanctionOrderDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}

			if (adminSanctionForm.getPurchaseDate().equals("")
					|| adminSanctionForm.getPurchaseDate() == "")
				adminSanctionVO.setPurchaseDate(null);
			else
				try {
					adminSanctionVO.setPurchaseDate(sdf.parse(adminSanctionForm
							.getPurchaseDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}

			adminSanctionVO.setInspectionAuthority(adminSanctionForm
					.getInspectionAuthority());
			adminSanctionVO.setExecutingAuthority(adminSanctionForm
					.getExecutingAuthority());
			adminSanctionVO.setVendorType(adminSanctionForm.getVendorType());
			adminSanctionVO.setVendorName(adminSanctionForm.getvName());
			adminSanctionVO.setVendorCode(adminSanctionForm.getVendorName());
			adminSanctionVO
					.setToBeIssuedOn(adminSanctionForm.getToBeIssuedOn());

			adminSanctionVO.setTypoOfSanction(adminSanctionForm
					.getTypoOfSanction());

			adminSanctionVO.setSpecification(adminSanctionForm
					.getSpecification());
			specString = adminSanctionForm.getSpecification();
			request.setAttribute("specString", specString);
			adminSanctionVO.setCost(adminSanctionForm.getCost());
			costString = adminSanctionForm.getCost();
			request.setAttribute("costString", costString);
			adminSanctionForm.setSpecFlag("Y");

			adminSanctionVO.setDeptName(adminSanctionForm.getDeptName());

			adminSanctionVO.setCrtUsr(lStrEmpId);
			adminSanctionVO.setLstUpdUsr(lStrEmpId);
			adminSanctionVO.setSanctionId(sanctionId);

			String grpId = "";
			String workFlowId = "";
			// To save workflow information
			if ((buttonValue.equalsIgnoreCase("Submit"))
					|| (buttonValue.equalsIgnoreCase("Update"))) {

				String prevworkFlowId = adminSanctionService
						.getWorkFlowId(sanctionId);
				grpId = adminSanctionService.getGrpId(prevworkFlowId);
				adminSanctionVO.setGrpId(grpId);
				nextWorkFlowId = workFlowCommonService.getNextWorkFlowId(
						prevworkFlowId, buttonValue);
				currentOwner = workFlowCommonService
						.getCurrenttOwnerForNewWorkFlow(nextWorkFlowId);
				if (adminSanctionForm.getAdminSancRemarks() != null
						&& !(adminSanctionForm.getAdminSancRemarks()
								.equalsIgnoreCase(""))) {

					adminSanctionVO.setAdminSancRemarks(adminSanctionForm
							.getAdminSancRemarks());
				} else {
					adminSanctionVO
							.setAdminSancRemarks("Admin Sanction WorkFlow Initiated");
				}

				if (buttonValue.equalsIgnoreCase("Submit")) {
					alertMsg = "Submitted";

				} else if (buttonValue.equalsIgnoreCase("Update")) {
					alertMsg = "Updated";

				}

				adminSanctionVO.setWorkFlowName(nextWorkFlowId);
				adminSanctionVO.setCurrentOwner(currentOwner);
				adminSanctionVO.setWorkFlowId(nextWorkFlowId);
				adminSanctionVO.setPrevWorkFlowId(prevworkFlowId);
				adminSanctionVO.setPrevOwner(grpId);
				adminSanctionVO.setButtonValue(buttonValue);

			}
			// To save workflow information
			if (buttonValue.equalsIgnoreCase("SaveBasic")) {
				workFlowId = config.getString("AdminSanctionInit.WorkFlowId")
						.trim();
				adminSanctionVO.setWorkFlowId(workFlowId);
				grpId = adminSanctionService.getGrpId(workFlowId);
				adminSanctionVO.setGrpId(grpId);

				nextWorkFlowId = workFlowCommonService.getNextWorkFlowId(
						workFlowId, buttonValue);
				currentOwner = workFlowCommonService
						.getCurrenttOwnerForNewWorkFlow(nextWorkFlowId);

				adminSanctionVO.setWorkFlowName(nextWorkFlowId);
				adminSanctionVO.setCurrentOwner(currentOwner);
				adminSanctionVO.setButtonValue(buttonValue);

			}
			String successMsg = adminSanctionService
					.saveSanctionDetails(adminSanctionVO);
			if (buttonValue.equalsIgnoreCase("SaveBasic")) {
				request.setAttribute("successMsg", successMsg);
				alertMsg = "absent";
			}
			request.setAttribute("alertMsg", alertMsg);

			if (lStrUserState.equals(config.getString("ACC.APTGState"))) {
				schemeList = adminSanctionService.getSchemeList(lStrUserState);
				lstrSchemeId = adminSanctionForm.getScheme();
			}

			detailedHeadsList = adminSanctionService
					.getDetailedHeadList(lstrSchemeId);
			vendorNamesList = adminSanctionService.getVendorList();
			sourceOfBudgetList = adminSanctionService
					.getBudgetSourceList(lStrUserState);
			if (vendorNamesList.size() > 0) {
				for (int i = 0; i < vendorNamesList.size(); i++) {
					labelBean = new LabelBean();
					labelBean.setID(vendorNamesList.get(i).getVALUE());
					labelBean.setVALUE(vendorNamesList.get(i).getID());
					vendorCodesList.add(labelBean);
				}
			}
			issuingAuthNamesList = adminSanctionService.getIssuingAuthName(adminSanctionForm.getIssuingAuthority(),lStrUserState);
			request.setAttribute("issuingAuthNamesList", issuingAuthNamesList);
			request.setAttribute("showDetailedList", showDetailedList);
			request.setAttribute("deptNamesList", deptList);
			request.setAttribute("listSchemes", schemeList);
			request.setAttribute("detailedHeadsList", detailedHeadsList);
			request.setAttribute("sourceOfBudgetList", sourceOfBudgetList);
			request.setAttribute("vendorNamesList", vendorNamesList);
			request.setAttribute("vendorCodesList", vendorCodesList);
			request.setAttribute("showPrint", showPrint);
			request.setAttribute("showRecApprBtn", showRecApprBtn);
			request.setAttribute("btnRecAppr", btnRecAppr);

			showSubmit = "display:block";
			request.setAttribute("submit", "Submit");
			request.setAttribute("showSubmit", showSubmit);
			request.setAttribute("showSave", showSave);
			request.setAttribute("showReset", showReset);
			showRemarksTextArea = "display:block";
			request.setAttribute("showRemarksTextArea", showRemarksTextArea);

			request.setAttribute("showUpdateBtn", showUpdateBtn);
			request.setAttribute("showActionBtn1", showActionBtn1);
			request.setAttribute("showActionBtn", showActionBtn);
			request.setAttribute("showRemarks", showRemarks);

			lStrResultPage = "sanctionForm";

		}

		// For tracking status of the sanction requests
		if (lStrFlagStatus != null
				&& "viewSanctionStatus".equals(lStrFlagStatus)) {
			List<AdminSanctionVO> sancRequestList = null;
			sancRequestList = adminSanctionService.findSanctionRequestStatus(lStrUserState,lStrEmpId);
			sancRequestList = adminSanctionService.getAdminSancworkList(sancRequestList);
			adminSanctionForm.setAdminSancList(sancRequestList);
			String crtUsr=adminSanctionService.verifyIfDyEo(grplist);
			if(crtUsr.equalsIgnoreCase("Y"))
			  {
				 adminSanctionForm.setStatusFlag("Initiate");
			  }
			lStrResultPage = "trackStatus";
		}
		
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("viewSelectAttach"))
             
        {
			 try
		        { 
      	  File file = null ;
            FileInputStream fis = null ;
            DataInputStream dis = null ;
            String lStrFileType=null;
            String lStrContentType=null;
            ServletOutputStream out = response.getOutputStream();
      	  String lStrFileName=request.getParameter("fileName").toString(); 
      	  String lStrtransid=request.getParameter("TransId").toString();
      	 String path= adminSanctionService.getAttachPath(lStrtransid,lStrFileName);
           if ( path != null )
           {
             int pos=lStrFileName.lastIndexOf(".")+1;
             lStrFileType = lStrFileName.substring(pos);
             byte[] lbBytes = null ;
             String attachMode="attachment";
	          if(lStrFileType.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPG) || lStrFileType.equalsIgnoreCase
               (ASRIConstants.FILE_EXT_JPEG) || lStrFileType.equalsIgnoreCase(ASRIConstants.FILE_EXT_GIF)){
	              attachMode="inline";
	          }
	         lStrContentType=ASRIConstants.FILE_EXT.get(lStrFileType); 
	        
	        	  
	            if ( lStrFileName != null && !lStrFileName.equals ( "" ) )
	            {
	              file = new File ( path ) ; 
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
	              lbBytes = new byte[0] ;
	            }
	          
	         
           }
		        }
			 catch(Exception e)
	          {
	        	  e.getMessage();
	        	  e.printStackTrace();
	          }
           return mapping.findForward(null) ;  
  }

		// To retrieve all the sanction details
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("getSanctionDetails")) {
			String sanctionId = request.getParameter("sanctionId");
			EhfAdminSanctionsMetaData ehfAdminSanctionMetaData = adminSanctionService
					.getSanctionDeatils(sanctionId);
			adminSanctionForm.setShowBack("show");
			request.setAttribute("showPrint", showPrint);
			showBack = "display:block";
			request.setAttribute("showBack", showBack);
			request.setAttribute("alertMsg", alertMsg);
			adminSanctionForm.setSanctionId(sanctionId);
			if (ehfAdminSanctionMetaData != null) {
				adminSanctionForm.setIssuingAuthority(ehfAdminSanctionMetaData
						.getIssuingAuthority());
				adminSanctionForm
						.setIssuingAuthorityName(ehfAdminSanctionMetaData
								.getIssuingAuthorityName());
				adminSanctionForm.setDate(sdf.format(ehfAdminSanctionMetaData
						.getFormDate()));
				adminSanctionForm.setSubject(ehfAdminSanctionMetaData
						.getSubject());
				adminSanctionForm.setDeptName(ehfAdminSanctionMetaData
						.getDeptName());
				adminSanctionForm.setReference(ehfAdminSanctionMetaData
						.getReference());
				adminSanctionForm.setScheme(ehfAdminSanctionMetaData
						.getScheme());

				adminSanctionForm.setSanctionAmount(ehfAdminSanctionMetaData
						.getSanctionAmount());
				adminSanctionForm.setDetailedHead(ehfAdminSanctionMetaData
						.getDetailedHead());
				adminSanctionForm.setDetailedHeadName(ehfAdminSanctionMetaData
						.getDetailedHeadName());
				adminSanctionForm.setSubHead(ehfAdminSanctionMetaData
						.getSubHead());
				adminSanctionForm.setSubHeadName(ehfAdminSanctionMetaData
						.getSubHeadName());
				adminSanctionForm.setMajorHead(ehfAdminSanctionMetaData
						.getMajorHead());
				adminSanctionForm.setMajorHeadName(ehfAdminSanctionMetaData
						.getMajorHeadName());
				adminSanctionForm.setSourceOfBudget(ehfAdminSanctionMetaData
						.getSourceCode());
				adminSanctionForm.setSourceCode(ehfAdminSanctionMetaData
						.getSourceCode());
				adminSanctionForm.setSourceName(ehfAdminSanctionMetaData
						.getSourceOfBudget());

				if (ehfAdminSanctionMetaData.getSanctionOrderDate() == null
						|| ehfAdminSanctionMetaData.getSanctionOrderDate()
								.equals(""))
					adminSanctionForm.setSanctionOrderDate(null);
				else
					try {
						adminSanctionForm.setSanctionOrderDate(sdf
								.format(ehfAdminSanctionMetaData
										.getSanctionOrderDate()));
					} catch (Exception e) {

						e.printStackTrace();
					}

				if (ehfAdminSanctionMetaData.getPurchaseDate() == null
						|| ehfAdminSanctionMetaData.getPurchaseDate()
								.equals(""))
					adminSanctionForm.setPurchaseDate(null);
				else
					try {
						adminSanctionForm.setPurchaseDate(sdf
								.format(ehfAdminSanctionMetaData
										.getPurchaseDate()));
					} catch (Exception e) {

						e.printStackTrace();
					}
				adminSanctionForm
						.setInspectionAuthority(ehfAdminSanctionMetaData
								.getInspectionAuthority());
				adminSanctionForm
						.setExecutingAuthority(ehfAdminSanctionMetaData
								.getExecutingAuthority());
				adminSanctionForm.setVendorType(ehfAdminSanctionMetaData
						.getVendorType());
				adminSanctionForm.setVendorName(ehfAdminSanctionMetaData
						.getVendorCode());
				adminSanctionForm.setVendorCode(ehfAdminSanctionMetaData
						.getVendorCode());
				adminSanctionForm.setvName(ehfAdminSanctionMetaData
						.getVendorName());
				adminSanctionForm.setToBeIssuedOn(ehfAdminSanctionMetaData
						.getToBeIssuedOn());

				adminSanctionForm.setTypoOfSanction(ehfAdminSanctionMetaData
						.getTypoOfSanction());

				adminSanctionForm.setSpecification(ehfAdminSanctionMetaData
						.getSpecification());
				specString = adminSanctionForm.getSpecification();
				request.setAttribute("specString", specString);
				adminSanctionForm.setCost(ehfAdminSanctionMetaData.getCost());
				costString = adminSanctionForm.getCost();
				request.setAttribute("costString", costString);
				adminSanctionForm.setSpecFlag("Y");
				adminSanctionForm.setDeptName(ehfAdminSanctionMetaData
						.getDeptName());

				String prevDetails = adminSanctionService
						.decideLabelValue(sanctionId);
				String workFlowId = adminSanctionService
						.getWorkFlowId(sanctionId);
				String workFlowIdConfig = config.getString(
						"AdminSanctionInit.WorkFlowId").trim();
				request.setAttribute("disable", "true");

				if (workFlowIdConfig.equalsIgnoreCase(workFlowId)) {

					readonly = "false";
					request.setAttribute("readonly", readonly);
					request.setAttribute("disabled", disabled);
					request.setAttribute("butDisabled", butDisabled);
					showRemarksTextArea = "display:block";
					showRemarks = "display:''";
					request.setAttribute("showRemarks", showRemarks);
					request.setAttribute("showRemarksTextArea",
							showRemarksTextArea);
					request.setAttribute("showActionBtn", showActionBtn);
					request.setAttribute("showActionBtn1", showActionBtn1);
					request.setAttribute("showRecApprBtn", showRecApprBtn);
					request.setAttribute("btnRecAppr", btnRecAppr);
					if (prevDetails.equalsIgnoreCase("Incomplete")) {
						showSubmit = "display:block";
						request.setAttribute("showSave", showSave);
						request.setAttribute("submit", "Submit");
						request.setAttribute("showSubmit", showSubmit);

						request.setAttribute("showUpdateBtn", showUpdateBtn);
					} else {
						List<AdminSanctionRemarksVO> remarksList = adminSanctionService
								.getPreviousRemarks(sanctionId);
						adminSanctionForm.setRemarksList(remarksList);
						showUpdateBtn = "display:block";
						request.setAttribute("btnLbl", "Update");
						request.setAttribute("showSubmit", showSubmit);
						request.setAttribute("showSave", showSave);
						request.setAttribute("showUpdateBtn", showUpdateBtn);
						request.setAttribute("showSubBtn", showSubBtn);
						// adminSanctionForm.setAttachFlag("Present");
						// showResetBtn="display:block";
						// request.setAttribute("showReset",showReset);
						// request.setAttribute("showResetBtn",showResetBtn);

					}
				}

				else {

					List<AdminSanctionRemarksVO> remarksList = adminSanctionService
							.getPreviousRemarks(sanctionId);
					adminSanctionForm.setRemarksList(remarksList);

					// For EO Approval
					workFlowIdConfig = config.getString(
							"AdminSanctionEOApproval.WorkFlowId").trim();
					Long sancAmount = adminSanctionService
							.getSanctionAmountByMonth();
					
					Long eoSanctionLimit=null;
					if (lStrUserState.equals(config.getString("ACC.TGState"))) {
						eoSanctionLimit=Long.parseLong(config.getString("ADMIN_SANC_TG_AMOUNT").trim());
					}
					else
					{
						eoSanctionLimit=Long.parseLong(config.getString("ADMIN_SANC_AP_AMOUNT").trim());
					}
					
					Long reqAmount = Long.parseLong(ehfAdminSanctionMetaData
							.getSanctionAmount());
					if (workFlowIdConfig.equalsIgnoreCase(workFlowId)
							&& (sancAmount >= eoSanctionLimit || reqAmount >= eoSanctionLimit)) {
						adminSanctionForm.setAmtSanc(sancAmount);
						btnRecAppr = "recApprove";
						showRecApprBtn = "";
					} else if (workFlowIdConfig.equalsIgnoreCase(workFlowId)
							&& (sancAmount < eoSanctionLimit && reqAmount < eoSanctionLimit)) {
						adminSanctionForm.setAmtSanc(sancAmount);
						if (sancAmount + reqAmount > eoSanctionLimit) {
							btnRecAppr = "recApprove";
							showRecApprBtn = "";
						} else {
							btnRecAppr = "recApprove";
							showRecApprBtn = "";
							btnAppr = "Approve";
							showActionBtn = "";
						}
					} else {
						btnAppr = "Approve";
						showActionBtn = "";

					}
					btnPen = "Pending";
					btnRej = "Reject";
					showActionBtn1 = "";
					request.setAttribute("showRecApprBtn", showRecApprBtn);
					request.setAttribute("btnRecAppr", btnRecAppr);
					request.setAttribute("btnAppr", btnAppr);

					adminSanctionForm.setAttachFlag("Present");
					request.setAttribute("showReset", showReset);

					request.setAttribute("btnPen", btnPen);
					request.setAttribute("btnRej", btnRej);
					request.setAttribute("showSave", showSave);
					request.setAttribute("showSubmit", showSubmit);
					request.setAttribute("showResetBtn", showResetBtn);
					request.setAttribute("showActionBtn1", showActionBtn1);
					request.setAttribute("showActionBtn", showActionBtn);
					request.setAttribute("showUpdateBtn", showUpdateBtn);
					readonly = "true";
					request.setAttribute("readonly", readonly);
					disabled = "true";
					butDisabled = "disabled";
					request.setAttribute("disabled", disabled);
					request.setAttribute("butDisabled", butDisabled);
					
					
					 if(session.getAttribute("dsgnName")!=null  && session.getAttribute("dsgnName").equals(config.getString("ACC.CEO")))
					 {  
		                      List<LabelBean> fileNames = adminSanctionService.viewAttachments(sanctionId);
			            	  request.setAttribute("fileNames", fileNames);
			            	      if(fileNames.size()>0)
			            	       {
			            	    	 request.setAttribute("showFiles","Y");
			            	       }
					 }
				}
			}

			if (lStrUserState.equals(config.getString("ACC.APTGState"))) {
				schemeList = adminSanctionService.getSchemeList(lStrUserState);
				lstrSchemeId = adminSanctionForm.getScheme();
			}
			issuingAuthNamesList = adminSanctionService.getIssuingAuthName(adminSanctionForm.getIssuingAuthority(),lStrUserState);
			request.setAttribute("issuingAuthNamesList", issuingAuthNamesList);
			detailedHeadsList = adminSanctionService
					.getDetailedHeadList(lstrSchemeId);
			vendorNamesList = adminSanctionService.getVendorList();
			sourceOfBudgetList = adminSanctionService
					.getBudgetSourceList(lStrUserState);
			if (vendorNamesList.size() > 0) {
				for (int i = 0; i < vendorNamesList.size(); i++) {
					labelBean = new LabelBean();
					labelBean.setID(vendorNamesList.get(i).getID());
					labelBean.setVALUE(vendorNamesList.get(i).getID());
					vendorCodesList.add(labelBean);
				}
			}
			request.setAttribute("showDetailedList", showDetailedList);
			request.setAttribute("deptNamesList", deptList);
			request.setAttribute("listSchemes", schemeList);
			request.setAttribute("detailedHeadsList", detailedHeadsList);
			request.setAttribute("sourceOfBudgetList", sourceOfBudgetList);
			request.setAttribute("vendorNamesList", vendorNamesList);
			request.setAttribute("vendorCodesList", vendorCodesList);
			lStrResultPage = "sanctionForm";

		}

		// To load the sanction form initially
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("sanctionForm")) {

			showSave = "display:block";
			showReset = "display:block";
			request.setAttribute("showPrint", showPrint);
			request.setAttribute("showReset", showReset);
			request.setAttribute("btnLbl", "SaveBasic");
			request.setAttribute("showSave", showSave);
			request.setAttribute("showSubmit", showSubmit);
			request.setAttribute("showRemarksTextArea", showRemarksTextArea);
			request.setAttribute("showActionBtn", showActionBtn);
			request.setAttribute("showActionBtn1", showActionBtn1);
			request.setAttribute("showRemarks", showRemarks);
			request.setAttribute("showUpdateBtn", showUpdateBtn);
			request.setAttribute("showSubBtn", showSubBtn);
			request.setAttribute("showResetBtn", showResetBtn);
			request.setAttribute("viewCR", "hide");
			request.setAttribute("showClose", showClose);
			request.setAttribute("disable", "false");
			request.setAttribute("alertMsg", alertMsg);
			request.setAttribute("showRecApprBtn", showRecApprBtn);
			request.setAttribute("btnRecAppr", btnRecAppr);
			if (lStrUserState.equals(config.getString("ACC.APTGState"))) {
				schemeList = adminSanctionService.getSchemeList(lStrUserState);
			} else {
				detailedHeadsList = adminSanctionService
						.getDetailedHeadList(lstrSchemeId);
				showDetailedList = "Y";
			}
			vendorNamesList = adminSanctionService.getVendorList();
			sourceOfBudgetList = adminSanctionService
					.getBudgetSourceList(lStrUserState);
			if (vendorNamesList.size() > 0) {
				for (int i = 0; i < vendorNamesList.size(); i++) {
					labelBean = new LabelBean();
					labelBean.setID(vendorNamesList.get(i).getID());
					labelBean.setVALUE(vendorNamesList.get(i).getID());
					vendorCodesList.add(labelBean);
				}
			}
			request.setAttribute("showDetailedList", showDetailedList);
			request.setAttribute("deptNamesList", deptList);
			request.setAttribute("listSchemes", schemeList);
			request.setAttribute("detailedHeadsList", detailedHeadsList);
			request.setAttribute("sourceOfBudgetList", sourceOfBudgetList);
			request.setAttribute("vendorNamesList", vendorNamesList);
			request.setAttribute("vendorCodesList", vendorCodesList);

			lStrResultPage = "sanctionForm";
		}

		alertMsg = "absent";
		request.setAttribute("alertMsg", alertMsg);
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("submitAdminSancDetailsCEO")) {
			String buttonValue = request.getParameter("buttonValue");
			String sanctionIdString = request.getParameter("sancId");
			String[] sanctionIdsArray=null;
			if(sanctionIdString!=null && !sanctionIdString.equals(""))
			sanctionIdsArray=sanctionIdString.split("~");
			String successMsg ="";
			for(int i=1;i<sanctionIdsArray.length;i++)
			{
				String prevworkFlowId = adminSanctionService.getWorkFlowId(sanctionIdsArray[i]);
				AdminSanctionVO adminSanctionVO = new AdminSanctionVO();
				adminSanctionVO.setSanctionId(sanctionIdsArray[i]);
				String grpId = adminSanctionService.getGrpId(prevworkFlowId);
				String nextWorkFlowId = workFlowCommonService.getNextWorkFlowId(prevworkFlowId, buttonValue);
				String currentOwner ="";
				if (nextWorkFlowId.equalsIgnoreCase("NA")) {
					currentOwner = "";
				} else {
					currentOwner = workFlowCommonService
							.getCurrenttOwnerForNewWorkFlow(nextWorkFlowId);
				}
				
				if (buttonValue.equalsIgnoreCase("Pending")) {
					alertMsg = "Updated to pending";
				}
				if (buttonValue.equalsIgnoreCase("Approve")) {
					alertMsg = "Approved";
				}
				if (buttonValue.equalsIgnoreCase("Reject")) {
					alertMsg = "Rejected";
				}
				
				adminSanctionVO.setCrtUsr(lStrEmpId);
				adminSanctionVO.setLstUpdUsr(lStrEmpId);
				adminSanctionVO.setCurrentOwner(currentOwner);
				adminSanctionVO.setWorkFlowId(nextWorkFlowId);
				adminSanctionVO.setPrevWorkFlowId(prevworkFlowId);
				adminSanctionVO.setPrevOwner(grpId);
				adminSanctionVO.setButtonValue(buttonValue);
				adminSanctionVO.setGrpId(grpId);
				adminSanctionVO.setAdminSancRemarks(alertMsg+" successfully");

				 successMsg = adminSanctionService.saveSanctionDetails(adminSanctionVO);
				if(!successMsg.equalsIgnoreCase("success"))
					break;
				System.out.println(successMsg);
			}
			if(!successMsg.equalsIgnoreCase("success"))
				alertMsg="failure";
				request.setAttribute("alertMsg", alertMsg);
				
				lStrFlagStatus="sanctionWorkflow";
			
		}
		
		
		// To get the requests that are in a particular workflow
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("sanctionWorkflow")) {
			List<AdminSanctionVO> adminSancList = null;
			List<AdminSanctionVO> adminSancListTemp = null;
			String userId = request.getParameter("userId");
			adminSanctionForm.setUserId(userId);
			session.setAttribute("userId", userId);
			adminSancListTemp = adminSanctionService.getAdminSancRequests(grplist, lStrUserState);
			 if(session.getAttribute("dsgnName")!=null  && session.getAttribute("dsgnName").equals(config.getString("ACC.CEO")))
			 {
				 adminSancListTemp = adminSanctionService.getCeoAdminSancRequests(grplist, lStrUserState);
				 adminSanctionForm.setAdminSancList(adminSancListTemp);
				
	    			
	    					int  totalRecords=1;
	    	    			int pageNo=1;
	    	    			int  showPage=1;;
	    	    			int rowsPerPage=10;
	    	    			int pages=1;
	    	    			int startIndex=1;
	    	    			int endIndex=5;
	    	    			int startPage=1;
	    	    			int endPage=10;
	    			if(adminSanctionForm.getAdminSancList()!=null &&adminSanctionForm.getAdminSancList().size()>0){
	    				
	    				
	    				totalRecords=adminSanctionForm.getAdminSancList().size();
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
	    								adminSanctionForm.setNext("next");
	    								
	    							}
	    				              if(pages>10 && (showPage-10)>1){
	    								
	    								request.setAttribute("prev", "prev");
	    								adminSanctionForm.setPrev("prev");
	    								
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
	    						
	    						if(pages>10 && pages>(showPage+10)){
	    							
	    							
	    							request.setAttribute("next", "next");
	    							adminSanctionForm.setNext("next");
	    							
	    						}
	    						
	    						if(pages>10 && (showPage-10)>1){
	    							request.setAttribute("prev", "prev");
	    							adminSanctionForm.setPrev("prev");
	    						}
	    						
	    						
	    						
	    					}
	    					
	    					if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
	    						
	    						int x=adminSanctionForm.getStartPage();
	    						
	    						int y=adminSanctionForm.getEndPage();
	    						if(x>=10 && pages>10){
	    							
	    							request.setAttribute("next", "next");
	    							adminSanctionForm.setNext("next");
	    							
	    						}
	    						
	    						if(x-10>10 && pages>10){
	    							
	    							request.setAttribute("prev", "prev");
	    							adminSanctionForm.setPrev("prev");
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
	    						
	    						
	    						adminSanctionForm.setStartPage(startPage);
	    						adminSanctionForm.setEndPage(endPage);
	    						
	    			startIndex=((showPage-1)*rowsPerPage)+1;
	    						
	    						if((showPage*rowsPerPage)<totalRecords){
	    							
	    							
	    							endIndex=showPage*rowsPerPage;
	    							
	    						}else{
	    							
	    							endIndex=totalRecords;
	    							
	    						}
	    					}
	    					
	    					
	    					if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
	    						
	    		                int x=adminSanctionForm.getStartPage();
	    						
	    						int y=adminSanctionForm.getEndPage();
	    					
	    						
	    						startPage=y+1;
	    						endPage=y+10;
	    						if(endPage>pages){
	    							
	    							endPage=pages;
	    						}
	    						
	    						if(pages>startPage+10){
	    							request.setAttribute("next", "next");
	    							adminSanctionForm.setNext("next");
	    							
	    						}
	    						
	    						if(startPage-10>=1){
	    							
	    							request.setAttribute("prev", "prev");
	    							adminSanctionForm.setPrev("prev");
	    						}
	    						adminSanctionForm.setStartPage(startPage);
	    						adminSanctionForm.setEndPage(endPage);
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
			List<AdminSanctionVO> listVo=new ArrayList<AdminSanctionVO>();
	    					
	    				    for(int j=startIndex;j<endIndex;j++){
	    						
	    				    	AdminSanctionVO vo=new AdminSanctionVO();
	                        	
	                        vo.setSANCAMOUNT(adminSancListTemp.get(j).getSANCAMOUNT());
	                        vo.setSANCTIONID(adminSancListTemp.get(j).getSANCTIONID());
	                        vo.setSUBJECT(adminSancListTemp.get(j).getSUBJECT());
	                        vo.setACCCODE(adminSancListTemp.get(j).getACCCODE());
	                        vo.setVENDORNAME(adminSancListTemp.get(j).getVENDORNAME());
	                        	
	                        	
	                        	listVo.add(vo);
	                        
	    				}
	    				    
	    				    if(listVo!=null && listVo.size()>0){
	    				    	
	    				    	adminSanctionForm.setAdminSancList(listVo);
	    				    	
	    				    }
	    				
	    				    request.setAttribute("liPageNo",showPage);
	    					if(adminSanctionForm.getAdminSancList().size()==0){
	    						request.setAttribute("startIndex", 0);
	    					}else{
	    						
	    						request.setAttribute("startIndex", startIndex+1);
	    					}
	    					if(adminSanctionForm.getAdminSancList().size()==0){
	    						request.setAttribute("endIndex", 0);
	    					}else{
	    						request.setAttribute("endIndex", endIndex);
	    						
	    					}
	    					
	    					request.setAttribute("rowsPerPage", rowsPerPage);
	    					request.setAttribute("pages", pages);
	    					request.setAttribute("startPage", startPage);
	    					request.setAttribute("endPage", endPage);
	    					request.setAttribute("endPage", endPage);
	    					if(adminSanctionForm.getAdminSancList().size()==0){
	    						
	    						request.setAttribute("totalRecords", 0);
	    					}else{
	    						request.setAttribute("totalRecords", totalRecords);
	    					}
	    				
	    			
	    			 }
				
		              request.setAttribute("RowsDisplay",rowsPerPage);
		          request.setAttribute("TotalRecords",adminSanctionForm.getAdminSancList().size());
				 lStrResultPage = "sanctionWorkFlowCEO";
			 }
				 else
				 {
					 adminSancListTemp = adminSanctionService.getAdminSancRequests(grplist, lStrUserState);
			          adminSancList = adminSanctionService.getAdminSancworkList(adminSancListTemp);
		       	adminSanctionForm.setAdminSancList(adminSancList);
			lStrResultPage = "sanctionWorkFlow";
				 }
			
		}
		// ajax call for retrieving detailed head details
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("getDetailedHeads")) {

			lstrSchemeId = request.getParameter("scheme");
			if (lstrSchemeId != null && !"".equalsIgnoreCase(lstrSchemeId)) {
				detailedHeadsList = adminSanctionService
						.getDetailedHeadList(lstrSchemeId);
				request.setAttribute("detailedHeadsList", detailedHeadsList);
				List<String> detHeadsAjax = new ArrayList<String>();
				for (LabelBean labelBean1 : detailedHeadsList) {
					if (labelBean1.getID() != null
							&& labelBean1.getVALUE() != null)
						if (detHeadsAjax != null && detHeadsAjax.size() > 0) {
							detHeadsAjax.add("@" + labelBean1.getID() + "~"
									+ labelBean1.getVALUE());
						} else {
							detHeadsAjax.add(labelBean1.getID() + "~"
									+ labelBean1.getVALUE());
						}
				}
				request.setAttribute("AjaxMessage", detHeadsAjax);
				return mapping.findForward("ajaxResult");
			}
		}
		
		if(lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("getVendorsSchemewise"))
		{
			
			String state = request.getParameter("state");
			if (state != null && !"".equalsIgnoreCase(state)) {
			vendorNamesList = adminSanctionService.getVendorList();
			request.setAttribute("vendorNamesList", vendorNamesList);
			List<String> vendorsAjax = new ArrayList<String>();
			for (LabelBean labelBean1 : vendorNamesList) {
				if (labelBean1.getID() != null
						&& labelBean1.getVALUE() != null)
					if (vendorsAjax != null && vendorsAjax.size() > 0) {
						vendorsAjax.add("@" + labelBean1.getID() + "~"
								+ labelBean1.getVALUE());
					} else {
						vendorsAjax.add(labelBean1.getID() + "~"
								+ labelBean1.getVALUE());
					}
			}
			request.setAttribute("AjaxMessage", vendorsAjax);
			return mapping.findForward("ajaxResult");
			}
			
		}
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("getHeadDetails")) {

			if (lStrUserState.equals(config.getString("ACC.APTGState"))) {
				lstrSchemeId = request.getParameter("scheme");
			}

			String detailedHead = request.getParameter("detailedHead");
			String headDetails = adminSanctionService.getHeadsDetails(
					lstrSchemeId, detailedHead);
			request.setAttribute("AjaxMessage", headDetails);
			return mapping.findForward("ajaxResult");
		}
		// Ajax call to get issuing authority name
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("getIssuingAuthName")) {
			String issuingAuthority = request.getParameter("issuingAuthority");
			if (issuingAuthority != null && !"".equalsIgnoreCase(issuingAuthority)) {
				issuingAuthNamesList = adminSanctionService.getIssuingAuthName(issuingAuthority,lStrUserState);
				request.setAttribute("issuingAuthNamesList", issuingAuthNamesList);
				List<String> issuingNamesAjax = new ArrayList<String>();
				for (LabelBean labelBean1 : issuingAuthNamesList) {
					if (labelBean1.getID() != null
							&& labelBean1.getVALUE() != null)
						if (issuingNamesAjax != null && issuingNamesAjax.size() > 0) {
							issuingNamesAjax.add("@" + labelBean1.getID() + "~"
									+ labelBean1.getVALUE());
						} else {
							issuingNamesAjax.add(labelBean1.getID() + "~"
									+ labelBean1.getVALUE());
						}
				}
				request.setAttribute("AjaxMessage", issuingNamesAjax);
				return mapping.findForward("ajaxResult");
			}
		}

		// Ajax call to verify whether attachments added or not
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("checkAttachments")) {
			String sanctionId = request.getParameter("sanctionId");
			String status = adminSanctionService.getAttachStatus(sanctionId);
			request.setAttribute("AjaxMessage", status);
			return mapping.findForward("ajaxResult");

		}
		
		// To submit the sanction details in a workflow
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("submitAdminSancDetails")) {
			String buttonValue = request.getParameter("buttonValue");
			String sanctionId = request.getParameter("sanctionId");
			String prevworkFlowId = adminSanctionService
					.getWorkFlowId(sanctionId);
			if (buttonValue.equalsIgnoreCase("recApprove")) {
				prevworkFlowId = config.getString(
						"AdminSanctionEORecApproval.WorkFlowId").trim();
			}

			AdminSanctionVO adminSanctionVO = new AdminSanctionVO();
			adminSanctionVO.setSanctionId(sanctionId);
			String grpId = adminSanctionService.getGrpId(prevworkFlowId);

			request.setAttribute("showPrint", showPrint);
			showBack = "display:block";
			request.setAttribute("showBack", showBack);

			String nextWorkFlowId = null;
			String currentOwner = null;

			request.setAttribute("showBack", showBack);

			if (adminSanctionForm.getAdminSancRemarks() != null
					&& !(adminSanctionForm.getAdminSancRemarks()
							.equalsIgnoreCase(""))) {

				adminSanctionVO.setAdminSancRemarks(adminSanctionForm
						.getAdminSancRemarks());
			} else {
				adminSanctionVO
						.setAdminSancRemarks("Admin Sanction WorkFlow Initiated");
			}

			if ((buttonValue.equalsIgnoreCase("Pending"))
					|| (buttonValue.equalsIgnoreCase("Approve"))
					|| (buttonValue.equalsIgnoreCase("Reject"))
					|| (buttonValue.equalsIgnoreCase("recApprove"))) {
				nextWorkFlowId = workFlowCommonService.getNextWorkFlowId(
						prevworkFlowId, buttonValue);
				if (nextWorkFlowId.equalsIgnoreCase("NA")) {

					currentOwner = "";
				} else {
					currentOwner = workFlowCommonService
							.getCurrenttOwnerForNewWorkFlow(nextWorkFlowId);
				}
			}
			if (buttonValue.equalsIgnoreCase("Pending")) {
				alertMsg = "Updated to pending";
			}
			if (buttonValue.equalsIgnoreCase("Approve")) {

				alertMsg = "Approved";

			}
			if (buttonValue.equalsIgnoreCase("recApprove")) {

				alertMsg = "Recommended for approval";

			}
			if (buttonValue.equalsIgnoreCase("Reject")) {

				alertMsg = "Rejected";
			}
			if (buttonValue.equalsIgnoreCase("Submit")) {
				alertMsg = "Submitted";
				// buttonValue="Approve";
			}
			if (buttonValue.equalsIgnoreCase("Update")) {
				alertMsg = "Updated";
			}
			adminSanctionVO.setCrtUsr(lStrEmpId);
			adminSanctionVO.setLstUpdUsr(lStrEmpId);
			request.setAttribute("alertMsg", alertMsg);

			adminSanctionVO.setCurrentOwner(currentOwner);
			adminSanctionVO.setWorkFlowId(nextWorkFlowId);
			adminSanctionVO.setPrevWorkFlowId(prevworkFlowId);
			adminSanctionVO.setPrevOwner(grpId);
			adminSanctionVO.setButtonValue(buttonValue);
			adminSanctionVO.setGrpId(grpId);

			String successMsg = adminSanctionService
					.saveSanctionDetails(adminSanctionVO);
			System.out.println(successMsg);
			List<AdminSanctionRemarksVO> remarksList = adminSanctionService
					.getPreviousRemarks(sanctionId);
			adminSanctionForm.setRemarksList(remarksList);
			showRemarksTextArea = "display:block";
			showRemarks = "display:''";
			request.setAttribute("showRemarks", showRemarks);
			request.setAttribute("showRemarksTextArea", showRemarksTextArea);
			showActionBtn = "";
			showActionBtn1 = "";
			request.setAttribute("showActionBtn1", showActionBtn1);
			btnAppr = "Approve";
			btnPen = "Pending";
			btnRej = "Reject";
			adminSanctionForm.setAttachFlag("Present");
			request.setAttribute("showReset", showReset);
			request.setAttribute("btnAppr", btnAppr);
			request.setAttribute("btnPen", btnPen);
			request.setAttribute("btnRej", btnRej);
			request.setAttribute("showSave", showSave);
			request.setAttribute("showSubmit", showSubmit);
			request.setAttribute("showResetBtn", showResetBtn);
			request.setAttribute("showActionBtn", showActionBtn);
			request.setAttribute("showUpdateBtn", showUpdateBtn);
			request.setAttribute("showRecApprBtn", showRecApprBtn);
			request.setAttribute("btnRecAppr", btnRecAppr);
			readonly = "true";
			request.setAttribute("readonly", readonly);
			disabled = "true";
			butDisabled = "disabled";
			request.setAttribute("disabled", disabled);
			request.setAttribute("butDisabled", butDisabled);

			if (lStrUserState.equals(config.getString("ACC.APTGState"))) {
				schemeList = adminSanctionService.getSchemeList(lStrUserState);
				lstrSchemeId = adminSanctionForm.getScheme();
			}

			detailedHeadsList = adminSanctionService
					.getDetailedHeadList(lstrSchemeId);
			vendorNamesList = adminSanctionService.getVendorList();
			sourceOfBudgetList = adminSanctionService
					.getBudgetSourceList(lStrUserState);
			if (vendorNamesList.size() > 0) {
				for (int i = 0; i < vendorNamesList.size(); i++) {
					labelBean = new LabelBean();
					labelBean.setID(vendorNamesList.get(i).getVALUE());
					labelBean.setVALUE(vendorNamesList.get(i).getID());
					vendorCodesList.add(labelBean);
				}
			}
			request.setAttribute("issuingAuthNamesList", issuingAuthNamesList);
			request.setAttribute("showDetailedList", showDetailedList);
			request.setAttribute("deptNamesList", deptList);
			request.setAttribute("listSchemes", schemeList);
			request.setAttribute("detailedHeadsList", detailedHeadsList);
			request.setAttribute("sourceOfBudgetList", sourceOfBudgetList);
			request.setAttribute("vendorNamesList", vendorNamesList);
			request.setAttribute("vendorCodesList", vendorCodesList);
			lStrResultPage = "sanctionForm";

		}

		// To search the requests based on the search criteria
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("searchSancStatus")) {
			List<AdminSanctionVO> searchList = null;
			List<AdminSanctionVO> searchListTemp = null;

			String sanctionId = request.getParameter("sanctionId");
			String reqStatus = request.getParameter("reqStatus");
			String startDate = request.getParameter("strtdate");
			String endDate = request.getParameter("endDate");

			adminSanctionForm.setSanctionId(sanctionId);
			adminSanctionForm.setReqStatus(reqStatus);
			adminSanctionForm.setReqRaisedDtFrm(startDate);
			adminSanctionForm.setReqRaisedDtTo(endDate);

			String crtUsr=adminSanctionService.verifyIfDyEo(grplist);
			if(crtUsr.equalsIgnoreCase("Y"))
			  {
				 adminSanctionForm.setStatusFlag("Initiate");
			  }
			searchListTemp = adminSanctionService.findSancRequests(sanctionId,
					reqStatus, startDate, endDate, lStrUserState,lStrEmpId);
			searchList = adminSanctionService
					.getAdminSancworkList(searchListTemp);
			adminSanctionForm.setAdminSancList(searchList);
			lStrResultPage = "trackStatus";
		}
		
		
		
		// To view the details in track status of saction requests
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("viewDetailsById"))

		{
			String sanctionId = request.getParameter("sanctionId");
			String printFlag = request.getParameter("printFlag");

			EhfAdminSanctionsMetaData ehfAdminSanctionMetaData = adminSanctionService
					.getSanctionDeatils(sanctionId);
			request.setAttribute("alertMsg", alertMsg);
			showBack = "display:block";
			request.setAttribute("showBack", showBack);
			adminSanctionForm.setShowBack("show");

			if (ehfAdminSanctionMetaData != null) {
				adminSanctionForm.setIssuingAuthority(ehfAdminSanctionMetaData
						.getIssuingAuthority());
				adminSanctionForm
						.setIssuingAuthorityName(ehfAdminSanctionMetaData
								.getIssuingAuthorityName());
				adminSanctionForm.setDate(sdf.format(ehfAdminSanctionMetaData
						.getFormDate()));
				adminSanctionForm.setSubject(ehfAdminSanctionMetaData
						.getSubject());
				adminSanctionForm.setDeptName(ehfAdminSanctionMetaData
						.getDeptName());
				adminSanctionForm.setReference(ehfAdminSanctionMetaData
						.getReference());
				adminSanctionForm.setScheme(ehfAdminSanctionMetaData
						.getScheme());

				adminSanctionForm.setSanctionAmount(ehfAdminSanctionMetaData
						.getSanctionAmount());
				adminSanctionForm.setDetailedHead(ehfAdminSanctionMetaData
						.getDetailedHead());
				adminSanctionForm.setDetailedHeadName(ehfAdminSanctionMetaData
						.getDetailedHeadName());
				adminSanctionForm.setSubHead(ehfAdminSanctionMetaData
						.getSubHead());
				adminSanctionForm.setSubHeadName(ehfAdminSanctionMetaData
						.getSubHeadName());
				adminSanctionForm.setMajorHead(ehfAdminSanctionMetaData
						.getMajorHead());
				adminSanctionForm.setMajorHeadName(ehfAdminSanctionMetaData
						.getMajorHeadName());
				adminSanctionForm.setSourceOfBudget(ehfAdminSanctionMetaData
						.getSourceCode());
				adminSanctionForm.setSourceCode(ehfAdminSanctionMetaData
						.getSourceCode());
				adminSanctionForm.setSourceName(ehfAdminSanctionMetaData
						.getSourceOfBudget());

				if (ehfAdminSanctionMetaData.getSanctionOrderDate() == null
						|| ehfAdminSanctionMetaData.getSanctionOrderDate()
								.equals(""))
					adminSanctionForm.setSanctionOrderDate(null);
				else
					try {
						adminSanctionForm.setSanctionOrderDate(sdf
								.format(ehfAdminSanctionMetaData
										.getSanctionOrderDate()));
					} catch (Exception e) {

						e.printStackTrace();
					}

				if (ehfAdminSanctionMetaData.getPurchaseDate() == null
						|| ehfAdminSanctionMetaData.getPurchaseDate()
								.equals(""))
					adminSanctionForm.setPurchaseDate(null);
				else
					try {
						adminSanctionForm.setPurchaseDate(sdf
								.format(ehfAdminSanctionMetaData
										.getPurchaseDate()));
					} catch (Exception e) {

						e.printStackTrace();
					}
				adminSanctionForm
						.setInspectionAuthority(ehfAdminSanctionMetaData
								.getInspectionAuthority());
				adminSanctionForm
						.setExecutingAuthority(ehfAdminSanctionMetaData
								.getExecutingAuthority());
				adminSanctionForm.setVendorType(ehfAdminSanctionMetaData
						.getVendorType());
				adminSanctionForm.setVendorName(ehfAdminSanctionMetaData
						.getVendorCode());
				adminSanctionForm.setVendorCode(ehfAdminSanctionMetaData
						.getVendorCode());
				adminSanctionForm.setvName(ehfAdminSanctionMetaData
						.getVendorName());
				adminSanctionForm.setToBeIssuedOn(ehfAdminSanctionMetaData
						.getToBeIssuedOn());

				adminSanctionForm.setTypoOfSanction(ehfAdminSanctionMetaData
						.getTypoOfSanction());
				adminSanctionForm.setSpecification(ehfAdminSanctionMetaData
						.getSpecification());
				specString = adminSanctionForm.getSpecification();
				request.setAttribute("specString", specString);
				adminSanctionForm.setCost(ehfAdminSanctionMetaData.getCost());
				costString = adminSanctionForm.getCost();
				request.setAttribute("costString", costString);
				adminSanctionForm.setSpecFlag("Y");
				adminSanctionForm.setDeptName(ehfAdminSanctionMetaData
						.getDeptName());

				if (!printFlag.equalsIgnoreCase("yes")) {
					showRemarks = "display:''";
					request.setAttribute("showRemarks", showRemarks);
					request.setAttribute("showRemarksTextArea",
							showRemarksTextArea);
					request.setAttribute("showSave", showSave);
					request.setAttribute("showSubmit", showSubmit);
					request.setAttribute("showActionBtn", showActionBtn);
					request.setAttribute("showActionBtn1", showActionBtn1);
					request.setAttribute("showUpdateBtn", showUpdateBtn);
					request.setAttribute("showRecApprBtn", showRecApprBtn);
					request.setAttribute("btnRecAppr", btnRecAppr);
					List<AdminSanctionRemarksVO> remarksList = adminSanctionService
							.getPreviousRemarks(sanctionId);
					adminSanctionForm.setRemarksList(remarksList);
					readonly = "true";
					request.setAttribute("readonly", readonly);
					disabled = "true";
					butDisabled = "disabled";
					request.setAttribute("disabled", disabled);
					request.setAttribute("butDisabled", butDisabled);
					request.setAttribute("showPrint", showPrint);

					String verifyFlag = adminSanctionService
							.verifyFinalLevelApproval(sanctionId);
					if (verifyFlag != null) {
						showPrint = "display:block";
						request.setAttribute("showPrint", showPrint);
					}
					if (lStrUserState.equals(config.getString("ACC.APTGState"))) {
						schemeList = adminSanctionService
								.getSchemeList(lStrUserState);
						lstrSchemeId = adminSanctionForm.getScheme();
					}

					detailedHeadsList = adminSanctionService
							.getDetailedHeadList(lstrSchemeId);
					vendorNamesList = adminSanctionService.getVendorList();
					sourceOfBudgetList = adminSanctionService
							.getBudgetSourceList(lStrUserState);
					if (vendorNamesList.size() > 0) {
						for (int i = 0; i < vendorNamesList.size(); i++) {
							labelBean = new LabelBean();
							labelBean.setID(vendorNamesList.get(i).getVALUE());
							labelBean.setVALUE(vendorNamesList.get(i).getID());
							vendorCodesList.add(labelBean);
						}
					}
					issuingAuthNamesList = adminSanctionService.getIssuingAuthName(adminSanctionForm.getIssuingAuthority(),lStrUserState);
					request.setAttribute("issuingAuthNamesList", issuingAuthNamesList);
					adminSanctionForm.setAttachFlag("Present");
					request.setAttribute("showDetailedList", showDetailedList);
					request.setAttribute("deptNamesList", deptList);
					request.setAttribute("listSchemes", schemeList);
					request.setAttribute("detailedHeadsList", detailedHeadsList);
					request.setAttribute("sourceOfBudgetList",
							sourceOfBudgetList);
					request.setAttribute("vendorNamesList", vendorNamesList);
					request.setAttribute("vendorCodesList", vendorCodesList);
					String backTo = "track";
					request.setAttribute("backTo", backTo);
					lStrResultPage = "sanctionForm";

				} else {
					adminSanctionForm.setDeptName(adminSanctionService
							.getDepartment(adminSanctionForm.getDeptName())
							.getVALUE());
					String approvedDate=adminSanctionService.getAdminSancApprovedDate(sanctionId);
					if (approvedDate!= null && !approvedDate.equals(""))
					adminSanctionForm.setDate(approvedDate);
					showPrint = "display:block";
					request.setAttribute("showPrint", showPrint);
					lStrResultPage = "printSanctionForm";
				}
			}

		}
		
		
		
		return mapping.findForward(lStrResultPage);
	}

}
