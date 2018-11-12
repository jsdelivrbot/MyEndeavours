<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="VDST_CPL.xsd"/>
      <rootElement name="CPL-Load" namespace="http://schema.emerson.com/VDST_FTP_GET_ChannelPartnerList"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="EMR_CTE.xsd"/>
      <rootElement name="body" namespace="http://schemas.emerson.com/common/envelope"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU JUL 02 01:16:24 GMT+05:30 2009]. -->
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
                xmlns:trg="http://schemas.emerson.com/common/envelope"
                exclude-result-prefixes="xsl xref xp20 bpws ora ehdr orcl ids hwf">
  <xsl:template match="/">
  <trg:body>
        <trg:xmlPayload>
            <xsl:copy-of select="."/>
        </trg:xmlPayload>
    </trg:body>
  </xsl:template>
</xsl:stylesheet>
