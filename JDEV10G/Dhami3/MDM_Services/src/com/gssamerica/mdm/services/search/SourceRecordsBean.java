package com.gssamerica.mdm.services.search;

import java.util.ArrayList;
import java.util.List;

public class SourceRecordsBean {
    
    private List<String> masterID = null;
    private List<String> sourceID = null;
    private List<String> sourceKey = null;
    private int size = 0;
    
    public SourceRecordsBean() {
        masterID = new ArrayList<String>();
        sourceID = new ArrayList<String>();
        sourceKey = new ArrayList<String>();
    }

    public void addMasterID(String masterID) {
        size++;
        this.masterID.add(masterID);
    }

    public List<String> getMasterID() {
        return masterID;
    }

    public void addSourceID(String sourceID) {
        this.sourceID.add(sourceID);
    }

    public List<String> getSourceID() {
        return sourceID;
    }

    public void addSourceKey(String sourceKey) {
        this.sourceKey.add(sourceKey);
    }

    public List<String> getSourceKey() {
        return sourceKey;
    }

    public int getSize() {
        return size;
    }
}
