package com.gssamerica.mdm.services.delete;

import com.gssamerica.mdm.services.ConfigurationDataType;
import com.gssamerica.mdm.services.DelRemoteKeyRequestType;
import com.gssamerica.mdm.services.ExecutionStatusType;
import com.gssamerica.mdm.services.RecordIdentifierType;
import com.gssamerica.mdm.services.RecordType;
import com.gssamerica.mdm.services.RepositoryInformationType;
import com.gssamerica.mdm.services.ServiceReturnType;

import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.db.DataRepository;

import com.gssamerica.mdm.services.queries.execution.MDMDeleteQueryExecution;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class MDMDeleteRecordImpl {
    public ServiceReturnType deleteRecord(DeleteServiceType deleteRecordRequest) {
        DelRemoteKeyRequestType  delRemoteKeyRequestType=deleteRecordRequest.getDeleteRecordRequest();
        RepositoryInformationType repositoryInformation = null;
        ConfigurationDataType configurationElement = null;
        RecordIdentifierType recordIdentifier = null;
        ServiceReturnType statusArray = new ServiceReturnType();
        List executionStatusList = null;
        ExecutionStatusType executionStatus = null;
        ExecutionStatusType[] executionStatusArray = null;
        MDMDelete mdmDelete = null;       
        DataRepository dataRepository = null;
        MDMQueryExecution queryExecution = null;
        Hashtable configDataTable = null;
        RecordIdentifierType[] recordIdentifierArray = null;
        
        executionStatusList = new ArrayList();
        repositoryInformation = delRemoteKeyRequestType.getRepositoryInfo();
        configurationElement = delRemoteKeyRequestType.getConfigData();
        configDataTable = ServiceUtils.getConfigHashtable(delRemoteKeyRequestType.getConfigData());
        dataRepository = new DataRepository(repositoryInformation);
        queryExecution = new MDMDeleteQueryExecution(dataRepository);
        queryExecution.setConfigData(configDataTable);
        mdmDelete = new MDMDelete(queryExecution,configDataTable);
        recordIdentifierArray = delRemoteKeyRequestType.getRecordIdentifier();
        if (recordIdentifierArray!=null) {
            for (int i = 0; i <recordIdentifierArray.length; i++) {
                if(deleteRecordRequest.deleteType){
                    recordIdentifier = recordIdentifierArray[i];
                    System.out.println("[MDMDeleteRecordImpl] calling deleteRecordData for recordID - "+recordIdentifier.getRecordId());
                    executionStatus=mdmDelete.deleteRecordData(recordIdentifier);
                    executionStatusList.add(executionStatus);
                }else{
                    recordIdentifier = recordIdentifierArray[i];
                    System.out.println("[MDMDeleteRecordImpl] calling deleteRecordMapping for recordID - "+recordIdentifier.getRecordId());
                    executionStatus =mdmDelete.deleteRecordMapping(recordIdentifier);
                    executionStatusList.add(executionStatus); 
                }
            }
        }
        queryExecution.closeExecution();
        executionStatusArray = new ExecutionStatusType[executionStatusList.size()];
        executionStatusArray =(ExecutionStatusType[])executionStatusList.toArray(executionStatusArray);
        statusArray.setExecutionStatus(executionStatusArray);
      
        System.out.println("Delete Returned");
        return statusArray;
    }
}
