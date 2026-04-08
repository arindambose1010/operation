<%
/**
* FILENAME     : InPatientRegDlhJHS.jsp
* @AUTHOR      : B Pravalika
* --------------------------------------------------------------
* Change Id       AUTHOR          DESCRIPTION
* ---------------------------------------------------------------
*  8602		  B Pravalika	    Jsp to view Delhi Journalist details
*    
* --------------------------------------------------------------
**/
%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="/common/include.jsp"%>

<%
int liTabIndex = 0;
String photoUrl=(String)request.getAttribute("photo");
 %>
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="js/jquery-1.9.1.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script><!-- Chandana - 8326 -->
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>

<script type="text/javascript">
var $ = jQuery.noConflict();
var todayDt = new Date();

$(document).ready(function(){

	 $('#admissionDate').datepicker({
	        dateFormat: 'dd/mm/yy',
	        maxDate: new Date(),
	        changeMonth: true,
	        changeYear: true,
	        yearRange: '2000:' + new Date().getFullYear(),
	        showOn: "both",                 
	        buttonImage: "images/calend.gif", 
	        buttonImageOnly: true,
	        buttonText: "Select date"
	    });

	});

function validateInput(event) {
    const inputField = event.target;
    let value = inputField.value;
    const regex = /^[a-zA-Z0-9 ,.]*$/;
    if (!regex.test(value)) {
        inputField.value = value.slice(0, -1);
    }
}

function fn_captureBiometrics()
	{
		if(document.getElementById("bioFinger")!=null && document.getElementById("bioHand")!=null )
			{
				var bioFinger=document.getElementById("bioFinger").value;
				var bioHand=document.getElementById("bioHand").value;
				if(bioHand!=null && bioHand!='' && bioHand!=' ')
					{
						if(bioHand=='-1')
							{
								document.getElementById("captured").style.display="none";
								document.getElementById("unCaptured").style.display="";
								alert("Please select the Hand to which the Biometrics need to be Captured.");
								focusFieldView("bioHand");
							}
						else if(bioFinger=='-1')
							{
								document.getElementById("captured").style.display="none";
								document.getElementById("unCaptured").style.display="";
								alert("Please select the finger to which the Biometrics need to be Captured.");
								focusFieldView("bioFinger");
								return false;
							}
						else
							{
								GetFeatureAccrual(document.getElementById("biometricValue"));
								//document.getElementById("biometricValue").value="TESTVALUE";
								var biometricReturnValue=document.getElementById("biometricValue").value;
								if(biometricReturnValue!=null && biometricReturnValue!='' && biometricReturnValue!=' ')	
									{
										document.getElementById("captured").style.display="";
										document.getElementById("unCaptured").style.display="none";
									}
								else
									{
										document.getElementById("captured").style.display="none";
										document.getElementById("unCaptured").style.display="";
										alert("There is a problem in fetching the Biometric Data at this moment.Please try again after some time");
										return false;
									}
							}
					}
				else
					{
						document.getElementById("captured").style.display="none";
						document.getElementById("unCaptured").style.display="";
						alert("Please select the Hand to which the Biometrics need to be Captured.");
						focusFieldView("bioHand");
						return false;
					}
			}
		
			
	}
function checkExtension(id)
	{
		fileName=document.getElementById(id).value;
			if((fileName!=null)||(fileName!="")||(fileName.length>0))
				{
					var dotPos=fileName.lastIndexOf(".");
					var ext=fileName.substring((dotPos+1),fileName.length);
						if((ext=="png")||(ext=="PNG")||(ext=="gif")||(ext=="GIF")||(ext=="jpeg")||(ext=="JPEG")||(ext=="jpg")||(ext=="JPG")) 
							{
								var split=fileName.split("\\");
								fileName=split[(split.length)-1];
								
									if(fileName.length>104)
										{
											alert("Filename cannot be of length more than 100 characters");
											document.getElementById(id).value=null;
											focusBox(document.getElementById(id));
											return false;
										}
									else
										{
											fn_checkAttachValue(fileName,id);
										}
							}
						else
							{
								alert("Please upload valid attachments only(PNG/GIF/JPEG/JPG)");
								document.getElementById(id).value=null;
								focusBox(document.getElementById(id));
								return false;
							}
				}
	
	}
function fn_checkAttachValue(name,id)
	{
		var specialCharName=/^[a-zA-Z0-9.]*$/;
		
		if(!specialCharName.test(name))
			{
				alert("Attachments cannot have special characters and spaces");
				document.getElementById(id).value=null;
				focusBox(document.getElementById(id));
				return false;
			}
						
		if(!(window.ActiveXObject))
			{
				var size=document.getElementById(id).files[0].size;
				if(size>204800)
					{
						alert("Cannot upload a file of size more than 200KB");
						document.getElementById(id).value=null;
						focusBox(document.getElementById(id));
						return false;
					}		
			}
	}
function retrieveDetails()
{
	resetPatientData();
	/*Disabling Communication hno,street,pin as these fields are enabled in resetPatientData();*/
	document.forms[0].comm_hno.disabled=true;
	document.forms[0].comm_street.disabled=true;
	document.forms[0].comm_pin.disabled=true;
	var wapFamilyNo=readCardData1();

 		document.forms[0].action="./patientDetails.do?actionFlag=retrieveCardDetails&cardNo="+wapFamilyNo;
		document.forms[0].method="post";
 		document.forms[0].submit(); 
	 
}

