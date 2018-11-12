// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.4.0, build 080709.0800.28953)

package com.emerson.schemas.common.envelope;


public class HeaderType implements java.io.Serializable {
    protected java.lang.String flowID;
    protected java.lang.String transactionID;
    protected java.lang.String originalSource;
    protected com.emerson.schemas.common.envelope.RoutingInfoType routingInfo;
    protected java.lang.String destination;
    protected java.lang.String userID;
    protected com.emerson.schemas.common.envelope.HeaderPropertiesType headerProperties;
    
    public HeaderType() {
    }
    
    public java.lang.String getFlowID() {
        return flowID;
    }
    
    public void setFlowID(java.lang.String flowID) {
        this.flowID = flowID;
    }
    
    public java.lang.String getTransactionID() {
        return transactionID;
    }
    
    public void setTransactionID(java.lang.String transactionID) {
        this.transactionID = transactionID;
    }
    
    public java.lang.String getOriginalSource() {
        return originalSource;
    }
    
    public void setOriginalSource(java.lang.String originalSource) {
        this.originalSource = originalSource;
    }
    
    public com.emerson.schemas.common.envelope.RoutingInfoType getRoutingInfo() {
        return routingInfo;
    }
    
    public void setRoutingInfo(com.emerson.schemas.common.envelope.RoutingInfoType routingInfo) {
        this.routingInfo = routingInfo;
    }
    
    public java.lang.String getDestination() {
        return destination;
    }
    
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }
    
    public java.lang.String getUserID() {
        return userID;
    }
    
    public void setUserID(java.lang.String userID) {
        this.userID = userID;
    }
    
    public com.emerson.schemas.common.envelope.HeaderPropertiesType getHeaderProperties() {
        return headerProperties;
    }
    
    public void setHeaderProperties(com.emerson.schemas.common.envelope.HeaderPropertiesType headerProperties) {
        this.headerProperties = headerProperties;
    }
}
