package com.ahct.bioMetric.action;


import org.apache.struts.upload.FormFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;

import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.bioMetric.form.BioMetricForm;
import com.ahct.bioMetric.service.BioMetricRegistrationService;
import com.ahct.bioMetric.vo.BioMetricVo;
import com.ahct.common.service.CommonService;
import com.ahct.common.vo.LabelBean;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;




public class BioMetricRegistrationAction extends Action {

private final static Logger GLOGGER = Logger.getLogger ( BioMetricRegistrationAction.class ) ;


@SuppressWarnings({ "unchecked", "unused" })
public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
{
response.setHeader("Cache-Control","no-cache");
response.setHeader("pragma","no-cache");
response.setDateHeader("Expires", 0);
HttpSession session = request.getSession ( false ) ;


ConfigurationService configurationService = 
(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");

Configuration config = configurationService.getConfiguration();

BioMetricRegistrationService  bioMetricService=(BioMetricRegistrationService)ServiceFinder.getContext(request).getBean("bioMetricService");
CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");

BioMetricForm  bioMetricForm=(BioMetricForm)form;

String lStrEmpId = null ;
String lStrLocId = null ;
String lStrLangId = null ;
String lStrUserName = null;
String lStrFlagStatus = null;
String lStrUserRole = null;
String userState=null;
String msg=null;
List<LabelBean> rolesList = null;
if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
Date crtDt = new Date () ;
if ( ( session.getAttribute ( "LocID" ) != null ) && !( session.getAttribute ( "LocID" ).equals ( "" ) ) )
lStrLocId = ( String ) session.getAttribute ( "LocID" ) ;
if ( ( session.getAttribute ( "LangID" ) != null ) && !( session.getAttribute ( "LangID" ).equals ( "" ) ) )
lStrLangId = ( String ) session.getAttribute ( "LangID" ) ;
if ( ( session.getAttribute ( "userName" ) != null ) && !( session.getAttribute ( "userName" ).equals ( "" ) ) )
lStrUserName = ( String ) session.getAttribute ( "userName" ) ;
if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
rolesList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
if ( ( session.getAttribute ( "UserRole" ) != null ) && !( session.getAttribute ( "UserRole" ).equals ( "" ) ) )
lStrUserRole = ( String ) session.getAttribute ( "UserRole" ) ;
if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
userState = ( String ) session.getAttribute ( "userState" ) ;
request.setAttribute("userState", userState);



String userName=null;
String lStrLangID=null;
String lStrUserId =null;
List<LabelBean> grpList=null;
List<String> lStrgrpList=new ArrayList<String>();
String roleId=null;
SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
String serverDt = sdfw.format(new Date());
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
FileInputStream fis=null;
DataInputStream dis=null;
String schemeId=null;
byte bytes[]=null;
//EhfmSeq ehfmSeq=null;
String smsNextVal="";
String callType=null;
String ipOpType = null;
if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
{
callType=request.getParameter("callType");
}
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
if(session.getAttribute("EmpID").toString()!=null)
{
lStrUserId = session.getAttribute("EmpID").toString();
}
if(session.getAttribute("LangID").toString()!=null)
{
lStrLangID = session.getAttribute("LangID").toString();
}
if(session.getAttribute("userName").toString()!=null)
{
userName=session.getAttribute("userName").toString();
}
if(session.getAttribute("userState").toString()!=null)
{
schemeId=session.getAttribute("userState").toString();
}
if(session.getAttribute("groupList").toString()!=null)
{
grpList=(List<LabelBean>)session.getAttribute("groupList");
}
for(LabelBean labelBean:grpList)
{
lStrgrpList.add(labelBean.getID());
}
if(lStrgrpList.contains(config.getString("Group.Mithra")))
{
roleId=config.getString("Group.Mithra");
}
else if(lStrgrpList.contains(config.getString("Group.Medco")))
{
roleId=config.getString("Group.Medco");
session.setAttribute("medco", "Y");

}
else if(lStrgrpList.contains(config.getString("Group.NTL")))
{
roleId=config.getString("Group.NTL");


}
else if(lStrgrpList.contains(config.getString("Group.DC")))
{
roleId=config.getString("Group.DC");


}
else if(lStrgrpList.contains(config.getString("Group.DM")))
{
roleId=config.getString("Group.DM");


}
else if(lStrgrpList.contains(config.getString("Group.DTL")))
{
roleId=config.getString("Group.DTL");


}
else if(lStrgrpList.contains(config.getString("Group.Pex")))
{
roleId=config.getString("Group.Pex");
}else if(lStrgrpList.contains(config.getString("Group.COEX")))
{
roleId=config.getString("Group.COEX");
}else if(lStrgrpList.contains(config.getString("Group.COTD")))
{
roleId=config.getString("Group.COTD");
}else if(lStrgrpList.contains(config.getString("Group.COCH")))
{
roleId=config.getString("Group.COCH");
}else if(lStrgrpList.contains(config.getString("GROUP.COACO")))
{
roleId=config.getString("GROUP.COACO");
}else if(lStrgrpList.contains(config.getString("GROUP.COEO")))
{
roleId=config.getString("GROUP.COEO");
}
else
{
roleId=lStrgrpList.get(0);
}

String lStrActionVal = request.getParameter("actionFlag");

String lStrPageName = null; 

String stateType=request.getParameter("stateType");
String distHdrId=config.getString("distLocHdr");
String state=config.getString("telangana_hdr_id");

List<LabelBean> DistList=new ArrayList<LabelBean>();
DistList=commonService.getLocationsNew(distHdrId,state,"O");


if ("getLocations".equalsIgnoreCase(lStrActionVal))
{
	String locHdrId=request.getParameter("lStrHdrId");
	String stateId=config.getString("telangana_hdr_id");
	String distId=request.getParameter("distId");
	String mandalId=request.getParameter("mandalId");
	String villageId=request.getParameter("villageId");
	stateType=request.getParameter("stateType");
	try {
		List<LabelBean> cmbHdrList=null;
		List<String> locationsList = new ArrayList<String>();
		if(stateId!=null)
		{
			cmbHdrList=commonService.getLocationsNew(locHdrId, stateId,stateType);
			session.setAttribute("districtList",cmbHdrList);
		}
		else if(distId!=null)
		{
			cmbHdrList=commonService.getAsrimLocations(locHdrId, distId);
			session.setAttribute("mandalList",cmbHdrList);
		}
		else if(mandalId!=null)
		{
			cmbHdrList=commonService.getAsrimLocations(locHdrId, mandalId);
			session.setAttribute("villageList",cmbHdrList);
		}
		else if(villageId!=null)
		{
			cmbHdrList=commonService.getAsrimLocations(locHdrId, villageId);
			session.setAttribute("hamletList",cmbHdrList);
		}
		for (LabelBean labelBean: cmbHdrList) {
			if (labelBean.getID() != null && 
					labelBean.getVALUE() != null)
				if (locationsList != null && locationsList.size() > 0) {
					locationsList.add(labelBean.getID() + "~" + 
							labelBean.getVALUE());
				} else
					locationsList.add(labelBean.getID() + "~" + 
							labelBean.getVALUE());
		}
		if (locationsList != null && locationsList.size() > 0)

			request.setAttribute("AjaxMessage", locationsList);
	}
	catch(Exception e)
	{
		GLOGGER.error ( "Exception occurred in Ajax getLocations actionFlag in PatientAction." +e.getMessage()) ;
	}
	
	lStrPageName="ajaxResult";
}



if(lStrActionVal!= null && "uploadPhoto".equals(lStrActionVal)) 
{
lStrActionVal = request.getParameter("actionVal");
 String type = request.getParameter("docType");  
 String upldType = request.getParameter("UpdType");  
 String userId=(String) session.getAttribute("UserID");
 BioMetricVo bioMetricVo=new BioMetricVo(); 
 String msg1 = null;

  String lStrSharePath = null;

  SimpleDateFormat sdfw2 = new SimpleDateFormat("dd-MM-yyyy");
  Date date = new Date();
String crtDate = sdfw.format(date);
  lStrSharePath = config.getString("biometricReportFolder")+config.getString("userPhoto")+userId;  
  
  String filePath= lStrSharePath;
 
 Long attAllowSize = null ;
 if(request.getParameter("size")!=null && !request.getParameter("size").equalsIgnoreCase(""))
 attAllowSize = Long.parseLong((String)request.getParameter("size"));
 String resultMsg = null;
 String dir = null;
 int i=0;
  
 FormFile formFileObj = bioMetricForm.getPhotoAttach();
 try{
 if(formFileObj.getFileSize() > attAllowSize)//start 006
{
 String exceedSize = null;
 if(attAllowSize/1024 > 1024)
 {
exceedSize = (attAllowSize/(1024*1024)) + "MB";
 }
 else
 {
exceedSize = (attAllowSize/1024) + "KB";
 }
 
 resultMsg = ASRIConstants.ATTCH_SIZE_EXCEED_ERROR+exceedSize+ " in \\'"+ formFileObj.getFileName()+ "\\' \\n";  
 
}
 else
 {
 java.util.Date ldtToday = new java.util.Date();
 String fullPath = filePath ; 
 dir =fullPath;
 boolean flag = ( new File ( dir ) ).mkdirs () ;
 dir = dir +ASRIConstants.SLASH_VALUE+ ldtToday.getTime()+ASRIConstants.UNDERSCORE_VALUE+formFileObj.getFileName();
 File lFileFS = new File ( dir ) ;
FileOutputStream fos = new FileOutputStream ( lFileFS  + "/" ) ;
fos.write ( formFileObj.getFileData() ) ;

bioMetricVo.setUserId(userId);
bioMetricVo.setPHOTO(dir);

  

try{
msg1=bioMetricService.uploadEmpPhoto(bioMetricVo);
}
catch(Exception e)
{
e.printStackTrace();
}

  
 }
 }
 catch(Exception e)
 {
 resultMsg = ASRIConstants.ATTCH_UPLD_FAILURE + "  \\'"+ formFileObj.getFileName()+ "\\' \\n";
e.printStackTrace(); 
 }

if(("success").equalsIgnoreCase(msg1))
bioMetricForm.setPhotoUpload("Y");
  
lStrActionVal = "bioCapture";
 
 }















if("bioCapture".equalsIgnoreCase(lStrActionVal))
		{
		
		String userId=(String) session.getAttribute("UserID");
		String sysMacId=request.getParameter("sysMacId");
		String photoUpload=bioMetricForm.getPhotoUpload();
		String hospDetails=null;
		String[] hosp;
		String hospId=null;
		String hospMacId=null;
		String[] sysMacIdArr;
		String[] hospMacIdArr;
		String empCapMacId = null;
		boolean isTrue=false;
		boolean isRegistered=false;
		String[] hospListArr;
		
		List<BioMetricVo> hospMacList = new ArrayList<BioMetricVo>();
		
		if(userId!=null)
		isRegistered=bioMetricService.bioEnrollStatus(userId);
		
		if(isRegistered)
		{
		request.setAttribute("isRegistered","Y");
		}
		
		if(("Y").equalsIgnoreCase(photoUpload))
		{
		isTrue=true;
		}
		
		if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.Mithra")))
		{
		hospDetails=bioMetricService.getMacIdForHosp(userId);
		}
		if(roleId!=null && config.getString("Group.Others").contains("~"+roleId+"~"))
		{
		System.out.println(roleId);
		hospMacList=bioMetricService.getMacIdForOthers(userId);
		
		}
		
		if(hospDetails!=null){
		hosp=hospDetails.split("~");
		hospId=hosp[0];
		hospMacId=hosp[1];
		}
		
		if(sysMacId!=null&&hospMacId!=null&&!("null").equalsIgnoreCase(hospMacId) ){
		
		sysMacIdArr=sysMacId.split(",");
		
		hospMacIdArr=hospMacId.split(",");
		
		if(hospMacIdArr!=null && sysMacIdArr!=null ){
		for(String hospSysMacId:hospMacIdArr)
		{
		for(String macId:sysMacIdArr)
		{
		if(macId!=null)
		{
		if(hospSysMacId.equalsIgnoreCase(macId))
		{
		isTrue=true;
		empCapMacId = macId;
		}
		
		}
		}
		}
		}
		}
		if(hospMacList!=null && hospMacList.size()>0)
		{
		for(int i=0;i<hospMacList.size();i++)
		{
		if(sysMacId!=null&&hospMacList.get(i).getHospMacId()!=null )
		{
		sysMacIdArr=sysMacId.split(",");
		hospListArr=hospMacList.get(i).getHospMacId().split(",");
		for(String hospListArrId:hospListArr)
		{
		for(String macId:sysMacIdArr)
		{
		if(macId!=null)
		{
		if(hospListArrId.equalsIgnoreCase(macId))
		{
		isTrue=true;
		hospId=hospMacList.get(i).getHospId();
		hospMacId=hospListArrId;
		empCapMacId=hospListArrId;
		}
		
		}
		
		}
		}
		
		}
		
		}
		
		}
		 if(isTrue){
		
		List<BioMetricVo> bioMetricVo=new ArrayList<BioMetricVo>();
		bioMetricVo=bioMetricService.getUserDetails(userId);
		if(bioMetricVo!=null && bioMetricVo.size()>0)
		{
		bioMetricForm.setUserId(bioMetricVo.get(0).getUSERID());
		bioMetricForm.setUserName(bioMetricVo.get(0).getEMPLOYEENAME());
		bioMetricForm.setDesignation(bioMetricVo.get(0).getDESIGNATION());
		bioMetricForm.setDepartment(bioMetricVo.get(0).getDEPARTMENT());
		bioMetricForm.setCugNumber(bioMetricVo.get(0).getMOBILE());
		bioMetricForm.setMobNumber(bioMetricVo.get(0).getMOBILE());
		bioMetricForm.setAddress(bioMetricVo.get(0).getADDRESS());
		bioMetricForm.setPermAddress(bioMetricVo.get(0).getPERMADDRESS());
		bioMetricForm.setLoginName(bioMetricVo.get(0).getLOGINNAME());
		bioMetricForm.setHospId(hospId);
		bioMetricForm.setHospMacId(empCapMacId);
		String photoUrl=bioMetricVo.get(0).getPHOTO();
		
		
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
		  request.setAttribute ( "FileName", photoUrl ) ;
		  
		fis.close();
		dis.close();
		bioMetricForm.setPhotoUrl(photoUrl);
		}
		catch(Exception e)
		{
		GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
		}
		}
		
		
		}
		
		if(isRegistered)
		{
		request.setAttribute("isRegistered","Y");
		}
		
		}
		
		else{
		request.setAttribute("macId","N");
		}
		 
		 request.setAttribute("roleId",roleId);
		lStrPageName="EnrollmentPage";
		
}


if("enrollUser".equalsIgnoreCase(lStrActionVal))
	{
	
	String userId=bioMetricForm.getUserId();
	boolean isEnroll=false;
	String address=null;String permAddress=null;String mobNumber=null;String fingerPrint=null;
	BioMetricVo bioMetricVo=new BioMetricVo();
	bioMetricVo.setUserId(userId);
	bioMetricVo.setMOBILE(bioMetricForm.getMobNumber());
	bioMetricVo.setADDRESS(bioMetricForm.getAddress());
	bioMetricVo.setPERMADDRESS(bioMetricForm.getPermAddress());
	bioMetricVo.setFINGERPRINT(bioMetricForm.getFingerPrint());
	bioMetricVo.setLOGINNAME(bioMetricForm.getLoginName());
	bioMetricVo.setHospId(bioMetricForm.getHospId());
	bioMetricVo.setHospMacId(bioMetricForm.getHospMacId());
	try{
	isEnroll=bioMetricService.biometricEnroll(bioMetricVo);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	if(isEnroll)
	{
	request.setAttribute("isEnroll",isEnroll);
	}
	lStrPageName="EnrollmentPage";
}


if("bioMetricAttendance".equalsIgnoreCase(lStrActionVal))
{
String userId=(String) session.getAttribute("UserID");
String sysMacId=request.getParameter("sysMacId");


String hospDetails=null;
String[] hosp;
String hospId=null;
String hospMacId=null;
String[] sysMacIdArr;
String[] hospMacIdArr;
String[] hospListArr;
String empCapMacId = null;
boolean isTrue=false;
boolean isRegistered=false;
List<BioMetricVo> hospMacList = new ArrayList<BioMetricVo>();

if(userId!=null)
isRegistered=bioMetricService.bioEnrollStatus(userId);

if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.Mithra")))
{
hospDetails=bioMetricService.getMacIdForHosp(userId);
}
if(roleId!=null && config.getString("Group.Others").contains("~"+roleId+"~"))
{
System.out.println(roleId);
hospMacList=bioMetricService.getMacIdForOthers(userId);

}

if(hospDetails!=null){
hosp=hospDetails.split("~");
hospId=hosp[0];
hospMacId=hosp[1];
}

if(isRegistered)
{
if(sysMacId!=null&&hospMacId!=null ){

sysMacIdArr=sysMacId.split(",");

hospMacIdArr=hospMacId.split(",");

List<LabelBean> othersList=new ArrayList<LabelBean>();
othersList=bioMetricService.getOthersList();
request.setAttribute("othersList",othersList);

if(hospMacIdArr!=null && sysMacIdArr!=null &&!("null").equalsIgnoreCase(hospMacId) ){
for(String hospSysMacId:hospMacIdArr)
{
for(String macId:sysMacIdArr)
{
if(macId!=null)
{
if(hospSysMacId.equalsIgnoreCase(macId))
{
isTrue=true;
empCapMacId = macId;
}

}
}
}
}
}
if(hospMacList!=null && hospMacList.size()>0)
{
for(int i=0;i<hospMacList.size();i++)
{
if(sysMacId!=null&&hospMacList.get(i).getHospMacId()!=null )
{
sysMacIdArr=sysMacId.split(",");
hospListArr=hospMacList.get(i).getHospMacId().split(",");
for(String hospListArrId:hospListArr)
{
for(String macId:sysMacIdArr)
{
if(macId!=null)
{
if(hospListArrId.equalsIgnoreCase(macId))
{
isTrue=true;
hospId=hospMacList.get(i).getHospId();
hospMacId=hospListArrId;
empCapMacId=hospListArrId;
}

}

}
}

}

}

}

 if(isTrue){

List<BioMetricVo> bioMetricVo=new ArrayList<BioMetricVo>();
bioMetricVo=bioMetricService.getUserDetails(userId);
if(bioMetricVo!=null && bioMetricVo.size()>0)
{
bioMetricForm.setUserId(bioMetricVo.get(0).getUSERID());
bioMetricForm.setUserName(bioMetricVo.get(0).getEMPLOYEENAME());
bioMetricForm.setDesignation(bioMetricVo.get(0).getDESIGNATION());
bioMetricForm.setDepartment(bioMetricVo.get(0).getDEPARTMENT());
bioMetricForm.setCugNumber(bioMetricVo.get(0).getMOBILE());
bioMetricForm.setMobNumber(bioMetricVo.get(0).getMOBILE());
bioMetricForm.setAddress(bioMetricVo.get(0).getADDRESS());
bioMetricForm.setPermAddress(bioMetricVo.get(0).getPERMADDRESS());
bioMetricForm.setLoginName(bioMetricVo.get(0).getLOGINNAME());
bioMetricForm.setHospId(hospId);
bioMetricForm.setHospMacId(empCapMacId);
String photoUrl=bioMetricVo.get(0).getPHOTO();


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
  request.setAttribute ( "FileName", photoUrl ) ;
  
fis.close();
dis.close();
bioMetricForm.setPhotoUrl(photoUrl);
}
catch(Exception e)
{
GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
}
}


}

if(isRegistered)
{
request.setAttribute("isRegistered","Y");
}

}
 
 else{
request.setAttribute("macId","N");
}
}
else
{
request.setAttribute("isRegistered","N");
}
request.setAttribute("roleId",roleId);
lStrPageName="biometricAttendance";
}


