

package com.ahct.common.util;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class AttachmentSize {
    public AttachmentSize() {
    }
  
	/**
	 * 
	 * @param lbBytes
	 * @return ArrayList that contains "exceededflag","size of the attachment" and "Maximimum attchment size"  respectively
	 */
	public ArrayList checkFileSize(byte[] lbBytes)
	{  
		ResourceBundle localConstantBundle = ResourceBundle.getBundle("SGVConstants");
		int iConstantSize=Integer.parseInt(localConstantBundle.getString("Attachment.AttachSizeKB"));
		String lStrSizeExceed="false";
		int iFileSize=0;
		String lStrDocSize = "";
		String lStrMaxSize="";
		iFileSize = lbBytes.length;
		ArrayList lFileDtlsList = new ArrayList();
                    
		if(iFileSize<1024)
		{
			lStrDocSize = String.valueOf(iFileSize) + "B";
		}
		else if(iFileSize<(1024*1024))
		{
			lStrDocSize = String.valueOf(iFileSize/(1024))+"KB";
		}
		else if(iFileSize<(1024*1024*1024))
		{
			lStrDocSize = String.valueOf(iFileSize/(1024*1024))+"MB";
		}           
		if(iFileSize > iConstantSize)
		{
			lStrSizeExceed = "true";              
		}         
         
		if(iConstantSize <1024)
		{
			lStrMaxSize = String.valueOf(iConstantSize) + "B";
		}
		else if(iConstantSize<(1024*1024))
		{
			lStrMaxSize = String.valueOf(iConstantSize/(1024))+"KB";
		}
		else if(iConstantSize<(1024*1024*1024))
		{
			lStrMaxSize = String.valueOf(iConstantSize/(1024*1024))+"MB";
		}  
          
          
		lFileDtlsList.add(lStrSizeExceed);
		lFileDtlsList.add(lStrDocSize);
		lFileDtlsList.add(lStrMaxSize);
         
		return lFileDtlsList;
	}
        //begin 001
    public ArrayList checkFileSize(byte[] lbBytes,String lStrSize)
    {  
           // ResourceBundle localConstantBundle = ResourceBundle.getBundle("SGVConstants");
            int iConstantSize=Integer.parseInt(lStrSize);//Integer.parseInt(localConstantBundle.getString("Attachment.AttachSizeKB"));
            String lStrSizeExceed="false";
            int iFileSize=0;
            String lStrDocSize = "";
            String lStrMaxSize="";
            iFileSize = lbBytes.length;
            ArrayList lFileDtlsList = new ArrayList();
                
            if(iFileSize<1024)
            {
                    lStrDocSize = String.valueOf(iFileSize) + "B";
            }
            else if(iFileSize<(1024*1024))
            {
                    lStrDocSize = String.valueOf(iFileSize/(1024))+"KB";
            }
            else if(iFileSize<(1024*1024*1024))
            {
                    lStrDocSize = String.valueOf(iFileSize/(1024*1024))+"MB";
            }           
            if(iFileSize > iConstantSize)
            {
                    lStrSizeExceed = "true";              
            }         
     
            if(iConstantSize <1024)
            {
                    lStrMaxSize = String.valueOf(iConstantSize) + "B";
            }
            else if(iConstantSize<(1024*1024))
            {
                    lStrMaxSize = String.valueOf(iConstantSize/(1024))+"KB";
            }
            else if(iConstantSize<(1024*1024*1024))
            {
                    lStrMaxSize = String.valueOf(iConstantSize/(1024*1024))+"MB";
            }  
      
      
            lFileDtlsList.add(lStrSizeExceed);
            lFileDtlsList.add(lStrDocSize);
            lFileDtlsList.add(lStrMaxSize);
     
            return lFileDtlsList;
    }     
    //end 001
}
