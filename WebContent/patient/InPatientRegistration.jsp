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
<!-- Added by Srikalyan for TG Patient Registration to Capture Patient's Biometrics -->
<script language="vbscript" type="text/vbscript">  
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
<!-- End by Srikalyan for TG Patient Registration to Capture Patient's Biometrics -->
<script>
/* var s$=jQuery.noConflict();

s$(function(){ */
   /*
    * this swallows backspace keys on any non-input element.
    * stops backspace -> back
    */
   /* var rx = /INPUT|SELECT|TEXTAREA/i;

   s$(document).bind("keydown keypress", function(e){
       if( e.which == 8 ){ // 8 == backspace
           if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
               e.preventDefault();
           }
       }
   });
});  */
/* function uploadBabyPhoto(arg)
	{
		checkExtension(arg);
		var photoID='';
		if(arg='childPhoto')
			photoID='CD1000';
		else if(arg='identificationAttachment')
			photoID='CD2000';
		document.forms[0].action="./patientDetails.do?actionFlag=uploadRegPhoto&photoID="+photoID;
		document.forms[0].method="post";
		document.forms.submit();
		
	} */
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
	var wapFamilyNo=readCardData();
	var hospId = '${userHospId}';
	 if (wapFamilyNo != false && hospId == 'EHS34') {//Chandana - 8618 - Condition to check the registration is done in nims and 30 days condition
	        checkPatientIsRegisteredInNims(wapFamilyNo, function(isRegistered) {
	            if (!isRegistered) {
	                if (wapFamilyNo != false) {
	                    document.forms[0].action = "./patientDetails.do?actionFlag=retrieveCardDetails&cardNo=" + wapFamilyNo;
	                    document.forms[0].method = "post";
	                    document.forms[0].submit();
	                }
	            } else {
	                return false;
	            }
	        });
	    } else if(wapFamilyNo!=false){
 		document.forms[0].action="./patientDetails.do?actionFlag=retrieveCardDetails&cardNo="+wapFamilyNo;
		document.forms[0].method="post";
 		document.forms[0].submit(); 
	 }else{
		 return false;
	 }
}
//Chandana - 8618 - Added this function to check the card number registered with nims or not and 30 days condition for registration
function checkPatientIsRegisteredInNims(wapFamilyNo, callback) {
    $.ajax({
        url: '/Operations/patientDetails.do?actionFlag=checkPatientIsRegisteredInNims',
        data: 'cardNo=' + wapFamilyNo,
        type: 'POST',
        success: function(data) {
            var response = JSON.parse(data);
            if (response.length > 0 && response != null) {
            	var parts = response.split("~");
                var patientId = parts[0];
                var validTillDate = parts[1];
            	var confirmUpload = confirm("Patient registration for OPD is valid for 30 days, and the registration is currently active till " + validTillDate + " for the user with patient ID: " + patientId + "." +
            			"\n Do you wish to print the registration form?");
            	if (confirmUpload) {
            		generatePrint(patientId);
            		clearCardDetails();
            		callback(true);
            	}
                else{
                	clearCardDetails();
                	callback(true);
                }
            } else {
                callback(false);
            }
        },
        error: function() {
            console.error("Error checking registration status.");
            callback(false);
        }
    });
}
//Chandana - 8618 - New function for claering the entered card details
function clearCardDetails() {    
    if(document.forms[0].card_type[0].checked){
		for(var i=0;i<11;i++){
			document.getElementById("ECNo"+i).value='';
		}
		document.forms[0].card_type[0].checked = false;
	}else if(document.forms[0].card_type[1].checked){
		for(var i=0;i<11;i++){
			document.getElementById("WCNo"+i).value='';
		}
		document.forms[0].card_type[1].checked = false;
	}else if(document.forms[0].card_type[2].checked){
		for(var i=0;i<11;i++){
			document.getElementById("JCNo"+i).value='';
		}
		document.forms[0].card_type[2].checked = false;
	}else if(document.forms[0].card_type[3].checked){
		for(var i=0;i<11;i++){
			document.getElementById("ABHANo"+i).value='';
		}
		document.forms[0].card_type[3].checked = false;
	}
    $("input[name='card_type']").prop("checked", false);
}



