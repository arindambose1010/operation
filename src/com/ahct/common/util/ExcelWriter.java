
package com.ahct.common.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelWriter 
{
    static Logger goslogger = Logger.getLogger(ExcelWriter.class);
    public  byte[] getBytesFromFileExcel(File file)
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
	public static <T> void prepareExl(File xlFile,List<T> reportList) throws Exception
	{
		Field[] fields = reportList.get(0).getClass().getDeclaredFields();
		WritableWorkbook writableWorkbook = Workbook.createWorkbook(xlFile);
		
		WritableFont lLblFont = new WritableFont(WritableFont.ARIAL,
                  WritableFont.DEFAULT_POINT_SIZE,
                  WritableFont.BOLD,
                  false,
                  UnderlineStyle.NO_UNDERLINE,
                  Colour.DARK_BLUE);
		WritableFont ldataFont = new WritableFont(WritableFont.ARIAL);
		
		WritableSheet writableSheet = writableWorkbook.createSheet("Sheet1", 0);
		WritableCellFormat lLblFmt = new WritableCellFormat(lLblFont);
		WritableCellFormat ldataFmt = new WritableCellFormat(ldataFont);
		
		// sets background of Label cell
	       lLblFmt.setBackground(Colour.GRAY_25);
	       lLblFmt.setWrap(true);
	       lLblFmt.setBorder(Border.ALL,BorderLineStyle.THIN);
	       lLblFmt.setAlignment(Alignment.GENERAL);
	       lLblFmt.setVerticalAlignment(VerticalAlignment.TOP);
	     //setting the format for data cells
	       ldataFmt.setBorder(Border.ALL,BorderLineStyle.THIN);
	       ldataFmt.setWrap(true);
	       ldataFmt.setAlignment(Alignment.GENERAL);
	       ldataFmt.setVerticalAlignment(VerticalAlignment.TOP);
	       ldataFmt.setWrap(true);
	       
	       //setting the Headers (First Row of the List Should Be headers)
	       for(int i=0;i<fields.length;i++)
	       {
	    	   fields[i].setAccessible(true);
	    	   if(fields[i].get(reportList.get(0)) != null)
	    	   writableSheet.addCell(new Label(i,0,fields[i].get(reportList.get(0)).toString(),lLblFmt));
	       }

			Label[] label = new Label[fields.length];
			
			for(int i=1;i<reportList.size();i++)
			{
				
				for(int j=0;j<fields.length;j++)
				{
					fields[j].setAccessible(true);
					 if(fields[j].get(reportList.get(i)) != null)
					 {
					label[j] =new Label(j,i,fields[j].get(reportList.get(i)).toString(),ldataFmt);
					writableSheet.addCell(label[j]);
					
					 }
				}
			}
			//Write and close the workbook
			writableWorkbook.write();
			writableWorkbook.close(); 
	}
}


