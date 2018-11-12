package com.gssamerica.mdm.services.db;

import com.gssamerica.mdm.constants.MDMConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.db.castor.Database;
import com.gssamerica.mdm.services.db.castor.Table;

import com.gssamerica.mdm.services.exceptions.MDMXMLException;

import com.gssamerica.mdm.utils.MDMUtils;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.exolab.castor.xml.Unmarshaller;

import org.xml.sax.InputSource;

public class DatabaseDefinition {

	private static DatabaseDefinition definition = null;
	private Database database = null;
	private Hashtable dbColumnsbyName = null;
	
	private DatabaseDefinition() throws MDMXMLException {
		System.out.println("[DatabaseDefinition] Called...");
		this.database = prepareDefinition();
		if(this.database != null){
			System.out.println("[DatabaseDefinition]Database - "+this.database.getName());
		}
		else{
			System.out.println("[DatabaseDefinition]Database is NULL");
		}
		this.dbColumnsbyName = getAllColumns();
	}
	
	private Database prepareDefinition() throws MDMXMLException {
            Database database = null;
            InputStream is = null;
            InputSource inputSource = null;
            System.out.println("[prepareDefinition] Preparing");
            String xmlSource = "db.xml";
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlSource);
            inputSource = new InputSource(is);
            database = (Database)Unmarshaller.unmarshal(Database.class,inputSource);
        } catch (ValidationException e) {
             e.printStackTrace();
             MDMXMLException mdmXMLException = null;
             String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while Validating Database Definition":e.getMessage());
             mdmXMLException = new MDMXMLException(message,e.getCause(), e.getStackTrace());
             mdmXMLException.setOriginalErrorCode(""+e.getErrorCode());
             if(e.getLocation()!=null){
                 mdmXMLException.setLocation(e.getLocation().toString());
             }
             mdmXMLException.setData(MDMUtils.inputStreamAsString(is));
             mdmXMLException.setSource(xmlSource);
             mdmXMLException.setSeverity(MDMConstants.SEVERITY_HIGH);
             throw (mdmXMLException);
        } catch (MarshalException e) {
             e.printStackTrace();
             MDMXMLException mdmXMLException = null;
             String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while Marshalling Database Definition":e.getMessage());
             mdmXMLException = new MDMXMLException(message,e.getCause(), e.getStackTrace());
             mdmXMLException.setOriginalErrorCode(""+e.getErrorCode());
             mdmXMLException.setData(MDMUtils.inputStreamAsString(is));
             mdmXMLException.setSource(xmlSource);
             mdmXMLException.setSeverity(MDMConstants.SEVERITY_HIGH);
             throw (mdmXMLException);
        }
        if (database != null){
                System.out.println("[prepareDefinition]Database - "+database.getName());
            }
            else{
                System.out.println("[prepareDefinition]Database is NULL");
            }
            return database;
	}
	
	public static synchronized DatabaseDefinition getInstance() throws MDMXMLException {
            if(definition==null){
                definition = new DatabaseDefinition();       
            }
            return definition;
	}
	
	public Table[] getTables(){
            return this.database.getTable();
	}
	
	public Table getTableByCode(String tableCode){
            if(this.database != null){
                    System.out.println("[getTableByCode]Database - "+this.database.getName());
            }
            else{
                    System.out.println("[getTableByCode]Database is NULL");
            }
            Table[] tables = this.database.getTable();
            Table table = null;
            for(int i=0;i<this.database.getTableCount();i++){
                    table = tables[i];
                    if(table.getCode().equalsIgnoreCase(tableCode)){
                            break;
                    }
            }
            return table;
	}
	
	public Table getTableByName(String tableName){
            Table[] tables = this.database.getTable();
            Table table = null;
            for(int i=0;i<this.database.getTableCount();i++){
                    table = tables[i];
                    if(table.getName().equalsIgnoreCase(tableName)){
                            break;
                    }
            }
            return table;
	}
	
	public Column getTablePrimaryKey(String tableName){
            Column[] columns = null;
            Column column = null;
            Table table = getTableByName(tableName);
            columns = table.getColumn();
            for(int i=0;i<columns.length;i++){
                    column = columns[i];
                    if(column.getPrimaryKey()){
                            return column;
                    }
            }
            return null;
	}
	
	private Hashtable getAllColumns(){
            Hashtable dbColumns = new Hashtable();
            Table[] tables = null;
            Column[] columns = null;
            Table table = null;
            Column column = null;
            System.out.println("[getAllColumns] columnsCaching");
            
            if(this.database != null){
                    System.out.println("[getAllColumns]Database - "+this.database.getName());
            }
            else{
                    System.out.println("[getAllColumns]Database is NULL");
            }
            tables = this.database.getTable();
            for(int i=0;i<tables.length;i++){
                    table = tables[i];
                    columns = table.getColumn();
                    for(int j=0;j<columns.length;j++){
                            column = columns[j];
                            dbColumns.put(column.getName(),column);
                    }
            }
            return dbColumns;
	}
	
	public Column getColumnByName(String columnCode){
            Column column = null;
            column = (Column)this.dbColumnsbyName.get(columnCode);
            return column;
	}
}
