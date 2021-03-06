<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="Error%20Topic.xsd"/>
      <rootElement name="Error_Topic_Message" namespace="http://xmlns.oracle.com/pcbpel/adapter/jms/"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="http://INEDEC-MOH-03:8888/orabpel/default/CEH_BPEL_CommonErrorHandler/ErrorMessage.xsd"/>
      <rootElement name="errorMessage" namespace="http://schemas.emerson.com/common/errorMessage"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [WED MAR 04 15:10:40 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tns="http://schemas.emerson.com/common/errorMessage"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:jms="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                xmlns:emr="http://schemas.emerson.com/common/envelope"
                exclude-result-prefixes="xsl ns0 jms tns xref xp20 bpws ora ehdr orcl ids hwf emr">
  <xsl:template match="/">
    <tns:errorMessage>
    <tns:errorMessageHeader/>
    <tns:errorMessagePayload>
        <xsl:copy-of select='/jms:Error_Topic_Message/jms:payload/node()'/>
    </tns:errorMessagePayload>    
      <tns:errorMessageDetails>
      
       <xsl:choose>
    <xsl:when test='/jms:Error_Topic_Message/jms:payload/emr:envelope/emr:header'>
        <tns:flowID><xsl:value-of select='/emr:flowID'/></tns:flowID>
        <tns:source><xsl:value-of select='/emr:originalSource'/></tns:source>
        <tns:destination><xsl:value-of select='/emr:destination'/></tns:destination>
        <xsl:choose>
            <xsl:when test='/emr:routingInfo/emr:process[last()]'>
                <tns:processDetails>
                  <tns:processID><xsl:value-of select='@emr:processID'/></tns:processID>
                  <tns:processName><xsl:value-of select='.'/></tns:processName>
                  <tns:processType>ESB</tns:processType>
                  <tns:processLocation><xsl:value-of select='@emr:location'/></tns:processLocation>
                </tns:processDetails>
            </xsl:when>
            <xsl:otherwise>
                <tns:processDetails>
                  <tns:processID/>
                  <tns:processName/>
                  <tns:processType>ESB</tns:processType>
                  <tns:processLocation/>
                </tns:processDetails>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:when>
    <xsl:otherwise>
        <tns:flowID/>
        <tns:source/>
        <tns:destination/>
        <tns:processDetails>
          <tns:processID/>
          <tns:processName/>
          <tns:processType>ESB</tns:processType>
          <tns:processLocation/>
        </tns:processDetails>
    </xsl:otherwise>
  </xsl:choose>
    <tns:errorTime><xsl:value-of select="xp20:current-dateTime()"/></tns:errorTime>
    <tns:environment>LabTest</tns:environment>
    <tns:errorID/>
    <tns:errorMessageCode/>
    <tns:errorReason><xsl:value-of select='/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundProperties/jms:Property[@jms:name="ESB_ERROR_MESSAGE"]/@jms:value'/></tns:errorReason>
    <tns:errorException/>
    <tns:errorStacktrace><xsl:value-of select='/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundProperties/jms:Property[@jms:name="ESB_ERROR_STACK"]/@jms:value'/></tns:errorStacktrace>
        <tns:errorProperties>
            <tns:errorProperty>
              <tns:name>JMSCorrelationID</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSCorrelationID"/></tns:value>
            </tns:errorProperty>
            <tns:errorProperty>
              <tns:name>JMSDeliveryMode</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSDeliveryMode"/></tns:value>
            </tns:errorProperty>
            <tns:errorProperty>
              <tns:name>JMSExpiration</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSExpiration"/></tns:value>
            </tns:errorProperty>
            <tns:errorProperty>
              <tns:name>JMSMessageID</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSMessageID"/></tns:value>
            </tns:errorProperty>
            <tns:errorProperty>
              <tns:name>JMSPriority</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSPriority"/></tns:value>
            </tns:errorProperty>
            <tns:errorProperty>
              <tns:name>JMSRedelivered</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSRedelivered"/></tns:value>
            </tns:errorProperty>
            <tns:errorProperty>
              <tns:name>JMSType</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSType"/></tns:value>
            </tns:errorProperty>
            <tns:errorProperty>
              <tns:name>JMSTimestamp</tns:name>
              <tns:value><xsl:value-of select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundHeaders/jms:JMSTimestamp"/></tns:value>
            </tns:errorProperty>
          <xsl:for-each select="/jms:Error_Topic_Message/jms:JMSInboundHeadersAndProperties/jms:JMSInboundProperties/jms:Property">
            <tns:errorProperty>
              <tns:name><xsl:value-of select="@name"/></tns:name>
              <tns:value><xsl:value-of select="@value"/></tns:value>
            </tns:errorProperty>
          </xsl:for-each>
        </tns:errorProperties>
      </tns:errorMessageDetails>
    </tns:errorMessage>
  </xsl:template>
</xsl:stylesheet>
