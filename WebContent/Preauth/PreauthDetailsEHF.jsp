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
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeScrollbar.jsp"%>
<script type="text/javascript">
var splCount =1;
var splList = new Array();
var splInvestdata = new Array();
var casSurglist = null;
var totPkgAmt = '';
var splInvestIds = null;
var surgeryId = null;
var surgertIdsarray = new Array();
var testAmt = null;
var testSurg = null;
function fn_getsubCat(){ 
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getDocAvailLst&docSpec='+document.forms[0].category.value;
	 xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {	
	        var resultArray=xmlhttp.responseText;
	        var resultArray = resultArray.split("*");
	       
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
	
function fn_getSurgeryList()
{
	//alert(document.forms[0].category.value);
	//alert(document.forms[0].therapyType.value);
	if(document.forms[0].category.value == null || document.forms[0].category.value =='' || document.forms[0].category.value=='-1' ||document.forms[0].therapyType.value == null || document.forms[0].therapyType.value=='')
		{
		return ;
		}
	if(document.forms[0].therapyType.value != null && document.forms[0].therapyType.value!='')
		{
		if(document.forms[0].category.value == null || document.forms[0].category.value =='' || document.forms[0].category.value=='-1')
		{
			alert('Please select Therapy Category');
		return ;
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSurgeryLst&disMainId='+document.forms[0].category.value+'&therapyType='+document.forms[0].therapyType.value;
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
function fn_getSpecialInvestigations_test()
{
	var xmlhttp;
	var url;
	var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
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
		        var my2div = document.getElementById('myDivSplUpload');
                if (my2div != null) 
            	{
            		 while (my2div.hasChildNodes()) 
            		 {  my2div.removeChild(my2div.lastChild);    }
            	 }
                var myDivSpl = document.getElementById('myDivSpl');
                if (myDivSpl != null) 
                	{
                		 while (myDivSpl.hasChildNodes()) 
                		 {  myDivSpl.removeChild(myDivSpl.lastChild);    }
                	 }
                document.getElementById('myDivSpl').style.display="";
				 document.getElementById('myDivSplUpload').style.display="";
	            if(invstList.length>0)
	            { 
	            	for(var i = 0; i<invstList.length;i++)
	                {	
	                    var arr=invstList[i].split("~");
	                     if(arr[1]!=null && arr[0]!=null)
	                     {
	                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                         var name1 = val1;
	                         var value1 = val2;
	                         var attachTable=document.getElementById("myDivSpl");
								var attachData=attachTable.innerHTML;
								//alert(attachData);
								attachData=attachData.replace("</TBODY>","");
								attachData = attachData + '<table width="100%" cellpadding="3"><tr><td width="30%"><input  type="checkbox" id="'+value1+'" name="checkspl'+value1+'" value="'+name1+'" />  '+name1+' </td><td width="70%"><iframe id="iframe1'+value1+'" name="iframe1'+value1+'1" height="32" style="width:100%;border:1;"  src="/<%=context%>/preauthDetails.do?actionFlag=getCaseAtachments&caseId='+caseId+'&mode=add&uploadType=SplInvest&surgId='+document.forms[0].surgery.value+'&splinvestdesc='+name1+'&splinvestid='+value1+'&spltype=PRE" frameborder=no scrolling=no> </iframe></td></tr></table>';
								attachTable.innerHTML = attachData;
								 splList[i] = value1;
								
	                     }
	            }
	            	 if(document.forms[0].surgery.options!=null){  
						 surgeryId = document.forms[0].surgery.value;
							document.forms[0].surgery.options.length=0;
							document.forms[0].surgery.options[0]=new Option("--select--","-1");
						}
		    }
		 
		}
	
	}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}
function fn_getSpecialInvestigations()
{
	var xmlhttp;
	var url;
	var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
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
	                         var myDivSpl = document.getElementById('myDivSpl');
	                         if (myDivSpl != null) 
		                     	{
		                     		 while (myDivSpl.hasChildNodes()) 
		                     		 {  myDivSpl.removeChild(myDivSpl.lastChild);    }
		                     	 }
	                         document.getElementById('myDivSpl').style.display="";
							 document.getElementById('myDivSplUpload').style.display="";
	                         
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
			                         
			                         newdiv1.innerHTML ='<br><input  type="checkbox" id="'+value1+'" name="checkspl'+value1+'" value="'+name1+'" />  '+name1+'  ';
			                         ni1.appendChild(newdiv1);
			                         // adding frames for attachments
			                          var ni = document.getElementById('myDivSplUpload'); 
			                         var newdiv = document.createElement('div');
                                    var divIdName = 'my'+i+'DivSplUpload';  
 									newdiv.setAttribute('id',divIdName);
 		 newdiv.innerHTML =  '<td nowrap="nowrap"><br><iframe id="iframe1'+value1+'" name="iframe1'+value1+'1" height="32" style="width:100%;border:1;"  src="/<%=context%>/preauthDetails.do?actionFlag=getCaseAtachments&caseId='+caseId+'&mode=add&uploadType=SplInvest&surgId='+document.forms[0].surgery.value+'&splinvestdesc='+name1+'&splinvestid='+value1+'&spltype=PRE" frameborder=no scrolling=no> </iframe></td>';
										ni.appendChild(newdiv);
										    //splList = new Array();
							               // splList[0] = name1;
							                splList[i] = value1;
							   }
			                     
			                }
						 if(document.forms[0].surgery.options!=null){  
							 surgeryId = document.forms[0].surgery.value;
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
	var category = document.forms[0].category.value;
    var splInvest = null;
	var ichecked =0;
	var iNotChecked=0;
	var remarks = document.forms[0].treatingDocRmks.value;
	/* chcek for all mandatory fields selected*/
	if(category == null || category =='' || category =='-1')
		{
		alert('Please select the category');
		return;
		}
	if( surgeryId == null || surgeryId =='')
	{
	alert('Please select the Surgery');
	return;
	}
	//alert(surgertIdsarray.length);
	/**check for already added surgical dtls **/
	for(var i=0; i<surgertIdsarray.length;i++)
		{
		if(surgertIdsarray[i]==surgeryId)
			{
			alert('Surgery already added');
			fn_clearFields();
			return;
			}
		}	
	
	/** check for whether special investigations are checked are not**/
	 for(var cntspl=0;cntspl < splList.length;cntspl++)
    {
		 
      var splInvest1 = eval("document.forms[0].checkspl"+splList[cntspl]);
      if(splInvest1.checked)  
      { 
        ichecked=1; 
        if(splInvest == null || splInvest=='')
        	{
        	 splInvestIds =splInvest1.id;
        	  splInvest = splInvest1.value;
        	}
      
        else
        	{
        	 splInvestIds =splInvestIds+"$"+splInvest1.id;
        	splInvest =splInvest+" , "+splInvest1.value;
        	}
        	
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
    
    for(var cntspl=0;cntspl < splList.length;cntspl++)
    {
      var splInvest1 = eval("document.forms[0].checkspl"+splList[cntspl]);
     // alert(document.getElementById('iframe1'+splList[cntspl][1]).contentWindow.document.getElementById('splCnt').value);
      //alert(document.getElementById('iframe1'+splList[cntspl][1]).contentWindow.document.getElementById('fileName').value);
      var cntsplValue=document.getElementById('iframe1'+splList[cntspl]).contentWindow.document.getElementById('fileName').value;
      if(splInvest1.checked && cntsplValue < 1 )
      {
        alert("Selected Special Investigation Attachments are Mandatory");
        return;
      }
      else
    	  {
    	  }
    }
    if(remarks == null || remarks =='' || remarks =='-1')
	{
	alert('Treating doctor remarks are mandatory');
	return;
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSurgeryDtls&suregryId='+surgeryId+'&category='+category;
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
							 var arr=invstList[j].split("~");
							 var val1 = arr[0].replace(/^\s+|\s+$/g,"");
							 var val2 = arr[1].replace(/^\s+|\s+$/g,"");
							 var val3 = arr[2].replace(/^\s+|\s+$/g,"");
							 var val4 = arr[3].replace(/^\s+|\s+$/g,"");
							 var val5 = arr[4].replace(/^\s+|\s+$/g,"");
							var surgtable=document.getElementById("addSurgdiv");
							var sampleData=surgtable.innerHTML;
							sampleData=sampleData.replace("</TBODY>","");
							sampleData=sampleData+'<table border="0"   width="100%"  cellpadding="1" cellspacing="1" align="center"  id="SplInvest'+splCount+'"  ><tr ><td width="5%">'+splCount+'</td><td width="20%">'+val1+'</td><td width="20%">'+val3+'</td><td width="15%">'+splInvest+'</td><td width="10%">'+val4+'</td><td width="25%">'+remarks+'</td><td width="5%"><a href="javascript:fn_removeSplInvest('+splCount+','+val4+')" >Delete</a></td></tr></table>';
							surgtable.innerHTML=sampleData;
							splInvestdata[splCount] = new Array();
							splInvestdata[splCount][0]=surgeryId;
							splInvestdata[splCount][1]=category;
							splInvestdata[splCount][2]=remarks;
							splInvestdata[splCount][3]=splInvestIds;
							splInvestdata[splCount][4]="*";
							document.forms[0].splInvest.value=splInvestdata;
							alert('Surgery'+val3 + 'added');
							fn_addAmt(val4);
							surgertIdsarray.push(surgeryId);
							fn_clearFields();
			                }
						 splCount++;
		            }
		    	
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	}
	function fn_removeSplInvest(splcnt,amount)
	{
	fn_deleteSurgId(splInvestdata[splcnt][0]);
	delete splInvestdata[splcnt];
	var surgtable=document.getElementById("addSurgdiv");
	var sampleData=surgtable.innerHTML;
	var remElement = document.getElementById("SplInvest"+splcnt);
	surgtable.removeChild(remElement);
	fn_deleteAmt(amount);
	}
	function fn_submitPreauth()
	{
		var flag = fn_testMandatoryAttach('submit');
		
	}
	function fn_hideTable(value,casesurg,amount,surgerId)
	{
	document.getElementById(value).style.display='none';
	if(casSurglist != null)
		casSurglist = casSurglist+"~"+casesurg;
	else
		casSurglist = casesurg;
	document.forms[0].casSugeryId.value = casSurglist;
	fn_deleteAmt(amount);
	fn_deleteSurgId(surgerId);
	}
	function fn_setTotalAmt()
	{
		document.forms[0].totPkgAmt.value = totPkgAmt;
		
	}
	function fn_deleteAmt(amount)
	{
		totPkgAmt = parseInt(totPkgAmt)-parseInt(amount);	
		document.forms[0].totPkgAmt.value = totPkgAmt;
	}
	function fn_addAmt(amount)
	{
		//alert(totPkgAmt);
		if(totPkgAmt !='')
			{
			if(parseInt(totPkgAmt) != null && parseInt(totPkgAmt) !='' )
			{
			totPkgAmt = parseInt(totPkgAmt)+parseInt(amount);	
			}	
			else
			{
			totPkgAmt = parseInt(amount);	
			}
			}
		
		else
			{
			totPkgAmt = parseInt(amount);	
			}
	
		document.forms[0].totPkgAmt.value = totPkgAmt;
	}
	function checkRadio(val)
	{
		document.forms[0].therapyType.value=val;
		fn_getSurgeryList();
	}
	function fn_deleteSurgId(surgerId){
		for(var i=0;i<surgertIdsarray.length;i++)
			{
			if(surgertIdsarray[i]==surgerId)
				{
				delete surgertIdsarray[i];
				}
			}
	}
	function fn_clearFields()
	{
		if(document.getElementById('therapyType').checked)
			{
			document.getElementById('therapyType').checked=false;
			}
		document.forms[0].treatingDocRmks.value ="";
		var my2div = document.getElementById('myDivSplUpload');
         if (my2div != null) 
     	{
     		 while (my2div.hasChildNodes()) 
     		 {  my2div.removeChild(my2div.lastChild);    }
     	 }
         var myDivSpl = document.getElementById('myDivSpl');
         if (myDivSpl != null) 
         	{
         		 while (myDivSpl.hasChildNodes()) 
         		 {  myDivSpl.removeChild(myDivSpl.lastChild);    }
         	 }
         splList = new Array();	
         surgeryId = null;
	}
	
	function fn_addAttachments()
	{
		 window.open("/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId="+<bean:write name="preauthDetailsForm" property="caseId" />+"&caseAttachmentFlag=Y&PreauthFlag=Y", 'window1',
			'toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');
	}
	function fn_testMandatoryAttach(varType)
	{
		var flag = null;
		var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
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
		 url = '/<%=context%>/preauthDetails.do?actionFlag=checkMandatoryAttch&caseId='+caseId;
		 xmlhttp.onreadystatechange=function()
			{
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]!=null)
				        {	
				           if(resultArray[0] =='success')
				        	   {
				        	   if(varType != null && varType=='submit')
				        		   {
				        		   document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitPreauthEhf";
					   			   document.forms[0].submit();	
				        		   }
				        	   else if(varType != null && varType=='sentForPreauth')
				        		   {
				        		   document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=sentForPreauth";
					   			   document.forms[0].submit();	
				        		   }
				        	  
				        	   }
				           else
				        	   {
				        	   alert(resultArray[0]);
				        	  return;
				        	   }
				        } 
			    }
			}
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		//return flag;
	}
	function fn_sentPreauth()
	{
		if(!document.getElementById('sentForPreauth').checked)
			{
			alert('Please check the Sent For Preauthorisation checkbox');
			return;
			}
		else
			{
			fn_testMandatoryAttach('sentForPreauth');
			}
	}
	function fn_setDisable()
	{
		if('${viewType}' !='ramco')
			{
			document.getElementById('selectionBlock').disabled=true;	
			}
		if('${viewType}' !='ramco' ||'${viewType}' !='mithra' )
		{
			//document.getElementById('submitPreauth').disabled=true;		
			//document.getElementById('addAttachments').disabled=true;	
		}
	}
	function fn_pexQuestions()
	{
		 window.open("/Operations/preauthDetails.do?actionFlag=pexQuestions&caseId="+<bean:write name="preauthDetailsForm" property="caseId" />+"&caseAttachmentFlag=Y&PreauthFlag=Y", 'window1',
		 'toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=50,left=50');	
	}
	function fn_pexVerify()
	{
		if(document.forms[0].genRemarks.value == null || document.forms[0].genRemarks.value=='')
			{
			alert('Please enter the remarks');
			return;
			}
	if(document.forms[0].quesFlag.value != null && document.forms[0].quesFlag.value !='' && document.forms[0].quesFlag.value =='Y')
		{
		 document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitNextLvl&submitType=pex";
		 document.forms[0].submit();	
		}
	else
		{
		alert('Please fill the verification form');
		return;
		}
	}
	function fn_SubmitNextLvl(type,val)
	{
		var cnt =0;
		var cntTemp=0;
		if(document.forms[0].genRemarks.value == null || document.forms[0].genRemarks.value=='')
		{
		alert('Please enter the remarks');
		return;
		}
		var rmks =document.forms[0].genRemarks.value;
		if(type=="ppd")
		  {
			searchEles = document.getElementById("L1CHK_LST").children; 
			alertMsg="Please fill the complete L2 checklist";
		  }
		  else if(type=="ptd")
		  {
			searchEles = document.getElementById("L2CHK_LST").children; 
			alertMsg="Please fill the complete L3 checklist";
		  }
		for(var i = 0; i < searchEles.length; i++) 
		{   
			if(searchEles[i].type == 'radio')
			{				
				if(document.getElementById(searchEles[i].id).checked)
				{
					cntTemp=cntTemp+1;
				}
				cnt=cnt+1;
			} 
		}
		cnt = cnt /2; //cnt is divided by 3 as there are 3 elements in each radio group
	    if(cntTemp != cnt)
	    {
	    	alert(alertMsg);
			//buttonblock.style.display='';
			//actblock.style.display='none';
	    	return;
	    }
	    else
	    	{
	    	alertMsg ="";
	    	}
				for(var i = 0; i < searchEles.length; i++) 
				{   
					if(searchEles[i].type == 'text')
					{	
						rmks=rmks+document.getElementById(searchEles[i].id).value+"?";						
					}			
					if(searchEles[i].type == 'radio' && document.getElementById(searchEles[i].id).checked)
					{	
						rmks=rmks+document.getElementById(searchEles[i].id).value+"  ";
					}	 
				}
				document.forms[0].genRemarks.value=rmks;
		//alert(document.forms[0].genRemarks.value);
		if(alertMsg =="")
			{
			document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitNextLvl&submitType="+type+"&submitVal="+val;
			 document.forms[0].submit();
			}
	}