function validate()
{
	var errMsg='';
	var lField='';

	if (!document.forms[0].card_type[0].checked && !document.forms[0].card_type[1].checked)
	{
		if(errMsg=='')
			errMsg=errMsg+"Select card type <br>";
		if(lField=='')
	        lField='card_type'; 
	}
	
	else if(document.forms[0].card_type[0].checked)
	{
		var jouCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("DJCNo"+i).value=='')	
			{
				jouCardCount++;	
			}
		}
		if(jouCardCount>0)
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter valid Delhi journalist Card No <br>";
			if(lField=='')
				lField='DJCNo0';
			}
	}
	
	else if(document.forms[0].card_type[1].checked)
	{
		var abhaCardCount=0;
		for(var i=0;i<=13;i++)
			{
			if (document.getElementById("DABHANo"+i).value=='')	
			{
				abhaCardCount++;	
			}
		}
		if(abhaCardCount>0)
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter valid ABHA No <br>";
			if(lField=='')
				lField='DABHANo0';
			}
	}
	
	
	if(document.getElementById("fname").value==null || document.getElementById("fname").value=='')
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Name <br>";
		if(lField=='')
	        lField='fname'; 
		}
	if (!document.forms[0].gender[0].checked && !document.forms[0].gender[1].checked)
		{
		if(errMsg=='')
			errMsg=errMsg+"Select Gender <br>";
		if(lField=='')
	        lField='gender'; 
		}
 	if(document.getElementById("dateOfBirth").value==null || document.getElementById("dateOfBirth").value=='')
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select Date Of Birth <br>";
		if(lField=='')
	        lField='dateOfBirth'; 
 		}
 	if(document.getElementById("relation").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select Relationship <br>";
 		if(lField=='')
        lField='relation'; 
 		}
 	/* if(document.getElementById("caste").value==-1)
 	 errMsg=errMsg+"Please Select Caste\n"; */
 	if(document.getElementById("slab").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Click on Retrieve button to retrieve Slab <br>";
		if(lField=='')
 	        lField='RetrieveDetails'; 
 	 	}
 	//pravalika
		if(document.getElementById("diagnosisCategory").value== -1)
			{
			if(errMsg=='')
				errMsg=errMsg+"Select Category <br> ";
	 	 	if(lField=='')
	 	        lField='diagnosisCategory'; 
			}
		if(document.getElementById("dialysis").value === "")
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Diagnosis <br> ";
	 	if(lField=='')
	        lField='dialysis'; 
		}
		//CR 9043 validation for admission details 
		if(document.getElementById("attachment").value==="")
			{
			if(errMsg=='')
				errMsg=errMsg+"Add Attachment <br> ";
	 	 	if(lField=='')
	 	        lField='attachment'; 
			}
		if(document.getElementById("attachment").value==="")
		{
		if(errMsg=='')
			errMsg=errMsg+"Add Attachment <br> ";
 	 	if(lField=='')
 	        lField='attachment'; 
		}
		if(document.getElementById("admissionDate").value==="")
		{
		if(errMsg=='')
			errMsg=errMsg+"Select Admission Date <br> ";
 	 	if(lField=='')
 	        lField='admissionDate'; 
		}
		if(document.getElementById("admissionNote").value==="")
		{
		if(errMsg=='')
			errMsg=errMsg+"Add Admission Notes <br> ";
 	 	if(lField=='')
 	        lField='admissionNote'; 
		}
		var estAmount = document.getElementById("estAmount").value.trim();

		if (estAmount === "") {
		    if (errMsg == '')
		        errMsg = errMsg + "Enter estimated amount <br> ";

		    if (lField == '')
		        lField = 'estAmount';
		}
		else if (/^0\d+/.test(estAmount)) {   
		    if (errMsg == '')
		        errMsg += "Estimated amount should not start with 0 <br>";

		    if (lField == '')
		        lField = 'estAmount';
		}
		else if (parseInt(estAmount, 10) <= 0) {
		    if (errMsg == '')
		        errMsg = errMsg + "Estimated amount should be greater than 0 <br> ";

		    if (lField == '')
		        lField = 'estAmount';
		}
		
 	if(document.getElementById("occupation").value=='')
 		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Designation <br>"; 
		if(lField=='')
	        lField='occupation'; 
 		}
 	if(document.getElementById("contactno").value==null || document.getElementById("contactno").value=='')
 		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Contact No <br>";
		if(lField=='')
	        lField='contactno'; 
 		}
 	if(document.getElementById("maritalStatus").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select Marital Status <br>"; 
		if(lField=='')
	        lField='maritalStatus'; 
 		}
 	if(document.getElementById("hno").value==null || document.getElementById("hno").value=='')
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter House No  in Card Address <br>";
	if(lField=='')
        lField='hno'; 
		}
	if(document.getElementById("street").value==null || document.getElementById("street").value=='')
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Street in Card Address <br>"; 
		if(lField=='')
        lField='street'; 
		}
	if(document.getElementById("state").value==-1)
	{
	if(errMsg=='')
		errMsg=errMsg+"Select State in Card Address <br>";
 	if(lField=='')
        lField='state'; 
	}
	if(document.getElementById("district").value==-1)
		{
		if(errMsg=='')
			errMsg=errMsg+"Select District in Card Address <br>";
	 	if(lField=='')
	        lField='district'; 
		}
	if(document.getElementById("mdl_mcl").value=="Mdl")
		{
		if(document.getElementById("mandal").value==-1)
			{
			if(errMsg=='')
				errMsg=errMsg+"Select Mandal in Card Address <br>";
	 		if(lField=='')
	 	        lField='mandal'; 
			}
		}
	else if (document.getElementById("mdl_mcl").value=="Mpl")
		{
		if(document.getElementById("municipality").value==-1)
			{
			if(errMsg=='')
				errMsg=errMsg+"Select Municipality in Card Address <br>";
	 		if(lField=='')
	 	        lField='municipality'; 
			}
		}
	else
		{
		if(errMsg=='')
			errMsg=errMsg+"Select Mandal/Municipality in Card Address <br>";
		if(lField=='')
	        lField='mdl_mcl'; 
		}
	if(document.getElementById("village").value==-1)
		{
		if(errMsg=='')
			errMsg=errMsg+"Select Village in Card Address <br>";
	 	if(lField=='')
	        lField='village'; 
		}
	var tgGovPatCond=document.getElementById("tgGovPatCond").value;
	if(tgGovPatCond==null || tgGovPatCond=='' || tgGovPatCond==' ' ||
			(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond!='Y'))
		{
			if(document.getElementById("pin").value==null || document.getElementById("pin").value=='')
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter Pin code in Card Address <br>"; 
			if(lField=='')
	        lField='pin'; 
			}
		}
	
	if(document.getElementById("commCheck").value=='N')
		 {
		 if(document.getElementById("comm_hno").value==null || document.getElementById("comm_hno").value=='')
			 {
			 if(errMsg=='')
				errMsg=errMsg+"Enter House No  in Communication Address <br>";
			if(lField=='')
		        lField='comm_hno'; 
			 }
	 	if(document.getElementById("comm_street").value==null || document.getElementById("comm_street").value=='')
	 		{
			if(errMsg=='')
				errMsg=errMsg+"Enter Street in Communication Address <br>"; 
			if(lField=='')
		        lField='comm_street'; 
	 		}
	 	if(document.getElementById("comm_state").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select State in Communication Address <br>";
 	 	if(lField=='')
 	        lField='comm_state'; 
 		}
	 	if(document.getElementById("comm_dist").value==-1)
	 		{
			if(errMsg=='')
				errMsg=errMsg+"Select District in Communication Address <br>";
	 	 	if(lField=='')
	 	        lField='comm_dist'; 
	 		}
	 	if(document.getElementById("comm_mdl_mcl").value==-1)
	 		{
			if(errMsg=='')
				errMsg=errMsg+"Select Mandal/Municipality in Communication Address <br>";
	 		if(lField=='')
	 	        lField='comm_mdl_mcl'; 
	 		}
	 	else if(document.getElementById("comm_mdl_mcl").value=="Mdl")
		{
	 		if(document.getElementById("comm_mandal").value==-1)
	 			{
				if(errMsg=='')
					errMsg=errMsg+"Select Mandal in Communication Address <br>";
	 	 	 	if(lField=='')
	 	        	lField='comm_mandal'; 
	 			}
		}
		else if (document.getElementById("comm_mdl_mcl").value=="Mpl")
		{
			if(document.getElementById("comm_municipality").value==-1)
				{
				if(errMsg=='')
					errMsg=errMsg+"Select Municipality in Communication Address <br> ";
	 			if(lField=='')
	 		        lField='comm_municipality'; 
				}
		}
		if(document.getElementById("comm_village").value==-1)
			{
			if(errMsg=='')
				errMsg=errMsg+"Select Village in Communication Address <br> ";
	 	 	if(lField=='')
	 	        lField='comm_village'; 
			}
		
		if(tgGovPatCond==null || tgGovPatCond=='' || tgGovPatCond==' ' ||
				(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond!='Y'))
			{
				if(document.getElementById("comm_pin").value==null || document.getElementById("comm_pin").value=='')
				{
				if(errMsg=='')
					errMsg=errMsg+"Enter Pin code in Communication Address <br> "; 
				if(lField=='')
			        lField='comm_pin'; 
				}
			}
		
		
	 	}
	var bioRegFlag='${bioRegFlag}';
	if(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond=='Y' && bioRegFlag!=null
			&& bioRegFlag !='' && bioRegFlag!=' ' && bioRegFlag=='Y' )
		{
			var bioHand=document.getElementById("bioHand").value;
			var bioFinger=document.getElementById("bioFinger").value;
			if(bioHand!=null && bioHand!='' && bioHand!=' ')
				{
					if(bioHand=='-1')
						{
							document.getElementById("captured").style.display="none";
							document.getElementById("unCaptured").style.display="";
							alert("Please select the Hand to which the Biometrics need to be Captured.");
							focusFieldView("bioHand");
							return false;
						}
					else if(bioFinger=='-1')
						{
							document.getElementById("captured").style.display="none";
							document.getElementById("unCaptured").style.display="";
							alert("Please select the finger to which the Biometrics need to be Captured.");
							focusFieldView("bioFinger");
							return false;
						}
					else
						{
							var biometricReturnValue=document.getElementById("biometricValue").value;
							if(biometricReturnValue==null || biometricReturnValue=='' || biometricReturnValue==' ')	
								{
									document.getElementById("captured").style.display="none";
									document.getElementById("unCaptured").style.display="";
									alert("Please Capture the Biometrics of Patient.");
									focusFieldView("biometricValue");
									return false;
								}
						}
					
				}
			else
				{
					document.getElementById("captured").style.display="none";
					document.getElementById("unCaptured").style.display="";
					alert("Please select the Hand to which the Biometrics need to be Captured.");
					focusFieldView("bioHand");
					return false;
				}
		}
	if(!errMsg=='')
	{
 		var fr = partial(focusFieldView,lField);
 		jqueryAlertMsg('Patient Registration Page Mandatory Fields',errMsg,fr);
		//focusBox(document.getElementById(lField));
		return false;
	}
  else
	  {
	 	if(checkGenderRelation()!=false)
	 		{
	 			jqueryConfirmMsg('Patient Registration Confirmation','Do you want to register patient?',registerPatientDetails);
	 		}
	 	else
	 		{
	 		return false;
	 		}
	  }
}
function registerPatientDetails()
{
	var wapNo="";
	var familyNo="";
	 if(document.forms[0].card_type[0].checked)
		{
			wapNo=document.forms[0].DJCNo0.value+document.forms[0].DJCNo1.value+document.forms[0].DJCNo2.value+document.forms[0].DJCNo3.value+document.forms[0].DJCNo4.value+document.forms[0].DJCNo5.value
			+document.forms[0].DJCNo6.value+document.forms[0].DJCNo7.value+document.forms[0].DJCNo8.value;
			familyNo=document.forms[0].DJCNo9.value+document.forms[0].DJCNo10.value;
		}
	
	else if(document.forms[0].card_type[1].checked)
		{
			wapNo=document.forms[0].DABHANo0.value+document.forms[0].DABHANo1.value+document.forms[0].DABHANo2.value+document.forms[0].DABHANo3.value+document.forms[0].DABHANo4.value+document.forms[0].DABHANo5.value
			+document.forms[0].DABHANo6.value+document.forms[0].DABHANo7.value+document.forms[0].DABHANo8.value+document.forms[0].DABHANo9.value+document.forms[0].DABHANo10.value+document.forms[0].DABHANo11.value+document.forms[0].DABHANo12.value
			+document.forms[0].DABHANo13.value;
		}
	 
	 if (document.forms[0].card_type[1].checked) {
			wapNo=wapNo.toUpperCase();
			var cardFamilyNo = wapNo;
		} else {
			wapNo=wapNo.toUpperCase();
			var cardFamilyNo=wapNo+"/"+familyNo;
		}
	
	 //var cardFamilyNo=document.getElementById("cardNo").value;
	 if(document.getElementById("cardNo").value==cardFamilyNo)
		{
	   document.getElementById("slab").disabled=false;
	 	//Enabling basic fields on retreiving enrollment data
		document.forms[0].gender[0].disabled=false;
		document.forms[0].gender[1].disabled=false;
		document.getElementById("fname").disabled=false;
		document.getElementById("dateOfBirth").disabled=false;
		document.getElementById("relation").disabled=false;
		document.getElementById("hno").disabled=false;
		document.getElementById("street").disabled=false;
		document.getElementById("state").disabled=false;
		document.getElementById("district").disabled=false;
		document.getElementById("mdl_mcl").disabled=false;
		document.getElementById("mandal").disabled=false;
		document.getElementById("municipality").disabled=false;
		document.getElementById("village").disabled=false;
		
		if(document.getElementById("bioCapture")!=null)
			document.getElementById("bioCapture").className='butdisable';
		
	   document.getElementById("RegisterPatient").className='butdisable';	
	   document.getElementById("RegisterPatient").disabled=true;
	   var tgGovPatCond=document.getElementById("tgGovPatCond").value;
	   document.forms[0].action="./patientDetails.do?actionFlag=registerPatientDetails&tgGovPatCond="+tgGovPatCond+"&wapNo="+wapNo+"&familyNo="+familyNo;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
		}
	else
		{
		jqueryAlertMsg('Patient Registration Page','Card  No is changed.Click on Retrieve Details to get patient details');
		}
	     
}


