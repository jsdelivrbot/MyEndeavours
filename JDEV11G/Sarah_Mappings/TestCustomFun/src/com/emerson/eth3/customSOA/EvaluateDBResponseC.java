package com.emerson.eth3.customSOA;

public class EvaluateDBResponseC {
  
  public static String EvaluateDBResponse(int responseCode, String errorCodeNumber, int rollbackFlag)
                                          
  {
    if (responseCode == 0)
        return "SUCCESS";
    else if (responseCode == 99 ||
             (responseCode == 98 && errorCodeNumber != "DB-999999"))
        return "DB_FAILURE";
    else
        return "FAILURE";
  }

//  public static void main(String[] args) {
//    EvaluateDBResponseC obj = new EvaluateDBResponseC();
//    String res=obj.EvaluateDBResponse(99, "ABC", 1);
//    System.out.println("============= returned value ===========    " +res );
//  }
   
}

