//#	
//#	Copyright (c) 2003 Emerson Electric Company 
//#	This program contains proprietary and confidential information.
//#	All rights reserved except as may be permitted by prior
//#	written consent.
//#
//#	%PCMS_HEADER_SUBSTITUTION_START%
//#	File Name:   %ARCHIVE%
//#	PVCS Spec:   %PID% 
//#		
//#	Attribute Label:  %LABEL%
//#	
//#	Description:
//#		%PD%
//#	
//#	Modification History:
//#		%PL%
//#	%PCMS_HEADER_SUBSTITUTION_END%
//#
//
//############################################################################
//#
//# Application   : XXEMR
//# Module        : E-commerce Gatway
//# File          : XXEMRRUNKSH.java
//# Version       : 1.0
//# Description   : Java Source for host commands from PL/SQL
//#
//# Change History
//# --------------
//#
//# Initial Version
//#
//############################################################################
//
import java.io.*;

import java.lang.*;

import java.sql.*;

import java.util.ArrayList;

import java.util.Calendar;

import oracle.sql.*;

import oracle.jdbc.driver.*;

public class xxemr_java_util extends Object {

    // added p_exception to fetch the Exception string if any. Else it will return simple stderr

    public static int RunKsh(String p_command, oracle.sql.ARRAY[] p_output, 
                             oracle.sql.ARRAY[] p_error, 
                             oracle.sql.ARRAY[] p_executionTrack) throws java.sql.SQLException, 
                                                                    IOException {

        Runtime rt = Runtime.getRuntime();

        String arrayType = "VARCHAR2_TABLE_2000";
        String tmpString;
        String[] tmpStringTable = new String[2000];
       ArrayList executionTracking = new ArrayList();
        String[] cmd = new String[2];
        int index;
        int i2;
        int rc = -1;
        Connection conn = null;
        ArrayDescriptor descriptor = null;
         // Inderpal Singh Dhami Added 20th July, 2010 - Start
         executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Starting the Java Process - xxemr_java_util.RunKsh");
         // Inderpal Singh Dhami Added 20th July, 2010 - End
        System.out.println("Start..");
        try {
            // Initialize the cmd array
            cmd[0] = "dir";
            cmd[1] = p_command;
            
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Executing the command got - "+cmd[0]+" "+cmd[1]);
            // Inderpal Singh Dhami Added 20th July, 2010 - End         
            
             System.out.println("Executing...");
            //Process the command
            Process p = rt.exec("date");
            System.out.println("Exec Done..");
            
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Process generated on System - "+p);
            // Inderpal Singh Dhami Added 20th July, 2010 - End 
            
             System.out.println("Getting the Streams");
            // Define the stdout and sterr stream readers
            BufferedReader stdout = 
                new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stderr = 
                new BufferedReader(new InputStreamReader(p.getErrorStream()));
            System.out.println("Got the Streams");
            
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Getting STDOUT and STDERR from System Process- "+stdout+" "+stderr);
            // Inderpal Singh Dhami Added 20th July, 2010 - End 
            
             System.out.println("Getting the Connection");
            // Define the stdout and stderr string arrays
            conn = new OracleDriver().defaultConnection();
            System.out.println("Got the Connection - "+conn);
            descriptor = ArrayDescriptor.createDescriptor(arrayType, conn);
            System.out.println("Got the Descriptor - "+descriptor);
            
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Got DB Connection and ArrayDescriptor - "+conn+" "+descriptor);
            // Inderpal Singh Dhami Added 20th July, 2010 - End 

             // Inderpal Singh Dhami Added 20th July, 2010 - Start
             executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Waiting for System Process to execute");
             // Inderpal Singh Dhami Added 20th July, 2010 - End 
             
              System.out.println("Executing Waiting...");
            // Wait for the process to finish
            rc = p.waitFor();
            System.out.println("Waiting Done..."+rc);
            
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] System Process execution complete "+rc);
            // Inderpal Singh Dhami Added 20th July, 2010 - End 
            
            // -------------------------------------------------------------------------------------       
            // Read the stdout into a temporary table of fixed length 2000 rows
            index = -1;
            while ((tmpString = stdout.readLine()) != null) {
                index++;
                // append the tmpString to the temporary table
                tmpStringTable[index] = tmpString;
            }
            // Read the fixed length table into a temporary table with just as many rows as necessary
            String[] tmpOutput = new String[index + 1];
            i2 = -1;
            while ((i2 < index)) {
                i2++;
                tmpOutput[i2] = tmpStringTable[i2];
            }
            // Define the output array 
            p_output[0] = new ARRAY(descriptor, conn, tmpOutput);
            
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Got the STDOUT into ORACLE ARRAY. Lines - "+(index + 1));
            // Inderpal Singh Dhami Added 20th July, 2010 - End

            // -------------------------------------------------------------------------------------
            // Read the stderr into a temporary table of fixed length 2000 rows
            index = -1;
            while ((tmpString = stderr.readLine()) != null) {
                index++;
                // append the tmpString to the temporary table
                tmpStringTable[index] = tmpString;
            }
            // Read the fixed length table into a temporary table with just as many rows as necessary
            String[] tmpError = new String[index + 1];
            i2 = -1;
            while ((i2 < index)) {
                i2++;
                tmpError[i2] = tmpStringTable[i2];
            }
            // Define the output array 
            p_error[0] = new ARRAY(descriptor, conn, tmpError);
            p_executionTrack[0] = new ARRAY(descriptor, conn, tmpError);
            
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Got the STDERR into ORACLE ARRAY. Lines - "+(index + 1));
            // Inderpal Singh Dhami Added 20th July, 2010 - End
            System.out.println("Done..");
        } catch (Exception e) {
            System.out.println("Done.."+e.toString());
            e.printStackTrace();
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
             executionTracking.add("["+Calendar.getInstance().getTime()+"] [ERROR] Error Occured - "+e.toString());
             int arrListSize = executionTracking.size();
            String[] expString = new String[arrListSize];
            for (int i=0;i<arrListSize ;i++ )  {
                expString[i] = (String)executionTracking.get(i+1);
            }
            p_executionTrack[0] = new ARRAY(descriptor, conn, expString);
            // Inderpal Singh Dhami Added 20th July, 2010 - End
            rc = -1;
        } finally {
            // Inderpal Singh Dhami Added 20th July, 2010 - Start
            executionTracking.add("["+Calendar.getInstance().getTime()+"] [INFO] Returning from Java Process - xxemr_java_util.RunKsh - "+rc);
            // Inderpal Singh Dhami Added 20th July, 2010 - End
            return rc;
        }
    }
    
    public static void main(String[] args) {
        oracle.sql.ARRAY[] p_out = null;
        oracle.sql.ARRAY[] p_error = null;
        oracle.sql.ARRAY[] p_trace = null;
        try {
            RunKsh("date",p_out, p_error, p_trace);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

