<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>

<html>
<head>

 <link href="/Operations/css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen">

<style>
	

	

	#backgroundPopup{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
height:100%;
width:100%;
top:0;
left:0;
background:#000000;
border:1px solid #cecece;
z-index:1;
}
#popupEditEmpDtls{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
background:#FFFFFF;
border:2px solid #cecece;
z-index:2;
padding:2px;
}
#popupEditEmpDtls h1{
text-align:left;
color:#6FA5FD;
font-size:22px;
font-weight:700;
border-bottom:1px dotted #D3D3D3;
padding-bottom:2px;
margin-bottom:20px;
}
#popupEditEmpDtlsClose{
font-size:18px;
right:1px;
top:1px;
position:absolute;
color:#fff;
font-weight:700;
display:block;
}
	
	
	</style>
	
<script>
//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;
//loading popup with jQuery magic!
var rcntId="";
function loadPopup(id){
	rcntId=id;
	//loads popup only if it is disabled
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
		});
		$("#backgroundPopup").fadeIn("slow");
		$(id).fadeIn("slow");
		popupStatus = 1;
	}
}
	

	//disabling popup with jQuery magic!
	function disablePopup(id){
		//disables popup only if it is enabled
		if(popupStatus==1){
			$("#backgroundPopup").fadeOut("slow");
			$(id).fadeOut("slow");
			popupStatus = 0;
		}
	}

	//centering to align popup
	function centerPopup(id){
		//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $(id).height();
		var popupWidth = $(id).width();
		//centering
		$(id).css({
			"position": "absolute",
			"top": windowHeight/2-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});
		//only need force for IE6
		
		$("#backgroundPopup").css({
			"height": windowHeight
		});
		
	}
	//centering popup
	function centerTopAlignPopup(id){
		//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;

		var popupWidth = $(id).width();
		//centering
		$(id).css({
			"position": "absolute",
			"top": 0,
			"left": windowWidth/2-popupWidth/2
		});
		//only need force for IE6
		
		$("#backgroundPopup").css({
			"height": windowHeight
		});	
	}

	$(document).ready(function(){
		//CLOSING POPUP
		//Click the x event!
		$("#popupEditEmpDtlsClose").click(function(){
			disablePopup('#popupEditEmpDtls');
		});
		//Click out event!
		$("#backgroundPopup").click(function(){
			disablePopup(rcntId);
		});
		//Press Escape event!
		$(document).keypress(function(e){
			if(e.keyCode==27 && popupStatus==1){
				disablePopup(rcntId);
			}
		});
	});
	

function test(){
	
	parent.fn_loadImage();
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
	 
	  
	    url ="/Operations/loginAction.do?actionFlag=getcontactDetails";
		
		
	   xmlhttp.onreadystatechange=function()
	   {
	       if(xmlhttp.readyState==4)
	       {	
	           var resultArray=xmlhttp.responseText;
	           if(trim(resultArray)=="SessionExpired*")
	     		{
	     			alert("Session has been expired");
	     			parent.sessionExpireyClose();
	     		}
	     		else
	     		{
	           if(resultArray!=null)
	           {	
	               resultArray = resultArray.replace("[","");
	               resultArray = resultArray.replace("]","");    
	               resultArray = resultArray.replace("*","");            
	               var payList = resultArray.split(",");

                   arr=payList[0].split("~");
                
                   if(arr[1]!=null && arr[0]!=null)
                   {
                       var s;
                       parent.fn_removeLoadingImage();
                       val1 = arr[1].replace(/^\s+|\s+$/g,"");
                       
                       val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       s='<p style="text-align:right"><a href="javascript:edit()"><img src="/Operations/images/edit.png" title="Edit" style="no-repeat #fff; height:22px; width:22px; vertical-align:middle; border:0;"></a></p>';
                       s = s+' <table align="center">';
                       s=s+'<tr><td  class="labelheading1"><b>Phone No:</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td></td><td width="180px">'+val2+'</td></tr>';
                       s=s+'<tr><td class="labelheading1"><b>Email ID:</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td></td><td width=150px >'+val1+'</td></tr></table>';
                       var ajaxDisplay = document.getElementById('ajaxDiv');
           				ajaxDisplay.innerHTML =s ;
                                  
                     
                   }
					
	               
	       }
	       			
	   }
	       }
	   }
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	
		
}
function getEnrolledRep(){
	parent.fn_loadImage();
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
	 
	  
	    url ="/Operations/loginAction.do?actionFlag=getEnrolledDetails";
		
		
	   xmlhttp.onreadystatechange=function()
	   {
	       if(xmlhttp.readyState==4)
	       {	
	           var resultArray=xmlhttp.responseText;
	           if(trim(resultArray)=="SessionExpired*")
	     		{
	     			alert("Session has been expired");
	     			parent.sessionExpireyClose();
	     		}
	     		else
	     		{
	           if(resultArray!=null)
	           {	
	        	   resultArray = resultArray.replace("[","");
	               resultArray = resultArray.replace("]","");    
	               resultArray = resultArray.replace("*","");            
	               var payList = resultArray.split(",");

                   arr=payList[0].split("~");
                
                   if(arr[1]!=null && arr[0]!=null)
                   {
                       var s;
                       parent.fn_removeLoadingImage();
                       val1 = arr[0].replace(/^\s+|\s+$/g,"");
                       
                       val2 = arr[1].replace(/^\s+|\s+$/g,"");
                       val3 = arr[2].replace(/^\s+|\s+$/g,"");
                     
                       s =' <table align="center" >';
                       s=s+'<tr><td  class="labelheading1" align="center"><b>Waiting Count:</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td >'+val1+'</td></tr>';
                       s=s+'<tr><td class="labelheading1" align="center"><b>Approved Count:</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td  >'+val2+'</td></tr>';
                       s=s+'<tr><td class="labelheading1" align="center"><b>Rejected Count:</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td  >'+val3+'</td></tr></table>';
                       var ajaxDisplay = document.getElementById('ajaxRep');
           				ajaxDisplay.innerHTML =s ;
                                  
                     
                   }
	           }
	       			
	   }
	       }
	   }
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);

	
}
function trim (str) { 
	
	  return str.replace (/^\s+|\s+$/g, '');
	  }
function edit(){
	
	centerPopup("#popupEditEmpDtls");
	loadPopup("#popupEditEmpDtls");
	document.getElementById("updatePage").src='/Operations/editEmpDtlsAction.do?actionFlag=onLoad';

		
	}
	  
</script>

</head>

<body >

<html:form  method="post"  action="/loginAction.do" > 

<!-- START FOR KNOW USER-ID POPUP -->
<div id="backgroundPopup"   >
</div>
<div id="popupEditEmpDtls"   style="width: 450px; height:430px;"  >  
    <a id="popupEditEmpDtlsClose" ><img  src="/Operations/images/fileclose.png"/></a>
    <br>
    <iframe style="width: 450px;height: 390px" frameborder="0" id="updatePage"  >
        </iframe>
	</div>

</html:form>


</body>

</html>