package com.gssamerica.mdm.services.delete;


import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.DataElementType;
import com.gssamerica.mdm.services.DelRemoteKeyRequestType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.FaultType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RemoteKeyType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.db.DatabaseDefinition;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMException;
import com.gssamerica.mdm.services.exceptions.MDMExceptionLogBean;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.services.log.MDMServiceLog;
import com.gssamerica.mdm.services.queries.MDMDeleteQuery;
import com.gssamerica.mdm.services.queries.MDMQuery;

import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class MDMDelete {
    private MDMQueryExecution queryExecution = null;
    private List queryExecutionResult = null;
    private MDMQuery deleteQuery = null;
    private Hashtable configData = null;

    public MDMDelete(MDMQueryExecution queryExecution, Hashtable configData) {
        this.queryExecution = queryExecution;
        this.configData = configData;
        deleteQuery = new MDMDeleteQuery();
        deleteQuery.setConfigData(this.configData);
    }

    public ExecutionStatusType deleteRecordData(RecordIdentifierType recordIdentifier) {
        String tableName = null;
        ExecutionStatusType executionStatus = null;
        String recordId = null;
        String autoID = null;
        String transactionID = null;
        String remoteSystem = null;
        MDMExceptionLogBean exceptionBean = null;
        try {
            executionStatus = new ExecutionStatusType();
            queryExecutionResult = new ArrayList();
            tableName = recordIdentifier.getTable().getTableName().getName();
            recordId = recordIdentifier.getRecordId();
            if(recordId==null || recordId.equalsIgnoreCase("")){
                throw (new Exception("[MDMDelete] RECORDID NULL in Request"));
            }
            transactionID = (String)configData.get(MDMConstants.TransactionID);
            remoteSystem = (String)configData.get(MDMConstants.RemoteSystem);
            autoID = queryExecution.getAutoID(recordId);
            deleteMasterRecord(autoID, recordId);
            System.out.println("[MDMDelete] The auto id in deleteRecordData " + autoID);
            deleteRecord(tableName, autoID);
            queryExecution.execute(queryExecutionResult);
            queryExecutionResult = null;
            //recordIdentifier.setUnifiedFields(dataElements);
            recordIdentifier.setRecordId(recordId);
            executionStatus.setStatus(MDMConstants.MDM_SUCCESS_STATUS);
            executionStatus.setDataObjectId(recordIdentifier);
            queryExecution.commitExecution();
            return executionStatus;
        } catch (MDMException e) {
            e.printStackTrace();
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(e.getErrorId());
            fault.setFaultText(e.getMessage());
            fault.setSeverity(e.getSeverity());
            executionStatus.setFault(fault);

            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId("" + Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_DELETERECORD_SERVICE);
            exceptionBean.setSource(remoteSystem);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(e);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean);
        } catch (Exception e) {
            MDMRuntimeException runtimeException = new MDMRuntimeException("Runtime Exception in MDM Delete: " + e.getMessage(), e.getCause(), e.getStackTrace());

            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(runtimeException.getErrorId());
            fault.setFaultText(runtimeException.getMessage());
            fault.setSeverity(runtimeException.getSeverity());
            executionStatus.setFault(fault);
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId("" + Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_DELETERECORD_SERVICE);
            exceptionBean.setSource(remoteSystem);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(runtimeException);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean);
        }
        ServiceUtils.rollback(queryExecution, MDMConstants.PROCESS_DELETERECORD_SERVICE, transactionID, remoteSystem);
        return executionStatus;
    }

    public ExecutionStatusType deleteRecordMapping(RecordIdentifierType recordIdentifier) {
        String srcSystemID = null;
        String recordId = null;
        ExecutionStatusType executionStatus = null;
        String transactionID = null;
        String remoteSystem = null;
        MDMExceptionLogBean exceptionBean = null;
        RemoteKeyType[] remoteKeys = null;
        RemoteKeyType remoteKey = null;
        String sourceId=null;
        try {
            recordIdentifier.getRemoteKey();
            executionStatus = new ExecutionStatusType();
            remoteKeys = recordIdentifier.getRemoteKey();
            for (int i = 0; i < remoteKeys.length; i++) {
                remoteKey = remoteKeys[i];
                if ("true".equalsIgnoreCase(remoteKey.getKey().getIsDefault())) {
                    srcSystemID = remoteKey.getRemoteSystem();
                    recordId = recordIdentifier.getRecordId();
                    //System.out.println("the source system id is {MDMDelete}" + srcSystemID);
                    //System.out.println("the record id is {MDMDelete}" + recordId);
                    transactionID = (String)configData.get(MDMConstants.TransactionID);
                    remoteSystem = (String)configData.get(MDMConstants.RemoteSystem);
                    sourceId = getSourceID(srcSystemID);
                    if (sourceId != null && !("".equalsIgnoreCase(sourceId))) {
                        deleteMDMMaster(sourceId, recordId);
                    }
                }
            }
            executionStatus.setStatus(MDMConstants.MDM_SUCCESS_STATUS);
            executionStatus.setDataObjectId(recordIdentifier);
            queryExecution.commitExecution();
            return executionStatus;
        } catch (MDMException e) {
            e.printStackTrace();
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(e.getErrorId());
            fault.setFaultText(e.getMessage());
            fault.setSeverity(e.getSeverity());
            executionStatus.setFault(fault);

            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId("" + Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_DELETERECORD_SERVICE);
            exceptionBean.setSource(remoteSystem);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(e);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean);
        } catch (Exception e) {
            e.printStackTrace();
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            MDMRuntimeException runtimeException = 
                new MDMRuntimeException("Runtime Exception in MDM Delete: " + 
                                        e.getMessage(), e.getCause(), 
                                        e.getStackTrace());
            FaultType fault = new FaultType();
            fault.setFaultId(runtimeException.getErrorId());
            fault.setFaultText(runtimeException.getMessage());
            fault.setSeverity(runtimeException.getSeverity());
            executionStatus.setFault(fault);
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId("" + Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_DELETERECORD_SERVICE);
            exceptionBean.setSource(remoteSystem);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(runtimeException);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean);
        }
        //System.out.println("Table - "+executionStatus.getDataObjectId().getTable().getTableName().getName());
        //justPrint(executionStatus.getDataObjectId().getUnifiedFields());
        ServiceUtils.rollback(queryExecution, 
                              MDMConstants.PROCESS_DELETERECORD_SERVICE, 
                              transactionID, remoteSystem);
        return executionStatus;
    }

    private void deleteRecord(String tableName, 
                              String autoID) throws MDMDatabaseException, 
                                                      MDMRepositoryConnectionException, 
                                                      MDMXMLException, 
                                                      MDMQueryFormationException, 
                                                      MDMRuntimeException {
       
        List foreignTableNames = null;
        List foriegnKeyNames = null;
        List foreignRecordIds = null;
        String primaryKeyName = null;        
        String primaryKeyDataType = null;
        String deleteQueryString = null;
        Column[] columns=null;
        primaryKeyName = DatabaseDefinition.getInstance().getTablePrimaryKey(tableName).getName();
        primaryKeyDataType = DatabaseDefinition.getInstance().getTablePrimaryKey(tableName).getType().toString();
        deleteQuery.addQueryItem(tableName,primaryKeyDataType,primaryKeyName,autoID);
        deleteQueryString = deleteQuery.getQuery();
        System.out.println("the query formed is {MDMDelete}" + 
                           deleteQuery.getQuery());
        queryExecutionResult.add(deleteQueryString);
        columns= DatabaseDefinition.getInstance().getTableByName(tableName).getColumn();
        
        foreignTableNames = new ArrayList();
        foriegnKeyNames = new ArrayList();
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].getForeignKey() != null) {
                foreignTableNames.add(columns[i].getForeignKey().getReferenceTable().getName());
                foriegnKeyNames.add(columns[i].getName());
                foreignRecordIds =getForienKeyValues(tableName, autoID, primaryKeyName, foriegnKeyNames, primaryKeyDataType);
            }
        }
        for(int i=0;i<foreignTableNames.size();i++){
            String foreignTableName = (String)foreignTableNames.get(i);
            String foreignKeyId = (String)foreignRecordIds.get(i);
            if(foreignKeyId!=null && !foreignKeyId.equalsIgnoreCase("")){
                deleteRecord(foreignTableName, foreignKeyId);
            }
        }
    }
    
    private List getForienKeyValues(String tableName, String autoID, 
                                     String columnName, List foriegnKeyNames, 
                                     String dataType) throws MDMRepositoryConnectionException, 
                                                             MDMDatabaseException, 
                                                             MDMRuntimeException {

        List foriegnKeyIds = null;
        String foriegnKeyName = null;
        String selectQuery = "";
        List queryResult = null;
        List resultRow = null;
       
        selectQuery = "select ";
        
        for(int i=0;i<foriegnKeyNames.size();i++){
            foriegnKeyName = (String)foriegnKeyNames.get(i);
            selectQuery = selectQuery + foriegnKeyName;
            if(i!=(foriegnKeyNames.size()-1)){
                selectQuery = selectQuery + ", ";
            }
        }
        selectQuery = selectQuery+" from " + tableName + " where ";
        if (dataType.equalsIgnoreCase(MDMConstants.MDM_STRING.getTypeName()) || 
            dataType.equalsIgnoreCase(MDMConstants.MDM_DATE.getTypeName()) || 
            dataType.equalsIgnoreCase(MDMConstants.MDM_CHAR.getTypeName())) {
            selectQuery = selectQuery + columnName + "='" + autoID + "'";
        } else {
            selectQuery = selectQuery + columnName + "=" + autoID;
        }
        
        queryResult=queryExecution.execute(selectQuery);
        if (queryResult.size()!=0)
            foriegnKeyIds = (List)queryResult.get(0);
    
        return foriegnKeyIds;
    }

    private String getSourceID(String sourcesystemid) throws MDMRepositoryConnectionException, 
                                                            MDMDatabaseException, 
                                                            MDMRuntimeException {

        String sourceId = null;
        int r = 0;
        String query = 
            "select sourceid from source where sourcesystemid='" + sourcesystemid + 
            "'";
        List a = queryExecution.execute(query);
        if (a.size() != 0)
            sourceId = (String)a.get(0);
        return sourceId;
    }

    private void deleteMDMMaster(String id, 
                                String rid) throws MDMRepositoryConnectionException, 
                                                   MDMDatabaseException, 
                                                   MDMRuntimeException {
        String queryFormation = "delete from "+MDMConstants.MDM_MASTER_TABLE+" where SOURCEID=" + id + " and RECORDID=" + rid + "";
        queryExecution.execute(queryFormation);
    }

    private void deleteMasterRecord(String autoID, 
                                   String recordID) throws MDMRepositoryConnectionException, 
                                                           MDMDatabaseException, 
                                                           MDMRuntimeException {
        String deleteQuery = "delete from "+MDMConstants.MDM_MASTER_TABLE+" where RECORDID=" + recordID;
        queryExecution.execute(deleteQuery);
        deleteQuery = "delete from "+MDMConstants.MDM_ENTITY_LINK_TABLE+" where "+MDMConstants.MDM_ENTITY_LINK_RECORD_COLUMN+"=" + recordID;
        queryExecution.execute(deleteQuery);
        deleteQuery = "delete from "+MDMConstants.MDM_CUSTOMER_TABLE+" where "+MDMConstants.MDM_CUSTOMER_PERSON_COLUMN+"=" + autoID;
        queryExecution.execute(deleteQuery);
        deleteQuery = "delete from "+MDMConstants.MDM_RECORD_TABLE+" where RECORDID=" + recordID;
        queryExecution.execute(deleteQuery);
    }
}


