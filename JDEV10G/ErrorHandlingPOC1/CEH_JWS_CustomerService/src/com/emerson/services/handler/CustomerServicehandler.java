package com.emerson.services.handler;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPException;

public class CustomerServicehandler implements Handler {
    private QName[] headers = new QName[0];
    public CustomerServicehandler() {
        System.out.println("["+this.getClass()+"]: "+"Handler Called.");
    }

    public boolean handleRequest(MessageContext messageContext) {
        System.out.println("["+this.getClass()+"]: "+"Handler Request");
        
        System.out.println("["+this.getClass()+"]: "+"Envelope:");
        try {
            SOAPMessageContext soapMsgCtx = (SOAPMessageContext)messageContext;
            System.out.println(soapMsgCtx.getMessage().getSOAPPart().getEnvelope());
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean handleResponse(MessageContext messageContext) {
        System.out.println("["+this.getClass()+"]: "+"Handler Response");
        
        System.out.println("["+this.getClass()+"]: "+"Envelope:");
        try {
            SOAPMessageContext soapMsgCtx = (SOAPMessageContext)messageContext;
            System.out.println(soapMsgCtx.getMessage().getSOAPPart().getEnvelope());
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean handleFault(MessageContext messageContext) {
        return true;
    }

    public void init(HandlerInfo handlerInfo) {
        System.out.println("["+this.getClass()+"]: "+"Handler Initiated");
    }

    public void destroy() {
    }

    public QName[] getHeaders() {
        return headers;
    }
}
