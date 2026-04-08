var msgBoxImagePath = "/Operations/images/";

jQuery.msgBox = msg;


var captureTopValue;  



function detectBrowser(){
	if(navigator.userAgent.indexOf("Opera")!=-1){
		return "Opera";
		
	}else if(navigator.userAgent.indexOf("MSIE")!=-1){
		return "MSIE";
	}else if(navigator.userAgent.indexOf("Navigator")!=-1){
		return "Netscape";
	}else if(navigator.userAgent.indexOf("Firefox")!=-1){
		return "Firefox";
	}else if(navigator.userAgent.indexOf("Chrome")!=-1){
		return "Chrome";
	}
}


function getOffset( el ) {
    var _x = 0;
    var _y = 0;
    while( el && !isNaN( el.offsetLeft ) && !isNaN( el.offsetTop ) ) {
        _x += el.offsetLeft - el.scrollLeft;
        _y += el.offsetTop - el.scrollTop;
        el = el.offsetParent;
    }
    return { top: _y, left: _x };
}


function msg (options) {
	
	
	
    var isShown = false;
    var typeOfValue = typeof options;
    var defaults = {
        content: (typeOfValue == "string" ? options : "Message"),
        title: "Warning",
        type: "alert",
        autoClose: false,
        timeOut: 0,
        showButtons: true,
        buttons: [{ value: "Ok"}],
        inputs: [{ type: "text", name:"userName", header: "User Name" }, { type: "password",name:"password", header: "Password"}],
        success: function (result) { },
        beforeShow: function () { },
        afterShow: function () { },
        beforeClose: function () { },
        afterClose: function () { },
        opacity: 0.1
    };
    options = typeOfValue == "string" ? defaults : options;
    if (options.type != null) {
        switch (options.type) {
            case "alert":
                options.title = options.title == null ? "Warning" : options.title;
                break;
            case "info":
                options.title = options.title == null ? "Information" : options.title;
                break;
            case "error":
                options.title = options.title == null ? "Error" : options.title;
                break;
            case "confirm":
                options.title = options.title == null ? "Confirmation" : options.title;
                options.buttons = options.buttons == null ? [{ value: "Yes" }, { value: "No" }, { value: "Cancel"}] : options.buttons;
                break;
            case "prompt":
                options.title = options.title == null ? "Log In" : options.title;
                options.buttons = options.buttons == null ? [{ value: "Login" }, { value: "Cancel"}] : options.buttons;
                break;
            case "largeInfo":
                options.title = options.title == null ? "Warning" : options.title;
                break;
            case "alertAtTop":
                options.title = options.title == null ? "Warning" : options.title;
                break;
	    case "showContent":
        	 image = "fileclose.png";
            break;
            default:
                image = "alert.png";
        }
    }
    options.timeOut = options.timeOut == null ? (options.content == null ? 500 : options.content.length * 70) : options.timeOut;
    options = $.extend({},defaults, options);
    
   
    if (options.autoClose) {
        setTimeout(hide, options.timeOut);
    }
    var image = "";
    
    switch (options.type) {
        case "alert":
            image = "alert.png";
            break;
        case "info":
            image = "info.png";
            break;
        case "error":
            image = "error.png";
            break;
        case "confirm":
            image = "confirm.png";
            break;
        case "largeInfo":
            image = "alert.png";
            break;
        case "alertAtTop":
            image = "alert.png";
            break;
        default:
            image = "alert.png";
    }
   
   
    var divId = "msgBox" + new Date().getTime();

    //Css changed for IE7
    if ( navigator.userAgent.match(/msie/i) && navigator.userAgent.match(/6/) ) { var divMsgBoxContentClass = "msgBoxContentIEOld"; } else {var divMsgBoxContentClass = "msgBoxContent";} 


    var divMsgBoxId = divId; 
    var divMsgBoxContentId = divId+"Content"; 
    var divMsgBoxImageId = divId+"Image";
    var divMsgBoxButtonsId = divId+"Buttons";
    var divMsgBoxBackGroundId = divId+"BackGround";
 
    var buttons = "";
	var button1;
	var button2;
    var i=0;
    $(options.buttons).each(function (index, button) {
    	
        buttons += "<input class=\"msgButton\" type=\"button\" name=\"" + button.value + "\" id=\"" + button.value + "\" value=\"" + button.value + "\" />";
        if(index ==0)
        	{
        	button1=button.value;
        	}
        else
        	{
        	button2 = button.value;
        	}
       
    });
    
    var inputs = "";
    $(options.inputs).each(function (index, input) {
        var type = input.type;
        if (type=="checkbox" || type =="radiobutton") {
            inputs += "<div class=\"msgInput\">" +
            "<input type=\"" + input.type + "\" name=\"" + input.name + "\" "+(input.checked == null ? "" : "checked ='"+input.checked+"'")+" value=\"" + (typeof input.value == "undefined" ? "" : input.value) + "\" />" +
            "<text>"+input.header +"</text>"+
            "</div>";
        }
        else {
            inputs += "<div class=\"msgInput\">" +
            "<span class=\"msgInputHeader\">" + input.header + "<span>" +
            "<input type=\"" + input.type + "\" name=\"" + input.name + "\" value=\"" + (typeof input.value == "undefined" ? "" : input.value) + "\" />" +
            "</div>";
        }
    });

    
    var divBackGround = "<div id=\"" + divMsgBoxBackGroundId + "\" class=\"msgBoxBackGround\"></div>";
    var divTitle = "<div class=\"msgBoxTitle\">" + options.title + "</div>";
      if (options.type == "showContent")
    	{
    	var divContainer = "<div class=\"msgBoxContainer\"><div id=\"" + divMsgBoxImageId + "\" class=\"showContentMsgBoxImage\"><img src=\"" + msgBoxImagePath + image + "\"/></div><div id=\"" + divMsgBoxContentId + "\" class=\"" + divMsgBoxContentClass + "\"><p><span>" + options.content + "</span></p></div></div>"; 
    	}
    else
    	{
    var divContainer = "<div class=\"msgBoxContainer\"><div id=\"" + divMsgBoxImageId + "\" class=\"msgBoxImage\"><img src=\"" + msgBoxImagePath + image + "\"/></div><div id=\"" + divMsgBoxContentId + "\" class=\"" + divMsgBoxContentClass + "\"><p><span>" + options.content + "</span></p></div></div>"; 
    	}
    var divButtons = "<div id=\"" + divMsgBoxButtonsId + "\" class=\"msgBoxButtons\">" + buttons + "</div>";

    var divInputs = "<div class=\"msgBoxInputs\">" + inputs + "</div>";

    var divMsgBox; 
    var divMsgBoxContent; 
    var divMsgBoxImage;
    var divMsgBoxButtons;
    var divMsgBoxBackGround;
    
    if (options.type == "prompt") { 
        $("body").append(divBackGround + "<div id=" + divMsgBoxId + " class=\"msgBox\">" + divTitle + "<div>" + divContainer + (options.showButtons ? divButtons + "</div>" : "</div>") + "</div>"); 
        divMsgBox = $("#"+divMsgBoxId); 
        divMsgBoxContent = $("#"+divMsgBoxContentId); 
        divMsgBoxImage = $("#"+divMsgBoxImageId);
        divMsgBoxButtons = $("#"+divMsgBoxButtonsId);
        divMsgBoxBackGround = $("#"+divMsgBoxBackGroundId);

        divMsgBoxImage.remove();
        divMsgBoxButtons.css({"text-align":"center","margin-top":"5px"});
        divMsgBoxContent.css({"width":"100%","height":"100%"});
        divMsgBoxContent.html(divInputs);
    }
    else if(options.type == "largeInfo")
    {
        $("body").append(divBackGround + "<div id=" + divMsgBoxId + " class=\"msgBoxLarge\">" + divTitle + "<div>" + divContainer + (options.showButtons ? divButtons + "</div>" : "</div>") + "</div>"); 
    	divMsgBox= $("#"+divMsgBoxId); 
        divMsgBoxContent = $("#"+divMsgBoxContentId); 
        divMsgBoxImage = $("#"+divMsgBoxImageId);
        divMsgBoxButtons = $("#"+divMsgBoxButtonsId);
        divMsgBoxBackGround = $("#"+divMsgBoxBackGroundId);
        divMsgBoxContent.css({"font-size":"8pt",width:"350px"});
        divMsgBoxButtons.css({"position":"absolute","bottom":"0","right":"0"});
        
    }
 	else if (options.type == "showContent")
	 {
    	 $("body").append(divBackGround + "<div id=" + divMsgBoxId + " class=\"msgBox\"><div>" + divContainer + (options.showButtons ? divButtons + "</div>" : 	"</div>") + "</div>"); 
     	 divMsgBox= $("#"+divMsgBoxId); 
         divMsgBoxContent = $("#"+divMsgBoxContentId); 
         divMsgBoxImage = $("#"+divMsgBoxImageId);
         divMsgBoxButtons = $("#"+divMsgBoxButtonsId);
         divMsgBoxBackGround = $("#"+divMsgBoxBackGroundId);
		 
         divMsgBoxContent.css({"font-size":"8pt",width:"350px"});
         divMsgBox.css({"width":"500px","min-height":"100px"});
                 
         divMsgBoxButtons.remove();
        	
	 }
    else
    	{
    	 $("body").append(divBackGround + "<div id=" + divMsgBoxId + " class=\"msgBox\">" + divTitle + "<div>" + divContainer + (options.showButtons ? divButtons + "</div>" : "</div>") + "</div>"); 
     	 divMsgBox= $("#"+divMsgBoxId); 
         divMsgBoxContent = $("#"+divMsgBoxContentId); 
         divMsgBoxImage = $("#"+divMsgBoxImageId);
         divMsgBoxButtons = $("#"+divMsgBoxButtonsId);
         divMsgBoxBackGround = $("#"+divMsgBoxBackGroundId);
    	}

    var width = divMsgBox.width();
    var height = divMsgBox.height();
    var windowHeight = $(window).height();
    var windowWidth = $(window).width();
   
      
   
    var top = windowHeight/2- height/2;
    var left = windowWidth / 2 - width / 2;
    
    var browserName=detectBrowser();
   
    
  
    
     if(browserName == "Firefox")
     	{
    	
	    if(document.body.scrollTop!=null && document.body.scrollTop!='0')
	    	{
	    	if(windowHeight > height*1.5)
	    		{
			    	captureTopValue = document.body.scrollTop;
			    	top = document.body.scrollTop+height/2;
	    		}
	    	else
	    		{
	    		captureTopValue = 10;
	    		top=10;
				if(windowHeight>height)
	    		divMsgBox.css({ "min-height": height-10});
				else
				divMsgBox.css({ "min-height": windowHeight-10});
	    		}
	    	 
	    	}
	    else if(parent.elemJqueryScrollTop!=null)
	    	{
		    	//To capture New Jquery Scroll bar Top Position
		    	if(windowHeight > height*1.5)
	    		{
		    	captureTopValue= parent.elemJqueryScrollTop;
		    	top = parent.elemJqueryScrollTop+height/2;
	    		}
		    	else
		    		{
		    		captureTopValue= parent.elemJqueryScrollTop;
			    	top=10;
					if(windowHeight>height)
		    		divMsgBox.css({ "min-height": height-10});
					else
					divMsgBox.css({ "min-height": windowHeight-10});
		    		}
	    	}
      
     	}
     
     if(browserName == "MSIE" || browserName == "Chrome")
    	 {
			    	if(document.documentElement.scrollTop!=null && document.documentElement.scrollTop!='0')
			     	{
			    		if(windowHeight > height*1.5)
			    		{
					     	captureTopValue = document.documentElement.scrollTop;
					        top =document.documentElement.scrollTop+height/2;
			    		}
			    		else
			    			{
			    			captureTopValue = document.documentElement.scrollTop;
			    			top=10;
							if(windowHeight>height)
				    		divMsgBox.css({ "min-height": height-10});
							else
							divMsgBox.css({ "min-height": windowHeight-10});
			    			}
			       
			     	}
			    	else if(parent.elemJqueryScrollTop!=null)
			    		{
					    		if(windowHeight > height*1.5)
					    		{
					    		captureTopValue= parent.elemJqueryScrollTop;
						    	top = parent.elemJqueryScrollTop+height/2;
					    		}
					    		else
					    			{
					    			captureTopValue= parent.elemJqueryScrollTop;
					    			top=10;
									if(windowHeight>height)
						    		divMsgBox.css({ "min-height": height-10});
									else
									divMsgBox.css({ "min-height": windowHeight-10});
					    			}
			    		}
			       
    	 }
     if(options.type == "alertAtTop")
	{
          top=30;
	}
	if(options.type == "showContent")
	{
   	var position=getOffset(document.getElementById('moreLink'));
    	top=position.top-120;
	left=position.left-400;
	}
    show();
    

    function show() {
        if (isShown) {
            return;
        }
        
 	if(options.type != "showContent")
        {
        var buttonFocus = document.getElementById(button1); //for mozilla;
        buttonFocus.focus();
	}
        
      
        
        divMsgBox.css({ opacity: 0, top: top, left: left});
        divMsgBox.css("background-image", "url('"+msgBoxImagePath+"msgBoxBackGround.png')");
        divMsgBoxBackGround.css({ opacity: options.opacity });
        options.beforeShow();
        divMsgBoxBackGround.css({ "width":$(window).width() , "height": getDocHeight() });
        

        $(divMsgBoxId+","+divMsgBoxBackGroundId).fadeIn(0);
        divMsgBox.animate({ opacity: 0.9, "top": top, "left": left }, 100);
        
        setTimeout(options.afterShow, 200);
        isShown = true;
        window.scrollTo(left,captureTopValue);
        $(window).bind("resize", function (e) {
            var width = divMsgBox.width();
            var height = divMsgBox.height();
            var windowHeight = $(window).height();
            var windowWidth = $(window).width();

            var top = windowHeight / 2 - height / 2;
            var left = windowWidth / 2 - width / 2;

            divMsgBox.css({ "top": top, "left": left });
            
        });
        
        if(options.type != "showContent")
    	{  
        var buttonOnFocus =button1;
        var buttonToBeFocused = button2;
    	}
        
        //Setting focus explicitly while clicking on divMsgBoxBackGround
        divMsgBoxBackGround.click(function (e) {
           if(options.type == "showContent")
        	{ 
            	hide();
        	}
	else
	{
            if (!options.showButtons || options.autoClose) {
                hide();
            }
            else {
                //getFocus(); -- Commented for removal of fading in and out while clicking outside message box .
            	var butFocus =document.getElementById(buttonOnFocus); //for mozilla;
    		    butFocus.focus();
            }
	}
        });
        
      //Setting focus explicitly while clicking on divMsgBox
        divMsgBox.click(function (e)
        		{
	        	if(options.type != "showContent")
			{
	        	var butFocus =document.getElementById(buttonOnFocus); //for mozilla;
			    butFocus.focus();
			}
        		});
	divMsgBoxImage.click(function (e)
        	{
        	if(options.type == "showContent")
        	{ 
            	hide();
        	}
        	});
        
        
        // Tab Key Control For IE & Mozilla
        //If it is Confirm box , option is given to toggle b/n yes or no. For rest all focus will be given to the respective button and tab control is halted.
        document.onkeydown = function (e) {
        	var cacheObj;
        	if(window.event)
			{
			   if (window.event && !(window.event.keyCode == 9 || window.event.keyCode == 13) ) { 
        			return false;
			    }
			    if (window.event && window.event.keyCode == 9) { // Capture and remap TAB
			        window.event.keyCode = 9;
			    }
			    if (window.event && window.event.keyCode == 9) { // New action for TAB

			    	if(options.type =="confirm")
			    		{
			    			cacheObj = buttonOnFocus;
			    		    var butFocus =document.getElementById(buttonToBeFocused); //for mozilla;
			    		    butFocus.focus();
			    		    buttonOnFocus = buttonToBeFocused;
			    		    buttonToBeFocused =cacheObj;
			    		    return false;
			    		}
			    	else
			    		{
			    		 return false;
			    		}
			       
			    }
			if (window.event && window.event.keyCode == 13 && options.type == "showContent") 
			    { 
			    	hide();
			    	return false;
			    }
			}
		else
			{
				if (!(e.which == 9 ||  e.which == 13)) { 
					e.preventDefault();
			        }
			 	if(e.which == 9)  //for mozilla
				 {
					 if(options.type =="confirm")
			    		{
			    			cacheObj = buttonOnFocus;
			    		    var butFocus =document.getElementById(buttonToBeFocused); //for mozilla;
			    		    butFocus.focus();
			    		    buttonOnFocus = buttonToBeFocused;
			    		    buttonToBeFocused =cacheObj;
			    		    e.preventDefault();
			    		}
			    	  else
			    		{
			    		e.preventDefault();
			    		}
					
				 }
				if (e.which == 13 && options.type == "showContent") 
				    { 
				    	hide();
				    	e.preventDefault();
				    }
			}
    	};
       
    }

    function hide() {
        if (!isShown) {
            return;
        }
       allowTabKey();
        options.beforeClose();
        divMsgBox.animate({ opacity: 0, "top": top - 50, "left": left }, 100);
        divMsgBoxBackGround.fadeOut(300);
        setTimeout(function () { divMsgBox.remove(); divMsgBoxBackGround.remove(); }, 300);
        setTimeout(options.afterClose, 300);
        isShown = false;
    }

    function getDocHeight() {

        var D = document;
        return Math.max(
        Math.max(D.body.scrollHeight, D.documentElement.scrollHeight),
        Math.max(D.body.offsetHeight, D.documentElement.offsetHeight),
        Math.max(D.body.clientHeight, D.documentElement.clientHeight));
    }
    
    

    function getFocus() {
    	divMsgBox.fadeOut(200).fadeIn(200);
    }

    $("input.msgButton").click(function (e) {
  
        e.preventDefault();
        var value = $(this).val();
        if (options.type != "prompt") {
            options.success(value);
        }
        else {
            var inputValues = [];
            $("div.msgInput input").each(function (index, domEle) {
                var name = $(this).attr("name");
                var value = $(this).val();
                var type = $(this).attr("type");
                if (type == "checkbox" || type == "radiobutton") {
                    inputValues.push({ name: name, value: value,checked: $(this).attr("checked")});
                }
                else {
                    inputValues.push({ name: name, value: value });
                }
            });
            options.success(value,inputValues);
        }
        hide();
    });

    
};


