package com.gssamerica.mdm.services.queries;

import com.gssamerica.mdm.constants.MDMConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.db.DatabaseDefinition;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.db.castor.Table;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;


public class MDMDeleteQuery extends MDMQuery {

    
    private String primaryKeyName=null;
    private String primaryKeyDataType=null;
    private String autoID = null;
    private String tableName = null;
    
    public MDMDeleteQuery(){
        
    }

//    public MDMDeleteQuery(String tableName, String autoId, String pkey, 
//                          String dataType) throws MDMXMLException {
//
//        this.autoID = autoId;
//        this.fieldCount = 0;
//        this.table = DatabaseDefinition.getInstance().getTableByName(tableName);
//        this.tableName = tableName;
//        Column primaryKey = DatabaseDefinition.getInstance().getTablePrimaryKey(tableName);
//        this.primaryKeyName = primaryKey.getName();
//        this.primaryKeyDataType = primaryKey.getType().toString();
//
//    }

    protected String createQuery() {
        String query = "";
        query = "delete from " + this.tableName + " where ";
        if (this.primaryKeyDataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName()) || 
            this.primaryKeyDataType.equalsIgnoreCase(MDMConstants.MDM_DATE.getTypeName()) || 
            this.primaryKeyDataType.equalsIgnoreCase(MDMConstants.MDM_CHAR.getTypeName())) {
            query = query + this.primaryKeyName + "='" + this.autoID + "'";
        } else {
            query = query + this.primaryKeyName + "=" + this.autoID;
        }
        return query;
    }


    @Override
    protected int createProjection(Table table, List projection, int columnId, 
                                   String tableAlias) {
        // DO NOTHING
        return 0;
    }

    public void addQueryItem(String tableName, String dataType, String columnName, String columnValue) {
        this.query = null;
        this.tableName = tableName;
        this.primaryKeyDataType = dataType;
        this.primaryKeyName = columnName;
        this.autoID = columnValue;
    }

   
}
