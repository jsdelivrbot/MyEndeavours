// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.4.0, build 080709.0800.28953)

package com.tempproxy.proxy;


public class LoginRequestType implements java.io.Serializable {
    protected java.lang.String loginName;
    protected byte[] password;
    
    public LoginRequestType() {
    }
    
    public java.lang.String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(java.lang.String loginName) {
        this.loginName = loginName;
    }
    
    public byte[] getPassword() {
        return password;
    }
    
    public void setPassword(byte[] password) {
        this.password = password;
    }
}