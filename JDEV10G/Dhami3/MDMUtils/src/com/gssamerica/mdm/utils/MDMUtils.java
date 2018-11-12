package com.gssamerica.mdm.utils;

import com.gssamerica.mdm.constants.MDMConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public abstract class MDMUtils {
       
    public static String getDataTypeName(int sqlType){
            String typeName = null;

            if(sqlType == MDMConstants.MDM_STRING.getSqlType()){
                    typeName = MDMConstants.MDM_STRING.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_INTEGER.getSqlType()){
                    typeName = MDMConstants.MDM_INTEGER.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_FLOAT.getSqlType()){
                    typeName = MDMConstants.MDM_FLOAT.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_DOUBLE.getSqlType()){
                    typeName = MDMConstants.MDM_DOUBLE.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_DATE.getSqlType()){
                    typeName = MDMConstants.MDM_DATE.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_BOOLEAN.getSqlType()){
                    typeName = MDMConstants.MDM_BOOLEAN.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_LONG.getSqlType()){
                    typeName = MDMConstants.MDM_LONG.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_NUMBER.getSqlType()){
                    typeName = MDMConstants.MDM_NUMBER.getTypeName();
            }
            else if(sqlType == MDMConstants.MDM_CHAR.getSqlType()){
                    typeName = MDMConstants.MDM_CHAR.getTypeName();
            }
            else{
                    typeName = MDMConstants.MDM_OTHER.getTypeName();
            }
            
            return typeName;
    }
    
    public static String inputStreamAsString(InputStream stream){
        BufferedReader bufferedReader = null;
        StringBuilder sb = null;
        String line = null;
        String streamString = null;
        try {
            if (stream!=null) {
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                bufferedReader.close();
                streamString = sb.toString();
            }
        } catch (Exception e) {
            System.out.println("[MDMUtils][inputStreamAsString] : Exception while processing InputStream - "+stream);
            e.printStackTrace();
        }    
        return streamString;
    }
    
    public static Date getDateFromString(String dateString, String dateFormat){
        DateFormat df = null;
        Date date = null;
        if(dateString==null || dateFormat==null){
            return null;
        }
        try{
            df = new SimpleDateFormat(dateFormat);
            date = df.parse(dateString);            
        } catch (Exception e){
            System.out.println("[MDMUtils][getDateFromString] : Exception while creating Date - dateString["+dateString+"] dateFormat["+dateFormat+"]");
            e.printStackTrace();
        }
        return date;
    }
    
    public static String getDateString(Date date, String dateFormat){
        DateFormat df = null;
        String dateString = null;
        if(date==null || dateFormat==null){
            return null;
        }
        try{
            df = new SimpleDateFormat(dateFormat);
            dateString = df.format(date);           
        } catch (Exception e){
            System.out.println("[MDMUtils][getDateString] : Exception while creating DateString - date["+date+"] dateFormat["+dateFormat+"]");
            e.printStackTrace();
        }
        return dateString;
    }
}
