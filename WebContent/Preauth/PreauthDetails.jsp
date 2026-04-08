 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale value='${langID}'/> 
<fmt:bundle basename="ApplicationResources">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Preauth Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<LINK rel="stylesheet" href="css/commonEhfCss.css" type="text/css" media="screen" /> 
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeScrollbar.jsp"%>  
<script type="text/javascript">
var splCount =1;
var splList = new Array();
function addElement(tdId,tdname,enhflg)
{
 	
 	var currentCount = document.getElementById(tdId+tdname+'Count').value;
	if(currentCount > 0 &&(document.getElementById(tdId+tdname+'No'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'Name'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'UnitPrice'+currentCount).value==''))
	{
	if(document.getElementById(tdId+tdname+'Name'+currentCount).value=='')
	{
	alert('Please enter the Name');
	document.getElementById(tdId+tdname+'Name'+currentCount).focus();
	return;
	}
	if(document.getElementById(tdId+tdname+'UnitPrice'+currentCount).value=='')
	{
	alert('Please enter the Unit Price');	
	document.getElementById(tdId+tdname+'UnitPrice'+currentCount).focus();
	return;	
	}
	}
	else
	{
		var tdRow= document.getElementById(tdId+tdname);
		var numi = document.getElementById(tdId+tdname+'Count');
		var num = (document.getElementById(tdId+tdname+'Count').value -1)+ 2;   
		numi.value = num;
		var newdiv = document.createElement('div');  
		var divIdName = tdId+tdname+'divid'+num;       
		newdiv.setAttribute('id',divIdName); 
		newdiv.innerHTML ='<input type="text" id='+tdId+tdname+'No'+num+' name='+tdId+tdname+'No'+num+' value='+num+' style="width:12px" readOnly >&nbsp;<input type="text" id='+tdId+tdname+'Name'+num+' name='+tdId+tdname+'Name'+num+' style="width:70px" onBlur ="checkAlpha(this.id,\'Name\')" maxlength="50" >&nbsp;<input type="text" id='+tdId+tdname+'UnitPrice'+num+' name='+tdId+tdname+'UnitPrice'+num+' style="width:25px" onBlur="IsNumeric_YN(this.id,\'Unit Price\');calcUnitPrice(\''+tdId+'\',\''+enhflg+'\');checkNonZero(this)" maxlength="6">';
		tdRow.appendChild(newdiv);
		var removeid=document.getElementById('Remove'+tdId+tdname);
		removeid.style.display=""; 
		if(num==10)
		{
		var addid=document.getElementById('Add'+tdId+tdname);
		addid.style.display="none"; 
		}
		
	}
}

function removeElement(tdId,tdname,enhflg)
{
	var tdRow = document.getElementById(tdId+tdname);
	var numi = document.getElementById(tdId+tdname+'Count');
	var num=document.getElementById(tdId+tdname+'Count').value
	var divIdName = tdId+tdname+'divid'+num; 
	var olddiv = document.getElementById(divIdName);
	tdRow.removeChild(olddiv); 
	numi.value = num-1;
	if(numi.value==0)
	{
	removeid=document.getElementById('Remove'+tdId+tdname);
	removeid.style.display="none"; 
	}
	calcUnitPrice(tdId,enhflg);
}
function checkAlpha(arg1,arg2) //only alphabets A-Z and a-z
{
     var Names=eval("document.forms[0]."+arg1);
     var Names1=Names.value;
     if(Names1 != null && Names1 != "")
     {
       var reg=/^[a-zA-Z]+$/;
       if(Names1.search(reg)==-1)
       {
            alert("Please enter only alphabets in "+arg2);
            return 0;
       }
       return 1;
     }
}
function IsNumeric_YN(arg,arg2)
{
   var textbox1 =  eval("document.forms[0]."+arg);
   var textval = textbox1.value;
   var ValidChars = "0123456789.";
   var IsNumber=true;
   var Char; 
   
   for (i = 0; i < textval.length && IsNumber == true; i++)
   { 
       Char = textval.charAt(i); 
       if (ValidChars.indexOf(Char) == -1) 
	   {
		alert("Only Numbers are allowed in "+arg2);
         textbox1.value="";       
         textbox1.focus();
		return false;
	   }
   }
}

function checkNonZero(id)
{
if(id.value!='')
{
if(id.value==0)
{
alert('Please enter value more than zero');
id.value='';
return false;
}
else
{
id.value=checkZero(id.value);
}
}
}

function checkZero(arg)
{
        while((parseInt(arg.substring(0,1))==0) && arg.length>1)
        {
                arg=arg.substring(1);
        }
        return arg;
}

