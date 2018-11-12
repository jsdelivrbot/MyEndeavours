<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="Item_number_And_Cost_LBT_Rtr_ItemNumAndCostToFTP.wsdl"/>
      <rootElement name="SyncItemMaster" namespace="http://www.openapplications.org/oagis/9"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="http://essapt020-u006:12001/orabpel/LiebertServices/LIEBERT_VENDAVO_INTEGRATION_ItemNumberAndCost_CTL_BPEL/1.0/LIEBERT_VENDAVO_INTEGRATION_ItemNumberAndCost_CTL_BPEL?wsdl"/>
      <rootElement name="Root-Element" namespace="http://TargetNamespace.com/read"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU NOV 20 15:43:23 IST 2008]. -->
?>
<xsl:stylesheet version="1.0" xmlns:ns10="http://TargetNamespace.com/read"
                xmlns:ns4="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:ns8="http://xmlns.oracle.com/LIEBERT_VENDAVO_INTEGRATION_ItemNumberAndCost_CTL_BPEL"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns1="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns9="http://TargetNamespace.com/InboundService"
                xmlns:ns5="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns7="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns3="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns6="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns2="http://www.openapplications.org/oagis/9/codelists"
                xmlns:wsa="http://schemas.xmlsoap.org/ws/2003/03/addressing"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tns="http://oracle.com/esb/namespaces/Item_number_And_Cost"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:inp1="http://www.openapplications.org/oagis/9"
                exclude-result-prefixes="xsl ns4 ns1 ns0 ns5 ns7 ns3 ns6 ns2 tns inp1 ns10 ns8 plnk ns9 wsa soap ehdr xp20 ora orcl bpws hwf xref ids">
  <xsl:template match="/">
    <ns10:Root-Element>
      <ns10:recctl>
        <ns10:C1>
          <xsl:text disable-output-escaping="no">ENPItemNumber.csv</xsl:text>
        </ns10:C1>
        <ns10:C2>
          <xsl:text disable-output-escaping="no">ENPItemCost.csv</xsl:text>
        </ns10:C2>
      </ns10:recctl>
    </ns10:Root-Element>
  </xsl:template>
</xsl:stylesheet>