 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/include.jsp"%>
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<meta name="GENERATOR" content="Microsoft Visual Studio .NET 7.1"/>
<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5"></meta>
<script src="../js/jquery-1.9.1.min.js"></script>
<link href="/Operations/css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">

<style type="text/css">

/* side menu css */
#content{
width:85%;
height:440px;
border:0;
padding: 12px;
/*overflow:auto;*/
background-color: #fafafa;
border: 1px solid #ddd;
}
#bottomframe{
  min-height:370px;
}
.output{
    float:right;
}
.output a {
    background: none repeat scroll 0 0 #000000;
    border-radius: 15px 15px 15px 15px;
    display: inline-block;
    margin: 5px 20px 5px 0;
    padding: 3px 15px;
    text-decoration: none;
    color:white;
}
.output a:hover {
    text-decoration:underline;
    color:#ccc;
}

#vtab {
    margin: 2% auto;
}
#vtab  ul  li {
    width: 110px;
    /*background: -moz-linear-gradient(center top , #F5DEB3, #D2B48C);*/
    list-style-type: none;
    display: block;
    text-align: left;
    margin: auto;
    padding-bottom: 10px;
    border: 1px solid #fff;
    position: relative;
    border-right: none;
}
/*.ie.vtablist , .ie.selected , .ie#vtab ul li{
    margin: -3px 0px;
}*/
#vtab  ul  li a {
    background: none repeat scroll 0 0 transparent;
    /*color: black;*/
    /*font-size: small;*/
    font-weight: bold;
    padding: 8px 8px 8px 8px;
    text-decoration:none;
    display:block;
}

#vtab  ul  li.selected {
    border: 1px solid #ddd;
    border-right: none;
    z-index: 10;
    background: #fafafa !important;
    background: linear-gradient(top, #fafafa, #fafafa);
    filter:progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#fafafa, endColorstr=#fafafa);
    position: relative;
   
}
#vtab  ul {
    float: left;
    width: 110px;
    text-align: left;
    display: block;
    margin: auto 0;
    padding: 0;
    position: relative;
    top: 80px;
}
#vtab  #content {
    background-color: #fafafa;
    border: 1px solid #ddd;
    /*min-height: 440px;*/
    height:92%;
	margin-left:110px;
    padding: 12px;
    position: absolute;
    -moz-border-radius: 20px;
    -webkit-border-radius: 20px;
    border-radius: 20px;
    z-index: 9;
}
#vtab  #content {
    padding-top: 5px;
    margin-top: 0;
}

#vtab ul li.selected{
    border-right: 1px solid #fff !important;
}
#vtab ul li {
    border-right: 1px solid #ddd !important;
}
#vtab  #content {
    /*z-index: -1 !important;*/
    left:1px;
}
.selected{
background:none;
}
.loading{opacity:0.3;}
#countdown span {
    font-size: 14px;
    font-weight: bold;
    margin: 1px;
    padding: 5px;
    text-align: center;
}
#countdown {
    border: 1px solid #eee;
    color: black;
    left: 2px;
    padding: 0 0 10px 0;
    position: absolute;
    width: 105px;
    z-index: 15;
    text-align: center;
    font-weight: bold;
}
#countdown #minutesID, #countdown #secondsID {
    border: 2px solid #fafafa;
    background: #eee;
}
</style>

<script type="text/javascript">

