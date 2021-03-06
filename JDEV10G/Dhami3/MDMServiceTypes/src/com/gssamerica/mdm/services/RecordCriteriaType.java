// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.3.0, build 070610.1800.23513)

package com.gssamerica.mdm.services;


public class RecordCriteriaType implements java.io.Serializable {
    protected java.lang.String logicOperator;
    protected com.gssamerica.mdm.services.DataElementCriteriaType[] dataElementCriteria;
    protected com.gssamerica.mdm.services.RecordIdentifierCriteriaType[] recordIdentifierCriteria;
    protected com.gssamerica.mdm.services.SimpleValueCriteriaType[] keywordCriteria;
    protected java.lang.String tableCode;
    
    public RecordCriteriaType() {
    }
    
    public java.lang.String getLogicOperator() {
        return logicOperator;
    }
    
    public void setLogicOperator(java.lang.String logicOperator) {
        this.logicOperator = logicOperator;
    }
    
    public com.gssamerica.mdm.services.DataElementCriteriaType[] getDataElementCriteria() {
        return dataElementCriteria;
    }
    
    public void setDataElementCriteria(com.gssamerica.mdm.services.DataElementCriteriaType[] dataElementCriteria) {
        this.dataElementCriteria = dataElementCriteria;
    }
    
    public com.gssamerica.mdm.services.RecordIdentifierCriteriaType[] getRecordIdentifierCriteria() {
        return recordIdentifierCriteria;
    }
    
    public void setRecordIdentifierCriteria(com.gssamerica.mdm.services.RecordIdentifierCriteriaType[] recordIdentifierCriteria) {
        this.recordIdentifierCriteria = recordIdentifierCriteria;
    }
    
    public com.gssamerica.mdm.services.SimpleValueCriteriaType[] getKeywordCriteria() {
        return keywordCriteria;
    }
    
    public void setKeywordCriteria(com.gssamerica.mdm.services.SimpleValueCriteriaType[] keywordCriteria) {
        this.keywordCriteria = keywordCriteria;
    }
    
    public java.lang.String getTableCode() {
        return tableCode;
    }
    
    public void setTableCode(java.lang.String tableCode) {
        this.tableCode = tableCode;
    }
}