function addDrugElement(tdId,tdname,enhflg)
{
	var currentCount = document.getElementById(tdId+tdname+'Count').value;		
	if(currentCount > 0 && (document.getElementById(tdId+tdname+'No'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'Name'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'Route'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'Strength'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'Dosage'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'Days'+currentCount).value=='' ||
	document.getElementById(tdId+tdname+'UnitPrice'+currentCount).value==''))
	{
	if(document.getElementById(tdId+tdname+'Name'+currentCount).value=='')
	{
	alert('Please enter the Name');
	document.getElementById(tdId+tdname+'Name'+currentCount).focus();
	return;
	}
	if(document.getElementById(tdId+tdname+'Route'+currentCount).value=='')
	{
	alert('Please enter the Route');
	document.getElementById(tdId+tdname+'Route'+currentCount).focus();
	return;
	}
	if(document.getElementById(tdId+'Strength'+currentCount).value=='')
	{
	alert('Please enter the Strength');
	document.getElementById(tdId+tdname+'Strength'+currentCount).focus();
	return;
	}
	if(document.getElementById(tdId+tdname+'Dosage'+currentCount).value=='')
	{
	alert('Please enter the Dosage');	
	document.getElementById(tdId+tdname+'Dosage'+currentCount).focus();
	return;	
	}
	if(document.getElementById(tdId+tdname+'Days'+currentCount).value=='')
	{
	alert('Please enter the Days');
	document.getElementById(tdId+tdname+'Days'+currentCount).focus();
	return;
	}
	if(document.getElementById(tdId+tdname+'UnitPrice'+currentCount).value=='')
	{
	alert('Please enter the Unit Price');	
	document.getElementById(tdId+tdname+'UnitPrice'+currentCount).focus();
	return;	
	}
	}	
	else
	{		
		var tdRow= document.getElementById(tdId+tdname);
		var numi = document.getElementById(tdId+tdname+'Count');
		var num = (document.getElementById(tdId+tdname+'Count').value -1)+ 2;   
		numi.value = num;
		var newdiv = document.createElement('div');  
		var divIdName = tdId+tdname+'divid'+num;       
		newdiv.setAttribute('id',divIdName); 
		newdiv.innerHTML ='<input type="text" id='+tdId+tdname+'No'+num+' name='+tdId+tdname+'No'+num+' value='+num+' style="width:12px" readOnly>&nbsp;<input type="text" id='+tdId+tdname+'Name'+num+' name='+tdId+tdname+'Name'+num+' style="width:70px" onBlur ="checkAlpha(this.id,\'Name\')" maxlength="50">&nbsp;<input type="text" id='+tdId+tdname+'Route'+num+' name='+tdId+tdname+'Route'+num+' style="width:68px" onBlur ="checkAlpha(this.id,\'Route\')" maxlength="50">&nbsp;<input type="text" id='+tdId+tdname+'Strength'+num+' name='+tdId+tdname+'Strength'+num+' style="width:33px"  onBlur="IsNumeric_YN(this.id,\'Strength\');checkNonZero(this)" maxlength="6" >&nbsp;<input type="text" id='+tdId+tdname+'Dosage'+num+' name='+tdId+tdname+'Dosage'+num+' style="width:33px"  onBlur="IsNumeric_YN(this.id,\'Dosage\');checkNonZero(this)" maxlength="6" >&nbsp;<input type="text" id='+tdId+tdname+'Days'+num+' name='+tdId+tdname+'Days'+num+' style="width:33px"  onBlur="IsNumeric_YN(this.id,\'Days\');calcUnitPrice(\''+tdId+'\',\''+enhflg+'\');checkNonZero(this)" maxlength="6">&nbsp;<input type="text" id='+tdId+tdname+'UnitPrice'+num+' name='+tdId+tdname+'UnitPrice'+num+' style="width:35px"  onBlur="IsNumeric_YN(this.id,\'Unit Price\');calcUnitPrice(\''+tdId+'\',\''+enhflg+'\');checkNonZero(this)" maxlength="6">';
															
		tdRow.appendChild(newdiv);
		var removeid=document.getElementById('Remove'+tdId+tdname);
		removeid.style.display=""; 
		if(num==10)
		{
		var addid=document.getElementById('Add'+tdId+tdname);
		addid.style.display="none"; 
		}		
	}
}

function calcUnitPrice(tdId,enhflg)
{
	
var  LabInvestUnitPrice=0, ImageologyUnitPrice=0, DrugUnitPrice=0, ImplantUnitPrice=0,AlosUnitPrice=0;
	if(document.getElementById(tdId+'LabInvestCount'))
	{	
	var LabInvestCount=document.getElementById(tdId+'LabInvestCount').value;
	for(i=1;i<=LabInvestCount;i++)	{
		obj=document.getElementById(tdId+"LabInvestUnitPrice"+i);
		if(obj!=null)
		{		
		LabInvestUnitPrice+=Number(obj.value);
		}
	}
	}
	if(document.getElementById(tdId+'ImageologyCount'))
	{
	var ImageologyCount=document.getElementById(tdId+'ImageologyCount').value;
	for(i=1;i<=ImageologyCount;i++)
	{
		obj=document.getElementById(tdId+"ImageologyUnitPrice"+i);
		if(obj!=null)
		{ 
		ImageologyUnitPrice+=Number(obj.value);
		}
	}
	}
	var obj1;
	if(document.getElementById(tdId+'DrugCount'))
	{
	var DrugCount=document.getElementById(tdId+'DrugCount').value;
	for(i=1;i<=DrugCount;i++)
	{
		obj=document.getElementById(tdId+"DrugUnitPrice"+i);
		obj1=document.getElementById(tdId+"DrugDays"+i);
		if(obj!=null && obj1 != null)
		{ 			
		DrugUnitPrice+=(Number(obj.value) * Number(obj1.value));
		}
	}
	}
	if(document.getElementById(tdId+'ImplantCount'))
	{
	var ImplantCount=document.getElementById(tdId+'ImplantCount').value;
	for(i=1;i<=ImplantCount;i++)
	{
		obj=document.getElementById(tdId+"ImplantUnitPrice"+i);
		if(obj!=null)
		{ 
		ImplantUnitPrice+=Number(obj.value);
		}
	}	
	}
	objALOS=document.getElementById(tdId+'ALOS1');
	objUnitPrice=document.getElementById(tdId+'UnitPrice1');
	if(objALOS!=null && objUnitPrice!=null)
	{ 	
	AlosUnitPrice+=Number(objALOS.value)*Number(objUnitPrice.value);
	}	
document.getElementById(tdId+'Total').value= parseInt(LabInvestUnitPrice)+parseInt(ImageologyUnitPrice)+parseInt(DrugUnitPrice)+parseInt(ImplantUnitPrice)+parseInt(AlosUnitPrice);
if(enhflg=='')	
	{
	totObj=document.getElementById('appxTotAmt');
	}
 else if(enhflg=='Enh')	
	{
//	totObj=document.getElementById('txtNWHAmt');
		totObj=document.getElementById('appxTotAmt');
	} 
var totalVal=0;
var totalVal1 = 0;

<c:forEach var='i' begin='0' end="${fn:length(StayTypeList)-1}"  > 
totalVal+=parseInt(document.getElementById('${StayTypeList[i].ID}Total').value);
</c:forEach>
<c:forEach var='i' begin='0' end="${fn:length(StayTypeList2)-1}"  > 
totalVal1+=parseInt(document.getElementById(enhflg+'${StayTypeList2[i].ID}Total').value);
</c:forEach>
totObj.value=(parseInt(totalVal)+parseInt(totalVal1));
}
	
