package com.gssamerica.mdm.services.search.criterias;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.queries.MDMSearchAllQuery;

public class SearchAllCriteria extends SearchCriteria {
    
    private String tableName = null;
    
    public SearchAllCriteria(String tableName) {
        this.tableName = tableName;
    }

    public MDMQuery createSearchQuery() throws MDMXMLException, 
                                               MDMQueryFormationException {
        MDMQuery searchQuery = null;
        searchQuery = new MDMSearchAllQuery(this.tableName);
        return searchQuery;
    }
}