</script>
</head>
<body onload="javascript:fn_setTotalAmt();fn_setDisable()">
<html:form action="/preauthDetails.do" enctype="multipart/form-data" method="post">
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" class="tableClass1">
<tr ><td  colspan="6" align="center"  class="tbheader" ><b> Preauth Details </b></td></tr>
<tr><td  colspan="6" align="left"  class="tbheader" ><b> NWH Details </b></td></tr>
<tr><td >Name</td><td><bean:write name="preauthDetailsForm"  property="hospitalName" /></td>
<td>Address</td><td colspan="3"><bean:write name="preauthDetailsForm"  property="hospAddress" /></td></tr>
<tr><td colspan="6" align="left" class="tbheader" ><b>Treating Doctor Details</b></td></tr>
<tr><td>Name</td><td><bean:write name="preauthDetailsForm"  property="docName" /></td>
<td>Reg No</td><td><bean:write name="preauthDetailsForm"  property="docReg" /></td>
<td>Speciality</td><td><bean:write name="preauthDetailsForm"  property="docType" /></td>
</tr>
<tr><td>Mobile Number</td><td><bean:write name="preauthDetailsForm"  property="docMobNo" /></td>
<td>Qualification</td><td><bean:write name="preauthDetailsForm"  property="docQual" /></td>
</tr>
<tr ><td  colspan="6" align="center"  class="tbheader" ><b> Diagnosis and Treatment </b></td></tr>
<tr ><td  colspan="6" align="center"  class="tbheader" ><b> Diagnosis </b></td></tr>
<tr><td>Diagnosis Type</td><td></td>
<td>Main Category Name</td><td></td>
<td>Category Name</td><td></td>
</tr>
<tr><td>Sub Category Name</td><td></td>
<td>Disease Name</td><td></td>
<td>Disease Anatomical Site Name</td><td></td>
</tr>
<logic:equal value="Y" name="preauthDetailsForm" property="telephonicFlag">
<tr><td>Telephonic Id</td><td><bean:write name="preauthDetailsForm"  property="telephonicId" /></td></tr>
<tr><td>Telephonic Remarks</td><td><bean:write name="preauthDetailsForm" property="telephonicRemarks" ></bean:write></td></tr>
</logic:equal>
</table>
<div id="selectionBlock" >
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" >
<tr><td class="tbheader" align="left" colspan="4"><b>Plan Of Treatment(Therapy)</b></td></tr>
<tr><td width="50%">&nbsp;Therapy Category : <font color="red">*</font></td><td width="50%">Thearapy Type : <font color="red">*</font></td></tr>
<tr><td>&nbsp;<html:select  name="preauthDetailsForm" property="category" style="width:400px;" onchange="javascript:fn_getSurgeryList()">
<option  value="">----Select----</option>
<html:options collection="docSpecList" property="ID" labelProperty="VALUE"  />
</html:select></td>
<td align="left" style="width:420px;">
	<html:radio name="preauthDetailsForm" property="therapyType" styleId="therapyType" value="M" onclick="javascript:checkRadio('Y')"/>&nbsp;&nbsp;Medical
	<html:radio name="preauthDetailsForm" property="therapyType" styleId="therapyType" value="S" onclick="javascript:checkRadio('N')"/>&nbsp;&nbsp;Surgical	
