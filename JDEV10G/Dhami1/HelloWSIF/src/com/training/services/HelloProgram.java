package com.training.services;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;

import java.lang.reflect.Method;

import java.net.URL;
import java.net.URLConnection;

import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.rmi.PortableRemoteObject;

//import oracle.tip.adapter.ftp.FTPAdapterMetaData;
//import oracle.tip.adapter.ftp.FTPConnection;
//import oracle.tip.adapter.ftp.FTPConnectionFactory;
//import oracle.tip.adapter.ftp.FTPManagedConnectionFactory;
//import oracle.tip.adapter.api.OracleConnectionFactory;
//import oracle.tip.adapter.jms.JmsConnectionFactory;

//import com.evermind.server.ApplicationClientInitialContextFactory;
//import oracle.j2ee.rmi.RMIInitialContextFactory;
//import com.evermind.server.rmi.RMIInitialContextFactory

public class HelloProgram
{
  public HelloProgram()
  {
  }

  public String hello(String title, String name)
  {
    return "Hello " + title + " " + name + ":" + 
      setHostUidPwdUsingJndi("eis/FTP/InternalFTP");//eis/FTP/Liebert_Vendavo  eis/aqjms/Topic
  }
  
  public static void main(String[] args){
      HelloProgram hp = new HelloProgram();
      hp.setHostUidPwdUsingJndi("java:comp/env/eis/FTP/InternalFTP");
  }
  
//  private String setHostUidPwdUsingJndi2(String jndi){
//      long start, stop;
//      start = Calendar.getInstance().getTimeInMillis();
//      System.out.println("[PollFTP_ClosetMaid_JB] Start Time - "+start);
//      try {
//          JmsConnectionFactory jmsConF = null;
//          FTPConnectionFactory ftpConF = null;  
//          FTPConnection ftpConn = null;
//          FTPManagedConnectionFactory ftpMCF = null;
//          FTPAdapterMetaData ftpAMD = null;
//          OracleConnectionFactory oCF = null;
//          //FTPInteraction ftpInteraction = null;
//          //InteractionSpec interactionSpec = null;
//          
//          
//          java.util.Hashtable env = new java.util.Hashtable();
//          //Thread.currentThread().getContextClassLoader()
//          env.put("java.naming.factory.initial","com.evermind.server.rmi.RMIInitialContextFactory");
//          env.put("java.naming.provider.url","opmn:ormi://10.129.160.118:6003:oc4j_soa");
//          env.put("java.naming.security.principal","oc4jadmin");
//          env.put("java.naming.security.credentials","welcome1");
//          InitialContext ctx = new InitialContext();
//          Object obj = ctx.lookup(jndi);
//          System.out.println("Got Object1234 from JNDI is: "+obj+" ClassLoader - "+obj.getClass().getClassLoader()+" System CL - "+obj.getClass().getClassLoader().getSystemClassLoader());
//          //ftpConF = (FTPConnectionFactory)PortableRemoteObject.narrow(ctx.lookup(jndi), FTPConnectionFactory.class);
//          //System.out.println("FTPConnectionFactory ClassLoader - "+Thread.currentThread().getContextClassLoader().loadClass("oracle.tip.adapter.ftp.FTPConnectionFactory"));
//          //Class ftpConnFact = Thread.currentThread().getContextClassLoader().loadClass("oracle.tip.adapter.ftp.FTPConnectionFactory");
//          //oCF = (OracleConnectionFactory )obj;
//          //ftpConnFact = oCF.getClass()
//          
//          /****** Comparing the Class References ******/
////          Class c1 = obj.getClass().getClassLoader().loadClass("oracle.tip.adapter.ftp.FTPConnectionFactory");
////          Class c2 = Thread.currentThread().getContextClassLoader().loadClass("oracle.tip.adapter.ftp.FTPConnectionFactory");
////          if(c1==c2){
////              System.out.println("They are Same...  "+c1+"     "+c2);
////          }
////          else{
////              System.out.println("They are different - "+c1+"    "+c2);
////          }
//          /*********************************************/
//          
//           ClassLoader expected = FTPConnectionFactory.class.getClassLoader();
//           System.out.println("Expected Sirji: " + expected+"   "+expected.getParent()+"    "+expected.getParent().getParent());
//           ClassLoader actual = obj.getClass().getClassLoader();
//           System.out.println("  Actual: " + actual);
//          
//         // Class.forName("oracle.tip.adapter.ftp.FTPConnectionFactory",true,obj.getClass().getClassLoader());
//            
//          ftpConF = (oracle.tip.adapter.ftp.FTPConnectionFactory)obj;
////          jmsConF = (oracle.tip.adapter.jms.JmsConnectionFactory)obj;
////          
////          System.out.println("Jms Conf got  - "+ jmsConF);
//          
//          
//          System.out.println(ftpConF.getApplicationName());
//          System.out.println(ftpConF.getDescription());
//          System.out.println(ftpConF.getResourceAdapterClassName());
//          System.out.println(ftpConF.toString());
//          System.out.println(ftpConF.getConnection());
//          System.out.println(ftpConF.getManagedConnectionFactory());
//          System.out.println(ftpConF.getMetaData());
//          System.out.println(ftpConF.getRecordFactory());
//          ftpConn = (FTPConnection)ftpConF.getConnection();
//          ftpMCF = (FTPManagedConnectionFactory)ftpConF.getManagedConnectionFactory();
//          ftpAMD = (FTPAdapterMetaData)ftpConF.getMetaData();
//          System.out.println("Details: ");
//          System.out.println("Interaction - "+ftpConn.createInteraction());
//          System.out.println("ConnectionMetadata - "+ftpConn.getMetaData());
//          System.out.println("FTPMannagedConnection - "+ftpConn.getManagedConnection());
//          System.out.println("Name - "+ftpConn.getName());
//          
//          System.out.println("Host - "+ftpMCF.getHost());
//          System.out.println("UserAgent - "+ftpMCF.getHttpUserAgent());
//          System.out.println("Password - "+ftpMCF.getPassword());
//          System.out.println("Username - "+ftpMCF.getUsername());
//          System.out.println("Path Separater - "+ftpMCF.getFtpPathSeparator());
//          System.out.println("AdapterVendor - "+ftpAMD.getAdapterVendorName());
//
//
//      } catch (Exception e) {
//         e.printStackTrace();
//      }  
//      stop = Calendar.getInstance().getTimeInMillis();
//      System.out.println("[PollFTP_ClosetMaid_JB] Stop Time - "+stop);
//      System.out.println("[PollFTP_ClosetMaid_JB] Total time taken is - "+((stop-start)/1000)+" secs");
//      return "DONE";
//  }

