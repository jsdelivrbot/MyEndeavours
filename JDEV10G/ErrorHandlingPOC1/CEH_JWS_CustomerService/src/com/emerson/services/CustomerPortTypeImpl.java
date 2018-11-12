package com.emerson.services;

import com.emerson.services.types.com.emerson.schemas.common.envelope.FaultType;
import com.emerson.services.types.com.emerson.schemas.common.envelope.HeaderType;
import com.emerson.services.types.com.emerson.schemas.common.envelope.SeverityTypeEnum;
import com.emerson.services.types.com.emerson.schemas.customer.CustomerType;

public class CustomerPortTypeImpl {
    public String processCustomer(CustomerType customer, HeaderType headerPart) throws FaultType {
        
        System.out.println("["+this.getClass()+"]: "+"processCustomer called");
        System.out.println("["+this.getClass()+"]: "+"Header Got is:");
        try{
            System.out.println("["+this.getClass()+"]: flowID="+headerPart.getFlowID()+" transactionID="+headerPart.getTransactionID()+" OrginalSource="+headerPart.getOriginalSource()+" Destination="+headerPart.getDestination()+" UserId="+headerPart.getUserID());
            System.out.println("["+this.getClass()+"]: Routing Index Length = "+headerPart.getRoutingInfo().getSystem().length);
            System.out.println("["+this.getClass()+"]: Header Properties Got = "+headerPart.getHeaderProperties().getHeaderProperty().length);   
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        if(customer.getName()!=null && customer.getAge()!=null && customer.getCompany()!=null){
            if(customer.getName().getFirstName().equalsIgnoreCase("John") && customer.getName().getLastName().equalsIgnoreCase("Mantle")){
                FaultType dataReplicate = new FaultType();
                SeverityTypeEnum severity = SeverityTypeEnum.fromValue(SeverityTypeEnum._MEDIUM);
                dataReplicate.setFaultCode("Duplicate");
                dataReplicate.setFaultMessage("Data Already Exists");
                dataReplicate.setFaultSeverity(severity);
                dataReplicate.setFaultSummary("Customer John Mantle is already in our list");
                throw dataReplicate;
            }
        }
        else{
            FaultType dataInsufficient = new FaultType();
            SeverityTypeEnum severity = SeverityTypeEnum.fromValue(SeverityTypeEnum._HIGH);
            dataInsufficient.setFaultCode("Insufficient");
            dataInsufficient.setFaultMessage("NULL data got");
            dataInsufficient.setFaultSeverity(severity);
            dataInsufficient.setFaultSummary("Customer data got is insufficient to save");
            throw dataInsufficient;
        }
        System.out.println("SUCCESS");
        return "SUCCESS";
    }
}
