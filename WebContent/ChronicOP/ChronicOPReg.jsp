<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%> --%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ include file="/common/include.jsp"%>
    <%-- <%@ include file="/common/includeCalendar.jsp"%> --%>  
<%
int liTabIndex = 0;
String photoUrl=(String)request.getAttribute("photo");
 %>
<html>
<style>
body
{
font-family:Tahoma, Geneva, sans-serif;
    font-size: 0.75em;

}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title >Chronic OP Patient Registration</title>
<!-- <LINK href="./css/patient.css" type="text/css" rel="stylesheet"> -->
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script>

function focusBox(id)
{
	aField=id;
	setTimeout("aField.focus()",0);
}

function autoTab1(input,len, e) 
{
	var keyCode = (isNN) ? e.which : e.keyCode; 
     if( keyCode== 32 )
     {
       input.value='';
       return 0;
  	 }
	 if(keyCode!=8 && keyCode!=32 && keyCode!=16)
	 {
	if(validateAlphaNum1('Employee Card No',input,'Health Card No'))
	{  
	  function containsElement(arr, ele) 
	  {
	 	 var found = false, index = 0;
		 while(!found && index < arr.length)
		 if(arr[index] == ele)
			 found = true;
			  else
			 index++;
		   return found;
	 }
	  function getIndex(input) 
	  {
		   var index = -1, i = 0, found = false;
			while (i < input.form.length && index == -1)
					if (input.form[i] == input)index = i;
					  else i++; 
			return index;
	 }
    var filter = (isNN) ? [0,8,9] : [0,8,9,16,17,18,37,38,39,40,46];
     if(input.value.length >= len && !containsElement(filter,keyCode)) 
     {
       input.value = input.value.slice(0, len);
       //input.form[(getIndex(input)+1) % input.form.length].focus();
       var eleIndex=((getIndex(input)+1) % input.form.length);
       if(input.form[eleIndex].disabled==false)
      focusBox(input.form[(getIndex(input)+1) % input.form.length]);
     }
  
 return true;
 }
 }
}
function validateAlphaNum1(arg1,input,fieldType)
{

	var a=input.value;
	//var fr = partial(focusBox,input);
				if(a.trim()=="")
				{
					input.value="";
					//jqueryErrorMsg('Alphanumeric Validation',"Blank spaces are not allowed for "+arg1,fr);
					alert("Blank spaces are not allowed for "+arg1);
					focusBox(input);
					return false;
				}
				var regAlphaNum=/^[0-9a-zA-Z]+$/;
				if(a.search(regAlphaNum)==-1)
				{
					input.value="";
					//jqueryErrorMsg('Alphanumeric Validation',"Only alphanumerics are allowed for "+arg1,fr);
					alert("Only alphanumerics are allowed for "+arg1);
					focusBox(input);
					return false;
				}
				else
				{
					input.value=a.trim();
				}
		return true;
}


