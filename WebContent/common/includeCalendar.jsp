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
	<script src="js/jquery.ui.datepicker.js"></script>
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
			dateFormat: "dd-mm-yy",
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
            dateFormat: "dd-mm-yy",
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#fromDate" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
		
		//For only month and year selection
	 $("#monthPicker").datepicker({
	    	dateFormat: 'mm-yy',
	    	changeMonth: true,
	    	changeYear: true,
	    	 maxDate:new Date(),
	    	 showOn: "both", 
	    	 showButtonPanel: true,
	    	 buttonImage: "images/calend.gif", 
	    	 buttonImageOnly: true,
	    	onClose: function(dateText, inst) {
	    	$(this).val($.datepicker.formatDate('mm-yy', new Date(inst.selectedYear, inst.selectedMonth, 1)));
	    	}
	    	});
	    	 
	    	$("#monthPicker").focus(function () {
	    	$('<style type="text/css"> .ui-datepicker-current { display: none; } </style>').appendTo("head");
	    	$(".ui-datepicker-calendar").hide();
	    	$("#ui-datepicker-div").position({
	    	 
	    	of: $(this)
	    	});
	    	
	    });
		
	});
	</script>
</head>
<body>

</body>
</html>