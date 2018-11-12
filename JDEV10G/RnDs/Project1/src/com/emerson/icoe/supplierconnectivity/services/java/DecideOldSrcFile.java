package com.emerson.icoe.supplierconnectivity.services.java;


import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class DecideOldSrcFile {

    public DecideOldSrcFile()
    {
        System.out.println("DecideOldSrcFile instantiated");
    }
    
    private String selectOldFile(String strDir) throws RuntimeException
    {
    /*Logic of this method -
     * This method will check all files in specified directory for timestamp &
     * will note down the old file's timestamp & its complete path.
     * Finally it will return the OLD file absolute path to the caller.
     * 
     * Components used - File I/O, Collection framework
     * */
       File fDir = new File(strDir);
       String strOldFile = null;
       SortedSet<Long> srtSet = null;
       Map<String,Long> mapFiles = null;
       List<String> lstOldFiles = new ArrayList<String>();
       
        if(!fDir.exists())
        {
           // System.out.println("File / directory does not exists");
            return "";
        }
        //System.out.println("File / directory exists\n");
        if(fDir.isDirectory())
        {
            String files[] = fDir.list();
           // long fileModifiedTimes[] = null;
           
            if(files.length>0)
            {
                strOldFile = "";
                srtSet = new TreeSet<Long>();
                mapFiles = new HashMap<String,Long>();
            }
            for (int i = 0; i < files.length ; i++)
            {
                File fCurrFile = new File("d:/input/"+files[i]);
                long fModifiedTime = fCurrFile.lastModified();
                
                mapFiles.put(fCurrFile.getAbsolutePath(),fModifiedTime);
                System.out.println("Current File name : "+fCurrFile+" --- last modified time is : "+fCurrFile.lastModified());
                //fileModifiedTimes[i] = fModifiedTime;
                /*if(i>0)
                {
                    if(fileModifiedTimes[i]>fileModifiedTimes[i-1])
                        maxValue = fileModifiedTimes[i];
                }*/
                srtSet.add(fModifiedTime);
                
                //List<Long> lst = new ArrayList<Long>();
                //lst.add(fModifiedTime);
                
            
                
                if(fModifiedTime >= srtSet.last().longValue())//means compare it with the largest value from the set
                {// it means the current file is older, so save its absolute path in a string
                    System.out.println("current file's modified time : "+fModifiedTime);
                    System.out.println("last file's modified time : "+srtSet.first().longValue());
                    
                    if(fModifiedTime == srtSet.first().longValue())
                    {// it means more than 1 old files with same time stamp
                    // so maintain a collection of all old files with same timestamp
                    //& also clear this collection when any new file with older timestamp comes than these once
                    //because logically the currently hold file names are not needed as these are not older once.
                        lstOldFiles.add(strOldFile);//because as of now this string contains last read file's name
                    }
                    strOldFile = fCurrFile.getAbsolutePath();
                    //System.out.println("old file : "+strOldFile+"\n");
                }
                                
//                if(i==3)
//                {
//                    //srtSet.add(new Long("1274706034281"));
//                    //System.out.println("\n largest element is : "+srtSet.last());
//                    //System.out.println("\n smallest element is : "+srtSet.first());
//                    System.out.println("\n size of the sorted set is : "+srtSet.size());
//                    //System.out.println("\n size of the list is : "+lst.size());
//                    //System.out.println("trying to fetch key using value : "+mapFiles.get("1274706034281"));
//                }
                if(lstOldFiles.size()>1)
                {
                    System.out.println("list size is : "+lstOldFiles.size());
                    System.out.println("More old files found with same timestamp");
                }
                
            }
            
            System.out.println("\n directory successfully scanned & old file is : "+strOldFile);
            return strOldFile;
            
            /*Long lng[] = new Long[200];
            for(int i=0; i<fileModifiedTimes.length; i++)
            {
                lng[i] = new Long(fileModifiedTimes[i]);
            }*/
            
            //return fileModifiedTimes.
        }
        else
            System.out.println("not a valid directory");
            
        return "";
   }

    /*private readFile(String fName) throws RuntimeException
    {
        
    }*/

    public static void main(String[] args) {
        System.out.println("main started");
        DecideOldSrcFile fileReadDemo = new DecideOldSrcFile();
        String strFileName = fileReadDemo.selectOldFile("d:/input");
        boolean boolStatus = false;
        ParseFlatFile parseFileObj = new ParseFlatFile();
        boolStatus = parseFileObj.parseFile(strFileName);
        System.out.println(strFileName+" parsed successfully");
        System.out.println("main over :)");
    }
}
