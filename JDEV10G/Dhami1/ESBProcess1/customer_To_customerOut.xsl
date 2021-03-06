<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="customerFileAdapter.wsdl"/>
      <rootElement name="customer" namespace="http://www.emerson.com/inder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="resultOutFileAdapter.wsdl"/>
      <rootElement name="customerOut" namespace="http://www.emerson.com/inderOut"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [MON FEB 16 12:48:53 IST 2009]. -->
?>
<xsl:stylesheet version="1.0" xmlns:ns2="http://www.emerson.com/inderOut"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:imp1="http://www.emerson.com/inder"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/file/resultOutFileAdapter/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/customerFileAdapter/"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/file/"
                exclude-result-prefixes="xsl plt pc ns0 jca imp1 tns hdr ns2 ns1 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <ns2:customerOut>
      <ns2:nameOut>
        <xsl:value-of select="/imp1:customer/imp1:name"/>
      </ns2:nameOut>
      <ns2:ageOut>
        <xsl:value-of select="/imp1:customer/imp1:age"/>
      </ns2:ageOut>
      <ns2:salaryOut>
        <xsl:value-of select="/imp1:customer/imp1:salary"/>
      </ns2:salaryOut>
    </ns2:customerOut>
  </xsl:template>
</xsl:stylesheet>
