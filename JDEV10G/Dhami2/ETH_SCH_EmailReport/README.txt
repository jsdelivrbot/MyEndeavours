
Description:
------------

  This sample illustrates the use of Quartz to create Cron type schedules for 
  BPEL Processes.

  There are two Java Classes in this sample
     1. BPELScheduleServlet - The Servlet that will be used to create the 
                              scheduler and the schedules
     2. BPELHelloWorldJob - The implementation of a Quartz Job which initiates 
                            the HelloWorld BPEL process

  The web.xml and two properties files  are used to externalize configurations
     1. web.xml - The deployment descriptor which registers the 
                  BPELSechduleServlet, with the Cron Expresssion specified as 
                  an init parameter 'cronExpr'
     2. bpel_quartz.properties - The properties file used by the Quartz 
                                 Scheduler
     3. context.properties - The context information needed to initiate the 
                             BPEL process
                             
  The shared library 'oracle.bpel.common' is imported for this application 
  (specified in the orion-application.xml deployment decriptor file) since 
  this sample is using the quartz libraries that is included in the BPEL 
  Process Manager, and also the BPELHellowWorldJob will use BPEL Client JAVA 
  API to invoke the HelloWorld process. 

Getting started:
----------------

 1. Start the JDeveloper by double-clicking on the "JDeveloper" 
    icon on your desktop.

 2. If you haven't created a workspace in JDeveloper, you will need
    to first create an empty workspace. To do this, Select the 
    File Menu -> New, double click the "Workspace" option,
    Un-check the option for "New Empty Project" and click OK 

 3. Now you can open up the 'BPEL_Schedule' project for this sample 
    by navigating to the corresponding Samples folder and opening the 
    .JPR file. 


To compile and deploy:
----------------------

 1. Follow the instruction in tutorials/101.HelloWorld to deploy the HelloWorld
    process

 2. Deploy from developer prompt, run 'ant' in the current
    directory.  This will build, compile and deploy the webapp BPELSchedule


To test:
--------

 1. Login to the BPEL Console 

 2. Check the instances tab to see if the HelloWorld process is initiated 
    periodically according to the Cron Expression specified in web.xml
