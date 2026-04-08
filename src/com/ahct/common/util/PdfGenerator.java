package com.ahct.common.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.Logger;

import com.ahct.common.vo.LabelBean;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class PdfGenerator {
	static Logger goslogger = Logger.getLogger(PdfGenerator.class);
	
	
	public static byte[] getBytesFromFile(File file)
	        throws IOException
	{
	        FileInputStream fileinputstream = new FileInputStream(file);
	        long l = file.length();
	        if(l <= 0x50fffffffL);
	        byte abyte0[] = new byte[(int)l];
	        int i = 0;
	        for(int j = 0; i < abyte0.length && (j = fileinputstream.read(abyte0, i, abyte0.length - i)) >= 0; i += j);
	        if(i < abyte0.length)
	        {
	            throw new IOException("Could not completely read file " + file.getName());
	        } else
	        {
	            fileinputstream.close();
	            return abyte0;
	        }
	 }
	public static <T> void preparePdf(File lPdffile, List<T> list, String head) throws Exception {
		 	 Document document = new Document(PageSize.LEGAL, 10, 10, 10, 10);
		 	 Field[] fields = list.get(0).getClass().getDeclaredFields();
		     PdfWriter.getInstance(document,new FileOutputStream(lPdffile));
		     document.open();
		   
		          
		     Paragraph heading = new Paragraph(head , FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,0,Color.BLUE));
	         heading.setAlignment(Paragraph.ALIGN_CENTER);
	         heading.setSpacingBefore(15F);
	         heading.setSpacingAfter(10F);
	         document.add(heading);
	         PdfPTable table=new PdfPTable(fields.length);
	         
	        
	         
	         for(int i=0;i<list.size();i++)
	 		 {
	        	 for(int j=0;j<fields.length;j++)
		 		 {
	        		 
	        		 fields[j].setAccessible(true);
	        		 if(fields[j].get(list.get(i)) != null)
	        		 {
	        		 table.addCell(fields[j].get(list.get(i)).toString());
	        		 }
		 		 }
	 		 }
	         document.add(table);		
	 		 document.close(); 
	}
	
	
	
	public static <T> void preparePdfSpecificLength(File lPdffile, List<T> list, String head , int length) throws Exception {
	 	 Document document = new Document(PageSize.LEGAL, 10, 10, 10, 10);
	 	 Field[] fields = list.get(0).getClass().getDeclaredFields();
	     PdfWriter.getInstance(document,new FileOutputStream(lPdffile));
	     document.open();
	    
	     Paragraph heading = new Paragraph(head , FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,0,Color.BLUE));
        heading.setAlignment(Paragraph.ALIGN_CENTER);
        heading.setSpacingBefore(15F);
        heading.setSpacingAfter(10F);
        document.add(heading);
        PdfPTable table=new PdfPTable(length);
       
       
        for(int i=0;i<list.size();i++)
		 {
       	 for(int j=0;j<fields.length;j++)
	 		 { 
       		 
       		 fields[j].setAccessible(true);
       		 if(fields[j].get(list.get(i)) != null)
       		 {
       		 table.addCell(fields[j].get(list.get(i)).toString());
       		 }
       		
		 }
		 }
        document.add(table);		
		 document.close(); 
}
	
	
	
	
	public static <T> void preparePdfRTIreport(File lPdffile, List<T> list, String head , int length) throws Exception {
	 	 Document document = new Document(PageSize.LEGAL, 10, 10, 10, 10);
	 	 Field[] fields = list.get(0).getClass().getDeclaredFields();
	     PdfWriter.getInstance(document,new FileOutputStream(lPdffile));
	     document.open();
	    
	     Paragraph heading = new Paragraph(head , FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,0,Color.BLUE));
       heading.setAlignment(Paragraph.ALIGN_CENTER);
       heading.setSpacingBefore(15F);
       heading.setSpacingAfter(10F);
       document.add(heading);
       PdfPTable table=new PdfPTable(length);
       String col="RequestId~name~emailId~mobileno~Reply";
       String headValues[] = col.split("~");
       
      
       
       if(headValues!=null && headValues.length>0)
       {
    	 for(int headSize=0;headSize<headValues.length;headSize++)
    		 table.addCell(headValues[headSize]);
	 
       }
       for(int i=0;i<list.size();i++)
		 {
    	   
      	 for(int j=0;j<fields.length;j++)
	 		 { 
      		 
      		 fields[j].setAccessible(true);
      		 if(fields[j].get(list.get(i)) != null)
      		 {
      		 table.addCell(fields[j].get(list.get(i)).toString());
      		 }
      		
		 }
		 }
       document.add(table);		
		 document.close(); 
}
	
	
	}