function allowTabKey()
{
	document.onkeydown = function (e) {
	    if (window.event && window.event.keyCode == 9) { // Capture and remap TAB
	        window.event.keyCode = 9;
	    }
	    if (window.event && window.event.keyCode == 9) { // New action for TAB
	        return true;
	    }
	};
};

function jqueryAlertMsg(titleContent,MessageContent,onSuccess)
{
	MessageContent = MessageContent.replace('<br>','\n').replace('<br/>','\n'); 
	alert(MessageContent);
	if(onSuccess !=undefined)
		{
		onSuccess();
		}
	/*


    if(onSuccess !=undefined)
    {
            $.msgBox({
            title:titleContent,
            content:MessageContent,
            success: function (result) 
            { 
              onSuccess();
            }
            });
    }
    else
    {       $.msgBox({
            title:titleContent,
            content:MessageContent
            });
    
    }
 */
}

function jqueryInfoMsg(titleContent,MessageContent,onSuccess)
{
	MessageContent = MessageContent.replace('<br>','\n').replace('<br/>','\n'); 
	alert(MessageContent);
	if(onSuccess !=undefined)
		{
		onSuccess();
		}
     /* if(onSuccess !=undefined)
      {
           $.msgBox({
           title:titleContent,
           content:MessageContent,
           type:"info",
           success: function (result) 
           { 
             onSuccess();
           }
           });

      }
      else
      {
          $.msgBox({
          title:titleContent,
          content:MessageContent,
          type:"info"
          });
      }*/
}