function ChangeEnhStayType(StayTypeVal,display)
{
	
	if(StayTypeVal!='-1') 
	{
	if(display=='add')
	{
	document.getElementById('Enh'+StayTypeVal+'BLK').style.display='';
	document.getElementById('RemoveEnh'+StayTypeVal+'BLK').style.display='';
	}
	else if(display=='remove')
	{	
		
		if((document.getElementById('Enh'+StayTypeVal+'LabInvestCount').value>0)||
		(document.getElementById('Enh'+StayTypeVal+'ImageologyCount').value>0)||
		(document.getElementById('Enh'+StayTypeVal+'DrugCount').value>0)||
		(document.getElementById('Enh'+StayTypeVal+'ImplantCount').value>0))
		{
		alert('Please remove already added elememts');	
		}
		else if((document.getElementById(StayTypeVal+'LabInvestCount').value>0)||
				(document.getElementById(StayTypeVal+'ImageologyCount').value>0)||
				(document.getElementById(StayTypeVal+'DrugCount').value>0)||
				(document.getElementById(StayTypeVal+'ImplantCount').value>0))
{
			alert('Please remove already added elememts');	
			}
		else
		{
	document.getElementById('Enh'+StayTypeVal+'BLK').style.display='none';
	}
	}	
	}
	document.getElementById('EnhStayTypeId').value='-1';
}

