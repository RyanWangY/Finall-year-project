package yuan.project.utils;

import java.util.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.Calendar; 

import java.text.SimpleDateFormat; 

public class Gettime {

	public String gettime() {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String hehe = dateFormat.format( now ); 
		
		System.out.println(hehe);
		return hehe;
	}
	
	public static void main(String[] args) throws ParseException{ 
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String hehe = dateFormat.format( now ); 
		Date s= java.sql.Date.valueOf(hehe);
		System.out.println(s);
	}
}