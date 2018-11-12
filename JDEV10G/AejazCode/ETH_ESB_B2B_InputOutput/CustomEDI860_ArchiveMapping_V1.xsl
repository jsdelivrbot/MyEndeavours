<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_B2BInOut_ETH_ChangePO_CustomEDI_EDI_5020_RS.wsdl"/>
      <rootElement name="CustomEDI860" namespace="http://xmlns.emrsn.com/CustomEDI860"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ENQJMS_ArchiveTransaction.wsdl"/>
      <rootElement name="RootElement" namespace="http://xmlns.emrsn.com/SC_Archive"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU SEP 24 16:43:08 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:eth="http://www.oracle.com/XSL/Transform/java/ETHComplianceCheck.functions.ETHComplianceCheck"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:ns1="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_B2BInOut"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:inp1="http://xmlns.emrsn.com/CustomEDI860"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_ENQJMS_ArchiveTransaction/"
                xmlns:ns2="urn:oracle:integration:b2b:DCC1FCD2811C47748DF43A39F735F4F0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:imp1="http://xmlns.emrsn.com/SC_Archive"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ns3="urn:oracle:integration:b2b:5DBD819D1FC74E2884B368A5B7392F9F"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                exclude-result-prefixes="xsl ns0 ns1 inp1 ns3 plt jca tns imp1 hdr bpws ehdr eth hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <imp1:RootElement>
      <ScMasterTransaction>
        <Message_ID>
          <xsl:value-of select="/CustomEDI860/Message_id"/>
        </Message_ID>
        <flowId1>
          <xsl:value-of select="/CustomEDI860/Flow_id_1"/>
        </flowId1>
        <flowId2>
          <xsl:value-of select="/CustomEDI860/Flow_id_2"/>
        </flowId2>
        <xsl:if test="/CustomEDI860/Attribute5 = 'TP1'">
          <flowId3>
            <xsl:value-of select="ehdr:getInstanceID()"/>
          </flowId3>
        </xsl:if>
        <stage>
          <xsl:text disable-output-escaping="no">B2B_Sent</xsl:text>
        </stage>
        <!--
        <businessDocumentNumber>
          <xsl:value-of select="/CustomEDI860/Sender_Document_number"/>
        </businessDocumentNumber>
        -->
        <receiverMessageStandard>
          <xsl:text disable-output-escaping="no">EDI_X12_V5020</xsl:text>
        </receiverMessageStandard>
        <receiverTransactionType>
          <xsl:text disable-output-escaping="no">POChange</xsl:text>
        </receiverTransactionType>
        <receiverFeedType>
          <xsl:text disable-output-escaping="no">EDI</xsl:text>
        </receiverFeedType>
        <receiverSystemType>
          <xsl:text disable-output-escaping="no">External</xsl:text>
        </receiverSystemType>
        <ethsentDate>
          <xsl:value-of select="xp20:current-date()"/>
        </ethsentDate>
        <ethsentTime>
          <xsl:value-of select="xp20:current-time()"/>
        </ethsentTime>
        <stControl>
          <xsl:value-of select="/CustomEDI860/Transaction-860/Segment-ST/Element-329"/>
        </stControl>
        <!--  <attribute6>
          <xsl:value-of select="/CustomEDI860/Attribute6"/>
        </attribute6>  -->
      </ScMasterTransaction>
      <scDetailedTransaction>
        <transactionDate>
          <xsl:value-of select="xp20:current-date()"/>
        </transactionDate>
        <transactionType>
          <xsl:text disable-output-escaping="no">EDI_860_V5020</xsl:text>
        </transactionType>
        <payload>
          <xsl:value-of select="orcl:get-content-as-string(/CustomEDI860/Transaction-860)"/>
        </payload>
        <businessDocumentID>
          <xsl:value-of select="/CustomEDI860/Sender_Document_number"/>
        </businessDocumentID>
        <source>
          <xsl:text disable-output-escaping="no">B2B</xsl:text>
        </source>
        <xsl:choose>
          <xsl:when test="/CustomEDI860/Attribute5 ='TP1'">
            <destinationSystem>
              <xsl:value-of select="/CustomEDI860/Trading_partner_1_name"/>
            </destinationSystem>
          </xsl:when>
          <xsl:when test="/CustomEDI860/Attribute5 ='TP2'">
            <destinationSystem>
              <xsl:value-of select="/CustomEDI860/Trading_partner_2_name"/>
            </destinationSystem>
          </xsl:when>
          <xsl:when test="/CustomEDI860/Attribute5 ='TP3'">
            <destinationSystem>
              <xsl:value-of select="/CustomEDI860/Trading_partner_3_name"/>
            </destinationSystem>
          </xsl:when>
          
          <xsl:when test="/CustomEDI860/Attribute5 ='TP4'">
            <destinationSystem>
              <xsl:value-of select="/CustomEDI860/Logistics_1_name"/>
            </destinationSystem>
          </xsl:when>
          
          <xsl:when test="/CustomEDI860/Attribute5 ='TP5'">
            <destinationSystem>
              <xsl:value-of select="/CustomEDI860/Logistics_2_name"/>
            </destinationSystem>
          </xsl:when>
          <xsl:otherwise>
            <destinationSystem>
              <xsl:value-of select="ERROR"/>
            </destinationSystem>
          </xsl:otherwise>
        </xsl:choose>
        <!-- <destinationSystem>
          <xsl:value-of select="/CustomEDI860/Trading_partner_1_name"/>
        </destinationSystem> -->
        <destinationFlowID>
          <xsl:value-of select="ehdr:getInstanceID()"/>
        </destinationFlowID>
        <destinationAckTrackingID>
          <xsl:value-of select="/CustomEDI860/Attribute6"/>
          <!--  <xsl:value-of select="/CustomEDI860/Transaction-860/Segment-ST/Element-329"/> -->
        </destinationAckTrackingID>
        <XMLConfirmBODNumber>
	  <xsl:value-of select="/CustomEDI860/Attribute7"/>
        </XMLConfirmBODNumber>
      </scDetailedTransaction>
    </imp1:RootElement>
  </xsl:template>
</xsl:stylesheet>
