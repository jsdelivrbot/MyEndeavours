<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="JCAErrorHandling.xsd"/>
      <rootElement name="RejectedMessage" namespace="http://xmlns.oracle.com/pcbpel/errorHandling"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="http://inedec-moh-03:8888/orabpel/default/CEH_BPEL_CommonErrorHandler/ErrorMessage.xsd"/>
      <rootElement name="errorMessage" namespace="http://schemas.emerson.com/common/errorMessage"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [TUE MAR 03 20:48:00 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:sns="http://xmlns.oracle.com/pcbpel/errorHandling"
                xmlns:tns="http://schemas.emerson.com/common/errorMessage"
                xmlns:emr="http://schemas.emerson.com/common/envelope"
                exclude-result-prefixes="xsl ns0 xref xp20 bpws ora ehdr orcl ids hwf sns tns emr">
  <xsl:template match="/">
  <tns:errorMessage>
  <tns:errorMessageHeader>
    <xsl:copy-of select='/sns:RejectedMessage/sns:MessageHeader/node()'/>
  </tns:errorMessageHeader>
  <tns:errorMessagePayload>
    <xsl:copy-of select='/sns:RejectedMessage/sns:MessagePayload/node()'/>
  </tns:errorMessagePayload>
  <tns:errorMessageDetails>
  <xsl:choose>
    <xsl:when test='/sns:RejectedMessage/sns:MessagePayload/emr:envelope/emr:header'>
        <tns:flowID><xsl:value-of select='/emr:flowID'/></tns:flowID>
        <tns:source><xsl:value-of select='/emr:originalSource'/></tns:source>
        <tns:destination><xsl:value-of select='/emr:destination'/></tns:destination>
        <xsl:choose>
            <xsl:when test='/emr:routingInfo/emr:process[last()]'>
                <tns:processDetails>
                  <tns:processID><xsl:value-of select='@emr:processID'/></tns:processID>
                  <tns:processName><xsl:value-of select='.'/></tns:processName>
                  <tns:processType>BPEL</tns:processType>
                  <tns:processLocation><xsl:value-of select='@emr:location'/></tns:processLocation>
                </tns:processDetails>
            </xsl:when>
            <xsl:otherwise>
                <tns:processDetails>
                  <tns:processID/>
                  <tns:processName/>
                  <tns:processType>BPEL</tns:processType>
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
          <tns:processType>BPEL</tns:processType>
          <tns:processLocation/>
        </tns:processDetails>
    </xsl:otherwise>
  </xsl:choose>
    <tns:errorTime><xsl:value-of select="xp20:current-dateTime()"/></tns:errorTime>
    <tns:environment>LabTest</tns:environment>
    <tns:errorID><xsl:value-of select='/sns:RejectedMessage/@sns:RejectionId'/></tns:errorID>
    <tns:errorMessageCode/>
    <tns:errorReason><xsl:value-of select='/sns:RejectedMessage/sns:RejectionReason'/></tns:errorReason>
    <tns:errorException/>
    <tns:errorStacktrace/>
    <tns:errorProperties>
       <tns:errorProperty>
        <tns:name>BatchId</tns:name>
        <tns:value><xsl:value-of select='/sns:RejectedMessage/@sns:BatchId'/></tns:value>
      </tns:errorProperty>
      <tns:errorProperty>
        <tns:name>BatchInfo</tns:name>
        <tns:value><xsl:value-of select='/sns:RejectedMessage/@sns:BatchInfo'/></tns:value>
      </tns:errorProperty>
      <tns:errorProperty>
        <tns:name>PrimaryKey</tns:name>
        <tns:value><xsl:value-of select='/sns:RejectedMessage/@sns:PrimaryKey'/></tns:value>
      </tns:errorProperty>
      <tns:errorProperty>
        <tns:name>MessageOriginReference</tns:name>
        <tns:value><xsl:value-of select='/sns:RejectedMessage/@sns:MessageOriginReference'/></tns:value>
      </tns:errorProperty>
        <xsl:for-each select='/sns:RejectedMessage/sns:AdditionalProperties/sns:RejectionProp'>
            <tns:errorProperty>
                <tns:name><xsl:value-of select='@sns:name'/></tns:name>
                <tns:value><xsl:value-of select='@sns:value'/></tns:value>
            </tns:errorProperty>
        </xsl:for-each>
    </tns:errorProperties>
  </tns:errorMessageDetails>
</tns:errorMessage>
  </xsl:template>
</xsl:stylesheet>