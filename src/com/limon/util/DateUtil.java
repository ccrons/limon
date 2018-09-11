/**
 * 
 */
package com.limon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author gqf
 *
 * 日期工具类
 * 2015-2-26 上午09:15:03
 */
public class DateUtil {
	public static String getToday(){
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	public static String getTodayTime(){
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(c.getTime());
	}
	
	public static String getLastWeek(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE,-7);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	public static String getLastDayTime(int daynum){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE,daynum);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(c.getTime());
	}
	public static String getLastDay(int daynum){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE,daynum);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	public static String getTwoDay(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE,-2);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	public static String dateToStr(Date date){
		String str = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = sdf.format(date);
		return str;
	}
	public static Date strToDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
