<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="CEH_FL_GET_ReceiveCustomer.wsdl"/>
      <rootElement name="customer" namespace="http://schemas.emerson.com/customer"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="http://INEDEC-MOH-03:8888/orabpel/default/CEH_BPEL_ReceiveData/1.0/CEH_BPEL_ReceiveData?wsdl"/>
      <rootElement name="envelope" namespace="http://schemas.emerson.com/common/envelope"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [FRI FEB 27 12:36:06 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns1="http://xmlns.oracle.com/CEH_BPEL_ReceiveData"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:wsa="http://schemas.xmlsoap.org/ws/2003/03/addressing"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/CEH_FL_GET_ReceiveCustomer/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:imp1="http://schemas.emerson.com/customer"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ns2="http://schemas.emerson.com/common/envelope"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/file/"
                xmlns:emr="http://schemas.emerson.com/common/envelope"
                exclude-result-prefixes="xsl plt pc ns0 jca tns imp1 hdr ns1 wsa soap ns2 bpws ehdr hwf xp20 xref ora ids orcl emr">
  <xsl:template match="/">
  <xsl:variable name="guid" select="orcl:generate-guid()" />
  <xsl:variable name="instanceid" select="ehdr:getInstanceID()" />
    <emr:envelope>
  <emr:header>
    <emr:flowID><xsl:value-of select="$guid"/></emr:flowID>
    <emr:transactionID><xsl:value-of select="$guid"/></emr:transactionID>
    <emr:originalSource>CustomerApp</emr:originalSource>
    <emr:routingInfo>
        <emr:process emr:processID="{$instanceid}">CEH_ESB_FetchCustomerData</emr:process>
    </emr:routingInfo>
    <emr:destination>CEH_BPEL_Receivedata</emr:destination>
    <emr:userID>Unknown</emr:userID>
    <emr:headerProperties/>
  </emr:header>
  <emr:body>
    <emr:xmlPayload>
      <xsl:copy-of select="."/>
    </emr:xmlPayload>
  </emr:body>
</emr:envelope>
</xsl:template>
</xsl:stylesheet>
