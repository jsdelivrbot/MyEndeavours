package com.gssamerica.mdm.services.search.criterias;

import java.util.ArrayList;

import com.gssamerica.mdm.services.DataElementCriteriaType;
import com.gssamerica.mdm.services.RecordIdentifierCriteriaType;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.queries.MDMSearchByDataElementQuery;
import com.gssamerica.mdm.services.queries.MDMSearchByRecordIdQuery;

public class RecordIdentifierCriteria extends SearchCriteria {

	private String tableName = null;
	private String logicalOperator = null;
	private RecordIdentifierCriteriaType[] criterion = null;
	
	public RecordIdentifierCriteria(String tableName, String logicalOperator, RecordIdentifierCriteriaType[] criterion){
		this.tableName = tableName;
		this.logicalOperator = logicalOperator;
		this.criterion = criterion;
	}
	
	@Override
	public MDMQuery createSearchQuery() throws MDMQueryFormationException, 
                                               MDMXMLException {
		
		MDMQuery searchQuery = null;
		RecordIdentifierCriteriaType criteria = null;
		ArrayList recordIds = null;
		
		if(criterion==null){
			throw (new MDMQueryFormationException("[RecordIdentifierCriteria]: NULL Criterion."));
		}
		else{
			System.out.println("Criterion is not NULL - "+criterion.length);
		}
		
		recordIds = new ArrayList();
				
		for(int i=0; i<criterion.length;i++){
			criteria = criterion[i];
			recordIds.add(criteria.getRecordId());
		}
		searchQuery = new MDMSearchByRecordIdQuery(this.tableName,recordIds);
		return searchQuery;
	}
}
