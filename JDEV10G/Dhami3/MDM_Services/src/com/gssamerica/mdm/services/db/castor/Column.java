/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: Column.java,v 1.2 2008/07/02 10:59:28 inderpal Exp $
 */

package com.gssamerica.mdm.services.db.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Column.
 * 
 * @version $Revision: 1.2 $ $Date: 2008/07/02 10:59:28 $
 */
public class Column implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private long _id;

    /**
     * keeps track of state for field: _id
     */
    private boolean _has_id;

    /**
     * Field _code.
     */
    private java.lang.String _code;

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _type.
     */
    private com.gssamerica.mdm.services.db.castor.types.DataType _type;

    /**
     * Field _null.
     */
    private boolean _null = true;

    /**
     * keeps track of state for field: _null
     */
    private boolean _has_null;

    /**
     * Field _primaryKey.
     */
    private boolean _primaryKey = false;

    /**
     * keeps track of state for field: _primaryKey
     */
    private boolean _has_primaryKey;

    /**
     * Field _default.
     */
    private java.lang.String _default = "";

    /**
     * Field _comment.
     */
    private java.lang.String _comment;

    /**
     * Field _foreignKey.
     */
    private com.gssamerica.mdm.services.db.castor.ForeignKey _foreignKey;


      //----------------/
     //- Constructors -/
    //----------------/

    public Column() {
        super();
        setDefault("");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     */
    public void deleteNull(
    ) {
        this._has_null= false;
    }

    /**
     */
    public void deletePrimaryKey(
    ) {
        this._has_primaryKey= false;
    }

    /**
     * Returns the value of field 'code'.
     * 
     * @return the value of field 'Code'.
     */
    public java.lang.String getCode(
    ) {
        return this._code;
    }

    /**
     * Returns the value of field 'comment'.
     * 
     * @return the value of field 'Comment'.
     */
    public java.lang.String getComment(
    ) {
        return this._comment;
    }

    /**
     * Returns the value of field 'default'.
     * 
     * @return the value of field 'Default'.
     */
    public java.lang.String getDefault(
    ) {
        return this._default;
    }

    /**
     * Returns the value of field 'foreignKey'.
     * 
     * @return the value of field 'ForeignKey'.
     */
    public com.gssamerica.mdm.services.db.castor.ForeignKey getForeignKey(
    ) {
        return this._foreignKey;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public long getId(
    ) {
        return this._id;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'null'.
     * 
     * @return the value of field 'Null'.
     */
    public boolean getNull(
    ) {
        return this._null;
    }

    /**
     * Returns the value of field 'primaryKey'.
     * 
     * @return the value of field 'PrimaryKey'.
     */
    public boolean getPrimaryKey(
    ) {
        return this._primaryKey;
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public com.gssamerica.mdm.services.db.castor.types.DataType getType(
    ) {
        return this._type;
    }

    /**
     * Method hasId.
     * 
     * @return true if at least one Id has been added
     */
    public boolean hasId(
    ) {
        return this._has_id;
    }

    /**
     * Method hasNull.
     * 
     * @return true if at least one Null has been added
     */
    public boolean hasNull(
    ) {
        return this._has_null;
    }

    /**
     * Method hasPrimaryKey.
     * 
     * @return true if at least one PrimaryKey has been added
     */
    public boolean hasPrimaryKey(
    ) {
        return this._has_primaryKey;
    }

    /**
     * Returns the value of field 'null'.
     * 
     * @return the value of field 'Null'.
     */
    public boolean isNull(
    ) {
        return this._null;
    }

    /**
     * Returns the value of field 'primaryKey'.
     * 
     * @return the value of field 'PrimaryKey'.
     */
    public boolean isPrimaryKey(
    ) {
        return this._primaryKey;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'code'.
     * 
     * @param code the value of field 'code'.
     */
    public void setCode(
            final java.lang.String code) {
        this._code = code;
    }

    /**
     * Sets the value of field 'comment'.
     * 
     * @param comment the value of field 'comment'.
     */
    public void setComment(
            final java.lang.String comment) {
        this._comment = comment;
    }

    /**
     * Sets the value of field 'default'.
     * 
     * @param _default
     * @param default the value of field 'default'.
     */
    public void setDefault(
            final java.lang.String _default) {
        this._default = _default;
    }

    /**
     * Sets the value of field 'foreignKey'.
     * 
     * @param foreignKey the value of field 'foreignKey'.
     */
    public void setForeignKey(
            final com.gssamerica.mdm.services.db.castor.ForeignKey foreignKey) {
        this._foreignKey = foreignKey;
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(
            final long id) {
        this._id = id;
        this._has_id = true;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'null'.
     * 
     * @param _null
     * @param null the value of field 'null'.
     */
    public void setNull(
            final boolean _null) {
        this._null = _null;
        this._has_null = true;
    }

    /**
     * Sets the value of field 'primaryKey'.
     * 
     * @param primaryKey the value of field 'primaryKey'.
     */
    public void setPrimaryKey(
            final boolean primaryKey) {
        this._primaryKey = primaryKey;
        this._has_primaryKey = true;
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(
            final com.gssamerica.mdm.services.db.castor.types.DataType type) {
        this._type = type;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.gssamerica.mdm.services.db.castor.Column
     */
    public static com.gssamerica.mdm.services.db.castor.Column unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.gssamerica.mdm.services.db.castor.Column) Unmarshaller.unmarshal(com.gssamerica.mdm.services.db.castor.Column.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