function ChangeEnhAlosStayType(StayTypeVal,display)
{
	
	if(StayTypeVal!='-1' && StayTypeVal=='OTS')
	{
	if(surgSelected==0)
	{
	  alert("Please select Surgery/Therapy Details before selecting stay Type OT");
	  document.getElementById('EnhStayTypeAlosId').value='-1';
	  return false;
	}
	/* if(document.forms[0].MedicalMng.value=='N')		
	{
	  alert("For Non surgical cases ,OT stay type cannot be selected");
	  document.getElementById('EnhStayTypeAlosId').value='-1';
	  return false;
	} */
	}
	if(StayTypeVal!='-1') 
	{
	if(display=='add')
	{
	document.getElementById('Enh'+StayTypeVal+'BLK').style.display='';
	document.getElementById('RemoveEnh'+StayTypeVal+'BLK').style.display='';
	}
	else if(display=='remove')
	{			
	document.getElementById('Enh'+StayTypeVal+'BLK').style.display='none';
	document.getElementById('Enh'+StayTypeVal+'ALOS1').value='';
	document.getElementById('Enh'+StayTypeVal+'UnitPrice1').value='';
	calcUnitPrice('Enh'+StayTypeVal,'Enh');
	}	
	}
		document.getElementById('EnhStayTypeAlosId').value='-1';
}	
function fn_getDocAvail(){ 
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getDocAvailLst&docType='+document.forms[0].docType.value+'&docSpec='+document.forms[0].docSpec.value;
	 xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {	
	        var resultArray=xmlhttp.responseText;
	        var resultArray = resultArray.split("*");
	        if(resultArray[0]!=null)
	        {	
	            resultArray1 = resultArray[0].replace("[","");
	            resultArray1 = resultArray1.replace("]","");    
	            var docList =resultArray1.split(","); 
	            if(docList.length>0)
	            {   
					if(document.forms[0].docAval.options!=null){  
						document.forms[0].docAval.options.length=0;
						document.forms[0].docAval.options[0]=new Option("--select--","-1");
					}
					
					
	                for(var i = 0; i<docList.length;i++)
	                {	
	                    var arr=docList[i].split("~");
	                     if(arr[1]!=null && arr[0]!=null)
	                     {
	                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                         document.forms[0].docAval.options[i+1] =new Option(val1,val2);
					   }
	                    else
	                    {
	                    	document.forms[0].docAval.options[0]=new Option("--select--","-1");
							
	                    }
	                }
	            }
	        }  
	        if(resultArray[1]!= null)
	        	{
	        	 resultArray2 = resultArray[1].replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var disList = resultArray2.split(",");
		         if(disList.length>0)
		            {   
						if(document.forms[0].subCategory.options!=null){  
							document.forms[0].subCategory.options.length=0;
							document.forms[0].subCategory.options[0]=new Option("--select--","-1");
						}
						
						
		                for(var i = 0; i<disList.length;i++)
		                {	
		                    var arr=disList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null)
		                     {
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                         document.forms[0].subCategory.options[i+1] =new Option(val1,val2);
						   }
		                    else
		                    {
		                    	document.forms[0].subCategory.options[0]=new Option("--select--","-1");
								
		                    }
		                }
		            }
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	    //Ajax end
	}
	
function fn_getDocDetails() 
{
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getDocdetails&regNo='+document.forms[0].docAval.value;
	 xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {	
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!=null)
	        {	
	            resultArray = resultArray.replace("[","");
	            resultArray = resultArray.replace("]","");            
	            var docList = resultArray.split(","); 
				
	            if(docList.length>0)
	            {   
	                for(var i = 0; i<docList.length;i++)
	                {	
	                    var arr=docList[i].split("~");
	                     if(arr[1]!=null && arr[0]!=null)
	                     {
	                         var val2 = arr[1].replace(/^\s+|\s+$/g,"");
	                         var val1 = arr[0].replace(/^\s+|\s+$/g,"");
	                         var val3 = arr[2].replace(/^\s+|\s+$/g,"");
	                         var val4 = arr[3].replace(/^\s+|\s+$/g,"");
	                         var val5 = arr[4].replace(/^\s+|\s+$/g,"");
	                         document.getElementById('docName').value=val2;
	                         document.getElementById('docRegNo').value=val1;
	                         document.getElementById('docMobNo').value=val4;
	                         document.getElementById('docQual').value=val5;
	                         document.getElementById('docNo').value=val3;
	                         document.getElementById('docDet').style.display="";
					   }
	                     else
	                    	 {
	                    	  document.getElementById('docName').value='';
		                         document.getElementById('docRegNo').value='';
		                         document.getElementById('docMobNo').value='';
		                         document.getElementById('docQual').value='';
		                         document.getElementById('docNo').value='';
		                         document.getElementById('docDet').style.display="none";
	                    	 }
	                    
	                }
	            }
	        }   
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
	
function fn_getSurgeryList()
{
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSurgeryLst&disMainId='+document.forms[0].docSpec.value+'&disSubId='+document.forms[0].subCategory.value;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var surList = resultArray1.split(","); 
			        }
					
		            if(surList.length>0)
		            {   
						if(document.forms[0].surgery.options!=null){  
							document.forms[0].surgery.options.length=0;
							document.forms[0].surgery.options[0]=new Option("--select--","-1");
						}
						 for(var i = 0; i<surList.length;i++)
			                {	
			                    var arr=surList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].surgery.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                    	document.forms[0].surgery.options[0]=new Option("--select--","-1");
										
				                    }
			                }
		            }
		    	
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}
function fn_getSpecialInvestigations()
{
	var xmlhttp;
	var url;
	var caseId = '82277';
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSpecialInvestigation&suregryId='+document.forms[0].surgery.value;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var invstList = resultArray1.split(","); 
			        }
		            if(invstList.length>0)
		            {   
						  var my2div = document.getElementById('myDivSplUpload');
	                         if (my2div != null) 
	                     	{
	                     		 while (my2div.hasChildNodes()) 
	                     		 {  my2div.removeChild(my2div.lastChild);    }
	                     	 }
	                         
						 for(var i = 0; i<invstList.length;i++)
			                {	
			                    var arr=invstList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         var ni1 = document.getElementById('myDivSpl');
			                         var newdiv1 = document.createElement('div');
			                         var divIdName1 = 'my'+i+'DivSpl';
			                         newdiv1.setAttribute('id',divIdName1);
			                         var divappend = '';
			                         var divappend1 ='';
			                         var name1 = val1;
			                         var value1 = val2;
			                         
			                         newdiv1.innerHTML ='<br><input  type="checkbox" name="checkspl'+value1+'" value="'+name1+'" />  '+name1+'  ';
			                         ni1.appendChild(newdiv1);
			                         // adding frames for attachments
			                          var ni = document.getElementById('myDivSplUpload'); 
			                         var newdiv = document.createElement('div');
                                    var divIdName = 'my'+i+'DivSplUpload';  
 									newdiv.setAttribute('id',divIdName);
 		 newdiv.innerHTML =  '<td nowrap="nowrap"><br><iframe id="iframe1'+value1+'" name="I'+name1+'1" height="32" style="width:100%;border:1;"  src="/<%=context%>/preauthDetails.do?actionFlag=getCaseAtachments&caseId='+caseId+'&mode=add&uploadType=SplInvest&surgId='+document.forms[0].surgery.value+'&splinvestdesc='+name1+'&splinvestid='+value1+'&spltype=PRE" frameborder=no scrolling=no> </iframe></td>';
										ni.appendChild(newdiv);
										    splList[i] = new Array();
							                splList[i][0] = name1;
							                splList[i][1] = value1;
							   }
			                     
			                }
						 if(document.forms[0].surgery.options!=null){  
								document.forms[0].surgery.options.length=0;
								document.forms[0].surgery.options[0]=new Option("--select--","-1");
							}
		            }
		    	
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	
	}
	
