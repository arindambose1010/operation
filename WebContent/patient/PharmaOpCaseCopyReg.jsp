<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
<%@ include file="/common/include.jsp"%>

<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
	<html>
<head>
<title>EHS::Pharmacy Form</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css"
	rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css"
	media="print">
	<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<script src="js/jquery-1.9.1.min.js"></script>	
<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker.js"></script>
	<script src="js/DateTimePicker.js"></script>	

<script type="text/vbscript">  
      Function GetFeatureAccrual (IdOfHiddenTextField)
	
	Dim Obj

	If (IdOfHiddenTextField Is Nothing) Then

		MsgBox "Invalid Hidden Field Argument Passed", vbExclamation, "Web API BMA Application"
	Else

		Set Obj = CreateObject ("Web_API_3.Legend")

		If (Obj Is Nothing) Then

			MsgBox "Unable To Create Instance For Web API, Check Dll Is Registered Properly", vbExclamation, "Web API BMA Application"
		Else

			IdOfHiddenTextField.Value = ""

			Obj.GetFeatureAccrual

			IdOfHiddenTextField.Value = Obj.Feature

			If IdOfHiddenTextField.Value <> "" Then
				MsgBox "Fingerprint Captured Successfully.", vbInformation, "Web API BMA Application"
			Else
				MsgBox "Fingerprint Not Captured Successfully", vbExclamation, "Web API BMA Application"
			End If

		End If

	End If

End Function 
    </script>
