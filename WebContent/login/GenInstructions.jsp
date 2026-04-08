<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS:General Instructions</title>
<LINK rel="stylesheet" href="../css/InstStyles.css" type="text/css" media="screen" />
<link href="/Operations/css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">	   
 <script src="js/jquery-1.9.1.js"></script>
<%@ include file="/common/includeScrollbar.jsp"%> 
  <script src="/Operations/js/popup.js"></script>

<script type="text/javascript">
	

function fn_emailSubscription(){		
	var url='/Operations/requestInfo.do?actionFlag=emailSubscription';				
	document.getElementById('EmailFrame').src=url;
	centerPopup("#popupRaiseEmailSubscription");
	loadPopup("#popupRaiseEmailSubscription");
}


function trim (str) { 
	
	  return str.replace (/^\s+|\s+$/g, '');
	  }

function test(){

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
	   // alert("Your Browser Does Not Support XMLHTTP!");
	   }
	 		

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
                    
                    
                     val1 = arr[1].replace(/^\s+|\s+$/g,"");
                     
                     val2 = arr[0].replace(/^\s+|\s+$/g,"");
                    
                     s =' <table style="width:80%;margin:0 auto;break-word;"  >';
                     s=s+'<tr><td><caption style="color:#005dbd;font-weight:bold;">PROFILE</caption></td></tr>';
                     s=s+'<tr><td class="labelheading1" ><b>Name:</b><td ><%=session.getAttribute("fullName")%></td></tr>';
                     s=s+'<tr><tr><tr><td    class="labelheading1" ><b>Phone:</b></td><td >'+val2+'</td><br></br></tr>';
                     s=s+'<tr><tr><tr><tr><td  class="labelheading1" ><b>Email:</b></td><td width="100px" style="word-break:break-all;" >'+val1+'</td></tr>';
                    // s=s+'<tr><tr><tr><td></td><td></td><td><a href="javascript:edit()"><img src="/Operations/images/editProfile.png" title="Edit" style="no-repeat #fff; height:25px; width:40px; vertical-align:middle; border:0;"></a></td></tr></table>';
                      s=s+'<tr><tr><tr><td></td><td></td><td></td></tr></table>';
                    var ajaxDisplay = document.getElementById('ajaxDiv');
         				ajaxDisplay.innerHTML =s ;
                               
                 }
				  
	       }
	       			
	   }
	       }
	   }
	    var url ="/Operations/loginAction.do?actionFlag=getcontactDetailsForEhfmUsers"; 
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}

</script>
</head>
<body >

<div id="PenInst">
<!--  Pensioner Instructions - Left side Instructions Images -->
<div id="PenInst_left">
   <div id="pen_profile">
   <div id="pen_profile_top" style="background:url('/Operations/images/profile_top.png');height:23px;width:211px;"></div>
   <div id="pen_profile_middle" style="background:url('/Operations/images/profile_middle.png');width:211px;">
   <div id="ajaxDiv">
      <table style="width:80%;margin:0 auto;" >
         <caption style="color:#005dbd;font-weight:bold;">PROFILE</caption>
              
      </table>
      </div>
   </div>
    <div id="pen_profile_bottom" style="background:url('/Operations/images/profile_bottom.png');height:22px;width:211px;"></div>
   </div>
   <ul id="PenInfo_links">
	<li><a href="#">
	<img title="IT Helpline"  src="/Operations/images/IThelpline.jpg"  alt="IT Helpline"></a></li>
    </ul>
   
  </div>

<!--  Pensioner Instructions - Middle portion to display the content accordingly left side chosen option  -->
<div id="PenInst_middle">

</div>
<div id="backgroundPopup"   >
</div>
<div id="popupRaiseEmailSubscription" style="position: relative; width: 520px; height:320px; overflow:hidden;display:none" >  
 <a id="popupRaiseCloseEmailSubscription" >X</a>
<iframe  id="EmailFrame" style="width: 520px;height: 320px" frameborder="0"> </iframe>
</div>
<!--  Pensioner Instructions - right side Options -->
<div id="PenInst_right">
  <div id="PenInst_CMmsg">
   <!--<p id="PenInst_cmphoto"><img src="/Operations/images/CM2.png" alt="Chief Minister" /></p>-->
   <p id="PenInst_cmmsg_title">Message</p>
   <p id="PenInst_cmtext">
     "Employees Health Scheme is intended to provide cashless treatment to all the State Government employees including the State Government pensioners, 
     along with their dependent family members through a network of empanelled hospitals of Aarogyasri Health Care Trust."
   </p>
   <p id="PenInst_cmname">Hon'ble Chief Minister <br /> <!--N.Kiran Kumar Reddy-->
   </p>
  </div>
</div>
<div class="PenClear">&nbsp;</div>
</div>
<script src="/Operations/js/genafterlogin.js" type="text/javascript"></script>  
</body>
</html>