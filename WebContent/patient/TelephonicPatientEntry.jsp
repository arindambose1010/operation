<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ include file="/common/include.jsp"%>
  
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script> 
<style>
 input:focus 
{
  outline:#000 dotted 1px; 
}    
select:focus
{
  outline:#000 dotted 1px; 
} 
radio:focus
{
  outline:#000 dotted 1px; 
}
</style>
<script>
$(function() { 
    $( "#dateOfBirth" ).datepicker({ 
            changeMonth: true, 
            changeYear: true, 
            showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
            maxDate:0,
            yearRange: '1900:' + new Date().getFullYear(),
    }); 
}); 
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
function maxLengthPaste(field,maxChars)
{
      event.returnValue=false;
      if(window.clipboardData)
    	  {
      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
			{
      			jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
    	  }
      if (event.clipboardData) 
      {
    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
    		{
    		jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
      }
}
function pasteIntercept(evt)
 {  
var input=document.getElementById('indication');
maxLengthPaste(input,3000); 
}
function hospStatus(id)
{
	var cardNo = '${patientForm.cardNo}';
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("]","");            
                	resultArray = resultArray.replace("*","");           
                	if(resultArray.trim() != "success")
                	{  
                		alert(resultArray);
                		document.getElementById("hospitalId").value="-1";
            		}
            	}
        	}
        }
   	 }
    	
	url = "./patientDetails.do?actionFlag=getHospStatus&callType=Ajax&hospId="+id+"&cardNo="+cardNo;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}

