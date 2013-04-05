package org.jewel.util;

import java.text.NumberFormat;

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
}