function fn_addSurgicalDtls()
{
	var category = document.forms[0].surgery.value;
	var SubCategory = document.forms[0].subCategory.value;
	var surgeryTherapy=document.forms[0].docSpec.value;
	var ichecked =0;
	var iNotChecked=0;
	
	/** check for whether special investigations are checked are not**/
	 for(var cntspl=0;cntspl < splList.length;cntspl++)
    {
		 
      var splInvest1 = eval("document.forms[0].checkspl"+splList[cntspl][1]);
      if(splInvest1.checked)  
      {   
        ichecked=1;     
      }
      else 
      {   
        iNotChecked=1;                  
      }
    }  
    if(ichecked == 0 && splList.length > 1)
    {  
      alert("Atleast one Special Investigation is mandatory");           
      return;
    }
    
    for(var cntspl=1;cntspl < splList.length;cntspl++)
    {
      var splInvest1 = eval("document.forms[0].checkspl"+splList[cntspl][1]);
      var cntsplValue=document.frames['iframe1'+splList[cntspl][1]].document.CaseAttachments.splCnt;
     alert(cntsplValue);
      if(splInvest1.checked && cntsplValue < 1 )
      {
        alert("Selected Special Investigation Attachments are Mandatory");
        addSplInvestAtchElement(surgid);
        return;
      }
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSurgeryDtls&suregryId='+document.forms[0].surgery.value+'&category='+category+'&subCat='+SubCategory;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var invstList = resultArray1.split(","); 
			        }
		            if(invstList.length>0)
		            {   
						 for(var j = 0; j<invstList.length;j++)
			                {
							 var i = parseInt(j)+parseInt(splCount)-1;
			                    var arr=invstList[j].split("~");
			                    var sno = document.getElementById('addSurgicalDivSno['+i+']');
			                    var snoNew = document.createElement('div');
			                    snoNew.innerHTML ='<td>'+splCount+'</td>';
			                    sno.appendChild(snoNew);
			                    
			                    	 var val1 = arr[0].replace(/^\s+|\s+$/g,"");
			                    	 var n1 = document.getElementById('addSurgicalDivCat['+i+']'); 
			                    	 var newdiv1 = document.createElement('div');
			                    	 newdiv1.innerHTML =  '<td>'+val1+'</td>';
			                    	 n1.appendChild(newdiv1);  
			                    	 
			                    	 var val2 = arr[1].replace(/^\s+|\s+$/g,"");
			                    	 var n2 = document.getElementById('addSurgicalDivSubCat['+i+']'); 
			                    	 var newdiv2 = document.createElement('div');
			                    	 newdiv2.innerHTML =  '<td>'+val2+'</td>';
			                    	 n2.appendChild(newdiv2);
			                    	 
			                    	 var val3 = arr[2].replace(/^\s+|\s+$/g,"");
			                    	 var n3 = document.getElementById('addSurgicalDivSurgery['+i+']'); 
			                    	 var newdiv3 = document.createElement('div');
			                    	 newdiv3.innerHTML =  '<td>'+val3+'</td>';
			                    	 n3.appendChild(newdiv3);
			                    	    
			                    	 var val4 = arr[3].replace(/^\s+|\s+$/g,"");
			                    	 var n4 = document.getElementById('addSurgicalDivAmt['+i+']'); 
			                    	 var newdiv4 = document.createElement('div');
			                    	 newdiv4.innerHTML =  '<td>'+val4+'</td>';
			                    	 n4.appendChild(newdiv4);
			                    	 
			                    	 var val5 = arr[4].replace(/^\s+|\s+$/g,"");
			                    	 var n5 = document.getElementById('addSurgicalDivPay['+i+']'); 
			                    	 var newdiv5 = document.createElement('div');
			                    	 newdiv5.innerHTML =  '<td>'+val5+'</td>';
			                    	 n5.appendChild(newdiv5);
			                    	
			                    var splId = document.getElementById('addSurgicalDivSpl['+i+']');
			                    var splIdNew = document.createElement('div');
			                    splIdNew.innerHTML ='<td>'+splCount+'</td>';
			                    splId.appendChild(splIdNew);
			                    
			                    splCount++;
			         
			                     
			                }
		            }
		    	
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	}
	function fn_submitPreauth()
	{
		document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitPreauth";
		document.forms[0].submit();
	}
