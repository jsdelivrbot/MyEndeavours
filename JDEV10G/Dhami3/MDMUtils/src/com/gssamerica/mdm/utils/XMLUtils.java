package com.gssamerica.mdm.utils;

import java.io.IOException;
import java.io.Reader;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtils {
    public XMLUtils() {
    }
    
    public String getXMLDocument(Document doc) {
        TransformerFactory tf = null;
        Transformer transformer = null;
        String xml = null;
        StringWriter sw = null;
        StreamResult sr = null;
        DOMSource domSource = null;
        
        try {
            tf = TransformerFactory.newInstance();
            domSource = new DOMSource(doc);
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            transformer.setOutputProperty  (OutputKeys.OMIT_XML_DECLARATION, "yes");
            //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            sw = new StringWriter();
            sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            xml = sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
    
    public Document createDocument(String xml){
        DocumentBuilderFactory docBuilderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document document = null;
        Reader reader = null;
        InputSource xmlSource = null;
        try {
            reader = new java.io.StringReader(xml);
            xmlSource = new InputSource(reader);
            docBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = docBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlSource);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return document;
    }
    
    public Document createDocument(Element element){
        Document document = null;
        String xml = null;
        try {
            xml = getXMLElement(element);
            document = createDocument(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
    
    public String getXMLElement(Element element) {
        TransformerFactory tf = null;
        Transformer transformer = null;
        String xml = null;
        StringWriter sw = null;
        StreamResult sr = null;
        DOMSource domSource = null;
        //transformer.setOutputProperty  (OutputKeys.OMIT_XML_DECLARATION, "yes");
        try {
            tf = TransformerFactory.newInstance();
            domSource = new DOMSource(element);
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            sw = new StringWriter();
            sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            xml = sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
