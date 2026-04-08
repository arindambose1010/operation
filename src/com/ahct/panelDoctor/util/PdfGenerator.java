package com.ahct.panelDoctor.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.ahct.panelDoctor.vo.PanelDocPayVO;


import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import java.util.List;
import org.apache.log4j.Logger;

public class PdfGenerator
{
  static Logger goslogger = Logger.getLogger(PdfGenerator.class);

  

  public static byte[] getBytesFromFile(File file)
    throws IOException
  {
    FileInputStream fileinputstream = new FileInputStream(file);
    long l = file.length();
    if (l <= 21743271935L);
    byte[] abyte0 = new byte[(int)l];
    int i = 0;
    for (int j = 0; i < abyte0.length; i += j) if ((j = fileinputstream.read(abyte0, i, abyte0.length - i)) < 0) break;
    if (i < abyte0.length)
    {
      throw new IOException("Could not completely read file " + file.getName());
    }

    fileinputstream.close();
    return abyte0;
  }

 
private static PdfPCell rightAlignPdfCell(String str)
{
	PdfPCell cell = new PdfPCell(new Paragraph(str));
	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);	
	return cell;
	
}
private static PdfPCell setBackGroundColor(String str)
{
	PdfPCell cell = new PdfPCell(new Paragraph(str));
	cell.setBackgroundColor(Color.GRAY);
	return cell;
}

public static void generateGeneralLedgerPdf(File lPdffile,
		List<PanelDocPayVO> panelDocCasesList, String imagePath) throws DocumentException, MalformedURLException, IOException {
	Document document = new Document(PageSize.LEGAL, 10.0F, 10.0F, 10.0F, 10.0F);

    PdfWriter.getInstance(document, new FileOutputStream(lPdffile));
    document.open();
    
    Font mainHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD);
    Image image = Image.getInstance(imagePath); 
    image.scaleAbsolute(600, 50); 
    image.setAlignment(Image.MIDDLE); 
    document.add(image);

    Paragraph heading = new Paragraph("Panel Doctor Cases" , FontFactory.getFont("Helvetica-Bold", 12.0F, 0, Color.BLUE));
    heading.setAlignment(1);
    heading.setSpacingBefore(15.0F);
    heading.setSpacingAfter(10.0F);
    document.add(heading);

    PdfPTable table = new PdfPTable(4);

    table.addCell(setBackGroundColor("DATE"));
    table.addCell(setBackGroundColor("CASE ID"));
    table.addCell(setBackGroundColor("HOSPITAL NAME"));
    table.addCell(setBackGroundColor("CASE STATUS"));
    for(PanelDocPayVO panelDocPayVO : panelDocCasesList)
    {

      table.addCell(panelDocPayVO.getCASE_DATE());
      table.addCell(panelDocPayVO.getCASE_ID());
      table.addCell(panelDocPayVO.getHOSP_NAME());
      table.addCell(panelDocPayVO.getPARTICULARS());
      
    }

   
    document.add(table);
    document.close();
	
}
}