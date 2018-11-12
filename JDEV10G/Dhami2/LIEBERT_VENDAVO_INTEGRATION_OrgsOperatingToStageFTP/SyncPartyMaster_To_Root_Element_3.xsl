<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="Liebert_OrgsOperating_LBT_Rtr_OperatingunitFTP.wsdl"/>
      <rootElement name="SyncPartyMaster" namespace="http://www.openapplications.org/oagis/9"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="http://essapt020-u006:12001/orabpel/LiebertServices/LIEBERT_VENDAVO_INTEGRATION_ENPOperatingUnit_BPEL/1.0/LIEBERT_VENDAVO_INTEGRATION_ENPOperatingUnit_BPEL?wsdl"/>
      <rootElement name="Root-Element" namespace="http://TargetNamespace.com/FtpOpertngUnit_to_Vendavo"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU NOV 20 13:28:45 IST 2008]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                exclude-result-prefixes="xsl xref xp20 bpws ora ehdr orcl ids hwf">
  <xsl:template match="/">
  </xsl:template>
</xsl:stylesheet>