package com.gssamerica.mdm.services.log;

import com.gssamerica.mdm.constants.MDMConstants;
import com.gssamerica.mdm.exceptionlogger.MDMExceptionLogger;
import com.gssamerica.mdm.logger.MDMLogger;

import com.gssamerica.mdm.services.exceptions.MDMExceptionLogBean;

import java.util.Calendar;

public class MDMServiceLog {
    public MDMServiceLog() {}
    
    public static boolean exceptionLog(MDMExceptionLogBean exceptionBean){
        MDMExceptionLogger exceptionLogger = null;
        
        try {
            exceptionLogger = new MDMExceptionLogger();
            exceptionLogger.setAction(exceptionBean.getAction());
            exceptionLogger.setBusinessKey(exceptionBean.getBusinessKey());
            exceptionLogger.setData(exceptionBean.getData());
            exceptionLogger.setDateTime(exceptionBean.getDateTime());
            exceptionLogger.setDestination(exceptionBean.getDestination());
            exceptionLogger.setEnvironment(exceptionBean.getEnvironment());
            exceptionLogger.setErrorID(exceptionBean.getErrorID());
            exceptionLogger.setErrorMessage(exceptionBean.getErrorMessage());
            exceptionLogger.setErrorMessageCode(exceptionBean.getErrorMessageCode());
            exceptionLogger.setExchangeFormat(exceptionBean.getExchangeFormat());
            exceptionLogger.setGroupID(exceptionBean.getGroupID());
            exceptionLogger.setInputData(exceptionBean.getInputData());
            exceptionLogger.setProcessID(exceptionBean.getProcessId());
            exceptionLogger.setProcessName(exceptionBean.getProcessName());
            exceptionLogger.setRemediationQueue(exceptionBean.getRemediationQueue());
            exceptionLogger.setSource(exceptionBean.getSource());
            exceptionLogger.setStackTrace(exceptionBean.getStackTrace());
            exceptionLogger.setTransactionID(exceptionBean.getTransactionId());
            exceptionLogger.setType(exceptionBean.getType());
            exceptionLogger.log();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean log(String transactionID, String location, String processID, String processName, String msg, String level, String status, String data){
        MDMLogger mdmLogger = new MDMLogger();
        try {
            mdmLogger.setTransactionID(transactionID);
            mdmLogger.setEnvironment(MDMConstants.CURRENT_ENVIRONMENT);
            mdmLogger.setCreatedDateTime(Calendar.getInstance().getTime());
            mdmLogger.setLocation(location);
            mdmLogger.setProjectName(MDMConstants.PROJECT_NAME);
            mdmLogger.setProcessId(processID);
            mdmLogger.setProcessName(processName);
            mdmLogger.setLogMessage(msg);
            mdmLogger.setLogData(data);
            mdmLogger.setLogLevel(level);
            mdmLogger.setStatusFlag(status);
            mdmLogger.log();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
