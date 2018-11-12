package com.gssamerica.mdm.services.update;

import com.gssamerica.mdm.services.DataElementType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.LookupValueType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.queries.MDMSearchLinkIdsQuery;
import com.gssamerica.mdm.services.queries.MDMUpdateQuery;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.FaultType;
import com.gssamerica.mdm.services.RecordType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.exceptions.MDMException;
import com.gssamerica.mdm.services.exceptions.MDMExceptionLogBean;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.services.log.MDMServiceLog;

import java.util.Hashtable;

public class MDMUpdate {
    private MDMQueryExecution queryExecution = null;
    private Hashtable configData = null;
    
    public MDMUpdate(MDMQueryExecution queryExecution, Hashtable configData) {
        this.queryExecution = queryExecution;
        this.configData = configData;
    }
    public ExecutionStatusType updateRecordByRecordId(RecordType record){
        ExecutionStatusType executionStatus = null;
        MDMExceptionLogBean exceptionBean = null;
        RecordIdentifierType recordIdentifier = null;
        String transactionId = null;
        String source = null;
        String recordID = null;
        String autoID = null;
        
        try {
            transactionId = (String)configData.get(MDMConstants.TransactionID);
            source = (String)configData.get(MDMConstants.RemoteSystem);
            recordIdentifier = record.getRecordIdentifier();
            recordID = recordIdentifier.getRecordId();
            if(recordID==null || recordID.equalsIgnoreCase("")){
                throw (new Exception("[MDMUpdate] RECORDID NULL in Request"));
            }
            autoID = queryExecution.getAutoID(recordID);
            recordIdentifier.setAutoId(autoID);
            record.setRecordIdentifier(recordIdentifier);
            executionStatus = updateRecordByAutoId(record);
        } catch (MDMException e) {
            e.printStackTrace();
            executionStatus = new ExecutionStatusType();
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(e.getErrorId());
            fault.setFaultText(e.getMessage());
            fault.setSeverity(e.getSeverity());
            executionStatus.setFault(fault);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionId);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_UPDATERECORD_SERVICES);
            exceptionBean.setSource(source);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(e);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
                        
