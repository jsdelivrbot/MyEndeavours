<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETH_DEQAQ_IPINQUEUE_Edi_855.wsdl"/>
      <rootElement name="Transaction-855" namespace="NS_31CA8D0F33324F95A0BF15D85539C27E20081029152015"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_B2BInOut_ETH_Transform_Edi_855_5020.wsdl"/>
      <rootElement name="CustomEDI855" namespace="http://xmlns.emrsn.com/CustomTransaction855"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [TUE MAR 10 14:29:46 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/aq/ETH_DEQAQ_IPINQUEUE_Edi_855/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:ns1="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_B2BInOut"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:inp1="http://xmlns.emrsn.com/CustomTransaction855"
                xmlns:obj1="http://xmlns.oracle.com/xdb/B2B"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:imp1="NS_31CA8D0F33324F95A0BF15D85539C27E20081029152015"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                exclude-result-prefixes="xsl plt pc tns jca ns0 obj1 imp1 ns1 inp1 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <xsl:variable name="FROM_PARTY"
                  select="ehdr:getRequestHeader('/ns1:Header/ns1:PayloadHeader/FROM_PARTY','ns1=http://xmlns.oracle.com/pcbpel/adapter/aq/ETH_DEQAQ_IPINQUEUE_Edi_855/; ')"/>
    <xsl:variable name="TO_PARTY"
                  select="ehdr:getRequestHeader('/ns1:Header/ns1:PayloadHeader/TO_PARTY','ns1=http://xmlns.oracle.com/pcbpel/adapter/aq/ETH_DEQAQ_IPINQUEUE_Edi_855/; ')"/>
    <xsl:variable name="DOCTYPE_NAME"
                  select="ehdr:getRequestHeader('/ns1:Header/ns1:PayloadHeader/DOCTYPE_NAME','ns1=http://xmlns.oracle.com/pcbpel/adapter/aq/ETH_DEQAQ_IPINQUEUE_Edi_855/; ')"/>
    <xsl:variable name="DOCTYPE_REVISION"
                  select="ehdr:getRequestHeader('/ns1:Header/ns1:PayloadHeader/DOCTYPE_REVISION','ns1=http://xmlns.oracle.com/pcbpel/adapter/aq/ETH_DEQAQ_IPINQUEUE_Edi_855/; ')"/>
 <!--   <inp1:CustomEDI855>
      <xsl:if test='xp20:matches(/ns9:Transaction-855/ns9:Internal-Properties/ns9:Data-Structure/ns9:Data-Structure/@Name,"Group")'>
      <Sender_Id>
        <xsl:value-of select="/imp1:Transaction-855/imp1:Internal-Properties/imp1:Data-Structure/imp1:Data-Structure/imp1:Property[2.0]"/>
      </Sender_Id>
      </xsl:if>
    </inp1:CustomEDI855> -->
      <inp1:CustomEDI855>
    <imp1:Transaction-855>
        <xsl:copy-of select="/imp1:Transaction-855/*"/>
      </imp1:Transaction-855>  
          <Message_id>
        <xsl:value-of select='orcl:sequence-next-val("SC_MessageID_Seq","jdbc:polite4:system/any@localhost:1531:OraESB")'/>
      </Message_id> 
      <Message_direction>
        <xsl:text disable-output-escaping="no">IN</xsl:text>
      </Message_direction>
      <xsl:if test='/imp1:Transaction-855/imp1:Internal-Properties/imp1:Data-Structure/imp1:Data-Structure/@Name = "Group"'>
      <Sender_Id>
        <xsl:value-of select="/imp1:Transaction-855/imp1:Internal-Properties/imp1:Data-Structure/imp1:Data-Structure/imp1:Property[2.0]"/>
      </Sender_Id>
      </xsl:if>
     <Sender_Message_standard>
        <xsl:value-of select="concat('EDIX12',$DOCTYPE_REVISION)"/>
      </Sender_Message_standard> 
      <Sender_Site_Id>
        <xsl:text disable-output-escaping="no">NA</xsl:text>
      </Sender_Site_Id>
      <Sender_Transaction_type>
        <xsl:value-of select="$DOCTYPE_NAME"/>
      </Sender_Transaction_type>
       <Sender_Name>
        <xsl:value-of select="$FROM_PARTY"/>
      </Sender_Name>
     
     <xsl:if test='/imp1:Transaction-855/imp1:Internal-Properties/imp1:Data-Structure/imp1:Data-Structure/@Name = "Group"'>
      <Receiver_Id>
        <xsl:value-of select="/imp1:Transaction-855/imp1:Internal-Properties/imp1:Data-Structure/imp1:Data-Structure/imp1:Property[3.0]"/>
      </Receiver_Id>
      </xsl:if>
     <Flow_id_1>
      <xsl:value-of select="ehdr:getInstanceID()"/>
   </Flow_id_1> 
    </inp1:CustomEDI855>
    <!--  <inp1:CustomEDI855>
      <imp1:Transaction-855>
      
       <xsl:copy-of select="/imp1:Transaction-855/*"/>
      </imp1:Transaction-855>  
     
      
      <Sender_Message_standard>
        <xsl:value-of select="$DOCTYPE_REVISION"/>
      </Sender_Message_standard>
      <Sender_Name>
        <xsl:value-of select="$FROM_PARTY"/>
      </Sender_Name>
    </inp1:CustomEDI855> -->
  </xsl:template>
</xsl:stylesheet>