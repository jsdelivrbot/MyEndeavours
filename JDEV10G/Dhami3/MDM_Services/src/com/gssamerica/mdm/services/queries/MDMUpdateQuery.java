package com.gssamerica.mdm.services.queries;

import com.gssamerica.mdm.services.db.DatabaseDefinition;

import com.gssamerica.mdm.services.db.castor.Table;
import com.gssamerica.mdm.constants.MDMConstants;

import com.gssamerica.mdm.services.db.castor.Column;

import com.gssamerica.mdm.services.db.castor.types.DataType;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;

import com.gssamerica.mdm.utils.MDMUtils;

import java.util.ArrayList;
import java.util.List;

public class MDMUpdateQuery extends MDMQuery {
    private int fieldCount = 0;
    private String autoId = null;
    private String primaryKeyString = null;

    public MDMUpdateQuery(String tableName, String autoId) throws MDMXMLException {
        DataType dataType = null;
        Column primaryKey = null;
        this.columnDataTypes = new ArrayList();
        this.columnNames = new ArrayList();
        this.columnValues = new ArrayList();
        this.autoId = autoId;
        this.fieldCount = 0;
        this.table = DatabaseDefinition.getInstance().getTableByName(tableName);
        primaryKey = DatabaseDefinition.getInstance().getTablePrimaryKey(tableName);
        dataType = primaryKey.getType();
        this.primaryKeyString = primaryKey.getName();
        if (dataType.toString().equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName()) || 
            dataType.toString().equalsIgnoreCase(MDMConstants.MDM_DATE.getTypeName()) || 
            dataType.toString().equalsIgnoreCase(MDMConstants.MDM_CHAR.getTypeName())) {
            autoId = "'" + autoId + "'";
        }
    }

    public void addQueryItem(String tableName, String dataType, 
                             String columnName, String columnValue) {
        this.query = null;
        this.columnDataTypes.add(dataType);
        this.columnNames.add(columnName);


        if (dataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName()) || 
            dataType.equalsIgnoreCase(MDMConstants.MDM_CHAR.getTypeName())) {
            columnValue = "'" + columnValue + "'";
        }
        else if(dataType.equalsIgnoreCase(MDMConstants.MDM_DATE.getTypeName())){
            columnValue = MDMUtils.getDateString(MDMUtils.getDateFromString(columnValue,MDMConstants.DATE_FORMAT_EXTERNAL),MDMConstants.DATE_FORMAT_MDM_HUB);
            System.out.println("["+this.getClass()+"] Date Format converted - "+columnValue);
            columnValue = "'"+columnValue+"'";
        }
        this.columnValues.add(columnValue);
        this.fieldCount++;
    }

    public boolean validateQuery() {
        if (this.table.getName() == null || this.table.getName().equals("")) {
            return false;
        }
        if (this.columnNames.size() != this.columnValues.size() || 
            fieldCount == 0) {
            return false;
        }
        return true;
    }

    protected String createQuery() throws MDMQueryFormationException {
        String query = null;
        try {
            if (columnNames.size() != 0) {
                query = "update " + this.table.getName() + " set ";
                for (int i = 0; i < this.fieldCount; i++) {
    
                    if (i == (this.fieldCount - 1)) {
                        query = 
                                query + this.columnNames.get(i) + "=" + this.columnValues.get(i);
                    } else {
                        query = 
                                query + this.columnNames.get(i) + "=" + this.columnValues.get(i) + 
                                ", ";
    
                    }
                }
                query = query + " where " + this.primaryKeyString + "=" + this.autoId;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            MDMQueryFormationException mdmQueryFormationException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating Update Query":e.getMessage());
            mdmQueryFormationException = new MDMQueryFormationException(message, e.getCause(), e.getStackTrace());
            mdmQueryFormationException.setColumnDataTypes(this.columnDataTypes);
            mdmQueryFormationException.setColumnNames(this.columnNames);
            mdmQueryFormationException.setColumnValues(this.columnValues);
            mdmQueryFormationException.setFromTablesList(this.fromTablesList);
            mdmQueryFormationException.setJoinWheresList(this.joinWheresList);
            mdmQueryFormationException.setProjection(this.projection);
            mdmQueryFormationException.setQueryResult(this.queryResult);
            mdmQueryFormationException.setReferenceTablesList(this.referenceTablesList);
            mdmQueryFormationException.setTableName(this.table.getName());
            mdmQueryFormationException.setQuery(query);
            throw (mdmQueryFormationException);
        }
        return query;
    }


    @Override
    protected int createProjection(Table table, List projection, int columnId, 
                                   String tableAlias) {
        // DO NOTHING
        return 0;
    }


}