function retrieveDetails()
{
	resetPatientData();
	/*Disabling Communication hno,street,pin as these fields are enabled in resetPatientData();*/
	document.forms[0].comm_hno.disabled=true;
	document.forms[0].comm_street.disabled=true;
	document.forms[0].comm_pin.disabled=true;
	var wapFamilyNo=readCardData();
 	if(wapFamilyNo!=false)
	 {
 		document.forms[0].action="./chronicAction.do?actionFlag=retrieveCardDetails&cardNo="+wapFamilyNo;
		document.forms[0].method="post";
 		document.forms[0].submit(); 
	 }
 	else
	 return false;
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
	if (!document.forms[0].card_type[0].checked && !document.forms[0].card_type[1].checked)
	{
		if(errMsg=='')
			errMsg=errMsg+"Select card type";
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
				errMsg=errMsg+"Enter valid Employee Card No";
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
				errMsg=errMsg+"Enter valid Pensioner Card No";
			if(lField=='')
				lField='WCNo0';
			}
	}
	/* if(document.getElementById("cardIssueDt").value==null || document.getElementById("cardIssueDt").value=='')
		errMsg=errMsg+"Please Select Card Issue Date \n"; */
	if(document.getElementById("fname").value==null || document.getElementById("fname").value=='')
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Name";
		if(lField=='')
	        lField='fname'; 
		}
	if (!document.forms[0].gender[0].checked && !document.forms[0].gender[1].checked)
		{
		if(errMsg=='')
			errMsg=errMsg+"Select Gender";
		if(lField=='')
	        lField='gender'; 
		}
 	if(document.getElementById("dateOfBirth").value==null || document.getElementById("dateOfBirth").value=='')
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select Date Of Birth";
		if(lField=='')
	        lField='dateOfBirth'; 
 		}
 	if(document.getElementById("relation").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select Relationship";
 		if(lField=='')
        lField='relation'; 
 		}
 	/* if(document.getElementById("caste").value==-1)
 	 errMsg=errMsg+"Please Select Caste\n"; */
 	if(document.getElementById("slab").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Click on Retrieve button to retrieve Slab";
		if(lField=='')
 	        lField='RetrieveDetails'; 
 	 	}
 	if(document.getElementById("occupation").value=='')
 		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Designation"; 
		if(lField=='')
	        lField='occupation'; 
 		}
 	var ret1=validateAlphaSpace1('Designation',document.getElementById("occupation"),'Designation');
 	if(ret1==false)
 		return false;
 	
 	
 	if(document.getElementById("contactno").value==null || document.getElementById("contactno").value=='')
 		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Contact No";
		if(lField=='')
	        lField='contactno'; 
 		}
 	if(document.getElementById("maritalStatus").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select Marital Status"; 
		if(lField=='')
	        lField='maritalStatus'; 
 		}
 	if(document.getElementById("hno").value==null || document.getElementById("hno").value=='')
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter House No  in Card Address";
	if(lField=='')
        lField='hno'; 
		}
	if(document.getElementById("street").value==null || document.getElementById("street").value=='')
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Street in Card Address"; 
		if(lField=='')
        lField='street'; 
		}
	if(document.getElementById("state").value==-1)
	{
	if(errMsg=='')
		errMsg=errMsg+"Select State in Card Address";
 	if(lField=='')
        lField='state'; 
	}
	if(document.getElementById("district").value==-1)
		{
		if(errMsg=='')
			errMsg=errMsg+"Select District in Card Address";
	 	if(lField=='')
	        lField='district'; 
		}
	if(document.getElementById("mdl_mcl").value=="Mdl")
		{
		if(document.getElementById("mandal").value==-1)
			{
			if(errMsg=='')
				errMsg=errMsg+"Select Mandal in Card Address";
	 		if(lField=='')
	 	        lField='mandal'; 
			}
		}
	else if (document.getElementById("mdl_mcl").value=="Mpl")
		{
		if(document.getElementById("municipality").value==-1)
			{
			if(errMsg=='')
				errMsg=errMsg+"Select Municipality in Card Address";
	 		if(lField=='')
	 	        lField='municipality'; 
			}
		}
	else
		{
		if(errMsg=='')
			errMsg=errMsg+"Select Mandal/Municipality in Card Address";
		if(lField=='')
	        lField='mdl_mcl'; 
		}
	if(document.getElementById("village").value==-1)
		{
		if(errMsg=='')
			errMsg=errMsg+"Select Village in Card Address";
	 	if(lField=='')
	        lField='village'; 
		}
	if(document.getElementById("pin").value==null || document.getElementById("pin").value=='')
		{
		if(errMsg=='')
			errMsg=errMsg+"Enter Pin code in Card Address"; 
		if(lField=='')
        lField='pin'; 
		}
	if(document.getElementById("commCheck").value=='N')
		 {
		 if(document.getElementById("comm_hno").value==null || document.getElementById("comm_hno").value=='')
			 {
			 if(errMsg=='')
				errMsg=errMsg+"Enter House No  in Communication Address";
			if(lField=='')
		        lField='comm_hno'; 
			 }
		 var ret4=validateAddressA(document.getElementById("comm_hno"),'Communication Address House No');
			if(ret4==false)
				return false;
	 	if(document.getElementById("comm_street").value==null || document.getElementById("comm_street").value=='')
	 		{
			if(errMsg=='')
				errMsg=errMsg+"Enter Street in Communication Address"; 
			if(lField=='')
		        lField='comm_street'; 
	 		}
	 	var ret5=validateAddressA(document.getElementById("comm_street"),'Communication Address Street');
		if(ret5==false)
			return false;
	 	if(document.getElementById("comm_state").value==-1)
 		{
		if(errMsg=='')
			errMsg=errMsg+"Select State in Communication Address";
 	 	if(lField=='')
 	        lField='comm_state'; 
 		}
	 	if(document.getElementById("comm_dist").value==-1)
	 		{
			if(errMsg=='')
				errMsg=errMsg+"Select District in Communication Address";
	 	 	if(lField=='')
	 	        lField='comm_dist'; 
	 		}
	 	if(document.getElementById("comm_mdl_mcl").value==-1)
	 		{
			if(errMsg=='')
				errMsg=errMsg+"Select Mandal/Municipality in Communication Address";
	 		if(lField=='')
	 	        lField='comm_mdl_mcl'; 
	 		}
	 	else if(document.getElementById("comm_mdl_mcl").value=="Mdl")
		{
	 		if(document.getElementById("comm_mandal").value==-1)
	 			{
				if(errMsg=='')
					errMsg=errMsg+"Select Mandal in Communication Address";
	 	 	 	if(lField=='')
	 	        	lField='comm_mandal'; 
	 			}
		}
		else if (document.getElementById("comm_mdl_mcl").value=="Mpl")
		{
			if(document.getElementById("comm_municipality").value==-1)
				{
				if(errMsg=='')
					errMsg=errMsg+"Select Municipality in Communication Address";
	 			if(lField=='')
	 		        lField='comm_municipality'; 
				}
		}
		if(document.getElementById("comm_village").value==-1)
			{
			if(errMsg=='')
				errMsg=errMsg+"Select Village in Communication Address ";
	 	 	if(lField=='')
	 	        lField='comm_village'; 
			}
		if(document.getElementById("comm_pin").value==null || document.getElementById("comm_pin").value=='')
			{
			if(errMsg=='')
				errMsg=errMsg+"Enter Pin code in Communication Address "; 
			if(lField=='')
		        lField='comm_pin'; 
			}
	 	}
	if(!errMsg=='')
	{
 		//var fr = partial(focusFieldView,lField);
 		//jqueryAlertMsg('Patient Registration Page Mandatory Fields',errMsg,fr);
 		alert(errMsg);
		focusBox(document.getElementById(lField));
		return false;
	}
  else
	  {
	 	if(checkGenderRelation()!=false)
	 		{
	 			//jqueryConfirmMsg('Patient Registration Confirmation','Do you want to register patient?',registerPatientDetails);
	 			var ret=confirm('Do you want to register Chronic OP ?');
	 			if (ret==false)
	 				return false;
	 			registerPatientDetails();
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
	else
		{
		wapNo=document.forms[0].WCNo0.value+document.forms[0].WCNo1.value+document.forms[0].WCNo2.value+document.forms[0].WCNo3.value+document.forms[0].WCNo4.value+document.forms[0].WCNo5.value
		+document.forms[0].WCNo6.value+document.forms[0].WCNo7.value+document.forms[0].WCNo8.value;
	familyNo=document.forms[0].WCNo9.value+document.forms[0].WCNo10.value;
		}
	wapNo=wapNo.toUpperCase();
	var cardFamilyNo=wapNo+"/"+familyNo;
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
		
	   document.getElementById("RegisterPatient").className='butdisable';	
	   document.getElementById("RegisterPatient").disabled=true;
	   document.forms[0].action="./chronicAction.do?actionFlag=registerPatientDetails&wapNo="+wapNo+"&familyNo="+familyNo;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
		}
	else
		{
		//jqueryAlertMsg('Patient Registration Page','Card  No is changed.Click on Retrieve Details to get patient details');
		alert('Card  No is changed.Click on Retrieve Details to get patient details');
			return false;
		}
	     
}


