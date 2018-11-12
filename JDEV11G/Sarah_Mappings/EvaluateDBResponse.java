package com.emerson.eth3.customSOA;

import java.util.List;

import javax.xml.xpath.XPathFunctionException;

import oracle.fabric.common.xml.xpath.IXPathContext;
import oracle.fabric.common.xml.xpath.IXPathFunction;


/**
 * This Function is used to evaluate ETH DB Procedure response. 
 * If the ResponseCode is 0(ZERO) then it will return 'SUCCESS'.
 * If the ResponseCode is 99 OR (98 with ErrorCodeNumber != 999999) then it will return 'DB_FAILURE'
 * If the ResponseCode is anything else, it will return 'FAILURE'
 * @param responseCode - A Number
 * @param errorCodeNumber - A String.
 * @param rollBackFlag - A Number. Not used as of now, but mandatory to pass.
 * @return responseFlag - A String 
 * */
public class EvaluateDBResponse  {
  
  final String success = "SUCCESS";
  final String failure = "FAILURE";
  final String db_failure = "DB_FAILURE";
  
  public EvaluateDBResponse() {
    super();
  }
  
  @SuppressWarnings("unchecked")
  public Object call(IXPathContext context, List funArgs) throws XPathFunctionException {
      int responseCode = (Integer)funArgs.get(0);
      String errorCodeNumber = (String)funArgs.get(1);
      int rollbackFlag = (Integer)funArgs.get(2);
      if (funArgs.size() != 3) {
         throw new XPathFunctionException("EvaluateDBResponse custom XPath function requires exactly three parameters");
      } else {
          if (responseCode == 0)
              return success;
          else if (responseCode == 99 ||
                   (responseCode == 98 && errorCodeNumber != "DB-999999"))
              return db_failure;
          else
              return failure;
      }
  }
  
  public static void main(String[] args) {
    EvaluateDBResponse e = new EvaluateDBResponse();
    //e.call(context, funArgs);
    
    //IXPathContext x = 
  }
}
