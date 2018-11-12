package com.gssamerica.mdm.services.queries.execution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.gssamerica.mdm.services.DataElementType;
import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.FieldType;
import com.gssamerica.mdm.services.LookupValueType;
import com.gssamerica.mdm.services.ObjectNameType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RecordType;
import com.gssamerica.mdm.services.SimpleValueType;
import com.gssamerica.mdm.services.TableType;
import com.gssamerica.mdm.services.UnifiedFieldType;
import com.gssamerica.mdm.services.UnifiedValueType;
import com.gssamerica.mdm.services.ValueType;
import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.queries.MDMQueryResult;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;
import com.gssamerica.mdm.utils.MDMUtils;

public class MDMSearchQueryExecution extends MDMQueryExecution{
	
    public MDMSearchQueryExecution(DataRepository dataRepository){
        //this.dataRepository = dataRepository;
        super(dataRepository);
    }
    
    @Override
    public ServiceReturnType execute(String query, List resultDefList) throws MDMRepositoryConnectionException, MDMDatabaseException, 
                                                                MDMRuntimeException {
        List executionStatusList = null;
        ServiceReturnType executionStatusArrayType = null;
        ExecutionStatusType[] executionStatusArray = null;
        MDMQueryResult column = null;
        int size = 0;
                        
        column = (MDMQueryResult)resultDefList.get(0);
        executionStatusList = executeQuery(query, column.getTableName(), resultDefList);
        if(executionStatusList!=null){
                size = executionStatusList.size();
                executionStatusArray = new ExecutionStatusType[size];
                executionStatusArray = (ExecutionStatusType[])executionStatusList.toArray(executionStatusArray);
        }
        else{
                executionStatusArray = new ExecutionStatusType[size];
        }
        System.out.println("Return Records - "+size);
        executionStatusArrayType = new ServiceReturnType();
        executionStatusArrayType.setExecutionStatus(executionStatusArray);
        return executionStatusArrayType;
    }

