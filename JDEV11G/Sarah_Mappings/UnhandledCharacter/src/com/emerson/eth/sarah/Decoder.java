package com.emerson.eth.sarah;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.io.*;
import oracle.soa.common.util.Base64Decoder;
import oracle.soa.common.util.Base64Encoder;

public class Decoder {
  public Decoder() {
    super();
  }
  
  public static void main(String[] args) {
    String originalMessage = "<Head xmlns=\"http://schema.emerson.com/FL_GET_UnhandledCharacter\">\n" + 
    "	<Line>\n" + 
    "		<COLUMN1>Prag</COLUMN1>\n" + 
    "		<COLUMN2>Customer Number +420 234 262 000</COLUMN2>\n" + 
    "		<COLUMN3>This is the note from éèçà</COLUMN3>\n" + 
    "	</Line>\n" + 
    "</Head>";
   
    String System_base64string1 = "PEhlYWQgeG1sbnM9Imh0dHA6Ly9zY2hlbWEuZW1lcnNvbi5jb20vRkxfR0VUX1VuaGFuZGxlZENoYXJhY3RlciI+Cgk8TGluZT4KCQk8Q09MVU1OMT5QcmFnPC9DT0xVTU4xPgoJCTxDT0xVTU4yPkN1c3RvbWVyIE51bWJlciArNDIwIDIzNCAyNjIgMDAwPC9DT0xVTU4yPgoJCTxDT0xVTU4zPlRoaXMgaXMgdGhlIG5vdGUgZnJvbSDp6OfgPC9DT0xVTU4zPgoJPC9MaW5lPgo8L0hlYWQ+Cg==";
    String System_base64string2 = "PEhlYWQgeG1sbnM9Imh0dHA6Ly9zY2hlbWEuZW1lcnNvbi5jb20vRkxfR0VUX1VuaGFuZGxlZENoYXJhY3RlciI+Cgk8TGluZT4KCQk8Q09MVU1OMT5QcmFnPC9DT0xVTU4xPgoJCTxDT0xVTU4yPkN1c3RvbWVyIE51bWJlciArNDIwIDIzNCAyNjIgMDAwPC9DT0xVTU4yPgoJCTxDT0xVTU4zPlRoaXMgaXMgdGhlIG5vdGUgZnJvbSDDg8Kpw4PCqMODwqfDg8KgPC9DT0xVTU4zPgoJPC9MaW5lPgo8L0hlYWQ+";
    String System_base64string3 = "PEhlYWQgeG1sbnM9Imh0dHA6Ly9zY2hlbWEuZW1lcnNvbi5jb20vRkxfR0VUX1VuaGFuZGxlZENoYXJhY3RlciI+PExpbmU+PENPTFVNTjE+UHJhZzwvQ09MVU1OMT48Q09MVU1OMj5DdXN0b21lciBOdW1iZXIgKzQyMCAyMzQgMjYyIDAwMDwvQ09MVU1OMj48Q09MVU1OMz5UaGlzIGlzIHRoZSBub3RlIGZyb20gw6nDqMOnw6A8L0NPTFVNTjM+PC9MaW5lPjwvSGVhZD4=";
    try{
      System.out.println(System.setProperty("file.encoding","UTF8"));
      //System.out.println(System_base64string);
      //System_base64string1 = Base64Encoder.encode(originalMessage);
      //System.out.println(System_base64string1);
      String decoded = Base64Decoder.decode(System_base64string3);
      System.out.println(decoded);
    }
    catch(Exception e){
      e.printStackTrace();
    }
    
  }
}
