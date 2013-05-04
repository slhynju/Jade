package org.jade.util;

import java.text.NumberFormat;

/**
 * Various Number utility methods.
 * 
 * @author slhynju
 */
public final class NumberUtil {

	public static NumberFormat newFormat(int minFractionDigits,
			int maxFractionDigits, boolean groupingUsed) {
		NumberFormat f = NumberFormat.getInstance();
		f.setMinimumFractionDigits(minFractionDigits);
		f.setMaximumFractionDigits(maxFractionDigits);
		f.setGroupingUsed(groupingUsed);
		return f;
	}

	public static NumberFormat newFormat(int fraction) {
		return newFormat(fraction, fraction, false);
	}

	public static int toInt(String s, int defaultValue) {
		if (StringUtil.isEmpty(s)) {
			return defaultValue;
		}
		return Integer.parseInt(s);
	}

	public static long toLong(String s, long defaultValue) {
		if (StringUtil.isEmpty(s)) {
			return defaultValue;
		}
		return Long.parseLong(s);
	}

	public static double toDouble(String s, double defaultValue) {
		if (StringUtil.isEmpty(s)) {
			return defaultValue;
		}
		return Double.parseDouble(s);
	}

	public static String toS(double d, int minFraction, int maxFraction,
			boolean groupingUsed) {
		NumberFormat format = newFormat(minFraction, maxFraction, groupingUsed);
		return format.format(d);
	}

	public static String toS(double d, int fraction) {
		return toS(d, fraction, fraction, false);
	}

}