</tr>
<tr><td colspan="2">&nbsp;Surgery/Therapy : <font color="red">*</font></td></tr>
<tr><td  colspan="2">
&nbsp;<html:select  name="preauthDetailsForm" property="surgery" style="width:815px;" onchange="javascript:fn_getSpecialInvestigations_test()">
<option  value="">----Select----</option>
</html:select>
</td></tr>

<tr>
<td colspan="2"><table width="100%" border="0">
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
<tr align="center"><td colspan="4"><button class="but"   type="button" name="addSurgicalDtls" value="Add Surgical Details"  onclick="javascript:fn_addSurgicalDtls()"  >Add Surgical Details</button></td></tr>
</table>
</div>
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr  class="tbheader"  align="center"><td colspan="7">&nbsp;<b>Treatment Protocol</b></td></tr>
<tr  class="tbheader"><td width="5%">SNo</td>
<td width="20%">Category</td>
<td width="20%">Surgery/Therapy</td>
<td width="15%" >Special/Investigations </td>
<td width="10%">Amount</td>
<td width="25%">Remarks</td>
<c:if test="${viewType eq 'ramco' }" >
<td width="5%"></td>
</c:if>
</tr>
<logic:present name="preauthDetailsForm" property="lstPreauthVO">
<bean:size id="caseList" name="preauthDetailsForm" property="lstPreauthVO"/>
<logic:greaterThan value="0" name="caseList">
<logic:iterate id="results" name="preauthDetailsForm" property="lstPreauthVO" indexId="index" >
<tr id="splInvetsDataTable<%=index+1%>" style="display:true">
<td><%=index+1%></td>
<td><bean:write name="results" property="disMainName" /></td>
<td><bean:write name="results" property="surgeryName" /></td>
<td></td>
<td><bean:write name="results" property="amount" /></td>
<td><bean:write name="results" property="investRemarks" /></td>
<c:if test="${viewType eq 'ramco' }" >
<td><a href="javascript:fn_hideTable('splInvetsDataTable<%=index+1%>','<bean:write name="results" property="caseSurgId" />','<bean:write name="results" property="amount" />','<bean:write name="results" property="surgeryId" />')" >Delete</a></td>
</c:if>
<script>
if(totPkgAmt == null || totPkgAmt=='')
totPkgAmt = parseInt('<bean:write name="results" property="amount" />');
else
	totPkgAmt = totPkgAmt+parseInt('<bean:write name="results" property="amount" />');
	
