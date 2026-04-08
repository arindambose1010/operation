document.onkeydown = document.onkeypress = function (evt) {
	if (typeof evt == 'undefined') {
	evt = window.event;
	}
	if (evt) {
	var keyCode = evt.keyCode ? evt.keyCode : evt.charCode;
	/* special characters check */
	if (keyCode > 31 && (keyCode < 47 || keyCode > 58)
		    &&(keyCode < 65 || keyCode > 90)&&(keyCode < 96 || keyCode > 122 )&&( keyCode < 188 || keyCode > 191 )
			&&(keyCode != 63 &&(keyCode < 37 || keyCode > 41)&&(keyCode != 44)&&(keyCode != 46)&&(keyCode != 91)&&(keyCode != 93)
			&&(keyCode != 95)&&(keyCode !=32))&&(keyCode != 37))	
		    return false; 	
	else
			return true; 
	
	if (keyCode == 8) {
		var objTxtBox = window.event.srcElement; 
		if(objTxtBox.type != null && (objTxtBox.type =='text' ||objTxtBox.type=='textarea'))
			{
			return true;
			}
		else
			{
	if (evt.preventDefault) {
	evt.preventDefault();
	}
	return false;
			}
	}
	else {
	return true;
	}
	}
	else {
	return true;
	}
	}
function fn_maxmizeTop1(){

	parent.fn_maxmizeTop();
	if(parent.topMaxFlag==1){
		document.getElementById("topImage").src="images/updownArrow.jpg";
		document.getElementById("topImage").alt="Minimise";
		//document.getElementById("topSlide").innerHTML="Minimise";
	}
		else{
			document.getElementById("topImage").src="images/updownArrow.jpg";
			document.getElementById("topImage").alt="Maximize";
			//document.getElementById("topSlide").innerHTML="Maxmize";
		}
	
}

function fn_maxmizeRight1(){
	parent.fn_maxmizeRight();
	if(parent.rightMaxFlag==1){
		document.getElementById("menuImage").src="images/rightLeftArrow.jpg";
		document.getElementById("menuImage").alt="Show Menu";
		//document.getElementById("menuSlide").innerHTML="Show Menu";
	
	}
			else{
				document.getElementById("menuImage").src="images/rightLeftArrow.jpg";
				document.getElementById("menuImage").alt="Hide Menu";
			//	document.getElementById("menuSlide").innerHTML="Hide Menu";
			
			}
}