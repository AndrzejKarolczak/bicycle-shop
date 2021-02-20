package com.example.bicycleshop.utilities;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;

public class Formatter {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
	private static final DecimalFormat DECIMAL_FORMAT;
	
	static {
		String pattern = "###,###.##";
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DECIMAL_FORMAT = new DecimalFormat(pattern, symbols);
	}
	
	public static DateTimeFormatter getDateTimeFormatter() {
		return DATE_TIME_FORMATTER;
	}
	
	public static DecimalFormat getDecimalFormatter(){
		return DECIMAL_FORMAT;
	}
}