            System.out.println("[MDMUpdate] MDM Exception logged - "+MDMServiceLog.exceptionLog(exceptionBean)); 
        } catch (Exception e) {
            e.printStackTrace();
            MDMRuntimeException runtimeException = new MDMRuntimeException("Runtime Exception in MDM Save: "+e.getMessage(), e.getCause(),e.getStackTrace());
            
            executionStatus = new ExecutionStatusType();
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(runtimeException.getErrorId());
            fault.setFaultText(runtimeException.getMessage());
            fault.setSeverity(runtimeException.getSeverity());
            executionStatus.setFault(fault);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionId);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_UPDATERECORD_SERVICES);
            exceptionBean.setSource(source);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(runtimeException);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            
            System.out.println("[MDMUpdate] MDM Runtime Exception logged - "+MDMServiceLog.exceptionLog(exceptionBean));
        }
        return executionStatus;
    }
    public ExecutionStatusType updateRecordByAutoId(RecordType record){
        
        ExecutionStatusType executionStatus = null;
        MDMExceptionLogBean exceptionBean = null;
        String tableName = null;
        DataElementType[] dataElements = null;
        RecordIdentifierType recordIdentifier = null;
        String transactionId = null;
        String source = null;
        try {
            executionStatus = new ExecutionStatusType();
            transactionId = (String)configData.get(MDMConstants.TransactionID);
            source = (String)configData.get(MDMConstants.RemoteSystem);
            record = prepareInput(record,record.getRecordIdentifier().getAutoId());
            dataElements = record.getDataElement();
            recordIdentifier = record.getRecordIdentifier();
            tableName = recordIdentifier.getTable().getTableName().getName();
            updateRecord(tableName, dataElements, recordIdentifier.getAutoId());
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
            exceptionBean.setTransactionId(transactionId);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_UPDATERECORD_SERVICES);
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
            
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(runtimeException.getErrorId());
            fault.setFaultText(runtimeException.getMessage());
            fault.setSeverity(runtimeException.getSeverity());
            executionStatus.setFault(fault);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionId);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_UPDATERECORD_SERVICES);
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
        ServiceUtils.rollback(queryExecution,MDMConstants.PROCESS_UPDATERECORD_SERVICES, transactionId, source);
        return executionStatus;
    }
    
    private String  updateRecord(String tableName, DataElementType[] dataElements, String autoID) throws MDMDatabaseException, MDMRepositoryConnectionException, MDMXMLException, MDMQueryFormationException {
        
        DataElementType dataElement = null;
        LookupValueType lookupValue = null;
        String field = null;
        String dataType = null;
        String value = null;
        String lookupTableName = null;
        String returnId = null;
        String lookupAutoId = null;
        MDMQuery updateQuery = null;
        updateQuery = new MDMUpdateQuery(tableName,autoID);
        updateQuery.setConfigData(this.configData);

        for (int i = 0; i < dataElements.length; i++) {

            dataElement = dataElements[i];
            dataType = dataElement.getField().getDataType();
            field = dataElement.getField().getUnifiedField().getFieldName().getName();
            if (dataElement.getSimpleValue() != null) {
                value = dataElement.getSimpleValue().getUnifiedValue().getValue().getValue();
                updateQuery.addQueryItem(tableName, dataType, field, value);
            } else if (dataElement.getLookupValue() != null) {
                System.out.println("in look up");
                lookupValue = dataElement.getLookupValue();
                lookupTableName = lookupValue.getRecord().getRecordIdentifier().getTable().getTableName().getName();
                lookupAutoId = lookupValue.getLinkId();
                updateRecord(lookupTableName, lookupValue.getRecord().getDataElement(), lookupAutoId);
            } else {
                System.out.println("[MDMSave.insertRecord] - ERROR");
            }
        }
        if(updateQuery.getQuery()!=null){
            System.out.println("Update query - " + updateQuery.getQuery());
            returnId = queryExecution.execute(tableName,null,updateQuery);   
        } 
     return returnId;
    }
    
    private RecordType prepareInput(RecordType record, String autoId) throws MDMXMLException, MDMQueryFormationException, 
                                                          MDMRepositoryConnectionException, 
                                                          MDMDatabaseException {
        DataElementType[] dataElements = null;
        DataElementType dataElement = null;
        RecordIdentifierType recordIdentifier = null;
        String tableName = record.getRecordIdentifier().getTable().getTableName().getName();
        String linkId = null;
        Hashtable linkIds = null;
        LookupValueType lookupValue = null;
        MDMQuery mdmQuery = null;
        System.out.println("[MDMUpdate] Preparing Input for Table - "+tableName+" | with Primary Key - "+autoId);
        mdmQuery = new MDMSearchLinkIdsQuery(tableName, autoId);
        mdmQuery.setConfigData(this.configData);
        linkIds = queryExecution.getLinkIDs(mdmQuery);
        System.out.println("[MDMUpdate] LinkIds for table "+tableName+" are "+linkIds.toString());
        dataElements = record.getDataElement();
        recordIdentifier = record.getRecordIdentifier();
        for (int i = 0; i < dataElements.length; i++)  {
            linkId = "";
            dataElement = dataElements[i];
            if(dataElement.getLookupValue()!=null){
                lookupValue = dataElement.getLookupValue();
                if(linkIds!=null && linkIds.containsKey(dataElement.getField().getUnifiedField().getFieldName().getName().toLowerCase())){
                    linkId = (String)linkIds.get(dataElement.getField().getUnifiedField().getFieldName().getName().toLowerCase());
                    lookupValue.setLinkId(linkId);
                    lookupValue.setRecord(prepareInput(lookupValue.getRecord(),linkId));
                    dataElement.setLookupValue(lookupValue);
                    dataElements[i] = dataElement;
                }
            }
        }
        record.setDataElement(dataElements);
        recordIdentifier.setAutoId(autoId);
        record.setRecordIdentifier(recordIdentifier);
        return record;
    }
}
