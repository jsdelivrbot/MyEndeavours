package com.emerson.bpel.ftp;

import java.io.BufferedInputStream;

import java.io.FileOutputStream;

import java.net.URL;

import java.net.URLConnection;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.InitialContext;

import oracle.tip.adapter.ftp.*;
//import com.evermind.server.ApplicationClientInitialContextFactory;
//import oracle.j2ee.rmi.RMIInitialContextFactory;
//import com.evermind.server.rmi.RMIInitialContextFactory

//import javax.resource.cci.InteractionSpec;

//import oracle.tip.adapter.ftp.outbound.FTPInteraction;

public class ReadFTP_JB {
    public ReadFTP_JB() {
    }
    
    public static void readFTP(String host, String filename){
        long start, stop;
        start = Calendar.getInstance().getTimeInMillis();
        System.out.println("[PollFTP_ClosetMaid_JB] Host got is = "+host);
        System.out.println("[PollFTP_ClosetMaid_JB] Filename got is = "+filename);
        System.out.println("[PollFTP_ClosetMaid_JB] Start Time - "+start);
        try {
            FTPConnectionFactory ftpConF = null;  
            FTPConnection ftpConn = null;
            FTPManagedConnectionFactory ftpMCF = null;
            FTPAdapterMetaData ftpAMD = null;
            //FTPInteraction ftpInteraction = null;
            //InteractionSpec interactionSpec = null;
            
            
            java.util.Hashtable env = new java.util.Hashtable();
                        
            env.put("java.naming.factory.initial","oracle.j2ee.rmi.RMIInitialContextFactory");
            env.put("java.naming.provider.url","opmn:ormi://inedec-mlap-45:6100:oc4j_soa");
            env.put("java.naming.security.principal","oc4jadmin");
            env.put("java.naming.security.credentials","welcome1");
            InitialContext ctx = new InitialContext(env);
            Hashtable env1 = ctx.getEnvironment();
            System.out.println("Environment Hashtable updated - "+env1);
            
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
            
//            ftpInteraction = (FTPInteraction)ftpConn.createInteraction();
//            interactionSpec.
//            ftpInteraction.execute()
                URL url = new URL("ftp://"+ftpMCF.getUsername()+":"+ftpMCF.getPassword()+"@ftp01.b2netsolutions.com/VSP/"+filename+";type=i");
                URLConnection con = url.openConnection();
                BufferedInputStream in = new BufferedInputStream(con.getInputStream());
                FileOutputStream out = new FileOutputStream("C:\\my_"+filename);

                int i = 0,j=0;
                byte[] bytesIn = new byte[1024];
                while ((i = in.read(bytesIn)) >= 0) {
                        //System.out.println("This is "+(++j)+" - i = "+i);
                        out.write(bytesIn, 0, i);
                }
                out.close();
                in.close();


        } catch (Exception e) {
           e.printStackTrace();
        }  
        stop = Calendar.getInstance().getTimeInMillis();
        System.out.println("[PollFTP_ClosetMaid_JB] Stop Time - "+stop);
        System.out.println("[PollFTP_ClosetMaid_JB] Total time taken is - "+((stop-start)/1000)+" secs");
    }
    
    public static void main(String[] args){
        readFTP("","");
    }
}
