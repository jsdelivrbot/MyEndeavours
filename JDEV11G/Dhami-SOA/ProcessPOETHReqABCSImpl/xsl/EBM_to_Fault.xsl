<?xml version = '1.0' encoding = 'UTF-8'?>
<?oracle-xsl-mapper ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions" xmlns:aia="http://www.oracle.com/XSL/Transform/java/oracle.apps.aia.core.xpath.AIAFunctions" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:ora="http://schemas.oracle.com/xpath/extension" xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/" xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath" xmlns:corecom="http://xmlns.oracle.com/EnterpriseObjects/Core/Common/V2" version="1.0" exclude-result-prefixes="xsl plnk xml corecom ehdr aia xp20 ora orcl bpws hwf xref ids" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue">

  <xsl:template match="/">
    <corecom:Fault>
        <xsl:apply-templates select="*/corecom:EBMHeader"/>
        <corecom:FaultNotification>
            <corecom:ReportingDateTime>
                <xsl:value-of select="xp20:current-dateTime()"/>
            </corecom:ReportingDateTime>
            <corecom:CorrectiveAction/>
            <corecom:FaultMessage>
                <corecom:Code/>
                <corecom:Text/>
                <corecom:Severity/>
                <corecom:Stack/>
            </corecom:FaultMessage>
            <corecom:FaultingService>
                <corecom:ID/>
                <corecom:ImplementationCode/>
                <corecom:InstanceID/>
                <!--<corecom:ECID/>-->
                <corecom:ExecutionContextID/>
            </corecom:FaultingService>
        </corecom:FaultNotification>
    </corecom:Fault>
  </xsl:template>

  <xsl:template match="corecom:EBMHeader">
    <corecom:EBMReference>
        <xsl:apply-templates select="corecom:EBMID"/>
        <xsl:apply-templates select="corecom:EBMName"/>
        <xsl:apply-templates select="corecom:EBOName"/>
        <xsl:apply-templates select="corecom:VerbCode"/>
        <xsl:apply-templates select="corecom:BusinessScope[corecom:BusinessScopeTypeCode='BusinessProcess']"/>
        <xsl:apply-templates select="corecom:Sender"/>
    </corecom:EBMReference>
  </xsl:template>

  <xsl:template match="corecom:BusinessScope">
    <corecom:BusinessScopeReference>
        <xsl:apply-templates select="corecom:ID"/>
        <xsl:apply-templates select="corecom:InstanceID"/>
        <xsl:apply-templates select="corecom:EnterpriseServiceName"/>
        <xsl:apply-templates select="corecom:EnterpriseServiceOperationName"/>
    </corecom:BusinessScopeReference>
  </xsl:template>
  
  <xsl:template match="corecom:Sender">
    <corecom:SenderReference>
        <xsl:apply-templates select="corecom:ID"/>
        <xsl:apply-templates select="corecom:SenderMessageID"/>
        <xsl:apply-templates select="corecom:TransactionCode"/>
        <xsl:apply-templates select="corecom:ObjectCrossReference"/>
        <xsl:apply-templates select="corecom:Application"/>
    </corecom:SenderReference>  
  </xsl:template>

  <xsl:template match="*">
    <xsl:copy-of select="."/>
  </xsl:template>

</xsl:stylesheet>