/* function fn_chkChildYN(status)
{
	if(status)
		{
		if(!document.forms[0].card_type[0].checked)
		{
			document.forms[0].WCNo9.disabled=false;
			document.forms[0].WCNo10.disabled=false;
			document.forms[0].head.checked = false;
			document.forms[0].head.disabled = true;
		}
		else
			{
			document.forms[0].ECNo9.disabled=false;
			document.forms[0].ECNo10.disabled=false;
			document.forms[0].head.checked = false;
			document.forms[0].head.disabled = true;
			}
		}
	else
		{
		document.forms[0].head.disabled = false;
		}
    
}  */ 

function validate()
{
	var errMsg='';
	var lField='';
	if(document.getElementById("hospitalId").value==null || document.getElementById("hospitalId").options.length==0)
		{
			if(errMsg=='')
				errMsg=errMsg+"Cannot register patient because this mithra is not mapped to any hospital"; 
			if(lField=='')
		        lField='hospitalId'; 
		}
	//Chandana - 8442 - Added for card type 3 in the below existing if condition
	if (!document.forms[0].card_type[0].checked && !document.forms[0].card_type[1].checked && !document.forms[0].card_type[2].checked && !document.forms[0].card_type[3].checked)
	{
		if(errMsg=='')
			errMsg=errMsg+"Select card type <br>";
		if(lField=='')
	        lField='card_type'; 
	}
	else if(document.forms[0].card_type[0].checked)
	{
		var empCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("ECNo"+i).value=='')	
			{
				empCardCount++;	
			}
			}
		if(empCardCount>0)
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter valid Employee Card No <br>";
			if(lField=='')
				lField='ECNo0';
			}
	}
	else if(document.forms[0].card_type[1].checked)
	{
		var penCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("WCNo"+i).value=='')	
			{
				penCardCount++;	
			}
		}
		if(penCardCount>0)
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter valid Pensioner Card No <br>";
			if(lField=='')
				lField='WCNo0';
			}
	}
	else if(document.forms[0].card_type[2].checked)
	{
		var jouCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("JCNo"+i).value=='')	
			{
				jouCardCount++;	
			}
		}
		if(jouCardCount>0)
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter valid Journalist Card No <br>";
			if(lField=='')
				lField='JCNo0';
			}
	}
	//Chandana - 8442 - Added below else if for abha card type
	else if(document.forms[0].card_type[3].checked)
	{
		var abhaCardCount=0;
		for(var i=0;i<=13;i++)
			{
			if (document.getElementById("ABHANo"+i).value=='')	
			{
				abhaCardCount++;	
			}
		}
		if(abhaCardCount>0)
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter valid ABHA No <br>";
			if(lField=='')
				lField='ABHANo0';
			}
	}
	/* else if(document.forms[0].card_type[3].checked)
	{
		var newBornCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("NBNo"+i).value=='')	
			{
				newBornCount++;	
			}
		}
		if(newBornCount>0)
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter valid New Born Babys Parent Card No <br>";
			if(lField=='')
				lField='JCNo0';
			}
	} */
	/* if(document.getElementById("cardIssueDt").value==null || document.getElementById("cardIssueDt").value=='')
		errMsg=errMsg+"Please Select Card Issue Date \n"; */
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
			wapNo=document.forms[0].ECNo0.value+document.forms[0].ECNo1.value+document.forms[0].ECNo2.value+document.forms[0].ECNo3.value+document.forms[0].ECNo4.value+document.forms[0].ECNo5.value
			+document.forms[0].ECNo6.value+document.forms[0].ECNo7.value+document.forms[0].ECNo8.value;
			familyNo=document.forms[0].ECNo9.value+document.forms[0].ECNo10.value;
		}
	else if(document.forms[0].card_type[1].checked)
		{
			wapNo=document.forms[0].WCNo0.value+document.forms[0].WCNo1.value+document.forms[0].WCNo2.value+document.forms[0].WCNo3.value+document.forms[0].WCNo4.value+document.forms[0].WCNo5.value
			+document.forms[0].WCNo6.value+document.forms[0].WCNo7.value+document.forms[0].WCNo8.value;
			familyNo=document.forms[0].WCNo9.value+document.forms[0].WCNo10.value;
		}
	else if(document.forms[0].card_type[2].checked)
		{
			wapNo=document.forms[0].JCNo0.value+document.forms[0].JCNo1.value+document.forms[0].JCNo2.value+document.forms[0].JCNo3.value+document.forms[0].JCNo4.value+document.forms[0].JCNo5.value
			+document.forms[0].JCNo6.value+document.forms[0].JCNo7.value+document.forms[0].JCNo8.value;
			familyNo=document.forms[0].JCNo9.value+document.forms[0].JCNo10.value;
		}
	//Chandana - 8442 -- Added below else if condition for card type 3
	else if(document.forms[0].card_type[3].checked)
		{
			wapNo=document.forms[0].ABHANo0.value+document.forms[0].ABHANo1.value+document.forms[0].ABHANo2.value+document.forms[0].ABHANo3.value+document.forms[0].ABHANo4.value+document.forms[0].ABHANo5.value
			+document.forms[0].ABHANo6.value+document.forms[0].ABHANo7.value+document.forms[0].ABHANo8.value+document.forms[0].ABHANo9.value+document.forms[0].ABHANo10.value+document.forms[0].ABHANo11.value+document.forms[0].ABHANo12.value
			+document.forms[0].ABHANo13.value;
		}
	/* else if(document.forms[0].card_type[3].checked)
		{
			wapNo=document.forms[0].NBNo0.value+document.forms[0].NBNo1.value+document.forms[0].NBNo2.value+document.forms[0].NBNo3.value+document.forms[0].NBNo4.value+document.forms[0].NBNo5.value
			+document.forms[0].NBNo6.value+document.forms[0].NBNo7.value+document.forms[0].NBNo8.value;
			familyNo=document.forms[0].NBNo9.value+document.forms[0].NBNo10.value;
		} */
	//Chandana - 8442 - Added below if else condition as abha no does not have any familyno so
	if (document.forms[0].card_type[3].checked) {
		wapNo=wapNo.toUpperCase();
		var cardFamilyNo = wapNo;
	} else {
		wapNo=wapNo.toUpperCase();
		var cardFamilyNo=wapNo+"/"+familyNo;
	}
	
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
		
			document.forms[0].card_type[2].style.display='none';
			document.getElementById("journalist").style.display='none';
			document.forms[0].card_type[4].style.display='none';//Chandana - 8442 - Changed this to 4 as i am using 3 for abha number
		//	document.getElementById("newBorn").style.display='none';
		//	document.getElementById("newBornInstruct").style.display='none';
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
					document.getElementById('ECNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="";
					document.getElementById("label_pencard").style.display="none";
					document.getElementById("label_joucard").style.display="none";
				//	document.getElementById("label_newBorncard").style.display='none';
					document.getElementById("label_abhaCard").style.display="none";//Chandana - 8442 - Added this to hide the abha number text filed if the cardtype is 0
				}
			else if(document.forms[0].card_type[1].checked)
				{
					document.getElementById('WCNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="";
					document.getElementById("label_joucard").style.display="none";
				//	document.getElementById("label_newBorncard").style.display='none';
					document.getElementById("label_abhaCard").style.display="none";//Chandana - 8442 - Added this to hide the abha number text filed if the cardtype is 1
				}
			else if(document.forms[0].card_type[2].checked)
				{
					document.getElementById('JCNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="none";
					document.getElementById("label_joucard").style.display="";
				//	document.getElementById("label_newBorncard").style.display='none';
					document.getElementById("label_abhaCard").style.display="none";//Chandana - 8442 - Added this to hide the abha number text filed if the cardtype is 2
				}
			else if(document.forms[0].card_type[3].checked)//Chandana - 8442 - Added this for abha number search
			{
				document.getElementById('ABHANo'+i).value=cardNo[i];
				document.getElementById("label_empcard").style.display="none";
				document.getElementById("label_pencard").style.display="none";
				document.getElementById("label_joucard").style.display="none";
			//	document.getElementById("label_newBorncard").style.display='none';
				document.getElementById("label_abhaCard").style.display="";
			}
			/* else if(document.forms[0].card_type[3].checked)
				{
					document.getElementById('NBNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="none";
					document.getElementById("label_joucard").style.display="none";
					document.getElementById("label_newBorncard").style.display='';
				} */
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
    document.forms[0].card_type[2].disabled=val2;
    document.forms[0].card_type[3].disabled=val2;//Chandana - 8442 - Uncommented this, as i am uisng this for abha card number
    document.getElementById("RetrieveDetails").className='but';
    document.getElementById("RetrieveDetails").disabled=val2;
    
		
    for(var i=0;i<=10;i++)
    {
        document.getElementById('ECNo'+i).disabled=val2;
        document.getElementById('WCNo'+i).disabled=val2;
        document.getElementById('JCNo'+i).disabled=val2;
       // document.getElementById('NBNo'+i).disabled=val2;
    }
    for(var i=0;i<=13;i++){//Chandana - 8442 - Added this else if for abha number, as it having 14 numbers
    	document.getElementById('ABHANo'+i).disabled=val2;
    }
     if(document.forms[0].card_type[4]!=null && document.forms[0].card_type[4].checked==true)//Chandana - 8442 - Changed cardtype from 3 to 4, as 3 i am using for abha and there is no new born card search
    	{
    		document.getElementById("relation").value="19";
    		document.getElementById("maritalStatus").value="CD26";
    		document.getElementById("dateOfBirth").value="";
    		document.getElementById("years").value="0";
			document.getElementById("months").value="0";
			document.getElementById("days").value="0";
    		document.getElementById("occupation").value="";
    		document.getElementById("maritalStatus").disabled=true;
    		document.getElementById("slab").disabled=true;
    		document.getElementById("relation").disabled=true;
			
    		
    		document.forms[0].NBNo9.disabled=true;
			document.forms[0].NBNo9.value=0;
			document.forms[0].NBNo10.disabled=true;
			document.forms[0].NBNo10.value=1;
			
			document.getElementById("head").checked=true;
			document.getElementById("head").disabled=true;
			
			$( "#dateOfBirth" ).datepicker({
				defaultDate: "+1w",
				changeMonth: true,
				changeYear: true,
				showOn: "both", 
	            buttonImage: "images/calend.gif", 
	            buttonText: "Calendar",
	            buttonImageOnly: true ,
	            dateFormat: "dd-mm-yy",
				numberOfMonths: 1,
				maxDate: new Date(y, m, d),
				yearRange: new Date().getFullYear()-5+':' + new Date().getFullYear()
			});
    	} 
     else
    	{
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
    	}
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
		document.forms[0].card_type[2].checked=false;
		document.forms[0].card_type[3].checked=false;//Chandana - 8442 - Uncommented this, as i am uisng this for abha card number
		document.getElementById("head").checked=false;
		document.getElementById("label_empcard").style.display="";
		document.getElementById("label_pencard").style.display="none";
		document.getElementById("label_joucard").style.display="none";
		document.getElementById("label_newBorncard").style.display="none";
		document.getElementById("label_abhaCard").style.display="none";//Chandana - 8442 - Added this for not showing the abha input fileds
		
		for(var i=0;i<11;i++)
			{
			document.getElementById("ECNo"+i).value="";
			document.getElementById("WCNo"+i).value="";
			document.getElementById("JCNo"+i).value="";
			document.getElementById("NBNo"+i).value="";
			} 
		for(var i=0;i<=13;i++){//Chandana - 8442 - Added this for loop for abha number
			document.getElementById("ABHANo"+i).value="";
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
//Chandana - 8326 - Added this for displaying the consent form
function fn_aadharAuthModal(){
	var userName = document.getElementById("userName").value;
	document.getElementById("userNamePlaceholder").innerText = userName;
	$("#viewekycModal").modal();
}
</script>
</head>
<body onload="javascript:fn_chkTGGovCond();">
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" name="InPatientForm">
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
			<html:radio name="patientForm" property="card_type" value="E" styleId="card_type" onclick="enable_cardType(this.checked)" title="Employee Card No"/><b><fmt:message key="EHF.EmployeeCardNo"/></b> 
			<html:radio name="patientForm" property="card_type" value="P" styleId="card_type" onclick="enable_cardType(this.checked)" title="Pensioner Card No"/><b><fmt:message key="EHF.PensionerCardNo"/></b> 
			<html:radio name="patientForm" property="card_type" value="J" styleId="card_type" onclick="enable_cardType(this.checked)" title="journalist Card No"/><b><label id="journalist"><fmt:message key="EHF.JournalistCardNo"/></label></b>
			<html:radio name="patientForm" property="card_type" value="AB" styleId="card_type" onclick="enable_cardType(this.checked)" title="ABHA Card No"/><b>ABHA Card No</b><!-- Chandana - 8442 - Added this for showing the abha number radio button -->
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
		<td id="label_empcard" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<!-- <input type="text" name="ECNoT"  id="ECNoT" maxlength="1"  style="width:15px"  onkeydown="validateBackSpace(event)" readonly="true" value="T"/> -->
		<input type="text" name="ECNo0"  id="ECNo0" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo1"  id="ECNo1" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo2"  id="ECNo2" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo3"  id="ECNo3" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo4"  id="ECNo4" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo5"  id="ECNo5" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo6"  id="ECNo6" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo7"  id="ECNo7" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo8"  id="ECNo8" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		/
		<input type="text" name="ECNo9"  id="ECNo9" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ECNo10" id="ECNo10" maxlength="1"  style="width:15px" onkeyup="validateAlphaNum('Employee/Pensioner Card No',this,'Health Card No')"/>
		</td>
		<td id="label_pencard" style="display:none" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<!-- <input type="text" name="WCNoT"  id="WCNoT" maxlength="1"  style="width:15px"  onkeydown="validateBackSpace(event)" readonly="true" value="T"/> -->
		<input type="text" name="WCNo0"  id="WCNo0" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo1"  id="WCNo1" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo2"  id="WCNo2" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo3"  id="WCNo3" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo4"  id="WCNo4" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo5"  id="WCNo5" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo6"  id="WCNo6" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo7"  id="WCNo7" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo8"  id="WCNo8" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		/
		<input type="text" name="WCNo9"  id="WCNo9" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="WCNo10" id="WCNo10" maxlength="1"  style="width:15px" onkeyup="validateAlphaNum('Employee/Pensioner Card No',this,'Health Card No')"/>
		</td>
		<td id="label_joucard" style="display:none" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<!-- <input type="text" name="JCNoT"  id="JCNoT" maxlength="1"  style="width:15px"  onkeydown="validateBackSpace(event)" readonly="true" value="T"/> -->
		<input type="text" name="JCNo0"  id="JCNo0" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo1"  id="JCNo1" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo2"  id="JCNo2" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo3"  id="JCNo3" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo4"  id="JCNo4" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo5"  id="JCNo5" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo6"  id="JCNo6" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo7"  id="JCNo7" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo8"  id="JCNo8" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		/
		<input type="text" name="JCNo9"  id="JCNo9" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="JCNo10" id="JCNo10" maxlength="1"  style="width:15px" onkeyup="validateAlphaNum('Journalist Card No',this,'Health Card No')"/>
		</td>
		<!-- <td id="label_newBorncard" style="display:none" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<input type="text" name="NBNoT"  id="NBNoT" maxlength="1"  style="width:15px"  onkeydown="validateBackSpace(event)" readonly="true" value="T"/>
		<input type="text" name="NBNo0"  id="NBNo0" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo1"  id="NBNo1" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo2"  id="NBNo2" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo3"  id="NBNo3" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo4"  id="NBNo4" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo5"  id="NBNo5" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo6"  id="NBNo6" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo7"  id="NBNo7" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo8"  id="NBNo8" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		/
		<input type="text" name="NBNo9"  id="NBNo9" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="NBNo10" id="NBNo10" maxlength="1"  style="width:15px" onkeyup="validateAlphaNum('New Born Babys Card No',this,'Health Card No')"/>
		</td> -->
		<!-- Chandana - 8442 - Added the below text fields for abha number-->
		<td id="label_abhaCard" style="display:none" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<input type="text" name="ABHANo0"  id="ABHANo0" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo1"  id="ABHANo1" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo2"  id="ABHANo2" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo3"  id="ABHANo3" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo4"  id="ABHANo4" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo5"  id="ABHANo5" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo6"  id="ABHANo6" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo7"  id="ABHANo7" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo8"  id="ABHANo8" maxlength="1"  style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo9"  id="ABHANo9" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo10"  id="ABHANo10" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo11"  id="ABHANo11" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo12"  id="ABHANo12" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
		<input type="text" name="ABHANo13"  id="ABHANo13" maxlength="1"   style="width:15px" onkeyup="autoTab(this,1,event)"/>
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
		<%-- <table class="tbheader" style="display:none" id="attachHeader">
		<tr><td><b><fmt:message key="EHF.Title.Attachments"/></b></td></tr>
		</table>
		<table class="contentTable" style="display:none" id="attachBody">
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.IdentificationProof"/></b> <font color="red">*</font></td>
		<tr>
		<td class="tbcellBorder"><input type="file" disabled="disabled" name="identificationAttachment" id="identificationAttachment"  style="WIDTH: 30%" title="Provide Identification Attachment" onchange="javascript:checkExtension(id)"></input>
		<button disabled="disabled" class="butdisable" id="uploadBabyIdentification" onclick="javascript:uploadBabyPhoto('identificationAttachment')">Upload</button></td>
		</tr>
		</table> --%>
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
</tr></table>
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
</script>
</div>

<%@ include file="/common/aadharAuthForeKYC.jsp"%> <!-- Chandana - 8326 -->
</body>
</html>
</fmt:bundle>




