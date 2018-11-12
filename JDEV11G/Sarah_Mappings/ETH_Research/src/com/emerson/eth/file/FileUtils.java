package com.emerson.eth.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.sql.Time;

import oracle.soa.common.util.Base64Decoder;
import oracle.soa.common.util.Base64Encoder;

public class FileUtils {
  public FileUtils() {
    super();
  }

  private static String readFileByCharacter(String filePath, String fileName) {
    File file = new File(filePath + fileName);
    int ch;
    StringBuffer strContent = new StringBuffer("");
    FileInputStream fin = null;
    try {
      fin = new FileInputStream(file);
      while ((ch = fin.read()) != -1) {
        strContent.append((char)ch);
        //strContent.append(Integer.toBinaryString(ch));
      }
      fin.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return strContent.toString();
  }

  private static String readFileByLine(String filePath, String fileName) {
    StringBuffer fileContent = new StringBuffer("");
    try {
      FileInputStream fstream = new FileInputStream(filePath + fileName);
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String str;
      while ((str = br.readLine()) != null) {
        fileContent.append(str);
      }
      in.close();
    } catch (Exception e) {
      System.err.println(e);
    }
    return fileContent.toString();
  }

  private static String readFileByBytes(String filePath, String fileName) {
    File file = new File(filePath + fileName);
    String fileContent = null;
    FileInputStream fin = null;
    byte inputBytes[] = new byte[1024];
    try {
      fin = new FileInputStream(file);
      fin.read(inputBytes);
      fileContent = new String(inputBytes, "UTF8");
    } catch (Exception e) {
      System.out.println(e);
    }
    return fileContent;
  }

  private static String base64Encode(String content) throws UnsupportedEncodingException,
                                                            Exception {
    return Base64Encoder.encode(content);
  }

  private static String base64Decode(String encodedContent) throws UnsupportedEncodingException {
    return Base64Decoder.decode(encodedContent);
  }

  private static String filterControlCharacters(String inputString) {
    String filteredString = "";
    if (inputString != null && !("".equalsIgnoreCase(inputString))) {
      StringBuffer buff = new StringBuffer("");
      int charCount = inputString.length();
      int controlCharCount = 0;
      int codePoint;
      System.out.println(charCount);
      for (int i = 0; i < charCount; i++) {
        codePoint = inputString.codePointAt(i);
        if (Character.isISOControl(codePoint)) {
          controlCharCount++;
          char ch = inputString.charAt(i);
          System.out.println(codePoint+" - "+ch+" - "+Character.isISOControl(codePoint)+" - "+i);
          buff.append("\\ud" + codePoint);
        } else {
          buff.append(inputString.charAt(i));
        }
      }
      filteredString = buff.toString();
      System.out.println(controlCharCount);
    }
    return filteredString;
  }
  
  private static String getFileEncoding(String filePath, String fileName){
    String encoding = "";
    InputStreamReader r = null;
    File in = null;
    try {
      in =  new File(filePath+fileName);
      r = new InputStreamReader(new FileInputStream(in));
      encoding = r.getEncoding();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    finally{
      try {
        if(r!=null)
        r.close();
      } catch (IOException e) {
      }
    }
       
    return encoding;
  }

  public static void main(String[] args) throws UnsupportedEncodingException,
                                                Exception {
    String result = null;
    System.out.println(System.getProperty("file.encoding"));

    /*result = FileUtils.readFileByCharacter("C:/","test.txt");
    System.out.println("Result1 - "+result);*/

    /*result = FileUtils.readFileByBytes("C:/","test.txt");
    System.out.println("Result2 - "+result);*/
   
    result = FileUtils.readFileByLine("C:/TestFiles/", "EMR501_ETH_20111122_13204961_org.txt");
    //System.out.println("Result3 - " + result);

    result = filterControlCharacters(result);
    System.out.println("Result Fileterd - "+result);
    
    
    result = getFileEncoding("C:/TestFiles/", "EMR501_ETH_20111122_13204961_org.txt");

    System.out.println("File Encoding - "+result);

    //result = base64Encode(result);
    //System.out.println("Encoded Message - " + result);

    //result = base64Decode(result);
    //System.out.println("Decoded Message - " + result);
  }
}
