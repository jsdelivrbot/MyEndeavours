 /**
  * Add the following jars
 commons-net-1.4.1.jar (http://www.java2s.com/Code/JarDownload/commons-net-1.4.1.jar.zip)
 jakarta-oro-2.0.8 (http://mirrors.kahuki.com/apache/jakarta/oro/binaries/jakarta-oro-2.0.8.zip)
  */
 package com.emerson.services;

import java.io.IOException;

import java.lang.reflect.Method;

import java.util.Calendar;

import javax.naming.InitialContext;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;

public class FtpDirGetterProcess {
    String m_host;
    String m_uid;
    String m_pwd;
    String m_dir;

    public FtpDirGetterProcess() {
    }

    private FTPClient connect() {
        FTPClient client = null;

        try {
            client = new FTPClient();
            client.connect(m_host);
            client.login(m_uid, m_pwd);
            client.changeWorkingDirectory(m_dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * 
     * @param jndi - Format is eis/FTP/MYFTP, not java:comp/env/eis/FTP/MYFTP
     * @param dir  - Directory for which file listing is to be returned
     * @param mode - how the directory listing should be returned.  
     *               SIMPLE= only files, 
     *               COMPLETE = complete file info
     * @return string that represents the file list for a given directory
     */
    public String getDirectoryListing(String jndi, String dir, String mode, 
                                      String host, String uid, String pwd) {
        FTPClient client = null;
        System.out.println("*** IN FtpDirGetterProcess.getDirectoryListing() *** ");
        StringBuilder dirListing = new StringBuilder(1000);
        //Set default values...useful during testing.
        if (jndi == null || 
            jndi.trim().equals("")) { //Use the default JNDI when even host name passed in is empty.
            if (host == null || host.trim().equals("")) {
                dirListing.append("You cannot pass both JNDI and hostname as null");
            }
        }

        if (mode == null || mode.trim().equals(""))
            mode = "SIMPLE";

        m_host = host;
        m_uid = uid;
        m_pwd = pwd;
        m_dir = dir;
        System.out.println("****jndi " + jndi);
        System.out.println("****host " + host);
        System.out.println("***dir " + dir);
        System.out.println("***mode " + mode);

        //We try to get the host, uid and pwd information from the JNDI, 
        //instead of using the values passed.
        //If this fails, we will use the values passed in.
        try {
            //comment this when running independently.  
            //This is because in standalone, JNDI will not work
            if (!(jndi == null || jndi.trim().equals(""))) {
                setHostUidPwdUsingJndi(jndi);
            }
            System.out.println("Got credentials.  Now trying to connect to " + 
                               m_host + "(user = " + m_uid + ")");
            client = connect();
            if (mode.equals("SIMPLE")) {
                // Obtain a list of filenames in the current working directory.
                System.out.println("Retrieving directory listing");
                String separator = ";";
                try {
                    String[] names = client.listNames();

                    for (String name: names) {
                        dirListing.append(name);
                        dirListing.append(separator);
                    }
                    
                    int result = client.sendCommand("RNFR","/infile/850/testEMC123.txt");
                    System.out.println("Result - "+result);
                    result = client.sendCommand("RNTO","/infile/temp/testEMC123.txt20090910");
                    System.out.println("Result - "+result);
                } catch (Exception e) {
                    System.out.println("*** Following exception occured while using listNames. ");
                    System.out.println(e.getMessage());
                    System.out.println("So now trying using listFiles() function");
                    FTPFile[] files = client.listFiles();
                    for (FTPFile f: files) {
                        dirListing.append(f.getName());
                        dirListing.append(separator);
                    }

                }

            } //If mode is SIMPLE
            else {
                dirListing.append("Only SIMPLE mode supported as of now");
            }

            System.out.println(dirListing);
        } catch (NullPointerException npe) {
            String ex = 
                "Seems you dont have access to FTP host " + m_host + " from Fusion Server";
            System.out.println(ex);
            dirListing.append(ex);
            try {
                client.logout();
            } catch (IOException f) {
                f.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("***There was an error");
            e.printStackTrace();
            dirListing.append(e.getMessage());
            try {
                client.logout();
            } catch (IOException f) {
                f.printStackTrace();
            }
        }

        return dirListing.toString();
    }


    private void setHostUidPwdUsingJndi(String jndi) {
        InitialContext ctx = null;

        try {
            ctx = new InitialContext();
            Object jndiFtpObj = ctx.lookup(jndi);


            Class fTPConnectionFactoryClass = 
                Class.forName("oracle.tip.adapter.ftp.FTPConnectionFactory", 
                              true, jndiFtpObj.getClass().getClassLoader());
            Method getManagedConnectionFactory = 
                fTPConnectionFactoryClass.getMethod("getManagedConnectionFactory", 
                                                    (Class[])null);

            Object fTPManagedConnectionFactoryObject = 
                getManagedConnectionFactory.invoke(jndiFtpObj, (Object[])null);

            Class fTPManagedConnectionFactoryClass = 
                fTPManagedConnectionFactoryObject.getClass();
            Method getHost = 
                fTPManagedConnectionFactoryClass.getMethod("getHost", 
                                                           (Class[])null);
            Method getUsername = 
                fTPManagedConnectionFactoryClass.getMethod("getUsername", 
                                                           (Class[])null);
            Method getPassword = 
                fTPManagedConnectionFactoryClass.getMethod("getPassword", 
                                                           (Class[])null);

            m_host = 
                    (String)getHost.invoke(fTPManagedConnectionFactoryObject, (Object[])null);
            m_uid = 
                    (String)getUsername.invoke(fTPManagedConnectionFactoryObject, 
                                               (Object[])null);
            m_pwd = 
                    (String)getPassword.invoke(fTPManagedConnectionFactoryObject, 
                                               (Object[])null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @param jndi - Format is eis/FTP/MYFTP, not java:comp/env/eis/FTP/MYFTP
     * @param dir  - Directory for which file listing is to be returned
     * @param mode - how the directory listing should be returned.  
     *               SIMPLE= only files, 
     *               COMPLETE = complete file info
     * @return string that represents the file list for a given directory
     */
    public String getModifiedListing(String jndi, String dir, String mode) {
        FTPClient client = null;
        System.out.println("*** IN FtpDirGetterProcess.getModifiedListing() *** ");
        StringBuilder dirListing = new StringBuilder(1000);

        if (mode == null || mode.trim().equals("")){
            mode = "SIMPLE";   
        }
        m_dir = dir;
        System.out.println("****2jndi " + jndi);
        System.out.println("***2dir " + dir);
        System.out.println("***2mode " + mode);

        //We try to get the host, uid and pwd information from the JNDI, 
        //instead of using the values passed.
        //If this fails, we will use the values passed in.
        try {
            //comment this when running independently.  
            //This is because in standalone, JNDI will not work
            if (!(jndi == null || jndi.trim().equals(""))) {
                setHostUidPwdUsingJndi(jndi);
            }
            System.out.println("Got credentials.  Now trying to connect to " + m_host + "(user = " + m_uid + ")");
            client = connect();
            if (mode.equals("SIMPLE")) {
                // Obtain a list of filenames in the current working directory.
                System.out.println("Retrieving directory listing");
                String separator = ";";
                Calendar currentDateTime = Calendar.getInstance();
                try {
                    FTPFile[] files = client.listFiles();
                    for (FTPFile f: files) {
                        System.out.println("************************");
                        System.out.println("FileName - "+f.getName()+"  "+f.getRawListing()+"  "+f.getTimestamp());
                        if(currentDateTime.compareTo(f.getTimestamp())<0){
                            dirListing.append(f.getName());
                            dirListing.append(separator);   
                        }
                    }
                } catch (Exception e) {
                    System.out.println("*** Following exception occured while using listNames. ");
                    System.out.println(e.getMessage());
                }

            }
            else {
                dirListing.append("Only SIMPLE mode supported as of now");
            }
            System.out.println(dirListing);
        } catch (NullPointerException npe) {
            String ex = 
                "Seems you dont have access to FTP host " + m_host + " from Fusion Server";
            System.out.println(ex);
            dirListing.append(ex);
            try {
                client.logout();
                client.disconnect();
            } catch (IOException f) {
                f.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("***There was an error");
            e.printStackTrace();
            dirListing.append(e.getMessage());
            try {
                client.logout();
                client.disconnect();
            } catch (IOException f) {
                f.printStackTrace();
            }
        }
        return dirListing.toString();
    }


    public static void main(String[] args) {
        FtpDirGetterProcess ftp = new FtpDirGetterProcess();
        String names = ftp.getDirectoryListing("", "/archive/TransactionReports", "", "elshat1.emrsn.com", 
                                "eemct1", "et167002");
                                
        System.out.println("Names - "+names);
    }
}