var elemJqueryScrollTop=0;
var dentalFlg=parent.dentalFlg;
function sessionExpireyClose(){
	
	parent.sessionExpireyClose();
}
var rightMaxFlag=0;
var topMaxFlag=0;
function fn_maxmizeTop(){
	parent.fn_maxmizeTop();
	//if(topMaxFlag==0){
	//	document.getElementById("content").style.height='470px';
	//	topMaxFlag=1;
	//	}
	//	else{
	//		document.getElementById("content").style.height='470px';
	//		topMaxFlag=0;
	//	}
	//fn_resizePage1();
	/*var url='/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&disSearchType='+parent.disSearchType+'&module='+parent.module;
	 document.forms[0].action=url;
	 document.forms[0].target="_parent";
   document.forms[0].submit();*/
}
function fn_maxmizeRight()
{
	if(rightMaxFlag==0){
	document.getElementById("vtabul").style.display="none";
	document.getElementById("content").style.width='97%';
	document.getElementById("content").style.margin='0';
	rightMaxFlag=1;
	}
	else
		{
		document.getElementById("vtabul").style.display="block";
		document.getElementById("content").style.width='85%';
		document.getElementById("content").style.margin='0 0 0 110px';
		rightMaxFlag=0;
		}
	}
	
	/**
	Loading Image
	*/
	function fn_loadImage()
	{
		document.getElementById('processImagetable').style.display="";

	}
	function detectBrowser(){
		if ( navigator.appName == 'Opera' ) return 'Opera';
		 if ( navigator.appName == 'Microsoft Internet Explorer' )  return 'MSIE';
		 if ( navigator.userAgent.indexOf( 'Chrome' ) >= 0 ) return 'Chrome';
		 if ( navigator.userAgent.indexOf( 'SeaMonkey' ) >= 0 ) return 'SeaMonkey';  // must test before Firefox
		 if ( navigator.userAgent.indexOf( 'Safari' ) >= 0 ) return 'Safari';
		 if ( navigator.userAgent.indexOf( 'Firefox' ) >= 0 ) return 'Firefox';
		 return navigator.appName;
	}
	function fn_removeLoadingImage()
	{   
		document.getElementById('processImagetable').style.display="none";
		var browserName=detectBrowser();
		if(browserName == "Chrome"){
		      document.getElementById("bottomFrame").style.height="95%";
		 }
		 else{
		      document.getElementById("bottomFrame").style.height="100%";
		 }
	}
function fn_ipRefresh()
{
	parent.fn_ipRefresh();
}
function fn_getCaseForApproval(){
	parent.fn_getCaseForApproval();  
}
function fn_getCaseForDisscussion()
{
	parent.fn_getCaseForDisscussion();  
}
function fn_getFraudCr()
{
	parent.topFrame.fn_getFraudCr();
}
function fn_getIP()
{  
	fn_loadImage();
	closeAttachmentWin();
	parent.ipRefershFlag='N';
	parent.topFrame.fn_getIP(); 
}
function fn_flagging()
{
	fn_loadImage();
	closeAttachmentWin();
	parent.topFrame.fraudClicked ='Y';
	parent.topFrame.fn_flagging();
}

function fn_getClinicData(vType)
{  
	fn_loadImage();
	closeAttachmentWin();
	parent.topFrame.fn_getClinicData(vType,''); 
}