function disableScreen()
{
	var test='${newBornBabyFlag}';
	
	if(test=='N')
		{
		
			/* document.forms[0].card_type[2].style.display='none';
			document.getElementById("journalist").style.display='none';
			document.forms[0].card_type[4].style.display='none';//Chandana - 8442 - Changed this to 4 as i am using 3 for abha number
		//	document.getElementById("newBorn").style.display='none';
		//	document.getElementById("newBornInstruct").style.display='none'; */
		}
	
	var disableFlg=null;
	disableFlg=document.getElementById("disableFlag").value;
	addrEnable=document.getElementById("addrEnable").value;
	var val=null;
	var val2=null;
	if(disableFlg=='Y')
		{
		val=true; 
		val2=false;
		if(document.getElementById("errMsg").value!="")
			{
			jqueryErrorMsg('Health Card Validation',document.getElementById("errMsg").value);
			}
		}
	if(document.getElementById("cardNo").value!=null || document.getElementById("cardNo").value!="")
		{
		var cardFamilyNo=document.getElementById("cardNo").value;
		//cardFamilyNo.replace("/","");
		var cardNo=cardFamilyNo.replace("/","").split("");

		for(var i=0;i<cardNo.length;i++)
		{
			
			 if(document.forms[0].card_type[0].checked)
				{
					document.getElementById('DJCNo'+i).value=cardNo[i];
					document.getElementById("label_djoucard").style.display="";
				//	document.getElementById("label_newBorncard").style.display='none';
					document.getElementById("label_dabhaCard").style.display="none";//Chandana - 8442 - Added this to hide the abha number text filed if the cardtype is 0
				}
			
			else if(document.forms[0].card_type[1].checked)//Chandana - 8442 - Added this for abha number search
			{
				document.getElementById('DABHANo'+i).value=cardNo[i];
			
				document.getElementById("label_djoucard").style.display="none";
			//	document.getElementById("label_newBorncard").style.display='none';
				document.getElementById("label_dabhaCard").style.display="";
			}
		}
		}
	
	if(disableFlg=='N')
		{
		val=false;
		val2=false;
		
		if(document.getElementById("mdl_mcl").value == 'Mdl')
		{
		document.getElementById("mandaltab").style.display="";
		document.getElementById("municipalitytab").style.display="none";
		document.getElementById("mandallist").style.display="";
		document.getElementById("municipalitylist").style.display="none";
		}
		else if(document.getElementById("mdl_mcl").value == 'Mpl')
	 	{
		document.getElementById("mandaltab").style.display="none";
		document.getElementById("municipalitytab").style.display="";
		document.getElementById("mandallist").style.display="none";
		document.getElementById("municipalitylist").style.display="";
	 	}
		if(!document.getElementById("telephonicId").value == '')
			{
			if(document.getElementById('comm_mdl_mcl').value == 'Mdl')
			{
			document.getElementById("munciplcommtab").style.display='none';
            document.getElementById("comm_municipaltd").style.display='none';
            document.getElementById("mandalcommtab").style.display='';
            document.getElementById("comm_mandaltd").style.display='';
			}
		else if(document.getElementById('comm_mdl_mcl').value == 'Mpl')
			{
			document.getElementById("munciplcommtab").style.display='';
        	document.getElementById("comm_municipaltd").style.display='';
       	 	document.getElementById("mandalcommtab").style.display='none';
        	document.getElementById("comm_mandaltd").style.display='none';
			}
			}
		populateAge(document.getElementById("dateOfBirth"));
		}
	var elLength = document.InPatientForm.elements.length; 
    for (var i=0; i<elLength; i++)
    {
    	var type = InPatientForm.elements[i].type; 
    	 if(type=="text" || type=="button" || type=="checkbox" || type=="radio" || type=="select-one")
        {
        	InPatientForm.elements[i].disabled=val;
        	if(type=="button" && val==true)
        		InPatientForm.elements[i].className='butdisable';
        	else if(type=="button" && val==false)
        		InPatientForm.elements[i].className='but';
      	
        }
    }
    document.getElementById('head').disabled=val2;
    document.forms[0].card_type[0].disabled=val2;
    document.forms[0].card_type[1].disabled=val2;
   
    document.getElementById("RetrieveDetails").className='but';
    document.getElementById("RetrieveDetails").disabled=val2;
    
		
    for(var i=0;i<=10;i++)
    {
        document.getElementById('DJCNo'+i).disabled=val2;
       // document.getElementById('NBNo'+i).disabled=val2;
    }
    for(var i=0;i<=13;i++){
    	document.getElementById('DABHANo'+i).disabled=val2;
    }
    
    
	    	document.getElementById("dtTime").disabled=true;
	    	document.getElementById('years').disabled=true;
	    	document.getElementById('months').disabled=true;
	    	document.getElementById('days').disabled=true;
	    	document.getElementById("slab").disabled=true;
	    	
	    	//Disabling basic fields on retreiving enrollment data
	    	document.forms[0].gender[0].disabled=true;
	    	document.forms[0].gender[1].disabled=true;
	    	document.getElementById("fname").disabled=true;
	    	document.getElementById("dateOfBirth").disabled=true;
	    	document.getElementById("relation").disabled=true;
			//document.getElementById("contactno").disabled=true;
    	
    document.getElementById("hno").disabled=true;
	document.getElementById("street").disabled=true;
	document.getElementById("state").disabled=true;
	document.getElementById("district").disabled=true;
	document.getElementById("mdl_mcl").disabled=true;
	document.getElementById("mandal").disabled=true;
	document.getElementById("municipality").disabled=true;
	document.getElementById("village").disabled=true;    
	
	
	if(addrEnable=='Y')
		{
			document.getElementById("hno").disabled=false;
			document.getElementById("street").disabled=false;
			document.getElementById("state").disabled=false;
			document.getElementById("district").disabled=false;
			document.getElementById("mdl_mcl").disabled=false;
			document.getElementById("mandal").disabled=false;
			document.getElementById("municipality").disabled=false;
			document.getElementById("village").disabled=false;
		}
}

