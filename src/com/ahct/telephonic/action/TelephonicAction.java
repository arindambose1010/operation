package com.ahct.telephonic.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.common.vo.LabelBean;
import com.ahct.telephonic.form.TelephonicForm;
import com.ahct.telephonic.service.TelephonicService;
import com.ahct.telephonic.vo.TelephonicVO;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class TelephonicAction extends Action {

	@SuppressWarnings("unused")
	private final static Logger GLOGGER = Logger.getLogger ( TelephonicAction.class ) ;
	@SuppressWarnings({ "unchecked", "unused" })
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		response.setHeader("Cache-Control","no-cache");
        response.setHeader("pragma","no-cache");
        response.setDateHeader("Expires", 0);    
        HttpSession session = request.getSession ( false ) ;
        ConfigurationService configurationService = 
	            (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
        TelephonicService telephonicService = (TelephonicService)ServiceFinder.getContext(request).getBean("telephonicService");
        TelephonicForm telephonicForm = (TelephonicForm)form;
        String lStrLangID=null;
        String lStrUserId =null;
		List<LabelBean> grpList=null;
		String schemeId=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String roleId=null;
    	SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
    	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    	String callType=null;
    	if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
    	{
    		callType=request.getParameter("callType");
    	}
    	String lStrResultPage = HtmlEncode.verifySession(request, response);
        if (lStrResultPage.length() > 0)
        {
        	if(callType!=null && "Ajax".equals(callType))
        	{
        		request.setAttribute("AjaxMessage","SessionExpired");
        		return mapping.findForward("ajaxResult");
        	}
        	else
        		return mapping.findForward("sessionExpire");
        }
        if(session.getAttribute("EmpID").toString()!=null)
        {
        	lStrUserId = session.getAttribute("EmpID").toString();
        }
        if(session.getAttribute("LangID").toString()!=null)
        {
        	lStrLangID = session.getAttribute("LangID").toString();
        }
        if(session.getAttribute("groupList").toString()!=null)
        {
        	grpList=(List<LabelBean>)session.getAttribute("groupList");
        }
        if(session.getAttribute("userState").toString()!=null)
		{
			schemeId=session.getAttribute("userState").toString();
		}
        
        for(LabelBean labelBean:grpList)
        {
        	lStrgrpList.add(labelBean.getID());
        }
        if(lStrgrpList.contains(config.getString("Role.Pex")))
        {
        	roleId=config.getString("Role.Pex");
        }
        String lStrActionVal = request.getParameter("actionFlag");
        String lStrPageName = null; 
        String lStrRowsperpage=config.getString("pagin.RowsPerPage");
        String lStrStartIndex=config.getString("pagin.StartIndex0");
        String showPage= config.getString("pagin.ShowPage");
        if(lStrActionVal!= null && "telephonicIntimationSearch".equals(lStrActionVal)) 
        {
        	lStrStartIndex=config.getString("pagin.StartIndex0");
        	TelephonicVO telephonicVO = new TelephonicVO();
        	telephonicVO.setUserId(lStrUserId);
        	telephonicVO.setRowsPerPage(lStrRowsperpage);
        	telephonicVO.setStartIndex(lStrStartIndex);
        	telephonicVO.setShowPage(showPage);
        	telephonicVO.setSearchFlag(request.getParameter("searchFlag"));
        	telephonicVO.setTelephonicId(telephonicForm.getTelephonicId());
        	telephonicVO.setFromDate(telephonicForm.getFromDate());
        	telephonicVO.setToDate(telephonicForm.getToDate());
        	
        	if(telephonicForm.getRowsPerPage() != null && !telephonicForm.getRowsPerPage().equalsIgnoreCase(""))
        	{
        		lStrRowsperpage = telephonicForm.getRowsPerPage();
        		telephonicVO.setRowsPerPage(telephonicForm.getRowsPerPage() );
        		telephonicForm.setRowsPerPage( telephonicForm.getRowsPerPage());
        	}
        	if(telephonicForm.getShowPage() != null && !telephonicForm.getShowPage().equals(""))
        	{
        		telephonicVO.setShowPage(telephonicForm.getShowPage());
        		telephonicForm.setShowPage(telephonicForm.getShowPage());
        		if(Integer.parseInt(telephonicForm.getShowPage()) >Integer.parseInt(config.getString("pagin.RowsPerPage")) )
        		{
        			telephonicForm.setPrev(telephonicForm.getShowPage());
        			telephonicForm.setNext(Integer.toString((Integer.parseInt(telephonicForm.getShowPage())+1)));	
        		
        		}
        	}
        	TelephonicVO  lstTelephonicVO  = telephonicService.getTeleIntimationCases(telephonicVO);
        	telephonicForm.setStartIndex(lstTelephonicVO.getStartIndex());
        	telephonicForm.setRowsPerPage(telephonicVO.getRowsPerPage());
        	telephonicForm.setLstCaseSearch(lstTelephonicVO.getLstCaseSearch());
        	
        	telephonicForm.setEndIndex(Integer.toString((Integer.parseInt(telephonicForm.getStartIndex())+lstTelephonicVO.getLstCaseSearch().size())));
        	telephonicForm.setTotalRows(lstTelephonicVO.getTotRowCount());
                
        	int liTotalRows = Integer.parseInt( lstTelephonicVO.getTotRowCount());
        	int liRowsPerPage =Integer.parseInt( telephonicForm.getRowsPerPage());
        	int liStartIndex = Integer.parseInt( telephonicForm.getStartIndex());
            int liPageNo=Integer.parseInt(telephonicVO.getShowPage());
            int liStartPage=Integer.parseInt(config.getString("pagin.StartIndex1"));
            int liEndPage=Integer.parseInt(config.getString("pagin.RowsPerPage"));
            int liTotalPages=Integer.parseInt(config.getString("pagin.StartIndex0"));
       	    if((liTotalRows != Integer.parseInt(config.getString("pagin.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(config.getString("pagin.StartIndex0"))))
                 { 
                   liTotalPages=liTotalRows/liRowsPerPage;
                   if((liTotalRows%liRowsPerPage)>Integer.parseInt(config.getString("pagin.StartIndex0"))) 
                      liTotalPages++;
                   }
       	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(config.getString("pagin.StartIndex1"));
       	  if(liPageNo > Integer.parseInt(config.getString("pagin.StartIndex1")) && liPageNo <=liTotalPages)
             {
                if(liPageNo%Integer.parseInt(config.getString("pagin.RowsPerPage"))==1)
                {
                    liStartPage=liPageNo;
                    liEndPage=liPageNo+9;
                }
                else if(liPageNo >Integer.parseInt(config.getString("pagin.RowsPerPage")))
                {
                    int liTemp=Integer.parseInt(config.getString("pagin.StartIndex0"));
                    liTemp=liPageNo%Integer.parseInt(config.getString("pagin.RowsPerPage"));
                    if(liTemp == Integer.parseInt(config.getString("pagin.StartIndex0")))
                      liTemp=liPageNo-9;                  
                    else
                      liTemp=(liPageNo-liTemp)+Integer.parseInt(config.getString("pagin.StartIndex1"));
                    liStartPage=liTemp;
                    liEndPage=liTemp+9;                
                }
             } 
       	  if(liEndPage > liTotalPages)
                 liEndPage=liTotalPages;
             request.setAttribute("liEndPage", liEndPage);
             request.setAttribute("liTotalPages", liTotalPages);
             request.setAttribute("liStartPage", liStartPage);
             request.setAttribute("liPageNo", liPageNo);
             request.setAttribute("lStrRowsperpage", lStrRowsperpage);
             
             List<Integer> pages = new ArrayList<Integer>();
             if(liTotalPages >Integer.parseInt(config.getString("pagin.StartIndex0")))
             {
            	for(int i=liStartPage ; i<=liEndPage ;i++)
            	{
            		pages.add(i);
            	}
             }
            request.setAttribute("pages", pages);
            if(pages.size() > Integer.parseInt(config.getString("pagin.RowsPerPage")))
            {
            if(telephonicForm.getNext()!= null)
            {
            if(!(Integer.parseInt(telephonicForm.getNext()) <= pages.size()))
            {
            	telephonicForm.setNext(null);	
            }
            }
            else
            	telephonicForm.setNext(config.getString("pagin.next"));
            }
          
           
            /**
             * pagination code ending
             */
            lStrPageName="telephonicSearch";
        }
        if(lStrActionVal!= null && "telephonicIntimationRaised".equals(lStrActionVal)) 
        {
        	
        	int start_index=0;
			int end_index=0;
			int pageId;	
			int noOfRecords;
			int noOfPages=0;
        	
        	lStrStartIndex=config.getString("pagin.StartIndex0");
        	TelephonicVO telephonicVO = new TelephonicVO();
        	telephonicVO.setUserId(lStrUserId);
        	telephonicVO.setRowsPerPage(lStrRowsperpage);
        	telephonicVO.setStart_index(start_index);
        	telephonicVO.setEnd_index(end_index);
        	telephonicVO.setShowPage(showPage);
        	telephonicVO.setSearchFlag(request.getParameter("searchFlag"));
        	telephonicVO.setTelephonicId(telephonicForm.getTelephonicId());
        	telephonicVO.setFromDate(telephonicForm.getFromDate());
        	telephonicVO.setToDate(telephonicForm.getToDate());
        	telephonicVO.setSchemeId(schemeId);
        	
        
        	TelephonicVO  lstTelephonicVO  = telephonicService.getRegTeleIntimationCases(telephonicVO);
        	
        	if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
			{
				pageId=Integer.parseInt(request.getParameter("pageId"));
					
				if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
						end_index=Integer.parseInt(request.getParameter("end_index"));
					else
						end_index=10;
						
				start_index=end_index*(pageId-1);
			}
			else
			{
				pageId=1;
				end_index=10;
				start_index=0;
			}
        	
        	noOfRecords=lstTelephonicVO.getLstCaseSearch().size();
			int div=noOfRecords%end_index;
			if(div==0)
				noOfPages=noOfRecords/end_index;
			if(div!=0)
				noOfPages=(noOfRecords/end_index)+1;
			
			telephonicVO.setStart_index(start_index);
			telephonicVO.setEnd_index(end_index);

			lstTelephonicVO=new TelephonicVO();
			
			lstTelephonicVO  = telephonicService.getRegTeleIntimationCases(telephonicVO);
			
			telephonicForm.setLstCaseSearch(lstTelephonicVO.getLstCaseSearch());
			
        	//telephonicForm.setStartIndex(lstTelephonicVO.getStartIndex());
        	//telephonicForm.setRowsPerPage(telephonicVO.getRowsPerPage());
        	//telephonicForm.setLstCaseSearch(lstTelephonicVO.getLstCaseSearch());
        	
        	//telephonicForm.setEndIndex(Integer.toString((Integer.parseInt(telephonicForm.getStartIndex())+lstTelephonicVO.getLstCaseSearch().size())));
        	//telephonicForm.setTotalRows(lstTelephonicVO.getTotRowCount());
                
            request.setAttribute("pageId",pageId);
			request.setAttribute("noOfRecords",noOfRecords);
			request.setAttribute("lastPage",noOfPages);
			request.setAttribute("start_index",start_index);
			request.setAttribute("end_index",end_index);
			request.setAttribute("backFlag",request.getParameter("backFlag"));
			request.setAttribute("endresults",start_index+lstTelephonicVO.getLstCaseSearch().size());
            
           
            /**
             * pagination code ending
             */
            lStrPageName="telephonicIntimationReg";
        }
         return mapping.findForward(lStrPageName);
    }
}
