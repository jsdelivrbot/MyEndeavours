package com.gssamerica.mdm.constants;

import com.gssamerica.mdm.constants.MDMDataTypes;

import java.sql.SQLException;
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
    
    public final static String PROJECT_NAME = "MDM";
    public final static String STATUS_PROGRESS = "IN-PROGRESS";
    public final static String STATUS_COMPLETE = "COMPLETED";
    public final static String STATUS_FAILED = "FAILED";
    
    public final static String DEFAULT_DESTINATION = "QUEUE";
    public final static String DEFAULT_EXCHANGE_FORMAT = "NONE";
    public final static String DEFAULT_BUSINESS_KEY = "NONE";
    public final static String DEFAULT_REMEDIATION_QUEUE = "NONE";
    public final static String DEFAULT_SOURCE_DESC = "NONE";
    
    public final static String PROCESS_CREATERECORD_SERVICE = "MDMCreateRecords";
    public final static String PROCESS_DESC_CREATERECORD_SERVICE = "WebService for Creating Records";
    public final static String PROCESS_SEARCHRECORD_SERVICE = "MDMSearchRecords";
    public final static String PROCESS_DESC_SEARCHRECORD_SERVICE = "WebService for Searching Records";
    public final static String PROCESS_UPDATERECORD_SERVICES = "MDMUpadateRecords";
    public final static String PROCESS_DESC_UPDATERECORD_SERVICES = "WebService for Updating Records";
    public final static String PROCESS_ADDREMOTEKEY_SERVICES = "MDMAddRemoteKey";
    public final static String PROCESS_DESC_ADDREMOTEKEY_SERVICES = "WebService for Adding Remote Key for Records";
    public final static String PROCESS_DELETERECORD_SERVICE = "MDMDeleteRecords";
    public final static String PROCESS_DESC_DELETERECORD_SERVICE = "WebService for Deleting Records";
    public final static String PROCESS_CHECKANDREMOVE_SERVICE = "CheckAndRemoveDuplicatesService";
    public final static String PROCESS_DESC_CHECKANDREMOVE_SERVICE = "WebService for Checking and Deleting Duplicate Records";
    public final static String PROCESS_REPLICATEDATA_SERVICE = "ReplicateDataService";  
    public final static String PROCESS_DESC_REPLICATEDATA_SERVICE = "WebService for Replicating Keys to Local Hubs";
    
    public final static String PROCESS_WORKFLOW_MDB = "WorkFlowInitiatorMDB";
    public final static String PROCESS_DESC_WORKFLOW_MDB = "MDB to delgate Request from BPEL to Workflow Initiator";
    
    public final static String PROCESS_WORKFLOW_SERVICE = "WorkFlowInitiatorService";
    public final static String PROCESS_DESC_WORKFLOW_SERVICE = "WebService for Dispatching Request to Workflow";
    
    public final static String PROCESS_WORKFLOW_ADMIN = "Workflow Admin TaskList";
    public final static String PROCESS_DESC_WORKFLOW_ADMIN = "Workflow Process for Administrator";
    public final static String PROCESS_WORKFLOW_USER = "Workflow User/Manager TaskList";
    public final static String PROCESS_DESC_WORKFLOW_USER = "Workflow Process for User/Manager";
    
    public final static String PROCESS_WORKFLOW = "WorkFlow";
    public final static String PROCESS_DESC_WORKFLOW = "MDM Steward Workflow";
    public final static String PROCESS_WORKFLOW_MDMWORKFLOW = "MDMWorkFlow";
    public final static String PROCESS_DESC_WORKFLOW_MDMWORKFLOW = "Workflow for Creating Records";
    public final static String PROCESS_WORKFLOW_ADDREMOTEKEY = "AddRemoteKeyWorkFlow";
    public final static String PROCESS_DESC_WORKFLOW_ADDREMOTEKEY = "Workflow for Adding Remote Keys for Records";
    public final static String PROCESS_WORKFLOW_DELETERECORD = "DeleteRecordWorkFlow";
    public final static String PROCESS_DESC_WORKFLOW_DELETERECORD = "Workflow for Deleting Records";
    public final static String PROCESS_WORKFLOW_UPDATERECORD = "UpdateRecordWorkFlow";
    public final static String PROCESS_DESC_WORKFLOW_UPDATERECORD = "Workflow for Updating Records";    
    
    public final static String PROCESS_MDMADMIN = "MDMAdminServices";
    public final static String PROCESS_DESC_MDMADMIN = "UI Component for MDM Administrators";
    public final static String PROCESS_MDMADMIN_CREATE = "MDMAdminServices_CreateEntity";
    public final static String PROCESS_DESC_MDMADMIN_CREATE = "UI Component for MDM Administrators for Creating Entity";
    public final static String PROCESS_MDMADMIN_UPDATE = "MDMAdminServices_UpdateEntity";
    public final static String PROCESS_DESC_MDMADMIN_UPDATE = "UI Component for MDM Administrators for Updating Entity";
    public final static String PROCESS_MDMADMIN_DELETE = "MDMAdminServices_DeleteEntity";
    public final static String PROCESS_DESC_MDMADMIN_DELETE = "UI Component for MDM Administrators for Deleting Entity";
    
    public final static String PROCESS_SEARCHRECORD_PROXY_SERVICE = "ProxyMDMSearchRecord";
    public final static String PROCESS_DESC_SEARCHRECORD_PROXY_SERVICE = "Proxy WebService for Searching Records from Admin UI";
    
    public final static String PROCESS_TRANSFORM_ESB = "TransformESB";
    public final static String PROCESS_DESC_TRANSFORM_ESB = "ESB Service to transform data from Common Schema to MDM Schema";
    public final static String PROCESS_ADAPTER_ESB = "AdapterESB";
    public final static String PROCESS_DESC_ADAPTER_ESB = "ESB Service to fetch and transform from CRM Schema to Common Schema";
    
    public final static String OPERATION_CREATE = "CREATE";
    public final static String OPERATION_UPDATE = "UPDATE";
    public final static String OPERATION_DELETE = "DELETE";
    public final static String OPERATION_SEARCH = "SEARCH";
    
    public final static String ENTITYSTATUS_NEW = "NEW";
    public final static String ENTITYSTATUS_REMOTEKEYNOTFOUND = "REMOTEKEYNOTFOUND";
    public final static String ENTITYSTATUS_REMOTEKEYFOUND = "REMOTEKEYFOUND";
    
    public final static String SEVERITY_HIGH = "HIGH";
    public final static String SEVERITY_MEDIUM = "MEDIUM";
    public final static String SEVERITY_LOW = "LOW";
    
    // Config Data Key Names
    public static final String TransactionID = "transactionID";
    public static final String UserID = "userID";
    public static final String RemoteSystem = "srcID";
    
    public static final String ENV_TESTING = "TESTING SERVER";
    public static final String ENV_DEVELOPMENT = "DEVELOPMENT SERVER";
    public static final String ENV_PRODUCTION = "PRODUCTION SERVER";
    public static final String ENV_STAGE = "STAGING SERVER";
    public static final String CURRENT_ENVIRONMENT = ENV_TESTING;
    
    public static final String ACTION_DONOTHING = "NO ACTION";
    public static final String ACTION_RESEND = "RESEND";
    public static final String ACTION_FORWARD = "FORWARD";
    
    public static final String LOGLEVEL_INFO = "INFO";
    public static final String LOGLEVEL_WARN = "WARNING";
    public static final String LOGLEVEL_ALERT = "ALERT";
    public static final String LOGLEVEL_ERR = "ERROR";
    
    public static final String MDM_HUB_USERNAME = "mdm";
    public static final String MDM_HUB_PASSWORD = "mdm";
    public static final String MDM_LOCAL_USERNAME = "mdmlocal";
    public static final String MDM_LOCAL_PASSWORD = "mdmlocal";
    
    public static final String ENTITY_CUSTOMER = "Customer";
    public static final String ENTITY_CUSTOMER_REQUESTTYPE_CREATE = "CreateCustomer";
    public static final String ENTITY_CUSTOMER_REQUESTTYPE_UPDATE = "UpdateCustomer";
    public static final String ENTITY_CUSTOMER_REQUESTTYPE_DELETE = "DeleteCustomer";
    
    public static final String ENTITY_PRODUCT = "Customer";
    public static final String ENTITY_PRODUCT_REQUESTTYPE_CREATE = "CreateProduct";
    public static final String ENTITY_PRODUCT_REQUESTTYPE_UPDATE = "UpdateProduct";
    public static final String ENTITY_PRODUCT_REQUESTTYPE_DELETE = "DeleteProduct";
    
    public static final String WILDCARD_ANY = "*";
    public final static String DATE_FORMAT_MDM_HUB = "dd-MMM-yyyy";
    public final static String DATE_FORMAT_FETCH_JDBC_MDM_HUB = "yyyy-MM-dd";
    public final static String DATE_FORMAT_EXTERNAL = "MM/dd/yyyy";
    
    
    public static void main(String args[]){
        SQLException se = new SQLException();
        Exception ex = (Exception)se;
        System.out.println("Class - "+ ex.getClass().getCanonicalName());
    }
}
