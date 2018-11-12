package com.emerson.date.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class ConvertFormat {
    public ConvertFormat() {
        System.out.println("<INFO> ConvertFormat Called");
    }

    public static String date(String srcDateString, String sourceFormat, 
                              String destinationFormat) {
        String destDateString = null;
        if ((srcDateString != null && srcDateString.equalsIgnoreCase("")) || 
            (sourceFormat != null && sourceFormat.equalsIgnoreCase("")) || 
            (destinationFormat != null && 
             destinationFormat.equalsIgnoreCase(""))) {

            System.out.println("<ERROR> ConvertFormat:date - Input got is NULL - " + 
                               srcDateString + ";" + sourceFormat + ";" + 
                               destinationFormat);
            return srcDateString;
        }
        try {
            System.out.println("<INFO> ConvertFormat:date - Input got - " + 
                               srcDateString + ";" + sourceFormat + ";" + 
                               destinationFormat);
            // 08/14/2009 15:24
            //create SimpleDateFormat object with source string date format      
            SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);
            //parse the string into Date object      
            Date date = sdfSource.parse(srcDateString);

            // 2009-07-01T14:54:30+00:00
            //create SimpleDateFormat object with desired date format      
            SimpleDateFormat sdfDestination = 
                new SimpleDateFormat(destinationFormat);
            //parse the date into another format      
            destDateString = sdfDestination.format(date);
            System.out.println("Date is converted from " + sourceFormat + 
                               " format to " + destinationFormat);
        } catch (ParseException pe) {
            System.out.println("<ERROR> ConvertFormat:date - Parse Exception : " + 
                               pe);
        }
        return destDateString;
    }
    
    public static String toISODate(String srcDateString, String sourceFormat) {
        String destinationFormat = "yyyy-MM-dd'T'HH:mm:ss";
        String destDateString = null;
        if ((srcDateString != null && srcDateString.equalsIgnoreCase("")) || 
            (sourceFormat != null && sourceFormat.equalsIgnoreCase("")) || 
            (destinationFormat != null && 
             destinationFormat.equalsIgnoreCase(""))) {

            System.out.println("<ERROR> ConvertFormat:date - Input got is NULL - " + 
                               srcDateString + ";" + sourceFormat + ";" + 
                               destinationFormat);
            return srcDateString;
        }
        try {
            System.out.println("<INFO> ConvertFormat:date - Input got - " + 
                               srcDateString + ";" + sourceFormat + ";" + 
                               destinationFormat);
            // 08/14/2009 15:24
            //create SimpleDateFormat object with source string date format      
            SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);
            //parse the string into Date object      
            Date date = sdfSource.parse(srcDateString);

            // 2009-07-01T14:54:30+00:00
            //create SimpleDateFormat object with desired date format      
            SimpleDateFormat sdfDestination = 
                new SimpleDateFormat(destinationFormat);
            //parse the date into another format      
            destDateString = sdfDestination.format(date);
            System.out.println("Date is converted from " + sourceFormat + 
                               " format to " + destinationFormat);
        } catch (ParseException pe) {
            System.out.println("<ERROR> ConvertFormat:date - Parse Exception : " + 
                               pe);
        }
        return destDateString;
    }
}
