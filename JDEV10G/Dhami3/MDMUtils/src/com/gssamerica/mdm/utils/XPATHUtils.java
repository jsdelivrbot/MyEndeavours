package com.gssamerica.mdm.utils;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XPATHUtils {
    
    private XPathFactory xpathFactory = null;
    private XPath xpath = null;
        
    public XPATHUtils(){
        this.xpathFactory = XPathFactory.newInstance();
        this.xpath = xpathFactory.newXPath();
    }
    
    public Object queryXMLDocument(Document document, String xpathExpressionString, QName xpathReturnType) throws XPathExpressionException {
        return queryXMLDocument(document,xpathExpressionString,xpathReturnType,(new MDMXpathNSContext()));
    }
    
    public Object queryXMLDocument(Document document, String xpathExpressionString, QName xpathReturnType, NamespaceContext namespaceContext) throws XPathExpressionException {
        
        XPathExpression xpathExpression = null;
        Object obj = null;
        
        this.xpath.setNamespaceContext(namespaceContext);
        xpathExpression = this.xpath.compile(xpathExpressionString);
        obj = xpathExpression.evaluate(document,xpathReturnType);
        return obj;
    }
}