</script>
<style>
.tableGrey{margin:0;padding:1px solid grey;border:1px solid grey;background:#fff;}
</style>
</head>
<body>
<form name="preauthDetailsForm" action="/preauthDetails.do" enctype="multipart/form-data" method="post">
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" class="tableClass1">
<tr ><td  colspan="6" align="center"  class="tbheaderLightSeaGreen" ><b> Preauth Details </b></td></tr>
<tr ><td  colspan="6" align="left"  class="tbheaderLightSeaGreen" > 1(a). NWH Details </td></tr>
<tr ><td > Name </td><td></td><td>Address</td><td></td></tr>
<tr ><td  colspan="6" align="left"  class="tbheaderLightSeaGreen" >1(b). Treating Doctor Details </td></tr>
<tr><td colspan="6"><table width="100%" border="0" >
<tr><td > Doctor type </td>
<td><html:select  name="preauthDetailsForm" property="docType" style="width:150px;">
<option  value="">----Select----</option>
<html:options collection="docTypeList" property="ID" labelProperty="VALUE"  />
</html:select></td>
<td>Doctor Speciality</td><td width="">
<html:select  name="preauthDetailsForm" property="docSpec" style="width:280px;" onchange="javascript:fn_getDocAvail()">
<option  value="">----Select----</option>
<html:options collection="docSpecList" property="ID" labelProperty="VALUE"  />
</html:select>
</td><td>Doctors Available</td>
<td>
<html:select  name="preauthDetailsForm" property="docAval" style="width:150px;" onchange="javascript:fn_getDocDetails()">
<option  value="">----Select----</option>
<html:options collection="docAvalList" property="ID" labelProperty="VALUE"  />
</html:select>
</td>
</tr>
</table></td></tr>
<!-- get doc details -->
<div id="docDet" name="docDet" style='display:none'>
<tr >
<td >
Name</td><td><input readonly type="text" style="width:150px" id="docName" ></td>
<td>Regn No</td><td><input readonly type="text" style="width:100px" id="docRegNo" ></td>	
<td>Qualification</td><td><input readonly type="text" style="width:150px" id="docQual" ></td></tr>
<tr>	
<td>Mobile No</td><td><input readonly type="text" style="width:150px" id="docMobNo"></td>
<td>Phone No</td><td><input readonly type="text" style="width:150px" id="docNo" >	 
 </td>	
</tr>
 </div>
<tr><td>&nbsp;</td></tr>
<tr ><td  colspan="6" align="left"  class="tbheaderLightSeaGreen" ><b>2. Online Case Sheet </b></td></tr>
<tr><td class="tbheaderLightSeaGreen" colspan="3">(i) History of present illness : <font color="red">*</font></td>
<td class="tbheaderLightSeaGreen" colspan="3">(ii) Past History : <font color="red">*</font> </td></tr>
<tr><td colspan="3"><html:textarea name="preauthDetailsForm" property="presentillness" style="width:400px;"></html:textarea> </td>
<td colspan="3"><html:textarea name="preauthDetailsForm" property="pasthistory" style="width:400px;"></html:textarea> </td></tr>

<tr><td class="tbheaderLightSeaGreen" colspan="3">(iii) Examination Findings : <font color="red">*</font></td>
<td class="tbheaderLightSeaGreen" colspan="3">(iv) Provisional Diagnosis : <font color="red">*</font> </td></tr>
<tr><td colspan="3"><html:textarea name="preauthDetailsForm" property="examinationFindings" style="width:400px;"></html:textarea> </td>
<td colspan="3"><html:textarea name="preauthDetailsForm" property="provisionalDisg" style="width:400px;"></html:textarea> </td></tr>

<tr ><td  colspan="6" align="left"  class="tbheaderLightSeaGreen" ><b>(v). Investigations </b></td></tr>
<tr><td class="tbheaderLightSeaGreen" colspan="3">Routine : <font color="red">*</font></td>
<td class="tbheaderLightSeaGreen" colspan="3">Special : <font color="red">*</font> </td></tr>
<tr><td colspan="3"><html:textarea name="preauthDetailsForm" property="routine" style="width:400px;"></html:textarea> </td>
<td colspan="3"><html:textarea name="preauthDetailsForm" property="special" style="width:400px;"></html:textarea> </td></tr>

<tr ><td  colspan="6" align="left"  class="tbheaderLightSeaGreen" ><b>(vi). Final Diagnosis: <font color="red">*</font> </b></td></tr>
<tr><td colspan="6"><html:textarea name="preauthDetailsForm" property="finalDig" style="width:820px;"></html:textarea> </td></tr>
</table>

<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center">
<tr><td class="tbheaderLightSeaGreen" align="left" colspan="4"><b>3.Plan Of Treatment(Therapy)</b></td></tr>
<tr><td width="40%">&nbsp; Category : <font color="red">*</font></td><td width="40%">Sub Category : <font color="red">*</font></td></tr>
<tr><td>&nbsp;<html:select  name="preauthDetailsForm" property="category" style="width:400px;">
<option  value="">----Select----</option>
<html:options collection="docSpecList" property="ID" labelProperty="VALUE"  />
</html:select></td>
<td><html:select  name="preauthDetailsForm" property="subCategory" style="width:400px;" onchange="javascript:fn_getSurgeryList()">
<option  value="">----Select----</option>
</html:select></td></tr>
<tr><td colspan="2">&nbsp;Surgery/Therapy : <font color="red">*</font></td></tr>
<tr><td  colspan="2">
&nbsp;<html:select  name="preauthDetailsForm" property="surgery" style="width:815px;" onchange="javascript:fn_getSpecialInvestigations()">
<option  value="">----Select----</option>
</html:select>
</td></tr>

<tr>
<td colspan="2"><table width="100%" border="1">
<tr>
<td>&nbsp; Special Investigation :<font color="red">*</font>&nbsp;&nbsp;</td>
<td nowrap="nowrap" colspan="3" height="32" rowspan="2">&nbsp;&nbsp;<div id="myDivSpl">    </div></td>
 <td nowrap="nowrap" colspan="6">&nbsp;&nbsp;<div id="myDivSplUpload">    </div></td></tr>
</table></td>
</tr>
<tr><td  colspan="2"> &nbsp;Treating Doctor Remarks  :<font color="red">*</font> </td></tr>
<tr><td  colspan="2">
 &nbsp;<html:textarea name="preauthDetailsForm" property="treatingDocRmks" style="width:820px;"></html:textarea>
</td></tr>
<tr><td><input type="button" name="addSurgicalDtls" value="Add Surgical Details"  onclick="javascript:fn_addSurgicalDtls()"  /></td></tr>

<tr><td>&nbsp;Treatment Protocol</td></tr>
</table>
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center">
<tr class="tbheader"><td>SNo</td>
<td>Category</td>
<td>Sub Category</td>
<td>Surgery/Therapy</td>
<td>Spl Investigations</td>
<td>Amount</td>
<td>Payment Type</td>
<td>Days</td>
</tr>
<c:forEach var="i" begin="0" end="10" >
  <tr> <td id="addSurgicalDivSno[${i}]"></td><td id="addSurgicalDivCat[${i}]"></td>
 <td id="addSurgicalDivSubCat[${i}]"></td><td id="addSurgicalDivSurgery[${i}]"></td><td id="addSurgicalDivSpl[${i}]"></td><td id="addSurgicalDivAmt[${i}]"></td>
 <td id="addSurgicalDivPay[${i}]"></td>
 </tr> 
 </c:forEach>
</table>

<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center">
<tr class="tbheader"><td>Treating Doctor Remarks</td></tr>
</table>

<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center">
<tr class="tbheaderLightBrown"><td align="left" colspan="7">Admission Details</td></tr>
<tr><td >Admission</td>
<td><input type="radio" name="AdmissRadio" value="planned"/>Planned</td>
<td><input type="radio" name="AdmissRadio" value="emergency"/>Emergency</td>
<td>Date</td>
<td><input type="text" /></td>
<td>Proposed Surgery Date</td>
<td><input type="text" /></td>
</tr></table>

<c:if test="${fn:length( StayTypeList) gt 0 }" >

<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center">
<tr class="tbheaderLightBrown"><td colspan="20"><b>4.Cost Estimation Break-Up</b></td></tr>
<tr class="tbheaderLightBrown"><td rowspan="2"><b>Cost Center</b></td>
<td colspan="3" rowspan="2"><b>Lab Investigations</b></td>
<td colspan="3" rowspan="2"><b>Imageology</b></td>
<td colspan="10" align="center"><b>Pharmacy</b></td>
</tr>
<tr class="tbheaderLightBrown"><td colspan="7" align="center"><b>Drugs</b></td>
<td colspan="3" align="center"><b>Implants</b></td></tr>
<tr class="tbheaderLightBrown"><td width="10%">Type of Stay</td>
<td width="2%">Sno</td><td width="11%" align="center">Name</td><td width="4%">Unit Price</td>
<td width="2%">Sno</td><td width="11%" align="center">Name</td><td width="4%">Unit Price</td>
<td width="2%">Sno</td><td width="11%" align="center">Name</td><td width="11%" align="center">Route</td><td width="4%">Strength</td><td width="4%">Dosage</td><td width="4%">Days</td><td width="5%">UnitPrice</td>
<td width="2%">Sno</td><td width="11%" align="center">Name</td><td width="4%">Unit Price</td>
</tr>

<c:forEach var="i" begin="0" end="${fn:length(StayTypeList)-1}"  >
<tr id="Enh${StayTypeList[i].ID}BLK" style="display:none">
<td valign="top">${StayTypeList[i].VALUE}
<center>
	<input type="button" style="width:50px" value="Remove" id="RemoveEnh${StayTypeList[i].ID}BLK"  style="display:none" onClick="ChangeEnhStayType('${StayTypeList[i].ID}','remove');" >
	</center>
</td>

<td colspan="3" id="${StayTypeList[i].ID}LabInvest" name="${StayTypeList[i].ID}LabInvest" valign="top">
<center><input type="button" style="width:30px" value="Add" id="Add${StayTypeList[i].ID}LabInvest" onClick="addElement('${StayTypeList[i].ID}','LabInvest','');">
	<input type="button" style="width:50px" value="Remove" id="Remove${StayTypeList[i].ID}LabInvest"  style="display:none" onClick="removeElement ('${StayTypeList[i].ID}','LabInvest','');" >
	</center>
	<input type="hidden" id="${StayTypeList[i].ID}LabInvestCount" name="${StayTypeList[i].ID}LabInvestCount" value="0"/>
	<td colspan="3" valign="top" id="${StayTypeList[i].ID}Imageology" name="${StayTypeList[i].ID}Imageology">
	<center><input type="button" style="width:30px" value="Add" id="Add${StayTypeList[i].ID}Imageology" onClick="addElement('${StayTypeList[i].ID}','Imageology','');">
	<input type="button" style="width:50px" value="Remove" id="Remove${StayTypeList[i].ID}Imageology"  style='display:none' onClick="removeElement ('${StayTypeList[i].ID}','Imageology','');" >
	</center>
 <input type="hidden" id="${StayTypeList[i].ID}ImageologyCount" name="${StayTypeList[i].ID}ImageologyCount" value="0"/>	
<td colspan="7" valign="top" id="${StayTypeList[i].ID}Drug" name="${StayTypeList[i]}Drug">
	<center><input type="button" style="width:30px" value="Add" id="Add${StayTypeList[i].ID}Drug" onClick="addDrugElement('${StayTypeList[i].ID}','Drug','');">
	<input type="button" style="width:50px" value="Remove" id="Remove${StayTypeList[i].ID}Drug"  style='display:none' onClick="removeElement ('${StayTypeList[i].ID}','Drug','');" >
	</center>
	<input type="hidden" id="${StayTypeList[i].ID}DrugCount" name="${StayTypeList[i].ID}DrugCount" value="0"/>	
	</td>
<td colspan="3" valign="top" id="${StayTypeList[i].ID}Implant" name="${StayTypeList[i].ID}Implant">
	<center><input type="button" style="width:30px" value="Add" id="Add${StayTypeList[i].ID}Implant" onClick="addElement('${StayTypeList[i].ID}','Implant','');">
	<input type="button" style="width:50px" value="Remove" id="Remove${StayTypeList[i].ID}Implant"  style="display:none" onClick="removeElement ('${StayTypeList[i].ID}','Implant','');" >
	</center>
<input type="hidden" id="${StayTypeList[i].ID}ImplantCount" name="${StayTypeList[i].ID}ImplantCount" value="0"/>	
	</td>	
<input type="hidden" id="Enh${StayTypeList[i].ID}ImageologyCount" name="Enh${StayTypeList[i].ID}ImageologyCount" value="0"/>	
<input type="hidden" id="Enh${StayTypeList[i].ID}DrugCount" name="Enh${StayTypeList[i].ID}DrugCount" value="0"/>	
<input type="hidden" id="Enh${StayTypeList[i].ID}ImplantCount" name="Enh${StayTypeList[i].ID}ImplantCount" value="0"/>
<input type="hidden" id="Enh${StayTypeList[i].ID}LabInvestCount" name="Enh${StayTypeList[i].ID}LabInvestCount" value="0"/>	
<input type="hidden" id="${StayTypeList[i].ID}LabInvestName" name="${StayTypeList[i].ID}LabInvestName"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}LabInvestUnitPrice" name="${StayTypeList[i].ID}LabInvestUnitPrice"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}ImageologyName" name="${StayTypeList[i].ID}ImageologyName"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}ImageologyUnitPrice" name="${StayTypeList[i].ID}ImageologyUnitPrice"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}DrugName" name="${StayTypeList[i].ID}DrugName"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}DrugRoute" name="${StayTypeList[i].ID}DrugRoute"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}DrugStrength" name="${StayTypeList[i].ID}DrugStrength"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}DrugDosage" name="${StayTypeList[i].ID}DrugDosage"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}DrugDays" name="${StayTypeList[i].ID}DrugDays"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}DrugUnitPrice" name="${StayTypeList[i].ID}DrugUnitPrice"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}ImplantName" name="${StayTypeList[i].ID}ImplantName"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}ImplantUnitPrice" name="${StayTypeList[i].ID}ImplantUnitPrice"  value="" >
<input type="hidden" id="${StayTypeList[i].ID}StayType" name="${StayTypeList[i].ID}StayType" value=""/>
<input type="hidden" id="${StayTypeList[i].ID}Total" name="${StayTypeList[i].ID}Total" value="0"/>
</tr>
</c:forEach>
</table>
<br>
<table>
<tr><td>Select Stay Type </td>
<td> 
<select id="EnhStayTypeId" name="preauthDetailsForm" property="treatingDocRmks"  style="width:200px;" onChange="ChangeEnhStayType(this.value,'add')" >
<option  value="-1">----Select----</option>
<c:forEach var="lst"  items="${StayTypeList}"  >
	<option value="${lst.ID}"> ${lst.VALUE} </option>
	</c:forEach>	