function fn_PastData()
{
	fn_loadImage();
	closeAttachmentWin();
	parent.topFrame.fn_PastData();
}
function fn_attachments()
{
	fn_loadImage();
	closeAttachmentWin();
	parent.topFrame.attachmentsClicked ='Y';
	parent.topFrame.fn_attachments();
}
function fn_OnlineCaseSheet()
{
	fn_loadImage();
	closeAttachmentWin();
	parent.topFrame.caseSheetClicked ='Y';
	parent.topFrame.fn_OnlineCaseSheet(parent.onlineCaseSheetFlag);
}
function fn_pastHistory()
{
	//alert("1");
	fn_loadImage();
	closeAttachmentWin();
	parent.topFrame.pastHistClicked ='Y';
	parent.topFrame.fn_PastData();
}
function fn_newUser()
{
	fn_loadImage();
	closeAttachmentWin();
 	parent.topFrame.fn_newUser();
}
	function fn_showQuestionnaire()
	{
		var caseId=parent.caseId;
		//alert(caseId);
		fn_loadImage();
		//closeAttachmentWin();
		parent.topFrame.fn_showQuestionnaire(caseId);
	}
	function fn_viewQuestionnaire()
	{
		var fromPage= parent.fromPage;
		
		var caseId=parent.caseId;
		//alert(caseId);
		fn_loadImage();
		//closeAttachmentWin();
		parent.topFrame.fn_viewQuestionnaire(caseId,fromPage);
	}
	var prevHighliteTab='';
	/* function fn_highlight(rowId){
	document.getElementById(rowId).className="sideMenuActive";
	if(prevHighliteTab!='')
	document.getElementById(prevHighliteTab).className='';
	prevHighliteTab=rowId;
	
	} */
	function fn_highlight(id)
	{
	
	   var arr=new Array("ipTab","attachments","clinicalNotes","preauthDetails","claims","ipTab1","attachments1","caseSheet","pastHistory","mawkdiv","madiv","flagging");
	   for(i=0;i<=arr.length-1;i++)
	   { 
	       var x=document.getElementById(id).id;
	         if(arr[i] == x)
	            {	     
	            document.getElementById(id).className ="selected";
	            }
	             else
	            {
	        	document.getElementById(arr[i]).className =" ";
	        	}
		}
	}

	function fn_claims()
	{
		if(parent.topFrame.ClaimPage!=null && parent.topFrame.ClaimPage!=''
			&& parent.topFrame.ClaimPage=='Y')
			{
					if(parent.topFrame.pastHistClicked!= 'Y'
						&& (parent.topFrame.caseSheetClicked==null || parent.topFrame.caseSheetClicked=='' || parent.topFrame.caseSheetClicked!= 'Y') 
						&& (parent.topFrame.attachmentsClicked==null || parent.topFrame.attachmentsClicked=='' || parent.topFrame.attachmentsClicked!= 'Y') 
						&& (parent.topFrame.casesheetAttachClick==null || parent.topFrame.casesheetAttachClick=='' || parent.topFrame.casesheetAttachClick!= 'Y')
						&& (parent.topFrame.preauthClicked==null || parent.topFrame.preauthClicked=='' || parent.topFrame.preauthClicked!= 'Y'))
					{
						alert('Please view Past History,Preauth,Case Sheet and Casesheet Attachments to proceed');
						return false;
					}
					
					if(parent.caseApprovalFlag=='Y' && parent.topFrame.pastHistClicked!= 'Y' )
					{
						alert('Please view Past History to proceed');
						document.getElementById('preauthDetails').className =" ";
						return false;
					}
					if(parent.caseApprovalFlag=='Y'&& (parent.topFrame.caseSheetClicked==null || parent.topFrame.caseSheetClicked=='' || parent.topFrame.caseSheetClicked!= 'Y'))
					{
						alert('Please view Case Sheet tab to proceed');
						//document.getElementById('claimDetails').className =" ";
						return false;
					} 
					if(parent.caseApprovalFlag=='Y' && (parent.topFrame.attachmentsClicked==null || parent.topFrame.attachmentsClicked=='' || parent.topFrame.attachmentsClicked!= 'Y'))
					{
						alert('Please view Attachments tab to proceed');
						return false;
					}
					if(parent.caseApprovalFlag=='Y' && (parent.topFrame.casesheetAttachClick==null || parent.topFrame.casesheetAttachClick=='' || parent.topFrame.casesheetAttachClick!= 'Y'))
					{
		
						alert('Please click on case sheet attachments to proceed');
						return false;
					}
					if(parent.caseApprovalFlag=='Y' && (parent.topFrame.preauthClicked==null || parent.topFrame.preauthClicked=='' || parent.topFrame.preauthClicked!= 'Y'))
					{
		
						alert('Please view Preauth tab to proceed');
						return false;
					}	
					else
						{
		
						parent.topFrame.fn_claim();
						}
			}
		else
		parent.topFrame.fn_claim();
	}
function fn_enableDiv()
{		
	if(parent.ipOpType != null && parent.ipOpType !='' && parent.ipOpType =='OP' )
		{
		if(document.getElementById('opDiv') !=null)
			{
			document.getElementById('opDiv').style.display="";
			}
		}
	else
		{
		if(document.getElementById('ipDiv') !=null)
		{
		document.getElementById('ipDiv').style.display="";
		}
		}	
}
function closeAttachmentWin()
{
	//parent.fn_CloseWins();
}

