<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_ProcessPurchaseOrder_ETH_Transform_CustomLiebertPO_RS.wsdl"/>
      <rootElement name="CustomLiebertPO" namespace="http://xmlns.emrsn.com/CustomLiebertPurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ENQJMS_ArchiveTransaction.wsdl"/>
      <rootElement name="RootElement" namespace="http://xmlns.emrsn.com/SC_Archive"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [TUE MAR 17 13:57:38 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns2="http://xmlns.emrsn.com/LiebertPurchaseOrder"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:inp1="http://xmlns.emrsn.com/CustomLiebertPurchaseOrder"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_ENQJMS_ArchiveTransaction/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:imp1="http://xmlns.emrsn.com/SC_Archive"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:tns="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_ProcessPurchaseOrder"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                exclude-result-prefixes="xsl ns2 inp1 ns0 tns plt jca ns1 imp1 hdr bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <imp1:RootElement>
      <ScMasterTransaction>
        <Message_ID>
          <xsl:value-of select="/inp1:CustomLiebertPO/Message_id"/>
        </Message_ID>
        <flowId1>
          <xsl:value-of select="/inp1:CustomLiebertPO/Flow_id_1"/>
        </flowId1>
        <flowId2>
          <xsl:value-of select="ehdr:getInstanceID()"/>
        </flowId2>
        <messageDirection>
          <xsl:value-of select="/inp1:CustomLiebertPO/Message_direction"/>
        </messageDirection>
        <stage>
          <xsl:value-of select='string("Initial")'/>
        </stage>
        <businessDocumentNumber>
          <xsl:value-of select="/inp1:CustomLiebertPO/ns2:Root-Element/record/HDBEG03"/>
        </businessDocumentNumber>
        <senderGroup>
          <xsl:value-of select='orcl:lookup-dvm("GroupID","SenderID",/inp1:CustomLiebertPO/Sender_Id,"Group","ETH")'/>
        </senderGroup>
        <senderId>
          <xsl:value-of select="/inp1:CustomLiebertPO/Sender_Id"/>
        </senderId>
        <senderName>
          <xsl:value-of select='orcl:lookup-dvm("GroupID","SenderID",/inp1:CustomLiebertPO/Sender_Id,"SenderName","ETH")'/>
        </senderName>
        <senderMessageStandard>
          <xsl:value-of select="/inp1:CustomLiebertPO/Sender_Message_standard"/>
        </senderMessageStandard>
        <senderTransactionType>
          <xsl:value-of select='orcl:lookup-dvm("TransactionType","XMLType",/inp1:CustomLiebertPO/Sender_Transaction_subtype,"TransactionName","INVALID")'/>
        </senderTransactionType>
        <senderFeedType>
          <xsl:value-of select="/inp1:CustomLiebertPO/Sender_Message_type"/>
        </senderFeedType>
        <senderSystemType>
          <xsl:value-of select="/inp1:CustomLiebertPO/Sender_System_type"/>
        </senderSystemType>
        <senderFileDate>
          <xsl:value-of select="xp20:current-date()"/>
        </senderFileDate>
        <senderFileTime>
          <xsl:value-of select="xp20:current-time()"/>
        </senderFileTime>
        <senderFlatFileFileName>
          <xsl:value-of select="/inp1:CustomLiebertPO/File_name"/>
        </senderFlatFileFileName>
        <senderFlatFileRecordNumber>
          <xsl:value-of select="/inp1:CustomLiebertPO/Record_number"/>
        </senderFlatFileRecordNumber>
        <senderRecordTotal>
          <xsl:value-of select="/inp1:CustomLiebertPO/Record_total"/>
        </senderRecordTotal>
        <ethReceiveDate>
          <xsl:value-of select="xp20:current-date()"/>
        </ethReceiveDate>
        <ethReceiveTime>
          <xsl:value-of select="xp20:current-time()"/>
        </ethReceiveTime>
      </ScMasterTransaction>
      <scDetailedTransaction>
        <transactionDate>
          <xsl:value-of select="xp20:current-date()"/>
        </transactionDate>
        <transactionType>
          <xsl:value-of select="/inp1:CustomLiebertPO/Sender_Transaction_type"/>
        </transactionType>
        <payload>
          <xsl:value-of select="orcl:get-content-as-string(/inp1:CustomLiebertPO/ns2:Root-Element)"/>
        </payload>
        <businessDocumentID>
          <xsl:value-of select="/inp1:CustomLiebertPO/ns2:Root-Element/record/HDBEG03"/>
        </businessDocumentID>
        <source>
          <xsl:value-of select='string("FTP")'/>
        </source>
      </scDetailedTransaction>
    </imp1:RootElement>
  </xsl:template>
</xsl:stylesheet>
