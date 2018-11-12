package com.gssamerica.mdm.services.replicatedata;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RecordType;
import com.gssamerica.mdm.services.ReplicateDataRequestType;
import com.gssamerica.mdm.services.RepositoryInformationType;
import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.db.DataRepository;

import com.gssamerica.mdm.services.log.MDMServiceLog;
import com.gssamerica.mdm.services.queries.execution.MDMInsertQueryExecution;

import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;

import com.gssamerica.mdm.services.queries.execution.MDMReplicateDataQueryExecution;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ReplicateDataImpl {
    public ServiceReturnType replicateData(ReplicateDataRequestType replicateDataRequest) {
        
        RecordType[] records = null;
        RecordType record = null;
        RepositoryInformationType repositoryInformation = null;
        Hashtable configDataTable = null;
        List executionStatusList = null;
        DataRepository dataRepository = null;
        MDMQueryExecution queryExecution = null;
        MDMReplicate replicate = null;
        RecordIdentifierType recordIdentifier = null;
        ExecutionStatusType executionStatus = null;
        ExecutionStatusType[] executionStatusArray = null;
        String transactionID = null;
        String remoteSystem = null;
        String processId = null;
        ServiceReturnType statusArray = new ServiceReturnType();
    
        System.out.println("ReplicateData Started");
        executionStatusList = new ArrayList();
        records = replicateDataRequest.getRecord();
        repositoryInformation = replicateDataRequest.getRepositoryInfo();
        configDataTable = ServiceUtils.getConfigHashtable(replicateDataRequest.getConfigData());
        dataRepository = new DataRepository(repositoryInformation,MDMConstants.MDM_LOCAL_USERNAME,MDMConstants.MDM_LOCAL_PASSWORD);
        queryExecution = new MDMReplicateDataQueryExecution(dataRepository);
        queryExecution.setConfigData(configDataTable);
        transactionID = (String)configDataTable.get(MDMConstants.TransactionID);
        remoteSystem = (String)configDataTable.get(MDMConstants.RemoteSystem);
        processId = Thread.currentThread().getId()+"";
        MDMServiceLog.log(transactionID, "ReplicateDataImpl", processId, MDMConstants.PROCESS_REPLICATEDATA_SERVICE, "MDMSearchRecord Started", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_PROGRESS, "");
        replicate = new MDMReplicate(queryExecution,configDataTable);
        for (int i = 0; i < records.length; i++) {
            record = records[i];
            System.out.println("Record  - "+(i+1));
            recordIdentifier = record.getRecordIdentifier();
            executionStatus = replicate.replicate(recordIdentifier);
            executionStatusList.add(executionStatus);
        }
        queryExecution.closeExecution();
        executionStatusArray = new ExecutionStatusType[executionStatusList.size()];
        executionStatusArray = (ExecutionStatusType[])executionStatusList.toArray(executionStatusArray);
        statusArray.setExecutionStatus(executionStatusArray);
        MDMServiceLog.log(transactionID, "ReplicateDataImpl", processId, MDMConstants.PROCESS_REPLICATEDATA_SERVICE, "MDMSearchRecord Returned", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_COMPLETE, "");
        System.out.println("ReplicateData Returned");
        return statusArray;
    }
}
