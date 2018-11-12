<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="LBT_POLL_ITEM_COST_OUTBOUND.wsdl"/>
      <rootElement name="ItemCostOutboundCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/LBTPOLLITEMCOSTOUTBOUND"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="LBT_StagingDBToVendavoFTP_LBT_RS_ITEM_NUM_COST_FTP.wsdl"/>
      <rootElement name="SyncItemMaster" namespace="http://www.openapplications.org/oagis/9"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU JUL 09 14:40:49 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns5="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/db/LBT_POLL_ITEM_COST_OUTBOUND/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns2="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns9="http://oracle.com/esb/namespaces/LBT_StagingDBToVendavoFTP"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns8="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/LBTPOLLITEMCOSTOUTBOUND"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ns4="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns7="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns3="http://www.openapplications.org/oagis/9/codelists"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:ns1="http://oracle.com/esb/namespaces/Item_number_And_Cost"
                xmlns:inp1="http://www.openapplications.org/oagis/9"
                exclude-result-prefixes="xsl tns plt ns0 jca top pc ns5 ns2 ns9 ns6 ns8 ns4 ns7 ns3 inp1 ehdr xp20 ora orcl bpws hwf xref ids">
  <xsl:template match="/">
    <inp1:SyncItemMaster>
      <xsl:for-each select="/top:ItemCostOutboundCollection/top:ItemCostOutbound">
        <DataArea>
          <inp1:ItemMaster>
            <inp1:ItemMasterHeader>
              <inp1:Lot>
                <inp1:LotIDs>
                  <inp1:ID>
                    <xsl:value-of select="top:itemNbr"/>
                  </inp1:ID>
                </inp1:LotIDs>
              </inp1:Lot>
              <inp1:EffectiveTimePeriod>
                <inp1:StartDateTime>
                  <xsl:value-of select="top:creationDate"/>
                </inp1:StartDateTime>
              </inp1:EffectiveTimePeriod>
              <inp1:ShippingUOMCode>
                <xsl:attribute name="listAgencyID">
                  <xsl:value-of select="top:operatingUnitCode"/>
                </xsl:attribute>
              </inp1:ShippingUOMCode>
              <inp1:ItemValue>
                <inp1:UnitValue>
                  <inp1:Amount>
                    <xsl:attribute name="currencyID">
                      <xsl:value-of select="top:currencyCode"/>
                    </xsl:attribute>
                    <xsl:value-of select="top:materialCostElement"/>
                  </inp1:Amount>
                  <inp1:PerQuantity>
                    <xsl:value-of select="top:unitCost"/>
                  </inp1:PerQuantity>
                </inp1:UnitValue>
              </inp1:ItemValue>
              <inp1:BOMReference>
                <inp1:EffectiveTimePeriod>
                  <inp1:StartDateTime>
                    <xsl:value-of select="top:effectiveStartDate"/>
                  </inp1:StartDateTime>
                  <inp1:EndDateTime>
                    <xsl:value-of select="top:effectiveEndDate"/>
                  </inp1:EndDateTime>
                </inp1:EffectiveTimePeriod>
                <inp1:Item>
                  <inp1:Classification>
                    <inp1:Codes>
                      <inp1:Code>
                        <xsl:attribute name="sequence">
                          <xsl:value-of select="top:costType"/>
                        </xsl:attribute>
                      </inp1:Code>
                    </inp1:Codes>
                  </inp1:Classification>
                </inp1:Item>
              </inp1:BOMReference>
              <inp1:SerialControlIndicator>
                <xsl:value-of select="top:processFlag"/>
              </inp1:SerialControlIndicator>
            </inp1:ItemMasterHeader>
          </inp1:ItemMaster>
        </DataArea>
      </xsl:for-each>
    </inp1:SyncItemMaster>
  </xsl:template>
</xsl:stylesheet>