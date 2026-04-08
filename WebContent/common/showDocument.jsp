<%--
/*
 * FILENAME     : Document.jsp 
 * VERSION      : 1.0
 * AUTHOR       : Murthy K V V S
 * DATE         :
 * REV. HISTORY :
 *   ---------------------------------------------------------------------------
 *          Date                   Author          Change Desciption
 *   ---------------------------------------------------------------------------
 *    14th Dec 2005         Devendra       Modified  page directive
 *    26th May 2006         LakshmiNarayana (0001) Added Code For BlobSeparation 
 *   ---------------------------------------------------------------------------
 *
 * THIS JSP OPENS THE DOCUMENT 
 */
--%>
<%@ page isErrorPage="false" 
                import="java.util.Locale,java.util.ResourceBundle,org.apache.log4j.Logger,
                           java.io.OutputStream, java.io.ByteArrayInputStream, java.io.IOException" %>
<%  
         Locale myLocale=null;
         if (session.getAttribute("LocaleID") != null && !(session.getAttribute("LocaleID").equals("null")) )
            myLocale = (Locale) session.getAttribute("LocaleID") ;
         else
            myLocale = request.getLocale();

         ResourceBundle localStringsBundle = ResourceBundle.getBundle("ApplicationResources", myLocale); 
         String lStrContentType = (String)request.getAttribute("ContentType");
         if(lStrContentType==null){
          lStrContentType = (String)session.getAttribute("ContentType");
        }
         response.setContentType(lStrContentType);
        String lStrFileName = (String)request.getAttribute("FileName");
        if(lStrFileName==null){
          lStrFileName = (String)session.getAttribute("FileName");
        }
        
         String lStrFileExtn = (String)request.getAttribute("Extn");
         if(lStrFileExtn==null){
          lStrFileExtn = (String)session.getAttribute("Extn");
        }
         lStrFileExtn = (lStrFileExtn==null)?"":lStrFileExtn;
         if(!lStrFileExtn.equals(""))
         {
            response.setHeader("Content-Disposition","attachment; filename="+lStrFileName+"."+lStrFileExtn);
         }

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
            bufferSize    = 30 * 24 *24;
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
        window.parent.self.focus();
        window.resizeTo(10,10);
        window.moveTo(420,345);
        alert('<%=localStringsBundle.getString("NoAttachMsg")%>');
        window.close();
    </script>
    <%}%>
