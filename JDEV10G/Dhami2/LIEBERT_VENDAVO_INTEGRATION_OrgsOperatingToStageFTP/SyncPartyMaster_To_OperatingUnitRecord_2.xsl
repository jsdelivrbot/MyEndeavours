<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="Liebert_OrgsOperating_LBT_Rtr_OperatingTypeFtp.wsdl"/>
      <rootElement name="SyncPartyMaster" namespace="http://www.openapplications.org/oagis/9"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="http://mdpemr161368:8889/orabpel/default/LIEBERT_VENDAVO_INTEGRATION_ENPOperatingType_BPEL/1.0/LIEBERT_VENDAVO_INTEGRATION_ENPOperatingType_BPEL?wsdl"/>
      <rootElement name="OperatingUnitRecord" namespace="http://TargetNamespace.com/LBT_FtpPut_OperatingType"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.3.0(build 070615.0525) AT [THU NOV 06 14:19:13 IST 2008]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ns8="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ns5="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns3="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:ns1="http://xmlns.oracle.com/LIEBERT_VENDAVO_INTEGRATION_ENPOperatingType_BPEL"
                xmlns:ns4="http://www.openapplications.org/oagis/9/codelists"
                xmlns:wsa="http://schemas.xmlsoap.org/ws/2003/03/addressing"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:tns="http://oracle.com/esb/namespaces/Liebert_OrgsOperating"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns2="http://TargetNamespace.com/LBT_FtpPut_OperatingType"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:inp1="http://www.openapplications.org/oagis/9"
                xmlns:ns7="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns9="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                exclude-result-prefixes="xsl ns6 ns8 ns5 ns3 ns0 ns4 tns inp1 ns7 ns9 plnk ns1 wsa soap ns2 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <ns2:OperatingUnitRecord>
      <xsl:for-each select="/inp1:SyncPartyMaster/DataArea">
        <ns2:OperatingTypeRoot>
          <ns2:C1>
            <xsl:value-of select="concat('&quot;',inp1:PartyMaster/inp1:ParentParty/inp1:Location/@type,'&quot;')"/>
          </ns2:C1>
          <ns2:C2>
            <xsl:value-of select="concat('&quot;',inp1:PartyMaster/inp1:ParentParty/inp1:Name,'&quot;')"/>
          </ns2:C2>
        </ns2:OperatingTypeRoot>
      </xsl:for-each>
    </ns2:OperatingUnitRecord>
  </xsl:template>
</xsl:stylesheet>
