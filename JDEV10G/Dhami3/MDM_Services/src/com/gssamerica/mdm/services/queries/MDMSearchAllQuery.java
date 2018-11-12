package com.gssamerica.mdm.services.queries;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.db.DatabaseDefinition;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.db.castor.ForeignKey;
import com.gssamerica.mdm.services.db.castor.Table;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;

import java.util.ArrayList;
import java.util.List;

public class MDMSearchAllQuery extends MDMQuery{
    public MDMSearchAllQuery(String tableName) throws MDMXMLException, 
                                                      MDMQueryFormationException {
        this.table = DatabaseDefinition.getInstance().getTableByName(tableName);
        this.fromTablesList = new ArrayList();
        this.joinWheresList = new ArrayList();
        this.projection = new ArrayList();
        this.queryResult = new ArrayList();
        this.referenceTablesList = new ArrayList();
        createProjection(this.table, this.projection, 0, null);
    }

    protected String createQuery() throws MDMQueryFormationException {
        String query = null;
        String whereClause = "( ";
        String operator = null;
        String projectedColumns = null;
        String fromTables = null;
        String joinTables = null;

        try {
            projectedColumns = getProjectedColumnsString();
            fromTables = getFromTablesString();
            joinTables = getJoinsString();
            
            query = "select " + projectedColumns + " from " + fromTables
                            + " where " + joinTables + " ";
        }
        catch (Exception e) {
            e.printStackTrace();
            MDMQueryFormationException mdmQueryFormationException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating SearchAll Query":e.getMessage());
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
    
    protected String getJoinsString(){
            String joinTables = "";
            for (int i = 0; i < this.joinWheresList.size(); i++) {
                    joinTables = joinTables + (String) this.joinWheresList.get(i);
                    if (i != (this.joinWheresList.size() - 1)) {
                        joinTables = joinTables + " "+MDMConstants.AND_OPERATOR + " ";
                    }
            }
            return joinTables;
    }

    protected int createProjection(Table table, List projection, int columnId, String tableAlias) throws MDMQueryFormationException {
        String tableName = null;
        String referenceTablePrefix = null;
        Column[] columns = null;
        Column column = null;
        ForeignKey foreignKey = null;
        boolean isForeignKey = false;
        String referenceTableCode = null;
        Table referenceTable = null;
        String referenceColumnName = null;
        Column referenceColumn = null;
        MDMQueryResult queryColumn = null;
        MDMQueryResult queryParentColumn = null;
        int parentColumnId = columnId;
        boolean isForiegnRefernce = false;
        boolean isMultiRefernce = false;

        try{
            tableName = table.getName();
            columns = table.getColumn();
            //System.out.println("[createProjection] "+((tableAlias==null)?(table.getName()):(table.getName()+" "+tableAlias))+" added in fromTablesList");
            fromTablesList.add(((tableAlias==null)?(table.getName()):(table.getName()+" "+tableAlias)));
            tableName = ((tableAlias==null)?tableName:tableAlias);
            //System.out.println("ParentColumnId got is - "+columnId);
            if(parentColumnId!=0){
                    columnId++;
                    queryParentColumn = (MDMQueryResult)this.queryResult.get(parentColumnId);
                    isForiegnRefernce = true;
            }
            for (int i = 0; i < columns.length; i++) {
                    isForeignKey = false;
                    isMultiRefernce = false;
                    column = columns[i];
                    //System.out.println("[createProjection] column - "+column.getName());
                    foreignKey = column.getForeignKey();
                    queryColumn = new MDMQueryResult(table.getCode(), table.getName(),column.getCode(), column.getName(), column.getType().toString(),isForiegnRefernce);
                    //System.out.println("Query Result added at - "+columnId);
                    this.queryResult.add(columnId,queryColumn);
                    
                    if(isForiegnRefernce){
                            queryParentColumn.addForeignColumnIndex(columnId);
                    }

                    if (foreignKey != null) {
                            //System.out.println("[createProjection]its a forieng key to "+foreignKey.getReferenceTable().getName());
                            isForeignKey = true;
                            referenceTableCode = foreignKey.getReferenceTable().getCode();
                            referenceColumnName = foreignKey.getReferenceTable().getColumn();

                            referenceTable = DatabaseDefinition.getInstance().getTableByCode(referenceTableCode);
                            referenceColumn = DatabaseDefinition.getInstance().getColumnByName(referenceColumnName);
                            
            //                              if(!referenceTablesList.contains(referenceTable.getName())){
            //                                      referenceTablesList.add(referenceTable.getName());
            //                                      referenceTablePrefix = referenceTable.getName();
            //                              }
            //                              else{
                                    //referenceTablePrefix = referenceTable.getName()+(prefixCounter++);
                                     referenceTablePrefix = column.getName();
            //                                      isMultiRefernce = true;
            //                              }
                            
                            //System.out.println("[createProjection] - (" + tableName + "." + column.getName()+ " = " + referenceTablePrefix + "."  + referenceColumn.getName() + ") added into joinWheresList");
                            
                            joinWheresList.add("(" + tableName + "." + column.getName()+" "+MDMConstants.EQUAL_OPERATOR+" "+ referenceTablePrefix + "."+ referenceColumn.getName() + "(+))");

                    }
                    //System.out.println("[createProjection] - "+tableName + "." + column.getName()+" added to projection");
                    projection.add(tableName + "." + column.getName());
                    if (isForeignKey) {
            //                              if(!isMultiRefernce){
            //                                      referenceTablePrefix = null;
            //                              }
                            columnId = createProjection(referenceTable,projection,columnId,referenceTablePrefix);
                            columnId--;
                    }
                    columnId++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            MDMQueryFormationException mdmQueryFormationException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating projection of SearchAll Query":e.getMessage());
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
            mdmQueryFormationException.setQuery(getQuery());
            throw (mdmQueryFormationException);
        }
        
        return columnId;
    }

    public void addQueryItem(String tableName, String dataType, String columnName, String columnValue) {
        // do nothing
    }
}
