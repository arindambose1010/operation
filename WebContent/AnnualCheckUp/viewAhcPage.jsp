<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.Iterator,java.util.List,com.ahct.patient.vo.PatientVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ include file="/common/include.jsp"%>

<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="EHF.Title.AHC" /></title>
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
	<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
	<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet"
		type="text/css" media="screen">
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<script src="js/jquery.msgBox.js" type="text/javascript"></script>
	<script src="js/patientscripts.js"></script>
	<%@ include file="/common/includeCalendar.jsp"%>
	<style>
	fieldset {
    border: 1px solid silver;
    margin: 0 2px;
    padding: 0.35em 0.625em 0.75em;
	}
	.info{
		color:red;
	}
	body{
	font-size:1.2em;
	}
	.form-control{font-size:1.1em !important;font-weight:normal}
	</style>
	<logic:equal name ="annualCheckUpForm" property="medcoFlag" value="Y">
	<script>
	var save='${save}';
	var msg='<bean:write name="annualCheckUpForm" property="msg"/>';
	if(msg!=null && msg!=''){
		
		alert(msg);
		if(save!=null && save=='N')
		parent.fn_annualRegisteredPatientsView();
	}
	
	function fn_loadProcessImage() {
		document.getElementById('processImagetable').style.display = "";
		setTimeout(
				function() {
					document.getElementById('processImagetable').style.display = "none";
				}, 3000)
	}
	
	
	
	function getSubFieldType(input)
	{
		
		var examinField="";
		var id=input.value;	
		
		var maxlength=5;
		var subTypeField=document.getElementById(id+"h").value;
		var prop = document.getElementById(id+"h").name;
		if(prop=="Height (in Cm)")
		{maxlength=10;}
	     else if(prop=="Weight (in Kg)")
		{maxlength=10;}
	    else if(prop=="BMI")
		maxlength=21;
				
		if(input.checked)
		{
		if(subTypeField=='T')
			{
			if(prop!='BP Lt.Arm mm/Hg'&& prop!='BP Rt.Arm mm/Hg'&& prop!='Temperature(C/F)')
				{examinField=examinField+"<input  type='text' name='"+id+"' id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'onchange='validateInnerNum(this)'";
				 if(prop=="BMI") 
					 examinField=examinField+" readOnly/> ";
				 else
					examinField=examinField+" /> ";
				}
			else if(prop=='BP Lt.Arm mm/Hg')
				examinField=examinField+"<input  type='text' style='width:37%' name='"+prop+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>&nbsp; / &nbsp;&nbsp;<input type='text' name='BP1' id='BP1' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			else if(prop=='BP Rt.Arm mm/Hg') 
				examinField=examinField+"<input  type='text' style='width:37%' name='"+prop+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> &nbsp;/&nbsp;&nbsp; <input type='text' name='BP2' id='BP2' style='width:38%'  maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			else if(prop=='Temperature(C/F)')
				{examinField=examinField+"<input type='radio' name='temp' id='temp' value='C' title='Centigrade' onclick='showTemp()'/>C<input type='radio'  name='temp' id='temp' value='F' title='ForeignHeat' onclick='showTemp()' />F&nbsp;&nbsp;&nbsp;<input type='text'  style='width:57%;' name='"+prop+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'  onchange='validateInnerNum(this)'/>";

				}
			}
		else if(subTypeField=='R')
			{
			
				examinField=examinField+"<input type='radio' name='"+prop+"' id='"+id+"' value='Y' title='Yes' onclick='getExaminSubCatValue(this)'/>Yes<input type='radio'  name='"+prop+"' id='"+id+"' value='N' title='No' onclick='getExaminSubCatValue(this)'/>No<br /><span id='"+prop+"Sub'></span>";
			}
		
		document.getElementById(id).innerHTML=examinField;
		}
		else
		{
		document.getElementById(id).innerHTML="";
		}
		//parent.fn_resizePage();
	}
	function getOtherText(input){
		var othrField="";
		var id=input.value;	
		var subTypeField=document.getElementById(id).value;
		var prop = document.getElementById(id).name;
		var surgName='History Of Past illness Surgeries text Field';
		if(input.checked){
		if(id=='PH11')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryOthr' id='pastHistryOthr' maxlength='100' title='History Of Past illness Other text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH8')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryCancers' id='pastHistryCancers' maxlength='100' title='History Of Past illness Cancers text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH12')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryEndDis' id='pastHistryEndDis' maxlength='100' title='History Of Past illness Endocrine Diseases text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH10')
		othrField=othrField+'<textarea class="form-control" name="pastHistrySurg" id="pastHistrySurg" title="History Of Past illness Surgeries text Field" style="width:12em;height:2em" onkeydown="return maxLengthPress(this,4000,event)" onkeypress="return validateSplKeyCodes(event)" onchange="validateSpaces(this,\''+surgName+'\')"/>';
		else if(id=='FH11')
			othrField=othrField+"<input type='text' class='form-control' name='familyHistoryOthr' id='familyHistoryOthr' maxlength='100' title='Family History Other Text Field' onchange='validateInnerAlphaSpace(this)'/>";
		document.getElementById(id).innerHTML=othrField;
		}
	else
		{
		document.getElementById(id).innerHTML="";
		}
	}
	function getSubListHistory(input,button)
	{	
		
		var parntCode=input.value;
		var prop = document.getElementById(parntCode+"p").name;
		if(input.checked)
		{
		   if (window.XMLHttpRequest)
		   {			   
		    xmlhttp=new XMLHttpRequest();
		   }
		   else if (window.ActiveXObject)
		   {		
		    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		   }
		   else
		   {
			   alert("Your browser does not support XMLHTTP!");
		   }   
		   url = "./annualCheckUpAction.do?actionFlag=getPersonalHistory&callType=Ajax&parntCode="+parntCode;    
		   xmlhttp.onreadystatechange=function()
		   {
			  
		       if(xmlhttp.readyState==4)
		       {
	          		var resultArray=xmlhttp.responseText;
	          		
	          		if(resultArray.trim()=="SessionExpired*"){
		    	    	alert("Session has been expired");
		    	    	parent.sessionExpireyClose();
		            }
		           else
		           {
		        	   
		           		resultArray = resultArray.replace("[","");
		           		
		           		resultArray = resultArray.replace("]","");
		           	
		           		var pHistoryList = resultArray.split(","); 
		           		if(pHistoryList.length>0)
		           		{
		        	   		var phistory="";
		               		for(var i = 0; i<pHistoryList.length;i++)
		               		{	
		                    	var arr=pHistoryList[i].split("~");                     
		                    	if(arr[1]!=null && arr[0]!=null)
		                    	{
		                        	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                        	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                        	
	                                if(button!='reset' && '${lstPerHis}'!=null && '${lstPerHis}'.indexOf(val2,0)!=-1)	                                         
			                    	{
	    		                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"' checked='checked'/>"+""+val1;
	                                 }
			                    	else
			                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"'/>"+""+val1;	
				             	}
		               		}
		               		document.getElementById(parntCode).innerHTML=phistory+"<span id='"+prop+"Td'></span>";
		            	}
		         	}
		       }			
		   }
			xmlhttp.open("Post",url,false);
			xmlhttp.send(null);
		}
		else
			{
			document.getElementById(parntCode).innerHTML="";
			}
			 //parent.fn_resizePage();
	}