surgertIdsarray.push('<bean:write name="results" property="surgeryId" />');
</script>
</tr>
</logic:iterate>
</logic:greaterThan>
</logic:present>
</table>
<div id="addSurgdiv" >
</div>
<c:if test="${viewType eq 'ppd'}">
<table width="100%" border="0" cellspacing="2" cellpadding="1" >
<tr><td>&nbsp;</td></tr>
							<tr><td align="center"><B>&nbsp;LEVEL 2 Checklist</B></td></tr>
							<tr><td align="center">
							<div id="L1CHK_LST">
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="1) L1 Check list Verified" id="L1_Q1">&nbsp;<input type="radio" value="YES" id="L1Chk_1" name="L1Chk">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L1Chk_2" value="NO" name="L1Chk">No<br>
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="2) Is diagnosis supported by sufficient evidence" id="L1_Q2">&nbsp;<input type="radio" id="L1Diag_1" value="YES" name="L1Diag">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L1Diag_2" value="NO" name="L1Diag">No<br>
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="3) Is the Suggested line of Treatment Supported by Sufficient evidence" id="L1_Q3">&nbsp;<input type="radio" id="L1Inv_1" value="YES" name="L1Inv">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L1Inv_2" value="No" name="L1Inv">No<br>
							</div>
							</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
						</table>
