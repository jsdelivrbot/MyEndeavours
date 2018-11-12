package com.emerson.execute.os.windows;

import java.io.*;

import java.util.ArrayList;

public class Executor {
    public Executor() {
    }

    private static ArrayList executeMPSTAT(String column){
        String sh = "/bin/sh";
        String cop = "-c";
        String cmd = "mpstat | awk '{ print $"+column+"}'";
        String[] commands = new String[]{sh,cop,cmd};
        ArrayList usage = new ArrayList();
        Process p = null;
        BufferedReader reader = null;
        try {
            p = Runtime.getRuntime().exec(commands);
            p.waitFor();
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                //usage.add(Integer.parseInt(line));
                line = reader.readLine();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                commands = null;
                p.destroy();
                reader.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return usage;
    }
    public static void main(String[] args) {
        ArrayList userUsage = null;
        ArrayList systemUsage = null;
        ArrayList totalUsage = new ArrayList();
        int accumulatedUsage = 0;
        int totalCPUs = 1;
        try {
            totalCPUs = Runtime.getRuntime().availableProcessors();
            System.out.println("Total Processes - "+totalCPUs);
            userUsage = executeMPSTAT("13");
            systemUsage = executeMPSTAT("14");
            for(int i=0;i<userUsage.size();i++){
                //accumulatedUsage = accumulatedUsage + ((Integer)userUsage.get(i)+(Integer)systemUsage.get(i));
                //totalUsage.add((Integer)userUsage.get(i)+(Integer)systemUsage.get(i));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Total CPU Usage(%) = "+totalUsage);
        System.out.println("Accumulated CPU Usage(%) = "+(float)accumulatedUsage/totalCPUs);
    }
}
