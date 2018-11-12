package com.gssamerica.mdm.services.addremotekey;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.logger.MDMLogger;
import com.gssamerica.mdm.services.AddRemoteKeyRequestType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RepositoryInformationType;
import com.gssamerica.mdm.services.ServiceReturnType;

import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.db.DataRepository;

import com.gssamerica.mdm.services.log.MDMServiceLog;
import com.gssamerica.mdm.services.queries.execution.MDMAddKeyQueryExecution;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class AddRemoteKeyImpl {
    public ServiceReturnType addRemoteKey(AddRemoteKeyRequestType addRemoteKeyRequest) {
        RepositoryInformationType repositoryInformation = null;
        Hashtable configDataTable = null;
        RecordIdentifierType recordIdentifier = null;
        ServiceReturnType statusArray = new ServiceReturnType();
        List executionStatusList = null;
        ExecutionStatusType executionStatus = null;
        ExecutionStatusType[] executionStatusArray = null;
        MDMAddKey remoteKey = null;
        DataRepository dataRepository = null;
        MDMQueryExecution queryExecution = null;
        com.gssamerica.mdm.services.RecordIdentifierType[] recordIdentifierArray = null;
        String transactionID = null;
        String remoteSystem = null;
        String processId = null;
        
        System.out.println("AddRemoteKey Started");
        executionStatusList = new ArrayList();
        repositoryInformation = addRemoteKeyRequest.getRepositoryInfo();
        configDataTable = ServiceUtils.getConfigHashtable(addRemoteKeyRequest.getConfigData());
        dataRepository = new DataRepository(repositoryInformation);
        recordIdentifierArray= addRemoteKeyRequest.getRecordIdentifier();
        queryExecution = new MDMAddKeyQueryExecution(dataRepository);
        queryExecution.setConfigData(configDataTable);
        remoteKey = new MDMAddKey(queryExecution, configDataTable);
        transactionID = (String)configDataTable.get(MDMConstants.TransactionID);
        remoteSystem = (String)configDataTable.get(MDMConstants.RemoteSystem);
        processId = Thread.currentThread().getId()+"";
        MDMServiceLog.log(transactionID, "AddRemoteKeyImpl", processId, MDMConstants.PROCESS_ADDREMOTEKEY_SERVICES, "AddRemoteKey Started", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_PROGRESS,"");
        
        if(recordIdentifierArray!=null){
            for (int i = 0; i < recordIdentifierArray.length; i++) {
                recordIdentifier = recordIdentifierArray[i];
                executionStatus = remoteKey.addKey(recordIdentifier);
                executionStatusList.add(executionStatus);
            }
        }
        queryExecution.closeExecution();
        executionStatusArray = new ExecutionStatusType[executionStatusList.size()];
        executionStatusArray = (ExecutionStatusType[])executionStatusList.toArray(executionStatusArray);
        statusArray.setExecutionStatus(executionStatusArray);
        MDMServiceLog.log(transactionID, "AddRemoteKeyImpl", processId, MDMConstants.PROCESS_ADDREMOTEKEY_SERVICES, "AddRemoteKey Returned", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_COMPLETE, "");
        System.out.println("AddRemoteKey Returned");
        return statusArray;
    }
}
