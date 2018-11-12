<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="VDST_JMSQ_DEQ_ChannelPartnerEBS.wsdl"/>
      <rootElement name="envelope" namespace="http://schemas.emerson.com/common/envelope"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="VDST_JMSQ_ENQ_ChannelPartner_PartMap.wsdl"/>
      <rootElement name="envelope" namespace="http://schemas.emerson.com/common/envelope"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU JUL 02 15:49:03 GMT+05:30 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/jms/VDST_JMSQ_DEQ_ChannelPartnerEBS/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/jms/VDST_JMSQ_ENQ_ChannelPartner_PartMap/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:imp1="http://schemas.emerson.com/common/envelope"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/jms/"
                exclude-result-prefixes="xsl plt pc tns ns0 jca imp1 hdr ns1 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
  <xsl:variable name="ExecutionUnitId" select="ehdr:getInstanceID()"/>
    <xsl:variable name="ExecutionUnitName" select="string('VDST_ESB_ChannelPartnerEBS')"/>
    <xsl:variable name="ExecutionUnitType" select="string('ESB')"/>
    <xsl:variable name="CurrentExecutionTime" select="xp20:current-dateTime()"/>
    <imp1:envelope>
      <imp1:header>
        <imp1:EBMID>
          <xsl:value-of select="/imp1:envelope/imp1:header/imp1:EBMID"/>
        </imp1:EBMID>
        <imp1:EBOName>
          <xsl:value-of select="/imp1:envelope/imp1:header/imp1:EBOName"/>
        </imp1:EBOName>
        <imp1:RequestEBMID>
          <xsl:value-of select="/imp1:envelope/imp1:header/imp1:RequestEBMID"/>
        </imp1:RequestEBMID>
        <imp1:InteractionID>
          <xsl:value-of select="/imp1:envelope/imp1:header/imp1:InteractionID"/>
        </imp1:InteractionID>
        <imp1:CreationDateTime>
          <xsl:value-of select="/imp1:envelope/imp1:header/imp1:CreationDateTime"/>
        </imp1:CreationDateTime>
        <imp1:VerbCode>
          <xsl:value-of select="/imp1:envelope/imp1:header/imp1:VerbCode"/>
        </imp1:VerbCode>
        <imp1:MessageProcessingInstruction>
          <imp1:EnvironmentCode>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:MessageProcessingInstruction/imp1:EnvironmentCode"/>
          </imp1:EnvironmentCode>
          <imp1:DefinitionID>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:MessageProcessingInstruction/imp1:DefinitionID"/>
          </imp1:DefinitionID>
          <imp1:InstanceID>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:MessageProcessingInstruction/imp1:InstanceID"/>
          </imp1:InstanceID>
        </imp1:MessageProcessingInstruction>
        <imp1:Sender>
          <imp1:ID>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:ID"/>
          </imp1:ID>
          <xsl:for-each select="/imp1:envelope/imp1:header/imp1:Sender/imp1:Description">
            <imp1:Description>
              <xsl:value-of select="."/>
            </imp1:Description>
          </xsl:for-each>
          <imp1:IPAddress>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:IPAddress"/>
          </imp1:IPAddress>
          <imp1:SenderMessageID>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:SenderMessageID"/>
          </imp1:SenderMessageID>
          <imp1:TransactionCode>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:TransactionCode"/>
          </imp1:TransactionCode>
          <imp1:CallingServiceName>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:CallingServiceName"/>
          </imp1:CallingServiceName>
          <imp1:Application>
            <imp1:ID>
              <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:Application/imp1:ID"/>
            </imp1:ID>
            <imp1:Version>
              <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:Application/imp1:Version"/>
            </imp1:Version>
          </imp1:Application>
          <imp1:ContactName>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:ContactName"/>
          </imp1:ContactName>
          <imp1:ContactEmail>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:ContactEmail"/>
          </imp1:ContactEmail>
          <imp1:ContactPhoneNumber>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Sender/imp1:ContactPhoneNumber"/>
          </imp1:ContactPhoneNumber>
          <xsl:for-each select="/imp1:envelope/imp1:header/imp1:Sender/imp1:ESBHeaderExtension">
            <imp1:ESBHeaderExtension>
              <imp1:Name>
                <xsl:value-of select="imp1:Name"/>
              </imp1:Name>
              <imp1:DataType>
                <xsl:value-of select="imp1:DataType"/>
              </imp1:DataType>
              <imp1:Value>
                <xsl:value-of select="imp1:Value"/>
              </imp1:Value>
            </imp1:ESBHeaderExtension>
          </xsl:for-each>
        </imp1:Sender>
        <imp1:Target>
          <imp1:ID>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Target/imp1:ID"/>
          </imp1:ID>
          <imp1:OverrideRoutingRuleIndicator>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Target/imp1:OverrideRoutingRuleIndicator"/>
          </imp1:OverrideRoutingRuleIndicator>
          <imp1:ServiceName>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Target/imp1:ServiceName"/>
          </imp1:ServiceName>
          <imp1:ApplicationTypeCode>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Target/imp1:ApplicationTypeCode"/>
          </imp1:ApplicationTypeCode>
          <imp1:EndpointURI>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:Target/imp1:EndpointURI"/>
          </imp1:EndpointURI>
        </imp1:Target>
        <imp1:BusinessScope>
          <imp1:ID>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:BusinessScope/imp1:ID"/>
          </imp1:ID>
          <imp1:InstanceID>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:BusinessScope/imp1:InstanceID"/>
          </imp1:InstanceID>
          <imp1:BusinessScopeTypeCode>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:BusinessScope/imp1:BusinessScopeTypeCode"/>
          </imp1:BusinessScopeTypeCode>
          <imp1:EnterpriseServiceName>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:BusinessScope/imp1:EnterpriseServiceName"/>
          </imp1:EnterpriseServiceName>
          <imp1:EnterpriseServiceOperationName>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:BusinessScope/imp1:EnterpriseServiceOperationName"/>
          </imp1:EnterpriseServiceOperationName>
        </imp1:BusinessScope>
        <imp1:EBMTracking>
          <xsl:attribute name="imp1:executionUnitCount">
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:EBMTracking/@imp1:executionUnitCount"/>
          </xsl:attribute>
          <xsl:for-each select="/imp1:envelope/imp1:header/imp1:EBMTracking/imp1:ExecutionUnit">
            <imp1:ExecutionUnit>
              <imp1:SequenceNumber>
                <xsl:value-of select="imp1:SequenceNumber"/>
              </imp1:SequenceNumber>
              <imp1:ExecutionUnitID>
                <xsl:value-of select="imp1:ExecutionUnitID"/>
              </imp1:ExecutionUnitID>
              <imp1:ExecutionUnitName>
                <xsl:value-of select="imp1:ExecutionUnitName"/>
              </imp1:ExecutionUnitName>
              <imp1:ImplementationCode>
                <xsl:value-of select="imp1:ImplementationCode"/>
              </imp1:ImplementationCode>
              <imp1:ActivityDateTime>
                <xsl:value-of select="imp1:ActivityDateTime"/>
              </imp1:ActivityDateTime>
            </imp1:ExecutionUnit>
          </xsl:for-each>
          <imp1:ExecutionUnit>
              <imp1:SequenceNumber>
                <xsl:value-of select="1.0"/>
              </imp1:SequenceNumber>
              <imp1:ExecutionUnitID>
                <xsl:value-of select="$ExecutionUnitId"/>
              </imp1:ExecutionUnitID>
              <imp1:ExecutionUnitName>
                <xsl:value-of select="$ExecutionUnitName"/>
              </imp1:ExecutionUnitName>
              <imp1:ImplementationCode>
                <xsl:value-of select="$ExecutionUnitType"/>
              </imp1:ImplementationCode>
              <imp1:ActivityDateTime>
                <xsl:value-of select="$CurrentExecutionTime"/>
              </imp1:ActivityDateTime>
            </imp1:ExecutionUnit>
        </imp1:EBMTracking>
        <imp1:UserID>
          <xsl:value-of select="/imp1:envelope/imp1:header/imp1:UserID"/>
        </imp1:UserID>
        <imp1:HeaderProperties>
          <xsl:for-each select="/imp1:envelope/imp1:header/imp1:HeaderProperties/imp1:HeaderProperty">
            <imp1:HeaderProperty>
              <imp1:Name>
                <xsl:value-of select="imp1:Name"/>
              </imp1:Name>
              <imp1:DataType>
                <xsl:value-of select="imp1:DataType"/>
              </imp1:DataType>
              <imp1:Value>
                <xsl:value-of select="imp1:Value"/>
              </imp1:Value>
            </imp1:HeaderProperty>
          </xsl:for-each>
        </imp1:HeaderProperties>
        <imp1:LogDetails>
          <imp1:logTime>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:LogDetails/imp1:logTime"/>
          </imp1:logTime>
          <imp1:environment>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:LogDetails/imp1:environment"/>
          </imp1:environment>
          <imp1:serverName>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:LogDetails/imp1:serverName"/>
          </imp1:serverName>
          <imp1:logLevel>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:LogDetails/imp1:logLevel"/>
          </imp1:logLevel>
        </imp1:LogDetails>
        <imp1:ErrorDetails>
          <imp1:process>
            <xsl:attribute name="imp1:processID">
              <xsl:value-of select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:process/@imp1:processID"/>
            </xsl:attribute>
            <xsl:attribute name="imp1:location">
              <xsl:value-of select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:process/@imp1:location"/>
            </xsl:attribute>
            <xsl:attribute name="imp1:processDateTime">
              <xsl:value-of select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:process/@imp1:processDateTime"/>
            </xsl:attribute>
            <xsl:attribute name="imp1:type">
              <xsl:value-of select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:process/@imp1:type"/>
            </xsl:attribute>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:process"/>
          </imp1:process>
          <imp1:environment>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:environment"/>
          </imp1:environment>
          <imp1:server>
            <xsl:value-of select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:server"/>
          </imp1:server>
          <xsl:for-each select="/imp1:envelope/imp1:header/imp1:ErrorDetails/imp1:error">
            <imp1:error>
              <imp1:errorID>
                <xsl:value-of select="imp1:errorID"/>
              </imp1:errorID>
              <imp1:errorTime>
                <xsl:value-of select="imp1:errorTime"/>
              </imp1:errorTime>
              <imp1:severity>
                <xsl:value-of select="imp1:severity"/>
              </imp1:severity>
              <imp1:errorMessageCode>
                <xsl:value-of select="imp1:errorMessageCode"/>
              </imp1:errorMessageCode>
              <imp1:errorMessage>
                <xsl:value-of select="imp1:errorMessage"/>
              </imp1:errorMessage>
              <imp1:errorException>
                <xsl:value-of select="imp1:errorException"/>
              </imp1:errorException>
              <imp1:errorStacktrace>
                <xsl:value-of select="imp1:errorStacktrace"/>
              </imp1:errorStacktrace>
            </imp1:error>
          </xsl:for-each>
        </imp1:ErrorDetails>
      </imp1:header>
      <imp1:body>
        <imp1:xmlPayload>
            <xsl:copy-of select="/imp1:envelope/imp1:body/imp1:xmlPayload/node()"/>
        </imp1:xmlPayload>
      </imp1:body>
    </imp1:envelope>
  </xsl:template>
</xsl:stylesheet>