package com.emerson.epth.customSOA;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * This class is to declare all the methods to be used inside SOA BPEL processes.
 * @author Inderpal Singh Dhami
 * @version 1.0
 */
public class EPTHCustomSOAExtension {
  
  final static String SUCCESS = "SUCCESS";
  final static String FAILURE = "FAILURE";
  final static String DB_FAILURE = "DB_FAILURE";
  final static String JAVA_FAILURE = "JAVA_ERROR";
  
  final static String FILENAME_ILLEGAL_CHARACTERS_REGEXP = "[^a-zA-Z0-9\\._]";
  final static String FILENAME_REPLACEMENT_CHARACTER = "X";
  final static String FILENAME_PREFIX = "EPTH_";
  final static int FILENAME_MAX_LENGTH = 100;
  
  public EPTHCustomSOAExtension() {
    super();
  }

  /**
   * This Function is used to evaluate EPTH DB Procedure response. 
   * If the ResponseCode is 0(ZERO) then it will return 'SUCCESS'.
   * If the ResponseCode is 99 OR (98 with ErrorCodeNumber not starting with SOA or B2B or DB) then it will return 'DB_FAILURE'
   * If the ResponseCode is anything else, it will return 'FAILURE'
   * If there is an exception in the function, it will return 'JAVA_ERROR'
   * @param responseCode - A Number
   * @param errorCodeNumber - A String.
   * @param rollbackFlag - A Number. Not used as of now, but mandatory to pass.
   * @return responseFlag - A String 
   * */
  public static String evaluateDBResponse(int responseCode,
                                          String errorCodeNumber,
                                          int rollbackFlag)

  {
    String errorNumber = (errorCodeNumber==null)?"":errorCodeNumber.trim();
    try{
      if (responseCode == 0)
        return SUCCESS;
      else if (responseCode == 99)
        return DB_FAILURE;
      else if (responseCode == 98 && !errorNumber.equalsIgnoreCase("") && !(errorNumber.startsWith("DB") || errorNumber.startsWith("SOA") || errorNumber.startsWith("B2B")))
        return DB_FAILURE;
      else
        return FAILURE;
    }
    catch(Exception e){
      System.out.println("[EPTHCustomSOAExtension][EXCEPTION][evaluateDBResponse] - "+e.getMessage());
      return JAVA_FAILURE;
    }
    finally{
      errorCodeNumber = null;
    }
  }
  
  /**
   * This Function will validate a fileName and return back the validated file name. 
   * A file name is not valid if it has any character other than Alphanumeric, .(period) and _(underscore).
   * This function will replace any illegal character found, with a replacement character.
   * We are using 'X' as replacement character.
   * If no Illegal chracters are found, the file name is returned as it is.
   * If NULL or "" or whitespaces are passed as file name, it will return back a random type 4 UUID prefixed with string 'EPTH_'.
   * If the filename passed is over 100 characters, it will be truncated to 100 and return back.
   * If there is any Java Exception encountered, orignal file name will be returned back without any change.
   * @param fileName - A String.
   * @return validFileName - A String
   * */
  public static String validateFileName(String fileName){
    String validFileName = (fileName==null || "".equalsIgnoreCase(fileName.trim()))?(FILENAME_PREFIX+UUID.randomUUID().toString()):fileName;
    try{
      if(fileName!=null && !("".equalsIgnoreCase(fileName.trim()))){
        fileName = (fileName.length()>FILENAME_MAX_LENGTH)?fileName.substring(0, FILENAME_MAX_LENGTH):fileName;
        validFileName = fileName.replaceAll(FILENAME_ILLEGAL_CHARACTERS_REGEXP, FILENAME_REPLACEMENT_CHARACTER);
      } 
    }
    catch(Exception e){
      System.out.println("[EPTHCustomSOAExtension][EXCEPTION][validateFileName] - "+e.getMessage());
    }
    finally{
      fileName = null;
    }
    return validFileName;
  }
}