<script type="text/javascript">
	$(document).ready(function (){
		
		var apprPharma='${apprPharma}';
		if(apprPharma!=null && apprPharma!='' && apprPharma!=' ' && apprPharma=='Y')
			{
				var drugsSize='${drugsSize}';
				if(drugsSize!=null && drugsSize!='' && drugsSize!=' ')
					{
						var numDrugsSize=Number(drugsSize);
						for(var i=1;i<=numDrugsSize;i++)
							{
								$( "#drugExpDateDP"+i ).datepicker({
									changeMonth: true,
									changeYear: true,
									showOn: "both", 
						            buttonImage: "images/calend.gif", 
						            buttonText: "Calendar",
						            buttonImageOnly: true ,
									numberOfMonths: 1,
									minDate:new Date(),
							        maxDate:"+10y"
								}); 
							}
					}
			}

		
	});
	function fn_captureBiometrics()
		{
			if(document.getElementById('bioCapture')!=null && document.getElementById("biometricValue")!=null &&
					document.getElementById("fingerPrint")!=null )
				{
					document.getElementById('bioCapture').disabled=true;
					//BioMetric Code
					GetFeatureAccrual(document.getElementById("biometricValue"));
					//document.getElementById("biometricValue").value="TESTVALUE";
					var biometricReturnValue=document.getElementById("biometricValue").value;
					if(biometricReturnValue!=null && biometricReturnValue!='' && biometricReturnValue!=' ')	
						{
							var fingerPrint=document.getElementById("fingerPrint").value;
							//alert("fingerPrint"+fingerPrint);
							//alert("biometricReturnValue"+biometricReturnValue);
							if(biometricReturnValue==fingerPrint)
								{
									document.getElementById("captured").style.display="";
									document.getElementById("unCaptured").style.display="none";
									document.getElementById('bioCapture').disabled=false;	
								}
							else
								{
									alert('Failed!Current Biometrics does not match with the Biometrics Provided at Patient registraton');
									document.getElementById("biometricValue").value='';
									document.getElementById("captured").style.display="none";
									document.getElementById("unCaptured").style.display="";
									document.getElementById('bioCapture').disabled=false;
									//focusFieldView("bioCapture");
									return false;
								}
						}
					else
						{
							document.getElementById("captured").style.display="none";
							document.getElementById("unCaptured").style.display="";
							document.getElementById('bioCapture').disabled=false;
							//focusFieldView("bioCapture");
							alert("There is a problem in fetching the Biometric Data at this moment.Please try again after some time");
							return false;
						}
				}
			
			//End of BioMetric Code
		}	
	function fn_submitDrugs()
		{
			if(document.getElementById('bioCapture')!=null && document.getElementById("biometricValue")!=null &&
					document.getElementById("fingerPrint")!=null )
				{
					document.getElementById('bioCapture').disabled=true;
					var biometricReturnValue=document.getElementById("biometricValue").value;
					if(biometricReturnValue==null || biometricReturnValue=='' || biometricReturnValue==' ')	
						{
							document.getElementById("captured").style.display="none";
							document.getElementById("unCaptured").style.display="";
							alert("Please Capture the Biometrics of Patient.");
							//focusFieldView("bioCapture");
							return false;
							document.getElementById('bioCapture').disabled=false;
						}
				}
			
			
			document.getElementById('SubmitDrugs').disabled=true;
			var drugDtls=null,firstCheckId=null,count=0;
			$('#drugTable tr').each(function (){
				var checkId=$(this).find('input[type=checkbox]').attr('id');
				if(checkId!=null && checkId!='' && checkId!=' ')
					{
						count++;
						if(count==1)
							firstCheckId=document.getElementById(checkId);
						
						if(document.getElementById(checkId).checked==true)
							{
								var checkVal=document.getElementById(checkId).value;
								if(checkVal!=null)
									{
										var individualIds=checkVal.split("~");
										var expiryDate=null,drugQuantity=null;
										
										if(individualIds[2]!=null)
											expiryDate=document.getElementById(individualIds[2]).value;
										if(individualIds[3]!=null)
											drugQuantity=document.getElementById(individualIds[3]).value;
											
										if(drugDtls==null)
											drugDtls=individualIds[0]+'~'+individualIds[1];
										else
											drugDtls=drugDtls+'@'+individualIds[0]+'~'+individualIds[1];
										
										drugDtls=drugDtls+'~'+expiryDate+'~'+drugQuantity;
									}
							}
					}
			});
			/* if(drugDtls==null || drugDtls =='' || drugDtls==' ')
				{
					alert('Please select any of the drug to Allocate to Patient');
					
					if(firstCheckId!=null)
						focusBox(firstCheckId);
					
					return false;
				}
			else
				{ */
			var res=confirm("Do you want to Submit ?");
			if(res==true)
				{
					document.forms[0].action="patientDetails.do?actionFlag=submitPharmaCase&pharmaCases=${pharmaCases}&apprPharma=${apprPharma}&caseId=${caseId}&patientId=${patientId}&pharmaCaseId=${pharmaCaseId}&drugDtls="+drugDtls;
					document.forms[0].method="post";
					document.forms[0].submit();
				}
			else 
				{
					document.getElementById('SubmitDrugs').disabled=false;
					if(document.getElementById('bioCapture')!=null)
						document.getElementById('bioCapture').disabled=false;
					return false;
				}
				/* } */
		}
	function checkBoxClicked(drugCode,expDtId,doseId,chckBoxId)
		{
			if(drugCode!=null && chckBoxId!=null && doseId!=null && expDtId!=null )
				{
					if(document.getElementById(chckBoxId)!=null
							&& document.getElementById(expDtId)!=null
							&& document.getElementById(doseId)!=null)
						{
							if(document.getElementById(expDtId).value==null && document.getElementById(expDtId).value==''
									&& document.getElementById(expDtId).value==' ')
								document.getElementById(expDtId).value="";
							
							document.getElementById(doseId).value="";
							
							if(document.getElementById(chckBoxId).checked==false)
								document.getElementById(doseId).disabled=true;
							else
								document.getElementById(doseId).disabled=false;
						}
				}
		}
	function validateNumber(input)
		{
			var a = input.value;
			/* if(a=="")
				{
					alert('Enter the Contact No');
					input.value="";
					focusBox(input);
					return false;
				} */
			var regAlphaNum=/^[0-9]+$/;
			if(a.search(regAlphaNum)==-1)
				{
					alert('Enter the valid Quantity');
					input.value="";
					focusBox(input);
					return false;
				}
			if((a.trim().length > 2))
				{
					alert('A maximum of 99 drugs can only be issued');
					input.value="";
					focusBox(input);
					return false;
				}
		}
	function focusBox(arg)
		{
			aField=arg;
			setTimeout("aField.focus()",0);
		}
	function fn_loadImage()
		{
			document.getElementById('processImagetable').style.display="";
		}
	function fn_removeLoadingImage()  
		{
			document.getElementById('processImagetable').style.display="none";  
		}
	function fn_checkCond()
		{
			fn_removeLoadingImage();
			var resultMsg='${resultMsg}';
			if(resultMsg!=null && resultMsg!='' && resultMsg!=' ')
				alert(resultMsg);
				
		}	
