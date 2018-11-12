package com.emerson.date.formatter;

import com.oracle.bpel.xml.xpath.IXPathContext;
import com.oracle.bpel.xml.xpath.IXPathFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConvertDate implements IXPathFunction{
    public ConvertDate() {System.out.println("ConvertDate Started"); }

    public static void main(String[] args) {
        ConvertDate c = new ConvertDate();
        
        String strDate = "08/14/2009 15:24";
        List l = new ArrayList();
        l.add("08/14/2009 15:24");
        l.add("MM/dd/yyyy HH:mm");
        l.add("yyyy-MM-dd'T'HH:mm:ss");
        System.out.println("Result got - "+c.call(null, l));
        /*try{      
            
            // 08/14/2009 15:24
            //create SimpleDateFormat object with source string date format      
            SimpleDateFormat sdfSource = new SimpleDateFormat("MM/dd/yyyy HH:mm");       
            //parse the string into Date object      
            Date date = sdfSource.parse(strDate);
            
            // 2009-07-01T14:54:30+00:00
            //create SimpleDateFormat object with desired date format      
            SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");       
            //parse the date into another format      
            strDate = sdfDestination.format(date);       
            System.out.println("Date is converted from MM/dd/yyyy hh:mm format to yyyy-MM-dd'T'hh:mm:ss'+00:00'");
            System.out.println("Converted date is : " + strDate);     
        }    
        catch(ParseException pe){
            System.out.println("Parse Exception : " + pe);    
        }*/
    }

    public Object call(IXPathContext ixPathContext, List args) {
        String srcDateString = null;
        String sourceFormat = null;
        String destinationFormat = null;
        String destDateString = null;
        if(args != null && args.size()==3){
            srcDateString = (String)args.get(0);
            sourceFormat = (String)args.get(1);
            destinationFormat = (String)args.get(2);
            
            try{      
                
                // 08/14/2009 15:24
                //create SimpleDateFormat object with source string date format      
                SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);       
                //parse the string into Date object      
                Date date = sdfSource.parse(srcDateString);
                
                // 2009-07-01T14:54:30+00:00
                //create SimpleDateFormat object with desired date format      
                SimpleDateFormat sdfDestination = new SimpleDateFormat(destinationFormat);       
                //parse the date into another format      
                destDateString = sdfDestination.format(date);       
                System.out.println("Date is converted from "+sourceFormat+" format to "+destinationFormat); 
            }    
            catch(ParseException pe){
                System.out.println("Parse Exception : " + pe);
            }
        }
        
        return destDateString;
    }
}
