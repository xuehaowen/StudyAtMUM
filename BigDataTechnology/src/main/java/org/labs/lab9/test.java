package org.labs.lab9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static final int NUM_FIELDS = 9;

	/** The sample log entry to be parsed. */
	public static final String logEntryLine = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450";
	
	public static void main(String[] args) throws ParseException {
		String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+)";

	    System.out.println("Using RE Pattern:");
	    System.out.println(logEntryPattern);

	    System.out.println("Input line is:");
	    System.out.println(logEntryLine);

	    Pattern p = Pattern.compile(logEntryPattern);
	    Matcher matcher = p.matcher(logEntryLine);
	    if (!matcher.matches()) {
	      System.err.println("Bad log entry (or problem with RE?):");
	      System.err.println(logEntryLine);
	      return;
	    }
	    System.out.println("IP Address: " + matcher.group(1));
	    System.out.println("Date&Time: " + matcher.group(4));
	    System.out.println("Request: " + matcher.group(5));
	    System.out.println("Response: " + matcher.group(6));
	    System.out.println("Bytes Sent: " + matcher.group(7));
//	    if (!matcher.group(8).equals("-"))
//	      System.out.println("Referer: " + matcher.group(8));
//	    System.out.println("Browser: " + matcher.group(9));
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MMM/yyyy");
		Date startDate = sdf1.parse("2004.03.08");
		Date endDate = sdf1.parse("2004.03.12");
	    String date = "08/Mar/2004:16:24:16 -0400";
	    System.out.println(sdf2.parse(date).compareTo(startDate));
	    System.out.println(sdf2.parse(date).compareTo(endDate));
	  }
}
