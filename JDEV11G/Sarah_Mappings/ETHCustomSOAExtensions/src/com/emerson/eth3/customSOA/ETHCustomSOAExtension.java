package com.emerson.eth3.customSOA;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * This class is to declare all the methods to be used inside SOA BPEL processes.
 * @author Inderpal Singh Dhami
 * @version 3.1
 */
public class ETHCustomSOAExtension {
  
  final static String SUCCESS = "SUCCESS";
  final static String FAILURE = "FAILURE";
  final static String DB_FAILURE = "DB_FAILURE";
  final static String JAVA_FAILURE = "JAVA_ERROR";
  
  final static String FILENAME_ILLEGAL_CHARACTERS_REGEXP = "[^a-zA-Z0-9\\._]";
  final static String FILENAME_REPLACEMENT_CHARACTER = "X";
  final static String FILENAME_PREFIX = "ETH_";
  final static int FILENAME_MAX_LENGTH = 100;
  
  //final static String[] SUPPORTED_ENCODINGS = {"UTF-8", "UTF8"};
  //final static String SYSTEM_FILE_ENCODING = System.getProperty("file.encoding");
  
  public ETHCustomSOAExtension() {
    super();
  }

  /**
   * This Function is used to evaluate ETH DB Procedure response. 
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
      System.out.println("[ETHCustomSOAExtension][EXCEPTION][evaluateDBResponse] - "+e.getMessage());
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
   * If NULL or "" or whitespaces are passed as file name, it will return back a random type 4 UUID prefixed with string 'ETH_'.
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
      System.out.println("[ETHCustomSOAExtension][EXCEPTION][validateFileName] - "+e.getMessage());
    }
    finally{
      fileName = null;
    }
    return validFileName;
  }
  
  
  /**
   * This Function will filter out any ISO Control Characters found in the input string.
   * If NULL or "" or Whitespaces are passed as input, same will be returned back without any alteration.
   * If there is any Java Exception encountered, orignal input will be returned back without any filteration.
   * @param inputString - A String.
   * @return filteredString - A String 
   * */
  public static String filterControlCharacters(String inputString) {
    String filteredString = inputString;
    try{
      if (inputString != null && !("".equalsIgnoreCase(inputString.trim()))) {

        StringBuffer buff = new StringBuffer("");
        int charCount = inputString.length();
        int controlCharCount = 0;
        int codePoint;
        //System.out.println(charCount);
        for (int i = 0; i < charCount; i++) {
          codePoint = inputString.codePointAt(i);
          if (Character.isISOControl(codePoint)) {
            controlCharCount++;
            //buff.append("\\ud" + codePoint);
            //char ch = inputString.charAt(i);
            //System.out.println(codePoint+" - "+ch+" - "+Character.isISOControl(codePoint)+" - "+i);
            System.out.println("[ETHCustomSOAExtension][INFO][filterControlCharacters] Found - "+controlCharCount+" Control Characters. CodePoint - "+codePoint);
          } else {
            buff.append(inputString.charAt(i));
          }
        }
        filteredString = buff.toString();
        //System.out.println(controlCharCount);
      } 
    }
    catch(Exception e){
      System.out.println("[ETHCustomSOAExtension][EXCEPTION][filterControlCharacters] - "+e.getMessage());
    }
    finally{
      inputString=null;
    }
    return filteredString;
  }


//public static void main(String[] args) {
//  //System.out.println("Result - "+evaluateDBResponse(1, "", 1));
//  //System.out.println("Result - "+validateFileName("(hs   os^.txt"));
//  //System.out.println("will ship 82pcs only pÛlease confirm");
//  System.out.println("Result - "+filterControlCharacters("will ship 82pcs only pÛlease confirm"));
//
//  //System.out.println(Charset.defaultCharset());
//  //System.out.println(System.getProperty("file.encoding"));
//  
//  
////  String s = "";
////  int codePoint = s.codePointAt(0);
////  System.out.println(codePoint+" - "+Character.isISOControl(codePoint));
////  char ch = s.charAt(0);
////  System.out.println(Character.isISOControl(ch));
//}

}
