
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Calendar;
import javax.naming.InitialContext;
import oracle.tip.adapter.ftp.*;


public class _jndiTest extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _jndiTest page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      out.write(__oracle_jsp_text[3]);
      out.write(__oracle_jsp_text[4]);
      
                long start, stop;
              start = Calendar.getInstance().getTimeInMillis();
              System.out.println("[PollFTP_ClosetMaid_JB] Start Time - "+start);
              try {
                  FTPConnectionFactory ftpConF = null;
                  FTPConnection ftpConn = null;
                  FTPManagedConnectionFactory ftpMCF = null;
                  FTPAdapterMetaData ftpAMD = null;
                  java.util.Hashtable env = new java.util.Hashtable();
                              
                  env.put("java.naming.factory.initial","com.evermind.server.rmi.RMIInitialContextFactory");
                  env.put("java.naming.provider.url","opmn:ormi://inedec-mlap-45:6003:oc4j_soa");
                  env.put("java.naming.security.principal","oc4jadmin");
                  env.put("java.naming.security.credentials","welcome1");
                  InitialContext ctx = new InitialContext();
                  Object obj = ctx.lookup("eis/FTP/ClosetMaidTest");
                  System.out.println("Got Object from JNDI is: "+obj);
                  ftpConF = (FTPConnectionFactory)obj;
                  System.out.println(ftpConF.getApplicationName());
                  System.out.println(ftpConF.getDescription());
                  System.out.println(ftpConF.getResourceAdapterClassName());
                  System.out.println(ftpConF.toString());
                  System.out.println(ftpConF.getConnection());
                  System.out.println(ftpConF.getManagedConnectionFactory());
                  System.out.println(ftpConF.getMetaData());
                  System.out.println(ftpConF.getRecordFactory());
                  
                  
                  ftpConn = (FTPConnection)ftpConF.getConnection();
                  ftpMCF = (FTPManagedConnectionFactory)ftpConF.getManagedConnectionFactory();
                  ftpAMD = (FTPAdapterMetaData)ftpConF.getMetaData();
                  System.out.println("Details: ");
                  System.out.println("Interaction - "+ftpConn.createInteraction());
                  System.out.println("ConnectionMetadata - "+ftpConn.getMetaData());
                  System.out.println("FTPMannagedConnection - "+ftpConn.getManagedConnection());
                  System.out.println("Name - "+ftpConn.getName());
                  
                  System.out.println("Host - "+ftpMCF.getHost());
                  System.out.println("UserAgent - "+ftpMCF.getHttpUserAgent());
                  System.out.println("Password - "+ftpMCF.getPassword());
                  System.out.println("Username - "+ftpMCF.getUsername());
                  System.out.println("Path Separater - "+ftpMCF.getFtpPathSeparator());
                  System.out.println("AdapterVendor - "+ftpAMD.getAdapterVendorName());
      
              } catch (Exception e) {
                 e.printStackTrace();
              }  
              stop = Calendar.getInstance().getTimeInMillis();
              System.out.println("[PollFTP_ClosetMaid_JB] Stop Time - "+stop);
              System.out.println("[PollFTP_ClosetMaid_JB] Total time taken is - "+((stop-start)/1000)+" secs");
        
        
      out.write(__oracle_jsp_text[5]);

    }
    catch (Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch (Exception clearException) {
        }
        pageContext.handlePageException(e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext, true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[6][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\n".toCharArray();
    __oracle_jsp_text[3] = 
    "\n".toCharArray();
    __oracle_jsp_text[4] = 
    "\n<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\n    <title>jndiTest</title>\n  </head>\n  <body>\n  This JNDI Test: See the OPMN log\n  ".toCharArray();
    __oracle_jsp_text[5] = 
    "\n  </body>\n</html>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
