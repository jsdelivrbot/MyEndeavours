// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.4.0, build 080709.0800.28953)

package com.emerson.service.vdst;


public class ContentType implements java.io.Serializable {
    private java.lang.String value;
    private static final String _ThumbnailImageString = "ThumbnailImage";
    private static final String _ZoneImageString = "ZoneImage";
    private static final String _ZoneImageMapString = "ZoneImageMap";
    
    public static final java.lang.String _ThumbnailImage = new java.lang.String(_ThumbnailImageString);
    public static final java.lang.String _ZoneImage = new java.lang.String(_ZoneImageString);
    public static final java.lang.String _ZoneImageMap = new java.lang.String(_ZoneImageMapString);
    
    public static final ContentType ThumbnailImage = new ContentType(_ThumbnailImage);
    public static final ContentType ZoneImage = new ContentType(_ZoneImage);
    public static final ContentType ZoneImageMap = new ContentType(_ZoneImageMap);
    
    protected ContentType(java.lang.String value) {
        this.value = value;
    }
    
    public java.lang.String getValue() {
        return value;
    }
    
    public static ContentType fromValue(java.lang.String value)
        throws java.lang.IllegalStateException {
        if (ThumbnailImage.value.equals(value)) {
            return ThumbnailImage;
        }if (ZoneImage.value.equals(value)) {
            return ZoneImage;
        }if (ZoneImageMap.value.equals(value)) {
            return ZoneImageMap;
        }
        throw new IllegalArgumentException();
    }
    
    public static ContentType fromString(String value)
        throws java.lang.IllegalStateException {
        if (value.equals(_ThumbnailImageString)) {
            return ThumbnailImage;
        }if (value.equals(_ZoneImageString)) {
            return ZoneImage;
        }if (value.equals(_ZoneImageMapString)) {
            return ZoneImageMap;
        }
        throw new IllegalArgumentException();
    }
    
    public String toString() {
        return value.toString();
    }
    
    public boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentType)) {
            return false;
        }
        return ((ContentType)obj).value.equals(value);
    }
    
    public int hashCode() {
        return value.hashCode();
    }
}
