package com.ahct.common.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class TrainingMaterialsAction extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		
        String lStrFlagStatus = request.getParameter("actionFlag");
        ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("getManuals"))
		 {
			 
			 
			 String id=request.getParameter("id");
			 String lStrDirPath=config.getString("manualsPath");
			
			 String lStrFileName=config.getString("manualsPath")+"/"+id+".pdf";
			
			 System.out.println("file name = "+lStrFileName);
			 String lStrContentType = "application/pdf";
			// String lStrContentType = request.getContentType();
			 
			try
			{
				
			  File file = new File(lStrFileName);
			  
			  FileInputStream in = new FileInputStream(file);
			  DataInputStream dis = null;
			  dis = new DataInputStream ( in ) ;
             byte[] lbBytes = new byte[ dis.available () ] ;
             OutputStream out = response.getOutputStream();
             in.read ( lbBytes ) ;
             response.setContentType ( lStrContentType ) ;
             response.setHeader("Content-Disposition","filename="+lStrFileName);//006
             out.write(lbBytes);
			  
			 
			 //Desktop.getDesktop().open(file);
			}
			catch(IOException ex)
			{
				ex.getMessage();
				ex.printStackTrace();
				
			}
		 }
		
		
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("dentalGuidelines"))
		 {
			 
			 
			 String id=request.getParameter("id");
			 String lStrDirPath=config.getString("manualsPath");
			
			 String lStrFileName=config.getString("manualsPath")+"/"+id+".tif";
			
			 System.out.println("file name = "+lStrFileName);
			 String lStrContentType = "image/tiff";
			// String lStrContentType = request.getContentType();
			 
			try
			{
				
			  File file = new File(lStrFileName);
			  
			  FileInputStream in = new FileInputStream(file);
			  DataInputStream dis = null;
			  dis = new DataInputStream ( in ) ;
             byte[] lbBytes = new byte[ dis.available () ] ;
             OutputStream out = response.getOutputStream();
             in.read ( lbBytes ) ;
             response.setContentType ( lStrContentType ) ;
             response.setHeader("Content-Disposition","filename="+lStrFileName);//006
             out.write(lbBytes);
			  
			 
			 //Desktop.getDesktop().open(file);
			}
			catch(IOException ex)
			{
				ex.getMessage();
				ex.printStackTrace();
				
			}
		 }

		return null;
    }
	
	
}
