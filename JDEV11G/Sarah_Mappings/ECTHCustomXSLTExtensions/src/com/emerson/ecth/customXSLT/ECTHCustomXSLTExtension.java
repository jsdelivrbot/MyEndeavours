package com.emerson.ecth.customXSLT;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Types;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

/**
 * This class is to declare all the methods to be used inside SOA Mapping (XSLTs).
 * @author Inderpal Singh Dhami
 * @version 1.0
 */
public class ECTHCustomXSLTExtension {
  private static boolean logFlag = false;

  public ECTHCustomXSLTExtension() {
    super();
  }

  /*
   * This function will get EDI Date(CCYYMMDD) and Time in format - 20110401 and (150723 or 1507) with time zone +05:30
   * And will return back just the Date in ISO format - 2011-04-01T15:07:00.0000+05:30
   * This function will not handle Date in YYMMDD. Caller can concatenate the CC with the ediDate before calling the method.
   * If only Date with NO time is required back, i.e. 2011-04-01. Then pass "NO_TIME" in ediTime parameter
   *
   * @param ediDate - format CCYYMMDD
   * @param ediTime - format HHmmss OR HHmm
   * @param timezone - format (+/-)HH:mm
   * @param defaultResponse - any string. If any issue in processing the request, function will return this value.
   * @return ISO DateTime in format CCYY-MM-DDTHH:mm:ss.SSSS+TT:TT
   */

  public static String getISODateFromEDI(String editDate, String ediTime,
                                         String ediTimezone,
                                         String defaultResponse) {
    // This function will get EDI Date and Time in format - 20110401 and (150723 or 1507)
    // And will return back just the Date in ISO format - 2011-04-01T15:07:00.0000+00:00
    String isoDateTime = defaultResponse;
    String track =
      "StartXSLT:getISODateFromEDI[" + editDate + "," + ediTime + "," +
      ediTimezone + "," + defaultResponse + "]";

    try {
      if (editDate != null) {
        editDate = editDate.trim();
        if (!editDate.equalsIgnoreCase("") && (editDate.length() == 8)) {
          String year = editDate.substring(0, 4);
          String month = editDate.substring(4, 6);
          String day = editDate.substring(6);
          if(!"NO_TIME".equalsIgnoreCase(ediTime)){
            String hour = "00";
            String minutes = "00";
            String seconds = "00";
            String timezone = "+00:00";
            if (ediTime != null) {
              ediTime = ediTime.trim();
              if (!ediTime.equalsIgnoreCase("")) {
                if (ediTime.length() == 6) {
                  hour = ediTime.substring(0, 2);
                  minutes = ediTime.substring(2, 4);
                  seconds = ediTime.substring(4);
                }
                if (ediTime.length() == 4) {
                  hour = ediTime.substring(0, 2);
                  minutes = ediTime.substring(2);
                }
              }
            }
            if (ediTimezone != null) {
              ediTimezone = ediTimezone.trim();
              if (!ediTimezone.equalsIgnoreCase("") &&
                  (ediTimezone.length() == 6)) {
                timezone = ediTimezone;
              }
            }
            isoDateTime =
                year + "-" + month + "-" + day + "T" + hour + ":" + minutes +
                ":" + seconds + ".0000" + timezone;
          }
          else{
            isoDateTime = year + "-" + month + "-" + day;
          }
          
        } else {
          track = track + " | [EDIDate is Empty=" + editDate + "]";
        }
      } else {
        track = track + " | [EDIDate is NULL=" + editDate + "]";
      }
    } catch (Exception e) {
      track = track + " | {ERROR=" + e.getMessage() + "}";
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][getISODateFromEDI] - "+e.getMessage());
    }

    track = track + ":ENDXSLT:getISODateFromEDI[" + isoDateTime + "]";

