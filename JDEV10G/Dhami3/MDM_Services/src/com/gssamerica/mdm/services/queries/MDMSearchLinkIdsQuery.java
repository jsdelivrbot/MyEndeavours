package com.gssamerica.mdm.services.queries;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.db.DatabaseDefinition;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.db.castor.Table;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;

import java.util.ArrayList;
import java.util.List;

public class MDMSearchLinkIdsQuery extends MDMQuery{
    private int whereCount = 0;
    
    public MDMSearchLinkIdsQuery(String tableName, String autoId) throws MDMXMLException, 
                                                       MDMQueryFormationException {
        Column primaryKey = null;
        this.table = DatabaseDefinition.getInstance().getTableByName(tableName);
        primaryKey = DatabaseDefinition.getInstance().getTablePrimaryKey(tableName);
        this.fromTablesList = new ArrayList();
        this.projection = new ArrayList();
        this.queryResult = new ArrayList();
        this.columnNames = new ArrayList();
        this.columnValues = new ArrayList();
        this.columnDataTypes = new ArrayList();
        addQueryItem(tableName,primaryKey.getType().toString(),primaryKey.getName(),autoId);
        createProjection(this.table, this.projection, 0,null);
    }

    protected String createQuery() throws MDMQueryFormationException {
        String projectedColumns = null;
        String fromTables = null;
        String whereClause = "";
        String operator = null;
        try {
            if(this.projection==null || this.projection.size()==0){
                return null;
            }
            projectedColumns = getProjectedColumnsString();
            fromTables = getFromTablesString();
            query = "select " + projectedColumns + " from " + fromTables
                            + " where ";
            for (int i = 0; i < this.whereCount; i++) {
    
                    if (this.columnValues.get(i) == null) {
                            operator = MDMConstants.COMPARE_WITH_NULL_OPERATOR;
                            this.columnValues.set(i, MDMConstants.NULL);
                    } else {
                            operator = MDMConstants.EQUAL_OPERATOR;
                    }
    
                    if (i == (this.whereCount - 1)) {
                            whereClause = whereClause + this.columnNames.get(i) + " "
                                            + operator + " " + this.columnValues.get(i);
                    } else {
                            whereClause = whereClause + this.columnNames.get(i) + " "
                                            + operator + " " + this.columnValues.get(i) + " "
                                            + MDMConstants.AND_OPERATOR + " ";
                    }
            }
            query = query + whereClause;
        }
        catch (Exception e) {
            e.printStackTrace();
            MDMQueryFormationException mdmQueryFormationException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating SearchLinkIds Query":e.getMessage());
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

    protected int createProjection(Table table, List projection, int columnId, String tableAlias) throws MDMQueryFormationException {
        String tableName = null;
        Column[] columns = null;
        Column column = null;
        MDMQueryResult queryColumn = null;
        
        try {
            tableName = table.getName();
            columns = table.getColumn();
            this.fromTablesList.add(tableName);
            if(columns!=null){
                for(int i=0;i<columns.length;i++){
                    column = columns[i];
                    if(column.getForeignKey()!=null){
                        queryColumn = new MDMQueryResult(table.getCode(), table.getName(),column.getCode(), column.getName(), column.getType().toString(),false);
                        this.queryResult.add(columnId,queryColumn);
                        this.projection.add(tableName + "." + column.getName());
                        columnId++;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            MDMQueryFormationException mdmQueryFormationException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating projecttion for SearchLinkIds Query":e.getMessage());
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
        return 0;
    }

    public void addQueryItem(String tableName, String dataType, String columnName, String columnValue) {
        this.query = null;
        if (columnValue!=null && (!columnValue.equalsIgnoreCase(""))) {
            System.out.println(dataType + " " + columnName + " " + columnValue);
            this.columnDataTypes.add(dataType);
            if (dataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName())
                            || dataType.equalsIgnoreCase(MDMConstants.MDM_DATE
                                            .getTypeName())
                            || dataType.equalsIgnoreCase(MDMConstants.MDM_CHAR
                                            .getTypeName())) {
                    columnValue = "'" + columnValue + "'";
            }
            
            if (dataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName())
                            ||  dataType.equalsIgnoreCase(MDMConstants.MDM_CHAR.getTypeName())) {
                    this.columnNames.add(MDMConstants.TO_LOWER+"("+tableName+"."+columnName+")");
                    this.columnValues.add(MDMConstants.TO_LOWER+"("+columnValue+")");
            }
            else{
                    this.columnNames.add(tableName+"."+columnName);
                    this.columnValues.add(columnValue);
            }
            
            this.whereCount++;
        }
    }
}
