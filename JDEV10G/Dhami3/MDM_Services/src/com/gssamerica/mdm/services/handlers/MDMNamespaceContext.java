package com.gssamerica.mdm.services.handlers;

import com.gssamerica.mdm.constants.XPATHConstants;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class MDMNamespaceContext implements NamespaceContext{
    public String getNamespaceURI(String prefix) {
        if (prefix == null) throw new NullPointerException("Null prefix");
        else if("soap".equalsIgnoreCase(prefix)){
            return "http://schemas.xmlsoap.org/soap/envelope/";
        }
        else if("imp1".equalsIgnoreCase(prefix)){
            return "http://www.gssamerica.com/mdm/services";
        }
        else if("ns1".equalsIgnoreCase(prefix)){
            return "http://www.gssamerica.com/mdm/services/MDMDeleteRecord/";
        }
        return XMLConstants.NULL_NS_URI;
    }

    public String getPrefix(String namespaceURI) {
        return null;
    }

    public Iterator getPrefixes(String namespaceURI) {
        return null;
    }
}
