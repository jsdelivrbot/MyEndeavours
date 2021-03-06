<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_ProcessPurchaseOrder_ETH_ProcessPO_RemoveComplianceError_RS.wsdl"/>
      <rootElement name="CustomProcessPO" namespace="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="http://D-181486:8888/orabpel/default/CommonErrorHandling/CommonErrorHandling?wsdl"/>
      <rootElement name="CommonErrorHandlingProcessRequest" namespace="http://xmlns.oracle.com/CommonErrorHandling"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [WED MAR 04 13:06:12 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:inp1="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns7="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:tns="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns6="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns3="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:ns4="http://www.openapplications.org/oagis/9/codelists"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns9="http://xmlns.oracle.com/CommonErrorHandling"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns2="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_ProcessPurchaseOrder"
                xmlns:ns10="http://xmlns.oracle.com/pcbpel/SC_Error_Details"
                xmlns:ns5="http://www.openapplications.org/oagis/9"
                xmlns:ns8="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns1="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                exclude-result-prefixes="xsl inp1 ns7 tns ns6 ns3 ns0 ns4 ns2 ns5 ns8 ns1 plnk soap ns9 ns10 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <ns9:CommonErrorHandlingProcessRequest>
      <ns9:input>
        <ns10:CommonErrorHandler>
          <ns10:activityId>
            <xsl:value-of select='string("99")'/>
          </ns10:activityId>
          <ns10:messageId>
            <xsl:value-of select="/inp1:CustomProcessPO/Message_id"/>
          </ns10:messageId>
          <ns10:sourceName>
            <xsl:value-of select='string("RS_RoutingRules")'/>
          </ns10:sourceName>
          <ns10:invokedOperationName>
            <xsl:value-of select='string("RoutingFailure")'/>
          </ns10:invokedOperationName>
          <ns10:flowId>
            <xsl:value-of select="/inp1:CustomProcessPO/Flow_id_2"/>
          </ns10:flowId>
          <ns10:message>
            <xsl:value-of select='string("Routing has failed. Please check if the supplier party ID, logical ID and version are valid and exists in the DVM")'/>
          </ns10:message>
          <ns10:exception>
            <xsl:value-of select='string("Routing Rules Exception")'/>
          </ns10:exception>
          <ns10:inPayload>
            <xsl:value-of select="orcl:get-content-as-string(/inp1:CustomProcessPO/ProcessPO)"/>
          </ns10:inPayload>
        </ns10:CommonErrorHandler>
      </ns9:input>
    </ns9:CommonErrorHandlingProcessRequest>
  </xsl:template>
</xsl:stylesheet>
