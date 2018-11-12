package com.gssamerica.mdm.constants;

public abstract class XPATHConstants {
    
    public static final String SOAP_PREFIX = "soap";
    public static final String ESB_SOAP_PREFIX = "env";
    public static final String MDM_SERVICE_PREFIX = "imp1";
    public static final String MDM_DELETESERVICE_PREFIX = "ns1";
    public static final String WORKFLOW_SERVICE_PREFIX = "wfl";
    public static final String ESB_PROCESS_CUSTOMER_PREFIX = "tns";
    
    
    public static final String SOAP_ENVELOPE = SOAP_PREFIX+":Envelope";
    public static final String SOAP_HEADER = SOAP_PREFIX+":Header";
    public static final String SOAP_BODY = SOAP_PREFIX+":Body";
    public static final String CreateRecordsRequest = MDM_SERVICE_PREFIX+":CreateRecordsRequest";
    public static final String UpdateRecordRequest = MDM_SERVICE_PREFIX+":UpdateRecordRequest";
    public static final String SearchRecordRequest = MDM_SERVICE_PREFIX+":SearchRecordRequest";
    public static final String AddRemoteKeyRequest = MDM_SERVICE_PREFIX+":AddRemoteKeyRequest";
    public static final String ReplicateDataRequest = MDM_SERVICE_PREFIX+":ReplicateDataRequest";
    public static final String DeleteRecordRequest = MDM_SERVICE_PREFIX+":DeleteRecordRequest";
    public static final String DeleteServiceRequest = MDM_DELETESERVICE_PREFIX+":DeleteServiceRequest";
    public static final String Record = MDM_SERVICE_PREFIX+":record";
    public static final String RecordIdentifier = MDM_SERVICE_PREFIX+":recordIdentifier";
    public static final String RemoteKey = MDM_SERVICE_PREFIX+":remoteKey";
    public static final String RemoteSystem = MDM_SERVICE_PREFIX+":remoteSystem";
    public static final String RemoteKey_Key = MDM_SERVICE_PREFIX+":key";
    public static final String RemoteKey_Key_isdefault = MDM_SERVICE_PREFIX+":isDefault";
    public static final String RemoteKey_Key_Value = MDM_SERVICE_PREFIX+":value";
    public static final String ConfigData = MDM_SERVICE_PREFIX+":configData";
    public static final String ConfigElement = MDM_SERVICE_PREFIX+":element";
    public static final String Value = MDM_SERVICE_PREFIX+":value";
    public static final String Name = MDM_SERVICE_PREFIX+":name";
    
    public static final String WorkflowRequest = WORKFLOW_SERVICE_PREFIX+":WorkflowRequest";
    public static final String WorkflowRequestHeader = "header";
    public static final String WorkflowRequestHeader_srcID ="srcID";
    public static final String WorkflowRequestHeader_operation = "operation";
    public static final String WorkflowRequestHeader_entitystatus = "entitystatus";
    public static final String WorkflowRequestHeader_entity = "entity";
    public static final String WorkflowRequestBody = "body";
    public static final String WorkflowRequestConfig = "configData";
    public static final String WorkflowRequestConfigName = "name";
    public static final String WorkflowRequestConfigValue = "value";
    
    public static final String ESB_REQUEST_PROCESS_CUSTOMER = ESB_PROCESS_CUSTOMER_PREFIX+":ESBProcessCustomerRequest";
    public static final String ESB_REQUEST_HEADER = ESB_PROCESS_CUSTOMER_PREFIX+":header";
    public static final String ESB_REQUEST_BODY = ESB_PROCESS_CUSTOMER_PREFIX+":body";
    public static final String ESB_REQUEST_SOURCE = ESB_PROCESS_CUSTOMER_PREFIX+":srcID";
    public static final String ESB_REQUEST_REQUESTTYPE = ESB_PROCESS_CUSTOMER_PREFIX+":requestType";
    public static final String ESB_REQUEST_TRANSID = ESB_PROCESS_CUSTOMER_PREFIX+":transactionID";
    public static final String ESB_REQUEST_ENTITY = ESB_PROCESS_CUSTOMER_PREFIX+":entity";
}
