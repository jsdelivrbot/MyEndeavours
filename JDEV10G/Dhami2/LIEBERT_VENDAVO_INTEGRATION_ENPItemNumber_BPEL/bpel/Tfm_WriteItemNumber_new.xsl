<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="DBUpdITEMNUMOUTBOUND_table.xsd"/>
      <rootElement name="ItemNumberOutboundCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/DBUpdITEMNUMOUTBOUND"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="ItemMaster_1.xsd"/>
      <rootElement name="Root-Element" namespace="http://TargetNamespace.com/InboundService"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [WED JUL 08 12:35:37 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/DBUpdITEMNUMOUTBOUND"
                xmlns:nxsd="http://xmlns.oracle.com/pcbpel/nxsd"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:tns="http://TargetNamespace.com/InboundService"
                exclude-result-prefixes="xsl xs ns0 nxsd tns bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <tns:Root-Element>
      <xsl:for-each select="/ns0:ItemNumberOutboundCollection/ns0:ItemNumberOutbound">
        <tns:rec>
          <tns:VName>
            <xsl:value-of select="concat('&quot;',ns0:itemNbr,'&quot;')"/>
          </tns:VName>
          <tns:VLabel>
            <xsl:value-of select="concat('&quot;',ns0:itemDescription,'&quot;')"/>
          </tns:VLabel>
          <tns:enpsCenterOfExpertise>
            <xsl:value-of select="concat('&quot;',ns0:centerOfExpertiseId,'&quot;')"/>
          </tns:enpsCenterOfExpertise>
          <tns:enpsProductSegment>
            <xsl:value-of select="concat('&quot;',ns0:productSegmentId,'&quot;')"/>
          </tns:enpsProductSegment>
          <tns:enpsProductLIneId>
            <xsl:value-of select="concat('&quot;',ns0:productlineCode,'&quot;')"/>
          </tns:enpsProductLIneId>
          <tns:enpsModelLineId>
            <xsl:value-of select="concat('&quot;',ns0:modellineCode,'&quot;')"/>
          </tns:enpsModelLineId>
          <tns:enpssubModelId>
            <xsl:value-of select="concat('&quot;',ns0:submodellineCode,'&quot;')"/>
          </tns:enpssubModelId>
          <tns:Package_Description>
            <xsl:value-of select="concat('&quot;',ns0:issueUomName,'&quot;')"/>
          </tns:Package_Description>
          <tns:Status>
            <xsl:value-of select="concat('&quot;',ns0:itemStatus,'&quot;')"/>
          </tns:Status>
          <tns:Target_State>
            <xsl:value-of select="concat('&quot;',ns0:targetState,'&quot;')"/>
          </tns:Target_State>
          <tns:Local_Flag>
            <xsl:value-of select="ns0:localFlag"/>
          </tns:Local_Flag>
          <tns:Service_Flag>
            <xsl:value-of select="ns0:serviceFlag"/>
          </tns:Service_Flag>
        </tns:rec>
      </xsl:for-each>
    </tns:Root-Element>
  </xsl:template>
</xsl:stylesheet>
