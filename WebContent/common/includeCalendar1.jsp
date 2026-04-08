<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<title>Insert title here</title>
<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker1.js"></script>
	<script src="js/DateTimePicker.js"></script>
	
<script>

var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

    
	$(function() {
		$( "#fromDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#toDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#fromDate" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
		$( "#sfpFromDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#sfpToDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#sfpToDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#sfpFromDate" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
		$( "#tpFromDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#tpToDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#tpToDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#tpFromDate" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
		
	});
	</script>
</head>
<body>

</body>
</html>