function validateDate(arg1,input)
{
	var entered = input.value;
	entered = entered.split("-");
	var cmth = parseInt(entered[1],10); 
	var cdy = parseInt(entered[0],10);
	var cyr = parseInt(entered[2],10);
	if(isNaN(cyr))
	{
		input.value="";
		jqueryAlertMsg('Date Validation','Select '+arg1);
	}
	else
	{
	var DoB=""+(cmth)+"/"+ cdy +"/"+ cyr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(DoB>=DoC)
		{
		input.value="";
		jqueryAlertMsg('Date Validation',arg1+" should be less than Today's Date");
		}
	}
}

function resetData()
{
		$('.tgGovCond').css('display','block');
		
		if(document.getElementById("bioHand")!=null)
			document.getElementById("bioHand").value="";
		if(document.getElementById("bioFinger")!=null)
			document.getElementById("bioFinger").value="";
		
		document.getElementById("tgGovPatCond").value="N";
		document.getElementById("disableFlag").value="Y";
		disableScreen();
		document.forms[0].card_type[0].checked=false;
		document.forms[0].card_type[1].checked=false;
		
		document.getElementById("head").checked=false;
		/* document.getElementById("label_empcard").style.display="";
		document.getElementById("label_pencard").style.display="none"; */
		document.getElementById("label_djoucard").style.display="";
		document.getElementById("label_dabhaCard").style.display="none";
		
		for(var i=0;i<11;i++)
			{
			document.getElementById("DJCNo"+i).value="";
			
			} 
		for(var i=0;i<=13;i++){
			document.getElementById("DABHANo"+i).value="";
		}
		resetPatientData();
		document.getElementById("hno").disabled=true;
		document.getElementById("street").disabled=true;
		document.getElementById("state").disabled=true;
		document.getElementById("district").disabled=true;
		document.getElementById("mdl_mcl").disabled=true;
		document.getElementById("mandal").disabled=true;
		document.getElementById("municipality").disabled=true;
		document.getElementById("village").disabled=true;
}
function resetPatientData()
{
	document.getElementById("dummyPhoto").style.display="";
	document.getElementById("patPhoto").style.display="none";
	//Chandana - 8326 
	var eKYCTr = document.getElementById("eKYCTr");
	if (eKYCTr)
	    eKYCTr.style.visibility = "hidden";
	var abhaRow = document.getElementById("abhaRow");
	 if (abhaRow) 
		  abhaRow.style.display = "none";
	document.forms[0].gender[0].checked=false;
	document.forms[0].gender[1].checked=false;
	//document.getElementById("child").checked=false;
	//document.getElementById("cardIssueDt").value="";
	document.getElementById("fname").value="";
	document.getElementById("dateOfBirth").value="";
	document.getElementById("years").value="";
	document.getElementById("months").value="";
	document.getElementById("days").value="";
	document.getElementById("relation").value="-1";
	//document.getElementById("caste").value="-1";
	document.getElementById("slab").value="-1";
	document.getElementById("maritalStatus").value="-1";
	document.getElementById("occupation").value="";
	document.getElementById("contactno").value="";
	document.getElementById("hno").value="";
	document.getElementById("street").value="";
	document.getElementById("state").value="-1";
	document.getElementById("district").options.length=1;
	document.getElementById("mdl_mcl").value="-1";
	document.getElementById("mandal").options.length=1;
	document.getElementById("municipality").options.length=1;
	document.getElementById("village").options.length=1;
	document.getElementById("pin").value="";
	document.getElementById("commCheck").checked=false;
	document.getElementById("comm_hno").value="";
	document.getElementById("comm_street").value="";
	document.getElementById("comm_state").value="-1";
	document.getElementById("comm_dist").options.length=1;
	document.getElementById("comm_mdl_mcl").value="-1";
	document.getElementById("comm_mandal").options.length=1;
	document.getElementById("comm_municipality").options.length=1;
	document.getElementById("comm_village").options.length=1;
	document.getElementById("comm_pin").value="";
	
	document.getElementById("same_state").style.display="none";
	document.getElementById("comm_statetd").style.display="";
	
	document.getElementById("same_dist").style.display="none";
	document.getElementById("comm_disttd").style.display="";
	
	document.getElementById("same_mdl_mcltd").style.display='none';
	document.getElementById("comm_mdl_mcltd").style.display='';
	
	document.getElementById("mandaltab").style.display='';
	document.getElementById("mandallist").style.display='';
	document.getElementById("municipalitytab").style.display='none';
	document.getElementById("municipalitylist").style.display='none';
		
	document.getElementById("mandalcommtab").style.display='';
	document.getElementById("munciplcommtab").style.display='none';
	document.getElementById("same_mandal").style.display="none";
	document.getElementById("comm_mandaltd").style.display="";
	document.getElementById("comm_municipaltd").style.display='none';
	document.getElementById("same_municipalitytd").style.display='none';
	
	document.getElementById("same_villagetd").style.display="none";
	document.getElementById("comm_villagetd").style.display="";
	
	document.getElementById("village").options.length = 1;
	document.getElementById("comm_village").options.length = 1;

	document.getElementById("commCheck").value='N';
	document.getElementById("diagnosisCategory").value=-1;
	document.getElementById("dialysis").value='';
	document.getElementById("admissionDate").value='';
	document.getElementById("admissionNote").value='';
	document.getElementById("estAmount").value='';
	document.getElementById("attachment").value='';
	}

function viewTelephonicInfo(){
	window.open('/<%=context%>/patientDetails.do?actionFlag=ViewTelephonicDtls&telephonicId='+document.forms[0].telephonicId.value,'TelephonicRegisteredDetails','scrollbars=1,left=20,top=20,width=1000,height=650');
}
function fn_chkTGGovCond()
	{
		if(document.getElementById("tgGovPatCond")!=null && document.getElementById("bioTable")!=null)
			{
				var tgGovPatCond=document.getElementById("tgGovPatCond").value;
				if(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond=='Y')
					{
						$('.tgGovCond').css('display','none');
						document.getElementById("bioTable").style.display="";
					}
				else
					{
						document.getElementById("bioTable").style.display="none";
						$('.tgGovCond').css('display','block');
					}
			}
		
		
	}
var $ = jQuery.noConflict();

function fn_aadharAuthModal(){
	var userName = document.getElementById("userName").value;
	document.getElementById("userNamePlaceholder").innerText = userName;
	$("#viewekycModal").modal();
}



