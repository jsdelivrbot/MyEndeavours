// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.3.0, build 070610.1800.23513)

package com.gssamerica.mdm.services;


public class ReplicateDataRequestType implements java.io.Serializable {
    protected com.gssamerica.mdm.services.RecordType[] record;
    protected com.gssamerica.mdm.services.RepositoryInformationType repositoryInfo;
    protected com.gssamerica.mdm.services.ConfigurationDataType configData;
    
    public ReplicateDataRequestType() {
    }
    
    public com.gssamerica.mdm.services.RecordType[] getRecord() {
        return record;
    }
    
    public void setRecord(com.gssamerica.mdm.services.RecordType[] record) {
        this.record = record;
    }
    
    public com.gssamerica.mdm.services.RepositoryInformationType getRepositoryInfo() {
        return repositoryInfo;
    }
    
    public void setRepositoryInfo(com.gssamerica.mdm.services.RepositoryInformationType repositoryInfo) {
        this.repositoryInfo = repositoryInfo;
    }
    
    public com.gssamerica.mdm.services.ConfigurationDataType getConfigData() {
        return configData;
    }
    
    public void setConfigData(com.gssamerica.mdm.services.ConfigurationDataType configData) {
        this.configData = configData;
    }
}