function jqueryErrorMsg(titleContent,MessageContent,onSuccess)
{
	MessageContent = MessageContent.replace('<br>','\n').replace('<br/>','\n'); 
	alert(MessageContent);
	if(onSuccess !=undefined)
		{
		onSuccess();
		}
 
   /*if(onSuccess !=undefined)
         {
              $.msgBox({
              title:titleContent,
              content: MessageContent,
              type: "error",
              buttons: [{ value: "Ok"}],
              success: function (result) 
              { 
               onSuccess();
              }
              });
         }
         else
         {
              $.msgBox({
              title:titleContent,
              content: MessageContent,
              type: "error",
              buttons: [{ value: "Ok"}]
              });
         }
*/
}


function jqueryConfirmMsg(titleContent,MessageContent,onSuccess,onFailure)
{
	var r=confirm(MessageContent);
    if (r==true)
    	{
    	if(onSuccess !=undefined)
		{
		onSuccess();
		}
    	}
    else
    	{
    	if(onFailure !=undefined)
		{
    		onFailure();
    	}    	
    	return false;
    	}
       
	
	

        /* if(onSuccess !=undefined)
         {
               $.msgBox({
               title: titleContent,
               content: MessageContent,
               type: "confirm",
               buttons: [{ value: "Yes" }, { value: "No" }],
               success: function (result) 
                 {
            	   if(result=='Yes')
            	   { onSuccess();}
            	   if(result=='No')
            	   {
            		   if(onFailure !=undefined)
            	         {
            		   onFailure();
            		   }
            	   }
            	   
                 }
               });
         }
         else
         {
               $.msgBox({
               title: titleContent,
               content: MessageContent,
               type: "confirm",
               buttons: [{ value: "Yes" }, { value: "No" }]
               });
         }*/

}