    if (logFlag) {
      System.out.println(track);
      track = null;
    }
    return isoDateTime;
  }
  /*
   * This function will get canonical Date Time in format - 2011-04-01T09:39:44.0000+00:00
   * And will return back just the Date in format - 20110401
   *
   * @param canonicalDateTime - format CCYY-MM-DDTHH:mm:ss.SSSS+TT:TT
   * @param defaultResponse - any string. If any issue in processing the request, function will return this value.
   * @return EDI Date in CCYYMMDD
   */

  public static String getEDIDate(String canonicalDateTime,
                                  String defaultResponse) {
    // This function will get canonical Date Time in format - 2011-04-01T09:39:44.0000+00:00
    // And will return back just the Date in format - 20110401
    String ediDate = defaultResponse;
    String track = "StartXSLT:getEDIDate[" + canonicalDateTime + "]";

    try {
      if (canonicalDateTime != null &&
          ("".compareToIgnoreCase(canonicalDateTime) != 0)) {
        String[] date = canonicalDateTime.split("T");
        track = track + " | DateTimeSplit[" + date.length + "]";
        if (date != null && date.length > 0) {
          String[] dateParts = date[0].split("-");
          track = track + " | DatePartsSplit[" + dateParts.length + "]";
          if (dateParts != null && dateParts.length == 3) {
            ediDate = dateParts[0] + dateParts[1] + dateParts[2];
            track = track + " | EDIDate[" + ediDate + "]";
          } else {
            track = track + " | DatePartsNULL[" + dateParts + "]";
          }
        } else {
          track = track + " | DateTimeNULL[" + date + "]";
        }
      }
    } catch (Exception e) {
      track = track + " | {ERROR=" + e.getMessage() + "}";
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][getEDIDate] - "+e.getMessage());
    }
    track = track + ":ENDXSLT:getEDIDate[" + canonicalDateTime + "]";

    if (logFlag) {
      System.out.println(track);
      track = null;
    }
    return ediDate;
  }

 /*
   * This Function will split the inputString passed using the splitCharacter and will return the [returnIndex-1]th value.
   * Value of returnIndex passed should be from 1 to inputstring.length
   *
   * @param inputString - Input String
   * @param splitCharacter - a string on base of that <code>inputString</code> will be split.
   * @param returnIndex - number starting from 1 upto total length of <code>inputString</code>
   * @param defaultResponse - any string. If any issue in processing the request, function will return this value.
   * @return string containing [returnIndex-1]th split part
   */

  public static String split(String inputString, String splitCharacter,
                             int returnIndex, String defaultResponse) {

    // This Function will split the inputString passed using the splitCharacter and will return the [returnIndex-1]th value.
    // Value of returnIndex passed would be from 1 to inputstring.length
    String result = defaultResponse;
    String track =
      "StartXSLT:split[" + inputString + "," + splitCharacter + "" +
      returnIndex + "]";
    try {
      if ((inputString != null &&
           ("".compareToIgnoreCase(inputString) != 0)) ||
          (splitCharacter != null &&
           ("".compareToIgnoreCase(splitCharacter) != 0)) ||
          returnIndex >= 1) {
        String[] splitParts = inputString.split(splitCharacter);
        track = track + " | SplitParts[" + splitParts + "]";
        if (splitParts != null && splitParts.length >= returnIndex) {
          result = splitParts[returnIndex - 1];
          track = track + " | Result[" + result + "]";
        } else {
          track = track + " | InvalidReturnIndex[" + returnIndex + "]";
        }
      } else {
        track = track + " | WrongInput";
      }
    } catch (Exception e) {
      track = track + " | {ERROR=" + e.getMessage() + "}";
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][split] - "+e.getMessage());
    }
    track =
        track + ":ENDXSLT:split[" + inputString + "," + splitCharacter + "" +
        returnIndex + "]";
    if (logFlag) {
      System.out.println(track);
      track = null;
    }
    return result;
  }

  /*
   * This Function will be used to call the ECTH DB-Lookup (ECTH_GET_PARAMETERVALUES_PKG.ECTH_GET_PARAMETERVALUES_PRC) Procedure.
   *
   * @param dataSourceJNDI - This will be the JNDI for the Datasource of database.
   * @param jdbcURL - This will be the JDBC URL in format - jdbc:oracle:thin:@<DB_HOST_NAME>:<DB_PORT_NUMBER>:<SID> <Username> <Password>
   * @useDataSource - This is a string flag. if 'true' it will use dataSource to get Connection else it will use the JDBCUrl to get the Connection.
   * @param splitCharacter - a string on base of that <code>inputString</code> will be split.
   * @param returnIndex - number starting from 1 upto total length of <code>inputString</code>
   * @param defaultResponse - any string. If any issue in processing the request, function will return this value.
   * @return string containing [returnIndex-1]th split part
   */

  public static String ecthParameterLookup(String dataSourceJNDI,
                                          String jdbcURL,
                                          String useDataSourceFlag,
                                          String soa_caller_name,
                                          String soa_instance_id,
                                          String searchType,
                                          String lookupValue,
                                          String defaultResponse) {
    String result = defaultResponse;
    String track =
      "StartXSLT:ecthParameterLookup[" + dataSourceJNDI + "," + jdbcURL + "," +
      useDataSourceFlag + "," + soa_caller_name + "," + soa_instance_id + "," +
      searchType + "," + lookupValue + "]";
    Connection connection = null;
    CallableStatement cs = null;
    try {
      // Load the JDBC driver
      String driverName = "oracle.jdbc.driver.OracleDriver";
      String resultValue;
      boolean resultFlag;


      // Create a connection to the database
      if (useDataSourceFlag.equalsIgnoreCase("true")) {
        track = track + " | Using DataSource to get Connection[" + dataSourceJNDI +"]";
        connection = getConnection();
        //return result;
      } else {
        track = track + " | Using JDBCUrl to get Connection";
        Class.forName(driverName);
        track = track + " | JDBCDriverFound";
        String[] jdbcURL_Parts = jdbcURL.split(" ");
        String url = jdbcURL_Parts[0];
        String username = jdbcURL_Parts[1];
        String password = jdbcURL_Parts[2];
        track =
            track + " | JDBCUrl[" + url + "@" + username + "/password[" + password.length() +
            "]]";
        connection = DriverManager.getConnection(url, username, password);
      }
      track = track + " | DBConnectionCreated[" + connection + "]";
      cs =
          connection.prepareCall("{call ECTH_CUSTOM_SCHEMA.ECTH_GET_PARAMETERVALUES_PKG.ECTH_GET_PARAMETERVALUES_PRC(?,?,?,?,?,?,?,?)}");
      cs.setString(1, soa_caller_name);
      cs.setString(2, soa_instance_id);
      cs.setString(3, searchType);
      cs.setString(4, lookupValue);

      cs.registerOutParameter(5, Types.VARCHAR);
      cs.registerOutParameter(6, Types.INTEGER);
      cs.registerOutParameter(7, Types.VARCHAR);
      cs.registerOutParameter(8, Types.VARCHAR);

      resultFlag = cs.execute();
      track = track + " | ProcedureExecuted[Result=" + resultFlag + "]";

      resultValue = cs.getString(5);
      track = track + " | LookedUpValue[" + resultValue + "]";
      if (resultValue != null) {
        result = resultValue;
      }

    } catch (Exception e) {
      track = track + " | {ERROR=" + e.getMessage() + "}";
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][ecthParameterLookup] - "+e.getMessage());
    } finally {
      try {
        if (cs != null) {
          cs.close();
        }
        if (connection != null) {
          connection.close();
        }
        track = track + " | DBConnectionClosed";
      } catch (Exception e) {
        track = track + " | ClosingDBConnection{ERROR=" + e.getMessage() + "}";
        System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][ecthParameterLookup] - "+e.getMessage());
      }
    }
    track =
        track + ":ENDXSLT:ecthParameterLookup[" + dataSourceJNDI + "," + jdbcURL +
        "," + useDataSourceFlag + "," + soa_caller_name + "," +
        soa_instance_id + "," + searchType + "," + lookupValue + "]";
    if (logFlag) {
      System.out.println(track);
      track = null;
    }
    return result;
  }

  /*
   * This function is used to check the length of <code>input</code>.
   * It will return ZERO, if the length of input is between or equal any of minLength and maxLength.
   * It will return the difference in negative if length is less than minLength.
   * It will return the difference in positive if length is more than maxLength.
   *
   * @param input - String whose length has to be checked
   * @param minLength - Integer containing below threshold
   * @param maxLength - Integer containing above threshold
   * @param defaultResponse - Integer Response which will be returned if some error occurs.
   *
   * @return Integer containing 0(ZERO) or a positive/negative number
   */

  public static int lengthCheck(String input, int minLength, int maxlength,
                                int defaultResponse) {
    int result = defaultResponse;
    String track =
      "StartXSLT:lengthCheck[" + input + "," + minLength + "," + maxlength +
      "]";

    try {
      if (input != null && !("".equalsIgnoreCase(input))) {
        int length = input.length();
        if ((length >= minLength) && (length <= maxlength)) {
          result = 0;
        } else if (length < minLength) {
          result = length - minLength;
        } else {
          result = length - maxlength;
        }
        track = track + " | Result[" + result + "]";
      } else {
        track = track + " | INPUTisNULL";
      }
    } catch (Exception e) {
      track = track + " | {ERROR=" + e.getMessage() + "}";
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][lengthCheck] - "+e.getMessage());
    }

    track =
        track + ":ENDXSLT:lengthCheck[" + input + "," + minLength + "," + maxlength +
        "]";
    if (logFlag) {
      System.out.println(track);
      track = null;
    }
    return result;
  }

  /*
   * This function is used to evaluate a String value on base of its length abd conditionally return values.
   * If the inputValue String length falls in-between or equals minLength or maxLength, then inputValue will be returned as it is.
   * If the inputValu is larger than maxLength then it is trim to (maxLength-1)
   * If the inputValue is shorter than minLength then shortValue is returned.
   * If there is any kind of error while processing, then defaultResponse is returned
   *
   * @param inputValue - String to be evaluated
   * @param minLength - below threshold
   * @param maxLength - above threshold
   * @strictCheck - Integer value pass 0(ZERO) or 1(ONE). if strictCheck is ON, i.e. its ONE. shortValue will be returned for below or above threshold case.
   * @shortValue - Value to be used if string is smaller than minLength
   * @defaultResponse - String returned incase of an unexpected error.
   *
   * @return String containing final formatted Value.
   */

  public static String formatValue(String inputValue, int minLength,
                                   int maxlength, int strictCheck,
                                   String shortValue, String defaultResponse) {
    String result = defaultResponse;
    String track =
      "StartXSLT:formatValue[" + inputValue + "," + minLength + "," +
      maxlength + "," + strictCheck + "," + shortValue + "," +
      defaultResponse + "]";

    try {
      if (inputValue != null && !("".equalsIgnoreCase(inputValue))) {
        int lengthResult;
        lengthResult = lengthCheck(inputValue, minLength, maxlength, -999999);
        track = track + " | Called lengthCheck[Return=" + lengthResult + "]";
        if (lengthResult == 0) {
          result = inputValue;
        } else if (lengthResult == -999999) {
          result = defaultResponse;
        } else if ((strictCheck == 0) && (lengthResult > 0)) {
          result = inputValue.substring(0, (maxlength));
        } else {
          result = shortValue;
        }
        track = track + " | Result[" + result + "]";
      } else {
        track = track + " | InputValueisNULL";
      }
    } catch (Exception e) {
      track = track + " | {ERROR=" + e.getMessage() + "}";
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][formatValue] - "+e.getMessage());
    }

    track =
        track + ":ENDXSLT:formatValue[" + inputValue + "," + minLength + "," +
        maxlength + "," + strictCheck + "," + shortValue + "," +
        defaultResponse + "]";
    if (logFlag) {
      System.out.println(track);
      track = null;
    }
    return result;
  }


  /*
   * This function is used to truncate Input Values.
   * If the inputValue is NULL, then Empty String is returned.
   * If the inputValue is Empty String, then Empty String is returned.
   * If truncateValue is ZERO or Negative, then Empty String is returned.
   * If truncateValue is more than or equal to InputValue actual length, then InputValue is returned as it is.
   *
   * @param inputValue - String to be evaluated
   * @param truncateLength - Index after which String will be truncated. INDEX Starting is from 1.
   * @defaultResponse - String returned incase of an unexpected error.
   *
   * @return String containing final truncated Value.
   */
  public static String truncate(String inputValue, int truncateLength, String defaultResponse){
    String result="";
    try{
      if(inputValue != null){
        if(truncateLength >0){
         int inputValueLength = inputValue.length();
         if(truncateLength < inputValueLength){
           result=inputValue.substring(0, truncateLength);
         }
         else{
           result = inputValue;
         }
        }
      }
    }
    catch(Exception e){
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][truncate] - "+e.getMessage());
      result = defaultResponse;
    }
    return result;
  }
  
  /* This function will get Amount as - number, decimalPoint and Sign.
   * It will return back the actual amount figure.
   * For Example - 
   * Number(420020), decimalPoint(2) and sign(+) will return -> 4200.20
   * Number(420020), decimalPoint(0) and sign(-) will return -> -420020
   * Number(420020), decimalPoint(6) and sign(+) will return -> 0.420020
   * Number(420020), decimalPoint(10) and sign(+) will return -> defaultResponse
   * Number(4200.20), decimalPoint(2) and sign(+) will return -> defaultResponse
   * 
   * @param inputLongString - Input Amount Number
   * @param decimalPointString - palce (from right end) at where decimal should come.
   * @param sign - should be either '+' or '-'.
   * @param defaultResponse - any string. If any issue in processing the request, function will return this value.
   * @return the actual amount
   */
  public static String formatAmount(String inputLongString, String decimalPointString, String sign, String defaultResponse) {
    String result = defaultResponse;
    String track = "StartXSLT:formatAmount[" + inputLongString + "," + decimalPointString + "," + sign + "]";
    
    try{
      if(inputLongString != null && !("".equalsIgnoreCase(inputLongString)) && inputLongString.matches("[0-9]+")){
        int inputLongStringLength = inputLongString.length();
        
        if(!"-".equalsIgnoreCase(sign)){
          sign = "";
        }
        
        if(decimalPointString != null && !("".equalsIgnoreCase(decimalPointString)) && decimalPointString.matches("[0-9]+")){
          int decimalPointInt = Integer.parseInt(decimalPointString);
          if(decimalPointInt > 0){
            if(decimalPointInt < inputLongStringLength){
              int decimalPlace = (inputLongStringLength - decimalPointInt);
              result = sign+inputLongString.substring(0,decimalPlace) + "." + inputLongString.substring(decimalPlace);
            }
            else if(decimalPointInt == inputLongStringLength){
              result = sign+"0."+inputLongString;
            }
          }
          else{
            result = sign+inputLongString;
            track = track + "[| decimalPointString is either null or empty or Not an Integer"+"]";
          }
        }
      }
      else{
        track = track + "[| inputLongString is either null or empty or Not an Integer"+"]";
      }
    }
    catch(Exception e){
      track = track + " | {ERROR=" + e.getMessage() + "}";
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][formatAmount] - "+e.getMessage());
    }
    track = track + ":ENDXSLT:formatAmount[" + result + "]";

    if (logFlag) {
      System.out.println(track);
      track = null;
    }
    return result;
  }

  private static Connection getConnection(){
    Context ctx = null;
    Hashtable properties = new Hashtable();
    properties.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
    properties.put(Context.PROVIDER_URL, "t3://usmtnz-sscmwi10.stage.emrsn.org:8081");
    properties.put(Context.SECURITY_PRINCIPAL, "Inder_Dhami");
    properties.put(Context.SECURITY_CREDENTIALS, "welcome");
    Connection conn = null;

    try {
      ctx = new InitialContext(properties);
      Object dataSourceObject = ctx.lookup("jdbc/ECTH/AppDB_DS_NXA");
      System.out.println("Object - "+dataSourceObject);
      DataSource dataSource = (DataSource)dataSourceObject;
      conn = dataSource.getConnection();
      System.out.println("Connection - " + conn + " DataSource - " +dataSource);
    } catch (NamingException e) {
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][getConnection] - "+e.getMessage());
    } catch (SQLException e) {
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][getConnection] - "+e.getMessage());
    } catch (Exception e) {
      System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][getConnection] - "+e.getMessage());
    } finally {
      try {
        ctx.close();
      } catch (Exception e) {
        System.out.println("[ECTHCustomXSLTExtension][EXCEPTION][getConnection] - "+e.getMessage());
        // a failure occurred
      }
    }
    return conn;
  }
}
