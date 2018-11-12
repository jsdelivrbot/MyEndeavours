<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ETH_DEQJMS_POAck_CustomOAGIS_9_0.wsdl"/>
      <rootElement name="CustomAcknowledgePO" namespace="http://xmlns.emrsn.com/CustomAcknowledgePurchaseOrder"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ETHSys_OutputServices_ETHSysGrp_POAck_FlatFile_Output_ETH_Transform_CustomFlatFile_RS.wsdl"/>
      <rootElement name="CustomLiebertPOAck" namespace="http://xmlns.emrsn.com/CustomPOAckFlatFile"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [WED JUL 22 23:35:28 IST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:ns7="http://www.openapplications.org/oagis/9/unqualifieddatatypes/1.1"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns5="http://xmlns.emrsn.com/CustomPOAckFlatFile"
                xmlns:ns3="http://www.openapplications.org/oagis/9/IANAMIMEMediaTypes:2003"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:inp1="http://xmlns.emrsn.com/CustomAcknowledgePurchaseOrder"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns2="http://oracle.com/esb/namespaces/ETHSys_IntegrationServices_ETHSysGrp_AcknowledgePurchaseOrder"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/ETH_PUTFL_CustomPOAck_LiebertFlatFile/"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/file/"
                xmlns:ns8="http://www.openapplications.org/oagis/9/unitcode/66411:2001"
                xmlns:ns10="http://www.openapplications.org/oagis/9/languagecode/5639:1988"
                xmlns:ns9="http://xmlns.emrsn.com/POAckFlatFile"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns6="http://www.openapplications.org/oagis/9/qualifieddatatypes/1.1"
                xmlns:ns1="http://www.openapplications.org/oagis/9/currencycode/54217:2001"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns4="http://www.openapplications.org/oagis/9/codelists"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:imp1="http://www.openapplications.org/oagis/9"
                exclude-result-prefixes="xsl ns7 ns3 ns0 inp1 ns2 ns8 ns10 ns6 ns9 ns4 ns5 plt imp1 jca tns hdr ns1 ehdr xp20 ora orcl bpws hwf xref ids">
  <xsl:template match="/">
    <ns5:CustomLiebertPOAck>
      <!--  <ns1:Root-Element>
        <record>
          <HDGS02>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/ns5:PurchaseOrder/ns5:PurchaseOrderHeader/ns5:DocumentID/ns5:ID"/>
          </HDGS02>
        </record>
      </ns1:Root-Element> -->
      <ns9:Root-Element>
        <xsl:for-each select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderLine">
        <ns9:record>
         <ns9:HDGS02>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/imp1:ApplicationArea/imp1:Sender/imp1:LogicalID"/>
          </ns9:HDGS02>
          <ns9:HDGS03>
                      <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:Acknowledge/imp1:OriginalApplicationArea/imp1:Sender/imp1:LogicalID"/>
          </ns9:HDGS03>
          <ns9:HDGS04>
            <xsl:value-of select="concat(substring(/inp1:CustomAcknowledgePO/AcknowledgePO/imp1:ApplicationArea/imp1:CreationDateTime,1.0,4.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/imp1:ApplicationArea/imp1:CreationDateTime,6.0,2.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/imp1:ApplicationArea/imp1:CreationDateTime,9.0,2.0))"/>
          </ns9:HDGS04>
          <ns9:HDGS05>
            <xsl:value-of select="concat(substring(/inp1:CustomAcknowledgePO/AcknowledgePO/imp1:ApplicationArea/imp1:CreationDateTime,12.0,2.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/imp1:ApplicationArea/imp1:CreationDateTime,15.0,2.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/imp1:ApplicationArea/imp1:CreationDateTime,18.0,2.0))"/>
          </ns9:HDGS05>
          <ns9:HDBAK01>
            <xsl:value-of select='string("00")'/>
          </ns9:HDBAK01>
          <xsl:for-each select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:Status/imp1:Code">
            <xsl:if test="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:Status/imp1:Code = 'Accepted'">
              <ns9:HDBAK02>
                <xsl:value-of select='string("AT")'/>
              </ns9:HDBAK02>
            </xsl:if>
          </xsl:for-each>
          <xsl:for-each select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:Status/imp1:Code">
            <xsl:if test="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:Status/imp1:Code = 'Rejected'">
              <ns9:HDBAK02>
                <xsl:value-of select='string("RD")'/>
              </ns9:HDBAK02>
            </xsl:if>
          </xsl:for-each>
          <xsl:for-each select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:Status/imp1:Code">
            <xsl:if test="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:Status/imp1:Code = 'PartialAccept'">
              <ns9:HDBAK02>
                <xsl:value-of select='string("AC")'/>
              </ns9:HDBAK02>
            </xsl:if>
          </xsl:for-each>
          <ns9:HDBAK03>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:DocumentID/imp1:ID"/>
          </ns9:HDBAK03>
          <ns9:HDBAK04>
            <xsl:value-of select="concat(substring(/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:OrderDateTime,1.0,4.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:OrderDateTime,6.0,2.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:OrderDateTime,9.0,2.0))"/>
          </ns9:HDBAK04>
          <ns9:HDBAK05>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:ReleaseNumber"/>
          </ns9:HDBAK05>
          <ns9:HDBAK08>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:DocumentReference/imp1:DocumentID/imp1:ID"/>
          </ns9:HDBAK08>
          <ns9:HDBAK09>
            <xsl:value-of select="concat(substring(/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:DocumentDateTime,1.0,4.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:DocumentDateTime,6.0,2.0),substring(/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:DocumentDateTime,9.0,2.0))"/>
          </ns9:HDBAK09>
          <ns9:HD1REF03>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:DocumentID/imp1:RevisionID"/>
          </ns9:HD1REF03>
          <ns9:HD4REF02>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:Note"/>
          </ns9:HD4REF02>
          <ns9:HD5REF03>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:UserArea/imp1:SystemID"/>
          </ns9:HD5REF03>
          <ns9:HD1N102>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Name"/>
          </ns9:HD1N102>
          <ns9:HD1N104>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:PartyIDs/imp1:ID"/>
          </ns9:HD1N104>
          <ns9:HD1N301>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:LineOne"/>
          </ns9:HD1N301>
          <ns9:HD1N302>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:LineTwo"/>
          </ns9:HD1N302>
          <ns9:HD1N303>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:LineThree"/>
          </ns9:HD1N303>
          <ns9:HD1N304>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:LineFour"/>
          </ns9:HD1N304>
          <ns9:HD1N401>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:CityName"/>
          </ns9:HD1N401>
          <ns9:HD1N402>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:CountrySubDivisionCode"/>
          </ns9:HD1N402>
          <ns9:HD1N403>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:PostalCode"/>
          </ns9:HD1N403>
          <ns9:HD1N404>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:Address/imp1:CountryCode"/>
          </ns9:HD1N404>
          <ns9:HD1N406>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:Location/imp1:ID"/>
          </ns9:HD1N406>
          <ns9:HD1PER02>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:CustomerServiceContact/imp1:Name"/>
          </ns9:HD1PER02>
          <ns9:HD1PER04>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:CustomerServiceContact/imp1:Communication[3]/imp1:DialNumber"/>
          </ns9:HD1PER04>
          <ns9:HD1PER06>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:CustomerServiceContact/imp1:Communication[2]/imp1:DialNumber"/>
          </ns9:HD1PER06>
          <ns9:HD1PER08>
            <xsl:value-of select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderHeader/imp1:SupplierParty/imp1:CustomerServiceContact/imp1:Communication[1]/imp1:URI"/>
          </ns9:HD1PER08>
          
          <!--<xsl:for-each select="/inp1:CustomAcknowledgePO/AcknowledgePO/DataArea/imp1:PurchaseOrder/imp1:PurchaseOrderLine">-->
          <ns9:LN1PO101>
            <xsl:value-of select="imp1:LineNumber"/>
          </ns9:LN1PO101>
          <ns9:LN1PO102>
            <xsl:value-of select="imp1:Quantity"/>
          </ns9:LN1PO102>
          <ns9:LN1PO103>
            <xsl:value-of select="imp1:Quantity/@unitCode"/>
          </ns9:LN1PO103>
          <ns9:LN1PO104>
            <xsl:value-of select="imp1:UnitPrice/imp1:Amount"/>
          </ns9:LN1PO104>
          <ns9:LN1PO107>
            <xsl:value-of select="imp1:Item/imp1:ItemID/imp1:ID"/>
          </ns9:LN1PO107>
          <ns9:LN1PO109>
            <xsl:value-of select="imp1:Item/imp1:SupplierItemID/imp1:ID"/>
          </ns9:LN1PO109>
          <ns9:LN1PO111>
            <xsl:value-of select="imp1:Item/imp1:ItemID/imp1:RevisionID"/>
          </ns9:LN1PO111>
          <ns9:LN1CUR02>
            <xsl:value-of select="imp1:UnitPrice/imp1:Amount/@currencyID"/>
          </ns9:LN1CUR02>
          <ns9:LN1PID05>
            <xsl:value-of select="imp1:Item/imp1:Description"/>
          </ns9:LN1PID05>
          <ns9:LN1ACK01>
            <xsl:value-of select="imp1:Status/imp1:Code"/>
          </ns9:LN1ACK01>
          <xsl:for-each select="imp1:PurchaseOrderSchedule">
          <ns9:LN1N102>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Name"/>
          </ns9:LN1N102>
          <ns9:LN1N104>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:PartyIDs/imp1:ID"/>
          </ns9:LN1N104>
          <ns9:LN1N301>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:LineOne"/>
          </ns9:LN1N301>
          <ns9:LN1N302>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:LineTwo"/>
          </ns9:LN1N302>
          <ns9:LN1N303>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:LineThree"/>
          </ns9:LN1N303>
          <ns9:LN1N304>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:LineFour"/>
          </ns9:LN1N304>
          <ns9:LN1N401>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:CityName"/>
          </ns9:LN1N401>
          <ns9:LN1N402>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:CountrySubDivisionCode"/>
          </ns9:LN1N402>
          <ns9:LN1N403>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:PostalCode"/>
          </ns9:LN1N403>
          <ns9:LN1N404>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:Address/imp1:CountryCode"/>
          </ns9:LN1N404>
          <ns9:LN1N406>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Location/imp1:ID"/>
          </ns9:LN1N406>
          <ns9:LN1PER02>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Contact/imp1:Name"/>
          </ns9:LN1PER02>
          <ns9:LN1PER04>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Contact/imp1:Communication[3]/imp1:DialNumber"/>
          </ns9:LN1PER04>
          <ns9:LN1PER06>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Contact/imp1:Communication[2]/imp1:DialNumber"/>
          </ns9:LN1PER06>
          <ns9:LN1PER08>
            <xsl:value-of select="imp1:UserArea/imp1:ShipToParty/imp1:Contact/imp1:Communication[1]/imp1:URI"/>
          </ns9:LN1PER08>
          <!--commented besed on the new POACK mappings documents<ns9:LN1DTM02>
          <xsl:value-of select="concat(substring(imp1:UserArea/imp1:PromisedShipDateTime,1.0,4.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,6.0,2.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,9.0,2.0))"/>
        </ns9:LN1DTM02>
        <ns9:LN1DTM03>
          <xsl:value-of select="concat(substring(imp1:UserArea/imp1:PromisedShipDateTime,12.0,2.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,15.0,2.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,18.0,2.0))"/>
        </ns9:LN1DTM03>-->
          <!--  <ns9:LN1DTM04>
            <xsl:value-of select="concat(substring(imp1:UserArea/imp1:PromisedShipDateTime,20.0,3.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,24.0,2.0))"/>
         <xsl:value-of select="substring(imp1:UserArea/imp1:PromisedShipDateTime,20.0,6.0)"/>
        </ns9:LN1DTM04> -->
          <ns9:LN1SCH01>
            <xsl:value-of select="imp1:Quantity"/>
          </ns9:LN1SCH01>
          <ns9:LN1SCH02>
            <xsl:value-of select="imp1:Quantity/@unitCode"/>
          </ns9:LN1SCH02>
          <ns9:LN1SCH06>
            <xsl:value-of select="concat(substring(imp1:RequiredDeliveryDateTime,1.0,4.0),substring(imp1:RequiredDeliveryDateTime,6.0,2.0),substring(imp1:RequiredDeliveryDateTime,9.0,2.0))"/>
          </ns9:LN1SCH06>
          <ns9:LN1SCH07>
            <xsl:value-of select="concat(substring(imp1:RequiredDeliveryDateTime,12.0,2.0),substring(imp1:RequiredDeliveryDateTime,15.0,2.0),substring(imp1:RequiredDeliveryDateTime,18.0,2.0))"/>
          </ns9:LN1SCH07>
          <ns9:LN1SCH09>
            <xsl:value-of select="concat(substring(imp1:UserArea/imp1:PromisedShipDateTime,1.0,4.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,6.0,2.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,9.0,2.0))"/>
          </ns9:LN1SCH09>
          <ns9:LN1SCH10>
            <xsl:value-of select="concat(substring(imp1:UserArea/imp1:PromisedShipDateTime,12.0,2.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,15.0,2.0),substring(imp1:UserArea/imp1:PromisedShipDateTime,18.0,2.0))"/>
          </ns9:LN1SCH10>
          <ns9:LN1SCH12>
            <xsl:value-of select="imp1:LineNumber"/>
          </ns9:LN1SCH12>
          <ns9:LN2N102>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Name"/>
          </ns9:LN2N102>
          <ns9:LN2N104>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:PartyIDs/imp1:ID"/>
          </ns9:LN2N104>
          <ns9:LN2N301>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:LineOne"/>
          </ns9:LN2N301>
          <ns9:LN2N302>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:LineTwo"/>
          </ns9:LN2N302>
          <ns9:LN2N303>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:LineThree"/>
          </ns9:LN2N303>
          <ns9:LN2N304>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:LineFour"/>
          </ns9:LN2N304>
          <ns9:LN2N401>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:CityName"/>
          </ns9:LN2N401>
          <ns9:LN2N402>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:CountrySubDivisionCode"/>
          </ns9:LN2N402>
          <ns9:LN2N403>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:PostalCode"/>
          </ns9:LN2N403>
          <ns9:LN2N404>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:Address/imp1:CountryCode"/>
          </ns9:LN2N404>
          <ns9:LN2N406>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Location/imp1:ID"/>
          </ns9:LN2N406>
          <ns9:LN2PER02>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Contact/imp1:Name"/>
          </ns9:LN2PER02>
          <ns9:LN2PER04>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Contact/imp1:Communication[3]/imp1:DialNumber"/>
          </ns9:LN2PER04>
          <ns9:LN2PER06>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Contact/imp1:Communication[2]/imp1:DialNumber"/>
          </ns9:LN2PER06>
          <ns9:LN2PER08>
            <xsl:value-of select="imp1:UserArea/imp1:ShipFromParty/imp1:Contact/imp1:Communication[1]/imp1:URI"/>
          </ns9:LN2PER08>
          </xsl:for-each>
        </ns9:record>
        </xsl:for-each>
      </ns9:Root-Element>
      <Message_id>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Message_id"/>
      </Message_id>
      <Message_direction>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Message_direction"/>
      </Message_direction>
      <Sender_Document_number>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Document_number"/>
      </Sender_Document_number>
      <Sender_Message_type>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Message_type"/>
      </Sender_Message_type>
      <Sender_Message_standard>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Message_standard"/>
      </Sender_Message_standard>
      <Sender_Transaction_type>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Transaction_type"/>
      </Sender_Transaction_type>
      <Sender_Transaction_subtype>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Transaction_subtype"/>
      </Sender_Transaction_subtype>
      <Sender_System_type>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_System_type"/>
      </Sender_System_type>
      <Sender_Id>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Id"/>
      </Sender_Id>
      <Sender_Site_Id>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Site_Id"/>
      </Sender_Site_Id>
      <Sender_Name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Name"/>
      </Sender_Name>
      <Sender_Type>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Sender_Type"/>
      </Sender_Type>
      <Receiver_System_type>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Receiver_System_type"/>
      </Receiver_System_type>
      <Receiver_Id>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Receiver_Id"/>
      </Receiver_Id>
      <Receiver_Site_id>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Receiver_Site_id"/>
      </Receiver_Site_id>
      <Receiver_Name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_1_name"/>
      </Receiver_Name>
      <Receiver_type>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Receiver_type"/>
      </Receiver_type>
      <File_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/File_name"/>
      </File_name>
      <Record_total>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Record_total"/>
      </Record_total>
      <Record_number>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Record_number"/>
      </Record_number>
      <Flow_id_1>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Flow_id_1"/>
      </Flow_id_1>
      <Flow_id_2>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Flow_id_2"/>
      </Flow_id_2>
      <Flow_id_3>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Flow_id_3"/>
      </Flow_id_3>
      <Send_on_fail>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Send_on_fail"/>
      </Send_on_fail>
      <Division_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Division_name"/>
      </Division_name>
      <Division_format>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Division_format"/>
      </Division_format>
      <Trading_partner_1_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_1_name"/>
      </Trading_partner_1_name>
      <Trading_partner_1_format>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_1_format"/>
      </Trading_partner_1_format>
      <Trading_partner_1_date_from>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_1_date_from"/>
      </Trading_partner_1_date_from>
      <Trading_partner_1_date_to>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_1_date_to"/>
      </Trading_partner_1_date_to>
      <Trading_partner_2_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_2_name"/>
      </Trading_partner_2_name>
      <Trading_partner_2_format>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_2_format"/>
      </Trading_partner_2_format>
      <Trading_partner_2_date_from>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_2_date_from"/>
      </Trading_partner_2_date_from>
      <Trading_partner_2_date_to>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_2_date_to"/>
      </Trading_partner_2_date_to>
      <Trading_partner_3_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_3_name"/>
      </Trading_partner_3_name>
      <Trading_partner_3_format>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_3_format"/>
      </Trading_partner_3_format>
      <Trading_partner_3_date_from>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_3_date_from"/>
      </Trading_partner_3_date_from>
      <Trading_partner_3_date_to>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Trading_partner_3_date_to"/>
      </Trading_partner_3_date_to>
      <Logistics_1_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_1_name"/>
      </Logistics_1_name>
      <Logistics_1_format>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_1_format"/>
      </Logistics_1_format>
      <Logistics_1_date_from>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_1_date_from"/>
      </Logistics_1_date_from>
      <Logistics_1_date_to>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_1_date_to"/>
      </Logistics_1_date_to>
      <Logistics_2_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_2_name"/>
      </Logistics_2_name>
      <Logistics_2_format>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_2_format"/>
      </Logistics_2_format>
      <Logistics_2_date_from>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_2_date_from"/>
      </Logistics_2_date_from>
      <Logistics_2_date_to>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_2_date_to"/>
      </Logistics_2_date_to>
      <Logistics_3_name>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_3_name"/>
      </Logistics_3_name>
      <Logistics_3_format>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_3_format"/>
      </Logistics_3_format>
      <Logistics_3_date_from>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_3_date_from"/>
      </Logistics_3_date_from>
      <Logistics_3_date_to>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Logistics_3_date_to"/>
      </Logistics_3_date_to>
      <Compliance_result>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Compliance_result"/>
      </Compliance_result>
      <Attribute1>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute1"/>
      </Attribute1>
      <Attribute2>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute2"/>
      </Attribute2>
      <Attribute3>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute3"/>
      </Attribute3>
      <Attribute4>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute4"/>
      </Attribute4>
      <Attribute5>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute5"/>
      </Attribute5>
      <Attribute6>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute6"/>
      </Attribute6>
      <Attribute7>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute7"/>
      </Attribute7>
      <Attribute8>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute8"/>
      </Attribute8>
      <Attribute9>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute9"/>
      </Attribute9>
      <Attribute10>
        <xsl:value-of select="/inp1:CustomAcknowledgePO/Attribute10"/>
      </Attribute10>
    </ns5:CustomLiebertPOAck>
  </xsl:template>
</xsl:stylesheet>
