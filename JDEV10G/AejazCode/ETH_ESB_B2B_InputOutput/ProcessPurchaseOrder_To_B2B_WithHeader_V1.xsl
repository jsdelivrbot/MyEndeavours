<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSystem_OutputServices_ETHSysGrp_B2BInOut_ETH_ProcessPO_B2B.wsdl"/>
      <rootElement name="ProcessPurchaseOrder" namespace="http://www.openapplications.org/oagis/9"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ProcessPO_ENQAQ_Oagis_9_0_to_B2B.wsdl"/>
      <rootElement name="ProcessPurchaseOrder" namespace="http://www.openapplications.org/oagis/9"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [FRI FEB 26 19:30:14 IST 2010]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns5="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ns7="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ns4="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns2="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:eth="http://www.oracle.com/XSL/Transform/java/ETHComplianceCheck.functions.ETHComplianceCheck"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:ns3="http://www.openapplications.org/oagis/9/codelists"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:obj1="http://xmlns.oracle.com/xdb/B2B"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/aq/ETH_ProcessPO_ENQAQ_Oagis_9_0_to_B2B/"
                xmlns:ns1="http://oracle.com/esb/namespaces/ETHSystem_OutputServices_ETHSysGrp_B2BInOut"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:imp1="http://www.openapplications.org/oagis/9"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns8="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                exclude-result-prefixes="xsl ns5 ns7 ns4 ns2 ns0 ns3 ns1 imp1 ns6 ns8 plt jca obj1 tns bpws ehdr eth hwf xp20 xref ora ids orcl">
   <xsl:template match="*">
   <xsl:variable name="messageid" select="ehdr:getRequestHeader('/obj1:Header/obj1:PayloadHeader/MSG_ID','obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
   <xsl:variable name="source" select="ehdr:getRequestHeader('/obj1:Header/obj1:PayloadHeader/FROM_PARTY','obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
   <xsl:variable name="destination" select="ehdr:getRequestHeader('/obj1:Header/obj1:PayloadHeader/TO_PARTY','obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
   <xsl:variable name="actionName" select="ehdr:getRequestHeader('/obj1:Header/obj1:PayloadHeader/ACTION_NAME','obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
   <xsl:variable name="docName" select="ehdr:getRequestHeader('/obj1:Header/obj1:PayloadHeader/DOCTYPE_NAME','obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
   <xsl:variable name="docRevision" select="ehdr:getRequestHeader('/obj1:Header/obj1:PayloadHeader/DOCTYPE_REVISION','obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
   <xsl:variable name="msgType" select="ehdr:getRequestHeader('/obj1:Header/obj1:PayloadHeader/MSG_TYPE','obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
   
    <xsl:variable name="obj1:MSG_ID" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/MSG_ID',$messageid,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:FROM_PARTY" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/FROM_PARTY',$source,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:TO_PARTY" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/TO_PARTY',$destination,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:ACTION_NAME" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/ACTION_NAME',$actionName,'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:DOCTYPE_NAME" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/DOCTYPE_NAME',$docName,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:DOCTYPE_REVISION" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/DOCTYPE_REVISION',$docRevision,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/>
    <xsl:variable name="obj1:MSG_TYPE" select="ehdr:setOutboundHeader('/obj1:Header/obj1:PayloadHeader/MSG_TYPE',$msgType,
                            'obj1=http://xmlns.oracle.com/xdb/B2B;')"/> 

  <xsl:element name="{name()}" namespace="http://www.openapplications.org/oagis/9">
    <xsl:copy-of select="@*"/>
    <xsl:apply-templates/>
  </xsl:element>
  </xsl:template>
</xsl:stylesheet>