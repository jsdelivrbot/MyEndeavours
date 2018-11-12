package com.assignment.stocks;

public class InderTemp {
  public InderTemp() {
    super();
  }
  
  public static void main(String[] args) {
    String lvarErrorRegistrationAttemptCount = "10a";
    /*if(lvarErrorRegistrationAttemptCount != null){
      lvarErrorRegistrationAttemptCount = lvarErrorRegistrationAttemptCount.trim();
      if(("".compareToIgnoreCase(lvarErrorRegistrationAttemptCount) == 0) || !(lvarErrorRegistrationAttemptCount.matches("[0-9]*"))){
        System.out.println("1");
      }
      else{
        System.out.println(Integer.parseInt(lvarErrorRegistrationAttemptCount)+1); 
      }
    }
    else{
        System.out.println("1");
    }
  }*/
  /*  
    String lvarOriginalErrorSourceType = null;
    lvarOriginalErrorSourceType = "";  
        if(lvarOriginalErrorSourceType == null || ("".compareToIgnoreCase(lvarOriginalErrorSourceType.trim()) == 0)){  
            lvarNewErrorSourceType = getPreference("ERROR_STOPPED"); 
        } 
        else if(("SOA_WARNING".compareToIgnoreCase(lvarOriginalErrorSourceType.trim()) == 0) ||
                ("DEAD".compareToIgnoreCase(lvarOriginalErrorSourceType.trim()) == 0) ||
                ("DEAD_WARNING".compareToIgnoreCase(lvarOriginalErrorSourceType.trim()) == 0)){
            lvarNewErrorSourceType = getPreference("ERROR_STOPPED");
        } 
        else{ 
            lvarNewErrorSourceType = "ERROR_STOPPED"; 
         
        } 
    
    
  */
    String normalizedMessagePayload = "<SENDER_ID xsi:nil=\"true\"/><B2BINBOUND_RECORD_ID xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ns=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"ns:string\"/><DOCUMENTKEY xsi:nil=\"true\"/><PAYLOAD></PAYLOAD>";
  //normalizedMessagePayload = normalizedMessagePayload.replaceAll("\\b xsi\\b:\\bnil\\b=\"\\btrue\"\\B", "");
    normalizedMessagePayload = normalizedMessagePayload.replaceAll("<PAYLOAD[\\S\\s]*</PAYLOAD>", "<PAsYLOAD/>");
    //normalizedMessagePayload = normalizedMessagePayload.replaceAll(" xsi:nil=\"true\"", "");
  System.out.println(normalizedMessagePayload);
  
//    StringBuffer decodedMessagePayloadBuffer;
//    decodedMessagePayloadBuffer = new StringBuffer(normalizedMessagePayload);
//     
//      int startLength = decodedMessagePayloadBuffer.indexOf("<PAYLOAD>");     
//      int endLength = decodedMessagePayloadBuffer.indexOf("</PAYLOAD>");             
//      decodedMessagePayloadBuffer.replace(startLength+9, endLength  , ""); 
//      System.out.println(startLength+" "+endLength);
//    normalizedMessagePayload = decodedMessagePayloadBuffer.toString();
//    System.out.println(normalizedMessagePayload);
  /*boolean s = false;
  
    try{
      
    }
    catch(Exception e){
      e.printStackTrace();
    }
    if(lvarErrorRegistrationAttemptCount == null || ("".compareToIgnoreCase(lvarErrorRegistrationAttemptCount.trim()) == 0) || !(lvarErrorRegistrationAttemptCount.trim().matches("[0-9]*"))){
      System.out.println("1");
    }
    else{
      System.out.println(Integer.parseInt(lvarErrorRegistrationAttemptCount.trim())+1); 
    }*/
  }
  
}
