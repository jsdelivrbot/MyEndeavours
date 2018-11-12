package com.gssamerica.mdm.services.handlers;

import com.gssamerica.mdm.auditlogger.MDMAudit;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.constants.XPATHConstants;
import com.gssamerica.mdm.utils.XMLUtils;
import com.gssamerica.mdm.utils.XPATHUtils;

import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

public class ReplicateDataAuditHandler implements Handler  {
    
    private QName[] headerNames = null;
    
    public ReplicateDataAuditHandler() {}
    
    public QName[] getHeaders() {
        System.out.println("[ReplicateDataAuditHandler] getHeaders method called..");
        return new QName[0];
    }
    
    public boolean handleRequest(MessageContext messageContext) {
        System.out.println("[ReplicateDataAuditHandler] handleRequest method called..");
        SOAPMessageContext soapCntxt = (SOAPMessageContext)messageContext;
        SOAPEnvelope soapEnvelope = null;
        SOAPMessage soapMsg = null;
        String envelopeString = null;
        String remoteSource = null;
        String transactionId = null;
        String userId = null;
        String xpathRemoteSource = null;
        String xpathTransactionId = null;
        String xpathUserId = null;
        MDMAudit mdmAudit = null;
        Document doc = null;
        XPATHUtils xpathUtils = null;
        XMLUtils xmlUtils = null;
        try {
            xmlUtils = new XMLUtils();
            soapMsg = soapCntxt.getMessage();
            soapEnvelope = soapMsg.getSOAPPart().getEnvelope();
            doc = soapEnvelope.getOwnerDocument();
            xpathUtils = new XPATHUtils();
            xpathRemoteSource = "//" + XPATHConstants.SOAP_ENVELOPE + "/" + 
                                  XPATHConstants.SOAP_BODY + "/" + 
                                  XPATHConstants.ReplicateDataRequest + "/" + 
                                  XPATHConstants.ConfigData + "/" + 
                                  XPATHConstants.ConfigElement + "[" + 
                                  XPATHConstants.Name + "='" + 
                                  MDMConstants.RemoteSystem + "']/" + 
                                  XPATHConstants.Value + "/text()";
            remoteSource = (String)xpathUtils.queryXMLDocument(doc,xpathRemoteSource,XPathConstants.STRING);
            
            xpathTransactionId = "//" + XPATHConstants.SOAP_ENVELOPE + "/" + 
                                  XPATHConstants.SOAP_BODY + "/" + 
                                  XPATHConstants.ReplicateDataRequest + "/" + 
                                  XPATHConstants.ConfigData + "/" + 
                                  XPATHConstants.ConfigElement + "[" + 
                                  XPATHConstants.Name + "='" + 
                                  MDMConstants.TransactionID + "']/" + 
                                  XPATHConstants.Value + "/text()";
            transactionId = (String)xpathUtils.queryXMLDocument(doc,xpathTransactionId,XPathConstants.STRING);
            
            xpathUserId = "//" + XPATHConstants.SOAP_ENVELOPE + "/" + 
                                  XPATHConstants.SOAP_BODY + "/" + 
                                  XPATHConstants.ReplicateDataRequest + "/" + 
                                  XPATHConstants.ConfigData + "/" + 
                                  XPATHConstants.ConfigElement + "[" + 
                                  XPATHConstants.Name + "='" + 
                                  MDMConstants.UserID + "']/" + 
                                  XPATHConstants.Value + "/text()";
            userId = (String)xpathUtils.queryXMLDocument(doc,xpathUserId,XPathConstants.STRING);
            
            System.out.println("[ReplicateDataAuditHandler] XPath Source - " + remoteSource);
            System.out.println("[ReplicateDataAuditHandler] XPath transactionId is - " + transactionId);
            System.out.println("[ReplicateDataAuditHandler] XPath userId is - " + userId);
            envelopeString = xmlUtils.getXMLDocument(doc);
            //System.out.println("SOAP Enevelope - "+envelopeString);
            mdmAudit = new MDMAudit();
            mdmAudit.setOldRecord(envelopeString);
            mdmAudit.setProcessName(MDMConstants.PROCESS_REPLICATEDATA_SERVICE);
            mdmAudit.setProjectName(MDMConstants.PROJECT_NAME);
            mdmAudit.setStatusFlag(MDMConstants.STATUS_PROGRESS);
            mdmAudit.setTransactionId(transactionId);
            mdmAudit.setUserId(userId);
            mdmAudit.setCreatedDateTime(Calendar.getInstance().getTime());
            mdmAudit.setSource(remoteSource);
            mdmAudit.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            mdmAudit.setDestination(MDMConstants.DEFAULT_DESTINATION);
            mdmAudit.setProcessDescription(MDMConstants.PROCESS_DESC_REPLICATEDATA_SERVICE);
            mdmAudit.setSourceDescription(MDMConstants.DEFAULT_SOURCE_DESC);
            mdmAudit.log();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean handleResponse(MessageContext messageContext) {
        System.out.println("[ReplicateDataAuditHandler] handleResponse method called..");
        return true;
    }

    public boolean handleFault(MessageContext messageContext) {
        System.out.println("[ReplicateDataAuditHandler] handleFault method called..");
        return true;
    }

    public void init(HandlerInfo handlerInfo) {
        System.out.println("[ReplicateDataAuditHandler] init method called..");
        headerNames = new QName[0];
    }

    public void destroy() {
        System.out.println("[ReplicateDataAuditHandler] destroy method called..");
    }
}
