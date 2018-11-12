package com.gssamerica.mdm.services.search.criterias;

import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.RecordCriteriaType;
import com.gssamerica.mdm.services.exceptions.MDMException;
import com.gssamerica.mdm.services.exceptions.MDMMissingTableException;
import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;

public abstract class SearchCriteria {
	
	public static SearchCriteria getInstance(RecordCriteriaType criteriaType) throws MDMQueryFormationException, MDMMissingTableException{
		
		SearchCriteria criteria = null; 
		String tableName = null;
		String logicalOperator = null;
		
		if(criteriaType!=null){
			if(criteriaType.getTableCode() == null || criteriaType.getTableCode().equals("")){
				throw (new MDMMissingTableException("[SearchCriteria]: No TABLE Name Found"));
			}
			tableName = criteriaType.getTableCode();
			if(criteriaType.getLogicOperator()!=null && criteriaType.getLogicOperator().equalsIgnoreCase(MDMConstants.OR_OPERATOR)){
				logicalOperator = MDMConstants.OR_OPERATOR;
			}
			else{
				logicalOperator = MDMConstants.AND_OPERATOR;
			}
						
			if(criteriaType.getDataElementCriteria()!=null && criteriaType.getDataElementCriteria().length!=0){
				//System.out.println("Data Element Criteria...");
				criteria = new DataElementCriteria(tableName, logicalOperator, criteriaType.getDataElementCriteria());
			}
			else if(criteriaType.getRecordIdentifierCriteria()!=null && criteriaType.getRecordIdentifierCriteria().length!=0){
                                //System.out.println("RecordIdentifier Criteria...");
				criteria = new RecordIdentifierCriteria(tableName, logicalOperator, criteriaType.getRecordIdentifierCriteria());
			}
			else{
			    //System.out.println("Search All Criteria...");
			    criteria = new SearchAllCriteria(tableName);
			}
		}
		else{
			throw (new MDMQueryFormationException("[SearchCriteria]: Criteria is NULL."));
		}
			
		return criteria;
	}
	public abstract MDMQuery createSearchQuery() throws MDMException;
}
