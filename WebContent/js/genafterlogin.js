/* Pensioner after login - script: By default when mouse hover on the leftside images download health Card, Initiate health card,
 * new/rejected beneficiaries and Any Issue or complaint, the content in the middle div will change accordingly
 */
/*$("#PenInst_middle").load('../staticpages/EmpInstContent.html div',function(){
$("#PenInst_middle div:first").show().siblings().hide();
$("#PenInst_middle div:first").mCustomScrollbar({autoHideScrollbar:true});
});*/
$("#PenInst_middle").load('../login/Gennoticeboard.jsp', function(){
	$('p.noticeboard_tab:first').addClass('noticeboard_hover').next("div.noticeboard_contexttab").show().siblings('p.noticeboard_tab').addClass('noticeboard_closed');
	$('p.noticeboard_tab').click(function(){
		$(this).addClass('noticeboard_hover').next("div.noticeboard_contexttab").slideDown(500).siblings("div.noticeboard_contexttab").slideUp("slow");
		$(this).siblings('p.noticeboard_tab').addClass('noticeboard_closed').removeClass('noticeboard_hover');
		});
	});
/* Pensioner Instruction -script: changed the content in the middle div when clicking on the Knowledge Centre tab */
$("#KC").click(function(e){
	$("#PenInst_middle").load('../staticpages/KnowledgeCentre.html #KC_tabs');
	e.preventDefault();
}
);
   
    /* Script to load 104 Services */
  $("#104Services").click(function(){
     $("#PenInst_middle").load('../staticpages/Services104.html #popupServices104', function(){
		             $("#PenInst_middle #popupServices104").mCustomScrollbar({autoHideScrollbar:true});

	 });
});