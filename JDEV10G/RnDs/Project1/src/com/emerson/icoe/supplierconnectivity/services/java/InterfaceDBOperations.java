package com.emerson.icoe.supplierconnectivity.services.java;

import java.sql.Connection;
import java.util.HashMap;

public interface InterfaceDBOperations 
{
    boolean insertDataInterimHeader(HashMap mapHeaderData);
    boolean insertDataInterimDetail(HashMap mapDetailData);
    
    //boolean insertDataTransactionHeader(HashMap mapHeaderData);
    //boolean insertDataTransactionDetail(HashMap mapDetailData);
}
