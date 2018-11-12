package com.gssamerica.mdm.services.search;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.QueryType;
import com.gssamerica.mdm.services.SearchRecordRequestType;
import com.gssamerica.mdm.services.ServiceReturnType;

import java.util.Hashtable;

import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;
import com.gssamerica.mdm.services.queries.execution.MDMSearchQueryExecution;
import com.gssamerica.mdm.services.RepositoryInformationType;
import com.gssamerica.mdm.services.ServiceUtils;
import com.gssamerica.mdm.services.log.MDMServiceLog;

public class MDMSearchRecord {
    public ServiceReturnType lookupRecord(SearchRecordRequestType searchRecordRequest) {
        return null;
    }

    public ServiceReturnType searchRecord(SearchRecordRequestType searchRecordRequest) {
        RepositoryInformationType repositoryInfo = null;
        ServiceReturnType executionStatusArray = null;
        MDMQueryExecution queryExecution = null;
        DataRepository dataRepository = null;
        Hashtable configDataTable = null;
        MDMSearch mdmSearch = null;
        QueryType queryType = null;
        String transactionID = null;
        String remoteSystem = null;
        String processId = null;

        System.out.println("Search Start....");
        queryType = searchRecordRequest.getQuery();
        repositoryInfo = searchRecordRequest.getRepositoryInfo();
        configDataTable = ServiceUtils.getConfigHashtable(searchRecordRequest.getConfigData());
        dataRepository = new DataRepository(repositoryInfo);
        queryExecution = new MDMSearchQueryExecution(dataRepository);
        queryExecution.setConfigData(configDataTable);
        transactionID = (String)configDataTable.get(MDMConstants.TransactionID);
        remoteSystem = (String)configDataTable.get(MDMConstants.RemoteSystem);
        processId = Thread.currentThread().getId()+"";
        MDMServiceLog.log(transactionID, "MDMSearchRecord", processId, MDMConstants.PROCESS_SEARCHRECORD_SERVICE, "MDMSearchRecord Started", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_PROGRESS, "");
        
        mdmSearch = new MDMSearch(queryExecution,configDataTable);
        executionStatusArray = mdmSearch.search(queryType);   
        queryExecution.closeExecution();
        MDMServiceLog.log(transactionID, "MDMSearchRecord", processId, MDMConstants.PROCESS_SEARCHRECORD_SERVICE, "MDMSearchRecord Returned", MDMConstants.LOGLEVEL_INFO, MDMConstants.STATUS_COMPLETE, "");
        System.out.println("Search Returned");
        return executionStatusArray;
    }
}