function on_load(){
	
	var execScheme='${execScheme}';
	if(execScheme!=null)
		{
			if(execScheme=='CD202')
				{
				//	document.forms[0].card_type[2].style.display='none';
				//	document.forms[0].card_type[3].style.display='none';
					//document.getElementById('journalist').style.display='none';
				//	 document.getElementById('newBorn').style.display='none';
				}
		}
	
	
	if(document.getElementById("errMsg").value!="")
	{
		jqueryErrorMsg('Health Card Validation',document.getElementById("errMsg").value);
	}
	document.forms[0].dtTime.disabled="true";
	if(document.getElementById("disableFlag").value=='N'){
	if(document.getElementById("dateOfBirth")!=null)
		populateAge(document.getElementById("dateOfBirth"));
	
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
	}
	if((document.getElementById("telScheme").value!=null && document.getElementById("telScheme").value!='-1' && document.getElementById("telScheme").value!='' )
			||(document.getElementById("scheme").value!=null && document.getElementById("scheme").value!='-1' && document.getElementById("scheme").value!='' ))
		{
			document.getElementById("telScheme").disabled="true";
			if(document.getElementById("scheme").value!=null && document.getElementById("scheme").value!='-1'
				&& document.getElementById("scheme").value!='')
				document.getElementById("telScheme").value=document.getElementById("scheme").value;
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
				}
			else if(document.forms[0].card_type[1].checked)
				{
					document.getElementById('WCNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="";
					document.getElementById("label_joucard").style.display="none";
				//	document.getElementById("label_newBorncard").style.display='none';
				}
			else if(document.forms[0].card_type[2].checked)
				{
					document.getElementById('JCNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="none";
					document.getElementById("label_joucard").style.display="";
				//	document.getElementById("label_newBorncard").style.display='none';
				}
			/* else if(document.forms[0].card_type[3].checked)
				{
					document.getElementById('NBNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="none";
					document.getElementById("label_joucard").style.display="none";
					document.getElementById("label_newBorncard").style.display='';
					
					document.forms[0].NBNo9.disabled=true;
					document.forms[0].NBNo9.value=0;
					document.forms[0].NBNo10.disabled=true;
					document.forms[0].NBNo10.value=1;
					
					document.getElementById("head").checked=true;
					document.getElementById("head").disabled=true;
					
					document.getElementById("occupation").value="";
					document.getElementById("dateOfBirth").value="";
					document.getElementById("years").value="0";
					document.getElementById("months").value="0";
					document.getElementById("days").value="0";
				
					document.getElementById("relation").value="19";
					document.getElementById("relation").disabled=true;
					
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
				} */
		}
	}
}
function count()
{
	
	document.getElementById("diag").value='Y';	
}
function sameAddrTele(status)
{
	var errMsg="";
	var lField='';
	
	if(document.getElementById("commCheck").checked==true)
		{
		document.getElementById("commCheck").value='Y';
		if(document.getElementById("hno").value=="")
			{
			errMsg=errMsg+"Enter House No in Card Address \n";
			if(lField=='')
		        lField='hno'; 
			}
		if(document.getElementById("street").value=="")
			{
			errMsg=errMsg+"Enter Street in Card Address \n";
			if(lField=='')
		        lField='street'; 
			}
		if(document.getElementById("state").value==-1)
		{
		errMsg=errMsg+"Select State in Card Address \n";
		if(lField=='')
	        lField='state'; 
		}
		if(document.getElementById("district").value==-1)
			{
			errMsg=errMsg+"Select District in Card Address \n";
			if(lField=='')
		        lField='district'; 
			}  
		if(document.getElementById("mdl_mcl").value==-1)
			{
			errMsg=errMsg+"Select Mandal/Municipality in Card Address \n";
			if(lField=='')
		        lField='mdl_mcl'; 
			}
		if(document.getElementById("mdl_mcl").value == 'Mdl')
			{
			if(document.getElementById("mandal").value==-1)
				{
				errMsg=errMsg+"Select Mandal in Card Address \n";
				if(lField=='')
			        lField='mandal'; 
				}
			}
		else if(document.getElementById("mdl_mcl").value == 'Mpl')
	 		{
			if(document.getElementById("municipality").value==-1)
				{
				errMsg=errMsg+"Please Select Municipality in Card Address \n";
				if(lField=='')
			        lField='municipality'; 
				}
	 		}
		if(document.getElementById("village").value == "-1")
			{
			errMsg=errMsg+"Select Village in Card Address \n";
			if(lField=='')
		        lField='village'; 
			}
		/*if(document.getElementById("tgGovPatCond")!=null)
			{
				var tgGovPatCond=document.getElementById("tgGovPatCond").value;
				if(tgGovPatCond==null || tgGovPatCond=='' || tgGovPatCond==' ' ||
						(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond!='Y'))
					{
						if(document.getElementById("pin").value=="")
						{
						errMsg=errMsg+"Enter Pin code in Card Address \n";
						if(lField=='')
					        lField='pin'; 
						}
					}
			}
		else
			{
				if(document.getElementById("pin").value=="")
					{
					errMsg=errMsg+"Enter Pin code in Card Address \n";
					if(lField=='')
				        lField='pin'; 
					}
			}	*/
		
		if(errMsg=="")
			{
			document.getElementById("comm_hno").value=document.getElementById("hno").value;
			document.forms[0].comm_hno.disabled=true;
		
			document.forms[0].comm_street.value = document.forms[0].street.value;
			document.forms[0].comm_street.disabled=true;
		
			document.getElementById("same_state").style.display="";
			document.getElementById("comm_statetd").style.display="none";
			document.forms[0].same_state.value=document.getElementById("state").options[document.getElementById("state").selectedIndex].text;
			document.forms[0].same_state.disabled=true;
			
			document.getElementById("same_dist").style.display="";
			document.getElementById("comm_disttd").style.display="none";
			document.forms[0].same_dist.value=document.getElementById("district").options[document.getElementById("district").selectedIndex].text;
			document.forms[0].same_dist.disabled=true;
		
			document.getElementById("same_mdl_mcltd").style.display="";
			document.getElementById("comm_mdl_mcltd").style.display="none";
			document.forms[0].same_mdl_mcl.value=document.getElementById("mdl_mcl").options[document.getElementById("mdl_mcl").selectedIndex].text;
			document.forms[0].same_mdl_mcl.disabled=true;
		
			if(document.getElementById("mdl_mcl").value == 'Mdl')
				{
				document.getElementById("mandalcommtab").style.display="";
				document.getElementById("munciplcommtab").style.display="none";
				document.getElementById("same_mandal").style.display="";
				document.getElementById("comm_mandaltd").style.display="none";
				document.getElementById("comm_municipaltd").style.display="none";
				document.forms[0].same_mandal.value=document.getElementById("mandal").options[document.getElementById("mandal").selectedIndex].text;
				document.forms[0].same_mandal.disabled=true;
				}
			else if(document.getElementById("mdl_mcl").value == 'Mpl')
	 			{
				document.getElementById("mandalcommtab").style.display="none";
				document.getElementById("munciplcommtab").style.display="";
				document.getElementById("same_municipalitytd").style.display="";
		 		document.getElementById("comm_municipaltd").style.display="none";
		 		document.getElementById("comm_mandaltd").style.display="none";
		 		document.forms[0].same_municipality.value=document.getElementById("municipality").options[document.getElementById("municipality").selectedIndex].text;
		 		document.forms[0].same_municipality.disabled=true;	
	 			}
			document.getElementById("same_villagetd").style.display="";
			document.getElementById("comm_villagetd").style.display="none";
			document.forms[0].same_village.value=document.getElementById("village").options[document.getElementById("village").selectedIndex].text;
			document.forms[0].same_village.disabled=true;
		
			document.forms[0].comm_pin.value = document.forms[0].pin.value;
			document.forms[0].comm_pin.disabled=true;
			}
		else
			{
			document.getElementById("commCheck").value='N';
			document.getElementById("commCheck").checked=false;
			var fr = partial(focusBox,document.getElementById(lField));
	 		jqueryAlertMsg('Same Address Check',errMsg,fr);
			return false;
			}
		}
	else if(document.getElementById("commCheck").checked==false)
		{
		document.getElementById("commCheck").value='N';
		document.forms[0].comm_hno.value="";
        document.forms[0].comm_street.value="";
		document.forms[0].comm_pin.value="";
		document.forms[0].comm_hno.disabled=false;
		document.forms[0].comm_street.disabled=false;
		document.forms[0].comm_pin.disabled=false;
		
		document.getElementById("same_state").style.display = 'none';
		document.getElementById("same_state").value="";
		document.getElementById("comm_state").value="-1";
		document.getElementById("comm_statetd").style.display = '';
		
		document.getElementById("same_dist").style.display = 'none';
		document.getElementById("same_dist").value="";
		//document.getElementById("comm_dist").value="-1";
		document.getElementById("comm_disttd").style.display = '';
		document.getElementById("comm_dist").options.length = 1;
		
		document.getElementById("same_mdl_mcltd").style.display='none';
		document.getElementById("same_mdl_mcl").value="";
		document.getElementById("comm_mdl_mcl").value="-1";
		document.getElementById("comm_mdl_mcltd").style.display='';
		
		document.getElementById("same_mandal").style.display='none';
		document.getElementById("same_mandal").value="";
		document.getElementById("mandalcommtab").style.display='';
		document.getElementById("munciplcommtab").style.display='none';
		document.getElementById("same_mandal").style.display='none';
		document.forms[0].comm_mandal.options.length=1;
		document.getElementById("comm_mandaltd").style.display='';
		document.forms[0].comm_municipality.options.length=1;
		document.getElementById("comm_municipaltd").style.display='none';
		document.getElementById("same_municipalitytd").style.display='none';
		
		document.getElementById("same_villagetd").style.display='none';
		document.getElementById("same_village").value="";
		document.forms[0].comm_village.options.length=1;
		document.getElementById("comm_villagetd").style.display='';
		
		}
}
function retrieveDetails()
{
	resetPatientData();
	var wapFamilyNo=readCardData();
 if(wapFamilyNo!=false)
	 {
 document.forms[0].action="./patientDetails.do?actionFlag=retrieveCardDetails&cardNo="+wapFamilyNo+"&SelOper=TELE";
 document.forms[0].method="post";
 document.forms[0].submit(); 
	 }
 else
	 return false;
}
function validate()
{
	var errMsg='';
	var lField='';
	
	if(document.getElementById("telScheme").value!=-1)
		{
			var userScheme='${userState}';
			if(userScheme!='CD203')
				{
					if(userScheme.localeCompare(document.getElementById("telScheme").value)!=0)
						{
							alert("Patient cannot be registered as the Logged user and the Patient are mapped to different States.");
							return false;
						}
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
 	if(document.getElementById("occupation").value=="")
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
 	 				errMsg=errMsg+"Select Municipality in Communication Address <br>";
 	 			if(lField=='')
 	 		        lField='comm_municipality'; 
 				}
 		}
 		if(document.getElementById("comm_village").value==-1)
 			{
 			if(errMsg=='')
 	 	 		errMsg=errMsg+"Select Village in Communication Address <br>";
 	 	 	if(lField=='')
 	 	        lField='comm_village'; 
 			}
 		/* if(document.getElementById("comm_pin").value==null || document.getElementById("comm_pin").value=='')
 			{
 			if(errMsg=='')
 				errMsg=errMsg+"Enter Pin code in Communication Address <br>"; 
 			if(lField=='')
 		        lField='comm_pin'; 
 			} */
 	 	}
 	
 	if(document.getElementById("hospitalId").value==-1)
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Select Hospital in Caller Details <br>";
 	 	if(lField=='')
 	        lField='hospitalId'; 
 		}
 	if(document.getElementById("teleCallerName").value==null || document.getElementById("teleCallerName").value=='')
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Caller name in Caller Details <br>";
 	 	if(lField=='')
 	        lField='teleCallerName'; 
 		}
 	if(document.getElementById("teleCallerDesgn").value==null || document.getElementById("teleCallerDesgn").value=='')
		{
		if(errMsg=='')
	 		errMsg=errMsg+"Enter Designation in Caller Details <br>";
	 	if(lField=='')
	        lField='teleCallerDesgn'; 
		}
 	if(document.getElementById("telePhoneNo").value==null || document.getElementById("telePhoneNo").value=='')
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Phone Number in Caller Details <br>";
 	 	if(lField=='')
 	        lField='telePhoneNo'; 
 		}
	 
 	if(document.getElementById("diag").value=='Y' && document.getElementById("diagType").value!=-1){
	 if(document.getElementById("diagType").value==-1)
		 {
		 if(errMsg=='')
 	 		errMsg=errMsg+"Please Select Diagnosis Type in Caller Diagnosis Details <br>";
 	 	if(lField=='')
 	        lField='diagType'; 
		 }
	if(document.getElementById("mainCatName").value==-1)
		{
		if(errMsg=='')
 			errMsg=errMsg+"Select Main Category Name in Caller Diagnosis Details <br>";
 		if(lField=='')
 	        lField='mainCatName'; 
		}
	if(document.getElementById("catName").value==-1)
		{
		if(errMsg=='')
 	 		errMsg=errMsg+"Select Category Name in Caller Diagnosis Details <br>";
 	 	if(lField=='')
 	        lField='catName'; 
		}
	if(document.getElementById("subCatName").value==-1)
		{
		if(errMsg=='')
 	 		errMsg=errMsg+"Select Sub Category Name in Caller Diagnosis Details <br>";
 	 	if(lField=='')
 	        lField='subCatName'; 
		}
	if(document.getElementById("diseaseName").value==-1)
		{
		if(errMsg=='')
 	 		errMsg=errMsg+"Select Disease Name in Caller Diagnosis Details <br>";
 	 	if(lField=='')
 	        lField='diseaseName'; 
		}
	if(document.getElementById("disAnatomicalName").value==-1)
		{
		if(errMsg=='')
 	 		errMsg=errMsg+"Select Disease Anatomical Name in Caller Diagnosis Details <br>";
 	 	if(lField=='')
 	        lField='disAnatomicalName'; 
		}
 	}
	if(document.getElementById("telScheme").value==-1)
		{
		if(errMsg=='')
		 		errMsg=errMsg+"Select Scheme for the patient in Provisional Approval Details <br>";
		 	if(lField=='')
		        lField='telScheme'; 
		}
	if(document.getElementById("asriCatName").value==-1)
		{
		if(errMsg=='')
 	 		errMsg=errMsg+"Select Therapy Category in Provisional Approval Details <br>";
 	 	if(lField=='')
 	        lField='asriCatName'; 
		}
 	if(document.getElementById("ICDCatName").value==-1)
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Select Therapy ICD Category in Provisional Approval Details <br>";
 	 	if(lField=='')
 	        lField='ICDCatName'; 
 		}
	if(document.getElementById("ICDProcName").value==-1)
		{
		if(errMsg=='')
 	 		errMsg=errMsg+"Select Therapy ICD Procedure in Provisional Approval Details <br>";
 	 	if(lField=='')
 	        lField='ICDProcName'; 
		}
	if(document.getElementById("indication").value==null || document.getElementById("indication").value=='')
		{
		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Indication for Telephonic Intimation in Provisional Approval Details <br>";
 	 	if(lField=='')
 	        lField='indication'; 
		}
		
 	if(document.getElementById("teleDocName").value==null || document.getElementById("teleDocName").value=='')
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Doctor Name in Provisional Approval Authority Details <br>";
 	 	if(lField=='')
 	        lField='teleDocName'; 
 		}
 	if(document.getElementById("teleDocDesgn").value==null || document.getElementById("teleDocDesgn").value=='')
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Designation in Provisional Approval Authority Details <br>";
 	 	if(lField=='')
 	        lField='teleDocDesgn'; 
 		}
 	if( document.getElementById("teleDocPhoneNo").value==null || document.getElementById("teleDocPhoneNo").value=='')
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Phone Number in Provisional Approval Authority Details<br>";
 	 	if(lField=='')
 	        lField='teleDocPhoneNo'; 
 		}
 	if(document.getElementById("remarksProcedure").value==null || document.getElementById("remarksProcedure").value=='')
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Procedure Remarks in Provisional Approval Authority Details <br>";
 	 	if(lField=='')
 	        lField='remarksProcedure'; 
 		}
 	if(document.getElementById("remarksDiagnosis").value==null || document.getElementById("remarksDiagnosis").value=='')
 		{
 		if(errMsg=='')
 	 		errMsg=errMsg+"Enter Diagnosis Remarks in Provisional Approval Authority Details <br>";
 	 	if(lField=='')
 	        lField='remarksDiagnosis'; 
 		}
		
  if(!errMsg=='')
	{
	  	var fr = partial(focusFieldView,lField);
		jqueryAlertMsg('Telephonic Registration Page Mandatory Fields',errMsg,fr);
		//document.getElementById(lField).focus();
		return false;
	}
  else
	  {
	 	if(checkGenderRelation()!=false)
	 	{
			if(validateSpecialities()!=false)
			{
	 		jqueryConfirmMsg('Telephonic Registration Confirmation','Do you want to register patient?',registerPatientDetails);
			}
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
	/* else if(document.forms[0].card_type[3].checked)
		{
			wapNo=document.forms[0].NBNo0.value+document.forms[0].NBNo1.value+document.forms[0].NBNo2.value+document.forms[0].NBNo3.value+document.forms[0].NBNo4.value+document.forms[0].NBNo5.value
			+document.forms[0].NBNo6.value+document.forms[0].NBNo7.value+document.forms[0].NBNo8.value;
			familyNo=document.forms[0].NBNo9.value+document.forms[0].NBNo10.value;
		} */
	
	   wapNo=wapNo.toUpperCase();

	   document.getElementById("submitPatient").disabled=true;
	   document.getElementById("submitPatient").className='butdisable';
	   document.forms[0].action="./patientDetails.do?actionFlag=captureTelephonicPatientDtls&wapNo="+wapNo+"&familyNo="+familyNo+"&cardNo="+document.getElementById("cardNo").value;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
   
}

function disableScreen()
{
	var disableFlg=null;
	disableFlg=document.getElementById("disableFlag").value;
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
				//	document.getElementById("label_newBorncard").style.display="none";
				}
			else if(document.forms[0].card_type[1].checked)
				{
					document.getElementById('WCNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="";
					document.getElementById("label_joucard").style.display="none";
				//	document.getElementById("label_newBorncard").style.display="none";
				}
			else if(document.forms[0].card_type[2].checked)
				{
					document.getElementById('JCNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="none";
					document.getElementById("label_joucard").style.display="";
				//	document.getElementById("label_newBorncard").style.display="none";
				}
			/* else if(document.forms[0].card_type[3].checked)
				{
					document.getElementById('NBNo'+i).value=cardNo[i];
					document.getElementById("label_empcard").style.display="none";
					document.getElementById("label_pencard").style.display="none";
					document.getElementById("label_joucard").style.display="none";
					document.getElementById("label_newBorncard").style.display="";
				}	 */
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
		populateAge(document.getElementById("dateOfBirth"));
		}
	var elLength = document.InPatientForm.elements.length; 
    for (var i=0; i<elLength; i++)
    {
    	var type = InPatientForm.elements[i].type; 
    	 if(type=="text" || type=="button" || type=="checkbox" || type=="radio" || type=="select-one")
        {
        	InPatientForm.elements[i].disabled=val;
        }
    }
    document.getElementById('head').disabled=val2;
    document.forms[0].card_type[0].disabled=val2;
    document.forms[0].card_type[1].disabled=val2;
    document.forms[0].card_type[2].disabled=val2;
   // document.forms[0].card_type[3].disabled=val2;
    document.getElementById("RetrieveDetails").disabled=val2;
   
		
    for(var i=0;i<=10;i++)
    {
        document.getElementById('ECNo'+i).disabled=val2;
        document.getElementById('WCNo'+i).disabled=val2;
        document.getElementById('JCNo'+i).disabled=val2;
      //  document.getElementById('NBNo'+i).disabled=val2;
    }
    document.getElementById("dtTime").disabled=true;
    document.getElementById('years').disabled=true;
	document.getElementById('months').disabled=true;
	document.getElementById('days').disabled=true;
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
	document.forms[0].card_type[0].checked=false;
	document.forms[0].card_type[1].checked=false;
	document.forms[0].card_type[2].checked=false;
//	document.forms[0].card_type[3].checked=false;
	document.getElementById("head").checked=false;
	document.getElementById("label_empcard").style.display="";
	document.getElementById("label_pencard").style.display="none";
	document.getElementById("label_joucard").style.display="none";
	//document.getElementById("label_newBorncard").style.display="none";
	for(var i=0;i<11;i++)
		{
		document.getElementById("ECNo"+i).value="";
		document.getElementById("WCNo"+i).value="";
		document.getElementById("JCNo"+i).value="";
	//	document.getElementById("NBNo"+i).value="";
		}
	resetPatientData();
	resetDiagnosisData();
}
function resetPatientData()
{
	document.forms[0].gender[0].checked=false;
	document.forms[0].gender[1].checked=false;
	//document.getElementById("cardIssueDt").value="";
	document.getElementById("fname").value="";
	document.getElementById("dateOfBirth").value="";
	document.getElementById("years").value="";
	document.getElementById("months").value="";
	document.getElementById("days").value="";
	document.getElementById("relation").value="-1";
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

	document.forms[0].comm_hno.disabled=false;
	document.forms[0].comm_street.disabled=false;
	document.forms[0].comm_pin.disabled=false;
	document.getElementById("commCheck").value='N';
	}
function resetDiagnosisData()
{
	document.getElementById("hospitalId").value="-1";
	document.getElementById("teleCallerDesgn").value="";
	document.getElementById("teleCallerName").value="";
	document.getElementById("telePhoneNo").value="";
	
	document.getElementById("diagType").value="-1";
	document.getElementById("diagCode").value="";
	document.getElementById("mainCatName").options.length=1;
	document.getElementById("mainCatCode").value="";
	document.getElementById("catName").options.length=1;
	document.getElementById("catCode").value="";
	document.getElementById("subCatName").options.length=1;
	document.getElementById("subCatCode").value="";
	document.getElementById("diseaseName").options.length=1;
	document.getElementById("diseaseCode").value="";
	document.getElementById("disAnatomicalName").options.length=1;
	document.getElementById("disAnatomicalCode").value="";
	
	document.getElementById("asriCatName").options.length=1;
	document.getElementById("asriCatCode").value="";
	document.getElementById("ICDCatName").options.length=1;
	document.getElementById("ICDCatCode").value="";
	document.getElementById("ICDProcName").options.length=1;
	document.getElementById("ICDProcCode").value="";
	document.getElementById("indication").value="";
	
	document.getElementById("teleDocName").value="";
	document.getElementById("teleDocDesgn").value="";
	document.getElementById("teleDocPhoneNo").value="";
	document.getElementById("remarksProcedure").value="";
	document.getElementById("remarksDiagnosis").value="";
}
function getTherapyCategoryList(param)
{
	document.forms[0].asriCatName.options.length=1;
	getIcdCategoryCodes();
	if(document.forms[0].hospitalId.value==-1){
		return false;
		}
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var categoryList = resultArray.split("@,"); 
                	if(categoryList.length>0)
                	{  
                		if(param==1)
            			{
                			document.forms[0].asriCatName.options.length=0;
                        	document.forms[0].asriCatName.options[0]=new Option("---select---","-1");
            			}
            			for(var i = 0; i<categoryList.length;i++)
                		{	
                     		var arr=categoryList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                         		if(param==1)
                    	 		{
                        	 		document.forms[0].asriCatName.options[i+1] =new Option(val1,val2);
                    	 		}
                     		}
                		}
            		}
            	}
        	}
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getTherapyCategory&callType=Ajax&lStrHospId="+document.forms[0].hospitalId.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function getIcdCategoryCodes()
{
	document.getElementById("asriCatCode").value="";
	document.forms[0].ICDCatName.options.length=1;
	getTherapyICDProcedureList(1);
	if(document.getElementById("asriCatName").value=="-1")
		{
		return false;
		}
	else
		{
	var asriCatCode=document.getElementById("asriCatName").value;
	document.getElementById("asriCatCode").value=asriCatCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var categoryList = resultArray.split("@,"); 
                	if(categoryList.length>0)
                	{  
                 		document.forms[0].ICDCatName.options.length=0;
                    	document.forms[0].ICDCatName.options[0]=new Option("---select---","-1");
            			for(var i = 0; i<categoryList.length;i++)
                		{	
                     		var arr=categoryList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].ICDCatName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getICDCatByAsriCode&callType=Ajax&lStrAsriCatId="+asriCatCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}
function getTherapyICDProcedureList(param)
{
	document.forms[0].ICDCatCode.value="";
	document.forms[0].ICDProcName.options.length=1;
	getICDProcedureCode();
	if(document.forms[0].ICDCatName.value=="-1")
		{
		return false;
		}
	else
		{
		var scheme="${scheme}";
		
		if(scheme==""||scheme==null)
			{	
				if(document.getElementById("telScheme").value=='-1')
					{
						document.getElementById("ICDCatName").value='-1';
						alert("Patient Scheme has to be selected before selecting the ICD Category to fetch the procedures");
						focusBox(document.getElementById("telScheme"));
						return false;
					}
				else
					scheme=document.getElementById("telScheme").value;
			}
				
	var hospId = document.getElementById("hospitalId").value;
	var icdCatCode=document.forms[0].ICDCatName.value;
	var asriCode=document.forms[0].asriCatName.value;
	document.forms[0].ICDCatCode.value=icdCatCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var procedureList = resultArray.split("@,"); 
                	if(procedureList.length>0)
                	{  
                		if(param==1)
            			{
                			document.forms[0].ICDProcName.options.length=0;
                        	document.forms[0].ICDProcName.options[0]=new Option("---select---","-1");
            			}
            			for(var i = 0; i<procedureList.length;i++)
                		{	
                     		var arr=procedureList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                         		if(param==1)
                    	 		{
                        	 		document.forms[0].ICDProcName.options[i+1] =new Option(val1,val2);
                    	 		}
                     		}
                		}
            		}
            	}
        	}
        }
    }
    url = "./patientDetails.do?actionFlag=getProcByCat&callType=Ajax&lStrICDCatId="+icdCatCode+"&lStrAsriCode="+asriCode+"&scheme="+scheme+"&hospId="+hospId;	
	
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getICDProcedureCode()
{
	document.forms[0].ICDProcCode.value="";
	if(document.forms[0].ICDProcName.value=="-1")
		return false;
	else
	document.forms[0].ICDProcCode.value=document.forms[0].ICDProcName.value;
}
function validateSpecialities()
{
var lField='asriCatName';
var fr=partial(focusBox,document.getElementById(lField));
var speciality=document.getElementById("asriCatCode").value;
var years=document.getElementById("years").value;
if(years>=14 && (speciality=='M4' || speciality=='S8'))
{
jqueryAlertMsg("Speciality Age Validation","Pediatrics and Pediatric Surgeries are not allowed if age is greater than or equal to 14 years",fr);
return false;
}
if(document.forms[0].gender[0].checked && speciality=='S4')
{
jqueryAlertMsg("Speciality Gender Validation","Gynaecology and obstetrics surgery is not allowed for male",fr);
return false;
}
return true;
}
</script>
</head>
<body >
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" name="InPatientForm">
<table width="95%" style="margin:0 auto" border="1"><tr><td>
<br>
<table class="tbheader">
<tr><th><b><fmt:message key="EHF.Title.TelephonicPatientRegistration"/></b></th></tr>
</table>
<table width="100%">
<tr><td class="labelheading1 tbcellCss" width="15%"><b><fmt:message key="EHF.Cardtype"/></b></td>
<td class="tbcellBorder" width="55%">
	<html:radio name="patientForm" property="card_type" value="E" styleId="card_type" onclick="enable_cardType(this.checked)" title="Employee Card No"/><b><fmt:message key="EHF.EmployeeCardNo"/></b> 
	<html:radio name="patientForm" property="card_type" value="P" styleId="card_type" onclick="enable_cardType(this.checked)" title="Pensioner Card No"/><b><fmt:message key="EHF.PensionerCardNo"/></b>
	<html:radio name="patientForm" property="card_type" value="J" styleId="card_type" onclick="enable_cardType(this.checked)" title="journalist Card No"/><b><label id="journalist"><fmt:message key="EHF.JournalistCardNo"/></label></b>
	<%-- <html:radio name="patientForm" property="card_type" value="NB" styleId="card_type" onclick="enable_cardType(this.checked)" title="New Born Baby"/><b><label id="newBorn"><fmt:message key="EHF.NewBornBabyCardNo"/></label></b> --%> 
  </td>
  <td width="20%">&nbsp;</td>
  <td width="10%">&nbsp;</td></tr>

