package com.dtr.oas.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by safayat on 12/12/15.
 */
public class CommonUtils {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static int nullToZero(Integer number){
        if(number == null) return 0;
        return number;
    }
    public static long nullToZero(Long number){
        if(number == null) return 0;
        return number;
    }

    public static double nullToZero(Double number){
        if(number == null) return 0;
        return number;
    }
    public static float nullToZero(Float number){
        if(number == null) return 0;
        return number;
    }

    public static int getCurrentMonth(){
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return now.get(Calendar.MONTH);
    }

    public static Date getCurrentMonthDate(int day){
        if(day<0) day = 0;
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        if(day > now.getActualMaximum(Calendar.DAY_OF_MONTH)) day = now.getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar newCalender = Calendar.getInstance();
        newCalender.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), day);
        return newCalender.getTime();
    }

    public static Date getMinimumMonthDate(){
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar newCalender = Calendar.getInstance();
        newCalender.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.getActualMinimum(Calendar.DAY_OF_MONTH));
        return newCalender.getTime();
    }

    public static Date getMaximumMonthDate(){
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar newCalender = Calendar.getInstance();
        newCalender.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.getActualMaximum(Calendar.DAY_OF_MONTH));
        return newCalender.getTime();
    }

    public static String toDateString(Date date){
        return date == null ? null : simpleDateFormat.format(date);
    }
    
    
    public static List<String> getDepartments() {
		
    	List<String> departmentList = new ArrayList<>();
    	
    	departmentList.add("Cable");
    	departmentList.add("Greenroad-Cable");
    	departmentList.add("Greenroad-Support");
    	departmentList.add("Gulshan-Support");
    	departmentList.add("Hosting");
    	departmentList.add("Maintainance");
    	departmentList.add("Motijheel-Calbe");
    	departmentList.add("Motijheel-Suppoert");
    	departmentList.add("NOC-GCL");
    	departmentList.add("Sales");
    	departmentList.add("Uttora-Support");
    	
    	return departmentList;
    	
	}
    
    
    public static List<String> getHelpTopicList() {
		
    	List<String> helpTopicList = new ArrayList<>();
    	
    	helpTopicList.add("Bandwidth Issue");
    	helpTopicList.add("Connection Problem");
    	helpTopicList.add("Domain Hosting");
    	helpTopicList.add("FX Down");
    	helpTopicList.add("General Inquiry");
    	helpTopicList.add("Internet Issue");
    	helpTopicList.add("Lan Issue");
    	helpTopicList.add("Mail Issue");
    	helpTopicList.add("Mikrotic Issue");
    	helpTopicList.add("New Activation");
    	helpTopicList.add("Other");
    	helpTopicList.add("RF Problem");
    	helpTopicList.add("Shifting");
    	
    	return helpTopicList;
    	
	}
    
    
    public static List<String> getSLAPlan() {
		
    	List<String> list = new ArrayList<>();
    	
    	list.add("Default SLA(48 Hours-Active)");
    	
    	return list;
    	
	}
    
    
    public static List<String> getCannedResponseList() {
		
    	List<String> cannedResponseList = new ArrayList<>();
    	
    	cannedResponseList.add("Sample (With Variables)");
    	cannedResponseList.add("What is osTicket(Sample)?");
    	
    	return cannedResponseList;
    	
	}

}
