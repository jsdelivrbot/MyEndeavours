<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETH_DEQJMS_CustomProcessPO.wsdl"/>
      <rootElement name="CustomProcessPO" namespace="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ENQJMS_ArchiveTransaction.wsdl"/>
      <rootElement name="RootElement" namespace="http://xmlns.emrsn.com/SC_Archive"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [MON MAY 25 21:12:49 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns7="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns3="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_DEQJMS_CustomChangePO/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:imp1="http://xmlns.emrsn.com/SC_Archive"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns8="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns10="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                xmlns:ns12="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns11="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_DEQJMS_CustomProcessPO/"
                xmlns:ns2="http://xmlns.emrsn.com/CustomChangePurchaseOrder"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ns9="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ns6="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:eth="http://www.oracle.com/XSL/Transform/java/ETHComplianceCheck.functions.ETHComplianceCheck"
                xmlns:ns4="http://www.openapplications.org/oagis/9/codelists"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_ENQJMS_ArchiveTransaction/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:ns5="http://www.openapplications.org/oagis/9"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                exclude-result-prefixes="xsl ns7 plt ns3 ns0 jca ns8 ns10 ns12 ns11 pc ns9 ns6 ns4 ns5 hdr imp1 tns ehdr xp20 ora orcl bpws eth hwf xref ids">
  <xsl:template match="/">
    <imp1:RootElement>
      <ScMasterTransaction>
        <Message_ID>
          <xsl:value-of select="/ns12:CustomProcessPO/Message_id"/>
        </Message_ID>
        <flowId3>
          <xsl:value-of select="ehdr:getInstanceID()"/>
        </flowId3>
      </ScMasterTransaction>
      <scDetailedTransaction>
        <transactionDate>
          <xsl:value-of select="xp20:current-date()"/>
        </transactionDate>
        <transactionType>
          <xsl:text disable-output-escaping="no">DoNotSend</xsl:text>
        </transactionType>
        <payload>
          <xsl:value-of select="orcl:get-content-as-string(/ns12:CustomProcessPO/ProcessPO)"/>
        </payload>
        <businessDocumentID>
          <xsl:value-of select="/ns12:CustomProcessPO/Sender_Document_number"/>
        </businessDocumentID>
        <source>
          <xsl:text disable-output-escaping="no">ETH</xsl:text>
        </source>
        <xsl:choose>
          <xsl:when test='/ns12:CustomProcessPO/Attribute5 = "TP1"'>
            <destinationSystem>
              <xsl:value-of select="/ns12:CustomProcessPO/Trading_partner_1_name"/>
            </destinationSystem>
          </xsl:when>
          <xsl:when test='/ns12:CustomProcessPO/Attribute5 = "TP2"'>
            <destinationSystem>
              <xsl:value-of select="/ns12:CustomProcessPO/Trading_partner_2_name"/>
            </destinationSystem>
          </xsl:when>
          <xsl:otherwise>
            <destinationSystem>
              <xsl:value-of select='string("ERROR")'/>
            </destinationSystem>
          </xsl:otherwise>
        </xsl:choose>
        <Details_attribute3>
          <xsl:value-of select='string("DoNotSend")'/>
        </Details_attribute3>
      </scDetailedTransaction>
    </imp1:RootElement>
  </xsl:template>
</xsl:stylesheet>
