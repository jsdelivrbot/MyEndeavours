package com.gssamerica.mdm.utils;

import com.gssamerica.mdm.constants.XPATHConstants;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class MDMXpathNSContext implements NamespaceContext{

    public String getNamespaceURI(String prefix) {
        if (prefix == null) throw new NullPointerException("Null prefix");
        else if(XPATHConstants.SOAP_PREFIX.equalsIgnoreCase(prefix)){
            return "http://schemas.xmlsoap.org/soap/envelope/";
        }
        else if(XPATHConstants.ESB_SOAP_PREFIX.equalsIgnoreCase(prefix)){
            return "http://schemas.xmlsoap.org/soap/envelope/";
        }
        else if(XPATHConstants.MDM_SERVICE_PREFIX.equalsIgnoreCase(prefix)){
            return "http://www.gssamerica.com/mdm/services";
        }
        else if(XPATHConstants.MDM_DELETESERVICE_PREFIX.equalsIgnoreCase(prefix)){
            return "http://www.gssamerica.com/mdm/services/MDMDeleteRecord/";
        }
        else if(XPATHConstants.WORKFLOW_SERVICE_PREFIX.equalsIgnoreCase(prefix)){
            return "http://www.gssamerica.com/mdm/workflowservice";
        }
        else if(XPATHConstants.ESB_PROCESS_CUSTOMER_PREFIX.equalsIgnoreCase(prefix)){
            return "http://www.gssamerica.com/mdm/esbprocesscustomer";
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
