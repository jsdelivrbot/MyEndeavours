// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.4.0, build 080709.0800.28953)

package com.emerson.services.types.com.emerson.schemas.common.envelope;


public class SeverityTypeEnum implements java.io.Serializable {
    private java.lang.String value;
    private static final String _HIGHString = "HIGH";
    private static final String _MEDIUMString = "MEDIUM";
    private static final String _LOWString = "LOW";
    
    public static final java.lang.String _HIGH = new java.lang.String(_HIGHString);
    public static final java.lang.String _MEDIUM = new java.lang.String(_MEDIUMString);
    public static final java.lang.String _LOW = new java.lang.String(_LOWString);
    
    public static final SeverityTypeEnum HIGH = new SeverityTypeEnum(_HIGH);
    public static final SeverityTypeEnum MEDIUM = new SeverityTypeEnum(_MEDIUM);
    public static final SeverityTypeEnum LOW = new SeverityTypeEnum(_LOW);
    
    protected SeverityTypeEnum(java.lang.String value) {
        this.value = value;
    }
    
    public java.lang.String getValue() {
        return value;
    }
    
    public static SeverityTypeEnum fromValue(java.lang.String value)
        throws java.lang.IllegalStateException {
        if (HIGH.value.equals(value)) {
            return HIGH;
        }if (MEDIUM.value.equals(value)) {
            return MEDIUM;
        }if (LOW.value.equals(value)) {
            return LOW;
        }
        throw new IllegalArgumentException();
    }
    
    public static SeverityTypeEnum fromString(String value)
        throws java.lang.IllegalStateException {
        if (value.equals(_HIGHString)) {
            return HIGH;
        }if (value.equals(_MEDIUMString)) {
            return MEDIUM;
        }if (value.equals(_LOWString)) {
            return LOW;
        }
        throw new IllegalArgumentException();
    }
    
    public String toString() {
        return value.toString();
    }
    
    public boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SeverityTypeEnum)) {
            return false;
        }
        return ((SeverityTypeEnum)obj).value.equals(value);
    }
    
    public int hashCode() {
        return value.hashCode();
    }
}
