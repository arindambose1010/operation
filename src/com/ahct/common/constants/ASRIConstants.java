package com.ahct.common.constants;

import java.util.HashMap;
import java.util.Map;

public class ASRIConstants {
	public static final String SLASH_VALUE="/" ;
	public static final String STORAGE_BOX="/storageNAS3-Production/";
	public static final String STORAGE_BOX_CASES=STORAGE_BOX+"Phase";
	public static final String TESTS_ATTACH="TestsAttachments";
	public static final String LANG_EN_US = "en_US" ;
	public static final String EMPTY_STRING = "" ;
	public static final String ACTION_VAL="actionVal";
	public static final String USER_ROLE ="UserRole";
	public static final String FLAG_Y ="Y";
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

}
