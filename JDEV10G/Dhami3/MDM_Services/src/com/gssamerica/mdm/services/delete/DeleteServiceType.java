// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.3.0, build 070610.1800.23513)

package com.gssamerica.mdm.services.delete;


public class DeleteServiceType implements java.io.Serializable {
    protected com.gssamerica.mdm.services.DelRemoteKeyRequestType deleteRecordRequest;
    protected boolean deleteType;
    
    public DeleteServiceType() {
    }
    
    public com.gssamerica.mdm.services.DelRemoteKeyRequestType getDeleteRecordRequest() {
        return deleteRecordRequest;
    }
    
    public void setDeleteRecordRequest(com.gssamerica.mdm.services.DelRemoteKeyRequestType deleteRecordRequest) {
        this.deleteRecordRequest = deleteRecordRequest;
    }
    
    public boolean isDeleteType() {
        return deleteType;
    }
    
    public void setDeleteType(boolean deleteType) {
        this.deleteType = deleteType;
    }
}
