// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.4.0, build 080709.0800.28953)

package com.tempproxy.proxy.types.com.emerson.service.vdst.tool.common;


public class FaultDetailType extends Exception {
    private java.lang.String code;
    private java.lang.String message;
    
    
    public FaultDetailType() {
    }
    
    public FaultDetailType(java.lang.String code, java.lang.String message) {
        this.code = code;
        this.message = message;
    }
    
    public void setCode(java.lang.String code) {
        this.code = code;
    }
    
    public void setMessage(java.lang.String message) {
        this.message = message;
    }
    
    public java.lang.String getCode() {
        return code;
    }
    
    public java.lang.String getMessage() {
        return message;
    }
    
}