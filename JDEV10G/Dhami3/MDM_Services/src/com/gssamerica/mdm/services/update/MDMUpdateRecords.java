package com.gssamerica.mdm.services.update;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.exceptionlogger.MDMExceptionLogger;
import com.gssamerica.mdm.services.ConfigurationDataType;
import com.gssamerica.mdm.services.DataElementType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RecordType;
import com.gssamerica.mdm.services.RepositoryInformationType;
import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.UpdateRecordRequestType;
import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.log.MDMServiceLog;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;

import com.gssamerica.mdm.services.queries.execution.MDMUpdateQueryExecution;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

public class MDMUpdateRecords {
    public ServiceReturnType updateRecords(UpdateRecordRequestType updateRecordRequest) {
        RecordType[] records = null;
        RecordType record = null;
        RepositoryInformationType repositoryInformation = null;
        Hashtable configDataTable = null;
        ServiceReturnType statusArray = new ServiceReturnType();
        List executionStatusList = null;
        ExecutionStatusType executionStatus = null;
        ExecutionStatusType[] executionStatusArray = null;
        MDMUpdate mdmUpdate = null;       
        DataRepository dataRepository = null;
        MDMQueryExecution queryExecution = null;
        String transactionID = null;
        String remoteSystem = null;
        String processId = null;
        
        executionStatusList = new ArrayList();
        records = updateRecordRequest.getRecord();
        repositoryInformation = updateRecordRequest.getRepositoryInfo();
        configDataTable = ServiceUtils.getConfigHashtable(updateRecordRequest.getConfigData());
        dataRepository = new DataRepository(repositoryInformation);
        queryExecution = new MDMUpdateQueryExecution(dataRepository);
        queryExecution.setConfigData(configDataTable);
        mdmUpdate = new MDMUpdate(queryExecution,configDataTable);
        transactionID = (String)configDataTable.get(MDMConstants.TransactionID);
        remoteSystem = (String)configDataTable.get(MDMConstants.RemoteSystem);
        processId = Thread.currentThread().getId()+"";
        MDMServiceLog.log(transactionID, "MDMUpdateRecords", processId, MDMConstants.PROCESS_UPDATERECORD_SERVICES, "MDMUpdateRecords Started", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_PROGRESS, "");
        for (int i = 0; i < records.length; i++) {
            record = records[i];
            System.out.println("Record  - "+(i+1));
            executionStatus = mdmUpdate.updateRecordByRecordId(record);
            executionStatusList.add(executionStatus);
        }
        queryExecution.closeExecution();
        executionStatusArray = new ExecutionStatusType[executionStatusList.size()];
        executionStatusArray = (ExecutionStatusType[])executionStatusList.toArray(executionStatusArray);
        statusArray.setExecutionStatus(executionStatusArray);
        MDMServiceLog.log(transactionID, "MDMUpdateRecords", processId, MDMConstants.PROCESS_UPDATERECORD_SERVICES, "MDMUpdateRecords Returned", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_COMPLETE, "");
        System.out.println("Update Returned");
        return statusArray;
    }
}
