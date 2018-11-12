package com.emerson.eth.scheduler;

import com.emerson.eth.scheduler.proxy.BPEL_HelloWorld;
import com.emerson.eth.scheduler.proxy.Bpel_helloworld_ep_ep;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.ws.WebServiceRef;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloWorldJob implements Job {
  
  @WebServiceRef
  private static Bpel_helloworld_ep_ep bpel_helloworld_ep_ep;
  
  public HelloWorldJob() {
    super();
    bpel_helloworld_ep_ep = new Bpel_helloworld_ep_ep();
  }

  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    System.out.println("HelloWorldJob started");
    try {
    bpel_helloworld_ep_ep = new Bpel_helloworld_ep_ep();
    BPEL_HelloWorld helloWorldProcess = bpel_helloworld_ep_ep.getBPEL_HelloWorld_pt();
    // Add your code to call the desired methods.
    System.out.println("HelloWorld Response: " + helloWorldProcess.process("SOAScheduler@" + df.format(date)));
    } catch (Exception e) {
    System.out.println("HelloWorld Process failed: " + e.toString());
    e.printStackTrace();
    }
  }
}