function validateTodayDate(arg1,input){

	var fr = partial(focusBox,input);	
    var entered = input.value;
	entered = entered.split("-");
	var cmth = parseInt(entered[1],10); 
	var cdy = parseInt(entered[0],10);
	var cyr = parseInt(entered[2],10);
	if(isNaN(cyr))
	{
		input.value="";
		//alert("Please Select "+arg1);
		jqueryErrorMsg('Date Validation',"Please Select "+arg1,fr);
	}
	else
	{
	var billDate=""+(cmth)+"/"+ cdy +"/"+ cyr;
	billDate=Date.parse(billDate);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var todayDate=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	todayDate=Date.parse(todayDate);
	}

}
function validateBackSpace(e)
{
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;  
    if( code== 8 )
    {
      e.returnValue=false;
 	 }
}
</script>
</head>
<body onload="javascript:fn_chkTGGovCond();">
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" name="InPatientForm" enctype = "multipart/form-data">
<html:hidden name="patientForm" property="tgGovPatCond" styleId="tgGovPatCond" />
<html:hidden name="patientForm" property="biometricValue"  styleId="biometricValue" ></html:hidden>
<table width="95%" style="margin:0 auto" class="tb" cellspacing="2" cellpadding="2">
<tr>

	<c:if test="${inActiveStatusFlag eq 'Y' }">
	<td style="height:400px;text-align:center;vertical-align:center;" class="labelheading1 tbcellCss">	
		<b>${inActiveStatusMsg}</b>
	</td>
	</c:if>
	<c:if  test="${inActiveStatusFlag ne 'Y' }">
	<td>
		<table class="tbheader">
		<tr><th><b><fmt:message key="EHF.Title.PatientRegistration"/></b></th></tr>
		</table>
		<table width="100%">
		<tr><td class="labelheading1 tbcellCss" width="15%"><b><fmt:message key="EHF.Cardtype"/></b></td>
		<td class="tbcellBorder"  width="55%">
			 
			<html:radio name="patientForm" property="card_type" value="DJ" styleId="card_type" onclick="enable_cardType1(this.checked)" title="Delhi journalist Card No"/><b><label id="delhijournalist"><fmt:message key="EHF.JournalistCardNo"/></label></b>
			<html:radio name="patientForm" property="card_type" value="DAB" styleId="card_type" onclick="enable_cardType1(this.checked)" title="ABHA Card No"/><b>ABHA Card No</b>
			<%-- <html:radio name="patientForm" property="card_type" value="NB" styleId="card_type" onclick="enable_cardType(this.checked)" title="New Born Baby"/><b><label id="newBorn"><fmt:message key="EHF.NewBornBabyCardNo"/></label></b> --%>
			</td>
			
		  
		  <td width="20%">&nbsp;</td>
			<td width="10%">&nbsp;</td>
		  </tr>

		<tr><td class="tbcellBorder">
		<!-- <input type="checkbox" name="head" value="head" id="head" onclick="checkFamilyHead(this.checked)" /> -->
		<html:checkbox name="patientForm" property="head" styleId="head"  onclick="checkFamilyHead(this.checked)" title="Check Family Head"></html:checkbox>
		<b><fmt:message key="EHF.FamilyHead"/></b> 
		</td>
		
		<td id="label_djoucard" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<!-- <input type="text" name="JCNoT"  id="JCNoT" maxlength="1"  style="width:15px"  onkeydown="validateBackSpace(event)" readonly="true" value="T"/> -->
		<input type="text" name="DJCNo0"  id="DJCNo0" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo1"  id="DJCNo1" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo2"  id="DJCNo2" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo3"  id="DJCNo3" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo4"  id="DJCNo4" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo5"  id="DJCNo5" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo6"  id="DJCNo6" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo7"  id="DJCNo7" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo8"  id="DJCNo8" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		/
		<input type="text" name="DJCNo9"  id="DJCNo9" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DJCNo10" id="DJCNo10" maxlength="1"  style="width:15px" onkeyup="validateAlphaNum('Journalist Card No',this,'Health Card No')"/>
		</td>
		
		<!-- Chandana - 8442 - Added the below text fields for abha number-->
		<td id="label_dabhaCard" style="display:none" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<input type="text" name="DABHANo0"  id="DABHANo0" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo1"  id="DABHANo1" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo2"  id="DABHANo2" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo3"  id="DABHANo3" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo4"  id="DABHANo4" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo5"  id="DABHANo5" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo6"  id="DABHANo6" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo7"  id="DABHANo7" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo8"  id="DABHANo8" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo9"  id="DABHANo9" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo10"  id="DABHANo10" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo11"  id="DABHANo11" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo12"  id="DABHANo12" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="DABHANo13"  id="DABHANo13" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<!-- <input type="text" name="ABHANo14"  id="ABHANo14" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/> -->
		</td>
		<td> <button class="but"  type="button" id="RetrieveDetails" onclick="javascript:retrieveDetails()">Retrieve</button>
		<!--  input type="button" name="RetrieveDetails" id="RetrieveDetails" value="RetrieveDetails" onclick="retrieveDetails()" --></td>
		<logic:equal  name="patientForm" property="telephonicReg" value="Yes">
		<td><a href="javascript:viewTelephonicInfo()">View Telephonic Intimation Details</a></td>
		</logic:equal>
		</tr>
		</table>
		<!-- 8326 - Added for Abha Registration by Chandana -->
         <logic:equal name="patientForm" property="ekycFlag" value="N">
    		<div style="display:flex; align-items: center;" id="eKYCTr">
        		<h4 style="color:red; margin-right: 15px;">Do you want to generate ABHA</h4>
        		<label style="margin-right:10px;"><input type="radio" id="abhaOption" name="abhaOption" value="YES" onclick="fn_aadharAuthModal()">Yes</label>
        		<label><input type="radio" name="abhaOption" value="NO">No</label>
    		</div>
		</logic:equal>
		<!-- <br> -->
		<table class="tbheader">
		<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
		</table>

		<table class="contentTable">
		<tr>
		<%-- <td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CardIssueDate"/></b></td> --%>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Name"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b> <font color="red">*</font>
			</td>	
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MaritalStatus"/></b> <font color="red">*</font></td>
		<td>&nbsp;</td>	
		</tr>
		<tr>
		<%-- <td class="tbcellBorder"> <html:text name="patientForm" property="cardIssueDt" styleId="cardIssueDt" title="Select Card Issue Date" style="WIDTH: 11em" onchange="validateCardIssueDate('Card Issue Date',this)" onkeydown="validateBackSpace(event)" readonly="true"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('cardIssueDt','330','50')" title="Calendar"></img> --></td> --%>
		<td class="tbcellBorder"> <html:text name="patientForm" property="fname" styleId="fname" title="Enter Name" maxlength="100" style="WIDTH: 11em" onchange="validateAlphaSpace('Name',this,'')"/></td>
		<td class="tbcellBorder"><html:radio name="patientForm" property="gender" value="M" title="Male" styleId="gender" /><b><fmt:message key="EHF.Male"/></b> 
			<html:radio name="patientForm" property="gender" value="F" title="Female" styleId="gender"/><b><fmt:message key="EHF.Female"/></b></td><%-- <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;<html:checkbox name="patientForm" property="child" styleId="child" onclick="fn_chkChildYN(this.checked)"><fmt:message key="EHF.Child"/></html:checkbox> </td>  --%>
		<td class="tbcellBorder"><html:select name="patientForm" property="maritalStatus" styleId="maritalStatus" title="Select Marital Status" style="WIDTH: 12em" onmouseover="addTooltip('maritalStatus')">
				<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
				<html:options property="ID" collection="maritalStatusList" labelProperty="VALUE"/>
			</html:select></td>
		<td style="text-align:center" rowspan="5" class="tbcellBorder" id="patPhoto">
		<logic:notEmpty name="patientForm" property="photoUrl">
		<img  id="patImage" src="common/showDocument.jsp" width="120" height="110"  onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','120','110')"/>
		</logic:notEmpty>
		<logic:empty name="patientForm" property="photoUrl">
		<img src="images/photonot.gif" width="120" height="110" alt="NO DATA"/>
		</logic:empty></td>
		<td style="text-align:center;display:none" rowspan="5" class="tbcellBorder" id="dummyPhoto">
		<img src="images/photonot.gif" width="120" height="110" alt="NO DATA"/>
		</td>
		<!-- <td style="text-align:center;display:none" rowspan="5" class="tbcellBorder" id="childPhotoTd">
		<div style="margin-bottom:2px">
		<img src="images/photonot.gif" width="120" height="110" alt="NO DATA"/>
		</div>
		<div style="display:inline-flex">
		<input disabled="disabled" type="file" name="childPhoto" id="childPhoto" onchange="javascript:checkExtension(id)" style="width:130%"/>
		<font color="red"><i class="fa fa-upload" disabled="disabled" style="margin-top:3px;cursor:pointer" title="Click to upload" onclick="javascript:uploadBabyPhoto('childPhoto')"></i></font>
		</div>
		</td> -->
		<td rowspan="15" valign="top" class="tbcellCss" style="text-align:center">
		<br><br><br><font color="327ACC" size="3px">Instructions</font><br><br><br><br>
		<p>Please select valid health card type.</p>
		<br><br><p>Enter valid health card no and click on Retrieve button .</p>
		<br><br><p>Health card details will be populated for patient to get registered.</p>
		<br><br><p id="newBornInstruct">For New Born Baby,consent Documents should be provided at Claim Level.</p>
		</td> 
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b> </td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b> <font color="red">*</font></td> 

		</tr>
		<tr>
		<td class="tbcellBorder"><html:text name="patientForm" property="dateOfBirth" styleId="dateOfBirth" title="Select Date Of Birth" style="WIDTH: 11em" onchange="populateAge(this)" onkeydown="validateBackSpace(event)" readonly="true"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('dateOfBirth','260','60')" title="Calendar"></img> --></td>
		<td class="tbcellBorder"><html:text name="patientForm" property="years" styleId="years" title="Years"  style="width:22px"  maxlength="3" disabled="true"/>&nbsp;<b><fmt:message key="EHF.Years"/></b>
		<html:text name="patientForm" property="months" styleId="months"  style="width:15px" title="Months" maxlength="2" disabled="true"/><b><fmt:message key="EHF.Months"/></b>
		<html:text name="patientForm" property="days" styleId="days"  style="width:15px" title="Days" maxlength="2" disabled="true"/><b><fmt:message key="EHF.Days"/></b>
		</td>
		<td class="tbcellBorder"><html:select name="patientForm" property="relation" styleId="relation" title="Select Relationship" style="WIDTH: 12em" onmouseover="addTooltip('relation')">
			<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:options property="ID" collection="relationList" labelProperty="VALUE"/> 
			</html:select></td> 
		</tr>
		<tr>
		<%-- <td><b><fmt:message key="EHF.Caste"/></b> <font color="red">*</font></td> --%>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b> <font color="red">*</font></td> 
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Slab"/></b> <font color="red">*</font></td>
		</tr>
		<tr>
		<%-- <td><html:select name="patientForm" property="caste" styleId="caste" title="Select Caste" style="WIDTH: 130px" onmouseover="addTooltip('caste')">
				<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
				<html:options property="ID" collection="castesList" labelProperty="VALUE"/>
			</html:select></td> --%>
		<%-- <td class="tbcellBorder"><html:select name="patientForm" property="occupation" styleId="occupation" title="Select Designation" style="WIDTH: 12em" onmouseover="addTooltip('occupation')">
			<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:options property="ID" collection="occupationList" labelProperty="VALUE"/> 
			</html:select>
		</td> --%>
			
		<td class="tbcellBorder"><html:text name="patientForm" property="occupation" styleId="occupation" maxlength="100" title="Enter Designation" onchange="validateAlphaSpace('Designation',this,'Designation')"/></td>
		<td class="tbcellBorder"><html:text name="patientForm" property="contactno" styleId="contactno" maxlength="10" title="Enter Contact No" onchange="validateMobile(this)" readonly="true"/></td> 
		<td class="tbcellBorder">
		<html:select name="patientForm" property="slab" styleId="slab" title="Select Slab" style="WIDTH: 12em" onmouseover="addTooltip('slab')" onkeydown="validateBackSpace(event)">
					<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
					<!--<html:option value="A"><fmt:message key="EHF.GeneralWard"/></html:option>-->
					<html:option value="S"><fmt:message key="EHF.SemiPrivateWard"/></html:option>
					<html:option value="P"><fmt:message key="EHF.PrivateWard"/></html:option>
					</html:select></td>
		</tr>
		<!-- Chandana - 8326 - Added this to show the ABHA Address -->
		<logic:equal name="patientForm" property="ekycFlag" value="Y">
			  <tr id="abhaRow">
				<td class="labelheading1 tbcellCss"><b>ABHA Number</b> <font color="red">*</font></td>
				<td class="tbcellBorder" id="abhaField"><html:text name="patientForm" property="abhaId" styleId="abhaId" readonly="true"/></td>
			</tr>
		</logic:equal>

		<tr><td colspan="5"><font size="2px"><b><fmt:message key="EHF.CardAddress"/></b></font></td>
		</tr>
		<tr> 
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HouseNo"/></b> <font color="red">*</font></td> 
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>State</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b> <font color="red">*</font></td>
		</tr>
		<tr> 
		<td class="tbcellBorder"><html:text name="patientForm" property="hno" styleId="hno" maxlength="100" style="WIDTH: 11em" title="Enter Card House No" onchange="validateAddress(this,'Card Address House No')" /> </td>
		<td class="tbcellBorder"><html:text name="patientForm" property="street" styleId="street" maxlength="100" title="Enter Card Street"  style="WIDTH: 11em" onchange="validateAddress(this,'Card Address Street')" /> </td>
		<td class="tbcellBorder"><html:select name="patientForm" property="state" styleId="state" title="Select Card State" style="WIDTH: 12em" onmouseover="addTooltip('state')" onchange="stateSelected(1)">
					<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
					<html:options property="ID" collection="stateList" labelProperty="VALUE"/>
			</html:select></td>
		<td class="tbcellBorder"><html:select name="patientForm" property="district" styleId="district" title="Select Card District" style="WIDTH: 12em" onchange="resetDist(1)" onkeypress="resetDist(1)" onmouseover="addTooltip('district')">
					<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
					<logic:notEmpty name="districtList">
					<html:options property="ID" collection="districtList" labelProperty="VALUE"/>
					</logic:notEmpty>
			</html:select></td>
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b> <font color="red">*</font></td> 
		<td id="mandaltab" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mandal"/></b> <font color="red">*</font></td> 
		<td style="display:none" id="municipalitytab" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Municipality"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><p style="float:left;"><b><fmt:message key="EHF.Pin"/></b></p> <p class="tgGovCond" style="float:left;"><font color="red">*</font></p></td>
		</tr>
		<tr>
		<td class="tbcellBorder"><html:select name="patientForm" property="mdl_mcl" styleId="mdl_mcl" style="WIDTH: 12em" title="Select Card Mandal/Municipality" onchange="distSelected(1)" onkeypress="distSelected(1)" onmouseover="addTooltip('mdl_mcl')">
			<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:option value="Mdl"><fmt:message key="EHF.Mandal"/></html:option>
			<html:option value="Mpl"><fmt:message key="EHF.Municipality"/></html:option>
		</html:select></td>
		<td id="mandallist" class="tbcellBorder"><html:select name="patientForm" property="mandal" styleId="mandal" style="WIDTH: 12em" title="Select Card Mandal" onchange="mandalSelected(1,'Mdl')" onkeypress="mandalSelected(1,'Mdl')" onmouseover="addTooltip('mandal')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<logic:notEmpty name="mdlList">
		<html:options property="ID" collection="mdlList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select></td> 
		<td style="display:none" id="municipalitylist" class="tbcellBorder"><html:select name="patientForm" property="municipality" styleId="municipality" style="WIDTH: 12em" title="Select Card Municipality" onchange="mandalSelected(1,'Mpl')" onkeypress="mandalSelected(1,'Mpl')" onmouseover="addTooltip('municipality')">
		<html:option value="-1" ><fmt:message key="EHF.Select"/></html:option>
		<logic:notEmpty name="mplList">
		<html:options property="ID" collection="mplList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select></td> 
		<td class="tbcellBorder"><html:select name="patientForm" property="village" styleId="village" style="WIDTH: 12em" title="Select Card Village" onmouseover="addTooltip('village')"  onchange="villageSelected(1)" onkeypress="villageSelected(1)">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<logic:notEmpty name="villList">
		<html:options property="ID" collection="villList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select></td> 
		<td class="tbcellBorder"><html:text name="patientForm"  property="pin" styleId="pin" maxlength="6" onchange="validatePin(this),pinChanged(1)" title="Enter Card Pin code" style="WIDTH: 11em"/></td>
		</tr>
		<tr><td colspan="5"><font size="2px"><b><fmt:message key="EHF.CommunicationAddress"/></b></font></td></tr>
		<tr><td colspan="5"><b><fmt:message key="EHF.CheckSameAddress"/></b>
		<html:checkbox name="patientForm" property="commCheck" value="N" styleId="commCheck" onclick="sameAddr(this.checked)" title="Same Address Check"/></td>
		</tr>

		<tr> 
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HouseNo"/></b> <font color="red">*</font></td> 
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>State</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b> <font color="red">*</font></td>
		</tr>
		<tr> 
		<td class="tbcellBorder"><html:text name="patientForm" property="comm_hno" styleId="comm_hno" maxlength="100" title="Enter Communication House No" style="WIDTH: 11em" onchange="validateAddress(this,'Communication Address House No')"/> </td>
		<td class="tbcellBorder"><html:text name="patientForm" property="comm_street" styleId="comm_street" maxlength="100" title="Enter Communication Street" style="WIDTH: 11em" onchange="validateAddress(this,'Communication Address Street')"/> </td>
		<td style='display:none'id='same_state' class="tbcellBorder"><input type="text" name="same_state" id="same_state" title="Communication State"/></td>
		<td id='comm_statetd' class="tbcellBorder"><html:select name="patientForm" property="comm_state" styleId="comm_state" style="WIDTH: 12em" title="Select Communication State" onmouseover="addTooltip('comm_state')" onchange="stateSelected(2)">
					<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
					<html:options property="ID" collection="stateList" labelProperty="VALUE"/>
			</html:select></td>
		<td style='display:none'id='same_dist' class="tbcellBorder"><input type="text" name="same_dist" id="same_dist" title="Communication District"/></td>
		<td id='comm_disttd' class="tbcellBorder"><html:select name="patientForm" property="comm_dist" styleId="comm_dist" style="WIDTH: 12em" title="Select Communication District" onchange="resetDist(2)" onkeypress="resetDist(2)" onmouseover="addTooltip('comm_dist')">
					<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
					<logic:notEmpty name="cdistrictList">
					<html:options property="ID" collection="cdistrictList" labelProperty="VALUE"/>
					</logic:notEmpty>
			</html:select></td>
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b> <font color="red">*</font></td>  
		<td id="mandalcommtab" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mandal"/></b> <font color="red">*</font></td>
		<td style="display:none" id="munciplcommtab" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Municipality"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><p style="float:left"><b><fmt:message key="EHF.Pin"/></b> </p><p style="float:left" class="tgGovCond"><font color="red">*</font></p></td>
		</tr>
		<tr>
		<td style='display:none' id='same_mdl_mcltd' class="tbcellBorder"><input type="text" name="same_mdl_mcl" id="same_mdl_mcl" style="WIDTH: 11em" title="Communication Mandal/Municipality"/></td>
			<td id='comm_mdl_mcltd'  class="tbcellBorder"><html:select name="patientForm" property="comm_mdl_mcl" styleId="comm_mdl_mcl" style="WIDTH: 12em" title="Select Communication Mandal/Municipality" onchange="distSelected(2)" onkeypress="distSelected(2)" onmouseover="addTooltip('comm_mdl_mcl')">
			<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:option value="Mdl"><fmt:message key="EHF.Mandal"/></html:option>
			<html:option value="Mpl"><fmt:message key="EHF.Municipality"/></html:option>
		</html:select></td> 
		<td style='display:none'id='same_mandal' class="tbcellBorder"><input type="text" name="same_mandal" id="same_mandal" style="WIDTH: 11em" title="Communication Mandal"/></td>
			<td id='comm_mandaltd' class="tbcellBorder"><html:select name="patientForm" property="comm_mandal" styleId="comm_mandal" style="WIDTH: 12em" title="Select Communication Mandal" onchange="mandalSelected(2,'Mdl')" onkeypress="mandalSelected(2,'Mdl')" onmouseover="addTooltip('comm_mandal')">
			<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<logic:notEmpty name="cmdlList">
			<html:options property="ID" collection="cmdlList" labelProperty="VALUE"/>
			</logic:notEmpty>
			</html:select> </td>
		<td style='display:none'id='same_municipalitytd' class="tbcellBorder"><input type="text" name="same_municipality" id="same_municipality" style="WIDTH: 11em" title="Communication Municipality"/></td>
		<td style="display:none" id="comm_municipaltd" class="tbcellBorder"><html:select name="patientForm" property="comm_municipality" styleId="comm_municipality" style="WIDTH: 12em" title="Select Communication Municipality" onchange="mandalSelected(2,'Mpl')"  onkeypress="mandalSelected(2,'Mpl')" onmouseover="addTooltip('comm_municipality')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<logic:notEmpty name="cmplList">
		<html:options property="ID" collection="cmplList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select></td> 
		<td style='display:none'id='same_villagetd' class="tbcellBorder"><input type="text" name="same_village" id="same_village" style="WIDTH: 11em" title="Communication Village"/></td> 
		<td id='comm_villagetd' class="tbcellBorder"><html:select name="patientForm" property="comm_village" styleId="comm_village" style="WIDTH: 12em" title="Select Communication Village" onmouseover="addTooltip('comm_village')" onchange="villageSelected(2)" onkeypress="villageSelected(2)">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<logic:notEmpty name="cvillList">
		<html:options property="ID" collection="cvillList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select> </td>
		<td class="tbcellBorder"><html:text name="patientForm" property="comm_pin" styleId="comm_pin" maxlength="6" onchange="validatePin(this),pinChanged(2)" title="Enter Communication Pin code" style="WIDTH: 11em"/> </td>
		</tr>
		</table>
		<!-- Added by Srikalyan for BioMetric Registration of TG Patients in Government Hospitals -->
		<c:if test="${bioRegFlag eq 'Y' }">
			<div id="bioTable" style="display:none;">
				<table class="tbheader">
					<tr>
						<td>
							<b><fmt:message key="EHF.Title.BioRegistration"/></b>
						</td>
					</tr>
				</table>
				<table  class="contentTable" style="width:100%;">
					<tr >
						<td class="labelheading1 tbcellCss" style="width:25% !important">
							<b>
								<fmt:message key="EHF.Bio.HandType"/>
							</b>
							<font color="red">*</font>
						</td>
						<td class="labelheading1 tbcellCss" style="width:10% !important">
							<html:select style="width:150px" disabled="false" name="patientForm" property="bioHand" styleId="bioHand" title="Please select the Hand to which Biometrics needs to be Captured" >
								<html:option value="-1" >Select</html:option>
								<html:option value="Left Hand" >Left Hand</html:option>
								<html:option value="Right Hand" >Right Hand</html:option>
							</html:select>
						</td>
						<td class="labelheading1 tbcellCss" style="width:25% !important">
							<b>
								<fmt:message key="EHF.Bio.FingerType"/>
							</b>
							<font color="red">*</font>
						</td>
						 
						<td class="labelheading1 tbcellCss" style="width:10% !important" >
							<html:select style="width:150px" disabled="false" name="patientForm" property="bioFinger" styleId="bioFinger" title="Please select the finger to which Biometrics needs to be Captured" >
								<html:option value="-1" >Select</html:option>
									<c:if test="${bioFingerOptions ne null}">
										<c:if test="${fn:length(bioFingerOptions)> 0}">
											<html:options property="ID" labelProperty="VALUE" collection="bioFingerOptions" />
										</c:if>
									</c:if>
							</html:select>
						</td>
						<td class="labelheading1 tbcellCss" style="width:30% !important" align="center">
							<div id="unCaptured" style="float:left;margin-left:15px !important;"><i style="color:red" class="fa fa-2x fa-exclamation-circle"></i></div>
							<div id="captured"  style="display:none;float:left;margin-left:15px !important;" ><i style="color:green" class="fa fa-2x fa-check"></i></div>
							<button class="but" style="float:left;margin-left:5px !important;" type="button" title="Click to Capture Biometric Details" onclick="javascript:fn_captureBiometrics()" name="bioCapture" id="bioCapture">
								<fmt:message key="EHF.Bio.Cap"/>
							</button>
						</td>
					</tr> 
				</table>
			</div>	
		</c:if>	
		<!-- End by Srikalyan for TG Patients Biometric Registrations -->	
		<table class="tbheader">
		<tr><td><b><fmt:message key="EHF.Title.CaseDetails"/></b></td></tr>
		</table>
		<table class="contentTable">
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Hospital"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateAndTime"/></b> <font color="red">*</font></td></tr>
		<tr>
		<td class="tbcellBorder"><html:select name="patientForm" property="hospitalId" styleId="hospitalId" style="WIDTH: 75%" title="Select Hospital" onmouseover="addTooltip('hospitalId')">
					<html:options property="ID" collection="hospitalList" labelProperty="VALUE"/>
			</html:select></td>
		<td class="tbcellBorder"><html:text name="patientForm"  property="dtTime" styleId="dtTime" title="Registration Date & Time" style="WIDTH: 15em"/></td>
		</tr>
		</table>
		<!-- pravalika -->
		<table class="tbheader">
		<tr><td><b><fmt:message key="EHF.Title.Category"/></b></td></tr>
		</table>
		<table class="contentTable">
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Category"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Diagnosis"/></b> <font color="red">*</font></td></tr>
		<tr>
		<td class="tbcellBorder"><html:select name="patientForm" property="diagnosisCategory" styleId="diagnosisCategory" style="WIDTH: 75%" title="Select Category" onmouseover="addTooltip('diagnosisCategory')">
					<option value="-1">-- Select Category --</option>
					<html:options  property="ID" collection="categoryList" labelProperty="VALUE"/>
			</html:select></td>
		<td class="tbcellBorder"><html:textarea name="patientForm"  property="dialysis" styleId="dialysis" title="Diagnosis" rows="4" cols="50" style="resize: none; width: 100%;" /></td>
		</tr>
		</table>
		<!-- end -->
		<!-- CR 9043 admission details pravalika --> 
		 <table class="tbheader">
		<tr><td><b><fmt:message key="EHF.Title.Admission"/></b></td></tr>
		</table> 
		
		<table class="contentTable">
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.AdmissionDate"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.AdmissionNote"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.EstimatedAmt"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Attachments"/></b> <font color="red">*</font></td>
		</tr>
		<tr>
		
		<td class="tbcellBorder"><html:text name="patientForm" property="admissionDate" styleId="admissionDate" style="width:150px;" ></html:text></td>
		<td class="tbcellBorder"><html:text name="patientForm" property="admissionNote" styleId="admissionNote" maxlength="500" title="Enter admission Note "  style="WIDTH: 11em" /> </td>
		<td class="tbcellBorder"><html:text name="patientForm" property="estAmount" styleId="estAmount" maxlength = "8" title="Enter estimated amount" style="WIDTH: 12em" /> </td>
	    <td class="tbcellBorder"><html:file name="patientForm" property="attachment" styleId="attachment" accept=".pdf,.jpg,.jpeg,.png" /></td> 
		</tr>
		
		</table> 
		<!-- end -->
	
		<table width="100%">
		<tr>
		<td style="height: 10px; font-size:small;" nowrap="nowrap" width=20%>
				<font color="red"> <fmt:message key="EHF.MandatoryFields"/><br /></font>
		</td>
		<td width="20%" align="right" id="Register"> <button class="but"  type="button" id="RegisterPatient" onclick="validate()">Register</button></td>
		<td width="20%" colspan="2" id="Resetbtn"> <button class="but"  type="button" id="reset" onclick="jqueryConfirmMsg('Data Reset Confirmation','Do you want to reset patient data?',resetData)">Reset</button></td>
		<td width="20%"></td>
		</tr>
		</td>
	</c:if>