</c:if>
<c:if test="${viewType eq 'ptd'}">
<table width="100%" border="0" cellspacing="2" cellpadding="1" >
<tr><td>&nbsp;</td></tr>
							<tr><td align="center"><B>&nbsp;LEVEL 3 Checklist</B></td></tr>
							<tr><td align="center">
							<div id="L2CHK_LST">
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="1) L1 Check list Verified" id="L2_Q1">&nbsp;<input type="radio" value="YES" id="L2Chk_1" name="L2Chk">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Chk_2" value="NO" name="L2Chk">No<br>
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="2) L2 Check list Verified" id="L2_Q2">&nbsp;<input type="radio" value="YES" id="L2Chk1_1" name="L2Chk1">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Chk1_2" value="NO" name="L2Chk1">No<br>
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="3) Is beneficiary eligible" id="L2_Q3">&nbsp;<input type="radio" id="L2Eligi_1" value="YES" name="L2Eligi">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Eligi_2" value="NO" name="L2Eligi">No<br>
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="4) Is diagnosis supported by sufficient evidence" id="L2_Q4">&nbsp;<input type="radio" id="L2Diag_1" value="YES" name="L2Diag">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Diag_2" value="NO" name="L2Diag">No<br>
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="5) Is the suggested line of Treatment Supported by sufficient evidence" id="L2_Q5">&nbsp;<input type="radio" id="L2Inv_1" value="YES" name="L2Inv">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Inv_2" value="NO" name="L2Inv">No<br>
							</div>
							</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
						</table>
