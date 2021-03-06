// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.3.0, build 070610.1800.23513)

package com.gssamerica.mdm.services;


public class ExecutionStatusType implements java.io.Serializable {
    protected java.lang.String status;
    protected java.lang.String description;
    protected java.lang.String dataObject;
    protected java.lang.String returnValue;
    protected com.gssamerica.mdm.services.RecordIdentifierType dataObjectId;
    protected com.gssamerica.mdm.services.FaultType fault;
    
    public ExecutionStatusType() {
    }
    
    public java.lang.String getStatus() {
        return status;
    }
    
    public void setStatus(java.lang.String status) {
        this.status = status;
    }
    
    public java.lang.String getDescription() {
        return description;
    }
    
    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    
    public java.lang.String getDataObject() {
        return dataObject;
    }
    
    public void setDataObject(java.lang.String dataObject) {
        this.dataObject = dataObject;
    }
    
    public java.lang.String getReturnValue() {
        return returnValue;
    }
    
    public void setReturnValue(java.lang.String returnValue) {
        this.returnValue = returnValue;
    }
    
    public com.gssamerica.mdm.services.RecordIdentifierType getDataObjectId() {
        return dataObjectId;
    }
    
    public void setDataObjectId(com.gssamerica.mdm.services.RecordIdentifierType dataObjectId) {
        this.dataObjectId = dataObjectId;
    }
    
    public com.gssamerica.mdm.services.FaultType getFault() {
        return fault;
    }
    
    public void setFault(com.gssamerica.mdm.services.FaultType fault) {
        this.fault = fault;
    }
}