</tr>
</table>
<html:hidden name="patientForm" property="disableFlag" styleId="disableFlag"/>
<html:hidden name="patientForm" property="addrEnable" styleId="addrEnable"/>
<html:hidden name="patientForm" property="errMsg" styleId="errMsg"/>
<input type="hidden" name="abhaGenReg" id="abhaGenReg" value="OPR"><!-- Chandana - 8326 - for abha -->
<input type="hidden" value="N" id="abhaDone"><!-- Chandana - 8326 -->
<html:hidden name="patientForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="patientForm" property="ageString" styleId="ageString"/>
<html:hidden name="patientForm" property="telephonicId" styleId="telephonicId"/>
<html:hidden name="patientForm" property="telephonicReg" styleId="telephonicReg"/>
<html:hidden name="patientForm" property="empCode" styleId="empCode"/>
<html:hidden name="patientForm" property="eMailId" styleId="eMailId"/>
<html:hidden name="patientForm" property="scheme" styleId="scheme"/>
<html:hidden name="patientForm" property="prc" styleId="prc"/>
<html:hidden name="patientForm" property="payScale" styleId="payScale"/>
<html:hidden name="patientForm" property="dept" styleId="dept"/>
<html:hidden name="patientForm" property="deptHod" styleId="deptHod"/>
<html:hidden name="patientForm" property="postDist" styleId="postDist"/>
<html:hidden name="patientForm" property="postDDOcode" styleId="postDDOcode"/>
<html:hidden name="patientForm" property="servDsgn" styleId="servDsgn"/>
<html:hidden name="patientForm" property="ddoOffUnit" styleId="ddoOffUnit"/>
<html:hidden name="patientForm" property="currPay" styleId="currPay"/>
<html:hidden name="patientForm" property="designation" styleId="designation"/>
<html:hidden name="patientForm" property="aadharID" styleId="aadharID"/>
<html:hidden name="patientForm" property="aadharEID" styleId="aadharEID"/>
<html:hidden name="patientForm" property="userName" styleId="userName"/>

