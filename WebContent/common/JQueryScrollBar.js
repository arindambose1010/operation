var scrollOrStop="Start";
		 var elemJqueryScrollTop=0;
		(function($){

			$(window).load(function(){
				$("body").mCustomScrollbar({
					theme:"dark",
						  callbacks:{
								onScroll:function(){ 
									$(".output .content-position").text(mcs.top);	
									elemJqueryScrollTop= mcs.top ;
									elemJqueryScrollTop =(-elemJqueryScrollTop);
									
								}
							}
						});
				
				$("body").hover(function(){
					$(document).data({"keyboard-input":"enabled"});
					$(this).addClass("keyboard-input");
				},function(){
					$(document).data({"keyboard-input":"disabled"});
					$(this).removeClass("keyboard-input");
				});
				$(document).keydown(function(e){
					if($(this).data("keyboard-input")==="enabled"){
						var activeElem=$(".keyboard-input"),
							activeElemPos=Math.abs($(".keyboard-input .mCSB_container").position().top),
							pixelsToScroll=60;
						if(e.which===38){ //scroll up
							if(scrollOrStop=="Start")
							{
							e.preventDefault();
							if(pixelsToScroll>activeElemPos){
								activeElem.mCustomScrollbar("scrollTo","top");
							}else{
								activeElem.mCustomScrollbar("scrollTo",(activeElemPos-pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
							}
							}
						}else if(e.which===40){ //scroll down
							if(scrollOrStop=="Start")
							{
							e.preventDefault();
							activeElem.mCustomScrollbar("scrollTo",(activeElemPos+pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
							}
						}
					}
				});
			});
		})(jQuery);
		function startScroll()
		{
			scrollOrStop ="Start";
		}
		function stopScroll()
		{
			scrollOrStop ="Stop";
		}
