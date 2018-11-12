package com.gssamerica.mdm.services.handlers;

import com.gssamerica.mdm.auditlogger.JMSAdapter;
import com.gssamerica.mdm.auditlogger.MDMAudit;
import com.gssamerica.mdm.auditlogger.castor.AuditData;

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

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

public class SearchServiceAuditHandler implements Handler {
    private QName[] headerNames = null;

    public SearchServiceAuditHandler() {
    }

    public QName[] getHeaders() {
        System.out.println("[SearchServiceAuditHandler] getHeaders method called..");
        return new QName[0];
    }

    public boolean handleRequest(MessageContext messageContext) {
        System.out.println("[SearchServiceAuditHandler] handleRequest method called..");
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
            //System.out.println("[SearchServiceAuditHandler] Audit Msg before Transforming - "+soapEnvelope.toString());
            doc = soapEnvelope.getOwnerDocument();
            xpathUtils = new XPATHUtils();
            xpathRemoteSource = "//" + XPATHConstants.SOAP_ENVELOPE + "/" + 
                                  XPATHConstants.SOAP_BODY + "/" + 
                                  XPATHConstants.SearchRecordRequest + "/" + 
                                  XPATHConstants.ConfigData + "/" + 
                                  XPATHConstants.ConfigElement + "[" + 
                                  XPATHConstants.Name + "='" + 
                                  MDMConstants.RemoteSystem + "']/" + 
                                  XPATHConstants.Value + "/text()";
            remoteSource = (String)xpathUtils.queryXMLDocument(doc,xpathRemoteSource,XPathConstants.STRING);
            
            xpathTransactionId = "//" + XPATHConstants.SOAP_ENVELOPE + "/" + 
                                  XPATHConstants.SOAP_BODY + "/" + 
                                  XPATHConstants.SearchRecordRequest + "/" + 
                                  XPATHConstants.ConfigData + "/" + 
                                  XPATHConstants.ConfigElement + "[" + 
                                  XPATHConstants.Name + "='" + 
                                  MDMConstants.TransactionID + "']/" + 
                                  XPATHConstants.Value + "/text()";
            transactionId = (String)xpathUtils.queryXMLDocument(doc,xpathTransactionId,XPathConstants.STRING);
            
            xpathUserId = "//" + XPATHConstants.SOAP_ENVELOPE + "/" + 
                                  XPATHConstants.SOAP_BODY + "/" + 
                                  XPATHConstants.SearchRecordRequest + "/" + 
                                  XPATHConstants.ConfigData + "/" + 
                                  XPATHConstants.ConfigElement + "[" + 
                                  XPATHConstants.Name + "='" + 
                                  MDMConstants.UserID + "']/" + 
                                  XPATHConstants.Value + "/text()";
            userId = (String)xpathUtils.queryXMLDocument(doc,xpathUserId,XPathConstants.STRING);

            System.out.println("[SearchServiceAuditHandler] XPath Source - " + remoteSource);
            System.out.println("[SearchServiceAuditHandler] XPath transactionId is - " + transactionId);
            System.out.println("[SearchServiceAuditHandler] XPath userId is - " + userId);
            envelopeString = xmlUtils.getXMLDocument(doc);
            //System.out.println("[SearchServiceAuditHandler] Audit Msg after Transforming - "+envelopeString);
            mdmAudit = new MDMAudit();
            mdmAudit.setOldRecord(envelopeString);
            mdmAudit.setProcessName(MDMConstants.PROCESS_SEARCHRECORD_SERVICE);
            mdmAudit.setProjectName(MDMConstants.PROJECT_NAME);
            mdmAudit.setStatusFlag(MDMConstants.STATUS_PROGRESS);
            mdmAudit.setTransactionId(transactionId);
            mdmAudit.setUserId(userId);
            mdmAudit.setCreatedDateTime(Calendar.getInstance().getTime());
            mdmAudit.setSource(remoteSource);
            mdmAudit.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            mdmAudit.setDestination(MDMConstants.DEFAULT_DESTINATION);
            mdmAudit.setProcessDescription(MDMConstants.PROCESS_DESC_SEARCHRECORD_SERVICE);
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
        System.out.println("[SearchServiceAuditHandler] handleResponse method called..");
//        SOAPMessageContext soapCntxt = (SOAPMessageContext)messageContext;
//        try {
//            System.out.println("[SearchServiceAuditHandler] Response - "+soapCntxt.getMessage().getSOAPPart().getEnvelope().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return true;
    }

    public boolean handleFault(MessageContext messageContext) {
        System.out.println("[SearchServiceAuditHandler] handleFault method called..");
        return true;
    }

    public void init(HandlerInfo handlerInfo) {
        System.out.println("[SearchServiceAuditHandler] init method called..");
        headerNames = new QName[0];
    }

    public void destroy() {
        System.out.println("[SearchServiceAuditHandler] destroy method called..");
    }
}
