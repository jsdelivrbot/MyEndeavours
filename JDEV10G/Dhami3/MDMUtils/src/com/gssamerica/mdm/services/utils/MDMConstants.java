package com.gssamerica.mdm.services.utils;

import java.sql.Types;

public abstract class MDMConstants {
	
	public final static MDMDataTypes MDM_STRING = new MDMDataTypes(Types.VARCHAR,"STRING");
	public final static MDMDataTypes MDM_INTEGER = new MDMDataTypes(Types.INTEGER,"INTEGER");
	public final static MDMDataTypes MDM_FLOAT = new MDMDataTypes(Types.FLOAT,"FLOAT");
	public final static MDMDataTypes MDM_DOUBLE = new MDMDataTypes(Types.DOUBLE,"DOUBLE");
	public final static MDMDataTypes MDM_DATE = new MDMDataTypes(Types.DATE,"DATE");
	public final static MDMDataTypes MDM_BOOLEAN = new MDMDataTypes(Types.BOOLEAN,"BOOLEAN");
	public final static MDMDataTypes MDM_LONG = new MDMDataTypes(Types.BIGINT,"LONG");
	public final static MDMDataTypes MDM_OTHER = new MDMDataTypes(Types.OTHER,"OTHER");
	public final static MDMDataTypes MDM_NUMBER = new MDMDataTypes(Types.NUMERIC,"NUMBER");
	public final static MDMDataTypes MDM_CHAR = new MDMDataTypes(Types.CHAR,"CHAR");
	
	public final static String AND_OPERATOR = "AND";
	public final static String OR_OPERATOR = "OR";
	public final static String EQUAL_OPERATOR = "=";
	public final static String COMPARE_WITH_NULL_OPERATOR = "IS";
	public final static String NULL = "NULL";
	public final static String LEFT_OUTER_JOIN = "LEFT OUTER JOIN";
	public final static String TO_LOWER = "lower";
	
        public final static String MDM_SOURCE_TABLE = "Source";
        public final static String MDM_MASTER_TABLE = "MDMMaster";
        public final static String MDM_MASTER_PK_COLUMN = "MasterID";
	public final static String MDM_RECORD_TABLE = "MDMRecord";
	public final static String MDM_ENTITY_LINK_TABLE = "MDMEntityLink";
	public final static String MDM_ENTITY_LINK_COLUMN = "EntityId";
	public final static String MDM_ENTITY_LINK_RECORD_COLUMN = "RecordId";
	public final static String MDM_ENTITY_PK_COLUMN = "PersonId";
	public final static String MDM_CUSTOMER_TABLE = "CustomerAccount";
        public final static String MDM_CUSTOMER_PK_COLUMN = "Accountid";
        public final static String MDM_CUSTOMER_PERSON_COLUMN = "Person";
	
	public final static String MDM_DEFAULT_LANG = "English[US]";
	
	public final static String MDM_SUCCESS_STATUS = "SUCCESS";
	public final static String MDM_FAILURE_STATUS = "FAILURE";
	
	public final static String MDM_DB_SEQUENCE = "MDM_SEQUENCE";
	public final static String MDM_DB_SEQUENCE_QUERY = "select "+MDM_DB_SEQUENCE+".NEXTVAL from dual";
        
        public final static String AUDIT_PROJECT_NAME = "MDM";
        public final static String AUDIT_STATUS_PROGRESS = "IN-PROGRESS";
        public final static String AUDIT_STATUS_COMPLETE = "COMPLETED";
        
        public final static String PROCESS_CREATE_RECORD = "MDMCreateRecords";
        public final static String PROCESS_SEARCH_RECORD = "MDMCreateRecords";
	
	public static String getDataTypeName(int sqlType){
		String typeName = null;

		if(sqlType == MDM_STRING.getSqlType()){
			typeName = MDM_STRING.getTypeName();
		}
		else if(sqlType == MDM_INTEGER.getSqlType()){
			typeName = MDM_INTEGER.getTypeName();
		}
		else if(sqlType == MDM_FLOAT.getSqlType()){
			typeName = MDM_FLOAT.getTypeName();
		}
		else if(sqlType == MDM_DOUBLE.getSqlType()){
			typeName = MDM_DOUBLE.getTypeName();
		}
		else if(sqlType == MDM_DATE.getSqlType()){
			typeName = MDM_DATE.getTypeName();
		}
		else if(sqlType == MDM_BOOLEAN.getSqlType()){
			typeName = MDM_BOOLEAN.getTypeName();
		}
		else if(sqlType == MDM_LONG.getSqlType()){
			typeName = MDM_LONG.getTypeName();
		}
		else if(sqlType == MDM_NUMBER.getSqlType()){
			typeName = MDM_NUMBER.getTypeName();
		}
		else if(sqlType == MDM_CHAR.getSqlType()){
			typeName = MDM_CHAR.getTypeName();
		}
		else{
			typeName = MDM_OTHER.getTypeName();
		}
		
		return typeName;
	}
}
