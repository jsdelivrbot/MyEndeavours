package com.gssamerica.mdm.constants;

public class MDMDataTypes {
    
    private int sqlType = 0;
    private String typeName = null;
    
    public MDMDataTypes(int sqlType, String typeName){
            this.sqlType = sqlType;
            this.typeName = typeName;
    }
    
    public String getTypeName() {
            return typeName;
    }
    public int getSqlType() {
            return sqlType;
    }
}
