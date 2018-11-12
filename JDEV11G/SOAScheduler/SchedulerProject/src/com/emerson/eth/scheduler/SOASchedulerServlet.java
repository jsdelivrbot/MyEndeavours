package com.emerson.eth.scheduler;

import org.quartz.JobDetail;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

public class SOASchedulerServlet extends HttpServlet {

  StdSchedulerFactory schedFact;
  Scheduler sched;

  public SOASchedulerServlet() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try {
      schedFact = new StdSchedulerFactory("soa_quartz.properties");
      sched = schedFact.getScheduler();
      System.out.println(this.getClass().getName() + " started");
      /*
  // Add the holiday calendar to the schedule
  AnnualCalendar holidays = new AnnualCalendar();
  // fourth of July (July 4)
  Calendar fourthOfJuly = new GregorianCalendar(2011, 7, 4);
  holidays.setDayExcluded(fourthOfJuly, true);
  // halloween (Oct 31)
  Calendar halloween = new GregorianCalendar(2011, 9, 31);
  holidays.setDayExcluded(halloween, true);
  // christmas (Dec 25)
  Calendar christmas = new GregorianCalendar(2011, 11, 25);
  holidays.setDayExcluded(christmas, true);
  // tell the schedule about our holiday calendar
  sched.addCalendar("holidays", holidays, false, false);
  */
      sched.start();
      JobDetail jd = new JobDetail(JOB_NAME, GROUP_NAME, HelloWorldJob.class);
      CronTrigger cronTrigger = new CronTrigger(TRIGGER_NAME, GROUP_NAME);
      String cronExpr = null;
      // Get the cron Expression as an Init parameter
      cronExpr = getInitParameter("cronExpr");
      System.out.println(this.getClass().getName() + " Cron Expression for " +
                         JOB_NAME + ":" + cronExpr);
      cronTrigger.setCronExpression(cronExpr);
      System.out.println(this.getClass().getName() + " Scheduling Job " +
                         JOB_NAME);
      sched.scheduleJob(jd, cronTrigger);
      System.out.println(this.getClass().getName() + " Job " + JOB_NAME +
                         " scheduled.");
    } catch (Exception e) {
      System.out.println(this.getClass().getName() + e.getLocalizedMessage());
      e.printStackTrace();
    }
  }

  public void destroy() {
    try {
      if (sched != null) {
        sched.unscheduleJob(TRIGGER_NAME, JOB_NAME);
        sched.shutdown();
      }
    } catch (Exception e) {
      System.out.println(this.getClass().getName() + " failed to shutdown: " +
                         e.toString());
      e.printStackTrace();
    }
    System.out.println(this.getClass().getName() + " stopped");
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
                                                         IOException {
    PrintWriter ajax = new PrintWriter(response.getOutputStream());
    // logger.warning("get");
    String action = request.getParameter("action");
    if ("single".equals(action)) {
      if (sched != null) {
        try {
          Trigger trigger =
            new SimpleTrigger("SOASingleTrigger", GROUP_NAME, new Date());
          trigger.setJobName(JOB_NAME);
          trigger.setJobGroup(GROUP_NAME);
          // Schedule the trigger
          sched.scheduleJob(trigger);
        } catch (Exception e) {
          System.out.println(this.getClass().getName() +
                             e.getLocalizedMessage());
          e.printStackTrace();
        }
      }
    } else if ("start".equals(action)) {
      if (sched != null) {
        try {
          JobDetail jd =
            new JobDetail(JOB_NAME, GROUP_NAME, HelloWorldJob.class);
          CronTrigger cronTrigger = new CronTrigger(TRIGGER_NAME, GROUP_NAME);

          // Get the cron Expression as an Init parameter
          String cronExpr = getInitParameter("cronExpr");
          System.out.println(this.getClass().getName() +
                             " Cron Expression for " + JOB_NAME + ":" +
                             cronExpr);
          cronTrigger.setCronExpression(cronExpr);
          System.out.println(this.getClass().getName() + " Scheduling Job " +
                             JOB_NAME);
          sched.scheduleJob(jd, cronTrigger);
          System.out.println(this.getClass().getName() + " Job " + JOB_NAME +
                             " scheduled.");
        } catch (Exception e) {
          System.out.println(this.getClass().getName() +
                             e.getLocalizedMessage());
          e.printStackTrace();
        }
      }
    } else if ("stop".equals(action)) {
      if (sched != null) {
        try {
          sched.unscheduleJob(TRIGGER_NAME, GROUP_NAME);
          System.out.println(this.getClass().getName() + " stopped");
        } catch (Exception e) {
          System.out.println(this.getClass().getName() +
                             " failed to shutdown: " + e.toString());
          e.printStackTrace();
        }
      }
    }

    ajax.println("<html>");
    ajax.println(" <head>");
    ajax.println(" <title>SOAScheduler - Web Interface</title>");
    ajax.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"css/mystyle.css\"></link>");
    ajax.println(" </head>");
    ajax.println(" <body onload='startAjaxPeriodicalUpdater()'>");
    ajax.println(" <h2>");
    ajax.println(" SOAScheduler @");
    ajax.println(" <span class=\"server\">" +
                 System.getProperty("weblogic.Name") + "</span>");
    ajax.println(" </h2>");
    ajax.println("<table id=\"events_table\" class=\"events_table\" width=\"100%\">");
    ajax.println("<tbody>");
    String[] jobGroups;
    String[] jobsInGroup;
    String[] triggerGroups;
    Trigger[] jobTriggers;
    String[] calendersList;
    AnnualCalendar calen;
    CronTrigger cronTrigger;
    int i, j, k;
    try {
      jobGroups = sched.getJobGroupNames();
      triggerGroups = sched.getTriggerGroupNames();
      calendersList = sched.getCalendarNames();
      for (i = 0; i < calendersList.length; i++) {
        calen = (AnnualCalendar)sched.getCalendar(calendersList[i]);
        //System.out.println("Calendar: " + calendersList[i]);
        ajax.printf("Calendar: " + calendersList[i]);
      }
      for (i = 0; i < jobGroups.length; i++) {
        // System.out.println("Group: " + jobGroups[i] + " contains the following jobs");
        jobsInGroup = sched.getJobNames(jobGroups[i]);
        for (j = 0; j < jobsInGroup.length; j++) {
          // System.out.println("- " + jobsInGroup[j]);
          jobTriggers = sched.getTriggersOfJob(jobsInGroup[j], jobGroups[i]);
          for (k = 0; k < jobTriggers.length; k++) {
            // System.out.println("- " + triggersInGroup[j]);
            if ("org.quartz.CronTrigger".equals(jobTriggers[k].getClass().getName())) {
              cronTrigger = (CronTrigger)jobTriggers[k];
              ajax.printf("<tr class=\"%s\"><td align=\"left\">Trigger: %s</td><td>Next: %s</td><td>Last: %s</td><td>Cron: %s</td></tr>",
                          "events", jobTriggers[k].getName(),
                          jobTriggers[k].getNextFireTime(),
                          jobTriggers[k].getPreviousFireTime(),
                          cronTrigger.getCronExpression());
            } else {
              ajax.printf("<tr class=\"%s\"><td align=\"left\">Trigger: %s</td><td>Next: %s</td></tr>",
                          "events", jobTriggers[k].getName(),
                          jobTriggers[k].getNextFireTime());
            }
          }
        }
      }
    } catch (Exception e) {
      System.out.println("SOASchdulerServlet failed: " + e.toString());
      e.printStackTrace();
    }
    ajax.println("</tbody>");
    ajax.println("</table>");
    ajax.flush();
  }
  static final String GROUP_NAME = "SOAGroup";
  static final String TRIGGER_NAME = "SOATrigger";
  static final String JOB_NAME = "SOAJob";
  static final String TARGET_PAGE = "index.jsp";
}