</script>
</head>
<body onload="fn_checkCond()">

<form action="/patientDetails.do" method="post" name="caseGeneratedForm">
<html:hidden name="patientForm" property="biometricValue"  styleId="biometricValue" ></html:hidden>
<html:hidden name="patientForm" property="fingerPrint"  styleId="fingerPrint" ></html:hidden>

	<table width="90%" style="margin: 0 auto" class="tb print_table">
		<tr>
			<logic:notEqual name="patientForm" property="patientScheme"
				value="CD502">
				<logic:notEqual name="patientForm" property="scheme" value="CD202">
					<td><%@ include file="/common/Printheader.html"%>
					</td>
				</logic:notEqual>
				<logic:equal name="patientForm" property="scheme" value="CD202">
					<td><%@ include file="/common/Printheader_tg.html"%>
					</td>
				</logic:equal>
			</logic:notEqual>
			<logic:equal name="patientForm" property="patientScheme"
				value="CD502">
				<logic:notEqual name="patientForm" property="scheme" value="CD202">
					<td><%@ include file="/common/PrintHeaderJouAP.html"%>
					</td>
				</logic:notEqual>
				<logic:equal name="patientForm" property="scheme" value="CD202">
					<td><%@ include file="/common/PrintHeaderJouTG.html"%>
					</td>
				</logic:equal>
			</logic:equal>
		</tr>
		<tr>
			<th class="tbheader">OUTPATIENT TREATMENT FORM</th>
		</tr>
		<tr>
			<td style="text-align: left;" class="tbheader print_heading"><b>1.&nbsp;Patient&nbsp;Details</b></td>
		</tr>
		<!-- Patient details -->
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Health
								Card No</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="cardNo" /></td>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="dateIp" /></td>
					</tr>
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient
								Name</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="patientName" /></td>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="ageString" /></td>

					</tr>
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="gender" /></td>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="addr" /></td>
					</tr>
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact
								No</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="contactno" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<!--  Case details -->
		<tr>
			<td style="text-align: left;" class="tbheader print_heading"><b>2.&nbsp;Case&nbsp;Details</b></td>
		</tr>
		<tr>
			<td>
				<table width="100%">

					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>OP
								Number</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="ipNo" /></td>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration
								date and time</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="dtTime" /></td>
					</tr>
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Case
								No.</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="caseId" /></td>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Hospital
								Name</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="hospitalName" /></td>
					</tr>
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address
								and contact</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="hospaddr" /></td>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Doctor
								name</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><bean:write
								name="patientForm" property="doctorName" /></td>
						<!-- <td width="15%" class="tbcellCss print_cell"><strong>Registration no</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="docRegNo" /></td>
				<tr>
				<td width="15%" class="tbcellCss">Qualification:</td>
				<td width="35%" class="tbcellCss"><bean:write name="patientForm" property="docQual" /></td>
				<td width="15%" class="tbcellCss">Contact:</td>
				<td width="35%" class="tbcellCss"><bean:write name="patientForm" property="docMobileNo" /></td>
			</tr> -->
					</tr>
				</table>
			</td>
		</tr>
		<!-- Medical details -->
		<%--  <tr><td style="text-align:left" class="tbheader print_heading"><b>3.&nbsp;Medical&nbsp;Details</b></td></tr>
		<tr><td>
		   <table width="100%">
		     
		      
		      <c:if test="${opTgFlag ne 'Y' }">
		      <tr><td rowspan="3" width="30%"class="labelheading1 tbcellCss print_cell"><strong>i) Present Illness History</strong></td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>a) Complaint Type</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm"
						property="complType" /></td></tr>
		      	 <tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>b) Patient Complaint</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm"
						property="complaints" /></td></tr>
		      	<tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>c) Present Illness History</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm"
						property="presentHistory" /></td></tr>
				</c:if>	
				
				      <c:if test="${opTgFlag eq 'Y' }">
		 
		      	 <tr>
		      	 <td rowspan="1" width="30%"class="labelheading1 tbcellCss print_cell"><strong>i) Present Illness History</strong></td>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>a) Patient Complaint</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm"
						property="complaints" /></td></tr>
		  
				</c:if>	
					
				<!-- 		
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>iii) Known Allergies</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='algy'></div></td></tr>
		      
		       <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>iv) Habits or Addictions</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='hbts'></div></td></tr>	 
		      
		      <c:if test="${schemeId eq 'CD201' }">
		      <tr><td rowspan="5" width="30%"class="labelheading1 tbcellCss print_cell"><strong>v) Examination Findings</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> a) Temperature in (&deg;C/&deg;F</strong>)</td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patTemp" /></td></tr>	
		      
		      <tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>b) Pulse rate (/minute)</strong></td>
				<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patPulse" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>c) Respiration rate (/minute)</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="respir" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>d) Blood-Pressure Lt.Arm (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bloodPrLt" /></td>
			</tr>
		
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>e) Blood-Pressure Rt.Arm (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bloodPrRt" /></td>
			</tr> 
			</c:if>
			 -->
			
			
			
		    <c:if test="${schemeId eq 'CD202' }">
		      <tr><td rowspan="7" width="30%"class="labelheading1 tbcellCss print_cell"><strong>ii) Examination Findings</strong> </td>
		      
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> a) Height</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="height" /></td></tr>	
		      
		      
		       <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> b) Weight</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="weight" /></td></tr>	
		      </tr>
		      
		       <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> c) BMI</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bmi" /></td></tr>	
		      </tr>
		      
		      
		      <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> d) Temperature in (&deg;C/&deg;F</strong>)</td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patTemp" /></td></tr>	
		      </tr>
		      
		      <tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>e) Pulse rate</strong></td>
				<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patPulse" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>f) Respiratory rate </strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="respir" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>g) Blood-Pressure (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bloodPrLt" /></td>
			</tr>
		
		 
			</c:if>
			
			 <tr><td  width="30%"class="labelheading1 tbcellCss print_cell"><strong>iii) Past illness history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Past illness history</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="pastIllnessValue" /></td></tr>	 
		      
		      
		      <tr><td  width="30%"class="labelheading1 tbcellCss print_cell"><strong>iv) Family history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Family history</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="familyHistValue" /></td></tr>	 
		      
		      
		       <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>v) Main Signs and Symptoms</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="symptomName" /></td></tr>
		      
		      <tr><td colspan="3" class="tbcellCss print_cell"><span class="labelheading1"><strong>vi) Investigations done</strong></span>
		           <table width="100%">
		              <c:if test="${schemeId eq 'CD201' }">
		              <tr>
							<th width="50%" class="tbheader1">Investigation Block Name</th>  
							<th width="50%" class="tbheader1">Test name</th>
					 </tr>
					 </c:if>
					 
					       <c:if test="${schemeId eq 'CD202' }">
		              <tr>
					 
							<th width="100%" class="tbheader1">Test name</th>
					 </tr>
					 </c:if>
					 
					 <logic:iterate id="inv" name="patientForm" property="investigationLt">
					 	 <c:if test="${schemeId eq 'CD201' }">
					 	<tr>
					 	
						    <td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="TEST"/></td> 
							<td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="NAME"/></td>
						</tr></c:if>
						
								 	 <c:if test="${schemeId eq 'CD202' }">
					 	<tr>
					 	
						     
							<td  width="100%" class="tbcellBorder print_cell"><b><bean:write name="inv" property="NAME"/></b></td>
						</tr></c:if>
						
					</logic:iterate>
					<logic:empty name="patientForm" property="investigationLt">
						<tr>
							<td colspan="2" width="100%" class="tbcellBorder print_cell" align="center">No Investigations Found</td>
						</tr>
					</logic:empty>
		           </table>	      
		      </td></tr>	 
		   </table>
		</td></tr>
		<!--  diagnosis -->
		
		<tr><td style="text-align:left;" class="tbheader print_heading"><strong>4.&nbsp;Diagnosis</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   
		  <!--   <c:if test="${DiagnosisType ne 'N' }">
		      <tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Diagnosis Type</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="diagType" /></td>
			</tr>
			</c:if>
			
			<c:if test="${MainCatName ne 'N' }">
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Main Category Name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="mainCatName" /></td>
			</tr>
			</c:if>
			
			<c:if test="${CatName ne 'N' }">
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Category name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="catName" /></td>
			</tr>
			</c:if>
			
			<c:if test="${SubCatName ne 'N' }">
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Sub-Category Name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="subCatName" /></td>
			</tr>
			</c:if>
			
			<c:if test="${DisName ne 'N' }">
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Disease name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="diseaseName" /></td>
			</tr>
			</c:if>
			
			 --> 
			
			<c:if test="${DisAnatomicalSitename ne 'N' }">
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Disease anatomical name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="disAnatomicalName" /></td>
			</tr>
			</c:if>
			<c:if test="${DisAnatomicalSitename eq 'N' }">
			<tr>
			  
				<td  colspan="2" width="100%"  align="center" class="labelheading1 tbcellCss print_cell"><b>Diagnosis Not Found</b></td>
				
			</tr>
			</c:if>
			
		   </table>
		< /td></tr>--%>

		<!-- Biometric Details -->
		
		<logic:present name="patientForm" property="tgGovPatCond">
			<logic:equal name="patientForm" value="Y" property="tgGovPatCond">
					<c:if test="${patientForm.tgGovPatCond eq 'Y' && apprPharma eq 'Y' }">
					
				<tr>
					<td style="text-align: left" class="tbheader print_heading"><b>3.Biometric
							Details</b></td>
				</tr>
				<tr>
					<td class="tbcellCss">
						<table width="100%" style="table-layout: fixed;">
							<tr>
								<td width="70%" class="labelheading1 tbcellCss print_cell">
									<b>At the time of Patient Registration,Biometrics has been
										Captured for :</b>
								</td>
								<td width="30%" class="tbcellBorder print_cell"><b>
										&nbsp;<bean:write name="patientForm" property="bioHand" />&nbsp;-&nbsp;<bean:write
											name="patientForm" property="bioFinger" />&nbsp;Finger</b></td>
							</tr>
							<tr>
								<td width="70%" class="labelheading1 tbcellCss print_cell">
									<b>Please Capture the Biometric Details of the Patient for
										the Same Finger Captured at Registration.<font color="red">*</font></b>
								</td>
								<td width="30%" class="tbcellBorder print_cell">
								<div id="unCaptured" style="float:left;margin-left:5px !important;"><i style="color:red" class="fa fa-2x fa-exclamation-circle"></i></div>
						<div id="captured"  style="display:none;float:left;margin-left:5px !important;" ><i style="color:green" class="fa fa-2x fa-check"></i></div>
									<button class="but"
										style="float: left; margin-left: 2px !important;"
										type="button" title="Click to Capture Biometric Details"
										onclick="javascript:fn_captureBiometrics()" name="bioCapture"
										id="bioCapture">CAPTURE BIOMETRICS</button>
								</td>
						</table>
					</td>
				</tr>
				</c:if>
			</logic:equal>
		</logic:present>
		<!--  treatment details -->
		<tr>
			<td style="text-align: left" class="tbheader print_heading"><b>
					<c:if test="${patientForm.tgGovPatCond eq 'Y' && apprPharma eq 'Y' }">
						4.
					</c:if>
					<c:if test="${patientForm.tgGovPatCond ne 'Y' || apprPharma ne 'Y' }">
						3.
					</c:if>
					 Treatment details
			</b></td>
		</tr>
		<tr>
			<td class="tbcellCss"><span class="labelheading1"><strong>i)
						Drug details</strong></span>
				<table id="drugTable"width="100%" style="table-layout: fixed;">
					<logic:present name="patientForm" property="drugLt">
						<tr>


							<td style="width: 5%; word-wrap: break-word;"
								class="tbheader1 print_cell"><strong>Sr. No.</strong></td>

							<c:if test="${schemeId eq 'CD201'}">

								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Main Group
										Name</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Therapeutic
										Main Group Name</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Pharmacological
										SubGroup Name</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Chemical
										SubGroup Name</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Chemical
										Substance Name</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Route Type</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Route</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Strength
										Type</strong></td>
								<td style="width: 5%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Strength</strong></td>
								<td style="width: 5%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Dosage(per
										day)</strong></td>
								<td style="width: 5%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Medication
										Period(Days)</strong></td>
							</c:if>

							<c:if test="${schemeId eq 'CD202'}">

								<td style="width: 20%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Chemical
										Substance Name</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Route Type</strong></td>

								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Strength</strong></td>
								<td style="width: 15%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Dosage(per
										day)</strong></td>
								<td style="width: 12%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Medication
										Period(Days)</strong></td>
								<td style="width: 13%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Expiry Date</strong></td>
								<td style="width: 10%; word-wrap: break-word;"
									class="tbheader1 print_cell"><strong>Quantity</strong></td>
								<c:if test="${apprPharma eq 'Y' }">
									<td style="width: 5%; word-wrap: break-word;"
										class="tbheader1 print_cell">&nbsp;</td>
								</c:if>		
							</c:if>

						</tr>
						<c:set var="counter" value="${1}"></c:set>
						<logic:iterate id="drugs" name="patientForm" property="drugLt">
							<tr>
								<td style="width: 5%; word-wrap: break-word;"
									class="tbcellBorder  print_cell"><c:out value="${counter}" /></td>
								<c:if test="${schemeId eq 'CD201'}">

									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.DRUGTYPENAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.DRUGSUBTYPENAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.PSUBGRPNAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.CSUBGRPNAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.DRUGNAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.ROUTETYPENAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.ROUTENAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.STRENGTHTYPENAME}</td>
									<td style="width: 5%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.STRENGTHNAME}</td>
									<td style="width: 5%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.DOSAGE}</td>
									<td style="width: 5%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.MEDICATIONPERIOD}</td>
								</c:if>

								<c:if test="${schemeId eq 'CD202'}">
									<td style="width: 20%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.DRUGNAME}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.ROUTETYPENAME}</td>

									<td style="width: 5%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.STRENGTHNAME}</td>
									<td style="width: 5%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.DOSAGE}</td>
									<td style="width: 10%; word-wrap: break-word;"
										class="tbcellBorder  print_cell">${drugs.MEDICATIONPERIOD}</td>
									<td style="width: 15%;"	class="tbcellBorder  print_cell">
										<input value="${drugs.EXPIRYDATE}" type="text" readonly="readonly" style="width:70px" id="drugExpDateDP${counter}" name="drugExpDate" title="Select Expiry Date" >
									</td>
									<td style="width: 10%;"	class="tbcellBorder  print_cell">
										<input value="${drugs.QUANTITY}"  type="text" maxlength="2" onchange="validateNumber(this)" disabled="disabled" style="width:70px" name="drugDose" id="drugDose${counter}" title="Enter Dose" >
									</td>
									<c:if test="${apprPharma eq 'Y' }">
									<td align="center" style="width: 5%; word-wrap: break-word;"
										class="tbcellBorder  print_cell"><input type="checkbox"
										class="newCheckBoxClass" id="newCheckBox${counter}" name="selectedDrug"
										onclick="javascript:checkBoxClicked('${drugs.DRUGCODE}','drugExpDateDP${counter}','drugDose${counter}','newCheckBox${counter}')"
										value="${drugs.DRUGCODE}~${drugs.DRUGID}~drugExpDateDP${counter}~drugDose${counter}"
										title="Select to allocate Drug to Patient "></td>
									</c:if>	
								</c:if>
							</tr>
							<c:set var="counter" value="${counter+1}" />
						</logic:iterate>
					</logic:present>
					
					<logic:notPresent name="patientForm" property="drugLt">
						<tr>

							<td colspan="5" width="100%" align="center"
								class="labelheading1 tbcellCss print_cell"><b>Drugs Not
									Found</b></td>

						</tr>

					</logic:notPresent>

					<!-- 			<tr>
				<td colspan="4" class="tbheader1">iv) Follow up Information-</td>
			</tr>
			<tr>
			
				<td width="15%" class="tbcellCss" height="50px">Next Follow Up Date:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px">Floor:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss" height="50px">Block Name (OPD):</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px">Room:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss" height="50px">Consultant Name:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px">Mobile Number:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss" height="50px">Designation:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px"></td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr> -->
					<!-- <tr>
						<td colspan="12" class="tbcellBorder" style="color: red;">*The
							Investigations/ tests were done free of cost.</td>
					</tr> -->
				</table></td>
		</tr>
		<!--Declaration should not be displayed if the page is opened from past history or employee/pensioners login -->
		<c:if test="${decFlag ne 'N'}">
			<!-- Declaration -->
			<%-- <tr>
				<td  style="text-align:left;" class="tbheader print_heading"><b>6.Declaration by MEDCO/Resident DOCTOR/Consultant</b></td>
			</tr>
			<tr>
				<td  style="text-align:justify;line-height:20px;"class="tbcellBorder print_cell">The patient is examined by the consultant and follow up advice is given as per the prescription of the treating doctor in accordance to the standard medical practices.The prescribed medicines are essential for the follow up treatment and the validity of the expiry date is ascertained before the medicines are given to the patient.The patient has been properly advised about the dosage, timing, duration and precautions while consuming the medicines along with the side effects that are likely to occur. He/She is further advised to report immediately in case of experiencing any side effect/ adverse reactions.</td>
			</tr>
		<tr><td>
		  <table width="100%">
		  
		
			
		  		<tr>
				<td style="width:5%" class="tbheader1  print_cell"><strong>Sr.No</strong></td>
				<td style="width:15%" class="tbheader1 print_cell"><strong> Designation</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong> Name</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong>Signature/ Thumb impression</strong></td>
			</tr>
			
			
			  	<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">1</td>
				<td width="15%" class="tbcellCss print_cell" height="50px"><c:choose>
							<c:when test="${caseSchemeId eq 'CD201'}" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya Mithra
							</c:otherwise>
						</c:choose></td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="patientForm" property="mithra" /></td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
			
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">2</td>
				<c:if test="${diagnosedBy != '-1'}">
				<td width="15%" class="tbcellCss print_cell"  height="50px"><bean:write name="patientForm" property="diagnosedBy" /></td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="patientForm" property="doctorName" /></td>
				</c:if>
				<c:if test="${diagnosedBy == '-1'}">
				<td width="15%" class="tbcellCss print_cell"  height="50px">MEDCO</td>
				<td width="40%" class="tbcellCss print_cell" height="50px"></td>
				</c:if>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
