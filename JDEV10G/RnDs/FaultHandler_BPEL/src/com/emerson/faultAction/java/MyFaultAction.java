package com.emerson.faultAction.java;

import com.oracle.bpel.client.BPELFault;
import com.oracle.bpel.client.BPELProcessId;
import com.oracle.bpel.client.Locator;
import com.oracle.bpel.client.NormalizedMessage;
import com.oracle.bpel.client.config.faultpolicy.IFaultRecoveryContext;
import com.oracle.bpel.client.config.faultpolicy.IFaultRecoveryJavaClass;
import com.oracle.bpel.client.delivery.IDeliveryService;

public class MyFaultAction implements IFaultRecoveryJavaClass {
    public MyFaultAction() {
        System.out.println("[MyFaultAction] Instantiated...");
    }


    public void handleRetrySuccess(IFaultRecoveryContext iFaultRecoveryContext) {
        System.out.println("[MyFaultAction]handleRetrySuccess - Called");
    }

    public String handleBPELFault(IFaultRecoveryContext iFaultRecoveryContext) {
        BPELFault bpelFault = null;
        BPELProcessId bpelProcessId = null;
        System.out.println("[MyFaultAction]handleBPELFault - Called");
        String processName = "", faultName = "", faultMessage = "";
        try{
        
            Object bpelInput = iFaultRecoveryContext.getVariableData("G_BPEL_INPUT");
            Object bpelCurrentInvoke = iFaultRecoveryContext.getVariableData("G_CURRENT_INVOKE");
            //BPELFaultRecoveryContextImpl bpelCtx = (BPELFaultRecoveryContextImpl) iFaultRecoveryContext;

            System.out.println("G_BPEL_INPUT - "+bpelInput);
            System.out.println("G_CURRENT_INVOKE - "+bpelCurrentInvoke);
            System.out.println("Action ID - "+iFaultRecoveryContext.getActionId());                     // gives the id of action called. e.g. ora-java
            System.out.println("Activity ID - "+iFaultRecoveryContext.getActivityId());                 // gives the activity id - BpInv0
            System.out.println("Activity Name - "+iFaultRecoveryContext.getActivityName());             // gives the name of activity from .bpel file - Invoke_1
            System.out.println("Activity Type - "+iFaultRecoveryContext.getActivityType());             // gives activity type - invoke
            System.out.println("Correlation ID - "+iFaultRecoveryContext.getCorrelationId());           // gives CorrelationId. e.g. bpel://localhost/default/FaultHandler_BPEL~1.0/120001-BpInv0-BpSeq0.3-2
            System.out.println("BPEL Instance ID - "+iFaultRecoveryContext.getInstanceId());            // gives the Instance Id of the BPEL - 120001
            System.out.println("Partner Link Name - "+iFaultRecoveryContext.getPartnerLinkName());      // gives the name of the Partner Link which generated error - FaultGenerator_BPEL
            System.out.println("Policy ID - "+iFaultRecoveryContext.getPolicyId());                     // should give ID of policy which got executed... but gave NULL
            System.out.println("Priority - "+iFaultRecoveryContext.getPriority());                      // default 0
            System.out.println("Status - "+iFaultRecoveryContext.getStatus());                          // status of the transaction. e.g. initiated
            System.out.println("Title - "+iFaultRecoveryContext.getTitle());                            // Title of the BPEL Instance - Instance #120001 of FaultHandler_BPEL
            System.out.println("WSDL Location - "+iFaultRecoveryContext.getWsdlLocation());             // WSDL location of the BPEL - http://INEDEC-MLAP-45.emrcorp.local:80/orabpel/default/FaultGenerator_BPEL/FaultGenerator_BPEL?wsdl
            bpelFault = iFaultRecoveryContext.getFault();                                               
            if(bpelFault!=null){
                System.out.println("BPEL Fault Message - "+bpelFault.getMessage());                     // BPEL Fault Message. Like - faultName: {{http://schemas.xmlsoap.org/ws/2003/03/business-process/}selectionFailure}
                                                                                                        //                              messageType: {}
                                                                                                        //                              parts: {{summary=<summary>empty variable/expression result.
                                                                                                        //                              xpath variable/expression expression "ora:processXSLT('Transformation_1.xsl',bpws:getVariableData('inputVariable','payload'))" is empty at line 76, when attempting reading/copying it.
                                                                                                        //                              Please make sure the variable/expression result "ora:processXSLT('Transformation_1.xsl',bpws:getVariableData('inputVariable','payload'))" is not empty. 
                                                                                                        //                              Possible reasons behind this problems are: some xml elements/attributes are optional or the xml data is invalid according to XML Schema. 
                                                                                                        //                              To verify whether XML data received by a process is valid, user can turn on validateXML switch at the domain administration page. 
                                                                                                        //                              </summary>}}

                System.out.println("BPEL Fault Localized Message - "+bpelFault.getLocalizedMessage());  // Same as BPEL Fault Message.
                System.out.println("BPEL Message Parts - "+bpelFault.getParts());                       // {summary=oracle.xml.parser.v2.XMLElement@1430578}
                System.out.println("BPEL Fault Name - "+bpelFault.getFaultName());                      // {http://schemas.xmlsoap.org/ws/2003/03/business-process/}selectionFailure
                System.out.println("BPEL Fault Stacktrace - "+bpelFault.getStackTrace());               // Stacktrace of the Exception
                
                faultMessage = bpelFault.getMessage();
                faultName = bpelFault.getFaultName()+"";
            }
            else{
                System.out.println("BPEl Fault is NULL");
            }
            System.out.println("Port Type - "+iFaultRecoveryContext.getPortType());
            bpelProcessId = iFaultRecoveryContext.getProcessId();
            if(bpelProcessId !=null){
                System.out.println("BPEL Domain ID - "+bpelProcessId.getDomainId());                    // domain name - default
                System.out.println("BPEL Instance ID - "+bpelProcessId.getInstanceId());                // got NULL
                System.out.println("BPEL Key - "+bpelProcessId.getKey());                               // got NULL
                System.out.println("BPEL Process GUID - "+bpelProcessId.getProcessGUID());              // got NULL
                System.out.println("BPEL Process ID - "+bpelProcessId.getProcessId());                  // BPEL Process ID - FaultHandler_BPEL
                System.out.println("BPEL Revision Tag - "+bpelProcessId.getRevisionTag());              // revision number of the BPEL Process 1.0
                
                processName = bpelProcessId.getProcessId();
                
            }
            else{
                System.out.println("BPEL Process ID is NULL");
            }
            
            String xml = "<tns:errorMessage xmlns:tns=\"http://schemas.emerson.com/common/errorMessage\">" + 
            "  <tns:errorMessageHeader/>" + 
            "  <tns:errorMessagePayload/>\n" + 
            "  <tns:errorMessageDetails>\n" + 
            "    <tns:flowID>"+iFaultRecoveryContext.getInstanceId()+"</tns:flowID>\n" + 
            "    <tns:source>POC</tns:source>\n" + 
            "    <tns:destination>POC</tns:destination>\n" + 
            "    <tns:processDetails>\n" + 
            "      <tns:processID>"+iFaultRecoveryContext.getInstanceId()+"</tns:processID>\n" + 
            "      <tns:processName>"+processName+"</tns:processName>\n" + 
            "      <tns:processType>BPEL</tns:processType>\n" + 
            "      <tns:processLocation>"+iFaultRecoveryContext.getWsdlLocation()+"</tns:processLocation>\n" + 
            "    </tns:processDetails>\n" + 
            "    <tns:errorTime>1986-07-09T12:52:08.77</tns:errorTime>\n" + 
            "    <tns:environment>LabTest</tns:environment>\n" + 
            "    <tns:errorID>"+faultName+"</tns:errorID>\n" + 
            "    <tns:errorMessageCode>"+faultMessage+"</tns:errorMessageCode>\n" + 
            "    <tns:errorReason>"+faultMessage+"</tns:errorReason>\n" + 
            "    <tns:errorException>"+faultMessage+"</tns:errorException>\n" + 
            "    <tns:errorStacktrace>"+faultMessage+"</tns:errorStacktrace>\n" + 
            "    <tns:errorProperties>\n" + 
            "      <tns:errorProperty><tns:name>Activity Name</tns:name><tns:value>"+iFaultRecoveryContext.getActivityName()+"</tns:value></tns:errorProperty>\n" + 
            "      <tns:errorProperty><tns:name>Correlation ID</tns:name><tns:value>"+iFaultRecoveryContext.getCorrelationId()+"</tns:value></tns:errorProperty>\n" + 
            "      <tns:errorProperty><tns:name>Partner Link Name</tns:name><tns:value>"+iFaultRecoveryContext.getPartnerLinkName()+"</tns:value></tns:errorProperty>\n" + 
            "    </tns:errorProperties>\n" + 
            "  </tns:errorMessageDetails>\n" + 
            "</tns:errorMessage>";
            
            Locator locator = iFaultRecoveryContext.getLocator();
            
            IDeliveryService deliveryService = (IDeliveryService)locator.lookupService(IDeliveryService.SERVICE_NAME);

            NormalizedMessage nm = new NormalizedMessage();
            nm.addPart("payload", xml);

            deliveryService.post("CEH_BPEL_CommonErrorHandler", "handleError", nm);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "Success";
    }
}
