var elemJqueryScrollTop=0;
(function($){	   
	$(window).load(function(){
		$("body").mCustomScrollbar({
			theme:"dark",
			advanced:{updateOnBrowserResize:true,updateOnContentResize:true},
			callbacks:{
				onScroll:function(){ 
					elemJqueryScrollTop= mcs.top ;
					elemJqueryScrollTop =(-elemJqueryScrollTop);
				}
			}  
			});	
	});	
})(jQuery);
