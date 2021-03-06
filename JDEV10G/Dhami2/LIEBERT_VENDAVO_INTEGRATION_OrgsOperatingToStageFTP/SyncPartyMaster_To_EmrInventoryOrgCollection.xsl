<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="Liebert_OrgsOperating_LBT_Rtr_InventoryOrgFtp.wsdl"/>
      <rootElement name="SyncPartyMaster" namespace="http://www.openapplications.org/oagis/9"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="LBT_INSERT_UPdate_InventoryOrg.wsdl"/>
      <rootElement name="EmrInventoryOrgCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/LBTINSERTUPdateInventoryOrg"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.3.0(build 070615.0525) AT [WED SEP 10 12:35:54 IST 2008]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns5="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/LBTINSERTUPdateInventoryOrg"
                xmlns:ns2="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns1="http://oracle.com/esb/namespaces/Liebert_OrgsOperating"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/db/LBT_INSERT_UPdate_InventoryOrg/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns6="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns8="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ns4="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns7="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns3="http://www.openapplications.org/oagis/9/codelists"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/db/"
                xmlns:inp1="http://www.openapplications.org/oagis/9"
                exclude-result-prefixes="xsl ns5 ns2 ns0 ns1 ns6 ns8 ns4 ns7 ns3 inp1 plt top jca tns pc hdr ehdr xp20 ora orcl bpws hwf xref ids">
  <xsl:template match="/">
    <top:EmrInventoryOrgCollection>
      <xsl:for-each select="/inp1:SyncPartyMaster/DataArea">
        <top:EmrInventoryOrg>
          <top:appId>
            <xsl:value-of select="inp1:PartyMaster/inp1:PartyIDs/inp1:TaxID"/>
          </top:appId>
          <top:organizationCode>
            <xsl:value-of select="inp1:PartyMaster/inp1:PartnerRoleCodes/inp1:Code"/>
          </top:organizationCode>
          <top:organizationName>
            <xsl:value-of select="inp1:PartyMaster/inp1:Name"/>
          </top:organizationName>
          <top:operatingUnitCode>
            <xsl:value-of select="inp1:PartyMaster/inp1:PartyIDs/inp1:ID"/>
          </top:operatingUnitCode>
          <top:organizationCostPriority>
            <xsl:value-of select="inp1:PartyMaster/inp1:FinancialParty/inp1:FinancialAccount/inp1:Type"/>
          </top:organizationCostPriority>
        </top:EmrInventoryOrg>
      </xsl:for-each>
    </top:EmrInventoryOrgCollection>
  </xsl:template>
</xsl:stylesheet>