</c:if>

<table width="100%" border="0">
<c:if test="${ viewType ne 'ramco' }" >
<tr><td>&nbsp;</td></tr>
<tr><td  colspan="2"> &nbsp; Remarks  :<font color="red">*</font> </td></tr>
<tr><td  colspan="2">
 &nbsp;<html:textarea name="preauthDetailsForm" property="genRemarks" style="width:820px;"></html:textarea>
</td></tr>
</c:if>
<tr><td>&nbsp;</td></tr>
<tr><td width="50%">Total Package Amount Admissible under  the scheme Rs.</td>
<td width="50%">
<html:text property="totPkgAmt" name="preauthDetailsForm" readonly="true"></html:text>
<c:if test="${ viewType eq 'mithra' }" >
<tr><td>&nbsp;</td></tr>
<tr><td>
&nbsp;&nbsp;<input type="checkbox" name="sentForPreauth" id="sentForPreauth" > &nbsp;&nbsp;Sent Fot Preauthorisation</input>
</td></tr>
</c:if>
</td>
</tr>
<logic:present name="preauthDetailsForm" property="lstworkFlow">
<bean:size id="wrkList" name="preauthDetailsForm" property="lstworkFlow"/>
<logic:greaterThan value="0" name="wrkList">
<tr><td colspan="2">
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr  class="tbheader"  align="center"><td colspan="6">&nbsp;<b>Work Flow</b></td></tr>
<tr  class="tbheader" ><td width="5%">SNo</td>
<td width="20%">Date & Time</td>
<td width="20%">Role & Name</td>			
<td width="30%">Remarks</td>
<td width="20%">Action</td>
<td width="10%">Amount</td>		
</tr>



