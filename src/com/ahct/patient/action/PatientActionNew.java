package com.ahct.patient.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.struts.upload.FormFile;

import com.google.gson.Gson;
import com.ahct.claims.util.ClaimCases;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfPatient;
import com.ahct.patient.constants.FileConstants;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.service.PatientService;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class PatientActionNew extends Action {
	private final static Logger GLOGGER = Logger.getLogger ( PatientActionNew.class ) ;
	@SuppressWarnings({ "unchecked", "null" })
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{	response.setHeader("Cache-Control","no-cache");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires", 0);    
		HttpSession session = request.getSession ( false ) ;
		PatientForm patientForm=(PatientForm)form;
		ConfigurationService configurationService = 
				(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
		CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
		String userName=null;
		String lStrLangID=null;
		String lStrUserId =null;
		List<LabelBean> grpList=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String roleId="";
		String userMobileNo=null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		FileInputStream fis=null;
		DataInputStream dis=null;
		String schemeId=null;
		byte bytes[]=null;
		String callType=null;
		String claimStatus = null;
    	String nextStatus = null;
    	String nextRoleStatus = null; String userRole = null;
    	String actnDone = null;
		String bifurcationDate=config.getString("bifurcationDate");
		request.setAttribute("bifurcationDate", bifurcationDate);
		if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
			callType=request.getParameter("callType");
		String lStrResultPage = HtmlEncode.verifySession(request, response);
		if (lStrResultPage.length() > 0)
		{
			if(callType!=null && "Ajax".equals(callType))
			{
				request.setAttribute("AjaxMessage","SessionExpired");
				return mapping.findForward("ajaxResult");
			}
			else
				return mapping.findForward("sessionExpire");
		}
		
		String userIDu=(String) session.getAttribute("UserID");
		String hospGovu=patientService.checkGovernmentHosp(userIDu);
		String checkHubSpoke=patientService.checkHubSpoke(userIDu);
		if(checkHubSpoke!=null && "SPOKE".equalsIgnoreCase(checkHubSpoke))
			request.setAttribute("hubSpoke","S");
		if(checkHubSpoke!=null && "HUB".equalsIgnoreCase(checkHubSpoke))
			request.setAttribute("hubSpoke","S");
		request.setAttribute("hospGovu", hospGovu);
		String bioRegFlag = config.getString("biometricRegistration");
		
		if(bioRegFlag==null && session.getAttribute("bioRegFlag")!=null)
			bioRegFlag=(String)session.getAttribute("bioRegFlag");
		
		if(bioRegFlag!=null &&!"".equalsIgnoreCase(bioRegFlag))
			session.setAttribute("bioRegFlag",bioRegFlag);
		
		String spokeRole="";
		if(session.getAttribute("EmpID").toString()!=null)
			lStrUserId = session.getAttribute("EmpID").toString();
		if(session.getAttribute("LangID").toString()!=null)
			lStrLangID = session.getAttribute("LangID").toString();
		if(session.getAttribute("userName").toString()!=null)
			userName=session.getAttribute("userName").toString();
		if(session.getAttribute("groupList").toString()!=null)
			grpList=(List<LabelBean>)session.getAttribute("groupList");
		if(session.getAttribute("userState").toString()!=null)
			schemeId=session.getAttribute("userState").toString();
		if(session.getAttribute("userMobileNo").toString()!=null)
			userMobileNo=session.getAttribute("userMobileNo").toString();
		for(LabelBean labelBean:grpList)
			lStrgrpList.add(labelBean.getID());
		if(lStrgrpList.contains(config.getString("Group.Mithra")))
			roleId=config.getString("Group.Mithra");
		else if(lStrgrpList.contains(config.getString("Group.Medco")))
		{
			if((lStrgrpList.contains(config.getString("preauth_spoke_role"))))
			{
				roleId=config.getString("Group.Medco");
				spokeRole=config.getString("preauth_spoke_role");
			}
			else
				roleId=config.getString("Group.Medco");
		}
		else if(lStrgrpList.contains(config.getString("Group.Pex")))
			roleId=config.getString("Group.Pex");
		else
			roleId=lStrgrpList.get(0);
		String lStrcastesHdrId = config.getString("IPOP.CasteCMBHDRID");
		String mandateAttachmntsLst = config.getString("DelhiJHSMandateAttachmnts");

		String lStrActionVal = request.getParameter("actionFlag");
		String lStrPageName = null; 
		List<LabelBean> hospDtlsList = null;
		request.setAttribute("hospDtlsList", hospDtlsList);
		
		List<LabelBean> drugTypeList = null;
		request.setAttribute("drugTypeList", drugTypeList);
		String loggedUserHospId= commonService.getloggedUserHospId(lStrUserId,schemeId);
		
		List<LabelBean> medicalSpltyList=null;
		if(!"".equalsIgnoreCase(loggedUserHospId) && loggedUserHospId != null)
		{
			medicalSpltyList	=commonService.getMedicalSpltyList(loggedUserHospId,schemeId);     
			request.setAttribute("medicalSpltyList", medicalSpltyList);
		}
		List<LabelBean> medicalCatList = commonService.getMedicalCatgryListDflt();   
		request.setAttribute("medicalCatList", medicalCatList);
		
		String dentalrnt=request.getParameter("dentalredirect");
		request.setAttribute("dentalornt", "dentalrnt");

		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "viewAttachment" ) )
	      {
			String lStrFilePath = null;
      	   	String lStrContentType = request.getContentType();
	          lStrContentType = (lStrContentType==null)?FileConstants.EMPTY_STRING:lStrContentType;
	          lStrFilePath = request.getParameter("filePath");
      	    File file = null ;
		        FileInputStream fis1 = null ;
		        DataInputStream dis1 = null ;
		        String lStrType = null;
		        ServletOutputStream out = response.getOutputStream();
	          String fileExt = lStrFilePath.substring((lStrFilePath.lastIndexOf(".")+1));
	          String lStrFileName=lStrFilePath.substring((lStrFilePath.lastIndexOf("/")+1));
	          String attachMode="attachment";
	          if(fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPG) || fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPEG) || fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_GIF)){
	              attachMode="inline";
	          }
	           lStrContentType=FileConstants.FILE_EXT.get(fileExt);
	           String dir =  lStrFilePath  ;
	          byte[] lbBytes = null ;
	          try
	          {
	            if ( lStrFileName != null && !lStrFileName.equals ( "" ) )
	            {
	              file = new File ( dir ) ; 
	              fis1 = new FileInputStream ( file ) ;
	              dis1 = new DataInputStream ( fis1 ) ;
	              lbBytes = new byte[ dis1.available () ] ;
	              fis1.read ( lbBytes ) ;
	              request.setAttribute ( "File", lbBytes ) ;
	              request.setAttribute ( "ContentType", lStrContentType ) ;
	              request.setAttribute ( "FileName", lStrFileName ) ;
	              request.setAttribute ( "Extn", lStrType ) ;
	              response.setContentType ( lStrContentType ) ;
	              response.setHeader("Content-Disposition",""+attachMode+"; filename="+lStrFileName);//006
	              out.write(lbBytes);
	            }
	            else
	              lbBytes = new byte[ 0 ];
	          }
	          catch ( Exception e )
	          {
	            e.getMessage () ;
	            e.printStackTrace();
	          }
	          finally
	          {
	        	  out.close();
	          }
	      }
	
		//Chandana - 8755 - New actionflag for showing the list of delhi journalist patients
		if(lStrActionVal != null && "getDlhJrnlstClaimCasesList".equals(lStrActionVal)){
        	String claimDt  = request.getParameter("claimDt");
			String patientId  = request.getParameter("patientId");
			String cardNo = request.getParameter("cardNo");
			String clmStatus = request.getParameter("claimStatus");
			String claimSubmittedNo = request.getParameter("claimSubmittedNo");
			String ajaxCall = request.getParameter("ajaxCall");
			HashMap<String, String> hashMap = new HashMap<String, String>();
			
			hashMap.put("claimDt", claimDt);
			hashMap.put("patientId", patientId);
			hashMap.put("claimSubmittedNo", claimSubmittedNo);
			hashMap.put("claimStatus", clmStatus);
			hashMap.put("cardNo", cardNo);
			hashMap.put("searchFlg", ajaxCall);
			hashMap.put("loggedUserHospId", loggedUserHospId);//Chandana - 9033 - Added this to send it to method
			String acoFlag = "N";
			List<LabelBean> opClaimCasesList = patientService.getDlhJrnlstClaimCasesLst(hashMap);
			List<LabelBean> claimStatusList = patientService.getJrnlstDlhClaimStatusLst(acoFlag);
			Gson gson = new Gson();
			if(ajaxCall == null || !"Y".equalsIgnoreCase(ajaxCall)){
				request.setAttribute("opClaimCasesList", opClaimCasesList);
				request.setAttribute("claimStatusList", claimStatusList);
				return mapping.findForward("dlhJrnlstClaimCases");
			}else{
	        	String gsonString = gson.toJson(opClaimCasesList);   
	        	response.getWriter().write(gsonString);
	        	return null;
			}
        }
        if(lStrActionVal != null && "getDlhJrnlstClaimCaseDtls".equals(lStrActionVal)){
        	EhfPatient ehfPatient = new EhfPatient();
        	String patientId = request.getParameter("patientId");
        	String crNo = request.getParameter("cardNo");
        	String type = request.getParameter("type");
        	String acoFlag = request.getParameter("acoFlag");
			patientForm.setPatientNo(patientId);
			patientForm.setCrNumber(crNo);
			String userID=(String) session.getAttribute("UserID");
			String check=patientService.checkDentalClinic(userID,patientId);
			if(!check.equals("hospital")){
				patientForm.setHosType("DC");
			}else{
				patientForm.setHosType("hospital");
			}
			ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
			session.setAttribute("patCrtdt",ehfPatient.getCrtDt());
			patientForm.setCardNo(ehfPatient.getCardNo());
			patientForm.setFname(ehfPatient.getName());
			if(ehfPatient.getCardIssueDt()!=null){
				patientForm.setCardIssueDt(sdf.format(ehfPatient.getCardIssueDt()));
			}else
				patientForm.setCardIssueDt("NA");
			if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender())){
				patientForm.setGender("Male");
			}
			else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender())){
				patientForm.setGender("Female");
			}
			if(ehfPatient.getEnrollDob()!=null){
				patientForm.setDateOfBirth(sdf.format(ehfPatient.getEnrollDob()));
			}
			if(ehfPatient.getAge()!=null){
				patientForm.setYears(ehfPatient.getAge().toString());
				if(ehfPatient.getAge().toString() != null && (ehfPatient.getAge() <=13 )){
					request.setAttribute("child", "Y");
				}else{
					request.setAttribute("child", "N");
				}
			}
			if(ehfPatient.getAgeMonths()!=null){
				patientForm.setMonths(ehfPatient.getAgeMonths().toString());
			}
			if(ehfPatient.getAgeDays()!=null){
				patientForm.setDays(ehfPatient.getAgeDays().toString());
			}
			String relationId=ehfPatient.getRelation();
			String relationName=patientService.getRelationName(relationId);
			patientForm.setRelation(relationName);
			String casteId=ehfPatient.getCaste();
			if(casteId!=null && !casteId.equals(""))
			{
				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
				patientForm.setCaste(casteName);
			}
			if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
			{
				patientForm.setContactno(ehfPatient.getContactNo().toString());
			}
			patientForm.setOccupation(ehfPatient.getOccupationCd());
			if(ehfPatient.getSchemeId()!=null )
				session.setAttribute("regPatientScheme",ehfPatient.getSchemeId());
			patientForm.setScheme(ehfPatient.getSchemeId());
			patientForm.setTelScheme(ehfPatient.getSchemeId());
			String slabType=null;
			String slab=null;
			if(ehfPatient.getSlab()!=null)
			{
				slabType=ehfPatient.getSlab();
			}
			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.SemiPrivateWard");
			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.PrivateWard");
			patientForm.setSlab(slab);
			patientForm.setHno(ehfPatient.getHouseNo());
			patientForm.setStreet(ehfPatient.getStreet());
			patientForm.setState(patientService.getLocationName(ehfPatient.getState()));
			String distCode=ehfPatient.getDistrictCode();
			String distName=patientService.getLocationName(distCode);
			patientForm.setDistrict(distName);
			String mandalCode=ehfPatient.getMandalCode();
			String mandalName=patientService.getLocationName(mandalCode);
			patientForm.setMandal(mandalName);
			String villageCode=ehfPatient.getVillageCode();
			String villageName=patientService.getLocationName(villageCode);
			patientForm.setVillage(villageName);
			if(ehfPatient.getPinCode()!=null && !ehfPatient.getPinCode().equalsIgnoreCase(""))
				patientForm.setPin(ehfPatient.getPinCode());
			else
				patientForm.setPin("NA");
			patientForm.setComm_hno(ehfPatient.getcHouseNo());
			patientForm.setComm_street(ehfPatient.getcStreet());
			patientForm.setComm_state(patientService.getLocationName(ehfPatient.getcState()));
			patientForm.setComm_dist(patientService.getLocationName(ehfPatient.getcDistrictCode()));
			patientForm.setComm_mandal(patientService.getLocationName(ehfPatient.getcMandalCode()));
			patientForm.setComm_village(patientService.getLocationName(ehfPatient.getcVillageCode()));
			if(ehfPatient.getcPinCode()!=null && !ehfPatient.getcPinCode().equalsIgnoreCase(""))
				patientForm.setComm_pin(ehfPatient.getcPinCode());
			else
				patientForm.setComm_pin("NA");
			patientForm.setDtTime(sdfw.format(ehfPatient.getRegHospDate()));
			String photoUrl=null;
			if(ehfPatient.getPatientScheme()!=null)
				{
					patientForm.setPatientScheme(ehfPatient.getPatientScheme());
					request.setAttribute("patientScheme",ehfPatient.getPatientScheme());
					if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
							config.getString("Scheme.JHS").equalsIgnoreCase(ehfPatient.getPatientScheme()))
						 photoUrl=patientService.getJournalistPhoto(ehfPatient.getCardNo());
					else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
							 && ehfPatient.getNewBornBaby().equalsIgnoreCase("Y"))
						photoUrl=null;
					else
						photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
				}
			else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
					 && ehfPatient.getNewBornBaby().equalsIgnoreCase(config.getString("Y")))
				photoUrl=null;
			else
				photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
			if(photoUrl!=null)
			{
				try
				{
					File photo = new File(photoUrl);
					fis = new FileInputStream(photo);
					dis= new DataInputStream(fis);
					bytes = new byte[dis.available()];
					fis.read(bytes);
					String lStrContentType=null;
					lStrContentType="image/jpg";
					session.setAttribute("ContentType", lStrContentType);
					session.setAttribute("File", bytes);
					fis.close();
					dis.close();
					patientForm.setPhotoUrl(photoUrl);
				}
				catch(Exception e)
				{
					GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
				}
			}
			String hospCode=ehfPatient.getRegHospId();
			String hospName=patientService.getHospName(hospCode);
			patientForm.setHospitalId(hospCode);
			request.setAttribute("hospitalId",hospCode);
			patientForm.setHospitalName(hospName);
			HashMap hashMap = new HashMap();
			hashMap.put("patientId", patientId);
			hashMap.put("mandateAttachmntsLst", mandateAttachmntsLst);
			List<LabelBean> attachmentsList = patientService.getAttachmentsLst(hashMap);
			request.setAttribute("attachmentsList", attachmentsList);
			
			hashMap.put("roleId", roleId);
			List<LabelBean> workFlowDtls = patientService.getJrnlstDWorkFlowDtls(hashMap);
			request.setAttribute("workFlowDtls", workFlowDtls);
			List<LabelBean> additionalAttach = patientService.getDlhJrnlstAddtnalAttach(hashMap);
			request.setAttribute("additionalDJHSAttach", additionalAttach);
			List<LabelBean> provisionalDtls = patientService.getCatProvisionalDtls(hashMap);
			request.setAttribute("provisionalDtls", provisionalDtls);
			List<LabelBean> admissionDtls = patientService.getAdmissionDtls(hashMap);//Chandana - 9045 - Added to get the admission details
			request.setAttribute("admissionDtls", admissionDtls);
			request.setAttribute("estmtdAmnt", admissionDtls.get(0).getNEXTVAL());
			String jrnlstDClaimNo = patientService.checkjrnlstDClaimNo(patientId);
			String claimsStatus = patientService.checkjrnlstDStatus(patientId);
			request.setAttribute("jrnlstDClaimNum", jrnlstDClaimNo);
			request.setAttribute("claimsStatus", claimsStatus);
			List<LabelBean> mandateAttachList = patientService.getDlhJrnlstMandateAttach(hashMap);
			request.setAttribute("mandateAttachList", mandateAttachList);
			/*String amount = patientService.getInitiatedAmount(patientId);
			request.setAttribute("claimAmount", amount);*/
			String amount = null;
			if(!"CD7351".equalsIgnoreCase(claimsStatus)){
				amount = patientService.getInitiatedAmount(patientId);
				if (amount != null) {
				    String[] values = amount.split("~");
				    request.setAttribute("approvedAmount", values[0]);
				    request.setAttribute("deductedAmount", values[1]);
				    request.setAttribute("claimAmount", values[2]);
				}
			}
			List<LabelBean> jrnlstDworkFlowDtls = patientService.getJrnlstDWorkFlowDtls(hashMap);
			request.setAttribute("jrnlstDworkFlowDtls", jrnlstDworkFlowDtls);
			
			request.setAttribute("roleId", roleId);
			request.setAttribute("dataIsPresent", "Y");
			request.setAttribute("type", type);
			if("Y".equalsIgnoreCase(acoFlag)){
				List<LabelBean> attachDtls = patientService.getDlhJrnlstMandateAttach(hashMap);
				request.setAttribute("attachDtls", attachDtls);
			}
			if("Y".equalsIgnoreCase(acoFlag))
				lStrPageName = "viewDlhClaimsForACOApproval";
			else
				lStrPageName = "dlhJrnlstClaimCaseDtls";
        }
        
        if (lStrActionVal != null && "updateMedcoDlhJrnlstClaim".equalsIgnoreCase(lStrActionVal)) {
            PatientForm patForm = (PatientForm) form;
            String status = null;
            FormFile[] disFile = patForm.getDischargeAttach();
            FormFile[] benFile = patForm.getBenAttach();
            FormFile[] caseSheetFile = patForm.getCaseSheetAttach();
            FormFile[] selfCertFile = patForm.getSelfCertAttach();
            FormFile[] invFile = patForm.getInvAttach();

            String patientId = request.getParameter("patientId");
            String initAmnt = request.getParameter("initAmnt");
            String estmtdAmnt = request.getParameter("estmtdAmount");//Chandana - 9045 - Added this to get the estimated amount from jsp
            String userId = (String) session.getAttribute("UserID");
            String remarks = request.getParameter("remarks");
            status = request.getParameter("action");
            String claimNo = null;
            HashMap hashMap = new HashMap();
            if ("Submit".equalsIgnoreCase(status)) {
                actnDone = "CD7321";
                claimStatus = "CD7351";
            } else if ("ReSubmit".equalsIgnoreCase(status)) {
                actnDone = "CD7333";
                claimStatus = patientService.getDlhJurnlstClaimStatus(patientId);
                claimNo = request.getParameter("claimNo");
                hashMap.put("claimNo", claimNo);
            }
            if("Submit".equalsIgnoreCase(status)){
	            String[] attachTypeIds = request.getParameterValues("attachTypeIds");
	            hashMap.put("attachTypeIds", attachTypeIds);
            }
            hashMap.put("patientId", patientId);
            hashMap.put("userId", userId);
            hashMap.put("remarks", remarks);
            hashMap.put("status", status);
            hashMap.put("disFile", disFile);
            hashMap.put("benFile", benFile);
            hashMap.put("caseSheetFile", caseSheetFile);
            hashMap.put("selfCertFile", selfCertFile);
            hashMap.put("invFile", invFile);
            hashMap.put("initAmnt", initAmnt);
            hashMap.put("estmtdAmnt", estmtdAmnt);//Chandana - 9045 - Added this to send the estimated amount to method

            nextRoleStatus = patientService.getNextStatus(claimStatus, actnDone);
            if (nextRoleStatus != null && nextRoleStatus.contains("~")) {
                String[] parts = nextRoleStatus.split("~");
                nextStatus = parts[0];
                userRole = parts[1];
            }
            hashMap.put("nextStatus", nextStatus);
            hashMap.put("userRole", userRole);
            
            String opdClaimApprovedSeq = patientService.updateMedcoDlhJrnlstClaim(hashMap);

            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(opdClaimApprovedSeq));
            return null;
        }
        if(lStrActionVal != null && "viewDocuments".equalsIgnoreCase(lStrActionVal)){
        	String filePath = (String) request.getParameter("filePath");
        	if (filePath != null && !filePath.isEmpty()) {
    			String base64Data = null;
    			if(filePath.toLowerCase().endsWith(".pdf")){
    				base64Data = FileConstants.convertPDFToBase64(filePath);
    			}else{
    				base64Data = FileConstants.convertImageToBase64(filePath);
    			}
    			response.setContentType("text/html");		
    			response.getWriter().write(base64Data);
    		}
        }
        if(lStrActionVal != null && "getDlhClaimCasesforApproval".equalsIgnoreCase(lStrActionVal)){
        	String status = (String) request.getParameter("status");
        	List<String> claimsCasesList = new ArrayList<String>();
    	    String assigningPatientId = null;
    	    String claimSeqId = null;    	    
    	    String[] claimCasePart = null;
        	claimsCasesList = patientService.getDlhJrnlstClaimCases(status,roleId,lStrUserId);
        	if(!claimsCasesList.isEmpty() && claimsCasesList.size() > 0){
        		request.setAttribute("dataIsPresent", "Y");
        			if(!claimsCasesList.isEmpty() && claimsCasesList.size() > 0){
        				for(String claimCase : claimsCasesList){
        					System.out.println("Cases: "+ claimCase);
        					claimCasePart = claimCase.split("~");
        					claimSeqId = claimCasePart[0];
        						assigningPatientId = claimCasePart[1];
        						claimStatus = claimCasePart[2];
        						if("CD7357".equalsIgnoreCase(claimStatus)){
        							String sameChUser = patientService.getFlagForACOReturnedClaim(lStrUserId,claimCasePart[0]);
        							if("Y".equalsIgnoreCase(sameChUser))
        								break;
        						}
        						else
        							break;
        				}
        			}else{
        				request.setAttribute("dataIsPresent", "N");
        			}
        	}else{
        		request.setAttribute("dataIsPresent", "N");
        	}
        	if(assigningPatientId != null && !"".equalsIgnoreCase(assigningPatientId)){
        		EhfPatient ehfPatient = new EhfPatient();
        		patientForm.setPatientNo(assigningPatientId);
    			String check=patientService.checkDentalClinic(lStrUserId,assigningPatientId);
    			String hospGov=patientService.checkGovernmentHosp(lStrUserId,assigningPatientId);
    			if(!check.equals("hospital")){
    				patientForm.setHosType("DC");
    			}else{
    				patientForm.setHosType("hospital");
    			}
    			ehfPatient=(EhfPatient)patientService.getPatientDetails(assigningPatientId);
    			session.setAttribute("patCrtdt",ehfPatient.getCrtDt());

    			patientForm.setCardNo(ehfPatient.getCardNo());
    			patientForm.setFname(ehfPatient.getName());
    			
    			if(ehfPatient.getCardIssueDt()!=null)
    				patientForm.setCardIssueDt(sdf.format(ehfPatient.getCardIssueDt()));
    			else
    				patientForm.setCardIssueDt("NA");
    			if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
    				patientForm.setGender("Male");
    			else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
    				patientForm.setGender("Female");
    			if(ehfPatient.getEnrollDob()!=null)
    				patientForm.setDateOfBirth(sdf.format(ehfPatient.getEnrollDob()));
    			if(ehfPatient.getAge()!=null){
    				patientForm.setYears(ehfPatient.getAge().toString());
    				if(ehfPatient.getAge().toString() != null && (ehfPatient.getAge() <=13 ))
    					request.setAttribute("child", "Y");
    				else
    					request.setAttribute("child", "N");
    			}
    			if(ehfPatient.getAgeMonths()!=null)
    				patientForm.setMonths(ehfPatient.getAgeMonths().toString());
    			if(ehfPatient.getAgeDays()!=null)
    				patientForm.setDays(ehfPatient.getAgeDays().toString());
    			String relationId=ehfPatient.getRelation();
    			String relationName=patientService.getRelationName(relationId);
    			patientForm.setRelation(relationName);
    			String casteId=ehfPatient.getCaste();
    			if(casteId!=null && !casteId.equals(""))
    			{
    				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
    				patientForm.setCaste(casteName);
    			}
    			if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
    				patientForm.setContactno(ehfPatient.getContactNo().toString());

    			patientForm.setOccupation(ehfPatient.getOccupationCd());
    			if(ehfPatient.getSchemeId()!=null )
    				session.setAttribute("regPatientScheme",ehfPatient.getSchemeId());
    			patientForm.setScheme(ehfPatient.getSchemeId());
    			patientForm.setTelScheme(ehfPatient.getSchemeId());
    			
    			String slabType=null;
    			String slab=null;
    			if(ehfPatient.getSlab()!=null)
    				slabType=ehfPatient.getSlab();
    			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
    				slab=config.getString("Slab.Name.SemiPrivateWard");
    			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
    				slab=config.getString("Slab.Name.PrivateWard");
    			patientForm.setSlab(slab);
    			patientForm.setHno(ehfPatient.getHouseNo());
    			patientForm.setStreet(ehfPatient.getStreet());
    			patientForm.setState(patientService.getLocationName(ehfPatient.getState()));
    			String distCode=ehfPatient.getDistrictCode();
    			String distName=patientService.getLocationName(distCode);
    			patientForm.setDistrict(distName);
    			String mandalCode=ehfPatient.getMandalCode();
    			String mandalName=patientService.getLocationName(mandalCode);
    			patientForm.setMandal(mandalName);
    			String villageCode=ehfPatient.getVillageCode();
    			String villageName=patientService.getLocationName(villageCode);
    			patientForm.setVillage(villageName);
    			if(ehfPatient.getPinCode()!=null && !ehfPatient.getPinCode().equalsIgnoreCase(""))
    				patientForm.setPin(ehfPatient.getPinCode());
    			else
    				patientForm.setPin("NA");
    			patientForm.setComm_hno(ehfPatient.getcHouseNo());
    			patientForm.setComm_street(ehfPatient.getcStreet());
    			patientForm.setComm_state(patientService.getLocationName(ehfPatient.getcState()));
    			patientForm.setComm_dist(patientService.getLocationName(ehfPatient.getcDistrictCode()));
    			patientForm.setComm_mandal(patientService.getLocationName(ehfPatient.getcMandalCode()));
    			patientForm.setComm_village(patientService.getLocationName(ehfPatient.getcVillageCode()));
    			if(ehfPatient.getcPinCode()!=null && !ehfPatient.getcPinCode().equalsIgnoreCase(""))
    				patientForm.setComm_pin(ehfPatient.getcPinCode());
    			else
    				patientForm.setComm_pin("NA");
    			patientForm.setDtTime(sdfw.format(ehfPatient.getRegHospDate()));
    			String photoUrl=null;
    			if(ehfPatient.getPatientScheme()!=null)
    				{
    					patientForm.setPatientScheme(ehfPatient.getPatientScheme());
    					request.setAttribute("patientScheme",ehfPatient.getPatientScheme());
    					if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
    							config.getString("Scheme.JHS").equalsIgnoreCase(ehfPatient.getPatientScheme()))
    						 photoUrl=patientService.getJournalistPhoto(ehfPatient.getCardNo());
    					else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
    							 && ehfPatient.getNewBornBaby().equalsIgnoreCase("Y"))
    						photoUrl=null;
    					else
    						photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
    				}
    			else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
    					 && ehfPatient.getNewBornBaby().equalsIgnoreCase(config.getString("Y")))
    				photoUrl=null;
    			else
    				photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
    			if(photoUrl!=null)
    			{
    				try
    				{
    					File photo = new File(photoUrl);
    					fis = new FileInputStream(photo);
    					dis= new DataInputStream(fis);
    					bytes = new byte[dis.available()];
    					fis.read(bytes);
    					String lStrContentType=null;
    					lStrContentType="image/jpg";
    					session.setAttribute("ContentType", lStrContentType);
    					session.setAttribute("File", bytes);
    					fis.close();
    					dis.close();
    					patientForm.setPhotoUrl(photoUrl);
    				}
    				catch(Exception e)
    				{
    					GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
    				}
    			}
    			String hospCode=ehfPatient.getRegHospId();
    			String hospName=patientService.getHospName(hospCode);
    			String dentalFlg=patientService.checkDentalHospital(hospCode);
    			patientForm.setHospitalId(hospCode);
    			request.setAttribute("hospitalId",hospCode);
    			patientForm.setHospitalName(hospName);
    			
    			HashMap hashMap = new HashMap();
    			hashMap.put("patientId", assigningPatientId);
    			hashMap.put("claimAssignFlag", "Y");
				request.setAttribute("claimNo", claimSeqId);
				
				List<LabelBean> provisionalDtls = patientService.getCatProvisionalDtls(hashMap);
				request.setAttribute("provisionalDtls", provisionalDtls);
				
				List<LabelBean> admissionDtls = patientService.getAdmissionDtls(hashMap);//Chandana - 9045 - Added to get the admission details
				if(admissionDtls != null || !admissionDtls.isEmpty())
					request.setAttribute("estmtdAmnt", admissionDtls.get(0).getNEXTVAL());
				request.setAttribute("admissionDtls", admissionDtls);
				
    			List<LabelBean> additionalAttach = patientService.getDlhJrnlstAddtnalAttach(hashMap);
				request.setAttribute("additionalAttach", additionalAttach);
				
				List<LabelBean> attachDtls = patientService.getDlhJrnlstMandateAttach(hashMap);
				request.setAttribute("attachDtls", attachDtls);
				String amount = patientService.getInitiatedAmount(assigningPatientId);
				//request.setAttribute("claimAmount", amount);
				if (amount != null) {
				    String[] values = amount.split("~");
				    request.setAttribute("approvedAmount", values[0]);
				    request.setAttribute("deductedAmount", values[1]);
				    request.setAttribute("claimAmount", values[2]);
				}
				
				List<LabelBean> jrnlstDworkFlowDtls = patientService.getJrnlstDWorkFlowDtls(hashMap);
				request.setAttribute("jrnlstDworkFlowDtls", jrnlstDworkFlowDtls);
				
    			request.setAttribute("claimStatus", claimStatus);
        	}else{
        		request.setAttribute("dataIsPresent", "N");
        	}
        	request.setAttribute("roleId", roleId);
        	/*if("CD7357".equalsIgnoreCase(claimStatus) && ("GP29".equalsIgnoreCase(roleId) || "GP9".equalsIgnoreCase(roleId))){//Chandana - 8602 - if condition to get the approved amount for CD7343 this status
        		String amount = patientService.getApprovedAmount(patientForm.getPatientNo());
        		String approvedAmount = "0"; String deductedAmount = "0";
        		if (amount != null && amount.contains("~")) {
                    String[] parts = amount.split("~");
                    approvedAmount = parts[0];
                    deductedAmount = parts[1];
                }
        		request.setAttribute("approvedAmount", approvedAmount);
        		request.setAttribute("deductedAmount", deductedAmount);
			}*/
        	lStrPageName = "dlhJrnlstClaimCaseDtlsForApprovals";
        }
        if(lStrActionVal != null && "updCEXDlhJrnlstClaim".equalsIgnoreCase(lStrActionVal)){
        	String result = null;
        	String status = request.getParameter("status");
        	String claimNo = request.getParameter("claimNo");
        	String remarks = request.getParameter("remarks");
        	String patientId = request.getParameter("patientId");
        	String finalAmt = request.getParameter("finalAmt");
        	String clmStatus = request.getParameter("claimStatus");
        	HashMap hashMap = new HashMap();
        	
        	hashMap.put("status", status);
        	hashMap.put("claimNo", claimNo);
        	hashMap.put("remarks", remarks);
        	hashMap.put("userId",lStrUserId);
        	hashMap.put("patientId", patientId);
        	hashMap.put("finalAmt", finalAmt);
        	if("Approve".equalsIgnoreCase(status)) 
        		actnDone = "CD7331";
        	else if("Return".equalsIgnoreCase(status))
        		actnDone = "CD7332";
        	//claimStatus = patientService.getDlhJrnlstClaimStatus(patientId);
        	if(clmStatus != null || "".equalsIgnoreCase(clmStatus)) 
        		nextRoleStatus = patientService.getNextStatus(clmStatus,actnDone);
        	if (nextRoleStatus != null && nextRoleStatus.contains("~")) {
                String[] parts = nextRoleStatus.split("~");
                nextStatus = parts[0];
                userRole = parts[1];
            }
        	hashMap.put("nextStatus", nextStatus);
        	hashMap.put("userRole", userRole);
        	result = patientService.updCEXDlhJrnlstClaim(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(result);   
        	response.getWriter().write(gsonString);
        	return null;
        }
        if("updDlhJrnlstCHClaim".equalsIgnoreCase(lStrActionVal)){//Chandana - 9033 - Added this action val for updating the claims from CH
        	String result = null;
        	String status = request.getParameter("status");
        	String claimNo = request.getParameter("claimNo");
        	String deductAmt = request.getParameter("deductAmt");
        	String finalAmt = request.getParameter("finalAmt");
        	String remarks = request.getParameter("remarks");
        	String patientId = request.getParameter("patientId");
        	String clmStatus = request.getParameter("claimStatus");
        	HashMap hashMap = new HashMap();
        	
        	hashMap.put("status", status);
        	hashMap.put("claimNo", claimNo);
        	hashMap.put("remarks", remarks);
        	hashMap.put("deductAmt", deductAmt);
        	hashMap.put("finalAmt", finalAmt);
        	hashMap.put("userId",lStrUserId);
        	hashMap.put("patientId",patientId);
        	
        	if("Approve".equalsIgnoreCase(status))
        		actnDone = "CD7322";
        	else if("ReApprove".equalsIgnoreCase(status))
        		actnDone = "CD7346";
			else if("Reject".equalsIgnoreCase(status))
				actnDone = "CD7323";
			else
				actnDone = "CD7324";
			
        	//claimStatus = patientService.getClaimStatus(patientId);
        	if(clmStatus != null || "".equalsIgnoreCase(clmStatus)) //Chandana - 8036 - if not null then sending the claim status and taking back the next status
        		nextRoleStatus = patientService.getNextStatus(clmStatus,actnDone);
        	if (nextRoleStatus != null && nextRoleStatus.contains("~")) {//Chandana - 8602 - Added if condition and getting the both next action and user role from the workflow status table
                String[] parts = nextRoleStatus.split("~");
                nextStatus = parts[0];
                userRole = parts[1];
            }
        	hashMap.put("nextStatus", nextStatus);
        	hashMap.put("userRole", userRole);
        	result = patientService.updDlhJrnlstCHClaim(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(result);   
        	response.getWriter().write(gsonString);
        	return null;
        }
      //Chandana - 9033 - To get the ACO approved cases
    	if("getDlhClaimsforACOApproval".equalsIgnoreCase(lStrActionVal)){
    		String patientId  = request.getParameter("patientId");
    		String cardNo = request.getParameter("cardNo");
    		String clmStatus = request.getParameter("claimStatus");
    		String claimSubmittedNo = request.getParameter("claimSubmittedNo");
    		String claimDt  = request.getParameter("claimDt");
    		String ajaxCall = request.getParameter("ajaxCall");
    		HashMap hashMap = new HashMap();
    		hashMap.put("patientId", patientId);
    		hashMap.put("cardNo", cardNo);
    		hashMap.put("clmStatus", clmStatus);
    		hashMap.put("opClaimNo", claimSubmittedNo);
    		hashMap.put("claimDt", claimDt);
    		hashMap.put("ajaxCall", ajaxCall);
    		String acoFlag = "Y";
    		List<LabelBean> opClaimCasesList = patientService.getDlhJrnlstClaimsLstForApprvl(hashMap);
    		List<LabelBean> claimStatusList = patientService.getJrnlstDlhClaimStatusLst(acoFlag);
    		Gson gson = new Gson();
    		if(ajaxCall == null || !"Y".equalsIgnoreCase(ajaxCall)){
    			request.setAttribute("opClaimCasesList", opClaimCasesList);
    			request.setAttribute("claimStatusList", claimStatusList);
    			lStrPageName = "viewDlhJrnlstClaimsForACO";
    		}else{
            	String gsonString = gson.toJson(opClaimCasesList);   
            	response.getWriter().write(gsonString);
            	return null;
    		}
    	}
    	//Chandana - 9033 - Added this for ACO kept pending action to CH
        if("dlhJrnlstClaimPendingByACO".equalsIgnoreCase(lStrActionVal)){
        	String result = null;
        	String actionDone = null;
        	String action = request.getParameter("action");
        	String claimNo = request.getParameter("claimNo");
        	String finalAmt = request.getParameter("finalAmt");
        	String remarks = request.getParameter("remarks");
        	String patientId = request.getParameter("patientId");
        	String currentStatus = request.getParameter("claimsStatus");
        	HashMap hashMap = new HashMap();
        	nextStatus = null;
        	nextRoleStatus = null;
        	userRole = null;
            if ("Return".equalsIgnoreCase(action) && ("CD7356".equalsIgnoreCase(currentStatus) || "CD7358".equalsIgnoreCase(currentStatus)))
                actionDone = "CD7342";
            nextRoleStatus = patientService.getNextStatus(currentStatus, actionDone);
            if (nextRoleStatus != null && nextRoleStatus.contains("~")) {
                String[] parts = nextRoleStatus.split("~");
                nextStatus = parts[0];
                userRole = parts[1];
            }
        	hashMap.put("status", action);
        	hashMap.put("claimNo", claimNo);
        	hashMap.put("remarks", remarks);
        	hashMap.put("finalAmt", finalAmt);
        	hashMap.put("userId",lStrUserId);
        	hashMap.put("patientId",patientId);
        	hashMap.put("currentStatus", currentStatus);
        	hashMap.put("nextStatus", nextStatus);
        	hashMap.put("userRole", userRole);
        	result = patientService.dlhJrnlstClaimPenByACO(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(result);   
        	response.getWriter().write(gsonString);
        	return null;
        }
      //Chandana - 9033 - Below is for verify from ACO role
        if ("dlhJrnlstdClaimsBulkApprvl".equalsIgnoreCase(lStrActionVal)) {
            String acoFlag = request.getParameter("acoFlag");
            String action = request.getParameter("actionDone");
            String cases = request.getParameter("cases");
            String[] casesSelected = null;
            int count = 0;
            if (cases != null && cases.length() > 0) {
                casesSelected = cases.split("~");
                if (casesSelected != null) {
                    for (String caseId : casesSelected) {
                        if (caseId != null && !("").equalsIgnoreCase(caseId)) {
                        	String caseStatus = null;
                            String patientId = null;
                            String actionDone = null;
                            patientId = patientService.getPatientId(caseId);
                            caseStatus = patientService.getDlhJurnlstClaimStatus(patientId);
                            if ("Approve".equalsIgnoreCase(action) && ("CD7356".equalsIgnoreCase(caseStatus) || "CD7358".equalsIgnoreCase(caseStatus)))
                                actionDone = "CD7326";
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
                            ClaimsFlowVO resultVO = patientService.saveDlhJrnlstClaim(claimFlowVO);
                            count++;
                        }
                    }
                }
            }
    		List<LabelBean> opClaimCasesList = patientService.getDlhJrnlstClaimsLstForApprvl(new HashMap());
    		List<LabelBean> claimStatusList = patientService.getJrnlstDlhClaimStatusLst(acoFlag);
    		Gson gson = new Gson();
    		request.setAttribute("opClaimCasesList", opClaimCasesList);
    		request.setAttribute("claimStatusList", claimStatusList);
    		lStrPageName = "viewDlhJrnlstClaimsForACO";
        }
      //Chandana - 8755 - Added this action flag for deleting or saving the additional attachments
       else if(lStrActionVal != null && "updDlhJrnlAdditnalAttach".equalsIgnoreCase(lStrActionVal)){
        	PatientForm patForm = (PatientForm) form;
        	FormFile file = null;
        	String fileName = null;
        	String description = null;
        	String remarks = null;
	        file = patForm.getAttachment();
			fileName = patForm.getFname();
			description = patientForm.getMsg();
			remarks = patientForm.getRemarks();
        	String seqId = request.getParameter("seqId");
        	String patientId = request.getParameter("patientNo");
        	String action = request.getParameter("action"); 
        	String type = "Additional";
        	HashMap hashMap = new HashMap();
        	hashMap.put("seqId",seqId);
        	hashMap.put("file", file);
        	hashMap.put("fileName", fileName);
        	hashMap.put("patientId", patientId);
        	hashMap.put("lStrUserId", lStrUserId);
        	hashMap.put("type", type);
        	hashMap.put("action", action);
        	hashMap.put("description", description);
        	hashMap.put("remarks", remarks);
        	String attachDel = patientService.updDlhJrnlstAddtnalAttach(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(attachDel);   
        	response.getWriter().write(gsonString);
        	return null;
        }
		return mapping.findForward(lStrPageName);
	  }
	
	
}