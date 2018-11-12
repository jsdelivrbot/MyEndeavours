package com.gssamerica.mdm.services.queries;

import java.util.ArrayList;
import java.util.List;

import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.db.DatabaseDefinition;
import com.gssamerica.mdm.services.db.castor.Table;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.utils.MDMUtils;

public class MDMInsertQuery extends MDMQuery{
	
	private int fieldCount = 0;
	
	public MDMInsertQuery (String tableName) throws MDMXMLException {
            this.columnDataTypes = new ArrayList();
            this.columnNames = new ArrayList();
            this.columnValues = new ArrayList();
            this.fieldCount = 0;
            this.table = DatabaseDefinition.getInstance().getTableByName(tableName);
	}
	
	public void addQueryItem(String tableName, String dataType, String columnName, String columnValue){
            this.query = null;
            this.columnDataTypes.add(dataType);
            this.columnNames.add(columnName);
            
            if(dataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName()) || dataType.equalsIgnoreCase(MDMConstants.MDM_CHAR.getTypeName())){
                columnValue = "'"+columnValue+"'";
            }
            else if(dataType.equalsIgnoreCase(MDMConstants.MDM_DATE.getTypeName())){
                columnValue = MDMUtils.getDateString(MDMUtils.getDateFromString(columnValue,MDMConstants.DATE_FORMAT_EXTERNAL),MDMConstants.DATE_FORMAT_MDM_HUB);
                System.out.println("["+this.getClass()+"] Date Format converted - "+columnValue);
                columnValue = "'"+columnValue+"'";
            }
            this.columnValues.add(columnValue);
            this.fieldCount++;
	}
	
	public boolean validateQuery(){
		if(this.table.getName()==null || this.table.getName().equals("")){
			return false;
		}
		if(this.columnNames.size()!=this.columnValues.size() || fieldCount==0){
			return false;
		}
		return true;
	}
	
	protected String createQuery() throws MDMQueryFormationException {
            String query = null;
            String columns = "";
            String values = "";
            try {
                query = "insert into "+this.table.getName()+" ";
                for(int i=0;i<this.fieldCount;i++){   
                    if(i==(this.fieldCount-1)){
                            columns = columns+this.columnNames.get(i);
                            values = values+this.columnValues.get(i);
                    }
                    else{
                            columns = columns+this.columnNames.get(i)+", ";
                            values = values+this.columnValues.get(i)+", ";
                    }			
                }
                query = query+"("+columns+") values ("+values+")";
            }
            catch (Exception e) {
                MDMQueryFormationException mdmQueryFormationException = null;
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating Insert Query":e.getMessage());
                mdmQueryFormationException = new MDMQueryFormationException(message,e.getCause());
                mdmQueryFormationException.setColumnDataTypes(this.columnDataTypes);
                mdmQueryFormationException.setColumnNames(this.columnNames);
                mdmQueryFormationException.setColumnValues(this.columnValues);
                mdmQueryFormationException.setTableName(this.table.getName());
                mdmQueryFormationException.setQuery(query);
                throw (mdmQueryFormationException);
            }
            return query;
	}
//	public void addColumn(String columnName){
//		this.columnNames.add(columnName);
//	}
//	
//	public void addValue(String columnValue){
//		this.columnValues.add(columnValue);
//	}

	@Override
	protected int createProjection(Table table, List projection, int columnId, String tableAlias) {
            // DO NOTHING
            return 0;
	}
}
