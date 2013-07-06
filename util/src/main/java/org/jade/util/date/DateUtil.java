package org.jade.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jade.JadeException;
import org.jade.util.StringUtil;

public final class DateUtil {

	public static final String SIMPLE_FORMAT = "yyyy-MM-dd";

	public static final String LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String toS(Date d, String format) {
		if (d == null) {
			return "";
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(d);
	}

	public static String toS(Date d, DateFormat format) {
		if (d == null) {
			return "";
		}
		return format.format(d);
	}

	public static Date toDate(String value, String format) {
		if (StringUtil.isEmpty(value)) {
			return null;
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		try {
			return f.parse(value);
		} catch (ParseException e) {
			String message = String.format(
					"Cannot parse Date from String %s with format %s.", value,
					format);
			throw new JadeException(message);
		}
	}

	public static Date toDate(String value, DateFormat format) {
		if (StringUtil.isEmpty(value)) {
			return null;
		}
		try {
			return format.parse(value);
		} catch (ParseException e) {
			String message = String.format(
					"Cannot parse Date from String %s with format %s.", value,
					format);
			throw new JadeException(message);
		}
	}

	@SuppressWarnings("fallthrough")
	public static Date truncate(Date d, int precision) {
		Calendar c = toCalendar(d);
		switch (precision) {
		case Calendar.YEAR:
			c.set(Calendar.MONTH, Calendar.JANUARY);
		case Calendar.MONTH:
			c.set(Calendar.DATE, 1);
		case Calendar.DATE:
			c.set(Calendar.HOUR_OF_DAY, 0);
		case Calendar.HOUR_OF_DAY:
			c.set(Calendar.MINUTE, 0);
		case Calendar.MINUTE:
			c.set(Calendar.SECOND, 0);
		case Calendar.SECOND:
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		case Calendar.DAY_OF_WEEK:
			c.set(Calendar.DAY_OF_WEEK,
					c.getActualMinimum(Calendar.DAY_OF_WEEK));
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		default:
			String message = String
					.format("Cannot truncate date with given field %d. Only understands field YEAR(1), MONTH(2), DATE(5), DAY_OF_WEEK(7), HOUR_OF_DAY(11), MINUTE(12), SECOND(13).",
							precision);
			throw new JadeException(message);
		}
	}

	public static Date add(Date d, int field, int amount) {
		Calendar c = toCalendar(d);
		c.add(field, amount);
		return c.getTime();
	}

	public static Date max(Date d, int field) {
		Calendar c = toCalendar(d);
		max(c, field);
		return c.getTime();
	}

	public static void max(Calendar c, int field) {
		c.set(field, c.getActualMaximum(field));
	}

	public static Date min(Date d, int field) {
		Calendar c = toCalendar(d);
		min(c, field);
		return c.getTime();
	}

	public static void min(Calendar c, int field) {
		c.set(field, c.getActualMinimum(field));
	}

	public static Calendar toCalendar(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	public static Date today() {
		return truncate(new Date(), Calendar.DATE);
	}

	public static TimeInterval getCurrentDay() {
		return getCurrentTimeInterval(Calendar.DATE);
	}

	public static TimeInterval getCurrentWeek() {
		Date d = new Date();
		Date start = truncate(d, Calendar.DAY_OF_WEEK);
		Date end = add(start, Calendar.DATE, 7);
		return new TimeInterval(start, end);
	}

	public static TimeInterval getCurrentMonth() {
		return getCurrentTimeInterval(Calendar.MONTH);
	}

	public static TimeInterval getCurrentYear() {
		return getCurrentTimeInterval(Calendar.YEAR);
	}

	public static TimeInterval getCurrentHour() {
		return getCurrentTimeInterval(Calendar.HOUR_OF_DAY);
	}

	public static TimeInterval getCurrentMinute() {
		return getCurrentTimeInterval(Calendar.MINUTE);
	}

	private static TimeInterval getCurrentTimeInterval(int field) {
		Date d = new Date();
		Date start = truncate(d, field);
		Date end = add(start, field, 1);
		return new TimeInterval(start, end);
	}

}
