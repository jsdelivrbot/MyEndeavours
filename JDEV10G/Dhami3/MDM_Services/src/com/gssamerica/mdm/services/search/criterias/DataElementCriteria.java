package com.gssamerica.mdm.services.search.criterias;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.queries.MDMSearchByDataElementQuery;
import com.gssamerica.mdm.services.DataElementCriteriaType;
import com.gssamerica.mdm.services.LookupValueCriteria;
import com.gssamerica.mdm.services.exceptions.MDMXMLException;

public class DataElementCriteria extends SearchCriteria {

	private String tableName = null;
	private String logicalOperator = null;
	private DataElementCriteriaType[] criterion = null;
	
	public DataElementCriteria(String tableName, String logicalOperator, DataElementCriteriaType[] criterion){
		this.tableName = tableName;
		this.logicalOperator = logicalOperator;
		this.criterion = criterion;
	}
	
	@Override
	public MDMQuery createSearchQuery() throws MDMQueryFormationException, 
                                               MDMXMLException {
		
		MDMQuery searchQuery = null;
		
		searchQuery = new MDMSearchByDataElementQuery(this.tableName,this.logicalOperator);
		if(criterion==null){
			throw (new MDMQueryFormationException("[DataElementCriteria]: NULL Criterion."));
		}
		else{
			System.out.println("Criterion is not NULL - "+criterion.length);
		}
		
		searchQuery = addCriteria(this.tableName,null,searchQuery, this.criterion);		
		return searchQuery;
	}
	
	private MDMQuery addCriteria(String tableName, String alias, MDMQuery searchQuery, DataElementCriteriaType[] criterion){
		String fieldName = null;
		String fieldValue = null;
		String fieldDataType = null;
		String lookupTableName = null;
		LookupValueCriteria lookupDataCriteria = null;
		DataElementCriteriaType criteria = null;
		DataElementCriteriaType[] lookupCriterion = null;
		String tableAlias = (alias==null)?tableName:alias;
                
		for(int i=0;i<criterion.length;i++){
			criteria = criterion[i];
			fieldDataType = criteria.getDataType();
			fieldName = criteria.getFieldCode();
			if(criteria.getSimpleValueCriteria()!=null){
				fieldValue = criteria.getSimpleValueCriteria().getValue();
				searchQuery.addQueryItem(tableAlias, fieldDataType, fieldName, fieldValue);
			}
			else{
				lookupDataCriteria = criteria.getLookupValueCriteria();
				lookupTableName = lookupDataCriteria.getTableCode();
				lookupCriterion = lookupDataCriteria.getDataElementCriteria();
				searchQuery = addCriteria(lookupTableName,fieldName, searchQuery, lookupCriterion);
			}

		}
		return searchQuery;
	}
}
