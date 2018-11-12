<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/" xmlns:ns0="urn:/crmondemand/xml/Account/Query" xmlns:bpel2="http://docs.oasis-open.org/wsbpel/2.0/process/executable" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions" xmlns:xsdLocal2="urn:/crmondemand/xml/Opportunity/Query" xmlns:client="http://LBTCRMIntegration.com/LBTCRM_UC4_CRM_PWEB_OpportunityQuery/BPEL_QueryOpportunityAndAccount" xmlns:ns3="http://LBTCRMIntegration.com" xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ora="http://schemas.oracle.com/xpath/extension" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:tns="urn:crmondemand/ws/ecbs/opportunity/10/2004" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath" xmlns:ns1="urn:crmondemand/ws/ecbs/account/10/2004" xmlns:med="http://schemas.oracle.com/mediator/xpath" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath" xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ns2="urn:/crmondemand/xml/Account/Data" xmlns:xsdLocal1="urn:/crmondemand/xml/Opportunity/Data" xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap" exclude-result-prefixes="xsi xsl ns0 xsdLocal2 soapenc soap tns ns1 xsd ns2 xsdLocal1 client ns3 plnk wsdl xp20 bpws bpel2 bpm ora socket mhdr oraext dvm hwf med ids xdk xref ldap">
   <xsl:param name="Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output"/>
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
               <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:dCustomer_Required_Date"/>
            </client:CloseDate>
            <client:OpportunityType>
               <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:OpportunityType"/>
            </client:OpportunityType>
            <client:ElnetFolderType>
               <xsl:value-of select="/tns:OpportunityQueryPage_Output/xsdLocal1:ListOfOpportunity/xsdLocal1:Opportunity/xsdLocal1:plELNet_Folder_Type"/>
            </client:ElnetFolderType>
         </client:OpportunityDetails>
         <client:AccountDetails>
            <client:AccountId>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:Id"/>
            </client:AccountId>
            <client:AccountName>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:AccountName"/>
            </client:AccountName>
            <client:stNext_BillTo_Cust__Calculated>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:stNext_BillTo_Cust__Calculated"/>
            </client:stNext_BillTo_Cust__Calculated>
            <client:PrimaryBillToStreetAddress>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToStreetAddress"/>
            </client:PrimaryBillToStreetAddress>
            <client:PrimaryBillToStreetAddress2>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToStreetAddress2"/>
            </client:PrimaryBillToStreetAddress2>
            <client:PrimaryBillToStreetAddress3>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToStreetAddress3"/>
            </client:PrimaryBillToStreetAddress3>
            <client:PrimaryBillToCity>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToCity"/>
            </client:PrimaryBillToCity>
            <client:PrimaryBillToProvince>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToProvince"/>
            </client:PrimaryBillToProvince>
            <client:PrimaryBillToState>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToState"/>
            </client:PrimaryBillToState>
            <client:PrimaryBillToCountry>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToCountry"/>
            </client:PrimaryBillToCountry>
            <client:PrimaryBillToPostalCode>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:PrimaryBillToPostalCode"/>
            </client:PrimaryBillToPostalCode>
            <client:MainPhone>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:MainPhone"/>
            </client:MainPhone>
            <client:MainFax>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:MainFax"/>
            </client:MainFax>
            <client:stTax_ID_Number>
               <xsl:value-of select="$Invoke_AccountQuery_OutputVariable.AccountQueryPage_Output/ns1:AccountQueryPage_Output/ns2:ListOfAccount/ns2:Account/ns2:stTax_ID_"/>
            </client:stTax_ID_Number>
         </client:AccountDetails>
      </client:queryResults>
   </xsl:template>
</xsl:stylesheet>
