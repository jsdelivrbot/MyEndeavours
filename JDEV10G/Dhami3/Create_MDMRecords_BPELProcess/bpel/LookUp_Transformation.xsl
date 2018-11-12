<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="MDMService.xsd"/>
      <rootElement name="CreateRecordsRequest" namespace="http://www.gssamerica.com/mdm/services"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="MDMService.xsd"/>
      <rootElement name="LookupSearchRequest" namespace="http://www.gssamerica.com/mdm/services"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.3.0(build 070615.0525) AT [WED JUN 11 19:42:27 IST 2008]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:sample="http://www.oracle.com/XSL/Transform/java/esbext.func.ESBExtensionFunctions"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:tns="http://www.gssamerica.com/mdm/services"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                exclude-result-prefixes="xsl xs tns xref sample xp20 bpws ora ehdr orcl ids hwf">
  <xsl:template match="/">
     <tns:SearchRecordRequest>
      <tns:query>
        <tns:recordCriteria>
          <xsl:for-each select="/tns:CreateRecordsRequest/tns:record/tns:dataElement">
            <tns:dataElementCriteria>
              <tns:fieldCode>
                <xsl:value-of select="tns:field/tns:unifiedField/tns:fieldName/tns:name"/>
              </tns:fieldCode>
              <tns:dataType>
                <xsl:value-of select="tns:field/tns:dataType"/>
              </tns:dataType>
              <xsl:if test="tns:simpleValue">
                <tns:simpleValueCriteria>
                  <tns:value>
                    <xsl:value-of select="tns:simpleValue/tns:unifiedValue/tns:value/tns:value"/>
                  </tns:value>
                </tns:simpleValueCriteria>
              </xsl:if>
              <xsl:if test="tns:lookupValue">
                <tns:lookupValueCriteria>
                  <tns:tableCode>
                    <xsl:value-of select="tns:lookupValue/tns:record/tns:recordIdentifier/tns:table/tns:tableName/tns:name"/>
                  </tns:tableCode>
                  <xsl:for-each select="tns:lookupValue/tns:record/tns:dataElement">
                    <tns:dataElementCriteria>
                      <tns:fieldCode>
                        <xsl:value-of select="tns:field/tns:unifiedField/tns:fieldName/tns:name"/>
                      </tns:fieldCode>
                      <tns:dataType>
                        <xsl:value-of select="tns:field/tns:dataType"/>
                      </tns:dataType>
                      <xsl:if test="tns:simpleValue">
                        <tns:simpleValueCriteria>
                          <tns:value>
                            <xsl:value-of select="tns:simpleValue/tns:unifiedValue/tns:value/tns:value"/>
                          </tns:value>
                        </tns:simpleValueCriteria>
                      </xsl:if>
                      <xsl:if test="tns:lookupValue">
                        <tns:lookupValueCriteria>
                          <tns:tableCode>
                            <xsl:value-of select="tns:lookupValue/tns:record/tns:recordIdentifier/tns:table/tns:tableName/tns:name"/>
                          </tns:tableCode>
                          <xsl:for-each select="tns:lookupValue/tns:record/tns:dataElement">
                            <tns:dataElementCriteria>
                              <tns:fieldCode>
                                <xsl:value-of select="tns:field/tns:unifiedField/tns:fieldName/tns:name"/>
                              </tns:fieldCode>
                              <tns:dataType>
                                <xsl:value-of select="tns:field/tns:dataType"/>
                              </tns:dataType>
                              <xsl:if test="tns:simpleValue">
                                <tns:simpleValueCriteria>
                                  <tns:value>
                                    <xsl:value-of select="tns:simpleValue/tns:unifiedValue/tns:value/tns:value"/>
                                  </tns:value>
                                </tns:simpleValueCriteria>
                              </xsl:if>
                              <xsl:if test="tns:lookupValue">
                                <tns:lookupValueCriteria>
                                  <tns:tableCode>
                                    <xsl:value-of select="tns:lookupValue/tns:record/tns:recordIdentifier/tns:table/tns:tableName/tns:name"/>
                                  </tns:tableCode>
                                  <xsl:for-each select="tns:lookupValue/tns:record/tns:dataElement">
                                    <tns:dataElementCriteria>
                                      <tns:fieldCode>
                                        <xsl:value-of select="tns:field/tns:unifiedField/tns:fieldName/tns:name"/>
                                      </tns:fieldCode>
                                      <tns:dataType>
                                        <xsl:value-of select="tns:field/tns:dataType"/>
                                      </tns:dataType>
                                      <tns:simpleValueCriteria>
                                        <tns:value>
                                          <xsl:value-of select="tns:simpleValue/tns:unifiedValue/tns:value/tns:value"/>
                                        </tns:value>
                                      </tns:simpleValueCriteria>
                                    </tns:dataElementCriteria>
                                  </xsl:for-each>
                                </tns:lookupValueCriteria>
                              </xsl:if>
                            </tns:dataElementCriteria>
                          </xsl:for-each>
                        </tns:lookupValueCriteria>
                      </xsl:if>
                    </tns:dataElementCriteria>
                  </xsl:for-each>
                </tns:lookupValueCriteria>
              </xsl:if>
            </tns:dataElementCriteria>
          </xsl:for-each>
          <tns:tableCode>
            <xsl:value-of select="/tns:CreateRecordsRequest/tns:record/tns:recordIdentifier/tns:table/tns:tableName/tns:name"/>
          </tns:tableCode>
        </tns:recordCriteria>
      </tns:query>
      <tns:repositoryInfo>
        <tns:dataLang>
          <xsl:value-of select="/tns:CreateRecordsRequest/tns:repositoryInfo/tns:dataLang"/>
        </tns:dataLang>
        <tns:repositoryName>
          <xsl:value-of select="/tns:CreateRecordsRequest/tns:repositoryInfo/tns:repositoryName"/>
        </tns:repositoryName>
        <tns:repositoryPort>
          <xsl:value-of select="/tns:CreateRecordsRequest/tns:repositoryInfo/tns:repositoryPort"/>
        </tns:repositoryPort>
        <tns:serverName>
          <xsl:value-of select="/tns:CreateRecordsRequest/tns:repositoryInfo/tns:serverName"/>
        </tns:serverName>
        <tns:schemaLang>
          <xsl:value-of select="/tns:CreateRecordsRequest/tns:repositoryInfo/tns:schemaLang"/>
        </tns:schemaLang>
      </tns:repositoryInfo>
      <tns:configData>
        <xsl:for-each select="/tns:CreateRecordsRequest/tns:configData/tns:element">
          <tns:element>
            <tns:name>
              <xsl:value-of select="tns:name"/>
            </tns:name>
            <tns:value>
              <xsl:value-of select="tns:value"/>
            </tns:value>
          </tns:element>
        </xsl:for-each>
      </tns:configData>
    </tns:SearchRecordRequest>
  </xsl:template>
</xsl:stylesheet>
