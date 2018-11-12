package com.gssamerica.mdm.services.create;


import com.gssamerica.mdm.services.DataElementType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.LookupValueType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RemoteKeyType;
import com.gssamerica.mdm.services.db.DatabaseDefinition;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.queries.MDMInsertQuery;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.FaultType;
import com.gssamerica.mdm.services.RepositoryInformationType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.exceptions.MDMException;
import com.gssamerica.mdm.services.exceptions.MDMExceptionLogBean;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;

import com.gssamerica.mdm.services.log.MDMServiceLog;

import java.util.Hashtable;

public class MDMSave {
	
	private MDMQueryExecution queryExecution = null;
        private Hashtable configData = null;
	
	public MDMSave(MDMQueryExecution queryExecution, Hashtable configData){
            this.queryExecution = queryExecution;
	    this.configData = configData;
	}
	
	public ExecutionStatusType saveRecord(DataElementType[] dataElements, RecordIdentifierType recordIdentifier){
            String tableName = null;
            RemoteKeyType[] remoteKeys = null;
	    MDMExceptionLogBean exceptionBean = null;
            ExecutionStatusType executionStatus = null;
            String entityId = null;
            String recordId = null;
	    String transactionId = null;
	    String source = null;
            try {
                executionStatus = new ExecutionStatusType();
                transactionId = (String)configData.get(MDMConstants.TransactionID);
                source = (String)configData.get(MDMConstants.RemoteSystem);
                tableName = recordIdentifier.getTable().getTableName().getName();
                remoteKeys = recordIdentifier.getRemoteKey();
                entityId = insertRecord(tableName,dataElements);
                recordId = insertMDMRecord(entityId, remoteKeys);
                executionStatus.setStatus(MDMConstants.MDM_SUCCESS_STATUS);
                //recordIdentifier.setUnifiedFields(dataElements);
                recordIdentifier.setAutoId(entityId);
                recordIdentifier.setRecordId(recordId);
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
                exceptionBean.setProcessName(MDMConstants.PROCESS_CREATERECORD_SERVICE);
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
                MDMRuntimeException runtimeException = new MDMRuntimeException("Runtime Exception in MDM Save: "+e.getMessage(), e.getCause(),e.getStackTrace());
                
                executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
                FaultType fault = new FaultType();
                fault.setFaultId(runtimeException.getErrorId());
                fault.setFaultText(runtimeException.getMessage());
                fault.setSeverity(runtimeException.getSeverity());
                executionStatus.setFault(fault);
                
                exceptionBean = new MDMExceptionLogBean();
                exceptionBean.setTransactionId(transactionId);
                exceptionBean.setProcessId(""+Thread.currentThread().getId());
                exceptionBean.setProcessName(MDMConstants.PROCESS_CREATERECORD_SERVICE);
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
        ServiceUtils.rollback(queryExecution, MDMConstants.PROCESS_CREATERECORD_SERVICE, transactionId, source);
        return executionStatus;
	}
	
	private String insertRecord(String tableName, DataElementType[] dataElements) throws MDMXMLException, 
                                                                       MDMRepositoryConnectionException, 
                                                                       MDMDatabaseException, 
                                                                       MDMQueryFormationException {
		
            DataElementType dataElement = null;
            LookupValueType lookupValue = null;
            Column primaryKey = null;
            String field = null;
            String dataType = null;
            String value = null;
            String lookupTableName = null;
            String returnId = null;
            MDMQuery insertQuery = null;
            
            insertQuery = new MDMInsertQuery(tableName);
	    insertQuery.setConfigData(this.configData);
            primaryKey = DatabaseDefinition.getInstance().getTablePrimaryKey(tableName);
            
            for(int i=0; i<dataElements.length;i++){
                    
                dataElement = dataElements[i];
                dataType = dataElement.getField().getDataType();
                field = dataElement.getField().getUnifiedField().getFieldName().getName();
                if(dataElement.getSimpleValue()!=null){
                    value = dataElement.getSimpleValue().getUnifiedValue().getValue().getValue();
                    insertQuery.addQueryItem(tableName,dataType,field,value);
                }
                else if(dataElement.getLookupValue()!=null){
                    lookupValue = dataElement.getLookupValue();
                    lookupTableName = lookupValue.getRecord().getRecordIdentifier().getTable().getTableName().getName();
                    value = insertRecord(lookupTableName,lookupValue.getRecord().getDataElement());
                    insertQuery.addQueryItem(tableName,dataType,field,value);
                }
                else{
                    System.out.println("[MDMSave.insertRecord] - ERROR");
                }
            }
            returnId = queryExecution.execute(tableName, primaryKey, insertQuery);
            //System.out.println("[insertRecord]: Return Id returned - "+returnId);
            return returnId;
	}
	
	private String insertMDMRecord(String entityId, RemoteKeyType[] remoteKeys) throws MDMRepositoryConnectionException, 
                                                                      MDMDatabaseException, 
                                                                      MDMRuntimeException {
	    String id = null;
            String srcID = null;
	    String srcKey = null;
	    RemoteKeyType remoteKey = null;
            
            if(remoteKeys!=null && remoteKeys.length!=0){
                
                for (int i = 0; i <remoteKeys.length; i++)  {
                    remoteKey = remoteKeys[i];
                    if("true".equalsIgnoreCase(remoteKey.getKey().getIsDefault())){
                        srcID = remoteKeys[i].getRemoteSystem();
                        srcKey = remoteKeys[i].getKey().getValue();
                        break;
                        //System.out.println("SrcID - "+srcID+" : SrcKey  - "+srcKey); 
                    }
                }
            }
	    if((srcID==null || "".equalsIgnoreCase(srcID)) || (srcKey==null || "".equalsIgnoreCase(srcKey))){
	        System.out.println("[MDMSave]ERROR: SOURCE OR SOURCEKEY not FOUND");
	        MDMRuntimeException runtimeException = new MDMRuntimeException("[MDMSave] Error in Input - SOURCE OR SOURCE_KEY MISSING");
	        runtimeException.setInputData("REMOTE_KEYS_ARRAY",remoteKeys+"");
                if(remoteKeys!=null){
                    runtimeException.setInputData("REMOTE_KEYS_ARRAY_LENGTH",remoteKeys.length+"");
                }
                runtimeException.setInputData("SRCID",srcID);
	        runtimeException.setInputData("SRC_KEY",srcKey);
                throw(runtimeException);
	    }
	    id = queryExecution.insertMDMRecord(entityId, srcID, srcKey); 
            return id;
	}
	
}
