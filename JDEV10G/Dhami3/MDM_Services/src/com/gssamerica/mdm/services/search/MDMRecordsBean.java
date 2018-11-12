package com.gssamerica.mdm.services.search;

import java.util.ArrayList;
import java.util.List;

public class MDMRecordsBean {
    
    private List<String> tables = null;
    private List<String> entityID = null;
    private List<String> accountID = null;
    private List<String> recordID = null;
    private SourceRecordsBean sourceRecords = null;
    
    public MDMRecordsBean(List<String> tables, List<String> entityID) {
        this.tables = tables;
        this.entityID = entityID;
        this.accountID = new ArrayList<String>();
        this.recordID = new ArrayList<String>();
    }

    public List<String> getEntityID() {
        return entityID;
    }

    public void addAccountID(String accountID) {
        this.accountID.add(accountID);
    }

    public List<String> getAccountID() {
        return accountID;
    }

    public void addRecordID(String recordID) {
        this.recordID.add(recordID);
    }

    public List<String> getRecordID() {
        return recordID;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setSourceRecords(SourceRecordsBean sourceRecords) {
        this.sourceRecords = sourceRecords;
    }

    public SourceRecordsBean getSourceRecords() {
        return sourceRecords;
    }
}
