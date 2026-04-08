<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Include Patient Details</title>
<script type="text/javascript" src="js/popup.js"></script>
<style type="text/css">
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
#popupRaiseComplaint{
display:none;
position:relative;
_position:relative; /* hack for internet explorer 6*/
background:#FFFFFF;
border-color:blue;
border:2px solid #cecece;
z-index:2;
padding:3px;
font-size:13px;
}
#popupRaiseComplaint h1{
text-align:left;
color:#6FA5FD;
font-size:22px;
font-weight:700;
border-bottom:1px dotted #D3D3D3;
padding-bottom:2px;
margin-bottom:10px;
}
#popupRaiseCloseComplaint{
font-size:14px;
line-height:14px;
float:right;
color:#000;
font-weight:700;
display:block;
cursor: pointer;
position: relative;
}
#imageID {
    position: fixed;
    top: 35px;
    right: 6px;
    z-index: 99999;
    cursor: pointer;
}
#viewDtlsID {
    z-index: 99999;
    cursor: pointer;
}
/* #imageID {
    position: fixed;
    top: 35px;
    right: 6px;
    z-index: 99999;
    cursor: pointer;
    padding: 5px;
    background: #fff;
    text-align: center;
    border: 2px solid #E2E2E2;
} */
#modal-lgx{width:815px;}
</style>
<script type="text/javascript">
function fn_getPatDetails(){
	var caseIdd;
	if('${winOpen}'!=null && '${winOpen}'!='' && '${winOpen}'=='Y')
		caseIdd =document.forms[0].caseId.value;
	else
		caseIdd =parent.parent.caseId;
	var url='/Operations/patCommonDtls.htm?actionFlag=patCommonDtls&CaseId='+caseIdd;
    document.getElementById('complaintFrame').src=url;
    centerPopup("#popupRaiseComplaint");
	loadPopup("#popupRaiseComplaint");
	document.getElementById('popupRaiseComplaint').style.top="0px";
	document.getElementById('popupRaiseComplaint').style.left="-743px";
	document.getElementById('popupRaiseComplaint').style.right="10px";
}
</script>
</head>
<body>
</body>
</html>