function fn_preauthDetails()
{
	parent.topFrame.preauthClicked ='Y';
	if( parent.caseApprovalFlag==null || parent.caseApprovalFlag=='' || parent.caseApprovalFlag!= 'Y' || parent.topFrame.loggedInRole =='medco' || (parent.caseApprovalFlag=='Y' && parent.topFrame.loggedInRole !='medco' && parent.topFrame.pastHistClicked!= null && parent.topFrame.pastHistClicked !='' && parent.topFrame.pastHistClicked == 'Y' ))
	{
		
		//alert("caseApprovalFlag :" + parent.caseApprovalFlag);
		if(parent.topFrame.loggedInRole=='ppd'||parent.topFrame.loggedInRole=='claimHead'||parent.topFrame.loggedInRole=='ctd'||parent.topFrame.loggedInRole=='pao'||
				                                     parent.topFrame.loggedInRole=='cpd'||parent.topFrame.loggedInRole=='eo'||parent.topFrame.loggedInRole=='ptd')
			{// alert("parent.topFrame.loggedInRole :"+parent.topFrame.loggedInRole);
				if(parent.flagged=='Y' || parent.flagged=='N')
				 {
					if(parent.caseApprovalFlag=='Y'&& (parent.topFrame.fraudClicked==null || parent.topFrame.fraudClicked=='' || parent.topFrame.fraudClicked!= 'Y'))
						{
							alert('Please see Fraud/CR Tab to proceed');
							document.getElementById('preauthDetails').className =" ";
							return false;
						}
				 }
			}
		fn_loadImage();
		//parent.topFrame.fn_preauthDetails();	
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
		 url = '/<%=context%>/preauthDetails.do?actionFlag=checkClinicalNotes&caseId='+parent.topFrame.caseId+'&callType=Ajax';
		 xmlhttp.onreadystatechange=function()
		{
			if(xmlhttp.readyState==4)
			{	
				 var resultArray=xmlhttp.responseText;
					var resultArray = resultArray.split("*");
				   
					if(resultArray[0]!=null)
					{
						 if(parent.topFrame.ViewType != null && parent.topFrame.ViewType =='medco' && parent.topFrame.caseFlag =='Y' && parent.topFrame.currStatus=='IP case Registered')
							 {
							 
							 if(resultArray[0] =='success')
							   {
								 parent.topFrame.fn_preauthDetails();	
							   }
							 else
								 {
								 parent.topFrame.fn_navigateClinical(resultArray[0]); 
								 }
							 }
						 else
							 {
							 parent.topFrame.fn_preauthDetails();	
							 }
					}
		  //  } end of if
			}}	
		 xmlhttp.open("Post",url,true);
		 xmlhttp.send(null);
	}
    else if(parent.topFrame.loggedInRole=='ppd'||parent.topFrame.loggedInRole=='claimHead'||parent.topFrame.loggedInRole=='ctd'||parent.topFrame.loggedInRole=='pao'
                                               ||parent.topFrame.loggedInRole=='cpd'||parent.topFrame.loggedInRole=='eo'||parent.topFrame.loggedInRole=='ptd')
		{
    		if(parent.flagged=='Y' || parent.flagged=='N')
				{
				//alert(parent.topFrame.pastHistClicked);
		    		if(parent.caseApprovalFlag=='Y' && parent.topFrame.fraudClicked!= null && parent.topFrame.fraudClicked !='' && parent.topFrame.fraudClicked == 'Y' && parent.topFrame.pastHistClicked!= 'Y' && parent.newUser!='Y')
							{
								alert('Please see Past History to proceed');
								document.getElementById('preauthDetails').className =" ";
								return false;
							}
						else if(parent.flagged!=null && parent.flagged!='' && parent.caseApprovalFlag=='Y' && parent.topFrame.pastHistClicked!= null && parent.topFrame.pastHistClicked !='' && parent.topFrame.pastHistClicked == 'Y' && parent.topFrame.fraudClicked!= 'Y' )
							{
								alert('Please see Fraud/CR Tab to proceed');
								document.getElementById('preauthDetails').className =" ";
								return false;
							}
						else if(parent.flagged!=null && parent.flagged!='' && parent.caseApprovalFlag=='Y' && parent.topFrame.pastHistClicked != 'Y' && parent.topFrame.fraudClicked != 'Y' && parent.newUser!='Y')
							{
								alert('Please see both Past History and Fraud/CR first to proceed');
								document.getElementById('preauthDetails').className =" ";
								return false;
							}	
				}
    		else if(parent.flagged==null || parent.flagged=='')
    			{
	    			if(parent.caseApprovalFlag=='Y' && parent.topFrame.pastHistClicked!= 'Y' && parent.newUser!='Y')
					{
						alert('Please see Past History to proceed');
						document.getElementById('preauthDetails').className =" ";
						return false;
					}
	    			else{
	    				
	    					parent.topFrame.fn_preauthDetails();
	    				}
    			}
    		
		}
  //For alert MULTIPLE PREAUTHS RAISED FOR SINGLE PATIENT
    if(parent.topFrame.loggedInRole=='ppd'|| parent.topFrame.loggedInRole=='ctd'||
            parent.topFrame.loggedInRole=='cpd'|| parent.topFrame.loggedInRole=='ptd')
    	{
    	
    
		//parent.topFrame.fn_preauthDetails();	
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
        	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    		alert("Your browser does not support XMLHTTP!");
        }
      
        xmlhttp.onreadystatechange=function()
        {
            if(xmlhttp.readyState==4)
            {
                var resultArray=xmlhttp.responseText;
             
                	if(resultArray!=null && resultArray!="")
                	{

                    	var resultArray1 = resultArray.split("*");
                    	if(resultArray1[0]!=null)
						{
                    		if(resultArray1[0] =='success')
							   {
								alert("Multiple Preauthorizations have been raised for this patient with in 7 days.");
								parent.topFrame.fn_preauthDetails();
							   }
							 else
								 {
								 parent.topFrame.fn_preauthDetails();
								 
								 }
						}
                    
                	}
            	
            }
        }
        	
    	url = '/<%=context%>/preauthDetails.do?actionFlag=checkPreauthDates&cardNo='+parent.topFrame.cardNo ;
    	xmlhttp.open("Post",url,true);
    	xmlhttp.send(null);
    	}
	else if(parent.topFrame.pastHistClicked != 'Y')
	{
	//alert(parent.topFrame.pastHistClicked+"3333333");
		alert('Please see Past History first');
		document.getElementById('preauthDetails').className =" ";
		return false;
	}
}

   var preventDefault = function(e) { 
	   e.preventDefault(); 
	   e.stopPropagation(); 
	   } ;
   var onDivMouseEnter = function() { 
	   $(window).bind('DOMMouseScroll.myNameSpace', preventDefault); 
	   $(document).bind('mousewheel.myNameSpace', preventDefault); 
	   } ;
   var onDivMouseLeave = function() { 
	   $(window).unbind('DOMMouseScroll.myNameSpace'); 
	   $(document).unbind('mousewheel.myNameSpace'); 
	   } ;
		(function($){
			var $items = $('.vtablist');
		       $items.click(function() {
		           $items.removeClass('selected');
		           $(this).addClass('selected');
		           var index = $items.index($(this));
		           $('#content').hide().eq(index).show();
		       }).eq(1).click();       	
		})(jQuery);
		function fn_resizePage1(){
			
			var browserName=parent.parent.detectBrowser();
		     if(browserName == "Chrome"){
		      var height=document.getElementById("bottomFrame").contentDocument.documentElement.scrollHeight;
		     }
		     else{
		      var height=document.getElementById("bottomFrame").contentWindow.document.body.scrollHeight;
		         }
		    //var height=document.getElementById("bottomFrame").contentWindow.document .body.scrollHeight;
		    height=height*1+parseInt(10*1);
			document.getElementById("bottomFrame").height=height;	
			//$("#content").mCustomScrollbar("update");
			//$("#content").mCustomScrollbar("scrollTo","top");			
		}		
