/**
 * 
 */
package com.nixsolutions;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @author mixeyes
 *
 */
public class TimeFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Using Java7\n");
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,Locale.CANADA);
		System.out.println("CANADA: "+df.format(new Date())+"\n");
		
		df = DateFormat.getDateInstance(DateFormat.FULL,Locale.GERMAN);
		System.out.println("GERMAN: "+df.format(new Date())+"\n");
		
		Locale vietnamese = new Locale("vi", "vi_VN");
		df = DateFormat.getDateInstance(DateFormat.FULL,vietnamese);
		System.out.println("Vietnamese: "+df.format(new Date())+"\n");
		
		Locale pakistan = new Locale("en", "PAK");
		df = DateFormat.getDateInstance(DateFormat.FULL,pakistan);
		System.out.println("Pakistan: "+df.format(new Date())+"\n");
	

		System.out.println("Using Java8\n");
		ZoneId zoneId =ZoneId.of("Europe/Berlin");
		ZonedDateTime time = ZonedDateTime.now(zoneId);
		System.out.println("GERMAN: "+time.format(DateTimeFormatter.RFC_1123_DATE_TIME)+"\n");
		
		zoneId =ZoneId.of("Canada/Central");
		time = ZonedDateTime.now(zoneId);
		System.out.println("CANADA: "+time.format(DateTimeFormatter.RFC_1123_DATE_TIME)+"\n");
		
		zoneId =ZoneId.of("Asia/Karachi");
		time = ZonedDateTime.now(zoneId);
		System.out.println("Pakistan: "+time.format(DateTimeFormatter.RFC_1123_DATE_TIME)+"\n");
		
		zoneId =ZoneId.of("Asia/Ho_Chi_Minh");
		time = ZonedDateTime.now(zoneId);
		System.out.println("Vietnamese: "+time.format(DateTimeFormatter.RFC_1123_DATE_TIME)+"\n");
}

}
