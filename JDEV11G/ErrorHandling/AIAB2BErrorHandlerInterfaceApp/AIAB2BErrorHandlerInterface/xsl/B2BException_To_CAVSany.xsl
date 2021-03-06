<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../ProcessB2BError.wsdl"/>
      <rootElement name="Exception" namespace="http://integration.oracle.com/B2B/Exception"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="oramds:/apps/AIAMetaData/AIAComponents/EnterpriseBusinessServiceLibrary/Utilities/B2BInterfaceV1.wsdl"/>
      <rootElement name="Fault" namespace="http://xmlns.oracle.com/EnterpriseObjects/Core/Common/V2"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.0.0(build 090213.0200.2535) AT [WED APR 22 12:29:13 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:svcdoc="http://xmlns.oracle.com/Services/Documentation/V1"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/aq/AIAB2BErrorHandlerInterface/ReadOracleB2BError"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:obj1="http://xmlns.oracle.com/xdb/SH_SOAINFRA"
                xmlns:xpath20="http://www.oracle.com/XSorm/L/Transfjava/oracle.tip.pc.services.functions.Xpath20"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ns="http://schemas.xmlsoap.org/soap/encoding/"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:ns2="http://xmlns.oracle.com/EnterpriseObjects/Core/Custom/Common/V2"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns1="http://schemas.xmlsoap.org/ws/2003/03/addressing"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:orab2bexc="http://integration.oracle.com/B2B/Exception"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:ns4="urn:oasis:names:tc:xacml:2.0:policy:schema:cd:04"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:aiafault="http://xmlns.oracle.com/EnterpriseObjects/Core/Common/V2"
                xmlns:ns3="urn:oasis:names:tc:xacml:2.0:context:schema:cd:04"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl tns obj1 plt wsdl orab2bexc xsd svcdoc ns soap ns2 ns1 ns4 aiafault ns3 bpws xpath20 ora socket mhdr oraext dvm hwf med ids xdk xref ldap">
  <xsl:template match="/">
    <aiafault:Fault>
      <aiafault:B2BMReference>
        <aiafault:B2BMID>
          <xsl:value-of select="/orab2bexc:Exception/orab2bexc:b2bMessageId"/>
        </aiafault:B2BMID>
        <aiafault:GatewayID>
          <xsl:attribute name="schemeID">
            <xsl:text disable-output-escaping="no">CAVS</xsl:text>
          </xsl:attribute>
          <xsl:text disable-output-escaping="no">OracleB2B</xsl:text>
        </aiafault:GatewayID>
      </aiafault:B2BMReference>
      <aiafault:FaultNotification>
        <aiafault:FaultMessage>
          <aiafault:Code>
            <xsl:value-of select="/orab2bexc:Exception/orab2bexc:errorCode"/>
          </aiafault:Code>
          <aiafault:Text>
            <xsl:value-of select="/orab2bexc:Exception/orab2bexc:errorText"/>
          </aiafault:Text>
          <aiafault:Severity>
            <xsl:value-of select="/orab2bexc:Exception/orab2bexc:errorSeverity"/>
          </aiafault:Severity>
          <aiafault:Stack>
            <xsl:value-of select="/orab2bexc:Exception/orab2bexc:errorDescription"/>
          </aiafault:Stack>
          <aiafault:IntermediateMessageHop>
            <aiafault:Custom>
              
            </aiafault:Custom>
          </aiafault:IntermediateMessageHop>
        </aiafault:FaultMessage>
      </aiafault:FaultNotification>
    </aiafault:Fault>
  </xsl:template>
</xsl:stylesheet>
