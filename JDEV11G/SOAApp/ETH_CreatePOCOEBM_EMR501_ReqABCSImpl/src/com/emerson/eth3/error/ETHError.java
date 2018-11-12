package com.emerson.eth3.error;

import java.util.List;

import javax.xml.ws.Response;

import oracle.fabric.common.xml.xpath.IXPathContext;
import oracle.fabric.common.xml.xpath.IXPathFunction;
import oracle.fabric.common.xml.xpath.XPathFunctionException;

public class ETHError implements IXPathFunction {
  Integer responseCode;
  Integer errorNumber;
  String response;
    public ETHError() {
        super();
    }

    public Object call(IXPathContext iXPathContext,
                       List list) throws XPathFunctionException {
      if(list.size()==2) {
          if(list.get(0) instanceof String) {
            return getResponse((String )list.get(0));
          }
      }
      return null;
    }
      public String getResponse(String responseCode) {
        if(responseCode.equals("0"))
            return "Success";
        return null;
      }
}



