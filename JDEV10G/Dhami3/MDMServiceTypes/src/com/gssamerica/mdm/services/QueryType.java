// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.3.0, build 070610.1800.23513)

package com.gssamerica.mdm.services;


public class QueryType implements java.io.Serializable {
    protected com.gssamerica.mdm.services.RecordCriteriaType recordCriteria;
    protected com.gssamerica.mdm.services.ResultDefinitionType resultDefinition;
    
    public QueryType() {
    }
    
    public com.gssamerica.mdm.services.RecordCriteriaType getRecordCriteria() {
        return recordCriteria;
    }
    
    public void setRecordCriteria(com.gssamerica.mdm.services.RecordCriteriaType recordCriteria) {
        this.recordCriteria = recordCriteria;
    }
    
    public com.gssamerica.mdm.services.ResultDefinitionType getResultDefinition() {
        return resultDefinition;
    }
    
    public void setResultDefinition(com.gssamerica.mdm.services.ResultDefinitionType resultDefinition) {
        this.resultDefinition = resultDefinition;
    }
}
