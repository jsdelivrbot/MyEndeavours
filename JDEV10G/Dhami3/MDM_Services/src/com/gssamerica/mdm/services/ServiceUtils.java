package com.gssamerica.mdm.services;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMExceptionLogBean;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;
import com.gssamerica.mdm.services.log.MDMServiceLog;
import com.gssamerica.mdm.services.queries.execution.MDMQueryExecution;

import java.util.Hashtable;

public abstract class ServiceUtils {

    public static Hashtable getConfigHashtable(ConfigurationDataType configData) {
        Hashtable configDataTable = new Hashtable();
        ConfigurationDataElementType[] elements = null;
        ConfigurationDataElementType element = null;
        try {
            if (configData != null) {
                elements = configData.getElement();
                if (elements != null) {
                    for (int i = 0; i < elements.length; i++) {
                        element = elements[i];
                        configDataTable.put(element.getName(), 
                                            element.getValue());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return configDataTable;
    }
    
    public static void rollback(MDMQueryExecution mdmQueryExecution, String process, String transactionID, String source){
        MDMExceptionLogBean exceptionBean = null;
        String processId = ""+Thread.currentThread().getId();
        try {
            mdmQueryExecution.rollbackExecution();
        } catch (MDMDatabaseException e) {
            e.printStackTrace();
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId(processId);
            exceptionBean.setProcessName(process);
            exceptionBean.setSource(source);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(e);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean);   
        } 
        catch(Exception e){
            e.printStackTrace();
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Runtime Exception in MDM Save":e.getMessage());
            MDMRuntimeException runtimeException = new MDMRuntimeException("Runtime Exception in ServiceUtils while rollBAck: "+e.getMessage(), e.getCause(),e.getStackTrace());
            
            exceptionBean = new MDMExceptionLogBean();
            exceptionBean.setTransactionId(transactionID);
            exceptionBean.setProcessId(processId);
            exceptionBean.setProcessName(process);
            exceptionBean.setSource(source);
            exceptionBean.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            exceptionBean.setAction(MDMConstants.ACTION_DONOTHING);
            exceptionBean.setException(runtimeException);
            exceptionBean.setDestination(MDMConstants.DEFAULT_DESTINATION);
            exceptionBean.setExchangeFormat(MDMConstants.DEFAULT_EXCHANGE_FORMAT);
            exceptionBean.setBusinessKey(MDMConstants.DEFAULT_BUSINESS_KEY);
            exceptionBean.setRemediationQueue(MDMConstants.DEFAULT_REMEDIATION_QUEUE);
            MDMServiceLog.exceptionLog(exceptionBean);
        }
    }
}
