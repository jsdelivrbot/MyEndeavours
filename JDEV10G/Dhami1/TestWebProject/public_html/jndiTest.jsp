<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="oracle.tip.adapter.ftp.*"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>jndiTest</title>
  </head>
  <body>
  This JNDI Test: See the OPMN log
  <%
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
  
  %>
  </body>
</html>