<tr><td class="tbcellBorder">
<html:checkbox  name="patientForm" property="head" styleId="head"  onclick="checkFamilyHead(this.checked)" title="Check Family Head"></html:checkbox>
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
<td><button class="but"  type="button" id="RetrieveDetails" onclick="javascript:retrieveDetails()">Retrieve</button></td>
<td>&nbsp;</td>
</tr>
</table>
<br>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table>

<table class="contentTable">
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Name"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b> <font color="red">*</font>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b> </td>
	</td>
</tr>
<tr>
<td class="tbcellBorder"> <html:text name="patientForm" property="fname" styleId="fname" title="Enter Name" maxlength="100" style="WIDTH: 11em" onchange="validateAlphaSpace('Name',this,'')"/></td>
<td class="tbcellBorder"><html:radio name="patientForm" property="gender" value="M" title="Male" styleId="gender" /><b><fmt:message key="EHF.Male"/></b> 
	<html:radio name="patientForm" property="gender" value="F" title="Female" styleId="gender"/><b><fmt:message key="EHF.Female"/></b></td><%-- <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;<html:checkbox name="patientForm" property="child" styleId="child" onclick="fn_chkChildYN(this.checked)"><fmt:message key="EHF.Child"/></html:checkbox> </td>  --%>
<td class="tbcellBorder"><html:text name="patientForm" property="dateOfBirth" styleId="dateOfBirth" title="Select Date Of Birth" style="WIDTH: 11em" onchange="populateAge(this)" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:text name="patientForm" property="years" styleId="years" title="Years" style="width:22px"  maxlength="3" disabled="true" onkeydown="validateBackSpace(event)"/>&nbsp;<b><fmt:message key="EHF.Years"/></b>
<html:text name="patientForm" property="months" styleId="months"  style="width:15px"  title="Months" maxlength="2" disabled="true" onkeydown="validateBackSpace(event)"/><b><fmt:message key="EHF.Months"/></b>
<html:text name="patientForm" property="days" styleId="days"  style="width:15px"  title="Days" maxlength="2" disabled="true" onkeydown="validateBackSpace(event)"/><b><fmt:message key="EHF.Days"/></b>
</td>
<td rowspan="6" style="text-align:center" class="tbcellBorder">
<logic:notEmpty name="patientForm" property="photoUrl">
<img src="common/showDocument.jsp" width="120" height="120"/>
</logic:notEmpty>
<logic:empty name="patientForm" property="photoUrl">
<img src="images/photonot.gif" width="120" height="120" alt="NO DATA"/>
</logic:empty></td>
<td>&nbsp;</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b> <font color="red">*</font></td> 
</tr>
<tr>
<td class="tbcellBorder"><html:text name="patientForm" property="occupation" styleId="occupation" maxlength="100" title="Enter Designation" onchange="validateAlphaSpace('Designation',this,'Designation')"/></td>
<td class="tbcellBorder"><html:text name="patientForm" property="contactno" styleId="contactno" maxlength="10" title="Enter Contact No" style="WIDTH: 11em" onchange="validateMobile(this)"/></td> 
<td class="tbcellBorder"><html:select name="patientForm" property="relation" styleId="relation" title="Select Relationship" style="WIDTH: 12em" onmouseover="addTooltip('relation')">
	<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
	<html:options property="ID" collection="relationList" labelProperty="VALUE"/> 
	</html:select></td> 
