package eth_sch_emailreport;

    import eth_sch_emailreport.Transactionreport;
    import java.io.IOException;
    import java.util.logging.Logger;
    import javax.servlet.ServletConfig;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
     import org.quartz.CronTrigger;
    import org.quartz.JobDetail;
    import org.quartz.Scheduler;
    import org.quartz.impl.StdSchedulerFactory;

public class TransactionreportScheduler extends HttpServlet {
    private static final String CONTENT_TYPE = 
               "text/html; charset=windows-1252";
           Logger logger;
           public void init(ServletConfig config) throws ServletException {
               super.init(config);
               logger = Logger.getLogger(this.getClass().getName());
               try {
                   StdSchedulerFactory schedFact = 
                       new StdSchedulerFactory("bpel_quartz.properties");
                   Scheduler sched = schedFact.getScheduler();
                   logger.info("Starting Quartz Schduler BPELScheduler");
                   sched.start();

                   JobDetail jd = 
                       new JobDetail("Transactionreport Job", "BPEL Job", Transactionreport.class);

                   CronTrigger cronTrigger = 
                       new CronTrigger("Transactionreport Job", "BPEL Job");

                   String cronExpr = null;
                   // Get the cron Expression as an Init parameter
                   cronExpr = getInitParameter("cronExpr");
                   logger.info("Cron Express for Transactionreport Job:"+cronExpr);
                   cronTrigger.setCronExpression(cronExpr);
                   logger.info("Scheduling Job 'Transactionreport Job'");
                   sched.scheduleJob(jd, cronTrigger);
                   logger.info("Job 'Transactionreport Job' scheduled.");


               } catch (Exception e) {
                   e.printStackTrace();
                   throw new ServletException(e);
               }

           }
           public void destroy() {
               try {
                   StdSchedulerFactory schedFactory = new StdSchedulerFactory();
                   Scheduler sched = schedFactory.getScheduler("BPELScheduler");
                   if (sched != null) {
                       sched.shutdown();
                   }
               } catch (Exception e) {
                   logger.warning("Quartz Scheduler 'BPELSchduler' failed to shutdown cleanly: " + 
                                  e.toString());
                   e.printStackTrace();
               }
               logger.info("Quartz Scheduler 'BPELSchduler' successful shutdown.");
           }
           public void service(HttpServletRequest request, 
                               HttpServletResponse response) throws ServletException, 
                                                                    IOException {
               response.setContentType(CONTENT_TYPE);
           }
       
}
