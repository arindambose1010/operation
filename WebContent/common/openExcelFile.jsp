
<%@ page isErrorPage="false" 
                import="java.util.Locale,java.util.ResourceBundle,org.apache.log4j.Logger,
                           java.io.OutputStream, java.io.ByteArrayInputStream, java.io.IOException,
                           org.apache.poi.ss.usermodel.Row,org.apache.poi.ss.usermodel.Sheet,
                            org.apache.poi.xssf.streaming.SXSSFWorkbook,java.lang.reflect.Field,java.util.List,
                            org.apache.poi.ss.usermodel.Cell,org.apache.poi.ss.util.CellReference" %>
<%  
       String lStrFileName=(String)request.getAttribute("FileName");

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
		
		Logger osLogger= Logger.getLogger(this.getClass());
		OutputStream out1 = response.getOutputStream();   
		List headerList=(List)request.getAttribute("headerList");
		List reportList=(List)request.getAttribute("List");
		//HSSFWorkbook wb = new HSSFWorkbook();
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		Field[] fields = reportList.get(0).getClass().getDeclaredFields();
	    Sheet sheet = wb.createSheet("Sheet 1");
	    int rowCount=0,colCount=0;
	    Row row = sheet.createRow(rowCount++);
	    
        for(int col=0;col<fields.length;col++)
        {
    	   fields[col].setAccessible(true);
    	   if(fields[col].get(reportList.get(0))!=null && fields[col].getGenericType().toString().equalsIgnoreCase("class java.lang.String"))
    	   {
    		   row.createCell(colCount++).setCellValue(fields[col].get(headerList.get(0))+"");
    		  
    	   }
        }
		for(int i=1;i<=reportList.size();i++)
		{
			
			if(i%60000==0)
			{
				sheet=wb.createSheet("Sheet "+((i/60000)+1));
				rowCount=0;
				colCount=0;
				row = sheet.createRow(rowCount++);
		        for(int col=0;col<fields.length;col++)
		        {
		    	   fields[col].setAccessible(true);
		    	   if(fields[col].get(reportList.get(0))!=null && fields[col].getGenericType().toString().equalsIgnoreCase("class java.lang.String"))
		    	   {
		    		   Cell cell = row.createCell(colCount++);
		               String address = new CellReference(cell).formatAsString();
		               cell.setCellValue(fields[col].get(headerList.get(0))+"");
		               
		    	   }
		        }
			}
			row = sheet.createRow(rowCount++);
			colCount=0;
			for(int j=0;j<fields.length;j++)
			{
				fields[j].setAccessible(true);
				if(fields[j].get(reportList.get(0))!=null && fields[j].getGenericType().toString().equalsIgnoreCase("class java.lang.String"))
				{
		    		Cell cell = row.createCell(colCount++);
		            String address = new CellReference(cell).formatAsString();
	            	cell.setCellValue(fields[j].get(reportList.get(i-1))+"");
	            	
				}
			}
		}
		wb.write(out1);
		out1.close();
		wb.dispose();
        out.clear(); 
        out = pageContext.pushBody(); 
		System.gc();
%>