function jqueryPromptMsg(val1,val2)
{

		$.msgBox({ type: "prompt",
                title: "Log In",
                inputs: [
                { header: "Employee No",content: '${val1}'},
                { header: "Emp Name",content: '${val2}'}]
                            });

}


//If the information to be displayed is large,use this function
function jqueryInfoMsgLarge(titleContent,MessageContent,onSuccess)
{
	MessageContent = MessageContent.replace('<br>','\n').replace('<br/>','\n'); 
	alert(MessageContent);
	if(onSuccess !=undefined)
		{
		onSuccess();
		}
    /*if(onSuccess !=undefined)
    {
            $.msgBox({
            title:'Information',
            content:MessageContent,
            type: "largeInfo",
            success: function (result) 
            { 
              onSuccess();
            }
            });
    }
    else
    {       $.msgBox({
            title:'Information',
            content:MessageContent,
            type: "largeInfo"
            });
    
    }*/
 
}
//If the Alert should be displayed at Top use this function
function jqueryAlertMsgTop(titleContent,MessageContent,onSuccess)
{
	MessageContent = MessageContent.replace('<br>','\n').replace('<br/>','\n'); 
	alert(MessageContent);
	if(onSuccess !=undefined)
		{
		onSuccess();
		}
    /*  if(onSuccess !=undefined)
      {
           $.msgBox({
           title:titleContent,
           content:MessageContent,
           type:"alertAtTop",
           success: function (result) 
           { 
             onSuccess();
           }
           });

      }
      else
      {
          $.msgBox({
          title:titleContent,
          content:MessageContent,
          type:"alertAtTop"
          });
      }*/
}

function jqueryShowContentMsg(MessageContent,onSuccess)
{

      if(onSuccess !=undefined)
      {
           $.msgBox({
           content:MessageContent,
           type:"showContent",
           success: function (result) 
           { 
             onSuccess();
           }
           });

      }
      else
      {
          $.msgBox({
          content:MessageContent,
          type:"showContent"
          });
      }
}


