Description:
------------

This sample demonstrates how to use Dynamic JCA Partnerlinks in BPEL, where
the dynamic part is the jca:address location attribute, for example

    <jca:address location="eis/ftp/anonymous" />

In short that means that a Invoke (run-)time the BPEL process can decide
which JCA Connection Factory (JNDI entry) to use, thus overriding the 
(default) JNDI location present in the WSDL (in the jca:address), by updating
the partnerlink with a WS-Adressing compliant XML fragment, for example

  <copy>
    <from>
      <EndpointReference xmlns="http://schemas.xmlsoap.org/ws/2003/03/addressing">
        <Address>eis/ftp/anonymous</Address>
      </EndpointReference>
    </from>
    <to partnerLink="FtpPutFileBLOBPayload"/>
  </copy>

Notice that the value of the jca:address location attribute is the text node
value of the <Address> element. If the value is blank, the (default) value
in the WSDL will be used.

One prerequisite is thus that the dynamically chosen JNDI names at runtime
must already be defined and available in oc4j-ra.xml for the particular
JCA resource adapter servicing the partnerlink.

This sample uses the FTP adapter to demonstrate this feature. It assumes the
existence of the following 3 FTP accounts on the server "my.ftpsrv.com".

   - anonymous/jjoe@acme.com
   - oracle/welcome
   - scott/tiger

Each FTP account must have a directory named "outputdir" in the home directory. 

In the "setup" folder a sample oc4j-ra.xml file is supplied. Change the host,
port and account settings to match your environment.


To compile and deploy:
----------------------

1. run obant.bat (or obant.sh on Unix/Linux).


To test:
--------

1. Copy the file "oralogo.gif" to the directory "inputDir"

2. Log in to each FTP account and verify the presence of a new file 
   in the "outputdir" directory.

