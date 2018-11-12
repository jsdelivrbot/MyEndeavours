package com.emerson.servlet.jndi;


import java.io.IOException;
import java.io.PrintWriter;


import java.util.Calendar;
import java.util.Hashtable;

import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.http.*;

import oracle.tip.adapter.ftp.FTPAdapterMetaData;
import oracle.tip.adapter.ftp.FTPConnection;
import oracle.tip.adapter.ftp.FTPConnectionFactory;
import oracle.tip.adapter.ftp.FTPManagedConnectionFactory;

public class JNDITest extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        String result = readFTP("eis/FTP/ClosetMaidTest");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>JNDITest</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.\n "+result+" </p>");
        out.println("</body></html>");
        out.close();
    }
    
    
    private String readFTP(String jNDI){
        long start, stop;
        start = Calendar.getInstance().getTimeInMillis();
        System.out.println("[PollFTP_ClosetMaid_JB] JNDI to be searched is = "+jNDI);
        System.out.println("[PollFTP_ClosetMaid_JB] Start Time - "+start);
        try {
            FTPConnectionFactory ftpConF = null;  
            FTPConnection ftpConn = null;
            FTPManagedConnectionFactory ftpMCF = null;
            FTPAdapterMetaData ftpAMD = null;
            //FTPInteraction ftpInteraction = null;
            //InteractionSpec interactionSpec = null;
            
            
            java.util.Hashtable env = new java.util.Hashtable();
                        
            env.put("java.naming.factory.initial","com.evermind.server.rmi.RMIInitialContextFactory");
            env.put("java.naming.provider.url","opmn:ormi://inedec-mlap-45:6003:oc4j_soa");
            env.put("java.naming.security.principal","oc4jadmin");
            env.put("java.naming.security.credentials","welcome1");
            InitialContext ctx = new InitialContext();
            Hashtable env1 = ctx.getEnvironment();
            System.out.println("Environment Hashtable updated - "+env1);
            
            Object obj = ctx.lookup(jNDI);
            System.out.println("Got Object from JNDI is: "+obj+" Classloader - "+obj.getClass().getClassLoader());
            
            ClassLoader expected = FTPConnectionFactory.class.getClassLoader();
            System.out.println("Expected: " + expected);
            ClassLoader actual = obj.getClass().getClassLoader();
            System.out.println("  Actual: " + actual);
            
            ftpConF = (FTPConnectionFactory)obj;
            System.out.println("Local Classloader - "+ftpConF.getClass().getClassLoader());
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
           return (e.getMessage());
        }  
        stop = Calendar.getInstance().getTimeInMillis();
        System.out.println("[PollFTP_ClosetMaid_JB] Stop Time - "+stop);
        System.out.println("[PollFTP_ClosetMaid_JB] Total time taken is - "+((stop-start)/1000)+" secs");
        return "DONE";
    }
}
