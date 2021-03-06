<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="xsd/projectWoSvc.xsd"/>
      <rootElement name="projectDetailsCanonical" namespace="http://www.allete.com/ns/Allete/BusinessSupport/Projects/projectWoSvc/V1"/>
    </source>
    <source type="XSD">
      <schema location="../xsd/DB_Insert_AlePpWoSubscribe_table.xsd"/>
      <rootElement name="AleppwosubscribeCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/DB_Insert_AlePpWoSubscribe"/>
      <param name="dbInsertAlePpWoSubscribeOutput.AleppwosubscribeCollection" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="xsd/alleteInterfaceMetadata.xsd"/>
      <rootElement name="InterfaceStatus" namespace="http://www.allete.com/ns/BusinessSupport/Common/alleteInterfaceMetadata/v1"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.4.0(build 110106.1932.5682) AT [FRI MAY 13 17:16:39 CDT 2011]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/DB_Insert_AlePpWoSubscribe"
                xmlns:projWoTypes="http://www.allete.com/ns/Allete/BusinessSupport/Projects/projectWoTypes/V1"
                xmlns:ns1="http://www.allete.com/ns/interfaceMetadata/v1"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns2="http://www.allete.com/ns/BusinessSupport/Common/alleteInterfaceMetadata/v1"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:projWo="http://www.allete.com/ns/Allete/BusinessSupport/Projects/projectWoSvc/V1"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl ns0 projWoTypes ns1 xsd projWo ns2 bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:param name="dbInsertAlePpWoSubscribeOutput.AleppwosubscribeCollection"/>
  <xsl:template match="/">
    <ns2:InterfaceStatus>
      <ns1:RiceId>
        <xsl:attribute name="xsi:nil">
          <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:RiceId/@xsi:nil"/>
        </xsl:attribute>
        <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:RiceId"/>
      </ns1:RiceId>
      <ns1:SourceSystem>
        <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:SourceSystem"/>
      </ns1:SourceSystem>
      <ns1:TransactionId>
        <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:TransactionId"/>
      </ns1:TransactionId>
      <ns1:TransactionType>
        <xsl:attribute name="xsi:nil">
          <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:TransactionType/@xsi:nil"/>
        </xsl:attribute>
        <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:TransactionType"/>
      </ns1:TransactionType>
      <ns1:TransactionDate>
        <xsl:attribute name="xsi:nil">
          <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:TransactionDate/@xsi:nil"/>
        </xsl:attribute>
        <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:TransactionDate"/>
      </ns1:TransactionDate>
      <ns1:RecordCount>
        <xsl:attribute name="xsi:nil">
          <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:RecordCount/@xsi:nil"/>
        </xsl:attribute>
        <xsl:value-of select="/projWo:projectDetailsCanonical/projWoTypes:projectDetails/projWoTypes:InterfaceMetadata/ns1:RecordCount"/>
      </ns1:RecordCount>
      <ns1:Processed>
        <xsl:value-of select="count($dbInsertAlePpWoSubscribeOutput.AleppwosubscribeCollection/ns0:AleppwosubscribeCollection/ns0:Aleppwosubscribe)"/>
      </ns1:Processed>
    </ns2:InterfaceStatus>
  </xsl:template>
</xsl:stylesheet>
