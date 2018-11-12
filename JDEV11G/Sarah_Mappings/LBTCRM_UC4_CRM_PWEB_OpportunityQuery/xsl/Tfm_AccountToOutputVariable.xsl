<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/" xmlns:xsdLocal2="urn:/crmondemand/xml/Account/Query" xmlns:bpel2="http://docs.oasis-open.org/wsbpel/2.0/process/executable" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions" xmlns:client="http://LBTCRMIntegration.com/LBTCRM_UC4_CRM_PWEB_OpportunityQuery/BPEL_QueryOpportunityAndAccount" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ora="http://schemas.oracle.com/xpath/extension" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath" xmlns:med="http://schemas.oracle.com/mediator/xpath" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="urn:crmondemand/ws/ecbs/account/10/2004" xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath" xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsdLocal1="urn:/crmondemand/xml/Account/Data" xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap" exclude-result-prefixes="xsi xsl xsdLocal2 soapenc soap tns xsd xsdLocal1 client plnk wsdl xp20 bpws bpel2 bpm ora socket mhdr oraext dvm hwf med ids xdk xref ldap">
   <xsl:template match="/">
      <client:queryResults>
         <client:AccountDetails>
            <client:AccountId>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:Id"/>
            </client:AccountId>
            <client:AccountName>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:AccountName"/>
            </client:AccountName>
            <client:stNext_BillTo_Cust__Calculated>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:stNext_BillTo_Cust__Calculated"/>
            </client:stNext_BillTo_Cust__Calculated>
            <client:PrimaryBillToStreetAddress>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:PrimaryBillToStreetAddress"/>
            </client:PrimaryBillToStreetAddress>
            <client:PrimaryBillToStreetAddress2>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:PrimaryBillToStreetAddress2"/>
            </client:PrimaryBillToStreetAddress2>
            <client:PrimaryBillToStreetAddress3>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:PrimaryBillToStreetAddress3"/>
            </client:PrimaryBillToStreetAddress3>
            <client:PrimaryBillToCity>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:PrimaryBillToCity"/>
            </client:PrimaryBillToCity>
            <client:PrimaryBillToProvince>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:PrimaryBillToProvince"/>
            </client:PrimaryBillToProvince>
            <client:PrimaryBillToCountry>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:PrimaryBillToCountry"/>
            </client:PrimaryBillToCountry>
            <client:PrimaryBillToPostalCode>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:PrimaryBillToPostalCode"/>
            </client:PrimaryBillToPostalCode>
            <client:MainPhone>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:MainPhone"/>
            </client:MainPhone>
            <client:MainFax>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:MainFax"/>
            </client:MainFax>
            <client:stTax_ID_Number>
               <xsl:value-of select="/tns:AccountQueryPage_Output/xsdLocal1:ListOfAccount/xsdLocal1:Account/xsdLocal1:stTax_ID_Number"/>
            </client:stTax_ID_Number>
         </client:AccountDetails>
      </client:queryResults>
   </xsl:template>
</xsl:stylesheet>
