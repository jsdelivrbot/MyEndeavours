<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_ProcessPurchaseOrder_ETH_ProcessPO_SequencingChecker_RS.wsdl"/>
      <rootElement name="CustomProcessPO" namespace="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ENQJMS_BusinessErrors.wsdl"/>
      <rootElement name="CommonErrorHandlerCollection" namespace="http://xmlns.oracle.com/pcbpel/SC_Error_Details"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [WED APR 15 16:22:40 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_ENQJMS_BusinessErrors/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns2="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:imp1="http://xmlns.oracle.com/pcbpel/SC_Error_Details"
                xmlns:ns7="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns9="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                xmlns:inp1="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns5="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns8="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns3="http://www.openapplications.org/oagis/9/codelists"
                xmlns:sample="http://www.oracle.com/XSL/Transform/java/myxpath.functions.StringTest"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns1="http://oracle.com/esb/namespaces/DefaultSystem"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:ns4="http://www.openapplications.org/oagis/9"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                exclude-result-prefixes="xsl ns6 ns2 ns0 ns7 ns9 inp1 ns5 ns8 ns3 ns1 ns4 tns plt jca imp1 hdr ehdr xp20 ora orcl bpws hwf sample xref ids">
  <xsl:template match="/">
    <imp1:CommonErrorHandlerCollection>
      <imp1:CommonErrorHandler>
        <imp1:activityId>
          <xsl:value-of select='string("00")'/>
        </imp1:activityId>
        <imp1:messageId>
          <xsl:value-of select="/inp1:CustomProcessPO/Message_id"/>
        </imp1:messageId>
        <imp1:senderId>
          <xsl:value-of select="/inp1:CustomProcessPO/Sender_Id"/>
        </imp1:senderId>
        <imp1:sourceName>
          <xsl:value-of select='string("RS_SequenceChecker")'/>
        </imp1:sourceName>
        <imp1:invokedOperationName>
          <xsl:value-of select='string("DuplicateDataError")'/>
        </imp1:invokedOperationName>
        <imp1:flowId>
          <xsl:value-of select="/inp1:CustomProcessPO/Flow_id_2"/>
        </imp1:flowId>
        <imp1:message>
          <xsl:value-of select='string("Duplicate Error : Same transaction previously processed.")'/>
        </imp1:message>
        <imp1:exception>
          <xsl:value-of select='string("Duplicate Data Exception")'/>
        </imp1:exception>
        <imp1:inPayload>
          <xsl:value-of select="orcl:get-content-as-string(/inp1:CustomProcessPO)"/>
        </imp1:inPayload>
      </imp1:CommonErrorHandler>
    </imp1:CommonErrorHandlerCollection>
  </xsl:template>
</xsl:stylesheet>