if("validateAttendance".equalsIgnoreCase(lStrActionVal))
{
String userId=(String) session.getAttribute("UserID");
String userType=request.getParameter("userType"); /*s=Self , o=Others*/
String attendanceType=request.getParameter("attendType");  /*0=login , 1=logout*/
String hospId=request.getParameter("hospId");
String otherUser=request.getParameter("otherUser");
BioMetricVo bioMetricVo=new BioMetricVo();
String status=null;




if(("o").equals(userType)){

bioMetricVo.setHospId(bioMetricForm.getHospId());
bioMetricVo.setOtherUser(bioMetricForm.getOtherUser());
userId=request.getParameter("otherUser");
//userId=bioMetricService.getUserIdByRole(bioMetricVo);

}

bioMetricVo.setUSERID(userId);
bioMetricVo.setATTENDANCEUSER(userType);
bioMetricVo.setATTENDTYPE(attendanceType);
bioMetricVo.setHospId(hospId);

status=bioMetricService.validateAttendance(bioMetricVo);

request.setAttribute("AjaxMessage",status);
return mapping.findForward("ajaxResult");

}

if("getOthersList".equalsIgnoreCase(lStrActionVal))
{
BioMetricVo bioMetricVo=new BioMetricVo();
String userId=null;
String userType=bioMetricForm.getOtherUser();
bioMetricVo.setHospId(bioMetricForm.getHospId());
bioMetricVo.setOtherUser(bioMetricForm.getOtherUser());

List<BioMetricVo> othersList=new ArrayList<BioMetricVo>();
othersList = bioMetricService.getNewOthersList(bioMetricVo);

List<String> otherListAjax = new ArrayList<String>();
for (BioMetricVo labelBean : othersList) {
if (labelBean.getID() != null
&& labelBean.getVALUE() != null)
if (otherListAjax != null && otherListAjax.size() > 0) {
otherListAjax.add("@" + labelBean.getID() + "~"
+ labelBean.getVALUE());
} else {
otherListAjax.add(labelBean.getID() + "~"
+ labelBean.getVALUE());
}
}

request.setAttribute("AjaxMessage",otherListAjax);
return mapping.findForward("ajaxResult");
}

