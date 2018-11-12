package com.gssamerica.mdm.services.search;

import com.gssamerica.mdm.services.DataElementType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.KeyType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RemoteKeyType;
import com.gssamerica.mdm.services.db.DatabaseDefinition;

import com.gssamerica.mdm.services.log.MDMServiceLog;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;
import com.gssamerica.mdm.constants.MDMConstants;

import com.gssamerica.mdm.services.FaultType;
import com.gssamerica.mdm.services.QueryType;

import com.gssamerica.mdm.services.RecordCriteriaType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.services.exceptions.MDMExceptionLogBean;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.search.criterias.SearchCriteria;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MDMSearch {
    private MDMQueryExecution mdmQueryExecution = null;
    private Hashtable configData = null;
    
    public MDMSearch(MDMQueryExecution mdmQueryExecution, Hashtable configData) {
        this.mdmQueryExecution = mdmQueryExecution;
        this.configData = configData;
    }
    
    public ServiceReturnType search(QueryType queryType){
        SearchCriteria searchCriteria = null;
        RecordCriteriaType recordCriteria = null;
        MDMQuery searchQuery = null;
        MDMExceptionLogBean exceptionBean = null;
        String query = null;
        String transactionId = null;
        String source = null;
        List queryResult = null;
        ServiceReturnType executionStatusArray = null;
        try {
            transactionId = (String)configData.get(MDMConstants.TransactionID);
            source = (String)configData.get(MDMConstants.RemoteSystem);
            recordCriteria = queryType.getRecordCriteria();
            searchCriteria = SearchCriteria.getInstance(recordCriteria);
            searchQuery = searchCriteria.createSearchQuery();
            searchQuery.setConfigData(this.configData);
            query = searchQuery.getQuery();
            queryResult = searchQuery.getQueyResult();
            //System.out.println("Result Query - " + query);
            executionStatusArray = mdmQueryExecution.execute(query, queryResult);
            executionStatusArray = searchMDMRecord(executionStatusArray);
            mdmQueryExecution.commitExecution();
            return executionStatusArray;
        } catch (MDMException e) {
            e.printStackTrace();
            
//            executionStatusArray = new ServiceReturnType();
//            ExecutionStatusType[] executionStatusTypes = new ExecutionStatusType[1];
//            ExecutionStatusType executionStatusType = new ExecutionStatusType();
//            executionStatusType.setStatus(MDMConstants.MDM_FAILURE_STATUS);
//            FaultType fault = new FaultType();
//            fault.setFaultId(e.getErrorId());
//            fault.setFaultText(e.getMessage());
//            fault.setSeverity(e.getSeverity());
//            executionStatusType.setFault(fault);
//            executionStatusTypes[0] = executionStatusType;
//            executionStatusArray.setExecutionStatus(executionStatusTypes);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionId);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_SEARCHRECORD_SERVICE);
            exceptionBean.setSource(source);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(e);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean); 
            
        } catch(Exception e){
            e.printStackTrace();
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Runtime Exception in MDM Save":e.getMessage());
            MDMRuntimeException runtimeException = new MDMRuntimeException(message, e.getCause(),e.getStackTrace());
            
//            executionStatusArray = new ServiceReturnType();
//            ExecutionStatusType[] executionStatusTypes = new ExecutionStatusType[1];
//            ExecutionStatusType executionStatusType = new ExecutionStatusType();
//            executionStatusType.setStatus(MDMConstants.MDM_FAILURE_STATUS);
//            FaultType fault = new FaultType();
//            fault.setFaultId(runtimeException.getErrorId());
//            fault.setFaultText(runtimeException.getMessage());
//            fault.setSeverity(runtimeException.getSeverity());
//            executionStatusType.setFault(fault);
//            executionStatusTypes[0] = executionStatusType;
//            executionStatusArray.setExecutionStatus(executionStatusTypes);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionId);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_SEARCHRECORD_SERVICE);
            exceptionBean.setSource(source);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(runtimeException);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean);
        }
        ServiceUtils.rollback(mdmQueryExecution, MDMConstants.PROCESS_SEARCHRECORD_SERVICE, transactionId, source);
        return executionStatusArray;
    }
    
    private ServiceReturnType searchMDMRecord(ServiceReturnType serviceReturnType) throws MDMXMLException, 
                                                                                         MDMRepositoryConnectionException, 
                                                                                         MDMDatabaseException {
        List<String> entityIdList = null;
        List<String> tablesList = null;
        ExecutionStatusType executionStatusType = null;
        ExecutionStatusType[] executionStatusTypeArray = null;
        DataElementType[] dataElements = null;
        DataElementType dataElement = null;
        MDMRecordsBean mdmRecordsBean = null;
        RecordIdentifierType recordIdentifierType = null;
        RemoteKeyType[] remoteKeys = null;
        RemoteKeyType remoteKey = null;
        SourceRecordsBean sourceRecordsBean = null;
        KeyType keyType = null;
        String tableName = null;
        String primaryKey = null;
        if(serviceReturnType.getExecutionStatus()!=null && serviceReturnType.getExecutionStatus().length!=0){
            entityIdList = new ArrayList<String>();
            tablesList = new ArrayList<String>();
            for(int i=0;i<serviceReturnType.getExecutionStatus().length;i++){
                executionStatusType = serviceReturnType.getExecutionStatus()[i];
                tableName = executionStatusType.getDataObjectId().getTable().getTableName().getName();
                tablesList.add(tableName);
                primaryKey = DatabaseDefinition.getInstance().getTablePrimaryKey(tableName).getName();
                dataElements = executionStatusType.getDataObjectId().getUnifiedFields();
                for(int j=0;j<dataElements.length;j++){
                    dataElement = dataElements[j];
                    if(dataElement.getField().getUnifiedField().getFieldName().getName().equalsIgnoreCase(primaryKey)){
                        entityIdList.add(dataElement.getSimpleValue().getUnifiedValue().getValue().getValue());
                        break;
                    }
                }
            }
            mdmRecordsBean = new MDMRecordsBean(tablesList, entityIdList);
            mdmRecordsBean = mdmQueryExecution.searchMDMRecord(mdmRecordsBean);
            executionStatusTypeArray = serviceReturnType.getExecutionStatus();
            for(int i=0;i<serviceReturnType.getExecutionStatus().length;i++){
               executionStatusType = executionStatusTypeArray[i];
               recordIdentifierType = executionStatusType.getDataObjectId();
                recordIdentifierType.setRecordId(mdmRecordsBean.getRecordID().get(i));
                recordIdentifierType.setAutoId(mdmRecordsBean.getEntityID().get(i));
                
                if(mdmRecordsBean.getSourceRecords()!=null){
                    remoteKeys = new RemoteKeyType[mdmRecordsBean.getSourceRecords().getSize()];
                    for(int j=0;j<mdmRecordsBean.getSourceRecords().getSize();j++){
                        remoteKey = new RemoteKeyType();
                        keyType = new KeyType();
                        keyType.setIsDefault("false");
                        keyType.setValue(mdmRecordsBean.getSourceRecords().getSourceKey().get(j));
                        remoteKey.setKey(keyType);
                        remoteKey.setRemoteSystem(mdmRecordsBean.getSourceRecords().getSourceID().get(j));
                        remoteKeys[j] = remoteKey;
                    }
                    recordIdentifierType.setRemoteKey(remoteKeys);
                }
                executionStatusType.setDataObjectId(recordIdentifierType);
                executionStatusTypeArray[i] = executionStatusType;
            }
            serviceReturnType.setExecutionStatus(executionStatusTypeArray);
        } 
        return serviceReturnType;
    }
    
}
