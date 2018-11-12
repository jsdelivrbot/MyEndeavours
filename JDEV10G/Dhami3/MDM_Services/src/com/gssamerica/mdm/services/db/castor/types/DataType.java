/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: DataType.java,v 1.2 2008/07/02 11:00:07 inderpal Exp $
 */

package com.gssamerica.mdm.services.db.castor.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class DataType.
 * 
 * @version $Revision: 1.2 $ $Date: 2008/07/02 11:00:07 $
 */
public class DataType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The NUMBER type
     */
    public static final int NUMBER_TYPE = 0;

    /**
     * The instance of the NUMBER type
     */
    public static final DataType NUMBER = new DataType(NUMBER_TYPE, "NUMBER");

    /**
     * The DATE type
     */
    public static final int DATE_TYPE = 1;

    /**
     * The instance of the DATE type
     */
    public static final DataType DATE = new DataType(DATE_TYPE, "DATE");

    /**
     * The BOOLEAN type
     */
    public static final int BOOLEAN_TYPE = 2;

    /**
     * The instance of the BOOLEAN type
     */
    public static final DataType BOOLEAN = new DataType(BOOLEAN_TYPE, "BOOLEAN");

    /**
     * The STRING type
     */
    public static final int STRING_TYPE = 3;

    /**
     * The instance of the STRING type
     */
    public static final DataType STRING = new DataType(STRING_TYPE, "STRING");

    /**
     * The CHAR type
     */
    public static final int CHAR_TYPE = 4;

    /**
     * The instance of the CHAR type
     */
    public static final DataType CHAR = new DataType(CHAR_TYPE, "CHAR");

    /**
     * The INTEGER type
     */
    public static final int INTEGER_TYPE = 5;

    /**
     * The instance of the INTEGER type
     */
    public static final DataType INTEGER = new DataType(INTEGER_TYPE, "INTEGER");

    /**
     * Field _memberTable.
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type.
     */
    private final int type;

    /**
     * Field stringValue.
     */
    private java.lang.String stringValue = null;


      //----------------/
     //- Constructors -/
    //----------------/

    private DataType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of DataType
     * 
     * @return an Enumeration over all possible instances of DataTyp
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this DataType
     * 
     * @return the type of this DataType
     */
    public int getType(
    ) {
        return this.type;
    }

    /**
     * Method init.
     * 
     * @return the initialized Hashtable for the member table
     */
    private static java.util.Hashtable init(
    ) {
        Hashtable members = new Hashtable();
        members.put("NUMBER", NUMBER);
        members.put("DATE", DATE);
        members.put("BOOLEAN", BOOLEAN);
        members.put("STRING", STRING);
        members.put("CHAR", CHAR);
        members.put("INTEGER", INTEGER);
        return members;
    }

    /**
     * Method readResolve. will be called during deserialization to
     * replace the deserialized object with the correct constant
     * instance.
     * 
     * @return this deserialized object
     */
    private java.lang.Object readResolve(
    ) {
        return valueOf(this.stringValue);
    }

    /**
     * Method toString.Returns the String representation of this
     * DataType
     * 
     * @return the String representation of this DataType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new DataType based on the given
     * String value.
     * 
     * @param string
     * @return the DataType value of parameter 'string'
     */
    public static com.gssamerica.mdm.services.db.castor.types.DataType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid DataType";
            throw new IllegalArgumentException(err);
        }
        return (DataType) obj;
    }

}
