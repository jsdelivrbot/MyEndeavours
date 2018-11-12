<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_B2BInOut_ETH_ProcessPO_CustomOagis_Oagis_9_0_RS.wsdl"/>
      <rootElement name="CustomProcessPO" namespace="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ENQJMS_ArchiveTransaction.wsdl"/>
      <rootElement name="RootElement" namespace="http://xmlns.emrsn.com/SC_Archive"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU MAR 05 18:52:43 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns2="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:imp1="http://xmlns.emrsn.com/SC_Archive"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns7="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns9="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                xmlns:inp1="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns5="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns8="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ns1="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_B2BInOut"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns3="http://www.openapplications.org/oagis/9/codelists"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_ENQJMS_ArchiveTransaction/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:ns4="http://www.openapplications.org/oagis/9"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                exclude-result-prefixes="xsl ns6 ns2 ns0 ns7 ns9 inp1 ns5 ns8 ns1 ns3 ns4 plt jca imp1 tns hdr ehdr xp20 ora orcl bpws hwf xref ids">
  <xsl:template match="/">
    <imp1:RootElement>
      <ScMasterTransaction>
        <Message_ID>
          <!--<xsl:value-of select="string('1471')"/>-->
          <xsl:value-of select="/CustomProcessPO/Message_id"/>
        </Message_ID>
         <flowId1>
          <xsl:value-of select="/CustomProcessPO/Flow_id_1"/>
        </flowId1>
        <flowId2>
          <xsl:value-of select="/CustomProcessPO/Flow_id_2"/>
        </flowId2>
        <flowId3>
        <xsl:value-of select="ehdr:getInstanceID()"/>
        </flowId3>
        <stage>
          <xsl:text disable-output-escaping="no">B2B_Sent</xsl:text>
        </stage>
        <receiverMessageStandard>
          <xsl:text disable-output-escaping="no">OAGIS_XML_9.0</xsl:text>
        </receiverMessageStandard>
        <receiverTransactionType>
          <!--<xsl:text disable-output-escaping="no">ProcessPO_9_0</xsl:text> -->
          <xsl:text disable-output-escaping="no">PO</xsl:text>
        </receiverTransactionType>
        <receiverFeedType>
          <xsl:text disable-output-escaping="no">XML</xsl:text>
        </receiverFeedType>
        <receiverSystemType>
          <xsl:text disable-output-escaping="no">ORACLE</xsl:text>
        </receiverSystemType>
        <receiverXmlDocumentNumber>
          <xsl:value-of select="/CustomProcessPO/ProcessPO/ApplicationArea/Sender/ReferenceID"/>
        </receiverXmlDocumentNumber>
        <ethsentDate>
          <xsl:value-of select="xp20:current-date()"/>
        </ethsentDate>
        <ethsentTime>
          <xsl:value-of select="xp20:current-time()"/>
        </ethsentTime>
       <!--  <attribute6>
          <xsl:value-of select="/CustomProcessPO/Attribute6"/>
        </attribute6> -->
      </ScMasterTransaction>
      <scDetailedTransaction>
        <transactionDate>
          <xsl:value-of select="xp20:current-date()"/>
        </transactionDate>
        <transactionType>
          <xsl:text disable-output-escaping="no">ProcessPO_9_0</xsl:text>
        </transactionType>
        <payload>
           <xsl:value-of select="orcl:get-content-as-string(/CustomProcessPO/ProcessPO)"/>
        </payload>
        <businessDocumentID>
          <xsl:value-of select="/CustomProcessPO/ProcessPO/DataArea/PurchaseOrder/PurchaseOrderHeader/DocumentID/ID"/>
        </businessDocumentID>
        <source>
          <xsl:text disable-output-escaping="no">B2B</xsl:text>
        </source>
         
       <!-- <destinationSystem>
          <xsl:value-of select="/CustomProcessPO/Trading_partner_1_name"/>
          
        </destinationSystem> -->
       
        <xsl:choose>
                  <xsl:when test="/CustomProcessPO/Attribute5 ='TP1'">  
                   <destinationSystem>
                      <xsl:value-of select="/CustomProcessPO/Trading_partner_1_name"/>
                       </destinationSystem>
                 </xsl:when>
                 <xsl:when test="/CustomProcessPO/Attribute5 ='TP2'">  
                 <destinationSystem>
                      <xsl:value-of select="/CustomProcessPO/Trading_partner_2_name"/>
                      </destinationSystem>
                </xsl:when>
            <xsl:otherwise>
            <destinationSystem>
            <xsl:value-of select="ERROR"/>
            </destinationSystem>
            </xsl:otherwise>
             
            </xsl:choose>
   
    
        <destinationFlowID>
          <xsl:value-of select="ehdr:getInstanceID()"/>
        </destinationFlowID>
        <destinationAckTrackingID>
         <xsl:value-of select="/CustomProcessPO/Attribute6"/>
        <!--  <xsl:value-of select="/CustomProcessPO/ProcessPO/ApplicationArea/Sender/ReferenceID"/> -->
        </destinationAckTrackingID>
      </scDetailedTransaction>
    </imp1:RootElement>
  </xsl:template>
</xsl:stylesheet>
