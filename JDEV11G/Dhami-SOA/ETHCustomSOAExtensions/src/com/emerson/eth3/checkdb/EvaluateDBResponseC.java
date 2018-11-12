package com.emerson.eth3.checkdb;

import java.util.ArrayList;
import java.util.List;

import oracle.fabric.common.xml.xpath.IXPathContext;
import oracle.fabric.common.xml.xpath.IXPathFunction;
import oracle.fabric.common.xml.xpath.XPathFunctionException;



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
public class EvaluateDBResponseC implements IXPathFunction {
  
  final String success = "SUCCESS";
  final String failure = "FAILURE";
  final String db_failure = "DB_FAILURE";
  
  public EvaluateDBResponseC() {
    super();
  }
  
  public Object call(IXPathContext context,
                     List funArgs)throws XPathFunctionException {
      int responseCode = (Integer)funArgs.get(0);
      String errorCodeNumber = (String)funArgs.get(1);
      int rollbackFlag = (Integer)funArgs.get(2);
      if (funArgs.size() != 3) {
       throw new XPathFunctionException("This custom XPath function requires exactly three parameters");
     //return "Exception";
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
//  public static void main(String[] args) {
//      
//      try{
//          EvaluateDBResponseC obj = new EvaluateDBResponseC();
//          List l= new ArrayList(); 
//          l.add(0);
//          l.add("abc");
//       // l.add(1);
//         // String res = obj.call( null, l);
//          System.out.println("============= returned value ===========    " +
//                             obj.call( null, l));
//      }
//      catch (Exception e){
//         System.out.println("============= exception  ===========    "+e);
//      }
//      
//      }
}
