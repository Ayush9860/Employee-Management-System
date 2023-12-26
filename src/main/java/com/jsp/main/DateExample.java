package com.jsp.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateExample {

	public static void main(String[] args) throws ParseException {
		
		Date d1 = new Date();
		System.out.println(d1);
		
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		System.out.println(d2);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the date in dd-mm-yyyy");
		String d3 = sc.next();
		System.out.println(d3);
		
		SimpleDateFormat s1 = new SimpleDateFormat("dd-MM-yyyy");
		Date d4 = s1.parse(d3);
		
		java.sql.Date d5 = new java.sql.Date(d4.getTime());
		System.out.println(d5);
		
	}
}
