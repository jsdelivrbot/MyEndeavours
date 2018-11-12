package com.gssamerica.mdm.services.replicatedata;

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

import java.util.Hashtable;
import java.util.List;

public class MDMReplicate {

    private MDMQueryExecution queryExecution = null;
    private Hashtable configData = null;
    
    public MDMReplicate(MDMQueryExecution queryExecution, Hashtable configData) {
        this.queryExecution = queryExecution;
        this.configData = configData;
    }
    
    public ExecutionStatusType replicate(RecordIdentifierType recordIdentifier){
        ExecutionStatusType executionStatus = null;
        String transactionID = null;
        String remoteSystem = null;
        RemoteKeyType[] remoteKeyArray = null;
        RemoteKeyType remoteKey = null;
        String recordId = null;
        String externalId = null;
        String sourceKey = null;
        String sourceSystem = null;
        MDMExceptionLogBean exceptionBean = null;
        
        try {
            executionStatus = new ExecutionStatusType();
            transactionID = (String)configData.get(MDMConstants.TransactionID);
            remoteSystem = (String)configData.get(MDMConstants.RemoteSystem);
            remoteKeyArray = recordIdentifier.getRemoteKey();
            recordId = recordIdentifier.getRecordId();
            externalId = recordIdentifier.getExternalId();
            if(remoteKeyArray!=null && remoteKeyArray.length!=0){
                for (int i = 0; i < remoteKeyArray.length; i++)  {
                    remoteKey = remoteKeyArray[i];
                    sourceKey = remoteKey.getKey().getValue();
                    sourceSystem = remoteKey.getRemoteSystem();
                    replicateMDMRecord(sourceSystem,sourceKey,recordId,externalId);
                }
            }
            executionStatus.setStatus(MDMConstants.MDM_SUCCESS_STATUS);
            executionStatus.setDataObjectId(recordIdentifier);
            queryExecution.commitExecution();
            return executionStatus;
        }
        catch (MDMException e) {
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
            exceptionBean.setProcessName(MDMConstants.PROCESS_REPLICATEDATA_SERVICE);
            exceptionBean.setSource(remoteSystem);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(e);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean); 
        }
        catch (Exception e) {
            e.printStackTrace();
            MDMRuntimeException runtimeException = new MDMRuntimeException("Runtime Exception in MDM Replicate: "+e.getMessage(), e.getCause(),e.getStackTrace());
            
            executionStatus.setStatus(MDMConstants.MDM_FAILURE_STATUS);
            FaultType fault = new FaultType();
            fault.setFaultId(runtimeException.getErrorId());
            fault.setFaultText(runtimeException.getMessage());
            fault.setSeverity(runtimeException.getSeverity());
            executionStatus.setFault(fault);
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId(""+Thread.currentThread().getId());
            exceptionBean.setProcessName(MDMConstants.PROCESS_REPLICATEDATA_SERVICE);
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
        ServiceUtils.rollback(queryExecution, MDMConstants.PROCESS_REPLICATEDATA_SERVICE, transactionID, remoteSystem);
        return executionStatus;
    }
    
    private int replicateMDMRecord(String remoteSystem, String sourceKey, String recordId, String externalId) throws MDMRepositoryConnectionException, 
                                                            MDMDatabaseException {
        List resultList = null;
        String mdmLocalID = null;
        
        resultList = queryExecution.execute("select MDMLOCALID from MDMLOCAL where LOCALKEY='"+sourceKey+"' AND SOURCESYSTEMID = '"+remoteSystem+"'");
        
        if(resultList!=null && resultList.size()!=0){
            mdmLocalID = (String)resultList.get(0);
            queryExecution.execute("update MDMLOCAL set MDMRECORDID='"+recordId+"', EXTERNALID='"+externalId+"' where MDMLOCALID="+mdmLocalID);
            return (1);
        }
        else{
            resultList = queryExecution.execute("select MDMLOCALSEQUENCE.NEXTVAL from dual");
            mdmLocalID = (String)resultList.get(0);
            queryExecution.execute("insert into MDMLOCAL (MDMLOCALID, LOCALKEY, SOURCESYSTEMID, MDMRECORDID, EXTERNALID) values ("+mdmLocalID+",'"+sourceKey+"','"+remoteSystem+"','"+recordId+"','"+externalId+"')");
            return (2);
        }
    }
}
