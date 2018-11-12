package com.emerson.inder.handler;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPException;

public class ServiceHandler implements Handler{
    private static QName[] headers = null;
    public ServiceHandler() {
    System.out.println("Service Handler Called...");
    }

    public boolean handleRequest(MessageContext messageContext) {
        System.out.println("ServiceHandler handleRequest");
        SOAPMessageContext soapMessageContext = (SOAPMessageContext)messageContext;
        try {
            System.out.println(soapMessageContext.getMessage().getSOAPPart().getEnvelope().toString());
        } catch (SOAPException e) {
            System.out.println("ServiceHandler - "+e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public boolean handleResponse(MessageContext messageContext) {
        return true;
    }

    public boolean handleFault(MessageContext messageContext) {
        return true;
    }

    public void init(HandlerInfo handlerInfo) {
        headers = handlerInfo.getHeaders();
    }

    public void destroy() {
    }

    public QName[] getHeaders() {
        return headers;
    }
}
