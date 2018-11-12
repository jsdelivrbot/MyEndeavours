package com.gssamerica.mdm.services.queries;

import java.util.List;

import com.gssamerica.mdm.services.db.castor.Table;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;

import java.util.Hashtable;

public abstract class MDMQuery {
	
	protected String query = null;
	protected Table table = null;
	protected List queryResult = null;
	protected List fromTablesList = null;
	protected List referenceTablesList = null;
	protected List joinWheresList = null;
	protected List projection = null;
	protected List columnDataTypes = null;
	protected List columnNames = null;
	protected List columnValues = null;
        private Hashtable configData = null;
	
	protected abstract String createQuery() throws MDMQueryFormationException;
	protected abstract int createProjection(Table table, List projection, int columnId, String tableAlias)  throws MDMQueryFormationException;
	public abstract void addQueryItem(String tableName, String dataType, String columnName, String columnValue);
	
	protected String getProjectedColumnsString(){
		String projectedColumns = "";
		for (int i = 0; i < this.projection.size(); i++) {
			projectedColumns = projectedColumns
					+ (String) this.projection.get(i);
			if (i != (this.projection.size() - 1)) {
				projectedColumns = projectedColumns + ", ";
			}
		}
		return projectedColumns;
	}
	
	protected String getFromTablesString(){
		String fromTables = "";
		for (int i = 0; i < this.fromTablesList.size(); i++) {
			fromTables = fromTables + (String) this.fromTablesList.get(i);
			if (i != (this.fromTablesList.size() - 1)) {
				fromTables = fromTables + ", ";
			}
		}
		return fromTables;
	}
	
	protected String getJoinsString(){
		String joinTables = "";
		for (int i = 0; i < this.joinWheresList.size(); i++) {
			joinTables = joinTables + (String) this.joinWheresList.get(i);
			//if (i != (this.joinWheresList.size() - 1)) {
			joinTables = joinTables + " "+MDMConstants.AND_OPERATOR + " ";
			//}
		}
		return joinTables;
	}
	
	public boolean validateQuery() {
		return true;
	}
	
	public String getQuery() throws MDMQueryFormationException {
		if(this.query==null){
			this.query = createQuery();
		}
		return this.query;
	}
	
	public List getQueyResult() {
		return this.queryResult;
	}

    public void setConfigData(Hashtable configData) {
        this.configData = configData;
    }

    public Hashtable getConfigData() {
        return this.configData;
    }
}
