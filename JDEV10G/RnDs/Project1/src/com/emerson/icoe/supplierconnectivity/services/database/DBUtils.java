package com.emerson.icoe.supplierconnectivity.services.database;

import com.emerson.icoe.supplierconnectivity.services.java.InterfaceDBOperations;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DBUtils implements InterfaceDBOperations
{
    private static int feedID = 201;
    private Connection connRef = null;
    
    public DBUtils() 
    {
        System.out.println("DBUtils instantiated");
    }

    public boolean insertDataInterimHeader(HashMap<String,String> mapHeaderData)
    {
        System.out.println("Starting DBUtils:insertDataInterimHeader()");
        try
        {
            if(connRef == null)
                connRef = ConnectionManager.getDBConnection();
            PreparedStatement pstmtHeader = connRef.prepareStatement("insert into interim_header values(?,?,?,?,?,?,?,?,?,?,?)");
            //(feedid, source, source_system, source_format, source_file_name, status,direction, DroppedDateTime, PickedDateTime, ProcessedDateTime, CompletionDateTime) 
            
            if(mapHeaderData.size() < 11)
                System.out.println("One or more parameters missing for Header table operation; but still continuing");
            
            //passing parameter values from input HashMap
            
            pstmtHeader.setInt(1,feedID++);//feedid, as of now we do not have any sequence defined
            pstmtHeader.setString(2,mapHeaderData.get("source").toString());//source
            pstmtHeader.setString(3,mapHeaderData.get("sourceSystem").toString());//sourceSystem
            pstmtHeader.setString(4,mapHeaderData.get("sourceFormat").toString());//sourceFormat
            pstmtHeader.setString(5,mapHeaderData.get("sourceFileName").toString());//sourceFileName
            
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            //System.out.println("current date-time using java.sql.Date is : "+date.toString());
            
            pstmtHeader.setString(6,mapHeaderData.get("status").toString());//status
            pstmtHeader.setString(7,mapHeaderData.get("direction").toString());//direction
             
            pstmtHeader.setDate(8,date);//dateDropped
            pstmtHeader.setDate(9,date);//datePicked
            pstmtHeader.setDate(10,date);//dateProcessed
            pstmtHeader.setDate(11,date);//dateCompleted 
            
//            pstmtHeader.setString(8,mapHeaderData.get("dateDropped"));//dateDropped
//            pstmtHeader.setString(9,mapHeaderData.get("datePicked").toString());//datePicked
//            pstmtHeader.setString(10,mapHeaderData.get("dateProcessed").toString());//dateProcessed
//            pstmtHeader.setString(11,mapHeaderData.get("dateCompleted").toString());//dateCompleted  
           
            
            //pstmtHeader.setClob();   
            //cstmt.setObject(parmInd, charData, java.sql.Types.CLOB); 
            pstmtHeader.executeUpdate();
            
            System.out.println("INSERT into Header table finished successfully");
                    
        }
        catch(Exception e)
        {
            System.out.println("Exception occurred in DBUtils:insertDataHeaderTable() \n");
            e.printStackTrace();
        }
        
        System.out.println("Exiting DBUtils:insertDataInterimHeader()");
        return true;
    }

    public boolean insertDataInterimDetail(HashMap<String,String> mapDetailData)
    {
        System.out.println("Starting DBUtils:insertDataInterimDetail()");
        try
        {
            if(connRef == null)
                connRef = ConnectionManager.getDBConnection();
                
            PreparedStatement pstmtDetail = connRef.prepareStatement("insert into interim_detail values(?,?,?,?,?,?,?,?,?,?,?)");
            
            if(mapDetailData.size()<11)
                System.out.println("One or more parameters missing for Detail table operation; but still continuing");
            
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            
            System.out.println("mapDetailData.get(feedid)"+mapDetailData.get("feedid"));
            System.out.println("mapDetailData.get(key1)"+mapDetailData.get("key1"));
            System.out.println("mapDetailData.get(key2)"+mapDetailData.get("key2"));
            System.out.println("mapDetailData.get(key3)"+mapDetailData.get("key3"));
            System.out.println("mapDetailData.get(key4)"+mapDetailData.get("key4"));
            System.out.println("mapDetailData.get(key5)"+mapDetailData.get("key5"));
            System.out.println("mapDetailData.get(key6)"+mapDetailData.get("key6"));
            System.out.println("mapDetailData.get(key7)"+mapDetailData.get("key7"));
            System.out.println("mapDetailData.get(payload)"+mapDetailData.get("payload"));
            
            pstmtDetail.setString(1,mapDetailData.get("feedid"));
            pstmtDetail.setString(2,mapDetailData.get("key1"));
            pstmtDetail.setString(3,mapDetailData.get("key2"));
            pstmtDetail.setString(4,mapDetailData.get("key3"));
            pstmtDetail.setString(5,mapDetailData.get("key4"));
            pstmtDetail.setString(6,mapDetailData.get("key5"));
            pstmtDetail.setString(7,mapDetailData.get("key6"));
            pstmtDetail.setString(8,mapDetailData.get("key7"));
            //pstmtDetail.setString(9,"");
            pstmtDetail.setString(9,mapDetailData.get("payload"));
            //pstmtDetail.setObject(9, mapDetailData.get("payload"), java.sql.Types.CLOB);
            pstmtDetail.setDate(10,null);
            pstmtDetail.setDate(11,null);
            
            pstmtDetail.executeUpdate();
            
            System.out.println("INSERT into Detail table finished successfully");
            
        }
        catch(Exception e)
        {
            System.out.println("Exception occurred in DBUtils:insertDataDetailTable() \n");
            e.printStackTrace();  
        }
        
        System.out.println("Exiting DBUtils:insertDataInterimDetail()");
        return true;
    }
    
   public static void main(String abc[]) throws Exception
    {
        DBUtils obj = new DBUtils();
        
        HashMap<String,String> mapPara = new HashMap<String,String> ();
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        
        mapPara.put("feedid","10");
        mapPara.put("source","FTP");
        mapPara.put("sourceSystem","FTP");
        mapPara.put("sourceFormat","Flat File");
        mapPara.put("sourceFileName","input.txt");
        mapPara.put("status","Started");
        mapPara.put("direction","Outbound");
        mapPara.put("dateDropped",null);
        mapPara.put("datePicked",null);
        mapPara.put("dateProcessed",null);
        mapPara.put("dateCompleted",null);

        
        obj.insertDataInterimHeader(mapPara);
        
        
    }
}
