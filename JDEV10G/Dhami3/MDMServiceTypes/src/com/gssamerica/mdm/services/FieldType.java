// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.3.0, build 070610.1800.23513)

package com.gssamerica.mdm.services;


public class FieldType implements java.io.Serializable {
    protected java.lang.String dataType;
    protected com.gssamerica.mdm.services.UnifiedFieldType taxonomyField;
    protected com.gssamerica.mdm.services.UnifiedFieldType unifiedField;
    
    public FieldType() {
    }
    
    public java.lang.String getDataType() {
        return dataType;
    }
    
    public void setDataType(java.lang.String dataType) {
        this.dataType = dataType;
    }
    
    public com.gssamerica.mdm.services.UnifiedFieldType getTaxonomyField() {
        return taxonomyField;
    }
    
    public void setTaxonomyField(com.gssamerica.mdm.services.UnifiedFieldType taxonomyField) {
        this.taxonomyField = taxonomyField;
    }
    
    public com.gssamerica.mdm.services.UnifiedFieldType getUnifiedField() {
        return unifiedField;
    }
    
    public void setUnifiedField(com.gssamerica.mdm.services.UnifiedFieldType unifiedField) {
        this.unifiedField = unifiedField;
    }
}