<!-- 			<tr>
				<td width="5%" class="tbcellCss" height="50px">2</td>
				<td width="15%" class="tbcellCss" height="50px">Treating doctor</td>
				<td width="40%" class="tbcellCss" height="50px"></td>
				<td width="40%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="5%" class="tbcellCss" height="50px">3</td>
				<td width="15%" class="tbcellCss" height="50px">Dispensary in-charge</td>
				<td width="40%" class="tbcellCss" height="50px"></td>
				<td width="40%" class="tbcellCss" height="50px"></td>
			</tr> -->
		
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">3</td>
				<td width="15%" class="tbcellCss print_cell" height="50px">Patient</td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="patientForm" property="patientName" /></td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
		  
		  </table>
		</td></tr> --%>
		</c:if>
					<tr>	
						<td>
							&nbsp;
						</td>
					</tr>
		<logic:present name="patientForm" property="drugLt">
			<c:if test="${apprPharma eq 'Y' }">
				<tr class="print_buttons">
					<td align="center">
						<button class="but" type="button" id="SubmitDrugs" onclick="fn_submitDrugs();">Submit</button>
					</td>
				</tr>
			</c:if>	
		</logic:present>

	</table>
	<html:hidden name="patientForm" property="patientScheme"
		styleId="patientScheme" />
<div id="processImagetable" style="top:50%;width:100%;position:absolute;z-index:60;height:100%">
   <table border="0" align="center" width="100%" style="height:400" >
      <tr>
         <td>
           <div id="processImage" align="center">
             <img src="images/Progress.gif" width="100"
                     height="100" border="0"></img>
            </div>
          </td>
        </tr>
     </table>
</div>
</form>

</body>
	</html>
</fmt:bundle>