</select>
</td>
</tr>
</table>
<br>
</c:if>

<c:if test="${fn:length(StayTypeList2) gt 0 }" >
<table border="0" width="70%"  cellpadding="1" cellspacing="1" >
<tr class="tbheaderLightBrown">
<td ><b>Type Of Stay</b></td>
<td><b>ALOS/AV</b></td>
<td><b>Unit Price</b></td>
</tr>
<c:forEach var="i" begin="0" end="${fn:length(StayTypeList2)-1}"  >
<tr id="Enh${StayTypeList2[i].ID}BLK" style="display:none"><td vlaign="top">
${StayTypeList2[i].VALUE}

	<input type="button" style="width:60px" value="Remove" id="RemoveEnh${StayTypeList2[i].ID}BLK"  style="display:none" onClick="ChangeEnhAlosStayType('${StayTypeList2[i].ID}','remove');" >

</td>
<td valign="top">
<input id="Enh${StayTypeList2[i].ID}ALOS1" name="Enh${StayTypeList2[i].ID}ALOS1" type="text" style="width:30px" maxlength="2" onBlur="IsNumeric_YN(this.id,'ALOS/AV');calcUnitPrice('Enh${StayTypeList2[i].ID}','Enh');checkNonZero(this)" ></td>
	<td valign="top"><input id="Enh${StayTypeList2[i].ID}UnitPrice1" name="Enh${StayTypeList2[i].ID}UnitPrice1" type="text" style="width:50px" maxlength="6" onBlur="IsNumeric_YN(this.id,'Unit Price');calcUnitPrice('Enh${StayTypeList2[i].ID}','Enh');checkNonZero(this)" ></td>
	<input type="hidden" id="Enh${StayTypeList2[i].ID}StayType" name="Enh${StayTypeList2[i].ID}StayType" value=""/>
	<input type="hidden" id="Enh${StayTypeList2[i].ID}AlosAvail" name="Enh${StayTypeList2[i].ID}AlosAvail" value="${StayTypeList2[i].VALUE}"/>
	<input type="hidden" id="Enh${StayTypeList2[i].ID}Total" name="Enh${StayTypeList2[i].ID}Total" value="0"/>
     <input type="hidden" id="${StayTypeList2[i].ID}Total" name="${StayTypeList2[i].ID}Total" value="0"/>
