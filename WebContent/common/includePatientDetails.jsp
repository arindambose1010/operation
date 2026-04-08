<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Include Patient Details</title>
<style type="text/css">
#imageID {
    position: fixed;
    top: 35px;
    right: 6px;
    z-index: 99999;
    cursor: pointer;
    padding: 5px;
    background: #fff;
    text-align: center;
    border: 2px solid #E2E2E2;
}
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
}
</script>
</head>
<body>
</body>
</html>