</script>
</logic:equal>
	</head>
	<body onload="fn_loadProcessImage();">
	 <div id="processImagetable"
		style="top: 50%; width: 100%; position: absolute; z-index: 60;">
	<table border="0" align="left" width="50%">
		<tr>
			<td align="center">
			<div id="processImage" align="center"><img
				src="images/Progress.gif" width="100" height="100" border="0"
				tabindex="3"></img></div>
			</td>
		</tr>
	</table>
	</div> 
	<div id="middleFrame_content">
	<form action="/annualCheckUpAction.do" method="post"
		enctype="multipart/form-data"><br>
	<table style="margin: 0 auto; width: 100%">
		<tr>
			<td colspan="4">
			<div class="col-lg-6"> 
				<table class="tbheader table-responsive" width="100%">
			
				<tr style="margin: 0 auto; width: 100%">
					<td align="center" width="100%"><b>&nbsp;&nbsp;&nbsp;<fmt:message
						key="EHF.Title.AnnualHealthCheckup" /></b></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td>
		<table class="table table-responsive" width="100%">
				<tr>
					<td class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.HealthCardNo" /></b></td>
					<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write
						name="annualCheckUpForm" property="cardNo" /></b></td>
					<td>&nbsp;</td>
					<td class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.PatientNo" /></b></td>
					<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write
						name="annualCheckUpForm" property="patientNo" /></b></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			
			<br>
			<div class="col-lg-6"> 
				<table class="tbheader table-responsive" width="100%">
					<tr>
						<td align="center" width="100%"><b><fmt:message key="EHF.Title.PatientDetails" /></b></td>
					</tr>
				</table>
			</div>
		
			</td>
		</tr>
		<tr>
			<td>
			<table class="table table-responsive" style="margin: 0 auto; width: 100%">
				<tr>
					<td width="27%" valign="top">
					<fieldset ><legend
						class="legendStyle"><b><fmt:message
						key="EHF.FrameSet.PatientDetails" /></b></legend>
					<div  id="commonDtlsField" style="height:21em;">
					<table width="100%" height="200px" class="table-responsive" style="table-layout:fixed;word-wrap:break-word;">
						<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="annualCheckUpForm" property="cardIssueDt"/></b></td></tr> --%>
						<tr>
							<td class="labelheading1 tbcellCss" width="40%"><b><fmt:message
								key="EHF.Name" /></b></td>
							<td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="name" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Gender" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="gender" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.DateOfBirth" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="dateOfBirth" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Age" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="years" />Y <bean:write
								name="annualCheckUpForm" property="months" />M <bean:write
								name="annualCheckUpForm" property="days" />D</b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Relationship" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="relation" /></b></td>
						</tr>
						<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="annualCheckUpForm" property="caste"/></b></td></tr> --%>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Slab" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="slab" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Designation" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="designation" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.ContactNo" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="contactNo" /></b></td>
						</tr>
					</table>
					</div>
					</fieldset>
					</td>
					<td width="29%" valign="top">
					<fieldset ><legend
						class="legendStyle"><b><fmt:message
						key="EHF.CardAddress" /></b></legend>
					<div  id='cardAddressField' style="height:21em;">
					<table width="100%" height="200px" class="table-responsive" style="table-layout:fixed;word-wrap:break-word;">
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><b><fmt:message
								key="EHF.HouseNo" /></b></td>
							<td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="houseNo" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Street" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="street" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b>State</b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="state" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.District" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="district" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Mdl/Mcl" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="mandal" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Village" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="village" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Pin" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="pin" /></b></td>
						</tr>
					</table>
					
					</div>
					</fieldset>
					</td>
					<td  width="29%" valign="top">
					<fieldset height="20em;"><legend
						class="legendStyle"><b><fmt:message
						key="EHF.CommunicationAddress" /></b></legend>
					<div id="commAddressField" style="height:21em;">
					<table width="100%" 
						style="table-layout: fixed; word-wrap: break-word;">
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><b><fmt:message
								key="EHF.HouseNo" /></b></td>
							<td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commHouseNo" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Street" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commStreet" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b>State</b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commState" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.District" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commDistrict" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Mdl/Mcl" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commMandal" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Village" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commVillage" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Pin" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commPin" /></b></td>
						</tr>
					</table>
				
					</div>
					</fieldset>
					</td>
					<td width="15%" valign="top" >
					<fieldset  id='photoField'><legend
						class="legendStyle"><b><fmt:message key="EHF.Photo" /></b></legend>
					<table width="80%" height="80%" style="margin: auto auto">
						<tr>
							<td align="center"><logic:notEmpty name="annualCheckUpForm"
								property="photoUrl">
								<img id="patImage" src="common/showDocument.jsp" width="150"
									height="150" alt="NO DATA"
									onmouseover="resizePatImage('patImage','200','200')"
									onmouseout="resizePatImage('patImage','150','150')" />
							</logic:notEmpty> <logic:empty name="annualCheckUpForm" property="photoUrl">
								<img src="images/photonot.gif" width="150" height="150"
									alt="NO DATA" />
							</logic:empty></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			<table class="tbheader" style="margin: 0 auto; width: 100%">
				<tr>
					<td align="center"><b><fmt:message
						key="EHF.Title.CaseDetails" /></b></td>
				</tr>
			</table>
			<br>
			<table width="100%">
				<tr>
					<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.RegisteredHospital" /> </b></td>
					<td width="25%" class="tbcellBorder"><b><bean:write
						name="annualCheckUpForm" property="hospitalName" /></b></td>
					<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.DateAndTime" /> </b></td>
					<td width="25%" class="tbcellBorder"><b><bean:write
						name="annualCheckUpForm" property="dtTime" /></b></td>
				</tr>
			</table>
			<logic:equal name ="annualCheckUpForm" property="medcoFlag" value="Y">
			<table class="tbheader" style="margin: 0 auto; width: 100%">
				<tr>
					<td width="100%" align="center"><b><fmt:message
						key="EHF.Title.MedicalDetails" /></b></td>
				</tr>
			</table>
			
			<table width="100%" class="medicalDetailsTable" border="0">
				<tr>
					<td colspan="2" valign="top" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.HistoryOfPresentIllness" /></b> <font color="red">*</font></td>
					<td colspan="2" valign="top" rowspan="3" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.ExaminationFindings" /></b> <font color="red">*</font></legend>
					<table width="100%">
						<logic:iterate id="examinFnds" name="examinFndgsList">
							<tr>
								<td width="60%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="examinationFnd"
									styleId="examinationFnd" onclick="getSubFieldType(this)">
									<bean:write name="examinFnds" property="ID" />
								</html:multibox> <bean:write name="examinFnds" property="VALUE" /></td>
								<td id="<bean:write name="examinFnds" property='ID'/>"
									width="39%"></td>
								<td width="1%"><input type="hidden"
									name="<bean:write name='examinFnds' property='VALUE'/>"
									id="<bean:write name='examinFnds' property='ID'/>h"
									value="<bean:write name='examinFnds' property='LVL'/>" /></td>
							</tr>
						</logic:iterate>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2" class="tbcellBorder"><html:textarea
						name="annualCheckUpForm" property="presentHistory" 
						styleClass="form-control" styleId="presentHistory"
						style="resize:none;" 
						title="Enter History Of Present Illness"
						onkeydown="return maxLengthPress(this,4000,event)"
						onkeypress="return checkAlphaNumericCodes(event);"
						onchange="validateSpaces(this,'History Of Present Illness')" /></td>
				</tr>
				<tr>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.PersonalHistory" /></b> <font color="red">*</font></legend>
					<table width="100%">
						<logic:iterate id="phistory" name="personalHistoryList">
							<tr>
								<td width="43%"><html:multibox name="annualCheckUpForm"
									property="personalHistory" styleId="personalHistory"
									style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
									<bean:write name="phistory" property="ID" />
								</html:multibox> <bean:write name="phistory" property="VALUE" /></td>
								<td id="<bean:write name="phistory" property='ID'/>" width="59%"
									height="1em"></td>
								<td width="1%"><input type="hidden"
									name="<bean:write name='phistory' property='VALUE'/>"
									id="<bean:write name='phistory' property='ID'/>p" /></td>
							</tr>
						</logic:iterate>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<!--  <tr>
      <td  id="habitsTd" colspan="3">
      </td></tr> -->
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.PastHistory" /></b> </legend>
					<table width="100%">
						<logic:iterate id="psthistory" name="pastHistoryList"
							indexId="cnt">
							<tr>
								<td width="50%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="pastHistory"
									styleId="pastHistory" onclick="getOtherText(this)"
									title="History Of Past Illness">
									<bean:write name="psthistory" property="ID" />
								</html:multibox> <bean:write name="psthistory" property="VALUE" /></td>
								<td id="<bean:write name="psthistory" property='ID'/>"
									width="60%"></td>
							</tr>
						</logic:iterate>

					</table>
					</fieldset>
					</td>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.FamilyHistory" /></b> </legend>
					<table width="100%">
						<logic:iterate id="fhistory" name="familyHistoryList">
							<tr>
								<td width="40%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="familyHistory"
									styleId="familyHistory" onclick="getOtherText(this)">
									<bean:write name="fhistory" property="ID" />
								</html:multibox> <bean:write name="fhistory" property="VALUE" /></td>
								<td id="<bean:write name="fhistory" property='ID'/>" width="30%"></td>
							</tr>
						</logic:iterate>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			<table class="tbheader" style="margin: 0 auto; width: 100%">
				<tr>
					<td width="100%" align="center"><b>REPORTS DETAILS</b></td>
				</tr>
			</table>
			
			<table style="width: 100%" >
			<tr>
			<td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>CBP(Complete  Blood Picture Test)/Haemogram <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr><td class="labelheading1 tbcellCss" >Haemoglobin : <br/><span class="info">(Male: 14.0-17.0 , Female: 11.5-15.1)</span></td><td class="tbcellBorder" ><div class="input-group"><input type="text"  name="haemoglobin" id="haemoglobin" class="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Haemoglobin',this,'AHC Field');"/><span class="input-group-addon">g/dL</span></div></td>
					<td class="labelheading1 tbcellCss" >TLC(Total Leukocyte Count) : <br/><span class="info">(4000 – 11000 cells/cubic millimeter of blood)</span></td><td class="tbcellBorder" > <div class="input-group"><html:text name ="annualCheckUpForm" property="tlc" styleId="tlc" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('TLC(Total Leukocyte Count)',this,'AHC Field');"/><span class="input-group-addon">/cmm</span></div></td></tr>
					<tr><td colspan="4">
						<table style="width: 100%" class="tbcellBorder"><tr  ><td class="tbheader" width="100%" colspan="4" align="center">DLC(Differential Leukocyte Count)</td></tr>
							<tr><td class="labelheading1 tbcellCss">Polymorphs/Neutrophils : <br/><span class="info">(50 – 70%)</span></td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="polymorphs" styleId="polymorphs" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Polymorphs/Neutrophils',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td>
							<td class="labelheading1 tbcellCss">Lymphocytes : <br/><span class="info">(20 – 40%)</span></td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="lymphocytes" styleId="lymphocytes" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Lymphocytes',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td></tr>
							<tr><td class="labelheading1 tbcellCss">Eosinophils : <br/><span class="info">(1 – 4%)</span></td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="eosinophils" styleId="eosinophils" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Eosinophils',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td>
							<td class="labelheading1 tbcellCss">Basophils : <br/><span class="info">(0.5 – 1%)</span></td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="basophils" styleId="basophils" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Basophils',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td></tr>
							<tr><td class="labelheading1 tbcellCss">Monocytes : <br/><span class="info">(1 – 4%)</span></td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="monocytes" styleId="monocytes" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Monocytes',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td></tr>
						</table>
					</td></tr>
					<tr><td colspan="4">
						<table style="width: 100%" class="tbcellBorder"><tr  ><td class="tbheader" width="100%" colspan="4" align="center">Peripheral Smear</td></tr>
							<tr><td class="labelheading1 tbcellCss">RBC : </td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="rbc" styleId="rbc" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('RBC',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td>
							<td class="labelheading1 tbcellCss">WBC : </td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="wbc" styleId="wbc" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('WBC',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td></tr>
							<tr><td class="labelheading1 tbcellCss">Platelets : </td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="platelets" styleId="platelets" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Platelets',this,'AHC Field');"/><span class="input-group-addon">%</span></div></td></tr>
						</table>
					</td></tr>
					<tr><td colspan="1" class="labelheading1 tbcellCss">Blood Group : </td><td colspan="1" class="tbcellBorder">
					<select name ="bloodGroup"  id="bloodGroup" class="form-control" >
					<option value="">--Select--</option>
					<option value="O+">O+</option>
					<option value="O-">O-</option>
					<option value="A+">A+</option>
					<option value="A-">A-</option>
					<option value="B+">B+</option>
					<option value="B-">B-</option>
					<option value="AB+">AB+</option>
					<option value="AB-">AB-</option>
					</select>
					</td>
					<td></td><td></td>
					</tr>
				</table>
			</fieldset>
			</td></tr>
			<tr><td colspan="1" class="labelheading1 tbcellCss"><b>ESR(Erythrocyte Sedimentation Rate) : </b></td><td colspan="1" class="tbcellBorder"><html:text name ="annualCheckUpForm" property="esr" styleId="esr" styleClass="form-control" onblur="javascript:validateAlphaNum('ESR(Erythrocyte Sedimentation Rate)',this,'AHC Field');"/></td><td></td><td></td></tr>
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>Blood Sugar  <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr><td class="labelheading1 tbcellCss">Fasting : <br/><span class="info">(70-100mg %)</span></td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="fastingSugar" styleId="fastingSugar" styleClass="form-control" maxlength="3" onblur="javascript:validateAlphaNum('Fasting Sugar',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					<td class="labelheading1 tbcellCss">Post Prandial : <br/><span class="info">(80-110mg %)</span></td><td class="tbcellBorder"><div class="input-group"><html:text name ="annualCheckUpForm" property="postPrandialSugar" styleId="postPrandialSugar" styleClass="form-control" maxlength="3" onblur="javascript:validateAlphaNum('Post Prandial Sugar',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td></tr>
				</table>
			</fieldset>
			</td></tr>
			
			<tr><td colspan="1" class="labelheading1 tbcellCss"><b>Glycosylated Haemoglobin : <font color="red">*</font></b></td><td colspan="1" class="tbcellBorder"><html:text name ="annualCheckUpForm" property="glycosylatedHaemoglobin" styleId="glycosylatedHaemoglobin" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Glycosylated Haemoglobin',this,'AHC Field');"/></td>
			<td colspan="1" class="labelheading1 tbcellCss"><b>HbsAg(surface antigen of Hepatitis B virus) <font color="red">*</font>: </b></td><td colspan="1" class="tbcellBorder"><html:text name ="annualCheckUpForm" property="hbsAg" styleId="hbsAg" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('HbsAg',this,'AHC Field');"/></td></tr>
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>Lipid Profile <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr><td class="labelheading1 tbcellCss" >Total Cholesterol : <br/><span class="info">(100-200mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="totalCholesterol" styleId="totalCholesterol" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Total Cholesterol',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					<td class="labelheading1 tbcellCss" > HDL Cholesterol : <br/><span class="info">(35-155mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="hdlCholesterol" styleId="hdlCholesterol" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('HDL Cholesterol',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					</tr>
					<tr><td class="labelheading1 tbcellCss" >LDL Cholesterol : <br/><span class="info">(Upto 130 mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="ldlCholesterol" styleId="ldlCholesterol" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('LDL Cholesterol',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					<td class="labelheading1 tbcellCss" >  VLDL Cholesterol : <br/><span class="info">(10 – 30mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="vldlCholesterol" styleId="vldlCholesterol" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('VLDL Cholesterol',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					</tr>
					<tr>
					<td class="labelheading1 tbcellCss" >  Triglycerides : <br/><span class="info">(50-150mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="triglycerides" styleId="triglycerides" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Triglycerides',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					<td></td></tr>
				</table>
			</fieldset>
			</td></tr>
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>Serum Urea(Kidney Function test) <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr><td class="labelheading1 tbcellCss" >Blood Urea : <br/><br/><span class="info">(7-18mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="bloodUrea" styleId="bloodUrea" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Blood Urea',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					<td class="labelheading1 tbcellCss" > S.Creatinine : <br/><span class="info">(0.6-1.2mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="sCreatinine" styleId="sCreatinine" styleClass="form-control" maxlength="10"  onblur="javascript:validateAlphaNum('S.Creatinine',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					</tr>
					<tr><td class="labelheading1 tbcellCss" >S.Uric Acid : <br/><span class="info">(2-7mg %)</span></td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="sUricAcid" styleId="sUricAcid" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('S.Uric Acid',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					<td></td></tr>
					
				</table>
			</fieldset>
			</td></tr>
			<tr><td class="labelheading1 tbcellCss"><b>Serum Electrolytes <font color="red">*</font>: </b></td><td class="tbcellBorder"><html:text name ="annualCheckUpForm" property="serumElectrolytes" styleId="serumElectrolytes" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Serum Electrolytes',this,'AHC Field');"/></td>
			<td class="labelheading1 tbcellCss"><b>Serum Creatinine : <font color="red">*</font></b></td><td class="tbcellBorder"><html:text name ="annualCheckUpForm" property="serumCreatinine" styleId="serumCreatinine" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Serum Creatinine',this,'AHC Field');"/></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b>SGOT(Serum Glutamic Oxaloacetic Transaminase) <font color="red">*</font>: </b></td><td class="tbcellBorder"><html:text name ="annualCheckUpForm" property="sgot" styleId="sgot" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('SGOT(Serum Glutamic Oxaloacetic Transaminase)',this,'AHC Field');"/></td>
			<td class="labelheading1 tbcellCss"><b>SGPT(Serum Glutamic Pyruvic Transaminase) : <font color="red">*</font></b></td><td class="tbcellBorder"><html:text name ="annualCheckUpForm" property="sgpt" styleId="sgpt" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('SGPT(Serum Glutamic Pyruvic Transaminase)',this,'AHC Field');"/></td></tr>
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>Liver Function Test <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr><td class="labelheading1 tbcellCss" >S.Bilirubin(total) <br/><span class="info">(Upto 1.0mg %)</span>: </td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="sTotalBilirubin" styleId="sTotalBilirubin" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('S. Bilirubin(Total)',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					<td class="labelheading1 tbcellCss" > S.Bilirubin(direct) <br/><span class="info">(Upto 1.0mg %-)</span>: </td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="sDirectBilirubin" styleId="sDirectBilirubin" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('S. Bilirubin(Direct)',this,'AHC Field');"/><span class="input-group-addon">mg %</span></div></td>
					</tr>
					<tr><td class="labelheading1 tbcellCss" >S.G.O.T <br/><span class="info">(1 – 21units/L)</span>: </td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="liverSgot" styleId="liverSgot" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('S.G.O.T',this,'AHC Field');"/><span class="input-group-addon">units/L</span></div>units/L</td>
					<td class="labelheading1 tbcellCss" > S.G.P.T <br/><span class="info">(7 – 27units/L)</span>: </td><td class="tbcellBorder" ><div class="input-group"><html:text name ="annualCheckUpForm" property="liverSgpt" styleId="liverSgpt" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('S.G.P.T',this,'AHC Field');"/><span class="input-group-addon">units/L</span></div>units/L</td>
					</tr>
				</table>
			</fieldset>
			</td></tr>
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>Complete Urine Examination <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr><td class="labelheading1 tbcellCss" >Color : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="urineColor" styleId="urineColor" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Urine color',this,'AHC Field');"/></td>
					<td class="labelheading1 tbcellCss" > Albumin : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="urineAlbumin" styleId="urineAlbumin" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Urine albumin',this,'AHC Field');"/></td>
					</tr>
					<tr><td class="labelheading1 tbcellCss" >Sugar : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="urineSugar" styleId="urineSugar" styleClass="form-control" maxlength="10" onblur="javascript:validateAlphaNum('Urine Sugar',this,'AHC Field');"/></td>
					<td class="labelheading1 tbcellCss" > Microscopic Examination : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="urineMicroscopicExam" styleId="urineMicroscopicExam" styleClass="form-control" maxlength="10" /></td>
					</tr>
				</table>
			</fieldset>
			</td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>Ultrasound Abdomen <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="abdomenUltrasound" styleId="abdomenUltrasound" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>Chest X-ray PA View <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="chestXrayPAView" styleId="chestXrayPAView" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)" /></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>Pulmonary Function Test <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="pulmonaryFunction" styleId="pulmonaryFunction" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>ECG(Electrocardiography) <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="ecg" styleId="ecg" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>2D-Echo <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" property="twodEcho" style="resize:none;" styleId="twodEcho" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>TMT- Treadmill Test : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="treadmillTest" styleId="treadmillTest" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>Retinopathy <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="retinopathy" styleId="retinopathy" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>Fundoscopy <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="fundoscopy" styleId="fundoscopy" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			<tr><td class="labelheading1 tbcellCss" colspan="1"><b>Audiometry <font color="red">*</font> : </b></td><td colspan="3" class="tbcellBorder"><html:textarea name ="annualCheckUpForm" style="resize:none;" property="audiometry" styleId="audiometry" styleClass="form-control" onkeydown="return maxLengthPress(this,4000,event)"/></td></tr>
			
			<logic:equal name="annualCheckUpForm" property="gender" value="F" >
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>Gynecology Examination <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr>
					<td class="tbcellCss" rowspan="3" >
						<fieldset>
							<legend class="legendStyle"><b>Pelvic Examination</b> </legend>
							<table style="width: 100%">
							<tr><td class="labelheading1 tbcellCss" >Local Examination : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="pelvicLocalExam" styleId="pelvicLocalExam" styleClass="form-control" maxlength="10"/></td></tr>
							<tr><td class="labelheading1 tbcellCss" > Per Vaginum (P/V)    : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="perVaginum" styleId="perVaginum" styleClass="form-control" maxlength="10"/></td></tr>
							<tr><td class="labelheading1 tbcellCss" >Per Speculum : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="perSpeculum" styleId="perSpeculum" styleClass="form-control" maxlength="10"/></td></tr>
							</table>
						</fieldset>
						</td>
					<td class="labelheading1 tbcellCss" >Surgical Examination : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="surgicalExam" styleId="surgicalExam" styleClass="form-control" maxlength="10"/></td></tr>
					<tr><td class="labelheading1 tbcellCss" > Breast Examination : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="breastExam" styleId="breastExam" styleClass="form-control" maxlength="10"/></td>
					</tr>
					<tr><td class="labelheading1 tbcellCss" >PAP Smear Test : </td><td class="tbcellBorder" ><html:text name ="annualCheckUpForm" property="papSmear" styleId="papSmear" styleClass="form-control" maxlength="10"/></td>
					</tr>
				</table>
			</fieldset>
			</td></tr>
			</logic:equal>
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>MEDICAL REPORT OF THE OFFICER <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr>
					<td class="labelheading1 tbcellCss" >Haemoglobin Level of the officer : </td><td class="tbcellBorder" ><input type ="radio" name="haemoglobinReport" id="haemoglobinReport" value="HR1.1"> Normal <input type ="radio" name="haemoglobinReport" id="haemoglobinReport" value="HR1.2"> Low</td></tr>
					<tr><td class="labelheading1 tbcellCss" > Blood sugar level : </td><td class="tbcellBorder" ><input type ="radio" name="bloodSugarReport" id="bloodSugarReport" value="HR2.1"> Satisfactory <input type ="radio" name="bloodSugarReport" id="bloodSugarReport" value="HR2.2"> Normal <input type ="radio" name="bloodSugarReport" id="bloodSugarReport" value="HR2.3"> High <input type ="radio" name="bloodSugarReport" id="bloodSugarReport" value="HR2.4"> Low </td></tr>
					<tr><td class="labelheading1 tbcellCss" >Cholesterol level of the officer : </td><td class="tbcellBorder" ><input type ="radio" name="cholesterolReport" id="cholesterolReport" value="HR3.1"> Normal <input type ="radio" name="cholesterolReport" id="cholesterolReport" value="HR3.2"> High <input type ="radio" name="cholesterolReport" id="cholesterolReport" value="HR3.3"> Low </td>
					<tr><td class="labelheading1 tbcellCss" > Liver Functioning : </td><td class="tbcellBorder" ><input type ="radio" name="liverFunctionReport" id="liverFunctionReport" value="HR4.1"> Satisfactory <input type ="radio" name="liverFunctionReport" id="liverFunctionReport" value="HR4.2"> Normal <input type ="radio" name="liverFunctionReport" id="liverFunctionReport" value="RH4.3"> High <input type ="radio" name="liverFunctionReport" id="liverFunctionReport" value="HR4.4"> Low </td></tr>
					<tr><td class="labelheading1 tbcellCss" >Kidney Status : </td><td class="tbcellBorder" ><input type ="radio" name="kidneyReport" id="kidneyReport" value="HR5.1"> Normal <input type ="radio" name="kidneyReport" id="kidneyReport" value="HR5.2"> Both/one kidney not functioning optimally </td>
					<tr><td class="labelheading1 tbcellCss" >Cardiac Status : </td><td class="tbcellBorder" ><input type ="radio" name="cardiacReport" id="cardiacReport" value="HR6.1"> Normal <input type ="radio" name="cardiacReport" id="cardiacReport" value="HR6.2"> Enlarged <input type ="radio" name="cardiacReport" id="cardiacReport" value="HR6.3"> Blocked  <input type ="radio" name="cardiacReport" id="cardiacReport" value="HR6.4"> Not Normal</td>
					</tr>
				</table>
			</fieldset>
			</td></tr>
			<tr><td colspan="4">
			<fieldset>
			<legend class="legendStyle"><b>SUMMARY OF MEDICAL REPORT <font color="red">*</font></b> </legend>
				<table style="width: 100%">
					<tr>
					<td class="labelheading1 tbcellCss" width="40%">Overall Health of the officer : </td><td class="tbcellBorder" ><html:textarea  name="annualCheckUpForm" property="overallHealthRemarks" styleId="overallHealthRemarks" styleClass="form-control" style="resize:none"></html:textarea></td></tr>
					<tr><td class="labelheading1 tbcellCss" width="40%"> Any other remarks based on the health medical checkup of the officer : </td><td class="tbcellBorder" > <html:textarea  name="annualCheckUpForm" property="summary" styleId="summary" styleClass="form-control" style="resize:none"></html:textarea></td></tr>
					<tr><td class="labelheading1 tbcellCss" width="40%">Health Profile Grading : </td><td class="tbcellBorder" ><html:select name ="annualCheckUpForm" property="healthGrade" styleId="healthGrade" styleClass="form-control">
					<html:option value="">--Select--</html:option>
					<html:option value="A">A</html:option>
					<html:option value="B">B</html:option>
					<html:option value="C">C</html:option>
					<html:option value="D">D</html:option>
					<html:option value="E">E</html:option>
					</html:select></td>
					</tr>
				</table>
			</fieldset>
			</td></tr>
			<tr><td colspan="4">
				<table style="width:100%">
				<tr>
					<td style="height: 1em; font-size:small;" nowrap="nowrap" width=20%>
						<font color="red"><fmt:message key="EHF.MandatoryFields"/> <br /></font>
					</td>
		
<td align="center" colspan="3"> <button class="btn but"  type="button" id="Submit" onclick="javascript:fn_savePatientDetails('submit')">Submit</button>
 <button class="btn but"  type="button" id="Save" onclick="javascript:fn_saveDetails()">Save</button>
 <button class="btn but"  type="button" id="Reset" onclick="javascript:fn_resetAll()">Reset</button></td>
<td width="20%"></td>
</tr>
</table>
</td></tr>
</table>
			</logic:equal>
			</table>
	
	<html:hidden name="annualCheckUpForm" property="patientNo" styleId="patientNo"/>
	<html:hidden name="annualCheckUpForm" property="examFndsVal" styleId="examFndsVal"/>
	<html:hidden name="annualCheckUpForm" property="personalHistVal" styleId="personalHistVal"/>
	<html:hidden name="annualCheckUpForm" property="personalHist" styleId="personalHist"/>

	</form>
	</div>
	</body>
	<logic:equal name ="annualCheckUpForm" property="medcoFlag" value="Y">
	<script>
	function resetMedicalDetails()
	{
		
	
		document.getElementById("presentHistory").value="";
		
		var pastHistory=document.forms[0].pastHistory;
		for(var i=0;i<pastHistory.length;i++)
		{
			if(pastHistory[i].checked)
			{
	        pastHistory[i].checked=false;
	        getOtherText(pastHistory[i]);
			}
		}
		var personalHistory=document.forms[0].personalHistory;
		for(var i=0;i<personalHistory.length;i++)
		{
			if(personalHistory[i].checked)
			{
	        personalHistory[i].checked=true;
	        getSubListHistory(personalHistory[i],'reset');
			}
		}
		var examinationFnd=document.forms[0].examinationFnd;
		for(var i=0;i<examinationFnd.length;i++)
		{
			if(examinationFnd[i].checked)
			{
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]);
			}
		}
		var familyHistory=document.forms[0].familyHistory;
		for(var i=0;i<familyHistory.length;i++)
		{
			if(familyHistory[i].checked)
			{
				familyHistory[i].checked=false;
				getOtherText(familyHistory[i]);
			}
		}
		
		}
	function fn_resetAll(){
		var msg='Do you want to reset?';
		 if(confirm(msg)==true)
			 resetMedicalDetails();
	}
	function fn_saveDetails(){
		var patId=document.getElementById("patientNo").value;
		
		//Mandatory Check validation For Personal History and its sublist
		var personalHistory=document.forms[0].personalHistory;
		var personalCount=0;
		var personalSubCount=0;
		var personalHistVal="";
		var genTestsCount=0;
	
		var lErrorMsg='';
		var lField='';
		
		for(var i=0;i<personalHistory.length;i++)
		{
			if(personalHistory[i].checked)
			{
			var personalHistValue=personalHistory[i].value;
			var personalHistSubList=document.forms[0].elements[personalHistValue];
			for(var j=0;j<personalHistSubList.length;j++)
			{
				if(personalHistSubList[j].checked)	
				{
				personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
				personalSubCount++;
				if(personalHistSubList[j].value=='PR5.1'){
					if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
					{
						
					}
					else if(document.forms[0].AllMed[0].checked){
						if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
							{
							personalHistVal = personalHistVal+",AllMed$AllMedYes";					
							}
							else {
							personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
							}
							
						}
					else
					{
					var AllMedList=document.forms[0].AllMed;
					for(var z=0;z<AllMedList.length;z++)
						{
						if(AllMedList[z].checked)
							{
							personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
							}
						}
					}
					if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
					{
						
					}
					else if(document.forms[0].AllSub[0].checked){
						if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
							{
							personalHistVal = personalHistVal+",AllSub$AllSubYes";				
							}
							else {
							personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
							}
							
						}
					else
					{
					var AllSubList=document.forms[0].AllSub;
					for(var z=0;z<AllSubList.length;z++)
						{
						if(AllSubList[z].checked)
							{
							personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
							}
						}
					}
				}
				
				if(personalHistSubList[j].value=='PR6.1')
					{
					if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
						{
							 
						}
					else
						{
						var alcoholSubList=document.forms[0].alcohol;
						for(var z=0;z<alcoholSubList.length;z++)
							{
							if(alcoholSubList[z].checked)
								{
								personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
								}
							}
						}
					if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
					{
						
					}
					else if(document.forms[0].tobacco[2].checked)
						{
						if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
							{
							 
							}
						else
							{
							personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
							}
						if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
						{
						
						}
						else
							{
							personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
							}
					}
					else
					{
						var tobaccoSubList=document.forms[0].tobacco;
						for(var z=0;z<tobaccoSubList.length;z++)
							{
							if(tobaccoSubList[z].checked)
								{
								personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
								}
							}
					}
							
					
						var drugUseSubList=document.forms[0].drugUse;
						for(var z=0;z<drugUseSubList.length;z++)
							{
							if(drugUseSubList[z].checked)
								{
								personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
								}
							}
					
					
						var betelNutSubList=document.forms[0].betelNut;
						for(var z=0;z<betelNutSubList.length;z++)
							{
							if(betelNutSubList[z].checked)
								{
								personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
								}
							}
					  var betelLeafSubList=document.forms[0].betelLeaf;
						for(var z=0;z<betelLeafSubList.length;z++)
							{
							if(betelLeafSubList[z].checked)
								{
								personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
								}
							}			
				  }
				}
			}
			
			if(personalSubCount==0)
				{
				
				}
			personalSubCount=0;
			personalHistVal = personalHistVal+"#";
		     }
			else
				{
				personalCount++;
				}
		}

		//Mandatory Check validation For Examination Findings and its sublist
		var examinFnds=document.forms[0].examinationFnd;
		var examinCount=0;
		var examinSubCount=0;
		var examFndsVal="";
		for(var i=0;i<examinFnds.length;i++)
		{
			if(examinFnds[i].checked)
			{
				//examinCount++;
				var examinFndsValue=examinFnds[i].value;		
				var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
				var subType=document.forms[0].elements[examinFndsValue].type;
				
				if(examinFndsValue=='GE11'){			
						var tempType= '';
						if(document.forms[0].temp[0].checked==true) tempType='C';
						if(document.forms[0].temp[1].checked==true) tempType='F';
						examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
						examFndsVal = examFndsVal+"#";					
					}
				else if(examinFndsValue=='GE14'){
					    examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
						examFndsVal = examFndsVal+"#";
					}
				else if(examinFndsValue=='GE15'){
						examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
					}
				else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){				
				if(subType=="text" )
				{
	            examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
				}
				else
				{
					var examinFndsSubList=document.forms[0].elements[examinFndsValue];
					
					
					for(var j=0;j<examinFndsSubList.length;j++)
					{
					if(examinFndsSubList[j].checked)	
						{
						examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
						examinSubCount++; 
						if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
							{
							var d;
								var dehydSubList=document.forms[0].dehydrationY;
								 for(d=0;d<dehydSubList.length;d++)
									{
									if(dehydSubList[d].checked)
										{
										examFndsVal = examFndsVal+dehydSubList[d].value;
										}
									}
							}
						}
					}
					examinSubCount=0;
				}
				examFndsVal = examFndsVal+"#";
			}
			}
			else
				{
				examinCount++;
				}
			}
			
		for (var x=0;x<pastHistory.length;x++)
		{
			if (pastHistory[x].checked)
			{
				if(pastHistory[x].value=="PH10")
				{
					if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
					{
						var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
						document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
					}
				}
			}
		}
		
		if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
		{
			//var fr=partial(focusBox,document.getElementById(lField));
			var fr=partial(focusFieldView,lField);
			alert(lErrorMsg);partial(focusFieldView,lField);
		    return;
		 }
		else
		{
		var saveFlag="Yes";
		var checkType="Save";
		var res=confirm('Do you want to Save?');
		
		if(res==true){
			registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType);
		}
		}
		}
	function fn_savePatientDetails(checkType)
	{
		var patId=document.getElementById("patientNo").value;
		var lErrorMsg='';
		var lField='';
		var genTestsCount=0;
		var ipTestsCount=0;
		var updTestsCount=0;
		
	//Mandatory check for Present History
	if(document.forms[0].presentHistory.value=='' || document.forms[0].presentHistory.value==null){
		if(lErrorMsg==''){
	             lErrorMsg=lErrorMsg+"Enter History Of Present Illness\n ";
			}
	        if(lField=='')
	        lField='presentHistory';   
	}
	//Mandatory check for History Of Past Illness
	var pastHistory=document.forms[0].pastHistory;
	var pastHistoryCnt=0;
	for (var x=0;x<pastHistory.length;x++)
	{
	  if (pastHistory[x].checked)
	  {
	   pastHistoryCnt++;
	   if(pastHistory[x].value=="PH11")
		   {
		   if(document.getElementById("pastHistryOthr").value=='' || document.getElementById("pastHistryOthr").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter Other History Of Past Illness value ";
			      }
			        if(lField=='')
			        lField='pastHistryOthr'; 
			   }
		   }
		   if(pastHistory[x].value=="PH8")
		   {
		   if(document.getElementById("pastHistryCancers").value=='' || document.getElementById("pastHistryCancers").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Cancers value ";
			      }
			        if(lField=='')
			        lField='pastHistryCancers'; 
			   }
		   }
		   if(pastHistory[x].value=="PH12")
		   {
		   if(document.getElementById("pastHistryEndDis").value=='' || document.getElementById("pastHistryEndDis").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Endocrine Diseases value ";
			      }
			        if(lField=='')
			        lField='pastHistryEndDis'; 
			   }
		   }
		   if(pastHistory[x].value=="PH10")
		   {
		   if(document.getElementById("pastHistrySurg").value=='' || document.getElementById("pastHistrySurg").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Surgeries value ";
			      }
			        if(lField=='')
			        lField='pastHistrySurg'; 
			   }
			 if(checkType=='SaveDTRS')
				{
				if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
					{
						var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
						document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
					}
				}
		   }
	  }
	}
	//Mandatory Check validation For Personal History and its sublist
	var personalHistory=document.forms[0].personalHistory;
	var personalCount=0;
	var personalSubCount=0;
	var personalHistVal="";

	for(var i=0;i<personalHistory.length;i++)
	{
		if(personalHistory[i].checked)
		{
		//personalCount++;
		var personalHistValue=personalHistory[i].value;
		var personalHistSubList=document.forms[0].elements[personalHistValue];
		for(var j=0;j<personalHistSubList.length;j++)
		{
			if(personalHistSubList[j].checked)	
			{
			personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
			personalSubCount++;
			if(personalHistSubList[j].value=='PR5.1'){
				if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Medicine) Options ";
			     	 }
			        if(lField=='')
			        lField='AllMed';   
				}
				else if(document.forms[0].AllMed[0].checked){
					if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
						{
							if(lErrorMsg==''){
						        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Medicine) Specify Reason ";
						      }
							 if(lField=='') lField='AllMedRemrk'; 						
						}
						else {
						personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
						}
						
					}
				else
				{
				var AllMedList=document.forms[0].AllMed;
				for(var z=0;z<AllMedList.length;z++)
					{
					if(AllMedList[z].checked)
						{
						personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
						}
					}
				}
				if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Substance other than medicine) Options ";
			     	 }
			        if(lField=='')
			        lField='AllSub';   
				}
				else if(document.forms[0].AllSub[0].checked){
					if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
						{
							if(lErrorMsg==''){
						        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Substance other than Medicine) Specify Reason ";
						      }
							 if(lField=='') lField='AllSubRemrk'; 						
						}
						else {
						personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
						}
						
					}
				else
				{
				var AllSubList=document.forms[0].AllSub;
				for(var z=0;z<AllSubList.length;z++)
					{
					if(AllSubList[z].checked)
						{
						personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
						}
					}
				}
			}
			
			if(personalHistSubList[j].value=='PR6.1')
				{
				if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
					{
						if(lErrorMsg==''){
				         lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Alcohol options ";
				      	}
				        if(lField=='')
				        lField='alcohol';   
					}
				else
					{
					var alcoholSubList=document.forms[0].alcohol;
					for(var z=0;z<alcoholSubList.length;z++)
						{
						if(alcoholSubList[z].checked)
							{
							personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
							}
						}
					}
				if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Tobacco Options ";
			     	 }
			        if(lField=='')
			        lField='tobacco';   
				}
				else if(document.forms[0].tobacco[2].checked)
					{
					if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
						{
						if(lErrorMsg==''){
					        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking PackNo ";
					      }
					     if(lField=='')
					        lField='packNo';   
						}
					else
						{
						personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
						}
					if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
					{
					if(lErrorMsg==''){
				        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking Years ";
				      }
				      if(lField=='')
				        lField='smokeYears';   
					}
					else
						{
						personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
						}
				}
				else
				{
					var tobaccoSubList=document.forms[0].tobacco;
					for(var z=0;z<tobaccoSubList.length;z++)
						{
						if(tobaccoSubList[z].checked)
							{
							personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
							}
						}
				}
						
				if (!document.forms[0].drugUse[0].checked && !document.forms[0].drugUse[1].checked)
				{
					if(lErrorMsg==''){
			       lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Drug Use Options ";
			     	 }
			        if(lField=='')
			        lField='drugUse';   
				}
				else
				{
					var drugUseSubList=document.forms[0].drugUse;
					for(var z=0;z<drugUseSubList.length;z++)
						{
						if(drugUseSubList[z].checked)
							{
							personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
							}
						}
				}
				if (!document.forms[0].betelNut[0].checked && !document.forms[0].betelNut[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Nut Options ";
			     	 }
			        if(lField=='')
			        lField='betelNut';   
				}
				else
				{
					var betelNutSubList=document.forms[0].betelNut;
					for(var z=0;z<betelNutSubList.length;z++)
						{
						if(betelNutSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
							}
						}
				}
				if (!document.forms[0].betelLeaf[0].checked && !document.forms[0].betelLeaf[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Leaf Options ";
			      	}
			        if(lField=='')
			        lField='betelLeaf';   
				}
				else
				{
					var betelLeafSubList=document.forms[0].betelLeaf;
					for(var z=0;z<betelLeafSubList.length;z++)
						{
						if(betelLeafSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
							}
						}
				}
			  }
			}
		}
		
		if(personalSubCount==0)
			{
			if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Select Personal History "+personalHistSubList[0].name+" Options ";
					}
	        if(lField=='')
	        lField=personalHistValue;  
			}
		personalSubCount=0;
		personalHistVal = personalHistVal+"#";
	     }
		else
			{
			personalCount++;
			}
	}
	if(personalCount>0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select All Personal History Options ";
			}
	        if(lField=='')
	        lField='personalHistory';  
		}
	//Mandatory Check validation For Examination Findings and its sublist
	var examinFnds=document.forms[0].examinationFnd;
	var examinCount=0;
	var examinSubCount=0;
	var examFndsVal="";
	for(var i=0;i<examinFnds.length;i++)
	{
		if(examinFnds[i].checked)
		{
			//examinCount++;
			var examinFndsValue=examinFnds[i].value;		
			var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
			var subType=document.forms[0].elements[examinFndsValue].type;
			
			if(examinFndsValue=='GE11'){
				if(document.forms[0].temp[0].checked==false && document.forms[0].temp[1].checked==false){
					if(lErrorMsg==''){
						lErrorMsg=lErrorMsg+"Please select C or F option in "+examinFndsName+"";
						}
						if(lField=='')
						lField=examinFndsValue; 
					}
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					var tempType= '';
					if(document.forms[0].temp[0].checked==true) tempType='C';
					if(document.forms[0].temp[1].checked==true) tempType='F';
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";
					}
				}
			else if(examinFndsValue=='GE14'){
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null
						||document.getElementById("BP1").value==""||document.getElementById("BP1").value==null )
					
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
					examFndsVal = examFndsVal+"#";
					}
				}
			else if(examinFndsValue=='GE15'){
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null||
						document.getElementById("BP2").value==""||document.getElementById("BP2").value==null)
					
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
	           }
				}
			else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){
				
			if(subType=="text" )
			{
			if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
			{
			if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
			}
			if(lField=='')
			lField=examinFndsValue; 
			}
			else{
				
				examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
				
				}
			}
			else
			{
				var examinFndsSubList=document.forms[0].elements[examinFndsValue];
				
				
				for(var j=0;j<examinFndsSubList.length;j++)
				{
				if(examinFndsSubList[j].checked)	
					{
					examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
					examinSubCount++; 
					if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
						{
						if(!document.forms[0].dehydrationY[0].checked && !document.forms[0].dehydrationY[1].checked && !document.forms[0].dehydrationY[2].checked)
							{
							if(lErrorMsg==''){
								lErrorMsg=lErrorMsg+"Select Examination Findings Dehydration Sub Options ";
								}
								if(lField=='')
								lField='dehydrationY'; 
							}
						else
							{
							var d;
							var dehydSubList=document.forms[0].dehydrationY;
							 for(d=0;d<dehydSubList.length;d++)
								{
								if(dehydSubList[d].checked)
									{
									examFndsVal = examFndsVal+dehydSubList[d].value;
									}
								}
	 
							}
						}
					}
				}
				if(examinSubCount==0)
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Select Examination Findings "+examinFndsSubList[0].name+" Options ";
				}
				if(lField=='')
				lField=examinFndsValue;  
				}
				examinSubCount=0;
			}
			examFndsVal = examFndsVal+"#";
		}
		}
		else
			{
			examinCount++;
			}
		}
		
	if(examinCount>0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select All Examination Findings Options ";
			}
	        if(lField=='')
	        lField='examinationFnd'; 
		}
	//Mandatory Check validation For Family History 
	var familyHistory=document.forms[0].familyHistory;
	var familyHistCount=0;
	for(var i=0;i<familyHistory.length;i++)
	{
		if(familyHistory[i].checked)
		{
		familyHistCount++;
		if(familyHistory[i].value=="FH11")
		   {
		   if(document.getElementById("familyHistoryOthr").value=='' || document.getElementById("familyHistoryOthr").value==null)
			   {
			   if(lErrorMsg==''){
			     lErrorMsg=lErrorMsg+"Enter Other Family History Value ";
				 }
			        if(lField=='')
			        lField='familyHistoryOthr'; 
			   }
		   }
		}
	}
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		alert(lErrorMsg);
		focusBox(document.getElementById(lField));
		return;
	 }
	if(document.getElementById("haemoglobin").value==null || document.getElementById("haemoglobin").value==''){
		lErrorMsg=lErrorMsg+"Please enter haemoglobin count. ";
		if(lField=='')
	        lField='haemoglobin';
	}
	else if(document.getElementById("tlc").value==null || document.getElementById("tlc").value==''){
		lErrorMsg=lErrorMsg+"Please enter Total Leukocyte count ";
		if(lField=='')
	        lField='tlc';
	}
	else if(document.getElementById("polymorphs").value==null || document.getElementById("polymorphs").value==''){
		lErrorMsg=lErrorMsg+"Please enter polymorphs count ";
		if(lField=='')
	        lField='polymorphs';
	}
	else if(document.getElementById("lymphocytes").value==null || document.getElementById("lymphocytes").value==''){
		lErrorMsg=lErrorMsg+"Please enter lymphocytes count ";
		if(lField=='')
	        lField='lymphocytes';
	}
	else if(document.getElementById("eosinophils").value==null || document.getElementById("eosinophils").value==''){
		lErrorMsg=lErrorMsg+"Please enter eosiophils count ";
		if(lField=='')
	        lField='eosinophils';
	}
	else if(document.getElementById("basophils").value==null || document.getElementById("basophils").value==''){
		lErrorMsg=lErrorMsg+"Please enter basophils count ";
		if(lField=='')
	        lField='basophils';
	}
	else if(document.getElementById("monocytes").value==null || document.getElementById("monocytes").value==''){
		lErrorMsg=lErrorMsg+"Please enter monocytes count ";
		if(lField=='')
	        lField='monocytes';
	}
	else if(document.getElementById("rbc").value==null || document.getElementById("rbc").value==''){
		lErrorMsg=lErrorMsg+"Please enter RBC count ";
		if(lField=='')
	        lField='rbc';
	}
	else if(document.getElementById("wbc").value==null || document.getElementById("wbc").value==''){
		lErrorMsg=lErrorMsg+"Please enter WBC count ";
		if(lField=='')
	        lField='wbc';
	}
	else if(document.getElementById("platelets").value==null || document.getElementById("platelets").value==''){
		lErrorMsg=lErrorMsg+"Please enter platelets count. ";
		if(lField=='')
	        lField='platelets';
	}
	else if(document.getElementById("bloodGroup").value==null || document.getElementById("bloodGroup").value==''){
		lErrorMsg=lErrorMsg+"Please enter blood group. ";
		if(lField=='')
	        lField='bloodGroup';
	}

	else if(document.getElementById("esr").value==null || document.getElementById("esr").value==''){
		lErrorMsg=lErrorMsg+"Please enter Erythrocyte Sedimentation Rate. ";
		if(lField=='')
	        lField='esr';
	}
	else if(document.getElementById("fastingSugar").value==null || document.getElementById("fastingSugar").value==''){
		lErrorMsg=lErrorMsg+"Please enter Fasting sugar. ";
		if(lField=='')
	        lField='fastingSugar';
	}
	else if(document.getElementById("postPrandialSugar").value==null || document.getElementById("postPrandialSugar").value==''){
		lErrorMsg=lErrorMsg+"Please enter Post prandial sugar. ";
		if(lField=='')
	        lField='postPrandialSugar';
	}
	else if(document.getElementById("glycosylatedHaemoglobin").value==null || document.getElementById("glycosylatedHaemoglobin").value==''){
		lErrorMsg=lErrorMsg+"Please enter Glycosylated Haemoglobin ";
		if(lField=='')
	        lField='glycosylatedHaemoglobin';
	}
	else if(document.getElementById("hbsAg").value==null || document.getElementById("hbsAg").value==''){
		lErrorMsg=lErrorMsg+"Please enter HbsAg(surface antigen of Hepatitis B virus)";
		if(lField=='')
	        lField='hbsAg';
	}
	else if(document.getElementById("totalCholesterol").value==null || document.getElementById("totalCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter Total Cholesterol ";
		if(lField=='')
	        lField='totalCholesterol';
	}
	else if(document.getElementById("hdlCholesterol").value==null || document.getElementById("hdlCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter HDL Cholesterol  ";
		if(lField=='')
	        lField='hdlCholesterol';
	}
	else if(document.getElementById("ldlCholesterol").value==null || document.getElementById("ldlCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter LDL Cholesterol ";
		if(lField=='')
	        lField='ldlCholesterol';
	}
	else if(document.getElementById("vldlCholesterol").value==null || document.getElementById("vldlCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter VLDL Cholesterol ";
		if(lField=='')
	        lField='vldlCholesterol';
	}
	else if(document.getElementById("triglycerides").value==null || document.getElementById("triglycerides").value==''){
		lErrorMsg=lErrorMsg+"Please enter Triglycerides. ";
		if(lField=='')
	        lField='triglycerides';
	}
	else if(document.getElementById("bloodUrea").value==null || document.getElementById("bloodUrea").value==''){
		lErrorMsg=lErrorMsg+"Select enter Blood Urea. ";
		if(lField=='')
	        lField='bloodUrea';
	}

	else if(document.getElementById("sCreatinine").value==null || document.getElementById("sCreatinine").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Creatinine. ";
		if(lField=='')
	        lField='sCreatinine';
	}
	else if(document.getElementById("sUricAcid").value==null || document.getElementById("sUricAcid").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Uric Acid ";
		if(lField=='')
	        lField='sUricAcid';
	}
	else if(document.getElementById("serumElectrolytes").value==null || document.getElementById("serumElectrolytes").value==''){
		lErrorMsg=lErrorMsg+"Please enter Serum Electrolytes ";
		if(lField=='')
	        lField='serumElectrolytes';
	}
	else if(document.getElementById("serumCreatinine").value==null || document.getElementById("serumCreatinine").value==''){
		lErrorMsg=lErrorMsg+"Please enter Serum Creatinine ";
		if(lField=='')
	        lField='serumCreatinine';
	}
	else if(document.getElementById("sgot").value==null || document.getElementById("sgot").value==''){
		lErrorMsg=lErrorMsg+"Please enter SGOT(Serum Glutamic Oxaloacetic Transaminase)  ";
		if(lField=='')
	        lField='sgot';
	}
	else if(document.getElementById("sgpt").value==null || document.getElementById("sgpt").value==''){
		lErrorMsg=lErrorMsg+"Please enter SGPT(Serum Glutamic Pyruvic Transaminase)  ";
		if(lField=='')
	        lField='sgpt';
	}
	else if(document.getElementById("sTotalBilirubin").value==null || document.getElementById("sTotalBilirubin").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Bilirubin(total) ";
		if(lField=='')
	        lField='sTotalBilirubin';
	}
	else if(document.getElementById("sDirectBilirubin").value==null || document.getElementById("sDirectBilirubin").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Bilirubin(direct)  ";
		if(lField=='')
	        lField='sDirectBilirubin';
	}
	else if(document.getElementById("liverSgot").value==null || document.getElementById("liverSgot").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.G.O.T ";
		if(lField=='')
	        lField='liverSgot';
	}
	else if(document.getElementById("liverSgpt").value==null || document.getElementById("liverSgpt").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.G.P.T ";
		if(lField=='')
	        lField='liverSgpt';
	}
	else if(document.getElementById("urineColor").value==null || document.getElementById("urineColor").value==''){
		lErrorMsg=lErrorMsg+"Please enter urine color. ";
		if(lField=='')
	        lField='urineColor';
	}
	else if(document.getElementById("urineAlbumin").value==null || document.getElementById("urineAlbumin").value==''){
		lErrorMsg=lErrorMsg+"Select enter urine albumin. ";
		if(lField=='')
	        lField='urineAlbumin';
	}
	else if(document.getElementById("urineSugar").value==null || document.getElementById("urineSugar").value==''){
		lErrorMsg=lErrorMsg+"Please enter urine sugar. ";
		if(lField=='')
	        lField='urineSugar';
	}
	else if(document.getElementById("urineMicroscopicExam").value==null || document.getElementById("urineMicroscopicExam").value==''){
		lErrorMsg=lErrorMsg+"Select enter urine microscopin examination result. ";
		if(lField=='')
	        lField='urineMicroscopicExam';
	}

	else if(document.getElementById("abdomenUltrasound").value==null || document.getElementById("abdomenUltrasound").value==''){
		lErrorMsg=lErrorMsg+"Please enter ultrasound abdomen report findings ";
		if(lField=='')
	        lField='abdomenUltrasound';
	}
	else if(document.getElementById("chestXrayPAView").value==null || document.getElementById("chestXrayPAView").value==''){
		lErrorMsg=lErrorMsg+"Please enter chest Xray PA view report findings ";
		if(lField=='')
	        lField='chestXrayPAView';
	}
	else if(document.getElementById("pulmonaryFunction").value==null || document.getElementById("pulmonaryFunction").value==''){
		lErrorMsg=lErrorMsg+"Select enter pulmonary function report findings. ";
		if(lField=='')
	        lField='pulmonaryFunction';
	}
	else if(document.getElementById("ecg").value==null || document.getElementById("ecg").value==''){
		lErrorMsg=lErrorMsg+"Please enter ECG report findings ";
		if(lField=='')
	        lField='ecg';
	}
	else if(document.getElementById("twodEcho").value==null || document.getElementById("twodEcho").value==''){
		lErrorMsg=lErrorMsg+"Please enter 2D Echo report findings ";
		if(lField=='')
	        lField='twodEcho';
	}
	
	else if(document.getElementById("retinopathy").value==null || document.getElementById("retinopathy").value==''){
		lErrorMsg=lErrorMsg+"Please enter retinopathy report findings. ";
		if(lField=='')
	        lField='retinopathy';
	}
	else if(document.getElementById("fundoscopy").value==null || document.getElementById("fundoscopy").value==''){
		lErrorMsg=lErrorMsg+"Select enter fundoscopy report findings. ";
		if(lField=='')
	        lField='fundoscopy';
	}
	else if(document.getElementById("audiometry").value==null || document.getElementById("audiometry").value==''){
		lErrorMsg=lErrorMsg+"Please enter audiometry report findings. ";
		if(lField=='')
	        lField='audiometry';
	}
	else if(document.forms[0].haemoglobinReport[0].checked==false && document.forms[0].haemoglobinReport[1].checked==false ){
		lErrorMsg=lErrorMsg+"Please select haemoglobin level of the officer. ";
		if(lField=='')
	        lField='haemoglobinReport';

	}
	else if(document.forms[0].bloodSugarReport[0].checked==false && document.forms[0].bloodSugarReport[1].checked==false && document.forms[0].bloodSugarReport[2].checked==false && document.forms[0].bloodSugarReport[3].checked==false){
		lErrorMsg=lErrorMsg+"Please select Blood sugar report of the officer. ";
		if(lField=='')
	        lField='bloodSugarReport';

	}
	else if(document.forms[0].cholesterolReport[0].checked==false && document.forms[0].cholesterolReport[1].checked==false && document.forms[0].cholesterolReport[2].checked==false ){
		lErrorMsg=lErrorMsg+"Please select cholesterol report of the officer. ";
		if(lField=='')
	        lField='cholesterolReport';

	}
	else if(document.forms[0].liverFunctionReport[0].checked==false && document.forms[0].liverFunctionReport[1].checked==false && document.forms[0].liverFunctionReport[2].checked==false && document.forms[0].liverFunctionReport[3].checked==false){
		lErrorMsg=lErrorMsg+"Please select liver functioning report of the officer. ";
		if(lField=='')
	        lField='liverFunctionReport';

	}
	else if(document.forms[0].kidneyReport[0].checked==false && document.forms[0].kidneyReport[1].checked==false ){
		lErrorMsg=lErrorMsg+"Please select Kidney status of the officer. ";
		if(lField=='')
	        lField='kidneyReport';

	}
	else if(document.forms[0].cardiacReport[0].checked==false && document.forms[0].cardiacReport[1].checked==false  && document.forms[0].cardiacReport[2].checked==false  && document.forms[0].cardiacReport[3].checked==false ){
		lErrorMsg=lErrorMsg+"Please select cardiac status of the officer. ";
		if(lField=='')
	        lField='cardiacReport';

	}
	
	else if(document.getElementById("overallHealthRemarks").value==null || document.getElementById("overallHealthRemarks").value==''){
		lErrorMsg=lErrorMsg+"Please enter Overall Health remarks of the officer. ";
		if(lField=='')
	        lField='overallHealthRemarks';
	}
	
	else if(document.getElementById("healthGrade").value==""){
		lErrorMsg=lErrorMsg+"Please select health grade. ";
		if(lField=='')
	        lField='healthGrade';
	}
	if(checkType=='submit')
	{
		
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		alert(lErrorMsg);
		focusBox(document.getElementById(lField));
		return;
	 }
	
	 
	else
	{
		var saveFlag="Submit";
		if(confirm('Do you want to register Annual health checkup case?')==true)
			registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType);
			
	}
	}
	}  
	function registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType)
	{
	    var selInvData='';
		 var selGenInvData='';
		 var updateGenInvData='';
		 
			document.forms[0].personalHistVal.value=personalHistVal;
	   		document.forms[0].examFndsVal.value=examFndsVal;
	   		document.getElementById("Submit").disabled=true;
			document.getElementById("Submit").className='butdisable';
			document.getElementById("Save").disabled=true;
			document.getElementById("Save").className='butdisable';
			document.getElementById("Reset").disabled=true;
			document.getElementById("Reset").className='butdisable';
			
			var url="./annualCheckUpAction.do?actionFlag=saveAhcDetails&patientId="+patId+"&saveFlag="+saveFlag+"&checkType="+checkType;
			
			
			
			/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.forms[0].submit();
	}
	function maxLengthPress(field,maxChars,e)
	{
		var code;
	    if (!e) var e = window.event;
	    if (e.keyCode) code = e.keyCode;
	    else if (e.which) code = e.which; 
		if(field.value.length >= maxChars) 
		{
			if(code==8 || code==9 || code==46 || code==37 || code==38 || code==39 || code==40)
				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
				{
					e.returnValue=true;
					return true;
				}
			else
				{
					e.returnValue=false;
		        	return false;
			 	}
	         }
	}
	//For validating maxlength onpaste event--For textarea fields
	function maxLengthPaste(field,maxChars,event)
	{
	      event.returnValue=false;
	      if(window.clipboardData)
	    	  {
	      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
				{
	      			alert("Characters should not exceed 3000");
	        	return false;
	        	}
	      		event.returnValue=true;
	    	  }
	      if (event.clipboardData) 
	      {
	    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
	    		{
	    		alert("Characters should not exceed 3000");
	        	return false;
	        	}
	      		event.returnValue=true;
	      }
	}
	function browserDetect()
	{
		 var objAgent = navigator.userAgent; 
		 var objbrowserName  = navigator.appName;
		 var objOffsetVersion;
		if ((objOffsetVersion=objAgent.indexOf("Chrome"))!=-1) { 
			 objbrowserName = "Chrome"; 
		}
		else if ((objOffsetVersion=objAgent.indexOf("MSIE"))!=-1) { 
			objbrowserName = "Microsoft Internet Explorer"; 
		}
		else if ((objOffsetVersion=objAgent.indexOf("Firefox"))!=-1) { 
			objbrowserName = "Firefox"; 
		}
		return objbrowserName;
	}
	function checkAlphaNumericCodes(event) {
		browserName=browserDetect();
		//if(navigator.appName=="Microsoft Internet Explorer" || navigator.appVersion=='5.0 (Windows NT 5.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19')
		if(browserName=="Microsoft Internet Explorer" || browserName=="Chrome")
		{
		var charCode=event.keyCode;
		if ((charCode<65 || charCode>90)&&(charCode<97 || charCode>122)&&(charCode<48 || charCode>57)&&(charCode!=13 && charCode!=32))
				    return false; 	
						return true;  
		}
		else if(browserName=="Firefox")
		{
			var inputValue = String.fromCharCode(event.keyCode || event.charCode)
			var regExpr = /^[0-9a-zA-Z\s]+$/;
			if(event.keyCode != 0) {
				if(event.keyCode == 38 || event.keyCode == 40 || event.keyCode == 37 || event.keyCode == 39 ||
					event.keyCode == 8 || event.keyCode == 13 || event.keyCode == 46 || event.keyCode == 36 ||
					event.keyCode == 35 || event.keyCode == 33 || event.keyCode == 34 || event.keyCode == 45 ||
					event.keyCode == 9) {
				} else {
					return false;
				}
			} else if(event.charCode != 0){
				if(!inputValue.match(regExpr)) {
					return false;
				}
			}
			return true;
		}
	}
	function pasteInterceptUltra(evt)
	 {  
	var input=document.getElementById('abdomenUltrasound');
	maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptChestXray(evt)
	{
		var input=document.getElementById('chestXrayPAView');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptPulmonary(evt)
	{
		var input=document.getElementById('pulmonaryFunction');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptEcg(evt)
	{
		var input=document.getElementById('ecg');
		maxLengthPaste(input,4000,evt); 
	}
	function pasteIntercept2D(evt)
	 {  
	var input=document.getElementById('twodEcho');
	maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptTmt(evt)
	{
		var input=document.getElementById('treadmillTest');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptRetinopathy(evt)
	{
		var input=document.getElementById('retinopathy');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptFundoscopy(evt)
	{
		var input=document.getElementById('fundoscopy');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptAudiometry(evt)
	{
		var input=document.getElementById('audiometry');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptHealth(evt)
	{
		var input=document.getElementById('overallHealthRemarks');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptPresent(evt)
	{
		var input=document.getElementById('presentHistory');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptSummary(evt)
	{
		var input=document.getElementById('summary');
		maxLengthPaste(input,4000,evt); 
	}
	var personalHistory=document.forms[0].personalHistory;
	for(var i=0;i<personalHistory.length;i++)
	    {
		if('${otherFindings.personalHis}'==''){
			personalHistory[i].checked=true;
			  getSubListHistory(personalHistory[i],'NA');
		}
		else{
			if('${otherFindings.personalHis}'.indexOf(personalHistory[i].value,0)!=-1){
				personalHistory[i].checked=true;
	            getSubListHistory(personalHistory[i],'NA'); 
		}
	     }
	    }
		var examinationFnd=document.forms[0].examinationFnd;
		for(var i=0;i<examinationFnd.length;i++)
		{
			if('${otherFindings.examFindg}'==''){
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]);
			}
			else{
				if('${otherFindings.examFindg}'.indexOf(examinationFnd[i].value,0)!=-1){
					examinationFnd[i].checked=true;
					getSubFieldType(examinationFnd[i]); 
			}
		     }
		}

	
	var pastHistory=document.forms[0].pastHistory;
	var pastHistoryVal='${otherFindings.pastIllness}'.split("~");
	
	for(var i=0;i<pastHistory.length;i++)
	    {
		for(var j=0;j<pastHistoryVal.length;j++)
		{
		if(pastHistory[i].value==pastHistoryVal[j])
		{
			pastHistory[i].checked=true;
				if(pastHistory[i].value=='PH11' || pastHistory[i].value=='PH8' || pastHistory[i].value=='PH12' || pastHistory[i].value=='PH10'){
				getOtherText(pastHistory[i]);
				if(pastHistory[i].value=='PH11' && '${otherFindings.pastIllenesOthr}'!='')
					document.getElementById('pastHistryOthr').value='${otherFindings.pastIllenesOthr}';
				if(pastHistory[i].value=='PH8' && '${otherFindings.pastIllenesCancers}'!='')
					document.getElementById('pastHistryCancers').value='${otherFindings.pastIllenesCancers}';
				if(pastHistory[i].value=='PH12' && '${otherFindings.pastIllenesEndDis}'!='')
					document.getElementById('pastHistryEndDis').value='${otherFindings.pastIllenesEndDis}';
				if(pastHistory[i].value=='PH10' && '${otherFindings.pastIllenesSurg}'!='')
					document.getElementById('pastHistrySurg').value='${otherFindings.pastIllenesSurg}';
				}
		}}}
	var familyHistory=document.forms[0].familyHistory;
	var famHistoryVal;
	if('${otherFindings.familyHis}'!='')
	{
		famHistoryVal='${otherFindings.familyHis}'.split("~");
		for(var i=0;i<familyHistory.length;i++)
	    {
			for(var j=0;j<famHistoryVal.length;j++)
			{
				if(familyHistory[i].value==famHistoryVal[j])
				{
					familyHistory[i].checked=true;
					if(familyHistory[i].value=='FH11'){	
						getOtherText(familyHistory[i]);
						if('${otherFindings.familyHistoryOthr}'!='')
							document.getElementById('otherFindings.familyHistoryOthr').value='${otherFindings.familyHistoryOthr}';
					}
				}
			}
		} 
	}
	
	if('${otherFindings.lstPerHis}'!=null && '${otherFindings.lstPerHis}'.indexOf("PR5.1",0)!=-1)
		{ 
			var KnownAllg='<table width="100%" border="1"><tr><td>'+
		'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
		'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
		'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
		'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
		'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
		'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
	    
		document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
		}
		if('${otherFindings.lstPerHis}'!=null && '${otherFindings.lstPerHis}'.indexOf("PR6.1",0)!=-1)
	{
	var personalHabits='<table width="100%" border="1"><tr><td>'+
	'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Regular'+
	'<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>Occasional'+
	'<input type="radio" name="alcohol" id="alcohol" value="Teetotaler" title="Teetotaler"/>Teetotaler </td></tr>'+
		'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
	'<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
	'<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
	'<div id="smokingTd" style="display:none">'+
	'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
	'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
	'<tr><td>Drug Use:<input type="radio" name="drugUse" id="drugUse" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="drugUse" id="drugUse" value="No" title="No"/>No</td></tr>'+
	'<tr><td>Betel nut:<input type="radio" name="betelNut" id="betelNut" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="betelNut" id="betelNut" value="No" title="No"/>No</td></tr>'+
	'<tr><td>Betel leaf:<input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="betelLeaf" id="betelLeaf" value="No" title="No"/>No</td></tr></table>';
	document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
	}

		var personalHistVal= '${otherFindings.lstPerHis}';	
		personalHistVal=personalHistVal.replace("[","");
		personalHistVal=personalHistVal.replace("]","");
		var perHistListing=personalHistVal.split(",");
		for(i=0;i<perHistListing.length;i++){
			var pr= perHistListing[i];
			var prval=pr.substring(0,6);
			prval1=pr.substring(0,5);
			var prval2=prval.substring(prval.length-1,prval.length);
			var val=parseInt(prval2,10)-1;
			if(prval1.trim()=="PR1.1"){
				document.forms[0].PR1[val].checked='checked';
			}
			if(prval1.trim()=="PR2."){
				document.forms[0].PR2[val].checked='checked';
			}
			if(prval1.trim()=="PR3."){
				document.forms[0].PR3[val].checked='checked';
			}
			if(prval1.trim()=='PR4.'){
				document.forms[0].PR4[val].checked='checked';
			}
			if(prval1.trim()=='PR5.'){
				document.forms[0].PR5[val].checked='checked';
			}
			if(prval1.trim()=='PR6.'){
				document.forms[0].PR6[val].checked='checked';
			}
			if(prval1.trim()=='PR7.'){
				document.forms[0].PR7[val].checked='checked';
			}

		}
		
	
	var addition='${otherFindings.addiction}';var additionKnown='${otherFindings.allergy}';
		addition=addition.replace("PR6.1(","");
		additionKnown=additionKnown.replace("PR5.1,","");
		var additionList = addition.split(",");
		var addKnownList = additionKnown.split(",");
		if(addKnownList.length>0){
			
			for(var i = 0; i<addKnownList.length;i++)
		    {	            			    
				var addtn = addKnownList[i].split("$");	
				           		        
				if(addtn[0]=='AllMed'){
					var spitedY = addtn[1].split("(");	
					if(spitedY[0]=='AllMedYes'){	               						
						document.forms[0].AllMed[0].checked='checked';
						document.getElementById("AllMedDiv").style.display='block';
						if(spitedY.length>1){
						var valueY = spitedY[1].split("@");					
						document.getElementById("AllMedRemrk").value=valueY[1];
						}
					}
					else if(addtn[1]=='AllMedNo'){
						document.forms[0].AllMed[1].checked='checked';
				}
			   }
				if(addtn[0]=='AllSub'){
					var spitedY = addtn[1].split("(");	
					if(spitedY[0]=='AllSubYes'){
						
						document.forms[0].AllSub[0].checked='checked';
						document.getElementById("AllSubDiv").style.display='block';
						if(spitedY.length>1){
							var valueY = spitedY[1].split("@");					
							document.getElementById("AllSubRemrk").value=valueY[1];
							}
					}
					else if(addtn[1]=='AllSubNo'){
						document.forms[0].AllSub[1].checked='checked';
				}
			   }
		}
		}
		if(additionList.length>0)
		{
			for(var i = 0; i<additionList.length;i++)
		    {	
			
			    var addtn = additionList[i].split("$");
			    if(addtn[0]=='Alcohol')
			    	{if(addtn[1]=='Regular')
			    		document.forms[0].alcohol[0].checked='checked';
			    	else if (addtn[1]=='Occasional')
			    		document.forms[0].alcohol[1].checked='checked';
			    	else if (addtn[1]=='Teetotaler')
			    		document.forms[0].alcohol[2].checked='checked';
			    	}
			    else if(addtn[0]=='Tobacco')
				    {
			    	var tabacoLst = addtn[1].split("(");
			    	
			    	if(tabacoLst[0]=='Snuff')
			    		document.forms[0].tobacco[0].checked='checked';
			    	else if (tabacoLst[0]=='Chewable')
			    		document.forms[0].tobacco[1].checked='checked';
			    	else if (tabacoLst[0]=='Smoking')
				    	{
			    		document.forms[0].tobacco[2].checked='checked';
			    		tabacoLst[1] = tabacoLst[1].replace(")","");
			    		
			    		document.getElementById("smokingTd").style.display='block';
			    		
			    		var smokSub = tabacoLst[1].split("*");
			    	
			    		if(smokSub.length>0)
				    		{
		                       for(var j=0;j<smokSub.length;j++){
		                    	   
		                    	  var smokeVal= smokSub[j].split("@");
		                    	  
		                    	  if(smokeVal[0]=='PackNo')
		                    		  document.forms[0].packNo.value=smokeVal[1];
		                    	  else
		                    		  document.forms[0].smokeYears.value=smokeVal[1];
		                           } 
				    		}
				    	}
		             }
			    else if(addtn[0]=='DrugUse')
				    {
		              if(addtn[1]=='Yes')
		            	  document.forms[0].drugUse[0].checked='checked';
		              else  if(addtn[1]=='No')
		            	  document.forms[0].drugUse[1].checked='checked';
		            }
			    else if(addtn[0]=='BetelNut')
			    {
			    	if(addtn[1]=='Yes')
		          	  document.forms[0].betelNut[0].checked='checked';
		            else  if(addtn[1]=='No')
		          	  document.forms[0].betelNut[1].checked='checked';
			    }
			    else if(addtn[0]=='BetelLeaf')
			    {
			    	addtn[1] = addtn[1].replace(")","");
			    	if(addtn[1]=='Yes')
		          	  document.forms[0].betelLeaf[0].checked='checked';
		            else  if(addtn[1]=='No')
		          	  document.forms[0].betelLeaf[1].checked='checked';
			    }
		    }
		}	
		
		if('${otherFindings.height}'!='NA' && '${otherFindings.height}'!=''){
			document.forms[0].GE1.value='${otherFindings.height}';
			heightVar='${otherFindings.height}';
		}
		if('${otherFindings.weight}'!='NA' && '${otherFindings.weight}'!=''){
			document.forms[0].GE2.value='${otherFindings.weight}';
			weightVar='${otherFindings.weight}';
			}
		if('${otherFindings.bmi}'!='NA' && '${otherFindings.bmi}'!='')
			document.forms[0].GE3.value='${otherFindings.bmi}';
		if('${otherFindings.pallor}'!=''){
				if('${otherFindings.pallor}'=='Y')
		      	  document.forms[0].GE4[0].checked='checked';
		        else  if('${otherFindings.pallor}'=='N')
		      	  document.forms[0].GE4[1].checked='checked';
			}
		if('${otherFindings.cyanosis}'!=''){
			if('${otherFindings.cyanosis}'=='Y')
	      	  document.forms[0].GE5[0].checked='checked';
	        else  if('${otherFindings.cyanosis}'=='N')
	      	  document.forms[0].GE5[1].checked='checked';
		}
		if('${otherFindings.clubbingOfFingers}'!=''){
			if('${otherFindings.clubbingOfFingers}'=='Y')
	      	  document.forms[0].GE6[0].checked='checked';
	        else  if('${otherFindings.clubbingOfFingers}'=='N')
	      	  document.forms[0].GE6[1].checked='checked';
		}
		if('${otherFindings.lymphadenopathy}'!=''){
			if('${otherFindings.lymphadenopathy}'=='Y')
	      	  document.forms[0].GE7[0].checked='checked';
	        else  if('${otherFindings.lymphadenopathy}'=='N')
	      	  document.forms[0].GE7[1].checked='checked';
		}
		if('${otherFindings.oedema}'!=''){
			if('${otherFindings.oedema}'=='Y')
	      	  document.forms[0].GE8[0].checked='checked';
	        else  if('${otherFindings.oedema}'=='N')
	      	  document.forms[0].GE8[1].checked='checked';
		}
		if('${otherFindings.malNutrition}'!=''){
			if('${otherFindings.malNutrition}'=='Y')
	      	  document.forms[0].GE9[0].checked='checked';
	        else  if('${otherFindings.malNutrition}'=='N')
	      	  document.forms[0].GE9[1].checked='checked';
		}
		if('${otherFindings.dehydration}'!=''){
			if('${otherFindings.dehydration}'=='Y'){
	      	  document.forms[0].GE10[0].checked='checked';
	      	  var examinField="<input type='radio' name='dehydrationY' id='dehydrationY' value='Mild' title='Mild'/>Mild<input type='radio'  name='dehydrationY' id='dehydrationY' value='Moderate' title='Moderate'/>Moderate<input type='radio'  name='dehydrationY' id='dehydrationY' value='Severe' title='Severe'/>Severe";
		      document.getElementById("DehydrationSub").innerHTML=examinField;
	      	  
	      	  if('${otherFindings.dehydrationType}'!=''){
	      		if('${otherFindings.dehydrationType}'=='Mild')
	      		document.forms[0].dehydrationY[0].checked='checked';
	      		if('${otherFindings.dehydrationType}'=='Moderate')
	      		document.forms[0].dehydrationY[1].checked='checked';
	      		if('${otherFindings.dehydrationType}'=='Severe')
	      		document.forms[0].dehydrationY[2].checked='checked';
	          }
			}
	        else  if('${otherFindings.dehydration}'=='N')
	      	  document.forms[0].GE10[1].checked='checked';
		}	
		if('${otherFindings.temperature}'!='NA' && '${otherFindings.temperature}'!=''){
			var temp = '${otherFindings.temperature}';
			var tempType = temp.charAt(temp.length - 1);
			temp = temp.slice(0,str.length-1);
			document.forms[0].GE11.value=temp;	
			
			if(tempType=="C"){
				document.forms[0].temp[0].checked='checked';
				}
			else if(tempType=="F")
				document.forms[0].temp[1].checked='checked';			
		}	
		if('${otherFindings.pulseRate}'!='NA' && '${otherFindings.pulseRate}'!='')
			document.forms[0].GE12.value='${otherFindings.pulseRate}';
		if('${otherFindings.respirationRate}'!='NA' && '${otherFindings.respirationRate}'!='')
			document.forms[0].GE13.value='${otherFindings.respirationRate}';
		if('${otherFindings.bpLmt}'!='NA' && '${otherFindings.bpLmt}'!=''){
			var bpLmt='${otherFindings.bpLmt}'.split("/");
			document.forms[0].GE14.value=bpLmt[0];
			document.forms[0].BP1.value=bpLmt[1];	
		}	
		if('${otherFindings.bpRmt}'!='NA' && '${otherFindings.bpRmt}'!='')
			{var bpRmt='${otherFindings.bpRmt}'.split("/");
			document.forms[0].GE15.value=bpRmt[0];
			document.forms[0].BP2.value=bpRmt[1];	
		}
		var browserName=navigator.appName;
		if(browserName=="Microsoft Internet Explorer")
			{
			//For validating maxlength onpaste event--For textarea fields
			document.getElementById("presentHistory").attachEvent("paste", pasteInterceptPresent, false);
			document.getElementById("abdomenUltrasound").attachEvent("onpaste",pasteInterceptUltra);
			document.getElementById("chestXrayPAView").attachEvent("onpaste",pasteInterceptChestXray);
			document.getElementById("pulmonaryFunction").attachEvent("onpaste",pasteInterceptPulmonary);
			document.getElementById("ecg").attachEvent("onpaste",pasteInterceptEcg);
			document.getElementById("twodEcho").attachEvent("onpaste",pasteIntercept2D);
			document.getElementById("treadmillTest").attachEvent("onpaste",pasteInterceptTmt);
			document.getElementById("retinopathy").attachEvent("onpaste",pasteInterceptRetinopathy);
			document.getElementById("fundoscopy").attachEvent("onpaste",pasteInterceptFundoscopy);
			document.getElementById("audiometry").attachEvent("onpaste",pasteInterceptAudiometry);
			document.getElementById("overallHealthRemarks").attachEvent("onpaste",pasteInterceptHealth);
			document.getElementById("summary").attachEvent("onpaste",pasteInterceptSummary);
			
			}
		else if(browserName == "Netscape")
			{
			document.getElementById("presentHistory").addEventListener("paste", pasteInterceptPresent, false);
			document.getElementById("abdomenUltrasound").addEventListener("onpaste",pasteInterceptUltra);
			document.getElementById("chestXrayPAView").addEventListener("onpaste",pasteInterceptChestXray);
			document.getElementById("pulmonaryFunction").addEventListener("onpaste",pasteInterceptPulmonary);
			document.getElementById("ecg").addEventListener("onpaste",pasteInterceptEcg);
			document.getElementById("twodEcho").addEventListener("onpaste",pasteIntercept2D);
			document.getElementById("treadmillTest").addEventListener("onpaste",pasteInterceptTmt);
			document.getElementById("retinopathy").addEventListener("onpaste",pasteInterceptRetinopathy);
			document.getElementById("fundoscopy").addEventListener("onpaste",pasteInterceptFundoscopy);
			document.getElementById("audiometry").addEventListener("onpaste",pasteInterceptAudiometry);
			document.getElementById("overallHealthRemarks").addEventListener("onpaste",pasteInterceptHealth);
			document.getElementById("summary").addEventListener("onpaste",pasteInterceptSummary);
			}
	</script>
	</logic:equal>
	</html>
</fmt:bundle>