	/***************************/
	//@website: www.ZwebbieZ.com
	//@license: Feel free to use it, but keep this credits please!					
	/***************************/

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
		$("#popupContactClose").click(function(){
			disablePopup('#popupContact');
		});
		//Click the x event!
		$("#popupMailClose").click(function(e){
			disablePopup('#popupMail');
			e.preventDefault();
		});
		//Click the x event!
		$("#popupContactCloseKnowId").click(function(){
			disablePopup('#popupContactKnowId');
		});
		$("#popupContactCloseTrouble").click(function(){
			disablePopup('#popupContactTrouble');
		});
		$("#popupRaiseCloseComplaint").click(function(){
			disablePopup('#popupRaiseComplaint');
		});
			$("#popupRaiseCloseEmailSubscription").click(function(){
			disablePopup('#popupRaiseEmailSubscription');
		});
		$("#popupThemeClose").click(function(){
			disablePopup('#popupTheme');
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
		
		
		
		
		
		$("#ktabs li").click(function() {
			//	First remove class "active" from currently active tab
			$("#ktabs li").removeClass('active');

			//	Now add class "active" to the selected/clicked tab
			$(this).addClass("active");

			//	Hide all tab content
			$(".ktab_content").hide();

			//	Here we get the href value of the selected tab
			var selected_tab = $(this).find("a").attr("href");

			//	Show the selected tab content
			$(selected_tab).fadeIn();

			//	At the end, we add return false so that the click on the link is not executed
			return false;
		});
	});
	