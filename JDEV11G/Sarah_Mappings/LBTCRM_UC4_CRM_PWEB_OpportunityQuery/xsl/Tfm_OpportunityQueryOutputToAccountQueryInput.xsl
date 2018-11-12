<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/" xmlns:ns0="urn:/crmondemand/xml/Account/Query" xmlns:bpel2="http://docs.oasis-open.org/wsbpel/2.0/process/executable" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions" xmlns:xsdLocal2="urn:/crmondemand/xml/Opportunity/Query" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ora="http://schemas.oracle.com/xpath/extension" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:tns="urn:crmondemand/ws/ecbs/opportunity/10/2004" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath" xmlns:med="http://schemas.oracle.com/mediator/xpath" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns1="urn:crmondemand/ws/ecbs/account/10/2004" xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath" xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ns2="urn:/crmondemand/xml/Account/Data" xmlns:xsdLocal1="urn:/crmondemand/xml/Opportunity/Data" xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap" exclude-result-prefixes="xsi xsl xsdLocal2 soapenc soap tns xsd xsdLocal1 ns0 ns1 ns2 xp20 bpws bpel2 bpm ora socket mhdr oraext dvm hwf med ids xdk xref ldap">
   <xsl:template match="/">
      <ns1:AccountQueryPage_Input>
         <ns0:ListOfAccount>
            <xsl:attribute name="pagesize">
               <xsl:text disable-output-escaping="no">1</xsl:text>
            </xsl:attribute>
            <xsl:attribute name="startrownum">
               <xsl:text disable-output-escaping="no">0</xsl:text>
            </xsl:attribute>
            <xsl:attribute name="recordcountneeded">
               <xsl:text disable-output-escaping="no">false</xsl:text>
            </xsl:attribute>
            <ns0:Account>
               <xsl:attribute name="searchspec">
                  <xsl:text disable-output-escaping="no">[CreatedDate] IS NOT NULL</xsl:text>
               </xsl:attribute>
               <ns0:Id>
                  <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:AccountId"/>
               </ns0:Id>
               <ns0:AccountName>
                  <xsl:text disable-output-escaping="no">''</xsl:text>
               </ns0:AccountName>
            </ns0:Account>
         </ns0:ListOfAccount>
         <ns1:LOVLanguageMode>
            <xsl:text disable-output-escaping="no">LDC</xsl:text>
         </ns1:LOVLanguageMode>
         <ns1:IncludeSubBooks>
            <xsl:text disable-output-escaping="no">true</xsl:text>
         </ns1:IncludeSubBooks>
         <ns1:ViewMode>
            <xsl:text disable-output-escaping="no">Broadest</xsl:text>
         </ns1:ViewMode>
      </ns1:AccountQueryPage_Input>
   </xsl:template>
</xsl:stylesheet>
