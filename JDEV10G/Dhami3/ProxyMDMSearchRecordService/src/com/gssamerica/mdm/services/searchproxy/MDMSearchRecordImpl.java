package com.gssamerica.mdm.services.searchproxy;


import java.io.File;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;

import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MDMSearchRecordImpl {
    MessageFactory msgFactory=null;
    SOAPMessage requestMsg=null;
    SOAPPart soapPart=null;
    SOAPEnvelope envelope=null;
    SOAPBody soapBody =null;   
    DocumentBuilderFactory factory=null;
    DocumentBuilder builder=null;
    Document domDoc=null;
    TransformerFactory transformerFactory=null;
    Transformer transformer=null;
    SOAPConnectionFactory conFactry =null;
    SOAPConnection connection =null;
    Properties properties=null;
    public SOAPElement searchCustomerRecord(SOAPElement customerSearchRecordRequest) {
        System.out.println("\n *** Inside searchCustomerRecord() of ProxyMDMSearchRecord service *** \n");
        //System.out.println("\n Name** :"+customerSearchRecordRequest.getNodeName());
       InputStream istream=null;
        Document searchDOMDoc=null;
        try {            
               SOAPMessage searchCustomerSoapMessage=null;               
                // creating soap message from  SOAPElement
                searchCustomerSoapMessage=createSoapMessage(customerSearchRecordRequest);                  
                Source source=searchCustomerSoapMessage.getSOAPPart().getContent();                 
               // displayXMLFromSource(source);// display xml for testing purpose
                Document domDocument=createDomDocument(source);//creating dom document from soap message
                NodeList nodeList= domDocument.getElementsByTagName("CustomerPartyMaster");
                //  displayXMLFromDomDoc(domDocument);//display xml for testing purpose
                 istream=MDMSearchRecordImpl.class.getClassLoader().getResourceAsStream("application.properties");
         
                 properties=new Properties();
                 properties.load(istream);                
                 if(nodeList.getLength()>0){
                 //  File f=new File(properties.getProperty("Customer_AdvancedSearch_Transformation"));                 
                 //  searchDOMDoc=transformXMLUsingXSL(domDocument,f);// transformation to search service;                         
                  istream=MDMSearchRecordImpl.class.getClassLoader().getResourceAsStream(properties.getProperty("Customer_AdvancedSearch_Transformation"));
                  searchDOMDoc=transformXMLUsingXSL(domDocument,istream);
                }
                else{
                   // File f=new File(properties.getProperty("Customer_SearchAll_Transformation"));                 
                   // searchDOMDoc=transformXMLUsingXSL(domDocument,f);// transformation to search service;
                    istream=MDMSearchRecordImpl.class.getClassLoader().getResourceAsStream(properties.getProperty("Customer_SearchAll_Transformation"));
                    searchDOMDoc=transformXMLUsingXSL(domDocument,istream);
                }
                //System.out.println("\n  ** Search Transformation XML **");
                //displayXMLFromDomDoc(searchDOMDoc);
                searchCustomerSoapMessage=createSoapMessage(searchDOMDoc);
                //Set the destination
                String destination = properties.getProperty("MDMSearchRecordService.ServiceEndpoint");                
             
                Document customerSearchResult=sendSoapMessage(searchCustomerSoapMessage,destination);
                System.out.println("\n customerSearchResult** :\n");
                //displayXMLFromDomDoc(customerSearchResult);
                /* ** transform search result to esb response xsd**/
               //  File esbResponseXSLFile=new File(properties.getProperty("Customer_SearchResponse_Transformation"));                 
                istream=MDMSearchRecordImpl.class.getClassLoader().getResourceAsStream(properties.getProperty("Customer_SearchResponse_Transformation"));
             
                 Document  esbResponseDOMDoc=transformXMLUsingXSL(customerSearchResult,istream);// transformation to search service;
                 //System.out.println("\n  ** ESB Response Transformation **");
                 //displayXMLFromDomDoc(esbResponseDOMDoc);         
               return  createSoapMessage(esbResponseDOMDoc).getSOAPBody();
              
                 
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
     
    }
    
    public void displayXMLFromSource(Source source) {
           System.out.println("\n *** Inside displayXMLFromSource() of ProxyMDMSearchRecord service *** \n");
            //       Display the Soap Message 
           try{
            transformerFactory =TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            //Set the output for the transformation
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println();             
           }catch(TransformerConfigurationException e){
               e.printStackTrace();
           }catch(TransformerException e){
               e.printStackTrace();
           }
       }
       
    public void displayXMLFromDomDoc(Document doc) {
           System.out.println("\n *** Inside displayXMLFromDomDoc() of ProxyMDMSearchRecord service *** \n");
           
            //       Display the Soap Message 
            try{
            transformerFactory =TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            //Set the output for the transformation
            StreamResult result = new StreamResult(System.out);
            DOMSource domSource = new DOMSource(doc); 
            transformer.transform(domSource, result);
            System.out.println();
            } catch(TransformerConfigurationException e){
                          e.printStackTrace();
               }catch(TransformerException e){
                          e.printStackTrace();
               }
       }
       
    public Document createDomDocument(Source source){
        System.out.println("\n *** Inside createDomDocument() of ProxyMDMSearchRecord service *** \n");
        try{
        factory=DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        domDoc=builder.newDocument();        
        DOMResult dr = new DOMResult(domDoc) ;
        transformerFactory =TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
        transformer.transform(source, dr);  
        }catch(ParserConfigurationException e){
               e.printStackTrace();
         }catch(TransformerConfigurationException e){
               e.printStackTrace();
         }catch(TransformerException e){
               e.printStackTrace();
         }
        return domDoc;
      }
   public Document transformXMLUsingXSL(Document searchDOMDoc,InputStream iStream){
       System.out.println("\n *** Inside transformXMLUsingXSL() of ProxyMDMSearchRecord service *** \n");
        try{
        transformer = transformerFactory.newTransformer(new StreamSource(iStream));           
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        domDoc= builder.newDocument();     
        DOMResult dr = new DOMResult(domDoc) ;
        DOMSource domSource = new DOMSource(searchDOMDoc); 
        transformer.transform(domSource, dr);           
        }catch(ParserConfigurationException e){
               e.printStackTrace();
         }catch(TransformerConfigurationException e){
               e.printStackTrace();
         }catch(TransformerException e){
               e.printStackTrace();
         }
        return domDoc;        
   }
  public SOAPMessage createSoapMessage(Document domDoc) {
  
      System.out.println("\n *** Inside createSoapMessage() of ProxyMDMSearchRecord service *** \n");
      
     // creating a soap message using input soap element as body content
      try{
      msgFactory = MessageFactory.newInstance();
      requestMsg = msgFactory.createMessage();
      soapPart =requestMsg.getSOAPPart();
      envelope = soapPart.getEnvelope();
      soapBody =envelope.getBody(); 
      Node domDocNode=soapPart.importNode(domDoc.getDocumentElement(),true);
      soapBody.appendChild(domDocNode);
      }catch(SOAPException e){
               e.printStackTrace();
         }
      return requestMsg;
  }
    public SOAPMessage createSoapMessage(SOAPElement soapElement) {
    
        System.out.println("\n *** Inside createSoapMessage() using soap element of ProxyMDMSearchRecord service *** \n");
        
       // creating a soap message using input soap element as body content
        try{
        msgFactory = MessageFactory.newInstance();
        requestMsg = msgFactory.createMessage();
        soapPart =requestMsg.getSOAPPart();
        envelope = soapPart.getEnvelope();
        soapBody =envelope.getBody(); 
         soapBody.addChildElement(soapElement); 
        }catch(SOAPException e){
               e.printStackTrace();
         }
       return requestMsg;
    }
    public Document sendSoapMessage(SOAPMessage message,String destination) {
         
        Document searchResultDoc=null;
        try{
            conFactry = SOAPConnectionFactory.newInstance();
            connection = conFactry.createConnection();
            SOAPMessage reply = connection.call(requestMsg, destination);  
            connection.close();
            Source source=reply.getSOAPPart().getContent();
            searchResultDoc=createDomDocument(source);
        }catch(SOAPException e){
            e.printStackTrace();
        }
       return searchResultDoc;
    }
    
}
