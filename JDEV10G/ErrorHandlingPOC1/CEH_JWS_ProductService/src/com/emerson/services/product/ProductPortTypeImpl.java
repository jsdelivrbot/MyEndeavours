package com.emerson.services.product;

import com.emerson.schemas.common.envelope.FaultType;
import com.emerson.schemas.common.envelope.HeaderType;
import com.emerson.schemas.common.envelope.SeverityTypeEnum;
import com.emerson.schemas.product.ProductType;

public class ProductPortTypeImpl {
    public String processProduct(ProductType product, HeaderType headerPart) throws FaultType {
        
        System.out.println("["+this.getClass()+"]: "+"processProduct called");
        System.out.println("["+this.getClass()+"]: "+"Header Got is:");
        try{
            System.out.println("["+this.getClass()+"]: flowID="+headerPart.getFlowID()+" transactionID="+headerPart.getTransactionID()+" OrginalSource="+headerPart.getOriginalSource()+" Destination="+headerPart.getDestination()+" UserId="+headerPart.getUserID());
            System.out.println("["+this.getClass()+"]: Routing Index Length = "+headerPart.getRoutingInfo().getSystem().length);
            System.out.println("["+this.getClass()+"]: Header Properties Got = "+headerPart.getHeaderProperties().getHeaderProperty().length);   
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        if(product.getProductName()!=null && product.getProductDescription()!=null && product.getProductCatagory()!=null){
            if(product.getProductCatagory().getValue().equalsIgnoreCase("Other")){
                FaultType dataReplicate = new FaultType();
                SeverityTypeEnum severity = SeverityTypeEnum.fromValue(SeverityTypeEnum._MEDIUM);
                dataReplicate.setFaultCode("Invalid");
                dataReplicate.setFaultMessage("Invalid Product Type");
                dataReplicate.setFaultSeverity(severity);
                dataReplicate.setFaultSummary("Product Type received is not supportable");
                throw dataReplicate;
            }
        }
        else{
            FaultType dataInsufficient = new FaultType();
            SeverityTypeEnum severity = SeverityTypeEnum.fromValue(SeverityTypeEnum._HIGH);
            dataInsufficient.setFaultCode("Insufficient");
            dataInsufficient.setFaultMessage("NULL data got");
            dataInsufficient.setFaultSeverity(severity);
            dataInsufficient.setFaultSummary("Product data got is insufficient to save");
            throw dataInsufficient;
        }
        return "SUCCESS";
    }
}
