<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_B2BInOut_ETH_ChangePO_CustomEDI_EDI_5020_RS.wsdl"/>
      <rootElement name="CustomEDI860" namespace="http://xmlns.emrsn.com/CustomEDI860"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ChangePO_ENQAQ_EDI_5020_to_B2B.wsdl"/>
      <rootElement name="Transaction-860" namespace="urn:oracle:integration:b2b:5DBD819D1FC74E2884B368A5B7392F9F"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU SEP 24 16:20:23 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/aq/ETH_ChangePO_ENQAQ_EDI_5020_to_B2B/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:eth="http://www.oracle.com/XSL/Transform/java/ETHComplianceCheck.functions.ETHComplianceCheck"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:tns="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_B2BInOut"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:inp1="http://xmlns.emrsn.com/CustomEDI860"
                xmlns:obj1="http://xmlns.oracle.com/xdb/B2B"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:imp1="urn:oracle:integration:b2b:5DBD819D1FC74E2884B368A5B7392F9F"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                exclude-result-prefixes="xsl ns0 tns inp1 imp1 ns1 plt jca obj1 bpws ehdr eth hwf xp20 xref ora ids orcl">
  
<xsl:template match="/">
  
     <xsl:variable name="source" select="'Emerson'"/> 
   <!-- <xsl:variable name="destination" select="/CustomEDI860/Trading_partner_1_name"/> -->
   <!-- <xsl:variable name="destination" select="'Dummy'"/> -->
   <xsl:variable name="destination">
        <xsl:choose>
            <xsl:when test="/CustomEDI860/Attribute5 ='TP1'">        
                <xsl:value-of select="/CustomEDI860/Trading_partner_1_name"/>
            </xsl:when>
            <xsl:when test="/CustomEDI860/Attribute5 ='TP2'">
                <xsl:value-of select="/CustomEDI860/Trading_partner_2_name"/>
            </xsl:when> 
            <xsl:when test="/CustomEDI860/Attribute5 ='TP3'">        
                <xsl:value-of select="/CustomEDI860/Trading_partner_3_name"/>
            </xsl:when>
            
            <xsl:when test="/CustomEDI860/Attribute5 ='TP4'">        
                <xsl:value-of select="/CustomEDI860/Logistics_1_name"/>
            </xsl:when>
            
            <xsl:when test="/CustomEDI860/Attribute5 ='TP5'">        
                <xsl:value-of select="/CustomEDI860/Logistics_2_name"/>
            </xsl:when>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="docName" select="'860'"/>
    <xsl:variable name="docRevision" select="'5020'"/>
    <xsl:variable name="msgType" select="1"/>
  <!--   <xsl:variable name="messageid" select="/inp1:CustomEDI860/imp1:Transaction-860/imp1:Segment-ST/imp1:Element-329"/> -->
    <!-- <xsl:variable name="messageid" select="/CustomEDI860/Message_id"/> -->
    <xsl:variable name="messageid" select="/CustomEDI860/Attribute6"/>
    <xsl:variable name="actionName" select="concat('contentType:application/octet-stream;filename:',/CustomEDI860/Attribute7)"/>
     
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
                            
    <Transaction-860>
      <xsl:attribute name="XDataVersion">
        <xsl:value-of select="/CustomEDI860/Transaction-860/@XDataVersion"/>
      </xsl:attribute>
      <xsl:attribute name="Standard">
        <xsl:value-of select="/CustomEDI860/Transaction-860/@Standard"/>
      </xsl:attribute>
      <xsl:attribute name="GUID">
        <xsl:value-of select="/CustomEDI860/Transaction-860/@GUID"/>
      </xsl:attribute>
      <xsl:attribute name="CreatedBy">
        <xsl:value-of select="/CustomEDI860/Transaction-860/@CreatedBy"/>
      </xsl:attribute>
      <xsl:attribute name="CreatedDate">
        <xsl:value-of select="/CustomEDI860/Transaction-860/@CreatedDate"/>
      </xsl:attribute>
      <xsl:copy-of select="/CustomEDI860/Transaction-860/*"/>
    </Transaction-860>  
  </xsl:template>
</xsl:stylesheet>