</form>
<script>
if('${inActiveStatusFlag}' !='Y')
	disableScreen();
/* $(function() { 
    $( "#cardIssueDt" ).datepicker({ 
            changeMonth: true, 
            changeYear: true, 
      		showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
            yearRange: '2000:' + new Date().getFullYear()
    }); 
    var disabled = $('#cardIssueDt').attr('disabled');
    if (disabled == 'disabled')
    {
        $("#cardIssueDt").datepicker('disable');
    }
    else
    {
        $("#cardIssueDt").datepicker('enable');
    } 
}); */

//Chandana - 8618 - New function for print
function generatePrint(patientId)
{
	window.open('./patientDetails.do?actionFlag=viewPatientDetails&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
}

document.addEventListener("DOMContentLoaded", function () {
    var estAmount = document.getElementById("estAmount");

    estAmount.addEventListener("input", function () {
        this.value = this.value.replace(/[^0-9]/g, '');
    });
    
    var fileInput = document.getElementById("attachment");

    if (fileInput) {
        fileInput.addEventListener("change", function () {

            var file = this.files[0];
            if (!file) return;

            var allowedTypes = ["application/pdf", "image/jpeg", "image/jpg", "image/png"];
            var maxSize = 200 * 1024; 
            if (!allowedTypes.includes(file.type)) {
                alert("Only PDF, JPG, JPEG and PNG files are allowed.");
                this.value = "";
                return;
            }

            if (file.size > maxSize) {
                alert("File size should not exceed 200 KB.");
                this.value = "";
                return;
            }

        });
    }
});
</script>
</div>

<%@ include file="/common/aadharAuthForeKYC.jsp"%> <!-- Chandana - 8326 -->
</body>
</html>
</fmt:bundle>




