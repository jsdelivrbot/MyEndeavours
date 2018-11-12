/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: Table.java,v 1.2 2008/07/02 10:59:28 inderpal Exp $
 */

package com.gssamerica.mdm.services.db.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Table.
 * 
 * @version $Revision: 1.2 $ $Date: 2008/07/02 10:59:28 $
 */
public class Table implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _code.
     */
    private java.lang.String _code;

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _comment.
     */
    private java.lang.String _comment;

    /**
     * Field _columnList.
     */
    private java.util.List _columnList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Table() {
        super();
        this._columnList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vColumn
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addColumn(
            final com.gssamerica.mdm.services.db.castor.Column vColumn)
    throws java.lang.IndexOutOfBoundsException {
        this._columnList.add(vColumn);
    }

    /**
     * 
     * 
     * @param index
     * @param vColumn
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addColumn(
            final int index,
            final com.gssamerica.mdm.services.db.castor.Column vColumn)
    throws java.lang.IndexOutOfBoundsException {
        this._columnList.add(index, vColumn);
    }

    /**
     * Method enumerateColumn.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateColumn(
    ) {
        return java.util.Collections.enumeration(this._columnList);
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
     * Method getColumn.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.gssamerica.mdm.services.db.castor.Column at the given
     * index
     */
    public com.gssamerica.mdm.services.db.castor.Column getColumn(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._columnList.size()) {
            throw new IndexOutOfBoundsException("getColumn: Index value '" + index + "' not in range [0.." + (this._columnList.size() - 1) + "]");
        }
        
        return (com.gssamerica.mdm.services.db.castor.Column) _columnList.get(index);
    }

    /**
     * Method getColumn.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.gssamerica.mdm.services.db.castor.Column[] getColumn(
    ) {
        com.gssamerica.mdm.services.db.castor.Column[] array = new com.gssamerica.mdm.services.db.castor.Column[0];
        return (com.gssamerica.mdm.services.db.castor.Column[]) this._columnList.toArray(array);
    }

    /**
     * Method getColumnCount.
     * 
     * @return the size of this collection
     */
    public int getColumnCount(
    ) {
        return this._columnList.size();
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
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
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
     * Method iterateColumn.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateColumn(
    ) {
        return this._columnList.iterator();
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
     */
    public void removeAllColumn(
    ) {
        this._columnList.clear();
    }

    /**
     * Method removeColumn.
     * 
     * @param vColumn
     * @return true if the object was removed from the collection.
     */
    public boolean removeColumn(
            final com.gssamerica.mdm.services.db.castor.Column vColumn) {
        boolean removed = _columnList.remove(vColumn);
        return removed;
    }

    /**
     * Method removeColumnAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.gssamerica.mdm.services.db.castor.Column removeColumnAt(
            final int index) {
        java.lang.Object obj = this._columnList.remove(index);
        return (com.gssamerica.mdm.services.db.castor.Column) obj;
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
     * 
     * 
     * @param index
     * @param vColumn
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setColumn(
            final int index,
            final com.gssamerica.mdm.services.db.castor.Column vColumn)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._columnList.size()) {
            throw new IndexOutOfBoundsException("setColumn: Index value '" + index + "' not in range [0.." + (this._columnList.size() - 1) + "]");
        }
        
        this._columnList.set(index, vColumn);
    }

    /**
     * 
     * 
     * @param vColumnArray
     */
    public void setColumn(
            final com.gssamerica.mdm.services.db.castor.Column[] vColumnArray) {
        //-- copy array
        _columnList.clear();
        
        for (int i = 0; i < vColumnArray.length; i++) {
                this._columnList.add(vColumnArray[i]);
        }
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
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
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
     * com.gssamerica.mdm.services.db.castor.Table
     */
    public static com.gssamerica.mdm.services.db.castor.Table unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.gssamerica.mdm.services.db.castor.Table) Unmarshaller.unmarshal(com.gssamerica.mdm.services.db.castor.Table.class, reader);
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
