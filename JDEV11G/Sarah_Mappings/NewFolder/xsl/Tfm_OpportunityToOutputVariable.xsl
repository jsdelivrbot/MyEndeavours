<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/" xmlns:bpel2="http://docs.oasis-open.org/wsbpel/2.0/process/executable" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions" xmlns:xsdLocal2="urn:/crmondemand/xml/Opportunity/Query" xmlns:client="http://LBTCRMIntegration.com/LBTCRM_UC4_CRM_PWEB_OpportunityQuery/BPEL_QueryOpportunityAndAccount" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ora="http://schemas.oracle.com/xpath/extension" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:tns="urn:crmondemand/ws/ecbs/opportunity/10/2004" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath" xmlns:med="http://schemas.oracle.com/mediator/xpath" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath" xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap" xmlns:xsdLocal1="urn:/crmondemand/xml/Opportunity/Data" exclude-result-prefixes="xsi xsl xsdLocal2 soapenc soap tns xsd xsdLocal1 client plnk wsdl xp20 bpws bpel2 bpm ora socket mhdr oraext dvm hwf med ids xdk xref ldap">
   <xsl:template match="/">
      <client:queryResults>
         <client:OpportunityDetails>
            <client:OpportunityId>
               <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:Id"/>
            </client:OpportunityId>
            <client:OpportunityName>
               <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:OpportunityName"/>
            </client:OpportunityName>
            <client:CloseDate>
               <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:CloseDate"/>
            </client:CloseDate>
            <client:OpportunityType>
               <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:OpportunityType"/>
            </client:OpportunityType>
         </client:OpportunityDetails>
      </client:queryResults>
   </xsl:template>
</xsl:stylesheet>
