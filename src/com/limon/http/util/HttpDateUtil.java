package com.limon.http.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class HttpDateUtil {
    private static final Logger log = Logger.getLogger(HttpDateUtil.class);
	public static SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Calendar cal; 
	/**
	 * 获取当前日期对象
	 * @return
	 */
	public static Date getNowDate(){
		cal=Calendar.getInstance();
		return cal.getTime();
	}
	
	/**
	 * 获取与当前日期差值的日期
	 * @param date
	 * @return
	 */
	public static Date addDate(int date){
		cal=Calendar.getInstance();
		cal.add(Calendar.DATE, -date); 
		return cal.getTime();
	}
	
	/**
     * 获取当前时间自定义字符串N位    格式字符串： yyyyMMddHHmmss
     * @param @return
     * @return String
     * @throws
     */
    public static String getNowDateString(String format){
        SimpleDateFormat df=new SimpleDateFormat(format);
        return df.format(getNowDate());     
    }
	
	/**
	 * 获取当前时间字符串 14位 yyyyMMddHHmmss
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getNowDateString(){
	    SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
	    return df.format(getNowDate());	    
	}
	
	/**
	 * @Title: parse 
	 * @Description: TODO
	 * @param @param format
	 * @param @param time
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date parse(String time, String format){
        Date date = null;
        try{
            SimpleDateFormat df=new SimpleDateFormat(format);
            date = df.parse(time);
        }catch(Exception e){
            date = new Date();
            log.error("字符串转换成时间类型失败  error:" + time);
            e.printStackTrace();
        }
        return date;
        
    }
}
