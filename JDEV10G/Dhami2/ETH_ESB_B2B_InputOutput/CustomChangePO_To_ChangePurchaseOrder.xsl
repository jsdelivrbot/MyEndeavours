<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_B2BInOut_ETH_ChangePO_CustomOagis_to_Oagis_9_0_RS.wsdl"/>
      <rootElement name="CustomChangePO" namespace="http://xmlns.emrsn.com/CustomChangePurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="Write_PUTFL_ChangePO_Oagis_9_0.wsdl"/>
      <rootElement name="ChangePurchaseOrder" namespace="http://www.openapplications.org/oagis/9"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [MON APR 27 14:50:05 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns5="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:inp1="http://xmlns.emrsn.com/CustomChangePurchaseOrder"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ns7="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns4="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns2="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:tns="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_B2BInOut"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/file/Write_PUTFL_ChangePO_Oagis_9_0/"
                xmlns:ns3="http://www.openapplications.org/oagis/9/codelists"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:imp1="http://www.openapplications.org/oagis/9"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/file/"
                xmlns:ns8="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                exclude-result-prefixes="xsl ns5 inp1 ns7 ns4 ns2 ns0 tns ns3 imp1 ns6 ns8 plt jca ns1 hdr bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <imp1:ChangePurchaseOrder>
        <xsl:attribute name="releaseID">
        <xsl:value-of select="/inp1:CustomChangePO/ChangePO/@releaseID"/>
      </xsl:attribute>
       <xsl:if test="/inp1:CustomChangePO/ChangePO/@versionID !=''">
      <xsl:attribute name="versionID">
        <xsl:value-of select="/inp1:CustomChangePO/ChangePO/@versionID"/>
      </xsl:attribute>
      </xsl:if>
       <xsl:if test="/inp1:CustomChangePO/ChangePO/@systemEnvironmentCode !=''">
      <xsl:attribute name="systemEnvironmentCode">
        <xsl:value-of select="/inp1:CustomChangePO/ChangePO/@systemEnvironmentCode"/>
      </xsl:attribute>
      </xsl:if>
      <xsl:if test="/inp1:CustomChangePO/ChangePO/@languageCode !=''">
      <xsl:attribute name="languageCode">
        <xsl:value-of select="/inp1:CustomChangePO/ChangePO/@languageCode"/>
      </xsl:attribute>
      </xsl:if> 
        <xsl:copy-of select="/inp1:CustomChangePO/ChangePO/*"/> 
    </imp1:ChangePurchaseOrder>
    <!-- <xsl:variable name="source" select="'Emerson'"/>
    <xsl:variable name="destination" select="/CustomEDI850/Trading_partner_1_name"/>
    <xsl:variable name="docName" select="'ChangePO'"/>
    <xsl:variable name="docRevision" select="'9.0'"/>
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
    <!--  <ChangePurchaseOrder>
      <xsl:attribute name="releaseID">
        <xsl:value-of select="/CustomChangePO/ProcessPO/@releaseID"/>
      </xsl:attribute>
      <xsl:if test="/CustomChangePO/ProcessPO/@versionID !=''">
      <xsl:attribute name="versionID">
        <xsl:value-of select="/CustomChangePO/ProcessPO/@versionID"/>
      </xsl:attribute>
      </xsl:if>
      <xsl:if test="/CustomChangePO/ProcessPO/@systemEnvironmentCode !=''">
      <xsl:attribute name="systemEnvironmentCode">
        <xsl:value-of select="/CustomChangePO/ProcessPO/@systemEnvironmentCode"/>
      </xsl:attribute>
      </xsl:if>
       <xsl:if test="/CustomChangePO/ProcessPO/@languageCode !=''">
      <xsl:attribute name="languageCode">
        <xsl:value-of select="/CustomChangePO/ProcessPO/@languageCode"/>
      </xsl:attribute>
      </xsl:if>
       <xsl:copy-of select="/CustomChangePO/ProcessPO/*"/>
    </ChangePurchaseOrder>  -->
    <!-- <ChangePurchaseOrder>
      <xsl:copy-of select="/CustomChangePO/ProcessPO/*"/>
    </ChangePurchaseOrder> -->
    <!--<ChangePurchaseOrder>
      <xsl:copy-of select="/CustomChangePO/ProcessPO/*"/>
    </ChangePurchaseOrder> -->
  </xsl:template>
</xsl:stylesheet>
