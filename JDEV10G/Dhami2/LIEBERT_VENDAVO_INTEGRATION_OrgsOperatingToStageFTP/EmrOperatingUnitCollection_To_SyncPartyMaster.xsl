<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="Liebert_OrgsOperating_LBT_Rtr_OperatingUnit.wsdl"/>
      <rootElement name="EmrOperatingUnitCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/LBTSELECTEmrOperatingUnit"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="Liebert_OrgsOperating_LBT_Rtr_OperatingunitFTP.wsdl"/>
      <rootElement name="SyncPartyMaster" namespace="http://www.openapplications.org/oagis/9"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.3.0(build 070615.0525) AT [TUE AUG 26 18:55:47 IST 2008]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:inp1="http://xmlns.oracle.com/pcbpel/adapter/db/top/LBTSELECTEmrOperatingUnit"
                xmlns:ns5="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns7="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:ns4="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns2="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:ns3="http://www.openapplications.org/oagis/9/codelists"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:tns="http://oracle.com/esb/namespaces/Liebert_OrgsOperating"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns1="http://www.openapplications.org/oagis/9"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns8="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                exclude-result-prefixes="xsl inp1 ns0 tns ns5 ns7 ns4 ns2 ns3 ns1 ns6 ns8 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <ns1:SyncPartyMaster>
      <xsl:for-each select="/inp1:EmrOperatingUnitCollection/inp1:EmrOperatingUnit">
        <DataArea>
          <ns1:PartyMaster>
            <ns1:PartyIDs>
              <ns1:ID>
                <xsl:value-of select="inp1:operatingUnitCode"/>
              </ns1:ID>
              <ns1:TaxID>
                <xsl:value-of select="inp1:appId"/>
              </ns1:TaxID>
            </ns1:PartyIDs>
            <ns1:ParentParty>
              <ns1:Name>
                <xsl:value-of select="inp1:operatingUnitName"/>
              </ns1:Name>
              <ns1:Location>
                <xsl:attribute name="type">
                  <xsl:value-of select="inp1:operatingTypeCode"/>
                </xsl:attribute>
                <ns1:Address>
                  <ns1:CountryCode>
                    <xsl:value-of select="inp1:worldAreaCode"/>
                  </ns1:CountryCode>
                </ns1:Address>
                <ns1:Description>
                  <xsl:value-of select="inp1:operatingUnitDescription"/>
                </ns1:Description>
              </ns1:Location>
            </ns1:ParentParty>
            <ns1:ChildParty>
              <ns1:PartyIDs>
                <ns1:DUNSID>
                  <xsl:value-of select="inp1:muFdoFlag"/>
                </ns1:DUNSID>
                <ns1:CAGEID>
                  <xsl:value-of select="inp1:warehouseFlag"/>
                </ns1:CAGEID>
                <ns1:DODAACID>
                  <xsl:value-of select="inp1:factoryFlag"/>
                </ns1:DODAACID>
              </ns1:PartyIDs>
            </ns1:ChildParty>
          </ns1:PartyMaster>
        </DataArea>
      </xsl:for-each>
    </ns1:SyncPartyMaster>
  </xsl:template>
</xsl:stylesheet>
