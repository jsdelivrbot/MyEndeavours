<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_B2BInOut_ETH_ProcessPO_CustomEDI_EDI_5020_RS.wsdl"/>
      <rootElement name="CustomEDI850" namespace="http://xmlns.emrsn.com/CustomEDI850"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="Write_PUTFL_EDI_ChangePO.wsdl"/>
      <rootElement name="Transaction-850" namespace="urn:oracle:integration:b2b:DCC1FCD2811C47748DF43A39F735F4F0"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [FRI MAR 06 12:38:27 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:inp1="http://xmlns.emrsn.com/CustomEDI850"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns1="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_B2BInOut"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:imp1="urn:oracle:integration:b2b:DCC1FCD2811C47748DF43A39F735F4F0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/Write_PUTFL_EDI_ChangePO/"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/file/"
                exclude-result-prefixes="xsl inp1 ns0 ns1 imp1 plt jca tns hdr bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <!--<imp1:Transaction-850>
      <xsl:attribute name="CreatedBy">
        <xsl:value-of select="/inp1:CustomEDI850/Trading_partner_1_name"/>
      </xsl:attribute>
    </imp1:Transaction-850>-->
  <!--   <xsl:variable name="source" select="'Emerson'"/>
    <xsl:variable name="destination" select="/CustomEDI850/Trading_partner_1_name"/>
    <xsl:variable name="docName" select="'850'"/>
    <xsl:variable name="docRevision" select="'5020'"/>
    <xsl:variable name="msgType" select="1"/>
    
    <xsl:variable name="obj1:MSG_ID" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/MSG_ID',orcl:generate-guid(),
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:FROM_PARTY" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/FROM_PARTY',$source,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:TO_PARTY" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/TO_PARTY',$destination,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:DOCTYPE_NAME" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/DOCTYPE_NAME',$docName,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:DOCTYPE_REVISION" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/DOCTYPE_REVISION',$docRevision,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:MSG_TYPE" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/MSG_TYPE',$msgType,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/> -->
    

    <Transaction-850>
      
      <xsl:attribute name="XDataVersion">
        <xsl:value-of select="/CustomEDI850/Transaction-850/@XDataVersion"/>
      </xsl:attribute>
      <xsl:attribute name="Standard">
        <xsl:value-of select="/CustomEDI850/Transaction-850/@Standard"/>
      </xsl:attribute>
           <xsl:attribute name="GUID">
        <xsl:value-of select="/CustomEDI850/Transaction-850/@GUID"/>
      </xsl:attribute>
      <xsl:attribute name="CreatedBy">
        <xsl:value-of select="/CustomEDI850/Transaction-850/@CreatedBy"/>
      </xsl:attribute>
      <xsl:attribute name="CreatedDate">
        <xsl:value-of select="/CustomEDI850/Transaction-850/@CreatedDate"/>
      </xsl:attribute>
      <xsl:copy-of select="/CustomEDI850/Transaction-850/*"/>
    </Transaction-850> 
   
  </xsl:template>
</xsl:stylesheet>
