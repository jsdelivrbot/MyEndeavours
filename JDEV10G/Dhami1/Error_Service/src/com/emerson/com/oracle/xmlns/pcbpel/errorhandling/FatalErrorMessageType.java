// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.4.0, build 080709.0800.28953)

package com.emerson.com.oracle.xmlns.pcbpel.errorhandling;


public class FatalErrorMessageType implements java.io.Serializable {
    protected java.lang.String originator;
    protected java.lang.String reason;
    protected java.lang.String exception;
    protected java.lang.String stackTrace;
    
    public FatalErrorMessageType() {
    }
    
    public java.lang.String getOriginator() {
        return originator;
    }
    
    public void setOriginator(java.lang.String originator) {
        this.originator = originator;
    }
    
    public java.lang.String getReason() {
        return reason;
    }
    
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }
    
    public java.lang.String getException() {
        return exception;
    }
    
    public void setException(java.lang.String exception) {
        this.exception = exception;
    }
    
    public java.lang.String getStackTrace() {
        return stackTrace;
    }
    
    public void setStackTrace(java.lang.String stackTrace) {
        this.stackTrace = stackTrace;
    }
}