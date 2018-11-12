package eth_sch_emailreport;

import com.oracle.bpel.client.Locator;
import com.oracle.bpel.client.NormalizedMessage;
import com.oracle.bpel.client.delivery.IDeliveryService;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import javax.naming.Context;

import org.w3c.dom.Element;

public class Transactionreport implements Job{
    Logger logger;
    public Transactionreport() {
    }
    public void execute(JobExecutionContext jobExecutionContext) {
           logger.fine("Call ETH_BPEL_Email_Transaction_Report  BPEL Process");
           try {

               Properties props = new Properties();
              // props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("context.properties"));
                                   props.put(Context.PROVIDER_URL, "opmn:ormi://10.238.4.140:12201:elshap1_Ofm_soa/orabpel");
                                               props.put(Context.INITIAL_CONTEXT_FACTORY, "com.evermind.server.rmi.RMIInitialContextFactory");
                                               props.put(Context.SECURITY_PRINCIPAL, "oc4jadmin");
                                               props.put(Context.SECURITY_CREDENTIALS, "welc0me");
                                               props.put("dedicated.connection","true");
               Locator locator = new Locator("ETHServices", "bpel", props);
               IDeliveryService deliveryService =
                   (IDeliveryService)locator.lookupService(IDeliveryService.SERVICE_NAME);

               // construct the normalized message and send to BPEL server
                String xml =
                    "<ns1:input xmlns:ns1=\"http://xmlns.emrsn.com/ETH_BPEL_Email_Transaction_Report\">Joe</ns1:input>";

               NormalizedMessage nm = new NormalizedMessage();

               nm.addPart("payload", xml);
              // deliveryService.post("ETH_BPEL_Email_Transaction_Report", "process", nm);
               deliveryService.request("ETH_BPEL_Email_Transaction_Report", "process", nm);
               //NormalizedMessage res = deliveryService.request("ETH_BPEL_Email_Transaction_Report", "process", nm);
               //  Map payload = res.getPayload();
              //  Element partEl = (Element) payload.get("payload");
            // String str1=partEl.toString();
                                //   System.out.println("str1 is "+str1 );

           } catch (Exception e) {
               e.printStackTrace();
           }
           logger.fine("Call ETH_BPEL_Email_Transaction_Report Process Finished");
       }
}
