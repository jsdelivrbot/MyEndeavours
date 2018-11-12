package com.emerson.common.http.accessCheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.URLConnection;

import java.util.ArrayList;
import java.util.Calendar;

public class Check {
    public Check() {

    }
    private final static String FAILED_URL_FILE_NAME = "FailedURLs.txt";
    private final static String LOG_URL_FILE_NAME = "Http_Check.log";
    private final static String NEW_LINE = "\n";
    private final static String PROJECT_URL_SPLITTER = "@";
    private final static String HEADER_STARS = "***************************************************";
    private final static String COMMENT_CHARACTER = "#";
    private final static int HTTP_LOWEST_ERROR_CODE = 400;
    private final static long HIGH_ACCESS_TIME_MS = 50000;

    public static void main(String[] args) {
        ArrayList urlList = null;
        URL url = null;
        HttpURLConnection urlConnection = null;
        urlList = readURLFile("urlList.txt");
        //System.out.println("List - " + urlList);

        if (urlList != null && urlList.size() != 0) {
            String result = null;
            String failedURLs = "";
            String log = HEADER_STARS+NEW_LINE+Calendar.getInstance().getTime()+NEW_LINE+HEADER_STARS+NEW_LINE;
            long startTime = 0, connectTime = 0;
            for (int i = 0; i < urlList.size(); i+=2) {
                try {
                    startTime = 0;
                    result = urlList.get(i).toString() + " - " + urlList.get(i+1).toString();;
                    url = new URL(urlList.get(i+1).toString());
                    startTime = System.currentTimeMillis();
                    urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.connect();
                    result = result + "  " + urlConnection.getResponseCode() + "  " + urlConnection.getResponseMessage();
                    connectTime = System.currentTimeMillis() - startTime;
                    result = result +"  "+connectTime+"ms";
                    
                    if (urlConnection.getResponseCode() >= HTTP_LOWEST_ERROR_CODE || connectTime>=HIGH_ACCESS_TIME_MS) {
                        failedURLs = failedURLs + result + NEW_LINE;
                    }
                    urlConnection.disconnect();
                } catch (Exception e) {
                    connectTime = System.currentTimeMillis() - startTime;
                    result = result + "  ERROR  " + e.getMessage()+ "  "+connectTime+"ms";
                    failedURLs = failedURLs + result + NEW_LINE;
                } finally {
                    log = log + result + NEW_LINE;
                    try {
                        urlConnection.disconnect();
                        urlConnection = null;
                    } catch (Exception e) {
                        // DO NOTHING
                    }
                }
                System.out.println(result);
            }
            
            if (failedURLs != null && failedURLs.length() > 0) {
                failedURLs = "Following are the inaccessible URLs: "+NEW_LINE+failedURLs;
                writeToFile(FAILED_URL_FILE_NAME,failedURLs, false);
            }
            log = log + HEADER_STARS+NEW_LINE+NEW_LINE;
            writeToFile(LOG_URL_FILE_NAME,log, true);
            failedURLs = null;
            log = null;
            urlList = null;
        }        
    }
    
    private static boolean writeToFile(String fileName, String fileContent, boolean append){
        FileWriter fstream = null;
        BufferedWriter out = null;
        boolean fileWritten = false;
        try {
            fstream = new FileWriter(fileName,append);
            out = new BufferedWriter(fstream);
            out.write(fileContent);
            out.close();
            fileWritten = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try{
                fstream = null;
                out.close();
                out = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileWritten;
    }

    private static ArrayList readURLFile(String fileName) {
        ArrayList urlList = null;
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String[] readURLLine = null;
            urlList = new ArrayList();
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                // System.out.println(strLine);
                strLine = strLine.trim();
                if(strLine.length()>0 && !strLine.startsWith(COMMENT_CHARACTER)){
                    System.out.println();    
                    readURLLine = strLine.split(PROJECT_URL_SPLITTER);
                    //System.out.println("Splitted - "+readURLLine[0]+" "+readURLLine[1]);
                    urlList.add(readURLLine[0]);    
                    urlList.add(readURLLine[1]);
                }
                
            }
            //Close the input stream
            in.close();
        } catch (Exception e) { //Catch exception if any
            System.err.println("com.emerson.common.http.accessCheck.Check.readURLFile: Error: " + 
                               e.getMessage());
            e.printStackTrace();
        }
        return urlList;
    }

}
