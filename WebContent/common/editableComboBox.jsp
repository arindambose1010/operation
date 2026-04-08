<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editable ComboBox</title>
 <script type="text/javascript">
              (window.jQuery)||document.write('<script type="text/javascript" src="js/jquery-1.9.1.js"><\/script>');
   </script>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">  
 <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> 
  <script type="text/javascript">
(window.jQuery && window.jQuery.ui && window.jQuery.ui.version === '1.10.4')|| 
document.write('<link rel="stylesheet" href="CSS/jquery-ui.css" type="text/css" /><script type="text/javascript" src="js/jquery-ui.js"><\/script>');
 </script> 
<style> 
  .custom-combobox {    position: relative;    display: inline-block;  margin: 0; margin-right: 1.8em;}  
  .custom-combobox-toggle {    position: absolute;    top: 0;    bottom: 0;    margin-left: -1px;    padding: 0;      *height: 1.7em;    *top: 0.1em;  }  
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; } 
  .ui-autocomplete {
    max-height: 200px;
    overflow-y: auto;   /* prevent horizontal scrollbar */
    overflow-x: hidden; /* add padding to account for vertical scrollbar */
    z-index:1000 !important; 
   }
 .ui-autocomplete-input{ width:90%; } 
 .ui-button-icon-only{ width:8%; }
 
 </style>
<script>
(function( $ ) 
		 {
			
		 $.widget( "custom.combobox", 
		 {      
		 _create: function() 
		 {        
		 this.wrapper = $( "<span>" )          
		 .addClass( "custom-combobox" )          
		 .insertAfter( this.element );         
		 this.element.hide();        
		 this._createAutocomplete();        
		 this._createShowAllButton();      
		 },       
		 _createAutocomplete: function() 
		 {        
		 var selected = this.element.children( ":selected" ),
		 value = selected.val() ? selected.text() : ""; 
		 this.input = $( "<input>" )          
		 .appendTo( this.wrapper )          
		 .val( value )          
		 .attr( "title", "" )
		 .attr( 'id', this.element.attr( 'id' )+'-input' )          
		 .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )          
		 .autocomplete({            
		 delay: 0,            
		 minLength: 0,            
		 source: $.proxy( this, "_source" )          })          
		 .tooltip({            
			 tooltipClass: "ui-state-highlight"          
			 });         
		 
		 this._on( this.input, {          
		 autocompleteselect: function( event, ui ) 
		 {            
		 ui.item.option.selected = true;            
		 this._trigger( "select", event, {              
		 item: ui.item.option            
		 });  
		                 
		 },           
		 autocompletechange: "_removeIfInvalid"  ,
		 
		 });      
		 },       
		 _createShowAllButton: function() {       
		 var input = this.input,          
		 wasOpen = false;         
		 $( "<a>" )          
		 .attr( "tabIndex", -1 )          
		 .attr( "title", "Show All Items" ).tooltip().appendTo( this.wrapper ).button({           
		 icons: {              
		 primary: "ui-icon-triangle-1-s"            
		 },            
		 text: false          
		 })          
		 .removeClass( "ui-corner-all" )          
		 .addClass( "custom-combobox-toggle ui-corner-right" )          
		 .mousedown(function() {            
		 wasOpen = input.autocomplete( "widget" ).is( ":visible" );          })          
		 .click(function() {            
		 input.focus();             
		 // Close if already visible           
		 if ( wasOpen ) {              return;            }             
		 // Pass empty string as value to search for, displaying all results            
		 input.autocomplete( "search", "" );          });      },
		 
		 _source: function( request, response ) {        
		 var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );        
		 response( this.element.children( "option" ).map(function() {         
		 var text = $( this ).text();          
		 if ( this.value && ( !request.term || matcher.test(text) ) )            
		 return {              
			 label: text,              
			 value: text,              
			 option: this           
			 };       
			 }) );     
		 },   
		 
		 _removeIfInvalid: function( event, ui ) {         
		 // Selected an item, nothing to do        
		 if ( ui.item ) 
		 { 
			 //Code for onchange event
		   var ele = $(this.element).attr('id');
		   $("#"+ele).trigger('change');
		   $("#"+ele).trigger('click');
		   $("#"+ele).trigger('focus');
		   $("#"+ele).trigger('blur');
		   $("#"+ele).trigger('dblclick');
		   $("#"+ele).trigger('keydown');
		   $("#"+ele).trigger('keypress');
		   $("#"+ele).trigger('keyup');
		   $("#"+ele).trigger('mousedown');
		   $("#"+ele).trigger('mousemove');
		   $("#"+ele).trigger('mouseover');
		   $("#"+ele).trigger('mouseup');
		   $("#"+ele).trigger('mouseout');
		   return;        
		 }
		 
		 // Search for a match (case-insensitive)        
		 var value = this.input.val(),          
		 valueLowerCase = value.toLowerCase(),          
		 valid = false;        
		 this.element.children( "option" ).each(function() {          
		 if ( $( this ).text().toLowerCase() === valueLowerCase ) 
		 {            
		 this.selected = valid = true;            return false;          
		 }       
		 });         
		 // Found a match, nothing to do        
		 if ( valid ) {        return;        }         
		 // Remove invalid value        
		 this.input.val( "" ).attr( "title", value + " didn't match any item" ).tooltip( "open" );   
		 this.element.val( "" );        
		 this._delay(function() 
		 {           
		 this.input.tooltip( "close" ).attr( "title", "" );        }, 2500 );        
		 this.input.data( "ui-autocomplete" ).term = "";      
		 },
		 
		 _destroy: function() {        
		 this.wrapper.remove();        
		 this.element.show();      }    });  })
		 ( jQuery );   
		 $(function() {    $( "select" ).combobox();
		                   $( "#toggle" ).click(function() {      
		 				   $( "select" ).toggle();    
		 				   });
      //For disabling combobox
      var noOfComboBoxes = document.getElementsByTagName("select").length;
      var comboBoxId="";
      if(noOfComboBoxes > 0)
      {
	for(var cnt = 0;cnt < noOfComboBoxes;cnt++)
	{
	    comboBoxId = document.getElementsByTagName("select")[cnt].id;
	    if(comboBoxId !=null && comboBoxId !='')
		{
	    if(document.getElementById(comboBoxId).disabled) 
	    {
		 $("#"+comboBoxId).parent().find("input.ui-autocomplete-input").autocomplete("option", "disabled", true).prop("disabled",true);
	         $("#"+comboBoxId).parent().find("a.ui-button").button("disable");
	    }
		}
	}
      }   
      }); 

 function disableComboBox(fieldId)
		 {
			 $(function() {
				    $("#"+fieldId).parent().find("input.ui-autocomplete-input").autocomplete("option", "disabled", true).prop("disabled",true);
            		$("#"+fieldId).parent().find("a.ui-button").button("disable");
			 }); 
		 }
		 function enableComboBox(fieldId)
		 {
			 $(function() {
			 $("#"+fieldId).parent().find("input.ui-autocomplete-input").autocomplete("option", "disabled", false).prop("disabled",false);
			 $("#"+fieldId).parent().find("a.ui-button").button("enable");
			 });  
		 }
		  
		 
</script>
</head>
<body>

</body>
</html>