</script>
</head>
<body onload="fn_enableDiv();">   
<div id="countdown">
<p class="tb">Time Remaining</p>
<div style="float:left;width:50%;">mins<br><br><span id="minutesID"></span></div>
<div style="float:left;width:50%;">sec<br><br><span id="secondsID"></span></div>
</div>
<div id="vtab" >
<div id="ipDiv" style="display:none">
<ul id="vtabul" >
<li id="ipTab" class="selected" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content" onclick="javascript:fn_getIP();">IP Registration Details</a>
</li>
<li  id="pastHistory" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1" onclick="javascript:fn_pastHistory();">Past History</a>
</li>
<li  id="clinicalNotes" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content2" onclick="javascript:fn_getClinicData('PRE');">Clinical Notes</a>
</li>
<li  id="preauthDetails" class="vtablist" onclick="javascript:fn_highlight(this.id);" >
<a href="#" rel="load-content3" onclick="javascript:fn_preauthDetails()">Preauth</a>
</li>
<li  id="claims" class="vtablist" onclick="javascript:fn_highlight(this.id);" >
<a href="#" rel="load-content4" onclick="javascript:fn_claims();">Claims</a>
</li>
<li  id="attachments" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1" onclick="javascript:fn_attachments();">Attachments</a>
</li>
<div id="onlineCaseSheet" >
<li  id="caseSheet" class="vtablist" onclick="javascript:fn_highlight(this.id);" >
<a href="#" rel="load-content1" onclick="javascript:fn_OnlineCaseSheet();">Case Sheet</a>
</li>
</div>
<li id="flagging" class="vtablist" style="display: inline-block;" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1"  onclick="javascript:fn_flagging();" style="width:70px;clear:none;float:left;">Fraud/CR</a>
<img id="redFlag" src="../images/RedFlag.png" style="cursor:pointer;display:none;float:left;margin-top:6px;" onclick="javascript:fn_flagging()" ></img>
<img id="greenFlag" src="../images/GreenFlag.png" style="cursor:pointer;display:none;float:left;margin-top:6px;" onclick="javascript:fn_flagging()" ></img>
</li>
<!-- <div id="flagStatusDiv" style="display:none;">
<li id="flagStatus" class="vtablist" style="display: inline-block;">
<a href="#" rel="load-content1" style="width:70px;clear:none;float:left;">Flag Status</a>
<img id="redFlag" src="../images/RedFlag.png" style="cursor:pointer;display:none;float:left;margin-top:6px;" onclick="javascript:fn_flagging()" ></img>
<img id="greenFlag" src="../images/GreenFlag.png" style="cursor:pointer;display:none;float:left;margin-top:6px;" onclick="javascript:fn_flagging()" ></img>
</li>
</div>  -->
<div id="medicalAuditDiv" style="display:none">
<li  id="madiv" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1" onclick="javascript:fn_showQuestionnaire();" >Initiate Medical Audit</a>
</li>
</div> 
<div id="medicalAuditWorklistDiv" style="display:none">
<li  id="mawkdiv" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1" onclick="javascript:fn_viewQuestionnaire();" >View Questionnaire</a>
</li>
</div> 
</ul>
</div>

