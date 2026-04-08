<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Feedback Form EHS</title>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
   <link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
   <link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />  
   <link href="js/star-rating/jquery.rating.css" rel="stylesheet" type="text/css"/>
   <link href="css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/star-rating/jquery.js"></script>
   <script src="js/star-rating/jquery.rating.js"></script>
   <script src="js/jquery.msgBox.js" type="text/javascript"></script>
 <script type="text/javascript"  src="js/jquery.mCustomScrollbar.concat.min.js" ></script>
<style type="text/css">
td
{
text-align:left;
height:50px;
}
</style>
<style>

 html, body{
 overflow:hidden;
 height:100%;
} 

</style>
<script>
	(function($){

		$(window).load(function(){
			$("body").mCustomScrollbar({
				theme:"dark"
				});
			
			$("body").hover(function(){
				$(document).data({"keyboard-input":"enabled"});
				$(this).addClass("keyboard-input");
			},function(){
				$(document).data({"keyboard-input":"disabled"});
				$(this).removeClass("keyboard-input");
			});
			$(document).keydown(function(e){
				if($(this).data("keyboard-input")==="enabled"){
					var activeElem=$(".keyboard-input"),
						activeElemPos=Math.abs($(".keyboard-input .mCSB_container").position().top),
						pixelsToScroll=60;
					if(e.which===38){ //scroll up
						e.preventDefault();
						if(pixelsToScroll>activeElemPos){
							activeElem.mCustomScrollbar("scrollTo","top");
						}else{
							activeElem.mCustomScrollbar("scrollTo",(activeElemPos-pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
						}
					}else if(e.which===40){ //scroll down
						e.preventDefault();
						activeElem.mCustomScrollbar("scrollTo",(activeElemPos+pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
					}
				}
			});
		});
	})(jQuery);
	
		
</script>
<script type="text/javascript">
var flag=0;
var count=1;
var length=0;
var radioFlag=0;
var validateNewRow;
var _validFileExtensions = [".jpg", ".jpeg", ".pdf", ".png"];
var fileNamesArray=new Array();

function fn_fadeStars()
{
	$('.ratings').find('input[type=radio]').attr("checked", false);
	$('.star-rating-control').find('.star-rating-live').removeClass('star-rating-on');
	if(flag==1)
    {
	  var tab=document.getElementById('feedbackTable');
	  tab.deleteRow(4);
	  flag=0;
	  validateNewRow=false;
    }
	}
function fn_submitFeedback()
{
	document.forms[0].action="feedback.do?count="+length;
	document.forms[0].submit();
}

function uploadFile() 
   {  
	   if(count<5)
	   {
	var div = document.getElementById('fileuploads[1]');  
	var iDiv= document.createElement("div"); 
	 div.appendChild(iDiv); 
	 iDiv.id="fileuploads["+(count+1)+"]";
	 iDiv.innerHTML='<input type="file" name="uploads['+(count+1)+']" id="uploads['+(count+1)+']"/>';   	 
	 iDiv.appendChild(document.createElement("br"));   
	 count=count+1;
	   } 
   else
	   alert("Atmost 5 bills can be attached");
   }
   
function uploadFileSub()
{
	var div = document.getElementById('fileuploads[1]');  
	if(count!=1)
		{
	var iDiv= document.getElementById("fileuploads["+(count)+"]");
	 div.removeChild(iDiv);  
	 count=count-1;
		}
	else
		div.innerHTML='<input type="file" name="uploads[1]" id="uploads[1]"/>'; 
		
}
function validateFileSize(file, maxSize) {  
	 if (navigator.appName=="Microsoft Internet Explorer") {  
		 var sizeVerify="verifySize";
		 return sizeVerify;
	 }
	    else {  
	        if (file.files[0]!=undefined) {  
            var size = file.files[0].size;
	        }  
   }  

    if (size!=undefined && size > maxSize)  
       return false;  
     return true;  
  } 
  
function fn_checkOption(arg,arg1){
	
	var newTR=document.getElementById('optionsTD');
	var tab=document.getElementById('feedbackTable');
	
	if(arg=="Q4" && arg1=="Yes" )
		{
		if(flag==0)
	     {
	     	var row=tab.insertRow(4);
		    var cell1=row.insertCell(0);
		    cell1.colSpan=2;
		    cell1.style.textAlign='center';
	        cell1.innerHTML=newTR.innerHTML;
	        cell1.className='tbcellBorder';
	        var filetype=document.getElementById("fileuploads[1]");
	        filetype.innerHTML='<input type="file" name="uploads[1]" id="uploads[1]"/>'; 
	        flag=1;
	        validateNewRow=true;
		   }
		}
	else if(arg=="Q4" && arg1=="No")
        {
		  if(flag==1)
	      {
		  tab.deleteRow(4);
		  flag=0;
		  validateNewRow=false;
	      }
		}	
	return;
}

function fn_validate()
{
	
	var ansFlag=0;	
	
	for(i=0;i<length-2;i++)
		{
		var ans=document.getElementById("answersList["+i+"]");
		  var fType=document.getElementById("TYPE["+i+"]").value;
		  var ques=document.getElementById("TDQues["+i+"]").innerHTML;
	   
	     if(fType=="textarea" && (ans.value=="" || ans.value==null))
	     { alert("Please answer: "+ques);  
	       ansFlag=1;
	       break;
	     }
	     else
	    	 {
	    	 if(fType=="radio"||fType=="rating")
	    	 {
	    		 var radioButtons = document.getElementsByName("answersList["+i+"]");
		 	     for (var x = 0; x < radioButtons.length; x ++) {
		 	         if (radioButtons[x].checked) {
		 	        	radioFlag=1;
		 	         }  
		 	     }    
	    	 if(radioFlag==0)
	    		 {
	    		alert("please answer:"+ques);
	    		ansFlag=1;
	    		break;
	    		 }
	    	 else 
	    		 {
	    		 radioFlag=0; 
	    		 }
	    	 }
		 }		
		}

	if(validateNewRow==true && ansFlag==0)
		{
		  var ddl=document.getElementById("selectCategory");
		 if(ddl.value=="")
			 {
			 ansFlag=1;
			 alert("Please select one or more payment categories");
			 }
		  else
			  {
			  for(i=1;i<=count;i++)
				  {
		     file=document.getElementById("uploads["+i+"]");
		     if(file.type=="file")
		     {
		        fileName=file.value;
		       
		        if(fileName.length > 0)
		    	 {			    	
		    	 for (var j = 0; j < _validFileExtensions.length; j++)
		    	 {
	                    var curExtension = _validFileExtensions[j];
	                    if (fileName.substr(fileName.length - curExtension.length, curExtension.length).toLowerCase() == curExtension.toLowerCase()) 
	                    {
	                    	 fileFlag=1;
	                        break;
	                    }
	                    else
	                    	fileFlag=0;
		    	 }
	                    if(fileFlag==0)
	                    	{
	                    	alert("Sorry, " + fileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
	                    	ansFlag=1;
	                    	fileFlag=0;
	                    	}
	                    else
		                     {
	                    	 var fileFlag= validateFileSize(file,262144);
	                    	 if(fileFlag==false)
		                    	 {
	                    		 alert("Sorry, " + fileName + " greater than 256KB");
	 	                    	 ansFlag=1; 
	 	                    	 break;      
		                    	 }
	                    	 else
		                    	 {
		                    	 if(fileFlag==true)
			                    	 {
		                    	 ansFlag=0;
			                    	 }
		                    	 else
			                    	 {
			                    	
			                    	 ansFlag=ValidateFileNames();
			                 		if(ansFlag==0)
				                 		{
		                    		 document.forms[0].action="feedback.do?flg=verifyFiles&count="+length;
		                    		 document.forms[0].submit();
				                 		}
			                 		
			                    	 }
			                    	 
			                    	 
		                    	 }
	                         }
		          
		     }	 
		    	 
		   }
	      }
		}
		}	       
	
	if(ansFlag==0)
		{
		if(validateNewRow==true)
		ansFlag=ValidateFileNames();
		if(ansFlag==0)
		fn_submitFeedback();
		}
		}
		

function ValidateFileNames()
{
	var fileNamesArray=new Array();
	var fileNameFlag=0;
	for(i=1;i<=count;i++)
	  {
  file=document.getElementById("uploads["+i+"]");
  fName=file.value;

           for(j=0;j<fileNamesArray.length;j++)
               {
                 if(fName==fileNamesArray[j])
                     {
                     fileNameFlag=1;
                     break; 
                     }
               }
           if(fileNameFlag==1)
               {
               alert(fName+" is attached twice");
               break;
               }
           else
               {
        	   fileNameFlag=0;
               fileNamesArray[fileNamesArray.length]=fName;
               }

	  }
	 return fileNameFlag;

	}
	

function verifyVisibility()
{
	 <%
     String res1 =(String)request.getAttribute("visibilityFlag");
     %>
    var Value1 = "<%=res1%>";
    
	if(Value1=="true")
	fn_checkOption("Q4","Yes");
}

function goToHomePage()
{
  window.close();	
}

window.onunload = refreshParent;
function refreshParent() {
   window.opener.location.reload();
}

</script>
</head>

<body onload="verifyVisibility()">
<div style="margin:0 auto;width:100%;">
<%@ include file="/common/Printheader.html" %>

 <logic:present  name="feedbackForm"  property="feedBackQuestions">
    <div style="width:90%;margin:0 auto;">
    <html:form action="feedback.do"  method="post" enctype="multipart/form-data" >
        <p class="tbheader"><strong>Employees Health Scheme Feedback Form</strong></p>
      <br/>
         <p>Dear <b><%= session.getAttribute("patName") %></b>,</p><br/>
         <p  style="text-align:justify;"> &nbsp;&nbsp;&nbsp;      It is to inform that patient <b><%= session.getAttribute("patName") %> </b>
           has undergone treatment at <b>${hospDtls.hospitalName},${district}</b> under Employees Health Scheme with a CaseId <b>${case_id}</b> for Amount <b>Rs.${amt}</b>  whose details are as follows:<br/>
           
         <br/> </p>
          <p ><b> Name:</b>  <%= session.getAttribute("patName") %></p>
          <p><b> Health Card No:</b>   ${PatientOpList.rationCard}</p>
          <p> <b> Contact No:</b>  ${PatientOpList.contactNo}   </p><br/>
         <c:if test="${not empty procList}">  
           <table class="contentTable" style="width: 100%">
           <tr>
           <th class="labelheading1 tbcellCss" style="width:15%">Procedure Code</th>
           <th class="labelheading1 tbcellCss" style="width:65%">Procedure Name</th>
           <th class="labelheading1 tbcellCss" style="width:20%">Category Name</th>
           </tr>
           
           <c:forEach var="code" items="${procList}">  
           
               <tr> <c:forEach var="proc" items="${code}"><td class="tbcellBorder" style="text-align:center"><c:out value="${proc}"> 
                </c:out>  
               </td> </c:forEach>  
                </tr>
           </c:forEach>
          
           </table>
           </c:if>
          <p  style="text-align:justify;"> Therefore, it is requested to give your feedback on the following.</p>
          <c:set var="ddlFlag" value="1" scope="page" />
          <table class="contentTable" cellpadding="5" id="feedbackTable" style="width:100%">
          
           <%
           String propName = "";
           String propName1="";
           String propName2="";
           int flag=1;
           String idType="";
           String idTdQues="";
           String idTdAns="";
           %> 
           
         <logic:iterate name="feedbackForm"  property="feedBackQuestions" id="feedback" indexId="index">
         <script> length=length+1;</script>
       
           <%
            propName = "answersList["+index+"]"; 
            propName1="questionsList["+index+"]";
            propName2="questionType["+index+"]";
            idType="TYPE["+index+"]";
            idTdQues="TDQues["+index+"]";
            idTdAns="TDAns["+index+"]";
            
           %>
          
          <html:hidden name="feedbackForm" property="<%=propName1%>" value='${feedback.ques_id}'></html:hidden>
          <html:hidden name="feedbackForm" property="<%=propName2%>" value='${feedback.feedback_type}' styleId="<%=idType%>"></html:hidden>
           
           
            <tr>
             <logic:notEqual value="Q7" property="ques_id" name="feedback">
             <logic:notEqual value="Q8" property="ques_id" name="feedback">
            <td class="labelheading1 tbcellCss" id="<%=idTdQues%>">
           
            <bean:write name="feedback" property="ques_name"/>

           </td>
            
          <td class="tbcellBorder" id="<%=idTdAns%>">
          
          <logic:equal value="textarea" property="feedback_type" name="feedback">
           <html:textarea name="feedbackForm" property="<%=propName%>" style="width: 300px; height=200px;" styleId="<%=propName%>" cols="5" /></logic:equal>

          <logic:equal value="radio" property="feedback_type" name="feedback">
            <logic:notEqual value="" property="option1" name="feedback">
                 <html:radio property="<%=propName%>" value='${feedback.option1}' styleId="<%=propName%>" onclick="fn_checkOption('${feedback.ques_id}','${feedback.option1}')"></html:radio><bean:write name="feedback" property="option1"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>
            <logic:notEqual value="" property="option2" name="feedback">
                <html:radio property="<%=propName%>" value='${feedback.option2}' styleId="<%=propName%>" onclick="fn_checkOption('${feedback.ques_id}','${feedback.option2}')"></html:radio><bean:write name="feedback" property="option2"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>         
            <logic:notEqual value="" property="option3" name="feedback">
                <html:radio property="<%=propName%>" value='${feedback.option3}' styleId="<%=propName%>"></html:radio><bean:write name="feedback" property="option3"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>
            <logic:notEqual value="" property="option4" name="feedback">
                <html:radio property="<%=propName%>" value='${feedback.option4}' styleId="<%=propName%>"></html:radio><bean:write name="feedback" property="option3"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>             
         </logic:equal>
         
         
            
         <logic:equal value="rating" property="feedback_type" name="feedback">
             <html:radio property="<%=propName%>" value="1"  styleClass="star" styleId="<%=propName%>"></html:radio>
             <html:radio property="<%=propName%>" value="2"  styleClass="star" styleId="<%=propName%>"></html:radio>
             <html:radio property="<%=propName%>" value="3"  styleClass="star" styleId="<%=propName%>"></html:radio>
             <html:radio property="<%=propName%>" value="4"  styleClass="star" styleId="<%=propName%>"></html:radio>
             <html:radio property="<%=propName%>" value="5"  styleClass="star" styleId="<%=propName%>"></html:radio>
           </logic:equal>
           
           
          <logic:equal value="checkbox" property="feedback_type" name="feedback">
            <logic:notEqual value="" property="option1" name="feedback">
                 <html:checkbox property="<%=propName%>" value='${feedback.option1}' styleId="<%=propName%>"></html:checkbox><bean:write name="feedback" property="option1"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>
            <logic:notEqual value="" property="option2" name="feedback">
                <html:checkbox property="<%=propName%>" value='${feedback.option2}' styleId="<%=propName%>"></html:checkbox><bean:write name="feedback" property="option2"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>         
            <logic:notEqual value="" property="option3" name="feedback">
                <html:checkbox property="<%=propName%>" value='${feedback.option3}' styleId="<%=propName%>"></html:checkbox><bean:write name="feedback" property="option3"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>
            <logic:notEqual value="" property="option4" name="feedback">
                <html:checkbox property="<%=propName%>" value='${feedback.option4}' styleId="<%=propName%>"></html:checkbox><bean:write name="feedback" property="option3"/> &nbsp;&nbsp;&nbsp;
            </logic:notEqual>
          </logic:equal>
           <logic:notEqual value="Q7" property="ques_id" name="feedback">
           <logic:equal value="multiselect" property="feedback_type" name="feedback">
      
            <html:select property="ctd" multiple="true" size="5" styleId="selectCategory">
             <html:option value="00" disabled="true">--Select--</html:option> 
              <c:set var="ddlFlag" value="${ddlFlag + 1}" scope="page"/>   					
               <c:forEach var="c" items="${optionsCollections}" varStatus="i">
                 <c:if test="${ddlFlag==i.count }">			
	              <c:forEach var="cc" items="${c}">
		            <option value="${cc}" title="${cc}">${cc}</option>
		         </c:forEach>                
		         </c:if>
                </c:forEach>
            </html:select>  
         </logic:equal>
         </logic:notEqual>
          
           </td>
       </logic:notEqual>
    </logic:notEqual>
   </tr>    
 </logic:iterate>
                    
  <tr id="category" style="display: none">
     <td id="optionsTD" class="tbcellBorder" style="text-align:center" colspan="2">
         <logic:equal value="multiselect" property="feedback_type" name="feedback">
         
            <html:select property="ctd" multiple="true" size="5" styleId="selectCategory" style="width:350px;">
             <html:option value="00" disabled="true">--Select Payment Category--</html:option>  
               <c:forEach var="cc" items="${optionsCollections[0]}">			
		            <option value="${cc}" title="${cc}">${cc}</option>		 
               </c:forEach>
                          </html:select>  
         </logic:equal>
       <br/>
        Please upload the bill(if any)  
               <div  id="fileuploads[1]"> </div>
         <input type="button" name="addmore" id="addmore" value="+" class="but" onClick="uploadFile();" />
         <input type="button" name="submore" id="submore" value="-" class="but" onClick="uploadFileSub();" />
        </td>
   </tr> 
</table> 
  <p style="text-align:center">      <input type="button"  value="Submit" class="but" onclick="fn_validate()"/>&nbsp;
           <input type="reset" value="Reset" class="but" onclick="fn_fadeStars()"/></p>
           <br/>
          
         <html:hidden property="case_id" name="feedbackForm" value='${caseId}'></html:hidden>
       </html:form>
       </div>
       </logic:present>
  </div>
       
     
       <logic:equal property="flag" value="1" name="feedbackForm">
       <script type="text/javascript">
       jqueryInfoMsg('Success','Thankyou For your Feedback',goToHomePage);
       
       </script>
       </logic:equal>
       <logic:equal property="flag" value="-1" name="feedbackForm">
       <script type="text/javascript">
       jqueryInfoMsg('Failure','Error while processing your feedback',goToHomePage);
       </script>
       </logic:equal>
       
        <logic:equal value="4" name="feedbackForm" property="flag">
       <script type="text/javascript">
       <%
       String res =(String)request.getAttribute("fileName");
     %>
     var Value = "<%=res%>";
            
      alert("File " +Value+ " size Exceeded 256 KB");
       </script>
       </logic:equal>
</body>
</html>