</tr>
</c:forEach>
</table>
<table>
<tr><td>Select Stay Type</td>
<td><select id="EnhStayTypeAlosId" onChange="ChangeEnhAlosStayType(this.value,'add')"   style="width:200px;" >
	<option value="-1">--select--</option>
	<c:forEach var="i" begin="0" end="${fn:length(StayTypeList2)-1}"  >
	<option value="${StayTypeList2[i].ID}"> ${StayTypeList2[i].VALUE} </option>
	</c:forEach>	
	</select>	</td>
</tr>
</table>
<br>
<table>
<tr><td>Total Estimated Cost</td>
<td>
<input type="text" name="appxTotAmt" id="appxTotAmt" value="" readonly="true"> 														
</td>
</tr>
<tr><td>Previously Consumed Amount</td>
<td>
<input id="Text60" type="text" name="PrevConsumedAmt1" value='' readonly="true">													
</td>
</tr>
<tr><td>Total Package Amount Admissible under  the scheme Rs.</td>
<td>
<input id="Text60" type="text" name="totPkgAmt" value='' readonly="true">  
</td>
</tr>
<tr><td><input type="button" name="submitPreauth" value="Submit" onclick="javascript:fn_submitPreauth()" /></td></tr>
</table>

</c:if>
 <input type="hidden" name="txtNWHAmt" id="txtNWHAmt" value=0 />
 <c:forEach var="i" begin="0" end="10" >
 <input type="hidden" name="addSurgicalDivSno[${i}]" id="addSurgicalDivSno[${i}]" value=""/>
 <input type="hidden" name="addSurgicalDivCat[${i}]" id="addSurgicalDivCat[${i}]" value=""/>
 <input type="hidden" name="addSurgicalDivSubCat[${i}]" id="addSurgicalDivSubCat[${i}]" value=""/>
 <input type="hidden" name="addSurgicalDivSurgery[${i}]" id="addSurgicalDivSpl[${i}]" value=""/>
 <input type="hidden" name="addSurgicalDivSpl[${i}]" id="addSurgicalDivSpl[${i}]" value=""/>
 <input type="hidden" name="addSurgicalDivPay[${i}]" id="addSurgicalDivPay[${i}]" value=""/>
 <input type="hidden" name="addSurgicalDivAmt[${i}]" id="addSurgicalDivAmt[${i}]" value=""/>
 </c:forEach>
</form>
</body>
</fmt:bundle>
</html>