</tr>
<tr><td colspan="5"><font size="2px"><b><fmt:message key="EHF.CardAddress"/></b></font></td>
</tr>
<tr> 
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HouseNo"/></b> <font color="red">*</font></td> 
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b>State</b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b> <font color="red">*</font></td>
</tr>
<tr> 
<td class="tbcellBorder"><html:text name="patientForm" property="hno" styleId="hno" maxlength="100" title="Enter Card House No" style="WIDTH: 11em" onchange="validateAddress(this,'Card Address House No');resetCommAddr()"/> </td>
<td class="tbcellBorder"><html:text name="patientForm" property="street" styleId="street" maxlength="100" title="Enter Card Street" style="WIDTH: 11em" onchange="validateAddress(this,'Card Address Street');resetCommAddr()"/> </td>
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
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td>
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
<td style="display:none" id="municipalitylist" class="tbcellBorder"><html:select name="patientForm" property="municipality" styleId="municipality" style="WIDTH: 12em" title="Select Card Municipality" onchange="mandalSelected(1,'Mpl')" onkeypress="mandalSelected(1,'Mpl')"  onmouseover="addTooltip('municipality')"> 
<html:option value="-1" ><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="mplList">
<html:options property="ID" collection="mplList" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select></td> 
<td class="tbcellBorder"><html:select name="patientForm" property="village" styleId="village" style="WIDTH: 12em" title="Select Card Village" onmouseover="addTooltip('village')" onchange="villageSelected(1)" onkeypress="villageSelected(1)">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="villList">
<html:options property="ID" collection="villList" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select></td> 
<td class="tbcellBorder"><html:text name="patientForm"  property="pin" styleId="pin" maxlength="6" onchange="validatePin(this),pinChanged(1)" title="Enter Card Pin code" style="WIDTH: 11em"/></td>
</tr>
<tr><td colspan="5"><font size="2px"><b><fmt:message key="EHF.CommunicationAddress"/></b></font></td></tr>
<tr><td  colspan="5"><b><fmt:message key="EHF.CheckSameAddress"/></b>
<html:checkbox name="patientForm" property="commCheck" value="N" styleId="commCheck" onclick="sameAddrTele(this.checked)" title="Same Address Check"/></td>
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
			<logic:notEmpty name="districtList">
			<html:options property="ID" collection="districtList" labelProperty="VALUE"/>
			</logic:notEmpty>
    </html:select></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b> <font color="red">*</font></td>  
