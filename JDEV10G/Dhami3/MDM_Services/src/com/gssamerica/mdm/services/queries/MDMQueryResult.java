package com.gssamerica.mdm.services.queries;

import java.util.ArrayList;
import java.util.List;

public class MDMQueryResult {

	private boolean isForeignReference = false;
	private String tableName = null;
	private String tableCode = null;
	private String columnName = null;
	private String columnCode = null;
	private String dataType = null;
	private List foreignColumnIndexes = null;
	
	public MDMQueryResult(String tableCode, String tableName, String columnCode, String columnName, String dataType, boolean isForeignReference){
		this.tableName = tableName;
		this.columnName = columnName;
		this.tableCode = tableCode;
		this.columnCode = columnCode;
		this.dataType = dataType;
		this.isForeignReference = isForeignReference;
		foreignColumnIndexes = new ArrayList();
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public boolean isForeignReference() {
		return isForeignReference;
	}

	public void setForeignReference(boolean isForeignReference) {
		this.isForeignReference = isForeignReference;
	}

	public List getForeignColumnIndexes() {
		return foreignColumnIndexes;
	}
	public void addForeignColumnIndex(int index){
		foreignColumnIndexes.add(index);
	}

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
}
