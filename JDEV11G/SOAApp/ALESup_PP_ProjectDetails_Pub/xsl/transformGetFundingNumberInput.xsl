<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../xsd/ALE_ESB_CONNECT_ALLETE_PP_WO_PUBLISH_PKG_GET_PROJECT_DETAILS.xsd"/>
      <rootElement name="OutputParameters" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/ALE_ESB_CONNECT/ALLETE_PP_WO_PUBLISH_PKG/GET_PROJECT_DETAILS/"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../xsd/ALE_ESB_CONNECT_ALLETE_PP_WO_PUBLISH_PKG_GET_FUNDING_NUMBER_FROM_ID.xsd"/>
      <rootElement name="InputParameters" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/ALE_ESB_CONNECT/ALLETE_PP_WO_PUBLISH_PKG/GET_FUNDING_NUMBER_FROM_ID/"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.4.0(build 110106.1932.5682) AT [THU APR 07 12:47:26 CDT 2011]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/ALE_ESB_CONNECT/ALLETE_PP_WO_PUBLISH_PKG/GET_FUNDING_NUMBER_FROM_ID/"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:db="http://xmlns.oracle.com/pcbpel/adapter/db/ALE_ESB_CONNECT/ALLETE_PP_WO_PUBLISH_PKG/GET_PROJECT_DETAILS/"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl db xsd ns0 bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:template match="/">
    <ns0:InputParameters>
      <ns0:N_FUNDING_ID_IN>
        <xsl:attribute name="xsi:nil">
          <xsl:value-of select="/db:OutputParameters/db:T_REFERENCE_CURSOR_OUT/db:T_REFERENCE_CURSOR_OUT_Row/db:FUNDING_WO_ID/@xsi:nil"/>
        </xsl:attribute>
        <xsl:value-of select="/db:OutputParameters/db:T_REFERENCE_CURSOR_OUT/db:T_REFERENCE_CURSOR_OUT_Row/db:FUNDING_WO_ID"/>
      </ns0:N_FUNDING_ID_IN>
    </ns0:InputParameters>
  </xsl:template>
</xsl:stylesheet>