<div id="opDiv" style="display:none">
<ul id="vtabul">
<li id="ipTab1" class="selected" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content" onclick="javascript:fn_getIP();">OP</a>
</li>
<li  id="pastHistory" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1" onclick="javascript:fn_pastHistory();">Past History</a>
</li>
<li  id="attachments1" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1" onclick="javascript:fn_attachments();">Attachments</a>
</li>
</ul>
</div>
 <div id="processImagetable" style="top:10%;width:80%;position:absolute;z-index:60;height:10%">
        <table border="0" align="center" width="100%" style="height:400" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src="../images/Progress.gif" width="100" height="100" border="0" tabindex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
        
<div id="content">
<iframe name="bottomFrame" scrolling="auto" id="bottomFrame" style="background:white;" height="100%" width="100%" frameBorder="0" onload="fn_removeLoadingImage();" ></iframe>
</div>
<div style="height:0px;width:0px;margin:0px;padding:0px;clear:both;">&nbsp;</div>
<!-- <p class="output"><a href="top" rel="_mCS_1_scrollTo">Scroll to top</a></p> -->
<!-- <p class="output"><span class="content-position"></span></p>-->
</div>
<script type="text/javascript">

if(parent.module == "medicalAudit"){
	
	document.getElementById('medicalAuditDiv').style.display='';
}
if(parent.module == "medicalAuditworklist"){
	
	document.getElementById('medicalAuditWorklistDiv').style.display='';
}
if(parent.flagColour=="R"||parent.flagColour=="G")
	{
		//document.getElementById('flagStatusDiv').style.display='';
		if(parent.flagColour=="R")
			document.getElementById('redFlag').style.display='';
		else
			document.getElementById('greenFlag').style.display='';
	}