  private String setHostUidPwdUsingJndi(String jndi)
  {
    StringBuilder log = new StringBuilder();
    InitialContext ctx = null;

    try
    {
      ctx = new InitialContext();
      System.out.println("env=" + ctx.getEnvironment());

      Object jndiFtpObj = ctx.lookup(jndi);
        
        
        Class fTPConnectionFactoryClass = Class.forName("oracle.tip.adapter.ftp.FTPConnectionFactory",true,jndiFtpObj.getClass().getClassLoader());
        Method getManagedConnectionFactory = fTPConnectionFactoryClass.getMethod("getManagedConnectionFactory",(Class[])null);
        
        Object fTPManagedConnectionFactoryObject = getManagedConnectionFactory.invoke(jndiFtpObj,(Object[])null);
        
        Class fTPManagedConnectionFactoryClass = fTPManagedConnectionFactoryObject.getClass();
        Method getHost = fTPManagedConnectionFactoryClass.getMethod("getHost",(Class[])null);
        Method getUsername = fTPManagedConnectionFactoryClass.getMethod("getUsername",(Class[])null);
        Method getPassword = fTPManagedConnectionFactoryClass.getMethod("getPassword",(Class[])null);

        String host = (String)getHost.invoke(fTPManagedConnectionFactoryObject,(Object[])null);
        String userName = (String)getUsername.invoke(fTPManagedConnectionFactoryObject,(Object[])null);
        String password = (String)getPassword.invoke(fTPManagedConnectionFactoryObject,(Object[])null);
        
        System.out.println("Got FTP Credentials are - Host - "+host+" | UserName - "+userName+" | Password - "+password);
        
//
//      System.out.println("***Got Object from JNDI is: " + jndiFtpObj);
//      log.append("||Got Object from JNDI is: " + jndiFtpObj);
//      
//      
//      System.out.println("class of obj is=" + jndiFtpObj.getClass());
//      log.append("||class of obj is=" + jndiFtpObj.getClass());
//      
//      
//      System.out.println("simple name of obj is=" + obj.getClass().getSimpleName());
//      log.append("||simple name of obj is=" + obj.getClass().getSimpleName());
//      
//      System.out.println("Methods in this class:");
//      Method m[] =obj.getClass().getMethods();
//      for(int i=0;i<m.length;i++)
//      {
//        System.out.println(m[i].getName());
//      }     
//      
//
//      System.out.println("Trying to cast to FTPConnectionFactory");
//      log.append("||Trying to cast to FTPConnectionFactory");
//      
//      
//      ftpConF = (FTPConnectionFactory) obj;
//      ftpMCF = (FTPManagedConnectionFactory)ftpConF.getManagedConnectionFactory();
//      System.out.println("Host = " + ftpMCF.getHost());
//      System.out.println("user = " + ftpMCF.getUsername());
//      System.out.println("pwd = " + ftpMCF.getPassword());
//
//      log.append("||"+ftpMCF.getHost() + ":" + ftpMCF.getUsername());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      log.append(e.getMessage());
    }

    return log.toString();
  }


}
