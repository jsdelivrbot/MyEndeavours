/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: ForeignKey.java,v 1.2 2008/07/02 10:59:28 inderpal Exp $
 */

package com.gssamerica.mdm.services.db.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ForeignKey.
 * 
 * @version $Revision: 1.2 $ $Date: 2008/07/02 10:59:28 $
 */
public class ForeignKey implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _referenceTable.
     */
    private com.gssamerica.mdm.services.db.castor.ReferenceTable _referenceTable;


      //----------------/
     //- Constructors -/
    //----------------/

    public ForeignKey() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'referenceTable'.
     * 
     * @return the value of field 'ReferenceTable'.
     */
    public com.gssamerica.mdm.services.db.castor.ReferenceTable getReferenceTable(
    ) {
        return this._referenceTable;
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
     * Sets the value of field 'referenceTable'.
     * 
     * @param referenceTable the value of field 'referenceTable'.
     */
    public void setReferenceTable(
            final com.gssamerica.mdm.services.db.castor.ReferenceTable referenceTable) {
        this._referenceTable = referenceTable;
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
     * com.gssamerica.mdm.services.db.castor.ForeignKey
     */
    public static com.gssamerica.mdm.services.db.castor.ForeignKey unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.gssamerica.mdm.services.db.castor.ForeignKey) Unmarshaller.unmarshal(com.gssamerica.mdm.services.db.castor.ForeignKey.class, reader);
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
