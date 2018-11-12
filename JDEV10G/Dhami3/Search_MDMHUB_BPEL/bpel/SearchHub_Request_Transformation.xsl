<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../MDMSchemas/ServiceSchemas/ProcessCustomerService.xsd"/>
      <rootElement name="ESBProcessCustomerRequest" namespace="http://www.gssamerica.com/mdm/esbprocesscustomer"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../MDMSchemas/ServiceSchemas/MDMService.xsd"/>
      <rootElement name="SearchRecordRequest" namespace="http://www.gssamerica.com/mdm/services"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.3.0(build 070615.0525) AT [WED JUN 25 18:24:41 IST 2008]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:sample="http://www.oracle.com/XSL/Transform/java/esbext.func.ESBExtensionFunctions"
                xmlns:ns5="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns0="http://www.gssamerica.com/mdm/esbprocesscustomer"
                xmlns:ns7="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ns4="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns1="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:ns2="http://www.openapplications.org/oagis/9/codelists"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:tns="http://www.gssamerica.com/mdm/services"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns3="http://www.openapplications.org/oagis/9"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns8="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                exclude-result-prefixes="xsl ns5 ns0 ns7 ns3 ns1 xs ns2 ns3 ns6 ns8 tns sample bpws ehdr hwf xp20 xref ora ids orcl"
                xmlns:nsext="http://www.gssamerica.com/mdm/oagisextensions">
  <xsl:template match="/">
      <tns:SearchRecordRequest>
        <tns:query>
          <tns:recordCriteria>
            <xsl:for-each select="/ns0:ESBProcessCustomerRequest/ns0:body/ns3:ProcessCustomerPartyMaster/ns3:DataArea/ns3:CustomerPartyMaster">
                <tns:recordIdentifierCriteria>
                    <tns:recordId>
                        <xsl:value-of select="ns3:UserArea/nsext:CustomerPartyExtensions/nsext:ControlExtentions/nsext:RecordId"/>
                    </tns:recordId>
                </tns:recordIdentifierCriteria>
                <!--<tns:recordIdentifierCriteria>
                    <xsl:if test="ns3:UserArea/nsext:CustomerPartyExtensions/nsext:ControlExtentions/nsext:AutoId">
                        <tns:autoId>
                            <xsl:value-of select="ns3:UserArea/nsext:CustomerPartyExtensions/nsext:ControlExtentions/nsext:AutoId"/>
                        </tns:autoId>
                    </xsl:if>
                    <xsl:if test="ns3:UserArea/nsext:CustomerPartyExtensions/nsext:ControlExtentions/nsext:ExternalId">
                        <tns:externalId>
                            <xsl:value-of select="ns3:UserArea/nsext:CustomerPartyExtensions/nsext:ControlExtentions/nsext:ExternalId"/>
                        </tns:externalId>
                    </xsl:if>
                    <xsl:if test="ns3:UserArea/nsext:CustomerPartyExtensions/nsext:ControlExtentions/nsext:RemoteId">
                        <tns:remoteKey>
                          <tns:key>
                            <tns:isDefault>true</tns:isDefault>
                            <tns:value>
                                <xsl:value-of select="ns3:UserArea/nsext:CustomerPartyExtensions/nsext:ControlExtentions/nsext:RemoteId"/>
                            </tns:value>
                          </tns:key>
                          <tns:remoteSystem>
                            <xsl:value-of select="/env:Envelope/env:Body/ns0:ESBProcessCustomerRequest/ns0:header/ns0:srcID"/>
                          </tns:remoteSystem>
                        </tns:remoteKey>
                    </xsl:if>
                </tns:recordIdentifierCriteria>-->
            </xsl:for-each>
            <tns:tableCode>
		<xsl:text disable-output-escaping="no">Person</xsl:text>      
            </tns:tableCode>
          </tns:recordCriteria>
        </tns:query>
      <tns:repositoryInfo>
        <tns:dataLang>
          <xsl:text disable-output-escaping="no">string</xsl:text>
        </tns:dataLang>
        <tns:repositoryName>
         <xsl:text disable-output-escaping="no">test</xsl:text>
        </tns:repositoryName>
        <tns:repositoryPort>
          <xsl:text disable-output-escaping="no">1521</xsl:text>
        </tns:repositoryPort>
        <tns:serverName>
         <xsl:text disable-output-escaping="no">gdc-17</xsl:text>
        </tns:serverName>
        <tns:schemaLang>
         <xsl:text disable-output-escaping="no">string</xsl:text>
        </tns:schemaLang>
      </tns:repositoryInfo>
		<tns:configData>
            <tns:element>
                   <tns:name>
                      <xsl:text disable-output-escaping="no">srcID</xsl:text>
                   </tns:name>
                   <tns:value>
                       <xsl:value-of select="/ns0:ESBProcessCustomerRequest/ns0:header/ns0:srcID"/>
                   </tns:value>              
             </tns:element>
            <tns:element>
                   <tns:name>
                      <xsl:text disable-output-escaping="no">entity</xsl:text>
                   </tns:name>
                   <tns:value>
                       <xsl:value-of select="/ns0:ESBProcessCustomerRequest/ns0:header/ns0:entity"/>
                   </tns:value>              
             </tns:element>
              <tns:element>
                   <tns:name>
                      <xsl:text disable-output-escaping="no">transactionID</xsl:text>
                   </tns:name>
                   <tns:value>
                       <xsl:value-of select="/ns0:ESBProcessCustomerRequest/ns0:header/ns0:transactionID"/>
                   </tns:value>              
             </tns:element>
             <tns:element>
                   <tns:name>
                      <xsl:text disable-output-escaping="no">userID</xsl:text>
                   </tns:name>
                   <tns:value>
                       <xsl:value-of select="/ns0:ESBProcessCustomerRequest/ns0:header/ns0:userID"/>
                   </tns:value>              
             </tns:element>
       </tns:configData>
        
      </tns:SearchRecordRequest>
  </xsl:template>
</xsl:stylesheet>