<td id="mandalcommtab" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mandal"/></b> <font color="red">*</font></td>
<td style="display:none" id="munciplcommtab" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Municipality"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td>
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
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.CallerDetails"/></b></td></tr>
</table>
<table width="98%" class="contentTable">
<tr>
<td  width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Hospital"/></b> <font color="red">*</font></td>
<td  width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CallerName"/></b> <font color="red">*</font></td>
<td  width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b> <font color="red">*</font></td>
<td  width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PhoneNo"/></b> <font color="red">*</font></td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="hospitalId" styleId="hospitalId" style="WIDTH: 12em" title="Select Hospital" onchange="javascript:getTherapyCategoryList(1);javascript:hospStatus(this.value);" onmouseover="addTooltip('hospitalId')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            <html:options property="ID" collection="hospitalList" labelProperty="VALUE"/>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="teleCallerName"  styleId="teleCallerName" title="Caller Name" maxlength="100"  style="WIDTH: 11em" onchange="validateAlphaSpace('caller name',this,'')"/></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="teleCallerDesgn" styleId="teleCallerDesgn" title="Designation" maxlength="100" style="WIDTH: 11em" onchange="validateAlphaSpace('Designation',this,'Designation')"/></td>
<td  class="tbcellBorder"><html:text name="patientForm"  property="telePhoneNo" styleId="telePhoneNo" title="Phone Number" maxlength="10"  style="WIDTH: 11em" onchange="validateMobile(this)"/></td>
</tr>
</table>