if("checkOthers".equalsIgnoreCase(lStrActionVal))
{
BioMetricVo bioMetricVo=new BioMetricVo();
String userId=null;
String userType=bioMetricForm.getOtherUser();
bioMetricVo.setHospId(bioMetricForm.getHospId());
bioMetricVo.setOtherUser(bioMetricForm.getOtherUser());
userId=bioMetricService.getUserIdByRole(bioMetricVo);
String status=null;

if(userId==null){

if(userType!=null && config.getString("DC_Grp").equalsIgnoreCase(userType))
status="0";

else if(userType!=null && config.getString("DM_Grp").equalsIgnoreCase(userType))
status="1";

else if(userType!=null && config.getString("NTL_Grp").equalsIgnoreCase(userType))
status="2";
}



request.setAttribute("AjaxMessage",status);
return mapping.findForward("ajaxResult");
}

if("recordAttendance".equalsIgnoreCase(lStrActionVal))
{
String userId=(String) session.getAttribute("UserID");
String userType=bioMetricForm.getAttendanceUser(); /*s=Self , o=Others*/
String attendanceType=bioMetricForm.getAttendType();/*0=login , 1=logout*/
String fingerPrint=bioMetricForm.getFingerPrint();
BioMetricVo bioMetricVo=new BioMetricVo();
boolean isRecord=false;
boolean isMatched=false;
String ResultMsg=null;




if(("o").equals(userType)){

bioMetricVo.setHospId(bioMetricForm.getHospId());
bioMetricVo.setOtherUser(bioMetricForm.getOtherUser());
userId=request.getParameter("otherUserId");
//userId=bioMetricService.getUserIdByRole(bioMetricVo);

}
bioMetricVo.setATTENDANCEUSER(userType);
bioMetricVo.setATTENDTYPE(attendanceType);
bioMetricVo.setUSERID(userId);
bioMetricVo.setHospId(bioMetricForm.getHospId());
bioMetricVo.setHospMacId(bioMetricForm.getHospMacId());
bioMetricVo.setFINGERPRINT(fingerPrint);

if(fingerPrint!=null && !("").equalsIgnoreCase(fingerPrint))
{
isMatched=bioMetricService.matchFingerPrint(bioMetricVo);
}






if(isMatched)
{
ResultMsg=bioMetricService.biometricAttendance(bioMetricVo);
if(ResultMsg!=null && !("").equalsIgnoreCase(ResultMsg))
{
request.setAttribute("isRecord",true);
request.setAttribute("ResultMsg",ResultMsg);
}
}
else
{
request.setAttribute("isMatched",isMatched);
}

lStrPageName="biometricAttendance";
}