function disableScreen()
{
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
			//jqueryErrorMsg('Health Card Validation',document.getElementById("errMsg").value);
			alert(document.getElementById("errMsg").value);
				return false;
			}
		}
	if(document.getElementById("cardNo").value!=null || document.getElementById("cardNo").value!="")
		{
		var cardFamilyNo=document.getElementById("cardNo").value;
		//cardFamilyNo.replace("/","");
		var cardNo=cardFamilyNo.replace("/","").split("");

		for(var i=0;i<cardNo.length;i++)
		{
			if(!document.forms[0].card_type[0].checked)
				{
			document.getElementById('WCNo'+i).value=cardNo[i];
			document.getElementById("label_empcard").style.display="none";
			document.getElementById("label_pencard").style.display="";
				}
			else
				{
			document.getElementById('ECNo'+i).value=cardNo[i];
			document.getElementById("label_empcard").style.display="";
			document.getElementById("label_pencard").style.display="none";
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
	var elLength = document.chronicOpForm.elements.length; 
    for (var i=0; i<elLength; i++)
    {
    	var type = chronicOpForm.elements[i].type; 
    	 if(type=="text" || type=="button" || type=="checkbox" || type=="radio" || type=="select-one")
        {
    		 chronicOpForm.elements[i].disabled=val;
        	if(type=="button" && val==true)
        		chronicOpForm.elements[i].className='butdisable';
        	else if(type=="button" && val==false)
        		chronicOpForm.elements[i].className='but';
      	
        }
    }
    document.getElementById('head').disabled=val2;
    document.forms[0].card_type[0].disabled=val2;
    document.forms[0].card_type[1].disabled=val2;
    document.getElementById("RetrieveDetails").className='but';
    document.getElementById("RetrieveDetails").disabled=val2;
    
		
    for(var i=0;i<=10;i++)
    {
        document.getElementById('ECNo'+i).disabled=val2;
        document.getElementById('WCNo'+i).disabled=val2;
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
		//jqueryAlertMsg('Date Validation','Select '+arg1);
		alert('Select '+arg1);
		focusBox(input);
		return false;
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
		//jqueryAlertMsg('Date Validation',arg1+" should be less than Today's Date");
			alert(arg1+" should be less than Today's Date");
			focusBox(input);
			return false;
		}
	}
}

function resetData()
{
	var ret=confirm('Do you want to Reset all the Details');
	if(ret==false)
		return false;
	
		document.getElementById("disableFlag").value="Y";
		disableScreen();
		document.forms[0].card_type[0].checked=false;
		document.forms[0].card_type[1].checked=false;
		document.getElementById("head").checked=false;
		document.getElementById("label_empcard").style.display="";
		document.getElementById("label_pencard").style.display="none";
		for(var i=0;i<11;i++)
			{
			document.getElementById("ECNo"+i).value="";
			document.getElementById("WCNo"+i).value="";
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
function validateAlphaSpace1(arg1,input,fieldType)
{
	var a=input.value;
	if(a.trim()=="")
	{
		alert("Blank spaces are not allowed in "+arg1);
		focusBox(input);
		return false;
	}
	if(a.charAt(0)==" ")
	{
		alert(arg1+ " should not start with space ");
		focusBox(input);
		return false;
	}
	var regAlphaNum=/^[a-zA-Z ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
	{
		alert("Only alphabets are allowed for "+arg1);
		focusBox(input);
		return false;
	}
}
function populateAgeA(input)
{
	document.getElementById("years").value=0;
	document.getElementById("months").value=0;
	document.getElementById("days").value=0;
	document.getElementById("ageString").value="0~0~0";
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	var bmth = parseInt(entered[1],10); 
	var bdy = parseInt(entered[0],10);
	var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
		if(isNaN(byr))
			{
				alert("Select Date Of Birth");
			}
		else
			{
				DoB=Date.parse(DoB);
				var dob=new Date(DoB);

				var today= new Date();
				var nowmonth = today.getMonth();
				var nowday = today.getDate();
				var nowyear = today.getFullYear();
				var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
				DoC=Date.parse(DoC);
				var now=new Date(DoC);
				var minYears= new Date();
				minYears.setFullYear(minYears.getFullYear()-150);
				var minDate=""+(minYears.getMonth()+1)+"/"+ minYears.getDate() +"/"+ minYears.getFullYear();
				minDate=Date.parse(minDate);

				if(DoB>DoC || DoB<minDate)
					{
						alert("Date Of Birth should be less than Today's Date and age should be less than 150 years");
					}
				else
					{
						var yearDob = dob.getFullYear();
						var monthDob =dob.getMonth();
						var dateDob = dob.getDate();
						var yearNow = now.getFullYear();
						var monthNow = now.getMonth();
						var dateNow = now.getDate();
						var yearAge="";
						var monthAge="";
						var dateAge="";
						
						yearAge = yearNow - yearDob;
						
						if (monthNow >= monthDob)
						  monthAge = monthNow - monthDob;
						else {
						  yearAge--;
						   monthAge = 12 + monthNow -monthDob;
						}
						
						if (dateNow >= dateDob)
						  dateAge = dateNow - dateDob;
						else {
						  monthAge--;
						  if(monthAge<1 && yearAge<1 && monthDob==1)
							  {
							  dateAge = 28+dateNow-dateDob;
							  }
						  else
						  dateAge = 31 + dateNow - dateDob;
						
						  if (monthAge < 0) {
						    monthAge = 11;
						    yearAge--;
						  }
							}
							var year=""+yearAge;
							var browserName=navigator.appName;
							if(browserName=="Microsoft Internet Explorer")
								{
								document.getElementById('years').value=year;
								document.getElementById('ageString').value=year+"~"+monthAge+"~"+dateAge;
								
								}
							  else
								  {
								  document.getElementById('years').value=yearAge;
								  document.getElementById('ageString').value=yearAge+"~"+monthAge+"~"+dateAge;
								  }
							
							document.getElementById("months").value=monthAge;
							document.getElementById("days").value=dateAge;
						}
				}
}
function validateAddressA(input,arg1)
{
	var a=input.value;
	var regAlphaNum=/^[0-9a-zA-Z,.:\/\- ]+$/;
	if(a.trim()=="")
	{
		alert("Blank spaces are not allowed for "+arg1);
		input.value="";
		focusBox(input);
		return false;
	}
	if(a.charAt(0)=="-" || a.charAt(0)=="." || a.charAt(0)=="," || a.charAt(0)==":" || a.charAt(0)=="/")
	{
		alert(arg1+ " should not start with special characters");
		focusBox(input);
		input.value="";
		return false;
	}
	if(a.trim().search(regAlphaNum)==-1)
	{
		alert('Only alphanumerics and -,.,:,/ are allowed in '+arg1);
		focusBox(input);
		input.value="";
		return false;
	}
	else
		{
			var flag=true;
			flag=consecutiveSpecialChar(a.trim());
			if(flag)
			{
				input.value=a.trim();
			}
			else
			{
				alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
		}
	
}

function stateSelectedA(param)
{
	resetDist(param);
	var state=null;
	var lStrHdrId='LH6';
	if(param==1)
	{
		state=document.getElementById("state").value;
		if(document.getElementById("commCheck").checked==true)
		{
			resetCommAddr();
		}
	}
	if(param==2)
		{
			state=document.getElementById("comm_state").value;
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
    	alert("Your browser does not support XMLHTTP!");
    	return false;
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	alert("Session has been expired");
            	parent.sessionExpireyClose;
            	return false;
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var districtList = resultArray.split(","); 
            		if(districtList.length>0)
            		{  
            			if(param==1)
            			{
            				document.forms[0].district.options.length=0;
            				document.forms[0].district.options[0]=new Option("---select---","-1");
            			}
            			if(param==2)
          	  			{
            				document.forms[0].comm_dist.options.length=0;
            				document.forms[0].comm_dist.options[0]=new Option("---select---","-1");
          	  			}
            			for(var i = 0; i<districtList.length;i++)
            			{	
            				var arr=districtList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					if(param==1)
            					{
            						document.forms[0].district.options[i+1] =new Option(val1,val2);
            					}
            					if(param==2)
            					{
            						document.forms[0].comm_dist.options[i+1] =new Option(val1,val2);
            					}
            				}
            			}
            		}
            	}
            }
        }
    }
	url = "./chronicAction.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&stateId="+state;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function distSelectedA(param)
{
	var district=null;
	var lStrHdrId=null;
	
	if(param==1)
		{
		document.getElementById("village").options.length = 1;
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		if(document.getElementById("district").value=="-1")
			{
				alert("Select District in Card Address");
				focusBox(document.getElementById("district"));
            	return false;
			}
		else
			district=document.getElementById("district").value;
		}
	if(param==2)
		{
		document.getElementById("comm_village").options.length = 1;
		if(document.getElementById("comm_dist").value=="-1")
		 	{
			alert("Select District in Communication Address");
			focusBox(document.getElementById("comm_dist"));
         	return false;
		 	}
		else
			district=document.getElementById("comm_dist").value;
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
    	alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	alert("Session has been expired");
            	parent.sessionExpireyClose;
            	return false;
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var mandalList = resultArray.split(","); 
            		if(mandalList.length>0)
            		{   
            			if(param==1)
                		{
            				if(document.getElementById('mdl_mcl').value == 'Mdl')
                			{
            					document.forms[0].mandal.options.length=0;
            					document.forms[0].mandal.options[0]=new Option("---select---","-1");
            					document.getElementById("municipalitytab").style.display='none';
            					document.getElementById("municipalitylist").style.display='none';
            					document.getElementById("mandaltab").style.display='';
            					document.getElementById("mandallist").style.display='';
                			}
            				else if(document.getElementById('mdl_mcl').value == 'Mpl')
                			{
            					document.forms[0].municipality.options.length=0;
            					document.forms[0].municipality.options[0]=new Option("---select---","-1");
            					document.getElementById("municipalitytab").style.display='';
            					document.getElementById("municipalitylist").style.display='';
            					document.getElementById("mandaltab").style.display='none';
            					document.getElementById("mandallist").style.display='none';
                			}
            				else
                			{
            					document.forms[0].mandal.options.length=1;
            					document.forms[0].municipality.options.length=1;
            					document.forms[0].village.options.length=1;
            					document.getElementById("municipalitytab").style.display='none';
            					document.getElementById("municipalitylist").style.display='none';
            					document.getElementById("mandaltab").style.display='';
            					document.getElementById("mandallist").style.display='';
                			}
                		}
            			if(param==2)
              	  		{
            				if(document.getElementById('comm_mdl_mcl').value == 'Mdl')
                			{
            					document.forms[0].comm_mandal.options.length=0;
            					document.forms[0].comm_mandal.options[0]=new Option("---select---","-1");
            					document.getElementById("munciplcommtab").style.display='none';
            					document.getElementById("comm_municipaltd").style.display='none';
            					document.getElementById("mandalcommtab").style.display='';
            					document.getElementById("comm_mandaltd").style.display='';
                			}
            				else if(document.getElementById('comm_mdl_mcl').value == 'Mpl')
            				{
            					document.forms[0].comm_municipality.options.length=0;
            					document.forms[0].comm_municipality.options[0]=new Option("---select---","-1");
            					document.getElementById("munciplcommtab").style.display='';
            					document.getElementById("comm_municipaltd").style.display='';
                       	 		document.getElementById("mandalcommtab").style.display='none';
                       	 		document.getElementById("comm_mandaltd").style.display='none';
            				}
            				else
            				{
            					document.forms[0].comm_mandal.options.length=1;
            					document.forms[0].comm_municipality.options.length=1;
            					document.forms[0].comm_village.options.length=1;
            					document.getElementById("munciplcommtab").style.display='none';
                       	 		document.getElementById("comm_municipaltd").style.display='none';
                       	 		document.getElementById("mandalcommtab").style.display='';
                       	 		document.getElementById("comm_mandaltd").style.display='';
            				}
              	  		}
				    
            			for(var i = 0; i<mandalList.length;i++)
            			{	
            					var arr=mandalList[i].split("~");
            					if(arr[1]!=null && arr[0]!=null)
            					{
            						var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            						var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            						if(param==1)
            						{
            							if(document.getElementById('mdl_mcl').value == 'Mdl')
            								document.forms[0].mandal.options[i+1] =new Option(val1,val2);
            							else if(document.getElementById('mdl_mcl').value == 'Mpl')
            								document.forms[0].municipality.options[i+1] =new Option(val1,val2); 
            						}
            						if(param==2)
            						{
            							if(document.getElementById('comm_mdl_mcl').value == 'Mdl')
            								document.forms[0].comm_mandal.options[i+1] =new Option(val1,val2);
            							else if(document.getElementById('comm_mdl_mcl').value == 'Mpl')
            								document.forms[0].comm_municipality.options[i+1] =new Option(val1,val2);
                        	 
            						}
            					}
            			}
            		}
            	}
            }
        }
    }
    if(param==1){
    	if(document.getElementById('mdl_mcl').value == 'Mdl')
		lStrHdrId = 'LH7';
		else if(document.getElementById('mdl_mcl').value == 'Mpl')
		lStrHdrId = 'LM7';
	}
	else if(param==2){
		if(document.getElementById('comm_mdl_mcl').value == 'Mdl')
		lStrHdrId = 'LH7';
		else if(document.getElementById('comm_mdl_mcl').value == 'Mpl')
		lStrHdrId = 'LM7';
		}
    url = "./chronicAction.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&distId="+district;
    xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function mandalSelectedA(param,type)
{
	var mandal=null;
	var lStrHdrId=null;
	if(param==1)
		{
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		if(type=='Mdl')
		mandal=document.getElementById("mandal").value;
		else
		mandal=document.getElementById("municipality").value;	
		}
	if(param==2)
		{
		if(type=='Mdl')
			mandal=document.getElementById("comm_mandal").value;
		else
			mandal=document.getElementById("comm_municipality").value;	
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
    	alert("Your browser does not support XMLHTTP!");
    	return false;
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	alert("Session has been expired");
            	parent.sessionExpireyClose;
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var villageList = resultArray.split(","); 
            		if(villageList.length>0)
            		{  
            			if(param==1)
            			{
            				document.forms[0].village.options.length=0;
            				document.forms[0].village.options[0]=new Option("---select---","-1");
            			}
            			if(param==2)
          	  			{
            				document.forms[0].comm_village.options.length=0;
            				document.forms[0].comm_village.options[0]=new Option("---select---","-1");
          	  			}
            			for(var i = 0; i<villageList.length;i++)
            			{	
            				var arr=villageList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					if(param==1)
            					{
            						document.forms[0].village.options[i+1] =new Option(val1,val2);
            					}
            					if(param==2)
            					{
            						document.forms[0].comm_village.options[i+1] =new Option(val1,val2);
            					}
            				}
            			}
            		}
            	}
            }
        }
    }

    if(type == 'Mdl')
		lStrHdrId = 'LH8';
	else if(type == 'Mpl')
		lStrHdrId = 'LM8';
    	
	url = "./chronicAction.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&mandalId="+mandal;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function villageSelectedA(param)
{
	/* var village=null; */
	if(param==1)
		{
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		village=document.getElementById("village").value;
		}
}
function sameAddr1(status)
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
		if(document.getElementById("pin").value=="")
			{
			errMsg=errMsg+"Enter Pin code in Card Address \n";
			if(lField=='')
		        lField='pin'; 
			}
		
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
			//var fr = partial(focusBox,document.getElementById(lField));
	 		//jqueryAlertMsg('Same Address Check',errMsg,fr);
	 		alert(errMsg);
	 		focusBox(document.getElementById(lField));
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
</script>
</head>
<body>
<form action="/chronicAction.do?" method="post" name="chronicOpForm">
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
               <b>CHRONIC OP REGISTRATION</b>
	</div>
		<div id="container" class="table-responsive">
		<table width="100%" align="center" style="padding-top:0px;margin:auto">
		<tr><td class="labelheading1 tbcellCss" width="15%"><b>Card Type</b></td>
		<td class="tbcellBorder"  width="40%">
			<html:radio name="chronicOpForm" property="card_type" value="E" styleId="card_type" onclick="enable_cardType(this.checked)" title="Employee Card No"/><b>Employee Card No</b> 
			<html:radio name="chronicOpForm" property="card_type" value="P" styleId="card_type" onclick="enable_cardType(this.checked)" title="Pensioner Card No"/><b>Pensioner Card No</b> 
		  </td>
		  <td width="20%">&nbsp;</td>
			<td width="25%">&nbsp;</td>
		  </tr>

		<tr><td class="tbcellBorder">
		<!-- <input type="checkbox" name="head" value="head" id="head" onclick="checkFamilyHead(this.checked)" /> -->
		<html:checkbox name="chronicOpForm" property="head" styleId="head"  onclick="checkFamilyHead(this.checked)" title="Check Family Head"></html:checkbox>
		<b>Family Head</b> 
		</td>
		<td id="label_empcard" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<!-- <input type="text" name="ECNoT"  id="ECNoT" maxlength="1"  style="width:22px"  onkeydown="validateBackSpace(event)" readonly="true" value="T"/> -->
		<input type="text" name="ECNo0"  id="ECNo0" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo1"  id="ECNo1" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo2"  id="ECNo2" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo3"  id="ECNo3" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo4"  id="ECNo4" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo5"  id="ECNo5" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo6"  id="ECNo6" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo7"  id="ECNo7" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo8"  id="ECNo8" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		/
		<input type="text" name="ECNo9"  id="ECNo9" maxlength="1"   style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="ECNo10" id="ECNo10" maxlength="1"  style="width:22px" onkeyup="validateAlphaNum1('Employee/Pensioner Card No',this,'Health Card No')"/>
		</td>
		<td id="label_pencard" style="display:none" class="tbcellBorder">&nbsp;&nbsp;&nbsp;
		<!-- <input type="text" name="WCNoT"  id="WCNoT" maxlength="1"  style="width:22px"  onkeydown="validateBackSpace(event)" readonly="true" value="T"/> -->
		<input type="text" name="WCNo0"  id="WCNo0" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo1"  id="WCNo1" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo2"  id="WCNo2" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo3"  id="WCNo3" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo4"  id="WCNo4" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo5"  id="WCNo5" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo6"  id="WCNo6" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo7"  id="WCNo7" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo8"  id="WCNo8" maxlength="1"  style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		/
		<input type="text" name="WCNo9"  id="WCNo9" maxlength="1"   style="width:22px" onkeyup="autoTab1(this,1,event)"/>
		<input type="text" name="WCNo10" id="WCNo10" maxlength="1"  style="width:22px" onkeyup="validateAlphaNum1('Employee/Pensioner Card No',this,'Health Card No')"/>
		</td>

		<td> <button class="but"  type="button" id="RetrieveDetails" onclick="javascript:retrieveDetails()">Retrieve</button>
		<!--  input type="button" name="RetrieveDetails" id="RetrieveDetails" value="RetrieveDetails" onclick="retrieveDetails()" --></td>
		<%-- <logic:equal  name="patientForm" property="telephonicReg" value="Yes">
		<td><a href="javascript:viewTelephonicInfo()">View Telephonic Intimation Details</a></td>
		</logic:equal> --%>
		</tr>
		   </table>
    </div>
   </div>
 </div> 
		<div class="panel panel-default">
	 		<div class="tbheader">
    						<b>	PATIENT DETAILS</b>
    		</div>
		<div id="container1" class="table-responsive">
    			<table class="contentTable" width="100%">
		<tr>
		<%-- <td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CardIssueDate"/></b></td> --%>
		<td class="labelheading1 tbcellCss" width="20%"><b>Name</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss" width="20%"><b>Gender</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss" width="20%"><b>Marital Status</b> <font color="red">*</font></td>
		<td>&nbsp;</td>	
		</tr>
		<tr>
		<%-- <td class="tbcellBorder"> <html:text name="patientForm" property="cardIssueDt" styleId="cardIssueDt" title="Select Card Issue Date" style="WIDTH: 11em" onchange="validateCardIssueDate('Card Issue Date',this)" onkeydown="validateBackSpace(event)" readonly="true"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('cardIssueDt','330','50')" title="Calendar"></img> --></td> --%>
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="fname" styleId="fname" title="Enter Name" maxlength="100" style="WIDTH: 11em" onchange="validateAlphaSpace1('Name',this,'')" /></td>
		<td class="tbcellBorder"><html:radio name="chronicOpForm" property="gender" value="M" title="Male" styleId="gender" /><b>Male</b> 
								 <html:radio name="chronicOpForm" property="gender" value="F" title="Female" styleId="gender"/><b>Female</b></td><%-- <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;<html:checkbox name="patientForm" property="child" styleId="child" onclick="fn_chkChildYN(this.checked)"><fmt:message key="EHF.Child"/></html:checkbox> </td>  --%>
		<td class="tbcellBorder"><html:select name="chronicOpForm" property="maritalStatus" styleId="maritalStatus" title="Select Marital Status" style="WIDTH: 12em" onmouseover="addTooltip('maritalStatus')">
				<html:option value="-1">Select</html:option>
				<logic:notEmpty name="maritalStatusList">
				<html:options property="ID" collection="maritalStatusList" labelProperty="VALUE"/>
				</logic:notEmpty>
			</html:select></td>
		<td style="text-align:center" rowspan="5" class="tbcellBorder" id="patPhoto">
		<logic:notEmpty name="chronicOpForm" property="photoUrl">
		<img  id="patImage" src="common/showDocument.jsp" width="120" height="110"  onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','120','110')"/>
		</logic:notEmpty>
		<logic:empty name="chronicOpForm" property="photoUrl">
		<img src="images/photonot.gif" width="120" height="110" alt="NO DATA"/>
		</logic:empty></td>
		<td style="text-align:center;display:none" rowspan="5" class="tbcellBorder" id="dummyPhoto">
		<img src="images/photonot.gif" width="120" height="110" alt="NO DATA"/>
		</td>
		<td rowspan="15" valign="top" class="tbcellCss" style="text-align:center">
		<br><br><br><font color="327ACC" size="3px">Instructions</font><br><br><br><br>
		<p><font color="red">1.Please select valid health card type.</font></p>
		<br><br><p><font color="red">2.Enter valid health card no and click on Retrieve button .</font></p>
		<br><br><p><font color="red">3.Health card details will be populated for patient to get registered.</font></p>
		</td> 
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b>Date Of Birth</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>Age</b> </td>
		<td class="labelheading1 tbcellCss"><b>Relationship</b> <font color="red">*</font></td> 

		</tr>
		<tr>
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="dateOfBirth" styleId="dateOfBirth" title="Select Date Of Birth" style="WIDTH: 11em" onblur="populateAgeA(this)" onkeydown="validateBackSpace(event)" readonly="true"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('dateOfBirth','260','60')" title="Calendar"></img> --></td>
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="years" styleId="years" title="Years"  style="width:25px"  maxlength="3" disabled="true"/>&nbsp;<b>Y</b>
		<html:text name="chronicOpForm" property="months" styleId="months"  style="width:25px" title="Months" maxlength="2" disabled="true"/><b>M</b>
		<html:text name="chronicOpForm" property="days" styleId="days"  style="width:25px" title="Days" maxlength="2" disabled="true"/><b>D</b>
		</td>
		<td class="tbcellBorder"><html:select name="chronicOpForm" property="relation" styleId="relation" title="Select Relationship" style="WIDTH: 12em" onmouseover="addTooltip('relation')">
			<html:option value="-1">Select</html:option>
			<logic:notEmpty name="relationList">
			<html:options property="ID" collection="relationList" labelProperty="VALUE"/> 
			</logic:notEmpty>
			</html:select></td> 
		</tr>
		<tr>
		<%-- <td><b><fmt:message key="EHF.Caste"/></b> <font color="red">*</font></td> --%>
		<td class="labelheading1 tbcellCss"><b>Designation</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>Contact No</b> <font color="red">*</font></td> 
		<td class="labelheading1 tbcellCss"><b>Slab</b> <font color="red">*</font></td>
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
			
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="occupation" styleId="occupation" maxlength="100" title="Enter Designation" onchange="validateAlphaSpace1('Designation',this,'Designation')"/></td>
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="contactno" styleId="contactno" maxlength="10" title="Enter Contact No" onchange="validateMobile(this)"/></td> 
		<td class="tbcellBorder">
		<html:select name="chronicOpForm" property="slab" styleId="slab" title="Select Slab" style="WIDTH: 12em" onmouseover="addTooltip('slab')" onkeydown="validateBackSpace(event)">
					<html:option value="-1">Select</html:option>
					<!--<html:option value="A"><fmt:message key="EHF.GeneralWard"/></html:option>-->
					<html:option value="S">Semi Private Ward</html:option>
					<html:option value="P">Private Ward</html:option>
					</html:select></td>
		</tr>

		<tr><td colspan="5"><font size="2px"><b>Card Address</b></font></td>
		</tr>
		<tr> 
		<td class="labelheading1 tbcellCss"><b>House No</b> <font color="red">*</font></td> 
		<td class="labelheading1 tbcellCss"><b>Street</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>State</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>District</b> <font color="red">*</font></td>
		</tr>
		<tr> 
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="hno" styleId="hno" maxlength="100" style="WIDTH: 11em" title="Enter Card House No" onchange="validateAddressA(this,'Card Address House No')"/> </td>
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="street" styleId="street" maxlength="100" title="Enter Card Street"  style="WIDTH: 11em" onchange="validateAddressA(this,'Card Address Street')" /> </td>
		<td class="tbcellBorder"><html:select name="chronicOpForm" property="state" styleId="state" title="Select Card State" style="WIDTH: 12em" onmouseover="addTooltip('state')" onchange="stateSelectedA(1)">
					<html:option value="-1">Select</html:option>
											<logic:notEmpty name="stateList">
											<html:options property="ID" collection="stateList" labelProperty="VALUE"/>
											</logic:notEmpty>
			</html:select></td>
		<td class="tbcellBorder"><html:select name="chronicOpForm" property="district" styleId="district" title="Select Card District" style="WIDTH: 12em" onchange="resetDist(1)" onkeypress="resetDist(1)" onmouseover="addTooltip('district')">
					<html:option value="-1">Select</html:option>
											<logic:notEmpty name="districtList">
											<html:options property="ID" collection="districtList" labelProperty="VALUE"/>
											</logic:notEmpty>
			</html:select></td>
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b>Mandal/Municipality</b> <font color="red">*</font></td> 
		<td id="mandaltab" class="labelheading1 tbcellCss"><b>Mandal</b> <font color="red">*</font></td> 
		<td style="display:none" id="municipalitytab" class="labelheading1 tbcellCss"><b>Municipality</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>City/Town/Village</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>Pin Code</b> <font color="red">*</font></td>
		</tr>
		<tr>
		<td class="tbcellBorder"><html:select name="chronicOpForm" property="mdl_mcl" styleId="mdl_mcl" style="WIDTH: 12em" title="Select Card Mandal/Municipality" onchange="distSelectedA(1)" onkeypress="distSelectedA(1)" onmouseover="addTooltip('mdl_mcl')">
				<html:option value="-1">Select</html:option>
									<html:option value="Mdl">Mandal</html:option>
									<html:option value="Mpl">Municipality</html:option>
								</html:select></td>
		<td id="mandallist" class="tbcellBorder"><html:select name="chronicOpForm" property="mandal" styleId="mandal" style="WIDTH: 12em" title="Select Card Mandal" onchange="mandalSelectedA(1,'Mdl')" onkeypress="mandalSelectedA(1,'Mdl')" onmouseover="addTooltip('mandal')">
								<html:option value="-1">Select</html:option>
														<logic:notEmpty name="mdlList">
														<html:options property="ID" collection="mdlList" labelProperty="VALUE"/>
														</logic:notEmpty>
		</html:select></td> 
		<td style="display:none" id="municipalitylist" class="tbcellBorder"><html:select name="chronicOpForm" property="municipality" styleId="municipality" style="WIDTH: 12em" title="Select Card Municipality" onchange="mandalSelectedA(1,'Mpl')" onkeypress="mandalSelectedA(1,'Mpl')" onmouseover="addTooltip('municipality')">
		<html:option value="-1" >Select</html:option>
		<logic:notEmpty name="mplList">
		<html:options property="ID" collection="mplList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select></td> 
		<td class="tbcellBorder"><html:select name="chronicOpForm" property="village" styleId="village" style="WIDTH: 12em" title="Select Card Village" onmouseover="addTooltip('village')"  onchange="villageSelectedA(1)" onkeypress="villageSelectedA(1)">
		<html:option value="-1">Select</html:option>
		<logic:notEmpty name="villList">
		<html:options property="ID" collection="villList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select></td> 
		<td class="tbcellBorder"><html:text name="chronicOpForm"  property="pin" styleId="pin" maxlength="6" onchange="validatePin(this),pinChanged(1)" title="Enter Card Pin code" style="WIDTH: 11em"/></td>
		</tr>
		<tr><td colspan="5"><font size="2px"><b>Communication Address</b></font></td></tr>
		<tr><td colspan="5"><b>If Card and Communication Address are same</b>
		<html:checkbox name="chronicOpForm" property="commCheck" value="N" styleId="commCheck" onclick="sameAddr1(this.checked)" title="Same Address Check"/></td>
		</tr>

		<tr> 
		<td class="labelheading1 tbcellCss"><b>House No</b> <font color="red">*</font></td> 
		<td class="labelheading1 tbcellCss"><b>Street</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>State</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>District</b> <font color="red">*</font></td>
		</tr>
		<tr> 
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="comm_hno" styleId="comm_hno" maxlength="100" title="Enter Communication House No" style="WIDTH: 11em" onchange="validateAddressA(this,'Communication Address House No')"/> </td>
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="comm_street" styleId="comm_street" maxlength="100" title="Enter Communication Street" style="WIDTH: 11em" onchange="validateAddressA(this,'Communication Address Street')"/> </td>
		<td style='display:none'id='same_state' class="tbcellBorder"><input type="text" name="same_state" id="same_state" title="Communication State"/></td>
		<td id='comm_statetd' class="tbcellBorder"><html:select name="chronicOpForm" property="comm_state" styleId="comm_state" style="WIDTH: 12em" title="Select Communication State" onmouseover="addTooltip('comm_state')" onchange="stateSelected(2)">
					<html:option value="-1">Select</html:option>
					<logic:notEmpty name="stateList">
											<html:options property="ID" collection="stateList" labelProperty="VALUE"/>
											</logic:notEmpty>
			</html:select></td>
		<td style='display:none'id='same_dist' class="tbcellBorder"><input type="text" name="same_dist" id="same_dist" title="Communication District"/></td>
		<td id='comm_disttd' class="tbcellBorder"><html:select name="chronicOpForm" property="comm_dist" styleId="comm_dist" style="WIDTH: 12em" title="Select Communication District" onchange="resetDist(2)" onkeypress="resetDist(2)" onmouseover="addTooltip('comm_dist')">
					<html:option value="-1">Select</html:option>
											<logic:notEmpty name="cdistrictList">
											<html:options property="ID" collection="cdistrictList" labelProperty="VALUE"/></logic:notEmpty>
			</html:select></td>
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b>Mandal/Municipality</b> <font color="red">*</font></td> 
		<td id="mandalcommtab" class="labelheading1 tbcellCss"><b>Mandal</b> <font color="red">*</font></td> 
		<td style="display:none" id="munciplcommtab" class="labelheading1 tbcellCss"><b>Municipality</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>City/Town/Village </b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>Pin Code</b> <font color="red">*</font></td>
		</tr>
		<tr>
		<td style='display:none' id='same_mdl_mcltd' class="tbcellBorder"><input type="text" name="same_mdl_mcl" id="same_mdl_mcl" style="WIDTH: 11em" title="Communication Mandal/Municipality"/></td>
			<td id='comm_mdl_mcltd'  class="tbcellBorder"><html:select name="chronicOpForm" property="comm_mdl_mcl" styleId="comm_mdl_mcl" style="WIDTH: 12em" title="Select Communication Mandal/Municipality" onchange="distSelectedA(2)" onkeypress="distSelectedA(2)" onmouseover="addTooltip('comm_mdl_mcl')">
				<html:option value="-1">Select</html:option>
									<html:option value="Mdl">Mandal</html:option>
									<html:option value="Mpl">Municipality</html:option>
								</html:select></td>
		<td style='display:none'id='same_mandal' class="tbcellBorder"><input type="text" name="same_mandal" id="same_mandal" style="WIDTH: 11em" title="Communication Mandal"/></td>
			<td id='comm_mandaltd' class="tbcellBorder"><html:select name="chronicOpForm" property="comm_mandal" styleId="comm_mandal" style="WIDTH: 12em" title="Select Communication Mandal" onchange="mandalSelectedA(2,'Mdl')" onkeypress="mandalSelectedA(2,'Mdl')" onmouseover="addTooltip('comm_mandal')">
			<html:option value="-1">Select</html:option>
			<logic:notEmpty name="cmdlList">
			<html:options property="ID" collection="cmdlList" labelProperty="VALUE"/>
			</logic:notEmpty>
			</html:select> </td>
		<td style='display:none'id='same_municipalitytd' class="tbcellBorder"><input type="text" name="same_municipality" id="same_municipality" style="WIDTH: 11em" title="Communication Municipality"/></td>
		<td style="display:none" id="comm_municipaltd" class="tbcellBorder"><html:select name="chronicOpForm" property="comm_municipality" styleId="comm_municipality" style="WIDTH: 12em" title="Select Communication Municipality" onchange="mandalSelectedA(2,'Mpl')"  onkeypress="mandalSelectedA(2,'Mpl')" onmouseover="addTooltip('comm_municipality')">
		<html:option value="-1">Select</html:option>
		<logic:notEmpty name="cmplList">
		<html:options property="ID" collection="cmplList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select></td> 
		<td style='display:none'id='same_villagetd' class="tbcellBorder"><input type="text" name="same_village" id="same_village" style="WIDTH: 11em" title="Communication Village"/></td> 
		<td id='comm_villagetd' class="tbcellBorder"><html:select name="chronicOpForm" property="comm_village" styleId="comm_village" style="WIDTH: 12em" title="Select Communication Village" onmouseover="addTooltip('comm_village')" onchange="villageSelectedA(2)" onkeypress="villageSelectedA(2)">
		<html:option value="-1">Select</html:option>
		<logic:notEmpty name="cvillList">
		<html:options property="ID" collection="cvillList" labelProperty="VALUE"/>
		</logic:notEmpty>
		</html:select> </td>
		<td class="tbcellBorder"><html:text name="chronicOpForm" property="comm_pin" styleId="comm_pin" maxlength="6" onchange="validatePin(this),pinChanged(2)" title="Enter Communication Pin code" style="WIDTH: 11em"/> </td>
		</tr>
		</table>
		<div class="tbheader">
    		<b>CASE DETAILS</b>
    	</div>	
		<table class="contentTable"  width="100%">
		<tr>
		<td class="labelheading1 tbcellCss"><b>Hospital</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>Date and Time of Registration</b> <font color="red">*</font></td></tr>
		<tr>
		<td class="tbcellBorder" width="50%"><html:select name="chronicOpForm" property="hospitalId" styleId="hospitalId" style="WIDTH: 75%" title="Select Hospital" onmouseover="addTooltip('hospitalId')">
					<html:options property="ID" collection="hospitalList" labelProperty="VALUE"/>
			</html:select></td>
		<td class="tbcellBorder" width="50%"><html:text name="chronicOpForm"  property="dtTime" styleId="dtTime" title="Registration Date & Time" style="WIDTH: 15em"/></td>
		</tr>
		</table>
		<table width="100%">
		<tr>
		<td style="height: 10px; font-size:small;" nowrap="nowrap" width=20%>
				<font color="red">All fields marked with * are mandatory<br /></font>
		</td>
		<td width="20%" align="right" id="Register"> <button class="but"  type="button" id="RegisterPatient" onclick="validate()">Register</button></td>
		<td width="20%" colspan="2" id="Resetbtn"> <button class="but"  type="button" id="reset" onclick="resetData()">Reset</button></td>
		<td width="20%"></td>
		</tr></table>	</div>
    	</div>
<html:hidden name="chronicOpForm" property="disableFlag" styleId="disableFlag"/>
<html:hidden name="chronicOpForm" property="addrEnable" styleId="addrEnable"/>
<html:hidden name="chronicOpForm" property="errMsg" styleId="errMsg"/>
<html:hidden name="chronicOpForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="chronicOpForm" property="ageString" styleId="ageString"/>
<html:hidden name="chronicOpForm" property="telephonicId" styleId="telephonicId"/>
<html:hidden name="chronicOpForm" property="telephonicReg" styleId="telephonicReg"/>
<html:hidden name="chronicOpForm" property="empCode" styleId="empCode"/>
<html:hidden name="chronicOpForm" property="eMailId" styleId="eMailId"/>
<html:hidden name="chronicOpForm" property="scheme" styleId="scheme"/>
<html:hidden name="chronicOpForm" property="deptHod" styleId="deptHod"/>
<html:hidden name="chronicOpForm" property="postDist" styleId="postDist"/>
<html:hidden name="chronicOpForm" property="stoCode" styleId="stoCode"/>
<html:hidden name="chronicOpForm" property="ddoCode" styleId="ddoCode"/>
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

</script>
</div>
</body>
</html>