if(parent.clinicalTab=="hide")
	{
		document.getElementById('clinicalNotes').style.display='none';
	}
if(parent.clinicalTab=="show")
{
	document.getElementById('clinicalNotes').style.display='';
}
//set the date we're counting down to
var target_date = new Date().getTime();
 
// variables for time units
var days, hours, minutes, seconds, ms_step=10;
 
// get tag element
var countdown = document.getElementById('countdown');
var autoAction = parent.autoActionFlag;
var timoutTimer = parent.timerTime;

setInterval(function () {
	if(autoAction!=null && autoAction=="OnloadCaseOpen"){
		if(window.bottomFrame.document.getElementById("topImage")!=null){
            window.bottomFrame.document.getElementById("topImage").style.display='none';
		}
		if(window.bottomFrame.document.getElementById("menuImage")!=null)
			window.bottomFrame.document.getElementById("menuImage").style.display='none';
    var current_date = new Date().getTime();
    var seconds_left = (target_date - current_date) / 1000;
    seconds_left=seconds_left+parseInt(timoutTimer);
    days = parseInt(seconds_left / 86400);
    seconds_left = seconds_left % 86400;
    hours = parseInt(seconds_left / 3600);
    seconds_left = seconds_left % 3600;
    min = parseInt(seconds_left / 60);
    sec = parseInt(seconds_left % 60);
    ms = parseInt(target_date-current_date);
    // format countdown string + set tag value
    if(min<=0 && sec<=0){
    	minutesID.innerHTML = '00';
		secondsID.innerHTML = '00';
        
   	    if(window.bottomFrame.document.getElementById('buttonVisibleId')!=null){
   		 window.bottomFrame.document.getElementById('buttonVisibleId').style.display='block';
   		window.bottomFrame.document.getElementById('buttonVisibleId').align='center';
        }
        if(window.bottomFrame.document.getElementById('buttonVisibleEnhId')!=null){
      		 window.bottomFrame.document.getElementById('buttonVisibleEnhId').style.display='block';
        		window.bottomFrame.document.getElementById('buttonVisibleEnhId').align='center';
             }
    }
    else {
   			minutesID.innerHTML = min;
   			secondsID.innerHTML = sec;
    }
	}
	else{
		document.getElementById('countdown').style.display='none';
		if(window.bottomFrame.document.getElementById('buttonVisibleId')!=null){
	   		 window.bottomFrame.document.getElementById('buttonVisibleId').style.display='block';
	   		window.bottomFrame.document.getElementById('buttonVisibleId').align='center';
	        }
	        if(window.bottomFrame.document.getElementById('buttonVisibleEnhId')!=null){
	      		 window.bottomFrame.document.getElementById('buttonVisibleEnhId').style.display='block';
	        		window.bottomFrame.document.getElementById('buttonVisibleEnhId').align='center';
	             }
		}
}, ms_step);
</script>
</body>
</html>     