<table width="98%" class="contentTable">
<tr><td><font size="2px"><b><fmt:message key="EHF.Diagnosis"/></b></font></td></tr>
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagType"/></b></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagCode"/></b></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatName"/></b></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatCode"/></b></td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="diagType" styleId="diagType" style="WIDTH: 12em" title="Select Diagnosis Type" onchange="getDiagnMainCatList();count();" onmouseover="addTooltip('diagType')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            <html:options property="ID" collection="diagnTypeList" labelProperty="VALUE"/>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diagCode" styleId="diagCode" title="Diagnosis Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="mainCatName" styleId="mainCatName" style="WIDTH: 12em" title="Select Main Category Name" onchange="getDiagnCategoryList()" onmouseover="addTooltip('mainCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
      </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="mainCatCode" styleId="mainCatCode" title="Main Category Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatName"/></b> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatCode"/></b> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatName"/></b> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatCode"/></b> </td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="catName" styleId="catName" style="WIDTH: 12em" title="Select Category Name" onchange="getDiagnSubCategoryList()" onmouseover="addTooltip('catName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="catCode" styleId="catCode" title="Category Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="subCatName" styleId="subCatName" style="WIDTH: 12em" title="Select Sub Category Name" onchange="getDiagnDiseaseList()" onmouseover="addTooltip('subCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="subCatCode" styleId="subCatCode" title="Sub Category Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseName"/></b> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseCode"/></b> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalName"/></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalCode"/></b></td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="diseaseName" styleId="diseaseName" style="WIDTH: 12em" title="Select Disease Name" onchange="getDiagnDisAnatomicalList()" onmouseover="addTooltip('diseaseName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
 	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diseaseCode" styleId="diseaseCode" title="Disease Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="disAnatomicalName" styleId="disAnatomicalName" style="WIDTH: 12em" title="Select Disease Anatomical Name" onmouseover="addTooltip('disAnatomicalName')" onchange="getDisAnatomicalCode()">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="disAnatomicalCode" styleId="disAnatomicalCode" title="Disease Anatomical Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
</table>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.ProvAppDetails"/></b></td></tr>
</table>
<table width="98%" class="contentTable">
<tr><td><font size="2px"><b><fmt:message key="EHF.Therapy"/></b></font></td><td width="25%" >&nbsp;</td>
<td width="25%" class="labelheading1 tbcellCss"><b> <fmt:message key="EHF.Scheme"/> </b> <font color="red">*</font></td>
<td class="tbcellBorder"><html:select name="patientForm" property="telScheme" styleId="telScheme" title="Select Scheme" style="WIDTH: 12em" >
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="CD201"><fmt:message key="EHF.AndhraPradesh"/></html:option>
<html:option value="CD202"><fmt:message key="EHF.Telangana"/></html:option>
</html:select></td>
</tr>
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b> <fmt:message key="EHF.CatName"/> </b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b> <fmt:message key="EHF.CatCode"/> </b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDCatName"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDCatCode"/></b> <font color="red">*</font></td>
<%-- <td><b><fmt:message key="EHF.ICDSubCatName"/></b> <font color="red">*</font></td>
<td><b><fmt:message key="EHF.ICDSubCatCode"/></b> <font color="red">*</font></td> --%>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="asriCatName" styleId="asriCatName" title="Select Category" style="WIDTH: 12em" onchange="getIcdCategoryCodes()" onmouseover="addTooltip('asriCatName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="asriCatCode" styleId="asriCatCode" title="Category Code" maxlength="10"  style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="ICDCatName" styleId="ICDCatName" style="WIDTH: 12em" title="Select ICD Category " onchange="javascript:getTherapyICDProcedureList(1);" onmouseover="addTooltip('ICDCatName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="ICDCatCode" styleId="ICDCatCode" title="ICD Category Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<%-- <td><html:select name="patientForm" property="ICDSubCatName" styleId="ICDSubCatName" style="WIDTH:130px" title="Select ICD Sub Category" onchange="javascript:getTherapyICDProcedureList(1);" onmouseover="addTooltip('ICDSubCatName')">                        
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
   </html:select>
</td>
<td><html:text name="patientForm"  property="ICDSubCatCode" styleId="ICDSubCatCode" title="ICD Sub Category Code" maxlength="10" onkeydown="validateBackSpace(event)" readonly="true"/></td> --%>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDProc"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDProcCode"/></b> <font color="red">*</font></td>
<td colspan="2" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Indication"/></b> <font color="red">*</font></td>
</tr>
<tr>
<td class="tbcellBorder" valign="top"><html:select name="patientForm" property="ICDProcName" styleId="ICDProcName" style="WIDTH: 12em" title="Select ICD Procedure" onmouseover="addTooltip('ICDProcName')" onchange="getICDProcedureCode()">                        
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
  </html:select></td>
<td class="tbcellBorder" valign="top"><html:text name="patientForm"  property="ICDProcCode" styleId="ICDProcCode" title="ICD Procedure Code" maxlength="10" style="WIDTH: 11em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td colspan="2" class="tbcellBorder"><html:textarea name="patientForm"  property="indication"  title="Enter Indication For Telephonic Intimation"  styleId="indication"  style="WIDTH: 35em" onkeydown="return maxLengthPress(this,3000,event)"  onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Indication For Telephonic Intimation')"/></td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
</table>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.ApprovalBy"/></b></td></tr>
</table>
<table width="98%" class="contentTable">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DocName"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PhoneNo"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfInt"/></b> <font color="red">*</font></td>
</tr>
<tr>
<td class="tbcellBorder"><html:text name="patientForm"  property="teleDocName"  styleId="teleDocName" title="Doctor Name" style="WIDTH: 11em" maxlength="100" onchange="validateAlphaSpace('Doctor Name',this,'')"/></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="teleDocDesgn"  styleId="teleDocDesgn" title="Doctor Designation" style="WIDTH: 11em" maxlength="100" onchange="validateAlphaSpace('Doctor Designation',this,'Designation')"/></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="teleDocPhoneNo"  styleId="teleDocPhoneNo" title="Doctor Phone Number" style="WIDTH: 11em" maxlength="10"  onchange="validateMobile(this)"/></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="dtTime" styleId="dtTime"  title="Intimation Date & Time" style="WIDTH: 11em"/></td>
</tr>
<tr>
<td colspan="4"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/></b> <font color="red">*</font></td>
</tr>
<tr>
<td colspan="4" class="tbcellBorder">This is a Provisonal Approval given for  <html:text name="patientForm"  property="remarksProcedure" styleId="remarksProcedure" title="Enter Remarks Procedure" style="WIDTH: 11em" maxlength="50" onchange="validateAlphaNumCommaSpace('Remarks Procedure',this)"/>  [enter: the procedure] 
therapy(ies) for <html:text name="patientForm"  property="remarksDiagnosis" styleId="remarksDiagnosis" title="Enter Remarks Diagnosis" style="WIDTH: 11em" maxlength="50" onchange="validateAlphaNumCommaSpace('Remarks Diagnosis',this)"/> [enter: the diagnosis] 
based on the indication stated by the treating doctor over phone.The Preauthorization has to be raised within 72 Hrs with complete clinical and documentary evidence from this telephonic intimation ID only. 
</td>
</tr>
</table>
<table width="100%">
<tr>
<td style="height: 10px; font-size:small;" nowrap="nowrap" width=20%>
		<font color="red"> <fmt:message key="EHF.MandatoryFields"/><br /></font>
</td>
<td width="20%" align="right" id="Register"> <button class="but"  type="button" id="submitPatient" onclick="validate()">Submit</button></td>
<td width="20%" colspan="2" id="Resetbtn"> <button class="but"  type="button" id="reset" onclick="jqueryConfirmMsg('Data Reset Confirmation','Do you want to reset patient data?',resetData)">Reset</button></td>
<td width="20%"></td>
</tr>
</table>
</td></tr></table>
<html:hidden name="patientForm" property="diag" styleId="diag"/>
<html:hidden name="patientForm" property="disableFlag" styleId="disableFlag"/>
<html:hidden name="patientForm" property="errMsg" styleId="errMsg"/>
<html:hidden name="patientForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="patientForm" property="ageString" styleId="ageString"/>
<html:hidden name="patientForm" property="empCode" styleId="empCode"/>
<html:hidden name="patientForm" property="slab" styleId="slab"/>
<html:hidden name="patientForm" property="scheme" styleId="scheme"/>
</form>
<script>
on_load();
var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	//For validating maxlength onpaste event--For textarea fields
	document.getElementById("indication").attachEvent("onpaste",pasteIntercept);
	}
else if(browserName == "Netscape")
	{
	document.getElementById("indication").addEventListener("paste", pasteIntercept, false);
	}
</script>
</div>
</body>
</html>
</fmt:bundle>