    private List executeQuery(String query, String table_Name, List resultDefList) throws MDMRepositoryConnectionException, MDMDatabaseException, 
                                                         MDMRuntimeException {
        List executionStatusList = null;
        ExecutionStatusType executionStatus = null;
        Connection connection = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;
        DataElementType[] dataElementArray = null;
        ObjectNameType tableName = null;
        RecordIdentifierType identifierType = null;
        TableType table = null;
        List resultData = new ArrayList();
        MDMDatabaseException databaseException = null;
                                
        int updateCount = 0, columnCount = 0;
        boolean result = false;
        
        connection = getGlobalConnection();
        try {
            preparestmt=connection.prepareStatement(query);
            result = preparestmt.execute();
            executionStatusList = new ArrayList();
            if(result){
                resultSet = preparestmt.getResultSet();
                metaData = resultSet.getMetaData();
                columnCount = metaData.getColumnCount();
                while(resultSet.next()){
                    resultData.clear();
                    executionStatus = new ExecutionStatusType();
                    identifierType = new RecordIdentifierType();
                    table = new TableType();
                    tableName = new ObjectNameType();
                    
                    for(int i=1;i<=columnCount;i++){
                            resultData.add(resultSet.getString(i));
                    }
                    tableName.setLanguage(MDMConstants.MDM_DEFAULT_LANG);
                    tableName.setName(table_Name);
                    dataElementArray = getFormattedResult(resultDefList, resultData);
                    table.setTableName(tableName);
                    table.setCode(table_Name);
                    identifierType.setTable(table);
                    identifierType.setUnifiedFields(dataElementArray);
                    executionStatus.setDataObjectId(identifierType);
                    executionStatus.setStatus(MDMConstants.MDM_SUCCESS_STATUS);
                    executionStatusList.add(executionStatus);
                }                    
            }
            else{
                updateCount = preparestmt.getUpdateCount();
                executionStatus = new ExecutionStatusType();
                identifierType = new RecordIdentifierType();
                executionStatus.setReturnValue(""+updateCount);
                executionStatus.setStatus(MDMConstants.MDM_SUCCESS_STATUS);
                executionStatusList.add(executionStatus);
            }
                
        } catch (SQLException e) {
            e.printStackTrace();
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while Executing Search Query":e.getMessage());
            databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(connection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setResultSet(resultSet+"");
            databaseException.setQuery(query);
            throw (databaseException);
        }
        finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while closing the Database Resources":e.getMessage());
                databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
                databaseException.setOriginalErrorCode(""+e.getErrorCode());
                databaseException.setSqlState(e.getSQLState());
                databaseException.setConnection(connection+"");
                databaseException.setStatement(preparestmt+"");
                databaseException.setQuery(query);
                databaseException.setSeverity(MDMConstants.SEVERITY_LOW);
                // not throwing
            }
        }
        return executionStatusList;
    }
    
    private DataElementType[] getFormattedResult(List resultDefinition, List resultData) throws MDMRuntimeException {
            
        FieldType field = null;
        UnifiedFieldType fieldType = null;
        ObjectNameType fieldtName = null;
        SimpleValueType simpleValueType = null;
        UnifiedValueType valueType = null;
        ValueType value = null;
        List dataElementsList = null;
        DataElementType element = null;
        LookupValueType lookupElement = null;
        String dbValue = null;
        MDMQueryResult columnDef = null;
        DataElementType[] dataElementTypeArray = null;
        
        dataElementsList = new ArrayList();
        for(int i=0;i<resultData.size();i++){
            dbValue = (String)resultData.get(i);
            columnDef = (MDMQueryResult)resultDefinition.get(i);
            if(!columnDef.isForeignReference()){
                element = new DataElementType();
                field = new FieldType();
                fieldType = new UnifiedFieldType();
                fieldtName = new ObjectNameType();
                field.setDataType(columnDef.getDataType());
                fieldtName.setLanguage(MDMConstants.MDM_DEFAULT_LANG);
                fieldtName.setName(columnDef.getColumnName());
                fieldType.setFieldName(fieldtName);
                fieldType.setCode(columnDef.getColumnName());
                field.setUnifiedField(fieldType);
                element.setField(field);
                
                if(columnDef.getForeignColumnIndexes().size()==0){
                        simpleValueType = new SimpleValueType();
                        valueType = new UnifiedValueType();
                        value = new ValueType();
                        
                        value.setLanguage(MDMConstants.MDM_DEFAULT_LANG);
                        if(dbValue!=null && columnDef.getDataType().equalsIgnoreCase(MDMConstants.MDM_DATE.getTypeName())){
                            String[] dateStrings = dbValue.split(" ");
                            if(dateStrings!=null){
                                dbValue = dateStrings[0];
                            }
                            dbValue = MDMUtils.getDateString(MDMUtils.getDateFromString(dbValue,MDMConstants.DATE_FORMAT_FETCH_JDBC_MDM_HUB),MDMConstants.DATE_FORMAT_EXTERNAL);
                            System.out.println("["+this.getClass()+"] Date Format converted - "+dbValue);
                        }
                        value.setValue(dbValue);
                        valueType.setValue(value);
                        simpleValueType.setUnifiedValue(valueType);
                        element.setSimpleValue(simpleValueType);
                }
                else{
                        lookupElement = createLookup(dbValue, resultDefinition, resultData, columnDef.getForeignColumnIndexes());
                        element.setLookupValue(lookupElement);
                }
                dataElementsList.add(element);
            }
        }
        dataElementTypeArray = new DataElementType[dataElementsList.size()];
        dataElementTypeArray = (DataElementType[])dataElementsList.toArray(dataElementTypeArray);
        return dataElementTypeArray;
    }
    
    private LookupValueType createLookup(String linkId, List resultDefinition, List resultData, List foreignColumIndexesList) throws MDMRuntimeException {
        LookupValueType mainlookupElement = null;
        LookupValueType lookupElement = null;
        RecordType record = null;
        List dataElementTypeList = null;
        DataElementType[] dataElementTypeArray = null;
        DataElementType dataElement = null;
        RecordIdentifierType recordIdentifier = null;
        TableType tableType = null;
        ObjectNameType nameType = null;
        MDMQueryResult columnDef = null;
        String dbValue = null;
        String tableName = null;
        String tableCode = null;
        FieldType fieldType = null;
        UnifiedFieldType unifiedFieldType = null;
        SimpleValueType simpleValue = null;
        UnifiedValueType unifiedValue = null;
        ValueType value = null;
        
        try {
            mainlookupElement = new LookupValueType();
            record = new RecordType();
            recordIdentifier = new RecordIdentifierType();
            tableType = new TableType();
            dataElementTypeList = new ArrayList();
            
            
            for(int i=0;i<foreignColumIndexesList.size();i++){
                dbValue = (String)resultData.get((Integer)foreignColumIndexesList.get(i));
                columnDef = (MDMQueryResult)resultDefinition.get((Integer)foreignColumIndexesList.get(i));
                tableName = columnDef.getTableName();
                tableCode = columnDef.getTableCode();
                dataElement = new DataElementType();
                fieldType = new FieldType();
                unifiedFieldType = new UnifiedFieldType();
                nameType = new ObjectNameType();
                
                fieldType.setDataType(columnDef.getDataType());
                unifiedFieldType.setCode(columnDef.getColumnCode());
                nameType.setLanguage(MDMConstants.MDM_DEFAULT_LANG);
                nameType.setName(columnDef.getColumnName());
                unifiedFieldType.setFieldName(nameType);
                unifiedFieldType.setCode(columnDef.getColumnName());
                fieldType.setUnifiedField(unifiedFieldType);
                dataElement.setField(fieldType);
                if(columnDef.getForeignColumnIndexes().size()==0){
                    //simpleValue
                    simpleValue = new SimpleValueType();
                    unifiedValue = new UnifiedValueType();
                    value = new ValueType();
                    value.setLanguage(MDMConstants.MDM_DEFAULT_LANG);
                    value.setValue(dbValue);
                    unifiedValue.setValue(value);
                    simpleValue.setUnifiedValue(unifiedValue);
                    dataElement.setSimpleValue(simpleValue);
                }
                else{
                    // lookupElement
                    lookupElement = createLookup(dbValue, resultDefinition, resultData,columnDef.getForeignColumnIndexes());
                    dataElement.setLookupValue(lookupElement);
                }
                dataElementTypeList.add(dataElement);
            }
            nameType = new ObjectNameType();
            nameType.setLanguage(MDMConstants.MDM_DEFAULT_LANG);
            nameType.setName(tableName);
            tableType.setCode(tableCode);
            tableType.setTableName(nameType);
            recordIdentifier.setTable(tableType);
            mainlookupElement.setLinkId(linkId);
            dataElementTypeArray = new DataElementType[dataElementTypeList.size()];
            record.setDataElement((DataElementType[])dataElementTypeList.toArray(dataElementTypeArray));
            record.setRecordIdentifier(recordIdentifier);
            mainlookupElement.setRecord(record);
        } catch (Exception e) {
            e.printStackTrace();
            
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Runtime Exception While Creating Lookup DataElements for Search Result":e.getMessage());
            MDMRuntimeException runtimeException = new MDMRuntimeException(message, e.getCause(),e.getStackTrace());
            throw (runtimeException);
        }
        return mainlookupElement;
    }

    @Override
    public String execute(String tableName, Column primaryKey, MDMQuery query) throws MDMDatabaseException, MDMRepositoryConnectionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List execute(String query) {
        return Collections.EMPTY_LIST;
    }

    public void execute(List queriesList) {}
}
