package com.ahct.patient.constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

public class FileConstants {

	  public static final String EMPTY_STRING = "" ;
	  public static final String FILE_EXT_JPG="jpg";
	  public static final String FILE_EXT_JPEG="jpeg";
	  public static final String FILE_EXT_GIF="gif";
	  public static  Map<String,String> FILE_EXT=new HashMap<String,String>();
	  static{
	          FILE_EXT.put("doc","application/msword");
	          FILE_EXT.put("rtf","application/msword");
	          FILE_EXT.put("xls","application/vnd.ms-excel");
	          FILE_EXT.put("ppt","application/vnd.ms-powerpoint");
	          FILE_EXT.put("pdf","application/pdf");
	          FILE_EXT.put("txt","text/plain");
	          FILE_EXT.put("sxw","application/vnd.sun.xml.writer");
	          FILE_EXT.put("stw","application/vnd.sun.xml.writer.template");
	          FILE_EXT.put("sxd","application/vnd.sun.xml.draw");
	          FILE_EXT.put("std","application/vnd.sun.xml.draw.template");
	          FILE_EXT.put("sxc","application/vnd.sun.xml.calc");
	          FILE_EXT.put("sxi","application/vnd.sun.xml.impress");
	          FILE_EXT.put("zip","application/x-zip-compressed");
	          FILE_EXT.put("html","message/rfc822");
	          FILE_EXT.put("text/html","message/rfc822");
	          FILE_EXT.put("htm","message/rfc822");
	          FILE_EXT.put("mht","message/rfc822");
	          FILE_EXT.put("wmv","application/octet-stream");
	          FILE_EXT.put("video/x-ms-wmv","application/octet-stream");
	  		FILE_EXT.put("jpg","image/jpg");
	        FILE_EXT.put("jpeg","image/jpeg");
	        FILE_EXT.put("png","image/png");
	        FILE_EXT.put("gif","image/gif");
	        FILE_EXT.put("bmp","image/bmp");
	        FILE_EXT.put("JPG","image/jpg");
	        FILE_EXT.put("JPEG","image/jpeg");
	        FILE_EXT.put("PNG","image/png");
	        FILE_EXT.put("GIF","image/gif");
	        FILE_EXT.put("BMP","image/bmp");
			FILE_EXT.put("PDF","application/pdf");
	        }
	  
	  public static String convertPDFToBase64(String filePath) throws IOException {
		    File file = new File(filePath);
	        FileInputStream fileInputStream = new FileInputStream(file);
	        byte[] pdfBytes = new byte[(int) file.length()];
	        fileInputStream.read(pdfBytes);
	        fileInputStream.close();

	        return DatatypeConverter.printBase64Binary(pdfBytes);
	    }
	  
	  public static String convertImageToBase64(String imagePath) {
	        File file = new File(imagePath);
	        if (!file.exists()) {
	            throw new IllegalArgumentException("File not found: " + imagePath);
	        }
	 
	        try {
	            Path path = Paths.get(imagePath);
	            byte[] imageBytes = Files.readAllBytes(path);
	            return DatatypeConverter.printBase64Binary(imageBytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
