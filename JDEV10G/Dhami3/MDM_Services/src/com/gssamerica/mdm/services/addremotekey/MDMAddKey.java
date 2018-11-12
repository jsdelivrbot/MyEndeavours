package com.gssamerica.mdm.services.addremotekey;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.FaultType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RemoteKeyType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMException;
import com.gssamerica.mdm.services.exceptions.MDMExceptionLogBean;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;
import com.gssamerica.mdm.services.log.MDMServiceLog;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Hashtable;
import java.util.List;

public class MDMAddKey {

    private MDMQueryExecution queryExecution = null;
    private Hashtable configData = null;

    public MDMAddKey(MDMQueryExecution queryExecution, Hashtable configData) {
        this.queryExecution = queryExecution;
        this.configData = configData;
    }

    public ExecutionStatusType addKey(RecordIdentifierType recordIdentifier) {
    
        ExecutionStatusType executionStatus = null;
        String sourceId = null;
        String recordId = null;
        String sourceKey = null;
        String source = null;
        String transactionID = null;
        String remoteSystem = null;
        RemoteKeyType[] remoteKeyArray = null;
        RemoteKeyType remoteKey = null;
        MDMExceptionLogBean exceptionBean = null;
        
        try{
            executionStatus = new ExecutionStatusType();
            transactionID = (String)configData.get(MDMConstants.TransactionID);
            remoteSystem = (String)configData.get(MDMConstants.RemoteSystem);
            remoteKeyArray = recordIdentifier.getRemoteKey();
            recordId = recordIdentifier.getRecordId();
            if(remoteKeyArray!=null && remoteKeyArray.length!=0){
                for (int i = 0; i < remoteKeyArray.length; i++)  {
                    remoteKey = remoteKeyArray[i];
                    if ("true".equalsIgnoreCase(remoteKey.getKey().getIsDefault())) {
                        source = remoteKey.getRemoteSystem();
                        sourceKey = remoteKey.getKey().getValue();
                        sourceId = fetchSource(source);
                        updateMDMMaster(sourceId, sourceKey, recordId);
                    }
                }
            }
            executionStatus.setStatus(MDMConstants.MDM_SUCCESS_STATUS);
            executionStatus.setDataObjectId(recordIdentifier);
            queryExecution.commitExecution();
            return executionStatus;
        } catch(MDMException e){
            e.printStackTrace();
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(e.getErrorId());
            fault.setFaultText(e.getMessage());
            fault.setSeverity(e.getSeverity());
            executionStatus.setFault(fault);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_ADDREMOTEKEY_SERVICES);
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
            MDMRuntimeException runtimeException = new MDMRuntimeException("Runtime Exception in MDM AddKey: "+e.getMessage(), e.getCause(),e.getStackTrace());
            
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(runtimeException.getErrorId());
            fault.setFaultText(runtimeException.getMessage());
            fault.setSeverity(runtimeException.getSeverity());
            executionStatus.setFault(fault);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_ADDREMOTEKEY_SERVICES);
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
        ServiceUtils.rollback(queryExecution, MDMConstants.PROCESS_ADDREMOTEKEY_SERVICES, transactionID, remoteSystem);
        return executionStatus;
    }


    private String fetchSource(String source) throws MDMRepositoryConnectionException, 
                                                      MDMDatabaseException {

        String sourceID = null;
        List resultList = null;
        
        resultList = queryExecution.execute("select SOURCEID from "+MDMConstants.MDM_SOURCE_TABLE+" where SOURCESYSTEMID='"+source+"'");
        
        if(resultList!=null && resultList.size()!=0){
            sourceID = (String)resultList.get(0);
        }
        else{
            resultList = queryExecution.execute(MDMConstants.MDM_DB_SEQUENCE_QUERY);
            sourceID = (String)resultList.get(0);
            queryExecution.execute("insert into "+MDMConstants.MDM_SOURCE_TABLE+" (SOURCEID,SOURCESYSTEMID,SOURCENAME) values("+sourceID+",'"+source+"','"+source+"')");
        }
        return sourceID;
    }

    private int updateMDMMaster(String sourceId, String sourceKey, String recordId) throws MDMRepositoryConnectionException, 
                                                        MDMDatabaseException {
        
        List resultList = null;
        String mdmMasterID = null;
        
        resultList = queryExecution.execute("select "+MDMConstants.MDM_MASTER_PK_COLUMN+" from "+MDMConstants.MDM_MASTER_TABLE+" where SOURCEID="+sourceId+" and RECORDID="+recordId);
        
        if(resultList!=null && resultList.size()!=0){
            mdmMasterID = (String)resultList.get(0);
            queryExecution.execute("update "+MDMConstants.MDM_MASTER_TABLE+" set SOURCEKEY='"+sourceKey+"' where SOURCEID="+sourceId+" and RECORDID="+recordId);
            return (1);
        }
        else{
            resultList = queryExecution.execute(MDMConstants.MDM_DB_SEQUENCE_QUERY);
            mdmMasterID = (String)resultList.get(0);
            queryExecution.execute("insert into "+MDMConstants.MDM_MASTER_TABLE+" (MASTERID, RECORDID, SOURCEID, SOURCEKEY) values("+mdmMasterID+","+recordId+","+sourceId+",'"+sourceKey+"')");
            return (2);
        }
    }
}
