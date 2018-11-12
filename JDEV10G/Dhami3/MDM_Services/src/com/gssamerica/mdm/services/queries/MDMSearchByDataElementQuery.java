package com.gssamerica.mdm.services.queries;

import java.util.ArrayList;
import java.util.List;

import com.gssamerica.mdm.services.db.DatabaseDefinition;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.db.castor.ForeignKey;
import com.gssamerica.mdm.services.db.castor.Table;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.utils.MDMUtils;

public class MDMSearchByDataElementQuery extends MDMQuery {

	private String logicalOperator = null;
	private int whereCount = 0;
	private int prefixCounter = 1;
	
	public MDMSearchByDataElementQuery(String tableName, String logicalOperator) throws MDMXMLException, 
                                                                      MDMQueryFormationException {
		this.columnDataTypes = new ArrayList();
		this.table = DatabaseDefinition.getInstance().getTableByName(tableName);
		this.logicalOperator = logicalOperator;
		this.columnNames = new ArrayList();
		this.columnValues = new ArrayList();
		this.fromTablesList = new ArrayList();
		this.joinWheresList = new ArrayList();
		this.projection = new ArrayList();
		this.queryResult = new ArrayList();
		this.referenceTablesList = new ArrayList();
		this.whereCount = 0;
		createProjection(this.table, this.projection, 0, null);
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
                                    
                                    //System.out.println("[createProjection] - (" + tableName + "." + column.getName()+ " = " + referenceTablePrefix + "." + referenceColumn.getName() + ") added into joinWheresList");
                                    
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
                    String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating projection for SearchByDataElement Query":e.getMessage());
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
		
		return columnId;
	}

	public void addQueryItem(String tableAlias, String dataType, String columnName,
			String columnValue) {
            this.query = null;
            if (columnValue!=null && (!columnValue.equalsIgnoreCase(""))) {
                System.out.println(dataType + " " + columnName + " " + columnValue);
                this.columnDataTypes.add(dataType);
                if (dataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName())
                                || dataType.equalsIgnoreCase(MDMConstants.MDM_CHAR
                                                .getTypeName())) {
                        columnValue = "'" + columnValue + "'";
                }
                else if(dataType.equalsIgnoreCase(MDMConstants.MDM_DATE.getTypeName())){
                    columnValue = MDMUtils.getDateString(MDMUtils.getDateFromString(columnValue,MDMConstants.DATE_FORMAT_EXTERNAL),MDMConstants.DATE_FORMAT_MDM_HUB);
                    System.out.println("["+this.getClass()+"] Date Format converted - "+columnValue);
                    columnValue = "'"+columnValue+"'";
                }
                
                if (dataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName())
                                ||	dataType.equalsIgnoreCase(MDMConstants.MDM_CHAR.getTypeName())) {
                        this.columnNames.add(MDMConstants.TO_LOWER+"("+tableAlias+"."+columnName+")");
                        this.columnValues.add(MDMConstants.TO_LOWER+"("+columnValue+")");
                }
                else{
                        this.columnNames.add(tableAlias+"."+columnName);
                        this.columnValues.add(columnValue);
                }
                
                this.whereCount++;
            }
	}

	// public String getColumnPrimitiveValue(String dataType, Object
	// columnValue){
	// String returnValue = null;
	// if(dataType.equalsIgnoreCase(MDMConstants.stringDataType)){
	// returnValue = (String)columnValue;
	// }
	// else if(dataType.equalsIgnoreCase(MDMConstants.dateDataType)){
	//			
	// }
	// return returnValue;
	// }

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
                                            + this.logicalOperator + " ";
                    }
                }
                query = query + whereClause+" )";
            }
            catch (Exception e) {
                e.printStackTrace();
                MDMQueryFormationException mdmQueryFormationException = null;
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating SearchByDataElement Query":e.getMessage());
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
}
