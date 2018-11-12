package com.emerson.common.services;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.net.SocketException;

import java.util.Calendar;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;

public class FTPService {
    public FTPService() {}
    
    /**
     * This method is used to connect to an FTP site and return the FTPClient object back. 
     * @param hostname
     * @param username
     * @param password
     * @return <code>org.apache.commons.net.ftp.FTPClient</code>
     */
    private FTPClient connect(String hostname, String username, String password) {
        FTPClient client = null;

        try {
            client = new FTPClient();
            client.connect(hostname);
            client.login(username, password);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return client;
    }
    
    /**
     * This method is used to extract the hostname, username and password from a 
     * FTP RA object fetched by the JNDI.
     * @param ftpRACFJndi
     * @return <code>String[]</code> containing hostname, username and password in the array of 3.
     */
    private String[] extractFTPCredentialsFromJNDI(String ftpRACFJndi) {
        InitialContext ctx = null;
        String[] credentials = null;
        Object jndiFtpObj;
        
        Class fTPConnectionFactoryClass;
        Method getManagedConnectionFactory;
        try {
            ctx = new InitialContext();
            jndiFtpObj = ctx.lookup(ftpRACFJndi);
            fTPConnectionFactoryClass = Class.forName("oracle.tip.adapter.ftp.FTPConnectionFactory", true, jndiFtpObj.getClass().getClassLoader());
            getManagedConnectionFactory = fTPConnectionFactoryClass.getMethod("getManagedConnectionFactory", (Class[])null);
            Object fTPManagedConnectionFactoryObject;
            fTPManagedConnectionFactoryObject = getManagedConnectionFactory.invoke(jndiFtpObj, (Object[])null);
            Class fTPManagedConnectionFactoryClass = fTPManagedConnectionFactoryObject.getClass();
            Method getHost = fTPManagedConnectionFactoryClass.getMethod("getHost", (Class[])null);
            Method getUsername = fTPManagedConnectionFactoryClass.getMethod("getUsername", (Class[])null);
            Method getPassword = fTPManagedConnectionFactoryClass.getMethod("getPassword", (Class[])null);

            credentials = new String[3];
            credentials[0] = (String)getHost.invoke(fTPManagedConnectionFactoryObject, (Object[])null);
            credentials[1] = (String)getUsername.invoke(fTPManagedConnectionFactoryObject, (Object[])null);
            credentials[2] = (String)getPassword.invoke(fTPManagedConnectionFactoryObject, (Object[])null);
        }catch (NamingException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return credentials;
    }
    
    /**
     * 
     * @param ftpRACFJndi - Format is eis/FTP/MYFTP, not java:comp/env/eis/FTP/MYFTP
     * @param directory  - Directory for which file listing is to be returned
     * @param mode - how the directory listing should be returned.  
     *               SIMPLE= only files, 
     *               COMPLETE = complete file info
     * @return string that represents the file list for a given directory
     */
    public String getFTPDirectoryListing(String ftpRACFJndi, String directory, String mode, String hostname, String username, String password) {
        FTPClient client = null;
        String[] ftpCredentials = null;
        System.out.println("*** IN FtpDirGetterProcess.getDirectoryListing() *** ");
        String dirListing = "";
        //Set default values...useful during testing.
        if (ftpRACFJndi == null || 
            ftpRACFJndi.trim().equals("")) { //Use the default JNDI when even host name passed in is empty.
            if (hostname == null || hostname.trim().equals("")) {
                dirListing = "You cannot pass both JNDI and hostname as null";
            }
        }
        if (mode == null || mode.trim().equals("")){
            mode = "SIMPLE";
        }
           
        //We try to get the host, uid and pwd information from the JNDI, 
        //instead of using the values passed.
        //If this fails, we will use the values passed in.
        try {
            //comment this when running independently.  
            //This is because in standalone, JNDI will not work
            if (!(ftpRACFJndi == null || ftpRACFJndi.trim().equals(""))) {
                ftpCredentials = extractFTPCredentialsFromJNDI(ftpRACFJndi);
                hostname = ftpCredentials[0];
                username = ftpCredentials[1];
                password = ftpCredentials[2];
            }
            
            System.out.println("Got credentials.  Now trying to connect to " + hostname + "(user = " + username + ")");
            client = connect(hostname, username, password);
            if (mode.equals("SIMPLE")) {
                // Obtain a list of filenames in the current working directory.
                System.out.println("Retrieving directory listing");
                String separator = ";";
                try {
                    client.changeWorkingDirectory(directory);
                    String[] names = client.listNames();

                    for (String name: names) {
                        dirListing = dirListing+name+separator;
                    }
                } catch (Exception e) {
                    System.out.println("*** Following exception occured while using listNames. ");
                    System.out.println(e.getMessage());
                    System.out.println("So now trying using listFiles() function");
                    FTPFile[] files = client.listFiles();
                    dirListing = "";
                    for (FTPFile f: files) {
                        dirListing = dirListing+f.getName()+separator;
                    }

                }

            } //If mode is SIMPLE
            else {
                dirListing = "Only SIMPLE mode supported as of now";
            }

            System.out.println(dirListing);
        } catch (NullPointerException npe) {
            String ex = "Seems you dont have access to FTP host " + hostname + " from Fusion Server";
            System.out.println(ex);
            dirListing = ex;
            try {
                client.logout();
            } catch (IOException f) {
                f.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("***There was an error");
            e.printStackTrace();
            dirListing = e.getMessage();
            try {
                client.logout();
            } catch (IOException f) {
                f.printStackTrace();
            }
        }

        return dirListing;
    }
    
    public String moveFile (String ftpRACFJndi, String sourcePathwithFileName, String destPathWithFileName){
        FTPClient client = null;
        String result = "";
        int resultFrom=0, resultTo=0;
        String[] ftpCredentials = {"ESSapd020-W251V.emrsn.org","ECMCS5","Emerson1234"};
        System.out.println("*** IN FtpDirGetterProcess.getDirectoryListing() *** 2");
        String dirListing = "";
        if (!(ftpRACFJndi == null || ftpRACFJndi.trim().equals(""))) {
            ftpCredentials = extractFTPCredentialsFromJNDI(ftpRACFJndi);
        }
        System.out.println("Got credentials.  Now trying to connect to " + ftpCredentials[0] + "(user = " + ftpCredentials[1] + ")");
        client = connect(ftpCredentials[0], ftpCredentials[1], ftpCredentials[2]);
        try {
            resultFrom = client.sendCommand("RNFR",sourcePathwithFileName);
            resultTo = client.sendCommand("RNTO",destPathWithFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = result+resultFrom+" "+resultTo;
        return result;
    }
    
    public static void main(String[] args) {
        FTPService object = new FTPService();
        String result = object.getFTPDirectoryListing("", "/CMC/DR/Infile", "", "ESSapd020-W251V.emrsn.org", "ECMCS5", "Emerson1234");
        System.out.println("List = "+result);
        result = object.moveFile(null,"/CMC/DR/Infile/5543_B.x_t","/CMC/DR/Staging/5543_B.x_t20090910");
        System.out.println("Return Code = "+result);
    }
    
}
