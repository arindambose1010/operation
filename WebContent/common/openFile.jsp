
<%@ page isErrorPage="false" 
                import="java.util.Locale,java.util.ResourceBundle,org.apache.log4j.Logger,
                           java.io.OutputStream, java.io.ByteArrayInputStream, java.io.IOException" %>
<%  
          

         Logger osLogger= Logger.getLogger(this.getClass());
         OutputStream out1 = response.getOutputStream();   
         byte[] allBytesInBlob = (byte[])request.getAttribute("File");
         if(allBytesInBlob==null){
         allBytesInBlob = (byte[])session.getAttribute("File");
         }
     // 0001 - Begin  
         if(allBytesInBlob!=null)
         {
            ByteArrayInputStream bain= new ByteArrayInputStream(allBytesInBlob);
            int numRead       = 0;
            int bufferSize    = 30 * 1024 *1024;
            byte[] readBuffer = new byte[bufferSize];
            while ((numRead = bain.read(readBuffer, 0, bufferSize)) != -1) 
            {
                 out1.write(readBuffer, 0, numRead);
                 out1.flush();
            }
            out1.close();
	        out.clear(); 
            out = pageContext.pushBody(); 
        } //0001- End
        
        else
        {%>
    <script>
       alert("No Records Found");
    </script>
    <%}%>
