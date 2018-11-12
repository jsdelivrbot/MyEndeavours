// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.4.0, build 080709.0800.28953)

package com.tempproxy.proxy.runtime;


import oracle.j2ee.ws.common.encoding.*;
import oracle.j2ee.ws.common.soap.SOAPEncodingConstants;
import oracle.j2ee.ws.common.soap.message.SOAPFaultInfo;
import oracle.j2ee.ws.common.streaming.*;
import oracle.j2ee.ws.common.soap.SOAPVersion;
import oracle.j2ee.ws.common.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;

public class UserWebServicePortType_login_Fault_SOAPSerializer extends SOAPFaultInfoSerializer {
    private static final QName ns1_FaultDetail_QNAME = new QName("http://service.emerson.com/vdst/tool/common", "FaultDetail");
    private static final QName ns1_FaultDetailType_TYPE_QNAME = new QName("http://service.emerson.com/vdst/tool/common", "FaultDetailType");
    private CombinedSerializer myns1_FaultDetailType__FaultDetailType_LiteralSerializer;
    private CombinedSerializer myns1_FaultDetailType__FaultDetailType_LiteralSerializer_Serializer;
    private static final QName ns1_SOAPException_QNAME = new QName("http://service.emerson.com/vdst/tool/common", "SOAPException");
    private static final QName ns1_SOAPException_TYPE_QNAME = new QName("http://service.emerson.com/vdst/tool/common", "SOAPException");
    private CombinedSerializer myns1_SOAPException__SOAPException_LiteralSerializer;
    private CombinedSerializer myns1_SOAPException__SOAPException_LiteralSerializer_Serializer;
    private static final int com_tempproxy_proxy_types_com_emerson_service_vdst_tool_common_FaultDetailType_INDEX = 0;
    private static final int com_tempproxy_proxy_types_com_emerson_service_vdst_tool_common_SOAPException_INDEX = 1;
    
    public UserWebServicePortType_login_Fault_SOAPSerializer(boolean encodeType, boolean isNullable, SOAPVersion soapVersion) {
        super(encodeType, isNullable, soapVersion);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        super.initialize(registry);
        myns1_FaultDetailType__FaultDetailType_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.tempproxy.proxy.types.com.emerson.service.vdst.tool.common.FaultDetailType.class, ns1_FaultDetailType_TYPE_QNAME);
        myns1_FaultDetailType__FaultDetailType_LiteralSerializer_Serializer = myns1_FaultDetailType__FaultDetailType_LiteralSerializer.getInnermostSerializer();
        myns1_SOAPException__SOAPException_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.tempproxy.proxy.types.com.emerson.service.vdst.tool.common.SOAPException.class, ns1_SOAPException_TYPE_QNAME);
        myns1_SOAPException__SOAPException_LiteralSerializer_Serializer = myns1_SOAPException__SOAPException_LiteralSerializer.getInnermostSerializer();
    }
    
    protected Object deserializeDetail(SOAPDeserializationState state, XMLReader reader,
        SOAPDeserializationContext context, SOAPFaultInfo instance) throws Exception {
        boolean isComplete = true;
        QName elementName;
        QName elementType = null;
        SOAPInstanceBuilder builder = null;
        Object detail = null;
        Object obj = null;
        
        reader.nextElementContent();
        if (reader.getState() == XMLReader.END)
            return deserializeDetail(reader, context);
        XMLReaderUtil.verifyReaderState(reader, XMLReader.START);
        elementName = reader.getName();
        elementType = getType(reader);
        if (elementName.getLocalPart().equals(ns1_FaultDetail_QNAME.getLocalPart())) {
            if (elementType == null || 
                (elementType.equals(myns1_FaultDetailType__FaultDetailType_LiteralSerializer.getXmlType()) ||
                (myns1_FaultDetailType__FaultDetailType_LiteralSerializer instanceof ArraySerializerBase &&
                (elementType.equals(SOAPEncodingConstants.getSOAPEncodingConstants(SOAPVersion.SOAP_11).getQNameEncodingArray()) ||
                 elementType.equals(SOAPEncodingConstants.getSOAPEncodingConstants(SOAPVersion.SOAP_12).getQNameEncodingArray())) ) ) ){
                obj = myns1_FaultDetailType__FaultDetailType_LiteralSerializer.deserialize(ns1_FaultDetail_QNAME, reader, context);
                detail = obj;
                reader.nextElementContent();
                if( reader.getState() != XMLReader.END)
                {
                    reader.skipElement();
                }
                XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
                return (isComplete ? (Object)detail : (Object)state);
            } 
        } else if (elementName.getLocalPart().equals(ns1_SOAPException_QNAME.getLocalPart())) {
            if (elementType == null || 
                (elementType.equals(myns1_SOAPException__SOAPException_LiteralSerializer.getXmlType()) ||
                (myns1_SOAPException__SOAPException_LiteralSerializer instanceof ArraySerializerBase &&
                (elementType.equals(SOAPEncodingConstants.getSOAPEncodingConstants(SOAPVersion.SOAP_11).getQNameEncodingArray()) ||
                 elementType.equals(SOAPEncodingConstants.getSOAPEncodingConstants(SOAPVersion.SOAP_12).getQNameEncodingArray())) ) ) ){
                obj = myns1_SOAPException__SOAPException_LiteralSerializer.deserialize(ns1_SOAPException_QNAME, reader, context);
                detail = obj;
                reader.nextElementContent();
                if( reader.getState() != XMLReader.END)
                {
                    reader.skipElement();
                }
                XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
                return (isComplete ? (Object)detail : (Object)state);
            } 
        }
        return deserializeDetail(reader, context);
    }
    
    protected void serializeDetail(Object detail, XMLWriter writer, SOAPSerializationContext context)
        throws Exception {
        if (detail == null) {
            throw new SerializationException("soap.unexpectedNull");
        }
        if(context.getSOAPVersion().equals(SOAPVersion.SOAP_11)){
            writer.startElement(DETAIL_QNAME);
        }
        else{
            writer.startElement(DETAIL12_QNAME);
        }
        
        boolean pushedEncodingStyle = false;
        if (encodingStyle != null) {
            context.pushEncodingStyle(encodingStyle, writer);
        }
        if (detail instanceof com.tempproxy.proxy.types.com.emerson.service.vdst.tool.common.FaultDetailType) {
            myns1_FaultDetailType__FaultDetailType_LiteralSerializer_Serializer.serialize(detail, ns1_FaultDetail_QNAME, null, writer, context);
        }
        else if (detail instanceof com.tempproxy.proxy.types.com.emerson.service.vdst.tool.common.SOAPException) {
            myns1_SOAPException__SOAPException_LiteralSerializer_Serializer.serialize(detail, ns1_SOAPException_QNAME, null, writer, context);
        }
        writer.endElement();
        if (pushedEncodingStyle) {
            context.popEncodingStyle();
        }
    }
}