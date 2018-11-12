package com.emerson.service.vdst;

import com.emerson.servicetypes.vdst.com.emerson.service.vdst.tool.common.FaultDetailType;
import com.emerson.servicetypes.vdst.com.emerson.service.vdst.tool.common.SOAPException;

public class ConversionWebServicePortTypeImpl {
    public void importCADModel(String sessionID, 
                               String[] filenames) throws SOAPException, 
                                                          FaultDetailType {
        System.out.println("*******************START*********************");
        System.out.println("ConversionWebService: importCADModel operation called");
        System.out.println("ConversionWebService.importCADModel: Input - sessionID="+sessionID+" fileNames=["+filenames+"]");
        System.out.println("*******************STOP**********************");
    }

    public void importOtherModel(String sessionID, String filename, 
                                 String productID) throws SOAPException, 
                                                          FaultDetailType {
        System.out.println("*******************START*********************");
        System.out.println("ConversionWebService: importOtherModel operation called");
        System.out.println("ConversionWebService.importOtherModel: Input - sessionID="+sessionID+" filename="+filename+" productID="+productID);
        System.out.println("*******************STOP**********************");
    }

    public void retesselateModel(String sessionID, String productID, float tesselationFactor) throws SOAPException, 
                                                                                                     FaultDetailType {
        System.out.println("*******************START*********************");
        System.out.println("ConversionWebService: retesselateModel operation called");
        System.out.println("ConversionWebService.retesselateModel: Input - sessionID="+sessionID+" productID="+productID+" tesselationFactor="+tesselationFactor);
        System.out.println("*******************STOP**********************");
    }

    public void importERPData(String sessionID) throws SOAPException, 
                                                       FaultDetailType {
        System.out.println("*******************START*********************");
        System.out.println("ConversionWebService: importERPData operation called");
        System.out.println("ConversionWebService.importERPData: Input - sessionID="+sessionID);
        System.out.println("*******************STOP**********************");
    }

    public void updateProductContent(String sessionID, String productID, byte[] content, 
        ContentType contentType) throws SOAPException, FaultDetailType {
        System.out.println("*******************START*********************");
        System.out.println("ConversionWebService: updateProductContent operation called");
        System.out.println("ConversionWebService.updateProductContent: Input - sessionID="+sessionID+" productID="+productID+" content="+content+"] contentType="+contentType);
        System.out.println("*******************STOP**********************");
    }
}
