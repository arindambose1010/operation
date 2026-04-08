package com.ahct.claims.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.form.ClaimsFlowForm;
import com.ahct.claims.service.ClaimsFlowService;
import com.ahct.claims.util.ClaimCases;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.claims.valueobject.ErrPaymentReportVO;
import com.ahct.claims.valueobject.PaymentReportVO;
import com.ahct.claims.valueobject.claimPaymentReportVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.ExcelWriter;
import com.ahct.common.util.PdfGenerator;
import com.ahct.model.EhfCase;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.login.service.LoginService;
import com.ahct.patient.service.PatientService;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthVO;
import com.google.gson.Gson;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.service.TimeLoggingService;

/**
 * The Class ClaimsFlowAction.
 * 
 * @author Ishank Paharia
 * @class This Class is used for Claim Process
 * @version jdk 1.6
 * @Date 4 April 2013
 */
public class ClaimsFlowAction extends Action {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		HttpSession session = null;String lStrResultPage = null;String userName = null;String lStrLangID = null;String lStrUserId = null;
		List<LabelBean> lStrUserRoleList = null;String roleId = null;	

		ClaimsFlowForm claimsFlowForm = (ClaimsFlowForm) form;
		ClaimsFlowService claimsService = (ClaimsFlowService) ServiceFinder
				.getContext(request).getBean("claimsFlowService");
		LoginService loginService =(LoginService)ServiceFinder.getContext(request).getBean("loginService");
		ConfigurationService configurationService = (ConfigurationService) ServiceFinder
				.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
		CommonService commonService = (CommonService) ServiceFinder.getContext(
				request).getBean("commonService");
		PatientService patientService = (PatientService) ServiceFinder.getContext(request).getBean("patientService");//Chandana - 8602 - to call the existing methods written in patientservice
		session = request.getSession(false);
		String callType = null;String lStrUserState=null;
		if (request.getParameter("callType") != null
				&& !ClaimsConstants.EMPTY.equals(request
						.getParameter("callType"))) {
			callType = request.getParameter("callType");
		}
		lStrResultPage = HtmlEncode.verifySession(request, response);
		if (lStrResultPage.length() > 0) {
			request.setAttribute("caseSession", ClaimsConstants.Y);
			if (callType != null && "Ajax".equals(callType)) {
				request.setAttribute("AjaxMessage", "SessionExpired");
				return mapping.findForward("ajaxResult");
			} else
				return mapping.findForward("sessionExpire");
		}

		if (session.getAttribute("EmpID").toString() != null) {
			lStrUserId = session.getAttribute("EmpID").toString();
		}
		if (session.getAttribute("LangID").toString() != null) {
			lStrLangID = session.getAttribute("LangID").toString();
		}
		if (session.getAttribute("fullName").toString() != null) {
			userName = session.getAttribute("fullName").toString();
		}
		if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
	    	lStrUserState = ( String ) session.getAttribute ( "userState" ) ;
        //getting group list
		List<LabelBean> groupsList = null;
		if ((session.getAttribute("groupList") != null)
				&& !(session.getAttribute("groupList")
						.equals(ClaimsConstants.EMPTY)))
			groupsList = (List<LabelBean>) session.getAttribute("groupList");

		if (session.getAttribute("rolesList") != null) {
			if (session.getAttribute("rolesList").toString() != null) {
				lStrUserRoleList = (List<LabelBean>) session
						.getAttribute("rolesList");
			}

			for (LabelBean labelBean : lStrUserRoleList) {
				roleId = labelBean.getID();
			}
		} else
			roleId = request.getParameter("UserRole");

		SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		String serverDt = sdfw.format(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		String lStrActionVal = request.getParameter("actionFlag");
		String lStrUserGroup = null;
		String oldRoleId = null;
		String saveFlag = ClaimsConstants.EMPTY;

		// Added for getting unique price for dental procedures in claims tab
		if(lStrActionVal!= null && "getUniquePrice".equals(lStrActionVal)) 
        {
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			String caseId = request.getParameter("caseId");
			String caseTherapyId = request.getParameter("caseTherapyId");
			String ctdApprv = request.getParameter("ctdApprv").toString();
			claimFlowVO.setCaseId(caseId);
			claimFlowVO.setCaseTherapyId(caseTherapyId);
			claimFlowVO.setCtdApprvdUnits(ctdApprv);
	    	ClaimsFlowVO packgDtls = claimsService.getUniquePrices(claimFlowVO);
	    	if(packgDtls !=null)
	    	{
	    		String finalPrice = packgDtls.getTotPackgAmt().toString();
	    		request.setAttribute("AjaxMessage",finalPrice);
	    	}
	    	
	    	return mapping.findForward("ajaxResult");	
        }
		
		
		if(lStrActionVal!= null && "getTotalPrice".equals(lStrActionVal)) 
        {
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			String caseId = request.getParameter("caseId");
			String subVal = request.getParameter("subVal");
			roleId = request.getParameter("roleId");
			
			String checkDentalCond=claimsService.checkDentalCase(caseId);
			String nabhAmnt = claimsService.getNabhAmnt(caseId);
			if(!"".equalsIgnoreCase(nabhAmnt) && nabhAmnt != null)
				claimFlowVO.setHospStayAmt(nabhAmnt);
				
		
			if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
				claimFlowVO.setDentalFlag(checkDentalCond);
			
			claimFlowVO.setCaseId(caseId);
			claimFlowVO.setRoleId(roleId);
			claimFlowVO.setUserId(lStrUserId);
			if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
			{
				if(subVal != null)	
					{
						claimFlowVO.setDentCond(checkDentalCond);
						claimFlowVO.setCaseUnitsPar(subVal);
					}
			}
	    	ClaimsFlowVO packgDtls = claimsService.getTotalPrice(claimFlowVO);
	    	if(packgDtls !=null)
	    	{
	    		String finalPrice = packgDtls.getTotPackgAmt().toString();
	    		request.setAttribute("AjaxMessage",finalPrice);
	    	}
	    	
	    	return mapping.findForward("ajaxResult");	
        }
		
		
		/* saving claim Details */
		if ("saveClaimDtls".equalsIgnoreCase(lStrActionVal)) {
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			ClaimsFlowVO claimCalTimeVo = new ClaimsFlowVO();
			sdfw = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
			String caseStartTime = sdfw.format(new Date().getTime());
			claimFlowVO.setCaseId(claimsFlowForm.getCaseId());
			
			com.ahct.model.EhfCase ehfCase=claimsService.getCase(claimsFlowForm.getCaseId());
			/** 
			 * Added by ramalakshmi for skipping CPD role in N18.6.A cases
			 */
			String heamFlag = claimsService.getTherapyId(claimsFlowForm.getCaseId());
			claimFlowVO.setHeamFlag(heamFlag);
			//End of N18.6.A Changes
			
			claimFlowVO.setCaseStat(claimsFlowForm.getCaseStatus());
			claimFlowVO.setClaimSubDt(serverDt);
			claimFlowVO.setActionDone(request.getParameter("actionDone"));
			/* to check whether saving Erroneous claim or not */
			String errClaimFlag = request.getParameter("errClaimFlag");
			claimFlowVO.setUserId(lStrUserId);
			claimFlowVO.setRoleId(claimsFlowForm.getRoleId());
			oldRoleId = claimsFlowForm.getRoleId();
			//details regarding medco
			claimFlowVO.setBillAmt(claimsFlowForm.getBillAmt());
			if (claimsFlowForm.getClaimInitAmt() != null
					&& !claimsFlowForm.getClaimInitAmt().equalsIgnoreCase(ClaimsConstants.EMPTY))
				claimFlowVO.setClaimInitAmt(claimsFlowForm.getClaimInitAmt());
			if (claimsFlowForm.getBillDt() != null
					&& !claimsFlowForm.getBillDt().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setBillDt(sdfw.format(sdf.parse(claimsFlowForm
						.getBillDt())));
			if (claimsFlowForm.getMedcoRemark() != null
					&& claimsFlowForm.getMedcoRemark().length() > 3000)
				claimFlowVO.setMedcoRemark(claimsFlowForm.getMedcoRemark()
						.substring(0, 3000));
			else
				claimFlowVO.setMedcoRemark(claimsFlowForm.getMedcoRemark());
			/* if Erroneous claim then */
			if (errClaimFlag != null
					&& errClaimFlag.equalsIgnoreCase(ClaimsConstants.YES)) {
				claimFlowVO.setErrAmt(claimsFlowForm.getErrAmount());
				claimFlowVO.setErrClaimSubDt(serverDt);
				if (claimsFlowForm.getErrMedRemark() != null
						&& claimsFlowForm.getErrMedRemark().length() > 3000)
					claimFlowVO.setErrMedcoRemark(claimsFlowForm
							.getErrMedRemark().substring(0, 3000));
				else
					claimFlowVO.setErrMedcoRemark(claimsFlowForm
							.getErrMedRemark());
				if (claimsFlowForm.getErrClaimPaid() != null)
					claimFlowVO.setClaimPaidAmt(Long.parseLong(claimsFlowForm
							.getErrClaimPaid()));

				claimFlowVO.setErrCtdAprAmt(claimsFlowForm.getErrAppAmount());
				if (claimsFlowForm.getErrCtdRemark() != null
						&& claimsFlowForm.getErrCtdRemark().length() > 3000)
					claimFlowVO.setErrCtdRemark(claimsFlowForm
							.getErrCtdRemark().substring(0, 3000));
				else
					claimFlowVO.setErrCtdRemark(claimsFlowForm
							.getErrCtdRemark());

				claimFlowVO.setErrChAprAmt(claimsFlowForm.getErrChAppAmount());
				if (claimsFlowForm.getErrChRemark() != null
						&& claimsFlowForm.getErrChRemark().length() > 3000)
					claimFlowVO.setErrChRemark(claimsFlowForm.getErrChRemark()
							.substring(0, 3000));
				else
					claimFlowVO.setErrChRemark(claimsFlowForm.getErrChRemark());

				claimFlowVO.setErrAcoAprAmt(claimsFlowForm.getErrAcoAprAmt());
				if (claimsFlowForm.getErrAcoRemark() != null
						&& claimsFlowForm.getErrAcoRemark().length() > 3000)
					claimFlowVO.setErrAcoRemark(claimsFlowForm
							.getErrAcoRemark().substring(0, 3000));
				else
					claimFlowVO.setErrAcoRemark(claimsFlowForm
							.getErrAcoRemark());

				claimFlowVO.setErrClaimStatus(claimsFlowForm
						.getErrClaimStatus());
			}
			//details regarding CEX
			if (claimFlowVO.getActionDone().equalsIgnoreCase(
					config.getString("EHF.FwdButton"))) {
				claimFlowVO.setNameCheck(claimsFlowForm.getNameCheck());
				claimFlowVO.setGenderCheck(claimsFlowForm.getGenderCheck());
				claimFlowVO.setBenPhotoCheck(claimsFlowForm.getBenPhotoCheck());
				if (request.getParameter("admDtCheck") != null
						&& !request.getParameter("admDtCheck")
								.equalsIgnoreCase(""))
					claimFlowVO.setAdmDtCheck(request
							.getParameter("admDtCheck"));
				else
					claimFlowVO.setAdmDtCheck(claimsFlowForm.getAdmDtCheck());

				if (request.getParameter("dischargeDtCheck") != null
						&& !request.getParameter("dischargeDtCheck")
								.equalsIgnoreCase(""))
					claimFlowVO.setDischargeDtCheck(request
							.getParameter("dischargeDtCheck"));
				else
					claimFlowVO.setDischargeDtCheck(claimsFlowForm
							.getDischargeDtCheck());

				if (request.getParameter("surgDtCheck") != null
						&& !request.getParameter("surgDtCheck")
								.equalsIgnoreCase(""))
					claimFlowVO.setSurgDtCheck(request
							.getParameter("surgDtCheck"));
				else
					claimFlowVO.setSurgDtCheck(claimsFlowForm.getSurgDtCheck());

				if (claimsFlowForm.getCaseStAdmDt() != null)
					claimFlowVO.setCaseStAdmDt(sdfw.format(sdf
							.parse(claimsFlowForm.getCaseStAdmDt())));
				if (claimsFlowForm.getCaseStDischrgDt() != null)
					claimFlowVO.setCaseStDischrgDt(sdfw.format(sdf
							.parse(claimsFlowForm.getCaseStDischrgDt())));
				if (claimsFlowForm.getCaseStSurgDt() != null)
					claimFlowVO.setCaseStSurgDt(sdfw.format(sdf
							.parse(claimsFlowForm.getCaseStSurgDt())));
				if (claimsFlowForm.getCexRemark() != null
						&& claimsFlowForm.getCexRemark().length() > 3000)
					claimFlowVO.setCexRemark(claimsFlowForm.getCexRemark()
							.substring(0, 3000));
				else
					claimFlowVO.setCexRemark(claimsFlowForm.getCexRemark());
				claimFlowVO.setCexAprAmt(claimsFlowForm.getCexAprAmt());
				claimFlowVO.setDocVer1(claimsFlowForm.getDocVer1());
				claimFlowVO.setDocVer2(claimsFlowForm.getDocVer2());
				// claimFlowVO.setDocVer3(claimsFlowForm.getDocVer3());
				claimFlowVO.setDocVer4(claimsFlowForm.getDocVer4());
				claimFlowVO.setDocVer5(claimsFlowForm.getDocVer5());
			}
			//details regarding CPD
			claimFlowVO.setTechCheck1(claimsFlowForm.getTechCheck1());
			claimFlowVO.setTechCheck2(claimsFlowForm.getTechCheck2());
			claimFlowVO.setTechCheck3(claimsFlowForm.getTechCheck3());
			claimFlowVO.setTechCheck4(claimsFlowForm.getTechCheck4());

			if (request.getParameter("totalClaim") != null
					&& !request.getParameter("totalClaim").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setTotalClaim(request.getParameter("totalClaim"));
			if (request.getParameter("deduction") != null
					&& !request.getParameter("deduction").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setDeduction(request.getParameter("deduction"));
			claimFlowVO.setStay(claimsFlowForm.getStay());
			/* more than 3000(just for saftey purpose)*/
			if (claimsFlowForm.getStayRemark() != null
					&& claimsFlowForm.getStayRemark().length() > 3000)
				claimFlowVO.setStayRemark(claimsFlowForm.getStayRemark()
						.substring(0, 3000));
			else
				claimFlowVO.setStayRemark(claimsFlowForm.getStayRemark());
			claimFlowVO.setInputs(claimsFlowForm.getInputs());
			if (claimsFlowForm.getInputsRmrk() != null
					&& claimsFlowForm.getInputsRmrk().length() > 3000)
				claimFlowVO.setInputsRmrk(claimsFlowForm.getInputsRmrk()
						.substring(0, 3000));
			else
				claimFlowVO.setInputsRmrk(claimsFlowForm.getInputsRmrk());

			claimFlowVO.setProfFee(claimsFlowForm.getProfFee());
			if (claimsFlowForm.getProfFeeRmrk() != null
					&& claimsFlowForm.getProfFeeRmrk().length() > 3000)
				claimFlowVO.setProfFeeRmrk(claimsFlowForm.getProfFeeRmrk()
						.substring(0, 3000));
			else
				claimFlowVO.setProfFeeRmrk(claimsFlowForm.getProfFeeRmrk());

			claimFlowVO.setInvestBill(claimsFlowForm.getInvestBill());
			if (claimsFlowForm.getInvestBillRmrk() != null
					&& claimsFlowForm.getInvestBillRmrk().length() > 3000)
				claimFlowVO.setInvestBillRmrk(claimsFlowForm
						.getInvestBillRmrk().substring(0, 3000));
			else
				claimFlowVO.setInvestBillRmrk(claimsFlowForm
						.getInvestBillRmrk());

			if (claimsFlowForm.getClaimPanelRemark() != null
					&& claimsFlowForm.getClaimPanelRemark().length() > 3000)
				claimFlowVO.setClaimPanelRemark(claimsFlowForm
						.getClaimPanelRemark().substring(0, 3000));
			else
				claimFlowVO.setClaimPanelRemark(claimsFlowForm
						.getClaimPanelRemark());
			
			if (request.getParameter("cpdAprAmt") != null
					&& !request.getParameter("cpdAprAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setCpdAprAmt(request.getParameter("cpdAprAmt"));
	//second cpd
			
			claimFlowVO.setTechCheckOrg1(claimsFlowForm.getTechCheckOrg1());
			claimFlowVO.setTechCheckOrg2(claimsFlowForm.getTechCheckOrg2());
			claimFlowVO.setTechCheckOrg3(claimsFlowForm.getTechCheckOrg3());
			claimFlowVO.setTechCheckOrg4(claimsFlowForm.getTechCheckOrg4());

			if (request.getParameter("totalClaim") != null
					&& !request.getParameter("totalClaim").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setTotalClaim(request.getParameter("totalClaim"));
			if (request.getParameter("deduction") != null
					&& !request.getParameter("deduction").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setDeductionOrg(request.getParameter("deduction"));
			claimFlowVO.setStay(claimsFlowForm.getStay());
			/* more than 3000(just for saftey purpose)*/
			if (claimsFlowForm.getStayRemark() != null
					&& claimsFlowForm.getStayRemark().length() > 3000)
				claimFlowVO.setStayRemark(claimsFlowForm.getStayRemark()
						.substring(0, 3000));
			else
				claimFlowVO.setStayRemark(claimsFlowForm.getStayRemark());
			claimFlowVO.setInputsOrg(claimsFlowForm.getInputsOrg());
			if (claimsFlowForm.getInputsRmrkOrg() != null
					&& claimsFlowForm.getInputsRmrkOrg().length() > 3000)
				claimFlowVO.setInputsRmrkOrg(claimsFlowForm.getInputsRmrkOrg()
						.substring(0, 3000));
			else
				claimFlowVO.setInputsRmrkOrg(claimsFlowForm.getInputsRmrkOrg());

			claimFlowVO.setProfFeeOrg(claimsFlowForm.getProfFeeOrg());
			if (claimsFlowForm.getProfFeeRmrkOrg() != null
					&& claimsFlowForm.getProfFeeRmrkOrg().length() > 3000)
				claimFlowVO.setProfFeeRmrkOrg(claimsFlowForm.getProfFeeRmrkOrg()
						.substring(0, 3000));
			else
				claimFlowVO.setProfFeeRmrkOrg(claimsFlowForm.getProfFeeRmrkOrg());

			claimFlowVO.setInvestBillOrg(claimsFlowForm.getInvestBillOrg());
			if (claimsFlowForm.getInvestBillRmrkOrg() != null
					&& claimsFlowForm.getInvestBillRmrkOrg().length() > 3000)
				claimFlowVO.setInvestBillRmrkOrg(claimsFlowForm
						.getInvestBillRmrkOrg().substring(0, 3000));
			else
				claimFlowVO.setInvestBillRmrkOrg(claimsFlowForm
						.getInvestBillRmrkOrg());

			if (claimsFlowForm.getClaimPanelRemarkOrg() != null
					&& claimsFlowForm.getClaimPanelRemarkOrg().length() > 3000)
				claimFlowVO.setClaimPanelRemarkOrg(claimsFlowForm
						.getClaimPanelRemarkOrg().substring(0, 3000));
			else
				claimFlowVO.setClaimPanelRemarkOrg(claimsFlowForm
						.getClaimPanelRemarkOrg());
			
			if (request.getParameter("cpdAprAmt") != null
					&& !request.getParameter("cpdAprAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setCpdAprAmtOrg(request.getParameter("cpdAprAmt"));
			//details regarding CTD
			claimFlowVO.setTrustDoc1(claimsFlowForm.getTrustDoc1());
			claimFlowVO.setTrustDoc2(claimsFlowForm.getTrustDoc2());
			claimFlowVO.setTrustDoc3(claimsFlowForm.getTrustDoc3());
			claimFlowVO.setTrustDoc4(claimsFlowForm.getTrustDoc4());
			claimFlowVO.setCtdAprAmt(claimsFlowForm.getCtdAprAmt());
			if (request.getParameter("ctdFinAprAmt") != null
					&& !request.getParameter("ctdFinAprAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
			claimFlowVO.setCtdFinAprAmt(request.getParameter("ctdFinAprAmt"));
			
			if (request.getParameter("ctdNabhAmt") != null
					&& !request.getParameter("ctdNabhAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setCtdNabhAmt(request.getParameter("ctdNabhAmt"));
			
			if (claimsFlowForm.getCtdRemark() != null
					&& claimsFlowForm.getCtdRemark().length() > 3000)
				claimFlowVO.setCtdRemark(claimsFlowForm.getCtdRemark()
						.substring(0, 3000));
			else
				claimFlowVO.setCtdRemark(claimsFlowForm.getCtdRemark());
			
			//details regarding CH
			claimFlowVO.setChEntAprAmt(claimsFlowForm.getChEntAprAmt());
			if (request.getParameter("chfAprAmt") != null
					&& !request.getParameter("chfAprAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
			claimFlowVO.setChAprAmt(request.getParameter("chfAprAmt"));
			
			if (request.getParameter("chNabhAmt") != null
					&& !request.getParameter("chNabhAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setChNabhAmt(request.getParameter("chNabhAmt"));
			
			if (claimsFlowForm.getChRemark() != null
					&& claimsFlowForm.getChRemark().length() > 3000)
				claimFlowVO.setChRemark(claimsFlowForm.getChRemark().substring(
						0, 3000));
			else
				claimFlowVO.setChRemark(claimsFlowForm.getChRemark());		
			
			//details regarding EO,Eo-comm,Aco
			claimFlowVO.setEoAprAmt(claimsFlowForm.getEoAprAmt());
			if (claimsFlowForm.getEoRemark() != null
					&& claimsFlowForm.getEoRemark().length() > 3000)
				claimFlowVO.setEoRemark(claimsFlowForm.getEoRemark().substring(
						0, 3000));
			else
				claimFlowVO.setEoRemark(claimsFlowForm.getEoRemark());

			if (claimsFlowForm.getEoComRemark() != null
					&& claimsFlowForm.getEoComRemark().length() > 3000)
				claimFlowVO.setEoComRemark(claimsFlowForm.getEoComRemark()
						.substring(0, 3000));
			else
				claimFlowVO.setEoComRemark(claimsFlowForm.getEoComRemark());
			if (claimFlowVO.getRoleId() != null
					&& !claimFlowVO.getRoleId().equalsIgnoreCase("")
					&& claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.EOComm"))&&claimFlowVO.getActionDone().equalsIgnoreCase(
									config.getString("EHF.SendTo"))){
				claimFlowVO.setEoComRemark(claimsFlowForm.getEoComSendRemarks());
				claimFlowVO.setSennBackRole(claimsFlowForm.getAllUsers());
			}
			
			claimFlowVO.setEoComEntAprAmt(claimsFlowForm.getEoComEntAprAmt());
			if (request.getParameter("eoComfAprAmt") != null
					&& !request.getParameter("eoComfAprAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
			claimFlowVO.setEoComAprAmt(request.getParameter("eoComfAprAmt"));
			
			if (request.getParameter("eoComNabhAmt") != null
					&& !request.getParameter("eoComNabhAmt").equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimFlowVO.setEoComNabhAmt(request.getParameter("eoComNabhAmt"));

			claimFlowVO.setAcoAprAmt(claimsFlowForm.getAcoAprAmt());
			if (claimsFlowForm.getAcoRemark() != null
					&& claimsFlowForm.getAcoRemark().length() > 3000)
				claimFlowVO.setAcoRemark(claimsFlowForm.getAcoRemark()
						.substring(0, 3000));
			else
				claimFlowVO.setAcoRemark(claimsFlowForm.getAcoRemark());
			
			String checkDentalCond=claimsService.checkDentalCase(claimsFlowForm.getCaseId());
			if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
				claimFlowVO.setDentalFlag(checkDentalCond);
			if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
			{
				String caseUnits = claimsFlowForm.getCaseUnits();
				if(caseUnits != null)	
					{
						claimFlowVO.setDentCond(checkDentalCond);
						claimFlowVO.setCaseUnits(caseUnits);
					}
			}
			/* Saving Erroneous claim detials or regular claim */
			if (errClaimFlag != null
					&& errClaimFlag.equalsIgnoreCase(ClaimsConstants.YES))
				claimFlowVO = claimsService.saveErrClaim(claimFlowVO);
			else
				claimFlowVO = claimsService.saveClaim(claimFlowVO);

			String caseEndTime = sdfw.format(new Date().getTime());
		    String actionDone = commonService.getActionDoneName(claimsFlowForm.getCaseId(),"ehfCase");
			loggingService.logTime(actionDone, claimsFlowForm.getCaseId(), caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
			if (!claimFlowVO.getSmsMsg()
					.equalsIgnoreCase(ClaimsConstants.EMPTY)) {
				String smsNextVal = ClaimsConstants.EMPTY;
				PatientVO patientVO = new PatientVO();
				Date date = new Date();
				Date crtDt = sdfw.parse((sdfw.format(date)));
				//sequence for SMS
				smsNextVal = commonService.getSequence("PATIENT_SMS_SNO");
				//getting patient details for SMS
				patientVO = claimsService.getPatientDtls(claimFlowVO
						.getPatientId());
				//IF sms required
				if (ClaimsConstants.TRUE.equalsIgnoreCase(config
						.getString("SmsRequired"))) {
					String lStrResultMsg = null;
					PatientSmsVO patientSmsVO = new PatientSmsVO();
					patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
					patientSmsVO.setPhoneNo(patientVO.getContactNo());
					patientSmsVO.setSmsText("Dear "
							+ patientVO.getFirstName().trim() + " ,"
							+ claimFlowVO.getSmsMsg());
					patientSmsVO.setEmpCode(patientVO.getEmpCode());
					patientSmsVO.setEmpName(patientVO.getFirstName());
					patientSmsVO.setCrtUsr(lStrUserId);
					patientSmsVO.setCrtDt(crtDt);
					patientSmsVO.setSmsAction(claimFlowVO.getMsg());
					patientSmsVO.setPatientId(claimFlowVO.getPatientId());
					//send SMS
					lStrResultMsg = commonService.sendPatientSms(patientSmsVO);
				}
				//IF Email required
				if (config.getBoolean("EmailRequired")) {
					String mailId = null;
					if (patientVO.geteMailId() != null
							&& !patientVO.geteMailId().equals(
									ClaimsConstants.EMPTY)) {
						mailId = patientVO.geteMailId();
						String[] emailToArray = { mailId };
						EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
						
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("claimmailSubject"));
						Map<String, String> model = new HashMap<String, String>();
						model.put("patientName", patientVO.getFirstName()
								.trim());
						model.put("caseNo", claimFlowVO.getCaseId());
						model.put("status", claimFlowVO.getMsg());
						model.put("statusDate", crtDt.toString());
						if(ehfCase!=null)
							{
								if(ehfCase.getPatientScheme()!=null)
									{
										if(ehfCase.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS")))
											{
												emailVO.setSubject(config.getString("claimmailSubjectJourn"));
												emailVO.setFromEmail(config.getString("emailFromJournalist"));
												emailVO.setTemplateName(config.getString("EhfClaimTemplateNameJourn"));
												commonService.generateNonImageMail(emailVO, model);
											}
										else
											{
												emailVO.setTemplateName(config.getString("EhfClaimTemplateName"));
												commonService.generateMail(emailVO, model);
											}
									}
								else
									{
										emailVO.setTemplateName(config.getString("EhfClaimTemplateName"));
										commonService.generateMail(emailVO, model);
									}
							}
						else
							{
								emailVO.setTemplateName(config.getString("EhfClaimTemplateName"));
								commonService.generateMail(emailVO, model);
							}
						//generating Email
						
					}
				}
			}
			if (!claimFlowVO.getMsg().equalsIgnoreCase(ClaimsConstants.Failure)) {
				request.setAttribute("saveMsg", claimFlowVO.getMsg());
				request.setAttribute("disableAll", ClaimsConstants.YES);
				request.setAttribute("nextStat", claimFlowVO.getCaseNextStat());
			}
			saveFlag = ClaimsConstants.TRUE;
			lStrActionVal = "Claims";
		}
        //fetching claim details
		if ("Claims".equalsIgnoreCase(lStrActionVal)) {

			String caseId = request.getParameter("CaseId");
			String caseStat = request.getParameter("CaseStat");
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			claimFlowVO.setCaseId(caseId);
			claimFlowVO.setCaseStat(caseStat);
			claimFlowVO.setRoleId(roleId);
			String orgFlagNew=loginService.getorgFlag(caseId);
			String nimsHosp=loginService.getNimsMedcoDoc(caseId);
	    	if(!"".equalsIgnoreCase(nimsHosp) && "EHS34".equalsIgnoreCase(nimsHosp))
	    	{
	    		request.setAttribute("nimsMedco", "Y");
	    		claimFlowVO.setNimsFlag("Y");
	    	}
	    	else
	    	{
	    		request.setAttribute("nimsMedco", "N");
	    		claimFlowVO.setNimsFlag("N");
	    	}

			//getting erroneous dental claim or not 
			int denCount = claimsService.getDentalErrClm(caseId);
			if(denCount>0)
				request.setAttribute("denErrSearchType", "Y");
			//getting caseDetails
			claimFlowVO = claimsService.getCaseDtls(claimFlowVO);
			if(claimFlowVO.getNewBornBaby()!=null)
				claimsFlowForm.setNewBornBaby(claimFlowVO.getNewBornBaby());
			claimsFlowForm.setCaseId(claimFlowVO.getCaseId());
			claimsFlowForm.setCaseStatus(claimFlowVO.getCaseStat());
			claimsFlowForm.setShowEo(claimFlowVO.getShowEO());
			claimsFlowForm.setShowEoCom(claimFlowVO.getShowEOCom());
			claimsFlowForm.setSchemeType(claimFlowVO.getSchemeType());
			claimsFlowForm.setNabhFlag(claimFlowVO.getNabhFlag());
			claimsFlowForm.setPhaseId(claimFlowVO.getPhaseId());
			//Added by Srikalyan For Dental Related Changes in Claims
			String checkDentalCond=claimsService.checkDentalCase(caseId);
			if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
				{
					List<PreauthVO> lstSurgyDtls = claimsService.getcaseSurgertDtls(caseId);
					claimsFlowForm.setLstPreauthVO(lstSurgyDtls);
					request.setAttribute("dentalSurg","Y");
					for(LabelBean labelBean:groupsList)
			    		{
							if(labelBean.getID() != null && config.getString("view_audit_names")!=null && config.getString("view_audit_names").contains("~"+labelBean.getID()+"~") )
						    	{
						    		request.setAttribute("viewAuditNames", "Y");
						    		if(!labelBean.getID().equalsIgnoreCase(config.getString("Group.Pex")) && !labelBean.getID().equalsIgnoreCase(config.getString("EHF.Claims.CEX"))
						    				&& !labelBean.getID().equalsIgnoreCase(config.getString("EHF.Claims.CEX.DENAP")))
						    			{
						    				request.setAttribute("viewProcAmts", "Y");
						    				break;
						    			}
						    			}
							if(labelBean.getID() != null && config.getString("ahc_medco_role").contains(labelBean.getID()))
									{
											request.setAttribute("isMedcoFlg", "Y");
									}
			    		}	
				}
			/*if(claimFlowVO.getFlagged()!=null && claimFlowVO.getFlagged().equalsIgnoreCase("Y"))	
			{
			claimsFlowForm.setMsg(ClaimsConstants.CLAIM_FLAGED_MSG);
			}*/
			// getting group id
			if (saveFlag != null
					&& saveFlag.equalsIgnoreCase(ClaimsConstants.TRUE)) {
				roleId = oldRoleId;
			} else {
				//getting user from status and config
				if (claimFlowVO.getErrClaimStatus() != null
						&& !claimFlowVO.getErrClaimStatus().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					lStrUserGroup = config.getString("claimGroup_"
							+ claimFlowVO.getErrClaimStatus());
					claimsFlowForm.setErrClaimStatus(claimFlowVO
							.getErrClaimStatus());
				} else
					lStrUserGroup = config.getString("claimGroup_"
							+ claimFlowVO.getCaseStat());

				String userGrpTemp = lStrUserGroup;
				for (LabelBean labelBean : groupsList) {
					if (labelBean.getID() != null
							&& labelBean.getID()
									.equalsIgnoreCase(lStrUserGroup)) {
						userGrpTemp = lStrUserGroup;
						break;
					} else
						userGrpTemp = null;
				}
				lStrUserGroup = userGrpTemp;
				
				if(lStrUserGroup==null)
					{
						lStrUserGroup = config.getString("claimGroup_"
								+ claimFlowVO.getCaseStat());
						if(lStrUserGroup!=null)
							if(lStrUserGroup.equalsIgnoreCase("GP8"))
								{
									lStrUserGroup="GP73";
								}
					}
				
				userGrpTemp = lStrUserGroup;
				for (LabelBean labelBean : groupsList) {
					if (labelBean.getID() != null
							&& labelBean.getID()
									.equalsIgnoreCase(lStrUserGroup)) {
						userGrpTemp = lStrUserGroup;
						break;
					} else
						userGrpTemp = null;
				}
				lStrUserGroup = userGrpTemp;
				
				
				if (groupsList == null || groupsList.isEmpty()) {
					lStrUserGroup = null;
				}
				
				String caseApprovalFlag = null;
				caseApprovalFlag = request.getParameter("caseApprovalFlag");
				request.setAttribute("caseApprovalFlag", caseApprovalFlag);
				if (caseApprovalFlag != null
						&& caseApprovalFlag.equalsIgnoreCase("N")) {
					//in case of Erronenous Claim
					lStrUserGroup = null;
					if (claimFlowVO.getErrCtdAprAmt() != null
							&& !claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.ErrReInit")))
						claimsFlowForm.setShowCtd1(ClaimsConstants.YES);
					if (claimFlowVO.getErrChAprAmt() != null
							&& !claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.ErrReInit")))
						claimsFlowForm.setShowCh1(ClaimsConstants.YES);
					if (claimFlowVO.getErrAcoAprAmt() != null
							&& !claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.ErrReInit")))
						claimsFlowForm.setShowAco1(ClaimsConstants.YES);
				}
				else if (caseApprovalFlag != null
						&& caseApprovalFlag.equalsIgnoreCase("Y") && lStrUserGroup==null) {
						
	    			//For Dental Cases Activating CTD for CEX Forwarded/CPD pending updated Cases
					if(config.getString("claim_Dent_panelDocStatus").contains("~"+caseStat+"~"))
	    				{
					    	if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
				    			{
				    				for(LabelBean labelBean:groupsList)
							    		{
								    		if(labelBean.getID() != null && (config.getString("preauth_ctd_role").equalsIgnoreCase(labelBean.getID())))
										    	{
								    				lStrUserGroup=config.getString("EHF.Claims.CTD");
										    		break;	
										    	}	
								    	}
				    			}	
	    				}
					
				}

				roleId = lStrUserGroup;
				claimFlowVO.setRoleId(roleId);
				// Added by sravan to view CTD or CH Approved units
				if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y") && "GP8".equalsIgnoreCase(roleId))
				{	
					request.setAttribute("ctdViewFlag", "Y");
					request.setAttribute("totalPriceBtn", "Y");
				}
				if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y") && "GP9".equalsIgnoreCase(roleId))
				{
						request.setAttribute("chViewFlag", "Y");
						request.setAttribute("totalPriceBtn", "Y");
				}
				claimFlowVO.setUserId(lStrUserId);
				//checking whether any case is pending for disscussion in CTD,CH login or not
				if (claimFlowVO.getCaseStat() != null
						&& roleId != null
						&& (roleId.equalsIgnoreCase(config
								.getString("EHF.Claims.CTD")) || roleId
								.equalsIgnoreCase(config
										.getString("EHF.Claims.CH")))
						&& !claimFlowVO
								.getCaseStat()
								.equalsIgnoreCase(
										config.getString("EHF.Claims.ClaimKeptDiscuCTD"))
						&& !claimFlowVO
								.getCaseStat()
								.equalsIgnoreCase(
										config.getString("EHF.Claims.ClaimKeptDiscuCH"))) {
					String dissCaseFlag = claimsService
							.checkDissCaseFlag(claimFlowVO);
					if (dissCaseFlag != null
							&& dissCaseFlag.equalsIgnoreCase("true")) {
						claimsFlowForm
								.setMsg(ClaimsConstants.CLAIM_DISSPEND);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
						claimsFlowForm.setClaimInfo(claimFlowVO);
						return mapping.findForward(lStrResultPage);
					}
				}
			}
			List<LabelBean> investDetails =  claimFlowVO.getInvestDetails();
			if(investDetails!= null && investDetails.size() > 0){
				claimsFlowForm.setInvestDetails(investDetails);
				
			}
			
			// getting list of Drug details 
						List<LabelBean> drugDetails =  claimFlowVO.getDrugDetails();
						if(drugDetails!= null && drugDetails.size() > 0){
							claimsFlowForm.setDrugDetails(drugDetails);
							
						}
			claimsFlowForm.setMedicalSurgFlag(claimFlowVO.getMedicalSurgFlag());
			// getting list of buttons for specific role and status
			List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
					claimFlowVO.getCaseStat(), roleId,
					config.getString("EHF.Claims"),
					config.getString("EHF.RegClaims"));
			claimFlowVO.setButtonList(buttonList);

			if (buttonList.size() == 0)
				request.setAttribute("disableAll", ClaimsConstants.YES);
			
			/***
    		 * 1.Added by Kalyan to remove unnecessary buttons for CTD/CH Pending Cases 
    		 * 2.For CH , If count is greater than 2 pending button needs to be removed
    		 * 3.For CTD ,If count is less than 2 pending button needs to be added for Only Dental Cases
    		 */
			if(roleId!=null && (	claimFlowVO.getCaseStat()!=null
									&& 		!claimFlowVO.getCaseStat().equalsIgnoreCase(config.getString("EHF.Claims.ClaimKeptDiscuCTD"))
									&&		!claimFlowVO.getCaseStat().equalsIgnoreCase(config.getString("EHF.Claims.ClaimKeptDiscuCH"))
								) 
							&& 
					            (	(roleId.equalsIgnoreCase(config.getString("Group.COCH")) && checkDentalCond!=null && 
										checkDentalCond.equalsIgnoreCase("Y")) || (roleId.equalsIgnoreCase(config.getString("EHF.Claims.CPD")))
					            		
					        ||  	(roleId.equalsIgnoreCase(config.getString("EHF.Claims.CTD")) && checkDentalCond!=null && 
									checkDentalCond.equalsIgnoreCase("Y"))
								)
							)
					{
						int count=claimsService.getCount(caseId,roleId);
						String  orgFlag=claimsService.getorgFlag(caseId);
						SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				    	//String d1="13/10/2019 00:00:00";
				    	String d1=config.getString("EHF.Claims.CPD.TTP");
			            Date date1 = sdfNew.parse(d1);
			           
			            
				    	Date date2 = sdfNew.parse(claimFlowVO.getPreAuthDate().toString());
						request.setAttribute("chPendingCount", count);
						if(count<2 && roleId.equalsIgnoreCase(config.getString("Group.COCH")) && checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
							{
			    			
							List<LabelBean> newButtonLst =new ArrayList<LabelBean>();
		    				if(buttonList!=null && buttonList.size()>0)
		    					{
		    						for(LabelBean lbLst : buttonList)
		    							{
    										if(lbLst!=null && lbLst.getID()!=null &&
		    										!lbLst.getID().equalsIgnoreCase(config.getString("EHF.PendButton")))
		    									{
		    										newButtonLst.add(lbLst);
		    									}
		    							}
		    						
		    						LabelBean localBean =new LabelBean();
		    						localBean.setID(config.getString("EHF.PendButton"));
		    						localBean.setVALUE("Pending");
		    						newButtonLst.add(localBean);
		    					}
		    				
			    				buttonList=Collections.unmodifiableList(newButtonLst);
			    				claimFlowVO.setButtonList(buttonList);
							}
						else if(count<2 && roleId.equalsIgnoreCase(config.getString("EHF.Claims.CPD")) && orgFlag!=null && !"".equalsIgnoreCase(orgFlag) &&  !"Y".equalsIgnoreCase(orgFlag) && date2.after(date1))
							{
			    				/**
			    				 * newButtonLst added because the Button List returned is an unmodifiable List 
			    				 */
			    				List<LabelBean> newButtonLst =new ArrayList<LabelBean>();
			    				if(buttonList!=null && buttonList.size()>0)
			    					{
			    						for(LabelBean lbLst : buttonList)
			    							{
	    										if(lbLst!=null && lbLst.getID()!=null &&
			    										!lbLst.getID().equalsIgnoreCase(config.getString("EHF.PendButton")))
			    									{
			    										newButtonLst.add(lbLst);
			    									}
			    							}
			    						
			    						LabelBean localBean =new LabelBean();
			    						localBean.setID(config.getString("EHF.PendButton"));
			    						localBean.setVALUE("Pending");
			    						newButtonLst.add(localBean);
			    					}
			    				
			    				buttonList=Collections.unmodifiableList(newButtonLst);
			    				claimFlowVO.setButtonList(buttonList);
							}
						else if(count<2 && roleId.equalsIgnoreCase(config.getString("EHF.Claims.CTD")))						{
		    				/**
		    				 * newButtonLst added because the Button List returned is an unmodifiable List 
		    				 */
		    				List<LabelBean> newButtonLst =new ArrayList<LabelBean>();
		    				if(buttonList!=null && buttonList.size()>0)
		    					{
		    						for(LabelBean lbLst : buttonList)
		    							{
    										if(lbLst!=null && lbLst.getID()!=null &&
		    										!lbLst.getID().equalsIgnoreCase(config.getString("EHF.PendButton")))
		    									{
		    										newButtonLst.add(lbLst);
		    									}
		    							}
		    						
		    						LabelBean localBean =new LabelBean();
		    						localBean.setID(config.getString("EHF.PendButton"));
		    						localBean.setVALUE("Pending");
		    						newButtonLst.add(localBean);
		    					}
		    				
		    				buttonList=Collections.unmodifiableList(newButtonLst);
		    				claimFlowVO.setButtonList(buttonList);
						}
					}
			
            //Medco Details
			claimsFlowForm.setTotalClaim(claimFlowVO.getBillAmt());
			claimsFlowForm.setCpdAprAmt(claimsFlowForm.getTotalClaim());
			claimsFlowForm.setPackageAmt(claimFlowVO.getPackAmt());
			if(claimFlowVO.getPenaltyAmount()!=null && !claimFlowVO.getPenaltyAmount().equalsIgnoreCase(""))
			claimsFlowForm.setPenaltyAmount(claimFlowVO.getPenaltyAmount());
			else 
			claimsFlowForm.setPenaltyAmount("0");	
			claimsFlowForm.setClaimInitAmt(claimFlowVO.getClaimInitAmt());
			//claimsFlowForm.setClaimInitAmt(claimFlowVO.getPackAmt());
			claimsFlowForm.setBillAmt(claimFlowVO.getBillAmt());
			claimsFlowForm.setBillDt(claimFlowVO.getBillDt());
			claimsFlowForm.setMedcoRemark(claimFlowVO.getMedcoRemark());
			claimsFlowForm.setPreauthDate(claimFlowVO.getPreAuthDate());
			claimsFlowForm.setPreAuthApprvDate(claimFlowVO.getPreAuthApprvDate());
			if(claimFlowVO.getTotalConsumableAmount()!=null)
			{
			claimsFlowForm.setTotalConsumableAmount(claimFlowVO.getTotalConsumableAmount());
			}
			else
			{
				claimsFlowForm.setTotalConsumableAmount(0);
			}
			//getting total drug amount
			if(claimFlowVO.getTotalDrugAmt()!=null)
			{
			claimsFlowForm.setTotalDrugAmount(claimFlowVO.getTotalDrugAmt());
			}
			else
			{
				claimsFlowForm.setTotalDrugAmount(0);
			}
			if (claimFlowVO.getClaimSubDt() != null
					&& !claimFlowVO.getClaimSubDt().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				claimsFlowForm.setDtTime(claimFlowVO.getClaimSubDt());
			else
				claimsFlowForm.setDtTime(serverDt);
			claimsFlowForm.setRoleId(roleId);
			// for non tech checklist
			claimsFlowForm.setOnlineAdmDate(claimFlowVO.getAdmDate());
			claimsFlowForm.setOnlineDschrgeDate(claimFlowVO.getDisDate());
			claimsFlowForm.setOnlineSurgDate(claimFlowVO.getSurgDate());
						
			if (claimFlowVO.getNonTechChklst() != null
					&& claimFlowVO.getNonTechChklst().equalsIgnoreCase(
							ClaimsConstants.YES)) {
				claimsFlowForm.setNameCheck(claimFlowVO.getNameCheck());
				claimsFlowForm.setGenderCheck(claimFlowVO.getGenderCheck());
				claimsFlowForm.setBenPhotoCheck(claimFlowVO.getBenPhotoCheck());
				claimsFlowForm.setCaseStAdmDt(claimFlowVO.getCaseStAdmDt());
				claimsFlowForm.setCaseStDischrgDt(claimFlowVO
						.getCaseStDischrgDt());
				claimsFlowForm.setCaseStSurgDt(claimFlowVO.getCaseStSurgDt());
				claimsFlowForm.setAdmDtCheck(claimFlowVO.getAdmDtCheck());
				claimsFlowForm.setSurgDtCheck(claimFlowVO.getSurgDtCheck());
				claimsFlowForm.setDischargeDtCheck(claimFlowVO
						.getDischargeDtCheck());
				claimsFlowForm.setDocVer1(claimFlowVO.getDocVer1());
				claimsFlowForm.setDocVer2(claimFlowVO.getDocVer2());
				// claimsFlowForm.setDocVer3(claimFlowVO.getDocVer3());
				claimsFlowForm.setDocVer4(claimFlowVO.getDocVer4());
				claimsFlowForm.setDocVer5(claimFlowVO.getDocVer5());
				claimsFlowForm.setCexRemark(claimFlowVO.getCexRemark());
				claimsFlowForm.setCexAprAmt(claimFlowVO.getCexAprAmt());
			}
			// for tech checklist
			if (claimFlowVO.getTechChklst() != null
					&& claimFlowVO.getTechChklst().equalsIgnoreCase(
							ClaimsConstants.YES)) {
				claimsFlowForm.setTechCheck1(claimFlowVO.getTechCheck1());
				claimsFlowForm.setTechCheck2(claimFlowVO.getTechCheck2());
				claimsFlowForm.setTechCheck3(claimFlowVO.getTechCheck3());
				claimsFlowForm.setTechCheck4(claimFlowVO.getTechCheck4());
				// claimsFlowForm.setTotalClaim(claimFlowVO.getTotalClaim());
				claimsFlowForm.setDeduction(claimFlowVO.getDeduction());
				claimsFlowForm.setStay(claimFlowVO.getStay());
				claimsFlowForm.setStayRemark(claimFlowVO.getStayRemark());
				claimsFlowForm.setInputs(claimFlowVO.getInputs());
				claimsFlowForm.setInputsRmrk(claimFlowVO.getInputsRmrk());
				claimsFlowForm.setProfFee(claimFlowVO.getProfFee());
				claimsFlowForm.setProfFeeRmrk(claimFlowVO.getProfFeeRmrk());
				claimsFlowForm.setInvestBill(claimFlowVO.getInvestBill());
				claimsFlowForm.setInvestBillRmrk(claimFlowVO
						.getInvestBillRmrk());
				claimsFlowForm.setClaimPanelRemark(claimFlowVO
						.getClaimPanelRemark());
				claimsFlowForm.setCpdAprAmt(claimFlowVO.getCpdAprAmt());
			} else {
				claimsFlowForm.setDeduction(ClaimsConstants.ZERO);
				claimsFlowForm.setStay(ClaimsConstants.ZERO);
				claimsFlowForm.setInputs(ClaimsConstants.ZERO);
				claimsFlowForm.setProfFee(ClaimsConstants.ZERO);
				claimsFlowForm.setInvestBill(ClaimsConstants.ZERO);
				claimsFlowForm.setCpdAprAmt(claimsFlowForm.getTotalClaim());
			}
			// for tech checklist
			if (claimFlowVO.getTechChklstOrg() != null
					&& claimFlowVO.getTechChklstOrg().equalsIgnoreCase(
							ClaimsConstants.YES)) {
				claimsFlowForm.setTechCheckOrg1(claimFlowVO.getTechCheckOrg1());
				claimsFlowForm.setTechCheckOrg2(claimFlowVO.getTechCheckOrg2());
				claimsFlowForm.setTechCheckOrg3(claimFlowVO.getTechCheckOrg3());
				claimsFlowForm.setTechCheckOrg4(claimFlowVO.getTechCheckOrg4());
				// claimsFlowForm.setTotalClaim(claimFlowVO.getTotalClaim());
				claimsFlowForm.setDeductionOrg(claimFlowVO.getDeductionOrg());
				claimsFlowForm.setStayOrg(claimFlowVO.getStayOrg());
				claimsFlowForm.setStayRemarkOrg(claimFlowVO.getStayRemarkOrg());
				claimsFlowForm.setInputsOrg(claimFlowVO.getInputsOrg());
				claimsFlowForm.setInputsRmrkOrg(claimFlowVO.getInputsRmrkOrg());
				claimsFlowForm.setProfFeeOrg(claimFlowVO.getProfFeeOrg());
				claimsFlowForm.setProfFeeRmrkOrg(claimFlowVO.getProfFeeRmrkOrg());
				claimsFlowForm.setInvestBillOrg(claimFlowVO.getInvestBillOrg());
				claimsFlowForm.setInvestBillRmrkOrg(claimFlowVO
						.getInvestBillRmrkOrg());
				claimsFlowForm.setClaimPanelRemarkOrg(claimFlowVO
						.getClaimPanelRemarkOrg());
				claimsFlowForm.setCpdAprAmtOrg(claimFlowVO.getCpdAprAmtOrg());
			} else {
				claimsFlowForm.setDeductionOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setStayOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setInputsOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setProfFeeOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setInvestBillOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setCpdAprAmtOrg(claimsFlowForm.getTotalClaim());
			}
			// for trust doc checlList
			if (claimFlowVO.getTrstDocChklst() != null
					&& claimFlowVO.getTrstDocChklst().equalsIgnoreCase(
							ClaimsConstants.YES)) {
				claimsFlowForm.setTrustDoc1(claimFlowVO.getTrustDoc1());
				claimsFlowForm.setTrustDoc2(claimFlowVO.getTrustDoc2());
				claimsFlowForm.setTrustDoc3(claimFlowVO.getTrustDoc3());
				claimsFlowForm.setTrustDoc4(claimFlowVO.getTrustDoc4());
				claimsFlowForm.setCtdRemark(claimFlowVO.getCtdRemark());
				claimsFlowForm.setCtdAprAmt(claimFlowVO.getCtdAprAmt());
			}
			//for CH,EO,EO-Comm,ACO
			claimsFlowForm.setChRemark(claimFlowVO.getChRemark());
			claimsFlowForm.setChAprAmt(claimFlowVO.getChAprAmt());
			claimsFlowForm.setChEntAprAmt(claimFlowVO.getChEntAprAmt());
			claimsFlowForm.setChNabhAmt(claimFlowVO.getChNabhAmt());
			claimsFlowForm.setEoRemark(claimFlowVO.getEoRemark());
			claimsFlowForm.setEoAprAmt(claimFlowVO.getEoAprAmt());
			claimsFlowForm.setEoComRemark(claimFlowVO.getEoComRemark());
			claimsFlowForm.setEoComAprAmt(claimFlowVO.getEoComAprAmt());
			claimsFlowForm.setEoComEntAprAmt(claimFlowVO.getEoComEntAprAmt());
			claimsFlowForm.setEoComNabhAmt(claimFlowVO.getEoComNabhAmt());
			claimsFlowForm.setAcoRemark(claimFlowVO.getAcoRemark());
			claimsFlowForm.setAcoAprAmt(claimFlowVO.getAcoAprAmt());
            //for Err cases
			if (claimFlowVO.getErrClaimStatus() != null
					&& !claimFlowVO.getErrClaimStatus().equalsIgnoreCase(
							ClaimsConstants.EMPTY)) {
				claimsFlowForm.setDispErrInitBlock(ClaimsConstants.YES);
				claimsFlowForm.setErrAmount(claimFlowVO.getErrAmt());
				claimsFlowForm.setErrClaimPaid(claimFlowVO.getClaimPaidAmt()
						.toString());
				claimsFlowForm.setErrSubDate(claimFlowVO.getErrClaimSubDt());
				claimsFlowForm.setErrMedRemark(claimFlowVO.getErrMedcoRemark());
				claimsFlowForm.setErrCtdRemark(claimFlowVO.getErrCtdRemark());
				claimsFlowForm.setErrAppAmount(claimFlowVO.getErrCtdAprAmt());
				claimsFlowForm.setErrChRemark(claimFlowVO.getErrChRemark());
				claimsFlowForm.setErrChAppAmount(claimFlowVO.getErrChAprAmt());
				claimsFlowForm.setErrAcoRemark(claimFlowVO.getErrAcoRemark());
				claimsFlowForm.setErrAcoAprAmt(claimFlowVO.getErrAcoAprAmt());
			}
			//If role is medco
			if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.MEDCO"))) {
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.DISCHARGE"))) {
					//calculating Claim Amount
					//claimFlowVO = claimsService
					//		.calculateClaimAmount(claimFlowVO);
					if(claimFlowVO.getPenaltyAmount()!=null && !claimFlowVO.getPenaltyAmount().equalsIgnoreCase(""))
					{
						Long totalPreauthAmt =  Long.parseLong(claimFlowVO.getPackAmt());
						Long penaltyAmt =  Long.parseLong(claimFlowVO.getPenaltyAmount());
						Long claimInitAmt = totalPreauthAmt - penaltyAmt;
						claimFlowVO.setClaimInitAmt(claimInitAmt.toString());
					}
					else{
						claimFlowVO.setClaimInitAmt(claimFlowVO.getPackAmt());
						claimsFlowForm.setPenaltyAmount("0");
					}					
					if(claimFlowVO.getTotalConsumableAmount()!=null)
					{
					claimsFlowForm.setTotalConsumableAmount(claimFlowVO.getTotalConsumableAmount());
					Double totalInitAmount=0.0;					
					//modified for medical amount calculation
					if(claimsFlowForm.getMedicalSurgFlag()!=null && claimsFlowForm.getMedicalSurgFlag().equalsIgnoreCase(ClaimsConstants.MEDICAL_FLAG))
					{													
							 totalInitAmount=Double.parseDouble(claimFlowVO.getTotalConsumableAmount().toString());
							claimFlowVO.setClaimInitAmt(totalInitAmount.toString());												
					}
					else
					{
						if(claimFlowVO.getClaimInitAmt()!=null)
							 totalInitAmount=Double.parseDouble(claimFlowVO.getTotalConsumableAmount().toString())+Double.parseDouble(claimFlowVO.getClaimInitAmt());
							claimFlowVO.setClaimInitAmt(totalInitAmount.toString());
					}
					
					}
					else
					{
						claimsFlowForm.setTotalConsumableAmount(0);
					}
					
					//getting Total Drug Amount
					if(claimsFlowForm.getMedicalSurgFlag()!=null && claimsFlowForm.getMedicalSurgFlag().equalsIgnoreCase(ClaimsConstants.MEDICAL_FLAG))
					{	
				
					if(claimFlowVO.getTotalDrugAmt()!=null)
					{
					claimsFlowForm.setTotalDrugAmount(claimFlowVO.getTotalDrugAmt());
					Double totalInitAmount=0.0;			
					Double totConsumable=0.0;
					
					if(claimFlowVO.getTotalConsumableAmount() !=null)
						 totConsumable=Double.parseDouble(claimFlowVO.getTotalConsumableAmount().toString());
					 totalInitAmount=Double.parseDouble(claimFlowVO.getTotalDrugAmt().toString())+totConsumable;					 				
					 claimFlowVO.setClaimInitAmt(totalInitAmount.toString());
					}
					else
					{
						claimsFlowForm.setTotalDrugAmount(0);
					}
					}
					claimsFlowForm.setClaimInitAmt(claimFlowVO.getClaimInitAmt());
					claimsFlowForm.setAddAttach(ClaimsConstants.YES);
					claimsFlowForm.setViewAttach(ClaimsConstants.NO);
					if (claimFlowVO.getDaysDiff() != null
							&& claimFlowVO.getDaysDiff() < 11) {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_VALID_MSG);
						claimsFlowForm.setRoleId(roleId);
					}
					if(claimFlowVO.getFlagged()!=null && claimFlowVO.getFlagged().equalsIgnoreCase("Y"))	
					{
					String flagMoney = claimsService.checkFlaggingForMoneyCollection(claimFlowVO);
					if(flagMoney!=null && flagMoney.equalsIgnoreCase("Y")){
					claimsFlowForm.setMsg(ClaimsConstants.CLAIM_MONEYFLAGED_MSG);
					}
					}/*claimFlowVO.getHospActiveYN().equalsIgnoreCase("S")||claimFlowVO.getHospActiveYN().equalsIgnoreCase("C")||*/
					if(claimFlowVO.getHospActiveYN()!=null)
						{
							if(claimFlowVO.getHospActiveYN().equalsIgnoreCase("CB"))
								claimsFlowForm.setMsg(ClaimsConstants.CLAIM_CB_HOSP_SUSPENSION_MSG);
							else if(claimFlowVO.getHospActiveYN().equalsIgnoreCase("SP"))
								claimsFlowForm.setMsg(ClaimsConstants.CLAIM_SP_HOSP_SUSPENSION_MSG);
							else if(claimFlowVO.getHospActiveYN().equalsIgnoreCase("CP"))
								claimsFlowForm.setMsg(ClaimsConstants.CLAIM_CP_HOSP_SUSPENSION_MSG);
							else if(claimFlowVO.getHospActiveYN().equalsIgnoreCase("CBP"))
								claimsFlowForm.setMsg(ClaimsConstants.CLAIM_CBP_HOSP_SUSPENSION_MSG);
							//claimsFlowForm.setMsg(ClaimsConstants.CLAIM_HOSP_SUSPENSION_MSG);
						}
					else if(claimFlowVO.getHospActiveYN()==null)
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_HOSP_SUSPENSION_MSG_NULL);
					else
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_HOSP_SUSPENSION_MSG_NULL);
					
				} else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.HeadPending"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.COCTDPending")) ||
								claimFlowVO.getCaseStat().equalsIgnoreCase("CD515")
								|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.ORGCTDPending"))
									|| claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.PanelDocPending"))
										|| claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.HubPendingMedco"))
										|| claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.FirstPanelPending"))
										|| claimFlowVO.getCaseStat().equalsIgnoreCase(
												config.getString("EHF.Claims.TrustDocPending"))
												||claimFlowVO.getCaseStat().equalsIgnoreCase(
														config.getString("EHF.Claims.EoPending"))) {

					claimsFlowForm.setBillAmt(claimFlowVO.getBillAmt());
					claimsFlowForm.setBillDt(claimFlowVO.getBillDt());
					claimsFlowForm.setMedcoRemark(ClaimsConstants.EMPTY);
					
					if(claimFlowVO.getFlagged()!=null && claimFlowVO.getFlagged().equalsIgnoreCase("Y"))	
						{
							String flagMoney = claimsService.checkFlaggingForMoneyCollection(claimFlowVO);
							if(flagMoney!=null && flagMoney.equalsIgnoreCase("Y"))
								{
									claimsFlowForm.setMsg(ClaimsConstants.CLAIM_MONEYFLAGED_MSG);
								}
						}
					
					//calculating Claim Amount
					//claimFlowVO = claimsService
					//		.calculateClaimAmount(claimFlowVO);
					if(claimFlowVO.getPenaltyAmount()!=null && !claimFlowVO.getPenaltyAmount().equalsIgnoreCase(""))
					{
						Long totalPreauthAmt =  Long.parseLong(claimFlowVO.getPackAmt());
						Long penaltyAmt =  Long.parseLong(claimFlowVO.getPenaltyAmount());
						Long claimInitAmt = totalPreauthAmt - penaltyAmt;
						claimFlowVO.setClaimInitAmt(claimInitAmt.toString());
					}
					else{
						claimFlowVO.setClaimInitAmt(claimFlowVO.getPackAmt());
						claimsFlowForm.setPenaltyAmount("0");
					}	
					claimsFlowForm.setClaimInitAmt(claimFlowVO
							.getClaimInitAmt());
					claimsFlowForm.setAddAttach(ClaimsConstants.YES);
					claimsFlowForm.setViewAttach(ClaimsConstants.NO);
					
					request.setAttribute("pendFlag", "Y");
					//claimsFlowForm.setDtTime(serverDt);
				} else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.ClaimPaid"))) {

					Long errClaimAmt = 0L;
					if(claimFlowVO.getPenaltyAmount()==null)
						claimFlowVO.setPenaltyAmount("0");
					errClaimAmt = Long.parseLong(claimFlowVO.getPackAmt())
							- claimFlowVO.getClaimPaidAmt()- Long.parseLong(claimFlowVO.getPenaltyAmount());
					if (errClaimAmt != null && errClaimAmt > 0
							&& claimFlowVO.getErrClaimStatus() == null) {
						if (saveFlag != null
								&& !saveFlag
										.equalsIgnoreCase(ClaimsConstants.TRUE)) {
							// getting list of buttons for specific role and
							// status
							buttonList = commonService.getDynamicWrkFlowDtls(
									claimFlowVO.getCaseStat(), roleId,
									config.getString("EHF.Claims"),
									ClaimsConstants.ERR_CLAIM);
							claimFlowVO.setButtonList(buttonList);
							if (buttonList.size() == 0)
								request.setAttribute("disableAll",
										ClaimsConstants.YES);
							else
								request.setAttribute("disableAll",
										ClaimsConstants.EMPTY);
						} else {
							claimsFlowForm.setRoleId(ClaimsConstants.EMPTY);
						}

						claimsFlowForm.setDispErrInitBlock(ClaimsConstants.YES);
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
							claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
						claimsFlowForm.setShowCh(ClaimsConstants.YES);
						claimsFlowForm.setShowAco(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd1(ClaimsConstants.NO);
						claimsFlowForm.setShowCh1(ClaimsConstants.NO);
						claimsFlowForm.setShowAco1(ClaimsConstants.NO);
						claimsFlowForm.setErrAmount(errClaimAmt.toString());
						claimsFlowForm.setErrClaimPaid(claimFlowVO
								.getClaimPaidAmt().toString());
						claimsFlowForm.setErrSubDate(serverDt);

					} else {
						if (claimFlowVO.getErrClaimStatus() != null
								&& !claimFlowVO
										.getErrClaimStatus()
										.equalsIgnoreCase(ClaimsConstants.EMPTY)) {
							claimsFlowForm
									.setErrAmount(claimFlowVO.getErrAmt());
							claimsFlowForm.setErrClaimPaid(claimFlowVO
									.getClaimPaidAmt().toString());
							claimsFlowForm.setErrSubDate(claimFlowVO
									.getErrClaimSubDt());
							// claimsFlowForm.setErrMedRemark(claimFlowVO.getErrMedcoRemark());
							claimsFlowForm
									.setErrMedRemark(ClaimsConstants.EMPTY);
							claimsFlowForm.setShowCtd1(ClaimsConstants.NO);
							claimsFlowForm.setShowCh1(ClaimsConstants.NO);
							claimsFlowForm.setShowAco1(ClaimsConstants.NO);
							if (claimFlowVO.getErrClaimStatus() != null
									&& claimFlowVO
											.getErrClaimStatus()
											.equalsIgnoreCase(
													config.getString("EHF.Claims.CTDPendErr"))
									|| claimFlowVO
											.getErrClaimStatus()
											.equalsIgnoreCase(
													config.getString("EHF.Claims.CHPendErr"))) {
								buttonList = commonService
										.getDynamicWrkFlowDtls(
												claimFlowVO.getErrClaimStatus(),
												roleId,
												config.getString("EHF.Claims"),
												ClaimsConstants.ERR_CLAIM);
								claimFlowVO.setButtonList(buttonList);
								if (buttonList.size() == 0)
									request.setAttribute("disableAll",
											ClaimsConstants.YES);
								else
									request.setAttribute("disableAll",
											ClaimsConstants.EMPTY);
							}

						}
					}
				} else {
					if (claimFlowVO.getClaimSubDt() == null) {
						claimsFlowForm
								.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}
				if (claimFlowVO.getErrClaimStatus() != null
						&& !claimFlowVO.getErrClaimStatus().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					claimsFlowForm.setDispErrInitBlock(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowAco(ClaimsConstants.YES);
					claimsFlowForm.setErrAmount(claimFlowVO.getErrAmt());
					claimsFlowForm.setErrClaimPaid(claimFlowVO
							.getClaimPaidAmt().toString());
					claimsFlowForm
							.setErrSubDate(claimFlowVO.getErrClaimSubDt());
					if (claimFlowVO.getErrClaimStatus() != null
							&& claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.CTDPendErr"))
							|| claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.CHPendErr"))) {
						claimsFlowForm.setErrMedRemark("");
					} else
						claimsFlowForm.setErrMedRemark(claimFlowVO
								.getErrMedcoRemark());
				}

			} else if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.CEX"))) {         //If role is CEX

				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.Initiated"))) {

					claimsFlowForm.setNameCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setGenderCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setBenPhotoCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStAdmDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStDischrgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStSurgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setAdmDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setSurgDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDischargeDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer1(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer2(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer3(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer4(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer5(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
				} else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.CHPendUpdated"))) {

					claimsFlowForm.setNameCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setGenderCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setBenPhotoCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStAdmDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStDischrgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStSurgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setAdmDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setSurgDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDischargeDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer1(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer2(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer3(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer4(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer5(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
				} else {
					if (claimFlowVO.getNonTechChklst() != null
							&& claimFlowVO.getNonTechChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			}
			
			else if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.COC.CEX"))) {         //If role is Coc-CEX

				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.Coclear.Initiated"))) {

					claimsFlowForm.setNameCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setGenderCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setBenPhotoCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStAdmDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStDischrgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStSurgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setAdmDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setSurgDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDischargeDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer1(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer2(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer3(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer4(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer5(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
				} /*else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.Coclear.CHPendUpdated"))) {

					claimsFlowForm.setNameCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setGenderCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setBenPhotoCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStAdmDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStDischrgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStSurgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setAdmDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setSurgDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDischargeDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer1(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer2(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer3(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer4(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer5(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
				} */else {
					if (claimFlowVO.getNonTechChklst() != null
							&& claimFlowVO.getNonTechChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			}
			
			else if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.ORG.CEX"))) {         //If role is organ-CEX

				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.Organ.Initiated"))) {

					claimsFlowForm.setNameCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setGenderCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setBenPhotoCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStAdmDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStDischrgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setCaseStSurgDt(ClaimsConstants.EMPTY);
					claimsFlowForm.setAdmDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setSurgDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDischargeDtCheck(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer1(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer2(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer3(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer4(ClaimsConstants.EMPTY);
					claimsFlowForm.setDocVer5(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCexAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
				} else {
					if (claimFlowVO.getNonTechChklst() != null
							&& claimFlowVO.getNonTechChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			}
			
			else if (roleId != null
					&& (roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.CPD")) ||
							roleId.equalsIgnoreCase(config
									.getString("EHF.Claims.COCCMT"))		//If role is CPD or Coclear Commitee 
							|| roleId.equalsIgnoreCase(config
									.getString("EHF.Claims.ORGCMT")))) {   //IF ROLE IS ORGAN COMMITTEE
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.Forwarded"))||
						claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.By.COCCEX"))
							|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.CPDPendUpdated"))
							|| claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.CPDPendNotUpdated"))
							|| claimFlowVO.getCaseStat().equalsIgnoreCase(
									config.getString("EHF.Claims.Forwarded.By.ORGCEX"))||claimFlowVO.getCaseStat().equalsIgnoreCase(
									config.getString("EHF.Claims.SENDBACKTOCPDBYEOCOM"))
									|| claimFlowVO.getCaseStat().equalsIgnoreCase(
											config.getString("EHF.Claims.FirstPanelApprove"))
										|| claimFlowVO.getCaseStat().equalsIgnoreCase(
											config.getString("EHF.Claims.SecondPanelApprove"))
											|| claimFlowVO.getCaseStat().equalsIgnoreCase(
													config.getString("EHF.Claims.FirstPanelPendingUpdated"))	
											)	{
					if(!(claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.FirstPanelApprove"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.SecondPanelApprove"))
						|| (claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.CPDPendUpdated")) && (orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew)))
							))
					{
					if(!claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.SENDBACKTOCPDBYEOCOM"))){
					claimsFlowForm.setTechCheck1(ClaimsConstants.EMPTY);
					claimsFlowForm.setTechCheck2(ClaimsConstants.EMPTY);
					claimsFlowForm.setTechCheck3(ClaimsConstants.EMPTY);
					claimsFlowForm.setTechCheck4(ClaimsConstants.EMPTY);
					claimsFlowForm.setDeduction(ClaimsConstants.ZERO);
					claimsFlowForm.setStay(ClaimsConstants.ZERO);
					claimsFlowForm.setInputs(ClaimsConstants.ZERO);
					claimsFlowForm.setProfFee(ClaimsConstants.ZERO);
					claimsFlowForm.setInvestBill(ClaimsConstants.ZERO);
					claimsFlowForm.setStayRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setInputsRmrk(ClaimsConstants.EMPTY);
					claimsFlowForm.setProfFeeRmrk(ClaimsConstants.EMPTY);
					claimsFlowForm.setInvestBillRmrk(ClaimsConstants.EMPTY);
					claimsFlowForm.setClaimPanelRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCpdAprAmt(claimsFlowForm.getTotalClaim());

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					}
					else{
						claimsFlowForm.setAddAttach(ClaimsConstants.NO);
						claimsFlowForm.setViewAttach(ClaimsConstants.YES);
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
						claimsFlowForm.setShowCh(ClaimsConstants.YES);
						claimsFlowForm.setShowEo(ClaimsConstants.YES);
						claimsFlowForm.setShowEoCom(ClaimsConstants.YES);
					}
					}
					if(claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.FirstPanelApprove"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.SecondPanelApprove"))
						|| (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.CPDPendUpdated")) && (orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew)))		
							)
			{
						if(!claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.SENDBACKTOCPDBYEOCOM"))){
				claimsFlowForm.setTechCheckOrg1(ClaimsConstants.EMPTY);
				claimsFlowForm.setTechCheckOrg2(ClaimsConstants.EMPTY);
				claimsFlowForm.setTechCheckOrg3(ClaimsConstants.EMPTY);
				claimsFlowForm.setTechCheckOrg4(ClaimsConstants.EMPTY);
				claimsFlowForm.setDeductionOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setStayOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setInputsOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setProfFeeOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setInvestBillOrg(ClaimsConstants.ZERO);
				claimsFlowForm.setStayRemarkOrg(ClaimsConstants.EMPTY);
				claimsFlowForm.setInputsRmrkOrg(ClaimsConstants.EMPTY);
				claimsFlowForm.setProfFeeRmrkOrg(ClaimsConstants.EMPTY);
				claimsFlowForm.setInvestBillRmrkOrg(ClaimsConstants.EMPTY);
				claimsFlowForm.setClaimPanelRemarkOrg(ClaimsConstants.EMPTY);
				claimsFlowForm.setCpdAprAmtOrg(claimsFlowForm.getTotalClaim());

				claimsFlowForm.setAddAttach(ClaimsConstants.NO);
				claimsFlowForm.setViewAttach(ClaimsConstants.YES);
				claimsFlowForm.setShowCex(ClaimsConstants.YES);
				claimsFlowForm.setShowCpd(ClaimsConstants.YES);
				claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
			}
					else{
						claimsFlowForm.setAddAttach(ClaimsConstants.NO);
						claimsFlowForm.setViewAttach(ClaimsConstants.YES);
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
						claimsFlowForm.setShowCh(ClaimsConstants.YES);
						claimsFlowForm.setShowEo(ClaimsConstants.YES);
						claimsFlowForm.setShowEoCom(ClaimsConstants.YES);
					}
			}
				}

				else {
					if (claimFlowVO.getTechChklst() != null
							&& claimFlowVO.getTechChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						if(claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.FirstPanelApprove"))
							|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.SecondPanelApprove"))
								|| (claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.CPDPendUpdated")) && (orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew)))			
									)
								
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					}
					else if (claimFlowVO.getTechChklstOrg() != null
							&& claimFlowVO.getTechChklstOrg().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						if(claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.FirstPanelApprove"))
							|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.SecondPanelApprove"))
								|| (claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.CPDPendUpdated")) && (orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew)))			
								)
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					}
					
					 else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			}
//vijay
			else if (roleId != null
					&& (roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.HUB"))		//If role is HUB in claim 
							)) {  
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.Forwarded.HUB")) || claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.Pending.HUB")))	 {

					claimsFlowForm.setTechCheck1(ClaimsConstants.EMPTY);
					claimsFlowForm.setTechCheck2(ClaimsConstants.EMPTY);
					claimsFlowForm.setTechCheck3(ClaimsConstants.EMPTY);
					claimsFlowForm.setTechCheck4(ClaimsConstants.EMPTY);
					claimsFlowForm.setDeduction(ClaimsConstants.ZERO);
					claimsFlowForm.setStay(ClaimsConstants.ZERO);
					claimsFlowForm.setInputs(ClaimsConstants.ZERO);
					claimsFlowForm.setProfFee(ClaimsConstants.ZERO);
					claimsFlowForm.setInvestBill(ClaimsConstants.ZERO);
					claimsFlowForm.setStayRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setInputsRmrk(ClaimsConstants.EMPTY);
					claimsFlowForm.setProfFeeRmrk(ClaimsConstants.EMPTY);
					claimsFlowForm.setInvestBillRmrk(ClaimsConstants.EMPTY);
					claimsFlowForm.setClaimPanelRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCpdAprAmt(claimsFlowForm.getTotalClaim());

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
				}

				else {
					if (claimFlowVO.getTechChklst() != null
							&& claimFlowVO.getTechChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			}
			else if (roleId != null
					&& (roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.CTD"))||roleId.equalsIgnoreCase(config
									.getString("EHF.Claims.COCTD"))
							||roleId.equalsIgnoreCase(config
											.getString("EHF.Claims.ORGCTD")))) {                   //If role is CTD 
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.PanelDocApprove"))
						||claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.CocCmtApprove"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.PanelDocReject"))
								|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.HUB.ForwardClaim"))
								|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.HUB.RecommenndReject"))
						||claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.CocCmtReject"))		
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.CocCmtPending"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.DissClearCTD"))
								|| claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.CTDPendUpdated"))
					|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.ORGCmtApprove"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.ORGCmtPending"))
						||claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.ORGCmtReject"))
						||claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.Organ.CTDPendUpdated"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.Heamodialysis.Forwarded"))
						||		
						(request.getParameter("caseApprovalFlag") != null
								&& request.getParameter("caseApprovalFlag").equalsIgnoreCase("Y")
								&& (config.getString("claim_Dent_panelDocStatus").contains("~"+claimFlowVO.getCaseStat()+"~"))
								&& checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
						)		
					 {

					claimsFlowForm.setTrustDoc1(ClaimsConstants.EMPTY);
					claimsFlowForm.setTrustDoc2(ClaimsConstants.EMPTY);
					claimsFlowForm.setTrustDoc3(ClaimsConstants.EMPTY);
					claimsFlowForm.setTrustDoc4(ClaimsConstants.EMPTY);
					claimsFlowForm.setCtdRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCtdAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					
					if(roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.COCTD")))
							{
								claimsFlowForm.setCocFinal((ClaimsConstants.YES));
							}
						else if(roleId.equalsIgnoreCase(config
								.getString("EHF.Claims.ORGCTD")))
							{
								claimsFlowForm.setCocFinal((ClaimsConstants.YES));
							}
					else
						claimsFlowForm.setCocFinal((ClaimsConstants.NO));

				}
				else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.ClaimKeptDiscuCTD"))) {
					claimsFlowForm.setTrustDoc1(ClaimsConstants.EMPTY);
					claimsFlowForm.setTrustDoc2(ClaimsConstants.EMPTY);
					claimsFlowForm.setTrustDoc3(ClaimsConstants.EMPTY);
					claimsFlowForm.setTrustDoc4(ClaimsConstants.EMPTY);
					claimsFlowForm.setCtdRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setCtdAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.YES);
					claimsFlowForm.setViewAttach(ClaimsConstants.NO);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
				} else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.ClaimPaid"))) {
					if (saveFlag != null
							&& !saveFlag.equalsIgnoreCase(ClaimsConstants.TRUE)) {
						// getting list of buttons for specific role and status
						if (claimFlowVO.getErrClaimStatus() != null
								&& !claimFlowVO
										.getErrClaimStatus()
										.equalsIgnoreCase(ClaimsConstants.EMPTY)) {
							buttonList = commonService.getDynamicWrkFlowDtls(
									claimFlowVO.getErrClaimStatus(), roleId,
									config.getString("EHF.Claims"),
									ClaimsConstants.ERR_CLAIM);
							claimFlowVO.setButtonList(buttonList);
						}
						if (buttonList != null && buttonList.size() == 0) {
							buttonList = commonService.getDynamicWrkFlowDtls(
									claimFlowVO.getCaseStat(), roleId,
									config.getString("EHF.Claims"),
									ClaimsConstants.ERR_CLAIM);
							claimFlowVO.setButtonList(buttonList);
						}

						if (buttonList.size() == 0)
							request.setAttribute("disableAll",
									ClaimsConstants.YES);
						else
							request.setAttribute("disableAll",
									ClaimsConstants.EMPTY);
					} else {
						claimsFlowForm.setRoleId(ClaimsConstants.EMPTY);
					}
					claimsFlowForm.setDispErrInitBlock(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowAco(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd1(ClaimsConstants.YES);
					claimsFlowForm.setShowCh1(ClaimsConstants.NO);
					claimsFlowForm.setShowAco1(ClaimsConstants.NO);
					if (claimFlowVO.getErrClaimStatus() != null
							&& (claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.ErrReInit")) || claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.chErrReInit")))) {
						claimsFlowForm.setErrCtdRemark(ClaimsConstants.EMPTY);
						claimsFlowForm.setErrAppAmount(ClaimsConstants.EMPTY);
					} else {
						claimsFlowForm.setErrCtdRemark(claimFlowVO
								.getErrCtdRemark());
						claimsFlowForm.setErrAppAmount(claimFlowVO
								.getErrCtdAprAmt());
					}
				} else {
					if (claimFlowVO.getTrstDocChklst() != null
							&& claimFlowVO.getTrstDocChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			} else if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.CH"))) {                   //If role is CH
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.TrustDocApprove"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.PanelDocApprove"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.PanelDocReject"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.TrustDocReject"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.SentBackCH"))
							|| claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.CTDPendUpdated"))	
					  || claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.Heamodialysis.Forwarded"))
								|| claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.HUB.ForwardClaim"))	
											|| claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.HUB.RecommenndReject"))	
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.DissClearCH"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.CHPendUpdated")) 
					|| claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.CHPendNotUpdated")))	{

					claimsFlowForm.setChRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setChAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
					{
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					}
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowEo(ClaimsConstants.NO);
					claimsFlowForm.setShowEoCom(ClaimsConstants.NO);
				} else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.ClaimKeptDiscuCH"))) {
					claimsFlowForm.setChRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setChAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.YES);
					claimsFlowForm.setViewAttach(ClaimsConstants.NO);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
					{
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					}
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
				} else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.ClaimPaid"))) {
					if (saveFlag != null
							&& !saveFlag.equalsIgnoreCase(ClaimsConstants.TRUE)) {
						// getting list of buttons for specific role and status
						if (claimFlowVO.getErrClaimStatus() != null
								&& !claimFlowVO
										.getErrClaimStatus()
										.equalsIgnoreCase(ClaimsConstants.EMPTY)) {
							buttonList = commonService.getDynamicWrkFlowDtls(
									claimFlowVO.getErrClaimStatus(), roleId,
									config.getString("EHF.Claims"),
									ClaimsConstants.ERR_CLAIM);
							claimFlowVO.setButtonList(buttonList);
						}
						if (buttonList != null && buttonList.size() == 0) {

							buttonList = commonService.getDynamicWrkFlowDtls(
									claimFlowVO.getCaseStat(), roleId,
									config.getString("EHF.Claims"),
									ClaimsConstants.ERR_CLAIM);

							claimFlowVO.setButtonList(buttonList);
						}
						if (buttonList.size() == 0)
							request.setAttribute("disableAll",
									ClaimsConstants.YES);
						else
							request.setAttribute("disableAll",
									ClaimsConstants.EMPTY);
					} else {
						claimsFlowForm.setRoleId(ClaimsConstants.EMPTY);
					}
					claimsFlowForm.setDispErrInitBlock(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowAco(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd1(ClaimsConstants.YES);
					claimsFlowForm.setShowCh1(ClaimsConstants.YES);
					claimsFlowForm.setShowAco1(ClaimsConstants.NO);
					if (claimFlowVO.getErrClaimStatus() != null
							&& claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.chErrReInit"))) {
						claimsFlowForm.setErrChAppAmount(ClaimsConstants.EMPTY);
						claimsFlowForm.setErrChRemark(ClaimsConstants.EMPTY);
					}

				} else {
					if ((claimFlowVO.getTrstDocChklst() != null
							&& claimFlowVO.getTrstDocChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) || (claimFlowVO.getCaseStat().equalsIgnoreCase(
											config.getString("EHF.Claims.ACOReverted")))) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
							claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
						claimsFlowForm.setShowCh(ClaimsConstants.YES);

					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			} else if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.EO"))) {                     //If role is EO
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.RecomedToEO"))
						||claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.EOPendUpdated"))) {
					claimsFlowForm.setEoRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setEoAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowEo(ClaimsConstants.YES);
				} else {
					if (claimFlowVO.getTrstDocChklst() != null
							&& claimFlowVO.getTrstDocChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
						claimsFlowForm.setShowCh(ClaimsConstants.YES);
						claimsFlowForm.setShowEo(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}
			} else if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.EOComm"))) {                      //If role is EO-Comm
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.EOApproved"))||claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.SENDBACKTUPDATEDBYMEDCO"))) {
					claimsFlowForm.setEoComRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setEoComAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowEo(ClaimsConstants.YES);
					claimsFlowForm.setShowEoCom(ClaimsConstants.YES);
				} else {
					if (claimFlowVO.getTrstDocChklst() != null
							&& claimFlowVO.getTrstDocChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
							claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
						claimsFlowForm.setShowCh(ClaimsConstants.YES);
						claimsFlowForm.setShowEo(ClaimsConstants.YES);
						claimsFlowForm.setShowEoCom(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}
			} else if (roleId != null
					&& roleId.equalsIgnoreCase(config
							.getString("EHF.Claims.ACO"))) {                         //If role is ACO
				if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.HeadApprove"))
						|| claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.EOCommApproved"))
						||claimFlowVO.getCaseStat().equalsIgnoreCase(
								config.getString("EHF.Claims.cocCDApprove"))
						||claimFlowVO.getCaseStat().equalsIgnoreCase(
										config.getString("EHF.Claims.ORGCTDApprove"))) {

					claimsFlowForm.setAcoRemark(ClaimsConstants.EMPTY);
					claimsFlowForm.setAcoAprAmt(ClaimsConstants.EMPTY);

					claimsFlowForm.setAddAttach(ClaimsConstants.NO);
					claimsFlowForm.setViewAttach(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					
					if(claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.cocCDApprove")))
						claimsFlowForm.setShowCh(ClaimsConstants.NO);
					
					if(claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.ORGCTDApprove")))
						claimsFlowForm.setShowCh(ClaimsConstants.NO);
					claimsFlowForm.setShowAco(ClaimsConstants.YES);
				} else if (claimFlowVO.getCaseStat().equalsIgnoreCase(
						config.getString("EHF.Claims.ClaimPaid"))) {
					if (saveFlag != null
							&& !saveFlag.equalsIgnoreCase(ClaimsConstants.TRUE)) {
						// getting list of buttons for specific role and status
						if (claimFlowVO.getErrClaimStatus() != null
								&& !claimFlowVO
										.getErrClaimStatus()
										.equalsIgnoreCase(ClaimsConstants.EMPTY)) {
							buttonList = commonService.getDynamicWrkFlowDtls(
									claimFlowVO.getErrClaimStatus(), roleId,
									config.getString("EHF.Claims"),
									ClaimsConstants.ERR_CLAIM);
							claimFlowVO.setButtonList(buttonList);
						}
						if (buttonList != null && buttonList.size() == 0) {

							buttonList = commonService.getDynamicWrkFlowDtls(
									claimFlowVO.getCaseStat(), roleId,
									config.getString("EHF.Claims"),
									ClaimsConstants.ERR_CLAIM);

							claimFlowVO.setButtonList(buttonList);
						}
						if (buttonList.size() == 0)
							request.setAttribute("disableAll",
									ClaimsConstants.YES);
						else
							request.setAttribute("disableAll",
									ClaimsConstants.EMPTY);
					} else {
						claimsFlowForm.setRoleId(ClaimsConstants.EMPTY);
					}
					claimsFlowForm.setDispErrInitBlock(ClaimsConstants.YES);
					claimsFlowForm.setShowCex(ClaimsConstants.YES);
					claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					claimsFlowForm.setShowCh(ClaimsConstants.YES);
					claimsFlowForm.setShowAco(ClaimsConstants.YES);
					claimsFlowForm.setShowCtd1(ClaimsConstants.YES);
					claimsFlowForm.setShowCh1(ClaimsConstants.YES);
					claimsFlowForm.setShowAco1(ClaimsConstants.YES);
				} else {
					if (claimFlowVO.getTrstDocChklst() != null
							&& claimFlowVO.getTrstDocChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
						if(orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew))
							claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
						claimsFlowForm.setShowCh(ClaimsConstants.YES);
						claimsFlowForm.setShowAco(ClaimsConstants.YES);
					} else {
						claimsFlowForm.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
				}

			} else {
				if (roleId != null) {
					claimsFlowForm.setMsg(ClaimsConstants.NOT_Auth_MSG);
					claimsFlowForm.setRoleId(roleId);
					lStrResultPage = "viewClaimPage";
				} else {
					if (claimFlowVO.getClaimInitFlag() != null
							&& claimFlowVO.getClaimInitFlag().equalsIgnoreCase(
									ClaimsConstants.NO)) {
						claimsFlowForm.setMsg(ClaimsConstants.NOT_Auth_MSG);
						claimsFlowForm.setRoleId(roleId);
						lStrResultPage = "viewClaimPage";
					}
					claimsFlowForm.setRoleId(ClaimsConstants.EMPTY);
					if (claimFlowVO.getNonTechChklst() != null
							&& claimFlowVO.getNonTechChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCex(ClaimsConstants.YES);
					}
					if (claimFlowVO.getTechChklst() != null
							&& claimFlowVO.getTechChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCpd(ClaimsConstants.YES);
					}
					if (claimFlowVO.getTechChklstOrg() != null
							&& claimFlowVO.getTechChklstOrg().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCpdOrg(ClaimsConstants.YES);
					}
					if (claimFlowVO.getTrstDocChklst() != null
							&& claimFlowVO.getTrstDocChklst().equalsIgnoreCase(
									ClaimsConstants.YES)) {
						claimsFlowForm.setShowCtd(ClaimsConstants.YES);
					}
					if (claimFlowVO.getChAprAmt() != null
							&& !claimFlowVO.getChAprAmt().equalsIgnoreCase(
									ClaimsConstants.EMPTY)) {
						claimsFlowForm.setShowCh(ClaimsConstants.YES);
					}
					if (claimFlowVO.getAcoRemark() != null
							&& !claimFlowVO.getAcoRemark().equalsIgnoreCase(
									ClaimsConstants.EMPTY)) {
						claimsFlowForm.setShowAco(ClaimsConstants.YES);
					}

					if (claimFlowVO.getErrCtdAprAmt() != null
							&& !claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.ErrReInit")))
						claimsFlowForm.setShowCtd1(ClaimsConstants.YES);
					if (claimFlowVO.getErrChAprAmt() != null
							&& !claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.ErrReInit")))
						claimsFlowForm.setShowCh1(ClaimsConstants.YES);
					if (claimFlowVO.getErrAcoRemark() != null
							&& !claimFlowVO
									.getErrClaimStatus()
									.equalsIgnoreCase(
											config.getString("EHF.Claims.ErrReInit")))
						claimsFlowForm.setShowAco1(ClaimsConstants.YES);
				}
			}

			if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
				claimsFlowForm.setShowCpd(ClaimsConstants.NO);	
			
			claimsFlowForm.setClaimInfo(claimFlowVO);

			/**
			 * get audit trail dtls for worklist
			 */

			List<ClaimsFlowVO> lstWorkFlow = claimsService
					.getAuditTrail(claimFlowVO);
			claimsFlowForm.setLstworkFlow(lstWorkFlow);
			
			//To show Auditor Names to Respective Users
			for(LabelBean labelBean:groupsList)
				{
					if(labelBean.getID() != null && config.getString("view_claim_audit_names")!=null && config.getString("view_claim_audit_names").contains("~"+labelBean.getID()+"~") )
					{
						request.setAttribute("viewClaimAuditNames", "Y");
						break;	
					}
				}
			if(claimFlowVO.getCaseStat()!=null && (claimFlowVO.getCaseStat().equalsIgnoreCase(
					config.getString("EHF.Claims.FirstPanelApprove")) || claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.SecondPanelApprove")))
						|| (claimFlowVO.getCaseStat().equalsIgnoreCase(
							config.getString("EHF.Claims.CPDPendUpdated")) && (orgFlagNew!=null && "Y".equalsIgnoreCase(orgFlagNew)))	
							)
							{
			request.setAttribute("newCpd", "Y");
							}
		else
			request.setAttribute("newCpd", "N");

			lStrResultPage = "viewClaimPage";

			request.setAttribute("UserRole", claimsFlowForm.getRoleId());

		}
        
		if ("FolowUpClaim".equalsIgnoreCase(lStrActionVal)) {
			lStrResultPage = "folowUpClaimPage";
		}
		//to get Claim Payment in EO_ADMIN login
		if ("getClaimsPaymentRecrds".equalsIgnoreCase(lStrActionVal)) {
			
			String genFlag = request.getParameter("genFlag");
			userName = session.getAttribute("userName").toString();
			ClaimsFlowVO claimsFlowVO = new ClaimsFlowVO();
			String lStrSchemeType = request.getParameter("schemeType");
			
			request.setAttribute("StatusList", claimsService.getCaseStatus(userName));
			
			if (!request.getParameter("caseStatus").equalsIgnoreCase(
					ClaimsConstants.EMPTY)
					&& request.getParameter("caseStatus") != null){
				claimsFlowVO.setCaseStat(request.getParameter("caseStatus"));
			/*if(userName!=null && (userName.equalsIgnoreCase("D058")))
				claimsFlowVO.setCaseStat("CD98");*/
			}
			/*else {
				claimsFlowVO.setCaseStat(claimsFlowForm.getCaseStatus());
				if(userName!=null && (userName.equalsIgnoreCase("D058")))
					claimsFlowVO.setCaseStat("CD98");
			}*/

			lStrUserGroup = config.getString("claimGroup_"
					+ claimsFlowVO.getCaseStat());
			String userGrpTemp = lStrUserGroup;
			for (LabelBean labelBean : groupsList) {
				if (labelBean.getID() != null
						&& labelBean.getID().equalsIgnoreCase(lStrUserGroup)) {
					userGrpTemp = lStrUserGroup;
					break;
				} else
					userGrpTemp = null;
			}
			lStrUserGroup = userGrpTemp;
			if (groupsList == null || groupsList.isEmpty()) {
				lStrUserGroup = null;
			}		
				
			
			if(lStrUserGroup!=null && (config.getString("FIN.AccountsJEOGrp").equalsIgnoreCase(lStrUserGroup)))
			{
				request.setAttribute("StatusList", claimsService.getCaseStatusByGrp(lStrUserGroup));
			}
			
			if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase("")){
				if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON")))
				lStrSchemeType = config.getString("AP");
				else
				lStrSchemeType = lStrUserState;
			}
			
			claimsFlowForm.setRoleId(lStrUserGroup);			
			claimsFlowVO.setCaseId(claimsFlowForm.getCaseId());
			claimsFlowVO.setFromDate(claimsFlowForm.getFromDate());
			claimsFlowVO.setToDate(claimsFlowForm.getToDate());
			claimsFlowVO.setPatName(claimsFlowForm.getPatName());
			claimsFlowVO.setWapNo(claimsFlowForm.getWapNo());
			claimsFlowVO.setSchemeType(lStrSchemeType);
			
			if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
				claimsFlowForm.setShowScheme("show");
				claimsFlowForm.setSchemeType(lStrSchemeType);				
			}
			else {				
				claimsFlowForm.setShowScheme("hide");
			}  

			List<LabelBean> casesForPaymentList = claimsService
					.getCasesForPayments(claimsFlowVO);
			claimsFlowForm.setCasesForPaymentList(casesForPaymentList);
			if (casesForPaymentList.size() > 0) {
				// getting list of buttons for specific role and status
				List<LabelBean> buttonList = commonService
						.getDynamicWrkFlowDtls(claimsFlowVO.getCaseStat(),
								claimsFlowForm.getRoleId(),
								config.getString("EHF.Claims"),
								ClaimsConstants.CLAIMPAYMENT);

				claimsFlowVO.setButtonList(buttonList);
				request.setAttribute("buttons", buttonList);
			}

			claimsFlowForm.setGenFlag(ClaimsConstants.Y);
			claimsFlowForm.setCaseStatus(claimsFlowVO.getCaseStat());
            // defining number of cases for payment
			if (claimsFlowVO.getCaseStat() != null
					&& (claimsFlowVO.getCaseStat().equalsIgnoreCase(config.getString("EHF.Claims.AcountsVerified")) || claimsFlowVO
							.getCaseStat().equalsIgnoreCase(config.getString("EHF.Claims.CHVerified"))))
				request.setAttribute("pageSize", config.getString("EHF.PageSize.Pay"));
			else
				request.setAttribute("pageSize", config.getString("EHF.PageSize.All"));
            //for excel 
			List<claimPaymentReportVO> expList = new ArrayList<claimPaymentReportVO>();
			claimPaymentReportVO row1 = new claimPaymentReportVO();
			row1.setSno("SL No.");
			row1.setCaseNo("Case No");
			row1.setPatName("Patient Name");
			row1.setHospName("Hospital Name");
			row1.setHospAccountName("Hospital Account Name");
			row1.setWapNo("Health Card No");
			row1.setApprovedAmount("Claim Amount");
			row1.setDistrict("District");
			row1.setLstUpdDt("Last Updated Date");
			expList.add(row1);
			int i = 0;
			for (LabelBean row : casesForPaymentList) {
				row1 = new claimPaymentReportVO();
				row1.setSno(++i + ClaimsConstants.EMPTY);
				row1.setCaseNo(row.getID());
				row1.setPatName(row.getVALUE());
				row1.setHospName(row.getLVL());
				row1.setHospAccountName(row.getSUBNAME());
				row1.setWapNo(row.getUNITID());
				row1.setApprovedAmount(row.getWFTYPE());
				row1.setDistrict(row.getEMPNAME());
				row1.setLstUpdDt(row.getLSTUPDDT());
				expList.add(row1);
			}
			//To get Total Claim Amount Pending at CEO
			int totalPendingAmt=0;
			for(LabelBean localBean:casesForPaymentList)
				{
					if(localBean.getWFTYPE()!=null && !"".equalsIgnoreCase(localBean.getWFTYPE()))
						totalPendingAmt=totalPendingAmt+Integer.parseInt(localBean.getWFTYPE());
					
					request.setAttribute("totalPendingAmt", totalPendingAmt);
				}
		
			if (genFlag != null && genFlag.equalsIgnoreCase("E")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Claim")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.XlsExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				ExcelWriter.prepareExl(xlFile, expList);
				byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);
				  	
				// Setting request and response objects
				request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.ExcelContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}
			if (genFlag != null && genFlag.equalsIgnoreCase("P")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Claim")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.PdfExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				PdfGenerator.preparePdf(xlFile, expList, "TDS Payments Report");
				byte[] lbBytes = PdfGenerator.getBytesFromFile(xlFile);

				// Setting request and response objects
				request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.PdfContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}

			lStrResultPage = "claimsPayment";
		}
		
		//added for chronic op claim cases
		if ("getChronicClaimsPaymentRecrds".equalsIgnoreCase(lStrActionVal)) {
			String genFlag = request.getParameter("genFlag");
			userName = session.getAttribute("userName").toString();
			ClaimsFlowVO claimsFlowVO = new ClaimsFlowVO();
			String lStrSchemeType = request.getParameter("schemeType");
			
			request.setAttribute("StatusList", claimsService.getChronicStatus(userName));
			String chronicStatus=request.getParameter("chronicStatus");
				
			if(chronicStatus==null){
					chronicStatus=config.getString("COACO-VERIFY");
					}
			/*if (!request.getParameter("caseStatus").equalsIgnoreCase(
					ClaimsConstants.EMPTY)
					&& request.getParameter("caseStatus") != null){
				claimsFlowVO.setCaseStat(chronicStatus);
			if(userName!=null && userName.equalsIgnoreCase("C075"))
				claimsFlowVO.setCaseStat("CD98");
			}
			else {
				claimsFlowVO.setCaseStat(claimsFlowForm.getCaseStatus());
				if(userName!=null && userName.equalsIgnoreCase("C075"))
					claimsFlowVO.setCaseStat("CD98");
			}*/
			claimsFlowVO.setCaseStat(chronicStatus);
			lStrUserGroup = config.getString("chronicGroup_"
					+ claimsFlowVO.getCaseStat());
			String userGrpTemp = lStrUserGroup;
			for (LabelBean labelBean : groupsList) {
				if ( labelBean.getID()!= null
						&& lStrUserGroup.contains(labelBean.getID())) {
					userGrpTemp = labelBean.getID();
					break;
				} else
					userGrpTemp = null;
			}
			lStrUserGroup = userGrpTemp;
			if (groupsList == null || groupsList.isEmpty()) {
				lStrUserGroup = null;
			}		
				
			if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase("")){
				if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON")))
				lStrSchemeType = config.getString("AP");
				else
				lStrSchemeType = lStrUserState;
			}
			
			claimsFlowForm.setRoleId(lStrUserGroup);			
			claimsFlowVO.setCaseId(claimsFlowForm.getCaseId());
			claimsFlowVO.setFromDate(claimsFlowForm.getFromDate());
			claimsFlowVO.setToDate(claimsFlowForm.getToDate());
			claimsFlowVO.setPatName(claimsFlowForm.getPatName());
			claimsFlowVO.setWapNo(claimsFlowForm.getWapNo());
			claimsFlowVO.setSchemeType(lStrSchemeType);
			
			if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
				claimsFlowForm.setShowScheme("show");
				claimsFlowForm.setSchemeType(lStrSchemeType);				
			}
			else {				
				claimsFlowForm.setShowScheme("hide");
			}  

			List<LabelBean> casesForPaymentList = claimsService
					.getChronicCasesForPayments(claimsFlowVO);
			claimsFlowForm.setCasesForPaymentList(casesForPaymentList);
			if (casesForPaymentList.size() > 0) {
				// getting list of buttons for specific role and status
				List<LabelBean> buttonList = commonService
						.getDynamicWrkFlowDtls(claimsFlowVO.getCaseStat(),
								claimsFlowForm.getRoleId(),
								ClaimsConstants.CHRONICOP,
								ClaimsConstants.CHRONICOP_CLAIM);

				claimsFlowVO.setButtonList(buttonList);
				request.setAttribute("buttons", buttonList);
			}

			claimsFlowForm.setGenFlag(ClaimsConstants.Y);
			claimsFlowForm.setCaseStatus(claimsFlowVO.getCaseStat());
            // defining number of cases for payment
			if (chronicStatus != null
					&& (chronicStatus.equalsIgnoreCase(config.getString("COACO-VERIFY")) || chronicStatus.equalsIgnoreCase(config.getString("CLAIM-VERIFIED-BY-CH"))))
				request.setAttribute("pageSize", config.getString("EHF.PageSize.Pay"));
			else
				request.setAttribute("pageSize", config.getString("EHF.PageSize.All"));
            //for excel 
			List<claimPaymentReportVO> expList = new ArrayList<claimPaymentReportVO>();
			claimPaymentReportVO row1 = new claimPaymentReportVO();
			row1.setSno("SL No.");
			row1.setCaseNo("Chronic No");
			row1.setPatName("Patient Name");
			row1.setHospName("Hospital Name");
			row1.setHospAccountName("Hospital Account Name");
			row1.setWapNo("Health Card No");
			row1.setApprovedAmount("Claim Amount");
			row1.setDistrict("District");
			expList.add(row1);
			int i = 0;
			for (LabelBean row : casesForPaymentList) {
				row1 = new claimPaymentReportVO();
				row1.setSno(++i + ClaimsConstants.EMPTY);
				row1.setCaseNo(row.getID());
				row1.setPatName(row.getVALUE());
				row1.setHospName(row.getLVL());
				row1.setHospAccountName(row.getSUBNAME());
				row1.setWapNo(row.getUNITID());
				row1.setApprovedAmount(row.getWFTYPE());
				row1.setDistrict(row.getEMPNAME());
				expList.add(row1);
			}

			if (genFlag != null && genFlag.equalsIgnoreCase("E")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Claim")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.XlsExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				ExcelWriter.prepareExl(xlFile, expList);
				byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);

				// Setting request and response objects
				request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.ExcelContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}
			if (genFlag != null && genFlag.equalsIgnoreCase("P")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Claim")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.PdfExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				PdfGenerator.preparePdf(xlFile, expList, "Chronic OP Payments Report");
				byte[] lbBytes = PdfGenerator.getBytesFromFile(xlFile);

				// Setting request and response objects
				request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.PdfContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}

			lStrResultPage = "chronicOpClaimsPayment";
		}
		
		
        //performing send back to CH in EO_ADMIN login
		if ("sendBackToCh".equalsIgnoreCase(lStrActionVal)) {

			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			claimFlowVO.setCaseId(request.getParameter("caseId"));
			if (claimFlowVO.getCaseId() == null)
				claimFlowVO.setCaseId(claimsFlowForm.getCaseId());
			claimFlowVO.setCaseStat(claimsFlowForm.getCaseStatus());
			claimFlowVO.setClaimSubDt(serverDt);
			claimFlowVO.setActionDone(request.getParameter("actionDone"));
			claimFlowVO.setUserId(lStrUserId);
			claimFlowVO.setRoleId(claimsFlowForm.getRoleId());
			if (claimsFlowForm.getCeoRemark() != null
					&& claimsFlowForm.getCeoRemark().length() > 3000)
				claimFlowVO.setCeoRemark(claimsFlowForm.getCeoRemark()
						.substring(0, 3000));
			else
				claimFlowVO.setCeoRemark(claimsFlowForm.getCeoRemark());
			claimFlowVO = claimsService.saveClaim(claimFlowVO);
			if (!claimFlowVO.getMsg().equalsIgnoreCase(ClaimsConstants.Failure)) {
				request.setAttribute("saveMsg", claimFlowVO.getMsg());
			}

			lStrActionVal = "getCaseDetails";
		}
       //getting case details while Payment
		if ("getCaseDetails".equalsIgnoreCase(lStrActionVal)) {
			String caseNo = request.getParameter("CaseNo");
			String paymentType = request.getParameter("paymentType");
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			//getting common details about case
			CommonDtlsVO commonDtlsVO = claimsService.getCaseDtlsForPayment(
					caseNo, paymentType);
			claimFlowVO.setCaseId(commonDtlsVO.getCASEID());

			claimsFlowForm.setCaseId(claimFlowVO.getCaseId());
			claimsFlowForm.setCaseStatus(commonDtlsVO.getCASESTAT());
			claimFlowVO.setCaseStat(commonDtlsVO.getCASESTAT());

			lStrUserGroup = config.getString("claimGroup_"
					+ claimFlowVO.getCaseStat());
			String userGrpTemp = lStrUserGroup;
			for (LabelBean labelBean : groupsList) {
				if (labelBean.getID() != null
						&& labelBean.getID().equalsIgnoreCase(lStrUserGroup)) {
					userGrpTemp = lStrUserGroup;
					break;
				} else
					userGrpTemp = null;
			}
			lStrUserGroup = userGrpTemp;
			if (groupsList == null || groupsList.isEmpty()) {
				lStrUserGroup = null;
			}
			claimsFlowForm.setRoleId(lStrUserGroup);

			/**
			 * get audit trail dtls for worklist
			 */
			List<ClaimsFlowVO> lstWorkFlow = claimsService
					.getAuditTrail(claimFlowVO);
			claimsFlowForm.setLstworkFlow(lstWorkFlow);
			request.setAttribute("patCommonDtls", commonDtlsVO);

			// getting list of buttons for specific role and status
			List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
					commonDtlsVO.getCASESTAT(), claimsFlowForm.getRoleId(),
					config.getString("EHF.Claims"),
					config.getString("EHF.RegClaims"));

			claimFlowVO.setButtonList(buttonList);
			request.setAttribute("buttons", buttonList);
			
			if(commonDtlsVO.getCochlear()!=null)
				{
					if(commonDtlsVO.getCochlear().equalsIgnoreCase("Y"))
						request.setAttribute("cochlearFlag",commonDtlsVO.getCochlear());
				}

			request.setAttribute("UserRole", claimsFlowForm.getRoleId());
			request.setAttribute("paymentType", paymentType);

			lStrResultPage = "viewClaimDtlsPayment";
		}
		if("changeClaimForCases".equalsIgnoreCase(lStrActionVal)){
			String lResult = ClaimsConstants.EMPTY;
			String caseStatus = request.getParameter("caseStatus");
			String paymentType = request.getParameter("paymentType");
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			claimFlowVO.setCaseSelected(claimsFlowForm.getCaseSelected());
			claimFlowVO.setRoleId(claimsFlowForm.getRoleId());
			claimFlowVO.setUserId(lStrUserId);
			claimFlowVO.setActionDone(request.getParameter("actionDone"));
			claimFlowVO.setPaymentType(paymentType);
			claimFlowVO.setCaseStat(caseStatus);  
			claimFlowVO.setSchemeType(claimsFlowForm.getSchemeType());
			
			lResult = claimsService.updateClaimStatusReady(claimFlowVO);
			
			if ("0".equals(lResult)) {
				lResult = ClaimsConstants.MSG_0;
			} else if ("2".equals(lResult)) {
				lResult = ClaimsConstants.MSG_2;
			} else if ("1".equals(lResult) && "GP58".equalsIgnoreCase(claimsFlowForm.getRoleId())) {
				lResult = ClaimsConstants.MSG_5;
			} else if ("1".equals(lResult)) {
				lResult = ClaimsConstants.MSG_1;

			}else {
				lResult += ClaimsConstants.MSG_3;
			}
			request.setAttribute("saveMsg", lResult);
			request.setAttribute("caseStatus",caseStatus);
			request.setAttribute("schemeTypeSel", claimFlowVO.getSchemeType());
			request.setAttribute("StatusList", claimsService.getCaseStatus(userName));
			if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim))
				lStrResultPage = "errClaimsPayment";
			else
				lStrResultPage = "claimsPayment";
		}
		
		
		
		
		
		
		
		
		
		if("changeChronicClaimForCases".equalsIgnoreCase(lStrActionVal)){
			String lResult = ClaimsConstants.EMPTY;
			String chronicStatus = request.getParameter("chronicStatus");
			String paymentType = request.getParameter("paymentType");
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			claimFlowVO.setCaseSelected(claimsFlowForm.getCaseSelected());
			claimFlowVO.setRoleId(claimsFlowForm.getRoleId());
			claimFlowVO.setUserId(lStrUserId);
			claimFlowVO.setActionDone(request.getParameter("actionDone"));
			claimFlowVO.setPaymentType(paymentType);
			claimFlowVO.setCaseStat(chronicStatus);  
			claimFlowVO.setSchemeType(claimsFlowForm.getSchemeType());
			
			lResult = claimsService.updateChronicClaimStatusReady(claimFlowVO);
			
			if ("0".equals(lResult)) {
				lResult = ClaimsConstants.MSG_0;
			} else if ("2".equals(lResult)) {
				lResult = ClaimsConstants.MSG_2;
			} else if ("1".equals(lResult)) {
				lResult = ClaimsConstants.MSG_1;
			} else if ("4".equals(lResult)) {
				lResult = ClaimsConstants.MSG_4;

			} else {
				lResult += ClaimsConstants.MSG_3;
			}
			request.setAttribute("saveMsg", lResult);
			request.setAttribute("schemeTypeSel", claimFlowVO.getSchemeType());
			request.setAttribute("StatusList", claimsService.getCaseStatus(userName));
		/*	if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim))
				lStrResultPage = "errClaimsPayment";
			else*/
				lStrResultPage = "chronicOpClaimsPayment";
		}
		
		
		/*getting Claim PAYMENT records
		if ("getPayClaimForCases".equalsIgnoreCase(lStrActionVal)) {
			HashMap hParamMap = new HashMap();
			Map lParamMap = new HashMap();
			String lResult = ClaimsConstants.EMPTY;
			String caseStatus = request.getParameter("caseStatus");
			String paymentType = request.getParameter("paymentType");
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			claimFlowVO.setCaseSelected(claimsFlowForm.getCaseSelected());
			claimFlowVO.setRoleId(claimsFlowForm.getRoleId());
			claimFlowVO.setUserId(lStrUserId);
			claimFlowVO.setActionDone(request.getParameter("actionDone"));

			hParamMap.put("claimFlowVO", claimFlowVO);
			hParamMap.put("caseStatus", caseStatus);
			hParamMap.put("CRTUSER", lStrUserId);
			hParamMap.put("SentStatus", ClaimsConstants.SENT);
			hParamMap.put("SharePath", config.getString("mapPath"));
			// hParamMap.put("nextCaseStatus",config.getString("EHF.Claims.sentForPayment"));
			hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
			hParamMap
					.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
			hParamMap.put("PaymentType", paymentType);
			//updating claim Status while paying
			lParamMap = claimsService.updateClaimStatus(hParamMap);

			if (lParamMap.size() != 0) {
				lResult = (String) lParamMap.get("Message");
			}
			if ("0".equals(lResult)) {
				lResult = ClaimsConstants.MSG_0;
			} else if ("2".equals(lResult)) {
				lResult = ClaimsConstants.MSG_2;
			} else if ("1".equals(lResult)) {
				lResult = ClaimsConstants.MSG_1;

			} else {
				lResult += ClaimsConstants.MSG_3;
			}
			request.setAttribute("saveMsg", lResult);
			request.setAttribute("StatusList", claimsService.getCaseStatus());
            //sending Email for failed cases
			if (config.getBoolean("EmailRequired")) {
				String mailId = null;
				if (lParamMap.get("failedCaseIdInList") != null) {
					mailId = config.getString("claimFailedCaseEmail");
					String[] emailToArray = { mailId };
					EmailVO emailVO = new EmailVO();
					emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
					emailVO.setTemplateName(config
							.getString("EhfFailedCasePayment"));
					emailVO.setFromEmail(config.getString("emailFrom"));
					emailVO.setToEmail(emailToArray);
					emailVO.setSubject(config.getString("failedCasesPayment"));
					Map<String, String> model = new HashMap<String, String>();
					model.put("caseNo",
							(String) lParamMap.get("failedCaseIdInList"));
					commonService.generateMail(emailVO, model);
				}
			}

			if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim))
				lStrResultPage = "errClaimsPayment";
			else
				lStrResultPage = "claimsPayment";
		}*/
		//getting ERRoneous PAYMENT records
		if ("getErrPaymentRecrds".equalsIgnoreCase(lStrActionVal)) {
			String genFlag = request.getParameter("genFlag");
			userName = session.getAttribute("userName").toString();
			
			ClaimsFlowVO claimsFlowVO = new ClaimsFlowVO();
			String lStrSchemeType = request.getParameter("schemeType");
			if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase("")){
				if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON")))
				lStrSchemeType = config.getString("AP");
				else
				lStrSchemeType = lStrUserState;
			}
			request.setAttribute("StatusList", claimsService.getErrCaseStatus(userName));
			
			if (!request.getParameter("errStatusId").equalsIgnoreCase(
					ClaimsConstants.EMPTY)
					&& request.getParameter("errStatusId") != null)
				claimsFlowVO.setCaseStat(request.getParameter("errStatusId"));
			if(userName!=null && userName.equalsIgnoreCase("C075"))
				claimsFlowVO.setCaseStat("CD117");
			else {
				if(claimsFlowForm.getCaseStatus()!=null)
				claimsFlowVO.setCaseStat(claimsFlowForm.getCaseStatus());
				if(request.getParameter("errStatusId")!=null )
					if(request.getParameter("errStatusId").equalsIgnoreCase("CD109"))
						if(claimsFlowForm.getCaseStatus()==null)
							claimsFlowVO.setCaseStat(claimsFlowForm.getErrStatusId());
				if(userName!=null && userName.equalsIgnoreCase("C075"))
					claimsFlowVO.setCaseStat("CD117");
			}

			lStrUserGroup = config.getString("claimGroup_"
					+ claimsFlowVO.getCaseStat());
			String userGrpTemp = lStrUserGroup;
			for (LabelBean labelBean : groupsList) {
				if (labelBean.getID() != null
						&& labelBean.getID().equalsIgnoreCase(lStrUserGroup)) {
					userGrpTemp = lStrUserGroup;
					break;
				} else
					userGrpTemp = null;
			}
			lStrUserGroup = userGrpTemp;
			if (groupsList == null || groupsList.isEmpty()) {
				lStrUserGroup = null;
			}
			
			if(lStrUserGroup!=null && (config.getString("FIN.AccountsJEOGrp").equalsIgnoreCase(lStrUserGroup)))
			{
				request.setAttribute("StatusList", claimsService.getCaseStatusByGrp(lStrUserGroup));
			}
			
			
			claimsFlowForm.setRoleId(lStrUserGroup);

			claimsFlowVO.setCaseId(claimsFlowForm.getCaseId());
			claimsFlowVO.setFromDate(claimsFlowForm.getFromDate());
			claimsFlowVO.setToDate(claimsFlowForm.getToDate());
			claimsFlowVO.setPatName(claimsFlowForm.getPatName());
			claimsFlowVO.setWapNo(claimsFlowForm.getWapNo());
			claimsFlowVO.setSchemeType(lStrSchemeType);
			
			if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
				claimsFlowForm.setShowScheme("show");
				claimsFlowForm.setSchemeType(lStrSchemeType);
			}
			else {
				claimsFlowForm.setShowScheme("hide");
			}  
			
			List<LabelBean> casesForPaymentList = claimsService
					.getErrCasesForPayments(claimsFlowVO);
			claimsFlowForm.setCasesForPaymentList(casesForPaymentList);
			if (casesForPaymentList.size() > 0) {
				// getting list of buttons for specific role and status
				List<LabelBean> buttonList = commonService
						.getDynamicWrkFlowDtls(claimsFlowVO.getCaseStat(),
								claimsFlowForm.getRoleId(),
								config.getString("EHF.Claims"),
								ClaimsConstants.ERR_CLAIM_PAYMENT);

				claimsFlowVO.setButtonList(buttonList);
				request.setAttribute("buttons", buttonList);
			}
			claimsFlowForm.setGenFlag(ClaimsConstants.Y);
            //for excel
			claimsFlowForm.setCaseStatus(claimsFlowVO.getCaseStat());
			List<ErrPaymentReportVO> expList = new ArrayList<ErrPaymentReportVO>();
			ErrPaymentReportVO row1 = new ErrPaymentReportVO();
			row1.setSno("SL No.");
			row1.setErrCaseId("Erroneous Case Id");
			row1.setCaseNo("Case No");
			row1.setPatName("Patient Name");
			row1.setHospName("Hospital Name");
			row1.setHospAccountName("Hospital Account Name");
			row1.setWapNo("Health Card No");
			row1.setApprovedAmount("Claim Amount");
			row1.setDistrict("District");
			expList.add(row1);
			int i = 0;
			for (LabelBean row : casesForPaymentList) {
				row1 = new ErrPaymentReportVO();
				row1.setSno(++i + ClaimsConstants.EMPTY);
				row1.setErrCaseId(row.getID());
				row1.setCaseNo(row.getDSGNID());
				row1.setPatName(row.getVALUE());
				row1.setHospName(row.getLVL());
				row1.setHospAccountName(row.getSUBNAME());
				row1.setWapNo(row.getUNITID());
				row1.setApprovedAmount(row.getWFTYPE());
				row1.setDistrict(row.getEMPNAME());
				expList.add(row1);
			}

			if (genFlag != null && genFlag.equalsIgnoreCase("E")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Erroneous")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.XlsExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				//ExcelWriter.prepareExl(xlFile, expList);
				//byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);

				// Setting request and response objects
				//request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.ExcelContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}
			if (genFlag != null && genFlag.equalsIgnoreCase("P")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Erroneous")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.PdfExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				PdfGenerator.preparePdf(xlFile, expList, "TDS Payments Report");
				byte[] lbBytes = PdfGenerator.getBytesFromFile(xlFile);

				// Setting request and response objects
				request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.PdfContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}
			lStrResultPage = "errClaimsPayment";
		}
         //getting TDS PAYMENT records
		if ("getTdsPaymentRecrds".equalsIgnoreCase(lStrActionVal)) {
			String genFlag = request.getParameter("genFlag");
			String schemeType = request.getParameter("schemeType");
			if(schemeType == null || schemeType.equalsIgnoreCase("")){
				if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON")))
					schemeType = config.getString("AP");
				else
					schemeType = lStrUserState;
			}

			ClaimsFlowVO claimsFlowVO = new ClaimsFlowVO();
			String paymentType = request.getParameter("paymentType");
			claimsFlowVO.setCaseStat(ClaimsConstants.CLAIM_READY_PAYMENT);
			lStrUserGroup = config.getString("claimGroup_"
					+ claimsFlowVO.getCaseStat());
			String userGrpTemp = lStrUserGroup;
			for (LabelBean labelBean : groupsList) {
				if (labelBean.getID() != null
						&& labelBean.getID().equalsIgnoreCase(lStrUserGroup)) {
					userGrpTemp = lStrUserGroup;
					break;
				} else
					userGrpTemp = null;
			}
			lStrUserGroup = userGrpTemp;
			if (groupsList == null || groupsList.isEmpty()) {
				lStrUserGroup = null;
			}
			claimsFlowForm.setRoleId(lStrUserGroup);

			claimsFlowVO.setCaseId(claimsFlowForm.getCaseId());
			claimsFlowVO.setFromDate(claimsFlowForm.getFromDate());
			claimsFlowVO.setToDate(claimsFlowForm.getToDate());
			claimsFlowVO.setPatName(claimsFlowForm.getPatName());
			claimsFlowVO.setWapNo(claimsFlowForm.getWapNo());
			claimsFlowVO.setPaymentType(paymentType);
			claimsFlowVO.setSchemeType(schemeType);
			request.setAttribute("tdsPaymentList",
					claimsService.getTdsPaymentType());

			List<LabelBean> casesForPaymentList = claimsService
					.getTDSCasesForPayments(claimsFlowVO);
			claimsFlowForm.setCasesForPaymentList(casesForPaymentList);
			if (casesForPaymentList.size() > 0) {
				// getting list of buttons for specific role and status
				List<LabelBean> buttonList = commonService
						.getDynamicWrkFlowDtls(claimsFlowVO.getCaseStat(),
								claimsFlowForm.getRoleId(),
								ClaimsConstants.TDS,
								ClaimsConstants.TDS_PAYMENT);

				claimsFlowVO.setButtonList(buttonList);
				request.setAttribute("buttons", buttonList);
			}
			claimsFlowForm.setGenFlag(ClaimsConstants.Y);
			claimsFlowForm.setSchemeType(schemeType);
			claimsFlowForm.setCaseStatus(claimsFlowVO.getCaseStat());

			List<PaymentReportVO> expList = new ArrayList<PaymentReportVO>();
			PaymentReportVO row1 = new PaymentReportVO();
			row1.setSno("SL No.");
			row1.setFollowUpId("TDS Payment Id");
			row1.setCaseNo("Case No");
			row1.setPatName("Patient Name");
			row1.setHospName("Hospital Name");
			row1.setWapNo("Health Card No");
			row1.setApprovedAmount("TDS Amount");
			expList.add(row1);
			int i = 0;
			for (LabelBean row : casesForPaymentList) {
				row1 = new PaymentReportVO();
				row1.setSno(++i + ClaimsConstants.EMPTY);
				row1.setFollowUpId(row.getID());
				row1.setCaseNo(row.getDSGNID());
				row1.setPatName(row.getVALUE());
				row1.setHospName(row.getLVL());
				row1.setWapNo(row.getUNITID());
				row1.setApprovedAmount(row.getWFTYPE());
				expList.add(row1);
			}

			if (genFlag != null && genFlag.equalsIgnoreCase("E")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Tds")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.XlsExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				//ExcelWriter.prepareExl(xlFile, expList);
				//byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);

				// Setting request and response objects
				//request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.ExcelContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}
			if (genFlag != null && genFlag.equalsIgnoreCase("P")) {
				String lStrDirPath = config.getString("PAYMENT.MapPath");
				String lStrFileName = config.getString("PAYMENT.MapPath")
						+ config.getString("SLASH_VALUE")
						+ config.getString("PAYMENT.Tds")
						+ config.getString("DOT_VALUE")
						+ config.getString("REPORT.PdfExtn");

				// Creates file in EHFTemp folder of client machine
				File xlFile = createFile(lStrDirPath, lStrFileName);
				PdfGenerator.preparePdf(xlFile, expList, "TDS Payments Report");
				byte[] lbBytes = PdfGenerator.getBytesFromFile(xlFile);

				// Setting request and response objects
				request.setAttribute("File", lbBytes);
				response.setContentType(config
						.getString("REPORT.PdfContentType"));
				response.setHeader("Content-Disposition",
						"Attachment; filename=" + lStrFileName);
				return mapping.findForward("openFile");
			}

			lStrResultPage = "tdsClaimsPayment";
		}
		if("UpdateTdsClaim".equalsIgnoreCase(lStrActionVal)){
			String lResult = ClaimsConstants.EMPTY;
			String caseStatus = request.getParameter("caseStatus");
			String paymentType = request.getParameter("paymentType");
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			claimFlowVO.setCaseSelected(claimsFlowForm.getCaseSelected());
			claimFlowVO.setRoleId(claimsFlowForm.getRoleId());
			claimFlowVO.setUserId(lStrUserId);
			claimFlowVO.setActionDone(request.getParameter("actionDone"));
			claimFlowVO.setPaymentType(paymentType);
			claimFlowVO.setCaseStat(caseStatus);  
			claimFlowVO.setSchemeType(claimsFlowForm.getSchemeType());
			
			lResult = claimsService.updateTDSStatusReady(claimFlowVO);
			
			if ("0".equals(lResult)) {
				lResult = ClaimsConstants.MSG_0;
			} else if ("2".equals(lResult)) {
				lResult = ClaimsConstants.MSG_2;
			} else if ("1".equals(lResult)) {
				lResult = ClaimsConstants.MSG_1;

			} else {
				lResult += ClaimsConstants.MSG_3;
			}
			request.setAttribute("saveMsg", lResult);
			request.setAttribute("schemeTypeSel",claimFlowVO.getSchemeType());
			request.setAttribute("StatusList", claimsService.getCaseStatus(userName));
			//for TDS Payment Type drop down
			request.setAttribute("tdsPaymentList",
					claimsService.getTdsPaymentType());
			
			lStrResultPage = "tdsClaimsPayment";  
		}
	if(lStrActionVal!= null && "getAllPanelUsers".equals(lStrActionVal)) 
    {
		String caseId=request.getParameter("caseId");
		List<LabelBean> usrLst=commonService.getAllUsersFromDepts1(caseId);
		List ajax=new ArrayList();
		for(LabelBean lb:usrLst)
			{
				if(lb.getID()!=null && ajax.size()>0)
					{
						ajax.add("@"+lb.getID()+"~"+lb.getEMPNAME().toUpperCase());
					}
				else
					{
						ajax.add(lb.getID()+"~"+lb.getEMPNAME().toUpperCase());
					}
			}
		request.setAttribute("AjaxMessage", ajax);
		return mapping.findForward("ajaxResult");
    }
        /*
		if ("UpdateTdsClaim".equalsIgnoreCase(lStrActionVal)) {

			HashMap hParamMap = new HashMap();
			Map lParamMap = new HashMap();
			String lResult = ClaimsConstants.EMPTY;
			String caseStatus = request.getParameter("caseStatus");
			String paymentType = request.getParameter("paymentType");
			ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
			claimFlowVO.setCaseSelected(claimsFlowForm.getCaseSelected());
			claimFlowVO.setRoleId(claimsFlowForm.getRoleId());
			claimFlowVO.setUserId(lStrUserId);
			claimFlowVO.setActionDone(request.getParameter("actionDone"));

			hParamMap.put("claimFlowVO", claimFlowVO);
			hParamMap.put("caseStatus", caseStatus);
			hParamMap.put("CRTUSER", lStrUserId);
			hParamMap.put("SentStatus", ClaimsConstants.SENT);
			hParamMap.put("SharePath", config.getString("mapPath"));

			hParamMap.put("TdsRemarks", ClaimsConstants.CLAIM_SENT_RMK);
			hParamMap.put("UserRole", claimsFlowForm.getRoleId());
			hParamMap.put("TDS_CASE_TYPE", ClaimsConstants.TRUST_DEDUCTOR);

			hParamMap.put("SentStatus", ClaimsConstants.SENT);
			hParamMap.put("FormPaymentType", paymentType);
            //upddating tds payment
			lParamMap = claimsService.updateTDSClaimStatus(hParamMap);

			if (lParamMap.size() != 0) {
				lResult = (String) lParamMap.get("Message");
			}
			if ("0".equals(lResult)) {
				lResult = ClaimsConstants.MSG_0;
			} else if ("2".equals(lResult)) {
				lResult = ClaimsConstants.MSG_2;
			} else if ("1".equals(lResult)) {
				lResult = ClaimsConstants.MSG_1;

			} else {
				if (lResult == null)
					lResult = ClaimsConstants.MSG_3;
				else
					lResult += ClaimsConstants.MSG_3;
			}
			request.setAttribute("saveMsg", lResult);
			//for cases status drop down
			request.setAttribute("StatusList", claimsService.getCaseStatus());
			//for TDS Payment Type drop down
			request.setAttribute("tdsPaymentList",
					claimsService.getTdsPaymentType());

			if (config.getBoolean("EmailRequired")) {
				String mailId = null;
				if (lParamMap.get("failedCaseIdInList") != null) {
					mailId = config.getString("claimFailedCaseEmail");
					String[] emailToArray = { mailId };
					EmailVO emailVO = new EmailVO();
					emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
					emailVO.setTemplateName(config
							.getString("EhfFailedCasePayment"));
					emailVO.setFromEmail(config.getString("emailFrom"));
					emailVO.setToEmail(emailToArray);
					emailVO.setSubject(config.getString("failedCasesPayment"));
					Map<String, String> model = new HashMap<String, String>();
					model.put("caseNo",
							(String) lParamMap.get("failedCaseIdInList"));
					commonService.generateMail(emailVO, model);
				}
			}

			lStrResultPage = "tdsClaimsPayment";

		}*/
	//Chandana - 8602 - To get the CH approved cases
	if("getNimsOpdClaimCasesforACOApproval".equalsIgnoreCase(lStrActionVal)){
		String patientId  = request.getParameter("patientId");
		String cardNo = request.getParameter("cardNo");
		String clmStatus = request.getParameter("claimStatus");
		String claimSubmittedNo = request.getParameter("claimSubmittedNo");
		String claimDt  = request.getParameter("claimDt");
		String ajaxCall = request.getParameter("ajaxCall");
		HashMap hashMap = new HashMap();
		hashMap.put("patId", patientId);
		hashMap.put("cardNo", cardNo);
		hashMap.put("clmStatus", clmStatus);
		hashMap.put("opClaimNo", claimSubmittedNo);
		hashMap.put("claimDt", claimDt);
		hashMap.put("ajaxCall", ajaxCall);
		String acoFlag = "Y";
		
		List<LabelBean> opClaimCasesList = claimsService.getOpdClaimCasesListForACO(hashMap);
		List<LabelBean> claimStatusList = patientService.getNimsOPDClaimStatusLst(acoFlag);
		Gson gson = new Gson();
		if(ajaxCall == null || !"Y".equalsIgnoreCase(ajaxCall)){
			request.setAttribute("opClaimCasesList", opClaimCasesList);
			request.setAttribute("claimStatusList", claimStatusList);
			lStrResultPage = "opdClaimCasesForACO";
		}else{
        	String gsonString = gson.toJson(opClaimCasesList);   
        	response.getWriter().write(gsonString);
        	return null;
		}
		//lStrResultPage = "opdClaimCasesForACO";
	}
    
    if ("opdClaimsBulkApproval".equalsIgnoreCase(lStrActionVal)) {
        String acoFlag = request.getParameter("acoFlag");
        String action = request.getParameter("actionDone");
        String nextStatus = null, nextRoleStatus = null, userRole = null;
        String cases = request.getParameter("cases");
        String[] casesSelected = null;
        int count = 0; // to count processed cases
        if (cases != null && cases.length() > 0) {
            casesSelected = cases.split("~");
            if (casesSelected != null) {
                for (String caseId : casesSelected) {
                    if (caseId != null && !("").equalsIgnoreCase(caseId)) {
                    	String caseStatus = null;
                        String patientId = null;
                        String actionDone = null;
                        String caseData = claimsService.getNIMSOPDCaseStat(caseId);
                        if (caseData != null && caseData.contains("~")) {
                            String[] parts = caseData.split("~");
                            caseStatus = parts[0];
                            patientId = parts[1];
                        }
                        if ("Approve".equalsIgnoreCase(action) && ("CD7305".equalsIgnoreCase(caseStatus) || "CD7344".equalsIgnoreCase(caseStatus)))
                            actionDone = "CD7326";
                        if (caseData != null && !"".equalsIgnoreCase(caseData))
                        	nextRoleStatus = patientService.getNextStatus(caseStatus, actionDone);
                        if (nextRoleStatus != null && nextRoleStatus.contains("~")) {
                            String[] parts = nextRoleStatus.split("~");
                            nextStatus = parts[0];
                            userRole = parts[1];
                        }
                        ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
                        if (acoFlag != null && acoFlag.equalsIgnoreCase("Y"))
                            claimFlowVO.setRoleId(config.getString("EHF.Claims.ACO"));
                        claimFlowVO.setCaseStat(nextStatus);
                        claimFlowVO.setPatientId(patientId);
                        claimFlowVO.setCaseId(caseId);
                        claimFlowVO.setActionDone(actionDone);
                        claimFlowVO.setAcoRemark("Claim Verified BY ACO");
                        claimFlowVO.setUserId(lStrUserId);
                        claimFlowVO.setRoleId(userRole);
                        ClaimsFlowVO resultVO = claimsService.saveOpdClaim(claimFlowVO);
                        count++;
                    }
                }
            }
        }
        System.out.println("Total cases processed: " + count);
        List<LabelBean> opClaimCasesList = claimsService.getOpdClaimCasesListForACO(new HashMap());
        List<LabelBean> claimStatusList = patientService.getNimsOPDClaimStatusLst(acoFlag);
        Gson gson = new Gson();
        request.setAttribute("opClaimCasesList", opClaimCasesList);
        request.setAttribute("claimStatusList", claimStatusList);
        lStrResultPage = "opdClaimCasesForACO";
    }
    //Chandana - 8602 - Added this for ACO kept pending action to CH
    if("opClaimPendingByACO".equalsIgnoreCase(lStrActionVal)){
    	String result = null;
    	String actionDone = null;
    	String action = request.getParameter("action");
    	String opdClaimNo = request.getParameter("opdClaimNo");
    	String finalAmt = request.getParameter("finalAmt");
    	String remarks = request.getParameter("remarks");
    	String patientId = request.getParameter("patientId");
    	HashMap hashMap = new HashMap();
    	String currentStatus = null;
    	String nextStatus = null;
    	String nextRoleStatus = null, userRole = null;

    	String caseData = claimsService.getNIMSOPDCaseStat(opdClaimNo);
        if (caseData != null && caseData.contains("~")) {
            String[] parts = caseData.split("~");
            currentStatus = parts[0];
            patientId = parts[1];
        }
        if ("Return".equalsIgnoreCase(action) && ("CD7305".equalsIgnoreCase(currentStatus) || "CD7344".equalsIgnoreCase(currentStatus)))
            actionDone = "CD7342";
        if (caseData != null && !"".equalsIgnoreCase(caseData))
        	nextRoleStatus = patientService.getNextStatus(currentStatus, actionDone);
        if (nextRoleStatus != null && nextRoleStatus.contains("~")) {
            String[] parts = nextRoleStatus.split("~");
            nextStatus = parts[0];
            userRole = parts[1];
        }
    	hashMap.put("status", action);
    	hashMap.put("opdClaimNo", opdClaimNo);
    	hashMap.put("remarks", remarks);
    	hashMap.put("finalAmt", finalAmt);
    	hashMap.put("userId",lStrUserId);
    	hashMap.put("patientId",patientId);
    	hashMap.put("currentStatus", currentStatus);
    	hashMap.put("nextStatus", nextStatus);
    	hashMap.put("userRole", userRole);
    	result = claimsService.pendingOpClaimByACO(hashMap);
    	Gson gson = new Gson();
    	String gsonString = gson.toJson(result);   
    	response.getWriter().write(gsonString);
    	return null;
    }
		return mapping.findForward(lStrResultPage);
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