<logic:iterate id="results1" name="preauthDetailsForm" property="lstworkFlow" indexId="index1" >
<tr>
<td><%=index1+1%></td>

<td><bean:write name="results1" property="auditDate" /></td>
<td><c:choose>
							<c:when test="${preauthCaseSchemeId eq 'CD201' && results1.auditRole eq 'MITHRA'}" >
								VAIDYA MITHRA
							</c:when>
							<c:otherwise >
								<bean:write name="results1" property="auditRole" />
							</c:otherwise>
						</c:choose><bean:write name="results1" property="auditName" />
</td>
<c:choose>

<c:when test="${results1.auditComboRole eq 'CD424' && userRole ne 'CD9' &&  userRole ne 'CD10'}" >
<td><a href="#" onclick="javascript:fn_pexQuestions();"><bean:write name="results1" property="auditRemarks" /></a></td>
</c:when>
<c:otherwise  >
<td><bean:write name="results1" property="auditRemarks" /></td>
</c:otherwise>
</c:choose>
<td><bean:write name="results1" property="auditAction" /></td>
<td><bean:write name="results1" property="auditAmount" /></td>
</tr>

</logic:iterate>

</table>
</td></tr>
</logic:greaterThan>
</logic:present>
<c:if test="${viewType eq 'ppd'}">
<tr><td colspan="2" align="center">
<button class="but" type="button" name="recomendedApproval" id="recomendedApproval" value="Recomended Approval" style="width:140px" onclick="javascript:fn_SubmitNextLvl('ppd','Approve')" >Recomended Approval</button>
<button class="but" type="button" name="recomendedRejection" id="recomendedRejection" value="Recomended Rejection" style="width:140px" onclick="javascript:fn_SubmitNextLvl('ppd','Reject')" >Recomended Rejection</button>
<button class="but" type="button" name="ppdPending" id="ppdPending" value="Pending" style="width:140px" onclick="javascript:fn_SubmitNextLvl('ppd','pending')" >Pending</button>

