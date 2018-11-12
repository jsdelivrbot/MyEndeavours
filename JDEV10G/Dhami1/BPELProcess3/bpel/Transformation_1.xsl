<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="http://localhost:8888/orabpel/xmllib/jca/RejectionMessage.wsdl"/>
      <rootElement name="RejectedMessage" namespace="http://xmlns.oracle.com/pcbpel/errorHandling"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="http://localhost:8888/orabpel/xmllib/jca/RejectionMessage.wsdl"/>
      <rootElement name="RejectedMessage" namespace="http://xmlns.oracle.com/pcbpel/errorHandling"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [TUE FEB 17 14:48:57 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:err="http://xmlns.oracle.com/pcbpel/errorHandling"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/rejectionHandler"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                exclude-result-prefixes="xsl err ns0 tns xref xp20 bpws ora ehdr orcl ids hwf">
  <xsl:template match="/">
    <err:RejectedMessage>
      <xsl:attribute name="err:RejectionId">
        <xsl:value-of select="/err:RejectedMessage/@err:RejectionId"/>
      </xsl:attribute>
      <xsl:attribute name="err:BatchId">
        <xsl:value-of select="/err:RejectedMessage/@err:BatchId"/>
      </xsl:attribute>
      <xsl:attribute name="err:BatchInfo">
        <xsl:value-of select="/err:RejectedMessage/@err:BatchInfo"/>
      </xsl:attribute>
      <xsl:attribute name="err:PrimaryKey">
        <xsl:value-of select="/err:RejectedMessage/@err:PrimaryKey"/>
      </xsl:attribute>
      <xsl:attribute name="err:MessageOriginReference">
        <xsl:value-of select="/err:RejectedMessage/@err:MessageOriginReference"/>
      </xsl:attribute>
      <err:MessageHeader>
        <xsl:value-of select="/err:RejectedMessage/err:MessageHeader"/>
      </err:MessageHeader>
      <err:MessagePayload>
        <xsl:value-of select="/err:RejectedMessage/err:MessagePayload"/>
      </err:MessagePayload>
      <err:RejectionReason>
        <xsl:value-of select="/err:RejectedMessage/err:RejectionReason"/>
      </err:RejectionReason>
      <err:AdditionalProperties>
        <err:RejectionProp>
          <xsl:attribute name="err:name">
            <xsl:value-of select="/err:RejectedMessage/err:AdditionalProperties/err:RejectionProp/@err:name"/>
          </xsl:attribute>
          <xsl:attribute name="err:value">
            <xsl:value-of select="/err:RejectedMessage/err:AdditionalProperties/err:RejectionProp/@err:value"/>
          </xsl:attribute>
        </err:RejectionProp>
      </err:AdditionalProperties>
    </err:RejectedMessage>
  </xsl:template>
</xsl:stylesheet>