if("bioMetricReport".equalsIgnoreCase(lStrActionVal))
{
String scheme=userState;
String userId=(String) session.getAttribute("UserID");
String searchFlag=request.getParameter("searchFlag");
String csvFlag=request.getParameter("csvFlag");
stateType=request.getParameter("stateType");
distHdrId=config.getString("distLocHdr");
state=config.getString("telangana_hdr_id");
DistList=new ArrayList<LabelBean>();
List<BioMetricVo> dsgnLst=new ArrayList<BioMetricVo>();
BioMetricVo bioMetricVo=new BioMetricVo();

/*District List*/
	



/*To get depts having biometric option*/
List<BioMetricVo> deptLst=new ArrayList<BioMetricVo>();
deptLst=bioMetricService.getBiomDepts(scheme);
if(deptLst!=null)
bioMetricForm.setDeptLst(deptLst);

/*To get dsgns having biometric option*/
if(bioMetricForm.getDeptId()!=null && !("").equalsIgnoreCase(bioMetricForm.getDeptId()))
{

dsgnLst=bioMetricService.getBiomDsgns(scheme,bioMetricForm.getDeptId());
if(dsgnLst!=null)
bioMetricForm.setDsgnLst(dsgnLst);
}


/*if(bioMetricForm.getDeptId()!=null&& !("").equalsIgnoreCase(bioMetricForm.getDeptId()))
List<String> dsgnLst=new ArrayList<String>();
dsgnLst=bioMetricService.getBiomDsgnLst(scheme,deptId);*/

request.setAttribute("DistList",DistList);
request.setAttribute("deptLst",deptLst);
request.setAttribute("dsgnLst",dsgnLst);



/*pagination code*/
int showPage=1;
int rowsPerPage=10;
if(config.getString("BiometricRowsPerPage")!=null)
rowsPerPage=Integer.parseInt(config.getString("BiometricRowsPerPage"));
int startIndex=0;
int maxResults=0;

if(bioMetricForm.getShowPage()!=null && !("").equalsIgnoreCase(bioMetricForm.getShowPage()))
showPage=Integer.parseInt(bioMetricForm.getShowPage());
if(bioMetricForm.getRowsPerPage()!=null && !("").equalsIgnoreCase(bioMetricForm.getRowsPerPage()))
rowsPerPage=Integer.parseInt(bioMetricForm.getRowsPerPage());

startIndex=(showPage-1)*rowsPerPage;
maxResults=showPage*rowsPerPage;

bioMetricVo.setStartIndex(startIndex);
bioMetricVo.setMaxResults(maxResults);

/*end of pagination code*/



if(searchFlag!=null && ("Y").equalsIgnoreCase(searchFlag))
{
bioMetricVo.setDeptId(bioMetricForm.getDeptId());
bioMetricVo.setDsgnId(bioMetricForm.getDsgnId());
bioMetricVo.setDistId(bioMetricForm.getDistId());
bioMetricVo.setToDate(bioMetricForm.getToDate());
bioMetricVo.setFromDate(bioMetricForm.getFromDate());
if(csvFlag!=null && ("Y").equalsIgnoreCase(csvFlag))
{
bioMetricVo.setCsvFlag(csvFlag);
}
bioMetricForm.setDeptId(bioMetricForm.getDeptId());
bioMetricForm.setDsgnId(bioMetricForm.getDsgnId());
bioMetricForm.setDistId(bioMetricForm.getDistId());
bioMetricForm.setFromDate(bioMetricForm.getFromDate());
bioMetricForm.setToDate(bioMetricForm.getToDate());
bioMetricVo.setUserId(userId);

List<BioMetricVo> lstBiometricSearch=new ArrayList<BioMetricVo>();
BioMetricVo biometricReport=new BioMetricVo();
biometricReport=bioMetricService.getBiometricReport(bioMetricVo);
lstBiometricSearch=biometricReport.getAttendanceReport();
int count=(int) biometricReport.getCount();
int totalPages=0;

int rem=count%rowsPerPage;

if(rem==0)
{
totalPages=(count/rowsPerPage);
}
else
{
totalPages=(count/rowsPerPage)+1;
}
int endIndex=0;
if(totalPages==showPage)
{
endIndex=count;
}
else
{
endIndex= startIndex+rowsPerPage;
}


if(csvFlag!=null && ("Y").equalsIgnoreCase(csvFlag))
{


String lStrDirPath=config.getString("mapPath");
String lStrFileName=config.getString("biometricReportListTempFile")+config.getString("DOT_VALUE")+config.getString("OPR.CsvExtn");
 File lcsvfile = createFile(lStrDirPath,lStrFileName); 
 char separator = '^';
 StringBuilder line = new StringBuilder();

 line.append("Login Name");
 line.append(separator);
 line.append("Employee Name");
 line.append(separator);
 line.append("Designation");
 line.append(separator);
 line.append("Hospital Name");
 line.append(separator);
 line.append("MAC ID");
 line.append(separator);
 line.append("Logged in Days");
 line.append(separator);
 line.append("Not Logged in Days");
 line.append("\n");
 line.append("\n");
 
 for(BioMetricVo attLst: lstBiometricSearch)
 {
 line.append(attLst.getLOGINNAME());
 line.append(separator);
 line.append(attLst.getEmpName());
 line.append(separator);
 line.append(attLst.getDESIGNATION());
 line.append(separator);
 line.append(attLst.getHospitalName());
 line.append(separator);
 line.append(attLst.getHospMacId());
 line.append(separator);
 line.append(attLst.getLoginDays());
 line.append(separator);
 line.append(attLst.getNotLoginDays());
 line.append("\n");
 }

request.setAttribute("File", line.toString().getBytes());
response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
response.setCharacterEncoding("UTF-8");
return mapping.findForward("openFile");
 
}

bioMetricForm.setLstBiometricSearch(lstBiometricSearch);
bioMetricForm.setStartIndex((startIndex+1)+"");
bioMetricForm.setEndIndex((endIndex)+"");
bioMetricForm.setTotalRows(count+"");
request.setAttribute("fromDate",bioMetricForm.getFromDate());
request.setAttribute("toDate",bioMetricForm.getToDate());
request.setAttribute("liTotalPages",totalPages);
request.setAttribute("liPageNo",showPage);
request.setAttribute("searchflag","Y");

}
request.setAttribute("districtList", DistList);
request.setAttribute("distBifurcationDate", config.getString("distBifurcationDate"));
lStrPageName="biometricReport";

}

