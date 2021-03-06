// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.3.0, build 070610.1800.23513)

package com.gssamerica.mdm.services.search;


public class Result implements java.io.Serializable {
    private java.lang.String value;
    private static final String _FULL_MATCHString = "FULL_MATCH";
    private static final String _PARTIAL_MATCHString = "PARTIAL_MATCH";
    private static final String _NO_MATCHString = "NO_MATCH";
    
    public static final java.lang.String _FULL_MATCH = new java.lang.String(_FULL_MATCHString);
    public static final java.lang.String _PARTIAL_MATCH = new java.lang.String(_PARTIAL_MATCHString);
    public static final java.lang.String _NO_MATCH = new java.lang.String(_NO_MATCHString);
    
    public static final Result FULL_MATCH = new Result(_FULL_MATCH);
    public static final Result PARTIAL_MATCH = new Result(_PARTIAL_MATCH);
    public static final Result NO_MATCH = new Result(_NO_MATCH);
    
    protected Result(java.lang.String value) {
        this.value = value;
    }
    
    public java.lang.String getValue() {
        return value;
    }
    
    public static Result fromValue(java.lang.String value)
        throws java.lang.IllegalStateException {
        if (FULL_MATCH.value.equals(value)) {
            return FULL_MATCH;
        } else if (PARTIAL_MATCH.value.equals(value)) {
            return PARTIAL_MATCH;
        } else if (NO_MATCH.value.equals(value)) {
            return NO_MATCH;
        }
        throw new IllegalArgumentException();
    }
    
    public static Result fromString(String value)
        throws java.lang.IllegalStateException {
        if (value.equals(_FULL_MATCHString)) {
            return FULL_MATCH;
        } else if (value.equals(_PARTIAL_MATCHString)) {
            return PARTIAL_MATCH;
        } else if (value.equals(_NO_MATCHString)) {
            return NO_MATCH;
        }
        throw new IllegalArgumentException();
    }
    
    public String toString() {
        return value.toString();
    }
    
    public boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Result)) {
            return false;
        }
        return ((Result)obj).value.equals(value);
    }
    
    public int hashCode() {
        return value.hashCode();
    }
}
