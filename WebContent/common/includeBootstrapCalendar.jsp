<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<title>Insert title here</title>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/datepicker3.css">
<script>
var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

    
$(function() {
	$( "#fromDate" ).datepicker({
		defaultDate: "+1w",
		endDate: new Date(y, m, d),
		todayHighlight: true,
		autoclose: true,
		format:'dd-mm-yyyy'
		
	}).on('changeDate', function(selected){
		$( "#toDate" ).datepicker( "setStartDate", new Date(selected.date.valueOf()) );
		});
		
	$( "#toDate" ).datepicker({
		defaultDate: "+1w",
		todayHighlight: true,
		autoclose: true,
		format : 'dd-mm-yyyy',
		endDate: new Date(y, m, d)
	}).on('changeDate', function(selected){
		$( "#fromDate" ).datepicker( "setEndDate", new Date(selected.date.valueOf()) );
		});
});

	</script>
</head>
<body>

</body>
</html>