<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="RoutingService.wsdl"/>
      <rootElement name="envelope" namespace="http://schemas.emerson.com/common/envelope"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="http://INEDEC-MOH-03:8888/orabpel/default/CEH_BPEL_ProcessCustomer/1.0/CEH_BPEL_ProcessCustomer?wsdl"/>
      <rootElement name="envelope" namespace="http://schemas.emerson.com/common/envelope"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [MON MAR 02 16:25:52 IST 2009]. -->
?>
<xsl:stylesheet version="1.0" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:client="http://bpel.emerson.com/CEH_BPEL_ProcessCustomer"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://esb.emerson.com/dataRouting"
                xmlns:ns2="http://schemas.emerson.com/customer"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:emr="http://schemas.emerson.com/common/envelope"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                exclude-result-prefixes="xsl mime ns0 soap tns emr plnk client ns2 bpws ehdr hwf xp20 xref ora ids orcl emr">
  <xsl:template match="/">
    <emr:envelope>
      <emr:header>
        <emr:flowID>
          <xsl:value-of select="/emr:envelope/emr:header/emr:flowID"/>
        </emr:flowID>
        <emr:transactionID>
          <xsl:value-of select="/emr:envelope/emr:header/emr:transactionID"/>
        </emr:transactionID>
        <emr:originalSource>
          <xsl:value-of select="/emr:envelope/emr:header/emr:originalSource"/>
        </emr:originalSource>
        <emr:routingInfo>
          <xsl:for-each select="/emr:envelope/emr:header/emr:routingInfo/emr:process">
            <emr:process>
              <xsl:attribute name="emr:processID">
                <xsl:value-of select="@emr:processID"/>
              </xsl:attribute>
              <xsl:attribute name="emr:location">
                <xsl:value-of select="@emr:location"/>
              </xsl:attribute>
              <xsl:value-of select="."/>
            </emr:process>
          </xsl:for-each>
          <emr:process>
              <xsl:attribute name="emr:processID">
                <xsl:value-of select="ehdr:getInstanceID()"/>
              </xsl:attribute>CEH_ESB_MessageRouting</emr:process>
        </emr:routingInfo>
        <!--<xsl:if test="/emr:envelope/emr:header/emr:originalSource = CustomerApp">
            <emr:destination>CEH_BPEL_ProcessCustomer</emr:destination>
        </xsl:if>
        <xsl:if test="/emr:envelope/emr:header/emr:originalSource = ProductApp">
            <emr:destination>CEH_BPEL_ProcessProduct</emr:destination>
        </xsl:if>-->
        <emr:destination>CEH_BPEL_ProcessCustomer</emr:destination>
        <emr:userID>
          <xsl:value-of select="/emr:envelope/emr:header/emr:userID"/>
        </emr:userID>
        <emr:headerProperties>
          <xsl:for-each select="/emr:envelope/emr:header/emr:headerProperties/emr:headerProperty">
            <emr:headerProperty>
              <emr:name>
                <xsl:value-of select="emr:name"/>
              </emr:name>
              <emr:value>
                <xsl:value-of select="emr:value"/>
              </emr:value>
            </emr:headerProperty>
          </xsl:for-each>
        </emr:headerProperties>
      </emr:header>
      <emr:body>
    <emr:xmlPayload>
          <xsl:copy-of select="/emr:envelope/emr:body/emr:xmlPayload/node()"/>
    </emr:xmlPayload>
  </emr:body>
    </emr:envelope>
  </xsl:template>
</xsl:stylesheet>
