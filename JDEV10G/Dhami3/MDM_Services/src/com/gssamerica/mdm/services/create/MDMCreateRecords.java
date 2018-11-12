package com.gssamerica.mdm.services.create;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.CreateRecordRequestType;
import com.gssamerica.mdm.services.ServiceReturnType;

import java.util.ArrayList;
import java.util.List;

import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.queries.execution.MDMInsertQueryExecution;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;
import com.gssamerica.mdm.services.DataElementType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.ConfigurationDataType;
import com.gssamerica.mdm.services.RepositoryInformationType;
import com.gssamerica.mdm.services.RecordType;

import com.gssamerica.mdm.services.ServiceUtils;

import com.gssamerica.mdm.services.log.MDMServiceLog;

import java.util.Hashtable;

public class MDMCreateRecords {
    public ServiceReturnType createRecords(CreateRecordRequestType createRecordsRequest) {
        RecordType[] records = null;
        RecordType record = null;
        RepositoryInformationType repositoryInformation = null;
        Hashtable configDataTable = null;
        DataElementType[] dataElements = null;
        RecordIdentifierType recordIdentifier = null;
        ServiceReturnType statusArray = new ServiceReturnType();
        List executionStatusList = null;
        ExecutionStatusType executionStatus = null;
        ExecutionStatusType[] executionStatusArray = null;
        MDMSave mdmSave = null;
        DataRepository dataRepository = null;
        MDMQueryExecution queryExecution = null;
        String transactionID = null;
        String remoteSystem = null;
        String processId = null;
        
        executionStatusList = new ArrayList();
        records = createRecordsRequest.getRecord();
        repositoryInformation = createRecordsRequest.getRepositoryInfo();
        configDataTable = ServiceUtils.getConfigHashtable(createRecordsRequest.getConfigData());
        dataRepository = new DataRepository(repositoryInformation);
        queryExecution = new MDMInsertQueryExecution(dataRepository);
        queryExecution.setConfigData(configDataTable); 
        mdmSave = new MDMSave(queryExecution, configDataTable);
        transactionID = (String)configDataTable.get(MDMConstants.TransactionID);
        remoteSystem = (String)configDataTable.get(MDMConstants.RemoteSystem);
        processId = Thread.currentThread().getId()+"";
        MDMServiceLog.log(transactionID, "MDMCreateRecords", processId, MDMConstants.PROCESS_CREATERECORD_SERVICE, "MDMCreateRecords Started", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_PROGRESS, "");
        for (int i = 0; i < records.length; i++) {
            record = records[i];
            System.out.println("Record  - "+(i+1));
            dataElements = record.getDataElement();
            recordIdentifier = record.getRecordIdentifier();
            executionStatus = mdmSave.saveRecord(dataElements, recordIdentifier);
            executionStatusList.add(executionStatus);
        }
        queryExecution.closeExecution();
        executionStatusArray = new ExecutionStatusType[executionStatusList.size()];
        executionStatusArray = (ExecutionStatusType[])executionStatusList.toArray(executionStatusArray);
        statusArray.setExecutionStatus(executionStatusArray);
        MDMServiceLog.log(transactionID, "MDMCreateRecords", processId, MDMConstants.PROCESS_CREATERECORD_SERVICE, "MDMCreateRecords Returned", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_COMPLETE, "");
        System.out.println("Create Returned");
        return statusArray;
    }
}