</td></tr>
</c:if>
<c:if test="${viewType eq 'ptd'}">
<tr><td  colspan="2" align="center">
<button class="but" type="button" name="finalApproval" id="finalApproval" value="Final Approval" style="width:140px" onclick="javascript:fn_SubmitNextLvl('ptd','Approve')" >Final Approval</button>
<button class="but" type="button" name="finalRejection" id="finalRejection" value="Final Rejection" style="width:140px" onclick="javascript:fn_SubmitNextLvl('ptd','Reject')" >Final Rejection</button>
<button class="but" type="button" name="ptdPending" id="ptdPending" value="Pending" style="width:140px" onclick="javascript:fn_SubmitNextLvl('ptd','pending')" >Pending</button>

</td></tr>
</c:if>
<tr><td>&nbsp;</td></tr>


<tr align="center" ><td colspan="2">
<c:if test="${ viewType eq 'ramco'  }" >
<button class="but" type="button" name="submitPreauth" id="submitPreauth" value="Submit" style="width:140px" onclick="javascript:fn_submitPreauth()" >Submit</button>
</c:if>
<c:if test="${ viewType eq 'ramco' ||  viewType eq 'mithra'}" >
<button class="but" type="button" name="addAttachments" id="addAttachments" value="Add Attachments" style="width:140px" onclick="javascript:fn_addAttachments()" >Add Attachments</button>
</c:if>
<c:if test="${ viewType eq 'mithra'  }" >
<button class="but" type="button" name="sentPreauuth" id="sentPreauuth" value="Sent For Preauthorisation" style="width:160px" onclick="javascript:fn_sentPreauth()" >Sent For Preauthorisation</button>
</c:if>
</td></tr>
<c:if test="${ viewType eq 'pex'  }" >
<tr><td>&nbsp;</td></tr>
<tr><td colspan="2" align="center">
<a href="javascript:fn_pexQuestions()"; >Clich here to verify</a>
<button class="but" type="button" name="verifyButton" id="verifyButton" value="Verify" style="width:160px" onclick="javascript:fn_pexVerify()" >Verify</button>
</td></tr>
</c:if>

<tr><td>&nbsp;</td></tr>
</table>


<html:hidden property="finalDig" value="0"/>

<html:hidden  name="preauthDetailsForm" property="splInvest"   />
<html:hidden  name="preauthDetailsForm" property="caseId"   />
<html:hidden  name="preauthDetailsForm" property="casSugeryId"   />
<html:hidden name="preauthDetailsForm" property="quesFlag" />

</html:form>
</body>

</fmt:bundle>
</html>