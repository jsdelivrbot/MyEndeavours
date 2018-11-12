<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ProcessPOETHReqABCSImpl.wsdl"/>
      <rootElement name="OAGISPO" namespace="http://xmlns.emrsn.com/CommonProcessPO_007"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETH_CommonService_BPELRef.wsdl"/>
      <rootElement name="CreatePurchaseOrderEBM" namespace="http://xmlns.oracle.com/EnterpriseObjects/Core/EBO/PurchaseOrder/V1"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.3.0(build 070615.0525) AT [FRI JUN 06 11:33:00 CDT 2008]. -->
?>
<xsl:stylesheet version="2.0"
                xmlns:ns1="http://xmlns.oracle.com/EnterpriseObjects/Core/EBO/PurchaseOrder/V1"
		xmlns:oag7abo="http://xmlns.emrsn.com/CommonProcessPO_007"
		 
		xmlns:aia="http://www.oracle.com/XSL/Transform/java/oracle.apps.aia.core.xpath.AIAFunctions"
                xmlns:corecom="http://xmlns.oracle.com/EnterpriseObjects/Core/Common/V2"
                xmlns:xacml="urn:oasis:names:tc:xacml:2.0:context:schema:cd:04"
                
		xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:wsa="http://schemas.xmlsoap.org/ws/2003/03/addressing"
                xmlns:hashmap="http://www.oracle.com/XSL/Transform/java/java.util.HashMap"
                exclude-result-prefixes="xsl xref xp20 bpws ora ehdr orcl ids hwf sbldata xacml coresalesorder corecom hashmap wsa aia">
  
  <xsl:template name="EBMHeader">
       <xsl:param name="BusinessScopeIDPath"/>
       <xsl:param name="IntegrationScenarioName"/>
        <corecom:EBMHeader>
            <xsl:variable name="EBMID" select="orcl:generate-guid()"/>
            <corecom:EBMID>
                <xsl:value-of select="$EBMID"/>
            </corecom:EBMID>
            <corecom:EBMName>
                <xsl:text>{http://xmlns.oracle.com/EnterpriseObjects/Core/EBO/PurchaseOrder/V1}ProcessETH_CommonService_BPELEBM</xsl:text>
            </corecom:EBMName>
            <corecom:EBOName>
                <xsl:text>{http://xmlns.oracle.com/EnterpriseObjects/Core/EBO/PurchaseOrder/V1}ProcessETH_CommonService_BPELEBO</xsl:text>
            </corecom:EBOName>
            <corecom:CreationDateTime>
                <xsl:value-of select="xp20:current-dateTime()"/>
            </corecom:CreationDateTime>
            <corecom:VerbCode>
                <xsl:text>Process</xsl:text>
            </corecom:VerbCode>
	    <corecom:MessageProcessingInstruction>
                <xsl:variable name="ConfigServiceName" select="'{http://xmlns.oracle.com/ABCSImpl/ETH/Core/ProcessPOETHReqABCSImpl/V1}ProcessPOETHReqABCSImpl'"/>
                <xsl:variable name="RouteToCAVS" select="aia:getServiceProperty($ConfigServiceName, 'Routing.ETH_CommonService_BPELV1.process.RouteToCAVS', false())='true'"/>
                <corecom:EnvironmentCode>
                    <xsl:choose>
                        <xsl:when test="$RouteToCAVS">
                            <xsl:text>CAVS</xsl:text>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:variable name="EnvCode" select="aia:getServiceProperty($ConfigServiceName,'Routing.ETH_CommonService_BPELV1.process.MessageProcessingInstruction.EnvironmentCode',false())"/>
                            <xsl:choose>
                                <xsl:when test="$EnvCode!=''">
                                    <xsl:value-of select="$EnvCode"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:text>PRODUCTION</xsl:text>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:otherwise>
                    </xsl:choose>
                </corecom:EnvironmentCode>
				<corecom:DefinitionID>
                <xsl:if test="$RouteToCAVS">
                        <xsl:value-of select="aia:getServiceProperty($ConfigServiceName, 'Routing.ETH_CommonService_BPELV1.process.CAVS.EndpointURI', false())"/>
                </xsl:if>
				</corecom:DefinitionID>
                
                <xsl:call-template name="MessageProcessingInstructionType_ext"/>
            </corecom:MessageProcessingInstruction>
	    <xsl:copy-of select="$Sender"/>
            <corecom:Target>
                <corecom:ID/>
                <xsl:call-template name="TargetType_ext"/>
            </corecom:Target>
            <corecom:BusinessScope>
                <corecom:ID>
                    <xsl:value-of select="concat($IntegrationScenarioName,$BusinessScopeIDPath)"/>
                </corecom:ID>
                <corecom:InstanceID>
                    <xsl:value-of select="concat('ProcessETH_CommonService_BPEL/',orcl:generate-guid())"/>
                </corecom:InstanceID>
                <corecom:BusinessScopeTypeCode>BusinessProcess</corecom:BusinessScopeTypeCode>
                <corecom:EnterpriseServiceName>{http://xmlns.oracle.com/Dhami_SOA_jws/ETH_CommonServices_EBS/ETH_CommonService_BPEL}ETH_CommonService_BPELV1</corecom:EnterpriseServiceName>
                <corecom:EnterpriseServiceOperationName>process</corecom:EnterpriseServiceOperationName>
                <xsl:call-template name="BusinessScopeType_ext"/>
            </corecom:BusinessScope>
            <corecom:BusinessScope>
                <corecom:ID>ETH_CommonService_BPELRequestMessage</corecom:ID>
                <corecom:InstanceID>
                    <xsl:value-of select="concat('ETH_CommonService_BPELRequestMessage/',$EBMID)"/>
                </corecom:InstanceID>
                <corecom:BusinessScopeTypeCode>Message</corecom:BusinessScopeTypeCode>
                <corecom:EnterpriseServiceName>{http://xmlns.oracle.com/Dhami_SOA_jws/ETH_CommonServices_EBS/ETH_CommonService_BPEL}ETH_CommonService_BPELV1</corecom:EnterpriseServiceName>
                <corecom:EnterpriseServiceOperationName>process</corecom:EnterpriseServiceOperationName>
                <xsl:call-template name="BusinessScopeType_ext"/>
            </corecom:BusinessScope>
            <corecom:EBMTracking>
                <corecom:SequenceNumber>1</corecom:SequenceNumber>
                <corecom:ExecutionUnitID/>
                <corecom:ExecutionUnitName>{http://xmlns.oracle.com/ABCSImpl/ETH/Core/ProcessPOETHReqABCSImpl/V1}ProcessPOETHReqABCSImpl</corecom:ExecutionUnitName>
                <corecom:ImplementationCode>BPEL</corecom:ImplementationCode>
                <corecom:ActivityDateTime>
                    <xsl:value-of select="xp20:current-dateTime()"/>
                </corecom:ActivityDateTime>
                <xsl:call-template name="EBMTrackingType_ext"/>
            </corecom:EBMTracking>
            <xacml:Request/>
            <xsl:call-template name="EBMHeaderType_ext"/>            
        </corecom:EBMHeader>
        
  </xsl:template>


  
 
  

    <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      lookupOrPopulateXRef template
       1. first attempt lookup in local hashmap of already seen xrefs
       2. if not found, then attempt xref:lookupXRef()
       3. if no value is found, then generate a value and xref:populateXRefRow()
       4. save results in local hashmap
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
    <xsl:template name="lookupOrPopulateXRef">
        <xsl:param name="xrefTableName"/>
        <xsl:param name="xrefReferenceColumnName" select="$XREFSourceCol"/>
        <xsl:param name="xrefReferenceValue"/>
        <xsl:param name="xrefColumnName" select="$XREFTargetCol"/>
        <xsl:variable name="XrefMapKey" select="concat($xrefTableName,':',$xrefReferenceColumnName,':',$xrefReferenceValue,':',$xrefColumnName)"/>
        <xsl:variable name="XrefMapValue" select="hashmap:get($XrefMap,$XrefMapKey)"/>
        <xsl:choose>
            <xsl:when test="$XrefMapValue!=''">
                <xsl:value-of select="$XrefMapValue"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable name="xrefValue" select="xref:lookupXRef($xrefTableName,$xrefReferenceColumnName,$xrefReferenceValue,$xrefColumnName,false())"/>
                <xsl:choose>
                    <xsl:when test="$xrefValue!=''">
                        <xsl:variable name="addToMap" select="hashmap:put($XrefMap,$XrefMapKey,$xrefValue)"/>
                        <xsl:value-of select="$xrefValue"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:variable name="newGuid" select="orcl:generate-guid()"/>
                        <xsl:value-of select="xref:populateXRefRow($xrefTableName,$xrefReferenceColumnName,$xrefReferenceValue,$xrefColumnName,$newGuid,'ANY')"/>
                        <xsl:variable name="addToMap2" select="hashmap:put($XrefMap,$XrefMapKey,$newGuid)"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


    <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      lookupXRef template
      same as above but do not generate a new guid or populate xref.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
    <xsl:template name="lookupXRef">
        <xsl:param name="xrefTableName"/>
        <xsl:param name="xrefReferenceColumnName" select="$XREFSourceCol"/>
        <xsl:param name="xrefReferenceValue"/>
        <xsl:param name="xrefColumnName" select="$XREFTargetCol"/>
        <xsl:param name="xrefNeedException" select="true()"/>
        <xsl:variable name="XrefMapKey" select="concat($xrefTableName,':',$xrefReferenceColumnName,':',$xrefReferenceValue,':',$xrefColumnName)"/>
        <xsl:variable name="XrefMapValue" select="hashmap:get($XrefMap,$XrefMapKey)"/>
        <xsl:choose>
            <xsl:when test="$XrefMapValue!=''">
                <xsl:value-of select="$XrefMapValue"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable name="xrefValue" select="xref:lookupXRef($xrefTableName,$xrefReferenceColumnName,$xrefReferenceValue,$xrefColumnName,$xrefNeedException)"/>
                <xsl:if test="$xrefValue!=''">
                    <xsl:variable name="addToMap" select="hashmap:put($XrefMap,$XrefMapKey,$xrefValue)"/>
                </xsl:if>
                <xsl:value-of select="$xrefValue"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    
    <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      GetSenderNode template
      Determines sender system instance ID and outputs corecom:Sender node
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->    
    <xsl:template name="GetSenderNode">
        <xsl:param name="EnterpriseServerNamePath" select="/*/@EnterpriseServerName"/>
        <xsl:variable name="EnterpriseServerName" select="$EnterpriseServerNamePath"/>
        <xsl:variable name="SenderSystemInfoFromEnterpriseServerName">
            <xsl:if test="$EnterpriseServerName!=''">
                <xsl:copy-of select="aia:getEBMHeaderSenderSystemNode('',$EnterpriseServerName)"/>
            </xsl:if>
        </xsl:variable>
        <xsl:variable name="SenderIDFromEnterpriseServerName" select="$SenderSystemInfoFromEnterpriseServerName/ID/text()"/>
        <xsl:variable name="SenderID">
            <xsl:choose>
                <xsl:when test="$SenderIDFromEnterpriseServerName!=''">
                    <xsl:value-of select="$SenderIDFromEnterpriseServerName"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="aia:getServiceProperty('{http://xmlns.oracle.com/ABCSImpl/ETH/Core/ProcessPOETHReqABCSImpl/V1}ProcessPOETHReqABCSImpl','Default.SystemID',true())"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <xsl:variable name="FinalSenderSystemInfo">
            <xsl:choose>
                <xsl:when test="$SenderIDFromEnterpriseServerName!=''">
                    <xsl:copy-of select="$SenderSystemInfoFromEnterpriseServerName"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:copy-of select="aia:getEBMHeaderSenderSystemNode($SenderID,'')"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <corecom:Sender>
            <corecom:ID>
                <xsl:value-of select="$SenderID"/>
            </corecom:ID>
            <corecom:Description>
                <xsl:value-of select="$FinalSenderSystemInfo/Description"/>
            </corecom:Description>
            <corecom:IPAddress>
                <xsl:value-of select="$FinalSenderSystemInfo/IPAddress"/>
            </corecom:IPAddress>
            <corecom:CallingServiceName>
                <xsl:text>{http://xmlns.oracle.com/ABCSImpl/ETH/Core/ProcessPOETHReqABCSImpl/V1}ProcessPOETHReqABCSImpl</xsl:text>
            </corecom:CallingServiceName>
            <corecom:Application>
                <corecom:ID>
                    <xsl:value-of select="$FinalSenderSystemInfo/Application/ID"/>
                </corecom:ID>
                <corecom:Version>
                    <xsl:value-of select="$FinalSenderSystemInfo/Application/Version"/>
                </corecom:Version>
            </corecom:Application>
            <corecom:ContactName>
                <xsl:value-of select="$FinalSenderSystemInfo/ContactName"/>
            </corecom:ContactName>
            <corecom:ContactEmail>
                <xsl:value-of select="$FinalSenderSystemInfo/ContactEmail"/>
            </corecom:ContactEmail>
            <corecom:ContactPhoneNumber>
                <xsl:value-of select="$FinalSenderSystemInfo/ContactPhone"/>
            </corecom:ContactPhoneNumber>
            <xsl:call-template name="SenderType_ext"/>
        </corecom:Sender>
    </xsl:template>
</xsl:stylesheet>