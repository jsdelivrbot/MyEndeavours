package com.emerson.icoe.supplierconnectivity.services.java;


import com.emerson.icoe.supplierconnectivity.services.database.DBUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.StringTokenizer;


public class ParseFlatFile implements InterfaceParseFile
{
    public ParseFlatFile()
    {
        System.out.println("ParseFlatFile instantiated");
    }

    public boolean parseFile(String fileName)
    {
        System.out.println("in ParseFlatFile:ParseFile(String)");
    
        try
        {
            File fInput = new File(fileName);
            String strFilePath = fInput.getAbsolutePath();
            //System.out.println(fInput.getName());
            
            FileInputStream fStreamInput = new FileInputStream(fInput);
            InputStreamReader isr = new InputStreamReader(fStreamInput);
            BufferedReader brInputFile = new BufferedReader(isr);
            String strLine = null;
            
            int lineNo = 0;
            while((strLine =brInputFile.readLine())!= null)
            {
            //each line reprensents a record to be inserted into database table
            
                System.out.println("Processing line - "+lineNo);
                
                //skiping first line as this is header row indicating description of the data
                if(lineNo == 1)
                {
                    System.out.println("Skipping first Line");
                    lineNo ++; // this will be executed only once
                    continue;//this means skip first header line
                }
                lineNo ++;    
                StringTokenizer strTok = new StringTokenizer(strLine,"|");
                System.out.println("token count is : "+strTok.countTokens()+"\n");
                
                int tokenNo = 1;
                HashMap<String,String> hMapDetailParams = new HashMap<String,String>();
                while (strTok.hasMoreElements())
                {
                    //we need 27,29,30,42,123,182 numbered tokens                                  
                    
                    if(tokenNo == 27 || tokenNo == 29 || tokenNo == 30 || tokenNo == 42 || tokenNo == 123 || tokenNo == 182)
                    {
                        String token = strTok.nextToken();
                        System.out.println("Token no.  "+tokenNo+" --"+token);
                        
                        if(tokenNo == 27)//means PO or ChangePO -- transformation required
                        {
                            if(token.equalsIgnoreCase("00")) // means PO, so Key1='PO' and Key2='Process'
                            {
                                System.out.println("Key1(Transaction Type) entered successfully as 'PO'");
                                hMapDetailParams.put("key1","PO");
                                
                                System.out.println("Key2(TransactionSubType) entered successfully as 'Process'");
                                hMapDetailParams.put("key2","Process");
                            }
                            else//means ChangePO, so Key1='PO' and Key2='Change'
                            {
                                System.out.println("Key1(Transaction Type) entered successfully as 'ChangePO'");
                                hMapDetailParams.put("key1","ChangePO");
                                
                                System.out.println("Key2(TransactionSubType) entered successfully as 'Change'");
                                hMapDetailParams.put("key2","Change");
                            }
                        }
                        
                        else if(tokenNo == 29)//PONumber - Key3
                        {
                            System.out.println("Key3(PONumber) entered successfully as "+token);
                            hMapDetailParams.put("key3",token);
                        }
                            
                        else if(tokenNo == 30)//ReleaseNumber - Key4
                        {
                            System.out.println("Key4(ReleaseNumber) entered successfully as "+token);
                            hMapDetailParams.put("key4",token);
                        }
                        
                        else if(tokenNo == 42)//RevisionNumber - Key5
                        {
                            System.out.println("Key5(RevisionNumber) entered successfully as "+token);
                            hMapDetailParams.put("key5",token);
                        }                    
                        
                        else if(tokenNo == 123)//POLineNumber - Key6
                        {
                            System.out.println("Key6(POLineNumber) entered successfully as "+token);
                            hMapDetailParams.put("key6",token);
                        }
                        
                        else if(tokenNo == 182)//ScheduleNumber - Key7
                        {
                            System.out.println("Key7(ScheduleNumber) entered successfully as "+token);
                            hMapDetailParams.put("key7",token);
                        }
                    }
                    else
                        strTok.nextToken();//increments the pointer
                    
                    //setting remaining parameters
                    
                     hMapDetailParams.put("feedid","201");
                     hMapDetailParams.put("payload",strLine);
                    
                    tokenNo ++;  
                }// this finishes one line from the inout file
                //now we will prepare call to insert operations in both Interim_Header & Interim_Details table
                
                
                HashMap<String,String> hMapHeaderParams = new HashMap<String,String> ();
                java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
                
                hMapHeaderParams.put("feedid","10");
                hMapHeaderParams.put("source","FTP");
                hMapHeaderParams.put("sourceSystem","FTP");
                hMapHeaderParams.put("sourceFormat","Flat File");
                hMapHeaderParams.put("sourceFileName",strFilePath);
                hMapHeaderParams.put("status","Started");
                hMapHeaderParams.put("direction","Outbound");
                hMapHeaderParams.put("dateDropped",date.toString());
                hMapHeaderParams.put("datePicked",date.toString());
                hMapHeaderParams.put("dateProcessed",date.toString());
                hMapHeaderParams.put("dateCompleted",date.toString());
                
            //now we have all set to invoke DB operations on Interim tables
            //we will call Header operation & then Detail operation
                
                DBUtils objDBUtils = new DBUtils();
                boolean boolHeaderStatus = objDBUtils.insertDataInterimHeader(hMapHeaderParams);
                if(boolHeaderStatus == true)
                {
                    System.out.println("Header table data insert successful");
                    boolean boolDetailstatus = objDBUtils.insertDataInterimDetail(hMapDetailParams);
                    if(boolDetailstatus == true)
                    {//it means transaction successful
                        System.out.println("Transaction successful");
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception occurred : "+e);
        }
        
        System.out.println("Exiting ParseFlatFile:parseFile");

        return false;
    }
    /*private boolean readFile(String fileName) throws Exception
    {
        File fInput = new File(fileName);
        System.out.println(fInput.getName());
        
        FileInputStream fStreamInput = new FileInputStream(fInput);
        InputStreamReader isr = new InputStreamReader(fStreamInput);
        BufferedReader brInputFile = new BufferedReader(isr);
        String strLine = null;
        
        
        while((strLine =brInputFile.readLine())!= null)
        {
            StringTokenizer strTok = new StringTokenizer(strLine,"|");
            //System.out.println(strLine);
            System.out.println("token count is : "+strTok.countTokens()+"\n");
            //working on tokens
            //while(strTok.hasMoreTokens())
            //{
                for(int i=0; i<strTok.countTokens(); i++)
                {
                    System.out.println("Token no.  "+i+" --"+strTok.nextToken());
                }
            //}
        }
                  
        return fInput.exists();
    } */
    
    public static void main(String[] args) throws Exception
    {
        ParseFlatFile rfc = new ParseFlatFile();
        rfc.parseFile("d:/input/input.txt");
        System.out.println("main over");
    }
}
