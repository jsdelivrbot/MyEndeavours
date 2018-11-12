<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETHSys_IntegrationServices_ETHSysGrp_B2BInOut_ETH_ChangePO_CustomEDI_EDI_5020_RS.wsdl"/>
      <rootElement name="CustomEDI850" namespace="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_ENQJMS_ArchiveTransaction.wsdl"/>
      <rootElement name="RootElement" namespace="http://xmlns.emrsn.com/SC_Archive"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU MAR 05 20:10:43 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:inp1="http://xmlns.emrsn.com/CustomProcessPurchaseOrder"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns1="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_B2BInOut"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/jms/ETH_ENQJMS_ArchiveTransaction/"
                xmlns:ns2="urn:oracle:integration:b2b:DCC1FCD2811C47748DF43A39F735F4F0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:imp1="http://xmlns.emrsn.com/SC_Archive"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                exclude-result-prefixes="xsl inp1 ns0 ns1 ns2 plt jca tns imp1 hdr bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <!--  <RootElement>
      <ScMasterTransaction>
        <Message_ID>
          <xsl:value-of select="/CustomEDI850/Message_id"/>
        </Message_ID>
      </ScMasterTransaction>
    </RootElement> -->
  </xsl:template>
</xsl:stylesheet>
