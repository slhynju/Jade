package org.jewel.util;

import java.text.NumberFormat;
import java.util.Date;

import org.jewel.util.date.DateUtil;

/**
 * Utility class to support equals() method.
 * 
 * @author slhynju
 * @ThreadSafe
 */
public final class EqualsUtil {

	public static boolean isEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		return o1 != null && o1.equals(o2);
	}

	public static boolean notEquals(Object o1, Object o2) {
		return !isEquals(o1, o2);
	}

	public static boolean isEquals(int i1, int i2) {
		return i1 == i2;
	}

	public static boolean notEquals(int i1, int i2) {
		return i1 != i2;
	}

	public static boolean isEquals(long l1, long l2) {
		return l1 == l2;
	}

	public static boolean notEquals(long l1, long l2) {
		return l1 != l2;
	}

	public static boolean isEquals(boolean b1, boolean b2) {
		return b1 == b2;
	}

	public static boolean notEquals(boolean b1, boolean b2) {
		return b1 != b2;
	}

	public static boolean isEquals(double d1, double d2, int fraction) {
		NumberFormat format = NumberUtil.newFormat(fraction);
		String s1 = format.format(d1);
		String s2 = format.format(d2);
		return isEquals(s1, s2);
	}

	public static boolean notEquals(double d1, double d2, int fraction) {
		return !isEquals(d1, d2, fraction);
	}

	public static boolean isEquals(char c1, char c2) {
		return c1 == c2;
	}

	public static boolean notEquals(char c1, char c2) {
		return c1 != c2;
	}

	public static boolean isEqualsIgnoreCase(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}
		return s1 != null && s1.equalsIgnoreCase(s2);
	}

	public static boolean notEqualsIgnoreCase(String s1, String s2) {
		return !isEqualsIgnoreCase(s1, s2);
	}

	public static boolean isEquals(Date d1, Date d2, int precision) {
		Date t1 = DateUtil.truncate(d1, precision);
		Date t2 = DateUtil.truncate(d2, precision);
		return isEquals(t1, t2);
	}

	public static boolean notEquals(Date d1, Date d2, int precision) {
		return !isEquals(d1, d2, precision);
	}

}
