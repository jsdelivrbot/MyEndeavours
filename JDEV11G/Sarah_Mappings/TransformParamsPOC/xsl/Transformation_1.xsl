<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../xsd/people.xsd"/>
      <rootElement name="People" namespace="http://www.my.poc.in/people"/>
    </source>
    <source type="XSD">
      <schema location="../xsd/empData.xsd"/>
      <rootElement name="People" namespace="http://www.my.poc.in/empdata"/>
      <param name="varEmpData.payload" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../MainBPEL.wsdl"/>
      <rootElement name="processResponse" namespace="http://xmlns.oracle.com/MyApp/TransformParamsPOC/MainBPEL"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.0.0(build 090618.1440.5219) AT [MON APR 26 14:43:03 IST 2010]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xpath20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns0="http://www.my.poc.in/people"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:ns1="http://www.my.poc.in/empdata"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:client="http://xmlns.oracle.com/MyApp/TransformParamsPOC/MainBPEL"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi xsl ns0 ns1 xsd client plnk wsdl xpath20 bpws mhdr oraext dvm hwf med ids xdk xref ora socket">
  <xsl:param name="varEmpData.payload"/>
  <xsl:template match="/">
    <client:processResponse>
      <client:result>
        <xsl:value-of select='concat($varEmpData.payload/ns1:People/ns1:Employee/ns1:fname," ",/ns0:People/ns0:Employee/ns0:name)'/>
      </client:result>
    </client:processResponse>
  </xsl:template>
</xsl:stylesheet>