if(("getDsgns").equalsIgnoreCase(lStrActionVal))
{

String scheme=userState;
String deptId=request.getParameter("deptId");
List<String> dsgnLst=new ArrayList<String>();
dsgnLst=bioMetricService.getBiomDsgnLst(scheme,deptId);
if(dsgnLst!=null && dsgnLst.size()>0)
request.setAttribute("AjaxMessage",dsgnLst);
return mapping.findForward("ajaxResult");

}

if(("getAttendanceReport").equalsIgnoreCase(lStrActionVal))
{
List<BioMetricVo> attendanceReport=new ArrayList<BioMetricVo>();
BioMetricVo bioMetricVo=new BioMetricVo();

String userId=request.getParameter("userId");
String fromDt=request.getParameter("fromDate");
String toDate=request.getParameter("toDate");
String csvFlag=request.getParameter("csvFlag");

int loginDays=0;
int notLoginDays=0;

if(bioMetricForm.getLoginDays()!=null && !("").equalsIgnoreCase(bioMetricForm.getLoginDays()))
{
loginDays=Integer.parseInt(bioMetricForm.getLoginDays());
}
if(bioMetricForm.getNotLoginDays()!=null && !("").equalsIgnoreCase(bioMetricForm.getNotLoginDays()))
{
notLoginDays=Integer.parseInt(bioMetricForm.getNotLoginDays());
}
request.setAttribute("loginDays",loginDays);
request.setAttribute("notLoginDays",notLoginDays);
String errorMsg=null;
if(userId!=null && fromDt!=null && toDate!=null)
{
bioMetricVo.setUserId(userId);
bioMetricVo.setFromDate(fromDt);
bioMetricVo.setToDate(toDate);
attendanceReport=bioMetricService.getAttendanceReport(bioMetricVo);

if(attendanceReport!=null && attendanceReport.size()>0)
{
bioMetricForm.setLoginName(attendanceReport.get(0).getLOGINNAME());
bioMetricForm.setEmpName(attendanceReport.get(0).getEMPNAME());
bioMetricForm.setDesignation(attendanceReport.get(0).getDESIGNATION());
bioMetricForm.setHospitalName(attendanceReport.get(0).getHOSPNAME());

if(csvFlag!=null && ("Y").equalsIgnoreCase(csvFlag))
{


String lStrDirPath=config.getString("mapPath");
String lStrFileName=config.getString("biometricReportTempFile")+config.getString("DOT_VALUE")+config.getString("OPR.CsvExtn");
 File lcsvfile = createFile(lStrDirPath,lStrFileName); 
 char separator = '^';
 StringBuilder line = new StringBuilder();

 line.append("Employee Name");
 line.append(separator);
 line.append("Designation");
 line.append(separator);
 line.append("Hospital Name");
 line.append(separator);
 line.append("MAC ID");
 line.append(separator);
 line.append("Login Date");
 line.append(separator);
 line.append("Login Time");
 line.append(separator);
 line.append("Logout Time");
 line.append(separator);
 line.append("Total Hours Worked");
 line.append("\n");
 line.append("\n");
 
 for(BioMetricVo attLst: attendanceReport)
 {
 line.append(attLst.getEMPNAME());
 line.append(separator);
 line.append(attLst.getDESIGNATION());
 line.append(separator);
 line.append(attLst.getHOSPNAME());
 line.append(separator);
 line.append(attLst.getMACADDRESS());
 line.append(separator);
 line.append(attLst.getLOGINDATE());
 line.append(separator);
 if(attLst.getFIRSTLOGIN()!=null)
 line.append(attLst.getFIRSTLOGIN());
 else
 line.append("--NA--");
 line.append(separator);
 if(attLst.getLASTLOGOUT()!=null)
 line.append(attLst.getLASTLOGOUT());
 else
 line.append("--NA--");
 line.append(separator);
 line.append(attLst.getTOTALHOURSWORKED());
 line.append("\n");
 }

request.setAttribute("File", line.toString().getBytes());
response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
response.setCharacterEncoding("UTF-8");
return mapping.findForward("openFile");
 
}


}





bioMetricForm.setAttendanceRept(attendanceReport);
request.setAttribute("attendanceRept",attendanceReport);
request.setAttribute("userId",userId);
request.setAttribute("fromDate",fromDt);
request.setAttribute("toDate",toDate);
lStrPageName="biometricAttView";
}
else
{
errorMsg="There is an Error Processing your Request.Please try Again";
request.setAttribute("errorMsg",errorMsg);
lStrPageName="biometricReport";
}
}

return mapping.findForward(lStrPageName);

}

private File createFile(String lStrDirPath,String lStrFileName) 
{
/*//Making Directory  
File file = new File(lStrDirPath);
if (!file.exists()) 
{
file.mkdir();
}
File lfile = new File(lStrFileName);
if(!lfile.exists())
{
lfile.createNewFile();
}
else
{
lfile.delete();
lfile.createNewFile();
}*/
return null;

}

}
